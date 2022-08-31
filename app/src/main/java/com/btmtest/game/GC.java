//*CID://+vaq8R~: update#= 882;                                    //~vaq8R~
//**********************************************************************//~v101I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~va60I~
//2022/08/19 vaq8 (Bug)doubleRon by Win-Anyway;2nd ron ignored Win-Anyway requested and issie compose err//~vaq8I~
//2022/04/23 vamv change server color for Connect Button and GameType on gameboard//~vamvR~
//2022/04/05 vamg Animation. at Win call                           //~vamgI~
//2022/03/09 vakr (Bug)PAN mode; call not postDelayedAutoTakeKan but postDelayedAutoTake when human pushed Kan button//~vakrI~
//2022/01/31 vaji change color of top left to identify server      //~vajiI~
//2021/12/31 vaii notifyPlayMatch:Chii overrun Pon button push(Chii button lit yellow after Pon pushed for the discarded tile of Pon and Chii)//~vaiiI~
//2021/11/13 vagn (Bug)GC buttons remains active because Robot Ron did not stop Notifing Pon to human//~vagnI~
//2021/10/23 vaf4 (Bug)Could not back to top at before game start on client when ioerr occured. msg issued "try from server"//~vaf4I~
//2021/09/27 vaef gesture navigation mode from android11
//2021/09/26 vaee gesture navigation mode from android10           //~vaeeI~
//2021/08/24 vad1 game buttons layout for lefty                    //~vad1I~
//2021/08/22 vad0 button size for small device but not small dpi   //~vad0I~
//2021/08/22 vacf vertical button label for landscape              //~vacfI~
//2021/08/21 vacd (Bug)PlayAlone mode; at blocked by Ron issued, Discard btn reset Cancel btn and issue msg  select meld then push orange.(Ron is not select multi meld candidate case)//~vacdI~
//2021/08/18 vacb Win btn do AinAny after WinAny button.           //~vacbI~
//					(if Win is not cancalable Win btn dose not change to orange and Score btn winn change to orange directly)//~vacbI~
//2021/08/18 vaca test SmallFont dialog                            //~vacaI~
//2021/08/11 vac3 add BGM kouka                                    //~vac3I~
//2021/08/03 vac0 display Match type                               //~vaahI~
//2021/07/29 vabh (Bug)cancel of Ankan, sendmsg not GCM_NEXT_PLAYER but schedule autodiscard//~vaahI~
//2021/07/24 vaaZ PlayAlone mode;after Cancel Ron,discard rejected by err msg of "push orange btn" when ron is cancelable//~vaaZI~
//2021/07/23 vaaV PlayAlone mode;win btn after chankan cause err NotYourTurn//~vaaVI~
//2021/07/19 vaaU chankan was not notified                         //~vaaUI~
//2021/07/03 vaah For PlayAloneNotify mode,reject other action when cancel btn shown//~vaahI~
//2021/07/01 vaaf Button BG color by span string because setbackgroundColor is expands button to its boundary.//~vaafI~
//2021/06/27 vaa2 Notify mode of Match                             //~vaa2I~
//2021/06/17 va9f correct reason of reverse orientation did not work(fix orientation was called)//~va9fI~
//2021/06/15 va97 (Bug)CompReq button left as background table color if pushed at off(not orange)//~va97I~
//2021/05/04 va8B (Bug)when Score(CmpReqDlg) btn is pushed to open CompReqDlg, Pon button size is shorten//~va8BI~
//2021/04/13 va86 show RonAnyWay button by not preference by operation rule//~va86I~
//2021/04/11 va80 return to top by back btn when gameover         //~va80R~//~vaahR~
//2021/04/05 va78 (Bug)PlayAlone notifymode; next player is not blocked by pending on//~va78I~
//2021/03/31 va75 Autotake when Notify mode(Chii or Take)          //~va75I~
//2021/03/31 va74 va60 ignore robot Ron if Human  ron is cancelable, Now allow schedule next Robot ron if human canceled also when trainingmode without notify option//~va74I~
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/03/15 va6i add BGM of eburishou kouka                       //~va6iI~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va6iI~
//2021/01/23 va61 (BUG)abend  when bgm=off                         //~va61I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~//~va6iM~
//2020/11/21 va49 highlight compreqdlg button when Ron             //~va49I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//2020/11/03 va27 Tenpai chk at Reach                              //~va27I~
//2020/04/27 va06:BGM                                              //~va06I~
//2020/04/13 va02:At Server,BackButton dose not work when client app canceled by androiud-Menu button//~va02R~
//**********************************************************************//~va02I~
package com.btmtest.game;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.BT.BTControl;
import com.btmtest.BT.BTMulti;
import com.btmtest.MainActivity;
import com.btmtest.MainView;
import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.dialog.AccountsDlg;
import com.btmtest.dialog.ActionMenuDlg;
import com.btmtest.dialog.BTCDialog;
import com.btmtest.dialog.CompReqDlg;
import com.btmtest.dialog.CompleteDlg;
import com.btmtest.dialog.MenuInGameDlg;
import com.btmtest.dialog.PrefSetting;                             //~9630I~
import com.btmtest.dialog.RuleSettingEnum;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.dialog.ScoreDlg;
import com.btmtest.dialog.TestLayout;
import com.btmtest.game.UA.UARestart;
import com.btmtest.game.UA.UARon;
import com.btmtest.game.RA.RoundStat;                              //~va60I~
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.gui.UButton;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;
import com.btmtest.utils.EventCB;
import com.btmtest.utils.URunnable;
import com.btmtest.utils.UView;//~v@@@R~

import static android.util.TypedValue.*;
import static com.btmtest.AG.*;
import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.Status.*;//~v@@@I~
import com.btmtest.game.gv.GameView;                               //~v@@@I~
import com.btmtest.game.UA.UAEndGame;                              //~9303I~
import com.btmtest.dialog.RuleSetting;
import com.btmtest.utils.UiFunc;
import com.btmtest.utils.Utils;
import com.btmtest.utils.sound.Sound;
import com.btmtest.wifi.WDA;

import static com.btmtest.game.GConst.*;                           //~v@@@R~
import static com.btmtest.StaticVars.AG;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.utils.Alert.*;
import static com.btmtest.game.UAD2Touch.*;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Message;
import android.view.Gravity;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GC implements UButton.UButtonI                        //~v@@@R~
   							,Alert.AlertI                          //~9B25I~
							,URunnable.URunnableI                  //~v@@@I~
{                                                                  //~0914I~
    private static final int BUTTONS_RIGHT =R.layout.gvbuttons_right;//~v@@@I~
    private static final int BUTTONS_LEFT  =R.layout.gvbuttons_left ;//~v@@@I~
    private static final int BUTTONS_RIGHT_SMALL =R.layout.gvbuttons_right_small;//~9808I~
    private static final int BUTTONS_LEFT_SMALL  =R.layout.gvbuttons_left_small ;//~9808I~
    private static final int BUTTONS_RIGHT_SMALLFONT =R.layout.gvbuttons_right_smallfont;//~vad0I~
    private static final int BUTTONS_LEFT_SMALLFONT  =R.layout.gvbuttons_left_smallfont ;//~vad0I~
    private static final int BUTTONS_TOP   =R.layout.gvbuttons_top  ;//~v@@@I~
    private static final int BUTTONS_BOTTOM=R.layout.gvbuttons_bottom;//~v@@@I~
    private static final int BUTTONS_TOP_SMALL   =R.layout.gvbuttons_top_small  ;//~9808I~
    private static final int BUTTONS_BOTTOM_SMALL=R.layout.gvbuttons_bottom_small;//~9808I~
    private static final int BUTTONS_TOP_SMALLFONT   =R.layout.gvbuttons_top_smallfont  ;//~vad0I~
    private static final int BUTTONS_BOTTOM_SMALLFONT=R.layout.gvbuttons_bottom_smallfont;//~vad0I~
                                                                   //~vad1I~
    private static final int BUTTONS_BOTTOM_REV=R.layout.gvbuttons_bottom_rev;//~vad1I~
    private static final int BUTTONS_BOTTOM_SMALL_REV=R.layout.gvbuttons_bottom_small_rev;//~vad1I~
    private static final int BUTTONS_BOTTOM_SMALLFONT_REV=R.layout.gvbuttons_bottom_smallfont_rev;//~vad1I~
                                                                   //~vad1I~
    private static final int LAYOUTID_BUTTONS1=R.id.GameViewButtons1;  //~v@@@I~
    private static final int LAYOUTID_BUTTONS2=R.id.GameViewButtons2;  //~v@@@I~
                                                                   //~v@@@I~
    private static final int BTNID_TAKE    =R.id.UserAction_Take;  //~v@@@I~
    private static final int BTNID_PON     =R.id.UserAction_Pon;   //~v@@@I~
    private static final int BTNID_CHII    =R.id.UserAction_Chii;  //~v@@@I~
    private static final int BTNID_KAN     =R.id.UserAction_Kan;   //~v@@@I~
    private static final int BTNID_REACH   =R.id.UserAction_Reach; //~v@@@I~
    private static final int BTNID_REACH_RESET   =R.id.UserAction_Reach_Reset;//~9A30I~
    private static final int BTNID_RON     =R.id.UserAction_Ron;   //~v@@@I~
    private static final int BTNID_DISCARD =R.id.UserAction_Discard;//~v@@@I~
    private static final int BTNID_WAITON  =R.id.UserAction_WaitOn;//~v@@@I~
    private static final int BTNID_WAITOFF =R.id.UserAction_WaitOff;//~v@@@I~
    private static final int BTNID_DLGCOMP =R.id.UserAction_DlgComp;//~v@@@I~
    private static final int BTNID_DLGCOMPR=R.id.UserAction_DlgCompResult;//~v@@@I~
    private static final int BTNID_REACH_OPEN  =R.id.UserAction_Reach_Open;//~9301I~
    private static final int BTNID_REACH_OPEN_RESET  =R.id.UserAction_Reach_Open_Reset;//~9A30I~
    private static final int BTNID_FORCE_REACH =R.id.UserAction_Force_Reach;//~va27I~
    private static final int BTNID_DRAWNGAME   =R.id.UserAction_DrawnGame;//~9302I~
    private static final int BTNID_MINUSSTOP   =R.id.UserAction_MinusStop;//~9322I~
    private static final int BTNID_ENDSCORE    =R.id.UserAction_EndScore;//~9402I~
    private static final int BTNID_MENUINGAME  =R.id.MenuInGame;   //~9406I~
    private static final int BTNID_SHOWRULE    =R.id.ShowRule;     //~9408R~
    private static final int BTNID_ACTION_CANCEL=R.id.UserAction_Cancel;//~9B25I~
    private static final int BTNID_ANYWAY=R.id.UserAction_Anyway;  //~va06I~
                                                                   //~v@@@I~
    private static final int BTNID_F1      =R.id.Func1;   //TODO   //~v@@@I~
    private static final int BTNID_F2      =R.id.Func2;   //TODO   //~v@@@I~
    private static final int BTNID_F3      =R.id.Func3;   //TODO   //~v@@@I~
    private static final int BTNID_F4      =R.id.Func4;   //TODO   //~v@@@I~
    private static final int BTNID_F5      =R.id.Func5;   //TODO   //~v@@@I~
    private static final int BTNID_F6      =R.id.Func6;   //TODO   //~v@@@I~
                                                                   //~9301I~
//  private static final int COLOR_ACTIVEBTN=Color.argb(0xff,0xff,0xa5,0x00);//orange//~9301I~//~vacbR~
//  private static final int COLOR_BTN_NORMAL_BG= AG.getColor(R.color.btn_normal_bg);		//yellow//~vaafI~//~vaa2I~//~vajiR~
    public  static final int COLOR_BTN_NORMAL_BG= AG.getColor(R.color.btn_normal_bg);		//yellow//~vajiI~
                                                                   //~vaahI~
//  private static final int COLOR_GST_DEBUG=AG.getColor(R.color.btn_normal_bg);//~vaahR~
    private static final int COLOR_GST_DEBUG  =Color.argb(0xff,0xc0,0xf0,0x00);//~vaahR~
    private static final int COLOR_GST_DEBUG_CLIENT  =Color.argb(0xff,0xc0,0xf0,0xa0);//~vajiR~
//  private static final int COLOR_GST_RELEASE=Color.argb(0xff,0x00,0xf0,0xc0);//~vaahI~//~vajiR~
//  public  static final int COLOR_GST_RELEASE=Color.argb(0xff,0x00,0xf0,0xc0);//~vajiR~//~vamvR~
    public  static final int COLOR_GST_RELEASE=Color.argb(0xff,0xff,0xd7,0x00);   //server:Gold//~vamvI~
    public  static final int COLOR_GST_RELEASE_CLIENT=Color.argb(0xff,0xc0,0xf0,0xf0);//~vajiR~
//  private static final int COLOR_GST_RELEASE_TEXT=Color.argb(0xff,0xff,0x00,0x00);//orange//~vaahR~//~vamvR~
//  private static final int COLOR_GST_RELEASE_TEXT_CLIENT=Color.argb(0xff,0x40,0x00,0x00);//orange//~vajiI~//~vamvR~
                                                                   //~v@@@I~
    private static final int URO_STARTGAME=1;                      //~v@@@I~
    private static final int URO_ADDVIEW=2;                        //~v@@@I~
    //~v@@@I~
    private static final int BGC_BUTTON=Color.argb(0xff,0xff,0xff,0xff);//~v@@@I~
                                                                   //~v@@@I~
//  public final static int btnTopH=(int)AG.resource.getDimension(R.dimen.gvbtn_top);//~v@@@M~//~9808R~
//  public final static int btnBottomH=(int)AG.resource.getDimension(R.dimen.gvbtn_bottom);//~v@@@I~//~9808R~
//  public final static int btnLeftW=(int)AG.resource.getDimension(R.dimen.gvbtn_left);//~v@@@I~//~9808R~
//  public final static int btnRightW=(int)AG.resource.getDimension(R.dimen.gvbtn_right);//~v@@@I~//~9808R~
    public int btnLeftW,btnRightW,btnTopH,btnBottomH;              //~9808R~
                                                                   //~v@@@I~
//    private static Point frameLayoutSizePortrait,frameLayoutSizeLandscape;//~v@@@M~//~9411R~
                                                                   //~v@@@I~
    private GameView gameView;                                     //~v@@@R~
    private FrameLayout frame;                                            //~v@@@I~
    public  boolean swPortrait;                                    //~v@@@R~
    private boolean swCreatedView;
//  private UserAction userAction;                                 //~v@@@R~
    private int spotDice,player1stDice,playerTempStarter;          //~v@@@R~
    public int playerStarter;                                      //~v@@@R~
//  public String[] memberName;                                    //~v@@@I~//~0305R~
    private int[] posMember;                                       //~v@@@I~
    private int positionYou;                                       //~v@@@I~
    private View btns1,btns2;                                      //~v@@@I~
    private int frameLayoutWWL,frameLayoutHHL;                         //~v@@@I~
    private boolean swReach,swReachOpen;                           //~v@@@I~
//    private UButton btnReach,btnReachOpen,btnRon;                //~9301I~
	private Button btnPon,btnKan,btnChii,btnRon;                   //~9B25I~
    private Button btnReach,btnReachOpen,btnReachReset,btnReachOpenReset;//~9A30I~
    private Button btnForceReach;                                  //~va27I~
    private Button btnTake,btnDiscard;                             //~9701I~
    private Button btnAnyway;                                      //~va06I~
    private Button btnActionCancel;                                //~9B25I~
    private Button btnCompReq;                                     //~va49I~
    private boolean swBtnReach;                                    //~9A30I~
    private boolean swOpenReach;                                   //~9A31I~
    private boolean swOpenReachNow;                                //~va27I~
    public  boolean swGameView;                                    //~9B06I~
//  private int btnBackgroundColor;                                //~9B25R~
//  private Drawable btnBackgroundColor;                           //~9B25I~//~vaafR~
//  private int btnTextColor;                                      //~vaa2I~//~vaafR~
//  private Drawable btnBackgroundColorCompReqDlg;                 //~va8BI~//~vaafR~
//    private boolean swReconnect;                                 //~0411R~
	private int connectionCtrAtStartGame;                          //~va02I~
	private  int statusPlayAlone;                                   //~va70R~
                                                                   //~vaa2I~
	public  int statusPlayMatchNotify;                             //~vaa2R~
	private static final int SPMN_PON      =0x01;                  //~vaa2I~
	private static final int SPMN_KAN      =0x02;                   //~vaa2I~
	private static final int SPMN_CHII     =0x04;                   //~vaa2I~
	private static final int SPMN_RON      =0x08;                   //~vaa2I~
                                                                   //~vaa2I~
	private boolean swCancel;                                              //~va75I~
	private boolean swCompReqButtonStatus=false;                   //~va97I~
	private boolean swShownBtnCancel;                              //~vaahI~
	private boolean swChankan;                                     //~vaaVI~
	private boolean swWinAnywayPushed,swWinAnywayActive;           //~vacbR~
	private boolean swWinAnywayMore;                               //~vaq8I~
	private boolean swLeftyPortrait,swLeftyLandscape;              //~vad1I~
	public int marginLR;                                           //~vaefI~
//*************************                                        //~v@@@I~
	public GC()                             //for IT override      //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("GC.default Constructor swTrainingMode="+AG.swTrainingMode);        //~va60I~//~vaa2R~
    }                                                              //~va60I~
	public GC(View Pframe)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("GC Constructor");         //~1506R~//~@@@@R~//~v@@@R~
        frame=(FrameLayout)Pframe;                                              //~v@@@I~
        AG.aGC=this;                                               //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
