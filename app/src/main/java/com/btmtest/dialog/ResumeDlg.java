//*CID://+DATER~:                             update#= 1188;       //~v@@@R~//~9211R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.app.Dialog;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~9305I~

import com.btmtest.BT.Members;
import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.Complete;
import com.btmtest.game.GConst;
import com.btmtest.game.History;
import com.btmtest.game.HistoryData;
import com.btmtest.game.ScoreSort;
import com.btmtest.game.Status;
import com.btmtest.game.UserAction;
import com.btmtest.game.gv.GMsg;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.URadioGroup;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.EventCB;
import com.btmtest.utils.Prop;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.History.*;
import static com.btmtest.game.HistoryData.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.utils.Alert.*;
import static com.btmtest.utils.Utils.*;

public class ResumeDlg  extends OKNGDlg //UFDlg                                            //~9312R~//~9321R~//~9322R~//~9828R~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.resumedlg;              //~9312R~//~9322R~//~9828R~
    private static final int TITLEID=R.string.Title_ResumeDlg;//~9307I~//~9312R~//~9322R~//~9828R~
    private static final String HELPFILE="ResumeDlg";                //~9220I~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9828R~
                                                                   //~9318I~
    //*CONFIRM_REPLY                                               //~9831I~
    private static final int RDPOS_DEVICENAME=0;                   //~9831R~
    private static final int RDPOS_MSGID=1;                        //~9831R~
    private static final int RDPOS_SUBMSGID=2;                     //~9831R~
    private static final int RDPOS_OKNG=3;                         //~9831R~
    private static final int RDPOS_TIMESTAMP=4;                    //~9831R~
                                                                   //~9318I~
    private static final int RESUMEDLG_CONFIRM_REQUEST=1;   //~9322R~//~9830R~
    private static final int RESUMEDLG_CONFIRM_REPLY=2;    //~9322I~//~9830R~
    private static final int RESUMEDLG_CONFIRMED=3;         //~9322I~//~9830R~
//  private static final int[] rbIDSamePoint=new int[]{R.id.rbSamePointEswn,R.id.rbSamePointSpritCut,R.id.rbSamePointSpritUp};//~9401I~//~9407R~
                                                                   //~9322I~
//  private TextView[] tvsScore,tvsTotal,tvsBotCharge;                       //~9312I~//~9316R~//~9321R~//~9322R~//~9429R~//~9818R~
    protected TextView[] tvsScore,tvsTotal,tvsBotCharge;           //~9818I~
//  private TextView[] tvsName,tvsEswn;    //~9309R~               //~9312R~//~9818R~
    protected TextView[] tvsName,tvsEswn,tvsPos;                          //~9818I~//~9831R~
//  private int[] lastScore,topPrize=new int[PLAYERS],orderPrize=new int[PLAYERS];//~9322R~//~9819R~
    protected int[] lastScore;                                     //~9819I~
//  private int[] topPrize=new int[PLAYERS],orderPrize=new int[PLAYERS];//~9819I~//~9821R~
//  protected int[] topPrize=new int[PLAYERS],orderPrize=new int[PLAYERS];//~9821I~//~9828R~
//  private int[] newTotal=new int[PLAYERS];                       //~9312I~//~9819R~
    protected int[] newTotal=new int[PLAYERS];                     //~9819I~
//  private int[] minusPrize,minusCharge;                          //~9415R~//~9819R~
//  protected int[] minusPrize,minusCharge;                        //~9819I~//~9828R~
    private String[] accountNames;                                 //~9312I~
                                                                   //~9320I~
//  private TextView[] tvsOrder,tvsTopPrize,tvsOrderPrize,tvsLabelBotCharge;         //~9322R~//~9818R~
//    protected TextView[] tvsOrder,tvsTopPrize,tvsOrderPrize,tvsLabelBotCharge;//~9818I~//~9819R~
//  protected TextView[] tvsOrder,tvsTopPrize,tvsOrderPrize;       //~9819I~//~9831R~
    protected TextView[] tvsCtrReachStick;                         //~9831I~
    protected TextView tvLabelBotCharge;                           //~9819I~
//  private TextView[] tvsMinusPrize,tvsMinusCharge,tvsFinalScore,tvsFinalPoint; //~9415R~//~9416R~//~9818R~
    protected TextView[] tvsMinusPrize,tvsMinusCharge,tvsFinalScore,tvsFinalPoint;//~9818I~
//  private UCheckBox[][] cbssPayTo=new UCheckBox[PLAYERS][PLAYERS];//~9320I~//~9818R~
    protected UCheckBox[][] cbssPayTo=new UCheckBox[PLAYERS][PLAYERS];//~9818I~
//  private LinearLayout[] llsMinusPrize,llsMinusCharge,llsBotCharge;           //~9416R~//~9429R~//~9818R~
    protected LinearLayout[] llsMinusPrize,llsMinusCharge,llsBotCharge;//~9818I~
//  private Button btnTotal/*,btnShowRule*/;                           //~9417I~//~9615R~//~9818R~
    protected Button btnTotal/*,btnShowRule*/;                     //~9818I~
    private boolean /*swReceived,*/swMinusPayGetAllPoint;                                    //~9321R~//~9415R~//~9615R~
//  protected Accounts ACC;                                          //~9322I~//~9821R~//~9828R~
//  private Complete CMP;                                          //~9322I~//~9828R~
    protected boolean swInitLayout,swOrderByEswn; //,swSamePointCut;                    //~9322R~//~9407R~//~9822R~
    private int[] payerInfo=new int[PLAYERS*PLAYERS];              //~9322I~
    private boolean swDismissBeforNew;                             //~9322I~
    protected int[] settingOrderPrize;                               //~9322I~//~9822R~
    protected int settingTopPrize;                                   //~9322I~//~9822R~
//  private int[] idx2Order,order2Idx;                             //~9322I~//~9821R~
    protected int[] idx2Order,order2Idx;                           //~9821I~
//  private int[] accountEswn;                                     //~9322I~//~9615R~
//  private int[] finalScore=new int[PLAYERS];                     //~9819R~
    protected int[] finalScore=new int[PLAYERS];                   //~9819I~
    protected int[] finalPoint=new int[PLAYERS];//~9415I~          //~9821R~
    protected String[] finalPoint1000=new String[PLAYERS];                 //~9614I~//~9615R~//~9821R~
    protected	int initialScore,debt,adjustFinalPoint;                                 //~9401I~//~9416R~//~9821R~
    protected	int robotPayType;                                      //~9429I~//~9822R~
    private	int birdPay;                                           //~9501I~
    protected	boolean swBird,swBirdPayToEach;                                        //~9501I~//~9602R~//~9821R~
    protected	int endgameType=EGDR_MINUSSTOP;                        //~9525I~//~9823R~
    private	int typeClose;                                         //~9525I~
    private	boolean swServer;                                      //~9828I~
    private Prop propResume,propCurrent;                           //~9828I~
    private	int[] ctrReach,gameSeq,other;                                //~9828I~//~9830R~
    private HistoryData resumeHD;                                  //~9828R~//~9830R~
    private Prop saveProp;                                         //~9830I~
    private int[] currentEswnByPosition;                           //~9828I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public ResumeDlg()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9828R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("ResumeDlg.defaultConstructor"); //~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9828R~
        AG.aResumeDlg=this;                                         //~9321I~//~9322R~//~9828R~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
//  public static ResumeDlg newInstance(int[] Pscore)             //~9312R~//~9321R~//~9322R~//~9415R~//~9828R~
//  public static ResumeDlg newInstance(int[][] PintssP)         //~9415I~//~9828R~
    public static ResumeDlg newInstance(boolean PswServer,HistoryData Phds)//~9828R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("ResumeDlg.newInstance swServer="+PswServer+",scores="+Utils.toString(Phds.scores));//~9828R~
    	ResumeDlg dlg=new ResumeDlg();                                     //~v@@@I~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9828R~
    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9227R~
    			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_CLOSEBTN|FLAG_HELPBTN|FLAG_RULEBTN,//~v@@@I~//~9220R~//~9305R~//~9312R~//~9316R~//~9321R~//~9708R~//~9828R~
				TITLEID,HELPFILE);         //~v@@@I~               //~9220R~
        dlg.swServer=PswServer; //point at last game(not total score)//~9828I~
        dlg.resumeHD=Phds;                                         //~9828R~
        int[][] scores=Phds.scores;                              //~9828I~
        dlg.lastScore=scores[HDPOS_SCORES_SCORE]; //0,point at last game(not total score)//~9415I~//~9828R~//~9830R~
//      dlg.minusPrize=PintssP[1];	//point at last game(not total score)//~9415I~//~9828R~
//      dlg.minusCharge=PintssP[2];	//point at last game(not total score)//~9415I~//~9828R~
        dlg.ctrReach=scores[HDPOS_SCORES_REACH];	//1            //~9830R~
        dlg.gameSeq=scores[HDPOS_SCORES_GAMESEQ];	//2            //~9830R~
        dlg.other=scores[HDPOS_SCORES_OTHER];	//3                //~9830I~
	    Status.resetEndgameSomeone();   //allow BTIOThread out; for the case recevied restart from history from client//~0111I~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
