//*CID://+DATER~:                                   update#=   96; //~1AecR~//+9B03R~
//******************************************************************************************************************//~v107I~
//****************************************************************************//~@@@1I~
package com.btmtest.wifi;                                            //~1AecR~
                                                                   //~1AecI~
import com.btmtest.BT.BTIOServer;
import com.btmtest.utils.Dump;                                     //~1AecR~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~1AecI~

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class IPIOServer extends BTIOServer                         //~1AecR~
{                                                                  //~v107R~
    private Socket msgSocket;                                        //~v107I~//~1AecR~

	public IPIOServer(Socket Psocket,String Plocaldevicename,String Premotedevicename,int Pidxmember)//~v107R~//~1AecR~
    {                                                              //~v107I~
        localDeviceName=Plocaldevicename;                          //~v107R~
        remoteDeviceName=Premotedevicename;                     //~@@@2I~//~1AecR~
		msgSocket=Psocket;                                          //~v107R~//~1AecR~
		idxMember=Pidxmember;                                      //~1AecI~
		if (Dump.Y) Dump.println("IPIOServer constructor remote="+Premotedevicename+",local="+Plocaldevicename+",idxmember="+Pidxmember);//~v107R~//~1AecR~
	}
//****************************************************************************//~v107I~
//from BTMulti                                                     //~1AecI~
//****************************************************************************//~1AecI~
	public IPIOThread connect()                                      //~v107R~//~1AecR~
	{                                                              //~v107R~
		if (Dump.Y) Dump.println("IPIOServer connect");//~v107R~   //~1AecR~
    	IPIOThread t=IPIOThread.newIPIOThread(msgSocket,localDeviceName,remoteDeviceName,true/*swServer*/);//~1AecR~
        if (t!=null)                                               //~1AecI~
        {                                                          //~1AecI~
            t.idxMember=idxMember;                                 //~1AecM~
//      	AG.aMsgIO.openServer(t,idxMember);                     //~1AecR~
			t.start();                                             //~1AecR~
        }                                                          //~1AecI~
		return t;                                                  //~1AecR~
	}
}
