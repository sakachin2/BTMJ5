//*CID://+DATER~:                             update#=  116;       //~1Ac4R~//~@@@@R~//~9A03R~
//*************************************************************************//~1A65I~
//1Ac4 2015/07/06 WD:try disable wifi direct at unpair             //~1Ac4I~
//1Ac0 2015/07/06 for mutual exclusive problem of IP and wifidirect;try to use connectivityManager API//~1Ac0I~
//1Aby 2015/06/21 NFCWD:system settings id is not ACTION_WIREESS_SETTING but ACTION_WIFI_SETTINGS//~1AbyI~
//1Aa5 2015/04/20 test function for mdpi listview                  //~1Aa5I~
//1A6s 2015/02/17 move NFC starter from WifiDirect dialog to MainFrame//~1A6sI~
//1A6a 2015/02/10 NFC+Wifi support                                 //~1A6aI~
//1A67 2015/02/05 (kan)                                            //~1A67I~
//1A65 2015/01/29 implement Wifi-Direct function(>=Api14:android4.0)//~1A65I~
//*************************************************************************//~1A65I~
/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.btmtest.wifi;                                          //~1Ac4I~


//import android.annotation.TargetApi;                               //~1A65I~//~1Ac4R~
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.ChannelListener;
//import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.ProgDlg;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import com.btmtest.wifi.DeviceListFragment;

//import wifidirect.WDANFC;                                        //~1Ac4R~
                                                                   //~1A65I~
import static com.btmtest.StaticVars.AG;                           //~9721I~//~@@@@I~//~1Ac4I~

/**
 * An activity that uses WiFi Direct APIs to discover and connect with available
 * devices. WiFi Direct APIs are asynchronous and rely on callback mechanism
 * using interfaces to notify the application of operation success or failure.
 * The application should also register a BroadcastReceiver for notification of
 * WiFi state related events.
 */
//@TargetApi(AG.ICE_CREAM_SANDWICH)   //api14                           //~1A65R~//~1Ac4R~
//public class WiFiDirectActivity extends Activity implements ChannelListener, DeviceActionListener {//~1A65R~
//public class WiFiDirectActivity implements ChannelListener, DeviceListFragment.DeviceActionListener{//~9A04R~
public class WiFiDirectActivity implements ProgDlg.ProgDlgI ,ChannelListener, DeviceListFragment.DeviceActionListener//~9A04I~
{                                                                  //~9A04R~
    public static boolean Stestdevicelist=false;    //expand device list @@@@test//~1Aa5R~
    public static boolean Stestdevicelist_mdpi=false;    //set empty devcicelist for emulator test of mdpi @@@@test//~1Aa5R~
    public static final String TAG = "wifidirectdemo";
    protected static final int CONNECT_OK=1;                         //~1A6aI~//~1A6sR~
    protected static final int CONNECT_ER=2;                         //~1A6aI~//~1A6sR~
    protected static final int DISCONNECT_OK=3;                      //~1A6aI~//~1A6sR~
    protected static final int DISCONNECT_ER=4;                      //~1A6aI~//~1A6sR~
    protected static final int DISCONNECT_CANCEL_OK=5;               //~1A6aI~//~1A6sR~
    protected static final int DISCONNECT_CANCEL_ER=6;               //~1A6aI~//~1A6sR~
    protected static final int DISCONNECTED=7;                     //~1A6sI~
//  private WifiP2pManager manager;                                //~1A6sR~
//  protected WifiP2pManager manager;                              //~1A6sI~//~1Ac4R~
    public WifiP2pManager manager;                                 //~1Ac4I~
//  private boolean isWifiP2pEnabled = false;                      //~1A6sR~
    protected boolean isWifiP2pEnabled = false;                    //~1A6sI~
    private boolean retryChannel = false;

//  private final IntentFilter intentFilter = new IntentFilter();  //~1A6sR~
    protected final IntentFilter intentFilter = new IntentFilter();//~1A6sI~
//  private Channel channel;                                       //~1A6sR~
//  protected Channel channel;                                     //~1A6sI~//~1Ac4R~
    public Channel channel;                                        //~1Ac4I~
    private BroadcastReceiver receiver = null;
    private String connectProgressMsg;                             //~@@@@I~
    private int idProgress;                                        //~9A04I~
    public  static final int PROGRESS_CONNECT=1;                   //~9A04I~//~9A05R~
    public  static final int PROGRESS_DISCOVER=2;                  //~9A04I~//~9A05R~
	private WiFiDirectActivity aWifiDirectActivity;                //~9A04I~
    private boolean swProgDlg;                                     //+0124I~
    //*******************************************************************//~1A65I~
	public WiFiDirectActivity()                                    //~1A65I~
    {                                                              //~1A65R~
		aWifiDirectActivity=this;                                  //~9A04I~
	    onCreate();                                                //~1A65M~
    }                                                              //~1A65I~
    //*******************************************************************//~1A65I~
    /**
     * @param isWifiP2pEnabled the isWifiP2pEnabled to set
     */
    public void setIsWifiP2pEnabled(boolean isWifiP2pEnabled) {
        if (Dump.Y) Dump.println("WiFiDirectActivity.setIsWifiP2pEnabled enable="+isWifiP2pEnabled);//~1Ac4I~
        this.isWifiP2pEnabled = isWifiP2pEnabled;
    }

    //*******************************************************************//~1A65I~
//  @Override                                                      //~1A65R~
//  public void onCreate(Bundle savedInstanceState) {              //~1A65R~
    private void onCreate() {                                      //~1A65I~
//      super.onCreate(savedInstanceState);                        //~1A65R~
//      setContentView(R.layout.main);                             //~1A65R~
        if (Dump.Y) Dump.println("WiFiDirectActivity.onCreate");   //~1Ac4I~

        // add necessary intent values to be matched.

        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
//      channel = manager.initialize(this, getMainLooper(), null); //~1A65R~
        channel= manager.initialize(getContext(),getMainLooper(),this);//~1A65R~//~@@@@R~
        if (Dump.Y) Dump.println("WiFiDirectActivity.onCreate initialize return channel="+(channel==null?"null":channel.toString()));//~1Ac4I~
    }

