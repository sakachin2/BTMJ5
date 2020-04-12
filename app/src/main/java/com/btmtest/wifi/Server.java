//*CID://+DATER~:                             update#=   58;       //~1AecR~//~@@@@R~//+0108R~
//*************************************************************************//~v107I~
//1Aec 2015/07/26 set connection type for Server                   //~1AecI~
//1A90 2015/04/18 (like as 1A84)WiFiDirect from Top panel          //~1A90I~
//1A8ck2015/03/01 extends PartnerFrame/PartnerThread to wifidirect //~1A8cI~
//101a 2013/01/30 IP connection                                    //~v101I~
//1071:121204 partner connection using Bluetooth SPP               //~v107I~
//*************************************************************************//~v107I~
package com.btmtest.wifi;                                               //~v@@@I~//~9719I~//~@@@@I~

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~//~@@@@I~

import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;

import java.net.ServerSocket;
import java.net.Socket;

/**
This is the server thread for partner connections. If anyone connects
to the server, a new PartnerFrame will open to handle the connection.
If the server starts, it will open a new PartnerServerThread, which
checks for datagrams that announce open partners.
*/

public class Server extends Thread
{	int Port;
	boolean Public;
//    static public PartnerServerThread PST=null;                  //~v107R~//~v101R~
//    ServerSocket SS;                                             //~v101R~
//  static ServerSocket SS;                                        //~v101I~//~@@@@R~
    ServerSocket SS;                                               //~@@@@I~
//  static boolean swCancel;                                               //~v101I~//~@@@@R~
    boolean swCancel;                                              //~@@@@I~
//    private boolean swNFC;                                         //~1AecI~//~@@@@R~
	ConnectionData connectionData;                                 //~@@@@I~
	/**
	param p the server port                                        //~@@@@R~
	param publ server is public or not                             //~@@@@R~
	*/
	public Server (){}  //for extended(Asgts/jagoclient/partner/Server//~v107R~//~v101R~
//    public Server (int p, boolean publ)                          //~@@@@R~
//    {   Port=p; Public=publ;                                     //~@@@@R~
//        start();                                                 //~@@@@R~
//    }                                                            //~@@@@R~
//    public Server (int p, boolean publ,boolean Pnfc)               //~1AecI~//~@@@@R~
//    {                                                              //~1AecI~//~@@@@R~
//        swNFC=Pnfc;                                                //~1AecI~//~@@@@R~
//        Port=p; Public=publ;                                       //~1AecI~//~@@@@R~
//        start();                                                   //~1AecI~//~@@@@R~
//    }                                                              //~1AecI~//~@@@@R~
    public Server(ConnectionData PconnectionData)                  //~@@@@I~
    {                                                              //~@@@@I~
        connectionData=PconnectionData;                            //~@@@@R~
        Port=connectionData.portNo;                                //~@@@@I~
       	AG.RemoteStatusAccept=AG.RS_IPLISTENING;                   //~@@@@I~
        start();                                                   //~@@@@I~
    }                                                              //~@@@@I~
	public void run ()
    {                                                              //~v107I~
    	if (Dump.Y) Dump.println("Server:run===== PortNo="+Port+",this="+this.toString());//~v101R~
        try                                                      //~v107R~//~v101R~
        {                                                          //~@@@@R~
            SS=new ServerSocket(Port);                             //~@@@@I~
        	infoListening();                                       //~v107I~//~v101M~
            while (true)                                         //~v107R~//~v101R~//~@@@@R~
            {                                                      //~v101R~
//          	AG.RemoteStatusAccept=AG.RS_IPLISTENING;           //~v101I~//~@@@@R~
                Socket S=SS.accept();                              //~v101I~
//  	        AG.RemoteStatusAccept=0;                           //~v101I~//~@@@@R~
//                PartnerFrame cf=                                 //~v107R~//~v101R~//~1AecR~
//                    new PartnerFrame(Global.resourceString("Server"),true, //layout.partnerframe//~1AecR~
//                    (swNFC ? jagoclient.partner.IPConnection.NFC_SERVER : jagoclient.partner.IPConnection.WD_SERVER));//~1AecR~
//                cf.open(S);                                      //~v107R~//~v101R~//~1AecR~
//              SS.close();                                         //~v101I~//~1AecR~//~@@@@R~
//              SS=null;                                           //~v101I~//~1AecR~//~@@@@R~
//  			UView.showToast("Server.run Accepted inetAddress="+S.getInetAddress().toString());//~1AecI~//~@@@@R~
				PartnerFrame.dismissWaitingDialog();               //~@@@@I~
                connectionData.isOwner=true;                       //~@@@@I~
                connectionData.strClientAddress=S.getInetAddress().getHostAddress();//~@@@@R~
                connectionData.NameRemote=connectionData.strClientAddress;  //temporally avoid null until initial msg send/receive//~@@@@I~
    			Dump.println("Server.run Accepted inetAddress="+S.getInetAddress().toString()+",clientAddress="+connectionData.strClientAddress);//~@@@@I~
                AG.aIPMulti.onConnected(S,connectionData,false/*swClient*/);//~@@@@R~
            }                                                    //~v107R~//~v101R~
        }                                                        //~v107R~//~v101R~
        catch (Exception e)                                      //~v107R~//~v101R~
        {                                                          //~v101R~
//          AG.RemoteStatusAccept=0;                               //~v101I~//~@@@@R~
        	if (!swCancel)                                         //~v101I~
            {                                                      //+0108I~
			   Dump.println(e,"Server Error");//@@@@ add e         //~v101I~
               WDA.acceptFailed();                                 //+0108I~
            }                                                      //+0108I~
            else                                                   //~v101I~
			   if (Dump.Y) Dump.println("Server Accept canceled");//@@@@ add e//~v101I~
        }                                                        //~v107R~//~v101R~
        AG.RemoteStatusAccept=0;                                   //~@@@@I~
//      PartnerFrame.dismissWaitingDialog();                       //~@@@@R~
    	if (Dump.Y) Dump.println("Server:run===== end");           //~v101I~
	}
	
