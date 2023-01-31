//*CID://+vat2R~:                             update#=   81;       //~vat2R~
//*************************************************************************//~1A65I~
//2022/10/16 vat2 deprecated api33; getPercelableExtra;            //~vat2I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//*************************************************************************//~va40I~
//* BroadcastReceiver active when WDA is not opened                //+vat2I~
//*************************************************************************//+vat2I~
package com.btmtest.wifi;                                               //~v@@@I~//~9719I~//~1Ac4I~

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//import android.net.NetworkInfo;                                  //~va40R~
//import android.net.wifi.p2p.WifiP2pGroup;
//import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.ChannelListener;        //~0113I~
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;       //~0113I~
import android.net.wifi.p2p.WifiP2pDevice;                         //~0113I~
import android.net.wifi.p2p.WifiP2pDeviceList;                     //~0113I~
//import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener; //~0115I~
//import android.net.wifi.p2p.WifiP2pManager.GroupInfoListener;      //~0115I~
//~0113I~
import android.content.IntentFilter;                               //~0113I~
import android.os.Looper;                                          //~0113I~
import androidx.core.app.ActivityCompat;
//~1A65I~
import com.btmtest.BT.Members;
import com.btmtest.R;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.net.wifi.p2p.WifiP2pDevice.*;
import static com.btmtest.StaticVars.AG;                           //~9721I~//~@@@@I~
import static com.btmtest.game.GConst.*;

//@TargetApi(AG.ICE_CREAM_SANDWICH)   //api14                           //~1A65R~//~@@@@R~
public class WDIReceiver extends BroadcastReceiver                 //~0113R~
        implements ChannelListener                                 //~0113I~
        , PeerListListener                                  //~0113I~
//                ,ConnectionInfoListener                          //~0115R~
//                ,GroupInfoListener                               //~0115R~
{                                                                  //~0113I~
    private BroadcastReceiver receiver;                            //~0113I~
    private final IntentFilter intentFilter = new IntentFilter();  //~0113I~
    private WifiP2pManager manager;                                //~0113I~
    private Channel channel;                                       //~0113I~
    private boolean isWifiP2pEnabled;                              //~0113I~
    private boolean retryChannel = false;                          //~0113I~
    private List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();//~0113I~
    private List<PeerData> peersOld;                               //~0113I~//~0115R~
    private List<PeerData> peersPaired;                            //~0115I~
    private List<WifiP2pDevice> peersAdded = new ArrayList<WifiP2pDevice>();//~0113I~
    private List<PeerData> peersDeleted = new ArrayList<PeerData>(); //~0113I~
    private List<WifiP2pDevice> peersUpdated = new ArrayList<WifiP2pDevice>();//~0113I~
    private boolean swBTCD;                                        //~0113I~

    //*******************************************************************//~0113M~
    class PeerData                                                 //~0113M~
    {                                                              //~0113M~
        public int status;                                         //~0113M~
        String deviceAddress;                                      //~0113R~
        String deviceName;                                         //~0113I~

        PeerData(String Pname, String Paddr, int Pstatus)            //~0113R~
        {                                                          //~0113M~
            status = Pstatus;
            deviceAddress = Paddr;
            deviceName = Pname; //~0113R~
            if (Dump.Y) Dump.println("WDIReceiver.PeerData constructor name=" + Pname + ",addr=" + Paddr + ",status=" + Pstatus);//~va40R~
        }                                                          //~0113M~
    }                                                              //~0113M~

    //*******************************************************************//~0113R~
    public WDIReceiver()                                           //~0113I~
    {                                                              //~0113I~
        super();                                                   //~0113I~
        onCreate();                                                //~0113I~
    }                                                              //~0113I~

    //*******************************************************************//~0113I~
    private void onCreate()                                        //~0113I~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.onCreate");          //~0113I~
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);//~0113I~
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);//~0113I~
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);//~0113I~
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);//~0113I~
        manager = (WifiP2pManager) getContext().getSystemService(Context.WIFI_P2P_SERVICE);//~0113R~
