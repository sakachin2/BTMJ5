//*CID://+vat5R~:                             update#=  138;       //~vat3R~//~vat5R~
//********************************************************************************//~v101I~
//2022/10/16 vat5 deprecated api31;requires request permission BLUETOOTH_CONNECT//~vat5I~
//2022/10/16 vat3 deprecated api31; BluetoothDevice/BluetoothAdapter.getName()/getBondState() requires request permission BLUETOOTH_CONNECT//~vat3I~
//2022/03/28 vam3 android12(api31) deprecated Bluetooth.getDefaultAdapter//~vam3I~
//********************************************************************************//~vam3I~

package com.btmtest.BT;                                               //~1AedI~//~1AebI~

import com.btmtest.utils.Dump;                                     //~1AebR~

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import com.btmtest.R;                                              //~1AebR~
import com.btmtest.utils.Utils;
import com.btmtest.utils.UView;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~1AebI~

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;

public class BTService {                                           //~@@@@I~
    // Debugging

    private static final String NAME_SECURE = AG.appName+"_Bluetooth";//~1AbFI~
    private static final String NAME_INSECURE = AG.appName+"_Bluetooth";//~1AbFI~
    private static final String CONFIRM_ACCEPT="Service discovery failed";//~1A64I~

    // Unique UUID for this application
    private static final UUID MY_UUID_SECURE =                     //~v101I~
          UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //~v101I~
    private static final UUID MY_UUID_INSECURE =                   //~v101I~
          UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //~v101I~

    // Member fields
    private final BluetoothAdapter mAdapter;
    private final Handler mHandler;
    private AcceptThread mAcceptThread;
    private AcceptThread mAcceptThreadInSecure;                    //~v101I~
    private ConnectThread mConnectThread;
    private int mState;

    // Constants that indicate the current connection state
    public static final int STATE_NONE = 0;       // we're doing nothing
    public static final int STATE_LISTEN = 1;     // now listening for incoming connections
    public static final int STATE_CONNECTING = 2; // now initiating an outgoing connection
    public static final int STATE_CONNECTED = 3;  // now connected to a remote device
    public static final int CONN_FAILED=1;                         //~@@@2I~
    public static final int CONN_LOST=2;                           //~@@@2I~
    public static BluetoothSocket  mConnectedSocket;               //~@@@@R~

    /**
     * Constructor. Prepares a new BluetoothChat session.
     * @param context  The UI Activity Context
     * @param handler  A Handler to send messages back to the UI Activity
     */
    public BTService(Context context, Handler handler) {           //~@@@@I~
//      mAdapter = BluetoothAdapter.getDefaultAdapter();           //~vam3R~
        mAdapter = BTControl.getDefaultAdapter();                  //~vam3I~
        mState = STATE_NONE;
        mHandler = handler;
    }

    /**
     * Set the current state of the chat connection
     * @param state  An integer defining the current connection state
     */
    private void setState(int state) {                             //~v101I~
        if (Dump.Y) Dump.println("BTService.setState SYNC " + mState + " -> " + state);//~@@@@I~//~v101R~//~1AebR~
      synchronized(this)                                           //~v101I~
      {                                                            //~v101I~
        mState = state;
	    if (AG.aBTI.swDestroy)                                      //~@@@2I~//~1AebR~
        	return;                                                //~@@@2I~
        // Give the new state to the Handler so the UI Activity can update
        mHandler.obtainMessage(BTControl.MESSAGE_STATE_CHANGE, state, -1).sendToTarget();//~@@@@R~
      }                                                            //~v101I~
        if (Dump.Y) Dump.println("BTService.setState() SYNC return");        //~v101I~//~1AebR~
    }
    private void notifyFailure(int flag)              //~@@@2I~    //~v101R~
    {                                                              //~@@@2I~
        if (Dump.Y) Dump.println("Notifyfailure SYNC");                 //~@@@2I~//~v101R~
      synchronized(this)                                           //~v101I~
      {                                                            //~v101I~
	    if (AG.aBTI.swDestroy)                                      //~@@@2I~//~1AebR~
        	return;                                                //~@@@2I~
        mHandler.obtainMessage(BTControl.MESSAGE_FAILURE, flag, -1).sendToTarget();//~@@@2I~
      }                                                            //~v101I~
        if (Dump.Y) Dump.println("notifyFailure SYNC return");     //~v101I~
    }                                                              //~@@@2I~
    private void notifyFailureAccept(String Pinfo)                 //~v101R~
    {                                                              //~v101I~
        if (Dump.Y) Dump.println("NotifyfailureAccept SYNC");      //~v101I~
      synchronized(this)                                           //~v101I~
      {                                                            //~v101I~
	    if (AG.aBTI.swDestroy)                                      //~v101I~//~1AebR~
        	return;                                                //~v101I~
        Message msg = mHandler.obtainMessage(BTControl.MESSAGE_FAILUREACCEPT);//~v101I~
        Bundle bundle = new Bundle();                              //~v101I~
        bundle.putString(BTControl.ACCEPT_TYPE,Pinfo);              //~v101I~
        msg.setData(bundle);                                       //~v101I~
        mHandler.sendMessage(msg);                                 //~v101I~
      }                                                            //~v101I~
        if (Dump.Y) Dump.println("notifyFailureAccept SYNC return");//~v101I~
    }                                                              //~v101I~

    /**
     * Return the current connection state. */
    public int getState() {                                        //~v101I~
        if (Dump.Y) Dump.println("getState() SYNC " + mState );    //~v101I~
      synchronized(this)                                           //~v101I~
      {                                                            //~v101I~
          if (Dump.Y) Dump.println("getState() SYNC return");         //~v101I~
        return mState;
      }                                                            //~v101I~
    }

