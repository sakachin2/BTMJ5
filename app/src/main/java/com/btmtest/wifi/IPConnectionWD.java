//*CID://+@@@@R~:                             update#=  216;       //~1AecR~//~@@@@R~
//**********************************************************************************//~1A05I~
package com.btmtest.wifi;                                               //~v@@@I~//~9719I~//~@@@@I~

import android.content.Intent;
import android.provider.Settings;

import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.ListClass;
import com.btmtest.utils.ProgDlg;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.net.Socket;

import static com.btmtest.StaticVars.AG;                           //~@@@@I~
import static com.btmtest.wifi.WDI.*;                              //~@@@@I~


public class IPConnectionWD                                          //~1A84R~//~@@@@R~
		implements ProgDlg.ProgDlgI//~3105R~//~v101R~
{                                                                  //~2C29R~
//  public  static final int NFC_SERVER=1;    //identify connection type//~1A6BI~//~1AadR~
//  public  static final int NFC_CLIENT=2;                         //~1A6BI~//~1AadR~
//  public  static final int WD_SERVER=3;                          //~1A6BI~//~1AadR~
//  public  static final int WD_CLIENT=4;                          //~1A6BI~//~1AadR~
//  public  static final String PKEY_SERVER_PORT="ServerPort";     //~1A6sI~//~1AadR~
//  private static final String WDCLIENT_IPPREFIX="ClientOf-";     //~1A67I~//~1AadR~
	public Server S=null;                                          //~v101I~
	private WDA aWDA;                                              //~1A65I~
	private int portNo;                                  //~v101R~
	private String myIPAddr;                                       //~v101I~
    private boolean wifiStat;                                      //~1A05I~
    private int waitingDialog=0;                                   //~v101I~
    private ConnectionData connectionData;                                       //~v101I~//~@@@@R~
    private ConnectionData connectionDataDisconnect;               //~@@@@I~
    private ConnectionData connectionDataConnect;                  //~@@@@I~
	ListClass PartnerList;
//  FormTextField ServerPort;//~v101I~                             //~1A84R~
//    MainFrame F;                                                   //~v101I~
//  private String partnerprefix;                                  //~1A6nI~//~1A8aR~
//  public IPConnectionWD(MainFrame Pgf)                            //~v101I~//~1A84R~//~@@@@R~
    //***********************************************************************//~@@@@I~
    //*from WDI.startRemoteIP<--MenuDialogConnect                  //~@@@@I~
    //***********************************************************************//~@@@@I~
    public IPConnectionWD()                                          //~1A84R~//~@@@@R~
	{                                                              //~3105R~
        AG.aIPConnectionWD=this;  //used when PartnerThread detected err//~1A6kI~//~1A84R~//~@@@@R~
        myIPAddr=Utils.getIPAddressDirect(); //WiFiDirect IP addr(with local macaddr)//~1A8cI~
    	portNo=getPortNo();                                        //~1A84I~
		startWD();                                                 //~1A84I~
	}
    //***********************************************************************
    //*from WDA
    //***********************************************************************
    public boolean buttonAction(int Pbuttonid)                            //~1A84I~
    {                                                              //~1A84I~
    	if (Dump.Y) Dump.println("IPConnectionWD buttonAction id="+Integer.toHexString(Pbuttonid));//~@@@@I~
        boolean dismiss=false;                                     //~1A84I~
        switch(Pbuttonid)                                          //~1A84I~
        {                                                          //~1A84I~
//        case WDA.ID_GAME:                                          //~1A84I~//~@@@@R~
//            if (startGame())                                       //~1A84I~//~@@@@R~
//                dismiss=true;                                      //~1A84I~//~@@@@R~
//            break;                                                 //~1A84I~//~@@@@R~
        case WDA.ID_CONNECT:                                       //~1A84I~
            if (connectPartnerTest())                              //~1A84I~
            {                                                      //~1A84I~
                dismiss=true;                                      //~1A84I~//~@@@@R~
                waitingDialog= R.string.IPConnect;                  //~1A84I~
            }                                                      //~1A84I~
            break;                                                 //~1A84I~
        case WDA.ID_ACCEPT:                                        //~1A84I~
//          if (startServer())                                     //~1A84I~//~@@@@R~
//          {                                                      //~1A84I~//~@@@@R~
//              dismiss=true;                                      //~1A84I~//~@@@@R~
//              waitingDialog=R.string.AcceptIPConnection;         //~1A84I~//~@@@@R~
//          }                                                      //~1A84I~//~@@@@R~
            startServer();                                         //~@@@@I~
            break;                                                 //~1A84I~
        case WDA.ID_STOPACCEPT:                                    //~1A84I~
			stopServer();                                          //~1A84I~
            break;                                                 //~1A84I~
        case WDA.ID_DISCONNECT:                                    //~1A84I~
            if (disconnectPartnerTest())                           //~@@@@I~
            {                                                      //~@@@@I~
            	dismiss=true;                                          //~1A84I~//~@@@@R~
            	waitingDialog=R.string.IPDisConnect;                   //~1A84I~//~@@@@R~
            }                                                      //~@@@@I~
            break;                                                 //~1A84I~
        }                                                          //~1A84I~
//        if (dismiss)                                               //~1A84I~//~@@@@R~
//            onCloseWDA();   //get parm                             //~1A84I~//~@@@@R~
//      return dismiss;                                            //~1A84I~//~@@@@I~
		if (dismiss)                                               //~@@@@I~
    		showWaiting(waitingDialog);                            //~@@@@I~
        return false;	//no dismiss                               //~@@@@I~
    }                                                              //~1A84I~
//    //******************************************                 //~@@@@R~
//    //*on Client                                                 //~@@@@R~
//    //******************************************                 //~@@@@R~
//    private ConnectionData getServer()                           //~@@@@R~
//    {                                                            //~@@@@R~
////        if (cd!=null && !cd.isPaired)                          //~@@@@R~
////        {                                                      //~@@@@R~
////            setStatus(true,R.string.Wifi_Pair_NotPaired);      //~@@@@R~
////            cd=null;                                           //~@@@@R~
////        }                                                      //~@@@@R~
//        ConnectionData cd=null;                                  //~@@@@R~
//        int thisOwner=AG.aWDA.deviceListFragment.thisOwner;      //~@@@@R~
//        if (thisOwner!=0)   //client                             //~@@@@R~
//        {                                                        //~@@@@R~
//            setStatus(true,R.string.Wifi_Pair_NotPaired);        //~@@@@R~
//        }                                                        //~@@@@R~
//        else                                                     //~@@@@R~
//        {                                                        //~@@@@R~
//            cd=new ConnectionData();                             //~@@@@R~
//            cd.Name=AG.aWDA.deviceListFragment.thisDevice;       //~@@@@R~
//            cd.strOwnerAddress=AG.aWDA.deviceDetailFragment.ownerIPAddress;//~@@@@R~
//            cd.NameRemote=AG.aWDA.deviceDetailFragment.peerDevice;//~@@@@R~
//            cd.isOwner=thisOwner==1;                             //~@@@@R~
//            cd.portNo=portNo;                                    //~@@@@R~
//        }                                                        //~@@@@R~
//        if (Dump.Y) Dump.println("IPConnectionWD getServer cd="+Utils.toString(cd));//~@@@@R~
//        return cd;                                               //~@@@@R~
//    }                                                            //~@@@@R~
    //******************************************                   //~@@@@I~
    //*on Server                                                   //~@@@@I~
    //******************************************                   //~@@@@I~
    private ConnectionData getServer(boolean PswOwner)             //~@@@@I~
    {                                                              //~@@@@I~
    	ConnectionData cd=new ConnectionData();                    //~@@@@R~
        cd.Name=AG.aWDA.deviceListFragment.thisDevice;             //~@@@@I~
    	cd.strOwnerAddress=AG.aWDA.deviceDetailFragment.ownerIPAddress;//~@@@@I~
    	cd.isOwner=AG.aWDA.deviceListFragment.thisOwner==1;        //~@@@@I~
        cd.portNo=portNo;                                          //~@@@@I~
    	if (Dump.Y) Dump.println("IPConnectionWD getServer owner connectionData="+cd.toString());//~@@@@R~
    	return cd;                                                 //~@@@@I~
    }                                                              //~@@@@I~
    //******************************************                   //~@@@@I~
    //*on Client, set Name,NameRemote,ownerIPAddr,isPaired,osOwner //~@@@@R~
    //******************************************                   //~@@@@I~
    private ConnectionData getServerSelected()                     //~@@@@I~
    {                                                              //~@@@@I~
    	ConnectionData cd=AG.aWDA.getPairingInfo();	//Name,ownerIPAddr,isPaired,isOwner,remoteName//~@@@@R~
        if (cd==null)                                              //~@@@@I~
        {                                                          //~@@@@I~
        	return null;                                          //~@@@@I~
        }                                                          //~@@@@I~
        cd.portNo=portNo;                                          //~@@@@I~
    	if (Dump.Y) Dump.println("IPConnectionWD.getServerSelected connectionData="+cd.toString());//~@@@@R~
    	return cd;                                                 //~@@@@I~
    }                                                              //~@@@@I~
    //******************************************                   //~v101I~//~@@@@M~
    private boolean/*dispose*/ connectPartnerTest()                //~v101I~//~@@@@M~
    {                                                              //~v101I~//~@@@@M~
        boolean rc=false;                                          //~@@@@M~
//        if (!wifiStat)                                             //~1A05I~//~@@@@M~
//        {                                                          //~1A05I~//~@@@@M~
//            errWifi();                                             //~1A05I~//~@@@@M~
//            return false;                                          //~1A05I~//~@@@@M~
//        }                                                          //~1A05I~//~@@@@M~
//        String s=getSelected();                                    //~v101I~//~1A84R~//~@@@@M~
//        if (s==null)                                               //~v101I~//~1A84R~//~@@@@M~
//            return false;                                          //~v101I~//~1A84R~//~@@@@M~
//        Partner c=pfind(s);                                        //~v101I~//~1A84R~//~@@@@M~
//        if (c==null) // try connecting to this partner server, if not trying already//~v101I~//~1A84R~//~@@@@M~
//            return false;                                          //~v101I~//~1A84R~//~@@@@M~
//        partner=c;                                                 //~v101I~//~1A84R~//~@@@@M~
//      connectionData=getServer();                                //~@@@@M~
//      if (connectionData==null)                                  //~@@@@M~
//          return false;                                          //~@@@@M~
        ConnectionData cd=getServerSelected();	//name,ownerIPAddr,isPaired,isOwner//~@@@@M~
        if (cd!=null && cd.isPaired)                               //~@@@@M~
        {                                                          //~@@@@M~
//  		Socket s=AG.aBTMulti.BTGroup.getMemberSocketIP(cd.NameRemote);//~@@@@R~
//          if (s==null)                                           //~@@@@R~
    		Thread t=AG.aBTMulti.BTGroup.getThread(cd.NameRemote); //~@@@@I~
            if (t==null)                                           //~@@@@I~
            {                                                      //~@@@@M~
	            connectionDataConnect=cd;                          //~@@@@I~
                rc=true;                                           //~@@@@M~
	        	if (Dump.Y) Dump.println("IPConnectionWD:connectPartnerTest connectionDataConnect="+connectionDataConnect.toString());//~@@@@I~
            }                                                      //~@@@@M~
        }                                                          //~@@@@M~
        if (!rc)                                                   //~@@@@M~
			setStatus(true,R.string.Wifi_Pair_NotPaired);          //~@@@@M~
        return rc;                                               //~v101I~//~@@@@M~
    }                                                              //~v101I~//~@@@@M~
    //******************************************                   //~v101I~
    private boolean/*dispose*/ connectPartner()                    //~v101R~
    {                                                              //~v101I~
//      if (connectionData!=null) // try connecting to this partner server, if not trying already//~v101R~//~@@@@R~
//      {                                                          //~v101I~//~@@@@R~
//  		writeplist();                                          //~v101I~//~1A84R~
//      	Partner c=partner;                                     //~v101I~//~@@@@R~
//          int connectiontype=(c.Name.startsWith(partnerprefix)) ? WD_CLIENT : 0;//~1A6BI~//~1A84R~
//          int connectiontype=WD_CLIENT;                          //~1A84I~//~1AadR~
//          int connectiontype=jagoclient.partner.IPConnectionWD.WD_CLIENT;//~1AadI~//~@@@@R~
            int connectiontype=WD_CLIENT;                          //~@@@@I~
//          SlastConnectName=c.Name;                               //~v101I~
//TODO test   PartnerFrame cf=                                       //~v101I~//~@@@@R~
//                new PartnerFrame(                                  //~v101I~//~@@@@R~
////                  Global.resourceString("Connection_to_")+c.Name,false,connectiontype);//~1A6BI~//~@@@@R~
//                    Utils.getStr(R.string.Connection_to_)+c.Name,false,connectiontype);//~@@@@R~
//TODO test new ConnectPartner(c,cf);                              //~v101I~//~@@@@R~
//          connectionData.portNo=portNo;                          //~@@@@R~
//          PartnerFrame pf=new PartnerFrame(connectionData);      //~@@@@R~
            PartnerFrame pf=new PartnerFrame(connectionDataConnect);//~@@@@I~
            new ConnectPartner(pf);//go to PartnerFrame.connect() by ownerIP and portNo//~@@@@R~
            return true;                                           //~v101I~
//      }                                                          //~v101I~//~@@@@R~
//      return false;                                              //~v101I~//~@@@@R~
    }                                                              //~v101I~
    //******************************************                   //~@@@@I~
    private boolean disconnectPartnerTest()                        //~@@@@I~
    {                                                              //~@@@@I~
    	boolean rc=false;                                          //~@@@@I~
        if (Dump.Y) Dump.println("IPConnectionWD:disconnectPartnerTest");//~@@@@I~
        ConnectionData cd=getServerSelected();                     //~@@@@I~
        if (cd!=null)                                              //~@@@@I~
        {                                                          //~@@@@I~
//  		int idx=AG.aBTMulti.BTGroup.search(cd.Name);           //~@@@@R~
    		int idx=AG.aBTMulti.BTGroup.search(cd.NameRemote);     //~@@@@I~
            if (idx>=0)                                            //~@@@@I~
            {                                                      //~@@@@I~
        	    cd.IOthread=(IPIOThread)AG.aBTMulti.BTGroup.getThread(idx);    //~@@@@R~
                if (cd.IOthread!=null)                             //~@@@@I~
                {                                                  //~@@@@I~
                    cd.strAddress=AG.LocalInetAddress;             //~@@@@R~
                    if (cd.isOwner)                                //~@@@@R~
                        cd.strClientAddress=AG.aBTMulti.BTGroup.getAddr(idx);//~@@@@R~
                    connectionDataDisconnect=cd;                   //~@@@@R~
                    rc=true;                                       //~@@@@R~
                }                                                  //~@@@@I~
	        	if (Dump.Y) Dump.println("IPConnectionWD:disconnectPartnerTest connectionDataDisconnect="+Utils.toString(connectionDataDisconnect));//~@@@@R~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
        if (!rc)                                                   //~@@@@I~
			setStatus(true,R.string.Wifi_Pair_NotIPConnected);     //~@@@@M~
        if (Dump.Y) Dump.println("IPConnectionWD:disconnectPartnerTest rc="+rc);//~@@@@R~
        return rc;                                                 //~@@@@I~
    }                                                              //~@@@@I~
    //***********************************************************************
    private void disconnectPartner()                               //~v101M~//~1A87R~//+@@@@R~
    {                                                              //~v101M~
        if (Dump.Y) Dump.println("IPConnectionWD:disconnectPartner connectionDataDisconnect="+Utils.toString(connectionDataDisconnect));//~@@@@R~
        if (connectionDataDisconnect==null)                        //~@@@@I~
        	return;                                                //~@@@@I~
        if (Dump.Y) Dump.println("IPConnectionWD:disconnectPartner isOwner="+connectionDataDisconnect.isOwner+",partnerFrame="+Utils.toString(AG.aPartnerFrameIP));//~@@@@I~
	    if (connectionDataDisconnect.isOwner)                      //~@@@@I~
        {                                                          //~@@@@I~
			connectionDataDisconnect.IOthread.doClose();	//CloseConnection//~@@@@I~
        	return;                                                //~@@@@I~
        }                                                          //~@@@@I~
    	if (AG.aPartnerFrameIP!=null)                              //~v101R~
        {                                                          //~1A87I~
        	if (Dump.Y) Dump.println("IPConnectionWD:disconnectPartner");//~1A87I~//~@@@@R~
//      	AG.aPartnerFrameIP.disconnect();                       //~v101M~//~@@@@R~
        	AG.aPartnerFrameIP.disconnect(connectionDataDisconnect);//~@@@@I~
        }                                                          //~1A87I~
  	}                                                              //~v101M~
    //***********************************************************************//~1Ac3I~
    //*from WDA                                                    //~1Ac3I~
    //***********************************************************************//~1Ac3I~
    public void disconnectPartner(boolean Punpair)                 //~1Ac3I~
    {                                                              //~1Ac3I~
    	if (AG.aPartnerFrameIP!=null)                              //~1Ac3I~
        {                                                          //~1Ac3I~
        	if (Dump.Y) Dump.println("IPConnectionWD:disconnectPartner Punpair="+Punpair);//~1Ac3I~//~@@@@R~
        	AG.aPartnerFrameIP.disconnect(Punpair);                //~1Ac3I~
        }                                                          //~1Ac3I~
  	}                                                              //~1Ac3I~
    //***********************************************************************
    //*from WDA
    //***********************************************************************
    public void unpaired()                                         //~1A87I~
    {                                                              //~1A87I~
        if (Dump.Y) Dump.println("IPConnectionWD:unpaired remotestatus="+AG.RemoteStatus);//~1A87R~//~@@@@R~
		if (AG.RemoteStatus==AG.RS_IPCONNECTED)                    //~1A87I~
            disconnectPartner();                                   //~1A87I~
  	}                                                              //~1A87I~
    //******************************************                   //~v101I~
    private void stopServer()                                      //~v101I~//~@@@@R~
    {                                                              //~v101I~
        if (Dump.Y) Dump.println("IPConnectionWD:stopServer server="+Utils.toString(S));//~@@@@I~
        if (S!=null)    //S:Server                                 //~@@@@R~
        {                                                          //~@@@@I~
        	S.cancel();                                           //~v101I~//~@@@@R~
            S=null;                                           //~@@@@I~
        }                                                          //~@@@@I~
//      acceptButton.setAction(R.string.AcceptIPConnectionWD);       //~v101I~//~1A84R~//~@@@@R~
  	}                                                              //~v101I~
    //******************************************                   //~v101I~
    private boolean/*dispose*/ startServer()                       //~v101R~
    {                                                              //~v101R~
        if (!wifiStat)                                             //~1A05I~
        {                                                          //~1A05I~
        	errWifi();                                             //~1A05I~
            return false;                                          //~1A05I~
        }                                                          //~1A05I~
//  	portNo=getPortNo();                                        //~v101I~//~1A84R~
//      connectionData=getServer();                                //~@@@@R~
//      if (connectionData==null)                                  //~@@@@R~
//          return false;                                          //~@@@@R~
        connectionData=getServer(true);                            //~@@@@I~
        if (S==null)                                               //~v101R~
//      	S=new Server(portNo,false);                            //~v101R~//~1AecR~
//      	S=new Server(portNo,false,false/*not NFC*/);           //~1AecI~//~@@@@R~
        	S=new Server(connectionData);                          //~@@@@I~
		return true; 	//dispose at boardquestion                 //~v101R~
  	}                                                              //~v101I~
    //******************************************                   //~v101I~
	private void errNoThread()                                     //~v101R~
    {                                                              //~v101I~
    	UView.showToast(R.string.ErrNoThread);                     //~v101R~//~@@@@R~
    }                                                              //~v101I~
    //******************************************                   //~v101I~
	private void errWifi()                                         //~1A05I~
    {                                                              //~1A05I~
    	if (Dump.Y) Dump.println("errWifi");                       //~1A05I~
    	UView.showToast(R.string.ErrWifi);                         //~1A05I~//~@@@@R~
    }                                                              //~1A05I~
    //******************************************                   //~1A05I~
	public static int getPortNo()                                        //~v101R~//~1A84R~
    {                                                              //~v101I~
        int n=Utils.getPreference(PKEY_SERVER_PORT,AG.DEFAULT_SERVER_PORT)+2;//~@@@@I~
        if (Dump.Y) Dump.println("IPConnectionWD getPortNo:"+n);     //~1A84I~//~@@@@R~
        return n;                                                  //~1A84I~
    }                                                              //~v101I~
//    //******************************************                   //~v101I~//~@@@@R~
//    private void afterDismiss(int Pwaiting)                        //~1A8kI~//~@@@@R~
//    {                                                              //~v101I~//~@@@@R~
//        if (Dump.Y) Dump.println("IPConnectionWD afterDismiss Pwaiting="+Integer.toHexString(Pwaiting));//~v101R~//~@@@@R~
//        if (Pwaiting==R.string.AcceptIPConnection)                 //~v101I~//~@@@@R~
//        {                                                          //~v101I~//~@@@@R~
//            String msg=AG.resource.getString(R.string.Msg_WaitingIPAccept,myIPAddr,portNo);//~v101I~//~@@@@R~
//            waitingResponse(R.string.Title_WaitingAccept,msg);     //~v101R~//~@@@@R~
//        }                                                          //~v101I~//~@@@@R~
//        else                                                       //~v101I~//~@@@@R~
//        if (Pwaiting==R.string.IPConnect)                          //~v101I~//~@@@@R~
//        {                                                          //~v101I~//~@@@@R~
//            String n="";                                           //~v101I~//~@@@@R~
//            int p=0;                                               //~v101I~//~@@@@R~
//            if (partner!=null) // try connecting to this partner server, if not trying already//~v101I~//~@@@@R~
//            {                                                      //~v101I~//~@@@@R~
//                n=partner.Name;                                    //~v101I~//~@@@@R~
//                p=partner.Port;                                    //~v101I~//~@@@@R~
//            }                                                      //~v101I~//~@@@@R~
//            String msg=AG.resource.getString(R.string.Msg_WaitingIPConnect,n,p);//~v101I~//~@@@@R~
//            waitingResponse(R.string.Title_WaitingConnect,msg);    //~v101R~//~@@@@R~
//            connectPartner();                                      //~v101I~//~@@@@R~
//        }                                                          //~v101I~//~@@@@R~
//        else                                                       //~v101I~//~@@@@R~
//        if (Pwaiting==R.string.IPDisConnect)                       //~v101I~//~@@@@R~
//            disconnectPartner();                                   //~v101I~//~@@@@R~
//        if (Dump.Y) Dump.println("IPConnectionWD afterDismiss exit");//~v101I~//~@@@@R~
//        AG.aIPConnectionWD=null;                                     //~1A6kI~//~1A84R~//~@@@@R~
//    }                                                              //~v101I~//~@@@@R~
    //******************************************                   //~@@@@I~
    private void showWaiting(int Pwaiting)                         //~@@@@I~
    {                                                              //~@@@@I~
    	if (Dump.Y) Dump.println("IPConnectionWD showWaiting Pwaiting="+Integer.toHexString(Pwaiting));//~@@@@I~
        if (Pwaiting==R.string.AcceptIPConnection)                 //~@@@@I~
        {                                                          //~@@@@I~
            String msg=AG.resource.getString(R.string.Msg_WaitingIPAccept,myIPAddr,portNo);//~@@@@I~
			waitingResponse(R.string.Title_WaitingAccept,msg);     //~@@@@I~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        if (Pwaiting==R.string.IPConnect)                          //~@@@@I~
        {                                                          //~@@@@I~
//            String n="";                                         //~@@@@R~
//            int p=0;                                             //~@@@@R~
//            if (partner!=null) // try connecting to this partner server, if not trying already//~@@@@R~
//            {                                                    //~@@@@R~
//                n=partner.Name;                                  //~@@@@R~
//                p=partner.Port;                                  //~@@@@R~
//            }                                                    //~@@@@R~
			String n=connectionDataConnect.NameRemote;             //~@@@@R~
            int p=portNo;                                          //~@@@@R~
            String msg=AG.resource.getString(R.string.Msg_WaitingIPConnect,n,p);//~@@@@I~
			waitingResponse(R.string.Title_WaitingConnect,msg);    //~@@@@I~
			connectPartner();                                      //~@@@@I~
        }                                                          //~@@@@I~
        else                                                       //~@@@@I~
        if (Pwaiting==R.string.IPDisConnect)                       //~@@@@I~
			disconnectPartner();                                   //~@@@@I~
    	if (Dump.Y) Dump.println("IPConnectionWD showWaiting exit");//~@@@@R~
//        AG.aIPConnectionWD=null;                                 //~@@@@R~
    }                                                              //~@@@@I~
    //******************************************                   //~v101I~
	private void waitingResponse(int Ptitleresid,int Pmsgresid)    //~v101R~
    {                                                              //~v101I~
//  	ProgDlg.showProgDlg(this/*ProgDlgI*/,false/*cancelCB*/,Ptitleresid,Pmsgresid,true/*cancelable*/);//~1A2jI~//~@@@@R~
    	ProgDlg.showProgDlg(Ptitleresid,Pmsgresid,true/*cancelable*/,this);//~@@@@I~
    }                                                              //~v101I~
    //******************************************                   //~v101I~
	private void waitingResponse(int Ptitleresid,String Pmsg)      //~v101I~
    {                                                              //~v101I~
//  	ProgDlg.showProgDlg(this/*ProgDlgI*/,false/*cancel CB*/,Ptitleresid,Pmsg,true/*cancelable*/);//~1A2jI~//~@@@@R~
    	ProgDlg.showProgDlg(Ptitleresid,Pmsg,true/*cancelable*/,this);//~@@@@I~
    }                                                              //~v101I~
    //******************************************                   //~v101I~
    //*reason:0:cancel,1:dismiss                                   //~v101I~
    //******************************************                   //~v101I~
    @Override                                                      //~v101I~
	public void onCancelProgDlg(int Preason)                       //~v101I~
    {                                                              //~v101I~
    	if (Dump.Y) Dump.println("onCancelProgDlgI reason="+Preason);//~v101I~
    }                                                              //~v101I~
    //***********************************************************************//~1A65I~//~1A84R~
    private void startWD()                                         //~1A65I~//~1A84R~
    {                                                              //~1A65I~//~1A84R~
        if (Dump.Y) Dump.println("IPConnectionWD:startWD");          //~1A65I~//~1A84R~//~@@@@R~
        try                                                        //~1A65I~//~1A84R~
        {                                                          //~1A65I~//~1A84R~
            aWDA=WDA.showDialog(this);                             //~1A65R~//~1A84R~
            wifiStat=aWDA.swWifiEnable;                            //~1A84I~
        }                                                          //~1A65I~//~1A84R~
        catch(Exception e)                                         //~1A65I~//~1A84R~
        {                                                          //~1A65I~//~1A84R~
            Dump.println(e,"IPConnectionWD:startWD");                //~1A65I~//~1A84R~//~@@@@R~
        }                                                          //~1A65I~//~1A84R~
    }                                                              //~1A65I~//~1A84R~
//    //***********************************************************************//~1A65I~//~@@@@R~
//    //*TODO required?                                            //~@@@@R~
//    //***********************************************************************//~@@@@R~
////  public void onCloseWDA()                                        //~1A67R~//~1A8kR~//~@@@@R~
//    private void onCloseWDA()                                      //~1A8kI~//~@@@@R~
//    {                                                              //~1A65I~//~@@@@R~
//        boolean owner=aWDA.WDAowner;                               //~1A65I~//~@@@@R~
//        if (Dump.Y) Dump.println("IPConnectionWD:closedWDA owner="+owner);//~1A65I~//~@@@@R~
//        try                                                        //~1A65I~//~@@@@R~
//        {                                                          //~1A65I~//~@@@@R~
//            String serveripa=aWDA.WDAipa;                           //~1A65I~//~@@@@R~
//            String peername=aWDA.WDApeer;                        //~1A65I~//~1A67R~//~@@@@R~
//            if (!owner)                                            //~1A65I~//~@@@@R~
//            {                                                      //~1A65I~//~@@@@R~
//                if (Dump.Y) Dump.println("IPConnectionWD:closedWD client server="+peername+",addr="+serveripa);//~1A65I~//~1A67R~//~@@@@R~
////              String partnername=partnerprefix+peername;       //~1A65I~//~1A67R~//~1A8aR~//~@@@@R~
//                String partnername=peername;                       //~1A8aI~//~@@@@R~
////              Partner p=new Partner(partnername,serveripa,portNo,Partner.PRIVATE);//~1A65I~//~1A84R~//~@@@@R~
//                partner=new Partner(partnername,serveripa,portNo,Partner.PRIVATE);//~1A84I~//~@@@@R~
////              updatePeer(p,partnerprefix);                       //~1A65I~//~1A84R~//~@@@@R~
//            }                                                      //~1A65I~//~@@@@R~
//            else                                                   //~1A67I~//~@@@@R~
//            {                                                      //~1A67I~//~@@@@R~
//                if (Dump.Y) Dump.println("IPConnectionWD:closedWD server server="+peername+",addr="+serveripa);//~1A67I~//~@@@@R~
////              String partnername=partnerprefix+peername;         //~1A67I~//~1A8aR~//~@@@@R~
//                String partnername=peername;                       //~1A8aI~//~@@@@R~
////              Partner p=new Partner(partnername,WDCLIENT_IPPREFIX+serveripa,portNo,Partner.PRIVATE);//~1A67I~//~1A84R~//~@@@@R~
////              partner=new Partner(partnername,WDCLIENT_IPPREFIX+serveripa,portNo,Partner.PRIVATE);//~1A84I~//~1AadR~//~@@@@R~
////              partner=new Partner(partnername,jagoclient.partner.IPConnectionWD.WDCLIENT_IPPREFIX+serveripa,portNo,Partner.PRIVATE);//~1AadI~//~@@@@R~
//                partner=new Partner(partnername,WDI.WDCLIENT_IPPREFIX+serveripa,portNo,Partner.PRIVATE);//~@@@@R~
////              updatePeer(p,partnerprefix);                       //~1A67I~//~1A84R~//~@@@@R~
//            }                                                      //~1A67I~//~@@@@R~
////          partner=p;                                             //~1A84R~//~@@@@R~
//        }                                                          //~1A65I~//~@@@@R~
//        catch(Exception e)                                         //~1A65I~//~@@@@R~
//        {                                                          //~1A65I~//~@@@@R~
//            Dump.println(e,"IPConnectionWD:closedWD");               //~1A65I~//~@@@@R~
//        }                                                          //~1A65I~//~@@@@R~
//    }                                                              //~1A65I~//~@@@@R~
    //***********************************************************************//~1A67I~
    //*from WDA                                                    //~1A84I~
    //***********************************************************************//~1A84I~
    public void dismissWDA()                                       //~1A67I~
    {                                                              //~1A67I~
        if (Dump.Y) Dump.println("IPConnectionWD:dismissWDA");     //~@@@@I~
//        displayIPAddress();                                        //~1A67I~//~1A84R~
//        displaySession();                                          //~1A67I~//~1A84R~
//      afterDismiss(waitingDialog);                               //~1A84I~//~@@@@R~
	    stopServer();                                              //~@@@@I~
    }                                                              //~1A67I~
    //***********************************************************************//~1A6DI~
    private void callWiFiSetting()                                 //~1A6DI~
    {                                                              //~1A6DI~
        if (Dump.Y) Dump.println("IPConnectionWD:callWiFiSetting");  //~1A6DI~//~@@@@R~
        AG.activity.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));//~1A6DI~
    }                                                              //~1A6DI~
    //***********************************************************************//~@@@@I~
    private void setStatus(boolean PswErr,int Pmsgid)              //~@@@@R~
    {                                                              //~@@@@I~
        AG.aWDA.deviceDetailFragment.setStatus(PswErr,Pmsgid);     //~@@@@R~
    }                                                              //~@@@@I~
////****************************************                         //~1A6tI~//~1A84I~//~@@@@R~
//    public static String getRemoteIPAddr(boolean Pnull)           //~1A6tR~//~1A84I~//~@@@@R~
//    {                                                              //~1A6tI~//~1A84I~//~@@@@R~
//        String ipa=null;                                           //~1A6tI~//~1A84I~//~@@@@R~
//        if (AG.RemoteStatus==AG.RS_IPCONNECTED)                    //~5219R~//~1A6tI~//~1A84I~//~@@@@R~
//            ipa=AG.RemoteInetAddress;              //~5219I~       //~1A6tI~//~1A84I~//~@@@@R~
//        if (Dump.Y) Dump.println("DeviceListFragment:getRemoteIPAddr:"+ipa);//~1A6tI~//~1A84I~//~@@@@R~
//        if (!Pnull)                                                //~1A6tI~//~1A84I~//~@@@@R~
//            if (ipa==null)                                         //~1A6tI~//~1A84I~//~@@@@R~
//                return "";                                         //~1A6tI~//~1A84I~//~@@@@R~
//        return ipa;                                                //~1A6tI~//~1A84I~//~@@@@R~
//    }                                                              //~1A6tI~//~1A84I~//~@@@@R~
}

