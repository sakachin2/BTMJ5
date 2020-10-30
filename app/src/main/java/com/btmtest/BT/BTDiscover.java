//*CID://+v@@@R~:                             update#=   90;       //~v@@@R~
//*************************************************************************//~v101I~
//*************************************************************************//~v101I~

package com.btmtest.BT;                                               //~1AedI~//~1Af1I~

import com.btmtest.utils.Dump;                                     //~1Af1R~

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;

//import com.Ahsv.AView;                                           //~1Af1R~
//import com.Ahsv.Alert;                                           //~1Af1R~
import com.btmtest.R;                                              //~1Af1R~
import com.btmtest.utils.Alert;                                       //~1Af1R~
import com.btmtest.utils.URunnable;                                      //~1Af1R~//~v@@@R~
import com.btmtest.utils.Utils;
import com.btmtest.utils.UView;//~1Af1R~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
                                                                   //~1Af1I~
//import jagoclient.partner.BluetoothConnection;                   //~1Af1R~

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * This Activity appears as a dialog. It lists any paired devices and
 * devices detected in the area after discovery. When a device is chosen
 * by the user, the MAC address of the device is sent back to the parent
 * Activity in the result Intent.
 */
public class BTDiscover extends BroadcastReceiver                 //~@@@@R~
	implements URunnable.URunnableI                                          //~v101I~
{
	public static final int ACTION_FOUND=1;                        //~1AebI~//~v@@@M~
	public static final int ACTION_DISCOVERY_FINISHED=2;           //~1AebI~//~v@@@M~
	public static final int ACTION_SCAN_MODE_CHANGED=3;            //~1AebI~//~v@@@M~
	public static final int ACTION_STATE_CHANGED=4;                //~1AebI~//~v@@@M~
	public static final int ACTION_CONNECTION_STATE_CHANGED=5;     //~v@@@I~
	public static final int ACTION_BOND_STATE_CHANGED=6;           //~v@@@R~
	public static final int ACTION_PAIRING_REQUEST=7;              //~v@@@R~
	public static final int ACTION_UUID=7;//~1AebI~//~v@@@M~
    public static final int ACTION_ACL_CONNECTED=8;                //~v@@@R~
    public static final int ACTION_ACL_DISCONNECTED=9;             //~v@@@R~
                                                                   //~v@@@I~
	private static BTDiscover instBTD;                             //~v101I~
    private static final String TAG = "DeviceListActivity";
    private static final boolean D = true;

    // Return Intent extra
//  public static String EXTRA_DEVICE_ADDRESS = "device_address";  //~v@@@R~
    public static BTDiscover registeredReceiver;                   //~v@@@I~

    // Member fields
    private BluetoothAdapter mBtAdapter;
//    private ArrayAdapter<String> mPairedDevicesArrayAdapter;     //~v101R~
//    private ArrayAdapter<String> mNewDevicesArrayAdapter;        //~v101R~
    private LinkedList<String> devicelist;                         //~v101R~
    public static String[] newDevice;                              //~@@@@I~
    private boolean swDiscover;                                    //~v101I~
    private static String SbondingDeviceAddr,SbondingDeviceName;   //~1AbGI~
//***************************************************************************//~v@@@I~
    public BTDiscover()                                            //~@@@@R~
	{                                                              //~@@@@I~
        if (Dump.Y) Dump.println("BTDiscover constructor");//~1AbGI~//~v@@@R~
        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        instBTD=this;                                              //~v@@@R~
    }
//***************************************************************************//~v@@@I~
    public void doDiscovery()                //~@@@@I~             //~1Af1R~
	{                                                              //~@@@@I~
        if (Dump.Y) Dump.println("BTDiscover doDiscovery");        //~@@@@R~


        // If we're already discovering, stop it

        // Request discover from BluetoothAdapter
        if (Dump.Y) Dump.println("request startDiscovery adapter="+mBtAdapter.toString());//~v101R~
    	devicelist=new LinkedList<String>();                       //~v101I~
	    swDiscover=true;                                           //~v101I~
        startDiscover(mBtAdapter);                                 //~v101R~
        if (Dump.Y) Dump.println("request startDiscovery return"); //~v101I~
    }
//***************************************************************************//~v@@@I~
    // The BroadcastReceiver that listens for discovered devices and
    // changes the title when discovery is finished
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Dump.Y) Dump.println("BTDiscover.onReceive Broard receiver action="+action+",destroy="+AG.aBTI.swDestroy+",swDiscover="+swDiscover);//~@@@@I~//~v101R~//~1AbGI~//~1Af1R~//~v@@@R~
            if (AG.aBTI.swDestroy)                                  //~v101R~//~1Af1R~
            	return;                                            //~v101I~
          int actionid=0;                                          //~v@@@I~
          try                                                      //~v101I~
          {                                                        //~v101I~
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            	actionid=ACTION_FOUND;                             //~v@@@I~
                if (!swDiscover)                                   //~1AbGI~
              		return;                                        //~1AbGI~
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    String name=device.getName();                  //~@@@@I~
                    String addr=device.getAddress();               //~@@@@I~
   		   	      	if (Dump.Y) Dump.println("Broarcast receiver device="+name+",addr="+addr);//~@@@@I~
                    if (name==null)                                //~v@@@I~
                    {                                              //~v@@@I~
	              		return;                                    //~v@@@I~
                    }                                              //~v@@@I~
		        	devicelist.add(name);                          //~@@@@I~
		        	devicelist.add(addr);                          //~@@@@I~
                }
            // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            	actionid=ACTION_DISCOVERY_FINISHED;                //~v@@@I~
                if (!swDiscover)                                   //~1AbGI~
              		return;                                        //~1AbGI~
	            swDiscover=false;                                  //~v101I~
				if (devicelist.size()>0)                           //~@@@@I~
					newDevice=devicelist.toArray(new String[0]);   //~@@@@R~
                else                                               //~@@@@I~
					newDevice=null;                                //~@@@@I~
            	UView.showToastShort(AG.resource.getString(R.string.InfoBTDiscoverFinished,devicelist.size()/2));//~v@@@R~
	            if (Dump.Y) Dump.println("BTDiscover.onReceive ACTION_DISCOVERY_FINISHED newDevice="+Utils.toString(newDevice));//+v@@@I~
            }
            else                                                   //~1AbGI~
            if (BluetoothAdapter.ACTION_SCAN_MODE_CHANGED.equals(action))//~1AbGI~
            {                                                      //~1AbGI~
            	actionid=ACTION_SCAN_MODE_CHANGED;                 //~v@@@I~
	            int scanmode=intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE,BluetoothAdapter.ERROR);//~1AbGI~
	            int scanmodeold=intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_SCAN_MODE,BluetoothAdapter.ERROR);//~1AbGI~
                String smode="";                                   //~1AbGI~
                switch(scanmode)                                   //~1AbGI~
                {                                                  //~1AbGI~
                case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE://~1AbGI~
                	smode="ScanMode:CONNECTABLE_DISCOVERABLE";     //~1AbGI~
	        		UView.showToastLong(R.string.InfoBTDiscoverable);//~1AbGI~//~v@@@R~
                    break;                                         //~1AbGI~
                case BluetoothAdapter.SCAN_MODE_CONNECTABLE:       //~1AbGI~
                	smode="ScanMode:CONNECTABLE";                  //~1AbGI~
	        		UView.showToastLong(R.string.InfoBTConnectable);//~1AbGI~//~v@@@R~
                    break;                                         //~1AbGI~
                case BluetoothAdapter.SCAN_MODE_NONE:              //~1AbGI~
	        		UView.showToastLong(R.string.InfoBTNone);      //~1AbGI~//~v@@@R~
                	smode="ScanMode:NONE";                         //~1AbGI~
                    break;                                         //~1AbGI~
                }                                                  //~1AbGI~
	            if (Dump.Y) Dump.println("Broard receiver ACTION_SCAN_MODE_CHANGED scanmode old="+scanmodeold+"-->"+scanmode+"="+smode);//~1AbGI~//~v@@@R~
            }                                                      //~1AbGI~
            else                                                   //~1AbGI~
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) //local device status changed//~v@@@R~
            {                                                      //~1AbGI~
            	actionid=ACTION_STATE_CHANGED;                    //~v@@@I~
	            int state=intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR);//~1AbGI~
                String sstate="";                                  //~1AbGI~
                switch(state)                                      //~1AbGI~
                {                                                  //~1AbGI~
//                case BluetoothAdapter.STATE_DISCONNECTED://0       //~1AbGI~//~v@@@R~
//                    sstate="Bluetooth State disconnected";         //~1AbGI~//~v@@@R~
//                    break;                                         //~1AbGI~//~v@@@R~
//                case BluetoothAdapter.STATE_CONNECTING://1         //~1AbGI~//~v@@@R~
//                    sstate="Bluetooth State connecting";           //~1AbGI~//~v@@@R~
//                    break;                                         //~1AbGI~//~v@@@R~
//                case BluetoothAdapter.STATE_CONNECTED://2          //~1AbGI~//~v@@@R~
//                    sstate="Bluetooth State connected";            //~1AbGI~//~v@@@R~
//                    break;                                         //~1AbGI~//~v@@@R~
//                case BluetoothAdapter.STATE_DISCONNECTING://3      //~1AbGI~//~v@@@R~
//                    sstate="Bluetooth State disconnecting";        //~1AbGI~//~v@@@R~
//                    break;                                         //~1AbGI~//~v@@@R~
                case BluetoothAdapter.STATE_OFF:     //10          //~1AbGI~
                	sstate="Bluetooth State changed to OFF";       //~1AbGI~
    	            String msg=Utils.getStr(R.string.WarningBluetoothTurnedOff);//~1AbQR~//~v@@@R~
			    	int flag=Alert.BUTTON_POSITIVE;                //~1AbQR~
					Alert.showAlert(null/*title*/,msg,flag,null/*callback*/);//~1AbQR~
                    break;                                         //~1AbGI~
                case BluetoothAdapter.STATE_TURNING_ON:  //11      //~1AbGI~
                	sstate="Bluetooth State changed to Turning ON";//~1AbGI~
                    break;                                         //~1AbGI~
                case BluetoothAdapter.STATE_ON:      //12          //~1AbGI~
                	sstate="Bluetooth State changed to ON";        //~1AbGI~
                    break;                                         //~1AbGI~
                case BluetoothAdapter.STATE_TURNING_OFF: //13      //~1AbGI~
                	sstate="Bluetooth State changed to Turning OFF";//~1AbGI~
                    break;                                         //~1AbGI~
                }                                                  //~1AbGI~
	            if (Dump.Y) Dump.println("Broard receiver ACTION_STATE_CHANGED to "+state+"="+sstate);//~1AbGI~//~v@@@R~
            }                                                      //~1AbGI~
            else                                                   //~v@@@I~
            if (BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED.equals(action)) //local device connection status to a remote device//~v@@@I~
            {                                                      //~v@@@I~
            	actionid=ACTION_CONNECTION_STATE_CHANGED;          //~v@@@I~
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);//~v@@@I~
	            int state=intent.getIntExtra(BluetoothAdapter.EXTRA_CONNECTION_STATE,BluetoothAdapter.ERROR);//~v@@@I~
	            if (Dump.Y) Dump.println("Broard receiver ACTION_CONNECION_STATE_CHANGED remote=" +device.getName()+ ",state+="+state);//~v@@@R~
                String sstate="";
                switch(state)                                      //~v@@@I~
                {                                                  //~v@@@I~
                case BluetoothAdapter.STATE_DISCONNECTED://0       //~v@@@I~
                	sstate="Bluetooth State disconnected";         //~v@@@I~
                    break;                                         //~v@@@I~
                case BluetoothAdapter.STATE_CONNECTING://1         //~v@@@I~
                	sstate="Bluetooth State connecting";           //~v@@@I~
                    break;                                         //~v@@@I~
                case BluetoothAdapter.STATE_CONNECTED://2          //~v@@@I~
                	sstate="Bluetooth State connected";            //~v@@@I~
                    break;                                         //~v@@@I~
                case BluetoothAdapter.STATE_DISCONNECTING://3      //~v@@@I~
                	sstate="Bluetooth State disconnecting";        //~v@@@I~
                    break;                                         //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~1AbGI~
            if (BluetoothDevice.ACTION_PAIRING_REQUEST.equals(action))//~1AbGI~
            {                                                      //~1AbGI~
            	actionid=ACTION_PAIRING_REQUEST;                    //~v@@@I~
	            if (Dump.Y) Dump.println("Broard receiver ACTION_PARING_REQUEST");//~1AbGI~//~v@@@R~
            }                                                      //~1AbGI~
            else                                                   //~1AbGI~
            if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action))//~1AbGI~
            {                                                      //~1AbGI~
            	actionid=ACTION_BOND_STATE_CHANGED;                //~v@@@I~
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);//~1AbGI~
                String name="";                                    //~1AbGI~
                String addr="";                                    //~1AbGI~
                if (device!=null)                                  //~1AbGI~
                {                                                  //~1AbGI~
                	name=device.getName();                         //~1AbGI~
                	addr=device.getAddress();                      //~1AbGI~
                }                                                  //~1AbGI~
	            int state=intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE,BluetoothAdapter.ERROR);//~1AbGI~
	            int stateold=intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE,BluetoothAdapter.ERROR);//~1AbGI~
	            if (Dump.Y) Dump.println("Broard receiver ACTION_BOND_STATE_CHANGED name="+name+"="+addr+",state="+stateold+"->"+state);//~1AbGI~//~v@@@R~
                if (state==BluetoothDevice.BOND_BONDING)           //~1AbGI~
                {                                                  //~1AbGI~
                	SbondingDeviceAddr=addr;                       //~1AbGI~
                	SbondingDeviceName=name;                       //~1AbGI~
                }                                                  //~1AbGI~
            }                                                      //~1AbGI~
            else                                                   //~1AbGI~
            if (BluetoothDevice.ACTION_UUID.equals(action))        //~1AbGI~
            {                                                      //~1AbGI~
            	actionid=ACTION_UUID;                              //~v@@@I~
            	process_ActionUuid(intent);                        //~1AbGI~
            }                                                      //~1AbGI~
            else                                                   //~v@@@R~
            if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action))//~v@@@R~
            {                                                      //~v@@@R~
                actionid=ACTION_ACL_CONNECTED;                     //~v@@@R~
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);//~v@@@R~
                if (Dump.Y) Dump.println("Broard receiver ACTION_ACL_CONNECTED remote=" +device.getName());//~v@@@R~
            }                                                      //~v@@@R~
            else                                                   //~v@@@R~
            if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action))//~v@@@R~
            {                                                      //~v@@@R~
                actionid=ACTION_ACL_DISCONNECTED;                  //~v@@@R~
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);//~v@@@R~
                if (Dump.Y) Dump.println("Broard receiver ACTION_ACL_DISCONNECTED remote=" +device.getName());//~v@@@R~
                onDisconnectedIP();                                //~v@@@I~
            }                                                      //~v@@@R~
            AG.aBTMulti.onReceive(actionid,intent);    //->BTMulti    //~1Af1I~//~v@@@R~
		  }//try                                                   //~v101I~
          catch(Exception e)                                       //~v101R~
          {                                                        //~v101I~
          	Dump.println(e,"BTDiscover:onReceive");                //~v101I~
          }                                                        //~v101I~
          if (Dump.Y) Dump.println("Broard receiver return");  //~v101I~//~v@@@R~
    };
    //*********************************************************    //~@@@@I~
    public static void discover()             //~@@@@I~            //~1Af1R~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("BTDiscover start");              //~@@@@I~
        if (instBTD!=null)                                         //~v@@@I~
	        instBTD.doDiscovery();                                     //~@@@@I~//~v101R~//~1Af1R~//~v@@@R~
        return;                                                    //~@@@@I~
    }                                                              //~@@@@I~
    //*********************************************************    //~v101I~
    public static void cancelDiscover()       //~v101I~
    {                                                              //~v101I~
        if (Dump.Y) Dump.println("BTDiscover cancelDiscover");     //~v101I~
        if (instBTD!=null)                                         //~v@@@R~
        	instBTD.cancelDiscover(instBTD.mBtAdapter);                    //~v@@@I~
        return;                                                    //~v101I~
    }                                                              //~v101I~
    //*********************************************************    //~v101I~
    private void startDiscover(BluetoothAdapter Padapter)          //~v101R~
    {                                                              //~v101I~
        if (Dump.Y) Dump.println("BTDiscover startDiscover");      //~v@@@I~
    	requestDiscover(Padapter,1);                               //~v101I~
    }                                                              //~v101I~
    //*********************************************************    //~v101I~
    public void cancelDiscover(BluetoothAdapter Padapter)          //~v101I~
    {                                                              //~v101I~
        if (Dump.Y) Dump.println("BTDiscover cancelDiscover");     //~v@@@I~
        if (Padapter==null)                                        //~v@@@I~
        	return;                                                //~v@@@I~
    	requestDiscover(Padapter,0);                               //~v101I~
    }                                                              //~v101I~
    //*********************************************************    //~v101I~
    public void requestDiscover(BluetoothAdapter Padapter,int Pstart)//~v101I~
    {                                                              //~v101I~
        if (Dump.Y) Dump.println("BTDiscover requestDiscover");    //~v@@@I~
    	if (Padapter==null)                                        //~v101I~
        	return;                                                //~v101I~
        try                                                        //~v101I~
        {                                                          //~v101I~
            if (Dump.Y) Dump.println("BTDiscover requestDiscovery start="+Pstart+",setRunFuncSubThread:"+this);//~1AbGI~
		    URunnable.setRunFuncSubthread(this,0/*deley*/,Padapter,Pstart);//~v101R~
        }                                                          //~v101I~
        catch(Exception e)                                         //~v101I~
        {                                                          //~v101I~
        	Dump.println(e,"requestDiscover for adapter");          //~v101I~//~v@@@R~
        }                                                          //~v101I~
	    if (Dump.Y) Dump.println("BTDiscover requestDiscover return");//~v@@@R~
    }//~v101I~
    //*********************************************************    //~v101I~
    @Override
    public void URunnableCB(Object Pparmobj,int Pparmint)                   //~v101I~
    {                                                              //~v101I~
	    if (Dump.Y) Dump.println("BTDiscover runFunc:start");      //~v101R~
		BluetoothAdapter adapter=(BluetoothAdapter)Pparmobj;       //~v101I~
		try
		{
		    if (Dump.Y) Dump.println("BTDiscover.URunnableCB aBTI="+(AG.aBTI!=null));//~v@@@I~
		    if (Dump.Y) Dump.println("BTDiscover.URunnableCB mBTC="+(AG.aBTI.mBTC!=null));//~v@@@I~
        	if (AG.aBTI!=null)                                     //~v@@@I~
	        	if (AG.aBTI.mBTC!=null)                            //~v@@@I~
		        	AG.aBTI.mBTC.requestDiscover(adapter,Pparmint);         //~v101R~//~1Af1R~//~v@@@R~
		}//~v101I~
        catch(Exception e)                                         //~v101I~
        {                                                          //~v101I~
        	Dump.println(e,"BTDiscover:URunnableCB");  //~v101I~   //~v@@@R~
        }                                                          //~v101I~
	    if (Dump.Y) Dump.println("BTDiscover:URunnableCB:end");        //~v101R~//~v@@@R~
    }                                                              //~v101I~
