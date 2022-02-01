//*CID://+vajkR~:                             update#= 1126;       //~vajkR~
//*****************************************************************//~v101I~
//2022/01/31 vajk (Bug)invalid nameplate color on client(pos on msg:GCM_ENDGAME_ACCOUNT is invalid, then score received is invalid)//~vajkI~
//2021/08/18 vac9 AccountsDlg, avoid colomn shift by option, keep column same as title//~vac9I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
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
import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.Complete;
import com.btmtest.game.GConst;
import com.btmtest.game.History;
import com.btmtest.game.ScoreSort;
import com.btmtest.game.Status;
import com.btmtest.game.UserAction;
import com.btmtest.game.gv.GMsg;
import com.btmtest.gui.UButton;
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
import static com.btmtest.utils.Alert.*;
import static com.btmtest.utils.Utils.*;

public class AccountsDlg  extends OKNGDlg //UFDlg                                            //~9312R~//~9321R~//~9322R~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.accounts;              //~9312R~//~9322R~
    private static final int LAYOUTID_SMALLFONT=R.layout.accounts_theme;//~vac5I~
    private static final int TITLEID=R.string.Title_AccountsDlg;//~9307I~//~9312R~//~9322R~
    private static final String HELPFILE="AccountsDlg";                //~9220I~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~
                                                                   //~9318I~
    private static final int POS_SCOREMSGTYPE=1;                    //~9318R~//~9320R~
    private static final int POS_AMMOUNT=2;                        //~9318R~//~9322R~//~9525R~
    private static final int POS_AMMOUNT_MINUSPAY=POS_AMMOUNT+PLAYERS;//~9415I~
    private static final int POS_AMMOUNT_MINUSCHARGE=POS_AMMOUNT+PLAYERS*2;//~9415I~
    private static final int POS_AMMOUNT_FINALSCORE=POS_AMMOUNT+PLAYERS*3;//~9415I~
    private static final int POS_ENDGAMETYPE=POS_AMMOUNT_FINALSCORE+PLAYERS;//~9525I~
    private static final int POS_DATE=POS_ENDGAMETYPE+1;           //~0106I~
    private static final int POS_TIME=POS_ENDGAMETYPE+2;           //~0106I~
    private static final int POS_REPLAY_ESWN=0;                    //~9321R~
    private static final int POS_OKNG=2;                           //~9321R~//~9322R~
//  private static final int POS_DATE=2;                           //~9826I~//~0106R~
//  private static final int POS_TIME=3;                           //~9826I~//~0106R~
//  private static final int POS_AMMOUNT_CONFIRMED=4;              //~9826I~//~vajkR~
    private static final int POS_AMMOUNT_CONFIRMED=2;              //~vajkI~
    private static final int POS_AMMOUNT_MINUSPAY_CONFIRMED=POS_AMMOUNT_CONFIRMED+PLAYERS;//~9826I~
    private static final int POS_AMMOUNT_MINUSCHARGE_CONFIRMED=POS_AMMOUNT_CONFIRMED+PLAYERS*2;//~9826I~
    private static final int POS_AMMOUNT_FINALSCORE_CONFIRMED=POS_AMMOUNT_CONFIRMED+PLAYERS*3;//~9826I~
                                                                   //~9826I~
                                                                   //~9318I~
    private static final int ENDGAME_ACCOUNTS_CONFIRM_REQUEST=1;   //~9322R~
    private static final int ENDGAME_ACCOUNTS_CONFIRM_REPLY=2;    //~9322I~
    private static final int ENDGAME_ACCOUNTS_CONFIRMED=3;         //~9322I~
//  private static final int[] rbIDSamePoint=new int[]{R.id.rbSamePointEswn,R.id.rbSamePointSpritCut,R.id.rbSamePointSpritUp};//~9401I~//~9407R~
                                                                   //~9322I~
//  private TextView[] tvsScore,tvsTotal,tvsBotCharge;                       //~9312I~//~9316R~//~9321R~//~9322R~//~9429R~//~9818R~
    protected TextView[] tvsScore,tvsTotal,tvsBotCharge;           //~9818I~
//  private TextView[] tvsName,tvsEswn;    //~9309R~               //~9312R~//~9818R~
    protected TextView[] tvsName,tvsEswn;                          //~9818I~
//  private int[] lastScore,topPrize=new int[PLAYERS],orderPrize=new int[PLAYERS];//~9322R~//~9819R~
    protected int[] lastScore;                                     //~9819I~
//  private int[] topPrize=new int[PLAYERS],orderPrize=new int[PLAYERS];//~9819I~//~9821R~
    protected int[] topPrize=new int[PLAYERS],orderPrize=new int[PLAYERS];//~9821I~
//  private int[] newTotal=new int[PLAYERS];                       //~9312I~//~9819R~
    protected int[] newTotal=new int[PLAYERS];                     //~9819I~
//  private int[] minusPrize,minusCharge;                          //~9415R~//~9819R~
    protected int[] minusPrize,minusCharge;                        //~9819I~
    private String[] accountNames;                                 //~9312I~
                                                                   //~9320I~
//  private TextView[] tvsOrder,tvsTopPrize,tvsOrderPrize,tvsLabelBotCharge;         //~9322R~//~9818R~
//    protected TextView[] tvsOrder,tvsTopPrize,tvsOrderPrize,tvsLabelBotCharge;//~9818I~//~9819R~
    protected TextView[] tvsOrder,tvsTopPrize,tvsOrderPrize;       //~9819I~
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
    protected Accounts ACC;                                          //~9322I~//~9821R~
    protected Complete CMP;                                          //~9322I~//~0314R~
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
//  protected  LinearLayout[] llsResult;                           //~9820I~//~9821R~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public AccountsDlg()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("AccountsDlg.defaultConstructor"); //~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~
        AG.aAccountsDlg=this;                                         //~9321I~//~9322R~
        ACC=AG.aAccounts;                                          //~9322I~//~0314M~
        CMP=AG.aComplete;                                          //~9322I~//~0314M~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
//  public static AccountsDlg newInstance(int[] Pscore)             //~9312R~//~9321R~//~9322R~//~9415R~
    public static AccountsDlg newInstance(int[][] PintssP)         //~9415I~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("AccountsDlg.newInstance score="+Arrays.toString(PintssP[0])+",minusPrize="+Arrays.toString(PintssP[1])+",minusCharge="+Arrays.toString(PintssP[2]));//~9312R~//~9322R~//~9415R~
    	AccountsDlg dlg=new AccountsDlg();                                     //~v@@@I~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~
//  	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9227R~//~vac5R~
    	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT :LAYOUTID),//~vac5I~
    			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_CLOSEBTN|FLAG_HELPBTN|FLAG_RULEBTN,//~v@@@I~//~9220R~//~9305R~//~9312R~//~9316R~//~9321R~//~9708R~
				TITLEID,HELPFILE);         //~v@@@I~               //~9220R~
