//*CID://+varbR~:                             update#=  797;       //~varbR~
//*****************************************************************//~v101I~
//2022/10/07 varb (Bug)dump when prop is initial at received rule(UButtonRG default is -1)//~varbI~
//2022/09/03 var0 summary rule setting dialog                      //~var0I~
//2022/08/22 vaqh allow optio to resume ignoring app version       //~vaqhI~
//2022/08/19 vaqa App version exchange require because rule set effect may changed//~vaqaI~
//2022/03/28 vam2 japanese on RuleSetting at initial               //~vam2I~
//2022/03/24 vakW rule update msg on dialog                        //~vakWI~
//2022/03/19 vakR On client, dismiss child dialog of RuleSetting when receved from server//~vakRI~
//2022/03/19 vakQ notify update of rule when client received       //~vakQI~
//2021/09/28 vaeh (Bug)syncDate is "Unknown" at first install, could not start training mode even runle dialog open/closed//~vaehI~
//2021/09/19 vae8 keep sharedPreference to external storage with PrefSetting item.//~vae8I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2021/08/02 vabs drop robot option to discard just taken,remains as test option//~vabsI~
//2021/04/25 va8k KataAgari OK for all Draw(+pon/kan/chii) regardless fix option//~va8kI~
//2021/04/17 va8b add YakuFix1/2 to related of drawnReqDlgLast     //~va8bI~
//2021/04/07 va7e change default to On for allow_robot_all         //~va66I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2021/01/07 va60 CalcShanten                                      //~va60I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//2020/10/13 va15 Add chk kuikae                                   //~va15I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                          //~v@@@R~//~9412R~
import android.content.DialogInterface;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.btmtest.BT.BTMulti;
import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.gui.CommonListener;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UEditText;                                  //~9903I~
import com.btmtest.gui.UButtonRG;
import com.btmtest.gui.URadioGroup;
import com.btmtest.gui.USpinBtn;
import com.btmtest.utils.Alert;
import com.btmtest.utils.Prop;                                     //~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.USpinner;
import com.btmtest.utils.UFile;
import com.btmtest.utils.UScoped;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import com.btmtest.game.Status;                                    //~9412I~

import static com.btmtest.AG.*;
import static com.btmtest.dialog.RuleSettingEnum.*;                  //~9404I~//~9412R~
//~v@@@I~
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.game.GConst.*;

public class RuleSetting extends SettingDlg                        //~v@@@R~
 //           implements USpinner.USpinnerI                          //~9407I~
              implements CommonListener.CommonListenerI            //~9902I~
{                                                                  //~2C29R~
	public  static final String PROP_NAME="RuleSetting";           //~9404I~
	private static final int    TITLEID=R.string.Title_RuleSetting;//~v@@@I~
	private static final int    LAYOUTID=R.layout.setting_rule;      //~v@@@I~//~9412R~
	private static final int    LAYOUTID_SMALLFONT=R.layout.setting_rule_theme;//~vac5I~
	private static final int    HELP_TITLEID=R.string.Title_RuleSetting;//~v@@@I~
	private static final String HELPFILE="RuleSetting";       //~v@@@I~//~9412R~//~9515R~//~9615R~//~9C13R~
                                                                   //~9408I~
                                                                   //~9414I~
    private static final int COLOR_BG_CHANGED_RULE=AG.getColor(R.color.bg_rule_updated);//~vakQI~
    private static final int MINUSPRIZE_MINEMS=3;                  //~9412I~
//  public  static final int POINT_FOR_REACH=1000;                 //~9427I~//~9511R~
                                                                   //~9408I~
//  private static final int[] rbIDOrderSamePoint=new int[]{R.id.rbOrderSamePointEswn,R.id.rbSamePointSpritCut,R.id.rbSamePointSpritUp};//~9407R~
    private static final int[] rbIDOrderSamePoint=new int[]{R.id.rbOrderSamePointEswn,R.id.rbSamePointSpritCut};//~9407I~
                                                                   //~9408I~
//    private static final int[] rbIDMultiRon=new int[]{R.id.rbMultiRonNo,R.id.rbMultiRonYes,R.id.rbMultiRon3Drawn};//~9408I~//~9409R~
//    private static final int MULTIRON_NO=0;                        //~9408I~//~9409R~
//    private static final int MULTIRON_YES=1;                       //~9408I~//~9409R~
//    private static final int MULTIRON_3DRAWN=2;                    //~9408I~//~9409R~
                                                                   //~v@@@I~
//    private static final String RK_KUITAN="Kuitan";                //~v@@@R~//~9404R~
//    private static final String RK_INITSCORE="InitScore";              //~v@@@I~//~9404R~
    //*****************************************************        //~9404I~
	private Button btnYakuList;                                    //~9515I~
	private Button btnOperation;                                   //~9624I~
    private USpinner spnInitialScore;                              //~v@@@R~
    private USpinner spnDupPoint;                                  //~9512I~
//  private EditText etInitialScoreTestE,etInitialScoreTestS,etInitialScoreTestW,etInitialScoreTestN;//~9425I~//~9903R~
    private UEditText etInitialScoreTestE,etInitialScoreTestS,etInitialScoreTestW,etInitialScoreTestN;//~9903I~
//  private UCheckBox cbKuitan;                                    //~v@@@I~//~9515R~
    private UCheckBox cbMinusStop,cbMinus0,cbMinusRobot;           //~9403I~
    private UCheckBox cbBird,cbAllowRobot;                                      //~9430I~//~9607R~
//  private UCheckBox cbAllowRobotAll;                             //~va66R~
//  private UCheckBox cbAllowRobotAllButton;                       //~va66R~
    private UCheckBox cbThinkRobot;                         //~va60I~
    private URadioGroup rgMinusPay,rgMinusStopByErr;               //~9414R~
    private URadioGroup rgBirdPayType;                             //~9602I~
    private URadioGroup rgRobotPay;                                //~9429I~
    private URadioGroup rgMultiRon;                                //~9408I~//~9409R~//~9513R~
//  private UCheckBox cbMultiRon;                                  //~9409I~//~9513R~
    private UCheckBox cbMultiRon3Drawn;                            //~9409I~//~9513R~
    private UCheckBox cbDrawnHW99,cbDrawnHW4W,cbDrawnHW4K,cbDrawnHW4R;//~9425I~
    private UCheckBox cbDrawnHW99Cont,cbDrawnHW4WCont,cbDrawnHW4KCont,cbDrawnHW4RCont,cbMultiRon3DrawnCont;//~9B13I~
//  private UCheckBox  cbMultiRonStickAll;                         //~9408I~//~9513R~
                                                                   //~9407I~
//  private TextView tvSyncDate,tvIDName;                          //~9405I~//~9903R~
    private TextView tvSyncDate;                                   //~9903I~
    private TextView tvAppVersion;                                 //~vaqaI~
//  private UEditText etIDName;                                    //~9903I~//~var0R~
    protected UEditText etIDName;                                  //~var0I~
                                                                   //~9407I~
//  private EditText etOrderPrizeTop,etOrderPrize2nd;              //~9407R~
    private USpinner spnOrderPrize;                                //~9407I~
    private URadioGroup rgOrderSamePoint;                          //~9407I~
    private USpinBtn sbMinusPrize;                                 //~9408I~
    private USpinBtn sbBird;                                       //~9430I~
//  private USpinBtn sbDelayPonKan,sbDelayTake,sbDelayLast;        //~9412I~//~9624R~
//  private USpinBtn sbDelayDiscard,sbTimeoutTake,sbTimeoutTakeKan;                 //~9622R~//~9623R~//~9624R~
                                                                   //~9413I~
//  private UCheckBox  cbDrawnManganYN,cbDrawnManganPending,cbDrawnManganNext;//~9422R~//~9424R~//~9505R~
//  private URadioGroup rgDrawnMangan;                             //~9505I~//~9516R~
//  private UCheckBox  cbDrawnHWNext,cbDrawnManganDropBird;                              //~9425I~//~9430R~//~9B13R~
    private UCheckBox  cbDrawnManganDropBird;                      //~9B13I~
//  private USpinner spnDrawnManganRank;                           //~9413I~//~9516R~
//  private String strReceivedProp;                                //~9616I~//~var0R~
    protected String strReceivedProp;                              //~var0I~
                                                                   //~9501I~
//	private URadioGroup rgScoreToPoint;                            //~9416I~//~9417R~
    protected UButtonRG bgScoreToPoint;                            //~9417I~
//  protected TextView  tvScoreToPoint;                            //~vakQR~
                                                                   //~9413I~
//  private UCheckBox  cbOpenReach,cbMissingReach;                 //~9427I~//~9517R~
                                                                   //~9501I~
    private USpinner spnGameSetType;                               //~9501I~
    private UCheckBox  cbClosableRon,cbClosablePending,cbClosableNotTop;//~9501I~
    private URadioGroup rgFinalLastDealerNotPending,rgFinalLastAllMinus;//~9501I~
    private URadioGroup rgEatChange;                               //~9516I~
    private UCheckBox  cbSpritPos;                                 //~9528I~
    private UCheckBox  cbRankMUp;                                  //~9528I~
    private URadioGroup  rgDora,rgDoraHidden,rgKanDora,rgKanDoraHidden,rgKanDoraOpen;//~9528R~//~9529R~
    private UCheckBox  cbUseRed5;                                  //~9C01I~
    private UCheckBox  cbPendingCont;                              //~9709I~
    private Prop parmProp;                                         //~9830I~
    public RuleSettingOperation aRuleSettingOperation;             //~vakRI~
    public RuleSettingYaku      aRuleSettingYaku;                  //~vakRI~
    //*****************************************************        //~1A6fI~//~9404R~
    private SettingDlg settingDlg;                                 //~v@@@I~
    private static int pendingBase=PAY_BY_NOT_PENDING;             //~v@@@I~//~9412R~
                                                                   //~9404I~
                                                                   //~9404I~
    private static final int DEFAULT_POINT_MINUSSTOP=5000;                        //~v@@@I~//~9408R~
                                                                   //~v@@@I~
    private static final int TS_E    =0;                           //~v@@@I~
    private static final int TS_EE   =1;                           //~v@@@I~
    private static final int TS_ES   =2;                           //~v@@@I~
    private static final int TS_EN   =3;                           //~v@@@I~
    private static final int TS_ESWN =4;                           //~v@@@I~
    private static int typeSets=TS_ES;                             //~v@@@I~
    private static boolean swLastGameNoDrawn=true;                 //~v@@@I~
    private static int     pointToNextEswn=30000;                   //~v@@@I~
    private static boolean swDrawnToNextEswn=true;    //after south is west,else same eswn//~v@@@I~
//    private static int[] orderPrize=new int[]{DEFAULT_ORDERPRIZE_TOP,DEFAULT_ORDERPRIZE_2ND,-DEFAULT_ORDERPRIZE_2ND,-DEFAULT_ORDERPRIZE_TOP};//~v@@@I~//~9407R~
    private static boolean swOrderByEswn=false;                    //~v@@@R~
                                                                   //~9405I~
//  private boolean swOpenAfterDismiss;                            //~9405R~//~var0R~
    protected boolean swOpenAfterDismiss;                          //~var0I~
//  private String senderYourName;                                 //~9405I~//~var0R~
    protected String senderYourName;                               //~var0I~
    public boolean swChangedYaku;                                  //~9516I~
    public boolean swChangedOperation;                             //~9624I~
    public boolean swChildInitializing;                            //~9B10I~
//  private boolean swAnyUpdate;                                   //~vakWI~//~var0R~
    protected boolean swAnyUpdate;                                 //~var0I~
    //******************************************                   //~v@@@M~
	public RuleSetting()
    {
        super();
        AG.aRuleSetting=this;                                      //~9404I~
        swRuleSetting=true;		//update AG.ruleSyncDate           //~9622I~
        if (Dump.Y) Dump.println("RuleSetting.defaultConstructor"); //~v@@@I~//~vaqhR~
    }
    //******************************************                   //~9902I~
    @Override //CommonListenerI                                    //~9B10I~
    public void onWidgetChanged(int PwidgetType,int PviewID,int Pvalue,String PstrValue)//~9902I~//~9903R~
    {                                                              //~9902I~
        if (Dump.Y) Dump.println("RuleSetting.onWidgetChanged swChildInitializing="+swChildInitializing+",type="+PwidgetType+",viewID="+Integer.toHexString(PviewID)+",value="+Pvalue+",strValue="+Utils.toString(PstrValue));//~9902R~//~9903R~//~9B10R~
    	if (!swChildInitializing)                                  //~9B10I~
		    tvShowStatus.setText(Utils.getStr(R.string.Info_RuleSettingUpdated));//~9902I~//~9B10R~
    }                                                              //~9902I~
//    public RuleSetting(int Pa)                                   //~v@@@R~
//    {                                                            //~v@@@R~
//        super(TITLEID,LAYOUTID,           //SettingDlg           //~v@@@R~
//                UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN,//~v@@@R~
//                HELP_TITLEID,HELP_FILENAME);                     //~v@@@R~
//    }                                                            //~v@@@R~
    //******************************************                 //~v@@@I~//~var0R~
    public static RuleSetting newInstance()                        //~v@@@I~
    {                                                              //~v@@@I~
        RuleSetting dlg=new RuleSetting();                         //~v@@@I~
//      dlg.ufdlg=UFDlg.newInstance((UFDlg)dlg,TITLEID,LAYOUTID,           //SettingDlg//~v@@@R~
//      UFDlg.setBundle(dlg,TITLEID,LAYOUTID,           //SettingDlg//~v@@@I~//~vac5R~
        UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),           //SettingDlg//~vac5I~
                    UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN,//~v@@@I~
                    HELP_TITLEID,HELPFILE);                   //~v@@@I~//~9C13R~
        return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~9405I~
//  public static RuleSetting newInstance(boolean PswReceived,String PsenderYourName)//~9405R~//~9616R~
    public static RuleSetting newInstance(boolean PswReceived,String PsenderYourName,String PreceivedProp)//~9616I~
    {                                                              //~9405I~
        if (Dump.Y) Dump.println("RuleSetting.newInstance swReceived="+PswReceived+",senderYourName="+PsenderYourName);//~9405I~
        RuleSetting dlg=newInstance();                             //~9405I~
        dlg.swReceived=PswReceived;	//save at OK btn               //~9405R~
        dlg.senderYourName=PsenderYourName;                        //~9405I~
        dlg.strReceivedProp=PreceivedProp;                         //~9616I~
        return dlg;                                                //~9405I~
    }                                                              //~9405I~
    //******************************************                   //~9408I~
    public static void showRuleInGame()                            //~9408I~
    {                                                              //~9408I~
	    showRuleInGame(null);                                      //~9830I~
    }                                                              //~9830I~
    //******************************************                   //~9830I~
    public static void showRuleInGame(Prop Pprop)                  //~9830I~
    {                                                              //~9830I~
        if (Dump.Y) Dump.println("RuleSetting.showRuleInGame");    //~9408I~
        if (Utils.isShowingDialogFragment(AG.aRuleSetting))        //~9408I~
        {                                                          //~9408I~
            UView.showToast(R.string.Err_AlreadyShowing);          //~9408I~
            return;                                                //~9408I~
        }                                                          //~9408I~
        RuleSetting dlg=newInstance();                             //~9408I~
        dlg.swShowInGame=true;	//not updatable                    //~9408I~
        dlg.parmProp=Pprop;                                        //~9830I~
        dlg.show();                                                //~9408I~
    }                                                              //~9408I~
    //******************************************                   //~9404I~
    //*from BTIO at client received prop from server               //~9404I~
    //******************************************                   //~9404I~
    public static void received(String PsenderYourName,String Pprops)                     //~9404I~//~9405R~
    {                                                              //~9404I~
        if (Dump.Y) Dump.println("RuleSetting.received senderYourName="+PsenderYourName+",props="+Pprops);//~9404R~//~9405R~
//      Prop p=AG.ruleProp;                                        //~9404I~//~9405M~//~9616R~
//      p.loadFromString(Pprops);                                  //~9404R~//~9405M~//~9616R~
//      AG.ruleSyncDate=p.getParameter(getKeyRS(RSID_SYNCDATE),"");//~9404I~//~9405M~//~9616R~
        if (isAppVersionUnmatch(Pprops))                           //~vaqaR~
        {                                                          //~vaqaR~
	        if (Dump.Y) Dump.println("RuleSetting.received AppVersion Unmatch");//~vaqaR~
        	return;                                                //~vaqaR~
        }                                                          //~vaqaR~
        AG.aBTMulti.setLockRuleSetting(false);	//release lock until reply OK at client//~9406I~
        if (Utils.isShowingDialogFragment(AG.aRuleSetting))        //~9404I~
        {                                                          //~9404I~
            AG.aRuleSetting.swOpenAfterDismiss=true;               //~9405I~
            AG.aRuleSetting.senderYourName=PsenderYourName;        //~9405I~
            AG.aRuleSetting.strReceivedProp=Pprops;                //~9616I~
	        AG.aRuleSetting.dismiss();                             //~9404I~
        }                                                          //~9404I~
        else                                                       //~9404I~
        {                                                          //~9405I~
//  		RuleSetting.newInstance(true/*swReceived*/,PsenderYourName).show();//~9405R~//~9616R~
//  		RuleSetting.newInstance(true/*swReceived*/,PsenderYourName,Pprops).show();//~9616I~//~var0R~
    		RuleSettingSumm.newInstance(true/*swReceived*/,PsenderYourName,Pprops).show();//~var0I~
        }                                                          //~9405I~
	    AG.aBTMulti.setRuleSettingSynchedAll(false/*swOK*/,""/*syncDate*/);//~9406I~
		UView.showToast(R.string.Info_ReceivedRuleSetting);        //~9404I~//~9405I~
    }                                                              //~9404I~
    //******************************************                   //~9406I~
    //*from BTIO at client received prop from server               //~9406I~
    //******************************************                   //~9406I~
    public static void receivedReply(int PthreadIdx,boolean PswOK,String PsyncDate)//~9406I~
    {                                                              //~9406I~
        String senderYourName=AG.aBTMulti.getYourName(PthreadIdx); //~9406I~
        if (Dump.Y) Dump.println("RuleSetting.receivedReply senderYourName="+senderYourName+",swOK="+PswOK+",syncDate="+PsyncDate);//~9406I~
        String strOK=Utils.getStr(PswOK ? R.string.SendBackOK : R.string.SendBackNG);//~9406I~
        boolean swOK=PswOK;                                        //~9406I~
        Prop p;                                                    //~9406I~
        String sd=PsyncDate;                                       //~9406I~
        if (swOK)                                                  //~9406I~
        {                                                          //~9406I~
        	p=AG.ruleProp;                                         //~9406I~
        	sd=p.getParameter(getKeyRS(RSID_SYNCDATE),"");         //~9406I~
	        if (Dump.Y) Dump.println("RuleSetting.receivedReply AG.syncDate="+sd);//~9406I~
        	if (!sd.equals(PsyncDate))                             //~9406R~
        		swOK=false;                                        //~9406I~
            else                                                   //~9406I~
            if (AG.aRuleSetting!=null)                             //~9406I~
            {                                                      //~9406I~
        		sd=AG.aRuleSetting.curProp.getParameter(getKeyRS(RSID_SYNCDATE),"");//~9406I~
		        if (Dump.Y) Dump.println("RuleSetting.receivedReply curProp.syncDate="+sd);//~9406I~
	        	if (!sd.equals(PsyncDate))                         //~9406R~
    	    		swOK=false;                                    //~9406I~
            }                                                      //~9406I~
        }                                                          //~9406I~
//      AG.aBTMulti.setRuleSyncStatus(PthreadIdx,swOK,sd);         //~9406I~//~0323R~
        AG.aBTMulti.setRuleSyncStatusReply(PthreadIdx,swOK,sd);    //~0323I~
        if (PswOK && !swOK)                                        //~9406I~
	        UView.showToast(AG.resource.getString(R.string.Info_RuleSynchResponseReceivedResendRequired,senderYourName));//~9406I~
        else                                                       //~9406I~
	        UView.showToast(AG.resource.getString(R.string.Info_RuleSynchResponseReceivedFrom,senderYourName,strOK));//~9406R~
        if (BTMulti.isRuleSynched())	//all SS_OK                               //~9406I~//~9621R~
        {                                                          //~9406I~
		    AG.aBTMulti.setLockRuleSetting(true);	//release on server//~9406R~
	        AG.aBTMulti.setRuleSettingSynchedAll(true/*swOK*/,PsyncDate);//~9406I~
        	RuleSetting.notifySynched(true,PsyncDate);	//enable startGame on client//~9406R~
        }                                                          //~9406I~
        repaint();                                                 //~9406R~
    }                                                              //~9406I~
    //*************************************************************************//~9406I~
    //*from BTIO at client received from server when sync from all client//~9406I~
    //*************************************************************************//~9406I~
    public static void receivedSyncAll(int PthreadIdx,boolean PswOK,String PsyncDate)//~9406I~
    {                                                              //~9406I~
        String senderYourName=AG.aBTMulti.getYourName(PthreadIdx); //~9406I~
        if (Dump.Y) Dump.println("RuleSetting.receivedSyncAll senderYourName="+senderYourName+",swOK="+PswOK+",syncDate="+PsyncDate);//~9406I~
        AG.aBTMulti.setRuleSettingSynchedAll(PswOK,PsyncDate);     //~9406I~
        if (BTMulti.isServerDevice())                              //~9617I~
        	UView.showToast(Utils.getStr(R.string.Info_ResyncRuleClientChanged,senderYourName));//~9617I~
        repaint();                                                 //~9406I~
    }                                                              //~9406I~
    //*****************************************************************                   //~9404I~//~9406R~
    //*from MainActivity by btnGame after member checked           //~9406R~
    //*rc:true:NG(changed)                                         //~9406R~
    //*****************************************************************//~9406I~
    public static boolean chkChangedRule()                             //~9404I~//~9406R~
    {                                                              //~9404I~
    	boolean rc;                                                //~9406I~
        if ((TestOption.option & TestOption.TO_RULE_NOSYNC)!=0)   //TODO test//~9406I~
        	return false;                                          //~9406I~
//      rc=!(AG.aBTMulti.isLockRuleSetting() && AG.aBTMulti.isRuleSettingSynchedAll()); //continue if true//~9406R~
//      rc=!(AG.aBTMulti.isRuleSettingSynchedAll()); //continue if true//~9406I~//~9621R~
        rc=!AG.aBTMulti.isRuleSynched(); //by SS_OK                //~9621I~
        if (rc)                                                    //~9406R~
        {                                                          //~9406I~
            String serverYourName=BTMulti.getYourNameServer();     //~9406I~
            if (BTMulti.isServerDevice())                          //~9406I~
            {                                                      //~9406I~
                Prop p=AG.ruleProp;                                        //~9404I~//~9406R~
                String sync=p.getParameter(getKeyRS(RSID_SYNCDATE),"");   //~9404I~//~9406R~
                if (Dump.Y) Dump.println("RuleSetting.chkChangedRule AG="+AG.ruleSyncDate+",prop="+sync);//~9404I~//~9406R~
                if (AG.ruleSyncDate.compareTo(sync)!=0)                    //~9404I~//~9406R~
                {                                                          //~9404I~//~9406R~
                    if (AG.ruleSyncDate.compareTo("")==0)          //~9406R~
                        UView.showToast(Utils.getStr(R.string.Info_ResyncRuleAtFirst,serverYourName));//~9406R~
                    else                                           //~9406R~
                        UView.showToast(Utils.getStr(R.string.Info_ResyncRule,serverYourName));             //~9404I~//~9406R~
                }                                                          //~9404I~//~9406R~
                else                                               //~9406I~
            		UView.showToast(Utils.getStr(R.string.Info_RuleSyncNotAll,serverYourName));//~9406I~
            }                                                      //~9406I~
            else    //client                                       //~9406I~
            {                                                      //~9406I~
            	if (AG.ruleSyncDate.compareTo("")==0)              //~9406I~
                	UView.showToast(Utils.getStr(R.string.Info_ResyncRuleAtFirst,serverYourName));//~9406I~
                else                                               //~9406I~
            		UView.showToast(Utils.getStr(R.string.Info_RuleSyncNotAll,serverYourName));//~9406I~
            }                                                      //~9406I~
        }                                                          //~9406I~
        if (Dump.Y) Dump.println("RuleSetting.chkChangedRule rc="+rc);//~9406I~
        return rc;                                              //~9404I~//~9406R~
    }                                                              //~9404I~
    //******************************************                   //~9404I~
    @Override                                                      //~9404I~
    public void onDismissDialog()                                  //~9404I~
    {                                                              //~9404I~
        if (Dump.Y) Dump.println("RuleSetting.onDismissDialog swOpenAfterDismiss="+swOpenAfterDismiss);     //~9404I~//~9616R~//~vakRR~
        CommonListener.resetListener();                            //~9902I~
    	RuleSetting parent=AG.aRuleSetting;                                      //~9404I~//~vakRR~
    	AG.aRuleSetting=null;                                      //~vakRI~
	    if (swOpenAfterDismiss)                                    //~9405I~
        {                                                          //~9405I~
        	if (Utils.isShowingDialogFragment(parent.aRuleSettingOperation))//~vakRI~
        		parent.aRuleSettingOperation.dismiss();            //~vakRI~
        	if (Utils.isShowingDialogFragment(parent.aRuleSettingYaku))//~vakRI~
        		parent.aRuleSettingYaku.dismiss();                 //~vakRI~
		    swOpenAfterDismiss=false;                              //~9405I~
//  		RuleSetting.newInstance(true/*swReceived*/,senderYourName).show();//~9405R~//~9616R~
    		RuleSetting.newInstance(true/*swReceived*/,senderYourName,strReceivedProp).show();//~9616I~
        }                                                          //~9405I~
        else                                                       //~9623I~
        {                                                          //~9623I~
        	if (AG.aBTMulti.chkRuleSync(true/*Pmsg*/))             //~9623I~
	        	AG.aMainView.clearMsg();                           //~9623I~
        }                                                          //~9623I~
    }                                                              //~9404I~
    //******************************************                   //~9902I~
    //*when dialog is shown                                        //~9902I~
    //******************************************                   //~9902I~
    private void setOnShowListener()                               //~9902I~
    {                                                              //~9902I~
    	androidDlg.setOnShowListener(                              //~9902I~
        	new DialogInterface.OnShowListener()                    //~9902I~
            {                                                      //~9902I~
                @Override                                          //~9902I~
                public void onShow(DialogInterface Pdlg)           //~9902I~
                {                                                  //~9902I~
                    if (Dump.Y) Dump.println("RuleSetting.onShow swReceived="+swReceived+",inGame="+swShowInGame);//~9902I~
                    if (!swReceived && !swShowInGame)              //~9902I~
                        CommonListener.setListener((CommonListener.CommonListenerI)(AG.aRuleSetting));//~9902R~
            	}                                                  //~9902I~
            }                       );                             //~9902I~
    }                                                              //~9902I~
    //******************************************                   //~v@@@M~
    @Override
    protected void initLayout(View PView)                            //~v@@@I~//~9403R~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("RuleSetting.initLayoutAdditional");          //~v@@@I~//~9403R~//~9616R~
