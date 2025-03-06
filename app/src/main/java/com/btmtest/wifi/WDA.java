//*CID://+vaz9R~:                             update#=  266;       //+vaz9R~
//*************************************************************************//~1A65I~
//2025/03/05 vaz9 at App Finish, bypass Disconnected alert. App exit soon, so unexpected effect may be by dislog existing.//+vaz9I~
//2023/01/25 vavi close channel expecting to erase groupOwner persistency//~vaviI~
//2023/01/25 vavh avoid duplicated getView call for groupList      //~vavhI~
//2023/01/22 vav9 display not devicename but username on connection dialog//~vav9I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//2020/10/19 va1b (Bug)server crashes by @@add from client because thread=null; BTCDialog EeditText textchange listener is called by Button push by focus change.//~va1bI~
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


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pDevice;                       //~1A65R~
import android.net.wifi.WifiManager;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.btmtest.BT.GroupList;
import com.btmtest.BT.Members;
import com.btmtest.R;
import com.btmtest.dialog.AxeDlg;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.UEditText;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import com.btmtest.game.gv.GMsg;                                   //~9816I~
//~9721I~
import static com.btmtest.AG.*;
import static com.btmtest.BT.Members.*;
import static com.btmtest.StaticVars.AG;                           //~9721I~
import static com.btmtest.dialog.PrefSettingEnum.*;
import static com.btmtest.game.GConst.*;

//@TargetApi(AG.ICE_CREAM_SANDWICH)   //api14                           //~1A65R~//~9721R~
//public class WiFiDirectActivity extends Activity implements ChannelListener, DeviceActionListener {//~1A65R~
//public class WDA extends AxeDialog                                 //~1A65R~//~9720R~
public class WDA extends AxeDlg                                    //~9720I~
    implements  UEditText.UEditTextI          //~9722R~
    , DialogInterface.OnShowListener                               //~vavhI~
{                                                                  //~1A65I~
//  private static final int LAYOUTID=R.layout.device_wd;          //~1A65I~//~9720R~
    private static final int LAYOUTID= R.layout.wifidialog;         //~9720I~
    private static final int LAYOUTID_SMALLFONT= R.layout.wifidialog_theme;//~vac5I~
//  private static final int LAYOUTID_MDPI=R.layout.device_wd_mdpi;//~1A67I~//~9720R~
    private static final int TITLEID=R.string.DialogTitle_DeviceDetail;//~1A65R~
    private static final int HELP_TITLEID=TITLEID;                 //~9721I~
    protected static final String HELPFILE="WDA";                    //~9720R~//~9C13R~
    private static final int BUTTONS_2ND=R.id.DialogButtonsDetail; //~9720I~
    public static final int ID_ACCEPT=R.id.AcceptIPConnection;    //~1A84I~//~1A90I~
    public static final int ID_STOPACCEPT=R.id.StopAcceptIPConnection;//~1A84I~//~1A90I~
    public static final int ID_CONNECT=R.id.IPConnect;            //~1A84I~//~1A90I~
    public static final int ID_DISCONNECT=R.id.IPDisConnect;       //~1A84R~//~1A90I~
//  public static final int ID_GAME=R.id.IPGame;                  //~1A84I~//~1A90I~//~9720R~
    public static final int ALERT_TITLE_DISCONNECT=R.string.Title_ConfirmDisconnect;//~1A89I~
    public static final int ALERT_MSG_DISCONNECT=R.string.Msg_ConfirmDisconnect;//~1A89I~
//	public static WDA SWDA;                                        //~1A65I~//~9729R~
	public WiFiDirectActivity aWDActivity;                         //~1A65R~
	protected IPConnectionWD IPC;                                      //~1A65R~//~9721R~//~9B03R~
	public  DeviceDetailFragment deviceDetailFragment;             //~1A65I~//~9722R~
	public  DeviceListFragment deviceListFragment;                 //~1A65I~//~9722R~
                                                                   //~1A65I~
//  public static final String TAG = "wifidirectdemo";             //~9722R~
    public boolean swWifiEnable;                                   //~1A65I~
    public String WDAipa,WDApeer;                                  //~1A65R~
	public boolean WDAowner;                                       //~1A65I~
	public boolean swOwner;                                        //~1A84I~//~1A90I~
//    private WDANFC  NFCwaitingDiscover;                            //~1A6aI~//~9721R~
//    private String  NFCwaitingDiscoverAddr;                        //~1A6aI~//~9721R~
//  private UButton btnAccept,btnConnect,/*btnGame,*/btnDisconnect,btnStopAccept;//~1A84R~//~1A90I~//~9720R~
    protected Button btnAccept,btnConnect,/*btnGame,*/btnDisconnect,btnStopAccept;//~9720I~//~9B04R~
//  private boolean swDismissAtClose;                               //~9720I~//~9722R~
	private UEditText etYourName;                                   //~1Aa5I~//~9722M~
    private GroupList groupList;                                   //~1Aa5I~//~9722M~
    private boolean statusErr;                                     //~9729I~
    private String statusMsg;                                      //~9729I~
    public UCheckBox cbWantGroupOwner;                             //~9A03I~
    private boolean swChangedYourName;                                     //~0116I~
    private ListView viewGL;                                       //~vavhI~
	//***********************************************************************************//~9720I~
	public WDA()                                                   //~9720I~
    {                                                              //~9720I~
        if (Dump.Y) Dump.println("WDA:default constructor");       //~9720I~
        AG.aWDA=this;                                              //~9722I~
        AG.aAccName.loadProp();                                    //~vav9R~
        WDI.shownWDA();                                             //~0113R~
    }                                                              //~9720I~
    //******************************************                   //~9B03I~
    public static WDA getWDA()                                     //~9B03I~
    {                                                              //~9B03I~
        WDA dlg=AG.aWDAR!=null ? AG.aWDAR : AG.aWDA;               //~9B03I~
        if (Dump.Y) Dump.println("WDA:getWDA dlg="+dlg.toString());//~9B03I~
        return dlg;                                                //~9B03I~
    }                                                              //~9B03I~
    //******************************************                   //~9B03I~
    public static boolean isReconnecting()                         //~9B03I~
    {                                                              //~9B03I~
        boolean rc=WDAR.isShowing();                               //~9B03I~
        if (Dump.Y) Dump.println("WDA:isReconnecting rc="+rc);     //~9B03I~
        return rc;                                                 //~9B03I~
    }                                                              //~9B03I~
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
    private static WDA newInstance(IPConnectionWD Pipc)               //~9720R~
    {                                                              //~9720I~
        if (Dump.Y) Dump.println("WDA:newInstance");               //~9720R~
        WDA dlg=new WDA();                                         //~9720R~
        dlg.IPC=Pipc;                                              //~9720I~
//      AxeDlg.newInstance(dlg,TITLEID,LAYOUTID,HELPFILE);         //~9720I~//~vac5R~
        AxeDlg.newInstance(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),HELPFILE);//~vac5I~
        return dlg;                                                //~9720I~
    }                                                              //~9720I~
    //***********************************************************************//~9724I~
    //*from IPConnection.startWD<--WDI.startRemoteIP<--MenuDialogConnect//~9724I~
    //***********************************************************************//~9724I~
    public static WDA showDialog(IPConnectionWD Pipc)                //~9720I~
    {                                                              //~9720I~
        if (Dump.Y) Dump.println("WDA:showDialog");                //~9720I~
        WDA dlg=newInstance(Pipc);                                 //~9720I~
        dlg.initWifi();                                            //~9720I~
        if (!dlg.swWifiEnable)                                     //~9720I~
            return null;                                           //~9720I~
        ((AxeDlg)dlg).showDialog(null);                                      //~9720I~
        return dlg;                                                //~9720I~
    }                                                              //~9720I~
//    //***********************************************************************************//~1A65I~//~9720R~
//    public WDA(IPConnection Pipc)                                  //~1A65R~//~9720R~
//    {                                                              //~1A65I~//~9720R~
////      super((AG.screenDencityMdpiSmallV || AG.screenDencityMdpiSmallH/*mdpi and height or width <=320*/ ? LAYOUTID_MDPI : LAYOUTID));//~1A67I~//~9720R~
//        super(LAYOUTID);                                         //~9720R~
//        IPC=Pipc;                                                  //~1A65R~//~9720R~
//        if (AG.osVersion>=AG.ICE_CREAM_SANDWICH)  //android4          //~1A65I~//~1A6aR~//~9720R~
//            init_ICE_CREAM_SANDWICH();                             //~1A65I~//~9720R~
//        else                                                       //~1A65I~//~9720R~
//            init_deprecated();                                     //~1A65I~//~9720R~
//    }                                                              //~1A65I~//~9720R~
//    //***********************************************************************************//~1A65M~//~9720R~
//    private void init_deprecated()                                 //~1A65R~//~9720R~
//    {                                                              //~1A65I~//~9720R~
//        SWDA=null;                                                 //~1A65I~//~9720R~
//    }                                                              //~1A65I~//~9720R~
	//***********************************************************************************//~1A65I~
    protected void initWifi()                                        //~9720I~//~9B03R~
    {                                                              //~9720I~
		init_ICE_CREAM_SANDWICH();                                 //~9720I~
    }                                                              //~9720I~
	//***********************************************************************************//~9720I~