//      dlg.lastScore=Pscore;	//point at last game(not total score)//~9321I~//~9322R~//~9415R~
//      dlg.minusPrize=PminusPrize;	//point at last game(not total score)//~9415I~
        dlg.lastScore=PintssP[0]; //point at last game(not total score)//~9415I~
        dlg.minusPrize=PintssP[1];	//point at last game(not total score)//~9415I~
        dlg.minusCharge=PintssP[2];	//point at last game(not total score)//~9415I~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
    //******************************************                   //~9525I~
    public static AccountsDlg newInstance(int[][] PintssP,int PendgameType,int PtypeClose)//~9525I~
    {                                                              //~9525I~
        if (Dump.Y) Dump.println("AccountsDlg.newInstance endgameType="+PendgameType+",typeClose="+Integer.toHexString(PtypeClose));//~9525I~
    	AccountsDlg dlg=newInstance(PintssP);                      //~9525I~
        dlg.endgameType=PendgameType;                              //~9525I~
        dlg.typeClose=PtypeClose;                                  //~9525I~
        return dlg;                                                //~9525I~
    }                                                              //~9525I~
    //******************************************                 //~9303R~//~9312R~
    @Override                                                    //~9303R~//~9305R~
    protected void initLayoutAdditional(View PView)                            //~v@@@I~//~9303R~//~9305R~//~9321R~
    {                                                              //~v@@@M~//~9303R~//~9305R~
        if (Dump.Y) Dump.println("AccountsDlg.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9303R~//~9304R~//~9305R~//~9307R~//~9312R~//~9322R~
        if (Dump.Y) Dump.println("AccountsDlg:initLayout score="+Arrays.toString(ACC.score));//no minus status//~9320I~//~9322R~
        swRequester=AG.aAccounts.isFirstDealerReal();              //~9605M~
        swByPosition=true;  //OKNGDialog                           //~9606I~
        swInitLayout=true;                                         //~9322I~
        getRuleSetting();                                          //~9320I~
	    showRuleSetting(PView);                                    //~9401I~
        setupAmmount(PView);                                       //~9312I~
        setAccountName();                                          //~9309I~
	    showAmmount();                                             //~9309I~
        swInitLayout=false;                                        //~9322I~
	    saveLast();                                                //~9322M~
		setTitle();                                                //~9822I~
        hideResponseEswn();                                        //~0218I~
    }                                                              //~v@@@M~//~9303R~//~9305R~
    //******************************************                   //~0218I~
    private void hideResponseEswn()                                //~0218I~
    {                                                              //~0218I~
    	if (Dump.Y) Dump.println("AccountsDlg.hideResponseEswn");  //~0218I~
        hideResponseEswn(!swRequester);                            //~0218I~
    }                                                              //~0218I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    protected void setupValueAdditional(View PView)                //~9321I~
    {                                                              //~9321I~
    	if (Dump.Y) Dump.println("AccountsDlg.initEnv");              //~9321I~//~9322R~
//  	accountNames=ACC.getAccountNames();               //~9312I~//~9321M~//~9322R~//~9416R~
    	accountNames=ACC.getAccountNamesByPosition();              //~9416I~
//  	accountEswn=ACC.getStartingEswnOfAccounts();               //~9322I~//~9615R~
        btnTotal        =              UButton.bind(PView,R.id.FixTotal,this);//~9321M~
//      btnShowRule     =              UButton.bind(PView,R.id.ShowRule,this);//~9417I~//~9615R~
                                       UButton.bind(PView,R.id.ShowRule,this);//~9615I~
    }                                                              //~9322I~
    //******************************************                   //~9818R~
    @Override //UFDlg                                              //~9818R~
    protected int getDialogWidth()                                 //~9818R~
    {                                                              //~9818R~
        int ww=0;                                                  //~9818R~
//      if (AG.swSmallDevice && AG.portrait)                      //~9818R~//~0218R~
        if (AG.swSmallDevice)                                      //~0218I~
        {                                                          //~9818R~
          if (AG.portrait)                                         //~0218I~
            ww=(int)(AG.scrWidth*RATE_SMALLDEVICE_WIDTH);          //~9818R~//+vajkR~
          else                                                     //~0218I~
            ww=(int)(AG.scrWidth*RATE_SMALLDEVICE_WIDTH_LANDSCAPE);//~0218I~
        }                                                          //~9818R~
        if (Dump.Y) Dump.println("AccountsDlg.getDialogWidth ww="+ww+",portrait="+AG.portrait+",smallDevice="+AG.swSmallDevice);//~9818R~
        return ww;                                                 //~9818R~
    }                                                              //~9818R~
    //******************************************                   //~9322I~
    protected void saveLast()                                       //~9322I~//~9819R~
    {                                                              //~9322I~
        CMP.lastScore=lastScore;	//save for reshow              //~9322R~//~9402R~//~9415R~
        CMP.minusPrize=minusPrize;	//save for reshow              //~9415I~
        CMP.minusCharge=minusCharge;	//save for reshow          //~9415I~
        CMP.finalScore=finalScore;	//save for reshow              //~9415I~
        CMP.pos2Order=idx2Order;                                   //~9520I~
    }                                                              //~9321I~
    //******************************************                   //~v@@@I~//~9220R~//~9303R~//~9306R~
    protected void setTitle()                                        //~v@@@I~//~9220R~//~9303R~//~9306R~
    {                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
        Spanned s=Status.getSpannedGameTitle(Utils.getStr(TITLEID));//~9306I~
        androidDlg.setTitle(s);                                    //~v@@@R~//~9220R~//~9302R~//~9303R~//~9306R~
    }                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
    //******************************************                   //~9320I~
    protected void getRuleSetting()                                  //~9320I~//~9819R~
    {                                                              //~9320I~
    	initialScore=RuleSetting.getInitialScore();            //~9322R~//~9401R~
    	debt=RuleSetting.getDebt();                            //~9322I~//~9401R~
        settingTopPrize=ACC.topPrize;                              //~9428R~
    	settingOrderPrize=RuleSetting.getOrderPrize();              //~9322R~
    	swOrderByEswn=RuleSetting.isOrderByEswn();                 //~9322I~
//  	swSamePointCut=RuleSetting.isOrderPrizeSpritCut();         //~9407R~
    	swMinusPayGetAllPoint=RuleSetting.isMinusPayGetAllPoint(); //~9415I~
    	adjustFinalPoint=RuleSetting.getScoreToPoint();            //~9416I~
    	robotPayType=RuleSetting.getRobotPayType();                //~9429I~
    	swBird=ACC.isGrillBird();                                  //~9501I~
    	birdPay=RuleSetting.getGrilledBirdPay();                   //~9501I~
    	swBirdPayToEach=RuleSetting.isBirdPayToEach();             //~9602I~
    }                                                              //~9320I~
    //******************************************                   //~9401I~
    protected void showRuleSetting(View PView)                     //~9401I~
    {                                                              //~9401I~
        TextView tv;                                               //~9401I~
        String s;                                                  //~9401I~
        tv=(TextView)    UView.findViewById(PView,R.id.SettingTopPrize);//~9401R~
        s=AG.resource.getString(R.string.Label_AccountsSettingTopPrizeEdit,(debt-initialScore)*PLAYERS,initialScore,debt);//~9401R~//~9407R~
    	if (Dump.Y) Dump.println("AccountsDlg.showRuleSetting topprize="+s);//~9401I~
        tv.setText(s);                                             //~9401I~
//      tv=(TextView)    UView.findViewById(PView,R.id.SettingOrderPrize);//~9401R~//~9819R~
//      s=AG.resource.getString(R.string.Label_AccountsSettingOrderPrizeEdit,settingOrderPrize[1],settingOrderPrize[0]);//~9401R~//~9819R~
//  	if (Dump.Y) Dump.println("AccountsDlg.showRuleSetting orderprize="+s);//~9401I~//~9819R~
//      tv.setText(s);                                             //~9401I~//~9819R~
//      URadioGroup rg=new URadioGroup(PView,R.id.rgSamePointOption,0/*parm*/,rbIDSamePoint);//~9401I~//~9819R~
//      rg.setCheckedID(swOrderByEswn ? 0 : 1,true/*swFixed*/);    //~9401I~//~9819R~
        RuleSetting.setOrderPrize(PView,true/*swFixed*/);          //~9819I~
        RuleSetting.setGrillBird(PView,true/*swFixed*/);           //~9819I~
        RuleSetting.setRobotOption(PView,true/*swFixed*/);         //~9823I~
//      if (PrefSetting.isNoRelatedRule())                       //~9606I~//~9607M~//~9708R~
//          (UView.findViewById(PView,R.id.llRelatedRule)).setVisibility(View.GONE);//~9606I~//~9607M~//~9708R~
//      else                                                       //~9607I~//~9708R~
	    	RuleSetting.setScoreToPoint(PView,true);                   //~9416I~//~9607R~
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
        tvsOrder=new TextView[PLAYERS];                             //~9321I~//~9322R~
    	tvsName=new TextView[PLAYERS];                             //~9321I~
    	tvsEswn=new TextView[PLAYERS];                             //~9401I~
    	tvsScore=new TextView[PLAYERS];                            //~9321I~
    	tvsTopPrize=new TextView[PLAYERS];                              //~9321I~//~9322R~
    	tvsOrderPrize=new TextView[PLAYERS];                       //~9322I~
    	tvsTotal=new TextView[PLAYERS];                            //~9321I~
    	tvsMinusPrize=new TextView[PLAYERS];                       //~9415I~
    	tvsMinusCharge=new TextView[PLAYERS];                      //~9415I~
    	tvsFinalScore=new TextView[PLAYERS];                       //~9415I~
    	tvsFinalPoint=new TextView[PLAYERS];                       //~9416I~
    	tvsBotCharge=new TextView[PLAYERS];                        //~9429I~
//    	tvsLabelBotCharge=new TextView[PLAYERS];                   //~9501I~//~9819R~
        tvLabelBotCharge=(TextView)     UView.findViewById(PView,R.id.Label_ForBotCharge);//~9819I~
    	llsMinusPrize=new LinearLayout[PLAYERS];                   //~9416I~
    	llsMinusCharge=new LinearLayout[PLAYERS];                  //~9416I~
    	llsBotCharge=new LinearLayout[PLAYERS];                    //~9429I~
        for (int ii=0;ii<PLAYERS;ii++)                                 //~9321I~
        {                                                          //~9321I~
        	LinearLayout ll=lls[ii];                               //~9321I~
	        tvsOrder[ii]=(TextView)    UView.findViewById(ll,R.id.Order);//~9321I~//~9322R~
	        tvsEswn[ii]=(TextView)    UView.findViewById(ll,R.id.StartingEswn);//~9401I~
	        tvsName[ii]=(TextView)    UView.findViewById(ll,R.id.memberName);//~9321I~
	        tvsScore[ii]=(TextView)     UView.findViewById(ll,R.id.Score);//~9321I~//~9322R~
	        tvsTopPrize[ii]=(TextView)     UView.findViewById(ll,R.id.TopPrize);//~9321I~//~9322R~
	        tvsOrderPrize[ii]=(TextView)     UView.findViewById(ll,R.id.OrderPrize);//~9321I~//~9322R~
	        tvsTotal[ii]=(TextView)     UView.findViewById(ll,R.id.Total);//~9322I~
	        tvsMinusPrize[ii]=(TextView)     UView.findViewById(ll,R.id.minusPrize);//~9415I~
	        tvsMinusCharge[ii]=(TextView)     UView.findViewById(ll,R.id.minusCharge);//~9415I~
	        tvsFinalScore[ii]=(TextView)     UView.findViewById(ll,R.id.finalScore);//~9415I~
	        tvsFinalPoint[ii]=(TextView)     UView.findViewById(ll,R.id.finalPoint);//~9416I~
	        llsMinusPrize[ii]=(LinearLayout)     UView.findViewById(ll,R.id.llMinusPrize);//~9416I~
	        llsMinusCharge[ii]=(LinearLayout)     UView.findViewById(ll,R.id.llMinusCharge);//~9416I~
	        llsBotCharge[ii]=(LinearLayout)     UView.findViewById(ll,R.id.llForBotCharge);//~9429I~
	        tvsBotCharge[ii]=(TextView)     UView.findViewById(ll,R.id.tvForBot);//~9429I~
//          tvsLabelBotCharge[ii]=(TextView)     UView.findViewById(ll,R.id.Label_ForBotCharge);//~9501I~//~9819R~
        }                                                          //~9321I~
    }                                                              //~9309I~
    //******************************************                   //~9309I~
    protected void setAccountName()                                //~9309I~
    {                                                              //~9309I~
    	if (Dump.Y) Dump.println("AccountsDlg.setAccountName");    //~9309I~//~9316R~//~9322R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9309I~
        {                                                          //~9309I~
//          int eswn = ACC.accounts[ii].ESWN;	//startingESWN         //~9401I~//~9416R~
            int eswn = ii;                                         //~9416I~
        	tvsName[eswn].setText(accountNames[ii]);                 //~9309I~//~9401R~
	        tvsEswn[eswn].setText(GConst.nameESWN[eswn]); //~9312I~  //~9320R~//~9401R~
        }                                                          //~9309I~
    }                                                              //~9309I~
    //******************************************                   //~9309I~
    protected void showAmmount()                                     //~9309I~//~9819R~
    {                                                              //~9309I~
//    	testPrize();	//TODO test                                //~9401I~//~9402R~//~9407R~//~0322R~
       	getOrder(lastScore);                                       //~9322I~
       	setPrize(lastScore);                                                //~9322I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9309I~
        {                                                          //~9309I~
//            int idx=ACC.currentEswnToMember(ii);          //~9316M~//~9322R~//~9401R~
//            tvsOrder[ii].setText(Integer.toString(idx2Order[ii]+1));                       //~9312I~//~9316R~//~9322R~//~9401R~
//            tvsScore[ii].setText(Integer.toString(lastScore[ii])); //~9322I~//~9401R~
//            tvsTopPrize[ii].setText(Integer.toString(topPrize[ii]));//~9321I~//~9322R~//~9401R~
//            tvsOrderPrize[ii].setText(Integer.toString(orderPrize[ii]));//~9322I~//~9401R~
//            newTotal[ii]=lastScore[ii]+topPrize[ii]+orderPrize[ii];//~9322I~//~9401R~
//            tvsTotal[ii].setText(Integer.toString(newTotal[ii]));  //~9322I~//~9401R~
//          int eswn=ACC.accounts[ii].ESWN;                        //~9401I~//~9416R~
            int eswn=ii;    //account lsit is position order       //~9416I~
            tvsOrder[eswn].setText(Integer.toString(idx2Order[ii]+1));//~9401R~
            tvsScore[eswn].setText(Integer.toString(lastScore[ii]));//~9401R~
          if (topPrize[ii]!=0)                                     //~vac9I~
            tvsTopPrize[eswn].setText(Integer.toString(topPrize[ii]));//~9401R~
          else                                                     //~vac9I~
            tvsTopPrize[eswn].setText(" ");                        //~vac9I~
            tvsOrderPrize[eswn].setText(Integer.toString(orderPrize[ii]));//~9401R~
            newTotal[ii]=lastScore[ii]+topPrize[ii]+orderPrize[ii];//~9401R~
            tvsTotal[eswn].setText(Integer.toString(newTotal[ii]));//~9401R~
            int mp=minusPrize[ii];                                 //~9416I~
            if (mp!=0)                                             //~9416I~
	            tvsMinusPrize[eswn].setText(Integer.toString(mp));//~9415R~//~9416R~
            else                                                   //~9416I~
//              llsMinusPrize[eswn].setVisibility(View.GONE);      //~9416I~//~vac9R~
	            tvsMinusPrize[eswn].setText(" ");                  //~vac9I~
	    	if (swMinusPayGetAllPoint)                             //~9415I~
            {                                                      //~9415I~
                int mc=minusCharge[ii];                            //~9416I~
	            finalScore[ii]=newTotal[ii]+minusPrize[ii]+mc;//~9415I~//~9416R~
                if (mc!=0)                                         //~9416I~
		            tvsMinusCharge[eswn].setText(Integer.toString(mc));//~9415I~//~9416R~
                else                                               //~9416I~
//		            llsMinusCharge[eswn].setVisibility(View.GONE); //~9416I~//~vac9R~
		            tvsMinusCharge[eswn].setText("0");             //~vac9R~
            }                                                      //~9415I~
            else                                                   //~9415I~
            {                                                      //~9415I~
	            finalScore[ii]=newTotal[ii]+minusPrize[ii];        //~9415I~
//              tvsMinusCharge[eswn].setText(Integer.toString(0)); //~9415I~//~9416R~
//  	        llsMinusCharge[eswn].setVisibility(View.GONE);     //~9416I~//~vac9R~
                tvsMinusCharge[eswn].setText(" ");                 //~vac9I~
            }                                                      //~9415I~
            tvsFinalScore[eswn].setText(Integer.toString(finalScore[ii]));//~9415I~
        }                                                          //~9309I~
        if (swBird)                                                //~9501I~
        {                                                          //~9824I~
        	tvLabelBotCharge.setText(R.string.Label_Bird);         //~9824R~
	    	adjustByBird(finalScore);                              //~9501I~
        }                                                          //~9824I~
        else                                                       //~9501I~//~9611R~
        if (ACC.activeMembers<PLAYERS)                              //~9429I~
	    	adjustByRobotScore(finalScore);                        //~9429R~
        else                                                       //~9824I~
        {                                                          //~9824I~
        	tvLabelBotCharge.setText("");                       //~9819I~//~9824I~
        }                                                          //~9824I~
       	getFinalPoint(finalScore,lastScore);                                           //~9416I~//~9417R~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9416I~
        {                                                          //~9416I~
        	String s;                                              //~9416I~
            int fp=finalPoint[ii];                                 //~9416I~
            if (adjustFinalPoint==S2P_NO)                        //~9416I~
            {                                                      //~9614I~
            	s=fp/1000+"."+((Math.abs(fp)%1000)/100);           //~9614I~
            }                                                      //~9614I~
            else                                                   //~9416I~
            {                                                      //~9614I~
            	s=Integer.toString(fp/1000);                       //~9416I~
            }                                                      //~9614I~
            tvsFinalPoint[ii].setText(s);                          //~9416R~
            finalPoint1000[ii]=s;                                  //~9615I~
        }                                                          //~9416I~
        ACC.finalPoint1000=finalPoint1000;                         //~9614I~
//      endScore=newTotal;                                         //~9402I~//~9415R~
    }                                                              //~9309I~
    //********************************************************     //~9429I~
    protected void adjustByRobotScore(int [] PfinalScore)            //~9429I~//~9821R~
    {                                                              //~9429I~
    	if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore swTrainingMode="+AG.swTrainingMode+",payType="+robotPayType+",finalScore="+Arrays.toString(PfinalScore));//~9429R~//~va66R~
        if (AG.swTrainingMode)                                     //~va66I~
        	return;                                                //~va66I~
    	Accounts.Account act;
        int[] forRobot=new int[PLAYERS];                           //~9429I~
        int robotPay=0;                                            //~9429I~
    	int maxPay=-0x10000000;                                    //~9429I~
    	int minPay=+0x10000000;                                    //~9429I~
        int ctrRobot=0;                                            //~9429I~
        int maxPlayer=0;                                           //~9429I~
        for (int ii=0;ii<PLAYERS;ii++)     //account position sequence      //~9429I~//~9501R~
        {                                                          //~9429I~
        	llsBotCharge[ii].setVisibility(View.VISIBLE);          //~9429I~
        	act=ACC.accountsByESWN[ii];                      //~9429I~
	        int score=PfinalScore[ii]-debt;                        //~9429R~
            if (!act.isDummy())                                      //~9429I~
            {                                                      //~9429I~
	            if (score>maxPay)                                  //~9429R~
                {                                                  //~9429I~
                	maxPlayer=ii;                                  //~9429I~
                    maxPay=score;                                  //~9429I~
                }                                                  //~9429I~
	            minPay=Math.min(minPay,score);                     //~9429R~
            	continue;                                          //~9429I~
            }                                                      //~9429I~
    		if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore ii="+ii+",finalScore="+PfinalScore[ii]);//~9429I~
            robotPay+=score;	//robot's minus value                                       //~9429I~//~9607R~
            ctrRobot++;                                            //~9429I~
        }                                                          //~9429I~
    	if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore total finalScore="+robotPay+",ctrRobot="+ctrRobot+",minPay="+minPay+",maxPay="+maxPay);//~9429R~
        if (robotPay==0)                                           //~9429I~
        	return;                                                //~9429I~
        switch (robotPayType)                                      //~9429I~
        {                                                          //~9429I~
        case ROBOTPAY_SPRIT:                                       //~9429I~
        	int spritPay=((robotPay/(PLAYERS-ctrRobot))/100)*100;  //~9429R~
	    	if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore spritPay="+spritPay);//~9429I~
            for (int ii=0;ii<PLAYERS;ii++)     //account sequence  //~9429I~
            {                                                      //~9429I~
                act=ACC.accountsByESWN[ii];                  //~9429I~
                if (!act.isDummy())                                  //~9429I~
                {                                                  //~9429I~
                    if (ii!=maxPlayer)                             //~9429I~
                    {                                              //~9429I~
                		forRobot[ii]=spritPay;                     //~9429R~
                		if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore ii="+ii+",finalScore="+PfinalScore[ii]);//~9429R~
                    }                                              //~9429I~
                }                                                  //~9429I~
                else                                               //~9429I~
                	forRobot[ii]=-(PfinalScore[ii]-debt);          //~9429R~
            }                                                      //~9429I~
            forRobot[maxPlayer]+=robotPay-spritPay*(PLAYERS-ctrRobot-1);//~9429R~
            if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore forRobot="+Arrays.toString(forRobot));//~9429I~//~9823R~
            for (int ii=0;ii<PLAYERS;ii++)     //account sequence  //~9429I~
            {                                                      //~9429I~
                tvsBotCharge[ii].setText(Integer.toString(forRobot[ii]));//~9429R~
				PfinalScore[ii]+=forRobot[ii];                     //~9429I~
            }                                                      //~9429I~
            if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore finalScore="+Arrays.toString(PfinalScore));//~9429I~
        	break;                                                 //~9429I~
        case ROBOTPAY_BYSCORE:                                     //~9429I~
            int totalForRate=0;                                    //~9429I~
            for (int ii=0;ii<PLAYERS;ii++)     //account sequence  //~9429I~
            {                                                      //~9429I~
                act=ACC.accountsByESWN[ii];                  //~9429I~
                if (!act.isDummy())                                  //~9429I~
                {                                                  //~9429I~
			        int score=PfinalScore[ii]-debt; //real player's plus//~9607R~
                	totalForRate+=score-minPay;     //plus over minimum plus//~9607R~
			    	if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore ii="+ii+",finalScore="+PfinalScore[ii]+",score="+score+",minPay="+minPay+",totalForRate="+totalForRate);//~9607I~
                }                                                  //~9429I~
            }                                                      //~9429I~
	    	if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore byScore totalForRate="+totalForRate+",robotPay="+robotPay);//~9429R~//~9607R~
            int robotPayNonTop=0;                                  //~9429I~
            for (int ii=0;ii<PLAYERS;ii++)     //account sequence  //~9429I~
            {                                                      //~9429I~
                act=ACC.accountsByESWN[ii];                  //~9429I~
                int score=PfinalScore[ii]-debt;                    //~9429I~
                if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore ii="+ii+",score="+score+",minPay="+minPay+",forRobot[ii]="+forRobot[ii]);//~9607I~
                if (!act.isDummy())                                  //~9429I~
                {                                                  //~9429I~
                    if (ii!=maxPlayer)     //plus not minimum plus player//~9607R~
                    {                                              //~9429I~
                      if (totalForRate==0)                         //~9607I~
	                	forRobot[ii]=(robotPay/ctrRobot)/100*100;  //~9607R~
                      else                                         //~9607I~
	                	forRobot[ii]=(((robotPay*(score-minPay))/totalForRate)/100)*100;//~9429R~
                        robotPayNonTop+=forRobot[ii];              //~9429I~
                		if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore ii="+ii+",score="+score+",forRobot[ii]="+forRobot[ii]);//~9429I~
                    }                                              //~9429I~
                }                                                  //~9429I~
                else                                               //~9429I~
	                forRobot[ii]=-score;     //set robot to total 0      //~9429I~//~9607R~
            }                                                      //~9429I~
            forRobot[maxPlayer]=robotPay-robotPayNonTop;           //~9429I~
            if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore forRobot="+Arrays.toString(forRobot));//~9429I~
            for (int ii=0;ii<PLAYERS;ii++)     //account sequence  //~9429I~
            {                                                      //~9429I~
                tvsBotCharge[ii].setText(Integer.toString(forRobot[ii]));//~9429R~
				PfinalScore[ii]+=forRobot[ii];                     //~9429I~
            }                                                      //~9429I~
            if (Dump.Y) Dump.println("AccountsDlg.adjustByRobotScore finalScore="+Arrays.toString(PfinalScore));//~9429I~
        	break;                                                 //~9429I~
        }                                                          //~9429I~
    }//                                                            //~9429I~
    //********************************************************     //~9501I~
    protected void adjustByBird(int [] PfinalScore)                  //~9501I~//~9821R~
    {                                                              //~9501I~
    	if (Dump.Y) Dump.println("AccountsDlg.adjustByBird finalScore="+Arrays.toString(PfinalScore));//~9501I~
    	Accounts.Account act;                                      //~9501I~
        int ctrNonGrilled=0;                                         //~9501I~
        int[] intsBirdPay=new int[PLAYERS];                        //~9501I~
//      String labelBird=Utils.getStr(R.string.Label_Bird);        //~9501I~//~9824R~
        for (int ii=0;ii<PLAYERS;ii++)     //account position sequence//~9501I~
        {                                                          //~9501I~
        	llsBotCharge[ii].setVisibility(View.VISIBLE);          //~9501I~
//          tvsLabelBotCharge[ii].setText(labelBird);              //~9501I~//~9819R~
        	act=ACC.accountsByESWN[ii];                            //~9501I~
            if (!act.isGrilled())                                  //~9501I~
            	ctrNonGrilled++;                                    //~9501I~
        }                                                          //~9501I~
        for (int ii=0;ii<PLAYERS;ii++)     //account position sequence//~9501I~
        {                                                          //~9501I~
        	act=ACC.accountsByESWN[ii];                            //~9501I~
            if (!act.isGrilled())   //once upped                  //~9501I~//~9602R~
            {                                                      //~9602I~
      		  if (swBirdPayToEach)                                 //~9602I~
                intsBirdPay[ii]=birdPay*(PLAYERS-ctrNonGrilled);   //~9501I~
              else                                                 //~9602I~
                intsBirdPay[ii]=birdPay*(PLAYERS-ctrNonGrilled)/ctrNonGrilled;//~9602I~
            }                                                      //~9602I~
            else                    //no once upped                //~9501I~//~9602R~
            {                                                      //~9602I~
      		  if (swBirdPayToEach)                                 //~9602I~
                intsBirdPay[ii]=-birdPay*ctrNonGrilled;            //~9501I~
              else                                                 //~9602I~
                intsBirdPay[ii]=-birdPay;                          //~9602I~
            }                                                      //~9602I~
        }                                                          //~9501I~
    	if (Dump.Y) Dump.println("AccountsDlg.adjustByBird birdPay="+birdPay+",ctrNonGrilled="+ctrNonGrilled+",intsBirdPay="+Arrays.toString(intsBirdPay));//~9501I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9501I~
        {                                                          //~9501I~
            tvsBotCharge[ii].setText(Integer.toString(intsBirdPay[ii]));//~9501I~
            PfinalScore[ii]+=intsBirdPay[ii];                      //~9501I~
        }                                                          //~9501I~
        if (Dump.Y) Dump.println("AccountsDlg.adjustByBird finalScore="+Arrays.toString(PfinalScore));//~9501I~
    }//                                                            //~9501I~
    //********************************************************     //~9416R~
    protected void getFinalPoint(int[] PfinalScore,int[] PlastScore/*net score without prize*/)                  //~9416I~//~9417R~//~9821R~
    {                                                              //~9416I~
    	if (Dump.Y) Dump.println("AccountsDlg.getFinalPoint finalScore="+Arrays.toString(PfinalScore)+",lastScore="+Arrays.toString(PlastScore));//~9424R~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9416I~
        {                                                          //~9416I~
            int fp,up,fr;                                          //~9417R~
        	int fs=finalScore[ii];                                 //~9417R~
            fp=fs-debt;                            //~9416R~       //~9417I~
            if (adjustFinalPoint!=S2P_NO)                          //~9416R~//~9417R~
            {                                                      //~9417I~
                switch(adjustFinalPoint)                               //~9416I~//~9417R~
                {                                                      //~9416I~//~9417R~
                case S2P_FLOOR:                                        //~9416I~//~9417R~
                    fp=(fs/1000)*1000-debt;                                        //~9416I~//~9417R~
                    break;                                             //~9416I~//~9417R~
                case S2P_CEILING:                                      //~9416I~//~9417R~
                    if (fp>=0)                                     //~9417R~
                        fp=((fp+900)/1000)*1000;                   //~9417I~
                    else                                           //~9417I~
                        fp=(fp/1000)*1000;    //cut minus under 1000//~9417I~
                    break;                                             //~9416I~//~9417R~
                case S2P_45:	//not same as mathematical(absolute value), up to plus                                           //~9416I~//~9417R~
//                    fr=fp%1000;                                  //~9417R~
//                    if (fr!=0)                                   //~9417R~
//                    {                                            //~9417R~
//                        if (fp>=0)                               //~9417R~
//                            up=fr>=500 ? 1000 :0;                //~9417R~
//                        else                                     //~9417R~
//                            up=fr>-500 ? -1000 :0;               //~9417R~
//                        fp=((fp+up)/1000)*1000;                  //~9417R~
//                    }                                            //~9417R~
                    if (fp>=0)                                     //~9417I~
                        fp=((fp+500)/1000)*1000;  //900<-->500==>+1000//~9417I~
                    else                                           //~9417I~
                        fp=((fp-400)/1000)*1000;  //-600<-->-900==>-1000//~9417I~
                    break;                                             //~9416I~//~9417R~
                case S2P_56:                                           //~9416I~//~9417R~
//                    fr=fp%1000;                                  //~9417R~
//                    if (fr!=0)                                   //~9417R~
//                    {                                            //~9417R~
//                        if (fp>=0)                               //~9417R~
//                            up=fr>=600 ? 1000 :0;                //~9417R~
//                        else                                     //~9417R~
//                            up=fr>-600 ? -1000 :0;               //~9417R~
//                        fp=((fp+up)/1000)*1000;                  //~9417R~
//                    }                                            //~9417R~
                    if (fp>=0)                                     //~9417I~
                        fp=((fp+400)/1000)*1000;  //900<-->600==>+1000//~9417I~
                    else                                           //~9417I~
                        fp=((fp-500)/1000)*1000;  //-500<-->-900==>-1000//~9417I~
                    break;                                             //~9416I~//~9417R~
                case S2P_PM:                                       //~9417I~
                	if (PlastScore[ii]>=debt)                      //~9417I~
                    {                                              //~9417I~
                    	fp=(fs/1000)*1000-debt;                    //~9417I~
                    }                                              //~9417I~
                    else                                           //~9417I~
                    {                                              //~9417I~
	                    if (fp>=0)                                 //~9417I~
    	                    fp=((fp+900)/1000)*1000;               //~9417I~
        	            else                                       //~9417I~
            	            fp=(fp/1000)*1000;    //cut minus under 1000//~9417I~
                    }                                              //~9417I~
                    break;                                         //~9417I~
                }                                                      //~9416I~//~9417R~
            }                                                      //~9417I~
            finalPoint[ii]=fp;                                     //~9417I~
        }                                                          //~9416I~
    	if (Dump.Y) Dump.println("AccountsDlg.getFinalPoint="+Arrays.toString(finalPoint));//~9416I~
        if (adjustFinalPoint!=S2P_NO)                              //~9416I~
        {                                                          //~9416I~
            int sum=0;                                             //~9416I~
    	    for (int ii=0;ii<PLAYERS;ii++)     //account sequence  //~9416I~
        	{                                                      //~9416I~
        		sum+=finalPoint[ii];                               //~9416I~
            }                                                      //~9416I~
	    	int idxTop=order2Idx[0];                               //~9416I~
            finalPoint[idxTop]-=sum;	//zero sum                 //~9416I~
	    	if (Dump.Y) Dump.println("AccountsDlg.getFinalPoint idxTop="+idxTop+",sum="+sum+",finalPoint="+Arrays.toString(finalPoint));//~9416I~
        }                                                          //~9416I~
    }                                                              //~9416I~
    //******************************************                   //~9401I~
    private void testPrize()                                       //~9401I~
    {                                                              //~9401I~
    	if (Dump.Y) Dump.println("AccountsDlg.testPrize Start");   //~9416I~
    	int[] score1;                                              //~9402R~
    	score1=new int[]{0,32000,18000,-3000};                     //~9402I~
       	getOrder(score1);                                          //~9401I~
       	setPrize(score1);                                          //~9401I~
    	score1=new int[]{0,32000,32000,32000};                     //~9402I~
       	getOrder(score1);                                          //~9402I~
       	setPrize(score1);                                          //~9402I~
    	score1=new int[]{-32000,-32000,-32000,-32000};             //~9402I~
       	getOrder(score1);                                          //~9402I~
       	setPrize(score1);                                          //~9402I~
    	score1=new int[]{ 12000, 32000,-12000, 32000};             //~9402I~
       	getOrder(score1);                                          //~9402I~
       	setPrize(score1);                                          //~9402I~
    	score1=new int[]{-12000, 32000,-12000, 32000};             //~9402I~
       	getOrder(score1);                                          //~9402I~
       	setPrize(score1);                                          //~9402I~
    	score1=new int[]{ 32000, 12000,-12000, 12000};             //~9402I~
       	getOrder(score1);                                          //~9402I~
       	setPrize(score1);                                          //~9402I~
    	score1=new int[]{ 32000, 12000,-12000,-12000};             //~9402I~
       	getOrder(score1);                                          //~9402I~
       	setPrize(score1);                                          //~9402I~
    	score1=new int[]{ 32000,-12000,-12000,-12000};             //~9402I~
       	getOrder(score1);                                          //~9402I~
       	setPrize(score1);                                          //~9402I~
    	if (Dump.Y) Dump.println("AccountsDlg.testPrize End");     //~9416I~
    }                                                              //~9401I~
    //**************************************************************                   //~9401I~//~9407R~
    //*sort by point , if same order by eswn at initial            //~9407I~
    //**************************************************************//~9407I~
//    public int[] sortByPoint(int[] Pscore)                        //~9401I~//~9520R~
//    {                                                              //~9401I~//~9520R~
//        Integer[] scoreI=new Integer[]{0,1,2,3};    //idxPosition  //~9416R~//~9520R~
//        Arrays.sort(scoreI,new PointComp(Pscore));                 //~9401R~//~9520R~
//        int[] sorted=new int[]{scoreI[0],scoreI[1],scoreI[2],scoreI[3]};//~9520R~
//        if (Dump.Y) Dump.println("AccountsDlg.sortByPoint sorted="+Arrays.toString(sorted));//~9416I~//~9520R~
//        return sorted;                                              //~9401I~//~9520R~
//    }                                                              //~9401I~//~9520R~
//    //******************************************                   //~9401I~//~9520R~
//    class PointComp implements Comparator<Integer>                     //~9401I~//~9520R~
//    {                                                              //~9401I~//~9520R~
//        int[] compScore;                                           //~9401R~//~9520R~
//        public PointComp(int[] Pscore)                             //~9401R~//~9520R~
//        {                                                          //~9401I~//~9520R~
//            compScore=Pscore;                                      //~9401I~//~9520R~
//            if (Dump.Y) Dump.println("AccountsDlg.sortByPoint compScore="+Arrays.toString(compScore));//~9416I~//~9520R~
//        }                                                          //~9401I~//~9520R~
//        @Override                                                  //~9401I~//~9520R~
//        public int compare(Integer Pidx1,Integer Pidx2)                    //~9401I~//~9520R~
//        {                                                          //~9401I~//~9520R~
//            int rc=-(compScore[Pidx1]-compScore[Pidx2]);           //~9401R~//~9520R~
//            if (rc==0)                                             //~9401I~//~9520R~
//            {                                                      //~9401I~//~9520R~
////              int eswn1=ACC.accounts[Pidx1].ESWN;                    //~9401I~//~9416R~//~9520R~
////              int eswn2=ACC.accounts[Pidx2].ESWN;                    //~9401I~//~9416R~//~9520R~
//                int eswn1=Pidx1;                                   //~9416I~//~9520R~
//                int eswn2=Pidx2;                                   //~9416I~//~9520R~
//                rc=eswn1-eswn2;                                    //~9401R~//~9520R~
//                if (Dump.Y) Dump.println("AccountsDlg.PointComp.compre eswn1="+eswn1+",eswn2="+eswn2);//~9401I~//~9416R~//~9520R~
//            }                                                      //~9401I~//~9520R~
//            if (Dump.Y) Dump.println("AccountsDlg.PointComp.compre rc="+rc+",idx1="+Pidx1+",idx2="+Pidx2+",score1="+compScore[Pidx1]+",score2="+compScore[Pidx2]);//~9401R~//~9416R~//~9520R~
//            return rc;                                             //~9401I~//~9520R~
//        }                                                          //~9401I~//~9520R~
//    }                                                              //~9401I~//~9520R~
    //******************************************                   //~9322I~
    protected void getOrder(int[] Pscore)                           //~9322I~//~9821R~
    {                                                              //~9322I~
//        order2Idx=sortByPoint(Pscore);                             //~9401I~//~9520R~
//        if (Dump.Y) Dump.println("AccountsDlg:getOrder score="+Arrays.toString(Pscore));//~9322I~//~9401M~//~9520R~
//        if (Dump.Y) Dump.println("AccountsDlg:getOrder order2Idx="+Arrays.toString(order2Idx));//~9322I~//~9401M~//~9520R~
//        if (Dump.Y) Dump.println("AccountsDlg:getOrder order2eswn="+ACC.accounts[order2Idx[0]].ESWN+","+ACC.accounts[order2Idx[1]].ESWN+","+ACC.accounts[order2Idx[2]].ESWN+","+ACC.accounts[order2Idx[3]].ESWN);//~9407I~//~9520R~
//        idx2Order=new int[PLAYERS];                                //~9401I~//~9520R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~9401I~//~9520R~
//            idx2Order[order2Idx[ii]]=ii;                           //~9401I~//~9520R~
//        if (Dump.Y) Dump.println("AccountsDlg:getOrder idx2Order="+Arrays.toString(idx2Order));//~9401I~//~9520R~
		ScoreSort scoreSort=new ScoreSort();                       //~9520I~
        idx2Order=scoreSort.getOrder(Pscore);                      //~9520I~
        order2Idx=scoreSort.order2Pos;                             //~9520I~
    }                                                              //~9322I~
    //******************************************                   //~9322I~
    protected void setPrize(int [] Pscore)                           //~9322R~//~9821R~
    {                                                              //~9322I~
        Arrays.fill(topPrize,0);                                    //~9322I~//~9401M~
        Arrays.fill(orderPrize,0);                                  //~9322I~//~9401M~
        int ctr=0;
        if (swOrderByEswn)                                         //~9401I~
        {                                                          //~9401I~
        	topPrize[order2Idx[0]]=settingTopPrize;                //~9401I~
        }                                                          //~9401I~
        else                                                       //~9401I~
        {                                                          //~9401I~
          	int s=Pscore[order2Idx[0]];                             //~9401I~
            for (int ii=0;ii<PLAYERS;ii++)                             //~9322I~//~9401R~
            {                                                          //~9322I~//~9401R~
                if (s==Pscore[ii])                               //~9322I~//~9401R~
                    ctr++;                                             //~9322I~//~9401R~
            }                                                          //~9322I~//~9401R~
            int p=settingTopPrize/ctr;                             //~9401I~//~9407R~
//            int premain=0;                                       //~9407R~
//            if (p%100!=0)   //                                   //~9407R~
//            {                                                    //~9407R~
//                int pcut=(p/100)*100;                            //~9407R~
//                premain=p-pcut*(ctr-1);                          //~9407R~
//                p=pcut;                                          //~9407R~
//            }                                                    //~9407R~
            for (int ii=0;ii<PLAYERS;ii++)                         //~9401I~
            {                                                      //~9401I~
                if (s==Pscore[ii])                                  //~9401I~
                    topPrize[ii]=p;                                //~9401I~
            }                                                      //~9401I~
//            if (premain!=0)                                      //~9407R~
//                topPrize[order2Idx[0]]=premain;                  //~9407R~
        }                                                          //~9401I~
        if (Dump.Y) Dump.println("AccountsDlg:setTopPrize ctr="+ctr);//~9401R~
        if (Dump.Y) Dump.println("AccountsDlg:setTopPrize score="+Arrays.toString(Pscore));//~9322I~
        if (Dump.Y) Dump.println("AccountsDlg:setTopPrize topPrize="+Arrays.toString(topPrize));//~9322I~
        setOrderPrize(Pscore);                                     //~9322I~
    }                                                              //~9322I~
    //******************************************                   //~9401I~
    private void setOrderPrize(int [] Pscore)                      //~9401I~
    {                                                              //~9401I~
    	int pcut,premain;                                          //~9407R~
        if (swOrderByEswn)                                         //~9401I~
        {                                                          //~9401I~
        	for (int ii=0;ii<PLAYERS;ii++)                         //~9401I~
            {                                                      //~9401I~
        		orderPrize[order2Idx[ii]]=settingOrderPrize[ii];   //~9401I~
            }                                                      //~9401I~
        }                                                          //~9401I~
        else                                                       //~9401I~
        {                                                          //~9401I~
        	for (;;)                                               //~9401I~
            {                                                      //~9401I~
            //*top                                                 //~9401I~
                int ctr=0;                                         //~9401I~
                int s=Pscore[order2Idx[0]];                         //~9401I~
                int p=0;                                           //~9401I~
                for (int ii=0;ii<PLAYERS;ii++)                     //~9401I~
                {                                                  //~9401I~
                    if (s==Pscore[ii])                              //~9401I~
                    {                                              //~9401I~
                        p+=settingOrderPrize[ctr];                 //~9401I~
                        ctr++;                                     //~9401I~
                    }                                              //~9401I~
                }                                                  //~9401I~
//                psum=p;                                          //~9407R~
                p/=ctr;                                            //~9401I~
	            premain=0;                                         //~9407I~
                if (p%100!=0)   //occurs when ctr==3               //~9407R~
                {                                                  //~9407I~
                    pcut=(p/100)*100;                              //~9407I~
//                  premain=psum-pcut*(ctr-1);                     //~9407R~
                    premain=pcut*ctr;                              //~9407I~
                    p=pcut;                                        //~9407I~
                }                                                  //~9407I~
        if (Dump.Y) Dump.println("AccountsDlg:setOrderPrize top ctr="+ctr+",premain="+premain);//~9407R~
                for (int ii=0;ii<PLAYERS;ii++)                     //~9401I~
                {                                                  //~9401I~
                    if (s==Pscore[ii])                              //~9401I~
                        orderPrize[ii]=p;                          //~9401I~
                }                                                  //~9401I~
	            if (premain!=0)                                    //~9407I~
                {                                                  //~9407I~
    	    		orderPrize[order2Idx[3]]=-premain; //3 top 1 last//~9407R~
                	break;	//top3 and last1 was set               //~9407I~
                }                                                  //~9407I~
            	if (ctr==PLAYERS)                                  //~9401I~
                	break;                                         //~9401I~
            //*2nd                                                 //~9401I~
                int ctr2=0;                                        //~9401I~
                s=Pscore[order2Idx[ctr]];                           //~9401I~
                p=0;                                               //~9401I~
                for (int ii=0;ii<PLAYERS;ii++)                     //~9401I~
                {                                                  //~9401I~
                    if (s==Pscore[ii])                              //~9401I~
                    {                                              //~9401I~
                        p+=settingOrderPrize[ctr+ctr2];            //~9401I~
                        ctr2++;                                    //~9401I~
                    }                                              //~9401I~
                }                                                  //~9401I~
//                psum=p;                                          //~9407R~
                p/=ctr2;                                           //~9401I~
	            premain=0;                                         //~9407I~
                if (p%100!=0)   //                                 //~9407I~
                {                                                  //~9407I~
                    pcut=(p/100)*100;                              //~9407I~
                    premain=pcut*ctr2;                             //~9407R~
                    p=pcut;                                        //~9407I~
                }                                                  //~9407I~
        if (Dump.Y) Dump.println("AccountsDlg:setOrderPrize 2nd ctr2="+ctr2+",premain="+premain);//~9407R~
                for (int ii=0;ii<PLAYERS;ii++)                     //~9401I~
                {                                                  //~9401I~
                    if (s==Pscore[ii])                              //~9401I~
                        orderPrize[ii]=p;                          //~9401I~
                }                                                  //~9401I~
	            if (premain!=0)                                    //~9407I~
    	    		orderPrize[order2Idx[0]]=-premain;             //~9407R~
            	if (ctr+ctr2==PLAYERS)                             //~9401I~
                	break;                                         //~9401I~
            //*3rd                                                 //~9401I~
                int ctr3=0;                                        //~9401I~
                s=Pscore[order2Idx[ctr+ctr2]];                      //~9401I~
                p=0;                                               //~9401I~
                for (int ii=0;ii<PLAYERS;ii++)                     //~9401I~
                {                                                  //~9401I~
                    if (s==Pscore[ii])                              //~9401I~
                    {                                              //~9401I~
                        p+=settingOrderPrize[ctr+ctr2+ctr3];       //~9401I~
                        ctr3++;                                    //~9401I~
                    }                                              //~9401I~
                }                                                  //~9401I~
//              psum=p;                                            //~9407R~
                p/=ctr3;                                           //~9401I~
//                premain=0;                                       //~9407R~
//                if (p%100!=0)   //                               //~9407R~
//                {                                                //~9407R~
//                    pcut=(p/100)*100;                            //~9407R~
//                    premain=psum-pcut*(ctr3-1);                  //~9407R~
//                    p=pcut;                                      //~9407R~
//                }                                                //~9407R~
//        if (Dump.Y) Dump.println("AccountsDlg:setOrderPrize 3rd ctr3="+ctr3+",premain="+premain);//~9407R~
                for (int ii=0;ii<PLAYERS;ii++)                     //~9401I~
                {                                                  //~9401I~
                    if (s==Pscore[ii])                              //~9401I~
                        orderPrize[ii]=p;                          //~9401I~
                }                                                  //~9401I~
//                if (premain!=0)                                  //~9407R~
//                    orderPrize[order2Idx[ctr+ctr2]]=premain;     //~9407R~
            	if (ctr+ctr2+ctr3==PLAYERS)                        //~9401I~
                	break;                                         //~9401I~
            //*4th                                                 //~9401I~
                int ctr4=0;                                        //~9401I~
                s=Pscore[order2Idx[ctr+ctr2+ctr3]];                 //~9401I~
                p=0;                                               //~9401I~
                for (int ii=0;ii<PLAYERS;ii++)                     //~9401I~
                {                                                  //~9401I~
                    if (s==Pscore[ii])                              //~9401I~
                    {                                              //~9401I~
                        p+=settingOrderPrize[ctr+ctr2+ctr3+ctr4];  //~9401I~
                        ctr4++;                                    //~9401I~
                    }                                              //~9401I~
                }                                                  //~9401I~
//                psum=p;                                          //~9407R~
                p/=ctr4;                                           //~9401I~
//                premain=0;                                       //~9407R~
//                if (p%100!=0)   //                               //~9407R~
//                {                                                //~9407R~
//                    pcut=(p/100)*100;                            //~9407R~
//                    premain=psum-pcut*(ctr4-1);                  //~9407R~
//                    p=pcut;                                      //~9407R~
//                }                                                //~9407R~
        if (Dump.Y) Dump.println("AccountsDlg:setOrderPrize 4th ctr4="+ctr4+",premain="+premain);//~9407R~
                for (int ii=0;ii<PLAYERS;ii++)                     //~9401I~
                {                                                  //~9401I~
                    if (s==Pscore[ii])                              //~9401I~
                        orderPrize[ii]=p;                          //~9401I~
                }                                                  //~9401I~
//                if (premain!=0)                                  //~9407R~
//                    orderPrize[order2Idx[ctr+ctr2+ctr3]]=premain;//~9407R~
                break;                                             //~9401I~
            }                                                      //~9401I~
        }                                                          //~9401I~
        if (Dump.Y) Dump.println("AccountsDlg:setOrderPrize swOrderByEswn="+swOrderByEswn);//~9401I~
        if (Dump.Y) Dump.println("AccountsDlg:setOrderPrize score="+Arrays.toString(Pscore));//~9401I~
        if (Dump.Y) Dump.println("AccountsDlg:setOrderPrize orderPrize="+Arrays.toString(orderPrize));//~9401I~
        if (Dump.Y) Dump.println("AccountsDlg:setOrderPrize settingOrderPrize="+Arrays.toString(settingOrderPrize));//~9401I~
    }                                                              //~9401I~
    //*******************************************************      //~9321I~
    @Override                                                      //~9321I~
    public void onDismissDialog()                                  //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("AccountsDlg.onDismissDialog");   //~9321I~//~9819R~
        if (!swDismissBeforNew)                                    //~9322I~
	        AG.aAccountsDlg=null;                                         //~9321I~//~9322R~
        swDismissBeforNew=false;                                   //~9322I~
    }                                                              //~9321I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void setButton()                                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("AccountsDlg.setButton swAllOK="+swAllOK);   //~9819I~//~9A31R~//~vac9R~
    	super.setButton();                                         //~9321I~
        if (swRequester)                                           //~9321I~
	        btnTotal.setEnabled(swAllOK);                          //~9321I~
        else                                                       //~9321I~
	        btnTotal.setVisibility(View.GONE);                     //~9321I~
    }                                                              //~9321I~
    //*******************************************************************//~9321I~
    //*on UiThread                                                 //~9321I~
    //*******************************************************************//~9321I~
    @Override                                                      //~9321I~
