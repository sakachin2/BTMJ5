//*CID://+bac5R~:                             update#= 1256;       //+bac5R~
//*****************************************************************//~v101I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//+bac5I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~9305I~
import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.History;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.gui.UCheckBox;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.utils.Alert.*;

public class SuspendIOErrDlg  extends SuspendDlg                   //~v@01R~
            implements UCheckBox.UCheckBoxI                        //~v@01I~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.suspenddlgioe;              //~9312R~//~9322R~//~9819R~//~v@01R~
    private static final int LAYOUTID_SMALLFONT=R.layout.suspenddlgioe_theme;//+bac5I~
    private static final int TITLEID=R.string.Title_SuspendIOErrDlg;//~9307I~//~9312R~//~9322R~//~9818R~//~9822R~//~v@01R~
    private static final int TITLEID_RESP=R.string.Title_SuspendIOErrDlgResp;//~v@01I~
    private static final String HELPFILE="SuspendIOErrDlg";                //~9220I~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9818R~//~v@01R~
                                                                   //~v@01I~
    public  static final int SUSPENDIOERR_PENALTY_PAO=8000*3;      //~v@01I~
    private static final int UCBP_PAO=6;                           //~v@01I~
    private static final int UCBP_SUSPENDER=7;// 7-->10 (7+wind seq)//~v@01I~
                                                                   //~9322I~
//  private int suspendOption,suspendPenalty;                        //~9820I~//~v@01R~
//  private URadioGroup rgSuspend;                                 //~9820I~//~v@01R~
//  private TextView[] tvsReachPoint,tvsPenalty;               //~9820I~//~9821R~//~v@01R~
//  private int[] ctrReach;  //position seq                        //~9820R~//~v@01R~
//  private int[] newScore,lastScore;  //position seq              //~v@01R~
//  private boolean[] swsSuspend;			//wind seq                                    //~9820I~//~9822R~//~v@01R~
//  private UCheckBox[] cbsSuspendPlayer;                          //~9820I~//~v@01R~
//  private int[] intsReachPoint,intsPenalty;	//pos seq                      //~9821R~//~9822R~//~v@01R~
    private int suspendPenaltyIOErr;                               //~v@01I~
    private boolean swDismissBeforNew;                             //~v@01I~
    private boolean swServer;                                      //~v@01I~
    private TextView tvPenaltyIOErr;                               //~v@01I~
    private UCheckBox cbPenaltyPao;                                //~v@01R~
    private boolean suspendOptionPao;                              //~v@01I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public SuspendIOErrDlg()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9818R~//~v@01R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.defaultConstructor"); //~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~//~9322R~//~9818R~//~v@01R~
        Status.setSuspendGame(true);                               //~v@01I~
        AG.aSuspendIOErrDlg=this;                                         //~9321I~//~9322R~//~9818R~//~v@01R~
    }                                                              //~v@@@R~
    //******************************************                   //~9818I~
    //On Server                                                    //~9829I~
    //*from SuspendIOErrReqDlg                                     //~v@01R~
    //******************************************                   //~9820I~
    public static SuspendIOErrDlg newInstance()                         //~9818I~//~9823R~//~v@01R~
    {                                                              //~9818I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.newInstance");        //~9818I~//~v@01R~
    	SuspendIOErrDlg dlg=new SuspendIOErrDlg();                           //~9818I~//~v@01R~
//    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9818I~//+bac5R~
      	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//+bac5I~
    			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_CLOSEBTN|FLAG_HELPBTN|FLAG_RULEBTN,//~9818I~
				TITLEID,HELPFILE);                                 //~9818I~
        dlg.lastScore=AG.aAccounts.score;                          //~9818I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.newInstance score="+Arrays.toString(dlg.lastScore));//~9818I~//~v@01R~
        dlg.minusPrize=new int[PLAYERS];                           //~9818I~
        dlg.minusCharge=new int[PLAYERS];                          //~9818I~
        return dlg;                                                //~9818I~
    }                                                              //~9818I~
    //******************************************                   //~v@01I~
    public static void newInstance_Show()                          //~v@01I~
    {                                                              //~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.newInstance_Show");//~v@01I~
		String devnm=AG.aBTMulti.chkReconnectingEnv();             //~v@01I~
		if (devnm!=null)                                           //~v@01I~
        {                                                          //~v@01I~
	        Alert.showMessage(R.string.StartGame,Utils.getStr(R.string.Err_ReconnectingExistSuspendGame,devnm));//~v@01I~
            return;                                                //~v@01I~
        }                                                          //~v@01I~
	    SuspendIOErrDlg.newInstance().show();                      //~v@01I~
    }                                                              //~v@01I~
    //******************************************                   //~v@@@R~
    //*when received                                               //~v@01I~
    //******************************************                   //~v@01I~
    public static SuspendIOErrDlg newInstance(int[][] PintssP)         //~9415I~//~9818R~//~v@01R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.newInstance PintssP="+Utils.toString(PintssP));//~9822R~//~v@01R~
    	SuspendIOErrDlg dlg=newInstance();                              //~9818R~//~v@01R~
        dlg.swReceived=true;                                       //~9822R~
        dlg.suspendOption=PintssP[0][0];                           //~9822I~
        dlg.suspendOptionPao=PintssP[0][1]!=0;                        //~v@01I~
        dlg.lastScore=PintssP[1]; //point at last game(not total score)//~9822I~
        dlg.ctrReach=PintssP[2]; //point at last game(not total score)//~9822I~
        dlg.swsSuspend=new boolean[PLAYERS];                       //~9822I~
        Utils.boolean_int(false/*Pswb2i*/,dlg.swsSuspend,PintssP[3]);//~9822R~
        dlg.finalScore=PintssP[4];                                 //~9822I~//~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.newInstance suspendOption="+dlg.suspendOption);//~9822I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.newInstance lastScore="+Arrays.toString(dlg.lastScore));//~9822I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.newInstance ctrReach="+Arrays.toString(dlg.ctrReach));//~9822I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.newInstance swsSuspend="+Arrays.toString(dlg.swsSuspend));//~9822I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.newInstance finalScore="+Arrays.toString(dlg.finalScore));//~9822I~//~v@01R~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