//**************************************************************************//~@@@@I~//~v101I~
//*get device list pair of name,addr                               //~@@@@I~//~v101I~
//**************************************************************************//~@@@@I~//~v101I~
    public static String[] getPairDeviceList()                     //~@@@@R~//~v101I~
    {                                                              //~@@@@I~//~v101I~
        String[] sa=null;                                          //~v101I~
	    if (Dump.Y) Dump.println("BTDiscover getpairedDeviceList:start");//~v101I~
		try                                                        //~v101I~
		{                                                          //~v101I~
        	sa=AG.aBTI.mBTC.getPairedDeviceList();                  //~v101R~//~1Af1R~
		}                                                          //~v101I~
        catch(Exception e)                                         //~v101I~
        {                                                          //~v101I~
        	Dump.println(e,"BTDiscover getPairedDeviceLst");       //~v101I~
        }                                                          //~v101I~
	    if (Dump.Y) Dump.println("BTDiscover getpairedDeviceList:end list="+Arrays.toString(sa));//~v101I~//~v@@@R~
        return sa;                                                 //~@@@@I~//~v101I~
    }                                                              //~@@@@I~//~v101I~
//**************************************************************************//~1AbGI~
	private void process_ActionUuid(Intent intent)                 //~1AbGI~
    {                                                              //~1AbGI~
        if (AG.osVersion>=15)  //android4                          //~1AbGI~
        	process_ActionUuid15(intent);                          //~1AbGI~
    }                                                              //~1AbGI~