    //*******************************************************************//~1A65I~
    /** register the BroadcastReceiver with the intent values to be matched */
//  @Override                                                      //~1A65R~
//  public void onResume() {                                       //~1A65R~
//      super.onResume();                                          //~1A65R~
//      receiver = new WiFiDirectBroadcastReceiver(manager, channel, this);//~1A65R~
//      getContext().registerReceiver(receiver, intentFilter);     //~1A65R~
//  }                                                              //~1A65R~
    public void onResume() {                                       //~1A65I~
        if (Dump.Y) Dump.println("WiFiDirectActivity.onResume");   //~1Ac4I~
    	registerReceiver();                                        //~1A65I~
    }                                                              //~1A65I~
    public void registerReceiver()                                 //~1A65I~
    {                                                              //~1A65I~
        if (Dump.Y) Dump.println("WiFiDirectActivity.registerReceiver old receiver="+ Utils.toString(receiver));//~@@@@R~//~0115R~
        if (receiver==null)                                        //~@@@@R~
        {                                                          //~@@@@I~
        	receiver = new WiFiDirectBroadcastReceiver(manager, channel, this);//~1A65I~//~@@@@R~
        	getContext().registerReceiver(receiver, intentFilter);     //~1A65I~//~@@@@R~
        }                                                          //~@@@@I~
    }                                                              //~1A65I~
    //*******************************************************************//~1A65I~
//  @Override                                                      //~1A65R~
//  public void onPause() {                                        //~1A65R~
//      super.onPause();                                           //~1A65R~
//      unregisterReceiver(receiver);                              //~1A65R~
//  }                                                              //~1A65R~
    public void onPause()                                          //~1A65I~
	{                                                              //~1A65I~
        unregisterReceiver();                                      //~1A65I~
    }                                                              //~1A65I~
    public void unregisterReceiver()                               //~1A65I~
	{                                                              //~1A65I~
        if (Dump.Y) Dump.println("WiFiDirectActivity.unregisterReceiver receiver="+Utils.toString(receiver));//~@@@@R~
		if (receiver==null)                                        //~1A65I~
        	return;                                                //~1A65I~
        getContext().unregisterReceiver(receiver);                 //~1A65I~
        receiver=null;                                             //~1A65I~
    }                                                              //~1A65I~
    //*******************************************************************//~1A65I~
    /**
     * Remove all peers and clear all fields. This is called on
     * BroadcastReceiver receiving a state change event.
     */
//  public void resetData() {                                      //~9A05R~
    public int resetData() {                                       //~9A05I~
        if (Dump.Y) Dump.println("WiFiDirectActibity:resetData");  //~@@@@I~
//      DeviceListFragment fragmentList = (DeviceListFragment) getFragmentManager()//~1A65R~
//              .findFragmentById(R.id.frag_list);                 //~1A65R~
        DeviceListFragment fragmentList =WDA.getDeviceListFragment();//~1A65R~
//      DeviceDetailFragment fragmentDetails = (DeviceDetailFragment) getFragmentManager()//~1A65R~
//              .findFragmentById(R.id.frag_detail);               //~1A65R~
        DeviceDetailFragment fragmentDetails =WDA.getDeviceDetailFragment();//~1A65I~
        int ctr=0;                                                 //~9A05I~
        if (fragmentList != null) {
//          fragmentList.clearPeers();                             //~9A05R~
            ctr=fragmentList.clearPeers();                         //~9A05I~
        }
        if (fragmentDetails != null) {
            fragmentDetails.resetViews();
        }
        if (Dump.Y) Dump.println("WiFiDirectActibity:resetData clered ctr="+ctr);//~9A05I~
        return ctr;                                                //~9A05I~
    }

//    @Override                                                    //~1A65R~
//    public boolean onCreateOptionsMenu(Menu menu) {              //~1A65R~
//        MenuInflater inflater = getMenuInflater();               //~1A65R~
//        inflater.inflate(R.menu.action_items, menu);             //~1A65R~
//        return true;                                             //~1A65R~
//    }                                                            //~1A65R~

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
//  @Override                                                      //~1A65R~
//  public boolean onOptionsItemSelected(MenuItem item) {          //~1A65R~
    public boolean buttonAction(int Pbtnid){                       //~1A65R~
        if (Dump.Y) Dump.println("WiFiDirectActibity:buttonAction btnid="+Integer.toHexString(Pbtnid)+"iswifip2penabled="+isWifiP2pEnabled);//~1Ac4R~
//      switch (item.getItemId()) {                                //~1A65R~
        switch (Pbtnid) {                                          //~1A65I~
//          case R.id.atn_direct_enable:                           //~1A67R~//~@@@@R~
            case DeviceListFragment.BTNID_P2PENABLE:               //~1A67I~//~@@@@R~
                if (Dump.Y) Dump.println("WiFiDirectActibity:buttonAction:p2p enable");//~1A67I~//~@@@@R~
                if (manager != null && channel != null) {          //~@@@@R~

                    // Since this is the system wireless settings activity, it's//~@@@@R~
                    // not going to send us a result. We will be notified by//~@@@@R~
                    // WiFiDeviceBroadcastReceiver instead.        //~@@@@R~
                  getActivity().                                   //~1A65R~//~@@@@R~
//                  startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));//~1AbyR~//~@@@@R~
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));//~1AbyI~//~@@@@R~
                } else {                                           //~@@@@R~
//                  Log.e(TAG, "channel or manager is null");      //~1A65R~//~@@@@R~
                    if (Dump.Y) Dump.println("channel or manager is null");//~1A65I~//~@@@@R~
                }                                                  //~@@@@R~
                return true;                                       //~@@@@R~
//          case R.id.atn_direct_discover:                         //~1A65R~
            case DeviceListFragment.BTNID_DISCOVER:                //~1A65R~
                if (Dump.Y) Dump.println("WiFiDirectActivity.buttonAction discover");//~0124I~
                isWifiP2pEnabled=true;  //TODO test                //~1Ac4I~
                if (!isWifiP2pEnabled) {
//                  Toast.makeText(WiFiDirectActivity.this, R.string.p2p_off_warning,//~1A65R~
                    Toast.makeText(getContext(), R.string.p2p_off_warning,//~1A65I~
                            Toast.LENGTH_SHORT).show();
                    if (Stestdevicelist_mdpi)//emulator missing wifi func//~1Aa5R~
                    {                                              //~1Aa5R~
                		final DeviceListFragment fragment = WDA.getDeviceListFragment();//~1A6sI~
                		WifiP2pDeviceList pl=new WifiP2pDeviceList();//~1A6sI~
                		fragment.onPeersAvailable(pl);             //~1A6sI~
                    }                                              //~1Aa5R~
                    return true;
                }
//                if (Dump.Y) Dump.println("WiFiDirectActivity buttonAction:discover");//~1A6aI~//~0124R~
////              final DeviceListFragment fragment = (DeviceListFragment) getFragmentManager()//~1A65R~//~0124R~
////                      .findFragmentById(R.id.frag_list);         //~1A65R~//~0124R~
//                final DeviceListFragment fragment = WDA.getDeviceListFragment();//~1A65I~//~0124R~
////              fragment.onInitiateDiscovery();                    //~@@@@R~//~0124R~
//                manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {//~0124R~

//                    @Override                                    //~0124R~
//                    public void onSuccess() {                    //~0124R~
////                      Toast.makeText(WiFiDirectActivity.this, "Discovery Initiated",//~1A65R~//~0124R~
////                      Toast.makeText(getContext(),WDA.getResourceString(R.string.DiscoveryInitiated),//~1A65R~//~@@@@R~//~0124R~
////                              Toast.LENGTH_SHORT).show();        //~@@@@R~//~0124R~
////                      fragment.onInitiateDiscovery();            //~@@@@I~//~9A04R~//~0124R~
//                        onInitiateDiscovery();                     //~9A04I~//~0124R~
//                        if (Dump.Y) Dump.println("WiFiDirectActivity discover onSuccess");//~1A6aI~//~0124R~
//                    }                                            //~0124R~

//                    @Override                                    //~0124R~
//                    public void onFailure(int reasonCode) {      //~0124R~
////                      Toast.makeText(WiFiDirectActivity.this, "Discovery Failed : " + reasonCode,//~1A65R~//~0124R~
////                        Toast.makeText(getContext(),WDA.getResourceString(R.string.DiscoveryFailedReason) + reasonCode,//~1A65R~//~@@@@R~//~0124R~
//////                              Toast.LENGTH_SHORT).show();        //~1A65R~//~@@@@R~//~0124R~
////                                Toast.LENGTH_LONG).show();         //~1A65I~//~@@@@R~//~0124R~
//                        showReason(R.string.DiscoveryFailedReason,reasonCode);//~@@@@I~//~0124R~
//                        if (Dump.Y) Dump.println("WiFiDirectActivity discover onFailure reason="+reasonCode);//~1A6aI~//~0124R~
//                    }                                            //~0124R~
//                });                                              //~0124R~
				initiateDiscovery(true/*progDlg*/);                //~0124R~
                return true;
//            case DeviceListFragment.BTNID_NFC:                     //~1A6aI~//~1Ac4R~
//                startNFC();                                        //~1A6aI~//~1Ac4R~
//                return true;                                       //~1A6aI~//~1Ac4R~
            default:
//              return super.onOptionsItemSelected(item);          //~1A65R~
                return false;                                      //~1A65I~
        }
    }

    @Override
    public void showDetails(WifiP2pDevice device) {
      try                                                          //~1A65I~
      {                                                            //~1A65I~
//      DeviceDetailFragment fragment = (DeviceDetailFragment) getFragmentManager()//~1A65R~
//              .findFragmentById(R.id.frag_detail);               //~1A65R~
        DeviceDetailFragment fragment = WDA.getDeviceDetailFragment();//~1A65R~
        fragment.showDetails(device);
      }                                                            //~1A65R~
      catch(Exception e)                                           //~1A65I~
      {                                                            //~1A65I~
        Dump.println(e,"WiFiDirectActivity:showDetail");           //~1A65I~
      }                                                            //~1A65I~
    }
    //***********************************************************************************//~0124I~
    private void initiateDiscovery(boolean PswProgDlg)             //~0124R~
    {                                                              //~0124I~
        if (Dump.Y) Dump.println("WiFiDirectActivity.initiateDiscover");//+0124R~
        final DeviceListFragment fragment = WDA.getDeviceListFragment();//~0124I~
        swProgDlg=PswProgDlg;                                      //+0124R~
        manager.discoverPeers(channel, new WifiP2pManager.ActionListener()//~0124I~
        {                                                          //~0124I~
            @Override                                              //~0124I~
            public void onSuccess() {                              //~0124I~
            	if (swProgDlg)                                     //~0124I~
	                onInitiateDiscovery();                         //~0124R~
                if (Dump.Y) Dump.println("WiFiDirectActivity discover onSuccess");//~0124I~
            }                                                      //~0124I~
                                                                   //~0124I~
            @Override                                              //~0124I~
            public void onFailure(int reasonCode) {                //~0124I~
                showReason(R.string.DiscoveryFailedReason,reasonCode);//~0124I~
                if (Dump.Y) Dump.println("WiFiDirectActivity discover onFailure reason="+reasonCode);//~0124I~
            }                                                      //~0124I~
        });                                                        //~0124I~
    }                                                              //~0124I~
    public void setConnectProgressMsg(String PprogressMsg)         //~@@@@I~
    {                                                              //~@@@@I~
      	if (Dump.Y) Dump.println("WiFiDirectActivity:setConnectProgressMsg msg="+PprogressMsg);//~@@@@I~
    	connectProgressMsg=PprogressMsg;                           //~@@@@I~
    }                                                              //~@@@@I~
    //***********************************************************************************//~9A03I~
    @Override
    public void connect(WifiP2pConfig config) {                    //~@@@@R~
      try                                                          //~1A65I~
      {                                                            //~1A65I~
      	if (Dump.Y) Dump.println("WiFiDirectActivity:connect device="+config.deviceAddress);    //~1A65I~//~1A6aR~
//      cancelDiscovery();	//TODO test                            //~0124R~
        initiateDiscovery(false/*PswProgDlg*/); //TODO test        //~0124R~
        manager.connect(channel, config, new ActionListener() {

            @Override
            public void onSuccess() {
		        DeviceDetailFragment fragment = WDA.getDeviceDetailFragment();//~@@@@I~
                if (connectProgressMsg!=null)                      //~@@@@I~
                {                                                  //~@@@@I~
//              	fragment.progressDialog = fragment.progressDialogShow(R.string.ProgressDialogTitle,connectProgressMsg,true, true);//~@@@@R~//~9A04R~
    				dismissProgDlg(0);	//if current showing       //~9A04I~//~9A05R~
//				    idProgress=PROGRESS_CONNECT;                   //~9A04M~//~9A05R~
//              	ProgDlg.showProgDlg(R.string.WifiDirect,connectProgressMsg,false,aWifiDirectActivity/*ProgDlgI*/);//~9A04M~//~9A05R~
    				showProgDlg(PROGRESS_CONNECT,connectProgressMsg);//~9A05I~
                	connectProgressMsg=null;                       //~@@@@I~//~9A04R~
                }                                                  //~@@@@I~
//              if (connectProgressMsg!=null)                      //~@@@@I~//~9A03R~
                // WiFiDirectBroadcastReceiver will notify us. Ignore for now.
		      	if (Dump.Y) Dump.println("WiFiDirectActivity:connect:onSuccess");//~1A65I~
                notifyConnected(CONNECT_OK,null);                  //~1A6aR~
            }

            @Override
            public void onFailure(int reason) {
		      	if (Dump.Y) Dump.println("WiFiDirectActivity:connect:onFailure reason="+reason);//~1A65I~//~1A6aR~
                connectProgressMsg=null;                           //~@@@@I~
//              Toast.makeText(WiFiDirectActivity.this, "Connect failed. Retry.",//~1A65R~
//              Toast.makeText(getContext(),WDA.getResourceString(R.string.ConnectFailedRetry),//~1A65R~//~1A6aR~
//                      Toast.LENGTH_SHORT).show();                //~1A65R~
//                      Toast.LENGTH_LONG).show();                 //~1A65I~//~1A6aR~
//              String msg=WDA.getResourceString(R.string.ConnectFailedRetry);//~1A6aI~//~@@@@R~
                String msg=strReason(R.string.ConnectFailedRetry,reason);//~@@@@I~
                notifyConnected(CONNECT_ER,msg);                   //~1A6aR~
        		if (Dump.Y) Dump.println("WiFiDirectActivity:connect failure reason="+reason);//~1A65I~
            }
        });
      }                                                            //~1A65I~
      catch(Exception e)                                           //~1A65I~
      {                                                            //~1A65I~
        Dump.println(e,"WiFiDirectActivity:connect");              //~1A65I~
      }                                                            //~1A65I~
    }