//      channel= manager.initialize(getContext(),getMainLooper(),this/*ChannelListener*/);	//callback onChannelDisconnected()//~0113R~
    }                                                              //~0113I~

    //*******************************************************************//~0113I~
    private boolean isOpenWDA()                                    //~0113I~
    {                                                              //~0113I~
        boolean rc = AG.aWDA != null;                                  //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.isOpenWDA rc=" + rc);  //~0113I~
        return rc;                                                 //~0113I~
    }                                                              //~0113I~

    //*******************************************************************//~0113I~
    @Override
    public void onReceive(Context context, Intent intent)          //~0113R~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.onReceive");         //~0113I~
        try                                                          //~1A65I~//~0113R~
        {                                                            //~1A65I~//~0113R~
            onReceive(intent);                                     //~0113I~
        }                                                          //~0113I~
        catch (Exception e)                                         //~0113I~
        {                                                          //~0113I~
            Dump.println(e, "WDIReceiver.onReceive");               //~0113I~
        }                                                          //~0113I~
    }                                                              //~0113I~

    //*******************************************************************//~0113I~
    private void onReceive(Intent intent)                          //~0113I~
    {                                                              //~0113I~
        String action = intent.getAction();                        //~0113R~
        if (Dump.Y) Dump.println("WDIReceiver.onReceive action=" + action + ",intent=" + intent.toString());//~va40R~
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action))//~0113R~
        {                                                          //~0113I~
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);   //1:disabled, 2:enabled//~@@@@R~
            if (Dump.Y) Dump.println("WDIReceiver.onReceive:P2P_STATE_CHANGED_ACTION state=" + state);//~va40R~
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED)    //~0113R~
            {                                                      //~0113I~
                setIsWifiP2pEnabled(true);                         //~0113R~
//          	manager.requestPeers(channel,(PeerListListener)WDA.getDeviceListFragment());//~@@@@I~//~0113R~
                wifiEnabled();                                     //~0113I~
            }                                                      //~0113R~
            else                                                   //~0113I~
            {                                                      //~0113I~
                setIsWifiP2pEnabled(false);                        //~0113R~
//              activity.resetData();                              //~0113R~
                wifiDisabled();                                    //~0113I~
            }
        }                                                          //~0113R~
        else                                                       //~0113I~
            if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action))//~0113I~
            {                                                          //~0113I~
                if (Dump.Y) Dump.println("WDIReceiver.onReceive P2P_PEERS_CHANGED");//~va40R~
//          if (manager!= null)                                    //~0113R~
//  		{                                                      //~0113I~
//  			WDA.getWDActivity().dismissProgDlg(WiFiDirectActivity.PROGRESS_DISCOVER);	//current ProgDlg:Scan//~9A05I~//~0113R~
//              manager.requestPeers(channel,(PeerListListener)WDA.getDeviceListFragment());//~1A65R~//~0113R~
//          }                                                      //~0113R~
                peersChanged();                                        //~0113I~
            }                                                          //~0113R~
            else                                                       //~0113I~
                if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action))//~0113I~
                {                                                          //~0113I~
                    if (Dump.Y) Dump.println("WDIReceiver.onReceive P2P_CONNECTION_CHANGED manager=" + Utils.toString(manager));//~va40R~
                    if (manager == null)                                   //~0113R~
                    {                                                      //~0113I~
                        return;
                    }
                    boolean swPaired;                                        //~va40I~
//                    if (true)                                    //~va40R~
//                    {                                            //~va40R~
//                      WifiP2pInfo p2pinfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_INFO);//~va40I~//~vat2R~
                        WifiP2pInfo p2pinfo = getParcelableExtraWD(intent,WifiP2pManager.EXTRA_WIFI_P2P_INFO);//~vat2R~
                        swPaired = p2pinfo.groupFormed;                          //~va40I~
                        if (Dump.Y) Dump.println("WDIReceiver.onReceive swPaired=" + swPaired + ",WifiP2pInfo=" + Utils.toString(p2pinfo));//~va40R~
//                    }                                            //~va40R~
//                    else                                         //~va40R~
//                    {                                            //~va40R~
////          NetworkInfo networkInfo =intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);//~0113R~//~va40R~
//                        android.net.NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);//~va40R~
//                        swPaired = networkInfo.isConnected();    //~va40R~
//                        if (Dump.Y) Dump.println("WDIReceiver.onReceive swPaired=" + swPaired + ",networkInfo=" + Utils.toString(networkInfo));//~va40R~
//                    }                                            //~va40R~
//          if (networkInfo.isConnected())                         //~0113R~//~va40R~
                    if (swPaired)                                          //~va40I~
                    {                                                      //~0113I~
                        if (Dump.Y) Dump.println("WDIReceiver.onReceive connected");//~0113I~
//                WDA.getWDActivity().dismissProgDlg(WiFiDirectActivity.PROGRESS_CONNECT);    //current ProgDlg:DoPair//~9A05I~//~0113R~
//                DeviceDetailFragment fragment = WDA.getDeviceDetailFragment();//~1A65I~//~0113R~
//                if (Dump.Y) Dump.println("WDIReceiver.onReceive requestConnectionInfo");//~1A65I~//~0113R~
//                manager.requestConnectionInfo(channel, fragment);//~0113R~
//                manager.requestGroupInfo(channel,fragment/*GroupInfoListener*/);//~1A65I~//~0113R~
//                AG.aWDA.enableCBWantGroupOwner(false);  //connected//~9A03R~//~0113R~
//                WDA.getDeviceListFragment().setConnected(true);    //~9A05I~//~0113R~
                        pairConnected();                                   //~0113I~
                    }                                                      //~0113R~
                    else                                                   //~0113I~
                    {                                                      //~0113I~
                        if (Dump.Y) Dump.println("WDIReceiver.onReceive Not connected");//~0113I~
//                int clearCtr=activity.resetData();                 //~9A05I~//~0113R~
//                AG.aWDA.enableCBWantGroupOwner(true);   //disconnected//~9A03R~//~0113R~
                        pairDisconnected();                                //~0113I~
                    }
                }                                                          //~0113R~
                else                                                       //~0113I~
                    if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action))//~0113I~
                    {                                                          //~0113I~
                        if (Dump.Y) Dump.println("WDIReceiver.onReceive P2P_THIS_DEVICE_CHANGED");//~va40R~
//            DeviceListFragment fragment = WDA.getDeviceListFragment();//~1A65R~//~0113R~
//            fragment.updateThisDevice((WifiP2pDevice) intent.getParcelableExtra(//~0113R~
//                    WifiP2pManager.EXTRA_WIFI_P2P_DEVICE));      //~0113R~
                        thisDeviceChanged();                                   //~0113I~
                    }
        if (Dump.Y) Dump.println("WDIReceiver.onReceive exit action=" + action);//~vat2I~
    }

    //**********************************************************************//~0113I~
    @Override   //ChannelListener                                  //~0113I~
    public void onChannelDisconnected()                            //~0113I~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.onChannelDisconnected");//~0115I~
        if (isOpenWDA())    //dialog open                          //~0113R~
            return;                                                //~0113I~
        try                                                        //~0113I~
        {                                                          //~0113I~
            //*we will try once more                               //~0113I~
            if (manager != null && !retryChannel)                  //~0113I~
            {                                                      //~0113I~
//          	Toast.makeText(getContext(),WDA.getResourceString(R.string.ChannelLostRetry), Toast.LENGTH_LONG).show();//~0113I~
                UView.showToastLong(R.string.ChannelLostRetry);    //~0113I~
//          	resetData();                                       //~0113I~
                retryChannel = true;                               //~0113I~
                channel = manager.initialize(getContext(), getMainLooper(), this);//~0113R~
		        if (Dump.Y) Dump.println("WDIReceiver.onChannelDisconnected channel="+channel);//~vat2I~
            }                                                      //~0113I~
            else                                                   //~0113I~
            {                                                      //~0113I~
//          	Toast.makeText(getContext(),WDA.getResourceString(R.string.ChannelLostEnableP2P),Toast.LENGTH_LONG).show();//~0113I~
                UView.showToastLong(R.string.ChannelLostEnableP2P);//~0113I~
            }                                                      //~0113I~
        }                                                          //~0113I~
        catch (Exception e)                                         //~0113I~
        {                                                          //~0113I~
            Dump.println(e, "WDIReceiver.onChannelDisconnected");   //~0113I~
        }                                                          //~0113I~
    }                                                              //~0113I~

    //***************************************************************************//~0113I~
    @Override //*PeerListListener                                  //~0113I~
    public void onPeersAvailable(WifiP2pDeviceList peerList)       //~0113I~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.onPeersAvailable");  //~0113I~
        try                                                        //~0113I~
        {                                                          //~0113I~
//  		getPeersAvailable(peerList);                            //~0113I~//~0115R~
            chkUnpaired(peerList);                                 //~0115I~
        }                                                          //~0113I~
        catch (Exception e)                                         //~0113I~
        {                                                          //~0113I~
            Dump.println(e, "WDIReceiver.onPeersAvailable");        //~0113I~
        }                                                          //~0113I~
    }                                                              //~0113I~

    //    //***************************************************************************//~0113I~//~0115R~
