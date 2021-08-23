//*CID://+vac7R~:                             update#=  888;       //~vac7R~
//*****************************************************************//~v101I~
//2021/08/18 vac7 (Bug)99 tile did not dispaly taken               //~vac7I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//2020/04/16 va03:alert suspendrequested                           //~va03I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.graphics.Color;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~9305I~
import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.dialog.RuleSetting;                             //~9412R~
import com.btmtest.game.Complete;
import com.btmtest.game.GConst;
import com.btmtest.game.Players;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.game.UserAction;
import com.btmtest.game.gv.GameViewHandler;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.URadioGroup;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.utils.Alert.*;

public class DrawnDlgHW extends DrawnReqDlgHW                     //~9303R~//~9304R~//~9608R~
            implements UCheckBox.UCheckBoxI, Alert.AlertI          //~9611I~
            ,SuspendAlert.SuspendAlertI                            //~0306I~
            ,URadioGroup.URadioGroupI                                          //~9703I~
{                                                                  //~2C29R~
    private static final int LAYOUTID=R.layout.drawndlghw;      //~9220I~//~9302R~//~9303R~//~9304R~
    private static final int LAYOUTID_SMALLFONT=R.layout.drawndlghw_theme;//~vac5I~
    private static final int TITLEID=R.string.Title_DrawnDlgHW;//~9220I~//~9302R~//~9303R~//~9304R~
    private static final int TITLEID_NONDEALER=R.string.Title_DrawnDlgHWReceived;//~9306I~
    private static final String HELPFILE="DrawnDlgHW";                //~9220I~//~9302R~//~9303R~//~9304R~
                                                                   //~9305I~
    private static final int COLOR_RESPNONE=Color.argb(0xff,0xff,0xff,0x00);//yellow//~9305I~
    private static final int COLOR_RESPOK=Color.argb(0xff,0x00,0xff,0x00);  //green//~9305I~
    private static final int COLOR_RESPNG=Color.argb(0xff,0xff,0x66,0x00);//orange//~9305I~
    private static final int COLOR_RESPBOT=Color.argb(0xff,0xc0,0xc0,0xc0);//~9228I~//~9305I~
    private static final int COLOR_YOU=Color.argb(0xff,0x00,0xbf,0xff); //deep sky blue//~9305I~
//	private static final int COLOR_REQUESTER=AG.resource.getColor(R.color.yellow);//~va40R~
//	private static final int COLOR_NON_REQUESTER=AG.resource.getColor(R.color.bg_dialog);//~va40R~
  	private static final int COLOR_REQUESTER=AG.getColor(R.color.yellow);//~va40I~
  	private static final int COLOR_NON_REQUESTER=AG.getColor(R.color.bg_dialog);//~va40I~
                                                                   //~9426I~
	public static final int NEXTGAME_UNKNOWN=-1;                   //~9306I~
    public  static final int[] rbIDsNGTP={R.id.rbContinue,                          //~9305I~//~9705R~//~9706R~//~9708R~
    					 R.id.rbNext,                              //~9305I~
    					 R.id.rbNextPlayer,                        //~9703I~
    					 R.id.rbReset,                             //~9703I~
//  					 R.id.rbNextFieldE,                        //~9305I~//~9604R~
//  					 R.id.rbNextFieldS,                        //~9305I~//~9604R~
//  					 R.id.rbNextFieldW,                        //~9305I~//~9604R~
//  					 R.id.rbNextFieldN,                        //~9305I~//~9604R~
                         };                                        //~9305I~
    public  static final int RBIDNEXT_CONT=0;                      //~9703I~//~9705R~//~9706R~//~9708R~
    public  static final int RBIDNEXT_NEXT=1;                      //~9703I~//~9705R~//~9706R~//~9708R~
    public  static final int RBIDNEXT_NEXTPLAYER=2;                //~9703I~//~9705R~//~9706R~//~9708R~
    public  static final int RBIDNEXT_RESET=3;                     //~9703I~//~9705R~//~9706R~//~9708R~
                                                                   //~9703I~
    private static final int UCBP_ERROR=1;                         //~9611I~
    private static final int UCBP_SUSPEND=10;                      //~0306I~
                                                                   //~9611I~
//    protected int reason;                                        //~9303R~
      private int typeNextGame=NEXTGAME_UNKNOWN;                   //~9703R~
      protected URadioGroup rgNextGame;                           //~9303R~//~9305R~
      private static final int URGP_NEXTGAME=1;	//listner parm     //~9703I~
//    protected int gameField,gameSeq;               //~9212I~//~9213R~//~9219R~//~9303R~
//    protected UAEndGame UAEG;                                    //~9303R~
//    private UButtonRG UBRG;                                      //~9304R~
//    private UserAction UA;//~9303I~                              //~9304R~
//    private int reason;                                          //~9304R~
    protected int currentEswn;                                     //~9305I~//~9307R~//~9319R~
    private int[] respStat;                                      //~9305I~//~9319R~
    private String strErr,strOK,strNG,strSend,strBot,strNoReply,strDealer;                //~9305I~//~9306R~//~9311R~//~9319R~//~9611R~
    private LinearLayout[]  llReachers/*,lls99Tile*/;                  //~9425I~//~vac7R~
    private LinearLayout    llllReachers,llll99Tile;
    private boolean[] swsReach=new boolean[PLAYERS];              //~9425I~//~9522R~
    private boolean[] swsErrLooser=new boolean[PLAYERS];           //~9426I~
    private int[] amtsError=new int[PLAYERS];                      //~9426I~
    private int[] adjustedScoreWithError=new int[PLAYERS];         //~9426I~
    private LinearLayout ll99Reacher;
    private TextView[] tvsResp;                                  //~9305R~//~9319R~
    private TextView[] tvsRespESWN;                              //~9305I~//~9319R~
    private UCheckBox[] cbsErr;                                    //~9426I~
    private boolean swRequester;                                 //~9306I~//~9307R~//~9308R~//~9319R~
    private Button btnNextGame;                                    //~9311I~
    private boolean swContinue;                                    //~9319I~
    private int ctrReach,eswn99Tile;                                //~9425I~
    private Players PLS;                                           //~9425I~
    private Complete CMP;
    private Accounts ACC;                                          //~9704I~
    private int msgSubtype;//~9426I~
    private int parmReason;                                        //~9518I~
    private boolean swIgnoreAlertResponse;                         //~9609I~
    private TextView[] tvsName,tvsEswn,tvsAmmount,tvsDrawnType,tvsTotal;//~9611I~
	private static final int[] llsDrawnResultID=new int[]{R.id.lldrawn_result1,R.id.lldrawn_result2,R.id.lldrawn_result3,R.id.lldrawn_result4};//~9611I~
    private int[] accountEswn;                                     //~9611I~
    private String[] accountNames;                                 //~9611I~
    private boolean	swInitLayout;                                  //~9611I~
    private boolean	swErr;	//some Error chked                     //~9703I~
    private int endgameType;                                       //~9703I~
    private boolean svFixedErr,svFixedNextGame,svFixedReason;                    //~9704R~//~9705R~
    private int eswnRequester;                                     //~9705I~
    private int reasonSent;                                        //~9A14I~
    private boolean swCancelAlert;                                 //~9B13I~
    private boolean sw3rC,sw99C,sw4wC,sw4kC,sw4rC;                 //~9B13I~
    private boolean swHideResponseEswn;                            //~0217I~
    protected LinearLayout llEswnResponse;                         //~0217I~
    private boolean swConfirmedSuspend,swConfirmedSuspendOK;       //~0306I~
	private UCheckBox cbSuspend;                                   //~0306I~
    private boolean swSuspend;                                     //~0306I~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public DrawnDlgHW()                                           //~v@@@R~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("DrawnDlgHW.defaultConstructor"); //~9221R~//~9302R~//~9303R~//~9304R~
        AG.aUAEndGame.dlgConfirmHW=this;                           //~9611I~
        PLS=AG.aPlayers;                                           //~9425I~
        CMP=AG.aComplete;                                          //~9426I~
        ACC=AG.aAccounts;                                          //~9704I~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
//  public static DrawnDlgHW newInstance(int Preason,int PtypeNextGame,int[] PrespStat)             //~9302I~//~9303R~//~9304R~//~9305R~//~9705R~
    public static DrawnDlgHW newInstance(int Preason,int PtypeNextGame,int[] PrespStat,int PeswnRequester)//~9705I~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("DrawnDlgHW.newInstance reason="+Preason+",typeNextGame="+PtypeNextGame+",eswnRequester="+PeswnRequester+",respStat="+Arrays.toString(PrespStat));        //~9226I~//~9302R~//~9303R~//~9304R~//~9305R~//~9518R~//~9608R~//~9705R~
    	DrawnDlgHW dlg=new DrawnDlgHW();                                     //~v@@@I~//~9220R~//~9221R~//~9302R~//~9303R~//~9304R~
//    	UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~9227R~//~vac5R~
      	UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//~vac5I~
    			FLAG_OKBTN|FLAG_CANCELBTN|FLAG_CLOSEBTN|FLAG_HELPBTN|FLAG_RULEBTN,//~v@@@I~//~9220R~//~9305R~//~9708R~
				TITLEID,HELPFILE);         //~v@@@I~               //~9220R~
//      dlg.reason=Preason;                                        //~9425R~//~9426R~
        dlg.parmReason=Preason;                                    //~9518I~
        dlg.reason=getReasonFromEswnReason(Preason);               //~9426I~
        dlg.eswn99Tile=getEswnFromEswnReason(Preason);             //~9426I~
        dlg.typeNextGame=PtypeNextGame;                            //~9305I~
        dlg.respStat=PrespStat;                                    //~9305I~
        dlg.eswnRequester=PeswnRequester;                          //~9705M~
        dlg.swSuspend=false;                                       //~0307I~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
//    //******************************************                   //~9518I~//~9705R~
//    //*by HALFWAY_CONFIRM msg                                      //~9518I~//~9705R~
//    //******************************************                   //~9518I~//~9705R~
//    public static DrawnDlgHW newInstance(int PmsgSubtype,int Preason,int PtypeNextGame,int[] PrespStat)//~9518I~//~9705R~
//    {                                                              //~9518I~//~9705R~
//        DrawnDlgHW dlg=newInstance(Preason,PtypeNextGame,PrespStat);//~9518I~//~9705R~
//        if (Dump.Y) Dump.println("DrawnDlgHW.newInstance msgSubType="+PmsgSubtype+",reason="+Preason+",typeNextGame="+PtypeNextGame+",respStat="+PrespStat.toString());//~9518I~//~9705R~
//        dlg.msgSubtype=PmsgSubtype;                                //~9518I~//~9705R~
//        return dlg;                                                //~9518I~//~9705R~
//    }                                                              //~9518I~//~9705R~
    //******************************************                   //~9426I~