    /**
     * Start the chat service. Specifically start AcceptThread to begin a
     * session in listening (server) mode. Called by the Activity onResume() */
    public boolean start() {                                       //~v101R~
        if (Dump.Y) Dump.println("BTService SYNC:start()");               //~@@@@I~//~v101R~
      	synchronized(this)                                           //~v101I~//~1AebR~
      	{                                                            //~v101I~//~1AebR~

        // Cancel any thread attempting to make a connection
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}

        // Cancel any thread currently running a connection

        // Start the thread to listen on a BluetoothServerSocket
       		if (!AG.aBTI.swConnect)                                      //~1AbcI~//~1AebR~
       		{                                                           //~1AbcI~//~1AebR~
        		if (mAcceptThread == null)                         //~1AebR~
				{                                                  //~1AebI~
					acceptNext();                                          //~@@@@I~//~1AebR~
        		}                                                  //~1AebR~
        		if (mAcceptThreadInSecure == null)                 //~1AebR~
				{                                                  //~1AebI~
					acceptNextInSecure();                                  //~v101I~//~1AebR~
        		}                                                          //~v101I~//~1AebR~
       		}                                                           //~1AbcI~//~1AebR~
      	}                                                            //~v101I~//~1AebR~
        if (Dump.Y) Dump.println("BTService SYNC:start() return swConnect="+AG.aBTI.swConnect); //~v101I~//~1AbcI~//~1AebR~
        return (mAcceptThread!=null||mAcceptThreadInSecure!=null); //~v101I~
    }