//    private void getPeersAvailable(WifiP2pDeviceList peerList)     //~0113I~//~0115R~
//    {                                                              //~0113I~//~0115R~
//        if (Dump.Y) Dump.println("WDIReceiver.getPeersAvailable"); //~0113I~//~0115R~
//        Collection<WifiP2pDevice> devices=peerList.getDeviceList();//~0113I~//~0115R~
//        if (Dump.Y) Dump.println("WDIReceiver.getPeersAvailable devicelist count="+devices.size());//~0113I~//~0115R~
////      selectedPos=-1;                                            //~0113I~//~0115R~
//        peers.clear();                                             //~0113I~//~0115R~
//        peers.addAll(devices);                                     //~0113I~//~0115R~
//        if (!isOpenWDA()) //dialog not open                        //~0113R~//~0115R~
//        {                                                          //~0113I~//~0115R~
//            if (Dump.Y) Dump.println("WDIReceiver.getPeersAvailable peerSold="+Utils.toString(peersOld));//~0115R~
//            if (peersOld!=null)                                   //~0113I~//~0115R~
//            {                                                      //~0113I~//~0115R~
//                listPeers();                                       //~0113R~//~0115R~
//                closeDeletedSocket();                              //~0113I~//~0115R~
//            }                                                      //~0113I~//~0115R~
//            savePeers();                                           //~0113R~//~0115R~
//        }                                                          //~0113I~//~0115R~
//    }                                                              //~0113I~//~0115R~
    //***************************************************************************//~0115I~
    private void chkUnpaired(WifiP2pDeviceList peerList)           //~0115I~
    {                                                              //~0115I~
        if (Dump.Y) Dump.println("WDIReceiver.chkUnpaired");       //~0115I~
        Collection<WifiP2pDevice> devices = peerList.getDeviceList();//~0115I~
        if (Dump.Y) Dump.println("WDIReceiver.chkUnpaired devicelist count=" + devices.size());//~va40R~
        peers.clear();                                             //~0115I~
        peers.addAll(devices);                                     //~0115I~
        if (!isOpenWDA()) //dialog not open                        //~0115I~
        {                                                          //~0115I~
            getPaired();                                           //~0115I~
            closeUnpaired();                                       //~0115I~
        }                                                          //~0115I~
    }                                                              //~0115I~

    //    //***************************************************************************//~0113I~//~0115R~
//    private void closeDeletedSocket()                              //~0113I~//~0115R~
//    {                                                              //~0113I~//~0115R~
//        int ctr=peersDeleted.size();                                   //~0113I~//~0115R~
//        if (Dump.Y) Dump.println("WDIReceiver.closeDeletedSocket ctr="+ctr);//~0113I~//~0115R~
//        if (AG.aBTMulti==null)                                     //~0113I~//~0115R~
//            return;                                                //~0113I~//~0115R~
//        if (AG.aBTMulti.BTGroup==null)                             //~0113I~//~0115R~
//            return;                                                //~0113I~//~0115R~
//        for (int ii=0;ii<ctr;ii++)                                 //~0113I~//~0115R~
//        {                                                          //~0113I~//~0115R~
//            PeerData pd=peersDeleted.get(ii);                      //~0113I~//~0115R~
//            String devName=pd.deviceName;                          //~0113I~//~0115R~
//            if (Dump.Y) Dump.println("WDIReceiver.closeDeletedSocket name="+devName);//~0113I~//~0115R~
//            closeMemberSocket(devName);                           //~0113I~//~0115R~
//        }                                                          //~0113I~//~0115R~
//    }                                                              //~0113I~//~0115R~
    //***************************************************************************//~0115I~
    private void closeUnpaired()                                   //~0115I~
    {                                                              //~0115I~
        int ctrPair = peersPaired.size();                            //~0115I~
        if (Dump.Y) Dump.println("WDIReceiver.closeUnpaired ctr=" + ctrPair);//~0115I~
        if (AG.aBTMulti == null)                                     //~0115I~
            return;                                                //~0115I~
        Members m = AG.aBTMulti.BTGroup;                             //~0115I~
        if (m == null)                                               //~0115I~
            return;                                                //~0115I~
        int ctrMember = PLAYERS;                                    //~0115I~
        for (int ii = 0; ii < ctrMember; ii++)                           //~0115I~
        {                                                          //~0115I~
            if (Dump.Y) Dump.println("WDIReceiver.closeUnpaired MD[" + ii + "]=" + m.MD[ii].toString());//~va40R~
            IPIOThread t = (IPIOThread) (m.getThread(ii));            //~0115I~
            if (t == null)                                           //~0115I~
                continue;                                          //~0115I~
            String membName = m.getName(ii);                         //~0115I~
            boolean swFound = false;                                 //~0115I~
            for (int jj = 0; jj < ctrPair; jj++)                         //~0115I~
            {                                                      //~0115I~
                PeerData pd = peersPaired.get(jj);                     //~0115I~
                if (pd.status != CONNECTED)                          //~0115I~
                    continue;                                      //~0115I~
                String devName = pd.deviceName;                      //~0115I~
                if (devName.equals(membName))                      //~0115I~
                {                                                  //~0115I~
                    swFound = true;                                  //~0115I~
                    break;                                         //~0115I~
                }                                                  //~0115I~
            }                                                      //~0115I~
            if (!swFound)                                          //~0115I~
                closeSocket(membName, t);                           //~0115I~
        }                                                          //~0115I~
    }                                                              //~0115I~

    //    //***************************************************************************//~0113I~//~0115R~
