//*CID://+va66R~:                             update#= 1110;       //~va66R~
//*****************************************************************//~v101I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//2020/10/13 va16 do not show hidden dora when reach was not declared//~va03I~
//2020/04/16 va03:alert suspendrequested                           //~va03I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;                                      //~v@@@I~
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.game.ACAction;
import com.btmtest.game.Accounts;
import com.btmtest.game.Complete;
import com.btmtest.game.GConst;
import com.btmtest.game.Players;
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.UA.UAEndGame;
import com.btmtest.game.UserAction;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.USpinner;
import com.btmtest.gui.URadioGroup;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.gui.UButton;                                    //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.game.Complete.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.UA.UAEndGame.*;
import static com.btmtest.utils.Alert.*;
import static com.btmtest.dialog.DrawnDlgHW.*;             //~9705I~//~9706R~

//****************************************************************************//~9316I~
//*   Ron issuer --(COMPDLG_REQ)-->to All---(COMPDLG_RESP)-->to All//~9316I~
//*   when all COMPDLG_RESP received                               //~9316I~
//*          dealer: show CompleyteDlg                             //~9316I~
//*          client:--(COMPRSULT_REQ:-1)-->server--(COMPRESULT_REQ:-1)-->all client except dealer//~9316I~
//*   when received COMPRESULT_REQ                                 //~9316I~
//*          if showing, dismiss                                   //~9316I~
//*          if -1  CompleteDlg:newInstance()      //dealer        //~9316R~
//*          else   CompleteDlg:newInstance(intp)  //except dealer //~9316R~
//*                                                                //~9316I~
//*   by OK button, COMPDLG_RESP to all                            //~9316I~
//*   when received COMPRESULT_RESP   newInstance or repaint if shown//~9316R~
//****************************************************************************//~9316I~
public class CompleteDlg extends OKNGDlg //UFDlg                             //~v@@@R~//~9314R~
            implements UCheckBox.UCheckBoxI, URadioGroup.URadioGroupI, USpinner.USpinnerI                        //~9213I~//~9214R~//~9219R~
            ,Alert.AlertI                                          //~9B29I~
            ,SuspendAlert.SuspendAlertI                            //~0306I~
//            ,DialogInterface.OnShowListener                      //~9228R~
{                                                                  //~2C29R~
    private static final int TITLEID_REQ=R.string.Title_CompleteDlgReq;   //~9224I~//~9314R~
    private static final int TITLEID_RESP=R.string.Title_CompleteDlgResp;//~9314I~
    private static final int LAYOUTID=R.layout.completedlg;        //~9224I~
    private static final String HELPFILE="CompleteDlg";            //~9224I~
                                                                   //~9224I~
    private static final int COMPTYPE_NONE=0;                      //~9211I~
    private static final int COMPTYPE_TAKE=1;                      //~9211I~
    private static final int COMPTYPE_RIVER=2;                     //~9211I~
    private static final int COMPTYPE_LOOSER=3;                    //~9211I~
    private static final int COMPTYPE_INVALID=4;                   //~9228I~
    private static final int COMPTYPE_INVALID_BYESWN=5;            //~9519I~
    private static final int COMPTYPE_PAOLOOSER=6;                 //~9601I~
                                                                   //~9214I~
    private static final int UCBP_COMPTYPE_NORMAL=1;                    //~9214I~//~9219R~//~9320R~
    private static final int UCBP_COMPTYPE_ERROR=2;                //~9219I~
    private static final int UCBP_COMPTYPE_PAO=3;                  //~9219I~
    private static final int UCBP_ERROR=5;                         //~9214I~//~9219R~
//  private static final int UCBP_PAO=6;                           //~9219I~//~9601R~
    private static final int UCBP_COMPTYPE_PAO_DONE=7; 	//no need to getPao,already done//~9224I~
    private static final int UCBP_COMPTYPE_ESWN=8;                 //~9315I~
//  private static final int UCBP_TYPE_NEXTGAME=9;                 //~9705I~//~9B10R~
    private static final int UCBP_SUSPEND=10;                      //~0304I~
                                                                   //~9227I~
//  private static final int COLOR_RESULT_NONE=Color.argb(0xff,0x1b,0xa4,0xd7);//sky blue//~9227I~//~9228R~
    private static final int COLOR_RESULT_NONE=Color.argb(0xff,0xff,0xff,0x00);//yellow//~9228I~
    private static final int COLOR_RESULT_OK=Color.argb(0xff,0x00,0xff,0x00);//~9227I~
    private static final int COLOR_RESULT_NG=Color.argb(0xff,0xff,0x66,0x00);//orange//~9227I~
    private static final int[] colorResult={COLOR_RESULT_NONE,COLOR_RESULT_OK,COLOR_RESULT_NG};//~9227I~
    private static final int COLOR_RESULT_OTHER=Color.argb(0xff,0xc0,0xc0,0xc0);//~9228I~
    private static final int COLOR_SPRITPOS_BG=R.color.spritposid; //~9531R~
    private static final int COLOR_SPRITPOS_FG=Color.argb(0xff,0xff,0xff,0xff);//~9531I~
                                                                   //~9227I~
    private static final int COLOR_RESPNONE=COLOR_RESULT_NONE;     //~9228I~
    private static final int COLOR_RESPOK=COLOR_RESULT_OK;         //~9228I~
    private static final int COLOR_RESPNG=COLOR_RESULT_NG;          //~9227I~
    private static final int COLOR_RESPERR=Color.argb(0xff,0xff,0x00,0x00);//orange//~9227I~
                                                                   //~9214I~
    private static final int UBRGP_PAOESWN=3;                    //~9220I~//~9227R~
    private static final int UBRGP_TYPE_NEXTGAME=4;                //~9B10I~
                                                                   //~9214I~
//    private static final int USPP_COMPTYPE=1;                      //~9214I~//~9315R~
    private static final int USPP_POINT=2;                         //~9214I~
    private static final int USPP_RANK=3;                          //~9214I~
//  private static final int USPP_PAO=4;                           //~9214I~//~9601R~
                                                                   //~9706I~
    private static final int[] rbIDsNGTP={R.id.rbContinue,         //~9706I~
    					 R.id.rbNext,                              //~9706I~
    					 R.id.rbNextPlayer,  //visibility=gone but exist to set rbReset=3=NGTP_RESET//~9708I~
    					 R.id.rbReset,                             //~9706I~
                         };                                        //~9706I~
//  private static final int RBIDNEXT_CONT=0;                      //~9706I~//~9A14R~
//  private static final int RBIDNEXT_NEXT=1;                      //~9706I~//~9A14R~
//  private static final int RBIDNEXT_NEXRESET=2;                     //~9706I~//~9708R~//~9A14R~
//  private static final int RBIDNEXT_RESET=3;                     //~9708I~//~9A14R~
                                                                   //~9214I~
    private static final int PAOLOOSER_NO =R.id.rbPaoNo;           //~9601I~
    private static final int PAOLOOSER_E  =R.id.rbPaoE;            //~9220I~
    private static final int PAOLOOSER_S  =R.id.rbPaoS;            //~9220I~
    private static final int PAOLOOSER_W  =R.id.rbPaoW;            //~9220I~
    private static final int PAOLOOSER_N  =R.id.rbPaoN;            //~9220I~
//  private static final int[] rbIDsPaoLooser=new int[]{PAOLOOSER_E,PAOLOOSER_S,PAOLOOSER_W,PAOLOOSER_N};//~9226I~//~9601R~
    private static final int[] rbIDsPaoLooser=new int[]{PAOLOOSER_NO,PAOLOOSER_E,PAOLOOSER_S,PAOLOOSER_W,PAOLOOSER_N};//~9601I~
    private static final String[] strsCompType=AG.resource.getStringArray(R.array.CompType);//~9315I~
	private static final int[] llsCompResultID=new int[]{R.id.llcomp_result1,R.id.llcomp_result2,R.id.llcomp_result3,R.id.llcomp_result4};//~9528I~
//    private static final int REQ_OPEN_DIALOG_TO_DEALER=-1;         //~9409I~//~9410R~
//    private static final int REQ_TRANSFER_TO_DEALER_FROM_SERVER=-2;//~9409I~//~9410R~
                                                                   //~9A12I~
    private static final int POS_COMPINDEX=1;                      //~9A12I~
                                                                   //~9315I~
//  protected Button btnNG,btnSend;                                //~9226R~//~9314R~
    private Button btnTotal,btnShowRule;                                     //~9316I~//~9417R~
    private USpinner spnPoint,spnRank;            //~9224I~
//  private USpinner[] spnsRankPao;                                //~9224I~//~9320R~
    private USpinner spnComp1,spnComp2,spnComp3,spnComp4;          //~v@@@I~//~9212R~
//  private USpinner[] spnsComp;                                   //~9212I~//~9315R~
//  private LinearLayout llspnComp1,llspnComp2,llspnComp3,llspnComp4;//~9227I~//~9315R~
//  private LinearLayout[] llspnsComp;                             //~9227I~//~9315R~
    private LinearLayout llReacher1,llReacher2,llReacher3,llReacher4,llllReachers;//~9228I~
    private LinearLayout[] llReachers;                             //~9228I~
//    private TextView tvName1,tvName2,tvName3,tvName4;              //~9211I~//~9212R~//~9315R~
//    private TextView tvEswn1,tvEswn2,tvEswn3,tvEswn4;              //~9212R~//~9315R~
//    private TextView tvAmmount1,tvAmmount2,tvAmmount3,tvAmmount4;  //~9213I~//~9315R~
    private TextView tvCompType;                                   //~9218I~//~9219R~//~9223R~
    private TextView[] tvsName,tvsEswn,tvsAmmount,tvsCompType;                            //~9212R~//~9213R~//~9315R~
    private TextView[] tvsSpritPosID;                              //~9531I~
    private URadioGroup[] rgsPaoEswn;                              //~9224I~
//  private UCheckBox[] cbsPaoEswn,cbsCompType;                     //~9224I~//~9315R~//~9601R~
    private UCheckBox[] cbsCompType;                               //~9601I~
    private UCheckBox cbEE,cbES,cbEW,cbEN;                         //~9219R~
    private UCheckBox[] cbErr;                                     //~9223I~
//  private UCheckBox cbNormal,cbError/*,cbPao*/;                      //~9219I~//~9224R~//~9320R~
//  private UCheckBox cbError/*,cbPao*/;                           //~9320I~//~9424R~
                                                                   //~1A6fI~
//  protected UFDlg ufdlg;                                         //~v@@@R~//~9227R~
//  private Dialog androidDlg;                                     //~v@@@I~//~9314R~
    private boolean[] swsErrLooser=new boolean[PLAYERS];//={false,false,false,false};       //~9219R~//~9414R~//~9B12R~
    private Players PLS;                                           //~9212I~
    public int gameField;
    public int gameSeq;
//    public int pointReach;              //~9223I~
    private int idxPoint,idxRank;                                  //~9212I~
    private String[] accountNames;                                 //~9212I~
    private int[] accountEswn;                                     //~9212I~
    private boolean swMultiRon,swMultiRon3Next;            //~9217R~//~9223R~
    private boolean swDrawnHW3RCont,swDrawn3R;                     //~9B29I~
//  public  boolean swTake;                                        //~9217I~//~9519R~
    private boolean swTake;                                        //~9519I~
    private boolean	swInitLayout;                                  //~9214R~
    public CompDlgDora ivDora;                                     //~9219I~
//  public String title;                                           //~9219I~//~9306R~
    private Spanned title;                                         //~9306I~
    private LinearLayout llGainer1,llGainer2,llGainer3;
    private LinearLayout llPao,llPao1,llPao2,llPao3;               //~9224I~
    private LinearLayout[] llGainer;//~9222I~
    private LinearLayout[] llPaos;                                 //~9224I~
    private boolean swCompNormal=true;//,swCompError;            //~9219I~//~9223R~//~9224R~//~9424R~
    private int[][] amtsNormal=new int[PLAYERS][];                             //~9219I~//~9223R~
//  private int[][] amtsError=new int[PLAYERS][];                  //~9403I~//~9420R~
//  private int[][] amtsErrorByLooser=new int[PLAYERS][];          //~9414I~//~9420R~
    private int[][] amtsPao=new int[PLAYERS][];                    //~9403I~
    private int[] amtsError=new int[PLAYERS];                              //~9219I~//~9424R~
    private int[] amtPao=new int[PLAYERS];                                //~9219I~
    private int[] amtTotal=new int[PLAYERS];                    //~9219I~
    private int[] paoLooser=new int[]{-1,-1,-1,-1};                      //~9225I~//~9601R~
    private int[] paoGainer=new int[]{-1,-1,-1,-1};                      //~9225I~//~9601R~
    private int[] paoRank=new int[PLAYERS];                        //~9225I~
    private int[] resultCompType=new int[PLAYERS];                 //~9316I~
    private int ctrErrLooser;                                      //~9219I~
    private  Complete.Status[] sortedStatus;                       //~9223I~
    private  Complete.Status[] paoStatus;                          //~9224I~
    private  int[] receivedParm;                                   //~9225I~
    private  int receivedParmPos;                                  //~9225I~
    private  boolean swReceived;                                   //~9225I~
//  private  boolean /*rcv_swCompErr,*/rcv_swCompNormal;               //~9225I~//~9424R~//~0304R~
    private  boolean[] rcv_cbErrState;                             //~9225I~
    private boolean[] rcv_cbPaoStatus;                             //~9225I~
    private int[] rcv_paoGainer,rcv_paoLooser,rcv_paoRank,rcv_compType=new int[PLAYERS];         //~9225I~//~9226R~
    private int[] rcv_amtTotal=new int[PLAYERS];                   //~9320R~
    private int currentEswn;                                       //~9225I~
    private int requesterEswn,repaintEswn;                                     //~9227I~//~9313R~
    private Complete CMP;                                          //~9227I~
    private Accounts ACC;                                          //~9227I~
    private boolean swInitalPointShown;        //~9227I~//~9228R~  //~9315R~
//  private boolean[] swsInvalid=new boolean[PLAYERS];	//position seq//~9705R~
    private boolean[] swsInvalid;                                  //~9705I~
    private boolean[] swsInvalidByEswn=new boolean[PLAYERS];	//account index seq//~9409I~
    private boolean[] swsNG=new boolean[PLAYERS];                  //~9316I~
    private int[] responseStatus=new int[PLAYERS];                 //~9227I~
//  private int eswn1stGainer;                                     //~9315R~
    private boolean swNoPayMinusError=true;	//TODO                 //~9414I~
    private int cutEswn;
//  protected int ctrNG,ctrNone;//~9531I~                          //~9608R~
    protected int ctrNGCompleteReq;                                //~9608I~
    private URadioGroup rgNextGame;                                //~9705I~
	private UCheckBox cbSuspend;                                   //~0304I~
    private int typeNextGame=NEXTGAME_UNKNOWN;                     //~9705I~
    private boolean swErr;                                         //~9705I~
    private boolean swSuspend,rcv_swSuspend;                       //~0304I~
    private boolean /*swConfirmSuspend,*/swConfirmedSuspend,swConfirmedSuspendOK;           //~0304I~//~0306R~
    private int rcv_typeNextGame;                                  //~9707I~
    private int widthTileImage;                                    //~9927I~
    private int[] compIndexNextPlayerToReset;                      //~9A12I~
    private LinearLayout llReacherEswn;                            //~0328R~
    //*************************************************************************                       //~1A4zI~//~v@@@I~
    public CompleteDlg()                                           //~v@@@R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("CompleteDlg.defaultConstructor");//~v@@@R~
        PLS=AG.aPlayers;                                           //~9212I~
        CMP=AG.aComplete;                                          //~9227I~
        ACC=AG.aAccounts;                                          //~9227I~
        swsInvalid=CMP.swsInvalid;                                 //~9705I~
        typeNextGame=CMP.typeNextGame;                             //~0301I~
        if (CMP.swsErrLooser!=null)                                //~0301I~
	        swsErrLooser=CMP.swsErrLooser;                         //~0301R~
        if (CMP.paoLooser!=null)                                   //~0302I~
	        paoLooser=CMP.paoLooser;                               //~0302I~
	    swSuspend=CMP.swSuspend;                                   //~0304I~
	    CMP.swSent=false;                                              //~0314I~
        if (Dump.Y) Dump.println("CompleteDlg.defaultConstructor swsInvalid="+Arrays.toString(swsInvalid)+"swsErrLooser="+Arrays.toString(swsErrLooser)+",paoLooser="+Arrays.toString(paoLooser));//~0302R~
        if (Dump.Y) Dump.println("CompleteDlg.defaultConstructor swSuspen="+swSuspend);//~0304I~
    }                                                              //~v@@@R~
    //******************************************                   //~v@@@R~
    public static CompleteDlg newInstance()                        //~v@@@R~//~9315R~