//********************************************************************//~@@@2R~
    /**
     * Start the ConnectThread to initiate a connection to a remote device.
     * @param device  The BluetoothDevice to connect
     */
    public void connect(BluetoothDevice device, boolean secure) {//~v101I~
        if (Dump.Y) Dump.println("BTService:SYNC connect connect to: " + device);//~@@@@I~//~v101R~
      synchronized(this)                                           //~v101I~
      {                                                            //~v101I~

        // Cancel any thread attempting to make a connection
        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}
        }

        // Cancel any thread currently running a connection

        // Start the thread to connect with the given device
        mConnectThread = new ConnectThread(device,secure);         //~v101I~
        mConnectThread.start();
        setState(STATE_CONNECTING);
      }                                                            //~v101I~
        if (Dump.Y) Dump.println("BTService:SYNC connect return"); //~v101I~
    }

    /**
     * Start the ConnectedThread to begin managing a Bluetooth connection
     * @param socket  The BluetoothSocket on which the connection was made
     * @param device  The BluetoothDevice that has been connected
     */
    public void connected(BluetoothSocket socket, BluetoothDevice device) {//~v101I~
        if (Dump.Y) Dump.println("BTService:SYNC connected start");           //~@@@@I~//~v101R~
      synchronized(this)                                           //~v101I~
      {                                                            //~v101I~

        // Cancel the thread that completed the connection
        if (mConnectThread != null)                                //~1AebR~
 		{                                                          //~1AebI~
			mConnectThread.cancel();                               //~1AebI~
			mConnectThread = null;                                 //~1AebI~
	        if (Dump.Y) Dump.println("BTService:connected, after connectthread cancel");//~@@@2I~//~v101R~//~1AebI~
		}                                                          //~1AebI~

        // Cancel any thread currently running a connection

        // Cancel the accept thread because we only want to connect to one device
//      if (mAcceptThread != null) {mAcceptThread.cancel(); mAcceptThread = null;}//~1AebR~
//      if (Dump.Y) Dump.println("BTService:connected after acceptthread cancel");//~@@@2I~//~v101R~//~1AebR~
                                                                   //~v101I~
//      if (mAcceptThreadInSecure != null) {mAcceptThreadInSecure.cancel(); mAcceptThreadInSecure = null;}//~v101I~//~1AebR~
//      if (Dump.Y) Dump.println("BTService:connected after acceptthreadInSecure cancel");//~v101R~//~1AebR~

        // Start the thread to manage the connection and perform transmissions

        // Send the name of the connected device back to the UI Activity
        mConnectedSocket=socket;  //before sendMessage             //~@@@@I~
        Message msg = mHandler.obtainMessage(BTControl.MESSAGE_DEVICE_NAME);//~@@@@R~
        Bundle bundle = new Bundle();
//      bundle.putString(BTControl.DEVICE_NAME, device.getName()); //~@@@@R~//~vat3R~
        bundle.putString(BTControl.DEVICE_NAME,BTControl.getName(device));//~vat3I~
        msg.setData(bundle);
        if (Dump.Y) Dump.println("BTService:before sendmsg");      //~@@@2I~
        mHandler.sendMessage(msg);

        if (Dump.Y) Dump.println("BTService:connected return");    //~@@@2I~
        setState(STATE_CONNECTED);
      }                                                            //~v101I~
        if (Dump.Y) Dump.println("BTService:SYNC connected return");//~v101I~
    }

    /**                                                            //~1AebI~
     * Stop connect threads                                        //~1AebI~
     */                                                            //~1AebI~
    public boolean stopConnect() {                                 //~1AebR~
    	Boolean rc=false;                                          //~1AebI~
        if (Dump.Y) Dump.println("BTService:stopConnect SYNC");    //~1AebI~
      synchronized(this)                                           //~1AebI~
      {                                                            //~1AebI~
        if (mConnectThread != null)                                //~1AebI~
		{                                                          //~1AebI~
        	if (Dump.Y) Dump.println("BTService:cancel connectThread");//~1AebI~
			mConnectThread.cancel(); mConnectThread = null;        //~1AebI~
            rc=true;                                               //~1AebI~
		}                                                          //~1AebI~
      }                                                            //~1AebI~
        setState(STATE_NONE);                                      //~1AebM~
        if (Dump.Y) Dump.println("BTService:stopConnect SYNC return rc="+rc);//~1AebR~
        return rc;                                                 //~1AebI~
    }                                                              //~1AebI~


    /**
     * Indicate that the connection attempt failed and notify the UI Activity.
     */
    private void connectionFailed() {
        if (AG.aBTI.swDestroy)                                      //~@@@2I~//~1AebR~
        	return;                                                //~@@@2I~
    	notifyFailure(CONN_FAILED);                                 //~@@@2I~
        setState(STATE_LISTEN);

        // Send a failure message back to the Activity
    }

    /**
     * Indicate that the connection was lost and notify the UI Activity.
     */
    public  void connectionLost() {                                //~@@@@I~
        if (AG.aBTI.swDestroy)                                      //~@@@2I~//~1AebR~
            return;                                                //~@@@2I~
    	notifyFailure(CONN_LOST);                                   //~@@@2I~
        setState(STATE_LISTEN);
        if (AG.aBTI.swDestroy)                                         //~@@@2I~//~1AebR~
            return;                                                //~@@@2I~
        // Send a failure message back to the Activity
        Message msg = mHandler.obtainMessage(BTControl.MESSAGE_TOAST);//~@@@@R~
        Bundle bundle = new Bundle();
        bundle.putString(BTControl.TOAST, "Device connection was lost");//~@@@@R~
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }

    /**
     * This thread runs while listening for incoming connections. It behaves
     * like a server-side client. It runs until a connection is accepted
     * (or until cancelled).
     */
    private class AcceptThread extends Thread {
        // The local server socket
        private BluetoothServerSocket mmServerSocket;              //~1AebR~
        private String mSocketType;                                //~v101I~
        private boolean swCancel=false;                            //~@@@@I~
        private boolean acceptSecure;                              //~v101I~

        public AcceptThread(boolean secure) {                      //~v101I~
            BluetoothServerSocket tmp = null;
            mSocketType = secure ? "Secure":"Insecure";            //~v101I~
            acceptSecure=secure;                                   //~v101I~

            // Create a new listening server socket
            try {
              	if (secure)                                        //~v101I~
		        	tmp = mAdapter.listenUsingRfcommWithServiceRecord(NAME_SECURE, MY_UUID_SECURE);//~v101I~
                else                                               //~v101I~
		        	tmp = mAdapter.listenUsingInsecureRfcommWithServiceRecord(NAME_INSECURE, MY_UUID_INSECURE);//~v101I~
            } catch (IOException e) {
                Dump.println(e,"AcceptThread:listenUsingRfcommWithServiceRecord");//~@@@@I~
                // Send a failure message back to the Activity     //~@@@@I~
			    notifyFailureAccept(mSocketType);                  //~v101R~
            }
            catch(SecurityException e)                             //~vat5I~
            {                                                      //~vat5I~
              	Dump.println(e,"BTService.AcceptThread");          //~vat5I~
            }                                                      //~vat5I~
            mmServerSocket = tmp;
        }

        public void run() {
            if (Dump.Y) Dump.println("AcceptThread:run() BEGIN mAcceptThread" + this);//~@@@@I~
            setName("AcceptThread" + mSocketType);                 //~v101I~
            BluetoothSocket socket = null;

            // Listen to the server socket if we're not connected
//          while (mState != STATE_CONNECTED) {                    //~1AebR~
            while (true)	//until stopAccept()                   //~1AebI~
 			{                                                      //~1AebI~
		        setState(STATE_LISTEN);                            //~1AebI~
                try                                                //~1AebR~
				{                                                  //~1AebI~
                    // This is a blocking call and will only return on a
                    // successful connection or an exception
	            	if (Dump.Y) Dump.println("AcceptThread:accept() secure="+acceptSecure+",mmServerSocket="+mmServerSocket.toString());//~@@@2I~//~1AbcI~
                    AG.RemoteStatusAccept=(acceptSecure?AG.RS_BTLISTENING_SECURE:AG.RS_BTLISTENING_INSECURE);                //~@@@2R~//~v101R~
                    socket = mmServerSocket.accept();
                    AG.RemoteStatusAccept&=~(acceptSecure?AG.RS_BTLISTENING_SECURE:AG.RS_BTLISTENING_INSECURE);//~v101I~
	            	if (Dump.Y) Dump.println("AcceptThread:accept() accepted socket="+socket.toString()+",secure="+acceptSecure);//~@@@2I~//~v101R~
                }                                                  //~1AebR~
				catch (Exception e)                                //~1AebI~
				{                                                  //~1AebI~
                    AG.RemoteStatusAccept&=~(acceptSecure?AG.RS_BTLISTENING_SECURE:AG.RS_BTLISTENING_INSECURE);//~v101I~
					if (swCancel)                                  //~@@@@I~
                    {                                              //~@@@@I~
	                    if (Dump.Y) Dump.println("AcceptThread:serverSocket accept canceled");//~@@@@I~
                    }                                              //~@@@@I~
                    else                                           //~@@@@I~
                    {                                              //~@@@2I~
	                    Dump.println(e,"AcceptThread:run accept()");//~@@@@R~
                    }                                              //~@@@2M~
                    break;
                }
//              try                                                //~1AebR~
//              {                                                  //~1AebR~
//                  if (Dump.Y) Dump.println("AcceptThread mmServerSoket close()="+mmServerSocket.toString());//~1AebR~
//                  mmServerSocket.close();                        //~1AebR~
//              }                                                  //~1AebR~
//  			catch (Exception ex)                               //~1AebI~
//  			{                                                  //~1AebI~
//                    Dump.println(ex,"AcceptThread:Server Socket Close at IOException");//~1AebR~
//              }                                                  //~1AebR~
//              mmServerSocket=null;                               //~1AebR~

                // If a connection was accepted
		        if (Dump.Y) Dump.println("AcceptThread:cancel discovery after accepted");//~1AbZI~
				requestDiscover(mAdapter,0/*not start discovery*/);//~1AbZI~
                if (socket != null)                                //~1AebR~
				{                                                  //~1AebI~
            		if (Dump.Y)  Dump.println("acceptThread run SYNC accepted start");//~v101R~
                    synchronized (BTService.this)                  //~1AebR~
					{                                              //~1AebI~
                        switch (mState)                            //~1AebR~
						{                                          //~1AebI~
                        case STATE_LISTEN:
                        case STATE_CONNECTING:
                            // Situation normal. Start the connected thread.
            				if (Dump.Y)  Dump.println("acceptThread run connected socket="+socket.toString());//~@@@2I~
                            if (acceptSecure)                      //~1A60I~
//  					        UView.showToastLong(Utils.getStr(R.string.AcceptedBTSecureConnection,socket.getRemoteDevice().getName()));//~1A60R~//~1AebR~//~vat3R~
    					        UView.showToastLong(Utils.getStr(R.string.AcceptedBTSecureConnection,BTControl.getName(socket.getRemoteDevice())));//~vat3I~
                            else                                   //~1A60I~
//  					        UView.showToastLong(Utils.getStr(R.string.AcceptedBTInSecureConnection,socket.getRemoteDevice().getName()));//~1A60R~//~1AebR~//~vat3R~
    					        UView.showToastLong(Utils.getStr(R.string.AcceptedBTInSecureConnection,BTControl.getName(socket.getRemoteDevice())));//~vat3I~
                            connected(socket, socket.getRemoteDevice());
                            break;
                        case STATE_NONE:
                        case STATE_CONNECTED:
                            // Either not ready or already connected. Terminate new socket.
//                          try                                    //~1AebR~//~vat5R~
//  						{                                      //~1AebI~//~vat5R~
//          				    if (Dump.Y)  Dump.println("acceptThread run connected,but close() by mstate="+mState+",socket="+socket.toString());//~@@@2R~//~vat5R~
//                              socket.close();                    //~vat5R~
//                          }                                      //~1AebR~//~vat5R~
//  						catch (IOException e)                  //~1AebI~//~vat5R~
//  						{                                      //~1AebI~//~vat5R~
//                              Dump.println(e,"AcceptThread:run Could not close unwanted socket");//~@@@@I~//~vat5R~
//                          }                                      //~vat5R~
          				    if (Dump.Y)  Dump.println("acceptThread run connected,but close() by mstate="+mState+",socket="+socket.toString());//~vat5I~
    						closeSocket(socket);                   //~vat5I~
                            break;
                        }
                    }
            		if (Dump.Y)  Dump.println("acceptThread run SYNC accepted mState="+mState);//~v101I~//~1AbFI~//~1AebR~
                }
            }
            mmServerSocket=null;                                   //~1AebI~
            if (Dump.Y) Dump.println("END mAcceptThread");         //~@@@@I~
        }

        public void cancel()                                       //~1AebR~
 		{                                                          //~1AebI~
            if (Dump.Y) Dump.println("AcceptThread:cancel " + this);//~@@@@I~
            if (mmServerSocket==null)                              //~v101I~
            {                                                      //~v101I~
	            if (Dump.Y) Dump.println("AcceptThread:cancel mmserversocket:null");//~v101I~
            	return;                                            //~v101I~
            }                                                      //~v101I~
            if (Dump.Y) Dump.println("AcceptThread cancel() close() serversocket="+mmServerSocket.toString());//~@@@2R~
//          try                                                    //~1AebR~//~vat5R~
//  		{                                                      //~1AebI~//~vat5R~
                swCancel=true;                                     //~@@@@I~
//              mmServerSocket.close();                            //~vat5R~
//          }                                                      //~1AebR~//~vat5R~
//  		catch (IOException e)                                  //~1AebI~//~vat5R~
//  		{                                                      //~1AebI~//~vat5R~
//                Dump.println(e,"AcceptThread:cancel:close");     //~@@@@I~//~vat5R~
//          }                                                      //~vat5R~
    		closeServerSocket(mmServerSocket);                     //~vat5R~
        }
    }

