//*CID://+va69R~:                             update#=  504;       //+va69R~
//*****************************************************************//~v101I~
//*BlietoothConnectionDialog                                       //~v@@@I~
//*****************************************************************//~v101I~
//2021/02/12 va69 (BUG)BTCDialog;disconnect button is disable at connection failed even another connection is active//+va69I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//2020/10/19 va1b (Bug)server crashes by @@add from client because thread=null; BTCDialog EeditText textchange listener is called by Button push by focus change.//~va1bI~
//2020/10/05 va13:remember device selected                         //~va13I~
//2020/06/02 va10:BTCDialog search contains bug for null value; delete the function which is never called//~va10I~
//@002:20181103 use enum                                           //~v001I~
//@001:20181103 updatebuttonstatus over config change              //~v@@@I~
//*****************************************************************//~v@@@I~
package com.btmtest.dialog;                                        //~v@@@R~
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map; //~v@@@I~

import android.content.Intent;                                     //~v@@@I~
import android.bluetooth.BluetoothDevice;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;                                      //~v@@@I~
import android.app.Dialog;//~v@@@I~
import android.graphics.Color;                                     //~v@@@I~
import android.view.WindowManager;//~v@@@I~
import android.os.Bundle;

import com.btmtest.BT.GroupList;
import com.btmtest.R;                                              //~v@@@I~
import com.btmtest.game.GC;
import com.btmtest.game.UserAction;
import com.btmtest.game.gv.GMsg;
import com.btmtest.gui.UListView.UListViewData;
import com.btmtest.utils.UView;                                         //~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.gui.UListView;                                //~v@@@I~
import com.btmtest.gui.UEditText;                                  //~v@@@I~
import com.btmtest.utils.Utils;
import com.btmtest.utils.Alert;
import com.btmtest.utils.ProgDlg;
import com.btmtest.gui.UButton;                                    //~v@@@R~
import com.btmtest.BT.Members;
import com.btmtest.BT.BTService;
import com.btmtest.BT.BTDiscover;
import com.btmtest.BT.BTI;
import com.btmtest.BT.DeviceData;
import com.btmtest.BT.enums.ConnectionStatus;                         //~v@@@R~
import com.btmtest.TestOption;                                     //~9305I~
import com.btmtest.wifi.WDA;
import com.btmtest.wifi.WDI;

import static com.btmtest.AG.*;
import static com.btmtest.BT.Members.*;
import static com.btmtest.BT.enums.ConnectionStatus.*;                //~v@@@I~
import static com.btmtest.BT.enums.MsgIDConst.*;                      //~@002I~
import static com.btmtest.BT.BTMulti.*;                            //~@002I~
import static com.btmtest.game.GConst.*;                                  //~9723I~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@002I~
import static com.btmtest.StaticVars.connectionStatus;             //~@002I~
import static com.btmtest.TestOption.*;                            //~9305I~

