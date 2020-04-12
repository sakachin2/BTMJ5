//*CID://+DATER~:                             update#= 1063;       //~9708R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~9305I~
import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.game.Accounts;
import com.btmtest.game.Complete;
import com.btmtest.game.GConst;
import com.btmtest.game.Players;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.URadioGroup;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.TestOption.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.utils.Alert.*;
import static com.btmtest.dialog.DrawnDlgHW.*;                      //~9708I~

public class DrawnDlgLast extends DrawnReqDlgLast                 //~9303R~//~9304R~//~9307R~//~9308R~
            implements UCheckBox.UCheckBoxI                        //~9309I~
            ,SuspendAlert.SuspendAlertI                            //~0308I~
            ,URadioGroup.URadioGroupI                              //~9708I~
               ,Alert.AlertI                                        //~9611I~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.drawndlglast;      //~9220I~//~9302R~//~9303R~//~9304R~//~9307R~
    private static final int TITLEID=R.string.Title_DrawnDlgLast;//~9220I~//~9302R~//~9303R~//~9304R~//~9307R~
    private static final int TITLEID_NONDEALER=R.string.Title_DrawnDlgLastReceived;//~9307I~
    private static final String HELPFILE="DrawnDlgLast";                //~9220I~//~9302R~//~9303R~//~9304R~//~9307R~
                                                                   //~9305I~
//  private static final int POINT_REACH  =1000;                   //~9506I~//~9511R~
    private static final int COLOR_RESPNONE=Color.argb(0xff,0xff,0xff,0x00);//yellow//~9305I~
    private static final int COLOR_RESPOK=Color.argb(0xff,0x00,0xff,0x00);  //green//~9305I~
    private static final int COLOR_RESPNG=Color.argb(0xff,0xff,0x66,0x00);//orange//~9305I~
    private static final int COLOR_RESPBOT=Color.argb(0xff,0xc0,0xc0,0xc0);//~9228I~//~9305I~
    private static final int COLOR_YOU=Color.argb(0xff,0x00,0xbf,0xff); //deep sky blue//~9305I~
    private static final int COLOR_SPRITPOS_BG=R.color.spritposid; //~9603I~
    private static final int COLOR_SPRITPOS_FG=Color.argb(0xff,0xff,0xff,0xff);//~9603I~
//  private static final int[] baseMangan=new int[]{8000,12000,16000,24000,32000};//~9309I~//~9413R~
                                                                   //~9423I~
	private static final int[] llsDrawnResultID=new int[]{R.id.lldrawn_result1,R.id.lldrawn_result2,R.id.lldrawn_result3,R.id.lldrawn_result4};//~9413I~
//    private static final int[] rbIDs={R.id.rbContinue,                          //~9305I~//~9309R~//~9708R~
//                                    R.id.rbNext,                              //~9305I~//~9309R~//~9708R~
////                                  R.id.rbNextFieldE,                        //~9305I~//~9309R~//~9526R~//~9708R~
////                                  R.id.rbNextFieldS,                        //~9305I~//~9309R~//~9526R~//~9708R~
////                                  R.id.rbNextFieldW,                        //~9305I~//~9309R~//~9526R~//~9708R~
////                                  R.id.rbNextFieldN,                        //~9305I~//~9309R~//~9526R~//~9708R~
//                         };                                        //~9305I~//~9708R~
    private static final int URGP_NEXTGAME=1;	//listner parm     //~9708I~
//  private static final int POINT_RANKM  =8000;                   //~9309I~//~9420R~
                                                                   //~9309I~
    private static final int UCBP_NOLISTENER=0;                    //~9709I~
    private static final int UCBP_COMPTYPE_NORMAL=1;               //~9309I~
//    private static final int UCBP_COMPTYPE_ERROR=2;                //~9309I~//~0309R~
    private static final int UCBP_PENDING=3;                       //~9309R~
    private static final int UCBP_ERROR=4;                         //~9309R~
    private static final int UCBP_MANGAN=5;                        //~9309R~
    private static final int UCBP_SUSPEND=10;                      //~0308I~
                                                                   //~9309I~
    private int typeNextGame=NEXTGAME_UNKNOWN;                     //~9709R~
    protected URadioGroup rgNextGame;                           //~9303R~//~9305R~//~9309R~
    private int[] respStat;                                        //~9308R~
    private int[] reasonResponse;                                 //~9308I~
    private boolean[] swsPending=new boolean[PLAYERS];               //~9309I~//~9423R~//~9504R~
                                                                   //~9423I~
    private int[] intsPending=new int[PLAYERS];                    //~9423R~
	private static final int PENDING_NO                 =0;        //~9423I~
	private static final int PENDING_ONLY               =1;        //~9423I~
	private static final int PENDING_MANGAN             =2;        //~9423I~
//  private static final int PENDING_MANGAN_BYSETTING   =3;        //~9423I~//~9506R~
//  private static final int PENDING_MANGAN_ASRON       =3;        //~9506I~
	private static final int NOPENDING_MANGAN           =3;        //~9506I~
//  private static final int PENDING_MANGAN_ESWN        =4;        //~9520R~
//  private static final int NOPENDING_MANGAN_ESWN      =5;        //~9520R~
                                                                   //~9423I~
    private boolean[] swsErrLooser=new boolean[PLAYERS];               //~9309I~//~9422R~
    private boolean[] swsMangan=new boolean[PLAYERS];              //~9310I~//~9506R~
    private boolean[] swsReach=new boolean[PLAYERS];              //~9311I~//~9522R~
    private String[] accountNames;                                 //~9309I~
    private String strOK,strNG,strSend,strBot,strNoReply,strDealer;                //~9305I~//~9306R~//~9307R~//~9308R~//~9311R~
    private String strPending,strErr,strMangan,strPendingNo,strManganPending,strReach;       //~9309I~//~9310R~
    private String strNoManganByEswn,strNoManganPendingByEswn;     //~9520I~
    private TextView[] tvsResp;                                   //~9307R~//~9308R~
    private TextView[] tvsRespESWN;                              //~9305I~//~9307R~//~9308R~
    private TextView tvPending;                                 //~9307I~//~9308R~//~9309R~
    private TextView[] tvsSpritPosID;                              //~9603I~
    private TextView tvReach,tvDup,tvPointStick,tvReachRate,tvDupRate;//~9603I~
//  private USpinner spnDrawnMangan;                              //~9307I~//~9308R~//~9413R~
    private TextView tvDrawnMangan;                                //~9413I~
    private LinearLayout[]  llReachers,llPendingS;                 //~9309I~
    private LinearLayout    llllReachers,llPending,llMangan;              //~9308R~//~9309R~
    private boolean swRequester;                                   //~9308I~
    private Players PLS;                                                  //~9307I~//~9308R~
    private Complete CMP;                                          //~9420I~
    private Accounts ACC;                                          //~9421I~
    private int ctrReach,pendingBase,ctrPending;                   //~9309R~//~9424R~
    private boolean swPendingCB;                                   //~9309I~
    private TextView[] tvsName,tvsEswn,tvsAmmount,tvsDrawnType,tvsTotal;    //~9309R~//~9413R~
//  private UCheckBox[] cbsComp;                                   //~9309R~
    private UCheckBox[] cbsErr;                                    //~9309I~
    private UCheckBox[] cbsPending,cbsMangan;                      //~9309I~
    private int[] accountEswn;                                     //~9309I~
    private int[] totalAmmount=new int[PLAYERS];                   //~9309R~
    private int[] totalAmmountWithErr=new int[PLAYERS];            //~9424I~
//  private int[] minusTotal=new int[PLAYERS];                     //~9413I~//~9414R~
    private int[] receivedDialogData;                              //~9310I~
    private boolean	swInitLayout;//,swCompError;//,swReceived;                      //~9309R~//~9310R~//~9311R~//~9424R~
//  private UCheckBox cbError;                                     //~9309I~//~9424R~
    private int dataType;                                          //~9311I~
    private Button btnTotal;                                   //~9311I~//~9318R~
    private boolean swContinue,swMangan;                          //~9319I~//~9506R~
    private int pointDrawnMangan;                                  //~9413I~
    private String rankDrawnMangan;                                //~9413I~
//  private boolean /*swAvailableDrawnMangan,swDrawnManganPending,*/swDrawnManganNext;//swDrawnManganRon;       //~9413I~//~9422R~//~9424R~//~9425R~//~9505R~
    private boolean /*swDrawnManganAsDrawn,*/swDrawnManganAsRon;                          //~9505R~//~9506R~
    private boolean swDrawnManganAvailable;                        //~9520I~
    private boolean swDrawnMangan;                                 //~9506I~
    private boolean swMultiRon,swMultiRonPointDupAll;              //~9506I~
                                                                   //~9414I~
//  private int[][] amtssDrawnMangan=new int[PLAYERS][];  //payTo[looserEswn][gainerEswn]                           //~9219I~//~9223R~//~9414R~//~9421R~//~9422R~
    private int[][] amtssDrawnMangan=new int[PLAYERS][];  //payTo[gainer][looser]//~9421I~//~9422R~
    private int[] amtsDrawnMangan=new int[PLAYERS]; 	 //eswn base//~9422I~
    private int[][] amtssError=new int[PLAYERS][];                  //~9403I~//~9414I~//~9422R~
    private int[] amtsError=new int[PLAYERS];                     //~9420I~//~9422R~
//  private int[] amtPending=new int[PLAYERS];                     //~9420I~//~9421R~
    private int[][] amtssPending=new int[PLAYERS][]; //[gainerEswn][payerEswn]//~9420R~//~9422R~
    private boolean[] swPendingForAmmount=new boolean[PLAYERS];        //~9310I~//~9420I~
    private boolean[] swsStopPending,swsStopMangan;                //~9420I~
    private boolean[] swsNoManganByEswn=new boolean[PLAYERS];      //~9520I~
    private int[] minusPrize=new int[PLAYERS];                     //~9420R~
    private int[] chargeToGainer=new int[PLAYERS];                 //~9421I~
    private int[] adjustedScore=new int[PLAYERS];	//position seq                                   //~9421R~//~9422R~
    private int[] adjustedScoreWithErr=new int[PLAYERS];	//position seq//~9424I~
    private int[] adjustedScoreByMangan=new int[PLAYERS];	//position seq oldscore+mangan//~9422I~
    private int ctrMangan;                                         //~9420I~
    private int pointMinusStop,dupRate;                                    //~9420I~//~9506R~
    private boolean swMinusStop,swMinus0,swMinusRobot,swMinusPay,swMinusPayGetAllPoint;//~9421I~
    private int gameDup,gameReach;                                 //~9506I~
    private int cutEswn;                                           //~9603I~
    private int[] oldReasonResponse;                               //~9610I~
    private boolean swUpdatedReason;                               //~9610I~
    private int reloadEswn,reloadDataType;                         //~9610I~
    private boolean  swReload;                                     //~9610I~//~9611R~
    private boolean swIgnoreAlertResponse;                         //~9611I~
    private boolean	swErr;	//some Error chked                     //~9708I~
    private int widthTileImage;                                    //~9927I~
    private LinearLayout llReacherEswn,llPendingEswn;              //~9927I~
    private boolean swHideResponseEswn;                            //~0217I~
    protected LinearLayout llEswnResponse;                         //~0217I~
    private boolean swConfirmedSuspend,swConfirmedSuspendOK;       //~0308I~
	private UCheckBox cbSuspend;                                   //~0308I~
    private boolean swSuspend;                                     //~0308I~
	public int widthPendingEswn,widthReacherEswn;                //~0327I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public DrawnDlgLast()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("DrawnDlgLast.defaultConstructor"); //~9221R~//~9302R~//~9303R~//~9304R~//~9307R~
        AG.aUAEndGame.dlgConfirmLast=this;                         //~9611I~
        PLS=AG.aPlayers;                                           //~9307I~
        CMP=AG.aComplete;                                          //~9420I~
        ACC=AG.aAccounts;                                          //~9421I~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
//  public static DrawnDlgLast newInstance(int Preason,int[] PreasonResp,int[] PrespStat)             //~9302I~//~9303R~//~9304R~//~9305R~//~9307R~//~9308R~//~9310R~
//  public static DrawnDlgLast newInstance(int Preason,int[] PreasonResp,int[] PrespStat,int[] PdialogData)//~9310I~//~9311R~
    public static DrawnDlgLast newInstance(int Peswn,int PdataType,int[] PreasonResp,int[] PrespStat,int[] PdialogData)//~9311I~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("DrawnDlgLast.newInstance eswn="+Peswn+",dataType="+PdataType+",reason="+Arrays.toString(PreasonResp)+",respstat="+Arrays.toString(PrespStat)+",dialogData="+Arrays.toString(PdialogData));//~9311R~
    	DrawnDlgLast dlg=new DrawnDlgLast();                                     //~v@@@I~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~//~9307R~
    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9227R~
    			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_CLOSEBTN|FLAG_HELPBTN|FLAG_RULEBTN,//~v@@@I~//~9220R~//~9305R~//~9708R~
				TITLEID,HELPFILE);         //~v@@@I~               //~9220R~
//      dlg.reason=Preason;                                        //~9304M~//~9311R~
        dlg.reason=PrespStat[Peswn];	//OK/NG                    //~9311I~
        dlg.dataType=PdataType;                                    //~9311I~
        dlg.respStat=PrespStat;                                    //~9308R~//~9311R~
        dlg.reasonResponse=PreasonResp;                            //~9308I~//~9311R~
        dlg.oldReasonResponse=PreasonResp.clone(); //to chk updated//~9610I~
        dlg.receivedDialogData=PdialogData;                        //~9310I~//~9311R~
//      dlg.swSuspend=false;                                       //~0308I~//~0314R~
        dlg.swSuspend=PdialogData[PARMPOS_DRAWN_LAST_SUSPEND-PARMPOS_DRAWN_DIALOGDATA]!=0;//~0308I~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
