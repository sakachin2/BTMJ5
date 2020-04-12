//*CID://+@@@@R~:                                   update#=  198; //~1AecR~//~@@@@R~
//****************************************************************************//~@@@1I~
//*TODO test dummy class                                           //~@@@@R~
//****************************************************************************//~@@@1I~
package com.btmtest.wifi;                                               //~v@@@I~//~9719I~//~@@@@I~

import com.btmtest.utils.Dump;
import com.btmtest.utils.ProgDlg;
import com.btmtest.utils.StringParser;
import com.btmtest.utils.URunnable;
import com.btmtest.utils.Utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.btmtest.StaticVars.AG;                           //~@@@@I~

//import wifidirect.DialogNFC;


/**
The partner frame contains a simple chat dialog and a button
to start a game or restore an old game. This class contains an
interpreters for the partner commands.
*/

//public class PartnerFrame extends CloseFrame                     //~1A8cR~
public class PartnerFrame                                          //~@@@@R~
{	                                                               //~v107I~
    public  static final int NFC_SERVER=1;    //identify connection type//~1A6BI~//~1A8oI~
    public  static final int NFC_CLIENT=2;                         //~1A6BI~//~1A8oI~
    private static final String CONTYPE_PREFIX=";";                //~1A6BI~
	public static final String CONN_TITLE_IP="IP ";                //~1A8iI~
	public static final String CONN_TITLE_WD="WD ";                //~1A8iI~
	public static final String CONN_TITLE_BT="BT ";                //~1A8iI~
	public static final String CONN_TITLE_NFC="NFC ";              //~1A8iI~
//    public static int test=2;                                      //~@@@9R~//~1A6BR~
//    protected BufferedReader In;                                   //~v107I~//~1A8cR~//~@@@@R~
//    protected PrintWriter Out;                                      //~v107I~//~1A8cR~//~@@@@R~
	Socket Server;
//    public PartnerThread PT;                                    //~v107I~//~@@@2R~//~1A8cR~
//    public PartnerGoFrame PGF;                                   //~1A8cR~
//    boolean Serving;                                             //~1A8cR~
//    boolean Block;                                               //~1A8cR~
//    String Dir;                                                  //~1A8cR~
//    String PartnerName;                                            //~@@@2I~//~1A8cR~
    boolean GameRequester;                                         //~@@@2R~
    //    private OutputStream OS;                                       //~@@@9R~//~1A6BR~
//    public static InputStream IS;                                   //~@@@9I~//~1A6BR~
//  public int connectionType;    //defined on super              //~1A6BI~//~1Ae4R~
    public ConnectionData connectionData;                          //~@@@@R~
//*******************************************************************************//~@@@@I~
//*from IPConnectionWD.connectPartner                              //~@@@@I~
//*******************************************************************************//~@@@@I~
	public PartnerFrame (ConnectionData PconnectionData)           //~@@@@I~
    {                                                              //~@@@@I~
        AG.aPartnerFrameIP=this;                                   //~1A8oI~//~@@@@I~
    	connectionData=PconnectionData;                            //~@@@@I~
	}                                                              //~@@@@I~
//*******************************************************************************//~@@@@I~
//*from ConnectPartner on subthread                                //~@@@@I~
//*******************************************************************************//~@@@@I~
	public boolean connect()                                       //~@@@@I~
	{                                                              //~@@@@I~
		if (Dump.Y) Dump.println("PartnerFrame:connect  Starting partner connection");//~@@@@I~
		return connect(connectionData.strOwnerAddress,connectionData.portNo);//~@@@@R~
	}                                                              //~@@@@M~
	public boolean connect(String s, int p)                        //~@@@@R~
	{                                                              //~@@@@R~
		if (Dump.Y) Dump.println("PartnerFrame:connect s="+s+",p="+p);//~@@@@I~
		try                                                        //~@@@@I~
		{                                                          //~@@@@I~
			Server=new Socket(s,p);	//do connect                   //~@@@@I~
            Server.setTcpNoDelay(true);                            //~v101R~//~@@@@I~
//            Out=new PrintWriter(                                 //~@@@@R~
//                new DataOutputStream(Server.getOutputStream()),true);//~@@@@R~
//            In=new BufferedReader(new InputStreamReader(         //~@@@@R~
//                new DataInputStream(Server.getInputStream())));  //~@@@@R~
		}                                                          //~@@@@I~
		catch (Exception e)                                        //~@@@@I~
		{                                                          //~v101R~//~@@@@I~
        	if (Dump.Y) Dump.println("PartnerFrame:connect Server="+s+",port="+p+",cause="+e.getCause()+",msg="+e.getMessage());//~1B1jR~//~1A8cI~//~@@@@R~
//          Dump.println(e,"PartnerFrame:connect "+s+",port="+p); no toast//~@@@@R~
			AG.aIPMulti.onConnectionFailed(connectionData);        //~@@@@I~
			return false;                                          //~v101I~//~@@@@I~
		}                                                          //~@@@@I~
//TODO    PT=new PartnerThread(In,Out,null,null,this);               //~v108I~//~@@@@R~
//        PT.start();                                              //~@@@@R~
//        out("@@name "+                                             //~@@@2R~//~@@@@R~
//            AG.YourName+CONTYPE_PREFIX+connectionType);            //~1A6BR~//~@@@@R~
//      AG.RemoteStatus=AG.RS_IPCONNECTED;                                 //~@@@2I~//~@@@@R~
        getHostAddr(Server);                                       //~@@@2I~//~@@@@R~
		dismissWaitingDialog();                                    //~1A84I~//~1A90I~//~@@@@I~
		AG.aIPMulti.onConnected(Server,connectionData,true/*swClient*/);//~@@@@I~
        if (Dump.Y) Dump.println("PartnetFrame client after open");//~1Ac0I~//~@@@@I~
		return true;                                               //~@@@@I~
	}                                                              //~@@@@I~
//***************************************************************  //~@@@2I~
//  public void disconnect()	//from IPConnection                //~@@@2I~//~@@@@R~
    public void disconnect(ConnectionData PconnectionData)	//from IPConnection//~@@@@I~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("PartnerFrame Disconnect connectiondata="+PconnectionData.toString());       //~@@@2I~//~@@@@R~
		PconnectionData.IOthread.doClose();	//CloseConnection	                       //~@@@2I~//~@@@@R~
    }                                                              //~@@@2I~
