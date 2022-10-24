//*CID://+vatgR~:                             update#=  836;       //~vatgR~
//*****************************************************************//~v101I~
//2022/10/20 vatg after first install, reset SYNCDAYE_INIT automatically//~vatgI~
//2022/10/07 varb (Bug)dump when prop is initial at received rule(UButtonRG default is -1)//~varbI~
//2022/09/09 var2 summary rule setting dialog;add from operation   //~var2I~
//2022/09/09 var1 summary rule setting dialog;drop yakuman         //~var1I~
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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.btmtest.BT.BTMulti;
import com.btmtest.MainView;
import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.gui.CommonListener;
import com.btmtest.gui.UButton;
import com.btmtest.gui.UEditText;                                  //~9903I~
import com.btmtest.gui.UButtonRG;
import com.btmtest.gui.URadioGroup;
import com.btmtest.gui.USpinBtn;
import com.btmtest.utils.Prop;                                     //~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.USpinner;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import com.btmtest.game.Status;                                    //~9412I~

import static com.btmtest.AG.*;
import static com.btmtest.dialog.RuleSettingEnum.*;                  //~9404I~//~9412R~
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.dialog.RuleSettingOperation.*;

public class RuleSettingSumm extends RuleSetting                        //~v@@@R~//~var0R~
              implements CommonListener.CommonListenerI            //~9902I~
             ,UCheckBox.UCheckBoxI                                 //~var0I~
{                                                                  //~2C29R~
	public  static final String PROP_NAME="RuleSetting";           //~9404I~
	private static final int    TITLEID=R.string.Title_RuleSettingSumm;//~v@@@I~//~var0R~
	private static final int    LAYOUTID=R.layout.setting_rulesumm;      //~v@@@I~//~9412R~//~var0R~
	private static final int    LAYOUTID_SMALLFONT=R.layout.setting_rulesumm_theme;//~vac5I~//~var0R~
	private static final int    HELP_TITLEID=R.string.Title_RuleSettingSumm;//~v@@@I~//~var0R~
	private static final String HELPFILE="RuleSettingSumm";       //~v@@@I~//~9412R~//~9515R~//~9615R~//~9C13R~//~var0R~
                                                                   //~9408I~
                                                                   //~9414I~
    private static final int COLOR_BG_CHANGED_RULE=AG.getColor(R.color.bg_rule_updated);//~vakQI~
    private static final int MINUSPRIZE_MINEMS=3;                  //~9412I~
                                                                   //~9408I~
    private static final int[] rbIDOrderSamePoint=new int[]{R.id.rbOrderSamePointEswn,R.id.rbSamePointSpritCut};//~9407I~
                                                                   //~9408I~
                                                                   //~v@@@I~
    //*****************************************************        //~9404I~
//  private Button btnYakuList;                                    //~9515I~//~var0R~
//  private Button btnOperation;                                   //~9624I~//~var0R~
    private Button btnDetail;                                      //~var0I~
    private USpinner spnInitialScore;                              //~v@@@R~
    private USpinner spnDupPoint;                                  //~9512I~
//  private UEditText etInitialScoreTestE,etInitialScoreTestS,etInitialScoreTestW,etInitialScoreTestN;//~9903I~//~var0R~
    private UCheckBox cbMinusStop,cbMinus0,cbMinusRobot;           //~9403I~
    private UCheckBox cbBird,cbAllowRobot;                                      //~9430I~//~9607R~
    private UCheckBox cbThinkRobot;                         //~va60I~
    private URadioGroup rgMinusPay,rgMinusStopByErr;               //~9414R~
    private URadioGroup rgBirdPayType;                             //~9602I~
    private URadioGroup rgRobotPay;                                //~9429I~
    private URadioGroup rgMultiRon;                                //~9408I~//~9409R~//~9513R~
    private UCheckBox cbMultiRon3Drawn;                            //~9409I~//~9513R~
//  private UCheckBox cbDrawnHW99,cbDrawnHW4W,cbDrawnHW4K,cbDrawnHW4R;//~9425I~//~var0R~
//  private UCheckBox cbDrawnHW99Cont,cbDrawnHW4WCont,cbDrawnHW4KCont,cbDrawnHW4RCont,cbMultiRon3DrawnCont;//~9B13I~//~var0R~
                                                                   //~9407I~
    private TextView tvSyncDate;                                   //~9903I~
    private TextView tvAppVersion;                                 //~vaqaI~
//  private UEditText etIDName;                                    //~9903I~//~var0R~
                                                                   //~9407I~
    private USpinner spnOrderPrize;                                //~9407I~
    private URadioGroup rgOrderSamePoint;                          //~9407I~
    private USpinBtn sbMinusPrize;                                 //~9408I~
    private USpinBtn sbBird;                                       //~9430I~
                                                                   //~9413I~
    private UCheckBox  cbDrawnManganDropBird;                      //~9B13I~
//  private String strReceivedProp;                                //~9616I~//~var0R~
                                                                   //~9501I~
    protected UButtonRG bgScoreToPoint;                            //~9417I~//~var0R~
                                                                   //~9413I~
                                                                   //~9501I~
    private USpinner spnGameSetType;                               //~9501I~
    private UCheckBox  cbClosableRon,cbClosablePending,cbClosableNotTop;//~9501I~
    private URadioGroup rgFinalLastDealerNotPending,rgFinalLastAllMinus;//~9501I~
    private URadioGroup rgEatChange;                               //~9516I~
    private UCheckBox  cbSpritPos;                                 //~9528I~
    private UCheckBox  cbRankMUp;                                  //~9528I~
    private URadioGroup  rgDora,rgDoraHidden,rgKanDora,rgKanDoraHidden,rgKanDoraOpen;//~9528R~//~9529R~
    private UCheckBox  cbUseRed5;                                  //~9C01I~
//  private UCheckBox  cbPendingCont;                              //~9709I~//~var0R~
    private Prop parmProp;                                         //~9830I~
    public RuleSettingOperation aRuleSettingOperation;             //~vakRI~
    public RuleSettingYaku      aRuleSettingYaku;                  //~vakRI~
//  public RuleSetting          aRuleSettingDetail;                //~var0R~
    public RuleSettingDetail    aRuleSettingDetail;                //~var0I~
    private RuleSetting RSD;                                       //~var0I~
    //*_SummOther**************                                    //~var0R~
    private UCheckBox cbKuitan,cbPinfuTaken;                       //~var0I~
    private UCheckBox cbDoublePillow;                              //~var0I~
    private UCheckBox cbShowAnywayBtn;                             //~var0I~
    private UCheckBox cbPendingRankNo;                             //~var0I~
    private UCheckBox cbPendingRank0OK;                            //~var0I~
    private URadioGroup rgPendingRank2;                            //~var0I~
    private URadioGroup rgYaku7Pair;                               //~var0I~
    private UCheckBox cbYaku7Pair4Pair;                            //~var0I~
    private URadioGroup rgYakuFix,rgYakuFixMultiwaitTake;          //~var0I~
    private UCheckBox  cbOpenReach,cbAnkanAfterReach,cbOneShot;    //~var0I~
    private URadioGroup rgFuritenReach,rgOpenReach;                //~var0I~
    private UCheckBox cbOpenReachRobot;                            //~var0I~
    private URadioGroup rgYakuFix2;                                //~var0I~
//  private UCheckBox cbYakuMan2_4Anko,cbYakuMan2_Kokusi13,cbYakuMan2_9Ren9,cbYakuMan2_4Wind;//~var0I~//~var1R~
//  private UCheckBox cbAllGreenNoBlue,cb9RenPinSou,cbNoPair13,cbNoPair14,cbBigRing,cbRank13,cbKokusiAnkanRon;//~var0R~//~var1R~
//  private UCheckBox cb5thKan;                                    //~var0I~//~var1R~
//  private UCheckBox cbRenhoMix;                                  //~var0I~//~var1R~
//  private USpinner spnRenhoRank;                                 //~var0I~//~var1R~
//  private URadioGroup rgDrawnMangan;                             //~var0I~//~var1R~
//  private USpinner spnDrawnManganRank;                           //~var0I~//~var1R~
    //*_SummOtherOperation**************                           //~var2I~
    private UCheckBox cb2TouchCancelableRon;                       //~var2I~
    private UCheckBox cbYakuFix1;                                  //~var2I~
    private UCheckBox cbChkMultiWait;                              //~var2I~
    private UCheckBox cb2CheckRonValue;                            //~var2I~
    private UCheckBox cb2CheckReach;                               //~var2I~
    //*****************************************************        //~var0I~
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
    private static boolean swOrderByEswn=false;                    //~v@@@R~
                                                                   //~9405I~
//  private boolean swOpenAfterDismiss;                            //~9405R~//~var0R~
//  private String senderYourName;                                 //~9405I~//~var0R~
    public boolean swChangedYaku;                                  //~9516I~
    public boolean swChangedOperation;                             //~9624I~
    public boolean swChildInitializing;                            //~9B10I~
//  private boolean swAnyUpdate;                                   //~vakWI~//~var0R~
    private boolean swResetInitial;                                //~vatgI~
    //******************************************                   //~v@@@M~
	public RuleSettingSumm()                                       //~var0R~
    {
        super();
        AG.aRuleSettingSumm=this;                                      //~9404I~//~var0R~
    	RSD=(RuleSetting)this;                                    //~var0I~
        swRuleSetting=true;		//update AG.ruleSyncDate           //~9622I~//~var0R~
        swRuleSettingSumm=true;                                    //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.defaultConstructor"); //~v@@@I~//~var0R~
    }
    //******************************************                   //~9902I~
    @Override //CommonListenerI                                    //~9B10I~
    public void onWidgetChanged(int PwidgetType,int PviewID,int Pvalue,String PstrValue)//~9902I~//~9903R~
    {                                                              //~9902I~
        if (Dump.Y) Dump.println("RuleSettingSumm.onWidgetChanged swChildInitializing="+swChildInitializing+",type="+PwidgetType+",viewID="+Integer.toHexString(PviewID)+",value="+Pvalue+",strValue="+Utils.toString(PstrValue));//~9902R~//~9903R~//~9B10R~//~var0R~
    	if (!swChildInitializing)                                  //~9B10I~
		    tvShowStatus.setText(Utils.getStr(R.string.Info_RuleSettingUpdated));//~9902I~//~9B10R~//~var0R~
    }                                                              //~9902I~
    public static RuleSettingSumm newInstance()                        //~v@@@I~//~var0R~
    {                                                              //~v@@@I~
        RuleSettingSumm dlg=new RuleSettingSumm();                         //~v@@@I~//~var0R~
        UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),           //SettingDlg//~vac5I~
                    UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN,//~v@@@I~
                    HELP_TITLEID,HELPFILE);                   //~v@@@I~//~9C13R~
        return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~vatgI~
    public static void newInstanceForInitial()           //~vatgI~
    {                                                              //~vatgI~
        if (Dump.Y) Dump.println("RuleSettingSumm.newInstanceForInitial");//~vatgI~
        RuleSettingSumm dlg=newInstance();                         //~vatgI~
        dlg.swResetInitial=true;                                   //~vatgI~
        dlg.show();                                                //~vatgI~
    }                                                              //~vatgI~
    //******************************************                   //~9405I~
    public static RuleSettingSumm newInstance(boolean PswReceived,String PsenderYourName,String PreceivedProp)//~9616I~//~var0R~
    {                                                              //~9405I~
        if (Dump.Y) Dump.println("RuleSettingSumm.newInstance swReceived="+PswReceived+",senderYourName="+PsenderYourName);//~9405I~//~var0R~
        RuleSettingSumm dlg=newInstance();                             //~9405I~//~var0R~
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
        if (Dump.Y) Dump.println("RuleSettingSumm.showRuleInGame");    //~9408I~//~var0R~
        if (Utils.isShowingDialogFragment(AG.aRuleSettingSumm))        //~9408I~//~var0R~
        {                                                          //~9408I~
            UView.showToast(R.string.Err_AlreadyShowing);          //~9408I~
            return;                                                //~9408I~
        }                                                          //~9408I~
        RuleSettingSumm dlg=newInstance();                             //~9408I~//~var0R~
        dlg.swShowInGame=true;	//not updatable                    //~9408I~
        dlg.parmProp=Pprop;                                        //~9830I~
        dlg.show();                                                //~9408I~
    }                                                              //~9408I~
    //*****************************************************************                   //~9404I~//~9406R~
    //*from MainActivity by btnGame after member checked           //~9406R~
    //*rc:true:NG(changed)                                         //~9406R~
    //*****************************************************************//~9406I~
    public static boolean chkChangedRule()                             //~9404I~//~9406R~
    {                                                              //~9404I~
    	boolean rc;                                                //~9406I~
        if ((TestOption.option & TestOption.TO_RULE_NOSYNC)!=0)   //TODO test//~9406I~
        	return false;                                          //~9406I~
        rc=!AG.aBTMulti.isRuleSynched(); //by SS_OK                //~9621I~
        if (rc)                                                    //~9406R~
        {                                                          //~9406I~
            String serverYourName=BTMulti.getYourNameServer();     //~9406I~
            if (BTMulti.isServerDevice())                          //~9406I~
            {                                                      //~9406I~
                Prop p=AG.ruleProp;                                        //~9404I~//~9406R~
                String sync=p.getParameter(getKeyRS(RSID_SYNCDATE),"");   //~9404I~//~9406R~
                if (Dump.Y) Dump.println("RuleSettingSumm.chkChangedRule AG="+AG.ruleSyncDate+",prop="+sync);//~9404I~//~9406R~//~var0R~
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
        if (Dump.Y) Dump.println("RuleSettingSumm.chkChangedRule rc="+rc);//~9406I~//~var0R~
        return rc;                                              //~9404I~//~9406R~
    }                                                              //~9404I~
    //******************************************                   //~9404I~
    @Override                                                      //~9404I~
    public void onDismissDialog()                                  //~9404I~
    {                                                              //~9404I~
        if (Dump.Y) Dump.println("RuleSettingSumm.onDismissDialog swOpenAfterDismiss="+swOpenAfterDismiss);     //~9404I~//~9616R~//~vakRR~//~var0R~
        CommonListener.resetListener();                            //~9902I~
    	RuleSettingSumm parent=AG.aRuleSettingSumm;                                      //~9404I~//~vakRR~//~var0R~
        if (Utils.isShowingDialogFragment(parent.aRuleSettingDetail))//~var0I~
        {                                                          //~var0I~
        	if (Dump.Y) Dump.println("RuleSettingSumm.onDismissDialog by Detail Button");//~var0I~
        	parent.aRuleSettingDetail.dismiss();                   //~var0I~
        }                                                          //~var0I~
    	AG.aRuleSettingSumm=null;                                      //~vakRI~//~var0R~
	    if (swOpenAfterDismiss)                                    //~9405I~
        {                                                          //~9405I~
//      	if (Utils.isShowingDialogFragment(parent.aRuleSettingOperation))//~vakRI~//~var0R~
//      		parent.aRuleSettingOperation.dismiss();            //~vakRI~//~var0R~
//      	if (Utils.isShowingDialogFragment(parent.aRuleSettingYaku))//~vakRI~//~var0R~
//      		parent.aRuleSettingYaku.dismiss();                 //~vakRI~//~var0R~
		    swOpenAfterDismiss=false;                              //~9405I~
    		RuleSettingSumm.newInstance(true/*swReceived*/,senderYourName,strReceivedProp).show();//~9616I~//~var0R~
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
                    if (Dump.Y) Dump.println("RuleSettingSumm.onShow swReceived="+swReceived+",inGame="+swShowInGame+",swResetInitial="+swResetInitial);//~9902I~//~var0R~//~vatgR~
                    if (swResetInitial)                             //~vatgI~
                    {                                              //~vatgI~
	                    if (Dump.Y) Dump.println("RuleSettingSumm.onShow issue OnClieck()");//~vatgI~
                    	onClickOK();	//SettinDLG, disloag2Properties, dismiss//~vatgI~
            			MainView.drawMsg(R.string.Info_RuleIsInitialized);//+vatgI~
						UView.showToastLong(R.string.Info_RuleIsInitializedMsg);//+vatgI~
                        return;                                    //~vatgI~
                    }                                              //~vatgI~
                    if (!swReceived && !swShowInGame)              //~9902I~
                        CommonListener.setListener((CommonListener.CommonListenerI)(AG.aRuleSettingSumm));//~9902R~//~var0R~
            	}                                                  //~9902I~
            }                       );                             //~9902I~
    }                                                              //~9902I~
    //******************************************                   //~v@@@M~
    @Override
    protected void initLayout(View PView)                            //~v@@@I~//~9403R~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("RuleSettingSumm.initLayout");          //~v@@@I~//~9403R~//~9616R~//~var0R~
        btnDetail=UButton.bind(PView,R.id.btnDetail,this);         //~var0M~
        super.initLayout(PView);                                   //~9403R~//~var0R~
        setOnShowListener();                                       //~vatgI~
    }                                                              //~v@@@M~
    //******************************************                   //~9406I~
    private boolean setFixed()                                     //~9406I~
    {                                                              //~9406I~
        boolean rc=swReceived || swShowInGame;                                     //~9406I~//~9408R~
        if (!rc)                                                   //~9406I~
        {                                                          //~9406I~
        	if (Status.isGamingNow())	//before gameover          //~9730I~
	            	rc=true;                                       //~9406I~
        }                                                          //~9406I~
        if (Dump.Y) Dump.println("RuleSettingSumm.setFixed() rc="+rc+",swReceived="+swReceived+",swShowIngame="+swShowInGame);   //~9406I~//~9421R~//~9616R~//~var0R~
        return rc;                                                 //~9406I~
    }                                                              //~9406I~
    //******************************************                   //~9405I~
    protected void setTitle()                                      //~9405I~
    {                                                              //~9405I~
        if (Dump.Y) Dump.println("RuleSettingSumm.setTitle swReceived="+swReceived);//~9405I~//~9412R~//~var0R~
        if (!swReceived)                                           //~9405I~
        	return;                                                //~9405I~
  		Spanned st=Utils.fromHtml(AG.resource.getString(R.string.Title_RuleSettingReceived,Utils.getStr(TITLEID),senderYourName));//~va40I~//~var0R~
        getDialog().setTitle(st);                                              //~9405I~
    }                                                              //~9405I~
	//*****************                                            //~9403I~
    protected void setupLayout(View PView)                         //~9403I~
    {                                                              //~9403I~
        TextView tv;                                               //~9416I~
        String str;                                                //~9416I~
    	LinearLayout llSpinBtn;                                    //~9412I~
        if (Dump.Y) Dump.println("RuleSettingSumm.setupLayout");         //~9403I~//~9412R~//~var0R~
    //*                                                            //~9405I~
        tvSyncDate=(TextView)       UView.findViewById(PView,R.id.tvSyncDate);//~9405I~
        tvAppVersion=(TextView)     UView.findViewById(PView,R.id.tvAppVersion);//~vaqaI~
        etIDName  =UEditText.bind(PView,R.id.etIDName,null/*CommonListener*/);//~9903I~
    //*                                                            //~9405I~
        spnInitialScore  =          new USpinner(PView,R.id.InitialScore);//~9403I~
//      etInitialScoreTestE=UEditText.bind(PView,R.id.etInitialScoreTestE,null/*CommonListener*/);//~9903I~//~var0R~
//      etInitialScoreTestS=UEditText.bind(PView,R.id.etInitialScoreTestS,null/*CommonListener*/);//~9903I~//~var0R~
//      etInitialScoreTestW=UEditText.bind(PView,R.id.etInitialScoreTestW,null/*CommonListener*/);//~9903I~//~var0R~
//      etInitialScoreTestN=UEditText.bind(PView,R.id.etInitialScoreTestN,null/*CommonListener*/);//~9903I~//~var0R~
        spnDupPoint  =          new USpinner(PView,R.id.spnDupPoint);//~9512I~
//      if ((TestOption.option & TestOption.TO_INITIALSCORE)!=0)   //~9425I~//~var0R~
//          ((LinearLayout)UView.findViewById(PView,R.id.llInitialScoreTest)).setVisibility(View.VISIBLE);//~9425I~//~var0R~
        tv =(TextView)              UView.findViewById(PView,R.id.tvDebt);//~9416R~
        str=Utils.getStr(R.string.Label_Debt,getDebt());    //~9416I~
        tv.setText(str);                                           //~9416I~
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
        rgOrderSamePoint=new URadioGroup(PView,R.id.rgOrderSamePoint,0,rbIDOrderSamePoint);//~9407I~
    //*multiRon                                                    //~9408I~
        rgMultiRon=new URadioGroup(PView,R.id.rgMultiRon,0/*defaultIdx*/,rbIDMultiRon/*IDs*/);//~9408I~//~9409R~//~9513R~
//  //*drawnHW                                                     //~9425I~//~var0R~
//      cbMultiRon3Drawn=new UCheckBox(PView,R.id.cb3Ron);         //~9425I~//~var0R~
//      cbDrawnHW99=new UCheckBox(PView,R.id.cb99Tile);            //~9425R~//~var0R~
//      cbDrawnHW4W=new UCheckBox(PView,R.id.cb4Wind);             //~9425R~//~var0R~
//      cbDrawnHW4K=new UCheckBox(PView,R.id.cb4Kan);              //~9425R~//~var0R~
//      cbDrawnHW4R=new UCheckBox(PView,R.id.cb4Reach);            //~9425R~//~var0R~
//      cbMultiRon3DrawnCont=new UCheckBox(PView,R.id.cb3RonCont); //~9B13I~//~var0R~
//      cbDrawnHW99Cont=new UCheckBox(PView,R.id.cb99TileCont);    //~9B13I~//~var0R~
//      cbDrawnHW4WCont=new UCheckBox(PView,R.id.cb4WindCont);     //~9B13I~//~var0R~
//      cbDrawnHW4KCont=new UCheckBox(PView,R.id.cb4KanCont);      //~9B13I~//~var0R~
//      cbDrawnHW4RCont=new UCheckBox(PView,R.id.cb4ReachCont);    //~9B13I~//~var0R~
    //*drawnMangan                                                 //~9413I~
    //*ScoreToPoint                                                //~9416I~//~var0R~
        bgScoreToPoint=new UButtonRG((ViewGroup)PView,rbIDScoreToPoint.length);//~9417I~//~var0R~
        for (int ii=0;ii<rbIDScoreToPoint.length;ii++)             //~9417I~//~var0R~
    		bgScoreToPoint.add(IDScoreToPoint[ii],rbIDScoreToPoint[ii]);//~9417I~//~var0R~
        bgScoreToPoint.setDefaultChk(S2P_NO);                      //~9417I~//~var0R~
//  //*robot                                                       //~9429I~//~var0R~
//      cbAllowRobot=new UCheckBox(PView,R.id.cbAllowRobot);       //~9403R~//~9429I~//~9607R~//~var0R~
//      cbThinkRobot=new UCheckBox(PView,R.id.cbThinkRobot);       //~va60I~//~var0R~
//      cbMinusRobot=new UCheckBox(PView,R.id.cbMinusRobot);       //~9607I~//~var0R~
//      rgRobotPay=new URadioGroup(PView,R.id.rgRobotPay,0,rbIDRobotPay);//~9429I~//~var0R~
    //*bird                                                        //~9430I~
        rgBirdPayType=new URadioGroup(PView,R.id.rgBirdPayType,0,rbIDBirdPayType);//~9602I~
        cbBird     =new UCheckBox(PView,R.id.cbBird);              //~9430I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBBird);//~9430I~
    	sbBird=USpinBtn.newInstance(llSpinBtn,DEFAULT_BIRD_MIN,DEFAULT_BIRD_MAX,DEFAULT_BIRD_INC,DEFAULT_BIRD);//~9430I~
    //*GameSet                                                     //~var0I~
        spnGameSetType =new USpinner(PView,R.id.spnGameSetType);   //~9501I~//~var0I~
        spnGameSetType.setArray(strsGameSetType);                  //~9501I~//~var0I~
//    //*FinalLast                                                   //~9501I~//~var0R~
//        cbClosableRon=new UCheckBox(PView,R.id.cbClosableRon);     //~9501I~//~var0R~
//        cbClosablePending=new UCheckBox(PView,R.id.cbClosablePending);//~9501I~//~var0R~
//        cbClosableNotTop=new UCheckBox(PView,R.id.cbClosableNotTop);//~9501I~//~var0R~
//        rgFinalLastDealerNotPending=new URadioGroup(PView,R.id.rgFinalLastDealerNotPending,0,rbIDFinalLastDealerNotPending);//~9501I~//~var0R~
//        rgFinalLastAllMinus=new URadioGroup(PView,R.id.rgFinalLastAllMinus,0,rbIDFinalLastDealerAllMinus);//~9501I~//~var0R~
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
//  //*PendingCont                                                 //~9709I~//~var0R~
//  	cbPendingCont=new UCheckBox(PView,R.id.cbPendingCont);     //~9709I~//~var0R~
		setupLayout_SummOther(PView);                                    //~var0I~
    }                                                              //~9403I~
	//****************************************************************//~var0I~
	//*foy Yaku                                                    //~var0I~
	//****************************************************************//~var0I~
    protected void setupLayout_SummOther(View PView)               //~var0I~
    {                                                              //~var0I~
        cbKuitan         =          new UCheckBox(PView,R.id.cbKuitan);//~var0I~
        cbShowAnywayBtn  =          new UCheckBox(PView,R.id.cbShowAnywayBtn);//~var0I~
        cbDoublePillow   =          new UCheckBox(PView,R.id.cbDoublePillow);//~var0I~
        cbPinfuTaken     =          new UCheckBox(PView,R.id.cbPinfuTaken);//~var0I~
    //*keiten                                                      //~var0I~
        cbPendingRankNo   =         new UCheckBox(PView,R.id.cbPendingRankNo);//~var0I~
    	setCBListener(cbPendingRankNo,R.id.cbPendingRankNo);       //~var0I~
        cbPendingRank0OK    =       new UCheckBox(PView,R.id.cbPendingRank0OK);//~var0I~
    	rgPendingRank2=new URadioGroup(PView,R.id.rgPendingRank2,0,rbIDPendingRank2);//~var0I~
    //*7Pair                                                       //~var0I~
        rgYaku7Pair=new URadioGroup(PView,R.id.rgYaku7Pair,0,rbsYaku7Pair);//~var0I~
    	cbYaku7Pair4Pair=new UCheckBox(PView,R.id.cbYaku7Pair4Pair);//~var0I~
    //*YakuFix                                                     //~var0I~
        rgYakuFix=new URadioGroup(PView,R.id.rgYakuFix,0,rbsYakuFix);//~var0I~
        rgYakuFixMultiwaitTake=new URadioGroup(PView,R.id.rgYakuFixMultiwaitTake,0,rbsYakuFixMultiwaitTake);//~var0I~
    //*Reach                                                       //~var0I~
        cbOpenReach=new UCheckBox(PView,R.id.cbOpenReach);         //~var0I~
        cbOneShot=new UCheckBox(PView,R.id.cbOneShot);             //~var0I~
    	cbAnkanAfterReach=new UCheckBox(PView,R.id.cbAnkanAfterReach);//~var0I~
        rgFuritenReach=new URadioGroup(PView,R.id.rgFuritenReach,0,rbsFuritenReach);//~var0I~
        rgOpenReach=new URadioGroup(PView,R.id.rgOpenReach,0/*listenerParm*/,rbsOpenReach);//~var0I~
        cbOpenReachRobot=new UCheckBox(PView,R.id.cbOpenReachRobot);//~var0I~
    //*YakuFix2                                                    //~var0I~
        rgYakuFix2=new URadioGroup(PView,R.id.rgYakuFix2,0,rbsYakuFix2);//~var0I~
//    //*Yakuman2                                                    //~var0I~//~var1R~
//        cbYakuMan2_4Anko=new UCheckBox(PView,R.id.cbYakuMangan8_4AnkoTanki);//~var0I~//~var1R~
//        cbYakuMan2_Kokusi13=new UCheckBox(PView,R.id.cbYakuMangan8_Kokusi13);//~var0I~//~var1R~
//        cbYakuMan2_9Ren9=new UCheckBox(PView,R.id.cbYakuMangan8_9Ren9);//~var0I~//~var1R~
//        cbYakuMan2_4Wind=new UCheckBox(PView,R.id.cbYakuMangan8_4Wind);//~var0I~//~var1R~
//    //*Yakuman                                                     //~var0I~//~var1R~
//        cbAllGreenNoBlue=new UCheckBox(PView,R.id.cbYakuman_AllGreenNoBlue);//~var0I~//~var1R~
//        cb9RenPinSou=new UCheckBox(PView,R.id.cbYakuman_9RenPinSou);//~var0I~//~var1R~
//        cbNoPair13=new UCheckBox(PView,R.id.cbYakuman_NoPair13);   //~var0I~//~var1R~
//        cbNoPair14=new UCheckBox(PView,R.id.cbYakuman_NoPair14);   //~var0I~//~var1R~
//        cbBigRing=new UCheckBox(PView,R.id.cbYakuman_BigRing);     //~var0I~//~var1R~
//        cbRank13=new UCheckBox(PView,R.id.cbYakuman_Rank13);       //~var0I~//~var1R~
//        cbKokusiAnkanRon=new UCheckBox(PView,R.id.cbYakuman_KokusiAnkanRon);//~var0I~//~var1R~
//        cb5thKan=new UCheckBox(PView,R.id.cbYakuman_5thKan);       //~var0I~//~var1R~
//    //*Renho                                                       //~var0I~//~var1R~
//        spnRenhoRank=new USpinner(PView,R.id.spnRenhoRank);        //~var0I~//~var1R~
//        spnRenhoRank.setArray(rankRenho);                          //~var0I~//~var1R~
//        cbRenhoMix=new UCheckBox(PView,R.id.cbYakuman_RenhoMix);   //~var0I~//~var1R~
//    //*drawnMangan                                                 //~var0I~//~var1R~
//        rgDrawnMangan=new URadioGroup(PView,R.id.rgDrawnMangan,0,rbIDDrawnMangan);//~var0I~//~var1R~
//        spnDrawnManganRank =new USpinner(PView,R.id.spnDrawnManganRank);//~var0I~//~var1R~
//        spnDrawnManganRank.setArray(rankDrawnMangan);              //~var0I~//~var1R~
//*Operation                                                       //~var2I~
        cb2TouchCancelableRon=new UCheckBox(PView,R.id.cb2TouchCancelableRon);    //2touchMode//~var2I~
    	cbYakuFix1=new UCheckBox(PView,R.id.cbYakuFix1);           //~var2I~
        cbChkMultiWait=new UCheckBox(PView,R.id.cbChkMultiWait);    //2touchMode//~var2I~
        cb2CheckRonValue=new UCheckBox(PView,R.id.cbCheckRonValue);    //2touchMode//~var2I~
        cb2CheckReach=new UCheckBox(PView,R.id.cbCheckReach);    //2touchMode//~var2I~
    }                                                              //~var0I~
	//*****************                                                //~1613I~//~v@@@I~
    @Override //RuleSetting                                        //~var0I~
    protected void setInitialValue()                                 //~v@@@I~
    {                                                              //~1613I~//~v@@@M~
        if (Dump.Y) Dump.println("RuleSettingSumm.setInitialvalue swReceived="+swReceived);             //~v@@@I~//~9827R~//~vaehR~//~var0R~
        propCmt=PROP_NAME;                                     //~v@@@I~//~9405R~
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
//          chkUpdateOperYaku();                                   //~vakQI~//~var0R~
            chkUpdateDetail();                                     //~var0I~
            showUpdate(swAnyUpdate);                               //~vakWI~
        }                                                          //~vakQI~
    }                                                              //~v@@@I~
	//*****************                                            //~v@@@I~
    private void setupDialog()                                    //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("RuleSettingSumm.setupDialog curProp="+curProp);//~var0I~
        properties2Dialog(curProp);                      //~v@@@I~//~9403R~//~9404R~
    }                                                              //~1613I~//~v@@@M~
    //*******************************************************      //~var0I~
    //*from RuleSettingDetail at dismiss if swOK && swChanged      //~var0I~
    //*******************************************************      //~var0I~
    public void repaintSumm()                                          //~var0I~
    {                                                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.repaint curProp="+curProp);//~var0I~
	    properties2Dialog(curProp);                                //~var0I~
        swChanged=true;                                            //~var0I~
    }                                                              //~var0I~
    //*******************************************************      //~v@@@I~
    //*setupdialog from property                                   //~v@@@I~
    //*******************************************************      //~v@@@I~
    @Override //SettingDlg                                         //~v@@@I~
    protected void properties2Dialog(Prop Pprop)                     //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("RuleSettingSumm.properties2Dialog");                   //~v@@@I~//~9412R~//~vaehR~//~var0R~
        tvSyncDate.setText(Pprop.getParameter(getKeyRS(RSID_SYNCDATE_FORMATTED),Utils.getStr(R.string.RuleNotInitialized)));//~vam2I~
        tvAppVersion.setText(Pprop.getParameter(getKeyRS(RSID_APPVERSION_MIN),"Unknown AppVer"));//~vaqaR~
        etIDName.setText(Pprop.getParameter(getKeyRS(RSID_IDNAME),Utils.getStr(R.string.SampleRuleID)),true/*swLostFocus*/);//~vam2I~
        if (swFixed)                                               //~9405I~
        	etIDName.setEnabled(false);                            //~9903I~
    //*GameSet                                                     //~var0M~
        spnGameSetType.select(Pprop.getParameter(getKeyRS(RSID_GAMESET_TYPE),0),swFixed);//~9501I~//~var0M~
    //*InitialScore                                                //~var0I~
        spnInitialScore.select(Pprop.getParameter(getKeyRS(RSID_INITSCORE),0),swFixed);//~9404I~//~9405R~
