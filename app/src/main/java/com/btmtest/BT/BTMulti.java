//*CID://+varaR~:                             update#=  508;       //~varaR~
//********************************************************************************//~v101I~
//2022/10/06 vara chk appversion unmatch other than rule version unmatch//~varaI~
//2022/09/24 var8 display profile icon                             //~var8I~
//2022/03/28 vam3 android12(api31) deprecated Bluetooth.getDefaultAdapter//~vam3I~
//2022/01/31 vaji change color of top left to identify server      //~vajiI~
//2022/01/30 vajf (bug)if canceled on orientation dialog on client at startgame. back button on server cause dump on client//~vajfI~
//2021/06/19 va9g SwTrainigMode was not cleared, startgame Hung at match as client after play alone//~va9gI~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2020/11/20 va46 (Bug)reconnected member could not be disconnect  //~va46I~
//2020/10/19 va1b (Bug)server crashes by @@add from client because thread=null; BTCDialog EeditText textchange listener is called by Button push by focus change.//~va1bI~
//2020/10/14 va17 chk server for "Game" button click(stop start process if start game from client)//~va02I~
//                Game button on client is not disabled because connectionType was set to 0 because thread=null yet before openClien() at updateButtonStatus//~va02I~
//2020/04/13 va02:At Server,BackButton dose not work when client app canceled by androiud-Menu button//~va02I~
//@002:20181103 use enum                                           //~@002I~
//@001:20181103 updatebuttonstatus over config change              //~@001I~
//****************************************************************************//~@001I~

package com.btmtest.BT;//~1AedI~//~1AebI~

import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.dialog.SuspendIOErrReqDlg;
import com.btmtest.game.Accounts;
import com.btmtest.game.Status;
import com.btmtest.game.UserAction;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Alert;
import com.btmtest.utils.ProgDlg;
import com.btmtest.utils.Utils;//~1AebR~
import com.btmtest.utils.UView;
import com.btmtest.dialog.BTCDialog;

import com.btmtest.R;                                              //~1AebR~
import com.btmtest.wifi.WDA;

import static com.btmtest.AG.*;
import static com.btmtest.BT.Members.*;
import static com.btmtest.BT.enums.MsgIDConst.*;                   //~@002R~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@002I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;

import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;//~1AebI~
import android.graphics.Point;
import android.content.Intent;

import java.util.Arrays;


public class BTMulti                                               //~1AebR~
{                                                                  //~1AebI~
//    public static final int MSGID_NAME=1;                          //~1AebR~//~@002R~
//    public static final int MSGID_MEMBER_ADD=2;                    //~1AebI~//~@002R~
//    public static final int MSGID_MEMBER_DELETE=3;                 //~1AebI~//~@002R~
//    public static final int MSGID_NEWNAME=4;                     //~@002R~
//    public static final int MSGID_APP=5;//~1AebI~                //~@002R~
    private static final int MSG_NAMEQ_POS_RECONNECT=2;            //~0107I~
    private static final int MSG_NAMEQ_POS_APPVERSION=3;           //~varaI~
    private static final int MSG_NAMER_POS_RECONNECT=3;            //~0107I~
    private static final int MSG_NAMER_POS_APPVERSION=4;           //~varaI~
	                                                               //~1AebI~
    public static final String DEVICENAME_TRAINING="#This#";       //~va66I~
                                                                   //~va66I~
    private Boolean swSecureConnect=true;
    private boolean swAlertAction;                 //~1Ac5I~       //~1AebI~
    public static int maxClient;                                   //~1AebI~
    public int ctrClient=0;                                        //~1AebR~
    public Members BTGroup;                                        //~1AebR~

