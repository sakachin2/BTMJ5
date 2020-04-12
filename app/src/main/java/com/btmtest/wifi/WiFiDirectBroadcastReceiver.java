//*CID://+DATER~:                             update#=   35;       //~1Ac4R~//~@@@@R~//~9A03R~
//*************************************************************************//~1A65I~
//1Ac4 2015/07/06 WD:try disable wifi direct at unpair             //~1Ac4I~
//1A6s 2015/02/17 move NFC starter from WifiDirect dialog to MainFrame//~1A65I~
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

package com.btmtest.wifi;                                               //~v@@@I~//~9719I~//~1Ac4I~

//import android.annotation.TargetApi;                               //~1A65I~//~@@@@R~
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
                                                                   //~1A65I~
import com.btmtest.utils.Dump;

import static com.btmtest.StaticVars.AG;                           //~9721I~//~@@@@I~

/**
 * A BroadcastReceiver that notifies of important wifi p2p events.
 */
//@TargetApi(AG.ICE_CREAM_SANDWICH)   //api14                           //~1A65R~//~@@@@R~
public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

//  private WifiP2pManager manager;                                //~1A65R~
    protected WifiP2pManager manager;                              //~1A65I~
//  private Channel channel;                                       //~1A65R~
    protected Channel channel;                                     //~1A65I~
    private WiFiDirectActivity activity;

    /**
     * @param manager WifiP2pManager system service
     * @param channel Wifi p2p channel
     * @param activity activity associated with the receiver
     */
    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, Channel channel,
            WiFiDirectActivity activity) {
        super();
        this.manager = manager;
        this.channel = channel;
        this.activity = activity;
    }

    /*
     * (non-Javadoc)
     * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
     * android.content.Intent)
     */
    @Override
    public void onReceive(Context context, Intent intent) {
      try                                                          //~1A65I~
      {                                                            //~1A65I~
        String action = intent.getAction();
        if (Dump.Y) Dump.println("WiFiDirectBroadCastReceiver:onReceive action="+action);//~1Ac4I~//~@@@@I~
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {

            // UI update to indicate wifi p2p status.
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);   //1:disabled, 2:enabled//~@@@@R~
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // Wifi Direct mode is enabled
                activity.setIsWifiP2pEnabled(true);
//              WDA.disableWiFi();//@@@@test                       //~1Ac4I~
            	if (Dump.Y) Dump.println("WiFiDirectBroadCastReceiver:onReceive:P2P state changed TEST:Enabled issue requestPair");//~@@@@I~
            	manager.requestPeers(channel,(PeerListListener)WDA.getDeviceListFragment());//~@@@@I~
            } else {
                activity.setIsWifiP2pEnabled(false);
                activity.resetData();

            }
//          Log.d(WiFiDirectActivity.TAG, "P2P state changed - " + state);//~1A65R~
            if (Dump.Y) Dump.println("WiFiDirectBroadCastReceiver:onReceive:P2P state changed - " + state);//~1A65I~
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            if (Dump.Y) Dump.println("WiFiDirectBroadCastReceiver:onReceive:P2P peers changed");//~1A65I~//~@@@@M~
            // request available peers from the wifi p2p manager. This is an
            // asynchronous call and the calling activity is notified with a
            // callback on PeerListListener.onPeersAvailable()
            if (manager != null) {
//              manager.requestPeers(channel, (PeerListListener) activity.getFragmentManager()//~1A65R~
//                      .findFragmentById(R.id.frag_list));        //~1A65R~
    	        if (Dump.Y) Dump.println("WiFiDirectBroadCastReceiver:onReceive:issue requestPeers");//~@@@@I~
//  			WiFiDirectActivity.dismissProgDlg();	//current ProgDlg:Scan//~9A04I~//~9A05R~
    			WDA.getWDActivity().dismissProgDlg(WiFiDirectActivity.PROGRESS_DISCOVER);	//current ProgDlg:Scan//~9A05I~
                manager.requestPeers(channel,(PeerListListener)WDA.getDeviceListFragment());//~1A65R~
            }
//          Log.d(WiFiDirectActivity.TAG, "P2P peers changed");    //~1A65R~
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

            if (manager == null) {
                return;
            }
            if (Dump.Y) Dump.println("WiFiDirectBroadCastReceiver:onReceive:P2P connection changed");//~1A65I~

            NetworkInfo networkInfo = (NetworkInfo) intent
                    .getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

            if (networkInfo.isConnected()) {
//  			WiFiDirectActivity.dismissProgDlg();	//current ProgDlg:DoPair  //~@@@@I~//~9A04R~//~9A05R~
    			WDA.getWDActivity().dismissProgDlg(WiFiDirectActivity.PROGRESS_CONNECT);	//current ProgDlg:DoPair//~9A05I~
                // we are connected with the other device, request connection
                // info to find group owner IP

//              DeviceDetailFragment fragment = (DeviceDetailFragment) activity//~1A65R~
//                      .getFragmentManager().findFragmentById(R.id.frag_detail);//~1A65R~
                DeviceDetailFragment fragment = WDA.getDeviceDetailFragment();//~1A65I~
	            if (Dump.Y) Dump.println("WiFiDirectBroadCastReceiver:onReceive:requestConnectionInfo");//~1A65I~
                manager.requestConnectionInfo(channel, fragment);
                manager.requestGroupInfo(channel,fragment/*GroupInfoListener*/);//~1A65I~
				AG.aWDA.enableCBWantGroupOwner(false); 	//connected//~9A03R~
                WDA.getDeviceListFragment().setConnected(true);    //~9A05I~
            } else {
                // It's a disconnect
//              activity.resetData();                              //~9A05R~
                int clearCtr=activity.resetData();                 //~9A05I~
//              if (clearCtr==1)  //client                         //~9A05R~
//      			WDA.getWDActivity().removeGroup();	//TODO test//~9A05R~
				AG.aWDA.enableCBWantGroupOwner(true); 	//disconnected//~9A03R~
//              WDA.getDeviceListFragment().setConnected(false);   //+9A05R~
            }
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            if (Dump.Y) Dump.println("WiFiDirectBroadCastReceiver:onReceive:P2P this device changed");//~1A65I~
//          DeviceListFragment fragment = (DeviceListFragment) activity.getFragmentManager()//~1A65R~
//                  .findFragmentById(R.id.frag_list);             //~1A65R~
            DeviceListFragment fragment = WDA.getDeviceListFragment();//~1A65R~
            fragment.updateThisDevice((WifiP2pDevice) intent.getParcelableExtra(
                    WifiP2pManager.EXTRA_WIFI_P2P_DEVICE));

        }
      }                                                            //~1A65I~
      catch(Exception e)                                            //~1A65I~
      {                                                            //~1A65I~
      	Dump.println(e,"WiFiDirectBroadcastReceiver:onReceive");   //~1A65R~
      }                                                            //~1A65I~
    }
}