//***************************************************************************//~@@@2I~
    @TargetApi(15)                                                 //~1AbNI~
    private void displayMyUuid(BluetoothDevice Pdevice)            //~1AbNI~
    {                                                              //~1AbNI~
//  	ParcelUuid[] uuids=Pdevice.getUuids();                     //~1AbNI~//~1AbZR~//~vat5I~
    	ParcelUuid[] uuids=new ParcelUuid[0];                      //~vat5I~
      try                                                          //~vat5I~
      {                                                            //~vat5I~
    	uuids=Pdevice.getUuids();                                  //~vat5I~
      }                                                            //~vat5I~
      catch(SecurityException e)                                   //~vat5I~
      {                                                            //~vat5I~
        Dump.println(e,"BTService.displayMyUuid");                 //~vat5I~
      }                                                            //~vat5I~
    	for (int ii=0;ii<uuids.length;ii++)                        //~1AbNI~
        	if (Dump.Y) Dump.println("BTService connectThread my uuid"+ii+"="+uuids[ii].getUuid());//~1AbNI~
    }                                                              //~1AbNI~
    /**
     * This thread runs while attempting to make an outgoing connection
     * with a device. It runs straight through; the connection either
     * succeeds or fails.
     */
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private String mSocketType;                                //~v101I~
        private boolean swCancel=false;                            //~@@@2I~
        private boolean swSecure;                                  //~1A60I~

        public ConnectThread(BluetoothDevice device,boolean secure) {//~v101I~
            mmDevice = device;
            BluetoothSocket tmp = null;
            mSocketType = secure ? "Secure" : "Insecure";          //~v101I~
            swSecure=secure;                                       //~1A60I~

            // Get a BluetoothSocket for a connection with the
            // given BluetoothDevice
	        if (Dump.Y) Dump.println("ConnectThread:createRfComm secure="+secure);//~v101I~
            try {
	                if (secure)                                    //~v101I~
  	              		tmp = device.createRfcommSocketToServiceRecord(MY_UUID_SECURE);//~v101I~
                    else                                           //~v101I~
  	              		tmp = device.createInsecureRfcommSocketToServiceRecord(MY_UUID_INSECURE);//~v101I~
            } catch (IOException e) {
                Dump.println(e,"ConnectThread:createRfcommSocketToServiceRecord");//~@@@@I~
            }
            catch(SecurityException e)                             //~vat5I~
            {                                                      //~vat5I~
            	Dump.println(e,"BTService.connectThread");         //~vat5I~
            }                                                      //~vat5I~
            mmSocket = tmp;
            if (Dump.Y) Dump.println("BTService connectThread secure="+mSocketType+",connected mmSocket="+mmSocket.toString());//~@@@2I~//~1AebR~
        }

        public void run() {
            if (Dump.Y) Dump.println("ConnectThread:BEGIN mConnectThread");//~@@@@I~
            setName("ConnectThread" + mSocketType);                //~v101I~

            // Always cancel discovery because it will slow down a connection
	  //    if (Dump.Y) Dump.println("ConnectThread:connect() cancel discovery before connect discovering="+mAdapter.isDiscovering());//~1AbNI~
//  		if (mAdapter.isDiscovering())                          //~1AbNI~//~vat5R~
//  		{                                                      //~1AbNI~//~vat5R~
//            mAdapter.cancelDiscovery();                          //~@@@2R~//~vat5R~
    		if (cancelDiscoveryIfDiscovering(mAdapter)) //issued cancel//~vat5I~
            {                                                      //~vat5I~
              try                                                  //~1A64I~
              {                                                    //~1A64I~
              	Thread.sleep(300); //300ms                         //~1A64I~
              }                                                    //~1A64I~
              catch(InterruptedException e)                        //~1A64I~
              {                                                    //~1A64I~
              }                                                    //~1A64I~
	          if (Dump.Y) Dump.println("ConnectThread:connect() cancel discovery end before connect");//~v101I~
            }                                                      //~1AbNI~

            // Make a connection to the BluetoothSocket
            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
	            if (Dump.Y) Dump.println("ConnectThread:connect() mmSocket="+mmSocket.toString());//~@@@2I~//~v101R~
                mmSocket.connect();
	            if (Dump.Y) Dump.println("ConnectThread:connect() returned");//~v101I~
//          } catch (Exception e) {                                //~@@@2I~//~vat5R~
            }                                                      //~vat5I~
            catch(SecurityException e)                             //~vat5I~
            {                                                      //~vat5I~
                Dump.printlnNoMsg(e,"BTService:ConnectThread:run:connect");//~vat5I~
		        String exceptionMsg=e.getMessage();                //~vat5I~
    			if (exceptionMsg.equals(CONFIRM_ACCEPT))	//"Service discovery failed"//~vat5I~
					UView.showToastLong(R.string.ErrConnectConfirmAccept);//~vat5I~
                connectionFailed();                                //~vat5I~
	            if (Dump.Y) Dump.println("BTService:ConnectThread:mmSocket close()="+mmSocket.toString());//~vat5I~
	    		closeSocket(mmSocket);                             //~vat5I~
	            if (Dump.Y) Dump.println("BTService:ConnectThread:mmSocket closeed");//~vat5I~
                return;                                            //~vat5I~
            }                                                      //~vat5I~
			catch (Exception e)                                    //~vat5I~
			{                                                      //~vat5I~
//              if (!swCancel)                                     //~@@@2I~//~1AebR~
//                  Dump.println(false/*messagedialog*/,e,"BTService:ConnectThread:run:connect");//~@@@@I~//~@@@2R~//~1AebR~
                    Dump.printlnNoMsg(e,"BTService:ConnectThread:run:connect");//~1AebR~
		        String exceptionMsg=e.getMessage();                //~1A64I~
    			if (exceptionMsg.equals(CONFIRM_ACCEPT))	//"Service discovery failed"//~1A64I~
					UView.showToastLong(R.string.ErrConnectConfirmAccept);//~1A64I~//~1AebR~
                connectionFailed();                                //~@@@2R~//~1AebM~
                // Close the socket
	            if (Dump.Y) Dump.println("BTService:ConnectThread:mmSocket close()="+mmSocket.toString());//~@@@2I~
//              try {                                              //~vat5R~
//                  mmSocket.close();                              //~vat5R~
//              } catch (IOException e2) {                         //~vat5R~
//              	Dump.println(e,"BTService:ConnectThread:run:close");//~@@@@I~//~vat5R~
//              }                                                  //~vat5R~
	    		closeSocket(mmSocket);                             //~vat5I~
                // Start the service over to restart listening mode
	            if (Dump.Y) Dump.println("BTService:ConnectThread:mmSocket closeed");//~v101I~
                return;
            }
	        if (Dump.Y) Dump.println("ConnectThread:cancel discovery after connected");//~1AbZI~
			requestDiscover(mAdapter,0/*not start discovery*/);    //~1AbZI~

            // Reset the ConnectThread because we're done
            if (Dump.Y)  Dump.println("connectThread run SYNC connected");//~v101I~
            synchronized (BTService.this) {                        //~@@@@R~
                mConnectThread = null;
            }
            if (Dump.Y)  Dump.println("connectThread run SYNC connected exit");//~v101I~

            // Start the connected thread
            if (Dump.Y)  Dump.println("connectThread run connected socket="+mmSocket.toString());//~@@@2I~
            if (swSecure)                                          //~1A60I~