//    //******************************************                   //~v@@@M~//~9303R~
//    @Override                                                      //~9221I~//~9303R~
//    public void onPause()                                          //~9221I~//~9303R~
//    {                                                              //~9221I~//~9303R~
//        super.onPause();                                         //~9302R~//~9303R~
//        if (Dump.Y) Dump.println("DrawnDld:onPause issue dismiss");//~9221R~//~9302R~//~9303R~//~9304R~
//        dismiss();  //because hard to make Complete.Status.ammount to parcelable//~9221I~//~9302R~//~9303R~
//    }                                                              //~9221I~//~9303R~
//    //******************************************                 //~9303R~
    //******************************************                   //~9927I~
    //*at onResume                                                 //~9927I~
    //******************************************                   //~9927I~
    @Override                                                      //~9927I~
    protected int getDialogWidth()                                 //~9927I~
    {                                                              //~9927I~
		int ww=widthTileImage;                                     //~9927I~//~9B11R~
//    if (false) //TODO test                                       //~0327R~
        if (ww!=0)                                                 //~9927I~//~9B11R~
			ww+=AG.dialogPaddingHorizontal;                        //~9927I~//~9B11R~
    	int ww2=getDialogWidthSmallDevicePortraitWithRate(RATE_SMALLDEVICE_WIDTH);//~9B11I~
        int ww3=Math.max(ww,ww2);                                  //~9B11I~
        int ww4=Math.min(AG.scrWidth,ww3);                         //~0326I~
        if (Dump.Y) Dump.println("DrawnDlgLast.getDialogWidth swPortrait="+AG.portrait+",ww="+ww+",ww2="+ww2+",ww3="+ww3+",ww4="+ww4+",widthTileImage="+widthTileImage+",dialogPaddingHorizontal="+AG.dialogPaddingHorizontal+",AG.scrWidth="+AG.scrWidth);//~9927I~//~9B11R~//~0326R~
//      return ww3;                                                 //~9927I~//~9B11R~//~0326R~
        return ww4;                                                //~0326I~
    }                                                              //~9927I~
    @Override                                                    //~9303R~//~9305R~
    protected void initLayout(View PView)                            //~v@@@I~//~9303R~//~9305R~
    {                                                              //~v@@@M~//~9303R~//~9305R~
        if (Dump.Y) Dump.println("DrawnDlgLast.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9303R~//~9304R~//~9305R~//~9307R~
        swInitLayout=true;                                         //~9309I~
        swRequester=ACC.getCurrentDealerReal()==PLAYER_YOU;	//dialer//~9307R~//~9311R~
//        swReceived=receivedDialogData!=null;                       //~9310I~//~9311R~
//        if (swReceived)                                            //~9310I~//~9311R~
//            parseValueSendData(receivedDialogData,0);             //~9310I~//~9311R~//~0314R~
        super.initLayout(PView);                              //~9307I~//~9308R~
        setupByParm();                                             //~9311I~//~9423M~
//      getRuleSetting();                                                           //~v@@@I~//~9212R~//~9302M~//~9303R~//~9305R~//~9307R~//~9308I~
//      setupValue();                                              //~9212I~//~9219M~//~9302R~//~9303R~//~9305R~//~9307M~//~9308R~
//      setButton();                                           //~9221I~//~9302R~//~9303R~//~9305R~//~9307M~//~9308I~
//      setRadioGroup(PView);                                      //~9302I~//~9303R~//~9305R~//~9307R~//~9308R~//~9709R~
//      setTitle();                                                //~v@@@I~//~9220R~//~9302R~//~9303R~//~9305R~//~9307I~//~9308I~
		if (swRequester)                                           //~9B12I~
        	chkContinuedErr();                                     //~9B12I~
        setupAmmount(PView);                                       //~9309I~
        setupTextViewResp(PView);                                      //~9305M~//~9307M~
        hideResponseEswn(!swRequester);                            //~0217M~
//      setupNext(PView);                                     //~9308R~//~9309R~//~9709R~
        setupMangan(PView);                                   //~9307I~//~9309R~
	    setupErr(PView);                                           //~9309I~
        setupReach(PView);                                         //~9307I~
        setupPending(PView);                                       //~9307I~
                                                                   //~9308I~
//      Arrays.fill(swsPending,false);                             //~9309R~//~9504R~
        showMangan();                                              //~9309I~
        showReach();                                               //~9308I~//~9309M~
        showErr();                                                 //~9B11I~
	    showPending();                                             //~9308I~
	    showAmmount();                                             //~9309I~
        setupNext(PView);                                          //~9709I~
                                                                   //~9308I~
        update(true);                                                  //~9305M~//~9308R~
        swInitLayout=false;                                        //~9309I~
    }                                                              //~v@@@M~//~9303R~//~9305R~
    //******************************************                   //~9413I~
    @Override                                                      //~9413I~
    protected void initLayoutAdditional(View PView)                //~9413I~
    {                                                              //~9413I~
        if (Dump.Y) Dump.println("DrawnDlgLast.initLayoutAdditional");//~9413I~
//  	if (PrefSetting.isNoRelatedRule())                         //~9520I~//~9708R~
//      	((LinearLayout)UView.findViewById(PView,R.id.llRelatedRule)).setVisibility(View.GONE);//~9520I~//~9708R~
//      else                                                       //~9520I~//~9708R~
//      {                                                          //~9520I~//~9708R~
            RuleSetting.setPendingCont(PView,true/*swFixed*/);     //~9709I~
            RuleSettingYaku.setDrawnMangan(PView,true/*swFixed*/);         //~9422I~//~9520R~
            if (swDrawnManganAvailable)                            //~9520R~
                RuleSetting.setMultiRon(PView,true/*swFixed*/);    //~9520R~
            else                                                   //~9520R~
                ((LinearLayout)UView.findViewById(PView,R.id.llRuleMultiRon)).setVisibility(View.GONE);//~9520R~
//      }                                                          //~9520I~//~9708R~
    	if (!swDrawnManganAsRon)                                   //~9604I~
        	((LinearLayout)UView.findViewById(PView,R.id.llPointStick)).setVisibility(View.GONE);//~9604I~
                                                                   //~9604I~
    	Rect r=Status.getGameSeq();                                //~9506M~
        gameDup=r.right;                                           //~9506M~
        gameReach=r.bottom;                                        //~9506M~
//      if((TestOption.option & TO_DRAWNREQDLG_LAST)!=0) // TODO TEST //~9311I~//~9417R~//~9420R~//~9506M~//~9511M~//~9524R~
        if((TestOption.option2 & TO2_DRAWNLAST_SHOW_STICK)!=0) // TODO TEST//~9524I~
        {                                                          //~9506M~//~9511M~
            if (r.left==0 && r.top==0 && r.right==0)                             //~9511I~//~9525R~
            {                                                      //~9511I~
    	    Status.addReach();                                    //~9506I~//~9511M~
    	    Status.addReach();                                     //~9506I~//~9511M~
    	    Status.addReach();                                     //~9506I~//~9511M~
        	AG.aStatus.gameCtrDup=11;                              //~9506I~//~9510R~//~9511M~
	    	r=Status.getGameSeq();                                 //~9513I~
        	gameReach=r.bottom;                                    //~9513R~
            }                                                      //~9511I~
        }                                                          //~9506M~//~9511M~
    }                                                              //~9413I~
    //******************************************                   //~0217I~
    private void hideResponseEswn(boolean PswHide)                 //~0217R~
    {                                                              //~0217I~
    	if (Dump.Y) Dump.println("DrawnDlgLast.hideResponseEswn swHide="+PswHide);//~0217R~
    	swHideResponseEswn=PswHide;                                //~0217I~
        if (PswHide)                                               //~0217I~
    	    llEswnResponse.setVisibility(View.GONE);               //~0217I~
    }                                                              //~0217I~
    //******************************************                   //~9311I~
    private void setupByParm()                                     //~9311I~
    {                                                              //~9311I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setupByReasonParm dataType="+dataType);//~9311I~
    	setupByReasonResp();                                        //~9311I~
        if (dataType==LASTDT_DIALOG)                               //~9311I~
	    	setupByDialogDataReceived();                            //~9311I~
    }                                                              //~9311I~
    //******************************************                   //~9311I~
    private void setupByReasonResp()                               //~9311I~
    {                                                              //~9311I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setByReasonResp reasonResp="+Arrays.toString(reasonResponse));//~9311I~
        swMangan=false;                                            //~9506I~
        Arrays.fill(swsNoManganByEswn,false);                      //~9520I~
        int ctrM=0;                                                //~9520I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9311I~
        {                                                          //~9311I~
        	switch(reasonResponse[ii])                                 //~9311I~
            {                                                      //~9311I~
            case EGDR_PENDING:                                     //~9311I~
            	swsPending[ii]=true;                               //~9311I~//~9423R~//~9504R~
            	intsPending[ii]=PENDING_ONLY;                      //~9423I~
                swsMangan[ii]=false;                               //~9311I~//~9506R~
            	break;                                             //~9311I~
            case EGDR_PENDINGNO:                                   //~9311I~
            	swsPending[ii]=false;                              //~9311I~//~9423R~//~9504R~
            	intsPending[ii]=PENDING_NO;                        //~9423I~
                swsMangan[ii]=false;                               //~9311I~//~9506R~
            	break;                                             //~9311I~
            case EGDR_MANGAN_PENDING:   //mangan and pending       //~9506R~
            	swsPending[ii]=true;                               //~9311I~//~9423R~//~9504R~
            	intsPending[ii]=PENDING_MANGAN;                    //~9423I~
                swsMangan[ii]=true;                                //~9311I~//~9506R~
                ctrM++;                                            //~9520I~
                swMangan=true;                                     //~9506I~
            	break;                                             //~9311I~
//            case EGDR_MANGAN_PENDING_BYSETTING:                    //~9423I~//~9506R~
//                swsPending[ii]=true;                               //~9423I~//~9504R~//~9506R~
//                intsPending[ii]=PENDING_MANGAN_BYSETTING;          //~9423I~//~9506R~
//                swsMangan[ii]=true;                                //~9423I~//~9506R~
//                break;                                             //~9423I~//~9506R~
//            case EGDR_MANGAN_RON:   //treate as Ron              //~9506I~
//                swsPending[ii]=true;                             //~9506I~
//                intsPending[ii]=PENDING_MANGAN_ASRON;            //~9506I~
//                swsMangan[ii]=true;                              //~9506I~
//                break;                                           //~9506I~
            case EGDR_MANGAN:      //mangan with No-Pending        //~9311I~//~9506R~
//          	swsPending[ii]=swDrawnManganPending;               //~9423R~//~9504R~
            	swsPending[ii]=false;                              //~9423I~//~9504R~
            	intsPending[ii]=NOPENDING_MANGAN;                       //~9423I~//~9506R~
                swsMangan[ii]=true;                                //~9311I~//~9506R~
                ctrM++;                                            //~9520I~
                swMangan=true;                                     //~9506I~
            	break;                                             //~9311I~
            case EGDR_NONE:                                        //~9311I~
            	swsPending[ii]=false;                              //~9311I~//~9423R~//~9504R~
            	intsPending[ii]=PENDING_NO;                       //~9423I~
                swsMangan[ii]=false;                               //~9311I~//~9506R~
            	break;                                             //~9311I~
            default:                                               //~9311I~
        		if (Dump.Y) Dump.println("DrawnDlgLast.setByReasonResp invalid ReasonResp");//~9311I~
            }                                                      //~9311I~
            if (ctrM>1 && !swMultiRon)                             //~9520I~
            {                                                      //~9520I~
		        if (Dump.Y) Dump.println("DrawnDlgLast.setByReasonResp ii="+ii+",ctr="+ctrM+",swsNoManganByEswnintsPending="+Arrays.toString(swsNoManganByEswn));//~9520I~
		        swsNoManganByEswn[ii]=true;                        //~9520I~
            }                                                      //~9520I~
        }                                                          //~9311I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setByReasonResp intsPending="+Arrays.toString(intsPending)+",swsMangan="+Arrays.toString(swsMangan));//~9311I~//~9423R~//~9506R~
        if (Dump.Y) Dump.println("DrawnDlgLast.setByReasonResp swPending="+Arrays.toString(swsPending));//~9423I~//~9504R~
    }                                                              //~9311I~
    //******************************************                   //~9311I~
    private void setupByDialogDataReceived()                       //~9311I~
    {                                                              //~9311I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setByDialogDataReceived="+Arrays.toString(receivedDialogData));//~9311I~
    	parseValueSendData(receivedDialogData,0/*pos*/);                  //~9311I~//~0314R~
    }                                                              //~9311I~
    //******************************************                   //~9212I~//~9303R~//~9307R~
    @Override
    protected void getRuleSetting()                                      //~9212I~//~9303R~//~9307R~
    {                                                              //~9212I~//~9303R~//~9307R~
        if (Dump.Y) Dump.println("DrawnDlgLast.getRuleSetting");   //~9307I~
    	pointDrawnMangan=RuleSettingYaku.getDrawnManganPoint();        //~9413I~
    	rankDrawnMangan=RuleSettingYaku.getDrawnManganRank();          //~9413I~
//  	swAvailableDrawnMangan=RuleSetting.isAvailableDrawnMangan();//~9413I~//~9425R~
//  	swDrawnManganRon=RuleSetting.isDrawnManganRon();           //~9413I~//~9422R~
//  	swDrawnManganPending=RuleSetting.isDrawnManganPending();   //~9422I~//~9425R~
//  	swDrawnManganNext=RuleSetting.isDrawnManganNext(); //~9422I~//~9424R~//~9505R~
    	swDrawnManganAvailable=RuleSettingYaku.isDrawnManganAvailable();//~9520I~
    	swDrawnManganAsRon=RuleSettingYaku.isDrawnManganAsRon();   //~9505R~//~9506R~
//  	swDrawnManganAsDrawn=RuleSetting.isDrawnManganAsDrawn();   //~9506I~
        pendingBase=RuleSetting.getPendingBase();                  //~9309R~
        if (Dump.Y) Dump.println("DrawnDlgLast.getRuleSetting DrawnManganAsRon="+swDrawnManganAsRon+",point="+pointDrawnMangan+",pendingbase="+pendingBase);//~9413I~//~9422R~//~9424R~//~9425R~//~9505R~//~9506R~
//    	swContinue=RuleSetting.isContinueDrawnHW();                //~9319I~//~9424R~
    	pointMinusStop=RuleSetting.getPointMinusStop();            //~9420I~
    	swMinusStop=RuleSetting.isMinusStop();                     //~9421I~
    	swMinus0=RuleSetting.isZeroStop();                         //~9421I~
    	swMinusRobot=RuleSetting.isMinusStopRobot();               //~9421I~
    	swMinusPay=RuleSetting.isMinusPay();                       //~9421I~
    	swMinusPayGetAllPoint=RuleSetting.isMinusPayGetAllPoint(); //~9421I~
    	swMultiRon=RuleSetting.isMultiRon();                       //~9506I~
    	swMultiRonPointDupAll=RuleSetting.isMultiRonPointDupAll(); //~9506I~
    	dupRate=RuleSetting.getDupRate();                          //~9212I~//~9506I~
    	cutEswn=ACC.getCutEswn();                                  //~9603I~
    }                                                              //~9212I~//~9303R~//~9307R~
    //******************************************                   //~9302I~//~9303R~//~9304R~
    @Override                                                      //~9305I~
    protected void setupValue()                                      //~9302I~//~9303R~//~9304R~
    {                                                              //~9302I~//~9303R~//~9304R~
        if (Dump.Y) Dump.println("DrawnDlgLast.setupValue reason="+reason);//~9304I~//~9307R~
        super.setupValue();                                        //~9304I~//~9307M~
        strBot=Utils.getStr(R.string.YournameRobot);               //~9308I~
        strOK=Utils.getStr(R.string.OK);                           //~9308I~
        strNG=Utils.getStr(R.string.NG);                           //~9308I~
        strDealer=Utils.getStr(R.string.Label_DealerNow);           //~9311I~
        strSend=Utils.getStr(R.string.Send);                       //~9308I~
        strNoReply=Utils.getStr(R.string.NoReply);                 //~9308I~
        strPending=Utils.getStr(R.string.Label_Pending);           //~9309I~
        strErr=Utils.getStr(R.string.AbnormalGain);                //~9309I~
        strMangan=Utils.getStr(R.string.Label_DrawnMangan);        //~9309I~
        strManganPending=Utils.getStr(R.string.Label_DrawnManganPending);//~9310I~//~9423R~
        strNoManganByEswn=Utils.getStr(R.string.Label_DrawnNoManganByEswn);//~9520I~
        strNoManganPendingByEswn=Utils.getStr(R.string.Label_DrawnNoManganPendingByEswn);//~9520I~
        strPendingNo=Utils.getStr(R.string.Label_PendingNo);       //~9309I~
        strReach=Utils.getStr(R.string.LabelReachers);              //~9423I~
//  	accountNames=ACC.getAccountNames();                        //~9309I~//~9416R~
    	accountNames=ACC.getAccountNamesByPosition();              //~9416I~
//  	accountEswn=ACC.getCurrentEswnByAccount();                 //~9309I~//~9416R~
    	accountEswn=ACC.getCurrentEswnByPosition(); //position to eswn//~9416I~
//      Arrays.fill(swsErrLooser,false);                               //~9309R~//~9422R~
//        if ((TestOption.option & TO_DRAWNREQDLG_LAST)!=0)        //  0x800000;  //drawndlg last//~9308I~//~9425R~
//            setupValueTest();                                       //~9308I~//~9425R~
    }                                                              //~9302I~//~9303R~//~9304R~
//    //******************************************                   //~9308I~//~9425R~
//    protected void setupValueTest()                                //~9308I~//~9425R~
//    {                                                              //~9308I~//~9425R~
//        if (Dump.Y) Dump.println("DrawnDlgLast.setupValueTest");   //~9308I~//~9425R~
////        int p=AG.aAccounts.mapDummyPlayerByCurrentEswn(3);       //~9308R~//~9425R~
////        AG.aPlayers.setReachDone(p);                             //~9308R~//~9425R~
////        p=AG.aAccounts.mapDummyPlayerByCurrentEswn(Players.nextPlayer(p));//~9308R~//~9425R~
////        AG.aPlayers.setReachDone(p);                             //~9308R~//~9425R~
//    }                                                              //~9308I~//~9425R~
    //******************************************                   //~v@@@I~//~9220R~//~9303R~//~9306R~
    @Override                                                      //~9306I~
    protected void setTitle()                                        //~v@@@I~//~9220R~//~9303R~//~9306R~
    {                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
        Spanned s=Status.getSpannedGameTitle(Utils.getStr(swRequester ? TITLEID :TITLEID_NONDEALER));//~9306I~
        androidDlg.setTitle(s);                                    //~v@@@R~//~9220R~//~9302R~//~9303R~//~9306R~
    }                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
    //*******************************************************      //~v@@@I~//~9303R~//~9304R~
    @Override                                                      //~v@@@I~//~9303R~//~9304R~
    public void onDismissDialog()                                  //~v@@@I~//~9303R~//~9304R~
    {                                                              //~v@@@I~//~9303R~//~9304R~
        if (Dump.Y) Dump.println("DrawnDlgLast.onDismissDialog swReload="+swReload);               //~v@@@I~//~9303R~//~9304R~//~9308R~//~9611R~
        UAEG.dlgConfirmLast=null;                                      //~9303R~//~9304R~//~9307R~
        if (swReload)                                              //~9610I~//~9611R~
        {	                                                       //~9610I~
        	swReload=false;                                        //~9610I~//~9611R~
            reloadDlg();                                           //~9610I~
        }                                                          //~9610I~
    }                                                              //~v@@@I~//~9303R~//~9304R~
    //*******************************************************      //~9302I~//~9303R~//~9306R~//~9307R~//~9308R~
    @Override                                                      //~9306I~//~9307R~//~9308R~
    public void setButton()                                        //~9221I~//~9303R~//~9306R~//~9307R~//~9308R~
    {                                                              //~9221I~//~9303R~//~9306R~//~9307R~//~9308R~
        if (Dump.Y) Dump.println("DrawnDlgLast.setButton");        //~9709I~
        btnTotal = UButton.bind(layoutView,R.id.ShowTotal,this);      //~9311I~//~9318R~//~9610M~
        if (swRequester)                                           //~9306I~//~9307R~//~9308R~
        {                                                          //~9306I~//~9307R~//~9308R~
            btnOK.setText(strSend);                                 //~9306R~//~9307R~//~9308R~
            btnCancel.setVisibility(View.GONE);                    //~9306I~//~9307R~//~9308R~
        	btnTotal.setVisibility(View.VISIBLE);               //~9610I~
        }                                                          //~9306I~//~9307R~//~9308R~
        else                                                       //~9306I~//~9307R~//~9308R~
        {                                                          //~9306I~//~9307R~//~9308R~
            btnOK.setText(strOK);                                   //~9306I~//~9307R~//~9308R~
        }                                                          //~9306I~//~9307R~//~9308R~
    }                                                              //~9221I~//~9303R~//~9306R~//~9307R~//~9308R~
    //******************************************                   //~9309I~
    protected void setupAmmount(View PView)                        //~9309I~
    {                                                              //~9309I~
        tvReach         =(TextView)    UView.findViewById(PView,R.id.pointReach);//~9603I~
        tvReachRate     =(TextView)    UView.findViewById(PView,R.id.pointReachRate);//~9603I~
        tvDup           =(TextView)    UView.findViewById(PView,R.id.pointContinued);//~9603I~
        tvDupRate       =(TextView)    UView.findViewById(PView,R.id.pointDupRate);//~9603I~
        tvPointStick    =(TextView)    UView.findViewById(PView,R.id.stickPoint);//~9603I~
                                                                   //~9603I~
          tvsEswn=new TextView[PLAYERS];                           //~9413I~
          tvsSpritPosID=new TextView[PLAYERS];                     //~9603I~
          tvsAmmount=new TextView[PLAYERS];                        //~9413I~
          tvsTotal=new TextView[PLAYERS];                          //~9413I~
          tvsName=new TextView[PLAYERS];                           //~9413I~
          tvsDrawnType=new TextView[PLAYERS];                      //~9413I~
		  for (int ii=0;ii<PLAYERS;ii++)                           //~9413I~
          {                                                        //~9413I~
			LinearLayout   ll=(LinearLayout)UView.findViewById(PView,llsDrawnResultID[ii]);//~9413I~
			tvsEswn[ii]      =(TextView)    UView.findViewById(ll,R.id.nameESWN);//~9413I~
			tvsSpritPosID[ii]=(TextView)    UView.findViewById(ll,R.id.tvSpritPosID);//~9603I~
			tvsDrawnType[ii] =(TextView)    UView.findViewById(ll,R.id.tvDrawnType);//~9413I~
			tvsAmmount[ii]   =(TextView)    UView.findViewById(ll,R.id.ammountESWN);//~9413I~
			tvsTotal[ii]     =(TextView)    UView.findViewById(ll,R.id.totalESWN);//~9413I~
			tvsName[ii]      =(TextView)    UView.findViewById(ll,R.id.memberName);//~9413I~
          }                                                        //~9413I~
                                                                   //~9309I~
        setAccountName();                                          //~9309I~
    }                                                              //~9309I~
    //******************************************                   //~9309I~
    protected void setAccountName()                                //~9309I~
    {                                                              //~9309I~
    	if (Dump.Y) Dump.println("CompleteDlg.setAccountName");    //~9309I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9309I~
        {                                                          //~9309I~
        	tvsName[ii].setText(accountNames[ii]);	//by Position                 //~9309I~//~9513R~
	        tvsEswn[ii].setText(GConst.nameESWN[accountEswn[ii]]); //~9309I~
          if (swDrawnManganAsRon)                                  //~9605R~
	        if (accountEswn[ii]==cutEswn)                          //~9603I~
            {                                                      //~9603I~
	        	tvsSpritPosID[ii].setTextColor(COLOR_SPRITPOS_FG); //~9603I~
	        	tvsSpritPosID[ii].setBackgroundResource(COLOR_SPRITPOS_BG);//~9603I~
	        	tvsSpritPosID[ii].setText(Utils.getStr(R.string.Label_SpritPosID));//~9603I~
            }                                                      //~9603I~
        }                                                          //~9309I~
	    setPointStick();                                           //~9603I~
    }                                                              //~9309I~
    //******************************************                   //~9603I~
    private void setPointStick()                                   //~9603I~
    {                                                              //~9603I~
        gameReach+=AG.aPlayers.ctrReach;	                       //~9603R~
    	int reach=gameReach*POINT_REACH;                           //~9603I~
        int dup=gameDup*dupRate;                                   //~9603I~
        int pointStick=reach+dup;                                  //~9603I~
        tvReach.setText(Integer.toString(gameReach));              //~9603I~
        tvReachRate.setText("x"+POINT_REACH);                      //~9603I~
        tvDup.setText(Integer.toString(gameDup));                  //~9603I~
        tvDupRate.setText("x"+dupRate);                            //~9603I~
        tvPointStick.setText(Integer.toString(pointStick));        //~9603I~
    	if (Dump.Y) Dump.println("DrawnDlgLast.setPointStick="+pointStick);//~9603I~
    }                                                              //~9603I~
    //******************************************                   //~9308I~
    protected void setupTextViewResp(View PView)                   //~9308I~
    {                                                              //~9308I~
        llEswnResponse=(LinearLayout)UView.findViewById(layoutView,R.id.llEswnResponseLine);//~0217I~//~0317R~
      	TextView tvResp1,tvResp2,tvResp3,tvResp4;                  //~9308I~
      	TextView tvRespE,tvRespS,tvRespW,tvRespN;                  //~9308I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setupTextView");      //~9308I~//~9709R~
        tvResp1         =(TextView)    UView.findViewById(PView,R.id.ReplyE);//~9308I~
        tvResp2         =(TextView)    UView.findViewById(PView,R.id.ReplyS);//~9308I~
        tvResp3         =(TextView)    UView.findViewById(PView,R.id.ReplyW);//~9308I~
        tvResp4         =(TextView)    UView.findViewById(PView,R.id.ReplyN);//~9308I~
        tvRespE         =(TextView)    UView.findViewById(PView,R.id.LabelReplyE);//~9308I~
        tvRespS         =(TextView)    UView.findViewById(PView,R.id.LabelReplyS);//~9308I~
        tvRespW         =(TextView)    UView.findViewById(PView,R.id.LabelReplyW);//~9308I~
        tvRespN         =(TextView)    UView.findViewById(PView,R.id.LabelReplyN);//~9308I~
        tvsResp=new TextView[]{tvResp1,tvResp2,tvResp3,tvResp4};   //~9308I~
        tvsRespESWN=new TextView[]{tvRespE,tvRespS,tvRespW,tvRespN};//~9308I~
    }                                                              //~9308I~
    //*******************************************************      //~9308I~
    @Override //DrawnReqDlgLast                                    //~9709I~
    protected void setRadioGroup(View PView)                       //~9308I~
    {                                                              //~9308I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setRadioGroup swContinue="+swContinue);    //~9308I~//~9709R~
        rgNextGame= new URadioGroup(PView,R.id.rgNextGame,URGP_NEXTGAME/*parm*/,rbIDsNGTP);//~9708I~
        rgNextGame.setListener(this);                              //~9708I~
//      rgNextGame.setVisibility(RBIDNEXT_NEXTPLAYER,View.GONE);   //~9708I~//~9709R~
//      setCheckNextGame();                                        //~9708I~//~9709R~
		cbSuspend=new UCheckBox(PView,R.id.cbSuspend);             //~0308I~
		cbSuspend.setState(swSuspend,!swRequester);                //~0308I~
        cbSuspend.setListener(this,UCBP_SUSPEND);                  //~0308I~
    }                                                              //~9308I~
    //*******************************************************      //~9308I~
    private void setupMangan(View PView)                    //~9307I~//~9308I~//~9309R~
    {                                                              //~9307I~//~9308M~
//      spnDrawnMangan=  new USpinner(PView,R.id.spnDrawnMangan);  //~9307I~//~9308M~//~9413R~
//      spnDrawnMangan.setArray(R.array.DrawnManganRank);          //~9307I~//~9308M~//~9413R~
        tvDrawnMangan=(TextView)UView.findViewById(PView,R.id.tvDrawnManganPoint);//~9413I~
//      tvDrawnMangan.setText(Integer.toString(pointDrawnMangan)); //~9413R~
        tvDrawnMangan.setText(rankDrawnMangan);  //~9413I~
        llMangan=(LinearLayout) UView.findViewById(PView,R.id.llMangan);//~9309I~
//      if (swRequester)                                           //~9310I~//~9709R~
//          cbsMangan=setupEswnCB(llMangan,UCBP_MANGAN);               //~9309R~//~9310R~//~9709R~
//      else                                                       //~9310I~//~9709R~
	        cbsMangan=setupEswnCB(llMangan,swsMangan);             //~9310I~//~9506R~
    }                                                              //~9307I~//~9308M~
    //*******************************************************      //~9309I~
    private void setupErr(View PView)                              //~9309I~
    {                                                              //~9309I~
        if (Dump.Y) Dump.println("CompleteDlg.setupErr");          //~9309I~
        LinearLayout ll=(LinearLayout) UView.findViewById(PView,R.id.llError);//~9309I~
//      cbError         =          new UCheckBox(PView,R.id.cbAbnormalGain);//~9309I~//~9424R~
        if (swRequester)                                           //~9310I~
        {                                                          //~9310I~
//          cbError.setListener(this,UCBP_COMPTYPE_ERROR);             //~9309I~//~9310R~//~9424R~
    	    cbsErr=setupEswnCB(ll,UCBP_ERROR);                         //~9309R~//~9310I~
        }                                                          //~9310I~
        else                                                       //~9310I~
        {                                                          //~9310I~
//          cbError.setState(swCompError,true/*fixed*/);           //~9310I~//~9424R~
    	    cbsErr=setupEswnCB(ll,swsErrLooser);                       //~9310I~//~9422R~
        }                                                          //~9310I~
    }                                                              //~9309I~
    //*******************************************************      //~9309I~
    private UCheckBox[] setupEswnCB(View PView,int Pid)            //~9309R~
    {                                                              //~9309I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setupEswnCB");      //~9309I~//~9311R~
        UCheckBox cbE=new UCheckBox(PView,R.id.cbAbPE);            //~9309I~
        UCheckBox cbS=new UCheckBox(PView,R.id.cbAbPS);            //~9309I~
        UCheckBox cbW=new UCheckBox(PView,R.id.cbAbPW);            //~9309I~
        UCheckBox cbN=new UCheckBox(PView,R.id.cbAbPN);            //~9309I~
        UCheckBox[] cbs=new UCheckBox[]{cbE,cbS,cbW,cbN};          //~9309I~
        if (Pid>0)	//listener set req                             //~9309I~
		    setCBListener(cbs,Pid);                             //~9309I~
        return cbs;                                                //~9309R~
    }                                                              //~9309I~
    //*******************************************************      //~9310I~
    private UCheckBox[] setupEswnCB(View PView,boolean[] Psws)     //~9310I~
    {                                                              //~9310I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setupEswnCB");      //~9310I~//~9311R~
        UCheckBox cbE=new UCheckBox(PView,R.id.cbAbPE);            //~9310I~
        UCheckBox cbS=new UCheckBox(PView,R.id.cbAbPS);            //~9310I~
        UCheckBox cbW=new UCheckBox(PView,R.id.cbAbPW);            //~9310I~
        UCheckBox cbN=new UCheckBox(PView,R.id.cbAbPN);            //~9310I~
        UCheckBox[] cbs=new UCheckBox[]{cbE,cbS,cbW,cbN};          //~9310I~
		setCBFix(cbs,Psws);                                        //~9310I~
        return cbs;                                                //~9310I~
    }                                                              //~9310I~
    //*******************************************************      //~9311I~
    private void enableCB(boolean PswEnable,UCheckBox[] Pcb)      //~9311I~
    {                                                              //~9311I~
        if (Dump.Y) Dump.println("DrawnDlgLast.enableCB swEnable="+PswEnable);//~9311R~
        for (UCheckBox cb:Pcb)                                     //~9311I~
        {                                                          //~9311I~
            cb.setFixed(!PswEnable);                               //~9311R~
        }                                                          //~9311I~
    }                                                              //~9311I~
    //*******************************************************      //~9309I~
    private void setCBListener(UCheckBox[] Pcbs,int Pid)           //~9309I~
    {                                                              //~9309I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setCBListener id="+Pid);//~9309I~//~9310R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9309I~
            Pcbs[ii].setListener(this,Pid);                         //~9309I~
    }                                                              //~9309I~
    //*******************************************************      //~9310I~
    private void setCBFix(UCheckBox[] Pcbs,boolean[] Psws)           //~9310I~
    {                                                              //~9310I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setCBFixe Psws="+Arrays.toString(Psws));//~9310I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9310I~
            Pcbs[ii].setState(Psws[ii],true/*fixed*/);             //~9310I~
    }                                                              //~9310I~
    //*******************************************************      //~9308I~
    private void setupNext(View PView)                        //~9308I~//~9309R~
    {                                                              //~9308I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setupNext");       //~9308I~//~9309R~
//      super.setRadioGroup(PView);                                //~9308R~
//      UBRG.setChecked(reason,!swRequester/*swFixed*/);           //~9308R~
//      rgNextGame= new URadioGroup(PView,R.id.rgNextGame,0/*Pparm*/,rbIDs);//~9308I~//~9709R~
        if (Dump.Y) Dump.println("DrawnDlgLast.setupNext swsPending="+Arrays.toString(swsPending)+",swsReach="+Arrays.toString(swsReach));//~9424I~//~9504R~//~9709R~
//      if (intsPending[0]==PENDING_MANGAN_BYSETTING) //	mangan but not pending//~9424I~//~9506R~
        if (swDrawnManganAsRon && (intsPending[0]==PENDING_MANGAN || intsPending[0]==NOPENDING_MANGAN)) //	mangan but not pending//~9506R~
        {                                                          //~9506I~
//      	swContinue=!swDrawnManganNext;                         //~9424R~//~9505R~
//      	swContinue=!swDrawnManganAsDrawn;                      //~9505I~//~9506R~
        	swContinue=swsMangan[0];    //dealer get mangan        //~9506I~
        }                                                          //~9506I~
        else                                                       //~9424I~
        {                                                          //~9709I~
			if (RuleSetting.isPendingCont())	//                 //~9709I~
//      		swContinue=swsPending[0];   //dealer pending           //~9424I~//~9504R~//~9506R~//~9709R~
        		swContinue=swsPending[0]||swsReach[0];   //dealer reach//~9709I~
            else                                                   //~9709I~
        		swContinue=false;                                  //~9709I~
        }                                                          //~9709I~
	    setCheckNextGame();                                        //~9308I~
    }                                                              //~9308I~
//    //*******************************************************      //~9305I~//~9708R~
//    private void setCheckNextGame()                                     //~9305I~//~9708R~
//    {                                                              //~9305I~//~9708R~
//        typeNextGame=UAEndGame.getNextGameType(swContinue);        //~9319I~//~9708R~
//        if (Dump.Y) Dump.println("DrawDlgLast.setCheckNextGame typeNextGame="+typeNextGame);//~9527I~//~9708R~
//        rgNextGame.setCheckedID(typeNextGame,typeNextGame==NGTP_CONTINUE||typeNextGame==NGTP_NEXT/*swFixed*/);//~9424R~//~9527R~//~9708R~
//    }                                                              //~9302I~//~9303R~//~9708R~
    //*******************************************************      //~9708I~
    private void setCheckNextGame()                                //~9708I~
    {                                                              //~9708I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setCheckNextGame swRequester="+swRequester+",swErr="+swErr+",typeNextGame="+typeNextGame+",swContinue="+swContinue);//~9708I~//~9709R~
      	if (typeNextGame==NEXTGAME_UNKNOWN)                        //~9708I~//~9709R~
			typeNextGame=UAEndGame.getNextGameType(swContinue);	//may changed later when other or err//~9708I~//~9709M~
	    enableNextGame();                                          //~9708I~
    }                                                              //~9708I~
//    //*******************************************************    //~9304R~
//    public int getRBID(int Preason)                              //~9304R~
//    {                                                            //~9304R~
//        int rbid=-1;                                             //~9304R~
//        for (int ii=0;ii<rbIDs.length;ii++)                      //~9304R~
//        {                                                        //~9304R~
//            if (Preason==drawnReason[ii])                        //~9304R~
//            {                                                    //~9304R~
//                rbid=rbIDs[ii];                                  //~9304R~
//            }                                                    //~9304R~
//        }                                                        //~9304R~
//        if (Dump.Y) Dump.println("DrawDlgHW.getRBID reason="+reason+",id="+Integer.toHexString(rbid));//~9304R~
//        return rbid;                                             //~9304R~
//    }                                                            //~9304R~
    //*******************************************************      //~9303R~//~9304R~//~9305R~
    //*Label:OK                                                    //~9305I~
    //*******************************************************      //~9305I~
    @Override                                                      //~v@@@I~//~9221M~//~9303R~//~9304R~//~9305R~
    public void onClickOK()                                       //~v@@@R~//~9221M~//~9303R~//~9304R~//~9305R~
    {                                                              //~1602M~//~v@@@I~//~9221M~//~9303R~//~9304R~//~9305R~
        if (Dump.Y) Dump.println("DrawnDlgLast.onClickOK SendOK");                     //~v@@@I~//~9221M~//~9302R~//~9303R~//~9304R~//~9305R~//~0309R~
        if (!getSetting())                                              //~9303R~//~9304R~//~9305R~//~9306R~
        {                                                          //~9306R~
            UView.showToast(R.string.ErrSelectNextGame);           //~9306R~
            return;                                                //~9306R~
        }                                                          //~9306R~
//        updateResponse(reason);                                    //~9305I~//~9306M~//~9311R~
        if (swRequester)                                           //~9310I~
        {                                                          //~9708I~
    		if (confirmSuspend())	//issued Alert                 //~0308I~
            	return;                                            //~0308I~
    		enableFixButton(false);                                //~9708I~
		    Arrays.fill(respStat,EGDR_NONE);                       //~9708I~
            update(false);                                         //~9708R~
        	sendConfirmReqToServer(); //LAST_CONFIRM_REQUEST       //~9310R~
            CMP.swSent=true;                                       //~0309I~
        }                                                          //~9708I~
        else                                                       //~9310R~//~9311R~
            sendResponse(EGDR_OK);    //LAST_CONFIRM_RESPONSE                                            //~9303R~//~9304R~//~9305R~//~9306I~//~9308R~//~9310R~//~9311R~
        if (!swRequester)                                          //~9306I~
			dismiss();                                                 //~9303R~//~9304R~//~9305R~//~9306R~
//      else                                                       //~9308I~//~9309R~
//          btnOK.setEnabled(false);                               //~9308I~//~9309R~
        if (Dump.Y) Dump.println("DrawnDlgLast.onClickOK=Send return");         //~9311I~//~0309R~
    }                                                              //~9221I~//~9303R~//~9304R~//~9305R~
    //*******************************************************      //~9305I~
    //*Label=NG                                                    //~9305I~
    //*******************************************************      //~9305I~
    @Override                                                      //~9305I~
    public void onClickCancel()                                    //~9305I~
    {                                                              //~9305I~
        if (Dump.Y) Dump.println("DrawnDlgLast.onClickCancel sendNG");                //~9305I~//~0309R~
        reason=EGDR_NG; //no good                                  //~9305I~
//        updateResponse(reason);                                    //~9305I~//~9311R~
        sendResponse(EGDR_NG);	//                                            //~9305I~//~9308R~//~9310R~
        if (!swRequester)                                          //~9306I~
			dismiss();                                             //~9306I~
    }                                                              //~9305I~
    //*******************************************************      //~9311I~
    @Override                                                      //~9311I~
    public void onClickOther(int Pbuttonid)                        //~9311I~
	{                                                              //~9311I~
        if (Dump.Y) Dump.println("DrawDlgLast:onClickOther id="+Integer.toHexString(Pbuttonid));//~9311I~
        switch(Pbuttonid)                                          //~9311I~
        {                                                          //~9311I~
            case R.id.ShowTotal:                                    //~9311I~
            	showTotal();                                    //~9311R~//~9318R~
				dismiss();                                         //~9311I~
            	break;                                             //~9311I~
            case R.id.ShowRule:                                    //~9417I~
            	showRule();                                        //~9417I~
            	break;                                             //~9417I~
            default:                                               //~9311I~
                if (Dump.Y) Dump.println("DrawDlgLast:onClickOther unknown");//~9311I~
        }                                                          //~9311I~
    }                                                              //~9311I~
    //******************************************                   //~9220I~//~9303R~//~9304R~//~9305R~
    @Override                                                      //~9305I~
    protected boolean getSetting()                                   //~9220I~//~9302R~//~9303R~//~9304R~//~9305R~//~9306R~
    {                                                              //~9220I~//~9303R~//~9304R~//~9305R~
//      reason=UBRG.getChecked();                                  //~9303R~//~9304R~//~9305R~//~9308R~
//        int rbid=rgNextGame.getChecked();                          //~9305I~//~9306R~
//        if (rbid==-1)                                            //~9306R~
//            return false;                                        //~9306R~
//        for (int ii=0;ii<rbIDs.length;ii++)                        //~9305I~//~9306R~
//            if (rbid==rbIDs[ii])                                   //~9305I~//~9306R~
//            {                                                      //~9305I~//~9306R~
//                typeNextGame=ii;                                   //~9305R~//~9306R~
//                break;                                             //~9305I~//~9306R~
//            }                                                      //~9305I~//~9306R~
        getValue();                                                //~9310I~
        typeNextGame=rgNextGame.getCheckedID();                    //~9306I~
        boolean rc=typeNextGame!=-1;                               //~9306I~
        if (Dump.Y) Dump.println("DrawnDlgLast.getSetting rc="+rc+",reason="+reason+",typeNextGame="+typeNextGame);//~9305I~//~9306R~//~9307R~
        return rc;                                                 //~9306R~
    }                                                              //~9220I~//~9303R~//~9304R~//~9305R~
//    //*******************************************************************//~9303R~
//    public  void setReplyText()                                  //~9303R~
//    {                                                            //~9303R~
//        if (Dump.Y) Dump.println("DrawnDlgLast.setReplyText");  //~9303R~//~9304R~//~9307R~
//        int[] replys=UAEG.getReply();                            //~9303R~
//        for (int ii = 0; ii < PLAYERS; ii++)                     //~9303R~
//        {                                                        //~9303R~
//            int reply=replys[ii];                                //~9303R~
//            int color=colorReply[reply];                         //~9303R~
//            tvsReply[ii].setBackgroundColor(color);              //~9303R~
//            String s = replayText[reply];                        //~9303R~
//            tvsReply[ii].setText(s);                             //~9303R~
//        }                                                        //~9303R~
//    }                                                            //~9303R~
//    //*******************************************************      //~9303R~//~9304R~
//    @Override                                                      //~9303I~//~9304R~
//    public void sendResponse()                                     //~9303R~//~9304R~
//    {                                                              //~9303R~//~9304R~
//        if (Dump.Y) Dump.println("DrawnDlgLast.sendResponse");    //~9303R~//~9304R~//~9307R~
////        if (Accounts.isServer())                                   //~9303R~//~9304R~
////        {                                                          //~9303I~//~9304R~
//////          UAEG.setResponse(PLAYER_YOU,reason);                   //~9303R~//~9304R~
//////          String msg=GCM_ENDGAME_DRAWN+MSG_SEPAPP+ENDGAME_DRAWN_HALFWAY+MSG_SEPAPP+reason;//~9303R~//~9304R~
//////          String msg=ENDGAME_DRAWN_HALFWAY+MSG_SEPAPP+reason;//~9304R~
//////          UA.sendToClient(true/*swAll*/,false/*swRobot*/,0/*Pplayer*/,GCM_ENDGAME_DRAWN,msg);//~9303R~//~9304R~
////            setResponseOnServer(reason);                       //~9304R~
////        }                                                          //~9303I~//~9304R~
////        else                                                       //~9303R~//~9304R~
////            AG.aUserAction.sendToServer(GCM_ENDGAME_DRAWN,PLAYER_YOU,ENDGAME_DRAWN_HALFWAY,reason,0);//~9303R~//~9304R~
//        sendMsg(reason);                                         //~9304R~
//    }                                                              //~9303R~//~9304R~
    //*******************************************************      //~9304R~
    @Override                                                      //~9305I~
    public void sendMsg(int Preason)                               //~9304R~
    {                                                              //~9304R~
        if (Dump.Y) Dump.println("DrawnDlgLast.sendMsg reason="+Preason);//~9304R~//~9307R~
        GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN, PLAYER_YOU, ENDGAME_DRAWN_LAST_CONFIRM_RESPONSE,Preason);//~9304R~//~9305R~//~9308R~//~9311R~
    }                                                              //~9304R~
    //*******************************************************      //~9305I~
    public void update(boolean PswInit)                                           //~9305R~//~9308R~
    {                                                              //~9305I~
        if (Dump.Y) Dump.println("DrawnDlgLast.update swHideResponseEswn="+swHideResponseEswn+",datatype="+dataType+",swSuspend="+swSuspend);//~0217I~//~0309R~
        if (Dump.Y) Dump.println("DrawnDlgLast.update swInit="+PswInit+",respStat="+Arrays.toString(respStat));//~9305I~//~9307R~//~9308R~
        String stat;                                               //~9305I~
        int color;                                                 //~9305I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9305I~
        {                                                          //~9305I~
        	if (ACC.isDummyByCurrentEswn(ii))             //~9305I~//~9311R~
            {                                                      //~9305I~
            	stat=strBot;                                       //~9305I~
        		color=COLOR_RESPBOT;                               //~9305R~
            }                                                      //~9305I~
            else                                                   //~9305I~
        	if (ii==ACC.getCurrentDealerRealEswn())   //~9311I~    //~9315R~
            {                                                    //~9306R~//~9311M~
                stat=strDealer;                                  //~9306R~//~9311M~
                color=COLOR_RESPBOT;                            //~9306R~//~9311M~
            }                                                    //~9306R~//~9311M~
            else                                                 //~9306R~//~9311M~
            if (respStat[ii]==EGDR_NG) //no good                   //~9305R~
            {                                                      //~9305I~
            	stat=strNG;                                        //~9305I~
            	color=COLOR_RESPNG;                                //~9305I~
            }                                                      //~9305I~
            else                                                   //~9305I~
            if (respStat[ii]!=EGDR_NONE)                                   //~9305R~//~9311R~
            {                                                      //~9305I~
            	stat=strOK;                                        //~9305I~
            	color=COLOR_RESPOK;                                //~9305I~
            }                                                      //~9305I~
            else                                                   //~9305I~
            {                                                      //~9305I~
            	stat=strNoReply;                                   //~9305I~
        		color=COLOR_RESPNONE;                              //~9305I~
            }                                                      //~9305I~
	        if (Dump.Y) Dump.println("DrawnDlgLast.update stat="+stat+",resp="+respStat[ii]);//~9305I~//~9307R~
            tvsResp[ii].setText(stat);                             //~9305I~
            tvsResp[ii].setBackgroundColor(color);                 //~9305I~
        }                                                          //~9305I~
        tvsRespESWN[currentEswn].setBackgroundColor(COLOR_YOU);         //~9305I~
    if (dataType!=LASTDT_OKNG)                                     //~0309I~
    {                                                              //~0309I~
      if (!PswInit)                                                //~0209I~
	    setCheckNextGame();                                        //~9708I~
		cbSuspend.setState(swSuspend);                             //~0308I~
//	    enableTemporally(false);                                   //~9A14I~//~9B12R~
		if (swRequester)                                           //~9B12I~
	  	    enableRequesterCB();                                   //~9B12I~
    }                                                              //~0309I~
//      btnTotal.setVisibility(chkRespAllOK()? View.VISIBLE :View.GONE);                 //~9311I~//~9318R~//~9610R~
        if (swRequester)                                           //~9610I~
        {                                                          //~9611I~
//  		btnTotal.setEnabled(chkRespAllOK());                   //~9610I~//~9708R~
    		enableFixButton(chkRespAllOK());                       //~9708I~
		    if (chkRespAllWithNG())                                //~9611I~
    	    	alertToForceOK(TITLEID,R.string.Alert_DrawnLastNGForceOK);//~9611I~
        }                                                          //~9611I~
    }                                                              //~9305I~