//    //***********************************************************************************//~9A03I~//~9A04R~
//    public void connectAsOwner(WifiP2pConfig config)               //~9A03I~//~9A04R~
//    {                                                              //~9A03I~//~9A04R~
//        try                                                        //~9A03I~//~9A04R~
//        {                                                          //~9A03I~//~9A04R~
//            if (Dump.Y) Dump.println("WiFiDirectActivity:connectAsOwner device="+config.deviceAddress);//~9A03I~//~9A04R~
//            manager.createGroup(channel,new ActionListener()       //~9A03I~//~9A04R~
//            {                                                      //~9A03I~//~9A04R~
//                @Override                                          //~9A03I~//~9A04R~
//                public void onSuccess()                            //~9A03I~//~9A04R~
//                {                                                  //~9A03I~//~9A04R~
//                    DeviceDetailFragment fragment = WDA.getDeviceDetailFragment();//~9A03I~//~9A04R~
//                    if (connectProgressMsg!=null)                  //~9A03I~//~9A04R~
//                    {                                              //~9A03I~//~9A04R~
//                        fragment.progressDialog = fragment.progressDialogShow(R.string.ProgressDialogTitle,connectProgressMsg,true, true);//~9A03I~//~9A04R~
//                        connectProgressMsg=null;                   //~9A03I~//~9A04R~
//                    }                                              //~9A03I~//~9A04R~
////                  if (connectProgressMsg!=null)                  //~9A03I~//~9A04R~
//                    // WiFiDirectBroadcastReceiver will notify us. Ignore for now.//~9A03I~//~9A04R~
//                    if (Dump.Y) Dump.println("WiFiDirectActivity.connectAsOwner createGroup onSuccess");//~9A03I~//~9A04R~
////                  notifyConnected(CONNECT_OK,null);              //~9A03I~//~9A04R~
//                }                                                  //~9A03I~//~9A04R~
//                                                                   //~9A03I~//~9A04R~
//                @Override                                          //~9A03I~//~9A04R~
//                public void onFailure(int reason)                  //~9A03I~//~9A04R~
//                {                                                  //~9A03I~//~9A04R~
//                    if (Dump.Y) Dump.println("WiFiDirectActivity:connect:onFailure reason="+reason);//~9A03I~//~9A04R~
//                    connectProgressMsg=null;                       //~9A03I~//~9A04R~
//                    String msg=strReason(R.string.ConnectFailedRetry,reason);//~9A03I~//~9A04R~
////                  notifyConnected(CONNECT_ER,msg);               //~9A03I~//~9A04R~
//                    if (Dump.Y) Dump.println("WiFiDirectActivity.connectAsOwner createGroup failed reason="+reason);//~9A03I~//~9A04R~
//                }                                                  //~9A03I~//~9A04R~
//            });                                                    //~9A03I~//~9A04R~
//        }                                                          //~9A03I~//~9A04R~
//        catch(Exception e)                                         //~9A03I~//~9A04R~
//        {                                                          //~9A03I~//~9A04R~
//            Dump.println(e,"WiFiDirectActivity:connect");          //~9A03I~//~9A04R~
//        }                                                          //~9A03I~//~9A04R~
//    }                                                              //~9A03I~//~9A04R~
    //***********************************************************************************//~9A03I~