//    //******************************************                   //~9525I~//~9828R~
//    public static ResumeDlg newInstance(int[][] PintssP,int PendgameType,int PtypeClose)//~9525I~//~9828R~
//    {                                                              //~9525I~//~9828R~
//        if (Dump.Y) Dump.println("ResumeDlg.newInstance endgameType="+PendgameType+",typeClose="+Integer.toHexString(PtypeClose));//~9525I~//~9828R~
//        ResumeDlg dlg=newInstance(PintssP);                      //~9525I~//~9828R~
//        dlg.endgameType=PendgameType;                              //~9525I~//~9828R~
//        dlg.typeClose=PtypeClose;                                  //~9525I~//~9828R~
//        return dlg;                                                //~9525I~//~9828R~
//    }                                                              //~9525I~//~9828R~
    //******************************************                   //~9828I~
    @Override                        //OKNGDlg                     //~9828I~
    protected void setupValueOKNG()	//befire initLayoutAdditional  //~9828I~
    {                                                              //~9828I~
        if (Dump.Y) Dump.println("ResumeDlg.setupValueOKNG");      //~9828I~
        androidDlg=getDialog();                                    //~9828I~
//        currentEswn=Accounts.getCurrentEswn();                   //~9828I~
//        eswnRequester=getEswnRequester(currentEswn);             //~9828I~
//        swRequester=currentEswn==eswnRequester;                  //~9828I~
//        isServer=AG.aAccounts.isServer();                        //~9828I~
		setupResumeGame();	                                       //~9828I~
        llEswnResponse=(LinearLayout)UView.findViewById(layoutView,R.id.eswnResponseLine);//~9828I~
    }                                                              //~9828I~
    //******************************************                   //~9828I~
    private void setupResumeGame()                                 //~9828I~
    {                                                              //~9828I~
        if (Dump.Y) Dump.println("ResumeDlg.setupResumeGame");     //~9828I~
        currentEswnByPosition=History.getCurrentEswn(resumeHD);    //~9828R~
        resumeHD.setupMembers();	//set idx to MD                //~9828I~
    }                                                              //~9828I~
    //******************************************                 //~9303R~//~9312R~
    @Override                                                    //~9303R~//~9305R~
    protected void initLayoutAdditional(View PView)                            //~v@@@I~//~9303R~//~9305R~//~9321R~
    {                                                              //~v@@@M~//~9303R~//~9305R~
        if (Dump.Y) Dump.println("ResumeDlg.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9303R~//~9304R~//~9305R~//~9307R~//~9312R~//~9322R~//~9828R~
//      swRequester=AG.aAccounts.isFirstDealerReal();              //~9605M~//~9828R~
        swRequester=swServer;                                      //~9828I~
        swByPosition=true;  //OKNGDialog                           //~9606I~
        swInitLayout=true;                                         //~9322I~
//      getRuleSetting();                                          //~9320I~//~9829R~
	    showRuleSetting(PView);                                    //~9401I~
        setupAmmount(PView);                                       //~9312I~
        setGameStatus(PView);                                      //~9829I~
        setAccountName();                                          //~9309I~
	    showAmmount();                                             //~9309I~
        swInitLayout=false;                                        //~9322I~
//	    saveLast();                                                //~9322M~
//  	setTitle();                                                //~9822I~//~9829R~
        hideResponseEswn();                                        //~0218I~
    }                                                              //~v@@@M~//~9303R~//~9305R~
    //******************************************                   //~0218I~
    private void hideResponseEswn()                                //~0218I~
    {                                                              //~0218I~
    	if (Dump.Y) Dump.println("SuspendDlg.hideResponseEswn");   //~0218I~
        hideResponseEswn(!swRequester);                            //~0218I~
    }                                                              //~0218I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    protected void setupValueAdditional(View PView)                //~9321I~
    {                                                              //~9321I~
    	if (Dump.Y) Dump.println("ResumeDlg.initEnv");              //~9321I~//~9322R~//~9828R~
//      ACC=AG.aAccounts;                                          //~9322I~//~9828R~
//      CMP=AG.aComplete;                                          //~9322I~//~9828R~
//  	accountNames=ACC.getAccountNamesByPosition();              //~9416I~//~9828R~
        btnTotal        =              UButton.bind(PView,R.id.FixTotal,this);//~9321M~
                                       UButton.bind(PView,R.id.ShowRule,this);//~9615I~
    }                                                              //~9322I~
    //******************************************                   //~9818R~
    @Override //UFDlg                                              //~9818R~
    protected int getDialogWidth()                                 //~9818R~
    {                                                              //~9818R~
        int ww=getDialogWidthSmallDevicePortraitFixedRate();       //~9828R~
        if (Dump.Y) Dump.println("ResumeDlg.getDialogWidth ww="+ww+",portrait="+AG.portrait+",smallDevice="+AG.swSmallDevice);//~9818R~//~9828R~
        return ww;                                                 //~9818R~
    }                                                              //~9818R~
//    //******************************************                   //~9322I~//~9828R~
//    protected void saveLast()                                       //~9322I~//~9819R~//~9828R~
//    {                                                              //~9322I~//~9828R~
//        CMP.lastScore=lastScore;    //save for reshow              //~9322R~//~9402R~//~9415R~//~9828R~
//        CMP.minusPrize=minusPrize;  //save for reshow              //~9415I~//~9828R~
//        CMP.minusCharge=minusCharge;    //save for reshow          //~9415I~//~9828R~
//        CMP.finalScore=finalScore;  //save for reshow              //~9415I~//~9828R~
//        CMP.pos2Order=idx2Order;                                   //~9520I~//~9828R~
//    }                                                              //~9321I~//~9828R~
//    //******************************************                   //~v@@@I~//~9220R~//~9303R~//~9306R~//~9829R~
//    protected void setTitle()                                        //~v@@@I~//~9220R~//~9303R~//~9306R~//~9829R~
//    {                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~//~9829R~
//        Spanned s=Status.getSpannedGameTitle(Utils.getStr(TITLEID));//~9306I~//~9829R~
//        androidDlg.setTitle(s);                                    //~v@@@R~//~9220R~//~9302R~//~9303R~//~9306R~//~9829R~
//    }                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~//~9829R~
//    //******************************************                   //~9320I~//~9829R~
//    protected void getRuleSetting()                                  //~9320I~//~9819R~//~9829R~
//    {                                                              //~9320I~//~9829R~
//        initialScore=RuleSetting.getInitialScore();            //~9322R~//~9401R~//~9829R~
//        debt=RuleSetting.getDebt();                            //~9322I~//~9401R~//~9829R~
////        settingTopPrize=ACC.topPrize;                              //~9428R~//~9828R~//~9829R~
////        settingOrderPrize=RuleSetting.getOrderPrize();              //~9322R~//~9828R~//~9829R~
////        swOrderByEswn=RuleSetting.isOrderByEswn();                 //~9322I~//~9828R~//~9829R~
////        swMinusPayGetAllPoint=RuleSetting.isMinusPayGetAllPoint(); //~9415I~//~9828R~//~9829R~
////        adjustFinalPoint=RuleSetting.getScoreToPoint();            //~9416I~//~9828R~//~9829R~
////        robotPayType=RuleSetting.getRobotPayType();                //~9429I~//~9828R~//~9829R~
////        swBird=ACC.isGrillBird();                                  //~9501I~//~9828R~//~9829R~
////        birdPay=RuleSetting.getGrilledBirdPay();                   //~9501I~//~9828R~//~9829R~
////        swBirdPayToEach=RuleSetting.isBirdPayToEach();             //~9602I~//~9828R~//~9829R~
//    }                                                              //~9320I~//~9829R~
    //******************************************                   //~9830I~
    private void switchProp(HistoryData Phd)                       //~9830I~
    {                                                              //~9830I~
    	if (Dump.Y) Dump.println("ResumeDlg.switchProp Phd="+Utils.toString(Phd));//~9830R~
    	if (Phd!=null)                                            //~9830I~
        {                                                          //~9830I~
            saveProp=AG.ruleProp;                                  //~9830I~
            Prop P=Phd.getRuleProp();                              //~9830R~
            if (P!=null)                                           //~9830I~
                AG.ruleProp=P;                                     //~9830I~
        }                                                          //~9830I~
        else                                                       //~9830I~
        	if (saveProp!=null)                                    //~9830I~
		        AG.ruleProp=saveProp;                              //~9830R~
    }                                                              //~9830I~
    //******************************************                   //~9401I~
    protected void showRuleSetting(View PView)                     //~9401I~
    {                                                              //~9401I~
    	switchProp(resumeHD);                                      //~9830I~
                                                                   //~9829I~
        TextView tv;                                               //~9401I~
        String s;                                                  //~9401I~
        tv=(TextView)    UView.findViewById(PView,R.id.SettingTopPrize);//~9401R~
        s=AG.resource.getString(R.string.Label_AccountsSettingTopPrizeEdit,(debt-initialScore)*PLAYERS,initialScore,debt);//~9401R~//~9407R~
    	if (Dump.Y) Dump.println("ResumeDlg.showRuleSetting topprize="+s);//~9401I~//~9828R~
        tv.setText(s);                                             //~9401I~
        RuleSetting.setInitialScore(PView,true/*swFixed*/);        //~9829I~
        RuleSetting.setOrderPrize(PView,true/*swFixed*/);          //~9819I~
        RuleSetting.setGrillBird(PView,true/*swFixed*/);           //~9819I~
        RuleSetting.setRobotOption(PView,true/*swFixed*/);         //~9823I~
//      RuleSetting.setScoreToPoint(PView,true);                   //~9416I~//~9607R~//~9828R~
                                                                   //~9828I~
        RuleSetting.setMinusStop(PView,true/*swFixed*/);           //~9828I~
        RuleSetting.setDora(PView,true/*swFixed*/);                //~9828I~
        RuleSetting.setSpritPos(PView,true/*swFixed*/);            //~9828I~
                                                                   //~9829I~
    	switchProp(null);                                          //~9830I~
    }                                                              //~9401I~
    //******************************************                   //~9309I~
    protected void setupAmmount(View PView)                        //~9309I~
    {                                                              //~9309I~
        LinearLayout[] lls=new LinearLayout[PLAYERS];              //~9321I~
//      llsResult=lls;                                             //~9820I~//~9821R~
        lls[0]=(LinearLayout)    UView.findViewById(PView,R.id.llResult1);//~9309I~//~9321R~//~9322R~
        lls[1]=(LinearLayout)    UView.findViewById(PView,R.id.llResult2);//~9321I~//~9322R~
        lls[2]=(LinearLayout)    UView.findViewById(PView,R.id.llResult3);//~9321I~//~9322R~
        lls[3]=(LinearLayout)    UView.findViewById(PView,R.id.llResult4);//~9321I~//~9322R~
//        tvsOrder=new TextView[PLAYERS];                             //~9321I~//~9322R~//~9828R~
    	tvsName=new TextView[PLAYERS];                             //~9321I~
    	tvsEswn=new TextView[PLAYERS];                             //~9401I~
    	tvsPos=new TextView[PLAYERS];                              //~9831I~
//        tvsScore=new TextView[PLAYERS];                            //~9321I~//~9828R~
//        tvsTopPrize=new TextView[PLAYERS];                              //~9321I~//~9322R~//~9828R~
//        tvsOrderPrize=new TextView[PLAYERS];                       //~9322I~//~9828R~
    	tvsCtrReachStick=new TextView[PLAYERS];                            //~9321I~//~9831R~
    	tvsTotal=new TextView[PLAYERS];                            //~9831I~
//        tvsMinusPrize=new TextView[PLAYERS];                       //~9415I~//~9828R~
//        tvsMinusCharge=new TextView[PLAYERS];                      //~9415I~//~9828R~
//        tvsFinalScore=new TextView[PLAYERS];                       //~9415I~//~9828R~
//        tvsFinalPoint=new TextView[PLAYERS];                       //~9416I~//~9828R~
//        tvsBotCharge=new TextView[PLAYERS];                        //~9429I~//~9828R~
//        tvLabelBotCharge=(TextView)     UView.findViewById(PView,R.id.Label_ForBotCharge);//~9819I~//~9828R~
//        llsMinusPrize=new LinearLayout[PLAYERS];                   //~9416I~//~9828R~
//        llsMinusCharge=new LinearLayout[PLAYERS];                  //~9416I~//~9828R~
//        llsBotCharge=new LinearLayout[PLAYERS];                    //~9429I~//~9828R~
        for (int ii=0;ii<PLAYERS;ii++)                                 //~9321I~
        {                                                          //~9321I~
        	LinearLayout ll=lls[ii];                               //~9321I~
//            tvsOrder[ii]=(TextView)    UView.findViewById(ll,R.id.Order);//~9321I~//~9322R~//~9828R~
	        tvsEswn[ii]=(TextView)    UView.findViewById(ll,R.id.currentEswn);//~9401I~//~9828R~
	        tvsPos[ii]=(TextView)    UView.findViewById(ll,R.id.positionEswn);//~9831I~
	        tvsName[ii]=(TextView)    UView.findViewById(ll,R.id.memberName);//~9321I~
//            tvsScore[ii]=(TextView)     UView.findViewById(ll,R.id.Score);//~9321I~//~9322R~//~9828R~
//            tvsTopPrize[ii]=(TextView)     UView.findViewById(ll,R.id.TopPrize);//~9321I~//~9322R~//~9828R~
//            tvsOrderPrize[ii]=(TextView)     UView.findViewById(ll,R.id.OrderPrize);//~9321I~//~9322R~//~9828R~
	        tvsCtrReachStick[ii]=(TextView)     UView.findViewById(ll,R.id.tvCtrReachStick);//~9831R~
	        tvsTotal[ii]=(TextView)     UView.findViewById(ll,R.id.Total);//~9322I~
//            tvsMinusPrize[ii]=(TextView)     UView.findViewById(ll,R.id.minusPrize);//~9415I~//~9828R~
//            tvsMinusCharge[ii]=(TextView)     UView.findViewById(ll,R.id.minusCharge);//~9415I~//~9828R~
//            tvsFinalScore[ii]=(TextView)     UView.findViewById(ll,R.id.finalScore);//~9415I~//~9828R~
//            tvsFinalPoint[ii]=(TextView)     UView.findViewById(ll,R.id.finalPoint);//~9416I~//~9828R~
//            llsMinusPrize[ii]=(LinearLayout)     UView.findViewById(ll,R.id.llMinusPrize);//~9416I~//~9828R~
//            llsMinusCharge[ii]=(LinearLayout)     UView.findViewById(ll,R.id.llMinusCharge);//~9416I~//~9828R~
//            llsBotCharge[ii]=(LinearLayout)     UView.findViewById(ll,R.id.llForBotCharge);//~9429I~//~9828R~
//            tvsBotCharge[ii]=(TextView)     UView.findViewById(ll,R.id.tvForBot);//~9429I~//~9828R~
        }                                                          //~9321I~
    }                                                              //~9309I~
    //******************************************                   //~9829I~
    protected void setGameStatus(View PView)                       //~9829R~
    {                                                              //~9829I~
    	if (Dump.Y) Dump.println("ResumeDlg.setGameStatus");       //~9829I~
        TextView tv;                                               //~9829I~
                                                                   //~9829I~
        String setType=resumeHD.HD[HDPOS_HDR][POS_GAMESET];        //~9829I~
        tv=(TextView)UView.findViewById(PView,R.id.tvGameSetType); //~9829I~
        tv.setText(setType);                                       //~9829I~
                                                                   //~9829I~
        int ctrSet=resumeHD.scores[HDPOS_SCORES_GAMESEQ][POS_GAMESEQ_CTRSET];//~9829I~
        int ctrGame=resumeHD.scores[HDPOS_SCORES_GAMESEQ][POS_GAMESEQ_CTRGAME];//~9829I~
        int ctrDup=resumeHD.scores[HDPOS_SCORES_GAMESEQ][POS_GAMESEQ_CTRDUP];//~9829I~//~9902R~
	    String round=Status.getStringGameSeq(ctrSet,ctrGame,ctrDup);       //~9829I~
        tv=(TextView)UView.findViewById(PView,R.id.tvInterruptedGame);//~9829I~
        tv.setText(round);                                         //~9829I~
                                                                   //~9829I~
        tv=(TextView)UView.findViewById(PView,R.id.tvNextGameType);//~9829I~
        int intngtp=resumeHD.scores[HDPOS_SCORES_OTHER][POS_OTHER_NEXTGAME];//~9829I~
        switch (intngtp)                                           //~9829I~
        {                                                          //~9829I~
        case NGTP_CONTINUE:                                        //~9829I~
	        tv.setText(R.string.Label_Drawn_Continue);             //~9829I~
            break;                                                 //~9829I~
        case NGTP_NEXT:                                            //~9829I~
	        tv.setText(R.string.Label_Drawn_Next);                 //~9829I~
            break;                                                 //~9829I~
        case NGTP_RESET:                                           //~9905I~
	        tv.setText(R.string.Label_Drawn_Reset);                //~9905I~
            break;                                                 //~9905I~
        default:                                                   //~9829I~
	        tv.setText("");                                        //~9829I~
        }                                                          //~9829I~
                                                                   //~9829I~
        int ctrReach=resumeHD.scores[HDPOS_SCORES_GAMESEQ][POS_GAMESEQ_CTRREACH];//~9829I~
        tv=(TextView)UView.findViewById(PView,R.id.tvCtrReachStickTotal);//~9829I~//~9831R~
        tv.setText(Integer.toString(ctrReach));                    //~9829I~
                                                                   //~9829I~
        String ruleID=resumeHD.HD[HDPOS_HDR][POS_RULEID];          //~9829I~
        tv=(TextView)UView.findViewById(PView,R.id.tvRuleIDName);  //~9829I~
        tv.setText(ruleID);                                        //~9829I~
    }                                                              //~9829I~
    //******************************************                   //~9309I~
    protected void setAccountName()                                //~9309I~
    {                                                              //~9309I~
    	if (Dump.Y) Dump.println("ResumeDlg.setAccountName");    //~9309I~//~9316R~//~9322R~//~9828R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9309I~
        {                                                          //~9309I~
            int eswn = ii;                                         //~9416I~
//      	tvsName[eswn].setText(accountNames[ii]);                 //~9309I~//~9401R~//~9828R~
//          tvsEswn[eswn].setText(GConst.nameESWN[eswn]); //~9312I~  //~9320R~//~9401R~//~9828R~
        	tvsName[eswn].setText(resumeHD.HD[HDPOS_MEMBER][ii]);     //~9828I~
            tvsEswn[eswn].setText(GConst.nameESWN[currentEswnByPosition[ii]]);//~9828I~
            tvsPos[eswn].setText(GConst.nameESWN[eswn]);           //~9831I~
        }                                                          //~9309I~
    }                                                              //~9309I~
    //******************************************                   //~9309I~
    protected void showAmmount()                                     //~9309I~//~9819R~
    {                                                              //~9309I~
//        testPrize();    //TODO test                                //~9401I~//~9402R~//~9407R~//~9828R~
//        getOrder(lastScore);                                       //~9322I~//~9828R~
//        setPrize(lastScore);                                                //~9322I~//~9828R~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9309I~
        {                                                          //~9309I~
            int eswn=ii;    //account lsit is position order       //~9416I~
//            tvsOrder[eswn].setText(Integer.toString(idx2Order[ii]+1));//~9401R~//~9828R~
//            tvsScore[eswn].setText(Integer.toString(lastScore[ii]));//~9401R~//~9828R~
//            tvsTopPrize[eswn].setText(Integer.toString(topPrize[ii]));//~9401R~//~9828R~
//            tvsOrderPrize[eswn].setText(Integer.toString(orderPrize[ii]));//~9401R~//~9828R~
//            newTotal[ii]=lastScore[ii]+topPrize[ii]+orderPrize[ii];//~9401R~//~9828R~
//            tvsTotal[eswn].setText(Integer.toString(newTotal[ii]));//~9401R~//~9828R~
            tvsCtrReachStick[eswn].setText(Integer.toString(ctrReach[ii]));//~9828I~//~9831R~
            tvsTotal[eswn].setText(Integer.toString(lastScore[ii]));//~9831I~
//            int mp=minusPrize[ii];                                 //~9416I~//~9828R~
//            if (mp!=0)                                             //~9416I~//~9828R~
//                tvsMinusPrize[eswn].setText(Integer.toString(mp));//~9415R~//~9416R~//~9828R~
//            else                                                   //~9416I~//~9828R~
//                llsMinusPrize[eswn].setVisibility(View.GONE);      //~9416I~//~9828R~
//            if (swMinusPayGetAllPoint)                             //~9415I~//~9828R~
//            {                                                      //~9415I~//~9828R~
//                int mc=minusCharge[ii];                            //~9416I~//~9828R~
//                finalScore[ii]=newTotal[ii]+minusPrize[ii]+mc;//~9415I~//~9416R~//~9828R~
//                if (mc!=0)                                         //~9416I~//~9828R~
//                    tvsMinusCharge[eswn].setText(Integer.toString(mc));//~9415I~//~9416R~//~9828R~
//                else                                               //~9416I~//~9828R~
//                    llsMinusCharge[eswn].setVisibility(View.GONE); //~9416I~//~9828R~
//            }                                                      //~9415I~//~9828R~
//            else                                                   //~9415I~//~9828R~
//            {                                                      //~9415I~//~9828R~
//                finalScore[ii]=newTotal[ii]+minusPrize[ii];        //~9415I~//~9828R~
////              tvsMinusCharge[eswn].setText(Integer.toString(0)); //~9415I~//~9416R~//~9828R~
//                llsMinusCharge[eswn].setVisibility(View.GONE);     //~9416I~//~9828R~
//            }                                                      //~9415I~//~9828R~
//            tvsFinalScore[eswn].setText(Integer.toString(finalScore[ii]));//~9415I~//~9828R~
        }                                                          //~9309I~
//        if (swBird)                                                //~9501I~//~9828R~
//        {                                                          //~9824I~//~9828R~
//            tvLabelBotCharge.setText(R.string.Label_Bird);         //~9824R~//~9828R~
//            adjustByBird(finalScore);                              //~9501I~//~9828R~
//        }                                                          //~9824I~//~9828R~
//        else                                                       //~9501I~//~9611R~//~9828R~
//        if (ACC.activeMembers<PLAYERS)                              //~9429I~//~9828R~
//            adjustByRobotScore(finalScore);                        //~9429R~//~9828R~
//        else                                                       //~9824I~//~9828R~
//        {                                                          //~9824I~//~9828R~
//            tvLabelBotCharge.setText("");                       //~9819I~//~9824I~//~9828R~
//        }                                                          //~9824I~//~9828R~
//        getFinalPoint(finalScore,lastScore);                                           //~9416I~//~9417R~//~9828R~
//        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9416I~//~9828R~
//        {                                                          //~9416I~//~9828R~
//            String s;                                              //~9416I~//~9828R~
//            int fp=finalPoint[ii];                                 //~9416I~//~9828R~
//            if (adjustFinalPoint==S2P_NO)                        //~9416I~//~9828R~
//            {                                                      //~9614I~//~9828R~
//                s=fp/1000+"."+((Math.abs(fp)%1000)/100);           //~9614I~//~9828R~
//            }                                                      //~9614I~//~9828R~
//            else                                                   //~9416I~//~9828R~
//            {                                                      //~9614I~//~9828R~
//                s=Integer.toString(fp/1000);                       //~9416I~//~9828R~
//            }                                                      //~9614I~//~9828R~
//            tvsFinalPoint[ii].setText(s);                          //~9416R~//~9828R~
//            finalPoint1000[ii]=s;                                  //~9615I~//~9828R~
//        }                                                          //~9416I~//~9828R~
//        ACC.finalPoint1000=finalPoint1000;                         //~9614I~//~9828R~
    }                                                              //~9309I~
    //*******************************************************      //~9321I~
    @Override                                                      //~9321I~
    public void onDismissDialog()                                  //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ResumeDlg.onDismissDialog");   //~9321I~//~9819R~//~9828R~
        if (!swDismissBeforNew)                                    //~9322I~
	        AG.aResumeDlg=null;                                         //~9321I~//~9322R~//~9828R~
        swDismissBeforNew=false;                                   //~9322I~
    }                                                              //~9321I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void setButton()                                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ResumeDlg.onDismissDialog");   //~9819I~//~9828R~
    	super.setButton();                                         //~9321I~
        if (swRequester)                                           //~9321I~
	        btnTotal.setEnabled(swAllOK);                          //~9321I~
        else                                                       //~9321I~
	        btnTotal.setVisibility(View.GONE);                     //~9321I~
    }                                                              //~9321I~
    //*******************************************************************//~9321I~//~9828R~//~9831R~
    //*on UiThread                                                 //~9321I~//~9828R~//~9831R~
    //*******************************************************************//~9321I~//~9828R~//~9831R~
    @Override                                                      //~9321I~//~9828R~//~9831R~