//**************************************************************************//~1AbGI~
	@TargetApi(15)                                                 //~1AbGI~
	private void process_ActionUuid15(Intent intent)               //~1AbGI~
    {                                                              //~1AbGI~
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);//~1AbGI~
        String name="";                                            //~1AbGI~
        String addr="";                                            //~1AbGI~
        if (device!=null)                                          //~1AbGI~
        {                                                          //~1AbGI~
            name=device.getName();                                 //~1AbGI~
            addr=device.getAddress();                              //~1AbGI~
        }                                                          //~1AbGI~
        if (Dump.Y) Dump.println("Broard receiver ACTION_UUID name="+name+"="+addr);//~1AbGI~//~v@@@R~
        Parcelable puuid[] = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_UUID);//~1AbGI~
        if (puuid!=null)                                           //~1AbGI~
        {                                                          //~1AbGI~
            for (int ii=0;ii<puuid.length;ii++)                    //~1AbGI~
                if (Dump.Y) Dump.println("Broard receiver uuid["+ii+"]="+((ParcelUuid)puuid[ii]).toString());//~1AbGI~//~v@@@R~
        }                                                          //~1AbGI~
    }                                                              //~1AbGI~
//**************************************************************************//~1AbGI~
//*cancel bonding process                                          //~1AbLR~
//**************************************************************************//~1AbLR~
    public static void resetBonding()                              //~1AbLR~
    {                                                              //~1AbLR~
	    if (Dump.Y) Dump.println("BTDiscover resetBonding name="+SbondingDeviceName+",addr="+SbondingDeviceAddr);//~1AbLR~
        String addr=SbondingDeviceAddr;                            //~1AbLR~
    	if (addr!=null && !addr.equals(""))                                      //~1AbLR~
        {                                                          //~1AbLR~
	        BluetoothAdapter adapter=BluetoothAdapter.getDefaultAdapter();//~1AbLR~
			BluetoothDevice device=adapter.getRemoteDevice(addr);  //~1AbLR~
            int state=device.getBondState();                       //~1AbLR~
		    if (Dump.Y) Dump.println("BTDiscover resetBonding status="+state);//~1AbLR~
			if (state==BluetoothDevice.BOND_BONDING)               //~1AbLR~
            {                                                      //~1AbGI~
				cancelBonding(device);                             //~1AbLR~
//  			removeBonding(device); //apply only if BONDED device/+1AbGI~//~1AbGI~
            }                                                      //~1AbGI~
        }                                                          //~1AbLR~
    }                                                              //~1AbLR~
