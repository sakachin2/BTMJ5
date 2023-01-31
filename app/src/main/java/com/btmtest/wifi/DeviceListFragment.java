//*CID://+vavgR~:                             update#=  195;       //~vavgR~
//*************************************************************************//~1A65I~
//2023/01/25 vavg avoid duplicated drawListView request            //~vavgI~
//2023/01/24 vavd pairing connect by macaddr is not avail. It required scan befre. so delete vavc//~vavdI~
//2023/01/23 vavc display historical entry on WD devicelist        //~vavcI~
//2023/01/22 vav9 display not devicename but username on connection dialog//~vav9I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2020/11/19 va44 Android10:WD;no THIS_DEVICE_CHANGED broadcast msg.paired flag yet off//~va40I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//1Aa5 2015/04/20 test function for mdpi listview                  //~1Aa5I~
//1A8p 2015/04/10 listview devcice list for mdpi                   //~1A8pI~
//1A8n 2015/04/09 Wi-Fi direct ip addr is not shown                //~1A6nI~//~1A8nI~
//1A6t 2015/02/18 (BUG)simpleProgress Dialog thread err exception  //~1A6tI~
//1A6e 2015/02/13 runOnUiThread for processingdialog               //~1A6eI~
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

package com.btmtest.wifi;                                          //~1Aa5R~

import android.annotation.TargetApi;                               //~1A65I~
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.btmtest.BT.Members;
import com.btmtest.R;
import com.btmtest.dialog.BTCDialog;
import com.btmtest.game.AccName;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.btmtest.StaticVars.AG;                           //~9721I~//~@@@@I~//~1Aa5I~

/**
 * A ListFragment that displays available peers on discovery and requests the
 * parent activity to handle user interaction events
 */
//@TargetApi(AG.ICE_CREAM_SANDWICH)   //api14                           //~1A65R~//~1Aa5R~
//public class DeviceListFragment extends ListFragment implements PeerListListener {//~1A65R~
public class DeviceListFragment implements PeerListListener {      //~1A65R~