//  			UView.showToastLong(Utils.getStr(R.string.ConnectedBTSecureConnection,mmDevice.getName()));//~1A60R~//~1AebR~//~vat3R~
    			UView.showToastLong(Utils.getStr(R.string.ConnectedBTSecureConnection,BTControl.getName(mmDevice)));//~vat3I~
            else                                                   //~1A60I~
//  			UView.showToastLong(Utils.getStr(R.string.ConnectedBTInSecureConnection,mmDevice.getName()));//~1A60R~//~1AebR~//~vat3R~
    			UView.showToastLong(Utils.getStr(R.string.ConnectedBTInSecureConnection,BTControl.getName(mmDevice)));//~vat3I~
            connected(mmSocket, mmDevice);
        }

        public void cancel() {
//          try {                                                  //~vat5R~
            	if (Dump.Y) Dump.println("ConnectThread cancel() close socket="+mmSocket.toString());//~@@@2I~
                swCancel=true;                                     //~@@@2I~
//              mmSocket.close();                                  //~vat5R~
//          } catch (IOException e) {                              //~vat5R~
//              Dump.println(e,"ConnectThread:cancel close()");    //~@@@@I~//~vat5R~
//          }                                                      //~vat5R~
    		closeSocket(mmSocket);                                 //~vat5I~
            if (Dump.Y) Dump.println("ConnectThread cancel() closed");//~v101I~
        }
    }
//***************************************************************************//~@@@2R~
    /**
     * This thread runs during a connection with a remote device.
     * It handles all incoming and outgoing transmissions.
     */

        /**
         * Write to the connected OutStream.
         * param buffer  The bytes to write
         */
