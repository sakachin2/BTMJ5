package com.btmtest.wifi;                                          //~9723R~

import com.btmtest.utils.Dump;

import java.net.*;
import java.io.*;

public class CloseConnection extends Thread
{                                                                  //~9723R~
	Socket S;                                                      //~9723I~
	BufferedReader In;
	PrintWriter Out;                                               //~9723I~
	public CloseConnection (Socket s, BufferedReader in, PrintWriter out)//~9723R~
	{                                                              //~9723R~
        if (Dump.Y) Dump.println("CloseConnection wifi constructor");//~9725I~
		S=s; In=in; Out=out;                                       //~9723R~
//  	start();                                                   //~9B03R~
	}
	public void run()                                              //~9725R~
	{                                                              //~9725R~
        if (Dump.Y) Dump.println("CloseConnection wifi run");    //~9725I~
        doClose();                                                 //~9725I~
	}
	public void doClose()                                          //~9725I~
	{                                                              //~9725I~
        if (Dump.Y) Dump.println("CloseConnection wifi doclose");//~9725I~
		try                                                        //~9725I~
		{                                                          //~9725I~
    		if (Out!=null) Out.close();	//close all related to the socket                            //~9725I~//~9728I~
	        if (Dump.Y) Dump.println("CloseConnection Out Closed");//~9728M~
			if (S!=null) S.close();                                //~9725I~//~9728M~
	        if (Dump.Y) Dump.println("CloseConnection Socket Closed");//~9728M~
			if (In!=null) In.close();                              //~9725I~//~9728M~//+9B04M~
	        if (Dump.Y) Dump.println("CloseConnection In Closed"); //~9728I~//+9B04M~
		}                                                          //~9725I~
		catch (Exception e)                                        //~9725I~
		{                                                          //~9725I~
        	Dump.println(e,"CloseConnection");                     //~9725I~
		}                                                          //~9725I~
        if (Dump.Y) Dump.println("CloseConnection wifi doclose end");//~9728I~
	}                                                              //~9725I~
}