//      swFixed=swReceived || BTMulti.isRuleSynched();             //~9405I~//~9406R~
        swFixed=setFixed();                                        //~9406I~
        super.initLayout(PView);                                   //~9403R~
        btnYakuList=UButton.bind(PView,R.id.btnYakuList,this);     //~9515I~
        btnOperation=UButton.bind(PView,R.id.btnOperation,this);   //~9624I~
        setupLayout(PView);                                        //~9403I~
        setInitialValue();                                          //~v@@@I~
        setTitle();                                                //~9405I~
        setOnShowListener();                                       //~9902I~
    }                                                              //~v@@@M~
    //******************************************                   //~9406I~
    private boolean setFixed()                                     //~9406I~
    {                                                              //~9406I~
        boolean rc=swReceived || swShowInGame;                                     //~9406I~//~9408R~
        if (!rc)                                                   //~9406I~
        {                                                          //~9406I~
//      	if (Status.isGaming())                                 //~9406I~//~9730R~
//          	if (!Status.isGameOver())                          //~9406I~//~9730R~
        	if (Status.isGamingNow())	//before gameover          //~9730I~
	            	rc=true;                                       //~9406I~
        }                                                          //~9406I~
        if (Dump.Y) Dump.println("RuleSetting.setFixed() rc="+rc+",swReceived="+swReceived+",swShowIngame="+swShowInGame);   //~9406I~//~9421R~//~9616R~
        return rc;                                                 //~9406I~
    }                                                              //~9406I~
    //******************************************                   //~9405I~
    protected void setTitle()                                      //~9405I~
    {                                                              //~9405I~
        if (Dump.Y) Dump.println("RuleSetting.setTitle swReceived="+swReceived);//~9405I~//~9412R~
        if (!swReceived)                                           //~9405I~
        	return;                                                //~9405I~
//		Spanned st=Html.fromHtml(AG.resource.getString(R.string.Title_RuleSettingReceived,Utils.getStr(TITLEID),senderYourName));//~9223I~//~v@@@I~//~va40R~
  		Spanned st=Utils.fromHtml(AG.resource.getString(R.string.Title_RuleSettingReceived,Utils.getStr(TITLEID),senderYourName));//~va40I~
        getDialog().setTitle(st);                                              //~9405I~
    }                                                              //~9405I~
	//*****************                                            //~9403I~
    protected void setupLayout(View PView)                         //~9403I~
    {                                                              //~9403I~
        TextView tv;                                               //~9416I~
        String str;                                                //~9416I~
    	LinearLayout llSpinBtn;                                    //~9412I~
        if (Dump.Y) Dump.println("RuleSetting.setupLayout");         //~9403I~//~9412R~
    //*                                                            //~9405I~
        tvSyncDate=(TextView)       UView.findViewById(PView,R.id.tvSyncDate);//~9405I~
        tvAppVersion=(TextView)     UView.findViewById(PView,R.id.tvAppVersion);//~vaqaI~
//      tvIDName  =(TextView)       UView.findViewById(PView,R.id.tvIDName);//~9405I~//~9903R~
        etIDName  =UEditText.bind(PView,R.id.etIDName,null/*CommonListener*/);//~9903I~
//		tvSyncDate.requestFocus();	//to avoid kbd up for EditText; by xml focusable attribute//~9405R~
    //*                                                            //~9405I~
        spnInitialScore  =          new USpinner(PView,R.id.InitialScore);//~9403I~
//      etInitialScoreTestE=(EditText)UView.findViewById(PView,R.id.etInitialScoreTestE);//~9425I~//~9903R~
//      etInitialScoreTestS=(EditText)UView.findViewById(PView,R.id.etInitialScoreTestS);//~9425I~//~9903R~
//      etInitialScoreTestW=(EditText)UView.findViewById(PView,R.id.etInitialScoreTestW);//~9425I~//~9903R~
//      etInitialScoreTestN=(EditText)UView.findViewById(PView,R.id.etInitialScoreTestN);//~9425I~//~9903R~
        etInitialScoreTestE=UEditText.bind(PView,R.id.etInitialScoreTestE,null/*CommonListener*/);//~9903I~
        etInitialScoreTestS=UEditText.bind(PView,R.id.etInitialScoreTestS,null/*CommonListener*/);//~9903I~
        etInitialScoreTestW=UEditText.bind(PView,R.id.etInitialScoreTestW,null/*CommonListener*/);//~9903I~
        etInitialScoreTestN=UEditText.bind(PView,R.id.etInitialScoreTestN,null/*CommonListener*/);//~9903I~
        spnDupPoint  =          new USpinner(PView,R.id.spnDupPoint);//~9512I~
        if ((TestOption.option & TestOption.TO_INITIALSCORE)!=0)   //~9425I~
	        ((LinearLayout)UView.findViewById(PView,R.id.llInitialScoreTest)).setVisibility(View.VISIBLE);//~9425I~
        tv =(TextView)              UView.findViewById(PView,R.id.tvDebt);//~9416R~
        str=Utils.getStr(R.string.Label_Debt,getDebt());    //~9416I~
        tv.setText(str);                                           //~9416I~
//      cbKuitan         =          new UCheckBox(PView,R.id.Kuitan);//~9403I~//~9515R~
    //*minusPay                                                    //~9403I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBMinusPrize);//~9412I~
    	sbMinusPrize=USpinBtn.newInstance(llSpinBtn,DEFAULT_MINUSPRIZE_MIN,DEFAULT_MINUSPRIZE_MAX,DEFAULT_MINUSPRIZE_INC,DEFAULT_MINUSPRIZE_INIT);//~9408R~//~9412R~
        sbMinusPrize.setMinEms(MINUSPRIZE_MINEMS);                 //~9412I~
        cbMinusStop=new UCheckBox(PView,R.id.cbMinusStop);         //~9403R~
        cbMinus0=new UCheckBox(PView,R.id.cbMinus0);               //~9403R~
        rgMinusPay=new URadioGroup(PView,R.id.rgMinusPay,0,rbIDMinusPay);//~9403I~
        rgMinusStopByErr=new URadioGroup(PView,R.id.rgMinusStopByErr,0,rbIDMinusStopByErr);//~9414I~
    //*orderPrize                                                  //~9407I~
        spnOrderPrize  =                 new USpinner(PView,R.id.spnOrderPrize);//~9407I~
        spnOrderPrize.setArray(R.array.OrderPrize);                //~9407I~
//      etOrderPrizeTop=(EditText)       UView.findViewById(PView,R.id.etOrderPrizeTop);//~9407R~
//      etOrderPrize2nd=(EditText)       UView.findViewById(PView,R.id.etOrderPrize2nd);//~9407R~
        rgOrderSamePoint=new URadioGroup(PView,R.id.rgOrderSamePoint,0,rbIDOrderSamePoint);//~9407I~
    //*multiRon                                                    //~9408I~
        rgMultiRon=new URadioGroup(PView,R.id.rgMultiRon,0/*defaultIdx*/,rbIDMultiRon/*IDs*/);//~9408I~//~9409R~//~9513R~
//      cbMultiRon=new UCheckBox(PView,R.id.cbMultiRonYes);           //~9409I~//~9513R~
//      cbMultiRonStickAll=new UCheckBox(PView,R.id.cbMultiRonStickAll);//~9408I~//~9513R~
    //*drawnHW                                                     //~9425I~
//      cbMultiRon3Drawn=new UCheckBox(PView,R.id.cbMultiRon3Drawn);//~9409I~//~9425R~
        cbMultiRon3Drawn=new UCheckBox(PView,R.id.cb3Ron);         //~9425I~
        cbDrawnHW99=new UCheckBox(PView,R.id.cb99Tile);            //~9425R~
        cbDrawnHW4W=new UCheckBox(PView,R.id.cb4Wind);             //~9425R~
        cbDrawnHW4K=new UCheckBox(PView,R.id.cb4Kan);              //~9425R~
        cbDrawnHW4R=new UCheckBox(PView,R.id.cb4Reach);            //~9425R~
        cbMultiRon3DrawnCont=new UCheckBox(PView,R.id.cb3RonCont); //~9B13I~
        cbDrawnHW99Cont=new UCheckBox(PView,R.id.cb99TileCont);    //~9B13I~
        cbDrawnHW4WCont=new UCheckBox(PView,R.id.cb4WindCont);     //~9B13I~
        cbDrawnHW4KCont=new UCheckBox(PView,R.id.cb4KanCont);      //~9B13I~
        cbDrawnHW4RCont=new UCheckBox(PView,R.id.cb4ReachCont);    //~9B13I~
//    //*delay                                                       //~9412I~//~9624R~
//        llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBDelayPonKan);//~9412I~//~9624R~
//        sbDelayPonKan=USpinBtn.newInstance(llSpinBtn,DEFAULT_DELAY_PONKAN_MIN,DEFAULT_DELAY_PONKAN_MAX,DEFAULT_DELAY_PONKAN_INC,DEFAULT_DELAY_PONKAN);//~9412I~//~9624R~
//        llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBDelayTake);//~9412I~//~9624R~
//        sbDelayTake=USpinBtn.newInstance(llSpinBtn,DEFAULT_DELAY_TAKE_MIN,DEFAULT_DELAY_TAKE_MAX,DEFAULT_DELAY_TAKE_INC,DEFAULT_DELAY_TAKE);//~9412I~//~9624R~
//        llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBDelayLast);//~9412I~//~9624R~
//        sbDelayLast=USpinBtn.newInstance(llSpinBtn,DEFAULT_DELAY_LAST_MIN,DEFAULT_DELAY_LAST_MAX,DEFAULT_DELAY_LAST_INC,DEFAULT_DELAY_LAST);//~9412R~//~9624R~
//        llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBDelayDiscard);//~9622I~//~9624R~
//        sbDelayDiscard=USpinBtn.newInstance(llSpinBtn,DEFAULT_DELAY_DISCARD_MIN,DEFAULT_DELAY_DISCARD_MAX,DEFAULT_DELAY_DISCARD_INC,DEFAULT_DELAY_DISCARD);//~9622I~//~9624R~
//        llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBTimeoutTake);//~9622R~//~9624R~
//        sbTimeoutTake=USpinBtn.newInstance(llSpinBtn,DEFAULT_TIMEOUT_TAKE_MIN,DEFAULT_TIMEOUT_TAKE_MAX,DEFAULT_TIMEOUT_TAKE_INC,DEFAULT_TIMEOUT_TAKE);//~9622I~//~9624R~
//        llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBTimeoutTakeKan);//~9623I~//~9624R~
//        sbTimeoutTakeKan=USpinBtn.newInstance(llSpinBtn,DEFAULT_TIMEOUT_TAKEKAN_MIN,DEFAULT_TIMEOUT_TAKEKAN_MAX,DEFAULT_TIMEOUT_TAKEKAN_INC,DEFAULT_TIMEOUT_TAKEKAN);//~9623I~//~9624R~
    //*drawnMangan                                                 //~9413I~
//        cbDrawnManganYN=new UCheckBox(PView,R.id.cbDrawnManganYN); //~9413I~//~9505R~
//        cbDrawnManganPending=new UCheckBox(PView,R.id.cbDrawnManganPending);//~9413I~//~9422R~//~9505R~
//        cbDrawnManganNext=new UCheckBox(PView,R.id.cbDrawnManganNext);//~9422I~//~9424R~//~9505R~
//        cbDrawnManganDropBird=new UCheckBox(PView,R.id.cbDrawnManganDropBird);//~9430I~//~9505R~
//      rgDrawnMangan=new URadioGroup(PView,R.id.rgDrawnMangan,0,rbIDDrawnMangan);//~9505I~//~9516R~
//      spnDrawnManganRank =new USpinner(PView,R.id.spnDrawnManganRank);//~9413I~//~9516R~
//      spnDrawnManganRank.setArray(R.array.DrawnManganRank);          //~9307I~//~9308M~//~9413R~
//      spnDrawnManganRank.setArray(rankDrawnMangan);              //~9413I~//~9516R~
    //*drawnHW                                                     //~9425I~
//      cbDrawnHWNext=new UCheckBox(PView,R.id.cbDrawnHWNext);     //~9425I~//~9B13R~
    //*ScoreToPoint                                                //~9416I~
//      rgScoreToPoint=new URadioGroup(PView,R.id.rgScoreToPoint,0/*default*/,rbIDScoreToPoint);//~9416R~//~9417R~
        bgScoreToPoint=new UButtonRG((ViewGroup)PView,rbIDScoreToPoint.length);//~9417I~
//      tvScoreToPoint=(TextView)UView.findViewById(PView,R.id.tvS2PLast);//~vakQR~
	    for (int ii=0;ii<rbIDScoreToPoint.length;ii++)             //~9417I~
			bgScoreToPoint.add(IDScoreToPoint[ii],rbIDScoreToPoint[ii]);//~9417I~
        bgScoreToPoint.setDefaultChk(S2P_NO);                      //~9417I~
//    //*Reach                                                       //~9427I~//~9517R~
//        cbOpenReach=new UCheckBox(PView,R.id.cbOpenReach);         //~9427I~//~9517R~
//        cbMissingReach=new UCheckBox(PView,R.id.cbMissingReach);   //~9427I~//~9517R~
    //*robot                                                       //~9429I~
        cbAllowRobot=new UCheckBox(PView,R.id.cbAllowRobot);       //~9403R~//~9429I~//~9607R~
//      cbAllowRobotAll=new UCheckBox(PView,R.id.cbAllowRobotAll); //~va66R~
//      cbAllowRobotAllButton=new UCheckBox(PView,R.id.cbAllowRobotAllButton);//~va66R~
        cbThinkRobot=new UCheckBox(PView,R.id.cbThinkRobot);       //~va60I~
        cbMinusRobot=new UCheckBox(PView,R.id.cbMinusRobot);       //~9607I~
        rgRobotPay=new URadioGroup(PView,R.id.rgRobotPay,0,rbIDRobotPay);//~9429I~
    //*bird                                                        //~9430I~
        rgBirdPayType=new URadioGroup(PView,R.id.rgBirdPayType,0,rbIDBirdPayType);//~9602I~
        cbBird     =new UCheckBox(PView,R.id.cbBird);              //~9430I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBBird);//~9430I~
    	sbBird=USpinBtn.newInstance(llSpinBtn,DEFAULT_BIRD_MIN,DEFAULT_BIRD_MAX,DEFAULT_BIRD_INC,DEFAULT_BIRD);//~9430I~
    //*FinalLast                                                   //~9501I~
    	cbClosableRon=new UCheckBox(PView,R.id.cbClosableRon);     //~9501I~
    	cbClosablePending=new UCheckBox(PView,R.id.cbClosablePending);//~9501I~
    	cbClosableNotTop=new UCheckBox(PView,R.id.cbClosableNotTop);//~9501I~
        spnGameSetType =new USpinner(PView,R.id.spnGameSetType);   //~9501I~
        spnGameSetType.setArray(strsGameSetType);                  //~9501I~
        rgFinalLastDealerNotPending=new URadioGroup(PView,R.id.rgFinalLastDealerNotPending,0,rbIDFinalLastDealerNotPending);//~9501I~
        rgFinalLastAllMinus=new URadioGroup(PView,R.id.rgFinalLastAllMinus,0,rbIDFinalLastDealerAllMinus);//~9501I~
    //*EatChange                                                   //~v@@@I~//~9516M~
        rgEatChange=new URadioGroup(PView,R.id.rgEatChange,0,rbsEatChange);//~v@@@I~//~9516M~
    //*SpritPos                                                    //~9528I~
    	cbSpritPos=new UCheckBox(PView,R.id.cbSpritPos);           //~9528I~
    //*RankMUp                                                     //~9528I~
    	cbRankMUp=new UCheckBox(PView,R.id.cbRankMUp);             //~9528I~
    //*Dora                                                        //~9528I~
        rgDora=new URadioGroup(PView,R.id.rgDora,0/*UserParm*/,rbIDDora);//~9528I~
        rgDoraHidden=new URadioGroup(PView,R.id.rgDoraHidden,0/*UserParm*/,rbIDDoraHidden);//~9528I~
        rgKanDora=new URadioGroup(PView,R.id.rgKanDora,0/*UserParm*/,rbIDKanDora);//~9528I~
        rgKanDoraHidden=new URadioGroup(PView,R.id.rgKanDoraHidden,0/*UserParm*/,rbIDKanDoraHidden);//~9528I~
        rgKanDoraOpen=new URadioGroup(PView,R.id.rgKanDoraOpenTiming,0/*UserParm*/,rbIDMinKanDoraOpen);//~9529I~
    	cbUseRed5=new UCheckBox(PView,R.id.cbUseRed5);             //~9C01I~
    //*PendingCont                                                 //~9709I~
    	cbPendingCont=new UCheckBox(PView,R.id.cbPendingCont);     //~9709I~
    }                                                              //~9403I~
	//*****************                                            //~var0I~
	//*Detail will override                                        //~var0I~
	//*****************                                            //~var0I~
    protected void getCurProp()                                    //~var0I~
    {                                                              //~var0I~
        curProp=AG.ruleProp.getClone();                            //~var0I~
        if (Dump.Y) Dump.println("RuleSetting.getCurProp curProp="+curProp);//~var0I~
    }                                                              //~var0I~
	//*****************                                                //~1613I~//~v@@@I~
    protected void setInitialValue()                                 //~v@@@I~
    {                                                              //~1613I~//~v@@@M~
        if (Dump.Y) Dump.println("RuleSetting.setInitialvalue swReceived="+swReceived);             //~v@@@I~//~9827R~//~vaehR~
        propCmt=PROP_NAME;                                     //~v@@@I~//~9405R~
//      spnInitialScore.setArray(R.array.InitialScore);            //~v@@@M~//~9416R~
        spnInitialScore.setArray(strInitialScore);                 //~9416I~
        spnDupPoint.setArray(strDupPoint);                         //~9512I~
//      curProp=AG.ruleProp.getClone();                            //~v@@@M~//~var0R~
        getCurProp();                                              //~var0I~
        if (swReceived)                                            //~9616I~
        {                                                          //~vaehI~
        	curProp.loadFromString(strReceivedProp);               //~9616I~
        }                                                          //~vaehI~
        else                                                       //~9830I~
        if (parmProp!=null)  	//for resume of interrupted geme from historyData//~9830I~
		    curProp=parmProp;                                      //~9830I~
        setupDialog();                                             //~v@@@I~
        swAnyUpdate=false;                                         //~vakWI~
        if (swReceived)                                            //~vakQI~
        {                                                          //~vakQI~
            chkUpdate();                                           //~vakQM~
            chkUpdateOperYaku();                                   //~vakQI~
            showUpdate(swAnyUpdate);                               //~vakWI~
        }                                                          //~vakQI~
    }                                                              //~v@@@I~
	//*****************                                            //~v@@@I~
    private void setupDialog()                                    //~v@@@I~
    {                                                              //~v@@@I~
        properties2Dialog(curProp);                      //~v@@@I~//~9403R~//~9404R~
//      setupByStatic(); //TODO test                                //~9403I~//~9404R~
    }                                                              //~1613I~//~v@@@M~
    //*******************************************************      //~v@@@I~
    //*setupdialog from property                                   //~v@@@I~
    //*******************************************************      //~v@@@I~
    @Override //SettingDlg                                         //~v@@@I~
    protected void properties2Dialog(Prop Pprop)                     //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.properties2Dialog Pprop="+Pprop);                   //~v@@@I~//~9412R~//~vaehR~//~var0R~
//      tvSyncDate.setText(Pprop.getParameter(getKeyRS(RSID_SYNCDATE_FORMATTED),"ありありなど"));//~9405I~//~9515R~//~vam2R~
        tvSyncDate.setText(Pprop.getParameter(getKeyRS(RSID_SYNCDATE_FORMATTED),Utils.getStr(R.string.RuleNotInitialized)));//~vam2I~
        tvAppVersion.setText(Pprop.getParameter(getKeyRS(RSID_APPVERSION_MIN),"Unknown AppVer"));//~vaqaR~
//      tvIDName.setText(Pprop.getParameter(getKeyRS(RSID_IDNAME),"RuleA"));//~9405R~//~9826R~//~9903R~
//      etIDName.setText(Pprop.getParameter(getKeyRS(RSID_IDNAME),"RuleA"));//~9903I~//~9905R~
//      etIDName.setText(Pprop.getParameter(getKeyRS(RSID_IDNAME),"RuleA"),true/*swLostFocus*/);//~9905I~//~vam2R~
        etIDName.setText(Pprop.getParameter(getKeyRS(RSID_IDNAME),Utils.getStr(R.string.SampleRuleID)),true/*swLostFocus*/);//~vam2I~
        if (swFixed)                                               //~9405I~
