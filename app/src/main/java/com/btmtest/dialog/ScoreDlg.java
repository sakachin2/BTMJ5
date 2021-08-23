//*CID://+vac5R~:                             update#= 1121;       //+vac5R~
//*****************************************************************//~v101I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//+vac5I~
//*****************************************************************//~1312I~
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
import com.btmtest.game.Status;
import com.btmtest.game.UserAction;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.URadioGroup;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.dialog.FinalGameDlg.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.utils.Alert.*;

public class ScoreDlg  extends OKNGDlg //UFDlg                                            //~9312R~//~9321R~
//            implements UCheckBox.UCheckBoxI                        //~9322I~//~9408R~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.score;              //~9312R~
    private static final int LAYOUTID_SMALLFONT=R.layout.score_theme;//+vac5I~
    private static final int TITLEID=R.string.Title_ScoreDlg;//~9307I~//~9312R~
    private static final String HELPFILE="ScoreDlg";                //~9220I~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~
                                                                   //~9318I~
    private static final int POS_SCOREMSGTYPE=1;                    //~9318R~//~9320R~
    private static final int POS_ENDGAMETYPE=2;                    //~9320I~
    private static final int POS_NEXTGAMETYPE=3;                  //~9318R~//~9320R~
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
                                                                   //~9309I~
    private TextView[] tvsScore,tvsAdd,tvsPay,tvsTotal;                       //~9312I~//~9316R~//~9321R~
    private TextView[] tvsName,tvsEswn,tvsMinusCharge/*,tvsMinusChargeResult*/;    //~9309R~               //~9312R~//~9408R~//~9415R~
    private TextView[] tvsMinusChargeLater;	//2nd line             //~9415I~
    private int[] ammount,minusScore/*initial position seq*/;                              //~9414R~//~9606R~//~9607R~
    private int[] minusPay=new int[PLAYERS];	//idx seq          //~9414I~
    private int[] newTotal=new int[PLAYERS];   //initial position seq      //~9312I~//~9607R~
    private int[] oldTotal=new int[PLAYERS];   //initial pos seq                    //~9402I~//~9607R~
    private int[] payers=new int[PLAYERS];                         //~9322I~
    private String[] accountNames;                                 //~9312I~
                                                                   //~9320I~
    private boolean swMinusStop,swMinus0,/*swMinusRobot,*/swMinusPay,swMinusPayGetAllPoint;//~9402R~//~9429R~
    private int pointMinusStop;
    private boolean swMinusCharge;                                    //~9320I~//~9408R~
    private TextView[] tvsPayer=new TextView[PLAYERS];             //~9320I~
//  private TextView tvSetPayer;                                  //~9402I~//~9429R~
    private UCheckBox[][] cbssPayTo=new UCheckBox[PLAYERS][PLAYERS];//~9320I~
//  private UCheckBox cbMinusStop,cbMinus0,/*cbMinusPay,*/cbMinusRobot;//~9320I~//~9402R~//~9408R~
    private UCheckBox             cbMinus0/*cbMinusPay,*//*cbMinusRobot*/;//~9408I~//~9429R~
    private URadioGroup rgMinusPay;	//for TEST calc                //~9402I~
    private LinearLayout[] llsMinus=new LinearLayout[PLAYERS];     //~9320I~
    private LinearLayout[] llsMinusPay,llsMinusChargeLater;        //~9416I~
    private Button btnTotal,btnShowRule;                           //~9417R~
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
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public ScoreDlg()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("ScoreDlg.defaultConstructor"); //~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~
        AG.aScoreDlg=this;                                         //~9321I~
        ACC=AG.aAccounts;                                          //~9322I~//~0314M~
        CMP=AG.aComplete;                                          //~9322I~//~0314M~
//      CMP.swSent=false;                                          //~0314R~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
//  public static ScoreDlg newInstance(int[] Pammount,int[] Pscore)             //~9312R~//~9321R~//~9413R~
    public static ScoreDlg newInstance(int PendGameType,int[] Pammount,int[] Pscore)//~9413I~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("ScoreDlg.newInstance amt="+Arrays.toString(Pammount));//~9312R~
    	ScoreDlg dlg=new ScoreDlg();                                     //~v@@@I~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~//~9312R~
//    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9227R~//+vac5R~
      	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//+vac5I~
    			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_CLOSEBTN|FLAG_HELPBTN|FLAG_RULEBTN,//~9708R~
				TITLEID,HELPFILE);         //~v@@@I~               //~9220R~
        dlg.ammount=Pammount;	//point at last game(not total score)                                      //~9312R~//~9320R~
//      Pscore[0]=30000;    //TODO TEST                            //~9415R~
        dlg.minusScore=Pscore;	//point at last game(not total score)//~9321I~
        dlg.parmEndGameType=PendGameType;                          //~9413I~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
    //******************************************                   //~9321I~
//  public static ScoreDlg newInstance(int[] Pammount,int[] Pscore,int[] PminusPay,int[] PnewTotal,int[] PpayerInfo)//~9321R~//~9322R~//~9408R~
//  public static ScoreDlg newInstance(int[] Pammount,int[] Pscore,int[] PminusPay,int[] PnewTotal,int[] Pcharge,int[] PchargeResult)//~9408I~//~9413R~
    public static ScoreDlg newInstance(int PendgameType,int[] Pammount,int[] Pscore,int[] PminusPay,int[] PnewTotal,int[] Pcharge,int[] PchargeResult)//~9413I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ScoreDlg.newInstance amt="+Arrays.toString(Pammount));//~9321I~
        if (Dump.Y) Dump.println("ScoreDlg.newInstance score="+Arrays.toString(Pscore));//~9321I~
        if (Dump.Y) Dump.println("ScoreDlg.newInstance pay="+Arrays.toString(PminusPay));//~9321I~
        if (Dump.Y) Dump.println("ScoreDlg.newInstance newtotal="+Arrays.toString(PnewTotal));//~9321I~
//      if (Dump.Y) Dump.println("ScoreDlg.newInstance newtotal="+Arrays.toString(PpayerInfo));//~9322I~//~9408R~
        if (Dump.Y) Dump.println("ScoreDlg.newInstance charge="+Arrays.toString(Pcharge));//~9408I~
        if (Dump.Y) Dump.println("ScoreDlg.newInstance chargeResult="+Arrays.toString(PchargeResult));//~9408I~
//  	ScoreDlg dlg=newInstance(Pammount,Pscore);                 //~9321R~//~9413R~
    	ScoreDlg dlg=newInstance(PendgameType,Pammount,Pscore);    //~9413I~
        dlg.swReceived=true;                                       //~9321I~
        dlg.minusPay=PminusPay;                                    //~9321I~
        dlg.newTotal=PnewTotal;                                    //~9321I~
//      dlg.payerInfo=PpayerInfo;                                  //~9322I~//~9408R~
        dlg.chargeToGainer=Pcharge;                                //~9408I~
        dlg.chargeToGainerResult=PchargeResult;                    //~9408I~
        return dlg;                                                //~9321I~
    }                                                              //~9321I~
