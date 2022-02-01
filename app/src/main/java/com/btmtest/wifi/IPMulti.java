//*CID://+vajiR~:                             update#=  428;       //+vajiR~
//********************************************************************************//~v101I~
//2022/01/31 vaji change color of top left to identify server      //+vajiI~
//2021/06/19 va9g SwTrainigMode was not cleared, startgame Hung at match as client after play alone//~va9gI~
//2020/11/20 va46 (Bug)reconnected member could not be disconnect  //~va46I~
//@002:20181103 use enum                                           //~@002I~
//@001:20181103 updatebuttonstatus over config change              //~@001I~
//****************************************************************************//~@001I~

package com.btmtest.wifi;                                          //~9723R~

import com.btmtest.BT.BTMulti;                                     //~9723R~
import com.btmtest.BT.Members;
import com.btmtest.EventMsg;
import com.btmtest.game.Status;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Utils;//~1AebR~
import com.btmtest.utils.UView;

import com.btmtest.R;                                              //~1AebR~

import static com.btmtest.BT.Members.*;
import static com.btmtest.BT.enums.MsgIDConst.*;                   //~@002R~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@002I~
import java.net.Socket;
import java.util.Arrays;

public class IPMulti extends BTMulti                                               //~1AebR~//~9723R~
{                                                                  //~1AebI~
    private static final int MSG_NAMER_POS_RECONNECT_IP=5;         //~0107I~
    private ConnectionData connectionData;                                 //~9723I~//~9A10R~
    public  boolean swServer;                                              //~9724I~//~9A10R~
    private IPIOThread clientThread;                               //~9A10R~
    public String thisDeviceMacAddr;                               //~9B03R~
    private int idxMember;	//idx of remoe devce(server for client)//~0221I~
    // *******************************************************      //~1AebI~
    public IPMulti(int Pmaxclient)                                 //~1AebR~//~9723R~
	{                                                              //~1AebI~
        super(Pmaxclient);                                         //~9723I~
    	if (Dump.Y) Dump.println("IPMulti:constructor maxclient="+Pmaxclient+",memberRole="+memberRole);//~1AebI~//~@002R~//~9723R~
        AG.aIPMulti=this;                                          //~1AebR~//~9723R~
        IPSubThread.newInstance();                                 //~9A02I~
    }                                                              //~1AebR~
    //************************************************************************//~9723R~
    //*From Server after accept() and PernerFrame after connect()  //~9723R~
    //*From PertnerFrame.connect                                   //~0B19I~
    //************************************************************************//~9723I~
//  public void onConnected(Socket Psocket,ConnectionDta PconnectionData,Boolean PswClient)//~9723R~
    public void onConnected(Socket Psocket, ConnectionData PconnectionData, Boolean PswClient)//~9723I~
    {                                                              //~9723I~
        if (Dump.Y) Dump.println("IPMulti:onConnected: reset swTrainingMode old="+AG.swTrainingMode);//~va9gI~
        AG.swTrainingMode=false;                                   //~va9gI~
    	if (Dump.Y) Dump.println("IPMulti:onConnected swClient="+PswClient+",connectionData="+PconnectionData.toString());//~0117I~//~0B19R~
        AG.RemoteStatus=AG.RS_IPCONNECTED;                         //~9729I~
    	swServer=!PswClient;                                       //~9724I~
        connectionData=PconnectionData;                            //~9723I~
        localDeviceName=connectionData.Name;                       //~9723I~
        String remoteDeviceName=connectionData.NameRemote;                //~9723R~
//      String addr=Psocket.getRemoteDevice().getAddress();        //~9723R~
        String addrRemote=PswClient ? connectionData.strOwnerAddress : connectionData.strClientAddress;//~9723I~
//      setRuleOutOfSynch();                                       //~9723I~//~9B25R~
        setRuleOutOfSynch(remoteDeviceName);                       //~9B25I~
    	if (Dump.Y) Dump.println("IPMulti:onConnected localdevice="+localDeviceName+",remote="+remoteDeviceName+"="+addrRemote+",swClient="+PswClient+",memberRole="+memberRole+",oldMemberRole="+oldMemberRole+",AG.yourname="+AG.YourName);//~9723R~//~0120R~
        int role=PswClient ? ROLE_CLIENT : ROLE_SERVER;            //~9723I~
//        if (memberRole==ROLE_UNDEFINED)                            //~9723I~//~0112R~
//        {                                                          //~9723I~//~0112R~
////          localDeviceName=Plocaldevicename;                      //~9723R~//~0112R~
//            memberRole=role;                                       //~9723I~//~0112R~
//            if (!PswClient) //server                               //~9723I~//~0112R~
////              updateAdd(Plocaldevicename,AG.YourName,Members.MS_SERVER|Members.MS_LOCAL); //add server itself//~9723R~//~0112R~
//                updateAdd(localDeviceName,AG.YourName, Members.MS_SERVER|Members.MS_LOCAL); //add server itself//~9723I~//~9A02R~//~0112R~
////              idxMember=updateAdd(localDeviceName,AG.YourName, Members.MS_SERVER|Members.MS_LOCAL); //add server itself//~9A02R~//~0112R~
//        }                                                          //~9723I~//~0112R~
//        else                                                       //~9723I~//~0112R~
//        if (role!=memberRole)                                      //~9723I~//~0112R~
//        {                                                          //~9723I~//~0112R~
//            Alert.showMessage(null/*title*/,Utils.getStr(R.string.ErrMixedRole));//~9723I~//~0112R~
//        }                                                          //~9723I~//~0112R~
//        if (PswClient) //client                                    //~9723I~//~0112R~
//        {                                                          //~9723I~//~0112R~
////          updateAdd(Plocaldevicename,AG.YourName,Members.MS_CLIENT|Members.MS_LOCAL);               //add client itself//~9723R~//~0112R~
//            updateAdd(localDeviceName,AG.YourName,Members.MS_CLIENT|Members.MS_LOCAL);               //add client itself//~9723I~//~0112R~
//        }                                                          //~9723I~//~0112R~
//*add localdevice                                                 //~0112I~
        if (memberRole==ROLE_UNDEFINED)                            //~0120I~
        {                                                          //~0120I~
	        if (!PswClient) //server                               //~0120I~
            {                                                      //~0120I~
	            if (oldMemberRole==ROLE_CLIENT) //me is client at disconnedcted, changed to server//~0120I~
					resetMemberDisconnectedClear();	//reset other of local and server(thread!=null)//~0120I~
            }                                                      //~0120I~
        }                                                          //~0120I~
        int stat=(PswClient ? Members.MS_CLIENT : Members.MS_SERVER) | Members.MS_LOCAL; //add server itself//~0112I~
		updateAdd(localDeviceName,AG.YourName,stat);               //add client itself//~0112R~
        if (memberRole!=ROLE_UNDEFINED && role!=memberRole)       //~0112R~
        {                                                          //~0112I~
            Alert.showMessage(null/*title*/,Utils.getStr(R.string.ErrMixedRole));//~0112I~
        }                                                          //~0112I~
        memberRole=role;                                           //~0112I~
//      updateMember(Premotedevicename,Psocket,Pswclient ? Members.MS_SERVER : Members.MS_REMOTECLIENT);    //add remote, client for server, server for client//~9723R~
//      updateMember(remoteDeviceName,Psocket,PswClient ? Members.MS_SERVER : Members.MS_REMOTECLIENT);    //add remote, client for server, server for client//~9723I~//~9724R~//~9725R~
//*add remotedevice                                                //~0112I~
      if (WDA.isReconnecting())                                    //~9B03I~
        updateMemberReconnect(connectionData,Psocket,PswClient ? Members.MS_SERVER : MS_REMOTECLIENT);    //add remote, client for server, server for client//~9B03R~
      else                                                         //~9B03I~
        updateMember(connectionData,Psocket,PswClient ? Members.MS_SERVER : MS_REMOTECLIENT);    //add remote, client for server, server for client//~9725I~//~9726R~//~9A02R~
//      BTCDialog.onConnected(Premotedevicename,addr,Pswclient);   //~9723R~
//      WDA.onConnected(remoteDeviceName,addrRemote,PswClient);    //~9723I~//~9726R~
        if (!PswClient)                                            //~9723I~
        {                                                          //~9723I~
//      	serverDeviceName=Plocaldevicename;                     //~9723R~
//	    	openServer(Psocket,Plocaldevicename,Premotedevicename);//~9723I~
        	serverDeviceName=localDeviceName;                      //~9723I~
//	    	openServer(Psocket,localDeviceName,remoteDeviceName);  //~9723I~//~9726R~
  	    	openServer(Psocket,localDeviceName,connectionData.strClientAddress);//~9726I~
        }                                                          //~9723I~
        else                                                       //~9723I~
        {                                                          //~9723I~
//  		updateMember(connectionData,Psocket,PswClient ? Members.MS_SERVER : Members.MS_REMOTECLIENT);    //add remote, client for server, server for client//~9726I~//~9824R~//~9A02R~
//  		WDA.onConnected(remoteDeviceName,addrRemote,PswClient);//~9726I~//~9B05R~
//      	serverDeviceName=Premotedevicename;                    //~9723R~
//      	openClient(Psocket,Plocaldevicename,Premotedevicename);//~9723R~
        	serverDeviceName=remoteDeviceName;                     //~9723I~
        	openClient(Psocket,localDeviceName,remoteDeviceName);  //~9723I~
//    		WDA.onConnected(remoteDeviceName,addrRemote,PswClient); //dialog will be update fron on WDA.connectionChanged at received nameADD//~9B05I~
    		WDA.onConnected(remoteDeviceName);	//show status msg  //~0108I~
			resetMemberDisconnected();	//reset other of local and server(thread!=null)//~0120I~
        }                                                          //~9723I~
        AG.aMainView.showConnectStatus();                          //~vac5R~//+vajiI~
    }                                                              //~9723I~
    //*******************************************************      //~1AebI~//~9723I~
    //* add remote member at NotInGame                             //~0112R~
    //*******************************************************      //~1AebI~//~9723I~
    private boolean updateMember(ConnectionData PconnectionData,Socket Psocket,int Pstatus)//~1AebR~//~9723I~//~9724R~//~9725R~
	{                                                              //~1AebI~//~9723I~
        String devname=connectionData.NameRemote;                  //~9725I~
        if (Dump.Y) Dump.println("IPMulti.updateMember before="+BTGroup.toString());//~9A10I~
//  	int rc=BTGroup.updateAdd(devname,Psocket,Pstatus);         //~0112I~
        int rc;                                                    //~0112I~
        if (Pstatus==Members.MS_SERVER)    //add remote, client for server, server for client//~0112I~
    		rc=BTGroup.updateAdd(devname,Psocket,Pstatus);              //~1AebR~//~9723I~//~9724R~//~9725R~//~0112R~
        else                                                       //~0112I~
	    	rc=BTGroup.updateAddWDByIPAddr(devname,Psocket,Pstatus);  //search old by ip addr(if paied ip addr may not change)//~0112R~
    	if (rc<0)                                                  //~1AebR~//~9723I~
        {                                                          //~1AebI~//~9723I~
        	UView.showToastLong(AG.resource.getString(R.string.ErrMemberOverflow,maxClient));//~1AebI~//~9723I~
        	return false;                                          //~1AebI~//~9723I~
        }                                                          //~1AebI~//~9723I~
        idxMember=rc;	//idx of remote device                                              //~1AebI~//~9723I~//~9A09R~
        if (Dump.Y) Dump.println("IPMulti.updateMember after="+BTGroup.toString());//~9A10I~
        if (Dump.Y) Dump.println("IPMulti.updateMember with Socket idxMember="+idxMember+",addmember="+devname+",socket="+Psocket.toString());//~1AebR~//~9723I~//~9724R~//~9725R~//~9A09R~
        ctrClient=BTGroup.getConnectedCtr();                       //~1AebI~//~9723I~
        return true;                                               //~1AebI~//~9723I~
    }                                                              //~1AebI~//~9723I~
    //*******************************************************      //~9B03I~
    //* add group member at reconnect(old name:devicename,new name:ipaddr//~9B03I~
    //*******************************************************      //~9B03I~
    private boolean updateMemberReconnect(ConnectionData PconnectionData,Socket Psocket,int Pstatus)//~9B03R~
	{                                                              //~9B03I~
        String devname=connectionData.NameRemote;                  //~9B03I~
        if (Dump.Y) Dump.println("IPMulti.updateMemberReconnectWD before="+BTGroup.toString());//~9B03I~
        if (Dump.Y) Dump.println("IPMulti.updateMemberReconnectWD connectionData="+PconnectionData.toString());//~0117I~
    	int rc=BTGroup.updateAddReconnectWD(devname,Psocket,Pstatus);//~9B03I~
    	if (rc<0)                                                  //~9B03I~
        {                                                          //~9B03I~
        	UView.showToastLong(AG.resource.getString(R.string.ErrMemberOverflow,maxClient));//~9B03I~
        	return false;                                          //~9B03I~
        }                                                          //~9B03I~
        idxMember=rc;	//idx of remote device                     //~9B03I~
        if (Dump.Y) Dump.println("IPMulti.updateMemberReconnectWD after="+BTGroup.toString());//~9B03I~
        if (Dump.Y) Dump.println("IPMulti.updateMemberreconnectWD with Socket idxMember="+idxMember+",addmember="+devname+",socket="+Psocket.toString());//~9B03I~
        ctrClient=BTGroup.getConnectedCtr();                       //~9B03I~
        return true;                                               //~9B03I~
    }                                                              //~9B03I~
//***************************************************************************//~1AebI~//~9723I~
//  private void openServer(Socket Psocket,String Plocalname,String Premotename/*peerS*/)//~1AebR~//~9723I~//~9726R~
    private void openServer(Socket Psocket,String Plocalname,String PclientAddr)//~9726I~
    {                                                              //~1AebM~//~9723I~
    	if (Dump.Y) Dump.println("IPMulti:openServer");            //~1AebR~//~9723I~
    	if (Dump.Y) Dump.println("clientAddr="+PclientAddr+",local="+Plocalname);//~1AebI~//~9723I~//~9726R~
                                                                   //~9726I~
//      IPIOThread t=new IPIOServer(Psocket,Plocalname,Premotename,idxMember).connect();//~1AebR~//~9723R~//~9726R~
        IPIOThread t=new IPIOServer(Psocket,Plocalname,PclientAddr,idxMember).connect();//~9726I~
        if (t!=null)                                               //~1AebI~//~9723I~//~0112R~
        {                                                          //~1AebI~//~9723I~//~0112R~
//          updateMember(Premotename,t);                           //~1AebR~//~9723I~//~9726R~//~0112R~
//            int stat= MS_REMOTECLIENT;                      //~9726I~//~0112R~
//            BTGroup.updateAdd(PclientAddr/*tempname until receive NAMER*/,Psocket,stat);//~9726I~//~0112R~
        	BTGroup.updateWDBySocket(Psocket,t);                   //~0112I~
        }                                                          //~1AebI~//~9723I~//~0112R~
    }                                                              //~1AebM~//~9723I~
//***************************************************************************//~9726I~
//* on Server                                                      //~9729I~
//***************************************************************************//~9729I~
    public void receivedNameR(IPIOThread PioThread,String[] PnameR)//devname,yourname,syncdate,ipaddr,macaddr,swReconnecting//~9B06R~//~0107R~//~0108R~
    {                                                              //~9726I~
        UView.showToastLong(Utils.getStr(R.string.Info_Connected,PnameR[1]));//~9B06I~
        if (!chkReconnecting(PnameR[0],PnameR,MSG_NAMER_POS_RECONNECT_IP))//~0107I~
        {                                                          //~0107I~
            setReconnectErr(PnameR); //devname,macaddr             //~0116R~
//      	updateMember(PnameR[0],PnameR[1],""/*syncDate*/);      //~0107I~//~0108R~
            String macAddr=PnameR[4];                              //~va46I~
            String syncDT=PnameR[2];                               //~va46I~
        	updateMember(PnameR[0],PnameR[1],macAddr,syncDT,false/*swNotify*/); //set connected status on devicelist of dialog//~va46R~
			WDA.onConnectionChanged();  //update Dialog            //~va46I~
            return;                                                //~0107I~
        }                                                          //~0107I~
	    if (WDA.isReconnecting())                                  //~9B03I~
        {                                                          //~9B03I~
		    receivedNameR_Reconnect(PioThread,PnameR);              //~9B03I~
            return;                                                //~9B03I~
        }                                                          //~9B03I~
    	if (Dump.Y) Dump.println("IPMulti:receivedNameR PnameR="+ Arrays.toString(PnameR)); //devicename,yourname,syncdata,ipaddr//~9726I~
  	    int idx=BTGroup.update(PnameR,(Thread)PioThread);                            //~9726R~//~9729R~
        if (idx>=0)                                                //~9726I~
        {                                                          //~9726I~
        	notifyToMembers(true/*connected*/,PnameR[0]/*devname*/,PnameR[1]/*yourname*/);        //~1AebR~//~9815I~
	        String remote=BTGroup.getName(idx);                    //~9726I~
	        String addr=BTGroup.getAddr(idx);                      //~9726I~
			WDA.onConnected(remote,addr,true/*swClient*/);         //~9726I~
        }                                                          //~9726I~
    }                                                              //~9726I~
    //*******************************************************      //~0116I~
    protected void setReconnectErr(String[] PnameR)                //~0116I~
	{                                                              //~0116I~
    	if (Dump.Y) Dump.println("BTMulti.setReconnectErr nameR="+Arrays.toString(PnameR));//~0116I~
    	BTGroup.setReconnectErr(PnameR[4]); //macaddr              //~0116I~
    }                                                              //~0116I~
//***************************************************************************//~9B03I~
//* on server bt #@name msg                                        //~0108I~
//***************************************************************************//~0108I~
    private void receivedNameR_Reconnect(IPIOThread PioThread,String[] PnameR)//~9B03I~//~0108R~
    {                                                              //~9B03I~
    	if (Dump.Y) Dump.println("IPMulti:receivedNameR_Reconnect PnameR="+ Arrays.toString(PnameR)); //devicename,yourname,syncdata,ipaddr//~9B03I~
//	    int idx=BTGroup.updateReconnect(PnameR,(Thread)PioThread); //~9B03I~//~0108R~
  	    int idx=BTGroup.updateReconnect(PnameR,(Thread)PioThread,MS_REMOTECLIENT);//~0108I~
        if (idx>=0)                                                //~9B03I~
        {                                                          //~9B03I~
        	notifyToMembers(true/*connected*/,PnameR[0]/*devname*/,PnameR[1]/*yourname*/);//~9B03I~
	        String remote=BTGroup.getName(idx);                    //~9B03I~
	        String addr=BTGroup.getAddr(idx);                      //~9B03I~
			WDA.onConnected(remote,addr,true/*swClient*/);         //~9B03I~
        }                                                          //~9B03I~
    }                                                              //~9B03I~
//***************************************************************************//~9815I~
//* on client                                                      //~9815R~
//***************************************************************************//~9815I~
    public void receivedNameAdd(IPIOThread PioThread,String[] PnameAdd)//~9815R~
    {                                                              //~9815I~
        updateNotified(PnameAdd);  //-->BTM.updateNotified,updateSeq//~0224R~
        if (clientThread!=null)                                    //~9A10I~
        {                                                          //~9A10I~
//      	clientThread.idxMember=AG.aBTMulti.BTGroup.idxLocal;   //~9A10I~//~0224R~
        	clientThread.idxMember=AG.aBTMulti.BTGroup.idxServer; //seqno[] idx//~0224I~
        	clientThread.idxServer=AG.aBTMulti.BTGroup.idxServer;  //~9A10I~
        }                                                          //~9A10I~
    	if (Dump.Y) Dump.println("IPMulti:receivedNameAdd PnameAdd="+ Arrays.toString(PnameAdd)); //devicename,yourname,syncdata,ipaddr//~9815R~
		WDA.onConnectionChanged();                                 //~9815I~
    }                                                              //~9815I~
    public void receivedNameDel(IPIOThread PioThread,String Pname) //~9B07I~
    {                                                              //~9B07I~
    	if (Dump.Y) Dump.println("IPMulti:receivedNameDel Pname="+ Pname);//~9B07I~
        if (removeMember(Pname)) //->BTMulti,deleted                //~9B07I~
        {                                                          //~9B07I~
			WDA.onConnectionChanged();                             //~9B07I~
        }                                                          //~9B07I~
    }                                                              //~9B07I~
//***************************************************************************//~1AebM~//~9723I~
    private void openClient(Socket Psocket,String Plocalname,String Premotename)//~1AebR~//~9723I~
    {                                                              //~1AebM~//~9723I~
    	if (Dump.Y) Dump.println("IPMulti:openClient socket="+Psocket.toString());//~1AebR~//~9723I~
        int idxMember=BTGroup.search(Plocalname);  //later updated at received server's seq//~0221R~
    	if (Dump.Y) Dump.println("IPMulti:openClient idxMember="+idxMember+",remotedevice="+Premotename+",local="+Plocalname);//~1AebR~//~9723I~//~9B05R~//~0221R~
        IPIOThread t=new IPIOClient(Psocket,Plocalname,Premotename,idxMember).connect();//~1AebR~//~9723R~
        if (t!=null)                                               //~1AebI~//~9723I~
		    updateMember(Premotename,t);                           //~1AebR~//~9723I~
        clientThread=t;                                            //~9A10I~
    }                                                              //~1AebM~//~9723I~
//***************************************************************************//~9724I~
//* get remote ipaddress from wifidirect socket                    //~9724I~
//***************************************************************************//~9724I~
    public String getRemoteIPAddr(String Pdevicename)              //~9724R~
    {                                                              //~9724I~
        String ipa=null;                                                //~9724I~//~9729R~
    	if (swServer)                                              //~9724I~
        	ipa=BTGroup.getMemberAddr(Pdevicename);                //~9724R~
        else                                                       //~9724I~
        {                                                          //~9729I~
            Socket s=BTGroup.getMemberSocketIP(Pdevicename);       //~9729I~
            if (s!=null)                                           //~9729I~
	        	ipa=BTGroup.getMemberAddr(Pdevicename);            //~9729I~
        }                                                          //~9729I~
    	if (Dump.Y) Dump.println("IPMulti:getRemoteIPAddr swServer="+swServer+",devicename="+Pdevicename+",ipa="+Utils.toString(ipa));//~9724R~
        return ipa;                                                //~9724I~
    }                                                              //~9724I~
//***************************************************************************//~9729I~
//* from IPIOThread when thread terminating                        //~9729I~
//***************************************************************************//~9729I~
    public void connectionLost(boolean PswServer,String PlocalDeviceName,String PremoteDeviceName)//~9729I~
    {                                                              //~9729I~
    	if (Dump.Y) Dump.println("IPMulti.connectionLost swServer="+PswServer+",local="+PlocalDeviceName+",remote="+PremoteDeviceName);//~9729I~
        String name=PremoteDeviceName;                             //~9729I~
        if (Status.isGamingNow())                                  //~9B03I~
        {                                                          //~9B03I~
            EventMsg.postReadMsg(MSGID_IOERR,name); //to MainActivity.ACTION_GETCTLMSG-->BTMulti.msgRead-->BTM.onDisconectedIP//~9B03I~
            if (!swServer)                                         //~9B04I~
				AG.RemoteStatus=AG.RS_IP;     //disconnected       //~9B04I~
            return;                                                //~9B03I~
        }                                                          //~9B03I~
	    updateMember(name,(IPIOThread)null);                       //~9730I~
        if (removeMember(name)) //->BTMulti,deleted                           //~9729I~//~9B03R~
        {                                                  //~1AebI~//~9729I~
            WDA.connectionLost(PswServer,PlocalDeviceName,PremoteDeviceName);//~9729I~
            notifyToMembers(false/*disconnected*/,name,""/*yourname*/);//~1AebI~//~9729I~
            if (BTGroup.getConnectedCtr()==0)              //~@002R~//~9729R~
            {                                              //~@002I~//~9729R~
                if (Dump.Y) Dump.println("IPMulti:connectionLost connectedctr=0 AG.remoteStatus changed oldMemberRole="+memberRole);//~9729R~//~0120R~
	            oldMemberRole=memberRole;	//clear Member if changed client to server//~0120I~
//                memberRole=ROLE_UNDEFINED;  //enable to change role//~@002R~//~9729I~
                memberRole=ROLE_UNDEFINED;  //enable to change role//~9B07I~
				AG.RemoteStatus=AG.RS_IP;     //disconnected       //~9729I~
		        AG.aMainView.showConnectStatus();                          //~vac5R~//+vajiI~
            }                                              //~@002I~//~9729R~
        }                                                  //~1AebI~//~9729I~
    }                                                              //~9729I~
    //************************************************************ //~1AebI~//~9729I~
	public void onConnectionFailed(ConnectionData PconnectionData)                      //~1AebR~//~9729I~
    {                                                              //~1AebI~//~9729I~
        if (Dump.Y) Dump.println("IPMulti:onConnectionFailed connectionData="+PconnectionData.toString());//~9729I~
        PartnerFrame.dismissWaitingDialog();                                    //~1AebR~//~9729I~
		String msg=Utils.getStr(R.string.Err_No_connection_to_IP,PconnectionData.NameRemote);//~9729R~
        WDA.updateDialog(true,msg);                                //~9729I~
    }                                                              //~1AebI~//~9729I~
    //*****************************************************************//~0112I~
    //*from DviceListFragment.clearPeers                           //~0112I~
    //*****************************************************************//~0112I~
    public void resetAllConnection()                               //~0112I~
    {                                                              //~0112I~
		if (Dump.Y) Dump.println("IPMulti.resetAllConnection");    //~0112I~
        BTGroup.resetAllConnection();                              //~0112I~
    }                                                              //~0112I~
    //*****************************************************************//~0112I~
    public void closeAllConnection()                               //~0112I~
    {                                                              //~0112I~
		if (Dump.Y) Dump.println("IPMulti.closeAllConnection");    //~0112I~
        for (Members.MemberData d:BTGroup.MD)                  //~1AebR~//~0112I~
        {                                                      //~1AebR~//~0112I~
			if (Dump.Y) Dump.println("IPMulti.closeAllConnection MD="+d.toString());//~0112I~
            IPIOThread t=(IPIOThread)d.getThread();        //~1AebI~//~0112I~
            if (t!=null)                                           //~0112I~
            {                                                  //~1AebI~//~0112I~
            	t.endThread();                                     //~0112I~
            }                                                  //~1AebI~//~0112I~
        }                                                      //~1AebI~//~0112I~
    }                                                              //~0112I~
}