//  public static DrawnDlgHW newInstance(int Preason,int PtypeNextGame,int[] PrespStat,boolean[] PswsError)//~9426I~//~9705R~
//  public static DrawnDlgHW newInstance(int Preason,int PtypeNextGame,int[] PrespStat,boolean[] PswsError,int PeswnRequester)//~9705I~//~0306R~
    public static DrawnDlgHW newInstance(int Preason,int PtypeNextGame,int[] PrespStat,boolean[] PswsError,int PeswnRequester,boolean PswSuspend)//~0306I~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("DrawnDlgHW.newInstance swsError="+Arrays.toString(PswsError));//~9426I~
//  	DrawnDlgHW dlg=newInstance(Preason,PtypeNextGame,PrespStat);//~9426I~//~9705R~
    	DrawnDlgHW dlg=newInstance(Preason,PtypeNextGame,PrespStat,PeswnRequester);//~9705I~
        dlg.swsErrLooser=PswsError;                              //~9426I~
        dlg.swSuspend=PswSuspend;                                  //~0306I~
        return dlg;                                                //~9426I~
    }                                                              //~9426I~
//    //******************************************                   //~9518I~//~9705R~
//    public static DrawnDlgHW newInstance(int PmsgSubtype,int Preason,int PtypeNextGame,int[] PrespStat,boolean[] PswsError)//~9518I~//~9705R~
//    {                                                              //~9518I~//~9705R~
//        if (Dump.Y) Dump.println("DrawnDlgHW.newInstance msgSubtype="+PmsgSubtype+",swsError="+Arrays.toString(PswsError));//~9518I~//~9705R~
//        DrawnDlgHW dlg=newInstance(Preason,PtypeNextGame,PrespStat,PswsError);//~9518I~//~9705R~
//        dlg.msgSubtype=PmsgSubtype;                                //~9518I~//~9705R~
//        return dlg;                                                //~9518I~//~9705R~
//    }                                                              //~9518I~//~9705R~
//    //******************************************                   //~v@@@M~//~9303R~
//    @Override                                                      //~9221I~//~9303R~
//    public void onPause()                                          //~9221I~//~9303R~
//    {                                                              //~9221I~//~9303R~
//        super.onPause();                                         //~9302R~//~9303R~
//        if (Dump.Y) Dump.println("DrawnDld:onPause issue dismiss");//~9221R~//~9302R~//~9303R~//~9304R~
//        dismiss();  //because hard to make Complete.Status.ammount to parcelable//~9221I~//~9302R~//~9303R~
//    }                                                              //~9221I~//~9303R~
//    //******************************************                 //~9303R~
    @Override                                                    //~9303R~//~9305R~
    protected void initLayout(View PView)                            //~v@@@I~//~9303R~//~9305R~
    {                                                              //~v@@@M~//~9303R~//~9305R~
        if (Dump.Y) Dump.println("DrawnDlgHW.initLayout");        //~v@@@R~//~9220R~//~9302R~//~9303R~//~9304R~//~9305R~
        swInitLayout=true;                                         //~9611I~
    	currentEswn=Accounts.getCurrentEswn();                     //~9305I~
//      swRequester=typeNextGame==NEXTGAME_UNKNOWN;                //~9306I~//~0306R~
        swRequester=UAEndGame.isDealer();                          //~0306I~
        super.initLayout(PView);                                   //~9302R~//~9303R~//~9305R~
//      androidDlg=getDialog();                                    //~v@@@I~//~9302R~//~9303R~//~9305R~
//      getRuleSetting();                                                           //~v@@@I~//~9212R~//~9302M~//~9303R~//~9305R~
//      setupValue();                                              //~9212I~//~9219M~//~9302R~//~9303R~//~9305R~
//      setButton();                                               //~9221I~//~9302R~//~9303R~//~9305R~
//      setRadioGroup(PView);                                      //~9302I~//~9303R~//~9305R~
//      setTitle();                                                //~v@@@I~//~9220R~//~9302R~//~9303R~//~9305R~
        setupAmmount(PView);                                       //~9611I~
	    setupErr(PView);                                           //~9426I~
        setupReach(PView);                                         //~9425I~
        setup99Tile(PView);                                        //~9425I~
                                                                   //~9425I~
        showReach();                                               //~9425I~
	    show99Tile();                                             //~9425I~
	    showErr();                                                 //~9426I~
	    showAmmount();                                             //~9611I~
        update();                                                  //~9305M~
        swInitLayout=false;                                        //~9611I~
//        if (swRequester)              //requested at DrawnReqDlgHW                             //~9703I~//~9A20R~
//            stopAutoTakeDiscardFix();                              //~9703R~//~9A20R~
    }                                                              //~v@@@M~//~9303R~//~9305R~