//    @TargetApi(AG.ICE_CREAM_SANDWICH)                                 //~1A65I~//~9720R~
    private void init_ICE_CREAM_SANDWICH()                         //~1A65I~
    {                                                              //~1A65I~
//      SWDA=this;                                                 //~1A65I~//~9729R~
    	aWDActivity=new WiFiDirectActivity();                      //~1A65R~
        deviceListFragment=new DeviceListFragment();               //~1A65I~
        deviceDetailFragment=new DeviceDetailFragment();           //~1A65I~
      swWifiEnable=                                                //~1A84I~//~1A90I~
        enableWiFi();                                              //~1A65I~
        if (Dump.Y) Dump.println("WDA:showDialog init_ICE_CREAM_SANDWICH swWifiEnable="+swWifiEnable);//~1Ad2I~
//      aWDActivity.registerReceiver();                            //~1A65I~//~9720R~
    }                                                              //~1A65I~
	//***********************************************************************************//~1A65I~
    public static boolean enableWiFi()                             //~1A6CI~
    {                                                              //~1A6CI~
        if (AG.osVersion>=AG.ICE_CREAM_SANDWICH)  //android4       //~1A6CI~
        	return enableWiFi_ICE_CREAM_SANDWICH();              //~1A6CI~
        return false;                                              //~1A6CI~
    }                                                              //~1A6CI~
	//***********************************************************************************//~1A6CI~
//  @TargetApi(AG.ICE_CREAM_SANDWICH)                              //~1A65I~//~9721R~
//  private boolean enableWiFi()                                   //~1A65I~//~1A6CR~
    private static boolean enableWiFi_ICE_CREAM_SANDWICH()         //~1A6CI~
    {                                                              //~1A65I~
    	boolean rc=true;                                           //~1A65I~
    //**********************                                       //~1A65I~
    	WifiManager wm=(WifiManager) AG.context.getSystemService(Context.WIFI_SERVICE);//~1A65R~
        if (Dump.Y) Dump.println("WDA:enableWiFi_ICE_CREAM_SANDWITCH isWifiEnabled="+wm.isWifiEnabled());//~1Ac4I~//~1Ad2R~
//        if (true)                      //@@@@test                //~1Ac4I~
//        {                                                        //~1Ac4I~
//            setWifiDirectStatus(true);   //@@@@test              //~1Ac4I~
////          setWifiP2pState(true);  //enableP2p @@@@test         //~1Ac4I~
//        }                                                        //~1Ac4I~
//        else                                                     //~1Ac4I~
        if (!wm.isWifiEnabled())                                   //~1A65I~
        {                                                          //~1A65I~
        	if (Dump.Y) Dump.println("WDA:enableWiFi_ICE_CREAM_SANDWITCH isWifiEnabled:false");//~va40I~
//      	if (wm.setWifiEnabled(true))//success                  //~1A65I~//~va40R~
        	if (setWifiEnabled(wm,true))                           //~va40I~
				UView.showToastLong(R.string.InfoWifiEnabled);            //~1A65I~//~9721R~
            else                                                   //~1A65I~
            {                                                      //~1A65I~
				UView.showToastLong(R.string.ErrWifiEnable);              //~1A65I~//~9721R~
                rc=false;                                          //~1A65I~
            }                                                      //~1A65I~
        }                                                          //~1A65I~
        if (Dump.Y) Dump.println("enableWiFi rc="+rc);             //~1A65I~
//      swWifiEnable=rc;                                           //~1A65I~//~1A6CR~
//        if (SWDA!=null)                                            //~1A6CI~//~1A90R~
//            SWDA.swWifiEnable=rc;                                  //~1A6CI~//~1A90R~
        return rc;                                                 //~1A65I~
    }                                                              //~1A65I~
	//***********************************************************************************//~va40I~
    private static boolean setWifiEnabled(WifiManager Pwm,boolean Penable)//~va40I~
    {                                                              //~va40I~
        if (Dump.Y) Dump.println("setWifiEnabled Penable="+Penable);//~va40I~
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q) //api29 android10//~va40I~
			return setWifiEnabled_From29(Pwm,Penable);             //~va40I~
        else                                                       //~va40I~
			return setWifiEnabled_Bellow29(Pwm,Penable);           //~va40I~
    }                                                              //~va40I~
	//***********************************************************************************//~va40I~
	@SuppressWarnings("deprecation")                               //~va40I~
    private static boolean setWifiEnabled_Bellow29(WifiManager Pwm,boolean Penable)//~va40I~
    {                                                              //~va40I~
		boolean rc=Pwm.setWifiEnabled(Penable);                    //~va40I~
        if (Dump.Y) Dump.println("setWifiEnabled_Bellow29 Penable="+Penable+",rc="+rc);//~va40I~
        return rc;                                                 //~va40I~
    }                                                              //~va40I~
	//***********************************************************************************//~va40I~
    private static boolean setWifiEnabled_From29(WifiManager Pwm,boolean Penable)//~va40I~
    {                                                              //~va40I~
		boolean rc=false;	//app could not change                 //~va40I~
        if (Dump.Y) Dump.println("setWifiEnabled_From29 Penable="+Penable+",rc="+rc);//~va40I~
        return rc;                                                 //~va40I~
    }                                                              //~va40I~
	//***********************************************************************************//~1A6CI~
    public static boolean disableWiFi()                            //~1A6CI~
    {                                                              //~1A6CI~
        if (AG.osVersion>=AG.ICE_CREAM_SANDWICH)  //android4       //~1A6CI~
        	return disableWiFi_ICE_CREAM_SANDWICH();               //~1A6CI~
        return false;                                              //~1A6CI~
    }                                                              //~1A6CI~
	//***********************************************************************************//~1A6CI~
//  @TargetApi(AG.ICE_CREAM_SANDWICH)                              //~1A6CI~//~9721R~
    private static boolean disableWiFi_ICE_CREAM_SANDWICH()        //~1A6CR~
    {                                                              //~1A6CI~
    	boolean rc=true;                                           //~1A6CI~
    //**********************                                       //~1A6CI~
    	WifiManager wm=(WifiManager) AG.context.getSystemService(Context.WIFI_SERVICE);//~1A6CI~
        if (Dump.Y) Dump.println("WDA:disableWiFi old="+wm.isWifiEnabled());//~1Ac4I~
        if (wm.isWifiEnabled())                                    //~1A6CI~
        {                                                          //~1A6CI~
//      	if (wm.setWifiEnabled(false))                          //~1A6CI~//~va40R~
        	if (setWifiEnabled(wm,false))                          //~va40I~
				UView.showToastLong(R.string.InfoWifiDisabled);    //~1A6CI~//~9721R~
            else                                                   //~1A6CI~
            {                                                      //~1A6CI~
				UView.showToastLong(R.string.ErrWifiDisable);      //~1A6CI~//~9721R~
                rc=false;                                          //~1A6CI~
            }                                                      //~1A6CI~
        }                                                          //~1A6CI~
        if (Dump.Y) Dump.println("disnableWiFi rc="+rc);           //~1A6CI~
//  	if (SWDA!=null)                                            //~1A6CI~//~9729R~
//          SWDA.swWifiEnable=!rc;                                 //~1A6CI~//~9729R~
    	if (AG.aWDA!=null)                                         //~9729I~
            AG.aWDA.swWifiEnable=!rc;                              //~9729I~
        return rc;                                                 //~1A6CI~
    }                                                              //~1A6CI~
	//***********************************************************************************//~1A65I~
    @Override                                                      //~1A65I~