//    //******************************************                   //~v@@@M~//~9303R~//~9312R~//~9322R~
//    @Override                                                      //~9221I~//~9303R~//~9312R~//~9322R~
//    public void onPause()                                          //~9221I~//~9303R~//~9312R~//~9322R~
//    {                                                              //~9221I~//~9303R~//~9312R~//~9322R~
//        super.onPause();                                         //~9302R~//~9303R~//~9312R~//~9322R~
//        if (Dump.Y) Dump.println("ScoreDlg:onPause issue dismiss");//~9221R~//~9302R~//~9303R~//~9304R~//~9312R~//~9322R~
//        dismiss();  //because hard to make Complete.Status.ammount to parcelable//~9221I~//~9302R~//~9303R~//~9312R~//~9322R~
//    }                                                              //~9221I~//~9303R~//~9312R~//~9322R~
    //******************************************                 //~9303R~//~9312R~
    @Override                                                    //~9303R~//~9305R~
    protected void initLayoutAdditional(View PView)                            //~v@@@I~//~9303R~//~9305R~//~9321R~
    {                                                              //~v@@@M~//~9303R~//~9305R~
        if (Dump.Y) Dump.println("ScoreDlg.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9303R~//~9304R~//~9305R~//~9307R~//~9312R~
//      super.initLayout(PView);                                   //~9316I~//~9321R~
//      androidDlg=getDialog();                                    //~v@@@I~//~9302R~//~9312I~//~9321R~
        if (Dump.Y) Dump.println("ScoreDlg:initLayout ammount="+Arrays.toString(ammount));//~9320I~
        if (Dump.Y) Dump.println("ScoreDlg:initLayout score="+Arrays.toString(ACC.score));//no minus status//~9320I~//~9322R~
        swInitLayout=true;                                         //~9322I~
        getRuleSetting();                                          //~9320I~
        setupAmmount(PView);                                       //~9312I~
        setupMinus(PView);                                         //~9320I~
        setAccountName();                                          //~9309I~
	    showAmmount();                                             //~9309I~
        if (!swReceived)                                           //~9402I~
	        swInitLayout=false;	//activate chkbox change           //~9402I~
//        showMinus();                                               //~9320I~//~9408R~
        swInitLayout=false;                                        //~9322I~
        hideResponseEswn();                                        //~0218I~
    }                                                              //~v@@@M~//~9303R~//~9305R~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    protected void setupValueAdditional(View PView)                //~9321I~
    {                                                              //~9321I~
    	if (Dump.Y) Dump.println("ScoreDlg.setupValueAdditional");              //~9321I~//~9521R~
//  	accountNames=ACC.getAccountNames();               //~9312I~//~9321M~//~9322R~//~9416R~
    	accountNames=ACC.getAccountNamesByPosition();              //~9416I~
        btnTotal        =              UButton.bind(PView,R.id.FixTotal,this);//~9321M~
        btnShowRule     =              UButton.bind(PView,R.id.ShowRule,this);//~9417I~
	    saveLast();                                               //~9322I~//~9413R~
    }                                                              //~9322I~
    //******************************************                   //~0218I~
    private void hideResponseEswn()                                //~0218I~
    {                                                              //~0218I~
    	if (Dump.Y) Dump.println("ScoreDlg.hideResponseEswn");     //~0218I~
        hideResponseEswn(!isDealer());                             //~0218I~
    }                                                              //~0218I~
    //******************************************                   //~9322I~
    private void saveLast()                                       //~9322I~
    {                                                              //~9322I~
    	if (Dump.Y) Dump.println("ScoreDlg.saveLast");             //~9521I~
        CMP.lastMinusAmmount=ammount;	//save for reshow          //~9322R~
        CMP.lastMinusScore=minusScore;	//save for reshow          //~9322R~
        CMP.lastMinusPay=minusPay;	//save for reshow              //~9322R~
        CMP.lastMinusTotal=newTotal;	//save for reshow          //~9322R~
//      CMP.lastMinusPayerInfo=newTotal;	//save for reshow      //~9322I~//~9408R~
        CMP.lastMinusCharge=chargeToGainer;                        //~9408I~
        CMP.lastMinusChargeResult=chargeToGainerResult;            //~9408I~
        CMP.lastEndGameType=parmEndGameType;                       //~9413I~
    }                                                              //~9321I~
    //******************************************                   //~9521I~
    private static void saveLastWithoutScoreDlg(int PendgameType)  //~9521R~
    {                                                              //~9521I~
    	if (Dump.Y) Dump.println("ScoreDlg.saveLastWidthoutScoreDlg endgameType="+PendgameType);//~9521I~
        AG.aComplete.lastEndGameType=PendgameType;                          //~9521R~
    }                                                              //~9521I~
    //******************************************                   //~9818I~
    @Override //UFDlg                                              //~9818I~
    protected int getDialogWidth()                                 //~9818I~
    {                                                              //~9818I~
        int ww=0;                                                  //~9818I~
//      if (AG.swSmallDevice && AG.portrait)                       //~9818I~//~0218R~
        if (AG.swSmallDevice)                                      //~0218I~
        {                                                          //~9818I~
          if (AG.portrait)                                         //~0218I~
            ww=(int)(AG.scrWidth*RATE_SMALLDEVICE_WIDTH);          //~9818I~
          else                                                     //~0218I~
            ww=(int)(AG.scrWidth*RATE_SMALLDEVICE_WIDTH_LANDSCAPE); //~0218I~
        }                                                          //~9818I~
        if (Dump.Y) Dump.println("ScoreDlg.getDialogWidth ww="+ww+",portrait="+AG.portrait+",smallDevice="+AG.swSmallDevice);//~9818I~
        return ww;                                                 //~9818I~
    }                                                              //~9818I~
    //******************************************                   //~v@@@I~//~9220R~//~9303R~//~9306R~
    protected void setTitle()                                        //~v@@@I~//~9220R~//~9303R~//~9306R~
    {                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
        Spanned s=Status.getSpannedGameTitle(Utils.getStr(TITLEID));//~9306I~
        androidDlg.setTitle(s);                                    //~v@@@R~//~9220R~//~9302R~//~9303R~//~9306R~
    }                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
    //******************************************                   //~9320I~
    private void getRuleSetting()                                  //~9320I~
    {                                                              //~9320I~
    	swMinusStop=RuleSetting.isMinusStop();  //syop yes/no                   //~9320I~//~9818R~
    	swMinus0=RuleSetting.isZeroStop();                         //~9320I~
//  	swMinusRobot=RuleSetting.isMinusStopRobot();                //~9320I~//~9429R~
    	swMinusPay=RuleSetting.isMinusPay();  //minuspay type                     //~9320I~//~9322R~//~9818R~
    	swMinusPayGetAllPoint=RuleSetting.isMinusPayGetAllPoint(); //~9402I~
//  	swMinusCharge=!swMinusPay && swMinusPayGetAllPoint;        //~9408R~//~9415R~
//  	swMinusCharge=!swMinusPay;                                 //~9415I~//~9423R~
    	swMinusCharge=swMinusStop;                                 //~9423I~
    	pointMinusStop=RuleSetting.getPointMinusStop();           //~9408I~
    	caseMinusStopByErr=RuleSetting.getMinusStopByErr();        //~9414I~
        if (Dump.Y) Dump.println("ScoreDlg:getRuleSetting swMinusPay="+swMinusPay+",swMinusPayGetAllPoint="+swMinusPayGetAllPoint);//~9415I~//~1209R~
    }                                                              //~9320I~
    //******************************************                   //~9309I~
    protected void setupAmmount(View PView)                        //~9309I~
    {                                                              //~9309I~
        LinearLayout[] lls=new LinearLayout[PLAYERS];              //~9321I~
        lls[0]=(LinearLayout)    UView.findViewById(PView,R.id.llResultE);//~9309I~//~9321R~
        lls[1]=(LinearLayout)    UView.findViewById(PView,R.id.llResultS);//~9321I~
        lls[2]=(LinearLayout)    UView.findViewById(PView,R.id.llResultW);//~9321I~
        lls[3]=(LinearLayout)    UView.findViewById(PView,R.id.llResultN);//~9321I~
        tvsEswn=new TextView[PLAYERS];                             //~9321I~
    	tvsName=new TextView[PLAYERS];                             //~9321I~
    	tvsAdd=new TextView[PLAYERS];                              //~9321I~
    	tvsScore=new TextView[PLAYERS];                            //~9321I~
    	tvsPay=new TextView[PLAYERS];                              //~9321I~
    	tvsTotal=new TextView[PLAYERS];                            //~9321I~
    	tvsMinusCharge=new TextView[PLAYERS];                      //~9408I~
//  	tvsMinusChargeResult=new TextView[PLAYERS];                //~9408R~//~9415R~
    	tvsMinusChargeLater=new TextView[PLAYERS];                 //~9415I~
    	llsMinusPay=new LinearLayout[PLAYERS];                     //~9416I~
    	llsMinusPay=new LinearLayout[PLAYERS];                     //~9416I~
    	llsMinusChargeLater=new LinearLayout[PLAYERS];             //~9416I~
        for (int ii=0;ii<PLAYERS;ii++)                                 //~9321I~
        {                                                          //~9321I~
        	LinearLayout ll=lls[ii];                               //~9321I~
	        tvsEswn[ii]=(TextView)    UView.findViewById(ll,R.id.nameESWN);//~9321I~
	        tvsName[ii]=(TextView)    UView.findViewById(ll,R.id.memberName);//~9321I~
	        tvsAdd[ii]=(TextView)     UView.findViewById(ll,R.id.addTotal);//~9321I~
	        tvsScore[ii]=(TextView)   UView.findViewById(ll,R.id.newScore);//~9321I~//~9414R~
	        tvsPay[ii]=(TextView)     UView.findViewById(ll,R.id.minusPay);//~9321I~
			if (!OKNGDlg.isDealer())                               //~9322I~
		        tvsPay[ii].setFocusable(false);                    //~9322I~
	        tvsTotal[ii]=(TextView)     UView.findViewById(ll,R.id.newTotal);//~9321I~
            LinearLayout llCharge=(LinearLayout)    UView.findViewById(ll,R.id.llMinusPayCharge);//~9408R~
            tvsMinusCharge[ii]=(TextView)     UView.findViewById(ll,R.id.minusCharge);//~9408I~
//          tvsMinusChargeResult[ii]=(TextView)     UView.findViewById(ll,R.id.tvMinusChargeTotal);//~9408R~//~9415R~
            tvsMinusChargeLater[ii]=(TextView)      UView.findViewById(ll,R.id.tvMinusChargeLater);//~9415I~
            if (!swMinusCharge)                                    //~9408I~
            	llCharge.setVisibility(View.GONE);                 //~9408I~
            llsMinusPay[ii]=(LinearLayout)  UView.findViewById(ll,R.id.llMinusPay);//~9416I~
            llsMinusChargeLater[ii]=(LinearLayout)  UView.findViewById(ll,R.id.llMinusChargeLater);//~9416I~
        }                                                          //~9321I~
    }                                                              //~9309I~
    //******************************************                   //~9320I~
    protected void setupMinus(View PView)                          //~9320I~
    {                                                              //~9320I~
////        llsMinus[0]=(LinearLayout)    UView.findViewById(PView,R.id.MinusEswn1);//~9320R~//~9408R~//~9520R~
////        llsMinus[1]=(LinearLayout)    UView.findViewById(PView,R.id.MinusEswn2);//~9320I~//~9408R~//~9520R~
////        llsMinus[2]=(LinearLayout)    UView.findViewById(PView,R.id.MinusEswn3);//~9320I~//~9408R~//~9520R~
////        llsMinus[3]=(LinearLayout)    UView.findViewById(PView,R.id.MinusEswn4);//~9320I~//~9408R~//~9520R~
////        for (int ii=0;ii<PLAYERS;ii++)                                 //~9320I~//~9408R~//~9520R~
////        {                                                          //~9320I~//~9408R~//~9520R~
////            LinearLayout ll=llsMinus[ii];                               //~9320I~//~9408R~//~9520R~
////            tvsPayer[ii]=(TextView)  UView.findViewById(ll,R.id.tvPayer);//~9320I~//~9408R~//~9520R~
////            UCheckBox cb1=new UCheckBox(ll,R.id.cbPayToE);         //~9320I~//~9408R~//~9520R~
////            UCheckBox cb2=new UCheckBox(ll,R.id.cbPayToS);         //~9320I~//~9408R~//~9520R~
////            UCheckBox cb3=new UCheckBox(ll,R.id.cbPayToW);         //~9320I~//~9408R~//~9520R~
////            UCheckBox cb4=new UCheckBox(ll,R.id.cbPayToN);         //~9320I~//~9408R~//~9520R~
////            cb1.setListener(this,UCBP_MINUSSTOP+ii*PLAYERS+0);     //~9322I~//~9408R~//~9520R~
////            cb2.setListener(this,UCBP_MINUSSTOP+ii*PLAYERS+1);     //~9322I~//~9408R~//~9520R~
////            cb3.setListener(this,UCBP_MINUSSTOP+ii*PLAYERS+2);     //~9322I~//~9408R~//~9520R~
////            cb4.setListener(this,UCBP_MINUSSTOP+ii*PLAYERS+3);     //~9322I~//~9408R~//~9520R~
////            cbssPayTo[ii]=new UCheckBox[]{cb1,cb2,cb3,cb4};             //~9320I~//~9408R~//~9520R~
////        }                                                          //~9320I~//~9408R~//~9520R~
//        UCheckBox cbMinusStop=new UCheckBox(PView,R.id.cbMinusStop);//~9320R~//~9408R~//~9414R~//~9520R~
//        UCheckBox cbMinus0=new UCheckBox(PView,R.id.cbMinus0);     //~9320R~//~9414R~//~9520R~
////      UCheckBox cbMinusPay=new UCheckBox(PView,R.id.cbMinusPay); //~9320R~//~9402R~//~9520R~
////      UCheckBox cbMinusRobot=new UCheckBox(PView,R.id.cbMinusRobot);//~9320R~//~9414R~//~9429R~//~9520R~
//        TextView tvPointMinusStop=(TextView)    UView.findViewById(PView,R.id.tvPointMinusStop);//~9320I~//~9520R~
////      tvSetPayer=(TextView)    UView.findViewById(PView,R.id.tvSetPayer);//~9402I~//~9429R~//~9520R~
//        rgMinusPay=new URadioGroup(layoutView,R.id.rgMinusPay,0,rbIDMinusPay);//~9402I~//~9520R~
//        int mpid=swMinusPay ? 0 : (swMinusPayGetAllPoint ? 1 : 2); //~9402I~//~9520R~
//        rgMinusPay.setCheckedID(mpid,true/*swFixed*/);             //~9402I~//~9520R~
//        URadioGroup rg=new URadioGroup(layoutView,R.id.rgMinusStopByErr,0,rbIDMinusStopByErr);//~9414I~//~9520R~
//        rg.setCheckedID(caseMinusStopByErr,true/*swFixed*/);//~9414I~//~9520R~
//                                                                   //~9320I~//~9520R~
//        cbMinusStop.setState(swMinusStop,true/*swFixed*/);         //~9320I~//~9408R~//~9414R~//~9520R~
//        cbMinus0.setState(swMinus0,true/*swFixed*/);               //~9320I~//~9520R~
// //     cbMinusPay.setState(swMinusPay,true/*swFixed*/);           //~9320I~//~9520R~
////      cbMinusRobot.setState(swMinusRobot,true/*swFixed*/);       //~9320I~//~9429R~//~9520R~
//        LinearLayout llSB=(LinearLayout)UView.findViewById(PView,R.id.llSBMinusPrize);//~9414I~//~9520R~
//        llSB.setVisibility(View.GONE);                             //~9414I~//~9520R~
//        tvPointMinusStop.setText(Integer.toString(pointMinusStop));//~9320I~//~9520R~
//        tvPointMinusStop.setVisibility(View.VISIBLE);              //~9414I~//~9520R~
//                                                                   //~9321I~//~9520R~
////        cbssPayTo[0][0].checkbox.requestFocus();    //to avoid kbd up                               //~1403I~//~v@@@R~//~9321I~//~9408R~//~9520R~
////        for (int ii=0;ii<PLAYERS;ii++)                             //~9322I~//~9408R~//~9520R~
////            for (int jj=0;jj<PLAYERS;jj++)                         //~9322I~//~9408R~//~9520R~
////                if (OKNGDlg.isDealer())                            //~9322I~//~9408R~//~9520R~
////                    cbssPayTo[ii][jj].setState(payerInfo[ii*PLAYERS+jj]!=0);//~9322R~//~9408R~//~9520R~
////                else                                               //~9322I~//~9408R~//~9520R~
////                    cbssPayTo[ii][jj].setState(payerInfo[ii*PLAYERS+jj]!=0,true/*fixed*/);//~9322I~//~9408R~//~9520R~
//  	if (PrefSetting.isNoRelatedRule())                         //~9520R~//~9607R~//~9708R~
//      	((LinearLayout)UView.findViewById(PView,R.id.llRelatedRule)).setVisibility(View.GONE);//~9520R~//~9607R~//~9708R~
//      else                                                       //~9520R~//~9607R~//~9708R~
        	RuleSetting.setMinusStop(PView,true/*swFixed*/);                             //~9520I~
    }                                                              //~9320I~
    //******************************************                   //~9309I~
    protected void setAccountName()                                //~9309I~
    {                                                              //~9309I~
    	if (Dump.Y) Dump.println("ScoreDlg.setAccountName");    //~9309I~//~9316R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9309I~
        {                                                          //~9309I~
        	tvsName[ii].setText(accountNames[ii]);                 //~9309I~
//          int eswn = ACC.getCurrentEswnByAccount(ii);   //~9320I~//~9322R~//~9416R~
            int eswn = ACC.getCurrentEswnByPosition(ii);           //~9416I~
	        tvsEswn[ii].setText(GConst.nameESWN[eswn]); //~9312I~  //~9320R~
        }                                                          //~9309I~
    }                                                              //~9309I~
    //******************************************                   //~9309I~
    private void showAmmount()                                     //~9309I~
    {                                                              //~9309I~
    	if (swReceived)                                            //~9321I~
        {                                                          //~9321I~
		    showAmmountReceived();                                 //~9321I~
            return;                                                //~9321I~
        }                                                          //~9321I~
//        int[] total=AG.aAccounts.score;                              //~9312I~//~9320R~
        Arrays.fill(newTotal,0);                                   //~9321I~
        Arrays.fill(oldTotal,0);                                   //~9416I~
//      Arrays.fill(minusPay,0);                                   //~9321R~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9309I~
        {                                                          //~9309I~
//            int idx=ACC.currentEswnToMember(ii);          //~9316M~//~9322R~//~9416R~
//            if (Dump.Y) Dump.println("ScoreDlg:showAmmount ii="+ii+",idx="+idx);//~9402I~//~9416R~
//            tvsAdd[idx].setText(Integer.toString(ammount[ii]));                       //~9312I~//~9316R~//~9416R~
//            oldTotal[idx]-=ammount[ii];                            //~9402I~//~9416R~
//            tvsScore[ii].setText(Integer.toString(minusScore[ii]));//~9321I~//~9416R~
//            oldTotal[ii]+=minusScore[ii];                           //~9402I~//~9416R~
//            tvsPay[ii].setText(Integer.toString(minusPay[ii]));    //~9321I~//~9322R~
//            newTotal[ii]=minusScore[ii]+minusPay[ii];              //~9321R~//~9322R~
//            tvsTotal[ii].setText(Integer.toString(newTotal[ii]));   //~9321I~//~9322R~
            int eswn=ACC.getCurrentEswnByPosition(ii);             //~9416I~
            if (Dump.Y) Dump.println("ScoreDlg:showAmmount ii="+ii+",eswn="+eswn);//~9416I~
            tvsAdd[ii].setText(Integer.toString(ammount[eswn]));   //~9416I~
            oldTotal[ii]-=ammount[eswn];                           //~9416I~
//          tvsScore[ii].setText(Integer.toString(minusScore[eswn]));//~9416I~//~9606R~
            tvsScore[ii].setText(Integer.toString(minusScore[ii]));    //account seq//~9606I~
//          oldTotal[ii]+=minusScore[eswn];                        //~9416I~//~9607R~
            oldTotal[ii]+=minusScore[ii];                          //~9607I~
        }                                                          //~9309I~
        updateTotal();                                             //~9322I~
        if (Dump.Y) Dump.println("ScoreDlg:showAmmount score="+Arrays.toString(minusScore)+",amt="+Arrays.toString(ammount)+",new="+Arrays.toString(newTotal));//~9316I~//~9321I~
        if (Dump.Y) Dump.println("ScoreDlg:showAmmount oldTotal="+Arrays.toString(oldTotal));//~9402I~
//        showNewTotal();                                          //~9321R~
    }                                                              //~9309I~
    //******************************************                   //~9322I~
    private void updateTotal()                                     //~9322I~
    {                                                              //~9322I~
        Arrays.fill(newTotal,0);                                   //~9322I~//~9403M~
//      if (!swMinusPay)	//cut minus                            //~9403R~
        if (parmEndGameType==EGDR_DRAWN_LAST)   //drawnLast or drawnHW //~9413I~//~9426R~
	        adjustMinusPayLimitDrawn();                            //~9413I~
        else                                                       //~9426I~
        if (parmEndGameType==EGDR_DRAWN_HW)   //drawnLast or drawnHW//~9426R~
	        adjustMinusPayLimitDrawnHW();                          //~9426I~
        else                                                       //~9413I~
	        adjustMinusPayLimit();  //set newTotal                 //~9403I~//~9415R~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9322I~
        {                                                          //~9322I~
        	int mp=minusPay[ii];                                   //~9416I~
//          if (mp!=0)                                             //~9416I~//~9813R~
		        tvsPay[ii].setText(Integer.toString(mp));    //~9322I~//~9416R~
//          else                                                   //~9416I~//~9813R~
//  	        llsMinusPay[ii].setVisibility(View.GONE);          //~9416I~//~9813R~
    		if (!swMinusStop)                                      //~9818I~
				llsMinusPay[ii].setVisibility(View.GONE);          //~9818I~
//            int minus=minusScore[ii];                              //~9322I~//~9403R~
////          if (minus<0 && !swMinusPay)                            //~9322I~//~9402R~//~9403R~
//            if (minus<0 && !swMinusPay && !swMinusPayGetAllPoint)  //~9402I~//~9403R~
//                minus=0;                                           //~9322I~//~9403R~
//            newTotal[ii]=minus+minusPay[ii];                       //~9322R~//~9403R~
//          newTotal[ii]+=minusPay[ii];                            //~9403I~//~9415R~
	        if (!swMinusPayGetAllPoint)	//point is pay up to 0     //~9415I~
            {                                                      //~9415I~
            	newTotal[ii]=minusScore[ii]+chargeToGainer[ii];    //~9415R~
            	tvsMinusCharge[ii].setText(Integer.toString(chargeToGainer[ii]));//~9408R~//~9415I~
            }                                                      //~9415I~
          	else                                                   //~9415I~
            {                                                      //~9415I~
            	newTotal[ii]=minusScore[ii];                       //~9415I~
            	int mc=chargeToGainer[ii];                         //~9416R~
                if (mc!=0)                                         //~9416I~
	            	tvsMinusChargeLater[ii].setText(Integer.toString(mc));//~9416I~
                else                                               //~9416I~
	            	tvsMinusChargeLater[ii].setVisibility(View.GONE);//~9416I~
            }                                                      //~9415I~
            tvsTotal[ii].setText(Integer.toString(newTotal[ii]));  //~9322I~
//          chargeToGainerResult[ii]=minusScore[ii]+chargeToGainer[ii]+minusPay[ii];//~9408R~//~9415R~
            chargeToGainerResult[ii]=minusScore[ii]+chargeToGainer[ii];//~9415I~
//          if (!swMinusPayGetAllPoint)	//point is pay up to 0     //~9415R~
//          {                                                      //~9415R~
//          	tvsMinusChargeResult[ii].setVisibility(View.GONE); //~9415R~
//          }                                                      //~9415R~
//          else                                                   //~9415R~
//          {                                                      //~9415R~
//          	tvsMinusChargeResult[ii].setText(Integer.toString(chargeToGainerResult[ii]));//~9408R~//~9415R~
//          }                                                      //~9415R~
        }                                                          //~9322I~
        if (Dump.Y) Dump.println("ScoreDlg:updateTotal score="+Arrays.toString(minusScore)+",minusPay="+Arrays.toString(minusPay)+",new="+Arrays.toString(newTotal));//~9322I~//~9414R~
        if (Dump.Y) Dump.println("ScoreDlg:updateTotal chargeToGainer="+Arrays.toString(chargeToGainer)+",chargeToGainerResult="+Arrays.toString(chargeToGainerResult));//~9415I~
    }                                                              //~9322I~
    //**************************************************************************//~9413I~
    //*cut minus pay,determin payTo when multiple Ron under swMinusPay=false//~9413I~
    //**************************************************************************//~9413I~
    private void adjustMinusPayLimitDrawn()                        //~9413I~//~9420R~
    {                                                              //~9413I~
        if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimitDrawn swMinusPay="+swMinusPay+",swMinusPayGetAllPoint="+swMinusPayGetAllPoint+",swMinus0="+swMinus0);//~9420R~
        int[] adjustedScore=new int[PLAYERS];                      //~9422I~
        System.arraycopy(CMP.adjustedScoreDrawn,0,adjustedScore,0,PLAYERS);//~9422R~
        System.arraycopy(CMP.chargeToGainerDrawn,0,chargeToGainer,0,PLAYERS);//~9422R~
        System.arraycopy(CMP.minusPrizeDrawn,0,minusPay,0,PLAYERS);  //~9422R~
		if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimitDrawn adjustedScore="+Arrays.toString(adjustedScore));//~9422I~
		if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimitDrawn chargeToGainer="+Arrays.toString(chargeToGainer));//~9422I~
		if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimitDrawn minusPay="+Arrays.toString(minusPay));//~9422I~
        minusPrizeByError(adjustedScore,minusPay);                 //~9420I~
        System.arraycopy(adjustedScore,0,newTotal,0,PLAYERS);      //~9420I~
		if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimitDrawn newTotal="+Arrays.toString(newTotal));//~9420R~
    }                                                              //~9413I~
    //**************************************************************************//~9426I~
    private void adjustMinusPayLimitDrawnHW()                      //~9426I~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimitDrawnHW swMinusPay="+swMinusPay+",swMinusPayGetAllPoint="+swMinusPayGetAllPoint+",swMinus0="+swMinus0);//~9426I~
        int[] adjustedScore=new int[PLAYERS];                      //~9426I~
        System.arraycopy(CMP.adjustedScoreDrawn,0,adjustedScore,0,PLAYERS);//~9426I~
//      System.arraycopy(CMP.chargeToGainerDrawn,0,chargeToGainer,0,PLAYERS);//~9426I~
//      System.arraycopy(CMP.minusPrizeDrawn,0,minusPay,0,PLAYERS);//~9426I~
		if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimitDrawn adjustedScore="+Arrays.toString(adjustedScore));//~9426I~
//  	if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimitDrawn chargeToGainer="+Arrays.toString(chargeToGainer));//~9426I~
//  	if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimitDrawn minusPay="+Arrays.toString(minusPay));//~9426I~
        minusPrizeByError(adjustedScore,minusPay);                 //~9426I~
        System.arraycopy(adjustedScore,0,newTotal,0,PLAYERS);      //~9426I~
		if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimitDrawnHW newTotal="+Arrays.toString(newTotal));//~9426I~
    }                                                              //~9426I~
    //**************************************************************************//~9403I~
    //*cut minus pay,determin payTo when multiple Ron under minusStop=true && minusPay=false//~9403I~//~9414R~
    //**************************************************************************//~9403I~
    private void adjustMinusPayLimit()                             //~9403I~
    {                                                              //~9403I~
        if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit swMinusPay="+swMinusPay+",swMinusPayGetAllPoint="+swMinusPayGetAllPoint+",swMinus0="+swMinus0);//~9403R~
    	Complete.Status[] stats=AG.aComplete.sortStatusS();        //~9403I~
        int[] payTo=new int[PLAYERS];       //account idx seq      //~9403I~
        int[] adjustedScore=minusScore.clone(); //index seq        //~9403R~
        Arrays.fill(chargeToGainer,0);                             //~9408I~
		if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit oldTotal="+Arrays.toString(oldTotal));//~9403I~
		if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit newTotal="+Arrays.toString(newTotal));//~9403I~
		if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit minusScore="+Arrays.toString(minusScore));//~9403I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9403I~
        {                                                          //~9403I~
            int minus=minusScore[ii];                              //~9403I~
            if (minus>0 || (minus==0 && !swMinus0))                //~9403I~
            	continue;                                          //~9403I~
            int old=oldTotal[ii];                                  //~9403I~
//          int eswn=ACC.getCurrentEswnByAccount(ii);            //~9403I~//~9414R~//~9416R~
            int eswn=ACC.getCurrentEswnByPosition(ii);                //~9416I~
		    if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit ii="+ii+",eswn="+eswn+",old="+old);//~9403I~
            int remain=old;                                        //~9403I~
            boolean sw1st=true;                                    //~9403I~
            for (Complete.Status stat:stats)                       //~9403I~
            {                                                      //~9403I~
            	int pay=getLooserPay(eswn,stat);                   //~9403R~
                remain+=pay;      //pay:minus                      //~9403R~
		        if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit pay="+pay+",remain="+remain);//~9403I~
	            if (remain<0 || (remain==0 && swMinus0))           //~9403I~
                {                                                  //~9403I~
                	int compEswn=stat.completeEswn;                //~9403I~
                    if (sw1st)                                     //~9403I~
//                  	payTo[eswn]=compEswn+1;                    //~9403R~
                    	payTo[ii]=compEswn+1;                      //~9403I~
			        if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit ii="+ii+",payTo="+payTo[ii]+",compEswn="+compEswn);//~9403I~
//                  int idx=ACC.currentEswnToMember(compEswn);     //~9403I~//~9416R~
                    int idx=ACC.currentEswnToPosition(compEswn);   //~9416I~
                    minusPay[idx]+=pointMinusStop;                 //~9408I~
                    minusPay[ii]-=pointMinusStop;                  //~9408I~
					if (!swMinusPay)	//not pay all point        //~9408I~
                    {                                              //~9408I~
                        if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit swAllpoint="+swMinusPayGetAllPoint+",before ii="+ii+",adjustedScore[ii]="+adjustedScore[ii]+",idx="+idx+",adjustedScore[idx]="+adjustedScore[idx]);//~9403R~//~9408I~
                        if (!swMinusPayGetAllPoint)	//point is pay up to 0//~9408I~
                        {                                              //~9403I~//~9408R~
                            if (sw1st)                                 //~9403I~//~9408R~
                            {                                          //~9403I~//~9408R~
                                adjustedScore[idx]+=remain; //subtract from gainer//~9403R~//~9408R~
                                adjustedScore[ii]-=remain;  //add to looser//~9403R~//~9408R~
                                chargeToGainer[idx]+=remain; //subtract from gainer//~9415I~
                                chargeToGainer[ii]-=remain; //subtract from gainer//~9415I~
                            }                                          //~9403I~//~9408R~
                            else                                       //~9403I~//~9408R~
                            {                                          //~9403I~//~9408R~
                                adjustedScore[idx]+=pay;    //subtruct from gainer//~9403R~//~9408R~
                                adjustedScore[ii]-=pay;     //add to looser//~9403R~//~9408R~
                                chargeToGainer[idx]+=pay; //subtract from gainer//~9415I~
                                chargeToGainer[ii]-=pay; //subtract from gainer//~9415I~
                            }                                          //~9403I~//~9408R~
                            if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit after  ii="+ii+",adjustedScore[ii]="+adjustedScore[ii]+",idx="+idx+",adjustedScore[idx]="+adjustedScore[idx]);//~9403R~//~9408R~
                        }                                              //~9403I~//~9408R~
                        else	//point all, but pay is limitted and minus charge to gainer//~9408I~
                        {                                          //~9408I~
                            if (sw1st)                             //~9408I~
                            {                                      //~9408I~
                                chargeToGainer[idx]+=remain; //subtract from gainer//~9408R~
                                chargeToGainer[ii]-=remain; //subtract from gainer//~9408I~
//                              adjustedScore[ii]-=remain;  //add to looser//~9408I~//~9414R~
                            }                                      //~9408I~
                            else                                   //~9408I~
                            {                                      //~9408I~
                                chargeToGainer[idx]+=pay; //subtract from gainer//~9408I~
                                chargeToGainer[ii]-=pay; //subtract from gainer//~9408I~
//                              adjustedScore[ii]-=pay;     //add to looser//~9408I~//~9414R~
                            }                                      //~9408I~
                            if (Dump.Y) Dump.println("ScoreDlg:sw1ST="+sw1st+",remain="+remain+",pay="+pay);//~9414R~
                            if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit after  ii="+ii+",adjustedScore[ii]="+adjustedScore[ii]+",idx="+idx+",chargeToGainer[idx]="+chargeToGainer[idx]);//~9414I~
                        }                                          //~9408I~
                    }                                              //~9408I~
                    sw1st=false;                                   //~9403I~
                }                                                  //~9403I~
            }                                                      //~9403I~
        }                                                          //~9403I~
        minusPrizeByError(adjustedScore,minusPay);                    //~9414I~//~9415R~
//      System.arraycopy(payTo,0,eswnMinusPrizeTo,0,PLAYERS);      //~9403R~//~9415R~
        System.arraycopy(adjustedScore,0,newTotal,0,PLAYERS);      //~9403R~
		if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit newTotal="+Arrays.toString(newTotal));//~9403R~//~9415R~
		if (Dump.Y) Dump.println("ScoreDlg:AdjustminusPayLimit chargeToGainer="+Arrays.toString(chargeToGainer));//~9408I~
    }                                                              //~9403I~
    //**************************************************************************//~9414I~
    //*set minusPrize by error looser                              //~9414I~
    //**************************************************************************//~9414I~
    private void minusPrizeByError(int[] PadjustedScore,int[] PminusPay)//~9414I~//~9415R~
    {                                                              //~9414I~
    	boolean[] swsErr=CMP.swsErrLooser;                           //~9414I~
        if (Dump.Y) Dump.println("ScoreDlg:minusPrizeByErr case="+caseMinusStopByErr+",swsErrLooser="+Arrays.toString(swsErr));//~9414I~
		if (Dump.Y) Dump.println("ScoreDlg:minusPrizeByErr before PadjustedScore="+Arrays.toString(PadjustedScore));//~9414I~//~9415R~//~9424R~
		if (Dump.Y) Dump.println("ScoreDlg:minusPrizeByErr before minusPrize=="+Arrays.toString(PminusPay));//~9415I~//~9424R~
        if (caseMinusStopByErr==MINUSSTOP_BYERR_NO)                //~9414I~
        	return;                                                //~9414I~
        int[] minusPrizeByErr=new int[PLAYERS];                    //~9414I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9414I~
        {                                                          //~9414I~
            int minusPrize=minusPay[ii];                           //~9414I~
            int score=PadjustedScore[ii];                           //~9414I~
//          int eswn=ACC.getCurrentEswnByAccount(ii);              //~9414I~//~9416R~
            int eswn=ACC.getCurrentEswnByPosition(ii);                //~9416I~
            boolean swErr=swsErr[eswn];                             //~9414I~
			if (Dump.Y) Dump.println("ScoreDlg:minusPrizeByErr ii="+ii+",minusPrize="+minusPrize+",eswn="+eswn+",swErr="+swErr+",adjustedScore="+score);//~9414I~
            if (swErr && minusPrize>=0                             //~9414I~
	        &&  (score<0 || (score==0 && swMinus0))                 //~9414I~
			)                                                      //~9414I~
            {                                                      //~9414I~
            	int pt;                                            //~9414I~
		        if (caseMinusStopByErr==MINUSSTOP_BYERR_SPRIT)     //~9414I~
                	pt=pointMinusStop/3;                           //~9414I~
                else                                               //~9414I~
                	pt=pointMinusStop;                             //~9414I~
                for (int jj=0;jj<PLAYERS;jj++)                         //~9414I~
                {                                                  //~9414I~
                	if (jj==ii)                                    //~9414I~
                    	minusPrizeByErr[jj]-=pt*3;                 //~9414I~
                    else                                           //~9414I~
                    	minusPrizeByErr[jj]+=pt;                   //~9414I~
                }                                                  //~9414I~
            }                                                      //~9414I~
        }                                                          //~9414I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9414I~
        {                                                          //~9414I~
	        PminusPay[ii]+=minusPrizeByErr[ii];                //~9414I~//~9415R~
        }                                                          //~9414I~
		if (Dump.Y) Dump.println("ScoreDlg:minusPrizeByErr after="+Arrays.toString(PminusPay));//~9414I~//~9415R~
    }                                                              //~9414I~
    //******************************************************                   //~9403I~//~9414R~
    //*get point looserEswn pay to completeEswn                    //~9414I~
    //******************************************************       //~9414I~
    private int getLooserPay(int PeswnLooser,Complete.Status Pstat)//~9403I~
    {                                                              //~9403I~
        int[][] payss=CMP.amtsPayedToEswn;                         //~9403I~
    	int compEswn=Pstat.completeEswn;                           //~9403I~
        int[] pays=payss[compEswn];                                //~9403I~
        int pay=pays[PeswnLooser];                                 //~9403I~
//        if (swNoPayMinusError)  //no transfer pay minus by error //~9414R~
//        {                                                        //~9414R~
//            int[][] payerr=CMP.amtsError;   //gainer:looser      //~9414R~
//            int err=payerr[compEswn][PeswnLooser];               //~9414R~
//            if (Dump.Y) Dump.println("ScoreDlg:getLooserPay err="+err+",payerr[compEswn]="+Arrays.toString(payerr[compEswn]));//~9414R~
//            pay+=err;                                            //~9414R~
//        }                                                        //~9414R~
        if (Dump.Y) Dump.println("ScoreDlg:getLooserPay looser="+PeswnLooser+",completeEswn="+compEswn+",pays="+Arrays.toString(pays)+",pay="+pay);//~9403R~//~9414R~
        return pay;                                                //~9403I~
    }                                                              //~9403I~
    //******************************************                   //~9321I~
    private void showAmmountReceived()                             //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ScoreDlg:showAmmountReceived");  //~9321I~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9321I~
        {                                                          //~9321I~
//          int idx=ACC.currentEswnToMember(ii);          //~9321I~//~9322R~//~9416R~
//          tvsAdd[idx].setText(Integer.toString(ammount[ii]));    //~9321I~//~9416R~
            int eswn=ACC.getCurrentEswnByPosition(ii);                //~9416I~
            tvsAdd[ii].setText(Integer.toString(ammount[eswn]));   //~9416I~
            tvsScore[ii].setText(Integer.toString(minusScore[ii]));//~9321I~
        	int mp=minusPay[ii];                                   //~9416I~
//          if (mp!=0)                                             //~9416I~//~9818R~
		        tvsPay[ii].setText(Integer.toString(minusPay[ii]));    //~9321R~//~9416R~
//          else                                                   //~9416I~//~9818R~
//  	        llsMinusPay[ii].setVisibility(View.GONE);          //~9416I~//~9818R~
    		if (!swMinusStop)                                      //~9818I~
    	        llsMinusPay[ii].setVisibility(View.GONE);          //~9818I~
            tvsTotal[ii].setText(Integer.toString(newTotal[ii]));  //~9321I~
	        if (!swMinusPayGetAllPoint)	//point is pay up to 0     //~9415I~
	            tvsMinusCharge[ii].setText(Integer.toString(chargeToGainer[ii]));//~9408I~//~9415I~
          	else                                                   //~9415I~
            {                                                      //~9416I~
            	int mc=chargeToGainer[ii];                         //~9416I~
                if (mc!=0)                                         //~9416I~
            		tvsMinusChargeLater[ii].setText(Integer.toString(chargeToGainer[ii]));//~9415I~//~9416R~
                else                                               //~9416I~
	            	tvsMinusChargeLater[ii].setVisibility(View.GONE);//~9416I~
            }                                                      //~9416I~
//          tvsMinusChargeResult[ii].setText(Integer.toString(chargeToGainerResult[ii]));//~9408I~//~9415R~
        }                                                          //~9321I~
    }                                                              //~9321I~