//    private void closeMemberSocket(String PdevName)                //~0113I~//~0115R~
//    {                                                              //~0113I~//~0115R~
//        if (Dump.Y) Dump.println("WDIReceiver.closeMemberSocket name="+PdevName);//~0113I~//~0115R~
//        Members m=AG.aBTMulti.BTGroup;                             //~0113I~//~0115R~
//        int idx=m.search(PdevName);                         //~0113I~//~0115R~
//        if (idx<0)                                                 //~0113I~//~0115R~
//            return;                                                //~0113I~//~0115R~
//        if (Dump.Y) Dump.println("WDIReceiver.closeMemberSocket MD["+idx+"]="+m.MD[idx].toString());//~0113I~//~0115R~
//        IPIOThread t=(IPIOThread)(m.getThread(idx));               //~0113I~//~0115R~
//        if (t==null)                                               //~0113I~//~0115R~
//            return;                                                //~0113I~//~0115R~
////      t.doCose(); //TODO TEST                                    //~0113I~//~0115R~
//        t.closeSocket();                                           //~0113I~//~0115R~
//    }                                                              //~0113I~//~0115R~
    //***************************************************************************//~0115I~
    private void closeSocket(String Pname, IPIOThread Pthread)      //~0115I~
    {                                                              //~0115I~
        if (Dump.Y) Dump.println("WDIReceiver.closeSocket name=" + Pname);//~0115I~
//      Pthread.doCose(); //TODO TEST                              //~0115I~
        Pthread.closeSocket();                                     //~0115I~
    }                                                              //~0115I~

    //    //***************************************************************************//~0113I~//~0115R~
