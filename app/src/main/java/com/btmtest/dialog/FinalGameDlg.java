//*CID://+vamsR~:                             update#= 1158;       //~vamsR~
//*****************************************************************//~v101I~
//2022/04/20 vams Menu:gameover fail by "During game" when FinalGame canceled//~vamsI~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~9305I~
import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.Complete;
import com.btmtest.game.GConst;
import com.btmtest.game.LastGame;
import com.btmtest.game.ScoreSort;
import com.btmtest.game.Status;
import com.btmtest.game.UserAction;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.URadioGroup;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.LastGame.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.utils.Alert.*;

public class FinalGameDlg  extends OKNGDlg //UFDlg                                            //~9312R~//~9321R~//~9504R~//~9520R~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.finalgame;             //~9312R~//~9504R~//~9520R~
    private static final int LAYOUTID_SMALLFONT=R.layout.finalgame_theme;//~vac5I~
    private static final int TITLEID=R.string.Title_FinalGame;//~9307I~//~9312R~//~9504R~//~9520R~
    private static final String HELPFILE="FinalGameDlg";                //~9220I~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9504R~//~9520R~
                                                                   //~9318I~
    public  static final int CLOSABLE_NONE                             =-1;//~9521R~
                                                                   //~9521I~
    public  static final int CLOSABLE_QUERY                            =0x0100;//~9521I~
    public  static final int CLOSABLE_QUERY_DEALER_RON                 =0x0101;//~9521I~
    public  static final int CLOSABLE_QUERY_DEALER_PENDING             =0x0102;//~9521I~
    public  static final int CLOSABLE_QUERY_DECLARED                   =0x0200;//~9523R~
                                                                   //~9521I~
    public  static final int CLOSABLE_CONTINUE                         =0x0400;//~9521I~//~9523R~
    public  static final int CLOSABLE_CONTINUE_DEALER_RON              =0x0401;//~9521I~//~9523R~
    public  static final int CLOSABLE_CONTINUE_DEALER_PENDING          =0x0402;//~9521I~//~9523R~
    public  static final int CLOSABLE_CONTINUE_ALLMINUS                =0x0403;//~9521I~//~9523R~
    public  static final int CLOSABLE_CONTINUE_DRAWN_DEALER_NOTPENDING =0x0404;//~9521I~//~9523R~
    public  static final int CLOSABLE_CONTINUE_DRAWNHW_NEXT            =0x0405;//~9819I~
    public  static final int CLOSABLE_CONTINUE_DRAWNHW_SAME            =0x0406;//~9819I~
                                                                   //~9521I~
    public  static final int CLOSABLE_GAMEOVER                         =0x0800;//~9521I~//~9523R~
    public  static final int CLOSABLE_GAMEOVER_NONDEALER_RON           =0x0801;//~9521I~//~9523R~
    public  static final int CLOSABLE_GAMEOVER_DEALER_NOTPENDING       =0x0802;//~9521I~//~9523R~
    public  static final int CLOSABLE_GAMEOVER_DRAWNHW                 =0x0803;//~9819I~
                                                                   //~9521I~
    public  static final int CLOSABLE_FIXED                            =0x1000;//~9524I~
                                                                   //~9524I~
    private static final int POS_SCOREMSGTYPE=1;                    //~9318R~//~9320R~
    private static final int POS_ENDGAMETYPE=2;                    //~9320I~
    private static final int POS_NEXTGAMETYPE=3;                  //~9318R~//~9320R~
    private static final int POS_TYPECLOSE=4;                      //~9523I~
                                                                   //~9523I~
    private static final int POS_AMMOUNT=4;                        //~9318R~
    private static final int POS_AMMOUNT_CONFIRM=4;                //~9321R~
    private static final int POS_REPLAY_ESWN=0;                    //~9321R~
    private static final int POS_OKNG=4;                           //~9321R~
    private static final int POS_AMMOUNT_CONFIRMED=2;              //~9321I~
    private static final int POS_AMMOUNT_CONFIRMED_MINUSPRIZE=POS_AMMOUNT_CONFIRMED+PLAYERS;//~9415R~
    private static final int POS_AMMOUNT_CONFIRMED_CHARGE=POS_AMMOUNT_CONFIRMED+PLAYERS*2;//~9415I~
//  private static final int POS_CHARGE_RESULT=POS_AMMOUNT_CONFIRMED+5*PLAYERS;//~9408R~
                                                                   //~9318I~
    private static final int ENDGAME_SCORE_UPDATE=1;            //~9318I~
    private static final int ENDGAME_SCORE_MINUS=2;                //~9320I~
    private static final int ENDGAME_SCORE_CONFIRM_REQUEST=3;      //~9321I~
    private static final int ENDGAME_SCORE_CONFIRM_REPLY=4;        //~9321I~
    private static final int ENDGAME_SCORE_CONFIRMED=5;            //~9321I~
                                                                   //~9322I~
    private static final int UCBP_MINUSSTOP=100;                    //~9322I~
//  private static final int[] rbIDMinusPay=new int[]{R.id.rbMinusPayYes,R.id.rbMinusPayNoGetPointAll,R.id.rbMinusPayNoGetPointLimited};//~9402I~//~9414R~
                                                                   //~9521I~
    private static final String strCompStatRon=Utils.getStr(R.string.Label_CompStatRon);//~9521I~
    private static final String strCompStatMangan=Utils.getStr(R.string.Label_CompStatMangan);//~9521I~
    private static final String strCompStatPending=Utils.getStr(R.string.Label_CompStatPending);//~9521I~
    private static final String strCompStatPendingReach=Utils.getStr(R.string.LabelReachers);//~9521I~
    private static final String strCompStatDrawnHW=Utils.getStr(R.string.UserAction_DrawnHW);//~9522I~
                                                                   //~9309I~
    private TextView[] tvsOrder,tvsTotal;                       //~9312I~//~9316R~//~9321R~//~9520R~
    private TextView[] tvsName,tvsEswn;                            //~9520R~
                                                                   //~9520I~
    private int[] ammount,minusScore;                              //~9414R~
    private int[] minusPay=new int[PLAYERS];	//idx seq          //~9414I~
    private int[] newTotal=new int[PLAYERS];                       //~9312I~
    private int[] oldTotal=new int[PLAYERS];                       //~9402I~
    private int[] payers=new int[PLAYERS];                         //~9322I~
    private String[] accountNames;                                 //~9312I~
                                                                   //~9320I~
    private boolean swMinusStop,swMinus0,/*swMinusRobot,*/swMinusPay,swMinusPayGetAllPoint;//~9402R~//~9429R~
    private int pointMinusStop;
    private boolean swMinusCharge;                                    //~9320I~//~9408R~
    private TextView[] tvsCompStat,tvsPendingReach;                //~9521R~
//  private TextView tvSetPayer;                                  //~9402I~//~9429R~
    private UCheckBox[][] cbssPayTo=new UCheckBox[PLAYERS][PLAYERS];//~9320I~
//  private UCheckBox cbMinusStop,cbMinus0,/*cbMinusPay,*/cbMinusRobot;//~9320I~//~9402R~//~9408R~
    private UCheckBox             cbMinus0/*cbMinusPay,*//*cbMinusRobot*/;//~9408I~//~9429R~
    private Button btnGameOver,btnGameContinue;                    //~9522I~
    private URadioGroup rgMinusPay;	//for TEST calc                //~9402I~
    private LinearLayout[] llsMinus=new LinearLayout[PLAYERS];     //~9320I~
    private LinearLayout[] llsMinusPay,llsMinusChargeLater;        //~9416I~
    private Button btnFixGame,btnShowRule,btnSend;                           //~9417R~//~9523R~//~9525R~
    private boolean swReceived;                                    //~9321R~
    private Accounts ACC;                                          //~9322I~
    private Complete CMP;                                          //~9322I~
    private boolean swInitLayout;                                          //~9322I~
//  private boolean swNoPayMinusError=true;	//TODO                 //~9414R~//~0322R~
//  private int[] payerInfo=new int[PLAYERS*PLAYERS];              //~9322I~//~9408R~
    private boolean swDismissBeforNew;                             //~9322I~
//  private int[] eswnMinusPrizeTo=new int[PLAYERS];               //~9403R~//~9415R~
    private int[] chargeToGainer=new int[PLAYERS];                 //~9408I~
    private int[] chargeToGainerResult=new int[PLAYERS];           //~9408R~
    private int parmEndGameType,caseMinusStopByErr;                                   //~9413I~
    private int dealerPosition=ESWN_N;                             //~9504R~
    private boolean swRon;
    private TextView tvDesc;                                       //~9521I~
                                                                   //~9520I~
    private int typeClose;                                         //~9520I~
    private int endgameType;                                       //~9522R~//~9523R~
    private int nextgameTypeDeclared;                              //~9523I~
    private boolean swServer;                                      //~9523R~
    private int currentEswn;                                       //~9523I~
    private int[] statOKNG;                                        //~9524I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public FinalGameDlg()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9504R~//~9520R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("FinalGameDlg.defaultConstructor"); //~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9504R~//~9520R~
        AG.aFinalGameDlg=this;                                         //~9321I~//~9504R~//~9520R~
        Status.setShownFinalGame();	//server and client            //~vamsI~
//      swRon=AG.aComplete.isCompletedDealerRon();                       //~9504I~//~9520R~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
    public static FinalGameDlg newInstance(int PtypeClose,int PendgameType)                        //~9504R~//~9520R~//~9522R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("FinalGameDlg.newInstance typeClose="+Integer.toHexString(PtypeClose)+",endgameType="+PendgameType);//~9504R~//~9520R~//~9521R~//~9522R~
    	FinalGameDlg dlg=new FinalGameDlg();                                     //~v@@@I~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9504R~//~9520R~
//  	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9227R~//~9504R~//~9520R~//~vac5R~
    	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//~vac5I~
    			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_CLOSEBTN|FLAG_HELPBTN|FLAG_RULEBTN,//~v@@@I~//~9220R~//~9305R~//~9312R~//~9316R~//~9321R~//~9708R~
				TITLEID,HELPFILE);         //~v@@@I~               //~9220R~//~9520R~
        dlg.typeClose=PtypeClose;                                  //~9520R~
        dlg.endgameType=PendgameType;                              //~9522I~
        return dlg;                                                //~9520I~
    }                                                              //~v@@@R~
    //******************************************                   //~9523I~
    public static FinalGameDlg newInstance(int PtypeClose,int PendgameType,int PnextgameTypeDeclared)//~9523R~
    {                                                              //~9523I~
        if (Dump.Y) Dump.println("FinalGameDlg.newInstance nextgameTypeDeclaed="+PnextgameTypeDeclared);//~9523R~
    	FinalGameDlg dlg=newInstance(PtypeClose,PendgameType);     //~9523I~
        dlg.nextgameTypeDeclared=PnextgameTypeDeclared;            //~9523R~
        return dlg;                                                //~9523I~
    }                                                              //~9523I~
    //******************************************                   //~9523I~
    public static FinalGameDlg newInstance(int PtypeClose,int PendgameType,int PnextgameTypeDeclared,int[] PrespStat)//~9523I~//~9524R~
    {                                                              //~9523I~
        if (Dump.Y) Dump.println("FinalGameDlg.newInstance respStat="+Arrays.toString(PrespStat));//~9523I~
    	FinalGameDlg dlg=newInstance(PtypeClose,PendgameType,PnextgameTypeDeclared);//~9523I~
        dlg.statOKNG=PrespStat;                                    //~9524R~
        return dlg;                                                //~9523I~
    }                                                              //~9523I~
    //******************************************                   //~9520I~
    public static FinalGameDlg newInstance(int PendGameType,int[] Pammount,int[] Pscore)//~9520I~
    {                                                              //~9520I~
        if (Dump.Y) Dump.println("FinalGameDlg.newInstance amt="+Arrays.toString(Pammount));//~9520I~
    	FinalGameDlg dlg=new FinalGameDlg();                       //~9520I~
//  	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9520I~//~vac5R~
    	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//~vac5I~
    			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_CLOSEBTN|FLAG_HELPBTN,//~9520I~
				TITLEID,HELPFILE);                                 //~9520I~
        dlg.ammount=Pammount;	//point at last game(not total score)//~9520I~