//    //******************************************                 //~9321R~
//    private void showNewTotal()                                  //~9321R~
//    {                                                            //~9321R~
//        for (int ii=0;ii<PLAYERS;ii++)     //account sequence    //~9321R~
//        {                                                        //~9321R~
//            int idx=AG.aAccounts.currentEswnToMember(ii);        //~9321R~
//            int total=minusScore[ii]+minusPay[idx];              //~9321R~
//            tvsPay[ii].setText(Integer.toString(minusPay[idx])); //~9321R~
//            tvsTotal[ii].setText(Integer.toString(total);        //~9321R~
//            if (Dump.Y) Dump.println("ScoreDlg:showNewTotal ii="+ii+",old="+newTotal[ii]+",pay="+minusPay[ii]+",result="+total);//~9321R~
//        }                                                        //~9321R~
//    }                                                            //~9321R~
//    //******************************************                   //~9320I~//~9408R~
//    private void showMinus()                                       //~9320I~//~9408R~
//    {                                                              //~9320I~//~9408R~
//        if (Dump.Y) Dump.println("ScoreDlg:showMinus");            //~9402I~//~9408R~
//        int[] total = newTotal;                                    //~9321R~//~9403I~
//        for (int ii = 0; ii < PLAYERS; ii++)                                 //~9320I~//~9403R~
//        {                                                          //~9320I~//~9403R~
//            int amt = total[ii];                                     //~9320I~//~9403R~
//            if ((amt < 0 || amt == 0 && swMinus0)                      //~9320I~//~9403R~
//                    && (!Accounts.isDummy(ii) || swMinusRobot))            //~9320I~//~9403R~
//            {                                                      //~9320I~//~9403R~
//                int eswn = ACC.getCurrentEswnByAccount(ii);              //~9320I~//~9322R~//~9403R~
//                tvsPayer[ii].setText(GConst.nameESWN[eswn]);           //~9320I~//~9403R~
//                payers[ii]=eswn;                                   //~9322I~//~9403R~
//                cbssPayTo[ii][eswn].setEnabled(false);             //~9322I~//~9403R~
//                int payTo=getPayTo(eswn);                          //~9402I~//~9403R~
//                if (payTo>=0)                                      //~9402I~//~9403R~
//                    cbssPayTo[ii][payTo].setState(true);          //~9402I~//~9403R~
//                if (!swReceived)                                   //~9402I~//~9403R~
//                    tvSetPayer.setVisibility(View.VISIBLE);        //~9402I~//~9403R~
//                                                                   //~9402I~//~9403R~
//            }                                                      //~9320I~//~9403R~
//            else                                                   //~9320I~//~9403R~
//                llsMinus[ii].setVisibility(View.GONE);                 //~9320I~//~9403R~
//        }                                                        //~9403R~
//        for (int ii = 0; ii < PLAYERS; ii++)                       //~9403I~//~9408R~
//        {                                                          //~9403I~//~9408R~
//            int eswnPayTo = eswnMinusPrizeTo[ii]-1;                //~9403R~//~9408R~
//            if (eswnPayTo>=0)                                      //~9403I~//~9408R~
//            {                                                      //~9403I~//~9408R~
//                int eswn = ACC.getCurrentEswnByAccount(ii);        //~9403I~//~9408R~
//                tvsPayer[ii].setText(GConst.nameESWN[eswn]);       //~9403I~//~9408R~
//                payers[ii]=eswn;                                   //~9403I~//~9408R~
//                cbssPayTo[ii][eswn].setEnabled(false);             //~9403I~//~9408R~
//                if (eswnPayTo>=0)                                  //~9403I~//~9408R~
//                    cbssPayTo[ii][eswnPayTo].setState(true);           //~9403I~//~9408R~
//                if (!swReceived)                                   //~9403I~//~9408R~
//                    tvSetPayer.setVisibility(View.VISIBLE);        //~9403I~//~9408R~
//            }                                                      //~9403I~//~9408R~
//            else                                                   //~9403I~//~9408R~
//                llsMinus[ii].setVisibility(View.GONE);             //~9403I~//~9408R~
//        }                                                          //~9403I~//~9408R~
//    }//~9320I~                                                   //~9408R~
    //******************************************                   //~9402I~
    private int getPayTo(int PlooserEswn)                          //~9402I~
    {                                                              //~9402I~
        if (Dump.Y) Dump.println("ScoreDlg:getPayTo swReceived="+swReceived);//~9402I~
    	Complete.Status[] stats=AG.aComplete.sortStatusS();        //~9402I~
        int eswn=-1;                                               //~9402I~
        int ctrPayTo=0;                                            //~9402I~
        if (stats==null)                                           //~9402I~
        {                                                          //~9402I~
	        if (Dump.Y) Dump.println("ScoreDlg.getPayTo !!! no complete Status");//~9402I~
        	return eswn;                                           //~9402I~
        }                                                          //~9402I~
        if (stats.length==1)	//TODO when prevEswn option ctr==1 ?//~9402R~
        {                                                          //~9402I~
        	eswn=stats[0].completeEswn;                            //~9402I~
        }                                                          //~9402I~
        else                                                       //~9402I~
        {                                                          //~9402I~
        	for (int ii=0;ii<stats.length;ii++)                    //~9402I~
            {                                                      //~9402I~
            	                                                   //~9402I~
            	if (stats[ii].completeEswnLooser==PlooserEswn)           //~9402I~
                {                                                  //~9402I~
		        	eswn=stats[0].completeEswn;                    //~9402I~
                    ctrPayTo++;                                    //~9402I~
                }                                                  //~9402I~
            }                                                      //~9402I~
        }                                                          //~9402I~
        if (Dump.Y) Dump.println("ScoreDlg.getPayTo eswn="+eswn);  //~9402I~
        return eswn;                                               //~9402I~
    }                                                              //~9402I~