//    //*******************************************************      //~9305I~//~9311R~
//    public void updateResponse(int Preason)                        //~9305I~//~9311R~
//    {                                                              //~9305I~//~9311R~
//        if (Dump.Y) Dump.println("DrawnDlgLast.updateResponse reason="+Preason);//~9305I~//~9307R~//~9311R~
//        int eswn=Accounts.getCurrentEswn();                        //~9305I~//~9311R~
////      respStat[eswn]=Preason; //on UAEndGame                     //~9305R~//~9311R~
//        if (Preason==EGDR_NG) //no good                            //~9305I~//~9311R~
//        {                                                          //~9306I~//~9311R~
//            tvsResp[eswn].setText(strNG);                                   //~9305I~//~9311R~
//            tvsResp[eswn].setBackgroundColor(COLOR_RESPNG);        //~9306I~//~9311R~
//        }                                                          //~9306I~//~9311R~
//        else                                                       //~9305I~//~9311R~
//        {                                                          //~9306I~//~9311R~
//            tvsResp[eswn].setText(strOK);                                   //~9305I~//~9311R~
//            tvsResp[eswn].setBackgroundColor(COLOR_RESPOK);        //~9306I~//~9311R~
//        }                                                          //~9306I~//~9311R~
//    }                                                              //~9305I~//~9311R~
    //*******************************************************      //~9305I~
    private boolean chkReasonUpdated(int [] Pint1,int[] Pint2)     //~9610I~
    {                                                              //~9610I~
        if (Dump.Y) Dump.println("DrawnDlgLast.chkReasonUpdated int1="+Arrays.toString(Pint1)+",int2="+Arrays.toString(Pint2));//~9610I~
    	for (int ii=0;ii<Pint1.length;ii++)                        //~9610I~
        	if (Pint1[ii]!=Pint2[ii])                              //~9610I~
            	return true;                                       //~9610I~
        return false;                                              //~9610I~
    }                                                              //~9610I~
    //*******************************************************      //~9610I~
    private void reloadDlgPrepare(int Peswn,int PdataType)         //~9610I~
    {                                                              //~9610I~
        if (Dump.Y) Dump.println("DrawnDlgLast.reloadDlgPrepare"); //~9610I~
        reloadEswn=Peswn;                                          //~9610I~
        reloadDataType=PdataType;                                  //~9610I~
        swReload=true;                                             //~9610I~//~9611R~
        dismiss();                                                 //~9610I~
    }                                                              //~9610I~
    //*******************************************************      //~9610I~
    //*from OnDismissDlg                                           //~9610I~
    //*******************************************************      //~9610I~
    private void reloadDlg()                                       //~9610I~
    {                                                              //~9610I~
        if (Dump.Y) Dump.println("DrawnDlgLast.reloadDlg");        //~9610I~
        DrawnDlgLast.newInstance(reloadEswn,reloadDataType,reasonResponse,respStat,receivedDialogData).show();//~9611R~
    }                                                              //~9610I~
    //*******************************************************      //~9610I~
    //*from UAEndGame                                              //~9B11I~
    //*******************************************************      //~9B11I~
