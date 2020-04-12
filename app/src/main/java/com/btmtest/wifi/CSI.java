//*CID://+DATER~:                             update#=  115;       //~1Ac4R~//~1Ad2R~//~9712R~
//*************************************************************************//~1A65I~
//*ConnectivityManager Interface for ConnectivityService changed status//~9B05I~
//*SutdownReceiver                                                 //+9B06I~
//*************************************************************************//~9B05I~
package com.btmtest.wifi;                                               //~v@@@I~//~9719I~

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static com.btmtest.StaticVars.AG;                           //~@@@@M~//~9719I~
import static com.btmtest.game.GConst.*;

import com.btmtest.BT.BTIOThread;
import com.btmtest.BT.Members;
import com.btmtest.game.GC;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

public class CSI                                                   //~9719R~//~9B05R~
				extends BroadcastReceiver                          //~9B05I~
{                                                                  //~1A65I~
    private ConnectivityManager CM;                                //~9B05R~
    BroadcastReceiver receiver;                                    //~9B05I~//+9B06R~
    BroadcastReceiver receiverShutDown,receiverShutDown0;          //+9B06I~
                                                                   //~9722I~
    //***********************************************************************//~v101I~//~9719I~
    public CSI()                                                   //~9722I~//~9B05R~
    {                                                              //~9722I~
        AG.aCSI=this;                                              //~9728I~//~9B05R~
        CM=(ConnectivityManager)AG.context.getSystemService(Context.CONNECTIVITY_SERVICE);//~9B05R~
        receiverShutDown0=new ReceiverShutDown();                   //+9B06I~
    }                                                              //~9722I~
    //***********************************************************************//~9B05I~
    //Wifi/Bluetooth/Phone online                                  //~9B05I~
    //***********************************************************************//~9B05I~
    public static boolean isOnline()                                      //~9B05I~
    {                                                              //~9B05I~
        if (Dump.Y) Dump.println("CSI:isOnline");                  //~9B05I~
        boolean rc=false;                                          //~9B05I~
        NetworkInfo ni=AG.aCSI.CM.getActiveNetworkInfo();                  //~9B05I~
        if (Dump.Y) Dump.println("CSI:isOnline getActiveNetworkInfo="+ Utils.toString(ni));//~9B05I~
        if (ni!=null)                                              //~9B05I~
        	if (ni.isConnected())                                  //~9B05I~
            	rc=true;                                           //~9B05I~
        if (Dump.Y) Dump.println("CSI:isOnline rc="+rc);           //~9B05I~
		return rc;                                                 //~9B05I~
    }                                                              //~9B05I~
    //***********************************************************************//~9722I~
    public static boolean isOnlineWifi()                           //~9B05R~
    {                                                              //~v101I~//~9719I~
        if (Dump.Y) Dump.println("CSI:isOnlineWifi");              //~9B05I~
        boolean rc=false;                                          //~9B05I~
        NetworkInfo ni=AG.aCSI.CM.getActiveNetworkInfo();                  //~9B05I~
        if (Dump.Y) Dump.println("CSI:isOnlineWifi getActiveNetworkInfo="+Utils.toString(ni));//~9B05I~
        if (ni!=null)                                              //~9B05I~
        	if (ni.isConnected())                                  //~9B05I~
            {                                                      //~9B05I~
	        	int type=ni.getType();                             //~9B05I~
		        if (Dump.Y) Dump.println("CSI:isOnlineWifi type="+type);//~9B05I~
                if (type==ConnectivityManager.TYPE_WIFI)             //~9B05I~
	            	rc=true;                                       //~9B05I~
            }                                                      //~9B05I~
        if (Dump.Y) Dump.println("CSI:isOnlineWifi rc="+rc);       //~9B05I~
		return rc;                                                 //~9B05I~
    }                                                              //~1A90I~//~9719I~
//*****************************************************************************//~9B05I~
	public void onResume()                                         //~9B05I~
    {                                                              //~9B05I~
		if (Dump.Y) Dump.println("CSI:onResume");                  //~9B05I~
        registerReceiver();                                        //~9B05I~
        registerReceiverShutDown();                                 //+9B06I~
    }                                                              //~9B05I~
//*****************************************************************************//~9B05I~
	public void onPause()                                          //~9B05I~
    {                                                              //~9B05I~
		if (Dump.Y) Dump.println("CSI:onPause");                   //~9B05I~
        unregisterReceiver();                                      //~9B05I~
        unregisterReceiverShutDown();                              //+9B06I~
    }                                                              //~9B05I~
//*****************************************************************************//~9B05I~
	public void onDestroy()                                        //~9B05I~
    {                                                              //~9B05I~
		if (Dump.Y) Dump.println("CSI:onDestroy");                 //~9B05I~
        unregisterReceiver();                                      //~9B05I~
        unregisterReceiverShutDown();                              //+9B06I~
    }                                                              //~9B05I~
//*****************************************************************************//~9B05I~
	public void registerReceiver()                                 //~9B05I~
    {                                                              //~9B05I~
		if (Dump.Y) Dump.println("CSI:registerReceiver");          //~9B05I~
        if (receiver==null)                                        //~9B05I~
        {                                                          //~9B05I~
            IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);//~9B05R~
            AG.activity.registerReceiver(this,filter);             //~9B05R~
            receiver=this;                                         //~9B05R~
        }                                                          //~9B05I~
    }                                                              //~9B05I~