//***************************************************************************//~@@@@I~
	public static InputStream getBTInputStream(BluetoothSocket Psocket)//~@@@@I~
    {                                                              //~@@@@I~
    	InputStream s=null;                                        //~@@@@I~
		try                                                        //~@@@@I~
    	{                                                          //~@@@@I~
			s=Psocket.getInputStream();                            //~@@@@I~
		}                                                          //~@@@@I~
		catch (Exception e)                                        //~@@@@I~
		{                                                          //~@@@@I~
        	Dump.println(e,"getBTInputStream");                    //~@@@@I~
		}                                                          //~@@@@I~
        return s;                                                  //~@@@@I~
    }                                                              //~@@@@I~
//***************************************************************************//~@@@@I~
	public static OutputStream getBTOutputStream(BluetoothSocket Psocket)//~@@@@I~
    {                                                              //~@@@@I~
    	OutputStream s=null;                                       //~@@@@I~
		try                                                        //~@@@@I~
    	{                                                          //~@@@@I~
			s=Psocket.getOutputStream();                           //~@@@@I~
		}                                                          //~@@@@I~
		catch (Exception e)                                        //~@@@@I~
		{                                                          //~@@@@I~
        	Dump.println(e,"getBTOutputStream");                   //~@@@@I~
		}                                                          //~@@@@I~
        return s;                                                  //~@@@@I~
    }                                                              //~@@@@I~
//***************************************************************************//~@@@@I~
	public boolean acceptNext()                                    //~@@@@R~
    {                                                              //~@@@@I~
    	if (Dump.Y) Dump.println("acceptNext:"+mAcceptThread+",swNFC="+AG.aBTI.swNFC+",secureNFC="+AG.aBTI.swSecureNFC+",secureNonNfc="+AG.aBTI.swSecureNonNFCAccept);     //~1A64I~//~1AbbR~//~1AbtI~//~1AebR~
        if (AG.aBTI.swNFC && !AG.aBTI.swSecureNFC)                   //~1AbbI~//~1AebR~
        	return false;                                          //~1AbbI~
        if (!AG.aBTI.swNFC && !AG.aBTI.swSecureNonNFCAccept)         //~1AbtI~//~1AebR~
        	return false;                                          //~1AbtI~
        if (mAcceptThread == null) {                               //~@@@@I~
            mAcceptThread = new AcceptThread(true/*secure*/);      //~v101I~
    	  	if (mAcceptThread.mmServerSocket==null)                              //~@@@@I~//~v101R~
            	mAcceptThread =null;                               //~@@@@I~
            else                                                   //~@@@@I~
            	mAcceptThread.start();                             //~@@@@R~
            return true;  //started thread                         //~@@@@I~
        }                                                          //~@@@@I~
        return false;	//now alive                                //~@@@@I~
    }                                                              //~@@@@I~
//***************************************************************************//~v101I~
	public boolean acceptNextInSecure()                            //~v101I~
    {                                                              //~v101I~
    	if (Dump.Y) Dump.println("acceptNextInSecure:"+mAcceptThread+",swNFC="+AG.aBTI.swNFC+",secureNFC="+AG.aBTI.swSecureNFC+",swSecureNonNFC="+AG.aBTI.swSecureNonNFCAccept);//~1AbbI~//~1AbtR~//~1AbZI~//~1AebR~
        if (AG.aBTI.swNFC && AG.aBTI.swSecureNFC)                    //~1AbbI~//~1AebR~
        	return false;                                          //~1AbbI~
        if (!AG.aBTI.swNFC && AG.aBTI.swSecureNonNFCAccept)          //~1AbtI~//~1AebR~
        	return false;                                          //~1AbtI~
        if (mAcceptThreadInSecure == null) {                       //~v101I~
            mAcceptThreadInSecure = new AcceptThread(false/*insecure*/);//~v101I~
    	  	if (mAcceptThreadInSecure.mmServerSocket==null)        //~v101R~
            	mAcceptThreadInSecure =null;                       //~v101I~
            else                                                   //~v101I~
            	mAcceptThreadInSecure.start();                      //~v101I~
            return true;  //started thread                         //~v101I~
        }                                                          //~v101I~
        return false;	//now alive                                //~v101I~
    }                                                              //~v101I~
//***************************************************************************//~@@@2I~
	public boolean stopListen()                                    //~@@@2I~//~v101R~
    {                                                              //~@@@2I~
    	boolean rc=false;                                          //~@@@2I~
        if (Dump.Y) Dump.println("stopListen SYNC start");         //~v101I~
      synchronized(this)                                           //~v101I~
      {                                                            //~v101I~
        if (mAcceptThread != null)                                 //~@@@2I~
		{                                                          //~@@@2I~
        	if (Dump.Y) Dump.println("BTService:cancel AcceptThread");//~@@@2I~
			mAcceptThread.cancel();                                //~@@@2I~
			mAcceptThread = null;                                  //~@@@2I~
            rc=true;                                               //~@@@2I~
		}                                                          //~@@@2I~
        if (mAcceptThreadInSecure != null)                         //~v101I~
		{                                                          //~v101I~
        	if (Dump.Y) Dump.println("BTService:cancel AcceptThreadInsecure");//~v101I~
			mAcceptThreadInSecure.cancel();                        //~v101I~
			mAcceptThreadInSecure = null;                          //~v101I~
            rc=true;                                               //~v101I~
		}                                                          //~v101I~
      }                                                            //~v101I~
        setState(STATE_NONE);                                      //~1AebI~
        if (Dump.Y) Dump.println("stopListen SYNC end");           //~v101I~
        return rc;                                                 //~@@@2I~
    }                                                              //~@@@2I~