//  public static CompleteDlg newInstance(int Peswn1stGainer)      //~9315R~
    {                                                              //~v@@@R~
        CompleteDlg dlg=AG.aCompleteDlg;                           //~v@@@I~//~9226R~
        if (Utils.isShowingDialogFragment(dlg))                    //~v@@@I~//~9226R~
        {                                                          //~v@@@I~//~9226R~
            UView.showToast(R.string.Err_NowShowingTheDlg);   //~v@@@I~//~9226R~
            return dlg;                                                //~v@@@I~//~9226R~
        }                                                          //~v@@@I~//~9226R~
    	dlg=new CompleteDlg();                                     //~v@@@I~
//  	dlg.ufdlg=UFDlg.newInstance(dlg,TITLEID,LAYOUTID,          //~9224R~//~9227R~
    	UFDlg.setBundle(dlg,TITLEID_REQ,LAYOUTID,                      //~9227R~
				FLAG_OKBTN | FLAG_CANCELBTN | FLAG_CLOSEBTN | FLAG_HELPBTN | FLAG_RULEBTN,//~v@@@I~//~9314R~//~9708R~
				TITLEID_REQ/*helptitleid*/,HELPFILE);         //~v@@@I~//~9224R~
        AG.aCompleteDlg=dlg;                                       //~v@@@I~
//      dlg.eswn1stGainer=Peswn1stGainer;                          //~9315R~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
    //******************************************                   //~9225I~
    public static CompleteDlg newInstance(int[] PintParm,int Ppos) //~9225I~
    {                                                              //~9225I~
        if (Dump.Y) Dump.println("CompleteDlg:newInstance parm="+Arrays.toString(PintParm));//~9315I~//~9410R~
    	CompleteDlg dlg=AG.aCompleteDlg;                           //~9225I~
        if (Utils.isShowingDialogFragment(dlg))                    //~9225I~//~9226R~
        {                                                          //~9225I~//~9226R~
            UView.showToast(R.string.Err_NowShowingTheDlg);        //~9225I~//~9226R~
            return dlg;                                            //~9225I~//~9226R~
        }                                                          //~9225I~//~9226R~
    	dlg=new CompleteDlg();                                     //~9225I~
//  	dlg.ufdlg=UFDlg.newInstance(dlg,TITLEID,LAYOUTID,          //~9225I~//~9227R~
    	UFDlg.setBundle(dlg,TITLEID_REQ,LAYOUTID,                      //~9227R~
				FLAG_OKBTN | FLAG_CANCELBTN | FLAG_CLOSEBTN | FLAG_HELPBTN | FLAG_RULEBTN,//~9225I~//~9316R~//~9708R~
				TITLEID_REQ/*helptitleid*/,HELPFILE);                  //~9225I~
        AG.aCompleteDlg=dlg;                                       //~9225I~
        AG.aCompleteDlg.receivedParm=PintParm;                     //~9225I~
        AG.aCompleteDlg.receivedParmPos=Ppos;                      //~9225I~
        return dlg;                                                //~9225I~
    }                                                              //~9225I~
//    //******************************************                   //~9410I~//~9411R~
//    @Override                                                      //~9410I~//~9411R~
//    public void onStart()                                          //~9410I~//~9411R~
//    {                                                              //~9410I~//~9411R~
//        super.onStart();                                           //~9410I~//~9411R~
//        if (Dump.Y) Dump.println("CompleteDld:onStart");           //~9410I~//~9411R~
//        Dialog dlg=getDialog();                                    //~9410I~//~9411R~
//        int ww=CompReqDlg.getTilesWidth();                         //~9410I~//~9411R~
//        if (dlg!=null)                                             //~9410I~//~9411R~
//        {                                                        //~9411R~
////          UView.setDialogWidth(dlg,RATE_IN_SCR/*0.95*/,ww);      //~9410I~//~9411R~
//            llGainer1.getLayoutParams().width=CompReqDlg.getTilesWidth();//~9411R~
//            llGainer1.requestLayout();                           //~9411R~
//            if (Dump.Y) Dump.println("CompleteDlg:onStart requestLayout");//~9411R~
//        }                                                        //~9411R~
//    }                                                              //~9410I~//~9411R~
//    //******************************************                   //~9225I~//~9314R~
//    @Override                                                      //~9225I~//~9314R~
//    public void onPause()                                          //~9225I~//~9314R~
//    {                                                              //~9225I~//~9314R~
//        super.onPause();                                           //~9225I~//~9314R~
//        if (Dump.Y) Dump.println("CompleteDld:onPause issue dismiss");//~9225I~//~9314R~//~9315R~
//        dismiss();  //because hard to make Complete.Status.ammount to parcelable//~9225I~//~9314R~
//    }                                                              //~9225I~//~9314R~
    //******************************************                   //~9927I~
    //*at onResume                                                 //~9927I~
    //******************************************                   //~9927I~
    @Override                                                      //~9927I~
    protected int getDialogWidth()                                 //~9927I~
    {                                                              //~9927I~
//        if (AG.portrait)                                         //~9927I~
//            ww=0;   //UFFlg.machparentPortrait                   //~9927I~
//        else                                                     //~9927I~
//        {                                                        //~9927I~
//      	int ww=getTilesWidth();                                //~9927R~
        	int ww=widthTileImage;                                 //~9927I~
            if (ww!=0)                                             //~9927I~
	        	ww+=AG.dialogPaddingHorizontal;                    //~9927R~
//        }                                                        //~9927I~
//  	int ww2=getDialogWidthSmallDevicePortraitWithRate(RATE_SMALLDEVICE_WIDTH);//~9A12I~//~9B11R~
    	int ww2=UFDlg.getDialogWidthSmallDevicePortraitWithRate(RATE_SMALLDEVICE_WIDTH);//~9A12I~//~9B11I~
        ww=Math.max(ww,ww2);                                       //~9A12I~
        if (Dump.Y) Dump.println("CompleteDlg.getDialogWidth swPortrait="+AG.portrait+",ww="+ww+",smalldeviceport="+ww2);//~9927I~//~9A12R~
        return ww;                                                 //~9927I~
    }                                                              //~9927I~
    //******************************************                   //~v@@@M~
    @Override
//  protected void initLayout(View PView)                            //~v@@@I~//~9314R~
    protected void initLayoutAdditional(View PView)                //~9314I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("CompleteDlg.initLayoutAdditional");        //~v@@@R~//~9314R~
//      super.initLayout(PView);                                   //~9314R~
//      androidDlg=getDialog();                                    //~v@@@I~//~9314R~
                                                                   //~9228I~
//        androidDlg.setOnShowListener(this);                        //~9228I~
        AG.aComplete.dismissAllReqDlg();                                                           //~9228I~//~9315R~
        swReceived=receivedParm!=null;                             //~9225I~
        if (swReceived)                                                           //~9219I~//~9225R~
		    parseReqMsg(receivedParm,receivedParmPos);             //~9225I~
//      setupValue();                                              //~9212I~//~9219M~//~9314R~
//      swRequester=currentEswn==0;                                                           //~9219I~//~9227R~//~9315R~
        swInitLayout=true;                                         //~9214I~
//      btnNG           =              UButton.bind(PView,R.id.CompleteNG,this);//~v@@@R~//~9314R~
//      btnSend         =              UButton.bind(PView,R.id.Send,this);//~v@@@I~//~9314R~
        btnTotal        =              UButton.bind(PView,R.id.FixTotal,this);//~9316I~//~9321R~
        btnShowRule     =              UButton.bind(PView,R.id.ShowRule,this);//~9417I~
//      if (swReceived)                                            //~9226I~//~9227R~
                                                                   //~9212I~
//        tvName1         =(TextView)    UView.findViewById(PView,R.id.memberName1);//~v@@@R~//~9211I~//~9212R~//~9315R~
//        tvName2         =(TextView)    UView.findViewById(PView,R.id.memberName2);//~9211I~//~9212R~//~9315R~
//        tvName3         =(TextView)    UView.findViewById(PView,R.id.memberName3);//~9211I~//~9212R~//~9315R~
//        tvName4         =(TextView)    UView.findViewById(PView,R.id.memberName4);//~9211I~//~9212R~//~9315R~
//        tvAmmount1      =(TextView)    UView.findViewById(PView,R.id.ammountESWN1);//~9213I~//~9315R~
//        tvAmmount2      =(TextView)    UView.findViewById(PView,R.id.ammountESWN2);//~9213I~//~9315R~
//        tvAmmount3      =(TextView)    UView.findViewById(PView,R.id.ammountESWN3);//~9213I~//~9315R~
//        tvAmmount4      =(TextView)    UView.findViewById(PView,R.id.ammountESWN4);//~9213I~//~9315R~
//                                                                   //~9212I~//~9315R~
//        tvEswn1         =(TextView)    UView.findViewById(PView,R.id.nameESWN1);//~9212I~//~9315R~
//        tvEswn2         =(TextView)    UView.findViewById(PView,R.id.nameESWN2);//~9212I~//~9315R~
//        tvEswn3         =(TextView)    UView.findViewById(PView,R.id.nameESWN3);//~9212I~//~9315R~
//        tvEswn4         =(TextView)    UView.findViewById(PView,R.id.nameESWN4);//~9212I~//~9315R~
		setupResult(PView);                                         //~9315I~
                                                                   //~9218I~
        tvCompType      =(TextView)    UView.findViewById(PView,R.id.tvCompType);//~9218I~//~9219R~//~9223R~
                                                                   //~9212I~
        llGainer1        =(LinearLayout)UView.findViewById(PView,R.id.gainer1);//~9219I~//~9222R~
        llGainer2        =(LinearLayout)UView.findViewById(PView,R.id.gainer2);//~9222I~
        llGainer3        =(LinearLayout)UView.findViewById(PView,R.id.gainer3);//~9222I~
        llGainer=new LinearLayout[]{llGainer1,llGainer2,llGainer3};       //~9222I~
                                                                   //~9228I~
        llReacher1       =(LinearLayout)UView.findViewById(PView,R.id.reacher1);//~9228I~
        llReacher2       =(LinearLayout)UView.findViewById(PView,R.id.reacher2);//~9228I~
        llReacher3       =(LinearLayout)UView.findViewById(PView,R.id.reacher3);//~9228I~
        llReacher4       =(LinearLayout)UView.findViewById(PView,R.id.reacher4);//~9228I~
        llReachers=new LinearLayout[]{llReacher1,llReacher2,llReacher3,llReacher4};//~9228I~
        llllReachers     =(LinearLayout)UView.findViewById(PView,R.id.llReachers);//~9228I~
    	llReacherEswn=(LinearLayout)UView.findViewById(llReacher1,R.id.llReacherEswn);//~0328R~
                                                                   //~9224I~
        llPao            =(LinearLayout)UView.findViewById(PView,R.id.llPao);//~9224I~
        llPao1           =(LinearLayout)UView.findViewById(PView,R.id.compdlgpao1);//~9224I~
        llPao2           =(LinearLayout)UView.findViewById(PView,R.id.compdlgpao2);//~9224I~
        llPao3           =(LinearLayout)UView.findViewById(PView,R.id.compdlgpao3);//~9224I~
        llPaos  =new LinearLayout[]{llPao1,llPao2,llPao3};         //~9224I~
        paoStatus=new Complete.Status[llPaos.length];              //~9224I~
        Arrays.fill(paoStatus,null);                               //~9224I~
//      cbsPaoEswn=new UCheckBox[llPaos.length];                   //~9224I~//~9601R~
        rgsPaoEswn=new URadioGroup[llPaos.length];                 //~9224I~
//      spnsRankPao=new USpinner[llPaos.length];                   //~9224I~//~9320R~
                                                                   //~v@@@I~
    	if ((TestOption.option & TestOption.TO_COMPDLG_LAYOUT)==0) //TODO TEST//~9219I~
//  		ivDora=CompDlgDora.setImageLayout(PView);              //~va16R~
    		ivDora=CompDlgDora.setImageLayout(PView,-1/*eswn gainer:any*/);//~va16I~
                                                                   //~9219I~         //~9213I~
//        tvsEswn=new TextView[]{tvEswn1,tvEswn2,tvEswn3,tvEswn4};   //~9213R~//~9315R~
//        tvsAmmount=new TextView[]{tvAmmount1,tvAmmount2,tvAmmount3,tvAmmount4};//~9213R~//~9315R~
//        tvsName=new TextView[]{tvName1,tvName2,tvName3,tvName4};   //~9213I~//~9315R~
                                                                   //~9213I~        //~9212I~
                                                                   //~9219I~
    	setUCheckBox(PView);	//checkbox                         //~9213I~//~9214I~
	    setUSpinner(PView);                                        //~9214I~
//      setURadioGroup(PView);                                      //~9213R~//~9214R~//~9219R~//~9220R~//~9225R~
                                                                   //~9531I~
//  	if (PrefSetting.isNoRelatedRule())                         //~9531I~//~9708R~
//      	((LinearLayout)UView.findViewById(PView,R.id.llRelatedRule)).setVisibility(View.GONE);//~9531I~//~9708R~
//      else                                                       //~9531I~//~9708R~
//      {                                                          //~9531I~//~9708R~
	        RuleSetting.setDora(PView,true/*swFixed*/);            //~9531I~
	        RuleSetting.setSpritPos(PView,true/*swFixed*/);        //~9531I~
//      }                                                          //~9531I~//~9708R~
                                                                 //~9213I~
        getRuleSetting();                                                           //~v@@@I~//~9212R~
        setTitle();                                                //~v@@@I~
//      if (getNormalPoint())                                          //~9212I~//~9223R~//~9409R~
        if (getNormalPoint(true))                                  //~9409I~
        {                                                          //~9223I~
//	        swRequester=currentEswn==sortedStatus[0].completeEswn; //~9227I~//~9315R~
        	setCompType();                                             //~9218I~//~9223R~
			if (!swReceived)                                       //~9320I~
	        	setAmmount(UCBP_COMPTYPE_NORMAL,swCompNormal);                           //~9219I~//~9223R~//~9320R~
//            if ((TestOption.option & TestOption.TO_COMPDLG_LAYOUT)==0) //TODO TEST//~9228I~//~9314R~
//                repaintResultReply();                              //~9228R~//~9314R~
        }                                                          //~9223I~
        setAccountName();                                               //~v@@@I~//~9223R~//~9316M~
//      setCheckBoxResult();    //after set resultCompType                                   //~9315I~//~9316I~//~9706R~
        showReach();                                               //~9228I~
        getPao();                                                  //~9224I~//~9320I~
		if (swReceived)                                            //~9320I~
    		setTotalAmmountReceived();                            //~9320I~//~9416R~
        else                                                       //~9320I~
	    	setTotalAmmount();                                        //~9319I~//~9320R~//~9416R~
        setURadioGroupNGTP(PView);                                 //~9705I~//~9707M~
	    setUSpinnerListener();                                      //~9223I~//~9228R~
	    setUCheckBoxListener();  //required for also no dealer to the effect of fix                                   //~9223I~//~9228R~//~0301R~
        swInitLayout=false;                                        //~9214I~//~9228M~
//        if (!swRequester)                                          //~9227M~//~9314R~
//        {                                                          //~9226I~//~9227M~//~9314R~
//            btnSend.setVisibility(View.GONE);                                 //~9225M~//~9226I~//~9227M~//~9314R~
//        }                                                          //~9226I~//~9227M~//~9314R~
//        else                                                       //~9226I~//~9227M~//~9314R~
//        {                                                          //~9226I~//~9227M~//~9314R~
//            btnOK.setVisibility(View.GONE);                               //~9226I~//~9227M~//~9314R~
//            btnNG.setVisibility(View.GONE);                        //~9227M~//~9314R~
//        }                                                          //~9226I~//~9227M~//~9314R~
        if (Dump.Y) Dump.println("CompleteDlg.initLayout End");    //~9228I~
        if (ctrNGCompleteReq!=0)	                               //~9608I~
	        if (swRequester)                                       //~9609I~
	        	alertForNG(this,TITLEID_REQ,R.string.Alert_CompleteDlg_NGCompReqDlg);//~9608I~//~9609R~
        hideResponseEswn();                                        //~0218I~
    }                                                              //~v@@@M~
    //******************************************                   //~0218I~
    private void hideResponseEswn()                                //~0218I~
    {                                                              //~0218I~
    	if (Dump.Y) Dump.println("CompleteDlg.hideResponseEswn");  //~0218I~
        hideResponseEswn(!swRequester);                            //~0218I~
    }                                                              //~0218I~
    //******************************************                   //~9316I~
    @Override                                                      //~9316I~
    public void setButton()                                        //~9316I~
    {                                                              //~9316I~
        if (Dump.Y) Dump.println("CompleteDlg.setButton swRequester="+swRequester);//~9708I~
    	super.setButton();                                         //~9316I~
        if (AG.swTrainingMode)                                     //~va66I~
        {                                                          //~va66I~
            btnOK.setVisibility(View.VISIBLE);                     //~va66I~
	        btnCancel.setVisibility(View.VISIBLE);                 //~va66I~
	        btnClose.setVisibility(View.GONE);                     //~va66I~
		    disableFixGame(false/*PswResetResponse*/);             //~va66I~
        }                                                          //~va66I~
        else                                                       //~va66I~
        if (swRequester)                                           //~9316I~
	        btnTotal.setEnabled(swAllOK);                          //~9316R~
        else                                                       //~9316I~
	        btnTotal.setVisibility(View.GONE);                     //~9316I~
    }                                                              //~9316I~
    //******************************************                   //~9708I~
    public void disableFixGame(boolean PswResetResponse)           //~9708R~
    {                                                              //~9708I~
        if (Dump.Y) Dump.println("CompleteDlg.disableFixGame swResetResponse="+PswResetResponse);//~9708I~
		btnTotal.setEnabled(false);                                //~9708I~
        if (AG.swTrainingMode)                                     //+va66I~
	        btnOK.setEnabled(true);                                //+va66I~
        if (PswResetResponse)                                      //~9708I~
        {                                                          //~9708I~
		    Arrays.fill(respStat,EGDR_NONE);                       //~9708R~
            updateOKNG();                                          //~9708I~
        }                                                          //~9708I~
    }                                                              //~9708I~
    //******************************************                   //~9315I~
    private void setupResult(View PView)                           //~9315I~
    {                                                              //~9315I~
        if (Dump.Y) Dump.println("CompleteDlg.setupResult");       //~9315I~
//        TextView tvName1,tvName2,tvName3,tvName4;                  //~9315I~//~9528R~
//        TextView tvEswn1,tvEswn2,tvEswn3,tvEswn4;                  //~9315I~//~9528R~
//        TextView tvAmmount1,tvAmmount2,tvAmmount3,tvAmmount4;      //~9315I~//~9528R~
//        TextView tvComp1,tvComp2,tvComp3,tvComp4;                  //~9315I~//~9528R~
//                                                                   //~9315I~//~9528R~
//        tvName1         =(TextView)    UView.findViewById(PView,R.id.memberName1);//~9315I~//~9528R~
//        tvName2         =(TextView)    UView.findViewById(PView,R.id.memberName2);//~9315I~//~9528R~
//        tvName3         =(TextView)    UView.findViewById(PView,R.id.memberName3);//~9315I~//~9528R~
//        tvName4         =(TextView)    UView.findViewById(PView,R.id.memberName4);//~9315I~//~9528R~
//        tvName1         =(TextView)    UView.findViewById(PView,R.id.memberName1);//~9315I~//~9528R~
//        tvName2         =(TextView)    UView.findViewById(PView,R.id.memberName2);//~9315I~//~9528R~
//        tvName3         =(TextView)    UView.findViewById(PView,R.id.memberName3);//~9315I~//~9528R~
//        tvName4         =(TextView)    UView.findViewById(PView,R.id.memberName4);//~9315I~//~9528R~
//        tvAmmount1      =(TextView)    UView.findViewById(PView,R.id.ammountESWN1);//~9315I~//~9528R~
//        tvAmmount2      =(TextView)    UView.findViewById(PView,R.id.ammountESWN2);//~9315I~//~9528R~
//        tvAmmount3      =(TextView)    UView.findViewById(PView,R.id.ammountESWN3);//~9315I~//~9528R~
//        tvAmmount4      =(TextView)    UView.findViewById(PView,R.id.ammountESWN4);//~9315I~//~9528R~
//        tvComp1         =(TextView)    UView.findViewById(PView,R.id.tvComp1);//~9315I~//~9528R~
//        tvComp2         =(TextView)    UView.findViewById(PView,R.id.tvComp2);//~9315I~//~9528R~
//        tvComp3         =(TextView)    UView.findViewById(PView,R.id.tvComp3);//~9315I~//~9528R~
//        tvComp4         =(TextView)    UView.findViewById(PView,R.id.tvComp4);//~9315I~//~9528R~
//        tvEswn1         =(TextView)    UView.findViewById(PView,R.id.nameESWN1);//~9315I~//~9528R~
//        tvEswn2         =(TextView)    UView.findViewById(PView,R.id.nameESWN2);//~9315I~//~9528R~
//        tvEswn3         =(TextView)    UView.findViewById(PView,R.id.nameESWN3);//~9315I~//~9528R~
//        tvEswn4         =(TextView)    UView.findViewById(PView,R.id.nameESWN4);//~9315I~//~9528R~
//                                                                   //~9315I~//~9528R~
//        tvsEswn=new TextView[]{tvEswn1,tvEswn2,tvEswn3,tvEswn4};   //~9315I~//~9528R~
//        tvsAmmount=new TextView[]{tvAmmount1,tvAmmount2,tvAmmount3,tvAmmount4};//~9315I~//~9528R~
//        tvsName=new TextView[]{tvName1,tvName2,tvName3,tvName4};   //~9315I~//~9528R~
//        tvsCompType=new TextView[]{tvComp1,tvComp2,tvComp3,tvComp4};//~9315I~//~9528R~
//        UCheckBox cbCT1 =          new UCheckBox(PView,R.id.cbComp1);//~9315I~//~9528R~
//        UCheckBox cbCT2 =          new UCheckBox(PView,R.id.cbComp2);//~9315I~//~9528R~
//        UCheckBox cbCT3 =          new UCheckBox(PView,R.id.cbComp3);//~9315I~//~9528R~
//        UCheckBox cbCT4 =          new UCheckBox(PView,R.id.cbComp4);//~9315I~//~9528R~
//        cbsCompType=new UCheckBox[]{cbCT1,cbCT2,cbCT3,cbCT4};      //~9315I~//~9528R~
          tvsName=new TextView[PLAYERS];                           //~9528I~
          tvsAmmount=new TextView[PLAYERS];                        //~9528I~
          tvsCompType=new TextView[PLAYERS];                       //~9528I~
          tvsEswn=new TextView[PLAYERS];                           //~9528I~
          tvsSpritPosID=new TextView[PLAYERS];                     //~9531I~
          cbsCompType=new UCheckBox[PLAYERS];                      //~9528I~
		  for (int ii=0;ii<PLAYERS;ii++)                           //~9528I~
          {                                                        //~9528I~
			LinearLayout   ll=(LinearLayout)UView.findViewById(PView,llsCompResultID[ii]);//~9528I~
			tvsName[ii]      =(TextView)    UView.findViewById(ll,R.id.memberName);//~9528I~
			tvsAmmount[ii]   =(TextView)    UView.findViewById(ll,R.id.ammountESWN);//~9528I~
			tvsCompType[ii]  =(TextView)    UView.findViewById(ll,R.id.tvComp);//~9528I~
			tvsEswn[ii]      =(TextView)    UView.findViewById(ll,R.id.nameESWN);//~9528I~
			tvsSpritPosID[ii]=(TextView)    UView.findViewById(ll,R.id.tvSpritPosID);//~9531I~
			cbsCompType[ii]  =new UCheckBox(ll,R.id.cbComp);       //~9528I~
          }                                                        //~9528I~
    }                                                              //~9315I~
    //******************************************                   //~9315I~
    //*from setTotalCompType                                       //~9A12I~
    //******************************************                   //~9A12I~
    private void setCheckBoxResult()                               //~9315I~
    {                                                              //~9315I~
        if (Dump.Y) Dump.println("CompleteDlg.setCheckBoxResult rcv_CompType="+Arrays.toString(rcv_compType)+",resultCompType="+Arrays.toString(resultCompType));//~9316R~
        if (Dump.Y) Dump.println("CompleteDlg.setCheckBoxResult resultCompType="+Arrays.toString(resultCompType)+",swsInvalidByEswn="+Arrays.toString(swsInvalidByEswn));//~9409R~
        if (Dump.Y) Dump.println("CompleteDlg.setCheckBoxResult swsInvalid="+Arrays.toString(swsInvalid));//~0301I~
        for (int ii = 0; ii < PLAYERS; ii++)                       //~9316R~
		{                                                          //~9316I~
        	int tp=resultCompType[ii];                             //~9316R~
            if (tp==COMPTYPE_TAKE || tp==COMPTYPE_RIVER)           //~9316I~
            {                                                      //~9316I~
//                if (swReceived)                                    //~9316R~//~0301R~
//                {                                                  //~9316I~//~0301R~
//                    cbsCompType[ii].setState(swsInvalid[ii],true/*fixed*/);//~9316R~//~0301R~
//                }                                                  //~9316I~//~0301R~
////              else                                               //~9316R~//~0301R~
////                  cbsCompType[ii].setState(swsInvalid[ii]);              //~9315R~//~9316R~//~0301R~
////              else                                               //~9409I~//~9707R~//~0301R~
////                  if (swsInvalidByEswn[ii])                       //~9409I~//~9707R~//~0301R~
////                      cbsCompType[ii].setState(true);            //~9409I~//~9707R~//~0301R~
//                else                                               //~9A12I~//~0301R~
//                {                                                  //~9A12I~//~0301R~
//                    cbsCompType[ii].setState(swsInvalid[ii]);    //~0301R~
//                    if (!cbsCompType[ii].isEnabled())              //~9A12I~//~0301R~
//                    {                                              //~9A12I~//~0301R~
////                      cbsCompType[ii].setState(false); //now enabled//~9A12I~//~0301R~
//                        cbsCompType[ii].setEnabled(true);          //~9A12R~//~0301R~
//                    }                                              //~9A12I~//~0301R~
//                }                                                  //~9A12I~//~0301R~
                  cbsCompType[ii].setState(swsInvalid[ii],!swRequester/*swFixed*/);//~0301I~
            }                                                      //~9316I~
            else                                                   //~9316I~
            {                                                      //~9A12I~
            	cbsCompType[ii].setEnabled(false);                 //~9316I~
            }                                                      //~9A12I~
        }
    }                                                              //~9315I~
    //******************************************                   //~9315I~
    private void setResultInvalid(View Pbtn,boolean Pstatus) //~9315I~
    {                                                              //~9315I~
        if (Dump.Y) Dump.println("CompleteDlg.setResultInvalid status="+Pstatus+",btn="+Pbtn.toString());//~9707I~
        if (Dump.Y) Dump.println("CompleteDlg.setResultInvalid before swsInvalid="+Arrays.toString(swsInvalid));//~9707I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9315I~
        {                                                          //~9315I~
        	if (Dump.Y) Dump.println("CompleteDlg.setResultInvalid checkbox="+cbsCompType[ii].checkbox.toString()+",checked="+Pstatus+",btnb="+Pbtn.toString());//~9315I~
        	if (cbsCompType[ii].checkbox==Pbtn)                    //~9315I~
	        	swsInvalid[ii]=Pstatus;                            //~9315I~
        }                                                          //~9315I~
//      CMP.setInvalidCompletion(swsInvalid);                      //~0227R~
        if (Dump.Y) Dump.println("CompleteDlg.setResultInvalid after swsInvalid="+Arrays.toString(swsInvalid));//~9315R~//~9707R~
    }                                                              //~9315I~
    //******************************************                   //~9212I~
    private void getRuleSetting()                                      //~9212I~
    {                                                              //~9212I~
//    	dupRate=RuleSetting.getDupRate();                          //~9212I~
//  	swRankMUp=RuleSetting.isRankMUp();   //kiriage mangan      //~9212I~//~9224R~
    	swMultiRon=RuleSetting.isMultiRon();                       //~9223I~
    	swMultiRon3Next=RuleSetting.isDrawnHW3R();             //~9223I~
    	swDrawnHW3RCont=RuleSetting.isDrawnHW3RCont();             //~9B29I~
    	cutEswn=ACC.getCutEswn();                                  //~9531I~
    }                                                              //~9212I~
    //******************************************                   //~9212I~
    @Override                                                      //~9314R~
//  private void setupValue()                                      //~9212I~//~9314R~
    protected void setupValueAdditional(View PView)                  //~9314R~
    {                                                              //~9212I~
        if (Dump.Y) Dump.println("CompleteDlg.setupValueAdditionnal");//~9314I~
    	Rect r;                                                    //~9212I~
    	if ((TestOption.option & TestOption.TO_COMPDLG_LAYOUT)!=0)            //~9212I~
        {                                                          //~9212I~
        	setupValueTest();    //TO_COMPDLG_LAYOUT               //~9212I~//~0322R~
            return;                                                //~9212I~
        }                                                          //~9212I~
    //*                                                            //~9212I~
//  	accountNames=ACC.getAccountNames();               //~9212I~//~9227R~//~9416R~
    	accountNames=ACC.getAccountNamesByPosition();              //~9416R~
//  	accountEswn=ACC.getCurrentEswnByAccount();        //~9212R~//~9227R~//~9416R~
    	accountEswn=ACC.getCurrentEswnByPosition();	//Postion seq                //~9416I~//~9519R~
		currentEswn=Accounts.getCurrentEswn();                    //~9225I~//~9227R~
    	r=Status.getGameSeq();                                     //~9212R~
        gameField=r.left;                                          //~9212I~
        gameSeq=r.top;                                             //~9212I~
    	if ((TestOption.option & TestOption.TO_COMPDLG_TEST2)!=0)  //~9409I~
        {                                                          //~9409I~
        	setupValueTest2(); //TO_COMPDLG_TEST2                  //~0322R~
        }                                                          //~9409I~
    }                                                              //~9212I~
//    //******************************************                 //~9315R~
//    @Override                                                    //~9315R~
//    protected int getEswnRequester(int PcurrentEswn)             //~9315R~
//    {                                                            //~9315R~
//        if (eswn1stGainer<0)    //not yet unknown                //~9315R~
//        {                                                        //~9315R~
//            Complete.Status sorted[]=AG.aComplete.sortStatusS();  //list of complete replyAll(OK and NG)//~9315R~
//            if (sorted!=null && sorted.length>0)                 //~9315R~
//                eswn1stGainer=sorted[0].completeEswn;            //~9315R~
//        }                                                        //~9315R~
//        int rc=eswn1stGainer;                                    //~9315R~
//        if (Dump.Y) Dump.println("CompleteDlg.getEswnRequester rc="+rc);//~9315R~
//        return rc;                                               //~9315R~
//    }                                                            //~9315R~
    //******************************************                   //~9212I~
    //*by TestOption                                               //~0322I~
    //******************************************                   //~0322I~
    private void setupValueTest2()                                  //~9212I~//~9409R~
    {                                                              //~9212I~
        if (Dump.Y) Dump.println("CompleteDlg.swtupValueTest2");   //~9409I~
//  	AG.aStatus.gameCtrDup=2;                                   //~9409R~//~9504R~
//        Complete.Status compStat=AG.aComplete.new Status(COMPLETE_RIVER,0/*eswn*/,1/*looser*/,null,null);//~9409R~
//        Status.setCompleteStatus(compStat);                      //~9409R~
//        int[] amt1=new int[]{8000,7700,2000,1000,2,2};           //~9409R~
//        compStat.setAmmount(amt1);                               //~9409R~
//        compStat.setOK(-1,true);                                 //~9409R~
	}                                                              //~9409I~
    //******************************************                   //~9409I~
    //*by TestOption                                               //~0322I~
    //******************************************                   //~0322I~
    private void setupValueTest()                                  //~9409I~
    {                                                              //~9409I~
//        PLS=new Players();                                         //~9409I~//~va66R~
////      new Rule();                                                //~9409I~//~va66R~
////      new Accounts();                                            //~9409I~//~va66R~
////      new Tiles();                                               //~9409I~//~va66R~
////      AG.aTiles.shuffle();                                       //~9409I~//~va66R~
////      AG.aTiles.setInitialDeal();                                //~9409I~//~va66R~
//                                                                   //~9228I~//~va66R~
//        accountNames=new String[]{"あいうえ","かきくけ","さしすせそ","たちつてとなにぬねのまみむめもはひふへほらりるれろわいうえお"};//~9212R~//~9213R~//~va66R~
//        accountEswn=new int[]{3,1,0,2};                            //~9212I~//~va66R~
//        currentEswn=accountEswn[0];                                //~9225I~//~va66R~
//        idxPoint=2;                                                //~9212I~//~va66R~
//        idxRank=2;                                                 //~9212R~//~va66R~
//    //*                                                            //~9212I~//~va66R~
//        gameField=2;                                               //~9212I~//~va66R~
//        gameSeq=3;                                                 //~9212I~//~va66R~
////      gameDup=2;                                                 //~9212I~//~9223R~//~va66R~
////      gameReach=3;                                               //~9212I~//~9223R~//~va66R~
//    //*                                                            //~9212I~//~va66R~
//        new Status();                                              //~9223I~//~va66R~
//        Status.setGameSeq(false,true,false);    //TestOption                               //~9223I~//~9509R~//~9526R~//~0307R~//~va66R~
//        Status.setGameSeq(false,true,false);    //TestOption                               //~9223I~//~9509R~//~9526R~//~0307R~//~va66R~
//        Status.addReach();                                         //~9223I~//~va66R~
//        Status.addReach();                                         //~9223I~//~va66R~
//        Status.addReach();                                         //~9223I~//~va66R~
//        CMP=new Complete();                                            //~9223I~//~9228R~//~va66R~
////      Complete.Status compStat=AG.aComplete.new Status(COMPLETE_TAKEN,1/*eswn*/,0/*looser*/,null,null);//~9223R~//~va66R~
////      Complete.Status compStat=AG.aComplete.new Status(COMPLETE_KAN_ADD,2/*eswn*/,0/*looser*/,null,null);//~9223I~//~va66R~
////      Complete.Status compStat=AG.aComplete.new Status(COMPLETE_KAN_TAKEN_OTHER,2/*eswn*/,0/*looser*/,null,null);//~9223I~//~va66R~
////      Complete.Status compStat=AG.aComplete.new Status(COMPLETE_KAN_TAKEN,2/*eswn*/,0/*looser*/,null,null);//~9223I~//~va66R~
////      Complete.Status compStat=AG.aComplete.new Status(COMPLETE_KAN_RIVER,0/*eswn*/,1/*looser*/,null,null);//~9223I~//~va66R~
//        Complete.Status compStat=AG.aComplete.new Status(COMPLETE_RIVER,0/*eswn*/,1/*looser*/,null,null);//~9223I~//~9224R~//~va66R~
////      Complete.Status compStat=AG.aComplete.new Status(COMPLETE_TAKEN,0/*eswn*/,1/*looser*/,null,null);//~9224R~//~va66R~
//        Complete.Status compStat2=AG.aComplete.new Status(COMPLETE_RIVER,2/*eswn*/,1/*looser*/,null,null);//~9223I~//~9224R~//~va66R~
////      Complete.Status compStat3=AG.aComplete.new Status(COMPLETE_RIVER,3/*eswn*/,1/*looser*/,null,null);//~9223I~//~9224R~//~va66R~
//        Status.setCompleteStatus(compStat);                        //~9223I~//~va66R~
//        Status.setCompleteStatus(compStat2);                       //~9223I~//~9224R~//~va66R~
////      Status.setCompleteStatus(compStat3);                       //~9223I~//~9224R~//~va66R~
////      int[] amt1=new int[]{8000,7700,2000,1000,2,2};             //~9223R~//~9224R~//~va66R~
//        int[] amt1=new int[]{48000,48000,24000,12000,7,10};        //~9224R~//~va66R~
//        int[] amt2=new int[]{32000,32000,16000, 8000,5/*point*/,8/*rank*/};//~9223I~//~9224R~//~va66R~
////      int[] amt3=new int[]{64000,64000,32000,16000,6/*point*/,9/*rank*/};//~9223I~//~9224R~//~va66R~
//        compStat.setAmmount(amt1);                                 //~9223I~//~va66R~
//        compStat2.setAmmount(amt2);                                //~9223I~//~9224R~//~va66R~
////      compStat3.setAmmount(amt3);                                //~9223I~//~9224R~//~va66R~
//        compStat.setOK(-1,true);                                    //~9223I~//~va66R~
//        compStat2.setOK(-1,true);                                   //~9223I~//~9224R~//~va66R~
////      compStat2.setErr(true);                                    //~9223I~//~9224R~//~va66R~
////      compStat3.setOK(-1,true);                                  //~9223I~//~9224R~//~va66R~
//        PLS.setReachDone(1);                                       //~9228I~//~va66R~
//        PLS.setReachDone(3);                                       //~9228I~//~va66R~
    	if (Dump.Y) Dump.println("CompleteDlg.setupValueTest NOP");//~va66I~
    }                                                              //~9212I~
    //******************************************                   //~v@@@I~
    private void setTitle()                                        //~v@@@I~
    {                                                              //~v@@@I~
//      String s=Utils.getStr(TITLEID)+":"+GConst.nameESWN[gameField]+GConst.gameSeq[gameSeq];    //~v@@@I~//~9212R~//~9224R~//~9306R~
        Spanned s=Status.getSpannedGameTitle(Utils.getStr(swRequester?TITLEID_REQ:TITLEID_RESP));//~9306I~//~9314R~
        title=s;                                                   //~9219I~//~9306R~
    	if (Dump.Y) Dump.println("CompleteDlg.setTitle title="+title);//~9306I~//~va66R~
        androidDlg.setTitle(s);                                    //~v@@@R~//~9306R~
    }                                                              //~v@@@I~
    //******************************************                   //~9223I~
    private void setTitle(String Pcmt)                             //~9223I~
    {                                                              //~9223I~
    	if (Dump.Y) Dump.println("CompleteDlg.seTitle cmt="+Pcmt+",title="+title);//~9306I~
//  	Spanned s=Html.fromHtml(AG.resource.getString(R.string.Info_TitleCompType,title,Pcmt));//~va40R~
    	Spanned s=Utils.fromHtml(AG.resource.getString(R.string.Info_TitleCompType,title,Pcmt));//~va40I~
        androidDlg.setTitle(s);                                    //~9223I~
    }                                                              //~9223I~
    //******************************************                   //~9223I~
    protected void setCompType()                                     //~9223I~//~9307R~
    {                                                              //~9223I~
    	String winner,reason;                                      //~9223I~
    	Spanned spannedText;                                       //~9223I~
        boolean swLooser=true;                                     //~9223I~
    //******************************                               //~9223I~
    	if (Dump.Y) Dump.println("CompleteDlg.setCompType");       //~9223I~
    	StringBuffer sb=new StringBuffer();                        //~9223I~
        int ctr=0;                                                 //~9402I~
        sortedStatus=getSortedCompStat();                          //~9707R~
        for (Complete.Status stat:sortedStatus)                    //~9223I~
        {                                                          //~9223I~
	    	if (Dump.Y) Dump.println("CompleteDlg.setCompType swMultiRon="+swMultiRon+",ctrCompStatus="+sortedStatus.length+",ctr="+ctr);//~9402R~
        	if (!swMultiRon && ctr>0)                              //~9402I~
            {                                                      //~9409I~
//          	stat.setInvalid(true);                             //~9402I~//~9409R~
//          	stat.setInvalidByEswn(true);                       //~9409R~
            	;	//setInvalidByEswn(true) is already called     //~9409I~
            }                                                      //~9409I~
            else                                                   //~9402I~
	            setCompTypeWinner(stat.completeType,stat.completeEswn,sb); //~9223I~//~9402R~
            ctr++;                                                 //~9402I~
        }                                                          //~9223I~
        Complete.Status stat=sortedStatus[0];                      //~9223I~
        if (!stat.swTake)                                          //~9223I~
        	sb.append(Utils.getStr(R.string.Info_CompTypeLooser,GConst.nameESWN[stat.completeEswnLooser]));//~9223I~
//      spannedText=Html.fromHtml(sb.toString());                  //~va40R~
        spannedText=Utils.fromHtml(sb.toString());                 //~va40I~
        tvCompType.setText(spannedText);                           //~9223I~
    	if (Dump.Y) Dump.println("CompleteDlg.setCompType  sb="+sb.toString());//~9223I~
    }                                                              //~9223I~
    //******************************************                   //~9223I~
    private void setCompTypeWinner(int PcompType,int Peswn,StringBuffer Psb)//~9223I~
    {                                                              //~9223I~
    	String winner,reason;                                      //~9223I~
    	String txt;                                               //~9223I~
        boolean swLooser=true;                                     //~9223I~
    //******************************                               //~9223I~
    	if (Dump.Y) Dump.println("CompleteDlg.setCompTypeWinner comptype="+PcompType+",eswn="+Peswn);//~9223I~
    	winner=GConst.nameESWN[Peswn];                             //~9223I~
        int compid;                                                //~9223I~
        if ((PcompType & COMPLETE_KAN_TAKEN_OTHER)!=0)	//minkan ron//~9223I~
        	compid=R.string.Info_CompType_Ron_Ankan;               //~9223I~
        else                                                       //~9223I~
        if ((PcompType & COMPLETE_KAN_RIVER)!=0)	//minkan ron   //~9223I~
        	compid=R.string.Info_CompType_Minkan_RinshanKaiho;     //~9223I~
        else                                                       //~9223I~
        if ((PcompType & COMPLETE_KAN_ADD)!=0)	//chankan          //~9223I~
        	compid=R.string.Info_CompType_Ron_Chankan;             //~9223I~
        else                                                       //~9223I~
        if ((PcompType & COMPLETE_KAN_TAKEN)!=0)	//minkan ron   //~9223I~
        {                                                          //~9223I~
            swLooser=false;                                        //~9223I~
        	compid=R.string.Info_CompType_Ankan_RinshanKaiho;      //~9223I~
        }                                                          //~9223I~
        else                                                       //~9223I~
        if ((PcompType & COMPLETE_TAKEN)!=0)                       //~9223I~
        {                                                          //~9223I~
        	compid=R.string.Label_Take;                            //~9223R~
        }                                                          //~9223I~
        else                                                       //~9223I~
        	compid=R.string.Label_Ron;                             //~9223R~
		txt=AG.resource.getString(R.string.Info_CompTypeWinner,winner,Utils.getStr(compid));//~9223I~
        Psb.append(txt);                                           //~9223I~
    	if (Dump.Y) Dump.println("CompleteDlg.setCompTypeWinner txt="+txt);//~9223I~
    }                                                              //~9223I~
    //******************************************                   //~v@@@I~
    protected void setAccountName()                                       //~v@@@I~//~9223R~//~9307R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("CompleteDlg.setAccountName");//~9214I~//~9223R~
    	int idx;                                                   //~9212I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9211I~
        {                                                          //~9211I~
        	tvsName[ii].setText(accountNames[ii]);                 //~9212R~
	        tvsEswn[ii].setText(GConst.nameESWN[accountEswn[ii]]); //~9212R~
	        if (accountEswn[ii]==cutEswn)                          //~9531I~
            {                                                      //~9531I~
	        	tvsSpritPosID[ii].setTextColor(COLOR_SPRITPOS_FG); //~9531I~
	        	tvsSpritPosID[ii].setBackgroundResource(COLOR_SPRITPOS_BG);//~9531I~
	        	tvsSpritPosID[ii].setText(Utils.getStr(R.string.Label_SpritPosID));//~9531I~
            }                                                      //~9531I~
  	        if (swsNG[ii])                                          //~9316I~
	            tvsEswn[ii].setBackgroundColor(COLOR_RESPNG);      //~9316I~
                                                                   //~9316I~
        }                                                          //~9211I~
