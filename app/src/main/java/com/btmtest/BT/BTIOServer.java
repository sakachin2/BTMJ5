//*CID://+1AecR~:                                   update#=   92; //~1AecR~
//******************************************************************************************************************//~v107I~
//****************************************************************************//~@@@1I~
package com.btmtest.BT;                                            //~1AecR~
                                                                   //~1AecI~
import com.btmtest.utils.Dump;                                     //~1AecR~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~1AecI~

import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;


public class BTIOServer                                            //~1AecR~
{                                                                  //~v107R~
    protected static final int MSGBUFFSZ=4096;                     //~1AecR~
    private BluetoothSocket msgSocket;                                        //~v107I~
    protected String localDeviceName,remoteDeviceName;                                //~v107R~//~1AecR~
    protected InputStream In;                                      //~1AecR~
    protected OutputStream Out;                                    //~1AecR~
    protected boolean swClose=false;                               //~1AecR~
    protected int idxMember;                                       //~1AecR~

	public BTIOServer()                                            //~1AecI~
    {                                                              //~1AecI~
		if (Dump.Y) Dump.println("BTIOServer default constructor");//~1AecI~
    }                                                              //~1AecI~
	public BTIOServer(BluetoothSocket Psocket,String Plocaldevicename,String Premotedevicename,int Pidxmember)//~v107R~//~1AecR~
    {                                                              //~v107I~
        localDeviceName=Plocaldevicename;                          //~v107R~
        remoteDeviceName=Premotedevicename;                     //~@@@2I~//~1AecR~
		msgSocket=Psocket;                                          //~v107R~//~1AecR~
		idxMember=Pidxmember;                                      //~1AecI~
		if (Dump.Y) Dump.println("BTIOServer constructor remote="+Premotedevicename+",local="+Plocaldevicename+",idxmember="+Pidxmember);//~v107R~//~1AecR~
	}
//****************************************************************************//~v107I~
//from BTMulti                                                     //~1AecI~
//****************************************************************************//~1AecI~
	public BTIOThread connect()                                      //~v107R~//~1AecR~
	{                                                              //~v107R~
		if (Dump.Y) Dump.println("BTIOServer connect");//~v107R~   //~1AecR~
    	BTIOThread t=BTIOThread.newBTIOThread(msgSocket,localDeviceName,remoteDeviceName,true/*swServer*/);//~1AecI~
        if (t!=null)                                               //~1AecI~
        {                                                          //~1AecI~
//      	AG.aMsgIO.openServer(t,idxMember);                     //~1AecR~
            t.idxMember=idxMember;                                 //+1AecI~
			t.start();                                             //~1AecI~
        }                                                          //~1AecI~
		return t;                                                  //~1AecR~
	}
}