//  protected void updateOKNGAdditional(int PctrNone,int PctrNG,boolean PswAllOK)//~9321I~//~0119R~
    protected void updateOKNGAdditional(int PctrNone,int PctrNG,int PctrDisconnected,boolean PswAllOK)//~0119I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("AccountsDlg.updateOKNGAdditional ctrNG="+PctrNG+",ctrNone="+PctrNone+",swAllOK="+PswAllOK);//~9321I~//~9322R~
        btnTotal.setEnabled(PswAllOK);                             //~9321I~
//      layoutView.invalidate();                                   //~9813R~
//      if (Dump.Y) Dump.println("AccountsDlg.updateOKNGAdditional requestLayout");//~9813R~
//      layoutView.requestLayout();                                //~9813R~
        if (PctrNone==0 && PctrNG!=0) //all responsed, someone replyed NG//~9612I~
        {                                                          //~9612I~
	        if (swRequester)                                       //~9612I~
    	    	alertToForceOK(this,TITLEID,R.string.Alert_AccountsDlgForceOK);//~9612I~
        }                                                          //~9612I~
    }                                                              //~9321I~
    //*******************************************************************//~9612I~
    @Override                                                      //~9612I~
	protected void alertActionReceived(int Pbuttonid,int Prc)      //~9612I~
    {                                                              //~9612I~
        if (Dump.Y) Dump.println("AccountsDlg.alerActionReceived buttonID="+Pbuttonid+",rc="+Prc);//~9612I~
    	if (Pbuttonid==BUTTON_POSITIVE)                            //~9612I~
        {                                                          //~9612I~
	        btnTotal.setEnabled(true/*PswAllOK*/);                 //~9612I~
        }                                                          //~9612I~
    }                                                              //~9612I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void onClickOK()                                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("AccountsDlg.onClickOK swRequester="+swRequester);//~9321I~//~9322R~
        if (swRequester)                                           //~9321I~
        {                                                          //~9322I~
        	sendConfirmRequest();                                  //~9321R~
            resetRespStat();	//OKNGDlg                          //~0120I~
	        repaintOKNG();    //callback updateOKNGAdditional      //~0120I~
	        btnTotal.setEnabled(false);                            //~0120I~
        }                                                          //~9322I~
        else                                                       //~9321I~
        {                                                          //~9321I~
        	sendReply(true);                                       //~9321I~
			dismiss();                                          //~9321I~
        }                                                          //~9321I~
    }                                                              //~9321I~
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
        if (Dump.Y) Dump.println("AccountsDlg.onClickOther btnid="+Integer.toHexString(Pbuttonid));//~9321I~//~9322R~
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
        if (Dump.Y) Dump.println("AccountsDlg.onClickTotal");         //~9321I~//~9322R~
        dismiss();                                              //~9321I~
        sendEndGame(newTotal);                                     //~9321I~
    }                                                              //~9321I~
    //******************************************                   //~9417I~
    public void onClickShowRule()                                  //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("AccountsDlg.onClickShowRule");   //~9417I~
        showRule();                                                //~9417I~
    }                                                              //~9417I~
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
////      AccountsDlg.showDialog(PendgameType,Pamt,ACC.score,PnextgameType); //minus chk//~9318R~//~9321R~//~9322R~
//        AccountsDlg.newInstance(Pamt,AG.aAccounts.score).show();      //~9321R~//~9322R~
//    }                                                              //~9318I~//~9322R~
	//************************************************             //~9417I~
    private void showRule()                                        //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("AccountsDlg.showRule");          //~9417I~
        RuleSetting.showRuleInGame();                              //~9417I~
    }                                                              //~9417I~
    //**************************************************************************//~9321I~
    //*from OnClickTotal                                           //~9605I~
    //**************************************************************************//~9605I~
    public void sendEndGame(int[] Ptotal)                          //~9321I~
    {                                                              //~9321I~
    	int[][] intssP=new int[][]{Ptotal,minusPrize,minusCharge,finalScore};//~9415I~
        if (Dump.Y) Dump.println("AccountsDlg.sendEndGame total="+Arrays.toString(Ptotal));//~9525I~
    	if (!AG.aUserAction.isServer)                              //~9321I~
        {                                                          //~9321I~
    		sendToServerEndGame(Ptotal); //ENDGAME_ACCOUNTS_CONFIRMED //~9321R~//~9322R~
//  		endGame(EGDR_MINUSSTOP,Ptotal);                        //~9402I~//~9415R~
//  		endGame(EGDR_MINUSSTOP,intssP);                        //~9415I~//~9525R~
//  		endGame(endgameType,intssP);                           //~9525I~//~9826R~
        	return;                                                //~9321I~
        }                                                          //~9321I~
//  	endGame(EGDR_MINUSSTOP,Ptotal);                            //~9321I~//~9415R~
//  	endGame(EGDR_MINUSSTOP,intssP);                            //~9415I~//~9525R~
        String ts=Utils.getTimeStamp(TS_DATE_TIME2); //":" between date and time for parseInt//~9826I~
        String fnm= History.timestampToFilename(ts);  //"-"         //~9826I~
//    	endGame(endgameType,intssP);                               //~9525I~//~9826R~
      	endGame(fnm,endgameType,intssP);                           //~9826I~
//      sendToAllClientEndGame(Ptotal);                            //~9321I~//~9415R~
//      sendToAllClientEndGame(intssP);                            //~9415R~//~9525R~
//      sendToAllClientEndGame(endgameType,intssP);                //~9525I~//~9826R~
        sendToAllClientEndGame(ts,endgameType,intssP);             //~9826I~
    }                                                              //~9321I~
    //**************************************************************************//~9318I~
    //*from UserAction,GCM_ENDGAME_ACCOUNTS btio msg received                                           //~9318I~//~9320R~//~9322R~
    //**************************************************************************//~9318I~
    public static void onReceived(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~9318R~
    {                                                              //~9318I~
        int[] total,minusP,minusC,finalS;
        int[][] intssP;//~9321I~//~9415R~
        String ts;                                                 //~9826I~
        if (Dump.Y) Dump.println("AccountsDlg:onReceived swServer="+PswServer+",PswReceived="+PswReceived+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~9318R~//~9322R~
        int scoreMsgtype=PintParm[POS_SCOREMSGTYPE];               //~9320I~//~9322R~
//        int endgameType=PintParm[POS_ENDGAMETYPE];                 //~9318I~//~9322R~
//        int nextgameType=PintParm[POS_NEXTGAMETYPE];               //~9318I~//~9322R~
        if (PswServer)  //from dealer(client)                      //~9318R~
        {                                                          //~9318I~
        	switch(scoreMsgtype)                                   //~9321I~
            {                                                      //~9321I~
            case ENDGAME_ACCOUNTS_CONFIRM_REQUEST:                    //~9321R~
//          	int[] score=parseConfirmRequest(PintParm);               //~9321I~//~9322R~//~9415R~
            	intssP=parseConfirmRequest(PintParm);      //~9415I~
//  			sendToAllClientConfirm(score);                     //~9322R~//~9415R~
    			sendToAllClientConfirm(intssP);                    //~9415I~
		        if (Utils.isShowingDialogFragment(AG.aAccountsDlg))//~9322R~
                {                                                  //~9322I~
                	AG.aAccountsDlg.swDismissBeforNew=true;        //~9322R~
			        AG.aAccountsDlg.dismiss();                     //~9322R~
                }                                                  //~9322I~
//  			AccountsDlg.newInstance(score).show();//~9321R~    //~9322R~//~9415R~
    			AccountsDlg.newInstance(intssP).show();            //~9415I~
            	break;                                             //~9321I~
            case ENDGAME_ACCOUNTS_CONFIRM_REPLY:                      //~9321R~
                int replyEswn=PintParm[POS_REPLAY_ESWN];           //~9321I~//~9430I~
//				if (OKNGDlg.isDealer())                            //~9321I~//~9605R~
  				if (AG.aAccounts.isFirstDealerReal())              //~9605I~
                {                                                  //~9321I~
		            if (Utils.isShowingDialogFragment(AG.aAccountsDlg))//~9321I~//~9322R~
	            		AG.aAccountsDlg.repaintOKNG(replyEswn,PintParm[POS_OKNG]!=0);	//callback updateOKNGAdditional//~9321I~//~9322R~
                }                                                  //~9321I~
                else                                               //~9321I~
                {                                                  //~9321I~
        			String msgdata=ENDGAME_ACCOUNTS_CONFIRM_REPLY+MSG_SEPAPP2+PintParm[POS_OKNG];//~9321I~//~9322R~
//      			AG.aUserAction.sendToTheClientDealer(GCM_ENDGAME_ACCOUNTS,msgdata);//~9321I~//~9322R~//~9430R~
//      			AG.aUserAction.sendToTheClientDealerWithSourceEswn(GCM_ENDGAME_ACCOUNTS,replyEswn,msgdata);//~9430I~//~9606R~
        			AG.aUserAction.sendToTheClientDealerWithSourceEswnFirstStarter(GCM_ENDGAME_ACCOUNTS,replyEswn,msgdata);//~9606I~
        		}                                                  //~9321I~
            	break;                                             //~9321I~
            case ENDGAME_ACCOUNTS_CONFIRMED:                       //~9322I~
            	total=new int[PLAYERS];                            //~9322I~
            	minusP=new int[PLAYERS];                           //~9415I~
            	minusC=new int[PLAYERS];                           //~9415I~
            	finalS=new int[PLAYERS];                           //~9415I~
    			System.arraycopy(PintParm,POS_AMMOUNT,total,0,PLAYERS);//~9322I~
    			System.arraycopy(PintParm,POS_AMMOUNT_MINUSPAY,minusP,0,PLAYERS);//~9415I~
    			System.arraycopy(PintParm,POS_AMMOUNT_MINUSCHARGE,minusC,0,PLAYERS);//~9415I~
    			System.arraycopy(PintParm,POS_AMMOUNT_FINALSCORE,finalS,0,PLAYERS);//~9415R~
//  			endGame(EGDR_MINUSSTOP,total);                     //~9322I~//~9415R~
    			intssP=new int[][]{total,minusP,minusC,finalS};//~9415I~
//  			endGame(EGDR_MINUSSTOP,intssP);                    //~9415I~//~9525R~
        		int egtp=PintParm[POS_ENDGAMETYPE];                //~9525R~
		        ts=Utils.getTimeStamp(TS_DATE_TIME2); //":" between date and time for parseInt//~9826I~
        		String fnm=History.timestampToFilename(ts);  //"-" //~9826I~
//  			endGame(egtp,intssP);                              //~9525R~//~9826R~
    			endGame(fnm,egtp,intssP);                          //~9826I~
//  			sendToAllClientEndGame(total);                     //~9322I~//~9415R~
//  			sendToAllClientEndGame(egtp,intssP);                    //~9415R~//~9525R~//~9826R~
    			sendToAllClientEndGame(ts,egtp,intssP);            //~9826I~
            	break;                                             //~9322I~
            }                                                      //~9321I~
        }                                                          //~9318I~
        else                                                       //~9318I~
        {                                                          //~9318I~
        	switch(scoreMsgtype)                                   //~9321I~
            {                                                      //~9321I~
            case ENDGAME_ACCOUNTS_CONFIRM_REQUEST:                    //~9321I~
//  			if (!OKNGDlg.isDealer())                           //~9321I~//~9605R~
  				if (!AG.aAccounts.isFirstDealerReal())             //~9605I~
                {                                                  //~9321I~
			        if (Utils.isShowingDialogFragment(AG.aAccountsDlg))//~9322R~
    	            {                                              //~9322I~
            			AG.aAccountsDlg.swDismissBeforNew=true;    //~9322R~
			        	AG.aAccountsDlg.dismiss();                 //~9322R~
        	        }                                              //~9322I~
//          		int[] score=parseConfirmRequest(PintParm);   //~9321R~//~9322R~//~9415R~
            		intssP=parseConfirmRequest(PintParm);  //~9415I~
//  				AccountsDlg.newInstance(score).show();//~9321R~       //~9322R~//~9402R~//~9415R~
    				AccountsDlg.newInstance(intssP).show();        //~9415I~
                }                                                  //~9321I~
                else                                               //~9402I~
        			if (Dump.Y) Dump.println("AccountsDlg.onReceived dealer on client ignored CONFIRM_REQUEST from server");//~9402I~
                break;                                             //~9321I~
            case ENDGAME_ACCOUNTS_CONFIRM_REPLY:                      //~9321I~
//  			if (OKNGDlg.isDealer())                            //~9321I~//~9605R~
  				if (AG.aAccounts.isFirstDealerReal())              //~9605I~
                {                                                  //~9321I~
		        	int replyEswn=PintParm[POS_REPLAY_ESWN];       //~9321I~
		            if (Utils.isShowingDialogFragment(AG.aAccountsDlg))//~9321I~//~9322R~
	            		AG.aAccountsDlg.repaintOKNG(replyEswn,PintParm[POS_OKNG]!=0);	//callback updateOKNGAdditional//~9321I~//~9322R~
                }                                                  //~9321I~
                break;                                             //~9321I~
            case ENDGAME_ACCOUNTS_CONFIRMED:                          //~9322I~
//  			if (!OKNGDlg.isDealer())                           //~9322I~//~9605R~
//				if (!AG.aAccounts.isFirstDealerReal())             //~9605I~//~0106R~
//              {                                                  //~9322I~//~0106R~
	            	total=new int[PLAYERS];                        //~9322I~
            		minusP=new int[PLAYERS];                       //~9415I~
            		minusC=new int[PLAYERS];                       //~9415I~
            		finalS=new int[PLAYERS];                       //~9415I~
//  				System.arraycopy(PintParm,POS_AMMOUNT,total,0,PLAYERS);//~9322I~//~9826R~
//  				System.arraycopy(PintParm,POS_AMMOUNT_MINUSPAY,minusP,0,PLAYERS);//~9415I~//~9826R~
//  				System.arraycopy(PintParm,POS_AMMOUNT_MINUSCHARGE,minusC,0,PLAYERS);//~9415I~//~9826R~
//  				System.arraycopy(PintParm,POS_AMMOUNT_FINALSCORE,finalS,0,PLAYERS);//~9415I~//~9826R~
    				System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED,total,0,PLAYERS);//~9826I~
    				System.arraycopy(PintParm,POS_AMMOUNT_MINUSPAY_CONFIRMED,minusP,0,PLAYERS);//~9826I~
    				System.arraycopy(PintParm,POS_AMMOUNT_MINUSCHARGE_CONFIRMED,minusC,0,PLAYERS);//~9826I~
    				System.arraycopy(PintParm,POS_AMMOUNT_FINALSCORE_CONFIRMED,finalS,0,PLAYERS);//~9826I~
                    intssP=new int[][]{total,minusP,minusC,finalS};//~9415I~
//  				endGame(EGDR_MINUSSTOP,total);                 //~9322I~//~9415R~
//  				endGame(EGDR_MINUSSTOP,intssP);                //~9415I~//~9525R~
//  				endGame(egtp,intssP);                          //~9525R~//~9826I~
	        		int egtp=PintParm[POS_ENDGAMETYPE];            //~9525R~
					ts=History.timestampToFilename(PintParm[POS_DATE],PintParm[POS_TIME]);//~9826I~
    				endGame(ts,egtp,intssP);                       //~9826I~
//              }                                                  //~9322I~//~0106R~
                break;                                             //~9322I~
            }                                                      //~9320I~
        }                                                          //~9318I~
    }                                                              //~9318I~
    //**************************************************************************//~9321I~
    private static int[][] parseConfirmRequest(int[] PintParm)     //~9321R~//~9322R~//~9415R~
    {                                                              //~9321I~
    	int pos=POS_AMMOUNT;                                         //~9322R~
        if (Dump.Y) Dump.println("AccountsDlg:parseConfirmRequest startpos="+pos+",intp="+Arrays.toString(PintParm));//~9321I~//~9322R~
        int[] score=new int[PLAYERS];                              //~9321I~
        int[] minusP=new int[PLAYERS];                             //~9415I~
        int[] minusC=new int[PLAYERS];                             //~9415I~
        int[] finalS=new int[PLAYERS];                             //~9415R~
        System.arraycopy(PintParm,pos,score,0,PLAYERS);   pos+=PLAYERS;//~9321I~
        System.arraycopy(PintParm,pos,minusP,0,PLAYERS);   pos+=PLAYERS;//~9415I~
        System.arraycopy(PintParm,pos,minusC,0,PLAYERS);   pos+=PLAYERS;//~9415I~
        System.arraycopy(PintParm,pos,finalS,0,PLAYERS);   pos+=PLAYERS;//~9415R~
        if (Dump.Y) Dump.println("AccountsDlg:parseConfirmRequest lastScore="+Arrays.toString(score));//~9321I~//~9322R~
        if (Dump.Y) Dump.println("AccountsDlg:parseConfirmRequest minusP="+Arrays.toString(minusP));//~9415I~
        if (Dump.Y) Dump.println("AccountsDlg:parseConfirmRequest minusC="+Arrays.toString(minusC));//~9415I~
        if (Dump.Y) Dump.println("AccountsDlg:parseConfirmRequest finalS="+Arrays.toString(finalS));//~9415R~
        int[][] rc=new int[][]{score,minusP,minusC,finalS};        //~9415R~
        return rc;                                              //~9321I~//~9322R~
    }                                                              //~9321I~
    //**************************************************************************//~9321I~
//  public static void endGame(int PendgameType,int[] Ptotal/*indexSeq*/)//~9321R~//~9415R~
//  private static void endGame(int PendgameType,int[][] PintssP/*indexSeq*/)//~9415I~//~9501R~//~9826R~
    private static void endGame(String Pfnm,int PendgameType,int[][] PintssP/*indexSeq*/)//~9826I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("AccountsDlg:endGame type="+PendgameType+",fnm="+Pfnm);//~9321I~//~9322R~//~0106R~
//      AG.aAccounts.setScore(Ptotal);                             //~9321R~//~9415R~
        AG.aAccounts.setScore(PintssP[3]);	//save finalScore as Accounts.score           //~9415I~//~9823R~
//     	AG.aNamePlate.showScore();                                 //~9321I~//~9823R~
       	showScoreFinalPoint();                                     //~9823I~
//  	Complete.newInstance();                                    //~9321I~
		Status.endGame(PendgameType,NGTP_GAMEOVER);                           //~9321I~//~9401R~
//  	AG.aStarter.showGameSeq(AG.aAccounts.starterRelativePos);  //~9321I~
//    if (PendgameType==EGDR_MINUSSTOP)                            //~9525R~//~9826R~
//  	UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndMinusStop));//~9321I~//~9826R~
        AG.aStatus.gameOverScoreFixed();                           //~9612I~
    	String[] accountNames=AG.aAccounts.getAccountNamesByPosition();              //~9613I~
//      AG.aHistory.saveLatestGame(accountNames,PintssP);           //~9613I~//~9614R~//~9826R~
        AG.aHistory.saveLatestGame(Pfnm,accountNames,PintssP);     //~9826I~
//    	if (PendgameType==EGDR_MINUSSTOP)                          //~9826I~//~0224R~
//  		GMsg.drawMsgbar(R.string.Info_GameEndMinusStop);       //~9826I~//~0224R~
		UserAction.showInfo(0,R.string.Info_GameEnded);            //~0224I~
	}                                                              //~9321I~
    //*******************************************************      //~9823I~
    protected static void showScoreFinalPoint()                           //~9823R~
    {                                                              //~9823I~
    	int[] fs=AG.aAccounts.score;                               //~9823I~
    	String[] fp=AG.aAccounts.finalPoint1000;                      //~9823I~
        if (Dump.Y) Dump.println("AccountsDlg:showScoreFinalPoint finalPoint="+Arrays.toString(fs)+",finalPoint1000="+Arrays.toString(fp));//~9823R~
		AG.aNamePlate.showScoreFinalPoint(fp,fs);                  //~9823R~
    }                                                              //~9823I~
    //*******************************************************      //~9321I~
    //*on requester                                                //~9321I~
    //*******************************************************      //~9321I~
    public void sendConfirmRequest()                               //~9321R~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("AccountsDlg:sendConfirmRequest");   //~9321I~//~9322R~
        if (isServer)                                              //~9321I~