//      clearSpnComp();                                            //~9211I~//~9315R~
    }                                                              //~v@@@I~
//    //*******************************************************************//~9227I~//~9314R~
//    public  void repaintResultReply()                                    //~9227I~//~9228R~//~9314R~
//    {                                                              //~9227I~//~9314R~
//        int color;                                                 //~9228I~//~9314R~
//        if (Dump.Y) Dump.println("CompleteDlg.repaintResultReply");//~9228R~//~9314R~
//        for (int ii = 0; ii < PLAYERS; ii++)  //TODO             //~9227R~//~9228R~//~9314R~
//        {                                                        //~9227R~//~9228R~//~9314R~
//            int eswn=accountEswn[ii];                            //~9227R~//~9228I~//~9314R~
//            if (eswn==currentEswn)                                 //~9228I~//~9314R~
//                color=COLOR_RESULT_OTHER;                          //~9228I~//~9314R~
//            else                                                   //~9228I~//~9314R~
//            if (ACC.accounts[ii].isDummy())                          //~9228I~//~9314R~
//                color=COLOR_RESULT_OTHER;                          //~9228I~//~9314R~
//            else                                                   //~9228I~//~9314R~
//            {                                                      //~9228I~//~9314R~
//                int stat=CMP.resultOK[eswn];                         //~9227R~//~9228R~//~9314R~
//                color=colorResult[stat];                         //~9227R~//~9228R~//~9314R~
//            }                                                      //~9228I~//~9314R~
//            tvsEswn[ii].setBackgroundColor(color);               //~9227R~//~9228R~//~9314R~
//        }                                                        //~9227R~//~9228R~//~9314R~
//    }                                                              //~9227I~//~9314R~
    //******************************************                   //~9223I~
    //*from getNormalPoint                                         //~9A12I~
    //******************************************                   //~9A12I~
//  private void setTotalCompType(boolean PswInvalid,boolean PswTake,int PcompEswn,int PlooserEswn)//~9223I~//~9228R~//~9409R~//~9519R~
    private void setTotalCompType(boolean PswInvalid,boolean PswTake,int PcompEswn,int PlooserEswn,boolean PswInvalidByEswn)//~9519I~
    {                                                              //~9223I~
    	int idx;                                                   //~9223I~
    	if (Dump.Y) Dump.println("CompleteDlg.setTotalCompType swInvalid="+PswInvalid+",invalidByEswn="+PswInvalidByEswn+",PswTake="+PswTake+",compEswn="+PcompEswn+",looser="+PlooserEswn+",resultCompType="+Arrays.toString(resultCompType));//~9223I~//~9315R~//~9519R~//~9706R~
        idx=eswnToIdx(PcompEswn);                                  //~9223I~
        if (PswInvalid)                                            //~9228I~
        {                                                          //~9228I~
//          spnsComp[idx].selectNoListen(COMPTYPE_INVALID);        //~9228I~//~9315R~
            tvsCompType[idx].setText(strsCompType[COMPTYPE_INVALID]);  //~9315I~
            if (swInitLayout)                                      //~9316I~
            {                                                      //~9316I~
	            resultCompType[idx]=COMPTYPE_INVALID;              //~9316R~
	            swsInvalid[idx]=true;                              //~9316R~
            }                                                      //~9316I~
        }                                                          //~9228I~
        if (PswInvalidByEswn)                                      //~9519I~
        {                                                          //~9519I~
            tvsCompType[idx].setText(strsCompType[COMPTYPE_INVALID_BYESWN]);//~9519I~
			cbsCompType[idx].setState(false); //now enabled        //~9A12I~
            cbsCompType[idx].setEnabled(false);                    //~9A12I~
            if (swInitLayout)                                      //~9519I~
            {                                                      //~9519I~
	            resultCompType[idx]=COMPTYPE_INVALID;              //~9519I~
//              swsInvalid[idx]=true;                              //~9519I~//~9706R~
            }                                                      //~9519I~
        }                                                          //~9519I~
        else                                                       //~9228I~
        {                                                          //~9601I~
            if (PswTake)                                               //~9223R~//~9601R~
            {                                                          //~9223I~//~9601R~
//  //          spnsComp[idx].select(COMPTYPE_TAKE);                   //~9223I~//~9228R~//~9601R~
//  //          spnsComp[idx].selectNoListen(COMPTYPE_TAKE);           //~9228I~//~9315R~//~9601R~
                tvsCompType[idx].setText(strsCompType[COMPTYPE_TAKE]);     //~9315I~//~9601R~
                if (swInitLayout)                                      //~9316I~//~9601R~
                    resultCompType[idx]=COMPTYPE_TAKE;                 //~9316R~//~9601R~
            }                                                          //~9223I~//~9601R~
            else                                                       //~9223I~//~9601R~
            {                                                          //~9223I~//~9601R~
//  //          spnsComp[idx].select(COMPTYPE_RIVER);                  //~9223I~//~9228R~//~9601R~
//  //          spnsComp[idx].selectNoListen(COMPTYPE_RIVER);          //~9228I~//~9315R~//~9601R~
                tvsCompType[idx].setText(strsCompType[COMPTYPE_RIVER]);    //~9315I~//~9601R~
                if (swInitLayout)                                      //~9316I~//~9601R~
                    resultCompType[idx]=COMPTYPE_RIVER;                //~9316R~//~9601R~
                idx=eswnToIdx(PlooserEswn);                            //~9223I~//~9601R~
//  //          spnsComp[idx].select(COMPTYPE_LOOSER);                 //~9223I~//~9228R~//~9601R~
//  //          spnsComp[idx].selectNoListen(COMPTYPE_LOOSER);         //~9228I~//~9315R~//~9601R~
                tvsCompType[idx].setText(strsCompType[COMPTYPE_LOOSER]);   //~9315I~//~9601R~
                if (swInitLayout)                                      //~9316I~//~9601R~
                    resultCompType[idx]=COMPTYPE_LOOSER;               //~9316R~//~9601R~
            }                                                          //~9223I~//~9601R~
        }                                                          //~9601I~
//      setCheckBoxResult();    //after set resultCompType         //~9706I~//~0302R~
    	if (Dump.Y) Dump.println("CompleteDlg.setTotalCompType resultCompType="+Arrays.toString(resultCompType));//~9316I~
    }                                                              //~9223I~
    //******************************************                   //~9601I~
    private void setTotalCompTypePao(boolean PswClear)             //~9601R~
    {                                                              //~9601I~
    	if (Dump.Y) Dump.println("CompleteDlg.setTotalCompTypePao paoGainer="+Arrays.toString(paoGainer)+",paoLooser="+Arrays.toString(paoLooser));//~9601I~//~0302R~
        if (PswClear)                                              //~9601I~
        {                                                          //~9601I~
            for (int ii=0;ii<PLAYERS;ii++)                         //~9601R~
            {                                                      //~9601R~
                if (paoGainer[ii]>=0)    //-1 for non pao gainer  //~9601R~
                {                                                  //~9601R~
                    int looser=paoLooser[ii];                     //~9601R~
                    int idx=eswnToIdx(looser);                     //~9601R~
                    tvsCompType[idx].setText("");                  //~9601R~
                    if (Dump.Y) Dump.println("CompleteDlg.setTotalCompTypePao reset old PAO looserEswn="+looser+",idx="+idx);//~9601R~
                }                                                  //~9601R~
            }                                                      //~9601R~
        }                                                          //~9601I~
        else                                                       //~9601I~
        {                                                          //~9601I~
            for (int ii=0;ii<PLAYERS;ii++)                         //~9601R~
            {                                                      //~9601R~
                if (paoGainer[ii]>=0)    //-1 for non pao gainer   //~9601R~
                {                                                  //~9601R~
                    int looser=paoLooser[ii];                      //~9601R~
                    int idx=eswnToIdx(looser);                     //~9601R~
                    tvsCompType[idx].setText(strsCompType[COMPTYPE_PAOLOOSER]);//~9601R~
                    if (Dump.Y) Dump.println("CompleteDlg.setTotalCompTypePao set PAO looserEswn="+looser+",idx="+idx);//~9601R~
                }                                                  //~9601R~
            }                                                      //~9601R~
        }                                                          //~9601I~
    }                                                              //~9601I~
    //******************************************                   //~9227I~
    private void setResponseStatus(Complete.Status Pstat)          //~9227I~
    {                                                              //~9227I~
    	int idx,compEswn,color=0;                                  //~9227R~
        compEswn=Pstat.completeEswn;                               //~9227I~
        idx=eswnToIdx(compEswn);                                   //~9227I~
    	if (Dump.Y) Dump.println("CompleteDlg.setResponseStatus eswn="+compEswn+",idx="+idx);//~9227I~
        if (Pstat.swErr)                                       //~9227I~//~9228R~
            color=COLOR_RESPERR;                               //~9227I~//~9228R~
        else                                                   //~9227I~//~9228R~
        if (Pstat.swOK)                                            //~9228I~
            color=COLOR_RESPOK;                                    //~9228I~
        else                                                       //~9228I~
        if (Pstat.swNG) //anyone replyed NG(swReplyAll=true)       //~9228R~
        {                                                          //~9608I~
            color=COLOR_RESPNG;                                //~9227I~//~9228R~
            ctrNGCompleteReq++;                                    //~9608I~
        }                                                          //~9608I~
        else                                                       //~9228I~
        	color=COLOR_RESPNONE;                                  //~9228I~
//  	llspnsComp[idx].setBackgroundColor(color);                 //~9227R~//~9315R~
		responseStatus[idx]=color;	//allow update                 //~9227I~
    }                                                              //~9227I~
    //******************************************                   //~9223I~
    private void setTotalAmmount()                                //~9223R~//~9416R~
    {                                                              //~9223I~
    	int idx;                                                   //~9223I~
    	int[][] amtsPayed=new int[PLAYERS][];                      //~9403I~
        Arrays.fill(amtTotal,0);                                   //~9223I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9223I~
        {                                                          //~9223I~
        	if (amtsNormal[ii]!=null)                               //~9223I~
            {                                                      //~9223I~
    			if (Dump.Y) Dump.println("CompleteDlg.setTotalAmmount ii="+ii+",amtsNormal="+Arrays.toString(amtsNormal[ii]));//~9223I~
                amtsPayed[ii]=amtsNormal[ii].clone();              //~9403I~
	        	for (int jj=0;jj<PLAYERS;jj++)                     //~9223I~
                {                                                  //~9403I~
                	amtTotal[jj]+=amtsNormal[ii][jj];                //~9223I~
                }                                                  //~9403I~
    			if (Dump.Y) Dump.println("CompleteDlg.setTotalAmmount ii="+ii+",amtTotal="+Arrays.toString(amtTotal));//~9223I~
            }                                                      //~9223I~
            else                                                   //~9403I~
                amtsPayed[ii]=new int[PLAYERS];                    //~9403I~
        }                                                          //~9223I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9223I~
        {                                                          //~9223I~
			amtTotal[ii]+=amtsError[ii];                            //~9223I~//~9424R~
			amtTotal[ii]+=amtPao[ii];                              //~9223I~
        }                                                          //~9223I~
    	if (Dump.Y) Dump.println("CompleteDlg.setTotalAmmount amtsError="+Arrays.toString(amtsError));//~9223R~//~9424R~
    	if (Dump.Y) Dump.println("CompleteDlg.setTotalAmmount amtPao="+Arrays.toString(amtPao));//~9223I~
    	if (Dump.Y) Dump.println("CompleteDlg.setTotalAmmount amtTotal="+Arrays.toString(amtTotal));//~9223I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9223I~
        {                                                          //~9223I~
        	idx=eswnToIdx(ii);                                     //~9223I~
			tvsAmmount[idx].setText(Integer.toString(amtTotal[ii]));//~9223R~
        }                                                          //~9223I~
        savePayTo(amtsPayed);                                      //~9403I~
    }                                                              //~9403I~
    //******************************************                   //~9403I~
    private void savePayTo(int[][] PamtNormalPay)                  //~9403I~
    {                                                              //~9403I~
    	if (Dump.Y) Dump.println("CompleteDlg.savePayTo");     //~9403I~//~9420R~
        int[][] payss=PamtNormalPay;                               //~9403I~
//      int[][] errss=amtsError;	                               //~9403I~//~9420R~
        int[][] errss=CMP.amtsError;                               //~9420I~
        int[][] paoss=amtsPao;                                     //~9403I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9403I~
        {                                                          //~9403I~
            int[] pays=payss[ii];                                  //~9403I~
            if (Dump.Y) Dump.println("CompleteDlg.saveNormalPay old pay="+Arrays.toString(pays));//~9403I~
    	if (!swNoPayMinusError)	//TODO                             //~9414I~
        {                                                          //~9414I~
            int[] errs=errss[ii];                                  //~9403I~//~9414R~
            if (errs!=null)                                        //~9403I~//~9414R~
            {                                                      //~9403I~//~9414R~
                if (Dump.Y) Dump.println("CompleteDlg.saveNormalPay ii="+ii+",err="+Arrays.toString(errs));//~9403I~//~9414R~
                for (int jj=0;jj<PLAYERS;jj++)                         //~9403I~//~9414R~
                    pays[jj]+=errs[jj];                            //~9403I~//~9414R~
                if (Dump.Y) Dump.println("CompleteDlg.saveNormalPay new pay="+Arrays.toString(pays));//~9403I~//~9414R~
            }                                                      //~9403I~//~9414R~
         }                                                         //~9414I~
        	int[] paos=paoss[ii];                                  //~9403I~
            if (paos!=null)                                        //~9403I~
            {                                                      //~9403I~
                if (Dump.Y) Dump.println("CompleteDlg.saveNormalPay ii="+ii+",pao="+Arrays.toString(paos));//~9403I~
                for (int jj=0;jj<PLAYERS;jj++)                         //~9403I~
                    pays[jj]+=paos[jj];                            //~9403I~
                if (Dump.Y) Dump.println("CompleteDlg.saveNormalPay new pay="+Arrays.toString(pays));//~9403I~
            }                                                      //~9403I~
        }                                                          //~9403I~
		CMP.amtsPayedToEswn=payss;                                 //~9403R~
//  	CMP.amtsError=amtsError;                                   //~9414I~//~9420R~
//  	CMP.amtsErrorByLooser=amtsErrorByLooser;                   //~9414I~//~9420R~
		CMP.swsErrLooser=swsErrLooser;                             //~9414R~
		CMP.swsInvalid=swsInvalid;                                 //~0301I~
		CMP.typeNextGame=typeNextGame;                             //~0301I~
		CMP.paoLooser=paoLooser;                                   //~0302I~
	    saveSuspendRequest();                                      //~0304I~
        if (Dump.Y) Dump.println("CompleteDlg.saveNormalPay swsErrLooser="+Arrays.toString(swsErrLooser));//~0302I~
    }                                                              //~9223I~
    //******************************************                   //~0301I~
    private void saveNextGame()                                    //~0301I~
    {                                                              //~0301I~
    	if (Dump.Y) Dump.println("CompleteDlg.saveNextGame typeNextGame="+typeNextGame);//~0301I~
		CMP.typeNextGame=typeNextGame;                             //~0301I~
    }                                                              //~0301I~
    //******************************************                   //~9320I~
    private void setTotalAmmountReceived()                        //~9320I~//~9416R~
    {                                                              //~9320I~
    	int idx;                                                   //~9320I~
    	if (Dump.Y) Dump.println("CompleteDlg.setTotalAmmountReceived rcv_amtTotal="+Arrays.toString(rcv_amtTotal));//~9320I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9320I~
        {                                                          //~9320I~
        	idx=eswnToIdx(ii);                                     //~9320I~
			tvsAmmount[idx].setText(Integer.toString(rcv_amtTotal[ii]));//~9320I~
        }                                                          //~9320I~
        getErrCompleteReceived();                                  //~9420I~
		CMP.setAmtError(swsErrLooser,amtsError);                    //~9420R~//~9424R~
        savePayToReceived();                                       //~9420I~
    }                                                              //~9320I~
    //******************************************                   //~9212I~
    private void savePayToReceived()                               //~9420I~
    {                                                              //~9420I~
    	if (Dump.Y) Dump.println("CompleteDlg.savePayToReceived"); //~9420I~
//  	CMP.amtsPayedToEswn=payss;                                 //~9420I~
//  	CMP.amtsError=amtsError;                                   //~9420R~
//  	CMP.amtsErrorByLooser=amtsErrorByLooser;                   //~9420R~
		CMP.swsErrLooser=swsErrLooser;                             //~9420I~
		CMP.swsInvalid=swsInvalid;                                 //~0301I~
		CMP.typeNextGame=typeNextGame;                             //~0301I~
		CMP.paoLooser=paoLooser;                                   //~0302I~
	    saveSuspendRequest();                                      //~0304I~
        if (Dump.Y) Dump.println("CompleteDlg.saveToReceived swsErrLooser="+Arrays.toString(swsErrLooser)+",paoLooser="+Arrays.toString(paoLooser));//~0302R~
    }                                                              //~9420I~
    //******************************************                   //~9420I~
    //*currentEswn to Position                                     //~9519I~
    //******************************************                   //~9519I~
    private int eswnToIdx(int Peswn)                                   //~9212I~
    {                                                              //~9212I~
    	int idx=0;                                                 //~9212I~
		for (int ii=0;ii<PLAYERS;ii++)                             //~9212I~
        {                                                          //~9212I~
        	if (accountEswn[ii]==Peswn)                            //~9212I~
            {	                                                   //~9212I~
            	idx=ii;                                            //~9212I~
                break;                                             //~9212I~
            }                                                      //~9212I~
        }                                                          //~9212I~
    	if (Dump.Y) Dump.println("CompleteDlg.eswnToIdx eswn="+Peswn+",idx="+idx);//~9212I~
        return idx;                                                //~9212I~
    }                                                              //~9212I~
//    //******************************************                   //~9211I~//~9315R~
//    private void clearSpnComp()                                    //~9211I~//~9315R~
//    {                                                              //~9211I~//~9315R~
//        if (Dump.Y) Dump.println("CompleteDlg.clearSpnComp");      //~9211I~//~9315R~
//        for (USpinner spn:spnsComp)                                //~9211I~//~9315R~
////          spn.select(0);                                         //~9211I~//~9228R~//~9315R~
//            spn.selectNoListen(0);                                 //~9228I~//~9315R~
//    }                                                              //~9211I~//~9315R~
    //******************************************
    private boolean getNormalPoint(boolean PswInit)                                  //~9212I~//~9223R~//~9409R~
    {
        Arrays.fill(amtsNormal,null);                              //~9223I~
//  	boolean errDetected=false;                                 //~9223I~//~0302R~
    	boolean errDetected=chkErrLooser();  //cbErr to swsErrLooser//~0302I~
    	if (Dump.Y) Dump.println("CompleteDlg.getNormalPoint idxPoint=" + idxPoint + ",idxRank=" + idxRank);//~9214R~//~9223R~
//      Complete.Status[] sorted=CMP.sortStatusS();        //~9222I~//~9227R~//~9310R~
        Complete.Status[] sorted=getSortedCompStat();              //~9310I~
        if (sorted==null)                                          //~9223I~
        {                                                          //~9223I~
			UView.showToast(R.string.Err_DlgCompleteNotOK);        //~9223I~
            return false;                                          //~9223I~
        }                                                          //~9223I~
        sortedStatus=sorted;                                       //~9223I~//~9707R~
        int ctr=sorted.length;                                     //~9222I~
        int ctrOK=0;                                               //~9223I~//~9A12R~
        Arrays.fill(responseStatus,0);                             //~9227I~
        Arrays.fill(swsInvalidByEswn,false);                       //~9706I~
        ctrNGCompleteReq=0;                                        //~9608I~
        int ctr3R=0;                                               //~0107I~
        for (int ii=0;ii<ctr;ii++)                                 //~9222I~
        {                                                          //~9222I~
    	    int[] amt=new int[PLAYERS];                            //~9223I~
		    Arrays.fill(amt,0);                                    //~9223I~
	        Complete.Status stat=sorted[ii];                       //~9223I~
            LinearLayout ll=llGainer[ii];                            //~9222I~//~9223M~
    		if (Dump.Y) Dump.println("CompleteDlg.getNormalPoint ii="+ii+",swReceived="+swReceived+",rcv_compType="+Arrays.toString(rcv_compType)+",completeEswn="+stat.completeEswn+",stat.swErr="+stat.swErr);//~9319R~//~9409R~
    		if (Dump.Y) Dump.println("CompleteDlg.getNormalPoint stat="+stat.toString());//~0227I~
	        int idx=eswnToIdx(stat.completeEswn);                  //~9319I~
//            if (swReceived)                                        //~9319I~//~0302R~
//            {                                                      //~9319I~//~0302R~
////              stat.swErr=rcv_swCompErr && rcv_cbErrState[stat.completeEswn];//~9319I~//~9424R~//~0302R~
////                stat.swErr=rcv_cbErrState[stat.completeEswn];      //~9424I~//~0302R~
//                stat.setErr(rcv_cbErrState[stat.completeEswn]);  //~0302R~
//            }                                                      //~9319I~//~0302R~
            stat.setInvalidByEswn(false);                          //~9706I~
//          stat.setInvalid(false);                                //~9706I~//~0227R~
            boolean swInvalid=swsInvalid[stat.completeEswn];       //~9707I~
    		if (Dump.Y) Dump.println("CompleteDlg.getNormalPoint swsInvalid="+Arrays.toString(swsInvalid));//~9707I~//~0227R~
//          if (stat.swErr)                                        //~9227I~//~0302R~
//            if (isErrLooser(stat))                               //~0302R~
//            {                                                      //~9223I~//~0302R~
//                CompDlgGainer g=new CompDlgGainer(this,ll,ii,stat,swInvalid,amt);//~9223I~//~9707R~//~0302R~
//                widthTileImage=Math.max(widthTileImage,g.widthTileImage);//~9927I~//~0302R~
//                if (Dump.Y) Dump.println("CompleteDlg.getNormalPoint ERR CompDlgGainer out="+Arrays.toString(amt));//~9228I~//~0302R~
//                swTake=stat.swTake;                                //~9223I~//~0302R~
//                ll.setVisibility(View.VISIBLE);                    //~9223I~//~0302R~
//                cbErr[stat.completeEswn].setStatusWithoutCallback(true);//~9223I~//~0302R~
//                errDetected=true;                                   //~9223I~//~0302R~
//                setTotalCompType(false/*cbInvalid*/,swTake,stat.completeEswn,stat.completeEswnLooser,false/*stat.swInvalidByEswn*/);//~0302R~
//            }                                                      //~9223I~//~0302R~
//            else                                                   //~9228I~//~0302R~
//            {                                                      //~9223I~//~9228M~//~0302R~
            	swTake=stat.swTake;                                //~9519I~
//	            int idx=eswnToIdx(stat.completeEswn);              //~9316I~//~9319R~
  	            if (stat.swNG)                                     //~9316I~
                {                                                  //~9316I~
  	                swsNG[idx]=true;    //change background of tvsEswn//~9316I~
                }                                                  //~9316I~
    			if (Dump.Y) Dump.println("CompleteDlg.getNormalPoint swsMultiRon="+swMultiRon+",ctrOK="+ctrOK+",swsInvalid="+swsInvalid[idx]);//~9706I~
                stat.setInvalidByEswn(false);                      //~9707I~
                if (ctrOK>0 && !swMultiRon)                        //~9409I~
                {                                                  //~9409M~
                	stat.setInvalidByEswn(true);                   //~9409M~
                    swsInvalidByEswn[idx]=true;                    //~9409M~
//                  if (PswInit)                                   //~9409I~//~9706R~
//                      swsInvalid[idx]=true;                      //~9409R~//~9706R~
                }                                                  //~9409M~
        		CompDlgGainer g=new CompDlgGainer(this,ll,ctrOK,stat,swInvalid,amt);//~9223R~//~9228M~//~9706R~//~9707R~
                widthTileImage=Math.max(widthTileImage,g.widthTileImage);//~9927I~
    			if (Dump.Y) Dump.println("CompleteDlg.getNormalPoint OK CompDlgGainer out="+Arrays.toString(amt));//~9223I~//~9228M~//~9706R~
//  	    	setTotalCompType(stat.swInvalid,swTake,stat.completeEswn,stat.completeEswnLooser);//~9223I~//~9228R~//~9409R~//~9519R~//~9706R~
//  	    	setTotalCompType(stat.swInvalid,swTake,stat.completeEswn,stat.completeEswnLooser,stat.swInvalidByEswn);//~9519I~//~9706R~
                boolean cbInvalid=cbsCompType[idx].getState();     //~9706I~
    	    	setTotalCompType(cbInvalid,swTake,stat.completeEswn,stat.completeEswnLooser,stat.swInvalidByEswn);//~9706I~
		    	setResponseStatus(stat);                       //~9227I~//~9228M~//~9706R~
		        ll.setVisibility(View.VISIBLE);                        //~9222I~//~9223I~//~9228M~//~9706R~
//	            if (stat.swInvalid)                                 //~9228I~//~9315R~//~9706R~
//	            if (stat.swInvalid || swsInvalid[idx])//~9315I~//~9316R~//~9409R~//~9706R~
//	            if (swsInvalid[idx])                           //~9409I~//~9706R~
  	            if (swsInvalid[idx] || swsInvalidByEswn[idx])      //~9706I~
                {                                                  //~0107I~
                	Arrays.fill(amt,0);                        //~9228I~//~9706R~
  	            	if (swsInvalidByEswn[idx])                     //~0107I~
                    	ctr3R++;                                   //~0107I~
                }                                                  //~0107I~
                else                                               //~9706I~
                	ctrOK++;                                       //~9706R~
		        amtsNormal[stat.completeEswn]=amt;             //~9223I~//~9228M~//~9706M~
			    if (Dump.Y) Dump.println("CompleteDlg.getNormalPoint ron compEswn="+stat.completeEswn+",statInvalid="+stat.swInvalid+",swsInvalid="+Arrays.toString(swsInvalid));//~9316I~//~9706M~
			    if (Dump.Y) Dump.println("CompleteDlg.getNormalPoint amt="+Arrays.toString(amt));//~9403I~//~9706M~
			    if (Dump.Y) Dump.println("CompleteDlg.getNormalPoint swsInvalid="+Arrays.toString(swsInvalid));//~9706I~
//            }                                                      //~9228I~//~0302R~
        }                                                          //~9222I~
    	setTotalCompTypeErr();                                     //~0302I~
        setCheckBoxResult();    //after set resultCompType         //~0302I~
        if ((ctrOK+ctr3R)==3 && swMultiRon3Next)    //for also by eswnSeq//~0107I~
        {                                                          //~0107I~
        	setTitle(Utils.getStr(R.string.Info_MultiRon3Drawn));  //~0107I~
            swDrawn3R=true;                                        //~0107I~
        }                                                          //~0107I~
        if (ctrOK>1)                                               //~9223R~
        {                                                          //~9223I~
//      	if (ctrOK==3 && !swMultiRon3Next)                      //~9409R~//~9702R~
//      	if (ctrOK==3 && swMultiRon3Next)                       //~9702I~//~0107R~
//          {                                                      //~9B29I~//~0107R~
//      		setTitle(Utils.getStr(R.string.Info_MultiRon3Drawn));//~9409I~//~0107R~
//              swDrawn3R=true;                                    //~9B29I~//~0107R~
//          }                                                      //~9B29I~//~0107R~
//          else                                                   //~9409I~//~0107R~
            if (!swDrawn3R)                                        //~0107I~
	        if (swMultiRon)                                        //~9223I~
            {                                                      //~9223I~
            	if (ctrOK==2)                                      //~9223R~
		        	setTitle(Utils.getStr(R.string.Ron2));          //~9223I~
                else                                               //~9223I~
//              if (swMultiRon3Next)                               //~9223I~//~9409R~
//  	        	setTitle(Utils.getStr(R.string.Ron3Next));     //~9223I~//~9409R~
//              else                                               //~9223I~//~9409R~
		        	setTitle(Utils.getStr(R.string.Ron3));          //~9223I~
            }                                                      //~9223I~
            else                                                   //~9223I~
            {                                                      //~9409I~
//          	setTitle(Utils.getStr(R.string.Info_CompType_Shortcut),);//~9409I~
	            setTitle(Utils.getStr(ctrOK==2 ? R.string.Ron2 : R.string.Ron3 )+" "+GConst.nameESWN[sorted[0].completeEswn]+" "+Utils.getStr(R.string.Label_ByEswn));//~9409I~
            }                                                      //~9409I~
        }                                                          //~9223I~
//        if (swReceived)                                            //~9319R~//~0302R~
//        {                                                          //~9319I~//~0302R~
////            if (rcv_swCompErr)                                     //~9319I~//~9320R~//~0302R~
////                setAmmount(UCBP_COMPTYPE_ERROR,true);              //~9319R~//~9320R~//~0302R~
//            ;                                                      //~9320I~//~0302R~
//        }                                                          //~9319I~//~0302R~
//        else                                                       //~9319I~//~0302R~
        if (errDetected)                                            //~9223I~
        {                                                          //~9223I~
//        	cbError.setState(true);                                  //~9223I~//~9424R~
//          swCompError=true;                                      //~9223I~//~9424R~
    		setAmmount(UCBP_COMPTYPE_ERROR,true);                  //~9223I~
    	}                                                          //~9223I~
        return true;                                               //~9223I~
    }
    //************************************************************************//~0302I~
    //*Status withswErr is not on sortedStatus but need to show on resultlist//~0302I~
    //************************************************************************//~0302I~
    protected void setTotalCompTypeErr()                           //~0302I~
    {                                                              //~0302I~
    	if (Dump.Y) Dump.println("CompleteDlg.setTotalCompTypeErr");//~0302I~
        for (int eswn=0;eswn<PLAYERS;eswn++)                           //~0302I~
        {                                                          //~0302I~
        	Complete.Status stat=CMP.getStatus(eswn);              //~0302I~
            if (Dump.Y) Dump.println("CompleteDlg.setCompTypeErr stat="+Utils.toString(stat));//~0302I~
            if (stat==null)                                        //~0302I~
            	continue;                                          //~0302I~
            if (!stat.swErr)                                       //~0302I~
            	continue;                                          //~0302I~
	        int idx=eswnToIdx(stat.completeEswn);                  //~0302I~
            boolean cbInvalid=cbsCompType[idx].getState();         //~0302I~
    	    setTotalCompType(cbInvalid,stat.swTake,stat.completeEswn,stat.completeEswnLooser,stat.swInvalidByEswn);//~0302I~
        }                                                          //~0302I~
    }                                                              //~0302I~
    //******************************************                   //~9228I~
    protected void showReach()                                       //~9228I~//~9307R~
    {                                                              //~9228I~
    	if (Dump.Y) Dump.println("CompleteDlg.showReach");         //~9228I~
//      Complete.Status[] sorted=CMP.sortStatusS();                //~9228I~//~9310R~
        Complete.Status[] sorted=getSortedCompStat();              //~9310I~
        if (sorted==null)                                          //~9B12I~
            return;                                                //~9B12I~
        int ctr=sorted.length;                                     //~9228I~
        int reachctr=0;                                            //~9228I~
        for (int player=0;player<PLAYERS;player++)                //~9228I~
        {                                                          //~9228I~
        	if (PLS.getPosReach(player)<0)                         //~9228I~
            	continue;                                          //~9228I~
	        int eswn=ACC.playerToEswn(player);                          //~9228I~
            boolean swfound=false;                                 //~9228I~
        	for (int jj=0;jj<ctr;jj++)                             //~9228I~
            {                                                      //~9228I~
	        	Complete.Status stat=sorted[jj];                   //~9228I~
	            if (eswn==stat.completeEswn) //shown on complete column//~9228I~
                {                                                  //~9228I~
                	swfound=true;                                  //~9228I~
                	break;                                         //~9228I~
                }                                                  //~9228I~
    		}                                                      //~9228I~
            if (swfound)                                           //~9228I~
            	continue;                                          //~9228I~
            LinearLayout ll=llReachers[reachctr];                  //~9228I~
//      	new CompDlgReacher(this,ll,eswn);                       //~9228I~//~9927R~
        	CompDlgReacher cr=new CompDlgReacher(this,ll,eswn);    //~9927I~
//          widthTileImage=Math.max(widthTileImage,cr.widthTileImage);//~9927I~//~0328R~
			int widthReacherEswn=measureWidth(llReacherEswn);      //~0328I~
			if (Dump.Y) Dump.println("CompleteDlg.showReach widthReacherEswn="+widthReacherEswn+",cr.widthTileImage="+cr.widthTileImage+",widthTileImage="+widthTileImage);//~0328I~
            widthTileImage=Math.max(widthTileImage,cr.widthTileImage+widthReacherEswn);//~0328I~
			if (Dump.Y) Dump.println("CompleteDlg.showReach new widthTileImage="+widthTileImage);//~0328I~
            reachctr++;                                            //~9228I~
        }                                                          //~9228I~
        if (reachctr==0)                                           //~9228I~
        	llllReachers.setVisibility(View.GONE);                 //~9228I~
    }                                                              //~9228I~