//  protected void updateOKNGAdditional(int PctrNone,int PctrNG,boolean PswAllOK)//~9321I~//~9828R~//~9831R~//~0119R~
    protected void updateOKNGAdditional(int PctrNone,int PctrNG,int PctrDisconnected,boolean PswAllOK)//~0119I~
    {                                                              //~9321I~//~9828R~//~9831R~
        if (Dump.Y) Dump.println("ResumeDlg.updateOKNGAdditional ctrNG="+PctrNG+",ctrNone="+PctrNone+",swAllOK="+PswAllOK);//~9321I~//~9322R~//~9828R~//~9831R~
        btnTotal.setEnabled(PswAllOK);                             //~9321I~//~9828R~//~9831R~
        if (PctrNone==0 && PctrNG!=0) //all responsed, someone replyed NG//~9612I~//~9828R~//~9831R~
        {                                                          //~9612I~//~9828R~//~9831R~
        	UView.showToast(R.string.Err_ResumeDlgNG);//~9612I~//~9828R~//~9831R~
        }                                                          //~9612I~//~9828R~//~9831R~
    }                                                              //~9321I~//~9828R~//~9831R~
//    //*******************************************************************//~9612I~//~9828R~
//    @Override                                                      //~9612I~//~9828R~
//    protected void alertActionReceived(int Pbuttonid,int Prc)      //~9612I~//~9828R~
//    {                                                              //~9612I~//~9828R~
//        if (Dump.Y) Dump.println("ResumeDlg.alerActionReceived buttonID="+Pbuttonid+",rc="+Prc);//~9612I~//~9828R~
//        if (Pbuttonid==BUTTON_POSITIVE)                            //~9612I~//~9828R~
//        {                                                          //~9612I~//~9828R~
//            btnTotal.setEnabled(true/*PswAllOK*/);                 //~9612I~//~9828R~
//        }                                                          //~9612I~//~9828R~
//    }                                                              //~9612I~//~9828R~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void onClickOK()                                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ResumeDlg.onClickOK swRequester="+swRequester);//~9321I~//~9322R~//~9828R~
        if (swRequester)                                           //~9321I~
        {                                                          //~9322I~
        	sendConfirmRequest();                                  //~9321R~
    		disableFixGame(true);                                  //~9901I~
        }                                                          //~9322I~
        else                                                       //~9321I~
        {                                                          //~9321I~
        	sendReply(true);                                       //~9321I~
			dismiss();                                          //~9321I~
        }                                                          //~9321I~
    }                                                              //~9321I~
    //******************************************                   //~9901I~
    public void disableFixGame(boolean PswResetResponse)           //~9901I~
    {                                                              //~9901I~
        if (Dump.Y) Dump.println("ResumeDlg.disableFixGame swResetResponse="+PswResetResponse);//~9901I~
		btnTotal.setEnabled(false);                                //~9901I~
        if (PswResetResponse)                                      //~9901I~
        {                                                          //~9901I~
		    Arrays.fill(respStat,EGDR_NONE);                       //~9901I~
            updateOKNG();                                          //~9901I~
        }                                                          //~9901I~
    }                                                              //~9901I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void onClickCancel()                                    //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("SendDlg.onClickNG");             //~9321I~
        sendReply(false);                                          //~9321I~
        dismiss     ();                                              //~9321I~
    }                                                              //~9321I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void onClickOther(int Pbuttonid)                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ResumeDlg.onClickOther btnid="+Integer.toHexString(Pbuttonid));//~9321I~//~9322R~//~9828R~
        switch(Pbuttonid)                                          //~9321I~
        {                                                          //~9321I~
            case R.id.FixTotal:                                    //~9321I~
                onClickTotal();                                    //~9321I~
                break;                                             //~9321I~
            case R.id.ShowRule:                                    //~9417I~
                onClickShowRule();                                 //~9417I~
                break;                                             //~9417I~
        }                                                          //~9321I~
    }                                                              //~9321I~
    //******************************************                   //~9321I~
    public void onClickTotal()                                     //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ResumeDlg.onClickTotal");         //~9321I~//~9322R~//~9828R~
        dismiss();                                              //~9321I~
