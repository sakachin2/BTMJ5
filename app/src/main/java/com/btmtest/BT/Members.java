//*CID://+va9iR~:                             update#= 308;        //~va9iR~
//**********************************************************************//~v@@@I~
//2021/06/19 va9i (Bug)err by lacking member ast startGame after matchi mode anded bu disconnecting.//~va9iI~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2020/11/20 va46 (Bug)reconnected member could not be disconnect  //~va46I~
//**********************************************************************//~va46I~
package com.btmtest.BT;                                            //~v@@@R~
import android.bluetooth.BluetoothSocket;                         //~v@@@I~

import static com.btmtest.BT.BTMulti.*;
import static com.btmtest.StaticVars.AG;                           //~v@@@I~
import static com.btmtest.game.GConst.*;

import com.btmtest.MainView;
import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.GConst;
import com.btmtest.game.Robot;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;
import com.btmtest.wifi.IPIOThread;

import java.net.Socket;
import java.util.Arrays;

//****************************************************         //~1AbTI~//~v@@@R~
public class Members                                                      //~v@@@R~
{                                                              //~1AbTI~//~v@@@R~
    public static final int MS_LOCAL =0x01; //myself               //~v@@@R~
    public static final int MS_SERVER=0x02; //server for client OR server itself with MS_LOCAL//~v@@@R~
    public static final int MS_CLIENT=0x04; //client with MS_LOCAL //~v@@@R~
    public static final int MS_REMOTECLIENT=0x08; //all client for server,other client for client//~v@@@I~
    public static final int MS_PLAYALONE   =0x10; //training mode  //~va66I~
                                                                   //~v@@@I~
    public static final int MS_SETUPEND=0x100;//setup end          //~v@@@I~
    public static final int MS_IOERR=0x200;                        //~v@@@I~
    public static final int MS_ENDGAME=0x400;//endgame issued      //~9B19I~
    public static final int MS_RECONNECTERR=0x800;                 //~0110I~
                                                                   //~v@@@I~
                                                                   //~v@@@I~
    public static final int SS_NONE=0;                             //~v@@@R~
    public static final int SS_OK=1;                               //~v@@@I~
    public static final int SS_NG=2;                               //~v@@@I~
                                                                   //~v@@@I~
//  public static final String YOURNAME_TRAINING="--Me--";         //~va66R~
    public static final String YOURNAME_TRAINING= GConst.robotYourNameDefault[0];//~va66I~
                                                                   //~va66I~
    public MemberData[] MD;                                        //~v@@@R~
    private int maxMember;                                                 //~v@@@I~
    private String dummyName="";                                   //~v@@@I~
    public int idxServer=-1,idxLocal=-1;                               //~v@@@R~
                                                                   //~v@@@I~
    public static final int CM_NONE=-1;                            //~v@@@R~
    public static final int CM_BT=0;                               //~v@@@I~
    public static final int CM_WD=1;                               //~v@@@I~
    public int connectionMode=CM_NONE;                             //~v@@@I~
    private String[] interruptedYourName=new String[PLAYERS];      //~v@@@I~
//*****************                                            //~1AbTI~//~v@@@R~
    public Members(int Pmaxmember)                                 //~v@@@R~
    {                                                          //~1AbTI~//~v@@@R~
    	maxMember=Pmaxmember;                                      //~v@@@I~
    	MD=new MemberData[Pmaxmember];                              //~v@@@I~
    }                                                          //~1AbTI~//~v@@@R~
    public Members(int Pmaxmember,String Pdummyname)               //~v@@@I~
    {                                                              //~v@@@I~
    	this(Pmaxmember);                         //~v@@@R~
        clear(Pdummyname);                                       //~v@@@R~
        dummyName=Pdummyname;                                    //~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************************//~0112I~
    public boolean isDummyName(String Pname)                       //~0112I~
    {                                                              //~0112I~
    	boolean rc=Pname!=null && Pname.equals(dummyName);         //~0112I~
        if (Dump.Y) Dump.println("Members.isDummyName rc="+rc+",Pname="+Utils.toString(Pname));//~0112I~
        return rc;
    }                                                              //~0112I~
    //*************************************************************//~0305I~
    public void toStringMD(String Pcmt)                            //~0305R~
    {                                                              //~0305I~
    	if (MD==null)                                              //~0305I~
        {                                                          //~0305I~
        	if (Dump.Y) Dump.println(Pcmt+" Members.toStringMD=null");//~0305R~
        }                                                          //~0305I~
        else                                                       //~0305I~
        {                                                          //~0305I~
        	for (int ii=0;ii<PLAYERS;ii++)                         //~0305I~
	        	if (Dump.Y) Dump.println(Pcmt+" MD["+ii+"]="+MD[ii].toString());//~0305R~
        }                                                          //~0305I~
    }                                                              //~0305I~
    //*************************************************************//~v@@@I~
    //*                                                            //~v@@@I~
    //*************************************************************//~v@@@I~
    public int getMemberRole()                                     //~v@@@I~
    {                                                              //~v@@@I~
    	int rc=-1;                                                 //~v@@@I~
    	if (idxLocal!=-1)	//connected                            //~v@@@I~
			if (idxServer==idxLocal)                               //~v@@@I~
             	rc=MS_SERVER;                                      //~v@@@I~
            else                                                   //~v@@@I~
             	rc=MS_CLIENT;                                     //~v@@@I~
        if (Dump.Y) Dump.println("Members.getMemberRole rc="+rc);  //~v@@@I~
        return rc;
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public boolean resetMode(int PconnectionMode)                   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Members.resetMode Pmode="+PconnectionMode+",old="+connectionMode);//~v@@@I~
    	boolean rc=false;
        if (connectionMode!=PconnectionMode)	//BTCDialog or WD  //~v@@@I~
        {                                                          //~v@@@I~
            for (int ii=0;ii<maxMember;ii++)                       //~v@@@I~
            {                                                      //~v@@@I~
                if (Dump.Y) Dump.println("Members.resetMode ii="+ii+",MD="+MD[ii].toString());//~v@@@I~
                if (MD[ii].connectionMode!=PconnectionMode)        //~v@@@I~
                {                                                  //~v@@@I~
                    if (Dump.Y) Dump.println("Members.resetMode set new");//~v@@@I~
//                  if (connectionMode!=CM_NONE)    //initial      //~v@@@I~//~0118R~
                    if (MD[ii].connectionMode!=CM_NONE)    //initial//~0118I~
                    {
                        rc=true; //reset done
                        MD[ii]=new MemberData(dummyName);         //~v@@@I~
                    }
//                  MD[ii].connectionMode=PconnectionMode;         //~v@@@I~//~0118R~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
            connectionMode=PconnectionMode;                       //~v@@@I~
			AG.aBTMulti.memberRole=ROLE_UNDEFINED;                 //~0118I~
            idxLocal=-1; idxServer=-1;                             //~0118I~
        }
        return rc;//~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    private void clear(String Pdummyname)                           //~v@@@I~
	{                                                              //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
//      	MD[ii]=new MemberData(Pdummyname,null/*addr*/,null/*socket*/,0/*status*/);//~v@@@R~
        	MD[ii]=new MemberData(Pdummyname);                     //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~0120I~
    public void clear()                                            //~0120I~
	{                                                              //~0120I~
        if (Dump.Y) Dump.println("Members.clear dummyName="+dummyName);//~0120I~
    	clear(dummyName);                                          //~0120I~
    }                                                              //~0120I~
    //*************************************************************//~v@@@I~
    public void resetSyncDate(boolean PswRemoteOnly)               //~v@@@R~
	{                                                              //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
        	if (PswRemoteOnly &&  MD[ii].isLocal())                //~v@@@I~
            	continue;                                          //~v@@@I~
	        MD[ii].setRuleSyncDateBTIO(null);                      //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~9B22I~
    public void resetSetupEnd()                                    //~9B22I~
	{                                                              //~9B22I~
        if (Dump.Y) Dump.println("Members.resetSetupEnd");         //~9B22I~
        for (int ii=0;ii<maxMember;ii++)                           //~9B22I~
        {                                                          //~9B22I~
	        MD[ii].resetSetupEnd();                                //~9B22I~
        }                                                          //~9B22I~
    }                                                              //~9B22I~
    //*************************************************************//~v@@@I~
    //*rc=null:not found                                           //~v@@@R~
    //*************************************************************//~v@@@I~
    public BluetoothSocket getMemberSocket(String Pname)               //~v@@@R~
	{                                                              //~v@@@I~
        int idx=search(Pname);                                     //~v@@@R~
        if (idx>=0)                                                //~v@@@R~
        {                                                          //~9B04I~
        	if (Dump.Y) Dump.println("Members.getMemberSocket socket="+Utils.toString(MD[idx].socket));//~9B04I~
            return MD[idx].socket;                                 //~v@@@R~
        }                                                          //~9B04I~
        return null;                                               //~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*rc=null:not found                                           //~v@@@I~
    //*************************************************************//~v@@@I~
    public Socket getMemberSocketIP(String Pname)                  //~v@@@I~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Members.getMemberSocketIP name="+Pname);//~v@@@I~
        int idx=search(Pname);                                     //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        {                                                          //~9B04I~
        	if (Dump.Y) Dump.println("Members.getMemberSocketIP socketIP="+Utils.toString(MD[idx].socketIP));//~9B04I~
            return MD[idx].socketIP;                               //~v@@@I~
        }                                                          //~9B04I~
        return null;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*get remote addr of socket                                   //~v@@@I~
    //*************************************************************//~v@@@I~
    public String getMemberAddr(String Pname)                      //~v@@@R~
	{                                                              //~v@@@I~
        int idx=search(Pname);                                     //~v@@@I~
        String ipa=null;                                           //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
            ipa=MD[idx].addr;                                      //~v@@@R~
        if (Dump.Y) Dump.println("Members.getMemberAddr name="+Pname+",idx="+idx+",ipa="+ipa);//~v@@@R~
        return ipa;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public Thread getMemberThread(String Pname)                    //~v@@@I~
	{                                                              //~v@@@I~
	    int idx=search(Pname);                                     //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        {                                                          //~v@@@I~
        	if (Dump.Y) Dump.println("Members.getMemberThread name="+Pname+",idx="+idx+",thread="+Utils.toString(MD[idx].thread));//~v@@@I~
	        return MD[idx].thread;                                 //~v@@@I~
        }                                                          //~v@@@I~
        return null;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~9B19I~
    public void setEndGame(int Pidx)                               //~9B19I~
	{                                                              //~9B19I~
    	if (Pidx>=0 && Pidx<maxMember)                             //~9B19I~
        {                                                          //~9B19I~
        	MD[Pidx].status|=MS_ENDGAME;		//=0x400;//endgame issued//~9B19I~
        	if (Dump.Y) Dump.println("Members.setEndGame MD="+MD[Pidx].toString());//~9B19I~
        }                                                          //~9B19I~
        if (Dump.Y) Dump.println("Members.setEndGame idx="+Pidx);  //~9B19I~
    }                                                              //~9B19I~
    //*************************************************************//~9B19I~
    public boolean isEndGame(int Pidx)                             //~9B19I~
	{                                                              //~9B19I~
    	boolean rc=false;                                          //~9B19I~
    	if (Pidx>=0 && Pidx<maxMember)                             //~9B19I~
        {                                                          //~9B19I~
        	if (Dump.Y) Dump.println("Members.isEndGame MD="+MD[Pidx].toString());//~9B19I~
        	rc=(MD[Pidx].status & MS_ENDGAME)!=0;		//=0x400;//endgame issued//~9B19I~
        }                                                          //~9B19I~
        if (Dump.Y) Dump.println("Members.isEndGame idx="+Pidx+",rc="+rc);//~9B19I~
        return rc;
	}                                                              //~9B19I~
    //*************************************************************//~v@@@I~
    public String getName(int Pidx)                          //~v@@@I~
	{                                                              //~v@@@I~
    	String name="";                                            //~v@@@I~
    	if (Pidx>=0 && Pidx<maxMember)                             //~v@@@R~
        	name=MD[Pidx].name;                                    //~v@@@I~
        return name;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public BluetoothSocket getBluetoothSocket(int Pidx)            //~v@@@I~
	{                                                              //~v@@@I~
    	BluetoothSocket bts=null;                                  //~v@@@I~
    	if (Pidx>=0 && Pidx<maxMember)                             //~v@@@R~
        	bts=MD[Pidx].socket;                                   //~v@@@I~
        if (Dump.Y) Dump.println("Members.getBluetoothSocket idx="+Pidx+",socket="+Utils.toString(bts));//~v@@@I~
        return bts;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public String getYourNameName(int Pidx)                        //~v@@@I~
	{                                                              //~v@@@I~
    	String name="";                                            //~v@@@I~
    	if (Pidx>=0 && Pidx<maxMember)                             //~v@@@R~
        	name=MD[Pidx].getYourNameName();                       //~v@@@I~
        if (Dump.Y) Dump.println("Members.getYourNameName idx="+Pidx+",name="+name);//~v@@@I~
        return name;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public String getYourNameName(String Pdevname)                 //~v@@@I~
	{                                                              //~v@@@I~
    	String name;                                               //~v@@@I~
        int idx=search(Pdevname);                                     //~v@@@I~
        if (idx<0)                                                 //~v@@@I~
        	name=Pdevname;                                         //~v@@@I~
        else                                                       //~v@@@I~
    		name=getYourNameName(idx);                             //~v@@@I~
        if (Dump.Y) Dump.println("Members.getYourNameName name="+Pdevname+",name="+name);//~v@@@I~
        return name;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*get devicename by yourname                                  //~v@@@I~
    //*************************************************************//~v@@@I~
    public String getName(String Pyourname)                        //~v@@@I~
	{                                                              //~v@@@I~
		int idx=searchByYourname(Pyourname);                        //~v@@@I~
        if (idx<0)                                                 //~v@@@I~
        	return null;                                           //~v@@@I~
        if (Dump.Y) Dump.println("Members.getName by yourname="+MD[idx].name);//~v@@@I~
        return MD[idx].name;                                       //~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*update yourname by name change by BTCDialog                 //~v@@@I~
    //*************************************************************//~v@@@I~
    public boolean updateYourName(String PoldYourname,String PnewYourname)//~v@@@I~
	{                                                              //~v@@@I~
		int idx=searchByYourname(PoldYourname);                    //~v@@@I~
        if (idx<0)                                                 //~v@@@I~
        	return false;                                          //~v@@@I~
        if (Dump.Y) Dump.println("Members.updateYourName old="+PoldYourname+"new="+PnewYourname+",name="+MD[idx].name);//~v@@@I~
        MD[idx].yourname=PnewYourname;                             //~v@@@I~
        return true;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public String getAddr(int Pidx)                                //~v@@@I~
	{                                                              //~v@@@I~
    	String addr=null;                                          //~v@@@I~
    	if (Pidx>=0 && Pidx<maxMember)                             //~v@@@R~
        	addr=MD[Pidx].addr;                                    //~v@@@I~
        return addr;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public Thread getThread(int Pidx)                              //~v@@@I~
	{                                                              //~v@@@I~
    	Thread t=null;                                             //~v@@@I~
    	if (Pidx>=0 && Pidx<maxMember)                             //~v@@@R~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Members.getThread idx="+Pidx+",MD="+MD[Pidx].toString());//~v@@@I~
        	t=MD[Pidx].thread;                                     //~v@@@I~
        }                                                          //~v@@@I~
        return t;                                             //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~9B04I~
    public Thread getThread(String Pdevname)                       //~9B04I~
	{                                                              //~9B04I~
    	Thread t=null;                                             //~9B04I~
        int idx=search(Pdevname);                                  //~9B04I~
        if (idx>=0)                                                //~9B04I~
        {                                                          //~9B04I~
            if (Dump.Y) Dump.println("Members.getThread name="+Pdevname+",thread="+Utils.toString(MD[idx].thread));//~9B04I~
        	t=MD[idx].thread;                                     //~9B04I~
        }                                                          //~9B04I~
        return t;                                                  //~9B04I~
    }                                                              //~9B04I~
    //*************************************************************//~v@@@I~
    public String getYourName(int Pidx)                            //~v@@@I~
	{                                                              //~v@@@I~
    	String yn=null;                                            //~v@@@I~
    	if (Pidx>=0 && Pidx<maxMember)                             //~v@@@R~
        {                                                          //~v@@@I~
        	yn=MD[Pidx].yourname;                                  //~v@@@I~
            if (Dump.Y) Dump.println("Members.getYourName="+yn+",devicename="+MD[Pidx].name);//~v@@@R~
        }                                                          //~v@@@I~
        return yn;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public String getYourName(String Pdevicename)                  //~v@@@I~
	{                                                              //~v@@@I~
    	String yn=null;                                            //~v@@@I~
        int idx=search(Pdevicename);                                //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
	        yn=MD[idx].yourname;                                   //~v@@@I~
        return yn;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public int getConnectedCtr()                                  //~v@@@I~
	{                                                              //~v@@@I~
    	int ctr=0;                                                 //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~9B07I~
    		if (Dump.Y) Dump.println("Members.getConnectedCtr MD["+ii+"]="+MD[ii].toString());//~9B07I~
//      	if (MD[ii].addr!=null)                                 //~v@@@R~
        	if (MD[ii].thread!=null)                               //~v@@@I~
            {                                                      //~v@@@I~
		        if (Dump.Y) Dump.println("Members.getConnectedCtr add="+MD[ii].addr);//~v@@@I~
            	ctr++;                                             //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~9B07I~
		if (Dump.Y) Dump.println("Members.getConnectedCtr ctr="+ctr);//~v@@@I~
        return ctr;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public void getConnectionModeCtr(int[] Pctrs)                   //~v@@@I~
	{                                                              //~v@@@I~
    	Arrays.fill(Pctrs,0);                                       //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Members.getConnectionModeCtr ii="+ii+",MD="+MD[ii].toString());//~v@@@I~
        	if (MD[ii].thread!=null)                               //~v@@@I~
            {                                                      //~v@@@I~
            	if (MD[ii].socket!=null)	//bluetooth            //~v@@@I~
                	Pctrs[CM_BT]++;                                //~v@@@R~
                else                                               //~v@@@I~
            	if (MD[ii].socketIP!=null)  //WifiDirect           //~v@@@I~
                	Pctrs[CM_WD]++;                                //~v@@@R~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
		if (Dump.Y) Dump.println("Members.getConnectionModeCtr ctrs="+Arrays.toString(Pctrs));//~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public int getMemberCtr()                                      //~v@@@I~
	{                                                              //~v@@@I~
    	int ctr=0,ctrRemoteClient=0;                               //~v@@@R~
        boolean swClient=false;                                    //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@R~
        {                                                          //~v@@@R~
            if (MD[ii].thread!=null)                              //~v@@@R~
            {                                                      //~v@@@I~
            	ctr++;                                             //~v@@@R~
            	if ((MD[ii].status & MS_SERVER)!=0)                //~v@@@I~
                	swClient=true;                                 //~v@@@I~
            	if ((MD[ii].status & MS_LOCAL)!=0)                 //~v@@@I~
                	if (MD[ii].yourname!=null && !MD[ii].yourname.equals(""))//~v@@@R~
                    	ctrRemoteClient++;                         //~v@@@I~
            }                                                      //~v@@@I~
            if (Dump.Y) Dump.println("Members.getMemberCtr ctr="+ctr+",ctrRemoteClient="+ctrRemoteClient+",ii="+ii+",MD="+MD[ii].toString());//~v@@@R~
        }                                                          //~v@@@R~
        if (ctr>0)   //anyone connected                            //~v@@@R~
        	if (swClient)                                          //~v@@@I~
            	ctr=ctrRemoteClient+2; 	//of server & local        //~v@@@R~
            else        //server                                   //~v@@@I~
	        	ctr++;	//of Server                                //~v@@@R~
        if (Dump.Y) Dump.println("Members.getMemberCtr rc="+ctr);  //~v@@@I~
        return ctr;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public void saveForReconnect()                                 //~v@@@I~
    {                                                              //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
        	String yn=MD[ii].getYourName();                        //~v@@@I~
        	interruptedYourName[ii]=(yn==null ? null : new String(yn));//~v@@@I~
        }                                                          //~v@@@I~
	    if (Dump.Y) Dump.println("Members.saveForReconnect interruptedYourName="+Arrays.toString(interruptedYourName));//~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public String getYourNameForReconnect(String Pdevicename)      //~v@@@R~
    {                                                              //~v@@@I~
        String yn="";                                              //~v@@@I~
        int idx=search(Pdevicename);                               //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        	yn=interruptedYourName[idx];                           //~v@@@I~
	    if (Dump.Y) Dump.println("Members.getYourNameForReconnect devname="+Pdevicename+",idx="+idx+",yn="+yn);//~v@@@I~
        return yn;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************//~v@@@I~//~0218R~
//    public boolean isReconnectedAll()                              //~v@@@I~//~0218R~
//    {                                                              //~v@@@I~//~0218R~
//        int role=getMemberRole();                                   //~v@@@I~//~0218R~
//        if (Dump.Y) Dump.println("Members.isReconnectedAll role="+role+",oldMember="+Arrays.toString(interruptedYourName));//~v@@@I~//~0218R~
//        boolean rc=false;                                          //~v@@@I~//~0218R~
//        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~//~0218R~
//        {                                                          //~v@@@I~//~0218R~
//            if (Dump.Y) Dump.println("Members.isReconnectedAll MD="+MD[ii].toString());//~v@@@I~//~0218R~
//            if (MD[ii].isRobot())                                  //~v@@@I~//~0218R~
//                continue;                                          //~v@@@I~//~0218R~
//            if (interruptedYourName[ii]==null)                    //~v@@@I~//~0218R~
//                continue;                                          //~v@@@I~//~0218R~
//            if (role==MS_SERVER)                                   //~v@@@I~//~0218R~
//            {                                                      //~v@@@I~//~0218R~
//                if (MD[ii].isRemoteClient())                             //~v@@@I~//~0218R~
//                    if (MD[ii].getThread()!=null && MD[ii].getYourName().equals(interruptedYourName[ii]))//~v@@@I~//~0218R~
//                    {                                              //~v@@@I~//~0218R~
//                        rc=true;                                   //~v@@@I~//~0218R~
//                        break;                                     //~v@@@I~//~0218R~
//                    }                                              //~v@@@I~//~0218R~
//            }                                                      //~v@@@I~//~0218R~
//            else                                                   //~v@@@I~//~0218R~
//            {                                                      //~v@@@I~//~0218R~
//                if (MD[ii].isServer())                             //~v@@@I~//~0218R~
//                    if (MD[ii].getThread()!=null && MD[ii].getYourName().equals(interruptedYourName[ii]))//~v@@@I~//~0218R~
//                    {                                              //~v@@@I~//~0218R~
//                        rc=true;                                   //~v@@@I~//~0218R~
//                        break;                                     //~v@@@I~//~0218R~
//                    }                                              //~v@@@I~//~0218R~
//            }                                                      //~v@@@I~//~0218R~
//        }                                                          //~v@@@I~//~0218R~
//        if (Dump.Y) Dump.println("Members.isReconnectedAll rc="+rc);//~v@@@I~//~0218R~
//        return rc;                                                //~v@@@I~//~0218R~
//    }                                                              //~v@@@I~//~0218R~
    //*************************************************************//~v@@@I~
    public int getLength()                                         //~v@@@I~
	{                                                              //~v@@@I~
        return maxMember;                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*rc=-1:not found                                             //~v@@@I~
    //*************************************************************//~v@@@I~
    public int search(String Pname)                                //~v@@@R~
	{                                                              //~v@@@I~
    	if (Pname==null)                                           //~v@@@I~
        	return -1;                                             //~v@@@I~
    	if (Dump.Y) Dump.println("Members.search by name="+Pname);  //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
		    if (Dump.Y) Dump.println("Members.search MD["+ii+"]="+MD[ii].toString());//~9B07I~
        	if (Pname.equals(MD[ii].name))                         //~v@@@I~
            {                                                      //~v@@@I~
		    	if (Dump.Y) Dump.println("Members.search found ii="+ii);//~v@@@I~
            	return ii;                                         //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
		if (Dump.Y) Dump.println("Members.search not found");      //~v@@@I~
        return -1;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~//~9B03R~
    public int searchByMacAddr(String Pname)                     //~v@@@I~//~9B03R~
    {                                                              //~v@@@I~//~9B03R~
    	if (Pname==null)                                           //~9B03I~
        	return -1;                                             //~9B03I~
    	if (Dump.Y) Dump.println("Members.search by name="+Pname); //~9B03I~
        for (int ii=0;ii<maxMember;ii++)                           //~9B03I~
        {                                                          //~9B03I~
	    	if (Dump.Y) Dump.println("Members.searchbyMacAddr MD=ii="+ii+"="+MD[ii].toString());//~9B03I~
        	if (MD[ii].macAddr!=null && Pname.equals(MD[ii].macAddr))//~9B03I~
            {                                                      //~9B03I~
		    	if (Dump.Y) Dump.println("Members.searchByMacAddr found ii="+ii);//~9B03I~
            	return ii;                                         //~9B03I~
            }                                                      //~9B03I~
        }                                                          //~9B03I~
		if (Dump.Y) Dump.println("Members.searchByMacAddr not found");//~9B03I~
        return -1;                                                 //~9B03I~
    }                                                              //~v@@@I~//~9B03R~
    //*************************************************************//~v@@@I~//~9B03I~
    //*serach by name(=IPAddr) if not found search ioerr entry     //~0117R~
    //*************************************************************//~9B03I~
    private int searchReconnectWD(String Pname/*IPAddr*/)                     //~v@@@I~//~9B03I~//~0117R~
	{                                                              //~v@@@I~//~9B03I~
    	if (Pname==null)                                           //~v@@@I~//~9B03I~
        	return -1;                                             //~v@@@I~//~9B03I~
    	if (Dump.Y) Dump.println("Members.searchReconnectWD by name="+Pname);//~v@@@I~//~9B03I~
        int idxFound=-1;                                          //~v@@@I~//~9B03I~
//        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~//~9B03I~
//        {                                                          //~v@@@I~//~9B03I~
//            if (Dump.Y) Dump.println("Members.search MD=ii="+ii+"="+MD[ii].toString());//~v@@@I~//~9B03I~
//            if (MD[ii].addr!=null && Pname.equals(MD[ii].addr)     //~v@@@I~//~9B03I~
//            && isIOErr(ii))                                        //~v@@@I~//~9B03I~
//            {                                                      //~v@@@I~//~9B03I~
//                if (Dump.Y) Dump.println("Members.search found sameIP and IOERR ii="+ii);//~v@@@I~//~9B03I~
//                idxFound=ii;                                       //~v@@@I~//~9B03I~
//                break;                                             //~v@@@I~//~9B03I~
//            }                                                      //~v@@@I~//~9B03I~
//        }                                                          //~v@@@I~//~9B03I~
		int ctrIOErr=0;                                            //~0117I~
		int idxIOErr=-1;                                           //~0117I~
		int idxName=-1;                                            //~0117I~
        for (int ii=0;ii<maxMember;ii++)                       //~v@@@I~//~9B03I~//~0117R~
        {                                                      //~v@@@I~//~9B03I~//~0117R~
            if (Dump.Y) Dump.println("Members.searchReconnectWD MD=ii="+ii+"="+MD[ii].toString());//~v@@@I~//~9B03I~//~0117R~
            if (MD[ii].getThread()!=null)                          //~0117R~
            	continue;                                          //~0117I~
            if (Pname.equals(MD[ii].addr))                         //~0117I~
            {                                                      //~0117I~
                if (Dump.Y) Dump.println("Members.searchReconnectWD found by IOE and name ii="+ii);//~0117I~
                if (isIOErr(ii))                                   //~0117I~
                {                                                  //~0117I~
                    if (Dump.Y) Dump.println("Members.searchReconnectWD found by IOE and Name ii="+ii);//~0117I~
                	idxFound=ii;                                   //~0117I~
	                break;                                         //~0117I~
                }                                                  //~0117I~
                idxName=ii;                                        //~0117I~
            }                                                      //~0117I~
            if (isIOErr(ii))                                   //~v@@@I~//~9B03I~//~0117R~
            {                                                      //~0117R~
                idxIOErr=ii;                                       //~0117R~
                ctrIOErr++;                                        //~0117R~
            }                                                      //~0117R~
        }                                                      //~v@@@I~//~9B03I~//~0117R~
        if (Dump.Y) Dump.println("Members.searchReconnectWD idxFound="+idxFound+",ctrIOErr="+ctrIOErr+",idxIOErr="+idxIOErr+",idxName="+idxName);//~0117R~
        if (idxFound<0)                                            //~0117I~
        {                                                          //~0117I~
        	if (idxName!=-1)                                       //~0117I~
            {                                                      //~0117I~
            	idxFound=idxName;                                  //~0117I~
                if (Dump.Y) Dump.println("Members.searchReconnectWD found by name="+idxFound);//~0117I~
            }                                                      //~0117I~
            else                                                   //~0117I~
        	if (ctrIOErr==1)                                       //~0117I~
            {                                                      //~0117I~
            	idxFound=idxIOErr;                                 //~0117I~
                if (Dump.Y) Dump.println("Members.searchReconnectWD found by ioerr="+idxFound);//~0117R~
            }                                                      //~0117I~
        }                                                          //~0117I~
		if (Dump.Y) Dump.println("Members.searchReconnectWD idxFound="+idxFound);//~v@@@I~//~9B03I~//~9B05R~
        return idxFound;                                           //~v@@@I~//~9B03I~
    }                                                              //~v@@@I~//~9B03I~
    //*************************************************************//~v@@@I~
    //*rc=-1:not found                                             //~v@@@I~
    //*************************************************************//~v@@@I~
    public int searchByYourname(String Pyourname)                  //~v@@@I~
	{                                                              //~v@@@I~
    	if (Pyourname==null)                                       //~v@@@I~
        	return -1;                                             //~v@@@I~
		if (Dump.Y) Dump.println("Members.searchByYourname parm="+Pyourname);//~9B07I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
		    if (Dump.Y) Dump.println("Members.searchByYourname MD["+ii+"]="+MD[ii].toString());//~9B07R~//~0110R~
        	if (MD[ii].yourname!=null && Pyourname.equals(MD[ii].yourname))//~v@@@R~
            	return ii;                                         //~v@@@I~
        }                                                          //~v@@@I~
        return -1;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~0305I~
    //*rc=-1:not found                                             //~0305I~
    //*************************************************************//~0305I~
    public int searchRobot(int PctrRobot)                          //~0305I~
	{                                                              //~0305I~
        int ctrRobot=0;                                            //~0305I~
        int rc=-1;                                                 //~0305I~
        for (int ii=0;ii<maxMember;ii++)                           //~0305I~
        {                                                          //~0305I~
		    if (Dump.Y) Dump.println("Members.searchByYourname MD["+ii+"]="+MD[ii].toString());//~0305I~
        	if (MD[ii].yourname==null || MD[ii].yourname.equals(""))//~0305I~
            {                                                      //~0305I~
            	if (ctrRobot==PctrRobot)                           //~0305I~
                {                                                  //~0305I~
	            	rc=ii;                                         //~0305I~
                    break;                                         //~0305I~
                }                                                  //~0305I~
                ctrRobot++;                                        //~0305I~
            }                                                      //~0305I~
        }                                                          //~0305I~
		if (Dump.Y) Dump.println("Members.searchRobot ctrRobot="+PctrRobot+",rc="+rc);//~0305I~
        return rc;                                                 //~0305I~
    }                                                              //~0305I~
    //*************************************************************//~v@@@I~
    //*rc=-1:not found                                             //~v@@@I~
    //*************************************************************//~v@@@I~
    public int searchByAddr(String Paddr)                          //~v@@@I~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members search by addr Paddr="+Utils.toString(Paddr));//~v@@@I~
    	if (Paddr==null)                                           //~v@@@I~
        	return -1;                                             //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
	    	if (Dump.Y) Dump.println("Members search by addr ii="+ii+",addr="+Utils.toString(MD[ii].addr));//~v@@@I~
        	if (MD[ii].addr!=null && Paddr.equals(MD[ii].addr))    //~v@@@I~
            {                                                      //~9B04I~
	    		if (Dump.Y) Dump.println("Members search by addr ii="+ii+",MD="+MD[ii].toString());//~9B04I~
            	return ii;                                         //~v@@@I~
            }                                                      //~9B04I~
        }                                                          //~v@@@I~
        return -1;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~0112I~
    //*for server                                                  //~0112I~
    //*************************************************************//~0112I~
    public int searchBySocket(Socket Psocket)                      //~0112I~
	{                                                              //~0112I~
    	if (Dump.Y) Dump.println("Members search by Socket socket="+Psocket.toString());//~0112I~
        for (int ii=0;ii<maxMember;ii++)                           //~0112I~
        {                                                          //~0112I~
	    	if (Dump.Y) Dump.println("Members search by addr ii="+ii+",socket="+Utils.toString(MD[ii].socketIP));//~0112R~
        	if (MD[ii].socketIP==Psocket)                          //~0112I~
            {                                                      //~0112I~
	    		if (Dump.Y) Dump.println("Members search by socket ii="+ii+",MD="+MD[ii].toString());//~0112I~
            	return ii;                                         //~0112I~
            }                                                      //~0112I~
        }                                                          //~0112I~
        return -1;                                                 //~0112I~
    }                                                              //~0112I~
    //*************************************************************//~v@@@I~
    //*rc=-1:not found                                             //~v@@@I~
    //*************************************************************//~v@@@I~
    public int search(Thread Pthread)                              //~v@@@I~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members search by thread");      //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
        	if (MD[ii].thread==Pthread)                            //~v@@@I~
            {                                                      //~v@@@I~
    			if (Dump.Y) Dump.println("Members search by thread found="+ii);//~v@@@I~
            	return ii;                                         //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        return -1;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public void resetConnectionAtClient()                           //~v@@@I~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.resetConnectionAtClient");//~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
	    	if (Dump.Y) Dump.println("Members.resetConnectionAtClient MD before="+MD[ii].toString());//~v@@@I~
//      	if ((MD[ii].status & (MS_LOCAL | MS_REMOTECLIENT))!=0) //~v@@@R~
        	if ((MD[ii].status & (MS_SERVER | MS_REMOTECLIENT))!=0)//~v@@@I~
            {                                                      //~v@@@I~
    			MD[ii].yourname="";                                //~v@@@I~
            }                                                      //~v@@@I~
	    	if (Dump.Y) Dump.println("Members.resetConnectionAtClient MD after="+MD[ii].toString());//~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~0112I~
    //*WD:wifi disabled,upaired                                    //~0112I~
    //*************************************************************//~0112I~
    public void resetAllConnection()                               //~0112I~
	{                                                              //~0112I~
    	if (Dump.Y) Dump.println("Members.resetAllConnection");    //~0112I~
        for (int ii=0;ii<maxMember;ii++)                           //~0112I~
        {                                                          //~0112I~
	    	if (Dump.Y) Dump.println("Members.resetAllConnection MD before="+MD[ii].toString());//~0112I~
        	MD[ii].thread=null;                                    //~0112R~
	    	if (Dump.Y) Dump.println("Members.resetAllConnection MD after="+MD[ii].toString());//~0112I~
        }                                                          //~0112I~
    }                                                              //~0112I~
    //*************************************************************//~v@@@I~
    //*rc=-1:not found                                             //~v@@@I~
    //*************************************************************//~v@@@I~
    private MemberData getMemberData(String Pname)                 //~v@@@I~
	{                                                              //~v@@@I~
    	if (Pname==null)                                           //~v@@@I~
        	return null;                                           //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        	if (Pname.equals(MD[ii].name))                         //~v@@@I~
            	return MD[ii];                                     //~v@@@I~
        return null;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*at Server chk all client synched By RuleSetting send/receive//~v@@@R~
    //*chk SS_OK                                                   //~v@@@R~
    //*************************************************************//~v@@@I~
    public boolean isRuleSynched(boolean PswMsg)                   //~v@@@R~
	{                                                              //~v@@@I~
    	boolean rc=true;                                           //~v@@@I~
        int ctr=0;                                                 //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
        	if (ii==idxLocal)                                      //~v@@@R~
            	continue;                                          //~v@@@I~
        	if (MD[ii].getThread()==null)                          //~v@@@I~
            	continue;                                          //~v@@@I~
            ctr++;                                                 //~v@@@I~
    		if (Dump.Y) Dump.println("Members.isRuleSynched ii="+ii+",ctr="+ctr+",name="+MD[ii].getName()+",statusRuleSync="+MD[ii].statusRuleSynch+",syncdate="+MD[ii].ruleSyncDate+",syncdateBTIO="+MD[ii].ruleSyncDateBTIO);//~v@@@R~
        	if (MD[ii].statusRuleSynch!=SS_OK)                     //~v@@@R~
            {                                                      //~v@@@I~
            	if (PswMsg)                                         //~v@@@I~
                {                                                  //~v@@@I~
//              	String name=MD[ii].getYourNameName();          //~v@@@R~
//              	MainView.drawMsg(Utils.getStr(R.string.Err_ResyncRuleRequired,name));//~v@@@R~
            		showResyncErr(MD[ii]);                         //~v@@@I~
                }                                                  //~v@@@I~
            	rc=false;                                          //~v@@@I~
            	break;                                             //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        if (ctr==0)                                                //~v@@@I~
        	rc=false;                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.isRuleSynched rc="+rc+",ctr="+ctr);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~0107I~
    public boolean isRuleSynched(String Pname,boolean PswMsg)      //~0107I~
	{                                                              //~0107I~
    	boolean rc=false;                                          //~0107I~
    	int idx=search(Pname);                                     //~0107I~
    	if (Dump.Y) Dump.println("Members.isRuleSynched name="+Pname+",swMsg="+PswMsg+",idx="+idx);//~0107I~
        if (idx!=-1)                                               //~0107I~
        {                                                          //~0107I~
	    	if (Dump.Y) Dump.println("Members.isRuleSynched MD="+toStringMD(idx));//~0107I~
			rc=MD[idx].isRuleSynched();                            //~0107I~
            if (!rc)                                               //~0107I~
	            if (PswMsg)                                        //~0107I~
    	        {                                                  //~0107I~
        	    	showResyncErr(MD[idx]);                        //~0107I~
            	}                                                  //~0107I~
        }                                                          //~0107I~
    	if (Dump.Y) Dump.println("Members.isRuleSynched rc="+rc);  //~0107I~
        return rc;                                                 //~0107I~
    }                                                              //~0107I~
    //*************************************************************//~0110I~
    public String chkReconnectingEnv()                             //~0110R~
	{                                                              //~0110I~
    	String rc=null;                                            //~0110R~
        for (int ii=0;ii<maxMember;ii++)                           //~0110I~
        {                                                          //~0110I~
	    	if (Dump.Y) Dump.println("Members.chkReconnectingEnv MD["+ii+"]="+toStringMD(ii));//~0110I~
        	if ((MD[ii].status & MS_RECONNECTERR)!=0)              //~0110I~
            {                                                      //~0110I~
            	rc=MD[ii].getYourNameName();	//yourname or devname//~0110R~
                break;                                             //~0110I~
            }                                                      //~0110I~
        }                                                          //~0110I~
    	if (Dump.Y) Dump.println("Members.chkReconnectingEnv rc="+rc);//~0110I~
        return rc;                                                 //~0110I~
    }                                                              //~0110I~
    //*************************************************************//~v@@@I~
    //*chk synch date at connection by @@name+syncDate exchange    //~v@@@I~
    //*************************************************************//~v@@@I~
    private boolean isRuleSynchedBTIO()                            //~9B25I~
	{                                                              //~9B25I~
	    return isRuleSynchedBTIO(-1/*Pidx*/);                      //~9B25I~
    }                                                              //~9B25I~
//  private boolean isRuleSynchedBTIO()                             //~v@@@R~//~9B25R~
    private boolean isRuleSynchedBTIO(int Pidx)                    //~9B25I~
	{                                                              //~v@@@I~
    	boolean rc=true;                                           //~v@@@R~
    	if (Dump.Y) Dump.println("Members.isRuleSynchedBTIO idx="+Pidx);//~9B25R~
//      int idxLocal=getLocal();                                   //~v@@@R~
        if (idxLocal<0)                                            //~v@@@R~
        	return false;                                          //~v@@@R~
        String localSyncDate=MD[idxLocal].getRuleSyncDateBTIO();   //~v@@@I~
        if (localSyncDate==null)                                   //~v@@@I~
        	return false;                                          //~v@@@I~
        int ctrConnection=0;                                       //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
            if (Dump.Y) Dump.println("Members.isRuleSynchedBTIO idxLocal="+idxLocal+",MD="+toStringMD(ii));//~9B25R~
        	if (ii==idxLocal)                                      //~v@@@I~
            	continue;                                          //~v@@@I~
		 	String sd=MD[ii].getRuleSyncDateBTIO();                //~v@@@I~
            if (sd==null)                                          //~v@@@I~
            	continue;                                          //~v@@@I~
            if (Dump.Y) Dump.println("Members.isRuleSynchedBTIO localSyncDate="+localSyncDate+","+MD[ii].getName()+"="+sd);//~v@@@R~
        	if (sd.equals(localSyncDate))                          //~v@@@R~
			    setRuleSyncStatus(ii,true/*PswOK*/,null/*PsyncDate:no need to update*/);//~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
			    setRuleSyncStatus(ii,false/*PswOK*/,null/*PsyncDate:no need to update*/);//~v@@@I~
              if (Pidx==-1||Pidx==ii)	//errmsg to only this member//~9B25I~
              {                                                    //~9B25I~
               if (Pidx==ii)	//errmsg to only this member       //~9B25I~
            	showResyncErr(MD[ii]);                             //~v@@@I~
            	rc=false;                                          //~v@@@R~
              }                                                    //~9B25I~
//              break;                                             //~v@@@I~//~9B25R~
            }                                                      //~v@@@I~
            ctrConnection++;                                       //~v@@@I~
        }                                                          //~v@@@I~
        if (ctrConnection==0)                                      //~v@@@I~
            rc=false;                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.isRuleSynchedBTIO ctrConnection="+ctrConnection+",rc="+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*re-evaluate synch status after saveProperties               //~v@@@I~
    //*************************************************************//~v@@@I~
    private boolean resetSyncStatus()                               //~v@@@I~//~9B25R~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.resetSyncStatus");       //~v@@@I~
	    boolean rc=isRuleSynchedBTIO();                            //~v@@@I~
    	if (Dump.Y) Dump.println("Members.resetSyncStatus rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*rc:sync ok                                                  //~v@@@I~
    //*************************************************************//~v@@@I~
    private boolean setSyncStatus(int Pidx)                        //~v@@@I~
	{                                                              //~v@@@I~
    	boolean rc=false;                                          //~v@@@I~
        MemberData md=MD[Pidx];                                    //~v@@@I~
        String sd=md.getRuleSyncDateBTIO();                        //~v@@@I~
        String localSyncDate=MD[idxLocal].getRuleSyncDateBTIO();   //~v@@@I~
    	if (Dump.Y) Dump.println("Members.setSyncStatus idx="+Pidx+",name="+MD[Pidx].name+",syncDate="+sd+",localSyncDate="+localSyncDate);//~v@@@I~
        boolean swOK=sd!=null && localSyncDate!=null && sd.equals(localSyncDate);//~v@@@I~
		setRuleSyncStatus(Pidx,swOK,null/*PsyncDate:no need to update*/);//~v@@@I~
    	if (Dump.Y) Dump.println("Members.setSyncStatus swOK="+swOK);//~v@@@I~
        return swOK;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    private void showResyncErr(MemberData Pmd)                     //~v@@@I~
    {                                                              //~v@@@I~
    	String name=Pmd.getYourNameName();                         //~v@@@I~
        String msg=Utils.getStr(R.string.Err_ResyncRuleRequired,name);//~v@@@I~
        MainView.drawMsg(msg);                                     //~v@@@R~
    }                                                              //~v@@@I~
//    //*************************************************************//~v@@@R~
//    private int getLocal()                                       //~v@@@R~
//    {                                                            //~v@@@R~
//        int rc=-1;                                               //~v@@@R~
//        for (int ii=0;ii<maxMember;ii++)                         //~v@@@R~
//        {                                                        //~v@@@R~
//            if ((MD[ii].status & MS_LOCAL)!=0)                   //~v@@@R~
//            {                                                    //~v@@@R~
//                rc=ii;                                           //~v@@@R~
//                break;                                           //~v@@@R~
//            }                                                    //~v@@@R~
//        }                                                        //~v@@@R~
//        if (Dump.Y) Dump.println("Members.getLocal rc="+rc);     //~v@@@R~
//        return rc;                                               //~v@@@R~
//    }                                                            //~v@@@R~
    //*************************************************************//~v@@@I~
    public String getLocalSyncDate()                               //~v@@@R~
    {                                                              //~v@@@I~
        if (idxLocal<0)                                            //~v@@@I~
        	return null;                                           //~v@@@I~
        String syncDate=MD[idxLocal].getRuleSyncDateBTIO();              //~v@@@I~
        if (Dump.Y) Dump.println("Members.getLocalSyncDate syncDate="+syncDate);//~v@@@I~
        return syncDate;                                           //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
//  public boolean setRuleOutOfSync()                              //~v@@@I~//~9B25R~
    public boolean setRuleOutOfSync(String PremoteDevicename)      //~9B25I~
	{                                                              //~v@@@I~
    	boolean rc=true;                                           //~v@@@I~
//      for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~//~9B25R~
//      	MD[ii].statusRuleSynch=SS_NONE;                              //~v@@@R~//~9B25R~
    	int idx=search(PremoteDevicename);                          //~9B25I~
        if (idx!=-1)                                               //~9B25I~
			MD[idx].statusRuleSynch=SS_NONE;                       //~9B25I~
    	if (Dump.Y) Dump.println("Members.setRuleOutOfSync remote="+PremoteDevicename+",MD="+toStringMD(idx));//~9B25I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public void setRuleSyncStatus(int Pidx,boolean PswOK,String PsyncDate)//~v@@@R~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.setRuleSyncStatus idx="+Pidx+",name="+Utils.toString(MD[Pidx].name)+",swOK="+PswOK);//~v@@@R~
        MD[Pidx].statusRuleSynch=PswOK ? SS_OK : SS_NG;            //~v@@@I~
        if (PsyncDate!=null)                                       //~v@@@I~
	        MD[Pidx].ruleSyncDate=PsyncDate;                       //~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************************//~0323I~
    public void setRuleSyncStatusReplay(int Pidx,boolean PswOK,String PsyncDate)//~0323I~
	{                                                              //~0323I~
    	if (Dump.Y) Dump.println("Members.setRuleSyncStatusReply idx="+Pidx+",name="+Utils.toString(MD[Pidx].name)+",swOK="+PswOK);//~0323I~
	    setRuleSyncStatus(Pidx,PswOK,PsyncDate);                   //~0323I~
        if (PsyncDate!=null)                                       //~0323I~
		    MD[Pidx].ruleSyncDateBTIO=PsyncDate;                   //~0323I~
    }                                                              //~0323I~
    //*************************************************************//~v@@@I~
    //*return list of yourname 0:OK,1:NG:2:Not Yet Responsed       //~v@@@R~
    //*************************************************************//~v@@@I~
    public String[][] getRuleSynchStatus()                         //~v@@@R~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.getRuleSynchStatus");    //~v@@@R~
        int ctrOK=0,ctrNG=0,ctrNoResp=0,ctrClient=0;               //~v@@@R~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
        	if ((MD[ii].status & MS_REMOTECLIENT)==0)              //~v@@@I~
            	continue;                                          //~v@@@I~
            ctrClient++;                                           //~v@@@I~
        	switch(MD[ii].statusRuleSynch)                         //~v@@@R~
            {                                                      //~v@@@I~
            case SS_OK:                                            //~v@@@I~
            	ctrOK++;                                           //~v@@@I~
            	break;                                             //~v@@@I~
            case SS_NG:                                            //~v@@@I~
            	ctrNG++;                                           //~v@@@I~
            	break;                                             //~v@@@I~
            default:                                               //~v@@@I~
            	ctrNoResp++;                                       //~v@@@R~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        String[][] sss=new String[4][];                            //~v@@@R~
        if (ctrOK>0)                                               //~v@@@I~
	        sss[0]=new String[ctrOK];                               //~v@@@I~
        else                                                       //~v@@@I~
	        sss[0]=null;                                           //~v@@@I~
        if (ctrNG>0)                                               //~v@@@I~
	        sss[1]=new String[ctrNG];                                //~v@@@I~
        else                                                       //~v@@@I~
	        sss[1]=null;                                           //~v@@@I~
        if (ctrNoResp>0)                                           //~v@@@I~
	        sss[2]=new String[ctrNoResp];                           //~v@@@I~
        else                                                       //~v@@@I~
	        sss[2]=null;                                           //~v@@@I~
        if (ctrClient>0)                                           //~v@@@I~
	        sss[3]=new String[ctrClient];                          //~v@@@I~
        else                                                       //~v@@@I~
	        sss[3]=null;                                           //~v@@@I~
        ctrOK=0; ctrNG=0; ctrNoResp=0; ctrClient=0;                //~v@@@R~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        {                                                          //~v@@@I~
        	if ((MD[ii].status & MS_REMOTECLIENT)==0)              //~v@@@I~
            	continue;                                          //~v@@@I~
            String yn=MD[ii].yourname;                             //~v@@@I~
            sss[3][ctrClient++]=yn;                                //~v@@@I~
        	switch(MD[ii].statusRuleSynch)                         //~v@@@I~
            {                                                      //~v@@@I~
            case SS_OK:                                            //~v@@@I~
            	sss[0][ctrOK++]=yn;                                //~v@@@I~
            	break;                                             //~v@@@I~
            case SS_NG:                                            //~v@@@I~
            	sss[1][ctrNG++]=yn;                               //~v@@@I~
            	break;                                             //~v@@@I~
            default:                                               //~v@@@I~
            	sss[2][ctrNoResp++]=yn;                            //~v@@@R~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("Members.getRuleSynchStatus ok="+ctrOK+",ng="+ctrNG+",noresp="+ctrNoResp+",ctrClient="+ctrClient);//~v@@@R~
        return sss;                                                //~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*return idxMember,rc=-1:overflow                             //~v@@@R~
    //*************************************************************//~v@@@I~
    public int update(String Pname,BluetoothSocket Psocket,int Pstatus)//~v@@@R~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.update with socket name="+Pname+",status="+Pstatus);//~v@@@R~
    	String addr=Psocket.getRemoteDevice().getAddress();        //~v@@@I~
    	int idx=search(Pname);                                     //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        {                                                          //~v@@@I~
        	MD[idx].socket=Psocket;                                //~v@@@I~
        	MD[idx].addr=addr;                                     //~v@@@I~
        	MD[idx].thread=null;                                   //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        	idx=add(Pname,addr,Psocket);                           //~v@@@R~
        if (idx>=0)                                                //~v@@@R~
        {                                                          //~v@@@I~
            MD[idx].status=Pstatus;                                //~v@@@R~
//            if ((Pstatus & MS_LOCAL)!=0)                         //~v@@@R~
//                idxLocal=idx;                                    //~v@@@R~
//            else                                                 //~v@@@R~
//            if ((Pstatus & MS_SERVER)!=0)                        //~v@@@R~
//                idxServer=idx;                                   //~v@@@R~
			setIndexSL(idx);                                       //~v@@@I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("Members.update idxLocal="+idxLocal+",idxServer="+idxServer);//~v@@@I~
        return idx;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*WD:devname,yourname,syncdate,ipaddr                            //~v@@@I~//~0112R~
    //*************************************************************//~v@@@I~
    public int update(String[] PnameR,Thread Pthread)              //~0108I~
	{                                                              //~0108I~
    	if (Dump.Y) Dump.println("Members.update nameR="+ Arrays.toString(PnameR)+",Pthread="+Utils.toString(Pthread));//~v@@@I~//~0A14R~
        String tempname=PnameR[3];   //ipaddr                      //~v@@@I~//~0112R~
    	int idx=search(tempname);                                      //~v@@@I~
        if (idx<0)                                                 //~0112I~
    		idx=searchByAddr(tempname);                            //~0112I~
        if (idx>=0)                                                //~v@@@I~
        {                                                          //~v@@@I~
        	MD[idx].name=PnameR[0];                                //~v@@@I~
        	MD[idx].addr=PnameR[3];                                //~v@@@I~
        	MD[idx].yourname=PnameR[1];                            //~v@@@I~
        	MD[idx].thread=Pthread;                                //~v@@@I~
            if (PnameR.length>4)                                   //~9B03I~
	        	MD[idx].macAddr=PnameR[4];                         //~9B03I~
            setRuleSyncDateBTIO(idx,PnameR[2]);                    //~v@@@I~
	    	if (Dump.Y) Dump.println("Members.update idx="+idx+",member="+MD[idx].toString());//~v@@@I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("Members.update idxLocal="+idxLocal+",idxServer="+idxServer);//~v@@@I~
        return idx;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~9B03I~
    //*at reconnect(keep member seq by macaddr at initial connection completed)//~9B03I~
    //*************************************************************//~9B03I~
    public int updateReconnect(String[] PnameR,Thread Pthread,int Pstatus)     //~9B03I~//~0108R~
	{                                                              //~9B03I~
    	if (Dump.Y) Dump.println("Members.update nameR="+ Arrays.toString(PnameR));//~9B03I~
	    String macAddr=PnameR[4];                                  //~9B03I~
    	int idx=searchByMacAddr(macAddr);                          //~9B03I~
        if (idx>=0)                                                //~9B03I~
        {                                                          //~9B03I~
        	MD[idx].name=PnameR[0];                                //~9B03I~
        	MD[idx].addr=PnameR[3];                                //~9B03I~
        	MD[idx].yourname=PnameR[1];                            //~9B03I~
        	MD[idx].thread=Pthread;                                //~9B03I~
            MD[idx].socketIP=((IPIOThread)Pthread).ioSocket;       //~9B03I~
            setRuleSyncDateBTIO(idx,PnameR[2]);                    //~9B03I~
            setIOErr(idx,false/*PswOn*/);                          //~9B03I~
	        MD[idx].status=Pstatus;		//remote client            //~0108I~
            MD[idx].connectionMode=connectionMode;                 //~0108M~
	    	if (Dump.Y) Dump.println("Members.update idx="+idx+",member="+MD[idx].toString());//~9B03I~
        }                                                          //~9B03I~
    	if (Dump.Y) Dump.println("Members.update idxLocal="+idxLocal+",idxServer="+idxServer);//~9B03I~
        return idx;                                                //~9B03I~
    }                                                              //~9B03I~
    //*************************************************************//~va46I~
    //*at reconnect(keep member seq by macaddr at initial connection completed)//~va46I~
    //*************************************************************//~va46I~
    public int updateReconnect(String PmacAddr,String Pname,String Pyourname,String PsyncDate)//~va46I~
	{                                                              //~va46I~
    	if (Dump.Y) Dump.println("Members.updateReconnect mac="+PmacAddr+",name="+Pname+",yn="+Pyourname+",syncDate="+PsyncDate);//~va46R~
    	int idx=searchByMacAddr(PmacAddr);                         //~va46I~
        if (idx>=0)                                                //~va46I~
        {                                                          //~va46I~
        	MD[idx].name=Pname;                                    //~va46I~
        	MD[idx].yourname=Pyourname;                            //~va46I~
            setRuleSyncDateBTIO(idx,PsyncDate);                    //~va46I~
	    	if (Dump.Y) Dump.println("Members.updateReconnect idx="+idx+",member="+MD[idx].toString());//~va46R~
        }                                                          //~va46I~
    	if (Dump.Y) Dump.println("Members.updateReconnect rc="+idx);//~va46I~
        return idx;                                                //~va46I~
    }                                                              //~va46I~
    //*************************************************************//~0110I~
    public int setReconnectErr(String Pname,boolean PswOn)         //~0110I~
	{                                                              //~0110I~
    	if (Dump.Y) Dump.println("Members.setReconnectErr name="+Pname);//~0110I~
    	int idx=search(Pname);                                     //~0110I~
        if (idx>=0)                                                //~0110I~
        {                                                          //~0110I~
        	if (PswOn)                                             //~0110I~
	        	MD[idx].status|=MS_RECONNECTERR;                  //~0110I~
            else                                                   //~0110I~
	        	MD[idx].status&=~MS_RECONNECTERR;                 //~0110I~
        }                                                          //~0110I~
        return idx;                                                //~0110I~
    }                                                              //~0110I~
    //*************************************************************//~0116I~
    public int setReconnectErr(String Pmacaddr)                    //~0116I~
	{                                                              //~0116I~
    	if (Dump.Y) Dump.println("Members.setReconnectErr macAddr="+Pmacaddr);//~0116I~
	    int idx=searchByMacAddr(Pmacaddr);                         //~0116I~
        if (idx>=0)                                                //~0116I~
        {                                                          //~0116I~
	        MD[idx].status|=MS_RECONNECTERR;                       //~0116I~
	    	if (Dump.Y) Dump.println("Members.setReconnectErr by macAddr MD["+idx+"]="+MD[idx].toString());//~0116I~
        }                                                          //~0116I~
        return idx;                                                //~0116I~
    }                                                              //~0116I~
    //*************************************************************//~0116I~
    public boolean isConnected(int Pidx)                           //~0116I~
	{                                                              //~0116I~
    	boolean rc=false;                                          //~0116I~
    	if (Pidx>=0 && Pidx<maxMember)                             //~0116I~
    		rc=MD[Pidx].getThread()!=null && (MD[Pidx].status & MS_RECONNECTERR)==0;//~0116R~
    	if (Dump.Y) Dump.println("Members.isConnected rc="+rc+",MD["+Pidx+"]="+MD[Pidx].toString());//~0116I~
        return rc;                                                 //~0116I~
    }                                                              //~0116I~
    //*************************************************************//~v@@@I~
    //*return idxMember,rc=-1:overflow                             //~v@@@R~
    //*************************************************************//~v@@@I~
    public int updateAdd(String Pname,Socket Psocket,int Pstatus)  //~v@@@R~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.updateAdd with socket name="+Pname+",status="+Pstatus);//~v@@@R~
    	String addr=Utils.getRemoteIPAddr(Psocket,null/*nullopt*/);//~v@@@I~
    	int idx=search(Pname);                                     //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        {                                                          //~v@@@I~
        	MD[idx].socketIP=Psocket;                              //~v@@@I~
        	MD[idx].addr=addr;                                     //~v@@@I~
        	MD[idx].thread=null;                                   //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        	idx=add(Pname,addr,Psocket);                           //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        {                                                          //~v@@@I~
            MD[idx].status=Pstatus;                                //~v@@@I~
			setIndexSL(idx);                                       //~v@@@I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("Members.updateAdd with socket idx="+idx+",idxLocal="+idxLocal+",idxServer="+idxServer);//~v@@@R~
        return idx;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~0112I~
    //*return idxMember,rc=-1:overflow                             //~0112I~
    //*************************************************************//~0112I~
    public int updateAddWDByIPAddr(String Pname,Socket Psocket,int Pstatus)//~0112I~
	{                                                              //~0112I~
    	String addr=Utils.getRemoteIPAddr(Psocket,null/*nullopt*/);//~0112I~
    	if (Dump.Y) Dump.println("Members.updateAddWDByIPAddr socket name="+Pname+",addr="+addr+",status="+Pstatus);//~0112I~
    	int idx=searchByAddr(addr);                                //~0112I~
        if (idx>=0)                                                //~0112I~
        {                                                          //~0112I~
        	MD[idx].socketIP=Psocket;                              //~0112I~
        	MD[idx].addr=addr;                                     //~0112I~
        	MD[idx].thread=null;                                   //~0112I~
        }                                                          //~0112I~
        else                                                       //~0112I~
        	idx=add(Pname,addr,Psocket);                           //~0112I~
        if (idx>=0)                                                //~0112I~
        {                                                          //~0112I~
            MD[idx].status=Pstatus;                                //~0112M~
	    	if (Dump.Y) Dump.println("Members.updateAddWDByIPAddr found out MD["+idx+"]="+MD[idx].toString());//~0112M~
			setIndexSL(idx);                                       //~0112I~
        }                                                          //~0112I~
    	if (Dump.Y) Dump.println("Members.updateAdd with socket idx="+idx+",idxLocal="+idxLocal+",idxServer="+idxServer);//~0112I~
        return idx;                                                //~0112I~
    }                                                              //~0112I~
    //*************************************************************//~0112I~
    //*return idxMember,rc=-1:overflow                             //~0112I~
    //*************************************************************//~0112I~
    public int updateWDBySocket(Socket Psocket,Thread Pthread)     //~0112I~
	{                                                              //~0112I~
    	if (Dump.Y) Dump.println("Members.updateWDBySocket socket="+Psocket.toString()+",thread="+Pthread.toString());//~0112I~
    	int idx=searchBySocket(Psocket);                          //~0112I~
        if (idx>=0)                                                //~0112I~
        {                                                          //~0112I~
        	MD[idx].thread=Pthread;                                //~0112I~
	    	if (Dump.Y) Dump.println("Members.updateWDBySocket found out MD["+idx+"]="+MD[idx].toString());//~0112I~
        }                                                          //~0112I~
        return idx;                                                //~0112I~
    }                                                              //~0112I~
    //*************************************************************//~v@@@I~//~9B03R~
    //*return idxMember,rc=-1:overflow                             //~v@@@I~//~9B03R~
    //*************************************************************//~v@@@I~//~9B03R~
    public int updateAddReconnectWD(String Pname/*ipAddr*/,Socket Psocket,int Pstatus)//~v@@@I~//~9B03R~
    {                                                              //~v@@@I~//~9B03R~
        String addr=Utils.getRemoteIPAddr(Psocket,null/*nullopt*/);//~v@@@I~//~9B03R~
        if (Dump.Y) Dump.println("Members.updateAddReconnectWD with socket name="+Pname+",status="+Pstatus+",addr="+addr);//~v@@@I~//~9B03R~
        int idx=searchReconnectWD(Pname);                          //~v@@@I~//~9B03R~
        if (idx>=0)                                                //~v@@@I~//~9B03R~
        {                                                          //~v@@@I~//~9B03R~
            MD[idx].socketIP=Psocket;                              //~v@@@I~//~9B03R~
            MD[idx].addr=addr;                                     //~v@@@I~//~9B03R~
            if (Pstatus!=MS_SERVER) //remoteServer, server devname is remained,key of updateMembe(Thread) at openClient is remained remote(server) devicename//~9B05R~
	            MD[idx].name=addr;       //for update name by key:addr //~9B04I~//~9B05R~
            MD[idx].thread=null;                                   //~v@@@I~//~9B03R~
//          setIOErr(idx,false/*PswOn*/); //keep err for multiple ioerr device//~9B04R~
            MD[idx].status=Pstatus;                                //~v@@@I~//~9B03I~
        }                                                          //~v@@@I~//~9B03R~
        if (Dump.Y) Dump.println("Members.updateAdd with socket idx="+idx+",idxLocal="+idxLocal+",idxServer="+idxServer);//~v@@@I~//~9B03R~
        return idx;                                                //~v@@@I~//~9B03R~
    }                                                              //~v@@@I~//~9B03R~
    //*************************************************************//~v@@@I~
    private void setIndexSL(int Pidx)                              //~v@@@I~
    {                                                              //~v@@@I~
    	int stat=MD[Pidx].status;                                         //~v@@@I~
        if ((stat & MS_LOCAL)!=0)                                  //~v@@@I~
            idxLocal=Pidx;                                          //~v@@@I~
//      else                                                       //~v@@@R~
        if ((stat & MS_SERVER)!=0)                                 //~v@@@I~
            idxServer=Pidx;                                         //~v@@@I~
    	if (Dump.Y) Dump.println("Members.setIndexSL Pidx="+Pidx+",stat="+stat+",idxLocal="+idxLocal+",idxServer="+idxServer);//~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*rc=-1:overflow                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public int update(String Pname,Thread Pthread)                 //~v@@@I~
	{                                                              //~v@@@I~
    	int idx=search(Pname);                                     //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        {                                                          //~v@@@I~
        	MD[idx].thread=Pthread;                                //~v@@@I~
            if (Pthread==null)      //disconnected                 //~v@@@I~
            {                                                      //~9B11I~
		    	if (Dump.Y) Dump.println("Members.update set thread=null");//~9B11I~
            	MD[idx].yourname="";                               //~v@@@I~
	        	MD[idx].status=0;                                  //~9B11I~
            }                                                      //~9B11I~
            else                                                   //~v@@@I~
            {                                                      //~9B11I~
		    	if (Dump.Y) Dump.println("Members.update set thread=!null");//~9B11I~
				setIOErr(idx,false/*PswOn*/);                       //~v@@@I~
            }                                                      //~9B11I~
	    	if (Dump.Y) Dump.println("Members.update thread name="+Pname+",MD["+idx+"]="+MD[idx].toString());//~9B11R~
        }                                                          //~v@@@I~
        return idx;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*rc=-1:not found                                             //~v@@@I~
    //*************************************************************//~v@@@I~
    public int update(String Pname,int Pstatus)                    //~v@@@I~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.update without yourname/socket  name="+Pname+",status="+Pstatus);//~v@@@I~
       	int idx=search(Pname);                                     //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        {                                                          //~v@@@I~
        	MD[idx].status=Pstatus;                                //~v@@@I~
//            if ((Pstatus & MS_LOCAL)!=0)                         //~v@@@R~
//                idxLocal=idx;                                    //~v@@@R~
//            else                                                 //~v@@@R~
//            if ((Pstatus & MS_SERVER)!=0)                        //~v@@@R~
//                idxServer=idx;                                   //~v@@@R~
			setIndexSL(idx);                                       //~v@@@I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("Members.update idxLocal="+idxLocal+",idxServer="+idxServer);//~v@@@I~
        return idx;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public void setIOErr(int Pidx,boolean PswOn)                   //~v@@@I~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Members.setIOErr idx="+Pidx+",PswOn="+PswOn);//~v@@@I~
    	if (Pidx>=0 && Pidx<maxMember)                             //~v@@@R~
        {                                                          //~v@@@I~
        	if (PswOn)                                             //~v@@@I~
	        	MD[Pidx].status|=MS_IOERR;	                       //~v@@@I~
            else                                                   //~v@@@I~
	        	MD[Pidx].status&=~MS_IOERR;                         //~v@@@I~
            if (Dump.Y) Dump.println("Members.setIOErr name="+MD[Pidx].name+",status="+Integer.toHexString(MD[Pidx].status));//~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public boolean isIOErr(int Pidx)                               //~v@@@I~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Members.isIOErr idx="+Pidx);     //~v@@@I~
        boolean rc=false;                                          //~v@@@I~
    	if (Pidx>=0 && Pidx<maxMember)                             //~v@@@R~
        {                                                          //~v@@@I~
	        rc=(MD[Pidx].status & MS_IOERR)!=0;                     //~v@@@I~
            if (Dump.Y) Dump.println("Members.isIOErr rc="+rc+",name="+MD[Pidx].name+",status="+Integer.toHexString(MD[Pidx].status));//~v@@@I~
        }                                                          //~v@@@I~
        return rc;
	}                                                              //~v@@@I~
//    //*************************************************************//~v@@@R~
//    public void savePendingMsg(int Pidx,String Pmsg)             //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Members.savePendingMsg idx="+Pidx+",msg="+Pmsg);//~v@@@R~
////      MD[Pidx].pendingMsg=Pmsg;                                //~v@@@R~
//        MD[Pidx].pendingMsg.add(Pmsg);                           //~v@@@R~
//        if (Dump.Y) Dump.println("Members.savePendingMsg idx="+Pidx+",msg="+Pmsg+",size="+MD[Pidx].pendingMsg.size());//~v@@@R~
//    }                                                            //~v@@@R~
//    //*************************************************************//~v@@@R~
//    public String peekPendingMsg(int Pidx)                       //~v@@@R~
//    {                                                            //~v@@@R~
////      String msg=MD[Pidx].pendingMsg;                          //~v@@@R~
//        String msg=null;                                         //~v@@@R~
//        if (!MD[Pidx].pendingMsg.isEmpty())                      //~v@@@R~
//            msg=MD[Pidx].pendingMsg.getFirst();                  //~v@@@R~
//        if (Dump.Y) Dump.println("Members.peekPendingMsg idx="+Pidx+",msg="+msg);//~v@@@R~
//        return msg;                                              //~v@@@R~
//    }                                                            //~v@@@R~
//    //*************************************************************//~v@@@R~
//    public String getPendingMsg(int Pidx)                        //~v@@@R~
//    {                                                            //~v@@@R~
////      String msg=MD[Pidx].pendingMsg;                          //~v@@@R~
////      MD[Pidx].pendingMsg=null;                                //~v@@@R~
//        String msg=null;                                         //~v@@@R~
//        if (!MD[Pidx].pendingMsg.isEmpty())                      //~v@@@R~
//            msg=MD[Pidx].pendingMsg.removeFirst();               //~v@@@R~
//        if (Dump.Y) Dump.println("Members.getPendingMsg idx="+Pidx+",msg="+msg);//~v@@@R~
//        return msg;                                              //~v@@@R~
//    }                                                            //~v@@@R~
//    //*************************************************************//~v@@@R~
//    public void clearPendingMsg()                                //~v@@@R~
//    {                                                            //~v@@@R~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~v@@@R~
//        {                                                        //~v@@@R~
//            if (Dump.Y) Dump.println("Members.clearPendingMsg idx="+ii+",size="+MD[ii].pendingMsg.size());//~v@@@R~
//            MD[ii].pendingMsg.clear();                           //~v@@@R~
//        }                                                        //~v@@@R~
//    }                                                            //~v@@@R~
    //*************************************************************//~v@@@I~
    //*rc=-1:not found                                             //~v@@@I~
    //*************************************************************//~v@@@I~
//  public int update(String Pname,String Pyourname,int Pstatus)   //~v@@@R~
    public int update(String Pname,String Pyourname,int Pstatus,String PsyncDate)//~v@@@I~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.update with yourname name="+Pname+",Pyourname="+Pyourname+",status="+Pstatus+",syncDate="+(PsyncDate==null?"null":PsyncDate));//~v@@@M~
       	int idx=search(Pname);                                     //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        {                                                          //~v@@@I~
        	MD[idx].yourname=Pyourname;                            //~v@@@I~
        	MD[idx].status=Pstatus;                                //~v@@@I~
//            if ((Pstatus & MS_LOCAL)!=0)                         //~v@@@R~
//                idxLocal=idx;                                    //~v@@@R~
//            else                                                 //~v@@@R~
//            if ((Pstatus & MS_SERVER)!=0)                        //~v@@@R~
//                idxServer=idx;                                   //~v@@@R~
			setIndexSL(idx);                                       //~v@@@I~
//          if (PsyncDate!=null)                                   //~v@@@R~
//          	MD[idx].setRuleSyncDateBTIO(PsyncDate);            //~v@@@R~
            setRuleSyncDateBTIO(idx,PsyncDate);                    //~v@@@I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("Members.update idxLocal="+idxLocal+",idxServer="+idxServer);//~v@@@I~
        return idx;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~va66I~
    public void setTrainingMode(String PlocalDeviceName)           //~va66R~
	{                                                              //~va66I~
    	if (Dump.Y) Dump.println("Members.setTrainingMode");        //~va66I~
        MD[0].name=PlocalDeviceName;                               //~va66R~
        MD[0].yourname=(AG.YourName==null || AG.YourName.equals("")) ? YOURNAME_TRAINING :AG.YourName;//~va66R~
        MD[0].status=MS_SERVER|Members.MS_LOCAL|Members.MS_PLAYALONE;//~va66R~
		setIndexSL(0);	//idxServer and idxLocal                   //~va66I~
        setRuleSyncDateBTIO(0/*idx*/,AG.ruleSyncDate);             //~va66R~
        for (int ii=1;ii<maxMember;ii++)                           //~va9iI~
            MD[ii].status=0;    //to set type=AT_DUMMY ay Accounts.setMember//~va9iI~
    	if (Dump.Y) Dump.println("Members.setTrainingMode exit");  //~va66I~
    }                                                              //~va66I~
    //*************************************************************//~v@@@I~
    public int update(String Pname,String PsyncDate)               //~v@@@I~
	{                                                              //~v@@@I~
       	int idx=search(Pname);                                     //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        {                                                          //~v@@@I~
//          if (PsyncDate!=null)                                   //~v@@@R~
//          	MD[idx].setRuleSyncDateBTIO(PsyncDate);            //~v@@@R~
            setRuleSyncDateBTIO(idx,PsyncDate);                    //~v@@@I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("Members.update syncDate idx="+idx+",name="+Pname+",syncDate="+(PsyncDate==null?"null":PsyncDate));//~v@@@R~//~0107R~
        return idx;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public boolean updateServer(String PsyncDate)                  //~v@@@R~
	{                                                              //~v@@@I~
        if (idxServer<0)                                           //~v@@@I~
        	return false;                                          //~v@@@I~
    	setRuleSyncDateBTIO(idxServer,PsyncDate);                  //~v@@@I~
    	if (Dump.Y) Dump.println("Members.updateServer idx="+idxServer+",syncDate="+Utils.toString(PsyncDate));//~v@@@I~
        return true;
	}                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*rc:true:changed                                             //~v@@@I~
    //*************************************************************//~v@@@I~
    public boolean updateLocal(String PsyncDate)                   //~v@@@I~
	{                                                              //~v@@@I~
        if (idxLocal<0)                                            //~v@@@I~
        	return false;                                          //~v@@@I~
        MemberData md=MD[idxLocal];                                //~v@@@I~
        String old=md.getRuleSyncDateBTIO();                       //~v@@@R~
    	if (Dump.Y) Dump.println("Members.updateLocal idx="+idxLocal+",name="+md.getName()+",old="+Utils.toString(old)+",syncDate="+Utils.toString(PsyncDate));//~v@@@R~
        boolean rc=old==null || !old.equals(PsyncDate);             //~v@@@I~
	    md.setRuleSyncDateBTIO(PsyncDate);                         //~v@@@R~
	    resetSyncStatus();                                         //~v@@@I~
//      if (md.isServer())                                         //~v@@@R~
		if (BTMulti.isServerDevice())                              //~v@@@I~
        {                                                          //~v@@@I~
//  		resetSyncDate(true/*PswRemoteOnly*/);                  //~v@@@R~
          if (!AG.swTrainingMode)                                  //~va66I~
            AG.aMainView.drawMsg(R.string.Info_ResyncRuleServerChanged);//~v@@@I~
        }                                                          //~v@@@I~
        return rc;                                                 //~v@@@R~
	}                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public boolean updateRemote(int Pidx,String PsyncDate)         //~v@@@I~
	{                                                              //~v@@@I~
        String old=MD[Pidx].getRuleSyncDateBTIO();                 //~v@@@I~
    	if (Dump.Y) Dump.println("Members.updateLocal idx="+Pidx+",old="+Utils.toString(old)+",syncDate="+Utils.toString(PsyncDate));//~v@@@I~
        boolean rc=old==null || !old.equals(PsyncDate);             //~v@@@I~
	    MD[Pidx].setRuleSyncDateBTIO(PsyncDate);                   //~v@@@I~
        setSyncStatus(Pidx);                                       //~v@@@I~
        return rc;                                                 //~v@@@I~
	}                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    private int setRuleSyncDateBTIO(int Pidx,String PsyncDate)     //~v@@@I~
	{                                                              //~v@@@I~
	    return setRuleSyncDateBTIO(Pidx,PsyncDate,true/*PswChk*/);//~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    private int setRuleSyncDateBTIO(int Pidx,String PsyncDate,boolean PswChk)//~v@@@R~
	{                                                              //~v@@@I~
    	int rc=0;                                                  //~v@@@I~
        if (PsyncDate!=null)                                       //~v@@@I~
        {                                                          //~v@@@I~
			MD[Pidx].setRuleSyncDateBTIO(PsyncDate);               //~v@@@I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("Members.setRuleSyncDateBTIO swChk="+PswChk+",rc="+rc+",idx="+Pidx+",syncDate="+(PsyncDate==null?"null":PsyncDate));//~v@@@I~//~0119R~
        if (PswChk)                                                //~v@@@I~
        {                                                          //~v@@@I~
//      	boolean swSyncBTIO=isRuleSynchedBTIO();                //~v@@@R~//~9B25R~
        	boolean swSyncBTIO=isRuleSynchedBTIO(Pidx/*errmsg for this one*/);//~9B25I~
    		AG.aBTMulti.setRuleSettingSynchedAll(swSyncBTIO,PsyncDate);//~v@@@R~
        }                                                          //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*add if not found else update                                //~v@@@R~
    //*************************************************************//~v@@@I~
    public int updateAdd(String Pname,String Pyourname,int Pstatus)//~v@@@R~
	{                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.updateAdd with yourname name="+Pname+",yourname="+Pyourname+",status="+Pstatus+",connectionMode="+connectionMode);//~v@@@M~//~0118R~
       	int idx=search(Pname);                                     //~v@@@I~
        if (idx<0)                                                 //~v@@@I~
        {                                                          //~0118I~
            if (connectionMode==CM_BT)                             //~0118I~
	    		idx=add(Pname,null/*addr*/,(BluetoothSocket)null/*socket*/);//init connectionMode=CM_WD//~v@@@R~//~0118R~
            else                                                   //~0118I~
            if (connectionMode==CM_WD)                             //~0118I~
	    		idx=add(Pname,null/*addr*/,(Socket)null/*socket*/); //init connectionMode=CM_WD//~0118I~
        }                                                          //~0118I~
        idx=update(Pname,Pyourname,Pstatus,null/*syncDate*/);      //~v@@@R~
        return idx;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*sort(exchange local and server(move server to MD[0])        //~v@@@I~
    //*************************************************************//~v@@@I~
    public boolean updateSeq(String Pname,int Pidx)                //~v@@@I~
	{                                                              //~v@@@I~
       	int idx=search(Pname);                                     //~v@@@I~
        if (idx<0)                                                 //~v@@@I~
        	return false;                                          //~v@@@I~
		if (idx==Pidx)                                             //~v@@@I~
        	return false;                                          //~v@@@R~
    	if (Dump.Y) Dump.println("Members.updateSeq exchange idx="+idx+",name="+MD[idx].name);//~v@@@R~
    	if (Dump.Y) Dump.println("Members.updateSeq exchange Pidx="+Pidx+",name="+MD[Pidx].name);//~v@@@R~
        MemberData mdsave=MD[Pidx];   //excahange MD[Pidx] & MD[idx]//~v@@@I~
        MD[Pidx]=MD[idx];                                          //~v@@@I~
        MD[idx]=mdsave;                                            //~v@@@I~
		setIndexSL(Pidx);                                          //~v@@@I~
		setIndexSL(idx);                                           //~v@@@I~
    	if (Dump.Y) Dump.println("Members.updateSeq exchanged idxMember="+idx+",name="+MD[idx].name);//~v@@@R~//~0221R~
    	if (Dump.Y) Dump.println("Members.updateSeq exchanged idxMember="+Pidx+",name="+MD[Pidx].name);//~v@@@R~//~0221R~
        return true;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*rc=-1:overflow                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    private int add(String Pname,String Paddr,BluetoothSocket Psocket)          //~v@@@I~
	{                                                              //~v@@@I~
    //******************************                               //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
//      	if (MD[ii].name.equals(dummyName))                     //~v@@@R~//~va66R~
        	if (MD[ii].name.equals(dummyName) || (MD[ii].status & MS_PLAYALONE)!=0)//~va66I~
            {                                                      //~v@@@I~
            	MD[ii]=new MemberData(Pname,Paddr,Psocket);        //~v@@@R~
    			if (Dump.Y) Dump.println("Members.add idx="+ii+",name="+Pname);//~v@@@I~
                return ii;                                         //~v@@@I~
            }                                                      //~v@@@I~
        return -1;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    private int add(String Pname,String Paddr,Socket Psocket)      //~v@@@I~
	{                                                              //~v@@@I~
    //******************************                               //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        	if (MD[ii].name.equals(dummyName))                     //~v@@@I~
            {                                                      //~v@@@I~
            	MD[ii]=new MemberData(Pname,Paddr,Psocket);        //~v@@@I~
    			if (Dump.Y) Dump.println("Members.add idx="+ii+",name="+Pname);//~v@@@I~
                return ii;                                         //~v@@@I~
            }                                                      //~v@@@I~
        return -1;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~1AbUR~//~v@@@R~
    //*rc=-1:not found                                             //~v@@@I~
    //*************************************************************//~v@@@I~
    public int remove(String Pname)                           //~1AbUR~//~v@@@R~
    {                                                          //~1AbUR~//~v@@@R~
    	int idx=search(Pname);                                     //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        	remove(idx);                                           //~v@@@I~
        return idx;                                                //~v@@@R~
    }                                                          //~1AbUR~//~v@@@R~
    //*************************************************************//~v@@@I~
    public boolean setupEnd(String Pname)                          //~v@@@I~
    {                                                              //~v@@@I~
    	boolean rc=false;                                          //~v@@@I~
    	if (Dump.Y) Dump.println("Members.setupEnd name="+Pname);  //~v@@@I~
    	int idx=search(Pname);                                     //~v@@@I~
        if (idx>=0)                                                //~v@@@I~
        {                                                          //~v@@@I~
	    	MD[idx].setupEnd();                                    //~v@@@I~
        	rc=true;                                               //~v@@@I~
        }                                                          //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public boolean setupEnd(int Pidx)                              //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("Members.setupEnd idx="+Pidx);    //~v@@@I~
    	boolean rc=false;                                          //~v@@@I~
        if (Pidx>=0 && Pidx<MD.length)                             //~v@@@I~
        {                                                          //~v@@@I~
	    	if (Dump.Y) Dump.println("Members.setupEnd MD="+MD[Pidx].toString());//~9B19I~
	    	MD[Pidx].setupEnd();                                    //~v@@@I~
        	rc=true;                                               //~v@@@I~
        }                                                          //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*rc=-1:not found                                             //~v@@@I~
    //*************************************************************//~v@@@I~
    public void remove(int Pidx)                                  //~v@@@I~//~0119R~
    {                                                              //~v@@@I~
//  	MD[Pidx]=new MemberData(dummyName,null/*addr*/,null/*socket*/);//~v@@@R~
    	MD[Pidx]=new MemberData(dummyName);                        //~v@@@I~
    	if (Dump.Y) Dump.println("Members.remove MD["+Pidx+"]="+MD[Pidx].toString());//~9B07I~
    }                                                              //~v@@@R~
    //*************************************************************//~v@@@I~
    //*rc=-1:not found                                             //~v@@@I~
    //*************************************************************//~v@@@I~
    public String getYourNameServer()                              //~v@@@I~
    {                                                              //~v@@@I~
    	String rc="";                                              //~v@@@I~
    	for (MemberData md:MD)                                     //~v@@@I~
        {                                                          //~v@@@I~
        	if (md.isServer())                                     //~v@@@I~
            {                                                      //~v@@@I~
            	rc=md.getYourName();                                //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
    	if (Dump.Y) Dump.println("Members.getYourNameServer rc="+rc);//~v@@@I~
    	return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    public String toString()                                       //~v@@@I~
	{                                                              //~v@@@I~
    	StringBuffer sb=new StringBuffer();                        //~v@@@I~
        for (int ii=0;ii<maxMember;ii++)                           //~v@@@I~
        	sb.append("\n"+"MD["+ii+"]="+MD[ii].toString());        //~v@@@I~//~va66R~
        return sb.toString();                                      //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~9B25I~
    public String toStringMD(int Pidx)                             //~9B25I~
	{                                                              //~9B25I~
    	String s;                                                  //~9B25R~
    	if (Pidx>=0 && Pidx<PLAYERS)                               //~9B25I~
    		s="MD["+Pidx+"]="+MD[Pidx].toString();                 //~9B25R~
        else                                                       //~9B25I~
    		s="MD["+Pidx+"]=NotFound";                      //~9B25I~
        return s;                                                  //~9B25I~
    }                                                              //~9B25I~
//****************************************************************************************//~v@@@I~
//****************************************************************************************//~v@@@I~
//****************************************************************************************//~v@@@I~
    public class MemberData                                        //~v@@@R~
    {                                                              //~1AbTI~//~v@@@I~
	    private String name,addr,yourname;                         //~v@@@R~
	    private String macAddr;	//for WifiDirect                   //~9B03R~
	    private BluetoothSocket socket;                            //~v@@@R~
	    private Socket socketIP;                                   //~v@@@I~
        public int status;                                         //~v@@@R~
        private Thread thread;                                     //~v@@@I~
        public Accounts.Account account;                           //~v@@@I~
        public int statusRuleSynch;                                //~v@@@R~
        public String ruleSyncDate;		//SyncDAte of SS_OK        //~v@@@R~
        private String ruleSyncDateBTIO;                            //~v@@@R~//~0124R~
        public Robot robot;                                        //~v@@@I~
        public int connectionMode=CM_NONE;                         //~0108R~
//      private String pendingMsg;                                 //~v@@@R~
//      private LinkedList<String> pendingMsg=new LinkedList<String>();//~v@@@R~
        //*******************************************              //~v@@@I~
        MemberData(String Pname)                                   //~v@@@I~
        {                                                          //~v@@@I~
    		if (Dump.Y) Dump.println("MemberData.constructor name="+Pname);//~0118I~
        	name=Pname;                                            //~v@@@I~
        }                                                          //~v@@@I~
        //*******************************************              //~v@@@I~
        MemberData(String Pname,String Paddr,BluetoothSocket Psocket)//~v@@@I~
        {                                                          //~v@@@I~
        	this(Pname,Paddr,Psocket,0);                           //~v@@@R~
        }                                                          //~v@@@I~
        MemberData(String Pname,String Paddr,Socket Psocket)       //~v@@@I~
        {                                                          //~v@@@I~
        	this(Pname,Paddr,Psocket,0);                           //~v@@@I~
        }                                                          //~v@@@I~
        MemberData(String Pname,String Paddr,BluetoothSocket Psocket,int Pstatus)//~v@@@I~
        {                                                          //~v@@@I~
        	name=Pname; addr=Paddr; socket=Psocket; status=Pstatus;//~v@@@R~
            connectionMode=CM_BT;                                  //~0117I~
//          thread=null;                                           //~v@@@R~
//          yourname=null;                                         //~v@@@R~
        }                                                          //~v@@@I~
        MemberData(String Pname,String Paddr,Socket Psocket,int Pstatus)//~v@@@I~
        {                                                          //~v@@@I~
        	name=Pname; addr=Paddr; socketIP=Psocket; status=Pstatus;//~v@@@I~
            connectionMode=CM_WD;                                  //~0117I~
        }                                                          //~v@@@I~
        public String getName()                                    //~v@@@R~
        {                                                          //~v@@@I~
        	return name;                                           //~v@@@R~
        }                                                          //~v@@@I~
        public String getYourNameName()                            //~v@@@I~
        {                                                          //~v@@@I~
        	if (yourname!=null)                                    //~v@@@I~
            	return yourname;                                   //~v@@@I~
        	return name;                                           //~v@@@I~
        }                                                          //~v@@@I~
        public String getYourName()                                //~v@@@I~
        {                                                          //~v@@@I~
        	return yourname;                                       //~v@@@I~
        }                                                          //~v@@@I~
        public Thread getThread()                                  //~v@@@I~
        {                                                          //~v@@@I~
        	return thread;                                         //~v@@@I~
        }                                                          //~v@@@I~
        public void setupEnd()                                     //~v@@@I~
        {                                                          //~v@@@I~
    		if (Dump.Y) Dump.println("MemberData.setupEnd name="+name);       //~v@@@I~//~9B19R~
        	status|=MS_SETUPEND;                                   //~v@@@I~
        }                                                          //~v@@@I~
        public void setRuleSyncDateBTIO(String PsyncDate)          //~v@@@I~
        {                                                          //~v@@@I~
    		if (Dump.Y) Dump.println("MemberData.setRuleSyncDateBTIO name="+name+",syncDate="+Utils.toString(PsyncDate));//~v@@@R~
        	ruleSyncDateBTIO=PsyncDate;                            //~v@@@R~
            if (PsyncDate==null)                                   //~v@@@I~
     		   	statusRuleSynch=SS_NONE;                           //~v@@@I~
        }                                                          //~v@@@I~
        public String getRuleSyncDateBTIO()                          //~v@@@I~
        {                                                          //~v@@@I~
    		if (Dump.Y) Dump.println("MemberData.getRuleSyncDateBTIO name="+name+",syncDate="+Utils.toString(ruleSyncDateBTIO));//~v@@@R~
        	return ruleSyncDateBTIO;                               //~v@@@I~
        }                                                          //~v@@@I~
        public boolean isRuleSynched()                              //~9B25I~
        {                                                          //~9B25I~
        	boolean rc=statusRuleSynch==SS_OK;                     //~9B25I~
    		if (Dump.Y) Dump.println("MemberData.isRuleSynched name="+name+",rc="+rc);//~9B25I~//~0110R~
        	return rc;                                             //~9B25I~
        }                                                          //~9B25I~
        public void resetSetupEnd()                                //~v@@@I~
        {                                                          //~v@@@I~
        	status&=~MS_SETUPEND;                                  //~v@@@I~
    		if (Dump.Y) Dump.println("MemberData.resetSetupEnd new status="+status+",name="+name);//~9B19R~
        }                                                          //~v@@@I~
        public boolean isSetupEnd()                                //~v@@@I~
        {                                                          //~v@@@I~
        	boolean rc=(status & MS_SETUPEND)!=0;                  //~v@@@I~
    		if (Dump.Y) Dump.println("MemberData.isSetupEnd="+rc+",name="+name+",status="+Integer.toHexString(status)); //~v@@@I~//~9B19R~//~va66R~
        	return rc;                                             //~v@@@I~
        }                                                          //~v@@@I~
        public boolean isLocal()                                   //~v@@@R~
        {                                                          //~v@@@R~
            return (status & MS_LOCAL)!=0;                         //~v@@@R~
        }                                                          //~v@@@R~
        public boolean isServer()                                  //~v@@@R~
        {                                                          //~v@@@R~
            return (status & MS_SERVER)!=0;                        //~v@@@R~
        }                                                          //~v@@@R~
        public boolean isClient()                                  //~v@@@I~
        {                                                          //~v@@@I~
            return (status & MS_CLIENT)!=0;                        //~v@@@I~
        }                                                          //~v@@@I~
        public boolean isRemoteClient()                            //~v@@@I~
        {                                                          //~v@@@I~
            return (status & MS_REMOTECLIENT)!=0;                  //~v@@@I~
        }                                                          //~v@@@I~
        public boolean isRobot()                                   //~v@@@I~
        {                                                          //~v@@@I~
            boolean rc=(status & (MS_SERVER | MS_LOCAL | MS_CLIENT | MS_REMOTECLIENT))==0;//~v@@@R~
    		if (Dump.Y) Dump.println("MemberData.isRobot rc="+rc+",status="+status);//~v@@@I~
            return rc;
        }                                                          //~v@@@I~
        public void setAccount(Accounts.Account Paccount)          //~9B11I~
        {                                                          //~9B11I~
    		if (Dump.Y) Dump.println("MemberData.setAccount Paccount="+Utils.toString(Paccount));//~9B11I~
            account=Paccount;                                      //~9B11I~
        }                                                          //~9B11I~
        public String toString()                                   //~v@@@I~
        {                                                          //~v@@@I~
            return "name="+name+",addr="+addr+",yourname="+yourname+",macAddr="+macAddr+",status=0x"+Integer.toHexString(status)+",connectionMode="+connectionMode+",syncDate="+ruleSyncDate+",syncDateBTIO="+ruleSyncDateBTIO+",thread="+Utils.toString(thread)+",statusRuleSync="+statusRuleSynch+",SocketBT="+Utils.toString(socket)+",socketIP="+Utils.toString(socketIP)+",robot="+Utils.toString(robot)+",account="+(account==null ? "null" :account.name);//~v@@@R~//~9B03R~//~0108R~//~0110R~
        }                                                          //~v@@@I~
        public boolean  isSocketConnected()                        //~va9iR~
        {                                                          //~va9iR~
            boolean rc=socket!=null || socketIP!=null;             //~va9iR~
            if (Dump.Y) Dump.println("MemberData.isSocketConnected rc="+rc);//~va9iR~
            return rc;                                             //~va9iR~
        }                                                          //~va9iR~
    }//class                                                       //~1AbTI~//~v@@@I~
}//class                                                       //~1AbTI~//~v@@@I~