//    //******************************************                   //~9228I~//~9315R~
//    private boolean updateCompType(int PviewID,int Ppos)          //~9228I~//~9315R~
//    {                                                            //~9315R~
//        if (Dump.Y) Dump.println("CompleteDlg.updateComptype not found viewID="+Integer.toHexString(PviewID)+",pos="+Ppos);//~9228I~//~9315R~
//        boolean swIgnore=false; //chhanged                         //~9228I~//~9315R~
//        int eswnOfTotalList=-1;                                    //~9228I~//~9315R~
//        USpinner spn=null;                                         //~9228I~//~9315R~
//        for (int ii=0;ii<PLAYERS;ii++)                                 //~9228I~//~9315R~
//        {                                                          //~9228I~//~9315R~
//            if (PviewID== spnsComp[ii].getId())                    //~9228I~//~9315R~
//            {                                                      //~9228I~//~9315R~
//                spn=spnsComp[ii];                                  //~9228I~//~9315R~
//                eswnOfTotalList=accountEswn[ii];                   //~9228I~//~9315R~
//                if (Dump.Y) Dump.println("CompleteDlg.updateComptype idx="+ii);//~9228I~//~9315R~
//                break;                                             //~9228I~//~9315R~
//            }                                                      //~9228I~//~9315R~
//        }                                                          //~9228I~//~9315R~
//        if (eswnOfTotalList<0)                                     //~9228R~//~9315R~
//        {                                                          //~9228I~//~9315R~
//            if (Dump.Y) Dump.println("CompleteDlg.updateComptype not found viewID="+Integer.toHexString(PviewID));//~9228I~//~9315R~
//            return false;                                          //~9228I~//~9315R~
//        }                                                          //~9228I~//~9315R~
//        Complete.Status compStat=null;                             //~9228I~//~9315R~
//        for (Complete.Status stat :sortedStatus)                   //~9228I~//~9315R~
//        {                                                          //~9228I~//~9315R~
//            if (Dump.Y) Dump.println("CompleteDlg.updateComptype completeEswn="+stat.completeEswn+",accntEswn="+eswnOfTotalList);//~9228R~//~9315R~
//            if (stat.completeEswn==eswnOfTotalList)                //~9228I~//~9315R~
//            {                                                      //~9228I~//~9315R~
//                compStat=stat;                                     //~9228I~//~9315R~
//                break;                                             //~9228I~//~9315R~
//            }                                                      //~9228I~//~9315R~
//        }                                                          //~9228I~//~9315R~
//        if (compStat==null)                                        //~9228I~//~9315R~
//        {                                                          //~9228I~//~9315R~
//            if (Dump.Y) Dump.println("CompleteDlg.updateComptype not found eswn="+eswnOfTotalList);//~9228I~//~9315R~
//            return false;                                          //~9228I~//~9315R~
//        }                                                          //~9228I~//~9315R~
//        if (!compStat.swNG)                                        //~9228I~//~9315R~
//        {                                                          //~9228I~//~9315R~
//            if (Dump.Y) Dump.println("CompleteDlg.updateComptype not NG compstat");//~9228I~//~9315R~
//            UView.showToast(R.string.Err_CompTypeChangeIgnoredNotNG);//~9228I~//~9315R~
//            return false;                                          //~9228I~//~9315R~
//        }                                                          //~9228I~//~9315R~
//                                                                   //~9228I~//~9315R~
//        switch(Ppos)                                               //~9228I~//~9315R~
//        {                                                          //~9228I~//~9315R~
//        case 0: //null                                             //~9228I~//~9315R~
//        case 1: //Take                                             //~9228I~//~9315R~
//        case 2: //Ron                                              //~9228I~//~9315R~
//        case 3: //Looser                                           //~9228I~//~9315R~
//            if (compStat.swErr)                                             //~9228I~//~9315R~
//            {                                                      //~9228I~//~9315R~
//                compStat.setErr(false);                                //~9228I~//~9315R~
//                break;                                             //~9228I~//~9315R~
//            }                                                      //~9228I~//~9315R~
//            if (compStat.swInvalid)                                         //~9228I~//~9315R~
//            {                                                      //~9228I~//~9315R~
//                compStat.setInvalid(false);                            //~9228I~//~9315R~
//                break;                                             //~9228I~//~9315R~
//            }                                                      //~9228I~//~9315R~
//            swIgnore=true;                                         //~9228I~//~9315R~
//            break;                                                 //~9228I~//~9315R~
////        case 4: //AbnormalGain                                   //~9228R~//~9315R~
////            compStat.setErr(true);                               //~9228R~//~9315R~
////            break;                                               //~9228R~//~9315R~
////        case 5: //Ignore                                         //~9228R~//~9315R~
//        case 4: //Ignore                                           //~9228I~//~9315R~
//            compStat.setInvalid(true);                                    //~9228I~//~9315R~
//            break;                                                 //~9228I~//~9315R~
//        }                                                          //~9228I~//~9315R~
//        if (Dump.Y) Dump.println("CompleteDlg.updateComptype gainerEswn="+compStat.completeEswn+",swErr="+compStat.swErr+",swInvalid="+compStat.swInvalid+",swIgnore="+swIgnore);//~9228I~//~9315R~
//        if (swIgnore)                                              //~9228I~//~9315R~
//        {                                                          //~9228I~//~9315R~
//            UView.showToast(R.string.Err_CompTypeChangeIgnored);   //~9228I~//~9315R~
//            return false;                                          //~9228I~//~9315R~
//        }                                                          //~9228I~//~9315R~
//        return true;                                               //~9228I~//~9315R~
//    }                                                              //~9228I~//~9315R~
    //******************************************                   //~9213I~
    protected void setAmmount(int PcompTypeSelection,boolean PisChecked)                //~9213I~//~9219R~//~9307R~
    {                                                              //~9213I~
    	if (Dump.Y) Dump.println("CompleteDlg.setAmmount comptype="+PcompTypeSelection+",ischecked="+PisChecked);//~9213I~//~9220R~
    	switch(PcompTypeSelection)                                 //~9213I~
        {                                                          //~9213I~
    	case UCBP_COMPTYPE_NORMAL:                                 //~9219I~
        	if (!PisChecked)                                       //~9219I~
            {                                                      //~9219I~
	            Arrays.fill(amtsNormal,null);                          //~9219I~//~9223I~
                break;                                             //~9219I~
            }                                                      //~9219I~
    		getNormalPoint(false);                                      //~9223I~//~9409R~
        	break;                                                 //~9213I~
        case UCBP_COMPTYPE_ESWN:                                   //~9316I~
	        Arrays.fill(amtsNormal,null);                          //~9316I~
    		getNormalPoint(false);                                      //~9316I~
        	break;                                                 //~9316I~
  		case UCBP_COMPTYPE_ERROR:                                  //~9219I~
	        Arrays.fill(amtsError,0);                           //~9219I~//~9224I~//~9424R~
        	if (!PisChecked)                                       //~9219I~
            {                                                      //~9219I~
                break;                                             //~9219I~
            }                                                      //~9219I~
        	getErrComplete();                                      //~9213R~
//            for (int ii=0;ii<PLAYERS;ii++)                         //~9414I~//~9420R~
//            {                                                      //~9414I~//~9420R~
//                amtsError[ii]=new int[PLAYERS]; //[gainer][payer]  //~9414I~//~9420R~
//                amtsErrorByLooser[ii]=new int[PLAYERS]; //[looser][gainer]//~9414I~//~9420R~
//            }                                                      //~9414I~//~9420R~
//            for (int ii=0;ii<PLAYERS;ii++)                         //~9224I~//~9420R~
//            {                                                      //~9224I~//~9420R~
//                if (swsErrLooser[ii])    //ii erreswn               //~9224I~//~9414R~//~9420R~
//                {                                                  //~9224I~//~9420R~
//                    int a=ii==0 ? POINT_RANKM_DEALER :POINT_RANKM;//~9224I~//~9420R~
////                  amtsError[ii]=new int[PLAYERS];                //~9403I~//~9414R~//~9420R~
//                    for (int jj=0;jj<PLAYERS;jj++)                 //~9224I~//~9420R~
//                    {                                              //~9224I~//~9420R~
//                        if (jj==ii)                                //~9224I~//~9420R~
//                        {                                          //~9403I~//~9420R~
//                            amtsError[jj]+=-a;                      //~9224R~//~9420R~//~9424R~
////                          amtsError[ii][jj]=-a;                  //~9403I~//~9414R~//~9420R~
//                            amtsErrorByLooser[ii][jj]=-a;          //~9414I~//~9420R~
//                            amtsError[jj][ii]=a;                   //~9414R~//~9420R~
//                        }                                          //~9403I~//~9420R~
//                        else                                       //~9224I~//~9420R~
//                            if (ii==0)                              //~9227I~//~9420R~
//                            {                                      //~9403I~//~9420R~
//                                amtsError[jj]+=a/3;                 //~9227I~//~9420R~//~9424R~
////                              amtsError[ii][jj]=a/3;             //~9403I~//~9414R~//~9420R~
//                                amtsErrorByLooser[ii][jj]=a/3;     //~9414I~//~9420R~
//                                amtsError[jj][ii]=-a/3;            //~9414R~//~9420R~
//                            }                                      //~9403I~//~9420R~
//                            else                                   //~9227I~//~9420R~
//                            {                                      //~9227I~//~9420R~
//                                if (jj==0)                             //~9224I~//~9227R~//~9420R~
//                                {                                  //~9403I~//~9420R~
//                                    amtsError[jj]+=a/2;                 //~9224R~//~9227R~//~9420R~//~9424R~
////                                  amtsError[ii][jj]=a/2;         //~9403I~//~9414R~//~9420R~
//                                    amtsErrorByLooser[ii][jj]=a/2; //~9414I~//~9420R~
//                                    amtsError[jj][ii]-=a/2;        //~9414R~//~9420R~
//                                }                                  //~9403I~//~9420R~
//                                else                                   //~9224I~//~9227R~//~9420R~
//                                {                                  //~9403I~//~9420R~
//                                    amtsError[jj]+=a/4;                 //~9224R~//~9227R~//~9420R~//~9424R~
////                                  amtsError[ii][jj]=a/4;         //~9403I~//~9414R~//~9420R~
//                                    amtsErrorByLooser[ii][jj]=a/4; //~9414I~//~9420R~
//                                    amtsError[jj][ii]=-a/4;        //~9414R~//~9420R~
//                                }                                  //~9403I~//~9420R~
//                            }                                      //~9227I~//~9420R~
//                    }                                              //~9224I~//~9420R~
//                    if (Dump.Y) Dump.println("CompleteDlg.setAmmount ii="+ii+",amtsError="+Arrays.toString(amtsError[ii]));//~9403I~//~9420R~
//                    if (Dump.Y) Dump.println("CompleteDlg.setAmmount ii="+ii+",amtsErrorByLooser="+Arrays.toString(amtsErrorByLooser[ii]));//~9414I~//~9420R~
//                }                                                  //~9224I~//~9420R~
//            }                                                      //~9224I~//~9420R~
			CMP.setAmtError(swsErrLooser,amtsError);                //~9420R~//~9424R~
			if (Dump.Y) Dump.println("CompleteDlg.setAmmount amtsError="+Arrays.toString(amtsError));//~9220I~//~9424R~
        	break;                                                 //~9213I~
    	case UCBP_COMPTYPE_PAO_DONE:                               //~9224I~
			if (Dump.Y) Dump.println("CompleteDlg.setAmmount UCBP_COMPTYPE_PAO_DONE");//~9224I~
        	break;                                                 //~9224I~
    	case UCBP_COMPTYPE_PAO:                                    //~9219I~
        	if (!PisChecked)                                       //~9219I~
            {                                                      //~9219I~
	            Arrays.fill(amtPao,0);                             //~9219I~//~9220I~//~9224I~
                break;                                             //~9219I~
            }                                                      //~9219I~
        	getPao();                                              //~9213I~
			if (Dump.Y) Dump.println("CompleteDlg.setAmmount amtPao="+Arrays.toString(amtPao));//~9220R~
        	break;                                                 //~9213I~
        }                                                          //~9213I~
    	setTotalAmmount();                                        //~9223I~//~9416R~
    }                                                              //~9213I~
    //******************************************                   //~9212I~
    //*when checkbox,radiobutton change                            //~9223I~
    //******************************************                   //~9223I~
    private boolean getSetting(int PrbID)                                   //~9212I~//~9213R~
    {                                                              //~9212I~
    	if (Dump.Y) Dump.println("CompleteDlg.getSetting PrbID="+Integer.toHexString(PrbID));//~9213R~
    	if (Dump.Y) Dump.println("CompleteDlg.getSetting idxPoint=" + idxPoint + ",idxRank=" + idxRank);//~9214I~
        boolean rc=true;    //for rb of abnormal player                                      //~9212I~//~9213R~
        switch(PrbID)                                    //~9212I~ //~9213R~
        {                                                          //~9212I~
        case UCBP_COMPTYPE_NORMAL:                                 //~9219I~
//      	rc=getComplete();                                      //~9212I~//~9223R~
            break;                                                 //~9212I~
        case UCBP_COMPTYPE_ERROR:                                  //~9219I~
        	rc=getErrComplete();                                   //~9212I~
            break;                                                 //~9212I~
        case UCBP_COMPTYPE_PAO:                                    //~9219I~
        	rc=getPao();                                           //~9212I~
            break;                                                 //~9212I~
        }                                                          //~9212I~
        return rc;                                                 //~9212I~
    }                                                              //~9212I~
    //******************************************                   //~9212I~
    private boolean getErrComplete()                               //~9212I~
    {                                                              //~9212I~
        swsErrLooser[0]=cbEE.getState();                            //~9219R~//~9414R~
        swsErrLooser[1]=cbES.getState();                            //~9219R~//~9414R~
        swsErrLooser[2]=cbEW.getState();                            //~9219R~//~9414R~
        swsErrLooser[3]=cbEN.getState();                            //~9219R~//~9414R~
        ctrErrLooser=0;                                            //~9219I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9219I~
        	if (swsErrLooser[ii])                                   //~9219I~//~9414R~
            	ctrErrLooser++;                                    //~9219I~
    	if (Dump.Y) Dump.println("CompleteDlg.getErrComplete swsErrLooser="+Arrays.toString(swsErrLooser));//~9219R~//~9414R~
//      if (eswn==-1)                                              //~9213I~//~9219R~
        if (ctrErrLooser==0)                                       //~9219I~
        {                                                          //~9213I~
//          UView.showToast(R.string.Err_DlgCompleteErrLooser);    //~9213I~//~9708R~
        	return false;                                          //~9213I~
        }                                                          //~9213I~
//      errLooserEswn=eswn;                                         //~9212I~//~9213R~//~9219R~
        return true;                                               //~9212I~
    }                                                              //~9212I~
    //******************************************                   //~0302I~
    private boolean chkErrLooser()                                 //~0302I~
    {                                                              //~0302I~
    	boolean rc=false;                                          //~0302I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~0302I~
        	if (swsErrLooser[ii])                                  //~0302I~
            {                                                      //~0302I~
            	rc=true;                                           //~0302I~
            	break;                                             //~0302I~
            }                                                      //~0302I~
    	if (Dump.Y) Dump.println("CompleteDlg.chkErrLooser rc="+rc+",swsErrLooser="+Arrays.toString(swsErrLooser));//~0302I~
        return rc;                                                 //~0302I~
    }                                                              //~0302I~
    //******************************************                   //~9420I~
    private boolean getErrCompleteReceived()                       //~9420I~
    {                                                              //~9420I~
//        swsErrLooser[0]=cbEE.getState();                         //~9420I~
//        swsErrLooser[1]=cbES.getState();                         //~9420I~
//        swsErrLooser[2]=cbEW.getState();                         //~9420I~
//        swsErrLooser[3]=cbEN.getState();                         //~9420I~
        System.arraycopy(rcv_cbErrState,0,swsErrLooser,0,PLAYERS); //~9420I~
        ctrErrLooser=0;                                            //~9420I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9420I~
        	if (swsErrLooser[ii])                                  //~9420I~
            	ctrErrLooser++;                                    //~9420I~
    	setErrCompleteReceived();                                  //~0302R~
    	if (Dump.Y) Dump.println("CompleteDlg.getErrComplete ctrErrLooser="+ctrErrLooser+",swsErrLooser="+Arrays.toString(swsErrLooser));//~9420I~//~9424R~
//        if (ctrErrLooser==0)                                     //~9420I~
//        {                                                        //~9420I~
//            UView.showToast(R.string.Err_DlgCompleteErrLooser);  //~9420I~
//            return false;                                        //~9420I~
//        }                                                        //~9420I~
        return true;                                               //~9420I~
    }                                                              //~9420I~
    //*******************************************************************//~0302I~
    private void setErrCompleteReceived()                          //~0302I~
    {                                                              //~0302I~
        if (Dump.Y) Dump.println("CompleteDlg.setErrCompleteReceived swsErrLooser="+Arrays.toString(swsErrLooser));//~0302I~
        for (int eswn=0;eswn<PLAYERS;eswn++)                       //~0302I~
        {                                                          //~0302I~
        	Complete.Status stat=CMP.getStatus(eswn);              //~0302I~
            if (stat==null)                                        //~0302I~
                continue;                                          //~0302I~
	        if (Dump.Y) Dump.println("CompleteDlg.setErrCompleteReceived stat="+stat.toString());//~0302I~
            stat.setErr(swsErrLooser[eswn]);                       //~0302I~
        }                                                          //~0302I~
    }                                                              //~0302I~
    //*******************************************************************                   //~9212I~//~9224R~
    private boolean getPao()                                       //~9212I~
    {                                                              //~9212I~
    	if (Dump.Y) Dump.println("CompleteDlg.getPao");            //~9224R~
        Arrays.fill(amtPao,0);                                     //~9224I~
        setTotalCompTypePao(true/*swClear*/);                      //~9601I~
    	int ctrPao=chkPao();                                       //~9224I~
    	if (ctrPao==0)                                             //~9224I~
        	return false;                                          //~9224I~
//      if (!swCompPao)                                            //~9224R~
        if (!getPaoEswnStatus())                                   //~9224I~
        {                                                          //~9224I~
//      	resetPaoRadioButton();                                 //~9224I~//~9601R~
        	return false;                                          //~9224I~
        }                                                          //~9224I~
        Arrays.fill(paoGainer,-1);                                 //~9225I~//~9226M~
//      Arrays.fill(paoLooser,-1);                                  //~9225I~//~9226R~//~0302R~
        Arrays.fill(paoRank,0);                                    //~9225I~//~9226M~
        for (int ii=0;ii<ctrPao;ii++)                              //~9224I~
        {                                                          //~9224I~
		    int[] amt=new int[PLAYERS];                            //~9224I~
	        if (!getPaoWinner(ii,amt))                                  //~9224R~//~9428R~
            	continue;                                          //~9428I~
	        int gainer=paoGainer[ii];                              //~9403I~
            amtsPao[gainer]=amt.clone();                           //~9403R~
	    	if (Dump.Y) Dump.println("CompleteDlg.getPao ii="+ii+",gainer="+gainer+",amtsPao="+Arrays.toString(amtsPao[gainer]));//~9403R~
            for (int jj=0;jj<PLAYERS;jj++)                         //~9224I~
            	amtPao[jj]+=amt[jj];                               //~9224I~
        }                                                          //~9224I~
        setTotalCompTypePao(false/*swClear*/);                     //~9601R~
    	if (Dump.Y) Dump.println("CompleteDlg.getPao amtPao="+Arrays.toString(amtPao));//~9213R~//~9220R~
        return true;                                                 //~9212R~
    }                                                              //~9212I~
    //*******************************************************************//~9224I~
    private boolean getPaoWinner(int Ppos,int[] Pamt)              //~9224R~
    {                                                              //~9224I~
        int compEswn,compEswnLooser,eswn;                               //~9225I~
    //***********************************************              //~9225I~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoWinner pos="+Ppos);//~9224R~
        Arrays.fill(Pamt,0);                                         //~9224I~
//      if (!cbsPaoEswn[Ppos].getState())                          //~9224R~//~9601R~
        if (!isPaoActive(Ppos))                                    //~9601I~
        {                                                          //~9224I~
	    	if (Dump.Y) Dump.println("CompleteDlg.getPaoWinner cbstatus off");//~9224I~
        	return false;                                          //~9224I~
        }                                                          //~9224I~
        Complete.Status stat=paoStatus[Ppos];                      //~9224I~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoWinner pao stat completeEswn="+stat.completeEswn+",compEswnLooser="+stat.completeEswnLooser);//~9319I~
        URadioGroup rg=rgsPaoEswn[Ppos];                           //~9224I~//~9225M~
//      USpinner spn=spnsRankPao[Ppos];                            //~9224I~//~9225M~//~9320R~
//    if (swReceived)                                              //~9225I~//~0302R~
      if (!swRequester)                                            //~0302I~
      {                                                            //~9225I~
//    	eswn=rcv_paoLooser[Ppos];                                  //~9225I~//~0302R~
      	eswn=paoLooser[Ppos];                                      //~0302I~
//    	compEswn=rcv_paoGainer[Ppos];                              //~9225I~//~0302R~
        compEswn=stat.completeEswn;                                //~0302I~
//  	compEswnLooser=rcv_paoLooser[Ppos];//~9225I~               //~9531R~
        compEswnLooser=stat.completeEswnLooser;                    //~9531I~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoWinner swRequester="+swRequester+",compEswnLooser="+compEswnLooser);//~9531R~//~0302R~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoWinner rcv_paoLooser="+Arrays.toString(rcv_paoLooser)+",gainer="+Arrays.toString(rcv_paoGainer));//~9531I~
//      spn.select(rcv_paoRank[Ppos]);                               //~9225I~//~9226R~
//      rg.setEnabled(false);                                      //~9225I~//~9226R~
//      spn.setEnabled(false);                                     //~9225I~//~9226R~
      }                                                            //~9225I~
      else                                                         //~9225I~
      {                                                            //~9225I~
        int rbid=rg.getChecked();                                  //~9224I~
        compEswn=stat.completeEswn;                            //~9224I~//~9225R~
        compEswnLooser=stat.completeEswnLooser;                //~9224I~//~9225R~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoWinner idx="+Ppos+",compEswn="+compEswn+",looserEswn="+compEswnLooser);//~9224I~
        eswn=-1;                                               //~9224I~
        switch(rbid)                                               //~9224I~
        {                                                          //~9224I~
        case PAOLOOSER_E:                                          //~9224I~
            eswn=0;                                                //~9224I~
            break;                                                 //~9224I~
        case PAOLOOSER_S:                                          //~9224I~
            eswn=1;                                                //~9224I~
            break;                                                 //~9224I~
        case PAOLOOSER_W:                                          //~9224I~
            eswn=2;                                                //~9224I~
            break;                                                 //~9224I~
        case PAOLOOSER_N:                                          //~9224I~
            eswn=3;                                                //~9224I~
            break;                                                 //~9224I~
        }                                                          //~9224I~
      }                                                            //~9225I~
        if (!swCompNormal)                                         //~9224I~
        {                                                          //~9224I~
            UView.showToast(R.string.Err_DlgPaoNoGainer);          //~9224I~
//      	cbsPaoEswn[Ppos].setState(false);                      //~9320I~//~9601R~
            return false;                                          //~9224I~
        }                                                          //~9224I~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoWinner eswn="+eswn+",compEswn="+compEswn+",compEswnLooser="+compEswnLooser);//~9319I~
        if (eswn==-1 || eswn==compEswn || compEswnLooser==-1 || (!stat.swTake && eswn==compEswnLooser))//~9224R~//~9226R~
        {                                                          //~9224I~
            UView.showToast(R.string.Err_DlgPao);                  //~9224I~
        	rg.resetWithoutListener();                             //~9320R~
            return false;                                          //~9224I~
        }                                                          //~9224I~
//      int rankPao=spn.getSelectedIndex();                        //~9224I~//~9320R~
//      int amt=rankPao==0 ? POINT_RANKM*4 : POINT_RANKM*8;        //~9224I~//~9320R~
        int amt=stat.ammount[CALC_AMT_NETUP];                        //~9320I~//~9602R~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoWinner gainer amt="+amt);//~9320I~
//      if (compEswn==0)                                           //~9224I~//~9320R~
//          amt+=amt/2;                                            //~9224I~//~9320R~
        if (cutEswn>=0)                                            //~9601I~
            getPaoWinnerCutPos(stat,Pamt,eswn,compEswn,compEswnLooser);    //~9601I~//~9602R~
        else                                                       //~9601I~
        if (stat.swTake)                                           //~9224I~
        {                                                          //~9224I~
            if (compEswn==0)    //dealer                           //~9320I~
                for (int ii=0;ii<PLAYERS;ii++)                         //~9224I~//~9320R~
                {                                                      //~9224I~//~9320R~
                    if (ii==eswn)     //pao player                     //~9224I~//~9320R~
                        Pamt[ii]=-amt+amt/3;                           //~9224R~//~9320R~
                    else                                               //~9224I~//~9320R~
                    if (ii!=compEswn) //other not gainer               //~9224I~//~9320R~
                        Pamt[ii]=amt/3;                                //~9224R~//~9320R~
                }                                                      //~9224I~//~9320R~
            else                                                   //~9320I~
                for (int ii=0;ii<PLAYERS;ii++)                     //~9320I~
                {                                                  //~9320I~
                    if (ii==eswn)    //pao player                  //~9320I~
                        if (ii==0)    //dealer                     //~9320R~
                        	Pamt[ii]=-amt+amt/2;                   //~9320I~
                        else                                       //~9320I~
                        	Pamt[ii]=-amt+amt/4;                   //~9320I~
                    else                                           //~9320I~
                    if (ii!=compEswn)                              //~9320I~
                        if (ii==0)    //dealer                     //~9320R~
                        	Pamt[ii]=amt/2;                        //~9320I~
                        else                                       //~9320I~
                        	Pamt[ii]=amt/4;                        //~9320I~
                }                                                  //~9320I~
        }                                                          //~9224I~
        else                                                       //~9224I~
        {                                                          //~9224I~
        	Pamt[compEswnLooser]=amt/2;                            //~9224R~
        	Pamt[eswn]=-amt/2;                                      //~9224I~
        }                                                          //~9224I~
//        if (cutEswn>=0 && cutEswn==eswn)                           //~9531I~//~9601R~
//        {                                                          //~9531I~//~9601R~
//            Pamt[compEswn]+=-Pamt[eswn];                           //~9531I~//~9601R~
//            Pamt[eswn]-=Pamt[eswn];                                //~9531I~//~9601R~
//        }                                                          //~9531I~//~9601R~
        paoGainer[Ppos]=compEswn;                                  //~9225I~
        paoLooser[Ppos]=eswn;                                      //~9225I~
//      paoRank[Ppos]=rankPao;                                     //~9225I~//~9320R~
        paoRank[Ppos]=0;    //not used now                         //~9320I~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoWinner idx="+Ppos+",compEswn="+compEswn+",looserEswn="+compEswnLooser+",amt="+Arrays.toString(Pamt));//~9224I~//~9226R~
        return true;                                               //~9224I~
    }                                                              //~9224I~
    //***************************************************************//~9601I~
    private void getPaoWinnerCutPos(Complete.Status Pstat,int[] Pamt,int PeswnPao,int PeswnComp,int PeswnLooser)//~9601I~//~9602R~
    {                                                              //~9601I~
    	int amtNormal;                                             //~9602I~
        Complete.Status stat=Pstat;                                //~9601I~
//      int amt=stat.ammount[CALC_AMT_NET];                        //~9601I~//~9602R~
        int amt=stat.ammount[CALC_AMT_NETUP];                      //~9602I~
        int amtNet=stat.ammount[CALC_AMT_NET];                     //~9602I~
        int amtDealer=stat.ammount[CALC_AMT_DEALER];               //~9602I~
        int amtNonDealer=stat.ammount[CALC_AMT_NONDEALER];         //~9602I~
        int amtNonDealerCutPos=stat.ammount[CALC_AMT_NONDEALER_CUTPOS];//~9602I~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoWinnerCutPos swTake="+stat.swTake+",cutEswn="+cutEswn+",eswnPao="+PeswnPao+",comp="+PeswnComp+",looser="+PeswnLooser);//~9601I~//~9602R~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoWinnerCutPos amtNet="+amtNet+",amtUp="+amt+",amtDealer="+amtDealer+",amtNonDealer="+amtNonDealer+",amtNonDealerCutPos="+amtNonDealerCutPos);//~9602R~
        int diff=0;                                                //~9602I~
	    if (stat.swTake)	//cutPlayer Take                       //~9602I~
        {                                                          //~9602I~
            for (int ii=0;ii<PLAYERS;ii++)                         //~9602I~
            {                                                      //~9602I~
                if (ii==PeswnComp)                                 //~9602I~
                	continue;                                      //~9602I~
                if (ii==ESWN_E)                                    //~9602I~
	            	Pamt[ii]=amtDealer;        //clear other nondealer responsibility//~9602I~
                else                                               //~9602I~
                if (ii==cutEswn)                                   //~9602I~
	            	Pamt[ii]=amtNonDealerCutPos;        //clear other nondealer responsibility//~9602I~
                else                                               //~9602I~
	            	Pamt[ii]=amtNonDealer;        //clear other nondealer responsibility//~9602I~
            }                                                      //~9602I~
        }                                                          //~9602I~
        else                                                       //~9602I~
        {                                                          //~9602I~
	    	Pamt[PeswnLooser]=amt;                                 //~9602I~
        }                                                          //~9602I~
		amtNormal=amtNet;                                          //~9602I~
		diff=amtNormal-amt;                                        //~9602I~
        if (stat.swTake)    //Take                                 //~9602R~
        {                                                          //~9602I~
            if (PeswnComp==cutEswn||PeswnPao==cutEswn)             //~9602I~
            {                                                      //~9602I~
                Pamt[PeswnPao]-=amtNormal*2;                       //~9602I~
                diff+=amtNormal;                                   //~9602I~
            }                                                      //~9602I~
            else                                                   //~9602I~
            {                                                      //~9602I~
                Pamt[PeswnPao]-=amtNormal;                         //~9602I~
            }                                                      //~9602I~
        }                                                          //~9602I~
        else    //Ron                                              //~9602I~
        {                                                          //~9602I~
            if (PeswnComp==cutEswn)                                //~9602I~
            {                                                      //~9602I~
            	diff+=amtNormal;                                   //~9602I~
                Pamt[PeswnPao]-=amtNormal;                         //~9602I~
                Pamt[PeswnLooser]-=amtNormal;                      //~9602I~
            }                                                      //~9602I~
            else                                                   //~9602I~
            {                                                      //~9602I~
                if (PeswnPao==cutEswn)                             //~9602R~
                {                                                  //~9602R~
                    Pamt[PeswnPao]-=amtNormal/2*2;                 //~9602R~
                    diff+=amtNormal/2;                             //~9602R~
                }                                                  //~9602R~
                else                                               //~9602R~
                    Pamt[PeswnPao]-=amtNormal/2;                   //~9602R~
                if (PeswnLooser==cutEswn)                          //~9602R~
                {                                                  //~9602R~
                    Pamt[PeswnLooser]-=amtNormal/2*2;              //~9602R~
                    diff+=amtNormal/2;                             //~9602R~
                }                                                  //~9602R~
                else                                               //~9602R~
                    Pamt[PeswnLooser]-=amtNormal/2;                //~9602R~
            }                                                      //~9602I~
        }                                                          //~9602I~
//        if (stat.swTake)    //cutPlayer Take                     //~9602R~
//        {                                                        //~9602R~
//            if (PeswnComp==cutEswn)    //comp=dealer:(1+1+1)*2 or comp!=ndealer:(2+1+1)*2//~9602R~
//            {                                                    //~9602R~
//                amtNormal=amt/2;                                 //~9602R~
//                diff=-amt/2;                                     //~9602R~
//            }                                                    //~9602R~
//            else                                                 //~9602R~
//            if (PeswnComp==ESWN_E)     //comp=dealer!=cutPlayer 1+1+2//~9602R~
//            {                                                    //~9602R~
//                amtNormal=amt*3/4;                               //~9602R~
//                diff=-amt/4;                                     //~9602R~
//            }                                                    //~9602R~
//            else                      //comp!=dealer!=cutPlayer  //~9602R~
//            if (cutEswn==ESWN_E)      //dealer=cutPlayer   4+1+1 //~9602R~
//            {                                                    //~9602R~
//                amtNormal=amt*4/6;                               //~9602R~
//                diff=-amt*2/6;                                   //~9602R~
//            }                                                    //~9602R~
//            if (PeswnComp==cutEswn||PeswnPao==cutEswn)           //~9602R~
//            {                                                    //~9602R~
//                Pamt[PeswnPao]+=amtNormal*2;                     //~9602R~
//                diff+=amtNormal;                                 //~9602R~
//            }                                                    //~9602R~
//            else                                                 //~9602R~
//            {                                                    //~9602R~
//                Pamt[PeswnPao]+=amtNormal;                       //~9602R~
//            }                                                    //~9602R~
//        }                                                        //~9602R~
//        else    //Ron                                            //~9602R~
//        {                                                        //~9602R~
//            if (PeswnComp==cutEswn||PeswnLooser==cutEswn)        //~9602R~
//                amtNormal=amt/2;                                 //~9602R~
//            if (PeswnLooser==cutEswn)                            //~9602R~
//                Pamt[PeswnLooser]-=amtNormal*2;                  //~9602R~
//            else                                                 //~9602R~
//                Pamt[PeswnLooser]-=amtNormal;                    //~9602R~
//            if (PeswnPao==cutEswn)                               //~9602R~
//                Pamt[PeswnPao]-=amtNormal*2;                     //~9602R~
//            else                                                 //~9602R~
//                Pamt[PeswnPao]-=amtNormal;                       //~9602R~
//        }                                                        //~9602R~
//        if (PeswnComp==cutEswn)                                  //~9602R~
//        {                                                        //~9602R~
//            if (stat.swTake)    //cutPlayer Take                 //~9602R~
//            {                                                    //~9602R~
//                if (PeswnComp==ESWN_E)  //dealer Take  (1+1+1)*2 //~9602R~
//                {                                                //~9602R~
//                    Pamt[PeswnPao]=amtNonDealer-amt;        //fully responsible//~9602R~
//                    for (int ii=0;ii<PLAYERS;ii++)               //~9602R~
//                    {                                            //~9602R~
//                        if (ii!=PeswnComp && ii!=PeswnPao)       //~9602R~
//                            Pamt[ii]=amtNonDealer;        //clear other nondealer responsibility//~9602R~
//                    }                                            //~9602R~
//                    if (Dump.Y) Dump.println("CompleteDlg.getPaoWinnerCutPos take gainer=cutPlayer==dealer pao="+Pamt[PeswnPao]+",otherNonDealer="+amtNonDealer);//~9602R~
//                }                                                //~9602R~
//                else    //nondealer Take    (2+1+1)*2            //~9602R~
//                {                                                //~9602R~
//                    if (PeswnPao==ENWN_E)                        //~9602I~
//                        Pamt[PeswnPao]=amtDealer-amt;       //fully responsible//~9602R~
//                    else                                         //~9602I~
//                        Pamt[PeswnPao]=amtNonDealer-amt;        //fully responsible//~9602I~
//                    for (int ii=0;ii<PLAYERS;ii++)               //~9602R~
//                    {                                            //~9602R~
//                        if (ii!=PeswnComp && ii!=PeswnPao)       //~9602R~
//                            if (ii==ESWN_E)                      //~9602R~
//                                Pamt[ii]=amtDealer;         //clear dealer responsibility//~9602R~
//                            else                                 //~9602R~
//                                Pamt[ii]=amtNonDealer;      //clear other nondealer responsibility//~9602R~
//                    }                                            //~9602R~
//                    if (Dump.Y) Dump.println("CompleteDlg.getPaoWinnerCutPos take gainer=cutPlayer!=dealer pao="+Pamt[PeswnPao]+",amtDealer="+amtDealer+",otherNonDealer="+amtNonDealer);//~9602R~
//                }                                                //~9602R~
//            }                                                    //~9602R~
//            else    //cutPlayer Ron                              //~9602R~
//            {                                                    //~9602R~
//                Pamt[PeswnPao]=-amt/2;      //fully responsible  //~9602R~
//                Pamt[PeswnLooser]=amt/2;     //fully responsible //~9602R~
//                if (Dump.Y) Dump.println("CompleteDlg.getPaoWinnerCutPos Ron gainer=cutPlayerr pao="+Pamt[PeswnPao]+",amtLooser="+Pamt[PeswnLooser]);//~9602R~
//            }                                                    //~9602R~
//        }                                                        //~9602R~
//        else   //gainer!=cutPlayer                               //~9602R~
//        {                                                        //~9602R~
//            if (stat.swTake)    //NonCutPlayer Take              //~9602R~
//            {                                                    //~9602R~
//                for (int ii=0;ii<PLAYERS;ii++)                   //~9602I~
//                {                                                //~9602I~
//                    if (ii!=PeswnComp)                           //~9602I~
//                    {                                            //~9602I~
//                        if (ii==ESWN_E)                          //~9602I~
//                            Pamt[ii]=amtDealer;        //clear other nondealer responsibility//~9602I~
//                        else                                     //~9602I~
//                        if (ii==cutEswn)                         //~9602I~
//                            Pamt[ii]=amtNonDealerCutPos;        //clear other nondealer responsibility//~9602I~
//                        else                                     //~9602I~
//                            Pamt[ii]=amtNonDealer;        //clear other nondealer responsibility//~9602I~
//                    }                                            //~9602I~
//                }                                                //~9602I~
//                if (PeswnComp==ESWN_E)  //dealer!=cutPlayer Take  1+1+2//~9602R~
//                {                                                //~9602R~
//                    amtNormal=amt*3/4;      //1+1+2-->1+1+1      //~9602R~
//                    diff=amt/4;                                  //~9602R~
//                }                                                //~9602R~
//                else    //nondealer!==cutPlayer Take             //~9602R~
//                {                                                //~9602R~
//                    if (cutEswn==ESWN_E)    //dealer is cut player 4+1+1//~9602R~
//                    {                                            //~9602R~
//                        amtNormal=amt*4/6;      //4+1+1-->2+1+1  //~9602R~
//                        diff=amt*2/6;                            //~9602R~
//                    }                                            //~9602R~
//                    else    //dealer is not cutplayer  2+1+2     //~9602R~
//                    {                                            //~9602R~
//                        amtNormal=amt*4/5;      //2+1+2-->2+1+1  //~9602R~
//                        diff=amt*1/5;                            //~9602R~
//                    }                                            //~9602R~
//                }                                                //~9602R~
//                Pamt[PeswnLooser]=amtDealer-amt;                 //~9602R~
//                if (PeswnPao==cutEswn)                           //~9602R~
//                {                                                //~9602R~
//                    Pamt[PeswnPao]=amt-amtNormal*2;              //~9602R~
//                    diff+=amtNormal;                             //~9602R~
//                }                                                //~9602R~
//                else                                             //~9602R~
//                    Pamt[PeswnPao]=amtNormal;                    //~9602R~
//                for (int ii=0;ii<PLAYERS;ii++)                   //~9602R~
//                {                                                //~9602R~
//                    if (ii!=PeswnComp && ii!=PeswnPao)           //~9602R~
//                        if (PeswnLooser==ESWN_E)                 //~9602R~
//                            Pamt[ii]=amtDealer;        //clear other nondealer responsibility//~9602R~
//                        else                                     //~9602R~
//                        if (PeswnLooser==cutEswn)                //~9602R~
//                            Pamt[ii]=amtNonDealerCutPos;        //clear other nondealer responsibility//~9602R~
//                        else                                     //~9602R~
//                            Pamt[ii]=amtNonDealer;        //clear other nondealer responsibility//~9602R~
//                }                                                //~9602R~
//            }                                                    //~9602R~
//            else    //NonCutPlayer Ron                           //~9602R~
//            {                                                    //~9602R~
//                if (PeswnLooser==cutEswn)   //looser is cutplayer   2//~9602R~
//                {                                                //~9602R~
//                    amtNormal=amt/2;                             //~9602R~
//                    Pamt[PeswnLooser]=amt-amtNormal/2*2;         //~9602R~
//                    Pamt[PeswnPao]=-amtNormal/2;                 //~9602R~
//                    diff=-amtNormal/4;                           //~9602R~
//                    if (Dump.Y) Dump.println("CompleteDlg.getPaoWinnerCutPos Ron gainer!=cutPlayer cutPlayer=looser pao="+Pamt[PeswnPao]+",looser="+Pamt[PeswnLooser]);//~9602R~
//                }                                                //~9602R~
//                else                                             //~9602R~
//                if (PeswnPao==cutEswn)   //paoPlayer is cutPlayer, amt is normal  1//~9602R~
//                {                                                //~9602R~
//                    Pamt[PeswnLooser]=amt/2;                     //~9602R~
//                    Pamt[PeswnPao]=-amt/2*2;                     //~9602R~
//                    diff=amt/2;                                  //~9602R~
//                    if (Dump.Y) Dump.println("CompleteDlg.getPaoWinnerCutPos Ron gainer!=cutPlayer cutPlayer=paoPlayer pao="+Pamt[PeswnPao]+",looser="+Pamt[PeswnLooser]);//~9602R~
//                }                                                //~9602R~
//                else                                             //~9602R~
//                {                                                //~9602R~
//                    Pamt[PeswnLooser]=amt/2;                     //~9602R~
//                    Pamt[PeswnPao]=-amt/2;                       //~9602R~
//                    if (Dump.Y) Dump.println("CompleteDlg.getPaoWinnerCutPos Ron gainer!=cutPlayer cutPlayer=other pao="+Pamt[PeswnPao]+",looser="+Pamt[PeswnLooser]);//~9602R~
//                }                                                //~9602R~
//            }                                                    //~9602R~
//        }                                                        //~9602R~
        Pamt[PeswnComp]=diff;                                       //~9602I~
	    if (Dump.Y) Dump.println("CompleteDlg.getPaoWinnerCutPos compEswn="+Pamt[PeswnComp]+",amtNormal="+amtNormal);//~9602R~
    }                                                              //~9601I~
    //*******************************************************************//~9224I~
    //*set layout to visible when pao winner exist                 //~9224I~
    //*******************************************************************//~9224I~
    private int chkPao()                                           //~9224I~
    {                                                              //~9224I~
    	if (Dump.Y) Dump.println("CompleteDlg.chkPao");            //~9224I~
        Arrays.fill(paoStatus,null);                               //~9224I~
        Complete.Status[] sorted=sortedStatus;                     //~9224I~
        if (sorted==null)                                          //~9224I~
            return 0;                                              //~9224R~
        int ctr=sorted.length;                                     //~9224I~
        int ctrOK=0;                                               //~9224I~
        for (int ii=0;ii<ctr;ii++)                                 //~9224I~
        {                                                          //~9224I~
	        Complete.Status stat=sorted[ii];                       //~9224I~
//          if (stat.swErr)                                        //~9224R~//~0302R~
            if (isErrLooser(stat))                                 //~0302I~
            	continue;                                          //~9224I~
	        int idx=eswnToIdx(stat.completeEswn);                  //~0303I~
  	        if (swsInvalid[idx])                                   //~0303I~
            	continue;                                          //~0303I~
            int idxRank=stat.ammount[CALC_AMT_IDXRANK];    	       //~9224I~
            if (!CompReqDlg.isRank_YM(idxRank))                               //~9224I~
            	continue;                                          //~9224I~
            paoStatus[ctrOK]=stat;                                 //~9224R~
	    	if (Dump.Y) Dump.println("CompleteDlg.chkPao ctrOK="+ctrOK);//~0302I~
            setupPao(stat,ctrOK,llPaos[ctrOK++]);                  //~9224R~
        }                                                          //~9224I~
        for (int ii=ctrOK;ii<llPaos.length;ii++)                   //~9224I~
        {                                                          //~9224I~
        	llPaos[ii].setVisibility(View.GONE);                   //~9224I~
        }                                                          //~9224I~
        llPao.setVisibility(ctrOK!=0 ? View.VISIBLE : View.GONE);  //~9224R~
    	if (Dump.Y) Dump.println("CompleteDlg.chkPao rc=ctrOK="+ctrOK);//~9224R~//~0303R~
        return ctrOK;                                              //~9224R~
    }                                                              //~9224I~
    //*******************************************************************//~9224I~
    private void setupPao(Complete.Status Pstat,int Ppos,LinearLayout PllPao)//~9224I~
    {                                                              //~9224I~
    	if (Dump.Y) Dump.println("CompleteDlg.setupPao pos="+Ppos+",paoLooser="+Arrays.toString(paoLooser));//~9226I~//~0302R~
        PllPao.setVisibility(View.VISIBLE);                        //~9224I~
        TextView tvPaoEswn=(TextView)   UView.findViewById(PllPao,R.id.tvPaoEswn);//~9224R~
        int eswn=Pstat.completeEswn;                                   //~9224I~
        tvPaoEswn.setText(GConst.nameESWN[eswn]);                  //~9224R~
        if (rgsPaoEswn[Ppos]==null)                                //~9224R~
        {                                                          //~9224I~
//          UCheckBox cb=new UCheckBox(PllPao,R.id.cbPaoEswn);     //~9224R~//~9601R~
//         	cbsPaoEswn[Ppos]=cb;                                  //~9224I~//~9601R~
//      	cb.setListener(this,UCBP_PAO);                         //~9224I~//~9601R~
//        	URadioGroup rg=new URadioGroup(PllPao,R.id.rgPaoEswn,UBRGP_PAOESWN);//~9224R~//~9531R~//~0302R~
//        	URadioGroup rg=new URadioGroup(PllPao,R.id.rgPaoEswn,UBRGP_PAOESWN,rbIDsPaoLooser);//~0302R~
        	URadioGroup rg=setURadioGroup(PllPao);               //~9224R~     //~0302R~
        	rgsPaoEswn[Ppos]=rg;                                   //~0302I~
//            if (swReceived)                                        //~9226I~//~0302R~
//            {                                                      //~9226I~//~0302R~
//                int rbid=rcv_paoLooser[Ppos];                      //~9226I~//~0302R~
////              if (rbid>=0)                                       //~9226I~//~9601R~//~0302R~
////                  rbid=rbIDsPaoLooser[rbid];                     //~9226R~//~9601R~//~0302R~
//                rbid=rbIDsPaoLooser[rbid+1];                       //~9601I~//~0302R~
//                rg.setChecked(rbid,true/*swFixed*/);               //~9226R~//~0302R~
//            }                                                      //~9226I~//~0302R~
//            else                                                   //~9601I~//~0302R~
//                rg.setChecked(PAOLOOSER_NO,false/*swFixed*/);      //~9601I~//~0302R~
//          rg.setEnabledButton(rbIDsPaoLooser[eswn],false);       //~9531R~//~9601R~
            int rbid=paoLooser[Ppos];                              //~0302I~
//          if (rbid>=0)                                           //~0302I~//~0303R~
                rg.setCheckedID(rbid+1,!swRequester/*swFixed*/);   //~0302R~
            rg.setEnabledButton(rbIDsPaoLooser[eswn+1],false);     //~9601I~
//          rg.setEnabledButton(rbIDsPaoLooser[Pstat.completeEswnLooser],false);//~9531R~//~9601R~
            rg.setEnabledButton(rbIDsPaoLooser[Pstat.completeEswnLooser+1],false);//~9601I~
//            USpinner spn=new USpinner(PllPao,R.id.spnRankPao);     //~9224R~//~9320R~
//            spn.setArray(R.array.NormalRankPao);                   //~9224R~//~9320R~
//          if (swReceived)                                          //~9226I~//~9320R~
//          {                                                        //~9226I~//~9320R~
//            spn.select(rcv_paoRank[Ppos],true/*swFixed*/);         //~9226I~//~9228R~//~9320R~
//          }                                                        //~9226I~//~9320R~
//          else                                                     //~9226I~//~9320R~
//          {                                                        //~9226I~//~9320R~
////          spn.select(0);                                         //~9224I~//~9228R~//~9320R~
//            spn.selectNoListen(0);                                 //~9228I~//~9320R~
//            spn.setListener(this,USPP_PAO);                        //~9224R~//~9320R~
//          }                                                        //~9226I~//~9320R~
//            spnsRankPao[Ppos]=spn;                                 //~9224R~//~9320R~
        }                                                          //~9224I~
    }                                                              //~9224I~
    //*******************************************************************//~9224I~
    private boolean getPaoEswnStatus()                             //~9224I~
    {                                                              //~9224I~
        if (swReceived)                                            //~9225I~
    		return getPaoEswnStatusReceived();                      //~9225I~
    	boolean rc=false;                                          //~9224I~
//        for (UCheckBox cb:cbsPaoEswn)                              //~9224I~//~9601R~
//        {                                                          //~9224I~//~9601R~
//            if (cb!=null && cb.getState())                         //~9224I~//~9601R~
//            {                                                      //~9224I~//~9601R~
//                rc=true;                                           //~9224I~//~9601R~
//                break;                                             //~9224I~//~9601R~
//            }                                                      //~9224I~//~9601R~
//        }                                                          //~9224I~//~9601R~
        for (URadioGroup rg:rgsPaoEswn)                            //~9601I~
        {                                                          //~9601I~
            if (rg!=null && rg.getChecked()!=PAOLOOSER_NO)         //~9601I~
            {                                                      //~9601I~
                rc=true;                                           //~9601I~
                break;                                             //~9601I~
            }                                                      //~9601I~
        }                                                          //~9601I~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoEswnStatus rc="+rc);//~9224I~
        return rc;                                                 //~9224I~
    }                                                              //~9224I~
    //*******************************************************************//~9601I~
    private boolean isPaoActive(int Pidx)                          //~9601I~
    {                                                              //~9601I~
    	boolean rc=false;                                          //~9601I~
        if (Pidx<rgsPaoEswn.length && rgsPaoEswn[Pidx]!=null)      //~9601I~
            rc=rgsPaoEswn[Pidx].getChecked()!=PAOLOOSER_NO;        //~9601I~
    	if (Dump.Y) Dump.println("CompleteDlg.isPaoActive idx="+Pidx+",rc="+rc);//~9601I~
        return rc;                                                 //~9601I~
    }                                                              //~9601I~
    //*******************************************************************//~9225I~
    private boolean getPaoEswnStatusReceived()                     //~9225I~
    {                                                              //~9225I~
    	boolean rc=false;                                          //~9225I~
        int pos=0;                                                 //~9225I~
//        for (UCheckBox cb:cbsPaoEswn)                              //~9225I~//~9601R~
//        {                                                          //~9225I~//~9601R~
//            if (cb!=null)                                          //~9225I~//~9601R~
//            {                                                      //~9225I~//~9601R~
////              cb.setState(rcv_cbPaoStatus[pos]);                //~9225I~//~9226R~//~9601R~
//                cb.setState(rcv_cbPaoStatus[pos],true/*swFixed*/); //~9226I~//~9601R~
//                if (rcv_cbPaoStatus[pos])                          //~9225I~//~9601R~
//                {                                                  //~9225I~//~9601R~
//                    rc=true;                                       //~9225I~//~9601R~
//                }                                                  //~9225I~//~9601R~
//            }                                                      //~9225I~//~9601R~
//            pos++;                                                 //~9225I~//~9601R~
//        }                                                          //~9225I~//~9601R~
        for (URadioGroup rg:rgsPaoEswn)                             //~9601I~
        {                                                          //~9601I~
            if (rg!=null)                                          //~9601I~
            {                                                      //~9601I~
		        int paoLooserEswn=rcv_paoLooser[pos];              //~9601I~
	        	int rbid=rbIDsPaoLooser[paoLooserEswn+1];          //~9601R~
        		rg.setChecked(rbid,true/*swFixed*/);               //~9601I~
                if (rbid!=PAOLOOSER_NO)                            //~9601I~
                {                                                  //~9601I~
                    rc=true;                                       //~9601I~
                }                                                  //~9601I~
            }                                                      //~9601I~
            pos++;                                                 //~9601I~
        }                                                          //~9601I~
    	if (Dump.Y) Dump.println("CompleteDlg.getPaoEswnStatusReceived rc="+rc);//~9225I~
        return rc;                                                 //~9225I~
    }                                                              //~9225I~
