//*CID://+va44R~:                             update#=  193;       //~va44R~
//*************************************************************************//~1A65I~
//2020/11/19 va44 Android10:WD;no THIS_DEVICE_CHANGED broadcast msg.paired/owner flag not set//~va44I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//1AbB 2015/06/22 mask mac addr for security                       //~1AbBI~
//1Aa4 2015/04/20 show also empty msg for server side              //~1Aa4I~
//1A90 2015/04/18 (like as 1A84)WiFiDirect from Top panel          //~1A90I~
//1A89k2015/03/01 Ajagoc:2015/02/28 confirm session disconnect when unpair//~1A6tI~
//1A6t 2015/02/18 (BUG)simpleProgress Dialog thread err exception  //~1A6tI~
//1A6e 2015/02/13 runOnUiThread for processingdialog               //~1A67I~
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

package com.btmtest.wifi;                                          //~@@@@R~

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.annotation.TargetApi;                               //~1A65R~
import android.content.res.Resources;                              //~1A65R~
//import android.net.Uri;
//import android.net.wifi.WpsInfo;                                 //~va40R~
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.net.wifi.p2p.WifiP2pManager.GroupInfoListener;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.btmtest.R;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;
//import com.btmtest.utils.URunnable;                              //~va40R~
import com.btmtest.utils.Utils;
//~@@@@I~
import static com.btmtest.StaticVars.AG;                           //~9721I~//~@@@@I~



/**
 * A fragment that manages a particular peer and allows interaction with device
 * i.e. setting up network connection and transferring data.
 */
