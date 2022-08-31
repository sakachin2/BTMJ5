//*CID://+van0R~: update#= 662;                                    //~van0R~
//**********************************************************************//~v101I~
//2022/06/19 van0 Xlint:unchecked warning                          //~van0I~
//2020/04/16 va04:rule sync faile by msgseqno overrun              //~va04I~
//2020/04/13 va02:At Server,BackButton dose not work when client app canceled by androiud-Menu button//~va02I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.BT.BTIOThread;
import com.btmtest.BT.Members;
import com.btmtest.R;
import com.btmtest.dialog.BTCDialog;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.UserAction;                                //~v@@@I~
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import com.btmtest.wifi.WDA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Status.*;
import static com.btmtest.game.UADelayed.*;

//~v@@@I~
public class UARestart                                                //~v@@@R~//~9A28R~
{                                                                  //~0914I~
	private static final int RESTART_SERVER_STARTOFMSG=0;          //~9A28I~
	private static final int RESTART_SERVER_ENDOFMSG=1;            //~9A28I~
	private static final int RESTART_CLIENT_ENDOFMSG=2;            //~9A28I~
	private static final int RESTART_SERVER_RESTARTEDALL=3;        //~9A29I~
	private static final int RESTART_SERVER_STARTOFMSG_EMPTY=4;    //~0219I~
	private static final int RESTART_SERVER_STARTTIMER=5;          //~0223I~
                                                                   //~0223I~
	private static final int RESTART_DELAY=1000;	//1 sec        //~0223I~
                                                                   //~0223I~
    private UserAction UA;                                         //~v@@@I~
    private boolean isServer;                                      //~v@@@I~
	private Accounts ACC;                                          //~v@@@R~
	private Members MEMB;                                          //~9A28I~
	private boolean[] swsWaitingResp=new boolean[PLAYERS];         //~9A28R~
//  private LinkedList<String>[] pendingMsg=new LinkedList[PLAYERS];            //member seq//~van0R~
//  private LinkedList<String>[] pendingMsg=(LinkedList<String>[])new LinkedList<?>[PLAYERS];            //member seq//+van0R~
    class LinkedListString  extends LinkedList<String>{}           //+van0I~
    private LinkedListString[] pendingMsg=new LinkedListString[PLAYERS];            //member seq//+van0I~
//  private boolean swRestarted,swIOExceptionOccured;              //~9A29R~
//*************************                                        //~v@@@I~
	public UARestart(UserAction PuserAction)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~9A28R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UARestart Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~9A28R~
        AG.aUARestart=this;                                        //~9A28I~
        UA=PuserAction;                                            //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	public void init()                                             //~v@@@I~
    {                                                              //~v@@@I~
        ACC=AG.aAccounts;                                          //~9A28I~
        MEMB=AG.aBTMulti.BTGroup;                                  //~9A28I~
        isServer=Accounts.isServer();                              //~v@@@R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9A28I~
//  	    pendingMsg[ii]=new LinkedList<String>();               //+van0R~
    	    pendingMsg[ii]=new LinkedListString();                 //+van0I~
        init2();                                                   //~0220I~
        if (Dump.Y) Dump.println("UARestart init isServer="+isServer);//~v@@@I~//~9A28R~
    }                                                              //~v@@@I~
    //***************************************************************//~9A28I~
    public static void setIOException()              //~9A28I~     //~9A29R~
    {                                                              //~9A28I~
    	if (Dump.Y) Dump.println("UARestart.setIOException");//~9A28I~//~9A29R~
//      if (!Status.isGamingNow())                                  //~9A18I~//~9A28I~//~va02R~
        if (!Status.isGamingNowAndInterRound())                    //~va02I~
        	return;                                                //~9A28I~
//      AG.aUARestart.swIOExceptionOccured=true;	//oncedetected //~9A29R~
        Status.setIOExceptionInGaming(true);                           //~9A28I~
    }                                                              //~9A28I~
    //*******************************************************      //~9A27I~//~9A29I~
    //*from BTRDialog by restartGame button                        //~9A29I~
    //*******************************************************      //~9A29I~
    public void stopAutoTakeDiscardReset()                  //~9A27R~//~9A29I~
    {                                                              //~9A27I~//~9A29I~
        if (Dump.Y) Dump.println("UARestart.stopAutoTakeDiscardReset");//~9A27I~//~9A29I~
        GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,0/*eswn:no meaning*/,0/*swOn*/,STOPAUTO_IOERR);//~9A27I~//~9A29I~
    }                                                              //~9A27I~//~9A29I~
    //***************************************************************//~9A28I~
    //*rc:true:send                                                //~0218I~
    //***************************************************************//~0218I~
//  public void savePendingMsg(int PidxMember,int Pmsgid,String Ptext,String Pmsg)//~9A28R~//~0218R~
    public boolean savePendingMsg(int PidxMember,int Pmsgid,String Ptext,String Pmsg)//~0218I~
    {                                                              //~9A28I~
        boolean rc=false;   //do not send                          //~0218I~
        if (Dump.Y) Dump.println("UARestart.savePendingMsg msgid="+Pmsgid+",msg="+Pmsg);//~9A28I~
//      if (MEMB.isIOErr(PidxMember))                              //~9A28R~
//      {                                                          //~9A28R~
//      	if (chkMsg(Pmsgid,Ptext,Pmsg))                               //~9A28I~//~0218R~
        	int save=chkMsg(Pmsgid,Ptext,Pmsg);                    //~0218I~
        	if (save==1)                                           //~0218I~
            {                                                      //~9A28I~
//  	    	MEMB.savePendingMsg(PidxMember,Pmsg);              //~9A28R~
        		pendingMsg[PidxMember].add(Pmsg);                  //~9A28I~
        		if (Dump.Y) Dump.println("UARestart.savePendingMsg idx="+PidxMember+",msg="+Pmsg+",size="+pendingMsg[PidxMember].size());//~9A28I~
            }                                                      //~9A28I~
            rc=save==2;	//send                                     //~0218I~
//      }
        return rc;//~9A28R~
    }                                                              //~9A28I~
    //*************************************************************//~9A28I~
    private String getPendingMsg(int Pidx)                         //~9A28I~
    {                                                              //~9A28I~
//      String msg=MD[Pidx].pendingMsg;                            //~9A28I~
//      MD[Pidx].pendingMsg=null;                                  //~9A28I~
        String msg=null;                                           //~9A28I~
        if (!pendingMsg[Pidx].isEmpty())                           //~9A28I~
            msg=pendingMsg[Pidx].removeFirst();                    //~van0R~
        if (Dump.Y) Dump.println("UARestart.getPendingMsg idx="+Pidx+",msg="+msg);//~9A28I~
        return msg;                                                //~9A28I~
    }                                                              //~9A28I~
    //*************************************************************//~0219I~
    private boolean isEmptyPendingMsg(int Pidx)                    //~0219I~
    {                                                              //~0219I~
        boolean rc=pendingMsg[Pidx].isEmpty();                      //~0219I~
        if (Dump.Y) Dump.println("UARestart.isEmptyPendingMsg idx="+Pidx+",rc="+rc);//~0219I~
        return rc;                                                 //~0219I~
    }                                                              //~0219I~
    //*************************************************************//~9A28I~
    private void clearPendingMsg()                                 //~9A28I~
    {                                                              //~9A28I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9A28I~
        {                                                          //~9A28I~
            if (Dump.Y) Dump.println("Members.clearPendingMsg idx="+ii+",size="+pendingMsg[ii].size());//~9A28I~
            pendingMsg[ii].clear();                                //~9A28I~
        }                                                          //~9A28I~
    }                                                              //~9A28I~
    //***************************************************************//~9A28I~
