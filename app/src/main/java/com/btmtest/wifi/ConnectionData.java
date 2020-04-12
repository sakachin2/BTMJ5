//*CID://+@@@@R~:                             update#=  111;       //~@@@@I~
//*************************************************************************//~@@@@I~
package com.btmtest.wifi;                                           //~@@@@I~

import com.btmtest.utils.Utils;

public class ConnectionData                                        //~@@@@R~
{                                                                  //~@@@@R~
	public String Name;			//my device                        //~@@@@R~
	public String NameRemote; 	//remote, client for Server, owner for client//~@@@@R~
	public String strAddress;          //my address                       //~@@@@I~
	public String strOwnerAddress;	   //server for client         //+@@@@R~
	public String strClientAddress;    //client for server after connected//~@@@@R~
	public boolean isOwner;                                        //~@@@@I~
	public boolean isPaired;                                       //~@@@@I~
	public int portNo;                                             //~@@@@I~
	public IPIOThread IOthread;                                    //~@@@@R~
	public ConnectionData()                                        //~@@@@I~
	{                                                              //~@@@@I~
    }                                                              //~@@@@I~
	public String toString()                                       //~@@@@I~
	{                                                              //~@@@@I~
    	return "Name="+Name+",NameRemote="+NameRemote+",strAddress="+strAddress+",strOwnerAddress="+strOwnerAddress+",strClientAddress="+strClientAddress+",isOwner="+isOwner+",isPaired="+isPaired+",portNo="+portNo+",thread="+ Utils.toString(IOthread);//~@@@@R~
    }                                                              //~@@@@I~
}
