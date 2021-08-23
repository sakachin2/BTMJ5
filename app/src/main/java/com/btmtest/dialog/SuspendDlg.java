//*CID://+vac5R~:                             update#= 1245;       //+vac5R~
//*****************************************************************//~v101I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//+vac5I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.graphics.Rect;
import android.text.Spanned;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~9305I~
import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.GConst;
import com.btmtest.game.History;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.game.UserAction;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.URadioGroup;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.game.UserAction.*;
import static com.btmtest.game.gv.GMsg.*;
import static com.btmtest.utils.Alert.*;
import static com.btmtest.utils.Utils.*;

public class SuspendDlg  extends AccountsDlg                       //~9818R~
            implements UCheckBox.UCheckBoxI, URadioGroup.URadioGroupI//~0308I~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.suspenddlg;              //~9312R~//~9322R~//~9819R~
    private static final int LAYOUTID_SMALLFONT=R.layout.suspenddlg_theme;//+vac5I~
    private static final int TITLEID=R.string.Title_SuspendDlgReq;//~9307I~//~9312R~//~9322R~//~9818R~//~9822R~
    private static final int TITLEID_RESP=R.string.Title_SuspendDlgResp;//~9822I~
    private static final String HELPFILE="SuspendDlg";                //~9220I~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9818R~
                                                                   //~9318I~
    private static final int URGP_SUSPEND=1;	//listner parm     //~0308I~
    private static final int UCBP_SUSPENDER=5;                     //~0308I~
                                                                   //~0308I~
    protected static final int POS_SCOREMSGTYPE=1;                    //~9318R~//~9320R~//~v@01R~
//  private static final int POS_SUSPEND_OPT=2;                        //~9318R~//~9322R~//~9525R~//~9822R~//~9826R~
//  private static final int POS_AMMOUNT=3;                        //~9822R~//~9826R~
    protected static final int POS_DATE       =2;                    //~9826I~//~v@01R~
    protected static final int POS_TIME       =3;                    //~9826I~//~v@01R~
    protected static final int POS_SUSPEND_OPT=4;                    //~9826I~//~v@01R~
    protected static final int POS_AMMOUNT=5;                        //~9826I~//~v@01R~
    protected static final int POS_REACH=POS_AMMOUNT+PLAYERS;        //~9822I~//~v@01R~
    protected static final int POS_PENALTY=POS_AMMOUNT+PLAYERS*2;//~9415I~//~9822R~//~v@01R~
    protected static final int POS_GAMESEQ=POS_PENALTY;              //~9824I~//~v@01R~
    protected static final int POS_OTHER=POS_GAMESEQ+PLAYERS;      //~v@01R~
    protected static final int POS_AMMOUNT_FINALSCORE=POS_AMMOUNT+PLAYERS*3;//~9415I~//~v@01R~
    protected static final int POS_REPLAY_ESWN=0;                    //~9321R~//~v@01R~
    protected static final int POS_OKNG=2;                           //~9321R~//~9322R~//~v@01R~
//  private static final double RATE_SMALLDEVICE_WIDTH=0.95;       //~9818I~//~9819R~
                                                                   //~9318I~
    public  static final int SUSPENDGAME_CONFIRM_REQUEST=1;   //~9322R~//~9819R~//~v@01R~
    public  static final int SUSPENDGAME_CONFIRM_REPLY=2;    //~9322I~//~9819R~//~v@01R~
    public  static final int SUSPENDGAME_CONFIRMED=3;         //~9322I~//~9819R~//~v@01R~
    public  static final int SUSPENDGAME_INTERRUPTED=4;            //~9824I~//~v@01R~
//  private static final int[] rbIDSamePoint=new int[]{R.id.rbSamePointEswn,R.id.rbSamePointSpritCut,R.id.rbSamePointSpritUp};//~9401I~//~9407R~
//  private static final int[] rbIDSamePoint=new int[]{R.id.rbSamePointEswn,R.id.rbSamePointSpritCut};//~9407I~//~9819R~
                                                                   //~9322I~
//  private TextView[] tvsScore,tvsTotal,tvsBotCharge;                       //~9312I~//~9316R~//~9321R~//~9322R~//~9429R~//~9818R~
//  private TextView[] tvsName,tvsEswn;    //~9309R~               //~9312R~//~9818R~
//  private int[] lastScore,topPrize=new int[PLAYERS],orderPrize=new int[PLAYERS];//~9322R~//~9819R~
//  private int[] newTotal=new int[PLAYERS];                       //~9312I~//~9819R~
//  private int[] minusPrize,minusCharge;                          //~9415R~//~9819R~
//  private String[] accountNames;                                 //~9312I~//~9819R~
                                                                   //~9320I~
//  private TextView[] tvsOrder,tvsTopPrize,tvsOrderPrize,tvsLabelBotCharge;         //~9322R~//~9818R~
//  private TextView[] tvsLabelBotCharge;                          //~v@01R~
//  private TextView[] tvsMinusPrize,tvsMinusCharge,tvsFinalScore,tvsFinalPoint; //~9415R~//~9416R~//~9818R~
//  private UCheckBox[][] cbssPayTo=new UCheckBox[PLAYERS][PLAYERS];//~9320I~//~9818R~
//  private LinearLayout[] llsMinusPrize,llsMinusCharge,llsBotCharge;           //~9416R~//~9429R~//~9818R~
//  private Button btnTotal/*,btnShowRule*/;                           //~9417I~//~9615R~//~9818R~
//  private boolean /*swReceived,*/swMinusPayGetAllPoint;                                    //~9321R~//~9415R~//~9615R~//~9819R~
    protected boolean swReceived;                                    //~9822I~//~v@01R~
//  private Accounts ACC;                                          //~9322I~//~9819R~
//  private Complete CMP;                                          //~9322I~//~9819R~
//  private boolean swInitLayout,swOrderByEswn; //,swSamePointCut;                    //~9322R~//~9407R~//~9819R~
//  private int[] payerInfo=new int[PLAYERS*PLAYERS];              //~9322I~//~9819R~
    private boolean swDismissBeforNew;                             //~9322I~
//  private int[] settingOrderPrize;                               //~9322I~//~9819R~
//  private int settingTopPrize;                                   //~9322I~//~9819R~
//  private int[] idx2Order,order2Idx;                             //~9322I~//~9819R~
//  private int[] accountEswn;                                     //~9322I~//~9615R~
//  private int[] finalScore=new int[PLAYERS];                     //~9819R~
//  private int[] finalPoint=new int[PLAYERS];//~9415I~            //~9819R~
//  private String[] finalPoint1000=new String[PLAYERS];                 //~9614I~//~9615R~//~9819R~
//  private	int initialScore,debt,adjustFinalPoint;                                 //~9401I~//~9416R~//~9819R~
//  private	int robotPayType;                                      //~9429I~//~9819R~
//  private	int birdPay;                                           //~9501I~//~9819R~
//  private	boolean swBird,swBirdPayToEach;                                        //~9501I~//~9602R~//~9819R~
//  private	int endgameType=EGDR_MINUSSTOP;                        //~9525I~//~9823R~
//  private	int typeClose;                                         //~9525I~//~9819R~
    protected int suspendOption,suspendPenalty;                        //~9820I~//~v@01R~
    protected URadioGroup rgSuspend;                                 //~9820I~//~9A31R~
    private TextView[] tvsReachPoint,tvsPenalty;               //~9820I~//~9821R~
	protected int[] ctrReach;  //position seq                        //~9820R~//~v@01R~
//  private int[] lastScoreOrg,lastScore;  //position seq          //~9821I~//~v@01R~
//  private int[] newScore,lastScore;  //position seq              //~v@01R~
    private int[] newScore;  //position seq                        //~v@01I~
    protected int[] lastScore;  //position seq                     //~v@01I~
	protected boolean[] swsSuspend;			//wind seq                                    //~9820I~//~9822R~//~v@01R~
    protected UCheckBox[] cbsSuspendPlayer;                          //~9820I~//~v@01R~//~9A21R~
    private int[] intsReachPoint,intsPenalty;	//pos seq                      //~9821R~//~9822R~
    protected int[] accountEswn;                                     //~9822I~//~9A21R~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public SuspendDlg()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9818R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("SuspendDlg.defaultConstructor"); //~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9818R~
        Status.setSuspendGame(true);                               //~v@01I~
        AG.aSuspendDlg=this;                                         //~9321I~//~9322R~//~9818R~
	    CMP.swSent=false;                                          //~0314I~
    }                                                              //~v@@@R~
    //******************************************                   //~9818I~
    //On Server                                                    //~9829I~
    //*from Accounts.suspendGame<--startNextGame<--ACAtouch.GS_READY_TO_NEXTGAME<--ACAT.showReadyToStartNextGame//~9820I~
    //*ACC.nextGame,resetGame,nextGameByLastGeme                   //~9820I~
    //******************************************                   //~9820I~
    public static SuspendDlg newInstance()                         //~9818I~//~9823R~
    {                                                              //~9818I~
        if (Dump.Y) Dump.println("SuspendDlg.newInstance");        //~9818I~
    	SuspendDlg dlg=new SuspendDlg();                           //~9818I~
//  	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9818I~//+vac5R~
    	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//+vac5I~
    			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_CLOSEBTN|FLAG_HELPBTN|FLAG_RULEBTN,//~9818I~
				TITLEID,HELPFILE);                                 //~9818I~
        dlg.lastScore=AG.aAccounts.score;                          //~9818I~
        if (Dump.Y) Dump.println("SuspendDlg.newInstance score="+Arrays.toString(dlg.lastScore));//~9818I~
        dlg.minusPrize=new int[PLAYERS];                           //~9818I~
        dlg.minusCharge=new int[PLAYERS];                          //~9818I~
        return dlg;                                                //~9818I~
    }                                                              //~9818I~
    //******************************************                   //~v@@@R~
    public static SuspendDlg newInstance(int[][] PintssP)         //~9415I~//~9818R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("SuspendDlg.newInstance PintssP="+Utils.toString(PintssP));//~9822R~
    	SuspendDlg dlg=newInstance();                              //~9818R~
        dlg.swReceived=true;                                       //~9822R~
        dlg.suspendOption=PintssP[0][0];                           //~9822I~
        dlg.lastScore=PintssP[1]; //point at last game(not total score)//~9822I~
        dlg.ctrReach=PintssP[2]; //point at last game(not total score)//~9822I~
        dlg.swsSuspend=new boolean[PLAYERS];                       //~9822I~
        Utils.boolean_int(false/*Pswb2i*/,dlg.swsSuspend,PintssP[3]);//~9822R~
        dlg.finalScore=PintssP[4];                                 //~9822I~//~v@01I~
        if (Dump.Y) Dump.println("SuspendDlg.newInstance suspendOption="+dlg.suspendOption);//~9822I~
        if (Dump.Y) Dump.println("SuspendDlg.newInstance lastScore="+Arrays.toString(dlg.lastScore));//~9822I~
        if (Dump.Y) Dump.println("SuspendDlg.newInstance ctrReach="+Arrays.toString(dlg.ctrReach));//~9822I~
        if (Dump.Y) Dump.println("SuspendDlg.newInstance swsSuspend="+Arrays.toString(dlg.swsSuspend));//~9822I~
        if (Dump.Y) Dump.println("SuspendDlg.newInstance finalScore="+Arrays.toString(dlg.finalScore));//~9822I~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