//  protected void setupDialogExtend(ViewGroup PlayoutView)        //~1A65I~//~9720R~
    protected void setupDialogExtend(View PlayoutView)             //~9720I~
    {                                                              //~1A65I~
//  	if (SWDA==null)                                            //~1A65R~//~9729R~
//      	return;                                                //~1A65I~//~9729R~
        if (Dump.Y) Dump.println("WDA:setupDialogExtended");       //~9B07I~
	    resetMembersMode(AG.aBTMulti.BTGroup);                      //~9817I~
        deviceListFragment.initFragment(PlayoutView);              //~1A65I~
        deviceDetailFragment.initFragment(PlayoutView);            //~1A65I~
		etYourName=UEditText.bind(PlayoutView,R.id.YourName,this);//~1Aa5I~//~9722I~
	    cbWantGroupOwner=new UCheckBox(PlayoutView,R.id.cbWantGroupOwner);//~9A03I~
    	etYourName.setText(AG.YourName,true/*swLostFocus*/);       //~9A09I~
//      if (isReconnecting())   //not yet showing                  //~9B06I~//~9B07R~
        if (AG.aWDAR!=null)                                        //~9B07I~
        {                                                          //~9B07I~
		    enableCBWantGroupOwner(false/*Penable*/,AG.aAccounts.isServer());	//same role as initial connect//~9B06I~//~9B07M~
        	showYourName(true/*pdisable*/);                        //~9B07I~
        }                                                          //~9B07I~
        groupList=new GroupList();                                 //~1Aa5I~//~9722M~
        groupList.init(PlayoutView,etYourName);	//tell views                    //~1Aa5I~//~9722M~//~9723R~
        groupList.updateDialog();                                  //~9722I~
        setButtonListenerAll(BUTTONS_2ND);                         //~1A65R~
//        btnAccept=new UButton(layoutView,ID_ACCEPT);               //~1A84I~//~1A90I~//~9720R~
//        btnStopAccept=new UButton(layoutView,ID_STOPACCEPT);       //~1A84I~//~1A90I~//~9720R~
//        btnConnect=new UButton(layoutView,ID_CONNECT);             //~1A84I~//~1A90I~//~9720R~
//        btnDisconnect=new UButton(layoutView,ID_DISCONNECT);       //~1A84I~//~1A90I~//~9720R~
        btnAccept=UButton.bind(PlayoutView,ID_ACCEPT,null);        //~9720R~
        btnStopAccept= UButton.bind(PlayoutView,ID_STOPACCEPT,null);//~9720R~
        btnConnect=UButton.bind(PlayoutView,ID_CONNECT,null);      //~9720R~
        btnDisconnect=UButton.bind(PlayoutView,ID_DISCONNECT,null);//~9720R~
//  	btnGame=new UButton(layoutView,ID_GAME);                   //~1A84I~//~1A90I~//~9720R~
	    updateButtonView(false/*owner*/);                          //~1A84R~//~1A90I~
//      aWDActivity.registerReceiver();                            //~9720I~//~9724R~
		chkIPConnection();                                         //~0117I~
        setOnShowListener(PlayoutView);                            //~vavhR~
    }                                                              //~1A65I~
    //******************************************                   //~0117I~
    private void chkIPConnection()                                 //~0117I~
	{                                                              //~0117I~
        if (Dump.Y) Dump.println("WDA.chkIPConnection");           //~0117I~
        Members m=AG.aBTMulti.BTGroup;                              //~v@@@I~//~0117I~
        if (m!=null)                                               //~v@@@I~//~0117I~
        {                                                          //~v@@@I~//~0117I~
        	int[] ctrs=new int[2];                                 //~v@@@I~//~0117I~
        	m.getConnectionModeCtr(ctrs);                          //~v@@@I~//~0117I~
            if (ctrs[CM_WD]==0)                                    //~v@@@I~//~0117I~
				AG.RemoteStatus=RS_IP;                             //~0117I~
            else                                                   //~0117I~
				AG.RemoteStatus=RS_IPCONNECTED;                    //~0117I~
        }                                                          //~v@@@I~//~0117I~
    }                                                              //~0117I~
    //******************************************                   //~9B07I~
    //*callback from GroupList by updateDialog->displayGroup       //~9B07I~
    //******************************************                   //~9B07I~
    public static void showYourName(UEditText PetYourName,int PmemberCtr)//~9B07I~
	{                                                              //~9B07I~
        if (Dump.Y) Dump.println("WDA.showYourName PmemberCtr="+PmemberCtr);//~9B07I~
    	if (AG.aWDA!=null)                                         //~9B07I~
	        AG.aWDA.showYourName(PmemberCtr!=0 || AG.aWDAR!=null); //disable when connected to anyone//~9B07R~
    }                                                              //~9B07I~
    //******************************************                   //~9B07I~
    public void showYourName(boolean Pdisable)                     //~9B07R~
	{                                                              //~9B07I~
        if (Dump.Y) Dump.println("WDA.showYourName Pdisable="+Pdisable);//~9B07I~
        if (Pdisable) //disable when connected to anyone           //~9B07I~
        {                                                          //~9B07I~
//      	int colorEditableDisabled=AG.resource.getColor(COLOR_EDITABLE_DISABLED);//~va40R~
        	int colorEditableDisabled=AG.getColor(COLOR_EDITABLE_DISABLED);//~va40I~
	        etYourName.editText.setBackgroundColor(colorEditableDisabled);//~9B07I~
	        etYourName.editText.setEnabled(false);                 //~9B07I~
	        etYourName.editText.setTextColor(Color.BLACK);         //~9B07I~
        }                                                          //~9B07I~
        else                                                       //~9B07I~
        {                                                          //~9B07I~
//      	int colorEditable=AG.resource.getColor(COLOR_EDITABLE);//~va40R~
        	int colorEditable=AG.getColor(COLOR_EDITABLE);         //~va40I~
	        etYourName.editText.setBackgroundColor(colorEditable); //~9B07I~
	        etYourName.editText.setEnabled(true);                  //~9B07I~
        }                                                          //~9B07I~
    }                                                              //~9B07I~
    //******************************************                   //~9817I~
    private void resetMembersMode(Members Pmembers)                //~9817I~
    {                                                              //~9817I~
        if (Dump.Y) Dump.println("WDA:resetMembersMode");          //~9817I~
        Pmembers.resetMode(CM_WD);                                 //~9817I~
    }                                                              //~9817I~
//    //**********************************                           //~1A65I~//~1A90R~
//    @Override                                                      //~1A65I~//~1A90R~
//    protected boolean onClickClose()                                    //~1A65I~//~1A90R~
//    {                                                              //~1A65I~//~1A90R~
//        boolean rc=false;                                           //~1A65I~//~1A90R~
//        String ipa;                                                //~1A65I~//~1A90R~
//        boolean owner;                                             //~1A65I~//~1A90R~
//    //*****************************                                //~1A65I~//~1A90R~
//        ipa=deviceDetailFragment.ownerIPAddress;                   //~1A65R~//~1A90R~
//        owner=deviceListFragment.thisOwner==1;                     //~1A65R~//~1A90R~
//        if (deviceListFragment.thisStatus==WifiP2pDevice.CONNECTED)//~1A65I~//~1A90R~
//        {                                                          //~1A65I~//~1A90R~
//            if (Dump.Y) Dump.println("WDA:onClickClose connected ipa="+(ipa==null?"null":ipa)+",owner="+owner);//~1A65R~//~1A67R~//~1A90R~
//            if (!owner && ipa!=null)                               //~1A65R~//~1A90R~
//            {                                                      //~1A65I~//~1A90R~
//                UView.showToast(R.string.InfoWDUseOwnerIP,ipa);    //~1A65R~//~1A90R~//~9721R~
//                rc=true;                                           //~1A65I~//~1A90R~
//            }                                                      //~1A65I~//~1A90R~
//            else                                                   //~1A65I~//~1A90R~
//            if (owner)                                             //~1A65R~//~1A90R~
//            {                                                      //~1A65I~//~1A90R~
//                UView.showToast(R.string.InfoWDOpenPort);          //~1A65I~//~1A90R~//~9721R~
//                rc=true;                                           //~1A65I~//~1A90R~
//            }                                                      //~1A65I~//~1A90R~
//        }                                                          //~1A65I~//~1A90R~
//        if (!rc)                                                   //~1A65I~//~1A90R~
//            UView.showToast(R.string.WarningWDNotConnected);       //~1A65I~//~1A90R~//~9721R~
//        else                                                       //~1A65I~//~1A90R~
//        {                                                          //~1A65I~//~1A90R~
//            WDAipa=ipa;                                            //~1A65I~//~1A90R~
//            WDAowner=owner;                                        //~1A65I~//~1A90R~
//            WDApeer=deviceDetailFragment.peerDevice;                  //~1A65I~//~1A90R~
//            if (WDApeer==null)                                     //~1A65I~//~1A90R~
//                WDApeer=WDAipa;                                    //~1A65I~//~1A90R~
////          IPC.doAction(getResourceString(R.string.doAction_WDAOK));//~1A65R~//~1A67R~//~1A90R~
//            IPC.onCloseWDA();                                      //~1A67I~//~1A90R~
//        }                                                          //~1A65I~//~1A90R~
//        return rc;  //not dismiss                                  //~1A65I~//~1A90R~
//    }                                                              //~1A65I~//~1A90R~
    //**********************************                           //~9A09I~
    @Override                                                      //~9A09I~
    protected void onClickClose()                                  //~9A09I~
    {                                                              //~9A09I~
//      if (getYourName())            //set AG.yourName            //~9A09R~//~0322R~
        getYourName();            //set AG.yourName                //~0322I~
		    dismiss();                                             //~9A09R~
    }                                                              //~9A09I~
    //*************************************************                           //~1A84I~//~1A90I~//~9722R~
    //*TODO required?                                              //~9722I~
    //*************************************************            //~9722I~