//      Pscore[0]=30000;    //TODO TEST                            //~9520I~
        dlg.minusScore=Pscore;	//point at last game(not total score)//~9520I~
        dlg.parmEndGameType=PendGameType;                          //~9520I~
        return dlg;                                                //~9520I~
    }                                                              //~9520I~
    //******************************************                   //~9520I~
    public static FinalGameDlg newInstance(int PendgameType,int[] Pammount,int[] Pscore,int[] PminusPay,int[] PnewTotal,int[] Pcharge,int[] PchargeResult)//~9520I~
    {                                                              //~9520I~
        if (Dump.Y) Dump.println("FinalGameDlg.newInstance amt="+Arrays.toString(Pammount));//~9520I~
        if (Dump.Y) Dump.println("FinalGameDlg.newInstance score="+Arrays.toString(Pscore));//~9520I~
        if (Dump.Y) Dump.println("FinalGameDlg.newInstance pay="+Arrays.toString(PminusPay));//~9520I~
        if (Dump.Y) Dump.println("FinalGameDlg.newInstance newtotal="+Arrays.toString(PnewTotal));//~9520I~
//      if (Dump.Y) Dump.println("FinalGameDlg.newInstance newtotal="+Arrays.toString(PpayerInfo));//~9520I~
        if (Dump.Y) Dump.println("FinalGameDlg.newInstance charge="+Arrays.toString(Pcharge));//~9520I~
        if (Dump.Y) Dump.println("FinalGameDlg.newInstance chargeResult="+Arrays.toString(PchargeResult));//~9520I~
//  	FinalGameDlg dlg=newInstance(Pammount,Pscore);             //~9520I~
    	FinalGameDlg dlg=newInstance(PendgameType,Pammount,Pscore);//~9520I~
        dlg.swReceived=true;                                       //~9520I~
        dlg.minusPay=PminusPay;                                    //~9520I~
        dlg.newTotal=PnewTotal;                                    //~9520I~
//      dlg.payerInfo=PpayerInfo;                                  //~9520I~
        dlg.chargeToGainer=Pcharge;                                //~9520I~
        dlg.chargeToGainerResult=PchargeResult;                    //~9520I~
        return dlg;                                                //~9520I~
    }                                                              //~9520I~
    //******************************************                 //~9303R~//~9312R~
    @Override                                                    //~9303R~//~9305R~
    protected void initLayoutAdditional(View PView)                            //~v@@@I~//~9303R~//~9305R~//~9321R~
    {                                                              //~v@@@M~//~9303R~//~9305R~
        if (Dump.Y) Dump.println("FinalGameDlg.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9303R~//~9304R~//~9305R~//~9307R~//~9312R~//~9504R~//~9520R~
        if (Dump.Y) Dump.println("FinalGameDlg:initLayout ammount="+Arrays.toString(ammount));//~9320I~//~9504R~//~9520R~
        if (Dump.Y) Dump.println("FinalGameDlg:initLayout score="+Arrays.toString(ACC.score));//no minus status//~9320I~//~9322R~//~9504R~//~9520R~
        AG.aLastGame.swFixed=false; //sw of fixed NEXTROUND/CONTINUE//~0111I~
        swServer=Accounts.isServer();                              //~9523I~
		swRequester=OKNGDlg.isDealer();                            //~9523R~
		currentEswn=Accounts.getCurrentEswn();                    //~9225I~//~9227R~//~9523I~
        swInitLayout=true;                                         //~9322I~
        getRuleSetting();                                          //~9320I~
                                                                   //~9523I~
        tvDesc=(TextView)    UView.findViewById(PView,R.id.tvFinalGameDescription);//~9521I~
        btnShowRule     =              UButton.bind(PView,R.id.ShowRule,this);//~9417I~//~9523M~
        btnGameOver = UButton.bind(layoutView,R.id.GameOver,this); //~9522M~//~9523M~
        btnGameContinue = UButton.bind(layoutView,R.id.GameContinue,this);//~9522M~//~9523M~
        btnFixGame = UButton.bind(layoutView,R.id.FixGame,this);   //~9523I~
        btnSend    = UButton.bind(layoutView,R.id.Send,this);      //~9525I~
                                                                   //~9523I~
        setupAmmount(PView);                                       //~9312I~//~9521M~
//  	if (PrefSetting.isNoRelatedRule())                         //~9520I~//~9708R~
//      	((LinearLayout)UView.findViewById(PView,R.id.llRelatedRule)).setVisibility(View.GONE);//~9520I~//~9708R~
//      else                                                       //~9520I~//~9708R~
//      {                                                          //~9520I~//~9708R~
	        RuleSetting.setGameSet(PView,true/*swFixed*/);                              //~9520I~
            RuleSetting.setPendingCont(PView,true/*swFixed*/);     //~9709I~
//      }                                                          //~9520I~//~9708R~
        setAccountName();                                          //~9309I~
        setDescription();                                          //~9521I~
	    showAmmount();                                             //~9309I~
        if (!swReceived)                                           //~9402I~
	        swInitLayout=false;	//activate chkbox change           //~9402I~
        if (statOKNG!=null)                                        //~9524R~
        	repaintOKNG(statOKNG);                          //~9523I~//~9524R~
        swInitLayout=false;                                        //~9322I~
        saveStatus();                                              //~9521I~
        hideResponseEswn();                                        //~0217I~
    }                                                              //~v@@@M~//~9303R~//~9305R~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    protected void setupValueAdditional(View PView)                //~9321I~
    {                                                              //~9321I~
    	if (Dump.Y) Dump.println("FinalGameDlg.initEnv");              //~9321I~//~9504R~//~9520R~
        ACC=AG.aAccounts;                                          //~9322I~
        CMP=AG.aComplete;                                          //~9322I~
    	accountNames=ACC.getAccountNamesByPosition();              //~9416I~
    }                                                              //~9322I~
    //******************************************                   //~0217I~
    private void hideResponseEswn()                                //~0217I~
    {                                                              //~0217I~
    	if (Dump.Y) Dump.println("FinalGameDlg.hideResponseEswn"); //~0217I~
        hideResponseEswn(!isDealer());                             //~0217R~
    }                                                              //~0217I~
    //******************************************                   //~v@@@I~//~9220R~//~9303R~//~9306R~
    private void saveStatus()                                        //~v@@@I~//~9220R~//~9303R~//~9306R~//~9521R~
    {                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
        CMP.typeClose=typeClose;                                   //~9521R~
    }                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
//    //******************************************                 //~9521I~
//    private void setTitle()                                      //~9521I~
//    {                                                            //~9521I~
//        Spanned s=Status.getSpannedGameTitle(Utils.getStr(TITLEID));//~9521I~
//        androidDlg.setTitle(s);                                  //~9521I~
//    }                                                            //~9521I~
    //******************************************                   //~9320I~
    private void getRuleSetting()                                  //~9320I~
    {                                                              //~9320I~
    	swMinusStop=RuleSetting.isMinusStop();                     //~9320I~
    	swMinus0=RuleSetting.isZeroStop();                         //~9320I~
    	swMinusPay=RuleSetting.isMinusPay();                       //~9320I~//~9322R~
    	swMinusPayGetAllPoint=RuleSetting.isMinusPayGetAllPoint(); //~9402I~
    	swMinusCharge=swMinusStop;                                 //~9423I~
    	pointMinusStop=RuleSetting.getPointMinusStop();           //~9408I~
    	caseMinusStopByErr=RuleSetting.getMinusStopByErr();        //~9414I~
        if (Dump.Y) Dump.println("FinalGameDlg:getRuleSetting swMinusPayGetAllPoint="+swMinusPayGetAllPoint);//~9415I~//~9504R~//~9520R~
    }                                                              //~9320I~
    //******************************************                   //~9309I~
    protected void setupAmmount(View PView)                        //~9309I~
    {                                                              //~9309I~
        LinearLayout[] lls=new LinearLayout[PLAYERS];              //~9321I~//~9520R~
        lls[0]=(LinearLayout)    UView.findViewById(PView,R.id.llResultE);//~9309I~//~9321R~//~9520R~
        lls[1]=(LinearLayout)    UView.findViewById(PView,R.id.llResultS);//~9321I~//~9520R~
        lls[2]=(LinearLayout)    UView.findViewById(PView,R.id.llResultW);//~9321I~//~9520R~
        lls[3]=(LinearLayout)    UView.findViewById(PView,R.id.llResultN);//~9321I~//~9520R~
        tvsEswn=new TextView[PLAYERS];                             //~9321I~//~9520R~
        tvsOrder=new TextView[PLAYERS];                             //~9321I~//~9520R~
        tvsCompStat=new TextView[PLAYERS];                         //~9521I~
        tvsPendingReach=new TextView[PLAYERS];                     //~9521I~
        tvsName=new TextView[PLAYERS];                             //~9520I~
        tvsTotal=new TextView[PLAYERS];                            //~9321I~//~9520R~
        for (int ii=0;ii<PLAYERS;ii++)                                 //~9321I~//~9520R~
        {                                                          //~9321I~//~9520R~
            LinearLayout ll=lls[ii];                               //~9321I~//~9520R~
            tvsEswn[ii]=(TextView)    UView.findViewById(ll,R.id.nameESWN);//~9321I~//~9520R~
            tvsOrder[ii]=(TextView)     UView.findViewById(ll,R.id.Order);//~9321I~//~9520I~
            tvsCompStat[ii]=(TextView)     UView.findViewById(ll,R.id.tvCompStatus);//~9521I~
            tvsPendingReach[ii]=(TextView)     UView.findViewById(ll,R.id.tvPendingReachCtr);//~9521I~
            tvsTotal[ii]=(TextView)   UView.findViewById(ll,R.id.Total);//~9321I~//~9414R~//~9520I~
            tvsName[ii]=(TextView)    UView.findViewById(ll,R.id.memberName);//~9321I~//~9520R~
        }                                                          //~9321I~//~9520R~
    }                                                              //~9309I~
    //******************************************                   //~9309I~
    private void setAccountName()                                //~9309I~//~9523R~
    {                                                              //~9309I~
    	if (Dump.Y) Dump.println("FinalGameDlg.setAccountName");    //~9309I~//~9316R~//~9504R~//~9520R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9309I~
        {                                                          //~9309I~
        	tvsName[ii].setText(accountNames[ii]);                 //~9309I~
            int eswn = ACC.getCurrentEswnByPosition(ii);           //~9416I~
	        tvsEswn[ii].setText(GConst.nameESWN[eswn]); //~9312I~  //~9320R~
        }                                                          //~9309I~
    }                                                              //~9309I~
    //******************************************                   //~9521I~
    private void setDescription()                                //~9521I~//~9523R~
    {                                                              //~9521I~
    	if (Dump.Y) Dump.println("FinalGameDlg.setDescription closable=0x"+Integer.toHexString(typeClose)+",endgameType="+endgameType);//~9521I~//~9523R~//~9819R~
        int resid=0;                                               //~9521I~
        if ((typeClose & CLOSABLE_QUERY_DECLARED)!=0)              //~9523I~
        {                                                          //~9523I~
            switch(typeClose & ~CLOSABLE_QUERY_DECLARED)           //~9523I~
            {                                                      //~9523I~
            case CLOSABLE_QUERY_DEALER_RON:                        //~9523I~
                if (nextgameTypeDeclared==LASTGAME_GAMEOVER)       //~9523I~
    	            resid=R.string.Info_QueryDealerRonDeclaredGameover;//~9523I~
                else                                               //~9523I~
    	            resid=R.string.Info_QueryDealerRonDeclaredContinue;//~9523I~
                break;                                             //~9523I~
            case CLOSABLE_QUERY_DEALER_PENDING:                    //~9523I~
                if (nextgameTypeDeclared==LASTGAME_GAMEOVER)       //~9523I~
    	            resid=R.string.Info_QueryDealerPendingDeclaredGameover;//~9523I~
                else                                               //~9523I~
    	            resid=R.string.Info_QueryDealerPendingDeclaredContinue;//~9523I~
                break;                                             //~9523I~
            }                                                      //~9523I~
        }                                                          //~9523I~
        else                                                       //~9523I~
        {                                                          //~9523I~
            switch(typeClose)                                          //~9521I~//~9523R~
            {                                                          //~9521I~//~9523R~
            case CLOSABLE_QUERY_DEALER_RON:       //~9521I~        //~9523R~
                if (swRequester)                                   //~9523R~
                    resid=R.string.Info_QueryDealerRonDealer;                    //~9521I~//~9523R~
                else                                               //~9523R~
                    resid=R.string.Info_QueryDealerRonNonDealer;   //~9523R~
                break;                                                 //~9521I~//~9523R~
            case CLOSABLE_QUERY_DEALER_PENDING:                        //~9521I~//~9523R~
                if (swRequester)                                   //~9523R~
                    resid=R.string.Info_QueryDealerPendingDealer;                //~9521I~//~9523R~
                else                                               //~9523R~
                    resid=R.string.Info_QueryDealerPendingNonDealer;//~9523R~
                break;                                                 //~9521I~//~9523R~
            case CLOSABLE_CONTINUE_DEALER_RON:                         //~9521I~//~9523R~
                resid=R.string.Info_ContinueDealerRon;                 //~9521I~//~9523R~
                break;                                                 //~9521I~//~9523R~
            case CLOSABLE_CONTINUE_DEALER_PENDING:                     //~9521I~//~9523R~
                resid=R.string.Info_ContinueDealerPending;             //~9521I~//~9523R~
                break;                                                 //~9521I~//~9523R~
            case CLOSABLE_CONTINUE_ALLMINUS:                           //~9521I~//~9523R~
                resid=R.string.Info_ContinueAllMinus;                  //~9521I~//~9523R~
                break;                                                 //~9521I~//~9523R~
            case CLOSABLE_CONTINUE_DRAWN_DEALER_NOTPENDING:            //~9521I~//~9523R~
                resid=R.string.Info_ContinueDrawn;                     //~9521I~//~9523R~
                break;                                                 //~9521I~//~9523R~
            case CLOSABLE_CONTINUE_DRAWNHW_NEXT:                   //~9819I~
    	    	resid=R.string.Info_ContinueDrawnHWNext;           //~9819I~
                break;                                             //~9819I~
            case CLOSABLE_CONTINUE_DRAWNHW_SAME:                   //~9819R~
    	    	resid=R.string.Info_ContinueDrawnHW;               //~9819R~
                break;                                             //~9819I~
            case CLOSABLE_GAMEOVER_NONDEALER_RON:                      //~9521I~//~9523R~
                resid=R.string.Info_GameoverNonDealerRon;              //~9521I~//~9523R~
                break;                                                 //~9521I~//~9523R~
            case CLOSABLE_GAMEOVER_DEALER_NOTPENDING:                 //~9521I~//~9523R~
                resid=R.string.Info_GameoverDealerNotPending;       //~9521I~//~9523R~//~9527R~
                break;                                                 //~9521I~//~9523R~
            case CLOSABLE_GAMEOVER_DRAWNHW:                        //~9819I~
    	    	resid=R.string.Info_GameoverDrawnHW;               //~9819I~
                break;                                             //~9819I~
            }                                                          //~9521I~//~9523R~
        }                                                          //~9523I~
        if (resid!=0)                                              //~9521I~
        	tvDesc.setText(Utils.getStr(resid));                   //~9521I~
    }                                                              //~9521I~
    //******************************************                   //~9309I~
    private void showAmmount()                                     //~9309I~
    {                                                              //~9309I~
    	if (swReceived)                                            //~9321I~
        {                                                          //~9321I~
		    showAmmountReceived();                                 //~9321I~
            return;                                                //~9321I~
        }                                                          //~9321I~
        int[] score=ACC.score;                                     //~9520I~
	    int[] ctrReach=AG.aAccounts.intsCtrPendingReach;           //~9521I~//~9522R~
        int[] sortin=new int[PLAYERS];                              //~9522I~
        for (int pos=0;pos<PLAYERS;pos++)     //account sequence   //~9522I~
        {                                                          //~9522I~
            sortin[pos]=score[pos]+ctrReach[pos]*POINT_REACH;      //~9522R~
        }                                                          //~9522I~
        AG.aLastGame.endgameScore=sortin;	//for the case gameover declared//~9524I~
	    int[] pos2Order=getPos2Order(sortin);                      //~9520I~//~9521R~//~9522I~
        for (int pos=0;pos<PLAYERS;pos++)     //account sequence      //~9309I~//~9520R~
        {                                                          //~9309I~
            tvsOrder[pos].setText(Integer.toString(pos2Order[pos]+1));//~9520R~
            int ctr=ctrReach[pos];                                 //~9521I~
            tvsPendingReach[pos].setText(ctr==0 ? "" : Integer.toString(ctr));//~9521I~
            tvsCompStat[pos].setText(getCompStatus(pos));          //~9521I~
            tvsTotal[pos].setText(Integer.toString(sortin[pos]));//~9416I~//~9520R~//~9521I~//~9522R~
        }                                                          //~9309I~
        if (Dump.Y) Dump.println("FinalGameDlg:showAmmount score="+Arrays.toString(score));//~9520R~
        if (Dump.Y) Dump.println("FinalGameDlg:showAmmount score+reach="+Arrays.toString(sortin));//~9522I~
        if (Dump.Y) Dump.println("FinalGameDlg:showAmmount pos2Order="+Arrays.toString(pos2Order));//~9520R~
    }                                                              //~9309I~
    //******************************************                   //~9520I~
    private int[] getPos2Order(int[] Pscore)                        //~9520I~
    {                                                              //~9520I~
    	int[] pos2Order=new ScoreSort().getOrder(Pscore);           //~9520I~
        if (Dump.Y) Dump.println("FinalGameDlg.getPos2Order score="+Arrays.toString(Pscore)+",pos2Order="+Arrays.toString(pos2Order));//~9520R~
        return pos2Order;                                          //~9520I~
    }                                                              //~9520I~
    //******************************************                   //~9521I~
    private String getCompStatus(int Ppos)                         //~9521I~
    {                                                              //~9521I~
        String s;                                                  //~9521I~
        if (endgameType==EGDR_DRAWN_HW)                            //~9522I~
            s=strCompStatDrawnHW;                                  //~9522I~
        else                                                       //~9522I~
        {                                                          //~9522I~
	    	int[] cts=AG.aComplete.intsCompType;                       //~9521I~//~9522I~
    		int ct=cts[Ppos];                                           //~9521I~//~9522I~
            switch(ct)                                                 //~9521I~//~9522R~
            {                                                          //~9521I~//~9522R~
            case EGDR_NORMAL:                                          //~9521I~//~9522R~
                s=strCompStatRon;                                      //~9521I~//~9522R~
                break;                                                 //~9521I~//~9522R~
            case EGDR_MANGAN_RON:                                      //~9521I~//~9522R~
                s=strCompStatMangan;                                   //~9521I~//~9522R~
                break;                                                 //~9521I~//~9522R~
            case EGDR_PENDING:                                         //~9521R~//~9522R~
                s=strCompStatPending;                                  //~9521I~//~9522R~
                break;                                                 //~9521I~//~9522R~
            case EGDR_PENDING_REACH:                                   //~9521I~//~9522R~
                s=strCompStatPendingReach;                             //~9521I~//~9522R~
                break;                                                 //~9521I~//~9522R~
            default:                                                   //~9521I~//~9522R~
                s="";                                                  //~9521I~//~9522R~
            }                                                          //~9521I~//~9522R~
	        if (Dump.Y) Dump.println("FinalGameDlg:getCompStatus pos="+Ppos+",comptype="+ct+",intsCompstat="+Arrays.toString(cts));//~9522I~
        }                                                          //~9522I~
        if (Dump.Y) Dump.println("FinalGameDlg:getCompStatus rc="+s);//~9522R~
        return s;                                                  //~9521I~
    }                                                              //~9521I~
//    //******************************************                   //~9321I~//~9520R~
    private void showAmmountReceived()                             //~9321I~//~9520R~
    {                                                              //~9321I~//~9520R~
        if (Dump.Y) Dump.println("FinalGameDlg:showAmmountReceived");  //~9321I~//~9504R~//~9520R~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9321I~//~9520R~
        {                                                          //~9321I~//~9520R~
//          int idx=ACC.currentEswnToMember(ii);          //~9321I~//~9322R~//~9416R~//~9520R~
//          tvsAdd[idx].setText(Integer.toString(ammount[ii]));    //~9321I~//~9416R~//~9520R~
            int eswn=ACC.getCurrentEswnByPosition(ii);                //~9416I~//~9520R~
            int mp=minusPay[ii];                                   //~9416I~//~9520R~
            tvsTotal[ii].setText(Integer.toString(newTotal[ii]));  //~9321I~//~9520R~
        }                                                          //~9321I~//~9520R~
    }                                                              //~9321I~//~9520R~
    //*******************************************************      //~9321I~
    @Override                                                      //~9321I~
    public void onDismissDialog()                                  //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("CompleteDlg.onDismissDialog");   //~9321I~
        if (!swDismissBeforNew)                                    //~9322I~
	        AG.aFinalGameDlg=null;                                         //~9321I~//~9322R~//~9504R~//~9520R~
        swDismissBeforNew=false;                                   //~9322I~
    }                                                              //~9321I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void setButton()                                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("FinalGameDlg.setButton swTrainingMode="+AG.swTrainingMode+",typeClose="+Integer.toHexString(typeClose));//~9523I~//~9524R~//~va66R~
        if ((typeClose & CLOSABLE_QUERY_DECLARED)!=0)              //~9523I~
        {                                                          //~9523I~
            btnOK.setVisibility(View.VISIBLE);                     //~9523I~
//          btnClose.setVisibility(View.VISIBLE);                  //~9523R~//~9524R~
        	if (swRequester)                                       //~9523R~
            {                                                      //~9523I~
//          	btnGameContinue.setVisibility(View.GONE);          //~9523R~//~9524R~
//          	btnGameOver.setVisibility(View.GONE);              //~9523I~//~9524R~
	        	btnOK.setVisibility(View.GONE);                    //~9523I~
	            btnCancel.setVisibility(View.GONE); //NG button    //~9523I~
	            btnFixGame.setVisibility(View.VISIBLE);            //~9525R~
	            btnFixGame.setEnabled(swAllOK);                      //~9523I~//~9524R~
            }                                                      //~9523I~
            else                                                   //~9523I~
            {                                                      //~9523I~
	            btnCancel.setVisibility(View.VISIBLE); //NG button //~9523I~
            }                                                      //~9523I~
        }                                                          //~9523I~
        else                                                       //~9523I~
        if ((typeClose & CLOSABLE_QUERY)!=0)                       //~9522M~
        {                                                          //~9522M~
            btnOK.setVisibility(View.GONE);                        //~9523I~
            btnCancel.setVisibility(View.GONE); //NG button        //~9523I~
        	if (swRequester)                                       //~9523R~
            {                                                      //~9523I~
	        	btnGameOver.setVisibility(View.VISIBLE);               //~9522M~//~9523R~
	        	btnGameContinue.setVisibility(View.VISIBLE);           //~9522M~//~9523R~
            }                                                      //~9523I~
        }                                                          //~9522M~
        else                                                       //~9523I~//~9524R~//~9525R~
        {                                                          //~9523I~//~9524R~//~9525R~
        	if (swRequester)                                       //~9525I~
            {                                                      //~9525I~
	            btnFixGame.setVisibility(View.VISIBLE);            //~9525I~
	            btnFixGame.setEnabled(swAllOK);                    //~9525I~
	            btnOK.setVisibility(View.GONE);                    //~9525I~
	            btnCancel.setVisibility(View.GONE);                //~9902I~
              if (AG.swTrainingMode)                               //~va66I~
                btnSend.setVisibility(View.GONE);                  //~va66I~
              else                                                 //~va66I~
	            btnSend.setVisibility(View.VISIBLE);               //~9525I~
            }                                                      //~9525I~
        }                                                          //~9523I~//~9524R~//~9525R~
    }                                                              //~9321I~
    //*******************************************************      //~9305I~//~9523I~
    //*when dealer pushed Gameover/Continue button                 //~9523I~//~9524R~
    //*******************************************************      //~9523I~
    public void update()                                           //~9305R~//~9523I~
    {                                                              //~9305I~//~9523I~
        if (Dump.Y) Dump.println("FinalGameDlg.update");            //~9523I~
	    setDescription();                                          //~9523I~
        setButton();                                               //~9523I~
    }                                                              //~9305I~//~9523I~
    //*******************************************************************//~9321I~
    //*on UiThread                                                 //~9321I~
    //*******************************************************************//~9321I~
    @Override                                                      //~9321I~//~9523R~