//      	tvIDName.setEnabled(false);                            //~9405I~//~9903R~
        	etIDName.setEnabled(false);                            //~9903I~
//      cbKuitan.setStateInt(Pprop.getParameter(RK_KUITAN,0));     //~v@@@R~//~9404R~
//      cbKuitan.setStateInt(Pprop.getParameter(getKeyRS(RSID_KUITAN),0),swFixed);//~9404I~//~9405R~//~9515R~
//      spnInitialScore.select(Pprop.getParameter(RK_INITSCORE,0));//~v@@@R~//~9404R~
        spnInitialScore.select(Pprop.getParameter(getKeyRS(RSID_INITSCORE),0),swFixed);//~9404I~//~9405R~
        etInitialScoreTestE.setText(Integer.toString(Pprop.getParameter(getKeyRS(RSID_INITSCORE_TESTE),25000)));//~9425R~
        etInitialScoreTestS.setText(Integer.toString(Pprop.getParameter(getKeyRS(RSID_INITSCORE_TESTS),25000)));//~9425I~
        etInitialScoreTestW.setText(Integer.toString(Pprop.getParameter(getKeyRS(RSID_INITSCORE_TESTW),25000)));//~9425I~
        etInitialScoreTestN.setText(Integer.toString(Pprop.getParameter(getKeyRS(RSID_INITSCORE_TESTN),25000)));//~9425I~
        spnDupPoint.select(Pprop.getParameter(getKeyRS(RSID_POINT_DUP),0),swFixed);//~9512I~
    //*minusPay                                                    //~9404I~
        sbMinusPrize.setVal(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP_POINT),DEFAULT_MINUSPRIZE_INIT),swFixed);//~9408I~
        cbMinusStop.setStateInt(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP),0),swFixed);//~9404R~//~9405R~
        cbMinus0.setStateInt(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP_0),0),swFixed);//~9404R~//~9405R~
        rgMinusPay.setCheckedID(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP_PAYTYPE),0),swFixed);//~9404I~//~9405R~
        rgMinusStopByErr.setCheckedID(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP_BYERR),0),swFixed);//~9414I~
    //*orderPrize                                                  //~9407I~
//      etOrderPrizeTop.setText(Pprop.getParameter(getKeyRS(RSID_ORDERPRIZE_TOP),Integer.toString(DEFAULT_ORDERPRIZE_TOP)));//~9407R~
//      etOrderPrize2nd.setText(Pprop.getParameter(getKeyRS(RSID_ORDERPRIZE_2ND),Integer.toString(DEFAULT_ORDERPRIZE_2ND)));//~9407R~
        spnOrderPrize.select(Pprop.getParameter(getKeyRS(RSID_ORDERPRIZE),DEFAULT_ORDERPRIZE),swFixed);//~9407I~//~9819R~
        rgOrderSamePoint.setCheckedID(Pprop.getParameter(getKeyRS(RSID_ORDERPRIZE_SAMEPOINT),0),swFixed);//~9407I~
    //*multiRon                                                    //~9408I~
        rgMultiRon.setCheckedID(Pprop.getParameter(getKeyRS(RSID_MULTIRON),0/*defaultIdx*/),swFixed);//~9408I~//~9409R~//~9513R~
//      cbMultiRon.setStateInt(Pprop.getParameter(getKeyRS(RSID_MULTIRON),0/*defaultIdx*/),swFixed);//~9409I~//~9513R~
//      cbMultiRonStickAll.setStateInt(Pprop.getParameter(getKeyRS(RSID_MULTIRON_STICKALL),0),swFixed);//~9408I~//~9513R~
    //*drawnHW                                                     //~9425I~
        cbMultiRon3Drawn.setStateInt(Pprop.getParameter(getKeyRS(RSID_MULTIRON3DRAWN),1/*default true*/),swFixed);//~9409I~//~9425I~
        cbDrawnHW99.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW99),1/*default true*/),swFixed);//~9425R~
        cbDrawnHW4W.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4W),1/*default true*/),swFixed);//~9425R~
        cbDrawnHW4K.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4K),1/*default true*/),swFixed);//~9425R~
        cbDrawnHW4R.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4R),1/*default true*/),swFixed);//~9425R~
        cbMultiRon3DrawnCont.setStateInt(Pprop.getParameter(getKeyRS(RSID_MULTIRON3DRAWNC),0/*default F*/),swFixed);//~9B13I~
        cbDrawnHW99Cont.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW99C),1/*default T*/),swFixed);//~9B13I~
        cbDrawnHW4WCont.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4WC),1/*default T*/),swFixed);//~9B13I~
        cbDrawnHW4KCont.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4KC),0/*default F*/),swFixed);//~9B13I~
        cbDrawnHW4RCont.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4RC),0/*default F*/),swFixed);//~9B13I~
//    //*delay                                                       //~9412I~//~9624R~
//        sbDelayPonKan.setVal(Pprop.getParameter(getKeyRS(RSID_DELAY_PONKAN),DEFAULT_DELAY_PONKAN),swFixed);//~9412I~//~9624R~
//        sbDelayTake.setVal(Pprop.getParameter(getKeyRS(RSID_DELAY_TAKE),DEFAULT_DELAY_TAKE),swFixed);//~9412I~//~9624R~
//        sbDelayLast.setVal(Pprop.getParameter(getKeyRS(RSID_DELAY_LAST),DEFAULT_DELAY_LAST),swFixed);//~9412I~//~9624R~
//        sbDelayDiscard.setVal(Pprop.getParameter(getKeyRS(RSID_DELAY_DISCARD),DEFAULT_DELAY_DISCARD),swFixed);//~9622I~//~9624R~
//        sbTimeoutTake.setVal(Pprop.getParameter(getKeyRS(RSID_TIMEOUT_TAKE),DEFAULT_TIMEOUT_TAKE),swFixed);//~9622I~//~9624R~
//        sbTimeoutTakeKan.setVal(Pprop.getParameter(getKeyRS(RSID_TIMEOUT_TAKEKAN),DEFAULT_TIMEOUT_TAKEKAN),swFixed);//~9623I~//~9624R~
    //*drawnMangan                                                 //~9413I~
//        cbDrawnManganYN.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_MANGAN_YN),0),swFixed);//~9413I~//~9505R~
//        cbDrawnManganPending.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_MANGAN_PENDING),0),swFixed);//~9413I~//~9422R~//~9505R~
//        cbDrawnManganNext.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_MANGAN_NEXT),0),swFixed);//~9422I~//~9424R~//~9505R~
//        cbDrawnManganDropBird.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_MANGAN_DROPBIRD),0),swFixed);//~9430I~//~9505R~
//      rgDrawnMangan.setCheckedID(Pprop.getParameter(getKeyRS(RSID_DRAWN_MANGAN_TYPE),DRAWN_MANGAN_DEFAULT),swFixed);//~9505R~//~9516R~
//      spnDrawnManganRank.select(Pprop.getParameter(getKeyRS(RSID_DRAWN_MANGAN_RANK),0),swFixed);//~9413I~//~9422R~//~9516R~
    //*drawnHW                                                     //~9425I~
//      cbDrawnHWNext.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW_NEXT),0),swFixed);//~9425I~//~9B13R~
    //*scoreToPoint                                                //~9416I~
//      rgScoreToPoint.setCheckedID(Pprop.getParameter(getKeyRS(RSID_SCORE_TO_POINT),0),swFixed);//~9416I~//~9417R~
        bgScoreToPoint.setChecked(Pprop.getParameter(getKeyRS(RSID_SCORE_TO_POINT),0),swFixed);//~9417R~
//    //*Reach                                                       //~9427I~//~9517R~
//        cbOpenReach.setStateInt(Pprop.getParameter(getKeyRS(RSID_REACH_OPEN),0/*defaultIdx*/),swFixed);//~9427I~//~9517R~
//        cbMissingReach.setStateInt(Pprop.getParameter(getKeyRS(RSID_REACH_MISSING),0/*defaultIdx*/),swFixed);//~9427I~//~9517R~
    //*robot                                                       //~9429I~
//      cbAllowRobot.setStateInt(Pprop.getParameter(getKeyRS(RSID_ALLOW_ROBOT),0/*default:false*/),swFixed);//~9607I~//~va66R~
        cbAllowRobot.setStateInt(Pprop.getParameter(getKeyRS(RSID_ALLOW_ROBOT),DEFAULT_ALLOW_ROBOT_ALL/*default:true*/),swFixed);//~va66I~
//      cbAllowRobotAll.setStateInt(Pprop.getParameter(getKeyRS(RSID_ALLOW_ROBOT_ALL),0/*default:false*/),swFixed);//~va66R~
//      cbAllowRobotAllButton.setStateInt(Pprop.getParameter(getKeyRS(RSID_ALLOW_ROBOT_ALL_BTN),0/*default:false*/),swFixed);//~va66R~
        cbThinkRobot.setStateInt(Pprop.getParameter(getKeyRS(RSID_THINK_ROBOT),DEFAULT_THINK_ROBOT/*default:true*/),swFixed);//~va60I~//~va66R~
        cbMinusRobot.setStateInt(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP_ROBOT),0),swFixed);//~9429I~
        rgRobotPay.setCheckedID(Pprop.getParameter(getKeyRS(RSID_ROBOT_PAY),0),swFixed);//~9429I~
    //*bird                                                        //~9430I~
        rgBirdPayType.setCheckedID(Pprop.getParameter(getKeyRS(RSID_BIRD_PAYTYPE),0),swFixed);//~9602I~
        cbBird.setStateInt(Pprop.getParameter(getKeyRS(RSID_BIRD),0),swFixed);//~9430I~
        sbBird.setVal(Pprop.getParameter(getKeyRS(RSID_BIRD_PAY),DEFAULT_BIRD),swFixed);//~9430I~
    //*FinalLast                                                   //~9501I~
    	cbClosableRon.setStateInt(Pprop.getParameter(getKeyRS(RSID_CLOSABLE_RON),0),swFixed);//~9501I~
    	cbClosablePending.setStateInt(Pprop.getParameter(getKeyRS(RSID_CLOSABLE_PENDING),0),swFixed);//~9501I~
    	cbClosableNotTop.setStateInt(Pprop.getParameter(getKeyRS(RSID_CLOSABLE_NOTTOP),0),swFixed);//~9501I~
        spnGameSetType.select(Pprop.getParameter(getKeyRS(RSID_GAMESET_TYPE),0),swFixed);//~9501I~
        rgFinalLastDealerNotPending.setCheckedID(Pprop.getParameter(getKeyRS(RSID_FL_NOTPENDING),0),swFixed);//~9501I~
        rgFinalLastAllMinus.setCheckedID(Pprop.getParameter(getKeyRS(RSID_FL_ALLMINUS),0),swFixed);//~9501I~
    //*EatChange                                                   //~v@@@I~//~9516M~
        rgEatChange.setCheckedID(Pprop.getParameter(getKeyRS(RSID_EATCHANGE),EATCHANGE_DEFAULT),swFixed);//~v@@@I~//~9516M~
    //*SpritPos                                                    //~9528I~
    	cbSpritPos.setStateInt(Pprop.getParameter(getKeyRS(RSID_SPRITPOS),0),swFixed);//~9528I~
    //*RankMUp                                                     //~9528I~
    	cbRankMUp.setStateInt(Pprop.getParameter(getKeyRS(RSID_RANKMUP),1),swFixed);//~9528I~
    //*Dora                                                        //~9528I~
        rgDora.setCheckedID(Pprop.getParameter(getKeyRS(RSID_DORA),DORA_DEFAULT),swFixed);//~9528I~
        rgDoraHidden.setCheckedID(Pprop.getParameter(getKeyRS(RSID_DORA_HIDDEN),DORA_HIDDEN_DEFAULT),swFixed);//~9528I~
        rgKanDora.setCheckedID(Pprop.getParameter(getKeyRS(RSID_KANDORA),DORA_KANDORA_DEFAULT),swFixed);//~9528I~
        rgKanDoraHidden.setCheckedID(Pprop.getParameter(getKeyRS(RSID_KANDORA_HIDDEN),DORA_KANDORA_HIDDEN_DEFAULT),swFixed);//~9528I~
        rgKanDoraOpen.setCheckedID(Pprop.getParameter(getKeyRS(RSID_KANDORA_OPEN),DORA_KANDORA_OPEN_DEFAULT),swFixed);//~9529I~
    	cbUseRed5.setStateInt(Pprop.getParameter(getKeyRS(RSID_USERED5),0),swFixed);//~9C01I~
    //*PendingCont                                                 //~9709I~
    	cbPendingCont.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_CONT),1),swFixed);//~9709I~
    }                                                              //~v@@@I~
    //*******************************************************      //~vakQI~
//  private void chkUpdateOperYaku()                               //~vakQI~//~var0R~
    protected void chkUpdateOperYaku()                             //~var0I~
    {                                                              //~vakQI~
    	if (RuleSettingOperation.chkUpdateCheckOnly(this))           //~vakQI~
        {                                                          //~vakWI~
        	swAnyUpdate=true;                                      //~vakWI~
			Utils.setTintBG(btnOperation,COLOR_BG_CHANGED_RULE);   //~vakQR~
        }                                                          //~vakWI~
    	if (RuleSettingYaku.chkUpdateCheckOnly(this))                //~vakQI~
        {                                                          //~vakWI~
        	swAnyUpdate=true;                                      //~vakWI~
			Utils.setTintBG(btnYakuList,COLOR_BG_CHANGED_RULE);    //~vakQI~
        }                                                          //~vakWI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
    //*change BG color of option changed by received parm          //~vakQI~
    //*******************************************************      //~vakQI~
//  private void chkUpdate()                                       //~vakQR~//~var0R~
    protected void chkUpdate()                                     //~var0I~
    {                                                              //~vakQI~
		if (Dump.Y) Dump.println("RuleSetting.chkUpdate curProp="+curProp.toString());//~vaehR~//~vakQR~
	    if (Dump.Y) Dump.println("RuleSetting.chkUpdate AG.ruleProp="+AG.ruleProp.toString());//~vaehR~//~vakQR~
        setBGUpdated(tvSyncDate,isChangedText(RSID_SYNCDATE_FORMATTED));//~vakQR~
        swAnyUpdate=false;	//ignore syncdate                      //~vakWI~
        setBGUpdated(etIDName,isChangedText(RSID_IDNAME));         //~vakQR~
//      if (swFixed)                                               //~vakQR~
//      	etIDName.setEnabled(false);                            //~vakQR~
        setBGUpdated(spnInitialScore,isChanged(RSID_INITSCORE));   //~vakQR~
        setBGUpdated(etInitialScoreTestE,isChanged(RSID_INITSCORE_TESTE));//~vakQR~
        setBGUpdated(etInitialScoreTestS,isChanged(RSID_INITSCORE_TESTS));//~vakQR~
        setBGUpdated(etInitialScoreTestW,isChanged(RSID_INITSCORE_TESTW));//~vakQR~
        setBGUpdated(etInitialScoreTestN,isChanged(RSID_INITSCORE_TESTN));//~vakQR~
        setBGUpdated(spnDupPoint,isChanged(RSID_POINT_DUP));       //~vakQR~
    //*minusPay                                                    //~vakQI~
        setBGUpdated(sbMinusPrize,isChanged(RSID_MINUSSTOP_POINT));//~vakQR~
        setBGUpdated(cbMinusStop,isChanged(RSID_MINUSSTOP));       //~vakQR~
        setBGUpdated(cbMinus0,isChanged(RSID_MINUSSTOP_0));        //~vakQR~
        setBGUpdated(rgMinusPay,isChanged(RSID_MINUSSTOP_PAYTYPE));//~vakQR~
        setBGUpdated(rgMinusStopByErr,isChanged(RSID_MINUSSTOP_BYERR));//~vakQR~
    //*orderPrize                                                  //~vakQI~
        setBGUpdated(spnOrderPrize,isChanged(RSID_ORDERPRIZE));    //~vakQR~
        setBGUpdated(rgOrderSamePoint,isChanged(RSID_ORDERPRIZE_SAMEPOINT));//~vakQR~
    //*multiRon                                                    //~vakQI~
        setBGUpdated(rgMultiRon,isChanged(RSID_MULTIRON));         //~vakQR~
    //*drawnHW                                                     //~vakQI~
        setBGUpdated(cbMultiRon3Drawn,isChanged(RSID_MULTIRON3DRAWN));//~vakQR~
        setBGUpdated(cbDrawnHW99,isChanged(RSID_DRAWN_HW99));      //~vakQR~
        setBGUpdated(cbDrawnHW4W,isChanged(RSID_DRAWN_HW4W));      //~vakQR~
        setBGUpdated(cbDrawnHW4K,isChanged(RSID_DRAWN_HW4K));      //~vakQR~
        setBGUpdated(cbDrawnHW4R,isChanged(RSID_DRAWN_HW4R));      //~vakQR~
        setBGUpdated(cbMultiRon3DrawnCont,isChanged(RSID_MULTIRON3DRAWNC));//~vakQR~
        setBGUpdated(cbDrawnHW99Cont,isChanged(RSID_DRAWN_HW99C)); //~vakQR~
        setBGUpdated(cbDrawnHW4WCont,isChanged(RSID_DRAWN_HW4WC)); //~vakQR~
        setBGUpdated(cbDrawnHW4KCont,isChanged(RSID_DRAWN_HW4KC)); //~vakQR~
        setBGUpdated(cbDrawnHW4RCont,isChanged(RSID_DRAWN_HW4RC)); //~vakQR~
    //*scoreToPoint                                                //~vakQI~
//      setBGUpdated(bgScoreToPoint,RSID_SCORE_TO_POINT);          //~vakQR~//~varbR~
        setBGUpdated(bgScoreToPoint,RSID_SCORE_TO_POINT,0);        //~varbI~
    //*robot                                                       //~vakQI~
        setBGUpdated(cbAllowRobot,isChanged(RSID_ALLOW_ROBOT));    //~vakQR~
        setBGUpdated(cbThinkRobot,isChanged(RSID_THINK_ROBOT));    //~vakQR~
        setBGUpdated(cbMinusRobot,isChanged(RSID_MINUSSTOP_ROBOT));//~vakQR~
        setBGUpdated(rgRobotPay,isChanged(RSID_ROBOT_PAY));        //~vakQR~
    //*bird                                                        //~vakQI~
        setBGUpdated(rgBirdPayType,isChanged(RSID_BIRD_PAYTYPE));  //~vakQR~
        setBGUpdated(cbBird,isChanged(RSID_BIRD));                 //~vakQR~
        setBGUpdated(sbBird,isChanged(RSID_BIRD_PAY));             //~vakQR~
    //*FinalLast                                                   //~vakQI~
    	setBGUpdated(cbClosableRon,isChanged(RSID_CLOSABLE_RON));  //~vakQR~
    	setBGUpdated(cbClosablePending,isChanged(RSID_CLOSABLE_PENDING));//~vakQR~
    	setBGUpdated(cbClosableNotTop,isChanged(RSID_CLOSABLE_NOTTOP));//~vakQR~
        setBGUpdated(spnGameSetType,isChanged(RSID_GAMESET_TYPE)); //~vakQR~
        setBGUpdated(rgFinalLastDealerNotPending,isChanged(RSID_FL_NOTPENDING));//~vakQR~
        setBGUpdated(rgFinalLastAllMinus,isChanged(RSID_FL_ALLMINUS));//~vakQR~
    //*EatChange                                                   //~vakQI~
        setBGUpdated(rgEatChange,isChanged(RSID_EATCHANGE));       //~vakQR~
    //*SpritPos                                                    //~vakQI~
    	setBGUpdated(cbSpritPos,isChanged(RSID_SPRITPOS));         //~vakQR~
    //*RankMUp                                                     //~vakQI~
    	setBGUpdated(cbRankMUp,isChanged(RSID_RANKMUP));           //~vakQR~
    //*Dora                                                        //~vakQI~
        setBGUpdated(rgDora,isChanged(RSID_DORA));                 //~vakQR~
        setBGUpdated(rgDoraHidden,isChanged(RSID_DORA_HIDDEN));    //~vakQR~
        setBGUpdated(rgKanDora,isChanged(RSID_KANDORA));           //~vakQR~
        setBGUpdated(rgKanDoraHidden,isChanged(RSID_KANDORA_HIDDEN));//~vakQR~
        setBGUpdated(rgKanDoraOpen,isChanged(RSID_KANDORA_OPEN));  //~vakQR~
    	setBGUpdated(cbUseRed5,isChanged(RSID_USERED5));           //~vakQR~
    //*PendingCont                                                 //~vakQI~
    	setBGUpdated(cbPendingCont,isChanged(RSID_PENDING_CONT));  //~vakQR~
    }                                                              //~vakQI~
    //*******************************************************      //~v@@@I~
    //*update property from dialog setting                         //~v@@@R~
    //*return changed                                              //~9B09I~
    //*******************************************************      //~v@@@I~
    @Override //SettingDlg                                         //~9405I~
    protected boolean dialog2Properties(String Pfnm)               //~9405I~
    {                                                              //~9405I~
    	String basename=UFile.getBaseName(Pfnm);                   //~9405R~
        if (Dump.Y) Dump.println("RuleSetting.dialog2Properties basename="+basename+",fpath="+Pfnm);//~9405R~
        updateProp(getKeyRS(RSID_FILENAME),basename);              //~9405R~
	    return dialog2Properties();                                       //~9405I~
    }                                                              //~9405I~
    //*******************************************************      //~9405I~
    //*return changed                                              //~9B09I~
    //*******************************************************      //~9B09I~
    @Override //SettingDlg                                         //~v@@@I~
    protected boolean dialog2Properties()                            //~v@@@R~
    {                                                              //~v@@@R~
    	int  changed=0;                                            //~v@@@R~
        String txt;                                                //~9407I~
        //*******************                                      //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.dialog2Properties");               //~v@@@I~//~9405R~
//      changed+=updateProp(getKeyRS(RSID_IDNAME),tvIDName.getText().toString());//~9405R~//~9903R~
        changed+=updateProp(getKeyRS(RSID_IDNAME),etIDName.getText().toString());//~9903I~
//      changed+=updateProp(RK_KUITAN,cbKuitan.getStateInt());     //~v@@@I~//~9404R~
//      changed+=updateProp(getKeyRS(RSID_KUITAN),cbKuitan.getStateInt());//~9404I~//~9515R~
//      changed+=updateProp(RK_INITSCORE,spnInitialScore.getSelectedIndex());//~v@@@I~//~9404R~
        changed+=updateProp(getKeyRS(RSID_INITSCORE),spnInitialScore.getSelectedIndex());//~9404I~
        changed+=updateProp(getKeyRS(RSID_INITSCORE_TESTE),Utils.parseInt(etInitialScoreTestE.getText().toString(),25000));//~9425R~
        changed+=updateProp(getKeyRS(RSID_INITSCORE_TESTS),Utils.parseInt(etInitialScoreTestS.getText().toString(),25000));//~9425R~
        changed+=updateProp(getKeyRS(RSID_INITSCORE_TESTW),Utils.parseInt(etInitialScoreTestW.getText().toString(),25000));//~9425R~
        changed+=updateProp(getKeyRS(RSID_INITSCORE_TESTN),Utils.parseInt(etInitialScoreTestN.getText().toString(),25000));//~9425R~
        changed+=updateProp(getKeyRS(RSID_POINT_DUP),spnDupPoint.getSelectedIndex());//~9512I~
//      String s=(String)spnInitialScore.getSelectedItem();       //~v@@@I~//~9408R~
//      initialScore=Utils.parseInt(s,DEFAULT_INITIALSCORE);       //~v@@@I~//~9408R~
    //*minusPay                                                    //~9404I~
        changed+=updateProp(getKeyRS(RSID_MINUSSTOP_POINT),sbMinusPrize.getVal());//~9404I~//~9408R~
        changed+=updateProp(getKeyRS(RSID_MINUSSTOP),cbMinusStop.getStateInt());//~9408I~
        changed+=updateProp(getKeyRS(RSID_MINUSSTOP_0),cbMinus0.getStateInt());//~9404I~
        changed+=updateProp(getKeyRS(RSID_MINUSSTOP_PAYTYPE),rgMinusPay.getCheckedID());//~9404I~
        changed+=updateProp(getKeyRS(RSID_MINUSSTOP_BYERR),rgMinusStopByErr.getCheckedID());//~9414I~
    //*orderPrize                                                  //~9407I~
//      changed+=updateProp(getKeyRS(RSID_ORDERPRIZE_TOP),etOrderPrizeTop.getText().toString());//~9407R~
//      changed+=updateProp(getKeyRS(RSID_ORDERPRIZE_2ND),etOrderPrize2nd.getText().toString());//~9407R~
        changed+=updateProp(getKeyRS(RSID_ORDERPRIZE),spnOrderPrize.getSelectedIndex());//~9407I~
        changed+=updateProp(getKeyRS(RSID_ORDERPRIZE_SAMEPOINT),rgOrderSamePoint.getCheckedID());//~9407I~
    //*multiRon                                                    //~9408I~
        changed+=updateProp(getKeyRS(RSID_MULTIRON),rgMultiRon.getCheckedID());//~9408I~//~9409R~//~9513R~
//      changed+=updateProp(getKeyRS(RSID_MULTIRON),cbMultiRon.getStateInt());//~9409I~//~9513R~
//      changed+=updateProp(getKeyRS(RSID_MULTIRON_STICKALL),cbMultiRonStickAll.getStateInt());//~9408I~//~9513R~
    //*drawnHW                                                     //~9425I~
        changed+=updateProp(getKeyRS(RSID_MULTIRON3DRAWN),cbMultiRon3Drawn.getStateInt());//~9409I~//~9425M~
        changed+=updateProp(getKeyRS(RSID_DRAWN_HW99),cbDrawnHW99.getStateInt());//~9425I~
        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4W),cbDrawnHW4W.getStateInt());//~9425I~
        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4K),cbDrawnHW4K.getStateInt());//~9425I~
        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4R),cbDrawnHW4R.getStateInt());//~9425I~
        changed+=updateProp(getKeyRS(RSID_MULTIRON3DRAWNC),cbMultiRon3DrawnCont.getStateInt());//~9B13I~
        changed+=updateProp(getKeyRS(RSID_DRAWN_HW99C),cbDrawnHW99Cont.getStateInt());//~9B13I~
        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4WC),cbDrawnHW4WCont.getStateInt());//~9B13I~
        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4KC),cbDrawnHW4KCont.getStateInt());//~9B13I~
        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4RC),cbDrawnHW4RCont.getStateInt());//~9B13I~