	private static final int WD_STATUS_HISTORY=-1;                 //~vavcR~
	private static final int LAYOUTID_LIST_ROW=R.layout.textrowdevice_wd;//~1A65I~
	private static final int LAYOUTID_LIST_ROW_SMALLFONT=R.layout.textrowdevice_wd_theme;//~vac5I~
//	private static final int LAYOUTID_LIST_ROW_MDPI=R.layout.textrowdevice_wd_mdpi;//~1A6pI~//~1A8pI~
	private static final int COLOR_SELECTED=0xff000080;            //~1A65I~
	private static final int COLOR_BG_DEVICE_LIST= BTCDialog.COLOR_BG_DEVICE_LIST; //Color.rgb(0xc8,0xff,0xc8);  //orange//~v@@@R~//~1Aa5I~
	public  static final int BTNID_DISCOVER=R.id.atn_direct_discover;//~1A65I~
    public  static final int BTNID_P2PENABLE=R.id.atn_direct_enable;//~1A67I~//~1Aa5R~
//	public  static final int BTNID_NFC= R.id.WiFiNFCButton;         //~1A6aI~
                                                                   //~1A65I~
    private List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();
//  ProgressDialog progressDialog = null;                          //~1A6tR~
//  URunnable.URunnableData progressDialog = null;                 //~va40R~
    View mContentView = null;
    private WifiP2pDevice device;
    private ListView listView;                                     //~1A65I~
	private WiFiPeerListAdapter adapter;                           //~1A65I~
//	private ViewGroup layoutView;                                  //~1A65I~//~1Aa5R~
  	private View layoutView;                                       //~1Aa5I~
//  private TextView tvMyName,tvMyStatus,tvEmptyMsg,tvMyOwner;     //~1A65R~//~@@@@R~
    private TextView tvMyName,tvMyStatus,tvMyOwner;                //~@@@@I~
	private int selectedPos=-1;                                    //~1A65I~
    public String thisDevice;                                      //~1A65R~
    public String thisDeviceAddr;                                  //~1A6aI~
    public int thisStatus=-1;                                       //~1A65I~
    public int thisOwner=-1;                                       //~1A65I~
    private Button btnDiscover;                                    //~1A65I~
    private Button btnP2PEnable;                                   //~1A67I~
    private Button btnNFC;                                         //~1A6aI~
    private int resid_textrow;                                     //~1A6pI~//~1A8pI~
    public int statusConnected;                                    //~@@@@R~
    public int ctrConnected,ctrInvited;                            //~0116R~
//  public boolean swShowHistory=true;                             //~vavcI~//~vavdR~
    public boolean swShowHistory=false;                            //~vavdI~
    //******************************************                   //~1A65I~
    public DeviceListFragment()                                    //~1A65I~
    {                                                              //~1A65I~
    }                                                              //~1A65I~
    //******************************************                   //~1A65I~
//    @Override                                                    //~1A65R~
//    public void onActivityCreated(Bundle savedInstanceState) {   //~1A65R~
//        super.onActivityCreated(savedInstanceState);             //~1A65R~
//        this.setListAdapter(new WiFiPeerListAdapter(getActivity(), R.layout.row_devices, peers));//~1A65R~

//    }                                                            //~1A65R~

//    @Override                                                    //~1A65R~
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {//~1A65R~
//        mContentView = inflater.inflate(R.layout.device_list, null);//~1A65R~
//        return mContentView;                                     //~1A65R~
//    }                                                            //~1A65R~
    //******************************************                   //~1A65I~
//  public void initFragment(ViewGroup Playoutview)                //~1A65R~//~1Aa5R~
    public void initFragment(View Playoutview)                     //~1Aa5I~
    {                                                              //~1A65I~
//      resid_textrow=AG.layoutMdpi ? LAYOUTID_LIST_ROW_MDPI : LAYOUTID_LIST_ROW;//~1A6pI~//~1A8pI~//~1Aa5R~
//      resid_textrow=LAYOUTID_LIST_ROW;                           //~1Aa5I~//~vac5R~
        resid_textrow=AG.swSmallFont ? LAYOUTID_LIST_ROW_SMALLFONT : LAYOUTID_LIST_ROW;//~vac5R~
    	layoutView=Playoutview;                                    //~1A65I~
		tvMyName=(TextView)layoutView.findViewById(R.id.my_name);  //~1A65I~
//        tvMyStatus=(TextView)layoutView.findViewById(R.id.my_status);//~1A65I~//~1Aa5R~
		tvMyOwner=(TextView)layoutView.findViewById(R.id.my_groupowner);//~1A65I~
//  	tvEmptyMsg=(TextView)layoutView.findViewById(R.id.emptymsg);//~1A65I~//~@@@@R~
    	listView=(ListView)layoutView.findViewById(R.id.device_list);//~1A65R~
//  	adapter=new WiFiPeerListAdapter(getActivity(),LAYOUTID_LIST_ROW,peers);//~1A65R~//~1A6pR~//~1A8pI~
    	adapter=new WiFiPeerListAdapter(getActivity(),resid_textrow,peers);//~1A6pI~//~1A8pI~
        listView.setAdapter(adapter);                                 //~1A65I~
        OnItemClickListener itemlistener=new ListItemClickListener(this);//~1A65I~
        listView.setOnItemClickListener(itemlistener);             //~1A65I~
    	btnDiscover=(Button)layoutView.findViewById(BTNID_DISCOVER);//~1A65I~
//      WDA.SWDA.setButtonListener(btnDiscover);                   //~1A65I~//~1Aa5R~
        AG.aWDA.setButtonListener(btnDiscover);                   //~1Aa5I~
        btnP2PEnable=(Button)layoutView.findViewById(BTNID_P2PENABLE);//~1A67I~//~1Aa5R~
//      WDA.SWDA.setButtonListener(btnP2PEnable);                  //~1A67I~//~1Aa5R~
        AG.aWDA.setButtonListener(btnP2PEnable);                   //~1Aa5I~
//  	btnNFC=(Button)layoutView.findViewById(BTNID_NFC);         //~1A6aI~//~1Aa5R~
//      WDA.SWDA.setButtonListener(btnNFC);                        //~1A6aI~//~1Aa5R~
        if (swShowHistory)                                         //~vavcM~
        	createHistoryList();                                   //~vavcM~
//      if (Dump.Y) Dump.println("DeviceListFragment.initFragment getMacAddress="+Utils.getMacAddress());//~vavdR~
    }                                                              //~1Aa5I~
    //******************************************                   //~1A65I~

    /**
     * @return this device
     */
    public WifiP2pDevice getDevice() {
        return device;
    }

//  private static String getDeviceStatus(int deviceStatus) {      //~1A65R~
    public static String getDeviceStatus(int deviceStatus)         //~1A65I~
    {                                                              //~1A65I~
//      Log.d(WiFiDirectActivity.TAG, "Peer status :" + deviceStatus);//~1A65R~
        String s;                                                  //~1Aa5I~
        switch (deviceStatus) {
            case WifiP2pDevice.AVAILABLE:
//              return "Available";                                //~1A65R~
                s=WDA.getResourceString(R.string.DeviceAvailable);//~1A65I~//~1Aa5R~
                break;                                             //~1Aa5I~
            case WifiP2pDevice.INVITED:
//              return "Invited";                                  //~1A65R~
                s=WDA.getResourceString(R.string.DeviceInvited);//~1A65I~//~1Aa5R~
                break;                                             //~1Aa5I~
            case WifiP2pDevice.CONNECTED:
//              return "Connected";                                //~1A65R~
                s=WDA.getResourceString(R.string.DeviceConnected);//~1A65I~//~1Aa5R~
                break;                                             //~1Aa5I~
            case WifiP2pDevice.FAILED:
//              return "Failed";                                   //~1A65R~
                s=WDA.getResourceString(R.string.DeviceFailed);//~1A65I~//~1Aa5R~
                break;                                             //~1Aa5I~
            case WifiP2pDevice.UNAVAILABLE:
//              return "Unavailable";                              //~1A65R~
                s=WDA.getResourceString(R.string.DeviceUnavailable);//~1A65I~//~1Aa5R~
                break;                                             //~1Aa5I~
            case WD_STATUS_HISTORY:                                //~vavcI~
                s=WDA.getResourceString(R.string.DeviceHistory);   //~vavcI~
                break;                                             //~vavcI~
            default:
//              return "Unknown";                                  //~1A65R~
                s=WDA.getResourceString(R.string.DeviceUnknown);//~1A65I~//~1Aa5R~
        }
        if (Dump.Y) Dump.println("DeviceListFragment:getDeviceStatus:Peer status :" + deviceStatus+"="+s);//~1A65R~//~1Aa5I~//~@@@@R~
        return s;                                                  //~1Aa5I~
    }
    //******************************************************************************//~1Aa5I~
    public WifiP2pDevice getSelectedDevice()                       //~1Aa5I~
    {                                                              //~1Aa5I~
        if (Dump.Y) Dump.println("DeviceListFragment.getSelectedDevice selectedPos="+selectedPos);//~1Aa5I~
        if (selectedPos<0)                                         //~1Aa5I~
        	return null;                                           //~1Aa5I~
        WifiP2pDevice device=(WifiP2pDevice)getListAdapter().getItem(selectedPos);//~1Aa5I~
        if (Dump.Y) Dump.println("DeviceListFragment.getSelectedDevice deviceName="+device.deviceName);//~1Aa5I~
        return device;                                             //~1Aa5I~
    }                                                              //~1Aa5I~
    //******************************************************************************//~1Aa5I~
    //*get ctr except available                                    //~1Aa5I~
    //******************************************************************************//~1Aa5I~
    public int getDeviceCtrToUnpair()                              //~1Aa5I~
    {                                                              //~1Aa5I~
        int sz=getListAdapter().items.size();                            //~1Aa5I~
        int ctr=0;                                                 //~1Aa5I~
        ctrConnected=0;                                            //~0116I~
        ctrInvited=0;                                              //~0116I~
        for (int ii=0;ii<sz;ii++)                                  //~1Aa5I~
        {                                                          //~1Aa5I~
	        WifiP2pDevice device=(WifiP2pDevice)getListAdapter().getItem(ii);//~1Aa5I~
	        if (Dump.Y) Dump.println("DeviceListFragment.getDeviceCtrToUnpair sz="+sz+",ii="+ii+",device="+device.toString());//~1Aa5R~
            int status=device.status;                              //~1Aa5I~
            switch(status)                                         //~1Aa5I~
            {                                                      //~1Aa5I~
            case WifiP2pDevice.CONNECTED:    //0                   //~1Aa5I~
                ctr++;                                             //~1Aa5I~
                ctrConnected++;                                     //~0116I~
                break;                                             //~1Aa5I~
            case WifiP2pDevice.INVITED:      //1                   //~1Aa5I~
                ctr++;                                             //~1Aa5I~
                ctrInvited++;                                      //~0116I~
                break;                                             //~1Aa5I~
            case WifiP2pDevice.FAILED:       //2                   //~1Aa5I~
                ctr++;                                             //~1Aa5I~
                break;                                             //~1Aa5I~
            case WifiP2pDevice.AVAILABLE:    //3                   //~1Aa5I~
                break;                                             //~1Aa5I~
            case WifiP2pDevice.UNAVAILABLE:  //4                   //~1Aa5I~
                break;                                             //~1Aa5I~
            }                                                      //~1Aa5I~
                                                                   //~1Aa5I~
        }                                                          //~1Aa5I~
        if (Dump.Y) Dump.println("DeviceListFragment.getDeviceCtrToUnpair ctr="+ctr+",ctrConnected="+ctrConnected);//~1Aa5R~//~0116R~
        return ctr;                                             //~1Aa5I~
    }                                                              //~1Aa5I~
    //******************************************************************************//~1Aa5I~
    //*set Name,NameRemote,isPaired                                //~1Aa5I~
    //******************************************************************************//~1Aa5I~
    public ConnectionData getSelectedDeviceData()                  //~1Aa5I~
    {                                                              //~1Aa5I~
        ConnectionData cd=new ConnectionData();                    //~1Aa5I~
        WifiP2pDevice device=getSelectedDevice();                  //~1Aa5I~
        if (Dump.Y) Dump.println("DeviceListFragment.getSelectedDeviceData device="+Utils.toString(device));//~1Aa5I~
        if (device==null)                                          //~1Aa5I~
        {                                                          //~1Aa5I~
        	setStatus(true,R.string.Wifi_SelectDevice);            //~1Aa5R~
        	return null;                                           //~1Aa5I~
        }                                                          //~1Aa5I~
    	cd.Name=thisDevice;                                        //~1Aa5R~
    	cd.NameRemote=device.deviceName;                           //~1Aa5I~
        int status=device.status;                                  //~1Aa5I~
    	cd.isPaired=status==WifiP2pDevice.CONNECTED;               //~1Aa5I~
        if (Dump.Y) Dump.println("DeviceListFragment.getSelectedDeviceData name="+cd.Name+",isPaired="+cd.isPaired);//~1Aa5I~
        return cd;
    }                                                              //~1Aa5I~
    //******************************************************************************//~1A65R~
    /**
     * Initiate a connection with the peer.
     */
//  @Override                                                      //~1A65R~
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (Dump.Y) Dump.println("DeviceListFragment onListItemClick selectedpos="+position);//~1Aa5I~
        WifiP2pDevice device = (WifiP2pDevice) getListAdapter().getItem(position);
//      ((DeviceActionListener) getActivity()).showDetails(device);//~1A65R~
        ((DeviceActionListener) WDA.getWDActivity()).showDetails(device);//~1A65I~
        selectedPos=position;                                      //~1A65I~
//      ((WiFiPeerListAdapter)getListAdapter()).notifyDataSetChanged(); //call getView()//~1A67I~//~1Aa5R~
    	drawListView();                                            //~1Aa5I~
        if (Dump.Y) Dump.println("DeviceListFragment onListItemClick selectedpos="+position);//~1A67I~
    }
    //******************************************************************************//~1Aa5I~
    public void onListItemClicked(int Pposition)                   //~1Aa5I~
	{                                                              //~1Aa5I~
        if (Dump.Y) Dump.println("DeviceListFragment onListItemClicked positoion="+Pposition);//~1Aa5I~
        WifiP2pDevice device = (WifiP2pDevice) getListAdapter().getItem(Pposition);//~1Aa5I~
        ((DeviceActionListener) WDA.getWDActivity()).showDetails(device);//~1Aa5I~
        selectedPos=Pposition;                                      //~1Aa5I~
//      ((WiFiPeerListAdapter)getListAdapter()).notifyDataSetChanged(); //call getView()//~1Aa5R~
    	drawListView();                                            //~1Aa5I~
    }                                                              //~1Aa5I~
    //******************************************************************************//~vavgI~
    //*drawListView will be issued later                           //~vavgI~
    //******************************************************************************//~vavgI~
    public void onListItemClicked2(int Pposition)                  //~vavgI~
	{                                                              //~vavgI~
        if (Dump.Y) Dump.println("DeviceListFragment onListItemClicked2 positoion="+Pposition);//~vavgI~
        WifiP2pDevice device = (WifiP2pDevice) getListAdapter().getItem(Pposition);//~vavgI~
        ((DeviceActionListener) WDA.getWDActivity()).showDetails(device);//~vavgI~
        selectedPos=Pposition;                                     //~vavgI~
    }                                                              //~vavgI~
    //******************************************************************************//~1Aa5I~
    public void drawListView()                                     //~1Aa5I~
    {                                                              //~1Aa5R~
        if (Dump.Y) Dump.println("DeviceListFragment.drawListView");//~1Aa5R~
        ((WiFiPeerListAdapter)getListAdapter()).notifyDataSetChanged(); //call getView()//~1Aa5I~
    }                                                              //~1Aa5I~
    //******************************************************************************//~1A65I~
    /**
     * Array adapter for ListFragment that maintains WifiP2pDevice list.
     */
    private class WiFiPeerListAdapter extends ArrayAdapter<WifiP2pDevice> {