//***************************************************************  //~1Ac3I~
	public void disconnect(boolean Punpair)	//from IPConnection    //~1Ac3I~
    {                                                              //~1Ac3I~
        if (Dump.Y) Dump.println("PartnerFrame Disconnect Punpair="+Punpair);//+@@@@I~
    }                                                              //~1Ac3I~
//***************************************************************  //~@@@2I~
	public static void dismissWaitingDialog()                      //~@@@2R~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("PartnerFrame DismissWaitiingDialog");//~@@@2R~
//  	ProgDlg.dismiss();                                         //~@@@2I~//~@@@@R~
    	ProgDlg.dismissCurrent();                                  //~@@@@I~
    }                                                              //~@@@2I~
//***************************************************************  //~@@@2I~//~1A8oI~
	private void getHostAddr(Socket Psocket)                       //~@@@2I~//~1A8oI~//~@@@@I~
    {                                                              //~@@@2I~//~1A8oI~//~@@@@I~
//      AG.RemoteInetAddress= Utils.getRemoteIPAddr(Psocket,null);//~1A8fI~//~1A8oI~//~@@@@R~
//      AG.LocalInetAddress=Utils.getLocalIPAddr(Psocket,null);//~1A8fR~//~1A8oI~//~@@@@R~
        AG.ServerInetAddress= Utils.getRemoteIPAddr(Psocket,null); //~@@@@I~
        AG.LocalInetAddress=Utils.getLocalIPAddr(Psocket,null);    //~@@@@I~
	    if (Dump.Y) Dump.println("PartnerFrame:getHostAddr remote="+AG.ServerInetAddress+",local="+AG.LocalInetAddress);//~1A8fI~//~1A8oI~//~@@@@R~
    }                                                              //~@@@2I~//~1A8oI~//~@@@@I~
////***************************************************************  //~1Ac0I~//~@@@@R~
//    public void doclose ()                                       //~@@@@R~
//    {                                                              //~v108I~//~@@@@R~
//        if (Dump.Y) Dump.println("wifidirect.PartnerFrame:doclose()");        //~1A6BI~//~1A8cI~//~@@@@R~
//        new CloseConnection(Server,In,null/*out*/);                          //~1Ac0R~//~@@@@R~
//    }                                                            //~@@@@R~
}                                                                  //~1A8iR~