//  @Override                                                      //~0116R~
    public void disconnect() {
      try                                                          //~1A65I~
      {                                                            //~1A65I~
      	if (Dump.Y) Dump.println("WiFiActivity:disconnect");       //~1A65I~
//      final DeviceDetailFragment fragment = (DeviceDetailFragment) getFragmentManager()//~1A65R~
//              .findFragmentById(R.id.frag_detail);               //~1A65R~
        final DeviceDetailFragment fragment =WDA.getDeviceDetailFragment();//~1A65R~
        fragment.resetViews();
//        manager.removeGroup(channel, new ActionListener() {      //~9A05R~
//            @Override                                            //~9A05R~
//            public void onFailure(int reasonCode) {              //~9A05R~
////              Log.d(TAG, "Disconnect failed. Reason :" + reasonCode);//~1A65R~//~9A05R~
//                if (Dump.Y) Dump.println("WiFiDirectActivity:disconnect removeGroup failure reason="+reasonCode);//~1A65I~//~1Ac0I~//~9A05R~
////              String msg="Disconnect failed. Reason :" + reasonCode;//~1A6aI~//~@@@@R~//~9A05R~
//                String msg=strReason(R.string.RemoveFailedReason,reasonCode);//~@@@@I~//~9A05R~
//                notifyConnected(DISCONNECT_ER,msg);                 //~1A6aR~//~9A05R~
//            }                                                    //~9A05R~
//            @Override                                            //~9A05R~
//            public void onSuccess() {                            //~9A05R~
//                if (Dump.Y) Dump.println("WiFiDirectActivity:disconnect removeGroup() success");//~1Ac0I~//~9A05R~
////              fragment.getView().setVisibility(View.GONE);       //~1Ac4R~//~9A05R~
//                notifyConnected(DISCONNECT_OK,null);               //~1A6aR~//~9A05R~
////              WDA.setWifiDirectStatus(false);//@@@@test          //~1Ac4I~//~9A05R~
//            }                                                    //~9A05R~
//        });                                                      //~9A05R~
		removeGroup();                                  //~9A05I~
      }                                                            //~1A65I~
      catch(Exception e)                                           //~1A65I~
      {                                                            //~1A65I~
        Dump.println(e,"WiFiDirectActivity:disconnect");           //~1A65I~
      }                                                            //~1A65I~
    }
