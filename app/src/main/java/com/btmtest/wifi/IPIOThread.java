//*CID://+var8R~:                                   update#=  259; //~var8R~
//*************************************************************************//~@002R~
//*Blutooth msg IO thread                                          //~@002I~
//*************************************************************************//~@002I~
//2022/09/24 var8 display profile icon                             //~var8I~
//@002:20181103 use enum                                           //~@002I~
//****************************************************************************//~@@@1I~
package com.btmtest.wifi;                                          //~1AecR~//~9723R~
                                                                   //~1AecI~
import com.btmtest.BT.BTIOThread;
import com.btmtest.R;
import com.btmtest.utils.Dump;                                     //~1AecR~
import com.btmtest.utils.Utils;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@002I~

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;


//**************************************************************   //~1AecI~
public class IPIOThread extends BTIOThread                             //~1AecR~//~9723R~
{                                                                  //~1AecR~
    public Socket ioSocket;                              //~1AecR~//~9723R~//~9B03R~
//  public int idxMember;	//set by IPIOServer/IPIOClient         //~9A02I~//~0220R~
    public int idxServer=-1;	//set by IPIOClient                //~9A09R~
//********************************************************************************//~9723I~
    public static IPIOThread newIPIOThread(Socket Psocket,String Plocalname,String Premotename,boolean Pserver)//~1AecI~//~9723R~
    {                                                              //~1AecI~
    	IPIOThread t=new IPIOThread(Psocket,Plocalname,Premotename,Pserver);//~1AecI~//~9723R~
        if (t.In==null || t.Out==null)                                 //~1AecI~
        	return null;                                           //~1AecI~
        return t;                                                  //~1AecI~
    }                                                              //~1AecI~
//********************************************************************************//~9723I~
    public IPIOThread(Socket Psocket,String Plocalname,String Premotename,boolean Pserver)//~1AecR~//~9723R~
    {                                                              //~1AecR~
    	ioSocket=Psocket; swServer=Pserver; remoteDeviceName=Premotename; localDeviceName=Plocalname;//~1AecR~
		try                                                        //~9723I~
        {                                                          //~9723I~
            if (Dump.Y) Dump.println("IPIOThread tcpNoDelay="+ioSocket.getTcpNoDelay());//~9B04I~
            ioSocket.setTcpNoDelay(true);   //Nagle algorithm Off  //~9B04I~
//  		In=new BufferedReader(new InputStreamReader(new DataInputStream(ioSocket.getInputStream())));//~1AecR~//~9723R~//+var8R~
    		In=ioSocket.getInputStream();                       //+var8I~
//  		Out=new PrintWriter(ioSocket.getOutputStream(),true/*auto flush*/);//~1AecI~//~9723R~//+var8R~
    		Out=ioSocket.getOutputStream();                        //+var8I~
//  	    InputStream=ioSocket.getInputStream();                 //~var8R~
//      	OutputStream=ioSocket.getOutputStream();               //~var8R~
        }                                                          //~9723I~
		catch (Exception e)                                        //~9723I~
		{                                                          //~v101R~//~9723I~
        	Dump.println(e,"IPIOThread.constructor");  //~9723I~   //~9725R~
		}                                                          //~9723I~
    }                                                              //~1AecR~
//***************************************************************  //~9723I~
	@Override                                                      //~9725I~
    protected void endThread()                                     //~9723I~
    {                                                              //~9723I~
        if (Dump.Y) Dump.println("IPIOThread.endThread===== Run Terminated swclose="+swClose+",remote="+remoteDeviceName+",thread="+this.toString());//~9723I~//~9725R~//~0116R~
//      new CloseConnection(ioSocket,In,Out).start();              //~9723R~//~9725R~
//      new CloseConnection(ioSocket,In,Out).doClose();            //~9725I~//~9B03R~
//      new CloseConnection(ioSocket,In,Out).start();              //~9B03I~//~9B04R~
//      new CloseConnection(ioSocket,null/*In*/,Out).start();      //~9B04I~//~var8R~
//      new CloseConnection(ioSocket,null/*In*/,Out,InputStream,OutputStream).start();//~var8R~
//      new CloseConnection(ioSocket,null/*In*/,Out,InputStream,OutputStream).start();//~var8R~
        new CloseConnection(ioSocket,In,Out).start();              //~var8I~
        In=null;                                                   //~9723I~
        Out=null;                                                  //~9723I~
//      InputStream=null;                                          //~var8R~
//      OutputStream=null;                                         //~var8R~
        AG.aIPMulti.connectionLost(swServer,localDeviceName,remoteDeviceName);              //~9723I~//~9729R~
    }                                                              //~9723I~
//***************************************************************  //~9726I~
//*Client writes name response for Bluetooth,IPIOThread will override//~9726I~
//***************************************************************  //~9726I~
	@Override   //BTIOThread                                       //~9726I~
	protected void outNameR(String PlocalDeviceName,String Pyourname,String PsyncDate)//~9726I~
    {                                                              //~9726I~
//      String txt=MSGR_NAME+PlocalDeviceName+MSG_SEP+Pyourname+MSG_SEP+PsyncDate+MSG_SEP+AG.LocalInetAddress;//~9726R~//~9B03R~
//      String txt=MSGR_NAME+PlocalDeviceName+MSG_SEP+Pyourname+MSG_SEP+PsyncDate+MSG_SEP+AG.LocalInetAddress+MSG_SEP+AG.aIPMulti.thisDeviceMacAddr;//~9B03I~//~0107R~
        boolean swReconnecting=WDA.isReconnecting();               //~0107I~
        String txt=MSGR_NAME+PlocalDeviceName+MSG_SEP+Pyourname+MSG_SEP+PsyncDate+MSG_SEP+AG.LocalInetAddress+MSG_SEP+AG.aIPMulti.thisDeviceMacAddr+MSG_SEP+(swReconnecting ? "1" : "0");//~0107I~
		if (Dump.Y) Dump.println("IPIOThread.outNameR txt="+txt);  //~9726I~
		out(txt);                                                  //~9726I~
	}                                                              //~9726I~
//***************************************************************  //~9726I~
//*On Server                                                       //~9726I~
//***************************************************************  //~9726I~
	@Override   //BTIOThread                                       //~9726I~
	protected void receivedNameR(String Pmsg)                     //~9726R~
    {                                                              //~9726I~
        String[] strs=BTIOThread.parse(Pmsg);                      //~9726I~
        remoteDeviceName=strs[0];                                  //~9726I~
		if (Dump.Y) Dump.println("IPIOThread.receivedNameR msg="+Pmsg+",remote="+remoteDeviceName);//~9726I~//~9728R~
        AG.aIPMulti.receivedNameR(this/*Thread*/,strs);            //~9726I~
	}                                                              //~9726I~
//***************************************************************  //~9815I~
//*On Client                                                       //~9815I~
//***************************************************************  //~9815I~
	@Override   //BTIOThread                                       //~9815I~
	protected void receivedNameAdd(String Pmsg)                    //~9815I~
    {                                                              //~9815I~
        String[] strs=BTIOThread.parse(Pmsg);                      //~9815I~
		if (Dump.Y) Dump.println("IPIOThread.receivedNameAdd msg="+Pmsg+",parse="+Arrays.toString(strs));//~9815I~
        AG.aIPMulti.receivedNameAdd(this/*Thread*/,strs);          //~9815I~
	}                                                              //~9815I~
	@Override   //BTIOThread                                       //~9B07I~
	protected void receivedNameDel(String Pmsg)                    //~9B07I~
    {                                                              //~9B07I~
		if (Dump.Y) Dump.println("IPIOThread.receivedNameDel msg="+Pmsg);//~9B07I~
        AG.aIPMulti.receivedNameDel(this/*Thread*/,Pmsg);           //~9B07I~
	}                                                              //~9B07I~
//***************************************************************  //~9728I~
	public void doClose()                                          //~9728I~
    {                                                              //~9728I~
		if (Dump.Y) Dump.println("IPIOThread.doClose");            //~9728I~
        endThread();                                               //~9728I~
    }                                                              //~9728I~
//***************************************************************  //~9B04I~
	public void closeSocket()                                      //~9B04I~
    {                                                              //~9B04I~
		if (Dump.Y) Dump.println("IPIOThread.closeSocket ioSocket="+ Utils.toString(ioSocket));//~9B04I~
        if (ioSocket!=null)                                        //~9B04I~
			try                                                    //~9B04I~
			{                                                      //~9B04I~
        		ioSocket.close();                                  //~9B04R~
            }                                                      //~9B04I~
            catch (Exception e)                                    //~9B04I~
            {                                                      //~9B04I~
                Dump.println(e,"IPIOThread.closeSocket");          //~9B04I~
            }                                                      //~9B04I~
        if (Dump.Y) Dump.println("IPIOThread.closeSocket end");    //~9B04I~
    }                                                              //~9B04I~
}                                                                  //~1AecI~