//    //******************************************                   //~9322I~//~9408R~
//    private void getPayerInfo()                                    //~9322I~//~9408R~
//    {                                                              //~9322I~//~9408R~
//        for (int ii = 0; ii < PLAYERS; ii++)                       //~9322I~//~9408R~
//            for (int jj = 0; jj < PLAYERS; jj++)                   //~9322I~//~9408R~
//            {                                                      //~9322I~//~9408R~
//                payerInfo[ii*PLAYERS+jj]=cbssPayTo[ii][jj].getState() ? 1:0;//~9322I~//~9408R~
//            }                                                      //~9322I~//~9408R~
//    }                                                              //~9322I~//~9408R~
    //*******************************************************      //~9321I~
    @Override                                                      //~9321I~
    public void onDismissDialog()                                  //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("CompleteDlg.onDismissDialog");   //~9321I~
        if (!swDismissBeforNew)                                    //~9322I~
	        AG.aScoreDlg=null;                                         //~9321I~//~9322R~
        swDismissBeforNew=false;                                   //~9322I~
    }                                                              //~9321I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void setButton()                                        //~9321I~
    {                                                              //~9321I~
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
        if (Dump.Y) Dump.println("ScoreDlg.updateOKNGAdditional ctrNG="+PctrNG+",ctrNone="+PctrNone+",swAllOK="+PswAllOK);//~9321I~
        btnTotal.setEnabled(PswAllOK);                             //~9321I~
        if (PctrNone==0 && PctrNG!=0) //all responsed, someone replyed NG//~9612I~
        {                                                          //~9612I~
	        if (swRequester)                                       //~9612I~
    	    	alertToForceOK(this,TITLEID,R.string.Alert_ScoreDlgForceOK);//~9612I~
        }                                                          //~9612I~
    }                                                              //~9321I~
    //*******************************************************************//~9612I~
    @Override                                                      //~9612I~
	protected void alertActionReceived(int Pbuttonid,int Prc)      //~9612I~
    {                                                              //~9612I~
        if (Dump.Y) Dump.println("ScoreDlg.alerActionReceived buttonID="+Pbuttonid+",rc="+Prc);//~9612I~
    	if (Pbuttonid==BUTTON_POSITIVE)                            //~9612I~
        {                                                          //~9612I~
	        btnTotal.setEnabled(true/*PswAllOK*/);                 //~9612I~
        }                                                          //~9612I~
    }                                                              //~9612I~
    //******************************************                   //~9321I~
    @Override                                                      //~9321I~
    public void onClickOK()                                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ScoreDlg.onClickOK swRequester="+swRequester);//~9321I~
        if (swRequester)                                           //~9321I~
        {                                                          //~9322I~
//      	getPayerInfo();                                        //~9322I~//~9408R~
	  		saveLast();                                            //~9322I~
        	sendConfirmRequest();                                  //~9321R~
            resetRespStat();	//OKNGDlg                          //~0120I~
	        repaintOKNG();    //callback updateOKNGAdditional      //~0120I~
	        btnTotal.setEnabled(false);                            //~0120I~
//          CMP.swSent=true;                                       //~0314R~
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
        if (Dump.Y) Dump.println("ScoreDlg.onClickOther btnid="+Integer.toHexString(Pbuttonid));//~9321I~
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
        if (Dump.Y) Dump.println("ScoreDlg.onClickTotal");         //~9321I~
        dismiss();                                              //~9321I~
//      endGame(EGDR_MINUSSTOP,newTotal);                          //~9321R~
//      sendEndGame(newTotal);                                     //~9321I~//~9408R~
//      sendEndGame(chargeToGainerResult);                         //~9408I~//~9415R~
        sendEndGame(newTotal);                                     //~9415I~
    }                                                              //~9321I~
    //******************************************                   //~9417I~
    public void onClickShowRule()                                  //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("ScoreDlg.onClickShowRule");      //~9417I~
        showRule();                                                //~9417I~
    }                                                              //~9417I~