//  private boolean chkMsg(int Pmsgid,String Pdata,String Pmsg)       //~9A28I~//~0218R~
    private int chkMsg(int Pmsgid,String Pdata,String Pmsg)        //~0218I~
    {                                                              //~9A28I~
        if (Dump.Y) Dump.println("UARestart.chkMsg msgid="+Pmsgid+",text="+Pdata+",msg="+Pmsg);//~9A28I~
//      boolean rc=false;                                          //~9A28I~//~0218R~
        int rc=0;                                                  //~0218I~
        switch(Pmsgid)                                             //~9A28I~
        {                                                          //~9A28I~
        case GCM_USER_ACTION:                                      //~9A28I~
        	int actionid=getActionID(Pdata);                       //~9A28R~
//          rc=chkUserAction(actionid);                            //~9A28I~//~0218R~
            if (chkUserAction(actionid))                           //~0218I~
            	rc=1;	//save                                     //~0218I~
        	break;                                                 //~9A28I~
        case GCM_ERRMSG:                                           //~9A28I~
        case GCM_ERRMSG_ANDTOAST:                                  //~9B27I~
        case GCM_ERRMSG_ALL:                                       //~9A28I~
//      	rc=true;                                               //~9A28I~//~0218R~
        	rc=2;	//pass to other than ioerr                     //~0218I~
        	break;                                                 //~9A28I~
        }                                                          //~9A28I~
        if (Dump.Y) Dump.println("UARestart.chkMsg rc="+rc+",msgid="+Pmsgid+",text="+Pdata+",msg="+Pmsg);//~9A28I~
        return rc;
    }                                                              //~9A28I~
    //***************************************************************//~9A28I~
    private int getActionID(String Pdata)                          //~9A28I~
    {                                                              //~9A28I~
    	int[] intp=ACAction.parseAllData(Pdata);                   //~9A28I~
    	int actionid=intp[0];                                      //~9A28I~
        if (Dump.Y) Dump.println("UARestart.getActionID rc="+actionid+",data="+Pdata);//~9A28I~
        return actionid;                                           //~9A28I~
    }                                                              //~9A28I~
    //***************************************************************//~9A28I~
    //* called from also isYourTurn                                //~0111I~
    //***************************************************************//~0111I~
    public static boolean chkUserAction(int Paction)                        //~9A28I~//~0111R~
    {                                                              //~9A28I~
        boolean rc=false;                                          //~9A28I~
        switch(Paction)                                          //~v@@@R~//~9A28I~
        {                                                          //~v@@@I~//~9A28I~
	 	case GCM_TAKE:                                             //~v@@@I~//~9A28I~
	 	case GCM_DISCARD:                                          //~v@@@M~//~9A28I~
	 	case GCM_PON:                                              //~v@@@I~//~9A28I~
//      case GCM_PON_C:       //need not save confirm request,retry after session recovered                                   //~9B16R~//~9B17R~
	 	case GCM_CHII:                                             //~v@@@I~//~9A28I~
	 	case GCM_KAN:                                              //~v@@@I~//~9A28I~
	 	case GCM_REACH:                                            //~v@@@I~//~9A28I~
	 	case GCM_REACH_OPEN:                                       //~v@@7I~//~9A28I~
	 	case GCM_RON:                                              //~v@@@I~//~9A28I~
	 	case GCM_OPEN:                                             //~v@@@I~//~9A28I~
            rc=true;                                               //~9A28I~
            break;                                                 //~9701I~//~9A28I~
//   	case GCM_NEXT_PLAYER:	//client only                      //~v@@@R~//~9A28I~
//   	case GCM_NEXT_PLAYER_PONKAN:	//client only              //~v@@7I~//~9A28I~
//   	case GCM_ENDGAME_DRAWN:                                    //~v@@7I~//~9A28I~
//   	case GCM_ENDGAME_SCORE:                                    //~v@@7I~//~9A28I~
//   	case GCM_ENDGAME_ACCOUNTS:                                 //~9322I~//~9A28I~
//   	case GCM_SUSPENDDLG:                                       //~9822R~//~9A28I~
//   	case GCM_SUSPENDDLG_IOERR:                                 //~9A21I~//~9A28I~
//   	case GCM_WAITON:                                           //~v@@7I~//~9A28I~
//   	case GCM_WAITOFF:                                          //~v@@7I~//~9A28I~
//      case GCM_WAIT_RELEASE_ACTION:                          //~v@11I~//~9627I~//~9A28I~
//      case GCM_TIMEOUT_STOPAUTO:                                 //~9701I~//~9A28I~
        }                                                          //~v@@@I~//~9A28I~
        if (Dump.Y) Dump.println("UARestart.chkUserAction action="+Paction+",rc="+rc);//~9A28I~
        return rc;
    }                                                              //~9A28I~
    //***********************************************************************//~9A30I~
    public void resetIOExceptionInGaming()                         //~9A30I~
    {                                                              //~9A30I~
    	if (Dump.Y) Dump.println("UARestart.resetIOExceptionInPending");//~9A30I~
        Status.setIOExceptionInGaming(false);	//writable at BTIOThread//~9A30I~
    }                                                              //~9A30I~
    //***********************************************************************//~9A28I~
    //*on Server                                                   //~9A28I~
    //***********************************************************************//~9A28I~
    public void sendPendingMsgToClient(boolean[] PswsIOErr/*current Eswn seq*/)//~9A28I~
    {                                                              //~9A28I~
    //**************************                                   //~9A28I~
    	if (Dump.Y) Dump.println("UARestart.sendPendingMsgToClient waiting="+ Arrays.toString(PswsIOErr));//~9A28I~
//      swRestarted=true;                                          //~9A29R~
        Status.setStatusRestart(RESTART_RESTARTED); //server       //~9A29I~
//      Status.setIOExceptionInGaming(false);	//writable at BTIOThread//~9A28I~//~9A30R~
	    resetIOExceptionInGaming();                                //~9A30I~
//      System.arraycopy(PswsIOErr,0,swsWaitingResp,0,PLAYERS);    //~9A28R~
        Arrays.fill(swsWaitingResp,false);                         //~9A28I~
        int eswn=ACC.getCurrentServerEswn();                       //~0221I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9A28R~
        {                                                          //~9A28I~
//  		if (PswsIOErr[eswn])                                   //~9A28R~
//  		if (MEMB.getThread(ii)!=null)                          //~9A28I~//~0219R~
    		BTIOThread thread=(BTIOThread)MEMB.getThread(ii);                  //~0219I~
    		if (thread!=null)                                      //~0219I~
            {                                                      //~9A28R~
//          	int idxMember=ACC.currentEswnToMember(eswn);//~9A28R~//~0221R~
            	int idxMember=ii;                                  //~9A28I~
        		swsWaitingResp[ii]=true;                           //~9A28I~
              if (isEmptyPendingMsg(idxMember))                    //~0219I~
            	sendRestartGame(RESTART_SERVER_STARTOFMSG_EMPTY,idxMember,eswn);//~0219I~//~0221R~
              else                                                 //~0219I~
              {                                                    //~0219I~
            	sendRestartGame(RESTART_SERVER_STARTOFMSG,idxMember,eswn);//~9A28R~//~0221R~
                flush(thread);                                     //~0219I~
                for (;;)                                           //~9A28I~
                {                                                  //~9A28I~
                    String msg=getPendingMsg(idxMember);      //~9A28I~
                    if (Dump.Y) Dump.println("UARestart.sendPendingMsgToClient idxMember="+idxMember+",msg="+msg);//~9A28R~
                    if (msg==null)                                 //~9A28I~
                        break;                                     //~9A28I~
                    BTIOThread.sendRestartMsg(idxMember,msg);      //~9A28I~
	                flush(thread);                                 //~0219I~
                }                                                  //~9A28I~
//          	sendRestartGame(RESTART_SERVER_ENDOFMSG,idxMember,eswn);//~9A28R~
            	sendRestartGame(RESTART_SERVER_ENDOFMSG,idxMember,eswn);//~9A28I~//~0221R~
	            flush(thread);   //experienced  out of sequence    //~0219I~
              }                                                    //~0219I~
            }                                                      //~9A28I~
        }                                                          //~9A28I~
//  	clearPendingMsg();                                         //~9A28R~//~0220R~
    }                                                              //~9A28I~
    //***********************************************************************//~9A29I~
    //*on Server                                                   //~9A29I~
    //***********************************************************************//~9A29I~
    private void sendRestartedAll()                                 //~9A29I~//~0304R~
    {                                                              //~9A29I~
    //**************************                                   //~9A29I~
    	if (Dump.Y) Dump.println("UARestart.sendRestartedAll");     //~9A29I~
        Status.setStatusRestart(RESTART_RESTARTED_ALL); //server   //~9A29I~
        int eswn=ACC.getCurrentServerEswn();                       //~0221I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9A29I~
        {                                                          //~9A29I~
    		if (MEMB.getThread(ii)!=null)                          //~9A29I~
            	sendRestartGame(RESTART_SERVER_RESTARTEDALL,ii,eswn); //~9A29I~//~0221R~
        }                                                          //~9A29I~
        UserAction.showInfoAll(0,R.string.Info_GameRestarted);     //~9B04I~//~0115I~
    }                                                              //~9A29I~
    //***********************************************************************//~9A28I~
    //*on Client,from UATake.autoDiscardTimeout at received WAIT_OFF//~9A28I~
    //***********************************************************************//~9A28I~
    private void sendRestartGame(int Pphase,int PidxMember,int Peswn)//~9A28R~
    {                                                              //~9A28I~
        String msg=Peswn+MSG_SEPAPP2+Pphase;                       //~9A28R~
        if (Dump.Y) Dump.println("UARestart.sendRestartGame phase="+Pphase+",idxMember="+PidxMember+",eswn="+Peswn+",msg="+msg);//~0219I~
//      BTIOThread.sendMsg(PidxMember,true/*swApp*/,GCM_RESTARTGAME,msg);//~9A28I~//~0220R~
        BTIOThread.sendRestartGame(PidxMember,GCM_RESTARTGAME,msg);//~0220I~
    }                                                              //~9A28I~
    //***********************************************************************//~0219I~
    private void flush(BTIOThread Pthread)                         //~0219I~
    {                                                              //~0219I~
        if (Dump.Y) Dump.println("UARestart.flush");               //~0219I~
        Pthread.flush();                                           //~0219I~
    }                                                              //~0219I~
    //***********************************************************************//~0220I~
    private void flush(int Pidx)                                   //~0220I~
    {                                                              //~0220I~
        if (Dump.Y) Dump.println("UARestart.flush idx="+Pidx);     //~0220I~
        BTIOThread.flush(Pidx);                                    //~0220I~
    }                                                              //~0220I~
    //***********************************************************************//~9A28I~
    //*on Client                                                   //~9A28I~
    //***********************************************************************//~9A28I~
    public void sendPendingMsgToServer()                           //~9A28I~
    {                                                              //~9A28I~
    //**************************                                   //~9A28I~
        int idxServer=MEMB.idxServer;                              //~9A28I~
        String msg=getPendingMsg(idxServer);                  //~9A28I~
        if (Dump.Y) Dump.println("UARestart.sendPendingMsgToServer idxServer="+idxServer+",msg="+msg);//~9A28I~
        if (msg!=null)                                             //~0219I~
        {                                                          //~0219I~
        BTIOThread.sendRestartMsg(idxServer,msg);                  //~9A28I~
    		flush(idxServer);                                      //~0219I~
        }                                                          //~0219I~
    }                                                              //~9A28I~
    //***********************************************************************//~9A28I~
    public void setTimeout()                                       //~9A28I~
    {                                                              //~9A28I~
        AG.aUADelayed.setStopTemporally(false);	//stop timer sent by client pending msg//~0223I~
        int player=AG.aPlayers.getCurrentPlayer();                 //~9A28I~
//      int actionID=AG.aPlayers.getCurrentAction();               //~9A28I~//~9B04R~
        int actionID=UA.currentActionID;                           //~9B04I~
        if (Dump.Y) Dump.println("UARestart.setTimeout currentPlayer="+player+",lastAction="+actionID+",isRobotPlayer="+ACC.isRobotPlayer(player));//~9A28I~//~9A29R~//~0221R~
        switch(actionID)                                           //~9A28I~
        {                                                          //~9A28I~
        case GCM_DISCARD:                                          //~9A28I~
            TileData td=AG.aPlayers.tileLastDiscarded;                      //~9A28I~
            UA.UAD.postNextPlayerPonKan(player,td); //swith to next player after delay a moment//~9A28I~
            break;                                                 //~9A28I~
        case GCM_TAKE:                                             //~9A28I~
        case GCM_PON:                                              //~9A28I~
//      case GCM_PON_C:	//this is not set to currentAction                                          //~9B16R~//~9B17R~
        case GCM_CHII:                                             //~9A28I~
            UA.UAT.setAutoDiscardTimeout(true/*PswServer*/,player,actionID);//~9A28I~
            break;                                                 //~9A28I~
        case GCM_KAN:                                              //~9A28I~
//          UA.UAK.setTimeout(true,player); //no autotake at first of restart//~0403R~
            break;                                                 //~9A28I~
        default: //RON/REACH/REACH_OPEN                            //~9A28I~
            if (Dump.Y) Dump.println("UARestart.actionWaitOffAtRestartGame no Action");//~9A28I~
        }                                                          //~9A28I~
    }                                                              //~9A28I~
    //*******************************************************************//~9A27I~//~9A28I~
    //*by GCM_RESTARTGAME                                                   //~9A27I~//~9A28R~
    //*******************************************************************//~9A27I~//~9A28I~
    public void restartGame(int Psender/*idxMember*/,String Pdata) //~9A27I~//~9A28I~
    {                                                              //~9A27I~//~9A28I~
    	int[] intp=ACAction.parseAppData(Pdata);                   //~9A27I~//~9A28I~
//      int eswnIOErr=intp[0];                                     //~9A27I~//~9A28R~
        int eswn;                                                  //~0221I~
        int phase=intp[1];                                         //~9A28R~
		if (Dump.Y) Dump.println("UARestart.restartGame sender:idxMember="+Psender+",msg="+Pdata+",parse="+ Arrays.toString(intp));//~9A27I~//~9A28R~
		if (Dump.Y) Dump.println("UARestart.restartGame swsWaitingResp="+ Arrays.toString(swsWaitingResp));//~9A28R~
        switch(phase)                                              //~9A28I~
        {                                                          //~9A28I~
        case RESTART_SERVER_STARTOFMSG:	//from server,start of restart                     //~9A28I~//~9A29R~
//      	swRestarted=true;                                      //~9A29R~
	        Status.setStatusRestart(RESTART_RESTARTED); //client   //~9A29I~
//          Status.setIOExceptionInGaming(false);	//writable at BTIOThread//~9A28I~//~9A30R~
		    resetIOExceptionInGaming();                            //~9A30I~
//      	Status.setRestarted(Psender,true);                  //~9A28I~//~9A29R~
        	break;                                                 //~9A28I~
        case RESTART_SERVER_ENDOFMSG: //from server,end of restart msg                   //~9A28I~//~9A29R~
//      	if (eswnIOErr==ACC.getCurrentEswn())              //~9A27I~//~9A28R~
//          	sendPendingMsgToServer();            //~9A27I~//~9A28I~//~0220R~
            	sendPendingMsgToServerSeqNo();                     //~0220I~
            int idxMember=MEMB.idxServer;                          //~9A28I~
            eswn=ACC.getCurrentEswn();                             //~0221R~
    		sendRestartGame(RESTART_CLIENT_ENDOFMSG,idxMember,eswn);//~9A28R~//~0221R~
//  		sendRestartGame(RESTART_CLIENT_ENDOFMSG,idxMember,0);  //~9A28I~//~0221R~
        	break;                                                 //~9A28I~
		case RESTART_SERVER_STARTOFMSG_EMPTY:	//from server responsed from all client//~0219I~
	        Status.setStatusRestart(RESTART_RESTARTED); //client   //~0219I~
		    resetIOExceptionInGaming();                            //~0219I~
            //*                                                    //~0219I~
//          sendPendingMsgToServer();                              //~0219I~//~0220R~
            sendPendingMsgToServerSeqNo();                         //~0220I~
            int idx=MEMB.idxServer;                                //~0219R~
            eswn=ACC.getCurrentEswn();                             //~0221I~
    		sendRestartGame(RESTART_CLIENT_ENDOFMSG,idx,eswn);     //~0219R~//~0221R~
        	break;                                                 //~0219I~
        case RESTART_CLIENT_ENDOFMSG: //from client,end of restart msg                   //~9A28I~//~9A29R~
//      	swsWaitingResp[eswnIOErr]=false;                       //~9A28R~
        	swsWaitingResp[Psender]=false;                          //~9A28I~
            if (isReceivedAll())                                   //~9A28I~
            {                                                      //~9A29I~
            	sendRestartedAll();                                //~9A29I~
//          	setTimeout();                                      //~9A28I~//~0223R~
            	postDelayedMsg(RESTART_SERVER_STARTTIMER);         //~0223I~
            }                                                      //~9A29I~
		case RESTART_SERVER_RESTARTEDALL:	//from server responsed from all client//~9A29I~
	        Status.setStatusRestart(RESTART_RESTARTED_ALL); //on client//~9A29I~
            dismissDialog();                                       //~0108I~
        	break;                                                 //~9A28I~
		case RESTART_SERVER_STARTTIMER:                            //~0223I~
            setTimeout();                                          //~0223I~
        	break;                                                 //~0223I~
        }                                                          //~9A27I~//~9A28I~
		if (Dump.Y) Dump.println("UARestart.restartGame exit statusRestart="+Status.getStatusRestart()+",swsWaitingResp="+ Arrays.toString(swsWaitingResp));//~9A28R~//~9A29R~
    }                                                              //~9A27I~//~9A28I~
    //*******************************************************************//~0223I~
    private void postDelayedMsg(int Pphase)                        //~0223I~
    {                                                              //~0223I~
		if (Dump.Y) Dump.println("UARestart.postDelayedMsg phase="+Pphase);//~0223I~
        int eswn=ACC.getCurrentEswn();                             //~0223I~
        String data=eswn+MSG_SEPAPP+Pphase;                        //~0223I~
		AG.aACAction.receivedAppMsgEmulatedDelayed(RESTART_DELAY,GCM_RESTARTGAME,data,null,null);//~0223R~
    }                                                              //~0223I~
    //*******************************************************************//~0108I~
    private void dismissDialog()                                   //~0108I~
    {                                                              //~0108I~
		if (Dump.Y) Dump.println("UARestart.dismissDialog");       //~0108I~
        if (BTCDialog.isReconnecting())                            //~0108I~
        	AG.aBTRDialog.dismiss();                               //~0108I~
        if (WDA.isReconnecting())                                  //~0108I~
        	AG.aWDAR.dismiss();                                    //~0108I~
    }                                                              //~0108I~
    //*******************************************************************//~9A28I~
    public boolean isReceivedAll()                                    //~9A28I~
    {                                                              //~9A28I~
    	boolean rc=true;                                           //~9A28I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9A28I~
        	if (swsWaitingResp[ii])	//not yet                      //~9A28R~
            {	                                                   //~9A28I~
            	rc=false;                                          //~9A28I~
                break;                                             //~9A28I~
            }                                                      //~9A28I~
		if (Dump.Y) Dump.println("UARestart.isReceivedAll rc="+rc+",swsWaitingResp="+ Arrays.toString(swsWaitingResp));//~9A28R~
        return rc;                                                 //~9A28I~
    }                                                              //~9A28I~
    //*******************************************************************//~9A29I~
    public static boolean isIOExceptionInGaming()                  //~9A29I~
    {                                                              //~9A29I~
    	boolean rc=Status.isIOExceptionInGaming();                 //~9A29I~
		if (Dump.Y) Dump.println("UARestart.isIOExceptionInGaming rc="+rc);//~9A29I~
        return rc;                                                 //~9A29I~
    }                                                              //~9A29I~