//  public void repaint(int[] PrespStat,int[] PdialogData)         //~9305R~         //~9308R~//~9310R~//~9311R~
    public void repaint(int Peswn,int PdataType,int[] PreasonResp,int[] PrespStat,int[] PdialogData)//~9311I~
    {                                                              //~9305I~
        if (Dump.Y) Dump.println("DrawnDlgLast.repaint eswn="+Peswn+",dataType="+PdataType);//~9311R~
        if (Dump.Y) Dump.println("DrawnDlgLast.repaint reasonResp="+Arrays.toString(PreasonResp)+",respstat="+Arrays.toString(PrespStat)+",dialogData="+Arrays.toString(PdialogData));//~9311I~//~9610R~
        swUpdatedReason=chkReasonUpdated(oldReasonResponse,PreasonResp);//~9610R~
        reasonResponse=PreasonResp;                                        //~9305I~//~9311R~
        respStat=PrespStat;                                        //~9311I~
        receivedDialogData=PdialogData;                            //~9310I~
        if (PdataType!=LASTDT_OKNG)                                //~0309I~
        	swSuspend=PdialogData[PARMPOS_DRAWN_LAST_SUSPEND-PARMPOS_DRAWN_DIALOGDATA]!=0;//~0308I~//~0309R~
//      if (swUpdatedReason)                                      //~9610I~//~0331R~
        if (PdataType!=LASTDT_OKNG)//TODO Test                     //+0331R~
        {                                                          //~9610I~
        	reloadDlgPrepare(Peswn,PdataType);                     //~9610I~
            return;                                                //~9610I~
        }                                                          //~9610I~
//      final int dataType=PdataType;                                    //~9421I~//~0309R~
        dataType=PdataType;                                        //~0309I~
        AG.activity.runOnUiThread(                                 //~9305I~
            new Runnable()                                         //~9305I~
            {                                                      //~9305I~
                @Override                                          //~9305I~
                public void run()                                       //~9305I~
                {                                                  //~9305I~
                    try                                            //~9305I~
                    {                                              //~9305I~
    				    if (Dump.Y) Dump.println("DrawnDlgLast.repaint runonUiThread.run swUpdatedReason="+swUpdatedReason);//~9305I~//~9307R~//~9610R~
                    	update(false);                             //~9305I~//~9308R~
//TODO test             if (dataType!=LASTDT_OKNG)                 //~9421R~//~0331R~
//TODO test 				showAmmountReceived();                     //~9310I~//~9421R~//~0331R~
                    }                                              //~9305I~
                    catch(Exception e)                             //~9305I~
                    {                                              //~9305I~
                        Dump.println(e,"DrawnDlgLast:repaint.run");  //~9305I~//~9307R~
                    }                                              //~9305I~
                }                                                  //~9305I~
            }                                                      //~9305I~
                                  );                               //~9305I~
                                                                   //~9305I~
    }                                                              //~9305I~
    //******************************************                   //~9228I~//~9307I~
    private void setupReach(View PView)                                       //~9228I~//~9307I~
    {                                                              //~9228I~//~9307I~
    	if (Dump.Y) Dump.println("DrawDlgLast.setupReach");         //~9228I~//~9307I~
        LinearLayout ll=        (LinearLayout)UView.findViewById(PView,R.id.llReachers);//~9307I~
        llReachers=setupTiles(ll);                                 //~9307I~
        llllReachers     =(LinearLayout)UView.findViewById(PView,R.id.llReachers);//~9307I~
        llReacherEswn    =(LinearLayout)UView.findViewById(ll,R.id.llReacherEswn);//~9927R~
		widthReacherEswn=measureWidth(llReacherEswn);              //~0327I~
    }                                                              //~9228I~//~9307I~
    //******************************************                   //~9309I~
    private void setupPending(View PView)                          //~9309I~
    {                                                              //~9309I~
//        if (swPendingCB)                                           //~9309I~//~9311R~
//            setupPendingCB(PView);                                 //~9309I~//~9311R~
//        else                                                       //~9309I~//~9311R~
			setupPendingTile(PView);                               //~9309I~
    }                                                              //~9309I~
    //******************************************                   //~9307I~//~9309R~
    private void setupPendingTile(View PView)                        //~9307I~//~9309R~//~9311R~
    {                                                              //~9307I~//~9309R~
        if (Dump.Y) Dump.println("DrawDlgLast.setupPending");      //~9307I~//~9309R~
        llPending =(LinearLayout)UView.findViewById(PView,R.id.llPending);//~9307I~//~9308R~//~9309R~
        llPendingS=setupTiles(llPending);                                  //~9307I~//~9308R~//~9309R~
        tvPending    =(TextView)UView.findViewById(PView,R.id.tvPending);//~9308I~//~9309R~
        LinearLayout ll    =(LinearLayout)UView.findViewById(PView,R.id.llcbPending);//~9309R~
//      if (swRequester)         //fix pending on DlgLast         //~9310I~//~9311R~//~9709R~
//		    cbsPending=setupEswnCB(ll,UCBP_PENDING);                    //~9309I~//~9310R~//~9709R~
//      else                                                       //~9310I~//~9709R~
//          cbsPending=setupEswnCB(ll,swsPending);                 //~9310I~//~9504R~//~9709R~
  		cbsPending=setupEswnCB(ll,UCBP_NOLISTENER);                //~9709I~
        llPendingEswn    =(LinearLayout)UView.findViewById(llPending,R.id.llReacherEswn);//~9927R~
		widthPendingEswn=measureWidth(llPendingEswn);              //~0327I~
    }                                                              //~9307I~//~9309R~
    //******************************************                   //~9307I~//~9309R~
    private LinearLayout[] setupTiles(View PView)                  //~9307I~//~9309R~
    {                                                              //~9307I~//~9309R~
        if (Dump.Y) Dump.println("DrawDlgLast.setupTiles swPendingCB="+swPendingCB);        //~9307I~//~9309R~
        if (swPendingCB)                                           //~9309I~
        	return null;                                                //~9309I~
        LinearLayout llReacher1       =(LinearLayout)UView.findViewById(PView,R.id.reacher1);//~9307I~//~9309R~
        LinearLayout llReacher2       =(LinearLayout)UView.findViewById(PView,R.id.reacher2);//~9307I~//~9309R~
        LinearLayout llReacher3       =(LinearLayout)UView.findViewById(PView,R.id.reacher3);//~9307I~//~9309R~
        LinearLayout llReacher4       =(LinearLayout)UView.findViewById(PView,R.id.reacher4);//~9307I~//~9309R~
        return new LinearLayout[]{llReacher1,llReacher2,llReacher3,llReacher4};//~9307I~//~9309R~
    }                                                              //~9307I~//~9309R~
//    //******************************************                   //~9309I~//~9311R~
//    private void setupPendingCB(View PView)                        //~9309R~//~9311R~
//    {                                                              //~9309I~//~9311R~
//        if (Dump.Y) Dump.println("DrawDlgLast.setupPending");      //~9309I~//~9311R~
//        tvPending =(TextView)UView.findViewById(PView,R.id.tvPending);//~9309I~//~9311R~
//        llPending =(LinearLayout)UView.findViewById(PView,R.id.llPending);//~9309I~//~9311R~
//        cbsPending=setupEswnCB(llPending,0/*no listener*/);                         //~9309I~//~9311R~
//    }                                                              //~9309I~//~9311R~
    //******************************************                   //~9309I~
    protected void showMangan()                                    //~9309I~
    {                                                              //~9309I~
    	if (Dump.Y) Dump.println("DrawnDlgLast.showMangan swsMangan="+Arrays.toString(swsMangan));//~9309I~//~9311R~//~9506R~
        int ctr=0;                                                 //~9309I~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9309I~
        {                                                          //~9309I~
//            if (swRequester)                                       //~9310I~//~9311R~
//            {                                                      //~9310I~//~9311R~
//                if (!swsMangan[eswn])                              //~9310I~//~9311R~//~9506R~
//                    continue;                                      //~9310I~//~9311R~
//            }                                                      //~9310I~//~9311R~
//            else                                                   //~9310I~//~9311R~
//            {                                                      //~9310I~//~9311R~
//                if (reasonResponse[eswn]!=EGDR_MANGAN && reasonResponse[eswn]!=EGDR_MANGAN_PENDING)//~9310R~//~9311R~
//                    continue;                                          //~9309I~//~9310R~//~9311R~
//                swsMangan[eswn]=true;                              //~9310I~//~9311R~//~9506R~
//                cbsMangan[eswn].setState(true);                        //~9309I~//~9310I~//~9311R~
//            }                                                      //~9310I~//~9311R~
            cbsMangan[eswn].setState(swsMangan[eswn]);             //~9311I~//~9506R~
            if(swsMangan[eswn])                                    //~9311I~//~9506R~
	            ctr++;                                                 //~9309I~//~9311R~
        }                                                          //~9309I~
        if (ctr==0)                                                //~9309I~
            llMangan.setVisibility(View.GONE);                     //~9309I~
        ctrMangan=ctr;                                             //~9420I~
    }                                                              //~9309I~
    //******************************************                   //~9310I~
    protected void showErr()                                       //~9310I~
    {                                                              //~9310I~
    	if (Dump.Y) Dump.println("DrawnDlgLast.showErr swRequester="+swRequester+",swsErrLooser="+Arrays.toString(swsErrLooser)+",swsReach="+Arrays.toString(swsReach));//~9310I~//~9422R~//~9B11R~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9310I~
        {                                                          //~9310I~
//          cbsErr[eswn].setState(swsErrLooser[eswn],true/*swFixed*/);   //~9310I~//~9311R~//~9422R~
//          cbsErr[eswn].setState(swsErrLooser[eswn]);                 //~9311I~//~9422R~//~9B11R~
            cbsErr[eswn].setState(swsErrLooser[eswn],!swRequester);//~9B11I~
        }                                                          //~9310I~
//      cbError.setState(swCompError);               //~9310I~     //~9311R~//~9424R~
    }                                                              //~9310I~
    //*****************************************************************//~9B12I~
    //*get errlooser by continue to nextplayer by chombo           //~9B12I~
    //*****************************************************************//~9B12I~
    private void chkContinuedErr()                                //~9B12I~
    {                                                              //~9B12I~
    	if (Dump.Y) Dump.println("CompleteDlg.chkContinuedErr");   //~9B12I~
        Complete.Status[] sorted=CMP.getSortedStatusS();           //~9B12I~
        if (sorted==null)                                          //~9B12I~
        {                                                          //~9B12I~
	    	if (Dump.Y) Dump.println("DrawnDlgLast.chkContinuedErr no completion");//~9B12I~//~9B14R~
        	return;                                                //~9B12I~
        }                                                          //~9B12I~
	    for (Complete.Status stat:sorted)                          //~9B12I~
        {                                                          //~9B12I~
    		if (Dump.Y) Dump.println("DrawnDlgLast.chkContinuedErr swErr="+stat.swErr+",compeswn="+stat.completeEswn);//~9B12I~//~9B14R~
            if (stat.swErr)                                        //~9B12I~
            {                                                      //~9B12I~
		        swsErrLooser[stat.completeEswn]=true;              //~9B12I~
            }                                                      //~9B12I~
        }                                                          //~9B12I~
    	if (Dump.Y) Dump.println("DrawnDlgLast.chkContinuedErr errLooser="+Arrays.toString(swsErrLooser));//~9B12I~//~9B14R~
    }                                                              //~9B12I~
    //******************************************                   //~9307I~
    protected void showReach()                           //~9307I~ //~9308R~
    {                                                              //~9307I~
    	if (Dump.Y) Dump.println("DrawnDlgLast.showReach");        //~9307I~
        int reachctr=0;                                            //~9307I~
        int[] byEswn=new int[PLAYERS];                             //~9308I~
        Arrays.fill(byEswn,-1);                                    //~9308I~
        for (int player=0;player<PLAYERS;player++)                 //~9307I~
        {                                                          //~9307I~
        	if (PLS.getPosReach(player)<0)                         //~9307I~
            	continue;                                          //~9307I~
	        int eswn=Accounts.playerToEswn(player);                          //~9228I~//~9307I~
            reachctr++;                                             //~9308I~
            byEswn[eswn]=player;                                   //~9308I~
//          swsPending[eswn]=true;                                 //~9309I~//~9311R~//~9504R~
        }                                                          //~9307I~
        if (reachctr==0)                                           //~9307I~//~9308M~
        	llllReachers.setVisibility(View.GONE);                 //~9307I~//~9308M~
        else                                                       //~9308I~
        {                                                          //~9308I~
        	reachctr=0;                                            //~9308I~
            for (int eswn=0;eswn<PLAYERS;eswn++)                   //~9308I~
            {                                                      //~9308I~
                int player=byEswn[eswn];                               //~9308I~
				swsReach[eswn]=player>=0;                         //~9311I~//~9522R~
                if (player<0)                                      //~9308I~
                    continue;                                      //~9308I~
                LinearLayout ll=llReachers[reachctr++];            //~9308I~
                CompDlgReacher cr=new CompDlgReacher(this,ll,eswn);                  //~9308I~//~9927R~
                int ww=cr.widthTileImage;                          //~9927R~
                if (ww!=0)                                         //~9927I~
                {                                                  //~9927I~
			        int szReacherEswn=measureWidth(llReacherEswn); //~9928R~
	                widthTileImage=Math.max(widthTileImage,ww+szReacherEswn);//~9927I~
                }                                                  //~9927I~
		        if (Dump.Y) Dump.println("DrawnDlgLast.showReach widthTileImage="+widthTileImage);//~9927I~
            }                                                      //~9308I~
        }                                                          //~9308I~
        ctrReach=reachctr;                                         //~9309I~
    }                                                              //~9307I~
    //******************************************                   //~9309I~
    //*after calc Reach                                            //~9309I~
    //******************************************                   //~9309I~
    private void showPending()                                     //~9309I~
    {                                                              //~9309I~
//        if (swPendingCB)                                           //~9309I~//~9311R~
//            showPendingCB();                                       //~9309I~//~9311R~
//        else                                                       //~9309I~//~9311R~
			showPendingTile();                                     //~9309I~
    }                                                              //~9309I~
    //******************************************                   //~9307I~//~9309R~
    private void showPendingTile()                         //~9307I~   //~9308R~//~9309R~
    {                                                              //~9307I~//~9309R~
        if (Dump.Y) Dump.println("DrawnDlgLast.showPendingTile intsPending="+Arrays.toString(intsPending)+",swsPending="+Arrays.toString(swsPending));      //~9307I~//~9308R~//~9309R~//~9311R~//~9423R~//~9504R~
        if (Dump.Y) Dump.println("DrawnDlgLast.showPendingTile reasonResponse="+Arrays.toString(reasonResponse));//~9423I~
        if (Dump.Y) Dump.println("DrawnDlgLast.showPendingTile swsReach="+Arrays.toString(swsReach));//~9423I~//~9522R~
        int pendingctr=0;                                          //~9307I~//~9309R~
        for (int eswn=0;eswn<PLAYERS;eswn++)     //by Eswn seq                        //~9307I~//~9308R~//~9309R~
        {                                                          //~9307I~//~9309R~
//            if (reasonResponse[eswn]!=EGDR_PENDING && reasonResponse[eswn]!=EGDR_MANGAN_PENDING)//~9310R~//~9311R~
//                continue;                                          //~9307I~//~9309R~//~9311R~
//            if (swsPending[eswn])   //reach done                   //~9309I~//~9311R~//~9504R~
//                continue;                                          //~9309I~//~9311R~
//            swsPending[eswn]=true;                                 //~9309I~//~9311R~//~9504R~
//            if (reasonResponse[eswn]!=EGDR_MANGAN_PENDING)         //~9310I~//~9311R~
//          if (swsPending[eswn] && !swsReach[eswn])                //~9311I~//~9423R~//~9504R~//~9522R~
//          if ((swsPending[eswn] && reasonResponse[eswn]!=EGDR_MANGAN) && !swsReach[eswn])//~9423R~//~9504R~//~9522R~
//          if ((swsPending[eswn] && reasonResponse[eswn]!=EGDR_MANGAN_PENDING_BYSETTING) && !swsReach[eswn])//~9423I~//~9504R~//~9506R~//~9522R~
            if ((swsPending[eswn]) && !swsReach[eswn])            //~9506I~//~9522R~
            {                                                      //~9310I~
           		LinearLayout ll=llPendingS[pendingctr++];               //~9307I~//~9309R~//~9310R~
            	CompDlgReacher cr=new CompDlgReacher(this,ll,eswn,true/*swPending*/);                        //~9307R~//~9309R~//~9310R~//~9927R~
                int ww=cr.widthTileImage;                          //~9927R~
                if (ww!=0)                                         //~9927I~
                {                                                  //~9927I~
				    int szPendingEswn=measureWidth(llPendingEswn); //~9928R~
	                widthTileImage=Math.max(widthTileImage,ww+szPendingEswn);//~9927I~
        		}                                                  //~9927I~
		        if (Dump.Y) Dump.println("DrawnDlgLast.showReach widthTileImage="+widthTileImage);//~9927I~
            }                                                      //~9310I~
        }                                                          //~9307I~//~9309R~
//        if (pendingctr==0)                                         //~9307I~//~9309R~
//        {                                                        //~9309R~
//            tvPending.setText(Utils.getStr(ctrReach==0 ? R.string.Label_AllNoPending : R.string.Label_AllNoPendingElse));//~9309R~
//            llPending.setVisibility(View.GONE);               //~9308I~//~9309R~
//        }                                                        //~9309R~
        int ctrPending=ctrReach+pendingctr;                            //~9309I~//~9424R~
        if (Dump.Y) Dump.println("DrawnDlgLast.showPendingTile ctrPending="+ctrPending+",ctrReach="+ctrReach);//~9424I~
//  	boolean swFixed=swRequester;                               //~9709I~//~0217R~
    	boolean swFixed=true;	//retry from reqdlglast            //~0217R~
//        if (ctrPending==0)                                         //~9309I~//~9310R~
//        {                                                          //~9309I~//~9310R~
//            tvPending.setText(Utils.getStr(R.string.Label_AllNoPending));//~9309I~//~9310R~
//            llPending.setVisibility(View.GONE);                    //~9309I~//~9310R~
//        }                                                          //~9309I~//~9310R~
//        else                                                       //~9309I~//~9310R~
            for (int ii=0;ii<PLAYERS;ii++)                         //~9309I~
            {                                                      //~9309I~
//          	cbsPending[ii].setState(swsPending[ii]);           //~9309I~//~9311R~//~9504R~
//          	cbsPending[ii].setState(swsPending[ii]||swsReach[ii]);//~9311I~//~9423R~//~9504R~//~9522R~//~9709R~
            	cbsPending[ii].setState(swsPending[ii]||swsReach[ii],swFixed);//~9709I~
        		if (Dump.Y) Dump.println("DrawnDlgLast.showPendingTile set swRequester="+swRequester+",swFixed="+swFixed+",cbsPending ii="+ii+",state="+(swsPending[ii]||swsReach[ii]));//~9709R~//~0217R~
            }                                                      //~9309I~
    }                                                              //~9307I~//~9309R~