//    //*********************************************************    //~9314I~//~9822I~//~v@01R~
//    //*from OKNGDlg.initLayout.setupValueOKNG,return okngResponse dealer//~9822I~//~v@01R~
//    //*********************************************************    //~9822I~//~v@01R~
//    protected int getEswnRequester(int PcurrentEswn)               //~9314I~//~9822I~//~v@01R~
//    {                                                              //~9314I~//~9822I~//~v@01R~
//        int rc=AG.aAccounts.getCurrentServerEswn();                //~9822R~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg.getEswnRequester rc="+rc);//~9314I~//~9822I~//~v@01R~
//        return rc;                                                 //~9314I~//~9822I~//~v@01R~
//    }                                                              //~9314I~//~9822I~//~v@01R~
    //******************************************                 //~9303R~//~9312R~//~9819R~//~9822R~
    @Override                                                    //~9303R~//~9305R~//~9819R~//~9822R~
    protected void initLayoutAdditional(View PView)                            //~v@@@I~//~9303R~//~9305R~//~9321R~//~9819R~//~9822R~
    {                                                              //~v@@@M~//~9303R~//~9305R~//~9819R~//~9822R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9303R~//~9304R~//~9305R~//~9307R~//~9312R~//~9322R~//~9818R~//~9819R~//~9822R~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg:initLayout score="+Arrays.toString(ACC.score));//no minus status//~9320I~//~9322R~//~9818R~//~9819R~//~9822R~//~v@01R~
		swServer=AG.aAccounts.isServer();                          //~v@01I~
//        swRequester=!swReceived;                                   //~9822I~//~v@01R~
//        endgameType=EGDR_NORMAL;                                   //~9823R~//~v@01R~
//        swInitLayout=true;                                         //~9322I~//~9819R~//~9822R~//~v@01R~
//                                                                   //~9822I~//~v@01R~
//        initLayoutAdditionalSuspend(PView);                        //~9821R~//~9822M~//~v@01R~
//                                                                   //~9822I~//~v@01R~
//        getRuleSetting();                                          //~9320I~//~9819R~//~9822R~//~v@01R~
//        showRuleSetting(PView);                                    //~9401I~//~9819R~//~9822R~//~v@01R~
//        setupAmmount(PView);                                       //~9312I~//~9819R~//~9822R~//~v@01R~
//        setAccountName();                                          //~9309I~//~9819R~//~9822R~//~v@01R~
//        showAmmount();                                             //~9309I~//~9819R~//~9822R~//~v@01R~
//        swInitLayout=false;                                        //~9322I~//~9819R~//~9822R~//~v@01R~
//        saveLast();                                                //~9322M~//~9819R~//~9822R~//~v@01R~
	    AG.aUARestart.resetIOExceptionInGaming();	//activate send by OK button//~v@01I~
		super.initLayoutAdditional(PView);                         //~v@01I~
        hideResponseEswn();                                        //~v@01I~
    }                                                              //~v@@@M~//~9303R~//~9305R~//~9819R~//~9822R~
    //******************************************                   //~v@01I~
    private void hideResponseEswn()                                //~v@01I~
    {                                                              //~v@01I~
    	if (Dump.Y) Dump.println("SuspendIOErrDlg.hideResponseEswn");//~v@01I~
//      hideResponseEswn(!swServer);    //non server requres ctrDisconnected by updateOKNG process//~0219R~
    	llEswnResponse.setVisibility(View.GONE);	//hide view only//~0219I~
    }                                                              //~v@01I~
    //******************************************                   //~9821I~
    @Override                                                      //~v@01I~
    protected void initLayoutAdditionalSuspend(View PView)         //~9821I~
    {                                                              //~9821I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.initLayoutAdditionalSuspend");//~9821I~//~v@01R~
        tvPenaltyIOErr=(TextView)    UView.findViewById(PView,R.id.tvPenaltyIOErr);//~v@01I~
    	cbPenaltyPao=new UCheckBox(PView,R.id.cbPenaltyPao);       //~v@01I~
        if (swReceived)                                            //~v@01I~
        	cbPenaltyPao.setState(suspendOptionPao,true/*fix*/);   //~v@01R~
        else                                                       //~v@01I~
	        cbPenaltyPao.setListener(this,UCBP_PAO);               //~v@01R~
        setSuspender();                                            //~v@01I~
		super.initLayoutAdditionalSuspend(PView);                  //~v@01I~
    }                                                              //~9821I~
//    //******************************************                   //~9819I~//~v@01R~
//    @Override                                                      //~9819I~//~v@01R~
//    protected void saveLast()                                      //~9819R~//~v@01R~
//    {                                                              //~9819I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg.saveLast Nop");       //~9819I~//~v@01R~
//    }                                                              //~9819I~//~v@01R~
    //******************************************                   //~v@01I~
    private void setSuspender()                                    //~v@01I~
    {                                                              //~v@01I~
    	if (swReceived)                                            //~v@01I~
        	return;                                                //~v@01I~
    	boolean[] swsSuspendByPos=Status.getSuspendByIOErr();	//posistion seq//~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.setSuspender byIOErr="+Arrays.toString(swsSuspendByPos));//~v@01I~
        boolean[] swsSuspendByWind=new boolean[PLAYERS];             //~v@01I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@01I~
        {                                                          //~v@01I~
            int eswn=AG.aAccounts.getCurrentEswnByPosition(ii);    //~v@01I~
            swsSuspendByWind[eswn]=swsSuspendByPos[ii];            //~v@01I~
        }                                                          //~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.setSuspender byWind="+Arrays.toString(swsSuspendByWind));//~v@01I~
		swsSuspend=swsSuspendByWind;			//wind seq         //~v@01I~
    }                                                              //~v@01I~
    //******************************************                   //~v@@@I~//~9220R~//~9303R~//~9306R~
    @Override                                                      //~9819I~
    protected void setTitle()                                        //~v@@@I~//~9220R~//~9303R~//~9306R~
    {                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
        Spanned s=Status.getSpannedGameTitle(Utils.getStr(swReceived?TITLEID_RESP:TITLEID));//~9306I~//~9822R~
        androidDlg.setTitle(s);                                    //~v@@@R~//~9220R~//~9302R~//~9303R~//~9306R~
    }                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
    //******************************************                   //~9320I~//~9819R~//~9822I~//~v@01R~
    @Override                                                      //~9822I~//~v@01R~
    protected void getRuleSetting()                                  //~9320I~//~9819R~//~9822R~//~v@01R~
    {                                                              //~9320I~//~9819R~//~9822R~//~v@01R~
        super.getRuleSetting();                                    //~v@01I~
        suspendPenaltyIOErr=RuleSettingOperation.getSuspendPenaltyIOErr();    //~9820I~//~9822M~//~v@01R~
        suspendPenalty=suspendPenaltyIOErr;                        //~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.getRuleSetting penaltyIOErr="+suspendPenaltyIOErr);//~v@01I~
    }                                                              //~9320I~//~9819R~//~9822R~//~v@01R~