        private List<WifiP2pDevice> items;

        /**
         * @param context
         * @param textViewResourceId
         * @param objects
         */
        public WiFiPeerListAdapter(Context context, int textViewResourceId,
                List<WifiP2pDevice> objects) {
            super(context, textViewResourceId, objects);
            items = objects;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
          try                                                      //~1A65I~
          {                                                        //~1A65I~
          	if (Dump.Y) Dump.println("DeviceListFragment:getView pos="+position);//~1A65I~
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
//              v = vi.inflate(R.layout.row_devices, null);        //~1A65R~
//              v = vi.inflate(LAYOUTID_LIST_ROW,null);            //~1A65I~//~1A6pR~//~1A8pI~
                v = vi.inflate(resid_textrow,null);                //~1A6pI~//~1A8pI~
            }
            WifiP2pDevice device = items.get(position);
            if (device != null) {
                TextView top = (TextView) v.findViewById(R.id.device_name);
                TextView bottom = (TextView) v.findViewById(R.id.device_details);
                boolean swYN=false;                                //~vav9I~
                if (top != null) {
//                  top.setText(device.deviceName);                //~1A67R~
                    top.setText(DeviceDetailFragment.getDeviceName(device));//~1A67I~
                    if (position==selectedPos)                     //~1A65I~
                    {                                              //~1A65I~
                		top.setBackgroundColor(COLOR_SELECTED);    //~1A65I~
                		top.setTextColor(Color.WHITE);             //~1A65I~
                        if (Dump.Y) Dump.println("DeviceListFragment:getView pos==selcted="+position);//~1A67I~
                    }                                              //~1A65I~
                    else                                           //~1A65I~
                    {                                              //~1A65I~
                        if (Dump.Y) Dump.println("DeviceListFragment:getView pos!=selcted="+selectedPos);//~1A67R~
//              		top.setBackgroundColor(Color.WHITE);       //~1A65I~//~1Aa5R~
                		top.setBackgroundColor(COLOR_BG_DEVICE_LIST);   //~1Aa5I~
                		top.setTextColor(Color.BLACK);             //~1A65I~
                    }                                              //~1A65I~
                }
                if (bottom != null)                                //~1Aa5R~
				{                                                  //~1Aa5I~
//                  String ipa=IPConnectionWD.getRemoteIPAddr(true/*return null if N/A*/);//~1A6tR~//~1A8nI~//~1Aa5R~
                    String ipa=null;                               //~1Aa5R~
                    if (device.status==WifiP2pDevice.CONNECTED)    //~1Aa5I~
	                    ipa=AG.aIPMulti.getRemoteIPAddr(DeviceDetailFragment.getDeviceName(device));//~1Aa5I~
          			if (Dump.Y) Dump.println("DeviceListFragment:getView ipa="+ Utils.toString(ipa));//~1Aa5R~
//                  if (ipa==null)                                 //~1A6tI~//~1A6nR~//~1A8nI~
					String yourName=null;                          //~@@@@I~
                    if (ipa!=null)                                 //~1A6nI~//~1A8nI~
                    	yourName=getYourName(ipa);                 //~@@@@I~
                    if (yourName!=null)                            //~@@@@I~
                    {                                              //~@@@@I~
//                  	bottom.setText(ipa);                       //~1A6tI~//~1A8nI~//~1Aa5R~
//                  	bottom.setText(Utils.getStr(R.string.IPInSession));//~1Aa5I~//~@@@@R~
//                      String yourName=getYourName(ipa);          //~@@@@R~
                    	bottom.setText(yourName);                  //~@@@@I~
                        swYN=true;                                 //~vav9I~
                    }                                              //~@@@@I~
                    else                                           //~1A6tI~//~1A8nI~
	                    bottom.setText(getDeviceStatus(device.status));//~1Aa5R~
                	bottom.setBackgroundColor(COLOR_BG_DEVICE_LIST);    //~1Aa5I~
                    if (swYN)                                      //~vav9I~
		                updateUserName(top,yourName);              //~vav9I~
                    else                                           //~vav9I~
		                setUserName(top);                          //~vav9I~
                }
                TextView owner=(TextView) v.findViewById(R.id.device_owner);//~1A65R~//~1Aa5R~//~@@@@R~
                if (owner != null) {                             //~1A65R~//~1Aa5R~//~@@@@R~
                    updateOwner(device,owner);     //not set device.isOwner return invalid value//~1Aa5R~//~@@@@R~
                }                                                //~1A65R~//~1Aa5R~//~@@@@R~
            }
          }                                                        //~1A65R~
          catch(Exception e)                                       //~1A65I~
          {                                                        //~1A65I~
          	Dump.println(e,"DeviceListFragment:getView");          //~1A65I~
          }                                                        //~1A65I~
            return v;

        }
    }
    //***********************************************************  //~@@@@R~
    private String getYourName(String PipAddr)                     //~@@@@I~
    {                                                              //~@@@@I~
    	Members members=AG.aBTMulti.BTGroup;                       //~@@@@I~
        int idx=members.searchByAddr(PipAddr);                     //~@@@@I~
        String yn=null;                                            //~@@@@I~
        if (idx>=0)                                                //~@@@@I~
        	yn=members.getYourName(idx);                           //~@@@@I~
//  	if (yn==null)                                              //~@@@@R~
//  		yn=Utils.getStr(R.string.IPInSession);                 //~@@@@R~
    	if (yn!=null && yn.equals(""))                             //~@@@@R~
    		yn=null;                                               //~@@@@I~
        if (Dump.Y) Dump.println("DeviceListFragment.getYourName yourname="+yn);//~@@@@I~
        return yn;                                                  //~@@@@I~
    }                                                              //~@@@@I~
    /**
     * Update UI for this device.
     * 
     * @param device WifiP2pDevice object
     */
    public void updateThisDevice(WifiP2pDevice device) {
        this.device = device;
        thisStatus=device.status;                                  //~1A65I~
        if (Dump.Y) Dump.println("DeviceListFragment:updateThis status="+thisStatus);//~1A65I~
        if (Dump.Y) Dump.println("DeviceListFragment updateThis:name="+device.deviceName+",addr="+device.deviceAddress);//~1A6aI~
//      if (Dump.Y) Dump.println("DeviceListFragment updateThis:getMacAddress="+Utils.getMacAddress());//~vavdR~
//      TextView view = (TextView) mContentView.findViewById(R.id.my_name);//~1A65R~
        TextView view =tvMyName;                                   //~1A65I~
//      view.setText(device.deviceName);                           //~1A67R~
        view.setText(DeviceDetailFragment.getDeviceName(device));                        //~1A67I~
        thisDevice=device.deviceName;                              //~1A65I~
        thisDeviceAddr=device.deviceAddress;                      //~1A6aI~
        AG.aIPMulti.thisDeviceMacAddr=thisDeviceAddr;                                                           //~1A65I~//~@@@@R~
//      view = (TextView) mContentView.findViewById(R.id.my_status);//~1A65R~
//        view =tvMyStatus;                                          //~1A65I~//~1Aa5R~
//        view.setText(getDeviceStatus(device.status));            //~1Aa5R~
        view =tvMyOwner;                                           //~1A65I~
        updateThisOwner(device);                               //~1A65R~
        if (Dump.Y) Dump.println("DeviceListFragment:updateThisDevice device="+device.deviceName+",owner="+device.isGroupOwner());//~1A65I~
//      setEmptyMsg();  //duplicated with updateThisOwner           //~1A65I~//~va44R~
    }
    //***************************************************************************//~va44I~
    //*from groupInfoAvailable                                     //~va44I~
    //***************************************************************************//~va44I~
    public void updateThisDevice(String PdeviceName,boolean PswOwner)//~va44I~
	{                                                              //~va44I~
		if (Dump.Y) Dump.println("DeviceListFragment:updateThisDevice PswOwner="+PswOwner+",thisDevice="+thisDevice+",PdeviceName="+PdeviceName);//~va44I~
        if (thisDevice!=null || PdeviceName==null)                 //~va44I~
            return;                                                //~va44I~
//      this.device = device;                                      //~va44I~
//      thisStatus=device.status;                                  //~va44I~
        thisStatus=WifiP2pDevice.CONNECTED;	//THIS_DEVICE_CHANGED was not received//~va44I~
        TextView view =tvMyName;                                   //~va44I~
//      view.setText(DeviceDetailFragment.getDeviceName(device));  //~va44I~
        view.setText(PdeviceName);                                 //~va44I~
//      thisDevice=device.deviceName;                              //~va44I~
        thisDevice=PdeviceName;                                    //~va44I~
//      thisDeviceAddr=device.deviceAddress;                       //~va44I~
//      AG.aIPMulti.thisDeviceMacAddr=thisDeviceAddr;              //~va44I~
//      view =tvMyOwner;                                           //~va44I~
//      updateThisOwner(device);                                   //~va44I~
    	updateThisOwner(PswOwner);                                   //~va44I~
    }                                                              //~va44I~

    //***************************************************************************//~1A65I~
    //*PeerListListener                                            //~1A65I~
    //***************************************************************************//~1A65I~
    @Override
    public void onPeersAvailable(WifiP2pDeviceList peerList) {
      try                                                          //~1A65I~
      {                                                            //~1A65I~
      	Collection<WifiP2pDevice> devices=peerList.getDeviceList();//~1A65I~
      	if (Dump.Y) Dump.println("DeviceListFragment.onPeersAvailable list count="+devices.size());//~1A65I~//~1Aa5R~//~@@@@R~
//      if (progressDialog != null && progressDialog.isShowing()) {//~1A6eR~
//          progressDialog.dismiss();                              //~1A6eR~
//      }                                                          //~1A6eR~
//  	dismissProgressDialog();    //move to WifiDirectBroadCastReceiver.onReceive CONNECTION_CHANGED_ACTION//~@@@@R~//~0113R~
        selectedPos=-1;                                            //~1A65I~
        if (Dump.Y) Dump.println("DeviceListFragment:onPeersAvailable slectedpos="+selectedPos);//~1A67I~//~1Aa5R~
        boolean swDraw=isListUpdate(peers,devices);                //~vavgI~
        peers.clear();
//      peers.addAll(peerList.getDeviceList());                    //~1A65R~
        peers.addAll(devices);                                     //~1A65I~
        if (swShowHistory)                                         //~vavcI~
        	mergeHistory(peers);                                   //~vavcI~
        if (peers.size()!=0)                                       //~va44R~
        {                                                          //~va44I~
      		if (Dump.Y) Dump.println("DeviceListFragment.onPeersAvailable peers.size!=0 thisStatus="+thisStatus);//~vavdI~
	        if (thisStatus!=WifiP2pDevice.CONNECTED)	//THIS_DEVICE_CHANGED was not received//~va44R~
            	AG.aWDA.aWDActivity.requestThisInfo();             //~va44I~
        }                                                          //~va44I~
        if (peers.size() != 0)	                                   //~1Aa5R~
        {                                                          //~1Aa5R~
            if (WiFiDirectActivity.Stestdevicelist)                //~1Aa5I~
            {                                                      //~1Aa5I~
                WifiP2pDevice dup=peers.get(0);                    //~1Aa5R~
                peers.add(dup);                                    //~1Aa5R~
                peers.add(dup);                                    //~1Aa5R~
                peers.add(dup);                                    //~1Aa5R~
                peers.add(dup);                                    //~1Aa5R~
                peers.add(dup);                                    //~1Aa5R~
                peers.add(dup);                                    //~1Aa5I~
                peers.add(dup);                                    //~1Aa5I~
                peers.add(dup);                                    //~1Aa5I~
                peers.add(dup);                                    //~1Aa5I~
                peers.add(dup);                                    //~1Aa5I~
            }                                                      //~1Aa5I~
        }                                                          //~1Aa5R~
        else                                                       //~1Aa5R~
        {                                                          //~1Aa5R~
            if (WiFiDirectActivity.Stestdevicelist_mdpi)           //~1Aa5I~
            {                                                      //~1Aa5I~
        		WifiP2pDevice dup=new WifiP2pDevice();             //~1Aa5R~
            	peers.add(dup);                                    //~1Aa5R~
            	peers.add(dup);                                    //~1Aa5R~
            	peers.add(dup);                                    //~1Aa5R~
            	peers.add(dup);                                    //~1Aa5R~
            	peers.add(dup);                                    //~1Aa5R~
            	peers.add(dup);                                    //~1Aa5I~
            	peers.add(dup);                                    //~1Aa5I~
            	peers.add(dup);                                    //~1Aa5I~
            	peers.add(dup);                                    //~1Aa5I~
            	peers.add(dup);                                    //~1Aa5I~
            }                                                      //~1Aa5I~
        }                                                          //~1Aa5R~
//      ((WiFiPeerListAdapter) getListAdapter()).notifyDataSetChanged();//~1Aa5R~
//  	drawListView();                                            //~1Aa5I~//~vavgR~
//      WDA.SWDA.peerUpdated();	//issue connect                    //~1A6aI~//~1A6tR~
//      WDA.SWDA.peerUpdated(peers.size());	//issue connect        //~1A6tI~//~1Aa5R~
        AG.aWDA.peerUpdated(peers.size());	//updateButtonView
        if (peers.size() == 0) {
//          Log.d(WiFiDirectActivity.TAG, "No devices found");     //~1A65R~
            if (Dump.Y) Dump.println("DeviceListFragment.onPeersAvailable No devices found");          //~1A65I~//~1Aa5R~
//          tvEmptyMsg.setText(WDA.getResourceString(R.string.empty_message));//~1A65R~//~@@@@R~
            setStatus(false/*swErr*/,R.string.empty_message);     //~@@@@I~
//          WDA.getDeviceDetailFragment().getView().setVisibility(View.GONE);	//drop right half//~1A65R~//~1Aa5R~
			if (Dump.Y) Dump.println("DeviceListFragment.onPeersAvailable statusConnected="+statusConnected);//~@@@@R~
//            if (statusConnected==1)   //cause onFailure:Busy     //~@@@@R~
//            {                                                    //~@@@@R~
//                statusConnected=0;                               //~@@@@R~
////              WDA.getWDActivity().removeGroup();  //TODO test//~9A05I~//~@@@@R~
//                ((DeviceListFragment.DeviceActionListener)WDA.getWDActivity()).disconnect();//~@@@@R~
//            }                                                    //~@@@@R~
//          return;                                                //+vavgR~
        }
        else                                                       //~1A65I~
        {                                                          //~1A65I~
	        if (peers.size() == 1)                                 //~1Aa5I~
            {                                                      //~1Aa5I~
//  			onListItemClicked(0);                              //~1Aa5I~//~vavgR~
    			onListItemClicked2(0);                             //~vavgI~
                swDraw=true;                                       //~vavgI~
            }                                                      //~1Aa5I~
	    	setEmptyMsg();                                         //~1A65I~
        }                                                          //~1A65I~
	    if (swDraw)                                                //~vavgI~
    		drawListView();                                        //~vavgI~
      }                                                            //~1A65I~
      catch(Exception e)                                           //~1A65I~
      {                                                            //~1A65I~
      	Dump.println(e,"DeviceListFragment:onPeersAvailable");      //~1A65I~//~1Aa5R~
      }                                                            //~1A65I~

    }