//      etInitialScoreTestE.setText(Integer.toString(Pprop.getParameter(getKeyRS(RSID_INITSCORE_TESTE),25000)));//~9425R~//~var0R~
//      etInitialScoreTestS.setText(Integer.toString(Pprop.getParameter(getKeyRS(RSID_INITSCORE_TESTS),25000)));//~9425I~//~var0R~
//      etInitialScoreTestW.setText(Integer.toString(Pprop.getParameter(getKeyRS(RSID_INITSCORE_TESTW),25000)));//~9425I~//~var0R~
//      etInitialScoreTestN.setText(Integer.toString(Pprop.getParameter(getKeyRS(RSID_INITSCORE_TESTN),25000)));//~9425I~//~var0R~
        spnDupPoint.select(Pprop.getParameter(getKeyRS(RSID_POINT_DUP),0),swFixed);//~9512I~
    //*minusPay                                                    //~9404I~
        sbMinusPrize.setVal(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP_POINT),DEFAULT_MINUSPRIZE_INIT),swFixed);//~9408I~
        cbMinusStop.setStateInt(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP),0),swFixed);//~9404R~//~9405R~
        cbMinus0.setStateInt(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP_0),0),swFixed);//~9404R~//~9405R~
        rgMinusPay.setCheckedID(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP_PAYTYPE),0),swFixed);//~9404I~//~9405R~
        rgMinusStopByErr.setCheckedID(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP_BYERR),0),swFixed);//~9414I~
    //*orderPrize                                                  //~9407I~
        spnOrderPrize.select(Pprop.getParameter(getKeyRS(RSID_ORDERPRIZE),DEFAULT_ORDERPRIZE),swFixed);//~9407I~//~9819R~
        rgOrderSamePoint.setCheckedID(Pprop.getParameter(getKeyRS(RSID_ORDERPRIZE_SAMEPOINT),0),swFixed);//~9407I~
    //*multiRon                                                    //~9408I~
        rgMultiRon.setCheckedID(Pprop.getParameter(getKeyRS(RSID_MULTIRON),0/*defaultIdx*/),swFixed);//~9408I~//~9409R~//~9513R~
