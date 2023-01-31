//*CID://+va40R~:                             update#=  127;       //~va40R~
//*************************************************************************//~1A65I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//*************************************************************************//~va40I~
//*ConnectivityManager Interface for ConnectivityService changed status//~9B05I~
//*SutdownReceiver                                                 //~9B06I~
//*************************************************************************//~9B05I~
package com.btmtest.wifi;                                               //~v@@@I~//~9719I~

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
//import android.net.NetworkInfo;                                  //~va40R~
import android.net.NetworkRequest;
import android.os.Build;

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
    BroadcastReceiver receiver;                                    //~9B05I~//~9B06R~
    BroadcastReceiver receiverShutDown,receiverShutDown0;          //~9B06I~
    Object networkCallback;                                        //~va40I~
    public boolean swOnlineP2P;                                           //~va40I~
    private boolean swDefault=false;	//TODO test,true always work, false need TRANSPORT_WIFI//~va40R~
    //***********************************************************************//~v101I~//~9719I~
    public CSI()                                                   //~9722I~//~9B05R~
    {                                                              //~9722I~
        AG.aCSI=this;                                              //~9728I~//~9B05R~
        CM=(ConnectivityManager)AG.context.getSystemService(Context.CONNECTIVITY_SERVICE);//~9B05R~
        receiverShutDown0=new ReceiverShutDown();                   //~9B06I~
    }                                                              //~9722I~
    //***********************************************************************//~9B05I~//~va40R~
    //*Network was deprecated at API29 and isonline is not used    //~va40I~
    //***********************************************************************//~va40I~
//    //Wifi/Bluetooth/Phone online                                  //~9B05I~//~va40R~
//    //***********************************************************************//~9B05I~//~va40R~
//    public static boolean isOnline()                                      //~9B05I~//~va40R~
//    {                                                              //~9B05I~//~va40R~
//        if (Dump.Y) Dump.println("CSI:isOnline");                  //~9B05I~//~va40R~
//        boolean rc=false;                                          //~9B05I~//~va40R~
////        NetworkInfo ni=AG.aCSI.CM.getActiveNetworkInfo();                  //~9B05I~//~va40R~
//        android.net.NetworkInfo ni=AG.aCSI.CM.getActiveNetworkInfo();//~va40R~
//        if (Dump.Y) Dump.println("CSI:isOnline getActiveNetworkInfo="+ Utils.toString(ni));//~9B05I~//~va40R~
//        if (ni!=null)                                              //~9B05I~//~va40R~
//            if (ni.isConnected())                                  //~9B05I~//~va40R~
//                rc=true;                                           //~9B05I~//~va40R~
//        if (Dump.Y) Dump.println("CSI:isOnline rc="+rc);           //~9B05I~//~va40R~
//        return rc;                                                 //~9B05I~//~va40R~
//    }                                                              //~9B05I~//~va40R~
    //***********************************************************************//~9722I~
	@SuppressWarnings("deprecation")                               //~va40I~
    public static boolean isOnlineWifi()                           //~9B05R~
    {                                                              //~v101I~//~9719I~
        if (Dump.Y) Dump.println("CSI:isOnlineWifi");              //~9B05I~
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q) //api29 android10//~va40I~
            return isOnlineWifi_From29();                          //~va40I~
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.P) //api28 android9//~va40I~
            return isOnlineWifi_From28();                           //~va40I~
        boolean rc=false;                                          //~9B05I~
