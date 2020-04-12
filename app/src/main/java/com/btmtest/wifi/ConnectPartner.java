//*CID://+@@@@R~:                             update#=    4;       //~v101I~//~@@@@R~
//*******************************************************************************//~v101I~
//On subthread do new Sockt(connect to server)                     //~@@@@R~
//*******************************************************************************//~v101I~
package com.btmtest.wifi;                                          //~@@@@R~

import com.btmtest.utils.Dump;

import static com.btmtest.StaticVars.AG;                           //~@@@@I~
//*******************************************************************************//~@@@@I~
public class ConnectPartner extends Thread
{                                                                  //~@@@@R~
	PartnerFrame PF;
    ConnectionData connectionData;                                 //~@@@@I~
	public ConnectPartner(PartnerFrame Ppf)                        //~@@@@R~
	{                                                              //~@@@@R~
	 	PF=Ppf;                                                    //~@@@@I~
		start();
	}
	public void run ()
	{                                                              //~@@@@R~
    	if (Dump.Y) Dump.println("ConnectPartner.run");            //+@@@@I~
		PF.connect();                                              //~@@@@R~
	}
}