//***************************************************************************//~v101I~
	public static void requestDiscover(BTService Pservice,BluetoothAdapter Padapter,int Pdiscover)//~v101R~
    {                                                              //~v101I~
        if (Dump.Y) Dump.println("static requestDiscovery start Pservice="+Pservice); //~v101R~//~1AbKI~
        if (Pservice!=null)                                        //~v101I~
      	synchronized(Pservice)                                     //~v101R~
      	{                                                          //~v101I~
			if (Dump.Y) Dump.println("BTService requestDiscovery SYNC start");//~v101I~
			requestDiscover(Padapter,Pdiscover);                    //~v101I~
			if (Dump.Y) Dump.println("BTService requestDiscovery SYNC end");//~v101I~
        }                                                          //~v101I~
        else                                                       //~v101I~
        {                                                          //~v101I~
			if (Dump.Y) Dump.println("BTService requestDiscovery not SYNC start");//~1AbKI~
			requestDiscover(Padapter,Pdiscover);                    //~v101I~
			if (Dump.Y) Dump.println("BTService requestDiscovery not SYNC end");//~1AbKI~
      	}                                                          //~v101I~
        if (Dump.Y) Dump.println("static requestDiscovery end");   //~v101R~
    }                                                              //~v101I~
//***************************************************************************//~v101I~
	public static void requestDiscover(BluetoothAdapter Padapter,int Pdiscover)//~v101I~
    {                                                              //~v101I~
        if (Dump.Y) Dump.println("requestDiscover start Pdiscover="+Pdiscover);      //~v101I~//~1AbtI~
			if (Dump.Y) Dump.println("BTService requestDiscovery adapter="+Padapter);//~v101I~
//  		if (Padapter.isDiscovering())                          //~v101I~//~vat5R~
//  		{                                                      //~v101I~//~vat5R~
//  			if (Dump.Y) Dump.println("BTService:requestDiscovery cancelDiscover issue cancel");//~v101I~//~vat5R~
//  			Padapter.cancelDiscovery();                        //~v101I~//~vat5R~
//  		    if (Dump.Y) Dump.println("BTService requestDiscovery cancelDiscover end");//~v101I~//~1AbtI~//~vat5R~
//  		}                                                      //~v101I~//~vat5R~
    		cancelDiscoveryIfDiscovering(Padapter);                //~vat5I~
            if (Pdiscover==1)    //start                           //~v101I~
            {                                                      //~v101I~
                if (Dump.Y) Dump.println("BTService requestDiscovery:startDiscovery");//~v101I~//~1AbtI~
             try                                                   //~vat5I~
             {                                                     //~vat5I~
              boolean rc=                                          //~1AbKI~
                Padapter.startDiscovery();                         //~v101I~
                if (Dump.Y) Dump.println("BTService requestDiscovery:startDiscovery end rc="+rc);//~v101I~//~1AbKI~
             }                                                     //~vat5I~
             catch(SecurityException e)                            //~vat5I~
             {                                                     //~vat5I~
             	Dump.println(e,"BTService:requestDiscover");       //~vat5I~
             }                                                     //~vat5I~
            }                                                      //~v101I~
        if (Dump.Y) Dump.println("requestDiscovery sub end");      //~v101I~
    }                                                              //~v101I~
//**************************************************************************//~v101I~
//*get device list pair of name,addr                               //~v101I~
//**************************************************************************//~v101I~
    public  static String[] getPairedDeviceList(BTService Pservice) //~v101R~
    {                                                              //~v101I~
      	String[] sa;                                               //~v101I~
        if (Dump.Y) Dump.println("BTService getPairDevice start"); //~v101R~
    	if (Pservice!=null)	//after service initialized            //~v101I~
        {                                                          //~v101I~
	        if (Dump.Y) Dump.println("BTService getPairDevice SYNC start");//~v101I~
	        synchronized(Pservice)                                 //~v101R~
      		{                                                      //~v101R~
//          	if (Dump.Y) Dump.println("BTService getPairDevice cancelDiscovery,isDiscovering="+Pservice.mAdapter.isDiscovering());//~1AbNI~//~vat5R~
//  		  if (Pservice.mAdapter.isDiscovering())               //~1AbNI~//~vat5R~
//  			Pservice.mAdapter.cancelDiscovery();               //~v101I~//~vat5R~
    		  cancelDiscoveryIfDiscovering(Pservice.mAdapter);     //~vat5I~
	        	if (Dump.Y) Dump.println("BTService getPairDevice cancelDiscovery end");//~v101I~
				sa=getDeviceList(Pservice.mAdapter);                        //~v101I~
            }                                                      //~v101I~
	        if (Dump.Y) Dump.println("BTService getPairDevice SYNC end");//~v101I~
        }                                                          //~v101I~
        else                                                       //~v101I~
        {                                                          //~v101I~
//      	BluetoothAdapter adapter=BluetoothAdapter.getDefaultAdapter();//~v101I~//~vam3R~
        	BluetoothAdapter adapter=BTControl.getDefaultAdapter();//~vam3I~
			sa=getDeviceList(adapter);                             //~v101I~
        }                                                          //~v101I~
        if (Dump.Y) Dump.println("BTService getPairDevice end");   //~v101R~
        return sa;                                                 //~v101I~
    }                                                              //~v101I~
//**************************************************************************//~v101I~
//*get device list pair of name,addr                               //~v101I~
//**************************************************************************//~v101I~
    public  static String[] getDeviceList(BluetoothAdapter Padapter)//~v101I~
    {                                                              //~v101I~
        BluetoothAdapter bta=Padapter;                             //~v101I~
        if (bta==null)                                             //~v101I~
        	return null;                                           //~v101I~
        if (Dump.Y) Dump.println("BTService getDeviceList start"); //~v101I~
//      Set<BluetoothDevice> pairedDevices = bta.getBondedDevices();//~v101I~//~vat5R~
        Set<BluetoothDevice> pairedDevices=null;                   //~vat5I~
      try                                                          //~vat5I~
      {                                                            //~vat5I~
        pairedDevices = bta.getBondedDevices();                    //~vat5I~
      }                                                            //~vat5I~
      catch(SecurityException e)                                   //~vat5I~
      {                                                            //~vat5I~
       	Dump.println(e,"BTService:getDeviceList");                 //~vat5I~
        return null;                                               //~vat5I~
      }                                                            //~vat5I~
                                                                   //~v101I~
        int sz=pairedDevices.size();                               //~v101I~
        if (sz==0)                                                 //~v101I~
            return null;                                           //~v101I~
        String[] sa=new String[sz*2];                              //~v101I~
        int ii=0;                                                  //~v101I~
        for (BluetoothDevice device : pairedDevices)               //~v101I~
        {                                                          //~v101I~
//          sa[ii*2]=device.getName();                             //~v101I~//~vat3R~
            sa[ii*2]=BTControl.getName(device);                    //~vat3I~
            sa[ii*2+1]=device.getAddress();                        //~v101I~
            if (Dump.Y) Dump.println("getPairDeviceList="+sa[ii*2]+"="+sa[ii*2+1]);//~v101I~
            ii++;                                                  //~v101I~
        }                                                          //~v101I~
        if (Dump.Y) Dump.println("BTService getDevice end");       //~v101I~
        return sa;                                                 //~v101I~
    }                                                              //~v101I~