//**********************************************************************//~9A05I~
	private void removeGroup()                                      //~9A05I~//~0116R~
    {                                                              //~9A05I~
      	if (Dump.Y) Dump.println("WiFiActivity:removeGroup channel="+Utils.toString(channel));//~9A05I~
        manager.removeGroup(channel, new ActionListener() {        //~9A05I~
            @Override                                              //~9A05I~
            public void onFailure(int reasonCode) {                //~9A05I~
        		if (Dump.Y) Dump.println("WiFiDirectActivity.removeGroup.onFailure reason="+reasonCode);//~9A05I~
                String msg=strReason(R.string.RemoveFailedReason,reasonCode);//~9A05I~
    			notifyConnected(DISCONNECT_ER,msg);                //~9A05I~
                                                                   //~9A05I~
            }                                                      //~9A05I~
            @Override                                              //~9A05I~
            public void onSuccess() {                              //~9A05I~
        		if (Dump.Y) Dump.println("WiFiDirectActivity.removeGroup.onSuccess");//~9A05I~
    			notifyConnected(DISCONNECT_OK,null);               //~9A05I~
                WDA.getDeviceListFragment().setConnected(false);   //~9A05I~
            }                                                      //~9A05I~
                                                                   //~9A05I~
        });                                                        //~9A05I~
    }                                                              //~9A05I~
//**********************************************************************//~9A05I~
    @Override   //ChannelListener                                  //~0113R~
    public void onChannelDisconnected() {
      try                                                          //~1A65I~
      {                                                            //~1A65I~
      	if (Dump.Y) Dump.println("WiFiActivity:onChannelDisconnected");//~1A67I~
        // we will try once more
        if (manager != null && !retryChannel) {
//          Toast.makeText(this, "Channel lost. Trying again", Toast.LENGTH_LONG).show();//~1A65R~
            Toast.makeText(getContext(),WDA.getResourceString(R.string.ChannelLostRetry), Toast.LENGTH_LONG).show();//~1A65R~
            resetData();
            retryChannel = true;
//          manager.initialize(this, getMainLooper(), this);       //~1A65R~
            manager.initialize(getContext(), getMainLooper(), this);//~1A65I~
        } else {
//          Toast.makeText(this,                                   //~1A65R~
            Toast.makeText(getContext(),                           //~1A65I~
//                  "Severe! Channel is probably lost premanently. Try Disable/Re-Enable P2P.",//~1A65R~
                    WDA.getResourceString(R.string.ChannelLostEnableP2P),//~1A65I~
                    Toast.LENGTH_LONG).show();
        }
      }                                                            //~1A65I~
      catch(Exception e)                                           //~1A65I~
      {                                                            //~1A65I~
        Dump.println(e,"WiFiDirectActivity:onChannelDisconnected");//~1A65I~
      }                                                            //~1A65I~
    }