//  //*drawnHW                                                     //~9425I~//~var0R~
//      cbMultiRon3Drawn.setStateInt(Pprop.getParameter(getKeyRS(RSID_MULTIRON3DRAWN),1/*default true*/),swFixed);//~9409I~//~9425I~//~var0R~
//      cbDrawnHW99.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW99),1/*default true*/),swFixed);//~9425R~//~var0R~
//      cbDrawnHW4W.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4W),1/*default true*/),swFixed);//~9425R~//~var0R~
//      cbDrawnHW4K.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4K),1/*default true*/),swFixed);//~9425R~//~var0R~
//      cbDrawnHW4R.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4R),1/*default true*/),swFixed);//~9425R~//~var0R~
//      cbMultiRon3DrawnCont.setStateInt(Pprop.getParameter(getKeyRS(RSID_MULTIRON3DRAWNC),0/*default F*/),swFixed);//~9B13I~//~var0R~
//      cbDrawnHW99Cont.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW99C),1/*default T*/),swFixed);//~9B13I~//~var0R~
//      cbDrawnHW4WCont.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4WC),1/*default T*/),swFixed);//~9B13I~//~var0R~
//      cbDrawnHW4KCont.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4KC),0/*default F*/),swFixed);//~9B13I~//~var0R~
//      cbDrawnHW4RCont.setStateInt(Pprop.getParameter(getKeyRS(RSID_DRAWN_HW4RC),0/*default F*/),swFixed);//~9B13I~//~var0R~
    //*drawnMangan                                                 //~9413I~
    //*drawnHW                                                     //~9425I~
    //*scoreToPoint                                                //~9416I~//~var0R~
        bgScoreToPoint.setChecked(Pprop.getParameter(getKeyRS(RSID_SCORE_TO_POINT),0),swFixed);//~9417R~//~var0R~