//  protected void updateOKNGAdditional(int PctrNone,int PctrNG,boolean PswAllOK)//~9321I~//~9523R~//~0119R~
    protected void updateOKNGAdditional(int PctrNone,int PctrNG,int PctrDisconnected,boolean PswAllOK)//~0119I~
    {                                                              //~9321I~//~9523R~
        if (Dump.Y) Dump.println("FinalGameDlg.updateOKNGAdditional ctrNG="+PctrNG+",ctrNone="+PctrNone+",swAllOK="+PswAllOK);//~9321I~//~9504R~//~9520R~//~9523R~
        btnFixGame.setEnabled(PswAllOK);                             //~9321I~//~9523R~
        if (PctrNone==0 && (PctrNG!=0 || PctrDisconnected!=0)) //all responsed, someone replyed NG//~0112I~//~0119R~
        {                                                          //~0112I~
            if (swRequester)                                       //~0112I~
                alertToForceOK(this,TITLEID,R.string.Alert_FinalGameDlgForceOK);//~0112I~
        }                                                          //~0112I~
    }                                                              //~9321I~//~9523R~
    //*******************************************************************//~0112I~
    @Override                                                      //~0112I~
	protected void alertActionReceived(int Pbuttonid,int Prc)      //~0112I~
    {                                                              //~0112I~
        if (Dump.Y) Dump.println("FinalGameDlg.alerActionReceived buttonID="+Pbuttonid+",rc="+Prc);//~0112I~
    	if (Pbuttonid==BUTTON_POSITIVE)                            //~0112I~
        {                                                          //~0112I~
	        btnFixGame.setEnabled(true/*PswAllOK*/);                 //~0112I~
        }                                                          //~0112I~
    }                                                              //~0112I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void onClickOK()                                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("FinalGameDlg.onClickOK swRequester="+swRequester+",typeClose="+Integer.toHexString(typeClose)+",endgameType="+endgameType+",nextGameTypeDeclared="+nextgameTypeDeclared);//~9321I~//~9504R~//~9520R~//~9523R~
        sendToAllReply(true);                                       //~9321I~//~9523R~
		dismiss();                                          //~9321I~//~9523R~
    }                                                              //~9321I~
    //******************************************                   //~9525I~
    public void onClickSend()                                           //~9525I~
    {                                                              //~9525I~
        if (Dump.Y) Dump.println("FinalGameDlg.onClickSend swRequester="+swRequester+",typeClose="+Integer.toHexString(typeClose)+",endgameType="+endgameType+",nextGameTypeDeclared="+nextgameTypeDeclared);//~9525I~
//      sendToAllReply(true);                                      //~9525I~//~0217R~
        LastGame.sendToAll(LASTGAME_DECLARED,typeClose,endgameType,LASTGAME_GAMEOVER);//~0217I~
		Arrays.fill(AG.aLastGame.respOKNG,EGDR_NONE);              //~0217I~
        repaintOKNG(AG.aLastGame.respOKNG);                        //~0217I~
    }                                                              //~9525I~
    //******************************************                   //~9524I~
    @Override                                                      //~9524I~
    public void onClickClose()                                     //~9524I~
    {                                                              //~9524I~
        if (Dump.Y) Dump.println("FinalGameDlg.onClickClose");     //~9524I~
		dismiss();                                                 //~9524I~
    }                                                              //~9524I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void onClickCancel()                                    //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("FinalGameDlg.onClickCancel");             //~9321I~//~9523R~//+vamsR~
        sendToAllReply(false);                                          //~9321I~//~9523R~
        dismiss();                                              //~9321I~//~9523R~
    }                                                              //~9321I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void onClickOther(int Pbuttonid)                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("FinalGameDlg.onClickOther btnid="+Integer.toHexString(Pbuttonid));//~9321I~//~9504R~//~9520R~
        switch(Pbuttonid)                                          //~9321I~
        {                                                          //~9321I~
            case R.id.GameOver:                                    //~9321I~//~9522R~
                onClickGameOver();                                    //~9321I~
                break;                                             //~9321I~
            case R.id.GameContinue:                                //~9522I~
                onClickGameContinue();                                    //~9522I~
                break;                                             //~9522I~
            case R.id.FixGame:                                     //~9523I~
                onClickFixGame();                                  //~9523I~
                break;                                             //~9523I~
            case R.id.ShowRule:                                    //~9417I~
                onClickShowRule();                                 //~9417I~
                break;                                             //~9417I~
            case R.id.Send:    	//not query case                   //~9525I~
                onClickSend();                                     //~9525I~
                break;                                             //~9525I~
        }                                                          //~9321I~
    }                                                              //~9321I~
    //******************************************                   //~9321I~
    public void onClickGameOver()                                     //~9321I~//~9522R~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("FinalGameDlg.onClickGameOver");         //~9321I~//~9504R~//~9520R~//~9522R~