//  public void clearPeers() {                                     //~@@@@R~
    public int clearPeers() {                                      //~@@@@I~
		if (Dump.Y) Dump.println("DeviceListFragment clearPeers peers ctr="+ peers.size());//~@@@@I~
		int ctr=peers.size();                                      //~@@@@I~
        peers.clear();
        if (swShowHistory)                                         //~vavcI~
        {                                                          //~vavcI~
    		createHistoryList();                                   //~vavcI~
        	mergeHistory(peers);                                   //~vavcI~
        }                                                          //~vavcI~
//      resetAllConnection();                                      //~0112I~//~vavgR~
//      ((WiFiPeerListAdapter) getListAdapter()).notifyDataSetChanged();//~1Aa5R~
      if (ctr!=0)                                                  //~vavgI~
    	drawListView();                                            //~1Aa5I~
//	    resetAllConnection();                                      //~0112I~//~vavgR~
        return ctr;                                                //~@@@@I~
    }
    //*****************************************************************//~0112I~
//    private void resetAllConnection()   //not Used                           //~0112R~//~vavgR~
//    {                                                              //~0112I~//~vavgR~
//        if (Dump.Y) Dump.println("DeviceListFragment.resetAllConnection");//~0112I~//~vavgR~
////        AG.aIPMulti.resetAllConnection();                        //~0112R~//~vavgR~
////        AG.aWDA.updateDialog();                                //~@@@@I~//~0112R~//~vavgR~
////      AG.aIPMulti.closeAllConnection();  //no path because unregister-receiver when WDA is dismised,and unpair request closeConnection before//~0112R~//~vavgR~
//    }                                                              //~0112I~//~vavgR~
    //*****************************************************************//~@@@@I~
    //*moved to WifiDirectActivity                                 //~@@@@I~
    //*****************************************************************//~@@@@I~