//    //*******************************************************************//~9224I~//~9601R~
//    private void resetPaoRadioButton()                             //~9224I~//~9601R~
//    {                                                              //~9224I~//~9601R~
//        if (Dump.Y) Dump.println("CompleteDlg.resetPaoRadioButton");//~9224I~//~9601R~
//        for (URadioGroup rg:rgsPaoEswn)                            //~9224I~//~9601R~
//        {                                                          //~9224I~//~9601R~
//            if (rg!=null)                                          //~9224I~//~9601R~
//                rg.setChecked(-1);  //clearCheck                   //~9224I~//~9601R~
//        }                                                          //~9224I~//~9601R~
//    }                                                              //~9224I~//~9601R~
    //******************************************                   //~v@@@I~//~9314R~//~9316R~
    @Override                                                      //~v@@@I~//~9314R~//~9316R~
    public void onClickOther(int Pbuttonid)                       //~v@@@R~//~9314R~//~9316R~
    {                                                              //~v@@@I~//~9314R~//~9316R~
        if (Dump.Y) Dump.println("CompleteDlg.onClickOther btnid="+Integer.toHexString(Pbuttonid));//~v@@@R~//~9314R~//~9316R~
        switch(Pbuttonid)                                    //~v@@@R~//~9314R~//~9316R~
        {                                                          //~v@@@R~//~9314R~//~9316R~
            case R.id.FixTotal:                                  //~v@@@R~//~9314R~//~9316R~//~9321R~
                onClickTotal();                            //~v@@@R~//~9227R~//~9314R~//~9316R~
                break;                                             //~v@@@R~//~9314R~//~9316R~
            case R.id.ShowRule:                                    //~9417I~
                onClickShowRule();                                 //~9417I~
                break;                                             //~9417I~
        }                                                          //~v@@@R~//~9314R~//~9316R~
    }                                                              //~v@@@I~//~9314R~//~9316R~
    //*******************************************************      //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onDismissDialog()                                  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("CompleteDlg.onDismissDialog");               //~v@@@I~//~9225R~
        AG.aCompleteDlg=null;                                      //~v@@@I~//~9316R~
    }                                                              //~v@@@I~
    //*******************************************************      //~9316I~
    public void dismissDlg()                                       //~9316I~
    {                                                              //~9316I~
        if (Dump.Y) Dump.println("CompleteDlg.dismissDlg");        //~9316I~
//      AG.aCompleteDlg=null;                                      //~9316R~
        dismiss();                                                 //~9316I~
    }                                                              //~9316I~
    //*******************************************************      //~v@@@I~
    protected void onClickCompleteNG()                             //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("CompleteDlg.onClickCompleteNG");             //~v@@@I~//~9225R~