//    //******************************************                   //~9316I~//~9321R~
//    public static boolean showDialog(int[] Pammount/*eswn seq*/)         //~9316I~//~9321R~
//    {                                                              //~9316I~//~9321R~
//        if (Dump.Y) Dump.println("ScoreDlg:showDialog intp="+Arrays.toString(Pammount));//~9316I~//~9321R~
//        AG.aAccounts.updateScore(Pammount);                        //~9318I~//~9321R~
//        boolean[] swsMinusStop=getMinusStopAccount(AG.aAccounts.score);      //~9316I~//~9318R~//~9321R~
//        if (swsMinusStop==null)                                    //~9316I~//~9321R~
//        {                                                          //~9316I~//~9321R~
//            return false;                                          //~9316I~//~9321R~
//        }                                                          //~9316I~//~9321R~
//        ScoreDlg.newInstance(Pammount).show();                     //~9316I~//~9321R~
//        return true;                                               //~9316I~//~9321R~
//    }                                                              //~9316I~//~9321R~
    //*************************************************************************//~9318R~
    //*endgametype:EGDR_DRAWN_LAST:drawnLast/EGDR_DRAWN_HW:drawnHalfWay                   //~9318R~//~9413R~
    //*direct to showToatal when EGDR_NORMAL                       //~9413I~
    //*************************************************************************//~9318R~
    public static void showDialog(int PendgameType,int[] Pammount/*eswn seq*/,int PnextgameType)//~9318R~
    {                                                              //~9318R~
        if (Dump.Y) Dump.println("ScoreDlg:showDialog endgameType="+PendgameType+",nextGameType="+PnextgameType+",amt="+Arrays.toString(Pammount));//~9318R~
        showTotal(PendgameType,PnextgameType,Pammount);                                  //~9318I~//~9321R~
//      	return;		//minus account exists                     //~9318I~//~9321R~
//      AG.aNamePlate.showScore();                                 //~9318I~//~9321R~
//      Status.endGame(PendgameType,PnextgameType);                //~9318R~
//      AG.aStarter.showGameSeq(AG.aAccounts.starterRelativePos);  //~9318R~
        AG.aGMsg.drawMsgbar(Status.getStringGameSeq());            //~9318R~//~9503R~
    }                                                              //~9318R~
    //******************************************                   //~9316I~
    //*Pammount index is Accounts index                            //~9316I~
    //******************************************                   //~9316I~
    private static boolean[] getMinusStopAccount(int[] Pammount)   //~9316I~//~9420R~//~9424R~
    {                                                              //~9316I~
    	boolean swMinusStop=RuleSetting.isMinusStop();             //~9316I~
    	boolean swMinusRobot=RuleSetting.isMinusStopRobot();   //~9316I~//~9322R~
    	boolean swZeroStop=RuleSetting.isZeroStop();               //~9316I~
        if (Dump.Y) Dump.println("ScoreDlg:getMinusStopAccount swMinusStop="+swMinusStop+",swMinusRobot="+swMinusRobot+",swZeroStop="+swZeroStop+",intp="+Arrays.toString(Pammount));//~9316I~//~9420R~
        boolean[] swsStop=new boolean[PLAYERS];                          //~9316I~
        boolean swStop=false;                                      //~9316I~
        if (swMinusStop)                                           //~9316I~
        {                                                          //~9316I~
        	for (int ii=0;ii<PLAYERS;ii++)                         //~9316I~
            {                                                      //~9316I~
//          	int amt=getScoreWithErr(ii,Pammount[ii]);          //~9420I~//~9424R~
            	int amt=Pammount[ii];                              //~9424I~
//              if (Pammount[ii]<0 || (Pammount[ii]==0 && swZeroStop))               //~9316I~//~9420R~
                if (amt<0 || (amt==0 && swZeroStop))               //~9420I~
                {                                                  //~9316I~
                	if (!swMinusRobot && Accounts.isDummy(ii)) //~9316I~//~9322R~
                    {                                              //~9322I~
                    	UView.showToastLong(R.string.Info_NoMinusStopRobot);//~9322I~//~0224R~
//      				UserAction.showInfo(0,R.string.Info_NoMinusStopRobot);//~0224R~
                        continue;                                  //~9316I~
                    }                                              //~9322I~
                	swStop=true;                                   //~9316I~
                    swsStop[ii]=true;                                    //~9316I~
                }                                                  //~9316I~
            }                                                      //~9316I~
        }                                                          //~9316I~
        if (Dump.Y) Dump.println("ScoreDlg:getMinusStopAccount swStop="+swStop+",swsStop="+Arrays.toString(swsStop));//~9316I~//~9420R~//~9424R~
        if (!swStop)                                               //~9316I~
        	return null;                                           //~9316I~
        return swsStop;                                            //~9316I~
    }                                                              //~9316I~
//    //**************************************************************************//~9420I~//~9424R~
//    private static int getScoreWithErr(int Pidx,int PnetScore)            //~9420I~//~9424R~
//    {                                                              //~9420I~//~9424R~
//        if (Dump.Y) Dump.println("ScoreDlg:getScoreWithErr idx="+Pidx+",netScore="+PnetScore);//~9420I~//~9424R~
//        int[][] errPay=AG.aComplete.amtsErrorByLooser;             //~9420R~//~9424R~
//        int score=PnetScore;                                       //~9420M~//~9424R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~9420I~//~9424R~
//        {                                                          //~9420I~//~9424R~
//            if (errPay[ii]!=null)                                  //~9420I~//~9424R~
//            {                                                      //~9420I~//~9424R~
//                if (Dump.Y) Dump.println("ScoreDlg.getScoreWithErr ",errsPay);//~9424R~
//                score+=errPay[ii][Pidx];                           //~9420R~//~9424R~
//            }                                                      //~9420I~//~9424R~
//        }                                                          //~9420I~//~9424R~
//        if (Dump.Y) Dump.println("CompleteDlg.getScoreWithErr rc="+score);//~9420I~//~9424R~
//        return score;                                              //~9420I~//~9424R~
//    }                                                              //~9420I~//~9424R~
    //**************************************************************************//~9318I~
    //*from CompleteDlg/DrawnLastDlg/DrawnHWDlg                    //~9318I~
    //**************************************************************************//~9318I~
    public static void showTotal(int PendgameType,int PnextgameType,int[] Pamt)//~9318I~//~9519R~
    {                                                              //~9318I~
        if (Dump.Y) Dump.println("ScoreDlg.showTotal endgametype="+PendgameType+",nextgame="+PnextgameType+",amt="+Arrays.toString(Pamt));//~9708I~
    	saveLastWithoutScoreDlg(PendgameType);	//save endgametype wven when rc=true(ScoreDlg will not shown)//~9521I~
        AG.aComplete.setTotalAgreed();                                      //~9612I~
    	if (!AG.aUserAction.isServer)                              //~9318I~
        {                                                          //~9318I~
    		sendToServer(PendgameType,PnextgameType,Pamt); //ENDGAME_SCORE_UPDATE//~9319R~
        	return;                                                //~9318I~
        }                                                          //~9318I~
        AG.aComplete.setInvalidCompletion();                       //~9705M~
//        AG.aComplete.setErrCompletion();                         //~0302R~
//      AG.aUADelayed.resetWaitAll();	//clear stopAuto by DrawnHW//~9703I~//~9704R~
//  	resetWait();	//server                                   //~9704R~
//      if (calcOnServer(false/*swReceived*/,PLAYER_YOU,PendgameType,PnextgameType,Pamt))//~9318R~//~9703R~
        if (calcOnServer(false/*swReceived*/,PLAYER_YOU,PnextgameType,Pamt))     //~9703R~//~9704R~
        {                                                          //~9318I~
            sendToAllClientUpdate(PendgameType,PnextgameType,AG.aAccounts.score);//no minus status//~9318R~//~9321R~//~9322R~
//          AG.aNamePlate.showScore();                             //~9318I~//~9320R~
			nextGame(true/*swServer*/,PendgameType,PnextgameType);                    //~9318I~//~9502R~
            return;                                                //~9318I~
        }                                                          //~9318I~
        sendToAllClientUpdate(PendgameType,NGTP_AGREED,AG.aAccounts.score);//minus status//~9612R~//~9704R~
//      ScoreDlg.showDialog(PendgameType,Pamt,ACC.score,PnextgameType); //minus chk//~9318R~//~9321R~//~9322R~
//      ScoreDlg.newInstance(Pamt,AG.aAccounts.score).show();      //~9321R~//~9322R~//~9413R~
        ScoreDlg.newInstance(PendgameType,Pamt,AG.aAccounts.score).show();//~9413I~
    }                                                              //~9318I~
	//************************************************             //~9417I~
    private void showRule()                                        //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("ScoreDlg.showRule");             //~9417I~
        RuleSetting.showRuleInGame();                              //~9417I~
    }                                                              //~9417I~
//    //************************************************           //~9704R~
//    private static void resetWait()                              //~9704R~
//    {                                                            //~9704R~
//        if (Dump.Y) Dump.println("ScoreDlg.resetWait");          //~9704R~
//        AG.aUADelayed.resetWaitAll();   //clear stopAuto by DrawnHW//~9704R~
//    }                                                            //~9704R~
    //**************************************************************************//~9321I~