//    private void savePeers()                                       //~0113I~//~0115R~
//    {                                                              //~0113I~//~0115R~
//        if (peersOld==null)                                        //~0113I~//~0115R~
//        {                                                        //~0115R~
//            if (Dump.Y) Dump.println("WDIReceiver.savePeers peersOld created");//~0115R~
//            peersOld=new ArrayList<PeerData>();                    //~0113I~//~0115R~
//        }                                                        //~0115R~
//        else                                                       //~0113I~//~0115R~
//            peersOld.clear();                                      //~0113R~//~0115R~
//        int ctr=peers.size();                                      //~0113I~//~0115R~
//        for (int ii=0;ii<ctr;ii++)                                 //~0113I~//~0115R~
//        {                                                          //~0113I~//~0115R~
//            WifiP2pDevice dev=peers.get(ii);                                     //~0113I~//~0115R~
//            peersOld.add(new PeerData(dev.deviceName,dev.deviceAddress,dev.status));//~0113R~//~0115R~
//        }                                                          //~0113I~//~0115R~
//        if (Dump.Y) Dump.println("WDIReceiver.savePeers savedctr="+ctr);//~0113R~//~0115R~
//    }                                                              //~0113I~//~0115R~
    //***************************************************************************//~0115I~
    private void getPaired()                                       //~0115I~
    {                                                              //~0115I~
        if (peersPaired == null)                                     //~0115I~
        {                                                          //~0115I~
            if (Dump.Y) Dump.println("WDIReceiver.getPaired peersPaired created");//~0115I~
            peersPaired = new ArrayList<PeerData>();                 //~0115I~
        }                                                          //~0115I~
        else                                                       //~0115I~
            peersPaired.clear();                                    //~0115I~
        int ctr = peers.size();                                      //~0115I~
        for (int ii = 0; ii < ctr; ii++)                                 //~0115I~
        {                                                          //~0115I~
            WifiP2pDevice dev = peers.get(ii);                       //~0115I~
            peersPaired.add(new PeerData(dev.deviceName, dev.deviceAddress, dev.status));//~0115I~
        }                                                          //~0115I~
        if (Dump.Y) Dump.println("WDIReceiver.getPaired ctr=" + ctr);//~0115I~
    }                                                              //~0115I~

    //***************************************************************************//~0113I~
    private void listPeers()                                       //~0113R~
    {                                                              //~0113I~
        boolean swFound;                                           //~0113I~
        WifiP2pDevice dev;                                         //~0113R~
        PeerData devOld;                                           //~0113I~
        String nameOld, nameNew;                                    //~0113R~
        //*********************                                        //~0113I~
        int ctrChanged = 0;                                          //~0113I~
        int ctr = peers.size();                                      //~0113I~
        int ctrOld = peersOld.size();                                //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.listPeers ctr=" + ctr + ",old=" + ctrOld);//~0113I~
        peersAdded.clear();                                        //~0113R~
        peersDeleted.clear();                                      //~0113R~
        peersUpdated.clear();                                      //~0113R~
//*select deleted                                                  //~0113I~
        for (int ii = 0; ii < ctrOld; ii++)                              //~0113I~
        {                                                          //~0113I~
            devOld = peersOld.get(ii);                               //~0113I~
            if (Dump.Y) Dump.println("WDIReceiver.listPeers device Old=" + devOld.deviceName);//~va40R~
            nameOld = devOld.deviceName;                             //~0113R~
            swFound = false;                                         //~0113I~
            for (int jj = 0; jj < ctr; jj++)                             //~0113R~
            {                                                      //~0113I~
                dev = peers.get(jj);                                 //~0113R~
                nameNew = dev.deviceName;                            //~0113R~
                if (Dump.Y) Dump.println("WDIReceiver.listPeers device New jj=" + jj + "=" + dev.toString());//~va40R~
                if (nameNew.equals(nameOld))                       //~0113R~
                {                                                  //~0113I~
                    swFound = true;                                  //~0113I~
                    break;                                         //~0113I~
                }                                                  //~0113I~
            }                                                      //~0113I~
            if (!swFound)                                          //~0113I~
                peersDeleted.add(devOld);                          //~0113I~
        }                                                          //~0113I~
//*select added                                                    //~0113I~
        for (int ii = 0; ii < ctr; ii++)                                 //~0113I~
        {                                                          //~0113I~
            dev = peers.get(ii);                                     //~0113I~
            if (Dump.Y) Dump.println("WDIReceiver.listPeers device New=" + dev.deviceName);//~0113R~
            nameNew = dev.deviceName;                                //~0113R~
            swFound = false;                                         //~0113I~
            for (int jj = 0; jj < ctrOld; jj++)                          //~0113R~
            {                                                      //~0113I~
                devOld = peersOld.get(jj);                           //~0113R~
                nameOld = devOld.deviceName;                         //~0113R~
                if (Dump.Y) Dump.println("WDIReceiver.listPeers device Old=" + dev.deviceName);//~va40R~
                if (nameOld.equals(nameNew))                       //~0113R~
                {                                                  //~0113I~
                    if (dev.status != devOld.status)                 //~0113I~
                        peersUpdated.add(dev);                     //~0113I~
                    swFound = true;                                  //~0113I~
                    break;                                         //~0113I~
                }                                                  //~0113I~
            }                                                      //~0113I~
            if (!swFound)                                          //~0113I~
                peersAdded.add(dev);                               //~0113I~
        }                                                          //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.listPeers Summary ctrDeleted=" + peersDeleted.size() + ",ctrAdded=" + peersAdded.size() + ",updated=" + peersUpdated.size());//~va40R~
        StringBuilder sb = new StringBuilder();   //thread unsafe                         //~0113I~
//*list deleted                                                    //~0113I~
        ctr = peersDeleted.size();                                    //~0113I~
        ctrChanged += ctr;                                           //~0113I~
        if (ctr != 0)                                                //~0113I~
            sb.append(Utils.getStr(R.string.Info_WDPair_Deleted)); //~0113I~
        for (int ii = 0; ii < ctr; ii++)                                 //~0113I~
        {                                                          //~0113I~
            PeerData pd = peersDeleted.get(ii);                              //~0113I~
            if (Dump.Y) Dump.println("WDIReceiver.listPeers deviceDeleted=" + pd.deviceName);//~va40R~
            nameOld = pd.deviceName;                                 //~0113R~
            if (ii != 0)                                             //~0113I~
                sb.append(",");                                    //~0113I~
            sb.append(nameOld);                                    //~0113R~
        }                                                          //~0113I~
//*list added                                                      //~0113I~
        ctr = peersAdded.size();                                      //~0113I~
        if (ctrChanged != 0 && ctr != 0)                               //~0113I~
            sb.append("; ");                                       //~0113I~
        ctrChanged += ctr;                                           //~0113I~
        if (ctr != 0)                                                //~0113I~
            sb.append(Utils.getStr(R.string.Info_WDPair_Added));   //~0113I~
        for (int ii = 0; ii < ctr; ii++)                                 //~0113I~
        {                                                          //~0113I~
            dev = peersAdded.get(ii);                                //~0113I~
            if (Dump.Y) Dump.println("WDIReceiver.listPeers name=" + dev.deviceName);//~0113R~
            nameNew = dev.deviceName;                                //~0113R~
            if (ii != 0)                                             //~0113I~
                sb.append(",");                                    //~0113I~
            sb.append(nameNew);                                    //~0113R~
            sb.append("-");                                        //~0113I~
            sb.append(getDeviceStatus(dev.status));                //~0113I~
        }                                                          //~0113I~
//*list updated                                                    //~0113I~
        ctr = peersUpdated.size();                                    //~0113I~
        if (ctrChanged != 0 && ctr != 0)                               //~0113I~
            sb.append("; ");                                       //~0113I~
        ctrChanged += ctr;                                           //~0113I~
        if (ctr != 0)                                                //~0113I~
            sb.append(Utils.getStr(R.string.Info_WDPair_Updated)); //~0113I~
        for (int ii = 0; ii < ctr; ii++)                                 //~0113I~
        {                                                          //~0113I~
            dev = peersUpdated.get(ii);                              //~0113I~
            if (Dump.Y) Dump.println("WDIReceiver.listPeers deviceUpdated=" + dev.deviceName);//~va40R~
            nameNew = dev.deviceName;                                //~0113R~
            if (ii != 0)                                             //~0113I~
                sb.append(",");                                    //~0113I~
            sb.append(nameNew);                                    //~0113R~
            sb.append("-");                                        //~0113I~
            sb.append(getDeviceStatus(dev.status));                //~0113I~
        }                                                          //~0113I~
        if (ctrChanged != 0)                                            //~0113I~
        {                                                          //~0113I~
            String msg = sb.toString();                              //~0113I~
            if (Dump.Y) Dump.println("WDIReceiver.listPeers ctrChanged=" + ctrChanged + ",msg=" + msg);//~va40R~
            UView.showToastLong(msg);                              //~0113I~
        }                                                          //~0113I~
    }                                                              //~0113I~

    //***********************************************************  //~0113I~
    private String getDeviceStatus(int Pstatus)                    //~0113I~
    {                                                              //~0113I~
        return DeviceListFragment.getDeviceStatus(Pstatus);     //~0113I~
    }                                                              //~0113I~

    //***********************************************************  //~0113I~
    public void onResume()                                         //~0113I~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.onResume swBTCD=" + swBTCD);//~0113R~
        if (!isOpenWDA())    //dialog opened regster receiver of WDA or not//~0113R~
            registerReceiver();                                    //~0113R~
    }                                                              //~0113I~

    //***********************************************************  //~0113M~
    public void onPause()                                          //~0113M~
    {                                                              //~0113M~
        if (Dump.Y) Dump.println("WDIReceiver.onPause");           //~0113M~
        if (!isOpenWDA())    //dialog opened                        //~0113I~
            unregisterReceiver();                                  //~0113M~
    }                                                              //~0113M~

    //***********************************************************  //~0113I~
    //*BTCDialog/BTRDialog opened                                  //~0113I~
    //***********************************************************  //~0113I~
    public void shownBTCD()                                        //~0113I~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.shownBTCD");         //~0113I~
        swBTCD = true;                                               //~0113I~
        unregisterReceiver();                                      //~0113I~
    }                                                              //~0113I~

    //***********************************************************  //~0113I~
    //*from WDA after set AG.aWDA                                  //~0113I~
    //***********************************************************  //~0113I~
    public void shownWDA()                                         //~0113I~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.shownWDA");          //~0113R~
        swBTCD = false;                                              //~0113I~
        unregisterReceiver();                                      //~0113I~
    }                                                              //~0113I~

    //***********************************************************  //~0113I~
    //*from WDA after set AG.aWDA=null                             //~0113I~
    //***********************************************************  //~0113I~
    public void dismissedWDA()                                     //~0113I~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.dismissedWDA");      //~0113I~
        registerReceiver();                                        //~0113I~
    }                                                              //~0113I~

    //***********************************************************  //~0113I~
    public void registerReceiver()                                 //~0113R~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.registerReceiver swBTCD=" + swBTCD + ",receiver=" + Utils.toString(this));//~va40R~
        if (swBTCD)                                                //~0113I~
            return;                                                //~0113I~
        if (receiver == null)                                        //~0113I~
        {                                                          //~0113I~
            receiver = this;                                         //~0113I~
            getContext().registerReceiver(receiver, intentFilter);  //~0113R~
            channel = manager.initialize(getContext(), getMainLooper(), this/*ChannelListener*/);    //callback onChannelDisconnected()//~0113I~
        	if (Dump.Y) Dump.println("WDIReceiver.registerReceiver channel="+channel);//~vat2I~
            requestPeers(false/*swChanged*/);                      //~0113R~
        }                                                          //~0113I~
    }                                                              //~0113I~

    //***********************************************************  //~0113I~
    public void unregisterReceiver()                               //~0113R~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.unregisterReceiver receiver=" + Utils.toString(receiver));//~va40R~
        if (receiver == null)                                        //~0113I~
            return;                                                //~0113I~
        manager.initialize(getContext(), getMainLooper(), null/*ChannelListener*/);    //callback onChannelDisconnected()//~0113I~
        channel = null;                                              //~0113I~
        getContext().unregisterReceiver(receiver);                 //~0113I~
        receiver = null;                                             //~0113I~
    }                                                              //~0113I~

    //*****************************************************************//~0113I~
    private Context getContext()                                   //~0113I~
    {                                                              //~0113I~
        return AG.context;                                         //~0113I~
    }                                                              //~0113I~

    //*****************************************************************//~0113I~
    private Looper getMainLooper()                                 //~0113I~
    {                                                              //~0113I~
        return AG.context.getMainLooper();                         //~0113I~
    }                                                              //~0113I~

    //*****************************************************************//~0113I~
    private void setIsWifiP2pEnabled(boolean PisWifiP2pEnabled)     //~0113I~
    {                                                              //~0113I~
        isWifiP2pEnabled = PisWifiP2pEnabled;                         //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.setIsWifiP2pEnabled enable=" + isWifiP2pEnabled);//~va40R~
    }                                                              //~0113I~

    //*****************************************************************//~0113I~
    private void wifiEnabled()                                     //~0113I~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.wifiEnabled");       //~0113I~
        requestPeers(true/*wifienabled*/);                         //~0113R~
    }                                                              //~0113I~

    //*****************************************************************//~0113I~
    private void wifiDisabled()                                    //~0113I~
    {                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.wifiDisabled");      //~0113I~
        requestPeers(true/*wifienabled*/);                         //~0113I~
    }                                                              //~0113I~

    //*****************************************************************//~0113M~
    @SuppressLint("MissingPermission")
    private void requestPeers(boolean PswChanged)                  //~0113M~
    {                                                              //~0113M~
        if (Dump.Y) Dump.println("WDIReceiver.requestPeers swChanged=" + PswChanged + ",manager" + Utils.toString(manager) + ",channel=" + Utils.toString(channel));//~va40R~
        if (manager != null && channel != null)                       //~0113M~
        {                                                          //~0113I~
			if (!UView.isPermissionGrantedLocation())              //~va40I~
                return;
            manager.requestPeers(channel, this/*PeerListListener*/); //callback onPeersAvailable//~0113M~
        }                                                          //~0113I~
        if (!PswChanged)   //register                                        //~0113I~//~0115R~
            peersOld=null;                                         //~0113I~
    }                                                              //~0113M~
    //*****************************************************************//~0113I~
    private void peersChanged()                                    //~0113I~
	{                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.peersChanged");      //~0113I~
	    requestPeers(true/*PswChanged*/);                          //~0113I~
    }                                                              //~0113I~
    //*****************************************************************//~0113I~
    private void thisDeviceChanged()                               //~0113I~
	{                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.thisDeviceChanged"); //~0113I~
//            DeviceListFragment fragment = WDA.getDeviceListFragment();//~0113I~
//            fragment.updateThisDevice((WifiP2pDevice) intent.getParcelableExtra(//~0113I~
//                    WifiP2pManager.EXTRA_WIFI_P2P_DEVICE));      //~0113I~
    }                                                              //~0113I~
    //*****************************************************************//~0113I~
    private void pairConnected()                                   //~0113I~
	{                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.pairConnected");     //~0113I~
//      WDA.getWDActivity().dismissProgDlg(WiFiDirectActivity.PROGRESS_CONNECT);    //current ProgDlg:DoPair//~0113R~
//      DeviceDetailFragment fragment = WDA.getDeviceDetailFragment();//~0113R~
//      if (Dump.Y) Dump.println("WDIReceiver.onReceive requestConnectionInfo");//~0113R~
//      manager.requestConnectionInfo(channel, fragment);          //~0113R~
//      manager.requestGroupInfo(channel,fragment/*GroupInfoListener*/);//~0113R~
//      AG.aWDA.enableCBWantGroupOwner(false);  //connected        //~0113R~
//      WDA.getDeviceListFragment().setConnected(true);            //~0113R~
		requestPeers(true/*swChanged*/);	//may changed status to connected(0)-->available(=3)//~0115R~
    }                                                              //~0113I~
    //*****************************************************************//~0113I~
    private void pairDisconnected()                                //~0113R~
	{                                                              //~0113I~
        if (Dump.Y) Dump.println("WDIReceiver.pairDisconnected");  //~0113I~
//      int clearCtr=activity.resetData();                         //~0113R~
//      AG.aWDA.enableCBWantGroupOwner(true);   //disconnected     //~0113R~
//        manager.requestConnectionInfo(channel,this/*ConnectionInfoListener*/);//~0115R~
//        manager.requestGroupInfo(channel,this/*GroupInfoListener*/);//~0115R~
	    requestPeers(true/*PswChanged*/);                          //~0115R~
    }                                                              //~0113I~