	/**
	This is called, when the server is opened. It will announce
	the opening to known servers by a datagram.
	*/
	/**
	This is called, when the server is closed. It will announce
	the closing to known servers by a datagram.
	*/
    public void close ()                                    //~v101R~
//  {	if (!Public) return;                                      //~v107R~//~v101R~
    {                                                              //~v101I~
    	if (SS!=null)                                              //~v101I~
        try                                                        //~v101I~
        {                                                          //~v101I~
        	SS.close();                                            //~v101I~
        }                                                          //~v101I~
        catch (Exception e)                                        //~v101I~
        {                                                          //~v101I~
			Dump.println(e,"Server:SS close");                     //~v101I~
        }                                                          //~v101I~
    }                                                              //~v101R~
//  public static void cancel()                                    //~v101I~//~@@@@R~
    public void cancel()                                           //~@@@@I~
    {                                                              //~v101I~
		if (Dump.Y) Dump.println("Server.cancel");                 //+0108I~
    	if (SS!=null)                                              //~v101I~
        {                                                          //~v101I~
        	swCancel=true;                                         //~v101I~
            try                                                        //~v101I~
            {                                                          //~v101I~
            	SS.close();                                            //~v101I~
			    infoStopListening();                                //~v101I~
            }                                                          //~v101I~
            catch (Exception e)                                        //~v101I~
            {                                                          //~v101I~
    			Dump.println(e,"Server:SS close");                     //~v101I~
            }                                                          //~v101I~
        	swCancel=false;                                        //~v101I~
        }                                                          //~v101I~
	}                                                              //~v101I~
    private void infoListening()                                   //~v107I~//~v101M~
    {                                                              //~v107I~//~v101M~
//        UView.showToast(R.string.InfoIPListening);                 //~v107I~//~v101R~//~@@@@R~
	}                                                              //~v107I~//~v101M~
    private static void infoStopListening()                               //~v101I~
    {                                                              //~v101I~
    	UView.showToast(R.string.InfoIPStopListening);             //~v101I~//~@@@@R~
	}                                                              //~v101I~
}