//      NetworkInfo ni=AG.aCSI.CM.getActiveNetworkInfo();                  //~9B05I~//~va40R~
		android.net.NetworkInfo ni=AG.aCSI.CM.getActiveNetworkInfo();//~va40I~
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
    //***********************************************************************//~va40I~
	@TargetApi(Build.VERSION_CODES.P)                              //~va40I~
    public  static boolean isOnlineWifi_From28()                    //~va40I~
    {                                                              //~va40I~
        if (Dump.Y) Dump.println("CSI:isOnlineWifi_From28");       //~va40I~
        boolean rc=AG.aCSI.swOnlineP2P;                                    //~va40I~
        if (Dump.Y) Dump.println("CSI:isOnlineWifi_From28 rc="+rc);//~va40I~
		return rc;                                                 //~va40I~
    }                                                              //~va40I~
    //***********************************************************************//~va40I~
	@TargetApi(Build.VERSION_CODES.Q)         //api29 android10    //~va40I~
    public  static boolean isOnlineWifi_From29()                   //~va40I~
    {                                                              //~va40I~
    	boolean rc=false;                                          //~va40I~
        if (Dump.Y) Dump.println("CSI:isOnlineWifi_From29");       //~va40I~
        NetworkCapabilities capability=AG.aCSI.CM.getNetworkCapabilities(AG.aCSI.CM.getActiveNetwork());//~va40I~
        if (capability!=null)                                      //~va40I~
        {                                                          //~va40I~
            if (capability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))//~va40I~
            {                                                      //~va40I~
        		if (Dump.Y) Dump.println("CSI:isOnlineWifi_From29 TRANSPORT_WIFI");//~va40I~
                rc=true;                                           //~va40I~
            }                                                      //~va40I~
            else                                                   //~va40I~
            {                                                      //~va40I~
        		if (Dump.Y) Dump.println("CSI:isOnlineWifi_From29 not TRANSPORT_WIFI");//~va40I~
            }                                                      //~va40I~
        }                                                          //~va40I~
        else                                                       //~va40I~
        {                                                          //~va40I~
        	if (Dump.Y) Dump.println("CSI:isOnlineWifi_From29 no connection");//~va40I~
        }                                                          //~va40I~
        if (Dump.Y) Dump.println("CSI:isOnlineWifi_From29 rc="+rc);//~va40I~
		return rc;                                                 //~va40I~
    }                                                              //~va40I~
//*****************************************************************************//~9B05I~
	public void onResume()                                         //~9B05I~
    {                                                              //~9B05I~
		if (Dump.Y) Dump.println("CSI:onResume");                  //~9B05I~
        registerReceiver();                                        //~9B05I~
        registerReceiverShutDown();                                 //~9B06I~
    }                                                              //~9B05I~
//*****************************************************************************//~9B05I~
	public void onPause()                                          //~9B05I~
    {                                                              //~9B05I~
		if (Dump.Y) Dump.println("CSI:onPause");                   //~9B05I~
        unregisterReceiver();                                      //~9B05I~
        unregisterReceiverShutDown();                              //~9B06I~
    }                                                              //~9B05I~
//*****************************************************************************//~9B05I~
	public void onDestroy()                                        //~9B05I~
    {                                                              //~9B05I~
		if (Dump.Y) Dump.println("CSI:onDestroy");                 //~9B05I~
        unregisterReceiver();                                      //~9B05I~
        unregisterReceiverShutDown();                              //~9B06I~
    }                                                              //~9B05I~
//*****************************************************************************//~9B05I~
	public void registerReceiver()                                 //~9B05I~
    {                                                              //~9B05I~
		if (Dump.Y) Dump.println("CSI:registerReceiver");          //~9B05I~
        if (receiver==null)                                        //~9B05I~
        {                                                          //~9B05I~
//          IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);//~9B05R~//~va40R~
//          AG.activity.registerReceiver(this,filter);             //~9B05R~//~va40R~
			if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.P) //api28 android9//~va40I~
				registerReceiver_From28();                          //~va40I~
            else                                                   //~va40I~
				registerReceiver_Bellow28();                        //~va40I~
            receiver=this;                                         //~9B05R~
        }                                                          //~9B05I~
    }                                                              //~9B05I~
//*****************************************************************************//~va40I~
	@SuppressWarnings("deprecation")                               //~va40I~
	private void registerReceiver_Bellow28()                       //~va40I~
    {                                                              //~va40I~
		if (Dump.Y) Dump.println("CSI:registerReceiver_Bellow28"); //~va40I~
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);//~va40I~
        AG.activity.registerReceiver(this,filter);                 //~va40I~
    }                                                              //~va40I~