//      if (swServer)                                              //~9523I~//~9524R~
//      {                                                          //~9523I~//~9524R~
		    nextgameTypeDeclared=LASTGAME_GAMEOVER;                //~9523I~
            typeClose|=CLOSABLE_QUERY_DECLARED;                    //~9523I~
            update();                                              //~9523I~
//      }                                                          //~9523I~//~9524R~
//      else                                                       //~9523I~//~9524R~
//  	    dismiss();                                              //~9321I~//~9523R~//~9524R~
//      doGameOver();                                              //~9522I~//~9523R~
		AG.aLastGame.resetRespStat();                              //~9524I~
        LastGame.sendToAll(LASTGAME_DECLARED,typeClose,endgameType,LASTGAME_GAMEOVER);//~9523I~//~9524R~
    }                                                              //~9321I~
    //******************************************                   //~9522I~
    public void onClickGameContinue()                              //~9522I~
    {                                                              //~9522I~
        if (Dump.Y) Dump.println("FinalGameDlg.onClickGameContinue");//~9522I~
//      if (swServer)                                              //~9523I~//~9524R~
//      {                                                          //~9523I~//~9524R~
		    nextgameTypeDeclared=LASTGAME_CONTINUE;                //~9523I~
            typeClose|=CLOSABLE_QUERY_DECLARED;                    //~9523I~
            update();                                              //~9523I~
//      }                                                          //~9523I~//~9524R~
//      else                                                       //~9523I~//~9524R~
//      	dismiss();                                                 //~9522I~//~9523R~//~9524R~
//      doGameContinue();                                          //~9522I~//~9523R~
		AG.aLastGame.resetRespStat();                              //~9524I~
        LastGame.sendToAll(LASTGAME_DECLARED,typeClose,endgameType,LASTGAME_CONTINUE);//~9523I~//~9524R~
    }                                                              //~9522I~
    //******************************************                   //~9523I~
    //*on Dealer                                                   //~9524I~
    //******************************************                   //~9524I~
    public void onClickFixGame()                                   //~9523I~
    {                                                              //~9523I~
        if (Dump.Y) Dump.println("FinalGameDlg.onClickFixGame typeClose="+Integer.toHexString(typeClose));   //~9523I~//~9819R~
//      if (nextgameTypeDeclared==LASTGAME_GAMEOVER)               //~9525I~
        if (((typeClose & CLOSABLE_QUERY_DECLARED)!=0 && nextgameTypeDeclared==LASTGAME_GAMEOVER)           //~9524I~//~9525R~
        ||   (typeClose & CLOSABLE_GAMEOVER)!=0                    //~9525I~
        )                                                          //~9525I~
	    	doGameOver();                                      //~9524I~//~9525R~
        else                                                   //~9524I~//~9525R~
        if ((typeClose==CLOSABLE_CONTINUE_ALLMINUS)                //~9526I~
        ||  (typeClose==CLOSABLE_CONTINUE_DRAWNHW_NEXT)            //~9819I~
        ||  (typeClose==CLOSABLE_CONTINUE_DRAWN_DEALER_NOTPENDING) //~9526I~
        )                                                          //~9526I~
			doGameNextRound();                                     //~9526I~
        else                                                       //~9526I~
			doGameContinue();                                   //~9524I~//~9525R~
      	dismiss();                                                 //~9524I~
    }                                                              //~9523I~
    //******************************************                   //~9417I~
    public void onClickShowRule()                                  //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("FinalGameDlg.onClickShowRule");      //~9417I~//~9504R~//~9520R~
        showRule();                                                //~9417I~
    }                                                              //~9417I~
	//************************************************             //~9417I~
    private void showRule()                                        //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("FinalGameDlg.showRule");             //~9417I~//~9504R~//~9520R~
        RuleSetting.showRuleInGame();                              //~9417I~
    }                                                              //~9417I~