//    //******************************************                   //~9309I~//~9311R~
//    private void showPendingCB()                                   //~9309R~//~9311R~
//    {                                                              //~9309I~//~9311R~
//        if (Dump.Y) Dump.println("DrawnDlgLast.showPendingCB resonResponse="+Arrays.toString(reasonResponse));//~9309R~//~9311R~
//        int pendingctr=0;                                          //~9309I~//~9311R~
//        for (int ii=0;ii<PLAYERS;ii++)     //by Eswn seq           //~9309I~//~9311R~
//        {                                                          //~9309I~//~9311R~
//            if (reasonResponse[ii]!=EGDR_PENDING)                  //~9309I~//~9311R~
//                continue;                                          //~9309I~//~9311R~
//            swsPending[ii]=true;                                 //~9309I~//~9311R~//~9504R~
//            pendingctr++;                                          //~9309I~//~9311R~
//        }                                                          //~9309I~//~9311R~
//        if ((ctrReach+pendingctr)==0)                              //~9309R~//~9311R~
//        {                                                          //~9309I~//~9311R~
//            tvPending.setText(Utils.getStr(ctrReach==0 ? R.string.Label_AllNoPending : R.string.Label_AllNoPendingElse));//~9309I~//~9311R~
//            llPending.setVisibility(View.GONE);                    //~9309I~//~9311R~
//        }                                                          //~9309I~//~9311R~
//        else                                                       //~9309I~//~9311R~
//            for (int ii=0;ii<PLAYERS;ii++)                         //~9309I~//~9311R~
//            {                                                      //~9309I~//~9311R~
//                cbsPending[ii].setState(swsPending[ii]);          //~9309I~//~9311R~//~9504R~
//            }                                                      //~9309I~//~9311R~
//    }                                                              //~9309I~//~9311R~
    //******************************************                   //~9309I~
    private void showAmmount()                                     //~9309I~
    {                                                              //~9309I~
//      calcAmmount();                                             //~9309I~//~9310R~
        calcAmmountUpdate();                                       //~9310I~
        if (Dump.Y) Dump.println("DrawnDlgLast.showAmmount amt="+Arrays.toString(totalAmmount));//~9309R~
        if (Dump.Y) Dump.println("DrawnDlgLast.showAmmount score="+Arrays.toString(ACC.score));//~9413I~//~9421R~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9309I~
        {                                                          //~9309I~
	        int eswn=accountEswn[ii];                              //~9309I~
            int amt=totalAmmount[eswn]+amtsError[eswn];            //~9424I~
            tvsAmmount[ii].setText(Integer.toString(amt));//~9309R~ //~9424R~
//          int total=ACC.score[ii]+totalAmmount[eswn];             //~9413I~//~9421R~//~9424R~
            int total=ACC.score[ii]+amt;                           //~9424R~
            tvsTotal[ii].setText(Integer.toString(total));         //~9413I~
//          minusScore[ii]=total;                                  //~9413I~//~9414R~
        }                                                          //~9309I~
        chkMinusStop();                                            //~9413I~//~9414R~//~9421R~
        savePayTo();                                               //~9414I~
    }                                                              //~9309I~
    //******************************************                   //~9414I~
    private void savePayTo()                                       //~9414I~
    {                                                              //~9414I~
    	if (Dump.Y) Dump.println("DrawnDlgLast.savePayTo");        //~9414I~
        int[][] payss=amtssDrawnMangan;                             //~9414I~//~9422R~
        int[][] errss=amtssError;                                   //~9414I~//~9422R~
//      int[][] pendss=amtssPending;                                 //~9414I~//~9420R~//~9422R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9414I~
        {                                                          //~9414I~
            int[] pays=payss[ii];                                  //~9414I~
            if (Dump.Y) Dump.println("DrawnDlgLast.savePayTo old pay="+Arrays.toString(pays));//~9414I~
        	int[] errs=errss[ii];                                  //~9414I~
            if (errs!=null)                                        //~9414I~
            {                                                      //~9414I~
                if (Dump.Y) Dump.println("DrawnDlgLast.savePayTo ii="+ii+",err="+Arrays.toString(errs));//~9414I~
                for (int jj=0;jj<PLAYERS;jj++)                     //~9414I~
                    pays[jj]+=errs[jj];                            //~9414I~
                if (Dump.Y) Dump.println("DrawnDlgLast.savePayTo new pay="+Arrays.toString(pays));//~9414I~
            }                                                      //~9414I~
//            int[] pends=pendss[ii];                                  //~9414I~//~9420R~
//            if (pends!=null)                                        //~9414I~//~9420R~
//            {                                                      //~9414I~//~9420R~
//                if (Dump.Y) Dump.println("DrawnDlgLast.savePayTo ii="+ii+",Pending="+Arrays.toString(pends));//~9414I~//~9420R~
//                for (int jj=0;jj<PLAYERS;jj++)                     //~9414I~//~9420R~
//                    pays[jj]+=pends[jj];                            //~9414I~//~9420R~
//                if (Dump.Y) Dump.println("DrawnDlgLast.savePayTo new pay="+Arrays.toString(pays));//~9414I~//~9420R~
//            }                                                      //~9414I~//~9420R~
        }                                                          //~9414I~
		AG.aComplete.amtsPayedToEswn=payss;                        //~9414R~
    }                                                              //~9414I~
    //******************************************                   //~9310I~
    //*recalc by dialog update                                     //~9310I~
    //******************************************                   //~9310I~
    private void calcAmmountUpdate()                               //~9310I~
    {                                                              //~9310I~
        if (Dump.Y) Dump.println("DrawnDlgLast.calcAmmountUpdate pending="+Arrays.toString(swsPending)+",mangan="+Arrays.toString(swsMangan)+",err="+Arrays.toString(swsErrLooser));//~9311I~//~9422R~//~9424R~//~9504R~//~9506R~
        Arrays.fill(totalAmmount,0);                               //~9310I~//~9420R~
        int ctrPendingOK=0;                                        //~9310I~//~9424R~
        Arrays.fill(swPendingForAmmount,false);                    //~9310I~
        Arrays.fill(amtsDrawnMangan,0);  	//amt by drawnMangan only//~9424I~
        int ctrMangan=0;                                           //~9506I~
//      for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9310I~//~9520R~
        for (int eswn=0;eswn<PLAYERS;eswn++)     //current eswn seq//~9520I~
        {                                                          //~9310I~
//          int eswn=accountEswn[ii];                              //~9310I~//~9520R~
			if (Dump.Y) Dump.println("DrawnDlgLast.calcAmmountUpdate eswn="+eswn+",swsPending[eswn]="+swsPending[eswn]);//~9424I~//~9504R~//~9520R~
            String s;                                              //~9310I~
            s=strPendingNo;                                        //~9310I~
            if (swsErrLooser[eswn])                                    //~9310I~//~9422R~
                s=strErr;                                          //~9310I~
            else                                                   //~9310I~
            if (swsMangan[eswn])                                   //~9310I~//~9506R~
            {                                                      //~9310I~
            	if (swsPending[eswn])                              //~9310I~//~9504R~
                {                                                  //~9310I~
                    swPendingForAmmount[eswn]=true;                //~9310I~
    	            if (swsNoManganByEswn[eswn])                   //~9520I~
	    	        	s=strNoManganPendingByEswn;                //~9520I~
                    else                                           //~9520I~
	    	        	s=strManganPending;                            //~9310I~//~9520R~
                    ctrPendingOK++;                                //~9310I~
                }                                                  //~9310I~
                else                                               //~9310I~
                {                                                  //~9520I~
    	            if (swsNoManganByEswn[eswn])                   //~9520I~
	    	        	s=strNoManganByEswn;                       //~9520I~
                    else                                           //~9520I~
	            		s=strMangan;                                   //~9310I~//~9520R~
                }                                                  //~9520I~
                setAmmountMangan(totalAmmount,eswn,ctrMangan++);               //~9310I~//~9506R~
            }                                                      //~9310I~
            else                                                   //~9310I~
            if (swsPending[eswn])                                  //~9310I~//~9504R~
            {                                                      //~9310I~
            	s=strPending;                                      //~9310I~
                swPendingForAmmount[eswn]=true;                    //~9310I~
                ctrPendingOK++;                                    //~9310I~
            }                                                      //~9310I~
            else                                                   //~9423I~
            if (swsReach[eswn])                                   //~9423R~//~9522R~
            {                                                      //~9423I~
                swPendingForAmmount[eswn]=true;                    //~9423I~
                ctrPendingOK++;                                    //~9423I~
		        s=strReach;                                        //~9423R~
            }                                                      //~9423I~
//          tvsDrawnType[ii].setText(s);                           //~9310I~//~9520R~
            int pos=ACC.currentEswnToPosition(eswn);               //~9520I~
            tvsDrawnType[pos].setText(s);                          //~9520I~
            if (Dump.Y) Dump.println("DrawnDlgLast.calcAmmountUpdate eswn="+eswn+",pos="+pos+",s="+s+",ctrPendingOK="+ctrPendingOK+",pendingForAmmount="+Arrays.toString(swPendingForAmmount));//~9424I~//~9520R~
        }                                                          //~9310I~
//      reversePayToMangan();                                      //~9414I~//~9421R~
//      setMinusStopPayMangan(totalAmmount);                       //~9420R~
		ctrPending=ctrPendingOK;                                   //~9424I~
        if (!(swMangan && swDrawnManganAsRon))                     //~9506I~
	        setAmmountPending(totalAmmount,ctrPendingOK,swPendingForAmmount); //update totalAmmount//~9310I~//~9420R~//~9422R~//~9506R~
//      setAmmountPending(ctrPendingOK,swPendingForAmmount);       //~9420R~
//      setMinusStopPayPending(totalAmmount,amtPending);           //~9420R~
//      setAmmountErr(totalAmmount);                               //~9310I~//~9413M~//~9420R~
        setAmmountErr();                                           //~9420R~
//      setMinusStopPayErr(totalAmmount,amtsError)                  //~9420I~//~9422R~
        saveStatus();                                             //~9420I~
    }                                                              //~9310I~
    //**********************************************               //~9420I~
    private void saveStatus()                                           //~9420I~
    {                                                              //~9420I~
//      CMP.swsPending=swPendingForAmmount;                        //~9420R~
		boolean[] sws=swsMangan.clone();                             //~9520I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9520I~
        	if (swsNoManganByEswn[ii])                             //~9520I~
            	sws[ii]=false;       //for bird and ctrRon when no MultiRon option//~9520I~
        CMP.swsMangan=sws;                                   //~9420R~//~9422R~//~9501R~//~9506R~//~9520R~
        CMP.minusPrizeDrawn=minusPrize;                            //~9420R~
//      CMP.amtssDrawnMangan=amtssDrawnMangan;                       //~9420I~//~9422R~
//      CMP.adjustedScoreDrawn=adjustedScore;                      //~9422I~//~9424R~
        CMP.adjustedScoreDrawn=adjustedScoreWithErr;               //~9424I~
        CMP.chargeToGainerDrawn=chargeToGainer;                    //~9422I~
		CMP.swsErrLooser=swsErrLooser;                             //~9422I~
		CMP.swsPending=swsPending;                                 //~9504R~
		CMP.swsReach=swsReach;                                    //~9521I~//~9522R~
	    saveSuspendRequest();                                      //~0308I~
    }                                                              //~9420I~
    //*******************************************************      //~0308I~
    private void saveSuspendRequest()                              //~0308I~
    {                                                              //~0308I~
	    if (Dump.Y) Dump.println("DrawnDlgLast.saveSuspendRequest swSuspend="+swSuspend);//~0308I~
		AG.aComplete.swSuspend=swSuspend;                          //~0308I~
		AG.aStatus.setSuspendRequest(swSuspend);                   //~0308I~
    }                                                              //~0308I~
    //**********************************************               //~9309I~
//  private void setAmmountErr(int[] Pamt)                         //~9309I~//~9420R~
    private void setAmmountErr()                                 //~9420R~
    {                                                              //~9309I~//~9420R~
//        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountErr swCompError="+swCompError);//~9309R~//~9420R~
//        if (!swCompError)                                          //~9309I~//~9420R~
//            return;                                                //~9309I~//~9420R~
//        int base=POINT_RANKM;                                      //~9309I~//~9420R~
//        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9309I~//~9420R~
//        {                                                          //~9309I~//~9420R~
//            if (swsErrLooser[eswn])                                     //~9309I~//~9420R~//~9422R~
//            {                                                      //~9309I~//~9420R~
////                if (eswn==0)                                       //~9309I~//~9414R~//~9420R~
////                    setAmmountBase(Pamt,eswn,-(base+base/2));      //~9309I~//~9414R~//~9420R~
////                else                                               //~9309I~//~9414R~//~9420R~
////                    setAmmountBase(Pamt,eswn,-base);               //~9309I~//~9414R~//~9420R~
//                int a=eswn==0 ? -(base+base/2) : -base;            //~9414I~//~9420R~
//                amtssError[eswn]=new int[PLAYERS];   //[looserEswn][payedToEswn]//~9414I~//~9420R~//~9422R~
//                setAmmountBase(Pamt,eswn,a,amtssError[eswn]);       //~9414I~//~9420R~//~9422R~
//            }                                                      //~9309I~//~9420R~
//        }                                                          //~9309I~//~9420R~
	  	CMP.setAmtError(swsErrLooser,amtsError);                            //~9420R~//~9421R~//~9422R~
    }                                                              //~9309I~
    //**********************************************               //~9309I~
    private void setAmmountMangan(int[] Pamt,int Peswn/*gainer*/,int PctrMangan)            //~9309I~//~9414R~//~9506R~
    {                                                              //~9309I~
//      int base=getManganBase();                                  //~9309I~//~9413R~
        int base=pointDrawnMangan;                                 //~9413I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountMangan eswn="+Peswn+",pointDrawnMangan="+pointDrawnMangan+",ctrMangan="+PctrMangan);//~9309R~//~9413I~//~9506R~
        if (Peswn==0)                                              //~9309I~
        	base+=base/2;                                          //~9309I~
//  	setAmmountBase(Pamt,Peswn/*Gainer*/,base);                            //~9309I~//~9413R~//~9414R~
    	amtssDrawnMangan[Peswn]=new int[PLAYERS];                   //~9414R~//~9422R~//~9506M~
        if (PctrMangan>0 && !swMultiRon)                           //~9506I~
		    AG.aGMsg.drawMsgbar(R.string.Info_InterceptedMultiRon);//~9506I~
        else                                                       //~9506I~
	    	setAmmountBaseMangan(Pamt,Peswn/*Gainer*/,base,amtssDrawnMangan[Peswn],amtsDrawnMangan);//~9414I~//~9422R~//~9506R~
    	if (swDrawnManganAsRon)                                    //~9512I~
	    	setAmmountManganPointStick(Pamt,Peswn/*Gainer*/,amtssDrawnMangan[Peswn],amtsDrawnMangan,PctrMangan);//~9506I~//~9512R~
    }                                                              //~9309I~