//      sendEndGame(newTotal);                                     //~9321I~//~9828R~
        resumeGame();                                              //~9828I~
    }                                                              //~9321I~
    //******************************************                   //~9417I~
    public void onClickShowRule()                                  //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("ResumeDlg.onClickShowRule");   //~9417I~//~9828R~
        showRule();                                                //~9417I~
    }                                                              //~9417I~
	//************************************************             //~9417I~
    private void showRule()                                        //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("ResumeDlg.showRule");          //~9417I~//~9828R~
//      RuleSetting.showRuleInGame();                              //~9417I~//~9830R~
        RuleSetting.showRuleInGame(resumeHD.ruleProp);             //~9830I~
    }                                                              //~9417I~
    //**************************************************************************//~9321I~
    //*from OnClickTotal                                           //~9605I~
    //**************************************************************************//~9605I~
//    public void sendEndGame(int[] Ptotal)                          //~9321I~//~9828R~
//    {                                                              //~9321I~//~9828R~
//        int[][] intssP=new int[][]{Ptotal,minusPrize,minusCharge,finalScore};//~9415I~//~9828R~
//        if (Dump.Y) Dump.println("ResumeDlg.sendEndGame total="+Arrays.toString(Ptotal));//~9525I~//~9828R~
//        if (!AG.aUserAction.isServer)                              //~9321I~//~9828R~
//        {                                                          //~9321I~//~9828R~
//            sendToServerEndGame(Ptotal); //RESUMEDLG_CONFIRMED //~9321R~//~9322R~//~9828R~//~9830R~
//            return;                                                //~9321I~//~9828R~
//        }                                                          //~9321I~//~9828R~
//        String ts=Utils.getTimeStamp(TS_DATE_TIME2); //":" between date and time for parseInt//~9826I~//~9828R~
//        String fnm= History.timestampToFilename(ts);  //"-"         //~9826I~//~9828R~
//        endGame(fnm,endgameType,intssP);                           //~9826I~//~9828R~
//        sendToAllClientEndGame(ts,endgameType,intssP);             //~9826I~//~9828R~
//    }                                                              //~9321I~//~9828R~
    public void resumeGame()                                       //~9828I~
    {                                                              //~9828I~
        if (Dump.Y) Dump.println("ResumeDlg.resumeGame HD="+resumeHD.toString());//~9901I~
//        if (Utils.isShowingDialogFragment(AG.aHistoryDlg))         //~9901I~//~0111R~
//        {                                                          //~9901I~//~0111R~
//            AG.aHistoryDlg.dismiss();                              //~9901I~//~0111R~
//        }                                                          //~9901I~//~0111R~
    	dismissHistoryDlg();                                       //~0111I~
    	setResumeHD(resumeHD);                                     //~9901I~
        new EventCB(ECB_ACTION_RESUMEGAME).postEvent(); //goto Accounts.resumeGame//~9904R~
    }                                                              //~9828I~
    private static void dismissHistoryDlg()                        //~0111R~
    {                                                              //~0111I~
        if (Dump.Y) Dump.println("ResumeDlg.dismissHistoryDlg");   //~0111I~
        if (Utils.isShowingDialogFragment(AG.aHistoryDlg))         //~0111I~
        {                                                          //~0111I~
			AG.aHistoryDlg.dismiss();                              //~0111I~
        }                                                          //~0111I~
    }                                                              //~0111I~
    //**************************************************************************//~9831I~
    //*from HistoryDlg at received HistoryData flaged SAVE_EGTP_INTERRUPTED_RESUME//~9831R~//~9901R~
    //**************************************************************************//~9831I~
    public static void onReceivedHistoryInterruptedResume(String PsenderYourName,HistoryData Phd)//~9831R~
    {                                                              //~9831I~
        if (Dump.Y) Dump.println("ResumeDlg.onReceivedHistoryInterruptedResume sender="+PsenderYourName+",Phd="+Phd.toString());//~9831R~
	    Phd.setEndgameType(SAVE_EGTP_INTERRUPTED);	//reset SAVE_EGTP_INTERRUPTED_RESUME//~9831I~
        showDialog(false/*swServer*/,PsenderYourName,Phd);         //~9831R~
    }                                                              //~9831I~
    //**************************************************************************//~9831I~
    //*from BTIO,received GCM_RESUMEDLG                            //~9831I~
    //*Sender Devicename,GCM_RESUMEDLG,submsgid,                   //~9831I~
    //**************************************************************************//~9831I~
    public static void onReceived(int PthreadIdx,String PsenderYourName,String Pwords[])//~9831R~
    {                                                              //~9831I~
        if (Dump.Y) Dump.println("ResumeDlg:onReceived thread="+PthreadIdx+",sender="+PsenderYourName+",words="+Arrays.toString(Pwords));//~9831R~
        int submsgid=parseInt(Pwords[RDPOS_SUBMSGID],0);           //~9831R~
        switch(submsgid)                                           //~9831I~
        {                                                          //~9831I~
        case RESUMEDLG_CONFIRM_REPLY:                            //~9831I~
        	boolean okng=parseInt(Pwords[RDPOS_OKNG],0)==EGDR_OK;	//EGDR_OK or EGDR_NG//~9831R~
        	String timestamp=Pwords[RDPOS_TIMESTAMP];              //~9831R~
        	updateOKNGResume(PsenderYourName,okng,timestamp);      //~9831R~
            break;                                                 //~9831I~
        }                                                          //~9831I~
    }                                                              //~9831I~
