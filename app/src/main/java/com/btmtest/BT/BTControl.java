//*CID://+1aj1R~:                             update#=  177;       //~1aj1R~
//****************************************************************************//~v101I~
//1aj1 2021/08/14 androd11(api30) deprecated at api30;Handler default constructor(requires parameter)//~1aj1I~
//@004:20181103 remains constant "AjagoBT"                         //~@004I~
//@003:20181103 dismiss aler dialog when interrupted by other app  //~@003I~
//****************************************************************************//~v101I~

package com.btmtest.BT;                                               //~1AedI~//~1A6aI~

import com.btmtest.utils.Dump;                                           //~1A6aR~

import com.btmtest.R;
import com.btmtest.utils.UView;//~1A6aR~
import com.btmtest.utils.UHandler;
import com.btmtest.utils.Utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Message;
import android.os.Looper;                                          //+1aj1I~
                                                                   //~@003I~
//import static com.btmtest.AG.*;                                  //~@003R~
import java.lang.reflect.Method;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@003I~

public class BTControl {                                           //~@@@@I~

	public static final int REQUEST_ENABLE_BT = 2;                 //~@003I~
    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    public static final int MESSAGE_FAILURE = 6;                   //~@@@2R~
    public static final int MESSAGE_FAILUREACCEPT = 7;             //~v101I~

    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    public static final String ACCEPT_TYPE = "secure";             //~v101I~

    // Intent request codes
                                                                   //~@@@@I~
    public static final int BTA_ENABLE=1;                          //~@@@@I~
    public static final int BTA_DISABLE=2; //for IOErr test        //~9A26I~
    public static final int BTA_DISCOVERABLE=3;                    //~@@@@I~
    public static final int BTA_DISCOVERABLE_STOP=4;               //~1AbJI~
    private static final int DEFAULT_DISCOVERABLE_DUARATION=120;   //~1AbHI~
    private static final int DISCOVERABLE_DUARATION=120;           //~1AbHI~
    private static final int STOP_DISCOVERABLE_DUARATION=1;        //~1AbJI~

    // Layout Views