//    //******************************************                   //~9525I~//~9818R~
//    public static SuspendDlg newInstance(int[][] PintssP,int PendgameType,int PtypeClose)//~9525I~//~9818R~
//    {                                                              //~9525I~//~9818R~
//        if (Dump.Y) Dump.println("SuspendDlg.newInstance endgameType="+PendgameType+",typeClose="+Integer.toHexString(PtypeClose));//~9525I~//~9818R~
//        SuspendDlg dlg=newInstance(PintssP);                      //~9525I~//~9818R~
//        dlg.endgameType=PendgameType;                              //~9525I~//~9818R~
//        dlg.typeClose=PtypeClose;                                  //~9525I~//~9818R~
//        return dlg;                                                //~9525I~//~9818R~
//    }                                                              //~9525I~//~9818R~
    //*********************************************************    //~9314I~//~9822I~
    //*from OKNGDlg.initLayout.setupValueOKNG,return okngResponse dealer//~9822I~
    //*********************************************************    //~9822I~
    protected int getEswnRequester(int PcurrentEswn)               //~9314I~//~9822I~
    {                                                              //~9314I~//~9822I~
        int rc=AG.aAccounts.getCurrentServerEswn();                //~9822R~
        if (Dump.Y) Dump.println("SuspendDlg.getEswnRequester rc="+rc);//~9314I~//~9822I~
        return rc;                                                 //~9314I~//~9822I~
    }                                                              //~9314I~//~9822I~
    //******************************************                 //~9303R~//~9312R~//~9819R~//~9822R~
    @Override                                                    //~9303R~//~9305R~//~9819R~//~9822R~
    protected void initLayoutAdditional(View PView)                            //~v@@@I~//~9303R~//~9305R~//~9321R~//~9819R~//~9822R~
    {                                                              //~v@@@M~//~9303R~//~9305R~//~9819R~//~9822R~
        if (Dump.Y) Dump.println("SuspendDlg.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9303R~//~9304R~//~9305R~//~9307R~//~9312R~//~9322R~//~9818R~//~9819R~//~9822R~
//      swRequester=AG.aAccounts.isFirstDealerReal();              //~9605M~//~9819R~//~9822R~
        swRequester=!swReceived;	//Server                                   //~9822I~//~0218R~
        if (Dump.Y) Dump.println("SuspendDlg:initLayout swRequester="+swRequester+",score="+Arrays.toString(ACC.score));//no minus status//~9320I~//~9322R~//~9818R~//~9819R~//~9822R~//~0227I~
        endgameType=EGDR_NORMAL;                                   //~9823R~
//      swByPosition=true;  //OKNGDialog for AccountsDlg           //~9606I~//~9819R~//~9822R~
        swInitLayout=true;                                         //~9322I~//~9819R~//~9822R~
                                                                   //~9822I~
        if (!swReceived)                                           //~9A21I~
        {                                                          //~9A21I~
			ctrReach=AG.aAccounts.intsCtrPendingReach;             //~9A21I~
    		swsSuspend=AG.aStatus.swEswnSuspendRequest;            //~9A21I~
        }                                                          //~9A21I~
	    initLayoutAdditionalSuspend(PView);                        //~9821R~//~9822M~
                                                                   //~9822I~
        getRuleSetting();                                          //~9320I~//~9819R~//~9822R~
        showRuleSetting(PView);                                    //~9401I~//~9819R~//~9822R~
        setupAmmount(PView);                                       //~9312I~//~9819R~//~9822R~
        setAccountName();                                          //~9309I~//~9819R~//~9822R~
        showAmmount();                                             //~9309I~//~9819R~//~9822R~
        swInitLayout=false;                                        //~9322I~//~9819R~//~9822R~
        saveLast();                                                //~9322M~//~9819R~//~9822R~
        hideResponseEswn();                                        //~0218I~
    }                                                              //~v@@@M~//~9303R~//~9305R~//~9819R~//~9822R~
    //******************************************                   //~0218I~
    private void hideResponseEswn()                                //~0218I~
    {                                                              //~0218I~
    	if (Dump.Y) Dump.println("SuspendDlg.hideResponseEswn");   //~0218I~
        hideResponseEswn(!swRequester);                            //~0218R~
    }                                                              //~0218I~
    //******************************************                   //~9821I~
    protected void initLayoutAdditionalSuspend(View PView)         //~9821I~
    {                                                              //~9821I~
        if (Dump.Y) Dump.println("SuspendDlg.initLayoutAdditionalSuspend");//~9821I~
    	accountEswn=ACC.getCurrentEswnByPosition();	//Postion seq  //~9822I~
        rgSuspend=new URadioGroup(PView,R.id.rgSuspendOptionQuery,URGP_SUSPEND/*listener parm*/,rbIDSuspendOption);//~9822R~//~0308R~
        if (!swReceived)                                           //~9822I~
	        suspendOption=RuleSettingOperation.getSuspendOption(); //~9822I~
        rgSuspend.setCheckedID(suspendOption,swReceived ? true : false);//~9822I~
        rgSuspend.setListener(this);                               //~0308I~
//      if (!swReceived)                                           //~9822R~//~9A21R~
//      {                                                          //~9822I~//~9A21R~
//  		ctrReach=AG.aAccounts.intsCtrPendingReach;                 //~9821I~//~9822R~//~9A21R~
//  		swsSuspend=AG.aStatus.swEswnSuspendRequest;             //~9821I~//~9822R~//~9A21R~
//      }                                                          //~9822I~//~9A21R~
        if (Dump.Y) Dump.println("SuspendDlg.initLayout ctrReach="+Arrays.toString(ctrReach)+",swsSuspend="+Arrays.toString(swsSuspend));//~9821I~
        if (Dump.Y) Dump.println("SuspendDlg.initLayout after setoption swsSuspend="+Arrays.toString(swsSuspend));//~9821I~//~9A31R~
        setupSuspendPlayer(PView);                                 //~9820I~//~9821M~
//      setupBackPointReach(PView);                                //~9821R~
		setTitle();                                                //~9822I~
    }                                                              //~9821I~
//    //******************************************                   //~9321I~//~9819R~
//    @Override                                                      //~9321I~//~9819R~
//    protected void setupValueAdditional(View PView)                //~9321I~//~9819R~
//    {                                                              //~9321I~//~9819R~
//        if (Dump.Y) Dump.println("SuspendDlg.initEnv");              //~9321I~//~9322R~//~9818R~//~9819R~
//        ACC=AG.aAccounts;                                          //~9322I~//~9819R~
//        CMP=AG.aComplete;                                          //~9322I~//~9819R~
////      accountNames=ACC.getAccountNames();               //~9312I~//~9321M~//~9322R~//~9416R~//~9819R~
//        accountNames=ACC.getAccountNamesByPosition();              //~9416I~//~9819R~
////      accountEswn=ACC.getStartingEswnOfAccounts();               //~9322I~//~9615R~//~9819R~
//        btnTotal        =              UButton.bind(PView,R.id.FixTotal,this);//~9321M~//~9819R~
////      btnShowRule     =              UButton.bind(PView,R.id.ShowRule,this);//~9417I~//~9615R~//~9819R~
//                                       UButton.bind(PView,R.id.ShowRule,this);//~9615I~//~9819R~
//    }                                                              //~9322I~//~9819R~
//    //******************************************                   //~9818R~//~9819R~
//    @Override //UFDlg                                              //~9818R~//~9819R~
//    protected int getDialogWidth()                                 //~9818R~//~9819R~
//    {                                                              //~9818R~//~9819R~
//        int ww=0;                                                  //~9818R~//~9819R~
//        if (AG.swSmallDevice && AG.portrait)                      //~9818R~//~9819R~
//        {                                                          //~9818R~//~9819R~
//            ww=(int)(AG.scrWidth*RATE_SMALLDEVICE_WIDTH);          //~9818R~//~9819R~
//        }                                                          //~9818R~//~9819R~
//        if (Dump.Y) Dump.println("SuspendDlg.getDialogWidth ww="+ww+",portrait="+AG.portrait+",smallDevice="+AG.swSmallDevice);//~9818R~//~9819R~
//        return ww;                                                 //~9818R~//~9819R~
//    }                                                              //~9818R~//~9819R~
//    //******************************************                   //~9322I~//~9819R~
//    private void saveLast()                                       //~9322I~//~9819R~
//    {                                                              //~9322I~//~9819R~
//        CMP.lastScore=lastScore;    //save for reshow              //~9322R~//~9402R~//~9415R~//~9819R~
//        CMP.minusPrize=minusPrize;  //save for reshow              //~9415I~//~9819R~
//        CMP.minusCharge=minusCharge;    //save for reshow          //~9415I~//~9819R~
//        CMP.finalScore=finalScore;  //save for reshow              //~9415I~//~9819R~
//        CMP.pos2Order=idx2Order;                                   //~9520I~//~9819R~
//    }                                                              //~9321I~//~9819R~
    //******************************************                   //~9819I~
    @Override                                                      //~9819I~
    protected void saveLast()                                      //~9819R~
    {                                                              //~9819I~
        if (Dump.Y) Dump.println("SuspendDlg.saveLast Nop");       //~9819I~
    }                                                              //~9819I~
    //******************************************                   //~v@@@I~//~9220R~//~9303R~//~9306R~
    @Override                                                      //~9819I~
    protected void setTitle()                                        //~v@@@I~//~9220R~//~9303R~//~9306R~
    {                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
        Spanned s=Status.getSpannedGameTitle(Utils.getStr(swReceived?TITLEID_RESP:TITLEID));//~9306I~//~9822R~
        androidDlg.setTitle(s);                                    //~v@@@R~//~9220R~//~9302R~//~9303R~//~9306R~
    }                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
    //******************************************                   //~9320I~//~9819R~//~9822I~
    @Override                                                      //~9822I~
    protected void getRuleSetting()                                  //~9320I~//~9819R~//~9822R~
    {                                                              //~9320I~//~9819R~//~9822R~
        initialScore=RuleSetting.getInitialScore();            //~9322R~//~9401R~//~9819R~//~9822R~
        debt=RuleSetting.getDebt();                            //~9322I~//~9401R~//~9819R~//~9822R~
        settingTopPrize=ACC.topPrize;                              //~9428R~//~9819R~//~9822R~
        settingOrderPrize=RuleSetting.getOrderPrize();              //~9322R~//~9819R~//~9822R~
        swOrderByEswn=RuleSetting.isOrderByEswn();                 //~9322I~//~9819R~//~9822R~
//      swMinusPayGetAllPoint=RuleSetting.isMinusPayGetAllPoint(); //~9415I~//~9819R~//~9822R~
        adjustFinalPoint=RuleSetting.getScoreToPoint();            //~9416I~//~9819R~//~9822R~
        robotPayType=RuleSetting.getRobotPayType();                //~9429I~//~9819R~//~9822R~
        swBird=ACC.isGrillBird();                                  //~9501I~//~9819R~//~9822R~
//      birdPay=RuleSetting.getGrilledBirdPay();                   //~9501I~//~9819R~//~9822R~
//      swBirdPayToEach=RuleSetting.isBirdPayToEach();             //~9602I~//~9819R~//~9822R~
        suspendPenalty=RuleSettingOperation.getSuspendPenalty();    //~9820I~//~9822M~
    }                                                              //~9320I~//~9819R~//~9822R~
    //******************************************                   //~9822I~
    @Override                                                      //~9822I~
    protected void showRuleSetting(View PView)                     //~9822I~
    {                                                              //~9822I~
        TextView tv;                                               //~9822I~
        String s;                                                  //~9822I~
        tv=(TextView)    UView.findViewById(PView,R.id.SettingTopPrize);//~9822I~
        s=AG.resource.getString(R.string.Label_AccountsSettingTopPrizeEdit,(debt-initialScore)*PLAYERS,initialScore,debt);//~9822I~
    	if (Dump.Y) Dump.println("AccountsDlg.showRuleSetting topprize="+s);//~9822I~
        tv.setText(s);                                             //~9822I~
        RuleSetting.setOrderPrize(PView,true/*swFixed*/);          //~9822I~
//      RuleSetting.setGrillBird(PView,true/*swFixed*/);           //~9822R~
	    RuleSetting.setScoreToPoint(PView,true);                   //~9822I~
	    RuleSettingOperation.setSuspendOption(PView,true/*swFixed*/);//~9820R~//~9822R~
        RuleSetting.setRobotOption(PView,true/*swFixed*/);         //~9823I~
    }                                                              //~9822I~
    //******************************************                   //~9309I~//~9819R~//~9821R~
    @Override                                                      //~9821I~
    protected void setupAmmount(View PView)                        //~9309I~//~9819R~//~9821R~
    {                                                              //~9309I~//~9819R~//~9821R~
        LinearLayout[] lls=new LinearLayout[PLAYERS];              //~9321I~//~9819R~//~9821R~
        lls[0]=(LinearLayout)    UView.findViewById(PView,R.id.llResult1);//~9309I~//~9321R~//~9322R~//~9819R~//~9821R~
        lls[1]=(LinearLayout)    UView.findViewById(PView,R.id.llResult2);//~9321I~//~9322R~//~9819R~//~9821R~
        lls[2]=(LinearLayout)    UView.findViewById(PView,R.id.llResult3);//~9321I~//~9322R~//~9819R~//~9821R~
        lls[3]=(LinearLayout)    UView.findViewById(PView,R.id.llResult4);//~9321I~//~9322R~//~9819R~//~9821R~
        tvsOrder=new TextView[PLAYERS];                             //~9321I~//~9322R~//~9819R~//~9821R~
        tvsName=new TextView[PLAYERS];                             //~9321I~//~9819R~//~9821R~
        tvsEswn=new TextView[PLAYERS];                             //~9401I~//~9819R~//~9821R~
        tvsScore=new TextView[PLAYERS];                            //~9321I~//~9819R~//~9821R~
        tvsTopPrize=new TextView[PLAYERS];                              //~9321I~//~9322R~//~9819R~//~9821R~
        tvsOrderPrize=new TextView[PLAYERS];                       //~9322I~//~9819R~//~9821R~
//      tvsTotal=new TextView[PLAYERS];                            //~9321I~//~9819R~//~9821R~
//      tvsMinusPrize=new TextView[PLAYERS];                       //~9415I~//~9819R~//~9821R~
//      tvsMinusCharge=new TextView[PLAYERS];                      //~9415I~//~9819R~//~9821R~
        tvsFinalScore=new TextView[PLAYERS];                       //~9415I~//~9819R~//~9821R~
        tvsFinalPoint=new TextView[PLAYERS];                       //~9416I~//~9819R~//~9821R~
        tvsBotCharge=new TextView[PLAYERS];                        //~9429I~//~9819R~//~9821R~
        tvLabelBotCharge=(TextView)     UView.findViewById(PView,R.id.Label_ForBotCharge);//~v@01I~
//      tvsLabelBotCharge=new TextView[PLAYERS];                   //~9501I~//~9819R~//~9821R~//~v@01R~
//      llsMinusPrize=new LinearLayout[PLAYERS];                   //~9416I~//~9819R~//~9821R~
//      llsMinusCharge=new LinearLayout[PLAYERS];                  //~9416I~//~9819R~//~9821R~
        llsBotCharge=new LinearLayout[PLAYERS];                    //~9429I~//~9819R~//~9821R~
        tvsReachPoint=new TextView[PLAYERS];                       //~9821I~
        tvsPenalty=new TextView[PLAYERS];                          //~9820I~//~9821M~
        for (int ii=0;ii<PLAYERS;ii++)                                 //~9321I~//~9819R~//~9821R~
        {                                                          //~9321I~//~9819R~//~9821R~
            LinearLayout ll=lls[ii];                               //~9321I~//~9819R~//~9821R~
            tvsOrder[ii]=(TextView)    UView.findViewById(ll,R.id.Order);//~9321I~//~9322R~//~9819R~//~9821R~
            tvsEswn[ii]=(TextView)    UView.findViewById(ll,R.id.StartingEswn);//~9401I~//~9819R~//~9821R~
            tvsName[ii]=(TextView)    UView.findViewById(ll,R.id.memberName);//~9321I~//~9819R~//~9821R~
            tvsScore[ii]=(TextView)     UView.findViewById(ll,R.id.Score);//~9321I~//~9322R~//~9819R~//~9821R~
            tvsTopPrize[ii]=(TextView)     UView.findViewById(ll,R.id.TopPrize);//~9321I~//~9322R~//~9819R~//~9821R~
            tvsOrderPrize[ii]=(TextView)     UView.findViewById(ll,R.id.OrderPrize);//~9321I~//~9322R~//~9819R~//~9821R~
//          tvsTotal[ii]=(TextView)     UView.findViewById(ll,R.id.Total);//~9322I~//~9819R~//~9821R~
//          tvsMinusPrize[ii]=(TextView)     UView.findViewById(ll,R.id.minusPrize);//~9415I~//~9819R~//~9821R~
//          tvsMinusCharge[ii]=(TextView)     UView.findViewById(ll,R.id.minusCharge);//~9415I~//~9819R~//~9821R~
            tvsFinalScore[ii]=(TextView)     UView.findViewById(ll,R.id.finalScore);//~9415I~//~9819R~//~9821R~
            tvsFinalPoint[ii]=(TextView)     UView.findViewById(ll,R.id.finalPoint);//~9416I~//~9819R~//~9821R~
//          llsMinusPrize[ii]=(LinearLayout)     UView.findViewById(ll,R.id.llMinusPrize);//~9416I~//~9819R~//~9821R~
//          llsMinusCharge[ii]=(LinearLayout)     UView.findViewById(ll,R.id.llMinusCharge);//~9416I~//~9819R~//~9821R~
            llsBotCharge[ii]=(LinearLayout)     UView.findViewById(ll,R.id.llForBotCharge);//~9429I~//~9819R~//~9821R~
            tvsBotCharge[ii]=(TextView)     UView.findViewById(ll,R.id.tvForBot);//~9429I~//~9819R~//~9821R~
         //   tvsLabelBotCharge[ii]=(TextView)     UView.findViewById(ll,R.id.Label_ForBotCharge);//~9501I~//~9819R~//~9821R~
            tvsReachPoint[ii]=(TextView)    UView.findViewById(ll,R.id.tvBackPointReach);//~9821R~
            tvsPenalty[ii]=(TextView)       UView.findViewById(ll,R.id.tvSuspendPenalty);//~9820I~//~9821M~
        }                                                          //~9321I~//~9819R~//~9821R~
    }                                                              //~9309I~//~9819R~//~9821R~
//    //******************************************                   //~9819I~//~9821R~
//    private void setupAmmountSuspend(View PView)                        //~9819I~//~9820R~//~9821R~
//    {                                                              //~9819I~//~9821R~
//        LinearLayout ll,ll2;                                       //~9820R~//~9821R~
//        if (Dump.Y) Dump.println("SuspendDlg.setAmmountSuspend");         //~9819I~//~9820R~//~9821R~
////        ll=(LinearLayout) UView.findViewById(PView,R.id.llSuspendTitle);//~9820I~//~9821R~
////        ll.setVisibility(View.VISIBLE);                            //~9820I~//~9821R~
////        ll=(LinearLayout) UView.findViewById(PView,R.id.llMinusPayTitle);//~9820I~//~9821R~
////        ll.setVisibility(View.GONE);                               //~9820I~//~9821R~
////      TextView tv=(TextView) UView.findViewById(PView,R.id.Label_AccountTotal);//~9820I~//~9821R~
////      tv.setVisibility(View.GONE);                               //~9820I~//~9821R~
////      tvsReachPoint=new TextView[PLAYERS];                       //~9820I~//~9821R~
//        LinearLayout[] lls=llsResult;   //by AccountsDlg           //~9820M~//~9821R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~9820I~//~9821R~
//        {                                                          //~9820I~//~9821R~
//            ll=lls[ii];                               //~9820I~  //~9821R~
////            tvsTotal[ii].setVisibility(View.GONE);                 //~9820R~//~9821R~
////          tvsReachPoint[ii]=(TextView)    UView.findViewById(ll,R.id.tvBackReachPoint);//~9820I~//~9821R~
//            ll2=(LinearLayout) UView.findViewById(ll,R.id.llSuspend);//~9820R~//~9821R~
//            ll2.setVisibility(View.VISIBLE);                       //~9820R~//~9821R~
//            ll2=(LinearLayout) UView.findViewById(ll,R.id.llMinusPay);//~9820R~//~9821R~
//            ll2.setVisibility(View.GONE);                          //~9820R~//~9821R~
//        }                                                          //~9820I~//~9821R~
//    }                                                              //~9819I~//~9821R~
    //******************************************                   //~9820I~
    protected void setupSuspendPlayer(View PView)                              //~9820I~//~v@01R~
    {                                                              //~9820I~
        UCheckBox cbE=new UCheckBox(PView,R.id.cbAbPE);            //~9309I~//~9820I~
        UCheckBox cbS=new UCheckBox(PView,R.id.cbAbPS);            //~9309I~//~9820I~
        UCheckBox cbW=new UCheckBox(PView,R.id.cbAbPW);            //~9309I~//~9820I~
        UCheckBox cbN=new UCheckBox(PView,R.id.cbAbPN);            //~9309I~//~9820I~
        cbsSuspendPlayer=new UCheckBox[]{cbE,cbS,cbW,cbN};          //~9309I~//~9820I~
        if (Dump.Y) Dump.println("SuspendDlg.setupSuspendPlayer swsSuspend="+Arrays.toString(swsSuspend));//~9A21I~
        for (int ii=0;ii<PLAYERS;ii++)                                 //~9820I~
        {                                                          //~0308I~
//          cbsSuspendPlayer[ii].setState(swsSuspend[ii],true/*swFixed*/);//~9820R~//~0308R~
            cbsSuspendPlayer[ii].setState(swsSuspend[ii],!swRequester/*swFixed*/);//~0308R~
            if (swRequester)                                       //~0308I~
		        cbsSuspendPlayer[ii].setListener(this,UCBP_SUSPENDER);//~0308I~
        }                                                          //~0308I~
    }                                                              //~9820I~
//    //******************************************                 //~9821R~
//    private void setupBackPointReach(View PView)                 //~9821R~
//    {                                                            //~9821R~
//        TextView tve=(TextView)    UView.findViewById(PView,R.id.tvBackPointReachE);//~9821R~
//        TextView tvs=(TextView)    UView.findViewById(PView,R.id.tvBackPointReachS);//~9821R~
//        TextView tvw=(TextView)    UView.findViewById(PView,R.id.tvBackPointReachW);//~9821R~
//        TextView tvn=(TextView)    UView.findViewById(PView,R.id.tvBackPointReachN);//~9821R~
//        tvsReachPoint=new TextView[]{tve,tvs,tvw,tvn};           //~9821R~
//    }                                                            //~9821R~
    @Override                                                      //~9821I~
    //******************************************                   //~9309I~//~9819R~//~9821R~
    protected void showAmmount()                                     //~9309I~//~9819R~//~9821R~
    {                                                              //~9309I~//~9819R~//~9821R~
//  	lastScoreOrg=lastScore.clone();                            //~9821R~//~v@01R~
    	newScore=lastScore.clone();                                //~v@01I~
//      showAmmountSuspend(lastScore);	//update lastscore by backpointreach//~9821I~//~v@01R~
        showAmmountSuspend(newScore);	//update lastscore by backpointreach//~v@01I~
                                                                   //~9821I~
//      getOrder(lastScore);                                       //~9322I~//~9819R~//~9821R~//~v@01R~
//      setPrize(lastScore);                                                //~9322I~//~9819R~//~9821R~//~v@01R~
        getOrder(newScore);                                        //~v@01I~
        setPrize(newScore);                                        //~v@01I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9309I~//~9819R~//~9821R~
        {                                                          //~9309I~//~9819R~//~9821R~
            int eswn=ii;    //account lsit is position order       //~9416I~//~9819R~//~9821R~
            int wind=accountEswn[ii];                              //~9822I~
	        tvsEswn[eswn].setText(GConst.nameESWN[wind]); //~9312I~  //~9320R~//~9401R~//~9822I~
            tvsOrder[eswn].setText(Integer.toString(idx2Order[ii]+1));//~9401R~//~9819R~//~9821R~
//          tvsScore[eswn].setText(Integer.toString(lastScore[ii]));//~9401R~//~9819R~//~9821R~//~v@01R~
            tvsScore[eswn].setText(Integer.toString(newScore[ii]));//~v@01I~
            tvsTopPrize[eswn].setText(Integer.toString(topPrize[ii]));//~9401R~//~9819R~//~9821R~
            tvsOrderPrize[eswn].setText(Integer.toString(orderPrize[ii]));//~9401R~//~9819R~//~9821R~
//          newTotal[ii]=lastScore[ii]+topPrize[ii]+orderPrize[ii];//~9401R~//~9819R~//~9821R~//~v@01R~
            newTotal[ii]=newScore[ii]+topPrize[ii]+orderPrize[ii]; //~v@01I~
//          tvsTotal[eswn].setText(Integer.toString(newTotal[ii]));//~9401R~//~9819R~//~9821R~
//          int mp=minusPrize[ii];                                 //~9416I~//~9819R~//~9821R~
//          if (mp!=0)                                             //~9416I~//~9819R~//~9821R~
//              tvsMinusPrize[eswn].setText(Integer.toString(mp));//~9415R~//~9416R~//~9819R~//~9821R~
//          else                                                   //~9416I~//~9819R~//~9821R~
//              llsMinusPrize[eswn].setVisibility(View.GONE);      //~9416I~//~9819R~//~9821R~
//          if (swMinusPayGetAllPoint)                             //~9415I~//~9819R~//~9821R~
//          {                                                      //~9415I~//~9819R~//~9821R~
//              int mc=minusCharge[ii];                            //~9416I~//~9819R~//~9821R~
//              finalScore[ii]=newTotal[ii]+minusPrize[ii]+mc;//~9415I~//~9416R~//~9819R~//~9821R~
//              if (mc!=0)                                         //~9416I~//~9819R~//~9821R~
//                  tvsMinusCharge[eswn].setText(Integer.toString(mc));//~9415I~//~9416R~//~9819R~//~9821R~
//              else                                               //~9416I~//~9819R~//~9821R~
//                  llsMinusCharge[eswn].setVisibility(View.GONE); //~9416I~//~9819R~//~9821R~
//          }                                                      //~9415I~//~9819R~//~9821R~
//          else                                                   //~9415I~//~9819R~//~9821R~
//          {                                                      //~9415I~//~9819R~//~9821R~
//              finalScore[ii]=newTotal[ii]+minusPrize[ii];        //~9415I~//~9819R~//~9821R~
//              tvsMinusCharge[eswn].setText(Integer.toString(0)); //~9415I~//~9416R~//~9819R~//~9821R~
//              llsMinusCharge[eswn].setVisibility(View.GONE);     //~9416I~//~9819R~//~9821R~
//          }                                                      //~9415I~//~9819R~//~9821R~
            finalScore[ii]=newTotal[ii]+intsPenalty[ii];           //~9821I~
            tvsFinalScore[eswn].setText(Integer.toString(finalScore[ii]));//~9415I~//~9819R~//~9821R~
        }                                                          //~9309I~//~9819R~//~9821R~
//      if (swBird)                                                //~9501I~//~9819R~//~9821R~
//          adjustByBird(finalScore);                              //~9501I~//~9819R~//~9821R~
//      else                                                       //~9501I~//~9611R~//~9819R~//~9821R~
        if (ACC.activeMembers<PLAYERS)                              //~9429I~//~9819R~//~9821R~
            adjustByRobotScore(finalScore);                        //~9429R~//~9819R~//~9821R~
        else                                                       //~9824I~
        	tvLabelBotCharge.setText("");                          //~9824I~
//      getFinalPoint(finalScore,lastScore);                                           //~9416I~//~9417R~//~9819R~//~9821R~//~v@01R~
        getFinalPoint(finalScore,newScore);                        //~v@01I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9416I~//~9819R~//~9821R~
        {                                                          //~9416I~//~9819R~//~9821R~
            String s;                                              //~9416I~//~9819R~//~9821R~
            int fp=finalPoint[ii];                                 //~9416I~//~9819R~//~9821R~
            if (adjustFinalPoint==S2P_NO)                        //~9416I~//~9819R~//~9821R~
            {                                                      //~9614I~//~9819R~//~9821R~
                s=fp/1000+"."+((Math.abs(fp)%1000)/100);           //~9614I~//~9819R~//~9821R~
            }                                                      //~9614I~//~9819R~//~9821R~
            else                                                   //~9416I~//~9819R~//~9821R~
            {                                                      //~9614I~//~9819R~//~9821R~
                s=Integer.toString(fp/1000);                       //~9416I~//~9819R~//~9821R~
            }                                                      //~9614I~//~9819R~//~9821R~
            tvsFinalPoint[ii].setText(s);                          //~9416R~//~9819R~//~9821R~
            finalPoint1000[ii]=s;                                  //~9615I~//~9819R~//~9821R~
        }                                                          //~9416I~//~9819R~//~9821R~
        ACC.finalPoint1000=finalPoint1000;                         //~9614I~//~9819R~//~9821R~
//      endScore=newTotal;                                         //~9402I~//~9415R~//~9819R~//~9821R~
    }                                                              //~9309I~//~9819R~//~9821R~
    //******************************************                   //~9819I~
    protected void showAmmountSuspend(int[] PlastScore)                                   //~9819I~//~9820R~//~9821R~//~v@01R~
    {                                                              //~9819I~
    	if (Dump.Y) Dump.println("SuspendDlg.showAmmountSuspend lastScore in="+Arrays.toString(PlastScore));//~9821I~
        intsPenalty=new int[PLAYERS];                        //~9820I~//~9821R~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9820I~
        {                                                          //~9820I~
            if (swsSuspend[ii])   //current eswn seq                       //~9820R~//~9822R~//~9A21R~
            {                                                      //~9820I~
    	        int pos=ACC.currentEswnToPosition(ii);             //~9822I~
	            intsPenalty[pos]-=suspendPenalty;                   //~9820I~//~9822R~
            	for (int jj=0;jj<PLAYERS;jj++)                     //~9820R~
                {                                                  //~9820I~
                	if (jj!=pos)                                    //~9820I~//~9822R~
			            intsPenalty[jj]+=suspendPenalty/3;         //~9820R~
                }                                                  //~9820I~
            }                                                      //~9820I~
        }                                                          //~9820I~
        intsReachPoint=new int[PLAYERS];                           //~9821I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9820I~
        {                                                          //~9820I~
            int reachPoint=ctrReach[ii]*POINT_REACH;               //~9820I~
            intsReachPoint[ii]=reachPoint;                         //~9821I~
            PlastScore[ii]+=reachPoint;                            //~9821I~
            tvsReachPoint[ii].setText(Integer.toString(reachPoint));//~9820I~
	    	if (Dump.Y) Dump.println("SuspendDlg.showAmmountSuspend ii="+ii+",reachPoint="+reachPoint+",intsReachPoint="+Arrays.toString(intsPenalty));//~9821I~
            int penalty=intsPenalty[ii];                           //~9820I~
	        tvsPenalty[ii].setText(Integer.toString(penalty)); //gain also bot//~9820I~
        }                                                          //~9820I~
    	if (Dump.Y) Dump.println("SuspendDlg.showAmmountSuspend intsPenalty="+Arrays.toString(intsPenalty));//~9821I~
    	if (Dump.Y) Dump.println("SuspendDlg.showAmmountSuspend intsReachPoint="+Arrays.toString(intsReachPoint));//~9821I~
    	if (Dump.Y) Dump.println("SuspendDlg.showAmmountSuspend lastScore out="+Arrays.toString(PlastScore));//~9821I~
                                                                   //~9821I~
    }                                                              //~9819I~
    //*******************************************************      //~9321I~
    @Override                                                      //~9321I~
    public void onDismissDialog()                                  //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("SuspendDlg.onDismissDialog");   //~9321I~//~9819R~
        super.onDismissDialog();                                   //~9819I~
        if (!swDismissBeforNew)                                    //~9322I~
        {                                                          //~9819I~
	        AG.aSuspendDlg=null;                                         //~9321I~//~9322R~//~9818R~
        }                                                          //~9819I~
        swDismissBeforNew=false;                                   //~9322I~
    }                                                              //~9321I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void setButton()                                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("SuspendDlg.setButton");    //~9819I~//~9A31R~
    	super.setButton();                                         //~9321I~
        if (swRequester)                                           //~9321I~
	        btnTotal.setEnabled(swAllOK);                          //~9321I~
        else                                                       //~9321I~
	        btnTotal.setVisibility(View.GONE);                     //~9321I~
    }                                                              //~9321I~
    //*******************************************************      //~0308I~
    //*URadioGroupI                                                //~0308I~
    //*******************************************************      //~0308I~
    @Override                                                      //~0308I~
    public void onChangedURG(int PbtnID,int Pparm)                 //~0308I~
    {                                                              //~0308I~
        if (Dump.Y) Dump.println("SuspendDlg.onChangedURG parm="+Pparm+",btnid="+Integer.toHexString(PbtnID));//~0308I~
        if (swInitLayout)                                          //~0308I~
        {                                                          //~0308I~
            return;                                                //~0308I~
        }                                                          //~0308I~
        boolean swChanged=false;                                   //~0308I~
        switch (Pparm)                                             //~0308I~
        {                                                          //~0308I~
        case URGP_SUSPEND:                                        //~0308I~
        	swChanged=true;                                        //~0308I~
            break;                                                 //~0308I~
        }                                                          //~0308I~
        if (swChanged)                                             //~0308I~
        {                                                          //~0308I~
	        CMP.swSent=false;                                      //~0314I~
            swAllOK=false;                                         //~0308I~
            resetRespStat();	//OKNGDlg                          //~0308I~
    		setButton();                                           //~0308I~
        }                                                          //~0308I~
    }                                                              //~0308I~
    //*******************************************************      //~0308I~
    //*UCheckBoxI                                                  //~0308I~
    //*******************************************************      //~0308I~
    @Override                                                      //~0308I~
    public void onChangedUCB(CompoundButton Pbtn, boolean PisChecked, int Pparm)//~0308I~
    {                                                              //~0308I~
        if (Dump.Y) Dump.println("Suspend.onChangedUCB parm="+Pparm+",isChecked="+PisChecked);//~0308I~
        if (swInitLayout)                                          //~0308I~
        	return;                                                //~0308I~
        boolean swChanged=false;                                   //~0308I~
        switch(Pparm)                                              //~0308I~
        {                                                          //~0308I~
    	case UCBP_SUSPENDER:	//error eswn chkbox                //~0308I~
        	swChanged=true;                                        //~0308I~
            int eswn=getSuspenderEswn(Pbtn);                      //~0308I~
            swsSuspend[eswn]=PisChecked;                           //~0308R~
		    showAmmount();	//adjust penalty                       //~0308I~
        	break;                                                 //~0308I~
        }                                                          //~0308I~
        if (swChanged)                                             //~0308I~
        {                                                          //~0308I~
	        CMP.swSent=false;                                      //~0314I~
            swAllOK=false;                                         //~0308I~
            resetRespStat();	//OKNGDlg                          //~0308I~
    		setButton();                                           //~0308I~
        }                                                          //~0308I~
    }                                                              //~0308I~
    //*******************************************************************//~0308I~
    private int getSuspenderEswn(CompoundButton Pbtn)              //~0308I~
    {                                                              //~0308I~
    	int eswn=0;                                                //~0308I~
    	switch (Pbtn.getId())                                      //~0308I~
        {                                                          //~0308I~
        case R.id.cbAbPE:                                          //~0308I~
        	eswn=0;                                                //~0308I~
            break;                                                 //~0308I~
        case R.id.cbAbPS:                                          //~0308I~
        	eswn=1;                                                //~0308I~
            break;                                                 //~0308I~
        case R.id.cbAbPW:                                          //~0308I~
        	eswn=2;                                                //~0308I~
            break;                                                 //~0308I~
        case R.id.cbAbPN:                                          //~0308I~
        	eswn=3;                                                //~0308I~
            break;                                                 //~0308I~
        }                                                          //~0308I~
        if (Dump.Y) Dump.println("SuspendDlg.getSuspenderEswn rc="+eswn);//~0308I~
        return eswn;
    }                                                              //~0308I~
    //*******************************************************************//~9321I~
    //*on UiThread                                                 //~9321I~
    //*******************************************************************//~9321I~
    @Override                                                      //~9321I~