//    //*delay                                                       //~9412I~//~9624R~
//        changed+=updateProp(getKeyRS(RSID_DELAY_PONKAN),sbDelayPonKan.getVal());//~9412I~//~9624R~
//        changed+=updateProp(getKeyRS(RSID_DELAY_TAKE),sbDelayTake.getVal());//~9412I~//~9624R~
//        changed+=updateProp(getKeyRS(RSID_DELAY_LAST),sbDelayLast.getVal());//~9412I~//~9624R~
//        changed+=updateProp(getKeyRS(RSID_DELAY_DISCARD),sbDelayDiscard.getVal());//~9622I~//~9624R~
//        changed+=updateProp(getKeyRS(RSID_TIMEOUT_TAKE),sbTimeoutTake.getVal());//~9622I~//~9624R~
//        changed+=updateProp(getKeyRS(RSID_TIMEOUT_TAKEKAN),sbTimeoutTakeKan.getVal());//~9623I~//~9624R~
    //*drawnMangan                                                 //~9413I~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_YN),cbDrawnManganYN.getStateInt());//~9413I~//~9505R~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_PENDING),cbDrawnManganPending.getStateInt());//~9413I~//~9422R~//~9505R~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_NEXT),cbDrawnManganNext.getStateInt());//~9422I~//~9424R~//~9505R~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_DROPBIRD),cbDrawnManganDropBird.getStateInt());//~9430I~//~9505R~
//      changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_TYPE),rgDrawnMangan.getCheckedID());//~9505I~//~9516R~
//      changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_RANK),spnDrawnManganRank.getSelectedIndex());//~9413I~//~9516R~
    //*drawnHW                                                     //~9425I~
//      changed+=updateProp(getKeyRS(RSID_DRAWN_HW_NEXT),cbDrawnHWNext.getStateInt());//~9425I~//~9B13R~
    //*scoreToPoint                                                //~9416I~
//      changed+=updateProp(getKeyRS(RSID_SCORE_TO_POINT),rgScoreToPoint.getCheckedID());//~9416I~//~9417R~
        changed+=updateProp(getKeyRS(RSID_SCORE_TO_POINT),bgScoreToPoint.getChecked());//~9417I~
//    //*Reach                                                       //~9427I~//~9517R~
//        changed+=updateProp(getKeyRS(RSID_REACH_OPEN),cbOpenReach.getStateInt());//~9427I~//~9517R~
//        changed+=updateProp(getKeyRS(RSID_REACH_MISSING),cbMissingReach.getStateInt());//~9427I~//~9517R~
    //*robot                                                       //~9429I~
        changed+=updateProp(getKeyRS(RSID_ALLOW_ROBOT),cbAllowRobot.getStateInt());//~9607I~
//      changed+=updateProp(getKeyRS(RSID_ALLOW_ROBOT_ALL),cbAllowRobotAll.getStateInt());//~va66R~
//      changed+=updateProp(getKeyRS(RSID_ALLOW_ROBOT_ALL_BTN),cbAllowRobotAllButton.getStateInt());//~va66R~
        changed+=updateProp(getKeyRS(RSID_THINK_ROBOT),cbThinkRobot.getStateInt());//~va60I~
        changed+=updateProp(getKeyRS(RSID_MINUSSTOP_ROBOT),cbMinusRobot.getStateInt());//~9404I~//~9429M~
        changed+=updateProp(getKeyRS(RSID_ROBOT_PAY),rgRobotPay.getCheckedID());//~9429I~
    //*bird                                                        //~9430I~
        changed+=updateProp(getKeyRS(RSID_BIRD_PAYTYPE),rgBirdPayType.getCheckedID());//~9602I~
        changed+=updateProp(getKeyRS(RSID_BIRD),cbBird.getStateInt());//~9430I~
        changed+=updateProp(getKeyRS(RSID_BIRD_PAY),sbBird.getVal());//~9430I~
    //*FinalLast                                                   //~9501I~
        changed+=updateProp(getKeyRS(RSID_CLOSABLE_RON),cbClosableRon.getStateInt());//~9501I~
        changed+=updateProp(getKeyRS(RSID_CLOSABLE_PENDING),cbClosablePending.getStateInt());//~9501I~
        changed+=updateProp(getKeyRS(RSID_CLOSABLE_NOTTOP),cbClosableNotTop.getStateInt());//~9501I~
        changed+=updateProp(getKeyRS(RSID_GAMESET_TYPE),spnGameSetType.getSelectedIndex());//~9501I~
        changed+=updateProp(getKeyRS(RSID_FL_NOTPENDING),rgFinalLastDealerNotPending.getCheckedID());//~9501I~
        changed+=updateProp(getKeyRS(RSID_FL_ALLMINUS),rgFinalLastAllMinus.getCheckedID());//~9501I~
    //*EatChange                                                   //~v@@@I~//~9516M~
        changed+=updateProp(getKeyRS(RSID_EATCHANGE),rgEatChange.getCheckedID());//~v@@@I~//~9516M~
    //*SpritPos                                                    //~9528I~
        changed+=updateProp(getKeyRS(RSID_SPRITPOS),cbSpritPos.getStateInt());//~9528I~
    //*RankMUp                                                     //~9528I~
        changed+=updateProp(getKeyRS(RSID_RANKMUP),cbRankMUp.getStateInt());//~9528I~
    //*Dora                                                        //~9528I~
//  	if (!isShowHiddenDora() || isKanDoraNo())                  //~9529I~
	    if (rgDoraHidden.getCheckedID()==DORA_HIDDEN_NO //chk dialog because Prop is not yet saved//~9529I~
	    ||  rgKanDora.getCheckedID()==DORA_KANDORA_NO) //chk dialog because Prop is not yet saved//~9529I~
        {                                                          //~9529M~
	        if (rgKanDoraHidden.getCheckedID()!=DORA_KANDORA_HIDDEN_NO) //chk dialog because Prop is not yet saved//~9529I~
            {                                                      //~9529M~
	            UView.showToast(R.string.Err_ResetHiddenKanDora);  //~9529M~
        		rgKanDoraHidden.setCheckedID(DORA_KANDORA_HIDDEN_NO,false/*swFixed*/);
            }                                                      //~9529M~
        }                                                          //~9529M~
        changed+=updateProp(getKeyRS(RSID_DORA),rgDora.getCheckedID());//~9528I~
        changed+=updateProp(getKeyRS(RSID_DORA_HIDDEN),rgDoraHidden.getCheckedID());//~9528R~
        changed+=updateProp(getKeyRS(RSID_KANDORA),rgKanDora.getCheckedID());//~9528R~
        changed+=updateProp(getKeyRS(RSID_KANDORA_HIDDEN),rgKanDoraHidden.getCheckedID());//~9528R~
        changed+=updateProp(getKeyRS(RSID_KANDORA_OPEN),rgKanDoraOpen.getCheckedID());//~9529I~
        changed+=updateProp(getKeyRS(RSID_USERED5),cbUseRed5.getStateInt());//~9C01I~
    //*PendingCont                                                 //~9709I~
        changed+=updateProp(getKeyRS(RSID_PENDING_CONT),cbPendingCont.getStateInt());//~9709I~
    //**********                                                   //~9404I~
        if (swChangedYaku)                                         //~9629I~
        	changed++;                                             //~9629I~
        if (swChangedOperation)                                    //~9629I~
        	changed++;                                             //~9629I~
	    if (AG.ruleSyncDate.equals(PROP_INIT_SYNCDATE))            //~vaehI~
        {                                                          //~vaehI~
            changed++;  //update AG.ruleSyncDate at saveSyncDate   //~vaehI~
        	if (Dump.Y) Dump.println("RuleSetting.dialog2Properties suncdate is initial");//~vaehI~
        }                                                          //~vaehI~
        if (changed!=0)                                            //~v@@@I~
        {                                                          //~9404I~
        	saveSyncDate();                                        //~9404I~
	        swChanged=true;                                        //~v@@@I~
        }                                                          //~9404I~
	    AG.swChangedRule|=swChanged;                               //~vae8I~
        return changed!=0;                                         //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    @Override //SettingDlg                                         //~v@@@I~
    protected String getFilter()                                   //~v@@@I~
    {                                                              //~v@@@I~
    	return AG.PROP_EXT_RULE;                                   //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    //*update property from dialog setting                         //~v@@@I~
    //*******************************************************      //~v@@@I~
    @Override
    public Prop checkValidity(String Pfname)                        //~1A4zI~//~v@@@R~
    {                                                              //~1A4zI~//~v@@@M~
    	Prop prop=new Prop();                                      //~v@@@M~
        if (!prop.loadProperties(Pfname)                           //~v@@@M~
        ||  prop.getParameter(AG.PROP_VALIDATINGKEY_RULE,0)!=1)    //~v@@@I~
        {                                                          //~v@@@M~
        	return null;                                           //~v@@@R~
        }                                                          //~v@@@M~
        return prop;                                               //~v@@@R~
    }                                                              //~1A4zI~//~v@@@M~
//    //**************************************                       //~9403I~//~9404R~
//    @Override                                                      //~9403I~//~9404R~
//    public void onClickOK()                                        //~9403I~//~9404R~
//    {                                                              //~9403I~//~9404R~
//        if (Dump.Y) Dump.println("RuleSetting.onClickOK");         //~9403I~//~9404R~
////      getSettings();                                              //~9403I~//~9404R~
//        super.onClickOK();                                         //~9403R~//~9404R~
//    }                                                              //~9403I~//~9404R~
    //*******************************************************      //~9629I~
    //*save then showstatus                                        //~9629I~
    //*******************************************************      //~9629I~
    @Override                                                      //~9629I~
    public void onClickShowStatus()                                //~9629I~
    {                                                              //~9629I~
        if (Dump.Y) Dump.println("RuleSetting.onClickShowStatus"); //~9629I~
	    getValue();                                                //~9629I~
		super.onClickShowStatus();                                 //~9629I~
    }                                                              //~9629I~
    //*******************************************************      //~9616I~
    @Override                                                      //~9616I~
    protected void onClickLoad()                                   //~9616I~
    {                                                              //~9616I~
        if (Dump.Y) Dump.println("RuleSetting.onClickLoad");       //~9616I~
        RuleFileDlg.newInstance(getFilter()/*extension*/,true/*load*/).show(this/*FileDialogI*/,null/*defaultitemname*/);//~9616I~
    }                                                              //~9616I~
    //**************************************                       //~9616I~
    @Override                                                      //~9616I~
    public void onClickSave()                                      //~9616I~
    {                                                              //~9616I~
        if (Dump.Y) Dump.println("RuleSetting.onClickSave");        //~9616I~//~vaqhR~
//      String	saveFilename=tvIDName.getText().toString();        //~9616I~//~9903R~
        String	saveFilename=etIDName.getText().toString();        //~9903I~
//      FileDialog.newInstance(getFilter()/*extension*/,false/*not load*/).show(this/*FileDialogI*/,AG.ruleFile/*defaultitemname*/,saveFilename);//~9616R~
        RuleFileDlg.newInstance(getFilter()/*extension*/,false/*not load*/).show(this/*FileDialogI*/,AG.ruleFile/*defaultitemname*/,saveFilename);//~9616I~
    }                                                              //~9616I~
    //**************************************                       //~9404I~
    @Override                                                      //~9404I~
    public void onClickSend()                                      //~9404I~
    {                                                              //~9404I~
        if (Dump.Y) Dump.println("RuleSetting.onClickSend");         //~9404I~//~9629R~
	    getValue();		//update AG.ruleProc if changed                                                //~9404I~//~9406R~
        AG.ruleSyncDate=curProp.getParameter(getKeyRS(RSID_SYNCDATE),"Unknown");//~9404R~//~9A31R~
        String cmt=PROP_NAME;                                      //~9404R~
        send(cmt,curProp);	//SettingDlg                           //~9404R~
    }                                                              //~9404I~
//    //**************************************                       //~9826I~//~9901R~
//    public static void sendHistoryRule(String Pfnm,String Pcmt)    //~9826R~//~9901R~
//    {                                                              //~9826I~//~9901R~
//        if (Dump.Y) Dump.println("RuleSetting.sendHistoryRule Pfnm="+Pfnm+",cmt="+Pcmt);//~9826I~//~9901R~
//        Prop curProp=AG.ruleProp;                                  //~9826I~//~9901R~
//        SettingDlg.sendHistoryRule(Pfnm,Pcmt,curProp);  //SettingDlg//~9826I~//~9901R~
//    }                                                              //~9826I~//~9901R~
    //*******************************************************      //~9412I~
    //*by Send and OK button                                       //~9616I~
    //*******************************************************      //~9616I~
    @Override                                                      //~9412I~
    public void getValue()                                         //~9412I~
    {                                                              //~9412I~
        if (Dump.Y) Dump.println("RuleSettingDlg.getvalue swReceived="+swReceived);       //~9412I~//~9616R~
        super.getValue(); //==>dialog2Properties()                                          //~9412R~//~9629R~
//      if (swChangedYaku)                                         //~9516I~//~9629R~
//      	swChanged=true;                                        //~9516I~//~9629R~
//      if (swChangedOperation)                                    //~9624I~//~9629R~
//      	swChanged=true;                                        //~9624I~//~9629R~
        if (swReceived)                                            //~9824I~
        {                                                          //~9824I~
    		saveReceived();                                        //~9824I~
        }                                                          //~9824I~
        else                                                       //~9824I~
        if (swChanged)                                             //~9412I~
        {                                                          //~9412I~
//            if (AG.aBTMulti!=null)                                 //~9412I~//~9621R~
//            {                                                      //~9412I~//~9621R~
//                AG.aBTMulti.setRuleSettingSynchedAll(false/*swOK*/,""/*syncDate*/);//~9412I~//~9621R~
//                if (BTMulti.isServerDevice())                      //~9412I~//~9621R~
//                    notifySynched(false/*swOK*/,RESET_SYNCDATE);   //~9412I~//~9621R~
//                else                                               //~9617I~//~9621R~
//                    notifyOutOfSyncToServer(RESET_SYNCDATE);       //~9617I~//~9621R~
//            }                                                      //~9412I~//~9621R~
		    saveProperties();	//save to current.rule             //~9412I~
	        AG.ruleProp.resetProperties(curProp);                  //~9412I~
    	    swChanged=false;                                       //~9412I~
	        swChangedYaku=false;                                   //~9516I~
	        swChangedOperation=false;                              //~9624I~
        }                                                          //~9412I~
//        else                                                       //~9412I~//~9616R~//~9824R~
//        if (swReceived)                                            //~9412I~//~9616R~//~9824R~
//        {                                                          //~9412I~//~9616R~//~9824R~
//            saveReceived();                                        //~9616I~//~9824R~
//        }                                                          //~9412I~//~9616R~//~9824R~
    }                                                              //~9412I~
    //**************************************                       //~9404I~
    private void saveSyncDate()                                         //~9404I~
    {                                                              //~9404I~
        Long ct=System.currentTimeMillis();    //~9404I~           //~9405R~
        String syncDate=Long.toHexString(ct);                      //~9405I~
        Timestamp ts=new Timestamp(ct);                            //~9405I~
        String sdf=new SimpleDateFormat("yyyy/MM/dd-HH.mm.ss").format(ts);//~9405R~
        updateProp(getKeyRS(RSID_SYNCDATE),syncDate);                    //~9404I~//~9405R~
        updateProp(getKeyRS(RSID_SYNCDATE_FORMATTED),sdf);         //~9405I~
        tvSyncDate.setText(sdf);//~9405I~
	    if (AG.ruleSyncDate.equals(PROP_INIT_SYNCDATE))            //~va66I~
        	AG.ruleSyncDate=curProp.getParameter(getKeyRS(RSID_SYNCDATE),PROP_INIT_SYNCDATE);//~va66I~
    	if (Dump.Y) Dump.println("RuleSetting.saveSyncDate syncDate="+syncDate+",formatted="+sdf);//~9404I~//~9405R~
    }                                                              //~9404I~
    //**************************************                       //~9826I~
    public static String getSyncDateFormatted()                    //~9826I~
    {                                                              //~9826I~
    	String sd=AG.ruleProp.getParameter(getKeyRS(RSID_SYNCDATE_FORMATTED),null);//~9826I~
    	if (Dump.Y) Dump.println("RuleSetting.getSyncDateFormatted sd="+sd);//~9826I~
        return sd;                                                 //~9826I~
    }                                                              //~9826I~
    //**************************************                       //~v@@@I~
    @Override                                                     //~v@@@I~
    public void onClickOtherUnknown(int Pbuttonid)                        //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.onClickOtherUnknown btnid="+Integer.toHexString(Pbuttonid));//~v@@@I~
        switch(Pbuttonid)                                          //~9515I~
        {                                                          //~9515I~
            case R.id.btnYakuList:                                 //~9515I~
                onClickYakuList();                                 //~9515I~
                break;                                             //~9515I~
            case R.id.btnOperation:                                //~9624I~
                onClickOperation();                                //~9624I~
                break;                                             //~9624I~
        }                                                          //~9515I~
    }                                                              //~v@@@I~
    //*************************************************************//~9515I~
    private void onClickYakuList()                                 //~9515I~
    {                                                              //~9515I~
    	if (Dump.Y) Dump.println("RuleSetting.onClickYakuList");   //~9515I~
        RuleSettingYaku.newInstance(this).show();                  //~9515R~
    }                                                              //~9515I~
    //*************************************************************//~9624I~
    private void onClickOperation()                                //~9624I~
    {                                                              //~9624I~
    	if (Dump.Y) Dump.println("RuleSetting.onClickOperation");  //~9624I~
        RuleSettingOperation.newInstance(this).show();             //~9624I~
    }                                                              //~9624I~