//**************************************************************************//~1AbLI~
//*cancel bonding process                                          //~1AbLI~
//**************************************************************************//~1AbLI~
    public static boolean cancelBonding(BluetoothDevice Pdevice)   //~1AbLI~
    {                                                              //~1AbLI~
    	boolean rc=false;                                          //~1AbLI~
	    if (Dump.Y) Dump.println("BTDiscover cancelBonding");      //~1AbLI~
    	Method method;                                             //~1AbLI~
		try                                                        //~1AbLI~
		{                                                          //~1AbLI~
			method = Pdevice.getClass().getMethod("cancelBondProcess",(Class[])null);//~1AbLI~
			try                                                    //~1AbLI~
			{                                                      //~1AbLI~
				rc=(Boolean)method.invoke(Pdevice);                //~1AbLI~
            }                                                      //~1AbLI~
            catch (Exception e)                                    //~1AbLI~
            {                                                      //~1AbLI~
                Dump.println(e,"BTDiscover cancelBonding invoke"); //~1AbLI~
            }                                                      //~1AbLI~
		    if (Dump.Y) Dump.println("BTDiscover cancelBonding rc="+rc);//~1AbLI~
		}                                                          //~1AbLI~
		catch (NoSuchMethodException e)                            //~1AbLI~
		{                                                          //~1AbLI~
		    Dump.println(e,"BTDiscover cancelBonding getMethod");  //~1AbLI~
        }                                                          //~1AbLI~
        return rc;                                                 //~1AbLI~
    }                                                              //~1AbLI~