//      CompleteDlg.newInstance();//~v@@@R~                        //~9225R~
    }                                                              //~v@@@I~
    //******************************************                   //~9227I~
    @Override                                                      //~9227I~
    public void onClickOK()                                        //~9227I~
    {                                                              //~9227I~
        if (Dump.Y) Dump.println("CompleteDlg.onClickOK swRequester="+swRequester);//~9315R~
        if (swRequester)                                           //~9315I~
        {                                                          //~9707I~
        	if (swSuspend)                                         //~0304I~
            {                                                      //~0304I~
            	if (!swConfirmedSuspend || !swConfirmedSuspendOK)  //~0304I~
                {                                                  //~0304I~
            		swConfirmedSuspend=true;                       //~0304I~
                    confirmSuspend();                              //~0304I~
                    return;                                        //~0304I~
                }                                                  //~0304I~
            }                                                      //~0304I~
            else                                                   //~0304I~
            	swConfirmedSuspend=false;                          //~0304I~
	    	getSettingNextGame();	//set typeNextGame from radiogroup//~9707I~
        	if (typeNextGame==NGTP_NEXTPLAYER)                     //~9A12I~
            	if (!continueToNextPlayer(false/*swFixed*/))//~9A12R~//~9A13R~
                	return;                                        //~9A12I~
          if (AG.swTrainingMode)                                   //~va66I~
          {                                                        //+va66I~
	        btnTotal.setEnabled(true/*PswAllOK*/);                 //~va66I~
	        btnOK.setEnabled(false);                               //+va66I~
          }                                                        //+va66I~
          else                                                     //~va66I~
          {                                                        //~va66I~
        	sendRequest();                                         //~9315I~
    		disableFixGame(true);                                  //~9708I~
          }                                                        //~va66I~
            CMP.swSent=true;                                       //~0314I~
        }                                                          //~9707I~
        else                                                       //~9315I~
        {                                                          //~9315I~
        	sendReply(true);                                           //~9227I~//~9315R~
			dismissDlg();                                             //~9315I~//~9316R~
        }                                                          //~9315I~
                                                                   //~9315I~
//      if (swRequester)                                           //~9314I~
//          dismiss();                                                 //~9227I~//~9314R~
    }                                                              //~9227I~
    //******************************************                   //~9227I~
    @Override                                                      //~9314I~
    public void onClickCancel()                                        //~9227I~//~9314R~
    {                                                              //~9227I~
        if (Dump.Y) Dump.println("CompleteDlg.onClickNG");         //~9227I~
        sendReply(false);                                          //~9227I~
        dismissDlg();                                                 //~9227I~//~9314R~//~9315R~//~9316R~
    }                                                              //~9227I~
    //******************************************                   //~9316I~
    public void onClickTotal()                                     //~9316I~
    {                                                              //~9316I~
        if (Dump.Y) Dump.println("CompleteDlg.onClickTotal");      //~9316I~
//      UView.showToast("CalcTotal");                              //~9316R~
        if (swDrawn3R)                                             //~9B29I~
        {                                                          //~9B29I~
        	drawn3R();                                             //~9B29I~
        }                                                          //~9B29I~
        else                                                       //~9B29I~
        {                                                          //~9B29I~
        dismissDlg();                                              //~9316M~
        showTotal();                                               //~9316I~
        }                                                          //~9B29I~
    }                                                              //~9316I~
    //******************************************                   //~9417I~
    public void onClickShowRule()                                  //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("CompleteDlg.onClickShowRule");   //~9417I~
        showRule();                                                //~9417I~
    }                                                              //~9417I~
    //*******************************************************      //~9225I~
//  private void setURadioGroup(View PView)                        //~9220I~//~9224R~
    private URadioGroup setURadioGroup(View PView)                 //~9224I~
    {                                                              //~9220I~
        if (Dump.Y) Dump.println("CompleteDlg.setRGListener");     //~9220I~
//      URadioGroup rg  =              new URadioGroup(PView,R.id.rgPaoEswn,UBRGP_PAOESWN);//~9224I~//~0302R~
        URadioGroup rg=new URadioGroup(PView,R.id.rgPaoEswn,UBRGP_PAOESWN,rbIDsPaoLooser);//~0302I~
        rg.setListener(this);                                      //~9224I~
        return rg;                                                 //~9224I~
    }                                                              //~9220I~
    //*******************************************************      //~9705I~
    //*from initLayout                                             //~9707I~
    //*******************************************************      //~9707I~
    private void setURadioGroupNGTP(View PView)                    //~9705I~
    {                                                              //~9705I~
//      rgNextGame= new URadioGroup(PView,R.id.rgNextGame,UCBP_TYPE_NEXTGAME/*parm*/,rbIDsNGTP);//~9705R~//~9B10R~
        rgNextGame= new URadioGroup(PView,R.id.rgNextGame,UBRGP_TYPE_NEXTGAME/*parm*/,rbIDsNGTP);//~9B10I~
        rgNextGame.setListener(this);                              //~9705I~
        TextView tbLabelNextGame=(TextView)   UView.findViewById(PView,R.id.LabelNextGameWhenErr);//~9705I~
        tbLabelNextGame.setVisibility(View.VISIBLE);               //~9705I~
		cbSuspend=new UCheckBox(PView,R.id.cbSuspend);             //~0304M~
    	enableNextGame();                                          //~9705I~
//      rgNextGame.setVisibility(RBIDNEXT_CONT,View.GONE);         //~9705R~//~9706R~
//      rgNextGame.setVisibility(RBIDNEXT_NEXT,View.GONE);         //~9705R~//~9706R~
    }                                                              //~9705I~
    //*******************************************************      //~9705I~
    private void enableNextGame()                                  //~9705I~
    {                                                              //~9705I~
        boolean swFixed;                                           //~9707I~
    	boolean swEnable=ctrErrLooser!=0 || chkInvalid();                      //~9705I~//~9707R~
        rgNextGame.setEnabled(RBIDNEXT_RESET,swEnable);            //~9705I~
        rgNextGame.setEnabled(RBIDNEXT_NEXTPLAYER,swEnable);      //~0301I~
        typeNextGame=getNGTP(swEnable);                            //~9707R~
        if (swReceived)                                            //~9707R~
            swFixed=true;                                          //~9707I~
        else                                                       //~9707I~
        {                                                          //~9707I~
//            if (typeNextGame==NGTP_CONTINUE)                       //~9707R~//~9709R~
//            {                                                      //~9707R~//~9709R~
//                rgNextGame.setEnabled(RBIDNEXT_CONT,swEnable);     //~9707R~//~9709R~
//                rgNextGame.setEnabled(RBIDNEXT_NEXT,false);        //~9707R~//~9709R~
//            }                                                      //~9707R~//~9709R~
//            else                                                   //~9707R~//~9709R~
//            {                                                      //~9707R~//~9709R~
//                rgNextGame.setEnabled(RBIDNEXT_NEXT,swEnable);     //~9707R~//~9709R~
//                rgNextGame.setEnabled(RBIDNEXT_CONT,false);        //~9707R~//~9709R~
//            }                                                      //~9707R~//~9709R~
            swFixed=!swRequester || !swEnable;                 //~9705I~//~9707R~
        }                                                          //~9707I~
        rgNextGame.setFixed(false);                                //~9707I~
        rgNextGame.setCheckedID(typeNextGame,swFixed);             //~9707M~
		cbSuspend.setState(swSuspend,!swRequester);                //~0304I~
        cbSuspend.setListener(this,UCBP_SUSPEND);                  //~0304I~
        if (swSuspend && !swRequester)                             //~va03I~
        	alertSuspended();                                      //~va03I~
        if (Dump.Y) Dump.println("CompleteDlg.enableNextGame swReceived="+swReceived+",ctrErrLooser="+ctrErrLooser+",swRequester="+swRequester+",swEnable="+swEnable+",swFixed="+swFixed);//~9705I~//~9707R~
    }                                                              //~9705I~
    //*******************************************************      //~9705I~
    private boolean chkInvalid()                                   //~9705I~
    {                                                              //~9705I~
        boolean rc=false;                                          //~9705I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9705I~
        {                                                          //~9705I~
	        if (swsInvalid[ii])                                    //~9705I~
            {                                                      //~9705I~
            	rc=true;                                           //~9705I~
                break;                                             //~9705I~
            }                                                      //~9705I~
        }                                                          //~9705I~
        if (Dump.Y) Dump.println("CompleteDlg.chkInvalid rc="+rc+",swsInvalid="+Arrays.toString(swsInvalid));//~9705I~
        return rc;                                                 //~9705I~
    }                                                              //~9705I~
	//************************************************             //~9707I~
    private int getNGTP(boolean PswInvalid)                        //~9707R~
    {                                                              //~9707I~
	    int ngtp;                                                  //~9707I~
        if (Dump.Y) Dump.println("CompleteDlg.getNGTP typeNextGame="+typeNextGame+",PswInvalid="+PswInvalid+",swReceived="+swReceived+",resultCompType="+Arrays.toString(resultCompType));//~9707R~//~0301R~
        if (Dump.Y) Dump.println("CompleteDlg.getNGTP errLooser="+Arrays.toString(swsErrLooser)+",invalid="+Arrays.toString(swsInvalid));//~9707I~
        if (swReceived)                                            //~9707I~
        	ngtp=rcv_typeNextGame;                                 //~9707I~
        else                                                       //~9707I~
        if (typeNextGame!=-1) 	//reopen case                      //~0301I~
        	ngtp=typeNextGame;                                     //~0301I~
        else                                                       //~0301I~
        if (swDrawn3R)                                           //~9B29I~
        	ngtp=swDrawnHW3RCont?NGTP_CONTINUE:NGTP_NEXT;          //~9B29I~
        else                                                       //~9B29I~
        if (!PswInvalid)	//no Err or Invalid                    //~9707R~
        {                                                          //~9707I~
	        int idx=eswnToIdx(0/*East:dealer*/);                   //~9707I~
    	    int tp=resultCompType[idx]; //dealer comptype          //~9707I~
	        if (Dump.Y) Dump.println("CompleteDlg.getNGTP idx="+idx+",comptype="+tp);//~9707I~
	        ngtp=(tp==COMPTYPE_TAKE || tp==COMPTYPE_RIVER) ? NGTP_CONTINUE :NGTP_NEXT;//~9707I~
        }                                                          //~9707I~
        else                                                       //~9707I~
        	ngtp=chkInvalidComp();                                 //~9707I~
        if (Dump.Y) Dump.println("CompleteDlg.getNGTP ngtp="+ngtp);//~9707I~
        return ngtp;                                               //~9707I~
    }                                                              //~9707I~
	//************************************************             //~9707I~
	//*determin NGTP when err or invalid exist                     //~9707I~
	//************************************************             //~9707I~
    private int chkInvalidComp()                                   //~9707I~
    {                                                              //~9707I~
    	int rc,idx;                                                    //~9707I~//~9708R~
    	int ctrValid=0;                                            //~9707I~
        int eswnValid=-1;                                          //~9707I~
        boolean swInvalidParent=false;                             //~9707I~
	    if (Dump.Y) Dump.println("CompleteDlg.chkValidComp");      //~9707I~//~9708R~
        sortedStatus=getSortedCompStat();                          //~9707R~
	    idx=eswnToIdx(ESWN_E);                                     //~9708I~
        swInvalidParent=swsErrLooser[idx] || swsInvalid[idx];      //~9708I~
        for (Complete.Status stat:sortedStatus)                    //~9707I~
        {                                                          //~9707I~
            int eswn=stat.completeEswn;                            //~9707I~
	        idx=eswnToIdx(eswn);                               //~9707I~//~9708R~
	        if (Dump.Y) Dump.println("CompleteDlg.getValidComp eswnComp="+eswn+",idx="+idx+",swErr="+swsErrLooser[idx]+",swInvalid="+swsInvalid[idx]);//~9707I~
      		if (swsErrLooser[idx] || swsInvalid[idx])              //~9707I~
            {                                                      //~9707I~
//TODO          resultCompType[idx]=COMPTYPE_INVALID;              //~9707I~
			}                                                      //~9707I~
			else                                                   //~9707I~
            {                                                      //~9707I~
            	if (ctrValid==0)                                     //~9707I~
                	eswnValid=eswn;                                //~9707I~
                ctrValid++;                                        //~9707I~
            	if (stat.swTake)                                   //~9707I~
                    resultCompType[idx]=COMPTYPE_TAKE;             //~9707I~
                else                                               //~9707I~
                    resultCompType[idx]=COMPTYPE_RIVER;            //~9707I~
            }                                                      //~9707I~
        }                                                          //~9707I~
        if (ctrValid!=0)                                              //~9707I~
        	rc=eswnValid==ESWN_E ? NGTP_CONTINUE : NGTP_NEXT;      //~9707I~
        else                                                       //~9707I~
        	rc=!swInvalidParent ? NGTP_CONTINUE : NGTP_NEXT;       //~9707I~
	    if (Dump.Y) Dump.println("CompleteDlg.chkValidComp rc="+rc+",ctrValid="+ctrValid+",eswnValid="+eswnValid+",swInvalidParent="+swInvalidParent);//~9707I~//~9708R~
        return rc;                                                 //~9707I~
    }                                                              //~9707I~
    //*******************************************************      //~9705I~
    protected boolean getSettingNextGame()                         //~9705I~
    {                                                              //~9705I~
        typeNextGame=rgNextGame.getCheckedID();                    //~9705I~
        boolean rc=typeNextGame!=-1;                               //~9705I~
		swSuspend=cbSuspend.getState();                            //~0304I~
        if (Dump.Y) Dump.println("CompleteDlg.getSettingNextGame rc="+rc+",typeNextGame="+typeNextGame+",swSuspend="+swSuspend);//~9705I~//~0304R~
        return rc;                                                 //~9705I~
    }                                                              //~9705I~
    //*******************************************************      //~9213I~
    private void setUCheckBox(View PView)                        //~9213I~//~9214R~
    {                                                              //~9213I~
        if (Dump.Y) Dump.println("CompleteDlg.setUCheckBox");    //~9213I~//~9225R~
//      cbNormal        =          new UCheckBox(PView,R.id.cbNormalGain);//~9213I~//~9214M~//~9219R~//~9320R~
//      cbError         =          new UCheckBox(PView,R.id.cbAbnormalGain);//~9219I~//~9424R~
        cbEE            =          new UCheckBox(PView,R.id.cbAbPE);//~v@@@R~//~9213M~//~9219R~
        cbES            =          new UCheckBox(PView,R.id.cbAbPS);//~9219I~
        cbEW            =          new UCheckBox(PView,R.id.cbAbPW);//~9219I~
        cbEN            =          new UCheckBox(PView,R.id.cbAbPN);//~9219I~
        cbErr=new UCheckBox[]{cbEE,cbES,cbEW,cbEN};                //~9223I~
        if (swReceived)                                                           //~9219I~//~9225R~
        	setUCheckBoxReceived(PView);                           //~9225I~
        else                                                       //~9225I~
        {                                                          //~9225I~
//      	cbNormal.setState(swCompNormal);                           //~9219I~//~9225R~//~9320R~
//      	cbError.setState(swCompError);                             //~9219I~//~9225R~//~9424R~
        	setUCheckBoxAtOpen(PView);                             //~0301I~
        }                                                          //~9225I~
    }                                                              //~9223I~
    //*******************************************************      //~9225I~
    private void setUCheckBoxReceived(View PView)                  //~9225I~
    {                                                              //~9225I~
        if (Dump.Y) Dump.println("CompleteDlg.setUCheckBoxReceived");//~9225I~
//      swCompNormal=rcv_swCompNormal;                             //~9225I~//~0304R~
        swSuspend=rcv_swSuspend;                                   //~0304I~
//      swCompError=rcv_swCompErr;                                   //~9225I~//~9424R~
        int pos=0;
        for (UCheckBox cb:cbErr)                                   //~9225I~
        {                                                          //~9225I~
//      	cb.setState(rcv_cbErrState[pos]);                        //~9225I~//~9226R~
        	cb.setState(rcv_cbErrState[pos],true/*swFixed*/);       //~9226I~//~0301R~
//          cb.setEnabled(false);                                  //~9226R~
            pos++;//~9225I~
        }                                                          //~9225I~
//      cbNormal.setState(swCompNormal,true/*swFixed*/);                           //~9225I~//~9226R~//~9320R~
//      cbError.setState(swCompError,true/*swFixed*/);                             //~9225I~//~9226R~//~9424R~
//      cbNormal.setEnabled(false);                                //~9225I~//~9226R~
//      cbError.setEnabled(false);                                 //~9225I~//~9226R~
    }                                                              //~9225I~
    //*******************************************************      //~0301I~
    //*from CompRqDlg or Menu at reopen                            //~0301I~
    //*******************************************************      //~0301I~
    private void setUCheckBoxAtOpen(View PView)                    //~0301I~
    {                                                              //~0301I~
        if (Dump.Y) Dump.println("CompleteDlg.setUCheckBoxAtOpen swRequester="+swRequester+",swsErrLooser="+Arrays.toString(swsErrLooser));//~0301I~
        int pos=0;                                                 //~0301I~
        for (UCheckBox cb:cbErr)                                   //~0301I~
        {                                                          //~0301I~
        	cb.setState(swsErrLooser[pos],!swRequester/*swFixed*/);//~0301R~
            pos++;                                                 //~0301I~
        }                                                          //~0301I~
    }                                                              //~0301I~
    //*******************************************************      //~9223I~
    private void setUCheckBoxListener()                             //~9223I~//~9228R~
    {                                                              //~9223I~
        if (Dump.Y) Dump.println("CompleteDlg.setUCheckBoxListener");//~9228I~
//      cbNormal.setListener(this,UCBP_COMPTYPE_NORMAL);           //~9219I~//~9320R~
//      cbError.setListener(this,UCBP_COMPTYPE_ERROR);             //~9219I~//~9424R~
        cbEE.setListener(this,UCBP_ERROR);                         //~9219I~
        cbES.setListener(this,UCBP_ERROR);                         //~9219I~
        cbEW.setListener(this,UCBP_ERROR);                         //~9219I~
        cbEN.setListener(this,UCBP_ERROR);                         //~9219I~
                                                                   //~9315I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9315I~
        	cbsCompType[ii].setListener(this,UCBP_COMPTYPE_ESWN);  //~9315I~
                                                                   //~9219I~
    }                                                              //~9213I~
    //*******************************************************      //~9213I~
    //*UCheckBoxI                                                  //~9214I~
    //*******************************************************      //~9214I~
    @Override                                                      //~9214I~
    public void onChangedUCB(CompoundButton Pbtn, boolean PisChecked,int Pparm)//~9213I~//~9214R~
    {                                                              //~9213I~
//    	boolean swpao;                                             //~9224I~
        if (Dump.Y) Dump.println("CompleteDlg.onChangedUCB parm="+Pparm+",isChecked="+PisChecked);//~9213I~//~9214R~//~9219R~
        if (swInitLayout)                                          //~9214I~
        	return;                                                //~9214I~
        switch(Pparm)                                              //~9214I~
        {                                                          //~9214I~
//        case UCBP_COMPTYPE_NORMAL:                                 //~9219I~//~9320R~
//            swCompNormal=PisChecked;                               //~9219I~//~9320R~
//            if (swCompNormal)                                      //~9219I~//~9320R~
//            setAmmount(Pparm,swCompNormal);                        //~9219R~//~9320R~
//            break;                                                 //~9219I~//~9320R~
        case UCBP_COMPTYPE_ESWN:                                   //~9315I~
        	setResultInvalid(Pbtn,PisChecked);                     //~9315I~
    		enableNextGame();                                      //~9705I~
//  		disableFixGame(false);                                 //~9708I~//~0314R~
    		setAmmount(UCBP_COMPTYPE_PAO,true);    //set invalid may affect to pao//~0303I~
        	if (swCompNormal)                                      //~9315I~
            	setAmmount(Pparm,swCompNormal);                    //~9315I~
            break;                                                 //~9315I~
//      case UCBP_COMPTYPE_ERROR:                                  //~9219I~//~9424R~
//          swCompError=PisChecked;                                  //~9219I~//~9223R~//~9424R~
//  		errChanged();                                          //~9223I~//~9424R~
//          break;                                                 //~9219I~//~9424R~
    	case UCBP_ERROR:	//error eswn chkbox                    //~9223R~
			errChanged();                                          //~9223I~
    		enableNextGame();                                      //~9705I~
//  		disableFixGame(false);                                 //~9708I~//~0314R~
        	break;                                                 //~9223I~
//  	case UCBP_PAO:                                             //~9219I~//~9601R~
//   	        getSetting(UCBP_COMPTYPE_PAO);                     //~9224I~//~9601R~
//		        setAmmount(UCBP_COMPTYPE_PAO_DONE,true);          //~9224I~//~9601R~
//        	break;                                                 //~9214I~
//        case UCBP_TYPE_NEXTGAME:                                   //~9705I~//~9B10R~
//            getSettingNextGame();                                  //~9705I~//~9B10R~
//            disableFixGame(false);                               //~9B10R~
//            break;                                                 //~9705I~//~9B10R~
        case UCBP_SUSPEND:                                         //~0304I~
            swSuspend=PisChecked;                                  //~0304I~
            saveSuspendRequest();                                  //~0304I~
          	break;                                                 //~0304I~
        }                                                          //~9214I~
        CMP.swSent=false;                                          //~0314I~
   		disableFixGame(true/*PswResetResponse*/);                  //~0314I~
    }                                                              //~9213I~
    //*******************************************************      //~0304I~
    private void saveSuspendRequest()                              //~0304I~
    {                                                              //~0304I~
	    if (Dump.Y) Dump.println("CompleteDlg.saveSuspendRequest swSuspend="+swSuspend);//~0304I~
		CMP.swSuspend=swSuspend;                                   //~0304I~
		AG.aStatus.setSuspendRequest(swSuspend);                   //~0304I~
    }                                                              //~0304I~
    //*******************************************************      //~9223I~
    private void errChanged()                                      //~9223I~
    {                                                              //~9223I~
	    if (Dump.Y) Dump.println("CompleteDlg.errChanged");        //~9223I~
    	boolean changed=false;                                     //~9223I~
//        for (Complete.Status stat:sortedStatus)                    //~9223I~//~0302R~
//        {                                                          //~9223I~//~0302R~
////          boolean swErr=stat.swErr;                              //~9223I~//~0302R~
//            int eswn=stat.completeEswn;                            //~9223I~//~0302R~
//            boolean swErr=swsErrLooser[eswn];                    //~0302R~
////          boolean swErrCB=swCompError && cbErr[eswn].getState(); //~9223I~//~9424R~//~0302R~
//            boolean swErrCB=cbErr[eswn].getState();                //~9424I~//~0302R~
//            if (Dump.Y) Dump.println("CompleteDlg.errChanged eswn="+eswn+",old="+swErr+",stat="+cbErr[eswn].getState()+",new="+swErrCB);//~9223R~//~9424R~//~0302R~
//            if (swErr!=swErrCB)                                    //~9223I~//~0302R~
//            {                                                      //~9223I~//~0302R~
//                stat.setErr(swErrCB);   set at fixed               //~9223I~//~0302R~
//                changed=true;                                      //~9223I~//~0302R~
//            }                                                      //~9223I~//~0302R~
//        }                                                          //~9223I~//~0302R~
        for (int eswn=0;eswn<PLAYERS;eswn++)                           //~0302I~
        {                                                          //~0302I~
        	Complete.Status stat=CMP.getStatus(eswn);              //~0302I~
            if (stat==null)                                        //~0302I~
                continue;                                          //~0302I~
            boolean swErr=swsErrLooser[eswn];                      //~0302I~
            boolean swErrCB=cbErr[eswn].getState();                //~0302I~
            if (Dump.Y) Dump.println("CompleteDlg.errChanged eswn="+eswn+",old="+swErr+",stat="+cbErr[eswn].getState()+",new="+swErrCB);//~0302I~
            if (swErr!=swErrCB)                                    //~0302I~
            {                                                      //~0302I~
                stat.setErr(swErrCB);   //                         //~0302I~
                changed=true;                                      //~0302I~
            }                                                      //~0302I~
        }                                                          //~0302I~
      	boolean swErr=getErrComplete();  //cbErr to swsErrLooser                          //~9424I~//~9705R~//~9707R~
        if (Dump.Y) Dump.println("CompleteDlg.errChanged changed="+changed+",swErr="+swErr);//~9223I~//~9424R~
//  	setAmmount(UCBP_COMPTYPE_ERROR,swCompError);                      //~9223R~//~9224R~//~9424R~
    	setAmmount(UCBP_COMPTYPE_ERROR,swErr);                     //~9424I~
        if (changed)                                               //~9223I~
        {                                                          //~9601I~
//  		setAmmount(UCBP_COMPTYPE_PAO,false);   //once clear amtPao then when err changed//~9601I~//~9603R~
    		setAmmount(UCBP_COMPTYPE_PAO,true);    //set err may affect to pao//~9603I~
    		setAmmount(UCBP_COMPTYPE_NORMAL,true);                 //~9223I~//~9224R~
        }                                                          //~9601I~
    }                                                              //~9223I~
    //*******************************************************      //~9214I~
    private void setUSpinner(View PView)                            //~9214I~
    {                                                              //~9214I~
        if (Dump.Y) Dump.println("CompleteDlg.setUSpinner");      //~9214I~//~9228R~
//        spnComp1        =          new USpinner(PView,R.id.spnComp1);//~v@@@I~//~9211R~//~9212R~//~9214M~//~9315R~
//        spnComp2        =          new USpinner(PView,R.id.spnComp2);//~v@@@I~//~9211R~//~9212R~//~9214M~//~9315R~
//        spnComp3        =          new USpinner(PView,R.id.spnComp3);//~v@@@I~//~9211R~//~9212R~//~9214M~//~9315R~
//        spnComp4        =          new USpinner(PView,R.id.spnComp4);//~v@@@I~//~9211R~//~9212R~//~9214M~//~9315R~
//        llspnComp1      =(LinearLayout)UView.findViewById(PView,R.id.llspnComp1);//~9228I~//~9315R~
//        llspnComp2      =(LinearLayout)UView.findViewById(PView,R.id.llspnComp2);//~9228I~//~9315R~
//        llspnComp3      =(LinearLayout)UView.findViewById(PView,R.id.llspnComp3);//~9228I~//~9315R~
//        llspnComp4      =(LinearLayout)UView.findViewById(PView,R.id.llspnComp4);//~9228I~//~9315R~
                                                                   //~9214I~
        spnPoint        =          new USpinner(PView,R.id.spnPoint);//~v@@@I~//~9214M~
        spnRank         =          new USpinner(PView,R.id.spnRank );//~v@@@I~//~9214M~
//      spnRankPao      =          new USpinner(PView,R.id.spnRankPao);//~v@@@I~//~9214M~//~9224R~
                                                                   //~9214I~
//        spnComp1.setArray(R.array.CompType);                       //~v@@@I~//~9212R~//~9214M~//~9315R~
//        spnComp2.setArray(R.array.CompType);                       //~v@@@I~//~9211R~//~9212R~//~9214M~//~9315R~
//        spnComp3.setArray(R.array.CompType);                       //~v@@@I~//~9211R~//~9212R~//~9214M~//~9315R~
//        spnComp4.setArray(R.array.CompType);                       //~v@@@I~//~9211R~//~9212R~//~9214M~//~9315R~
//        spnsComp=new USpinner[]{spnComp1,spnComp2,spnComp3,spnComp4};//~9213R~//~9214M~//~9315R~
//      llspnsComp=new LinearLayout[]{llspnComp1,llspnComp2,llspnComp3,llspnComp4};//~9227I~//~9315R~
        spnPoint.setArray(R.array.NormalPoint);                    //~v@@@I~//~9214M~
        spnRank.setArray(R.array.NormalRank);                      //~v@@@I~//~9214M~
//        if (swReceived)                                            //~9225I~//~9315R~
//            setUSpinnerReceived(PView);                            //~9225I~//~9315R~
	}                                                                  //~9214I~//~9223I~