//******************************************                       //~1AebI~
//     * Stop all threads                                          //~1AebI~
//******************************************                       //~1AebI~
    public void stop()                                             //~1AebR~
	{                                                              //~1AebI~
        if (Dump.Y) Dump.println("BTService:stop SYNC");                //~@@@@I~//~v101R~//~1AebM~
        stopConnect();                                             //~1AebI~
        stopListen();                                              //~1AebI~
        if (Dump.Y) Dump.println("BTService:stop SYNC return");    //~v101I~//~1AebM~
    }                                                              //~1AebM~
//******************************************                       //~1AebI~
    public void onDisconnectedIP(String Pdevname,boolean PswServer)//~1AebR~
    {                                                              //~1AebR~
		if (Dump.Y) Dump.println("BTService:onDisconnectedIP dev="+Pdevname+",swServer="+PswServer);//~1AebI~
        if (!PswServer)                                            //~1AebI~
		    notifyStatusChanged(STATE_NONE);                       //~1AebI~
    }                                                              //~1AebR~
//******************************************                       //~1AebI~
//*from BTDiscover by ACL_DISCONNECTED                             //~1AebI~
//******************************************                       //~1AebI~
    public void onDisconnectedIP()                                 //~1AebI~
    {                                                              //~1AebI~
        int old=getState();                                        //~1AebI~
        if (Dump.Y) Dump.println("BTService.onDisconnectedIP state old="+old);//~1AebI~
        if (old!=STATE_LISTEN)                                     //~1AebI~
		    notifyStatusChanged(STATE_NONE);                       //~1AebI~
    }                                                              //~1AebI~
//******************************************                       //~1AebI~
    public boolean notifyStatusChanged(int Pstatus)                //~1AebI~
    {                                                              //~1AebI~
        int old=getState();                                        //~1AebI~
        if (Dump.Y) Dump.println("BTService:notifyStatusChanged old="+old+",new="+Pstatus);//~1AebI~
        if (Pstatus!=old)                                          //~1AebI~
        {                                                          //~1AebI~
            setState(Pstatus);                                     //~1AebI~
            return true;                                           //~1AebI~
        }                                                          //~1AebI~
        return false;                                              //~1AebI~
    }                                                              //~1AebI~
//*******************************************                      //~vat5I~
//*rc:true:discovering, issued cancelDiscover                      //~vat5I~
//*******************************************                      //~vat5I~
    public static boolean cancelDiscoveryIfDiscovering(BluetoothAdapter Padapter)//~vat5I~
    {                                                              //~vat5I~
    	boolean rc=false;                                          //~vat5I~
        if (Dump.Y) Dump.println("BTService:cancelDiscoveryIfDiscovering adapter="+Padapter);//~vat5I~
//No effect    if (UView.isPermissionGranted(Manifest.permission.BLUETOOTH_CONNECT))//~vat5M~
//Effective    if (ContextCompat.checkSelfPermission(AG.activity,Manifest.permission.BLUETOOTH_CONNECT)== PackageManager.PERMISSION_GRANTED;//~vat5M~
        try                                                        //~vat5I~
        {                                                          //~vat5I~
			if (Padapter.isDiscovering())                          //~vat5I~
            {                                                      //~vat5I~
            	rc=true;                                           //~vat5I~
  				if (Dump.Y) Dump.println("BTService:cancelDiscoveryIfDiscaovering isDiscovering=True,issue cancel");//~vat5R~
              	Padapter.cancelDiscovery();                        //~vat5I~
            }                                                      //~vat5I~
        }                                                          //~vat5I~
        catch(SecurityException e)                                 //~vat5I~
        {                                                          //~vat5I~
               Dump.println(e,"BTService:getPairedDeviceList");    //~vat5I~
        }                                                          //~vat5I~
        if (Dump.Y) Dump.println("BTService:cancelDiscoveryIfDiscovering edit rc="+rc);//~vat5I~
        return rc;                                                 //~vat5I~
    }                                                              //~vat5I~
//*******************************************                      //~vat5I~
    public static void closeSocket(BluetoothSocket Psocket)        //~vat5I~
    {                                                              //~vat5I~
        if (Dump.Y) Dump.println("BTService:closeSocket socket="+Psocket);//~vat5I~
        try                                                        //~vat5I~
        {                                                          //~vat5I~
			Psocket.close();                                       //~vat5I~
        }                                                          //~vat5I~
        catch (IOException e)                                      //~vat5I~
		{                                                          //~vat5I~
        	Dump.println(e,"BTService:closeSocket");               //~vat5I~
        }                                                          //~vat5I~
        if (Dump.Y) Dump.println("BTService:closeSocket exit");    //~vat5I~
    }                                                              //~vat5I~
//*******************************************                      //~vat5I~
    public static void closeServerSocket(BluetoothServerSocket Psocket)//~vat5I~
    {                                                              //~vat5I~
        if (Dump.Y) Dump.println("BTService:closeServerSocket socket="+Psocket);//~vat5I~
        try                                                        //~vat5I~
        {                                                          //~vat5I~
			Psocket.close();                                       //~vat5I~
        }                                                          //~vat5I~
        catch (IOException e)                                      //~vat5I~
		{                                                          //~vat5I~
        	Dump.println(e,"BTService:closeServerSocket");         //~vat5I~
        }                                                          //~vat5I~
        if (Dump.Y) Dump.println("BTService:closeServerSocket exit");//~vat5I~
    }                                                              //~vat5I~
}
