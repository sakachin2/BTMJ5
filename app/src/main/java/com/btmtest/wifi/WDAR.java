//*CID://+va45R~:                             update#=  213;       //+va45R~
//*************************************************************************//~1A65I~
//2020/11/20 va45 (Bug)WD:Suspend button was diable when disconnected//+va45I~
//1Ad2 2015/07/17 HelpDialog by helptext                           //~1Ad2I~
//1Ac4 2015/07/06 WD:try disable wifi direct at unpair             //~1Ac4I~
//1Ac3 2015/07/06 WD:Unpare after active session was closed        //~1Ac3I~
//1Ac2 2015/07/06 WD:Unpare process ignore push of "Cancel" button of alert dialog active session exist//~1Ac2I~
//1A90 2015/04/18 (like as 1A84)WiFiDirect from Top panel          //~1A90I~
//1A87 2015/02/26 WifiDirect:disconnect when Unpair; disconnect reuired before unpair,no ioexception when closed if upaired before  disconnect//~1A90I~
//1A81 2015/02/24 ANFC is not used now                             //~1A81I~
//1A89k2015/03/01 Ajagoc:2015/02/28 confirm session disconnect when unpair//~1A89I~
//1A6C 2015/02/22 after WiFiDirect,standard IP Connection is too late. try WiFi Off before std IPConnection.//~1A6CI~
//1A6a 2015/02/10 NFC+Wifi support                                 //~1A6aI~
//1A67 2015/02/05 (kan)                                            //~1A67I~
//1A65 2015/01/29 implement Wifi-Direct function(>=Api14:android4.0)//~1A65I~
//*************************************************************************//~1A65I~
package com.btmtest.wifi;                                                //~1A67R~

import android.view.View;
import android.widget.Button;

import com.btmtest.BT.Members;
import com.btmtest.R;
import com.btmtest.dialog.AxeDlg;
import com.btmtest.dialog.SuspendIOErrDlg;
import com.btmtest.game.Status;
import com.btmtest.game.UserAction;
import com.btmtest.game.gv.GMsg;
import com.btmtest.gui.UButton;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import static com.btmtest.StaticVars.AG;                           //~9721I~