//    //*******************************************************      //~9703I~//~9A20R~
//    private void stopAutoTakeDiscardFix()                          //~9703R~//~9A20R~
//    {                                                              //~9703I~//~9A20R~
//        if (Dump.Y) Dump.println("DrawnDlgHW.stopAutoTakeDiscardFix");//~9703R~//~9A20R~
//        if (Accounts.isServer())                                   //~9703I~//~9A20R~
////          GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),0/*On*/,1/*PswStopFix*/);//TODO bug? 0-->1//~9703R~//~9A12R~//~9A20R~
//            GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),1/*On*/,1/*PswStopFix*/);//~9A20R~
//        else                                                       //~9703I~//~9A20R~
//            AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,PLAYER_YOU,1/*On*/,1/*PswFix*/,0);//~9703R~//~9A20R~
//    }                                                              //~9703I~//~9A20R~
//    //*******************************************************      //~9704I~//~9A24R~
//    private void releaseStopAutoTakeDiscardAll()                       //~9704I~//~9A24R~
//    {                                                              //~9704I~//~9A24R~
//        if (Dump.Y) Dump.println("DrawnReqDlgHW.releaseAutoTakeDiscardFix");//~9704I~//~9A24R~
////        if (Accounts.isServer())                                   //~9704I~//~9A20R~//~9A24R~
////            GameViewHandler.sendMsg(GCM_TIMEOUT_STOPAUTO,Accounts.getCurrentEswn(),0/*Off*/,2/*resetall*/);//~9704R~//~9A20R~//~9A24R~
////        else                                                       //~9704I~//~9A20R~//~9A24R~
////            AG.aUserAction.sendToServer(GCM_TIMEOUT_STOPAUTO,PLAYER_YOU,0/*Off*/,2/*resetall*/,0);//~9704R~//~9A20R~//~9A24R~
//        DrawnReqDlgHW.releaseStopAutoTakeDiscardAll();           //~9A24R~
//    }                                                              //~9704I~//~9A24R~
    //**********************************************               //~9426I~
    private void saveStatus()                                      //~9426I~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("DrawnReqDlgHW.saveStatus swsErrLooser="+Arrays.toString(swsErrLooser)+",swsReach="+Arrays.toString(swsReach));//~9B14I~
		CMP.swsErrLooser=swsErrLooser;                             //~9426I~
		CMP.swsReach=swsReach;                                     //~9522I~
	    saveSuspendRequest();                                      //~0306I~
    }                                                              //~9426I~
    //*******************************************************      //~0306M~
    private void saveSuspendRequest()                              //~0306I~
    {                                                              //~0306M~
	    if (Dump.Y) Dump.println("CompleteDlg.saveSuspendRequest swSuspend="+swSuspend);//~0306M~
		AG.aComplete.swSuspend=swSuspend;                          //~0306M~
		AG.aStatus.setSuspendRequest(swSuspend);                   //~0306M~
    }                                                              //~0306M~
    //******************************************                   //~9212I~//~9303R~//~9319R~//~9413M~
    @Override                                                      //~9319I~//~9413M~
    protected void getRuleSetting()                                      //~9212I~//~9303R~//~9319R~//~9413M~
    {                                                              //~9212I~//~9303R~//~9319R~//~9413M~
        super.getRuleSetting();                                    //~9702I~
//  	swContinue=!RuleSetting.isDrawnHWNext();                //~9319I~//~9413M~//~9425R~//~9B13R~
    	sw3rC=RuleSetting.isDrawnHW3RCont();                       //~9B13I~
    	sw99C=RuleSetting.isDrawnHW99Cont();                       //~9B13I~
    	sw4wC=RuleSetting.isDrawnHW4WCont();                       //~9B13I~
    	sw4kC=RuleSetting.isDrawnHW4KCont();                       //~9B13I~
    	sw4rC=RuleSetting.isDrawnHW4RCont();                       //~9B13I~
    }                                                              //~9212I~//~9303R~//~9319R~//~9413M~
    //******************************************                   //~9413I~
    @Override                                                      //~9413I~
    protected void initLayoutAdditional(View PView)                //~9413I~
    {                                                              //~9413I~
        if (Dump.Y) Dump.println("DrawnDlgHW.initLayoutAdditional");//~9413I~
//  	if (PrefSetting.isNoRelatedRule())                         //~9520I~//~9708R~
//      	((LinearLayout)UView.findViewById(PView,R.id.llRelatedRule)).setVisibility(View.GONE);//~9520I~//~9708R~
//      else                                                       //~9520I~//~9708R~
//      {                                                          //~9520I~//~9708R~
    		RuleSetting.setDrawnHW(PView,true);                        //~9425I~//~9520R~
//      }                                                          //~9520I~//~9708R~
        setupTextViewResp(PView);                                      //~9305M~//~9307R~//~9413M~
        hideResponseEswn(!swRequester);                            //~0217I~
    }                                                              //~9413I~
    //******************************************                   //~9819I~
	@Override                                                      //~9819I~
    protected int getDialogWidth()                                 //~9819I~
    {                                                              //~9819I~
    	int ww=getDialogWidthSmallDevicePortraitFixedRate();       //~9820R~
    	if (Dump.Y) Dump.println("DrawnDlgHW.getDialogWidth swSmallDevice="+AG.swSmallDevice+",ww="+ww);//~9819I~
        return ww;                                                 //~9819I~
    }                                                              //~9819I~
    //******************************************                   //~0217I~
    private void hideResponseEswn(boolean PswHide)                 //~0217I~
    {                                                              //~0217I~
    	if (Dump.Y) Dump.println("DrawnDlgHW.hideResponseEswn swHide="+PswHide);//~0217I~
    	swHideResponseEswn=PswHide;                                //~0217I~
        if (PswHide)                                               //~0217I~
    	    llEswnResponse.setVisibility(View.GONE);               //~0217I~
    }                                                              //~0217I~
    //******************************************                   //~9611I~
    protected void setupAmmount(View PView)                        //~9611I~
    {                                                              //~9611I~
    	tvsEswn=new TextView[PLAYERS];                             //~9611I~
//      tvsSpritPosID=new TextView[PLAYERS];                       //~9611R~
        tvsAmmount=new TextView[PLAYERS];                          //~9611I~
        tvsTotal=new TextView[PLAYERS];                            //~9611I~
        tvsName=new TextView[PLAYERS];                             //~9611I~
        tvsDrawnType=new TextView[PLAYERS];                        //~9611I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9611I~
        {                                                          //~9611I~
			LinearLayout   ll=(LinearLayout)UView.findViewById(PView,llsDrawnResultID[ii]);//~9611I~
			tvsEswn[ii]      =(TextView)    UView.findViewById(ll,R.id.nameESWN);//~9611I~
//  		tvsSpritPosID[ii]=(TextView)    UView.findViewById(ll,R.id.tvSpritPosID);//~9611R~
			tvsDrawnType[ii] =(TextView)    UView.findViewById(ll,R.id.tvDrawnType);//~9611I~
			tvsAmmount[ii]   =(TextView)    UView.findViewById(ll,R.id.ammountESWN);//~9611I~
			tvsTotal[ii]     =(TextView)    UView.findViewById(ll,R.id.totalESWN);//~9611I~
			tvsName[ii]      =(TextView)    UView.findViewById(ll,R.id.memberName);//~9611I~
        }                                                          //~9611I~
        setAccountName();                                          //~9611I~
    }                                                              //~9611I~
    //******************************************                   //~9611I~
    protected void setAccountName()                                //~9611I~
    {                                                              //~9611I~
    	if (Dump.Y) Dump.println("DrawnDlgHW.setAccountName");    //~9611I~//~9705R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9611I~
        {                                                          //~9611I~
        	tvsName[ii].setText(accountNames[ii]);	//by Position  //~9611I~
	        tvsEswn[ii].setText(GConst.nameESWN[accountEswn[ii]]); //~9611I~
        }                                                          //~9611I~
    }                                                              //~9611I~
    //******************************************                   //~9705I~
    private void setMarkEswnRequester()                            //~9705I~
    {                                                              //~9705I~
    	if (Dump.Y) Dump.println("DrawnDlgHW.setMarkEswnRequester eswnRequester="+eswnRequester);//~9705R~//~9B14R~
        int pos=ACC.currentEswnToPosition(eswnRequester);          //~9705R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9705I~
        {                                                          //~9705I~
//	        tvsEswn[ii].setTextColor(ii==pos ? COLOR_REQUESTER : COLOR_NORMAL);//~9705R~
  	        tvsEswn[ii].setBackgroundColor(ii==pos ? COLOR_REQUESTER : COLOR_NON_REQUESTER);//~9705R~
        }                                                          //~9705I~
    }                                                              //~9705I~
    //*******************************************************      //~9426I~
    private void setupErr(View PView)                              //~9426I~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("DrawnDlgHW.setupErr");           //~9426I~
        LinearLayout ll=(LinearLayout) UView.findViewById(PView,R.id.llError);//~9426I~
        if (swRequester)                                           //~9426I~
        {                                                          //~9426I~
            chkContinuedErr();                                     //~9B14I~
    	    cbsErr=setupEswnCB(ll,UCBP_ERROR);                     //~9426R~//~9611R~
//  	    cbsErr=setupEswnCB(ll,0);                              //~9426I~//~9611R~
        }                                                          //~9426I~
        else                                                       //~9426I~
        {                                                          //~9426I~
    	    cbsErr=setupEswnCB(ll,swsErrLooser);                   //~9426I~
        }                                                          //~9426I~
    }                                                              //~9426I~
    //*****************************************************************//~9B14I~
    //*get errlooser by continue to nextplayer by chombo           //~9B14I~
    //*****************************************************************//~9B14I~
    private void chkContinuedErr()                                 //~9B14I~
    {                                                              //~9B14I~
    	if (Dump.Y) Dump.println("DrawnDlgHW.chkContinuedErr");    //~9B14I~
        Complete.Status[] sorted=CMP.getSortedStatusS();           //~9B14I~
        if (sorted==null)                                          //~9B14I~
        {                                                          //~9B14I~
	    	if (Dump.Y) Dump.println("DrawnDlgHW.chkContinuedErr no completion");//~9B14I~
        	return;                                                //~9B14I~
        }                                                          //~9B14I~
	    for (Complete.Status stat:sorted)                          //~9B14I~
        {                                                          //~9B14I~
    		if (Dump.Y) Dump.println("DrawnDlgHW.chkContinuedErr swErr="+stat.swErr+",compeswn="+stat.completeEswn);//~9B14I~
            if (stat.swErr)                                        //~9B14I~
            {                                                      //~9B14I~
		        swsErrLooser[stat.completeEswn]=true;              //~9B14I~
            }                                                      //~9B14I~
        }                                                          //~9B14I~
    	if (Dump.Y) Dump.println("DrawnDlgHW.chkContinuedErr errLooser="+Arrays.toString(swsErrLooser));//~9B14I~
    }                                                              //~9B14I~
    //*******************************************************      //~9426I~
    private UCheckBox[] setupEswnCB(View PView,int Pid)            //~9426I~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("DrawnDlgHW.setupEswnCB");        //~9426I~
        UCheckBox cbE=new UCheckBox(PView,R.id.cbAbPE);            //~9426I~
        UCheckBox cbS=new UCheckBox(PView,R.id.cbAbPS);            //~9426I~
        UCheckBox cbW=new UCheckBox(PView,R.id.cbAbPW);            //~9426I~
        UCheckBox cbN=new UCheckBox(PView,R.id.cbAbPN);            //~9426I~
        UCheckBox[] cbs=new UCheckBox[]{cbE,cbS,cbW,cbN};          //~9426I~
        if (Pid>0)	//listener set req                             //~9426I~//~9611R~
    	    setCBListener(cbs,Pid);                                //~9426I~//~9611R~
        return cbs;                                                //~9426I~
    }                                                              //~9426I~
    //*******************************************************      //~9611I~
    private void setCBListener(UCheckBox[] Pcbs,int Pid)           //~9611I~
    {                                                              //~9611I~
        if (Dump.Y) Dump.println("DrawnDlgHW.setCBListener id="+Pid);//~9611I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9611I~
            Pcbs[ii].setListener(this,Pid);                        //~9611I~
    }                                                              //~9611I~
    //*******************************************************      //~9703I~
    //*URadioGroupI                                                //~9703I~
    //*******************************************************      //~9703I~
    @Override                                                      //~9703I~
    public void onChangedURG(int PbtnID,int Pparm)                 //~9703I~
    {                                                              //~9703I~
        if (Dump.Y) Dump.println("DrawnDlgHW.onChangedURG parm="+Pparm+",btnid="+Integer.toHexString(PbtnID));//~9220I~//~9703I~
        if (swInitLayout)                                          //~9220I~//~9703I~
        {                                                          //~9220I~//~9703I~
            return;                                                //~9220I~//~9703I~
        }                                                          //~9220I~//~9703I~
        boolean swChangedNG=false;                                         //~9703I~
        switch (Pparm)                                             //~9220I~//~9703I~
        {                                                          //~9220I~//~9703I~
        case URGP_NEXTGAME:                                        //~9703I~
        	swChangedNG=true;                                      //~9703I~
            break;                                                 //~9220I~//~9703I~
        }                                                          //~9220I~//~9703I~
        if (swChangedNG)                                           //~9703I~
        {                                                          //~9703I~
        	typeNextGame=rgNextGame.getCheckedID();                //~9703I~
    	    enableFixButton(false);	//requester only by enabled chkbox//~9703I~
		    Arrays.fill(respStat,EGDR_NONE);                       //~0308I~
    		showAmmount();                                         //~9704I~
        }                                                          //~9703I~
        CMP.swSent=false;                                          //~0314I~
    }                                                              //~9703I~
    //*******************************************************      //~9611I~
    //*UCheckBoxI                                                  //~9611I~
    //*******************************************************      //~9611I~
    @Override                                                      //~9611I~
    public void onChangedUCB(CompoundButton Pbtn, boolean PisChecked, int Pparm)//~9611I~
    {                                                              //~9611I~
        if (Dump.Y) Dump.println("DrawnDlgLast.onChangedUCB swInitLayout="+swInitLayout+",parm="+Pparm+",isChecked="+PisChecked);//~9611I~
        if (swInitLayout)                                          //~9611I~
        	return;                                                //~9611I~
        boolean swRecalc=false;                                    //~9611I~
        switch(Pparm)                                              //~9611I~
        {                                                          //~9611I~
    	case UCBP_ERROR:	//error eswn chkbox                    //~9611I~
        	swRecalc=true;                                         //~9611I~
        	break;                                                 //~9611I~
        case UCBP_SUSPEND:                                         //~0306I~
            swSuspend=PisChecked;                                  //~0306I~
            saveSuspendRequest();                                  //~0306I~
          	break;                                                 //~0306I~
        }                                                          //~9611I~
        if (swRecalc)                                              //~9611I~
        {                                                          //~9611I~
        	boolean oldErr=swErr;                                  //~0217I~
    		getValue();                                            //~9611I~
    		showAmmount();                                         //~9611I~
        	if (Dump.Y) Dump.println("DrawnDlgLast.onChangedUCB reason="+reason+",oldErr="+oldErr+",swErr="+swErr);//~0217I~
    		if (reason!=EGDR_OTHER)                                //~0217I~
	            if (oldErr && !swErr)	//all err reset            //~0217I~
	    			setCheckNextGame();  //apply default cont/next //~0217I~
		    enableNextGame();                                      //~9703I~
        }                                                          //~9611I~
        enableFixButton(false); //requester only by enabled chkbox//~9703R~//~0308I~
        Arrays.fill(respStat,EGDR_NONE);                           //~0308I~
        CMP.swSent=false;                                          //~0314I~
    }                                                              //~9611I~
    //*******************************************************      //~9426I~
    private UCheckBox[] setupEswnCB(View PView,boolean[] Psws)     //~9426I~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("DrawnDlgHW.setupEswnCB");        //~9426I~
        UCheckBox cbE=new UCheckBox(PView,R.id.cbAbPE);            //~9426I~
        UCheckBox cbS=new UCheckBox(PView,R.id.cbAbPS);            //~9426I~
        UCheckBox cbW=new UCheckBox(PView,R.id.cbAbPW);            //~9426I~
        UCheckBox cbN=new UCheckBox(PView,R.id.cbAbPN);            //~9426I~
        UCheckBox[] cbs=new UCheckBox[]{cbE,cbS,cbW,cbN};          //~9426I~
		setCBFix(cbs,Psws);                                        //~9426I~
        return cbs;                                                //~9426I~
    }                                                              //~9426I~
    //*******************************************************      //~9426I~
    private void setCBFix(UCheckBox[] Pcbs,boolean[] Psws)         //~9426I~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("DrawnDlgHW.setCBFix Psws="+Arrays.toString(Psws));//~9426I~//~0306R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9426I~
            Pcbs[ii].setState(Psws[ii],true/*swFixed*/);             //~9426I~//~9704R~
    }                                                              //~9426I~
    //******************************************                   //~9425I~
    private void setupReach(View PView)                            //~9425I~
    {                                                              //~9425I~
    	if (Dump.Y) Dump.println("DrawDlgHW.setupReach");          //~9425I~
        LinearLayout ll=        (LinearLayout)UView.findViewById(PView,R.id.llReachers);//~9425I~
        llReachers=setupTiles(ll);                                 //~9425I~
//      llllReachers     =(LinearLayout)UView.findViewById(PView,R.id.llReachers);//~9425R~
        llllReachers     =ll;                                      //~9425I~
    }                                                              //~9425I~
    //******************************************                   //~9425I~
    private void setup99Tile(View PView)                           //~9425I~
    {                                                              //~9425I~
        if (Dump.Y) Dump.println("DrawDlgHW.setup99Tile reason="+reason);//~9425R~
        llll99Tile=(LinearLayout)UView.findViewById(PView,R.id.ll99Tile);//~9425I~
//      if (reason==EGDR_99TILE)                                   //~9425I~//~9518R~
		boolean sw99=getReason99();                                //~9518R~
        if (sw99)                                                  //~9518R~
        {                                                          //~9425I~
        	llll99Tile.setVisibility(View.VISIBLE);                 //~9425R~
	        ll99Reacher=(LinearLayout)UView.findViewById(PView,R.id.ll99Reacher);//~9425I~
        }                                                          //~9425I~
    }                                                              //~9425I~
    //******************************************                   //~9518I~
    private boolean  getReason99()                                 //~9518I~
    {                                                              //~9518I~
		boolean rc=false;                                          //~9518I~
        if (Dump.Y) Dump.println("DrawDlgHW.getReason99 reason="+reason+",respStat="+Arrays.toString(respStat));//~9518I~
//      if (true)	//TODO test                                    //~9518R~
		rc=reason==EGDR_99TILE;                                    //~9518R~
//        if (msgSubtype==ENDGAME_DRAWN_HALFWAY_CONFIRM            //~9518R~
//        ||  msgSubtype==ENDGAME_DRAWN_HALFWAY_DELAYED            //~9518R~
//        ||  msgSubtype==ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE   //~9518R~
//        ||  msgSubtype==ENDGAME_DRAWN_HALFWAY_RESPONSE_DELAYED   //~9518R~
//        )                                                        //~9518R~
//            rc=reason==EGDR_99TILE;                              //~9518R~
//        else                                                     //~9518R~
//        {                                                        //~9518R~
//             for (int ii=0;ii<PLAYERS;ii++)                      //~9518R~
//                if (respStat[ii]==EGDR_99TILE)                   //~9518R~
//                {                                                //~9518R~
//                    rc=true;                                     //~9518R~
//                    eswn99Tile=ii;                               //~9518R~
//                    break;                                       //~9518R~
//                }                                                //~9518R~
//        }                                                        //~9518R~
        if (Dump.Y) Dump.println("DrawDlgHW.getReason99 rc="+rc+",reason="+reason+",respStat="+Arrays.toString(respStat));//~9518I~
        return rc;                                                 //~9518I~
    }                                                              //~9518I~
    //******************************************                   //~9425I~
    private LinearLayout[] setupTiles(View PView)                  //~9425I~
    {                                                              //~9425I~
        if (Dump.Y) Dump.println("DrawDlgHW.setupTiles");          //~9425I~
        LinearLayout llReacher1       =(LinearLayout)UView.findViewById(PView,R.id.reacher1);//~9425I~
        LinearLayout llReacher2       =(LinearLayout)UView.findViewById(PView,R.id.reacher2);//~9425I~
        LinearLayout llReacher3       =(LinearLayout)UView.findViewById(PView,R.id.reacher3);//~9425I~
        LinearLayout llReacher4       =(LinearLayout)UView.findViewById(PView,R.id.reacher4);//~9425I~
        return new LinearLayout[]{llReacher1,llReacher2,llReacher3,llReacher4};//~9425I~
    }                                                              //~9425I~
    //**********************************************               //~9426I~
    private void getValue()                                        //~9426I~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("DrawnDlgHW.getValue");           //~9426I~
        getValueErr();                                             //~9426I~
    }                                                              //~9426I~
    //**********************************************               //~9426I~
    private void getValueErr()                                     //~9426I~
    {                                                              //~9426I~
    	swErr=false;                                               //~9703I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9426I~
        {                                                          //~9426I~
            swsErrLooser[ii]=cbsErr[ii].getState();                //~9426I~
            if (swsErrLooser[ii])                                  //~9703I~
            	swErr=true;                                        //~9703I~
        }                                                          //~9426I~
        if (Dump.Y) Dump.println("DrawnDlgHW.getValueErr swErr="+swErr+",swsErrLooser="+Arrays.toString(swsErrLooser));//~9426I~//~9703R~
    }                                                              //~9426I~
    //******************************************                   //~9611I~
    private void showAmmount()                                     //~9611I~
    {                                                              //~9611I~
        setAmmountErr();                                           //~9611I~
        if (Dump.Y) Dump.println("DrawnDlgHW.showAmmount amtsErr="+Arrays.toString(amtsError));//~9611R~
        if (Dump.Y) Dump.println("DrawnDlgHW.showAmmount score="+Arrays.toString(ACC.score));//~9611R~
        for (int ii=0;ii<PLAYERS;ii++)     //account sequence      //~9611I~
        {                                                          //~9611I~
	        int eswn=accountEswn[ii];                              //~9611I~
            int amt=amtsError[eswn];                               //~9611R~
            tvsAmmount[ii].setText(Integer.toString(amt));         //~9611I~
            tvsDrawnType[ii].setText(swsErrLooser[eswn]?strErr:" ");//~9611I~
            int total=ACC.score[ii]+amt;                           //~9611I~
            tvsTotal[ii].setText(Integer.toString(total));         //~9611I~
        }                                                          //~9611I~
    }                                                              //~9611I~
    //******************************************                   //~9426I~
    protected void showErr()                                       //~9426I~
    {                                                              //~9426I~
    	if (Dump.Y) Dump.println("DrawnDlgHW.showErr swsErrLooser="+Arrays.toString(swsErrLooser));//~9426I~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9426I~
        {                                                          //~9426I~
            cbsErr[eswn].setState(swsErrLooser[eswn]);             //~9426I~
        }                                                          //~9426I~
    }                                                              //~9426I~
    //******************************************                   //~9611I~
    private void setAmmountErr()                                   //~9611I~
    {                                                              //~9611I~
        if (typeNextGame==RBIDNEXT_NEXTPLAYER)                     //~9704R~
		  	Arrays.fill(amtsError,0);                              //~9704I~
        else                                                       //~9704I~
	  		CMP.setAmtError(swsErrLooser,amtsError);                   //~9611I~//~9704R~
    	if (Dump.Y) Dump.println("DrawnDlgHW.setAmmountErr typeNextGame="+typeNextGame+",swsErrLooser="+Arrays.toString(swsErrLooser)+",amtsError="+Arrays.toString(amtsError));//~9704I~
    }                                                              //~9611I~
    //******************************************                   //~9425I~
    //*to chk 4reach drawn                                         //~9709I~
    //******************************************                   //~9709I~
    protected void showReach()                                     //~9425I~
    {                                                              //~9425I~
    	if (Dump.Y) Dump.println("DrawnDlgHW.showReach");          //~9425I~
        int reachctr=0;                                            //~9425I~
        int[] byEswn=new int[PLAYERS];                             //~9425I~
        Arrays.fill(byEswn,-1);                                    //~9425I~
        for (int player=0;player<PLAYERS;player++)                 //~9425I~
        {                                                          //~9425I~
        	if (PLS.getPosReach(player)<0)                         //~9425I~
            	continue;                                          //~9425I~
	        int eswn=Accounts.playerToEswn(player);                //~9425I~
            reachctr++;                                            //~9425I~
            byEswn[eswn]=player;                                   //~9425I~
        }                                                          //~9425I~
        if (reachctr==0)                                           //~9425I~
        	llllReachers.setVisibility(View.GONE);                 //~9425I~
        else                                                       //~9425I~
        {                                                          //~9425I~
        	reachctr=0;                                            //~9425I~
            for (int eswn=0;eswn<PLAYERS;eswn++)                   //~9425I~
            {                                                      //~9425I~
                int player=byEswn[eswn];                           //~9425I~
				swsReach[eswn]=player>=0;                         //~9425I~//~9522R~
                if (player<0)                                      //~9425I~
                    continue;                                      //~9425I~
                LinearLayout ll=llReachers[reachctr++];            //~9425I~
                new CompDlgReacher(this,ll,eswn);                  //~9425I~
            }                                                      //~9425I~
        }                                                          //~9425I~
        ctrReach=reachctr;                                         //~9425I~
        if (Dump.Y) Dump.println("DrawnDlgHW.showReach ctr="+ctrReach);//~9425I~
    }                                                              //~9425I~
    //******************************************                   //~9425I~
    private void show99Tile()                                      //~9425I~
    {                                                              //~9425I~
        if (Dump.Y) Dump.println("DrawnDlgHW.show99Tile reason="+reason+",respStat="+Arrays.toString(respStat));//~9425R~//~9518R~//~9609R~
//      if (reason!=EGDR_99TILE)                                   //~9425R~//~9518R~
//        	return;                                                //~9425I~//~9518R~
//      new CompDlgReacher(this,ll99Reacher,eswn99Tile,true/*swPending*/);//~9518I~
		boolean sw99=getReason99();                                //~9518I~
        if (Dump.Y) Dump.println("DrawnDlgHW.show99Tile eswn99="+eswn99Tile+",sw99="+sw99);//~9518R~
        if (sw99)                                                  //~9518I~
//      	new CompDlgReacher(this,ll99Reacher,eswn99Tile,true/*swPending*/);//~9425R~//~9518R~//~vac7R~
        	show99Tile(ll99Reacher,eswn99Tile);                   //~vac7I~
    }                                                              //~9425I~
    //******************************************                   //~vac7I~
    private void show99Tile(View Pview,int Peswn)                  //~vac7I~
    {                                                              //~vac7I~
        if (Dump.Y) Dump.println("DrawnDlgHW.show99Tile eswn="+Peswn+",parent="+Pview.toString());//~vac7I~
        TextView tvReacherEswn         =(TextView)    UView.findViewById(Pview,R.id.tvReacherEswn);//~vac7I~
        tvReacherEswn.setText(GConst.nameESWN[Peswn]);             //~vac7I~
      	CompDlgTiles.setImageLayoutHandAll(ll99Reacher,Peswn);     //+vac7R~
    }                                                              //~vac7I~
    //******************************************                   //~9305I~
    protected void setupTextViewResp(View PView)                                  //~9305I~//~9307R~
    {                                                              //~9305I~
        llEswnResponse=(LinearLayout)UView.findViewById(layoutView,R.id.eswnResponseLine);//~0217I~
      	TextView tvResp1,tvResp2,tvResp3,tvResp4;                  //~9305I~
      	TextView tvRespE,tvRespS,tvRespW,tvRespN;                  //~9305I~
        if (Dump.Y) Dump.println("DrawnDlgHW.setupTextView");      //~9305I~
        tvResp1         =(TextView)    UView.findViewById(PView,R.id.ReplyE);//~9305I~
        tvResp2         =(TextView)    UView.findViewById(PView,R.id.ReplyS);//~9305R~
        tvResp3         =(TextView)    UView.findViewById(PView,R.id.ReplyW);//~9305I~
        tvResp4         =(TextView)    UView.findViewById(PView,R.id.ReplyN);//~9305I~
        tvRespE         =(TextView)    UView.findViewById(PView,R.id.LabelReplyE);//~9305I~
        tvRespS         =(TextView)    UView.findViewById(PView,R.id.LabelReplyS);//~9305I~
        tvRespW         =(TextView)    UView.findViewById(PView,R.id.LabelReplyW);//~9305I~
        tvRespN         =(TextView)    UView.findViewById(PView,R.id.LabelReplyN);//~9305I~
        tvsResp=new TextView[]{tvResp1,tvResp2,tvResp3,tvResp4};   //~9305I~
        tvsRespESWN=new TextView[]{tvRespE,tvRespS,tvRespW,tvRespN};//~9305I~
    }                                                              //~9305I~
    //******************************************                   //~9302I~//~9303R~//~9304R~
    @Override                                                      //~9305I~
    protected void setupValue()                                      //~9302I~//~9303R~//~9304R~
    {                                                              //~9302I~//~9303R~//~9304R~
        if (Dump.Y) Dump.println("DrawnDlgHW.setupValue reason="+reason);//~9304I~
        super.setupValue();                                        //~9304I~
        strBot=Utils.getStr(R.string.YournameRobot);               //~9305I~
        strOK=Utils.getStr(R.string.OK);                           //~9305I~
        strNG=Utils.getStr(R.string.NG);                           //~9305I~
        strDealer=Utils.getStr(R.string.Label_DealerNow);          //~9311I~
        strSend=Utils.getStr(R.string.Send);                       //~9306I~
        strErr=Utils.getStr(R.string.AbnormalGain);                //~9611I~
        strNoReply=Utils.getStr(R.string.NoReply);                //~9305I~
    	accountEswn=ACC.getCurrentEswnByPosition(); //position to eswn//~9611I~
    	accountNames=ACC.getAccountNamesByPosition();              //~9611I~
    }                                                              //~9302I~//~9303R~//~9304R~
    //******************************************                   //~v@@@I~//~9220R~//~9303R~//~9306R~
    @Override                                                      //~9306I~
    protected void setTitle()                                        //~v@@@I~//~9220R~//~9303R~//~9306R~
    {                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
//      String s=Utils.getStr(swRequester ? TITLEID :TITLEID_NONDEALER )+":"+GConst.nameESWN[gameField]+GConst.gameSeq[gameSeq];    //~v@@@I~//~9212R~//~9220R~//~9302R~//~9303R~//~9306R~
        Spanned s=Status.getSpannedGameTitle(Utils.getStr(swRequester ? TITLEID :TITLEID_NONDEALER));//~9306I~
        androidDlg.setTitle(s);                                    //~v@@@R~//~9220R~//~9302R~//~9303R~//~9306R~
    }                                                              //~v@@@I~//~9220R~//~9303R~//~9306R~
    //*******************************************************      //~v@@@I~//~9303R~//~9304R~
    @Override                                                      //~v@@@I~//~9303R~//~9304R~
    public void onDismissDialog()                                  //~v@@@I~//~9303R~//~9304R~
    {                                                              //~v@@@I~//~9303R~//~9304R~
        if (Dump.Y) Dump.println("onDismissDialog");               //~v@@@I~//~9303R~//~9304R~
        UAEG.dlgConfirmHW=null;                                      //~9303R~//~9304R~
    }                                                              //~v@@@I~//~9303R~//~9304R~
    //*******************************************************      //~9302I~//~9303R~//~9306R~
    @Override                                                      //~9306I~
    public void setButton()                                        //~9221I~//~9303R~//~9306R~
    {                                                              //~9221I~//~9303R~//~9306R~
        if (Dump.Y) Dump.println("DrawDlgHW.setButton swTrainingMode="+AG.swTrainingMode);//~va66I~
        btnNextGame = UButton.bind(layoutView,R.id.ShowTotal,this); //~9311I~//~9609M~
        if (swRequester)                                           //~9306I~
        {                                                          //~9306I~
          if (!AG.swTrainingMode)                                  //~va66I~
	        btnOK.setText(strSend);                                 //~9306R~
	        btnCancel.setVisibility(View.GONE);                    //~9306I~
        	btnNextGame.setVisibility(View.VISIBLE);               //~9609I~
        }                                                          //~9306I~
        else                                                       //~9306I~
        {                                                          //~9306I~
	        btnOK.setText(strOK);                                   //~9306I~
        }                                                          //~9306I~
    }                                                              //~9221I~//~9303R~//~9306R~
    //*******************************************************      //~9703I~
    public void enableFixButton(boolean Penable)                   //~9703I~
    {                                                              //~9703I~
        if (Dump.Y) Dump.println("DrawDlgHW.enableFixButton enable="+Penable);//~9703I~
        btnNextGame.setEnabled(Penable);                           //~9703R~
    }                                                              //~9703I~
    //*******************************************************      //~9302I~//~9303R~
    @Override                                                      //~9303I~
    protected void setRadioGroup(View PView)                          //~9302I~//~9303R~//~9307R~//~9308R~
    {                                                              //~9302I~//~9303R~
        if (Dump.Y) Dump.println("DrawDlgHW.setRadioGroup");                 //~9303R~//~9304R~
        super.setRadioGroup(PView);                                     //~9304I~//~9307R~//~9308R~
//      UBRG.setChecked(reason,!swRequester/*swFixed*/);                                   //~9304R~//~9306R~//~9426R~
        UBRG.setChecked(reason,true/*swFixed*/);                   //~9426I~
        rgNextGame= new URadioGroup(PView,R.id.rgNextGame,URGP_NEXTGAME/*parm*/,rbIDsNGTP);//~9306R~//~9703R~//~9705R~
        rgNextGame.setListener(this);                              //~9703I~
	    setCheckNextGame();                                        //~9305I~
		cbSuspend=new UCheckBox(PView,R.id.cbSuspend);             //~0306I~
		cbSuspend.setState(swSuspend,!swRequester);                //~0306I~
        if (swSuspend && !swRequester)                             //~va03R~
        	CompleteDlg.alertSuspended();                          //~va03R~
        cbSuspend.setListener(this,UCBP_SUSPEND);                  //~0306I~
    }                                                              //~9305I~
    //*******************************************************      //~9A14I~
    private void fixRGNextGame(boolean PswFix)                     //~9A14I~
    {                                                              //~9A14I~
        if (Dump.Y) Dump.println("DrawnDlgHW.fixRGNextGame swFix="+PswFix);//~9A14I~
        rgNextGame.setFixed(PswFix);                               //~9A14I~
	}                                                              //~9A14I~
    //*******************************************************      //~9703I~
    private void enableNextGame()                                  //~9703I~
    {                                                              //~9703I~
        if (Dump.Y) Dump.println("DrawnDlgHW.enableNextGame swErr="+swErr+",reason="+reason+",typeNextgame="+typeNextGame);//~9703I~//~9B13R~
        int rsn=reason;                                            //~9A14I~
        if (rsn==EGDR_OK||rsn==EGDR_NG)	//response reason          //~9A14I~
        	rsn=reasonSent;                                        //~9A14I~
//  	boolean swEnable=(reason==EGDR_OTHER || swErr);            //~9703I~//~9A14R~
    	boolean swEnable=(rsn==EGDR_OTHER || swErr);               //~9A14I~
        rgNextGame.setEnabled(RBIDNEXT_NEXTPLAYER,swEnable);       //~9703I~
        rgNextGame.setEnabled(RBIDNEXT_RESET,swEnable);            //~9703I~
        boolean swFixed=!swRequester || !swEnable;                 //~9703R~
        rgNextGame.setCheckedID(typeNextGame,swFixed);             //~9703I~
    }                                                              //~9703I~
    //*******************************************************      //~9305I~
    private void setCheckNextGame()                                     //~9305I~
    {                                                              //~9305I~
        if (Dump.Y) Dump.println("DrawnDlgHW.setCheckNextGame swRequester="+swRequester+",swErr="+swErr+",reason="+reason);//~9703I~//~9B13R~
//    	if (typeNextGame==NEXTGAME_UNKNOWN)                        //~9703I~//~9B13R~
//  	    typeNextGame=UAEndGame.getNextGameType(swContinue);	//may changed later when other or err         //~9319M~//~9703R~//~9B13R~
        if (swRequester)                                           //~9B13I~
        {                                                          //~9B13I~
        	int rbid;                                              //~9B13I~
        	if (swErr)                                             //~9B13I~
            {                                                      //~9B14I~
				if (typeNextGame==NEXTGAME_UNKNOWN)                //~9B14I~
			        typeNextGame=NGTP_RESET;	//=RBIDNEXT_RESET=3    //~9B13R~//~9B14R~
            }                                                      //~9B14I~
            else                                                   //~9B13I~
            {                                                      //~9B13I~
                switch(reason)                                     //~9B13I~
                {                                                  //~9B13I~
                case EGDR_4REACH:   //   =4;                       //~9B13I~
                    typeNextGame=sw4rC ? NGTP_CONTINUE : NGTP_NEXT;    //=RBIDNEXT_CONT/RBIDNEXT_NEXT//~9B13R~
                    break;                                         //~9B13I~
                case EGDR_4WIND:    //    =5;                      //~9B13I~
                    typeNextGame=sw4wC ? NGTP_CONTINUE : NGTP_NEXT;//~9B13R~
                    break;                                         //~9B13I~
                case EGDR_4KAN:     //  =6;                        //~9B13I~
                    typeNextGame=sw4kC ? NGTP_CONTINUE : NGTP_NEXT;//~9B13R~
                    break;                                         //~9B13I~
                case EGDR_3RON:     //     =7;                     //~9B13I~
                    typeNextGame=sw3rC ? NGTP_CONTINUE : NGTP_NEXT;//~9B13R~
                    break;                                         //~9B13I~
                case EGDR_99TILE:       //   =8;                   //~9B13I~
                    typeNextGame=sw99C ? NGTP_CONTINUE : NGTP_NEXT;//~9B13R~
                    break;                                         //~9B13I~
                case EGDR_OK:       //   =100;                     //~0306I~
                case EGDR_NG:       //   =99;                      //~0306I~
                    break;                                         //~0306I~
                default:                //10 EGDR_OTHER            //~9B13I~
					if (typeNextGame==NEXTGAME_UNKNOWN)            //~9B14I~
				        typeNextGame=NGTP_RESET;                       //~9B13I~//~9B14R~
                }                                                  //~9B13I~
            }                                                      //~9B13I~
        }                                                          //~9B13I~
        if (Dump.Y) Dump.println("DrawnDlgHW.setCheckNextGame typeNextGame="+typeNextGame);//~9B13I~
	    enableNextGame();                                          //~9703M~
    }                                                              //~9302I~//~9303R~
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
        if (Dump.Y) Dump.println("DrawnDlgHW.onClickOK=Send");                     //~v@@@I~//~9221M~//~9302R~//~9303R~//~9304R~//~9305R~//~9609R~
        if (!getSetting())                                              //~9303R~//~9304R~//~9305R~//~9306R~
        {                                                          //~9306R~
            UView.showToast(R.string.ErrSelectNextGame);           //~9306R~
            return;                                                //~9306R~
        }                                                          //~9306R~
        getValue();                                                //~9426I~
		if (swRequester)                                           //~0306I~
        {                                                          //~0306I~
    		if (confirmSuspend())	//issued Alert                 //~0306I~
            	return;                                            //~0306I~
        }                                                          //~0306I~
        saveStatus();                                              //~9426I~