    public static final int ROLE_UNDEFINED=0;                      //~1AebI~
    public static final int ROLE_SERVER=1;                         //~1AebI~
    public static final int ROLE_CLIENT=2;                         //~1AebI~
                                                                   //~@002I~
    public int memberRole=ROLE_UNDEFINED;                   //~1AebI~//~@002R~
    public int oldMemberRole=ROLE_UNDEFINED;                       //~0120I~
    public String memberNotConnectedId;                     //~1AebI~//~@002R~
    public String serverDeviceName="";                             //~1AebI~
    public String localDeviceName="";                              //~1AebI~
//  protected int idxMember;	//index on BTGroup                 //~0221R~
    public boolean swAcceptWaitingDiscoverable;                    //~@002I~
    private boolean swRuleSettingLock;                             //~9406I~
//  private boolean swRuleSettingSynchedAll;                       //~9406I~//~9621R~
    private String  syncDateRuleSettingSynchedAll;                 //~9406I~
    private boolean swWaitingSyncDate;                                     //~9621I~
    // *******************************************************      //~1AebI~
    // *from IPMulti.constructor                                   //~9828I~
    // *******************************************************     //~9828I~
    public BTMulti(int Pmaxclient)                                 //~1AebR~
	{                                                              //~1AebI~
    	if (Dump.Y) Dump.println("BTMulti:constructor maxclient="+Pmaxclient+",memberRole="+memberRole);//~1AebI~//~@002R~
        AG.aBTMulti=this;                                          //~1AebR~
//      AG.aBTI=BTI.createBTI();                                   //~1AebI~//~vam3R~
        BTI.createBTI();                                           //~vam3I~
        maxClient=Pmaxclient;                                       //~1AebI~
	    memberNotConnectedId=Utils.getStr(R.string.NotConnected);  //~1AebI~
        BTGroup=new Members(maxClient,memberNotConnectedId);       //~1AebR~
    }                                                              //~1AebR~
    //*******************************************************      //~1AebI~
    //* add group member                                           //~1AebI~
    //*******************************************************      //~1AebI~
    public boolean updateMember(String Pname,BluetoothSocket Psocket,int Pstatus)//~1AebR~
	{                                                              //~1AebI~
    	int rc=BTGroup.update(Pname,Psocket,Pstatus);              //~1AebR~
    	if (rc<0)                                                  //~1AebR~
        {                                                          //~1AebI~
        	UView.showToastLong(AG.resource.getString(R.string.ErrMemberOverflow,maxClient));//~1AebI~
        	return false;                                          //~1AebI~
        }                                                          //~1AebI~
//      idxMember=rc;                                              //~1AebI~//~0221R~
        if (Dump.Y) Dump.println("BTMulti.updateMember idxmember="+rc+",member="+Pname+",socket="+Psocket.toString()+",status="+Pstatus);//~1AebR~//~9817R~//~9824R~//~0221R~
        ctrClient=BTGroup.getConnectedCtr();                       //~1AebI~
        return true;                                               //~1AebI~
    }                                                              //~1AebI~
    //*******************************************************      //~vajiI~
    public static int getConnectedCtr()                            //~vajiI~
	{                                                              //~vajiI~
    	int rc=0;                                                  //~vajiI~
        if (AG.aBTMulti!=null && AG.aBTMulti.BTGroup!=null)        //~vajiI~
    		rc=AG.aBTMulti.BTGroup.getConnectedCtr();      //~vajiI~
    	if (Dump.Y) Dump.println("BTMulti.getConnectedCtr rc="+rc);//~vajiI~
        return rc;                                                 //~vajiI~
    }                                                              //~vajiI~
    //*******************************************************      //~1AebI~
//  public boolean updateMember(String Pname,BTIOThread Pthread)   //~1AebI~//~0117R~
    public int updateMember(String Pname,BTIOThread Pthread)       //~0117I~
	{                                                              //~1AebI~
    	int rc=BTGroup.update(Pname,Pthread);                      //~1AebI~
//    	if (rc<0)                                                  //~1AebI~//~0117R~
//      {                                                          //~1AebI~//~0117R~
//        	return rc;se;                                          //~1AebI~//~0117R~
//      }                                                          //~1AebI~//~0117R~
        if (Dump.Y) Dump.println("BTMulti.updateMember name="+Pname+",thread="+(Pthread==null?"null":Pthread.toString()));//~1AebR~//~9817R~//~9A10R~
//      return true;                                               //~1AebI~//~0117R~
        return rc;  //idx                                          //~0117I~
    }                                                              //~1AebI~
    //*******************************************************      //~1AebI~
    //*received yourname from client at connected                  //~1AebI~
    //*******************************************************      //~1AebI~
//  public boolean updateMember(String Pname,String Pyourname,int Pstatus)//~1AebR~//~9619R~
    public boolean updateMember(String Pname,String Pyourname,int Pstatus,String PsyncDate)//~9619I~
	{                                                              //~1AebI~
//  	int rc=BTGroup.update(Pname,Pyourname,Pstatus);            //~1AebR~//~9619R~
    	int rc=BTGroup.update(Pname,Pyourname,Pstatus,PsyncDate); //~9619I~
    	if (rc<0)                                                  //~1AebI~
        {                                                          //~1AebI~
        	return false;                                          //~1AebI~
        }                                                          //~1AebI~
        if (Dump.Y) Dump.println("BTMulti.updateMember name="+Pname+",youtrname="+(Pyourname==null?"null":Pyourname)+",status="+Pstatus+",synchDate="+PsyncDate);//~1AebR~//~@002R~//~9619R~
	    notifyToMembers(true/*connected*/,Pname,Pyourname);        //~1AebR~
        return true;                                               //~1AebI~
    }                                                              //~1AebI~
    //*******************************************************      //~va46I~
    //*at reconnect search by macaddr                              //~va46I~
    //*******************************************************      //~va46I~
    public boolean updateMember(String Pname,String Pyourname,String PmacAddr,String PsyncDate,boolean PswNotify)//~va46R~
	{                                                              //~va46I~
    	if (Dump.Y) Dump.println("BTMulti.updateMember name="+Pname+",yourname="+Pyourname+",macAddr="+PmacAddr+",syncData="+PsyncDate);//~va46I~
    	int rc=BTGroup.updateReconnect(PmacAddr,Pname,Pyourname,PsyncDate);//~va46I~
    	if (rc<0)                                                  //~va46I~
        {                                                          //~va46I~
        	return false;                                          //~va46I~
        }                                                          //~va46I~
        if (PswNotify)                                             //~va46I~
	        notifyToMembers(true/*connected*/,Pname,Pyourname);    //~va46R~
        return true;                                               //~va46I~
    }                                                              //~va46I~
    //*******************************************************      //~1AebI~
    //*received yourname from client at connected                  //~1AebI~
    //*******************************************************      //~1AebI~
//  public boolean updateMember(String Pname,String Pyourname)     //~1AebI~//~9619R~
    public boolean updateMember(String Pname,String Pyourname,String PsyncDate)//~9619I~
	{                                                              //~1AebI~
    	if (Dump.Y) Dump.println("BTMulti.updateMember name="+Pname+",yourname="+Pyourname+",synchDate="+PsyncDate);//~@002I~//~9619R~
    	int status=Members.MS_REMOTECLIENT;                        //~1AebI~
//      boolean rc=updateMember(Pname,Pyourname,status);           //~1AebI~//~9619R~
        boolean rc=updateMember(Pname,Pyourname,status,PsyncDate); //~9619I~
        return rc;                                                 //~1AebI~
    }                                                              //~1AebI~
    //*******************************************************      //~9619I~
    public boolean updateMember(String Pname,String PsyncDate)     //~9619I~
	{                                                              //~9619I~
    	if (Dump.Y) Dump.println("BTMulti.updateMember update synchDate name="+Pname+",synchDate="+PsyncDate);//~9619I~
    	int rc=BTGroup.update(Pname,PsyncDate);                   //~9619I~
    	if (rc<0)                                                  //~9619I~
        {                                                          //~9619I~
        	return false;                                          //~9619I~
        }                                                          //~9619I~
        return true;                                               //~9619I~
    }                                                              //~9619I~
    //*******************************************************      //~0110I~
    protected void setReconnectErr(String Pname)                     //~0110I~//~0116R~
	{                                                              //~0110I~
    	if (Dump.Y) Dump.println("BTMulti.setReconnectErr name="+Pname);//~0110I~
    	BTGroup.setReconnectErr(Pname,true/*swOn*/);               //~0110I~
    }                                                              //~0110I~
    //*******************************************************      //~9621I~
    private boolean updateLocal(String PsyncDate)                   //~9621I~//~9907R~
	{                                                              //~9621I~
    	boolean rc=BTGroup.updateLocal(PsyncDate);	//changed      //~9621R~
    	if (Dump.Y) Dump.println("BTMulti.updateLocal synchDate="+PsyncDate+",rc="+rc);//~9621I~
        return rc;                                                 //~9621R~
    }                                                              //~9621I~
    //*******************************************************      //~9621I~
    private boolean updateRemote(int Pidx,String PsyncDate)        //~9621R~
	{                                                              //~9621I~
    	boolean rc=BTGroup.updateRemote(Pidx,PsyncDate);	//changed//~9621I~
    	if (Dump.Y) Dump.println("BTMulti.updateRemote idx="+Pidx+",synchDate="+PsyncDate+",rc="+rc);//~9621I~
        return rc;                                                 //~9621I~
    }                                                              //~9621I~
    //*******************************************************      //~9621I~
    public boolean updateServer(String PsyncDate)                  //~9621I~
	{                                                              //~9621I~
    	if (Dump.Y) Dump.println("BTMulti.updateServer synchDate="+PsyncDate);//~9621I~
    	boolean rc=BTGroup.updateServer(PsyncDate);                //~9621I~
        return rc;                                                 //~9621I~
    }                                                              //~9621I~
    //*******************************************************      //~9405I~
    public static boolean isUndefinedDevice()                      //~9405I~
	{                                                              //~9405I~
        boolean rc=AG.aBTMulti==null || AG.aBTMulti.memberRole==ROLE_UNDEFINED;//~9405I~
    	if (Dump.Y) Dump.println("BTMulti.isUndefinedDevice rc="+rc);//~9405I~
        return rc;                                                 //~9405I~
    }                                                              //~9405I~
    //*******************************************************      //~9405I~
    public static boolean isServerDevice()                         //~9405I~
	{                                                              //~9405I~
        boolean rc=AG.aBTMulti!=null && AG.aBTMulti.memberRole==ROLE_SERVER;//~9405I~
    	if (Dump.Y) Dump.println("BTMulti.isServerDevice rc="+rc); //~9405I~
        return rc;                                                 //~9405I~
    }                                                              //~9405I~
    //*******************************************************      //~9405I~
    public static boolean isClientDevice()                         //~9405I~
	{                                                              //~9405I~
        boolean rc=AG.aBTMulti!=null && AG.aBTMulti.memberRole==ROLE_CLIENT;//~9405I~
    	if (Dump.Y) Dump.println("BTMulti.isClientDevice rc="+rc); //~9405I~
        return rc;                                                 //~9405I~
    }                                                              //~9405I~
    //*******************************************************      //~9406I~
    public static String getYourNameServer()                       //~9406I~
	{                                                              //~9406I~
    	String rc="";                                              //~9406I~
        if (AG.aBTMulti!=null)                                     //~9406I~
	        rc=AG.aBTMulti.BTGroup.getYourNameServer();            //~9406I~
    	if (Dump.Y) Dump.println("BTMulti.getYourNameServer rc="+rc);//~9406I~
        return rc;                                                 //~9406I~
    }                                                              //~9406I~
    //*******************************************************      //~9405I~
    //*chk SS_OK                                                   //~9621I~
    //*******************************************************      //~9621I~
    public static boolean isRuleSynched()                          //~9405I~
	{                                                              //~9405I~
//      boolean rc=AG.aBTMulti!=null && AG.aBTMulti.BTGroup.isRuleSynched();//~9405R~//~9620R~
        boolean rc=AG.aBTMulti!=null && AG.aBTMulti.BTGroup.isRuleSynched(false/*swMsg*/);//~9620I~
    	if (Dump.Y) Dump.println("BTMulti.isRuleSynched rc="+rc);  //~9405I~
        return rc;                                                 //~9405I~
    }                                                              //~9405I~
    //*******************************************************      //~9405I~
//  public static boolean setRuleOutOfSynch()                      //~9405I~//~9B25R~
    public static boolean setRuleOutOfSynch(String PremoteDevicename)//~9B25I~
	{                                                              //~9405I~
    	if (Dump.Y) Dump.println("BTMulti.setRuleOutOfSynch remote="+PremoteDevicename);     //~9405I~//~9B25R~
        if (AG.aBTMulti==null)                                     //~9405I~
        	return false;                                          //~9405I~
//      AG.aBTMulti.BTGroup.setRuleOutOfSync();                    //~9405R~//~9B25R~
        AG.aBTMulti.BTGroup.setRuleOutOfSync(PremoteDevicename);   //~9B25I~
    	if (Dump.Y) Dump.println("BTMulti.setRuleOutOfSynch set false");//~9405I~
        return true;                                               //~9405I~
    }                                                              //~9405I~
    //*******************************************************      //~9406I~
    public void setLockRuleSetting(boolean PswLock)                //~9406I~
	{                                                              //~9406I~
    	if (Dump.Y) Dump.println("BTMulti.setLockRuleSetting sw="+PswLock);//~9406I~
        swRuleSettingLock=PswLock;                                 //~9406I~
    }                                                              //~9406I~
    //*******************************************************      //~9406I~
    public void setRuleSettingSynchedAll(boolean PswOK,String PsyncDate)//~9406I~
	{                                                              //~9406I~
    	if (Dump.Y) Dump.println("BTMulti.setRuleSettingSynchedAll PswOK="+PswOK+",syncDate="+PsyncDate+",swWaitingSyncDate="+swWaitingSyncDate);//~9406I~//~0112R~//~va66R~
//      swRuleSettingSynchedAll=PswOK;                             //~9406I~//~9621R~
        syncDateRuleSettingSynchedAll=PsyncDate;                   //~9406I~
    	if (swWaitingSyncDate)                                     //~9621I~
        {                                                          //~9621I~
//      	swWaitingSyncDate=false;                               //~9621I~//~9812R~
    		if (PswOK)                                             //~9621I~
            {                                                      //~9621I~
   		     	swWaitingSyncDate=false;                           //~9812I~
            	AG.aGC.startGameGo();                              //~9621I~
            	sendStartGame(PsyncDate);                          //~9621I~
            }                                                      //~9621I~
        }                                                          //~9621I~
    }                                                              //~9406I~
    //*******************************************************      //~va66I~
    //*from GC.startGame                                           //~va66I~
    //*******************************************************      //~va66I~
    public void startGameTrainingMode()                            //~va66I~
	{                                                              //~va66I~
    	if (Dump.Y) Dump.println("BTMulti.startGameTrainigMode swWaitingSyncdate="+swWaitingSyncDate);//~va66I~
        syncDateRuleSettingSynchedAll=AG.ruleSyncDate;             //~va66I~
        swWaitingSyncDate=false;                                   //~va66I~
        AG.aGC.startGameGo();                                      //~va66I~
    }                                                              //~va66I~
    //*******************************************************      //~9812I~
    public void endgameIfDisconnectedAtRuleSettingSynch()          //~9812I~
	{                                                              //~9812I~
    	if (Dump.Y) Dump.println("BTMulti.endgameIfDisconnectedAtRuleSettingSynch swWaitingSynchDate="+swWaitingSyncDate);//~9812R~
    	if (swWaitingSyncDate)                                     //~9812I~
        {                                                          //~9812I~
	    	swWaitingSyncDate=false;                               //~9812I~
            AG.aGC.disconnectedAtStartGame();                       //~9812I~
        }                                                          //~9812I~
    }                                                              //~9812I~
//    //*******************************************************      //~9406I~//~9621R~
//    public boolean isRuleSettingSynchedAll()                          //~9406I~//~9621R~
//    {                                                              //~9406I~//~9621R~
//        if (Dump.Y) Dump.println("BTMulti.isRuleSettingSynchedAll rc="+swRuleSettingSynchedAll);//~9406I~//~9621R~
//        return swRuleSettingSynchedAll;                            //~9406I~//~9621R~
//    }                                                              //~9406I~//~9621R~
//    //*******************************************************      //~9406I~//~9621R~
//    public boolean isLockRuleSetting()                             //~9406I~//~9621R~
//    {                                                              //~9406I~//~9621R~
//        if (Dump.Y) Dump.println("BTMulti.isLockRuleSetting sw="+swRuleSettingLock);//~9406I~//~9621R~
//        return swRuleSettingLock;                                  //~9406I~//~9621R~
//    }                                                              //~9406I~//~9621R~
    //*******************************************************      //~9405I~
    public static String[][] getRuleSyncStatus()                   //~9405R~
	{                                                              //~9405I~
    	if (Dump.Y) Dump.println("BTMulti.getRuleSyncStatus");     //~9405I~
        if (AG.aBTMulti==null)                                     //~9405I~
        	return null;                                           //~9405I~
        String[][] sss=AG.aBTMulti.BTGroup.getRuleSynchStatus();    //~9405R~
        return sss;                                                //~9405R~
    }                                                              //~9405I~
    //*******************************************************      //~9406I~
    public void setRuleSyncStatus(int Pidx,boolean PswOK,String PsyncDate)//~9406R~
	{                                                              //~9406I~
    	if (Dump.Y) Dump.println("BTMulti.setRuleSyncStatus idx="+Pidx+",swOK="+PswOK+",syncDate="+Utils.toString(PsyncDate));//~9406I~//~0323R~
        BTGroup.setRuleSyncStatus(Pidx,PswOK,PsyncDate);           //~9406R~
    }                                                              //~9406I~
    //*******************************************************      //~0323I~
    public void setRuleSyncStatusReply(int Pidx,boolean PswOK,String PsyncDate)//~0323I~
	{                                                              //~0323I~
    	if (Dump.Y) Dump.println("BTMulti.setRuleSyncStatusReplay idx="+Pidx+",swOK="+PswOK+",syncDate="+Utils.toString(PsyncDate));//~0323I~
        BTGroup.setRuleSyncStatusReplay(Pidx,PswOK,PsyncDate);     //~0323I~
    }                                                              //~0323I~
    //*******************************************************      //~1AebI~
    //* add member of other client notified from server            //~1AebI~
    //* (name,yourname,idx,),(name,yourname,idx),...               //~@002I~
    //*******************************************************      //~1AebI~
    public boolean updateNotified(String[] Pdnyn)                  //~1AebI~
	{                                                              //~1AebI~
    	int sz=Pdnyn.length;                                        //~1AebI~
        if (Dump.Y) Dump.println("BTMulti updateNotified dnyn size="+sz+",Pdnyn="+Utils.toString(Pdnyn));//~1AebI~//~vam3R~
        for (int ii=1;ii+2<sz;ii+=3)                               //~1AebI~//~@002R~
        {                                                          //~1AebI~
        	if (Dump.Y) Dump.println("BTMulti.updateNotified name="+Pdnyn[ii]+",yourname="+Pdnyn[ii+1]);//~@002I~
        	String devicename=Pdnyn[ii];                           //~1AebI~
        	String yourname=Pdnyn[ii+1];                           //~1AebI~
        	int idx=Utils.parseInt(Pdnyn[ii+2],0);                 //~@002R~
            if (!devicename.equals(localDeviceName))               //~1AebR~
		    	updateAdd(devicename,yourname,devicename.equals(serverDeviceName) ? MS_SERVER : Members.MS_REMOTECLIENT);//~1AebI~
            updateSeq(devicename,idx);	//keep to same index as server                             //~@002I~//~0119R~
        }                                                          //~1AebI~
        return true;                                               //~1AebI~
    }                                                              //~1AebI~
    //*******************************************************      //~@002I~
    //*On Client                                                   //~0221I~
    //*sort Members same Seq as Server                             //~@002I~
    //*******************************************************      //~@002I~
    private void updateSeq(String Pname,int Pidx)                  //~@002I~
	{                                                              //~@002I~
        if (Dump.Y) Dump.println("BTMulti.updateSeq name="+Pname+",idx="+Pidx);//~@002I~
    	BTGroup.updateSeq(Pname,Pidx);                             //~@002I~
        BTIOThread t=(BTIOThread)BTGroup.getThread(BTGroup.idxServer);//~0221R~
      if (t!=null)	//server entry thread is null on server        //~va1bI~
        t.idxMember=BTGroup.idxServer;                             //~0221R~
    }                                                              //~@002I~
    //*******************************************************      //~1AebI~
    //* add member not connected(myself and other client)          //~1AebI~
    //*******************************************************      //~1AebI~
//  protected boolean updateAdd(String Pname,String Pyourname,int Pstatus)//~1AebR~//~9723R~//~9A02R~
    protected int updateAdd(String Pname,String Pyourname,int Pstatus)//~9A02I~
	{                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti updateAdd name="+Pname+",youtrname="+(Pyourname==null?"null":Pyourname)+",status="+Pstatus);//~1AebR~//~9824M~
        if (Dump.Y) Dump.println("BTMulti.updateAdd before="+BTGroup.toString());//~9A10I~//~9B05R~
//  	BTGroup.updateAdd(Pname,Pyourname,Pstatus);                //~1AebR~//~9A02R~
//      return true;                                               //~1AebI~//~9A02R~
        int rc=BTGroup.updateAdd(Pname,Pyourname,Pstatus);         //~9A02I~
        if (Dump.Y) Dump.println("BTMulti.updateAdd after="+BTGroup.toString());//~9A10I~//~9B05R~
        if (Dump.Y) Dump.println("BTMulti updateAdd rc="+rc);      //~9A02I~
        return rc;                                                 //~9A02I~
    }                                                              //~1AebI~
    //*******************************************************      //~1AebI~
    public int getMemberIndex(String Pname)                        //~1AebR~
	{                                                              //~1AebI~
        return BTGroup.search(Pname);                              //~1AebR~
    }                                                              //~1AebI~
    //*******************************************************      //~1AebI~
    public BluetoothSocket getMemberSocket(String Pname)           //~1AebI~
	{                                                              //~1AebI~
        BluetoothSocket s=BTGroup.getMemberSocket(Pname);          //~1AebI~
        if (Dump.Y) Dump.println("getMemberSocket devicename="+Pname+",socket="+(s==null?"null":s.toString()));//~1AebI~
        return s;                                                  //~1AebI~
    }                                                              //~1AebI~
    //*******************************************************      //~1AebI~
    public BTIOThread getMemberThread(String Pname)                //~1AebI~
	{                                                              //~1AebI~
        BTIOThread t=(BTIOThread)BTGroup.getMemberThread(Pname);   //~1AebI~
        if (Dump.Y) Dump.println("getMemberThread devicename="+Pname+",thread="+(t==null?"null":t.toString()));//~1AebI~
        return t;                                                  //~1AebI~
    }                                                              //~1AebI~
    //*******************************************************      //~1AebI~
    //*rc:deleted                                                  //~1AebI~
    //*******************************************************      //~1AebI~
    protected boolean removeMember(String Pname)                     //~1AebR~//~9729R~
	{                                                              //~1AebI~
        int idx=BTGroup.remove(Pname);                             //~1AebR~
        ctrClient=BTGroup.getConnectedCtr();                       //~1AebR~
        if (Dump.Y) Dump.println("BTMulti.removeMember devicename="+Pname+",idx="+idx+",new ctrClient="+ctrClient);//~1AebI~//~9B07R~
        return idx>=0;                                             //~1AebR~
    }                                                              //~1AebI~
    //*******************************************************      //~1AebI~
    //*BTService.connected-->MESSAGE_DEVICE_NAME-->BTControl.handleMessage-->BTI.connected-->BTMulti.onConnected//~1AebI~
    //*******************************************************      //~1AebI~
    public void onConnected(BluetoothSocket Psocket,String Premotedevicename,String Plocaldevicename,Boolean Pswclient)//~1AebR~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti:onConnected: reset swTrainingMode old="+AG.swTrainingMode);//~va9gI~
        AG.swTrainingMode=false;                                   //~va9gI~
        String addr=Psocket.getRemoteDevice().getAddress();   //hardware addr//~9817R~
//      setRuleOutOfSynch();                                       //~9405I~//~9B25R~
        setRuleOutOfSynch(Premotedevicename);                      //~9B25I~
    	if (Dump.Y) Dump.println("BTMulti:onConnected device="+Premotedevicename+"="+addr+",swClient="+Pswclient+",memberRole="+memberRole);//~1AebR~//~9723R~
        int role=Pswclient ? ROLE_CLIENT : ROLE_SERVER;            //~1AebR~
        if (memberRole==ROLE_UNDEFINED)                            //~1AebI~
        {                                                          //~1AebI~
	    	localDeviceName=Plocaldevicename;                      //~1AebI~
        	memberRole=role;                                       //~1AebI~
            if (!Pswclient)	//server                               //~1AebI~
            {                                                      //~0120I~
	            if (oldMemberRole==ROLE_CLIENT) //client at disconnedcted, changed to server//~0120I~
					resetMemberDisconnectedClear();	//reset other of local and server(thread!=null)//~0120I~
				updateAdd(Plocaldevicename,AG.YourName, MS_SERVER|Members.MS_LOCAL); //add server itself//~1AebR~
            }                                                      //~0120I~
        }                                                          //~1AebI~
        else                                                       //~1AebI~
        if (role!=memberRole)                                      //~1AebI~
        {                                                          //~1AebI~
//      	UView.showToastLong(R.string.ErrMixedRole);               //~1AebI~//~@002R~
			Alert.showMessage(null/*title*/,Utils.getStr(R.string.ErrMixedRole));//~v@@@I~//~@002I~
        }                                                          //~1AebI~
        if (Pswclient) //client                                    //~1AebI~
        {                                                          //~1AebI~
//	        BTGroup=new Members(maxClient,memberNotConnectedId);   //clear all//~1AebR~
			updateAdd(Plocaldevicename,AG.YourName,Members.MS_CLIENT|Members.MS_LOCAL);               //add client itself//~1AebR~
        }                                                          //~1AebI~
        updateMember(Premotedevicename,Psocket,Pswclient ? MS_SERVER : Members.MS_REMOTECLIENT);    //add remote, client for server, server for client//~1AebR~//~9824R~
//      BTCDialog.onConnected(Premotedevicename,addr,Pswclient);   //~1AebR~//~va02R~
        if (!Pswclient)                                              //~1AebI~
        {                                                          //~1AebI~
        	serverDeviceName=Plocaldevicename;                     //~1AebI~
  	    	openServer(Psocket,Plocaldevicename,Premotedevicename);//~1AebR~
        }                                                          //~1AebI~
        else                                                       //~1AebI~
        {                                                          //~1AebI~
        	serverDeviceName=Premotedevicename;                    //~1AebI~
	    	openClient(Psocket,Plocaldevicename,Premotedevicename);//~1AebR~
			resetMemberDisconnected();	//reset other of local and server(thread!=null)//~0119I~
        }                                                          //~1AebI~
        BTCDialog.onConnected(Premotedevicename,addr,Pswclient);   //~va02R~
//      BTRDialog.onConnectedAfterThreadCreated(Pswclient,Premotedevicename);//~9A24R~
        AG.aMainView.showConnectStatus();                          //~vac5R~//~vajiI~
    }                                                              //~1AebI~
    //*******************************************************      //~va66I~
    //*set local and server for [0]                                //~va66I~
    //*******************************************************      //~va66I~
    public void setTrainingMode()                                  //~va66R~
    {                                                              //~va66I~
    	if (Dump.Y) Dump.println("BTMulti:setTrainingMode");       //~va66I~
//      int role=ROLE_SERVER;                                      //~va66R~
        int role=ROLE_UNDEFINED;                                   //~va66I~
	    localDeviceName=DEVICENAME_TRAINING;                       //~va66I~
        memberRole=role;                                           //~va66I~
        serverDeviceName=localDeviceName;                          //~va66I~
        BTGroup.setTrainingMode(localDeviceName);                  //~va66I~
    }                                                              //~va66I~
    //************************************************************ //~1AebI~
	public void onConnectionFailed(int Pflag,String Pdevicename)                      //~1AebR~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti:connFailed "+Pflag);     //~1AebI~