//    //**************************************************************//~9414I~//~9421R~
//    //*payToDrawnMangan is gainer base, reverse it to looserBase   //~9414I~//~9421R~
//    //[looser][gainer]                                             //~9420I~//~9421R~
//    //**************************************************************//~9414I~//~9421R~
//    private void reversePayToMangan()                              //~9414I~//~9421R~
//    {                                                              //~9414I~//~9421R~
//        int[][] payTo=new int[PLAYERS][];                          //~9414I~//~9421R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~9414I~//~9421R~
//        {                                                          //~9414I~//~9421R~
//            payTo[ii]=new int[PLAYERS];                            //~9414I~//~9421R~
//        }                                                          //~9414I~//~9421R~
//        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9414R~//~9421R~
//        {                                                          //~9414I~//~9421R~
//            int[] payFrom=amtssDrawnMangan[eswn];                   //~9414R~//~9421R~//~9422R~
//            if (Dump.Y) Dump.println("DrawnDlgLast.reversePayToMangan eswn="+eswn+",payFrom="+Arrays.toString(payFrom));//~9414R~//~9421R~
//            if (payFrom!=null)                                     //~9414I~//~9421R~
//            {                                                      //~9414I~//~9421R~
//                for (int jj=0;jj<PLAYERS;jj++)                     //~9414R~//~9421R~
//                    payTo[jj][eswn]=-payFrom[jj];                  //~9414I~//~9421R~
//            }                                                      //~9414I~//~9421R~
//        }                                                          //~9414I~//~9421R~
//        if (Dump.Y)  Dump.println("DrawnDlgLast.reversePayToMangan 0=payTo="+Arrays.toString(payTo[0]));//~9420R~//~9421R~
//        if (Dump.Y)  Dump.println("DrawnDlgLast.reversePayToMangan 1=payTo="+Arrays.toString(payTo[1]));//~9420I~//~9421R~
//        if (Dump.Y)  Dump.println("DrawnDlgLast.reversePayToMangan 2=payTo="+Arrays.toString(payTo[2]));//~9420I~//~9421R~
//        if (Dump.Y)  Dump.println("DrawnDlgLast.reversePayToMangan 3=payTo="+Arrays.toString(payTo[3]));//~9420I~//~9421R~
//        amtssDrawnMangan=payTo;                                     //~9414R~//~9421R~//~9422R~
//    }                                                              //~9414I~//~9421R~
    //**************************************************************//~9309I~
    private void setAmmountBaseMangan(int[] Pamt,int Peswn/*Gainer*/,int Pbase,int[] Ppayto,int[] Ptotal)    //~9309I~//~9414R~//~9422R~//~9506R~//~9603R~
    {                                                              //~9309I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountBaseMangan Peswn="+Peswn+",base="+Pbase+",amt at entry="+Arrays.toString(Pamt));//~9309I~//~9414R~//~9506R~
        if (cutEswn>=0 && swDrawnManganAsRon)                      //~9603I~
        {                                                          //~9603I~
		    setAmmountBaseManganCutPos(Pamt,Peswn/*looser*/,Pbase,Ppayto,Ptotal);//~9603I~
            return;                                                //~9603I~
        }                                                          //~9603I~
        int[] payTo=Ppayto;	//[looserEswn][payedToEswn]            //~9414I~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9309I~
        {                                                          //~9309I~
//      	int old=Pamt[eswn];                                    //~9413I~//~9414R~
        	if (Peswn==0)                                          //~9309I~
            {                                                      //~9309I~
	        	if (eswn==Peswn)                                   //~9309I~
                {                                                  //~9414I~
    	        	Pamt[eswn]+=Pbase;                               //~9309I~
                    Ptotal[eswn]+=Pbase;                          //~9422I~//~9424R~
                    payTo[eswn]=Pbase;                             //~9414I~
                }                                                  //~9414I~
        	    else                                               //~9309I~
                {                                                  //~9414I~
            		Pamt[eswn]+=-Pbase/3;                            //~9309I~
                    Ptotal[eswn]+=-Pbase/3;                       //~9422I~//~9424R~
                    payTo[eswn]=-Pbase/3;                          //~9414I~
                }                                                  //~9414I~
            }                                                      //~9309I~
            else                                                   //~9309I~
            {                                                      //~9309I~
        		if (eswn==Peswn)                                   //~9309I~
                {                                                  //~9414I~
            		Pamt[eswn]+=Pbase;                               //~9309I~
                    Ptotal[eswn]+=Pbase;                          //~9422I~//~9424R~
                    payTo[eswn]=Pbase;                             //~9414I~
                }                                                  //~9414I~
                else                                               //~9309I~
                if (eswn==0)                                       //~9309I~
                {                                                  //~9414I~
            		Pamt[eswn]+=-Pbase/2;                            //~9309I~
                    Ptotal[eswn]+=-Pbase/2;                       //~9422I~//~9424R~
                    payTo[eswn]=-Pbase/2;                          //~9414I~
                }                                                  //~9414I~
                else                                               //~9309I~
                {                                                  //~9414I~
            		Pamt[eswn]+=-Pbase/4;                            //~9309I~
                    Ptotal[eswn]+=-Pbase/4;                       //~9422I~//~9424R~
                    payTo[eswn]=-Pbase/4;                          //~9414I~
                }                                                  //~9414I~
            }                                                      //~9309I~
//          int inc=Pamt[eswn]-old;                                //~9413I~//~9414R~
//          setMinusStopPayTo(Peswn/*Gainer*/,eswn/*Payer*/,inc);  //~9413I~//~9414R~
        }                                                          //~9309I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountBaseMangan amt exit="+Arrays.toString(Pamt));//~9309R~//~9506R~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountBaseMangan payto="+Arrays.toString(payTo));//~9414I~//~9506R~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountBaseMangan total="+Arrays.toString(Ptotal));//~9422I~//~9506R~
    }                                                              //~9309I~
    //**************************************************************//~9603I~
    private void setAmmountBaseManganCutPos(int[] Pamt,int Peswn/*Gainer*/,int Pbase,int[] Ppayto,int[] Ptotal)//~9603I~
    {                                                              //~9603I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountBaseManganCutPos eswnGainer="+Peswn+",base="+Pbase+",amt at entry="+Arrays.toString(Pamt));//~9603I~
        int[] payTo=Ppayto;	//[looserEswn][payedToEswn]            //~9603I~
        int gain,base;                                                  //~9603R~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9603I~
        {                                                          //~9603I~
        	if (Peswn==0)	//gainer:dealer                        //~9603I~
            {                                                      //~9603I~
	        	if (eswn==Peswn)   //gainer                        //~9603R~
                {                                                  //~9603I~
                	if (Peswn==cutEswn) //gainer is cutPlayer      //~9603I~
                    	gain=Pbase*2;                              //~9603I~
                    else                                           //~9603I~
                    	gain=Pbase+Pbase/3; //add by nondealer cutpos//~9603R~
    	        	Pamt[eswn]+=gain;                              //~9603I~
                    Ptotal[eswn]+=gain;                            //~9603I~
                    payTo[eswn]=gain;                              //~9603I~
                }                                                  //~9603I~
        	    else                    //non dealer               //~9603R~
                {                                                  //~9603I~
                	if (Peswn==cutEswn) //gainer:dealer is cutPlayer//~9603R~
                    	gain=Pbase*2/3;                            //~9603I~
                    else                                           //~9603I~
                    if (eswn==cutEswn)                             //~9603I~
                    	gain=Pbase*2/3;                            //~9603I~
                    else                                           //~9603I~
                    	gain=Pbase/3;                              //~9603I~
            		Pamt[eswn]+=-gain;                             //~9603I~
                    Ptotal[eswn]+=-gain;                           //~9603I~
                    payTo[eswn]=-gain;                             //~9603I~
                }                                                  //~9603I~
            }                                                      //~9603I~
            else  //gainer:nondealer                               //~9603I~
            {                                                      //~9603I~
            	if (Peswn==cutEswn)                                //~9603I~
                	base=Pbase*2;                                  //~9603I~
                else                                               //~9603I~
                	base=Pbase;                                    //~9603I~
        		if (eswn==Peswn)    //gainer                       //~9603R~
                {                                                  //~9603I~
                	if (cutEswn==ESWN_E)	//dealer is cutPlayer  //~9603I~
	                    gain=base+base/2;                          //~9603I~
                    else                    //non dealer is cutPlayer//~9603I~
                	if (eswn==cutEswn)  	//nondealer cutPlayer  //~9603I~
                    	gain=base;                                 //~9603I~
                    else                    //other nondealer is cutplayer//~9603I~
	                    gain=base+base/4;                          //~9603R~
            		Pamt[eswn]+=gain;                              //~9603I~
                    Ptotal[eswn]+=gain;                            //~9603I~
                    payTo[eswn]=gain;                              //~9603I~
                }                                                  //~9603I~
                else                                               //~9603I~
                if (eswn==0)    //dealer pay of nondealer gain     //~9603I~
                {                                                  //~9603I~
                	if (eswn==cutEswn)                             //~9603I~
                    	gain=base;                                 //~9603R~
                    else                                           //~9603I~
                    	gain=base/2;                               //~9603R~
            		Pamt[eswn]+=-gain;                             //~9603R~
                    Ptotal[eswn]+=-gain;                           //~9603R~
                    payTo[eswn]=-gain;                             //~9603R~
                }                                                  //~9603I~
                else           //other non dealer pay of non dealer gain//~9603R~
                {                                                  //~9603I~
                	if (eswn==cutEswn)  //cut player               //~9603R~
                    	gain=base/2;                            //~9603R~
                    else                                           //~9603I~
                    	gain=base/4;                            //~9603R~
            		Pamt[eswn]+=-gain;                             //~9603R~
                    Ptotal[eswn]+=-gain;                           //~9603R~
                    payTo[eswn]=-gain;                             //~9603R~
                }                                                  //~9603I~
            }                                                      //~9603I~
        }                                                          //~9603I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountBaseManganCutPos amt exit="+Arrays.toString(Pamt));//~9603I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountBaseManganCutPos payto="+Arrays.toString(payTo));//~9603I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountBaseManganCutPos total="+Arrays.toString(Ptotal));//~9603I~
    }                                                              //~9603I~
    //**************************************************************//~9506I~
    private void setAmmountManganPointStick(int[] Pamt,int Peswn/*Gainer*/,int[] Ppayto,int[] Ptotal,int PctrMangan)//~9506I~//~9512R~
    {                                                              //~9506I~
        int[] payTo=Ppayto;	//[looserEswn][payedToEswn]            //~9506M~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountManganPointStick Peswn="+Peswn+",ctrMangan="+PctrMangan);//~9506I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountManganPointStick before amt="+Arrays.toString(Pamt));//~9506I~//~9512R~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountManganPointStick before payto="+Arrays.toString(payTo));//~9506I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountManganPointStick before total="+Arrays.toString(Ptotal));//~9506I~
        int dup=gameDup*dupRate;                                   //~9506I~
        int dup3=dup/3;                                            //~9512I~
        int reach=gameReach*POINT_REACH;                           //~9506I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountManganPointStick reach="+reach+",gameDup="+gameDup+",dup="+dup);//~9513I~
//        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9506I~//~9512R~
//        {                                                          //~9506I~//~9512R~
//            if (eswn==Peswn && PctrMangan==0)                                     //~9506I~//~9512R~
//            {                                                      //~9506I~//~9512R~
//                Pamt[eswn]+=reach;                                 //~9506I~//~9512R~
//                Ptotal[eswn]+=reach;                               //~9506I~//~9512R~
//            }                                                      //~9506I~//~9512R~
//            if (swMultiRonPointDupAll || PctrMangan==0)            //~9506I~//~9512R~
//            {                                                      //~9506I~//~9512R~
//                if (eswn==Peswn)                                   //~9506I~//~9512R~
//                {                                                  //~9506I~//~9512R~
//                    Pamt[eswn]+=dup;                               //~9506I~//~9512R~
//                    Ptotal[eswn]+=dup;                             //~9506I~//~9512R~
//                    payTo[eswn]+=dup;                              //~9506I~//~9512R~
//                }                                                  //~9506I~//~9512R~
//                else                                               //~9506I~//~9512R~
//                {                                                  //~9506I~//~9512R~
//                                                                 //~9512R~
//                    Pamt[eswn]-=dup3;                               //~9506I~//~9512R~
//                    Ptotal[eswn]-=dup3;                             //~9506I~//~9512R~
//                    payTo[eswn]-=dup3;                              //~9506I~//~9512R~
//                }                                                  //~9506I~//~9512R~
//            }                                                      //~9506I~//~9512R~
//        }                                                          //~9506I~//~9512R~
        if (PctrMangan==0)                                         //~9512I~
        {                                                          //~9512I~
            Pamt[Peswn]+=reach;                                    //~9512I~
            Ptotal[Peswn]+=reach;                                  //~9512I~
        }                                                          //~9512I~
	    if (swMultiRonPointDupAll || PctrMangan==0)                //~9512I~//~9513R~
        {                                                          //~9512I~
            Pamt[Peswn]+=dup;                                      //~9512I~
            Ptotal[Peswn]+=dup;                                    //~9512I~
            payTo[Peswn]+=dup;                                     //~9512I~
            for (int eswn=0;eswn<PLAYERS;eswn++)                   //~9512I~
            {                                                      //~9512I~
                if (eswn!=Peswn)                                   //~9512I~
                {                                                  //~9512I~
                    Pamt[eswn]-=dup3;                              //~9512I~
                    Ptotal[eswn]-=dup3;                            //~9512I~
                    payTo[eswn]-=dup3;                             //~9512I~
                }                                                  //~9512I~
            }                                                      //~9512I~
        }                                                          //~9512I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountManganPointStick After amt="+Arrays.toString(Pamt));//~9506I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountManganPointStick After payto="+Arrays.toString(payTo));//~9506I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountManganPointStick After total="+Arrays.toString(Ptotal));//~9506I~
    }                                                              //~9506I~
//    //**********************************************               //~9309I~//~9413R~
//    private int getManganBase()                                    //~9309I~//~9413R~
//    {                                                              //~9309I~//~9413R~
//        int idx=spnDrawnMangan.getSelectedIndex();                   //~9309I~//~9413R~
//        if (idx<0)                                                 //~9309I~//~9413R~
//            idx=0;                                                 //~9309I~//~9413R~
//        if (idx>=baseMangan.length)                                //~9309I~//~9413R~
//            idx=baseMangan.length-1;                               //~9309I~//~9413R~
//        int base=baseMangan[idx];                                  //~9309I~//~9413R~
//        if (Dump.Y) Dump.println("DrawnDlgLast.setManganBase base="+base);//~9309I~//~9413R~
//        return base;                                               //~9309I~//~9413R~
//    }                                                              //~9309I~//~9413R~
    //***********************************************************  //~9309I~//~9420R~
    private void setAmmountPending(int[] Pamt,int PctrPending,boolean[] PswPending)//~9309R~//~9420R~