//    public void onInitiateDiscovery() {                          //~@@@@R~
////      if (progressDialog != null && progressDialog.isShowing()) {//~1A6eR~//~@@@@R~
////          progressDialog.dismiss();                              //~1A6eR~//~@@@@R~
////      }                                                          //~1A6eR~//~@@@@R~
//        dismissProgressDialog();                                   //~1A6eI~//~@@@@R~
////      progressDialog = ProgressDialog.show(getActivity(), "Press back to cancel", "finding peers", true,//~1A65R~//~@@@@R~
////      progressDialog = ProgressDialog.show(getActivity(),WDA.getResourceString(R.string.ProgressDialogTitle),WDA.getResourceString(R.string.ProgressDialogMsgFindingPeer), true,//~1A65I~//~1A6eR~//~@@@@R~
////              true, new DialogInterface.OnCancelListener() {     //~1A6eR~//~@@@@R~
////                                                                 //~1A6eR~//~@@@@R~
////                  @Override                                      //~1A6eR~//~@@@@R~
////                  public void onCancel(DialogInterface dialog) { //~1A6eR~//~@@@@R~
////                      if (Dump.Y) Dump.println("onInitiateDiscovery ProgressDialog canceled");//~1A65R~//~1A6eR~//~@@@@R~
////                  }                                              //~1A6eR~//~@@@@R~
////              });                                                //~1A6eR~//~@@@@R~