//    //**************************************************************************//~9321I~//~9605R~
//    public void sendEndGame(int[] Ptotal)                          //~9321I~//~9605R~
//    {                                                              //~9321I~//~9605R~
////      int[][] intssP=new int[][]{Ptotal,minusPay};               //~9415R~//~9605R~
//        int[][] intssP=new int[][]{Ptotal,minusPay,chargeToGainer};//~9415I~//~9605R~
//        if (!AG.aUserAction.isServer)                              //~9321I~//~9605R~
//        {                                                          //~9321I~//~9605R~
////          sendToServerEndGame(Ptotal); //ENDGAME_SCORE_CONFIRMED //~9321R~//~9415R~//~9605R~
//            sendToServerEndGame(intssP); //ENDGAME_SCORE_CONFIRMED //~9415I~//~9605R~
//            return;                                                //~9321I~//~9605R~
//        }                                                          //~9321I~//~9605R~
////      endGame(EGDR_MINUSSTOP,Ptotal);                            //~9321I~//~9415R~//~9605R~
//        endGame(EGDR_MINUSSTOP,intssP);                            //~9415R~//~9605R~
////      sendToAllClientEndGame(Ptotal);                            //~9321I~//~9415R~//~9605R~
//        sendToAllClientEndGame(intssP);                            //~9415I~//~9605R~
//    }                                                              //~9321I~//~9605R~
//    //**************************************************************************//~9318I~//~0305R~
//    //*from UserAction,GCM_ENDGAME_SCORE btio msg received                                           //~9318I~//~9320R~//~0305R~
//    //**************************************************************************//~9318I~//~0305R~
//    public static void onReceived(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~9318R~//~0305R~
//    {                                                              //~9318I~//~0305R~
//        int[] total;                                               //~9321I~//~0305R~
//        int[] minusPrize;                                          //~9415I~//~0305R~
//        int[] minusCharge;                                         //~9415I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:onReceived swServer="+PswServer+",PswReceived="+PswReceived+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~9318R~//~9504R~//~9520R~//~0305R~
//        int scoreMsgtype=PintParm[POS_SCOREMSGTYPE];               //~9320I~//~0305R~
//        int endgameType=PintParm[POS_ENDGAMETYPE];                 //~9318I~//~0305R~
//        int nextgameType=PintParm[POS_NEXTGAMETYPE];               //~9318I~//~0305R~
//        if (PswServer)  //from dealer(client)                      //~9318R~//~0305R~
//        {                                                          //~9318I~//~0305R~
//            switch(scoreMsgtype)                                   //~9321I~//~0305R~
//            {                                                      //~9321I~//~0305R~
//                case ENDGAME_SCORE_UPDATE:                             //~9321I~//~0305R~
//                int[] amt=new int[PLAYERS];                            //~9318I~//~9321R~//~0305R~
//                System.arraycopy(PintParm,POS_AMMOUNT,amt,0,PLAYERS);  //~9318I~//~9321R~//~0305R~
//                if (calcOnServer(PswReceived,Pplayer,endgameType,nextgameType,amt)) //no minus status//~9318I~//~9321R~//~0305R~
//                {                                                      //~9318I~//~9321R~//~0305R~
//                    sendToAllClientUpdate(endgameType,nextgameType,AG.aAccounts.score);//with old eswn status //~9318R~//~9319I~//~9321R~//~0305R~
//    //              AG.aNamePlate.showScore();                         //~9318I~//~9320R~//~9321R~//~0305R~
//                    nextGame(PswServer,endgameType,nextgameType);                //~9318M~//~9321R~//~9502R~//~0305R~
//                }                                                      //~9318I~//~9321R~//~0305R~
//                else                                                   //~9318I~//~9321R~//~0305R~
//                    sendToClientDealer(ENDGAME_SCORE_MINUS,endgameType,nextgameType,amt,AG.aAccounts.score);//no minus status//~9320R~//~9321R~//~0305R~
//                break;                                             //~9321I~//~0305R~
//            case ENDGAME_SCORE_CONFIRM_REQUEST:                    //~9321R~//~0305R~
//                int[][] intss=parseConfirmRequest(PintParm);               //~9321I~//~0305R~
////              sendToAllClientConfirm(intss[0],intss[1],intss[2],intss[3],intss[4]);//~9321I~//~9322R~//~9408R~//~0305R~
//                sendToAllClientConfirm(intss[0],intss[1],intss[2],intss[3],intss[4],intss[5]);//~9408I~//~0305R~
//                if (Utils.isShowingDialogFragment(AG.aFinalGameDlg))   //~9322I~//~9504R~//~9520R~//~0305R~
//                {                                                  //~9322I~//~0305R~
//                    AG.aFinalGameDlg.swDismissBeforNew=true;                        //~9322I~//~9504R~//~9520R~//~0305R~
//                    AG.aFinalGameDlg.dismiss();                        //~9322I~//~9504R~//~9520R~//~0305R~
//                }                                                  //~9322I~//~0305R~
////              FinalGameDlg.newInstance(intss[0],intss[1],intss[2],intss[3],intss[4],intss[5]).show();//~9321R~//~9322R~//~9408R~//~9413R~//~9504R~//~9520R~//~0305R~
//                FinalGameDlg.newInstance(endgameType,intss[0],intss[1],intss[2],intss[3],intss[4],intss[5]).show();//~9413I~//~9504R~//~9520R~//~0305R~
//                break;                                             //~9321I~//~0305R~
//            case ENDGAME_SCORE_CONFIRM_REPLY:                      //~9321R~//~0305R~
//                int replyEswn=PintParm[POS_REPLAY_ESWN];           //~9321I~//~9430I~//~0305R~
//                if (OKNGDlg.isDealer())                            //~9321I~//~0305R~
//                {                                                  //~9321I~//~0305R~
//                    if (Utils.isShowingDialogFragment(AG.aFinalGameDlg))//~9321I~//~9504R~//~9520R~//~0305R~
//                        AG.aFinalGameDlg.repaintOKNG(replyEswn,PintParm[POS_OKNG]!=0);  //callback updateOKNGAdditional//~9321I~//~9504R~//~9520R~//~0305R~
//                }                                                  //~9321I~//~0305R~
//                else                                               //~9321I~//~0305R~
//                {                                                  //~9321I~//~0305R~
////                  String msgdata=ENDGAME_SCORE_CONFIRM_REPLY+MSG_SEPAPP2+PintParm[POS_OKNG];//~9321I~//~9430R~//~0305R~
//                    String msgdata=ENDGAME_SCORE_CONFIRM_REPLY+MSG_SEPAPP2+endgameType+MSG_SEPAPP2+nextgameType+MSG_SEPAPP+PintParm[POS_OKNG];//~9430I~//~0305R~
////                  AG.aUserAction.sendToTheClientDealer(GCM_ENDGAME_SCORE,msgdata);//~9321I~//~9430R~//~0305R~
//                    AG.aUserAction.sendToTheClientDealerWithSourceEswn(GCM_ENDGAME_SCORE,replyEswn,msgdata);//~9430I~//~0305R~
//                }                                                  //~9321I~//~0305R~
//                break;                                             //~9321I~//~0305R~
//            case ENDGAME_SCORE_CONFIRMED:                          //~9321I~//~0305R~
//                total=new int[PLAYERS];                            //~9321R~//~0305R~
//                minusPrize=new int[PLAYERS];                     //~0305R~
//                minusCharge=new int[PLAYERS];//~9415I~           //~0305R~
//                System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED,total,0,PLAYERS);//~9321I~//~9408R~//~0305R~
//                System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED_MINUSPRIZE,minusPrize,0,PLAYERS);//~9415I~//~0305R~
//                System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED_CHARGE,minusCharge,0,PLAYERS);//~9415I~//~0305R~
////              endGame(EGDR_MINUSSTOP,total);                     //~9321I~//~9415R~//~0305R~
////              int[][] intssP=new int[][]{total,minusPrize};      //~9415R~//~0305R~
//                int[][] intssP=new int[][]{total,minusPrize,minusCharge};//~9415I~//~0305R~
////              endGame(EGDR_MINUSSTOP,total);                     //~9415R~//~0305R~
//                endGame(EGDR_MINUSSTOP,intssP);                    //~9415R~//~0305R~
////              sendToAllClientEndGame(total);                     //~9321R~//~9415R~//~0305R~
//                sendToAllClientEndGame(intssP);                    //~9415I~//~0305R~
//                break;                                             //~9321I~//~0305R~
//            }                                                      //~9321I~//~0305R~
//        }                                                          //~9318I~//~0305R~
//        else  //client                                                     //~9318I~//~9502R~//~0305R~
//        {                                                          //~9318I~//~0305R~
//            switch(scoreMsgtype)                                   //~9321I~//~0305R~
//            {                                                      //~9321I~//~0305R~
//            case ENDGAME_SCORE_UPDATE:                             //~9321I~//~0305R~
//                System.arraycopy(PintParm,POS_AMMOUNT,AG.aAccounts.score,0,PLAYERS);   //account idxde seq//~9318I~//~9320R~//~0305R~
////              AG.aNamePlate.showScore();                             //~9318M~//~9320R~//~0305R~
//                nextGame(PswServer,endgameType,nextgameType);                    //~9319I~//~9320R~//~9502R~//~0305R~
//                break;                                             //~9321I~//~0305R~
//            case ENDGAME_SCORE_MINUS:                              //~9321I~//~0305R~
//                int[] amt=new int[PLAYERS];                        //~9320I~//~9321M~//~0305R~
//                int[] minusScore=new int[PLAYERS];                 //~9321M~//~0305R~
//                System.arraycopy(PintParm,POS_AMMOUNT,amt,0,PLAYERS);   //account idxde seq//~9320I~//~9321M~//~0305R~
//                System.arraycopy(PintParm,POS_AMMOUNT+PLAYERS,minusScore,0,PLAYERS);   //account idxde seq//~9321M~//~0305R~
////              FinalGameDlg.newInstance(amt,minusScore).show();                  //~9320I~//~9321M~//~9413R~//~9504R~//~9520R~//~0305R~
//                FinalGameDlg.newInstance(endgameType,amt,minusScore).show();//~9413I~//~9504R~//~9520R~//~0305R~
//                break;                                             //~9321I~//~0305R~
//            case ENDGAME_SCORE_CONFIRM_REQUEST:                    //~9321I~//~0305R~
//                if (!OKNGDlg.isDealer())                           //~9321I~//~0305R~
//                {                                                  //~9321I~//~0305R~
//                    if (Utils.isShowingDialogFragment(AG.aFinalGameDlg))//~9322I~//~9504R~//~9520R~//~0305R~
//                    {                                              //~9322I~//~0305R~
//                        AG.aFinalGameDlg.swDismissBeforNew=true;                    //~9322I~//~9504R~//~9520R~//~0305R~
//                        AG.aFinalGameDlg.dismiss();                    //~9322I~//~9504R~//~9520R~//~0305R~
//                    }                                              //~9322I~//~0305R~
//                    int[][] intss=parseConfirmRequest(PintParm);   //~9321R~//~0305R~
////                  FinalGameDlg.newInstance(intss[0],intss[1],intss[2],intss[3],intss[4]).show();//~9321R~//~9322R~//~9408R~//~9504R~//~9520R~//~0305R~
////                  FinalGameDlg.newInstance(intss[0],intss[1],intss[2],intss[3],intss[4],intss[5]).show();//~9408I~//~9413R~//~9504R~//~9520R~//~0305R~
//                    FinalGameDlg.newInstance(endgameType,intss[0],intss[1],intss[2],intss[3],intss[4],intss[5]).show();//~9413I~//~9504R~//~9520R~//~0305R~
//                }                                                  //~9321I~//~0305R~
//                break;                                             //~9321I~//~0305R~
//            case ENDGAME_SCORE_CONFIRM_REPLY:                      //~9321I~//~0305R~
//                if (OKNGDlg.isDealer())                            //~9321I~//~0305R~
//                {                                                  //~9321I~//~0305R~
//                    int replyEswn=PintParm[POS_REPLAY_ESWN];       //~9321I~//~0305R~
//                    if (Utils.isShowingDialogFragment(AG.aFinalGameDlg))//~9321I~//~9504R~//~9520R~//~0305R~
//                        AG.aFinalGameDlg.repaintOKNG(replyEswn,PintParm[POS_OKNG]!=0);  //callback updateOKNGAdditional//~9321I~//~9504R~//~9520R~//~0305R~
//                }                                                  //~9321I~//~0305R~
//                break;                                             //~9321I~//~0305R~
//            case ENDGAME_SCORE_CONFIRMED:                          //~9321I~//~0305R~
////              if (!OKNGDlg.isDealer())                           //~9321R~//~9401R~//~0305R~
////              {                                                  //~9321I~//~9401R~//~0305R~
//                    total=new int[PLAYERS];                         //~9321I~//~0305R~
//                    minusPrize=new int[PLAYERS];                   //~9415I~//~0305R~
//                    minusCharge=new int[PLAYERS];                  //~9415I~//~0305R~
//                    int[][] intssP=new int[][]{total,minusPrize,minusCharge};//~9415R~//~0305R~
//                    System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED,total,0,PLAYERS);//~9321I~//~9408R~//~0305R~
//                    System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED_MINUSPRIZE,minusPrize,0,PLAYERS);//~9415I~//~0305R~
//                    System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED_CHARGE,minusCharge,0,PLAYERS);//~9415I~//~0305R~
////                  endGame(EGDR_MINUSSTOP,total);                 //~9321I~//~9415R~//~0305R~
//                    endGame(EGDR_MINUSSTOP,intssP);                //~9415I~//~0305R~
////              }                                                  //~9321I~//~9401R~//~0305R~
//                break;                                             //~9321I~//~0305R~
//            }                                                      //~9320I~//~0305R~
//        }                                                          //~9318I~//~0305R~
//    }                                                              //~9318I~//~0305R~
//    //**************************************************************************//~9321I~//~0305R~
//    private static int[][] parseConfirmRequest(int[] PintParm)     //~9321R~//~0305R~
//    {                                                              //~9321I~//~0305R~
//        int pos=POS_AMMOUNT_CONFIRM;                                //~9321I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:parseConfirmRequest startpos="+pos+",intp="+Arrays.toString(PintParm));//~9321I~//~9504R~//~9520R~//~0305R~
//        int[] amt=new int[PLAYERS];                                //~9321I~//~0305R~
//        int[] score=new int[PLAYERS];                              //~9321I~//~0305R~
//        int[] pay=new int[PLAYERS];                                //~9321I~//~0305R~
//        int[] total=new int[PLAYERS];                              //~9321I~//~0305R~
////      int[] payer=new int[PLAYERS*PLAYERS];                      //~9322I~//~9408R~//~0305R~
//        int[] charge=new int[PLAYERS];                             //~9408I~//~0305R~
//        int[] chargeResult=new int[PLAYERS];                       //~9408I~//~0305R~
//        System.arraycopy(PintParm,pos,amt,0,PLAYERS);   pos+=PLAYERS;//~9321I~//~0305R~
//        System.arraycopy(PintParm,pos,score,0,PLAYERS);   pos+=PLAYERS;//~9321I~//~0305R~
//        System.arraycopy(PintParm,pos,pay,0,PLAYERS);   pos+=PLAYERS;//~9321I~//~0305R~
//        System.arraycopy(PintParm,pos,total,0,PLAYERS); pos+=PLAYERS;           //~9321I~//~9322R~//~0305R~
////      System.arraycopy(PintParm,pos,payer,0,PLAYERS*PLAYERS);    //~9322R~//~9408R~//~0305R~
//        System.arraycopy(PintParm,pos,charge,0,PLAYERS);pos+=PLAYERS;//~9408I~//~0305R~
//        System.arraycopy(PintParm,pos,chargeResult,0,PLAYERS);pos+=PLAYERS;//~9408I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:parseConfirmRequest ammount="+Arrays.toString(amt));//~9321I~//~9504R~//~9520R~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:parseConfirmRequest minusScore="+Arrays.toString(score));//~9321I~//~9504R~//~9520R~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:parseConfirmRequest minusPay="+Arrays.toString(pay));//~9321I~//~9504R~//~9520R~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:parseConfirmRequest newTotal="+Arrays.toString(total));//~9321I~//~9504R~//~9520R~//~0305R~
////      if (Dump.Y) Dump.println("FinalGameDlg:parseConfirmRequest payer="+Arrays.toString(payer));//~9322I~//~9408R~//~9504R~//~9520R~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:parseConfirmRequest charge="+Arrays.toString(charge));//~9408I~//~9504R~//~9520R~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:parseConfirmRequest chargeResult="+Arrays.toString(chargeResult));//~9408I~//~9504R~//~9520R~//~0305R~
////      int[][] intss=new int[][]{amt,score,pay,total,payer};              //~9321I~//~9322R~//~9408R~//~0305R~
//        int[][] intss=new int[][]{amt,score,pay,total,charge,chargeResult};//~9408I~//~0305R~
//        return intss;                                              //~9321I~//~0305R~
//    }                                                              //~9321I~//~0305R~
//    //**************************************************************************//~9318I~//~0305R~
//    private static void nextGame(boolean PswServer,int PendgameType,int PnextgameType)//~9318I~//~9320R~//~9501R~//~9502R~//~0305R~
//    {                                                              //~9318I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:nextGame endgame="+PendgameType+",nextgame="+PnextgameType);//~9319I~//~9504R~//~9520R~//~0305R~
//        resetGrilledBird(PendgameType);                            //~9501I~//~9503R~//~0305R~
//        AG.aNamePlate.showScore();                                 //~9320I~//~9503R~//~0305R~
////        Complete.newInstance();                                    //~9320I~//~9502R~//~9503R~//~0305R~
////        Status.endGame(PendgameType,PnextgameType);                //~9318I~//~9502R~//~9503R~//~0305R~
////        AG.aStarter.showGameSeq(AG.aAccounts.starterRelativePos);  //~9318I~//~9502R~//~9503R~//~0305R~
////        UserAction.showInfoAll(0/*opt*/,Status.getStringGameSeq());//~9318I~//~9502R~//~9503R~//~0305R~
//          AG.aAccounts.nextGame(PswServer,PendgameType,PnextgameType);    //touch dice//~9502R~//~9503R~//~0305R~
//    }                                                              //~9318I~//~0305R~
//    //**************************************************************************//~9501I~//~9503R~//~0305R~
//    private static void resetGrilledBird(int PendgameType)         //~9501I~//~9503R~//~0305R~
//    {                                                              //~9501I~//~9503R~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:resetGrilledBird endgame="+PendgameType);//~9501I~//~9503R~//~9504R~//~9520R~//~0305R~
//        AG.aComplete.resetGrilledBird(PendgameType);               //~9501I~//~9503R~//~0305R~
//    }                                                              //~9501I~//~9503R~//~0305R~
//    //**************************************************************************//~9321I~//~0305R~
//  public static void endGame(int PendgameType,int[] Ptotal/*indexSeq*/)//~9321R~//~9415R~
//  public static void endGame(int PendgameType,int[][] PintssP/*indexSeq*/)//~9415I~//~9605R~
//    private static void endGame(int PendgameType,int[][] PintssP/*indexSeq*/)//~9605I~//~0305R~
//    {                                                              //~9321I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:endGame type="+PendgameType);//~9321I~//~9504R~//~9520R~//~0305R~
////      AG.aAccounts.setScore(Ptotal);                             //~9321R~//~9415R~//~0305R~
//        AG.aAccounts.setScore(PintssP[0]);                         //~9415I~//~0305R~
//        resetGrilledBird(PendgameType);                            //~9501I~//~9503R~//~0305R~
//        AG.aNamePlate.showScore();                                 //~9321I~//~0305R~
////      Complete.newInstance();                                    //~9321I~//~0305R~
//        Status.endGame(PendgameType,NGTP_GAMEOVER);                           //~9321I~//~9401R~//~0305R~
////      AG.aStarter.showGameSeq(AG.aAccounts.starterRelativePos);  //~9321I~//~0305R~
//        UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndMinusStop));//~9321I~//~0305R~
////      if (OKNGDlg.isDealer())                                    //~9322I~//~9605R~//~0305R~
//        if (AG.aAccounts.isFirstDealerReal())                      //~9605I~//~0305R~
//        {                                                          //~9415I~//~0305R~
////          AccountsDlg.newInstance(Ptotal).show();               //~9322I~//~9415R~//~0305R~
//            AccountsDlg.newInstance(PintssP).show();               //~9415R~//~0305R~
//        }                                                          //~9415I~//~0305R~
//    }                                                              //~9321I~//~0305R~
//    //*******************************************************      //~9321I~//~0218R~
//    //*on requester                                                //~9321I~//~0218R~
//    //*******************************************************      //~9321I~//~0217R~
//    public void sendConfirmRequest()                               //~9321R~//~0217R~
//    {                                                              //~9321I~//~0217R~
//        if (Dump.Y) Dump.println("FinalGameDlg:sendConfirmRequest");   //~9321I~//~9504R~//~9520R~//~0217R~
//        if (isServer)                                              //~9321I~//~0217R~
////          sendToAllClientConfirm(ammount,minusScore,minusPay,newTotal,payerInfo);//~9321R~//~9322R~//~9408R~//~0217R~
//            sendToAllClientConfirm(ammount,minusScore,minusPay,newTotal,chargeToGainer,chargeToGainerResult);//~9408I~//~0217R~
//        else                                                       //~9321I~//~0217R~
//            sendToServerConfirm();                                  //~9321I~//~0217R~
//    }                                                              //~9321I~//~0217R~
//    //**************************************************************************//~9318I~//~0217R~
//    private static void sendToServer(int PendgameType,int PnextgameType,int[] Pamt)//~9318I~//~9321R~//~0217R~
//    {                                                              //~9318I~//~0217R~
//        String strAmt=Pamt[0]+MSG_SEPAPP+Pamt[1]+MSG_SEPAPP+Pamt[2]+MSG_SEPAPP+Pamt[3];//~9318I~//~0217R~
//        AG.aUserAction.sendToServer(GCM_ENDGAME_SCORE,PLAYER_YOU,ENDGAME_SCORE_UPDATE,PendgameType,PnextgameType,strAmt);//~9318I~//~0217R~
//    }                                                              //~9318I~//~0217R~
//    //**************************************************************************//~9321I~//~0305R~
//    private void sendToServerConfirm()                             //~9321R~//~0305R~
//    {                                                              //~9321I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg.sendToServerConfirm");  //~9321I~//~9504R~//~9520R~//~0305R~
////      String msgdata=makeReqMsg(ammount,minusScore,minusPay,newTotal);//~9321R~//~9322R~//~0305R~
////      String msgdata=makeReqMsg(ammount,minusScore,minusPay,newTotal,payerInfo);//~9322I~//~9408R~//~0305R~
//        String msgdata=makeReqMsg(ammount,minusScore,minusPay,newTotal,chargeToGainer,chargeToGainerResult);//~9408I~//~0305R~
//        AG.aUserAction.sendToServer(GCM_ENDGAME_SCORE,PLAYER_YOU,ENDGAME_SCORE_CONFIRM_REQUEST,msgdata);//~9321R~//~0305R~
//    }                                                              //~9321I~//~0305R~
//    //**************************************************************************//~9321I~//~0217R~
////  private void sendToServerEndGame(int[] Ptotal)                 //~9321R~//~9415R~//~0217R~
//    private void sendToServerEndGame(int[][] PintssP)              //~9415I~//~0217R~
//    {                                                              //~9321I~//~0217R~
//        if (Dump.Y) Dump.println("FinalGameDlg.sendToServerEndGame");  //~9321I~//~9504R~//~9520R~//~0217R~
////      String msg=Ptotal[0]+MSG_SEPAPP+Ptotal[1]+MSG_SEPAPP+Ptotal[2]+MSG_SEPAPP+Ptotal[3];//~9321I~//~9415R~//~0217R~
//        String msg=AccountsDlg.makeReqMsg(PintssP);                //~9415I~//~0217R~
//        AG.aUserAction.sendToServer(GCM_ENDGAME_SCORE,PLAYER_YOU,ENDGAME_SCORE_CONFIRMED,msg);//~9321I~//~0217R~
//    }                                                              //~9321I~//~0217R~
//    //**************************************************************************//~9318I~//~0305R~
//    private static void sendToAllClientUpdate(int PendgameType,int PnextgameType,int[] Pamt)//~9318R~//~9321R~//~0305R~
//    {                                                              //~9318I~//~0305R~
////      int eswn=AG.aAccounts.playerToEswn(PLAYER_YOU);            //~9318I~//~9321R~//~0305R~
//        String strAmt=Pamt[0]+MSG_SEPAPP+Pamt[1]+MSG_SEPAPP+Pamt[2]+MSG_SEPAPP+Pamt[3];//~9318I~//~0305R~
////        String msgdata=eswn+MSG_SEPAPP2+ENDGAME_SCORE_UPDATE+MSG_SEPAPP2+PendgameType+MSG_SEPAPP2+PnextgameType+MSG_SEPAPP2+strAmt;//~9318R~//~9321R~//~0305R~
////        if (Dump.Y) Dump.println("FinalGameDlg:sendToAllClient msgdata="+msgdata);//~9318I~//~9321R~//~9504R~//~9520R~//~0305R~
////        AG.aUserAction.sendToClient(true/*swAll*/,false/*swRobot*/,GCM_ENDGAME_SCORE,PLAYER_YOU,msgdata);//~9318I~//~9321R~//~0305R~
//        String msgdata=PendgameType+MSG_SEPAPP2+PnextgameType+MSG_SEPAPP2+strAmt;//~9321I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:sendToAllClientUpdate msgdata="+msgdata);//~9321I~//~9504R~//~9520R~//~0305R~
//        sendToAllClient(ENDGAME_SCORE_UPDATE,msgdata);             //~9321I~//~0305R~
//    }                                                              //~9318I~//~0305R~
//    //**************************************************************************//~9321I~//~0305R~
////  private static void sendToAllClientEndGame(int[] Ptotal)       //~9321I~//~9415R~//~0305R~
//    private static void sendToAllClientEndGame(int[][] PintssP)    //~9415I~//~0305R~
//    {                                                              //~9321I~//~0305R~
////      String msg=Ptotal[0]+MSG_SEPAPP+Ptotal[1]+MSG_SEPAPP+Ptotal[2]+MSG_SEPAPP+Ptotal[3];//~9321I~//~9415R~//~0305R~
//        String msg=AccountsDlg.makeReqMsg(PintssP);                //~9415I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:sendToAllClientEndgame msgdata="+msg);//~9321I~//~9504R~//~9520R~//~0217R~//~0305R~
//        sendToAllClient(ENDGAME_SCORE_CONFIRMED,msg);             //~9321I~//~0305R~
//    }                                                              //~9321I~//~0305R~
    //*******************************************************      //~9321M~