    // Name of the connected device
    private static String mConnectedDeviceName = null;             //~@@@@I~//~@@@2R~
    public String mLocalDeviceName;                                //~@@@@I~
    // Array adapter for the conversation thread
    // String buffer for outgoing messages
    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the chat services
    public  BTService mChatService = null;                  //~@@@@R~//~@@@2R~//~v101R~//~0123R~
	private Activity activity;                                     //~@@@@I~//~@@@2R~
//  private Handler mHandler;                                      //~@@@@R~//~@003R~
    private BTHandler mHandler;                                    //~@003I~
    private BluetoothSocket mBTSocket;                             //~@@@@R~
 
//*************************************************************************//~@@@@I~
    public BTControl()                                             //~@@@@I~//~@@@2R~
    {                                                              //~@@@@I~
    	activity=AG.aMainActivity;                                       //~@@@@I~
    	mConnectedDeviceName=null;	//called at main create        //~@@@2I~
//      mHandler=new BTHandler();	//on MainThread                //~@@@@I~//~1aj1R~
        mHandler=new BTHandler(Looper.getMainLooper());	//on MainThread//~1aj1I~
    }                                                              //~@@@@I~
//*************************************************************************//~@@@@I~
//*create Adapter(bluetooth support chk)                           //~@@@@I~
//*************************************************************************//~@@@@I~
    public boolean BTCreate() {                                    //~@@@@R~
        if(Dump.Y) Dump.println("BTControl.BTCreate +++ BTCreate+++");                //~@@@@M~//~9718R~
        if (mBluetoothAdapter != null)                             //~@@@@I~
        	return true;                                           //~@@@@I~
        // Get local Bluetooth adapter                             //~@@@@R~
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();  //~@@@@I~
                                                                   //~@@@@I~
        // If the adapter is null, then Bluetooth is not supported //~@@@@I~
        if (mBluetoothAdapter == null) {                           //~@@@@I~
	        UView.showToastLong(R.string.noBTadapter);             //~@@@@R~//~1A6aR~
            return false;                                          //~@@@@I~
        }                                                          //~@@@@I~
        mLocalDeviceName=mBluetoothAdapter.getName();              //~@@@@I~
        return true;                                               //~@@@@I~
    }                                                              //~@@@@I~

//*************************************************************************//~@@@@M~
//*enable bluetooth                                                //~@@@@I~
//*rc:true if start activity-enableBluetooth                       //~@@@@M~
//*************************************************************************//~@@@@I~
    public boolean BTStart() {                                     //~@@@@R~
    	boolean rc=false;                                          //~@@@@I~
        if(Dump.Y) Dump.println("+++ BTStart+++");                 //~@@@@M~
        // If BT is not on, request that it be enabled.            //~@@@@I~
        // setupChat() will then be called during onActivityResult //~@@@@I~
		if (!BTEnable(false)){	//requested "enable"                   //~v101I~
            rc=true;                                               //~@@@@I~
        // Otherwise, setup the chat session                       //~@@@@I~
        } else {                                                   //~@@@@I~
            if (mChatService == null) setupChat();//accept                 //~@@@@R~//~v101R~
        }                                                          //~@@@@I~
        return rc;                                                 //~@@@@I~
    }                                                              //~@@@@I~
    //************************************                         //~v101I~
    public boolean BTEnable(boolean Presume)                       //~v101I~
    {                                                              //~v101I~
    	boolean rc=true;                                           //~v101I~
        if(Dump.Y) Dump.println("BTControl.BTEnable");                //~v101I~//~1A6aR~
        if (!mBluetoothAdapter.isEnabled())                        //~v101I~
        {                                                          //~v101I~
            startBTActivity(BTA_ENABLE);                           //~v101I~
            rc=false;                                              //~v101I~
        }                                                          //~v101I~
        if(Dump.Y) Dump.println("BTEnable rc="+rc);                //~v101I~
        return rc;                                                 //~v101I~
    }                                                              //~v101I~

//*************************************************************************//~@@@@I~
//*from Ahsv                                                       //~@@@@R~
//*************************************************************************//~@@@@I~
    public synchronized void BTResume() {                          //~@@@@I~
        if(Dump.Y) Dump.println("+++ BTResume+++");                //~@@@@I~
                                                                   //~@@@@I~
        // Performing this check in onResume() covers the case in which BT was//~@@@@I~
        // not enabled during onStart(), so we were paused to enable it...//~@@@@I~
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.//~@@@@I~
    }                                                              //~@@@@I~
//*************************************************************************//~@@@@I~
    private void startService() {                                  //~@@@@I~
        if(Dump.Y) Dump.println("+++ startService+++");            //~@@@@I~
        if (mChatService != null) {                                //~@@@@I~
            // Only if the state is STATE_NONE, do we know that we haven't started already//~@@@@I~
            if (mChatService.getState() == BTService.STATE_NONE) { //~@@@@I~
              // Start the Bluetooth chat services                 //~@@@@I~
              mChatService.start();	//issue accept                 //~@@@@R~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
    }                                                              //~@@@@I~
//*************************************************************************//~@@@@M~
//* init Service(issue accept)                                     //~@@@@M~
//* from BTStart and diffetred enableBT                            //~@@@@I~
//*************************************************************************//~@@@@I~
    public void setupChat() {                                      //~@@@@R~
        if (Dump.Y) Dump.println("BTC:setupChat");                 //~@@@@R~
                                                                   //~@@@@I~
	      mChatService = new BTService(activity, mHandler);        //~@@@@I~
    	startService();                                            //~@@@@I~
		return;                                                    //~@@@@R~
    }                                                              //~@@@@I~
//*************************************************************************//~@@@@I~
//*from MainActivity                                               //~1A6aR~
//*************************************************************************//~@@@@I~
    public void BTDestroy() {                                      //~@@@@R~
        // Stop the Bluetooth chat services                        //~@@@@I~
        if(Dump.Y) Dump.println("BTC destroy");                    //~@@@2I~
        if (mChatService != null) mChatService.stop();             //~@@@@I~
        mHandler.onDestroy();                                      //~@003I~
    }                                                              //~@@@@I~
    public void onPause()                                               //~@003R~
    {                                                              //~@003I~
    	mHandler.onPause();                                        //~@003I~
    }                                                              //~@003I~
    public void onResume()                                              //~@003I~
    {                                                              //~@003I~
    	mHandler.onResume(AG.activity);                                       //~@003I~
    }                                                              //~@003I~

//*************************************************************************//~@@@@I~
//*************************************************************************//~@@@@I~
//*Handler on MainThread                                           //~@@@@I~
//*************************************************************************//~@@@@I~
    // The Handler that gets information back from the BluetoothChatService
//  private static class BTHandler extends Handler {               //~@@@@R~//~@003R~
    private static class BTHandler extends UHandler                //~@003I~
    {                                                              //~@003I~
        public BTHandler(Looper Plooper)                           //~1aj1I~
        {                                                          //~1aj1I~
            super(Plooper);                                        //~1aj1I~
        	if(Dump.Y) Dump.println("BTC.BTHandler constructor looper="+Plooper.toString());//~1aj1I~
        }                                                          //~1aj1I~
    	//****************************************                 //~@003I~
    	@Override                                                  //~@003I~
        public boolean storeMsg(Message Pmsg)                             //~@003I~
        {                                                          //~@003I~
        	return true; //allow store when paused                 //~@003I~
        }                                                          //~@003I~
    	//****************************************                 //~@003I~
        @Override
//      public void handleMessage(Message msg) {                   //~@003R~
        public void handleMsg(Message msg)	//from UHandler.handleMessage//~@003I~
        {                                                          //~@003I~
        	try                                                    //~@@@@I~
            {                                                      //~@@@@I~
                if(Dump.Y) Dump.println("BTConstrol.BTHandler.handleMsg what="+msg.what);//~@003I~
                switch (msg.what) {                                //~@@@@R~
                case MESSAGE_STATE_CHANGE:                         //~@@@@R~
                    if(Dump.Y) Dump.println("MESSAGE_STATE_CHANGE: " + msg.arg1);//~@@@@R~
                    switch (msg.arg1) {                            //~@@@@R~
                    case BTService.STATE_CONNECTED:                //~@@@@R~
                        break;                                     //~@@@@R~
                    case BTService.STATE_CONNECTING:               //~@@@@R~
                        break;                                     //~@@@@R~
                    case BTService.STATE_LISTEN:                   //~@@@@R~
                        break;                                     //~@@@@R~
                    case BTService.STATE_NONE:                     //~@@@@R~
                        break;                                     //~@@@@R~
                    }                                              //~@@@@R~
                    break;                                         //~@@@@R~
                case MESSAGE_DEVICE_NAME:                          //~@@@@R~
                    // save the connected device's name            //~@@@@R~
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);//~@@@@R~
                    AG.aBTI.connected(BTService.mConnectedSocket,mConnectedDeviceName);//~@@@@R~//~1A6aR~
                    break;                                         //~@@@@R~
                case MESSAGE_TOAST:                                //~@@@@R~
                    UView.showToast(R.string.dev_act_msg,msg.getData().getString(TOAST));//~@@@@R~//~1A6aR~
                    break;                                         //~@@@@R~
                case MESSAGE_FAILURE:                              //~@@@2I~
                	if (Dump.Y) Dump.println("MESSAGE_FAILURE="+msg.arg1);//~@@@2I~
                    AG.aBTI.connFailed(msg.arg1);	               //~@@@2I~//~1A6aR~
                    break;                                         //~@@@2I~
                case MESSAGE_FAILUREACCEPT:                        //~v101I~
                	if (Dump.Y) Dump.println("MESSAGE_FAILUREACCEPT="+msg.arg1);//~v101I~
                    String secure=msg.getData().getString(ACCEPT_TYPE);//~v101I~
                    AG.aBTI.acceptFailed(secure);                   //~v101R~//~1A6aR~
                    break;                                         //~v101I~
                }                                                  //~@@@@R~
            }                                                      //~@@@@I~
            catch(Exception e)                                     //~@@@@I~
            {                                                      //~@@@@I~
                Dump.println(e,"BTControl:BTHandler.handleMessage");    //~@@@@I~//~@004R~
                UView.showToastLong(R.string.failedBluetooth);     //~@@@@R~//~1A6aR~
            }                                                      //~@@@@I~
        }
    } //BTHandler Class                                            //~@@@@M~

//*************************************************************************//~@@@@I~
//*from Ahsv                                                       //~@@@@R~
//*************************************************************************//~@@@@I~
    public void BTActivityResult(int requestCode, int resultCode, Intent data) {//~@@@@R~
        if(Dump.Y) Dump.println("onActivityResult reqcode=" +requestCode+ ",resultcode="+ resultCode); //~@@@@R~//~1A60R~
        switch (requestCode) {
//      case AG.ACTIVITY_REQUEST_ENABLE_BT:                        //~1A6aI~//~@003R~
        case REQUEST_ENABLE_BT:                                    //~@003I~
            // When the request to enable Bluetooth returns
            if (resultCode == Activity.RESULT_OK) {
                // Bluetooth is now enabled, so set up a chat session
                AG.aBTI.enabled();                                  //~@@@@R~//~1A6aR~
            } else {
                // User did not enable Bluetooth or an error occured
                if (Dump.Y) Dump.println("BT not enabled");        //~@@@@R~
	            UView.showToastLong(R.string.bt_not_enabled_leaving);  //~v107I~//~@@@@R~//~1A6aR~
            }
            break;                                                 //~9A26I~
        }
    }

//*************************************************************************//~@@@@I~
    public boolean startBTActivity(int Pfuncid)                    //~@@@@I~
    {                                                              //~@@@@I~
        Intent intent=null;                                        //~@@@@R~
        switch (Pfuncid) {                                         //~@@@@I~
        case BTA_ENABLE:                                           //~@@@@I~
            intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);//~@@@@I~
//          activity.startActivityForResult(intent,AG.ACTIVITY_REQUEST_ENABLE_BT);//~1A6aI~//~@003R~
            activity.startActivityForResult(intent,REQUEST_ENABLE_BT);//~@003I~
            return true;                                           //~@@@@I~
        case BTA_DISABLE:	//for Test                             //~9A26I~
		    mBluetoothAdapter.disable();                           //~9A26I~
            return true;                                           //~9A26I~
        case BTA_DISCOVERABLE:                                     //~@@@@R~
            intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);//~@@@@I~
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,DISCOVERABLE_DUARATION);//~1AbHI~
            activity.startActivity(intent);                        //~@@@@I~
            return true;                                           //~@@@@I~
        case BTA_DISCOVERABLE_STOP:                                //~1AbJI~
            intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);//~1AbJI~
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,STOP_DISCOVERABLE_DUARATION);//~1AbJI~
            activity.startActivity(intent);                        //~1AbJI~
            return true;                                           //~1AbJI~
        }                                                          //~@@@@I~
        return false;                                              //~@@@@I~
    }                                                              //~@@@@I~