//    //******************************************                   //~9822I~//~v@01R~
//    @Override                                                      //~9822I~//~v@01R~
//    protected void showRuleSetting(View PView)                     //~9822I~//~v@01R~
//    {                                                              //~9822I~//~v@01R~
//        TextView tv;                                               //~9822I~//~v@01R~
//        String s;                                                  //~9822I~//~v@01R~
//        tv=(TextView)    UView.findViewById(PView,R.id.SettingTopPrize);//~9822I~//~v@01R~
//        s=AG.resource.getString(R.string.Label_AccountsSettingTopPrizeEdit,(debt-initialScore)*PLAYERS,initialScore,debt);//~9822I~//~v@01R~
//        if (Dump.Y) Dump.println("AccountsDlg.showRuleSetting topprize="+s);//~9822I~//~v@01R~
//        tv.setText(s);                                             //~9822I~//~v@01R~
//        RuleSetting.setOrderPrize(PView,true/*swFixed*/);          //~9822I~//~v@01R~
//        RuleSetting.setScoreToPoint(PView,true);                   //~9822I~//~v@01R~
//        RuleSettingOperation.setSuspendOption(PView,true/*swFixed*/);//~9820R~//~9822R~//~v@01R~
//        RuleSetting.setRobotOption(PView,true/*swFixed*/);         //~9823I~//~v@01R~
//    }                                                              //~9822I~//~v@01R~
//    //******************************************                   //~9309I~//~9819R~//~9821R~//~v@01R~
//    @Override                                                      //~9821I~//~v@01R~
//    protected void setupAmmount(View PView)                        //~9309I~//~9819R~//~9821R~//~v@01R~
//    {                                                              //~9309I~//~9819R~//~9821R~//~v@01R~
//        LinearLayout[] lls=new LinearLayout[PLAYERS];              //~9321I~//~9819R~//~9821R~//~v@01R~
//        lls[0]=(LinearLayout)    UView.findViewById(PView,R.id.llResult1);//~9309I~//~9321R~//~9322R~//~9819R~//~9821R~//~v@01R~
//        lls[1]=(LinearLayout)    UView.findViewById(PView,R.id.llResult2);//~9321I~//~9322R~//~9819R~//~9821R~//~v@01R~
//        lls[2]=(LinearLayout)    UView.findViewById(PView,R.id.llResult3);//~9321I~//~9322R~//~9819R~//~9821R~//~v@01R~
//        lls[3]=(LinearLayout)    UView.findViewById(PView,R.id.llResult4);//~9321I~//~9322R~//~9819R~//~9821R~//~v@01R~
//        tvsOrder=new TextView[PLAYERS];                             //~9321I~//~9322R~//~9819R~//~9821R~//~v@01R~
//        tvsName=new TextView[PLAYERS];                             //~9321I~//~9819R~//~9821R~//~v@01R~
//        tvsEswn=new TextView[PLAYERS];                             //~9401I~//~9819R~//~9821R~//~v@01R~
//        tvsScore=new TextView[PLAYERS];                            //~9321I~//~9819R~//~9821R~//~v@01R~
//        tvsTopPrize=new TextView[PLAYERS];                              //~9321I~//~9322R~//~9819R~//~9821R~//~v@01R~
//        tvsOrderPrize=new TextView[PLAYERS];                       //~9322I~//~9819R~//~9821R~//~v@01R~
//        tvsFinalScore=new TextView[PLAYERS];                       //~9415I~//~9819R~//~9821R~//~v@01R~
//        tvsFinalPoint=new TextView[PLAYERS];                       //~9416I~//~9819R~//~9821R~//~v@01R~
//        tvsBotCharge=new TextView[PLAYERS];                        //~9429I~//~9819R~//~9821R~//~v@01R~
//        tvLabelBotCharge=(TextView)     UView.findViewById(PView,R.id.Label_ForBotCharge);//~v@01R~
//        llsBotCharge=new LinearLayout[PLAYERS];                    //~9429I~//~9819R~//~9821R~//~v@01R~
//        tvsReachPoint=new TextView[PLAYERS];                       //~9821I~//~v@01R~
//        tvsPenalty=new TextView[PLAYERS];                          //~9820I~//~9821M~//~v@01R~
//        for (int ii=0;ii<PLAYERS;ii++)                                 //~9321I~//~9819R~//~9821R~//~v@01R~
//        {                                                          //~9321I~//~9819R~//~9821R~//~v@01R~
//            LinearLayout ll=lls[ii];                               //~9321I~//~9819R~//~9821R~//~v@01R~
//            tvsOrder[ii]=(TextView)    UView.findViewById(ll,R.id.Order);//~9321I~//~9322R~//~9819R~//~9821R~//~v@01R~
//            tvsEswn[ii]=(TextView)    UView.findViewById(ll,R.id.StartingEswn);//~9401I~//~9819R~//~9821R~//~v@01R~
//            tvsName[ii]=(TextView)    UView.findViewById(ll,R.id.memberName);//~9321I~//~9819R~//~9821R~//~v@01R~
//            tvsScore[ii]=(TextView)     UView.findViewById(ll,R.id.Score);//~9321I~//~9322R~//~9819R~//~9821R~//~v@01R~
//            tvsTopPrize[ii]=(TextView)     UView.findViewById(ll,R.id.TopPrize);//~9321I~//~9322R~//~9819R~//~9821R~//~v@01R~
//            tvsOrderPrize[ii]=(TextView)     UView.findViewById(ll,R.id.OrderPrize);//~9321I~//~9322R~//~9819R~//~9821R~//~v@01R~
//            tvsFinalScore[ii]=(TextView)     UView.findViewById(ll,R.id.finalScore);//~9415I~//~9819R~//~9821R~//~v@01R~
//            tvsFinalPoint[ii]=(TextView)     UView.findViewById(ll,R.id.finalPoint);//~9416I~//~9819R~//~9821R~//~v@01R~
//            llsBotCharge[ii]=(LinearLayout)     UView.findViewById(ll,R.id.llForBotCharge);//~9429I~//~9819R~//~9821R~//~v@01R~
//            tvsBotCharge[ii]=(TextView)     UView.findViewById(ll,R.id.tvForBot);//~9429I~//~9819R~//~9821R~//~v@01R~
//            tvsReachPoint[ii]=(TextView)    UView.findViewById(ll,R.id.tvBackPointReach);//~9821R~//~v@01R~
//            tvsPenalty[ii]=(TextView)       UView.findViewById(ll,R.id.tvSuspendPenalty);//~9820I~//~9821M~//~v@01R~
//        }                                                          //~9321I~//~9819R~//~9821R~//~v@01R~
//    }                                                              //~9309I~//~9819R~//~9821R~//~v@01R~
    //******************************************                   //~9820I~//~v@01R~
    @Override                                                      //~v@01R~
    protected void setupSuspendPlayer(View PView)                              //~9820I~//~v@01R~
    {                                                              //~9820I~//~v@01R~
        UCheckBox cbE=new UCheckBox(PView,R.id.cbAbPE);            //~9309I~//~9820I~//~v@01R~
        UCheckBox cbS=new UCheckBox(PView,R.id.cbAbPS);            //~9309I~//~9820I~//~v@01R~
        UCheckBox cbW=new UCheckBox(PView,R.id.cbAbPW);            //~9309I~//~9820I~//~v@01R~
        UCheckBox cbN=new UCheckBox(PView,R.id.cbAbPN);            //~9309I~//~9820I~//~v@01R~
        cbsSuspendPlayer=new UCheckBox[]{cbE,cbS,cbW,cbN};          //~9309I~//~9820I~//~v@01R~
        for (int ii=0;ii<PLAYERS;ii++)                                 //~9820I~//~v@01R~
        {                                                          //~v@01R~
          if (swReceived)                                          //~v@01I~
	        cbsSuspendPlayer[ii].setState(swsSuspend[ii],true/*swFixed*/);//~v@01I~
          else                                                     //~v@01R~
          {                                                        //~v@01R~
	        cbsSuspendPlayer[ii].setState(swsSuspend[ii],false/*swFixed*/);//~v@01I~
            cbsSuspendPlayer[ii].setListener(this,UCBP_SUSPENDER+ii);//~v@01R~
          }                                                        //~v@01R~
        }                                                          //~v@01R~
    }                                                              //~9820I~//~v@01R~