//**************************************************************************//~1AbGI~
//*cancel bonding process                                          //~1AbGI~
//**************************************************************************//~1AbGI~
    public static boolean removeBonding(BluetoothDevice Pdevice)   //~1AbGI~
    {                                                              //~1AbGI~
    	boolean rc=false;                                          //~1AbGI~
	    if (Dump.Y) Dump.println("BTDiscover removeBonding");      //~1AbGI~
    	Method method;                                             //~1AbGI~
		try                                                        //~1AbGI~
		{                                                          //~1AbGI~
			method = Pdevice.getClass().getMethod("removeBond",(Class[])null);//~1AbGI~
			try                                                    //~1AbGI~
			{                                                      //~1AbGI~
				rc=(Boolean)method.invoke(Pdevice);                //~1AbGI~
            }                                                      //~1AbGI~
            catch (Exception e)                                    //~1AbGI~
            {                                                      //~1AbGI~
                Dump.println(e,"BTDiscover removeBond invoke");    //~1AbGI~
            }                                                      //~1AbGI~
		    if (Dump.Y) Dump.println("BTDiscover removeBond rc="+rc);//~1AbGI~
		}                                                          //~1AbGI~
		catch (NoSuchMethodException e)                            //~1AbGI~
		{                                                          //~1AbGI~
		    Dump.println(e,"BTDiscover removeBond getMethod");     //~1AbGI~
        }                                                          //~1AbGI~
        return rc;                                                 //~1AbGI~
    }                                                              //~1AbGI~