//*****************************************************************************//~9B05I~
	@TargetApi(Build.VERSION_CODES.P)                               //~va40I~
	private void registerReceiver_From28()                         //~va40I~
    {                                                              //~va40I~
		if (Dump.Y) Dump.println("CSI:registerReceiver_From28");   //~va40I~
        ConnectivityManager.NetworkCallback CB=new ConnectivityManager.NetworkCallback()//~va40M~
        {                                                          //~va40M~
        	@Override                                              //~va40M~
            public void onAvailable(Network Pnetwork)              //~va40M~
            {                                                      //~va40M~
            	super.onAvailable(Pnetwork);                       //~va40I~
            	chkConnectivity(0/*available*/,Pnetwork);          //~va40M~
            }                                                      //~va40M~
            @Override                                              //~va40M~
            public void onLost(Network Pnetwork)                   //~va40M~
            {                                                      //~va40M~
            	super.onLost(Pnetwork);                            //~va40I~
            	chkConnectivity(1/*lost*/,Pnetwork);               //~va40M~
            }                                                      //~va40M~
        };                                                         //~va40M~
        networkCallback=CB;                                        //~va40M~
        if (swDefault)                                             //~va40I~
        {                                                          //~va40I~
			if (Dump.Y) Dump.println("CSI.registerReceiver_From28 registerDefaultNetworkCallback");//~va40R~
	        CM.registerDefaultNetworkCallback(CB);                 //~va40I~
        }                                                          //~va40I~
        else                                                       //~va40I~
        {                                                          //~va40I~
			if (Dump.Y) Dump.println("CSI.registerReceiver_From28 registerNetworkCallback");//~va40R~
        	NetworkRequest.Builder builder=new NetworkRequest.Builder();//~va40R~
//      	builder.addCapability(NetworkCapabilities.NET_CAPABILITY_WIFI_P2P);//~va40R~
        	builder.addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);//~va40R~
        	builder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);//~va40R~
        	NetworkRequest networkRequest=builder.build();         //~va40R~
	        CM.registerNetworkCallback(networkRequest,CB);         //~va40I~
        }                                                          //~va40I~
    }                                                              //~va40I~
//*****************************************************************************//~va40I~
	private void chkConnectivity(int Pstat, Network Pnetwork)            //~va40I~
    {                                                              //~va40I~
        try                                                        //~va40I~
        {                                                          //~va40I~
            swOnlineP2P=Pstat==0;                                  //~va40I~
			if (Dump.Y) Dump.println("CSI:chkConnectivity swOnlineP2P="+swOnlineP2P+",stat="+Pstat+",network="+Pnetwork.toString());//~va40R~
        }                                                          //~va40I~
        catch(Exception e)                                         //~va40I~
		{                                                          //~va40I~
        	Dump.println(e,"CSI.chkConnectivity status="+Pstat);   //~va40I~
		}                                                          //~va40I~
    }                                                              //~va40I~
                                                                   //~va40I~
//*****************************************************************************//~va40I~
	public void unregisterReceiver()                               //~9B05I~
    {                                                              //~9B05I~
		if (Dump.Y) Dump.println("CSI:unregisterReceiver");        //~9B05I~
        if (receiver!=null)                                        //~9B05I~
        {                                                          //~9B05I~
//      	AG.activity.unregisterReceiver(this);                              //~9B05I~//~va40R~
			if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.P) //api28 android9//~va40I~
                unregisterReceiver_From28();                       //~va40I~
            else                                                   //~va40I~
                unregisterReceiver_Bellow28();                     //~va40I~
        	receiver=null;                                         //~9B05I~
        }                                                          //~9B05I~
    }                                                              //~9B05I~
//*****************************************************************************//~va40I~
	@SuppressWarnings("deprecation")                               //~va40I~
	public void unregisterReceiver_Bellow28()                      //~va40I~
    {                                                              //~va40I~
		if (Dump.Y) Dump.println("CSI:unregisterReceiver_Bellow28");//~va40I~
        if (receiver!=null)                                        //~va40I~
        {                                                          //~va40I~
        	AG.activity.unregisterReceiver(this);                  //~va40I~
        }                                                          //~va40I~
    }                                                              //~va40I~
//*****************************************************************************//~va40I~
	@TargetApi(Build.VERSION_CODES.P)                               //~va40I~
	public void unregisterReceiver_From28()                        //~va40I~
    {                                                              //~va40I~
		if (Dump.Y) Dump.println("CSI:unregisterReceiver_From28"); //~va40I~
        if (receiver!=null)                                        //~va40I~
        {                                                          //~va40I~
        	CM.unregisterNetworkCallback((ConnectivityManager.NetworkCallback)networkCallback);//~va40I~
        }                                                          //~va40I~
    }                                                              //~va40I~
//*****************************************************************************//~1A8gI~//~9719I~
//*registerReceiver CONNECTIVITY_ACTION for api<28****************************************************************************//~va40I~
//*****************************************************************************//~va40I~
	@Override                                                      //~9B05I~
    public void onReceive(Context Pcontext, Intent Pintent)         //~9B05R~
    {                                                              //~1A8gI~//~9719I~
		if (Dump.Y) Dump.println("CSI:onReceive intent="+Pintent.toString());//~9B05I~
//      NetworkInfo ni=CM.getActiveNetworkInfo();                  //~9B05R~//~va40R~
	    onReceive_Bellow28(Pcontext,Pintent);                      //~va40I~
  	}                                                              //~1A8gI~//~9719I~
    @SuppressWarnings("deprecation")                               //~va40I~
    private void onReceive_Bellow28(Context Pcontext, Intent Pintent)//~va40I~
    {                                                              //~va40I~
		if (Dump.Y) Dump.println("CSI:onReceive_Bellow28 intent="+Pintent.toString());//~va40I~
		android.net.NetworkInfo ni=CM.getActiveNetworkInfo();      //~va40I~
		if (Dump.Y) Dump.println("CSI:onReceive_Bellow28 networkInfo="+Utils.toString(ni));//~va40I~
  	}                                                              //~va40I~