//    //*******************************************************      //~9223I~//~9315R~
//    private void setUSpinnerReceived(View PView)                   //~9225I~//~9315R~
//    {                                                              //~9225I~//~9315R~
//        if (Dump.Y) Dump.println("CompleteDlg.setUSistenerReceived");//~9225I~//~9315R~
//        int pos=0;                                                 //~9226I~//~9315R~
//        for (USpinner spn:spnsComp)                                //~9226I~//~9315R~
//        {                                                          //~9226I~//~9315R~
//            spn.select(rcv_compType[pos++],true/*swFixed*/);       //~9226I~//~9228R~//~9315R~
//        }                                                          //~9226I~//~9315R~
//    }                                                              //~9225I~//~9315R~
    //*******************************************************      //~9225I~
    private void setUSpinnerListener()                              //~9223I~//~9228R~
    {                                                              //~9223I~
        if (Dump.Y) Dump.println("CompleteDlg.setUSpinnerListener");//~9228R~
//        spnComp1.setListener(this,USPP_COMPTYPE);                   //~9214I~//~9315R~
//        spnComp2.setListener(this,USPP_COMPTYPE);                   //~9214I~//~9315R~
//        spnComp3.setListener(this,USPP_COMPTYPE);                   //~9214I~//~9315R~
//        spnComp4.setListener(this,USPP_COMPTYPE);                   //~9214I~//~9315R~
                                                                   //~9214I~
        spnPoint.setListener(this,USPP_POINT);                      //~9214I~
        spnRank.setListener(this,USPP_RANK);                        //~9214I~
                                                                   //~9214I~
    }                                                              //~9214I~
    //*******************************************************      //~9220I~
    //*URadioGroupI                                                //~9220I~
    //*******************************************************      //~9220I~
    @Override                                                      //~9220I~
    public void onChangedURG(int PrbID,int Pparm)                  //~9220I~
    {                                                              //~9220I~
        if (Dump.Y) Dump.println("CompleteDlg.onChangedURG parm="+Pparm+",btnid="+Integer.toHexString(PrbID));//~9220I~
        if (swInitLayout)                                          //~9220I~
        {                                                          //~9220I~
            return;                                                //~9220I~
        }                                                          //~9220I~
        switch (Pparm)                                             //~9220I~
        {                                                          //~9220I~
        case UBRGP_PAOESWN:                                        //~9220I~
//          boolean swpao=getPaoEswnStatus();                      //~9224I~//~9601R~
//      	if (!swpao)                                            //~9224I~//~9601R~
//          	break;                                             //~9220I~//~9601R~
//  	    if (!getSetting(UCBP_COMPTYPE_PAO))                    //~9220I~//~9601R~
//              break;                                             //~9220I~//~9601R~
    	    getSetting(UCBP_COMPTYPE_PAO);                         //~9601I~
//		    setAmmount(UCBP_COMPTYPE_PAO_DONE,swpao);              //~9224I~//~9601R~
  		    setAmmount(UCBP_COMPTYPE_PAO_DONE,true);               //~9601I~
//  		disableFixGame(true);                                  //~9B10I~//~0314R~
            break;                                                 //~9220I~
        case UBRGP_TYPE_NEXTGAME:                                  //~9B10I~
			getSettingNextGame();                                  //~9B10I~
            saveNextGame();                                        //~0301I~
//  		disableFixGame(true);                                  //~9B10I~//~0314R~
            break;                                                 //~9B10I~
        }                                                          //~9220I~
        CMP.swSent=false;                                          //~0314I~
   		disableFixGame(true/*PswResetResponse*/);                  //~0314I~
    }                                                              //~9220I~
    //*******************************************************      //~9214I~
    //*USpinnerI                                                   //~9214I~
    //*******************************************************      //~9214I~
    @Override                                                      //~9214I~
    public void onItemSelectedUS(int PViewID,int Ppos,int Pparm)               //~9214R~//~9228R~
    {                                                              //~9214I~
        if (Dump.Y) Dump.println("CompleteDlg.onItemSelected swInitLayout="+swInitLayout+",parm="+Pparm+",pos="+Ppos);//~9214R~
        if (swInitLayout)                                          //~9214R~
        	return;                                                //~9214I~
        boolean swAmmount=false;                                   //~9214I~
        int comptype=UCBP_COMPTYPE_NORMAL;                         //~9219I~
        switch (Pparm)                                             //~9214I~
        {                                                          //~9214I~
//        case USPP_COMPTYPE:                                        //~9214R~//~9315R~
//            updateCompType(PViewID,Ppos);                          //~9228I~//~9315R~
//            if (swCompNormal)                                      //~9228I~//~9315R~
//                swAmmount=true;                                    //~9228I~//~9315R~
//            break;                                                 //~9228I~//~9315R~
        case USPP_POINT:                                           //~9214I~
        case USPP_RANK:                                            //~9214I~
            if (swCompNormal)                                      //~9219I~
            	swAmmount=true;                                     //~9214I~
        	break;                                                 //~9214I~
//        case USPP_PAO:                                             //~9214I~//~9601R~
////          if (swCompPao)                                         //~9219R~//~9224R~//~9601R~
//            if (getPaoEswnStatus())                                //~9224I~//~9601R~
//            {                                                      //~9219I~//~9601R~
//                swAmmount=true;                                     //~9214I~//~9601R~
//                comptype=UCBP_COMPTYPE_PAO;                        //~9219R~//~9601R~
//            }                                                      //~9219I~//~9601R~
//            break;                                                 //~9214I~//~9601R~
        }                                                          //~9214I~
        if (swAmmount)                                            //~9214I~
        {                                                          //~9214I~
            if (getSetting(comptype))                              //~9219I~
            {                                                      //~9224I~
  		    	if (comptype==UCBP_COMPTYPE_PAO)                    //~9224I~
	  		    	comptype=UCBP_COMPTYPE_PAO_DONE;                //~9224I~
                setAmmount(comptype,true/*comptype checked*/);                     //~9214I~//~9219R~
            }                                                      //~9224I~
                                                                   //~9224I~
        }                                                          //~9214I~
    }                                                              //~9214I~
    //*******************************************************      //~9214I~
    @Override                                                      //~9214I~
    public void onNothingSelectedUS(int Pparm)                     //~9214I~
    {                                                              //~9214I~
        if (Dump.Y) Dump.println("CompleteDlg.onNothingSelected parm="+Pparm);//~9214I~
    }                                                              //~9214I~
//    //*******************************************************      //~9225M~//~9315R~
//    public void onClickSend()                                      //~9225M~//~9315R~
//    {                                                              //~9225M~//~9315R~
//        if (Dump.Y) Dump.println("CompleteDlg.onClickSend");       //~9225M~//~9315R~
//        String msg=makeReqMsg();                                   //~9225M~//~9315R~
//        ACC.sendToAll(GCM_COMPRESULT_REQ,msg);                     //~9225M~//~9227R~//~9315R~
//    }                                                              //~9225M~//~9315R~
    //*******************************************************      //~9315I~
    public void sendRequest()                                      //~9315I~
    {                                                              //~9315I~
        if (Dump.Y) Dump.println("CompleteDlg.sendRequest swTrainigMode="+AG.swTrainingMode);       //~9315I~//~va66R~
        if (AG.swTrainingMode)                                     //~va66I~
        	return;                                                //~va66I~
        String msg=makeReqMsg();                                   //~9315I~
        ACC.sendToAll(GCM_COMPRESULT_REQ,msg);                     //~9315I~
    }                                                              //~9315I~
    //*******************************************************      //~9220I~//~9225M~
    //*senderEswn, "1"(compNormal), typeNextGame,                  //~9B10I~
	//compType(maybe COMTYPE_INVALID)*4,                           //~9B10I~
	//amtTotal*4,cbErr*4,                                          //~9B10I~
	//pao(On/Off,compEswn,looserEswn,rank)*3,                      //~9B10I~
    //*******************************************************      //~9B10I~
    private String makeReqMsg()                                    //~9225M~
    {                                                              //~9225M~
        StringBuffer sb=new StringBuffer();                        //~9225M~
        sb.append(currentEswn);                                    //~9225R~
//      sb.append(MSG_SEPAPP2+(swCompNormal?"1":"0"));              //~9225R~//~0304R~
        sb.append(MSG_SEPAPP2+(swSuspend ? "1" : "0"));            //~0304I~
//      sb.append(MSG_SEPAPP2+(swCompError?"1":"0"));               //~9225I~//~9424R~
//      sb.append(MSG_SEPAPP2+"9");		//"9":not used             //~9424I~//~9707R~
        sb.append(MSG_SEPAPP2+typeNextGame);                        //~9707I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9226I~
//      	sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+spnsComp[ii].getSelectedIndex());//~9226I~//~9315R~
        	sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+(swsInvalid[ii]?COMPTYPE_INVALID:rcv_compType[ii]));//~9315R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9320I~
        	sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+amtTotal[ii]);     //eswn seq//~9320I~
        for (int ii=0;ii<PLAYERS;ii++)                                 //~9225M~
        	sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+(cbErr[ii].getState()?"1":"0"));  //~9225R~//~9226R~
        for (int ii=0;ii<PLAYERS-1;ii++)                               //~9225M~
        	sb.append(MSG_SEPAPP2+makeReqMsgPao(ii));               //~9225R~//~9226R~
        String s=sb.toString();                                    //~9225M~
        if (Dump.Y) Dump.println("CompleteDlg.makeReqMsg swsInvalid="+Arrays.toString(swsInvalid));//~9707R~
        if (Dump.Y) Dump.println("CompleteDlg.makeReqMsg rcv_compType="+Arrays.toString(rcv_compType));//~9707I~
        if (Dump.Y) Dump.println("CompleteDlg.makeReqMsg resultCompType="+Arrays.toString(resultCompType));//~9707I~
        if (Dump.Y) Dump.println("CompleteDlg.makeReqMsg msg="+s); //~9707I~
        return s;
    }                                                              //~9225M~
    //*******************************************************      //~9430I~
    //*on Server                                                   //~0226I~
    //*******************************************************      //~0226I~
    public void sendRequestToClient(int PsrcEswn,int[] PintParm)   //~9430R~
    {                                                              //~9430I~
        if (Dump.Y) Dump.println("CompleteDlg.sendRequestToClient wrcEswn="+PsrcEswn);//~9430R~
        String msg=makeReqMsg(PintParm);                          //~9430I~
        ACC.sendToClientSkipByEswn(false/*robot*/,PsrcEswn/*skip*/,GCM_COMPRESULT_REQ,msg);//~9430R~
    }                                                              //~9430I~
    //*******************************************************      //~9430I~
    private String makeReqMsg(int[] PintParm)                      //~9430I~
    {                                                              //~9430I~
        StringBuffer sb=new StringBuffer();                        //~9430I~
        int pos=0;                                                 //~9430I~
        sb.append(currentEswn);                                    //~9430I~
        pos++;                                                     //~9430I~
        sb.append(MSG_SEPAPP2+PintParm[pos++]);                    //~9430I~
        sb.append(MSG_SEPAPP2+PintParm[pos++]);		//"9":not used //~9430I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9430I~
        	sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+PintParm[pos++]);//~9430I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9430I~
        	sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+PintParm[pos++]);     //eswn seq//~9430I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9430I~
        	sb.append(((ii==0)?MSG_SEPAPP2:MSG_SEPAPP)+PintParm[pos++]);//~9430I~
        for (int ii=0;ii<PLAYERS-1;ii++)                           //~9430I~
        	sb.append(MSG_SEPAPP2+PintParm[pos++]+MSG_SEPAPP+PintParm[pos++]+MSG_SEPAPP+PintParm[pos++]+MSG_SEPAPP+PintParm[pos++]);//~9430R~
        String s=sb.toString();                                    //~9430I~
        if (Dump.Y) Dump.println("CompleteDlg.makeReqMsg PintParm="+Arrays.toString(PintParm));//~9430I~
        if (Dump.Y) Dump.println("CompleteDlg.makeReqMsg msg="+s); //~9430I~
        return s;                                                  //~9430I~
    }                                                              //~9430I~
    //*******************************************************      //~9225M~
    private String makeReqMsgPao(int Pidx)                         //~9225M~
    {                                                              //~9225M~
    	String s;                                                  //~9225M~
//      UCheckBox cb=cbsPaoEswn[Pidx];                             //~9225M~//~9601R~
//      int chk=cb==null ? 0 : (cb.getState() ? 1 : 0);            //~9225M~//~9601R~
        int chk=isPaoActive(Pidx) ? 1 : 0;                         //~9601I~
        int compEswn=paoGainer[Pidx];                              //~9225M~
        int looserEswn=paoLooser[Pidx];                            //~9225M~
        if (chk==0)                                                //~9226I~
	        looserEswn=-1;                                         //~9226I~
        int rank=paoRank[Pidx];                                 //~9225M~
        if (Dump.Y) Dump.println("CompleteDlg.makeReqMsgPao chk="+chk+",compEswn="+compEswn+",looser="+looserEswn+",ranl="+rank);//~9226I~
        if (compEswn>=0)                                           //~9225M~
        	s=chk + MSG_SEPAPP+compEswn+MSG_SEPAPP+looserEswn+MSG_SEPAPP+rank;//~9225R~//~9226R~
        else                                                       //~9225M~
        	s=chk + MSG_SEPAPP+"-1"+MSG_SEPAPP+"-1"+MSG_SEPAPP+0;        //~9225R~//~9226R~
        if (Dump.Y) Dump.println("CompleteDlg.makeReqMsgPao s="+s);//~9225M~
        return s;                                                   //~9225M~
    }                                                              //~9225M~
    //*******************************************************      //~9225M~
    private void parseReqMsg(int[] PintParm,int Ppos)            //~9225M~
    {                                                              //~9225M~
        if (Dump.Y) Dump.println("CompleteDlg.parseReqMsg intp="+Arrays.toString(PintParm));//~9225M~//~0301R~
    	int[] prm=PintParm;                                        //~9225M~
        int pos=Ppos;                                              //~9225M~
//      rcv_swCompNormal=prm[pos++]!=0;                               //~9225R~//~0304R~
        rcv_swSuspend=prm[pos++]!=0;                               //~0304I~
//      rcv_swCompErr=prm[pos++]!=0;                                  //~9225I~//~9424R~
//      pos++;  //not used                                         //~9424I~//~9707R~
        rcv_typeNextGame=prm[pos++];                                          //~9707I~
        typeNextGame=rcv_typeNextGame;                             //~0301I~
        if (Dump.Y) Dump.println("CompleteDlg.parsReqMsg rcv_typeNextGame="+typeNextGame);//~9707I~
        rcv_cbErrState=new boolean[PLAYERS];                       //~9225R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9226I~
        {                                                          //~9226I~
        	rcv_compType[ii]=prm[pos++];                        //~9226I~
	        swsInvalid[ii] = rcv_compType[ii] == COMPTYPE_INVALID;     //~9315R~//~9316I~//~9319I~
        }                                                          //~9226I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9320I~
        {                                                          //~9320I~
        	rcv_amtTotal[ii]=prm[pos++];                        //~9320I~
        }                                                          //~9320I~
        for (int ii=0;ii<PLAYERS;ii++)                                 //~9225M~
        {                                                          //~9225M~
        	rcv_cbErrState[ii]=prm[pos++]!=0;                      //~9225M~
        }                                                          //~9225M~
        rcv_cbPaoStatus=new boolean[PLAYERS-1];                    //~9225R~
        rcv_paoGainer=new int[PLAYERS-1];                          //~9225R~
        rcv_paoLooser=new int[PLAYERS-1];                          //~9225R~
        rcv_paoRank=new int[PLAYERS-1];                            //~9225R~
        for (int ii=0;ii<PLAYERS-1;ii++)                               //~9225M~
        {                                                          //~9225M~
        	rcv_cbPaoStatus[ii]=prm[pos++]!=0;                      //~9225M~
        	rcv_paoGainer[ii]=prm[pos++];                        //~9225M~
        	rcv_paoLooser[ii]=prm[pos++];                        //~9225M~
        	rcv_paoRank[ii]=prm[pos++];                          //~9225M~
        }                                                          //~9225M~
        paoLooser=rcv_paoLooser;                                   //~0302I~
        if (Dump.Y) Dump.println("CompleteDlg.parsReqMsg rcv_comptype="+Arrays.toString(rcv_compType));//~9225M~//~9319R~
        if (Dump.Y) Dump.println("CompleteDlg.parsReqMsg swsInvalid="+Arrays.toString(swsInvalid));//~9319I~
        if (Dump.Y) Dump.println("CompleteDlg.parsReqMsg rcv_cbErrState="+Arrays.toString(rcv_cbErrState));//~9319I~
        if (Dump.Y) Dump.println("CompleteDlg.parsReqMsg cbPao="+Arrays.toString(rcv_cbPaoStatus));//~9319I~
        if (Dump.Y) Dump.println("CompleteDlg.parsReqMsg paoGainer="+Arrays.toString(rcv_paoGainer));//~9225M~
        if (Dump.Y) Dump.println("CompleteDlg.parsReqMsg paoLooser="+Arrays.toString(rcv_paoLooser));//~9225M~//~0302R~
        if (Dump.Y) Dump.println("CompleteDlg.parsReqMsg paoRank="+Arrays.toString(rcv_paoRank));//~9225M~//~0302R~
    }                                                              //~9225M~
    //*******************************************************************//~9316I~
    private boolean chkDupCall(int[] Pintp)                        //~9316I~
    {                                                              //~9316I~
        if (Dump.Y) Dump.println("CompleteDlg.chkDupCall intp="+Arrays.toString(Pintp));//~9316I~
        int[] old=receivedParm;                                    //~9316R~
		if (old==null)                                             //~9316R~
        {                                                          //~9316I~
	        if (Dump.Y) Dump.println("CompleteDlg.chkDupCall receivedParm==null");//~9316R~
        	return false;                                          //~9316I~
        }                                                          //~9316I~
	    if (Dump.Y) Dump.println("CompleteDlg.chkDupCall old receivedParm="+Arrays.toString(old));//~9316R~
        if (old.length!=Pintp.length)                              //~9316R~
        	return false;                                          //~9316I~
    	for (int ii=0;ii<Pintp.length;ii++)                        //~9316I~
        	if (old[ii]!=Pintp[ii])                                //~9316R~
            	return false;                                      //~9316I~
	    if (Dump.Y) Dump.println("CompleteDlg.chkDupCall return true");//~9316I~
        return true;                                               //~9316I~
    }                                                              //~9316I~
    //*******************************************************************//~9225I~
    //*From ACAction when received GCM_COMPRESULT_REQ/COMPRESULT_RESP                 //~9225I~//~9314R~
    //*******************************************************************//~9225I~
    public static void onReceivedRequest(int PmsgID,int Psender/*idxAccount*/,String PmsgData)//~9225I~//~9430R~
    {                                                              //~9225I~
        int[] intp=ACAction.parseAppData(PmsgData);                //~9225R~
		int curEswn=Accounts.getCurrentEswn();                     //~9430I~
        int eswnDealer=AG.aAccounts.getCurrentDealerRealEswn();    //~9430I~
        boolean swDealer=curEswn==eswnDealer;                      //~9430I~
        if (Dump.Y) Dump.println("CompleteDlg.onReceivedRequest curEswn="+curEswn+",real eswnDealer="+eswnDealer+",swDealer="+swDealer);//~9430I~
        int srcEswn=intp[0];                                       //~9225R~
        if (Dump.Y) Dump.println("CompleteDlg.onReceivedRequest msgid="+PmsgID+",sender="+Psender+",msgdata="+PmsgData+",srcEswn="+srcEswn+",parmint="+Arrays.toString(intp));//~9225R~//~9315R~
        if (PmsgID==GCM_COMPDLG_NEXTPLAYER)                        //~9A12I~
        {                                                          //~9A12I~
        	int[] compIndex=new int[PLAYERS];                      //~9A12I~
           	System.arraycopy(intp,POS_COMPINDEX,compIndex,0,PLAYERS);//~9A12I~
		    resetComplete(srcEswn,compIndex);                      //~9A12R~
        }                                                          //~9A12I~
        else                                                       //~9A12I~
        if (PmsgID==GCM_COMPRESULT_REQ)                            //~9225R~
        {                                                          //~9225I~
            if (Utils.isShowingDialogFragment(AG.aCompleteDlg))        //~9226R~//~9228I~//~9316R~
            {                                                          //~9226R~//~9228I~
            	if (AG.aCompleteDlg.chkDupCall(intp))                       //~9316I~
                {                                                  //~9316I~
        			if (Dump.Y) Dump.println("CompleteDlg.onReceiveRequest dupcall exit");//~9316I~
                	return;                                         //~9316I~
                }                                                  //~9316I~
//              UView.showToast(R.string.Err_DuplicatedDialog);        //~9226R~//~9228I~//~9315R~
//              UView.showToast("Dup resultReq");                  //~9315I~//~9B10R~
        		if (Dump.Y) Dump.println("CompleteDlg.onReceiveRequest Dup resultReq");//~9B10I~
		        AG.aCompleteDlg.dismissDlg();                      //~9430I~
            }                                                          //~9226R~//~9228I~
            	newInstance(intp,1/*pos*/).show();                     //~9225R~//~9315R~
            if (AG.aAccounts.isServer())                           //~9430I~
          		if (!swDealer)	//not dealer                       //~9430R~
                {                                                  //~9430I~
			        AG.aCompleteDlg.sendRequestToClient(srcEswn,intp);//~9430R~
                }                                                  //~9430I~
        }                                                          //~9225I~
        else	//GCM_COMPRESULT_RESP                                                       //~9227I~//~9314R~//~9430R~
        {                                                          //~9227I~
        	int replyEswn=intp[1];                                 //~9227R~
            AG.aComplete.setResultOK(srcEswn,replyEswn,intp[2]!=0);//~9227R~
        	if (Dump.Y) Dump.println("CompleteDlg.onReceivedRequest COMPRESULT RESP srcEswn="+srcEswn+",replyEswn="+replyEswn+",swOK="+(intp[2]!=0));//~9314I~//~9430R~
//          if (AG.aCompleteDlg==null)                             //~9315R~
//            newInstance(intp,1/*pos*/).show();                   //~9315R~
//          else                                                   //~9315R~
//          AG.aCompleteDlg.repaintOKNG(srcEswn,intp[2]!=0);                                      //~9227R~//~9313R~//~9314R~//~9315R~
          if (swDealer)	//dealer                                   //~9430R~
          {                                                        //~9430I~
            if (UAEndGame.isUpdateAfterSend())                               //~0309R~//~0314I~
            {                                                      //~0314I~
	        	if (Dump.Y) Dump.println("CompleteDlg.onReceivedRequest COMPRESULT RESP updateAfterSend");//~0314I~
            }                                                      //~0314I~
            else                                                   //~0314I~
            if (Utils.isShowingDialogFragment(AG.aCompleteDlg))    //~9316I~
            {                                                      //~9316I~
//              UView.showToast("repaint Dup ResultDialog");            //~9315I~//~9316I~//~9B10R~
        		if (Dump.Y) Dump.println("CompleteDlg.onReceivedRequest COMPRESULT RESP repaint Dup ResultDialog");//~9B10I~
            	AG.aCompleteDlg.repaintOKNG(replyEswn,intp[2]!=0);	//callback updateOKNGAdditional//~9316R~
            }                                                      //~9316I~
            else                                                   //~9316I~
	            newInstance().show();                              //~9316I~
          }                                                        //~9430I~
          else                                                     //~9430I~
            if (AG.aAccounts.isServer())                           //~9430I~
            {                                                      //~9430I~
                String msg=srcEswn+MSG_SEPAPP+replyEswn+MSG_SEPAPP+intp[2]/*okng*/;//~9430R~
                AG.aAccounts.sendToTheClientDealer(GCM_COMPRESULT_RESP,msg);	//notify to dealer//~9430R~
            }                                                          //~9227I~//~9430R~
        }                                                          //~9430I~
//        if (AG.aAccounts.isServer())                               //~9225I~//~9227R~//~9315R~
//        {                                                          //~9225I~//~9315R~
//            int idxAccount=AG.aAccounts.currentEswnToMember(srcEswn);  //~9225I~//~9227R~//~9315I~
//            AG.aAccounts.sendToClient(false/*PswRobot*/,idxAccount/*Pskip*/,PmsgID,PmsgData);//~9225I~//~9227R~//~9315R~
//        }                                                          //~9225I~//~9315R~
    }                                                              //~9225I~
    //*******************************************************************//~9316I~
    //*on UiThread                                                 //~9316I~
    //*******************************************************************//~9316I~
    @Override                                                      //~9316I~
//  protected void updateOKNGAdditional(int PctrNone,int PctrNG,boolean PswAllOK)//~9316I~//~0119R~
    protected void updateOKNGAdditional(int PctrNone,int PctrNG,int PctrDisconnected,boolean PswAllOK)//~0119I~
    {                                                              //~9316I~
        if (Dump.Y) Dump.println("CompleteDlg.updateOKNGAdditional ctrNG="+PctrNG+",ctrNone="+PctrNone+",swAllOK="+PswAllOK);//~9316I~
      if (!AG.swTrainingMode)                                      //~va66I~
        btnTotal.setEnabled(PswAllOK);                             //~9316R~
        if (PctrNone==0 && PctrNG!=0) //all responsed, someone replyed NG//~9608I~
        {                                                          //~9608I~
	        if (swRequester)                                       //~9609I~
    	    	alertToForceOK(this,TITLEID_REQ,R.string.Alert_CompleteDlgForceOK);//~9608I~//~9609R~
        }                                                          //~9608I~
    }                                                              //~9316I~
    //*******************************************************************//~9608I~
    @Override                                                      //~9608I~
	protected void alertActionReceived(int Pbuttonid,int Prc)       //~9608I~
    {                                                              //~9608I~
        if (Dump.Y) Dump.println("CompleteDlg.alerActionReceived buttonID="+Pbuttonid+",rc="+Prc);//~9608I~
    	if (Pbuttonid==BUTTON_POSITIVE)                            //~9608I~
        {                                                          //~9608I~
	        btnTotal.setEnabled(true/*PswAllOK*/);                 //~9608I~
        }                                                          //~9608I~
    }                                                              //~9608I~
    //*******************************************************************//~9227I~
    protected void sendReply(boolean PswOK)                          //~9227I~//~9307R~
    {                                                              //~9227I~
        if (Dump.Y) Dump.println("CompleteDlg.sendReply OK="+PswOK);//~9227I~
        CMP.setResultOK(requesterEswn,currentEswn,PswOK);          //~9227R~
        String msg=requesterEswn+MSG_SEPAPP+currentEswn+MSG_SEPAPP+(PswOK ? "1" : "0");//~9227I~
        ACC.sendToAll(GCM_COMPRESULT_RESP,msg);                    //~9227I~
    }                                                              //~9227I~
    //*******************************************************************//~9227I~
    //*from CompReqDlg at recived reply from all client           //~9313R~//~9315R~
    //*******************************************************************//~9227I~
    public static void showResult(int PcompEswn)                   //~9227R~
    {                                                              //~9227I~
        if (Dump.Y) Dump.println("CompleteDlg.showResult compEswn="+PcompEswn+",swsInvalid="+Arrays.toString(AG.aComplete.swsInvalid)+",swsErrLooser="+Arrays.toString(AG.aComplete.swsErrLooser));//~9227I~//~0301R~
	    Complete comp=AG.aComplete;                                //~9227I~
    	if (comp==null)                                            //~9227I~
        	return;                                                //~9227I~
//      int rc=comp.chkCompReqReply();                             //~9227I~//~9B11R~
//      if (rc<0)	//not all reply gotten                                                  //~9227I~//~9315R~//~9B11R~
        if (!chkCompReqReplyAll())                                 //~9B11I~
        	return;                                                //~9227I~//~9B11R~
        if (!isDealer())    //dealer only for result result        //~0218I~
        	return;                                                //~0218I~
        if (Utils.isShowingDialogFragment(AG.aCompleteDlg))        //~0303I~
        {                                                          //~0303I~
        	if (Dump.Y) Dump.println("CompleteDlg.showResult dismsiss by dup");//~0303I~
		    AG.aCompleteDlg.dismissDlg();                          //~0303I~
        }                                                          //~0303I~
//      if (AG.aCompleteDlg==null)                                 //~9227I~//~0303R~
        {                                                          //~9227I~
//*reset by new CompReqDlg                                         //~0303I~
            Arrays.fill(comp.swsInvalid,false);                    //~0303I~
        	comp.typeNextGame=-1;                                  //~0303I~
        	comp.swsErrLooser=null;                                //~0303R~
        	comp.paoLooser=null;                                   //~0303R~
//*                                                                //~0303I~
//          Complete.Status sorted[]=AG.aComplete.sortStatusS();  //list of complete replyAll(OK and NG)//~9227R~//~9310R~
                                                                   //~9315I~
            Complete.Status[] sorted=getSortedCompStat();          //~9310I~//~9315R~
            int comp1stEswn=sorted[0].completeEswn;                //~9227R~//~9315R~
            int curEswn=Accounts.getCurrentEswn();                 //~9227I~//~9315R~
            if (Dump.Y) Dump.println("CompleteDlg.showResult curEswn="+curEswn+",1stGainer="+comp1stEswn);//~9227I~//~9315R~
//            if (curEswn==comp1stEswn)                              //~9227R~//~9315R~
            int eswnDealer=AG.aAccounts.getCurrentDealerRealEswn();//~9315R~
            if (curEswn==eswnDealer)                               //~9315I~
            {                                                      //~9227I~
	            if (Dump.Y) Dump.println("CompleteDlg.showResult ccurrent is dealer");//~9315I~
//      		newInstance().show();  //open on on 1st gainer            //~9227R~//~9315R~
//      		newInstance().show();                   //~9315R~  //~9409R~
        		showDialogIfAllAgreed();                           //~9409I~
            }                                                      //~9227I~
//            else                                                   //~9315I~//~9410R~
//            if (AG.aAccounts.isServer())                           //~9315I~//~9410R~
//            {                                                      //~9315I~//~9410R~
//                if (Dump.Y) Dump.println("CompleteDlg.showResult current is server");//~9315I~//~9409R~//~9410R~
//                int realPlayer=AG.aAccounts.getCurrentDealerReal();//~9315I~//~9410R~
////              AG.aAccounts.sendToTheClient(realPlayer,GCM_COMPRESULT_REQ,"-1"/*open resultDlg*/);//~9315R~//~9409R~//~9410R~
//                AG.aAccounts.sendToTheClient(realPlayer,GCM_COMPRESULT_REQ,Integer.toString(REQ_OPEN_DIALOG_TO_DEALER/*-1*/));//~9409I~//~9410R~
//            }                                                      //~9315I~//~9410R~
//            else                                                   //~9315I~//~9410R~
//            {                                                      //~9315I~//~9410R~
//                if (Dump.Y) Dump.println("CompleteDlg.showResult current is client");//~9315I~//~9409R~//~9410R~
////              AG.aAccounts.sendToServer(GCM_COMPRESULT_REQ,"-1");//~9315R~//~9409R~//~9410R~
//                AG.aAccounts.sendToServer(GCM_COMPRESULT_REQ,Integer.toString(REQ_TRANSFER_TO_DEALER_FROM_SERVER/*-2*/));//~9409I~//~9410R~
//            }                                                      //~9315I~//~9410R~
        }                                                          //~9227I~
//        else                                                       //~9227I~//~9314R~
//        {                                                          //~9227I~//~9314R~
//            AG.aCompleteDlg.repaint(PcompEswn);                                    //~9227I~//~9313R~//~9314R~
//        }                                                          //~9227I~//~9314R~
    }                                                              //~9227I~