//  		sendToAllClientConfirm(ammount,lastScore,minusPay,newTotal,payerInfo);//~9321R~//~9322R~
//  		sendToAllClientConfirm(lastScore);                     //~9322R~//~9415R~
    		sendToAllClientConfirm(new int[][]{lastScore,minusPrize,minusCharge,finalScore});//~9415R~
        else                                                       //~9321I~
    		sendToServerConfirm();                                  //~9321I~
    }                                                              //~9321I~
    //*******************************************************************//~9321I~
    protected void sendReply(boolean PswOK)                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("AccountsDlg.sendReply OK="+PswOK);  //~9321R~//~9322R~
//        CMP.setResultOK(requesterEswn,currentEswn,PswOK);        //~9321R~
//        String msg=requesterEswn+MSG_SEPAPP+currentEswn+MSG_SEPAPP+(PswOK ? "1" : "0");//~9321R~
//        ACC.sendToAll(GCM_COMPRESULT_RESP,msg);                  //~9321R~
        String okng=(PswOK ? "1" : "0");                            //~9321I~
		if (!isServer)                                             //~9321R~
	        AG.aUserAction.sendToServer(GCM_ENDGAME_ACCOUNTS,PLAYER_YOU,ENDGAME_ACCOUNTS_CONFIRM_REPLY,okng);//~9321R~//~9322R~
		else                                                       //~9321R~
        {                                                          //~9321I~
        	String msgdata=ENDGAME_ACCOUNTS_CONFIRM_REPLY+MSG_SEPAPP2+okng;//~9321R~//~9322R~
//      	AG.aUserAction.sendToTheClientDealer(GCM_ENDGAME_ACCOUNTS,msgdata);//~9321I~//~9322R~//~9606R~
        	AG.aUserAction.sendToTheClientDealerFirstStarter(GCM_ENDGAME_ACCOUNTS,msgdata);//~9606I~
        }                                                          //~9321I~
    }                                                              //~9321I~