//    //*******************************************************************//~0115I~
//    public static boolean isIOExceptionInGamingSendBlocked()     //~0115I~
//    {                                                            //~0115I~
//        boolean rc=Status.isIOExceptionInGamingSendBlocked();    //~0115I~
//        if (Dump.Y) Dump.println("UARestart.isIOExceptionInGamingSendBlocked rc="+rc);//~0115I~
//        return rc;                                               //~0115I~
//    }                                                            //~0115I~
    //*******************************************************************//~9A29I~
    //*to bypass UADelayed timeout msg rescheduling                //~9A29I~
    //*******************************************************************//~9A29I~
    public static boolean isIOExceptionBeforeRestart()             //~9A29I~
    {                                                              //~9A29I~
//  	boolean rc=AG.aUARestart.swIOExceptionOccured && !AG.aUARestart.swRestarted;//~9A29R~
    	boolean rc=Status.getStatusRestart()==RESTART_ONCE_IOERR;    //~9A29I~
		if (Dump.Y) Dump.println("UARestart.isIOExceptionBeforeRestart rc="+rc+",statusRestart="+Status.getStatusRestart()+",isIOExceptionInGaming="+Status.isIOExceptionInGaming());//~9A29R~
        return rc;                                                 //~9A29I~
    }                                                              //~9A29I~
    //*******************************************************************//~9A29I~
    //*to bypass Button action until restarting process end        //~9A29I~
    //*******************************************************************//~9A29I~
    public static boolean isIOExceptionRestarting()                //~9A29I~
    {                                                              //~9A29I~
    	int stat=Status.getStatusRestart();                        //~9A29I~
    	boolean rc=stat!=0 && stat!=RESTART_RESTARTED_ALL;          //~9A29I~
		if (Dump.Y) Dump.println("UARestart.isIOExceptionRestarting rc="+rc+",statusRestart="+stat);//~9A29I~
        if (rc)                                                    //~9A29R~
        	UView.showToast(R.string.Err_InGameRestarting);            //~9A29I~
        return rc;                                                 //~9A29I~
    }                                                              //~9A29I~