//  //*robot                                                       //~9429I~//~var0R~
//      cbAllowRobot.setStateInt(Pprop.getParameter(getKeyRS(RSID_ALLOW_ROBOT),DEFAULT_ALLOW_ROBOT_ALL/*default:true*/),swFixed);//~va66I~//~var0R~
//      cbThinkRobot.setStateInt(Pprop.getParameter(getKeyRS(RSID_THINK_ROBOT),DEFAULT_THINK_ROBOT/*default:true*/),swFixed);//~va60I~//~va66R~//~var0R~
//      cbMinusRobot.setStateInt(Pprop.getParameter(getKeyRS(RSID_MINUSSTOP_ROBOT),0),swFixed);//~9429I~//~var0R~
//      rgRobotPay.setCheckedID(Pprop.getParameter(getKeyRS(RSID_ROBOT_PAY),0),swFixed);//~9429I~//~var0R~
    //*bird                                                        //~9430I~
        rgBirdPayType.setCheckedID(Pprop.getParameter(getKeyRS(RSID_BIRD_PAYTYPE),0),swFixed);//~9602I~
        cbBird.setStateInt(Pprop.getParameter(getKeyRS(RSID_BIRD),0),swFixed);//~9430I~
        sbBird.setVal(Pprop.getParameter(getKeyRS(RSID_BIRD_PAY),DEFAULT_BIRD),swFixed);//~9430I~
//    //*FinalLast                                                   //~9501I~//~var0R~
//        cbClosableRon.setStateInt(Pprop.getParameter(getKeyRS(RSID_CLOSABLE_RON),0),swFixed);//~9501I~//~var0R~
//        cbClosablePending.setStateInt(Pprop.getParameter(getKeyRS(RSID_CLOSABLE_PENDING),0),swFixed);//~9501I~//~var0R~
//        cbClosableNotTop.setStateInt(Pprop.getParameter(getKeyRS(RSID_CLOSABLE_NOTTOP),0),swFixed);//~9501I~//~var0R~
//        rgFinalLastDealerNotPending.setCheckedID(Pprop.getParameter(getKeyRS(RSID_FL_NOTPENDING),0),swFixed);//~9501I~//~var0R~
//        rgFinalLastAllMinus.setCheckedID(Pprop.getParameter(getKeyRS(RSID_FL_ALLMINUS),0),swFixed);//~9501I~//~var0R~
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
//  //*PendingCont                                                 //~9709I~//~var0R~
//  	cbPendingCont.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_CONT),1),swFixed);//~9709I~//~var0R~
		properties2Dialog_SummOther(Pprop);                        //~var0I~
    }                                                              //~v@@@I~
    //*******************************************************      //~var0I~
    protected void properties2Dialog_SummOther(Prop Pprop)         //~var0I~
    {                                                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.properties2Dialog_SummOther");//~var0I~
        cbKuitan.setStateInt(Pprop.getParameter(getKeyRS(RSID_KUITAN),0),swFixed);//~var0I~
        cbShowAnywayBtn.setStateInt(Pprop.getParameter(getKeyRS(RSID_SHOW_ANYWAY_BTN),0),swFixed);//~var0I~
        cbDoublePillow.setStateInt(Pprop.getParameter(getKeyRS(RSID_DOUBLE_PILLOW),0),swFixed);//~var0I~
        cbPinfuTaken.setStateInt(Pprop.getParameter(getKeyRS(RSID_PINFUTAKEN),0),swFixed);//~var0I~
    //keiten                                                       //~var0I~
        cbPendingRankNo.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_RANKNO),0),swFixed);//~var0I~
        cbPendingRank0OK.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_RANK0),1),swFixed);//~var0I~
        rgPendingRank2.setCheckedID(Pprop.getParameter(getKeyRS(RSID_PENDING_RANK2),PENDING_RANK2_DEFAULT),swFixed);//~var0I~
    //*7Pair                                                       //~var0I~
        rgYaku7Pair.setCheckedID(Pprop.getParameter(getKeyRS(RSID_7PAIR),YAKU7PAIR_DEFAULT),swFixed);//~var0I~
    	cbYaku7Pair4Pair.setStateInt(Pprop.getParameter(getKeyRS(RSID_7PAIR4PAIR),0),swFixed);//~var0I~
    //*YakuFix                                                     //~var0I~
        rgYakuFix.setCheckedID(Pprop.getParameter(getKeyRS(RSID_YAKUFIX),YAKUFIX_DEFAULT),swFixed);//~var0I~
        rgYakuFixMultiwaitTake.setCheckedID(Pprop.getParameter(getKeyRS(RSID_YAKUFIX_TAKE),YAKUFIX_TAKE_DEFAULT),swFixed);//~var0I~
    //*Reach                                                       //~var0I~
        cbOpenReach.setStateInt(Pprop.getParameter(getKeyRS(RSID_REACH_OPEN),0/*defaultIdx*/),swFixed);//~var0I~
        cbOneShot.setStateInt(Pprop.getParameter(getKeyRS(RSID_ONESHOT),1/*defaultIdx*/),swFixed);//~var0I~
        cbAnkanAfterReach.setStateInt(Pprop.getParameter(getKeyRS(RSID_ANKAN_AFTER_REACH),1/*default ON*/),swFixed);//~var0I~
        rgFuritenReach.setCheckedID(Pprop.getParameter(getKeyRS(RSID_REACH_FURITEN),FURITEN_REACH_DEFAULT),swFixed);//~var0I~
        rgOpenReach.setCheckedID(Pprop.getParameter(getKeyRS(RSID_OPENREACH_PAY),OPENREACH_DEFAULT),swFixed);//~var0I~
        cbOpenReachRobot.setStateInt(Pprop.getParameter(getKeyRS(RSID_OPENREACH_ROBOT_CBNO),1/*default ON*/),swFixed);//~var0I~
    //*YakuFix2                                                    //~var0I~
        rgYakuFix2.setCheckedID(Pprop.getParameter(getKeyRS(RSID_YAKUFIX2),YAKUFIX2_DEFAULT),swFixed);//~var0I~
