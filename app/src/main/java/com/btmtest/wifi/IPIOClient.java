//*CID://+1AecR~:                                   update#=   92; //~1AecR~
//******************************************************************************************************************//~v107I~
//****************************************************************************//~@@@1I~
package com.btmtest.wifi;                                            //~1AecR~
                                                                   //~1AecI~
import com.btmtest.BT.BTIOServer;
import com.btmtest.utils.Dump;                                     //~1AecR~

import java.net.Socket;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~1AecI~


public class IPIOClient extends BTIOServer                         //~1AecR~
{                                                                  //~v107R~
    private Socket msgSocket;                                      //~1AecR~
	public IPIOClient(Socket Psocket, String Plocaldevicename,String Premotedevicename,int Pidxmember)//~v107R~//~1AecR~
    {                                                              //~v107I~
        localDeviceName=Plocaldevicename;                          //~v107R~
        remoteDeviceName=Premotedevicename;                     //~@@@2I~//~1AecR~
		msgSocket=Psocket;                                          //~v107R~//~1AecR~
        idxMember=Pidxmember;                                      //~1AecI~
		if (Dump.Y) Dump.println("IPIOClient constructor remote="+Premotedevicename+",local="+Plocaldevicename+",idxmember="+Pidxmember);//~v107R~//~1AecR~
	}
//****************************************************************************//~v107I~
	public IPIOThread connect()                                      //~v107R~//~1AecR~
	{                                                              //~v107R~
		if (Dump.Y) Dump.println("IPIOClient connect");//~v107R~   //~1AecR~
    	IPIOThread t=IPIOThread.newIPIOThread(msgSocket,localDeviceName,remoteDeviceName,false/*swServer*/);               //~@@@@I~//~1AecR~
        if (t!=null)                                               //~1AecI~
        {                                                          //~1AecI~
            t.idxMember=idxMember;                                 //~1AecI~
	        t.idxServer=AG.aBTMulti.BTGroup.idxServer;             //~1AecI~
			if (Dump.Y) Dump.println("IPIOClient.connect idxServer="+t.idxServer+",idxMember="+idxMember);//~1AecI~
//          AG.aMsgIO.openClient(t,idxMember);                     //+1AecR~
			t.start();                                             //~1AecR~
        }                                                          //~1AecI~
		return t;                                                  //~1AecR~
	}
}