//  public void sendEndGame(int[] Ptotal)                          //~9321I~//~9605R~
    private void sendEndGame(int[] Ptotal)                         //~9605I~
    {                                                              //~9321I~
//      int[][] intssP=new int[][]{Ptotal,minusPay};               //~9415R~
        int[][] intssP=new int[][]{Ptotal,minusPay,chargeToGainer};//~9415I~
    	if (!AG.aUserAction.isServer)                              //~9321I~
        {                                                          //~9321I~
//  		sendToServerEndGame(Ptotal); //ENDGAME_SCORE_CONFIRMED //~9321R~//~9415R~
    		sendToServerEndGame(intssP); //ENDGAME_SCORE_CONFIRMED //~9415I~
        	return;                                                //~9321I~
        }                                                          //~9321I~
//    	endGame(EGDR_MINUSSTOP,Ptotal);                            //~9321I~//~9415R~
      	endGame(EGDR_MINUSSTOP,intssP);                            //~9415R~
//      sendToAllClientEndGame(Ptotal);                            //~9321I~//~9415R~
        sendToAllClientEndGame(intssP);                            //~9415I~
    }                                                              //~9321I~
    //**************************************************************************//~9318I~
    //*from UserAction,GCM_ENDGAME_SCORE btio msg received                                           //~9318I~//~9320R~
    //**************************************************************************//~9318I~
    public static void onReceived(boolean PswServer,boolean PswReceived,int Pplayer,int[] PintParm)//~9318R~
    {                                                              //~9318I~
        int[] total;                                               //~9321I~
        int[] minusPrize;                                          //~9415I~
        int[] minusCharge;                                         //~9415I~
        if (Dump.Y) Dump.println("ScoreDlg:onReceived swServer="+PswServer+",PswReceived="+PswReceived+",player="+Pplayer+",intp="+Arrays.toString(PintParm));//~9318R~
        int scoreMsgtype=PintParm[POS_SCOREMSGTYPE];               //~9320I~
        int endgameType=PintParm[POS_ENDGAMETYPE];                 //~9318I~
        int nextgameType=PintParm[POS_NEXTGAMETYPE];               //~9318I~
        if (Dump.Y) Dump.println("ScoreDlg:onReceived scoreMsgtype="+scoreMsgtype+",endgameType="+endgameType+",nextgameType="+nextgameType);//~9521I~
    	saveLastWithoutScoreDlg(endgameType);	//save endgametype wven when rc=true(ScoreDlg will not shown)//~9521I~
        if (PswServer)  //from dealer(client)                      //~9318R~
        {                                                          //~9318I~
        	switch(scoreMsgtype)                                   //~9321I~
            {                                                      //~9321I~
            case ENDGAME_SCORE_UPDATE:                             //~9321I~//~9521R~
		        if (Dump.Y) Dump.println("ScoreDlg:onReceived server ENDGAME_SCORE_UPDATE");//~9612I~
//  	    	resetWait();	//on server                        //~9704R~
			    AG.aComplete.setTotalAgreed();                              //~9612I~
		        AG.aComplete.setInvalidCompletion();               //~9705I~
//                AG.aComplete.setErrCompletion();                 //~0302R~
                int[] amt=new int[PLAYERS];                            //~9318I~//~9321R~
                System.arraycopy(PintParm,POS_AMMOUNT,amt,0,PLAYERS);  //~9318I~//~9321R~
//              if (calcOnServer(PswReceived,Pplayer,endgameType,nextgameType,amt)) //no minus status//~9318I~//~9321R~//~9703R~
                if (calcOnServer(PswReceived,Pplayer,nextgameType,amt)) //no minus status//~9703R~//~9704R~
                {                                                      //~9318I~//~9321R~
                    sendToAllClientUpdate(endgameType,nextgameType,AG.aAccounts.score);//with old eswn status //~9318R~//~9319I~//~9321R~
    //              AG.aNamePlate.showScore();                         //~9318I~//~9320R~//~9321R~
                    nextGame(PswServer,endgameType,nextgameType);                //~9318M~//~9321R~//~9502R~
                }                                                      //~9318I~//~9321R~
                else                                                   //~9318I~//~9321R~
                    sendToClientDealer(ENDGAME_SCORE_MINUS,endgameType,nextgameType,amt,AG.aAccounts.score);//no minus status//~9320R~//~9321R~
            	break;                                             //~9321I~
            case ENDGAME_SCORE_CONFIRM_REQUEST:                    //~9321R~
            	int[][] intss=parseConfirmRequest(PintParm);               //~9321I~
//  			sendToAllClientConfirm(intss[0],intss[1],intss[2],intss[3],intss[4]);//~9321I~//~9322R~//~9408R~
    			sendToAllClientConfirm(intss[0],intss[1],intss[2],intss[3],intss[4],intss[5]);//~9408I~
		        if (Utils.isShowingDialogFragment(AG.aScoreDlg))   //~9322I~
                {                                                  //~9322I~
                	AG.aScoreDlg.swDismissBeforNew=true;                        //~9322I~
			        AG.aScoreDlg.dismiss();                        //~9322I~
                }                                                  //~9322I~
//  			ScoreDlg.newInstance(intss[0],intss[1],intss[2],intss[3],intss[4],intss[5]).show();//~9321R~//~9322R~//~9408R~//~9413R~
    			ScoreDlg.newInstance(endgameType,intss[0],intss[1],intss[2],intss[3],intss[4],intss[5]).show();//~9413I~
            	break;                                             //~9321I~
            case ENDGAME_SCORE_CONFIRM_REPLY:                      //~9321R~
                int replyEswn=PintParm[POS_REPLAY_ESWN];           //~9321I~//~9430I~
				if (OKNGDlg.isDealer())                            //~9321I~
                {                                                  //~9321I~
		            if (Utils.isShowingDialogFragment(AG.aScoreDlg))//~9321I~
	            		AG.aScoreDlg.repaintOKNG(replyEswn,PintParm[POS_OKNG]!=0);	//callback updateOKNGAdditional//~9321I~
                }                                                  //~9321I~
                else                                               //~9321I~
                {                                                  //~9321I~
//      			String msgdata=ENDGAME_SCORE_CONFIRM_REPLY+MSG_SEPAPP2+PintParm[POS_OKNG];//~9321I~//~9430R~
        			String msgdata=ENDGAME_SCORE_CONFIRM_REPLY+MSG_SEPAPP2+endgameType+MSG_SEPAPP2+nextgameType+MSG_SEPAPP+PintParm[POS_OKNG];//~9430I~
//      			AG.aUserAction.sendToTheClientDealer(GCM_ENDGAME_SCORE,msgdata);//~9321I~//~9430R~
        			AG.aUserAction.sendToTheClientDealerWithSourceEswn(GCM_ENDGAME_SCORE,replyEswn,msgdata);//~9430I~
        		}                                                  //~9321I~
            	break;                                             //~9321I~
            case ENDGAME_SCORE_CONFIRMED:                          //~9321I~
            	total=new int[PLAYERS];                            //~9321R~
            	minusPrize=new int[PLAYERS];
            	minusCharge=new int[PLAYERS];//~9415I~
    			System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED,total,0,PLAYERS);//~9321I~//~9408R~
    			System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED_MINUSPRIZE,minusPrize,0,PLAYERS);//~9415I~
    			System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED_CHARGE,minusCharge,0,PLAYERS);//~9415I~
//  			endGame(EGDR_MINUSSTOP,total);                     //~9321I~//~9415R~
//  			int[][] intssP=new int[][]{total,minusPrize};      //~9415R~
    			int[][] intssP=new int[][]{total,minusPrize,minusCharge};//~9415I~
//  			endGame(EGDR_MINUSSTOP,total);                     //~9415R~
    			endGame(EGDR_MINUSSTOP,intssP);                    //~9415R~
//  			sendToAllClientEndGame(total);                     //~9321R~//~9415R~
    			sendToAllClientEndGame(intssP);                    //~9415I~
            	break;                                             //~9321I~
            }                                                      //~9321I~
        }                                                          //~9318I~
        else  //client                                                     //~9318I~//~9502R~
        {                                                          //~9318I~
        	switch(scoreMsgtype)                                   //~9321I~
            {                                                      //~9321I~
            case ENDGAME_SCORE_UPDATE:                             //~9321I~
		        if (Dump.Y) Dump.println("ScoreDlg:onReceived client ENDGAME_SCORE_UPDATE nextgametype="+nextgameType);//~9612R~
			    AG.aComplete.setTotalAgreed();                              //~9612I~
		        AG.aComplete.setInvalidCompletion();               //~9705M~
//                AG.aComplete.setErrCompletion();                 //~0302R~
        		if (nextgameType==NGTP_AGREED)	//notify totalAgreed//~9612R~
                	break;         //do setTotalAgreed only        //~9612R~
    	    	System.arraycopy(PintParm,POS_AMMOUNT,AG.aAccounts.score,0,PLAYERS);   //account idxde seq//~9318I~//~9320R~
//          	AG.aNamePlate.showScore();                             //~9318M~//~9320R~
    			nextGame(PswServer,endgameType,nextgameType);                    //~9319I~//~9320R~//~9502R~
                break;                                             //~9321I~
            case ENDGAME_SCORE_MINUS:                              //~9321I~
            	int[] amt=new int[PLAYERS];                        //~9320I~//~9321M~
            	int[] minusScore=new int[PLAYERS];                 //~9321M~
    	    	System.arraycopy(PintParm,POS_AMMOUNT,amt,0,PLAYERS);   //account idxde seq//~9320I~//~9321M~
    	    	System.arraycopy(PintParm,POS_AMMOUNT+PLAYERS,minusScore,0,PLAYERS);   //account idxde seq//~9321M~
//  			ScoreDlg.newInstance(amt,minusScore).show();                  //~9320I~//~9321M~//~9413R~
    			ScoreDlg.newInstance(endgameType,amt,minusScore).show();//~9413I~
                break;                                             //~9321I~
            case ENDGAME_SCORE_CONFIRM_REQUEST:                    //~9321I~
				if (!OKNGDlg.isDealer())                           //~9321I~
                {                                                  //~9321I~
			        if (Utils.isShowingDialogFragment(AG.aScoreDlg))//~9322I~
    	            {                                              //~9322I~
            			AG.aScoreDlg.swDismissBeforNew=true;                    //~9322I~
			        	AG.aScoreDlg.dismiss();                    //~9322I~
        	        }                                              //~9322I~
            		int[][] intss=parseConfirmRequest(PintParm);   //~9321R~
//  				ScoreDlg.newInstance(intss[0],intss[1],intss[2],intss[3],intss[4]).show();//~9321R~//~9322R~//~9408R~
//  				ScoreDlg.newInstance(intss[0],intss[1],intss[2],intss[3],intss[4],intss[5]).show();//~9408I~//~9413R~
    				ScoreDlg.newInstance(endgameType,intss[0],intss[1],intss[2],intss[3],intss[4],intss[5]).show();//~9413I~
                }                                                  //~9321I~
                break;                                             //~9321I~
            case ENDGAME_SCORE_CONFIRM_REPLY:                      //~9321I~
				if (OKNGDlg.isDealer())                            //~9321I~
                {                                                  //~9321I~
		        	int replyEswn=PintParm[POS_REPLAY_ESWN];       //~9321I~
		            if (Utils.isShowingDialogFragment(AG.aScoreDlg))//~9321I~
	            		AG.aScoreDlg.repaintOKNG(replyEswn,PintParm[POS_OKNG]!=0);	//callback updateOKNGAdditional//~9321I~
                }                                                  //~9321I~
                break;                                             //~9321I~
            case ENDGAME_SCORE_CONFIRMED:                          //~9321I~
//  			if (!OKNGDlg.isDealer())                           //~9321R~//~9401R~
//              {                                                  //~9321I~//~9401R~
	            	total=new int[PLAYERS];                         //~9321I~
	            	minusPrize=new int[PLAYERS];                   //~9415I~
	            	minusCharge=new int[PLAYERS];                  //~9415I~
                    int[][] intssP=new int[][]{total,minusPrize,minusCharge};//~9415R~
    				System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED,total,0,PLAYERS);//~9321I~//~9408R~
	    			System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED_MINUSPRIZE,minusPrize,0,PLAYERS);//~9415I~
	    			System.arraycopy(PintParm,POS_AMMOUNT_CONFIRMED_CHARGE,minusCharge,0,PLAYERS);//~9415I~
//  				endGame(EGDR_MINUSSTOP,total);                 //~9321I~//~9415R~
    				endGame(EGDR_MINUSSTOP,intssP);                //~9415I~