//**********************************************************************//~9A04R~
    @Override	//DeviceListFragment.DeviceActionListener          //~0113I~
    public void cancelDisconnect() {

      try                                                          //~1A65I~
      {                                                            //~1A65I~
        /*
         * A cancel abort request by user. Disconnect i.e. removeGroup if
         * already connected. Else, request WifiP2pManager to abort the ongoing
         * request
         */
        if (Dump.Y) Dump.println("WiFiDirectActivity.cancelDisconnect");//~9A04I~
        if (manager != null) {
//          final DeviceListFragment fragment = (DeviceListFragment) getFragmentManager()//~1A65R~
//                  .findFragmentById(R.id.frag_list);             //~1A65R~
            final DeviceListFragment fragment = WDA.getDeviceListFragment();//~1A65I~
	        if (Dump.Y) Dump.println("WiFiDirectActivity.cancelDisconnect device="+Utils.toString(fragment.getDevice()));//~9A04I~
            if (fragment.getDevice() == null
                    || fragment.getDevice().status == WifiP2pDevice.CONNECTED) {
                disconnect();
            } else if (fragment.getDevice().status == WifiP2pDevice.AVAILABLE
                    || fragment.getDevice().status == WifiP2pDevice.INVITED) {

		        if (Dump.Y) Dump.println("WiFiDirectActivity.cancelDisconnect issue cancelConnect");//~9A04I~
				cancelConnect();                                   //~9A04I~
//                manager.cancelConnect(channel, new ActionListener() {//~9A04R~

//                    @Override                                    //~9A04R~
//                    public void onSuccess() {                    //~9A04R~
////                      Toast.makeText(WiFiDirectActivity.this, "Aborting connection",//~1A65R~//~9A04R~
////                      Toast.makeText(getContext(),WDA.getResourceString(R.string.AbortingConnection),//~1A65R~//~1A6aR~//~9A04R~
////                              Toast.LENGTH_SHORT).show();        //~1A6aR~//~9A04R~
//                        String msg=WDA.getResourceString(R.string.AbortingConnection);//~1A6aI~//~9A04R~
//                        notifyConnected(DISCONNECT_CANCEL_OK,msg);   //~1A6aR~//~9A04R~
//                    }                                            //~9A04R~
//                    @Override                                    //~9A04R~
//                    public void onFailure(int reasonCode) {      //~9A04R~
////                      Toast.makeText(WiFiDirectActivity.this,    //~1A65R~//~9A04R~
////                      Toast.makeText(getContext(),               //~1A65I~//~1A6aR~//~9A04R~
////                              "Connect abort request failed. Reason Code: " + reasonCode,//~1A65R~//~9A04R~
////                              WDA.getResourceString(R.string.ConnectAbortReason) + reasonCode,//~1A65I~//~1A6aR~//~9A04R~
////                              Toast.LENGTH_SHORT).show();        //~1A6aR~//~9A04R~
////                      String msg=WDA.getResourceString(R.string.ConnectAbortReason) + reasonCode;//~1A6aI~//~@@@@R~//~9A04R~
//                        String msg=strReason(R.string.ConnectAbortReason,reasonCode);//~@@@@I~//~9A04R~
//                        notifyConnected(DISCONNECT_CANCEL_ER,msg);   //~1A6aR~//~9A04R~
//                    }                                            //~9A04R~
//                });                                              //~9A04R~
            }
        }
      }                                                            //~1A65I~
      catch(Exception e)                                           //~1A65I~
      {                                                            //~1A65I~
        Dump.println(e,"WiFiDirectActivity:cancelDisconnect");     //~1A65I~
      }                                                            //~1A65I~

    }
//**********************************************************************//~9A04I~
	public void cancelConnect()                                    //~9A04I~
    {                                                              //~9A04I~
		if (Dump.Y) Dump.println("WiFiDirectActivity.cancelConnect");//~9A04I~
        manager.cancelConnect(channel, new ActionListener()        //~9A04I~
				{                                                  //~9A04I~
                    @Override                                      //~9A04I~
                    public void onSuccess()                        //~9A04I~
					{                                              //~9A04I~
						if (Dump.Y) Dump.println("WiFiDirectActivity.cancelConnect.onSuccess");//~9A05I~
                        String msg=WDA.getResourceString(R.string.AbortingConnection);//~9A04I~
    					notifyConnected(DISCONNECT_CANCEL_OK,msg); //~9A04I~
                    }                                              //~9A04I~
                    @Override                                      //~9A04I~
                    public void onFailure(int reasonCode)          //~9A04I~
					{                                              //~9A04I~
						if (Dump.Y) Dump.println("WiFiDirectActivity.cancelConnect.onFailure");//~9A05I~
		                String msg=strReason(R.string.ConnectAbortReason,reasonCode);//~9A04I~
    					notifyConnected(DISCONNECT_CANCEL_ER,msg); //~9A04I~
                    }                                              //~9A04I~
                });                                                //~9A04I~
    }                                                              //~9A04I~
//**********************************************************************//~9A04I~
    public void cancelDiscovery()                                  //~9A04I~
	{                                                              //~9A04I~
        if (Dump.Y) Dump.println("WiFiDirectActivity.cancelDiscovery");//~9A04I~
      	try                                                        //~9A04I~
      	{                                                          //~9A04I~
        	if (manager==null)                                     //~9A04I~
            	return;                                            //~9A04I~
            manager.stopPeerDiscovery(channel,                     //~9A04I~
				new ActionListener()                               //~9A04I~
                {                                                  //~9A04I~
                    @Override                                      //~9A04I~
                    public void onSuccess()                        //~9A04I~
                    {                                              //~9A04I~
//                      UView.showToast(R.string.InfoDiscoverCanceled);//~9A04I~//~0124R~
        				if (Dump.Y) Dump.println("WiFiDirectActivity.cancelDiscovery onSuccess");//~0124I~
                    }                                              //~9A04I~
                    @Override                                      //~9A04I~
                    public void onFailure(int reasonCode)          //~9A04I~
                    {                                              //~9A04I~
        				if (Dump.Y) Dump.println("WiFiDirectActivity.cancelDiscovery onFailure reason="+reasonCode);//~0124I~
                        UView.showToast(Utils.getStr(R.string.InfoDiscoverCancelFailed,reasonCode));//~9A04I~
                    }                                              //~9A04I~
                });                                                //~9A04I~
      	}	                                                       //~9A04I~
      	catch(Exception e)                                         //~9A04I~
      	{                                                          //~9A04I~
        	Dump.println(e,"WiFiDirectActivity:cancelDiscovery");  //~9A04I~
      	}                                                          //~9A04I~
    }                                                              //~9A04I~
    //*****************************************************************//~1A65I~
    private Object getSystemService(String Pservice)               //~1A65I~
    {                                                              //~1A65I~
    	return AG.context.getSystemService(Pservice);              //~1A65I~
    }                                                              //~1A65I~
    //*****************************************************************//~1A65I~
    private Looper getMainLooper()                                 //~1A65I~
    {                                                              //~1A65I~
    	return AG.context.getMainLooper();                         //~1A65I~
    }                                                              //~1A65I~
    //*****************************************************************//~1A65I~
    private Context getContext()                                   //~1A65I~
    {                                                              //~1A65I~
    	return AG.context;                                         //~1A65I~
    }                                                              //~1A65I~
    //*****************************************************************//~1A65I~
    private Activity getActivity()                                 //~1A65I~
    {                                                              //~1A65I~
    	return AG.activity;                                        //~1A65I~
    }                                                              //~1A65I~