//    //**************************************************************************//~9318I~//~9831R~
//    //*from UserAction,GCM_ENDGAME_ACCOUNTS btio msg received                                           //~9318I~//~9320R~//~9322R~//~9831R~
//    //**************************************************************************//~9318I~//~9831R~
//    public static void onReceived(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~9318R~//~9831R~
//    {                                                              //~9318I~//~9831R~
//        int[] total,ctrReach,gameSeq,other;                        //~9830R~//~9831R~
//        int[][] intssP;//~9321I~//~9415R~                        //~9831R~
//        if (Dump.Y) Dump.println("ResumeDlg:onReceived swServer="+PswServer+",PswReceived="+PswReceived+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~9318R~//~9322R~//~9828R~//~9831R~
//        int scoreMsgtype=PintParm[RDPOS_SUBMSGID];               //~9320I~//~9322R~//~9830R~//~9831R~
//        if (PswServer)  //from dealer(client)                      //~9318R~//~9831R~
//        {                                                          //~9318I~//~9831R~
//            switch(scoreMsgtype)                                   //~9321I~//~9831R~
//            {                                                      //~9321I~//~9831R~
////            case RESUMEDLG_CONFIRM_REQUEST:                    //~9321R~//~9830R~//~9831R~
////                intssP=parseConfirmRequest(PintParm);      //~9415I~//~9830R~//~9831R~
////                sendToAllClientConfirm(intssP);                    //~9415I~//~9830R~//~9831R~
////                if (Utils.isShowingDialogFragment(AG.aResumeDlg))//~9322R~//~9828R~//~9830R~//~9831R~
////                {                                                  //~9322I~//~9830R~//~9831R~
////                    AG.aResumeDlg.swDismissBeforNew=true;        //~9322R~//~9828R~//~9830R~//~9831R~
////                    AG.aResumeDlg.dismiss();                     //~9322R~//~9828R~//~9830R~//~9831R~
////                }                                                  //~9322I~//~9830R~//~9831R~
//////              ResumeDlg.newInstance(intssP).show();            //~9415I~//~9828R~//~9830R~//~9831R~
////                break;                                             //~9321I~//~9830R~//~9831R~
//            case RESUMEDLG_CONFIRM_REPLY:                      //~9321R~//~9830R~//~9831R~
//                int replyEswn=PintParm[POS_REPLAY_ESWN];           //~9321I~//~9430I~//~9831R~
//                int okng=PintParm[RDPOS_OKNG];                       //~9830I~//~9831R~
////                if (AG.aAccounts.isFirstDealerReal())              //~9605I~//~9830R~//~9831R~
////                {                                                  //~9321I~//~9830R~//~9831R~
////                    if (Utils.isShowingDialogFragment(AG.aResumeDlg))//~9321I~//~9322R~//~9828R~//~9830R~//~9831R~
////                        AG.aResumeDlg.repaintOKNG(replyEswn,PintParm[RDPOS_OKNG]!=0); //callback updateOKNGAdditional//~9321I~//~9322R~//~9828R~//~9830R~//~9831R~
////                }                                                  //~9321I~//~9830R~//~9831R~
////                else                                               //~9321I~//~9830R~//~9831R~
////                {                                                  //~9321I~//~9830R~//~9831R~
////                    String msgdata=RESUMEDLG_CONFIRM_REPLY+MSG_SEPAPP2+PintParm[RDPOS_OKNG];//~9321I~//~9322R~//~9830R~//~9831R~
////                    AG.aUserAction.sendToTheClientDealerWithSourceEswnFirstStarter(GCM_ENDGAME_ACCOUNTS,replyEswn,msgdata);//~9606I~//~9830R~//~9831R~
////                }                                                  //~9321I~//~9830R~//~9831R~
//                                                                   //~9830I~//~9831R~
//                  if (Utils.isShowingDialogFragment(AG.aResumeDlg))//~9830I~//~9831R~
//                      AG.aResumeDlg.repaintOKNG(replyEswn,PintParm[RDPOS_OKNG]!=0); //callback updateOKNGAdditional//~9830I~//~9831R~
//                break;                                             //~9321I~//~9831R~
//            case RESUMEDLG_CONFIRMED:                       //~9322I~//~9830R~//~9831R~
////                total=new int[PLAYERS];                            //~9322I~//~9831R~
////                minusP=new int[PLAYERS];                           //~9415I~//~9831R~
////                minusC=new int[PLAYERS];                           //~9415I~//~9831R~
////                finalS=new int[PLAYERS];                           //~9415I~//~9831R~
////                System.arraycopy(PintParm,POS_AMMOUNT,total,0,PLAYERS);//~9322I~//~9831R~
////                System.arraycopy(PintParm,POS_AMMOUNT_MINUSPAY,minusP,0,PLAYERS);//~9415I~//~9831R~
////                System.arraycopy(PintParm,POS_AMMOUNT_MINUSCHARGE,minusC,0,PLAYERS);//~9415I~//~9831R~
////                System.arraycopy(PintParm,POS_AMMOUNT_FINALSCORE,finalS,0,PLAYERS);//~9415R~//~9831R~
////                intssP=new int[][]{total,minusP,minusC,finalS};//~9415I~//~9831R~
////                int egtp=PintParm[POS_ENDGAMETYPE];                //~9525R~//~9831R~
////                ts=Utils.getTimeStamp(TS_DATE_TIME2); //":" between date and time for parseInt//~9826I~//~9831R~
////                String fnm=History.timestampToFilename(ts);  //"-" //~9826I~//~9831R~
////                endGame(fnm,egtp,intssP);                          //~9826I~//~9831R~
////                sendToAllClientEndGame(ts,egtp,intssP);            //~9826I~//~9831R~
//                break;                                             //~9322I~//~9831R~
//            }                                                      //~9321I~//~9831R~
//        }                                                          //~9318I~//~9831R~
//        else                                                       //~9318I~//~9831R~
//        {                                                          //~9318I~//~9831R~
//            switch(scoreMsgtype)                                   //~9321I~//~9831R~
//            {                                                      //~9321I~//~9831R~
////            case RESUMEDLG_CONFIRM_REQUEST:                    //~9321I~//~9830R~//~9831R~
////                intssP=parseConfirmRequest(PintParm);  //~9415I~   //~9830R~//~9831R~
////                showDialog(intssP);                                //~9830I~//~9831R~
////                break;                                             //~9321I~//~9831R~
////            case RESUMEDLG_CONFIRM_REPLY:                      //~9321I~//~9830R~//~9831R~
////                if (AG.aAccounts.isFirstDealerReal())              //~9605I~//~9830R~//~9831R~
////                {                                                  //~9321I~//~9830R~//~9831R~
////                    int replyEswn=PintParm[POS_REPLAY_ESWN];       //~9321I~//~9830R~//~9831R~
////                    if (Utils.isShowingDialogFragment(AG.aResumeDlg))//~9321I~//~9322R~//~9828R~//~9830R~//~9831R~
////                        AG.aResumeDlg.repaintOKNG(replyEswn,PintParm[RDPOS_OKNG]!=0); //callback updateOKNGAdditional//~9321I~//~9322R~//~9828R~//~9830R~//~9831R~
////                }                                                  //~9321I~//~9830R~//~9831R~
////                break;                                             //~9321I~//~9830R~//~9831R~
//            case RESUMEDLG_CONFIRMED:                          //~9322I~//~9830R~//~9831R~
////                if (!AG.aAccounts.isFirstDealerReal())             //~9605I~//~9831R~
////                {                                                  //~9322I~//~9831R~
////                    total=new int[PLAYERS];                        //~9322I~//~9831R~
////                    minusP=new int[PLAYERS];                       //~9415I~//~9831R~
////                    minusC=new int[PLAYERS];                       //~9415I~//~9831R~
////                    finalS=new int[PLAYERS];                       //~9415I~//~9831R~
////                    System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED,total,0,PLAYERS);//~9826I~//~9831R~
////                    System.arraycopy(PintParm,POS_AMMOUNT_MINUSPAY_CONFIRMED,minusP,0,PLAYERS);//~9826I~//~9831R~
////                    System.arraycopy(PintParm,POS_AMMOUNT_MINUSCHARGE_CONFIRMED,minusC,0,PLAYERS);//~9826I~//~9831R~
////                    System.arraycopy(PintParm,POS_AMMOUNT_FINALSCORE_CONFIRMED,finalS,0,PLAYERS);//~9826I~//~9831R~
////                    intssP=new int[][]{total,minusP,minusC,finalS};//~9415I~//~9831R~
////                    int egtp=PintParm[POS_ENDGAMETYPE];            //~9525R~//~9831R~
////                    ts=History.timestampToFilename(PintParm[POS_DATE],PintParm[POS_TIME]);//~9826I~//~9831R~
////                    endGame(ts,egtp,intssP);                       //~9826I~//~9831R~
////                }                                                  //~9322I~//~9831R~
//                break;                                             //~9322I~//~9831R~
//            }                                                      //~9320I~//~9831R~
//        }                                                          //~9318I~//~9831R~
//    }                                                              //~9318I~//~9831R~
//    //**************************************************************************//~9831R~
//    //*from BTIO by GC_RESUMEDLG                                 //~9831R~
//    //**************************************************************************//~9831R~
//    public static void onReceived(String PsenderYourName,String[] Pwords)//~9831R~
//    {                                                            //~9831R~
//        if (Dump.Y) Dump.println("ResumeDlg:onReceived sender="+PsenderYourName+",words="+Arrays.toString(Pwords));//~9831R~
//    }                                                            //~9831R~
//    //**************************************************************************//~9321I~//~9831R~
//    private static int[][] parseConfirmRequest(int[] PintParm)     //~9321R~//~9322R~//~9415R~//~9831R~
//    {                                                              //~9321I~//~9831R~
//        int pos=POS_AMMOUNT;                                         //~9322R~//~9831R~
//        if (Dump.Y) Dump.println("ResumeDlg:parseConfirmRequest startpos="+pos+",intp="+Arrays.toString(PintParm));//~9321I~//~9322R~//~9828R~//~9831R~
//        int[] score=new int[PLAYERS];                              //~9321I~//~9831R~
//        int[] ctrReach=new int[PLAYERS];                             //~9415I~//~9830R~//~9831R~
//        int[] gameSeq=new int[PLAYERS];                             //~9415I~//~9830R~//~9831R~
//        int[] other=new int[PLAYERS];                             //~9415R~//~9830R~//~9831R~
//        System.arraycopy(PintParm,pos,score,0,PLAYERS);   pos+=PLAYERS;//~9321I~//~9831R~
//        System.arraycopy(PintParm,pos,ctrReach,0,PLAYERS);   pos+=PLAYERS;//~9415I~//~9830R~//~9831R~
//        System.arraycopy(PintParm,pos,gameSeq,0,PLAYERS);   pos+=PLAYERS;//~9415I~//~9830R~//~9831R~
//        System.arraycopy(PintParm,pos,other,0,PLAYERS);   pos+=PLAYERS;//~9415R~//~9830R~//~9831R~
//        if (Dump.Y) Dump.println("ResumeDlg:parseConfirmRequest lastScore="+Arrays.toString(score));//~9321I~//~9322R~//~9828R~//~9831R~
//        if (Dump.Y) Dump.println("ResumeDlg:parseConfirmRequest ctrReach="+Arrays.toString(ctrReach));//~9415I~//~9828R~//~9830R~//~9831R~
//        if (Dump.Y) Dump.println("ResumeDlg:parseConfirmRequest gameSeq="+Arrays.toString(gameSeq));//~9415I~//~9828R~//~9830R~//~9831R~
//        if (Dump.Y) Dump.println("ResumeDlg:parseConfirmRequest other="+Arrays.toString(other));//~9415R~//~9828R~//~9830R~//~9831R~
//        int[][] rc=new int[][]{score,ctrReach,gameSeq,other};        //~9415R~//~9830R~//~9831R~
//        return rc;                                              //~9321I~//~9322R~//~9831R~
//    }                                                              //~9321I~//~9831R~
//    //**************************************************************************//~9321I~//~9A31R~
//    private static void endGame(String Pfnm,int PendgameType,int[][] PintssP/*indexSeq*/)//~9826I~//~9A31R~
//    {                                                              //~9321I~//~9A31R~
//        if (Dump.Y) Dump.println("ResumeDlg:endGame type="+PendgameType);//~9321I~//~9322R~//~9828R~//~9A31R~
//        AG.aAccounts.setScore(PintssP[3]);  //save finalScore as Accounts.score           //~9415I~//~9823R~//~9A31R~
//        showScoreFinalPoint();                                     //~9823I~//~9A31R~
//        Status.endGame(PendgameType,NGTP_GAMEOVER);                           //~9321I~//~9401R~//~9A31R~
//        AG.aStatus.gameOverScoreFixed();                           //~9612I~//~9A31R~
//        String[] accountNames=AG.aAccounts.getAccountNamesByPosition();              //~9613I~//~9A31R~
//        AG.aHistory.saveLatestGame(Pfnm,accountNames,PintssP);     //~9826I~//~9A31R~
//        if (PendgameType==EGDR_MINUSSTOP)                          //~9826I~//~9A31R~
//            GMsg.drawMsgbar(R.string.Info_GameEndMinusStop);       //~9826I~//~9A31R~
//    }                                                              //~9321I~//~9A31R~
    //*******************************************************      //~9823I~
    protected static void showScoreFinalPoint()                           //~9823R~
    {                                                              //~9823I~
    	int[] fs=AG.aAccounts.score;                               //~9823I~
    	String[] fp=AG.aAccounts.finalPoint1000;                      //~9823I~
        if (Dump.Y) Dump.println("ResumeDlg:showScoreFinalPoint finalPoint="+Arrays.toString(fs)+",finalPoint1000="+Arrays.toString(fp));//~9823R~//~9828R~
		AG.aNamePlate.showScoreFinalPoint(fp,fs);                  //~9823R~
    }                                                              //~9823I~
    //*******************************************************      //~9321I~
    //*on requester                                                //~9321I~
    //*******************************************************      //~9321I~
    public void sendConfirmRequest()                               //~9321R~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ResumeDlg:sendConfirmRequest");   //~9321I~//~9322R~//~9828R~