//@TargetApi(AG.ICE_CREAM_SANDWICH)   //api14                           //~1A65R~//~9721R~
//public class WiFiDirectActivity extends Activity implements ChannelListener, DeviceActionListener {//~1A65R~
//public class WDA extends AxeDialog                                 //~1A65R~//~9720R~
//public class WDA extends AxeDlg                                    //~9720I~//~9B03R~
//    implements  UEditText.UEditTextI          //~9722R~          //~9B03R~
public class WDAR extends WDA                                      //~9B03I~
{                                                                  //~1A65I~
    private static final int LAYOUTID= R.layout.wifireconnectdlg;         //~9720I~//~9B04R~
    private static final int TITLEID=R.string.DialogTitle_WDAR;//~1A65R~//~9B04R~
    private static final int HELP_TITLEID=TITLEID;                 //~9721I~
                                                                   //~9B04I~
	private static final int BTNID_CONTINUE_GAME=R.id.BTGame;      //~9B04I~
	private static final int BTNID_SUSPEND_GAME=R.id.BTSuspendGame;//~9B04I~
//  public WiFiDirectActivity aWDActivity;                         //~1A65R~//~9B03R~
//  private IPConnectionWD IPC;                                      //~1A65R~//~9721R~//~9B03R~
//  public  DeviceDetailFragment deviceDetailFragment;             //~1A65I~//~9722R~//~9B03R~
//  public  DeviceListFragment deviceListFragment;                 //~1A65I~//~9722R~//~9B03R~
                                                                   //~1A65I~
//  public static final String TAG = "wifidirectdemo";             //~9722R~
//  public boolean swWifiEnable;                                   //~1A65I~//~9B03R~
//  public String WDAipa,WDApeer;                                  //~1A65R~//~9B03R~
//  public boolean WDAowner;                                       //~1A65I~//~9B03R~
//  public boolean swOwner;                                        //~1A84I~//~1A90I~//~9B03R~
//    private WDANFC  NFCwaitingDiscover;                            //~1A6aI~//~9721R~
//    private String  NFCwaitingDiscoverAddr;                        //~1A6aI~//~9721R~
//  private UButton btnAccept,btnConnect,/*btnGame,*/btnDisconnect,btnStopAccept;//~1A84R~//~1A90I~//~9720R~
//  private Button btnAccept,btnConnect,/*btnGame,*/btnDisconnect,btnStopAccept;//~9720I~//~9B03R~
//  private boolean swDismissAtClose;                               //~9720I~//~9722R~
//  private UEditText etYourName;                                   //~1Aa5I~//~9722M~//~9B03R~
//  private GroupList groupList;                                   //~1Aa5I~//~9722M~//~9B03R~
//  private boolean statusErr;                                     //~9729I~//~9B03R~
//  private String statusMsg;                                      //~9729I~//~9B03R~
//  public UCheckBox cbWantGroupOwner;                             //~9A03I~//~9B03R~
	private Button btnGame,btnSuspendGame;                         //~9B04I~
	private boolean swServer;                                      //~9B04I~
    private Members members;                                       //~9B04I~
	//***********************************************************************************//~9720I~
	public WDAR()                                                   //~9720I~//~9B03R~
    {                                                              //~9720I~
        if (Dump.Y) Dump.println("WDAR:default constructor");       //~9720I~//~9B03R~
        AG.aWDAR=this;                                              //~9722I~//~9B03R~
    }                                                              //~9720I~
//    //***********************************************************************************//~9720I~
//    public static WDA showDialog(IPConnection Pipc)                           //~1612R~//~1A65R~//~9720R~
//    {                                                              //~1528I~//~1A65I~//~9720R~
//        WDA dlg=new WDA(Pipc);           //~1612I~                 //~1A65R~//~9720R~
//        if (Dump.Y) Dump.println("WDA:showDialog swWifiEnable="+dlg.swWifiEnable+",SWDA=null?"+(SWDA==null?"yes":"no"));//~1Ad2I~//~9720R~
//        if (SWDA==null)                                            //~1A65I~//~9720R~
//            return null;                                           //~1A65I~//~9720R~
//        String title=dlg.getResources().getString(TITLEID);     //~1612I~//~1A65R~//~9720R~
//        if (!dlg.swWifiEnable)                                         //~1A65I~//~9720R~
//            return null;                                           //~1A65I~//~9720R~
//        dlg.showDialog(title);                               //~1612I~//~1A65I~                                           //~1A65I~//~9720R~
//        return dlg;                                          //~1612I~//~1A65I~//~9720R~
//    }                                                              //~1612I~//~1A65I~//~9720R~
    //***********************************************************************************//~9720I~
    public static WDAR newInstance(IPConnectionWD Pipc)               //~9720R~//~9B03R~
    {                                                              //~9720I~
        if (Dump.Y) Dump.println("WDAR:newInstance Pipc="+Pipc.toString());               //~9720R~//~9B03R~//~9B07R~
        WDAR dlg=new WDAR();                                         //~9720R~//~9B03R~
        dlg.IPC=Pipc;                                              //~9720I~
        AxeDlg.newInstance(dlg,TITLEID,LAYOUTID,HELPFILE);         //~9720I~
        return dlg;                                                //~9720I~
    }                                                              //~9720I~
    //***********************************************************************//~9724I~//~9B03R~
    //*from IPConnection.startWD<--WDI.startRemoteIP<--MenuDialogConnect//~9724I~//~9B03R~
    //***********************************************************************//~9724I~//~9B03R~
    public static WDAR showDialog()                //~9720I~       //~9B03R~
    {                                                              //~9720I~//~9B03R~
        if (Dump.Y) Dump.println("WDAR:showDialog");                //~9720I~//~9B03R~
        if (isShowing())                                           //~9B03I~
        {                                                          //~9B03I~
        	if (Dump.Y) Dump.println("WDAR:newInstance Dup");      //~9B03I~
        	return null;                                           //~9B03I~
        }                                                          //~9B03I~
        WDAR dlg=newInstance(AG.aIPConnectionWD);                  //~9B03R~
        dlg.initWifi();                                            //~9720I~//~9B03R~
//      if (!dlg.swWifiEnable)                                     //~9720I~//~9B03R~
//          return null;                                           //~9720I~//~9B03R~
        ((AxeDlg)dlg).showDialog(null);                                      //~9720I~//~9B03R~
        return dlg;                                                //~9720I~//~9B03R~
    }                                                              //~9720I~//~9B03R~
    //*****************************                                //~9724I~//~9B04I~
    @Override                                                      //~9724I~//~9B04I~
    public void onDismissDialog()                                  //~9724I~//~9B04I~
    {                                                              //~9724I~//~9B04I~
		if (Dump.Y) Dump.println("WDAR:onDismissDialog");           //~9724I~//~9B04I~
        aWDActivity.unregisterReceiver();                          //~1A65I~//~9725M~//~9B04I~
        IPC.dismissWDA();   //stopServer:stop accept               //~1A67I~//~9725M~//~9B04I~
//      boolean enable=(swOwner)&&(AG.aBTMulti.BTGroup.getConnectedCtr()!=0);//~9724I~//~9B04I~
//      AG.aMainView.enableStartGame(enable);                      //~9724I~//~9B04I~
        AG.aWDAR=null;                                              //~9724M~//~9B04I~
        AG.aWDA=null;                                              //~9B04I~
        WDI.dismissedWDA();                                        //~0115I~
	}                                                              //~9724I~//~9B04I~
    //******************************************                   //~9B04I~
    @Override                                                      //~9B04I~
    public void onDestroy() {                                      //~9B04I~
        if (Dump.Y) Dump.println("WDAR:onDestroy");                //~9B04I~
        super.onDestroy();                                         //~9B04I~
        AG.aWDAR=null;                                             //~9B04I~
        AG.aWDA=null;                                              //~9B04I~
    }                                                              //~9B04I~
    //******************************************                   //~9B04I~
////    //***********************************************************************************//~1A65I~//~9720R~//~9B03R~
////    public WDA(IPConnection Pipc)                                  //~1A65R~//~9720R~//~9B03R~
////    {                                                              //~1A65I~//~9720R~//~9B03R~
//////      super((AG.screenDencityMdpiSmallV || AG.screenDencityMdpiSmallH/*mdpi and height or width <=320*/ ? LAYOUTID_MDPI : LAYOUTID));//~1A67I~//~9720R~//~9B03R~
////        super(LAYOUTID);                                         //~9720R~//~9B03R~
////        IPC=Pipc;                                                  //~1A65R~//~9720R~//~9B03R~
////        if (AG.osVersion>=AG.ICE_CREAM_SANDWICH)  //android4          //~1A65I~//~1A6aR~//~9720R~//~9B03R~
////            init_ICE_CREAM_SANDWICH();                             //~1A65I~//~9720R~//~9B03R~
////        else                                                       //~1A65I~//~9720R~//~9B03R~
////            init_deprecated();                                     //~1A65I~//~9720R~//~9B03R~
////    }                                                              //~1A65I~//~9720R~//~9B03R~
////    //***********************************************************************************//~1A65M~//~9720R~//~9B03R~
////    private void init_deprecated()                                 //~1A65R~//~9720R~//~9B03R~
////    {                                                              //~1A65I~//~9720R~//~9B03R~
////        SWDA=null;                                                 //~1A65I~//~9720R~//~9B03R~
////    }                                                              //~1A65I~//~9720R~//~9B03R~
//    //***********************************************************************************//~1A65I~//~9B03R~
//    private void initWifi()                                        //~9720I~//~9B03R~
//    {                                                              //~9720I~//~9B03R~
//        init_ICE_CREAM_SANDWICH();                                 //~9720I~//~9B03R~
//    }                                                              //~9720I~//~9B03R~
//    //***********************************************************************************//~9720I~//~9B03R~
////    @TargetApi(AG.ICE_CREAM_SANDWICH)                                 //~1A65I~//~9720R~//~9B03R~
//    private void init_ICE_CREAM_SANDWICH()                         //~1A65I~//~9B03R~
//    {                                                              //~1A65I~//~9B03R~
////      SWDA=this;                                                 //~1A65I~//~9729R~//~9B03R~
//        aWDActivity=new WiFiDirectActivity();                      //~1A65R~//~9B03R~
//        deviceListFragment=new DeviceListFragment();               //~1A65I~//~9B03R~
//        deviceDetailFragment=new DeviceDetailFragment();           //~1A65I~//~9B03R~
//      swWifiEnable=                                                //~1A84I~//~1A90I~//~9B03R~
//        enableWiFi();                                              //~1A65I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:showDialog init_ICE_CREAM_SANDWICH swWifiEnable="+swWifiEnable);//~1Ad2I~//~9B03R~
////      aWDActivity.registerReceiver();                            //~1A65I~//~9720R~//~9B03R~
//    }                                                              //~1A65I~//~9B03R~
//    //***********************************************************************************//~1A65I~//~9B03R~
//    public static boolean enableWiFi()                             //~1A6CI~//~9B03R~
//    {                                                              //~1A6CI~//~9B03R~
//        if (AG.osVersion>=AG.ICE_CREAM_SANDWICH)  //android4       //~1A6CI~//~9B03R~
//            return enableWiFi_ICE_CREAM_SANDWICH();              //~1A6CI~//~9B03R~
//        return false;                                              //~1A6CI~//~9B03R~
//    }                                                              //~1A6CI~//~9B03R~
//    //***********************************************************************************//~1A6CI~//~9B03R~
////  @TargetApi(AG.ICE_CREAM_SANDWICH)                              //~1A65I~//~9721R~//~9B03R~
////  private boolean enableWiFi()                                   //~1A65I~//~1A6CR~//~9B03R~
//    private static boolean enableWiFi_ICE_CREAM_SANDWICH()         //~1A6CI~//~9B03R~
//    {                                                              //~1A65I~//~9B03R~
//        boolean rc=true;                                           //~1A65I~//~9B03R~
//    //**********************                                       //~1A65I~//~9B03R~
//        WifiManager wm=(WifiManager) AG.context.getSystemService(Context.WIFI_SERVICE);//~1A65R~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:enableWiFi_ICE_CREAM_SANDWITCH isWifiEnabled="+wm.isWifiEnabled());//~1Ac4I~//~1Ad2R~//~9B03R~
////        if (true)                      //@@@@test                //~1Ac4I~//~9B03R~
////        {                                                        //~1Ac4I~//~9B03R~
////            setWifiDirectStatus(true);   //@@@@test              //~1Ac4I~//~9B03R~
//////          setWifiP2pState(true);  //enableP2p @@@@test         //~1Ac4I~//~9B03R~
////        }                                                        //~1Ac4I~//~9B03R~
////        else                                                     //~1Ac4I~//~9B03R~
//        if (!wm.isWifiEnabled())                                   //~1A65I~//~9B03R~
//        {                                                          //~1A65I~//~9B03R~
//            if (wm.setWifiEnabled(true))//success                  //~1A65I~//~9B03R~
//                UView.showToastLong(R.string.InfoWifiEnabled);            //~1A65I~//~9721R~//~9B03R~
//            else                                                   //~1A65I~//~9B03R~
//            {                                                      //~1A65I~//~9B03R~
//                UView.showToastLong(R.string.ErrWifiEnable);              //~1A65I~//~9721R~//~9B03R~
//                rc=false;                                          //~1A65I~//~9B03R~
//            }                                                      //~1A65I~//~9B03R~
//        }                                                          //~1A65I~//~9B03R~
//        if (Dump.Y) Dump.println("enableWiFi rc="+rc);             //~1A65I~//~9B03R~
////      swWifiEnable=rc;                                           //~1A65I~//~1A6CR~//~9B03R~
////        if (SWDA!=null)                                            //~1A6CI~//~1A90R~//~9B03R~
////            SWDA.swWifiEnable=rc;                                  //~1A6CI~//~1A90R~//~9B03R~
//        return rc;                                                 //~1A65I~//~9B03R~
//    }                                                              //~1A65I~//~9B03R~
//    //***********************************************************************************//~1A6CI~//~9B03R~
//    public static boolean disableWiFi()                            //~1A6CI~//~9B03R~
//    {                                                              //~1A6CI~//~9B03R~
//        if (AG.osVersion>=AG.ICE_CREAM_SANDWICH)  //android4       //~1A6CI~//~9B03R~
//            return disableWiFi_ICE_CREAM_SANDWICH();               //~1A6CI~//~9B03R~
//        return false;                                              //~1A6CI~//~9B03R~
//    }                                                              //~1A6CI~//~9B03R~
//    //***********************************************************************************//~1A6CI~//~9B03R~
////  @TargetApi(AG.ICE_CREAM_SANDWICH)                              //~1A6CI~//~9721R~//~9B03R~
//    private static boolean disableWiFi_ICE_CREAM_SANDWICH()        //~1A6CR~//~9B03R~
//    {                                                              //~1A6CI~//~9B03R~
//        boolean rc=true;                                           //~1A6CI~//~9B03R~
//    //**********************                                       //~1A6CI~//~9B03R~
//        WifiManager wm=(WifiManager) AG.context.getSystemService(Context.WIFI_SERVICE);//~1A6CI~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:disableWiFi old="+wm.isWifiEnabled());//~1Ac4I~//~9B03R~
//        if (wm.isWifiEnabled())                                    //~1A6CI~//~9B03R~
//        {                                                          //~1A6CI~//~9B03R~
//            if (wm.setWifiEnabled(false))                          //~1A6CI~//~9B03R~
//                UView.showToastLong(R.string.InfoWifiDisabled);    //~1A6CI~//~9721R~//~9B03R~
//            else                                                   //~1A6CI~//~9B03R~
//            {                                                      //~1A6CI~//~9B03R~
//                UView.showToastLong(R.string.ErrWifiDisable);      //~1A6CI~//~9721R~//~9B03R~
//                rc=false;                                          //~1A6CI~//~9B03R~
//            }                                                      //~1A6CI~//~9B03R~
//        }                                                          //~1A6CI~//~9B03R~
//        if (Dump.Y) Dump.println("disnableWiFi rc="+rc);           //~1A6CI~//~9B03R~
////      if (SWDA!=null)                                            //~1A6CI~//~9729R~//~9B03R~
////          SWDA.swWifiEnable=!rc;                                 //~1A6CI~//~9729R~//~9B03R~
//        if (AG.aWDA!=null)                                         //~9729I~//~9B03R~
//            AG.aWDA.swWifiEnable=!rc;                              //~9729I~//~9B03R~
//        return rc;                                                 //~1A6CI~//~9B03R~
//    }                                                              //~1A6CI~//~9B03R~
//    //***********************************************************************************//~1A65I~//~9B03R~
//    @Override                                                      //~1A65I~//~9B03R~
////  protected void setupDialogExtend(ViewGroup PlayoutView)        //~1A65I~//~9720R~//~9B03R~
//    protected void setupDialogExtend(View PlayoutView)             //~9720I~//~9B03R~
//    {                                                              //~1A65I~//~9B03R~
////      if (SWDA==null)                                            //~1A65R~//~9729R~//~9B03R~
////          return;                                                //~1A65I~//~9729R~//~9B03R~
//        resetMembersMode(AG.aBTMulti.BTGroup);                      //~9817I~//~9B03R~
//        deviceListFragment.initFragment(PlayoutView);              //~1A65I~//~9B03R~
//        deviceDetailFragment.initFragment(PlayoutView);            //~1A65I~//~9B03R~
//        etYourName=UEditText.bind(PlayoutView,R.id.YourName,this);//~1Aa5I~//~9722I~//~9B03R~
//        cbWantGroupOwner=new UCheckBox(PlayoutView,R.id.cbWantGroupOwner);//~9A03I~//~9B03R~
//        etYourName.setText(AG.YourName,true/*swLostFocus*/);       //~9A09I~//~9B03R~
//        groupList=new GroupList();                                 //~1Aa5I~//~9722M~//~9B03R~
//        groupList.init(PlayoutView,etYourName); //tell views                    //~1Aa5I~//~9722M~//~9723R~//~9B03R~
//        groupList.updateDialog();                                  //~9722I~//~9B03R~
//        setButtonListenerAll(BUTTONS_2ND);                         //~1A65R~//~9B03R~
////        btnAccept=new UButton(layoutView,ID_ACCEPT);               //~1A84I~//~1A90I~//~9720R~//~9B03R~
////        btnStopAccept=new UButton(layoutView,ID_STOPACCEPT);       //~1A84I~//~1A90I~//~9720R~//~9B03R~
////        btnConnect=new UButton(layoutView,ID_CONNECT);             //~1A84I~//~1A90I~//~9720R~//~9B03R~
////        btnDisconnect=new UButton(layoutView,ID_DISCONNECT);       //~1A84I~//~1A90I~//~9720R~//~9B03R~
//        btnAccept=UButton.bind(PlayoutView,ID_ACCEPT,null);        //~9720R~//~9B03R~
//        btnStopAccept= UButton.bind(PlayoutView,ID_STOPACCEPT,null);//~9720R~//~9B03R~
//        btnConnect=UButton.bind(PlayoutView,ID_CONNECT,null);      //~9720R~//~9B03R~
//        btnDisconnect=UButton.bind(PlayoutView,ID_DISCONNECT,null);//~9720R~//~9B03R~
////      btnGame=new UButton(layoutView,ID_GAME);                   //~1A84I~//~1A90I~//~9720R~//~9B03R~
//        updateButtonView(false/*owner*/);                          //~1A84R~//~1A90I~//~9B03R~
////      aWDActivity.registerReceiver();                            //~9720I~//~9724R~//~9B03R~
//    }                                                              //~1A65I~//~9B03R~
    //***********************************************************************************//~9B04I~
    @Override                                                      //~9B04I~
    protected void setupDialogExtend(View PlayoutView)             //~9B04I~
    {                                                              //~9B04I~
        if (Dump.Y) Dump.println("WDAR:setupDialogExtended");      //~9B04I~
		swServer=AG.aAccounts.isServer();                          //~9B04I~
        members=AG.aBTMulti.BTGroup;                               //~9B04I~
		btnGame= UButton.bind(PlayoutView,BTNID_CONTINUE_GAME,this);     //~9B04I~
		btnSuspendGame= UButton.bind(PlayoutView,BTNID_SUSPEND_GAME,this);//~9B04I~
        super.setupDialogExtend(PlayoutView);                      //~9B04I~
        if (swServer)                                              //~9B04I~
        	btnConnect.setVisibility(View.GONE);                   //~9B04I~
        else                                                       //~9B04I~
        {                                                          //~9B04I~
        	btnAccept.setEnabled(false);                           //~9B04I~
			btnGame.setVisibility(View.GONE);                      //~9B04I~
        }                                                          //~9B04I~
    }                                                              //~9B04I~
//    //******************************************                   //~9817I~//~9B03R~
//    private void resetMembersMode(Members Pmembers)                //~9817I~//~9B03R~
//    {                                                              //~9817I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:resetMembersMode");          //~9817I~//~9B03R~
//        Pmembers.resetMode(CM_WD);                                 //~9817I~//~9B03R~
//    }                                                              //~9817I~//~9B03R~
////    //**********************************                           //~1A65I~//~1A90R~//~9B03R~
////    @Override                                                      //~1A65I~//~1A90R~//~9B03R~
////    protected boolean onClickClose()                                    //~1A65I~//~1A90R~//~9B03R~
////    {                                                              //~1A65I~//~1A90R~//~9B03R~
////        boolean rc=false;                                           //~1A65I~//~1A90R~//~9B03R~
////        String ipa;                                                //~1A65I~//~1A90R~//~9B03R~
////        boolean owner;                                             //~1A65I~//~1A90R~//~9B03R~
////    //*****************************                                //~1A65I~//~1A90R~//~9B03R~
////        ipa=deviceDetailFragment.ownerIPAddress;                   //~1A65R~//~1A90R~//~9B03R~
////        owner=deviceListFragment.thisOwner==1;                     //~1A65R~//~1A90R~//~9B03R~
////        if (deviceListFragment.thisStatus==WifiP2pDevice.CONNECTED)//~1A65I~//~1A90R~//~9B03R~
////        {                                                          //~1A65I~//~1A90R~//~9B03R~
////            if (Dump.Y) Dump.println("WDA:onClickClose connected ipa="+(ipa==null?"null":ipa)+",owner="+owner);//~1A65R~//~1A67R~//~1A90R~//~9B03R~
////            if (!owner && ipa!=null)                               //~1A65R~//~1A90R~//~9B03R~
////            {                                                      //~1A65I~//~1A90R~//~9B03R~
////                UView.showToast(R.string.InfoWDUseOwnerIP,ipa);    //~1A65R~//~1A90R~//~9721R~//~9B03R~
////                rc=true;                                           //~1A65I~//~1A90R~//~9B03R~
////            }                                                      //~1A65I~//~1A90R~//~9B03R~
////            else                                                   //~1A65I~//~1A90R~//~9B03R~
////            if (owner)                                             //~1A65R~//~1A90R~//~9B03R~
////            {                                                      //~1A65I~//~1A90R~//~9B03R~
////                UView.showToast(R.string.InfoWDOpenPort);          //~1A65I~//~1A90R~//~9721R~//~9B03R~
////                rc=true;                                           //~1A65I~//~1A90R~//~9B03R~
////            }                                                      //~1A65I~//~1A90R~//~9B03R~
////        }                                                          //~1A65I~//~1A90R~//~9B03R~
////        if (!rc)                                                   //~1A65I~//~1A90R~//~9B03R~
////            UView.showToast(R.string.WarningWDNotConnected);       //~1A65I~//~1A90R~//~9721R~//~9B03R~
////        else                                                       //~1A65I~//~1A90R~//~9B03R~
////        {                                                          //~1A65I~//~1A90R~//~9B03R~
////            WDAipa=ipa;                                            //~1A65I~//~1A90R~//~9B03R~
////            WDAowner=owner;                                        //~1A65I~//~1A90R~//~9B03R~
////            WDApeer=deviceDetailFragment.peerDevice;                  //~1A65I~//~1A90R~//~9B03R~
////            if (WDApeer==null)                                     //~1A65I~//~1A90R~//~9B03R~
////                WDApeer=WDAipa;                                    //~1A65I~//~1A90R~//~9B03R~
//////          IPC.doAction(getResourceString(R.string.doAction_WDAOK));//~1A65R~//~1A67R~//~1A90R~//~9B03R~
////            IPC.onCloseWDA();                                      //~1A67I~//~1A90R~//~9B03R~
////        }                                                          //~1A65I~//~1A90R~//~9B03R~
////        return rc;  //not dismiss                                  //~1A65I~//~1A90R~//~9B03R~
////    }                                                              //~1A65I~//~1A90R~//~9B03R~
//    //**********************************                           //~9A09I~//~9B03R~
//    @Override                                                      //~9A09I~//~9B03R~
//    protected void onClickClose()                                  //~9A09I~//~9B03R~
//    {                                                              //~9A09I~//~9B03R~
//        if (getYourName())            //set AG.yourName            //~9A09R~//~9B03R~
//            dismiss();                                             //~9A09R~//~9B03R~
//    }                                                              //~9A09I~//~9B03R~
//    //*************************************************                           //~1A84I~//~1A90I~//~9722R~//~9B03R~
//    //*TODO required?                                              //~9722I~//~9B03R~
//    //*************************************************            //~9722I~//~9B03R~
////  @Override                                                      //~9720I~//~9722R~//~9B03R~
////  protected void onClickClose()                               //~1A84R~//~1A90I~//~9720R~//~9722R~//~9B03R~
//    protected void onClickClose2()                                 //~9722I~//~9B03R~
//    {                                                              //~1A84I~//~1A90I~//~9B03R~
//        boolean rc=false;                                          //~1A84I~//~1A90I~//~9720R~//~9B03R~
//        String ipa;                                                //~1A84I~//~1A90I~//~9B03R~
//        boolean owner;                                             //~1A84I~//~1A90I~//~9B03R~
//    //*****************************                                //~1A84I~//~1A90I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:onClickClose");              //~9722I~//~9B03R~
//        ipa=deviceDetailFragment.ownerIPAddress;                   //~1A84I~//~1A90I~//~9B03R~
////      owner=deviceListFragment.thisOwner==1;                     //~1A84I~//~1A90I~//~9B03R~
//        owner=swOwner;                                             //~1A84I~//~1A90I~//~9B03R~
//        if (deviceListFragment.thisStatus==WifiP2pDevice.CONNECTED)//~1A84I~//~1A90I~//~9B03R~
//        {                                                          //~1A84I~//~1A90I~//~9B03R~
//            if (Dump.Y) Dump.println("WDA:onClickClose connected ipa="+(ipa==null?"null":ipa)+",owner="+owner);//~1A84I~//~1A90I~//~9B03R~
//            if (!owner && ipa!=null)                               //~1A84I~//~1A90I~//~9B03R~
//            {                                                      //~1A84I~//~1A90I~//~9B03R~
//                rc=true;                                           //~1A84I~//~1A90I~//~9B03R~
//            }                                                      //~1A84I~//~1A90I~//~9B03R~
//            else                                                   //~1A84I~//~1A90I~//~9B03R~
//            if (owner)                                             //~1A84I~//~1A90I~//~9B03R~
//            {                                                      //~1A84I~//~1A90I~//~9B03R~
//                rc=true;                                           //~1A84I~//~1A90I~//~9B03R~
//            }                                                      //~1A84I~//~1A90I~//~9B03R~
//        }                                                          //~1A84I~//~1A90I~//~9B03R~
//        if (!rc)                                                   //~1A84I~//~1A90I~//~9B03R~
//            UView.showToast(R.string.WarningWDNotConnected);   //~1A84I~//~1A90I~//~9721R~//~9B03R~
//        else                                                       //~1A84I~//~1A90I~//~9B03R~
//        {                                                          //~1A84I~//~1A90I~//~9B03R~
//            WDAipa=ipa;                                            //~1A84I~//~1A90I~//~9B03R~
//            WDAowner=owner;                                        //~1A84I~//~1A90I~//~9B03R~
//            WDApeer=deviceDetailFragment.peerDevice;               //~1A84I~//~1A90I~//~9B03R~
//            if (WDApeer==null)                                     //~1A84I~//~1A90I~//~9B03R~
//                WDApeer=WDAipa;                                    //~1A84I~//~1A90I~//~9B03R~
//        }                                                          //~1A84I~//~1A90I~//~9B03R~
////      return rc;  //not dismiss                                  //~1A84I~//~1A90I~//~9720R~//~9B03R~
//        if (rc)                                                    //~9720I~//~9B03R~
//            dismiss();                                             //~9720I~//~9B03R~
////      swDismissAtClose=rc;                                       //~9720I~//~9722R~//~9B03R~
//    }                                                              //~1A84I~//~1A90I~//~9B03R~
//    //**********************************                           //~1A84I~//~1A90I~//~9B03R~
//    private void updateButtonView(boolean Powner)                  //~1A84R~//~1A90I~//~9B03R~
//    {                                                              //~1A84I~//~1A90I~//~9B03R~
//        String ipa;                                                //~1A84I~//~1A90I~//~9B03R~
//        boolean connected,listening,paired;                               //~1A84I~//~1A87R~//~1A90I~//~9B03R~
//    //*****************************                                //~1A84I~//~1A90I~//~9B03R~
//        connected=AG.RemoteStatus==AG.RS_IPCONNECTED;   //server and client              //~1A84I~//~1A90I~//~9729R~//~9B03R~
//        listening=AG.RemoteStatusAccept==AG.RS_IPLISTENING;        //~1A84I~//~1A90I~//~9B03R~
//        paired=deviceListFragment.thisStatus==WifiP2pDevice.CONNECTED;//~1A87I~//~1A90I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:updateButtonView Powner="+Powner+",paired="+paired+",remotestatus="+AG.RemoteStatus+",remotestatusaccept="+AG.RemoteStatusAccept+",connected="+connected);//~1A84R~//~1A90I~//~9727R~//~9729R~//~9B03R~
//        if (paired)                                                //~9729I~//~9B03R~
//        {                                                          //~9729I~//~9B03R~
//            if (Powner)               //paired server              //~9729I~//~9B03R~
//            {                                                      //~9729I~//~9B03R~
//                btnAccept.setVisibility(View.VISIBLE);             //~9729I~//~9B03R~
//                btnStopAccept.setVisibility(View.VISIBLE);         //~9729I~//~9B03R~
//                btnConnect.setVisibility(View.GONE);               //~9729I~//~9B03R~
//                btnDisconnect.setVisibility(View.VISIBLE);         //~9729I~//~9B03R~
//                if (listening) //owner accepting                   //~9729I~//~9B03R~
//                {                                                  //~9729I~//~9B03R~
//                    btnAccept.setEnabled(false);                   //~9729I~//~9B03R~
//                    btnStopAccept.setEnabled(true);                //~9729I~//~9B03R~
//                }                                                  //~9729I~//~9B03R~
//                else                                               //~9729I~//~9B03R~
//                {                                                  //~9729I~//~9B03R~
//                    btnAccept.setEnabled(true);                    //~9729I~//~9B03R~
//                    btnStopAccept.setEnabled(false);               //~9729I~//~9B03R~
//                }                                                  //~9729I~//~9B03R~
//                btnDisconnect.setEnabled(connected);               //~9729I~//~9B03R~
//            }                                                      //~9729I~//~9B03R~
//            else                                                   //~9729I~//~9B03R~
//            {                                                      //~9729I~//~9B03R~
//                btnAccept.setVisibility(View.GONE);                //~9729I~//~9B03R~
//                btnStopAccept.setVisibility(View.GONE);            //~9729I~//~9B03R~
//                btnConnect.setVisibility(View.VISIBLE);            //~9729I~//~9B03R~
//                btnDisconnect.setVisibility(View.VISIBLE);         //~9729I~//~9B03R~
//                btnConnect.setEnabled(!connected);                 //~9729I~//~9B03R~
//                btnDisconnect.setEnabled(connected);               //~9729I~//~9B03R~
//            }                                                      //~9729I~//~9B03R~
//        }                                                          //~9729I~//~9B03R~
//        else                                                       //~9729I~//~9B03R~
//        {                                                          //~9729I~//~9B03R~
//            btnAccept.setVisibility(View.GONE);                    //~9729I~//~9B03R~
//            btnStopAccept.setVisibility(View.GONE);                //~9729I~//~9B03R~
//            btnConnect.setVisibility(View.GONE);                   //~9729I~//~9B03R~
//            btnDisconnect.setVisibility(View.GONE);                //~9729I~//~9B03R~
//        }                                                          //~9729I~//~9B03R~
//    }                                                              //~1A84I~//~1A90I~//~9729R~//~9B03R~
    //**********************************                           //~9B04I~
    @Override                                                      //~9B04I~
    protected void updateButtonView(boolean Powner)                  //~9B04I~
    {                                                              //~9B04I~
    //*****************************                                //~9B04I~
	    if (Dump.Y) Dump.println("WDAR.updateButtonView");         //~9B04I~
    	super.updateButtonView(Powner);                            //~9B04I~
        updateButtonStatusGame();                                  //~9B04I~
    }                                                              //~9B04I~
    //******************************************                   //~9B04I~
    private void updateButtonStatusGame()                          //~9B04I~
    {                                                              //~9B04I~
//      boolean swReconnectedAll=members.isReconnectedAll();       //~9B04I~//~0218R~
        boolean swReconnectedAll=AG.aBTMulti.chkMemberReconnect(); //~0218I~
	    if (Dump.Y) Dump.println("WDAR.updateButtonStatusGame swServer="+swServer);//~9B04I~
        if (swServer)                                              //~9B04I~
        {                                                          //~9B04I~
			btnGame.setEnabled(swReconnectedAll);                  //~9B04I~
        }                                                          //~9B04I~
        else                                                       //~9B04I~
        {                                                          //~9B04I~
//  		if (swReconnectedAll)                                  //~9B04I~//~0218R~
//          	btnConnect.setVisibility(View.GONE);               //~9B04I~//~0218R~
			btnConnect.setEnabled(!swReconnectedAll);              //~0218I~
        }                                                          //~9B04I~
//      btnSuspendGame.setEnabled(swReconnectedAll);               //~9B04I~//+va45R~
        btnSuspendGame.setEnabled(!swReconnectedAll);              //+va45I~
    }                                                              //~9B04I~
//                                                                   //~9729I~//~9B03R~
////    //**********************************                           //~1A65I~//~9721R~//~9B03R~
////    @Override                                                      //~1A65I~//~9721R~//~9B03R~
////    protected void onClickHelp()                                //~1A65I~//~9720R~//~9721R~//~9B03R~
////    {                                                              //~1A65I~//~9721R~//~9B03R~
//////      new HelpDialog(null,R.string.Help_WDA);                    //~1A65I~//~1Ad2R~//~9721R~//~9B03R~
////        new HelpDialog(Global.frame(),R.string.HelpTitle_WDA,"wifidirect");//~1A8rR~//~1Ad2I~//~9721R~//~9B03R~
//////      return false;   //not dismiss                              //~1A65I~//~9720R~//~9721R~//~9B03R~
////    }                                                              //~1A65I~//~9721R~//~9B03R~
//    //***********************************************************************************//~1A65I~//~9B03R~
//    @Override                                                      //~1A65I~//~9B03R~
//    protected void onClickOther(int PbuttonId)                  //~1A65I~//~9720R~//~9B03R~
//    {                                                              //~1A65I~//~9B03R~
//        boolean dismiss=false;                                     //~1A65R~//~9B03R~
//        int rc;                                                    //~1A65I~//~9B03R~
//    //********************                                         //~1A65I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:onClickOther buttonid="+Integer.toHexString(PbuttonId));//~1A84I~//~1A90I~//~9B03R~
//        clearStatusMsg();                                          //~9816I~//~9B03R~
////      if (SWDA==null)                                            //~1A65R~//~9729R~//~9B03R~
////          return false;                                          //~1A65I~//~9720R~//~9B03R~
////          return;                                                //~9720I~//~9729R~//~9B03R~
//        if (!getYourName()) //update AG.YourName                   //~9A09R~//~9B03R~
//            return;                                                //~9A09I~//~9B03R~
//        deviceDetailFragment.setStatus(false,R.string.empty);   //clear err msg//~9727I~//~9B03R~
//        try                                                        //~1A65I~//~9B03R~
//        {                                                          //~1A65I~//~9B03R~
//          switch(PbuttonId)                                        //~1A84I~//~1A90I~//~9B03R~
//          {                                                        //~1A84I~//~1A90I~//~9B03R~
//          case ID_ACCEPT:                                          //~1A84I~//~1A90I~//~9B03R~
//          case ID_CONNECT:                                         //~1A84I~//~1A90I~//~9B03R~
////        case ID_GAME:                                            //~1A84I~//~1A90I~//~9720R~//~9B03R~
//          case ID_STOPACCEPT:                                      //~1A84I~//~1A90I~//~9B03R~
//          case ID_DISCONNECT: //IPDisconnect                                      //~1A84I~//~1A90I~//~9722R~//~9B03R~
//            dismiss=buttonAction(PbuttonId);                       //~1A84I~//~1A90I~//~9B03R~
//            break;                                                 //~1A84I~//~1A90I~//~9B03R~
//          default:                                                 //~1A84I~//~1A90I~//~9B03R~
//            rc=deviceDetailFragment.buttonAction(PbuttonId);       //~1A65R~//~9B03R~
//            if (rc<0)   //not button of List                       //~1A65R~//~9B03R~
//                aWDActivity.buttonAction(PbuttonId);               //~1A65R~//~9B03R~
//            else                                                   //~1A65R~//~9B03R~
//                dismiss=(rc==1);                                   //~1A65R~//~9B03R~
//          }                                                        //~1A84I~//~1A90I~//~9B03R~
//        }                                                          //~1A65I~//~9B03R~
//        catch(Exception e)                                         //~1A65I~//~9B03R~
//        {                                                          //~1A65I~//~9B03R~
//            Dump.println(e,"WDA:onClickOther");                    //~1A65I~//~9B03R~
//        }                                                          //~1A65I~//~9B03R~
//        if (dismiss)                                               //~9720I~//~9B03R~
//            dismiss();                                             //~9720R~//~9B03R~
//        else                                                       //~9728I~//~9B03R~
//            updateButtonView(deviceListFragment.thisOwner==1);     //~9728I~//~9B03R~
//    }                                                              //~1A65I~//~9B03R~
    //***********************************************************************************//~9B04I~
    @Override                                                      //~9B04I~
    protected void onClickOther(int PbuttonId)                     //~9B04I~
    {                                                              //~9B04I~
        boolean dismiss=false;                                     //~9B04I~
        int rc;                                                    //~9B04I~
    //********************                                         //~9B04I~
        if (Dump.Y) Dump.println("WDAR:onClickOther buttonid="+Integer.toHexString(PbuttonId));//~9B04I~
	    GMsg.clearMsgbar();	//MainView and GameView                //~0113I~
        clearStatusMsg();                                          //~9B04I~
    	if (PbuttonId==BTNID_CONTINUE_GAME)                        //~9B04I~
			onClickGame();                                         //~9B04I~
        else                                                       //~9B04I~
        if (PbuttonId==BTNID_SUSPEND_GAME)                         //~9B04I~
			onClickSuspendGame();                                  //~9B04I~
        else                                                       //~9B04I~
        	super.onClickOther(PbuttonId);                         //~9B04I~
    }                                                              //~9B04I~
    //******************************************                   //~9B04I~
    //*on Server only                                              //~9B04I~
    //******************************************                   //~9B04I~
    private void onClickGame()                                     //~9B04I~
    {                                                              //~9B04I~
		if (Dump.Y) Dump.println("WDAR:onClickGame");              //~9B04I~
        if (restartGame())                                             //~9B04I~//~0116R~
	        dismiss();                                                 //~9B04I~//~0116R~
    }                                                              //~9B04I~
    //******************************************                   //~9B04I~
    //*on Server only                                              //~9B04I~
    //******************************************                   //~9B04I~
    private void onClickSuspendGame()                              //~9B04I~
    {                                                              //~9B04I~
		if (Dump.Y) Dump.println("WDAR:onClickSuspendGame");       //~9B04I~
        suspendGame();                                             //~9B04I~
        dismiss();                                                 //~9B04I~
    }                                                              //~9B04I~
//    //***********************************************************************************//~1A65I~//~9B03R~
//    @Override                                                      //~1A65I~//~9B03R~
////  protected void onDismiss()                                     //~1A65I~//~9725R~//~9B03R~
//    public void onDismiss(DialogInterface Pdialog)                 //~v@@@I~//~9725I~//~9B03R~
//    {                                                              //~1A65I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:onDismis");                  //~9725I~//~9B03R~
//        super.onDismiss(Pdialog);   //callback onDismissDialog from UFDlg//~9725R~//~9B03R~
//    }                                                              //~1A65I~//~9B03R~

//    /** register the BroadcastReceiver with the intent values to be matched *///~9B03R~
//    @Override                                                      //~1A65R~//~9720R~//~9B03R~
//    public void onResume() {                                     //~9B03R~
//        super.onResume();                                          //~1A65R~//~9720R~//~9B03R~
////      receiver = new WiFiDirectBroadcastReceiver(manager, channel, this);//~1A65R~//~9B03R~
////      registerReceiver(receiver, intentFilter);                  //~1A65R~//~9B03R~
////      if (SWDA==null)                                            //~1A65R~//~9729R~//~9B03R~
////          return;                                                //~1A65I~//~9729R~//~9B03R~
//        aWDActivity.onResume();                                     //~1A65R~//~9B03R~
//    }                                                            //~9B03R~

//    @Override                                                      //~1A65R~//~9720R~//~9B03R~
//    public void onPause()                                          //~9729R~//~9B03R~
//    {                                                              //~9729I~//~9B03R~
//        swDismissAtPause=false;                                    //~9729I~//~9B03R~
//        super.onPause();                                           //~1A65R~//~9720R~//~9729R~//~9B03R~
////      unregisterReceiver(receiver);                              //~1A65R~//~9B03R~
////      if (SWDA==null)                                            //~1A65R~//~9729R~//~9B03R~
////          return;                                                //~1A65I~//~9729R~//~9B03R~
//        aWDActivity.onPause();  //unregisterReceiver                                      //~1A65R~//~9729R~//~9B03R~
//    }                                                            //~9B03R~

//    //*******************************************************************************************************//~1A65I~//~9B03R~
//    public void connected()                                        //~1A65R~//~9B03R~
//    {                                                              //~1A65I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:connected");                 //~1A65R~//~9B03R~
////        if (AG.aWDANFC!=null)   //NFC activity alive                       //~1A6aI~//~1A81R~//~9B03R~
////            AG.aWDANFC.connected(WDANFC.CONNECTED/*connected*/);   //~1A6aI~//~1A81R~//~9B03R~
//    }                                                              //~1A65I~//~9B03R~
//    //*******************************************************************************************************//~1A84I~//~1A90I~//~9B03R~
//    public void connected(boolean Powner)                          //~1A84I~//~1A90I~//~9B03R~
//    {                                                              //~1A84I~//~1A90I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:connected owner="+Powner);   //~1A84I~//~1A90I~//~9B03R~
//        updateButtonView(Powner);          //~1A84I~               //~1A90I~//~9B03R~
//        swOwner=Powner;                                            //~1A84I~//~1A90I~//~9B03R~
//    }                                                              //~1A84I~//~1A90I~//~9B03R~
//    //*******************************************************************************************************//~1A6aI~//~9B03R~
//    public void connectError()                                     //~1A6aI~//~9B03R~
//    {                                                              //~1A6aI~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:connectError");              //~1A6aI~//~9B03R~
////        if (AG.aWDANFC!=null)   //NFC activity alive                       //~1A6aI~//~1A81R~//~9B03R~
////            AG.aWDANFC.connected(WDANFC.CONNECTERR);                //~1A6aI~//~1A81R~//~9B03R~
//    }                                                              //~1A6aI~//~9B03R~
//    //*******************************************************************************************************//~1A65I~//~9B03R~
//    public void disconnected()                                     //~1A65I~//~9B03R~
//    {                                                              //~1A65I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:diconnected");               //~1A65I~//~9B03R~
//        IPC.unpaired();                                             //~1A87I~//~1A90I~//~9B03R~
//    }                                                              //~1A65I~//~9B03R~
//    //******************************************************************************//~1A65I~//~9B03R~
//    public static DeviceDetailFragment getDeviceDetailFragment()   //~1A65I~//~9B03R~
//    {                                                              //~1A65I~//~9B03R~
////      return SWDA.deviceDetailFragment;                          //~1A65I~//~9729R~//~9B03R~
//        return AG.aWDA.deviceDetailFragment;                       //~9729I~//~9B03R~
//    }                                                              //~1A65I~//~9B03R~
//    //******************************************************************************//~1A65I~//~9B03R~
//    public static DeviceListFragment getDeviceListFragment()       //~1A65I~//~9B03R~
//    {                                                              //~1A65I~//~9B03R~
////      return SWDA.deviceListFragment;                            //~1A65R~//~9729R~//~9B03R~
//        return AG.aWDA.deviceListFragment;                         //~9729I~//~9B03R~
//    }                                                              //~1A65I~//~9B03R~
//    //*******************************************************************************************************//~1A65I~//~9B03R~
//    public static WiFiDirectActivity getWDActivity()            //~1A65R~//~1A6aR~//~9B03R~
//    {                                                              //~1A65M~//~9B03R~
////      return SWDA.aWDActivity;                                               //~1A65R~//~9729R~//~9B03R~
//        return AG.aWDA.aWDActivity;                                //~9729I~//~9B03R~
//    }                                                              //~1A65M~//~9B03R~
//    //*******************************************************************************************************//~1A65I~//~9B03R~
////    private Resources getResources()                               //~1A65R~//~9720R~//~9B03R~
////    {                                                              //~1A65M~//~9720R~//~9B03R~
////        return AG.resource;                                        //~1A65M~//~9720R~//~9B03R~
////    }                                                              //~1A65M~//~9720R~//~9B03R~
//    //*******************************************************************************************************//~1A65I~//~9B03R~
//    public static String getResourceString(int Pid)                //~1A65I~//~9B03R~
//    {                                                              //~1A65I~//~9B03R~
//        return AG.resource.getString(Pid);                         //~1A65I~//~9B03R~
//    }                                                              //~1A65I~//~9B03R~
//    //*******************************************************************************************************//~1A65I~//~9B03R~
//    public void setButtonListener(Button Pbtn)                     //~1A65I~//~9B03R~
//    {                                                              //~1A65I~//~9B03R~
//        super.setButtonListener(Pbtn);                             //~1A65I~//~9B03R~
//    }                                                              //~1A65I~//~9B03R~
////    //*******************************************************************************************************//~1A6aI~//~9A03R~//~9B03R~
////    public void connect(String Pmacaddr)                           //~1A6aI~//~9A03R~//~9B03R~
////    {                                                              //~1A6aI~//~9A03R~//~9B03R~
////        deviceDetailFragment.connect(Pmacaddr);                    //~1A6aI~//~9A03R~//~9B03R~
////    }                                                              //~1A6aI~//~9A03R~//~9B03R~
////    //*******************************************************************************************************//~1A6aI~//~9721R~//~9B03R~
////    //*on macaddr received side                                    //~1A6aI~//~9721R~//~9B03R~
////    //*rc:-1:not found,1:paired,0:not paired(do connect)           //~1A6aI~//~9721R~//~9B03R~
////    //*******************************************************************************************************//~1A6aI~//~9721R~//~9B03R~
////    public int discover(WDANFC Pwdanfc,String Pmacaddr)            //~1A6aR~//~9721R~//~9B03R~
////    {                                                              //~1A6aI~//~9721R~//~9B03R~
////        NFCwaitingDiscover=null;                                   //~1A6aI~//~9721R~//~9B03R~
////        int rc=aWDActivity.discover(Pmacaddr,true/*issue discover when not paired*/);//~1A6aR~//~9721R~//~9B03R~
////        if (rc==-1) //not found                                    //~1A6aR~//~9721R~//~9B03R~
////        {                                                          //~1A6aI~//~9721R~//~9B03R~
////            NFCwaitingDiscover=Pwdanfc; //expect callback at dicovery end//~1A6aI~//~9721R~//~9B03R~
////            NFCwaitingDiscoverAddr=Pmacaddr;    //expect callback at dicovery end//~1A6aI~//~9721R~//~9B03R~
////        }                                                          //~1A6aI~//~9721R~//~9B03R~
////        if (Dump.Y) Dump.println("WDA:discover rc="+rc);           //~1A6aI~//~9721R~//~9B03R~
////        return rc;                                                 //~1A6aI~//~9721R~//~9B03R~
////    }                                                              //~1A6aI~//~9721R~//~9B03R~
//    //*******************************************************************************************************//~1A6aI~//~9B03R~
//    //*on macaddr send side                                        //~1A6aI~//~9B03R~
//    //*******************************************************************************************************//~1A6aI~//~9B03R~
//    public void discover()                                         //~1A6aI~//~9B03R~
//    {                                                              //~1A6aI~//~9B03R~
//        aWDActivity.discover();                                    //~1A6aI~//~9B03R~
//    }                                                              //~1A6aI~//~9B03R~
//    //*******************************************************************************************************//~1A6aI~//~9B03R~
////  public void peerUpdated()                                      //~1A6aI~//~1A90R~//~9B03R~
//    public void peerUpdated(int Ppeerctr)                                      //~1A6aI~//~1A87R~//~1A90I~//~9B03R~
//    {                                                              //~1A6aI~//~9B03R~
//        int rc;                                                    //~1A6aR~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:peerUpdated ctr="+Ppeerctr); //~1A87R~//~1A90I~//~9B03R~
////        if (NFCwaitingDiscover!=null)                              //~1A6aI~//~9721R~//~9B03R~
////        {                                                          //~1A6aI~//~9721R~//~9B03R~
////            rc=aWDActivity.discover(NFCwaitingDiscoverAddr,false/*not issue discover when not paired*/);//~1A6aR~//~9721R~//~9B03R~
////            NFCwaitingDiscover.discovered(NFCwaitingDiscoverAddr,rc);//~1A6aR~//~9721R~//~9B03R~
////            NFCwaitingDiscover=null;    //response gotten          //~1A6aM~//~9721R~//~9B03R~
////        }                                                          //~1A6aI~//~9721R~//~9B03R~
//        updateButtonView(deviceListFragment.thisOwner==1);         //~1A87I~//~1A90I~//~9B03R~
//    }                                                              //~1A6aI~//~9B03R~
//    //*******************************************************************************************************//~1A84I~//~1A90I~//~9B03R~
//    //*from OnClickOther                                           //~9722I~//~9B03R~
//    //*******************************************************************************************************//~9722I~//~9B03R~
//    private boolean buttonAction(int PbuttonId)                    //~1A84I~//~1A90I~//~9B03R~
//    {                                                              //~1A84I~//~1A90I~//~9B03R~
////      if (!onClickClose())    //set partner info parm            //~1A84R~//~1A90I~//~9720R~//~9B03R~
////      onClickClose(); //set partner info parm                    //~9720I~//~9722R~//~9B03R~
////      if (!swDismissAtClose)                                     //~9720I~//~9722R~//~9B03R~
////          return false;                                          //~1A84I~//~1A90I~//~9722R~//~9B03R~
//        boolean rc=IPC.buttonAction(PbuttonId);                    //~1A84I~//~1A90I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:buttonAction rc="+rc);       //~1A84I~//~1A90I~//~9B03R~
//        return rc;                                                 //~1A84I~//~1A90I~//~9B03R~
//    }                                                              //~1A84I~//~1A90I~//~9B03R~
//    //*******************************************************************************************************//~1A87I~//~1A89I~//~9B03R~
//    //*from DeviceDetailFragment; before request unpair            //~1A87I~//~1A89I~//~9B03R~
//    //*******************************************************************************************************//~1A87I~//~1A89I~//~9B03R~
//    public boolean unpairRequest()                                   //~1A87I~//~1A89I~//~9B03R~
//    {                                                              //~1A87I~//~1A89I~//~9B03R~
//        boolean rc=true;    //execute unpair at return             //~1A89I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:unpairRequest remotestatus="+AG.RemoteStatus);//~1A87I~//~1A89I~//~9B03R~
//        if (AG.RemoteStatus==AG.RS_IPCONNECTED) //connection remains//~1A87I~//~1A89I~//~9B03R~
//        {                                                          //~1A87I~//~1A89I~//~9B03R~
//////          IPC.disconnectPartner();                               //~1A87I~//~1A89I~//~9729R~//~9B03R~
////            Alert.AlertI ai=new Alert.AlertI()                                 //~1A89R~//~9729R~//~9B03R~
////                                {                                  //~1A89I~//~9729R~//~9B03R~
////                                    @Override                      //~1A89I~//~9729R~//~9B03R~
////                                    public int alertButtonAction(int Pbuttonid,int Pitempos)//~1A89I~//~9729R~//~9B03R~
////                                    {                              //~1A89I~//~9729R~//~9B03R~
////                                        if (Dump.Y) Dump.println("WDA:unpairRequest buttonid="+Integer.toHexString(Pbuttonid));//~1A89I~//~9729R~//~9B03R~
////                                        if (Pbuttonid==Alert.BUTTON_POSITIVE)//~1A89R~//~9729R~//~9B03R~
////                                        {                          //~1Ac2I~//~9729R~//~9B03R~
//////                                          IPC.disconnectPartner();//~1A89I~//~1Ac3R~//~9729R~//~9B03R~
//////                                      deviceDetailFragment.doUnpair();//~1A89I~//~1Ac3R~//~9729R~//~9B03R~
////                                            IPC.disconnectPartner(true/*unpair after closed*/);//~1Ac3I~//~9729R~//~9B03R~
////                                        }                          //~1Ac2I~//~9729R~//~9B03R~
////                                        return 1;                  //~1A89I~//~9729R~//~9B03R~
////                                    }                              //~1A89I~//~9729R~//~9B03R~
////                                };                                 //~1A89I~//~9729R~//~9B03R~
//////            Alert.simpleAlertDialog(ai,ALERT_TITLE_DISCONNECT,ALERT_MSG_DISCONNECT,//~1A89R~//~9721R~//~9729R~//~9B03R~
//////                                Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE);//~1A89R~//~9721R~//~9729R~//~9B03R~
////            Alert.showAlert(ALERT_TITLE_DISCONNECT,ALERT_MSG_DISCONNECT,//~9721I~//~9729R~//~9B03R~
////                                Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,ai);//~9721I~//~9729R~//~9B03R~
//            deviceDetailFragment.setStatus(true,R.string.Msg_IPConnectionExist);//~9729I~//~9B03R~
//            rc=false;   //execute unpair at later dialog response  //~1A89I~//~9727R~//~9B03R~
//        }                                                          //~1A87I~//~1A89I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:unpairRequest rc="+rc);      //~1A89I~//~9B03R~
//        return rc;                                                 //~1A89I~//~9B03R~
//    }                                                              //~1A87I~//~1A89I~//~9B03R~
////    //*******************************************************************************************************//~1Ac3I~//~9728R~//~9B03R~
////    //*from PartnerThread by IPC.disconnectPartner(unpair=true) at thread terminate//~1Ac3I~//~9728R~//~9B03R~
////    //*******************************************************************************************************//~1Ac3I~//~9728R~//~9B03R~
////    public static void unpairFromPT()                              //~1Ac3I~//~9728R~//~9B03R~
////    {                                                              //~1Ac3I~//~9728R~//~9B03R~
////        if (Dump.Y) Dump.println("WDA:unpairFromPT");              //~1Ac3I~//~9728R~//~9B03R~
////        if (SWDA!=null)                                            //~1Ac3I~//~9728R~//~9B03R~
////        {                                                          //~1Ac3I~//~9728R~//~9B03R~
////            URunnable.setRunFunc(SWDA/*URunnableI*/,0/*delay*/,SWDA/*objparm*/,0/*int parm*/);//~1Ac3I~//~9728R~//~9B03R~
////        }                                                          //~1Ac3I~//~9728R~//~9B03R~
////    }                                                              //~1Ac3I~//~9728R~//~9B03R~
//////***************************************************************  //~1Ac3I~//~9728R~//~9B03R~
////    @Override                                                      //~1Ac3I~//~9728R~//~9B03R~
//////  public void runFunc(Object parmObj,int parmInt/*0*/)           //~1Ac3I~//~9721R~//~9728R~//~9B03R~
////    public void URunnableCB(Object parmObj,int parmInt/*0*/)       //~9721I~//~9728R~//~9B03R~
////    {                                                              //~1Ac3I~//~9728R~//~9B03R~
////        if (Dump.Y) Dump.println("WDA:runfunc");                   //~1Ac3I~//~9728R~//~9B03R~
////        try                                                        //~1Ac3I~//~9728R~//~9B03R~
////        {                                                          //~1Ac3I~//~9728R~//~9B03R~
////            deviceDetailFragment.doUnpair();                       //~1Ac3I~//~9728R~//~9B03R~
////        }                                                          //~1Ac3I~//~9728R~//~9B03R~
////        catch(Exception e)                                         //~1Ac3I~//~9728R~//~9B03R~
////        {                                                          //~1Ac3I~//~9728R~//~9B03R~
////            Dump.println(e,"WDA:runfunc");                         //~1Ac3I~//~9728R~//~9B03R~
////        }                                                          //~1Ac3I~//~9728R~//~9B03R~
////    }                                                              //~1Ac3I~//~9728R~//~9B03R~
////***************************************************************  //~1Ac4I~//~9B03R~
////*disable wifidirect                                              //~1Ac4I~//~9B03R~
////***************************************************************  //~1Ac4I~//~9B03R~
//    public static boolean setWifiDirectStatus(boolean Penable)     //~1Ac4I~//~9B03R~
//    {                                                              //~1Ac4I~//~9B03R~
//        if (Dump.Y) Dump.println("setWWifiDirectState parmenable="+Penable);//~1Ac4I~//~9B03R~
////      wifiApManager mgr=SWDA.new wifiApManager();                //~1Ac4I~//~9729R~//~9B03R~
//        wifiApManager mgr=AG.aWDA.new wifiApManager();             //~9729I~//~9B03R~
//        if (mgr.swNoMethod)                                        //~1Ac4I~//~9B03R~
//            return false;                                          //~1Ac4I~//~9B03R~
//        return mgr.setState(Penable);                              //~1Ac4I~//~9B03R~
//    }                                                              //~1Ac4I~//~9B03R~
//    class wifiApManager                                            //~1Ac4I~//~9B03R~
//    {                                                              //~1Ac4I~//~9B03R~
//        private final WifiManager wifiManager;                     //~1Ac4I~//~9B03R~
//        private Method ctlMethod,cfgMethod,statMethod;             //~1Ac4I~//~9B03R~
//        public boolean swNoMethod;                                 //~1Ac4I~//~9B03R~
//        public wifiApManager()                                     //~1Ac4I~//~9B03R~
//        {                                                          //~1Ac4I~//~9B03R~
//            wifiManager=(WifiManager)AG.context.getSystemService(Context.WIFI_SERVICE);//~1Ac4I~//~9B03R~
//            try                                                    //~1Ac4I~//~9B03R~
//            {                                                      //~1Ac4I~//~9B03R~
//                ctlMethod=wifiManager.getClass().getMethod("setWifiApEnabled",WifiConfiguration.class,boolean.class);//~1Ac4I~//~9B03R~
//                cfgMethod=wifiManager.getClass().getMethod("getWifiApConfiguration");//~1Ac4I~//~9B03R~
//                statMethod=wifiManager.getClass().getMethod("getWifiApState");//~1Ac4I~//~9B03R~
//            }                                                      //~1Ac4I~//~9B03R~
//            catch (NoSuchMethodException e)                        //~1Ac4I~//~9B03R~
//            {                                                      //~1Ac4I~//~9B03R~
//                Dump.println(e,"WDA.wifiApManager constructor");   //~1Ac4I~//~9B03R~
//                e.printStackTrace();                               //~1Ac4I~//~9B03R~
//                swNoMethod=true;                                   //~1Ac4I~//~9B03R~
//            }                                                      //~1Ac4I~//~9B03R~
//            if (Dump.Y) Dump.println("WDA.wifiApManager:wifiApManager ctlM="+ctlMethod+",cfgMethod="+cfgMethod+",staeMethod="+statMethod);//~1Ac4I~//~9B03R~
//        }                                                          //~1Ac4I~//~9B03R~
//        private WifiConfiguration getCfg()                         //~1Ac4I~//~9B03R~
//        {                                                          //~1Ac4I~//~9B03R~
//            try                                                    //~1Ac4I~//~9B03R~
//            {                                                      //~1Ac4I~//~9B03R~
//                return (WifiConfiguration)cfgMethod.invoke(wifiManager);//~1Ac4I~//~9B03R~
//            }                                                      //~1Ac4I~//~9B03R~
//            catch(Exception e)                                     //~1Ac4I~//~9B03R~
//            {                                                      //~1Ac4I~//~9B03R~
//                Dump.println(e,"WDA:wifiApManager.getCfg");        //~1Ac4I~//~9B03R~
//            }                                                      //~1Ac4I~//~9B03R~
//            return null;                                           //~1Ac4I~//~9B03R~
//        }                                                          //~1Ac4I~//~9B03R~
//        public boolean setState(boolean Penable)                   //~1Ac4I~//~9B03R~
//        {                                                          //~1Ac4I~//~9B03R~
//            WifiConfiguration cfg=getCfg();                        //~1Ac4I~//~9B03R~
//            if (cfg!=null)                                         //~1Ac4I~//~9B03R~
//            {                                                      //~1Ac4I~//~9B03R~
//                return setState(cfg,Penable);                      //~1Ac4I~//~9B03R~
//            }                                                      //~1Ac4I~//~9B03R~
//            return false;                                          //~1Ac4I~//~9B03R~
//        }                                                          //~1Ac4I~//~9B03R~
//        public boolean setState(WifiConfiguration Pcfg,boolean Penable)//~1Ac4I~//~9B03R~
//        {                                                          //~1Ac4I~//~9B03R~
//            try                                                    //~1Ac4I~//~9B03R~
//            {                                                      //~1Ac4I~//~9B03R~
//                int stat=(Integer)statMethod.invoke(wifiManager);  //~1Ac4I~//~9B03R~
//                if (Dump.Y) Dump.println("WDA.wifiApManager:setState before parm enable="+Penable+",wifi isenable="+wifiManager.isWifiEnabled()+",getstate="+wifiManager.getWifiState()+",apstat="+stat);//~1Ac4I~//~9B03R~
////              wifiManager.setWifiEnabled(!Penable);  //disable wifi case p2p disable//~1Ac4I~//~9B03R~
//                boolean rc=(Boolean)ctlMethod.invoke(wifiManager,Pcfg,Penable);//~1Ac4I~//~9B03R~
//                if (Dump.Y) Dump.println("WDA.wifiApManager:setState after parm enable="+Penable+",wifi isenable="+wifiManager.isWifiEnabled()+",getstate="+wifiManager.getWifiState()+",apstat="+stat);//~1Ac4I~//~9B03R~
//                if (Dump.Y) Dump.println("WDA.wifiApManager:setState rc="+rc);//~1Ac4I~//~9B03R~
//                return rc;                                         //~1Ac4I~//~9B03R~
//            }                                                      //~1Ac4I~//~9B03R~
//            catch(Exception e)                                     //~1Ac4I~//~9B03R~
//            {                                                      //~1Ac4I~//~9B03R~
//                Dump.println(e,"WDA:wifiApManager.getCfg");        //~1Ac4I~//~9B03R~
//            }                                                      //~1Ac4I~//~9B03R~
//            return false;                                          //~1Ac4I~//~9B03R~
//        }                                                          //~1Ac4I~//~9B03R~
//    }                                                              //~1Ac4I~//~9B03R~
////***************************************************************  //~1Ac4I~//~9B03R~
////*disable wifidirect 2'nd way                                     //~1Ac4I~//~9B03R~
////***************************************************************  //~1Ac4I~//~9B03R~
//    public static boolean setWifiP2pState(boolean Penable)         //~1Ac4I~//~9B03R~
//    {                                                              //~1Ac4I~//~9B03R~
//        boolean rc=false;                                          //~1Ac4I~//~9B03R~
//        if (Dump.Y) Dump.println("setWifiP2pState parmenable="+Penable);//~1Ac4I~//~9B03R~
////      WifiP2pManager mgr=SWDA.aWDActivity.manager;               //~1Ac4I~//~9729R~//~9B03R~
//        WifiP2pManager mgr=AG.aWDA.aWDActivity.manager;            //~9729I~//~9B03R~
////      Channel channel=SWDA.aWDActivity.channel;                  //~1Ac4I~//~9729R~//~9B03R~
//        Channel channel=AG.aWDA.aWDActivity.channel;               //~9729I~//~9B03R~
//        Method method;                                             //~1Ac4I~//~9B03R~
//        try                                                        //~1Ac4I~//~9B03R~
//        {                                                          //~1Ac4I~//~9B03R~
//            if (Penable)                                           //~1Ac4I~//~9B03R~
//                method=mgr.getClass().getMethod("enableP2p",Channel.class);//~1Ac4I~//~9B03R~
//            else                                                   //~1Ac4I~//~9B03R~
//                method=mgr.getClass().getMethod("disableP2p",Channel.class);//~1Ac4I~//~9B03R~
//            method.invoke(mgr,channel);                            //~1Ac4I~//~9B03R~
//            rc=true;                                               //~1Ac4I~//~9B03R~
//        }                                                          //~1Ac4I~//~9B03R~
//        catch (Exception e)                                        //~1Ac4I~//~9B03R~
//        {                                                          //~1Ac4I~//~9B03R~
//            Dump.println(e,"WDA:setWifiP2pState");                 //~1Ac4I~//~9B03R~
//            e.printStackTrace();                                   //~1Ac4I~//~9B03R~
//        }                                                          //~1Ac4I~//~9B03R~
//        return rc;                                                 //~1Ac4I~//~9B03R~
//    }                                                              //~1Ac4I~//~9B03R~
//    //************************************************************ //~9722I~//~9B03R~
//    @Override //UEditTextI                                         //~9722I~//~9B03R~
//    public void onTextChanged(UEditText Pedittext,String Ptext)    //~9722I~//~9B03R~
//    {                                                              //~9722I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.onTextChanged text="+Pedittext);//~9722I~//~9A09R~//~9B03R~
////      BTCDialog.getYourName(etYourName,false);    //update AG.YourName//~9722R~//~9A09R~//~9B03R~
//        if (!getYourName()) //update AG.YourName                   //~9A09R~//~9B03R~
//            return;                                                //~9A09I~//~9B03R~
////      AG.aBTMulti.sendMsg(BTMulti.MSGID_NEWNAME,Ptext);          //~9722I~//~9B03R~
//        AG.aBTMulti.sendMsg(MSGID_NEWNAME,Ptext);                  //~9722I~//~9B03R~
//    }                                                              //~9722I~//~9B03R~
//    //************************************************************ //~9A09I~//~9B03R~
//    private boolean getYourName()                                  //~9A09R~//~9B03R~
//    {                                                              //~9A09I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.getYourNema");               //~9A09I~//~9B03R~
//        String yourname=etYourName.getText();                     //~9722I~//~9A09I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.getYourName with UeditText yourname="+yourname);//~9722I~//~9A09I~//~9B03R~
//        if (yourname.equals(""))                                   //~9722I~//~9A09I~//~9B03R~
//        {                                                          //~9722I~//~9A09I~//~9B03R~
//            UView.showToast(R.string.ErrSpecifyYourname);      //~9722I~//~9A09I~//~9B03R~
//            return false;                                          //~9A09I~//~9B03R~
//        }                                                          //~9722I~//~9A09I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.getYourName="+yourname);//~9722I~//~9A09I~//~9B03R~
//        if (!yourname.equals(AG.YourName))                         //~9722I~//~9A09I~//~9B03R~
//        {                                                          //~9722I~//~9A09I~//~9B03R~
//            AG.aBTMulti.BTGroup.updateYourName(AG.YourName,yourname);           //~9905I~//~9A09I~//~9B03R~
//            AG.YourName=yourname;                                  //~9722I~//~9A09I~//~9B03R~
//            Utils.putPreference(PREFKEY_YOURNAME,yourname);        //~9722I~//~9A09I~//~9B03R~
//            displayGroup();                                         //~9905I~//~9A09R~//~9B03R~
//        }                                                          //~9722I~//~9A09I~//~9B03R~
//        return true;                                               //~9A09I~//~9B03R~
//    }                                                              //~9A09I~//~9B03R~
//    //******************************************                   //~9A09I~//~9B03R~
//    private void displayGroup()                                    //~9A09I~//~9B03R~
//    {                                                              //~9A09I~//~9B03R~
//        groupList.displayGroup();                                  //~9A09I~//~9B03R~
//    }                                                              //~9A09I~//~9B03R~
//    //*******************************************************************************************************//~9722I~//~9B03R~
//    //*onClient,From IPConnectionWD.getServer-->not used                                          //~9722I~//~9728R~//~9B03R~
//    //*set Name,NameRemote,isPaired                                //~9729I~//~9B03R~
//    //*******************************************************************************************************//~9722I~//~9B03R~
//    public ConnectionData getPairingInfo()                         //~9722R~//~9B03R~
//    {                                                              //~9722I~//~9B03R~
//        ConnectionData cd=deviceListFragment.getSelectedDeviceData(); //get Name NameRemote, and isPaired only//~9727R~//~9729R~//~9B03R~
//        if (cd==null)                                              //~9727I~//~9B03R~
//            return null;                                           //~9727I~//~9B03R~
//        cd.strAddress=deviceListFragment.thisDeviceAddr; //MacAddr          //~9722R~//~9723M~//~9728R~//~9B03R~
//        cd.strOwnerAddress=deviceDetailFragment.ownerIPAddress;      //~9722R~//~9B03R~
//        cd.isOwner=deviceListFragment.thisOwner==1;                //~9722I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.getPairingInfo vonnectionData="+cd.toString());//~9729R~//~9B03R~
//        return cd;                                                 //~9722I~//~9B03R~
//    }                                                              //~9722I~//~9B03R~
    //******************************************                   //~v@@@I~//~9723I~//~9B03R~
    public static boolean isShowing()                             //~v@@@R~//~9723I~//~9B03R~
    {                                                              //~v@@@I~//~9723I~//~9B03R~
        boolean rc=Utils.isShowingDialogFragment(AG.aWDAR);             //~9709I~//~9723I~//~9B03R~
        if (Dump.Y) Dump.println("WDAR.isShowing rc="+rc+",AG.aWDAR="+Utils.toString(AG.aWDAR));    //~9709I~//~9723I~//~9B03R~//~9B07R~
        return rc;                                                 //~9709R~//~9723I~//~9B03R~
    }                                                              //~v@@@I~//~9723I~//~9B03R~
//    //*******************************************************************************************************//~9723I~//~9B03R~
//    //*From IPMulti.onConnected                                    //~9723I~//~9725R~//~9B03R~
//    //*******************************************************************************************************//~9723I~//~9B03R~
//    public static void onConnected(String Pdevicename,String Paddr,Boolean Pswclient)//~v@@@I~//~9723I~//~9B03R~
//    {                                                              //~v@@@I~//~9723I~//~9B03R~
//    //************************                                     //~v@@@I~//~9723I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.onConnected name="+Pdevicename+",addr="+Paddr+",swclient="+Pswclient);//~v@@@I~//~9723I~//~9B03R~
//        if (!isShowing())                                          //~v@@@I~//~9723I~//~9B03R~
//            return;                                                //~v@@@I~//~9723I~//~9B03R~
//        WDA dlg=AG.aWDA;                                           //~9723I~//~9B03R~
//        dlg.onConnected(0,Pdevicename,Paddr,Pswclient);            //~v@@@I~//~9723I~//~9B03R~
//    }                                                              //~v@@@I~//~9723I~//~9B03R~
//    //*******************************************************************************************************//~9815I~//~9B03R~
//    //*From IPMulti.receivedNameAdd                                //~9815I~//~9B03R~
//    //*******************************************************************************************************//~9815I~//~9B03R~
//    public static void onConnectionChanged()                       //~9815I~//~9B03R~
//    {                                                              //~9815I~//~9B03R~
//    //************************                                     //~9815I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.onConnectionChanged");       //~9815I~//~9B03R~
//        if (!isShowing())                                          //~9815I~//~9B03R~
//            return;                                                //~9815I~//~9B03R~
//        WDA dlg=AG.aWDA;                                           //~9815I~//~9B03R~
//        dlg.updateDialog();                                        //~9815I~//~9B03R~
//    }                                                              //~9815I~//~9B03R~
//    //*******************************************************************************************************//~9723I~//~9B03R~
//    private void onConnected(int Popt,String Pdevicename,String Paddr,Boolean Pswclient)//~v@@@I~//~9723I~//~9B03R~
//    {                                                              //~v@@@I~//~9723I~//~9B03R~
//    //************************                                     //~v@@@I~//~9723I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.onConnected name="+Pdevicename+",addr="+Paddr+",swclient="+Pswclient);//~v@@@I~//~9723I~//~9B03R~
////        ProgDlg.dismissCurrent();                                  //~v@@@I~//~9723I~//~9B03R~
////        if (connectionStatus==CS_ACCEPTING)                       //~v@@@I~//~9723I~//~9B03R~
////            connectionType=ROLE_SERVER;                               //~v@@@I~//~@002R~//~9723I~//~9B03R~
////        else                                                       //~v@@@I~//~9723I~//~9B03R~
////        if (connectionStatus==CS_CONNECTING)                       //~v@@@I~//~9723I~//~9B03R~
////        {                                                          //~v@@@I~//~9723I~//~9B03R~
////            connectionType=ROLE_CLIENT;                               //~v@@@I~//~@002R~//~9723I~//~9B03R~
////            connectionStatus=CS_CONNECTED;                         //~v@@@I~//~9723I~//~9B03R~
////        }                                                          //~v@@@I~//~9723I~//~9B03R~
////        else                                                       //~v@@@I~//~9723I~//~9B03R~
////        {                                                          //~v@@@I~//~9723I~//~9B03R~
////            UView.showToast("BTCDialog.onConnected connectionStatus invalid");  ////~v@@@I~//~9723I~//~9B03R~
////            return;                                                //~v@@@I~//~9723I~//~9B03R~
////        }                                                          //~v@@@I~//~9723I~//~9B03R~
////        if (Pswclient!=(connectionType==ROLE_CLIENT))                //~v@@@I~//~@002R~//~9723I~//~9B03R~
////        {                                                          //~v@@@I~//~9723I~//~9B03R~
////            UView.showToast("BTCDialog.onConnected Role(Server or Client) Unmatch");//~v@@@R~//~9723I~//~9B03R~
////            return;                                                //~v@@@I~//~9723I~//~9B03R~
////        }                                                          //~v@@@I~//~9723I~//~9B03R~
////      addMember(Pdevicename,Paddr);                              //~v@@@R~//~9723I~//~9B03R~
//        updateDialog();                                             //~v@@@I~//~9723I~//~9B03R~
//    }                                                              //~9723I~//~9B03R~
//    //*******************************************************************************************************//~9723I~//~9B03R~
//    private void updateDialog()                                    //~9723I~//~9B03R~
//    {                                                              //~9723I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.updateDialog");              //~9723I~//~9B03R~
//        if (AG.isMainThread())                                     //~9723I~//~9B03R~
//        {                                                          //~9723I~//~9B03R~
//            if (Dump.Y) Dump.println("WDA.updateDialog mainThread");//~9723I~//~9B03R~
//            updateDialogSub();                              //~9723I~//~9724R~//~9B03R~
//            return;                                                //~9723I~//~9B03R~
//        }                                                          //~9723I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.updateDialog subThread");    //~9723I~//~9B03R~
//        AG.activity.runOnUiThread(                                 //~9723I~//~9B03R~
//            new Runnable()                                         //~9723I~//~9B03R~
//                {                                                  //~9723I~//~9B03R~
//                    @Override                                      //~9723I~//~9B03R~
//                    public void run()                              //~9723I~//~9B03R~
//                    {                                              //~9723I~//~9B03R~
//                        if (Dump.Y) Dump.println("WDA.updateDialog runonUiThread");//~9723I~//~9B03R~
//                        try                                        //~9724I~//~9B03R~
//                        {                                          //~9724I~//~9B03R~
//                            updateDialogSub();                  //~9723I~//~9724R~//~9B03R~
//                        }                                          //~9724I~//~9B03R~
//                        catch(Exception e)                         //~9724I~//~9B03R~
//                        {                                          //~9724I~//~9B03R~
//                            Dump.println(e,"WDA:updateDialog.runOnUIThread.run");//~9724I~//~9B03R~
//                        }                                          //~9724I~//~9B03R~
//                    }                                              //~9723I~//~9B03R~
//                });                                                //~9723I~//~9B03R~
//    }                                                              //~v@@@I~//~9723I~//~9B03R~
//    //*****************************                                //~9724I~//~9B03R~
//    private void updateDialogSub()                                 //~9724I~//~9B03R~
//    {                                                              //~9724I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.updateDialogSub");           //~9724I~//~9B03R~
//        deviceListFragment.drawListView();                         //~9724I~//~9B03R~
//        groupList.updateDialog();                                  //~9724I~//~9B03R~
//        updateButtonView(deviceListFragment.thisOwner==1);         //~9728I~//~9B03R~
//        if (statusMsg!=null)                                       //~9729I~//~9B03R~
//        {                                                          //~9729I~//~9B03R~
//            deviceDetailFragment.setStatus(statusErr,statusMsg);    //clear err msg//~9729I~//~9B03R~
//            statusMsg=null;                                        //~9729I~//~9B03R~
//        }                                                          //~9729I~//~9B03R~
//    }                                                              //~9724I~//~9B03R~
//    //*****************************                                //~9724I~//~9B03R~
//    @Override                                                      //~9724I~//~9B03R~
//    public void onDismissDialog()                                  //~9724I~//~9B03R~
//    {                                                              //~9724I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA:onDismissDialog");           //~9724I~//~9B03R~
////      cancelDiscover();                                          //~9724R~//~9B03R~
//        aWDActivity.unregisterReceiver();                          //~1A65I~//~9725M~//~9B03R~
//        IPC.dismissWDA();                                          //~1A67I~//~9725M~//~9B03R~
//        boolean enable=(swOwner)&&(AG.aBTMulti.BTGroup.getConnectedCtr()!=0);//~9724I~//~9B03R~
//        AG.aMainView.enableStartGame(enable);                      //~9724I~//~9B03R~
//        AG.aWDA=null;                                              //~9724M~//~9B03R~
//    }                                                              //~9724I~//~9B03R~
//    //********************************************************************//~9729I~//~9B03R~
//    //*from IPMulti                                                //~9729I~//~9B03R~
//    //********************************************************************//~9729I~//~9B03R~
//    public static void connectionLost(boolean PswServer,String PlocalDeviceName,String PremoteDeviceName)//~9729R~//~9B03R~
//    {                                                              //~9729I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.connectionLost swServer="+PswServer+",local="+PlocalDeviceName+",remote="+PremoteDeviceName);//~9729I~//~9B03R~
//        if (AG.aWDA==null)                                         //~9729I~//~9B03R~
//            return;                                                //~9729I~//~9B03R~
//        AG.aWDA.onDisconnected(PswServer,PlocalDeviceName,PremoteDeviceName);//~9729I~//~9B03R~
//    }                                                              //~9729I~//~9B03R~
//    //********************************************************************//~9729I~//~9B03R~
//    public void onDisconnected(boolean PswServer,String PlocalDeviceName,String PremoteDeviceName)//~9729I~//~9B03R~
//    {                                                              //~9729I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.onDisconnected swServer="+PswServer+",local="+PlocalDeviceName+",remote="+PremoteDeviceName);//~9729I~//~9B03R~
//        statusMsg=Utils.getStr(R.string.InfoDisconnected,PremoteDeviceName);//~9729I~//~9B03R~
//        statusErr=true;                                            //~9729I~//~9B03R~
//        updateDialog();                                            //~v@@@I~//~9729I~//~9B03R~
//    }                                                              //~9729I~//~9B03R~
//    //********************************************************************//~9729I~//~9B03R~
//    public static void updateDialog(boolean PswErr,String Pmsg)    //~9729I~//~9B03R~
//    {                                                              //~9729I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.showStatus swErr="+PswErr+",msg="+Pmsg);//~9729I~//~9B03R~
//        if (AG.aWDA==null)                                         //~9729I~//~9B03R~
//            return;                                                //~9729I~//~9B03R~
//        AG.aWDA.statusErr=PswErr;                                  //~9729I~//~9B03R~
//        AG.aWDA.statusMsg=Pmsg;                                    //~9729I~//~9B03R~
//        AG.aWDA.updateDialog();                                    //~9729I~//~9B03R~
//    }                                                              //~9729I~//~9B03R~
//    //********************************************************************//~9816I~//~9B03R~
//    public void clearStatusMsg()                                   //~9816I~//~9B03R~
//    {                                                              //~9816I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.clearStatusMsg");            //~9816I~//~9B03R~
//        getDeviceDetailFragment().setStatus(false/*swErr*/,"");//~@@@@I~//~9816I~//~9B03R~
//        GMsg.clearMsgbar();                                        //~9816M~//~9B03R~
//    }                                                              //~9816I~//~9B03R~
//    //********************************************************************//~9A03I~//~9B03R~
//    public void enableCBWantGroupOwner(boolean Penable)     //paired at scan//~9A03R~//~9B03R~
//    {                                                              //~9A03I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.enableCBWantGroupOwner sw="+Penable);//~9A03R~//~9B03R~
//        cbWantGroupOwner.setEnabled(Penable);                      //~9A03I~//~9B03R~
//    }                                                              //~9A03I~//~9B03R~
//    //********************************************************************//~9A03I~//~9B03R~
//    public void enableCBWantGroupOwner(boolean Penable,boolean Powner)  //paired at scan//~9A03I~//~9B03R~
//    {                                                              //~9A03I~//~9B03R~
//        if (Dump.Y) Dump.println("WDA.enableCBWantGroupOwner sw="+Penable+",owner="+Powner);//~9A03I~//~9B03R~
//        cbWantGroupOwner.setState(Powner);                         //~9A03I~//~9B03R~
//        enableCBWantGroupOwner(Penable);    //paired at scan//~9A03I~//~9B03R~
//    }                                                              //~9A03I~//~9B03R~
    //******************************************                   //~9B04I~
    private boolean restartGame()                                     //~9B04I~//~0116R~
    {                                                              //~9B04I~
        if (Dump.Y) Dump.println("WDAR:restartGame");              //~9B04I~
		if (!AG.aBTMulti.chkMemberReconnect())             //~v@01I~ //~0116R~
        {                                                          //~v@01I~//~0116I~
        	UView.showToast(R.string.Err_LackingMemberReconnect);  //~0116I~
            return false;                                                //~v@01I~//~0116R~
        }                                                          //~v@01I~//~0116I~
        if (Status.isStatusRestarted())                            //~0218I~
        {                                                          //~0218I~
        	UView.showToast(R.string.Err_AlreadyRestarted);        //~0218I~
            return false;                                          //~0218I~
        }                                                          //~0218I~
    	AG.aUARestart.stopAutoTakeDiscardReset();                  //~9B04I~
//      UserAction.showInfoAll(0,R.string.Info_GameRestarted);     //~9B04I~//~0115R~
		return true;                                               //~0116I~
    }                                                              //~9B04I~
    //******************************************                   //~9B04I~
    private void suspendGame()                                     //~9B04I~
    {                                                              //~9B04I~
        if (Dump.Y) Dump.println("WDAR:restartGame");              //~9B04I~
		if (AG.aAccounts.isServer()                                //~9B04I~
        ||  AG.aBTMulti.BTGroup.getConnectedCtr()==0)              //~9B04I~
//      	SuspendIOErrDlg.newInstance().show();                  //~9B04I~//~0110R~
        	SuspendIOErrDlg.newInstance_Show();                    //~0110I~
        else                                                       //~9B04I~
        	UView.showToast(R.string.Info_IOErrRecovered);         //~9B04I~
    }                                                              //~9B04I~
}