//        updateResponse(reason);                                    //~9305I~//~9306M~//~9311R~
        if (AG.swTrainingMode)                                     //~va66I~
        {                                                          //~va66I~
    		enableFixButton(true);                                 //~va66R~
            reasonSent=reason;                                     //~va66I~
            CMP.swSent=true;                                       //~va66I~
        }                                                          //~va66I~
        else                                                       //~va66I~
		if (swRequester)                                           //~9311I~
        {                                                          //~9708I~
//  		fixRGNextGame(true/*PswFix*/);                          //~9A14I~//~9B13R~
    		enableFixButton(false);                                //~9708I~
		    Arrays.fill(respStat,EGDR_NONE);                       //~9708I~
            update();                                          //~9708I~
	        sendResponse(reason);                                  //~9311I~
            reasonSent=reason;                                     //~9A14I~
            CMP.swSent=true;                                       //~0314I~
        }                                                          //~9708I~
        else                                                        //~9311I~
	        sendResponse(EGDR_OK);                                            //~9303R~//~9304R~//~9305R~//~9306I~//~9308R~//~9311R~
        if (!swRequester)                                          //~9306I~
			dismiss();                                                 //~9303R~//~9304R~//~9305R~//~9306R~
//      else                                                       //~9308I~//~9311R~
//          btnOK.setEnabled(false);                               //~9308I~//~9311R~
    }                                                              //~9221I~//~9303R~//~9304R~//~9305R~
    //*******************************************************      //~9305I~
    //*Label=NG                                                    //~9305I~
    //*******************************************************      //~9305I~
    @Override                                                      //~9305I~
    public void onClickCancel()                                    //~9305I~
    {                                                              //~9305I~
        if (Dump.Y) Dump.println("onClickOK=Send");                //~9305I~
        reason=EGDR_NG; //no good                                  //~9305I~
//        updateResponse(reason);                                    //~9305I~//~9311R~
        sendResponse(EGDR_NG);                                            //~9305I~//~9308R~//~9311R~
        if (!swRequester)                                          //~9306I~
			dismiss();                                             //~9306I~
    }                                                              //~9305I~
    //*******************************************************      //~9B13I~
    //*Requeser                                                    //~9B13I~
    //*******************************************************      //~9B13I~
    @Override                                                      //~9B13I~
    public void onClickClose()                                     //~9B13I~
    {                                                              //~9B13I~
        if (Dump.Y) Dump.println("onClickClose");                  //~9B13I~
        if (swRequester)                                           //~9B14I~
	    	alertToCancelHW();                                         //~9B13I~//~9B14R~
        else                                                       //~9B14I~
			dismiss();                                             //~9B14I~
    }                                                              //~9B13I~
    //*******************************************************      //~9311I~
    @Override                                                      //~9311I~
    public void onClickOther(int Pbuttonid)                        //~9311I~
	{                                                              //~9311I~
        if (Dump.Y) Dump.println("DrawDlgHW:onClickOther id="+Integer.toHexString(Pbuttonid));//~9311I~
        switch(Pbuttonid)                                          //~9311I~
        {                                                          //~9311I~
            case R.id.ShowTotal:                                    //~9311I~
            	showTotal();                                    //~9311I~//~9318R~
				dismiss();                                         //~9311I~
            	break;                                             //~9311I~
            case R.id.ShowRule:                                    //~9417I~
            	showRule();                                        //~9417I~
            	break;                                             //~9417I~
            default:                                               //~9311I~
                if (Dump.Y) Dump.println("DrawDlgHW:onClickOther unknown");//~9311I~
        }                                                          //~9311I~
    }                                                              //~9311I~
    //******************************************                   //~9220I~//~9303R~//~9304R~//~9305R~
    @Override                                                      //~9305I~
    protected boolean getSetting()                                   //~9220I~//~9302R~//~9303R~//~9304R~//~9305R~//~9306R~
    {                                                              //~9220I~//~9303R~//~9304R~//~9305R~
        reason=UBRG.getChecked();                                  //~9303R~//~9304R~//~9305R~
//      if (reason==EGDR_99TILE)                                   //~9425I~//~9426R~
//      	eswn99Tile=currentEswn;                                //~9425I~//~9426R~
//        int rbid=rgNextGame.getChecked();                          //~9305I~//~9306R~
//        if (rbid==-1)                                            //~9306R~
//            return false;                                        //~9306R~
//        for (int ii=0;ii<rbIDs.length;ii++)                        //~9305I~//~9306R~
//            if (rbid==rbIDs[ii])                                   //~9305I~//~9306R~
//            {                                                      //~9305I~//~9306R~
//                typeNextGame=ii;                                   //~9305R~//~9306R~
//                break;                                             //~9305I~//~9306R~
//            }                                                      //~9305I~//~9306R~
        typeNextGame=rgNextGame.getCheckedID();                    //~9306I~
        boolean rc=typeNextGame!=-1;                               //~9306I~
		swSuspend=cbSuspend.getState();                            //~0306I~
        if (Dump.Y) Dump.println("DrawnDlgHW.getSetting rc="+rc+",reason="+reason+",typeNextGame="+typeNextGame+",swSuspend="+swSuspend);//~9305I~//~9306R~//~0306R~
        return rc;                                                 //~9306R~
    }                                                              //~9220I~//~9303R~//~9304R~//~9305R~