//  protected void updateOKNGAdditional(int PctrNone,int PctrNG,boolean PswAllOK)//~9321I~//~0119R~
    protected void updateOKNGAdditional(int PctrNone,int PctrNG,int PctrDisconnected,boolean PswAllOK)//~0119I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("SuspendDlg.updateOKNGAdditional ctrNG="+PctrNG+",ctrNone="+PctrNone+",swAllOK="+PswAllOK);//~9321I~//~9322R~//~9818R~
        btnTotal.setEnabled(PswAllOK);                             //~9321I~
//      layoutView.invalidate();                                   //~9813R~
//      if (Dump.Y) Dump.println("SuspendDlg.updateOKNGAdditional requestLayout");//~9813R~//~9818R~
//      layoutView.requestLayout();                                //~9813R~
        if (PctrNone==0 && PctrNG!=0) //all responsed, someone replyed NG//~9612I~
        {                                                          //~9612I~
	        if (swRequester)                                       //~9612I~
    	    	alertToForceOK(this,TITLEID,R.string.Alert_SuspendDlgForceOK);//~9612I~//~9818R~
        }                                                          //~9612I~
    }                                                              //~9321I~
    //*******************************************************************//~9612I~
    @Override                                                      //~9612I~
	protected void alertActionReceived(int Pbuttonid,int Prc)      //~9612I~
    {                                                              //~9612I~
        if (Dump.Y) Dump.println("SuspendDlg.alerActionReceived buttonID="+Pbuttonid+",rc="+Prc);//~9612I~//~9818R~
    	if (Pbuttonid==BUTTON_POSITIVE)                            //~9612I~
        {                                                          //~9612I~
	        btnTotal.setEnabled(true/*PswAllOK*/);                 //~9612I~
        }                                                          //~9612I~
    }                                                              //~9612I~