//  private void setAmmountPending(int PctrPending,boolean[] PswPending)//~9420I~
    {                                                              //~9309I~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountPending ctrPending="+PctrPending+",swPening="+Arrays.toString(PswPending));//~9309R~//~9424R~
        int base=pendingBase;                                      //~9309R~
        int amtP,amtM;                                             //~9309I~
        switch (PctrPending)                                       //~9309R~
		{                                                          //~9309I~
		case 0:                                                    //~9309I~
		case PLAYERS:                                              //~9309I~
        	return;                                                //~9309I~
        case 1:                                                    //~9309I~
        	amtP=base; amtM=-base/3;                               //~9309I~
            break;                                                 //~9309I~
        case 3:                                                    //~9309I~
        	amtP=base/3; amtM=-base;                               //~9309I~
            break;                                                 //~9309I~
        default:                                                   //~9309I~
        	amtP=base/2; amtM=-base/2;                             //~9309I~
        }                                                          //~9309I~
//        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9309R~//~9420R~//~9422R~
//        {                                                          //~9309R~//~9420R~//~9422R~
//            if (PswPending[eswn])                                  //~9309I~//~9420R~//~9422R~
//                Pamt[eswn]+=amtP;                                    //~9309I~//~9420R~//~9422R~
//            else                                                   //~9309I~//~9420R~//~9422R~
//            {                                                      //~9414I~//~9420R~//~9422R~
//                Pamt[eswn]+=amtM;                                    //~9309I~//~9420R~//~9422R~
//                amtssPending[eswn]=new int[PLAYERS];                //~9403I~//~9414I~//~9420R~//~9422R~
//                for (int jj=0;jj<PLAYERS;jj++)                     //~9414I~//~9420R~//~9422R~
//                {                                                  //~9414I~//~9420R~//~9422R~
//                    if (jj==eswn)                                  //~9414I~//~9420R~//~9422R~
//                        amtssPending[eswn][jj]=amtM;                //~9414R~//~9420R~//~9422R~
//                    else                                           //~9414I~//~9420R~//~9422R~
//                    if (PswPending[jj])                            //~9414I~//~9420R~//~9422R~
//                        amtssPending[eswn][jj]=amtP;                //~9414R~//~9420R~//~9422R~
//                }                                                  //~9414I~//~9420R~//~9422R~
//            }                                                      //~9414I~//~9420R~//~9422R~
//        }                                                          //~9309R~//~9420R~//~9422R~
                                                                   //~9422I~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9422I~
        {                                                          //~9422I~
            if (PswPending[eswn])                                  //~9422I~
            {                                                      //~9422I~
                Pamt[eswn]+=amtP;                                  //~9422I~
                amtssPending[eswn]=new int[PLAYERS];               //~9422I~
                for (int jj=0;jj<PLAYERS;jj++)                     //~9422I~
                {                                                  //~9422I~
                    if (PswPending[jj])                            //~9422I~
                        amtssPending[eswn][jj]=amtP;               //~9422I~
                    else                                           //~9422I~
                        amtssPending[eswn][jj]=amtM;               //~9422M~
                }                                                  //~9422I~
            }                                                      //~9422I~
            else                                                   //~9422I~
            {                                                      //~9422I~
                Pamt[eswn]+=amtM;                                  //~9422I~
            }                                                      //~9422I~
        }                                                          //~9422I~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~9420R~
//        {                                                        //~9420R~
//            int eswn=ACC.getCurrentEswnByPosition(ii);           //~9420R~
//            if (PswPending[eswn])                                //~9420R~
//                amtPending[ii]=amtP;                             //~9420R~
//            else                                                 //~9420R~
//                amtPending[ii]=amtM;                             //~9420R~
//        }                                                        //~9420R~
//      setMinusStoppayToPending(PswPending,amtP,amtM);            //~9413I~//~9414R~
//      if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountPending amtPending="+Arrays.toString(amtPending));//~9309I~//~9421R~
        if (Dump.Y) Dump.println("DrawnDlgLast.setAmmountPending amtssPending=",amtssPending);//~9421I~//~9422R~
    }                                                              //~9309I~
    //**********************************************               //~9421I~
    private boolean chkMinusStop()                                 //~9421I~
    {                                                              //~9421I~
    	int amt,idx;                                               //~9421I~
        if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop swMinusStop="+swMinusStop);//~9421I~
        Arrays.fill(minusPrize,0);                                 //~9424I~
        int[] newTotal=new int[PLAYERS];                           //~9421I~
	    int[] newTotalWithErr=new int[PLAYERS];                    //~9421I~//~9424I~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9421I~
        {                                                          //~9421I~
        	idx=ACC.currentEswnToPosition(eswn);                          //~9421I~
        	int oldScore=ACC.score[idx];                           //~9422I~
            adjustedScoreByMangan[idx]=oldScore+amtsDrawnMangan[idx];//~9422I~
        	amt=oldScore+totalAmmount[eswn]; //oldScore+drawnMangan+drawnPending                //~9421I~//~9422R~
        	newTotal[eswn]=amt;                                    //~9421I~
            adjustedScore[idx]=amt;                                //~9422I~
        	amt+=amtsError[eswn];                                   //~9421I~//~9422R~
        	newTotalWithErr[eswn]=amt;		                       //~9422R~
        	totalAmmountWithErr[eswn]=totalAmmount[eswn]+amtsError[eswn];//~9424I~
        }                                                          //~9421I~
        if (!swMinusStop)                                          //~9421I~//~9424M~
        	return false;                                          //~9421I~//~9424M~
        boolean[] swsStopWithErr=new boolean[PLAYERS];             //~9421I~
    	boolean swStopWithErr=chkMinusStop(newTotalWithErr,swsStopWithErr);//~9421I~
        if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop score="+Arrays.toString(ACC.score));//~9421I~
        if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop totalAmmount="+Arrays.toString(totalAmmount));//~9421I~
        if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop amtsDrawnMangan="+Arrays.toString(amtsDrawnMangan));//~9422I~
        if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop adjustedScoreByMangan="+Arrays.toString(adjustedScoreByMangan));//~9422I~
        if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop amtsError="+Arrays.toString(amtsError));//~9421I~//~9422R~
        if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop newTotal="+Arrays.toString(newTotal));//~9421I~
        if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop swStopWithErr="+swStopWithErr+",swsStopWithErr="+Arrays.toString(swsStopWithErr));//~9421I~
        if (!swStopWithErr)                                        //~9421I~
        	return false;                                          //~9421I~
        boolean[] swsStop=new boolean[PLAYERS];                    //~9421I~
    	boolean swStop=chkMinusStop(newTotal,swsStop);             //~9421I~
//      adjustedScore=ACC.score.clone(); //index seq               //~9421R~//~9422R~
//      adjustedScore=newTotal.clone(); //index seq                //~9421R~//~9422R~
        for (int eswn=0;eswn<PLAYERS;eswn++)                           //~9421I~
        {                                                          //~9421I~
        	if (!swsStopWithErr[eswn])	//finally(added error) minus//~9421I~
            	continue;                                          //~9421I~
        	if (!swsStop[eswn])	//minus without error              //~9421I~
            	continue;                                          //~9421I~
            amt=newTotalWithErr[eswn];                                    //~9421I~//~9424R~
            if (amt<0 || (amt==0 && swMinus0))                   //~9421I~
            {                                                      //~9421I~
//  		    setMinusCharge(ACC.score,eswn,amtssDrawnMangan[eswn]);//~9421R~//~9422R~
    		    setMinusCharge(ACC.score,eswn,amtssDrawnMangan);//~9421R~//~9422R~
		        if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop minusPrize="+Arrays.toString(minusPrize));//~9421R~
            }                                                      //~9421I~
        }
        if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop amtssPending",amtssPending);//~9421I~//~9422R~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9421I~//~9422R~
        {                                                          //~9421I~//~9422R~
            if (!swsStopWithErr[eswn])  //finally(added error) minus//~9421I~//~9422R~
                continue;                                          //~9421I~//~9422R~
            if (!swsStop[eswn]) //minus without error              //~9421I~//~9422R~
                continue;                                          //~9421I~//~9422R~
            amt=newTotalWithErr[eswn];                                    //~9421I~//~9422R~//~9424R~
            if (amt<0 || (amt==0 && swMinus0))                     //~9421I~//~9422R~
            {                                                      //~9421I~//~9422R~
                if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop minusPrize="+Arrays.toString(minusPrize));//~9421I~//~9422R~
                setMinusChargeSprit(adjustedScoreByMangan,eswn,amtssPending);//~9421I~//~9422R~
                if (Dump.Y) Dump.println("DrawnDlgLast.chkMinusStop minusPrize="+Arrays.toString(minusPrize));//~9421I~//~9422R~
            }                                                      //~9421I~//~9422R~
        }                                                          //~9421I~//~9422R~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9424I~
        {                                                          //~9424I~
        	idx=ACC.currentEswnToPosition(eswn);                   //~9424I~
            adjustedScoreWithErr[idx]=newTotalWithErr[eswn];       //~9424I~
        }                                                          //~9424I~
        return true;//~9421I~
    }                                                              //~9421I~
    //**********************************************               //~9421M~
    private boolean chkMinusStop(int[] Pamt,boolean[] PswsStop)    //~9421M~
    {                                                              //~9421M~
    	boolean swStop=false;                                      //~9421M~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9421M~
        {                                                          //~9421M~
            int amt=Pamt[eswn];                                    //~9421M~
            if (amt<0 || (amt==0 && swMinus0))                     //~9421M~
            {                                                      //~9421M~
                if (!swMinusRobot && Accounts.isDummy(eswn))       //~9421M~
                {                                                  //~9421M~
//                  UView.showToast(R.string.Info_NoMinusStopRobot);//~9421M~
                    continue;                                      //~9421M~
                }                                                  //~9421M~
                swStop=true;                                       //~9421M~
                PswsStop[eswn]=true;                               //~9421M~
            }                                                      //~9421M~
        }                                                          //~9421M~
        return swStop;                                             //~9421M~
    }                                                              //~9421M~
    //***************************************                      //~9421I~
	private int getPayTo(int PeswnLooser,int PeswnGainer,int[][] PpayTo/*[gainer][looser]*/)//~9421I~
    {                                                              //~9421I~
        int rc=0;                                                  //~9421I~
    	if (PpayTo[PeswnGainer]!=null)                             //~9421I~
        {                                                          //~9421I~
		    if (Dump.Y) Dump.println("DrawnDlgLast:PpayTo[gainer]="+Arrays.toString(PpayTo[PeswnGainer]));//~9421I~
	        rc=PpayTo[PeswnGainer][PeswnLooser];                   //~9421R~
        }                                                          //~9421I~
	    if (Dump.Y) Dump.println("DrawnDlgLast:getPayTo rc="+rc+",looser="+PeswnLooser+",gainer="+PeswnGainer+",PpayTo[gainer]="+Arrays.toString(PpayTo[PeswnGainer]));//~9421R~
        return rc;                                                 //~9421I~
    }                                                              //~9421I~
    //***************************************                      //~9421I~
    private void setMinusCharge(int[] Pscore/*position idx*/,int Peswn,int[][] PpayTo/*[gainer][looser]*/)//~9421R~//~9422R~
    {                                                              //~9421I~
	    if (Dump.Y) Dump.println("DrawnDlgLast:setMinusCharge eswn="+Peswn+",score="+Arrays.toString(Pscore));//~9421R~//~9422R~
	    if (Dump.Y) Dump.println("DrawnDlgLast:setMinusCharge PpayTo",PpayTo);//~9421R~
    	boolean sw1st=true;                                        //~9421I~
        int idxLooser=ACC.currentEswnToPosition(Peswn);                          //~9421I~//~9422R~
	    int remain;                                                //~9421I~
	    remain=Pscore[idxLooser];                                    //~9421R~//~9422R~
	    if (remain<0 || (remain==0 && swMinus0))                   //~9421I~
            return;	//aready charged                               //~9421I~
        for (int eswnGainer=0;eswnGainer<PLAYERS;eswnGainer++)                             //~9421I~//~9422R~
        {                                                          //~9421I~
//      	int pay=-PpayTo(eswn,ii);                              //~9421R~
        	int pay=getPayTo(Peswn,eswnGainer,PpayTo);                              //~9421I~//~9422R~
            if (pay==0)                                            //~9421I~
                continue;                                          //~9421I~
            remain+=pay;      //pay:minus                          //~9421I~
		    if (Dump.Y) Dump.println("DrawnDlgLast:setMinusCharge eswnLooser="+Peswn+",idxLooser="+idxLooser+",eswnGainer="+eswnGainer+",pay="+pay+",remain="+remain);//~9421R~//~9422R~
	        if (!(remain<0 || (remain==0 && swMinus0)))            //~9421I~
            	continue;                                          //~9421I~
            int idxGainer=ACC.currentEswnToPosition(eswnGainer);   //~9422I~
//          if (!swMinusPayGetAllPoint) //point is pay up to 0     //~9421I~//~9422R~
            if (!swMinusPayGetAllPoint && !swMinusPay) //point is pay up to 0//~9422I~
            {                                                      //~9421I~
		    	if (Dump.Y) Dump.println("DrawnDlgLast:setMinusCharge sw1st="+sw1st+",eswnGainer="+eswnGainer+",idxGainer="+idxGainer+",pay="+pay+",remain="+remain);//~9422R~
                if (sw1st)                                         //~9421I~
                {                                                  //~9421I~
                    adjustedScore[idxGainer]+=remain; //subtract from gainer//~9421R~//~9422R~
//                  adjustedScore[idx]-=remain;  //add to looser   //~9421R~
                    adjustedScore[idxLooser]-=remain;  //add to looser //~9421I~//~9422R~
                                                                   //~9421I~
//                    chargeToGainer[ii]+=remain; //subtract from gainer//~9421R~
//                    chargeToGainer[idx]-=remain; //subtract from gainer//~9421R~
//                    minusPrize[ii]+=pointMinusStop;              //~9421R~
//                    minusPrize[idx]=-pointMinusStop;             //~9421R~
                }                                                  //~9421I~
                else                                               //~9421I~
                {                                                  //~9421I~
                    adjustedScore[idxGainer]+=pay;    //subtruct from gainer//~9421R~//~9422R~
//                  adjustedScore[idx]-=pay;     //add to looser   //~9421R~
                    adjustedScore[idxLooser]-=pay;     //add to looser //~9421I~//~9422R~
                                                                   //~9421I~
//                    chargeToGainer[ii]+=pay; //subtract from gainer//~9421R~
//                    chargeToGainer[idx]-=pay; //subtract from gainer//~9421R~
                }                                                  //~9421I~
                if (Dump.Y) Dump.println("DrawingDlgLast:setMinusCharge adjustedScore[idxGainer]="+adjustedScore[idxGainer]+",adjustedScore[idxLooser]="+adjustedScore[idxLooser]+",adjustedScore="+Arrays.toString(adjustedScore));//~9421R~//~9422R~
            }                                                      //~9421I~
            if (swMinusPay)                                        //~9422I~
            {                                                      //~9422I~
                if (sw1st)                                         //~9422I~
                {                                                  //~9422I~
                    minusPrize[idxGainer]+=pointMinusStop;         //~9422I~
                    minusPrize[idxLooser]=-pointMinusStop;         //~9422I~
                }                                                  //~9422I~
            }                                                      //~9422I~
            else                                                   //~9422I~
            {                                                      //~9422I~
                if (sw1st)                                         //~9421I~//~9422R~
                {                                                  //~9421I~//~9422R~
                    chargeToGainer[eswnGainer]+=remain; //subtract from gainer//~9421R~//~9422R~
                    chargeToGainer[Peswn]-=remain;                 //~9422R~
                    minusPrize[idxGainer]+=pointMinusStop;                //~9421I~//~9422R~
                    minusPrize[idxLooser]=-pointMinusStop;               //~9421I~//~9422R~
                }                                                  //~9421I~//~9422R~
                else                                               //~9421I~//~9422R~
                {                                                  //~9421I~//~9422R~
                    chargeToGainer[eswnGainer]+=pay; //subtract from gainer//~9421R~//~9422R~
                    chargeToGainer[Peswn]-=pay;                    //~9422R~
                }                                                  //~9421I~//~9422R~
            }                                                      //~9422I~
            if (Dump.Y) Dump.println("DrawnDlgLast.setMinusCharge sw1st="+sw1st+",remain="+remain+",pay="+pay);//~9421R~//~9422R~
            if (Dump.Y) Dump.println("DrawnDlgLast.setMinusCharge after eswnGainer="+eswnGainer+",adjustedScore="+Arrays.toString(adjustedScore)+",chargeToGainer="+Arrays.toString(chargeToGainer));//~9421R~//~9422R~
	        sw1st=false;                                           //~9421I~//~9422R~
        }                                                          //~9421I~
        if (Dump.Y) Dump.println("DrawingDlgLast:setMinusCharge adjustedScore="+Arrays.toString(adjustedScore));//~9421I~
        if (Dump.Y) Dump.println("DrawingDlgLast:setMinusCharge chargeTogainer="+Arrays.toString(chargeToGainer));//~9421I~
        if (Dump.Y) Dump.println("DrawingDlgLast:setMinusCharge minusPrize="+Arrays.toString(minusPrize));//~9421R~
    }                                                              //~9421I~
    //***************************************                      //~9422I~
    private void setMinusChargeSprit(int[] Pscore/*position idx*/,int Peswn,int[][] PpayTo/*[gainer][looser]*/)//~9422I~
    {                                                              //~9422I~
	    if (Dump.Y) Dump.println("DrawnDlgLast:setMinusChargeSprit eswn="+Peswn+",score="+Arrays.toString(Pscore));//~9422I~
	    if (Dump.Y) Dump.println("DrawnDlgLast:setMinusChargeSprit PpayTo",PpayTo);//~9422I~
    	boolean sw1st=true;                                        //~9422I~
        int idxLooser=ACC.currentEswnToPosition(Peswn);            //~9422I~
	    int remain;                                                //~9422I~
	    remain=Pscore[idxLooser];                                  //~9422I~
	    if (remain<0 || (remain==0 && swMinus0))                   //~9422I~
            return;	//aready charged                               //~9422I~
        for (int eswnGainer=0;eswnGainer<PLAYERS;eswnGainer++)     //~9422I~
        {                                                          //~9422I~
        	int pay=getPayTo(Peswn,eswnGainer,PpayTo);             //~9422I~
            if (pay==0)                                            //~9422I~
                continue;                                          //~9422I~
            remain+=pay;      //pay:minus                          //~9422I~
		    if (Dump.Y) Dump.println("DrawnDlgLast:setMinusCharge eswnLooser="+Peswn+",idxLooser="+idxLooser+",eswnGainer="+eswnGainer+",pay="+pay+",remain="+remain);//~9422I~
	        if (!(remain<0 || (remain==0 && swMinus0)))            //~9422I~
            	continue;                                          //~9422I~
            if (Dump.Y) Dump.println("DrawnDlgLast.setMinusCharge before minusPrize="+Arrays.toString(minusPrize));//~9422I~
	        int idxGainer=ACC.currentEswnToPosition(eswnGainer);   //~9422I~
            minusPrize[idxGainer]+=pointMinusStop/ctrPending;      //~9422R~
            minusPrize[idxLooser]-=pointMinusStop/ctrPending;     //~9422R~
            if (Dump.Y) Dump.println("DrawnDlgLast.setMinusCharge eswnGainer="+eswnGainer+",idxGainer="+idxGainer);//~9422R~
            if (Dump.Y) Dump.println("DrawnDlgLast.setMinusCharge after minusPrize="+Arrays.toString(minusPrize));//~9422I~
        }                                                          //~9422I~
        if (Dump.Y) Dump.println("DrawingDlgLast:setMinusCharge minusPrize="+Arrays.toString(minusPrize));//~9422I~
    }                                                              //~9422I~
    //**********************************************               //~9309I~
    private void showAmmountLatest()                                    //~9309I~
    {                                                              //~9309I~
        if (Dump.Y) Dump.println("DrawnDlgLast.showAmmountLatest");      //~9309I~//~9B11R~
        getValue();                                                //~9309I~
        showAmmount();                                             //~9309I~
    }                                                              //~9309I~
    //**********************************************               //~9310I~
    private void showAmmountReceived()                             //~9310I~
    {                                                              //~9310I~
        if (Dump.Y) Dump.println("DrawnDlgLast.showAmmountReceived");//~9310I~
    	parseValueSendData(receivedDialogData,0);                  //~9310I~//~0314R~
        if (!swRequester)                                          //~9311I~
	    	enableTemporally(true);                                //~9311R~
        showMangan();                                              //~9310I~
	    showPending();                                             //~9310I~
	    showErr();                                                 //~9310I~
        showAmmount();                                             //~9310I~
	    setCheckNextGame();                                        //~9311I~
        if (!swRequester)                                          //~9311I~
	        enableTemporally(false);                               //~9311R~
    }                                                              //~9310I~
    //**********************************************               //~9311I~
    private void enableTemporally(boolean Penable)                 //~9311I~
    {                                                              //~9311I~
        if (Dump.Y) Dump.println("DrawnDlgLast.enableTemporally enable="+Penable);//~9311I~
	    enableCB(Penable,cbsPending);                              //~9311I~
	    enableCB(Penable,cbsMangan);                               //~9311I~
        enableCB(Penable,cbsErr);                                //~9311I~//~9B11R~//~9B12R~
//  	cbError.setFixed(!Penable);                                //~9311R~//~9424R~
        rgNextGame.setFixed(!Penable);                             //~9311I~//~9B11R~//~9B12R~
    }                                                              //~9311I~
    //**********************************************               //~9B12I~
    private void enableRequesterCB()                               //~9B12I~
    {                                                              //~9B12I~
        if (Dump.Y) Dump.println("DrawnDlgLast.enableRequesterCB");//~9B12I~
//      enableCB(Penable,cbsPending);                              //~9B12I~
//      enableCB(Penable,cbsMangan);                               //~9B12I~
        enableCB(true,cbsErr);                                     //~9B12I~
//      rgNextGame.setFixed(false);                                //~9B12I~//~0209R~
    }                                                              //~9B12I~
    //**********************************************               //~9309I~
    private void getValue()                                        //~9309I~
    {                                                              //~9309I~
        if (Dump.Y) Dump.println("DrawnDlgLast.getValue");         //~9309I~
        getValuePending();                                         //~9310I~
        getValueMangan();                                             //~9309I~//~9310R~
        getValueErr();                                             //~9310I~
		swSuspend=cbSuspend.getState();                            //~0308I~
        if (Dump.Y) Dump.println("DrawnDlgLast.getValue swSuspend="+swSuspend);//~0308I~
    }                                                              //~9309I~
    //**********************************************               //~9310I~
    private void getValuePending()                                 //~9310I~
    {                                                              //~9310I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9310I~
        {                                                          //~9310I~
        	swsPending[ii]=cbsPending[ii].getState();              //~9310I~//~9504R~
        }                                                          //~9310I~
        if (Dump.Y) Dump.println("DrawnDlgLast.getValuePending swsPending="+Arrays.toString(swsPending));//~9310I~//~9504R~
    }                                                              //~9310I~
    //**********************************************               //~9309I~
    private void getValueMangan()                                     //~9309I~//~9310R~
    {                                                              //~9309I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9309I~
        {                                                          //~9309I~
        	swsMangan[ii]=cbsMangan[ii].getState();                        //~9309I~//~9310R~//~9506R~
        }                                                          //~9309I~
        if (Dump.Y) Dump.println("DrawnDlgLast.getValueMangan swsMangan="+Arrays.toString(swsMangan));//~9309I~//~9310R~//~9506R~
    }                                                              //~9309I~
    //**********************************************               //~9310I~
    private void getValueErr()                                     //~9310I~
    {                                                              //~9310I~
    	swErr=false;                                               //~9708I~
        for (int ii=0;ii<PLAYERS;ii++)                         //~9310R~//~9708R~
        {                                                      //~9310R~//~9708R~
            swsErrLooser[ii]=cbsErr[ii].getState();                //~9310R~//~9422R~//~9708R~
            if (swsErrLooser[ii])                                  //~9708I~
            	swErr=true;                                        //~9708I~
        }                                                      //~9310R~//~9708R~
        if (Dump.Y) Dump.println("DrawnDlgLast.getValueErr swErr="+swErr+",swsErrLooser="+Arrays.toString(swsErrLooser));//~9310R~//~9422R~//~9424R~//~9708R~
    }                                                              //~9310I~
    //**********************************************               //~9310I~
    //*from UAEndGame                                              //~9310I~
    //*data for LAST_CONFIRMED                                     //~9310I~
    //**********************************************               //~9310I~
    public String getValueSendData()                                 //~9310I~
    {                                                              //~9310I~
    	StringBuffer sb=new StringBuffer();                        //~9310I~
        sb.append(MSG_SEPAPP2+typeNextGame);                      //~9310R~
//      sb.append(MSG_SEPAPP2+(swCompError ? "1" :"0"));           //~9310I~//~9424R~
//      sb.append(MSG_SEPAPP2+"9");  //9:not used id                //~9424I~//~0308R~
        sb.append(MSG_SEPAPP2+(swSuspend?1:0));  //9:not used id   //~0308I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9310I~
//        	sb.append((ii==0 ? MSG_SEPAPP2 :MSG_SEPAPP)+(swsPending[ii] ? "1" :"0"));//~9310I~//~9423R~//~9504R~
         	sb.append((ii==0 ? MSG_SEPAPP2 :MSG_SEPAPP)+intsPending[ii]);//~9423I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9310I~
        	sb.append((ii==0 ? MSG_SEPAPP2 :MSG_SEPAPP)+(swsMangan[ii] ? "1" :"0"));//~9310I~//~9506R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9310I~
        	sb.append((ii==0 ? MSG_SEPAPP2 :MSG_SEPAPP)+(swsErrLooser[ii] ? "1" :"0"));//~9310I~//~9422R~
        String s=sb.toString();                                    //~9310I~
        if (Dump.Y) Dump.println("DrawnDlgLast.getValueSendData str="+s);//~9310I~
        return s;                                                  //~9310I~
    }                                                              //~9310I~
    //**********************************************               //~9310I~
    private void parseValueSendData(int[] PintParm,int Ppos)       //~9310I~//~0314R~
    {                                                              //~9310I~
        swMangan=false;                                            //~9506I~
    	int pos=Ppos;                                              //~9310I~
        typeNextGame=PintParm[pos++];                              //~9310R~
//      swCompError=PintParm[pos++]!=0;                            //~9310I~//~9424R~
        pos++;                                                     //~9424I~
                                                                   //~9424I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9310I~
//      	swsPending[ii]=PintParm[pos++]!=0;                     //~9310R~//~9423R~//~9504R~
        	intsPending[ii]=PintParm[pos++];                      //~9423I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9310I~
        	swsMangan[ii]=PintParm[pos++]!=0;                      //~9310R~//~9423R~//~9506R~
        swErr=false;                                               //~0314I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9310I~
        {                                                          //~0314I~
        	swsErrLooser[ii]=PintParm[pos++]!=0;                       //~9310R~//~9422R~
            if (swsErrLooser[ii])                                  //~0314I~
            	swErr=true;                                        //~0314I~
        }                                                          //~0314I~
        int ctrM=0;                                                //~9520I~
		Arrays.fill(swsNoManganByEswn,false);                      //~9520I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9423I~
        {                                                          //~9423I~
        	int reason;                                            //~9423I~
//            if (swsMangan[ii])                                   //~9423R~//~9506R~
//                reason=swsPending[ii] ? EGDR_MANGAN_PENDING : EGDR_MANGAN;//~9423R~//~9504R~
//            else                                                 //~9423R~
//                reason=swsPending[ii] ? EGDR_PENDING : EGDR_PENDINGNO;//~9423R~//~9504R~
	        switch (intsPending[ii])                               //~9423I~
            {                                                      //~9423I~
            case PENDING_ONLY:                                     //~9423I~
            	swsPending[ii]=true;                               //~9424I~//~9504R~
            	reason=EGDR_PENDING;                               //~9423I~
            	break;                                              //~9423I~
            case PENDING_MANGAN:                                   //~9423I~
            	swsPending[ii]=true;                               //~9424I~//~9504R~
            	reason=EGDR_MANGAN_PENDING;                        //~9423I~
		        swMangan=true;                                     //~9506I~
                ctrM++;                                            //~9520I~
            	break;                                              //~9423I~
            case NOPENDING_MANGAN:                                 //~9506I~
            	swsPending[ii]=false;                              //~9506I~
            	reason=EGDR_MANGAN;                                //~9506I~
		        swMangan=true;                                     //~9506I~
                ctrM++;                                            //~9520I~
            	break;                                             //~9506I~
//            case PENDING_MANGAN_BYSETTING:                         //~9423I~//~9506R~
//                swsPending[ii]=true;                               //~9424I~//~9504R~//~9506R~
//                reason=EGDR_MANGAN_PENDING_BYSETTING;              //~9423I~//~9506R~
//                break;                                              //~9423I~//~9506R~
//            case PENDING_MANGAN_ASRON:                           //~9506I~
//                swsPending[ii]=true;                             //~9506I~
//                reason=EGDR_MANGAN_RON;                          //~9506I~
//                break;                                           //~9506I~
            default:                                               //~9423I~
            	swsPending[ii]=false;                              //~9424I~//~9504R~
            	reason=EGDR_PENDINGNO;                             //~9423I~
            }                                                      //~9423I~
            if (ctrM>1 && !swMultiRon)                             //~9520I~
            {                                                      //~9520I~
		        if (Dump.Y) Dump.println("DrawnDlgLast.parseValueSendData ii="+ii+",ctr="+ctrM+",swsNoManganByEswnintsPending="+Arrays.toString(swsNoManganByEswn));//~9520I~
		        swsNoManganByEswn[ii]=true;                        //~9520I~
            }                                                      //~9520I~
	        reasonResponse[ii]=reason;                             //~9423I~
        }                                                          //~9423I~
        if (Dump.Y) Dump.println("DrawnDlgLast.parseValueSendData pos="+Ppos+",intParm(dialogData)="+Arrays.toString(PintParm));//~9310I~//~0331R~
        if (Dump.Y) Dump.println("DrawnDlgLast.parseValueSendData swsPending="+Arrays.toString(swsPending));//~9310I~//~9424R~//~9504R~
        if (Dump.Y) Dump.println("DrawnDlgLast.parseValueSendData swMangan="+Arrays.toString(swsMangan));//~9310I~//~9424R~//~9506R~
        if (Dump.Y) Dump.println("DrawnDlgLast.parseValueSendData swsErrLooser="+Arrays.toString(swsErrLooser));//~9310R~//~9422R~//~9424R~
        if (Dump.Y) Dump.println("DrawnDlgLast.parseValueSendData reasonResponse="+Arrays.toString(reasonResponse));//~9423I~
        if (Dump.Y) Dump.println("DrawnDlgLast.parseValueSendData swSuspend="+swSuspend);//~0314I~
    }                                                              //~9310I~
    //**********************************************               //~9310I~
    private void sendConfirmReqToServer()                           //~9310I~//~0331R~
    {                                                              //~9310I~
    	String dialogData=getValueSendData();                      //~9310I~
        String msg;                                                //~9310R~
        if (Dump.Y) Dump.println("DrawnDlgLast.sendConfirmReqToServer");//~9310I~
        if (!ACC.isServer())                               //~9225I~//~9227R~//~9310R~//~9311R~
        {                                                          //~9310I~
        	msg=GCM_ENDGAME_DRAWN+MSG_SEP+currentEswn+MSG_SEPAPP2+ENDGAME_DRAWN_LAST_CONFIRM_REQUEST+dialogData;//~9310R~
        	ACC.sendToServer(GCM_USER_ACTION,msg);                 //~9310I~
        }                                                          //~9310I~
        else                                                       //~9310I~//~9311R~
        {                                                          //~9310I~//~9311R~
            msg=currentEswn+MSG_SEPAPP2+ENDGAME_DRAWN_LAST_CONFIRM_REQUEST+dialogData;//~9310R~//~9311R~
            GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN,PLAYER_YOU,ENDGAME_DRAWN_LAST_CONFIRM_REQUEST,0/*intp3*/,msg);//~9304R~//~9310I~//~9311R~
        }                                                          //~9310I~//~9311R~
    }                                                              //~9310I~
    //*******************************************************      //~9708I~
    //*URadioGroupI                                                //~9708I~
    //*******************************************************      //~9708I~
    @Override                                                      //~9708I~
    public void onChangedURG(int PbtnID,int Pparm)                 //~9708I~
    {                                                              //~9708I~
        if (Dump.Y) Dump.println("DrawnDlgLast.onChangedURG parm="+Pparm+",btnid="+Integer.toHexString(PbtnID));//~9708I~//~9709R~
        if (swInitLayout)                                          //~9708I~
        {                                                          //~9708I~
            return;                                                //~9708I~
        }                                                          //~9708I~
        boolean swChangedNG=false;                                 //~9708I~
        switch (Pparm)                                             //~9708I~
        {                                                          //~9708I~
        case URGP_NEXTGAME:                                        //~9708I~
        	swChangedNG=true;                                      //~9708I~
            break;                                                 //~9708I~
        }                                                          //~9708I~
        if (swChangedNG)                                           //~9708I~
        {                                                          //~9708I~
        	typeNextGame=rgNextGame.getCheckedID();                //~9708I~
    	    enableFixButton(false);	//requester only by enabled chkbox//~9708I~
		    Arrays.fill(respStat,EGDR_NONE);                       //~0308I~
    		showAmmount();                                         //~9708I~
        }                                                          //~9708I~
        CMP.swSent=false;                                          //~0309I~
    }                                                              //~9708I~
    //*******************************************************      //~9309I~
    //*UCheckBoxI                                                  //~9309I~
    //*******************************************************      //~9309I~
    @Override                                                      //~9309I~
    public void onChangedUCB(CompoundButton Pbtn, boolean PisChecked, int Pparm)//~9309I~
    {                                                              //~9309I~
        if (Dump.Y) Dump.println("DrawnDlgLast.onChangedUCB swInitLayout="+swInitLayout+",parm="+Pparm+",isChecked="+PisChecked);//~9309I~//~9311R~
        if (swInitLayout)                                          //~9309I~
        	return;                                                //~9309I~
        boolean swRecalc=false;                                    //~9309I~
        switch(Pparm)                                              //~9309I~
        {                                                          //~9309I~
    	case UCBP_ERROR:	//error eswn chkbox                    //~9309I~
        	swRecalc=true;                                         //~9309I~
        	break;                                                 //~9309I~
    	case UCBP_PENDING:	                                       //~9309R~
        	swRecalc=true;                                         //~9309I~
        	break;                                                 //~9309I~
    	case UCBP_MANGAN:	                                       //~9309R~
        	swRecalc=true;                                         //~9309I~
        	break;                                                 //~9309I~
//        case UCBP_COMPTYPE_ERROR:                                  //~9309I~//~0309R~
//            swRecalc=true;                                         //~9309I~//~0309R~
//            break;                                                 //~9309I~//~0309R~
        case UCBP_SUSPEND:                                         //~0308I~
            swSuspend=PisChecked;                                  //~0308I~
            saveSuspendRequest();                                  //~0308I~
          	break;                                                 //~0308I~
        }                                                          //~9309I~
        if (swRecalc)                                              //~9309I~
        {                                                          //~9708I~
    		getValue();                                            //~9708I~
    		showAmmountLatest();                                   //~9309I~
        	if (Pparm==UCBP_ERROR)                                 //~9709I~
            {                                                      //~9709I~
            	if (!swErr)                                        //~9709R~
					typeNextGame=NEXTGAME_UNKNOWN;	//reset by value of when no err//~9709R~
				setCheckNextGame();                                //~9709R~
            }                                                      //~9709I~
//  	    enableFixButton(false);	//requester only by enabled chkbox//~9708I~//~0308R~
        }                                                          //~9708I~
        enableFixButton(false); //requester only by enabled chkbox //~0308I~
        Arrays.fill(respStat,EGDR_NONE);                           //~0308I~
        CMP.swSent=false;                                          //~0309I~
    }                                                              //~9309I~
    //*******************************************************      //~9311I~
    private boolean chkRespAllOK()                                 //~9311I~
    {                                                              //~9311I~
    	boolean rc=AG.aUAEndGame.chkResponseConfLast() && !AG.aUAEndGame.chkNGConfLast();//~9311I~
        if (Dump.Y) Dump.println("DrawnDlgLast.chkRespAllOK rc="+rc);//~9311I~
        return rc;                                                 //~9311I~
    }                                                              //~9311I~
    //*******************************************************      //~9611I~
    private boolean chkRespAllWithNG()                             //~9611I~
    {                                                              //~9611I~
    	boolean rc=AG.aUAEndGame.chkResponseConfLast() && AG.aUAEndGame.chkNGConfLast();//~9611I~
        if (Dump.Y) Dump.println("DrawnDlgLast.chkRespAllWithNG rc="+rc);//~9611I~
        return rc;                                                 //~9611I~
    }                                                              //~9611I~
    //*******************************************************      //~9311I~
    private void showTotal()                                    //~9311I~//~9318R~
    {                                                              //~9311I~
        if (Dump.Y) Dump.println("DrawnDlgLast.showTotal typeNextGame="+typeNextGame+",totalAmmount="+Arrays.toString(totalAmmount));     //~9311I~//~9318R~//~9424R~
//  	ScoreDlg.showDialog(EGDR_DRAWN_LAST,totalAmmount,typeNextGame);//~9318R~//~9424R~
		int endgametype=EGDR_DRAWN_LAST;                           //~9506I~
        if (swMangan && swDrawnManganAsRon)                       //~9506I~
			endgametype=EGDR_MANGAN_RON;                           //~9506I~
    	ScoreDlg.showDialog(endgametype,totalAmmountWithErr,typeNextGame);//~9424R~//~9506R~
    }                                                              //~9311I~
    //*******************************************************      //~9611I~
    //*query force to GO even NG response received                 //~9611I~
    //*******************************************************      //~9611I~
    protected void alertToForceOK(int Ptitleid,int Pmsgid)         //~9611I~
    {                                                              //~9611I~
        if (Dump.Y) Dump.println("DrawDlgLast.alertToForceOK");     //~9611I~//~9709R~
        swIgnoreAlertResponse=false;                               //~9611I~
        int flag=BUTTON_POSITIVE|BUTTON_NEGATIVE;                  //~9611I~
        Alert.showAlert(Ptitleid,Pmsgid,flag,this);                //~9611I~
    }                                                              //~9611I~
    //*******************************************************      //~9611I~
    @Override   //AlertI                                           //~9611I~
	public int alertButtonAction(int Pbuttonid,int Prc)            //~9611I~
    {                                                              //~9611I~
        if (Dump.Y) Dump.println("DrawnDlgLast.alertButtonAction buttonID="+Pbuttonid+",rc="+Prc);//~9611I~//~9709R~//~9A14R~
        if (!swIgnoreAlertResponse)                                //~9611I~
		    alertActionReceived(Pbuttonid,Prc);                    //~9611I~
        return 0;                                                  //~9611I~
    }                                                              //~9611I~
    //*******************************************************      //~9611I~
    //*Override this                                               //~9611I~
    //*******************************************************      //~9611I~
	protected void alertActionReceived(int Pbuttonid,int Prc)      //~9611I~
    {                                                              //~9611I~
        if (Dump.Y) Dump.println("DrawnDlgLast.alertActionReceived buttonID="+Pbuttonid+",rc="+Prc);//~9611I~//~9709R~//~9A14R~
    	if (Pbuttonid==BUTTON_POSITIVE)                            //~9611I~
        {                                                          //~9611I~
//          btnTotal.setEnabled(true);                          //~9611I~//~9708R~
    		enableFixButton(true);                                 //~9708R~
    		enableNextGame();                                      //~9A14I~
	    	enableTemporally(true);                                //~9A14I~
        }                                                          //~9611I~
    }                                                              //~9611I~
    //*******************************************************      //~9611I~
    protected void alertForNG(int Ptitleid,int Pmsgid)             //~9611I~
    {                                                              //~9611I~
        if (Dump.Y) Dump.println("DrawnDlgLast.alertForNG");          //~9611I~//~9708R~//~9A14R~
        swIgnoreAlertResponse=true;                                //~9611I~
        int flag=BUTTON_POSITIVE;                                  //~9611I~
        Alert.showAlert(Ptitleid,Pmsgid,flag,this/*callBack*/);    //~9611I~
    }                                                              //~9611I~
	//*************************************************************************//~0308I~
    private boolean confirmSuspend()                               //~0308I~
    {                                                              //~0308I~
    	if (Dump.Y) Dump.println("DrawnDlgLast.confirnmSuspend swSuspend="+swSuspend+",swConfirmedSuspend"+swConfirmedSuspend+",swConfirmedSuspendOK="+swConfirmedSuspendOK);//~0308I~
        boolean rc=false;                                          //~0308I~
        if (swSuspend)                                             //~0308I~
        {                                                          //~0308I~
            if (!swConfirmedSuspend || !swConfirmedSuspendOK)      //~0308I~
            {                                                      //~0308I~
                swConfirmedSuspend=true;                           //~0308I~
                SuspendAlert.newInstance(TITLEID,this,typeNextGame);//~0308I~
                rc=true;                                           //~0308I~
            }                                                      //~0308I~
        }                                                          //~0308I~
        else                                                       //~0308I~
            swConfirmedSuspend=false;                              //~0308I~
        return rc;                                                 //~0308I~
    }                                                              //~0308I~
	//*************************************************************************//~0308I~
    @Override //SuspendAlertI                                      //~0308I~
	public void suspendAlertAction(int PbuttonID)                  //~0308I~
    {                                                              //~0308I~
        swConfirmedSuspendOK=(PbuttonID==BUTTON_POSITIVE);         //~0308I~
        if (Dump.Y) Dump.println("DrawnDlgLast.suspendAlertAction PbuttonID="+PbuttonID+",swConfirmedSuspendOK="+swConfirmedSuspendOK);//~0308I~
        if (swConfirmedSuspendOK)                                  //~0308I~
            onClickOK();                                           //~0308I~
    }                                                              //~0308I~
    //*******************************************************      //~9708I~
    private void enableNextGame()                                  //~9708I~
    {                                                              //~9708I~
        if (Dump.Y) Dump.println("DrawnDlgLast.enableNextGame swErr="+swErr+",reason="+reason+",swContinue="+swContinue+",typeNextGame="+typeNextGame);//~9708I~//~9709R~
//  	boolean swEnable=(reason==EGDR_OTHER || swErr);            //~9708I~//~9709R~
    	boolean swEnable=swErr;                                    //~9709I~
//      rgNextGame.setEnabled(RBIDNEXT_NEXTPLAYER,swEnable);       //~9708I~//~9709R~
//        if (!swEnable)                                           //~9709I~
//        {                                                        //~9709I~
//            if (swContinue)                                      //~9709R~
//                rgNextGame.setEnabled(RBIDNEXT_NEXT,false);      //~9709R~
//            else                                                 //~9709R~
//                rgNextGame.setEnabled(RBIDNEXT_CONT,false);      //~9709R~
//        }                                                        //~9709I~
//        else                                                     //~9709I~
//        {                                                        //~9709I~
//            rgNextGame.setEnabled(RBIDNEXT_NEXT,true);           //~9709I~
//            rgNextGame.setEnabled(RBIDNEXT_CONT,true);           //~9709I~
//        }                                                        //~9709I~
        rgNextGame.setEnabled(RBIDNEXT_RESET,swEnable);            //~9708I~
        boolean swFixed=!swRequester || !swEnable;                 //~9708I~
	    rgNextGame.setCheckedID(typeNextGame,swFixed);             //~9708I~//~9709R~
    }                                                              //~9708I~
    //*******************************************************      //~9708I~
    public void enableFixButton(boolean Penable)                   //~9708I~
    {                                                              //~9708I~
        if (Dump.Y) Dump.println("DrawDlgLast.enableFixButton enable="+Penable);//~9708I~//~9709R~
        btnTotal.setEnabled(Penable);                              //~9708I~
    }                                                              //~9708I~
    //******************************************                   //~9928I~
    private int measureWidth(View PView)                           //~9928I~
    {                                                              //~9928I~
        int ww=UView.getMeasuredSize(PView,0/*size*/,View.MeasureSpec.UNSPECIFIED).x;//~9928R~
        if (Dump.Y) Dump.println("DrawnDlgLast.measureWidth ww="+ww+",getWidth="+PView.getWidth());//~9928I~
        return ww;                                                 //~9928I~
    }                                                              //~9928I~
}//class                                                           //~v@@@R~