public class BTCDialog extends UFDlg                               //~v@@@R~
        implements ProgDlg.ProgDlgI, UEditText.UEditTextI          //~v@@@R~
{                                                                  //~2C29R~
	protected static final String HELPFILE="BTCDialog";            //~9C13R~
    private static final String PKEY_BTSECURE="BTSecureOptionNonNFC";//~1AbuI~
    private static final int BTSECURE_DEFAULT=1; //default Secure  //~1AbVR~
    private static final int STRID_CONNECT=R.string.BTConnectCommon;//~1AbuI~
    private static final int STRID_ACCEPT=R.string.BTAcceptCommon; //~1AbuI~//~1AebM~//~v@@@M~
    private static final int RID_DEVICELIST=R.id.DeviceList;       //~v@@@I~
    private static final int RID_GROUPLIST=R.id.GroupList;         //~v@@@I~
    private static final String PKEY_BT_DEVICE_SELECTION="BTServerDeviceName";//~va13R~
                                                                   //~v@@@I~
    public static final int RENEWAL_MEMBER=1;                     //~v@@@I~
    public static final int RENEWAL_DEVICELIST=2;                  //~v@@@I~
                                                                   //~v@@@I~
    protected int waitingDialog=0;                                   //~3201I~//~9A21R~
//  private static DeviceDataList SdeviceList;                                   //~1AbTR~//~1AbUR~//~@002R~
    private DeviceDataList SdeviceList;                            //~@002I~
    private String[] pairedDeviceList;                      //~1AbTI~
        private ListView lvGroup;                                  //~v@@@R~
		private Button btnSettings;                                //~v@@@R~
		protected Button btnAccept;                                  //~v@@@R~//~9A30R~
		protected Button btnStopAccept;                              //~v@@@R~//~9A30R~
		protected Button btnConnect;                                 //~v@@@R~//~9A30R~
		protected Button btnDisconnect;                              //~v@@@R~//~9A30R~
		private Button btnUnpair;                                  //~9210I~
		private Button btnGame;                                    //~v@@@R~
		private Button btnDiscoverable;                            //~v@@@R~
		private Button btnDiscover;                                //~v@@@R~
		private Button btnDelete;                                  //~v@@@R~
		private UEditText etYourName;                              //~v@@@R~
        private TextView tvLocalDeviceName;                        //~v@@@R~
        private TextView tvClientMode;                             //~v@@@R~
        private TextView tvDiscoverable;                           //~v@@@R~
        private CheckBox cbSecureOption;                           //~v@@@R~
        private LinearLayout llSnackbarParent;                     //~v@@@R~
    private ListBT DL;   	//listview device list   //@@@@test parcelable//~v@@@R~
//  private ListGL GL;   	//listview group list    //@@@@test parcelable//~v@@@R~//~9724R~
    private GroupList GL;   	//listview group list    //@@@@test parcelable//~9724I~
    private boolean onDiscovery;                                   //~3203I~
    private int waitingid;//~3203I~                                //~v@@@R~
    private String myDevice,connectingDevice,disconnectingDevice;  //~v@@@R~
    private String unpairingDevice;                                //~9210I~
    private boolean swCancelDiscover;                                      //~3205I~
//  private static int lastSelected=-1;                            //~3205I~//~@002R~
    private static final int ID_STATUS_PAIRED=R.string.BTStatusPaired;   //~1A6fI~//~1AbUI~
    private static final int ID_STATUS_DISCOVERED=R.string.BTStatusDiscovered;//~1A6fI~//~1AbUI~
    private static final int ID_STATUS_CONNECTED=R.string.BTStatusConnected;//~1A6fI~//~1AbUI~
    private static final int ID_STATUS_CONNECTED_ONCE=R.string.BTStatusConnectedOnce;//~1AbRI~//~1AbUR~//~1AbRI~
    private        String statusPaired,statusDiscovered,statusConnected;//~1A6fR~//~1A6kR~//~v@@@R~//~@002R~
    private String statusConnectedOnce;                            //~1AbRI~
    private boolean swSecure;                                      //~1AbuI~
    private boolean swStartSettings;                               //~v@@@I~
                                                                   //~1A6fI~
    	private static final int BTROW_NAME=R.id.ListViewLine;     //~1A6fM~
    	private static final int BTROW_STATUS=R.id.ListViewLineStatus;//~1A6fM~
	    private static final int COLOR_STATUS_DISCOVERED=Color.rgb(0,   0xc0, 0xc0);//~1A6fM~//~1A6kR~//~v@@@R~
	    private static final int COLOR_STATUS_PAIRED    =Color.rgb(0,   0xa0, 0xa0);//~1A6fM~//~1A6kR~//~v@@@I~
	    private static final int COLOR_STATUS_CONNECTED =Color.rgb(0xff,0x6e, 0   );  //orange//~v@@@R~
	    private static final int COLOR_CLIENT           =Color.rgb(0xff,0x6e, 0   ); //orange//~v@@@R~
	    private static final int COLOR_SERVER           =Color.rgb(0xff,0x20, 0);  //orange//~v@@@R~
	    public  static final int COLOR_BG_DEVICE_LIST=Color.rgb(0xc8,0xff,0xc8);  //orange//~v@@@R~//~9727R~
	    private static final int COLOR_BG_GROUP_LIST =Color.rgb(0xc8,0xc8,0xff);  //orange//~v@@@R~
//      private static final int COLOR_EDITABLE          =R.color.editable;//~v@@@I~//~9B07R~
//      private static final int COLOR_EDITABLE_DISABLED =R.color.markabletext; //editabledisabled;//~v@@@R~//~9B07R~
                                                                   //~v@@@I~
    	private static final int BTROW_MEMB1=R.id.GroupMember1;    //~v@@@I~
    	private static final int BTROW_MEMB2=R.id.GroupMember2;    //~v@@@I~
                                                                   //~v@@@I~
//    private static int connectionType;                             //~v@@@R~//~9620R~
    protected int connectionType;                                    //~9620I~//~9A21R~
                                                                   //~v@@@I~
//  private int connectionStatus=CS_UNKNOWN;                       //~@002R~
//  private ConnectionStatus connectionStatus=CS_UNKNOWN;          //~@002R~
//  private UFDlg ufdlg;                                           //~v@@@I~//~9227R~
    protected View layoutView;                                       //~v@@@I~//~9A23R~
    protected Members members;                                       //~v@@@I~//~9A21R~
//  private int colorEditable,colorEditableDisabled;               //~v@@@I~//~9722R~
    protected int connectedCtr;    //number of addr!=null, =1 for client//~v@@@R~//~9A23R~
    protected int memberCtr;       //number of yourname!=null        //~v@@@I~//~9A23R~
    private boolean swChangedYourName;                             //~0116I~
                                                                   //~1A6fI~
    //******************************************                   //~v@@@M~
	public BTCDialog()                                             //~v@@@M~
	{                                                              //~3105R~//~v@@@M~
        if (Dump.Y) Dump.println("BTCDialog.constructor connectionStatus="+connectionStatus+",connectionType="+connectionType);//~@002I~
        WDI.shownBTCD();	//unregister WDIReceiver               //~0113I~
	}                                                              //~v@@@M~
    //******************************************                   //~v@@@I~
    public static BTCDialog newInstance(int PmemberRole)           //~v@@@R~
    {                                                              //~v@@@I~
        if (isShowing())                                           //~9709I~
        {	                                                       //~9709I~
        	if (Dump.Y) Dump.println("BTCDialog:newInstance Dup"); //~9709I~
        	return null;                                           //~9709I~
        }                                                          //~9709I~
//      connectionType=PmemberRole;                               //~@002I~//~9620R~
		BTCDialog dlg=new BTCDialog();                             //~v@@@I~
//  	dlg.ufdlg=UFDlg.newInstance(dlg,R.string.Title_Bluetooth,R.layout.btcdialog,//~v@@@R~//~9227R~
    	UFDlg.setBundle(dlg,R.string.Title_Bluetooth,R.layout.btcdialog,//~9227R~
				UFDlg.FLAG_CLOSEBTN|UFDlg.FLAG_HELPBTN,            //~v@@@I~
//  			R.string.Title_Bluetooth,"BTCDialog");             //~v@@@I~//~9C13R~
    			R.string.Title_Bluetooth,HELPFILE);                //~9C13I~
        dlg.connectionType=PmemberRole;                            //~9620I~
                                                                   //~9620I~
        return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~9A23M~
    public static BTCDialog getBTDialog()                          //~9A23M~
    {                                                              //~9A23M~
        BTCDialog dlg=AG.aBTRDialog!=null ? AG.aBTRDialog : AG.aBTCDialog;//~9A23M~
        if (Dump.Y) Dump.println("BTRDialog:getBTDialog dlg="+dlg.toString());//~9A23M~
        return dlg;                                                //~9A23M~
    }                                                              //~9A23M~
    //******************************************                   //~9A23M~
    public static boolean isReconnecting()                         //~9A23M~
    {                                                              //~9A23M~
        boolean rc=BTRDialog.isShowing();                          //~9A23R~
        if (Dump.Y) Dump.println("BTCDialog:isReconnecting rc="+rc);//~9A23M~//~9B07R~
        return rc;                                                 //~9A23M~
    }                                                              //~9A23M~
    //******************************************                   //~0107I~
    //*reconnecting BT or WD                                       //~0107I~
    //******************************************                   //~0107I~
    public static boolean isReconnectingAny()                      //~0107I~
    {                                                              //~0107I~
        boolean rc=isReconnecting() || WDA.isReconnecting();       //~0107I~
        if (Dump.Y) Dump.println("BTCDialog:isReconnectingAny rc="+rc);//~0107I~
        return rc;                                                 //~0107I~
    }                                                              //~0107I~
    //******************************************************************//~v@@@I~
    //*called at onCreateView to setup layout                      //~v@@@I~
    //*******************************************************************//~v@@@I~
	@Override                                                      //~v@@@I~
    public void initLayout(View Playoutview)                       //~v@@@I~
	{                                                              //~v@@@I~
    	super.initLayout(Playoutview);                             //~v@@@I~
        getComponent(Playoutview) ;                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected void getComponent(View PView)                          //~v@@@R~//~9A23R~
    {                                                              //~v@@@I~
    	layoutView=PView;                                          //~v@@@I~
        btnSettings      =              UButton.bind(PView,R.id.BTSettings,this);//~v@@@R~//~9817R~
        btnAccept        =              UButton.bind(PView,R.id.BTAccept,this);//~v@@@R~
        btnStopAccept    =              UButton.bind(PView,R.id.BTStopAccept,this);//~v@@@R~
        btnConnect       =              UButton.bind(PView,R.id.BTConnect,this);//~v@@@R~
        btnDisconnect    =              UButton.bind(PView,R.id.BTDisconnect,this);//~v@@@R~
        btnUnpair        =              UButton.bind(PView,R.id.BTUnpair,this);//~9210I~
//        btnGame          =              UButton.bind(PView,R.id.BTGame,this);//~v@@@R~//~@002R~
        btnDiscoverable  =              UButton.bind(PView,R.id.Discoverable,this);//~v@@@R~
        btnDiscover      =              UButton.bind(PView,R.id.Discover,this);//~v@@@R~
        btnDelete        =              UButton.bind(PView,R.id.Delete,this);//~v@@@R~
//        btnClose         =              UButton.bind(PView,R.id.Close,this);//~v@@@R~
//        btnHelp          =              UButton.bind(PView,R.id.Help,this);//~v@@@R~
        tvLocalDeviceName=(TextView)    UView.findViewById(PView,R.id.LocalDevice);//~v@@@R~
        tvClientMode     =(TextView)    UView.findViewById(PView,R.id.ClientMode);//~v@@@R~
        tvDiscoverable   =(TextView)    UView.findViewById(PView,R.id.LocalDeviceDiscoverable);//~v@@@R~
        cbSecureOption   =(CheckBox)    UView.findViewById(PView,R.id.BTSecureOption);//~v@@@R~
        llSnackbarParent =(LinearLayout)UView.findViewById(PView,R.id.snackbarParent);//~v@@@R~
        etYourName      =               UEditText.bind(PView,R.id.YourName,this);//~v@@@R~//~9905R~
        GL=new GroupList();                                 //~1Aa5I~//~9722M~//~9724M~
        GL.init(PView,etYourName);	//tell views                    //~1Aa5I~//~9722M~//~9723R~//~9724I~
                                                                   //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    //*called at onActivityCreated                                 //~v@@@I~
    //******************************************                   //~v@@@I~
	@Override                                                      //~v@@@I~
    public void setupDialog(Bundle Pbundle)                        //~v@@@R~
	{                                                              //~v@@@I~
        Dialog dlg=getDialog();                                    //~v@@@I~
		setSize(dlg);                                              //~v@@@I~
        init();                                                    //~v@@@I~
    }                                                              //~v@@@I~
    //*****************************************                    //~v@@@I~
    private static void setSize(Dialog Pdlg)                       //~v@@@I~
    {                                                              //~v@@@I~
        WindowManager.LayoutParams lp=Pdlg.getWindow().getAttributes();//~v@@@I~
        int ww=AG.scrWidth;                                        //~v@@@I~
        lp.width=(int)(ww*0.9);                                    //~v@@@I~
        Pdlg.getWindow().setAttributes(lp);                        //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog:setSize width="+lp.width);//~v@@@I~
    }                                                              //~v@@@I~
    //*****************************************                    //~v@@@I~
    public static void setSize()                                   //~v@@@I~
    {                                                              //~v@@@I~
    //************************                                     //~v@@@I~
        if (isShowing())                                           //~v@@@I~
        {                                                          //~v@@@I~
        	if (Dump.Y) Dump.println("BTCDialog:setSize");         //~v@@@I~
	        Dialog dlg=AG.aBTCDialog.getDialog();                  //~v@@@I~
			setSize(dlg);                                          //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected void init()                                            //~v@@@R~//~9A21R~
    {                                                              //~v@@@I~
        statusPaired=Utils.getStr(ID_STATUS_PAIRED);               //~v@@@R~
        statusDiscovered=Utils.getStr(ID_STATUS_DISCOVERED);       //~v@@@R~
        statusConnected=Utils.getStr(ID_STATUS_CONNECTED);         //~v@@@R~
        statusConnectedOnce=Utils.getStr(ID_STATUS_CONNECTED_ONCE);//~v@@@R~
                                                                   //~v@@@I~
		members=AG.aBTMulti.BTGroup;                               //~v@@@M~
        resetMembersMode(members);                                 //~9817I~
//        if (SdeviceList==null)                                     //~v@@@I~//~@002R~
			SdeviceList=new DeviceDataList();                      //~v@@@I~
//  	updateButtonStatus();                                      //~v@@@R~
    	setFromPropSecureOption();                                 //~v@@@I~
        UView.pushSnackbarParent(llSnackbarParent);                //~v@@@I~
        AG.aBTCDialog=this;	//used when PartnerThread detected err//~v@@@R~
	}                                                              //~v@@@I~
    //******************************************                   //~9817I~
    protected void resetMembersMode(Members Pmembers)                //~9817I~//~9A21R~
    {                                                              //~9817I~
        if (Dump.Y) Dump.println("BTCDialog:resetMembersMode");    //~9817I~
        Pmembers.resetMode(CM_BT);                                 //~9817I~
    }                                                              //~9817I~
    //******************************************                   //~v@@@I~
    private void updateDialog()                                    //~v@@@I~
    {                                                              //~v@@@I~
    	try                                                        //~9A21I~
        {                                                          //~9A21I~
            displayLocalDevice(); //local devicename                   //~v@@@R~//~9A21R~
            displayGroup();  //group member list(connected)            //~v@@@I~//~9A21R~
            displayRemoteDevice(); //paired device list                //~v@@@R~//~9A21R~
            updateButtonStatus();                                      //~v001R~//~9A21R~
        }                                                          //~9A21I~
        catch(Exception e)                                         //~9A21I~
        {                                                          //~9A21I~
            Dump.println(e,"BTCDialog:updateDialog");              //~9A21I~
        }                                                          //~9A21I~
	}                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onDestroyView() {                                  //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog:onDestroyView");//~v@@@I~
        super.onDestroyView();                                     //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onStart() {                                        //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog:onStart");//~v@@@I~
        super.onStart();                                           //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onPause() {                                        //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog:onPause");             //~v@@@I~
        super.onPause();                                           //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onResume() {                                       //~v@@@I~
        super.onResume();                                          //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog:onResume");            //~v@@@M~
	    updateDialog();	//may changed by Settings Button           //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onCreate(Bundle savedInstanceState) {              //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog:onCreate");//~v@@@I~
        super.onCreate(savedInstanceState);                        //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onDestroy() {                                      //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog:onDestroy");//~v@@@I~
        super.onDestroy();                                         //~v@@@I~
        AG.aBTCDialog=null;                                        //~v@@@I~
    }                                                              //~v@@@I~
//    //******************************************                   //~v@@@I~//~@002R~
//    @Override                                                      //~v@@@I~//~@002R~
//    public void onDismiss(DialogInterface Pdialog) {               //~v@@@R~//~@002R~
//        if (Dump.Y) Dump.println("BTCDialog:onDismiss");//~v@@@I~//~@002R~
//        onDismissDialog();                                          //~v@@@I~//~@002R~
////        UView.popSnackbarParent();                                 //~v@@@I~//~@002R~
//        super.onDismiss(Pdialog);                                //~@002R~
//    }                                                              //~v@@@I~//~@002R~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onClickOther(int Pbuttonid)                       //~v@@@R~
	{                                                              //~v@@@I~
	    GMsg.clearMsgbar();	//MainView and GameView                //~0113I~
	    waitingDialog=0;                                           //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog:onClickButton id="+Pbuttonid);//~v@@@R~
        switch(Pbuttonid)                                    //~v@@@R~
        {                                                          //~v@@@R~
            case R.id.BTSettings:                                  //~v@@@R~//~9817R~
                onClickSettings();                                 //~v@@@R~//~9817R~
                break;                                             //~v@@@R~//~9817R~
            case R.id.BTAccept:                                    //~v@@@R~
                onClickAccept();                                   //~v@@@R~
                break;                                             //~v@@@R~
            case R.id.BTStopAccept:                                //~v@@@R~
                onClickStopAccept();                               //~v@@@R~
                break;                                             //~v@@@R~
            case R.id.BTConnect:                                   //~v@@@R~
                onClickConnect();                                  //~v@@@R~
                break;                                             //~v@@@R~
            case R.id.BTDisconnect:                                //~v@@@R~
                onClickDisconnect();                               //~v@@@R~
                break;                                             //~v@@@R~
            case R.id.BTUnpair:                                    //~9210I~
                onClickUnpair();                                   //~9210I~
                break;                                             //~9210I~
            case R.id.Discoverable:                                //~v@@@R~
                onClickDiscoverable();                             //~v@@@R~
                break;                                             //~v@@@R~
            case R.id.Discover:                                    //~v@@@R~
                onClickDiscover();                                 //~v@@@R~
                break;                                             //~v@@@R~
            case R.id.Delete:                                      //~v@@@R~
                onClickDelete();                                   //~v@@@R~
                break;                                             //~v@@@R~
//                case R.id.Close:                                 //~v@@@R~
//                    onClickClose();                              //~v@@@R~
//                    break;                                       //~v@@@R~
//            case R.id.BTGame:                                      //~v@@@R~//~@002R~
//                onClickGame();                                     //~v@@@R~//~@002R~
//                break;                                             //~v@@@R~//~@002R~
//                case R.id.Help:                                  //~v@@@R~
//                    onClickHelp();                               //~v@@@R~
//                    break;                                       //~v@@@R~
            default:                                               //~v@@@R~
                if (Dump.Y) Dump.println("BTCDialog:onClick unknown");//~v@@@R~
        }                                                          //~v@@@R~
        showWaitingMsg();                                          //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void onClickSettings()                                 //~v@@@R~
	{                                                              //~v@@@I~
        if (callSettingsBT())	//started activity                 //~v@@@R~
        {                                                          //~v@@@R~
			if (Dump.Y) Dump.println("BTCDialog:onClickSettings after start acitivity");//~v@@@R~
        }                                                          //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void onClickAccept()                                   //~v@@@R~
	{                                                              //~v@@@I~
		if (Dump.Y) Dump.println("BTCDialog:onClickAccept");       //~9731I~
        if (getYourName(true/*issue toast*/)==null)                //~v@@@R~
        	return;                                                //~v@@@I~
       	swSecure=getSecureOption();                                //~v@@@R~
    	if (AG.aBTMulti.onAccept(swSecure))                        //~v@@@R~
        {                                                          //~v@@@I~
			if (Dump.Y) Dump.println("BTCDialog:onClickAccept Accepting");//~9731I~
//      	waitingDialog=STRID_ACCEPT;                            //~v@@@I~//~9731R~
            connectionStatus=CS_ACCEPTING;                         //~v@@@I~
            updateButtonStatus();                                  //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void onClickStopAccept()                               //~v@@@R~
	{                                                              //~v@@@I~
		stopListen();                                              //~v@@@R~
        connectionStatus=CS_ACCEPT_FAILED;                         //~v@@@I~
        updateButtonStatus();                                      //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void onClickConnect()                                  //~v@@@R~
	{                                                              //~v@@@I~
        if (getYourName(true/*issue toast*/)==null)                //~v@@@I~
        	return;                                                //~v@@@I~
        swSecure=getSecureOption();                                //~v@@@I~
        if (connectPartner(swSecure))                              //~v@@@I~
        {                                                          //~v@@@M~
            waitingDialog=STRID_CONNECT;                           //~v@@@M~
            connectionStatus=CS_CONNECTING;                        //~v@@@R~
            updateButtonStatus();                                  //~v@@@I~
        }                                                          //~v@@@M~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
//    //******************************************                   //~v@@@I~//~@002R~
//    private void onClickGame()                                     //~v@@@R~//~@002R~
//    {                                                              //~v@@@I~//~@002R~
//                if (startGame())                                   //~v@@@I~//~@002R~
//                {                                                  //~v@@@I~//~@002R~
//                    dismiss();                                     //~v@@@R~//~@002R~
//                }                                                  //~v@@@I~//~@002R~
//    }                                                              //~v@@@I~//~@002R~
    //******************************************                   //~v@@@I~
    private void onClickDisconnect()                               //~v@@@R~
	{                                                              //~v@@@I~
        connectionStatus=CS_DISCONNECTING;                         //~v@@@I~
        if (disconnectPartner())                                   //~v@@@R~
	        waitingDialog=R.string.Msg_WaitingDisconnect;          //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~9210I~
    private void onClickUnpair()                                   //~9210I~
	{                                                              //~9210I~
        connectionStatus=CS_UNPAIRING;                             //~9210I~
//      if (unpairPartner())                                       //~9210I~
//          waitingDialog=R.string.Msg_WaitingUnpair;              //~9210R~
        unpairPartner();                                           //~9210I~
    }                                                              //~9210I~
    //******************************************                   //~v@@@I~
    private void onClickDiscoverable()                             //~v@@@R~
	{                                                              //~v@@@I~
    	AG.aBTI.setDiscoverable();                                 //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void onClickDiscover()                                 //~v@@@R~
	{                                                              //~v@@@I~
        if (discover())                                            //~v@@@R~
        {                                                          //~v@@@R~
    		onDiscovery=true;                                      //~v@@@R~
            waitingDialog=R.string.Discover;                       //~v@@@R~
        }                                                          //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void onClickDelete()                                   //~v@@@R~
	{                                                              //~v@@@I~
    	deleteEntry();                                             //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@R~
    @Override                                                      //~v@@@I~
    protected void onClickClose()                                    //~v@@@R~
    {                                                              //~v@@@R~
		if (Dump.Y) Dump.println("BTCDialog:onClickClose");        //~v@@@I~
        getYourName(false/*no toast*/);                            //~v@@@R~
        dismiss();                                                 //~v@@@R~
    }                                                              //~v@@@R~
//    //*****************************                              //~v@@@R~
//    private void onClickHelp()                                   //~v@@@R~
//    {                                                            //~v@@@R~
//        HelpDialog.show(R.string.HelpTitle_Bluetooth,"BTCDialog");//~v@@@R~
//    }                                                            //~v@@@R~
    //*****************************                                //~v@@@I~
    @Override                                                      //~@002I~
    public void onDismissDialog()                                 //~v@@@R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("BTCDialog:onDismissDialog");     //~v@@@I~
    	cancelDiscover();                                          //~v@@@R~
		stopAll();                                                 //~v@@@I~
        afterDismiss(waitingDialog);                               //~v@@@R~
	}                                                              //~v@@@I~
    //**********************************************************   //~v@@@R~
    //*for member connected on Group device list                   //~v@@@I~
    //**********************************************************   //~v@@@I~
    public void onDisconnected(String Pdevicename)                 //~v@@@R~
    {                                                              //~v@@@I~
	    ProgDlg.dismissCurrent();                                  //~v@@@I~
//    	int old=connectionStatus;                                  //~@002R~
      	ConnectionStatus old=connectionStatus;                     //~@002R~
		if (Dump.Y) Dump.println("BTCDialog:onDisconnected oldstatus="+old+",connectionType="+connectionType);//~v@@@I~//~9731R~
        if (connectionType==ROLE_CLIENT) 	//server status is ACCEPTING//~v@@@I~//~@002R~
        {                                                          //~v@@@I~
	    	if (connectionStatus==CS_CONNECTED)  //for client,server is unknown                    //~v@@@I~//~9A22R~
		    	connectionStatus=CS_DISCONNECTED;                  //~9A22I~
		    notifyStatusChanged(BTService.STATE_NONE);	//reset STATE_CONNECTED//~v@@@M~
        }                                                          //~v@@@I~
		if (Dump.Y) Dump.println("BTCDialog:onDisconnected newstatus="+connectionStatus);//~9813I~
    	updateDeviceStatus(Pdevicename,null,ID_STATUS_CONNECTED_ONCE);//~v@@@R~
        updateDialog();                                            //~v@@@I~
//    	updateButtonStatus();                                      //~v001R~
        if (old==CS_DISCONNECTING)                                 //~v@@@I~
			UView.showToast(Utils.getStr(R.string.InfoDisconnected,Pdevicename));//~v@@@R~
        else                                                       //~v@@@I~
            if (GC.isOnGameView())  //IOErrReqDlg may opened       //~9B07I~
            {                                                      //~0108I~
//  		UView.showToast(Utils.getStr(R.string.InfoDisconnected,Pdevicename));//~9B07I~//~0108R~
    			String msg=Utils.getStr(R.string.InfoDisconnected,Pdevicename);//~0108I~
        		GMsg.drawMsgbar(msg);                              //~0108I~
    			UView.showToast(msg);                              //~0108I~
            }                                                      //~0108I~
            else                                                   //~9B07I~
			Alert.showMessage(null/*title*/,Utils.getStr(R.string.InfoDisconnected,Pdevicename));//~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~3203I~
    private boolean discover()                                     //~1AbWI~
    {                                                              //~3203I~
		swCancelDiscover=false;                                    //~3205I~
      	return AG.aBTI.discover();                                 //~v@@@R~
    }                                                              //~3203I~
    //******************************************                   //~3203I~
    private void cancelDiscover()                                       //~3203I~
    {                                                              //~3203I~
    	if (onDiscovery)                                           //~3203I~
        {                                                          //~3203I~
	        AG.aBTI.cancelDiscover();                               //~3203I~//~v@@@R~
            onDiscovery=false;                                     //~3203I~
		    swCancelDiscover=true;                                 //~3205I~
        }                                                          //~3203I~
    }                                                              //~3203I~
//    //******************************************                   //~3201I~//~@002R~
//    private boolean startGame()                                    //~3201I~//~@002R~
//    {                                                              //~3201I~//~@002R~
//        if (AG.aBTI.isConnectionAlive())                               //~@@@2I~//~3201I~//~v@@@R~//~@002R~
//        {                                                          //~@@@2I~//~3201I~//~@002R~
//            AG.aGame.startGame();//~@@@2I~//~3201I~              //~@002R~
//            return true;                                                //~@@@2I~//~3201I~//~@002R~
//        }                                                          //~@@@2I~//~3201I~//~@002R~
//        errNoThread();                                             //~3201I~//~@002R~
//        return false;                                              //~3201I~//~@002R~
//    }                                                              //~3201I~//~@002R~
    //******************************************                   //~3201I~
    private boolean disconnectPartner()                               //~3201I~//~v@@@R~
    {
        int idx;//~3201I~
	    idx=getSelected();                                         //~v@@@R~
        if (idx==-1)                                               //~v@@@R~
        	return false;                                          //~v@@@R~
//      lastSelected=idx;                                          //~v@@@I~//~@002R~
        UListViewData ld=getListData(idx);                         //~v@@@R~
        String name=ld.itemtext;                                   //~v@@@I~
		if (Dump.Y) Dump.println("BTCDialog:disconnectPartner idx="+idx+",name="+name);//~v@@@I~
        DeviceData data=SdeviceList.get(name);                     //~v@@@I~
        String addr=data.addr;                                     //~v@@@I~
        disconnectingDevice=name;                                  //~v@@@R~
        if (ld.itemint!=ID_STATUS_CONNECTED)                       //~v@@@I~
        {                                                          //~v@@@I~
            UView.showToast(R.string.ErrNotSelectedConnected);     //~v@@@I~
            return false;                                          //~v@@@R~
        }                                                          //~v@@@I~
        return AG.aBTMulti.disconnect(name);                       //~v@@@R~
//	    UView.showToast(AG.resource.getString(R.string.InfoDisconnectRequested,name));//~v@@@R~
  	}                                                              //~3201I~
    //******************************************                   //~9210I~
    private boolean unpairPartner()                                //~9210I~
    {                                                              //~9210I~
        int idx;                                                   //~9210I~
	    idx=getSelected();                                         //~9210I~
        if (idx==-1)                                               //~9210I~
        	return false;                                          //~9210I~
        UListViewData ld=getListData(idx);                         //~9210I~
        String name=ld.itemtext;                                   //~9210I~
		if (Dump.Y) Dump.println("BTCDialog:unpairPartner idx="+idx+",name="+name);//~9210I~
        DeviceData data=SdeviceList.get(name);                     //~9210I~
        String addr=data.addr;                                     //~9210I~
        unpairingDevice=name;                                      //~9210I~
//        if (ld.itemint!=ID_STATUS_CONNECTED)                     //~9210I~
//        {                                                        //~9210I~
//            UView.showToast(R.string.ErrNotSelectedConnected);   //~9210I~
//            return false;                                        //~9210I~
//        }                                                        //~9210I~
        return AG.aBTMulti.unpair(name,addr);                      //~9210I~
  	}                                                              //~9210I~
    //******************************************                   //~3201I~
    private void stopListen()                                      //~3201I~
    {                                                              //~3201I~
        AG.aBTI.stopListen();                                       //~3201R~//~v@@@R~
  	}                                                              //~3201I~
    //******************************************                   //~v@@@I~
    private void stopConnect()                                     //~v@@@I~
    {                                                              //~v@@@I~
        AG.aBTI.stopConnect();                                     //~v@@@R~
  	}                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void stopAll()                                         //~v@@@I~
    {                                                              //~v@@@I~
        AG.aBTI.stopAll();	//stopConnect,stopAccept                                          //~v@@@I~//~9731R~
		if (Dump.Y) Dump.println("BTCDialog:stopAll old connectionStatus="+connectionStatus);//~9813I~
        if (connectionStatus==CS_ACCEPTING)                        //~9808I~
        	connectionStatus=CS_UNKNOWN;                             //~9808I~
        else                                                       //~9808I~
        if (connectionStatus==CS_CONNECTING)                       //~9808I~
        	connectionStatus=CS_UNKNOWN;                             //~9808I~
		if (Dump.Y) Dump.println("BTCDialog:stopAll new connectionStatus="+connectionStatus);//~9813I~
  	}                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void notifyStatusChanged(int Pstat)                    //~v@@@I~
    {                                                              //~v@@@I~
        AG.aBTI.notifyStatusChanged(Pstat);                        //~v@@@I~
  	}                                                              //~v@@@I~
    //******************************************                   //~3202I~
    //*LocalDeviceName                                             //~v@@@I~
    //******************************************                   //~v@@@I~
    private void displayLocalDevice()                                   //~3202I~//~v@@@R~
    {                                                              //~3202I~
        String dev;
        TextView v;//~3202I~
    //************************                                     //~3202I~
        v=tvLocalDeviceName;              //~v101I~                //~v@@@R~
        if (AG.LocalDeviceName!=null)                              //~3202I~
        	dev=AG.LocalDeviceName;                                //~3202I~
        else                                                       //~3202I~
        	dev=Utils.getStr(R.string.UnknownDeviceName); //~3202I~//~v@@@R~
        myDevice=dev;                                              //~v101I~
        v.setText(dev);                                            //~3202I~
        displayDiscoverableStatus();                               //~1Af3I~
	    if (connectionType==ROLE_CLIENT)                             //~v@@@R~//~@002R~
        {                                                          //~v@@@I~
	        tvClientMode.setText(Utils.getStr(R.string.ModeClent));//~v@@@I~
	        tvClientMode.setTextColor(COLOR_CLIENT);               //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
	    if (connectionType==ROLE_SERVER)                             //~v@@@I~//~@002R~
        {                                                          //~v@@@I~
	        tvClientMode.setText(Utils.getStr(R.string.ModeServer));//~v@@@I~
	        tvClientMode.setTextColor(COLOR_SERVER);               //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
	        tvClientMode.setText("");                              //~v@@@I~
        }                                                          //~v@@@I~
	    showYourName();                                            //~v@@@I~
    }                                                              //~3202I~
    //******************************************                   //~3203I~
    //*at opened dialog                                            //~1AbRI~
    //******************************************                   //~1AbRI~
    private void displayRemoteDevice()                      //~v101R~    //~@@@2R~//~v@@@R~
    {                                                              //~3203I~        String l;                                                  //~3203I~
        if (Dump.Y) Dump.println("BTCDialog displayRemoteDevice");//~1AbSI~//~v@@@R~
        if (DL==null)                                              //~v@@@I~
        {                                                          //~v@@@I~
        	DL=new ListBT(layoutView,RID_DEVICELIST,R.layout.textrowlist_bt);//~1A6fI~//~v@@@R~
        	DL.setBackground(COLOR_BG_DEVICE_LIST);                             //~3203I~//~3209R~//~@@@2R~//~v@@@R~
        }                                                          //~v@@@I~
        pairedDeviceList=AG.aBTI.getPairDeviceList();               //~1AbTI~//~v@@@R~
        if (pairedDeviceList!=null)                                //~1AbUI~
	        SdeviceList.merge(pairedDeviceList,ID_STATUS_PAIRED);           //~1AbTI~//~1AbUR~
	    SdeviceList.merge(members,ID_STATUS_CONNECTED);            //~v@@@R~
  		displayRemoteDeviceListView();                             //~1AbTI~//~v@@@R~
	}                                                              //~1AbSI~//~1AbTI~
    //******************************************                   //~9210I~
    private void removeUnpaired(String Pname,String Paddr)         //~9210I~
    {                                                              //~3203I~        String l;//~9210I~
        if (Dump.Y) Dump.println("BTCDialog.removeUnpaired "+Pname+"="+Paddr);//~9210I~
        if (waitingid==R.string.Msg_WaitingUnpair)                 //~9210I~
	        ProgDlg.dismissCurrent();                              //~9210I~
	    SdeviceList.remove(Pname);                           //~9210I~
	}                                                              //~9210I~
    //******************************************                   //~1AbSI~//~1AbTI~
    private void displayRemoteDeviceListView()                     //~1AbTI~//~v@@@R~
    {                                                              //~1AbTI~
        if (Dump.Y) Dump.println("BTCDialog displayRemoteDeviceListView");//~1AbTI~//~v@@@R~
        String prevDeviceName=getPreviousSelction(PKEY_BT_DEVICE_SELECTION);//~va13I~
        ArrayList<String> keys=new ArrayList<String>(SdeviceList.devlist.keySet());//~1AbTI~//~1AbUR~
        int prevSelection=-1;                                      //~va13I~
        int ctr=keys.size();                                       //~1AbTI~
        DL.removeAll(false/*no notifyChanged*/);                   //~v@@@R~
        for (int ii=0;ii<ctr;ii++)                                 //~1AbTI~
        {                                                          //~1AbTI~
        	String nm=keys.get(ii);                                //~1AbTI~
            if (nm.equals(prevDeviceName))                         //~va13I~
            	prevSelection=ii;                                  //~va13I~
            if (Dump.Y) Dump.println("BTCDialog:displayRemoteDeviceListView nm="+nm+",prev="+prevDeviceName+",prevSelection="+prevSelection);//~va13I~
            DeviceData data=SdeviceList.get(nm);                        //~1AbTR~//~1AbUR~
            String addr=data.addr;                                 //~1AbTI~
            int stat=data.stat;                                    //~1AbTI~
            if (Dump.Y) Dump.println("BTCDialog:displayRemoteDeviceListView DeviceList ii="+ii+",addr="+addr+",name="+nm);//~1AbTI~//~v@@@R~
            switch(stat)                                       //~1AbTI~//~v@@@R~
            {                                                  //~1AbTI~//~v@@@R~
            case ID_STATUS_PAIRED:                             //~1AbTI~//~v@@@R~
                DL.add(nm,statusPaired,ID_STATUS_PAIRED);      //~1AbTI~//~v@@@R~
                break;                                         //~1AbTI~//~v@@@R~
            case ID_STATUS_CONNECTED:                     //~1AbTI~//~v@@@R~
            	String yn=members.getYourName(nm);                 //~v@@@M~
                if (yn==null)                                      //~v@@@M~
                	yn="";                                         //~v@@@M~
                DL.add(nm,statusConnected+yn,ID_STATUS_CONNECTED);//~1AbTI~//~v@@@R~
                break;                                         //~1AbTI~//~v@@@R~
            case ID_STATUS_CONNECTED_ONCE:                         //~v@@@R~
                DL.add(nm,statusConnectedOnce,ID_STATUS_CONNECTED_ONCE);//~v@@@R~
                break;                                             //~v@@@R~
            case ID_STATUS_DISCOVERED:                             //~v@@@I~
                DL.add(nm,statusDiscovered,ID_STATUS_DISCOVERED);//~1AbTI~//~v@@@R~
            default:                                           //~1AbTI~//~v@@@I~
            }                                                  //~1AbTI~//~v@@@R~
        }                                                          //~1AbTI~
        DL.showBottom();                                           //~v@@@R~
        if (ctr==1)                                                //~@002R~
		    DL.setSelection(0);                                    //~@002I~
        else                                                       //~va13I~
        if (prevSelection>=0)                                      //~va13I~
		    DL.setSelection(prevSelection);                        //~va13R~
    }                                                              //~1AbTI~
    //******************************************                   //~9724I~
    private void displayGroup()                                    //~9724I~
    {                                                              //~9724I~
    	GL.displayGroup();                                         //~9724I~
    }                                                              //~9724I~
//    //******************************************                   //~v@@@I~//~9724R~
//    private void displayGroup()                                     //~v@@@I~//~9724R~
//    {                                                              //~v@@@I~//~9724R~
//        Members gl=members;                                        //~v@@@R~//~9724R~
//        connectedCtr=gl.getConnectedCtr();                         //~v@@@I~//~9724R~
//        memberCtr=gl.getMemberCtr();                               //~v@@@I~//~9724R~
//        if (Dump.Y) Dump.println("BTCDialog displayGroup connectedctr="+connectedCtr+",memberctr="+memberCtr);//~v@@@R~//~9724R~
//        if (GL==null)                                              //~v@@@R~//~9724R~
//        {                                                          //~v@@@I~//~9724R~
//            GL=new ListGL(layoutView,RID_GROUPLIST,R.layout.textrowlist_bt_group); //display member 2column/row//~v@@@R~//~9724R~
//            GL.setBackground(COLOR_BG_GROUP_LIST);                 //~v@@@I~//~9724R~
//        }                                                          //~v@@@I~//~9724R~
//        else                                                       //~v@@@R~//~9724R~
//            GL.removeAll(false/*notifyChanged later*/);            //~v@@@R~//~9724R~
//        int ctr=gl.getLength();                                    //~v@@@R~//~9724R~
//        if (Dump.Y) Dump.println("BTCDialog displayGroup gl.length="+ctr);//~9722I~//~9724R~
//        for (int ii=0;ii<ctr;ii+=2)                                //~v@@@I~//~9724R~
//        {                                                          //~v@@@I~//~9724R~
//            if (ii+1>=ctr)                                         //~v@@@I~//~9724R~
//               GL.add(YNDN(gl,ii),"",0);                           //~v@@@R~//~9724R~
//            else                                                   //~v@@@I~//~9724R~
//            {                                                      //~v@@@I~//~9724R~
//               GL.add(YNDN(gl,ii),YNDN(gl,ii+1),0);                //~v@@@R~//~9724R~
//            }                                                      //~v@@@I~//~9724R~
//        }                                                          //~v@@@I~//~9724R~
//        GL.notifyChanged();                                           //~v@@@I~//~9724R~
//        showYourName();                                            //~v@@@I~//~9724R~
//     }                                                              //~v@@@I~//~9724R~
//    //******************************************                   //~v@@@I~//~9924R~
//    //*member name by YoutrName and DeviceName                     //~v@@@I~//~9924R~
//    //******************************************                   //~v@@@I~//~9924R~
//    private static String YNDN(Members Pmembers,int Pidx)          //~v@@@R~//~9924R~
//    {                                                              //~v@@@I~//~9924R~
//        if (Pmembers.getThread(Pidx)==null)                        //~9724I~//~9924R~
//        {                                                          //~9724I~//~9924R~
//            return AG.aBTMulti.memberNotConnectedId;               //~9724I~//~9924R~
//        }                                                          //~9724I~//~9924R~
//        String dn=Pmembers.getName(Pidx);   //devicename           //~v@@@I~//~9924R~
////      if (dn.contains(BTMulti.memberNotConnectedId))             //~v@@@I~//~@002R~//~9924R~
//        if (dn.contains(AG.aBTMulti.memberNotConnectedId))         //~@002I~//~9924R~
//            return dn;                                             //~v@@@I~//~9924R~
//        String yn=Pmembers.getYourName(Pidx);                      //~v@@@R~//~9924R~
//        if (yn==null||yn.equals(""))                               //~v@@@R~//~9924R~
//            yn=Utils.getStr(R.string.UnknownYourName);             //~v@@@I~//~9924R~
//        return yn;                                                 //~v@@@I~//~9924R~
//    }                                                              //~v@@@I~//~9924R~
    //******************************************                   //~3203I~
    private void discoveryFinished()                                      //~3203I~//~v@@@R~
    {                                                              //~3203I~
    	String[] sa=AG.aBTI.getNewDevice(); //not Bonded devices by ACTION_FOUND//~v@@@R~
        if (sa==null)                                              //~3203I~
        {                                                          //~3203I~
            if (swCancelDiscover)                                  //~3205I~
	        	infoDiscoverCanceled();                            //~3205I~
            else                                                   //~3205I~
	        	errNoNewDevice();                                      //~3203I~//~3205R~
	        ProgDlg.dismissCurrent();                                     //~3203I~
            return;                                                //~3203I~
        }                                                          //~3203I~
		SdeviceList.merge(sa,ID_STATUS_DISCOVERED);                //~1AbUI~
    	if (Dump.Y) Dump.println("BTCDialog.discoveryFinished after merge="+Utils.toString(sa));//~va10I~
        ProgDlg.dismissCurrent();                                         //~3203R~
        infoNewDevice(sa.length/2);                                //~3203R~
	    renewal(RENEWAL_DEVICELIST);                               //~v@@@I~
    }                                                              //~3203I~
    //******************************************                   //~3203I~
    private boolean connectPartner(boolean Psecure)                //~1A60I~//~v@@@R~//~9A23R~
    {                                                              //~3203I~
	    int idx=getSelected();                                     //~3203I~
        if (idx==-1)                                               //~3203I~
        	return false;                                          //~3203I~
//      lastSelected=idx;                                          //~3205I~//~@002R~
		UListViewData ld=getListData(idx);                              //~1AbUI~//~v@@@R~
        String name=ld.itemtext;                                   //~1AbUI~
        DeviceData data=SdeviceList.get(name);                     //~1AbUI~
        if (data==null)                                            //~v@@@I~
        {                                                          //~v@@@I~
    		UView.showToast("intennal err "+name+" is not on device list");//~v@@@I~
            return false;                                          //~v@@@I~
        }                                                          //~v@@@I~
        String addr=data.addr;                                     //~1AbUI~
        connectingDevice=name;                                     //~v101I~
        if (ld.itemint!=ID_STATUS_PAIRED)                          //~1Ac5I~
    		if (!AG.aBTMulti.chkDiscoverable(Psecure,false/*not server*/))     //~1Ac5I~
                return false;                                      //~1Ac5I~
        return AG.aBTMulti.onConnect(name,addr,Psecure);           //~v@@@R~
    }                                                              //~3203I~
    //******************************************                   //~1AbSI~
    private boolean deleteEntry()                                  //~1AbSI~
    {                                                              //~1AbSI~
	    int idx=getSelected();                                     //~1AbSI~
        if (idx==-1)                                               //~1AbSI~
        	return false;                                          //~1AbSI~
//      lastSelected=0;                                            //~1AbSI~//~@002R~
		UListViewData ld=getListData(idx);                              //~1AbSI~//~v@@@R~
        String name=ld.itemtext;                                   //~1AbSI~
        String status=ld.itemtext2;                                //~1AbSI~
        if (Dump.Y) Dump.println("BTCDialog deleteEntry name="+name+",status="+status);//~1AbSI~//~v@@@R~
        if (status.equals(statusPaired))                           //~1AbSI~
    		UView.showToast(R.string.WarningDeletingPaired);       //~1AbSI~//~v@@@R~
        deleteDeviceList(name);                                        //~1AbTI~//~1AbUR~
        deleteFromListView(idx);                                   //~1AbSI~
        return true;                                               //~1AbSI~
    }                                                              //~1AbSI~
    //******************************************                   //~1A6fR~//~v@@@R~
    public UListViewData getListData(int Pidx) //@@@@test parscelable//~v@@@R~
    {                                                              //~1A6fR~//~v@@@R~
        UListViewData ld;                                               //~1A6fR~//~v@@@R~
        try                                                        //~1A6fR~//~v@@@R~
        {                                                          //~1A6fR~//~v@@@R~
            ld=DL.arrayData.get(Pidx);                             //~1A6fR~//~v@@@R~
        }                                                          //~1A6fR~//~v@@@R~
        catch(Exception e)                                         //~1A6fR~//~v@@@R~
        {                                                          //~1A6fR~//~v@@@R~
            Dump.println(e,"BTCDialog:getListData");     //~1A6fR~ //~v@@@R~
            ld=DL.new UListViewData("OutOfBound",Color.BLACK/*dummy*/);    //~1A6fR~//~v@@@R~
        }                                                          //~1A6fR~//~v@@@R~
        return ld;                                                 //~1A6fR~//~v@@@R~
    }                                                              //~1A6fR~//~v@@@R~
    //******************************************                   //~3203I~
	private int getSelected()                                      //~3203I~
    {                                                              //~3203I~
	    int idx=DL.getValidSelectedPos();                           //~3203I~
        if (idx==-1)                                               //~3203I~
        {                                                          //~3203I~
    		UView.showToast(R.string.ErrNotSelected);                  //~v101I~//~v@@@R~
        }                                                          //~v@@@I~
        return idx;                                                //~3203I~
    }                                                              //~3203I~
    //******************************************                   //~3201I~
	protected void afterDismiss(int Pwaiting)                         //~3201I~//~9724R~//~9A23R~
    {                                                              //~3201I~
		if (Dump.Y) Dump.println("BTCDialog:afterDismiss");        //~v@@@I~
    	setToPropSecureOption();                                   //~1AbuI~
        waitingid=Pwaiting;                                        //~3203I~
    	if (Dump.Y) Dump.println("BTCDialog afterDismiss");     //~3201I~//~3204R~//~v@@@R~
        showWaitingMsg();                                          //~v@@@R~
    	if (Dump.Y) Dump.println("BTCDialog afterDismiss return connectionType="+connectionType);//~3207I~//~v@@@R~//~va13I~
        AG.aBTCDialog=null;                                     //~1A6kI~//~v@@@R~
//      boolean enable=(connectionType!=ROLE_CLIENT)&&(AG.aBTMulti.BTGroup.getConnectedCtr()!=0);//~9724I~//~va66R~
//      AG.aMainView.enableStartGame(enable); //enable even connectCtr=0 for play alone//~va66R~
    }                                                              //~3201I~
    //******************************************                   //~v@@@I~
	public void showWaitingMsg()                                   //~v@@@R~
    {                                                              //~v@@@I~
        if (waitingDialog==0)                                      //~v@@@I~
        	return;                                                //~v@@@I~
    	setToPropSecureOption();                                   //~v@@@I~
        waitingid=waitingDialog;                                   //~v@@@R~
        String msg;//~v@@@I~
    	if (Dump.Y) Dump.println("BTCDialog showWaitingMsg msgid="+waitingid);//~v@@@R~
        switch (waitingid)                                         //~v@@@R~
        {                                                          //~v@@@I~
        case STRID_ACCEPT:                                         //~v@@@I~
            msg=getMsgStringBTAccepting(myDevice,swSecure); //~v@@@I~
			waitingResponse(R.string.Title_WaitingAccept,msg);     //~v@@@I~
            break;                                                 //~v@@@I~
        case STRID_CONNECT:                                        //~v@@@I~
            msg=getMsgStringBTConnecting(connectingDevice,swSecure);//~v@@@I~
			waitingResponse(R.string.Title_WaitingConnect,msg);    //~v@@@I~
            break;                                                 //~v@@@I~
        case R.string.Discover:                                    //~v@@@I~
			waitingResponse(R.string.Title_WaitingDiscover,R.string.Msg_WaitingDiscover);//~v@@@I~
            break;                                                 //~v@@@I~
	    case R.string.Msg_WaitingDisconnect:                       //~v@@@I~
        	msg=AG.resource.getString(waitingid,disconnectingDevice);//~v@@@I~
			waitingResponse(R.string.Title_WaitingDisconnect,msg); //~v@@@I~
            break;                                                 //~v@@@I~
	    case R.string.Msg_WaitingUnpair:                           //~9210I~
        	msg=AG.resource.getString(waitingid,unpairingDevice);  //~9210I~
			waitingResponse(R.string.Title_WaitingUnpair,msg);     //~9210I~
            break;                                                 //~9210I~
        }                                                          //~v@@@I~
        waitingDialog=0;                                           //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~3201I~
	private void waitingResponse(int Ptitleresid,int Pmsgresid)    //~3201R~
    {                                                              //~3201I~
    	ProgDlg.showProgDlg(Ptitleresid,Pmsgresid,false/*cancelable*/,this/*ProgDlgI*/);//~1A2jI~//~v@@@R~
    }                                                              //~3201I~
    //******************************************                   //~v101I~
	private void waitingResponse(int Ptitleresid,String Pmsg)      //~v101I~
    {                                                              //~v101I~
    	ProgDlg.showProgDlg(Ptitleresid,Pmsg,false/*cancelable*/,this);//~1A2jI~//~v@@@R~
    }                                                              //~v101I~
    //******************************************                   //~3201I~
	protected void errNoThread()                                     //~3201I~//~9A21R~
    {                                                              //~3201I~
    	UView.showToast(R.string.ErrNoThread);                     //~3201R~//~v@@@R~
    }                                                              //~3201I~
    //******************************************                   //~3203I~
	private void errNoNewDevice()                                  //~3203I~
    {                                                              //~3203I~
    	UView.showToast(R.string.ErrNoNewDevice);                  //~3203I~//~v@@@R~
    }                                                              //~3203I~
    //******************************************                   //~3206I~
	private void errConnectingForScan()                            //~3206I~
    {                                                              //~3206I~
    	UView.showToast(R.string.ErrConnectingForScan);            //~3206I~//~v@@@R~
    }                                                              //~3206I~
	private void infoDiscoverCanceled()                             //~3205I~
    {                                                              //~3205I~
    	UView.showToast(R.string.InfoDiscoverCanceled);            //~3205I~//~v@@@R~
    }                                                              //~3205I~
    //******************************************                   //~3203I~
	private void infoNewDevice(int Pctr)                           //~3203I~
    {                                                              //~3203I~
    	UView.showToast(Utils.getStr(R.string.InfoNewDevice,Pctr));//~3203I~//~v@@@R~
    }                                                              //~3203I~
    //******************************************                   //~3203I~
    //*callback from ProDlg:onDismiss reason:0:cancel,1:dismiss                                   //~3203I~//~v@@@R~
    //******************************************                   //~3203I~
    @Override                                                      //~3203I~
	public void onCancelProgDlg(int Preason)                       //~3203I~
    {                                                              //~3203I~
    	if (Dump.Y) Dump.println("onCancelProgDlgI reason="+Preason);//~3203I~
        if (Preason==0)	//cancel                                   //~3203I~
        {                                                          //~3203I~
	    	if (Dump.Y) Dump.println("onCancelProgDlgI waitingID="+Integer.toHexString(waitingid));//~3203I~
        	if (waitingid==R.string.Discover)                       //~3203I~
	        	cancelDiscover();                                 //~3203I~
            else                                                   //~v@@@I~
        	if (waitingid==STRID_ACCEPT)                           //~v@@@I~
            {                                                      //~v@@@I~
		    	UView.showToast(Utils.getStr(R.string.InfoAcceptCanceled));//~v@@@I~
				onClickStopAccept();                                //~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
        	if (waitingid==STRID_CONNECT)                          //~v@@@I~
            {                                                      //~v@@@I~
		    	UView.showToast(Utils.getStr(R.string.InfoConnectCanceled));//~v@@@I~
				cancelConnect();                                   //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~3203I~
    }                                                              //~3203I~
//	public static class ListBT extends UListView              //@@@@test parcelable                                    //~1114//~1A6fI~//~v@@@R~//~@002R~
  	class ListBT extends UListView                                 //~@002R~
	{                                                              //~v@@@I~
    //*****************                                            //~1A6fI~//~v@@@M~
        ListBT(View Playout,int Plvid,int Prowresid)//~1A6fI~      //~v@@@R~
        {                                                          //~1A6fI~//~v@@@M~
            super(Playout,Plvid,Prowresid,null/*listener*/,UListView.CHOICEMODE_SINGLE);                     //~1A6fI~//~v@@@R~
        }                                                          //~1A6fI~//~v@@@M~
    //**********************************************************************//~1A6fI~//~v@@@M~
        @Override                                                  //~1A6fI~//~v@@@M~
        public View getViewCustom(int Ppos, View Pview, ViewGroup Pparent)//~1A6fI~//~v@@@M~
        {                                                          //~1A6fI~//~v@@@M~
        //*******************                                      //~1A6fI~//~v@@@M~
            if (Dump.Y) Dump.println("ListBT:getViewCustom Ppos="+Ppos+"CheckedItemPos="+((ListView)Pparent).getCheckedItemPosition());//~1A6fI~//~v@@@M~
            View v=Pview;                                          //~1A6fI~//~v@@@M~
            if (v == null)                                         //~1A6fI~//~v@@@M~
			{                                                      //~1A6fI~//~v@@@M~
                v=AG.inflater.inflate(rowId/*super*/,null);            //~1A65I~//~1A6fI~//~v@@@M~
            }                                                      //~1A6fI~//~v@@@M~
            TextView v1=(TextView)v.findViewById(BTROW_NAME);      //~1A6fI~//~v@@@M~
            TextView v2=(TextView)v.findViewById(BTROW_STATUS);    //~1A6fI~//~v@@@M~
            UListViewData ld=arrayData.get(Ppos);                  //~v@@@I~
            v1.setText(ld.itemtext);                               //~1A6fI~//~v@@@M~
            if (Ppos==selectedpos)                                 //~1A6fI~//~v@@@M~
            {                                                      //~1A6fI~//~v@@@M~
                v1.setBackgroundColor(bgColorSelected);   //~1A6fI~//~v@@@M~
                v1.setTextColor(bgColor);                 //~1A6fI~//~v@@@M~
            }                                                      //~1A6fI~//~v@@@M~
            else                                                   //~1A6fI~//~v@@@M~
            {                                                      //~1A6fI~//~v@@@M~
                v1.setBackgroundColor(bgColor);           //~1A6fI~//~v@@@M~
                v1.setTextColor(ld.itemcolor);            //~1A6fI~//~v@@@M~
            }                                                      //~1A6fI~//~v@@@M~
            String status;                                         //~1A6fI~//~v@@@M~
            if (ld.itemtext2==null)                                //~1A6fI~//~v@@@M~
            	status="";                                         //~1A6fI~//~v@@@M~
            else                                                   //~1A6fI~//~v@@@M~
            	status=ld.itemtext2;                               //~1A6fI~//~v@@@M~
            if (Dump.Y) Dump.println("ListBT:getViewCustom itemtext="+ld.itemtext+",itemtext2="+ld.itemtext2);//~9731I~
            v2.setText(status);                                    //~1A6fI~//~v@@@M~
            v2.setBackgroundColor(bgColor);               //~1A6fI~//~v@@@M~
            if (status.equals(statusPaired))                       //~1A6fI~//~v@@@M~
                v2.setTextColor(COLOR_STATUS_PAIRED);              //~1A6fI~//~v@@@M~
            else                                                   //~1A6fI~//~v@@@M~
            if (status.startsWith(statusConnected))                    //~1A6kI~//~v@@@R~
            {                                                      //~v@@@I~
	            v2.setText(status.substring(statusConnected.length()));//~v@@@I~
                v2.setTextColor(COLOR_STATUS_CONNECTED);  //~1A6kI~//~v@@@M~
            }                                                      //~v@@@I~
            else                                                   //~1A6kI~//~v@@@M~
                v2.setTextColor(COLOR_STATUS_DISCOVERED);          //~1A6fI~//~v@@@M~
            return v;                                              //~1A6fI~//~v@@@M~
        }                                                          //~1A6fI~//~v@@@M~
	}//class                                                       //~1A6fI~//~v@@@I~
//    //**********************************************************************//~v@@@I~//~9724R~
//    //*ListView GroupList *************************************************//~v@@@I~//~9724R~
//    //**********************************************************************//~v@@@I~//~9724R~
////  static class ListGL extends UListView                                   //@@@@test parcelable               //~1114//~v@@@R~//~@002R~//~9724R~
//    class ListGL extends UListView                                 //~@002R~//~9724R~
//    {                                                              //~v@@@I~//~9724R~
//    //*****************                                            //~v@@@I~//~9724R~
//        public ListGL(View Playout,int Plvid,int Prowresid)        //~v@@@R~//~9724R~
//        {                                                          //~v@@@I~//~9724R~
//            super(Playout,Plvid,Prowresid,null/*listener*/,UListView.CHOICEMODE_NONE);//~v@@@R~//~9724R~
//        }                                                          //~v@@@I~//~9724R~
//    //**********************************************************************//~v@@@I~//~9724R~
//        @Override                                                  //~v@@@I~//~9724R~
//        public View getViewCustom(int Ppos, View Pview,ViewGroup Pparent)//~v@@@I~//~9724R~
//        {                                                          //~v@@@I~//~9724R~
//        //*******************                                      //~v@@@I~//~9724R~
//            if (Dump.Y) Dump.println("BTCDialog.ListGL.getViewCustom Ppos="+Ppos+",CheckedItemPos="+((ListView)Pparent).getCheckedItemPosition());//~v@@@I~//~9722R~//~9724R~
//            View v=Pview;                                          //~v@@@I~//~9724R~
//            if (v == null)                                         //~v@@@I~//~9724R~
//            {                                                      //~v@@@I~//~9724R~
//                v=AG.inflater.inflate(rowId/*super*/,null);        //~v@@@I~//~9724R~
//            }                                                      //~v@@@I~//~9724R~
//            TextView v1=(TextView)v.findViewById(BTROW_MEMB1);     //~v@@@I~//~9724R~
//            TextView v2=(TextView)v.findViewById(BTROW_MEMB2);     //~v@@@I~//~9724R~
//            UListViewData ld=arrayData.get(Ppos);                  //~v@@@I~//~9724R~
//            v1.setText(ld.itemtext);                               //~v@@@I~//~9724R~
//            v2.setText(ld.itemtext2);                              //~v@@@I~//~9724R~
//            v1.setTextColor(memberColor(ld.itemtext));             //~v@@@I~//~9724R~
//            v2.setTextColor(memberColor(ld.itemtext2));            //~v@@@I~//~9724R~
//            v1.setBackgroundColor(bgColor);                        //~v@@@I~//~9724R~
//            v2.setBackgroundColor(bgColor);                        //~v@@@I~//~9724R~
//            if (Dump.Y) Dump.println("BTCDialog.ListGL.getViewCustom Ppos="+Ppos+",v1="+ld.itemtext+",v2="+ld.itemtext2);//~9722I~//~9724R~
//            return v;                                              //~v@@@I~//~9724R~
//        }                                                          //~v@@@I~//~9724R~
//        //**********************************************************************//~v@@@I~//~9724R~
//        private int memberColor(String Pyourname)                  //~v@@@R~//~9724R~
//        {                                                          //~v@@@I~//~9724R~
//            int color=Color.BLACK;                                 //~v@@@I~//~9724R~
//            if (Pyourname!=null)                                   //~v@@@R~//~9724R~
//            {                                                      //~v@@@I~//~9724R~
//                Members m=AG.aBTMulti.BTGroup;                     //~@002I~//~9724R~
//                String devname=m.getName(Pyourname);          //~v@@@R~//~@002R~//~9724R~
//                if (devname!=null)                                 //~v@@@I~//~9724R~
//                    if (devname.equals(AG.aBTMulti.serverDeviceName))//~v@@@R~//~9724R~
//                        color=COLOR_SERVER;                        //~v@@@R~//~9724R~
//                    else                                           //~v@@@R~//~9724R~
//                        color=COLOR_CLIENT;                        //~v@@@R~//~9724R~
//            }                                                    //~9724R~
//            return color;//~v@@@I~                               //~9724R~
//        }                                                          //~v@@@I~//~9724R~
//    }//class                                                       //~v@@@I~//~9724R~
	//**********************************************************************//~1AbuI~
    private boolean getSecureOption()                              //~1AbuI~
    {                                                              //~1AbuI~
    	return cbSecureOption.isChecked();                         //~1AbuI~
    }                                                              //~1AbuI~
	//**********************************************************************//~1AbuI~
    private void setFromPropSecureOption()                         //~1AbuI~
    {                                                              //~1AbuI~
    	swSecure=Utils.getPreference(PKEY_BTSECURE,BTSECURE_DEFAULT)!=0;//~1AbuI~
    	cbSecureOption.setChecked(swSecure);                       //~1AbuI~
    }                                                              //~1AbuI~
	//**********************************************************************//~1AbuI~
    private void setToPropSecureOption()                           //~1AbuI~
    {                                                              //~1AbuI~
    	swSecure=getSecureOption();                                //~1AbuI~
    	Utils.putPreference(PKEY_BTSECURE,swSecure?1:0);            //~1AbuI~
    }                                                              //~1AbuI~
    //***********************************************************************//~1AbvI~//~1AbuI~
    public static void closeDialog()                               //~1AbvI~//~1AbuI~
    {                                                              //~1AbvI~//~1AbuI~
    	if (Dump.Y) Dump.println("BTCDialog:closeDialog");//~1AbvI~//~1AbuI~//~v@@@R~
	    if (!isShowing())                                          //~v@@@I~
        {                                                          //~1AbvI~//~1AbuI~
	    	if (Dump.Y) Dump.println("BTCDialog:closeDialog dismiss");//~1AbvI~//~1AbuI~//~v@@@R~
		    AG.aBTCDialog.waitingDialog=0;               //~1AbvI~//~1AbuI~//~v@@@R~
        	AG.aBTCDialog.dismiss();                     //~1AbvI~//~1AbuI~//~v@@@R~
        }                                                          //~1AbvI~//~1AbuI~
    }                                                              //~1AbvI~//~1AbuI~
	//***********************************************************************************//~1AbEI~
    public static boolean callSettingsBT()                                  //~1AbEI~//~v@@@R~//~9A23R~
    {                                                              //~1AbEI~
   	    if (Dump.Y) Dump.println("BTCDialog:callSettingsBT");//~1AbEI~//~v@@@R~
        if (AG.aBTI.mBTC==null)                                     //~1AbWR~//~v@@@R~
        {                                                          //~1AbWR~
			BTI.BTNotAvailable();                                      //~1AbRI~//~1AbWI~//~v@@@R~
        	return false;                                                //~1AbWR~//~v@@@R~
        }                                                          //~1AbWR~
    	Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);//~1AbEI~
        AG.aMainActivity.startActivity(intent);                         //~1AbEI~
   	    if (Dump.Y) Dump.println("BTCDialog:callSettingsBT after startActivity");//~v@@@I~
        return true;                                               //~v@@@I~
    }                                                              //~1AbEI~
	//***********************************************************************************//~1AbRI~
	//*maintain group member                                       //~v@@@R~
	//***********************************************************************************//~1AbRI~
    private void addMember(String Pname,String Paddr)//~1AbRI~//~v@@@R~
    {                                                              //~1AbRI~
	    if (Dump.Y) Dump.println("BTCDialog addMember name="+Pname+",addr="+Paddr+",connectionType="+connectionType);//~1AbRI~//~1AbSR~//~v@@@R~
    }                                                              //~1AbRI~
	//***********************************************************************************//~1AbTI~
    private void deleteDeviceList(String Pname)                    //~1AbTI~
    {                                                              //~1AbTI~
	    if (Dump.Y) Dump.println("BTCDialog deleteDeviceList name="+Pname);//~1AbTI~//~v@@@R~
        DeviceData data=SdeviceList.get(Pname);                         //~1AbTI~//~1AbUR~
        if (data!=null)                                            //~1AbTI~//~1AbUR~
        {                                                          //~1AbTI~
        	SdeviceList.remove(Pname);                                  //~1AbTI~//~1AbUR~
	    	if (Dump.Y) Dump.println("BTCDialog deleteConnectedDevice deleted");//~1AbTI~//~v@@@R~
        }                                                          //~1AbTI~
    }                                                              //~1AbTI~
	//***********************************************************************************//~1AbSI~
    private void deleteFromListView(int Pindex)                    //~1AbSR~
    {                                                              //~1AbSI~
        if (Dump.Y) Dump.println("BTCDialog deleteFromListView idx="+Pindex);//~1AbSR~//~v@@@R~
        DL.remove(Pindex);                                         //~1AbSR~
    }                                                              //~1AbSI~
	//***********************************************************************************//~1AbUI~
    private void updateDeviceStatus(int PstatFrom,int PstatTo)     //~1AbUI~
    {                                                              //~1AbUI~
        if (Dump.Y) Dump.println("BTCDialog updateDeviceStatus");//~1AbUI~//~v@@@R~
        SdeviceList.updateStatusAll(PstatFrom,PstatTo);            //~1AbUI~
    }                                                              //~1AbUI~
	//***********************************************************************************//~v@@@I~
    private void updateDeviceStatus(String Pname,String Paddr,int Pstatus)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog updateDeviceStatus name="+Pname+",addr="+Paddr+",status="+Pstatus);//~v@@@I~
        SdeviceList.updateStatus(Pname,Paddr,Pstatus);             //~v@@@I~
    }                                                              //~v@@@I~
    //****************************************************         //~1AbTI~
    class DeviceDataList                             //~1AbTI~     //~1AbUR~
    {                                                              //~1AbTI~
	    private Map<String,DeviceData> devlist=new LinkedHashMap<String,DeviceData>();//~1AbTI~//~1AbUR~//~v@@@R~
    //*****************                                            //~1AbTI~
        public DeviceDataList()                                             //~1AbTI~//~1AbUR~
        {                                                          //~1AbTI~
        }                                                          //~1AbTI~
	    //*************************************************************//~1AbTI~
        private DeviceData get(String Pname)                        //~1AbTI~//~1AbUR~//~v@@@R~
        {                                                          //~1AbTI~
        	return devlist.get(Pname);                             //~1AbTI~
        }                                                          //~1AbTI~
	    //*************************************************************//~1AbTI~
        public void put(String[] Plist,int Pstat)                  //~1AbTI~
        {                                                          //~1AbTI~
        	for (int ii=0;ii<Plist.length/2;ii++)                  //~1AbTI~
            {                                                      //~1AbTI~
            	String name=Plist[ii*2];                           //~1AbTI~
            	String addr=Plist[ii*2+1];                         //~1AbTI~
            	DeviceData data=new DeviceData(name,addr,Pstat);   //~1AbTR~//~1AbUR~
                if (Dump.Y) Dump.println("DeviceDataList:put key="+name+",addr="+addr+",stat="+Pstat+",DeviceData="+data);//~1AbTI~//~1AbUR~
    			devlist.put(name,data);                            //~1AbTI~
            }                                                      //~1AbTI~
        }                                                          //~1AbTI~
	    //*************************************************************//~1AbUR~
        private void remove(String Pname)                           //~1AbUR~//~v@@@R~
        {                                                          //~1AbUR~
            if (Dump.Y) Dump.println("DeviceDataList:remove key="+Pname);//~1AbUR~
        	devlist.remove(Pname);                                 //~1AbUR~
        }                                                          //~1AbUR~
	    //*************************************************************//~1AbTI~
	    //*merge discovered device                                 //~v@@@R~
	    //*************************************************************//~1AbUR~
        private void merge(String[] Plist,int Pstat)                //~1AbTI~//~v@@@R~
        {                                                          //~1AbTI~
            if (Dump.Y) Dump.println("DeviceDataList:merge Plist="+Utils.toString(Plist));//~va10I~
        	for (int ii=0;ii<Plist.length/2;ii++)                  //~1AbTI~
            {                                                      //~1AbTI~
            	String name=Plist[ii*2];                           //~1AbTI~
            	String addr=Plist[ii*2+1];                         //~1AbTI~
                DeviceData data=get(name);                         //~1AbTR~//~1AbUR~//~v@@@R~
                if (data==null)                                    //~1AbTI~
                {                                                  //~1AbTI~
                	if (Dump.Y) Dump.println("DeviceDataList:merge data=null:add key="+addr+",name="+name+",stat="+Pstat);//~1AbTI~//~1AbUR~//~v@@@R~
            		DeviceData adddata=new DeviceData(name,addr,Pstat);//~1AbTI~//~1AbUR~
    				devlist.put(name,adddata);                     //~1AbTI~
                }                                                  //~1AbTI~
                else                                               //~1AbTI~
                {                                                  //~1AbTI~
                	if (Dump.Y) Dump.println("DeviceDataList:merge rep key="+addr+",name="+name+",stat  old="+data.stat+",stat="+Pstat+",DeviceData="+data);//~1AbTI~//~1AbUR~//~v@@@R~
                	data.addr=addr;                                //~1AbUR~
					data.stat=Pstat;                               //~1AbUR~//~v@@@R~
                }                                                  //~1AbTI~
            }                                                      //~1AbTI~
        }                                                          //~1AbTI~
	    //*************************************************************//~v@@@I~
	    //*Group member                                            //~v@@@I~
	    //*************************************************************//~v@@@I~
        private void merge(Members Pmembers,int Pstat)             //~v@@@R~
        {                                                          //~v@@@I~
        	for (int ii=0;ii<Pmembers.getLength();ii++)              //~v@@@I~
            {                                                      //~v@@@I~
            	String name=Pmembers.getName(ii);                  //~v@@@I~
            	String addr=Pmembers.getAddr(ii);                  //~v@@@I~
//              if (addr!=null)	//connected                        //~v@@@I~//~9731R~
                if (Pmembers.getThread(ii)!=null)	//connected    //~9816I~
                if (Pmembers.getThread(ii)!=null)	//connected    //~9731I~
                if (Pmembers.getBluetoothSocket(ii)!=null)	//connected//~9816I~
            	{                                                  //~v@@@I~
                	DeviceData data=get(name);                     //~v@@@I~
                	if (data==null)                                //~v@@@I~
                    {                                              //~v@@@I~
                        if (Dump.Y)
                            Dump.println("DeviceDataList:merge Member data=null:addr=" + addr + ",name=" + name + ",stat=" + Pstat);//~v@@@I~
                        DeviceData adddata = new DeviceData(name, addr, Pstat);//~v@@@I~
                        devlist.put(name, adddata);
                    }//~v@@@I~
                    else                                           //~v@@@R~
                    {                                              //~v@@@R~
                        if (Dump.Y) Dump.println("DeviceDataList:merge Member rep addr="+addr+",name="+name+",stat  old="+data.stat+",stat="+Pstat);//~v@@@R~
                        data.addr=addr;                            //~v@@@R~
                        data.stat=Pstat;                           //~v@@@R~
                    }                                              //~v@@@R~
            	}                                                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
//        //*************************************************************//~1AbUI~//~va10R~
//        public int search(String Pname)                            //~1AbUI~//~va10R~
//        {                                                          //~1AbUI~//~va10R~
//            int idx=-1;                                            //~1AbUI~//~va10R~
//            ArrayList<String> keys=new ArrayList<String>(devlist.keySet());    //names//~1AbUI~//~va10R~
//            if (Dump.Y) Dump.println("DeviceDataList:search ksys="+Utils.toString(devlist.keySet()));//~0602I~//~va10R~
//            for (int ii=0;ii<keys.size();ii++)                     //~1AbUI~//~va10R~
//            {                                                      //~1AbUI~//~va10R~
//                String name=keys.get(ii);                          //~1AbUI~//~va10R~
//                if (name.equals(Pname))                            //~1AbUI~//~va10R~
//                {                                                  //~1AbUI~//~va10R~
//                    idx=ii;                                        //~1AbUI~//~va10R~
//                    break;                                         //~1AbUI~//~va10R~
//                }                                                  //~1AbUI~//~va10R~
//            }                                                      //~1AbUI~//~va10R~
//            if (Dump.Y) Dump.println("DeviceDataList:search name="+Pname+",idx="+idx);//~1AbUI~//~va10R~
//            return idx;                                            //~1AbUI~//~va10R~
//        }                                                          //~1AbUI~//~va10R~
	    //*************************************************************//~1AbUI~
        private int updateStatusAll(int Pfrom,int Pto)              //~1AbUI~//~v@@@R~
        {                                                          //~1AbUI~
        	int ctr=0;                                             //~1AbUI~
            ArrayList<String> keys=new ArrayList<String>(devlist.keySet());    //names//~1AbUI~
        	for (int ii=0;ii<keys.size();ii++)                     //~1AbUI~
            {                                                      //~1AbUI~
            	String name=keys.get(ii);                          //~1AbUI~
                DeviceData data=get(name);                         //~1AbUI~
                if (data.stat==Pfrom)                              //~1AbUI~
                {                                                  //~1AbUI~
			        if (Dump.Y) Dump.println("DeviceDataList:updateStatus updated="+name);//~1AbUI~
                	data.stat=Pto;                                 //~1AbUI~
                    ctr++;                                         //~1AbUI~
                }                                                  //~1AbUI~
            }                                                      //~1AbUI~
	        if (Dump.Y) Dump.println("DeviceDataList:updateStatus updatectr="+ctr);//~1AbUI~
            return ctr;                                            //~1AbUI~
        }                                                          //~1AbUI~
	    //*************************************************************//~v@@@I~
        private void updateStatus(String Pname,String Paddr,int Pstatus)//~v@@@R~
        {                                                          //~v@@@I~
        	 DeviceData data=get(Pname);                           //~v@@@I~
             if (data==null)                                       //~v@@@I~
             {                                                     //~v@@@I~
		        if (Dump.Y) Dump.println("DeviceDataList:updateStatus add name="+Pname+",addr="+Paddr+",stat="+Pstatus);//~v@@@I~
            		DeviceData adddata=new DeviceData(Pname,Paddr,Pstatus);//~v@@@I~
    				devlist.put(Pname,adddata);                     //~v@@@I~
             }                                                     //~v@@@I~
             else                                                  //~v@@@I~
             {                                                     //~v@@@I~
		        if (Dump.Y) Dump.println("DeviceDataList:updateStatus update name="+Pname+",addr="+Paddr+",stat="+Pstatus);//~v@@@I~
				data.stat=Pstatus;                                   //~v@@@I~
             }                                                     //~v@@@I~
        }                                                          //~v@@@I~
    }//class                                                       //~1AbTI~
    //******************************************                   //~1AedI~
	public static String getMsgStringBTAccepting(String Pdevice,boolean Psecure)//~1AedI~
    {                                                              //~1AedI~
    	String secureopt=Utils.getStr(Psecure ? R.string.BTSecure : R.string.BTInSecure);//~1AedI~//~v@@@R~
		String msg=Utils.getStr(R.string.Msg_WaitingAccept,Pdevice,secureopt);//~1AedI~//~v@@@R~
        return msg;                                                //~1AedI~
    }                                                              //~1AedI~
    //******************************************                   //~1AedI~
	public static String getMsgStringBTConnecting(String Pdevice,boolean Psecure)//~1AedI~
    {                                                              //~1AedI~
    	String secureopt=Utils.getStr(Psecure ? R.string.BTSecure : R.string.BTInSecure);//~1AedI~//~v@@@R~
        String msg=Utils.getStr(R.string.Msg_WaitingConnect,Pdevice,secureopt);//~1AedI~//~v@@@R~
        return msg;                                                //~1AedI~
    }                                                              //~1AedI~
    //******************************************                   //~1Af1I~
    //*on MainThread                                               //~1Af1I~
    //******************************************                   //~1Af1I~
	public void renewal()                                          //~1Af1I~
    {                                                              //~1Af1I~
        if (Dump.Y) Dump.println("BTCDialog.renewal");   //~1Af1I~ //~v@@@R~
        displayLocalDevice();                                           //~1Af1I~//~v@@@R~
	    displayRemoteDevice();                                           //~1Af1I~//~v@@@R~
        if (Dump.Y) Dump.println("BTCDialog.renewal return");//~1Af1I~//~v@@@R~
    }                                                              //~1Af1I~
    //******************************************                   //~1Af3I~
    private void displayDiscoverableStatus()                       //~1Af3I~
    {                                                              //~1Af3I~
        String dev;                                                //~1Af3I~
        TextView v;                                                //~1Af3I~
    //************************                                     //~1Af3I~
        v=tvDiscoverable;  //~1Af3I~
        if (AG.aBTI.mBTC==null)                                 //~1Af6I~//~v@@@R~
        {                                                          //~1Af6I~
        	dev=Utils.getStr(R.string.noBTadapter);       //~1Af6I~//~v@@@R~
        }                                                          //~1Af6I~
        else                                                       //~1Af6I~
        if (BTI.BTisDiscoverable())                                //~1Af3I~//~v@@@R~
        {                                                          //~1Af3I~
        	dev=Utils.getStr(R.string.StatusDiscoverable);//~1Af3R~//~v@@@R~
            btnDiscoverable.setEnabled(false);                  //~1Af3R~
        }                                                          //~1Af3I~
        else                                                       //~1Af3I~
        {                                                          //~1Af3I~
        	dev=Utils.getStr(R.string.StatusNotDiscoverable);//~1Af3R~//~v@@@R~
            btnDiscoverable.setEnabled(true);                   //~1Af3R~
        }                                                          //~1Af3I~
        v.setText(dev);                                            //~1Af3I~
    }                                                              //~1Af3I~
    //******************************************                   //~v@@@I~
    private void displayDiscoverableStatus(boolean Pdiscoverable)  //~v@@@I~//~9A23R~
    {                                                              //~v@@@I~
        String dev;                                                //~v@@@I~
        TextView v;                                                //~v@@@I~
    //************************                                     //~v@@@I~
        v=tvDiscoverable;                               //~v@@@I~
        if (Pdiscoverable)                                         //~v@@@I~
        {                                                          //~v@@@I~
        	dev=Utils.getStr(R.string.StatusDiscoverable);         //~v@@@I~
            btnDiscoverable.setEnabled(false);                     //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	dev=Utils.getStr(R.string.StatusNotDiscoverable);      //~v@@@I~
            btnDiscoverable.setEnabled(true);                      //~v@@@I~
        }                                                          //~v@@@I~
        v.setText(dev);                                            //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    //* Discoverable status changed                                //~v@@@I~
    //*   BTDiscover:onReceive-->BTMulti:nowDiscoverable-->        //~v@@@I~
    //******************************************                   //~v@@@I~
    public static void nowDiscoverable(boolean Pdiscoverable)      //~v@@@I~//~9A23R~
    {                                                              //~v@@@I~
    //************************                                     //~v@@@I~
        if (isShowing())                                           //~v@@@R~
        {                                                          //~v@@@I~
	        BTCDialog dlg=AG.aBTCDialog;                           //~v@@@I~
			dlg.displayDiscoverableStatus(Pdiscoverable);         //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    //* Discoverable status changed                                //~v@@@I~
    //*   BTDiscover:onReceive-->BTMulti:nowReceive                //~v@@@R~
    //******************************************                   //~v@@@I~
    public static void onReceive(int Paction,String Pname,String Paddr,int Pstatus)//~v@@@R~
    {                                                              //~v@@@I~
    //************************                                     //~v@@@I~
	    if (!isShowing())                                          //~v@@@I~
        	return;                                                //~v@@@I~
        BTCDialog dlg=AG.aBTCDialog;                               //~v@@@M~
        if (Dump.Y) Dump.println("BTCDialog.onReceive action="+Paction+",name="+Pname+",addr="+Paddr+",status="+Pstatus);//~v@@@I~//~9724R~
        switch(Paction)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case BTDiscover.ACTION_DISCOVERY_FINISHED://remote device connection status:connecting,connected,disconnecting,disconnected//~v@@@I~
            dlg.discoveryFinished();                          //~1AebR~//~v@@@R~
        	break;                                                 //~v@@@I~
        case BTDiscover.ACTION_CONNECTION_STATE_CHANGED://remote device connection status:connecting,connected,disconnecting,disconnected//~v@@@R~
        	break;                                                 //~v@@@I~
        case BTDiscover.ACTION_BOND_STATE_CHANGED:   //remote device bond status changed,status:bond status//~v@@@R~
        	if (Pstatus==BluetoothDevice.BOND_BONDED               //~v@@@I~
        	||  Pstatus==BluetoothDevice.BOND_NONE)      //not bonded//~v@@@I~
            {                                                      //~9210I~
	        	if (Pstatus==BluetoothDevice.BOND_NONE)            //~9210I~
                	dlg.removeUnpaired(Pname,Paddr);               //~9210I~
                dlg.renewal();                                         //~v@@@I~
            }                                                      //~9210I~
        	break;                                                 //~v@@@I~
        case BTDiscover.ACTION_ACL_CONNECTED:                       //~v@@@I~
        	break;                                                 //~v@@@I~
        case BTDiscover.ACTION_ACL_DISCONNECTED:                    //~v@@@I~
        	dlg.onDisconnected(Pname);                                 //~v@@@I~
        	break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //**********************************************************   //~9731I~
    public static void onReceiveDisconnected(String Pname)         //~9731I~
    {                                                              //~9731I~
        if (Dump.Y) Dump.println("BTCDialog.onReceiveDisconnected name="+Pname);//~9731I~
	    if (!isShowing())                                          //~9731I~
        {                                                          //~0117I~
			Alert.showMessage(null/*title*/,Utils.getStr(R.string.InfoDisconnected,Pname));//~0117I~
        	return;                                                //~9731I~
        }                                                          //~0117I~
        BTCDialog dlg=AG.aBTCDialog;                               //~9731I~
        dlg.onDisconnected(Pname);                                 //~9731I~
    }                                                              //~9731I~
    //******************************************                   //~v@@@I~//~9731M~
    protected static boolean isShowing()                             //~v@@@R~//~9A21R~//~9A23R~
    {                                                              //~v@@@I~
        BTCDialog dlg=AG.aBTCDialog;                               //~v@@@I~
        boolean rc=Utils.isShowingDialogFragment(dlg);             //~9709I~
        if (Dump.Y) Dump.println("BTCDialog.isShowing rc="+rc);    //~9709I~
        return rc;                                                 //~9709R~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    //*from BTMulti                                                //~v@@@I~
    //******************************************                   //~v@@@I~
    public static void onAcceptFailed(String Psecure)              //~v@@@R~
    {                                                              //~v@@@I~
    //************************                                     //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog.onAcceptFailed");       //~v@@@I~
	    if (!isShowing())                                          //~v@@@I~
        	return;                                                //~v@@@I~
        BTCDialog dlg=AG.aBTCDialog;                               //~v@@@I~
	    dlg.onAcceptFailed();                                      //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@M~
    private  void onAcceptFailed()                                 //~v@@@M~
    {                                                              //~v@@@M~
    //************************                                     //~v@@@M~
        if (Dump.Y) Dump.println("BTCDialog.onAcceptFailed");      //~v@@@M~
        connectionStatus=CS_ACCEPT_FAILED;                         //~v@@@I~
        updateButtonStatus();                                      //~v@@@I~
    }                                                              //~v@@@M~
    //******************************************                   //~v@@@I~
    //*BTService.connected-->MESSAGE_DEVICE_NAME-->BTControl.handleMessage-->BTI.connected-->BTMulti.onConnected//~v@@@I~
    //******************************************                   //~v@@@I~
    public static void onConnected(String Pdevicename,String Paddr,boolean Pswclient)//~v@@@I~//~9A23R~
    {                                                              //~v@@@I~
    //************************                                     //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog.onConnected name="+Pdevicename+",addr="+Paddr+",swclient="+Pswclient);//~v@@@I~
        if (isReconnecting())                                      //~9A23R~
        {                                                          //~9A23I~
	        BTRDialog.onConnected(Pdevicename,Paddr,Pswclient);    //~9A23R~
            return;                                                //~9A23I~
        }                                                          //~9A23I~
	    if (!isShowing())                                          //~v@@@I~
        	return;                                                //~v@@@I~
        BTCDialog dlg=AG.aBTCDialog;                               //~v@@@I~
	    dlg.onConnected(0,Pdevicename,Paddr,Pswclient);            //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected void onConnected(int Popt,String Pdevicename,String Paddr,boolean Pswclient)//~v@@@I~//~9A23R~
    {                                                              //~v@@@I~
    //************************                                     //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog.onConnected name="+Pdevicename+",addr="+Paddr+",swclient="+Pswclient+",connectionStatus="+connectionStatus);//~v@@@I~//~9B07R~
	    ProgDlg.dismissCurrent();                                  //~v@@@I~
        if (connectionStatus==CS_ACCEPTING)                       //~v@@@I~
        	connectionType=ROLE_SERVER;                               //~v@@@I~//~@002R~
        else                                                       //~v@@@I~
        if (connectionStatus==CS_CONNECTING)                       //~v@@@I~
        {                                                          //~v@@@I~
        	connectionType=ROLE_CLIENT;                               //~v@@@I~//~@002R~
        	connectionStatus=CS_CONNECTED;                         //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
			UView.showToast("BTCDialog.onConnected connectionStatus invalid");	////~v@@@I~
            return;                                                //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog.onConnected connectionType="+connectionType+",connectionStatus="+connectionStatus);//~va13I~
        if (Pswclient!=(connectionType==ROLE_CLIENT))                //~v@@@I~//~@002R~
        {                                                          //~v@@@I~
			UView.showToast("BTCDialog.onConnected Role(Server or Client) Unmatch");//~v@@@R~
            return;                                                //~v@@@I~
        }                                                          //~v@@@I~
        if (Pswclient)                                             //~va13I~
	    	saveDeviceSelction(PKEY_BT_DEVICE_SELECTION,Pdevicename);//~va13R~
		addMember(Pdevicename,Paddr);                              //~v@@@R~
        updateDialog();                                             //~v@@@I~
//    	updateButtonStatus();                                      //~v001R~
        if ((TestOption.option & TO_CLOSEONCONNECT)!=0)            //~9305I~
        	dismiss();                                             //~9305I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    //*from BTMulti                                                //~v@@@I~
    //******************************************                   //~v@@@I~
    public static void onConnectionFailed(int Pflag,String Pdevicename)//~v@@@R~
    {                                                              //~v@@@I~
    //************************                                     //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog.onConnectionFailed name="+Pdevicename);//~v@@@I~
	    if (!isShowing())                                          //~v@@@I~
        	return;                                                //~v@@@I~
        BTCDialog dlg=AG.aBTCDialog;                               //~v@@@I~
        dlg.onConnectionFailed(Pdevicename);                       //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private  void onConnectionFailed(String Pdevicename)           //~v@@@I~
    {                                                              //~v@@@I~
    //************************                                     //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog.onConnectionFailed name="+Pdevicename);//~v@@@I~
        connectionStatus=CS_CONNECT_FAILED;                     //~v@@@I~
        updateButtonStatus();                                      //~v@@@I~
	    ProgDlg.dismissCurrent();                                  //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void cancelConnect()                                   //~v@@@I~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog.cancelConnect");       //~v@@@I~
		stopConnect();                                             //~v@@@I~
        connectionStatus=CS_CONNECT_FAILED;                        //~v@@@I~
        updateButtonStatus();                                      //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~9A24R~
    public static void renewal(int Pcase,String Pdevicename,String Pyourname)//~9A24R~
    {                                                              //~9A24R~
        if (Dump.Y) Dump.println("BTCDialog.renewal case="+Pcase+",device="+Pdevicename+",yn="+Pyourname);//~9A24R~
        renewal(Pcase);                                            //~9A24R~
        if (isReconnecting())                                      //~9A24R~
        {                                                          //~9A24R~
            BTRDialog dlg=(BTRDialog)getBTDialog();                //~9A24R~
            if (Dump.Y) Dump.println("BTCDialog.renewal reconnecting swServer="+dlg.swServer);//~9A24R~
            if (dlg.swServer)                                      //~9A24R~
            {                                                      //~9A25I~
    			int idx=AG.aAccounts.searchAccountByDeviceName(Pdevicename);    //~9A25I~
//              UserAction.showInfoAll(0,idx,Utils.getStr(R.string.Info_Reconnected,Pyourname));//~9A24R~//~9A25R~//~0304R~
                UserAction.showInfoAll(0,idx,R.string.Info_Reconnected,Pyourname);//~0304I~
            }                                                      //~9A25I~
            else                                                   //~9A24R~//~9A25R~
                GMsg.drawMsgbar(Utils.getStr(R.string.Info_Reconnected,Pyourname));//~9A24R~//~9A25R~
        }                                                          //~9A24R~
    }                                                              //~9A24R~
    //******************************************                   //~v@@@I~
    public static void renewal(int Pcase)                          //~v@@@R~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog.renewal case="+Pcase);//~v@@@R~//~9906R~
	    if (!isShowing())                                          //~v@@@I~
        	return;                                                //~v@@@I~
        BTCDialog dlg=AG.aBTCDialog;                               //~v@@@I~
        switch(Pcase)                                              //~v@@@I~
        {                                                          //~v@@@I~
        case RENEWAL_MEMBER:                                       //~v@@@I~
//          dlg.displayGroup();                                    //~v001R~
//          dlg.displayRemoteDevice();   //devicelist contains yourname if connected//~v001R~
//  	    dlg.updateButtonStatusGame();                          //~v001R~
    	    dlg.updateDialog();                                    //~v001R~
            break;                                                 //~v@@@I~
	    case RENEWAL_DEVICELIST:                                   //~v@@@I~
		    dlg.displayRemoteDevice(); //paired device list        //~v@@@I~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private String getYourName(boolean Pswtoast)                  //~v@@@R~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog.getYourName");         //~9722I~
    	return getYourName(etYourName,Pswtoast);                   //~9722R~
    }                                                              //~v@@@I~
    //******************************************                   //~9722I~
    private static String getYourName(UEditText PetYourName,boolean PswToast)//~9722I~
	{                                                              //~9722I~
    	String yourname=PetYourName.getText();                     //~9722I~
        if (Dump.Y) Dump.println("BTCDialog.getYourName with UeditText yourname="+yourname);//~9722I~
        if (yourname.equals(""))                                   //~9722I~
        {                                                          //~9722I~
            if (PswToast)                                          //~9722I~
				UView.showToast(R.string.ErrSpecifyYourname);      //~9722I~
            return null;                                           //~9722I~
        }                                                          //~9722I~
        if (Dump.Y) Dump.println("BTCDialog.getYourName="+yourname);//~9722I~
        if (!yourname.equals(AG.YourName))                         //~9722I~
        {                                                          //~9722I~
        	AG.aBTCDialog.members.updateYourName(AG.YourName,yourname);           //~9905I~
        	AG.YourName=yourname;                                  //~9722I~
            Utils.putPreference(PREFKEY_YOURNAME,yourname);        //~9722I~
		    AG.aBTCDialog.displayGroup();                                         //~9905I~
            AG.aBTCDialog.swChangedYourName=true;                                //~0116I~
        }                                                          //~9722I~
        return yourname;                                           //~9722I~
    }                                                              //~9722I~
    //******************************************                   //~9722I~
    private void showYourName()                                    //~9722I~
	{                                                              //~9722I~
        if (Dump.Y) Dump.println("BTCDialog.showYourName");        //~9722I~
        memberCtr=members.getMemberCtr();                          //~9928I~
	    showYourName(etYourName,memberCtr);                        //~9722R~
    }                                                              //~9722I~
//    //******************************************                   //~v@@@I~//~9724R~
//    public static void setYourName(UEditText PetYourName)                                    //~v@@@I~//~9722R~//~9723R~//~9724R~
//    {                                                              //~v@@@I~//~9724R~
//        String yourname=Utils.getPreference(PREFKEY_YOURNAME,AG.YourName);//~v@@@I~//~9724R~
//        PetYourName.setText(yourname);                             //~9722I~//~9724R~
//        AG.YourName=yourname;                                      //~9723I~//~9724R~
//        if (Dump.Y) Dump.println("BTCDialog.setYourName yourname="+yourname);//~v@@@I~//~9722R~//~9723I~//~9724R~
//    }                                                              //~9723I~//~9724R~
    //******************************************                   //~9723I~
    //*callback from GroupList by updateDialog->displayGroup       //~9B07I~
    //******************************************                   //~9B07I~
    public static void showYourName(UEditText PetYourName,int PmemberCtr)//~9723I~
	{                                                              //~9723I~
        if (Dump.Y) Dump.println("BTCDialog.showYourName memberCtr="+PmemberCtr+",AG.YourName="+AG.YourName);//~9723I~//~9724R~//~0404R~
//      String yourname=Utils.getPreference(PREFKEY_YOURNAME,AG.YourName);//~9723I~
//  	PetYourName.setText(yourname);                             //~9723I~
//  	setYourName(PetYourName);                                  //~9723I~//~9724R~
//  	PetYourName.setText(AG.YourName);                          //~9724I~//~9905R~
      	PetYourName.setText(AG.YourName,true/*swLostFocus*/);      //~9905I~//~va1bR~
//    	PetYourName.setText(AG.YourName,false/*No focusListener*/);//~va1bR~
//      if (PmemberCtr==BTMulti.maxClient)                          //~v@@@R~//~9722R~//~9928R~
//      if (PmemberCtr!=0) //disable when connected to anyone      //~9928R~//~9A23R~
        if (PmemberCtr!=0 || isReconnecting()) //disable when connected to anyone//~9A23I~
        {                                                          //~v@@@I~
//      	int colorEditableDisabled=AG.resource.getColor(COLOR_EDITABLE_DISABLED);//~va40R~
        	int colorEditableDisabled=AG.getColor(COLOR_EDITABLE_DISABLED);//~va40I~
	        PetYourName.editText.setBackgroundColor(colorEditableDisabled);//~v@@@R~//~9722R~
	        PetYourName.editText.setEnabled(false);                          //~v@@@I~//~9722R~
	        PetYourName.editText.setTextColor(Color.BLACK);         //~v@@@I~//~9722R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//      	int colorEditable=AG.resource.getColor(COLOR_EDITABLE);        //~v@@@I~//~va40R~
        	int colorEditable=AG.getColor(COLOR_EDITABLE);         //~va40I~
	        PetYourName.editText.setBackgroundColor(colorEditable);  //~v@@@R~//~9722R~
	        PetYourName.editText.setEnabled(true);                           //~v@@@I~//~9722R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected void updateButtonStatus()                                   //~v@@@I~//~9A23R~
	{                                                              //~v@@@I~
        connectedCtr=members.getConnectedCtr();                    //~@002R~
        if (Dump.Y) Dump.println("BTCDialog.updateButtonStatus connectionStatus="+connectionStatus+",connectedCtr="+connectedCtr+",connectionType="+connectionType);//~v@@@I~//~@002R~
        if (connectedCtr==0                                        //~@002I~//~9731R~
        &&  connectionStatus!=CS_ACCEPTING                         //~9731I~
        &&  connectionStatus!=CS_CONNECTING                        //~9731I~
        )                                                          //~9731I~
        {                                                          //~@002I~
            setButtonStatusDefault(connectedCtr);                              //~@002I~//~9808R~
        	connectionType=ROLE_UNDEFINED;                         //~0123M~
		    displayLocalDevice();                                  //~0123M~
        }                                                          //~@002I~
        else                                                       //~@002I~
        {                                                          //~@002I~
            switch (connectionStatus)                                  //~v@@@I~//~@002R~
            {                                                          //~v@@@I~//~@002R~
            case CS_ACCEPTING:     //1                                 //~v@@@R~//~@002R~
                btnAccept.setEnabled(false);                           //~v@@@I~//~@002R~
                btnStopAccept.setEnabled(true);                        //~v@@@I~//~@002R~
                btnConnect.setEnabled(false);                          //~v@@@I~//~@002R~
                if (connectedCtr!=0)                                            //~v@@@R~//~@002R~
                    btnDisconnect.setEnabled(true);                    //~v@@@I~//~@002R~
                else                                                   //~v@@@I~//~@002R~
                    btnDisconnect.setEnabled(false);                   //~v@@@R~//~@002R~
                break;                                                 //~v@@@I~//~@002R~
            case CS_ACCEPT_FAILED:                                     //~v@@@I~//~@002R~
                btnAccept.setEnabled(true);                            //~v@@@I~//~@002R~
                btnStopAccept.setEnabled(false);                       //~v@@@I~//~@002R~
                if (connectedCtr==0)                                   //~v@@@R~//~@002R~
                    btnConnect.setEnabled(true);                       //~v@@@I~//~@002R~
                else                                                   //~v@@@I~//~@002R~
                    btnConnect.setEnabled(false);                      //~v@@@I~//~@002R~
                btnDisconnect.setEnabled(false);                       //~v@@@I~//~@002R~
                break;                                                 //~v@@@I~//~@002R~
            case CS_CONNECTING:                                        //~v@@@I~//~@002R~
                btnAccept.setEnabled(false);                           //~v@@@I~//~@002R~
                btnStopAccept.setEnabled(false);                       //~v@@@I~//~@002R~
                btnConnect.setEnabled(false);                          //~v@@@I~//~@002R~
                btnDisconnect.setEnabled(true);                        //~v@@@I~//~@002R~
                break;                                                 //~v@@@I~//~@002R~
            case CS_CONNECTED:  //client action                        //~v@@@R~//~@002R~
                btnAccept.setEnabled(false);                           //~v@@@R~//~@002R~
                btnStopAccept.setEnabled(false);                       //~v@@@R~//~@002R~
                btnConnect.setEnabled(false);                          //~v@@@R~//~@002R~
                btnDisconnect.setEnabled(true);                        //~v@@@R~//~@002R~
                break;                                                 //~v@@@I~//~@002R~
            case CS_CONNECT_FAILED://client action                     //~v@@@I~//~@002R~
                btnAccept.setEnabled(true);                            //~v@@@I~//~@002R~
                btnStopAccept.setEnabled(false);                       //~v@@@I~//~@002R~
                btnConnect.setEnabled(true);                           //~v@@@I~//~@002R~
              if (connectedCtr==0)                                 //+va69I~
                btnDisconnect.setEnabled(false);                       //~v@@@I~//~@002R~
                break;                                                 //~v@@@I~//~@002R~
            case CS_DISCONNECTED://server & client action              //~v@@@I~//~@002R~
                switch(connectionType)                                 //~v@@@I~//~@002R~
                {                                                      //~v@@@I~//~@002R~
                case ROLE_SERVER:        //=1                                //~v@@@I~//~@002R~//~9B07R~
                	if (AG.RemoteStatusAccept==RS_IPLISTENING      //~9B07I~
                    ||   AG.RemoteStatusAccept==RS_BTLISTENING_SECURE//~9B07I~
                    ||   AG.RemoteStatusAccept==RS_BTLISTENING_INSECURE)//~9B07I~
                    {                                              //~9B07I~
            			connectionStatus=CS_ACCEPTING;	//reset disconnected//~9B07I~
                    	btnAccept.setEnabled(false);               //~9B07R~
                    	btnStopAccept.setEnabled(true);            //~9B07R~
                    }                                              //~9B07I~
                    else                                           //~9B07I~
                    {                                              //~9B07I~
                    btnAccept.setEnabled(true);                        //~v@@@I~//~@002R~
                    btnStopAccept.setEnabled(false);                   //~v@@@I~//~@002R~
                    }                                              //~9B07I~
                    if (connectedCtr==0)                                        //~v@@@R~//~@002R~
                    {                                                  //~v@@@I~//~@002R~
                        btnConnect.setEnabled(true);                   //~v@@@I~//~@002R~
                        btnDisconnect.setEnabled(false);               //~v@@@I~//~@002R~
                    }                                                  //~v@@@I~//~@002R~
                    else                                               //~v@@@I~//~@002R~
                    {                                                  //~v@@@I~//~@002R~
                        btnConnect.setEnabled(false);                  //~v@@@I~//~@002R~
                        btnDisconnect.setEnabled(true);                //~v@@@I~//~@002R~
                    }                                                  //~v@@@I~//~@002R~
                    break;                                             //~v@@@I~//~@002R~
                case ROLE_CLIENT:         //=2                               //~v@@@I~//~@002R~//~9B07R~
                    btnAccept.setEnabled(false);                       //~v@@@I~//~@002R~
                    btnStopAccept.setEnabled(false);                   //~v@@@I~//~@002R~
                    btnConnect.setEnabled(true);                       //~v@@@I~//~@002R~
                    btnDisconnect.setEnabled(false);                   //~v@@@I~//~@002R~
                    break;                                             //~v@@@I~//~@002R~
                default:    //unkown(by connectedctr==0)           //~@002R~
                    setButtonStatusDefault(connectedCtr);                      //~@002R~//~9808R~
                }                                                      //~v@@@I~//~@002R~
                break;                                                 //~v@@@I~//~@002R~
            default:                                                   //~v@@@I~//~@002R~
                setButtonStatusDefault(connectedCtr);                          //~@002R~//~9808R~
            }                                                          //~v@@@I~//~@002R~
        }                                                          //~@002I~
//        updateButtonStatusGame();                               //~v@@@R~//~@002R~
    }                                                              //~v@@@I~
//    private void updateButtonStatusGame()         //~v@@@R~      //~@002R~
//    {                                                              //~v@@@I~//~@002R~
//        btnGame.setEnabled((connectionType==ROLE_SERVER && (memberCtr==BTMulti.maxClient)));//~v@@@R~//~@002R~
//        if (Dump.Y) Dump.println("BTCDialog.updateButtonStatusGame connectedCtr="+connectedCtr);//~v@@@R~//~@002R~
//    }                                                              //~v@@@I~//~@002R~
    //************************************************************ //~@002I~
    protected void setButtonStatusDefault(int PconnectedCtr)                          //~@002I~//~9808R~//~9A30R~
    {                                                              //~@002I~
        if (Dump.Y) Dump.println("BTCDialog.setButtonStatusDefault PconnectedCtr="+PconnectedCtr);//~@002I~//~0123R~
        btnAccept.setEnabled(true);                                //~@002I~
        btnStopAccept.setEnabled(false);                           //~@002I~
        btnConnect.setEnabled(true);                               //~@002I~
        btnDisconnect.setEnabled(PconnectedCtr!=0);                           //~@002I~//~9808R~
    }                                                              //~@002I~
    //************************************************************ //~v@@@I~
    @Override //UEditTextI                                         //~v@@@I~
    public void onTextChanged(UEditText Pedittext,String Ptext)    //~v@@@R~
    {                                                              //~v@@@I~
        getYourName(false);	//update AG.YourName                       //~v@@@I~
        if (Dump.Y) Dump.println("BTCDialog.onTextChanged text="+Pedittext+",swChangedYourName="+swChangedYourName);//~v@@@I~//~0116I~
      if (swChangedYourName)                                       //~0116I~
      {                                                            //~0116I~
	      swChangedYourName=false;                                 //~0116I~
//      AG.aBTMulti.sendMsg(BTMulti.MSGID_NEWNAME,Ptext);                     //~v@@@R~//~@002R~
//*disabled when any connection exist, so new name is sent at connected by @@name//~va1bI~
//      AG.aBTMulti.sendMsg(MSGID_NEWNAME,Ptext);                  //~@002I~//~va1bR~
      }                                                            //~0116I~
    }                                                              //~v@@@I~
    //************************************************************ //~va13I~
    public static String getPreviousSelction(String Pkey)          //~va13I~
    {                                                              //~va13I~
		String svr=Utils.getPreference(Pkey,"");   //~@@01I~       //~va13I~
        if (Dump.Y) Dump.println("BTCDialog.getPreviousSelection key="+Pkey+",server="+svr);//~va13I~
        return svr;
    }                                                              //~va13I~
    //************************************************************ //~va13I~
    public static void saveDeviceSelction(String Pkey,String PdeviceName)//~va13I~
    {                                                              //~va13I~
		Utils.putPreference(Pkey,PdeviceName);          //~va13I~
    }                                                              //~va13I~
}//class                                                           //~v@@@R~