//              }                                                  //~9321I~//~9401R~
                break;                                             //~9321I~
            }                                                      //~9320I~
        }                                                          //~9318I~
    }                                                              //~9318I~
    //**************************************************************************//~9321I~
    private static int[][] parseConfirmRequest(int[] PintParm)     //~9321R~
    {                                                              //~9321I~
    	int pos=POS_AMMOUNT_CONFIRM;                                //~9321I~
        if (Dump.Y) Dump.println("ScoreDlg:parseConfirmRequest startpos="+pos+",intp="+Arrays.toString(PintParm));//~9321I~
        int[] amt=new int[PLAYERS];                                //~9321I~
        int[] score=new int[PLAYERS];                              //~9321I~
        int[] pay=new int[PLAYERS];                                //~9321I~
        int[] total=new int[PLAYERS];                              //~9321I~
//      int[] payer=new int[PLAYERS*PLAYERS];                      //~9322I~//~9408R~
        int[] charge=new int[PLAYERS];                             //~9408I~
        int[] chargeResult=new int[PLAYERS];                       //~9408I~
        System.arraycopy(PintParm,pos,amt,0,PLAYERS);   pos+=PLAYERS;//~9321I~
        System.arraycopy(PintParm,pos,score,0,PLAYERS);   pos+=PLAYERS;//~9321I~
        System.arraycopy(PintParm,pos,pay,0,PLAYERS);   pos+=PLAYERS;//~9321I~
        System.arraycopy(PintParm,pos,total,0,PLAYERS); pos+=PLAYERS;           //~9321I~//~9322R~
//      System.arraycopy(PintParm,pos,payer,0,PLAYERS*PLAYERS);    //~9322R~//~9408R~
        System.arraycopy(PintParm,pos,charge,0,PLAYERS);pos+=PLAYERS;//~9408I~
        System.arraycopy(PintParm,pos,chargeResult,0,PLAYERS);pos+=PLAYERS;//~9408I~
        if (Dump.Y) Dump.println("ScoreDlg:parseConfirmRequest ammount="+Arrays.toString(amt));//~9321I~
        if (Dump.Y) Dump.println("ScoreDlg:parseConfirmRequest minusScore="+Arrays.toString(score));//~9321I~
        if (Dump.Y) Dump.println("ScoreDlg:parseConfirmRequest minusPay="+Arrays.toString(pay));//~9321I~
        if (Dump.Y) Dump.println("ScoreDlg:parseConfirmRequest newTotal="+Arrays.toString(total));//~9321I~
//      if (Dump.Y) Dump.println("ScoreDlg:parseConfirmRequest payer="+Arrays.toString(payer));//~9322I~//~9408R~
        if (Dump.Y) Dump.println("ScoreDlg:parseConfirmRequest charge="+Arrays.toString(charge));//~9408I~
        if (Dump.Y) Dump.println("ScoreDlg:parseConfirmRequest chargeResult="+Arrays.toString(chargeResult));//~9408I~
//      int[][] intss=new int[][]{amt,score,pay,total,payer};              //~9321I~//~9322R~//~9408R~
        int[][] intss=new int[][]{amt,score,pay,total,charge,chargeResult};//~9408I~
        return intss;                                              //~9321I~
    }                                                              //~9321I~
    //**************************************************************************//~9318I~
    private static void nextGame(boolean PswServer,int PendgameType,int PnextgameType)//~9318I~//~9320R~//~9501R~//~9502R~
    {                                                              //~9318I~
        if (Dump.Y) Dump.println("ScoreDlg:nextGame endgame="+PendgameType+",nextgame="+PnextgameType);//~9319I~
//      if (PendgameType==EGDR_RESET)                              //~9704R~
        if (PnextgameType==NGTP_RESET)                             //~9704I~
        {                                                          //~9704I~
            resetGame(PswServer);                                  //~9704I~
            return;                                                 //~9704I~
        }                                                          //~9704I~
        resetGrilledBird(PendgameType);                            //~9501I~//~9503R~
//      AG.aNamePlate.showScore();                                 //~9320I~//~9503R~//~0324R~
        AG.aNamePlate.showScoreClearRiver();                       //~0324I~
//        Complete.newInstance();                                    //~9320I~//~9502R~//~9503R~
//        Status.endGame(PendgameType,PnextgameType);                //~9318I~//~9502R~//~9503R~
//        AG.aStarter.showGameSeq(AG.aAccounts.starterRelativePos);  //~9318I~//~9502R~//~9503R~
//        UserAction.showInfoAll(0/*opt*/,Status.getStringGameSeq());//~9318I~//~9502R~//~9503R~
          AG.aAccounts.nextGame(PswServer,PendgameType,PnextgameType);    //touch dice//~9502R~//~9503R~
	}                                                              //~9318I~
    //**************************************************************************//~9704I~
    private static void resetGame(boolean PswServer)               //~9704I~
    {                                                              //~9704I~
        if (Dump.Y) Dump.println("ScoreDlg:resetGame swServer="+PswServer);//~9704I~
//      resetGrilledBird(PendgameType);                            //~9704I~
//      AG.aNamePlate.showScore();                                 //~9704I~//~0324R~
        AG.aNamePlate.showScoreClearRiver();                       //~0324I~
        AG.aAccounts.resetGame(PswServer);    //touch dice         //~9704I~
    }                                                              //~9704I~
    //**************************************************************************//~9522I~
    public  static void nextGameByLastGame(boolean PswServer,int PendgameType,int PnextgameType,int PtypeClosable)//~9522R~
    {                                                              //~9522I~
        if (Dump.Y) Dump.println("ScoreDlg:nextGameByLastGame swServer="+PswServer+",endgameType="+PendgameType+",nextgameType="+PnextgameType+",typeClosable="+PtypeClosable);//~9522I~
        AG.aAccounts.nextGameByLastGame(PswServer,PendgameType,PnextgameType);    //touch dice//~9522I~
	}                                                              //~9522I~
    //**************************************************************************//~9501I~//~9503R~
    private static void resetGrilledBird(int PendgameType)         //~9501I~//~9503R~
    {                                                              //~9501I~//~9503R~
        if (Dump.Y) Dump.println("ScoreDlg:resetGrilledBird endgame="+PendgameType);//~9501I~//~9503R~
        AG.aComplete.resetGrilledBird(PendgameType);               //~9501I~//~9503R~
    }                                                              //~9501I~//~9503R~
    //**************************************************************************//~9321I~
//  public static void endGame(int PendgameType,int[] Ptotal/*indexSeq*/)//~9321R~//~9415R~
//  public static void endGame(int PendgameType,int[][] PintssP/*indexSeq*/)//~9415I~//~9605R~
    private static void endGame(int PendgameType,int[][] PintssP/*indexSeq*/)//~9605I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ScoreDlg.endGame type="+PendgameType+",isDealer="+OKNGDlg.isDealer());//~9321I~//~9527R~//~9605R~
//      AG.aAccounts.setScore(Ptotal);                             //~9321R~//~9415R~
        AG.aAccounts.setScore(PintssP[0]);                         //~9415I~
  		resetGrilledBird(PendgameType);                            //~9501I~//~9503R~
//     	AG.aNamePlate.showScore();                                 //~9321I~//~0324R~
       	AG.aNamePlate.showScoreClearRiver();                       //~0324I~
//  	Complete.newInstance();                                    //~9321I~
    	Status.endGame(PendgameType,NGTP_GAMEOVER);   //reset ctrGame to 0,affect to getCurrentEswn and OKNGDlg.isdealer()                        //~9321I~//~9401R~//~9605R~
//  	AG.aStarter.showGameSeq(AG.aAccounts.starterRelativePos);  //~9321I~
//  	UserAction.showInfoAll(0/*opt*/,Utils.getStr(R.string.Info_GameEndMinusStop));//~9321I~//~0224R~
//  	UserAction.showInfoAll(0/*opt*/,R.string.Info_GameEndMinusStop);//~0224R~
    	UserAction.showInfo(0/*opt*/,R.string.Info_GameEndMinusStop);//~0224I~
        if (Dump.Y) Dump.println("ScoreDlg.endGame isDealer="+OKNGDlg.isDealer());//~9605I~
//  	if (OKNGDlg.isDealer())                                    //~9322I~//~9605R~
    	if (AG.aAccounts.isFirstDealerReal())                               //~9605I~
        {                                                          //~9415I~
//      	AccountsDlg.newInstance(Ptotal).show();               //~9322I~//~9415R~
        	AccountsDlg.newInstance(PintssP).show();               //~9415R~
        }                                                          //~9415I~
	}                                                              //~9321I~
    //*******************************************************      //~9522I~
    public static void endGameOverByLastGame(int PendgameType,int PtypeClosable,int[] Pscore)//~9522R~//~9524R~
    {                                                              //~9522I~
        if (Dump.Y) Dump.println("ScoreDlg.endGameOverByLastGame endgameType="+PendgameType+",typeClosable="+Integer.toHexString(PtypeClosable)+",score="+Arrays.toString(Pscore)+",isDealer="+OKNGDlg.isDealer());//~9522I~//~9525R~//~9527R~//~9605R~
//  	Status.endGame(PendgameType,NGTP_GAMEOVER);                //~9522I~//~9527R~
        int resid=0;                                               //~9522I~
        switch(PtypeClosable)                                      //~9522I~
        {                                                          //~9522I~
        case CLOSABLE_QUERY_DEALER_RON:                            //~9522I~
        	resid=R.string.Info_GameEndDeclareRon;                 //~9522I~
            break;                                                 //~9522I~
        case CLOSABLE_QUERY_DEALER_PENDING:                        //~9522I~
        	resid=R.string.Info_GameEndDeclarePending;             //~9522I~
            break;                                                 //~9527I~
        case CLOSABLE_GAMEOVER_NONDEALER_RON:                      //~9527I~
        	resid=R.string.Info_GameoverNonDealerRon;              //~9527I~
            break;                                                 //~9527I~
        case CLOSABLE_GAMEOVER_DEALER_NOTPENDING:                  //~9527I~
        	resid=R.string.Info_GameoverDealerNotPending;          //~9527I~
            break;                                                 //~9527I~
        }                                                          //~9522I~
        if (resid!=0)                                              //~9522I~
//  		UserAction.showInfoAll(0/*opt*/,Utils.getStr(resid));   //~9522I~//~0224R~
    		UserAction.showInfoAll(0/*opt*/,resid); //NOLANG       //~0224I~
//  	if (OKNGDlg.isDealer())                                    //~9522I~//~9605R~
    	if (AG.aAccounts.isFirstDealerReal())                               //~9605I~
        {                                                          //~9522I~
	        int[] dummyMinus=new int[PLAYERS];                     //~9522I~
    	    int[][] intss=new int[][]{Pscore,dummyMinus,dummyMinus};//~9522I~//~9524R~
        	AccountsDlg.newInstance(intss,PendgameType,PtypeClosable).show();                 //~9522I~//~9525R~
        }                                                          //~9522I~
	}                                                              //~9522I~
    //*******************************************************      //~9321I~
    //*on requester                                                //~9321I~
    //*******************************************************      //~9321I~
    public void sendConfirmRequest()                               //~9321R~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ScoreDlg:sendConfirmRequest");   //~9321I~
        if (isServer)                                              //~9321I~
//  		sendToAllClientConfirm(ammount,minusScore,minusPay,newTotal,payerInfo);//~9321R~//~9322R~//~9408R~
    		sendToAllClientConfirm(ammount,minusScore,minusPay,newTotal,chargeToGainer,chargeToGainerResult);//~9408I~
        else                                                       //~9321I~
    		sendToServerConfirm();                                  //~9321I~
    }                                                              //~9321I~
    //*******************************************************************//~9321I~
    protected void sendReply(boolean PswOK)                        //~9321I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ScoreDlg.sendReply OK="+PswOK);  //~9321R~
//        CMP.setResultOK(requesterEswn,currentEswn,PswOK);        //~9321R~
//        String msg=requesterEswn+MSG_SEPAPP+currentEswn+MSG_SEPAPP+(PswOK ? "1" : "0");//~9321R~
//        ACC.sendToAll(GCM_COMPRESULT_RESP,msg);                  //~9321R~
        String okng=(PswOK ? "1" : "0");                            //~9321I~
		if (!isServer)                                             //~9321R~
	        AG.aUserAction.sendToServer(GCM_ENDGAME_SCORE,PLAYER_YOU,ENDGAME_SCORE_CONFIRM_REPLY,0,0,okng);//~9321R~
		else                                                       //~9321R~
        {                                                          //~9321I~
        	String msgdata=ENDGAME_SCORE_CONFIRM_REPLY+MSG_SEPAPP2+"0"/*endgameType*/+MSG_SEPAPP2+"0"/*nextGameType*/+MSG_SEPAPP2+okng;//~9321R~
        	AG.aUserAction.sendToTheClientDealer(GCM_ENDGAME_SCORE,msgdata);//~9321I~
        }                                                          //~9321I~
    }                                                              //~9321I~
    //**************************************************************************//~9318I~
    private static void sendToServer(int PendgameType,int PnextgameType,int[] Pamt)//~9318I~//~9321R~
    {                                                              //~9318I~
        String strAmt=Pamt[0]+MSG_SEPAPP+Pamt[1]+MSG_SEPAPP+Pamt[2]+MSG_SEPAPP+Pamt[3];//~9318I~
        if (Dump.Y) Dump.println("ScoreDlg.sendToServer strAmt="+strAmt);//~9708I~
        AG.aUserAction.sendToServer(GCM_ENDGAME_SCORE,PLAYER_YOU,ENDGAME_SCORE_UPDATE,PendgameType,PnextgameType,strAmt);//~9318I~
    }                                                              //~9318I~
    //**************************************************************************//~9321I~
    private void sendToServerConfirm()                             //~9321R~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ScoreDlg.sendToServerConfirm");  //~9321I~
//  	String msgdata=makeReqMsg(ammount,minusScore,minusPay,newTotal);//~9321R~//~9322R~
//  	String msgdata=makeReqMsg(ammount,minusScore,minusPay,newTotal,payerInfo);//~9322I~//~9408R~
    	String msgdata=makeReqMsg(ammount,minusScore,minusPay,newTotal,chargeToGainer,chargeToGainerResult);//~9408I~
        AG.aUserAction.sendToServer(GCM_ENDGAME_SCORE,PLAYER_YOU,ENDGAME_SCORE_CONFIRM_REQUEST,msgdata);//~9321R~
    }                                                              //~9321I~
    //**************************************************************************//~9321I~
//  private void sendToServerEndGame(int[] Ptotal)                 //~9321R~//~9415R~
    private void sendToServerEndGame(int[][] PintssP)              //~9415I~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ScoreDlg.sendToServerEndGame");  //~9321I~