//  @Override                                                      //~9720I~//~9722R~
//  protected void onClickClose()                               //~1A84R~//~1A90I~//~9720R~//~9722R~
    protected void onClickClose2()                                 //~9722I~
    {                                                              //~1A84I~//~1A90I~
        boolean rc=false;                                          //~1A84I~//~1A90I~//~9720R~
        String ipa;                                                //~1A84I~//~1A90I~
        boolean owner;                                             //~1A84I~//~1A90I~
    //*****************************                                //~1A84I~//~1A90I~
        if (Dump.Y) Dump.println("WDA:onClickClose");              //~9722I~
        ipa=deviceDetailFragment.ownerIPAddress;                   //~1A84I~//~1A90I~
//      owner=deviceListFragment.thisOwner==1;                     //~1A84I~//~1A90I~
        owner=swOwner;                                             //~1A84I~//~1A90I~
        if (deviceListFragment.thisStatus==WifiP2pDevice.CONNECTED)//~1A84I~//~1A90I~
        {                                                          //~1A84I~//~1A90I~
            if (Dump.Y) Dump.println("WDA:onClickClose connected ipa="+(ipa==null?"null":ipa)+",owner="+owner);//~1A84I~//~1A90I~
            if (!owner && ipa!=null)                               //~1A84I~//~1A90I~
            {                                                      //~1A84I~//~1A90I~
                rc=true;                                           //~1A84I~//~1A90I~
            }                                                      //~1A84I~//~1A90I~
            else                                                   //~1A84I~//~1A90I~
            if (owner)                                             //~1A84I~//~1A90I~
            {                                                      //~1A84I~//~1A90I~
                rc=true;                                           //~1A84I~//~1A90I~
            }                                                      //~1A84I~//~1A90I~
        }                                                          //~1A84I~//~1A90I~
        if (!rc)                                                   //~1A84I~//~1A90I~
            UView.showToast(R.string.WarningWDNotConnected);   //~1A84I~//~1A90I~//~9721R~
        else                                                       //~1A84I~//~1A90I~
        {                                                          //~1A84I~//~1A90I~
            WDAipa=ipa;                                            //~1A84I~//~1A90I~
            WDAowner=owner;                                        //~1A84I~//~1A90I~
            WDApeer=deviceDetailFragment.peerDevice;               //~1A84I~//~1A90I~
            if (WDApeer==null)                                     //~1A84I~//~1A90I~
                WDApeer=WDAipa;                                    //~1A84I~//~1A90I~
        }                                                          //~1A84I~//~1A90I~
//      return rc;  //not dismiss                                  //~1A84I~//~1A90I~//~9720R~
        if (rc)                                                    //~9720I~
	        dismiss();                                             //~9720I~
//      swDismissAtClose=rc;                                       //~9720I~//~9722R~
    }                                                              //~1A84I~//~1A90I~
    //**********************************                           //~1A84I~//~1A90I~
    protected void updateButtonView(boolean Powner)                  //~1A84R~//~1A90I~//~9B04R~
    {                                                              //~1A84I~//~1A90I~
        String ipa;                                                //~1A84I~//~1A90I~
        boolean connected,listening,paired;                               //~1A84I~//~1A87R~//~1A90I~
    //*****************************                                //~1A84I~//~1A90I~
		connected=AG.RemoteStatus==AG.RS_IPCONNECTED;	//server and client              //~1A84I~//~1A90I~//~9729R~
		listening=AG.RemoteStatusAccept==AG.RS_IPLISTENING;        //~1A84I~//~1A90I~
		paired=deviceListFragment.thisStatus==WifiP2pDevice.CONNECTED;//~1A87I~//~1A90I~
    	if (Dump.Y) Dump.println("WDA:updateButtonView Powner="+Powner+",paired="+paired+",remotestatus="+AG.RemoteStatus+",remotestatusaccept="+AG.RemoteStatusAccept+",connected="+connected+",listening="+listening);//~1A84R~//~1A90I~//~9727R~//~9729R~//~vaviR~
		if (paired)                                                //~9729I~
        {                                                          //~9729I~
            if (Powner)               //paired server              //~9729I~
            {                                                      //~9729I~
                btnAccept.setVisibility(View.VISIBLE);             //~9729I~
                btnStopAccept.setVisibility(View.VISIBLE);         //~9729I~
                btnConnect.setVisibility(View.GONE);               //~9729I~
                btnDisconnect.setVisibility(View.VISIBLE);         //~9729I~
                if (listening) //owner accepting                   //~9729I~
                {                                                  //~9729I~
                    btnAccept.setEnabled(false);                   //~9729I~
                    btnStopAccept.setEnabled(true);                //~9729I~
                }                                                  //~9729I~
                else                                               //~9729I~
                {                                                  //~9729I~
                    btnAccept.setEnabled(true);                    //~9729I~
                    btnStopAccept.setEnabled(false);               //~9729I~
                }                                                  //~9729I~
                btnDisconnect.setEnabled(connected);               //~9729I~
            }                                                      //~9729I~
            else                                                   //~9729I~
            {                                                      //~9729I~
                btnAccept.setVisibility(View.GONE);                //~9729I~
                btnStopAccept.setVisibility(View.GONE);            //~9729I~
                btnConnect.setVisibility(View.VISIBLE);            //~9729I~
                btnDisconnect.setVisibility(View.VISIBLE);         //~9729I~
                btnConnect.setEnabled(!connected);                 //~9729I~
                btnDisconnect.setEnabled(connected);               //~9729I~
            }                                                      //~9729I~
        }                                                          //~9729I~
        else                                                       //~9729I~
        {                                                          //~9729I~
            btnAccept.setVisibility(View.GONE);                    //~9729I~
            btnStopAccept.setVisibility(View.GONE);                //~9729I~
            btnConnect.setVisibility(View.GONE);                   //~9729I~
            btnDisconnect.setVisibility(View.GONE);                //~9729I~
        }                                                          //~9729I~
    	if (Dump.Y) Dump.println("WDA:updateButtonView btnAccept="+Integer.toHexString(btnAccept.getId())+"="+btnAccept.getText()+",btnStopAccept="+Integer.toHexString(btnStopAccept.getId())+"="+btnStopAccept.getText());//~vaviR~
    }                                                              //~1A84I~//~1A90I~//~9729R~
	                                                               //~9729I~
//    //**********************************                           //~1A65I~//~9721R~
//    @Override                                                      //~1A65I~//~9721R~
//    protected void onClickHelp()                                //~1A65I~//~9720R~//~9721R~
//    {                                                              //~1A65I~//~9721R~
////      new HelpDialog(null,R.string.Help_WDA);                    //~1A65I~//~1Ad2R~//~9721R~
//        new HelpDialog(Global.frame(),R.string.HelpTitle_WDA,"wifidirect");//~1A8rR~//~1Ad2I~//~9721R~
////      return false;   //not dismiss                              //~1A65I~//~9720R~//~9721R~
//    }                                                              //~1A65I~//~9721R~
	//***********************************************************************************//~1A65I~
    @Override                                                      //~1A65I~
    protected void onClickOther(int PbuttonId)                  //~1A65I~//~9720R~
    {                                                              //~1A65I~
        boolean dismiss=false;                                     //~1A65R~
        int rc;                                                    //~1A65I~
    //********************                                         //~1A65I~
    	if (Dump.Y) Dump.println("WDA:onClickOther buttonid="+Integer.toHexString(PbuttonId));//~1A84I~//~1A90I~
	    GMsg.clearMsgbar();	//MainView and GameView                //~0113R~
        clearStatusMsg();                                          //~9816I~
//  	if (SWDA==null)                                            //~1A65R~//~9729R~
//      	return false;                                          //~1A65I~//~9720R~
//      	return;                                                //~9720I~//~9729R~
        if (!getYourName())	//update AG.YourName                   //~9A09R~
        	return;                                                //~9A09I~
        deviceDetailFragment.setStatus(false,R.string.empty);	//clear err msg//~9727I~
        try                                                        //~1A65I~
        {                                                          //~1A65I~
          switch(PbuttonId)                                        //~1A84I~//~1A90I~
          {                                                        //~1A84I~//~1A90I~
          case ID_ACCEPT:                                          //~1A84I~//~1A90I~
          case ID_CONNECT:                                         //~1A84I~//~1A90I~
//        case ID_GAME:                                            //~1A84I~//~1A90I~//~9720R~
          case ID_STOPACCEPT:                                      //~1A84I~//~1A90I~
          case ID_DISCONNECT: //IPDisconnect                                      //~1A84I~//~1A90I~//~9722R~
            dismiss=buttonAction(PbuttonId);                       //~1A84I~//~1A90I~
            break;                                                 //~1A84I~//~1A90I~
          default:                                                 //~1A84I~//~1A90I~
            rc=deviceDetailFragment.buttonAction(PbuttonId);       //~1A65R~
            if (rc<0)   //not button of List                       //~1A65R~
                aWDActivity.buttonAction(PbuttonId);               //~1A65R~
            else                                                   //~1A65R~
                dismiss=(rc==1);                                   //~1A65R~
          }                                                        //~1A84I~//~1A90I~
        }                                                          //~1A65I~
        catch(Exception e)                                         //~1A65I~
        {                                                          //~1A65I~
        	Dump.println(e,"WDA:onClickOther");                    //~1A65I~
        }                                                          //~1A65I~
        if (dismiss)                                               //~9720I~
	        dismiss();                                             //~9720R~
        else                                                       //~9728I~
	        updateButtonView(deviceListFragment.thisOwner==1);     //~9728I~
    }                                                              //~1A65I~
	//***********************************************************************************//~1A65I~
    @Override                                                      //~1A65I~