//    //**************************************************************************//~9318I~//~9322R~
//    private static void sendToServer(int PendgameType,int PnextgameType,int[] Pamt)//~9318I~//~9321R~//~9322R~
//    {                                                              //~9318I~//~9322R~
//        String strAmt=Pamt[0]+MSG_SEPAPP+Pamt[1]+MSG_SEPAPP+Pamt[2]+MSG_SEPAPP+Pamt[3];//~9318I~//~9322R~
//        AG.aUserAction.sendToServer(GCM_ENDGAME_ACCOUNTS,PLAYER_YOU,ENDGAME_ACCOUNTS,PendgameType,PnextgameType,strAmt);//~9318I~//~9322R~
//    }                                                              //~9318I~//~9322R~
    //**************************************************************************//~9321I~
    private void sendToServerConfirm()                             //~9321R~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("AccountsDlg.sendToServerConfirm");  //~9321I~//~9322R~
//  	String msgdata=makeReqMsg(lastScore);                      //~9322R~//~9415R~
//  	String msgdata=makeReqMsg(new int[][]{lastScore,minusPrize});//~9415R~
    	String msgdata=makeReqMsg(new int[][]{lastScore,minusPrize,minusCharge,finalScore});//~9415R~
        AG.aUserAction.sendToServer(GCM_ENDGAME_ACCOUNTS,PLAYER_YOU,ENDGAME_ACCOUNTS_CONFIRM_REQUEST,msgdata);//~9321R~//~9322R~
    }                                                              //~9321I~
    //**************************************************************************//~9321I~
    private void sendToServerEndGame(int[] Ptotal)                 //~9321R~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("AccountsDlg.sendToServerEndGame");  //~9321I~//~9322R~