//    //*Yakuman2                                                    //~var0I~//~var1R~
//        cbYakuMan2_4Anko.setStateInt(Pprop.getParameter(getKeyRS(RSID_4ANKO1),1),swFixed);//~var0I~//~var1R~
//        cbYakuMan2_Kokusi13.setStateInt(Pprop.getParameter(getKeyRS(RSID_KOKUSI13),1),swFixed);//~var0I~//~var1R~
//        cbYakuMan2_9Ren9.setStateInt(Pprop.getParameter(getKeyRS(RSID_9REN9),1),swFixed);//~var0I~//~var1R~
//        cbYakuMan2_4Wind.setStateInt(Pprop.getParameter(getKeyRS(RSID_4WIND),1),swFixed);//~var0I~//~var1R~
//    //*Yakuman                                                     //~var0I~//~var1R~
//        cbAllGreenNoBlue.setStateInt(Pprop.getParameter(getKeyRS(RSID_ALLGREEN_NOBLUE),1),swFixed);//~var0I~//~var1R~
//        cb9RenPinSou.setStateInt(Pprop.getParameter(getKeyRS(RSID_9RENPINSOU),1),swFixed);//~var0I~//~var1R~
//        cbNoPair13.setStateInt(Pprop.getParameter(getKeyRS(RSID_NOPAIR13),1),swFixed);//~var0I~//~var1R~
//        cbNoPair14.setStateInt(Pprop.getParameter(getKeyRS(RSID_NOPAIR14),1),swFixed);//~var0I~//~var1R~
//        cbBigRing.setStateInt(Pprop.getParameter(getKeyRS(RSID_BIGRING),1),swFixed);//~var0I~//~var1R~
//        cbRank13.setStateInt(Pprop.getParameter(getKeyRS(RSID_RANK13),1),swFixed);//~var0I~//~var1R~
//        cbKokusiAnkanRon.setStateInt(Pprop.getParameter(getKeyRS(RSID_KOKUSI_ANKANRON),1),swFixed);//~var0I~//~var1R~
//        cb5thKan.setStateInt(Pprop.getParameter(getKeyRS(RSID_5THKAN),0),swFixed);//~var0I~//~var1R~
//    //*Renho                                                       //~var0I~//~var1R~
//        cbRenhoMix.setStateInt(Pprop.getParameter(getKeyRS(RSID_RENHOMIX),0),swFixed);//~var0I~//~var1R~
//        spnRenhoRank.select(Pprop.getParameter(getKeyRS(RSID_RENHORANK),RENHORANK_DEFAULT),swFixed);//~var0I~//~var1R~
//    //*drawnMangan                                                 //~var0I~//~var1R~
//        rgDrawnMangan.setCheckedID(Pprop.getParameter(getKeyRS(RSID_DRAWN_MANGAN_TYPE),DRAWN_MANGAN_DEFAULT),swFixed);//~var0I~//~var1R~
//        spnDrawnManganRank.select(Pprop.getParameter(getKeyRS(RSID_DRAWN_MANGAN_RANK),0),swFixed);//~var0I~//~var1R~
      //*Operation                                                 //~var2I~
        cb2TouchCancelableRon.setStateInt(Pprop.getParameter(getKeyRS(RSID_2TOUCH_CANCELABLE_RON),0/*default false*/),swFixed);//~var2I~
    	cbYakuFix1.setStateInt(Pprop.getParameter(getKeyRS(RSID_YAKUFIX1),DEFAULT_FIX1),swFixed);//~var2I~
        cbChkMultiWait.setStateInt(Pprop.getParameter(getKeyRS(RSID_CHK_MULTIWAIT),DEFAULT_CHK_MULTIWAIT/*default false*/),swFixed);//~var2I~
        cb2CheckRonValue.setStateInt(Pprop.getParameter(getKeyRS(RSID_CHECK_RONVALUE),DEFAULT_RONVALUE/*default false*/),swFixed);//~var2I~
        cb2CheckReach.setStateInt(Pprop.getParameter(getKeyRS(RSID_CHECK_REACH),DEFAULT_CHK_REACH/*default false*/),swFixed);//~var2I~
    }                                                              //~var0I~
    //*******************************************************      //~vakQI~
    private void chkUpdateDetail()                               //~vakQI~//~var0R~
    {                                                              //~vakQI~
	    if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdateDetail curProp="+curProp);//~var0R~
		boolean swUpdated=chkUpdate_DetailExceptSumm();  //chk update item on Detail except on Summ//~var0R~
        swUpdated|=chkUpdateOperYaku_ExceptSumm();                //~var0I~
        if (swUpdated)                                             //~var0R~
			Utils.setTintBG(btnDetail,COLOR_BG_CHANGED_RULE);      //~var0I~
    }                                                              //~vakQI~
    //*******************************************************      //~var0I~
    private boolean chkUpdateOperYaku_ExceptSumm()                 //~var0I~
    {                                                              //~var0I~
	    if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdateOperYaku_ExceptSumm");//~var0I~
//		boolean swUpdated=RuleSettingOperation.chkUpdateCheckOnly(this);//~var0I~//~var2R~
  		boolean swUpdated=RuleSettingOperation.chkUpdateCheckOnly_ExceptSumm(this);//~var2I~
    	swUpdated|=RuleSettingYaku.chkUpdateCheckOnly_ExceptSumm(this);//~var0I~
	    if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdateOperYaku_ExceptSumm rc="+swUpdated);//~var0I~
        return swUpdated;                                          //~var0I~
    }                                                              //~var0I~
    //*******************************************************      //~vakQI~
    //*change BG color of option changed by received parm          //~vakQI~
    //*******************************************************      //~vakQI~
    protected void chkUpdate()                                       //~vakQR~
    {                                                              //~vakQI~
		if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdate curProp="+curProp.toString());//~vaehR~//~vakQR~//~var0R~
	    if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdate AG.ruleProp="+AG.ruleProp.toString());//~vaehR~//~vakQR~//~var0R~
		chkUpdate_Summ();                                          //~var0R~
		chkUpdate_SummOther();                                     //~var0I~
    }                                                              //~vakQI~
    //*******************************************************      //~var0I~
    private void chkUpdate_Summ()                                  //~var0I~
    {                                                              //~var0I~
		if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdateSumm curProp="+curProp.toString());//~var0I~
	    if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdateSumm AG.ruleProp="+AG.ruleProp.toString());//~var0I~
        setBGUpdated(tvSyncDate,isChangedText(RSID_SYNCDATE_FORMATTED));//~var0I~
        swAnyUpdate=false;	//ignore syncdate                      //~var0I~
        setBGUpdated(etIDName,isChangedText(RSID_IDNAME));         //~var0I~
    //*gemaset_type                                                //~var0I~
        setBGUpdated(spnGameSetType,isChanged(RSID_GAMESET_TYPE)); //~var0I~
//    //*FinalLast                                                 //~var0I~
//        setBGUpdated(cbClosableRon,isChanged(RSID_CLOSABLE_RON));//~var0I~
//        setBGUpdated(cbClosablePending,isChanged(RSID_CLOSABLE_PENDING));//~var0I~
//        setBGUpdated(cbClosableNotTop,isChanged(RSID_CLOSABLE_NOTTOP));//~var0I~
//        setBGUpdated(rgFinalLastDealerNotPending,isChanged(RSID_FL_NOTPENDING));//~var0I~
//        setBGUpdated(rgFinalLastAllMinus,isChanged(RSID_FL_ALLMINUS));//~var0I~
    //*initialScore                                                //~var0I~
        setBGUpdated(spnInitialScore,isChanged(RSID_INITSCORE));   //~var0I~
//      setBGUpdated(etInitialScoreTestE,isChanged(RSID_INITSCORE_TESTE));//~var0I~
//      setBGUpdated(etInitialScoreTestS,isChanged(RSID_INITSCORE_TESTS));//~var0I~
//      setBGUpdated(etInitialScoreTestW,isChanged(RSID_INITSCORE_TESTW));//~var0I~
//      setBGUpdated(etInitialScoreTestN,isChanged(RSID_INITSCORE_TESTN));//~var0I~
        setBGUpdated(spnDupPoint,isChanged(RSID_POINT_DUP));       //~var0I~
    //*orderPrize                                                  //~var0I~
        setBGUpdated(spnOrderPrize,isChanged(RSID_ORDERPRIZE));    //~var0I~
        setBGUpdated(rgOrderSamePoint,isChanged(RSID_ORDERPRIZE_SAMEPOINT));//~var0I~
    //*RankMUp                                                     //~var0I~
    	setBGUpdated(cbRankMUp,isChanged(RSID_RANKMUP));           //~var0I~
    //*scoreToPoint                                                //~var0I~
//      setBGUpdated(bgScoreToPoint,RSID_SCORE_TO_POINT);          //~var0I~//~varbR~
        setBGUpdated(bgScoreToPoint,RSID_SCORE_TO_POINT,0/*default*/);//~varbI~
    //*Dora                                                        //~var0I~
        setBGUpdated(rgDora,isChanged(RSID_DORA));                 //~var0I~
        setBGUpdated(rgDoraHidden,isChanged(RSID_DORA_HIDDEN));    //~var0I~
        setBGUpdated(rgKanDora,isChanged(RSID_KANDORA));           //~var0I~
        setBGUpdated(rgKanDoraHidden,isChanged(RSID_KANDORA_HIDDEN));//~var0I~
        setBGUpdated(rgKanDoraOpen,isChanged(RSID_KANDORA_OPEN));  //~var0I~
    	setBGUpdated(cbUseRed5,isChanged(RSID_USERED5));           //~var0I~
    //*SpritPos                                                    //~var0I~
    	setBGUpdated(cbSpritPos,isChanged(RSID_SPRITPOS));         //~var0I~
    //*minusPay                                                    //~var0I~
        setBGUpdated(cbMinusStop,isChanged(RSID_MINUSSTOP));       //~var0I~
        setBGUpdated(sbMinusPrize,isChanged(RSID_MINUSSTOP_POINT));//~var0I~
        setBGUpdated(cbMinus0,isChanged(RSID_MINUSSTOP_0));        //~var0I~
        setBGUpdated(rgMinusPay,isChanged(RSID_MINUSSTOP_PAYTYPE));//~var0I~
        setBGUpdated(rgMinusStopByErr,isChanged(RSID_MINUSSTOP_BYERR));//~var0I~
    //*bird                                                        //~var0I~
        setBGUpdated(rgBirdPayType,isChanged(RSID_BIRD_PAYTYPE));  //~var0I~
        setBGUpdated(cbBird,isChanged(RSID_BIRD));                 //~var0I~
        setBGUpdated(sbBird,isChanged(RSID_BIRD_PAY));             //~var0I~
    //*EatChange                                                   //~var0I~
        setBGUpdated(rgEatChange,isChanged(RSID_EATCHANGE));       //~var0I~
//    //*PendingCont                                               //~var0I~
//        setBGUpdated(cbPendingCont,isChanged(RSID_PENDING_CONT));//~var0I~
//    //*drawnHW                                                   //~var0I~
//        setBGUpdated(cbMultiRon3Drawn,isChanged(RSID_MULTIRON3DRAWN));//~var0I~
//        setBGUpdated(cbDrawnHW99,isChanged(RSID_DRAWN_HW99));    //~var0I~
//        setBGUpdated(cbDrawnHW4W,isChanged(RSID_DRAWN_HW4W));    //~var0I~
//        setBGUpdated(cbDrawnHW4K,isChanged(RSID_DRAWN_HW4K));    //~var0I~
//        setBGUpdated(cbDrawnHW4R,isChanged(RSID_DRAWN_HW4R));    //~var0I~
//        setBGUpdated(cbMultiRon3DrawnCont,isChanged(RSID_MULTIRON3DRAWNC));//~var0I~
//        setBGUpdated(cbDrawnHW99Cont,isChanged(RSID_DRAWN_HW99C));//~var0I~
//        setBGUpdated(cbDrawnHW4WCont,isChanged(RSID_DRAWN_HW4WC));//~var0I~
//        setBGUpdated(cbDrawnHW4KCont,isChanged(RSID_DRAWN_HW4KC));//~var0I~
//        setBGUpdated(cbDrawnHW4RCont,isChanged(RSID_DRAWN_HW4RC));//~var0I~
    //*multiRon                                                    //~var0I~
        setBGUpdated(rgMultiRon,isChanged(RSID_MULTIRON));         //~var0I~
//    //*robot                                                     //~var0I~
//        setBGUpdated(cbAllowRobot,isChanged(RSID_ALLOW_ROBOT));  //~var0I~
//        setBGUpdated(cbThinkRobot,isChanged(RSID_THINK_ROBOT));  //~var0I~
//        setBGUpdated(cbMinusRobot,isChanged(RSID_MINUSSTOP_ROBOT));//~var0I~
//        setBGUpdated(rgRobotPay,isChanged(RSID_ROBOT_PAY));      //~var0I~
    }                                                              //~var0I~
    //*******************************************************      //~var0I~
    private boolean chkUpdate_DetailExceptSumm()                   //~var0I~
    {                                                              //~var0I~
		if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdate_DetailExceptSumm curProp="+curProp.toString());//~var0I~
	    if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdate_DetailExceptSumm AG.ruleProp="+AG.ruleProp.toString());//~var0I~
        boolean rc=false;                                          //~var0I~
        for (;;)                                                   //~var0I~
        {                                                          //~var0I~
    	//*FinalLast                                               //~var0I~
            if (isChanged(RSID_CLOSABLE_RON))                  {rc=true; break;}//~var0I~
            if (isChanged(RSID_CLOSABLE_PENDING))              {rc=true; break;}//~var0I~
            if (isChanged(RSID_CLOSABLE_NOTTOP))               {rc=true; break;}//~var0I~
            if (isChanged(RSID_FL_NOTPENDING))                 {rc=true; break;}//~var0I~
            if (isChanged(RSID_FL_ALLMINUS))                   {rc=true; break;}//~var0I~
        //*PendingCont                                             //~var0I~
            if (isChanged(RSID_PENDING_CONT))                  {rc=true; break;}//~var0I~
		//*drawnHW                                                 //~var0I~
            if (isChanged(RSID_MULTIRON3DRAWN))                {rc=true; break;}//~var0I~
            if (isChanged(RSID_DRAWN_HW99))                    {rc=true; break;}//~var0I~
            if (isChanged(RSID_DRAWN_HW4W))                    {rc=true; break;}//~var0I~
            if (isChanged(RSID_DRAWN_HW4K))                    {rc=true; break;}//~var0I~
            if (isChanged(RSID_DRAWN_HW4R))                    {rc=true; break;}//~var0I~
            if (isChanged(RSID_MULTIRON3DRAWNC))               {rc=true; break;}//~var0I~
            if (isChanged(RSID_DRAWN_HW99C))                   {rc=true; break;}//~var0I~
            if (isChanged(RSID_DRAWN_HW4WC))                   {rc=true; break;}//~var0I~
            if (isChanged(RSID_DRAWN_HW4KC))                   {rc=true; break;}//~var0I~
            if (isChanged(RSID_DRAWN_HW4RC))                   {rc=true; break;}//~var0I~
		//*robot                                                   //~var0I~
            if (isChanged(RSID_ALLOW_ROBOT))                   {rc=true; break;}//~var0I~
            if (isChanged(RSID_THINK_ROBOT))                   {rc=true; break;}//~var0I~
            if (isChanged(RSID_MINUSSTOP_ROBOT))               {rc=true; break;}//~var0I~
            if (isChanged(RSID_ROBOT_PAY))                     {rc=true; break;}//~var0I~
            break;                                                 //~var0I~
        }                                                          //~var0I~
	    if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdate_DetailExceptSumm rc="+rc);//~var0I~
        return rc;                                                 //~var0I~
    }                                                              //~var0I~
    //*******************************************************      //~var0I~
    private void chkUpdate_SummOther()                             //~var0R~
    {                                                              //~var0I~
		if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdateSummOther curProp="+curProp.toString());//~var0I~
	    if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdateSummOther AG.ruleProp="+AG.ruleProp.toString());//~var0R~
        RSD.setBGUpdated(cbKuitan,RSD.isChanged(RSID_KUITAN));     //~var0I~
        RSD.setBGUpdated(cbShowAnywayBtn,RSD.isChanged(RSID_SHOW_ANYWAY_BTN));//~var0I~
        RSD.setBGUpdated(cbDoublePillow,RSD.isChanged(RSID_DOUBLE_PILLOW));//~var0I~
        RSD.setBGUpdated(cbPinfuTaken,RSD.isChanged(RSID_PINFUTAKEN));//~var0I~
    //*keiten                                                      //~var0I~
        RSD.setBGUpdated(cbPendingRankNo,RSD.isChanged(RSID_PENDING_RANKNO));//~var0I~
        RSD.setBGUpdated(cbPendingRank0OK,RSD.isChanged(RSID_PENDING_RANK0));//~var0I~
        RSD.setBGUpdated(rgPendingRank2,RSD.isChanged(RSID_PENDING_RANK2));//~var0I~
    //*7Pair                                                       //~var0I~
        RSD.setBGUpdated(rgYaku7Pair,RSD.isChanged(RSID_7PAIR));   //~var0I~
    	RSD.setBGUpdated(cbYaku7Pair4Pair,RSD.isChanged(RSID_7PAIR4PAIR));//~var0I~
    //*YakuFix                                                     //~var0I~
        RSD.setBGUpdated(rgYakuFix,RSD.isChanged(RSID_YAKUFIX));   //~var0I~
        RSD.setBGUpdated(rgYakuFixMultiwaitTake,RSD.isChanged(RSID_YAKUFIX_TAKE));//~var0I~
    //*Reach                                                       //~var0I~
        RSD.setBGUpdated(cbOpenReach,RSD.isChanged(RSID_REACH_OPEN));//~var0I~
        RSD.setBGUpdated(cbOneShot,RSD.isChanged(RSID_ONESHOT));   //~var0I~
        RSD.setBGUpdated(cbAnkanAfterReach,RSD.isChanged(RSID_ANKAN_AFTER_REACH));//~var0I~
        RSD.setBGUpdated(rgFuritenReach,RSD.isChanged(RSID_REACH_FURITEN));//~var0I~
        RSD.setBGUpdated(rgOpenReach,RSD.isChanged(RSID_OPENREACH_PAY));//~var0I~
        RSD.setBGUpdated(cbOpenReachRobot,RSD.isChanged(RSID_OPENREACH_ROBOT_CBNO));//~var0I~
    //*YakuFix2                                                    //~var0I~
        RSD.setBGUpdated(rgYakuFix2,RSD.isChanged(RSID_YAKUFIX2)); //~var0I~
//    //*Yakuman2                                                    //~var0I~//~var1R~
//        RSD.setBGUpdated(cbYakuMan2_4Anko,RSD.isChanged(RSID_4ANKO1));//~var0I~//~var1R~
//        RSD.setBGUpdated(cbYakuMan2_Kokusi13,RSD.isChanged(RSID_KOKUSI13));//~var0I~//~var1R~
//        RSD.setBGUpdated(cbYakuMan2_9Ren9,RSD.isChanged(RSID_9REN9));//~var0I~//~var1R~
//        RSD.setBGUpdated(cbYakuMan2_4Wind,RSD.isChanged(RSID_4WIND));//~var0I~//~var1R~
//    //*Yakuman                                                     //~var0I~//~var1R~
//        RSD.setBGUpdated(cbAllGreenNoBlue,RSD.isChanged(RSID_ALLGREEN_NOBLUE));//~var0I~//~var1R~
//        RSD.setBGUpdated(cb9RenPinSou,RSD.isChanged(RSID_9RENPINSOU));//~var0I~//~var1R~
//        RSD.setBGUpdated(cbNoPair13,RSD.isChanged(RSID_NOPAIR13)); //~var0I~//~var1R~
//        RSD.setBGUpdated(cbNoPair14,RSD.isChanged(RSID_NOPAIR14)); //~var0I~//~var1R~
//        RSD.setBGUpdated(cbBigRing,RSD.isChanged(RSID_BIGRING));   //~var0I~//~var1R~
//        RSD.setBGUpdated(cbRank13,RSD.isChanged(RSID_RANK13));     //~var0I~//~var1R~
//        RSD.setBGUpdated(cbKokusiAnkanRon,RSD.isChanged(RSID_KOKUSI_ANKANRON));//~var0I~//~var1R~
//        RSD.setBGUpdated(cb5thKan,RSD.isChanged(RSID_5THKAN));     //~var0I~//~var1R~
//    //*Renho                                                       //~var0I~//~var1R~
//        RSD.setBGUpdated(spnRenhoRank,RSD.isChanged(RSID_RENHORANK));//~var0I~//~var1R~
//        RSD.setBGUpdated(cbRenhoMix,RSD.isChanged(RSID_RENHOMIX)); //~var0I~//~var1R~
//    //*drawnMangan                                                 //~var0I~//~var1R~
//        RSD.setBGUpdated(rgDrawnMangan,RSD.isChanged(RSID_DRAWN_MANGAN_TYPE));//~var0I~//~var1R~
//        RSD.setBGUpdated(spnDrawnManganRank,RSD.isChanged(RSID_DRAWN_MANGAN_RANK));//~var0I~//~var1R~
		chkUpdate_SummOther_Operation();                            //~var2I~
    }                                                              //~var0I~
    //*******************************************************      //~var2I~
    private void chkUpdate_SummOther_Operation()                   //~var2I~
    {                                                              //~var2I~
		if (Dump.Y) Dump.println("RuleSettingSumm.chkUpdateSummOtherOperation");//~var2I~
//    //*delay                                                     //~var2I~
//        RSD.setBGUpdated(sbDelayPonKan,RSD.isChanged(RSID_DELAY_PONKAN));//~var2I~
//        RSD.setBGUpdated(sbDelayTake,RSD.isChanged(RSID_DELAY_TAKE));//~var2I~
//        RSD.setBGUpdated(sbDelayLast,RSD.isChanged(RSID_DELAY_LAST));//~var2I~
//        RSD.setBGUpdated(sbDelayDiscard,RSD.isChanged(RSID_DELAY_DISCARD));//~var2I~
//        RSD.setBGUpdated(sbDelay2Touch,RSD.isChanged(RSID_DELAY_2TOUCH));//~var2I~
//        RSD.setBGUpdated(sbTimeoutTake,RSD.isChanged(RSID_TIMEOUT_TAKE));//~var2I~
//        RSD.setBGUpdated(sbTimeoutTakeRobot,RSD.isChanged(RSID_TIMEOUT_TAKEROBOT));//~var2I~
//        RSD.setBGUpdated(cbPlayMatchNotify,RSD.isChanged(RSID_PLAY_MATCH_NOTIFY));//~var2I~
    //*2Touch                                                      //~var2I~
        RSD.setBGUpdated(cb2TouchCancelableRon,RSD.isChanged(RSID_2TOUCH_CANCELABLE_RON));//~var2I~
//      RSD.setBGUpdated(cb2TouchTimeout,RSD.isChanged(RSID_2TOUCH_TIMEOUT));//~var2I~
    //*YakuFix1                                                    //~var2I~
    	RSD.setBGUpdated(cbYakuFix1,RSD.isChanged(RSID_YAKUFIX1)); //~var2I~
        RSD.setBGUpdated(cb2CheckRonValue,RSD.isChanged(RSID_CHECK_RONVALUE));//~var2I~
        RSD.setBGUpdated(cb2CheckReach,RSD.isChanged(RSID_CHECK_REACH));//~var2I~
        RSD.setBGUpdated(cbChkMultiWait,RSD.isChanged(RSID_CHK_MULTIWAIT));//~var2I~
//    //*positioning                                               //~var2I~
//        RSD.setBGUpdated(cbPositioning,RSD.isChanged(RSID_POSITIONING));//~var2I~
//    //*suspend                                                   //~var2I~
//        RSD.setBGUpdated(spnSuspendPenalty,RSD.isChanged(RSID_SUSPEND_PENALTY));//~var2I~
//        RSD.setBGUpdated(spnSuspendPenaltyIOErr,RSD.isChanged(RSID_SUSPEND_PENALTYIOERR));//~var2I~
//        RSD.setBGUpdated(rgSuspend,RSD.isChanged(RSID_SUSPEND)); //~var2I~
//        RSD.setBGUpdated(rgDelayUnit,RSD.isChanged(RSID_DELAY_UNIT));//~var2I~
    }                                                              //~var2I~
    //*******************************************************      //~v@@@I~
    //*update property from dialog setting                         //~v@@@R~
    //*return changed                                              //~9B09I~
    //*******************************************************      //~v@@@I~
    @Override //RuleSetting                                        //~var0R~
    protected boolean dialog2Properties(String Pfnm)               //~9405I~
    {                                                              //~9405I~
//        String basename=UFile.getBaseName(Pfnm);                   //~9405R~//~var0R~
//        if (Dump.Y) Dump.println("RuleSettingSumm.dialog2Properties basename="+basename+",fpath="+Pfnm);//~9405R~//~var0R~
//        updateProp(getKeyRS(RSID_FILENAME),basename);              //~9405R~//~var0R~
//        return dialog2Properties();                                       //~9405I~//~var0R~
        if (Dump.Y) Dump.println("RuleSettingSumm.dialog2Properties");//~var0I~
		boolean rc=super.dialog2Properties(Pfnm);                  //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.dialog2Properties rc="+rc);//~var0I~
        return rc;                                                 //~var0I~
    }                                                              //~9405I~
    //*******************************************************      //~9405I~
    //*return changed                                              //~9B09I~
    //*******************************************************      //~9B09I~
    @Override //SettingDlg                                         //~v@@@I~
    protected boolean dialog2Properties()                            //~v@@@R~
    {                                                              //~v@@@R~
        int  changed=0;                                            //~v@@@R~//~var0R~
        String txt;                                                //~9407I~//~var0R~
        //*******************                                      //~v@@@I~//~var0R~
        if (Dump.Y) Dump.println("RuleSettingSumm.dialog2Properties");               //~v@@@I~//~9405R~//~var0R~
        changed+=updateProp(getKeyRS(RSID_IDNAME),etIDName.getText().toString());//~9903I~//~var0R~
        changed+=updateProp(getKeyRS(RSID_GAMESET_TYPE),spnGameSetType.getSelectedIndex());//~var0I~
        changed+=updateProp(getKeyRS(RSID_INITSCORE),spnInitialScore.getSelectedIndex());//~9404I~//~var0R~
//    //*FinalLast                                                   //~9501I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_CLOSABLE_RON),cbClosableRon.getStateInt());//~9501I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_CLOSABLE_PENDING),cbClosablePending.getStateInt());//~9501I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_CLOSABLE_NOTTOP),cbClosableNotTop.getStateInt());//~9501I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_GAMESET_TYPE),spnGameSetType.getSelectedIndex());//~9501I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_FL_NOTPENDING),rgFinalLastDealerNotPending.getCheckedID());//~9501I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_FL_ALLMINUS),rgFinalLastAllMinus.getCheckedID());//~9501I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_INITSCORE_TESTE),Utils.parseInt(etInitialScoreTestE.getText().toString(),25000));//~9425R~//~var0R~
//        changed+=updateProp(getKeyRS(RSID_INITSCORE_TESTS),Utils.parseInt(etInitialScoreTestS.getText().toString(),25000));//~9425R~//~var0R~
//        changed+=updateProp(getKeyRS(RSID_INITSCORE_TESTW),Utils.parseInt(etInitialScoreTestW.getText().toString(),25000));//~9425R~//~var0R~
//        changed+=updateProp(getKeyRS(RSID_INITSCORE_TESTN),Utils.parseInt(etInitialScoreTestN.getText().toString(),25000));//~9425R~//~var0R~
          changed+=updateProp(getKeyRS(RSID_POINT_DUP),spnDupPoint.getSelectedIndex());//~9512I~//~var0R~
    //*orderPrize                                                  //~9407I~//~var0I~
        changed+=updateProp(getKeyRS(RSID_ORDERPRIZE),spnOrderPrize.getSelectedIndex());//~9407I~//~var0I~
        changed+=updateProp(getKeyRS(RSID_ORDERPRIZE_SAMEPOINT),rgOrderSamePoint.getCheckedID());//~9407I~//~var0I~
    //*RankMUp                                                     //~9528I~//~var0I~
        changed+=updateProp(getKeyRS(RSID_RANKMUP),cbRankMUp.getStateInt());//~9528I~//~var0I~
    //*scoreToPoint                                                //~9416I~//~var0I~
        changed+=updateProp(getKeyRS(RSID_SCORE_TO_POINT),bgScoreToPoint.getChecked());//~9417I~//~var0I~
    //*Dora                                                        //~9528I~//~var0I~
        if (rgDoraHidden.getCheckedID()==DORA_HIDDEN_NO //chk dialog because Prop is not yet saved//~9529I~//~var0I~
        ||  rgKanDora.getCheckedID()==DORA_KANDORA_NO) //chk dialog because Prop is not yet saved//~9529I~//~var0I~
        {                                                          //~9529M~//~var0I~
            if (rgKanDoraHidden.getCheckedID()!=DORA_KANDORA_HIDDEN_NO) //chk dialog because Prop is not yet saved//~9529I~//~var0I~
            {                                                      //~9529M~//~var0I~
                UView.showToast(R.string.Err_ResetHiddenKanDora);  //~9529M~//~var0I~
                rgKanDoraHidden.setCheckedID(DORA_KANDORA_HIDDEN_NO,false/*swFixed*/);//~var0I~
            }                                                      //~9529M~//~var0I~
        }                                                          //~9529M~//~var0I~
        changed+=updateProp(getKeyRS(RSID_DORA),rgDora.getCheckedID());//~9528I~//~var0I~
        changed+=updateProp(getKeyRS(RSID_DORA_HIDDEN),rgDoraHidden.getCheckedID());//~9528R~//~var0I~
        changed+=updateProp(getKeyRS(RSID_KANDORA),rgKanDora.getCheckedID());//~9528R~//~var0I~
        changed+=updateProp(getKeyRS(RSID_KANDORA_HIDDEN),rgKanDoraHidden.getCheckedID());//~9528R~//~var0I~
        changed+=updateProp(getKeyRS(RSID_KANDORA_OPEN),rgKanDoraOpen.getCheckedID());//~9529I~//~var0I~
        changed+=updateProp(getKeyRS(RSID_USERED5),cbUseRed5.getStateInt());//~9C01I~//~var0I~
    //*SpritPos                                                    //~9528I~//~var0I~
        changed+=updateProp(getKeyRS(RSID_SPRITPOS),cbSpritPos.getStateInt());//~9528I~//~var0I~
    //*minusPay                                                    //~9404I~//~var0R~
        changed+=updateProp(getKeyRS(RSID_MINUSSTOP_POINT),sbMinusPrize.getVal());//~9404I~//~9408R~//~var0R~
        changed+=updateProp(getKeyRS(RSID_MINUSSTOP),cbMinusStop.getStateInt());//~9408I~//~var0R~
        changed+=updateProp(getKeyRS(RSID_MINUSSTOP_0),cbMinus0.getStateInt());//~9404I~//~var0R~
        changed+=updateProp(getKeyRS(RSID_MINUSSTOP_PAYTYPE),rgMinusPay.getCheckedID());//~9404I~//~var0R~
        changed+=updateProp(getKeyRS(RSID_MINUSSTOP_BYERR),rgMinusStopByErr.getCheckedID());//~9414I~//~var0R~
    //*bird                                                        //~9430I~//~var0I~
        changed+=updateProp(getKeyRS(RSID_BIRD_PAYTYPE),rgBirdPayType.getCheckedID());//~9602I~//~var0I~
        changed+=updateProp(getKeyRS(RSID_BIRD),cbBird.getStateInt());//~9430I~//~var0I~
        changed+=updateProp(getKeyRS(RSID_BIRD_PAY),sbBird.getVal());//~9430I~//~var0I~
    //*EatChange                                                   //~v@@@I~//~9516M~//~var0I~
        changed+=updateProp(getKeyRS(RSID_EATCHANGE),rgEatChange.getCheckedID());//~v@@@I~//~9516M~//~var0I~
//    //*PendingCont                                                 //~9709I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_PENDING_CONT),cbPendingCont.getStateInt());//~9709I~//~var0M~
//    //*drawnHW                                                     //~9425I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_MULTIRON3DRAWN),cbMultiRon3Drawn.getStateInt());//~9409I~//~9425M~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_HW99),cbDrawnHW99.getStateInt());//~9425I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4W),cbDrawnHW4W.getStateInt());//~9425I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4K),cbDrawnHW4K.getStateInt());//~9425I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4R),cbDrawnHW4R.getStateInt());//~9425I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_MULTIRON3DRAWNC),cbMultiRon3DrawnCont.getStateInt());//~9B13I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_HW99C),cbDrawnHW99Cont.getStateInt());//~9B13I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4WC),cbDrawnHW4WCont.getStateInt());//~9B13I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4KC),cbDrawnHW4KCont.getStateInt());//~9B13I~//~var0M~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_HW4RC),cbDrawnHW4RCont.getStateInt());//~9B13I~//~var0M~
    //*multiRon                                                    //~9408I~//~var0R~
        changed+=updateProp(getKeyRS(RSID_MULTIRON),rgMultiRon.getCheckedID());//~9408I~//~9409R~//~9513R~//~var0R~
//    //*robot                                                       //~9429I~//~var0R~
//        changed+=updateProp(getKeyRS(RSID_ALLOW_ROBOT),cbAllowRobot.getStateInt());//~9607I~//~var0R~
//        changed+=updateProp(getKeyRS(RSID_THINK_ROBOT),cbThinkRobot.getStateInt());//~va60I~//~var0R~
//        changed+=updateProp(getKeyRS(RSID_MINUSSTOP_ROBOT),cbMinusRobot.getStateInt());//~9404I~//~9429M~//~var0R~
//        changed+=updateProp(getKeyRS(RSID_ROBOT_PAY),rgRobotPay.getCheckedID());//~9429I~//~var0R~
    //**********                                                   //~9404I~//~var0R~
    	if (dialog2Properties_SummOther())        //~var0I~
        changed++;                                 //~var0I~
    //**********                                                   //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.dialog2Properties swChanged="+swChanged+",swChangedYaku="+swChangedYaku+",swChangedOperation="+swChangedOperation);//~var0I~
        if (swChangedYaku)                                         //~9629I~//~var0R~
            changed++;                                             //~9629I~//~var0R~
        if (swChangedOperation)                                    //~9629I~//~var0R~
            changed++;                                             //~9629I~//~var0R~
        if (AG.ruleSyncDate.equals(PROP_INIT_SYNCDATE))            //~vaehI~//~var0R~
        {                                                          //~vaehI~//~var0R~
            changed++;  //update AG.ruleSyncDate at saveSyncDate   //~vaehI~//~var0R~
            if (Dump.Y) Dump.println("RuleSettingSumm.dialog2Properties suncdate is initial");//~vaehI~//~var0R~
        }                                                          //~vaehI~//~var0R~
        if (changed!=0)                                            //~v@@@I~//~var0R~
        {                                                          //~9404I~//~var0R~
            saveSyncDate();                                        //~9404I~//~var0R~
            swChanged=true;                                        //~v@@@I~//~var0R~
        }                                                          //~9404I~//~var0R~
        AG.swChangedRule|=swChanged;                               //~vae8I~//~var0R~
        if (Dump.Y) Dump.println("RuleSettingSumm.dialog2Properties changed="+changed);//~var0I~
        return changed!=0;                                         //~v@@@I~//~var0R~
    }                                                              //~v@@@I~
    //*******************************************************      //~var0I~
    //*chk change of yaku rule on Summ dialog                      //~var0I~
    //*******************************************************      //~var0I~
    protected boolean dialog2Properties_SummOther()                //~var0I~
    {                                                              //~var0I~
        int  changed=0;                                            //~var0I~
        //*******************                                      //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.dialog2Properties_SummOther");//~var0I~
        changed+=updateProp(getKeyRS(RSID_KUITAN),cbKuitan.getStateInt());//~var0I~
        changed+=updateProp(getKeyRS(RSID_SHOW_ANYWAY_BTN),cbShowAnywayBtn.getStateInt());//~var0I~
        changed+=updateProp(getKeyRS(RSID_DOUBLE_PILLOW),cbDoublePillow.getStateInt());//~var0I~
        changed+=updateProp(getKeyRS(RSID_PINFUTAKEN),cbPinfuTaken.getStateInt());//~var0I~
    //*keiten                                                      //~var0I~
        changed+=updateProp(getKeyRS(RSID_PENDING_RANKNO),cbPendingRankNo.getStateInt());//~var0I~
        changed+=updateProp(getKeyRS(RSID_PENDING_RANK0),cbPendingRank0OK.getStateInt());//~var0I~
        changed+=updateProp(getKeyRS(RSID_PENDING_RANK2),rgPendingRank2.getCheckedID());//~var0I~
    //*7Pair                                                       //~var0I~
        changed+=updateProp(getKeyRS(RSID_7PAIR),rgYaku7Pair.getCheckedID());//~var0I~
        changed+=updateProp(getKeyRS(RSID_7PAIR4PAIR),cbYaku7Pair4Pair.getStateInt());//~var0I~
    //*YakuFix                                                     //~var0I~
        changed+=updateProp(getKeyRS(RSID_YAKUFIX),rgYakuFix.getCheckedID());//~var0I~
        changed+=updateProp(getKeyRS(RSID_YAKUFIX_TAKE),rgYakuFixMultiwaitTake.getCheckedID());//~var0I~
    //*Reach                                                       //~var0I~
        changed+=updateProp(getKeyRS(RSID_REACH_OPEN),cbOpenReach.getStateInt());//~var0I~
        changed+=updateProp(getKeyRS(RSID_ONESHOT),cbOneShot.getStateInt());//~var0I~
        changed+=updateProp(getKeyRS(RSID_ANKAN_AFTER_REACH),cbAnkanAfterReach.getStateInt());//~var0I~
        changed+=updateProp(getKeyRS(RSID_REACH_FURITEN),rgFuritenReach.getCheckedID());//~var0I~
        changed+=updateProp(getKeyRS(RSID_OPENREACH_PAY),rgOpenReach.getCheckedID());//~var0I~
        changed+=updateProp(getKeyRS(RSID_OPENREACH_ROBOT_CBNO),cbOpenReachRobot.getStateInt());//~var0I~
    //*YakuFix2                                                    //~var0I~
        changed+=updateProp(getKeyRS(RSID_YAKUFIX2),rgYakuFix2.getCheckedID());//~var0I~
//    //*Yakuman2                                                    //~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_4ANKO1),cbYakuMan2_4Anko.getStateInt());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_KOKUSI13),cbYakuMan2_Kokusi13.getStateInt());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_9REN9),cbYakuMan2_9Ren9.getStateInt());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_4WIND),cbYakuMan2_4Wind.getStateInt());//~var0I~//~var1R~
//    //*Yakuman                                                     //~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_ALLGREEN_NOBLUE),cbAllGreenNoBlue.getStateInt());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_9RENPINSOU),cb9RenPinSou.getStateInt());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_NOPAIR13),cbNoPair13.getStateInt());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_NOPAIR14),cbNoPair14.getStateInt());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_BIGRING),cbBigRing.getStateInt());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_RANK13),cbRank13.getStateInt());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_KOKUSI_ANKANRON),cbKokusiAnkanRon.getStateInt());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_5THKAN),cb5thKan.getStateInt());//~var0I~//~var1R~
//    //*Renho                                                       //~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_RENHORANK),spnRenhoRank.getSelectedIndex());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_RENHOMIX),cbRenhoMix.getStateInt());//~var0I~//~var1R~
//    //*drawnMangan                                                 //~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_TYPE),rgDrawnMangan.getCheckedID());//~var0I~//~var1R~
//        changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_RANK),spnDrawnManganRank.getSelectedIndex());//~var0I~//~var1R~
		changed+=dialog2Properties_SummOther_Operation()?1:0;          //~var2I~
                                                                   //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.dialog2Properties_SummOther exit changed="+changed);//~var0I~
        return changed!=0;                                         //~var0I~
    }                                                              //~var0I~
    //*******************************************************      //~var2I~
    //*chk change of Operation rule on Summ dialog                 //~var2I~
    //*******************************************************      //~var2I~
    protected boolean dialog2Properties_SummOther_Operation()      //~var2I~
    {                                                              //~var2I~
        int  changed=0;                                            //~var2I~
        //*******************                                      //~var2I~
        if (Dump.Y) Dump.println("RuleSettingSumm.dialog2Properties_SummOther_Opeartion");//~var2I~
//    //*delay                                                     //~var2I~
//        changed+=updateProp(getKeyRS(RSID_DELAY_PONKAN),sbDelayPonKan.getVal());//~var2I~
//        changed+=updateProp(getKeyRS(RSID_DELAY_TAKE),sbDelayTake.getVal());//~var2I~
//        changed+=updateProp(getKeyRS(RSID_DELAY_LAST),sbDelayLast.getVal());//~var2I~
//        changed+=updateProp(getKeyRS(RSID_DELAY_DISCARD),sbDelayDiscard.getVal());//~var2I~
//        changed+=updateProp(getKeyRS(RSID_DELAY_2TOUCH),sbDelay2Touch.getVal());//~var2I~
//        changed+=updateProp(getKeyRS(RSID_TIMEOUT_TAKE),sbTimeoutTake.getVal());//~var2I~
//        changed+=updateProp(getKeyRS(RSID_TIMEOUT_TAKEROBOT),sbTimeoutTakeRobot.getVal());//~var2I~
//        changed+=updateProp(getKeyRS(RSID_PLAY_MATCH_NOTIFY),cbPlayMatchNotify.getStateInt());//~var2I~
    //*2Touch                                                      //~var2I~
        changed+=updateProp(getKeyRS(RSID_2TOUCH_CANCELABLE_RON),cb2TouchCancelableRon.getStateInt());//~var2I~
//      changed+=updateProp(getKeyRS(RSID_2TOUCH_TIMEOUT),cb2TouchTimeout.getStateInt());//~var2I~
    //*YakuFix1                                                    //~var2I~
        changed+=updateProp(getKeyRS(RSID_YAKUFIX1),cbYakuFix1.getStateInt());//~var2I~
        changed+=updateProp(getKeyRS(RSID_CHK_MULTIWAIT),cbChkMultiWait.getStateInt());//~var2I~
        changed+=updateProp(getKeyRS(RSID_CHECK_RONVALUE),cb2CheckRonValue.getStateInt());//~var2I~
        changed+=updateProp(getKeyRS(RSID_CHECK_REACH),cb2CheckReach.getStateInt());//~var2I~
//    //*positioning                                               //~var2I~
//        changed+=updateProp(getKeyRS(RSID_POSITIONING),cbPositioning.getStateInt());//~var2I~
//    //*suspend                                                   //~var2I~
//        changed+=updateProp(getKeyRS(RSID_SUSPEND_PENALTY),spnSuspendPenalty.getSelectedIndex());//~var2I~
//        changed+=updateProp(getKeyRS(RSID_SUSPEND_PENALTYIOERR),spnSuspendPenaltyIOErr.getSelectedIndex());//~var2I~
//        changed+=updateProp(getKeyRS(RSID_SUSPEND),rgSuspend.getCheckedID());//~var2I~
//        changed+=updateProp(getKeyRS(RSID_DELAY_UNIT),rgDelayUnit.getCheckedID());//~var2I~
                                                                   //~var2I~
        if (Dump.Y) Dump.println("RuleSettingSumm.dialog2Properties_SummOther_Operation exit changed="+changed);//~var2I~
        return changed!=0;                                         //~var2I~
    }                                                              //~var2I~
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
    //*******************************************************      //~9629I~
    //*save then showstatus                                        //~9629I~
    //*******************************************************      //~9629I~
    @Override                                                      //~9629I~
    public void onClickShowStatus()                                //~9629I~
    {                                                              //~9629I~
        if (Dump.Y) Dump.println("RuleSettingSumm.onClickShowStatus"); //~9629I~//~var0R~
	    getValue();                                                //~9629I~
		super.onClickShowStatus();                                 //~9629I~
    }                                                              //~9629I~
    //*******************************************************      //~9616I~//~var0R~
    @Override                                                      //~9616I~//~var0R~
    protected void onClickLoad()                                   //~9616I~//~var0R~
    {                                                              //~9616I~//~var0R~
        if (Dump.Y) Dump.println("RuleSettingSumm.onClickLoad");       //~9616I~//~var0R~
        super.onClickLoad();                                       //~var0I~
    }                                                              //~9616I~//~var0R~
    //**************************************                       //~9616I~//~var0R~
    @Override                                                      //~9616I~//~var0R~
    public void onClickSave()                                      //~9616I~//~var0R~
    {                                                              //~9616I~//~var0R~
        if (Dump.Y) Dump.println("RuleSettingSumm.onClickSave");        //~9616I~//~var0R~
        super.onClickSave();                                       //~var0I~
    }                                                              //~9616I~//~var0R~
    //**************************************                       //~9404I~
    @Override                                                      //~9404I~
    public void onClickSend()                                      //~9404I~
    {                                                              //~9404I~
        if (Dump.Y) Dump.println("RuleSettingSumm.onClickSend");         //~9404I~//~9629R~//~var0R~
//        getValue();     //update AG.ruleProc if changed                                                //~9404I~//~9406R~//~var0R~
//        AG.ruleSyncDate=curProp.getParameter(getKeyRS(RSID_SYNCDATE),"Unknown");//~9404R~//~9A31R~//~var0R~
//        String cmt=PROP_NAME;                                      //~9404R~//~var0R~
//        send(cmt,curProp);  //SettingDlg                           //~9404R~//~var0R~
		super.onClickSend();                                       //~var0I~
    }                                                              //~9404I~
    //*******************************************************      //~9412I~
    //*by Send and OK button                                       //~9616I~
    //*******************************************************      //~9616I~
    @Override                                                      //~9412I~
    public void getValue()                                         //~9412I~
    {                                                              //~9412I~
        if (Dump.Y) Dump.println("RuleSettingSumm.getvalue swReceived="+swReceived);       //~9412I~//~9616R~//~var0R~
//      super.getValue(); //==>dialog2Properties()                                          //~9412R~//~9629R~//~var0R~
        dialog2Properties();    //summ & summOther                 //~var0I~
        if (swReceived)                                            //~9824I~
        {                                                          //~9824I~
    		saveReceived();                                        //~9824I~
        }                                                          //~9824I~
        else                                                       //~9824I~
        if (swChanged)                                             //~9412I~
        {                                                          //~9412I~
		    saveProperties();	//save to current.rule             //~9412I~
	        AG.ruleProp.resetProperties(curProp);                  //~9412I~
    	    swChanged=false;                                       //~9412I~
	        swChangedYaku=false;                                   //~9516I~
	        swChangedOperation=false;                              //~9624I~
        }                                                          //~9412I~
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
    	if (Dump.Y) Dump.println("RuleSettingSumm.saveSyncDate syncDate="+syncDate+",formatted="+sdf);//~9404I~//~9405R~//~var0R~
    }                                                              //~9404I~
    //**************************************                       //~9826I~
    public static String getSyncDateFormatted()                    //~9826I~
    {                                                              //~9826I~
    	String sd=AG.ruleProp.getParameter(getKeyRS(RSID_SYNCDATE_FORMATTED),null);//~9826I~
    	if (Dump.Y) Dump.println("RuleSettingSumm.getSyncDateFormatted sd="+sd);//~9826I~//~var0R~
        return sd;                                                 //~9826I~
    }                                                              //~9826I~
    //**************************************                       //~v@@@I~
    @Override                                                     //~v@@@I~
    public void onClickOtherUnknown(int Pbuttonid)                        //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSettingSumm.onClickOtherUnknown btnid="+Integer.toHexString(Pbuttonid));//~v@@@I~//~var0R~
        switch(Pbuttonid)                                          //~9515I~
        {                                                          //~9515I~
//          case R.id.btnYakuList:                                 //~9515I~//~var0R~
//              onClickYakuList();                                 //~9515I~//~var0R~
//              break;                                             //~9515I~//~var0R~
//          case R.id.btnOperation:                                //~9624I~//~var0R~
//              onClickOperation();                                //~9624I~//~var0R~
//              break;                                             //~9624I~//~var0R~
            case R.id.btnDetail:                               //~var0I~
                onClickDetail();                                   //~var0I~
                break;                                             //~var0I~
        }                                                          //~9515I~
    }                                                              //~v@@@I~