//**************************************************************************//~v@@@I~
    public static void register()                                  //~v@@@R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("BTDiscover registerReceiver");   //~v@@@R~
        register(instBTD);                                         //~v@@@R~
    }                                                              //~v@@@I~
//**************************************************************************//~v@@@I~
    public static void register(BroadcastReceiver Preceiver)       //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("BTDiscover registerReceiver receiver!=null:"+(Preceiver!=null));//~v@@@R~
        if (Preceiver==null)                                       //~v@@@I~
        	return;                                                //~v@@@I~
        IntentFilter filter = new IntentFilter();                  //~v@@@I~
        filter.addAction(BluetoothDevice.ACTION_FOUND);            //~v@@@I~
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);//~v@@@I~
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);//~v@@@I~
        filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);//~v@@@I~
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED); //state change(bluetooth off/on)//~v@@@I~
        filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED); //state change(connecting/connected/disconnecting/disconnected)//~v@@@I~
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);   //~v@@@R~
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);//~v@@@R~
        if (AG.osVersion>=19)  //android4                          //~v@@@I~
        	filter.addAction(BluetoothDevice.ACTION_PAIRING_REQUEST);//~v@@@I~
        if (AG.osVersion>=15)  //android4                          //~1AbGI~//~v@@@M~
        	filter.addAction(BluetoothDevice.ACTION_UUID);//api15 android 4.03 4.04//~v@@@I~
        AG.activity.registerReceiver(Preceiver, filter);           //~v@@@I~
        registeredReceiver=(BTDiscover)Preceiver;                  //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v101I~//~v@@@M~
    public static void unregister()                                       //~v101I~//~v@@@I~
    {                                                              //~v101I~//~v@@@M~
        if (Dump.Y) Dump.println("BTDiscover unregister");         //~v101I~//~v@@@M~
        if (registeredReceiver!=null)                              //~v@@@I~
        {                                                          //~v@@@I~
			AG.activity.unregisterReceiver(registeredReceiver);                      //~@@@@I~//~v101I~//~v@@@I~
			registeredReceiver=null;                               //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v101I~//~v@@@M~
    //*********************************************************    //~v@@@I~
    public void onDisconnectedIP()                                 //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("BTDiscover.onDisconnectedIP");   //~v@@@I~
        if (AG.aBTI!=null)                                         //~v@@@I~
        	if (AG.aBTI.mBTC!=null)                                //~v@@@I~
        		if (AG.aBTI.mBTC.mChatService!=null)               //~v@@@I~
        			AG.aBTI.mBTC.mChatService.onDisconnectedIP();  //~v@@@I~
    }                                                              //~v@@@I~
}