//      String msg=Ptotal[0]+MSG_SEPAPP+Ptotal[1]+MSG_SEPAPP+Ptotal[2]+MSG_SEPAPP+Ptotal[3];//~9321I~//~9415R~
//  	String msg=makeReqMsg(new int[][]{Ptotal,minusPrize,minusCharge,finalScore});//~9415R~//~9525R~
    	String msg=makeReqMsg(new int[][]{Ptotal,minusPrize,minusCharge,finalScore})+MSG_SEPAPP2+endgameType;//~9525R~
        AG.aUserAction.sendToServer(GCM_ENDGAME_ACCOUNTS,PLAYER_YOU,ENDGAME_ACCOUNTS_CONFIRMED,msg);//~9321I~//~9322R~
    }                                                              //~9321I~
//    //**************************************************************************//~9318I~//~9322R~
//    private static void sendToAllClientUpdate(int PendgameType,int PnextgameType,int[] Pamt)//~9318R~//~9321R~//~9322R~
//    {                                                              //~9318I~//~9322R~
//        String strAmt=Pamt[0]+MSG_SEPAPP+Pamt[1]+MSG_SEPAPP+Pamt[2]+MSG_SEPAPP+Pamt[3];//~9318I~//~9322R~
//        String msgdata=PendgameType+MSG_SEPAPP2+PnextgameType+MSG_SEPAPP2+strAmt;//~9321I~//~9322R~
//        if (Dump.Y) Dump.println("AccountsDlg:sendToAllClientUpdate msgdata="+msgdata);//~9321I~//~9322R~
//        sendToAllClient(ENDGAME_ACCOUNTS_UPDATE,msgdata);             //~9321I~//~9322R~
//    }                                                              //~9318I~//~9322R~
    //**************************************************************************//~9321I~