//    private static String makeReqMsg(int[] Pamt,int[] Pscore,int[] Ppay,int[] Ptotal,int[] PpayerInfo)//~9321I~//~9322R~//~9408R~
//    private static String makeReqMsg(int[] Pamt,int[] Pscore,int[] Ppay,int[] Ptotal,int[] Pcharge,int[] PchargeResult)//~9408R~//~0305R~
//    {                                                              //~9321M~//~0305R~
//        StringBuffer sb=new StringBuffer();                        //~9321M~//~0305R~
//        sb.append("0 0");   //endgame,nextgame type                //~9321I~//~0305R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~9321M~//~0305R~
//            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Pamt[ii]);     //eswn seq//~9321I~//~0305R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~9321I~//~0305R~
//            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Pscore[ii]);     //eswn seq//~9321I~//~0305R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~9321I~//~0305R~
//            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Ppay[ii]);     //eswn seq//~9321I~//~0305R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~9321I~//~0305R~
//            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Ptotal[ii]);     //eswn seq//~9321I~//~0305R~
////        for (int ii=0;ii<PLAYERS*PLAYERS;ii++)                     //~9322I~//~9408R~//~0305R~
////            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+PpayerInfo[ii]);     //eswn seq//~9322I~//~9408R~//~0305R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~9408I~//~0305R~
//            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Pcharge[ii]);     //eswn seq//~9408I~//~0305R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~9408I~//~0305R~
//            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+PchargeResult[ii]);     //eswn seq//~9408I~//~0305R~
//        String s=sb.toString();                                    //~9321M~//~0305R~
//        if (Dump.Y) Dump.println("CompleteDlg.makeReqMsg msg="+s); //~9321M~//~0305R~
//        return s;                                                  //~9321M~//~0305R~
//    }                                                              //~9321M~//~0305R~
//    //**************************************************************************//~9321I~//~0305R~
////  private static void sendToAllClientConfirm(int[] Pamt,int[] Pscore,int[] Ppay,int[] Ptotal,int[] PpayerInfo)//~9321I~//~9322R~//~9408R~//~0305R~
//    private static void sendToAllClientConfirm(int[] Pamt,int[] Pscore,int[] Ppay,int[] Ptotal,int[] Pcharge,int[] PchargeResult)//~9408R~//~0305R~
//    {                                                              //~9321I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:sendToAllClientConfirm");//~9321I~//~9504R~//~9520R~//~0305R~
////      String msg=makeReqMsg(Pamt,Pscore,Ppay,Ptotal,PpayerInfo);            //~9321I~//~9322R~//~9408R~//~0305R~
//        String msg=makeReqMsg(Pamt,Pscore,Ppay,Ptotal,Pcharge,PchargeResult);//~9408R~//~0305R~
//        sendToAllClient(ENDGAME_SCORE_CONFIRM_REQUEST,msg); //~9321I~//~0305R~
//    }                                                              //~9321I~//~0305R~
//    //**************************************************************************//~9321I~//~0305R~
//    private static void sendToAllClient(int PendgameMsgid,String Pmsgdata)//~9321I~//~0305R~
//    {                                                              //~9321I~//~0305R~
//        int eswn=Accounts.playerToEswn(PLAYER_YOU);            //~9321I~//~0305R~
//        String msgdata=eswn+MSG_SEPAPP2+PendgameMsgid+MSG_SEPAPP2+Pmsgdata;//~9321I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:sendToAllClient msgdata="+msgdata);//~9321I~//~9504R~//~9520R~//~0305R~
//        AG.aUserAction.sendToClient(true/*swAll*/,false/*swRobot*/,GCM_ENDGAME_SCORE,PLAYER_YOU,msgdata);//~9321I~//~0305R~
//    }                                                              //~9321I~//~0305R~
//    //**************************************************************************//~9318I~//~0305R~
//    private static void sendToClientDealer(int PscoreMsgtype,int PendgameType,int PnextgameType,int[] Pamt,int[] Pscore)//~9318I~//~9320R~//~9321R~//~0305R~
//    {                                                              //~9318I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:sendToClientDealer endgame="+PendgameType+",nextgame="+PnextgameType+",amt="+Arrays.toString(Pamt)+",score="+Arrays.toString(Pscore));//~9320I~//~9321R~//~9504R~//~9520R~//~0305R~
//        String strAmt=Pamt[0]+MSG_SEPAPP+Pamt[1]+MSG_SEPAPP+Pamt[2]+MSG_SEPAPP+Pamt[3]+MSG_SEPAPP2//~9321R~//~0305R~
//                     +Pscore[0]+MSG_SEPAPP+Pscore[1]+MSG_SEPAPP+Pscore[2]+MSG_SEPAPP+Pscore[3];//~9321I~//~0305R~
//        String msgdata=PscoreMsgtype+MSG_SEPAPP2+PendgameType+MSG_SEPAPP2+PnextgameType+MSG_SEPAPP2+strAmt;//~9318I~//~9320R~//~0305R~
//        AG.aUserAction.sendToTheClientDealer(GCM_ENDGAME_SCORE,msgdata);       //~9318I~//~0305R~
//    }                                                              //~9318I~//~0305R~
//    //**************************************************************************//~9318I~//~0305R~
//    //*btio msg received or calc btn on server                     //~9318R~//~0305R~
//    //*rc:true:no minus stop                                       //~9321I~//~0305R~
//    //**************************************************************************//~9318I~//~0305R~
//    private static boolean calcOnServer(boolean PswReceived,int Pplayer,int PendgameType,int PnextgameType,int[] Pamt)//~9318I~//~9420R~//~0305R~
//    {                                                              //~9318I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg:calcOnServer PswReceived="+PswReceived+",player="+Pplayer+",endgametype="+PendgameType+",nextGameTypoe="+PnextgameType+",amt="+Arrays.toString(Pamt));//~9318I~//~9504R~//~9520R~//~0305R~
////        AG.aAccounts.updateScore(Pamt/*eswnSeq*/);                 //~9318R~//~9520R~//~0305R~
////        boolean[] swsMinusStop=getMinusStopAccount(AG.aAccounts.score);//~9318I~//~9520R~//~0305R~
////        boolean rc=swsMinusStop==null;                             //~9318I~//~9520R~//~0305R~
//        boolean rc=false; //TODO                                   //~9520I~//~0305R~
//        if (Dump.Y) Dump.println("FinalGameDlg.calcOnServer rc="+rc);  //~9318I~//~9504R~//~9520R~//~0305R~
//        return rc;                                                 //~9520R~//~0305R~
//    }                                                              //~9318I~//~0305R~
    //**************************************************************************//~9322I~
    //*from MenuInGameDlg                                          //~9521R~
    //**************************************************************************//~9322I~
    public static boolean showDismissed()                             //~9322I~//~9904R~
    {                                                              //~9322I~
        if (Dump.Y) Dump.println("FinalGameDlg:showDismiss typeClosable="+AG.aLastGame.typeClosable+",swFixed="+AG.aLastGame.swFixed);        //~9322I~//~9504R~//~9520R~//~0111R~
		if (Utils.isShowingDialogFragment(AG.aFinalGameDlg))           //~9322I~//~9504R~//~9520R~
        {                                                          //~9322I~
	        if (Dump.Y) Dump.println("FinalGameDlg:showDismisswd already shown");//~9322I~//~9504R~//~9520R~
        	return false;                                                //~9322I~//~9904R~
        }                                                          //~9322I~
//      if (AG.aLastGame==null || AG.aLastGame.typeClosable==CLOSABLE_NONE)	//not shown one//~9521I~//~0111R~
        if (AG.aLastGame==null || AG.aLastGame.typeClosable==CLOSABLE_NONE || AG.aLastGame.swFixed)//~0111I~
        {                                                          //~9322I~
        	UView.showToast(R.string.Err_FinalGameNoData);       //~9322I~//~9521R~
        	return false;                                                //~9322I~//~9904R~
        }                                                          //~9322I~
  	    FinalGameDlg.newInstance(AG.aLastGame.typeClosable,AG.aLastGame.endgameType).show();//~9521R~//~9522R~
        return true;                                               //~9904I~
    }                                                              //~9322I~
    //**************************************************************************//~9522I~
    //on dealer,LastGame receives                                  //~9524R~
    //**************************************************************************//~9524I~
    private void doGameOver()                                      //~9522I~
    {                                                              //~9522I~
        if (Dump.Y) Dump.println("FinalGameDlg:doGameOver");       //~9522I~
        sendMsg(LASTGAME_FIXED,LASTGAME_GAMEOVER);                 //~9524R~
    }                                                              //~9522I~
    //**************************************************************************//~9522I~
    //*on dealer,lastGame receives                                 //~9524R~
    //**************************************************************************//~9524I~
    private void doGameContinue()                                  //~9522I~
    {                                                              //~9522I~
        if (Dump.Y) Dump.println("FinalGameDlg:doGameContinue");   //~9522I~
        sendMsg(LASTGAME_FIXED,LASTGAME_CONTINUE);                 //~9524R~
    }                                                              //~9522I~
    //**************************************************************************//~9526I~
    //*on dealer,lastGame receives                                 //~9526I~
    //**************************************************************************//~9526I~
    private void doGameNextRound()                                 //~9526I~
    {                                                              //~9526I~
        if (Dump.Y) Dump.println("FinalGameDlg:doGameNextRound");   //~9526I~//~9819R~
        sendMsg(LASTGAME_FIXED,LASTGAME_NEXTROUND);                //~9526I~
    }                                                              //~9526I~
    //**************************************************************************//~9522I~
    //*when dealer on server                                       //~9524I~
    //**************************************************************************//~9524I~
    private void sendMsg(int Pmsgid,int PnextgameType)              //~9522R~//~9524R~
    {                                                              //~9522I~
        if (Dump.Y) Dump.println("FinalGameDlg:sendMsg msgid="+Pmsgid+",nextgameType="+PnextgameType+",typeClose="+Integer.toHexString(typeClose));//~9522R~//~9524R~
//      GameViewHandler.sendMsg(GCM_LASTGAME,PLAYER_YOU,Pmsgid,PnextgameType);//~9522I~//~9524I~
        String msgData=Accounts.getCurrentEswn()+MSG_SEPAPP2+Pmsgid+MSG_SEPAPP2+typeClose+MSG_SEPAPP2+endgameType+MSG_SEPAPP2+PnextgameType;//~9524I~
		AG.aACAction.receivedAppMsgEmulated(GCM_LASTGAME,msgData,""/*data2*/,""/*data3*/);//~9524I~
    }                                                              //~9522I~