//    //***********************************************************************************//~0115R~
//    @Override //ConnectionInfoListener                           //~0115R~
//    public void onConnectionInfoAvailable(final WifiP2pInfo info)//~0115R~
//    {                                                            //~0115R~
//        if (Dump.Y) Dump.println("WDIReceiver.onConnectionInfoAvailable");//~0115R~
//        try                                                      //~0115R~
//        {                                                        //~0115R~
//            connectionInfoAvailable(info);                       //~0115R~
//        }                                                        //~0115R~
//        catch(Exception e)                                       //~0115R~
//        {                                                        //~0115R~
//            Dump.println(e,"WDIReceiver.onConnectionInfoAvailable");//~0115R~
//        }                                                        //~0115R~
//    }                                                            //~0115R~
//    //***********************************************************************************//~0115R~
//    private void connectionInfoAvailable(final WifiP2pInfo Pinfo)//~0115R~
//    {                                                            //~0115R~
//        if (Dump.Y) Dump.println("WDIReceiver.connectionInfoAvailable info="+Pinfo.toString());//~0115R~
////        ownerIPAddress=info.groupOwnerAddress.getHostAddress();//~0115R~
////        if (info.groupFormed && info.isGroupOwner) {           //~0115R~
////            setStatus(false,R.string.server_text);             //~0115R~
////            if (Dump.Y) Dump.println("onConnectionInfoAvailable:Client:statusText="+tvStatusText.getText().toString());//~0115R~
////        } else if (info.groupFormed) {                         //~0115R~
////            // The other device acts as the client. In this case, we enable the//~0115R~
////            // get file button.                                //~0115R~
////            setStatus(false,R.string.client_text);             //~0115R~
////            if (Dump.Y) Dump.println("onConnectionInfoAvailable:Server:statusText="+tvStatusText.getText().toString());//~0115R~
////        }                                                      //~0115R~
//    }                                                            //~0115R~
//    //***********************************************************************************//~0115R~
//    @Override   //*GroupInfoListener                             //~0115R~
//    public void onGroupInfoAvailable(final WifiP2pGroup Pgroup)  //~0115R~
//    {                                                            //~0115R~
//    //*******************                                        //~0115R~
//        if (Dump.Y) Dump.println("WDIReceiver.onGroupInfoAvailable");//~0115R~
//        try                                                      //~0115R~
//        {                                                        //~0115R~
//            groupInfoAvailable(Pgroup);                          //~0115R~
//        }                                                        //~0115R~
//        catch(Exception e)                                       //~0115R~
//        {                                                        //~0115R~
//            Dump.println(e,"WDIReceiver.onGroupInfoAvailable");  //~0115R~
//        }                                                        //~0115R~
//    }                                                            //~0115R~
//    //***********************************************************************************//~0115R~
//    private void groupInfoAvailable(final WifiP2pGroup Pgroup)   //~0115R~
//    {                                                            //~0115R~
//    //*******************                                        //~0115R~
//        if (Dump.Y) Dump.println("WDIReceiver.groupInfoAvailable info="+Utils.toString(Pgroup));//~0115R~
//        List<String> groupList=new ArrayList<String>();          //~0115R~
//        if (Pgroup!=null)                                        //~0115R~
//        {                                                        //~0115R~
//            boolean swOwner=Pgroup.isGroupOwner();               //~0115R~
//            WifiP2pDevice deviceOwner=Pgroup.getOwner();         //~0115R~
//            if (Dump.Y) Dump.println("WDIReceiver.groupInfoAvailable swOwner="+swOwner+",ownerDevice="+Utils.toString(deviceOwner));//~0115R~
//            Collection<WifiP2pDevice> clients=Pgroup.getClientList();//~0115R~
//            List<WifiP2pDevice> clientList=new ArrayList<WifiP2pDevice>();//~0115R~
//            clientList.addAll(clients);                          //~0115R~
//            int sz=clientList.size();                            //~0115R~
//            if (Dump.Y) Dump.println("WDIReceiver.groupInfoAvailable client list ctr="+sz);//~0115R~
//            int clientCtr=0;                                     //~0115R~
//            for (int ii=0;ii<sz;ii++)                            //~0115R~
//            {                                                    //~0115R~
//                WifiP2pDevice client=clientList.get(ii);         //~0115R~
//                int status=client.status;                        //~0115R~
//                if (Dump.Y) Dump.println("WDIReceiver.groupInfoAvailable clientList="+client.toString());//~0115R~
//    //            if (status==WifiP2pDevice.CONNECTED            //~0115R~
//    //            &&  !client.isGroupOwner())                    //~0115R~
//    //            {                                              //~0115R~
//    //                clientCtr++;                               //~0115R~
//    //                peers+=getDeviceName(client)+" ";          //~0115R~
//    //            }                                              //~0115R~
//                if (status==WifiP2pDevice.CONNECTED)             //~0115R~
//                    groupList.add(client.deviceName);            //~0115R~
//            }                                                    //~0115R~
//        }                                                        //~0115R~
//    }                                                            //~0115R~
    //*********************************************************    //~vat2M~
	@SuppressWarnings("deprecation")                               //~vat2M~
    public static WifiP2pInfo getParcelableExtraWD(Intent Pintent,String PitemName)//~vat2M~
    {                                                              //~vat2M~
        if (Dump.Y) Dump.println("WDIReceiver.getPercelableExtraWD itemName="+PitemName+",intent="+Pintent);//~vat2R~
        WifiP2pInfo p2pinfo;                                       //~vat2M~
        if (AG.osVersion>=33)  //android4                          //~vat2M~
        {                                                          //~vat2M~
    		p2pinfo=getParcelableExtraWD33(Pintent,PitemName);     //~vat2M~
        }                                                          //~vat2M~
        else                                                       //~vat2M~
        {                                                          //~vat2M~
			p2pinfo = Pintent.getParcelableExtra(PitemName);        //~vat2M~
    	}                                                          //~vat2M~
        if (Dump.Y) Dump.println("WDIReceiver.getPercelableExtraWD pwpInfo="+p2pinfo);//~vat2R~
        return p2pinfo;                                             //~vat2M~
    }                                                              //~vat2M~
    //*********************************************************    //~vat2M~
    @TargetApi(33)                                                 //~vat2M~
    private static WifiP2pInfo getParcelableExtraWD33(Intent Pintent,String PitemName)//~vat2M~
    {                                                              //~vat2M~
        if (Dump.Y) Dump.println("WDIReceiver.getPercelableExtraWD33 itemName="+PitemName+",intent="+Pintent);//~vat2R~
		WifiP2pInfo p2pinfo=Pintent.getParcelableExtra(PitemName,WifiP2pInfo.class);//~vat2M~
        if (Dump.Y) Dump.println("WDIReceiver.getPercelableExtra33 pwpInfo="+p2pinfo);//~vat2R~
        return p2pinfo;                                                //~vat2M~
    }                                                              //~vat2M~
    //*********************************************************    //~vat2I~
	@SuppressWarnings("deprecation")                               //~vat2I~
    public static WifiP2pDevice getParcelableExtraDeviceWD(Intent Pintent,String PitemName)//~vat2I~
    {                                                              //~vat2I~
        if (Dump.Y) Dump.println("WDIReceiver.getPercelableExtraDeviceWD itemName="+PitemName+",intent="+Pintent);//~vat2R~
        WifiP2pDevice dev;                                         //~vat2I~
        if (AG.osVersion>=33)  //android4                          //~vat2I~
        {                                                          //~vat2I~
    		dev=getParcelableExtraDeviceWD33(Pintent,PitemName);   //~vat2I~
        }                                                          //~vat2I~
        else                                                       //~vat2I~
        {                                                          //~vat2I~
			dev=Pintent.getParcelableExtra(PitemName);             //~vat2I~
    	}                                                          //~vat2I~
        if (Dump.Y) Dump.println("WDIReceiver.getPercelableExtraWD dev="+dev);//~vat2R~
        return dev;                                            //~vat2I~
    }                                                              //~vat2I~
    //*********************************************************    //~vat2I~
    @TargetApi(33)                                                 //~vat2I~
    private static WifiP2pDevice getParcelableExtraDeviceWD33(Intent Pintent,String PitemName)//~vat2I~
    {                                                              //~vat2I~
        if (Dump.Y) Dump.println("WDIReceiver.getPercelableExtraDeviceWD33 itemName="+PitemName+",intent="+Pintent);//~vat2R~
		WifiP2pDevice dev=Pintent.getParcelableExtra(PitemName,WifiP2pDevice.class);//~vat2I~
        if (Dump.Y) Dump.println("WDIReceiver.getPercelableExtraDevice33 dev="+dev);//~vat2R~
        return dev;                                                //~vat2I~
    }                                                              //~vat2I~
}