//*****************************************************************************//~9B06I~
	public void registerReceiverShutDown()                          //~9B06I~
    {                                                              //~9B06I~
		if (Dump.Y) Dump.println("CSI:registerReceiverSutDown");   //~9B06I~
        if (receiverShutDown==null)                                //~9B06I~
        {                                                          //~9B06I~
            IntentFilter filter=new IntentFilter(Intent.ACTION_SHUTDOWN);//~9B06I~
            AG.activity.registerReceiver(receiverShutDown0,filter);//~9B06I~
            receiverShutDown=receiverShutDown0;                    //~9B06I~
        }                                                          //~9B06I~
    }                                                              //~9B06I~
//*****************************************************************************//~9B06I~
	public void unregisterReceiverShutDown()                       //~9B06I~
    {                                                              //~9B06I~
		if (Dump.Y) Dump.println("CSI:unregisterReceiverShutDown");//~9B06I~
        if (receiverShutDown!=null)                                //~9B06I~
        {                                                          //~9B06I~
        	AG.activity.unregisterReceiver(receiverShutDown);      //~9B06I~
        	receiverShutDown=null;                                 //~9B06I~
        }                                                          //~9B06I~
    }                                                              //~9B06I~
//*****************************************************************************//~9B06I~
	class ReceiverShutDown                                         //~9B06I~
				extends BroadcastReceiver                          //~9B06I~
    {                                                              //~9B06I~
		@Override                                                  //~9B06I~
        public void onReceive(Context Pcontext, Intent Pintent)    //~9B06I~
        {                                                          //~9B06I~
            if (Dump.Y) Dump.println("CSI:ReceiverShutDown.onReceive intent="+Pintent.toString());//~9B06I~
            if (GC.isOnGameView())                                 //~9B06I~
            	closeSession();                                    //~9B06I~
        }                                                          //~9B06I~
    }                                                              //~9B06I~
    private void closeSession()                                    //~9B06I~
    {                                                              //~9B06I~
        if (Dump.Y) Dump.println("CSI:ReceiverShutDown.closeSession");//~9B06I~
    	Members members=AG.aBTMulti.BTGroup;                       //~9B06I~
        if (members==null)                                          //~9B06I~
        	return;                                                //~9B06I~
        for (int ii=0;ii<PLAYERS;ii++)                                 //~9B06I~
        {                                                          //~9B06I~
	        if (Dump.Y) Dump.println("CSI:ReceiverShutDown.closeSession MD["+ii+"]="+members.MD[ii].toString());//~9B06I~
        	Thread t=members.getThread(ii);                        //~9B06I~
        	if (t!=null)                                           //~9B06I~
            {                                                      //~9B06I~
            	if (t instanceof IPIOThread)                       //~9B06I~
                    ((IPIOThread)t).closeSocket();  //socket is closable on Mainthread//~9B06I~
                else                                               //~9B06I~
            	if (t instanceof BTIOThread)                       //~9B06I~
                	((com.btmtest.BT.BTIOThread)t).closeSocket();	//bluetoothSocket is closable on MainThread//~9B06I~
            }                                                      //~9B06I~
        }                                                          //~9B06I~
    }                                                              //~9B06I~
////*****************************************************************************//+va40R~
//    @TargetApi(23)                                               //+va40R~
//    public void chkNetwork()                                     //+va40R~
//    {                                                            //+va40R~
//        if (Dump.Y) Dump.println("CSI:chkNetwork");              //+va40R~
//        Network[] nws=CM.getAllNetworks();                       //+va40R~
//        if (Dump.Y) Dump.println("CSI.chkNetwork ctr="+nws.length+",active="+CM.getActiveNetwork()+"="+CM.getActiveNetworkInfo());//+va40R~
//        for (Network nw:nws)                                     //+va40R~
//        {                                                        //+va40R~
//            if (Dump.Y) Dump.println("CSI.chkNetwork network="+nw.toString());//+va40R~
//        }                                                        //+va40R~
//    }                                                              //~va40I~
}