//    //*************************************************************//~9403I~//~9404R~
//    public void setupByStatic()                                    //~9403I~//~9404R~
//    {                                                              //~9403I~//~9404R~
//        if (Dump.Y) Dump.println("RuleSetting.setupByStatic");     //~9403I~//~9404R~
//        cbKuitan.setState(swKuitan);                               //~9403I~//~9404R~
//        if (Dump.Y) Dump.println("RuleSetting.getSettings swKuitan="+swKuitan);//~9403I~//~9404R~
//    //*minusPay                                                    //~9403I~//~9404R~
//        cbMinusStop.setState(swMinusStop);                         //~9403I~//~9404R~
//        cbMinus0.setState(swZeroStop);                             //~9403I~//~9404R~
//        cbMinusRobot.setState(swMinusStopRobot);                   //~9403I~//~9404R~
//        rgMinusPay.setCheckedID(rgMinusID,false/*fix*/);           //~9403I~//~9404R~
//        if (Dump.Y) Dump.println("RuleSetting.getSettings swMinusStop="+swMinusStop+",swZeroStop="+swZeroStop+",swMinusStopRobot="+swMinusStopRobot+",swMinusPay="+swMinusPay+",swMinusPayGetAllPoint="+swMinusPayGetAllPoint+",rgMinusID="+rgMinusID);//~9403I~//~9404R~
//    }                                                              //~9403I~//~9404R~
//    //*************************************************************//~9403I~//~9404R~
//    public void getSettings()                                      //~9403I~//~9404R~
//    {                                                              //~9403I~//~9404R~
//        if (Dump.Y) Dump.println("RuleSetting.getSettings");       //~9403I~//~9404R~
//        swKuitan=cbKuitan.getState();                              //~9403I~//~9404R~
//        if (Dump.Y) Dump.println("RuleSetting.getSettings swKuitan="+swKuitan);//~9403I~//~9404R~
//    //*minusPay                                                    //~9403I~//~9404R~
//        swMinusStop=cbMinusStop.getState();                        //~9403I~//~9404R~
//        swZeroStop=cbMinus0.getState();                            //~9403R~//~9404R~
//        swMinusStopRobot=cbMinusRobot.getState();                  //~9403R~//~9404R~
//        rgMinusID=rgMinusPay.getCheckedID();                       //~9403R~//~9404R~
////        switch(rgMinusID)                                          //~9403I~//~9404R~
////        {                                                          //~9403I~//~9404R~
////        case 0:                                                    //~9403I~//~9404R~
////            swMinusPay=true;                                       //~9403I~//~9404R~
////            swMinusPayGetAllPoint=false;                           //~9403I~//~9404R~
////            break;                                                 //~9403I~//~9404R~
////        case 1:                                                    //~9403I~//~9404R~
////            swMinusPay=false;                                      //~9403I~//~9404R~
////            swMinusPayGetAllPoint=true;                            //~9403I~//~9404R~
////            break;                                                 //~9403I~//~9404R~
////        case 2:                                                    //~9403I~//~9404R~
////            swMinusPay=false;                                      //~9403I~//~9404R~
////            swMinusPayGetAllPoint=false;                           //~9403I~//~9404R~
////            break;                                                 //~9403I~//~9404R~
////        default:                                                   //~9403I~//~9404R~
////            swMinusPay=true;                                       //~9403I~//~9404R~
////            swMinusPayGetAllPoint=false;                           //~9403I~//~9404R~
////            break;                                                 //~9403I~//~9404R~
////        }                                                          //~9403I~//~9404R~
//        if (Dump.Y) Dump.println("RuleSetting.getSettings MinusStop="+swMinusStop+",swZeroStop="+swZeroStop+",swMinusStopRobot="+swMinusStopRobot+",rgMinusID="+rgMinusID);//~9403I~//~9404R~
//    }                                                              //~9403I~//~9404R~
    //*******************************************************      //~9406I~
    public static void repaint()                                   //~9406R~
    {                                                              //~9406I~
        if (Dump.Y) Dump.println("RuleSetting.repaint");    //~9406I~//~var0R~
        if (!Utils.isShowingDialogFragment(AG.aRuleSetting))       //~9406I~
        	return;                                                //~9406I~
        AG.aRuleSetting.repaintOKNG();                             //~9406R~
    }                                                              //~9406I~
    //*******************************************************************************//~vae8I~
    //*from MainActivity->AG-->                                    //~vae8I~
    //*******************************************************************************//~vae8I~
    public static void recoverProp(String Pmember)                 //~vae8R~
    {                                                              //~vae8I~
    	if (Dump.Y) Dump.println("RuleSetting.recoverProp member="+Pmember);//~vae8R~
        String fpath=PrefSetting.makeFullpath(Pmember);            //~vae8I~
        if (fpath==null)                                           //~vae8I~
        	return;                                                //~vae8I~
        boolean rc=AG.ruleProp.loadProperties(fpath);              //~vae8I~
    	if (Dump.Y) Dump.println("RuleSetting.recoverProp rc="+rc+",AG.ruleProp="+AG.ruleProp.toString());//~vae8I~
        if (rc)                                                    //~vae8I~
	        recoverSharedPreference();                             //~vae8I~
    }                                                              //~vae8I~
    //*******************************************************************************//~vae8I~
    private static void recoverSharedPreference()                  //~vae8I~
    {                                                              //~vae8I~
    	if (Dump.Y) Dump.println("RuleSetting.recoverSharedPreference Nothing to do");//~vae8I~
    }                                                              //~vae8I~
    //*******************************************************************************//~vae8I~
    //*from MainActivity->AG-->                                    //~vae8I~
    //*******************************************************************************//~vae8I~
    public static boolean saveProp(String Pmember)                    //~vae8R~
    {                                                              //~vae8I~
    	if (Dump.Y) Dump.println("RuleSetting.saveProp member="+Pmember);//~vam2R~
        if (!AG.swChangedRule                                     //~vae8I~
//      &&  !(AG.ruleProp.getParameter(getKeyRS(RSID_SAVED_RULE),"")).equals(""))	//not copy of asset//~vae8R~
        )                                                          //~vae8I~
        {                                                          //~vae8I~
    		if (Dump.Y) Dump.println("RuleSetting.saveProp return false by swChangeRule");//~vae8I~
        	return false;                                          //~vae8I~
        }                                                          //~vae8I~
//      AG.ruleProp.setParameter(getKeyRS(RSID_SAVED_RULE),"1");   //~vae8R~
//      AG.swChangedRule=true;                                     //~vae8R~
        String fpath=PrefSetting.makeFullpath(Pmember);                        //~vae8I~
        boolean rc;                                                //~vae8I~
        if (UScoped.isScoped())                                    //~vae8I~
	        rc=AG.ruleProp.savePropertiesScoped(fpath,PROP_NAME,true/*swOverride*/);//~vae8I~
        else                                                       //~vae8I~
	        rc=AG.ruleProp.saveProperties(fpath,PROP_NAME);        //~vae8I~
    	if (Dump.Y) Dump.println("RuleSetting.saveProp rc="+rc);   //~vae8I~
        return rc;                                                 //~vae8I~
    }                                                              //~vae8I~
    //*****************************************************************************************                       //~v@@@I~//~9403R~
    //*****************************************************************************************//~9403I~
    //*****************************************************************************************//~9403I~
