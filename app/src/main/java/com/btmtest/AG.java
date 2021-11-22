//*CID://+vaf0R~:                             update#=  488;       //~vaf0R~
//******************************************************************************************************************//~v101R~
//2021/10/21 vaf1 Dump, initially terminal and follow test option to investigate vaf0//~vaf1I~
//2021/10/21 vaf0 Play console crash report "IllegalStateException" at FragmentManagerImple.1536(checkStateLoss)//~vaf0I~
//2021/09/28 vaeg enlarge nameplate for long device                //~vaegI~
//2021/09/27 vaef gesture navigation mode from android11           //~vaefI~
//2021/09/26 vaee gesture navigation mode from android10           //~vaeeI~
//2021/09/24 vaed more adjust for small device(dip=width/dip2px<=320)//~vaedI~
//2021/09/19 vae9 1ak2(access external audio file) for BTMJ        //~vae9I~
//2021/09/19 vae8 keep sharedPreference to external storage with PrefSetting item.//~vae8I~
//2021/09/16 vae5 (Bug)Property of resumed game did not use sg.rulefile at interrupted.//~vae5I~
//2021/08/25 vae0 Scped for BTMJ5                                  //~vae0I~
//1ak2 2021/09/04 access external audio file                       //~1ak2I~
//1ak0 2021/08/26 androd11:externalStorage:ScopedStorage           //~1ak0I~
//1aj0 2021/08/14 androd11(api30) deprecated at api30;getDefaultDisplay, display.getSize(), display/getMetrics()//~vaj0I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac4I~
//2021/08/11 vac4 for safety initialize scaleSmallDevice(currently swSmallDevice is checked)//~vaa4I~
//2021/06/27 vaa2 Notify mode of Match                             //~vaa2I~
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2021/01/07 va60 CalcShanten                                      //~va60I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//2020/11/03 va27 Tenpai chk at Reach                              //~va27I~
//@@01 20181105 for BTMJ3                                          //~@@01I~
//******************************************************************************************************************//~v101I~
//*Globals *****                                             //~1107I~//~1Ad7R~
//********************                                             //~1107I~
package com.btmtest;                                               //~1Ad8I~//~1Ad7I~

import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.Stack;

import android.graphics.Bitmap;
import android.os.Build;                                           //~vab0R~//~v101I~
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;                     //~va40I~
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.os.HandlerThread;                                   //~@@01I~

import com.btmtest.BT.BTI;                                         //~@@01R~
import com.btmtest.BT.BTMulti;                                     //~v@@@I~//~@@01R~
import com.btmtest.dialog.UFDlg;
import com.btmtest.game.UA.UARonValue;
import com.btmtest.game.UA.UAReachChk;                             //~va27I~
import com.btmtest.game.UA.UARon;                                  //~@@01I~
import com.btmtest.wifi.IPMulti;                                   //~@@01I~
import com.btmtest.dialog.BTCDialog;//~v@@@R~                      //~@@01I~
import com.btmtest.dialog.BTRDialog;                               //~@@01I~
import com.btmtest.dialog.CompleteDlg;                             //~@@01I~
import com.btmtest.dialog.ScoreDlg;                                //~@@01I~
import com.btmtest.dialog.AccountsDlg;                             //~@@01I~
import com.btmtest.dialog.SuspendDlg;                              //~@@01I~
import com.btmtest.dialog.ResumeDlg;                               //~@@01I~
import com.btmtest.game.Rule;                                      //~v@@@I~//~@@01R~
import com.btmtest.game.GC;                                        //~@@01I~
import com.btmtest.game.Tiles;
import com.btmtest.game.gv.DiceBox;
import com.btmtest.game.gv.GCanvas;
import com.btmtest.game.gv.GameView;                               //~@@01I~
import com.btmtest.game.gv.Hands;
import com.btmtest.game.gv.HandsTouch;                             //~@@01I~
import com.btmtest.game.gv.River;
import com.btmtest.game.gv.Stock;
import com.btmtest.game.Players;                                   //~@@01R~
import com.btmtest.game.Complete;                                  //~@@01I~
import com.btmtest.game.gv.PointStick;                                  //~@@01R~
import com.btmtest.game.gv.Pieces;                                 //~@@01I~
import com.btmtest.game.Accounts;                                  //~@@01I~
import com.btmtest.game.gv.Starter;                                   //~@@01I~
import com.btmtest.game.gv.GMsg;                                   //~@@01R~
import com.btmtest.game.gv.NamePlate;                              //~@@01I~
import com.btmtest.game.Status;                                    //~@@01I~
import com.btmtest.game.gv.Graphics;                               //~@@01I~
import com.btmtest.game.gv.Earth;                                  //~@@01I~
import com.btmtest.game.gv.ICanvas;                                //~@@01I~
import com.btmtest.game.gv.GameViewHandler;                        //~@@01I~
import com.btmtest.game.gv.MJTable;                                //~@@01I~
import com.btmtest.game.ACAction;                                  //~@@01I~
import com.btmtest.game.ACATouch;                                  //~@@01I~
import com.btmtest.game.UserAction;                                //~@@01I~
import com.btmtest.game.UADelayed2;                                //~@@01I~
import com.btmtest.game.UA.UAEndGame;                                   //~@@01I~
import com.btmtest.game.LastGame;                                  //~@@01I~
import com.btmtest.game.History;                                   //~@@01I~
import com.btmtest.game.UA.UARestart;                              //~@@01R~
import com.btmtest.dialog.FinalGameDlg;                            //~@@01R~
import com.btmtest.dialog.ActionAlert;                             //~@@01I~
import com.btmtest.utils.Dump;
import com.btmtest.utils.ProgDlg;
import com.btmtest.utils.Prop;
import com.btmtest.utils.UFile;
import com.btmtest.utils.Utils;
import com.btmtest.utils.UScoped;                                  //~1ak0I~
import com.btmtest.utils.UMediaStore;                              //~1ak2I~
import com.btmtest.gui.CommonListener;                             //~@@01I~
import com.btmtest.dialog.RuleSetting;                             //~@@01R~
import com.btmtest.dialog.RuleSettingEnum;                         //~@@01I~
import com.btmtest.dialog.PrefSettingEnum;                         //~@@01I~
import com.btmtest.dialog.PrefSetting;                             //~@@01I~
import com.btmtest.dialog.HistoryDlg;                              //~@@01I~
import com.btmtest.dialog.SuspendIOErrReqDlg;                      //~@@01I~
import com.btmtest.dialog.SuspendIOErrDlg;                         //~@@01I~
import com.btmtest.game.HistoryData;                               //~@@01I~
import com.btmtest.game.UAD2Touch;                                 //~@@01R~
import com.btmtest.wifi.WDA;                                       //~@@01I~
import com.btmtest.wifi.WDAR;                                      //~@@01I~
import com.btmtest.wifi.WDI;                                       //~@@01I~
import com.btmtest.wifi.IPConnectionWD;                            //~@@01R~
import com.btmtest.wifi.PartnerFrame;                              //~@@01R~
import com.btmtest.wifi.IPSubThread;                               //~@@01I~
import com.btmtest.wifi.CSI;                                       //~@@01I~
import com.btmtest.utils.sound.Sound;                                    //~@@01I~
import com.btmtest.game.RA.Shanten;                                //~va60I~
import com.btmtest.game.RA.RoundStat;                              //~va60I~
import com.btmtest.game.RA.RADiscard;                              //~va60I~
import com.btmtest.game.RA.RADSmart;                               //~va60I~
import com.btmtest.game.RA.RADSEval;                               //~va66I~
import com.btmtest.game.RA.RACall;                                 //~va60I~
import com.btmtest.game.RA.RARon;                                  //~va60I~
import com.btmtest.game.RA.RAReach;                                //~va60I~
                                                                   //~@@01I~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~@@01I~