//    //******************************************                   //~9321I~//~9819R~
//    @Override                                                      //~9321I~//~9819R~
//    public void onClickOK()                                        //~9321I~//~9819R~
//    {                                                              //~9321I~//~9819R~
//        if (Dump.Y) Dump.println("SuspendDlg.onClickOK swRequester="+swRequester);//~9321I~//~9322R~//~9818R~//~9819R~
//        if (swRequester)                                           //~9321I~//~9819R~
//        {                                                          //~9322I~//~9819R~
//            sendConfirmRequest();                                  //~9321R~//~9819R~
//        }                                                          //~9322I~//~9819R~
//        else                                                       //~9321I~//~9819R~
//        {                                                          //~9321I~//~9819R~
//            sendReply(true);                                       //~9321I~//~9819R~
//            dismiss();                                          //~9321I~//~9819R~
//        }                                                          //~9321I~//~9819R~
//    }                                                              //~9321I~//~9819R~
    //******************************************                   //~0314I~
    @Override                                                      //~0314I~
    public void onClickOK()                                        //~0314I~
    {                                                              //~0314I~
        if (Dump.Y) Dump.println("SuspendDlg.onClickOK swRequester="+swRequester);//~0314I~
        CMP.swSent=true;                                           //~0314I~
        super.onClickOK();                                         //~0314I~
    }                                                              //~0314I~