//******************************************************************************//~0220I~
//******************************************************************************//~0222I~
//******************************************************************************//~0222I~
//  private LinkedList<MsgData>[] msgDataLists=new LinkedList[PLAYERS];            //member seq//~van0R~
//  private LinkedList<MsgData>[] msgDataLists=(LinkedList<MsgData>[])new LinkedList<?>[PLAYERS];            //member seq//+van0R~
    class LinkedListMsgData extends LinkedList<MsgData>{}          //+van0I~
    private LinkedListMsgData[] msgDataLists=new LinkedListMsgData[PLAYERS];            //member seq//+van0I~
    private int[] receivedSeqNo=new int[PLAYERS];                  //~0220I~
//*************************************************************************//~0220I~
	public void init2()                                            //~0220I~
    {                                                              //~0220I~
        if (Dump.Y) Dump.println("UARestart init2");               //~0220I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~0220I~
//		    msgDataLists[ii]=new LinkedList<MsgData>();            //+van0R~
  		    msgDataLists[ii]=new LinkedListMsgData();              //+van0I~
    }                                                              //~0220I~
//******************************************************************************//~0220I~
    public static boolean saveMsg(int Pidx,long PseqNo,String Pmsg) //~0220R~//~0222R~
    {                                                              //~0220I~
    	int seqAll=(int)(PseqNo>>32);                              //~0222I~
    	int seqNo=(int)(PseqNo&0xffffffff);                        //~0222I~
		if (Dump.Y) Dump.println("UARestart.saveMsg idx="+Pidx+",seqAll="+seqAll+",seq="+seqNo+",msg="+Pmsg);//~0220I~//~0222R~
        if (AG.aUARestart==null)	//before init UserAction       //~0220I~
        	return false;                                          //~0220I~
    	boolean rc=AG.aUARestart.saveMsgSeqNo(Pidx,seqAll,seqNo,Pmsg);   //~0220R~//~0222R~
        return rc;                                                 //~0220I~
    }                                                              //~0220I~