//    //**************************************************************************//~9523I~//~9524R~
//    //*send to AllClient if server,else to Server                  //~9523I~//~9524R~
//    //**************************************************************************//~9523I~//~9524R~
//    public static void sendToAll(int Pmsgid,int PtypeClose,int PendgameType,int PnextgameType)//~9523I~//~9524R~
//    {                                                              //~9523I~//~9524R~
//        if (Dump.Y) Dump.println("FinalGameDlg:sendToAll msgid="+Pmsgid+",endgameType="+PendgameType+",nextgameType="+PnextgameType+",typeClose="+Integer.toHexString(PtypeClose));//~9523I~//~9524R~
//        LastGame.sendToAll(Pmsgid,PtypeClose,PendgameType,PnextgameType,0/*okng*/);//~9523I~//~9524R~
//    }                                                              //~9523I~//~9524R~
    //*******************************************************************//~9321I~//~9523M~
    public void sendToAllReply(boolean PswOK)                        //~9321I~//~9523R~
    {                                                              //~9321I~//~9523M~
        if (Dump.Y) Dump.println("FinalGameDlg.sendToAllReply swOK="+PswOK);  //~9321R~//~9504R~//~9520R~//~9523I~
        int okng=(PswOK ? 1 : 0);                            //~9321I~//~9523M~
	    LastGame.sendToAll(LASTGAME_REPLY,typeClose,endgameType,nextgameTypeDeclared,okng);//~9523I~//~9524R~
    }                                                              //~9321I~//~9523M~