import static com.btmtest.dialog.PrefSettingEnum.*;
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GConst.*;

//**************************                                       //~1120I~
public class AG                                                    //~1107R~
{                                                                  //~1109R~
    public static final String PROP_EXT_RULE=".rulefile";          //~v@@@R~
//  public static final String PROP_EXT_OPERATION=".operationfile";//~v@@@R~//~@@01R~
    public static final String PROP_EXT_PREFERENCE=".preferencefile";//~@@01R~
    public static final String PROP_VALIDATINGKEY_RULE="rule";          //pro file should contains this key//~v@@@R~
//  public static final String PROP_VALIDATINGKEY_OPERATION="operation";//~v@@@R~//~@@01R~
    public static final String PROP_VALIDATINGKEY_PREFERENCE="preference";//~@@01I~
    public static final String PROP_INIT_SYNCDATE="Unknown";       //~@@01I~
                                                                   //~v@@@I~
//    public static final int ACTIVITY_REQUEST_ENABLE_BT = 2;        //~v@@@I~//~@@01R~
//    public static final int ACTIVITY_REQUEST_NFCBEAM   = 3;       //~1A6aI~//~1Ad7R~
    public static final int ACTIVITY_REQUEST_PICKUP_AUDIO   = 10;  //~1Ak2I~//~1ak2I~
    public static final int ACTIVITY_REQUEST_SCOPED    = 100;      //~1Ak0I~//~1ak0I~
    public static final int ACTIVITY_REQUEST_SCOPED_OPEN_TREE = (ACTIVITY_REQUEST_SCOPED+1);//~1Ak0I~//~1ak0I~
    public static final int ACTIVITY_REQUEST_SCOPED_LAST=110;      //~1Ak0I~//~1ak0I~
//                                                                   //~1A6aI~//~1Ad7R~
//    public final static String PKEY_PIECE_TYPE="PieceType";        //~@@@@R~//~1Ad7R~
//    public final static String PKEY_BOARD_SIZE="BoardSize";       //~@@@@I~//~1Ad7R~
//    public static final String PKEY_OPTIONS="Options";             //~@@@@I~//~1Ad7R~
//    public static final String PKEY_YOURNAME="YourName";           //~@@@@I~//~1Ad7R~
//    public static final Color cursorColor=new Color(0x00,0xff,0x20);//~@@@2I~//~@@@@R~//~1Ad7R~
//    public static final Color selectedColor=new Color(0x00,0x20,0xff);//~@@@2R~//~@@@@R~//~1Ad7R~
//    public static final Color capturedColor=new Color(0xff,0x00,0x00);//~@@@@I~//~@@@2I~//~@@@@I~//~1Ad7R~
//    public static final Color lastPosColor=new Color(0xff,0x00,0x80);//~@@@@R~//~1Ad7R~
//    public static final Color lastFromColor=new Color(0x60,0x60,0x00);//~1A35I~//~1Ad7R~
//                                                                   //~@@@@I~//~1Ad7R~
////    public static final String DEBUGTRACE_CFGKEY  ="debugtrace"; //~@@@@R~//~1Ad7R~
//                                                                   //~@@@@I~//~1Ad7R~
////  public final static String SshogiV="\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d";//~@@@@I~//~1023R~//~1Ad7R~
//    public final static String SshogiVJ="\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d";//~1023I~//~1Ad7R~
//    public final static String SshogiVE="abcdefghi";               //~1023I~//~1Ad7R~
//    public       static String SshogiV;                            //~1023I~//~1Ad7R~
//    public final static String SshogiH="987654321";                //~@@@@I~//~1Ad7R~
//    public final static String SchessV="87654321";                 //~@@@@I~//~1Ad7R~
//    public final static String SchessH="abcdefgh";                 //~@@@@R~//~1Ad7R~
//    public static final String COMMON_BUTTON_NAME="Button"; //Butonnxx//~@@@@I~//~1Ad7R~
//    public static final int BOARDSIZE_SHOGI=9;                     //~@@@@R~//~1Ad7R~
//    public static final int BOARDSIZE_CHESS=8;                     //~@@@@I~//~1Ad7R~
//    public static final int EXTRA_MOVE=1;      //move per extratime//~@@@@I~//~1Ad7R~
//    public static       int propBoardSize;                         //~@@@@R~//~1Ad7R~
//    public static       Color chessBoardBlack=new Color(0x90,0x60,0x40);//~@@@@R~//~1Ad7R~
//    public static       Color chessBoardWhite=new Color(0xE6,0xD4,0xAE);//~@@@@I~//~1Ad7R~
//                                                                   //~@@@@I~//~1Ad7R~
    public int Options;                                     //~@@@@I~//~1Ad7R~//~@@01R~
//    public static final int OPTIONS_TIMER_IN_TITLE          =0x0001;//~@@@@R~//~1Ad7R~
//    public static final int OPTIONS_BIG_TIMER               =0x0002;//~@@@@R~//~1Ad7R~
//    public static final int OPTIONS_NOSOUND                 =0x0004;//~@@@@R~//~1Ad7R~
//    public static final int OPTIONS_BEEP_ONLY               =0x0008;//~@@@@R~//~1Ad7R~
//    public static final int OPTIONS_TIMER_WARNING           =0x0010;//~@@@@R~//~1Ad7R~
//    public static final int OPTIONS_SHOW_LAST               =0x0020;//~@@@@R~//~1Ad7R~
//    public static final int OPTIONS_COORDINATE              =0x0040;//~@@@@R~//~1Ad7R~
    public static final int OPTIONS_TRACE                   =0x0080;//~@@@@R~//~1Ad7R~
//    public static final int OPTIONS_GOFRAME_CLOSE_CONFIRM   =0x0100;//~@@@@I~//~1Ad7R~
//    public static final int OPTIONS_1TOUCH                  =0x2000;  //1touch on freeboard//~1A13I~//~1Ad7R~
//    public static final int OPTIONS_TRACE_CANVAS            =0x4000;//~1A6AI~//~1Ad7R~
//    public static final int OPTIONS_TRACE_UITHREAD          =0x8000;//~1A6AI~//~1Ad7R~
                                                                   //~@@01I~
    public Thread mainThread;                               //~1126I~//~1Ad7R~//~v@@@R~//~@@01M~
    public String dirSep;                                   //~v@@@I~//~@@01M~
    public Activity activity;                                 //~1109I~//~1111R~//~1Ad7R~//~v@@@R~//~@@01M~
    public Context context;                                 //~1111I~//~1Ad7R~//~@@01M~
//  public Context svContext;	//serfaceview context              //~@@01M~
    public LayoutInflater inflater;                          //~1113I~//~1Ad7R~//~v@@@R~//~@@01M~
    public FragmentManager fragmentManager;                 //~v@@@R~//~@@01M~
    public MainActivity aMainActivity;                             //~1107R~//~@@@@R~//~1Ad7R~//~@@01M~
    public Resources  resource;                              //~1109I~//~1Ad7R~//~@@01M~
//  public View mainView;                                          //~@@01R~
    public String YourName="";                                //~v@@@R~//~@@01R~
    public String language;                                 //~1531I~//~@@@@I~//~1Ad7R~//~v@@@R~//~@@01R~
//    public static String Glocale;                                  //~@@@@I~//~1Ad7R~
    public  boolean isLangJP;                                //~@@@@I~//~1Ad7R~//~v@@@R~//~@@01R~
    public  String helpFileSuffix;                           //~v@@@I~//~@@01R~
    public  boolean isDebuggable;                            //~v107I~//~1Ad7R~//~@@01R~
	public  int RemoteStatus;                                //~@@@@R~//~1Ad7R~//~v@@@R~//~@@01R~
    public  int RemoteStatusAccept;                          //~@@@@I~//~1Ad7R~//~v@@@R~//~@@01R~
    public static final int RS_IP=1;                               //~@@@@R~//~1Ad7R~//~v@@@R~
    public static final int RS_BT=2;                               //~@@@@I~//~1Ad7R~//~v@@@R~
    public static final int RS_IPLISTENING=RS_IP+4;                //~@@@@R~//~1Ad7R~//~v@@@R~
    public static final int RS_BTLISTENING_SECURE=RS_BT+4;         //~@@@@R~//~1Ad7R~//~v@@@R~
    public static final int RS_IPCONNECTED=RS_IP+8;                 //~@@@@R~//~1Ad7R~//~v@@@R~
    public static final int RS_BTCONNECTED=RS_BT+8;                //~@@@@R~//~1Ad7R~//~v@@@R~
    public static final int RS_BTLISTENING_INSECURE=RS_BT+16;      //~@@@@I~//~1Ad7R~//~v@@@R~
    public static final int DEFAULT_SERVER_PORT=6973;              //~@@@@I~//~1Ad7R~//~v@@@R~
//    public static String RemoteInetAddress;                        //~@@@@I~//~1Ad7R~
    public static String ServerInetAddress; //for wifi client      //~@@01I~
    public static String LocalInetAddress;  //for wifi client                       //~1A6sI~//~1Ad7R~//~@@01R~
//    public static String PartnerName;                              //~@@@@I~//~1Ad7R~
    public  String RemoteDeviceName;                         //~@@@@I~//~1Ad7R~//~v@@@R~//~@@01R~
    public  String LocalDeviceName;                          //~@@@@I~//~1Ad7R~//~v@@@R~//~@@01R~
    public  float dip2pix;                                   //~1428I~//~1Ad7R~//~v@@@R~//~@@01R~
    public  float sp2pix;                                    //~@@@@I~//~1Ad7R~//~v@@@R~//~@@01R~
    public  boolean   portrait;                              //~1428R~//~1Ad7R~//~v@@@R~//~@@01R~
    public  boolean   swMainView;                                  //~@@01I~
    public  String    appName;                               //~1428R~//~1Ad7R~//~@@01R~
    public  String    appNameE;	//by alphabet                      //~@@01I~
//    public static String    pkgName;                               //~1A6aI~//~1Ad7R~
//    public static String    appVersion;                            //~1506I~//~1Ad7R~
    public  int       scrWidth,scrHeight;                    //~1428R~//~1Ad7R~//~v@@@R~//~@@01R~
    public  int       scrWidthReal,scrHeightReal;                  //~@@01R~
    public  int       scrStatusBarHeight;	//API30, by insets     //~vaj0I~
    public  int       scrNavigationbarRightWidth;                  //~@@01I~
    public  int       scrNavigationbarBottomHeight;                //~vaeeI~
    public  int       scrNavigationbarBottomHeightA11;             //~vaefR~
    public  int       scrNavigationbarLeftWidthA11;                //~vaefI~
    public  int       scrNavigationbarRightWidthA11;               //~vaefI~
    public  boolean   swNavigationbarGestureMode;                  //~vaefR~
    public  int       scrPortraitWW;	//width of top panel(portrait)//~@@01I~
    public  boolean   swSmallDevice;      //portrait screen width<800pixel//~@@01R~
    public  boolean   swLongDevice;       //height>width*2         //~vaegI~
//  public  double    scaleSmallDevice;   //portrait screen width/800pixel//~@@01I~//~vac4R~
    public  double    scaleSmallDevice=1.0;   //portrait screen width/800pixel//~vaa4I~
    public  boolean   swSmallFont;        //portrait screen width<800pixel//~vac5M~
    public  boolean   swSmallDip;         //portrait screen dip(ww/dip2pix)<=320//~vaedI~
    public  double    scaleSmallFont=1.0;        //portrait screen width<800pixel//~vac5M~
    public  int       scrDencity;        //160:mdpi, 240:hdpi(*1.5), 320:xhdpi(*2), 480:xxhdpi(*3). 640:xxxhdpi(*4)//~vac5I~
    public  int       scrPortraitWidthDPI;       //portrait        //~vac5I~
//    private static View      currentLayout;//~1120I~               //~1428R~//~1Ad7R~
//    public static int       currentLayoutId;                       //~1428R~//~1Ad7R~
//    public static boolean currentIsDialog;                         //~1428R~//~1Ad7R~
//                                                                   //~1211I~//~1Ad7R~
//    public static int       listViewRowId=R.layout.textrowlist;       //~1211I~//~1219R~//~1Ad7R~
//    public static int       viewerRowId  =R.layout.textrowviewer;  //~1219R~//~1Ad7R~
//    public static int       listViewRowIdMultipleChoice=android.R.layout.simple_list_item_multiple_choice;//~1211I~//~1Ad7R~
//    public static final int TIME_LONGPRESS=1000;//milliSeconds     //~1412I~//~1Ad7R~
//    public static int currentTabLayoutId;                          //~1428R~//~1Ad7R~
    public  int titleBarTop;                                 //~1428R~//~1Ad7R~//~v@@@R~//~@@01R~
    public  int titleBarBottom;                              //~1428R~//~1Ad7R~//~v@@@R~//~@@01R~
//    public static final String PKEY_STARTUPCTR="startupctr";       //~v107I~//~1Ad7R~
//    public static int startupCtr;                                  //~v107I~//~1Ad7R~
//  public static int activeSessionType;                           //~1A8gI~//~1Ad7R~//~@@01R~
    public int activeSessionType;                                  //~@@01I~
    public static final int AST_NONE=0;                            //~@@01I~
    public static final int AST_IP=1;                              //~1A8gI~//~1Ad7R~//~@@01R~
    public static final int AST_WD=2;                              //~1A8gI~//~1Ad7R~//~@@01R~
    public static final int AST_BT=3;                              //~1A8gI~//~1Ad7R~//~@@01R~
//    public static String BlackSign,WhiteSign,Move1stSign,Move2ndSign;//~@@@@I~//~1Ad7R~
//    public static String BlackName,WhiteName;                      //~@@@@I~//~1Ad7R~
//    public static MsgThread msgThread;                             //~@@@@R~//~1Ad7R~
//    public static SayDialog sayDialog;                             //~@@@@I~//~1Ad7R~
//    public static boolean smallButton;                             //~@@@@R~//~1Ad7R~
//    public static int smallViewHeight;                         //~@@@@I~//~1Ad7R~
//    public static int smallImageHeight; //for captured List        //~@@@@I~//~1Ad7R~
//    public static int smallTextSize;                               //~@@@@I~//~1Ad7R~
//    public static Font smallFont;                                  //~@@@@I~//~1Ad7R~
//    public static boolean screenDencityMdpi;                       //~1A50I~//~1Ad7R~
//    public static boolean screenDencityMdpiSmallV;                 //~1A61I~//~1Ad7R~
//    public static boolean screenDencityMdpiSmallH;                 //~1A61I~//~1Ad7R~
//    public static boolean layoutMdpi;                              //~1A6cI~//~1Ad7R~
//    public static boolean tryHelpFileOpen;                         //~1A41R~//~1Ad2I~//~1Ad7R~
//    public static  boolean swNFCBT=true;   //support NFC Bluetooth handover//~1Ab7I~//~1Ad7R~
//    public static  boolean swSecureNFCBT;  //current active NFCBT session is secure//~1AbgI~//~1Ad7R~
//    public static  boolean isNFCBT;         //BT is by NFC         //~1AbgI~//~1Ad7R~
//                                                                   //~1120I~//~1Ad7R~
////****************                                                 //~1109I~//~1Ad7R~

////*                                                                //~v101I~//~1Ad7R~
//    public static int bottomSpaceHeight;                           //~v101I~//~1Ad7R~
//    public static final int SYSTEMBAR_HEIGHT=48;                   //~v101I~//~1Ad7R~
//    public static String PREFKEY_BOTTOMSPACE_HIGHT="BottomSpaceHeight";//~v101I~//~1Ad7R~
    public static final int LOLLIPOP=21; //android5.0              //~@@01I~
//    public static final int HONEYCOMB=11; //android3.0 (GINGERBREAD=9)//~vab0I~//~v101I~//~1Ad7R~
//    public static final int HONEYCOMB_MR1=12; //android3.1         //~1A4hI~//~1Ad6I~//~1Ad7R~
//    public static final int HONEYCOMB_MR2=13; //android3.2         //~1A6pI~//~1Ad7R~
//    public static final int ICE_CREAM_SANDWICH=14; //android4.0    //~vab0I~//~v101I~//~1Ad7R~
////************************************                                                 //~1402I~//~@@@@R~//~1Ad7R~
////*static to be creaded at create                                  //~@@@@I~//~1Ad7R~
////************************************                             //~@@@@I~//~1Ad7R~
    public int       status=0;                              //~1212I~//~@@@@M~//~1Ad7R~//~v@@@R~//~@@01R~
    public static final int STATUS_MAINFRAME_OPEN=1;               //~1212I~//~@@@@M~//~1Ad7R~//~v@@@R~
    public static final int STATUS_STOPFINISH=9;                   //~@@@@I~//~1Ad7R~//~v@@@R~
////    public static boolean   Utils_stopFinish;   //Utils                                 //~1309I~//~@@@@R~//~1Ad7R~
//    public static int Board_SpieceImageSize;                       //~@@@@I~//~1Ad7R~
//	public static String Home="";                                  //~1Ad7I~
//    public static Game aGame;                                      //~v@@@I~//~@@01R~
    public View parentSnackbar;                             //~v@@@R~//~@@01R~
    public static final String ruleFile="current"+PROP_EXT_RULE;	//".rule_file"//~v@@@R~
//  public static final String opeFile="current"+PROP_EXT_OPERATION;   //".operation_file"//~v@@@R~//~@@01R~
    public static final String prefFile="current"+PROP_EXT_PREFERENCE;   //".operation_file"//~@@01I~
    public static final String ruleFileDefault="default"+PROP_EXT_RULE;//~v@@@I~
//  public static final String opeFileDefault="default"+PROP_EXT_OPERATION;   //".operation_file"//~v@@@I~//~@@01R~
    public static final String prefFileDefault="default"+PROP_EXT_PREFERENCE;   //".operation_file"//~@@01I~
//    public static SurfaceHolder holder;                          //~@@01R~
                                                                   //~@@01I~
//volatile                                                         //~@@01I~
//    public static GC aGC;                                        //~@@01R~
//    public static Rule aRule;                                    //~@@01R~
//    public static Tiles aTiles;                                  //~@@01R~
//    public static GameView aGameView;                            //~@@01R~
//    public static DiceBox aDiceBox;                              //~@@01R~
//    public static River aRiver;                                  //~@@01R~
//    public static GCanvas aGCanvas;                              //~@@01R~
//    public static Stock aStock;                                  //~@@01R~
//    public static Hands aHands;                                  //~@@01R~
//    public static Players aPlayers;                              //~@@01R~
//    public static PointStick aPointStick;                        //~@@01R~
//    public static Accounts aAccounts;                            //~@@01R~
//    public static Complete aComplete;                            //~@@01R~
//    public static Starter aStarter;                              //~@@01R~
//    public static GMsg aGMsg;                                    //~@@01R~
//    public static Pieces aPieces;                                //~@@01R~
//    public static NamePlate aNamePlate;                          //~@@01R~
//    public static Status aStatus;                                //~@@01R~
//    public static Graphics aGraphics;                            //~@@01R~
//    public static Earth aEarth;                                  //~@@01R~
//    public static ICanvas aICanvas;                              //~@@01R~
                                                                   //~@@01I~
//    public static ProgDlg progDlg;                                       //~@@@@M~//~1Ad7R~//~v@@@R~//~@@01R~
//*volatile ****************************                           //~@@01I~
    public  GC aGC;                                                //~@@01I~
    public  Rule aRule;                                            //~@@01R~
    public  Tiles aTiles;                                          //~@@01I~
    public  GameView aGameView;                                    //~@@01I~
    public  DiceBox aDiceBox;                                      //~@@01I~
    public  River aRiver;                                          //~@@01I~
    public  GCanvas aGCanvas;                                      //~@@01I~
    public  Stock aStock;                                          //~@@01I~
    public  Hands aHands;                                          //~@@01I~
    public  HandsTouch aHandsTouch;                                //~@@01I~
    public  Players aPlayers;                                      //~@@01I~
    public  PointStick aPointStick;                                //~@@01I~
    public  Accounts aAccounts;                                    //~@@01I~
    public  Complete aComplete;                                    //~@@01I~
    public  Starter aStarter;                                      //~@@01I~
    public  GMsg aGMsg;                                            //~@@01I~
    public  Pieces aPieces;                                        //~@@01I~
    public  NamePlate aNamePlate;                                  //~@@01I~
    public  Status aStatus;                                        //~@@01I~
    public  Graphics aGraphics;                                    //~@@01I~
    public  Earth aEarth;                                          //~@@01I~
    public  ICanvas aICanvas;                                      //~@@01I~
    public  GameViewHandler aGameViewHandler;                      //~@@01R~
    public  HandlerThread aHandlerThread;                          //~@@01I~
    public  ACAction     aACAction;                                //~@@01I~
    public  ACATouch     aACATouch;                                //~@@01I~
    public  UserAction   aUserAction;                              //~@@01I~
//  public  UADelayed    aUADelayed;                               //~@@01R~
    public  UADelayed2   aUADelayed;                               //~@@01I~
    public UARonValue aUARonValue;                              //~@@01I~
    public UAReachChk aUAReachChk;                                 //~va27I~
    public UARon      aUARon;                                      //~@@01I~
    public  LastGame    aLastGame;                                 //~@@01I~
    public  History     aHistory;                                  //~@@01I~
    public  FinalGameDlg    aFinalGameDlg;                         //~@@01R~
//  public  Prop ruleProp,opeProp,prefProp;                  //~v@@@I~//~@@01R~
    public  Prop opeProp;                                          //~@@01I~
    public  Prop ruleProp,prefProp;                                //~@@01I~
    public  Prop savePropForResume;                                //~vae5I~
    public  CompleteDlg aCompleteDlg;                              //~@@01I~
//  public  CompNotenDlg aCompNotenDlg;                            //~@@01R~
    public  MJTable aMJTable;                                      //~@@01I~
    public  UAEndGame aUAEndGame;                                  //~@@01R~
    public  UARestart aUARestart;                                  //~@@01I~
    public  ScoreDlg aScoreDlg;                                    //~@@01I~
    public  AccountsDlg aAccountsDlg;                              //~@@01I~
    public  SuspendDlg aSuspendDlg;                                //~@@01I~
    public  ResumeDlg aResumeDlg;                                  //~@@01I~
    public  RuleSetting aRuleSetting;                              //~@@01I~
    public  PrefSetting aPrefSetting;                              //~@@01I~
    public  RuleSettingEnum aRuleSettingEnum;                      //~@@01I~
    public  PrefSettingEnum aPrefSettingEnum;                      //~@@01I~
    public  MainView aMainView;                                    //~@@01R~
    public  WDA aWDA;                                              //~@@01I~
    public  WDAR aWDAR;                                            //~@@01I~
    public  WDI aWDI;                                              //~@@01I~
    public  IPConnectionWD aIPConnectionWD;                        //~@@01R~
    public  HistoryDlg aHistoryDlg;                                //~@@01I~
    public  SuspendIOErrReqDlg aSuspendIOErrReqDlg;                //~@@01I~
    public  SuspendIOErrDlg aSuspendIOErrDlg;                      //~@@01I~
    public  ActionAlert aActionAlert;                              //~@@01I~
    public  UAD2Touch aUAD2Touch;                                  //~@@01R~
    public  Shanten aShanten;                                      //~va60I~
    public  RoundStat aRoundStat;                                  //~va60R~
    public  RADiscard aRADiscard;                                  //~va60I~
    public  RADSmart  aRADSmart;                                   //~va60I~
    public  RADSEval  aRADSEval;                                   //~va66I~
    public  RACall    aRACall;                                     //~va60I~
    public  RAReach   aRAReach;                                    //~va60I~
    public  RARon     aRARon;                                      //~va60R~
                                                                   //~@@01I~
    public  ProgDlg progDlg;                                       //~@@01I~
//  public ProgressDialog androidDialog;                           //~va40R~
    public Sound aSound;                                           //~@@01I~
                                                                   //~@@01I~
    public Stack<View> stackSnackbarLayout=new Stack<View>();      //~@@01I~
//*BT                                                              //~@@01I~
    public BTI aBTI;                                 //~v107R~//~@@@@R~//~1Ad7R~//~v@@@R~//~@@01I~
    public BTMulti aBTMulti;                               //~v@@@R~//~@@01I~
    public IPMulti aIPMulti;                                       //~@@01I~
    public BTCDialog aBTCDialog;                            //~v@@@M~//~@@01I~
    public BTRDialog aBTRDialog;                                   //~@@01I~
//  public MsgIO aMsgIO;                                    //~v@@@I~//~@@01R~
    public PartnerFrame aPartnerFrameIP;                           //~@@01R~
    public IPSubThread aIPSubThread;                               //~@@01I~
    public CSI aCSI;                                               //~@@01I~
//*Utils                                                           //~@@01I~
	public static final int TSID_MAX        =1;                    //~@@01I~
	public long[] Stimestamp=new long[TSID_MAX];                   //~@@01I~
//*UFile                                                           //~@@01I~
	public  boolean swSDAvailable=true;                            //~@@01I~
	public  String dirSD;                                          //~@@01I~
//*Other                                                           //~@@01I~
    public int osVersion;                                   //~vab0I~//~v101I~//~1Ad7R~//~v@@@R~//~@@01M~
    public static final int HONEYCOMB=11; //android3.0 (GINGERBREAD=9)//~vab0I~//~v101I~//~@@01I~
    public static final int HONEYCOMB_MR1=12; //android3.1         //~1A4hI~//~1Ad6I~//~@@01I~
    public static final int HONEYCOMB_MR2=13; //android3.2         //~1A6pI~//~@@01I~
    public static final int ICE_CREAM_SANDWICH=14; //android4.0    //~vab0I~//~v101I~//~@@01I~
    public String ruleSyncDate="";                                 //~@@01I~
                                                                   //~@@01I~
    public HistoryData resumeHD;                                   //~@@01I~
    public CommonListener.CommonListenerI aCommonListenerI;        //~@@01I~
    public int dialogPaddingHorizontal; //by UFDlg                 //~@@01I~
    public int ctrSaveAlert;                                       //~@@01R~
    private int msgSeqNo;                                          //~@@01I~
    private int[] msgSeqNoEach=new int[PLAYERS];                   //~@@01I~
    private Integer msgSeqNoLock=new Integer(0);    //lockword     //~@@01I~
    public boolean swTrainingMode;                                 //~va66R~
    public boolean swPlayAloneNotify;                              //~va70I~
    public boolean swPlayMatchNotify;                              //~vaa2I~
	public UScoped aUScoped;                               //~1Ak0I~//~1ak0I~
	public boolean swScoped;                               //~1Ak0I~//~1ak0I~
	public boolean swScopedGranted;                                //~vae0I~
	public UMediaStore aUMediaStore;                       //~1Ak2I~//~1ak2I~
	public boolean swGrantedExternalStorageRead,swGrantedExternalStorageWrite;//~1Ak2R~//~1ak2I~
	public boolean swChangedPreference,swChangedRule;              //~vae8R~
	public boolean swNewA10=true;	//navigationbar hide logic for Android10//~vaeeI~
//    private ArrayList<UFDlg> listUFDlg=new ArrayList<UFDlg>();   //+vaf0R~
//************************************                             //~@@01I~
//*static Bitmaps                                                  //~@@01I~
//************************************                             //~@@01I~
//*Pieces                                                          //~@@01I~
    public  Bitmap[][][] bitmapAllPieces;           //[standing/lying][man/pin/sou/ji][number]//~@@01I~
    public  Bitmap[][][] bitmapAllPiecesRiver;      //[standing/lying][man/pin/sou/ji][number]//~@@01I~
    public  Bitmap[][] bitmapStock;                                //~@@01I~
    public  Bitmap[][] bitmapStarter;                              //~@@01I~
    public  Bitmap[][] bitmapPointStick;                           //~@@01I~
    public  Bitmap[]   bitmapDice;                                 //~@@01I~
    public  Bitmap[]   bitmapBird;                                 //~@@01I~
////************************************                             //~@@@@I~//~1Ad7R~
    public AG()                                                    //~@@01I~
    {                                                              //~@@01I~
    }                                                              //~@@01I~
    public void init(MainActivity Pmain)                        //~1402I~//~v107R~//~@@@@R~//~1Ad7R~//~@@01R~
    {                                                              //~1402I~//~1Ad7R~
    //*need reset for each activity start time because static is not reinitialized//~@@01I~
//        aGC=null;                                                //~@@01R~
//        aRule=null;                                              //~@@01R~
//        aTiles=null;                                             //~@@01R~
//        aGameView=null;                                          //~@@01R~
//        aDiceBox=null;                                           //~@@01R~
//        aRiver=null;                                             //~@@01R~
//        aGCanvas=null;                                           //~@@01R~
//        aStock=null;                                             //~@@01R~
//        aHands=null;                                             //~@@01R~
//        aPlayers=null;                                           //~@@01R~
//        aPointStick=null;                                        //~@@01R~
//        aAccounts=null;                                          //~@@01R~
//        aComplete=null;                                          //~@@01R~
//        aStarter=null;                                           //~@@01R~
//        aGMsg=null;                                              //~@@01R~
//        aPieces=null;                                            //~@@01R~
//        aNamePlate=null;                                         //~@@01R~
//        aStatus=null;                                            //~@@01R~
//        aGraphics=null;                                          //~@@01R~
//        aEarth=null;                                             //~@@01R~
//        aICanvas=null;                                           //~@@01R~
//                                                                 //~@@01R~
//        progDlg=null;                                            //~@@01R~
                                                                   //~@@01I~
////******************************************************           //~@@@@I~//~1Ad7R~
        osVersion=Build.VERSION.SDK_INT;                //~vab0I~  //~v101I~//~1Ad7R~//~v@@@R~
        aMainActivity=Pmain;                                            //~1109I~//~1329R~//~1402I~//~@@@@R~//~1Ad7R~
        activity=(Activity)Pmain;                                //~1402I~//~@@@@R~//~1Ad7R~//~v@@@R~
        context=(Context)Pmain;                                  //~1402I~//~@@@@R~//~1Ad7R~
        resource=Pmain.getResources();                                //~1109I~//~1329R~//~1402I~//~@@@@R~//~1Ad7R~//~@@01M~
        inflater=Pmain.getLayoutInflater();                           //~1113I~//~1329R~//~1402I~//~@@@@R~//~1Ad7R~//~v@@@R~//~@@01M~
                                                                   //~@@01I~
        isDebuggable=Utils.isDebuggable(context);             //~v107I~//~@@@@R~//~1Ad7R~
        if (isDebuggable)                                          //~@@01R~
        {                                                          //~vae8I~
//      	Dump.open("");	//write all to Terminal log,not exception only//~@@01R~//~vae8R~
//  		Dump.open("Dump.txt",false/*sdcard*/);                 //~vae8I~//~vaf1R~
        	Dump.open("");	//write all to Terminal log,not exception only//~vaf1I~
		}                                                          //~vae8I~
//        startupCtr=Prop.getPreference(PKEY_STARTUPCTR,0);    //~v107I~//~@@@@R~//~1Ad7R~
//        Prop.putPreference(PKEY_STARTUPCTR,startupCtr+1);    //~v107I~//~@@@@R~//~1Ad7R~
//      fragmentManager=aMainActivity.getFragmentManager();        //~va40R~
        fragmentManager=aMainActivity.getSupportFragmentManager();//~va40I~
        appName=context.getText(R.string.app_name).toString();     //~1402I~//~1Ad7R~
        appNameE=Utils.getStr(R.string.app_nameE);                 //~@@01I~
//        pkgName=context.getPackageName();                          //~1A6aI~//~1Ad7R~
//        appVersion=context.getText(R.string.Version).toString();   //~1506I~//~1Ad7R~
//                                                                   //~v101I~//~1Ad7R~
//        if (osVersion>=HONEYCOMB && osVersion<ICE_CREAM_SANDWICH)  //android3 api11-13//~vab0R~//~v101I~//~1Ad7R~
//            bottomSpaceHeight=SYSTEMBAR_HEIGHT;                    //~vab0I~//~v101I~//~1Ad7R~
        mainThread=Thread.currentThread();                         //~@@@@I~//~1Ad7R~//~v@@@R~
        Locale locale=Locale.getDefault();                         //~@@@@I~//~1Ad7R~//~v@@@R~
//        Glocale=locale.toString();  //ja_JP                        //~1820R~//~@@@@I~//~1Ad7R~
        language=locale.getLanguage();   //ja(Locale.JAPANESE) or ja_JP(Locale.JAPAN)//~1531R~//~@@@@I~//~1Ad7R~//~v@@@R~
////      isLangJP=language.substring(0,2).equals(Locale.JAPANESE);  //~@@@@I~//~v102R~//~1Ad7R~
        isLangJP=language.substring(0,2).equals(Locale.JAPANESE.getLanguage());  //~@@@@I~//~v102I~//~1Ad7R~//~v@@@R~
        helpFileSuffix=isLangJP ? "_ja" : "";                      //~v@@@I~
//        Options=Prop.getPreference(PKEY_OPTIONS,                   //~@@@@R~//~1Ad7R~
//                0                                                  //~@@@@I~//~1Ad7R~
//                |OPTIONS_BIG_TIMER                                 //~@@@@I~//~1Ad7R~
//                |OPTIONS_SHOW_LAST                                 //~@@@@I~//~1Ad7R~
//                |OPTIONS_COORDINATE                                //~@@@@I~//~1Ad7R~
//                );                                                 //~@@@@R~//~1Ad7R~
//        MainFrameOptions.initialOptions();                       //~1Ad7R~
//      YourName=Utils.getStr(R.string.DefaultYourName);           //~v@@@R~//~@@01R~
        YourName=Utils.getPreference(PREFKEY_YOURNAME,YourName);   //~@@01I~
        Utils.putPreference(PREFKEY_YOURNAME,YourName);            //~@@01I~
//        LocalOpponentName=resource.getString(R.string.LocalOpponentName);//~@@@@I~//~1Ad7R~
//        BlackSign=resource.getString(R.string.BlackSign);          //~@@@@R~//~1Ad7R~
//        WhiteSign=resource.getString(R.string.WhiteSign);          //~@@@@R~//~1Ad7R~
//        BlackName=resource.getString(R.string.Black);              //~@@@@I~//~1Ad7R~
//        WhiteName=resource.getString(R.string.White);              //~@@@@I~//~1Ad7R~
//        Move1stSign=resource.getString(R.string.Move1st);          //~@@@@I~//~1Ad7R~
//        Move2ndSign=resource.getString(R.string.Move2nd);          //~@@@@I~//~1Ad7R~
//        if (isLangJP)                                              //~1023I~//~1Ad7R~
//            SshogiV=SshogiVJ; //japanese rank                      //~1023I~//~1Ad7R~
//        else                                                       //~1023I~//~1Ad7R~
//            SshogiV=SshogiVE; //letter rank                        //~1023I~//~1Ad7R~
//        screenDencityMdpi=resource.getDisplayMetrics().density==1.0;//~1A50I~//~1Ad7R~
		Properties p=System.getProperties();                       //~v@@@I~
		dirSep=p.getProperty("file.separator");                    //~v@@@I~
        createSettings();                                          //~@@01R~
        loadProp();                                                //~v@@@I~
//  	new Sound();     //after USCoped init                      //~@@01M~//~vae9R~
    }                                                              //~1402I~//~1Ad7R~
////****************                                                 //~1126I~//~1Ad7R~
////*for Modal Dialog                                                //~1126I~//~1Ad7R~
////****************                                                 //~1126I~//~1Ad7R~
    public static boolean isMainThread()                           //~1126M~//~1Ad7R~//~v@@@R~
    {                                                              //~1126M~//~1Ad7R~//~v@@@R~
        boolean rc=(Thread.currentThread()==AG.mainThread);              //~1126M~//~1Ad7R~//~v@@@R~//~@@01R~
        if (Dump.Y) Dump.println("AG.isMainThread rc="+rc+",curr="+Thread.currentThread().toString()+",main="+AG.mainThread.toString());//~@@01I~
        return rc;                                                 //~@@01I~
    }                                                              //~1126M~//~1Ad7R~//~v@@@R~

////*******************                                              //~1120I~//~v@@@R~
//    public static String home ()                                   //~1Ad7I~//~v@@@R~
//    {                                                              //~1Ad7I~//~v@@@R~
//        return Home;                                               //~1Ad7I~//~v@@@R~
//    }                                                              //~1Ad7I~//~v@@@R~
//    public static void getHome()                                   //~1Ad7I~//~v@@@R~
//    {                                                              //~1Ad7I~//~v@@@R~
//        Properties p=System.getProperties();                       //~1Ad7I~//~v@@@R~
//        Home=p.getProperty("user.home")+p.getProperty("file.separator");//~1Ad7R~//~v@@@R~
//    }                                                              //~1Ad7I~//~v@@@R~
//*************************************************************    //~v@@@I~
//  private static void loadProp()                                 //~@@01R~
    public  static void loadProp()                                 //~@@01I~
    {                                                              //~v@@@I~
        AG.ruleProp=loadProp(ruleFile,ruleFileDefault);               //~v@@@R~
//      AG.opeProp=loadProp(opeFile,opeFileDefault);                  //~v@@@R~//~@@01R~
        AG.prefProp=loadProp(prefFile,prefFileDefault);            //~@@01I~
//      AG.ruleSyncDate=AG.ruleProp.getParameter(getKeyRS(RSID_SYNCDATE),"Unknown");//~@@01R~
        AG.ruleSyncDate=AG.ruleProp.getParameter(getKeyRS(RSID_SYNCDATE),PROP_INIT_SYNCDATE);//~@@01I~
    	if (Dump.Y) Dump.println("AG.loadProp ruleSyncDAte="+AG.ruleSyncDate);//~@@01I~
    	if (AG.ruleSyncDate.equals(PROP_INIT_SYNCDATE))            //~@@01I~
	        AG.ruleProp.setParameter(getKeyRS(RSID_SYNCDATE),PROP_INIT_SYNCDATE);//~@@01I~
    }                                                              //~v@@@I~
//*************************************************************    //~vae8I~
//*1st run after (re-)instlation before recoverProp                //~vae8I~
//*************************************************************    //~vae8I~
    public static boolean is1stRun()                               //~vae8I~
    {                                                              //~vae8I~
        boolean rc=Utils.getPreference(PREFKEY_SAVED,"").equals("");//~vae8I~
        if (Dump.Y) Dump.println("AG.is1stRun rc="+rc);            //~vae8I~
        return rc;
    }                                                              //~vae8I~
    public static void reset1stRun()                               //~vae8I~
    {                                                              //~vae8I~
        if (Dump.Y) Dump.println("AG.reset1stRun");         //~vae8I~
        Utils.putPreference(PREFKEY_SAVED,"1");                    //~vae8I~
    }                                                              //~vae8I~
//*************************************************************    //~vae8I~
//*from MainActivity                                               //~vae8I~
//*************************************************************    //~vae8I~
    public  void recoverProp()                                     //~vae8I~
    {                                                              //~vae8I~
        if (Dump.Y) Dump.println("AG.recoverProp");                //~vae8I~
        if (is1stRun())                                            //~vae8R~
        {                                                          //~vae8I~
        	PrefSetting.recoverProp(prefFile);                     //~vae8R~
        	RuleSetting.recoverProp(ruleFile);                     //~vae8R~
        	reset1stRun();	//even failed, shared pref only,no need to prop//~vae8R~
        }                                                          //~vae8I~
    }                                                              //~vae8I~
//*************************************************************    //~vae8I~
    public  void saveProp()                                        //~vae8I~
    {                                                              //~vae8I~
        if (Dump.Y) Dump.println("AG.saveProp");                   //~vae8I~
        PrefSetting.saveProp(prefFile);                                //~vae8I~
        RuleSetting.saveProp(ruleFile);                                //~vae8I~
    }                                                              //~vae8I~
//*************************************************************    //~@@01I~
//  private void createSettings()                                  //~@@01R~
    public  void createSettings() //public for androidTest         //~@@01I~
    {                                                              //~@@01I~
    	if (Dump.Y) Dump.println("AG.createSettings");             //~@@01I~
        new RuleSettingEnum();                                     //~@@01I~
        new PrefSettingEnum();                                     //~@@01I~
    }                                                              //~@@01I~
//*************************************************************    //~v@@@I~
    public  static Prop loadProp(String Pcurrent,String Pdefault)  //~v@@@R~//~@@01R~
    {                                                              //~v@@@I~
    	boolean rc=false;                                          //~v@@@I~
        Prop prop=new Prop();                                      //~v@@@I~
        if (!UFile.isExistInFiles(Pdefault))	//not exist        //~v@@@R~
        {                                                          //~v@@@I~
        	UFile.copyAssetToDataDir(Pdefault,Pdefault);   //copy asset to files dir//~v@@@R~
        }                                                          //~v@@@I~
        if (!UFile.isExistInFiles(Pcurrent))	//not exist        //~v@@@I~
        {                                                          //~v@@@I~
        	if (!UFile.copyAssetToDataDir(Pdefault,Pcurrent))   //copy asset to files dir//~v@@@I~
            	return prop;	//null                             //~v@@@I~
        }                                                          //~v@@@I~
        prop.loadPropFiles(Pcurrent);                              //~v@@@R~
        return prop;//~v@@@I~
    }                                                              //~v@@@I~
//*************************************************************    //~@@01I~
    public long getMsgSeqNo(int Pidx)                              //~@@01R~
    {                                                              //~@@01I~
    	long rc;                                                   //~@@01R~
        synchronized(msgSeqNoLock)                                 //~@@01I~
        {                                                          //~@@01I~
    		msgSeqNo++;                                            //~@@01I~
    		int each=++msgSeqNoEach[Pidx];                         //~@@01R~
	        rc=((long)msgSeqNo<<32)|each;                          //~@@01R~
        }                                                          //~@@01I~
        return rc;                                                 //~@@01R~
    }                                                              //~@@01I~
//*************************************************************    //~va40I~
    public        int getColor(int Pcolor)                         //~va40R~
    {                                                              //~va40I~
    	int rc;                                                    //~va40I~
		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) //api23 Marshmallow android6//~va40I~
        	rc= ContextCompat.getColor(AG.context,Pcolor);           //~va40I~
        else                                                       //~va40I~
		    rc=getColor_Under23(Pcolor);                        //~va40I~
        return rc;                                                 //~va40I~
    }                                                              //~va40I~