//    //******************************************                   //~9309I~//~9819R~//~9821R~//~v@01I~
//    @Override                                                      //~9821I~//~v@01R~
//    protected void showAmmount()                                     //~9309I~//~9819R~//~9821R~//~v@01R~
//    {                                                              //~9309I~//~9819R~//~9821R~//~v@01R~
//        newScore=lastScore.clone();                              //~v@01R~
//        showAmmountSuspend(newScore);   //update lastscore by backpointreach//~v@01R~
//                                                                   //~9821I~//~v@01R~
//        getOrder(newScore);                                      //~v@01R~
//        setPrize(newScore);                                      //~v@01R~
//        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9309I~//~9819R~//~9821R~//~v@01R~
//        {                                                          //~9309I~//~9819R~//~9821R~//~v@01R~
//            int eswn=ii;    //account lsit is position order       //~9416I~//~9819R~//~9821R~//~v@01R~
//            int wind=accountEswn[ii];                              //~9822I~//~v@01R~
//            tvsEswn[eswn].setText(GConst.nameESWN[wind]); //~9312I~  //~9320R~//~9401R~//~9822I~//~v@01R~
//            tvsOrder[eswn].setText(Integer.toString(idx2Order[ii]+1));//~9401R~//~9819R~//~9821R~//~v@01R~
//            tvsScore[eswn].setText(Integer.toString(newScore[ii]));//~v@01R~
//            tvsTopPrize[eswn].setText(Integer.toString(topPrize[ii]));//~9401R~//~9819R~//~9821R~//~v@01R~
//            tvsOrderPrize[eswn].setText(Integer.toString(orderPrize[ii]));//~9401R~//~9819R~//~9821R~//~v@01R~
//            newTotal[ii]=newScore[ii]+topPrize[ii]+orderPrize[ii];//~v@01R~
//            finalScore[ii]=newTotal[ii]+intsPenalty[ii];           //~9821I~//~v@01R~
//            tvsFinalScore[eswn].setText(Integer.toString(finalScore[ii]));//~9415I~//~9819R~//~9821R~//~v@01R~
//        }                                                          //~9309I~//~9819R~//~9821R~//~v@01R~
//        if (ACC.activeMembers<PLAYERS)                              //~9429I~//~9819R~//~9821R~//~v@01R~
//            adjustByRobotScore(finalScore);                        //~9429R~//~9819R~//~9821R~//~v@01R~
//        else                                                       //~9824I~//~v@01R~
//            tvLabelBotCharge.setText("");                          //~9824I~//~v@01R~
//        getFinalPoint(finalScore,newScore);                      //~v@01R~
//        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9416I~//~9819R~//~9821R~//~v@01R~
//        {                                                          //~9416I~//~9819R~//~9821R~//~v@01R~
//            String s;                                              //~9416I~//~9819R~//~9821R~//~v@01R~
//            int fp=finalPoint[ii];                                 //~9416I~//~9819R~//~9821R~//~v@01R~
//            if (adjustFinalPoint==S2P_NO)                        //~9416I~//~9819R~//~9821R~//~v@01R~
//            {                                                      //~9614I~//~9819R~//~9821R~//~v@01R~
//                s=fp/1000+"."+((Math.abs(fp)%1000)/100);           //~9614I~//~9819R~//~9821R~//~v@01R~
//            }                                                      //~9614I~//~9819R~//~9821R~//~v@01R~
//            else                                                   //~9416I~//~9819R~//~9821R~//~v@01R~
//            {                                                      //~9614I~//~9819R~//~9821R~//~v@01R~
//                s=Integer.toString(fp/1000);                       //~9416I~//~9819R~//~9821R~//~v@01R~
//            }                                                      //~9614I~//~9819R~//~9821R~//~v@01R~
//            tvsFinalPoint[ii].setText(s);                          //~9416R~//~9819R~//~9821R~//~v@01R~
//            finalPoint1000[ii]=s;                                  //~9615I~//~9819R~//~9821R~//~v@01R~
//        }                                                          //~9416I~//~9819R~//~9821R~//~v@01R~
//        ACC.finalPoint1000=finalPoint1000;                         //~9614I~//~9819R~//~9821R~//~v@01R~
//    }                                                              //~9309I~//~9819R~//~9821R~//~v@01R~
    //******************************************                   //~v@01I~
    @Override                                                      //~v@01I~
    protected void showAmmount()                                   //~v@01I~
    {                                                              //~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.showAmmount");   //~v@01I~
        AG.aPlayers.resetReachAll();    //back reachstick to the player and reset reach flag//~v@01I~
        super.showAmmount();                                       //~v@01I~
    }                                                              //~v@01I~
    //******************************************                   //~9819I~//~v@01R~
    @Override                                                      //~v@01I~
    protected void showAmmountSuspend(int[] PlastScore)                                   //~9819I~//~9820R~//~9821R~//~v@01R~
    {                                                              //~9819I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.showAmmountSuspend lastScore in="+Arrays.toString(PlastScore));//~9821I~//~v@01R~
        getValue();                                                //~v@01I~
        tvPenaltyIOErr.setText(Integer.toString(suspendPenalty));  //~v@01R~
        super.showAmmountSuspend(PlastScore);                      //~v@01I~
    }                                                              //~9819I~//~v@01R~
    //*******************************************************      //~v@01I~
    private void getValue()                                        //~v@01I~
    {                                                              //~v@01I~
        suspendPenalty=suspendPenaltyIOErr;                        //~v@01I~
    	if (cbPenaltyPao.getState())                                //~v@01I~
        	suspendPenalty=Math.max(suspendPenalty,SUSPENDIOERR_PENALTY_PAO);//~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.getValue penalty="+suspendPenalty);//~v@01I~
    }                                                              //~v@01I~
    //*******************************************************      //~9321I~
    @Override                                                      //~9321I~
    public void onDismissDialog()                                  //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.onDismissDialog");   //~9321I~//~9819R~//~v@01R~
        super.onDismissDialog();                                   //~9819I~
        if (!swDismissBeforNew)                                    //~9322I~
        {                                                          //~9819I~
	        AG.aSuspendIOErrDlg=null;                                         //~9321I~//~9322R~//~9818R~//~v@01R~
        }                                                          //~9819I~
        swDismissBeforNew=false;                                   //~9322I~
    }                                                              //~9321I~
//    //******************************************                   //~9321I~//~v@01R~
//    @Override                                                      //~9321I~//~v@01R~
//    public void setButton()                                        //~9321I~//~v@01R~
//    {                                                              //~9321I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg.onDismissDialog");    //~9819I~//~v@01R~
//        super.setButton();                                         //~9321I~//~v@01R~
//        if (swRequester)                                           //~9321I~//~v@01R~
//            btnTotal.setEnabled(swAllOK);                          //~9321I~//~v@01R~
//        else                                                       //~9321I~//~v@01R~
//            btnTotal.setVisibility(View.GONE);                     //~9321I~//~v@01R~
//    }                                                              //~9321I~//~v@01R~
    //******************************************                   //~v@01I~
    //*after updateOKNG from initLayout of OKNGDlg                 //~0219I~
    //******************************************                   //~0219I~
    @Override                                                      //~v@01I~
    public void setButton()                                        //~v@01I~
    {                                                              //~v@01R~
		int ctrConnection=AG.aBTMulti.BTGroup.getConnectedCtr();       //~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.setButton swReceived="+swReceived+",swAllOK="+swAllOK+",ctrConnection="+ctrConnection+",ctrDisconnected="+ctrDisconnected);//~v@01R~
        super.setButton();                                         //~v@01I~
        if (swReceived)                                            //~v@01I~
        {                                                          //~v@01I~
			btnTotal.setVisibility(View.GONE);                     //~v@01I~
        }                                                          //~v@01I~
        else                                                       //~v@01I~
            if (swServer)                                          //~v@01R~
            {                                                      //~v@01R~
                btnOK.setEnabled(ctrConnection!=0);                //~v@01R~
//              btnTotal.setEnabled(swAllOK || ctrConnection==0);  //swAllOK=ctrNone=0 and ctrNG=0;//~v@01R~
                btnTotal.setEnabled(swAllOK && ctrDisconnected==0);  //swAllOK=ctrNone=0 and ctrNG=0;//~v@01R~
            }                                                      //~v@01R~
            else                                                   //~v@01R~
            {                                                      //~v@01R~
                btnOK.setVisibility(View.GONE);                    //~v@01R~
                btnTotal.setVisibility(View.VISIBLE);              //~v@01R~
                btnTotal.setEnabled(false); //enabled by OK on alert//~v@01R~
                if (ctrDisconnected!=0)                            //~v@01I~
	                alertToForceOK(this,TITLEID,R.string.Alert_SuspendIOErrDlgForceOKClient);//~v@01I~
            }                                                      //~v@01R~
    }                                                              //~v@01I~
    //*******************************************************************//~9321I~//~v@01R~
    //*on UiThread                                                 //~9321I~//~v@01R~
    //*******************************************************************//~9321I~//~v@01R~
    @Override                                                      //~9321I~//~v@01R~