//        if (isServer)                                              //~9321I~//~9828R~
//            sendToAllClientConfirm(new int[][]{lastScore,minusPrize,minusCharge,finalScore});//~9415R~//~9828R~
//        else                                                       //~9321I~//~9828R~
//            sendToServerConfirm();                                  //~9321I~//~9828R~
		sendToAllClientConfirm(resumeHD);                          //~9831I~
    }                                                              //~9321I~
//    //*******************************************************************//~9321I~//~9831R~
//    protected void sendReply(boolean PswOK)                        //~9321I~//~9831R~
//    {                                                              //~9321I~//~9831R~
//        if (Dump.Y) Dump.println("ResumeDlg.sendReply OK="+PswOK);  //~9321R~//~9322R~//~9828R~//~9831R~
//        String okng=(PswOK ? "1" : "0");                            //~9321I~//~9831R~
////      if (!isServer)                                             //~9321R~//~9828R~//~9831R~
//        if (!swServer)                                             //~9828I~//~9831R~
//            AG.aUserAction.sendToServer(GCM_ENDGAME_ACCOUNTS,PLAYER_YOU,RESUMEDLG_CONFIRM_REPLY,okng);//~9321R~//~9322R~//~9830R~//~9831R~
//        else                                                       //~9321R~//~9831R~
//        {                                                          //~9321I~//~9831R~
//            String msgdata=RESUMEDLG_CONFIRM_REPLY+MSG_SEPAPP2+okng;//~9321R~//~9322R~//~9830R~//~9831R~
//            AG.aUserAction.sendToTheClientDealerFirstStarter(GCM_ENDGAME_ACCOUNTS,msgdata);//~9606I~//~9831R~
//        }                                                          //~9321I~//~9831R~
//    }                                                              //~9321I~//~9831R~
    //*******************************************************************//~9831I~
    protected void sendReply(boolean PswOK)                        //~9831I~
    {                                                              //~9831I~
        if (Dump.Y) Dump.println("ResumeDlg.sendReply OK="+PswOK); //~9831I~
        int okng=(PswOK ? EGDR_OK : EGDR_NG);                   //~9831R~
        if (PswOK)                                                 //~9831I~
        {                                                          //~9901I~
        	AG.aHistory.saveReceived(false/*PswMsg,bypass override without warning*/,resumeHD);//~9831I~
			setResumeHD(resumeHD);                                 //~9901I~
        }                                                          //~9901I~
//      String msg=RESUMEDLG_CONFIRM_REPLY+MSG_SEP+okng+MSG_SEP+resumeHD.HD[HDPOS_HDR][RDPOS_TIMESTAMP];//~9831R~//~0314R~
        String msg=RESUMEDLG_CONFIRM_REPLY+MSG_SEP+okng+MSG_SEP+resumeHD.HD[HDPOS_HDR][POS_TIMESTAMP];//~0314I~
        if (Dump.Y) Dump.println("ResumeDlg.sendReply hdr="+Arrays.toString(resumeHD.HD[HDPOS_HDR]));//~9831I~
        if (Dump.Y) Dump.println("ResumeDlg.sendReply msg="+msg);  //~9831I~
		AG.aBTMulti.sendMsgToServer(true/*Pswapp*/,GCM_RESUMEDLG,msg);//~9831I~
    }                                                              //~9831I~
    //**************************************************************************//~9901I~
    private void setResumeHD(HistoryData Phd)                      //~9901I~
    {                                                              //~9901I~
    	AG.resumeHD=Phd;	//chket at startGame to resume         //~9901I~
    }                                                              //~9901I~