//      swConnect=false;                                           //~1AebI~
        dismissWaitingDialog();                                    //~1AebR~
      	if (Pflag==BTService.CONN_FAILED)                          //~1AebI~
      	{                                                          //~1AebI~
    		String secureopt=Utils.getStr(swSecureConnect ? R.string.BTSecure : R.string.BTInSecure);//~1AebR~
			Alert.showMessage(null,Utils.getStr(R.string.Err_No_connection_to_BT,Pdevicename,secureopt));//~1AebR~
      	}                                                          //~1AebI~
      	else                                                       //~1AebI~
			Alert.showMessage(null,Utils.getStr(R.string.No_Connection_to_)+Pdevicename);//~1AebR~
        BTCDialog.onConnectionFailed(Pflag,Pdevicename);           //~1AebI~
    }                                                              //~1AebI~
    //************************************************************ //~1AebI~
	public void onAcceptFailed(String Psecure)                     //~1AebI~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTI:acceptFailed "+Psecure);     //~1AebI~
        dismissWaitingDialog();                                    //~1AebR~
		Alert.showMessage(null,Utils.getStr(R.string.ErrAcceptfailed,Psecure));//~1AebR~
        BTCDialog.onAcceptFailed(Psecure);                                //~1AebI~
    }                                                              //~1AebI~
    //************************************************************ //~1AebI~
	public void onReceive(int Paction,Intent Pintent)              //~1AebR~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti:onReceive:"+Paction+",activeSessionType="+AG.activeSessionType);    //~1AebR~//~9A19R~
        if (AG.activeSessionType!=AST_BT)                          //~9A19R~
        {                                                          //~9A19I~
        	if (Dump.Y) Dump.println("BTMulti:onReceive ignored by not in BT mode");//~9A19R~
            return;                                                //~9A19I~
        }                                                          //~9A19I~
        BluetoothDevice device;                                    //~1AebI~
        int state=0;                                               //~1AebR~
        String name="";                                            //~1AebI~
        String addr="";                                            //~1AebI~
        switch (Paction)                                           //~1AebI~
        {                                                          //~1AebI~
        case BTDiscover.ACTION_FOUND:     //remote device discovered//~1AebR~
            break;                                                 //~1AebI~
        case BTDiscover.ACTION_DISCOVERY_FINISHED:                 //~1AebR~
            BTCDialog.onReceive(Paction,name,addr,state);          //~1AebR~
            break;                                                 //~1AebI~
        case BTDiscover.ACTION_SCAN_MODE_CHANGED:                  //~1AebR~
            int scanmode=Pintent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE,BluetoothAdapter.ERROR);//~1AebI~
            switch(scanmode)                                       //~1AebI~
            {                                                      //~1AebI~
            case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE://~1AebI~
                nowDiscoverable(true);                             //~1AebI~
                break;                                             //~1AebI~
            case BluetoothAdapter.SCAN_MODE_CONNECTABLE:           //~1AebI~
                nowDiscoverable(false);                            //~1AebI~
                break;                                             //~1AebI~
            }                                                      //~1AebI~
            break;                                                 //~1AebI~
        case BTDiscover.ACTION_STATE_CHANGED:                      //~1AebR~
            break;                                                 //~1AebI~
        case BTDiscover.ACTION_CONNECTION_STATE_CHANGED:           //~1AebI~
            device = Pintent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);	//remote device//~1AebI~
            state=Pintent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR);//~1AebI~
            name=device.getName();                                 //~1AebI~
            addr=device.getAddress();                              //~1AebI~
            if (Dump.Y) Dump.println("BTMulti onReceive ACTION_CONNECTION_STATE_CHANGED to "+state+"="+state);//~1AebI~
            BTCDialog.onReceive(Paction,name,addr,state);          //~1AebR~
            break;                                                 //~1AebI~
        case BTDiscover.ACTION_BOND_STATE_CHANGED:                 //~1AebR~
            device = Pintent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);//~1AebR~
	        state=Pintent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE,BluetoothAdapter.ERROR);//~1AebR~
            if (device!=null)                                      //~1AebI~
            {                                                      //~1AebI~
                name=device.getName();                             //~1AebI~
                addr=device.getAddress();                          //~1AebI~
                BTCDialog.onReceive(Paction,name,addr,state);      //~1AebR~
            }                                                      //~1AebI~
            break;                                                 //~1AebI~
        case BTDiscover.ACTION_ACL_CONNECTED:                      //~1AebI~
            device = Pintent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);//~1AebI~
            if (device!=null)                                      //~1AebI~
            {                                                      //~1AebI~
                name=device.getName();                             //~1AebI~
                addr=device.getAddress();                          //~1AebI~
                BTCDialog.onReceive(Paction,name,addr,state);      //~1AebR~
            }                                                      //~1AebI~
            break;                                                 //~1AebI~
        case BTDiscover.ACTION_ACL_DISCONNECTED:                    //~1AebI~
            device = Pintent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);//~1AebI~
            if (device!=null)                                      //~1AebI~
            {                                                      //~1AebI~
                name=device.getName();                             //~1AebI~
//                addr=device.getAddress();                          //~1AebI~//~9731R~
//                if (Dump.Y) Dump.println("BTMulti:onReceive DISCONNECTED name="+name+",add="+addr);//~@002I~//~9731R~
//                if (removeMember(name)) //connected                //~1AebR~//~9731R~
//                {                                                  //~1AebI~//~9731R~
//                    BTCDialog.onReceive(Paction,name,addr,state);  //~1AebR~//~9731R~
//                    notifyToMembers(false/*disconnected*/,name,""/*yourname*/);//~1AebI~//~9731R~
//                    if (BTGroup.getConnectedCtr()==0)              //~@002R~//~9731R~
//                    {                                              //~@002I~//~9731R~
//                        if (Dump.Y) Dump.println("BTMulti:onReceive all session disconnected reset oldrole="+memberRole);//~@002R~//~9731R~
//                        memberRole=ROLE_UNDEFINED;  //enable to change role//~@002R~//~9731R~
//                    }                                              //~@002I~//~9731R~
//                }                                                  //~1AebI~//~9731R~
                onDisconnectedIP(name);                            //~9731I~
            }                                                      //~1AebI~
            break;                                                 //~1AebI~
        }                                                          //~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~1AebM~
//*when connected                                                  //~1AebI~
//***************************************************************************//~1AebI~
    private void openServer(BluetoothSocket Psocket,String Plocalname,String Premotename)//~1AebR~
    {                                                              //~1AebM~
    	if (Dump.Y) Dump.println("BTMulti:openServer");            //~1AebR~
        int idxMember=BTGroup.search(Premotename);                 //~0221I~
    	if (Dump.Y) Dump.println("remotedevice="+Premotename+",local="+Plocalname+",idxMember="+idxMember);//~1AebI~//~9A02R~
        BTIOThread t=new BTIOServer(Psocket,Plocalname,Premotename,idxMember).connect();//~1AebR~
        if (t!=null)                                               //~1AebI~
        {                                                          //~1AebI~
		    updateMember(Premotename,t);                           //~1AebR~
        }                                                          //~1AebI~
    }                                                              //~1AebM~