//      String msg=Ptotal[0]+MSG_SEPAPP+Ptotal[1]+MSG_SEPAPP+Ptotal[2]+MSG_SEPAPP+Ptotal[3];//~9321I~//~9415R~
//      String msg=AccountsDlg.makeReqMsg(PintssP);                //~9415I~//~9525R~
        String msg=AccountsDlg.makeReqMsg(PintssP)+MSG_SEPAPP2+EGDR_MINUSSTOP;//~9525I~
        AG.aUserAction.sendToServer(GCM_ENDGAME_SCORE,PLAYER_YOU,ENDGAME_SCORE_CONFIRMED,msg);//~9321I~
    }                                                              //~9321I~
    //**************************************************************************//~9318I~
    private static void sendToAllClientUpdate(int PendgameType,int PnextgameType,int[] Pamt)//~9318R~//~9321R~
    {                                                              //~9318I~
//  	int eswn=AG.aAccounts.playerToEswn(PLAYER_YOU);            //~9318I~//~9321R~
        String strAmt=Pamt[0]+MSG_SEPAPP+Pamt[1]+MSG_SEPAPP+Pamt[2]+MSG_SEPAPP+Pamt[3];//~9318I~
//        String msgdata=eswn+MSG_SEPAPP2+ENDGAME_SCORE_UPDATE+MSG_SEPAPP2+PendgameType+MSG_SEPAPP2+PnextgameType+MSG_SEPAPP2+strAmt;//~9318R~//~9321R~
//        if (Dump.Y) Dump.println("ScoreDlg:sendToAllClient msgdata="+msgdata);//~9318I~//~9321R~
//        AG.aUserAction.sendToClient(true/*swAll*/,false/*swRobot*/,GCM_ENDGAME_SCORE,PLAYER_YOU,msgdata);//~9318I~//~9321R~
        String msgdata=PendgameType+MSG_SEPAPP2+PnextgameType+MSG_SEPAPP2+strAmt;//~9321I~
        if (Dump.Y) Dump.println("ScoreDlg:sendToAllClientUpdate msgdata="+msgdata);//~9321I~
    	sendToAllClient(ENDGAME_SCORE_UPDATE,msgdata);             //~9321I~
    }                                                              //~9318I~
    //**************************************************************************//~9321I~
//  private static void sendToAllClientEndGame(int[] Ptotal)       //~9321I~//~9415R~
    private static void sendToAllClientEndGame(int[][] PintssP)    //~9415I~
    {                                                              //~9321I~
//      String msg=Ptotal[0]+MSG_SEPAPP+Ptotal[1]+MSG_SEPAPP+Ptotal[2]+MSG_SEPAPP+Ptotal[3];//~9321I~//~9415R~
//      String msg=AccountsDlg.makeReqMsg(PintssP);                //~9415I~//~9525R~
        String msg=AccountsDlg.makeReqMsg(PintssP)+MSG_SEPAPP2+EGDR_MINUSSTOP;//~9525I~
        if (Dump.Y) Dump.println("ScoreDlg:sendToAllClientEndGame msgdata="+msg);//~9321I~//~9605R~
    	sendToAllClient(ENDGAME_SCORE_CONFIRMED,msg);             //~9321I~
    }                                                              //~9321I~
    //*******************************************************      //~9321M~
//    private static String makeReqMsg(int[] Pamt,int[] Pscore,int[] Ppay,int[] Ptotal,int[] PpayerInfo)//~9321I~//~9322R~//~9408R~
    private static String makeReqMsg(int[] Pamt,int[] Pscore,int[] Ppay,int[] Ptotal,int[] Pcharge,int[] PchargeResult)//~9408R~
    {                                                              //~9321M~
        StringBuffer sb=new StringBuffer();                        //~9321M~
        sb.append("0 0");	//endgame,nextgame type                //~9321I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9321M~
        	sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Pamt[ii]);     //eswn seq//~9321I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9321I~
        	sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Pscore[ii]);     //eswn seq//~9321I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9321I~
        	sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Ppay[ii]);     //eswn seq//~9321I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9321I~
        	sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Ptotal[ii]);     //eswn seq//~9321I~
//        for (int ii=0;ii<PLAYERS*PLAYERS;ii++)                     //~9322I~//~9408R~
//            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+PpayerInfo[ii]);     //eswn seq//~9322I~//~9408R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9408I~
            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+Pcharge[ii]);     //eswn seq//~9408I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9408I~
            sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+PchargeResult[ii]);     //eswn seq//~9408I~
        String s=sb.toString();                                    //~9321M~
        if (Dump.Y) Dump.println("CompleteDlg.makeReqMsg msg="+s); //~9321M~
        return s;                                                  //~9321M~
    }                                                              //~9321M~
    //**************************************************************************//~9321I~
//  private static void sendToAllClientConfirm(int[] Pamt,int[] Pscore,int[] Ppay,int[] Ptotal,int[] PpayerInfo)//~9321I~//~9322R~//~9408R~
    private static void sendToAllClientConfirm(int[] Pamt,int[] Pscore,int[] Ppay,int[] Ptotal,int[] Pcharge,int[] PchargeResult)//~9408R~
    {                                                              //~9321I~
        if (Dump.Y) Dump.println("ScoreDlg:sendToAllClientConfirm");//~9321I~
//    	String msg=makeReqMsg(Pamt,Pscore,Ppay,Ptotal,PpayerInfo);            //~9321I~//~9322R~//~9408R~
      	String msg=makeReqMsg(Pamt,Pscore,Ppay,Ptotal,Pcharge,PchargeResult);//~9408R~
    	sendToAllClient(ENDGAME_SCORE_CONFIRM_REQUEST,msg); //~9321I~
    }                                                              //~9321I~
    //**************************************************************************//~9321I~
    private static void sendToAllClient(int PendgameMsgid,String Pmsgdata)//~9321I~
    {                                                              //~9321I~
    	int eswn=AG.aAccounts.playerToEswn(PLAYER_YOU);            //~9321I~
        String msgdata=eswn+MSG_SEPAPP2+PendgameMsgid+MSG_SEPAPP2+Pmsgdata;//~9321I~
        if (Dump.Y) Dump.println("ScoreDlg:sendToAllClient msgdata="+msgdata);//~9321I~
        AG.aUserAction.sendToClient(true/*swAll*/,false/*swRobot*/,GCM_ENDGAME_SCORE,PLAYER_YOU,msgdata);//~9321I~
    }                                                              //~9321I~
    //**************************************************************************//~9318I~
    private static void sendToClientDealer(int PscoreMsgtype,int PendgameType,int PnextgameType,int[] Pamt,int[] Pscore)//~9318I~//~9320R~//~9321R~
    {                                                              //~9318I~
        if (Dump.Y) Dump.println("ScoreDlg:sendToClientDealer endgame="+PendgameType+",nextgame="+PnextgameType+",amt="+Arrays.toString(Pamt)+",score="+Arrays.toString(Pscore));//~9320I~//~9321R~
        String strAmt=Pamt[0]+MSG_SEPAPP+Pamt[1]+MSG_SEPAPP+Pamt[2]+MSG_SEPAPP+Pamt[3]+MSG_SEPAPP2//~9321R~
                     +Pscore[0]+MSG_SEPAPP+Pscore[1]+MSG_SEPAPP+Pscore[2]+MSG_SEPAPP+Pscore[3];//~9321I~
        String msgdata=PscoreMsgtype+MSG_SEPAPP2+PendgameType+MSG_SEPAPP2+PnextgameType+MSG_SEPAPP2+strAmt;//~9318I~//~9320R~
        AG.aUserAction.sendToTheClientDealer(GCM_ENDGAME_SCORE,msgdata);       //~9318I~
    }                                                              //~9318I~
    //**************************************************************************//~9318I~
    //*btio msg received or calc btn on server                     //~9318R~
    //PendgameType:EGDR_NORMAL,EGR_MANGAN_RON,EGDR_DRAWN_LAST,EGDR_DRAWN_HW//~9521I~
    //*rc:true:no minus stop                                       //~9321I~
    //**************************************************************************//~9318I~
//  private static boolean calcOnServer(boolean PswReceived,int Pplayer,int PendgameType,int PnextgameType,int[] Pamt)//~9318I~//~9420R~//~9703R~
    private static boolean calcOnServer(boolean PswReceived,int Pplayer,int PnextgameType,int[] Pamt)//~9703R~//~9704R~
    {                                                              //~9318I~
        if (Dump.Y) Dump.println("ScoreDlg:calcOnServer PswReceived="+PswReceived+",player="+Pplayer+",nextgametype="+PnextgameType+",amt="+Arrays.toString(Pamt));//~9318I~//~9703R~//~9704R~
        if (PnextgameType==NGTP_RESET)                             //~9704I~
        	AG.aPlayers.resetReachAll();    //back reachstick to the player~//~9904R~
        AG.aAccounts.updateScore(Pamt/*eswnSeq*/);                 //~9318R~
    	boolean[] swsMinusStop=getMinusStopAccount(AG.aAccounts.score);//~9318I~
        boolean rc=swsMinusStop==null;                             //~9318I~
        if (Dump.Y) Dump.println("ScoreDlg.calcOnServer rc="+rc);  //~9318I~
        return rc;
    }                                                              //~9318I~
    //**************************************************************************//~9322I~
    //*from GC by btn click                                        //~9322I~
    //**************************************************************************//~9322I~
    public static boolean showDismissed()                             //~9322I~//~9904R~
    {                                                              //~9322I~
        if (Dump.Y) Dump.println("ScoreDlg:showDismisswd");        //~9322I~
		if (Utils.isShowingDialogFragment(AG.aScoreDlg))           //~9322I~
        {                                                          //~9322I~
	        if (Dump.Y) Dump.println("ScoreDlg:showDismisswd already shown");//~9322I~
        	return false;                                                //~9322I~//~9904R~
        }                                                          //~9322I~
        if (Status.isGameOver())                                   //~9603R~
        {                                                          //~9322I~
        	UView.showToast(R.string.Err_AlreadyGameOver);       //~9322I~//~9603R~
        	return false;                                                //~9322I~//~9904R~
        }                                                          //~9322I~
        if (AG.aComplete.lastMinusAmmount==null)                   //~9603I~
        {                                                          //~9603I~
        	UView.showToast(R.string.Err_ScoreReviewNoData);       //~9603I~
        	return false;                                                //~9603I~//~9904R~
        }                                                          //~9603I~
        Complete cmp=AG.aComplete;                                 //~9322R~
		if (OKNGDlg.isDealer())                                    //~9322I~
//      	ScoreDlg.newInstance(cmp.lastMinusAmmount,cmp.lastMinusScore).show();//~9322R~//~9413R~
        	ScoreDlg.newInstance(cmp.lastEndGameType,cmp.lastMinusAmmount,cmp.lastMinusScore).show();//~9413I~
        else                                                       //~9322I~
//	    	ScoreDlg.newInstance(cmp.lastMinusAmmount,cmp.lastMinusScore,cmp.lastMinusPay,cmp.lastMinusTotal,cmp.lastMinusPayerInfo).show();//~9322R~//~9408R~
//	    	ScoreDlg.newInstance(cmp.lastMinusAmmount,cmp.lastMinusScore,cmp.lastMinusPay,cmp.lastMinusTotal,cmp.lastMinusCharge,cmp.lastMinusChargeResult).show();//~9408I~//~9413R~
  	    	ScoreDlg.newInstance(cmp.lastEndGameType,cmp.lastMinusAmmount,cmp.lastMinusScore,cmp.lastMinusPay,cmp.lastMinusTotal,cmp.lastMinusCharge,cmp.lastMinusChargeResult).show();//~9413I~
        return true;                                               //~9904I~
    }                                                              //~9322I~
//    //*******************************************************      //~9322I~//~9408R~
//    //*UCheckBoxI                                                  //~9322I~//~9408R~
//    //*******************************************************      //~9322I~//~9408R~
//    @Override                                                      //~9322I~//~9408R~
//    public void onChangedUCB(CompoundButton Pbtn, boolean PisChecked, int Pparm)//~9322I~//~9408R~
//    {                                                              //~9322I~//~9408R~
//        if (Dump.Y) Dump.println("CompleteDlg.onChangedUCB parm="+Pparm+",isChecked="+PisChecked);//~9322I~//~9408R~
//        if (swInitLayout)                                          //~9322I~//~9408R~
//            return;                                                //~9322I~//~9408R~
//        if (Pparm>=UCBP_MINUSSTOP)                                 //~9322I~//~9408R~
//        {                                                          //~9322I~//~9408R~
//            int pp=Pparm-UCBP_MINUSSTOP;                           //~9322I~//~9408R~
//            int payerEswn=payers[pp/PLAYERS];                      //~9322I~//~9408R~
//            int payToEswn=pp%PLAYERS;   //eswn;                    //~9322I~//~9408R~
//            int payerIdx=ACC.currentEswnToMember(payerEswn);        //~9322I~//~9408R~
//            int payToIdx=ACC.currentEswnToMember(payToEswn);        //~9322I~//~9408R~
//            minusPay[payerIdx]+=pointMinusStop*(PisChecked ? -1 :  1);//~9322I~//~9408R~
//            minusPay[payToIdx]+=pointMinusStop*(PisChecked ?  1 : -1);//~9322I~//~9408R~
//            updateTotal();                                         //~9322I~//~9408R~
//        }                                                          //~9322I~//~9408R~
//    }                                                              //~9322I~//~9408R~
}//class                                                           //~v@@@R~