//  protected void updateOKNGAdditional(int PctrNone,int PctrNG,boolean PswAllOK)//~9321I~//~v@01R~
    protected void updateOKNGAdditional(int PctrNone,int PctrNG,int PctrDisconnected,boolean PswAllOK)//~v@01I~
    {                                                              //~9321I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.updateOKNGAdditional ctrNG="+PctrNG+",ctrNone="+PctrNone+",swAllOK="+PswAllOK+",ctrDisconnected="+PctrDisconnected);//~9321I~//~9322R~//~9818R~//~v@01R~
//      btnTotal.setEnabled(PswAllOK);                             //~9321I~//~v@01R~
        btnTotal.setEnabled(PswAllOK && PctrDisconnected==0);       //~v@01I~
        if (PctrNone==0/*all replyed except disconneced*/ && (!PswAllOK/*NG replyed*/ || PctrDisconnected!=0)) //all responsed, someone replyed NG or disconnectedctr!=0//~v@01R~
        {                                                          //~9612I~//~v@01R~
            if (swRequester)                                       //~9612I~//~v@01R~
                alertToForceOK(this,TITLEID,R.string.Alert_SuspendIOErrDlgForceOK);//~9612I~//~9818R~//~v@01R~
        }                                                          //~9612I~//~v@01R~
    }                                                              //~9321I~//~v@01R~
    //*******************************************************************//~9612I~//~v@01R~
    @Override                                                      //~9612I~//~v@01R~
    protected void alertActionReceived(int Pbuttonid,int Prc)      //~9612I~//~v@01R~
    {                                                              //~9612I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.alerActionReceived buttonID="+Pbuttonid+",rc="+Prc);//~9612I~//~9818R~//~v@01R~
        if (Pbuttonid==BUTTON_POSITIVE)                            //~9612I~//~v@01R~
        {                                                          //~9612I~//~v@01R~
            btnTotal.setEnabled(true/*PswAllOK*/);                 //~9612I~//~v@01R~
        }                                                          //~9612I~//~v@01R~
    }                                                              //~9612I~//~v@01R~
    //**************************************************************************//~v@01I~
    @Override //UFDlg                                              //~v@01I~
    public void onClickButton(Button Pbutton)                      //~v@01I~
    {                                                              //~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.onClickButton"); //~v@01I~
	    AG.aUARestart.resetIOExceptionInGaming();	//activate send by OK button//~v@01I~
        super.onClickButton(Pbutton);                              //~v@01I~
    }                                                              //~v@01I~