//    //******************************************                   //~9321I~//~9819R~
//    @Override                                                      //~9321I~//~9819R~
//    public void onClickCancel()                                    //~9321I~//~9819R~
//    {                                                              //~9321I~//~9819R~
//        if (Dump.Y) Dump.println("SendDlg.onClickNG");             //~9321I~//~9819R~
//        sendReply(false);                                          //~9321I~//~9819R~
//        dismiss     ();                                              //~9321I~//~9819R~
//    }                                                              //~9321I~//~9819R~
//    //******************************************                   //~9321I~//~9819R~
//    @Override                                                      //~9321I~//~9819R~
//    public void onClickOther(int Pbuttonid)                        //~9321I~//~9819R~
//    {                                                              //~9321I~//~9819R~
//        if (Dump.Y) Dump.println("SuspendDlg.onClickOther btnid="+Integer.toHexString(Pbuttonid));//~9321I~//~9322R~//~9818R~//~9819R~
//        switch(Pbuttonid)                                          //~9321I~//~9819R~
//        {                                                          //~9321I~//~9819R~
//            case R.id.FixTotal:                                    //~9321I~//~9819R~
//                onClickTotal();                                    //~9321I~//~9819R~
//                break;                                             //~9321I~//~9819R~
//            case R.id.ShowRule:                                    //~9417I~//~9819R~
//                onClickShowRule();                                 //~9417I~//~9819R~
//                break;                                             //~9417I~//~9819R~
//        }                                                          //~9321I~//~9819R~
//    }                                                              //~9321I~//~9819R~
//    //******************************************                   //~9321I~//~9822R~
//    public void onClickTotal()                                     //~9321I~//~9822R~
//    {                                                              //~9321I~//~9822R~
//        if (Dump.Y) Dump.println("SuspendDlg.onClickTotal");         //~9321I~//~9322R~//~9818R~//~9822R~
//        dismiss();                                              //~9321I~//~9822R~
//        sendEndGame(newTotal);  //TODO                                     //~9321I~//~9819R~//~9822R~
//    }                                                              //~9321I~//~9822R~
    //******************************************                   //~9823I~
    @Override                                                      //~9823I~
    public void onClickTotal()                                     //~9823I~
    {                                                              //~9823I~
        if (Dump.Y) Dump.println("SuspendDlg.onClickTotal");       //~9823I~
        dismiss();                                                 //~9823I~
        suspendGame();                                             //~9823I~
    }                                                              //~9823I~