//*************************                                        //~v@@@I~
	private void init()                                            //~v@@@I~
    {                                                              //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
			swWinAnywayPushed=false; swWinAnywayActive=false;      //~vacbI~
			swWinAnywayMore=false;                                 //~vaq8I~
//          UView.fixOrientation(true);                            //~v@@@I~//~va9fR~
	    	UView.getScreenSize();                                 //~v@@@I~
            swPortrait=AG.scrWidth<AG.scrHeight;                   //~v@@@I~
            swLeftyPortrait=PrefSetting.isLeftyPortrait();         //~vad1I~
            swLeftyLandscape=PrefSetting.isLeftyLandscape();       //~vad1I~
//          btnLeftW =(int)AG.resource.getDimension(AG.swSmallDevice?R.dimen.gvbtn_left_small :R.dimen.gvbtn_left);//~9808M~//~vad0R~
//          btnRightW=(int)AG.resource.getDimension(AG.swSmallDevice?R.dimen.gvbtn_right_small:R.dimen.gvbtn_right);//~9808M~//~vad0R~
//          btnTopH   =(int)AG.resource.getDimension(AG.swSmallDevice?R.dimen.gvbtn_top_small   :R.dimen.gvbtn_top);//~9808I~//~vad0R~
//          btnBottomH=(int)AG.resource.getDimension(AG.swSmallDevice?R.dimen.gvbtn_bottom_small:R.dimen.gvbtn_bottom);//~9808I~//~vad0R~
            btnLeftW  =(int)AG.resource.getDimension(AG.swSmallFont ? R.dimen.gvbtn_left_smallfont//~vad0I~
							: (AG.swSmallDevice ? R.dimen.gvbtn_left_small : R.dimen.gvbtn_left));//~vad0I~
            btnRightW =(int)AG.resource.getDimension(AG.swSmallFont ? R.dimen.gvbtn_right_smallfont//~vad0I~
							: (AG.swSmallDevice ? R.dimen.gvbtn_right_small:R.dimen.gvbtn_right));//~vad0I~
            btnTopH   =(int)AG.resource.getDimension(AG.swSmallFont ? R.dimen.gvbtn_top_smallfont//~vad0I~
							: (AG.swSmallDevice?R.dimen.gvbtn_top_small   :R.dimen.gvbtn_top));//~vad0I~
            btnBottomH=(int)AG.resource.getDimension(AG.swSmallFont ? R.dimen.gvbtn_bottom_smallfont//~vad0I~
							: (AG.swSmallDevice?R.dimen.gvbtn_bottom_small:R.dimen.gvbtn_bottom));//~vad0I~
                                                                   //~vad0I~
        	if(Dump.Y) Dump.println("GC.init dimen Width left="+btnLeftW+",right="+btnRightW);//~9808R~//~vacfR~
        	if(Dump.Y) Dump.println("GC.init dimen Height top="+btnTopH+",bottom="+btnBottomH);//~vacfI~
//            chkLayoutSize();                                       //~v@@@R~//~9411R~
        	if(Dump.Y) Dump.println("GC.init swPortrait="+swPortrait+",ww="+AG.scrWidth+",hh="+AG.scrHeight+",swTrainingMode="+AG.swTrainingMode);//~v@@@I~//~vaa2R~
//          new Accounts();                                        //~v@@@R~
//          new Status();                                          //~v@@@R~
            new Rule();                                            //~v@@@I~//~9C01R~
            AG.aRule.swLeftButtons=swLeftyLandscape;               //~vad1M~
            new Players();                                         //~v@@@I~
            new RoundStat();                                       //~va60I~
            new Tiles();                                           //~v@@@I~
            new Complete();                                        //~v@@@I~
            new LastGame();                                        //~9504I~
            AG.aSound.resetOption();	//update preference-soundOption//~9C01I~
//          memberName=getMemberName();                            //~v@@@M~//~0305R~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
    		Dump.println(e,"GC startMain exception");              //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
////*************************                                        //~1109I~//~1111I~//~1122M~//~9411R~
//    private void chkLayoutSize()                                    //~v@@@I~//~9411R~
//    {                                                              //~v@@@I~//~9411R~
//        Point pold,pnew;                                           //~v@@@R~//~9411R~
//        pnew=UView.getMeasuredSize(frame);                         //~v@@@R~//~9411R~
//        if (swPortrait)                                            //~v@@@I~//~9411R~
//            pold=frameLayoutSizePortrait;                          //~v@@@R~//~9411R~
//        else                                                       //~v@@@I~//~9411R~
//            pold=frameLayoutSizeLandscape;                         //~v@@@R~//~9411R~
//        if(Dump.Y) Dump.println("GC.chkLayoutSize pold="+(pold==null?"null":pold.toString())+",pnew="+pnew.toString());               //~v@@@I~//~9411R~
//        if (pold!=null && pnew.x!=pold.x && pnew.y!=pold.y) //android could not set layout size (reason is unkown)//~v@@@R~//~9411R~
//            setFrameLayoutSize(pold);                              //~v@@@R~//~9411R~
//    }                                                              //~v@@@I~//~9411R~
////**********************************************************       //~v@@@I~//~9412R~
//    private void saveFrameLayoutSize()                                  //~v@@@I~//~9412R~
//    {                                                              //~v@@@I~//~9412R~
//        if (Dump.Y) Dump.println("GC.saveFrameLayoutSize swPortrait="+swPortrait);//~v@@@I~//~9411R~//~9412R~
//        Point p=UView.getMeasuredSize(frame);                      //~v@@@I~//~9412R~
//        if (swPortrait)                                            //~v@@@I~//~9412R~
//        {                                                          //~v@@@I~//~9412R~
//            if (frameLayoutSizePortrait==null)                     //~v@@@I~//~9412R~
//                frameLayoutSizePortrait=p;                         //~v@@@I~//~9412R~
//        }                                                          //~v@@@I~//~9412R~
//        else                                                       //~v@@@I~//~9412R~
//        {                                                          //~v@@@I~//~9412R~
//            if (frameLayoutSizeLandscape==null)                    //~v@@@I~//~9412R~
//                frameLayoutSizeLandscape=p;                        //~v@@@I~//~9412R~
//        }                                                          //~v@@@I~//~9412R~
//    }                                                              //~v@@@I~//~9412R~
////**********************************************************       //~v@@@I~//~9411R~
//    private void setFrameLayoutSize(Point PframeLayoutSize)        //~v@@@R~//~9411R~
//    {                                                              //~v@@@I~//~9411R~
//        if (true)     //TODO                                     //~9411R~
//            return;                                              //~9411R~
//        if (Dump.Y) Dump.println("GC.setFrameLayoutSize ww="+PframeLayoutSize.x+",hh="+PframeLayoutSize.y);//~v@@@R~//~9411R~
//        ViewGroup.LayoutParams lp=frame.getLayoutParams();   //~v@@@I~//~9411R~
//        lp.width=PframeLayoutSize.x;                               //~v@@@R~//~9411R~
//        lp.height=PframeLayoutSize.y;                              //~v@@@R~//~9411R~
//        frame.setLayoutParams(lp);                           //~v@@@I~//~9411R~
//    }                                                              //~v@@@I~//~9411R~
//****************************************************                                        //~v@@@I~//~9503R~
//*from MainActivity Button or Client received GCM_SETTING_NOTIFY_SYNCOK                                        //~9503I~//~9A23R~
//****************************************************             //~9503I~
//	public void startGame()                                        //~v@@@I~//~0119R~
	public boolean startGame()                                     //~0119I~
    {                                                              //~v@@@I~
        boolean rc=true;                                            //~0119I~
        if(Dump.Y) Dump.println("GC.startGame");                   //~v@@@I~
//        (new Thread(new Runnable()                               //~v@@@R~
//                        {                                        //~v@@@R~
//                            @Override                            //~v@@@R~
//                            public void run()                    //~v@@@R~
//                            {                                    //~v@@@R~
//                                init2();                         //~v@@@R~
//                                new EventCB(ECB_ADD_GCVIEW).postEvent();//~v@@@R~
//                            }                                    //~v@@@R~
//                        }                                        //~v@@@R~
//                   )).start();                                   //~v@@@R~
        AG.swPlayAloneNotify=false;                                //~va70I~
        AG.swPlayMatchNotify=false;                                //~vaa2I~
        if (AG.swTrainingMode)                                     //~va60I~
        {                                                          //~va60I~
            AG.swPlayAloneNotify= RuleSettingOperation.isPlayAloneNotify();//~va70I~
            AG.aBTMulti.startGameTrainingMode();                   //~va60I~
        }                                                          //~va60I~
        else                                                       //~va60I~
        {                                                          //~vaa2I~
        	AG.swPlayMatchNotify=RuleSettingOperation.isPlayMatchNotify();//~vaa2R~
		if (BTMulti.isServerDevice())                              //~9621I~
        {                                                          //~9B30I~
//	        AG.aBTMulti.startGameSendName();    //for the case anyone connected/disconnected//~9B30R~
//          AG.aBTMulti.startGameSyncDate();    //send startgame to all client//~9621R~//~0119R~
//          AG.aBTMulti.startGameSyncDate();    //send startgame to all client//~0119I~
            rc=AG.aBTMulti.startGameSyncDate();    //send startgame to all client//~0119I~
        }                                                          //~9B30I~
        else                                                       //~9621I~
			startGameGo();                                         //~9621I~
        }                                                          //~vaa2I~
//        AG.aStatus.startGame();                                    //~v@@@I~//~9621R~
//        URunnable.setRunFuncSubthread(this,0/*delay*/,this/*parmObj*/,URO_STARTGAME);//~v@@@I~//~9621R~
        if(Dump.Y) Dump.println("GC.startGame end rc="+rc);               //~v@@@I~//~9621R~//~0119R~
        return rc;                                                 //~0119I~
    }                                                              //~v@@@I~
//****************************************************             //~va70I~
//* from UserAction.newGame()                                      //~va70I~
//****************************************************             //~va70I~
	public void newGame()                                          //~va70I~
    {                                                              //~va70I~
        if(Dump.Y) Dump.println("GC.newGame");                     //~va70I~
//      statusPlayAlone=0;                                         //~va70R~//~vagnR~
        swChankan=false;                                           //~vaaVI~
//  	swShownBtnCancel=false;                                    //~vaahI~//~vagnR~
        resetButton();                                             //~vagnI~
    }                                                              //~va70I~
//***********************************************************      //~vagnI~
	private void resetButton()                                     //~vagnI~
    {                                                              //~vagnI~
        if (Dump.Y) Dump.println("GC.resetButton swShownBtnCancel="+swShownBtnCancel+",statusPlayAlone="+statusPlayAlone);//~vagnI~
        if (statusPlayAlone!=0)                                    //~vagnI~
        {                                                          //~vagnI~
    		updateActionBtn2Touch(statusPlayAlone,BTN_STATUS_DISABLE_CANCEL,COLOR_NORMAL);//~vagnI~
			statusPlayAlone=0;                                     //~vagnI~
        }                                                          //~vagnI~
		resetPendingPlayMatchNotify(0/*PmsgID*/);                  //~vagnI~
    }                                                              //~vagnI~
//***********************************************************      //~9621R~
//*from BTMulti when Date Synched OK                               //~9621I~
//***********************************************************      //~9621I~
	public void startGameGo()                                      //~9621I~
    {                                                              //~9621I~
        if(Dump.Y) Dump.println("GC.startGameGo");                 //~9621I~
        dismissConnectionDialog();                                 //~9B10I~
        AG.aBTMulti.saveForReconnect();                            //~9A23I~
		AG.aStatus.startGame();                                    //~9621I~
		URunnable.setRunFuncSubthread(this,0/*delay*/,this/*parmObj*/,URO_STARTGAME);//~9621I~
		connectionCtrAtStartGame=AG.aBTMulti.BTGroup.getConnectedCtr();//~va02I~
        if(Dump.Y) Dump.println("GC.startGameGo end");             //~9621I~
    }                                                              //~9621I~
//*************************                                        //~v@@@I~
    @Override                                                      //~v@@@I~
    public void URunnableCB(Object PobjGC,int Paction)                         //~v@@@I~
    {                                                              //~v@@@I~
        if(Dump.Y) Dump.println("GC.URunnableCB action="+Paction); //~v@@@I~
    	switch(Paction)                                            //~v@@@I~
    	{                                                          //~v@@@I~
		case URO_STARTGAME:                                        //~v@@@I~
			init2();                                               //~v@@@I~
			URunnable.setRunFuncDirect(this,this/*parmObj*/,URO_ADDVIEW);//~v@@@I~
            break;                                                 //~v@@@I~
		case URO_ADDVIEW:                                          //~v@@@I~
			addGCView();                                             //~v@@@I~
        	sendMsg(GCM_SETUP,null);                               //~v@@@R~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//*************************                                        //~v@@@I~
	public void addGCView()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if(Dump.Y) Dump.println("GC.addGCView");                   //~v@@@I~
		init3();                                                   //~v@@@I~
//        saveFrameLayoutSize();                                     //~v@@@I~//~9412R~
    }                                                              //~v@@@I~
//*************************                                        //~v@@@I~
	private void init2()                                           //~v@@@R~
    {                                                              //~1120I~//~1122M~
        if (Dump.Y) Dump.println("GC.init2 swTrainingMode="+AG.swTrainingMode);                       //~v@@@I~//~vaa2R~
        marginLR=0;                                                //~vaefI~
        if (Build.VERSION.SDK_INT>=30)   //for gesture navigationbar//~vaefI~
        {                                                          //~vaefI~
        	if (!swPortrait)	//landscape                            //~vaefR~
            {                                                      //~vaefI~
                if (true)   //TODO test                            //~vaefI~
                    marginLR=AG.scrNavigationbarRightWidthA11;     //~vaefI~
                else                                               //~vaefI~
                    marginLR=AG.scrNavigationbarRightWidthA11+AG.scrNavigationbarLeftWidthA11;//~vaefI~
            }                                                      //~vaefI~
            if (Dump.Y) Dump.println("GC.init2 portrait="+AG.portrait+",marginLR="+marginLR+",scrNavigationbarRightWidthA11="+AG.scrNavigationbarRightWidthA11+",scrNavigationbarLeftWidthA11="+AG.scrNavigationbarLeftWidthA11);//~vaefI~
        }                                                          //~vaefI~
        try                                                        //~1109I~//~1120M~//~1122M~
        {                                                          //~1109I~//~1120M~//~1122M~
        	gameView=new GameView(AG.context);                     //~v@@@R~
        	if(Dump.Y) Dump.println("GC.init2 gameView="+gameView.toString());//~v@@@I~
        }                                                          //~1109I~//~1120M~//~1122M~//~v@@@R~
        catch(Exception e)                                         //~1109I~//~1120M~//~1122M~
        {                                                          //~1109I~//~1120M~//~1122M~
    		Dump.println(e,"GC.init2");//~1109I~//~1120M~//~1122M~//~1329R~//~@@@@R~//~v@@@R~
        }                                                          //~1109I~//~1120M~//~1122M~
        if(Dump.Y) Dump.println("GC.init2 end swTrainingMode="+AG.swTrainingMode);                   //~v@@@I~//~vaa2R~
    }                                                              //~1120I~//~1122M~
//*************************                                        //~v@@@I~
	private void init3()                                           //~v@@@I~
    {                                                              //~v@@@I~
        if(Dump.Y) Dump.println("GC.init3 swTrainingMode="+AG.swTrainingMode);                       //~v@@@I~//~vaa2R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
//          int lp=ViewGroup.LayoutParams.FILL_PARENT;             //~v@@@I~//~va40R~
            int lp=ViewGroup.LayoutParams.MATCH_PARENT;            //~va40I~
            if (Dump.Y) Dump.println("GC.init3 addView gameView="+gameView.toString());//~v@@@R~
            frame.addView(gameView,lp,lp);                         //~v@@@I~
            AG.swMainView=false;                                   //~9815I~
//            AG.aGameView=gameView;                               //~v@@@I~
    		addButton();    //should be after addView(gameView) to show over gameView//~v@@@I~
//            new Players();                                       //~v@@@I~
//            new Tiles();                                         //~v@@@I~
//            new Complete();                                      //~v@@@I~
              (new UserAction()).init();                           //~v@@@R~
            swGameView=true;                                       //~9B06I~
//          btnBackgroundColor=Uview.getBackgroundColor(btnPon);   //~9B25R~
//          btnBackgroundColor=btnPon.getBackground();          //~9B25I~//~vaafR~
//          btnTextColor=btnPon.getCurrentTextColor();             //~vaa2R~//~vaafR~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
    		Dump.println(e,"GC.init3");                            //~v@@@I~
        }                                                          //~v@@@I~
        if(Dump.Y) Dump.println("GC.init3 end");                   //~v@@@I~
    }                                                              //~v@@@I~
//*************************                                        //~v@@@I~
	public void addButton()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if(Dump.Y) Dump.println("GC.addButton swPortrait="+swPortrait);//~v@@@I~
		int wc=ViewGroup.LayoutParams.WRAP_CONTENT;                //~v@@@M~
		int mp=ViewGroup.LayoutParams.MATCH_PARENT;                //~v@@@I~
        FrameLayout.LayoutParams lp;                               //~v@@@I~
                                                                   //~v@@@I~
        if (swPortrait)                                            //~v@@@I~
        {                                                          //~v@@@I~
            lp=new FrameLayout.LayoutParams(mp,wc);                //~v@@@I~//~9630R~
//          btns1=AG.inflater.inflate(BUTTONS_TOP,null);            //~v@@@I~//~9808R~
//          btns1=AG.inflater.inflate(AG.swSmallDevice?BUTTONS_TOP_SMALL:BUTTONS_TOP,null);//~9808I~//~vad0R~
            btns1=AG.inflater.inflate(AG.swSmallFont ? BUTTONS_TOP_SMALLFONT : (AG.swSmallDevice ? BUTTONS_TOP_SMALL : BUTTONS_TOP),null);//~vad0I~
            lp.gravity=Gravity.TOP|Gravity.LEFT;                   //~v@@@R~
            btns1.setLayoutParams(lp);                             //~v@@@R~
            frame.addView(btns1);                                  //~v@@@R~
                                                                   //~v@@@I~
            lp=new FrameLayout.LayoutParams(mp,wc);                //~v@@@I~//~9630R~
//          btns2=AG.inflater.inflate(BUTTONS_BOTTOM,null);        //~v@@@R~//~9808R~
//          btns2=AG.inflater.inflate(AG.swSmallDevice?BUTTONS_BOTTOM_SMALL:BUTTONS_BOTTOM,null);//~9808I~//~vad0R~
          if (swLeftyPortrait)                                     //~vad1I~
            btns2=AG.inflater.inflate(AG.swSmallFont ? BUTTONS_BOTTOM_SMALLFONT_REV : (AG.swSmallDevice ? BUTTONS_BOTTOM_SMALL_REV : BUTTONS_BOTTOM_REV),null);//~vad1I~
          else                                                     //~vad1I~
            btns2=AG.inflater.inflate(AG.swSmallFont ? BUTTONS_BOTTOM_SMALLFONT : (AG.swSmallDevice ? BUTTONS_BOTTOM_SMALL : BUTTONS_BOTTOM),null);//~vad0I~
            lp.gravity=Gravity.BOTTOM|Gravity.CENTER;    //=layout_gravity//~v@@@R~
//        //TODO test                                              //~vacfR~
//            Button b=(Button)UView.findViewById(btns2,R.id.UserAction_Ron);//TODO test//~vacfR~
//            if (Dump.Y) Dump.println("GC.addButton portrait getlayoutParams.Height="+b.getLayoutParams().height);//~vacfR~
//            ViewGroup.MarginLayoutParams mlp=(ViewGroup.MarginLayoutParams)b.getLayoutParams();//~vacfR~
//            if (Dump.Y) Dump.println("GC.addButton portrait bottom margin lrtb="+mlp.leftMargin+","+mlp.rightMargin+","+mlp.topMargin+","+mlp.bottomMargin);//~vacfR~
//            LinearLayout ll=(LinearLayout)UView.findViewById(btns2,R.id.GameViewButtons2);//~vacfR~
//            if (Dump.Y) Dump.println("GC.addButton portrait bottom btns="+Utils.toString(ll));//~vacfR~
//            if (Dump.Y) Dump.println("GC.addButton portrait bottom getlayoutParams="+Utils.toString(ll.getLayoutParams()));//~vacfR~
//        //TODO test                                              //~vacfR~
	    	if (Build.VERSION.SDK_INT==29)   //for gesture navigationbar//~vaeeI~
      	  	  if (AG.swNewA10)                                         //~vaeeR~
        		lp.setMargins(0/*left*/,0/*top*/,0/*right*/,AG.scrNavigationbarBottomHeight);//~vaeeI~
            btns2.setLayoutParams(lp);                             //~v@@@R~
            frame.addView(btns2);                                  //~v@@@R~
//          btns2.setBackgroundDrawable(null);                     //~v@@@R~
			if (Dump.Y) Dump.println("GC.addButton portrait bottom buttons MesuredHeight="+btns2.getMeasuredHeight()+",layoutHeight="+btns2.getHeight());//~vacfI~
			if (Dump.Y) Dump.println("GC.addButton portrait scrNavigationbarBottomHeight="+AG.scrNavigationbarBottomHeight);//~vaefI~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//          lp=new FrameLayout.LayoutParams(wc,mp);                //~v@@@M~//~9807R~//~vaefR~
            int hh2=mp;                                            //~vaefI~
//      	if (Build.VERSION.SDK_INT>=30)   //for gesture navigationbar//~vaefI~
//          	hh2=AG.scrHeightReal-AG.scrNavigationPillBottomHeightA11;//~vaefI~
            lp=new FrameLayout.LayoutParams(wc,hh2);               //~vaefI~
//          btns1=AG.inflater.inflate(BUTTONS_LEFT,null);          //~v@@@I~//~9808R~
//          btns1=AG.inflater.inflate(AG.swSmallDevice?BUTTONS_LEFT_SMALL:BUTTONS_LEFT,null);//~9808I~//~vad0R~
            btns1=AG.inflater.inflate(AG.swSmallFont ? BUTTONS_LEFT_SMALLFONT : (AG.swSmallDevice ? BUTTONS_LEFT_SMALL:BUTTONS_LEFT),null);//~vad0I~
            if (AG.aRule.swLeftButtons)	//gambutton to left edge   //~v@@@M~
            	lp.gravity=Gravity.RIGHT;    //=layout_gravity     //~v@@@M~
            else                                                   //~v@@@M~
            	lp.gravity=Gravity.LEFT;    //=layout_gravity      //~v@@@M~
//      	if (Build.VERSION.SDK_INT>=30)   //for gesture navigationbar//~vaefI~
//          	lp.gravity|=Gravity.CENTER_VERTICAL;    //=layout_gravity//~vaefI~
//          	lp.gravity|=Gravity.TOP;                           //~vaefI~
//            if (Build.VERSION.SDK_INT>=30)   //for gesture navigationbar//~vaefR~
//            {                                                    //~vaefR~
//                lp.setMargins(AG.scrNavigationbarLeftWidthA11/*left*/,0/*top*/,0/*right*/,0/*bottom*/);//~vaefR~
//                if (Dump.Y) Dump.println("GC.addButton landscape layout after setMargin w="+lp.width+",h="+lp.height+"scrnavigationbarLeftWidthA11="+AG.scrNavigationbarLeftWidthA11);//~vaefR~
//            }                                                    //~vaefR~
            btns1.setLayoutParams(lp);                             //~v@@@I~
			if (Dump.Y) Dump.println("GC.addButton landscape layout after setMargin btn1 w="+btns1.getWidth()+",h="+btns1.getHeight());//~vaefI~//~vakrR~
            frame.addView(btns1);                                  //~v@@@I~
                                                                   //~v@@@I~
//          lp=new FrameLayout.LayoutParams(wc,mp);                //~v@@@R~//~vaefR~
            lp=new FrameLayout.LayoutParams(wc,hh2);               //~vaefI~
//          btns2=AG.inflater.inflate(BUTTONS_RIGHT,null);         //~v@@@R~//~9808R~
//          btns2=AG.inflater.inflate(AG.swSmallDevice?BUTTONS_RIGHT_SMALL:BUTTONS_RIGHT,null);//~9808I~//~vad0R~
            btns2=AG.inflater.inflate(AG.swSmallFont ? BUTTONS_RIGHT_SMALLFONT : (AG.swSmallDevice ? BUTTONS_RIGHT_SMALL : BUTTONS_RIGHT),null);//~vad0I~
            if (AG.aRule.swLeftButtons)	//gambutton to left edge	   //~v@@@I~
            	lp.gravity=Gravity.LEFT|Gravity.CENTER;   //=layout_gravity//~v@@@I~
            else                                                   //~v@@@I~
            	lp.gravity=Gravity.RIGHT|Gravity.CENTER;   //=layout_gravity//~v@@@I~
//      	if (Build.VERSION.SDK_INT>=30)   //for gesture navigationbar//~vaefI~
//          	lp.gravity|=Gravity.CENTER_VERTICAL;    //=layout_gravity//~vaefI~
//          	lp.gravity|=Gravity.TOP;                           //~vaefI~
            if (Build.VERSION.SDK_INT>=30)   //for gesture navigationbar//~vaefR~
            {                                                      //~vaefR~
                lp.setMargins(0/*left*/,0/*top*/,marginLR/*right*/,0/*bottom*/);//~vaefI~
                if (Dump.Y) Dump.println("GC.addButton landscape layout btn2 after setMargin w="+lp.width+",h="+lp.height+",scrNavigationbarRightWidthA11="+AG.scrNavigationbarRightWidthA11+",scrNavigationbarLeftWidthA11="+AG.scrNavigationbarLeftWidthA11);//~vaefR~//~vakrR~
            }                                                      //~vaefR~
            btns2.setLayoutParams(lp);                             //~v@@@R~
			if (Dump.Y) Dump.println("GC.addButton landscape layout btn2 after setMarginRight marginLR="+marginLR+",btn w="+btns2.getWidth()+",h="+btns2.getHeight());//~vaefR~//~vakrR~
            frame.addView(btns2);                                   //~v@@@R~
//          btnLeftW=btns1.getMeasuredWidth();                     //~9808I~
//          btnRightW=btns2.getMeasuredWidth();                    //~9808I~
			if (Dump.Y) Dump.println("GC.addButton landscape scrNavigationbarBottomHeight="+AG.scrNavigationbarBottomHeightA11+",right="+AG.scrNavigationbarRightWidthA11+",left="+AG.scrNavigationbarLeftWidthA11);//~vaefR~
                                                                   //~v@@@I~
        }                                                          //~v@@@I~
    Button b1=                                                     //~9B20I~
        UButton.bind(btns1,BTNID_F1,this);   //TODO                //~v@@@R~
//      if (!AG.isDebuggable)                                      //~0123I~//~vaahR~
//      {                                                          //~vaahR~
//        	b1.setVisibility(View.GONE);                           //~0123I~//~vaahR~
//      }                                                          //~vaahR~
//    if (!AG.aAccounts.isServer())                                //~9B20I~//~9B23R~
//      b1.setEnabled(false);                                      //~9B20I~//~9B23R~
	    showGameType(b1);                                          //~vaahI~
                                                                   //~9B20I~
    Button b2=                                                     //~0123I~
        UButton.bind(btns1,BTNID_F2,this);                         //~v@@@I~
        if (!AG.isDebuggable)                                      //~0123I~
        	b2.setVisibility(View.GONE);                           //~0123R~
        else                                                       //~0206I~
        {                                                          //~0206I~
        	if ((TestOption.option2 & TestOption.TO2_SHOWF2)==0)   //~0206I~
	        	b2.setVisibility(View.GONE);                       //~0206I~
        }                                                          //~0206I~
    Button b3=                                                     //~0316I~
        UButton.bind(btns1,BTNID_F3,this);                         //~v@@@I~//~0316M~
        if (!AG.isDebuggable)                                      //~0316I~
        	b3.setVisibility(View.GONE);                           //~0316I~
        else                                                       //~0316I~
        {                                                          //~0316I~
	    	if ((TestOption.option2 & TestOption.TO2_LAYOUT_FINALGAME)!=0) //TODO TEST//~0316I~
            {                                                      //~0316I~
	        	b3.setVisibility(View.VISIBLE);                    //~0316I~
            }                                                      //~0316I~
        }                                                          //~0316I~
        UButton.bind(btns1,BTNID_F4,this);                         //~v@@@I~
        UButton.bind(btns1,BTNID_F5,this);                         //~v@@@I~
    Button b6=                                                     //~v@@@I~
        UButton.bind(btns1,BTNID_F6,this);                         //~v@@@I~
        b6.setVisibility(View.GONE);                               //~v@@@I~
//    Button btnWaitOn=                                              //~9629I~//~9B15R~
//        UButton.bind(btns1,BTNID_WAITON,this);                     //~v@@@I~//~9B15R~
//    Button btnWaitOff=                                             //~9629I~//~9B15R~
//        UButton.bind(btns1,BTNID_WAITOFF,this);                    //~v@@@I~//~9B15R~
//    if (!RuleSettingOperation.isRuleWait())                        //~9629I~//~9B15R~
//    {                                                              //~9629I~//~9B15R~
//        btnWaitOn.setVisibility(View.GONE);                        //~9629I~//~9B15R~
//        btnWaitOff.setVisibility(View.GONE);                       //~9629I~//~9B15R~
//    }                                                              //~9629I~//~9B15R~
    btnCompReq=                                                    //~va49I~
        UButton.bind(btns1,BTNID_DLGCOMP,this);                    //~v@@@I~
        UButton.bind(btns1,BTNID_DLGCOMPR,this);                   //~v@@@I~
//  Button btnReachOpen=                                           //~9301I~//~9A30R~
    btnReachOpen=                                                  //~9A30I~
        UButton.bind(btns1,BTNID_REACH_OPEN,this);                 //~9301I~
//      if (RuleSettingYaku.isAvailableOpenReach())                   //~9301I~//~9427R~//~9619R~//~9A31R~//~0329R~
        if (isAvailableOpenReach())                                //~0329I~
            swOpenReach=true;                                      //~9A31I~
        else                                                       //~9A31I~
        	btnReachOpen.setVisibility(View.GONE);                 //~9301I~//~9427R~//~9619R~
        UButton.bind(btns1,BTNID_DRAWNGAME,this);                  //~9302I~
        UButton.bind(btns1,BTNID_MINUSSTOP,this);                  //~9322I~
        UButton.bind(btns1,BTNID_ENDSCORE,this);                   //~9402I~
        UButton.bind(btns1,BTNID_MENUINGAME,this);                 //~9406I~
        UButton.bind(btns1,BTNID_SHOWRULE,this);                   //~9408I~
    btnReach=                                                      //~9A30I~
        UButton.bind(btns1,BTNID_REACH,this);                       //~v@@@I~//~9618I~
    btnReachReset=UButton.bind(btns1,BTNID_REACH_RESET,this);      //~9A30I~
    btnReachOpenReset=UButton.bind(btns1,BTNID_REACH_OPEN_RESET,this);//~9A30I~
    btnForceReach=UButton.bind(btns1,BTNID_FORCE_REACH,this);      //~va27I~
                                                                   //~v@@@I~
    btnTake=                                                       //~9701I~
        UButton.bind(btns2,BTNID_TAKE,this);                        //~v@@@I~
	if (PrefSetting.isNoTakeButton())                              //~9630R~
	    btnTake.setVisibility(View.GONE);                         //~9630I~
                                                                   //~9630I~
	btnPon=                                                        //~9B25I~
        UButton.bind(btns2,BTNID_PON,this);                         //~v@@@I~
	btnChii=                                                       //~9B25I~
        UButton.bind(btns2,BTNID_CHII,this);                        //~v@@@I~
	btnKan=                                                        //~9B25I~
        UButton.bind(btns2,BTNID_KAN,this);                         //~v@@@I~
	btnRon=                                                        //~9B25I~
        UButton.bind(btns2,BTNID_RON,this);                         //~v@@@I~
    btnDiscard=                                             //~9630I~//~9701R~
        UButton.bind(btns2,BTNID_DISCARD,this);                     //~v@@@I~
    btnAnyway=                                                     //~va06I~
        UButton.bind(btns1,BTNID_ANYWAY,this);                     //~va06R~
    btnActionCancel=UButton.bind(btns2,BTNID_ACTION_CANCEL,this);  //~9B25I~
	if (PrefSetting.isNoDiscardButton())                           //~9630R~
	    btnDiscard.setVisibility(View.GONE);                      //~9630I~
//  if (PrefSetting.isNoAnywayButton())                            //~va06I~//~va86R~
    if (RuleSettingYaku.isShowAnywayButton())                      //~va86R~
	    btnAnyway.setVisibility(View.VISIBLE);                     //~va86I~
    else                                                           //~va86I~
	    btnAnyway.setVisibility(View.GONE);                        //~va06I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9701I~
    public void prefSettingChanged()                               //~9701I~
	{                                                              //~9701I~
        if(Dump.Y) Dump.println("GC.prefSettingChanged");          //~9701I~
		if (PrefSetting.isNoTakeButton())                          //~9701I~
	    	btnTake.setVisibility(View.GONE);                      //~9701I~
        else                                                       //~9701I~
	    	btnTake.setVisibility(View.VISIBLE);                   //~9701I~
		if (PrefSetting.isNoDiscardButton())                       //~9701I~
	    	btnDiscard.setVisibility(View.GONE);                   //~9701I~
        else                                                       //~9701I~
	    	btnDiscard.setVisibility(View.VISIBLE);                //~9701I~
//  	if (PrefSetting.isNoAnywayButton())                        //~va06I~//~va86R~
//      	btnAnyway.setVisibility(View.GONE);                    //~va06I~//~va86R~
//      else                                                       //~va06I~//~va86R~
//      	btnAnyway.setVisibility(View.VISIBLE);                 //~va06I~//~va86R~
    }                                                              //~9701I~
//    //*************************************************************************//~v@@@I~//~9A15R~
//    public void invalidate()                                       //~v@@@I~//~9A15R~
//    {                                                              //~v@@@I~//~9A15R~
//        if(Dump.Y) Dump.println("GC.invalidate");                  //~v@@@I~//~9A15R~
//        if (gameView==null)                                        //~v@@@I~//~9A15R~
//            return;                                                //~v@@@I~//~9A15R~
//        if(Dump.Y) Dump.println("GC.invalidate gameView="+gameView.toString());//~v@@@I~//~9A15R~
//        gameView.invalidate();                                     //~v@@@I~//~9A15R~
//    }                                                              //~v@@@I~//~9A15R~
	//*************************************************************************//~v@@@I~
    public void onDestroy()                                        //~v@@@I~
	{                                                              //~v@@@I~
        if(Dump.Y) Dump.println("GC.onDestroy");                   //~v@@@I~
        if (gameView==null)                                        //~v@@@I~
        	return;                                                //~v@@@I~
        gameView.onDestroy();                                      //~v@@@I~
    }                                                              //~v@@@I~
    public void onPause()                                          //~v@@@I~
    {                                                              //~v@@@I~
        if(Dump.Y) Dump.println("GC.onPause");                     //~v@@@I~
        if (gameView==null)                                        //~v@@@I~
        	return;                                                //~v@@@I~
        gameView.onPause();                                        //~v@@@I~
    }                                                              //~v@@@I~
    public void onResume()                                         //~v@@@I~
    {                                                              //~v@@@I~
        if(Dump.Y) Dump.println("GC.onResume");                    //~v@@@I~
        if (gameView==null)                                        //~v@@@I~
        	return;                                                //~v@@@I~
        gameView.onResume();                                       //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public void sendMsg(int Pmsgid,String Pmsg)                   //~v@@@R~//~9622R~//~9B16R~
    {                                                              //~v@@@I~
        if(Dump.Y) Dump.println("GC.sendMsg msgid="+Pmsgid);       //~v@@@R~
        GameViewHandler.sendMsg(Pmsgid,Pmsg);                      //~v@@@R~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public void sendMsg(int Pmsgid,int Pparm)                     //~v@@@I~//~9622R~
    {                                                              //~v@@@I~
        if(Dump.Y) Dump.println("GC.sendMsg msgid="+Pmsgid+",parm="+Pparm);//~v@@@I~
        GameViewHandler.sendMsg(Pmsgid,Pparm,0,0);                 //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~9B16I~
    public void sendMsg(int Pmsgid,int Pparm1,int Pparm2)          //~9B16I~
    {                                                              //~9B16I~
        if(Dump.Y) Dump.println("GC.sendMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2);//~9B16I~
        GameViewHandler.sendMsg(Pmsgid,Pparm1,Pparm2,0);           //~9B16I~
    }                                                              //~9B16I~
	//*************************************************************************//~v@@@I~
    @Override                //UButtonI                            //~v@@@I~
    public void onClickButton(Button Pbtn)                         //~v@@@I~
    {                                                              //~v@@@I~
    	int player;                                                //~v@@@I~
    	int id=Pbtn.getId();                                       //~v@@@I~
        if (Dump.Y) Dump.println("GC.onClickButton id="+Integer.toHexString(id)+"="+Pbtn.getText()+",swWinAnywayActive="+swWinAnywayActive+",swWinAnywayPushed="+swWinAnywayPushed+",swWinAnywayMore="+swWinAnywayMore);//~v@@@R~//~9B18R~//~vacbR~//~vaq8R~
        if (id==BTNID_RON)                                         //~vacbI~
        {                                                          //~vacbI~
	        if (swWinAnywayActive)                                 //~vacbI~
            	id=BTNID_ANYWAY;                                   //~vacbI~
        }                                                          //~vacbI~
        switch(id)                                                 //~v@@@I~
        {                                                          //~v@@@I~
        case BTNID_F1:         //TODO                              //~v@@@R~
		    resetPendingPlayMatchNotify(0/*PmsgID*/);              //~vaa2I~
        	if ((TestOption.option & TestOption.TO_HIDE_BUTTON)!=0)//~v@@@R~
        		hideButtons();                                     //~v@@@I~
            else                                                   //~v@@@I~
        	if ((TestOption.option & TestOption.TO_ENDGAME)!=0)    //~v@@@R~
            	endGameReturnTest();                                         //~v@@@I~//~9B21R~
            else                                                   //~v@@@I~
        		sendMsg(GCM_TEST,null);                            //~v@@@R~
            break;                                                 //~v@@@I~
        case BTNID_F2:                 //TODO TEST                 //~v@@@R~//~9B03R~
//        	sendMsg(GCM_INIT,null);                                //~v@@@R~//~9504R~
//        	nextGameTest();                                        //~9504I~//~9A26R~
        	if (Dump.Y) Dump.println("F2:disable bluetooth");      //~9A26I~
            if (AG.activeSessionType==AST_WD)                      //~9B03I~
                TestOption.throwIOEWD();                             //~9B03I~//~9B05R~
            else                                                   //~9B03I~
//	          	AG.aBTI.mBTC.startBTActivity(BTControl.BTA_DISABLE);   //~9A26I~//~9B03R~//~vacaR~
                TestOption.throwIOEBT();                           //~vacaI~
            break;                                                 //~v@@@I~
        case BTNID_F3:                                             //~v@@@R~
//      	sendMsg(GCM_DICE,null);                                //~v@@@R~//~0316R~
            TestLayout.newInstance().show();                       //~0316I~
            break;                                                 //~v@@@I~
        case BTNID_F4:                                             //~v@@@I~
        	sendMsg(GCM_DEAL,null);   //BTN F5                             //~v@@@R~//~0309R~
            break;                                                 //~v@@@I~
        case BTNID_F5:                                             //~v@@@I~
    		showMenuDialog();                                      //~v@@@I~
            break;                                                 //~v@@@I~
        case BTNID_F6:                                             //~v@@@I~
        	sendMsg(GCM_SETUP,null);                               //~v@@@I~
            break;                                                 //~v@@@I~
        case BTNID_TAKE:                                           //~v@@@I~
			if (isRestarting())                                    //~9A29I~
            	break;                                             //~9A29I~
//          player=Players.nextPlayer(AG.aPlayers.getCurrentPlayer());//~v@@@R~
//          player=PLAYER_YOU;                                     //~v@@@R~
//      	sendMsg(GCM_TAKE,player);                              //~v@@@R~
		    resetPendingPlayMatchNotify(0/*PmsgID*/);              //~vaa2I~
    		if (!resetPendingPlayAloneNotifyTake())                //~va75R~
            	break;                                             //~va75I~
        	sendMsg(GCM_TAKE,null);                                //~v@@@I~
            break;                                                 //~v@@@I~
        case BTNID_PON:                                            //~v@@@I~
			if (isRestarting())                                    //~9A29I~
            	break;                                             //~9A29I~
		    resetPendingPlayMatchNotify(0/*PmsgID*/);              //~vaa2I~
//  		resetPendingPlayAloneNotify(GCM_PON);                  //~va70R~//~vaahR~
    		if (!resetPendingPlayAloneNotify(GCM_PON))	//have to reset Cancel//~vaahR~
            	break;                                             //~vaahR~
        	sendMsg(GCM_PON,null);                                 //~v@@@R~
            break;                                                 //~v@@@I~
        case BTNID_CHII:                                           //~v@@@I~
			if (isRestarting())                                    //~9A29I~
            	break;                                             //~9A29I~
		    resetPendingPlayMatchNotify(0/*PmsgID*/);              //~vaa2I~
//  		resetPendingPlayAloneNotify(GCM_CHII);                 //~va70R~//~vaahR~
    		if (!resetPendingPlayAloneNotify(GCM_CHII))	//have to reset Cancel//~vaahR~
            	break;                                             //~vaahR~
        	sendMsg(GCM_CHII,null);                                //~v@@@R~
            break;                                                 //~v@@@I~
        case BTNID_KAN:                                            //~v@@@I~
			if (isRestarting())                                    //~9A29I~
            	break;                                             //~9A29I~
		    resetPendingPlayMatchNotify(0/*PmsgID*/);              //~vaa2I~
//  		resetPendingPlayAloneNotify(GCM_KAN);                  //~va70R~//~vaahR~
    		if (!resetPendingPlayAloneNotify(GCM_KAN))	//have to reset Cancel//~vaahR~
            	break;                                             //~vaahR~
        	sendMsg(GCM_KAN,null);                                 //~v@@@R~
            break;                                                 //~v@@@I~
        case BTNID_REACH:                                           //~v@@@I~
			if (isRestarting())                                    //~9A29I~
            	break;                                             //~9A29I~
		    resetPendingPlayMatchNotify(0/*PmsgID*/);              //~vaa2I~
//          updateButtonStatusReach(id);                           //~9A30R~//~9A31R~
        	sendMsg(GCM_REACH,null);                               //~v@@@R~//~9301R~
//      	onClickReach();                                        //~v@@@I~//~9301R~
            break;                                                 //~v@@@I~
        case BTNID_REACH_OPEN:                                     //~9301I~
			if (isRestarting())                                    //~9A29I~
            	break;                                             //~9A29I~
		    resetPendingPlayMatchNotify(0/*PmsgID*/);              //~vaa2I~
//          updateButtonStatusReach(id);                           //~9A30R~//~9A31R~
//      	onClickReachOpen();                                    //~9301I~
        	sendMsg(GCM_REACH_OPEN,null);                          //~9301I~
            break;                                                 //~9301I~
        case BTNID_REACH_RESET:                                    //~9A30I~
			if (isRestarting())                                    //~9A30I~
            	break;                                             //~9A30I~
//          updateButtonStatusReach(id);                           //~9A30R~//~9A31R~
        	sendMsg(GCM_REACH_RESET,null);                         //~9A30I~
            break;                                                 //~9A30I~
        case BTNID_REACH_OPEN_RESET:                               //~9A30I~
			if (isRestarting())                                    //~9A30I~
            	break;                                             //~9A30I~
//          updateButtonStatusReach(id);                           //~9A30R~//~9A31R~
        	sendMsg(GCM_REACH_OPEN_RESET,null);                    //~9A30I~
            break;                                                 //~9A30I~
        case BTNID_FORCE_REACH:                                    //~va27I~
			if (isRestarting())                                    //~va27I~
            	break;                                             //~va27I~
		    resetPendingPlayMatchNotify(0/*PmsgID*/);              //~vaa2I~
        	sendMsg(swOpenReachNow ? GCM_FORCE_REACH_OPEN : GCM_FORCE_REACH,null);//~va27I~
            break;                                                 //~va27I~
        case BTNID_RON:                                           //~v@@@I~
			if (isRestarting())                                    //~9A29I~
            	break;                                             //~9A29I~
		    resetPendingPlayMatchNotify(0/*PmsgID*/);              //~vaa2I~
//  		resetPendingPlayAloneNotify(GCM_RON);                  //~va70I~//~vaahR~
    		if (!resetPendingPlayAloneNotify(GCM_RON))	//have to reset Cancel//~vaahR~
            	break;                                             //~vaahR~
        	sendMsg(GCM_RON,null);                                 //~v@@@R~
            break;                                                 //~v@@@I~
        case BTNID_DISCARD:                                           //~v@@@I~
			if (isRestarting())                                    //~9A29I~
            	break;                                             //~9A29I~
//          updateButtonStatusReach(0/*reset*/);                    //~9A30R~//~9A31R~
//          player=AG.aPlayers.getCurrentPlayer();                 //~v@@@R~
		    resetPendingPlayMatchNotify(0/*PmsgID*/);              //~vaa2I~
//  		resetPendingPlayAloneNotify(GCM_DISCARD);              //~va80I~//~vaahR~
    		if (!resetPendingPlayAloneNotify(GCM_DISCARD))	//have to reset Cancel//~vaahR~
            	break;                                             //~vaahR~
        	sendMsg(GCM_DISCARD,PLAYER_YOU);                       //~v@@@R~
            break;                                                 //~v@@@I~
//        case BTNID_WAITON:                                         //~v@@@I~//~0228R~
//            if (isRestarting())                                    //~9A29I~//~0228R~
//                break;                                             //~9A29I~//~0228R~
//            sendMsg(GCM_WAITON,PLAYER_YOU);                        //~v@@@I~//~0228R~
//            break;                                                 //~v@@@I~//~0228R~
//        case BTNID_WAITOFF:                                        //~v@@@I~//~0228R~
//            if (isRestarting())                                    //~9A29I~//~0228R~
//                break;                                             //~9A29I~//~0228R~
//            sendMsg(GCM_WAITOFF,PLAYER_YOU);                       //~v@@@I~//~0228R~
//            break;                                                 //~v@@@I~//~0228R~
        case BTNID_DLGCOMP:                                        //~v@@@I~
        	showDlgComp();                                         //~v@@@I~
            break;                                                 //~v@@@I~
        case BTNID_DLGCOMPR:                                       //~v@@@I~
        	showDlgCompResult();                                   //~v@@@I~
            break;                                                 //~v@@@I~
        case BTNID_DRAWNGAME:                                      //~9302I~
        	showDlgDrawn();                                        //~9302I~
            break;                                                 //~9302I~
        case BTNID_MINUSSTOP:                                      //~9322I~
        	showDlgScore();                                        //~9322I~
            break;                                                 //~9322I~
        case BTNID_ENDSCORE:                                       //~9402I~
        	showDlgEndScore();                                     //~9402I~
            break;                                                 //~9402I~
        case BTNID_MENUINGAME:                                     //~9406I~
//  		if (isRestarting())                                    //~9A29R~
//          	break;                                             //~9A29R~
        	showMenuInGame();                                      //~9406I~
            break;                                                 //~9406I~
        case BTNID_SHOWRULE:                                       //~9408I~
        	showRuleInGame();                                      //~9408I~
            break;                                                 //~9408I~
        case BTNID_ACTION_CANCEL:                                  //~9B25I~
        	actionCancel();                                        //~9B25I~
            break;                                                 //~9B25I~
        case BTNID_ANYWAY:                                         //~va06I~
        	actionAnyway();                                        //~va06I~
            break;                                                 //~va06I~
        default:                                                   //~v@@@I~
        	Alert.showMessage("btn?","Action?");                   //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	private boolean isRestarting()                                 //~9A29I~
    {                                                              //~9A29I~
		return UARestart.isIOExceptionRestarting();                //~9A29I~
    }                                                              //~9A29I~
	//**************************************************************//~v@@@M~
	//*from Handler (For test only,no caller)                      //~va49R~
	//**************************************************************//~v@@@M~
	private boolean diceCasted(Message Pmsg)                        //~v@@@M~
    {                                                              //~v@@@M~
    	boolean rc=true;   //no need call draw                     //~v@@@M~
        int[] intp=GameViewHandler.getMsgIntData(Pmsg);                            //~v@@@M~
        int roll1=intp[0];                                         //~v@@@M~
        int roll2=intp[1];                                         //~v@@@M~
        spotDice=roll1+roll2;                                      //~v@@@I~
        int status=Status.getGameStatus();                         //~v@@@R~
        if (Dump.Y) Dump.println("GC.diceCasted status="+status+",spots="+roll1+","+roll2);//~v@@@I~
        switch(status)                                             //~v@@@M~
        {                                                          //~v@@@M~
        case GS_SETUP:                                             //~v@@@I~
            break;                                                 //~v@@@I~
        case GS_SETUPEND:                                          //~v@@@I~
//	        AG.aACAction.diceCastedTempStarter(roll1,roll2); //TODO      //~v@@@I~
            break;                                                 //~v@@@I~
        case GS_BEFORE_DEAL:                                       //~v@@@M~
        	AG.aGCanvas.drawStock(roll1+roll2);                        //~v@@@M~
			Status.reset();                                        //~v@@@R~
            AG.aTiles.setInitialDeal();                            //~v@@@I~
        	AG.aHands.drawHands(false/*not takeone*/,false/*not intercept*/);                            //~v@@@R~
			Status.setGameStatus(GS_AFTER_DEAL);                       //~v@@@I~
        	sendMsg(GCM_TAKE,null);                                //~v@@@I~
            break;                                                 //~v@@@M~
        }                                                          //~v@@@M~
        return rc;  //true:do not call gCanvas                     //~v@@@M~
    }                                                              //~v@@@M~
	//*************************************************************************//~v@@@I~
    public void showMenuDialog()                                   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GC.showMenuDialog");             //~v@@@I~
        new ActionMenuDlg().show();                                  //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public void userAction(Message Pmsg)                           //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GC.userAction");                 //~v@@@I~
        AG.aUserAction.action(Pmsg);                               //~v@@@R~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static void actionError(int Popt,String Ptext)          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("@@@@ GC.actionError opt="+Popt+",msg="+Ptext);//~v@@@R~
        AG.aGMsg.errorMsg(Popt,Ptext);                                //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static void actionError(int Popt,int Pmsgid)            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("@@@@ GC.actionError opt="+Popt+",msgid=+"+Integer.toHexString(Pmsgid));//~v@@@I~
        AG.aGMsg.errorMsg(Popt,Pmsgid);                            //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static void actionError(int Popt,int Pplayer,int Pmsgid)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("@@@@ GC.actionError opt="+Popt+",player="+Pplayer+",msgid=+"+Integer.toHexString(Pmsgid)+"="+Utils.getStr(Pmsgid));//~v@@@I~//~0304R~//~va60R~
//      actionError(Popt,Pplayer,Utils.getStr(Pmsgid));            //~v@@@R~//~0304R~
        UserAction.sendErr(Popt,Pplayer,Pmsgid);                   //~0304I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static void actionError(int Popt,int Pplayer,String Ptext)  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("@@@@ GC.actionError opt="+Popt+",player="+Pplayer+",msg=+"+Ptext);//~v@@@I~
        UserAction.sendErr(Popt,Pplayer,Ptext);                   //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************************//~v@@@I~
    public static void resetActionError()                          //~v@@@I~
    {                                                              //~v@@@I~
        AG.aGMsg.reset();                                          //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************************************************************//~v@@@M~//~9902R~
//    public void setupEnd()                                         //~v@@@R~//~9902R~
//    {                                                              //~v@@@M~//~9902R~
//                                                                   //~v@@@I~//~9902R~
////        if ((TestOption.option & TestOption.TO_TEST_SETUP)==0)                //~v@@@I~//~9902R~
////          AG.aACAction.setupEnd();                               //~v@@@I~//~9902R~
////        else                                                       //~v@@@I~//~9902R~
////        {                                                          //~v@@@I~//~9902R~
////      AG.aACAction.tempStarter();                                //~v@@@R~//~9902R~
//        int player=ACAction.getStarterPos();                                //~v@@@R~//~9902R~
//        player1stDice=player;                                      //~v@@@R~//~9902R~
//        if (Dump.Y) Dump.println("GC.setup player="+player);       //~v@@@R~//~9902R~
//        AG.aNamePlate.showPlate();                                 //~v@@@R~//~9902R~
//        AG.aDiceBox.setWaitingDice(player);                        //~v@@@R~//~9902R~
//////      AG.aGMsg.drawMsg("仮東を決めます、ランプの場所の人が\nサイコロを振ってください");//~v@@@R~//~9902R~
//        AG.aGMsg.drawMsgbar(R.string.Msg_DiceForTempStarter);      //~v@@@R~//~9902R~
////        }                                                          //~v@@@I~//~9902R~
//    }                                                              //~v@@@M~//~9902R~
//    //*************************************************************************//~v@@@R~
//    public void tempStarter()                                    //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("GC.tempStarter");              //~v@@@R~
//        AG.aACAction.tempStarter();                              //~v@@@R~
//    }                                                            //~v@@@R~
//    //*************************************************************************//~v@@@R~
//    public int getStarterPos()                                   //~v@@@R~
//    {                                                            //~v@@@R~
//        int player=Utils.getRandom(PLAYERS);                     //~v@@@R~
//        if (Dump.Y) Dump.println("GC.getStarterPos player="+player);//~v@@@R~
//        return player;                                           //~v@@@R~
//    }                                                            //~v@@@R~
	//**************************************************************//~v@@@I~
	//*from Handler                                                //~v@@@R~
	//*GCM_REMOTE_DICE is for test only from ActionMenuDlg         //~va49I~
	//**************************************************************//~v@@@I~
	public boolean diceCastedRemote(Message Pmsg)                  //~v@@@I~
    {                                                              //~v@@@I~
    	int pos;                                                   //~v@@@R~
        boolean rc=true;   //no need call draw                     //~v@@@I~
        //********************                                     //~v@@@I~
        int stat=Status.getGameStatus();                           //~v@@@I~
        int[] intp=GameViewHandler.getMsgIntData(Pmsg);            //~v@@@I~
        int player=intp[0];                                        //~v@@@I~
        if (Dump.Y) Dump.println("GC.diceCastedRemote status="+stat+",player="+player+",dice="+spotDice);//~v@@@I~
        if (spotDice==0)                                          //~v@@@I~
        {                                                          //~v@@@I~
        	actionError(0,"throw dice before remoteDice");         //~v@@@I~
            return rc;                                                //~v@@@I~
        }                                                          //~v@@@I~
        int spot=spotDice;                                         //~v@@@I~
        spotDice=0;	//use only once                                //~v@@@I~
        switch(stat)                                               //~v@@@I~
        {                                                          //~v@@@I~
        case GS_SETUP:                                             //~v@@@I~
            pos=Players.playerByDice(spot);                        //~v@@@I~
            playerTempStarter=Players.nextPlayer(player1stDice,pos);//~v@@@R~
        	if (Dump.Y) Dump.println("GC.diceCastedRemote spot="+spot+",pos="+pos+",pl1yser1stDice="+player1stDice+",playerTempStart="+playerTempStarter);//~v@@@R~
            AG.aRiver.setup(playerTempStarter,false);	//show 6 tiles face down;//~v@@@I~
            AG.aStarter.drawStarter(playerTempStarter);            //~v@@@R~
//          DiceBox.enable(true);                                  //~v@@@R~
        	AG.aDiceBox.setWaitingDice(playerTempStarter);         //~v@@@R~
            Status.setGameStatus(GS_POSITIONING);                  //~v@@@I~
        	AG.aGMsg.drawMsgbar(R.string.Msg_DiceForPositioning);  //~v@@@I~
        	break;                                                 //~v@@@I~
        case GS_POSITIONING:                                       //~v@@@I~
            AG.aRiver.setup(playerTempStarter,true);    //show 6 tile face up//~v@@@R~
            int player1stPicker=AG.aRiver.get1stPicker(playerTempStarter,spot);//~v@@@I~
            posMember=AG.aRiver.getPlayerPosition(player1stPicker);   //waiting lamp//~v@@@R~
            setYourPosition(posMember);                            //~v@@@I~
            AG.aDiceBox.setWaitingResponse(player1stPicker);   //waiting lamp//~v@@@R~
//          AG.aDiceBox.setWaitingResponseAll();   //waiting lamp  //~v@@@R~
            Status.setGameStatus(GS_POSITION_ACCEPTING);           //~v@@@I~
            AG.aGMsg.drawMsgbar(R.string.Msg_DiceForPositionAccepting); //TODO test//~v@@@I~//~vaa2R~
//            AG.aNamePlate.show(playerTempStarter,spot);          //~v@@@R~
//            Status.setGameStatus(GS_POSITION_ACCEPTING);         //~v@@@R~
//            AG.aDiceBox.setWaitingResponse();   //wait all players//~v@@@R~
        	break;                                                 //~v@@@I~
        case GS_START_GAME:                                        //~v@@@M~
            Status.setGameStatus(GS_GAME_STARTED);                 //~v@@@M~
            AG.aDiceBox.resetLight(playerStarter);   //new starter take that place//~v@@@R~
            AG.aNamePlate.gameStarted();   //disable name plate    //~v@@@M~
//          AG.aGMsg.drawMsgbar("");                               //~v@@@R~//~9626R~
            AG.aGMsg.reset();                                      //~9626I~
            deal(player,spot);                                     //~v@@@R~
            break;                                                 //~v@@@M~
        }                                                          //~v@@@I~
        return rc;  //true:do not call gCanvas                     //~v@@@I~
                                                                   //~v@@@I~
    }                                                              //~v@@@I~
//    //*******************************************************************//~v@@@I~//~9514R~
//    //*dicebox light touched //TODO moved tp ACATouch              //~v@@@R~//~9514R~
//    //*******************************************************************//~v@@@I~//~9514R~
//    public void touchEvent(int Pplayer)                            //~v@@@I~//~9514R~
//    {                                                              //~v@@@I~//~9514R~
//        int stat=Status.getGameStatus();                           //~v@@@I~//~9514R~
//        int player=Pplayer & ISTOUCH_PLAYER_MASK;                  //~v@@@I~//~9514R~
//        if (Dump.Y) Dump.println("GC.touchEvent status="+stat+",player="+Pplayer);//~v@@@I~//~9514R~
//        switch(stat)                                               //~v@@@I~//~9514R~
//        {                                                          //~v@@@I~//~9514R~
//        case GS_POSITION_ACCEPTING:                                //~v@@@I~//~9514R~
////          AG.aDiceBox.resetWaitingResponse(player);              //~v@@@R~//~9514R~
////          if (Pplayer & ISTOUCH_ALL)   //all accepted            //~v@@@R~//~9514R~
//            if (AG.aRiver.setupAccepted(player))   //show ESWN tile for each,all accepted//~v@@@I~//~9514R~
//            {                                                      //~v@@@I~//~9514R~
//                Status.setGameStatus(GS_POSITION_ACCEPTED);        //~v@@@I~//~9514R~
//                AG.aGMsg.drawMsgbar(R.string.Msg_PositionAcceptedAll);//~v@@@I~//~9514R~
////              AG.aDiceBox.setWaitingResponseAnyone();            //~v@@@R~//~9514R~
//                AG.aDiceBox.setWaitingResponseAll();                //~v@@@I~//~9514R~
//            }                                                      //~v@@@I~//~9514R~
//            else                                                   //~v@@@I~//~9514R~
//            {                                                      //~v@@@I~//~9514R~
//                AG.aDiceBox.setWaitingResponse(Players.nextPlayer(Pplayer));   //waiting lamp//~v@@@R~//~9514R~
//                AG.aGMsg.drawMsgbar(R.string.Msg_DiceForPositionAccepting);//~v@@@I~//~9514R~
//            }                                                      //~v@@@I~//~9514R~
//            break;                                                 //~v@@@I~//~9514R~
//        case GS_POSITION_ACCEPTED:                                 //~v@@@I~//~9514R~
//            if ((Pplayer & ISTOUCH_ALL)!=0)   //all accepted            //~v@@@I~//~9514R~
//            {                                                      //~v@@@I~//~9514R~
//                AG.aRiver.endOfPositioning(); //erase setup 6 tiles//~v@@@I~//~9514R~
//                playerStarter=AG.aNamePlate.setPlayerPosition(playerTempStarter,posMember);//~v@@@I~//~9514R~
//                AG.aAccounts.setPosition(posMember);                //~v@@@I~//~9514R~
//                AG.aGMsg.drawMsgbar(R.string.Msg_DiceForStart1stGame);//~v@@@I~//~9514R~
//                Status.setGameStatusNewSet(playerStarter);        //~v@@@R~//~9514R~
////              AG.aDiceBox.setWaitingAction(playerStarter,true/*set starterID*/);   //waiting lamp//~v@@@R~//~9514R~
//                AG.aDiceBox.setWaitingDice(playerStarter,true/*set starterID*/);   //waiting lamp//~v@@@I~//~9514R~
//                AG.aStarter.moveStarter(playerStarter);            //~v@@@I~//~9514R~
////              DiceBox.enable(true);   //enable dice              //~v@@@R~//~9514R~
//                AG.aStarter.showGameSeq(playerStarter);            //~v@@@I~//~9514R~
//            }                                                      //~v@@@I~//~9514R~
//            break;                                                 //~v@@@I~//~9514R~
////        case GS_COMPLETION_ACCEPTING:                              //~v@@@I~//~9503R~//~9514R~
////            if ((Pplayer & ISTOUCH_ALL)!=0)   //all accepted       //~v@@@I~//~9503R~//~9514R~
////            {                                                      //~v@@@I~//~9503R~//~9514R~
////                int nextStarter=Status.setGameStatusGameComplete();//~v@@@R~//~9503R~//~9514R~
////                AG.aGMsg.drawMsgbar(R.string.Msg_DiceForNewGame);  //~v@@@R~//~9503R~//~9514R~
//////              AG.aDiceBox.setWaitingAction(playerStarter,true/*set starterID*/);   //waiting lamp//~v@@@R~//~9503R~//~9514R~
////                AG.aDiceBox.setWaitingDice(nextStarter,true/*set starterID*/);   //waiting lamp//~v@@@R~//~9503R~//~9514R~
//////              DiceBox.enable(true);   //enable dice              //~v@@@R~//~9503R~//~9514R~
////                AG.aStarter.showGameSeq(playerStarter);            //~v@@@I~//~9503R~//~9514R~
////            }                                                      //~v@@@I~//~9503R~//~9514R~
////            break;                                                 //~v@@@I~//~9503R~//~9514R~
//        }                                                          //~v@@@I~//~9514R~
//    }                                                              //~v@@@I~//~9514R~
    //*******************************************************************//~v@@@I~
    public void setYourPosition(int[] PposMember)                  //~v@@@I~
    {                                                              //~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("GC.setYourPosition ii="+ii+",player="+PposMember[ii]);//~v@@@I~
        	if (PposMember[ii]==0)                                 //~v@@@I~
            {                                                      //~v@@@I~
            	positionYou=ii;                                    //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("GC.setYourPosition ="+positionYou);//~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*tile touched                                                //~v@@@I~
    //*pos 0-13, or 100-113 if swiped                              //~9630I~
    //*******************************************************************//~v@@@I~
    public void touchEventTile(int Ppos)                           //~v@@@I~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("GC.touchEventTile pos="+Ppos);   //~v@@@I~
//      AG.aGMsg.drawMsgbar("");                                   //~v@@@I~//~9626R~
        AG.aGMsg.reset();                                          //~9626I~
        if (Ppos>=ISTOUCH_SWIPED)                                  //~9630I~
        {                                                          //~9630I~
        	int pos=Ppos-ISTOUCH_SWIPED;                           //~9630I~
//          AG.aPlayers.setTileSelected(PLAYER_YOU,pos);           //~9630R~
        	sendMsg(GCM_DISCARD,PLAYER_YOU);                       //~9630I~
        }                                                          //~9630I~
        else                                                       //~9630I~
        {                                                          //~9630I~
        AG.aPlayers.setTileSelected(PLAYER_YOU,Ppos);              //~v@@@R~
//      AG.aPlayers.setTileSelected(Accounts.getPlayerYou(),Ppos); //TODO//~v@@@R~
		}                                                          //~9630I~
    }                                                              //~v@@@I~
//    //*******************************************************************//~v@@@I~//~0305R~
//    private String[] getMemberName()                                //~v@@@I~//~0305R~
//    {                                                              //~v@@@I~//~0305R~
//        if (Dump.Y) Dump.println("GC.getMemberName");              //~v@@@I~//~0305R~
//        String[] mn;                                             //~0305R~
//        if ((TestOption.option & TestOption.TO_TEST_ORI)!=0)    //TODO test//~v@@@I~//~0305R~
//            mn=new String[]{"あいうえお", "かきく","さしすえそた","なにぬねのまみむめもはひふへほ"};//~v@@@R~//~0305R~
//        else                                                       //~v@@@I~//~0305R~
//            mn=AG.aAccounts.getAccountNames();                  //~v@@@I~//~0305R~
//        if (Dump.Y) Dump.println("GC.getMemberName rc="+Arrays.toString(mn));//~0305R~
//        return mn;                                                 //~v@@@I~//~0305R~
//    }                                                              //~v@@@I~//~0305R~
    //*******************************************************************//~v@@@I~
	//*GCM_REMOTE_DICE is for test only from ActionMenuDlg         //~va49I~
    //*******************************************************************//~va49I~
    private void deal(int Pplayer,int Pspot)                        //~v@@@I~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("GC.deal player="+Pplayer+",spot="+Pspot);//~v@@@I~
        AG.aTiles.shuffle();                                       //~v@@@I~
        AG.aGCanvas.drawStock(Pspot);                              //~v@@@I~
        AG.aTiles.setInitialDeal();                                //~v@@@I~
        AG.aHands.drawHands(false/*not takeone*/,false/*not intercept*/);//~v@@@I~
        sendMsg(GCM_TAKE,null);                                    //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    private void hideButtons()                                     //~v@@@R~
    {                                                              //~v@@@I~
        LinearLayout ll;                                           //~v@@@I~
		if (Dump.Y) Dump.println("GC.hiedButton");                 //~v@@@R~
        ll=(LinearLayout)UView.findViewById((View)frame,LAYOUTID_BUTTONS1);//~v@@@R~
        ll.setVisibility(View.GONE);                               //~v@@@I~
        ll=(LinearLayout)UView.findViewById((View)frame,LAYOUTID_BUTTONS2);//~v@@@I~
        ll.setVisibility(View.GONE);                               //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~9812I~
    //*from BTMulti                                                //~9812I~
    //*******************************************************************//~9812I~
    public void disconnectedAtStartGame()                          //~9812I~
    {                                                              //~9812I~
		if (Dump.Y) Dump.println("GC.disconnectedAtStartGame");    //~9812I~
	    endGame(true/*swReturn*/);                                                 //~9812I~//~9B21R~
        MainView.drawMsg(R.string.InfoDisconnectedSomeDevice);     //~9812I~
    }                                                              //~9812I~
//    //*******************************************************************//~9B21R~
//    public void endGame()                                        //~9B21R~
//    {                                                            //~9B21R~
//        endGame(false);                                          //~9B21R~
//    }                                                            //~9B21R~
    //*******************************************************************//~v@@@I~
    public void endGame(boolean PswReturn)                                         //~v@@@I~//~9B20R~//~9B21R~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("GC.endGame swReturn="+PswReturn+",swGameView="+swGameView);                    //~v@@@I~//~9B21R~//~0111R~
        if (!swGameView)	//dup req                              //~0111I~
        	return;                                                //~0111I~
        AG.aAnim.removeAnimation();                                //~vamgR~
      if (PswReturn)                                               //~9B21I~
      {                                                            //~9B21I~
        if (btns1!=null)                                           //~v@@@I~
        {                                                          //~v@@@I~
        	frame.removeView(btns1);                               //~v@@@I~
        }                                                          //~v@@@I~
        if (btns2!=null)                                           //~v@@@I~
        {                                                          //~v@@@I~
        	frame.removeView(btns2);                               //~v@@@I~
        }                                                          //~v@@@I~
        if (gameView!=null)                                        //~v@@@I~
        {                                                          //~v@@@I~
			if (Dump.Y) Dump.println("GC.endGame remove gameView="+gameView.toString());//~v@@@I~
        	frame.removeView(gameView);                            //~v@@@I~
        }                                                          //~v@@@I~
      }                                                            //~9B21I~
//      Graphics.reset();	//recycle bmShadow                     //~v@@@R~//~9319R~
//      AG.aAccounts.endGame();                                     //~v@@@I~//~9B21R~
        AG.aAccounts.endGame(PswReturn);                           //~9B21I~
        Status.endGameAnyway();                                          //~v@@@I~//~9406R~
        new EventCB(ECB_ACTION_ENDGAME).postEvent();               //~v@@@I~
        swGameView=false;                                          //~9B06I~
    }                                                              //~v@@@I~
    //*******************************************************************//~9903I~
    //*from MenuInGame                                             //~9903I~
    //*and back btn when gameover of playalone                     //~va80I~
    //*return true for dismiss menu                                //~9903I~
    //*******************************************************************//~9903I~
    public  boolean endGameReturn()                                //~9903R~
    {                                                              //~9903I~
		if (Dump.Y) Dump.println("GC.endGameReturn");              //~9903I~
        if (Dump.Y) Dump.println("GC.endGameReturn Status.endgameType="+AG.aStatus.endGameType);//~vamgI~
        if (Dump.Y) Dump.println("GC.endGameReturn Status.isIssuedRon()="+AG.aStatus.isIssuedRon());//~vamgI~
        if (Dump.Y) Dump.println("GC.endGameReturn Complete.isTotalAgreed()="+AG.aComplete.isTotalAgreed());//~vamgI~
//  	if (Status.isGamingNow()) //status=21 & ! gameover        //~9903I~//~0410R~//~va02R~
    	if (Status.isGamingNowAndInterRound()) //status=21 & ! gameover//~va02I~
        {                                                          //~9903I~
        	                                                       //~0110I~
		  if (!Status.isGameSuspended())                         //~0110I~
          {                                                        //~0110I~
			UView.showToast(R.string.Err_GamingNow);               //~9903I~
            return false;                                          //~9903I~
          }                                                        //~0110I~
        }                                                          //~9903I~
        else                                                       //~0410I~
    	if (!Status.isGameOver() && !Status.isGameSuspended())     //~0410R~
        {                                                          //~0410I~
			UView.showToast(R.string.Err_GamingNow);               //~0410I~
            return false;                                          //~0410I~
        }                                                          //~0410I~
        endGame(true/*swReturn*/);                                                 //~9903I~//~9B21R~//~9B22R~
        return true;                                               //~9903I~
    }                                                              //~9903I~
    //*******************************************************************//~9B21I~
    public  boolean endGameReturnTest()                            //~9B21I~
    {                                                              //~9B21I~
		if (Dump.Y) Dump.println("GC.endGameReturnTest");          //~9B21I~
        endGame(true);                                             //~9B21I~
        return true;                                               //~9B21I~
    }                                                              //~9B21I~
    //*******************************************************************//~v@@@I~
    private void showDlgComp()                                     //~v@@@I~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("GC.showDlgComp");                //~v@@@I~
//        if (chkComplete(true)==0)                                  //~v@@@I~//~9403R~
//            UView.showToast(R.string.Err_NoneCompleted);           //~v@@@I~//~9403R~
	  if (swCompReqButtonStatus)	//status orange                //~va97I~
	    highlightCompReq(false/*PswOn*/);                           //~va49I~
    	CompReqDlg.showDismissed();                                //~9403I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    private void showDlgCompResult()                               //~v@@@I~
    {                                                              //~v@@@I~
		if (Dump.Y) Dump.println("GC.showDlgCompResult");          //~v@@@I~
//        if (chkComplete(false)==0)                                 //~v@@@I~//~9403R~
//            UView.showToast(R.string.Err_NoneCompleted);           //~v@@@I~//~9403R~
//        else                                                       //~v@@@I~//~9403R~
//        if (!AG.aComplete.chkCompReqReplyAll())                    //~9320R~//~9403R~
//            UView.showToast(R.string.Err_CompReqNotShowableExist); //~9320I~//~9403R~
//        else                                                       //~9320I~//~9403R~
//            CompleteDlg.newInstance().show();                      //~v@@@R~//~9403R~
    	CompleteDlg.showDismissed();                               //~9403I~
    }                                                              //~v@@@I~
    //*******************************************************************//~9302I~
    private void showDlgDrawn()                                    //~9302I~
    {                                                              //~9302I~
		if (Dump.Y) Dump.println("GC.showDlgDdrawn");              //~9302I~
//  	DrawnReqDlg.newInstance().show();                          //~9302I~//~9303R~
    	UAEndGame.showDlgFromMenu();                                       //~9303I~//~9701R~
    }                                                              //~9302I~
//    //*******************************************************************//~v@@@I~//~9403R~
//    private int chkComplete(boolean PswReqDlg)                     //~v@@@I~//~9403R~
//    {                                                              //~v@@@I~//~9403R~
////        Complete.Status ss[]=AG.aComplete.statusS;                 //~v@@@I~//~9402R~//~9403R~
////        int ctr=0;                                                 //~v@@@I~//~9402R~//~9403R~
////        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~//~9402R~//~9403R~
////        {                                                          //~v@@@I~//~9402R~//~9403R~
////            if (ss[ii]==null)                                      //~v@@@I~//~9402R~//~9403R~
////                 continue;                                         //~v@@@I~//~9402R~//~9403R~
////            if (PswReqDlg)                                         //~v@@@I~//~9402R~//~9403R~
////            {                                                      //~v@@@I~//~9402R~//~9403R~
////                if (ss[ii].isShowable())                           //~v@@@I~//~9402R~//~9403R~
////                    CompReqDlg.newInstance(ss[ii]).show();         //~v@@@R~//~9402R~//~9403R~
////                else                                               //~v@@@I~//~9402R~//~9403R~
////                    UView.showToast(R.string.Err_CompReqNotShowable);//~v@@@I~//~9402R~//~9403R~
////            }                                                      //~v@@@I~//~9402R~//~9403R~
////            ctr++;                                                 //~v@@@I~//~9402R~//~9403R~
////        }                                                          //~v@@@I~//~9402R~//~9403R~
//        Complete.Status ss[]=AG.aComplete.sortStatusS();           //~9402I~//~9403R~
//        int ctr=ss.length;                                         //~9402I~//~9403R~
//        if (PswReqDlg)                                             //~9402I~//~9403R~
//            for (int ii=0;ii<ctr;ii++)                             //~9402I~//~9403R~
//            {                                                      //~9402I~//~9403R~
//                if (ss[ii].isShowable())                           //~9402I~//~9403R~
//                    CompReqDlg.newInstance(ss[ii]).show();         //~9402I~//~9403R~
//                else                                               //~9402I~//~9403R~
//                    UView.showToast(R.string.Err_CompReqNotShowable);//~9402I~//~9403R~
//            }                                                      //~9402I~//~9403R~
//        if (Dump.Y) Dump.println("GC.chkComplete ctr="+ctr);       //~v@@@I~//~9403R~
//        return ctr;                                                //~v@@@I~//~9403R~
//    }                                                              //~v@@@I~//~9403R~
    //*******************************************************************//~9322I~
    private void showDlgScore()                                    //~9322I~
    {                                                              //~9322I~
		if (Dump.Y) Dump.println("GC.showDlgScore");               //~9322I~
    	ScoreDlg.showDismissed();                                  //~9322I~
    }                                                              //~9322I~
    //*******************************************************************//~9402I~
    private void showDlgEndScore()                                 //~9402I~//~9406R~
    {                                                              //~9402I~
		if (Dump.Y) Dump.println("GC.showDlgEndScore");            //~9402I~
    	AccountsDlg.showDismissed();                               //~9402I~
    }                                                              //~9402I~
    //*******************************************************************//~9406I~
    private void showMenuInGame()                                  //~9406I~
    {                                                              //~9406I~
		if (Dump.Y) Dump.println("GC.showMenuInGame");             //~9406I~
        MenuInGameDlg.showSettingMenu();                           //~9406R~
    }                                                              //~9406I~
    //*******************************************************************//~9408I~
    private void showRuleInGame()                                  //~9408I~
    {                                                              //~9408I~
		if (Dump.Y) Dump.println("GC.showRuleInGame");             //~9408I~
        RuleSetting.showRuleInGame();                              //~9408R~
    }                                                              //~9408I~
    //*******************************************************************//~9504I~
    private void nextGameTest()                                    //~9504I~
    {                                                              //~9504I~
		if (Dump.Y) Dump.println("GC.nextgameTest");               //~9504I~
        boolean swServer=AG.aAccounts.isServer();                                          //~9504I~
        int endgameType=EGDR_NORMAL;                               //~9504I~
//      int endgameType=EGDR_DRAWN_LAST;                           //~9504I~
//      int endgameType=EGDR_DRAWN_HW;                             //~9504I~
        int nextgameType=NGTP_NEXT;                                 //~9504I~
//      int nextameType=NGTP_CONTINUE;                             //~9504I~
        AG.aAccounts.nextGame(swServer,endgameType,nextgameType);   //~9504I~
    }                                                              //~9504I~
//    //*******************************************************************//~9817I~//~0307R~
//    public void suspendGame()                                      //~9817I~//~0307R~
//    {                                                              //~9817I~//~0307R~
//        if (Dump.Y) Dump.println("GC.suspendGame");                //~9817I~//~0307R~
//        suspendGame(true,-1/*eswn*/);                     //~9817R~//~9818R~//~0307R~
//    }                                                              //~9817I~//~0307R~
//    //*******************************************************************//~9817M~//~0307R~
//    public void suspendGameReset()                                 //~9817M~//~0307R~
//    {                                                              //~9817M~//~0307R~
//        if (Dump.Y) Dump.println("GC.suspendReset");               //~9817M~//~0307R~
//        suspendGame(false,-1/*eswn*/);                    //~9817R~//~9818R~//~0307R~
//    }                                                              //~9817M~//~0307R~
    //*******************************************************************//~9817I~
    public void suspendGame(boolean PswSuspend,int Peswn)//~9817R~ //~9818R~
    {                                                              //~9817I~
        int eswn,rc;                                               //~9818R~
        String msg="";                                                //~9818I~//~0303R~
        int msgid=0;                                               //~0303I~
    //***********************                                      //~9818I~
		if (Dump.Y) Dump.println("GC.suspendGame eswn="+Peswn+",swSuspend="+PswSuspend);//~9817R~//~9818R~
        if (!Status.isGamingNow())                                 //~9817I~
        {                                                          //~9817I~
			UView.showToast(R.string.Err_SuspendNotInGame);        //~9817I~
            return;                                                //~9817I~
        }                                                          //~9817I~
        if (Peswn<0) //Menu action                                               //~9818I~//~9830R~
        	eswn=Accounts.getCurrentEswn();                        //~9818I~
        else                                                       //~9818I~
            eswn=Peswn;                                            //~9818I~
		if (Dump.Y) Dump.println("GC.suspendGame eswn="+eswn);     //~9830I~
		if (AG.aAccounts.isServer())                           //~9430I~//~9817I~
        {                                                          //~9817I~
      		rc=AG.aStatus.suspendGame(PswSuspend,eswn);                 //~9818R~
            if (rc<0)	//dup req                                  //~9818I~
            {                                                      //~9818I~
            	if (PswSuspend)                                    //~9818I~
//              	msg=Utils.getStr(R.string.Info_SuspendRequestedDup);//~9818I~//~0303R~
                	msgid=R.string.Info_SuspendRequestedDup;       //~0303I~
            	else                                               //~9818I~
//              	msg=Utils.getStr(R.string.Info_SuspendCanceledDup);//~9818I~//~0303R~
                	msgid=R.string.Info_SuspendCanceledDup;//~0303I~
            }                                                      //~9818I~
            else                                                   //~9818I~
            {                                                      //~9818I~
            	if (PswSuspend)                                    //~9818I~
                {                                                  //~0303I~
//              	msg=Utils.getStr(R.string.Info_SuspendRequested,AG.aStatus.ctrSuspendRequest);//~9818I~//~0303R~
                	msgid=R.string.Info_SuspendRequested;          //~0303I~
					msg=Integer.toString(AG.aStatus.ctrSuspendRequest);//~0303I~
                }                                                  //~0303I~
            	else                                               //~9818I~
                {                                                  //~0303I~
//              	msg=Utils.getStr(R.string.Info_SuspendCanceled,AG.aStatus.ctrSuspendRequest);//~9818I~//~0303R~
                	msgid=R.string.Info_SuspendCanceled;           //~0303I~
                	msg=Integer.toString(AG.aStatus.ctrSuspendRequest);//~0303I~
                }                                                  //~0303I~
            }                                                      //~9818I~
        	String msgData=eswn+MSG_SEP+(PswSuspend?1:0);          //~9830I~
			AG.aAccounts.sendToClient(false/*swRobot*/,-1/*skip*/,GCM_SUSPENDGAME,msgData);//~9830I~
//  		UserAction.showInfoAllEswnEswn(0,eswn,msg);            //~0303I~
          	if (rc<0)	//dup req                                  //~0303R~
    			UserAction.showInfoAllEswnEswn(0,eswn,msgid);      //~0303R~
          	else                                                   //~0303R~
    			UserAction.showInfoAllEswnEswn(0,-1/*idxSkip*/,eswn,msgid,msg);            //~9818I~//~0303R~
        }                                                          //~9817I~
        else                                                       //~9817I~
        {                                                          //~9817I~
        	if (Peswn<0)   //not received,menu action                          //~9818R~//~9830R~
            {                                                      //~9817I~
        		String msgData=eswn+MSG_SEP+(PswSuspend?1:0);//~9817R~//~9818R~
				AG.aAccounts.sendToServer(GCM_SUSPENDGAME,msgData);                //~v@@@R~//~9817R~
            }                                                      //~9817I~
            else                                                   //~9817I~//~9818R~//~9830R~
        		AG.aStatus.suspendGame(PswSuspend,eswn);                //~9817I~//~9818R~//~9830R~
        }                                                          //~9817I~
    }                                                              //~9817I~
    //*******************************************************************//~9A12I~
    public void suspendGameResetComplete()                         //~9A12I~
    {                                                              //~9A12I~
        int eswn, rc;                                               //~9A12I~
        String msg;                                                //~9A12I~
        //***********************                                      //~9A12I~
        if (Dump.Y) Dump.println("GC.suspendGameResetComplete");   //~9A12I~
//        if (!Status.isGamingNow())                               //~9A12I~
//        {                                                        //~9A12I~
//            UView.showToast(R.string.Err_SuspendNotInGame);      //~9A12I~
//            return;                                              //~9A12I~
//        }                                                        //~9A12I~
//        if (Peswn<0) //Menu action                               //~9A12I~
//            eswn=Accounts.getCurrentEswn();                      //~9A12I~
//        else                                                     //~9A12I~
//            eswn=Peswn;                                          //~9A12I~
//        if (Dump.Y) Dump.println("GC.suspendGame eswn="+eswn);   //~9A12I~
//        if (AG.aAccounts.isServer())                             //~9A12I~
//        {                                                        //~9A12I~
//            rc=AG.aStatus.suspendGame(PswSuspend,eswn);          //~9A12I~
//            if (rc<0)   //dup req                                //~9A12I~
//            {                                                    //~9A12I~
//                if (PswSuspend)                                  //~9A12I~
//                    msg=Utils.getStr(R.string.Info_SuspendRequestedDup);//~9A12I~
//                else                                             //~9A12I~
//                    msg=Utils.getStr(R.string.Info_SuspendCanceledDup);//~9A12I~
//            }                                                    //~9A12I~
//            else                                                 //~9A12I~
//            {                                                    //~9A12I~
//                if (PswSuspend)                                  //~9A12I~
//                    msg=Utils.getStr(R.string.Info_SuspendRequested,AG.aStatus.ctrSuspendRequest);//~9A12I~
//                else                                             //~9A12I~
//                    msg=Utils.getStr(R.string.Info_SuspendCanceled,AG.aStatus.ctrSuspendRequest);//~9A12I~
//            }                                                    //~9A12I~
//            String msgData=eswn+MSG_SEP+(PswSuspend?1:0);        //~9A12I~
//            AG.aAccounts.sendToClient(false/*swRobot*/,-1/*skip*/,GCM_SUSPENDGAME,msgData);//~9A12I~
//            UserAction.showInfoAllEswnEswn(0,eswn,msg);          //~9A12I~
//        }                                                        //~9A12I~
//        else                                                     //~9A12I~
//        {                                                        //~9A12I~
//            if (Peswn<0)   //not received,menu action            //~9A12I~
//            {                                                    //~9A12I~
//                String msgData=eswn+MSG_SEP+(PswSuspend?1:0);    //~9A12I~
//                AG.aAccounts.sendToServer(GCM_SUSPENDGAME,msgData);//~9A12I~
//            }                                                    //~9A12I~
//            else                                                 //~9A12I~
//                AG.aStatus.suspendGame(PswSuspend,eswn);         //~9A12I~
//        }                                                        //~9A12I~
        AG.aStatus.suspendGameResetComplete();
    }//~9A12I~
    //*******************************************************************//~va27I~
    private void calledReach()                                     //~va27I~
    {                                                              //~va27I~
            btnReach.setVisibility(View.GONE);                     //~va27I~
            btnReachReset.setVisibility(View.VISIBLE);             //~va27I~
            if (swOpenReach)                                       //~va27I~
	            btnReachOpen.setEnabled(false);                    //~va27I~
            swBtnReach=true;                                       //~va27I~
            swOpenReachNow=false;                                  //~va27I~
    }                                                              //~va27I~
    //*******************************************************************//~va27I~
    private void calledReachOpen()                                 //~va27I~
    {                                                              //~va27I~
            btnReachOpen.setVisibility(View.GONE);                 //~va27I~
            btnReachOpenReset.setVisibility(View.VISIBLE);         //~va27I~
            btnReach.setEnabled(false);                            //~va27I~
            swBtnReach=true;                                       //~va27I~
            swOpenReachNow=true;                                   //~va27I~
    }                                                              //~va27I~
    //*******************************************************************//~9A30I~
    //*from UserAction after isYourTurn().rc=OK thru MainActivity with EventCB     //~9A30I~//~9A31R~
    //*******************************************************************//~9A30I~
    public void updateButtonStatusReach(int PactionID)                //~9A30R~//~9A31R~
    {                                                              //~9A30I~
        if (Dump.Y) Dump.println("GC.updateButtonStatusReach actionID="+PactionID+",swBtnReach="+swBtnReach+",swOpenReach="+swOpenReach+",swOpenReachNow="+swOpenReachNow);//~9A30R~//~va27R~
    	switch(PactionID)                                             //~9A30R~
        {                                                          //~9A30I~
        case GCM_REACH:     //action id is on GVH thread           //~9A30R~//~9A31R~
//      case BTNID_REACH:                                          //~9A30I~//~9A31R~
//            btnReach.setVisibility(View.GONE);                   //~va27R~
//            btnReachReset.setVisibility(View.VISIBLE);           //~va27R~
//            if (swOpenReach)                                     //~va27R~
//                btnReachOpen.setEnabled(false);                        //~9A30I~//~va27R~
//            swBtnReach=true;                                     //~va27R~
//            swOpenReachNow=false;                                //~va27R~
			calledReach();                                         //~va27I~
            btnForceReach.setVisibility(View.GONE);                //~va27I~
            break;                                                 //~9A30I~
        case GCM_REACH_OPEN:                                       //~9A30R~//~9A31R~
//      case BTNID_REACH_OPEN:                                     //~9A30I~//~9A31R~
//            btnReachOpen.setVisibility(View.GONE);               //~va27R~
//            btnReachOpenReset.setVisibility(View.VISIBLE);       //~va27R~
//            btnReach.setEnabled(false);                          //~va27R~
//            swBtnReach=true;                                     //~va27R~
//            swOpenReachNow=true;                                 //~va27R~
			calledReachOpen();                                     //~va27I~
            btnForceReach.setVisibility(View.GONE);                //~va27I~
            break;                                                 //~9A30I~
        case GCM_REACH_RESET:                                      //~9A30R~//~9A31R~
//      case BTNID_REACH_RESET:                                    //~9A30I~//~9A31R~
            btnReach.setVisibility(View.VISIBLE);                  //~9A30I~
            btnReachReset.setVisibility(View.GONE);                //~9A30I~
            if (swOpenReach)                                       //~9A31I~
    	        btnReachOpen.setEnabled(true);                         //~9A30I~//~9A31R~
            btnForceReach.setVisibility(View.GONE);                //~va27I~
            swBtnReach=false;                                      //~9A30I~
            swOpenReachNow=false;                                  //~va27I~
            break;                                                 //~9A30I~
        case GCM_REACH_OPEN_RESET:                                 //~9A30R~//~9A31R~
//      case BTNID_REACH_OPEN_RESET:                               //~9A30I~//~9A31R~
            btnReachOpen.setVisibility(View.VISIBLE);              //~9A30I~
            btnReachOpenReset.setVisibility(View.GONE);            //~9A30I~
            btnReach.setEnabled(true);                             //~9A30I~
            btnForceReach.setVisibility(View.GONE);                //~va27I~
            swBtnReach=false;                                      //~9A30I~
            swOpenReachNow=false;                                  //~va27I~
            break;                                                 //~9A30I~
        case GCM_FORCE_REACH:                                      //~va27I~
            btnForceReach.setVisibility(View.GONE);                //~va27I~
			calledReach();                                         //~va27I~
            break;                                                 //~va27I~
        case GCM_FORCE_REACH_OPEN:                                 //~va27I~
            btnForceReach.setVisibility(View.GONE);                //~va27I~
			calledReachOpen();                                     //~va27I~
            break;                                                 //~va27I~
        case GCM_FORCE_REACH_ENABLE:                               //~va27I~
            btnForceReach.setVisibility(View.VISIBLE);             //~va27I~
            break;                                                 //~va27I~
        default:    //at Discard                                   //~9A30I~//~9A31R~
            swOpenReachNow=false;                                  //~va27I~
            if (!swBtnReach)                                       //~9A30I~
            	break;                                             //~9A30I~
            btnReach.setVisibility(View.VISIBLE);                  //~9A30I~
            btnReachReset.setVisibility(View.GONE);                //~9A30I~
            btnReach.setEnabled(true);                             //~9A30I~//~9A31M~
            if (swOpenReach)                                       //~9A31I~
            {                                                      //~9A31I~
            	btnReachOpen.setVisibility(View.VISIBLE);              //~9A30I~//~9A31R~
            	btnReachOpenReset.setVisibility(View.GONE);            //~9A30I~//~9A31R~
            	btnReachOpen.setEnabled(true);                         //~9A30I~//~9A31R~
            }                                                      //~9A31I~
            break;                                                 //~9A30I~
        }                                                          //~9A30I~
	}                                                                  //~9A12I~//~9A30R~
    //*******************************************************************//~9B06I~
    public static boolean isOnGameView()                           //~9B06R~
    {                                                              //~9B06I~
    	boolean rc=AG.aGC!=null && AG.aGC.swGameView;              //~9B06I~
        if (Dump.Y) Dump.println("GC.isOnGameView rc="+rc);        //~9B06I~
        return rc;                                                 //~9B06I~
    }                                                              //~9B06I~
    //*******************************************************************//~9B10I~
    private void dismissConnectionDialog()                         //~9B10I~
    {                                                              //~9B10I~
        if (Dump.Y) Dump.println("GC.dismissConnectinDialog");     //~9B10I~
        BTCDialog dlgBT=AG.aBTCDialog;                             //~9B10I~
        if (Utils.isShowingDialogFragment(dlgBT))                  //~9B10I~
        	dlgBT.dismiss();                                       //~9B10I~
        WDA dlgWD=AG.aWDA;                                         //~9B10I~
        if (Utils.isShowingDialogFragment(dlgWD))                  //~9B10I~
        	dlgWD.dismiss();                                       //~9B10I~
    }                                                              //~9B10I~
    //*******************************************************************//~9B25I~
    public void confirmEndGameReturn()                      //~9B25I~//~0411R~
    {                                                              //~9B25I~
        if (Dump.Y) Dump.println("GC.confirmEndGameReturn");       //~9B25I~//~va02R~
        int titleid=R.string.Title_ConfirmEndgame;                 //~9B25I~
        int msgid=R.string.Msg_ConfirmEndgame;                     //~9B25I~
//        swReconnect=false;                                       //~0411R~
		Alert.showAlert(titleid,msgid, BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,AG.aGC);//calback alertButtonAction//~9B25I~
    }                                                              //~9B25I~
    //*******************************************************************//~va02I~
    public void confirmEndGameReturn(int Pmsgid)                   //~va02I~
    {                                                              //~va02I~
        if (Dump.Y) Dump.println("GC.confirmEndGameReturn msgid="+Integer.toHexString(Pmsgid));//~va02R~
        int titleid=R.string.Title_ConfirmEndgame;                 //~va02I~
        int msgid=Pmsgid;                                          //~va02I~
		Alert.showAlert(titleid,msgid, BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,AG.aGC);//calback alertButtonAction//~va02I~
    }                                                              //~va02I~
    //**************************************                       //~9B25I~
    @Override //* AlertI                                           //~9B25I~
	public int alertButtonAction(int Pbuttonid,int Prc)            //~9B25I~
    {                                                              //~9B25I~
    	if (Dump.Y) Dump.println("GC.alertButtonAction puttonid="+Pbuttonid);//~9B25I~
        try                                                        //~9B25I~
        {                                                          //~9B25I~
//            if (swReconnect)                                     //~0411R~
//                alertActionReconnect(Pbuttonid);                 //~0411R~
//            else                                                 //~0411R~
        	if (Pbuttonid==BUTTON_POSITIVE)                        //~9B25I~
            	AG.aGC.endGame(true/*PswReturn*/);                 //~9B25R~
        }                                                          //~9B25I~
        catch (Exception e)                                        //~9B25I~
        {                                                          //~9B25I~
        	Dump.println(e,"GC.alertButtonAction");                //~9B25I~
        }                                                          //~9B25I~
    	return 0;                                                  //~9B25I~
    }                                                              //~9B25I~
    //*******************************************************************//~9B25I~
    private void actionCancel()                                    //~9B25I~
    {                                                              //~9B25I~
		if (Dump.Y) Dump.println("GC.actionCancel swPlayAloneNotify="+AG.swPlayAloneNotify+",swTrainingMode="+AG.swTrainingMode);               //~9B25I~//~va70R~//~va74R~
        int msgid=statusPlayAlone;                                 //~va70R~//~va74M~
        statusPlayAlone=0;   //clear for postDelayedPonKan         //~va70R~//~va74M~
        swCancel=true;                                             //~va75I~
      if (AG.swPlayAloneNotify)                                    //~va70I~
      {                                                            //~va70I~
//      actionPlayAloneNotify(msgid);                              //~va70R~//~va74R~
        actionPlayAlone(msgid,true/*updateBtn*/);                  //~va74R~
        AG.aUAD2Touch.actionCancel();                              //~vaaZR~
      }                                                            //~va70I~
      else                                                         //~va70I~
      {                                                            //~va74I~
        if (AG.swTrainingMode)                                     //~va74I~
	        actionPlayAlone(msgid,false/*updateBtn*/);             //~va74R~
        AG.aUAD2Touch.actionCancel();                           //~9B25R~
      }                                                            //~va74I~
        swCancel=false;                                            //~va75I~
    }                                                              //~9B25I~
    //*******************************************************************//~va06I~
    private void actionAnyway()                                    //~va06I~
    {                                                              //~va06I~
		if (Dump.Y) Dump.println("GC.actionAnyway entry swWinAnywayPushed="+swWinAnywayPushed+",swAnywayActive="+swWinAnywayActive);//~vacbI~
        if (Dump.Y) Dump.println("GC.actionAnyway Status.endgameType="+AG.aStatus.endGameType);//~vamgI~
        if (Dump.Y) Dump.println("GC.actionAnyway Status.isIssuedRon()="+AG.aStatus.isIssuedRon());//~vamgI~
        if (Dump.Y) Dump.println("GC.actionAnyway Complete.isTotalAgreed()="+AG.aComplete.isTotalAgreed());//~vamgI~
    	if (Dump.Y) Dump.println("GC.actionAnyway Status.isGamingNowAndInterRound()="+Status.isGamingNowAndInterRound());//~vamgI~
        swWinAnywayPushed=true;  //activate when Win btn changed to orange//~vacbR~
        swWinAnywayActive=false;                                   //~vacbI~
		if (Dump.Y) Dump.println("GC.actionAnyway exit swWinAnywayPushed="+swWinAnywayPushed+",swAnywayActive="+swWinAnywayActive);               //~va06I~//~vacbR~
        UARon.winAnyway();                                         //~va06I~
    }                                                              //~va06I~
    //*******************************************************************//~9B25I~
    //*from Action2Touch on MainThread                             //~9B25I~
    //*******************************************************************//~9B25I~
    public void updateActionBtn2Touch(int PactionID,int Pstat,int Pcolor)//~9B25I~//~9B26R~
    {                                                              //~9B25I~
		if (Dump.Y) Dump.println("GC.updateActionBtn2Touch actionID="+PactionID+",stat="+Pstat+",color="+Integer.toHexString(Pcolor));//~9B25I~//~9B26R~
        Button btn=null;                                           //~9B25I~
        Button btn2=null;                                          //~va74I~
        switch(PactionID)                                          //~9B25I~
        {                                                          //~9B25I~
        case GCM_PON:                                              //~9B25I~
        	btn=btnPon;                                            //~9B25I~
            break;                                                 //~9B25I~
        case GCM_KAN:                                              //~9B25I~
        	btn=btnKan;                                            //~9B25I~
            break;                                                 //~9B25I~
        case GCM_CHII:                                             //~9B25I~
        	btn=btnChii;                                           //~9B25I~
            break;                                                 //~9B25I~
        case GCM_RON:                                              //~9B25I~
        	btn=btnRon;                                            //~9B25I~
            break;                                                 //~9B25I~
        case GCM_KAN_OR_PON:                                       //~va74I~
        	btn=btnKan;                                            //~va74I~
        	btn2=btnPon;                                           //~va74I~
            break;                                                 //~va74I~
        }                                                          //~9B25I~
        if (btn==null)                                             //~9B25I~
        	return;                                                //~9B25I~
        if (Pcolor!=0)                                             //~9B25I~
        {                                                          //~9B25I~
            if (Pcolor==COLOR_NORMAL)                           //~9B25I~
//  	        btn.setBackground(btnBackgroundColor/*drawable*/); //~9B25R~//~vaafR~
				setBtnBG(btn,COLOR_BTN_NORMAL_BG);                 //~vaafI~
            else                                                   //~9B25I~
//  	        btn.setBackgroundColor(Pcolor);                    //~9B25R~//~vaafR~
				setBtnBG(btn,Pcolor);                              //~vaafI~
            if (btn2!=null)                                        //~va74I~
            {                                                      //~va74I~
            	if (Pcolor==COLOR_NORMAL)                          //~va74I~
//  	        	btn2.setBackground(btnBackgroundColor/*drawable*/);//~va74I~//~vaafR~
					setBtnBG(btn2,COLOR_BTN_NORMAL_BG);            //~vaafI~
            	else                                               //~va74I~
//  	        	btn2.setBackgroundColor(Pcolor);               //~va74I~//~vaafR~
					setBtnBG(btn2,Pcolor);                         //~vaafI~
            }                                                      //~va74I~
        }                                                          //~9B25I~
        switch(Pstat)                                              //~9B25I~
        {                                                          //~9B25I~
        case BTN_STATUS_ENABLE_CANCEL:                             //~9B25I~
        	btnActionCancel.setVisibility(View.VISIBLE);            //~9B25I~
			swShownBtnCancel=true;                                 //~vaahI~
            if (AG.swTrainingMode)                                 //~va74I~
				statusPlayAlone=PactionID;                         //~va74I~
        	break;                                                 //~9B25I~
        case BTN_STATUS_DISABLE_CANCEL:                            //~9B25I~
			swShownBtnCancel=false;                                //~vaahI~
        	btnActionCancel.setVisibility(View.GONE);               //~9B25I~
        	break;                                                 //~9B25I~
        }                                                          //~9B25I~
        if (Dump.Y) Dump.println("GC.updateActionBtn2Touch exit swChankan="+swChankan);//~vaaVI~
    }                                                              //~9B25I~
    //*******************************************************************//~vaa2I~
    //*from Action2Touch on MainThread when PlayMatchNotify mode   //~vaa2I~
    //*******************************************************************//~vaa2I~
    private void updateActionBtn2TouchMatchNotify(int PactionID,int Pstat,int Pcolor)//~vaa2R~
    {                                                              //~vaa2I~
		if (Dump.Y) Dump.println("GC.updateActionBtn2TouchMatchNotify actionID="+PactionID+",stat="+Pstat+",color="+Integer.toHexString(Pcolor));//~vaa2I~
        if (PactionID==0)	//reset req by received action msg     //~vaa2I~
        {                                                          //~vaa2I~
    		resetPendingPlayMatchNotify(0);                        //~vaa2I~
            return;                                                //~vaa2I~
        }                                                          //~vaa2I~
        Button btn=null;                                           //~vaa2I~
        Button btn2=null;                                          //~vaa2I~
        int statMatch=0;                                           //~vaa2I~
        switch(PactionID)                                          //~vaa2I~
        {                                                          //~vaa2I~
        case GCM_PON:                                              //~vaa2I~
        	btn=btnPon;                                            //~vaa2I~
            statMatch=SPMN_PON;                                    //~vaa2I~
            break;                                                 //~vaa2I~
        case GCM_KAN:                                              //~vaa2I~
        	btn=btnKan;                                            //~vaa2I~
            statMatch=SPMN_KAN;                                    //~vaa2I~
            break;                                                 //~vaa2I~
        case GCM_CHII:                                             //~vaa2I~
//            int cp=AG.aPlayers.getCurrentPlayer();               //~vaiiR~
//            if (Players.nextPlayer(cp)!=PLAYER_YOU)              //~vaiiR~
//            {                                                    //~vaiiR~
//                if (Dump.Y) Dump.println("GC.updateActionBtn2TouchMatchNotify@@@@ignore updateBtn msg by  current player changed actionID="+PactionID);//~vaiiR~
//                break;                                           //~vaiiR~
//            }                                                    //~vaiiR~
        	btn=btnChii;                                           //~vaa2I~
            statMatch=SPMN_CHII;                                   //~vaa2I~
            break;                                                 //~vaa2I~
        case GCM_RON:                                              //~vaa2I~
        	btn=btnRon;                                            //~vaa2I~
            statMatch=SPMN_RON;                                    //~vaa2I~
            break;                                                 //~vaa2I~
        case GCM_KAN_OR_PON:                                       //~vaa2I~
        	btn=btnKan;                                            //~vaa2I~
            statMatch=SPMN_PON|SPMN_KAN;                           //~vaa2I~
        	btn2=btnPon;                                           //~vaa2I~
            break;                                                 //~vaa2I~
        }                                                          //~vaa2I~
        statusPlayMatchNotify|=statMatch;                          //~vaa2I~
		if (Dump.Y) Dump.println("GC.updateActionBtn2TouchMatchNotify statMatch="+Integer.toHexString(statMatch)+",statusPlayMatchNotify="+Integer.toHexString(statusPlayMatchNotify));//~vaa2I~
        if (btn==null)                                             //~vaa2I~
        	return;                                                //~vaa2I~
        if (Pcolor!=0)                                             //~vaa2I~
        {                                                          //~vaa2I~
//          if (Pcolor==COLOR_NORMAL)                              //~vaa2R~
//  	        btn.setBackground(btnBackgroundColor/*drawable*/); //~vaa2R~
//          else                                                   //~vaa2R~
//  	        btn.setBackgroundColor(Pcolor);                    //~vaa2R~
    		updateActionBtn2TouchMatchNotify(btn,Pcolor);          //~vaa2I~
            if (btn2!=null)                                        //~vaa2I~
            {                                                      //~vaa2I~
//          	if (Pcolor==COLOR_NORMAL)                          //~vaa2R~
//  	        	btn2.setBackground(btnBackgroundColor/*drawable*/);//~vaa2R~
//          	else                                               //~vaa2R~
//  	        	btn2.setBackgroundColor(Pcolor);               //~vaa2R~
	    		updateActionBtn2TouchMatchNotify(btn2,Pcolor);     //~vaa2I~
            }                                                      //~vaa2I~
        }                                                          //~vaa2I~
//      switch(Pstat)                                              //~vaa2I~
//      {                                                          //~vaa2I~
//      case BTN_STATUS_ENABLE_CANCEL:                             //~vaa2I~
//      	btnActionCancel.setVisibility(View.VISIBLE);           //~vaa2I~
//          if (AG.swTrainingMode)                                 //~vaa2I~
//  			statusPlayAlone=PactionID;                         //~vaa2I~
//      	break;                                                 //~vaa2I~
//      case BTN_STATUS_DISABLE_CANCEL:                            //~vaa2I~
//      	btnActionCancel.setVisibility(View.GONE);              //~vaa2I~
//      	break;                                                 //~vaa2I~
//      }                                                          //~vaa2I~
    }                                                              //~vaa2I~
    //*******************************************************************//~vaa2I~
    private void updateActionBtn2TouchMatchNotify(Button Pbtn,int Pcolor)//~vaa2I~
    {                                                              //~vaa2I~
		if (Dump.Y) Dump.println("GC.updateActionBtn2TouchMatchNotify Button="+Pbtn.toString()+",color="+Integer.toHexString(Pcolor));//~vaa2I~
//    if (false)      //TODO test                                  //~vaa2R~//~vaafR~
//    {                                                            //~vaa2I~//~vaafR~
//      if (Pcolor==COLOR_NORMAL)                                  //~vaa2I~//~vaafR~
//          Pbtn.setTextColor(btnTextColor);                       //~vaa2I~//~vaafR~
//      else                                                       //~vaa2I~//~vaafR~
//          Pbtn.setTextColor(Pcolor);                             //~vaa2I~//~vaafR~
//    }                                                            //~vaa2I~//~vaafR~
//    else                                                         //~vaa2I~//~vaafR~
        if (Pcolor==COLOR_NORMAL)                                  //~vaa2I~
//        if (true)                                                //~vaa2I~//~vaafR~
//        {                                                        //~vaa2I~//~vaafR~
//  		Drawable bgd=Pbtn.getBackground();                     //~vaa2R~
//          ColorStateList sl=bgd.colostateList;                   //~vaa2R~
//          setBtnBG(Pbtn,0xfff0f0f0);                             //~vaa2R~//~vaafR~
//        }                                                        //~vaa2I~//~vaafR~
//        else                                                     //~vaa2I~//~vaafR~
//          Pbtn.setBackground(btnBackgroundColor/*drawable*/);    //~vaa2I~//~vaafR~
            setBtnBG(Pbtn,COLOR_BTN_NORMAL_BG);                    //~vaafI~
        else                                                       //~vaa2I~
            setBtnBG(Pbtn,Pcolor);                                 //~vaa2R~
    }                                                              //~vaa2I~
    //*******************************************************************//~vaa2I~
    //*TODO test                                                   //~vaa2I~
    //*******************************************************************//~vaa2I~
	private void setBtnBG(Button Pbtn,int Pcolor)                  //~vaa2R~
    {                                                              //~vaa2I~
		if (Dump.Y) Dump.println("GC.setBtnBG color="+Integer.toHexString(Pcolor)+",btn="+Pbtn.toString());//~vaa2R~
//  	Drawable bgd=Pbtn.getBackground();                         //~vaa2R~
//      if (bgd instanceof ColorDrawable)                          //~vaa2R~
//      {                                                          //~vaa2R~
//      	int bgc=((ColorDrawable)bgd).getColor();               //~vaa2R~
//      }                                                          //~vaa2R~
//      BitmapDrawable bmd=(BitmapDrawable)bgd;                    //~vaa2R~
//      int bmc=bmd.getBitmap().getPixel(0,0);                     //~vaa2R~
//      int txc=Pbtn.getCurrentTextColor();                        //~vaa2R~
//      if (true)                                                  //~vaa2I~//~vaafR~
        	Utils.setBtnBG(Pbtn,Pcolor);                           //~vaa2I~
//      else                                                       //~vaa2I~//~vaafR~
//    	if (false)                               //TODO test       //~vaa2R~//~vaafR~
//      	Utils.setSpanTextBG(Pbtn,Pcolor);                      //~vaa2R~//~vaafR~
//    	else                                                       //~vaa2R~//~vaafR~
//      if (false)                               //TODO test       //~vaa2I~//~vaafR~
//      	Utils.setSpanBG(Pbtn,Pcolor);                          //~vaa2R~//~vaafR~
//      else                                                       //~vaa2I~//~vaafR~
//  		Utils.setTintBG(Pbtn,Pcolor);                          //~vaa2I~//~vaafR~
		if (Dump.Y) Dump.println("GC.setBtnBG entry swWinAnywayActive="+swWinAnywayActive+",swWinAnywayPushed="+swWinAnywayPushed+",swWinAnywayMore="+swWinAnywayMore);//~vacbI~//~vaq8R~
        swWinAnywayActive=false;                                   //~vacbI~
		if (Pbtn.getId()==BTNID_RON)                               //~vacbI~
        {                                                          //~vacbI~
//      	if (Pcolor==UAD2Touch.COLOR_BLOCKING && swWinAnywayPushed)//~vacbR~//~vaq8R~
           	if (Pcolor==UAD2Touch.COLOR_MORE)                      //+vaq8I~
            {                                                      //+vaq8I~
              	if (swWinAnywayPushed)                             //+vaq8M~
        			swWinAnywayMore=true;                          //+vaq8M~
            }                                                      //+vaq8I~
            else                                                   //+vaq8I~
        	if (Pcolor==UAD2Touch.COLOR_BLOCKING)                  //~vaq8I~
            {                                                      //~vacbI~
              if (swWinAnywayPushed)                               //~vaq8I~
            	swWinAnywayActive=true;                             //~vacbI~
              else                                                 //~vaq8I~
        	  if (swWinAnywayMore)  //2nd Win                      //~vaq8I~
            	swWinAnywayActive=true;                            //~vaq8I~
              swWinAnywayMore=false;                               //+vaq8I~
            }                                                      //~vacbI~
        }                                                          //~vacbI~
        swWinAnywayPushed=false;	//effective only once              //~vacbI~
		if (Dump.Y) Dump.println("GC.setBtnBG return swWinAnywayActive="+swWinAnywayActive+",swWinAnywayPushed="+swWinAnywayPushed+",swWinAnyWayMore="+swWinAnywayMore);//~vacbR~//~vaq8R~
    }                                                              //~vaa2I~
    //*******************************************************************//~va70I~
	private boolean isAvailableOpenReach()                         //~0329I~
    {                                                              //~0329I~
    	boolean rc=RuleSettingYaku.isAvailableOpenReach();          //~0329I~
        if (rc)	//available                                        //~0329I~
        {                                                          //~0329I~
        	if (isRobotGame())                                     //~0329R~
//  	    	if (!RuleSettingYaku.isAvailableOpenReachRobot())  //~vamvR~
    	    	if (!RuleSettingYaku.isAvailableOpenReachRobotNo())//~vamvI~
    	        	rc=false;                                      //~0329R~
        }                                                          //~0329I~
		if (Dump.Y) Dump.println("GC.isAvailableOpenReach rc="+rc);
		return rc;//~0329I~
	}                                                              //~0329I~
//  public boolean isRobotGame()                                   //~0329I~//~va9fR~
    private boolean isRobotGame()                                  //~va9fI~
    {                                                              //~0329I~
        int ctrActive=BTMulti.getMemberConnected();                //~0329I~
        boolean rc=(ctrActive+1<PLAYERS);                          //~0329I~
		if (Dump.Y) Dump.println("GC.isRobotGame rc="+rc+",ctrActive="+ctrActive);//~0329I~
        return rc;
    }                                                              //~0329I~
    //*******************************************************************//~0411I~
    //*from MainActivity by Back button press                      //~0411I~
    //*******************************************************************//~0411I~
    public void exitOnGameView()                                   //~0411I~
    {                                                              //~0411I~
		if (Dump.Y) Dump.println("GC.exitOnGameView");             //~0411I~
    	if (isGameOver())                                          //~va02I~
        {                                                          //~va02I~
	        if (AG.swTrainingMode)                                 //~va66R~
            {                                                      //~va80I~
//      		UView.showToast(R.string.Info_GameEnded);          //~va66R~//~va80R~
				endGameReturn();                                   //~va80I~
            }                                                      //~va80I~
            else                                                   //~va66R~
			if (BTMulti.isServerDevice())                          //~va02I~
        		UView.showToast(R.string.Info_GameEnded);          //~va02R~
            else                                                   //~va02I~
			    UView.showToast(R.string.Err_TryBackButtonFromServer);	//do connection failure//~va02I~
            return;                                                //~va02I~
        }                                                          //~va02I~
		if (Status.isGameSuspended())                              //~va02I~
        {                                                          //~va02I~
	        if (AG.swTrainingMode)                                 //~va66R~
        		UView.showToast(R.string.Info_GameEnded);          //~va66R~
            else                                                   //~va66R~
			if (BTMulti.isServerDevice())                          //~va02I~
        		UView.showToast(R.string.Info_GameEnded);          //~va02I~
            else                                                   //~va02I~
        	if (isConnectionLost()) //connection err occured on client//~vaf4I~
			    confirmEndGameReturn(R.string.Err_ConfirmReturnInGameClientIOErr);//~vaf4I~
            else                                                   //~vaf4I~
			    UView.showToast(R.string.Err_TryBackButtonFromServer);	//do connection failure//~va02I~
            return;                                          //~va02I~
        }                                                          //~va02I~
        if (!Status.isGamingForMenuInGameAndInterRound())	//before deal//~va02I~
        {                                                          //~va02M~
	        if (AG.swTrainingMode)                                 //~va66R~
			    confirmEndGameReturn(R.string.Err_ConfirmReturn);  //~va66R~
            else                                                   //~va66R~
			if (BTMulti.isServerDevice())                          //~va02I~
			    confirmEndGameReturn(R.string.Err_ConfirmReturn);  //~va02R~
            else                                                   //~va02I~
        	if (isConnectionLost()) //connection err occured on client//~vaf4R~
			    confirmEndGameReturn(R.string.Err_ConfirmReturnBeforeDealClientIOErr);//~vaf4I~
            else                                                   //~vaf4I~
			    UView.showToast(R.string.Err_TryBackButtonFromServer);	//do connection failure//~va02I~
            return;                                                //~va02M~
		}                                                          //~va02M~
//      if (Status.getStatusRestart()!=RESTART_NONE)                //~9A29I~//~0411R~
        if (Status.getStatusRestart()!=RESTART_NONE)               //~va02I~
        {                                                          //~va02I~
		    UView.showToast(R.string.Err_SuspendNoIOErrExitOnGameView);	//do connection failure//~va02I~
            return;                                                //~va02I~
        }                                                          //~va02I~
//  	if (AG.aBTMulti.BTGroup.getConnectedCtr()==0)              //~0411R~//~va02R~
    	if (isConnectionLost())                                    //~va02I~
        {                                                          //~0411I~
//  		UView.showToastLong(R.string.Err_SuspendNoIOErrExitOnGameView);//~0411R~
		    confirmEndGameReturn();                                //~0411I~
            return;                                                //~0411I~
        }                                                          //~0411I~
//        if (ctr==0)                                              //~0411R~
//        {                                                        //~0411R~
//            confirmReconnect();                                  //~0411R~
//        }                                                        //~0411R~
//        else                                                     //~0411R~
//        {                                                        //~0411R~
//            UView.showToast(R.string.Err_GamingNow);             //~0411R~
//        }                                                        //~0411R~
	    if (AG.swTrainingMode)                                     //~va66I~
        {                                                          //~va66I~
			confirmEndGameReturn(R.string.Err_ConfirmReturn_TrainingMode);//~va66I~
            return;                                                //~va66I~
        }                                                          //~va66I~
        UView.showToast(R.string.Err_GamingNow);                   //~0411I~
    }                                                              //~0411I~
    //*******************************************************************//~va02I~
    public boolean isConnectionLost()                              //~va02I~
    {                                                              //~va02I~
		boolean rc=connectionCtrAtStartGame!=AG.aBTMulti.BTGroup.getConnectedCtr();//~va02I~
		if (Dump.Y) Dump.println("GC.isConnectinLost rc="+rc+",connectionCtrAtStartGame="+connectionCtrAtStartGame);//~va02I~
        return rc;                                                 //~va02R~
    }                                                              //~va02I~
//    //*******************************************************************//~0411R~
//    //*when connectionCtr=0                                      //~0411R~
//    //*******************************************************************//~0411R~
//    private void confirmReconnect()                              //~0411R~
//    {                                                            //~0411R~
//        if (Dump.Y) Dump.println("GC.confirmReconnect");         //~0411R~
//        int titleid=R.string.Title_ConfirmReconnectOnGameView;   //~0411R~
//        int msgid=R.string.Msg_ConfirmReconnectOnGameView;       //~0411R~
//        swReconnect=true;                                        //~0411R~
//        int[] labelIDs=new int[]{R.string.Suspend_IOEReconnect,R.string.Exit,R.string.CancelExecute};//~0411R~
//        Alert.showAlert(titleid,msgid, BUTTON_POSITIVE|BUTTON_NEGATIVE|BUTTON_CLOSE,AG.aGC,labelIDs);//calback alertButtonAction//~0411R~
//    }                                                            //~0411R~
//    //*******************************************************************//~0411R~
//    //*when connectionCtr=0                                      //~0411R~
//    //*******************************************************************//~0411R~
//    private void alertActionReconnect(int Pbuttonid)             //~0411R~
//    {                                                            //~0411R~
//        if (Dump.Y) Dump.println("GC.alertActionReconnect buttonID="+Pbuttonid);//~0411R~
//        switch(Pbuttonid)                                        //~0411R~
//        {                                                        //~0411R~
//        case BUTTON_POSITIVE:                                    //~0411R~
//            openReconnect();                                     //~0411R~
//            break;                                               //~0411R~
//        case BUTTON_CLOSE:                                       //~0411R~
//            endGameForce();                                      //~0411R~
//            break;                                               //~0411R~
//        }                                                        //~0411R~
//    }                                                            //~0411R~
//    //*******************************************************************//~0411R~
//    private void openReconnect()                                 //~0411R~
//    {                                                            //~0411R~
//        if (Dump.Y) Dump.println("GC.openReconnect");            //~0411R~
//        SuspendIOErrReqDlg.showDialog();                         //~0411R~
//    }                                                            //~0411R~
//    //*******************************************************************//~0411R~
//    private void endGameForce()                                  //~0411R~
//    {                                                            //~0411R~
//        if (Dump.Y) Dump.println("GC.endGameForce");             //~0411R~
//        endGame(true/*swReturn*/);                               //~0411R~
//    }                                                            //~0411R~
    //*******************************************************************//~va06I~
    public static void playSound(int PctrGame)                     //~va06I~
    {                                                              //~va06I~
        if (Dump.Y) Dump.println("GC.playSound ctrGame="+PctrGame);//~va06I~
//        int typeBGM=PrefSetting.getBGMType();                      //~va06I~//~vad1R~
//        int soundID;                                               //~va06I~//~vad1R~
//        switch (typeBGM)                                           //~va06I~//~vad1R~
//        {                                                          //~va06I~//~vad1R~
//        case PS_BGM_NO:                                            //~va06I~//~vad1R~
//            soundID=-1;                                            //~va06I~//~vad1R~
//            break;                                                 //~va06I~//~vad1R~
//        case PS_BGM_4SEASONS:                                      //~va06I~//~vad1R~
//            switch (PctrGame%PLAYERS)                              //~va06I~//~vad1R~
//            {                                                      //~va06I~//~vad1R~
//            case 0:                                                //~va06I~//~vad1R~
//                soundID=SOUNDID_BGM_GAME1SLOW;                     //~va06I~//~vad1R~
//                break;                                             //~va06I~//~vad1R~
//            case 1:                                                //~va06I~//~vad1R~
//                soundID=SOUNDID_BGM_GAME2SLOW;                     //~va06I~//~vad1R~
//                break;                                             //~va06I~//~vad1R~
//            case 2:                                                //~va06I~//~vad1R~
//                soundID=SOUNDID_BGM_GAME3SLOW;                     //~va06I~//~vad1R~
//                break;                                             //~va06I~//~vad1R~
//            default:                                               //~va06I~//~vad1R~
//                soundID=SOUNDID_BGM_GAME4SLOW;                     //~va06I~//~vad1R~
//            }                                                      //~va06I~//~vad1R~
//            break;                                                 //~va06I~//~vad1R~
//        case PS_BGM_4SEASONS_FAST:                                 //~va06I~//~vad1R~
//            switch (PctrGame%PLAYERS)                              //~va06I~//~vad1R~
//            {                                                      //~va06I~//~vad1R~
//            case 0:                                                //~va06I~//~vad1R~
//                soundID=SOUNDID_BGM_GAME1FAST;                     //~va06I~//~vad1R~
//                break;                                             //~va06I~//~vad1R~
//            case 1:                                                //~va06I~//~vad1R~
//                soundID=SOUNDID_BGM_GAME2FAST;                     //~va06I~//~vad1R~
//                break;                                             //~va06I~//~vad1R~
//            case 2:                                                //~va06I~//~vad1R~
//                soundID=SOUNDID_BGM_GAME3FAST;                     //~va06I~//~vad1R~
//                break;                                             //~va06I~//~vad1R~
//            default:                                               //~va06I~//~vad1R~
//                soundID=SOUNDID_BGM_GAME4FAST;                     //~va06I~//~vad1R~
//            }                                                      //~va06I~//~vad1R~
//            break;                                                 //~va06I~//~vad1R~
//        case PS_BGM_SERIESK:                                       //~vac3I~//~vad1R~
//            switch (PctrGame%PLAYERS)                              //~vac3I~//~vad1R~
//            {                                                      //~vac3I~//~vad1R~
//            case 0:                                                //~vac3I~//~vad1R~
//                soundID=SOUNDID_BGM_EBURISHOU;                      //~vac3I~//~vad1R~
//                break;                                             //~vac3I~//~vad1R~
//            case 1:                                                //~vac3I~//~vad1R~
//                soundID=SOUNDID_BGM_MIZUCHUKOUKA;                  //~vac3I~//~vad1R~
//                break;                                             //~vac3I~//~vad1R~
//            case 2:                                                //~vac3I~//~vad1R~
//                soundID=SOUNDID_BGM_TOUCHIKUKOUKA;                 //~vac3I~//~vad1R~
//                break;                                             //~vac3I~//~vad1R~
//            case 3:                                                //~vac3I~//~vad1R~
//                soundID=SOUNDID_BGM_KYOUTO;                       //~vac3I~//~vad1R~
//                break;                                             //~vac3I~//~vad1R~
//            default:                                               //~vac3I~//~vad1R~
//                soundID=SOUNDID_BGM_TOP;                           //~vac3I~//~vad1R~
//            }                                                      //~vac3I~//~vad1R~
//            break;                                                 //~vac3I~//~vad1R~
//        default:                                                   //~va06I~//~vad1R~
//            soundID=SOUNDID_BGM_TOP;                               //~va06I~//~vad1R~
//        }                                                          //~va06I~//~vad1R~
//        if (typeBGM!=PS_BGM_NO)                                    //~va6iI~//~vac3R~
//        {                                                          //~va6iI~//~vac3R~
//            Rect r=Status.getGameSeq();                        //~va06I~//~va6iR~//~vac3R~
//            int  gameCtrSet=r.left;                                //~va6iR~//~vac3R~
//            if (Status.isFinalGame())                              //~va6iR~//~vac3R~
//                soundID=SOUNDID_BGM_MIZUCHUKOUKA;                  //~va6iR~//~vac3R~
//            else                                                   //~va6iR~//~vac3R~
//            if (gameCtrSet==1 && PctrGame==0)  //1st round of sounth rotation//~va6iR~//~vac3R~
//                soundID=SOUNDID_BGM_EBURISHOU;                     //~va6iR~//~vac3R~
//        }                                                          //~va6iI~//~vac3R~
		int soundID=PrefSetting.getSoundID();                      //~vad1I~
      if (soundID!=-1)                                             //~va61I~
        Sound.playBGM(soundID);                                    //~va06I~
    }                                                              //~va06I~
    //*******************************************************************//~va49I~
    public void highlightCompReq(boolean PswOn)                    //~va49I~
    {                                                              //~va49I~
        if (Dump.Y) Dump.println("GC.highlightCompReq sw="+PswOn+",old="+swCompReqButtonStatus); //~va49I~//~va97R~
    	highlightButton(btnCompReq,PswOn);                          //~va49I~
		swCompReqButtonStatus=PswOn;                               //~va97I~
    }                                                              //~va49I~
    //*******************************************************************//~va49I~
    private void highlightButton(Button Pbtn,boolean PswOn)        //~va49R~
    {                                                              //~va49I~
        if (Dump.Y) Dump.println("GC.highlightButton sw="+PswOn);  //~va49I~
    	if (PswOn)                                                 //~va49I~
        {                                                          //~va49I~
//  	    btnBackgroundColorCompReqDlg=Pbtn.getBackground();     //~va8BI~//~vaafR~
        	int color=AG.getColor(R.color.action_cancel);          //~va49I~
//          if (true ) //TODO                                      //~vaa2R~//~vaafR~
//            setBtnBG(Pbtn,color);                                //~vaa2I~//~vaafR~
//          else                                                   //~vaa2R~//~vaafR~
//  	    new UiFunc().setBackground(Pbtn,color);                //~va49R~//~vaafR~
    	    new UiFunc().setBtnBG(Pbtn,color);                     //~vaafI~
        }                                                          //~va49I~
        else                                                       //~va49I~
        {                                                          //~va49I~
//          if (true)   //TODO                                     //~vaa2I~//~vaafR~
//            		Utils.clearTintBG(Pbtn,btnBackgroundColorCompReqDlg/*drawable*/);//~vaa2I~//~vaafR~
//          else                                                   //~vaa2I~//~vaafR~
//          if (true)   //TODO                                     //~vaa2I~//~vaafR~
//            setBtnBG(Pbtn,COLOR_BTN_NORMAL_BG);                  //~vaa2I~//~vaafR~
//          else                                                   //~vaa2I~//~vaafR~
//          if (false) //TODO                                      //~vaa2R~//~vaafR~
//          	if (true)  //TODO                                  //~vaa2R~//~vaafR~
//  		  		Utils.setTintBG(Pbtn,0xffc0c0f0);              //~vaa2R~//~vaafR~
//          	else                                               //~vaa2R~//~vaafR~
//            		Utils.clearTintBG(Pbtn,btnBackgroundColorCompReqDlg/*drawable*/);//~vaa2R~//~vaafR~
//          else                                                   //~vaa2R~//~vaafR~
//  	    new UiFunc().setBackgroundDrawable(Pbtn,btnBackgroundColor/*drawable*/);//~va49I~//~va8BR~
//  	    new UiFunc().setBackgroundDrawable(Pbtn,btnBackgroundColorCompReqDlg/*drawable*/);//~va8BI~//~vaafR~
    	    new UiFunc().setBtnBG(Pbtn,COLOR_BTN_NORMAL_BG);       //~vaafI~
        }                                                          //~va49I~
    }                                                              //~va49I~
//    //*******************************************************************//~va70R~
//    public void setButtonPlayAlone(int PmsgID)                   //~va70R~
//    {                                                            //~va70R~
//        if (highlightButtonPlayAlone(PmsgID,true/*PswOn*/))      //~va70R~
//            statusPlayAlone=Pmsgid;                              //~va70R~
//        else                                                     //~va70R~
//            statusPlayAlone=0;                                   //~va70R~
//        if (Dump.Y) Dump.println("GC.setButtonPlayAlone msgid="+PmdgID+",statusPlayAlone="+statusPlayAlone);//~va70R~
//    }                                                            //~va70R~
    //*******************************************************************//~va70I~
    //*from UAD2T.updateBtnUIPlayAloneNotify when Human player in PlayAlone mode//~vaa2R~
    //*from UAD2T.updateBtnUIPlayAloneNotify when Human player in Matchmode//~vaa2I~
    //*******************************************************************//~va70I~
    public void updateActionBtn2TouchPlayAloneNotify(int PactionID,int Pstat,int Pcolor)//~va70R~
    {                                                              //~va70I~
		if (Dump.Y) Dump.println("GC.updateActionBtn2TouchPlayAloneNotify actionID="+PactionID+",stat="+Pstat+",color="+Integer.toHexString(Pcolor));//~va70R~
       if ((Pstat & BTN_STATUS_MATCH_NOTIFY)!=0)                   //~vaa2I~
       {                                                           //~vaa2I~
    	updateActionBtn2TouchMatchNotify(PactionID,(Pstat & ~BTN_STATUS_MATCH_NOTIFY),Pcolor);//~vaa2I~
       }                                                           //~vaa2I~
       else                                                        //~vaa2I~
       {                                                           //~vaa2I~
	    updateActionBtn2Touch(PactionID,Pstat,Pcolor);             //~va70I~
        statusPlayAlone=PactionID;                                 //~va70R~
       }                                                           //~vaa2I~
    }                                                              //~va70I~
//    //*******************************************************************//~va70R~
//    private boolean highlightButtonPlayAlone(int PmsgID,boolean PswOn)//~va70R~
//    {                                                            //~va70R~
//        boolean rc=false;                                        //~va70R~
//        Button btn=null;                                         //~va70R~
//        switch(PmsgID)                                           //~va70R~
//        {                                                        //~va70R~
//        case GCM_RON:                                            //~va70R~
////          btn=btnRon;   //do by UARon.selectInfoPlayAlone<--RACall//~va70R~
//            break;                                               //~va70R~
//        case GCM_PON:                                            //~va70R~
//            btn=btnPon;                                          //~va70R~
//            break;                                               //~va70R~
//        case GCM_KAN:                                            //~va70R~
//            btn=btnKan;                                          //~va70R~
//            break;                                               //~va70R~
//        case GCM_CHII:                                           //~va70R~
//            btn=btnChii;                                         //~va70R~
//            break;                                               //~va70R~
//        }                                                        //~va70R~
//        if (btn!=null)                                           //~va70R~
//        {                                                        //~va70R~
//            rc=true;                                             //~va70R~
//            highlightButton(btn,PswOn);                          //~va70R~
//        }                                                        //~va70R~
//        if (Dump.Y) Dump.println("GC.highlightButtonPlayAlone msgid="+PmdgID+",swOn="+PswOn+",rc="+rc);//~va70R~
//        return rc;                                               //~va70R~
//    }                                                            //~va70R~
    //*******************************************************************//~va70I~
    //* chk action pending                                         //~va70I~
    //*******************************************************************//~va70I~
//  private void resetPendingPlayAloneNotify(int PmsgID)           //~va70R~//~vaahR~
    private boolean resetPendingPlayAloneNotify(int PmsgID)        //~vaahR~
    {                                                              //~va70I~
    	boolean rc=true;	//do sendMsg                           //~vaahI~
        if (Dump.Y) Dump.println("GC.resetPendingPlayAloneNotify msgID="+PmsgID+",statusPlayAlone="+statusPlayAlone);//~va70R~
        if (statusPlayAlone==0)                                    //~va70R~
//      	return;                                                //~va70R~//~vaahR~
        	return true;                                           //~vaahR~
        if (statusPlayAlone==GCM_KAN_OR_PON)                       //~va74I~
        {                                                          //~va74I~
        	if (PmsgID!=GCM_KAN && PmsgID!=GCM_PON)                //~va74I~
            {                                                      //~vaahI~
//          	return;                                            //~va74I~//~vaahR~
                rc=!chkCancelBtn();	//if cancel btn shown, return false(skip sendmsg)//~vaahR~
            	return rc;                                         //~vaahR~
            }                                                      //~vaahI~
        }                                                          //~va74I~
        else                                                       //~va74I~
        if (PmsgID==GCM_DISCARD)                                   //~va80I~
        {                                                          //~va80I~
//          if (statusPlayAlone!=GCM_RON)                          //~va80I~//~vacdR~
            {                                                      //~va80I~
		        if (Dump.Y) Dump.println("GC.resetPendingPlayAloneNotify ignored by status is not GCM_RON");//~va80I~
//          	return;                                            //~va80I~//~vaahR~
                rc=!chkCancelBtn();	//if cancel btn shown, return false(skip sendmsg)//~vaahR~
            	return rc;                                         //~vaahR~
            }                                                      //~va80I~
        }                                                          //~va80I~
        else                                                       //~va80I~
        if (PmsgID!=statusPlayAlone)                               //~va70R~
        {                                                          //~vaahI~
//      	return;                                                //~va70I~//~vaahR~
            rc=!chkCancelBtn();	//if cancel btn shown, return false(skip sendmsg)//~vaahR~
            return rc;                                             //~vaahR~
        }                                                          //~vaahI~
        int svStatus=statusPlayAlone;                              //~va74I~
	    statusPlayAlone=0;                                         //~va70R~
//      actionPlayAloneNotify(PmsgID);                             //~va70I~//~va74R~
        actionPlayAlone(svStatus,true/*updateBtn*/);               //~va74R~
//      AG.aUADelayed.actionCanceledPlayAloneNotify(PmsgID);       //~va70R~
        return true;                                               //~vaahI~
    }                                                              //~va70I~
    //*******************************************************************//~vaa2I~
    //* chk action pending                                         //~vaa2I~
    //*******************************************************************//~vaa2I~
    private void resetPendingPlayMatchNotify(int PmsgID)           //~vaa2I~
    {                                                              //~vaa2I~
        if (Dump.Y) Dump.println("GC.resetPendingPlayMatchNotify msgID="+PmsgID+",statusPlayMatchNotify="+Integer.toHexString(statusPlayMatchNotify));//~vaa2I~
        if (statusPlayMatchNotify==0)                              //~vaa2I~
        	return;                                                //~vaa2I~
        if ((statusPlayMatchNotify & SPMN_PON)!=0)                 //~vaa2I~
        {                                                          //~vaa2I~
        	if (PmsgID==0 || PmsgID==GCM_PON)                      //~vaa2I~
            {                                                      //~vaa2I~
		        statusPlayMatchNotify &= ~SPMN_PON;                //~vaa2I~
			    updateActionBtn2TouchMatchNotify(btnPon,COLOR_NORMAL);//~vaa2I~
            }                                                      //~vaa2I~
        }                                                          //~vaa2I~
        if ((statusPlayMatchNotify & SPMN_KAN)!=0)                 //~vaa2I~
        {                                                          //~vaa2I~
        	if (PmsgID==0 || PmsgID==GCM_KAN)                      //~vaa2I~
            {                                                      //~vaa2I~
		        statusPlayMatchNotify &= ~SPMN_KAN;                //~vaa2I~
			    updateActionBtn2TouchMatchNotify(btnKan,COLOR_NORMAL);//~vaa2I~
            }                                                      //~vaa2I~
        }                                                          //~vaa2I~
        if ((statusPlayMatchNotify & SPMN_CHII)!=0)                //~vaa2I~
        {                                                          //~vaa2I~
        	if (PmsgID==0 || PmsgID==GCM_CHII)                     //~vaa2I~
            {                                                      //~vaa2I~
		        statusPlayMatchNotify &= ~SPMN_CHII;               //~vaa2I~
			    updateActionBtn2TouchMatchNotify(btnChii,COLOR_NORMAL);//~vaa2I~
            }                                                      //~vaa2I~
        }                                                          //~vaa2I~
        if ((statusPlayMatchNotify & SPMN_RON)!=0)                 //~vaa2I~
        {                                                          //~vaa2I~
        	if (PmsgID==0 || PmsgID==GCM_RON)                      //~vaa2I~
            {                                                      //~vaa2I~
		        statusPlayMatchNotify &= ~SPMN_RON;                //~vaa2I~
			    updateActionBtn2TouchMatchNotify(btnRon,COLOR_NORMAL);//~vaa2I~
            }                                                      //~vaa2I~
        }                                                          //~vaa2I~
        if (Dump.Y) Dump.println("GC.resetPendingPlayMatchNotify exit msgID="+PmsgID+",statusPlayMatchNotify="+Integer.toHexString(statusPlayMatchNotify));//~vaa2I~
    }                                                              //~vaa2I~
    //*******************************************************************//~va75I~
    //* chk action pending                                         //~va75I~
    //* return false:not your turn                                 //~va75I~
    //*******************************************************************//~va75I~
    private boolean resetPendingPlayAloneNotifyTake()              //~va75R~
    {                                                              //~va75I~
        if (Dump.Y) Dump.println("GC.resetPendingPlayAloneNotifyTake statusPlayAlone="+statusPlayAlone);//~va75R~
        if (statusPlayAlone==0)                                    //~va75I~
        	return true;                                           //~va75R~
        if (chkCancelBtn()) 	//cancel btn shown                 //~vaahR~
        	return false;                                          //~vaahR~
        if (AG.aPlayers.getNextPlayer()!=PLAYER_YOU)               //~va75M~
        {                                                          //~va75M~
	        actionError(0,PLAYER_YOU,R.string.AE_NotYourTurn);     //~va75M~
            return false;                                          //~va75M~
        }                                                          //~va75M~
        int svStatus=statusPlayAlone;                              //~va75I~
	    statusPlayAlone=0;                                         //~va75I~
    	updateActionBtn2Touch(svStatus,BTN_STATUS_DISABLE_CANCEL,COLOR_NORMAL);//~va75I~
        return true;                                               //~va75I~
    }                                                              //~va75I~
    //*******************************************************************//~va70I~
    public int getStatusPlayAlone()                                //~va70R~
    {                                                              //~va70I~
        if (Dump.Y) Dump.println("GC.getStatusPlayAlone status="+statusPlayAlone);//~va70R~
	    return statusPlayAlone;                                    //~va70R~
    }                                                              //~va70I~
    //*******************************************************************
    //*on MainThread by btn push for TrainingMode                  //~va70R~
    //*******************************************************************//~va70I~
//  private void actionPlayAloneNotify(int PmsgID)                 //~va70I~//~va74R~
    private void actionPlayAlone(int PmsgID,boolean PswUpdateBtn)  //~va74R~
    {
        if (Dump.Y) Dump.println("GC.actionPlayAlone msgID="+PmsgID+",swUpdateBtn="+PswUpdateBtn+",swCancel="+swCancel+",swChankan="+swChankan+",notify mode="+AG.swPlayAloneNotify);//~va70I~//~va74R~//~va75R~//~vaaVR~
    	int player;                                                //~va70I~
        switch(PmsgID)                                             //~va78I~
        {                                                          //~va78I~
        case GCM_RON:                                       //~va70I~//~va78R~
    		player=AG.aPlayers.getCurrentPlayer();    //for pass chk of PLS.isActionDoneExceptRon//~va70R~
    	  	if (AG.aPlayers.getCurrentPlayerTaking()==player)      //~va80R~
            {                                                      //~va80I~
            	if (swCancel && swChankan)                         //~vaaVR~
            	{                                                  //~vaaVI~
			        if (Dump.Y) Dump.println("GC.actionPlayAlone reschedule rinshantakable by swChankan");//~vaaVR~
		        	AG.aUserAction.UAK.postDelayedTakableKan(player);//~vaaVI~//~vaaUR~
                }                                                  //~vaaVI~
                swChankan=false;                                   //~vaaUI~
		        if (Dump.Y) Dump.println("GC.actionPlayAlone skip postDelayedPonKan by currentPlayerTaking player="+player);//~va80R~
            	break;                                             //~va80I~
            }                                                      //~va80I~
        	AG.aUADelayed.postDelayedPonKan(player);               //~va70R~
            break;                                                 //~va78I~
        case GCM_CHII:                                             //~va78R~
        	if (swCancel && AG.swPlayAloneNotify)                  //~va75I~
		    	sendMsg(GCM_TAKE,PLAYER_YOU);	//simulate take button//~va75I~
            break;                                                 //~va78I~
        case GCM_PON:                                              //~va78R~
        case GCM_KAN:                                              //~va78I~
        case GCM_KAN_OR_PON:                                       //~va78I~
    		player=AG.aPlayers.getCurrentPlayer();    //for pass chk of PLS.isActionDoneExceptRon//~va78I~
        	if (Dump.Y) Dump.println("GC.actionPlayAlone swCancel="+swCancel+",current player="+player);//~vaahI~
//        if (swCancel && player==PLAYER_YOU)   // you are taken (ankan or kakan)  //~vaahI~//~vakrR~
//        	postAutoDiscardAnkan();                                //~vaahI~//~vakrR~
//        else                                                     //~vaahI~//~vakrR~
//  	    GameViewHandler.sendMsg(GCM_NEXT_PLAYER,Players.nextPlayer(player),0,0);	//on server,to UADiscard.nextPlayer()//~va78I~//~vakrR~
          	if (swCancel)   //cancel button in Notify Mode         //~vakrR~
            {                                                      //~vakrI~
          		if (player==PLAYER_YOU)   // you are taken (ankan or kakan)//~vakrI~
					postAutoDiscardAnkan();                        //~vakrI~
                else	//Pon or minkan                            //~vakrI~
					AG.aUADelayed.postDelayedTakablePlayAloneNotifyCanceledPonKan(player);//~vakrR~
            }                                                      //~vakrI~
            break;                                                 //~va78I~
        default:                                                   //~va78I~
    		player=AG.aPlayers.getNextPlayer();                    //~va70I~
        	AG.aUserAction.UAD.setTimeout(true/*PswServer*/,player/*nextPlayer*/);//~va70I~
        }                                                          //~va70I~
//      AG.aUADelayed.reset2TouchPlayAloneNotify(PmsgID);          //~va70R~
      if (PswUpdateBtn)                                            //~va74I~
    	updateActionBtn2Touch(PmsgID,BTN_STATUS_DISABLE_CANCEL,COLOR_NORMAL);//~va70I~
    }
    //*******************************************************************//~vaahI~
    private boolean chkCancelBtn()                                 //~vaahI~
    {                                                              //~vaahI~
    	if (swShownBtnCancel)                                      //~vaahI~
	        actionError(0,PLAYER_YOU,R.string.AE_CancelAtFirst);   //~vaahI~
		if (Dump.Y) Dump.println("GC.chkCancelBtn rc=swShownBtnCancel="+swShownBtnCancel);//~vaahI~
        return swShownBtnCancel;                                   //~vaahI~
    }                                                              //~vaahI~
    //*******************************************************************//~vaaUI~
    public void setPlayAloneNotifyChankan(boolean Psw)             //~vaaUI~
    {                                                              //~vaaUI~
		if (Dump.Y) Dump.println("GC.setPlayAloneNotifyChankan Psw="+Psw);//~vaaUI~
        swChankan=Psw;                                             //~vaaUI~
    }                                                              //~vaaUI~
    //*******************************************************************//~vaahI~
    //*when ankan or kakan was canceld                             //~vakrI~
    //*******************************************************************//~vakrI~
    private void postAutoDiscardAnkan()                            //~vaahI~
    {                                                              //~vaahI~
        if (Dump.Y) Dump.println("GC.postAutoDiscardAnkan");       //~vaahI~
        AG.aUserAction.UAT.setAutoDiscardTimeout(true/*swServer*/,PLAYER_YOU,GCM_TAKE);//~vaahI~
    }                                                              //~vaahI~
    //*******************************************************************//~vaahI~
    private void showGameType(Button Pbtn)                         //~vaahI~
    {                                                              //~vaahI~
        if (!AG.isDebuggable)                                      //~vaahI~
        {                                                          //~vaahI~
          	Pbtn.setEnabled(false);                                //~vaahI~
            float px=Pbtn.getTextSize();                             //~vaahI~
            Pbtn.setTextSize(COMPLEX_UNIT_PX,px*1.4f);             //~vaahR~
            Pbtn.setTypeface(Pbtn.getTypeface(), Typeface.BOLD);   //~vaahI~
		  if (BTMulti.isServerDevice())                            //~vajiI~
          {                                                        //~vajiI~
  	        Pbtn.setBackgroundColor(COLOR_GST_RELEASE);            //~vaahI~
            setBtnBG(Pbtn,COLOR_GST_RELEASE);                      //~vaahI~
//          Pbtn.setTextColor(COLOR_GST_RELEASE_TEXT);             //~vaahI~//~vamvR~
          }                                                        //~vajiM~
          else                                                     //~vajiM~
          {                                                        //~vajiM~
  	        Pbtn.setBackgroundColor(COLOR_GST_RELEASE_CLIENT);     //~vajiM~
            setBtnBG(Pbtn,COLOR_GST_RELEASE_CLIENT);               //~vajiM~
//          Pbtn.setTextColor(COLOR_GST_RELEASE_TEXT_CLIENT);      //~vajiI~//~vamvR~
          }                                                        //~vajiM~
        }                                                          //~vaahI~
        else                                                       //~vaahI~
        {                                                          //~vaahI~
		  if (BTMulti.isServerDevice())                            //~vajiI~
            setBtnBG(Pbtn,COLOR_GST_DEBUG);                        //~vaahI~
          else                                                     //~vajiI~
            setBtnBG(Pbtn,COLOR_GST_DEBUG_CLIENT);                 //~vajiI~
        }                                                          //~vaahI~
        int intGST=RuleSetting.getGameSetType();                   //~vaahI~
//      String strGST= RuleSettingEnum.strsGameSetType[intGST];    //~vaahI~//~vacfR~
        String strGST;                                             //~vacfI~
        if (AG.portrait)                                           //~vacfI~
            strGST= RuleSettingEnum.strsGameSetType[intGST];       //~vacfI~
        else                                                       //~vacfI~
            strGST= RuleSettingEnum.strsGameSetTypeLand[intGST];   //~vacfI~
		if (Dump.Y) Dump.println("GC.showGameType intGst="+intGST+",strGst="+strGST);//~vacfI~
        Pbtn.setText(strGST);                                      //~vaahI~
    }                                                              //~vaahI~
}//class GC                                                 //~dataR~//~@@@@R~//~v@@@R~