//    //*****************************************************************//~1A65I~//~1Ac4R~
//    private void startNFC()                                        //~1A6aI~//~1Ac4R~
//    {                                                              //~1A6aI~//~1Ac4R~
//        if (Dump.Y) Dump.println("WiFiDirectActivity startNFC");   //~1A6aI~//~1Ac4R~
//        try                                                        //~1A6aI~//~1Ac4R~
//        {                                                          //~1A6aI~//~1Ac4R~
//            new WDANFC();                                          //~1A6aR~//~1Ac4R~
//        }                                                          //~1A6aI~//~1Ac4R~
//        catch(Exception e)                                         //~1A6aI~//~1Ac4R~
//        {                                                          //~1A6aI~//~1Ac4R~
//            Dump.println(e,"WiFiDirectActivity");                  //~1A6aI~//~1Ac4R~
//        }                                                          //~1A6aI~//~1Ac4R~
//    }                                                              //~1A6aI~//~1Ac4R~
    //*****************************************************************//~1A6aI~
//  private void notifyConnected(int Presult,String Pmsg)               //~1A6aR~//~1A6sR~
    protected void notifyConnected(int Presult,String Pmsg)        //~1A6sI~
    {                                                              //~1A6aI~
      try                                                          //~1Ac0I~
      {                                                            //~1Ac0I~
    	if (Dump.Y) Dump.println("WiFiDirectActivity notifyConnected rc="+Presult+",msg="+Pmsg);//~1A6aI~//~1Ac0I~//~9A05R~
        DeviceDetailFragment fragmentDetails=WDA.getDeviceDetailFragment();//~1A6aI~
        if (Pmsg!=null)                                            //~1A6aI~
        {                                                          //~@@@@I~
        	boolean swErr=true;                                    //~@@@@I~
            switch(Presult)                                        //~@@@@I~
            {                                                      //~@@@@I~
            case CONNECT_OK:                                       //~@@@@I~
            case DISCONNECT_OK:                                    //~@@@@I~
            case DISCONNECT_CANCEL_OK:                             //~@@@@I~
                swErr=false;                                       //~@@@@I~
            }                                                      //~@@@@I~
	        fragmentDetails.connected(swErr,Pmsg);                //~1A6aI~//~@@@@R~
        }                                                          //~@@@@I~
                                                                   //~1A6aI~
        switch(Presult)                                            //~1A6aI~
        {                                                          //~1A6aI~
        case CONNECT_OK:                                           //~1A6aI~
//      	WDA.SWDA.connected();                              //~1A65I~//~1A6aI~//~@@@@R~
        	AG.aWDA.connected();                                   //~@@@@I~
            break;                                                 //~1A6aI~
        case CONNECT_ER:                                           //~1A6aI~
//      	WDA.SWDA.connectError();                               //~1A6aI~//~@@@@R~
        	AG.aWDA.connectError();                                //~@@@@I~
            break;                                                 //~1A6aI~
        case DISCONNECT_OK:                                        //~1A6aI~
//      	WDA.SWDA.disconnected();                               //~1A6aI~//~@@@@R~
        	AG.aWDA.disconnected();                                //~@@@@I~
            break;                                                 //~1A6aI~
        case DISCONNECT_ER:                                        //~1A6aI~
            break;                                                 //~1A6aI~
        case DISCONNECT_CANCEL_OK:                                 //~1A6aI~
            break;                                                 //~1A6aI~
        case DISCONNECT_CANCEL_ER:                                 //~1A6aI~
            break;                                                 //~1A6aI~
        }                                                          //~1A6aI~
//      Utils.chkNetwork();//@@@@test                              //~1Ac0I~//~1Ac4R~
      }                                                            //~1Ac0I~
      catch(Exception e)                                           //~1Ac0I~
      {                                                            //~1Ac0I~
          Dump.println(e,"WiFiDirectActivity:notifyConnected");    //~1Ac0I~
      }                                                            //~1Ac0I~
    }                                                              //~1A6aI~
    //*****************************************************************//~1A6aI~
    //*rc:-1:not found,1:paired,0:not paired(do connect)           //~1A6aI~
    //*****************************************************************//~1A6aI~
    public int discover(String Pmacaddr,boolean Pdodiscover)       //~1A6aR~
    {                                                              //~1A6aI~
    	if (Dump.Y) Dump.println("WiFiDirectActivity discover:"+Pmacaddr);//~1A6aI~
        DeviceListFragment fragmentList=WDA.getDeviceListFragment();//~1A6aI~
        int rc=fragmentList.chkDiscovered(Pmacaddr);               //~1A6aR~
        if (Pdodiscover && rc==-1)                                 //~1A6aR~
            buttonAction(DeviceListFragment.BTNID_DISCOVER);       //~1A6aR~
        return rc;                                                 //~1A6aR~
    }                                                              //~1A6aI~
    //*****************************************************************//~1A6aI~
    public void discover()                                          //~1A6aR~
    {                                                              //~1A6aI~
    	if (Dump.Y) Dump.println("WiFiDirectActivity discover sender side");//~1A6aI~
	    buttonAction(DeviceListFragment.BTNID_DISCOVER);           //~1A6aR~
    }                                                              //~1A6aI~
    //*****************************************************************//~@@@@I~
    private void showReason(int Pmsgid,int Preason)                //~@@@@I~
    {                                                              //~@@@@I~
    	String msg=strReason(Pmsgid,Preason);                      //~@@@@I~
        WDA.getDeviceDetailFragment().setStatus(true/*swErr*/,msg);//~@@@@I~
    }                                                              //~@@@@I~
    //*****************************************************************//~@@@@I~
    private String strReason(int Pmsgid,int Preason)                 //~@@@@I~
    {                                                              //~@@@@I~
    	int strid;                                                 //~@@@@I~
        switch(Preason)                                            //~@@@@I~
        {                                                          //~@@@@I~
        case WifiP2pManager.ERROR:  //0                            //~@@@@I~
        	strid=R.string.wifi_failure_reason_error;                     //~@@@@I~
        	break;                                                 //~@@@@I~
        case WifiP2pManager.P2P_UNSUPPORTED:	//1                //~@@@@I~
        	strid=R.string.wifi_failure_reason_unsupported;               //~@@@@I~
        	break;                                                 //~@@@@I~
        case WifiP2pManager.BUSY:	//2                            //~@@@@I~
        	strid=R.string.wifi_failure_reason_busy;                      //~@@@@I~
        	break;                                                 //~@@@@I~
        default:                                                   //~@@@@I~
	    	strid=R.string.Unknown;                                //~@@@@I~
        }                                                          //~@@@@I~
	    String rc=Utils.getStr(Pmsgid)+Preason+"("+Utils.getStr(strid)+")";//~@@@@I~
    	return rc;                                                 //~@@@@I~
    }                                                              //~@@@@I~
    //*****************************************************************//~9A05I~
    public void showProgDlg(int Paction,String Pmsg)               //~9A05I~
    {                                                              //~9A05I~
		idProgress=Paction;                                        //~9A05I~
    	if (Dump.Y) Dump.println("WiFiDirectActivity.showProgDlg action="+Paction+",msg="+Pmsg);//~9A05I~
    	switch(Paction)                                            //~9A05I~
        {                                                          //~9A05I~
        case PROGRESS_CONNECT:                                     //~9A05I~
            ProgDlg.showProgDlg(R.string.WifiDirect,Pmsg,false,aWifiDirectActivity/*ProgDlgI*/);//~9A05I~
            break;                                                 //~9A05I~
        case PROGRESS_DISCOVER:                                    //~9A05I~
			ProgDlg.showProgDlg(R.string.WifiDirect,Pmsg,false/*cancelable*/,aWifiDirectActivity/*ProgDlgI*/);//~9A05I~
            break;                                                 //~9A05I~
        }                                                          //~9A05I~
    }                                                              //~9A05I~
    //*****************************************************************//~9A04I~
    @Override //ProgDlgI                                           //~9A04I~
    public void onCancelProgDlg(int Preason)                                   //~9A04I~
    {                                                              //~9A04I~
    	if (Dump.Y) Dump.println("WiFiDirectActivity.onCancelProgDlg reason="+Preason+",idProgress="+idProgress);//~9A04R~
        if (Preason!=ProgDlg.REASON_CANCEL)	//0:CANCEL, 1:DISMISS  //~9A04I~
        	return;                                                //~9A04I~
	    switch(idProgress)                                         //~9A04I~
        {                                                          //~9A04I~
        case PROGRESS_CONNECT:                                     //~9A04I~
//  		cancelDisconnect();                                    //~9A04I~//~0116R~
            break;                                                 //~9A04I~
        case PROGRESS_DISCOVER:                                    //~9A04I~
    		if (Dump.Y) Dump.println("WiFiDirectActivity.onCancelProgDlg canceled discover");//~9A04I~
			cancelDiscovery();                                     //~9A04I~
            break;                                                 //~9A04I~
        }                                                          //~9A04I~
    }                                                              //~9A04I~
    //*****************************************************************//~9A04I~
    //*moved from DeviceListFragment                               //~9A04I~
    //*****************************************************************//~9A04I~
    public void onInitiateDiscovery()                              //~9A04I~
    {                                                              //~9A04I~
    	if (Dump.Y) Dump.println("WiFiDirectActivity.onInitiateDiscovery");//~9A04I~
    	dismissProgDlg(0);                                          //~9A04I~//~9A05R~
//      progressDialog=progressDialogShow(R.string.ProgressDialogTitle,//~9A04I~
//  				     					WDA.getResourceString(R.string.ProgressDialogMsgFindingPeer),//~9A04I~
//  					    				true,true);//onCancel ignored//~9A04I~
        String msg=Utils.getStr(R.string.ProgressDialogMsgFindingPeer);//~9A04I~
//  	idProgress=PROGRESS_DISCOVER;                              //~9A04I~//~9A05R~
//      ProgDlg.showProgDlg(R.string.WifiDirect,msg,false/*cancelable*/,aWifiDirectActivity/*ProgDlgI*/);//~9A04I~//~9A05R~
    	showProgDlg(PROGRESS_DISCOVER,msg);                        //~9A05I~
    }                                                              //~9A04I~
	//*******************************************************************************************************//~9A04I~
    public  void dismissProgDlg(int Paction)                            //~9A04R~//~9A05R~
    {                                                              //~9A04I~
		if (Dump.Y) Dump.println("WifiDirectActivity.dismissProgDlg idProgress="+idProgress+",Paction="+Paction+",AG.progDlg="+Utils.toString(AG.progDlg));//~9A04I~//~9A05R~
        if (AG.progDlg==null)                                      //~9A04I~
        	return;                                                //~9A04I~
        if (Paction==0 || idProgress==Paction)                     //~9A05R~
	        ProgDlg.dismissCurrent();                                  //~9A04I~//~9A05R~
    }                                                              //~9A04I~
 }