//    //******************************************                   //~9417I~//~9819R~
//    public void onClickShowRule()                                  //~9417I~//~9819R~
//    {                                                              //~9417I~//~9819R~
//        if (Dump.Y) Dump.println("SuspendDlg.onClickShowRule");   //~9417I~//~9818R~//~9819R~
//        showRule();                                                //~9417I~//~9819R~
//    }                                                              //~9417I~//~9819R~
//    //**************************************************************************//~9318I~//~9322R~
//    //*from CompleteDlg/DrawnLastDlg/DrawnHWDlg                    //~9318I~//~9322R~
//    //**************************************************************************//~9318I~//~9322R~
//    public static void showTotal(int PendgameType,int PnextgameType,int[] Pamt)//~9318I~//~9322R~
//    {                                                              //~9318I~//~9322R~
//        if (!AG.aUserAction.isServer)                              //~9318I~//~9322R~
//        {                                                          //~9318I~//~9322R~
//            sendToServer(PendgameType,PnextgameType,Pamt); //ENDGAME_SCORE_UPDATE//~9319R~//~9322R~
//            return;                                                //~9318I~//~9322R~
//        }                                                          //~9318I~//~9322R~
////      SuspendDlg.showDialog(PendgameType,Pamt,ACC.score,PnextgameType); //minus chk//~9318R~//~9321R~//~9322R~//~9818R~
//        SuspendDlg.newInstance(Pamt,AG.aAccounts.score).show();      //~9321R~//~9322R~//~9818R~
//    }                                                              //~9318I~//~9322R~
//    //************************************************             //~9417I~//~9819R~
//    private void showRule()                                        //~9417I~//~9819R~
//    {                                                              //~9417I~//~9819R~
//        if (Dump.Y) Dump.println("SuspendDlg.showRule");          //~9417I~//~9818R~//~9819R~
//        RuleSetting.showRuleInGame();                              //~9417I~//~9819R~
//    }                                                              //~9417I~//~9819R~
    //**************************************************************************//~9823I~
    //*from OnClickTotal,on Server                                 //~9823R~
    //**************************************************************************//~9823I~
    protected void suspendGame()                          //~9822I~   //~9823R~//~9B01R~
    {                                                              //~9822I~
        suspendOption=rgSuspend.getCheckedID();   //Send may not done when no connection//~9B01I~
        if (Dump.Y) Dump.println("SuspendDlg:suspendGame suspendOption="+suspendOption);//~9823I~
    	switch(suspendOption)                                      //~9823I~
        {                                                          //~9823I~
        case SUSPEND_GAMEOVER: //=0                                //~9823I~
        	doGameOver();                                          //~9823I~
        	break;                                                 //~9823I~
        case SUSPEND_SUSPEND:  //=1;                               //~9823I~
        	doSuspend();                                           //~9823I~
        	break;                                                 //~9823I~
        case SUSPEND_CONTINUE: //=2;                               //~9823I~
        	doContinue();                                          //~9823I~
        	break;                                                 //~9823I~
        }                                                          //~9823I~
        Status.setSuspendGame(false);                              //~v@01I~
    }                                                              //~9822I~
    //**************************************************************************//~9823I~
    protected void doGameOver()                                      //~9823I~//~v@01R~
    {                                                              //~9823I~
        if (Dump.Y) Dump.println("SuspendDlg:doGameOver");         //~9823I~
    	System.arraycopy(newScore,0,lastScore,0,PLAYERS);	//add reachback effect//~v@01I~
        endGame(newTotal);                                         //~9823R~
    }                                                              //~9823I~
    //**************************************************************************//~9823I~
    private void doSuspend()                                      //~9823I~//~v@01R~//~0110R~
    {                                                              //~9823I~
        if (Dump.Y) Dump.println("SuspendDlg:doSuspend");          //~9823I~
//  	System.arraycopy(lastScoreOrg,0,lastScore,0,PLAYERS);	//drop reachback effect//~v@01R~
        suspendGameSuspend();                                      //~9824R~
    }                                                              //~9823I~
    //**************************************************************************//~9823I~
    protected void doContinue()                                      //~9823I~//~v@01R~
    {                                                              //~9823I~
        if (Dump.Y) Dump.println("SuspendDlg:doContinue endgameType="+ACC.endgameTypeNextGame+",ACC.suspendEndgameType="+ACC.nextgameTypeNextGame);//~9823R~//~v@01R~
//  	System.arraycopy(lastScoreOrg,0,lastScore,0,PLAYERS);	//drop reachback effect//~v@01R~
        AG.aStatus.resetSuspendRequest();                          //~9823I~
        ACC.nextGameWithoutSuspended(ACC.endgameTypeNextGame,ACC.nextgameTypeNextGame);//~9823I~
    }                                                              //~9823I~
    //**************************************************************************//~9318I~
    //*from UserAction,GCM_SUSPENDDLG btio msg received                                           //~9318I~//~9320R~//~9322R~//~9822R~//~9826R~
    //**************************************************************************//~9318I~
    public static void onReceived(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~9318R~//~9826R~
    {                                                              //~9318I~
//      int[] total,minusP,minusC,finalS;                          //~9823R~
        int[] total,ctrreach,penalty,finalS;                       //~9823I~
        int[][] intssP;//~9321I~//~9415R~
		String ts;                                                 //~9826I~
        if (Dump.Y) Dump.println("SuspendDlg:onReceived swServer="+PswServer+",PswReceived="+PswReceived+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~9318R~//~9322R~//~9818R~
        int scoreMsgtype=PintParm[POS_SCOREMSGTYPE];               //~9320I~//~9322R~
//        int endgameType=PintParm[POS_ENDGAMETYPE];                 //~9318I~//~9322R~
//        int nextgameType=PintParm[POS_NEXTGAMETYPE];               //~9318I~//~9322R~
        if (PswServer)  //from dealer(client)                      //~9318R~
        {                                                          //~9318I~
        	switch(scoreMsgtype)                                   //~9321I~
            {                                                      //~9321I~
            case SUSPENDGAME_CONFIRM_REPLY:                      //~9321R~//~9819R~
//                int replyEswn=PintParm[POS_REPLAY_ESWN];           //~9321I~//~9430I~//~9822R~
////              if (OKNGDlg.isDealer())                            //~9321I~//~9605R~//~9822R~
//                if (AG.aAccounts.isFirstDealerReal())              //~9605I~//~9822R~
//                {                                                  //~9321I~//~9822R~
//                    if (Utils.isShowingDialogFragment(AG.aSuspendDlg))//~9321I~//~9322R~//~9818R~//~9822R~
//                        AG.aSuspendDlg.repaintOKNG(replyEswn,PintParm[POS_OKNG]!=0);    //callback updateOKNGAdditional//~9321I~//~9322R~//~9818R~//~9822R~
//                }                                                  //~9321I~//~9822R~
//                else                                               //~9321I~//~9822R~
//                {                                                  //~9321I~//~9822R~
//                    String msgdata=SUSPENDGAME_CONFIRM_REPLY+MSG_SEPAPP2+PintParm[POS_OKNG];//~9321I~//~9322R~//~9819R~//~9822R~
////                  AG.aUserAction.sendToTheClientDealer(GCM_SUSPENDDLG,msgdata);//~9321I~//~9322R~//~9430R~//~9822R~
////                  AG.aUserAction.sendToTheClientDealerWithSourceEswn(GCM_SUSPENDDLG,replyEswn,msgdata);//~9430I~//~9606R~//~9822R~
//                    AG.aUserAction.sendToTheClientDealerWithSourceEswnFirstStarter(GCM_SUSPENDDLG,replyEswn,msgdata);//~9606I~//~9822R~
//                }                                                  //~9321I~//~9822R~
                if (UAEndGame.isUpdateAfterSendServer())           //~0314R~
                {                                                  //~0314I~
                    if (Dump.Y) Dump.println("SuspendDlg.onReceivedRequest COMPRESULT RESP updateAfterSend");//~0314R~
                    break;                                         //~0314I~
                }                                                  //~0314I~
                int replyEswn=PintParm[POS_REPLAY_ESWN];           //~9822I~
                if (Utils.isShowingDialogFragment(AG.aSuspendDlg)) //~9822I~
                    AG.aSuspendDlg.repaintOKNG(replyEswn,PintParm[POS_OKNG]!=0);    //callback updateOKNGAdditional//~9822I~
            	break;                                             //~9321I~
              }                                                      //~9321I~//~9822R~
        }                                                          //~9318I~
        else                                                       //~9318I~
        {                                                          //~9318I~
        	switch(scoreMsgtype)                                   //~9321I~
            {                                                      //~9321I~
            case SUSPENDGAME_CONFIRM_REQUEST:                    //~9321I~//~9819R~
////              if (!OKNGDlg.isDealer())                           //~9321I~//~9605R~//~9822R~
//                if (!AG.aAccounts.isFirstDealerReal())             //~9605I~//~9822R~
//                {                                                  //~9321I~//~9822R~
//                    if (Utils.isShowingDialogFragment(AG.aSuspendDlg))//~9322R~//~9818R~//~9822R~
//                    {                                              //~9322I~//~9822R~
//                        AG.aSuspendDlg.swDismissBeforNew=true;    //~9322R~//~9818R~//~9822R~
//                        AG.aSuspendDlg.dismiss();                 //~9322R~//~9818R~//~9822R~
//                    }                                              //~9322I~//~9822R~
////                  int[] score=parseConfirmRequest(PintParm);   //~9321R~//~9322R~//~9415R~//~9822R~
//                    intssP=parseConfirmRequest(PintParm);  //~9415I~//~9822R~
////                  SuspendDlg.newInstance(score).show();//~9321R~       //~9322R~//~9402R~//~9415R~//~9818R~//~9822R~
//                    SuspendDlg.newInstance(intssP).show();        //~9415I~//~9818R~//~9822R~
//                }                                                  //~9321I~//~9822R~
//                else                                               //~9402I~//~9822R~
//                    if (Dump.Y) Dump.println("SuspendDlg.onReceived dealer on client ignored CONFIRM_REQUEST from server");//~9402I~//~9818R~//~9822R~
                  if (Utils.isShowingDialogFragment(AG.aSuspendDlg))//~9822I~
                  {                                                //~9822I~
                      AG.aSuspendDlg.swDismissBeforNew=true;       //~9822I~
                      AG.aSuspendDlg.dismiss();                    //~9822I~
                  }                                                //~9822I~
                  intssP=parseConfirmRequestSuspend(PintParm);     //~9822R~
                  SuspendDlg.newInstance(intssP).show();           //~9822I~
                  break;                                             //~9321I~//~9822R~
            case SUSPENDGAME_CONFIRMED:                          //~9322I~//~9819R~//~9822R~//~9823R~
////              if (!OKNGDlg.isDealer())                           //~9322I~//~9605R~//~9822R~//~9823R~
//                if (!AG.aAccounts.isFirstDealerReal())             //~9605I~//~9822R~//~9823R~
//                {                                                  //~9322I~//~9822R~//~9823R~
//                    total=new int[PLAYERS];                        //~9322I~//~9822R~//~9823R~
//                    minusP=new int[PLAYERS];                       //~9415I~//~9822R~//~9823R~
//                    minusC=new int[PLAYERS];                       //~9415I~//~9822R~//~9823R~
//                    finalS=new int[PLAYERS];                       //~9415I~//~9822R~//~9823R~
//                    System.arraycopy(PintParm,POS_AMMOUNT,total,0,PLAYERS);//~9322I~//~9822R~//~9823R~
//                    System.arraycopy(PintParm,POS_AMMOUNT_MINUSPAY,minusP,0,PLAYERS);//~9415I~//~9822R~//~9823R~
//                    System.arraycopy(PintParm,POS_AMMOUNT_MINUSCHARGE,minusC,0,PLAYERS);//~9415I~//~9822R~//~9823R~
//                    System.arraycopy(PintParm,POS_AMMOUNT_FINALSCORE,finalS,0,PLAYERS);//~9415I~//~9822R~//~9823R~
//                    intssP=new int[][]{total,minusP,minusC,finalS};//~9415I~//~9822R~//~9823R~
////                  endGame(EGDR_MINUSSTOP,total);                 //~9322I~//~9415R~//~9822R~//~9823R~
////                  endGame(EGDR_MINUSSTOP,intssP);                //~9415I~//~9525R~//~9822R~//~9823R~
//                    int egtp=PintParm[POS_ENDGAMETYPE];            //~9525R~//~9822R~//~9823R~
//                    endGame(egtp,intssP);                          //~9525R~//~9822R~//~9823R~
//                }                                                  //~9322I~//~9822R~//~9823R~
//              if (!AG.aAccounts.isFirstDealerReal())             //~9823I~
//              {                                                  //~9823I~
                    total=new int[PLAYERS];                        //~9823I~
                    ctrreach=new int[PLAYERS];                     //~9823I~
                    penalty=new int[PLAYERS];                      //~9823I~
                    finalS=new int[PLAYERS];                       //~9823I~
                    System.arraycopy(PintParm,POS_AMMOUNT,total,0,PLAYERS);//~9823I~
                    System.arraycopy(PintParm,POS_REACH,ctrreach,0,PLAYERS);//~9823I~
                    System.arraycopy(PintParm,POS_PENALTY,penalty,0,PLAYERS);//~9823I~
                    System.arraycopy(PintParm,POS_AMMOUNT_FINALSCORE,finalS,0,PLAYERS);//~9823I~
                    intssP=new int[][]{total,ctrreach,penalty,finalS};//~9823I~
//                  endGame(EGDR_NORMAL,intssP);                   //~9823R~//~9826R~
					ts=History.timestampToFilename(PintParm[POS_DATE],PintParm[POS_TIME]);//~9826R~
                    endGame(ts,EGDR_NORMAL,intssP);                //~9826I~
//              }                                                  //~9823I~
                break;                                             //~9322I~//~9822R~//~9823R~
            case SUSPENDGAME_INTERRUPTED:                          //~9824R~
                total=new int[PLAYERS]; //score                    //~9824M~
                ctrreach=new int[PLAYERS];                         //~9824M~
                int[] gameSeq=new int[PLAYERS];                    //~9824M~
                int[] other=new int[PLAYERS];                      //~v@01I~
                System.arraycopy(PintParm,POS_AMMOUNT,total,0,PLAYERS);//~9824M~
                System.arraycopy(PintParm,POS_REACH,ctrreach,0,PLAYERS);//~9824M~
                System.arraycopy(PintParm,POS_GAMESEQ,gameSeq,0,PLAYERS);//~9824M~
                System.arraycopy(PintParm,POS_OTHER,other,0,PLAYERS);//~v@01R~
//              intssP=new int[][]{total,ctrreach,gameSeq,total};  //~9824M~//~v@01R~
                intssP=new int[][]{total,ctrreach,gameSeq,other};  //~v@01I~
//  			suspendGameSuspend(intssP);	//save to history      //~9824M~//~9826R~
				ts=History.timestampToFilename(PintParm[POS_DATE],PintParm[POS_TIME]);//~9826R~
    			suspendGameSuspend(ts,intssP);	//save to history  //~9826I~
                break;                                             //~9824M~
            }                                                      //~9824I~
        }                                                          //~9318I~
    }                                                              //~9318I~
    //**************************************************************************//~9321I~
//    private static int[][] parseConfirmRequest(int[] PintParm)     //~9321R~//~9322R~//~9415R~//~9822R~
//    {                                                              //~9321I~//~9822R~
//        int pos=POS_AMMOUNT;                                         //~9322R~//~9822R~
//        if (Dump.Y) Dump.println("SuspendDlg:parseConfirmRequest startpos="+pos+",intp="+Arrays.toString(PintParm));//~9321I~//~9322R~//~9818R~//~9822R~
//        int[] score=new int[PLAYERS];                              //~9321I~//~9822R~
//        int[] minusP=new int[PLAYERS];                             //~9415I~//~9822R~
//        int[] minusC=new int[PLAYERS];                             //~9415I~//~9822R~
//        int[] finalS=new int[PLAYERS];                             //~9415R~//~9822R~
//        System.arraycopy(PintParm,pos,score,0,PLAYERS);   pos+=PLAYERS;//~9321I~//~9822R~
//        System.arraycopy(PintParm,pos,minusP,0,PLAYERS);   pos+=PLAYERS;//~9415I~//~9822R~
//        System.arraycopy(PintParm,pos,minusC,0,PLAYERS);   pos+=PLAYERS;//~9415I~//~9822R~
//        System.arraycopy(PintParm,pos,finalS,0,PLAYERS);   pos+=PLAYERS;//~9415R~//~9822R~
//        if (Dump.Y) Dump.println("SuspendDlg:parseConfirmRequest lastScore="+Arrays.toString(score));//~9321I~//~9322R~//~9818R~//~9822R~
//        if (Dump.Y) Dump.println("SuspendDlg:parseConfirmRequest minusP="+Arrays.toString(minusP));//~9415I~//~9818R~//~9822R~
//        if (Dump.Y) Dump.println("SuspendDlg:parseConfirmRequest minusC="+Arrays.toString(minusC));//~9415I~//~9818R~//~9822R~
//        if (Dump.Y) Dump.println("SuspendDlg:parseConfirmRequest finalS="+Arrays.toString(finalS));//~9415R~//~9818R~//~9822R~
//        int[][] rc=new int[][]{score,minusP,minusC,finalS};        //~9415R~//~9822R~
//        return rc;                                              //~9321I~//~9322R~//~9822R~
//    }                                                              //~9321I~//~9822R~
    protected static int[][] parseConfirmRequestSuspend(int[] PintParm)//~9822R~//~v@01R~
    {                                                              //~9822I~
        int pos=POS_SUSPEND_OPT;                                   //~9822R~
        if (Dump.Y) Dump.println("SuspendDlg:parseConfirmRequest startpos="+pos+",intp="+Arrays.toString(PintParm));//~9822I~
        int suspendOption=PintParm[pos++];                         //~9822I~
        int[] score=new int[PLAYERS];                              //~9822I~
        int[] reach=new int[PLAYERS];                              //~9822I~
        int[] penalty=new int[PLAYERS];                            //~9822I~
        int[] finalS=new int[PLAYERS];                             //~9822I~
        System.arraycopy(PintParm,pos,score,0,PLAYERS);   pos+=PLAYERS;//~9822I~
        System.arraycopy(PintParm,pos,reach,0,PLAYERS);   pos+=PLAYERS;//~9822I~
        System.arraycopy(PintParm,pos,penalty,0,PLAYERS);   pos+=PLAYERS;//~9822I~
        System.arraycopy(PintParm,pos,finalS,0,PLAYERS);   pos+=PLAYERS;//~9822I~
        if (Dump.Y) Dump.println("SuspendDlg:parseConfirmRequest suspendOption="+suspendOption);//~9822I~
        if (Dump.Y) Dump.println("SuspendDlg:parseConfirmRequest lastScore="+Arrays.toString(score));//~9822I~
        if (Dump.Y) Dump.println("SuspendDlg:parseConfirmRequest reach="+Arrays.toString(reach));//~9822I~
        if (Dump.Y) Dump.println("SuspendDlg:parseConfirmRequest penalty="+Arrays.toString(penalty));//~9822I~
        if (Dump.Y) Dump.println("SuspendDlg:parseConfirmRequest finalS="+Arrays.toString(finalS));//~9822I~
        int[][] rc=new int[][]{new int[]{suspendOption},score,reach,penalty,finalS};//~9822I~
        return rc;                                                 //~9822I~
    }                                                              //~9822I~
//    //**************************************************************************//~9321I~//~9823I~
////  public static void endGame(int PendgameType,int[] Ptotal/*indexSeq*/)//~9321R~//~9415R~//~9823I~
////  public static void endGame(int PendgameType,int[][] PintssP/*indexSeq*/)//~9415I~//~9605R~//~9823I~
//    private static void endGame(int PendgameType,int[][] PintssP/*indexSeq*/)//~9605I~//~9823I~
//    {                                                              //~9321I~//~9823I~
//        if (Dump.Y) Dump.println("ScoreDlg.endGame type="+PendgameType+",isDealer="+OKNGDlg.isDealer());//~9321I~//~9527R~//~9605R~//~9823I~
////      AG.aAccounts.setScore(Ptotal);                             //~9321R~//~9415R~//~9823I~
//        AG.aAccounts.setScore(PintssP[0]);                         //~9415I~//~9823I~
//        resetGrilledBird(PendgameType);                            //~9501I~//~9503R~//~9823I~
//        AG.aNamePlate.showScore();                                 //~9321I~//~9823I~
////      Complete.newInstance();                                    //~9321I~//~9823I~
//        Status.endGame(PendgameType,NGTP_GAMEOVER);   //reset ctrGame to 0,affect to getCurrentEswn and OKNGDlg.isdealer()                        //~9321I~//~9401R~//~9605R~//~9823I~
////      AG.aStarter.showGameSeq(AG.aAccounts.starterRelativePos);  //~9321I~//~9823I~
//        UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndMinusStop));//~9321I~//~9823I~
//        if (Dump.Y) Dump.println("ScoreDlg.endGame isDealer="+OKNGDlg.isDealer());//~9605I~//~9823I~
////      if (OKNGDlg.isDealer())                                    //~9322I~//~9605R~//~9823I~
//        if (AG.aAccounts.isFirstDealerReal())                               //~9605I~//~9823I~
//        {                                                          //~9415I~//~9823I~
////          AccountsDlg.newInstance(Ptotal).show();               //~9322I~//~9415R~//~9823I~
//            AccountsDlg.newInstance(PintssP).show();               //~9415R~//~9823I~
//        }                                                          //~9415I~//~9823I~
//    }                                                              //~9321I~//~9823I~
    //**************************************************************************//~9321I~//~9823I~
    //*like ScoreDlg.endgame()  by Button                          //~9823I~//~9826R~
    //**************************************************************************//~9823I~
    protected void endGame(int[] PnewTotal)                          //~9823R~//~v@01R~
    {                                                              //~9321I~//~9823I~
        if (Dump.Y) Dump.println("SuspendDlg.endGame type="+endgameType);//~9823R~
        AG.aAccounts.setScore(PnewTotal);                         //~9415I~//~9823I~
//		resetGrilledBird(endgameType);                            //~9501I~//~9503R~//~9823R~
//     	AG.aNamePlate.showScore();	//show AG.aAccounts.score                                 //~9321I~//~9823R~
       	showScoreFinalPoint();  //AccountsDlg                      //~9823I~
    	Status.endGame(endgameType,NGTP_GAMEOVER);   //reset ctrGame to 0,affect to getCurrentEswn and OKNGDlg.isdealer()                        //~9321I~//~9401R~//~9605R~//~9823I~
//  	UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndMinusStop));//~9321I~//~9823I~
//  	UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndSuspendGameover));//~9823I~//~9826R~
//      if (Dump.Y) Dump.println("ScoreDlg.endGame isDealer="+OKNGDlg.isDealer());//~9605I~//~9823I~
//  	if (AG.aAccounts.isFirstDealerReal())                               //~9605I~//~9823I~
//      {                                                          //~9415I~//~9823I~
//      	AccountsDlg.newInstance(PintssP).show();               //~9415R~//~9823I~
//      }                                                          //~9415I~//~9823I~
      	sendEndGame(PnewTotal);                                    //~9823I~
//  	UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndSuspendGameover));//~9826I~//~0303R~
    	UserAction.showInfoAll(0/*opt*/,R.string.Info_GameEndSuspendGameover);//~0303I~
	}                                                              //~9321I~//~9823I~
    //**************************************************************************//~9824I~
    protected void suspendGameSuspend()                              //~9824R~//~v@01R~
    {                                                              //~9824I~
//      AG.aAccounts.setScore(PnewTotal);                          //~9824I~
//  	AG.aNamePlate.showScore();      //show AG.aAccounts.score  //~9824R~
//  	Status.endGame(endgameType,NGTP_GAMEOVER);   //reset ctrGame to 0,affect to getCurrentEswn and OKNGDlg.isdealer()//~9824I~
		Rect r=Status.getGameSeq();                                //~9824I~
	    r.bottom+=AG.aPlayers.ctrReach; //reachctr is not yet set(it will be set at Status.setGameSeq//~9829I~
        if (Dump.Y) Dump.println("SuspendDlg.suspendGameSuspend type="+endgameType+",ctrReach="+AG.aPlayers.ctrReach+",gameSeqReach="+r.bottom);//~9824R~//~1312I~
        int[] gameSeq=new int[]{r.left/*wind*/,r.top/*gamectr*/,r.right/*dupctr*/,r.bottom/*reach ctr*/};//~9824I~
                                                                   //~9829I~
    	int[] score=AG.aAccounts.score;                            //~9824I~
//    	int[] other=new int[]{ACC.endgameTypeNextGame,ACC.nextgameTypeNextGame,0,0};//~v@01I~//~9B01R~
        int birdAndCont=ACC.getBirdAndCont();                      //~9B01I~
      	int[] other=new int[]{ACC.endgameTypeNextGame,ACC.nextgameTypeNextGame,birdAndCont,0};//~9B01I~
//  	int[][] intssP=new int[][]{score,ctrReach/*posSeq*/,gameSeq,score}; //4 entry//~9824I~//~v@01R~
    	int[][] intssP=new int[][]{score,ctrReach/*posSeq*/,gameSeq,other}; //4 entry//~v@01I~
        String[] accountNames=AG.aAccounts.getAccountNamesByPosition();//~9824I~
        String ts=Utils.getTimeStamp(TS_DATE_TIME2); //space between date and time for parseInt//~9826R~
        String fnm=History.timestampToFilename(ts);                //~9826I~
//      AG.aHistory.saveLatestGameInterrupted(accountNames,intssP);//~9824I~//~9826R~
//      sendToAllClientInterrupted(suspendOption,endgameType,intssP);//~9824I~//~9826R~
        AG.aHistory.saveRuleInterrupted();                         //~9826R~
//      sendToAllClientInterruptedRule(fnm);                       //~9826R~
        AG.aHistory.saveLatestGameInterrupted(fnm,accountNames,intssP);//~9826R~
//      sendToAllClientInterrupted(ts,suspendOption,endgameType,intssP);//~9826I~//~v@01R~
        sendToAllClientInterrupted(ts,suspendOption,intssP);       //~v@01I~
//  	UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndSuspend));//~9824M~//~0110R~
//  	UserAction.showInfoAll(IMO_CLIENTSELF/*opt*/,Utils.getStr(R.string.Info_GameEndSuspend));//~0110I~//~0224R~
//  	UserAction.showInfoAll(GMSGOPT_CLIENTSELF/*opt*/,Utils.getStr(R.string.Info_GameEndSuspend));//~0224I~//~0303R~
    	UserAction.showInfoAll(0/*opt*/,R.string.Info_GameEndSuspend);//~0303I~
        AG.aStatus.gameSuspended();  //allow back to top panel     //~0110I~
	}                                                              //~9824I~
    //**************************************************************************//~9824I~
    //*client received                                             //~9826I~
    //**************************************************************************//~9826I~
//  private static void suspendGameSuspend(int[][] PintssP)        //~9824R~//~9826R~
    protected static void suspendGameSuspend(String Ptimestamp,int[][] PintssP)//~9826I~//~v@01R~
    {                                                              //~9824I~
        if (Dump.Y) Dump.println("SuspendDlg.suspendgameSuspend intssP="+Utils.toString(PintssP));//~9824R~
        AG.aHistory.saveRuleInterrupted();                         //~9826I~
        String[] accountNames=AG.aAccounts.getAccountNamesByPosition();//~9824I~
//      AG.aHistory.saveLatestGameInterrupted(accountNames,PintssP);//~9824I~//~9826R~
        AG.aHistory.saveLatestGameInterrupted(Ptimestamp,accountNames,PintssP);//~9826I~
        AG.aStatus.gameSuspended();  //allow back to top panel     //~0110I~
	}                                                              //~9824I~
    //**************************************************************************//~9321I~//~9823I~
    //*from OnClickTotal                                           //~9823I~
    //*like from AccountsDlg.OnClickTotal                                           //~9605I~//~9823I~
    //**************************************************************************//~9605I~//~9823I~
    @Override                                                      //~9823I~
    public void sendEndGame(int[] Ptotal)                          //~9321I~//~9823I~
    {                                                              //~9321I~//~9823I~
//  	int[][] intssP=new int[][]{Ptotal,minusPrize,minusCharge,finalScore};//~9415I~//~9823I~
    	int[][] intssP=new int[][]{Ptotal,ctrReach,intsPenalty,finalScore};//~9823I~
        if (Dump.Y) Dump.println("SuspendDlg.sendEndGame total="+Arrays.toString(Ptotal));//~9525I~//~9823I~
//  	if (!AG.aUserAction.isServer)                              //~9321I~//~9823I~
//      {                                                          //~9321I~//~9823I~
//  		sendToServerEndGame(Ptotal); //ENDGAME_ACCOUNTS_CONFIRMED //~9321R~//~9322R~//~9823I~
//  		endGame(endgameType,intssP);                           //~9525I~//~9823I~
//      	return;                                                //~9321I~//~9823I~
//      }                                                          //~9321I~//~9823I~
//  	endGame(endgameType,intssP);                               //~9525I~//~9823R~
        String[] accountNames=AG.aAccounts.getAccountNamesByPosition();//~9826I~
//      AG.aHistory.saveLatestGameSuspended(accountNames,intssP);  //~9826R~
//      sendToAllClientEndGame(suspendOption,endgameType,intssP);                //~9525I~//~9823R~//~9826R~
        String ts=Utils.getTimeStamp(TS_DATE_TIME2); //space between date and time for parseInt//~9826I~
        String fnm=History.timestampToFilename(ts);                //~9826I~
        AG.aHistory.saveLatestGameSuspended(fnm,accountNames,intssP);//~9826I~
        sendToAllClientEndGame(ts,suspendOption,endgameType,intssP);//~9826I~
    }                                                              //~9321I~//~9823I~
//    //**************************************************************************//~9321I~//~9823I~
////  public static void endGame(int PendgameType,int[] Ptotal/*indexSeq*/)//~9321R~//~9415R~//~9823I~
//    private static void endGame(int PendgameType,int[][] PintssP/*indexSeq*/)//~9415I~//~9501R~//~9823I~
//    {                                                              //~9321I~//~9823I~
//        if (Dump.Y) Dump.println("AccountsDlg:endGame type="+PendgameType);//~9321I~//~9322R~//~9823I~
////      AG.aAccounts.setScore(Ptotal);                             //~9321R~//~9415R~//~9823I~
//        AG.aAccounts.setScore(PintssP[3]);  //finalScore           //~9415I~//~9823I~
//        AG.aNamePlate.showScore();                                 //~9321I~//~9823I~
////      Complete.newInstance();                                    //~9321I~//~9823I~
//        Status.endGame(PendgameType,NGTP_GAMEOVER);                           //~9321I~//~9401R~//~9823I~
////      AG.aStarter.showGameSeq(AG.aAccounts.starterRelativePos);  //~9321I~//~9823I~
//      if (PendgameType==EGDR_MINUSSTOP)                            //~9525R~//~9823I~
//        UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndMinusStop));//~9321I~//~9823I~
//        AG.aStatus.gameOverScoreFixed();                           //~9612I~//~9823I~
//        String[] accountNames=AG.aAccounts.getAccountNamesByPosition();              //~9613I~//~9823I~
//        AG.aHistory.saveLatestGame(accountNames,PintssP);           //~9613I~//~9614R~//~9823I~
//    }                                                              //~9321I~//~9823I~
    //**************************************************************************//~9823I~
    //*received CONFIRNED                                          //~9823I~
    //*newTotal,ctrReach,penalty,finalScore                        //~9823I~
    //**************************************************************************//~9823I~
//  private static void endGame(int PendgameType,int[][] PintssP/*indexSeq*/)//~9823I~//~9826R~
    protected static void endGame(String Pfnm,int PendgameType,int[][] PintssP/*indexSeq*/)//~9826I~//~v@01R~
    {                                                              //~9823I~
        if (Dump.Y) Dump.println("SuspendDlg:endGame type="+PendgameType+",intP="+Utils.toString(PintssP));//~9823I~
        AG.aAccounts.setScore(PintssP[3]);  //finalScore           //~9823I~
//      AG.aNamePlate.showScore();                                 //~9823R~
       	showScoreFinalPoint();  //AccountsDlg                      //~9823I~
        Status.endGame(PendgameType,NGTP_GAMEOVER);                //~9823I~
//    	if (PendgameType==EGDR_MINUSSTOP)                          //~9823I~
//      	UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndMinusStop));//~9823I~
        AG.aStatus.gameOverScoreFixed();                           //~9823I~
        String[] accountNames=AG.aAccounts.getAccountNamesByPosition();//~9823I~
//      AG.aHistory.saveLatestGame(accountNames,PintssP);          //~9823I~
//      AG.aHistory.saveLatestGameSuspended(accountNames,PintssP); //~9823I~//~9826R~
        AG.aHistory.saveLatestGameSuspended(Pfnm,accountNames,PintssP);//~9826I~
    }                                                              //~9823I~
    //*******************************************************      //~9321I~
    //*on requester                                                //~9321I~
    //*******************************************************      //~9321I~//~9822R~
//    public void sendConfirmRequest()                               //~9321R~//~9822R~
//    {                                                              //~9321I~//~9822R~
//        if (Dump.Y) Dump.println("SuspendDlg:sendConfirmRequest");   //~9321I~//~9322R~//~9818R~//~9822R~
//        if (isServer)                                              //~9321I~//~9822R~
////          sendToAllClientConfirm(ammount,lastScore,minusPay,newTotal,payerInfo);//~9321R~//~9322R~//~9822R~
////          sendToAllClientConfirm(lastScore);                     //~9322R~//~9415R~//~9822R~
//            sendToAllClientConfirm(new int[][]{lastScore,minusPrize,minusCharge,finalScore});//~9415R~//~9822R~
//        else                                                       //~9321I~//~9822R~
//            sendToServerConfirm();                                  //~9321I~//~9822R~
//    }                                                              //~9321I~//~9822R~
    @Override                                                      //~9822I~
    public void sendConfirmRequest()                               //~9822I~
    {                                                              //~9822I~
        if (Dump.Y) Dump.println("SuspendDlg:sendConfirmRequest"); //~9822I~
        suspendOption=rgSuspend.getCheckedID();                     //~9820I~//~9821M~//~9822M~
        int[] intsSuspend=new int[PLAYERS];                        //~9822I~
        Utils.boolean_int(true/*Pswb2i*/,swsSuspend,intsSuspend);  //~9822R~
//      sendToAllClientConfirm(suspendOption,new int[][]{lastScoreOrg,intsReachPoint,intsSuspend,finalScore});//~9822I~//~v@01R~
        sendToAllClientConfirm(suspendOption,new int[][]{lastScore,ctrReach,intsSuspend,finalScore});//~v@01I~
    }                                                              //~9822I~
//    //*******************************************************************//~9321I~//~9822R~
//    protected void sendReply(boolean PswOK)                        //~9321I~//~9822R~
//    {                                                              //~9321I~//~9822R~
//        if (Dump.Y) Dump.println("SuspendDlg.sendReply OK="+PswOK);  //~9321R~//~9322R~//~9818R~//~9822R~
////        CMP.setResultOK(requesterEswn,currentEswn,PswOK);        //~9321R~//~9822R~
////        String msg=requesterEswn+MSG_SEPAPP+currentEswn+MSG_SEPAPP+(PswOK ? "1" : "0");//~9321R~//~9822R~
////        ACC.sendToAll(GCM_COMPRESULT_RESP,msg);                  //~9321R~//~9822R~
//        String okng=(PswOK ? "1" : "0");                            //~9321I~//~9822R~
//        if (!isServer)                                             //~9321R~//~9822R~
//            AG.aUserAction.sendToServer(GCM_SUSPENDDLG,PLAYER_YOU,SUSPENDGAME_CONFIRM_REPLY,okng);//~9321R~//~9322R~//~9819R~//~9822R~
//        else                                                       //~9321R~//~9822R~
//        {                                                          //~9321I~//~9822R~
//            String msgdata=SUSPENDGAME_CONFIRM_REPLY+MSG_SEPAPP2+okng;//~9321R~//~9322R~//~9819R~//~9822R~
////          AG.aUserAction.sendToTheClientDealer(GCM_SUSPENDDLG,msgdata);//~9321I~//~9322R~//~9606R~//~9822R~
//            AG.aUserAction.sendToTheClientDealerFirstStarter(GCM_SUSPENDDLG,msgdata);//~9606I~//~9822R~
//        }                                                          //~9321I~//~9822R~
//    }                                                              //~9321I~//~9822R~
    //*******************************************************************//~9822I~
    protected void sendReply(boolean PswOK)                        //~9822I~
    {                                                              //~9822I~
        if (Dump.Y) Dump.println("SuspendDlg.sendReply OK="+PswOK);//~9822I~
        String okng=(PswOK ? "1" : "0");                           //~9822I~
        AG.aUserAction.sendToServer(GCM_SUSPENDDLG,PLAYER_YOU,SUSPENDGAME_CONFIRM_REPLY,okng);//~9822I~
    }                                                              //~9822I~
    //**************************************************************************//~9321I~
    protected void sendToServerConfirm()                             //~9321R~//~v@01R~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("SuspendDlg.sendToServerConfirm");  //~9321I~//~9322R~//~9818R~
//  	String msgdata=makeReqMsg(lastScore);                      //~9322R~//~9415R~
//  	String msgdata=makeReqMsg(new int[][]{lastScore,minusPrize});//~9415R~
    	String msgdata=makeReqMsg(new int[][]{lastScore,minusPrize,minusCharge,finalScore});//~9415R~
        AG.aUserAction.sendToServer(GCM_SUSPENDDLG,PLAYER_YOU,SUSPENDGAME_CONFIRM_REQUEST,msgdata);//~9321R~//~9322R~//~9819R~//~9822R~
    }                                                              //~9321I~
//    //**************************************************************************//~9321I~//~9823R~
//    private void sendToServerEndGame(int[] Ptotal)                 //~9321R~//~9823R~
//    {                                                              //~9321I~//~9823R~
//        if (Dump.Y) Dump.println("SuspendDlg.sendToServerEndGame");  //~9321I~//~9322R~//~9818R~//~9823R~
////      String msg=Ptotal[0]+MSG_SEPAPP+Ptotal[1]+MSG_SEPAPP+Ptotal[2]+MSG_SEPAPP+Ptotal[3];//~9321I~//~9415R~//~9823R~
////      String msg=makeReqMsg(new int[][]{Ptotal,minusPrize,minusCharge,finalScore});//~9415R~//~9525R~//~9823R~
//        String msg=makeReqMsg(new int[][]{Ptotal,minusPrize,minusCharge,finalScore})+MSG_SEPAPP2+endgameType;//~9525R~//~9823R~
//        AG.aUserAction.sendToServer(GCM_SUSPENDDLG,PLAYER_YOU,SUSPENDGAME_CONFIRMED,msg);//~9321I~//~9322R~//~9819R~//~9822R~//~9823R~
//    }                                                              //~9321I~//~9823R~
    //**************************************************************************//~9321I~
    //*total,ctrReach,penalty,finalscore                           //~9823I~
    //**************************************************************************//~9823I~
//  private static void sendToAllClientEndGame(int[] Ptotal)       //~9321I~//~9415R~
//  private static void sendToAllClientEndGame(int[][] PintssP)    //~9415I~//~9525R~
//  private static void sendToAllClientEndGame(int PsuspendOption,int PendgameType,int[][] PintssP)//~9525I~//~9823R~//~9826R~
//  protected static void sendToAllClientEndGame(String Ptimestamp,int PsuspendOption,int PendgameType,int[][] PintssP)//~9826I~//~v@01R~//~9A21R~
    protected        void sendToAllClientEndGame(String Ptimestamp,int PsuspendOption,int PendgameType,int[][] PintssP)//~9A21I~
    {                                                              //~9321I~
//      String msg=Ptotal[0]+MSG_SEPAPP+Ptotal[1]+MSG_SEPAPP+Ptotal[2]+MSG_SEPAPP+Ptotal[3];//~9321I~//~9415R~
//  	String msg=makeReqMsg(PintssP);                            //~9415R~//~9525R~
//  	String msg=makeReqMsg(PintssP)+MSG_SEPAPP2+PendgameType;   //~9525R~//~9823R~
//  	String msg=PsuspendOption+makeReqMsg(PintssP)+MSG_SEPAPP2+PendgameType;//~9823I~//~9826R~
    	String msg=Ptimestamp+MSG_SEPAPP2+PsuspendOption+makeReqMsg(PintssP)+MSG_SEPAPP2+PendgameType;//~9826I~
        if (Dump.Y) Dump.println("SuspendDlg:sendToAllClientEndGame msgdata="+msg);//~9321I~//~9322R~//~9525R~//~9818R~
    	sendToAllClient(SUSPENDGAME_CONFIRMED,msg);             //~9321I~//~9819R~
    }                                                              //~9321I~
    //**************************************************************************//~9824I~
    //*total,ctrReach,penalty,finalscore                           //~9824I~
    //**************************************************************************//~9824I~
//  private static void sendToAllClientInterrupted(int PsuspendOption,int PendgameType,int[][] PintssP)//~9824I~//~9826R~
//  private static void sendToAllClientInterrupted(String Ptimestamp,int PsuspendOption,int PendgameType,int[][] PintssP)//~9826I~//~v@01R~
//  private static void sendToAllClientInterrupted(String Ptimestamp,int PsuspendOption,int[][] PintssP/*score,reach,gameseq,other*/)//~v@01I~//~9A21R~
    private        void sendToAllClientInterrupted(String Ptimestamp,int PsuspendOption,int[][] PintssP/*score,reach,gameseq,other*/)//~9A21I~
    {                                                              //~9824I~
//  	String msg=PsuspendOption+makeReqMsg(PintssP)+MSG_SEPAPP2+PendgameType;//~9824I~//~9826R~
//  	String msg=Ptimestamp+MSG_SEPAPP2+PsuspendOption+makeReqMsg(PintssP)+MSG_SEPAPP2+PendgameType;//~9826I~//~v@01R~
    	String msg=Ptimestamp+MSG_SEPAPP2+PsuspendOption+makeReqMsg(PintssP)+MSG_SEPAPP2;//~v@01I~
        if (Dump.Y) Dump.println("SuspendDlg:sendToAllClientInterrupted msgdata="+msg);//~9824I~
    	sendToAllClient(SUSPENDGAME_INTERRUPTED,msg);              //~9824I~
    }                                                              //~9824I~
//    //**************************************************************************//~9826I~//~9827R~
//    private static void sendToAllClientInterruptedRule(String Pfnm)//~9826I~//~9827R~
//    {                                                              //~9826I~//~9827R~
//        if (Dump.Y) Dump.println("SuspendDlg:sendToAllClientInterruptedRule fnm="+Pfnm);//~9826I~//~9827R~
//        AG.aHistory.sendRule(Pfnm);                                //~9826I~//~9827R~
//    }                                                              //~9826I~//~9827R~
//    //**************************************************************************//~9321I~//~9822R~
////  private static void sendToAllClientConfirm(int[] Pscore)       //~9322R~//~9415R~//~9822R~
//    private static void sendToAllClientConfirm(int[][] PintssP)    //~9415I~//~9822R~
//    {                                                              //~9321I~//~9822R~
//        if (Dump.Y) Dump.println("SuspendDlg:sendToAllClientConfirm");//~9321I~//~9322R~//~9818R~//~9822R~
////      String msg=makeReqMsg(Pscore);            //~9321I~        //~9322R~//~9415R~//~9822R~
//        String msg=makeReqMsg(PintssP);                            //~9415I~//~9822R~
//        sendToAllClient(SUSPENDGAME_CONFIRM_REQUEST,msg); //~9321I~//~9819R~//~9822R~
//    }                                                              //~9321I~//~9822R~
    //**************************************************************************//~9822I~
//  protected static void sendToAllClientConfirm(int PsuspendOption,int[][] PintssP)//~9822I~//~v@01R~//~9A21R~
    protected        void sendToAllClientConfirm(int PsuspendOption,int[][] PintssP)//~9A21I~
    {                                                              //~9822I~
        if (Dump.Y) Dump.println("SuspendDlg:sendToAllClientConfirm suspendOption="+PsuspendOption+",intp="+Utils.toString(PintssP));//~9822I~
        String msg=makeReqMsg(PintssP);                            //~9822I~
//      msg=PsuspendOption+MSG_SEPAPP2+msg;                        //~9822I~//~9826R~
        msg="0:0"/*dummy timestamp*/+MSG_SEPAPP2+PsuspendOption+MSG_SEPAPP2+msg;//~9826I~
        sendToAllClient(SUSPENDGAME_CONFIRM_REQUEST,msg);          //~9822I~
    }                                                              //~9822I~
    //**************************************************************************//~9321I~
//  protected static void sendToAllClient(int PendgameMsgid,String Pmsgdata)//~9321I~//~v@01R~//~9A21R~
    protected        void sendToAllClient(int PendgameMsgid,String Pmsgdata)//~9A21I~
    {                                                              //~9321I~
    	int eswn=Accounts.playerToEswn(PLAYER_YOU);            //~9321I~
        String msgdata=eswn+MSG_SEPAPP2+PendgameMsgid+MSG_SEPAPP2+Pmsgdata;//~9321I~//~9322R~//~9402R~
//      String msgdata=eswn+MSG_SEPAPP2+Pmsgdata;                  //~9322I~//~9402R~
        if (Dump.Y) Dump.println("SuspendDlg:sendToAllClient msgdata="+msgdata);//~9321I~//~9322R~//~9818R~
        AG.aUserAction.sendToClient(true/*swAll*/,false/*swRobot*/,GCM_SUSPENDDLG,PLAYER_YOU,msgdata);//~9321I~//~9322R~//~9822R~
    }                                                              //~9321I~
//    //*******************************************************      //~9322I~//~9822R~
////  private static String makeReqMsg(int[] Pscore)                  //~9322I~//~9415R~//~9822R~
//    public static String makeReqMsg(int[][] PintssP)               //~9415R~//~9822R~
//    {                                                              //~9322I~//~9822R~
//        StringBuffer sb=new StringBuffer();                        //~9322I~//~9822R~
////        int[] score=PintssP[0];                                  //~9415R~//~9822R~
////        for (int ii=0;ii<PLAYERS;ii++)                             //~9322I~//~9415R~//~9822R~
//////          sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Pscore[ii]);     //eswn seq//~9322I~//~9415R~//~9822R~
////            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+score[ii]);     //eswn seq//~9415R~//~9822R~
////        int[] minusP=PintssP[1];                                 //~9415R~//~9822R~
////        for (int ii=0;ii<PLAYERS;ii++)                           //~9415R~//~9822R~
////            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+minusP[ii]);     //eswn seq//~9415R~//~9822R~
//        for (int jj=0;jj<PintssP.length;jj++)                       //~9415I~//~9822R~
//        {                                                          //~9415I~//~9822R~
//            for (int ii=0;ii<PLAYERS;ii++)                         //~9415I~//~9822R~
//                sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+PintssP[jj][ii]);     //eswn seq//~9415I~//~9822R~
//        }                                                          //~9415I~//~9822R~
//        String s=sb.toString();                                    //~9322I~//~9822R~
//        if (Dump.Y) Dump.println("SuspendDlg.makeReqMsg msg="+s); //~9322I~//~9818R~//~9822R~
//        return s;                                                  //~9322I~//~9822R~
//    }                                                              //~9322I~//~9822R~
    //**************************************************************************//~9322I~
    //*from GC by btn click                                        //~9322I~
    //**************************************************************************//~9322I~
    public static boolean showDismissed()                             //~9823M~//~v@01R~
    {                                                              //~9823M~
        if (Dump.Y) Dump.println("SuspendDlg.showDismissed");      //~9823M~
        boolean rc=false;	//dismiss menudlg                      //~v@01R~
		if (Utils.isShowingDialogFragment(AG.aSuspendDlg))         //~9823I~
        {                                                          //~9823I~
	        if (Dump.Y) Dump.println("SuspendDlg:showDismisswd already shown");//~9823I~
        	return false;                                                //~9823I~//~v@01R~
        }                                                          //~9823I~
//      if (AG.aStatus.ctrSuspendRequest!=0)                       //~9823M~//~0304R~
        if (AG.aStatus.isSuspendRequested())                       //~0304R~
        {                                                          //~9823M~//~0304R~
 	     	if (!Status.isSuspendGame())                          //~v@01I~//~0304R~
    	      	UView.showToast(R.string.Err_SuspendDlgNotNextGameTiming);//~v@01I~//~0304R~
            else                                                   //~v@01I~//~0304R~
		  	if (AG.aUserAction.isServer)                           //~9823M~
            {                                                      //~v@01I~
	        	SuspendDlg.newInstance().show();                   //~9823M~
                rc=true;                                           //~v@01I~
            }                                                      //~v@01I~
            else                                                   //~9823M~
            	UView.showToast(R.string.Err_SuspendDlgNotServer); //~9823M~//~v@01R~
        }                                                          //~9823M~//~0304R~
        else                                                       //~9823M~//~0304R~
            UView.showToast(R.string.Err_SuspendDlgNoSuspendRequest);//~9823M~//~v@01R~//~0304R~
        return rc;                                                 //~v@01I~
    }                                                              //~9823M~
}//class                                                           //~v@@@R~