//******************************************************************************//~0220I~
    private boolean saveMsgSeqNo(int Pidx,int PseqAll,int PseqNo,String Pmsg)  //~0220R~//~0222R~
    {                                                              //~0220I~
		if (Dump.Y) Dump.println("UARestart.saveMsgSeqNo idx="+Pidx+",seqAll="+PseqAll+",secNo="+PseqNo+",msg="+Pmsg);//~0220R~//~0222R~
        if (AG.aUARestart==null)	//before init UserAction       //~0220I~
        	return false;                                          //~0220I~
    	boolean rc=false;                                          //~0220I~
        int sz=0;                                                  //~0220I~
    	if (chkStatus())                                           //~0220R~
        {                                                          //~0220I~
        	MsgData msgData=new MsgData(Pidx,PseqAll,PseqNo,Pmsg);              //~0220I~//~0222R~
            LinkedList<MsgData> list=msgDataLists[Pidx];           //~0220I~
            synchronized(list)                                     //~0220I~
            {                                                      //~0220I~
        		list.addFirst(msgData);                            //~0220I~
                sz=list.size();                                    //~0220I~
            }                                                      //~0220I~
            rc=true;                                               //~0220I~
        }                                                          //~0220I~
		if (Dump.Y) Dump.println("UARestart.saveMsgSeqNo rc="+rc+",sz="+sz+",msg="+Pmsg);//~0220R~//~0221R~
        return rc;                                                 //~0220I~
    }                                                              //~0220I~
//******************************************************************************//~0220I~
    private int scan(LinkedList<MsgData> Plist,int PseqNo)         //~0220I~
    {                                                              //~0220I~
    	int rc=-1;                                                 //~0220I~
        MsgData msgData;                                           //~0220I~
        for (int ii=0;ii<Plist.size();ii++)                        //~0220I~
        {                                                          //~0220I~
	        msgData=Plist.get(ii);                                 //~0220I~
			if (Dump.Y) Dump.println("UARestart.scan msgData seq="+msgData.seqNo+",msg="+msgData.msg);//~0220I~
            if (msgData.seqNo==PseqNo)                             //~0220I~
            {                                                      //~0220I~
            	rc=ii;                                             //~0220I~
                break;                                             //~0220I~
            }                                                      //~0220I~
        }                                                          //~0220I~
		if (Dump.Y) Dump.println("UARestart.scan rc="+rc+",seqNo="+PseqNo);//~0220I~
        return rc;                                                 //~0220I~
    }                                                              //~0220I~