//***************************************************************************//~@@@@I~
//*rc:true:accept issued                                           //~v101I~
//***************************************************************************//~v101I~
    public boolean BTstartServer()                                 //~v101I~
    {                                                              //~@@@@I~
        boolean rc=false;		//failed                           //~v101I~
    	int state;//~@@@@I~
        boolean acceptThreadStarted=false;                         //~@@@@I~
	//************************                                     //~@@@@I~
        if(Dump.Y) Dump.println("BTstartServer");                  //~@@@@I~
        if (BTCreate())	//!supported                               //~@@@@R~
        {                                                          //~@@@@I~
        	if (!BTStart())		//already enable adapter           //~@@@@R~
            {                                                      //~@@@@I~
    			state=mChatService.getState();                     //~@@@@I~
    			if (state==BTService.STATE_CONNECTED)              //~@@@@I~
                	UView.showToast(R.string.alreadyconnected);    //~@@@@R~//~1A6aR~
                else                                               //~@@@@I~
                {                                                  //~@@@@I~
                    acceptThreadStarted=mChatService.start();  //restart AcceptThread//~v101I~
					rc=true;                                       //~v101I~
                }                                                  //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
        if(Dump.Y) Dump.println("BTstartServer acceptstarted="+acceptThreadStarted+",rc="+rc);//~@@@@R~//~v101R~
        return rc;                                                 //~v101I~
    }                                                              //~@@@@R~
//***************************************************************************//~@@@2I~//~v101R~
//*MAC addr specified connect                                      //~v101R~
//***************************************************************************//~@@@2I~//~v101R~
    public int BTconnect(String Paddr,boolean Psecure)             //~1A60I~
    {                                                              //~v101R~
        int rc=0,state=-1;                                         //~v101R~
    //*************************                                    //~v101R~
        if(Dump.Y) Dump.println("BTconnect addr="+Paddr);          //~v101R~
        if (!BTCreate())                                           //~v101R~
            rc=-1;                                                 //~v101R~
        else                                                       //~v101R~
            if (!BTStart())     //already enable adapter           //~v101R~
            {                                                      //~v101R~
                state=mChatService.getState();                     //~v101R~
                if (state==BTService.STATE_LISTEN                  //~v101R~
                ||  state==BTService.STATE_NONE)                   //~v101R~
                {                                                  //~v101R~
                	BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(Paddr);//~v101I~
                    mChatService.connect(device,Psecure);          //~1A60I~
                }                                                  //~v101R~
                else                                               //~v101R~
                {                                                  //~v101R~
                    rc=1;                                          //~v101R~
                }                                                  //~v101R~
            }                                                      //~v101R~
        if(Dump.Y) Dump.println("BTconnect rc="+rc+",state="+state);//~v101R~
        return rc;                                                 //~v101R~
    }                                                              //~v101R~
//***************************************************************************//~9210I~
    public boolean BTunpair(String Pname,String Paddr)             //~9210R~
    {                                                              //~9210I~
        boolean rc=true;                                           //~9210I~
    //*************************                                    //~9210I~
        if(Dump.Y) Dump.println("BTControl.BTunpair addr="+Paddr); //~9210I~
        if (!BTCreate())                                           //~9210I~
            rc=false;                                              //~9210I~
        else                                                       //~9210I~
        {                                                          //~9210I~
            BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(Paddr);//~9210I~
            try                                                    //~9210I~
            {                                                      //~9210I~
                Method m=device.getClass().getMethod("removeBond",(Class[])null);//~9210I~
                if (m==null)                                       //~9210I~
                {                                                  //~9210I~
                    if(Dump.Y) Dump.println("BTControl.BTunpair invoke method not found");//~9210I~
                    rc=false;                                      //~9210I~
                }                                                  //~9210I~
                else                                               //~9210I~
                {                                                  //~9210I~
                    m.invoke(device,(Object[])null);               //~9210I~
    	            UView.showToastLong(Utils.getStr(R.string.BluetoothUnpairRequested,Pname));//~9210I~
                    if(Dump.Y) Dump.println("BTunpair invoke m="+m.toString());//~9210I~
                }                                                  //~9210I~
            }                                                      //~9210I~
            catch(Exception e)                                     //~9210I~
            {                                                      //~9210I~
                Dump.println(e,"BTControl:BTUnpair");              //~9210I~
                UView.showToastLong(R.string.failedBluetoothUnpair);//~9210R~
                rc=false;                                          //~9210I~
            }                                                      //~9210I~
        }                                                          //~9210I~
        if(Dump.Y) Dump.println("BTconnect rc="+rc);               //~9210I~
        return rc;                                                 //~9210I~
    }                                                              //~9210I~
//*************************************************************************//~@@@@M~
//*ret -1:not discoverable,1:requested discoverable,0:discoverable //~@@@@M~
//*************************************************************************//~@@@@M~
    public int BTensureDiscoverable(boolean PrequestSet)           //~@@@@R~
	{                                                              //~@@@@M~
    	int rc=0;                                                  //~@@@@M~
    //*********************                                        //~@@@@M~
        if(Dump.Y) Dump.println("ensure discoverable req="+PrequestSet);//~@@@@M~
        if (!BTCreate())                                           //~@@@@I~
            rc=-1;                                                 //~@@@@I~
        else                                                       //~@@@@I~
        {                                                          //~1A6aI~
	        if(Dump.Y) Dump.println("ensure discoverable scanmode="+mBluetoothAdapter.getScanMode());//~1A6aI~//~@003R~
        if (mBluetoothAdapter.getScanMode()!=BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)//~@@@@M~
		{                                                          //~@@@@M~
        	if (PrequestSet)                                       //~@@@@M~
            {                                                      //~@@@@M~
            	startBTActivity(BTA_DISCOVERABLE);                 //~@@@@M~
                rc=1;                                              //~@@@@M~
            }                                                      //~@@@@M~
            else                                                   //~@@@@M~
            	rc=-1;                                             //~@@@@R~
        }                                                          //~@@@@M~
        }                                                          //~1A6aI~
        return rc;                                                 //~@@@@I~
    }                                                              //~@@@@M~
//*************************************************************************//~1AbHI~
//*************************************************************************//~1AbHI~
    public int BTisDiscoverable()                                  //~1AbHI~
	{                                                              //~1AbHI~
    	int rc=0;                                                  //~1AbHI~
    //*********************                                        //~1AbHI~
        if(Dump.Y) Dump.println("BTC:BTisDiscoverable");           //~1AbHI~
        if (!BTCreate())                                           //~1AbHI~
            rc=-1;                                                 //~1AbHI~
        else                                                       //~1AbHI~
        {                                                          //~1AbHI~
	        if(Dump.Y) Dump.println("ensure discoverable scanmode="+mBluetoothAdapter.getScanMode());//~1AbHI~//~@003R~
        	if (mBluetoothAdapter.getScanMode()==BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)//~1AbHI~
			{                                                      //~1AbHI~
        		rc=1;                                              //~1AbHI~
        	}                                                      //~1AbHI~
        }                                                          //~1AbHI~
        return rc;                                                 //~1AbHI~
    }                                                              //~1AbHI~
//*************************************************************************//~1AbJI~
    public int BTstopDiscoverable()                                //~1AbJI~
	{                                                              //~1AbJI~
    	int rc=0;                                                  //~1AbJI~
    //*********************                                        //~1AbJI~
        if(Dump.Y) Dump.println("stop discoverable");              //~1AbJI~
        if (!BTCreate())                                           //~1AbJI~
            rc=-1;                                                 //~1AbJI~
        else                                                       //~1AbJI~
        {                                                          //~1AbJI~
	        if(Dump.Y) Dump.println("stop discoverable scanmode="+mBluetoothAdapter.getScanMode());//~1AbJI~
	        if (mBluetoothAdapter.getScanMode()==BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)//~1AbJI~
            {                                                      //~1AbJI~
		        if(Dump.Y) Dump.println("stop discoverable request duration=1");//~1AbJI~
            	startBTActivity(BTA_DISCOVERABLE_STOP);            //~1AbJI~
                rc=1;                                              //~1AbJI~
            }                                                      //~1AbJI~
        }                                                          //~1AbJI~
        return rc;                                                 //~1AbJI~
    }                                                              //~1AbJI~
//*************************************************************************//~@@@@I~
    public String getDeviceName()                                  //~@@@@R~
    {                                                              //~@@@@I~
	    return (mLocalDeviceName!=null?mLocalDeviceName:"");       //~@@@@R~
    }                                                              //~@@@@I~
//*************************************************************************//~@@@@I~
    public void connectionLost()                                   //~@@@@I~
    {                                                              //~@@@@I~
        if (mChatService != null)                                  //~@@@@I~
        {                                                          //~@@@@I~
	    	mChatService.connectionLost();                        //~@@@@I~
        }                                                          //~@@@@I~
    }                                                              //~@@@@I~
//*************************************************************************//~@@@@I~
    public boolean isConnectionAlive()                                //~@@@@I~
    {                                                              //~@@@@I~
        return (mChatService!=null) && (mChatService.getState()==BTService.STATE_CONNECTED);//~@@@@I~
    }                                                              //~@@@@I~
//*************************************************************************//~1A6aI~
    public boolean notifyStatusChanged(int Pstat)                  //~1A6aI~
    {                                                              //~1A6aI~
        return (mChatService!=null) && (mChatService.stopListen());//~1A6aI~
    }                                                              //~1A6aI~
//*************************************************************************//~@@@2I~
    public boolean stopListen()                                    //~@@@2I~
    {                                                              //~@@@2I~
        return (mChatService!=null) && (mChatService.stopListen());//~@@@2I~
    }                                                              //~@@@2I~
//*************************************************************************//~1A6aI~
    public boolean stopConnect()                                   //~1A6aI~
    {                                                              //~1A6aI~
        return (mChatService!=null) && (mChatService.stopConnect());//~1A6aI~
    }                                                              //~1A6aI~
//*************************************************************************//~1A6aI~
    public void stopAll()                                          //~1A6aI~
    {                                                              //~1A6aI~
        if (mChatService!=null)
                mChatService.stop();      //~1A6aR~
    }                                                              //~1A6aI~
//*************************************************************************//~v101I~
    public boolean discover()                 //~v101I~            //~1A6aR~
    {                                                              //~v101I~
        BTDiscover.discover();                                 //~v101R~//~1A6aR~
		return true;//~v101R~
    }                                                              //~v101I~
//*************************************************************************//~v101I~
    public boolean cancelDiscover()                                //~v101I~
    {                                                              //~v101I~
        BTDiscover.cancelDiscover();                           //~v101I~
		return true;                                               //~v101I~
    }                                                              //~v101I~
//*************************************************************************//~v101I~
    public void requestDiscover(BluetoothAdapter Padapter,int Pdiscover)//~v101I~
    {                                                              //~v101I~
        if (Dump.Y) Dump.println("BTControl:requestDiscover Pdiscover="+Pdiscover+",mChatService="+mChatService);//~1AbHI~
        BTService.requestDiscover(mChatService,Padapter,Pdiscover);//~v101R~
    }                                                              //~v101I~
//*************************************************************************//~v101I~
    public String[] getPairedDeviceList()                          //~v101I~
    {                                                              //~v101I~
        return BTService.getPairedDeviceList(mChatService);        //~v101R~
    }                                                              //~v101I~
//********************************************************************//~0123I~
    public void onDisconnectedIP(String Pdevname,boolean PswServer)//~0123I~
    {                                                              //~0123I~
		if (Dump.Y) Dump.println("BTControl:onDisconnectedIP dev="+Pdevname+",swServer="+PswServer);//~0123I~
    	mChatService.onDisconnectedIP(Pdevname,PswServer);         //~0123I~
    }                                                              //~0123I~
}