//    //*************************************************************//~9515I~//~var0R~
//    private void onClickYakuList()                                 //~9515I~//~var0R~
//    {                                                              //~9515I~//~var0R~
//        if (Dump.Y) Dump.println("RuleSettingSumm.onClickYakuList");   //~9515I~//~var0R~
//        RuleSettingYaku.newInstance(this).show();                  //~9515R~//~var0R~
//    }                                                              //~9515I~//~var0R~
//    //*************************************************************//~9624I~//~var0R~
//    private void onClickOperation()                                //~9624I~//~var0R~
//    {                                                              //~9624I~//~var0R~
//        if (Dump.Y) Dump.println("RuleSettingSumm.onClickOperation");  //~9624I~//~var0R~
//        RuleSettingOperation.newInstance(this).show();             //~9624I~//~var0R~
//    }                                                              //~9624I~//~var0R~
    //*************************************************************//~var0I~
    private void onClickDetail()                                   //~var0I~
    {                                                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.onClickDetail swReceived="+swReceived);//~var0R~
        if (Dump.Y) Dump.println("RuleSettingSumm.onClickDetail before curProp="+curProp);//~var0I~
        if (swReceived)                                            //~var0I~
        {                                                          //~var0I~
	        aRuleSettingDetail=RuleSettingDetail.newInstance(this,swReceived,senderYourName,strReceivedProp);//~var0I~
	  		aRuleSettingDetail.show();                             //~var0I~
            return;                                                //~var0I~
        }                                                          //~var0I~
	    dialog2Properties();	//update curprop                   //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.onClickDetail after curProp="+curProp);//~var0I~
//      aRuleSettingDetail=RuleSetting.newInstance(this);          //~var0R~
        aRuleSettingDetail=RuleSettingDetail.newInstance(this);    //~var0I~
  		aRuleSettingDetail.show();                                 //~var0I~
//      dismiss();                                                 //~var0R~
    }                                                              //~var0I~
    //*******************************************************************************//~vae8I~
    //*from MainActivity->AG-->                                    //~vae8I~
    //*******************************************************************************//~vae8I~
    public static void recoverProp(String Pmember)                 //~vae8R~
    {                                                              //~vae8I~
    	if (Dump.Y) Dump.println("RuleSettingSumm.recoverProp member="+Pmember);//~vae8R~//~var0R~
        String fpath=PrefSetting.makeFullpath(Pmember);            //~vae8I~
        if (fpath==null)                                           //~vae8I~
        	return;                                                //~vae8I~
        boolean rc=AG.ruleProp.loadProperties(fpath);              //~vae8I~
    	if (Dump.Y) Dump.println("RuleSettingSumm.recoverProp rc="+rc+",AG.ruleProp="+AG.ruleProp.toString());//~vae8I~//~var0R~
        if (rc)                                                    //~vae8I~
	        recoverSharedPreference();                             //~vae8I~
    }                                                              //~vae8I~
    //*******************************************************************************//~vae8I~
    private static void recoverSharedPreference()                  //~vae8I~
    {                                                              //~vae8I~
    	if (Dump.Y) Dump.println("RuleSettingSumm.recoverSharedPreference Nothing to do");//~vae8I~//~var0R~
    }                                                              //~vae8I~
    //*******************************************************************************//~vae8I~
    //*from MainActivity->AG-->                                    //~vae8I~
    //*******************************************************************************//~vae8I~
    public static boolean saveProp(String Pmember)                    //~vae8R~
    {                                                              //~vae8I~
    	if (Dump.Y) Dump.println("RuleSettingSumm.saveProp member="+Pmember);//~vam2R~//~var0R~
//        if (!AG.swChangedRule                                     //~vae8I~//~var0R~
//        )                                                          //~vae8I~//~var0R~
//        {                                                          //~vae8I~//~var0R~
//            if (Dump.Y) Dump.println("RuleSettingSumm.saveProp return false by swChangeRule");//~vae8I~//~var0R~
//            return false;                                          //~vae8I~//~var0R~
//        }                                                          //~vae8I~//~var0R~
//        String fpath=PrefSetting.makeFullpath(Pmember);                        //~vae8I~//~var0R~
//        boolean rc;                                                //~vae8I~//~var0R~
//        if (UScoped.isScoped())                                    //~vae8I~//~var0R~
//            rc=AG.ruleProp.savePropertiesScoped(fpath,PROP_NAME,true/*swOverride*/);//~vae8I~//~var0R~
//        else                                                       //~vae8I~//~var0R~
//            rc=AG.ruleProp.saveProperties(fpath,PROP_NAME);        //~vae8I~//~var0R~
//        if (Dump.Y) Dump.println("RuleSettingSumm.saveProp rc="+rc);   //~vae8I~//~var0R~
		boolean rc=RuleSetting.saveProp(Pmember);                        //~var0I~
    	if (Dump.Y) Dump.println("RuleSettingSumm.saveProp rc="+rc);//~var0I~
        return rc;                                                 //~vae8I~
    }                                                              //~vae8I~
    //*****************************************************************************************//~9403I~
    private void setCBListener(UCheckBox Pcb,int Pid)              //~var0I~
    {                                                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.setCBListener id="+Pid+",cb="+Integer.toHexString(Pid));//~var0I~
        Pcb.setListener(this,Pid);                                 //~var0I~
    }                                                              //~var0I~
    //*******************************************************      //~var0I~
    @Override                                                      //~var0I~
    public void onChangedUCB(CompoundButton Pbtn, boolean PisChecked, int Pparm)//~var0I~
    {                                                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.onChangedUCB swChildInitializing="+RSD.swChildInitializing+",isChecked="+PisChecked+",parm="+Integer.toHexString(Pparm));//~var0I~
        boolean enable;                                            //~var0I~
	    if (RSD.swChildInitializing)                               //~var0I~
        	return;                                                //~var0I~
        switch(Pparm)                                              //~var0I~
        {                                                          //~var0I~
        case R.id.cbPendingRankNo:                                 //~var0I~
        	enable=!PisChecked;                                    //~var0I~
        	cbPendingRank0OK.setState(enable);                     //~var0I~
        	cbPendingRank0OK.setEnabled(enable);                   //~var0I~
        	rgPendingRank2.setEnabledAll(enable);                  //~var0I~
        	break;                                                 //~var0I~
        }                                                          //~var0I~
    }                                                              //~var0I~
    //******************************************                   //~var0I~
    //*from BTIO at client received prop from server               //~var0I~
    //******************************************                   //~var0I~
    public static void received(String PsenderYourName,String Pprops)//~var0I~
    {                                                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.received senderYourName="+PsenderYourName+",props="+Pprops);//~var0I~
        RuleSettingSumm dlg=AG.aRuleSettingSumm;                       //~var0I~
//      Prop p=AG.ruleProp;                                        //~9404I~//~9405M~//~9616R~//~var0I~
//      p.loadFromString(Pprops);                                  //~9404R~//~9405M~//~9616R~//~var0I~
//      AG.ruleSyncDate=p.getParameter(getKeyRS(RSID_SYNCDATE),"");//~9404I~//~9405M~//~9616R~//~var0I~
        if (RuleSetting.isAppVersionUnmatch(Pprops))                           //~vaqaR~//~var0R~
        {                                                          //~vaqaR~//~var0I~
	        if (Dump.Y) Dump.println("RuleSetting.received AppVersion Unmatch");//~vaqaR~//~var0I~
        	return;                                                //~vaqaR~//~var0I~
        }                                                          //~vaqaR~//~var0I~
        AG.aBTMulti.setLockRuleSetting(false);	//release lock until reply OK at client//~9406I~//~var0I~
        if (Utils.isShowingDialogFragment(dlg))        //~9404I~   //~var0I~
        {                                                          //~9404I~//~var0I~
            dlg.swOpenAfterDismiss=true;               //~9405I~   //~var0I~
            dlg.senderYourName=PsenderYourName;        //~9405I~   //~var0I~
            dlg.strReceivedProp=Pprops;                //~9616I~   //~var0I~
	        dlg.dismiss();                             //~9404I~   //~var0I~
        }                                                          //~9404I~//~var0I~
        else                                                       //~9404I~//~var0I~
        {                                                          //~9405I~//~var0I~
    		RuleSettingSumm.newInstance(true/*swReceived*/,PsenderYourName,Pprops).show();//~var0I~
        }                                                          //~9405I~//~var0I~
	    AG.aBTMulti.setRuleSettingSynchedAll(false/*swOK*/,""/*syncDate*/);//~9406I~//~var0I~
		UView.showToast(R.string.Info_ReceivedRuleSetting);        //~9404I~//~9405I~//~var0I~
    }                                                              //~var0I~
    //******************************************                   //~var0I~
    //*from BTIO at client received prop from server               //~var0I~
    //******************************************                   //~var0I~
    public static void receivedReply(int PthreadIdx,boolean PswOK,String PsyncDate)//~var0I~
    {                                                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.receivedReply swOK="+PswOK+",syncDate="+PsyncDate);//~var0I~
	    RuleSetting.receivedReply(PthreadIdx,PswOK,PsyncDate);     //~var0I~
    }                                                              //~var0I~
    //*************************************************************************//~var0I~
    //*from BTIO at client received from server when sync from all client//~var0I~
    //*************************************************************************//~var0I~
    public static void receivedSyncAll(int PthreadIdx,boolean PswOK,String PsyncDate)//~var0I~
    {                                                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingSumm.receivedSyncAll swOK="+PswOK+",syncDate="+PsyncDate);//~var0I~
	    RuleSetting.receivedSyncAll(PthreadIdx,PswOK,PsyncDate);   //~var0I~
    }                                                              //~var0I~
}//class                                                           //~v@@@R~