//    //*******************************************************************//~9303R~
//    public  void setReplyText()                                  //~9303R~
//    {                                                            //~9303R~
//        if (Dump.Y) Dump.println("DrawnDlgHW.setReplyText");  //~9303R~//~9304R~
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
//        if (Dump.Y) Dump.println("DrawnDlgHW.sendResponse");    //~9303R~//~9304R~
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
        if (Dump.Y) Dump.println("DrawnDlgHW.sendMsg swRequester="+swRequester+",reason="+Preason+",saveReason="+parmReason);//~9304R~//~9518R~//~9703R~
//      GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN, PLAYER_YOU, ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE,UAEndGame.putReason_typeNextGame(Preason,typeNextGame));//~9304R~//~9305R~//~9426R~
        String data=makeConfRespMsgData();                         //~9426I~
//      GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN, PLAYER_YOU, ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE,Preason,data);//~9426I~//~9518R~
//      GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN, PLAYER_YOU, ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE,parmReason,data);//~9518I~//~9608R~
//      GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN, PLAYER_YOU, ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE,Preason/*OK-NG*/,data);//~9609I~
		int rsn=Preason;                                           //~9609I~
        if (rsn!=EGDR_OK && rsn!=EGDR_NG)                          //~9609I~
        	rsn=parmReason;		//eswn+Reason                      //~9609I~
        if (swRequester)                                           //~9703I~
        	AG.aUAEndGame.resetResponseHW();                       //~9703I~