//******************************************************************************//~0220I~
    public boolean receivedRequestSeqNo(boolean PswServer,int Pidx,int PseqNo,String Pmsg,int Ppos/*senderDevicename*/)//~0220I~//~0224R~
    {                                                              //~0220I~
		if (Dump.Y) Dump.println("UARestart.receivedRequestSeqNo swServer="+PswServer+",idx="+Pidx+",seq="+PseqNo+",receivedSeqno="+Arrays.toString(receivedSeqNo));//~va04R~
        int idx;                                                   //~0220I~
//      if (PswServer)                                             //~0220I~//~0221R~
        	idx=Pidx;                                              //~0220I~
//      else                                                       //~0220I~//~0221R~
//      	idx=MEMB.idxServer;                                    //~0220I~//~0221R~
        if (idx==-1)                                               //~0220I~
        {                                                          //~0220I~
			if (Dump.Y) Dump.println("UARestart.receivedRequestSeqNo @@@@ idx=-1");//~0220I~
            return false;                                          //~0220I~
        }                                                          //~0220I~
    	if (!chkStatus())	//not in game                          //~0224I~
        {                                                          //~0224I~
        	if (resetSeqNoBySyncQuery(Pmsg,Ppos))                  //~0224I~
            {                                                      //~0224I~
				if (Dump.Y) Dump.println("UARestart.receivedRequestSeqNo init by SYNQ_QUERY old="+receivedSeqNo[idx]+",new="+PseqNo);//~0224I~
		    	receivedSeqNo[idx]=PseqNo;                         //~0224I~
                return true;                                       //~0224I~
            }                                                      //~0224I~
        }                                                          //~0224I~
        if (PseqNo<=receivedSeqNo[idx])	//overrun                  //~0220I~
        {                                                          //~0220I~
			if (Dump.Y) Dump.println("UARestart.receivedRequestSeqNo @@@@ overrun new="+PseqNo+",idx="+idx+",old="+Arrays.toString(receivedSeqNo));//~0220I~//~0221R~
            return false;                                          //~0220I~
        }                                                          //~0220I~
    	receivedSeqNo[idx]=PseqNo;                                 //~0220I~
		if (Dump.Y) Dump.println("UARestart.receivedRequestSeqNo seqno="+Arrays.toString(receivedSeqNo));//~0220I~
        return true;                                               //~0220I~
    }                                                              //~0220I~
//******************************************************************************//~0224I~
    private boolean resetSeqNoBySyncQuery(String Pmsg,int Ppos/*senderDevicename*/)//~0224I~
    {                                                              //~0224I~
		if (Dump.Y) Dump.println("UARestart.resetSeqNoBySyncQuery msg="+Pmsg+",pos="+Ppos);//~0224I~
        if (AG.aUARestart==null)                                   //~0224I~
        {                                                          //~0224I~
			if (Dump.Y) Dump.println("UARestart.resetSeqNoBySyncQuery @@@@ no UARestart");//~0224I~
        	return false;                                          //~0224I~
        }                                                          //~0224I~
        int pos2=Pmsg.indexOf(MSG_SEP,Ppos);                       //~0224I~
        if (pos2<=Ppos)                                            //~0224I~
        {                                                          //~0224I~
			if (Dump.Y) Dump.println("UARestart.resetSeqNoBySyncQuery @@@@ no Msgid1");//~0224I~
        	return false;                                          //~0224I~
        }                                                          //~0224I~
        pos2++;                                                    //~0224I~
        int pos=Pmsg.indexOf(MSG_SEP,pos2);                        //~0224R~
        if (pos<=pos2)                                             //~0224I~
        {                                                          //~0224I~
			if (Dump.Y) Dump.println("UARestart.resetSeqNoBySyncQuery @@@@ no Msgid2 pos2="+pos2+",pos="+pos);//~0224R~
        	return false;                                          //~0224I~
        }                                                          //~0224I~
        int msgid=Utils.parseInt(Pmsg.substring(pos2,pos),-1);     //~0224R~
        if (msgid==-1)                                             //~0224I~
        {                                                          //~0224I~
			if (Dump.Y) Dump.println("UARestart.resetSeqNoBySyncQuery @@@@ no Msgid3");//~0224R~
        	return false;                                          //~0224I~
        }                                                          //~0224I~
        if (msgid!=GCM_SETTING_SYNC_QUERY   //first seqnoMsg from server//~0224R~
        &&  msgid!=GCM_SETTING_RESP         //may first seqnoMsg from client when rule updated at client before connect//~va04I~
        &&  msgid!=GCM_SETTING_SYNC_RESP)   //first seqnoMsg from client//~0224I~
        {                                                          //~0224I~
			if (Dump.Y) Dump.println("UARestart.resetSeqNoBySyncQuery msgid="+msgid);//~0224R~
            return false;                                          //~0224I~
        }                                                          //~0224I~
		if (Dump.Y) Dump.println("UARestart.resetSeqNoBySyncQuery rc=true"+",msgid="+msgid);//~0224R~//~va04R~
        return true;                                               //~0224I~
    }                                                              //~0224I~
//******************************************************************************//~0220I~
    public boolean receivedResponseSeqNo(boolean PswServer,int Pidx,int PseqNo)//~0220R~
    {                                                              //~0220I~
		if (Dump.Y) Dump.println("UARestart.receivedResponseSeqNo swServer="+PswServer+",idx="+Pidx+",seq="+PseqNo);//~0220R~
    	boolean rc=false;                                          //~0220I~
    	if (!chkStatus())                                          //~0220R~
        	return false;                                          //~0220I~
        int idx;                                                   //~0220I~
//      if (PswServer)                                             //~0220I~//~0221R~
        	idx=Pidx;                                              //~0220I~//~0221R~
//      else                                                       //~0220I~//~0221R~
//      	idx=MEMB.idxServer;                                    //~0220R~//~0221R~
        if (idx==-1)                                               //~0220I~
        {                                                          //~0220I~
			if (Dump.Y) Dump.println("UARestart.receivedResponseSeqNo @@@@ idx=-1");//~0220R~
            return false;                                          //~0220I~
        }                                                          //~0220I~
	    LinkedList<MsgData> list=msgDataLists[idx];                //~0220R~
        synchronized(list)                                         //~0220I~
        {                                                          //~0220I~
		    int pos=scan(list,PseqNo);                             //~0220I~
            if (pos>=0)                                            //~0220I~
            {                                                      //~0220I~
            	list.remove(pos);                                  //~0220I~
				if (Dump.Y) Dump.println("UARestart.receivedResponseSeqNo removed pos="+pos+",remains="+list.size());//~0220R~
                rc=true;
            }                                                      //~0220I~
            else                                                   //~0220I~
				if (Dump.Y) Dump.println("UARestart.receivedResponseSeqNo @@@@ not found remove seqno=@"+PseqNo+",remains="+list.size());//~0220R~//~0221R~
        }                                                          //~0220I~
        return rc;
    }                                                              //~0220I~