//***************************************************************************//~1AebI~
    protected void notifyToMembers(boolean Pswconnected,String Premotename,String Pyourname)//~1AebR~//~9729R~
    {                                                              //~1AebI~
    	if (Dump.Y) Dump.println("BTMulti:notifyToMembers swConnected="+Pswconnected+",remote="+Premotename+",yourname="+Pyourname);//~1AebR~
        String msg="";                                             //~1AebI~
        boolean sw1st=true;                                        //~1AebI~
        if (Pswconnected)                                          //~1AebI~
        {                                                          //~@002I~
            int idx=0;                                             //~@002I~
            for (Members.MemberData d:BTGroup.MD)                  //~1AebR~
            {                                                      //~1AebR~
                String n=d.getName();                              //~1AebR~
                String y=d.getYourName();                          //~1AebI~
                if (y!=null && !y.equals(""))                      //~1AebI~
                {                                                  //~1AebI~
                	if (sw1st)                                     //~1AebI~
                    {                                              //~1AebI~
                    	sw1st=false;                               //~1AebI~
//              		msg=n+BTIOThread.MSG_SEP+y;                 //~1AebI~//~@002R~
//              		msg=n+MSG_SEP+y;                           //~@002R~
                		msg=n;      //sender name                  //~@002R~
                    }                                              //~1AebI~
//                  else                                           //~1AebI~//~@002R~
//              		msg+=BTIOThread.MSG_SEP+n+BTIOThread.MSG_SEP+y;//~1AebR~//~@002R~
//              		msg+=MSG_SEP+n+MSG_SEP+y;                  //~@002R~
                		msg+=MSG_SEP+n+MSG_SEP+y+MSG_SEP+idx;      //~@002R~
                }                                                  //~1AebI~
                idx++;                                             //~@002I~
            }                                                      //~1AebI~
        }                                                          //~@002I~
        else                                                       //~1AebI~
        	msg=Premotename;                                       //~1AebI~
    	if (Dump.Y) Dump.println("BTMulti:notifyToMembers msg="+msg);//~1AebI~
        for (Members.MemberData d:BTGroup.MD)                      //~1AebI~
        {                                                          //~1AebR~
        	BTIOThread t=(BTIOThread)d.getThread();                //~1AebR~
	        if (t!=null)                                           //~1AebR~
            {                                                      //~1AebI~
		    	if (Dump.Y) Dump.println("BTMulti:notifyToMembers out to "+d.getName());//~1AebI~
//          	t.out((Pswconnected ? BTIOThread.MSGQ_MEMBER_ADD :BTIOThread.MSGQ_MEMBER_DELETE),msg);//~1AebR~//~@002R~
//          	t.out((Pswconnected ? MSGQ_MEMBER_ADD :MSGQ_MEMBER_DELETE),msg);//~@002I~//~9B30R~
            	t.outForce((Pswconnected ? MSGQ_MEMBER_ADD :MSGQ_MEMBER_DELETE),msg);//~9B30I~
            }                                                      //~1AebI~
    	}                                                          //~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~1AebM~
    private void openClient(BluetoothSocket Psocket,String Plocalname,String Premotename)//~1AebR~
    {                                                              //~1AebM~
    	if (Dump.Y) Dump.println("BTMulti:openClient socket="+Psocket.toString());//~1AebR~//~9723R~
//      int idxMember=BTGroup.search(Plocalname);  //now 0 ,later updated at received server's sequence//~0221R~
        int idxMember=BTGroup.search(Premotename);  //now 0 ,later updated at received server's sequence//~0221I~
    	if (Dump.Y) Dump.println("idxMember="+idxMember+",remotedevice="+Premotename+",local="+Plocalname);//~1AebR~//~0221I~
        BTIOThread t=new BTIOClient(Psocket,Plocalname,Premotename,idxMember).connect();//~1AebR~
        if (t!=null)                                               //~1AebI~
		    updateMember(Premotename,t);                           //~1AebR~
    }                                                              //~1AebM~
//***************************************************************************//~1AebI~
    public Boolean disconnect(String Pdevicename)                  //~1AebR~
    {                                                              //~1AebI~
    	boolean rc=false;                                          //~1AebI~
    	if (Dump.Y) Dump.println("BTMulti:disconnect device="+Pdevicename);//~1AebR~
        BTIOThread t=getMemberThread(Pdevicename);                  //~1AebI~
        if (t!=null)                                               //~1AebI~
        {                                                          //~1AebI~
        	t.close();	//output close msg then close socket       //~1AebI~
            rc=true;                                               //~1AebI~
        }                                                          //~1AebI~
        return rc;                                                 //~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~9210I~
    public Boolean unpair(String Pdevicename,String Paddr)         //~9210I~
    {                                                              //~9210I~
    	if (Dump.Y) Dump.println("BTMulti:unpair device="+Pdevicename+",addr="+Paddr);//~9210I~
        boolean rc=AG.aBTI.unpair(Pdevicename,Paddr);              //~9210I~
        return rc;                                                 //~9210I~
    }                                                              //~9210I~
////***************************************************************************//~1AebI~//~9B05R~
//    private Boolean closeSocket(BluetoothSocket Psocket)                     //~1AebI~//~9B05R~
//    {                                                              //~1AebI~//~9B05R~
//        Boolean rc=false;                                          //~1AebI~//~9B05R~
//    //********************************                             //~1AebI~//~9B05R~
//        if (Psocket!=null)                                               //~1AebI~//~9B05R~
//        {                                                          //~1AebI~//~9B05R~
//            if (!Psocket.isConnected())                                     //~1AebI~//~9B05R~
//            {                                                      //~1AebI~//~9B05R~
//                //TODO close stream                                //~1AebI~//~9B05R~
//            }                                                      //~1AebI~//~9B05R~
//            try                                                    //~1AebR~//~9B05R~
//            {                                                      //~1AebR~//~9B05R~
//                Psocket.close();                                   //~1AebR~//~9B05R~
//                rc=true;                                           //~1AebR~//~9B05R~
//            }                                                      //~1AebR~//~9B05R~
//            catch(IOException e)                                   //~1AebR~//~9B05R~
//            {                                                      //~1AebR~//~9B05R~
//                Dump.println(false/*no dislog msg*/,e,"BTMulti.closeSocket");//~1AebR~//~9B05R~
//            }                                                      //~1AebR~//~9B05R~
//        }                                                          //~1AebI~//~9B05R~
//        if (Dump.Y) Dump.println("BTMulti.closeSocket rc="+rc);    //~1AebI~//~9B05R~
//        return rc;                                                 //~1AebI~//~9B05R~
//    }                                                              //~1AebI~//~9B05R~
//***************************************************************************//~1AebI~
	public static void dismissWaitingDialog()                      //~@@@2R~//~1AebI~
    {                                                              //~@@@2I~//~1AebI~
        if (Dump.Y) Dump.println("BTMulti DismissWaitiingDialog");//~@@@2R~//~1AebI~
		ProgDlg.dismissCurrent();                                         //~@@@2I~//~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~1AebI~
	public Boolean isBTAvailable(Boolean Pshowmsg)                 //~1AebR~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti isBTAvailable");         //~1AebI~
//      BluetoothAdapter bta=BluetoothAdapter.getDefaultAdapter(); //~1AebI~//~vam3R~
        BluetoothAdapter bta=BTControl.getDefaultAdapter();        //~vam3I~
        if (bta==null)                                             //~1AebR~
        {                                                          //~1AebI~
        	if (Pshowmsg)                                          //~1AebI~
	            BTI.BTNotAvailable();                              //~1AebR~
            return false;                                          //~1AebI~
        }                                                          //~1AebI~
        return true;                                               //~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~1AebI~
//*by BTCDialog Accept button                                      //~1AebI~
//***************************************************************************//~1AebI~
	public Boolean onAccept(Boolean Psecure)	                   //~1AebR~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti onAccept");              //~1AebI~
        swAcceptWaitingDiscoverable=false;                         //~@002I~
//      int waitingDialog=0;	                                   //~1AebI~//~@002R~
    	if (!chkDiscoverable(Psecure,true/*server*/))              //~1AebI~
            return false;                                              //~1AebR~
        boolean rc=startListen(Psecure);                           //~1AebI~
        return rc;	//issued accept                                //~1AebR~
    }                                                              //~1AebI~
//***************************************************************************//~1AebI~
    public boolean chkDiscoverable(boolean Psecure,boolean Pserver)//~1Ac5I~//~1AebR~
	{                                                              //~1Ac5I~//~1AebM~
    	boolean rc=true;                                           //~1Ac5I~//~1AebM~
    	if (swAlertAction)                                         //~1Ac5I~//~1AebM~
        	return rc;                                             //~1Ac5I~//~1AebM~
        if (Dump.Y) Dump.println("BTCDialog.chkDiscoverable secure="+Psecure);//~1Ac5I~//~v@@@R~//~1AebM~
        if (Psecure)                                               //~1Ac5I~//~1AebM~
        {                                                          //~1Ac5I~//~1AebM~
        	if (!BTI.BTisDiscoverable())                           //~1Ac5I~//~v@@@R~//~1AebM~
            {                                                      //~1Ac5I~//~1AebM~
//              if (OperationSetting.isForceAcceptEvenNotDiscoverable())//~@002R~//~9C04R~
                if (RuleSettingOperation.isForceAcceptEvenNotDiscoverable())//~9C04I~
                {                                                  //~@002I~
//                      AG.aBTI.setDiscoverable();                 //~@002R~
//                      swAcceptWaitingDiscoverable=true;          //~@002R~
//                      UView.showToastShort(Utils.getStr(R.string.InfoBTDiscoverableRequested));//~@002R~
					rc=true;                                       //~@002I~
                }                                                  //~@002I~
                else                                               //~@002I~
                {                                                  //~@002I~
			    showNotDiscoverableAlert(Psecure,Pserver);                 //~1Ac5I~//~1AebM~
                rc=false;  //DialogNFCBT from alert Action         //~1Ac5I~//~1AebM~
                }                                                  //~@002I~
            }                                                      //~1Ac5I~//~1AebM~
        }                                                          //~1Ac5I~//~1AebM~
        return rc;                                                 //~1Ac5I~//~1AebM~
    }                                                              //~1Ac5I~//~1AebM~
    //******************************************                   //~1AbtI~//~1AebM~
    private boolean startListen(boolean Psecure)                   //~1AbtI~//~1AebI~
    {                                                              //~1AbtI~//~1AebM~
    	boolean rc=BTI.startListen(Psecure);                 //~1AbtI~//~v@@@R~//~1AebR~
        return rc;                                                 //~1AbtI~//~1AebM~
    }                                                              //~1AbtI~//~1AebM~
	//*************************************************************************//~1Ac5I~//~1AebM~
    private void showNotDiscoverableAlert(boolean Psecure,boolean Pserver)         //~1Ac5I~//~1AebM~
    {                                                              //~1Ac5I~//~1AebM~
        Alert.showAlert(R.string.Title_Bluetooth,R.string.WarningBTNotDiscoverable,//~1Ac5I~//~1AebM~
                            Alert.BUTTON_CLOSE,null);//~1Ac5I~     //~1AebR~
    }                                                              //~1Ac5I~//~1AebM~
//***************************************************************************//~1AebI~
//*by MainView Connect button                                      //~1AebI~
//***************************************************************************//~1AebI~
    public void onConnect()                                        //~1AebI~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti onConnect(from MainActivity)");//~1AebI~
		if (!isBTAvailable(true/*toast*/))                         //~1AebR~
        	return;                                                //~1AebI~
//      String tag = BTCDialog.class.getSimpleName();              //~1AebI~//~@002R~
        if (AG.aBTCDialog!=null)                                   //~@002I~
            return;                                                //~@002I~
//      BTCDialog.newInstance(memberRole).show();                  //~1AebR~//~9709R~
        BTCDialog dlg=BTCDialog.newInstance(memberRole);           //~9709I~
        if (dlg!=null)                                             //~9709I~
        	dlg.show();                                            //~9709I~
    }                                                              //~1AebI~
//***************************************************************************//~1AebI~
//*by BTCDialog Connect button                                     //~1AebI~
//***************************************************************************//~1AebI~
	public Boolean onConnect(String Pdevicename,String Paddr,Boolean Psecure)//~1AebR~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti onConnect name="+Pdevicename+",addr="+Paddr);//~1AebR~
        return AG.aBTI.connect(Pdevicename,Paddr,Psecure);         //~1AebR~
    }                                                              //~1AebI~
//***************************************************************************//~1AebI~
//*from BTDiscover Discoverable status changed                     //~1AebI~
//***************************************************************************//~1AebI~
	private void nowDiscoverable(Boolean Pdiscoverable)            //~1AebR~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti nowDiscoverable discaoverable="+Pdiscoverable);//~1AebI~
        BTCDialog.nowDiscoverable(Pdiscoverable);                  //~1AebI~
    }                                                              //~1AebI~
////***************************************************************************//~1AebI~//~9731R~
////*from BTIOThread                                                 //~1AebI~//~9731R~
////***************************************************************************//~1AebI~//~9731R~
//    public void connectionLost(String Premotename)                 //~1AebR~//~9731R~
//    {                                                              //~1AebI~//~9731R~
//        if (Dump.Y) Dump.println("BTMulti connectionLost remote="+Premotename);//~1AebI~//~9731R~
//        updateMember(Premotename,(BTIOThread)null);                //~1AebI~//~9731R~
//    }                                                              //~1AebI~//~9731R~
//***************************************************************************//~1AebI~
	public void closeStream()                                      //~1AebI~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti closeStream");           //~1AebI~
        if (AG.aBTI==null)                                         //~1AebI~
            return;                                                //~1AebI~
        AG.aBTI.closeStream();                                     //~1AebI~
        closeConnection();                                        //~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~1AebI~
	public void destroy()                                          //~1AebI~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti destroy");               //~1AebI~
        if (AG.aBTI==null)                                         //~1AebI~
            return;                                                //~1AebI~
    	AG.aBTI.destroy();                                         //~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~1AebI~
	public void closeConnection()                                  //~1AebI~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti closeConnection");       //~1AebI~
        for (Members.MemberData d:BTGroup.MD)                                 //~1AebI~
        {                                                          //~1AebI~
        	BTIOThread t=(BTIOThread)d.getThread();                     //~1AebI~
	        if (t!=null)                                           //~1AebI~
            	t.close();                                         //~1AebI~
    	}                                                          //~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~1AebI~