//    //**************************************************************************//~9523I~//~9524R~
//    public static void sendToAll(int Pmsgid,int PtypeClose,int PendgameType,int PnextgameType,int Pokng)//~9523I~//~9524R~
//    {                                                              //~9523I~//~9524R~
//        if (Dump.Y) Dump.println("FinalGameDlg:sendToAll msgid="+Pmsgid+",endgameType="+PendgameType+",nextgameType="+PnextgameType+",typeClose="+Integer.toHexString(PtypeClose)+",okng="+Pokng);//~9523I~//~9524R~
//        String msgData=Accounts.getCurrentEswn()+MSG_SEPAPP2+Pmsgid+MSG_SEPAPP2+PtypeClose+MSG_SEPAPP2+PendgameType+MSG_SEPAPP2+PnextgameType+MSG_SEPAPP2+Pokng;//~9523I~//~9524R~
//        if (Dump.Y) Dump.println("FinalGameDlg.sendToAll msgData="+msgData);//~9523I~//~9524R~
//        AG.aAccounts.sendToAll(GCM_LASTGAME,msgData);              //~9523I~//~9524R~
//    }                                                              //~9523I~//~9524R~
//    //**************************************************************************//~9524R~
//    public static void sendToClientExceptSender(int PsenderEswn,int Pmsgid,int PtypeClose,int PendgameType,int PnextgameType)//~9524R~
//    {                                                            //~9524R~
//        if (Dump.Y) Dump.println("FinalGameDlg:sendToAllExceptSender senderEswn="+PsenderEswn+",msgid="+Pmsgid+",endgameType="+PendgameType+",nextgameType="+PnextgameType+",typeClose="+Integer.toHexString(PtypeClose));//~9524R~
//        String msgData=Accounts.getCurrentEswn()+MSG_SEPAPP2+Pmsgid+MSG_SEPAPP2+PtypeClose+MSG_SEPAPP2+PendgameType+MSG_SEPAPP2+PnextgameType;//~9524R~
//        if (Dump.Y) Dump.println("FinalGameDlg.sendToAllExceptSender msgData="+msgData);//~9524R~
//        AG.aAccounts.sendToClientSkipByEswn(false/*swRobot*/,PsenderEswn,GCM_LASTGAME,msgData);//~9524R~
//    }                                                            //~9524R~
    //**************************************************************************//~9524I~
    private void sendToServer(int Pmsgid,int PnextgameType) //~9524I~
    {                                                              //~9524I~
        String msgData=Accounts.getCurrentEswn()+MSG_SEPAPP2+Pmsgid+MSG_SEPAPP2+typeClose+MSG_SEPAPP2+endgameType+MSG_SEPAPP2+PnextgameType;//~9524I~
        AG.aAccounts.sendToServer(GCM_LASTGAME,msgData);           //~9524I~
    }                                                              //~9524I~
    //*******************************************************      //~9406I~//~9523I~
    public static void repaintQuery(int PtypeClose,int PendgameType,int PnextgameType)                                   //~9406R~//~9523R~
    {                                                              //~9406I~//~9523I~
        if (Dump.Y) Dump.println("FinalGameDlg:repaint static endgameType="+PendgameType+",nextgameType="+PnextgameType+",typeClose="+Integer.toHexString(PtypeClose));//~9523I~
        int typeClose=PtypeClose;                                  //~9523I~
        typeClose|=CLOSABLE_QUERY_DECLARED;                  //~9523I~
        if (Utils.isShowingDialogFragment(AG.aFinalGameDlg))       //~9406I~//~9523I~
        {                                                          //~9523I~
        	AG.aFinalGameDlg.repaint(typeClose,PendgameType,PnextgameType);//~9523R~
        }                                                          //~9523I~
        else                                                       //~9523I~
        {                                                          //~9523I~
	        FinalGameDlg.newInstance(typeClose,PendgameType,PnextgameType).show();//~9523R~
        }                                                          //~9523I~
    }                                                              //~9406I~//~9523I~
    //*******************************************************      //~9523I~
    public static void repaintOKNG(int PtypeClose,int PendgameType,int PnextgameType,int PsenderEswn,int[] Pokng)//~9523R~//~9524R~
    {                                                              //~9523I~
        if (Dump.Y) Dump.println("FinalGameDlg:repaintOKNG static endgameType="+PendgameType+",nextgameType="+PnextgameType+",typeClose="+Integer.toHexString(PtypeClose)+",senderEswn="+PsenderEswn+",okng="+Arrays.toString(Pokng));//~9523I~//~9524R~
        if (Utils.isShowingDialogFragment(AG.aFinalGameDlg))       //~9523I~
        {                                                          //~9523I~
        	AG.aFinalGameDlg.repaintOKNG(Pokng);   //OKNGDlg callbacks updateOKNGAdditional//~9525R~
        }                                                          //~9523I~
        else                                                       //~9523I~
        {                                                          //~9523I~
	        FinalGameDlg.newInstance(PtypeClose,PendgameType,PnextgameType,Pokng).show();//callback updateOKNGAdditional//~9525R~
        }                                                          //~9523I~
    }                                                              //~9523I~
    //*******************************************************      //~9523I~
    public void repaint(int PtypeClose,int PendgameType,int PnextgameType)//~9523R~
    {                                                              //~9523I~
        if (Dump.Y) Dump.println("FinalGameDlg.repaint typeClose="+Integer.toHexString(PtypeClose)+",endgameType="+PendgameType);//~9523R~
        typeClose=PtypeClose;                                      //~9523I~
        endgameType=PendgameType;                                  //~9523I~
        nextgameTypeDeclared=PnextgameType;                        //~9523I~
        AG.activity.runOnUiThread(                                 //~9523I~
            new Runnable()                                         //~9523I~
            {                                                      //~9523I~
                @Override                                          //~9523I~
                public void run()                                  //~9523I~
                {                                                  //~9523I~
                    try                                            //~9523I~
                    {                                              //~9523I~
    				    if (Dump.Y) Dump.println("FinalGameDlg.repaint runonUiThread.run");//~9523I~
                    	update();                                  //~9523I~
                    }                                              //~9523I~
                    catch(Exception e)                             //~9523I~
                    {                                              //~9523I~
                        Dump.println(e,"FinalGameDlg:repaint.run");//~9523I~
                    }                                              //~9523I~
                }                                                  //~9523I~
            }                                                      //~9523I~
                                  );                               //~9523I~
                                                                   //~9523I~
    }                                                              //~9523I~
}//class                                                           //~v@@@R~