//  protected void onDismiss()                                     //~1A65I~//~9725R~
    public void onDismiss(DialogInterface Pdialog)                 //~v@@@I~//~9725I~
    {                                                              //~1A65I~
    	if (Dump.Y) Dump.println("WDA:onDismis");                  //~9725I~
    	super.onDismiss(Pdialog);	//callback onDismissDialog from UFDlg//~9725R~
	    saveUserName();	//update propUserName                      //~vav9I~
    }                                                              //~1A65I~

    /** register the BroadcastReceiver with the intent values to be matched */
    @Override                                                      //~1A65R~//~9720R~
    public void onResume() {
        super.onResume();                                          //~1A65R~//~9720R~
//      receiver = new WiFiDirectBroadcastReceiver(manager, channel, this);//~1A65R~
//      registerReceiver(receiver, intentFilter);                  //~1A65R~
//      if (SWDA==null)                                            //~1A65R~//~9729R~
//      	return;                                                //~1A65I~//~9729R~
        aWDActivity.onResume();                                     //~1A65R~
    }

    @Override                                                      //~1A65R~//~9720R~
    public void onPause()                                          //~9729R~
	{                                                              //~9729I~
    	swDismissAtPause=false;                                    //~9729I~
        super.onPause();                                           //~1A65R~//~9720R~//~9729R~
//      unregisterReceiver(receiver);                              //~1A65R~
//      if (SWDA==null)                                            //~1A65R~//~9729R~
//      	return;                                                //~1A65I~//~9729R~
        aWDActivity.onPause();	//unregisterReceiver                                      //~1A65R~//~9729R~
    }

	//*******************************************************************************************************//~1A65I~
	public void connected()                                        //~1A65R~
    {                                                              //~1A65I~
    	if (Dump.Y) Dump.println("WDA:connected");                 //~1A65R~
//        if (AG.aWDANFC!=null)   //NFC activity alive                       //~1A6aI~//~1A81R~
//            AG.aWDANFC.connected(WDANFC.CONNECTED/*connected*/);   //~1A6aI~//~1A81R~
	}                                                              //~1A65I~
	//*******************************************************************************************************//~1A84I~//~1A90I~
	public void connected(boolean Powner)                          //~1A84I~//~1A90I~
    {                                                              //~1A84I~//~1A90I~
    	if (Dump.Y) Dump.println("WDA:connected owner="+Powner);   //~1A84I~//~1A90I~
        updateButtonView(Powner);	       //~1A84I~               //~1A90I~
        swOwner=Powner;                                            //~1A84I~//~1A90I~
	}                                                              //~1A84I~//~1A90I~
	//*******************************************************************************************************//~1A6aI~
	public void connectError()                                     //~1A6aI~
    {                                                              //~1A6aI~
    	if (Dump.Y) Dump.println("WDA:connectError");              //~1A6aI~
//        if (AG.aWDANFC!=null)   //NFC activity alive                       //~1A6aI~//~1A81R~
//            AG.aWDANFC.connected(WDANFC.CONNECTERR);                //~1A6aI~//~1A81R~
	}                                                              //~1A6aI~
	//*******************************************************************************************************//~1A65I~
	public void disconnected()                                     //~1A65I~
    {                                                              //~1A65I~
    	if (Dump.Y) Dump.println("WDA:diconnected");               //~1A65I~
        IPC.unpaired();                                             //~1A87I~//~1A90I~
	}                                                              //~1A65I~
	//******************************************************************************//~1A65I~
	public static DeviceDetailFragment getDeviceDetailFragment()   //~1A65I~
    {                                                              //~1A65I~
//      return SWDA.deviceDetailFragment;                          //~1A65I~//~9729R~
        return AG.aWDA.deviceDetailFragment;                       //~9729I~
    }                                                              //~1A65I~
	//******************************************************************************//~1A65I~
	public static DeviceListFragment getDeviceListFragment()       //~1A65I~
    {                                                              //~1A65I~
//      return SWDA.deviceListFragment;                            //~1A65R~//~9729R~
        return AG.aWDA.deviceListFragment;                         //~9729I~
    }                                                              //~1A65I~
	//*******************************************************************************************************//~1A65I~
	public static WiFiDirectActivity getWDActivity()            //~1A65R~//~1A6aR~
    {                                                              //~1A65M~
//      return SWDA.aWDActivity;                                               //~1A65R~//~9729R~
        return AG.aWDA.aWDActivity;                                //~9729I~
    }                                                              //~1A65M~
	//*******************************************************************************************************//~1A65I~
//    private Resources getResources()                               //~1A65R~//~9720R~
//    {                                                              //~1A65M~//~9720R~
//        return AG.resource;                                        //~1A65M~//~9720R~
//    }                                                              //~1A65M~//~9720R~
	//*******************************************************************************************************//~1A65I~
	public static String getResourceString(int Pid)                //~1A65I~
    {                                                              //~1A65I~
		return AG.resource.getString(Pid);                         //~1A65I~
	}                                                              //~1A65I~
	//*******************************************************************************************************//~1A65I~
	public void setButtonListener(Button Pbtn)                     //~1A65I~
    {                                                              //~1A65I~
		super.setButtonListener(Pbtn);                             //~1A65I~
	}                                                              //~1A65I~
//    //*******************************************************************************************************//~1A6aI~//~9A03R~
//    public void connect(String Pmacaddr)                           //~1A6aI~//~9A03R~
//    {                                                              //~1A6aI~//~9A03R~
//        deviceDetailFragment.connect(Pmacaddr);                    //~1A6aI~//~9A03R~
//    }                                                              //~1A6aI~//~9A03R~
//    //*******************************************************************************************************//~1A6aI~//~9721R~
//    //*on macaddr received side                                    //~1A6aI~//~9721R~
//    //*rc:-1:not found,1:paired,0:not paired(do connect)           //~1A6aI~//~9721R~
//    //*******************************************************************************************************//~1A6aI~//~9721R~
//    public int discover(WDANFC Pwdanfc,String Pmacaddr)            //~1A6aR~//~9721R~
//    {                                                              //~1A6aI~//~9721R~
//        NFCwaitingDiscover=null;                                   //~1A6aI~//~9721R~
//        int rc=aWDActivity.discover(Pmacaddr,true/*issue discover when not paired*/);//~1A6aR~//~9721R~
//        if (rc==-1) //not found                                    //~1A6aR~//~9721R~
//        {                                                          //~1A6aI~//~9721R~
//            NFCwaitingDiscover=Pwdanfc; //expect callback at dicovery end//~1A6aI~//~9721R~
//            NFCwaitingDiscoverAddr=Pmacaddr;    //expect callback at dicovery end//~1A6aI~//~9721R~
//        }                                                          //~1A6aI~//~9721R~
//        if (Dump.Y) Dump.println("WDA:discover rc="+rc);           //~1A6aI~//~9721R~
//        return rc;                                                 //~1A6aI~//~9721R~
//    }                                                              //~1A6aI~//~9721R~
	//*******************************************************************************************************//~1A6aI~
	//*on macaddr send side                                        //~1A6aI~
	//*******************************************************************************************************//~1A6aI~
	public void discover()                                         //~1A6aI~
    {                                                              //~1A6aI~
        aWDActivity.discover();                                    //~1A6aI~
	}                                                              //~1A6aI~
	//*******************************************************************************************************//~1A6aI~
//	public void peerUpdated()                                      //~1A6aI~//~1A90R~
	public void peerUpdated(int Ppeerctr)                                      //~1A6aI~//~1A87R~//~1A90I~
    {                                                              //~1A6aI~
    	int rc;                                                    //~1A6aR~
    	if (Dump.Y) Dump.println("WDA:peerUpdated ctr="+Ppeerctr); //~1A87R~//~1A90I~
//        if (NFCwaitingDiscover!=null)                              //~1A6aI~//~9721R~
//        {                                                          //~1A6aI~//~9721R~
//            rc=aWDActivity.discover(NFCwaitingDiscoverAddr,false/*not issue discover when not paired*/);//~1A6aR~//~9721R~
//            NFCwaitingDiscover.discovered(NFCwaitingDiscoverAddr,rc);//~1A6aR~//~9721R~
//            NFCwaitingDiscover=null;    //response gotten          //~1A6aM~//~9721R~
//        }                                                          //~1A6aI~//~9721R~
        updateButtonView(deviceListFragment.thisOwner==1);         //~1A87I~//~1A90I~
	}                                                              //~1A6aI~
	//*******************************************************************************************************//~1A84I~//~1A90I~
	//*from OnClickOther                                           //~9722I~
	//*******************************************************************************************************//~9722I~
	private boolean buttonAction(int PbuttonId)                    //~1A84I~//~1A90I~
    {                                                              //~1A84I~//~1A90I~
//  	if (!onClickClose())	//set partner info parm            //~1A84R~//~1A90I~//~9720R~
//  	onClickClose();	//set partner info parm                    //~9720I~//~9722R~
//      if (!swDismissAtClose)                                     //~9720I~//~9722R~
//      	return false;                                          //~1A84I~//~1A90I~//~9722R~
    	boolean rc=IPC.buttonAction(PbuttonId);                    //~1A84I~//~1A90I~
    	if (Dump.Y) Dump.println("WDA:buttonAction rc="+rc);       //~1A84I~//~1A90I~
        return rc;                                                 //~1A84I~//~1A90I~
    }                                                              //~1A84I~//~1A90I~
	//*******************************************************************************************************//~1A87I~//~1A89I~
	//*from DeviceDetailFragment; before request unpair            //~1A87I~//~1A89I~
	//*******************************************************************************************************//~1A87I~//~1A89I~
	public boolean unpairRequest()                                   //~1A87I~//~1A89I~
    {                                                              //~1A87I~//~1A89I~
    	boolean rc=true;	//execute unpair at return             //~1A89I~
    	if (Dump.Y) Dump.println("WDA:unpairRequest remotestatus="+AG.RemoteStatus);//~1A87I~//~1A89I~
        if (AG.RemoteStatus==AG.RS_IPCONNECTED) //connection remains//~1A87I~//~1A89I~
        {                                                          //~1A87I~//~1A89I~
////          IPC.disconnectPartner();                               //~1A87I~//~1A89I~//~9729R~
//            Alert.AlertI ai=new Alert.AlertI()                                 //~1A89R~//~9729R~
//                                {                                  //~1A89I~//~9729R~
//                                    @Override                      //~1A89I~//~9729R~
//                                    public int alertButtonAction(int Pbuttonid,int Pitempos)//~1A89I~//~9729R~
//                                    {                              //~1A89I~//~9729R~
//                                        if (Dump.Y) Dump.println("WDA:unpairRequest buttonid="+Integer.toHexString(Pbuttonid));//~1A89I~//~9729R~
//                                        if (Pbuttonid==Alert.BUTTON_POSITIVE)//~1A89R~//~9729R~
//                                        {                          //~1Ac2I~//~9729R~
////                                          IPC.disconnectPartner();//~1A89I~//~1Ac3R~//~9729R~
////                                      deviceDetailFragment.doUnpair();//~1A89I~//~1Ac3R~//~9729R~
//                                            IPC.disconnectPartner(true/*unpair after closed*/);//~1Ac3I~//~9729R~
//                                        }                          //~1Ac2I~//~9729R~
//                                        return 1;                  //~1A89I~//~9729R~
//                                    }                              //~1A89I~//~9729R~
//                                };                                 //~1A89I~//~9729R~
////            Alert.simpleAlertDialog(ai,ALERT_TITLE_DISCONNECT,ALERT_MSG_DISCONNECT,//~1A89R~//~9721R~//~9729R~
////                                Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE);//~1A89R~//~9721R~//~9729R~
//            Alert.showAlert(ALERT_TITLE_DISCONNECT,ALERT_MSG_DISCONNECT,//~9721I~//~9729R~
//                                Alert.BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,ai);//~9721I~//~9729R~
			deviceDetailFragment.setStatus(true,R.string.Msg_IPConnectionExist);//~9729I~
	    	rc=false;	//execute unpair at later dialog response  //~1A89I~//~9727R~
        }                                                          //~1A87I~//~1A89I~
    	if (Dump.Y) Dump.println("WDA:unpairRequest rc="+rc);      //~1A89I~
        return rc;                                                 //~1A89I~
    }                                                              //~1A87I~//~1A89I~