//  private static void sendToAllClientEndGame(int[] Ptotal)       //~9321I~//~9415R~
//  private static void sendToAllClientEndGame(int[][] PintssP)    //~9415I~//~9525R~
//  private static void sendToAllClientEndGame(int PendgameType,int[][] PintssP)//~9525I~//~9826R~
    private static void sendToAllClientEndGame(String Ptimestamp,int PendgameType,int[][] PintssP)//~9826I~
    {                                                              //~9321I~
//      String msg=Ptotal[0]+MSG_SEPAPP+Ptotal[1]+MSG_SEPAPP+Ptotal[2]+MSG_SEPAPP+Ptotal[3];//~9321I~//~9415R~
//  	String msg=makeReqMsg(PintssP);                            //~9415R~//~9525R~
//  	String msg=makeReqMsg(PintssP)+MSG_SEPAPP2+PendgameType;   //~9525R~//~9826R~
//  	String msg=Ptimestamp+MSG_SEPAPP+makeReqMsg(PintssP)+MSG_SEPAPP2+PendgameType;//~9826I~//~0106R~
    	String msg=makeReqMsg(PintssP)+MSG_SEPAPP2+PendgameType+MSG_SEPAPP2+Ptimestamp;//~0106I~
        if (Dump.Y) Dump.println("AccountsDlg:sendToAllClientEndGame msgdata="+msg);//~9321I~//~9322R~//~9525R~
    	sendToAllClient(ENDGAME_ACCOUNTS_CONFIRMED,msg);             //~9321I~
    }                                                              //~9321I~
    //**************************************************************************//~9321I~