//    //**************************************************************************//~9321I~//~9828R~
//    private void sendToServerConfirm()                             //~9321R~//~9828R~
//    {                                                              //~9321I~//~9828R~
//        if (Dump.Y) Dump.println("ResumeDlg.sendToServerConfirm");  //~9321I~//~9322R~//~9828R~
//        String msgdata=makeReqMsg(new int[][]{lastScore,minusPrize,minusCharge,finalScore});//~9415R~//~9828R~
//        AG.aUserAction.sendToServer(GCM_ENDGAME_ACCOUNTS,PLAYER_YOU,RESUMEDLG_CONFIRM_REQUEST,msgdata);//~9321R~//~9322R~//~9828R~//~9830R~
//    }                                                              //~9321I~//~9828R~
//    //**************************************************************************//~9321I~//~9828R~
//    private void sendToServerEndGame(int[] Ptotal)                 //~9321R~//~9828R~
//    {                                                              //~9321I~//~9828R~
//        if (Dump.Y) Dump.println("ResumeDlg.sendToServerEndGame");  //~9321I~//~9322R~//~9828R~
//        String msg=makeReqMsg(new int[][]{Ptotal,minusPrize,minusCharge,finalScore})+MSG_SEPAPP2+endgameType;//~9525R~//~9828R~
//        AG.aUserAction.sendToServer(GCM_ENDGAME_ACCOUNTS,PLAYER_YOU,RESUMEDLG_CONFIRMED,msg);//~9321I~//~9322R~//~9828R~//~9830R~
//    }                                                              //~9321I~//~9828R~
//    //**************************************************************************//~9321I~//~9831R~
//    private static void sendToAllClientEndGame(String Ptimestamp,int PendgameType,int[][] PintssP)//~9826I~//~9831R~
//    {                                                              //~9321I~//~9831R~
//        String msg=Ptimestamp+MSG_SEPAPP+makeReqMsg(PintssP)+MSG_SEPAPP2+PendgameType;//~9826I~//~9831R~
//        if (Dump.Y) Dump.println("ResumeDlg:sendToAllClientEndGame msgdata="+msg);//~9321I~//~9322R~//~9525R~//~9828R~//~9831R~
//        sendToAllClient(RESUMEDLG_CONFIRMED,msg);             //~9321I~//~9830R~//~9831R~
//    }                                                              //~9321I~//~9831R~
//    //**************************************************************************//~9321I~//~9831R~
//    private static void sendToAllClientConfirm(int[][] PintssP)    //~9415I~//~9831R~
//    {                                                              //~9321I~//~9831R~
//        if (Dump.Y) Dump.println("ResumeDlg:sendToAllClientConfirm");//~9321I~//~9322R~//~9828R~//~9831R~
//        String msg=makeReqMsg(PintssP);                            //~9415I~//~9831R~
//        sendToAllClient(RESUMEDLG_CONFIRM_REQUEST,msg); //~9321I~  //~9830R~//~9831R~
//    }                                                              //~9321I~//~9831R~
    //**************************************************************************//~9831I~
    private static void sendToAllClientConfirm(HistoryData Phd)    //~9831I~
    {                                                              //~9831I~
        if (Dump.Y) Dump.println("ResumeDlg:sendToAllClientConfirm");//~9831I~
        int old=Phd.setEndgameType(SAVE_EGTP_INTERRUPTED_RESUME);//received by HistoryDlg.onReceive then delivery to this//~9831R~
//      String msgHD=AG.aHistory.makeHistoryData(Phd);             //~9831R~
        String msgHD=HistoryData.makeSendData(Phd);                //~9831I~
		AG.aBTMulti.sendMsgToAllClientProp(GCM_HISTORY,msgHD);//send string datas as send properties,received by HistoryDlg//~9831I~
        Phd.setEndgameType(old);   //restore                          //~9831I~
//      String msg=Phd.HD[HDPOS_HDR][RDPOS_TIMESTAMP];    //date:time fmt//~9831R~
//      sendToAllClient(RESUMEDLG_CONFIRM_REQUEST,msg);            //~9831R~
    }                                                              //~9831I~
    //**************************************************************************//~9321I~
    private static void sendToAllClient(int PsubMsgid,String Pmsgdata)//~9321I~//~9830R~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ResumeDlg:sendToAllClient msgid="+PsubMsgid+",msgdata="+Pmsgdata);//~9831I~
//  	int eswn=Accounts.playerToEswn(PLAYER_YOU);            //~9321I~//~9831R~
//      String msgdata=eswn+MSG_SEPAPP2+PsubMsgid+MSG_SEPAPP2+Pmsgdata;//~9321I~//~9322R~//~9402R~//~9830R~//~9831R~
//      AG.aUserAction.sendToClient(true/*swAll*/,false/*swRobot*/,GCM_ENDGAME_ACCOUNTS,PLAYER_YOU,msgdata);//~9321I~//~9322R~//~9831R~
		String msg=PsubMsgid+MSG_SEPAPP2+Pmsgdata;                    //~9831I~
		AG.aBTMulti.sendMsgToAllClient(true/*swApp*/,GCM_RESUMEDLG,msg);//send string datas as send properties,received by HistoryDlg//~9831I~
                                                                   //~9831I~
    }                                                              //~9321I~
//    //*******************************************************      //~9322I~//~9831R~
//    public static String makeReqMsg(int[][] PintssP)               //~9415R~//~9831R~
//    {                                                              //~9322I~//~9831R~
//        StringBuffer sb=new StringBuffer();                        //~9322I~//~9831R~
//        for (int jj=0;jj<PintssP.length;jj++)                       //~9415I~//~9831R~
//        {                                                          //~9415I~//~9831R~
//            for (int ii=0;ii<PLAYERS;ii++)                         //~9415I~//~9831R~
//                sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+PintssP[jj][ii]);     //eswn seq//~9415I~//~9831R~
//        }                                                          //~9415I~//~9831R~
//        String s=sb.toString();                                    //~9322I~//~9831R~
//        if (Dump.Y) Dump.println("ResumeDlg.makeReqMsg msg="+s); //~9322I~//~9828R~//~9831R~
//        return s;                                                  //~9322I~//~9831R~
//    }                                                              //~9322I~//~9831R~
    //**************************************************************************//~9322I~
    //*from GC by btn click                                        //~9322I~