//        progressDialog=progressDialogShow(R.string.ProgressDialogTitle,//~1A6eI~//~@@@@R~
//                                            WDA.getResourceString(R.string.ProgressDialogMsgFindingPeer),//~1A6eI~//~@@@@R~
//                                            true,true);//onCancel ignored//~1A6eI~//~@@@@R~
//    }                                                            //~@@@@R~
    /**
     * An interface-callback for the activity to listen to fragment interaction
     * events.
     */
    public interface DeviceActionListener {

        void showDetails(WifiP2pDevice device);

        void cancelDisconnect();

        void connect(WifiP2pConfig config);

        void disconnect();
    }
//**********************************************************************//~1A65I~
//*itemclicklistener                                               //~1A65I~
//**********************************************************************//~1A65I~
    class ListItemClickListener implements OnItemClickListener     //~1A65I~
    {                                                              //~1A65I~
    	DeviceListFragment parent;                                 //~1A65I~
    	public ListItemClickListener(DeviceListFragment Pparent)//~1A65I~
        {                                                          //~1A65I~
        	parent=Pparent;                                        //~1A65I~
        }                                                          //~1A65I~
    	@Override                                                  //~1A65I~
        public void onItemClick(AdapterView<?> Plistview,View Ptextview,int Ppos,long Pid)//~1A65I~
        {                                                          //~1A65I~
        	try                                                    //~1A65I~
            {                                                      //~1A65I~
		    	parent.onListItemClick(null,Ptextview,Ppos,Pid);   //~1A65R~
            }                                                      //~1A65I~
            catch(Exception e)                                     //~1A65I~
            {                                                      //~1A65I~
            	Dump.println(e,"DeviceListFragment:onItemClick");  //~1A65I~
            }                                                      //~1A65I~
        }                                                          //~1A65I~
    }//subclass                                                    //~1A65R~
	//*******************************************************************************************************//~1A65I~
	//*from updateThis                                             //~1A65I~
	//*******************************************************************************************************//~1A65I~
    private boolean updateThisOwner(WifiP2pDevice device)           //~1A65R~//~va44R~
    {                                                              //~1A65I~
    	boolean owner=updateOwner(device,tvMyOwner);
    	thisOwner=owner?1:0;//~1A65R~
	    setEmptyMsg();                                            //~1A65R~
        if (Dump.Y) Dump.println("DeviceListFragment:updateThisOwner="+thisOwner);//~1A65I~
//  	AG.aWDA.enableCBWantGroupOwner(false,owner); 	//paired at scan//~@@@@R~
        return owner;                                          //~1A65I~
    }                                                              //~1A65I~
	//*******************************************************************************************************//~1A65I~
	//*from updateThis                                             //~1A65I~
	//*******************************************************************************************************//~1A65I~
    public boolean updateOwner(WifiP2pDevice device,TextView Pview)//~1A65R~
    {                                                              //~1A65I~
    	boolean rc=false;                                          //~1A65I~
        if (Dump.Y) Dump.println("DeviceListFragment.updateOwner name="+device.deviceName+",device.status="+device.status+",isGroupOwner="+device.isGroupOwner());//~1A6pI~//~1A8pI~//~@@@@R~
        if (Dump.Y) Dump.println("DeviceListFragment.updateOwner device="+device.toString());//~@@@@I~
        if (device.status!=WifiP2pDevice.CONNECTED)                //~1A65I~
	        Pview.setText("");                                     //~1A65I~
        else                                                       //~1A65I~
        {                                                          //~@@@@I~
        //  rc=updateOwner(device.isGroupOwner(),Pview);           //~1A65R~//~@@@@R~
			String nameOwner=WDA.getDeviceDetailFragment().nameGroupInfoOwner;//~@@@@I~
            boolean swOwner=nameOwner!=null && nameOwner.equals(device.deviceName);//~@@@@I~
        	if (Dump.Y) Dump.println("DeviceListFragment.updateOwner nameOwner="+Utils.toString(nameOwner)+",swOwner="+swOwner);//~@@@@I~
        	rc=updateOwner(swOwner,Pview);                         //~@@@@I~
        }                                                          //~@@@@I~
        return rc;                                                 //~1A65I~
    }                                                              //~1A65I~
	//*******************************************************************************************************//~1A65I~
	//*from DetailFragment                                         //~1A65I~
	//*******************************************************************************************************//~1A65I~
    public boolean updateThisOwner(boolean Powner)                 //~1A65R~
    {                                                              //~1A65I~
	    updateOwner(Powner,tvMyOwner);                             //~1A65R~
    	thisOwner=Powner?1:0;                                      //~1A65I~
	    setEmptyMsg();                                             //~1A65I~
        if (Dump.Y) Dump.println("DeviceListFragment:updateThisOwner="+Powner);//~1A65I~
        return Powner;                                             //~1A65I~
    }                                                              //~1A65I~
	//*******************************************************************************************************//~1A65I~
    public boolean updateOwner(boolean Powner,TextView Pview)      //~1A65R~
    {                                                              //~1A65I~
        Pview.setText(Powner ? WDA.getResourceString(R.string.MyOwnerYes) : WDA.getResourceString(R.string.MyOwnerNo));//~1A65I~
        if (Dump.Y) Dump.println("updateOwner owner="+Powner); //~1A65I~
        return Powner;                                             //~1A65I~
    }                                                              //~1A65I~
	//*******************************************************************************************************//~1A65I~
    public void setEmptyMsg()                                      //~1A65I~
    {                                                              //~1A65I~
        if (Dump.Y) Dump.println("DeviceListFragment:setEmptyMsg thisStatus="+thisStatus+",thisOwner="+thisOwner);//~va40I~
        int msgid=R.string.empty;                                  //~1A65I~
        if (thisStatus==WifiP2pDevice.CONNECTED)                   //~1A65I~
        {                                                          //~1A65I~
        	if (thisOwner==0)                                      //~1A65I~
            	msgid=R.string.performIPConnect;                   //~1A65I~
            else                                                   //~1A65I~
            	msgid=R.string.performIPAccept;                    //~1A65I~
        }                                                          //~1A65I~
        else                                                       //~1A65I~
        {                                                          //~1Aa5I~
            thisOwner=-1;                                          //~1Aa5I~
        	if (peers.size()!=0)                                   //~1A65I~
	        	msgid=R.string.deviceFound;                        //~1A65I~
        }                                                          //~1Aa5I~
//      tvEmptyMsg.setText(WDA.getResourceString(msgid));          //~1A65I~//~@@@@R~
        setStatus(false/*swerr*/,msgid);                           //~@@@@I~
//      if (Dump.Y) Dump.println("DeviceListFragment:setEmptyMsg="+tvEmptyMsg.getText().toString());//~1A65I~//~@@@@R~
    }                                                              //~1A65I~
	//*******************************************************************************************************//~1A6aI~
	//*return status,-1:not found,1:connected,0:do connect         //~1A6aR~
	//*******************************************************************************************************//~1A6aI~
    public int chkDiscovered(String Pmacaddr)                      //~1A6aI~
    {                                                              //~1A6aI~
    	int rc=-1;  //not found                                    //~1A6aR~
    //*****************************                                //~1A6aI~
        int ctr=peers.size();                                      //~1A6aR~
        for (int ii=0;ii<ctr;ii++)                                  //~1A6aI~
        {                                                          //~1A6aI~
        	WifiP2pDevice dev=peers.get(ii);                       //~1A6aI~
	        if (Dump.Y) Dump.println("DeviceListFragment:chkDiscovered perr device="+dev.deviceAddress);//~1A6aI~
            if (Pmacaddr.equals(dev.deviceAddress))                //~1A6aI~
            {                                                      //~1A6aI~
                int status=dev.status;                             //~1A6aI~
            	rc=0;	//issue connect                            //~1A6aI~
                switch(status)                                     //~1A6aI~
                {                                                  //~1A6aI~
            	case WifiP2pDevice.CONNECTED:    //0               //~1A6aI~
					rc=1; 	//alread paired                        //~1A6aM~
					break;                                         //~1A6aM~
            	case WifiP2pDevice.INVITED:      //1               //~1A6aI~
					break;                                         //~1A6aM~
                case WifiP2pDevice.FAILED:       //2               //~1A6aI~
					break;                                         //~1A6aM~
            	case WifiP2pDevice.AVAILABLE:    //3               //~1A6aR~
					break;                                         //~1A6aI~
            	case WifiP2pDevice.UNAVAILABLE:  //4               //~1A6aR~
					break;                                         //~1A6aI~
                default:                                           //~1A6aI~
                }                                                  //~1A6aI~
            }                                                      //~1A6aI~
        }   	                                                   //~1A6aI~
        if (Dump.Y) Dump.println("DeviceListFragment:chkDiscovered for "+Pmacaddr+",rc="+rc);//~1A6aI~
        return rc;                                                 //~1A6aR~
    }                                                              //~1A6aI~
	//*******************************************************************************************************//~1A65R~
    private WiFiPeerListAdapter getListAdapter()                   //~1A65I~
    {                                                              //~1A65I~
        return adapter;                                            //~1A65I~
    }                                                              //~1A65I~
	//*******************************************************************************************************//~1A65R~