//      GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN, PLAYER_YOU, ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE,rsn/*OK-NG or eswn+reason*/,data);//~9608I~//~9609R~//~9B14R~
        int msgid=swRequester ? ENDGAME_DRAWN_HALFWAY_CONFIRM_REQUEST : ENDGAME_DRAWN_HALFWAY_CONFIRM_RESPONSE;//~9B14I~
//      GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN, PLAYER_YOU, msgid,rsn/*OK-NG or eswn+reason*/,data);//~9B14I~//~0306R~
        GameViewHandler.sendMsg(GCM_ENDGAME_DRAWN, PLAYER_YOU, msgid,rsn/*OK-NG or eswn+reason*/,(swSuspend?1:0),data);//~0306I~
    }                                                              //~9304R~
    //*******************************************************      //~9426I~
    public String makeConfRespMsgData()                            //~9426I~
    {                                                              //~9426I~
//  	String s=typeNextGame+MSG_SEPAPP2+makeMsgDataBoolean(swsErrLooser);//~9426R~//~9705R~
    	String s=typeNextGame+MSG_SEPAPP2+eswnRequester+MSG_SEPAPP2+makeMsgDataBoolean(swsErrLooser);//~9705I~
        if (Dump.Y) Dump.println("DrawnDlgHW.makeConfRespMsgData s="+s);//~9426I~
    	return s;                                                  //~9426I~
    }                                                              //~9426I~
    //*******************************************************      //~9426I~
    public static String makeMsgDataBoolean(boolean[] PswsBoolean) //~9426R~
    {                                                              //~9426I~
    	String s=(PswsBoolean[0] ? "1" : "0")+MSG_SEPAPP           //~9426I~
                +(PswsBoolean[1] ? "1" : "0")+MSG_SEPAPP           //~9426I~
                +(PswsBoolean[2] ? "1" : "0")+MSG_SEPAPP           //~9426I~
                +(PswsBoolean[3] ? "1" : "0");                     //~9426I~
        if (Dump.Y) Dump.println("DrawnDlgHW.makeMsgDataBoolean s="+s);//~9426I~
    	return s;                                                  //~9426I~
    }                                                              //~9426I~
    //*******************************************************      //~9704I~
    private void saveFixedStatus()                                 //~9704I~
    {                                                              //~9704I~
        if (Dump.Y) Dump.println("DrawnDlgHW.saveFixedStatus swInitiLayout="+swInitLayout);//~9704I~
        svFixedErr=cbsErr[0].getFixed();                           //~9704I~
      	svFixedNextGame=rgNextGame.getFixed();                     //~9704I~
        svFixedReason=UBRG.getFixed();                             //~9705I~
    }                                                              //~9704I~
    //*******************************************************      //~9704I~
    private void resetFixedStatus()                                //~9704I~
    {                                                              //~9704I~
        if (Dump.Y) Dump.println("DrawnDlgHW.resetFixedStatus swInitiLayout="+swInitLayout);//~9704I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9704I~
            cbsErr[ii].setFixed(false);                            //~9704I~
      	rgNextGame.setFixed(false);                //~9704I~
        UBRG.setFixed(false);                                      //~9705I~
    }                                                              //~9704I~
    //*******************************************************      //~9704I~
    private void restoreFixedStatus()                              //~9704I~
    {                                                              //~9704I~
        if (Dump.Y) Dump.println("DrawnDlgHW.restoreFixedStatus swInitiLayout="+swInitLayout);//~9704I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9704I~
            cbsErr[ii].setFixed(svFixedErr);                       //~9704I~
      	rgNextGame.setFixed(svFixedNextGame);                      //~9704I~
        UBRG.setFixed(svFixedReason);                              //~9705I~
    }                                                              //~9704I~
    //*******************************************************      //~9305I~
    private void update()                                           //~9305R~//~9608R~
    {                                                              //~9305I~
        if (Dump.Y) Dump.println("DrawnDlgHW.update respStat="+Arrays.toString(respStat));//~9305I~
        if (!swInitLayout)                                         //~9705I~
        {                                                          //~9705I~
        	saveFixedStatus();                                         //~9704I~//~9705R~
	    	resetFixedStatus();                                        //~9704I~//~9705R~
	    	setCBFix(cbsErr,swsErrLooser);                         //~9704R~
        }                                                          //~9705I~
        if (Dump.Y) Dump.println("DrawnDlgHW.update swHideResponseEswn="+swHideResponseEswn);//~0217I~
        String stat;                                               //~9305I~
        int color;                                                 //~9305I~
        int ctrNG=0;                                               //~9608I~
        int ctrNone=0;                                             //~9608I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9305I~
        {                                                          //~9305I~
        	if (ACC.isDummyByCurrentEswn(ii))             //~9305I~//~9704R~
            {                                                      //~9305I~
            	stat=strBot;                                       //~9305I~
        		color=COLOR_RESPBOT;                               //~9305R~
            }                                                      //~9305I~
            else                                                   //~9305I~
        	if (ii==ACC.getCurrentDealerRealEswn())  //~9311I~     //~9315R~
            {                                                      //~9311I~
                stat=strDealer;                                    //~9311I~
                color=COLOR_RESPBOT;                               //~9311I~
            }                                                      //~9311I~
            else                                                   //~9311I~
            if (respStat[ii]==EGDR_NG) //no good                   //~9305R~
            {                                                      //~9305I~
            	stat=strNG;                                        //~9305I~
            	color=COLOR_RESPNG;                                //~9305I~
                ctrNG++;                                           //~9608I~
            }                                                      //~9305I~
            else                                                   //~9305I~
            if (respStat[ii]!=0)                                   //~9305R~
            {                                                      //~9305I~
            	stat=strOK;                                        //~9305I~
            	color=COLOR_RESPOK;                                //~9305I~
            }                                                      //~9305I~
            else                                                   //~9305I~
            {                                                      //~9305I~
            	stat=strNoReply;                                   //~9305I~
        		color=COLOR_RESPNONE;                              //~9305I~
                ctrNone++;                                         //~9608I~
            }                                                      //~9305I~
	        if (Dump.Y) Dump.println("DrawnDlgHW.update stat="+stat+",resp="+respStat[ii]);//~9305I~
            tvsResp[ii].setText(stat);                             //~9305I~
            tvsResp[ii].setBackgroundColor(color);                 //~9305I~
        }                                                          //~9305I~
        tvsRespESWN[currentEswn].setBackgroundColor(COLOR_YOU);         //~9305I~
	    setCheckNextGame();                                        //~9305I~
		cbSuspend.setState(swSuspend);                             //~0306I~
//      btnNextGame.setVisibility(chkRespAllOK()? View.VISIBLE :View.GONE);//~9311I~//~9609R~
        if (swRequester)                                           //~9609I~
//  		btnNextGame.setEnabled(chkRespAllOK());                //~9609I~//~9703R~
    		enableFixButton(chkRespAllOK());                       //~9703I~
//      btnOK.setVisibility(chkRespAllOK()? View.GONE :View.VISIBLE);//~9417I~//~9609R~
        if (ctrNone==0 && ctrNG>0)                                 //~9608I~
//      	alertForNG(TITLEID,R.string.Alert_DrawnHWNG);          //~9608I~//~9609R~
	        if (swRequester)                                       //~9609I~
    	    	alertToForceOK(TITLEID,R.string.Alert_DrawnHWNGForceOK);//~9609R~
        setMarkEswnRequester();                                    //~9705I~
        if (!swInitLayout)                                         //~9705I~
        {                                                          //~9705I~
        	if (reason!=EGDR_OK && reason!=EGDR_NG)                //~0306I~
		        UBRG.setChecked(reason,true/*swFixed*/);               //~9705I~//~0306R~
        	restoreFixedStatus();                                      //~9704I~//~9705R~
        }                                                          //~9705I~
    }                                                              //~9305I~