//    //**************************************************************************//~9322I~//~9828R~
//    public static void showDismissed()                             //~9322I~//~9828R~
//    {                                                              //~9322I~//~9828R~
//        if (Dump.Y) Dump.println("ResumeDlg:showDismisswd");     //~9322R~//~9828R~
//        if (Status.isGameOverScoreFixed())                     //~9612I~//~9828R~
//        {                                                          //~9612I~//~9828R~
//            UView.showToast(R.string.Err_AlreadyGameOver);         //~9612I~//~9828R~
//            return;                                                //~9612I~//~9828R~
//        }                                                          //~9612I~//~9828R~
//        if (Utils.isShowingDialogFragment(AG.aResumeDlg))        //~9322R~//~9828R~
//        {                                                          //~9322I~//~9828R~
//            if (Dump.Y) Dump.println("ResumeDlg:showDismisswd already shown");//~9322R~//~9828R~
//            return;                                                //~9322I~//~9828R~
//        }                                                          //~9322I~//~9828R~
//        Complete cmp=AG.aComplete;                                 //~9322R~//~9402M~//~9828R~
//        if (cmp.lastScore==null)                   //~9322I~        //~9402R~//~9415R~//~9828R~
//        {                                                          //~9322I~//~9828R~
//            UView.showToast(R.string.Err_ScoreReviewNoEndData);       //~9322I~//~9402R~//~9828R~
//            return;                                                //~9322I~//~9828R~
//        }                                                          //~9322I~//~9828R~
//        ResumeDlg.newInstance(new int[][]{cmp.lastScore,cmp.minusPrize,cmp.minusCharge}).show();//~9415R~//~9416R~//~9828R~
//    }                                                              //~9322I~//~9828R~
    //***********************************************************************************//~9606I~//~9828I~
    @Override                                                      //~9828I~
    public void updateOKNGByPosition()                             //~9606I~//~9828I~
    {                                                              //~9606I~//~9828I~
        if (Dump.Y) Dump.println("ResumeDlg.updateOKNGByPosition respStat="+Arrays.toString(respStat));//~9606I~//~9828I~
        if (Dump.Y) Dump.println("ResumeDlg.updateOKNGByPosition idxMembers="+Arrays.toString(resumeHD.idxMembers));//~9828I~
        String stat;                                               //~9606I~//~9828I~
        int color;                                                 //~9606I~//~9828I~
        int ctrNone=0,ctrNG=0;                                     //~9606I~//~9828I~
        int ctrDisconnected=0;                                     //~0119I~
//      int requester=AG.aAccounts.getFirstDealerPosReal();        //~9606R~//~9828I~
        Members memb=AG.aBTMulti.BTGroup;                          //~9828I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9606I~//~9828I~
        {                                                          //~9606I~//~9828I~
//      	int pos=AG.aAccounts.currentEswnToPosition(ii);                     //~9606I~//~9828I~
            int idx=resumeHD.idxMembers[ii];                       //~9828I~
//      	if (AG.aAccounts.isDummyByCurrentEswn(ii))             //~9606I~//~9828I~
        	if (Dump.Y) Dump.println("ResumeDlg.updateOKNGByPosition ii="+ii+",idx="+idx+",stat="+memb.MD[idx].status);//~9828I~
        	if (memb.MD[idx].isRobot())                                 //~9828R~
            {                                                      //~9606I~//~9828I~
            	stat=strBot;                                       //~9606I~//~9828I~
        		color=COLOR_RESPBOT;                               //~9606I~//~9828I~
            }                                                      //~9606I~//~9828I~
            else                                                   //~9606I~//~9828I~
//      	if (pos==requester)                                    //~9606R~//~9828I~
        	if (memb.MD[idx].isServer())                                //~9828R~
            {                                                      //~9606I~//~9828I~
                stat=strDealer;                                    //~9606I~//~9828I~
                color=COLOR_RESPBOT;                               //~9606I~//~9828I~
            }                                                      //~9606I~//~9828I~
            else                                                   //~9606I~//~9828I~
            if (respStat[ii]==EGDR_NG) //no good                   //~9606I~//~9828I~
            {                                                      //~9606I~//~9828I~
            	stat=strNG;                                        //~9606I~//~9828I~
            	color=COLOR_RESPNG;                                //~9606I~//~9828I~
                ctrNG++;                                           //~9606I~//~9828I~
            }                                                      //~9606I~//~9828I~
            else                                                   //~9606I~//~9828I~
            if (respStat[ii]!=0)                                   //~9606I~//~9828I~
            {                                                      //~9606I~//~9828I~
            	stat=strOK;                                        //~9606I~//~9828I~
            	color=COLOR_RESPOK;                                //~9606I~//~9828I~
            }                                                      //~9606I~//~9828I~
            else                                                   //~9606I~//~9828I~
            {                                                      //~9606I~//~9828I~
            	stat=strNoReply;                                   //~9606I~//~9828I~
        		color=COLOR_RESPNONE;                              //~9606I~//~9828I~
                ctrNone++;                                         //~9606I~//~9828I~
            }                                                      //~9606I~//~9828I~
	        if (Dump.Y) Dump.println("ResumeDlg.updateOKNGByPosition pos="+ii+",stat="+stat+",resp="+respStat[ii]);//~9606R~//~9828I~
            tvsResp[ii].setText(stat);                            //~9606I~//~9828I~
            tvsResp[ii].setBackgroundColor(color);                //~9606I~//~9828I~
        }                                                          //~9606I~//~9828I~
//      tvsRespESWN[AG.aAccounts.yourESWN].setBackgroundColor(COLOR_YOU);//~9606I~//~9828R~
        tvsRespESWN[resumeHD.posServer].setBackgroundColor(COLOR_YOU);//~9828I~
        swAllOK=ctrNone==0 && ctrNG==0;                            //~9606I~//~9828I~
//      updateOKNGAdditional(ctrNone,ctrNG,swAllOK);               //~9606I~//~9828I~//~9831R~//~0119R~
        updateOKNGAdditional(ctrNone,ctrNG,ctrDisconnected,swAllOK);//~0119I~
        if (Dump.Y) Dump.println("OKNGDlg.updateOKNGByPosition ctrNG="+ctrNG+",ctrNone="+ctrNone+",swAllOK="+swAllOK);//~9606R~//~9828R~
    }                                                              //~9606I~//~9828I~
//    //***********************************************************************************//~9831R~
//    private void showDialog(int[][] PintssP)                       //~9830I~//~9831R~
//    {                                                              //~9830I~//~9831R~
//        ResumeDlg.newInstance(PintssP).show();                     //~9830I~//~9831R~
//                    if (Utils.isShowingDialogFragment(AG.aResumeDlg))//~9830I~//~9831R~
//                        AG.aResumeDlg.repaintOKNG(replyEswn,PintParm[RDPOS_OKNG]!=0); //callback updateOKNGAdditional//~9830I~//~9831R~
//    }                                                              //~9830I~//~9831R~
    //***********************************************************************************//~9831I~
    //*From HistoryDlg,on server or reqMsg from client             //~9831I~
    //***********************************************************************************//~9831I~
    public  static void showDialog(boolean PswServer,String Psender,HistoryData Phd)//~9831R~
    {                                                              //~9831I~
        if (Dump.Y) Dump.println("ResumeDlg.showDoialog swServer="+PswServer);//~9831I~
        if (Phd.getRuleProp()==null)                               //~9901I~
        {                                                          //~9901I~
//      	UView.showToastLong(Utils.getStr(R.string.Warn_ResumeDialogNoRuleFound));//~9901I~//~0212R~//~0213R~
        	showAlert(R.string.Warn_ResumeDialogNoRuleFound);      //~0213I~
            return;                                                //~9901I~
        }                                                          //~9901I~
        if (Utils.isShowingDialogFragment(AG.aResumeDlg))          //~9831I~
        {                                                          //~9831I~
        	HistoryData hd=AG.aResumeDlg.resumeHD;                 //~9831I~
  	        if (Dump.Y) Dump.println("ResumeDlg.showDoialog showing now="+hd.HD[HDPOS_HDR][POS_TIMESTAMP]+",new="+Phd.HD[HDPOS_HDR][POS_TIMESTAMP]);//~9831R~//~0314R~
            if (Psender!=null)                                     //~9831I~
            {                                                      //~9831I~
//              if (hd.HD[HDPOS_HDR][RDPOS_TIMESTAMP].compareTo(Phd.HD[HDPOS_HDR][RDPOS_TIMESTAMP])==0)//~9831R~//~0314R~
                if (hd.HD[HDPOS_HDR][POS_TIMESTAMP].compareTo(Phd.HD[HDPOS_HDR][POS_TIMESTAMP])==0)//~0314I~
                {                                                  //~9831I~
                    UView.showToast(Utils.getStr(R.string.Warn_ResumeDlgDupShow,Psender));//~9831I~
                    return;                                        //~9831I~
                }                                                  //~9831I~
                else                                               //~9831I~
                    UView.showToast(Utils.getStr(R.string.Warn_ResumeDlgNewShow,Psender));//~9831I~
            }                                                      //~9831I~
        }                                                          //~9831I~
//      else                                                       //~9831I~//~9902R~
//          if (Psender!=null)                                     //~9831I~//~9902R~
//          	UView.showToast(Utils.getStr(R.string.Info_ResumeDlgFirstShow,Psender));//~9831I~//~9902R~
        Phd.setScores();	//string to int                        //~9831I~
        if (!PswServer)                                            //~0111I~
	    	dismissHistoryDlg();                                   //~0111I~
        ResumeDlg.newInstance(PswServer,Phd).show();               //~9831R~
	}                                                              //~9831I~
    //***********************************************************************************//~9831I~
    //*received Reply                                              //~9831I~
    //***********************************************************************************//~9831I~
    public static void updateOKNGResume(String Psender,boolean PswOK,String Ptimestamp)//~9831R~
    {                                                              //~9831I~
        if (Dump.Y) Dump.println("ResumeDlg.updateOKNGResume sender="+Psender+",okng="+PswOK+",timestamp="+Ptimestamp);//~9831R~
        if (!Utils.isShowingDialogFragment(AG.aResumeDlg))         //~9831I~
        {                                                          //~9831I~
            UView.showToastLong(Utils.getStr(R.string.Warn_ResumeDlgReceivedReplyUnderNoDialog,Psender));//~9831I~//+0314R~
            return;                                                //~9831I~
        }                                                          //~9831I~
        HistoryData hd=AG.aResumeDlg.resumeHD;                     //~9831I~
//      if (Ptimestamp.compareTo(hd.HD[HDPOS_HDR][RDPOS_TIMESTAMP])!=0)//~9831R~//~0314R~
        if (Ptimestamp.compareTo(hd.HD[HDPOS_HDR][POS_TIMESTAMP])!=0)//~0314I~
        {                                                          //~9831I~
            UView.showToastLong(Utils.getStr(R.string.Warn_ResumeDlgReceivedReplyUnmatchTimestamp,Psender));//~9831I~//+0314R~
            return;                                                //~9831I~
        }                                                          //~9831I~
        int pos=hd.getOldPosition(Psender);                        //~9831R~
        if (pos<0)                                                 //~9831I~
        {                                                          //~9831I~
        	UView.showToast(Utils.getStr(R.string.Err_ReloadDataErr));//~9831I~
            return;                                                //~9831I~
        }                                                          //~9831I~
//  	AG.aResumeDlg.respStat[pos]=Pokng;                         //~9831R~
//  	AG.aResumeDlg.updateOKNGByPosition();                      //~9831R~
//      if (Dump.Y) Dump.println("ResumeDlg.updateOKNGResume respstat="+Arrays.toString(AG.aResumeDlg.respStat));//~9831I~
//  	AG.aResumeDlg.repaintOKNG(AG.aResumeDlg.respStat); //OKNGDlg//~9831R~
    	AG.aResumeDlg.repaintOKNG(pos,PswOK);                      //~9831I~
	}                                                              //~9831I~
    private static void showAlert(int Pmsgid)                             //~0213I~
    {                                                              //~0213I~
    	Alert.showMessage(R.string.Title_ResumeDlg,R.string.Warn_ResumeDialogNoRuleFound);//~0213I~
    }                                                              //~0213I~
}//class                                                           //~v@@@R~