//*****************************************************************************//~9B05I~
	public void unregisterReceiver()                               //~9B05I~
    {                                                              //~9B05I~
		if (Dump.Y) Dump.println("CSI:unregisterReceiver");        //~9B05I~
        if (receiver!=null)                                        //~9B05I~
        {                                                          //~9B05I~
        	AG.activity.unregisterReceiver(this);                              //~9B05I~
        	receiver=null;                                         //~9B05I~
        }                                                          //~9B05I~
    }                                                              //~9B05I~
//*****************************************************************************//~1A8gI~//~9719I~
	@Override                                                      //~9B05I~
    public void onReceive(Context Pcontext, Intent Pintent)         //~9B05R~
    {                                                              //~1A8gI~//~9719I~
		if (Dump.Y) Dump.println("CSI:onReceive intent="+Pintent.toString());//~9B05I~
        NetworkInfo ni=CM.getActiveNetworkInfo();                  //~9B05R~
		if (Dump.Y) Dump.println("CSI:onReceive networkInfo="+Utils.toString(ni));//~9B05R~
  	}                                                              //~1A8gI~//~9719I~
//*****************************************************************************//+9B06I~
	public void registerReceiverShutDown()                          //+9B06I~
    {                                                              //+9B06I~
		if (Dump.Y) Dump.println("CSI:registerReceiverSutDown");   //+9B06I~
        if (receiverShutDown==null)                                //+9B06I~
        {                                                          //+9B06I~
            IntentFilter filter=new IntentFilter(Intent.ACTION_SHUTDOWN);//+9B06I~
            AG.activity.registerReceiver(receiverShutDown0,filter);//+9B06I~
            receiverShutDown=receiverShutDown0;                    //+9B06I~
        }                                                          //+9B06I~
    }                                                              //+9B06I~
//*****************************************************************************//+9B06I~
	public void unregisterReceiverShutDown()                       //+9B06I~
    {                                                              //+9B06I~
		if (Dump.Y) Dump.println("CSI:unregisterReceiverShutDown");//+9B06I~
        if (receiverShutDown!=null)                                //+9B06I~
        {                                                          //+9B06I~
        	AG.activity.unregisterReceiver(receiverShutDown);      //+9B06I~
        	receiverShutDown=null;                                 //+9B06I~
        }                                                          //+9B06I~
    }                                                              //+9B06I~
//*****************************************************************************//+9B06I~
	class ReceiverShutDown                                         //+9B06I~
				extends BroadcastReceiver                          //+9B06I~
    {                                                              //+9B06I~
		@Override                                                  //+9B06I~
        public void onReceive(Context Pcontext, Intent Pintent)    //+9B06I~
        {                                                          //+9B06I~
            if (Dump.Y) Dump.println("CSI:ReceiverShutDown.onReceive intent="+Pintent.toString());//+9B06I~
            if (GC.isOnGameView())                                 //+9B06I~
            	closeSession();                                    //+9B06I~
        }                                                          //+9B06I~
    }                                                              //+9B06I~
    private void closeSession()                                    //+9B06I~
    {                                                              //+9B06I~
        if (Dump.Y) Dump.println("CSI:ReceiverShutDown.closeSession");//+9B06I~
    	Members members=AG.aBTMulti.BTGroup;                       //+9B06I~
        if (members==null)                                          //+9B06I~
        	return;                                                //+9B06I~
        for (int ii=0;ii<PLAYERS;ii++)                                 //+9B06I~
        {                                                          //+9B06I~
	        if (Dump.Y) Dump.println("CSI:ReceiverShutDown.closeSession MD["+ii+"]="+members.MD[ii].toString());//+9B06I~
        	Thread t=members.getThread(ii);                        //+9B06I~
        	if (t!=null)                                           //+9B06I~
            {                                                      //+9B06I~
            	if (t instanceof IPIOThread)                       //+9B06I~
                    ((IPIOThread)t).closeSocket();  //socket is closable on Mainthread//+9B06I~
                else                                               //+9B06I~
            	if (t instanceof BTIOThread)                       //+9B06I~
                	((com.btmtest.BT.BTIOThread)t).closeSocket();	//bluetoothSocket is closable on MainThread//+9B06I~
            }                                                      //+9B06I~
        }                                                          //+9B06I~
    }                                                              //+9B06I~
}