//    @Override                                                    //~9228R~
//    public void onShow(DialogInterface Pdlg)                     //~9228R~
//    {                                                            //~9228R~
//        if (Dump.Y) Dump.println("CompleteDlg.onShow");          //~9228R~
//    }                                                            //~9228R~
	//************************************************             //~9310I~
    private static Complete.Status[] getSortedCompStat()           //~9310I~
    {                                                              //~9310I~
//      Complete.Status sorted[]=AG.aComplete.sortStatusS();       //~9310I~//~9707R~
        Complete.Status sorted[]=AG.aComplete.getSortedStatusS();    //~9707I~
		if (Dump.Y) Dump.println("CompleteDlg.getSortedCompStat swExistingnotrequested="+AG.aComplete.swExistingNotRequested);//~9310I~
		if (AG.aComplete.swExistingNotRequested)                   //~9310I~
        	UView.showToast(R.string.Err_CompReqNotShowableExist); //~9310I~
        return sorted;                                             //~9310I~
    }                                                              //~9310I~
//    //*******************************************************      //~9227I~//~9313I~
//    private static void repaint(int PrequesterEswn)                //~9227I~//~9313I~
//    {                                                              //~9227I~//~9313I~
//        CompleteDlg dlg=AG.aCompleteDlg;                           //~9227I~//~9313I~
//        if (dlg!=null)                                             //~9227I~//~9313I~
//            new EventCB(ECB_COMPRESULT_RESP,EventCB.newBundle(PrequesterEswn)).postEvent();//~9227R~//~9313I~
//        if (Dump.Y) Dump.println("CompleteDlg.repaint eswn="+PrequesterEswn+",dlg==null ?"+(dlg==null));//~9227I~//~9313I~
//    }                                                              //~9227I~//~9313I~
//    //*******************************************************      //~9227I~//~9313I~
//    //*from MainActivity on UIThread                               //~9227I~//~9313I~
//    //*******************************************************      //~9227I~//~9313I~
//    public static void repaintUI(EventCB PeventCB)                 //~9227I~//~9313I~
//    {                                                              //~9227I~//~9313I~
//        try                                                        //~9227I~//~9313I~
//        {                                                          //~9227I~//~9313I~
//            int eswn=PeventCB.getParmInt1();                       //~9227I~//~9313I~
//            if (Dump.Y) Dump.println("CompleteDlg.repaintUI eswn="+eswn);//~9227I~//~9313I~//~va66R~
//            CompleteDlg dlg=AG.aCompleteDlg;                       //~9227I~//~9313I~
//            if (dlg!=null)                                         //~9227I~//~9313I~
//                dlg.repaintResultReply();                                //~9227I~//~9228R~//~9313I~
//        }                                                          //~9227I~//~9313I~
//        catch(Exception e)                                         //~9227I~//~9313I~
//        {                                                          //~9227I~//~9313I~
//            Dump.println(e,"CompleteDlg:repaintUI");               //~9227I~//~9313I~
//        }                                                          //~9227I~//~9313I~
//    }                                                              //~9227I~//~9313I~
//    //*******************************************************    //~9313I~
//    public void repaint(int Peswn)                                 //~9313I~//~9314R~
//    {                                                              //~9313I~//~9314R~
//        if (Dump.Y) Dump.println("CompleteDlg.repaint eswn="+Peswn);//~9313I~//~9314R~
//        repaintEswn=Peswn;                                         //~9313I~//~9314R~
//        AG.activity.runOnUiThread(                                 //~9313I~//~9314R~
//            new Runnable()                                         //~9313I~//~9314R~
//            {                                                      //~9313I~//~9314R~
//                @Override                                          //~9313I~//~9314R~
//                public void run()                                  //~9313I~//~9314R~
//                {                                                  //~9313I~//~9314R~
//                    try                                            //~9313I~//~9314R~
//                    {                                              //~9313I~//~9314R~
//                        if (Dump.Y) Dump.println("CompleteDlg.repaint runonUiThread.run");//~9313I~//~9314R~
//                        repaintUI(repaintEswn);                    //~9313I~//~9314R~
//                    }                                              //~9313I~//~9314R~
//                    catch(Exception e)                             //~9313I~//~9314R~
//                    {                                              //~9313I~//~9314R~
//                        Dump.println(e,"CompleteDlg:repaint.run"); //~9313I~//~9314R~
//                    }                                              //~9313I~//~9314R~
//                }                                                  //~9313I~//~9314R~
//            }                                                      //~9313I~//~9314R~
//                                  );                               //~9313I~//~9314R~
//                                                                   //~9313I~//~9314R~
//    }                                                              //~9313I~//~9314R~
//    //*******************************************************      //~9313I~//~9314R~
//    public  void repaintUI(int Peswn)                        //~9313I~//~9314R~
//    {                                                              //~9313I~//~9314R~
//        if (Dump.Y) Dump.println("CompleteDlg.repaintUI eswn="+Peswn);//~9313I~//~9314R~//~va66R~
//        repaintResultReply();                                      //~9313I~//~9314R~
//    }                                                              //~9313I~//~9314R~
	//************************************************             //~9316I~
    private void showTotal()                                       //~9316I~
    {                                                              //~9316I~
//        setStatus();                                             //~0302R~
        int idx=eswnToIdx(0/*East:dealer*/);                       //~9318I~
        int tp=resultCompType[idx]; //dealer comptype              //~9318I~
        if (Dump.Y) Dump.println("CompleteDlg.showTotal idx="+idx+",comptype="+tp+",total="+Arrays.toString(amtTotal));//~9318I~
//      int typeNextGame=(tp==COMPTYPE_TAKE || tp==COMPTYPE_RIVER) ? NGTP_CONTINUE :NGTP_NEXT;//~9318I~//~9707R~
    	getSettingNextGame();	//set typeNextGame from radiogroup //~9707I~
        if (Dump.Y) Dump.println("CompleteDlg.showTotal typeNextGame="+typeNextGame+",amt="+Arrays.toString(amtTotal));//~9318I~//~va66R~
        if (typeNextGame==NGTP_NEXTPLAYER)                         //~9A12I~
            continueToNextPlayer(true/*swFixed*/);   //~9A12R~     //~9A13R~
        else                                                       //~9A12I~
        {                                                          //~9A12I~
        ScoreDlg.showTotal(EGDR_NORMAL,typeNextGame,amtTotal); //minus chk//~9318I~
        if (ctrNG!=0)                                              //~9608I~
//  		UserAction.showInfoAllEswn(0/*opt*/,PLAYER_YOU,Utils.getStr(R.string.Info_CompleteDlgForceOK));//~9608I~//~0225R~
    		UserAction.showInfoAllEswn(0/*opt*/,PLAYER_YOU,R.string.Info_CompleteDlgForceOK);//~0225I~
        }                                                          //~9A12I~
    }                                                              //~9316I~
//    //************************************************           //~0302R~
//    private void setStatus()                                     //~0302R~
//    {                                                            //~0302R~
//        if (Dump.Y) Dump.println("CompleteDlg.setStatus swsErrLooser="+Arrays.toString(swsErrLooser)+",swsInvalid="+Arrays.toString(swsInvalid));//~0302R~//~va66R~
//        Complete.Status[] sorted=getSortedCompStat();            //~0302R~
//        for (int ii=0;ii<sorted.length;ii++)                     //~0302R~
//        {                                                        //~0302R~
//            Complete.Status stat=sorted[ii];                     //~0302R~
//            if (Dump.Y) Dump.println("CompleteDlg.getNormalPoint stat="+stat.toString());//~0302R~
////          stat.setInvalid(swsInvalid[stat.completeEswn]); //do at Complete.setInvalidCompleteion//~0302R~
//            stat.setErr(isErrLooser(stat));                      //~0302R~
//        }                                                        //~0302R~
//    }                                                            //~0302R~
	//************************************************             //~9417I~
    private void showRule()                                        //~9417I~
    {                                                              //~9417I~
        if (Dump.Y) Dump.println("CompleteDlg.showRule");          //~9417I~//~va66R~
        RuleSetting.showRuleInGame();                              //~9408R~//~9417I~
    }                                                              //~9417I~
	//************************************************             //~9403I~
    public static boolean  showDismissed()                             //~9403I~
    {                                                              //~9403I~
        if (Dump.Y) Dump.println("CompleteDlg.showDismissed");     //~9403I~//~va66R~
        boolean rc=false;	//no dismiss menudialog                //~9904I~
        if (chkComplete()==0)                                      //~9403I~
        {                                                          //~9904I~
            UView.showToast(R.string.Err_NoneCompleted);           //~9403I~
        	return false;                                          //~9904I~
        }                                                          //~9904I~
        else                                                       //~9403I~
		if (AG.aComplete.isTotalAgreed())                          //~9612I~
        {                                                          //~9612I~
            UView.showToast(R.string.Err_TotalAgreedAlready);      //~9612I~
        	return false;                                             //~9612I~//~9904R~
        }                                                          //~9612I~
//      if (!AG.aComplete.chkCompReqReplyAll())                    //~9403I~//~9B11R~
//          UView.showToast(R.string.Err_CompReqNotShowableExist); //~9403I~//~9B11R~
//      else                                                       //~9403I~//~9B11R~
        if (chkCompReqReplyAll())                                  //~9B11I~
        {                                                          //~9904I~
            CompleteDlg.newInstance().show();                      //~9403I~
            rc=true;	//dismiss menu dlg                         //~9904I~
        }                                                          //~9904I~
        return rc;                                                 //~9904I~
    }                                                              //~9403I~
    //*******************************************************************//~9B11I~
    private static boolean chkCompReqReplyAll()                       //~9B11I~
    {                                                              //~9B11I~
        boolean rc=false;                                          //~9B11I~
        if (Dump.Y) Dump.println("CompleteDlg.chkCompReqReplyAll swsInvalid="+Arrays.toString(AG.aComplete.swsInvalid)+",swsErrLooser="+Arrays.toString(AG.aComplete.swsErrLooser));//~0301I~
        int rc2=AG.aComplete.chkCompReqReplyAll();                 //~9B11I~
        if (rc2==-1)       //noone replayed all                    //~9B11R~
            UView.showToast(R.string.Err_CompReqNotShowableExist); //~9B11I~
        else                                                       //~9B11I~
        if (rc2==1)        //exist not replyedAll                  //~9B11R~
            UView.showToast(R.string.Err_CompReqNotShowable);      //~9B11I~
        else               //all replyedAll                        //~9B11R~
            rc=true;                                               //~9B11I~
        if (Dump.Y) Dump.println("CompleteDlg.chkCompReqReplayAll rc="+rc);//~9B11I~//~va66R~
        return rc;                                                 //~9B11I~
    }                                                              //~9B11I~
    //*******************************************************************//~9403I~
    private static int chkComplete()                                   //~9403I~
    {                                                              //~9403I~
//        Complete.Status ss[]=AG.aComplete.statusS;               //~9403I~
//        int ctr=0;                                               //~9403I~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~9403I~
//        {                                                        //~9403I~
//            if (ss[ii]==null)                                    //~9403I~
//                 continue;                                       //~9403I~
//            if (PswReqDlg)                                       //~9403I~
//            {                                                    //~9403I~
//                if (ss[ii].isShowable())                         //~9403I~
//                    CompReqDlg.newInstance(ss[ii]).show();       //~9403I~
//                else                                             //~9403I~
//                    UView.showToast(R.string.Err_CompReqNotShowable);//~9403I~
//            }                                                    //~9403I~
//            ctr++;                                               //~9403I~
//        }                                                        //~9403I~
        Complete.Status ss[]=AG.aComplete.sortStatusS();           //~9403I~
        int ctr;                                                   //~9403R~
        if (ss==null)                                              //~9403I~
        	ctr=0;                                                 //~9403I~
        else                                                       //~9403I~
        	ctr=ss.length;                                         //~9403I~
		if (Dump.Y) Dump.println("CompleteDlg.chkComplete ctr="+ctr);//~9403I~
        return ctr;                                                //~9403I~
    }                                                              //~9403I~
    //*******************************************************************//~9409I~
    private static void showDialogIfAllAgreed()                           //~9409I~
    {                                                              //~9409I~
		if (Dump.Y) Dump.println("CompleteDlg.showDialogIfAllAgreed");//~9409I~
//      if (AG.aComplete.chkCompReqReplyAll())                    //~9409I~//~9B11R~
        if (chkCompReqReplyAll())                                  //~9B11I~
			newInstance().show();                                  //~9409I~
	}                                                              //~9409I~
//    //*******************************************************************//~9608I~//~0106R~
//    private void showAlert()                                       //~9608I~//~0106R~
//    {                                                              //~9608I~//~0106R~
//        if (Dump.Y) Dump.println("CompleteDlg.showAlert");//~9608I~//~0106R~
////      if (AG.aComplete.chkCompReqReplyAll())                     //~9608I~//~9B11R~//~0106R~
//        if (chkCompReqReplyAll())                                  //~9B11I~//~0106R~
//            newInstance().show();                                  //~9608I~//~0106R~
//    }                                                              //~9608I~//~0106R~
    //*******************************************************************//~9A12I~
    //*reset completed(including invalid/err) then continue this round//~9A12I~
    //*******************************************************************//~9A12I~
    private boolean continueToNextPlayer(boolean PswFixed)//~9A12R~//~9A13R~
    {                                                              //~9A12I~
		if (Dump.Y) Dump.println("CompleteDlg.continueToNextPlayer");//~9A12I~
	    Point p=chkNextPlayer();                                   //~9A12I~
        int ctrOK=p.x;                                             //~9A12I~
        int ctrErr=p.y;                                            //~9A12I~
        if (ctrOK!=0)                                              //~9A12I~
        {                                                          //~9A12I~
            UView.showToast(R.string.Err_ContinueNextPlayer_SomeoneOK);//~9A12I~
            return false;                                          //~9A12R~
        }                                                          //~9A12I~
        if (ctrErr==0)                                             //~9A12R~
        {                                                          //~9A12I~
            UView.showToast(R.string.Err_ContinueNextPlayer_NoOneErr);//~9A12I~
            return false;                                          //~9A12R~
        }                                                          //~9A12I~
        if (!PswFixed)                                             //~9A12I~
            return true;                                           //~9A12I~
        resetComplete(-1/*srcEswn:not received*/,compIndexNextPlayerToReset);        //~9A12R~//~9A13R~
        return true;                                               //~9A12I~
	}                                                              //~9A12I~
    //*******************************************************************//~9A12I~
    private static void resetComplete(int PsrcEswn,int[] PcompIndex)//~9A12R~
    {                                                              //~9A12I~
        boolean swServer=AG.aAccounts.isServer();                  //~9A12I~
        boolean swReceived=PsrcEswn!=-1;                           //~9A12I~
		if (Dump.Y) Dump.println("CompleteDlg.resetComplete swServer="+swServer+",srcEswn="+PsrcEswn+",compIndex="+Arrays.toString(PcompIndex));//~9A12R~
		if (Dump.Y) Dump.println("CompleteDlg.resetComplete Complete.swsInvalid="+Arrays.toString(AG.aComplete.swsInvalid));//~0227I~
        if (swServer||swReceived)                                  //~9A13I~
        {                                                          //~9A13I~
            AG.aUserAction.resetComplete(swServer,swReceived,PcompIndex);                  //~9A12R~//~9A13R~
//          AG.aComplete.resetComplete();                              //~9A12R~//~9A13R~//~9B10R~
//          AG.aComplete.setInvalidCompletion(AG.aComplete.swsInvalid);//~0227R~
            Complete.resetCompleteNextPlayer();                    //~9B10I~
//          AG.aGMsg.drawMsgbar(R.string.Info_ContinueNextPlayer);     //~9A12I~//~9A13R~//~9B10R~
            drawMsgbarNextPlayer(PcompIndex);                      //~9B10R~
        	setDelayedTimer(swServer);                                 //~9A12I~//~9A13I~
        }                                                          //~9A13I~
		String msg=makeMsgDataNextPlayer(PcompIndex);              //~9A12R~
        if (swServer)                                              //~9A12I~
        {                                                          //~9A12I~
//      	if (!swReceived)                                       //~9A12I~//~9A13R~
		        AG.aAccounts.sendToAll(GCM_COMPDLG_NEXTPLAYER,msg);//~9A12R~
//          else                                                   //~9A12I~//~9A13R~
//  			AG.aAccounts.sendToClientSkipByEswn(false/*PswRobot*/,PsrcEswn/*skipEswn*/,GCM_COMPDLG_NEXTPLAYER,msg);//~9A12R~//~9A13R~
        }                                                          //~9A12I~
        else                                                       //~9A12I~
        	if (!swReceived)                                       //~9A12I~
            {                                                      //~9A12I~
				AG.aAccounts.sendToServer(GCM_COMPDLG_NEXTPLAYER,msg);//~9A12R~
            }                                                      //~9A12I~
    }                                                              //~9A12I~
    //*******************************************************************//~9B11I~
    private static void drawMsgbarNextPlayer(int[] PcompIndex)     //~9B10R~
    {                                                              //~9B10I~
    	String errs="";                                            //~9B10I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~9B10I~
        {                                                          //~9B10I~
            int idx=PcompIndex[ii];	//index to sortedStatus        //~9B10R~
            if (idx>=0)                                            //~9B10I~
            {                                                      //~9B10I~
		        Complete.Status stat=AG.aComplete.sortedStatusS[idx];//~9B10R~
                int eswn=stat.completeEswn;                        //~9B10I~
                errs+=nameESWN[eswn];                              //~9B10I~
            }                                                      //~9B10I~
    	}                                                          //~9B10I~
		AG.aGMsg.drawMsgbar(Utils.getStr(R.string.Info_ContinueNextPlayer,errs));//~9B10I~
	}                                                              //~9B10I~
    //*******************************************************************//~9A12I~
    private static void setDelayedTimer(boolean PswServer)         //~9A12I~
    {                                                              //~9A12I~
        Players PLS=AG.aPlayers;                                   //~9A12I~
        int action=PLS.actionBeforeRon;                            //~9A12I~
        int player;                                                //~9A12I~
    	if (Dump.Y) Dump.println("CompleteDlg.setDelayedTimer swServer="+PswServer+",actionBeforeRon="+action);//~9A12I~
        if (action==GCM_DISCARD)                                   //~9A12I~
        {                                                          //~9A12I~
        	if (PswServer)                                         //~9A12I~
            {                                                      //~9A12I~
        		player=PLS.playerLastDiscarded;                    //~9A12I~
        		TileData td=PLS.tileLastDiscarded;                 //~9A12I~
            	AG.aUserAction.UAD.postNextPlayerPonKan(player,td);//~9A12I~
            }                                                      //~9A12I~
        }                                                          //~9A12I~
        else                                                       //~9A12I~
        if (action==GCM_TAKE) 	//for auto discard                  //~9A12I~//~0227R~
        {                                                          //~9A12I~
        	player=PLS.playerCurrent;                              //~9A12I~
	        AG.aUserAction.UAT.setAutoDiscardTimeout(PswServer,player,GCM_TAKE);//~9A12I~
        }                                                          //~9A12I~
    }                                                              //~9A12I~
    //*******************************************************************//~9A12I~
    private static String makeMsgDataNextPlayer(int[] PcompIndex)  //~9A12R~
    {                                                              //~9A12I~
	    int eswn=AG.aAccounts.playerToEswn(PLAYER_YOU);            //~9A12M~
        String msg=eswn+MSG_SEPAPP2+PcompIndex[0]+MSG_SEPAPP+PcompIndex[1]+MSG_SEPAPP+PcompIndex[2]+MSG_SEPAPP+PcompIndex[3];//~9A12R~
        return msg;                                                //~9A12I~
    }                                                              //~9A12I~
    //*******************************************************************//~9A12I~
    private Point chkNextPlayer()                                  //~9A12I~
    {                                                              //~9A12I~
    	if (Dump.Y) Dump.println("CompleteDlg.chkNextPlayer swsInvalid="+Arrays.toString(swsInvalid));//~9A12R~
        int ctr=sortedStatus.length;                               //~9A12I~
        int ctrOK=0,ctrErr=0;                                      //~9A12I~
        compIndexNextPlayerToReset=new int[PLAYERS];                    //~9A12I~
        Arrays.fill(compIndexNextPlayerToReset,-1);                     //~9A12I~
        for (int ii=0;ii<ctr;ii++)                                 //~9A12I~
        {                                                          //~9A12I~
	        Complete.Status stat=sortedStatus[ii];                 //~9A12I~
            int eswn=stat.completeEswn;                             //~9A12I~
		    int idx=eswnToIdx(eswn);                               //~9A12R~
        	boolean swInvalid=swsInvalid[idx];                     //~9A12I~
    		if (Dump.Y) Dump.println("CompleteDlg.chkNextPlayer ii="+ii+",compEswn="+stat.completeEswn+",idx="+idx+",swErr="+stat.swErr+",swInvalid="+stat.swInvalid+",swInvalidByEswn="+stat.swInvalidByEswn+",swsInvalid="+swInvalid);//~9A12R~
//          if (stat.swErr || stat.swInvalid || swInvalid)         //~9A12R~//~0302R~
            if (isErrLooser(stat) || stat.swInvalid || swInvalid)  //~0302I~
            {                                                      //~9A12I~
            	compIndexNextPlayerToReset[ctrErr++]=ii;           //~9A12I~
            }                                                      //~9A12I~
            else                                                   //~9A12I~
                ctrOK++;     //includes swInvalidByEswn            //~9A12I~
        }                                                          //~9A12I~
        return new Point(ctrOK,ctrErr);                            //~9A12I~
    }                                                              //~9A12I~
    //*******************************************************************//~9B29I~
    private void drawn3R()                                         //~9B29I~
    {                                                              //~9B29I~
    	if (Dump.Y) Dump.println("CompleteDlg.drawn3R");            //~9B29I~
        int titleid=TITLEID_REQ;                                   //~9B29I~
        int msgid=R.string.Alert_ConfirmDrawn3R;                     //~9B29I~
		Alert.showAlert(titleid,msgid, BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,this);//calback alertButtonAction//~9B29I~
    }                                                              //~9B29I~
    //*******************************************************      //~9B29I~
    @Override   //AlertI                                           //~9B29I~
	public int alertButtonAction(int Pbuttonid,int PactionID)      //~9B29I~
    {                                                              //~9B29I~
        if (Dump.Y) Dump.println("CompleteDlg.alertButtonAction swDrawn3R="+swDrawn3R+",buttonID="+Pbuttonid+",actionID="+PactionID);//~9B29I~//~0106R~//~0304R~
//        if (swConfirmSuspend)                                      //~0304I~//~0306R~
//        {                                                          //~0304I~//~0306R~
//            swConfirmedSuspendOK=(Pbuttonid==BUTTON_POSITIVE);     //~0304I~//~0306R~
//            if (Dump.Y) Dump.println("CompleteDlg.alertButtonAction confirmSuspend swConfirmedSuspendOK="+swConfirmedSuspendOK);//~0304I~//~0306R~
//            if (swConfirmedSuspendOK)                              //~0304I~//~0306R~
//                onClickOK();                                       //~0304I~//~0306R~
//        }                                                          //~0304I~//~0306R~
        if (!swDrawn3R)                                            //~0106I~
        {                                                          //~0106I~
			return super.alertButtonAction(Pbuttonid,PactionID);          //~0106I~
        }                                                          //~0106I~
    	if (Pbuttonid==BUTTON_POSITIVE)                            //~9B29I~
        {                                                          //~9B29I~
	        if (Dump.Y) Dump.println("CompleteDlg.alertButtonAction positive");//~9B29I~
		    drawn3RDrawn();                                        //~9B29I~
        }                                                          //~9B29I~
        else                                                       //~9B29I~
    	if (Pbuttonid==BUTTON_NEGATIVE)                            //~9B29I~
        {                                                          //~9B29I~
	        if (Dump.Y) Dump.println("CompleteDlg.alertButtonAction negative");//~9B29I~
        }                                                          //~9B29I~
        return 0;                                                  //~9B29I~
    }                                                              //~9B29I~
	//*************************************************************************//~9B29I~
    private void drawn3RDrawn()                                    //~9B29I~
    {                                                              //~9B29I~
        if (Dump.Y) Dump.println("CompleteDlg.drawn3RDrawn");      //~9B29I~
        dismiss();                                                 //~9B29I~
        AG.aComplete.setDrawn3R(true);                             //~9B29I~
        DrawnReqDlgHW.newInstance().show();                        //~9B29I~
    }                                                              //~9B29I~
	//*************************************************************************//~0302I~
    private boolean isErrLooser(Complete.Status Pstat)                     //~0302I~
    {                                                              //~0302I~
    	int eswn=Pstat.completeEswn;                               //~0302I~
		boolean rc=swsErrLooser[eswn];                             //~0302I~
    	if (Dump.Y) Dump.println("CompleteDlg.isErrLooser rc="+rc+",eswn="+eswn+",stat="+Pstat.toString());//~0302I~
        return rc;
    }                                                              //~0302I~
	//*************************************************************************//~0304I~
    private void confirmSuspend()                                  //~0304I~
    {                                                              //~0304I~
    	if (Dump.Y) Dump.println("CompleteDlg.confirnmSuspend");   //~0304I~
//        int titleid=TITLEID_REQ;                                   //~0304I~//~0306R~
//        int msgid=R.string.Alert_CompleteDlgConfirmSuspend;        //~0304I~//~0306R~
//        swConfirmSuspend=true;                                     //~0304I~//~0306R~
//        Alert.showAlert(titleid,msgid, BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE,this);//calback alertButtonAction//~0304I~//~0306R~
    	SuspendAlert.newInstance(TITLEID_REQ,this,typeNextGame);                        //~0306I~//~0307R~
    }                                                              //~0304I~
	//*************************************************************************//~va03I~
    public static void alertSuspended()                            //~va03I~
    {                                                              //~va03I~
    	if (Dump.Y) Dump.println("CompleteDlg.alertSuspended");    //~va03I~
    	SuspendAlert.showMsg(TITLEID_REQ);                         //~va03I~
    }                                                              //~va03I~
	//*************************************************************************//~0306I~
    @Override //SuspendAlertI                                        //~0306I~
	public void suspendAlertAction(int PbuttonID)                 //~0306I~
    {                                                              //~0306I~
        swConfirmedSuspendOK=(PbuttonID==BUTTON_POSITIVE);         //~0306I~
        if (Dump.Y) Dump.println("CompleteDlg.suspendAlertAction PbuttonID="+PbuttonID+",swConfirmedSuspendOK="+swConfirmedSuspendOK);//~0306I~
        if (swConfirmedSuspendOK)                                  //~0306I~
            onClickOK();                                           //~0306I~
    }                                                              //~0306I~
    //******************************************                   //~0328I~
    private int measureWidth(View PView)                           //~0328I~
    {                                                              //~0328I~
        int ww=UView.getMeasuredSize(PView,0/*size*/,View.MeasureSpec.UNSPECIFIED).x;//~0328I~
        if (Dump.Y) Dump.println("DrawnDlgLast.measureWidth ww="+ww+",getWidth="+PView.getWidth());//~0328I~
        return ww;                                                 //~0328I~
    }                                                              //~0328I~
}//class                                                           //~v@@@R~