//    public static boolean isOpenKanDoraJustNow()                   //~v@@@R~//~9529R~
//    {                                                              //~v@@@M~//~9529R~
//        boolean rc=swOpenKanDoraJustNow;                           //~v@@@R~//~9529R~
//        if (Dump.Y) Dump.println("isOpenKanDoraJustNow rc="+rc);   //~v@@@R~//~9529R~
//        return rc;                                                 //~v@@@M~//~9529R~
//    }                                                              //~v@@@M~//~9529R~
//    //**************************************                       //~v@@@I~//~9529R~
//    public static boolean isOpenKanDoraExceptRon()                 //~v@@@I~//~9529R~
//    {                                                              //~v@@@I~//~9529R~
//        boolean rc=swOpenKanDoraExceptRon;  //for minkan,open kandora at enxt action except ron//~v@@@I~//~9529R~
//        if (Dump.Y) Dump.println("RuleSetting.isOpenKanDoraExceptRon rc="+rc);//~v@@@R~//~9529R~
//        return rc;                                                 //~v@@@I~//~9529R~
//    }                                                              //~v@@@I~//~9529R~
//    //**************************************                       //~v@@@I~//~9528R~
//    public static boolean isRankMUp()                             //~v@@@I~//~9528R~
//    {                                                              //~v@@@I~//~9528R~
//        if (Dump.Y) Dump.println("RuleSetting.isRankMUp ="+swRankMUp);//~v@@@R~//~9528R~
//        return swRankMUp;                                          //~v@@@I~//~9528R~
//    }                                                              //~v@@@I~//~9528R~
//    //**************************************                       //~v@@@I~//~9528R~
//    public static boolean isDoraNext()                             //~v@@@I~//~9528R~
//    {                                                              //~v@@@I~//~9528R~
//        if (Dump.Y) Dump.println("RuleSetting.isDoraNext ="+swDoraNext);//~v@@@R~//~9528R~
//        return swDoraNext;                                         //~v@@@I~//~9528R~
//    }                                                              //~v@@@I~//~9528R~
//    //**************************************                       //~v@@@I~//~9528R~
//    public static boolean isHiddenDoraNext()                       //~v@@@I~//~9528R~
//    {                                                              //~v@@@I~//~9528R~
//        if (Dump.Y) Dump.println("RuleSetting.isHiddenDoraNext ="+swHiddenDoraNext);//~v@@@R~//~9528R~
//        return swHiddenDoraNext;                                   //~v@@@I~//~9528R~
//    }                                                              //~v@@@I~//~9528R~
//    //**************************************                       //~v@@@I~//~9528R~
//    public static boolean isKanDoraNext()                          //~v@@@I~//~9528R~
//    {                                                              //~v@@@I~//~9528R~
//        if (Dump.Y) Dump.println("RuleSetting.isDoraNext ="+swKanDoraNext);//~v@@@R~//~9528R~
//        return swKanDoraNext;                                      //~v@@@I~//~9528R~
//    }                                                              //~v@@@I~//~9528R~
//    //**************************************                       //~v@@@I~//~9528R~
//    public static boolean isHiddenKanDoraNext()                    //~v@@@I~//~9528R~
//    {                                                              //~v@@@I~//~9528R~
//        if (Dump.Y) Dump.println("RuleSetting.isHiddenKanDoraNext="+swHiddenKanDoraNext);//~v@@@R~//~9528R~
//        return swHiddenKanDoraNext;                                //~v@@@I~//~9528R~
//    }                                                              //~v@@@I~//~9528R~
//    //**************************************                       //~v@@@I~//~9528R~
//    public static boolean isShowHiddenDora()                       //~v@@@R~//~9528R~
//    {                                                              //~v@@@I~//~9528R~
//        if (Dump.Y) Dump.println("RuleSetting.isShowHiddenDora="+swShowHiddenDora);//~v@@@R~//~9528R~
//        return swShowHiddenDora;                                   //~v@@@R~//~9528R~
//    }                                                              //~v@@@I~//~9528R~
//    //**************************************                       //~v@@@I~//~9528R~
//    public static boolean isShowHiddenKanDora()                    //~v@@@I~//~9528R~
//    {                                                              //~v@@@I~//~9528R~
//        if (Dump.Y) Dump.println("RuleSetting.isShowHiddenKanDora="+swShowHiddenKanDora);//~v@@@R~//~9528R~
//        return swShowHiddenKanDora;                                //~v@@@I~//~9528R~
//    }                                                              //~v@@@I~//~9528R~
//    //**************************************                       //~v@@@I~//~9530R~
//    public static boolean isAvailableAnkanRon()                    //~v@@@R~//~9530R~
//    {                                                              //~v@@@I~//~9530R~
//        if (Dump.Y) Dump.println("RuleSetting.isAvailableAnkanRon="+swAvailableAnkanRon);//~v@@@R~//~9530R~
//        return swAvailableAnkanRon;                                //~v@@@R~//~9530R~
//    }                                                              //~v@@@I~//~9530R~
//    //**************************************                       //~v@@@I~//~9530R~
//    public static boolean isAvailableKanAfterReach()               //~v@@@I~//~9530R~
//    {                                                              //~v@@@I~//~9530R~
//        boolean rc=swAvailableKanAfterReach;                       //~v@@@R~//~9530R~
//        if (Dump.Y) Dump.println("RuleSetting.isAvailableKanAfterReach="+rc);//~v@@@R~//~9530R~
//        return rc;                                                 //~v@@@I~//~9530R~
//    }                                                              //~v@@@I~//~9530R~
    //**************************************                       //~v@@@I~
	public static boolean isMultiRon()                             //~v@@@I~
    {                                                              //~v@@@I~
//      boolean rc=swMultiRon;                                     //~v@@@I~//~9408R~//~9513R~
    	int idx=AG.ruleProp.getParameter(getKeyRS(RSID_MULTIRON),0);//~9408I~//~9409R~//~9513R~
        boolean rc=idx!=MULTIRON_NO;                               //~9408I~//~9409R~//~9513R~
//  	int def=0;	//false                                        //~9409I~//~9513R~
//      boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_MULTIRON),def)!=0;//~9409I~//~9513R~
        if (Dump.Y) Dump.println("RuleSetting.isMultiRon="+rc);    //~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static boolean isMultiRonPointDupAll()                  //~v@@@I~
    {                                                              //~v@@@I~
//        boolean rc=swMultiRonPointDupAll;                          //~v@@@I~//~9408R~
//  	int def=1;	//true=byEswn                                  //~9409R~//~9513R~
//      boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_MULTIRON_STICKALL),def)==0;//~9408I~//~9409R~//~9513R~
    	int idx=AG.ruleProp.getParameter(getKeyRS(RSID_MULTIRON),0);//~9513I~
        boolean rc=idx==MULTIRON_YES_DUPSTICK_ALL;                 //~9513I~
        if (Dump.Y) Dump.println("RuleSetting.isMultiRonPointDupAll="+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //**************************************                       //~v@@@I~//~9427R~
//    public static boolean isReachAvailableExceptLast()             //~v@@@I~//~9427R~
//    {                                                              //~v@@@I~//~9427R~
//        boolean rc=swReachAvailableExceptLast;                     //~v@@@I~//~9427R~
//        if (Dump.Y) Dump.println("RuleSetting.isReachAvailableExceptLast="+rc);//~v@@@R~//~9427R~
//        return rc;                                                 //~v@@@I~//~9427R~
//    }                                                              //~v@@@I~//~9427R~
    //**************************************                       //~9425I~
    //*Drawn HW                                                    //~9425I~
    //**************************************                       //~9425I~
	public static boolean isDrawnHW3R()                        //~v@@@I~//~9425M~//~9702R~
    {                                                              //~v@@@I~//~9425M~
//      boolean rc=swMultiRon3Next;                                //~v@@@I~//~9408R~//~9425M~
//  	int idx=AG.ruleProp.getParameter(getKeyRS(RSID_MULTIRON),0);//~9408I~//~9409R~//~9425M~
//      boolean rc=idx==MULTIRON_3DRAWN;                           //~9408I~//~9409R~//~9425M~
		int def=1;	//true                                         //~9409I~//~9425I~//~9702R~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_MULTIRON3DRAWN),def)!=0;//~9409I~//~9425I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnHW3R="+rc);//~v@@@R~//~9425M~//~9702R~
        return rc;                                                 //~v@@@I~//~9425M~
    }                                                              //~v@@@I~//~9425M~
    //**************************************                       //~9425I~
	public static boolean isDrawnHW99()                            //~9425I~
    {                                                              //~9425I~
		int def=1;	//true                                         //~9425I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW99),def)!=0;//~9425I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnHW99="+rc);   //~9425I~
        return rc;                                                 //~9425I~
    }                                                              //~9425I~
    //**************************************                       //~9425I~
	public static boolean isDrawnHW4W()                            //~9425I~
    {                                                              //~9425I~
		int def=1;	//true                                         //~9425I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4W),def)!=0;//~9425I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnHW4W="+rc);   //~9425I~
        return rc;                                                 //~9425I~
    }                                                              //~9425I~
    //**************************************                       //~9425I~
	public static boolean isDrawnHW4K()                            //~9425I~
    {                                                              //~9425I~
		int def=1;	//true                                         //~9425I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4K),def)!=0;//~9425I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnHW4K="+rc);   //~9425I~
        return rc;                                                 //~9425I~
    }                                                              //~9425I~
    //**************************************                       //~9425I~
	public static boolean isDrawnHW4R()                            //~9425I~
    {                                                              //~9425I~
		int def=1;	//true                                         //~9425I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4R),def)!=0;//~9425I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnHW4R="+rc);   //~9425I~
        return rc;                                                 //~9425I~
    }                                                              //~9425I~
    //**************************************                       //~9B13I~
	public static boolean isDrawnHW3RCont()                        //~9B13I~
    {                                                              //~9B13I~
		int def=0;                                                 //~9B13I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_MULTIRON3DRAWNC),def)!=0;//~9B13I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnHW3RCont="+rc);//~9B13I~
        return rc;                                                 //~9B13I~
    }                                                              //~9B13I~
    //**************************************                       //~9B13I~
	public static boolean isDrawnHW99Cont()                        //~9B13I~
    {                                                              //~9B13I~
		int def=1;	//true                                         //~9B13I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW99C),def)!=0;//~9B13I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnHW99Cont="+rc);//~9B13I~
        return rc;                                                 //~9B13I~
    }                                                              //~9B13I~
    //**************************************                       //~9B13I~
	public static boolean isDrawnHW4WCont()                        //~9B13I~
    {                                                              //~9B13I~
		int def=1;	//true                                         //~9B13I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4WC),def)!=0;//~9B13I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnHW4WCont="+rc);//~9B13I~
        return rc;                                                 //~9B13I~
    }                                                              //~9B13I~
    //**************************************                       //~9B13I~
	public static boolean isDrawnHW4KCont()                        //~9B13I~
    {                                                              //~9B13I~
		int def=0;	//true                                         //~9B13I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4KC),def)!=0;//~9B13I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnHW4KCont="+rc);//~9B13I~
        return rc;                                                 //~9B13I~
    }                                                              //~9B13I~
    //**************************************                       //~9B13I~
	public static boolean isDrawnHW4RCont()                        //~9B13I~
    {                                                              //~9B13I~
		int def=0;	//true                                         //~9B13I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4RC),def)!=0;//~9B13I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnHW4RCont="+rc);//~9B13I~
        return rc;                                                 //~9B13I~
    }                                                              //~9B13I~
    //**************************************                       //~v@@@I~
	public static int getPendingBase()                             //~v@@@R~
    {                                                              //~v@@@I~
        int rc=pendingBase;                                        //~v@@@R~
        if (Dump.Y) Dump.println("RuleSetting.getPendingBase="+pendingBase);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static int getInitialScore()                            //~v@@@I~
    {                                                              //~v@@@I~
		int idx=AG.ruleProp.getParameter(getKeyRS(RSID_INITSCORE),DEFAULT_IDX_INITSCORE);//~9408I~//~9416R~//~9421R~
        int rc=initialScoreS[idx];                                 //~9408I~
        if (Dump.Y) Dump.println("RuleSetting.getInitialScore rc="+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~//~9512M~
	public static int getDupRate()                                //~v@@@I~//~9512M~
    {                                                              //~v@@@I~//~9512M~
		int idx=AG.ruleProp.getParameter(getKeyRS(RSID_POINT_DUP),0);//~9512I~
        int rc=dupPointS[idx];                                      //~9512I~
        if (Dump.Y) Dump.println("RuleSetting.getDupRate duprate="+rc);//~v@@@R~//~9512I~
        return rc;                                                 //~9512I~
    }                                                              //~v@@@I~//~9512M~
    //**************************************                       //~9425I~
	public static int[] getInitialScoreTest()                      //~9425I~
    {                                                              //~9425I~
		int scoreE=AG.ruleProp.getParameter(getKeyRS(RSID_INITSCORE_TESTE),2500);//~9425I~
		int scoreS=AG.ruleProp.getParameter(getKeyRS(RSID_INITSCORE_TESTS),2500);//~9425I~
		int scoreW=AG.ruleProp.getParameter(getKeyRS(RSID_INITSCORE_TESTW),2500);//~9425I~
		int scoreN=AG.ruleProp.getParameter(getKeyRS(RSID_INITSCORE_TESTN),2500);//~9425I~
        int[] rc=new int[]{scoreE,scoreS,scoreW,scoreN};           //~9425I~
        if (Dump.Y) Dump.println("RuleSetting.getInitialScoreTest rc="+Arrays.toString(rc));//~9425I~
        return rc;                                                 //~9425I~
    }                                                              //~9425I~
    //**************************************                       //~v@@@I~
	public static int getDebt()                                    //~v@@@I~
    {                                                              //~v@@@I~
        int rc=DEFAULT_DEBT;                                       //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.getDebt rc="+rc);    //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static boolean isMinusStop()                            //~v@@@R~
    {                                                              //~v@@@I~
//        boolean rc=swMinusStop;                                    //~v@@@I~//~9404R~
		int def=0;	//false                                        //~9404I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_MINUSSTOP),def)!=0;//~9404I~
        if (Dump.Y) Dump.println("RuleSetting.isMinusStop rc="+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static boolean isZeroStop()                             //~v@@@R~
    {                                                              //~v@@@I~
//      boolean rc=swZeroStop;                                     //~v@@@I~//~9404R~
		int def=0;	//false                                        //~9404I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_MINUSSTOP_0),def)!=0;//~9404I~
        if (Dump.Y) Dump.println("RuleSetting.isZeroStop rc="+rc); //~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static boolean isMinusPay()                             //~v@@@R~
    {                                                              //~v@@@I~
////      boolean rc=swMinusPay;                                     //~v@@@R~//~9404R~
		int def=MINUSPAY_YES;                                      //~9404I~
        int rgMinusID=AG.ruleProp.getParameter(getKeyRS(RSID_MINUSSTOP_PAYTYPE),def);//~9404R~
        boolean rc=rgMinusID==MINUSPAY_YES;                        //~9404R~
        if (Dump.Y) Dump.println("RuleSetting.isMinusPay rc="+rc); //~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static boolean isMinusPayGetAllPoint()                  //~v@@@I~
    {                                                              //~v@@@I~
//      boolean rc=swMinusPayGetAllPoint;                          //~v@@@I~//~9404R~
        int rgMinusID=AG.ruleProp.getParameter(getKeyRS(RSID_MINUSSTOP_PAYTYPE),0);//~9404R~
        boolean rc=rgMinusID==MINUSPAY_NO_PAY_ALL;                     //~9404I~
        if (Dump.Y) Dump.println("RuleSetting.isMinusPaygetAllPoint rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~9414I~
	public static int getMinusStopByErr()                          //~9414I~
    {                                                              //~9414I~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_MINUSSTOP_BYERR),0);//~9414I~
        if (Dump.Y) Dump.println("RuleSetting.isMinusStopByErr rc="+rc);//~9414I~
        return rc;                                                 //~9414I~
    }                                                              //~9414I~
//    //**************************************                       //~v@@@I~//~9425R~
//    public static boolean isContinueDealerDrawnLast()              //~v@@@I~//~9425R~
//    {                                                              //~v@@@I~//~9425R~
//        boolean rc=swContinueDealerDrawnLast;                      //~v@@@I~//~9425R~
//        if (Dump.Y) Dump.println("RuleSetting.isContinueDealerDrawnLast rc="+rc);//~v@@@R~//~9425R~
//        return rc;                                                 //~v@@@I~//~9425R~
//    }                                                              //~v@@@I~//~9425R~
    //**************************************                       //~v@@@I~
//  public static boolean isContinueDrawnHW()                      //~v@@@I~//~9425R~
//    public static boolean isDrawnHWNext()                          //~9425I~//~9B13R~
//    {                                                              //~v@@@I~//~9B13R~
////      boolean rc=swContinueDrawnHW;                              //~v@@@I~//~9425R~//~9B13R~
//        int def=0;  //false                                        //~9425I~//~9B13R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW_NEXT),def)!=0;//~9425I~//~9B13R~
//        if (Dump.Y) Dump.println("RuleSetting.isDrawnHWNext rc="+rc);//~v@@@R~//~9425R~//~9B13R~
//        return rc;                                                 //~v@@@I~//~9B13R~
//    }                                                              //~v@@@I~//~9B13R~
//    //**************************************                       //~v@@@I~//~9425R~
//    public static boolean isContinueDrawnLastPending()             //~v@@@I~//~9425R~
//    {                                                              //~v@@@I~//~9425R~
//        boolean rc=swContinueDrawnLastPending;                     //~v@@@I~//~9425R~
//        if (Dump.Y) Dump.println("RuleSetting.isContinueDrawnLastPending rc="+rc);//~v@@@R~//~9425R~
//        return rc;                                                 //~v@@@I~//~9425R~
//    }                                                              //~v@@@I~//~9425R~
    //**************************************                       //~v@@@I~
	public static int getPointMinusStop()                          //~v@@@I~
    {                                                              //~v@@@I~
//      int rc=pointMinusStop;                                     //~v@@@I~//~9408R~
		int rc=AG.ruleProp.getParameter(getKeyRS(RSID_MINUSSTOP_POINT),DEFAULT_MINUSPRIZE_INIT);//~9408R~
        if (Dump.Y) Dump.println("RuleSetting.getPointMinusStop rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static int[] getOrderPrize()                            //~v@@@I~
    {                                                              //~v@@@I~
//        int[] rc=orderPrize;                                       //~v@@@I~//~9407R~
//        if (Dump.Y) Dump.println("RuleSetting.getOrderPrize rc="+Arrays.toString(rc));//~v@@@I~//~9407R~
//        return rc;                                                 //~v@@@I~//~9407R~
//        int pztop=AG.ruleProp.getParameter(getKeyRS(RSID_ORDERPRIZE_TOP),DEFAULT_ORDERPRIZE_TOP);//~9407R~
//        int pz2nd=AG.ruleProp.getParameter(getKeyRS(RSID_ORDERPRIZE_2ND),DEFAULT_ORDERPRIZE_2ND);//~9407R~
//        int[] rc=new int[]{pztop,pz2nd,-pz2nd,-pztop};           //~9407R~
		int idx=AG.ruleProp.getParameter(getKeyRS(RSID_ORDERPRIZE),DEFAULT_ORDERPRIZE);//~9407I~//~9819R~
        int[] ops=orderPrizeS[idx];                                 //~9407I~//~9408R~
        int[] rc=new int[]{ops[1]*1000,ops[0]*1000,-ops[0]*1000,-ops[1]*1000};//~9407R~
        if (Dump.Y) Dump.println("RuleSetting.getOrderPrize prize="+Arrays.toString(rc));//~9407I~
        return rc;                                                 //~9407I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static boolean isOrderByEswn()                          //~v@@@R~
    {                                                              //~v@@@I~
//        boolean rc=swOrderByEswn;                                  //~v@@@I~//~9407R~
//        if (Dump.Y) Dump.println("RuleSetting.isOrderByEswn rc="+swOrderByEswn);//~v@@@I~//~9407R~
        int rbid=AG.ruleProp.getParameter(getKeyRS(RSID_ORDERPRIZE_SAMEPOINT),0);//~9407I~
        boolean rc=rbIDOrderSamePoint[rbid]==R.id.rbOrderSamePointEswn;//~9407I~
        if (Dump.Y) Dump.println("RuleSetting.isOrderByEswn rc="+rc);//~9407I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //**************************************                     //~9407R~
//    public static boolean isOrderPrizeSpritCut()                 //~9407R~
//    {                                                            //~9407R~
//        int rbid=AG.ruleProp.getParameter(getKeyRS(RSID_ORDERPRIZE_SAMEPOINT),0);//~9407R~
//        boolean rc=rbIDOrderSamePoint[rbid]==R.id.rbOrderSamePointSpritCut;//~9407R~
//        if (Dump.Y) Dump.println("RuleSetting.isOrderPrizeSpritCut rc="+rc);//~9407R~
//        return rc;                                               //~9407R~
//    }                                                            //~9407R~
//    //**************************************                     //~9407R~
//    public static boolean isOrderPrizeSpritUp()                  //~9407R~
//    {                                                            //~9407R~
//        int rbid=AG.ruleProp.getParameter(getKeyRS(RSID_ORDERPRIZE_SAMEPOINT),0);//~9407R~
//        boolean rc=rbIDOrderSamePoint[rbid]==R.id.rbOrderSamePointSpritUp;//~9407R~
//        if (Dump.Y) Dump.println("RuleSetting.isOrderPrizeSpritUp rc="+rc);//~9407R~
//        return rc;                                               //~9407R~
//    }                                                            //~9407R~
    //**************************************                       //~v@@@I~
    //*called when gamectr==3(end of a set)                        //~v@@@I~
    //**************************************                       //~v@@@I~
	public static int getNextFieldEswn(int PseqSet,int PmaxScore)  //~v@@@I~
    {                                                              //~v@@@I~
    	int next,same,rc,lastNext,maxNext;	//East                 //~v@@@R~
        boolean swLast=false;                                       //~v@@@I~
        switch(typeSets)                                           //~v@@@I~
        {                                                          //~v@@@I~
        case TS_E:           //0                                   //~v@@@R~
        	swLast=PseqSet>=0;                                     //~v@@@I~
            next=0;     //East                                     //~v@@@I~
            maxNext=0;     //East                                  //~v@@@I~
            lastNext=0;     //East                                 //~v@@@I~
        	break;                                                 //~v@@@I~
        case TS_EE:          //1                                   //~v@@@R~
        	swLast=PseqSet>=1;                                     //~v@@@I~
            next=0;                                                //~v@@@I~
            maxNext=0;     //East                                  //~v@@@I~
            lastNext=0;     //East                                 //~v@@@I~
        	break;                                                 //~v@@@I~
        case TS_ES:          //2                                   //~v@@@R~
        	swLast=PseqSet>=1;                                     //~v@@@I~
            next=1;     //South                                    //~v@@@I~
            maxNext=3;                                             //~9527R~
            lastNext=(PseqSet+1)%PLAYERS; //West                   //~v@@@I~
        	break;                                                 //~v@@@I~
        case TS_EN:          //3                                   //~v@@@R~
        	swLast=PseqSet>=1;                                     //~v@@@I~
            next=3;   //North                                      //~v@@@I~
            maxNext=3;                                             //~9527R~
            lastNext=(PseqSet%2==1 ? 0 : 3); //North/East          //~v@@@R~
        	break;                                                 //~v@@@I~
        default: //case TS_ESWN: //4                               //~v@@@R~
        	swLast=PseqSet>=3;                                     //~v@@@I~
            next=PseqSet+1;                                        //~v@@@I~
            maxNext=3;     //East                                  //~v@@@I~
            lastNext=(PseqSet+1)%PLAYERS; //East                   //~v@@@I~
        }                                                          //~v@@@I~
        if (!swLast)                                               //~v@@@I~
        {                                                          //~v@@@I~
        	rc=next;                                               //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
    		if (swLastGameNoDrawn)                                 //~v@@@I~
            	rc=maxNext;                                        //~v@@@R~
            else                                                   //~v@@@I~
            if (PmaxScore>=pointToNextEswn)                        //~v@@@I~
            	rc=-1;	//end game                                 //~v@@@I~
            else                                                   //~v@@@I~
            	rc=lastNext;                                       //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.getNextFieldEswn swLast="+swLast+",PseqSet="+PseqSet+",maxScore="+PmaxScore+",typeSets="+typeSets+",swLastGameNoDrawn="+swLastGameNoDrawn+",pointToNextEswn="+pointToNextEswn+",swDrawnToNextEswn="+swDrawnToNextEswn+",rc=nextround ESWN="+rc);//~v@@@R~//~9527R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	public static void getNextFieldEswnTest()	//TDO for UT       //~v@@@I~
    {                                                              //~v@@@I~
    	swLastGameNoDrawn=true;                                    //~v@@@I~
        typeSets=TS_E;                                             //~v@@@R~
		getNextFieldEswn(0,30000);                                 //~v@@@I~
		getNextFieldEswn(1,30000);                                 //~v@@@I~
        typeSets=TS_EE;                                            //~v@@@R~
		getNextFieldEswn(0,30000);                                 //~v@@@I~
		getNextFieldEswn(1,30000);                                 //~v@@@I~
		getNextFieldEswn(2,30000);                                 //~v@@@I~
        typeSets=TS_ES;                                            //~v@@@R~
		getNextFieldEswn(0,30000);                                 //~v@@@I~
		getNextFieldEswn(1,30000);                                 //~v@@@I~
		getNextFieldEswn(2,30000);                                 //~v@@@I~
        typeSets=TS_EN;                                            //~v@@@R~
		getNextFieldEswn(0,30000);                                 //~v@@@I~
		getNextFieldEswn(1,30000);                                 //~v@@@I~
		getNextFieldEswn(2,30000);                                 //~v@@@I~
        typeSets=TS_ESWN;                                          //~v@@@R~
		getNextFieldEswn(0,30000);                                 //~v@@@I~
		getNextFieldEswn(1,30000);                                 //~v@@@I~
		getNextFieldEswn(2,30000);                                 //~v@@@I~
		getNextFieldEswn(3,30000);                                 //~v@@@I~
		getNextFieldEswn(4,30000);                                 //~v@@@I~
                                                                   //~v@@@I~
    	swLastGameNoDrawn=false;                                   //~v@@@I~
        typeSets=TS_E;                                             //~v@@@R~
		getNextFieldEswn(0,30000);                                 //~v@@@I~
		getNextFieldEswn(1,30000);                                 //~v@@@I~
		getNextFieldEswn(2,30000);                                 //~v@@@I~
        typeSets=TS_EE;                                            //~v@@@R~
		getNextFieldEswn(0,30000);                                 //~v@@@I~
		getNextFieldEswn(1,30000);                                 //~v@@@I~
		getNextFieldEswn(2,30000);                                 //~v@@@I~
		getNextFieldEswn(3,30000);                                 //~v@@@I~
        typeSets=TS_ES;                                            //~v@@@R~
		getNextFieldEswn(0,30000);                                 //~v@@@I~
		getNextFieldEswn(1,30000);                                 //~v@@@I~
		getNextFieldEswn(2,30000);                                 //~v@@@I~
		getNextFieldEswn(3,30000);                                 //~v@@@I~
        typeSets=TS_EN;                                            //~v@@@R~
		getNextFieldEswn(0,30000);                                 //~v@@@I~
		getNextFieldEswn(1,30000);                                 //~v@@@I~
		getNextFieldEswn(2,30000);                                 //~v@@@I~
		getNextFieldEswn(3,30000);                                 //~v@@@I~
        typeSets=TS_ESWN;                                          //~v@@@R~
		getNextFieldEswn(0,30000);                                 //~v@@@I~
		getNextFieldEswn(1,30000);                                 //~v@@@I~
		getNextFieldEswn(2,30000);                                 //~v@@@I~
		getNextFieldEswn(3,30000);                                 //~v@@@I~
		getNextFieldEswn(4,30000);                                 //~v@@@I~
		getNextFieldEswn(5,30000);                                 //~v@@@I~
                                                                   //~v@@@I~
        typeSets=TS_E;                                             //~v@@@R~
		getNextFieldEswn(0,2900);                                  //~v@@@I~
		getNextFieldEswn(1,2900);                                  //~v@@@I~
		getNextFieldEswn(2,2900);                                  //~v@@@I~
        typeSets=TS_EE;                                            //~v@@@R~
		getNextFieldEswn(0,2900);                                  //~v@@@I~
		getNextFieldEswn(1,2900);                                  //~v@@@I~
		getNextFieldEswn(2,2900);                                  //~v@@@I~
		getNextFieldEswn(3,2900);                                  //~v@@@I~
        typeSets=TS_ES;                                            //~v@@@R~
		getNextFieldEswn(0,2900);                                  //~v@@@I~
		getNextFieldEswn(1,2900);                                  //~v@@@I~
		getNextFieldEswn(2,2900);                                  //~v@@@I~
		getNextFieldEswn(3,2900);                                  //~v@@@I~
        typeSets=TS_EN;                                            //~v@@@R~
		getNextFieldEswn(0,2900);                                  //~v@@@I~
		getNextFieldEswn(1,2900);                                  //~v@@@I~
		getNextFieldEswn(2,2900);                                  //~v@@@I~
		getNextFieldEswn(3,2900);                                  //~v@@@I~
        typeSets=TS_ESWN;                                          //~v@@@R~
		getNextFieldEswn(0,2900);                                  //~v@@@I~
		getNextFieldEswn(1,2900);                                  //~v@@@I~
		getNextFieldEswn(2,2900);                                  //~v@@@I~
		getNextFieldEswn(3,2900);                                  //~v@@@I~
		getNextFieldEswn(4,2900);                                  //~v@@@I~
		getNextFieldEswn(5,2900);                                  //~v@@@I~
    }                                                              //~v@@@I~
//    //**************************************                       //~9412I~//~9624R~
//    public static int getDelayPonKan()                             //~9412I~//~9624R~
//    {                                                              //~9412I~//~9624R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_PONKAN),DEFAULT_DELAY_PONKAN);//~9412I~//~9624R~
////      if (rc<10)                                                 //~9412R~//~9622R~//~9624R~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~9622R~//~9624R~
//            rc*=1000;   //ms                                       //~9412I~//~9624R~
//        else                                                       //~9412I~//~9624R~
//            rc*=10;     //10ms unit 20-->200ms                     //~9412I~//~9624R~
//        if (Dump.Y) Dump.println("RuleSetting.getDelayPonKan:"+rc);//~9412I~//~9624R~
//        return rc;                                                 //~9412I~//~9624R~
//    }                                                              //~9412I~//~9624R~
//    //**************************************                       //~9412I~//~9624R~
//    public static int getDelayTake()                               //~9412I~//~9624R~
//    {                                                              //~9412I~//~9624R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_TAKE),DEFAULT_DELAY_TAKE);//~9412I~//~9624R~
////      if (rc<10)                                                 //~9412I~//~9622R~//~9624R~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~9622I~//~9624R~
//            rc*=1000;   //ms                                       //~9412I~//~9624R~
//        else                                                       //~9412I~//~9624R~
//            rc*=10;     //10ms unit 20-->200ms                     //~9412I~//~9624R~
//        if (Dump.Y) Dump.println("RuleSetting.getDelayTake:"+rc);  //~9412I~//~9624R~
//        return rc;                                                 //~9412I~//~9624R~
//    }                                                              //~9412I~//~9624R~
//    //**************************************                       //~9412I~//~9624R~
//    public static int getDelayLast()                               //~9412I~//~9624R~
//    {                                                              //~9412I~//~9624R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_LAST),DEFAULT_DELAY_LAST);//~9412I~//~9624R~
////      if (rc<10)                                                 //~9412I~//~9622R~//~9624R~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~9622I~//~9624R~
//            rc*=1000;   //ms                                       //~9412I~//~9624R~
//        else                                                       //~9412I~//~9624R~
//            rc*=10;     //10ms unit 20-->200ms                     //~9412I~//~9624R~
//        if (Dump.Y) Dump.println("RuleSetting.getDelayLast:"+rc);  //~9412I~//~9624R~
//        return rc;                                                 //~9412I~//~9624R~
//    }                                                              //~9412I~//~9624R~
//    //**************************************                       //~9622I~//~9624R~
//    public static int getDelayDiscard()                            //~9622I~//~9624R~
//    {                                                              //~9622I~//~9624R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_DISCARD),DEFAULT_DELAY_DISCARD);//~9622I~//~9624R~
////      if (rc<10)                                                 //~9622I~//~9624R~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~9622I~//~9624R~
//            rc*=1000;   //ms                                       //~9622I~//~9624R~
//        else                                                       //~9622I~//~9624R~
//            rc*=10;     //10ms unit 20-->200ms                     //~9622I~//~9624R~
//        if (Dump.Y) Dump.println("RuleSetting.getDelayLast:"+rc);  //~9622I~//~9624R~
//        return rc;                                                 //~9622I~//~9624R~
//    }                                                              //~9622I~//~9624R~
//    //**************************************                       //~9622I~//~9624R~
//    public static int getTimeoutTake()                             //~9622I~//~9624R~
//    {                                                              //~9622I~//~9624R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_TIMEOUT_TAKE),DEFAULT_TIMEOUT_TAKE);//~9622I~//~9624R~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~9622I~//~9624R~
//            rc*=1000;   //ms                                       //~9622I~//~9624R~
//        else                                                       //~9622I~//~9624R~
//            rc*=10;     //10ms unit 20-->200ms                     //~9622I~//~9624R~
//        if (Dump.Y) Dump.println("RuleSetting.getTimeoutTake:"+rc);//~9622I~//~9624R~
//        return rc;                                                 //~9622I~//~9624R~
//    }                                                              //~9622I~//~9624R~
//    //**************************************                       //~9623I~//~9624R~
//    public static int getTimeoutTakeKan()                          //~9623I~//~9624R~
//    {                                                              //~9623I~//~9624R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_TIMEOUT_TAKEKAN),DEFAULT_TIMEOUT_TAKEKAN);//~9623I~//~9624R~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~9623I~//~9624R~
//            rc*=1000;   //ms                                       //~9623I~//~9624R~
//        else                                                       //~9623I~//~9624R~
//            rc*=10;     //10ms unit 20-->200ms                     //~9623I~//~9624R~
//        if (Dump.Y) Dump.println("RuleSetting.getTimeoutTakeKan:"+rc);//~9623I~//~9624R~
//        return rc;                                                 //~9623I~//~9624R~
//    }                                                              //~9623I~//~9624R~
    //**************************************                       //~9413I~
    //*drawnMangan                                                 //~9413I~
    //**************************************                       //~9413I~
//    public static boolean isDrawnManganAvailable()                 //~9413R~//~9505R~
//    {                                                              //~9413I~//~9505R~
//        int def=0;  //false                                        //~9413I~//~9505R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_YN),def)!=0;//~9413R~//~9505R~
//        if (Dump.Y) Dump.println("RuleSetting.isDrawnManganAvailable:"+rc);//~9413I~//~9505R~
//        return rc;                                                 //~9413I~//~9505R~
//    }                                                              //~9413I~//~9505R~
//    private static int getDrawnManganType()                    //~9505I~//~9516R~
//    {                                                              //~9505I~//~9516R~
//        int def=DRAWN_MANGAN_DEFAULT;                              //~9505I~//~9516R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_TYPE),def);//~9505I~//~9516R~
//        if (Dump.Y) Dump.println("RuleSetting.getDrawnManganType rc"+rc);//~9505I~//~9516R~
//        return rc;                                                 //~9505I~//~9516R~
//    }                                                              //~9505I~//~9516R~
//    public static boolean isDrawnManganAvailable()                 //~9505I~//~9516R~
//    {                                                              //~9505I~//~9516R~
//        int type=getDrawnManganType();                             //~9505I~//~9516R~
//        boolean rc=type!=DRAWN_MANGAN_NO;                          //~9505I~//~9516R~
//        if (Dump.Y) Dump.println("RuleSetting.isDrawnManganAvailable rc"+rc);//~9505I~//~9516R~
//        return rc;                                                 //~9505I~//~9516R~
//    }                                                              //~9505I~//~9516R~
//    public static boolean isDrawnManganPending()                   //~9422I~//~9505R~
//    {                                                              //~9422I~//~9505R~
//        int def=0;  //false                                        //~9422I~//~9505R~
//        boolean rc;                                                //~9425I~//~9505R~
//        if (isDrawnManganAvailable())                              //~9425I~//~9505R~
//            rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_PENDING),def)!=0;//~9422I~//~9425R~//~9505R~
//        else                                                       //~9425I~//~9505R~
//            rc=false;                                              //~9425I~//~9505R~
//        if (Dump.Y) Dump.println("RuleSetting.isDrawnManganPending:"+rc);//~9422I~//~9505R~
//        return rc;                                                 //~9422I~//~9505R~
//    }                                                              //~9422I~//~9505R~
//    public static boolean isDrawnManganDropBird()                  //~9501I~//~9505R~
//    {                                                              //~9501I~//~9505R~
//        int def=0;  //false                                        //~9501I~//~9505R~
//        boolean rc;                                                //~9501I~//~9505R~
//        if (isDrawnManganAvailable())                              //~9501I~//~9505R~
//            rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_DROPBIRD),def)!=0;//~9501I~//~9505R~
//        else                                                       //~9501I~//~9505R~
//            rc=false;                                              //~9501I~//~9505R~
//        if (Dump.Y) Dump.println("RuleSetting.isDrawnManganDropBird:"+rc);//~9501I~//~9505R~
//        return rc;                                                 //~9501I~//~9505R~
//    }                                                              //~9501I~//~9505R~
//  public static boolean isDrawnManganDropBird()                  //~9505R~
//    public static boolean isDrawnManganAsRon()                     //~9505I~//~9516R~
//    {                                                              //~9505I~//~9516R~
//        int type=getDrawnManganType();                             //~9505I~//~9516R~
//        boolean rc=type==DRAWN_MANGAN_ASRON;                       //~9505I~//~9516R~
//        if (Dump.Y) Dump.println("RuleSetting.isDrawnManganAsRon:"+rc);//~9505R~//~9516R~
//        return rc;                                                 //~9505I~//~9516R~
//    }                                                              //~9505I~//~9516R~
//    public static boolean isDrawnManganNext()                  //~9422I~//~9424R~//~9505R~
//    {                                                              //~9422I~//~9505R~
//        int def=0;  //false                                        //~9422I~//~9505R~
//        boolean rc;                                                //~9425I~//~9505R~
//        if (isDrawnManganAvailable())                              //~9425I~//~9505R~
//            rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_NEXT),def)!=0;//~9422I~//~9424R~//~9425R~//~9505R~
//        else                                                       //~9425I~//~9505R~
//            rc=false;                                              //~9425I~//~9505R~
//        if (Dump.Y) Dump.println("RuleSetting.isDrawnManganNext:"+rc);//~9422I~//~9424R~//~9505R~
//        return rc;                                                 //~9422I~//~9505R~
//    }                                                              //~9422I~//~9505R~
//    public static boolean isDrawnManganAsDrawn()                   //~9505R~//~9516R~
//    {                                                              //~9505I~//~9516R~
//        int type=getDrawnManganType();                             //~9505I~//~9516R~
//        boolean rc=type==DRAWN_MANGAN_ASDRAWN;                    //~9505I~//~9516R~
//        if (Dump.Y) Dump.println("RuleSetting.isDrawnManganAsDrawn:"+rc);//~9505R~//~9516R~
//        return rc;                                                 //~9505I~//~9516R~
//    }                                                              //~9505I~//~9516R~
//    public static int getDrawnManganPoint()                        //~9413I~//~9516R~
//    {                                                              //~9413I~//~9516R~
//        int idx=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_RANK),0);//~9413I~//~9516R~
//        int rc=pointsDrawnMangan[idx];                             //~9413I~//~9516R~
//        if (Dump.Y) Dump.println("RuleSetting.getDrawnManganPoint:"+rc+",idx="+idx);//~9413R~//~9516R~
//        return rc;                                                 //~9413I~//~9516R~
//    }                                                              //~9413I~//~9516R~
//    public static String getDrawnManganRank()                         //~9413I~//~9516R~
//    {                                                              //~9413I~//~9516R~
//        int idx=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_RANK),0);//~9413I~//~9516R~
//        String rc=rankDrawnMangan[idx];                            //~9413I~//~9516R~
//        if (Dump.Y) Dump.println("RuleSetting.getDrawnManganRank:"+rc+",idx="+idx);//~9413I~//~9516R~
//        return rc;                                                 //~9413I~//~9516R~
//    }                                                              //~9413I~//~9516R~
    //**************************************                       //~9416I~
    //*ScoreToPoint                                                //~9416I~
    //**************************************                       //~9416I~
	public static int getScoreToPoint()                            //~9416I~
    {                                                              //~9416I~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_SCORE_TO_POINT),0);//~9416I~
        if (Dump.Y) Dump.println("RuleSetting.getScoreToPoint rc="+rc);//~9416I~
        return rc;                                                 //~9416I~
    }                                                              //~9416I~
    //*********************************************************    //~9416I~
    //*used on other dialog to show related setting                //~9416I~
    //*********************************************************    //~9416I~
    public static void setScoreToPoint(View PView,boolean PswFixed)                   //~9416I~
    {                                                              //~9416I~
    	int rc=0;                                                  //~9416I~
        if (Dump.Y) Dump.println("RuleSetting.SetupScoreToPoint swFixed="+PswFixed);//~9416I~//~9422R~
//      URadioGroup rg=new URadioGroup(PView,R.id.rgScoreToPoint,0,rbIDScoreToPoint);//~9416I~//~9417R~
//      rg.setCheckedID(getScoreToPoint(),PswFixed);//~9416I~      //~9417R~
        UButtonRG bgScoreToPoint=new UButtonRG((ViewGroup)PView,rbIDScoreToPoint.length);//~9417I~
	    for (int ii=0;ii<rbIDScoreToPoint.length;ii++)             //~9417I~
			bgScoreToPoint.add(IDScoreToPoint[ii],rbIDScoreToPoint[ii]);//~9417I~
        bgScoreToPoint.setChecked(getScoreToPoint(),PswFixed);//~9417I~
    }                                                              //~9416I~
    //*********************************************************    //~9422I~//~9516R~
    //*used on other dialog to show related setting                //~9422I~//~9516R~
    //*********************************************************    //~9422I~//~9516R~
//    public static void setDrawnMangan(View PView,boolean PswFixed) //~9422I~//~9516R~
//    {                                                              //~9422I~//~9516R~
//        int rc=0;                                                  //~9422I~//~9516R~
//        if (Dump.Y) Dump.println("RuleSetting.SetupDrawnMangan swFixed="+PswFixed);//~9422I~//~9516R~
////      UCheckBox cbDrawnManganYN=new UCheckBox(PView,R.id.cbDrawnManganYN);//~9422R~//~9505R~//~9516R~
////      UCheckBox cbDrawnManganPending=new UCheckBox(PView,R.id.cbDrawnManganPending);//~9422R~//~9505R~//~9516R~
////      UCheckBox cbDrawnManganNext=new UCheckBox(PView,R.id.cbDrawnManganNext);//~9422R~//~9424R~//~9505R~//~9516R~
////      UCheckBox cbDrawnManganDropBird=new UCheckBox(PView,R.id.cbDrawnManganDropBird);//~9430I~//~9505R~//~9516R~
//        URadioGroup rgDrawnMangan=new URadioGroup(PView,R.id.rgDrawnMangan,0,rbIDDrawnMangan);//~9505I~//~9516R~
//        USpinner spnDrawnManganRank =new USpinner(PView,R.id.spnDrawnManganRank);//~9422R~//~9516R~
//        spnDrawnManganRank.setArray(rankDrawnMangan);              //~9422I~//~9516R~
//                                                                   //~9422I~//~9516R~
////      int yn=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_YN),0);//~9425R~//~9505R~//~9516R~
////      cbDrawnManganYN.setStateInt(yn,PswFixed);                  //~9425I~//~9505R~//~9516R~
////      if (yn==0)                                                 //~9425I~//~9505R~//~9516R~
////      {                                                          //~9425I~//~9505R~//~9516R~
////          cbDrawnManganPending.setStateInt(0,PswFixed);          //~9425I~//~9505R~//~9516R~
////          cbDrawnManganNext.setStateInt(0,PswFixed);             //~9425I~//~9505R~//~9516R~
////          cbDrawnManganDropBird.setStateInt(0,PswFixed);         //~9430I~//~9505R~//~9516R~
////      }                                                          //~9425I~//~9505R~//~9516R~
////      else                                                       //~9425I~//~9505R~//~9516R~
////      {                                                          //~9425I~//~9505R~//~9516R~
////          cbDrawnManganPending.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_PENDING),0),PswFixed);//~9422R~//~9425R~//~9505R~//~9516R~
////          cbDrawnManganNext.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_NEXT),0),PswFixed);//~9422R~//~9424R~//~9425R~//~9505R~//~9516R~
////          cbDrawnManganDropBird.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_DROPBIRD),0),PswFixed);//~9430I~//~9505R~//~9516R~
////      }                                                          //~9425I~//~9505R~//~9516R~
//        rgDrawnMangan.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_TYPE),0),PswFixed);//~9505I~//~9516R~
//        spnDrawnManganRank.select(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_RANK),0),PswFixed);//~9422R~//~9425M~//~9516R~
//    }                                                              //~9422I~//~9516R~
    //*********************************************************    //~9425I~
    //*drawnHW                                                     //~9425I~
    //*********************************************************    //~9425I~
    public static void setDrawnHW(View PView,boolean PswFixed)     //~9425I~
    {                                                              //~9425I~
        if (Dump.Y) Dump.println("RuleSetting.SetupDrawnHW swFixed="+PswFixed);//~9425I~
//      UCheckBox cbDrawnHWNext=new UCheckBox(PView,R.id.cbDrawnHWNext);//~9425M~//~9B13R~
        UCheckBox cbMultiRon3Drawn=new UCheckBox(PView,R.id.cb3Ron);//~9425I~
        UCheckBox cbDrawnHW99=new UCheckBox(PView,R.id.cb99Tile);  //~9425R~
        UCheckBox cbDrawnHW4W=new UCheckBox(PView,R.id.cb4Wind);   //~9425R~
        UCheckBox cbDrawnHW4K=new UCheckBox(PView,R.id.cb4Kan);    //~9425R~
        UCheckBox cbDrawnHW4R=new UCheckBox(PView,R.id.cb4Reach);  //~9425R~
        UCheckBox cbMultiRon3DrawnCont=new UCheckBox(PView,R.id.cb3RonCont);//~9B13R~
        UCheckBox cbDrawnHW99Cont=new UCheckBox(PView,R.id.cb99TileCont);//~9B13R~
        UCheckBox cbDrawnHW4WCont=new UCheckBox(PView,R.id.cb4WindCont);//~9B13R~
        UCheckBox cbDrawnHW4KCont=new UCheckBox(PView,R.id.cb4KanCont);//~9B13R~
        UCheckBox cbDrawnHW4RCont=new UCheckBox(PView,R.id.cb4ReachCont);//~9B13R~
// 	    cbDrawnHWNext.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW_NEXT),0),PswFixed);//~9425M~//~9B13R~
        cbMultiRon3Drawn.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_MULTIRON3DRAWN),1/*default true*/),PswFixed);//~9425I~
   	    cbDrawnHW99.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW99),1),PswFixed);//~9425I~
   	    cbDrawnHW4W.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4W),1),PswFixed);//~9425R~
   	    cbDrawnHW4K.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4K),1),PswFixed);//~9425R~
   	    cbDrawnHW4R.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4R),1),PswFixed);//~9425I~
        cbMultiRon3DrawnCont.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_MULTIRON3DRAWNC),0),PswFixed);//~9B13I~
   	    cbDrawnHW99Cont.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW99C),1),PswFixed);//~9B13I~
   	    cbDrawnHW4WCont.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4WC),1),PswFixed);//~9B13I~
   	    cbDrawnHW4KCont.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4KC),0),PswFixed);//~9B13I~
   	    cbDrawnHW4RCont.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_HW4RC),0),PswFixed);//~9B13I~
    }                                                              //~9425I~