//******************************************************************************//~0220I~
    private boolean chkStatus()                                     //~0220R~//~0322R~
    {                                                              //~0220I~
    	boolean rc=Status.isGamingForMenuInGame();                  //~0220I~
		if (Dump.Y) Dump.println("UARestart.chkStatus rc="+rc);    //~va04I~
        return rc;                                                 //~0220I~
    }                                                              //~0220I~
    //***********************************************************************//~0220I~
    //*on Server                                                   //~0220I~
    //***********************************************************************//~0220I~
    public void sendPendingMsgToClientSeqNo(boolean[] PswsIOErr/*current Eswn seq*/)//~0220I~
    {                                                              //~0220I~
    //**************************                                   //~0220I~
        if (true)                                                  //~0222I~
        {                                                          //~0222I~
		    sendPendingMsgToClientSeqNoAll();                       //~0222I~
        	return;                                                //~0222I~
        }                                                          //~0222I~
    	if (Dump.Y) Dump.println("UARestart.sendPendingMsgToClientSeqNo waiting="+ Arrays.toString(PswsIOErr));//~0220I~
        Status.setStatusRestart(RESTART_RESTARTED); //server       //~0220I~
	    resetIOExceptionInGaming();                                //~0220I~
        Arrays.fill(swsWaitingResp,false);                         //~0220I~
        int eswn=ACC.getCurrentServerEswn();                       //~0221I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~0220I~
        {                                                          //~0220I~
    		BTIOThread thread=(BTIOThread)MEMB.getThread(ii);      //~0220I~
    		if (thread!=null)                                      //~0220I~
            {                                                      //~0220I~
            	int idxMember=ii;                                  //~0220I~
        		swsWaitingResp[ii]=true;                           //~0220I~
              if (isEmptyPendingMsgSeqNo(idxMember))               //~0220I~
            	sendRestartGame(RESTART_SERVER_STARTOFMSG_EMPTY,idxMember,eswn);//~0220I~//~0221R~
              else                                                 //~0220I~
              {                                                    //~0220I~
    			String[] msgS=getPendingMsgS(idxMember);	//msg will be removed from linklist at response msg received//~0221I~
            	sendRestartGame(RESTART_SERVER_STARTOFMSG,idxMember,eswn);//~0220I~//~0221R~
                flush(thread);                                     //~0220I~
                for (int jj=0;jj<msgS.length;jj++)                 //~0221R~
                {                                                  //~0220I~
                    String msg=msgS[jj];                           //~0221I~
                    msgS[jj]=null;                                 //~0221I~
                    if (Dump.Y) Dump.println("UARestart.sendPendingMsgToClientSeqNo idxMember="+idxMember+",msg="+msg);//~0220I~
                    BTIOThread.sendRestartMsg(idxMember,msg);      //~0220I~
	                flush(thread);                                 //~0220I~
                }                                                  //~0220I~
            	sendRestartGame(RESTART_SERVER_ENDOFMSG,idxMember,eswn);//~0220I~//~0221R~
	            flush(thread);   //experienced  out of sequence    //~0220I~
              }                                                    //~0220I~
            }                                                      //~0220I~
        }                                                          //~0220I~
    }                                                              //~0220I~
    //***********************************************************************//~0222I~
    //*on Server sequentially by seqNoAll                          //~0222I~
    //***********************************************************************//~0222I~
    public void sendPendingMsgToClientSeqNoAll()                   //~0222I~
    {                                                              //~0222I~
    	MsgData msgData;                                           //~0222I~
    //**************************                                   //~0222I~
    	if (Dump.Y) Dump.println("UARestart.sendPendingMsgToClientSeqNoAll");//~0222I~
        Status.setStatusRestart(RESTART_RESTARTED); //server       //~0222I~
        AG.aUADelayed.setStopTemporally(true);	//stop timer sent by client pending msg//~0223I~
	    resetIOExceptionInGaming();                                //~0222I~
        Arrays.fill(swsWaitingResp,false);                         //~0222I~
        int eswn=ACC.getCurrentServerEswn();                       //~0222I~
    	LinkedList<MsgData> msgS=getPendingMsgSeqNoAll();	//msg will be removed from linklist at response msg received//~0222I~
      if (msgS!=null)                                              //~0304I~
      {                                                            //~0304I~
        for (int ii=0;ii<msgS.size();ii++)                         //~0222I~
        {                                                          //~0222I~
        	msgData=msgS.get(ii);                                  //~0222I~
            int idxMember=msgData.idxMember;                       //~0222I~
    		BTIOThread thread=(BTIOThread)MEMB.getThread(idxMember);//~0222R~
    		if (thread==null)                                      //~0222I~
            	continue;                                           //~0222I~
        	if (!swsWaitingResp[idxMember])	//first msg of the thread//~0222R~
            {                                                      //~0222I~
            	sendRestartGame(RESTART_SERVER_STARTOFMSG,idxMember,eswn);//~0222I~
                flush(thread);                                     //~0222I~
            }                                                      //~0222I~
        	swsWaitingResp[idxMember]=true;                        //~0222R~
            String msg=msgData.msg;                                //~0222I~
            if (Dump.Y) Dump.println("UARestart.sendPendingMsgToClientSeqNoAll idxMember="+idxMember+",msg="+msg);//~0222I~
            BTIOThread.sendRestartMsg(idxMember,msg);              //~0222I~
	        flush(thread);                                         //~0222I~
        }                                                          //~0222I~
      }                                                            //~0304I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~0222I~
        {                                                          //~0222I~
        	if (swsWaitingResp[ii])	//sent a msg                   //~0222I~//~0304R~
            {                                                      //~0222I~
            	sendRestartGame(RESTART_SERVER_ENDOFMSG,ii,eswn);//~0222I~
	            flush(ii);   //experienced  out of sequence //~0222I~
            }                                                      //~0222I~
            else	//no msg                                       //~0222I~
            {                                                      //~0222I~
    			BTIOThread thread=(BTIOThread)MEMB.getThread(ii);  //~0222I~
    			if (thread!=null)                                  //~0222I~
                {                                                  //~0222I~
//TODO test	        	swsWaitingResp[ii]=true;                   //~0304R~
            		sendRestartGame(RESTART_SERVER_STARTOFMSG_EMPTY,ii,eswn);//~0222I~
		            flush(thread);   //experienced  out of sequence//~0222I~
                }                                                  //~0222I~
            }                                                      //~0222I~
        }                                                          //~0222I~
    }                                                              //~0222I~
    //*************************************************************//~0220I~
    private boolean isEmptyPendingMsgSeqNo(int Pidx)               //~0220I~
    {                                                              //~0220I~
        boolean rc=msgDataLists[Pidx].isEmpty();                   //~0220I~
        if (Dump.Y) Dump.println("UARestart.isEmptyPendingMsgSeqNo idx="+Pidx+",rc="+rc+",size="+msgDataLists[Pidx].size());//~0220I~
        return rc;                                                 //~0220I~
    }                                                              //~0220I~
    //*************************************************************//~0221I~
    private String[] getPendingMsgS(int Pidx)                      //~0221I~
    {                                                              //~0221I~
        LinkedList<MsgData> list=msgDataLists[Pidx];               //~0221I~
        int sz=list.size();                                        //~0221I~
        if (Dump.Y) Dump.println("UARestart.getPendingMsgS idx="+Pidx+",size="+sz);//~0221I~
        if (sz==0)                                                 //~0221I~
        	return null;                                           //~0221I~
        String[] msgS=new String[sz];                               //~0221I~
        for (int ii=0;ii<sz;ii++)                                  //~0221R~
        {                                                          //~0221I~
        	msgS[ii]=list.get(sz-ii-1).msg;                        //~0221R~
        }                                                          //~0221I~
        if (Dump.Y) Dump.println("UARestart.getPendingMsgS msgS="+Arrays.toString(msgS));//~0221I~
        return msgS;                                               //~0221I~
    }                                                              //~0221I~
    //*************************************************************//~0220I~
    private String getPendingMsgSeqNo(int Pidx)                    //~0220I~
    {                                                              //~0220I~
        String msg=null;                                           //~0220I~
		if (!isEmptyPendingMsgSeqNo(Pidx))                         //~0220I~
        {                                                          //~0220I~
            MsgData msgData=msgDataLists[Pidx].removeLast();       //~van0R~
            msg=msgData.msg;                                       //~0220I~
        }                                                          //~0220I~
        if (Dump.Y) Dump.println("UARestart.getPendingMsg idx="+Pidx+",msg="+msg+",size after="+msgDataLists[Pidx].size());//~0220I~
        return msg;                                                //~0220I~
    }                                                              //~0220I~
    //*************************************************************//~0222I~
    private LinkedList<MsgData> getPendingMsgSeqNoAll()            //~0222I~
    {                                                              //~0222I~
    	MsgData msgData;                                           //~0222I~
	    LinkedList<MsgData> list;                                  //~0222I~
    	HashMap<Integer,MsgData> map=new HashMap<Integer,MsgData>();       //~0222I~
        int mapCtr=0;                                              //~0222I~
        int lowKey=0x7fffffff;                                     //~0222I~
        int highKey=0;                                             //~0222I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~0222I~
        {                                                          //~0222I~
	        list=msgDataLists[ii];                                 //~0222I~
        	int sz=list.size();                                    //~0222I~
        	if (Dump.Y) Dump.println("UARestart.getPendingMsgSeqNoAll ii="+ii+",size="+sz);//~0222I~
        	for (int jj=0;jj<sz;jj++)                              //~0222I~
            {                                                      //~0222I~
        		msgData=list.get(sz-jj-1);                         //~0222R~
                int key=msgData.seqNoAll;                          //~0222I~
                map.put(key,msgData);                              //~0222I~
                mapCtr++;                                          //~0222I~
                if (key>highKey)                                   //~0222I~
                	highKey=key;                                   //~0222I~
                if (key<lowKey)                                    //~0222I~
                	lowKey=key;                                    //~0222I~
            }                                                      //~0222I~
        }                                                          //~0222I~
        if (Dump.Y) Dump.println("UARestart.getPendingMsgSeqNoAll ctr="+mapCtr+",low="+lowKey+",high="+highKey);//~0222I~
        if (mapCtr==0)                                             //~0222I~
            return null;                                           //~0222I~
//  	LinkedList<MsgData> listAll=new LinkedList();              //~van0R~
    	LinkedList<MsgData> listAll=new LinkedList<>();            //~van0I~
        for (int key=lowKey;key<=highKey;key++)                    //~0222I~
        {                                                          //~0222I~
        	if (map.containsKey(key))                              //~0222I~
            {                                                      //~0222I~
        		msgData=map.get(key);                              //~0222I~
        		if (Dump.Y) Dump.println("UARestart.getPendingMsgSeqNoAll mapget key="+key+",msgdata="+msgData.toString());//~0222I~
                listAll.addLast(msgData);                          //~0222I~
            }                                                      //~0222I~
        }                                                          //~0222I~
        return listAll;                                            //~0222I~
    }                                                              //~0222I~
    //***********************************************************************//~0220I~
    //*on Client                                                   //~0220I~
    //***********************************************************************//~0220I~
    public void sendPendingMsgToServerSeqNo()                      //~0220I~
    {                                                              //~0220I~
    //**************************                                   //~0220I~
        if (Dump.Y) Dump.println("UARestart.sendPendingMsgToServerSeqNo");//~0220I~
        int idxMember=MEMB.idxServer;                              //~0220I~
    	String[] msgS=getPendingMsgS(idxMember);	//msg will be removed from linklist at response msg received//~0221I~
        if (msgS==null)                                            //~0221I~
            return;                                                //~0221I~
        for (int ii=0;ii<msgS.length;ii++)                                                   //~0220R~//~0221R~
        {                                                          //~0220R~
            String msg=msgS[ii];                                   //~0221R~
            msgS[ii]=null;                                         //~0221I~
            if (Dump.Y) Dump.println("UARestart.sendPendingMsgToServerSeqNo idxMember="+idxMember+",msg="+msg);//~0220R~
            BTIOThread.sendRestartMsg(idxMember,msg);              //~0220R~
            flush(idxMember);                                      //~0220R~
        }                                                          //~0220R~
    }                                                              //~0220I~
    //***********************************************************************//~0221I~
    //*on Server,no set seqNo to avoid overrun                     //~0221I~
    //***********************************************************************//~0221I~
    public void sendToClientAll(int PmsgID,String Pdata)           //~0221R~
    {                                                              //~0221I~
    //**************************                                   //~0221I~
        if (Dump.Y) Dump.println("UARestart.sendToClientAll msgid="+PmsgID+",data="+Pdata);//~0221I~
        int eswn=ACC.getCurrentServerEswn();                       //~0221I~
        for (int ii=1;ii<PLAYERS;ii++)                             //~v@@@I~//~0221I~
        {                                                          //~v@@@I~//~0221I~
            Accounts.Account a=AG.aAccounts.accounts[ii];                            //~v@@@I~//~0221R~
            int idxMember=a.idxMembers;                             //~0221I~
            if (!a.isDummy())                                      //~v@@@I~//~0221R~
                sendToClient(idxMember,eswn,PmsgID,Pdata);         //~0221I~
        }                                                          //~v@@@I~//~0221I~
    }                                                              //~0221I~
    //***********************************************************************//~0221I~
    private void sendToClient(int PidxMember,int Peswn,int PmsgID,String Pdata)//~0221I~
    {                                                              //~0221I~
    //**************************                                   //~0221I~
        if (Dump.Y) Dump.println("UARestart.sendToClient idxMember="+PidxMember+",eswn="+Peswn+",msgid="+PmsgID+",data="+Pdata);//~0221I~
        String msg=PmsgID+MSG_SEP+Peswn+MSG_SEPAPP2+Pdata;         //~0221R~
        BTIOThread.sendRestartGame(PidxMember,GCM_USER_ACTION,msg);//~0221R~
    }                                                              //~0221I~
//******************************************************************************//~0220I~
//******************************************************************************//~0220I~
//******************************************************************************//~0220I~
    class MsgData                                                  //~0220I~
    {                                                              //~0220I~
        int seqNo,seqNoAll,idxMember;                                                 //~0220I~//~0222R~
        String msg;                                                //~0220I~
        public MsgData(int Pidx,int PseqNoAll,int PseqNo,String Pmsg)                     //~0220I~//~0222R~
        {                                                          //~0220I~
        	seqNoAll=PseqNoAll; seqNo=PseqNo; msg=Pmsg; idxMember=Pidx;                                //~0220I~//~0222R~
        }                                                          //~0220I~
        public String toString()                                   //~0222I~
        {                                                          //~0222I~
        	return "idx="+idxMember+",seqNoAll="+seqNoAll+",seqNo="+seqNo+",msg="+msg;//~0222R~
        }                                                          //~0222I~
    }                                                              //~0220I~
}//class                                                           //~v@@@R~