//@TargetApi(AG.ICE_CREAM_SANDWICH)   //api14                           //~1A65R~//~@@@@R~
//public class DeviceDetailFragment extends Fragment implements ConnectionInfoListener {//~1A65R~
public class DeviceDetailFragment implements ConnectionInfoListener
			,GroupInfoListener
{//~1A65R~
	

    private static final int BTNID_CONNECT= R.id.btn_connect;       //~1A65R~//~@@@@R~
//  private static final int BTNID_CONNECT_SERVER=R.id.btn_connect_server;//~@@@@R~
//  private static final int BTNID_CONNECT_CLIENT=R.id.btn_connect_client;//~@@@@R~
    private static final int BTNID_DISCONNECT=R.id.btn_disconnect; //~1A65I~
	private static final int COLOR_STATUS_NORMAL=0xffffffff;       //~@@@@I~
	private static final int COLOR_STATUS_ERR=0xffffa500;          //~@@@@I~
	private static final String LOCAL_HOSTNAME="LocalHost";        //~va44I~
//  private TextView /*tvGroupOwner,*/tvDeviceInfo,tvStatusText,/*tvDeviceAddress,*/tvGroupIP;//~1A65R~//~@@@@R~
    private TextView tvDeviceInfo,tvStatusText;                    //~@@@@I~
    private TextView tvPeerStatus,tvPeerInfo;                        //~1A84I~//~1A90I~
    private Button btnConnect/*,btnDisconnect*/;                       //~1A65R~//~@@@@R~
//  private Button btnConnectServer,btnConnectClient;              //~@@@@R~
//  private ViewGroup layoutView;                                  //~1A65I~//~@@@@R~
    private View layoutView;                                       //~@@@@I~
    public DeviceListFragment deviceListFragment;                  //~1A65R~
    public boolean swGroupInfoOwner;                               //~@@@@I~
    public String  nameGroupInfoOwner;                             //~@@@@I~
                                                                   //~1A65I~
    protected static final int CHOOSE_FILE_RESULT_CODE = 20;
//  private View mContentView = null;                              //~1A65R~
    private WifiP2pDevice device;
    private WifiP2pInfo info;
//  ProgressDialog progressDialog = null;                          //~1A6tR~
//  URunnable.URunnableData progressDialog = null;                 //~va40R~
    public String ownerIPAddress,peerDevice;                       //~1A65R~

//    @Override                                                    //~1A65R~
//    public void onActivityCreated(Bundle savedInstanceState) {   //~1A65R~
//        super.onActivityCreated(savedInstanceState);             //~1A65R~
//    }                                                            //~1A65R~
	public DeviceDetailFragment()                                  //~1A65R~//~@@@@R~
    {                                                              //~1A65I~
    }                                                              //~1A65R~
//    @Override                                                    //~1A65R~
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {//~1A65R~
//        mContentView = inflater.inflate(R.layout.device_detail, null);//~1A65R~
//        mContentView.findViewById(R.id.btn_connect).setOnClickListener(new View.OnClickListener() {//~1A65R~

//            @Override                                            //~1A65R~
//            public void onClick(View v) {                        //~1A65R~
//                WifiP2pConfig config = new WifiP2pConfig();      //~1A65R~
//                config.deviceAddress = device.deviceAddress;     //~1A65R~
//                config.wps.setup = WpsInfo.PBC;                  //~1A65R~
//                if (progressDialog != null && progressDialog.isShowing()) {//~1A65R~
//                    progressDialog.dismiss();                    //~1A65R~
//                }                                                //~1A65R~
//                progressDialog = ProgressDialog.show(getActivity(), "Press back to cancel",//~1A65R~
//                        "Connecting to :" + device.deviceAddress, true, true//~1A65R~
////                        new DialogInterface.OnCancelListener() {//~1A65R~
////                                                               //~1A65R~
////                            @Override                          //~1A65R~
////                            public void onCancel(DialogInterface dialog) {//~1A65R~
////                                ((DeviceActionListener) getActivity()).cancelDisconnect();//~1A65R~
////                            }                                  //~1A65R~
////                        }                                      //~1A65R~
//                        );                                       //~1A65R~
//                ((DeviceActionListener) getActivity()).connect(config);//~1A65R~

//            }                                                    //~1A65R~
//        });                                                      //~1A65R~

//        mContentView.findViewById(R.id.btn_disconnect).setOnClickListener(//~1A65R~
//                new View.OnClickListener() {                     //~1A65R~

//                    @Override                                    //~1A65R~
//                    public void onClick(View v) {                //~1A65R~
//                        ((DeviceActionListener) getActivity()).disconnect();//~1A65R~
//                    }                                            //~1A65R~
//                });                                              //~1A65R~

//        mContentView.findViewById(R.id.btn_start_client).setOnClickListener(//~1A65R~
//                new View.OnClickListener() {                     //~1A65R~

//                    @Override                                    //~1A65R~
//                    public void onClick(View v) {                //~1A65R~
//                        // Allow user to pick an image from Gallery or other//~1A65R~
//                        // registered apps                       //~1A65R~
//                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);//~1A65R~
//                        intent.setType("image/*");               //~1A65R~
//                        startActivityForResult(intent, CHOOSE_FILE_RESULT_CODE);//~1A65R~
//                    }                                            //~1A65R~
//                });                                              //~1A65R~

//        return mContentView;                                     //~1A65R~
//    }                                                            //~1A65R~
	//***********************************************************************************//~1A65I~
//	public void initFragment(ViewGroup PlayoutView)                //~1A65R~//~@@@@R~
  	public void initFragment(View PlayoutView)                     //~@@@@I~
    {                                                              //~1A65I~
    //********************                                         //~1A65I~
    	layoutView=PlayoutView;                                    //~1A65I~
//      TextView view = (TextView) mContentView.findViewById(R.id.group_owner);//~1A65I~
//      tvGroupOwner=(TextView)PlayoutView.findViewById(R.id.group_owner);//~1A65R~
//      view = (TextView) mContentView.findViewById(R.id.device_info);//~1A65I~
//      tvDeviceInfo=(TextView)PlayoutView.findViewById(R.id.device_info);//~1A65R~//~@@@@R~
//      tvPeerStatus=(TextView)PlayoutView.findViewById(R.id.peer_status);//~1A84I~//~1A90I~//~@@@@R~
//      tvPeerInfo=(TextView)PlayoutView.findViewById(R.id.peer_info);//~1A84R~//~1A90I~//~@@@@R~
//          new FileServerAsyncTask(getActivity(), mContentView.findViewById(R.id.status_text))//~1A65I~
        tvStatusText=(TextView)PlayoutView.findViewById(R.id.status_text);//~1A65I~
//      TextView view = (TextView) mContentView.findViewById(R.id.device_address);//~1A65I~
//      tvDeviceAddress=(TextView)PlayoutView.findViewById(R.id.device_address);//~1A65R~
//          mContentView.findViewById(R.id.btn_start_client).setVisibility(View.VISIBLE);//~1A65I~
//      tvGroupIP=(TextView)PlayoutView.findViewById(R.id.group_ip);//~1A65I~//~@@@@R~
//      mContentView.findViewById(R.id.btn_connect).setVisibility(View.GONE);//~1A65I~
        btnConnect=(Button)PlayoutView.findViewById(BTNID_CONNECT);//~1A65I~//~@@@@R~
//      btnConnectServer=(Button)PlayoutView.findViewById(BTNID_CONNECT_SERVER);//~@@@@R~
//      btnConnectClient=(Button)PlayoutView.findViewById(BTNID_CONNECT_CLIENT);//~@@@@R~
//      btnDisconnect=(Button)PlayoutView.findViewById(BTNID_DISCONNECT);//~1A65I~
//      btnConnect.setVisibility(View.VISIBLE);                    //~1A65I~//~@@@@R~
        btnConnect.setEnabled(true);                               //~@@@@I~
//      btnConnectServer.setVisibility(View.VISIBLE);              //~@@@@R~
//      btnConnectClient.setVisibility(View.VISIBLE);              //~@@@@R~
//      btnDisconnect.setVisibility(View.GONE);                    //~1A65R~
    }                                                              //~1A65I~
	//***********************************************************************************//~1A65I~
	//*rc=1:dismiss,-1:another button processing                   //~@@@@I~
	//***********************************************************************************//~@@@@I~
    public int buttonAction(int PbuttonId)                  //~1821I~//~1A65R~
    {                                                              //~1A65I~
        boolean rc=false;	  //no dismiss                                             //~1A65R~//~@@@@R~
    //********************                                         //~1A65I~
        if (Dump.Y) Dump.println("DeviceDetailFragment:buttonAction");//~@@@@I~
        switch(PbuttonId)                                          //~1A65I~
        {                                                          //~1A65I~
        case BTNID_CONNECT:                                        //~1A65I~//~@@@@R~
            WifiP2pDevice selectedDevice=WDA.getDeviceListFragment().getSelectedDevice();//~@@@@I~
            if (selectedDevice==null)                              //~@@@@I~
            {                                                      //~@@@@I~
                setStatus(true,R.string.Wifi_SelectDevice);        //~@@@@I~
                break;                                             //~@@@@I~
            }                                                      //~@@@@I~
        	rc=connect(selectedDevice);                                          //~1A65R~//~@@@@R~
        	break;                                                 //~@@@@R~
//        case BTNID_CONNECT_SERVER:                               //~@@@@R~
//            rc=connect(true/*SswServer*/);                       //~@@@@R~
//            break;                                                 //~1A65I~//~@@@@R~
//        case BTNID_CONNECT_CLIENT:                               //~@@@@R~
//            rc=connect(false/*SswServer*/);                      //~@@@@R~
//            break;                                               //~@@@@R~
        case BTNID_DISCONNECT:                                     //~1A65I~
        	rc=disconnect();                                       //~1A65R~//~@@@@R~
        	break;                                                 //~1A65I~
        default:                                                   //~1A65I~
        	return -1;	//goto WifiDirectActivity.buttonAction                                             //~1A65I~//~@@@@R~
        }                                                          //~1A65I~
    	return (rc ? 1 : 0);                                       //~1A65R~//~@@@@R~
    }                                                              //~1A65I~
	//***********************************************************************************//~1A65I~
	//*Paring                                                      //~@@@@I~
	//***********************************************************************************//~@@@@I~
//  private boolean connect()                                      //~1A65R~//~@@@@R~
    private boolean connect(WifiP2pDevice Pdevice)                 //~@@@@I~
    {                                                              //~1A65I~
        boolean rc=false;   //no dismiss                           //~1A65I~
 		WifiP2pDevice device=Pdevice;                              //~@@@@I~
    //********************                                         //~1A65I~
        if (Dump.Y) Dump.println("DeviceDetailFragment connect device.status="+device.status);//~@@@@I~
//  	if (device.status!=WifiP2pDevice.AVAILABLE)                //~@@@@R~
    	if (device.status==WifiP2pDevice.CONNECTED)                //~@@@@R~
        {                                                          //~@@@@I~
	        setStatus(true/*err*/,R.string.Wifi_Pair_NotAvailableDevice);//~@@@@R~
            return rc;                                             //~@@@@I~
        }                                                          //~@@@@I~
        if (Dump.Y) Dump.println("DeviceDetailFragment connect devicename="+getDeviceName(device)+",isOwner="+device.isGroupOwner()+",status="+device.status+",thisOwner="+WDA.getDeviceListFragment().thisOwner);//~@@@@I~
        if (WDA.getDeviceListFragment().thisOwner==0)	//already connected as client//~@@@@R~
        {                                                          //~@@@@I~
//          tvStatusText.setText(Utils.getStr(R.string.Wifi_Pair_AlreadyClientInGroup));//~@@@@R~
            setStatus(true,R.string.Wifi_Pair_AlreadyClientInGroup);//~@@@@I~
            return rc;                                             //~@@@@I~
        }                                                          //~@@@@I~
                                                                   //~@@@@I~
        		boolean swServer=AG.aWDA.cbWantGroupOwner.getState();//~@@@@I~
                WifiP2pConfig config = new WifiP2pConfig();        //~1A65I~
                if (Dump.Y) Dump.println("DeviceDetailFragment.connect wifip2pconfig="+config.toString());//~9A03I~
                config.deviceAddress = device.deviceAddress;       //~1A65I~
                if (Dump.Y) Dump.println("DeviceDetailFragment.connect:"+config.deviceAddress);//~1A67I~//~9A03R~
//              config.wps.setup = WpsInfo.PBC; //default and deprecated at api28~1A65I~//~9A03R~//~9A04R~
//  		    config.groupOwnerIntent = swServer ? 15: 1; // I want this device to become the owner(15)/Client(0),-1:selected by system//~@@@@I~//~9A03R~//~9B06R~
    		    config.groupOwnerIntent = swServer ? 15: -1; // I want this device to become the owner(15)/Client(0),-1:selected by system//~9B06I~
        		if (Dump.Y) Dump.println("DeviceDetailFragment:connect swServer="+swServer+",groupOwnerIntent="+config.groupOwnerIntent);//~@@@@I~
//                if (progressDialog != null && progressDialog.isShowing()) {//~1A65I~//~1A67R~
//                    progressDialog.dismiss();                      //~1A65I~//~1A67R~
//                }                                                  //~1A65I~//~1A67R~
//  			dismissProgressDialog();                           //~1A67I~//~9A04R~
//              progressDialog = ProgressDialog.show(getActivity(), "Press back to cancel",//~1A65R~
//                      "Connecting to :" + device.deviceAddress, true, true//~1A65R~
//                      );                                         //~1A65R~
//  			String server=device.deviceName;                   //~1A67R~
//              peerDevice=server;                                 //~1A67R~
//              if (server==null)                                  //~1A67R~
//              	server=device.deviceAddress;                   //~1A67R~
    			peerDevice=getDeviceName(device);                  //~1A67R~
//              progressDialog = ProgressDialog.show(getActivity(),WDA.getResourceString(R.string.ProgressDialogTitle),//~1A65I~//~1A67R~
//                      WDA.getResourceString(R.string.ProgressDialogMsgConnecting)+peerDevice, true, true//~1A67R~
//                      );                                         //~1A65I~//~1A67R~
//                progressDialog = progressDialogShow(R.string.ProgressDialogTitle,//~1A67I~//~@@@@R~
////                                              WDA.getResourceString(R.string.ProgressDialogMsgConnecting)+peerDevice,//~1A67I~//~1AbBR~//~@@@@R~
////                                              WDA.getResourceString(R.string.ProgressDialogMsgConnecting)+DialogNFC.maskMacAddr(peerDevice),//~1AbBI~//~@@@@R~
//                                                WDA.getResourceString(R.string.ProgressDialogMsgConnecting)+WDI.maskMacAddr(peerDevice),//~@@@@R~
//                                                true, true);       //~1A67I~//~@@@@R~
                String progressMsg=WDA.getResourceString(R.string.ProgressDialogMsgConnecting)+WDI.maskMacAddr(peerDevice);//~@@@@I~
                WDA.getWDActivity().setConnectProgressMsg(progressMsg);//~@@@@R~
//              ((DeviceActionListener) getActivity()).connect(config);//~1A65R~
//              if (swServer)                                      //~9A03I~//~9A04R~
//              	AG.aWDA.aWDActivity.connectAsOwner(config);    //accesspoint,require find service//~9A04R~
//              else                                               //~9A03I~//~9A04R~
                ((DeviceListFragment.DeviceActionListener) WDA.getWDActivity()).connect(config);//~1A65R~//~@@@@R~
    	return rc;                                                 //~1A65R~
    }                                                              //~1A65I~
//    //***********************************************************************************//~@@@@I~//~9A03R~
//    private boolean connect(boolean PswServer)                     //~@@@@I~//~9A03R~
//    {                                                              //~@@@@I~//~9A03R~
//        boolean rc=false;   //no dismiss                           //~@@@@I~//~9A03R~
//    //********************                                         //~@@@@I~//~9A03R~
//                WifiP2pConfig config = new WifiP2pConfig();        //~@@@@I~//~9A03R~
//                config.deviceAddress = device.deviceAddress;       //~@@@@I~//~9A03R~
//                if (Dump.Y) Dump.println("DeviceDetailFragment connect:"+config.deviceAddress);//~@@@@I~//~9A03R~
//                config.wps.setup = WpsInfo.PBC;                    //~@@@@I~//~9A03R~
//                config.groupOwnerIntent = PswServer ? 15: 0; // I want this device to become the owner(15)/Client(0)//~@@@@R~//~9A03R~
//        if (Dump.Y) Dump.println("DeviceDetailFragment:connect swServer="+PswServer+",groupOwnerIntent="+config.groupOwnerIntent);//~@@@@I~//~9A03R~
//                dismissProgressDialog();                           //~@@@@I~//~9A03R~
//                peerDevice=getDeviceName(device);                  //~@@@@I~//~9A03R~
////                progressDialog = progressDialogShow(R.string.ProgressDialogTitle,//~@@@@R~//~9A03R~
//////                                              WDA.getResourceString(R.string.ProgressDialogMsgConnecting)+DialogNFC.maskMacAddr(peerDevice),//~@@@@R~//~9A03R~
////                                                WDA.getResourceString(R.string.ProgressDialogMsgConnecting)+WDI.maskMacAddr(peerDevice),//~@@@@R~//~9A03R~
////                                                true, true);     //~@@@@R~//~9A03R~
//                String progressMsg=WDA.getResourceString(R.string.ProgressDialogMsgConnecting)+WDI.maskMacAddr(peerDevice);//~@@@@I~//~9A03R~
//                WDA.getWDActivity().setConnectProgressMsg(progressMsg);//~@@@@I~//~9A03R~
//                ((DeviceListFragment.DeviceActionListener) WDA.getWDActivity()).connect(config);//~@@@@R~//~9A03R~
//        return rc;                                                 //~@@@@I~//~9A03R~
//    }                                                              //~@@@@I~//~9A03R~
//    //***********************************************************************************//~1A67I~//~9A03R~
//    public boolean connect(String Pmacaddr)                       //~1A67I~//~9A03R~
//    {                                                              //~1A67I~//~9A03R~
//        boolean rc=false;   //no dismiss                           //~1A67I~//~9A03R~
//    //********************                                         //~1A67I~//~9A03R~
//        if (Dump.Y) Dump.println("DeviceDetailFragment:connect to "+Pmacaddr);//~1A67I~//~9A03R~
//                WifiP2pConfig config = new WifiP2pConfig();        //~1A67I~//~9A03R~
//                config.deviceAddress = Pmacaddr;//device.deviceAddress;//~1A67I~//~9A03R~
//                if (Dump.Y) Dump.println("DeviceDetailFragment connect:"+config.deviceAddress);//~1A67I~//~9A03R~
//                config.wps.setup = WpsInfo.PBC;                    //~1A67I~//~9A03R~
////              if (progressDialog != null && progressDialog.isShowing()) {//~1A67R~//~9A03R~
////                  progressDialog.dismiss();                      //~1A67R~//~9A03R~
////              }                                                  //~1A67R~//~9A03R~
//                dismissProgressDialog();                           //~1A67I~//~9A03R~
////              peerDevice=getDeviceName(device);                  //~1A67I~//~9A03R~
////              progressDialog = ProgressDialog.show(getActivity(),WDA.getResourceString(R.string.ProgressDialogTitle),//~1A67R~//~9A03R~
////                      WDA.getResourceString(R.string.ProgressDialogMsgConnectingNFC)+Pmacaddr, true, true//~1A67R~//~9A03R~
////                      );                                         //~1A67R~//~9A03R~
//                progressDialog = progressDialogShow(R.string.ProgressDialogTitle,//~1A67I~//~9A03R~
////                                              WDA.getResourceString(R.string.ProgressDialogMsgConnectingNFC)+Pmacaddr,//~1A67I~//~1AbBR~//~9A03R~
////                                              WDA.getResourceString(R.string.ProgressDialogMsgConnectingNFC)+DialogNFC.maskMacAddr(Pmacaddr),//~1AbBI~//~@@@@R~//~9A03R~
//                                                WDA.getResourceString(R.string.ProgressDialogMsgConnectingNFC)+WDI.maskMacAddr(Pmacaddr),//~@@@@I~//~9A03R~
//                                                true, true);       //~1A67I~//~9A03R~
//                ((DeviceListFragment.DeviceActionListener) WDA.getWDActivity()).connect(config);//~1A67I~//~9A03R~
//        if (Dump.Y) Dump.println("DeviceDetailFragment:connect return");//~1A67I~//~9A03R~
//        return rc;                                                 //~1A67I~//~9A03R~
//    }                                                              //~1A67I~//~9A03R~
	//***********************************************************************************//~1A65I~
    private boolean disconnect()                                   //~1A65R~//~@@@@R~
    {                                                              //~1A65I~
        boolean rc=false;   //no dismiss                           //~1A65I~
    //********************                                         //~1A65I~
    if (Dump.Y) Dump.println("DeviceDetailFragment:disconnect");    //~@@@@I~
//    boolean noActiveSession=                                     //~1A89R~//~1A6tI~//~@@@@R~//~0117R~
////      WDA.SWDA.unpairRequest();   //close if session alive       //~1A87R~//~1A6tI~//~@@@@R~//~0117R~
//        AG.aWDA.unpairRequest();    //close if session alive       //~@@@@I~//~0117R~
////                      ((DeviceActionListener) getActivity()).disconnect();//~1A65R~//~0117R~
////                      ((DeviceActionListener) WDA.getWDActivity()).disconnect();//~1A65R~//~1A6tR~//~0117R~
//        if (noActiveSession)                                       //~1A89R~//~1A6tI~//~0117R~
	    	doUnpair();                                            //~1A89R~//~1A6tI~//~@@@@R~
    	return rc;                                                 //~1A65I~
    }                                                              //~1A65I~
	//***********************************************************************************//~1A87I~//~1A6tI~
    private void doUnpair()                                         //~1A89R~//~1A6tI~//~@@@@R~//~0116R~
    {                                                              //~1A89R~//~1A6tI~
    	int typeOwner=WDA.getDeviceListFragment().thisOwner;       //~@@@@R~
    	if (Dump.Y) Dump.println("DeviceDetailFragment:doUnpair thisOwner="+typeOwner);//~@@@@I~
    	int ctr=WDA.getDeviceListFragment().getDeviceCtrToUnpair();//~@@@@I~
    	int ctrConnected=WDA.getDeviceListFragment().ctrConnected; //~0116I~
    	if (ctr==0)                                                //~@@@@R~
            setStatus(true,R.string.Wifi_Pair_NoPairedDevice);     //~@@@@I~
        else                                                       //~@@@@I~
//      if (typeOwner==1)	//owner                                //~@@@@R~//~0116R~
//  	    doUnpairGO(false);                                     //~@@@@I~//~0116R~
//      else                                                       //~@@@@R~//~0116R~
        {                                                          //~@@@@I~
            WifiP2pDevice dev=WDA.getDeviceListFragment().getSelectedDevice();//~@@@@I~
            if (dev==null                                          //~@@@@R~
			||  (dev.status==WifiP2pDevice.AVAILABLE)              //~@@@@R~
            )                                                      //~@@@@I~
            {                                                      //~@@@@I~
                setStatus(true,R.string.Wifi_Pair_NotConnectedDevice);//~@@@@I~
                return;                                         //~@@@@I~
            }                                                      //~@@@@I~
            if (dev.status==WifiP2pDevice.INVITED)                 //~9A04I~//~0117R~
            {                                                      //~9A04I~
		    	if (Dump.Y) Dump.println("DeviceDetailFragment:status INVITED");//~9A04I~
                    WDA.getWDActivity().cancelConnect();//~9A04I~
                return;                                            //~9A04I~
            }                                                      //~9A04I~

	    	if (Dump.Y) Dump.println("DeviceDetailFragment:doUnpair");//~@@@@R~
         boolean noActiveSession=AG.aWDA.unpairRequest();    //close if session alive//~0117I~
         if (noActiveSession)                                      //~0117I~
         {                                                         //~0117I~
          if (typeOwner==1 && ctrConnected>1)	//owner            //~0116I~
    	    doUnpairGO(false);                                     //~0116I~
          else                                                     //~0116I~
    		((DeviceListFragment.DeviceActionListener)WDA.getWDActivity()).disconnect(); //~1A89R~//~1A6tI~//~@@@@R~
         }                                                         //~0117I~
        }                                                          //~@@@@M~
    }                                                              //~1A89R~//~1A6tI~
	//***********************************************************************************//~@@@@I~
    private void doUnpairGO(boolean PswConfirmed)                   //~@@@@I~//~0116R~
    {                                                              //~@@@@I~
    	if (Dump.Y) Dump.println("DeviceDetailFragment:doUnpairGO swConfirmed="+PswConfirmed);//~@@@@I~
        if (!PswConfirmed)                                         //~@@@@I~
        {                                                          //~@@@@I~
            alertUnpairOnGO();                                     //~@@@@I~
            return;                                                //~@@@@I~
        }                                                          //~@@@@I~
    	if (Dump.Y) Dump.println("DeviceDetailFragment:doUnpairGO");//~@@@@R~
    	((DeviceListFragment.DeviceActionListener) WDA.getWDActivity()).disconnect();//~@@@@I~
    }                                                              //~@@@@I~
//    //***********************************************************************************//~@@@@I~//~0116R~
//    public void doUnpair(WifiP2pDevice Pdevice)                    //~@@@@I~//~0116R~
//    {                                                              //~@@@@I~//~0116R~
//        boolean swOwner=Pdevice.isGroupOwner();                    //~@@@@I~//~0116R~
//        if (Dump.Y) Dump.println("DeviceDetailFragment:doUnpair with device name="+getDeviceName(Pdevice)+",swOwner="+swOwner);//~@@@@I~//~0116R~
////TODO  if  (!swOwner)                                             //~@@@@R~//~0116R~
//            ((DeviceListFragment.DeviceActionListener)WDA.getWDActivity()).disconnect();//~@@@@I~//~0116R~
//    }                                                              //~@@@@I~//~0116R~
	//***********************************************************************************//~1A65I~
//    @Override                                                    //~1A65R~
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {//~1A65R~

//        // User has picked an image. Transfer it to group owner i.e peer using//~1A65R~
//        // FileTransferService.                                  //~1A65R~
//        Uri uri = data.getData();                                //~1A65R~
////      TextView statusText = (TextView) mContentView.findViewById(R.id.status_text);//~1A65R~
//        TextView statusText =tvStatusText;                       //~1A65R~
//        statusText.setText("Sending: " + uri);                   //~1A65R~
//        Log.d(WiFiDirectActivity.TAG, "Intent----------- " + uri);//~1A65R~
//        Intent serviceIntent = new Intent(getActivity(), FileTransferService.class);//~1A65R~
//        serviceIntent.setAction(FileTransferService.ACTION_SEND_FILE);//~1A65R~
//        serviceIntent.putExtra(FileTransferService.EXTRAS_FILE_PATH, uri.toString());//~1A65R~
//        serviceIntent.putExtra(FileTransferService.EXTRAS_GROUP_OWNER_ADDRESS,//~1A65R~
//                info.groupOwnerAddress.getHostAddress());        //~1A65R~
//        serviceIntent.putExtra(FileTransferService.EXTRAS_GROUP_OWNER_PORT, 8988);//~1A65R~
//        getActivity().startService(serviceIntent);               //~1A65R~
//    }                                                            //~1A65R~

	//***********************************************************************************//~1A65I~
	//*ConnectionInfoListener                                      //~1A65I~
	//***********************************************************************************//~1A65I~
    @Override
    public void onConnectionInfoAvailable(final WifiP2pInfo info) {
      try                                                          //~1A65I~
      {                                                            //~1A65I~
//      if (progressDialog != null && progressDialog.isShowing()) {//~1A67R~
//          progressDialog.dismiss();                              //~1A67R~
//      }                                                          //~1A67R~
//  	dismissProgressDialog();                                   //~1A67I~//~9A05R~
        this.info = info;
//      this.getView().setVisibility(View.VISIBLE);                //~@@@@R~
		if (Dump.Y) Dump.println("onConnectionInfoAvailable:infoToString="+info.toString());//~1A65I~
        if (!info.groupFormed)                                     //~va44I~
        	return;                                                //~va44R~
        // The owner IP is now known.
//      TextView view = (TextView) mContentView.findViewById(R.id.group_owner);//~1A65R~
//      TextView view=tvGroupOwner;                                //~1A65R~
        TextView view;                                             //~1A65I~
//      view.setText(getResources().getString(R.string.group_owner_text)//~1A65R~
//              + ((info.isGroupOwner == true) ? getResources().getString(R.string.yes)//~1A65R~
//                      : getResources().getString(R.string.no))); //~1A65R~
		WDA.getDeviceListFragment().updateThisOwner(info.isGroupOwner);//~1A65I~

//		if (Dump.Y) Dump.println("onConnectionInfoAvailable:groupowner="+view.getText().toString());//~1A65I~
        // InetAddress from WifiP2pInfo struct.
//      view = (TextView) mContentView.findViewById(R.id.device_info);//~1A65R~
//      view=tvGroupIP;                                            //~1A65R~//~@@@@R~
//      view.setText("Group Owner IP - " + info.groupOwnerAddress.getHostAddress());//~1A67R~
//      view.setText(WDA.getResourceString(R.string.GroupOwnerIP) + info.groupOwnerAddress.getHostAddress());//~1A67I~//~@@@@R~
//      view.setText(info.groupOwnerAddress.getHostAddress());     //~@@@@R~
        ownerIPAddress=info.groupOwnerAddress.getHostAddress();    //~1A65I~
//		if (Dump.Y) Dump.println("onConnectionInfoAvailable:groupip="+view.getText().toString());//~1A65R~
//        if (device!=null)   //showDetail parm                    //~1A65R~
//        {                                                        //~1A65R~
//            view=tvDeviceAddress;                                //~1A65R~
//            view.setText(device.deviceName+" (" + device.deviceAddress+" )");//~1A65R~
//            if (Dump.Y) Dump.println("onConnectionInfoAvailable:device!=null address="+view.getText().toString());//~1A65R~
//        }                                                        //~1A65R~

        // After the group negotiation, we assign the group owner as the file
        // server. The file server is single threaded, single connection server
        // socket.
        if (info.groupFormed && info.isGroupOwner) {
//          new FileServerAsyncTask(getActivity(), mContentView.findViewById(R.id.status_text))//~1A65R~
//            new FileServerAsyncTask(getActivity(),tvStatusText)  //~1A65R~
//                    .execute();                                  //~1A65R~
//          tvStatusText.setText(getResources()                    //~1Aa4I~//~@@@@R~
//                  .getString(R.string.server_text));             //~1Aa4I~//~@@@@R~
            setStatus(false,R.string.server_text);                 //~@@@@I~
			if (Dump.Y) Dump.println("onConnectionInfoAvailable:Client:statusText="+tvStatusText.getText().toString());//~1A65I~
        } else if (info.groupFormed) {
            // The other device acts as the client. In this case, we enable the
            // get file button.
//          mContentView.findViewById(R.id.btn_start_client).setVisibility(View.VISIBLE);//~1A65R~
//          ((TextView) mContentView.findViewById(R.id.status_text)).setText(getResources()//~1A65R~
//          tvStatusText.setText(getResources()                    //~1A65I~//~@@@@R~
//                  .getString(R.string.client_text));             //~@@@@R~
            setStatus(false,R.string.client_text);                 //~@@@@I~
			if (Dump.Y) Dump.println("onConnectionInfoAvailable:Server:statusText="+tvStatusText.getText().toString());//~1A65R~
        }
        // hide the connect button
//      mContentView.findViewById(R.id.btn_connect).setVisibility(View.GONE);//~1A65R~
//  	WDA.SWDA.connected();	//update AG.RemotestatusWD     //~1A65I~//~1A84R~//~1A90I~
//      btnConnect.setVisibility(View.GONE);                       //~1A65I~//~@@@@R~
//      btnConnect.setEnabled(false);                              //~@@@@R~
//      btnConnectServer.setVisibility(View.GONE);                 //~@@@@R~
//      btnConnectClient.setVisibility(View.GONE);                 //~@@@@R~
//      btnDisconnect.setVisibility(View.VISIBLE);                 //~1A65R~
//  	WDA.SWDA.connected(info.isGroupOwner);                     //~1A84I~//~1A90I~//~@@@@R~
    	AG.aWDA.connected(info.isGroupOwner);                      //~@@@@I~
      }                                                            //~1A65I~
      catch(Exception e)                                           //~1A65I~
      {                                                            //~1A65I~
      	Dump.println(e,"DeviceDetailFragment:onConnectionInfoAvailable");//~1A65I~
      }                                                            //~1A65I~
    }

    /**
     * Updates the UI with device data
     * 
     * @param device the device to be displayed
     */
    public void showDetails(WifiP2pDevice device) {
        this.device = device;
        if (Dump.Y) Dump.println("DeviceDetailFragment showDetail:name="+device.deviceName+",addr="+device.deviceAddress);//~1A67I~
//      peerDevice=device.deviceName;                              //~1A67R~
        peerDevice=getDeviceName(this.device);                     //~1A67R~
//      this.getView().setVisibility(View.VISIBLE);                //~@@@@R~
//      TextView view = (TextView) mContentView.findViewById(R.id.device_address);//~1A65R~
//      TextView view =tvDeviceAddress;                            //~1A65R~
//      view.setText(device.deviceAddress);                        //~1A65R~
//      view.setText(device.deviceName+" (" + device.deviceAddress+" )");//~1A65R~
//  	if (Dump.Y) Dump.println("DeviceDetailFragment:showDetails:deviceaddress="+view.getText().toString());//~1A65R~
//      view = (TextView) mContentView.findViewById(R.id.device_info);//~1A65R~
//      view =tvDeviceInfo;                                        //~1A65R~
//      view.setText(device.toString());                           //~1A65R~
//      view.setText(DeviceListFragment.getDeviceStatus(device.status));//~1A65R~
//  	if (Dump.Y) Dump.println("DeviceDetailFragment:showDetails:deviceinfo="+view.getText().toString());//~1A65R~

    }

    /**
     * Clears the UI fields after a disconnect or direct mode disable operation.
     */
    public void resetViews() {
		if (Dump.Y) Dump.println("DeviceDtailFragment:resetView");         //~1A65I~//~0112R~
//      mContentView.findViewById(R.id.btn_connect).setVisibility(View.VISIBLE);//~1A65R~
//      btnConnect.setVisibility(View.VISIBLE);                    //~1A65I~//~@@@@R~
        btnConnect.setEnabled(true);                               //~@@@@I~
//      btnConnectServer.setVisibility(View.VISIBLE);              //~@@@@R~
//      btnConnectClient.setVisibility(View.VISIBLE);              //~@@@@R~
//      btnDisconnect.setVisibility(View.GONE);                    //~1A65R~
//      TextView view = (TextView) mContentView.findViewById(R.id.device_address);//~1A65R~
//      TextView view =tvDeviceAddress;                            //~1A65R~
        TextView view;                                             //~1A65I~
//      view.setText(R.string.empty);                              //~1A65R~
//      view = (TextView) mContentView.findViewById(R.id.device_info);//~1A65R~
//      view =tvDeviceInfo;                                        //~1A65R~
//      view.setText(R.string.empty);                              //~1A65R~
//      view = (TextView) mContentView.findViewById(R.id.group_owner);//~1A65R~
//      view =tvGroupOwner;                                        //~1A65R~
//      view.setText(R.string.empty);                              //~1A65R~
//      view = (TextView) mContentView.findViewById(R.id.status_text);//~1A65R~
//      view =tvStatusText;                                        //~1A65I~//~@@@@R~
//      view.setText(R.string.empty);                              //~@@@@R~
        setStatus(false,R.string.empty);                           //~@@@@I~
//      mContentView.findViewById(R.id.btn_start_client).setVisibility(View.GONE);//~1A65R~
//      this.getView().setVisibility(View.GONE);                   //~@@@@R~
    }

    /**
     * A simple server socket that accepts connection and writes some data on
     * the stream.
     */
//    public static class FileServerAsyncTask extends AsyncTask<Void, Void, String> {//~1A65R~

//        private Context context;                                 //~1A65R~
//        private TextView statusText;                             //~1A65R~

//        /**                                                      //~1A65R~
//         * @param context                                        //~1A65R~
//         * @param statusText                                     //~1A65R~
//         */                                                      //~1A65R~
//        public FileServerAsyncTask(Context context, View statusText) {//~1A65R~
//            this.context = context;                              //~1A65R~
//            this.statusText = (TextView) statusText;             //~1A65R~
//        }                                                        //~1A65R~

//        @Override                                                //~1A65R~
//        protected String doInBackground(Void... params) {        //~1A65R~
//            try {                                                //~1A65R~
//                if (Dump.Y) Dump.println("DeviceDetailFragment:doInBackground");//~1A65R~
//                ServerSocket serverSocket = new ServerSocket(8988);//~1A65R~
////              Log.d(WiFiDirectActivity.TAG, "Server: Socket opened");//~1A65R~
//                if (Dump.Y) Dump.println("Server: Socket opened");//~1A65R~
//                Socket client = serverSocket.accept();           //~1A65R~
////              Log.d(WiFiDirectActivity.TAG, "Server: connection done");//~1A65R~
//                if (Dump.Y) Dump.println("Server: connection done");//~1A65R~
//                final File f = new File(Environment.getExternalStorageDirectory() + "/"//~1A65R~
//                        + context.getPackageName() + "/wifip2pshared-" + System.currentTimeMillis()//~1A65R~
//                        + ".jpg");                               //~1A65R~

//                File dirs = new File(f.getParent());             //~1A65R~
//                if (!dirs.exists())                              //~1A65R~
//                    dirs.mkdirs();                               //~1A65R~
//                f.createNewFile();                               //~1A65R~

////              Log.d(WiFiDirectActivity.TAG, "server: copying files " + f.toString());//~1A65R~
//                if (Dump.Y) Dump.println("server: copying files " + f.toString());//~1A65R~
//                InputStream inputstream = client.getInputStream();//~1A65R~
//                copyFile(inputstream, new FileOutputStream(f));  //~1A65R~
//                serverSocket.close();                            //~1A65R~
//                return f.getAbsolutePath();                      //~1A65R~
//            } catch (IOException e) {                            //~1A65R~
////              Log.e(WiFiDirectActivity.TAG, e.getMessage());   //~1A65R~
//                Dump.println(e,"DeviceDetailFragment:doInBackground");//~1A65R~
//                return null;                                     //~1A65R~
//            }                                                    //~1A65R~
//        }                                                        //~1A65R~

//        /*                                                       //~1A65R~
//         * (non-Javadoc)                                         //~1A65R~
//         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)//~1A65R~
//         */                                                      //~1A65R~
//        @Override                                                //~1A65R~
//        protected void onPostExecute(String result) {            //~1A65R~
//            if (Dump.Y) Dump.println("onPostExecute");           //~1A65R~
//            if (result != null) {                                //~1A65R~
//                statusText.setText("File copied - " + result);   //~1A65R~
//                Intent intent = new Intent();                    //~1A65R~
//                intent.setAction(android.content.Intent.ACTION_VIEW);//~1A65R~
//                intent.setDataAndType(Uri.parse("file://" + result), "image/*");//~1A65R~
//                context.startActivity(intent);                   //~1A65R~
//            }                                                    //~1A65R~

//        }                                                        //~1A65R~

//        /*                                                       //~1A65R~
//         * (non-Javadoc)                                         //~1A65R~
//         * @see android.os.AsyncTask#onPreExecute()              //~1A65R~
//         */                                                      //~1A65R~
//        @Override                                                //~1A65R~
//        protected void onPreExecute() {                          //~1A65R~
//            if (Dump.Y) Dump.println("onPreExecute");            //~1A65R~
////          statusText.setText("Opening a server socket");       //~1A65R~
//            statusText.setText(WDA.getResourceString(R.string.OpeningServerSocket));//~1A65R~
//        }                                                        //~1A65R~

//    }                                                            //~1A65R~

//    public static boolean copyFile(InputStream inputStream, OutputStream out) {//~1A65R~
//        byte buf[] = new byte[1024];                             //~1A65R~
//        int len;                                                 //~1A65R~
//        try {                                                    //~1A65R~
//            while ((len = inputStream.read(buf)) != -1) {        //~1A65R~
//                out.write(buf, 0, len);                          //~1A65R~

//            }                                                    //~1A65R~
//            out.close();                                         //~1A65R~
//            inputStream.close();                                 //~1A65R~
//        } catch (IOException e) {                                //~1A65R~
//            Log.d(WiFiDirectActivity.TAG, e.toString());         //~1A65R~
//            return false;                                        //~1A65R~
//        }                                                        //~1A65R~
//        return true;                                             //~1A65R~
//    }                                                            //~1A65R~
	//***********************************************************************************//~1A65I~
	//*GroupInfoListener                                           //~1A65I~
	//***********************************************************************************//~1A65I~
    @Override                                                      //~1A65I~
    public void onGroupInfoAvailable(final WifiP2pGroup Pgroup)    //~1A65I~
	{                                                              //~1A65I~
    	String localDevName=LOCAL_HOSTNAME;                        //~va44R~
    //*******************                                          //~1A65I~
      	try                                                        //~1A65I~
      	{                                                          //~1A65I~
      		if (Dump.Y) Dump.println("DeviceDetailFragment:onGroupInfoAvailable Pgroup="+Pgroup);//~va44R~
            if (Pgroup==null)                                      //~va44I~
            	return;                                            //~va44I~
      		if (Dump.Y) Dump.println("DeviceDetailFragment:onGroupInfoAvailable Interface="+Pgroup.getInterface()+",networkname="+Pgroup.getNetworkName()+",passphrase="+Pgroup.getPassphrase()+",toString="+Pgroup.toString());//~va44I~
        	boolean owner=Pgroup.isGroupOwner();                   //~1A65I~
	    	WifiP2pDevice deviceOwner=Pgroup.getOwner();           //~1A65I~
            swGroupInfoOwner=owner;                                //~@@@@I~
    		AG.aWDA.enableCBWantGroupOwner(false,owner);           //~9A03I~
            nameGroupInfoOwner=deviceOwner.deviceName;             //~@@@@I~
      		if (Dump.Y) Dump.println("DeviceDetailFragment:onGroupInfoAvailable owner="+owner+",ownername="+deviceOwner.deviceName);//~1A65I~
	    	Collection<WifiP2pDevice> clients=Pgroup.getClientList();//~1A65I~
		    List<WifiP2pDevice> clientList=new ArrayList<WifiP2pDevice>();//~1A65I~
        	clientList.addAll(clients);                            //~1A65I~
	        int sz=clientList.size();                              //~1A65I~
      		if (Dump.Y) Dump.println("DeviceDetailFragment:onGroupInfoAvailable client list ctr="+sz);//~1A65I~
            int clientCtr=0;                                       //~1A65I~
                                                                   //~1A65I~
		    String peers="";                                       //~1A65R~
            for (int ii=0;ii<sz;ii++)                              //~1A65I~
            {                                                      //~1A65I~
		    	WifiP2pDevice client=clientList.get(ii);     //~1A65I~
                int status=client.status;                          //~1A65I~
	      		if (Dump.Y) Dump.println("DeviceDetailFragment:onGroupInfoAvailable clientList name="+client.deviceName+",addr="+client.deviceAddress+",status="+DeviceListFragment.getDeviceStatus(status)+",owner="+client.isGroupOwner());//~1A65I~
                if (status==WifiP2pDevice.CONNECTED                //~1A65I~
                &&  !client.isGroupOwner())                        //~1A65I~
                {                                                  //~1A65I~
                	clientCtr++;                                   //~1A65I~
//                  peers+=client.deviceName+" ";                  //~1A67R~
                    peers+=getDeviceName(client)+" ";              //~1A67R~
		      		if (Dump.Y) Dump.println("DeviceDetailFragment:onGroupInfoAvailable connected devices="+peers);//~va44I~
//                  if (localDevName==null)                        //~va44R~
//                  	localDevName=peers;                        //~va44R~
                }                                                  //~1A65I~
            }                                                      //~1A65I~
            if (owner)                                             //~1A65I~
            {                                                      //~va44I~
				if (clientCtr!=0)                                  //~1A65R~
	            	peerDevice=peers.trim();                       //~1A65R~
                else                                               //~1A65I~
	            	peerDevice="None";                             //~1A65I~
                localDevName=getDeviceName(deviceOwner);           //~va44I~
            }                                                      //~va44I~
            else                                                   //~1A65I~
            {                                                      //~1A67R~
//          	peerDevice=deviceOwner.deviceName;                 //~1A67R~
	            peerDevice=getDeviceName(deviceOwner);             //~1A67R~
            }                                                      //~1A67R~
            WDA.getDeviceListFragment().updateThisDevice(localDevName,owner);//~va44R~
//            TextView tv=tvDeviceInfo;                              //~1A65I~//~@@@@R~
////          tv.setText(WDA.getResourceString(R.string.PeerDeviceName)+peerDevice);//~1A65R~//~1A84R~//~1A90I~//~@@@@R~
//            tv.setText(peerDevice);                                //~1A84I~//~1A90I~//~@@@@R~
//            String ip=IPConnectionWD.getRemoteIPAddr(true/*return null if N/A*/);//~1A84R~//~1A90I~//~@@@@R~
//            if (ip==null)                                          //~1A84I~//~1A90I~//~@@@@R~
//            {                                                      //~1A84I~//~1A90I~//~@@@@R~
//                tvPeerStatus.setText(AG.resource.getString(R.string.DeviceConnected));  //paired//~1A84I~//~1A90I~//~@@@@R~
////              if (owner)                                         //~1A84I~//~1A8mR~//~1A90I~//~@@@@R~
//                if (!owner)   //i'm client -->peer is server       //~1A8mI~//~1A90I~//~@@@@R~
//                    tvPeerInfo.setText(AG.resource.getString(R.string.MyOwnerYes));//~1A84I~//~1A90I~//~@@@@R~
//                else                                               //~1A84I~//~1A90I~//~@@@@R~
//                    tvPeerInfo.setText(AG.resource.getString(R.string.MyOwnerNo));//~1A84I~//~1A90I~//~@@@@R~
//            }                                                      //~1A84I~//~1A90I~//~@@@@R~
//            else                                                   //~1A84I~//~1A90I~//~@@@@R~
//            {                                                      //~1A84I~//~1A90I~//~@@@@R~
//                tvPeerStatus.setText(AG.resource.getString(R.string.IPInSession));//~1A84R~//~1A90I~//~@@@@R~
//                tvPeerInfo.setText(ip);                            //~1A84I~//~1A90I~//~@@@@R~
//            }                                                      //~1A84I~//~1A90I~//~@@@@R~
      		if (Dump.Y) Dump.println("DeviceDetailFragment:onGroupInfoAvailable settext deviceinfo "+peerDevice);//~1A65I~
      	}                                                          //~1A65I~
      	catch(Exception e)                                         //~1A65I~
      	{                                                          //~1A65I~
      		Dump.println(e,"DeviceDetailFragment:onGroupInfoAvailable");//~1A65I~
      	}                                                          //~1A65I~
    }                                                              //~1A65I~
	//***********************************************************************************//~va44I~
	//*from WifiDirectActivity:DeviceInfoListener(from API29)      //~va44R~
	//***********************************************************************************//~va44I~
    public void onDeviceInfoAvailable(WifiP2pDevice Pdevice)       //~va44I~
	{                                                              //~va44I~
    //*******************                                          //~va44I~
      	try                                                        //~va44I~
      	{                                                          //~va44I~
      		if (Dump.Y) Dump.println("DeviceDetailFragment:onDeviceupInfoAvailable swGroupInfoOwner="+swGroupInfoOwner+",device="+Utils.toString(Pdevice));//~va44R~
            if (Pdevice==null)                                     //~va44I~
            	return;                                            //~va44I~
            WDA.getDeviceListFragment().updateThisDevice(Pdevice); //+va44R~
//          WDA.getDeviceListFragment().updateThisDevice(getDeviceName(Pdevice),swGroupInfoOwner);//+va44R~
      	}                                                          //~va44I~
      	catch(Exception e)                                         //~va44I~
      	{                                                          //~va44I~
      		Dump.println(e,"DeviceDetailFragment:onDeviceInfoAvailable");//~va44I~
      	}                                                          //~va44I~
    }                                                              //~va44I~
//    //*******************************************************************************************************//~1A65I~//~@@@@R~
//    public void setVisibility(int Pvisibility)                     //~1A65R~//~@@@@R~
//    {                                                              //~1A65I~//~@@@@R~
//        View v=getView();                                          //~1A65I~//~@@@@R~
//        v.setVisibility(Pvisibility);                              //~1A65I~//~@@@@R~
//    }                                                              //~1A65I~//~@@@@R~
//    //*******************************************************************************************************//~1A65I~//~@@@@R~
//    public View getView()                                          //~1A65M~//~@@@@R~
//    {                                                              //~1A65M~//~@@@@R~
////      return layoutView.findViewById(R.id.device_detail_wd);     //~1A65M~//~@@@@R~
//        return layoutView;                                       //~@@@@R~
//    }                                                              //~1A65M~//~@@@@R~
	//*******************************************************************************************************//~1A65M~
    private Resources getResources()                               //~1A65I~
    {                                                              //~1A65I~
        return AG.resource;                                        //~1A65I~
    }                                                              //~1A65I~
	//*******************************************************************************************************//~1A67R~
    public static String getDeviceName(WifiP2pDevice Pdevice)      //~1A67R~
    {                                                              //~1A67R~
    	String name;                                               //~1A67R~
		name=Pdevice.deviceName;                                   //~1A67R~
		if (name==null || name.equals(""))                         //~1A67R~
    		name=Pdevice.deviceAddress;                            //~1A67R~
        if (Dump.Y) Dump.println("DeviceDetailFragment.getDeviceName name="+name+",devicename="+Utils.toString(Pdevice.deviceName)+",deviceAddress="+Utils.toString(Pdevice.deviceAddress));//~@@@@I~
        return name;                                               //~1A67R~
    }                                                              //~1A67R~
	//*******************************************************************************************************//~1A67I~
	//*from WiFiDirectActivity:notifyConnected                     //~1A67I~
	//*******************************************************************************************************//~1A67I~
//  public void connected(int Presult,String Pmsg)                 //~1A67I~//~@@@@R~
    public void connected(boolean PswErr,String Pmsg)              //~@@@@I~
    {                                                              //~1A67I~
//    	dismissProgressDialog(PROGRESS_CONNECT);                                   //~1A67I~//~9A05R~
//      tvStatusText.setText(Pmsg);                                //~1A67I~//~@@@@R~
        setStatus(PswErr,Pmsg);                                    //~@@@@R~
    }                                                              //~1A67I~
	//*******************************************************************************************************//~@@@@I~
    public void setStatus(boolean PswErr,int Pmsgid)               //~@@@@R~
    {                                                              //~@@@@I~
        setStatus(PswErr,Utils.getStr(Pmsgid));                    //~@@@@R~
    }                                                              //~@@@@I~
	//*******************************************************************************************************//~@@@@I~
    public void setStatus(boolean PswErr,String Pmsg)              //~@@@@R~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("DeviceDetailFragment.setStatus txt="+Pmsg);//~@@@@I~
        if (PswErr)                                                //~@@@@I~
	        tvStatusText.setBackgroundColor(COLOR_STATUS_ERR);     //~@@@@I~
        else                                                       //~@@@@I~
	        tvStatusText.setBackgroundColor(COLOR_STATUS_NORMAL);  //~@@@@I~
                                                                   //~@@@@I~
        tvStatusText.setText(Pmsg);                                //~@@@@I~
    }                                                              //~@@@@I~
	//*******************************************************************************************************//~1A67I~
    private void dismissProgressDialog(int Paction)                           //~1A67I~//~9A05R~
    {                                                              //~1A67I~
        if (Dump.Y) Dump.println("DeviceDetailFragment.dismissProgressDialog action="+Paction);//~9A04I~//~9A05R~
//  	if (progressDialog != null && progressDialog.isShowing())  //~1A67R~
//  	{                                                          //~1A67R~
//      	progressDialog.dismiss();                              //~1A67R~
//     	}                                                          //~1A67R~
//      URunnable.dismissDialog(progressDialog);                       //~1A67I~//~9A04R~
        WDA.getWDActivity().dismissProgDlg(Paction);                       //~9A04I~//~9A05R~
    }                                                              //~1A67I~
////****************************************                         //~1A67I~//~9A04R~
////  private ProgressDialog progressDialogShow(int Ptitleid,String Pmsg,boolean Pindeterminate,boolean Pcancelable)//~1A6tR~//~9A04R~
////  private URunnable.URunnableData progressDialogShow(int Ptitleid, String Pmsg, boolean Pindeterminate, boolean Pcancelable)//~1A6tR~//~@@@@R~//~9A04R~
//    public  URunnable.URunnableData progressDialogShow(int Ptitleid, String Pmsg, boolean Pindeterminate, boolean Pcancelable)//~@@@@I~//~9A04R~
//    {                                                              //~1A67I~//~9A04R~
//        if (Dump.Y) Dump.println("ProgDialog:simpleProgressDialogShow");//~1A67I~//~9A04R~
//        return URunnable.simpleProgressDialogShow(Ptitleid,Pmsg,Pindeterminate,Pcancelable);//~1A67I~//~9A04R~
//    }                                                              //~1A67I~//~9A04R~
	//*******************************************************************************************************//~1A87I~//~1A89I~//~@@@@I~
	//*question unpair on GroupOwner                               //~@@@@I~
	//*******************************************************************************************************//~@@@@I~
	public boolean alertUnpairOnGO()                                 //~@@@@I~
    {                                                              //~1A87I~//~1A89I~//~@@@@I~
    	boolean rc=true;	//execute unpair at return             //~1A89I~//~@@@@I~
    	if (Dump.Y) Dump.println("DeviceDetailFragment:alertUnpairOnGO");//~1A87I~//~1A89I~//~@@@@I~
        Alert.AlertI ai=new Alert.AlertI()                                 //~1A89R~//~@@@@I~
                            {                                  //~1A89I~//~@@@@I~
                                @Override                      //~1A89I~//~@@@@I~
                                public int alertButtonAction(int Pbuttonid,int Pitempos)//~1A89I~//~@@@@I~
                                {                              //~1A89I~//~@@@@I~
                                    if (Dump.Y) Dump.println("DeviceDetailFragment.alertUnpairOnGO.alertButtonAction buttonid="+Integer.toHexString(Pbuttonid));//~1A89I~//~@@@@I~
                                    if (Pbuttonid==Alert.BUTTON_POSITIVE)//~1A89R~//~@@@@I~
                                    {                          //~1Ac2I~//~@@@@I~
                                    	doUnpairGO(true);          //~@@@@I~
                                    }                          //~1Ac2I~//~@@@@I~
                                    return 1;                  //~1A89I~//~@@@@I~
                                }                              //~1A89I~//~@@@@I~
                            };                                 //~1A89I~//~@@@@I~
	    int titleid=R.string.Title_ConfirmUnpairGO;//~1A89I~       //~@@@@I~
    	int msgid=R.string.Msg_ConfirmUnpairGO;//~1A89I~          //~@@@@I~
        Alert.showAlert(titleid,msgid,Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,ai);//~@@@@I~
	    rc=false;	//execute unpair at later dialog response  //~1A89I~//~9727R~//~@@@@I~
    	if (Dump.Y) Dump.println("DeviceDetailFragment.alertUnpairOnGO rc="+rc);      //~1A89I~//~@@@@I~//~0116R~
        return rc;                                                 //~1A89I~//~@@@@I~
    }                                                              //~1A87I~//~1A89I~//~@@@@I~
}