//    //*******************************************************************************************************//~1Ac3I~//~9728R~
//    //*from PartnerThread by IPC.disconnectPartner(unpair=true) at thread terminate//~1Ac3I~//~9728R~
//    //*******************************************************************************************************//~1Ac3I~//~9728R~
//    public static void unpairFromPT()                              //~1Ac3I~//~9728R~
//    {                                                              //~1Ac3I~//~9728R~
//        if (Dump.Y) Dump.println("WDA:unpairFromPT");              //~1Ac3I~//~9728R~
//        if (SWDA!=null)                                            //~1Ac3I~//~9728R~
//        {                                                          //~1Ac3I~//~9728R~
//            URunnable.setRunFunc(SWDA/*URunnableI*/,0/*delay*/,SWDA/*objparm*/,0/*int parm*/);//~1Ac3I~//~9728R~
//        }                                                          //~1Ac3I~//~9728R~
//    }                                                              //~1Ac3I~//~9728R~
////***************************************************************  //~1Ac3I~//~9728R~
//    @Override                                                      //~1Ac3I~//~9728R~
////  public void runFunc(Object parmObj,int parmInt/*0*/)           //~1Ac3I~//~9721R~//~9728R~
//    public void URunnableCB(Object parmObj,int parmInt/*0*/)       //~9721I~//~9728R~
//    {                                                              //~1Ac3I~//~9728R~
//        if (Dump.Y) Dump.println("WDA:runfunc");                   //~1Ac3I~//~9728R~
//        try                                                        //~1Ac3I~//~9728R~
//        {                                                          //~1Ac3I~//~9728R~
//            deviceDetailFragment.doUnpair();                       //~1Ac3I~//~9728R~
//        }                                                          //~1Ac3I~//~9728R~
//        catch(Exception e)                                         //~1Ac3I~//~9728R~
//        {                                                          //~1Ac3I~//~9728R~
//            Dump.println(e,"WDA:runfunc");                         //~1Ac3I~//~9728R~
//        }                                                          //~1Ac3I~//~9728R~
//    }                                                              //~1Ac3I~//~9728R~
////***************************************************************  //~1Ac4I~//~va40R~
////*disable wifidirect                                              //~1Ac4I~//~va40R~
////***************************************************************  //~1Ac4I~//~va40R~
//    public static boolean setWifiDirectStatus(boolean Penable)     //~1Ac4I~//~va40R~
//    {                                                              //~1Ac4I~//~va40R~
//        if (Dump.Y) Dump.println("setWWifiDirectState parmenable="+Penable);//~1Ac4I~//~va40R~
////      wifiApManager mgr=SWDA.new wifiApManager();                //~1Ac4I~//~9729R~//~va40R~
//        wifiApManager mgr=AG.aWDA.new wifiApManager();             //~9729I~//~va40R~
//        if (mgr.swNoMethod)                                        //~1Ac4I~//~va40R~
//            return false;                                          //~1Ac4I~//~va40R~
//        return mgr.setState(Penable);                              //~1Ac4I~//~va40R~
//    }                                                              //~1Ac4I~//~va40R~
//    class wifiApManager                                            //~1Ac4I~//~va40R~
//    {                                                              //~1Ac4I~//~va40R~
//        private final WifiManager wifiManager;                     //~1Ac4I~//~va40R~
//        private Method ctlMethod,cfgMethod,statMethod;             //~1Ac4I~//~va40R~
//        public boolean swNoMethod;                                 //~1Ac4I~//~va40R~
//        public wifiApManager()                                     //~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            wifiManager=(WifiManager)AG.context.getSystemService(Context.WIFI_SERVICE);//~1Ac4I~//~va40R~
//            try                                                    //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                ctlMethod=wifiManager.getClass().getMethod("setWifiApEnabled",WifiConfiguration.class,boolean.class);//~1Ac4I~//~va40R~
//                cfgMethod=wifiManager.getClass().getMethod("getWifiApConfiguration");//~1Ac4I~//~va40R~
//                statMethod=wifiManager.getClass().getMethod("getWifiApState");//~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            catch (NoSuchMethodException e)                        //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                Dump.println(e,"WDA.wifiApManager constructor");   //~1Ac4I~//~va40R~
//                e.printStackTrace();                               //~1Ac4I~//~va40R~
//                swNoMethod=true;                                   //~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            if (Dump.Y) Dump.println("WDA.wifiApManager:wifiApManager ctlM="+ctlMethod+",cfgMethod="+cfgMethod+",staeMethod="+statMethod);//~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//        private WifiConfiguration getCfg()                         //~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            try                                                    //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                return (WifiConfiguration)cfgMethod.invoke(wifiManager);//~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            catch(Exception e)                                     //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                Dump.println(e,"WDA:wifiApManager.getCfg");        //~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            return null;                                           //~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//        public boolean setState(boolean Penable)                   //~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            WifiConfiguration cfg=getCfg();                        //~1Ac4I~//~va40R~
//            if (cfg!=null)                                         //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                return setState(cfg,Penable);                      //~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            return false;                                          //~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//        public boolean setState(WifiConfiguration Pcfg,boolean Penable)//~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            try                                                    //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                int stat=(Integer)statMethod.invoke(wifiManager);  //~1Ac4I~//~va40R~
//                if (Dump.Y) Dump.println("WDA.wifiApManager:setState before parm enable="+Penable+",wifi isenable="+wifiManager.isWifiEnabled()+",getstate="+wifiManager.getWifiState()+",apstat="+stat);//~1Ac4I~//~va40R~
////              wifiManager.setWifiEnabled(!Penable);  //disable wifi case p2p disable//~1Ac4I~//~va40R~
//                boolean rc=(Boolean)ctlMethod.invoke(wifiManager,Pcfg,Penable);//~1Ac4I~//~va40R~
//                if (Dump.Y) Dump.println("WDA.wifiApManager:setState after parm enable="+Penable+",wifi isenable="+wifiManager.isWifiEnabled()+",getstate="+wifiManager.getWifiState()+",apstat="+stat);//~1Ac4I~//~va40R~
//                if (Dump.Y) Dump.println("WDA.wifiApManager:setState rc="+rc);//~1Ac4I~//~va40R~
//                return rc;                                         //~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            catch(Exception e)                                     //~1Ac4I~//~va40R~
//            {                                                      //~1Ac4I~//~va40R~
//                Dump.println(e,"WDA:wifiApManager.getCfg");        //~1Ac4I~//~va40R~
//            }                                                      //~1Ac4I~//~va40R~
//            return false;                                          //~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//    }                                                              //~1Ac4I~//~va40R~
////***************************************************************  //~1Ac4I~//~va40R~
////*disable wifidirect 2'nd way                                     //~1Ac4I~//~va40R~
////***************************************************************  //~1Ac4I~//~va40R~
//    public static boolean setWifiP2pState(boolean Penable)         //~1Ac4I~//~va40R~
//    {                                                              //~1Ac4I~//~va40R~
//        boolean rc=false;                                          //~1Ac4I~//~va40R~
//        if (Dump.Y) Dump.println("setWifiP2pState parmenable="+Penable);//~1Ac4I~//~va40R~
////      WifiP2pManager mgr=SWDA.aWDActivity.manager;               //~1Ac4I~//~9729R~//~va40R~
//        WifiP2pManager mgr=AG.aWDA.aWDActivity.manager;            //~9729I~//~va40R~
////      Channel channel=SWDA.aWDActivity.channel;                  //~1Ac4I~//~9729R~//~va40R~
//        Channel channel=AG.aWDA.aWDActivity.channel;               //~9729I~//~va40R~
//        Method method;                                             //~1Ac4I~//~va40R~
//        try                                                        //~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            if (Penable)                                           //~1Ac4I~//~va40R~
//                method=mgr.getClass().getMethod("enableP2p",Channel.class);//~1Ac4I~//~va40R~
//            else                                                   //~1Ac4I~//~va40R~
//                method=mgr.getClass().getMethod("disableP2p",Channel.class);//~1Ac4I~//~va40R~
//            method.invoke(mgr,channel);                            //~1Ac4I~//~va40R~
//            rc=true;                                               //~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//        catch (Exception e)                                        //~1Ac4I~//~va40R~
//        {                                                          //~1Ac4I~//~va40R~
//            Dump.println(e,"WDA:setWifiP2pState");                 //~1Ac4I~//~va40R~
//            e.printStackTrace();                                   //~1Ac4I~//~va40R~
//        }                                                          //~1Ac4I~//~va40R~
//        return rc;                                                 //~1Ac4I~//~va40R~
//    }                                                              //~1Ac4I~//~va40R~
    //************************************************************ //~9722I~
    @Override //UEditTextI                                         //~9722I~
    public void onTextChanged(UEditText Pedittext,String Ptext)    //~9722I~
    {                                                              //~9722I~
        if (Dump.Y) Dump.println("WDA.onTextChanged text="+Pedittext);//~9722I~//~9A09R~
//      BTCDialog.getYourName(etYourName,false);	//update AG.YourName//~9722R~//~9A09R~
        if (!getYourName())	//update AG.YourName                   //~9A09R~
        	return;                                                //~9A09I~
	  if (swChangedYourName)                                       //~0116I~
      {                                                            //~0116I~
//      AG.aBTMulti.sendMsg(BTMulti.MSGID_NEWNAME,Ptext);          //~9722I~
//      AG.aBTMulti.sendMsg(MSGID_NEWNAME,Ptext);                  //~9722I~//~va1bR~
		swChangedYourName=false;                                   //~0116I~
      }                                                            //~0116I~
    }                                                              //~9722I~
    //************************************************************ //~9A09I~
    private boolean getYourName()                                  //~9A09R~
    {                                                              //~9A09I~
        if (Dump.Y) Dump.println("WDA.getYourName");               //~9A09I~//~0116R~
    	String yourname=etYourName.getText();                     //~9722I~//~9A09I~
        if (Dump.Y) Dump.println("WDA.getYourName with UeditText yourname="+yourname);//~9722I~//~9A09I~
        if (yourname.equals(""))                                   //~9722I~//~9A09I~
        {                                                          //~9722I~//~9A09I~
			UView.showToast(R.string.ErrSpecifyYourname);      //~9722I~//~9A09I~
            return false;                                          //~9A09I~
        }                                                          //~9722I~//~9A09I~
        if (Dump.Y) Dump.println("WDA.getYourName="+yourname);//~9722I~//~9A09I~
        if (!yourname.equals(AG.YourName))                         //~9722I~//~9A09I~
        {                                                          //~9722I~//~9A09I~
        	AG.aBTMulti.BTGroup.updateYourName(AG.YourName,yourname);           //~9905I~//~9A09I~
        	AG.YourName=yourname;                                  //~9722I~//~9A09I~
            Utils.putPreference(PREFKEY_YOURNAME,yourname);        //~9722I~//~9A09I~
		    displayGroup();                                         //~9905I~//~9A09R~
			swChangedYourName=true;                                //~0116I~
        }                                                          //~9722I~//~9A09I~
        return true;                                               //~9A09I~//~0116R~
    }                                                              //~9A09I~
    //******************************************                   //~9A09I~
    private void displayGroup()                                    //~9A09I~
    {                                                              //~9A09I~
    	groupList.displayGroup();                                  //~9A09I~
    }                                                              //~9A09I~
	//*******************************************************************************************************//~9722I~
	//*onClient,From IPConnectionWD.getServer-->not used                                          //~9722I~//~9728R~
    //*set Name,NameRemote,isPaired                                //~9729I~
	//*******************************************************************************************************//~9722I~
    public ConnectionData getPairingInfo()                         //~9722R~
    {                                                              //~9722I~
    	ConnectionData cd=deviceListFragment.getSelectedDeviceData(); //get Name NameRemote, and isPaired only//~9727R~//~9729R~
        if (cd==null)                                              //~9727I~
            return null;                                           //~9727I~
    	cd.strAddress=deviceListFragment.thisDeviceAddr; //MacAddr          //~9722R~//~9723M~//~9728R~
    	cd.strOwnerAddress=deviceDetailFragment.ownerIPAddress;      //~9722R~
    	cd.isOwner=deviceListFragment.thisOwner==1;                //~9722I~
        if (Dump.Y) Dump.println("WDA.getPairingInfo connectionData="+cd.toString());//~9729R~//~9B03R~
        return cd;                                                 //~9722I~
    }                                                              //~9722I~
    //******************************************                   //~v@@@I~//~9723I~
    public static boolean isShowing()                             //~v@@@R~//~9723I~//~0113R~
    {                                                              //~v@@@I~//~9723I~
        boolean rc=Utils.isShowingDialogFragment(AG.aWDA);             //~9709I~//~9723I~
        if (Dump.Y) Dump.println("WDA.isShowing rc="+rc);    //~9709I~//~9723I~
        return rc;                                                 //~9709R~//~9723I~
    }                                                              //~v@@@I~//~9723I~
	//*******************************************************************************************************//~9723I~
	//*From IPMulti.onConnected                                    //~9723I~//~9725R~
	//*******************************************************************************************************//~9723I~
    public static void onConnected(String Pdevicename,String Paddr,Boolean Pswclient)//~v@@@I~//~9723I~
    {                                                              //~v@@@I~//~9723I~
    //************************                                     //~v@@@I~//~9723I~
        if (Dump.Y) Dump.println("WDA.onConnected name="+Pdevicename+",addr="+Paddr+",swclient="+Pswclient);//~v@@@I~//~9723I~
	    if (!isShowing())                                          //~v@@@I~//~9723I~
        	return;                                                //~v@@@I~//~9723I~
        WDA dlg=AG.aWDA;                                           //~9723I~
	    dlg.onConnected(0,Pdevicename,Paddr,Pswclient);            //~v@@@I~//~9723I~
    }                                                              //~v@@@I~//~9723I~
    //*******************************************************************************************************//~0108R~
    //*show statusMsg:connected                                    //~0108I~
    //*******************************************************************************************************//~0108I~
    public static void onConnected(String Pdevicename)             //~0108R~
    {                                                              //~0108R~
    //************************                                     //~0108R~
        if (Dump.Y) Dump.println("WDA.onConnected name="+Pdevicename);//~0108R~
        if (!isShowing())                                          //~0108R~
            return;                                                //~0108R~
        WDA dlg=AG.aWDA;                                           //~0108R~
        dlg.statusMsg=Utils.getStr(R.string.Info_Connected,Pdevicename);//~0108R~
        dlg.statusErr=false;                                       //~0108R~
    }                                                              //~0108R~
	//*******************************************************************************************************//~9815I~
	//*From IPMulti.receivedNameAdd                                //~9815I~
	//*******************************************************************************************************//~9815I~
    public static void onConnectionChanged()                       //~9815I~
    {                                                              //~9815I~
    //************************                                     //~9815I~
        if (Dump.Y) Dump.println("WDA.onConnectionChanged");       //~9815I~
	    if (!isShowing())                                          //~9815I~
        	return;                                                //~9815I~
        WDA dlg=AG.aWDA;                                           //~9815I~
	    dlg.updateDialog();                                        //~9815I~
    }                                                              //~9815I~
	//*******************************************************************************************************//~9723I~
    private void onConnected(int Popt,String Pdevicename,String Paddr,Boolean Pswclient)//~v@@@I~//~9723I~
    {                                                              //~v@@@I~//~9723I~
    //************************                                     //~v@@@I~//~9723I~
        if (Dump.Y) Dump.println("WDA.onConnected name="+Pdevicename+",addr="+Paddr+",swclient="+Pswclient);//~v@@@I~//~9723I~
//        ProgDlg.dismissCurrent();                                  //~v@@@I~//~9723I~
//        if (connectionStatus==CS_ACCEPTING)                       //~v@@@I~//~9723I~
//            connectionType=ROLE_SERVER;                               //~v@@@I~//~@002R~//~9723I~
//        else                                                       //~v@@@I~//~9723I~
//        if (connectionStatus==CS_CONNECTING)                       //~v@@@I~//~9723I~
//        {                                                          //~v@@@I~//~9723I~
//            connectionType=ROLE_CLIENT;                               //~v@@@I~//~@002R~//~9723I~
//            connectionStatus=CS_CONNECTED;                         //~v@@@I~//~9723I~
//        }                                                          //~v@@@I~//~9723I~
//        else                                                       //~v@@@I~//~9723I~
//        {                                                          //~v@@@I~//~9723I~
//            UView.showToast("BTCDialog.onConnected connectionStatus invalid");  ////~v@@@I~//~9723I~
//            return;                                                //~v@@@I~//~9723I~
//        }                                                          //~v@@@I~//~9723I~
//        if (Pswclient!=(connectionType==ROLE_CLIENT))                //~v@@@I~//~@002R~//~9723I~
//        {                                                          //~v@@@I~//~9723I~
//            UView.showToast("BTCDialog.onConnected Role(Server or Client) Unmatch");//~v@@@R~//~9723I~
//            return;                                                //~v@@@I~//~9723I~
//        }                                                          //~v@@@I~//~9723I~
//  	addMember(Pdevicename,Paddr);                              //~v@@@R~//~9723I~
	    onConnected(Pdevicename);                                  //~0108I~
        updateDialog();                                             //~v@@@I~//~9723I~
    }                                                              //~9723I~
	//*******************************************************************************************************//~9723I~
    public void updateDialog()                                    //~9723I~//~0112R~
    {                                                              //~9723I~
        if (Dump.Y) Dump.println("WDA.updateDialog");              //~9723I~
	    if (AG.isMainThread())                                     //~9723I~
        {                                                          //~9723I~
	        if (Dump.Y) Dump.println("WDA.updateDialog mainThread");//~9723I~
			updateDialogSub();                              //~9723I~//~9724R~
            return;                                                //~9723I~
        }                                                          //~9723I~
        if (Dump.Y) Dump.println("WDA.updateDialog subThread");    //~9723I~
	    AG.activity.runOnUiThread(                                 //~9723I~
        	new Runnable()                                         //~9723I~
            	{                                                  //~9723I~
                	@Override                                      //~9723I~
                    public void run()                              //~9723I~
                    {                                              //~9723I~
				        if (Dump.Y) Dump.println("WDA.updateDialog runonUiThread");//~9723I~
                        try                                        //~9724I~
                        {                                          //~9724I~
				        	updateDialogSub();                  //~9723I~//~9724R~
                        }                                          //~9724I~
                        catch(Exception e)                         //~9724I~
                        {                                          //~9724I~
            				Dump.println(e,"WDA:updateDialog.runOnUIThread.run");//~9724I~
                        }                                          //~9724I~
                    }                                              //~9723I~
                });                                                //~9723I~
    }                                                              //~v@@@I~//~9723I~
    //*****************************                                //~9724I~
    private void updateDialogSub()                                 //~9724I~
    {                                                              //~9724I~
        if (Dump.Y) Dump.println("WDA.updateDialogSub");           //~9724I~
		deviceListFragment.drawListView();                         //~9724I~
        groupList.updateDialog();                                  //~9724I~
//  	showYourName(); updateDialog callback                      //~9B07R~
        updateButtonView(deviceListFragment.thisOwner==1);         //~9728I~
        if (statusMsg!=null)                                       //~9729I~
        {                                                          //~9729I~
			deviceDetailFragment.setStatus(statusErr,statusMsg);	//clear err msg//~9729I~
            statusMsg=null;                                        //~9729I~
        }                                                          //~9729I~
    }                                                              //~9724I~
    //*****************************                                //~9724I~
    @Override                                                      //~9724I~
    public void onDismissDialog()                                  //~9724I~
    {                                                              //~9724I~
		if (Dump.Y) Dump.println("WDA:onDismissDialog");           //~9724I~
//  	cancelDiscover();                                          //~9724R~
    	aWDActivity.cancelDiscovery();                              //~0124I~
        aWDActivity.unregisterReceiver();                          //~1A65I~//~9725M~
        IPC.dismissWDA();                                          //~1A67I~//~9725M~
//      boolean enable=(swOwner)&&(AG.aBTMulti.BTGroup.getConnectedCtr()!=0);//~9724I~//~va66R~
//      AG.aMainView.enableStartGame(enable);                      //~9724I~//~va66R~
        AG.aWDA=null;                                              //~9724M~
        closeChannel();                                            //~vaviI~
        WDI.dismissedWDA();                                         //~0113R~
	}                                                              //~9724I~
    //********************************************************************//~9729I~
    //*from IPMulti                                                //~9729I~
    //********************************************************************//~9729I~
    public static void connectionLost(boolean PswServer,String PlocalDeviceName,String PremoteDeviceName)//~9729R~
    {                                                              //~9729I~
    	if (Dump.Y) Dump.println("WDA.connectionLost swServer="+PswServer+",local="+PlocalDeviceName+",remote="+PremoteDeviceName);//~9729I~
        if (AG.aWDA==null)                                         //~9729I~
        {                                                          //~0117I~
//  		Alert.showMessage(null/*title*/,Utils.getStr(R.string.InfoDisconnected,PremoteDeviceName));//~0117I~//~0118R~
          if (AG.status!=AG.STATUS_STOPFINISH)                     //+vaz9I~
    		Alert.showMessage(0/*titleid=app_name*/,Utils.getStr(R.string.InfoDisconnected,PremoteDeviceName));//~0118I~
        	return;                                                //~9729I~
        }                                                          //~0117I~
        AG.aWDA.onDisconnected(PswServer,PlocalDeviceName,PremoteDeviceName);//~9729I~
    }                                                              //~9729I~
    //********************************************************************//~9729I~
    public void onDisconnected(boolean PswServer,String PlocalDeviceName,String PremoteDeviceName)//~9729I~
    {                                                              //~9729I~
    	if (Dump.Y) Dump.println("WDA.onDisconnected swServer="+PswServer+",local="+PlocalDeviceName+",remote="+PremoteDeviceName);//~9729I~
        statusMsg=Utils.getStr(R.string.InfoDisconnected,PremoteDeviceName);//~9729I~
        statusErr=true;                                            //~9729I~
        updateDialog();                                            //~v@@@I~//~9729I~
	}                                                              //~9729I~
    //********************************************************************//~9B07I~
    //*from BTMulti bt MSG_IOERR(IPIOThread terminated)            //~9B07I~
    //********************************************************************//~9B07I~
    public static void onReceiveDisconnected(String PremoteDeviceName)//~9B07R~
    {                                                              //~9B07I~
    	if (Dump.Y) Dump.println("WDA.onReceiveDisconnected remote="+PremoteDeviceName);//~9B07I~//~0117R~
        if (AG.aWDA==null)                                         //~9B07I~
        {                                                          //~0117I~
//  		Alert.showMessage(null/*title*/,Utils.getStr(R.string.InfoDisconnected,PremoteDeviceName));//~v@@@I~//~0117I~//~0118R~
    		Alert.showMessage(0/*titleid=app_name*/,Utils.getStr(R.string.InfoDisconnected,PremoteDeviceName));//~0118I~
        	return;                                                //~9B07I~
        }                                                          //~0117I~
        AG.aWDA.statusMsg=Utils.getStr(R.string.InfoDisconnected,PremoteDeviceName);//~9B07R~
        AG.aWDA.statusErr=true;                                    //~9B07R~
        AG.aWDA.updateDialog();                                    //~9B07R~
	}                                                              //~9B07I~
    //********************************************************************//~9729I~
    public static void updateDialog(boolean PswErr,String Pmsg)    //~9729I~
    {                                                              //~9729I~
    	if (Dump.Y) Dump.println("WDA.showStatus swErr="+PswErr+",msg="+Pmsg);//~9729I~
        if (AG.aWDA==null)                                         //~9729I~
        	return;                                                //~9729I~
        AG.aWDA.statusErr=PswErr;                                  //~9729I~
        AG.aWDA.statusMsg=Pmsg;                                    //~9729I~
        AG.aWDA.updateDialog();                                    //~9729I~
	}                                                              //~9729I~
    //********************************************************************//~9816I~
    public void clearStatusMsg()                                   //~9816I~
    {                                                              //~9816I~
    	if (Dump.Y) Dump.println("WDA.clearStatusMsg");            //~9816I~
		getDeviceDetailFragment().setStatus(false/*swErr*/,"");//~@@@@I~//~9816I~
//      GMsg.clearMsgbar();                                        //~9816M~//~9C02R~
    }                                                              //~9816I~
    //********************************************************************//~9A03I~
    public void enableCBWantGroupOwner(boolean Penable) 	//paired at scan//~9A03R~
    {                                                              //~9A03I~
    	if (Dump.Y) Dump.println("WDA.enableCBWantGroupOwner sw="+Penable);//~9A03R~
        cbWantGroupOwner.setEnabled(Penable);                      //~9A03I~
    }                                                              //~9A03I~
    //********************************************************************//~9A03I~
    public void enableCBWantGroupOwner(boolean Penable,boolean Powner) 	//paired at scan//~9A03I~
    {                                                              //~9A03I~
    	if (Dump.Y) Dump.println("WDA.enableCBWantGroupOwner sw="+Penable+",owner="+Powner);//~9A03I~
        cbWantGroupOwner.setState(Powner);                         //~9A03I~
	    enableCBWantGroupOwner(Penable); 	//paired at scan//~9A03I~
    }                                                              //~9A03I~
    //********************************************************************//~0108I~
    //*from Server                                                 //~0108I~
    //********************************************************************//~0108I~
    public static void acceptFailed()                              //~0108I~
    {                                                              //~0108I~
    	if (Dump.Y) Dump.println("WDA.acceptFailed");              //~0108I~
        if (Utils.isShowingDialogFragment(AG.aWDA))                //~0108I~
        	AG.aWDA.updateButtonView(true/*owner*/);               //~0108I~
    }                                                              //~0108I~
    //************************************************************ //~vav9I~
    public void saveUserName()                               //~vav9I~
    {                                                              //~vav9I~
        if (Dump.Y) Dump.println("WDA.saveUserName");//~vav9I~     //~vavhR~
    	AG.aAccName.saveProp();	//if updated                       //~vav9R~
    }                                                              //~vav9I~
    //************************************************************ //~vavhI~
    private void setOnShowListener(View PlayoutView)                     //~vavhR~
    {                                                              //~vavhI~
        if (Dump.Y) Dump.println("WDA.setOnShowListener");         //~vavhI~
        viewGL=(ListView)UView.findViewById(PlayoutView,R.id.GroupList);     //~vavhI~
        if (Dump.Y) Dump.println("WDA.setOnShowListener vieGL height="+viewGL.getHeight());//~vavhI~
        androidDlg/*AxeDlg*/.setOnShowListener(this);                            //~vavhI~
    }                                                              //~vavhI~
    //************************************************************ //~vavhI~
    @Override                                                      //~vavhI~
    public void onShow(DialogInterface Pdlg)                       //~vavhI~
    {                                                              //~vavhI~
        int hh=viewGL.getHeight();    //by pixel                   //~vavhI~
        if (Dump.Y) Dump.println("WDA.onShow vieGL height="+hh);   //~vavhR~
        int ww= LinearLayout.LayoutParams.MATCH_PARENT;                         //~vavhI~
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ww,hh);                   //~vavhI~
        viewGL.setLayoutParams(lp);                                //~vavhI~
        if (Dump.Y) Dump.println("WDA.onShow setLayoutparams lp="+lp);//~vavhI~
    }                                                              //~vavhI~
    //************************************************************ //~vaviI~
    private void closeChannel()                                    //~vaviI~
    {                                                              //~vaviI~
        if (Dump.Y) Dump.println("WDA.closeChannel channel");      //~vaviR~
        if (aWDActivity!=null)                                     //~vaviR~
	        aWDActivity.closeChannel();                            //~vaviR~
    }                                                              //~vaviI~
}