//    //******************************************                   //~9823I~//~v@01R~
//    @Override                                                      //~9823I~//~v@01R~
//    public void onClickTotal()                                     //~9823I~//~v@01R~
//    {                                                              //~9823I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg.onClickTotal");       //~9823I~//~v@01R~
//        dismiss();                                                 //~9823I~//~v@01R~
//        suspendGame();                                             //~9823I~//~v@01R~
//    }                                                              //~9823I~//~v@01R~
//    //**************************************************************************//~9823I~//~v@01R~
//    //*from OnClickTotal,on Server                                 //~9823R~//~v@01R~
//    //**************************************************************************//~9823I~//~v@01R~
//    @Override                                                    //~v@01R~
//    public void suspendGame()                          //~9822I~   //~9823R~//~v@01R~
//    {                                                              //~9822I~//~v@01R~
//        suspendOption=rgSuspend.getCheckedID();   //Send may not done when no connection//~v@01I~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg:suspendGame suspendOption="+suspendOption);//~9823I~//~v@01R~
//        switch(suspendOption)                                      //~9823I~//~v@01R~
//        {                                                          //~9823I~//~v@01R~
//        case SUSPEND_GAMEOVER: //=0                                //~9823I~//~v@01R~
//            doGameOver();                                          //~9823I~//~v@01R~
//            break;                                                 //~9823I~//~v@01R~
//        case SUSPEND_SUSPEND:  //=1;                               //~9823I~//~v@01R~
//            doSuspend();                                           //~9823I~//~v@01R~
//            break;                                                 //~9823I~//~v@01R~
//        case SUSPEND_CONTINUE: //=2;                               //~9823I~//~v@01R~
//            doContinue();                                          //~9823I~//~v@01R~
//            break;                                                 //~9823I~//~v@01R~
//        }                                                          //~9823I~//~v@01R~
//        Status.setSuspendGame(false);                            //~v@01R~
//    }                                                              //~9822I~//~v@01R~
//    //**************************************************************************//~9823I~//~v@01R~
//    protected void doGameOver()                                      //~9823I~//~v@01R~
//    {                                                              //~9823I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg:doGameOver");         //~9823I~//~v@01R~
//        System.arraycopy(newScore,0,lastScore,0,PLAYERS);   //add reachback effect//~v@01R~
//        endGame(newTotal);                                         //~9823R~//~v@01R~
//    }                                                              //~9823I~//~v@01R~
//    //**************************************************************************//~9823I~//~v@01R~
//    protected void doSuspend()                                      //~9823I~//~v@01R~
//    {                                                              //~9823I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg:doSuspend");          //~9823I~//~v@01R~
//        suspendGameSuspend();                                      //~9824R~//~v@01R~
//    }                                                              //~9823I~//~v@01R~
//    //**************************************************************************//~9823I~//~v@01R~
//    protected void doContinue()                                      //~9823I~//~v@01R~
//    {                                                              //~9823I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg:doContinue endgameType="+ACC.endgameTypeNextGame+",ACC.suspendEndgameType="+ACC.nextgameTypeNextGame);//~9823R~//~v@01R~
//        AG.aStatus.resetSuspendRequest();                          //~9823I~//~v@01R~
//        ACC.nextGameWithoutSuspended(ACC.endgameTypeNextGame,ACC.nextgameTypeNextGame);//~9823I~//~v@01R~
//    }                                                              //~9823I~//~v@01R~
    //**************************************************************************//~9318I~//~v@01R~
    //*from UserAction,GCM_SUSPENDDLG btio msg received                                           //~9318I~//~9320R~//~9322R~//~9822R~//~9826R~//~v@01R~
    //**************************************************************************//~9318I~//~v@01R~
    public static void onReceived(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~9318R~//~9826R~//~v@01R~
    {                                                              //~9318I~//~v@01R~
        int[] total,ctrreach,penalty,finalS;                       //~9823I~//~v@01R~
        int[][] intssP;//~9321I~//~9415R~                          //~v@01R~
        String ts;                                                 //~9826I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg:onReceived swServer="+PswServer+",PswReceived="+PswReceived+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~9318R~//~9322R~//~9818R~//~v@01R~
        int scoreMsgtype=PintParm[POS_SCOREMSGTYPE];               //~9320I~//~9322R~//~v@01R~
        if (PswServer)  //from dealer(client)                      //~9318R~//~v@01R~
        {                                                          //~9318I~//~v@01R~
            switch(scoreMsgtype)                                   //~9321I~//~v@01R~
            {                                                      //~9321I~//~v@01R~
            case SUSPENDGAME_CONFIRM_REPLY:                      //~9321R~//~9819R~//~v@01R~
                if (UAEndGame.isUpdateAfterSendServer())           //~0314I~
                {                                                  //~0314I~
                    if (Dump.Y) Dump.println("SuspendIOErrDlg.onReceivedRequest COMPRESULT RESP updateAfterSend");//~0314I~
                    break;                                         //~0314I~
                }                                                  //~0314I~
                int replyEswn=PintParm[POS_REPLAY_ESWN];           //~9822I~//~v@01R~
                if (Utils.isShowingDialogFragment(AG.aSuspendIOErrDlg)) //~9822I~//~v@01R~
                    AG.aSuspendIOErrDlg.repaintOKNG(replyEswn,PintParm[POS_OKNG]!=0);    //callback updateOKNGAdditional//~9822I~//~v@01R~
                break;                                             //~9321I~//~v@01R~
              }                                                      //~9321I~//~9822R~//~v@01R~
        }                                                          //~9318I~//~v@01R~
        else                                                       //~9318I~//~v@01R~
        {                                                          //~9318I~//~v@01R~
            switch(scoreMsgtype)                                   //~9321I~//~v@01R~
            {                                                      //~9321I~//~v@01R~
            case SUSPENDGAME_CONFIRM_REQUEST:                    //~9321I~//~9819R~//~v@01R~
                  if (Utils.isShowingDialogFragment(AG.aSuspendIOErrDlg))//~9822I~//~v@01R~
                  {                                                //~9822I~//~v@01R~
                      AG.aSuspendIOErrDlg.swDismissBeforNew=true;       //~9822I~//~v@01R~
                      AG.aSuspendIOErrDlg.dismiss();                    //~9822I~//~v@01R~
                  }                                                //~9822I~//~v@01R~
                  dismissReconnectDlg();                           //~0110I~//~v@01M~
                  intssP=parseConfirmRequestSuspend(PintParm);     //~9822R~//~v@01R~
                  SuspendIOErrDlg.newInstance(intssP).show();           //~9822I~//~v@01R~
                  break;                                             //~9321I~//~9822R~//~v@01R~
            case SUSPENDGAME_CONFIRMED:                          //~9322I~//~9819R~//~9822R~//~9823R~//~v@01R~
                    total=new int[PLAYERS];                        //~9823I~//~v@01R~
                    ctrreach=new int[PLAYERS];                     //~9823I~//~v@01R~
                    penalty=new int[PLAYERS];                      //~9823I~//~v@01R~
                    finalS=new int[PLAYERS];                       //~9823I~//~v@01R~
                    System.arraycopy(PintParm,POS_AMMOUNT,total,0,PLAYERS);//~9823I~//~v@01R~
                    System.arraycopy(PintParm,POS_REACH,ctrreach,0,PLAYERS);//~9823I~//~v@01R~
                    System.arraycopy(PintParm,POS_PENALTY,penalty,0,PLAYERS);//~9823I~//~v@01R~
                    System.arraycopy(PintParm,POS_AMMOUNT_FINALSCORE,finalS,0,PLAYERS);//~9823I~//~v@01R~
                    intssP=new int[][]{total,ctrreach,penalty,finalS};//~9823I~//~v@01R~
                    ts= History.timestampToFilename(PintParm[POS_DATE],PintParm[POS_TIME]);//~9826R~//~v@01R~
                    endGame(ts,EGDR_NORMAL,intssP);                //~9826I~//~v@01R~
                break;                                             //~9322I~//~9822R~//~9823R~//~v@01R~
            case SUSPENDGAME_INTERRUPTED:                          //~9824R~//~v@01R~
                total=new int[PLAYERS]; //score                    //~9824M~//~v@01R~
                ctrreach=new int[PLAYERS];                         //~9824M~//~v@01R~
                int[] gameSeq=new int[PLAYERS];                    //~9824M~//~v@01R~
                int[] other=new int[PLAYERS];                      //~v@01R~
                System.arraycopy(PintParm,POS_AMMOUNT,total,0,PLAYERS);//~9824M~//~v@01R~
                System.arraycopy(PintParm,POS_REACH,ctrreach,0,PLAYERS);//~9824M~//~v@01R~
                System.arraycopy(PintParm,POS_GAMESEQ,gameSeq,0,PLAYERS);//~9824M~//~v@01R~
                System.arraycopy(PintParm,POS_OTHER,other,0,PLAYERS);//~v@01R~
                intssP=new int[][]{total,ctrreach,gameSeq,other};  //~v@01R~
                ts=History.timestampToFilename(PintParm[POS_DATE],PintParm[POS_TIME]);//~9826R~//~v@01R~
                suspendGameSuspend(ts,intssP);  //save to history  //~9826I~//~v@01R~
                break;                                             //~9824M~//~v@01R~
            }                                                      //~9824I~//~v@01R~
        }                                                          //~9318I~//~v@01R~
    }                                                              //~9318I~//~v@01R~
    //**************************************************************************//~v@01R~
    protected static int[][] parseConfirmRequestSuspend(int[] PintParm)//~9822R~//~v@01R~
    {                                                              //~9822I~//~v@01R~
        int pos=POS_SUSPEND_OPT;                                   //~9822R~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg:parseConfirmRequest startpos="+pos+",intp="+Arrays.toString(PintParm));//~9822I~//~v@01R~
//      int suspendOption=PintParm[pos++];                         //~9822I~//~v@01R~
        int[] intsSuspendOption=new int[PLAYERS];                  //~v@01I~
        int[] score=new int[PLAYERS];                              //~9822I~//~v@01R~
        int[] reach=new int[PLAYERS];                              //~9822I~//~v@01R~
        int[] penalty=new int[PLAYERS];                            //~9822I~//~v@01R~
        int[] finalS=new int[PLAYERS];                             //~9822I~//~v@01R~
        System.arraycopy(PintParm,pos,intsSuspendOption,0,PLAYERS);   pos+=PLAYERS;//~v@01I~
        System.arraycopy(PintParm,pos,score,0,PLAYERS);   pos+=PLAYERS;//~9822I~//~v@01R~
        System.arraycopy(PintParm,pos,reach,0,PLAYERS);   pos+=PLAYERS;//~9822I~//~v@01R~
        System.arraycopy(PintParm,pos,penalty,0,PLAYERS);   pos+=PLAYERS;//~9822I~//~v@01R~
        System.arraycopy(PintParm,pos,finalS,0,PLAYERS);   pos+=PLAYERS;//~9822I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg:parseConfirmRequest intsSuspendOption="+Arrays.toString(intsSuspendOption));//~9822I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg:parseConfirmRequest lastScore="+Arrays.toString(score));//~9822I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg:parseConfirmRequest reach="+Arrays.toString(reach));//~9822I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg:parseConfirmRequest penalty="+Arrays.toString(penalty));//~9822I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg:parseConfirmRequest finalS="+Arrays.toString(finalS));//~9822I~//~v@01R~
//      int[][] rc=new int[][]{new int[]{suspendOption},score,reach,penalty,finalS};//~9822I~//~v@01R~
        int[][] rc=new int[][]{intsSuspendOption,score,reach,penalty,finalS};//~v@01I~
        return rc;                                                 //~9822I~//~v@01R~
    }                                                              //~9822I~//~v@01R~
//    //**************************************************************************//~9321I~//~9823I~//~v@01R~
//    //*like ScoreDlg.endgame()  by Button                          //~9823I~//~9826R~//~v@01R~
//    //**************************************************************************//~9823I~//~v@01R~
//    protected void endGame(int[] PnewTotal)                          //~9823R~//~v@01R~
//    {                                                              //~9321I~//~9823I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg.endGame type="+endgameType);//~9823R~//~v@01R~
//        AG.aAccounts.setScore(PnewTotal);                         //~9415I~//~9823I~//~v@01R~
//        showScoreFinalPoint();  //AccountsDlg                      //~9823I~//~v@01R~
//        Status.endGame(endgameType,NGTP_GAMEOVER);   //reset ctrGame to 0,affect to getCurrentEswn and OKNGDlg.isdealer()                        //~9321I~//~9401R~//~9605R~//~9823I~//~v@01R~
//        sendEndGame(PnewTotal);                                    //~9823I~//~v@01R~
//        UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndSuspendGameover));//~9826I~//~v@01R~
//    }                                                              //~9321I~//~9823I~//~v@01R~
//    //**************************************************************************//~9824I~//~v@01R~
//    protected void suspendGameSuspend()                              //~9824R~//~v@01R~
//    {                                                              //~9824I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg.suspendGameSuspend type="+endgameType);//~9824R~//~v@01R~
//        Rect r=Status.getGameSeq();                                //~9824I~//~v@01R~
//        r.bottom+=AG.aPlayers.ctrReach; //reachctr is not yet set(it will be set at Status.setGameSeq//~9829I~//~v@01R~
//        int[] gameSeq=new int[]{r.left/*wind*/,r.top/*gamectr*/,r.right/*dupctr*/,r.bottom/*reach ctr*/};//~9824I~//~v@01R~
//                                                                   //~9829I~//~v@01R~
//        int[] score=AG.aAccounts.score;                            //~9824I~//~v@01R~
//        int[] other=new int[]{ACC.endgameTypeNextGame,ACC.nextgameTypeNextGame,0,0};//~v@01R~
//        int[][] intssP=new int[][]{score,ctrReach/*posSeq*/,gameSeq,other}; //4 entry//~v@01R~
//        String[] accountNames=AG.aAccounts.getAccountNamesByPosition();//~9824I~//~v@01R~
//        String ts=Utils.getTimeStamp(TS_DATE_TIME2); //space between date and time for parseInt//~9826R~//~v@01R~
//        String fnm=History.timestampToFilename(ts);                //~9826I~//~v@01R~
//        AG.aHistory.saveRuleInterrupted();                         //~9826R~//~v@01R~
//        AG.aHistory.saveLatestGameInterrupted(fnm,accountNames,intssP);//~9826R~//~v@01R~
//        sendToAllClientInterrupted(ts,suspendOption,intssP);     //~v@01R~
//        UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndSuspend));//~9824M~//~v@01R~
//    }                                                              //~9824I~//~v@01R~
    //**************************************************************************//~v@01I~
    protected void suspendGameSuspend()                            //~v@01I~
    {                                                              //~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.suspendGameSuspend type="+endgameType);//~v@01I~
        ACC.resetGameByIOErr();	//set endgametype/nextgametype to reset//~v@01I~
		super.suspendGameSuspend();	//SuspendDlg                   //~v@01I~
    }                                                              //~v@01I~
//    //**************************************************************************//~9824I~//~v@01R~
//    //*client received                                             //~9826I~//~v@01R~
//    //**************************************************************************//~9826I~//~v@01R~
//    protected static void suspendGameSuspend(String Ptimestamp,int[][] PintssP)//~9826I~//~v@01R~
//    {                                                              //~9824I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg.suspendgameSuspend intssP="+Utils.toString(PintssP));//~9824R~//~v@01R~
//        AG.aHistory.saveRuleInterrupted();                         //~9826I~//~v@01R~
//        String[] accountNames=AG.aAccounts.getAccountNamesByPosition();//~9824I~//~v@01R~
//        AG.aHistory.saveLatestGameInterrupted(Ptimestamp,accountNames,PintssP);//~9826I~//~v@01R~
//    }                                                              //~9824I~//~v@01R~
//    //**************************************************************************//~9321I~//~9823I~//~v@01R~
//    //*from OnClickTotal                                           //~9823I~//~v@01R~
//    //*like from AccountsDlg.OnClickTotal                                           //~9605I~//~9823I~//~v@01R~
//    //**************************************************************************//~9605I~//~9823I~//~v@01R~
//    @Override                                                      //~9823I~//~v@01R~
//    public void sendEndGame(int[] Ptotal)                          //~9321I~//~9823I~//~v@01R~
//    {                                                              //~9321I~//~9823I~//~v@01R~
//        int[][] intssP=new int[][]{Ptotal,ctrReach,intsPenalty,finalScore};//~9823I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg.sendEndGame total="+Arrays.toString(Ptotal));//~9525I~//~9823I~//~v@01R~
//        String[] accountNames=AG.aAccounts.getAccountNamesByPosition();//~9826I~//~v@01R~
//        String ts=Utils.getTimeStamp(TS_DATE_TIME2); //space between date and time for parseInt//~9826I~//~v@01R~
//        String fnm=History.timestampToFilename(ts);                //~9826I~//~v@01R~
//        AG.aHistory.saveLatestGameSuspended(fnm,accountNames,intssP);//~9826I~//~v@01R~
//        sendToAllClientEndGame(ts,suspendOption,endgameType,intssP);//~9826I~//~v@01R~
//    }                                                              //~9321I~//~9823I~//~v@01R~
//    //**************************************************************************//~9823I~//~v@01R~
//    //*received CONFIRNED                                          //~9823I~//~v@01R~
//    //*newTotal,ctrReach,penalty,finalScore                        //~9823I~//~v@01R~
//    //**************************************************************************//~9823I~//~v@01R~
//    protected static void endGame(String Pfnm,int PendgameType,int[][] PintssP/*indexSeq*/)//~9826I~//~v@01R~
//    {                                                              //~9823I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg:endGame type="+PendgameType+",intP="+Utils.toString(PintssP));//~9823I~//~v@01R~
//        AG.aAccounts.setScore(PintssP[3]);  //finalScore           //~9823I~//~v@01R~
//        showScoreFinalPoint();  //AccountsDlg                      //~9823I~//~v@01R~
//        Status.endGame(PendgameType,NGTP_GAMEOVER);                //~9823I~//~v@01R~
//        AG.aStatus.gameOverScoreFixed();                           //~9823I~//~v@01R~
//        String[] accountNames=AG.aAccounts.getAccountNamesByPosition();//~9823I~//~v@01R~
//        AG.aHistory.saveLatestGameSuspended(Pfnm,accountNames,PintssP);//~9826I~//~v@01R~
//    }                                                              //~9823I~//~v@01R~
//    //*******************************************************      //~9321I~//~v@01R~
//    //*on requester                                                //~9321I~//~v@01R~
//    //*******************************************************      //~9321I~//~9822R~//~v@01R~
    @Override     //SuspendDlg                                    //~9822I~//~v@01R~//~0226R~
    public void sendConfirmRequest()                               //~9822I~//~v@01R~
    {                                                              //~9822I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg:sendConfirmRequest"); //~9822I~//~v@01R~
        suspendOption=rgSuspend.getCheckedID();                     //~9820I~//~9821M~//~9822M~//~v@01R~
        int[] intsSuspend=new int[PLAYERS];                        //~9822I~//~v@01R~
        Utils.boolean_int(true/*Pswb2i*/,swsSuspend,intsSuspend);  //~9822R~//~v@01R~
    	suspendOptionPao=cbPenaltyPao.getState();            //~v@01R~
        int[] intsSuspendOption=new int[]{suspendOption,suspendOptionPao?1:0,0,0};//~v@01I~
//      sendToAllClientConfirm(suspendOption,new int[][]{lastScore,ctrReach,intsSuspend,finalScore});//~v@01R~
        sendToAllClientConfirm(new int[][]{intsSuspendOption,lastScore,ctrReach,intsSuspend,finalScore});//~v@01R~
        resetRespStat();                                           //~v@01I~
        repaintOKNG();    //callback updateOKNGAdditional          //~v@01I~
        btnTotal.setEnabled(false);                                //~v@01I~
    }                                                              //~9822I~//~v@01R~
    //*******************************************************************//~9822I~//~v@01R~
    @Override                                                      //~v@01I~
    protected void sendReply(boolean PswOK)                        //~9822I~//~v@01R~
    {                                                              //~9822I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.sendReply OK="+PswOK);//~9822I~//~v@01R~
        String okng=(PswOK ? "1" : "0");                           //~9822I~//~v@01R~
//      AG.aUserAction.sendToServer(GCM_SUSPENDDLG,PLAYER_YOU,SUSPENDGAME_CONFIRM_REPLY,okng);//~9822I~//~v@01R~
        AG.aUserAction.sendToServer(GCM_SUSPENDDLG_IOERR,PLAYER_YOU,SUSPENDGAME_CONFIRM_REPLY,okng);//~v@01I~
    }                                                              //~9822I~//~v@01R~
    //**************************************************************************//~9321I~//~v@01R~
    @Override                                                      //~v@01I~
    protected void sendToServerConfirm()                             //~9321R~//~v@01R~
    {                                                              //~9321I~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.sendToServerConfirm");  //~9321I~//~9322R~//~9818R~//~v@01R~
        String msgdata=makeReqMsg(new int[][]{lastScore,minusPrize,minusCharge,finalScore});//~9415R~//~v@01R~
//      AG.aUserAction.sendToServer(GCM_SUSPENDDLG,PLAYER_YOU,SUSPENDGAME_CONFIRM_REQUEST,msgdata);//~9321R~//~9322R~//~9819R~//~9822R~//~v@01R~
        AG.aUserAction.sendToServer(GCM_SUSPENDDLG_IOERR,PLAYER_YOU,SUSPENDGAME_CONFIRM_REQUEST,msgdata);//~v@01I~
    }                                                              //~9321I~//~v@01R~
//    //**************************************************************************//~9321I~//~v@01R~
//    //*total,ctrReach,penalty,finalscore                           //~9823I~//~v@01R~
//    //**************************************************************************//~9823I~//~v@01R~
//    protected static void sendToAllClientEndGame(String Ptimestamp,int PsuspendOption,int PendgameType,int[][] PintssP)//~9826I~//~v@01R~
//    {                                                              //~9321I~//~v@01R~
//        String msg=Ptimestamp+MSG_SEPAPP2+PsuspendOption+makeReqMsg(PintssP)+MSG_SEPAPP2+PendgameType;//~9826I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg:sendToAllClientEndGame msgdata="+msg);//~9321I~//~9322R~//~9525R~//~9818R~//~v@01R~
//        sendToAllClient(SUSPENDGAME_CONFIRMED,msg);             //~9321I~//~9819R~//~v@01R~
//    }                                                              //~9321I~//~v@01R~
//    //**************************************************************************//~9824I~//~v@01R~
//    //*total,ctrReach,penalty,finalscore                           //~9824I~//~v@01R~
//    //**************************************************************************//~9824I~//~v@01R~
//    protected static void sendToAllClientInterrupted(String Ptimestamp,int PsuspendOption,int[][] PintssP/*score,reach,gameseq,other*/)//~v@01R~
//    {                                                              //~9824I~//~v@01R~
//        String msg=Ptimestamp+MSG_SEPAPP2+PsuspendOption+makeReqMsg(PintssP)+MSG_SEPAPP2;//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg:sendToAllClientInterrupted msgdata="+msg);//~9824I~//~v@01R~
//        sendToAllClient(SUSPENDGAME_INTERRUPTED,msg);              //~9824I~//~v@01R~
//    }                                                              //~9824I~//~v@01R~
//    //**************************************************************************//~9822I~//~v@01R~
//    protected static void sendToAllClientConfirm(int PsuspendOption,int[][] PintssP)//~9822I~//~v@01R~
//    {                                                              //~9822I~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg:sendToAllClientConfirm suspendOption="+PsuspendOption+",intp="+Utils.toString(PintssP));//~9822I~//~v@01R~
//        String msg=makeReqMsg(PintssP);                            //~9822I~//~v@01R~
//        msg="0:0"/*dummy timestamp*/+MSG_SEPAPP2+PsuspendOption+MSG_SEPAPP2+msg;//~9826I~//~v@01R~
//        sendToAllClient(SUSPENDGAME_CONFIRM_REQUEST,msg);          //~9822I~//~v@01R~
//    }                                                              //~9822I~//~v@01R~
    //**************************************************************************//~v@01I~
    private  void sendToAllClientConfirm(int[][] PintssP)    //~v@01I~
    {                                                              //~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg:sendToAllClientConfirm intp="+Utils.toString(PintssP));//~v@01I~
        String msg=makeReqMsg(PintssP);                            //~v@01I~
        msg="0:0"/*dummy timestamp*/+MSG_SEPAPP2+msg;              //~v@01I~
        sendToAllClient(SUSPENDGAME_CONFIRM_REQUEST,msg);          //~v@01I~
    }                                                              //~v@01I~
    //**************************************************************************//~9321I~//~v@01R~
    @Override                                                      //~v@01I~
    protected void sendToAllClient(int PendgameMsgid,String Pmsgdata)//~9321I~//~v@01R~
    {                                                              //~9321I~//~v@01R~
        int eswn= Accounts.playerToEswn(PLAYER_YOU);            //~9321I~//~v@01R~
        String msgdata=eswn+MSG_SEPAPP2+PendgameMsgid+MSG_SEPAPP2+Pmsgdata;//~9321I~//~9322R~//~9402R~//~v@01R~
        if (Dump.Y) Dump.println("SuspendIOErrDlg:sendToAllClient msgdata="+msgdata);//~9321I~//~9322R~//~9818R~//~v@01R~
//      AG.aUserAction.sendToClient(true/*swAll*/,false/*swRobot*/,GCM_SUSPENDDLG,PLAYER_YOU,msgdata);//~9321I~//~9322R~//~9822R~//~v@01R~
        AG.aUserAction.sendToClient(true/*swAll*/,false/*swRobot*/,GCM_SUSPENDDLG_IOERR,PLAYER_YOU,msgdata);//~v@01I~
    }                                                              //~9321I~//~v@01R~
//    //**************************************************************************//~9322I~//~v@01R~
//    //*from GC by btn click                                        //~9322I~//~v@01R~
//    //**************************************************************************//~9322I~//~v@01R~
//    public static boolean showDismissed()                             //~9823M~//~v@01R~
//    {                                                              //~9823M~//~v@01R~
//        if (Dump.Y) Dump.println("SuspendIOErrDlg.showDismissed");      //~9823M~//~v@01R~
//        boolean rc=false;   //dismiss menudlg                    //~v@01R~
//        if (Utils.isShowingDialogFragment(AG.aSuspendIOErrDlg))         //~9823I~//~v@01R~
//        {                                                          //~9823I~//~v@01R~
//            if (Dump.Y) Dump.println("SuspendIOErrDlg:showDismisswd already shown");//~9823I~//~v@01R~
//            return false;                                                //~9823I~//~v@01R~
//        }                                                          //~9823I~//~v@01R~
//        if (!Status.isSuspendByIOErr())                          //~v@01R~
//        {                                                        //~v@01R~
//            UView.showToast(R.string.Err_SuspendNoIOErr);        //~v@01R~
//            return false;                                        //~v@01R~
//        }                                                        //~v@01R~
//        return rc;                                               //~v@01R~
//    }                                                              //~9823M~//~v@01R~
    //*******************************************************      //~v@01I~
    //*UCheckBoxI                                                  //~v@01I~
    //*******************************************************      //~v@01I~
    @Override                                                      //~v@01I~
    public void onChangedUCB(CompoundButton Pbtn, boolean PisChecked, int Pparm)//~v@01I~
    {                                                              //~v@01I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.onChangedUCB swInitLayout="+swInitLayout+",parm="+Pparm+",isChecked="+PisChecked);//~v@01R~
        if (swInitLayout)                                          //~v@01I~
        	return;                                                //~v@01I~
                                                                   //~v@01I~
        switch(Pparm)                                              //~v@01I~
        {                                                          //~v@01I~
    	case UCBP_PAO:                                             //~v@01I~
  		    showAmmount();                                         //~v@01R~
          	break;                                                 //~v@01I~
    	default:  //UCBP_SUSPENDER                                 //~v@01I~
            int wind=Pparm-UCBP_SUSPENDER;                         //~v@01I~
        	if (wind>=0 && wind<PLAYERS)                            //~v@01I~
                swsSuspend[wind]=PisChecked;                       //~v@01I~
  		    showAmmount();                                         //~v@01I~
          	break;                                                 //~v@01I~
        }                                                          //~v@01I~
        resetSent();                                               //~0314I~
    }                                                              //~v@01I~
    //**************************************************************************//~0314I~
    private void resetSent()                                            //~0314I~
    {                                                              //~0314I~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.resetSent");     //~0314I~
	    CMP.swSent=false;                                          //~0314I~
        swAllOK=false;                                             //~0314I~
        resetRespStat();	//OKNGDlg                              //~0314I~
    	setButton();                                               //~0314I~
    }                                                              //~0314I~
    //**************************************************************************//~0110I~//~v@01M~
    private static void dismissReconnectDlg()                             //~0110I~//~v@01M~
    {                                                              //~0110I~//~v@01M~
        if (Dump.Y) Dump.println("SuspendIOErrDlg.dismissReconnectDlg");//~0110I~//~v@01M~//~0314R~
		if (Utils.isShowingDialogFragment(AG.aBTRDialog))          //~0110I~//~v@01M~
	        AG.aBTRDialog.dismiss();                               //~0110I~//~v@01M~
		if (Utils.isShowingDialogFragment(AG.aWDAR))               //~0110I~//~v@01M~
	        AG.aWDAR.dismiss();                                    //~0110I~//~v@01M~
    }                                                              //~0110I~//~v@01M~
}//class                                                           //~v@@@R~