//    //*******************************************************      //~9305I~//~9608R~
//    public void updateResponse(int Preason)                        //~9305I~//~9608R~
//    {                                                              //~9305I~//~9608R~
//        if (Dump.Y) Dump.println("DrawnDlgHW.updateResponse reason="+Preason);//~9305I~//~9608R~
//        int eswn=Accounts.getCurrentEswn();                        //~9305I~//~9608R~
////      respStat[eswn]=Preason; //on UAEndGame                     //~9305R~//~9608R~
//        if (Preason==EGDR_NG) //no good                            //~9305I~//~9608R~
//        {                                                          //~9306I~//~9608R~
//            tvsResp[eswn].setText(strNG);                                   //~9305I~//~9608R~
//            tvsResp[eswn].setBackgroundColor(COLOR_RESPNG);        //~9306I~//~9608R~
//        }                                                          //~9306I~//~9608R~
//        else                                                       //~9305I~//~9608R~
//        {                                                          //~9306I~//~9608R~
//            tvsResp[eswn].setText(strOK);                                   //~9305I~//~9608R~
//            tvsResp[eswn].setBackgroundColor(COLOR_RESPOK);        //~9306I~//~9608R~
//        }                                                          //~9306I~//~9608R~
//    }                                                              //~9305I~//~9608R~
    //*******************************************************      //~9305I~
//  public void repaint(int PtypeNextGame,int[] PrespStat)         //~9305R~//~9705R~
//  public void repaint(int PtypeNextGame,int[] PrespStat,int Preason)//~9705I~
    public void repaint(int PtypeNextGame,int[] PrespStat,int Preason,int PeswnRequester)//~9705I~
    {                                                              //~9305I~
        if (Dump.Y) Dump.println("DrawnDlgHW.repaint reason="+Preason+",nextgametype="+PtypeNextGame+",eswnRequester="+PeswnRequester+",respstat="+Arrays.toString(PrespStat));//~9305I~//~9705R~
        typeNextGame=PtypeNextGame;                                //~9305R~
        reason=Preason;                                            //~9705I~
        eswnRequester=PeswnRequester;                              //~9705I~
        respStat=PrespStat;                                        //~9305I~
//        new EventCB(ECB_DRAWNHW_RESP,EventCB.newBundle(0)).postEvent();//~9305R~
        AG.activity.runOnUiThread(                                 //~9305I~
            new Runnable()                                         //~9305I~
            {                                                      //~9305I~
                @Override                                          //~9305I~
                public void run()                                       //~9305I~
                {                                                  //~9305I~
                    try                                            //~9305I~
                    {                                              //~9305I~
    				    if (Dump.Y) Dump.println("DrawnDlgHW.repaint runonUiThread.run");//~9305I~
                    	update();                                  //~9305I~
                    }                                              //~9305I~
                    catch(Exception e)                             //~9305I~
                    {                                              //~9305I~
                        Dump.println(e,"DrawnDlgHW:repaint.run");  //~9305I~
                    }                                              //~9305I~
                }                                                  //~9305I~
            }                                                      //~9305I~
                                  );                               //~9305I~
                                                                   //~9305I~
    }                                                              //~9305I~
//    //*******************************************************      //~9518I~//~9705R~
//    public void repaint(int PmsgSubtype,int PtypeNextGame,int[] PrespStat)//~9518I~//~9705R~
//    {                                                              //~9518I~//~9705R~
//        if (Dump.Y) Dump.println("DrawnDlgHW.repaint msgSubtype="+PmsgSubtype+",respstat="+Arrays.toString(PrespStat));//~9518I~//~9705R~
//        msgSubtype=PmsgSubtype;                                   //~9518I~//~9705R~
//        repaint(PtypeNextGame,PrespStat);                          //~9518I~//~9705R~
//    }                                                              //~9518I~//~9705R~
    //*******************************************************      //~9426I~
//  public void repaint(int PtypeNextGame,int[] PrespStat,boolean[] PswsError)//~9426I~//~9705R~
//  public void repaint(int PtypeNextGame,int[] PrespStat,boolean[] PswsError,int Preason)//~9705R~
//  public void repaint(int PtypeNextGame,int[] PrespStat,boolean[] PswsError,int Preason,int PeswnRequester)//~9705I~//~0306R~
    public void repaint(int PtypeNextGame,int[] PrespStat,boolean[] PswsError,int Preason,int PeswnRequester,boolean PswSuspend)//~0306I~
    {                                                              //~9426I~
        if (Dump.Y) Dump.println("DrawnDlgHW.repaint reason="+Preason+",nextgametype="+PtypeNextGame+",PswsError="+Arrays.toString(PswsError)+",swSuspend="+PswSuspend);//~9426I~//~9705R~//~0306R~
        swsErrLooser=PswsError;                                     //~9426I~
        swSuspend=PswSuspend;                                      //~0306I~
//      repaint(PtypeNextGame,PrespStat);                          //~9426I~//~9705R~
//      repaint(PtypeNextGame,PrespStat,Preason);                  //~9705R~
        repaint(PtypeNextGame,PrespStat,Preason,PeswnRequester);   //~9705I~
    }                                                              //~9426I~