//*************************************************************    //~va40I~
	@SuppressWarnings("deprecation")                                //~va40I~
    private       int getColor_Under23(int Pcolor)                 //~va40R~
    {                                                              //~va40I~
        return AG.resource.getColor(Pcolor);                    //~va40I~
    }                                                              //~va40I~
////*************************************************************  //~vaf0R~
//    public void stackFragment(UFDlg Pdf)                         //~vaf0R~
//    {                                                            //~vaf0R~
//        listUFDlg.add(Pdf);                                      //~vaf0R~
//        if (Dump.Y) Dump.println("AG.stackFragment UFDlg="+Pdf.toString()+",listSize="+listUFDlg.size());//~vaf0R~
//    }                                                            //~vaf0R~
////*************************************************************  //~vaf0R~
//    public boolean removeFragment(UFDlg Pdf)                     //~vaf0R~
//    {                                                            //~vaf0R~
//        if (Dump.Y) Dump.println("AG.removeFragment UFDlg="+Pdf.toString());//~vaf0R~
//        boolean rc=listUFDlg.remove(Pdf);                        //~vaf0R~
//        if (Dump.Y) Dump.println("AG.removeFragment rc="+rc+",size="+listUFDlg.size());//~vaf0R~
//        return rc;                                               //~vaf0R~
//    }                                                            //~vaf0R~
////*************************************************************  //~vaf0R~
//    public boolean popFragment()                                 //~vaf0R~
//    {                                                            //~vaf0R~
//        int sz=listUFDlg.size();                                 //~vaf0R~
//        if (Dump.Y) Dump.println("AG.popFragment size="+sz);     //~vaf0R~
//        boolean rc=false;                                        //~vaf0R~
//        if (sz!=0)                                               //~vaf0R~
//        {                                                        //~vaf0R~
//            UFDlg df=listUFDlg.get(sz-1);                        //~vaf0R~
//            df.dismissPop();                                     //~vaf0R~
//            if (Dump.Y) Dump.println("AG.popFragment issue dismiss UFDlg="+df.toString());//~vaf0R~
//            rc=true;                                             //~vaf0R~
//        }                                                        //~vaf0R~
//        if (Dump.Y) Dump.println("AG.popFragment rc="+rc);       //~vaf0R~
//        return rc;                                               //~vaf0R~
//    }                                                            //~vaf0R~
}//class AG                                                        //~1107R~