//    private View getView()                                       //~1A65R~
//    {                                                            //~1A65R~
//        return layoutView.findViewById(R.id.device_list_wd);//AxeDialog:layoutView//~1A65R~
//    }                                                            //~1A65R~
	//*******************************************************************************************************//~1A65I~
	private Activity getActivity()                                 //~1A65I~
    {                                                              //~1A65I~
        return AG.activity;                                        //~1A65I~
    }                                                              //~1A65I~
//    //*******************************************************************************************************//~1A65R~
//    private Resources getResources()                             //~1A65R~
//    {                                                            //~1A65R~
//        return AG.resource;                                      //~1A65R~
//    }                                                            //~1A65R~
//    //*******************************************************************************************************//~1A67I~//~1A6eI~//~@@@@R~
//    private void dismissProgressDialog()                           //~1A67I~//~1A6eI~//~@@@@R~
//    {                                                              //~1A67I~//~1A6eI~//~@@@@R~
//        URunnable.dismissDialog(progressDialog);                       //~1A67I~//~1A6eI~//~@@@@R~
//    }                                                              //~1A67I~//~1A6eI~//~@@@@R~
//****************************************                         //~1A6eI~
//*No User                                                         //~va40I~
//****************************************                         //~va40I~
//  private ProgressDialog progressDialogShow(int Ptitleid,String Pmsg,boolean Pindeterminate,boolean Pcancelable)//~1A6eI~//~1A6tR~
//    private URunnable.URunnableData progressDialogShow(int Ptitleid, String Pmsg, boolean Pindeterminate, boolean Pcancelable)//~va40R~
//    {                                                            //~va40R~
//        if (Dump.Y) Dump.println("DeviceListFragment:progressDialogShow");//~1A6eI~//~va40R~
//        return URunnable.simpleProgressDialogShow(Ptitleid,Pmsg,Pindeterminate,Pcancelable);//~va40R~
//    }                                                            //~va40R~
//****************************************                         //~1Aa5I~
    public void setStatus(boolean PswErr,int Pmsgid)               //~1Aa5R~
    {                                                              //~1Aa5I~
        if (Dump.Y) Dump.println("DeviceListFragment:setState");   //~@@@@I~
		WDA.getDeviceDetailFragment().setStatus(PswErr,Pmsgid);    //~1Aa5R~
    }                                                              //~1Aa5I~
//****************************************                         //~@@@@I~
    public void setConnected(boolean PswConnected)                 //~@@@@I~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("DeviceListFragment:setConnected PswConnected="+PswConnected+",statusConnected="+statusConnected);//~@@@@R~
		if (PswConnected)                                          //~@@@@I~
        	statusConnected=1;        //Connected                  //~@@@@R~
        else           //from removeGroup                          //~@@@@R~
            statusConnected=0;                                     //~@@@@I~
        if (Dump.Y) Dump.println("DeviceListFragment:setConnected after statusConnected="+statusConnected);//~@@@@R~
    }                                                              //~@@@@I~