//    public static boolean isAvailableOpenReach()                   //~9427I~//~9517R~
//    {                                                              //~9427I~//~9517R~
//        int def=0;  //false                                        //~9427I~//~9517R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_REACH_OPEN),def)!=0;//~9427I~//~9517R~
//        if (Dump.Y) Dump.println("RuleSetting.isAvailableOpenReach:"+rc);//~9427I~//~9517R~
//        return rc;                                                 //~9427I~//~9517R~
//    }                                                              //~9427I~//~9517R~
    //**************************************                       //~9607I~
	public static boolean isAllowRobot()                           //~9607I~
    {                                                              //~9607I~
//  	int def=0;	//false                                        //~9607I~//~va66R~
    	int def=DEFAULT_ALLOW_ROBOT_ALL;	//true                 //~va66I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_ALLOW_ROBOT),def)!=0;//~9607I~
        if (Dump.Y) Dump.println("RuleSetting.isAllowRobot rc="+rc);//~9607I~
        return rc;                                                 //~9607I~
    }                                                              //~9607I~
//    //**************************************                     //~va66R~
//    public static boolean isAllowRobotAll()                      //~va66R~
//    {                                                            //~va66R~
//        int def=0;  //false                                      //~va66R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_ALLOW_ROBOT_ALL),def)!=0;//~va66R~
//        if (Dump.Y) Dump.println("RuleSetting.isAllowRobotAll rc="+rc);//~va66R~
//        return rc;                                               //~va66R~
//    }                                                            //~va66R~
//    //**************************************   moved to operation//~va66R~
//    public static boolean isAllowRobotAllButton()                //~va66R~
//    {                                                            //~va66R~
//        int def=0;  //false                                      //~va66R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_ALLOW_ROBOT_ALL_BTN),def)!=0;//~va66R~
//        if (Dump.Y) Dump.println("RuleSetting.isAllowRobotAllButton rc="+rc);//~va66R~
//        return rc;                                               //~va66R~
//    }                                                            //~va66R~
	public static boolean isThinkRobot()                           //~va60I~
    {                                                              //~va60I~
//        int def=DEFAULT_THINK_ROBOT;    //false                                        //~va60I~//~va66R~//~vabsR~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_THINK_ROBOT),def)!=0;//~va60I~//~vabsR~
        boolean rc=(TestOption.option5 & TestOption.TO5_NOTHINK_ROBOT)==0;      //~vabsI~
        if (Dump.Y) Dump.println("RuleSetting.isThinkRobot rc="+rc);//~va60I~
        return rc;                                                 //~va60I~
    }                                                              //~va60I~
	public static boolean isMinusStopRobot()                       //~v@@@R~//~9429M~
    {                                                              //~v@@@I~//~9429M~
//      boolean rc=swMinusStopRobot;                               //~v@@@I~//~9404R~//~9429M~
		int def=1;	//true                                         //~9404I~//~9429M~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_MINUSSTOP_ROBOT),def)!=0;//~9404I~//~9429M~
        if (Dump.Y) Dump.println("RuleSetting.isMinusStopRobot rc="+rc);//~v@@@R~//~9429M~
        return rc;                                                 //~v@@@I~//~9429M~
    }                                                              //~v@@@I~//~9429M~
    public static int getRobotPayType()                            //~9429I~
    {                                                              //~9429I~
        int def=0;  //false                                        //~9429I~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_ROBOT_PAY),def);//~9429I~
        if (Dump.Y) Dump.println("RuleSetting.getRobotPayType rc="+rc);//~9429I~
        return rc;                                                 //~9429I~
    }                                                              //~9429I~
    //**************************************                       //~9430I~
	public static boolean isGrillBird()                            //~9430I~
    {                                                              //~9430I~
		int def=0;	//false                                        //~9430I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_BIRD),def)!=0;//~9430I~
        if (Dump.Y) Dump.println("RuleSetting.isGrillBird rc="+rc);//~9430I~
        return rc;                                                 //~9430I~
    }                                                              //~9430I~
    public static int getGrilledBirdPay()                          //~9430I~
    {                                                              //~9430I~
		int rc=AG.ruleProp.getParameter(getKeyRS(RSID_BIRD_PAY),DEFAULT_BIRD);//~9430I~
        if (Dump.Y) Dump.println("RuleSetting.getGrilledBirdPay rc="+rc);//~9430I~
        return rc;                                                 //~9430I~
    }                                                              //~9430I~
	public static boolean isBirdPayToEach()                        //~9602I~
    {                                                              //~9602I~
        int payType=AG.ruleProp.getParameter(getKeyRS(RSID_BIRD_PAYTYPE),0);//~9602I~
        boolean rc=payType==BIRD_PAYTYPE_EACH;                     //~9602I~
        if (Dump.Y) Dump.println("RuleSetting.isBirdPayToEach rc="+rc);//~9602I~
        return rc;                                                 //~9602I~
    }                                                              //~9602I~
    //**************************************                       //~9501I~
    //*FinalLast                                                   //~9501I~
    //**************************************                       //~9501I~
    public static boolean isFinalLastClosableRon()                 //~9501I~
    {                                                              //~9501I~
		int def=0;	//false                                        //~9501I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_CLOSABLE_RON),def)!=0;//~9501I~
        if (Dump.Y) Dump.println("RuleSetting.isFinalLastClosableRon rc="+rc);//~9501I~
        return rc;                                                 //~9501I~
    }                                                              //~9501I~
    public static boolean isFinalLastClosablePending()             //~9501I~
    {                                                              //~9501I~
		int def=0;	//false                                        //~9501I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_CLOSABLE_PENDING),def)!=0;//~9501I~
        if (Dump.Y) Dump.println("RuleSetting.isFinalLastClosablePending rc="+rc);//~9501I~
        return rc;                                                 //~9501I~
    }                                                              //~9501I~
    public static boolean isFinalLastClosableNotTop()             //~9501I~
    {                                                              //~9501I~
		int def=0;	//false                                        //~9501I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_CLOSABLE_NOTTOP),def)!=0;//~9501I~
        if (Dump.Y) Dump.println("RuleSetting.isFinalLastClosableNotTop rc="+rc);//~9501I~
        return rc;                                                 //~9501I~
    }                                                              //~9501I~
    public static int getGameSetType()                             //~9501I~
    {                                                              //~9501I~
        if (Dump.Y) Dump.println("RuleSetting.getGameSetType");//~vaqaI~//+varbR~
		int idx=AG.ruleProp.getParameter(getKeyRS(RSID_GAMESET_TYPE),0);//~9501I~
        int rc=intsGameSetType[idx];                               //~9501I~
        if (Dump.Y) Dump.println("RuleSetting.getGameSetType rc="+rc);//~9501I~
        return rc;                                                 //~9501I~
    }                                                              //~9501I~
    public static boolean isCloseGameFinalLastDealerNotPending()   //~9501I~
    {                                                              //~9501I~
        int id=AG.ruleProp.getParameter(getKeyRS(RSID_FL_NOTPENDING),0);//~9501I~
        boolean rc=id==FINALLAST_CLOSE;                            //~9501I~
        if (Dump.Y) Dump.println("RuleSetting.isCloseGameFinalLastDealerNotPending rc="+rc);//~9501I~
        return rc;                                                 //~9501I~
    }                                                              //~9501I~
    public static boolean isCloseGameFinalLastAllMinus()           //~9501I~
    {                                                              //~9501I~
        int id=AG.ruleProp.getParameter(getKeyRS(RSID_FL_ALLMINUS),0);//~9501I~
        boolean rc=id==FINALLAST_CLOSE;                            //~9501I~
        if (Dump.Y) Dump.println("RuleSetting.isCloseGameFinalLastAllMinus rc="+rc);//~9501I~
        return rc;                                                 //~9501I~
    }                                                              //~9501I~
    public static boolean isSpritPos()                             //~9528I~
    {                                                              //~9528I~
		int def=0;	//false                                        //~9528I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_SPRITPOS),def)!=0;//~9528I~
        if (Dump.Y) Dump.println("RuleSetting.isSpritPos rc="+rc); //~9528I~
        return rc;                                                 //~9528I~
    }                                                              //~9528I~
    public static boolean isRankMUp()                              //~9528I~
    {                                                              //~9528I~
		int def=1;	//true                                         //~9528I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_RANKMUP),def)!=0;//~9528I~
        if (Dump.Y) Dump.println("RuleSetting.isRankMUp rc="+rc);  //~9528I~
        return rc;                                                 //~9528I~
    }                                                              //~9528I~
    //**************************************                       //~9528I~
    public static boolean isDoraNext()                             //~9528I~
    {                                                              //~9528I~
    	int def=DORA_DEFAULT;                                      //~9528R~
        int id=AG.ruleProp.getParameter(getKeyRS(RSID_DORA),def);  //~9528I~
        boolean rc=id==DORA_NEXT;                                  //~9528I~
        if (Dump.Y) Dump.println("RuleSetting.isDoraNext ="+rc);   //~9528I~
        return rc;                                                 //~9528I~
    }                                                              //~9528I~
    //**************************************                       //~9528I~
    public static int getStyleHiddenDora()                         //~9528I~
    {                                                              //~9528I~
    	int def=DORA_HIDDEN_DEFAULT;                               //~9528R~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DORA_HIDDEN),def);//~9528I~
        if (Dump.Y) Dump.println("RuleSetting.getStyleHiddenDora rc="+rc);//~9528I~
        return rc;                                                 //~9528I~
    }                                                              //~9528I~
    //**************************************                       //~9528I~
    public static boolean isShowHiddenDora()                       //~9528I~
    {                                                              //~9528I~
        boolean rc=getStyleHiddenDora()!=DORA_HIDDEN_NO;           //~9528R~
        if (Dump.Y) Dump.println("RuleSetting.isShowHiddenDora rc="+rc);//~9528R~
        return rc;                                                 //~9528I~
    }                                                              //~9528I~
    //**************************************                       //~9528I~
    public static int getStyleKanDora()                            //~9528I~
    {                                                              //~9528I~
    	int def=DORA_KANDORA_DEFAULT;                              //~9528R~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_KANDORA),def);//~9528I~
        if (Dump.Y) Dump.println("RuleSetting.getStyleKanDora rc="+rc);//~9528I~
        return rc;                                                 //~9528I~
    }                                                              //~9528I~
    //**************************************                       //~9528I~
    public static boolean isKanDoraNo()                            //~9528I~
    {                                                              //~9528I~
        boolean rc=getStyleKanDora()==DORA_KANDORA_NO;             //~9528I~
        if (Dump.Y) Dump.println("RuleSetting.isKanDoraNo rc="+rc);//~9528I~
        return rc;                                                 //~9528I~
    }                                                              //~9528I~
    //**************************************                       //~9528I~
    public static int getStyleKanDoraHidden()                      //~9528I~
    {                                                              //~9528I~
    	int def=DORA_KANDORA_HIDDEN_DEFAULT;                       //~9528R~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_KANDORA_HIDDEN),def);//~9528I~
        if (Dump.Y) Dump.println("RuleSetting.getStyleKanDoraHidden rc="+rc);//~9528I~
        return rc;                                                 //~9528I~
    }                                                              //~9528I~
    //**************************************                       //~9528I~
    public static boolean isShowHiddenKanDora()                    //~9528R~
    {                                                              //~9528I~
    	boolean rc= isShowHiddenDora()         //HiddenDora=Yes    //~9529I~
                && !isKanDoraNo()              //KanDora=Yes       //~9529R~
                &&  getStyleKanDoraHidden()!=DORA_KANDORA_HIDDEN_NO;//~9529I~
        if (Dump.Y) Dump.println("RuleSetting.isShowHiddenKanDora rc="+rc);//~9528R~
        return rc;                                                 //~9528I~
    }                                                              //~9528I~
    public static boolean isOpenKanDoraJustNow()                   //~9529I~
    {                                                              //~9529I~
    	int def=DORA_KANDORA_OPEN_DEFAULT;                         //~9529I~
        int id=AG.ruleProp.getParameter(getKeyRS(RSID_KANDORA_OPEN),def);//~9529I~
        boolean rc=id==DORA_KANDORA_OPEN_JUSTNOW;                  //~9529I~
        if (Dump.Y) Dump.println("RuleSetting.isOpenKanDoraJustNow rc="+rc);   //~9529I~//~vaqhR~
        return rc;                                                 //~9529I~
    }                                                              //~9529I~
    public static boolean isOpenKanDoraExceptRon()                 //~9529I~
    {                                                              //~9529I~
    	int def=DORA_KANDORA_OPEN_DEFAULT;                         //~9529I~
        int id=AG.ruleProp.getParameter(getKeyRS(RSID_KANDORA_OPEN),def);//~9529I~
        boolean rc=id==DORA_KANDORA_OPEN_EXCEPTRON;                //~9529I~
        if (Dump.Y) Dump.println("RuleSetting.isOpenKanDoraExceptRon rc="+rc);//~9529I~
        return rc;                                                 //~9529I~
    }                                                              //~9529I~
	public static boolean isUseRed5Tile()                          //~9C01I~
    {                                                              //~9C01I~
		int def=0;                                                 //~9C01I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_USERED5),def)!=0;//~9C01I~
        if (Dump.Y) Dump.println("RuleSetting.isUseRed5Tile="+rc); //~9C01I~
        return rc;                                                 //~9C01I~
    }                                                              //~9C01I~
    //**************************************                       //~9C01I~
    //*PendingCont                                                 //~9709I~
    public static boolean isPendingCont()                          //~9709I~
    {                                                              //~9709I~
		int def=1;	//true                                         //~9709I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_CONT),def)!=0;//~9709I~
        if (Dump.Y) Dump.println("RuleSetting.isPendingCont rc="+rc);//~9709I~
        return rc;                                                 //~9709I~
    }                                                              //~9709I~
    //*********************************************************    //~9520I~
    //*********************************************************    //~9709I~
    //*********************************************************    //~9709I~
    //*used on other dialog to show related setting                //~9520I~
    //*********************************************************    //~9520I~
    public static void setMultiRon(View PView,boolean PswFixed)    //~9520I~
    {                                                              //~9520I~
        if (Dump.Y) Dump.println("RuleSetting.setMultiRon swFixed="+PswFixed);//~9520I~
	    URadioGroup rgMultiRon;                                    //~9520I~
        rgMultiRon=new URadioGroup(PView,R.id.rgMultiRon,0/*defaultIdx*/,rbIDMultiRon/*IDs*/);//~9520I~
        rgMultiRon.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_MULTIRON),0/*defaultIdx*/),PswFixed);//~9520I~
    }                                                              //~9520I~
    //*********************************************************    //~va8bI~
    public static void setYakuFix(View PView,boolean PswFixed)     //~va8bI~
    {                                                              //~va8bI~
        if (Dump.Y) Dump.println("RuleSetting.setYakuFix swFixed="+PswFixed);//~va8bI~
    	UCheckBox  cbYakuFixMultiwaitOK/*,cbYakuFixMultiwaitDrawOK*/;  //~va8bI~//~va8kR~
    	cbYakuFixMultiwaitOK=new UCheckBox(PView,R.id.cbYakuFixMultiwaitOK);//~va8bI~
//  	cbYakuFixMultiwaitDrawOK=new UCheckBox(PView,R.id.cbYakuFixMultiwaitDrawOK);//~va8bI~//~va8kR~
    	cbYakuFixMultiwaitOK.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX_MULTIWAITOK),0),PswFixed);//~va8bI~