//  private static void sendToAllClientConfirm(int[] Pscore)       //~9322R~//~9415R~
    private static void sendToAllClientConfirm(int[][] PintssP)    //~9415I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("AccountsDlg:sendToAllClientConfirm");//~9321I~//~9322R~
//  	String msg=makeReqMsg(Pscore);            //~9321I~        //~9322R~//~9415R~
    	String msg=makeReqMsg(PintssP);                            //~9415I~
    	sendToAllClient(ENDGAME_ACCOUNTS_CONFIRM_REQUEST,msg); //~9321I~
    }                                                              //~9321I~
    //**************************************************************************//~9321I~
    private static void sendToAllClient(int PendgameMsgid,String Pmsgdata)//~9321I~
    {                                                              //~9321I~
    	int eswn=Accounts.playerToEswn(PLAYER_YOU);            //~9321I~
        String msgdata=eswn+MSG_SEPAPP2+PendgameMsgid+MSG_SEPAPP2+Pmsgdata;//~9321I~//~9322R~//~9402R~
//      String msgdata=eswn+MSG_SEPAPP2+Pmsgdata;                  //~9322I~//~9402R~
        if (Dump.Y) Dump.println("AccountsDlg:sendToAllClient msgdata="+msgdata);//~9321I~//~9322R~
        AG.aUserAction.sendToClient(true/*swAll*/,false/*swRobot*/,GCM_ENDGAME_ACCOUNTS,PLAYER_YOU,msgdata);//~9321I~//~9322R~
    }                                                              //~9321I~
    //**************************************************************************//~9318I~
//    private static void sendToClientDealer(int PscoreMsgtype,int PendgameType,int PnextgameType,int[] Pamt,int[] Pscore)//~9318I~//~9320R~//~9321R~//~9430R~
//    {                                                              //~9318I~//~9430R~
//        if (Dump.Y) Dump.println("AccountsDlg:sendToClientDealer endgame="+PendgameType+",nextgame="+PnextgameType+",amt="+Arrays.toString(Pamt)+",score="+Arrays.toString(Pscore));//~9320I~//~9321R~//~9322R~//~9430R~
//        String strAmt=Pamt[0]+MSG_SEPAPP+Pamt[1]+MSG_SEPAPP+Pamt[2]+MSG_SEPAPP+Pamt[3]+MSG_SEPAPP2//~9321R~//~9430R~
//                     +Pscore[0]+MSG_SEPAPP+Pscore[1]+MSG_SEPAPP+Pscore[2]+MSG_SEPAPP+Pscore[3];//~9321I~//~9430R~
//        String msgdata=PscoreMsgtype+MSG_SEPAPP2+PendgameType+MSG_SEPAPP2+PnextgameType+MSG_SEPAPP2+strAmt;//~9318I~//~9320R~//~9430R~
//        AG.aUserAction.sendToTheClientDealer(GCM_ENDGAME_ACCOUNTS,msgdata);       //~9318I~//~9322R~//~9430R~
//    }                                                              //~9318I~//~9430R~
    //*******************************************************      //~9322I~
//  private static String makeReqMsg(int[] Pscore)                  //~9322I~//~9415R~
    public static String makeReqMsg(int[][] PintssP)               //~9415R~
    {                                                              //~9322I~
        StringBuffer sb=new StringBuffer();                        //~9322I~
//        int[] score=PintssP[0];                                  //~9415R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~9322I~//~9415R~
////          sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Pscore[ii]);     //eswn seq//~9322I~//~9415R~
//            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+score[ii]);     //eswn seq//~9415R~
//        int[] minusP=PintssP[1];                                 //~9415R~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~9415R~
//            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+minusP[ii]);     //eswn seq//~9415R~
		for (int jj=0;jj<PintssP.length;jj++)                       //~9415I~
        {                                                          //~9415I~
	        for (int ii=0;ii<PLAYERS;ii++)                         //~9415I~
    	        sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+PintssP[jj][ii]);     //eswn seq//~9415I~
        }                                                          //~9415I~
        String s=sb.toString();                                    //~9322I~
        if (Dump.Y) Dump.println("AccountsDlg.makeReqMsg msg="+s); //~9322I~
        return s;                                                  //~9322I~
    }                                                              //~9322I~
    //**************************************************************************//~9322I~
    //*from GC by btn click                                        //~9322I~
    //**************************************************************************//~9322I~
    public static boolean showDismissed()                             //~9322I~//~9904R~
    {                                                              //~9322I~
        if (Dump.Y) Dump.println("AccountsDlg:showDismisswd");     //~9322R~
        if (Status.isGameOverScoreFixed())                     //~9612I~
        {                                                          //~9612I~
        	UView.showToast(R.string.Err_AlreadyGameOver);         //~9612I~
//      	return false;                                                //~9612I~//~9904R~//~0106R~
        }                                                          //~9612I~
		if (Utils.isShowingDialogFragment(AG.aAccountsDlg))        //~9322R~
        {                                                          //~9322I~
	        if (Dump.Y) Dump.println("AccountsDlg:showDismisswd already shown");//~9322R~
        	return false;                                                //~9322I~//~9904R~
        }                                                          //~9322I~
        Complete cmp=AG.aComplete;                                 //~9322R~//~9402M~
        if (cmp.lastScore==null)                   //~9322I~        //~9402R~//~9415R~
        {                                                          //~9322I~
        	UView.showToast(R.string.Err_ScoreReviewNoEndData);       //~9322I~//~9402R~
        	return false;                                                //~9322I~//~9904R~
        }                                                          //~9322I~
//      AccountsDlg.newInstance(cmp.lastScore).show();//~9322R~    //~9415R~
        AccountsDlg.newInstance(new int[][]{cmp.lastScore,cmp.minusPrize,cmp.minusCharge}).show();//~9415R~//~9416R~
        return true;                                               //~9904I~
    }                                                              //~9322I~
}//class                                                           //~v@@@R~