//    //*******************************************************      //~9518I~//~9705R~
//    public void repaint(int PmsgSubtype,int PtypeNextGame,int[] PrespStat,boolean[] PswsError)//~9518I~//~9705R~
//    {                                                              //~9518I~//~9705R~
//        if (Dump.Y) Dump.println("DrawnDlgHW.repaint msgSubtype="+PmsgSubtype+",PswsError="+Arrays.toString(PswsError));//~9518I~//~9705R~
//        msgSubtype=PmsgSubtype;                                    //~9518I~//~9705R~
//        repaint(PtypeNextGame,PrespStat,PswsError);                //~9518I~//~9705R~
//    }                                                              //~9518I~//~9705R~
//    //*******************************************************    //~9305R~
//    //*from MainActivity on UIThread                             //~9305R~
//    //*******************************************************    //~9305R~
//    public static void repaintUI(EventCB PeventCB)               //~9305R~
//    {                                                            //~9305R~
//        try                                                      //~9305R~
//        {                                                        //~9305R~
//            if (Dump.Y) Dump.println("DrawnDlgHW.repaintUI");    //~9305R~
//            if (AG.aUAEndGame!=null)                             //~9305R~
//                if (AG.aUAEndGame.dlgConfirmHW!=null)            //~9305R~
//                    AG.aUAEndGame.dlgConfirmHW.update();         //~9305R~
//        }                                                        //~9305R~
//        catch(Exception e)                                       //~9305R~
//        {                                                        //~9305R~
//            Dump.println(e,"DrawnDlgHW:repaintUI");              //~9305R~
//        }                                                        //~9305R~
//    }                                                            //~9305R~
    //*******************************************************      //~9311I~
    private boolean chkRespAllOK()                                 //~9311I~
    {                                                              //~9311I~
    	boolean rc=AG.aUAEndGame.chkResponseHW() && !AG.aUAEndGame.chkNGHW();//~9311I~
        if (Dump.Y) Dump.println("DrawnDlgHW.chkRespAllOK rc="+rc);//~9311I~
        return rc;                                                 //~9311I~
    }                                                              //~9311I~
    //*******************************************************      //~9311I~
    //*fix drawnHW                                                 //~9703I~
    //*******************************************************      //~9703I~
    private void showTotal()                                    //~9311I~//~9318R~
    {                                                              //~9311I~
//      AG.aUADelayed.resetStopAuto();                             //~9703M~//~9704R~
        endgameType=EGDR_DRAWN_HW;                                 //~9703I~
        switch (typeNextGame)                                      //~9703I~
        {                                                          //~9703I~
        case RBIDNEXT_NEXTPLAYER:                                  //~9703I~
	    	continueThisRound();                                    //~9703I~
            return;                                                //~9703I~
        case RBIDNEXT_RESET:                                       //~9703I~
	    	resetThisRound();                                       //~9703I~
        }                                                          //~9703I~
//      UserAction.showInfoAllEswn(0/*opt*/,Utils.getStr(R.string.Info_DrawnHW_Confirmed));//~9609I~//~0225R~
        UserAction.showInfoAllEswn(0/*opt*/,R.string.Info_DrawnHW_Confirmed);//~0225I~
        if (Dump.Y) Dump.println("DrawnDlgHW.showTotal");       //~9311I~//~9318R~
	    saveStatus();                                               //~9426I~
//    	CMP.setAmtError(swsErrLooser,amtsError);                   //~9426M~//~9704R~
    	setAmmountErr();                                           //~9704I~
        adjustScore();                                             //~9426I~
        CMP.adjustedScoreDrawn=adjustedScoreWithError;               //~9426I~
    	ScoreDlg.showDialog(endgameType,amtsError,typeNextGame);	//minus chk//~9318R~//~9426R~//~9703R~
    }                                                              //~9311I~
    //*******************************************************      //~9703M~
    private void continueThisRound()                               //~9703M~
    {                                                              //~9703M~
        if (Dump.Y) Dump.println("DrawnDlgHW.continueThisRound");  //~9703M~
        setNotRonable();                                           //~9A14I~
		UserAction.showInfoAll(0/*opt*/,R.string.Info_DrawnHWContinueThisRound);      //~9629R~//~9703M~
//      releaseStopAutoTakeDiscardAll();                               //~9704I~//~9A24R~
		DrawnReqDlgHW.releaseStopAutoTakeDiscardAll();             //~9A24I~
    }                                                              //~9703M~
    //*******************************************************      //~9A14I~
    private void setNotRonable()                                   //~9A14I~
    {                                                              //~9A14I~
        if (Dump.Y) Dump.println("DrawnDlgHW.setNotRonable errLooser="+Arrays.toString(swsErrLooser));//~9A14I~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9A14I~
        {                                                          //~9A14I~
        	if (swsErrLooser[eswn])                                //~9A14I~
            {                                                      //~9A14I~
	        	int player=Accounts.eswnToPlayer(eswn);            //~9A14R~
            	PLS.setNotRonable(player);                         //~9A14R~
            }                                                      //~9A14I~
        }                                                          //~9A14I~
    }                                                              //~9A14I~
    //*******************************************************      //~9703M~
    private void resetThisRound()                                  //~9703M~
    {                                                              //~9703M~
//  	ACC.resetReach(swsReach);                                  //~9704R~
        Arrays.fill(swsReach,false);                               //~9704I~
//      endgameType=EGDR_RESET;  //redo by same gamectr      //~9703R~//~9704R~
        typeNextGame=NGTP_RESET;       //means reset stick     //~9703I~//~9704R~
        if (Dump.Y) Dump.println("DrawnDlgHW.resetThisRound endgameType="+endgameType);//~9703I~
    }                                                              //~9703M~
    //*******************************************************      //~9426I~
    private void adjustScore()                                     //~9426I~
    {                                                              //~9426I~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~9426I~
        {                                                          //~9426I~
        	int idx=ACC.currentEswnToPosition(eswn);                   //~9426I~
            adjustedScoreWithError[idx]=ACC.score[idx]+amtsError[eswn];    //~9426I~
        }                                                          //~9426I~
        if (Dump.Y) Dump.println("DrawnDlgHW.adjustScore old score="+Arrays.toString(ACC.score)+"amtsError="+Arrays.toString(amtsError)+",adjustedscoreWithError="+Arrays.toString(adjustedScoreWithError));//~9426I~
    }                                                              //~9426I~
    //*******************************************************      //~9609I~
    //*query force to GO even NG response received                 //~9609I~
    //*******************************************************      //~9609I~
    protected void alertToForceOK(int Ptitleid,int Pmsgid)         //~9609I~
    {                                                              //~9609I~
        if (Dump.Y) Dump.println("DrawnDlgHW.alertToForceOK");     //~9609I~
        swIgnoreAlertResponse=false;                               //~9609I~
        int flag=BUTTON_POSITIVE|BUTTON_NEGATIVE;                  //~9609I~
        Alert.showAlert(Ptitleid,Pmsgid,flag,this);                //~9609I~
    }                                                              //~9609I~
    //*******************************************************      //~9B13I~
    //*query force to GO even NG response received                 //~9B13I~
    //*******************************************************      //~9B13I~
    private void alertToCancelHW()                                 //~9B13I~
    {                                                              //~9B13I~
        if (Dump.Y) Dump.println("DrawnDlgHW.alertToCancelHW");    //~9B13I~
        swIgnoreAlertResponse=false;                               //~9B13I~
        swCancelAlert=true;                                        //~9B13I~
        int flag=BUTTON_POSITIVE|BUTTON_NEGATIVE;                  //~9B13I~
        Alert.showAlert(TITLEID,R.string.Alert_DrawnHWCancel,flag,this);//~9B13I~
    }                                                              //~9B13I~
    //*******************************************************      //~9B13I~
	protected void alertActionReceivedCancelHW(int Pbuttonid,int Prc)//~9B13I~
    {                                                              //~9B13I~
        if (Dump.Y) Dump.println("DrawnDlgHW.alertActionReceivedCancelHW buttonID="+Pbuttonid+",rc="+Prc);//~9B13I~
    	if (Pbuttonid==BUTTON_POSITIVE)                            //~9B13I~
        {                                                          //~9B13I~
	    	continueThisRound();                                   //~9B13I~
	        AG.aUAEndGame.dlgConfirmHW.dismiss();                  //~9B13I~
        }                                                          //~9B13I~
    }                                                              //~9B13I~
    //*******************************************************      //~9608M~
    @Override   //AlertI                                           //~9608I~
	public int alertButtonAction(int Pbuttonid,int Prc)            //~9608M~
    {                                                              //~9608M~
        if (Dump.Y) Dump.println("DrawnDlgHW.alertButtonAction buttonID="+Pbuttonid+",rc="+Prc);//~9608I~//~9A14R~
        if (!swIgnoreAlertResponse)                                //~9609I~
		    alertActionReceived(Pbuttonid,Prc);                        //~9608M~//~9609R~
        return 0;                                                  //~9608M~
    }                                                              //~9608M~
    //*******************************************************      //~9608M~
    //*Override this                                               //~9609I~
    //*******************************************************      //~9609I~
	protected void alertActionReceived(int Pbuttonid,int Prc)      //~9608M~
    {                                                              //~9608M~
        if (Dump.Y) Dump.println("DrawnDlgHW.alertActionReceived buttonID="+Pbuttonid+",rc="+Prc);//~9608I~//~9A14R~
        if (swCancelAlert)                                          //~9B13I~
			alertActionReceivedCancelHW(Pbuttonid,Prc);            //~9B13I~
        else                                                       //~9B13I~
    	if (Pbuttonid==BUTTON_POSITIVE)                            //~9609I~
        {                                                          //~9609I~
	        btnNextGame.setEnabled(true);                          //~9609I~
		    enableNextGame();                                      //~9A14I~
			fixRGNextGame(false/*PswFix*/);                         //~9A14I~
        }                                                          //~9609I~
    }                                                              //~9608M~
    //*******************************************************      //~9608M~//~9609M~
    protected void alertForNG(int Ptitleid,int Pmsgid)             //~9608M~//~9609M~
    {                                                              //~9608M~//~9609M~
        if (Dump.Y) Dump.println("DrawnDlgHW.alertForNG");          //~9608I~//~9609M~//~9A14R~
        swIgnoreAlertResponse=true;                                //~9609I~
        int flag=BUTTON_POSITIVE;                                  //~9608M~//~9609M~
        Alert.showAlert(Ptitleid,Pmsgid,flag,this/*callBack*/);    //~9608I~//~9609M~
    }                                                              //~9608M~//~9609M~
	//*************************************************************************//~0306I~
    private boolean confirmSuspend()                               //~0306I~
    {                                                              //~0306I~
    	if (Dump.Y) Dump.println("DrawnDlgHW.confirnmSuspend swSuspend="+swSuspend+",swConfirmedSuspend"+swConfirmedSuspend+",swConfirmedSuspendOK="+swConfirmedSuspendOK);//~0306I~//~0307R~
        boolean rc=false;                                          //~0306I~
        if (swSuspend)                                             //~0306I~
        {                                                          //~0306I~
            if (!swConfirmedSuspend || !swConfirmedSuspendOK)      //~0306I~
            {                                                      //~0306I~
                swConfirmedSuspend=true;                           //~0306I~
                SuspendAlert.newInstance(TITLEID,this,typeNextGame);                    //~0306I~//~0307R~
                rc=true;                                           //~0306I~
            }                                                      //~0306I~
        }                                                          //~0306I~
        else                                                       //~0306I~
            swConfirmedSuspend=false;                              //~0306I~
        return rc;                                                 //~0306I~
    }                                                              //~0306I~
	//*************************************************************************//~0306I~
    @Override //SuspendAlertI                                      //~0306I~
	public void suspendAlertAction(int PbuttonID)                  //~0306I~
    {                                                              //~0306I~
        swConfirmedSuspendOK=(PbuttonID==BUTTON_POSITIVE);         //~0306I~
        if (Dump.Y) Dump.println("DrawnDlgHW.suspendAlertAction PbuttonID="+PbuttonID+",swConfirmedSuspendOK="+swConfirmedSuspendOK);//~0306I~
        if (swConfirmedSuspendOK)                                  //~0306I~
            onClickOK();                                           //~0306I~
    }                                                              //~0306I~
}//class                                                           //~v@@@R~