////****************************************                       //~@@@@R~
//    public void setDisconnected()                                //~@@@@R~
//    {                                                            //~@@@@R~
//        if (Dump.Y) Dump.println("DeviceListFragment:setDisconnected statusDisconnected="+statusDisconnected);//~@@@@R~
//        statusDisconnected=0;      //avoid dup removeGroup       //~@@@@R~
//    }                                                            //~@@@@R~
    //************************************************************ //~vav9I~
    private void setUserName(TextView PtvDeviceName)                         //~vav9I~
    {                                                              //~vav9I~
    	String dev=PtvDeviceName.getText().toString();             //~vav9I~
        if (Dump.Y) Dump.println("DeviceListFragment.setUserName dev="+dev);//~vav9I~
    	String userName=AG.aAccName.searchWD(dev);                 //~vav9R~
        if (userName!=null)                                        //~vav9I~
        	PtvDeviceName.setText(dev+ AccName.DEV_AND_USER+userName);                      //~vav9I~//~vavcR~
        if (Dump.Y) Dump.println("DeviceListFragment.setUserName setText="+PtvDeviceName.getText());//~vav9I~
    }                                                              //~vav9I~
    //************************************************************ //~vav9I~
    private void updateUserName(TextView PtvDeviceName,String PuserName)//~vav9I~
    {                                                              //~vav9I~
    	String dev=PtvDeviceName.getText().toString();             //~vav9I~
        if (Dump.Y) Dump.println("DeviceListFragment.updateUserName devname="+dev+",userName="+PuserName);//~vav9I~
    	AG.aAccName.updateWD(dev,PuserName);                       //~vav9R~
    }                                                              //~vav9I~
    //************************************************************ //~vavcI~
    private void createHistoryList()                               //~vavcI~
    {                                                              //~vavcI~
        if (Dump.Y) Dump.println("DeviceListFragment.createHistoryList");//~vavcI~
    	AG.aAccName.createHistoryListWD();                         //~vavcI~
    }                                                              //~vavcI~
    //************************************************************ //~vavcI~
    private void mergeHistory(List<WifiP2pDevice> Ppeers)     //~vavcI~
    {                                                              //~vavcI~
        if (Dump.Y) Dump.println("DeviceListFragment.mergeHistory old size="+Ppeers.size());//~vavcI~
        for (WifiP2pDevice dev:Ppeers)                             //~vavcI~
        {                                                          //~vavcI~
            AG.aAccName.pairedWD(dev.deviceName,dev.deviceAddress);   //~vavcR~
        }                                                          //~vavcI~
        String[] history=AG.aAccName.getEntrySetHistoryWD();       //~vavcI~
        if (history!=null)                                        //~vavcI~
        {                                                          //~vavcI~
        	for (int ii=0;ii<history.length;ii++)                  //~vavcI~
            {                                                      //~vavcI~
            	String dev=history[ii++];                          //~vavcI~
            	String user=history[ii++];                         //~vavcR~
            	String addr=history[ii];                           //~vavcI~
                if (addr==null)                                    //~vavcI~
                    continue;                                      //~vavcI~
    			WifiP2pDevice peer=new WifiP2pDevice();            //~vavcI~
                if (user!=null)                                    //~vavcI~
                	peer.deviceName=dev+AccName.DEV_AND_USER+user; //~vavcR~
                else                                               //~vavcI~
                	peer.deviceName=dev;                           //~vavcI~
                peer.deviceAddress=addr;                              //~vavcI~
                peer.status=WD_STATUS_HISTORY;    //-1             //~vavcR~
                Ppeers.add(peer);                                  //~vavcI~
        		if (Dump.Y) Dump.println("DeviceListFragment.mergeHistory add peer devname="+peer.deviceName+",addr="+peer.deviceAddress);//~vavcR~
            }                                                      //~vavcI~
        }                                                          //~vavcI~
        if (Dump.Y) Dump.println("DeviceListFragment.mergeHistory new size="+Ppeers.size());//~vavcI~
    }                                                              //~vavcI~
    //************************************************************ //~vavgI~
    private boolean isListUpdate(List<WifiP2pDevice> Ppeers,Collection<WifiP2pDevice> PnewList)//~vavgI~
    {                                                              //~vavgI~
        int ctrOld=Ppeers.size();                                  //~vavgI~
        int ctrNew=PnewList.size();                                //~vavgI~
        if (Dump.Y) Dump.println("DeviceListFragment.isListUpdate ctrOld="+ctrOld+",ctrNew="+ctrNew);//~vavgI~
        if (ctrOld!=ctrNew)                                        //~vavgI~
        {                                                          //~vavgI~
	        if (Dump.Y) Dump.println("DeviceListFragment.isListUpdate return true ctr changed");//~vavgI~
        	return true;                                           //~vavgI~
        }                                                          //~vavgI~
        if (ctrOld==0)	//and ctrNew=0                             //~vavgI~
        {                                                          //~vavgI~
	        if (Dump.Y) Dump.println("DeviceListFragment.isListUpdate return false ctr=0");//~vavgI~
        	return false;                                          //~vavgI~
        }                                                          //~vavgI~
        Object[] arrayDev=PnewList.toArray();                      //~vavgR~
        for (WifiP2pDevice devOld:Ppeers)                          //~vavgI~
        {                                                          //~vavgI~
        	boolean swFound=false;                                 //~vavgI~
        	for (Object obj:arrayDev)                              //~vavgR~
            {                                                      //~vavgI~
        		WifiP2pDevice devNew=(WifiP2pDevice)obj;           //~vavgI~
		        if (Dump.Y) Dump.println("DeviceListFragment.isListUpdate old="+devOld+",new="+devNew);//~vavgI~
                if (devOld.deviceName.equals(devNew.deviceName))   //~vavgI~
                {                                                  //~vavgI~
                	swFound=true;                                  //~vavgI~
	                if (devOld.status!=devNew.status)              //~vavgI~
                    {                                              //~vavgI~
				        if (Dump.Y) Dump.println("DeviceListFragment.isListUpdate return true by status changed");//~vavgI~
        				return true;                               //~vavgI~
                    }                                              //~vavgI~
                }                                                  //~vavgI~
            }                                                      //~vavgI~
            if (!swFound)                                          //~vavgI~
            {                                                      //~vavgI~
                if (Dump.Y) Dump.println("DeviceListFragment.isListUpdate return true by deviceName unmatch");//~vavgI~
                return true;                                       //~vavgI~
            }                                                      //~vavgI~
        }                                                          //~vavgI~
		if (Dump.Y) Dump.println("DeviceListFragment.isListUpdate return false devname and status is all same");//~vavgI~
        return false;                                              //~vavgI~
    }                                                              //~vavgI~
}