//  	cbYakuFixMultiwaitDrawOK.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX_MULTIWAITDRAWOK),0),PswFixed);//~va8bI~//~va8kR~
                                                                   //~va8bI~
	    URadioGroup rgYakuFix;                                     //~va8bI~
        rgYakuFix=new URadioGroup(PView,R.id.rgYakuFix,0,rbsYakuFix);//~va8bI~
        rgYakuFix.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX),YAKUFIX_DEFAULT),PswFixed);//~va8bI~
    }                                                              //~va8bI~
    //*********************************************************    //~va8bI~
    public static void setYakuFix2(View PView,boolean PswFixed)    //~va8bI~
    {                                                              //~va8bI~
        if (Dump.Y) Dump.println("RuleSetting.setYakuFix2 swFixed="+PswFixed);//~va8bI~
	    URadioGroup rgYakuFix2;                                    //~va8bI~
        rgYakuFix2=new URadioGroup(PView,R.id.rgYakuFix2,0,rbsYakuFix2);//~va8bI~
        rgYakuFix2.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX2),YAKUFIX2_DEFAULT),PswFixed);//~va8bI~
    }                                                              //~va8bI~
    //*********************************************************    //~9520I~
    public static void setMinusStop(View PView,boolean PswFixed)   //~9520I~
    {                                                              //~9520I~
    	boolean swMinusStop=RuleSetting.isMinusStop();             //~9520I~
    	boolean swMinusPay=isMinusPay();                           //~9520I~
    	boolean swMinusPayGetAllPoint=isMinusPayGetAllPoint();     //~9520I~
    	boolean swMinus0=RuleSetting.isZeroStop();                 //~9520I~
    	int pointMinusStop=getPointMinusStop();                    //~9520I~
                                                                   //~9520I~
        if (Dump.Y) Dump.println("RuleSetting.setMultiStop swFixed="+PswFixed);//~9520I~
        UCheckBox cbMinusStop=new UCheckBox(PView,R.id.cbMinusStop);//~9520I~
        UCheckBox cbMinus0=new UCheckBox(PView,R.id.cbMinus0);     //~9520I~
        TextView tvPointMinusStop=(TextView)    UView.findViewById(PView,R.id.tvPointMinusStop);//~9520I~
        URadioGroup rgMinusPay=new URadioGroup(PView,R.id.rgMinusPay,0,rbIDMinusPay);//~9520I~
                                                                   //~9520I~
    	int mpid=swMinusPay ? 0 : (swMinusPayGetAllPoint ? 1 : 2); //~9520I~
        rgMinusPay.setCheckedID(mpid,true/*swFixed*/);             //~9520I~
        URadioGroup rg=new URadioGroup(PView,R.id.rgMinusStopByErr,0,rbIDMinusStopByErr);//~9520I~
    	int caseMinusStopByErr=getMinusStopByErr();                //~9520I~
        rg.setCheckedID(caseMinusStopByErr,true/*swFixed*/);       //~9520I~
                                                                   //~9520I~
    	cbMinusStop.setState(swMinusStop,true/*swFixed*/);         //~9520I~
    	cbMinus0.setState(swMinus0,true/*swFixed*/);               //~9520I~
                                                                   //~9520I~
        LinearLayout llSB=(LinearLayout)UView.findViewById(PView,R.id.llSBMinusPrize);//~9520I~
        llSB.setVisibility(View.GONE);                             //~9520I~
    	tvPointMinusStop.setText(Integer.toString(pointMinusStop));//~9520I~
    	tvPointMinusStop.setVisibility(View.VISIBLE);              //~9520I~
    }                                                              //~9520I~
    //*********************************************************    //~9520I~
    public static void setGameSet(View PView,boolean PswFixed)     //~9520I~
    {                                                              //~9520I~
    	USpinner spnGameSetType;                                   //~9520I~
    	UCheckBox  cbClosableRon,cbClosablePending,cbClosableNotTop;//~9520I~
   		URadioGroup rgFinalLastDealerNotPending,rgFinalLastAllMinus;//~9520I~
    //*********************                                        //~9520I~
        spnGameSetType =new USpinner(PView,R.id.spnGameSetType);   //~9520I~
        spnGameSetType.setArray(strsGameSetType);                  //~9521I~
    	cbClosableRon=new UCheckBox(PView,R.id.cbClosableRon);     //~9520I~
    	cbClosablePending=new UCheckBox(PView,R.id.cbClosablePending);//~9520I~
    	cbClosableNotTop=new UCheckBox(PView,R.id.cbClosableNotTop);//~9520I~
        rgFinalLastDealerNotPending=new URadioGroup(PView,R.id.rgFinalLastDealerNotPending,0,rbIDFinalLastDealerNotPending);//~9520I~
        rgFinalLastAllMinus=new URadioGroup(PView,R.id.rgFinalLastAllMinus,0,rbIDFinalLastDealerAllMinus);//~9520I~
                                                                   //~9520I~
    	cbClosableRon.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_CLOSABLE_RON),0),PswFixed);//~9520R~
    	cbClosablePending.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_CLOSABLE_PENDING),0),PswFixed);//~9520R~
    	cbClosableNotTop.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_CLOSABLE_NOTTOP),0),PswFixed);//~9520R~
        spnGameSetType.select(AG.ruleProp.getParameter(getKeyRS(RSID_GAMESET_TYPE),0),PswFixed);//~9520R~
        rgFinalLastDealerNotPending.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_FL_NOTPENDING),0),PswFixed);//~9520R~
        rgFinalLastAllMinus.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_FL_ALLMINUS),0),PswFixed);//~9520R~
    }                                                              //~9520I~
    //*********************************************************    //~9529I~
    public static void setDora(View PView,boolean PswFixed)        //~9529I~
    {                                                              //~9529I~
    	URadioGroup  rgDora,rgDoraHidden,rgKanDora,rgKanDoraHidden,rgKanDoraOpen;//~9529I~
    	UCheckBox  cbUseRed5;                                      //~9C01I~
    //*********************                                        //~9529I~
        rgDora=new URadioGroup(PView,R.id.rgDora,0/*UserParm*/,rbIDDora);//~9529I~
        rgDoraHidden=new URadioGroup(PView,R.id.rgDoraHidden,0/*UserParm*/,rbIDDoraHidden);//~9529I~
        rgKanDora=new URadioGroup(PView,R.id.rgKanDora,0/*UserParm*/,rbIDKanDora);//~9529I~
        rgKanDoraHidden=new URadioGroup(PView,R.id.rgKanDoraHidden,0/*UserParm*/,rbIDKanDoraHidden);//~9529I~
        rgKanDoraOpen=new URadioGroup(PView,R.id.rgKanDoraOpenTiming,0/*UserParm*/,rbIDMinKanDoraOpen);//~9529I~
    	cbUseRed5=new UCheckBox(PView,R.id.cbUseRed5);             //~9C01I~
                                                                   //~9529I~
        rgDora.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_DORA),DORA_DEFAULT),PswFixed);//~9529I~
        rgDoraHidden.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_DORA_HIDDEN),DORA_HIDDEN_DEFAULT),PswFixed);//~9529I~
        rgKanDora.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_KANDORA),DORA_KANDORA_DEFAULT),PswFixed);//~9529I~
        rgKanDoraHidden.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_KANDORA_HIDDEN),DORA_KANDORA_HIDDEN_DEFAULT),PswFixed);//~9529I~
        rgKanDoraOpen.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_KANDORA_OPEN),DORA_KANDORA_OPEN_DEFAULT),PswFixed);//~9529I~
    	cbUseRed5.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_USERED5),0),PswFixed);//~9C01I~
    }                                                              //~9529I~
    //*********************************************************    //~9530I~
    public static void setSpritPos(View PView,boolean PswFixed)    //~9530I~
    {                                                              //~9530I~
	    UCheckBox  cbSpritPos;                                     //~9530I~
    //*********************                                        //~9530I~
    	cbSpritPos=new UCheckBox(PView,R.id.cbSpritPos);           //~9530I~
    	cbSpritPos.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_SPRITPOS),0),PswFixed);//~9530I~
    }                                                              //~9530I~
    //*********************************************************    //~9709I~
    public static void setPendingCont(View PView,boolean PswFixed) //~9709I~
    {                                                              //~9709I~
		int def=1;	//true                                         //~9709I~
    	UCheckBox cbPendingCont=new UCheckBox(PView,R.id.cbPendingCont);     //~9709I~
    	int val=AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_CONT),def);//~9709I~
    	cbPendingCont.setStateInt(val,PswFixed);                   //~9709I~
        if (Dump.Y) Dump.println("RuleSetting.setPendingCont value="+val);//~9709I~
    }                                                              //~9709I~
    //*********************************************************    //~9819I~
    public static void setOrderPrize(View PView,boolean PswFixed)  //~9819I~
    {                                                              //~9819I~
    	USpinner spnOrderPrize=new USpinner(PView,R.id.spnOrderPrize);//~9819I~
        spnOrderPrize.setArray(R.array.OrderPrize);                //~9819I~
        int orderPrize=AG.ruleProp.getParameter(getKeyRS(RSID_ORDERPRIZE),DEFAULT_ORDERPRIZE);//~9829R~
        spnOrderPrize.select(orderPrize,PswFixed);                 //~9829I~
		URadioGroup rgOrderSamePoint=new URadioGroup(PView,R.id.rgOrderSamePoint,0,rbIDOrderSamePoint);//~9819I~
        rgOrderSamePoint.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_ORDERPRIZE_SAMEPOINT),0),PswFixed);//~9819I~
        if (Dump.Y) Dump.println("RuleSetting.setOrderPrize orderPrize="+orderPrize);     //~9819I~//~9829R~
    }                                                              //~9819I~
    //*********************************************************    //~9819I~
    public static void setGrillBird(View PView,boolean PswFixed)   //~9819I~
    {                                                              //~9819I~
        URadioGroup rgBirdPayType=new URadioGroup(PView,R.id.rgBirdPayType,0,rbIDBirdPayType);//~9819I~
        rgBirdPayType.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_BIRD_PAYTYPE),0),PswFixed);//~9819I~
    	UCheckBox cbBird=new UCheckBox(PView,R.id.cbBird);         //~9819I~
        cbBird.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_BIRD),0),PswFixed);//~9819I~
    	LinearLayout llSpinBtn=(LinearLayout)UView.findViewById(PView,R.id.llSBBird);//~9819I~
	    USpinBtn sbBird=USpinBtn.newInstance(llSpinBtn,DEFAULT_BIRD_MIN,DEFAULT_BIRD_MAX,DEFAULT_BIRD_INC,DEFAULT_BIRD);//~9819I~
        sbBird.setVal(AG.ruleProp.getParameter(getKeyRS(RSID_BIRD_PAY),DEFAULT_BIRD),PswFixed);//~9819I~
                                                                   //~9819I~
        if (Dump.Y) Dump.println("RuleSetting.setGrillBird");      //~9819I~
    }                                                              //~9819I~
    //*********************************************************    //~9823I~
    public static void setRobotOption(View PView,boolean PswFixed) //~9823I~
    {                                                              //~9823I~
        UCheckBox cbAllowRobot=new UCheckBox(PView,R.id.cbAllowRobot);//~9823I~
//      UCheckBox cbAllowRobotAll=new UCheckBox(PView,R.id.cbAllowRobotAll);//~va66R~
//      UCheckBox cbAllowRobotAllButton=new UCheckBox(PView,R.id.cbAllowRobotAllButton);//~va66R~
        UCheckBox cbThinkRobot=new UCheckBox(PView,R.id.cbThinkRobot);//~va60I~
        UCheckBox cbMinusRobot=new UCheckBox(PView,R.id.cbMinusRobot);//~9823I~
        URadioGroup rgRobotPay=new URadioGroup(PView,R.id.rgRobotPay,0,rbIDRobotPay);//~9823I~
                                                                   //~9823I~
        cbAllowRobot.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_ALLOW_ROBOT),0/*default:false*/),PswFixed);//~9823I~
//      cbAllowRobotAll.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_ALLOW_ROBOT_ALL),0/*default:false*/),PswFixed);//~va66R~
//      cbAllowRobotAllButton.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_ALLOW_ROBOT_ALL_BTN),0/*default:false*/),PswFixed);//~va66R~
        cbThinkRobot.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_THINK_ROBOT),DEFAULT_THINK_ROBOT/*default:true*/),PswFixed);//~va60I~//~va66R~
        cbMinusRobot.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_MINUSSTOP_ROBOT),0),PswFixed);//~9823I~
        rgRobotPay.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_ROBOT_PAY),0),PswFixed);//~9823I~
        if (Dump.Y) Dump.println("RuleSetting.setRobotOption");    //~9823I~
    }                                                              //~9823I~
    //*********************************************************    //~9829I~
    public static void setInitialScore(View PView,boolean PswFixed)//~9829I~
    {                                                              //~9829I~
    	USpinner spnInitialScore;                                  //~9829I~
    	USpinner spnDupPoint;                                      //~9829I~
        spnInitialScore  =          new USpinner(PView,R.id.InitialScore);//~9829I~
        spnDupPoint  =              new USpinner(PView,R.id.spnDupPoint);//~9829I~
        spnInitialScore.setArray(strInitialScore);                 //~9829I~
        spnDupPoint.setArray(strDupPoint);                         //~9829I~
        spnInitialScore.select(AG.ruleProp.getParameter(getKeyRS(RSID_INITSCORE),0),PswFixed);//~9829I~
        spnDupPoint.select(AG.ruleProp.getParameter(getKeyRS(RSID_POINT_DUP),0),PswFixed);//~9829I~
                                                                   //~9829I~
        TextView tv =(TextView)              UView.findViewById(PView,R.id.tvDebt);//~9829I~
        String str=Utils.getStr(R.string.Label_Debt,getDebt());           //~9829I~
        tv.setText(str);                                           //~9829I~
        if (Dump.Y) Dump.println("RuleSetting.setInitialScore");   //~9829I~
    }                                                              //~9829I~
    //**************************************                       //~va15I~
    public static int getSameMeld()                                //~va15I~
    {                                                              //~va15I~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_EATCHANGE),EATCHANGE_DEFAULT);//~va15I~
        if (Dump.Y) Dump.println("RuleSetting.getSameMeld rc="+rc);//~va15I~
        return rc;                                                 //~va15I~
    }                                                              //~va15I~
    //*******************************************************      //~vakQI~
    public boolean isChanged(int Pid)                       //~vakQR~
    {                                                              //~vakQI~
	    boolean rc=isChanged(Pid,curProp,AG.ruleProp);             //~vakQR~
        if (Dump.Y) Dump.println("RuleSetting.isChanged rc="+rc+",id="+Pid);//~var0I~
        return rc;                                                 //~vakQI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
    public boolean isChangedText(int Pid)                          //~vakQI~
    {                                                              //~vakQI~
	    boolean rc=isChangedText(Pid,curProp,AG.ruleProp);         //~vakQR~
        return rc;                                                 //~vakQI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
//  public boolean isChanged(int Pid,Prop PnewProp,Prop PoldProp)  //~vakQR~//~varbR~
    private boolean isChanged(int Pid,Prop PnewProp,Prop PoldProp) //~varbI~
    {                                                              //~vakQI~
    	String key=getKeyRS(Pid);                                  //~vakQI~
    	int oldval=PoldProp.getParameter(key,-1); //TODO test     //~vakQI~//~varbR~
    	int newval=PnewProp.getParameter(key,-1); //TODO test      //~vakQI~//~varbR~
//  	int oldval=PoldProp.getParameter(key,0);                   //~varbR~
//  	int newval=PnewProp.getParameter(key,0);                   //~varbR~
        boolean rc=newval!=oldval;                                 //~vakQI~
        if (Dump.Y) Dump.println("RuleSetting.isChanged rc="+rc+",id="+Pid+",key="+key+",old="+oldval+",new="+newval);//~vakQI~
        return rc;                                                 //~vakQI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
//  public boolean isChangedText(int Pid,Prop PnewProp,Prop PoldProp)//~vakQR~//~varbR~
    private boolean isChangedText(int Pid,Prop PnewProp,Prop PoldProp)//~varbI~
    {                                                              //~vakQI~
    	String key=getKeyRS(Pid);                                  //~vakQI~
    	String oldval=PoldProp.getParameter(key," ");              //~vakQI~
    	String newval=PnewProp.getParameter(key," ");              //~vakQI~
        boolean rc=!newval.equals(oldval);                         //~vakQR~
        if (Dump.Y) Dump.println("RuleSetting.isChangedText rc="+rc+",id="+Pid+",key="+key+",old="+oldval+",new="+newval);//~vakQI~
        return rc;                                                 //~vakQI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
    public boolean setBGUpdated(UCheckBox Pgui,boolean PswChanged) //~vakQR~
    {                                                              //~vakQI~
        if (Dump.Y) Dump.println("RuleSetting.setBGUpdated UCheckBox="+Pgui.checkbox.getText()+",swChanged="+PswChanged);//~vakQR~
    	if (PswChanged)                                            //~vakQI~
        {                                                          //~vakWI~
        	Pgui.checkbox.setBackgroundColor(COLOR_BG_CHANGED_RULE);//~vakQR~
            swAnyUpdate=true;                                      //~vakWI~
        }                                                          //~vakWI~
        return PswChanged;                                         //~vakQI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
    public boolean setBGUpdated(URadioGroup Pgui,boolean PswChanged)//~vakQR~
    {                                                              //~vakQI~
        if (Dump.Y) Dump.println("RuleSetting.setBGUpdated URagioGroup.radioGroup="+Pgui.radioGroup+",swChanged="+PswChanged);//~vakQR~
    	if (PswChanged)                                            //~vakQI~
        {                                                          //~vakWI~
        	Pgui.radioGroup.setBackgroundColor(COLOR_BG_CHANGED_RULE);//~vakQI~
            swAnyUpdate=true;                                      //~vakWI~
        }                                                          //~vakWI~
        return PswChanged;                                         //~vakQI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
    public boolean setBGUpdated(UEditText Pgui,boolean PswChanged) //~vakQR~
    {                                                              //~vakQI~
        if (Dump.Y) Dump.println("RuleSetting.setBGUpdated UEditText="+Pgui.editText.getText()+",swChanged="+PswChanged);//~vakQI~
    	if (PswChanged)                                            //~vakQI~
        {                                                          //~vakWI~
        	Pgui.editText.setBackgroundColor(COLOR_BG_CHANGED_RULE);//~vakQI~
            swAnyUpdate=true;                                      //~vakWI~
        }                                                          //~vakWI~
        return PswChanged;                                         //~vakQI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
    public boolean setBGUpdated(TextView Pgui,boolean PswChanged)  //~vakQR~
    {                                                              //~vakQI~
        if (Dump.Y) Dump.println("RuleSetting.setBGUpdated TextView="+Pgui.getText()+",swChanged="+PswChanged);//~vakQI~
    	if (PswChanged)                                            //~vakQI~
        {                                                          //~vakWI~
        	Pgui.setBackgroundColor(COLOR_BG_CHANGED_RULE);        //~vakQI~
            swAnyUpdate=true;                                      //~vakWI~
        }                                                          //~vakWI~
        return PswChanged;                                         //~vakQI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
    public boolean setBGUpdated(USpinBtn Pgui,boolean PswChanged)  //~vakQR~
    {                                                              //~vakQI~
        if (Dump.Y) Dump.println("RuleSetting.setBGUpdated USpinBtn="+Pgui.tvText.getText()+",swChanged="+PswChanged);//~vakQI~
    	if (PswChanged)                                            //~vakQI~
        {                                                          //~vakWI~
        	Pgui.tvText.setBackgroundColor(COLOR_BG_CHANGED_RULE); //~vakQI~
            swAnyUpdate=true;                                      //~vakWI~
        }                                                          //~vakWI~
        return PswChanged;                                         //~vakQI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
    public boolean setBGUpdated(USpinner Pgui,boolean PswChanged)  //~vakQR~
    {                                                              //~vakQI~
        if (Dump.Y) Dump.println("RuleSetting.setBGUpdated USpinner spinner="+Pgui.spinner+",swChanged="+PswChanged);//~vakQI~
    	if (PswChanged)                                            //~vakQI~
        {                                                          //~vakWI~
        	Pgui.setBackgroundColor(COLOR_BG_CHANGED_RULE);        //~vakQR~
            swAnyUpdate=true;                                      //~vakWI~
        }                                                          //~vakWI~
        return PswChanged;                                         //~vakQI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
//  public boolean setBGUpdated(UButtonRG Pgui,int Pid)            //~vakQR~//~varbR~
    public boolean setBGUpdated(UButtonRG Pgui,int Pid,int Pdefault)//~varbI~
    {                                                              //~vakQI~
//      return setBGUpdated(AG.ruleProp,Pgui,Pid);        //~vakQR~//~varbR~
        return setBGUpdated(AG.ruleProp,Pgui,Pid,Pdefault);        //~varbI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakQI~
//  public boolean setBGUpdated(Prop PoldProp,UButtonRG Pgui,int Pid)//~vakQR~//~varbR~
    private boolean setBGUpdated(Prop PoldProp,UButtonRG Pgui,int Pid,int Pdefault)//~varbI~
    {                                                              //~vakQI~
        if (Dump.Y) Dump.println("RuleSetting.setBGUpdated UButtonRG id="+Pid+",default="+Pdefault);//~var0I~//~varbR~
    	String key=getKeyRS(Pid);                                  //~vakQI~
//    	int oldval=PoldProp.getParameter(key,-1);    //TODO test  //~vakQI~//~varbR~
    	int oldval=PoldProp.getParameter(key,Pdefault);            //~varbR~
        boolean swChanged=Pgui.setBGUpdated(COLOR_BG_CHANGED_RULE,oldval);//~vakQR~
    	if (swChanged)                                            //~vakWI~
        {                                                          //~vakWI~
            swAnyUpdate=true;                                      //~vakWI~
        }                                                          //~vakWI~
        if (Dump.Y) Dump.println("RuleSetting.setBGUpdated UButtonRG id="+Pid+",rc="+swChanged);//~vakQI~
        return swChanged;                                          //~vakQI~
    }                                                              //~vakQI~
    //*******************************************************      //~vakWI~
    public void showUpdate(boolean PswUpdate)                      //~vakWI~
    {                                                              //~vakWI~
        if (Dump.Y) Dump.println("RuleSetting.showUpdate PswUpdate="+PswUpdate);//~vakWI~
        int msgid;                                                 //~vakWR~
        if (PswUpdate)                                             //~vakWI~
			msgid=R.string.Info_RuleReceived_ChangeY;              //~vakWI~
        else                                                       //~vakWI~
        if (swServer)	//of RuleSettingDlg                        //~vakWI~
 			msgid=R.string.Info_RuleReceived_ChangeN_Server;       //~vakWI~
        else                                                       //~vakWI~
        	msgid=R.string.Info_RuleReceived_ChangeN;              //~vakWI~
        showStatus(msgid);	//SettingDlg.showStatus()                     //~vakWI~
    }                                                              //~vakWI~
    //*******************************************************      //~vaqaR~
//  private static boolean isAppVersionUnmatch(String PstrProp)    //~vaqaR~//~var0R~
    public  static boolean isAppVersionUnmatch(String PstrProp)    //~var0I~
    {                                                              //~vaqaR~
        if (Dump.Y) Dump.println("RuleSetting.isAppVersionUnmatch prop="+PstrProp);//~vaqaR~
    	boolean rc=true;   //unmatch                               //~vaqaR~
        int pos=PstrProp.indexOf(RSID_STR_APPVERSION);             //~vaqaR~
        String verReceived="Unknown";                              //~vaqaR~
        String appVerMin=Utils.getStr(R.string.app_MinVersion);    //~vaqaR~
        if (pos>0)                                                 //~vaqaR~
        {                                                          //~vaqaR~
        	int pos2=pos+RSID_STR_APPVERSION.length()+1;           //~vaqaR~
        	if (Dump.Y) Dump.println("RuleSetting.isAppVersionUnmatch pos2="+pos2+"="+(pos2>=0 ? PstrProp.substring(pos2):"null"));//~vaqaI~
            int pos22=PstrProp.indexOf('\n',pos2);                 //~vaqaR~
        	if (Dump.Y) Dump.println("RuleSetting.isAppVersionUnmatch pos22="+pos22);//~vaqaI~
            if (pos22>pos2)                                        //~vaqaR~
	            verReceived=PstrProp.substring(pos2,pos22);        //~vaqaR~
        	if (Dump.Y) Dump.println("RuleSetting.isAppVersionUnmatch pos2="+pos2+",pos22="+pos22+",verReceived="+verReceived+",appVerMin="+appVerMin);//~vaqaR~
            if (verReceived.compareTo(appVerMin)>=0)               //~vaqaR~
            	rc=false;   //match                                //~vaqaR~
        }                                                          //~vaqaR~
        if (!rc)                                                   //~vaqaR~
        	return false;                                          //~vaqaR~
        issueUnmatchAlert(verReceived,appVerMin);                     //~vaqaR~
        return true;                                               //~vaqaR~
    }                                                              //~vaqaR~
    //*******************************************************      //~vaqaI~
    //*from ResumeDlg                                              //~vaqaI~
    //*******************************************************      //~vaqaI~
    public  static boolean isAppVersionUnmatch(Prop Pprop/*of HistoryData*/)//~vaqaI~
    {                                                              //~vaqaI~
        if (Dump.Y) Dump.println("RuleSetting.isAppVersionUnmatch prop="+Pprop);//~vaqaI~
    	boolean rc=true;   //unmatch                               //~vaqaI~
        String verHD=Pprop.getParameter(getKeyRS(RSID_APPVERSION),"Unknown");//~vaqaI~
        String appVerMin=Utils.getStr(R.string.app_MinVersion);    //~vaqaI~
        if (Dump.Y) Dump.println("RuleSetting.isAppVersionUnmatch verHD="+verHD+",appVerMin="+appVerMin);//~vaqaI~
        if (verHD.compareTo(appVerMin)>=0)                         //~vaqaI~
        	rc=false;   //match                                    //~vaqaI~
        if (!rc)                                                   //~vaqaI~
        	return false;                                          //~vaqaI~
//      issueUnmatchAlertHD(verHD,appVerMin);                //~vaqaI~//~vaqhR~
        if (Dump.Y) Dump.println("RuleSetting.isAppVersionUnmatch rc="+true);//~vaqhI~
        return true;                                               //~vaqaI~
    }                                                              //~vaqaI~
    //*******************************************************      //~vaqaR~
    private static void issueUnmatchAlert(String PverReceived,String PverApp)//~vaqaR~
    {                                                              //~vaqaR~
        if (Dump.Y) Dump.println("RuleSetting.issueUnmatchAlert"); //~vaqaR~
        String msg=Utils.getStr(R.string.Alert_AppVersionUnmatch,PverReceived,PverApp);//~vaqaR~
        Alert.showMessage(TITLEID,msg);                            //~vaqaR~
    }                                                              //~vaqaR~
//    //*******************************************************      //~vaqaI~//~vaqhR~
//    private static void issueUnmatchAlertHD(String PverReceived,String PverApp)//~vaqaI~//~vaqhR~
//    {                                                              //~vaqaI~//~vaqhR~
//        if (Dump.Y) Dump.println("RuleSetting.issueUnmatchAlertHD");//~vaqaI~//~vaqhR~
//        String msg=Utils.getStr(R.string.Alert_AppVersionUnmatchHD,PverReceived,PverApp);//~vaqaI~//~vaqhR~
//        Alert.showMessage(TITLEID,msg);                            //~vaqaI~//~vaqhR~
//    }                                                              //~vaqaI~//~vaqhR~
}//class                                                           //~v@@@R~