//*from MainActivity.receivedCtlMsg                                //~9620I~
//***************************************************************************//~9620I~
	public void msgRead(int Pmsgid,String Pmsg)                    //~1AebI~
    {                                                              //~1AebI~
        String devicename,yourname;
        String[] strs=BTIOThread.parse(Pmsg);                      //~1AebR~
        if (Dump.Y) Dump.println("BTMulti.msgRead id="+Pmsgid+",msg="+Pmsg+",strs="+Arrays.toString(strs));//~0107I~//~varaR~
        switch(Pmsgid)                                             //~1AebI~
        {                                                          //~1AebI~
        case MSGID_NAME:    //=2                                   //~1AebI~//~9A24R~
        	if (!chkAppVerison(strs,MSG_NAMER_POS_APPVERSION))	//unmatch//~varaI~
            {                                                      //~varaI~
        		if (Dump.Y) Dump.println("BTMulti.msgRead MSGQ_NAME appversion unmatch return");//~varaI~
                break;                                             //~varaI~
            }                                                      //~varaI~
        	devicename=strs[0];                             //~1AebI~
        	yourname=strs[1];                               //~1AebI~
//          updateMember(devicename,yourname);                 //~1AebI~//~9619R~
            updateMember(devicename,yourname,strs[2]/*syncDate*/); //~9619I~
            if (!chkReconnecting(devicename,strs,MSG_NAMER_POS_RECONNECT))//~0107I~//~0110M~
                setReconnectErr(devicename);                       //~0110M~
//          BTCDialog.renewal(BTCDialog.RENEWAL_MEMBER);           //~1AebR~//~9A24R~
            BTCDialog.renewal(BTCDialog.RENEWAL_MEMBER,devicename,yourname);//~9A24I~
        	break;                                                 //~1AebI~
        case MSGID_QNAME:   //MSGQ_NAME with synchDate =6 and swReconnecting         //~9619I~//~9A24R~//~0107R~
        	if (!chkAppVerison(strs,MSG_NAMEQ_POS_APPVERSION))	//unmatch//~varaI~
            {                                                      //~varaI~
        		if (Dump.Y) Dump.println("BTMulti.msgRead MSGQ_NAME appversion unmatch return");//~varaI~
                break;                                             //~varaI~
            }                                                      //~varaI~
        	devicename=strs[0];                                    //~9619I~
            updateMember(devicename,strs[1]/*syncDate*/);          //~9619R~
            if (!chkReconnecting(devicename,strs,MSG_NAMEQ_POS_RECONNECT))//~0107R~//~0110M~
                setReconnectErr(devicename);           //~0107I~//~0108R~//~0110M~
        	break;                                                 //~9619I~
        case MSGID_MEMBER_ADD:           //=4                      //~1AebI~//~9A24R~
            updateNotified(strs);                                  //~1AebR~
//          BTCDialog.renewal(BTCDialog.RENEWAL_MEMBER);           //~1AebI~//~9A24R~//~9A25R~
            BTCDialog.renewal(BTCDialog.RENEWAL_MEMBER,strs[0],strs[1]);  //server name for client//~9A25I~
        	break;                                                 //~1AebI~
        case MSGID_MEMBER_DELETE:        //=5                      //~1AebI~//~9A24R~
        	devicename=strs[0];                             //~1AebI~
			removeMember(devicename);                              //~1AebI~
            BTCDialog.renewal(BTCDialog.RENEWAL_MEMBER);           //~1AebI~
        	break;                                                 //~1AebI~
        case MSGID_IOERR:                //=7                      //~9731I~//~9A24R~
        	devicename=strs[0];                                    //~9731I~
	        if (Dump.Y) Dump.println("BTMulti msgRead MSGID_IOERR name="+devicename);//~9731I~
			onDisconnectedIP(devicename);                          //~9731I~
		    endgameIfDisconnectedAtRuleSettingSynch();             //~9812I~
        	break;                                                 //~9731I~
        }                                                          //~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~0107I~
	protected boolean chkReconnecting(String Pdevicename,String[] PstrParm,int Ppos)//~0107R~
    {                                                              //~0107I~
    	if (PstrParm.length<=Ppos)  //missing parm reconnect       //~0107R~
        	return true;                                           //~0107I~
    	boolean swParm=PstrParm[Ppos].equals("1");                 //~0107I~
    	boolean swReconnect=BTCDialog.isReconnectingAny();	//BT or WD//~0107R~
        boolean rc=swParm==swReconnect;                            //~0107R~
	    if (Dump.Y) Dump.println("BTMulti.chkReconnecting swParm="+swParm+",swReconnect="+swReconnect+",rc="+rc+",dev="+Pdevicename+",pos="+Ppos+",parm="+ Arrays.toString(PstrParm));//~0107I~
        if (!rc)                                                   //~0107I~
        {                                                          //~0107I~
//      	UView.showToastLong(Utils.getStr(R.string.Err_ReconnectingEnv,Pdevicename));//~0107R~
        	alertReconnectMode(swReconnect,Pdevicename);           //~0107I~
        }                                                          //~0107I~
        return rc;                                                 //~0107I~
    }                                                              //~0107I~
    //*******************************************************      //~9611I~//~0107I~
    private void alertReconnectMode(boolean PswReconnect,String Pdevicename)             //~9611I~//~0107I~
    {                                                              //~9611I~//~0107I~
        if (Dump.Y) Dump.println("BTMulti.alertReconnectMode PswReconnect="+PswReconnect+",dev="+Pdevicename);//~0107I~
        String msg=Utils.getStr(R.string.Err_ReconnectingEnv,Pdevicename);//~0107I~
        int titleid;                                               //~0107R~
        if (AG.activeSessionType==AST_BT)                          //~0107I~
	        titleid=PswReconnect ? R.string.Title_BluetoothReconnect : R.string.Title_Bluetooth;//~0107I~
        else                                                       //~0107I~
	        titleid=PswReconnect ? R.string.DialogTitle_WDAR : R.string.DialogTitle_DeviceDetail;//~0107I~
        Alert.showMessage(titleid,msg);  //close btn               //~0107I~
    }                                                              //~9611I~//~0107I~
////***************************************************************************//~1AebI~//~9A27R~
////*SendAPP msg                                                     //~1AebR~//~9A27R~
////***************************************************************************//~1AebI~//~9A27R~
//    public void sendMsgApp(int Pmsgid,String Pmsg)                 //~1AebR~//~9A27R~
//    {                                                              //~1AebI~//~9A27R~
//        sendMsg(true,Pmsgid,Pmsg);                       //~1AebI~//~9A27R~
//    }                                                              //~1AebI~//~9A27R~
////***************************************************************************//~1AebI~//~9A27R~
//    public void sendMsgApp(int Pthreadid,int Pmsgid,String Pmsg)   //~1AebI~//~9A27R~
//    {                                                              //~1AebI~//~9A27R~
//        sendMsg(true,Pthreadid,Pmsgid,Pmsg);                       //~1AebR~//~9A27R~
//    }                                                              //~1AebI~//~9A27R~
//***************************************************************************//~1AebI~
//*BTCDialog.namechanged                                           //~1AebI~
//***************************************************************************//~1AebI~
	public void sendMsg(int Pmsgid,String Pmsg)                    //~1AebI~
    {                                                              //~1AebI~
		sendMsg(false,Pmsgid,Pmsg);                      //~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~1AebI~
	private void sendMsg(Boolean Pswapp,int Pmsgid,String Pmsg)     //~1AebI~//~0119R~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti sendMsg role="+memberRole+",swApp="+Pswapp+",id="+Pmsgid+",msg="+Pmsg);//~1AebI~
    	if (memberRole==ROLE_SERVER)                               //~1AebI~
        	sendMsgToAllClient(Pswapp,Pmsgid,Pmsg);                //~1AebI~
        else                                                       //~1AebI~
    	if (memberRole==ROLE_CLIENT)                               //~1AebI~
        	sendMsgToServer(Pswapp,Pmsgid,Pmsg);                   //~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~9621I~
	private void sendStartGame(String PsyncDate)                    //~9621I~//~va66R~
    {                                                              //~9621I~
        if (Dump.Y) Dump.println("BTMulti.sendStartGame send SYNCOK to all client");         //~9621I~//~var8R~
        AG.aProfileIcon.startSyncProfileServer(localDeviceName);          //~var8I~
        sendMsgToAllClient(true/*Pswapp*/,GCM_SETTING_NOTIFY_SYNCOK,PsyncDate);//~9621I~
    }                                                              //~9621I~
////***************************************************************************//~1AebI~//~9A27R~
//    public void sendMsg(Boolean Pswapp,int Pthreadid,int Pmsgid,String Pmsg)//~1AebI~//~9A27R~
//    {                                                              //~1AebI~//~9A27R~
//        if (Dump.Y) Dump.println("BTMulti sendMsg threadid="+Pthreadid+",swApp="+Pswapp+",id="+Pmsgid+",msg="+Pmsg);//~1AebI~//~9A27R~
//        sendMsgToRemote(Pswapp,Pthreadid,Pmsgid,Pmsg);      //~1AebI~//~9A27R~
//    }                                                              //~1AebI~//~9A27R~
//***************************************************************************//~1AebI~
	public void sendMsgToServer(boolean Pswapp,int Pmsgid,String Pmsg)//~1AebR~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti sendMsgToServer swapp="+Pswapp+",msgid="+Pmsgid+",msg="+Pmsg);//~@002I~
        sendMsgToRemote(Pswapp,serverDeviceName,Pmsgid,Pmsg);      //~1AebR~
    }                                                              //~1AebI~
//***************************************************************************//~9406I~
//*Properties and HistoryData                                      //~9825I~
//***************************************************************************//~9825I~
	public int sendMsgToServerProp(int Pmsgid,String Pmsg)        //~9406I~//~9A29R~
    {                                                              //~9406I~
        if (Dump.Y) Dump.println("BTMulti sendMsgToServerProp msgid="+Pmsgid+",msg="+Pmsg);//~9406I~
        int ctrSent=sendMsgToRemoteProp(serverDeviceName,Pmsgid,Pmsg)?1:0;         //~9406I~//~9A29R~
        return ctrSent;                                            //~9A29I~
    }                                                              //~9406I~
//***************************************************************************//~9827I~
//* rc: 1:sent,-1:not sent(no thread), 0:not connected             //~0112I~
//***************************************************************************//~0112I~
//	private void sendMsgToRemote(boolean Pswapp,String Premote,int Pmsgid,String Pmsg)//~1AebR~//~9A27R~//~0112R~
  	private int sendMsgToRemote(boolean Pswapp,String Premote,int Pmsgid,String Pmsg)//~0112I~
    {                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTMulti sendMsgToRemote remote="+Premote+",msgid="+Pmsgid+",msg="+Pmsg);//~1AebI~//~@002R~
        int rc=0;                                              //~0112I~
//      if (!Premote.equals(""))                                   //~0112R~
        if (!Premote.equals("") && !BTGroup.isDummyName(Premote))  //~0112I~
        {//~1AebI~
//          BTIOThread t = (BTIOThread) BTGroup.getMemberThread(Premote);              //~1AebI~//~9A27R~
//          BTIOThread.sendMsg(t,Pswapp,Pmsgid,Pmsg);              //~1AebR~//~9A27R~
            int idxMember=BTGroup.search(Premote);                 //~9A27I~
//          BTIOThread.sendMsg(idxMember,Pswapp,Pmsgid,Pmsg);      //~9A27I~//~0112R~
            rc=BTIOThread.sendMsg(idxMember,Pswapp,Pmsgid,Pmsg) ? 1 : -1;	//1:sent,-1:not sent(no thread)//~0112I~
        }//~1AebI~
        if (Dump.Y) Dump.println("BTMulti sendMsgToRemote rc="+rc);//~0112I~
        return rc;                                                 //~0112I~
    }                                                              //~1AebI~
//***************************************************************************//~9404I~
//*Properties and HistoryData                                      //~9825I~
//***************************************************************************//~9825I~
	private boolean sendMsgToRemoteProp(String Premote,int Pmsgid,String Pprops)//~9404R~//~9A29R~
    {                                                              //~9404I~
        if (Dump.Y) Dump.println("BTMulti sendMsgToRemoteProp remote="+Premote+",msgid="+Pmsgid+",props="+Pprops);//~9404R~
        boolean rc=false;                                          //~9A29I~
        if (!Premote.equals(""))                                   //~9404I~
        {                                                          //~9404I~
//          BTIOThread t = (BTIOThread) BTGroup.getMemberThread(Premote);//~9404I~//~9A27R~
//          BTIOThread.sendMsgProp(t,Pmsgid,Pprops);               //~9404R~//~9A27R~
            int idxMember=BTGroup.search(Premote);                 //~9A27I~
            rc=BTIOThread.sendMsgProp(idxMember,Pmsgid,Pprops);       //~9A27I~//~9A29R~
        }                                                          //~9404I~
        if (Dump.Y) Dump.println("BTMulti sendMsgToRemoteProp rc="+rc);//~9A29I~
        return rc;                                                 //~9A29I~
    }                                                              //~9404I~
//***************************************************************************//~9825I~
//*send prop for history interrupted                               //~9827I~
//***************************************************************************//~9827I~
	private void sendMsgToRemoteHistory(String Premote,int Pmsgid,String Phds)//~9825I~
    {                                                              //~9825I~
        if (Dump.Y) Dump.println("BTMulti sendMsgToRemoteHistory remote="+Premote+",msgid="+Pmsgid+",hds="+Phds);//~9825I~
        if (!Premote.equals(""))                                   //~9825I~
        {                                                          //~9825I~
//          BTIOThread t = (BTIOThread) BTGroup.getMemberThread(Premote);//~9825I~//~9A27R~
//          BTIOThread.sendMsgProp(t,Pmsgid,Phds);   //same as Prop//~9825R~//~9A27R~
            int idxMember=BTGroup.search(Premote);                 //~9A27I~
            BTIOThread.sendMsgProp(idxMember,Pmsgid,Phds);   //same as Prop//~9A27I~
        }                                                          //~9825I~
    }                                                              //~9825I~
////***************************************************************************//~1AebI~//~9A27R~
//    private void sendMsgToRemote(boolean Pswapp,int Pthreadid,int Pmsgid,String Pmsg)//~1AebI~//~9A27R~
//    {                                                              //~1AebI~//~9A27R~
//        if (Dump.Y) Dump.println("BTMulti sendMsgToRemote threadid="+Pthreadid);//~1AebI~//~9A27R~
//        BTIOThread t = (BTIOThread) BTGroup.getThread(tid2idx(Pthreadid));//~1AebR~//~9A27R~
//        BTIOThread.sendMsg(t,Pswapp,Pmsgid,Pmsg);                  //~1AebI~//~9A27R~
//    }                                                              //~1AebI~//~9A27R~
//***************************************************************************//~1AebI~
//  public void sendMsgToAllClient(boolean Pswapp,int Pmsgid,String Pmsg)//~1AebR~//~9406R~//~0112R~
    public boolean sendMsgToAllClient(boolean Pswapp,int Pmsgid,String Pmsg)//~0112I~
    {                                                              //~1AebI~
    	if (Dump.Y) Dump.println("BTMulti:sendMsgToAllClient msgid="+Pmsgid+",msg="+Pmsg);//~1AebI~
        boolean rc=true;                                           //~0112I~
        for (Members.MemberData d:BTGroup.MD)                      //~1AebI~
        {                                                          //~1AebI~
            String name=d.getName();                               //~1AebI~
            if (!name.equals(localDeviceName))                     //~1AebI~//~0112R~
            {                                                      //~1AebI~
//      		sendMsgToRemote(Pswapp,name,Pmsgid,Pmsg);                 //~1AebI~//~0112R~
        		int rc2=sendMsgToRemote(Pswapp,name,Pmsgid,Pmsg);  //~0112I~
                if (rc2==-1)	//no thread                        //~0112I~
                	rc=false;                                      //~0112I~
            }                                                      //~1AebI~
        }                                                          //~1AebI~
    	if (Dump.Y) Dump.println("BTMulti:sendMsgToAllClient rc="+rc);
    	return rc;//~0112I~
    }                                                              //~1AebI~
//***************************************************************************//~9404I~
    public int sendMsgToAllClientProp(int Pmsgid,String Pprops)   //~9404R~//~9A29R~
    {                                                              //~9404I~
    	if (Dump.Y) Dump.println("BTMulti:sendMsgToAllClientProp msgid="+Pmsgid+",props="+Pprops);//~9404R~
        int ctrSent=0;                                             //~9A29I~
        for (Members.MemberData d:BTGroup.MD)                      //~9404I~
        {                                                          //~9404I~
            String name=d.getName();                               //~9404I~
            if ((d.status & Members.MS_REMOTECLIENT)!=0)           //~9404I~
        		if (sendMsgToRemoteProp(name,Pmsgid,Pprops))            //~9404R~//~9A29R~
                	ctrSent++;                                     //~9A29I~
        }                                                          //~9404I~
    	if (Dump.Y) Dump.println("BTMulti:sendMsgToAllClientProp rc="+ctrSent);//~9A29I~
        return ctrSent;                                            //~9A29I~
    }                                                              //~9404I~
//***************************************************************************//~9B25I~
//  public int sendMsgToAllClientPropNotSynched(int Pmsgid,String Pprops)//~9B25R~
    public Point sendMsgToAllClientPropNotSynched(int Pmsgid, String Pprops)//~9B25I~
    {                                                              //~9B25I~
    	if (Dump.Y) Dump.println("BTMulti:sendMsgToAllClientPropNotSynched msgid="+Pmsgid+",props="+Pprops);//~9B25I~
        int ctrSent=0;                                             //~9B25I~
        int ctrClient=0;                                           //~9B25I~
        for (Members.MemberData d:BTGroup.MD)                      //~9B25I~
        {                                                          //~9B25I~
            String name=d.getName();                               //~9B25I~
            if ((d.status & Members.MS_REMOTECLIENT)!=0)           //~9B25I~
            {                                                      //~9B25I~
            	ctrClient++;                                       //~9B25I~
              if (!d.isRuleSynched())                              //~9B25R~
        		if (sendMsgToRemoteProp(name,Pmsgid,Pprops))       //~9B25I~
                	ctrSent++;                                     //~9B25I~
        	}                                                      //~9B25I~
        }                                                          //~9B25I~
    	if (Dump.Y) Dump.println("BTMulti:sendMsgToAllClientPropNotSynched sent="+ctrSent+",ctrClient="+ctrClient);//~9B25R~
//      return ctrSent;                                            //~9B25R~
        return new Point(ctrSent,ctrClient);                       //~9B25I~
    }                                                              //~9B25I~
//***************************************************************************//~1AebI~
	public static int tid2idx(int Pid)                             //~1AebI~
    {                                                              //~1AebI~
    	return Pid-1;                                              //~1AebI~
    }                                                              //~1AebI~
	public static int idx2tid(int Pidx)                            //~1AebI~
    {                                                              //~1AebI~
    	return Pidx+1;                                             //~1AebI~
    }                                                              //~1AebI~
//***************************************************************************//~@002I~
//*server received setupEnd from client                            //~@002I~
//***************************************************************************//~@002I~
//    public static int setupEnd(String PremoteName)               //~@002R~
    public static void setupEnd(String PremoteName)                //~@002I~
    {                                                              //~@002I~
        if (Dump.Y) Dump.println("BTMulti.setupEnd remotename="+PremoteName);//~@002I~
    	AG.aBTMulti.BTGroup.setupEnd(PremoteName);                 //~@002R~
//        int rc=chkSetupEnd();  //allclient setupEnd              //~@002R~
//        if (rc==0)                                               //~@002R~
//        {                                                        //~@002R~
//            if (Status.setupEnd(Status.GSF_SETUPEND_ALLCLIENT)) //server is already setupend//~@002R~
//                AG.aAccounts.setupEndAll();                      //~@002R~
//            else                                                 //~@002R~
//                UView.showToast(Utils.getStr(R.string.Info_WaitingAllMemberSetup,1/*server*/));//~@002R~
//        }                                                        //~@002R~
//        else                                                     //~@002R~
//            UView.showToast(Utils.getStr(R.string.Info_WaitingAllMemberSetup,rc));//~@002R~
//        if (Dump.Y) Dump.println("BTMulti.setupEnd remotename="+PremoteName+",rc="+rc);//~@002R~
//        return rc;                                               //~@002R~
    }                                                              //~@002I~
//***************************************************************************//~@002I~
	public static void setupEnd(int Pidx)                          //~@002R~
    {                                                              //~@002I~
        if (Dump.Y) Dump.println("BTMulti.setupEnd idx="+Pidx);    //~@002R~
        if (Pidx<0)                                                //~@002I~
			setupEnd(AG.aBTMulti.localDeviceName);                 //~@002R~
        else                                                       //~@002I~
        {                                                          //~@002I~
        	if (Dump.Y) Dump.println("BTMulti.setupEnd idx="+Pidx+",name="+AG.aBTMulti.BTGroup.MD[Pidx].getName());//~@002I~
	    	AG.aBTMulti.BTGroup.MD[Pidx].setupEnd();                 //~@002R~
        }                                                          //~@002I~
    }                                                              //~@002I~
//***************************************************************************//~@002I~
	public static int chkSetupEnd()                                //~@002R~
    {                                                              //~@002I~
        if (Dump.Y) Dump.println("BTMulti.chkSetupEnd");           //~@002I~
        int rc=0;                                                  //~@002R~
        for (Members.MemberData d:AG.aBTMulti.BTGroup.MD)          //~@002I~
        {                                                          //~@002I~
	        if (Dump.Y) Dump.println("BTMulti.chkSetupEnd name="+d.getName()+",thread:null="+(d.getThread()==null));//~@002R~
        	if ((d.getThread()!=null)                              //~@002R~
            ||  (d.isLocal() && d.isServer()))                      //~@002I~
            {                                                      //~@002I~
            	if (!d.isSetupEnd())                               //~@002I~
                {                                                  //~@002I~
			        if (Dump.Y) Dump.println("BTMulti.chkSetupEnd not setupend");//~@002I~
                	rc++;                                          //~@002R~
                    break;                                         //~@002I~
                }                                                  //~@002I~
            }                                                      //~@002I~
        }                                                          //~@002I~
        if (Dump.Y) Dump.println("BTMulti.chkSetupEnd rc="+rc);    //~@002I~
        return rc;                                                 //~@002I~
    }                                                              //~@002I~
//***************************************************************************//~9405I~
	public String getYourName(int Pidx)                            //~9405I~
    {                                                              //~9405I~
//      String yn=AG.aBTMulti.BTGroup.getYourName(Pidx);           //~9405I~//~9406R~
        String yn=BTGroup.getYourName(Pidx);                       //~9406I~
        if (Dump.Y) Dump.println("BTMulti.getYourName idx="+Pidx+",YourName="+yn);//~9405I~
        return yn;                                                 //~9405I~
    }                                                              //~9405I~
////***************************************************************************//~@002I~//~9B20R~
////*by menuInGame/F1                                              //~9B20R~
////*server/client                                                 //~9B20R~
////*idx:of client when server received,else =idxServer            //~9B20R~
////***************************************************************************//~@002I~//~9B20R~
////  public static void endGame(int Pidx)                           //~@002R~//~9B19R~//~9B20R~
//    public static void endGame(int Pidx,boolean PswReceived)       //~9B19I~//~9B20R~
//    {                                                              //~@002I~//~9B20R~
////        for (Members.MemberData d:AG.aBTMulti.BTGroup.MD)        //~@002R~//~9B20R~
//          Members.MemberData d=AG.aBTMulti.BTGroup.MD[Pidx];       //~@002I~//~9B20R~
//        if (Dump.Y) Dump.println("BTMulti.endGame idx="+Pidx+",swReceived="+PswReceived+",MD="+d.toString());//~9B20R~
////        {                                                        //~@002R~//~9B20R~
//          if (Dump.Y) Dump.println("BTMulti.endGame name="+d.getName()+",thread:null="+(d.getThread()==null));//~@002R~//~9B20R~
////        if (d.getThread()!=null)                                 //~@002R~//~9B20R~
//              d.resetSetupEnd();                                   //~@002R~//~9B20R~
////        }                                                        //~@002R~//~9B20R~
//        if (!PswReceived)                                        //~9B20R~
//        {                                                        //~9B20R~
//            if (Accounts.isServer())                             //~9B20R~
//            {                                                    //~9B20R~
//                if (Dump.Y) Dump.println("BTMulti.endGame server not received");//~9B20R~
//                AG.aBTMulti.sendMsgToAllClient(true/*Pswapp*/,GCM_ENDGAME,"");//~9B20R~
//            }                                                    //~9B20R~
//            else           //client                              //~9B20R~
//            {                                                    //~9B20R~
//                if (Dump.Y) Dump.println("BTMulti.endGame client not received");//~@002I~//~9B20R~
////              if ((d.status & (MS_LOCAL|MS_CLIENT))==(MS_LOCAL|MS_CLIENT))//~@002R~//~9B20R~
//                AG.aBTMulti.sendMsgToServer(true/*Pswapp*/,GCM_ENDGAME,"");//~@002R~//~9B20R~
//            }                                                    //~9B20R~
//        }//!Received                                             //~9B20R~
//        else                                                     //~9B20R~
//        {                                                        //~9B20R~
//            if (Accounts.isServer())                             //~9B20R~
//            {                                                    //~9B20R~
//                if (Dump.Y) Dump.println("BTMulti.endGame server received");//~9B20R~
//                AG.aBTMulti.sendMsgToAllClient(true/*Pswapp*/,GCM_ENDGAME,"");  //notify to other client//~9B20R~
//            }                                                    //~9B20R~
//        }                                                        //~9B20R~
//        Status.setEndgameSomeone();                                //~@002I~//~9B20R~
//    }                                                              //~@002I~//~9B20R~
//***************************************************************************//~9B20I~
//*by menuInGame/F1 on Server and receved msg GCM_ENDGAME on client//~9B20I~
//*server/client                                                   //~9B20I~
//*idx:to reset MS_SETUPEND;of local idx and of server when received at client//~9B22R~
//***************************************************************************//~9B20I~
    public static void endGame(int Pidx,boolean PswReceived)       //~9B21I~
    {                                                              //~9B21I~
	    endGame(Pidx,PswReceived,false);                           //~9B21I~
    }                                                              //~9B21I~
    public static void endGame(int Pidx,boolean PswReceived,boolean PswReturn)       //~9B20I~//~9B21R~
    {                                                              //~9B20I~
        Members.MemberData d=AG.aBTMulti.BTGroup.MD[Pidx];         //~9B20I~
        if (Dump.Y) Dump.println("BTMulti.endGame idx="+Pidx+",swReceived="+PswReceived+",swReturn="+PswReturn+",MD="+d.toString());//~9B20I~//~9B21R~
		if (Accounts.isServer())                                   //~9B20I~
        {                                                          //~9B20I~
	        int msgid=PswReturn ? GCM_ENDGAME_RETURN : GCM_ENDGAME;      //~9B21I~//~9B22I~
        	if (Dump.Y) Dump.println("BTMulti.endGame server");    //~9B20I~
//          AG.aBTMulti.sendMsgToAllClient(true/*Pswapp*/,GCM_ENDGAME,"");//~9B20I~//~9B21R~
            AG.aBTMulti.sendMsgToAllClient(true/*Pswapp*/,msgid,"");//~9B21I~
            if (PswReturn)                                         //~9B21I~
            {                                                      //~9B22I~
		        AG.aBTMulti.BTGroup.resetSetupEnd();               //~9B22I~
		        Status.setEndgameSomeone();                            //~9B20I~//~9B21R~
            }                                                      //~9B22I~
        }//!Received                                               //~9B20I~
        else //client                                              //~9B20I~
        {                                                          //~9B20I~
        	if (Dump.Y) Dump.println("BTMulti.endGame client");    //~9B20R~
	        if (PswReturn)                                         //~9B22I~
		        d.resetSetupEnd();                                         //~9B20I~//~9B22I~
            if (PswReceived)    //GC.endgame->Acc.endgame-->BTM.endgame(wsReceive:false),avoid loop//~9B20I~
            {                                                      //~9B20I~
            	if (PswReturn)                                     //~9B21I~
                {                                                  //~9B21I~
		        Status.setEndgameSomeone();                        //~9B20I~
	        	endGameUI();	//back to top panel                //~9B20R~
      			if (AG.aOrientationMenuDlg!=null)                  //~vajfI~
      				AG.aOrientationMenuDlg.returnEndgame();        //~vajfR~
                }                                                  //~9B21I~
            }                                                      //~9B20I~
        }                                                          //~9B20I~
    }                                                              //~9B20I~
//***************************************************************************//~9B20I~
    private static void endGameUI()                                //~9B20I~
    {                                                              //~9B20I~
        if (Dump.Y) Dump.println("BTMulti.endGameUI");             //~9B20I~
        AG.activity.runOnUiThread(                                 //~9305I~//~9B20I~
            new Runnable()                                         //~9305I~//~9B20I~
            {                                                      //~9305I~//~9B20I~
                @Override                                          //~9305I~//~9B20I~
                public void run()                                       //~9305I~//~9B20I~
                {                                                  //~9305I~//~9B20I~
                    try                                            //~9305I~//~9B20I~
                    {                                              //~9305I~//~9B20I~
    				    if (Dump.Y) Dump.println("BTMulti.endgameUI runonUiThread.run");//~9305I~//~9314R~//~9B20I~
                      if (AG.aGC!=null)                            //~vajfI~
                    	AG.aGC.endGame(true/*wsReturn*/);                          //~9B20I~//~9B21R~
                    }                                              //~9305I~//~9B20I~
                    catch(Exception e)                             //~9305I~//~9B20I~
                    {                                              //~9305I~//~9B20I~
                        Dump.println(e,"BTMulti.endgameUI:run");  //~9305I~//~9314R~//~9B20I~
                    }                                              //~9305I~//~9B20I~
                }                                                  //~9305I~//~9B20I~
            }                                                      //~9305I~//~9B20I~
                                  );                               //~9305I~//~9B20I~
	}                                                              //~9B20I~
////***************************************************************************//~@002I~//~9B19R~
////* active ctr;                                                    //~@002I~//~9B19R~
////***************************************************************************//~@002I~//~9B19R~
//    public static boolean chkMember()                              //~@002I~//~9B19R~
//    {                                                              //~@002I~//~9B19R~
//        if (Dump.Y) Dump.println("BTMulti.chkMember");             //~@002I~//~9B19R~
//        BTMulti btm=AG.aBTMulti;                                   //~@002I~//~9B19R~
//        if (btm==null)                                             //~@002I~//~9B19R~
//            return false;                                                 //~@002I~//~9B19R~
//        Members members=btm.BTGroup;                               //~@002I~//~9B19R~
//        if (members==null)                                         //~@002I~//~9B19R~
//            return false;                                                 //~@002I~//~9B19R~
//        int ctrMember=members.MD.length;                          //~@002I~//~9B19R~
//        int ctrActive=0;                                           //~@002I~//~9B19R~
//        for (int ii=0;ii<ctrMember;ii++)                           //~@002I~//~9B19R~
//        {                                                          //~@002I~//~9B19R~
//            members.MD[ii].resetSetupEnd();                       //~@002I~//~9B19R~
//            if ((members.MD[ii].status & (Members.MS_SERVER|Members.MS_LOCAL|Members.MS_REMOTECLIENT))!=0)//~@002I~//~9B19R~
//                ctrActive++;                                       //~@002I~//~9B19R~
//        }                                                          //~@002I~//~9B19R~
//        boolean rc=ctrActive==maxClient;                                   //~@002I~//~9B19R~
//        if (Dump.Y) Dump.println("BTMulti.chkMember active="+ctrActive+",max="+maxClient+",rc="+rc);//~@002I~//~9B19R~
//        return rc;                                                 //~@002I~//~9B19R~
//    }                                                              //~@002I~//~9B19R~
//***************************************************************************//~9622I~
//* active ctr;                                                    //~9622I~
//***************************************************************************//~9622I~
	public static int getMemberConnected()                         //~9622I~
    {                                                              //~9622I~
        if (Dump.Y) Dump.println("BTMulti.getMemberConnected");    //~9622I~//~va66R~
    	BTMulti btm=AG.aBTMulti;                                   //~9622I~
        if (btm==null)                                             //~9622I~
        	return 0;                                              //~9622I~
    	Members members=btm.BTGroup;                               //~9622I~
        if (members==null)                                         //~9622I~
        	return 0;                                              //~9622I~
        int ctrMember=members.MD.length;                           //~9622I~
        int ctrActive=0;                                           //~9622I~
        for (int ii=0;ii<ctrMember;ii++)                           //~9622I~
        {                                                          //~9622I~
            if (members.MD[ii].getThread()!=null)                      //~9622R~
            	ctrActive++;                                       //~9622I~
        }                                                          //~9622I~
        if (Dump.Y) Dump.println("BTMulti.getMemberConnected ctrActive="+ctrActive+",max="+maxClient);//~9622I~
        return ctrActive;                                          //~9622I~
    }                                                              //~9622I~
//***************************************************************************//~0119I~
//* at startGame,clear disconnected Entry                         //~0119I~//~0120R~
//***************************************************************************//~0119I~
	public static int resetMemberDisconnected()                    //~0119I~
    {                                                              //~0119I~
        if (Dump.Y) Dump.println("BTMulti.resetMemberDisconnected");//~0119I~
    	BTMulti btm=AG.aBTMulti;                                   //~0119I~
        if (btm==null)                                             //~0119I~
        	return 0;                                              //~0119I~
    	Members members=btm.BTGroup;                               //~0119I~
        if (members==null)                                         //~0119I~
        	return 0;                                              //~0119I~
        if (Dump.Y) Dump.println("BTMulti.resetMemberDisconnected before="+members.toString());//~0119I~
//      if (AG.swTrainingMode)                                     //~va66R~
//      	btm.setTrainingMode();                                 //~va66R~
        int ctrMember=members.MD.length;                           //~0119I~
        int ctrInActive=0;                                         //~0119I~
        for (int ii=0;ii<ctrMember;ii++)                           //~0119I~
        {                                                          //~0119I~
	        if (!AG.swTrainingMode && (members.MD[ii].status & MS_PLAYALONE)!=0)//~va66I~
            {                                                      //~va66I~
			    members.remove(ii);                                //~va66I~
            	ctrInActive++;                                     //~va66I~
            }                                                      //~va66I~
            else                                                   //~va66I~
            if (members.MD[ii].getThread()==null && (members.MD[ii].status & MS_LOCAL)==0)//~0119R~
            {                                                      //~0119I~
			    members.remove(ii);                                 //~0119I~
            	ctrInActive++;                                     //~0119I~
            }                                                      //~0119I~
        }                                                          //~0119I~
        if (Dump.Y) Dump.println("BTMulti.resetMemberDisconnected ctrInActive="+ctrInActive+",max="+maxClient);//~0119I~
        if (Dump.Y) Dump.println("BTMulti.resetMemberDisconnected afetr="+members.toString());//~0119I~
        return ctrInActive;                                        //~0119I~
    }                                                              //~0119I~
//***************************************************************************//~0120I~
//* at server connected to first client previously having connection to server//~0120I~
//***************************************************************************//~0120I~
	public static void resetMemberDisconnectedClear()              //~0120I~
    {                                                              //~0120I~
        if (Dump.Y) Dump.println("BTMulti.resetMemberDisconnectedClear");//~0120I~
    	BTMulti btm=AG.aBTMulti;                                   //~0120I~
    	Members members=btm.BTGroup;                               //~0120I~
        if (Dump.Y) Dump.println("BTMulti.resetMemberDisconnected before="+members.toString());//~0120I~
        members.clear();                                           //~0120R~
        if (Dump.Y) Dump.println("BTMulti.resetMemberDisconnected afetr="+members.toString());//~0120I~
    }                                                              //~0120I~
//***************************************************************************//~0116I~
//* active ctr;                                                    //~0116I~
//***************************************************************************//~0116I~
	public boolean chkMemberReconnect()                            //~0116I~
    {                                                              //~0116I~
        if (Dump.Y) Dump.println("BTMulti.chkMemberReconnect memberRole="+memberRole+",idxLocal="+BTGroup.idxLocal);//~0116R~
        boolean rc=true;                                           //~0116I~
        Members.MemberData[] md=BTGroup.MD;                       //~0116I~
        int ctrActive=0;                                           //~0218I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~0116R~
        {                                                          //~0116I~
            if (Dump.Y) Dump.println("BTMulti.chkMemberReconnect MD["+ii+"]="+md[ii].toString());//~0116R~
            if (ii==BTGroup.idxLocal)                              //~0116R~
            	continue;                                          //~0116I~
			if (memberRole==ROLE_CLIENT)	//=2                   //~0116I~
            {                                                      //~0116I~
            	if ((md[ii].status & MS_SERVER)==0)                //~0116R~
	            	continue;                                      //~0116I~
            }                                                      //~0116I~
            if (BTGroup.isConnected(ii))                          //~0116R~//~0218R~
            	ctrActive++;                                       //~0218I~
            else                                                   //~0218I~
            	if (!BTGroup.isDummyName(md[ii].getName()))	//name of "not connected"//~0116R~
                {                                                  //~0116I~
                	rc=false;                                      //~0116I~
                    break;                                         //~0116I~
                }
        }//~0116I~
        if (ctrActive==0)                                          //~0218I~
        	rc=false;                                              //~0218I~
        if (Dump.Y) Dump.println("BTMulti.chkMemberReconnect ctrActive="+ctrActive+",rc="+rc);//~0116I~//~0218R~
        return rc;                                                 //~0116I~
    }                                                              //~0116I~
//***************************************************************************//~9620I~
//* on server                                                      //~9620I~
//***************************************************************************//~9620I~
	public String chkReconnectingEnv()                             //~0110R~
    {                                                              //~0110I~
    	String devnm=BTGroup.chkReconnectingEnv();                 //~0110R~
        if (Dump.Y) Dump.println("BTMulti.chkReconnectingEnv devnm="+devnm);//~0110R~
        return devnm;                                              //~0110R~
    }                                                              //~0110I~
	public boolean chkRuleSync(boolean PswMsg)                     //~9620R~
    {                                                              //~9620I~
    	boolean rc=BTGroup.isRuleSynched(PswMsg);                  //~9620R~
        if (Dump.Y) Dump.println("BTMulti.chkRuleSync rc="+rc);    //~9620I~
        return rc;
    }                                                              //~9620I~
//***************************************************************************//~0107I~
	public boolean chkRuleSync(String Pname,boolean PswMsg)        //~0107I~
    {                                                              //~0107I~
        if (Dump.Y) Dump.println("BTMulti.chkRuleSync name="+Pname+",swMsg="+PswMsg);//~0107I~
    	boolean rc=BTGroup.isRuleSynched(Pname,PswMsg);            //~0107I~
        if (Dump.Y) Dump.println("BTMulti.chkRuleSync rc="+rc);    //~0107I~
        return rc;                                                 //~0107I~
    }                                                              //~0107I~
//***************************************************************************//~9621I~
//* on server,from GC at startGame button                          //~9621I~
//***************************************************************************//~9621I~
//  public void startGameSyncDate()                                //~9621I~//~0112R~
    public boolean startGameSyncDate()                             //~0112I~
    {                                                              //~9621I~
        if (Dump.Y) Dump.println("BTMulti.startGameSyncDate");     //~9621I~
    	String syncDate=getLocalSyncDate();                        //~9621R~
    	BTGroup.resetSyncDate(true);                               //~9621R~
//      swWaitingSyncDate=true;                                    //~9621I~//~9812M~//~0112R~
//    	sendMsgToAllClient(true/*Pswapp*/,GCM_SETTING_SYNC_QUERY,syncDate);//~9621I~//~0112R~
  		boolean rc=sendMsgToAllClient(true/*Pswapp*/,GCM_SETTING_SYNC_QUERY,syncDate);//~0112I~
        swWaitingSyncDate=rc;                                      //~0112I~
        if (!rc)                                                   //~0112I~
        	UView.showToast(R.string.Err_StartGameConnectionInvalid);//~0112I~
        return rc;                                                 //~0112I~
    }                                                              //~9621I~
//***************************************************************************//~9621I~
	public String getLocalSyncDate()                               //~9621I~
    {                                                              //~9621I~
    	String syncDate=BTGroup.getLocalSyncDate();                //~9621I~
        if (Dump.Y) Dump.println("BTMulti.getLocalSyncDate syncDate="+syncDate);//~9621I~
        return syncDate;                                           //~9621I~
    }                                                              //~9621I~
//***************************************************************************//~9621I~
	public void receivedSyncQueryResp(String Psender,String PsyncDate)//~9621R~
    {                                                              //~9621I~
        if (Dump.Y) Dump.println("BTMulti.receivedSyncQueryResp sender="+Psender+",syncDate="+PsyncDate);//~9621R~//~0107R~
   		updateMember(Psender,PsyncDate);                           //~9621I~
//  	if (!chkRuleSync(true/*PswMsg*/))                          //~9621I~//~0107R~
    	if (!chkRuleSync(Psender,true/*PswMsg*/))                  //~0107I~
        	return;                                                //~9621I~
//      AG.aMainView.drawMsg(R.string.Info_ResyncRuleChkOK);                             //~9621I~//~9823R~
    }                                                              //~9621I~
//***************************************************************************//~9621I~
//*from SettingDlg, at property saved                              //~9621I~
//***************************************************************************//~9621I~
	public void saveProperties(boolean PswReceived,String PsyncDate)//~9621R~
    {                                                              //~9621I~
        if (Dump.Y) Dump.println("BTMulti.saveProperties syncDate="+PsyncDate+",swReceived="+PswReceived);//~9621R~
   		if (updateLocal(PsyncDate))	//changed                      //~9621R~
        	if (!PswReceived)                                      //~9621R~
				sendMsg(true/*PswApp*/,GCM_SETTING_CHANGED,PsyncDate);//~9621R~
            else	//client                                       //~9623I~
				updateServer(PsyncDate);                           //~9623R~
    }                                                              //~9621I~
//***************************************************************************//~9621I~
//*from SettingDlg and by GCM_SETTING_CHANGED                      //~9621I~
//***************************************************************************//~9621I~
	public void receivedSettingChanged(int PthreadIdx,String Psender,String PsyncDate)//~9621I~
    {                                                              //~9621I~
        if (Dump.Y) Dump.println("BTMulti.receivedSettingChanged syncDate="+PsyncDate+",sender="+Psender+",idx="+PthreadIdx);//~9621I~
   		if (updateRemote(PthreadIdx,PsyncDate))	//changed          //~9621I~
        {                                                          //~9621I~
        	String name=BTGroup.getYourNameName(PthreadIdx);       //~9621I~
	        AG.aMainView.drawMsg(R.string.Info_RuleChangeReceived,name);//~9621I~
        }                                                          //~9621I~
    }                                                              //~9621I~
//***************************************************************************//~9622I~
	public String getYourNameName(String Pdevname)                   //~9622I~
    {                                                              //~9622I~
        String name=BTGroup.getYourNameName(Pdevname);             //~9622I~
        if (Dump.Y) Dump.println("BTMulti.getYourNameName deb="+Pdevname+",out="+name);//~9622I~
        return name;                                               //~9622I~
    }                                                              //~9622I~
//***************************************************************************//~9731I~
//*for BT,disconnect of ip session(not unpair case)                //~9B07R~
//*on MainThread at Received ACTION_ACL_DISCONNECTED               //~9B07I~
//*or from BTIOThread/IPIOThread endThread()                                    //~9731I~//~9B07R~
//***************************************************************************//~9731I~
	private void onDisconnectedIP(String Pdevname)                 //~9731R~
    {                                                              //~9731I~
        if (Dump.Y) Dump.println("BTMulti:onDisconnectedIP name="+Pdevname+",memberRole="+memberRole);//~9731I~//~9906R~
//  	BTI.onDisconnectedIP(Pdevname,memberRole==ROLE_SERVER); wait setStat=NONE until receive ACL_DISCONNECTED//~0123R~
        String name=Pdevname;                                      //~9731I~
//        if (removeMember(name)) //connected                      //~9731I~
//        {                                                        //~9731I~
//            BTCDialog.onReceive(Paction,name,addr,state);        //~9731I~
//            notifyToMembers(false/*disconnected*/,name,""/*yourname*/);//~9731I~
//            if (BTGroup.getConnectedCtr()==0)                    //~9731I~
//            {                                                    //~9731I~
//                if (Dump.Y) Dump.println("BTMulti:onReceive all session disconnected reset oldrole="+memberRole);//~9731I~
//                memberRole=ROLE_UNDEFINED;  //enable to change role//~9731I~
//            }                                                    //~9731I~
//        }                                                        //~9731I~
//      Thread t=BTGroup.getMemberThread(name);                    //~9731I~//~9A18R~
//      if (t==null)                                               //~9A18I~
        int idx=BTGroup.search(name);                              //~9A18I~
        if (BTGroup.getThread(idx)==null)                          //~9A18I~
        {                                                          //~9731I~
        	if (Dump.Y) Dump.println("BTMulti:onDisconnected thread=null");//~9731I~
        	return;                                                //~9731I~
        }                                                          //~9731I~
//      BTGroup.update(name,(Thread)null);                         //~9731I~//~9906R~//~9B02R~
    	updateMember(name,(BTIOThread)null);                            //~9B02I~
//      if (Status.isGamingNow())                                  //~9A18I~//~va02R~
        if (Status.isGamingNowAndInterRound())                     //~va02I~
        {                                                          //~9A18I~
//      	if (!BTCDialog.isReconnecting()) //BTRDialog is not opened//~9A23I~//~9B07R~
        	if (!BTCDialog.isReconnecting()  //BTRDialog is not opened//~9B07I~
        	&&  !WDA.isReconnecting()) //WDAR is not opened        //~9B07I~
            {                                                      //~9A23I~
        		onDisconnectedIPInGaming(idx,name);                         //~9A18R~//~9A19R~//~9A23R~
            	return;                                                //~9A18I~//~9A23R~
            }                                                      //~9A23I~
	        BTGroup.setIOErr(idx,true/*swOn*/);  //under WDAR opening set status ioerr//~0117I~
        }                                                          //~9A18I~
    	if (memberRole==ROLE_CLIENT) //=2                          //~9906I~
        {                                                          //~9906I~
        	BTGroup.resetConnectionAtClient();                     //~9906I~
        }                                                          //~9906I~
      if (AG.activeSessionType==AST_BT)                            //~9B07I~
        BTCDialog.onReceiveDisconnected(name);                     //~9731I~
      else                                                         //~9B07I~
      if (AG.activeSessionType==AST_WD)                            //~9B07I~
        WDA.onReceiveDisconnected(name);                           //~9B07I~
        notifyToMembers(false/*disconnected*/,name,""/*yourname*/);//~9731I~
        if (BTGroup.getConnectedCtr()==0)                          //~9731I~
        {                                                          //~9731I~
            if (Dump.Y) Dump.println("BTMulti:onDisconnectedIP all session disconnected reset oldrole="+memberRole);//~9731I~
            oldMemberRole=memberRole;	//clear Member if changed client to server//~0120I~
            memberRole=ROLE_UNDEFINED;  //enable to change role    //~9731I~
      		if (AG.activeSessionType==AST_BT)                      //~0116R~
        		AG.RemoteStatus=AG.RS_BT;     //disconnected all for unpair chk//~0116R~
            else                                                   //~0116R~
			if (AG.activeSessionType==AST_WD)                      //~0116I~
				AG.RemoteStatus=AG.RS_IP;     //disconnected       //~0116I~
        	AG.aMainView.showConnectStatus();                          //~vac5R~//~vajiI~
        }                                                          //~9731I~
	}                                                              //~9731I~
//***************************************************************************//~9A18I~
//*on MainThread, from BTIOThread/IPIOThread endThread()                        //~9A18I~//~9B07R~
//***************************************************************************//~9A18I~
	private void onDisconnectedIPInGaming(int Pidx/*idxMember*/,String Pname/*devname*/)                //~9A18R~//~9A19R~//~9A25R~//~9A27R~
    {                                                              //~9A18I~
        if (Dump.Y) Dump.println("BTMulti:onDisconnectedIPInGaming memberRole="+memberRole+",idx="+Pidx+",MD="+BTGroup.MD[Pidx].toString());//~9A18I~//~9A19R~
        notifyToMembers(false/*disconnected*/,Pname,BTGroup.getYourName(Pidx));//~9A18I~
//        if (memberRole==ROLE_CLIENT) //=2                        //~9A18R~
//        {                                                        //~9A18R~
//            BTGroup.resetConnectionAtClient();                   //~9A18R~
//        }                                                        //~9A18R~
//        if (BTGroup.getConnectedCtr()==0)                        //~9A18R~
//        {                                                        //~9A18R~
//            if (Dump.Y) Dump.println("BTMulti:onDisconnectedIP all session disconnected reset oldrole="+memberRole);//~9A18R~
//            memberRole=ROLE_UNDEFINED;  //enable to change role  //~9A18R~
//        }                                                        //~9A18R~
        BTGroup.setIOErr(Pidx,true/*swOn*/);                       //~9A27I~
        SuspendIOErrReqDlg.newInstance(Pidx);                      //~9A18R~
        if (memberRole==ROLE_SERVER)                               //~9A25I~
        {                                                          //~9A25I~
            String yn=BTGroup.getYourNameForReconnect(Pname);      //~9A25R~
//          UserAction.showInfoAll(0,Pidx/*skip*/,Utils.getStr(R.string.InfoDisconnected,yn));//~9A25I~//~9A27R~//~0304R~
            UserAction.showInfoAll(0,Pidx/*skip*/,R.string.InfoDisconnected,yn);//~0304I~
        }                                                          //~9A25I~
	}                                                              //~9A18I~
//***************************************************************************//~9A23I~
//*aftrt GC.init3()                                                //~9A23I~
//***************************************************************************//~9A23I~
	public void saveForReconnect()                                 //~9A23I~
    {                                                              //~9A23I~
        if (Dump.Y) Dump.println("BTMulti:saveForReconnect");      //~9A23I~
        BTGroup.saveForReconnect();	//Members                      //~9A23I~
    }                                                              //~9A23I~
//***************************************************************************//~9B01I~
	public boolean isConnected(int PidxMember)                     //~9B01I~
    {                                                              //~9B01I~
        boolean rc=BTGroup.getThread(PidxMember)!=null;            //~9B01I~
        if (Dump.Y) Dump.println("BTMulti:isConnected idx="+PidxMember+",rc="+rc);//~9B01I~
        return rc;                                                 //~9B01I~
    }                                                              //~9B01I~
//***************************************************************************//~varaI~
    private boolean/*true if OK*/ chkAppVerison(String[] Pparm,int PposAppVersion)//~varaI~
    {                                                              //~varaI~
        if (Dump.Y) Dump.println("BTMulti:chkAppVersion parm="+Utils.toString(Pparm)+",pos="+PposAppVersion);//~varaI~
        boolean rc=true;                                           //~varaI~
        if (Pparm.length<PposAppVersion+1)                         //~varaI~
        	rc=false;                                              //~varaI~
        else                                                       //~varaI~
        {                                                          //~varaI~
        	String senderVersion=Pparm[PposAppVersion];               //~varaI~
	        if (Dump.Y) Dump.println("BTMulti:chkAppVersion sender="+senderVersion+",AG.appVersionMinConnect="+AG.appVersionMinConnect);//~varaI~
            if (senderVersion.compareTo(AG.appVersionMinConnect)<0)//~varaI~
            	rc=false;                                          //~varaI~
        }                                                          //~varaI~
        if (Dump.Y) Dump.println("BTMulti:chkAppVersion rc="+rc);  //~varaI~
        if (!rc)                                                   //~varaI~
		    Alert.showMessage(null,Utils.getStr(R.string.Err_AppVersionConnect,Pparm[0]/*devname*/,AG.appVersionMinConnect));//+varaR~
        return rc;                                                 //~varaI~
    }                                                              //~varaI~
}
