//*CID://+va8xR~:                             update#=  527;       //~va8xR~
//*****************************************************************//~v101I~
//2021/05/01 va8x (Test)specify robot discard tile                 //~va8xI~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2021/02/01 va65 testoption of open hand for discardSmart test    //~va65I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                          //~v@@@R~
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.btmtest.R;
import com.btmtest.TestOption;                                     //~v@@@R~
import com.btmtest.dialog.SettingDlg;                              //~v@@@I~
import com.btmtest.gui.URadioGroup;
import com.btmtest.utils.Prop;                                     //~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.dialog.UFDlg;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.USpinner;
import com.btmtest.utils.UFile;
import com.btmtest.utils.UView;

import java.io.File;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.TestOption.*;                            //~v@@@I~

//~v@@@I~
public class TODlg extends UFDlg                           //~v@@@R~
{                                                                  //~2C29R~
//	private static final int    TITLEID=R.string.Title_TODlg;      //~v@@@R~
	private static final int    LAYOUTID=R.layout.testoptiondialog;//~v@@@R~
	private static final String PROPFILE="current.testoption";     //~v@@@I~
	private static final String DEFAULT_PROPFILE="default.testoption";//~v@@@I~
                                                                   //~v@@@I~
    private static final double RATE_WIDTH_PORTRAIT=0.7;           //~v@@@R~
    private static final double RATE_WIDTH_PORTRAIT_SMALLDEVICE=0.9;//~v@@@I~
    private static final String PON_TEST="Pon_Test";               //~v@@@I~
    private static final String RON_TEST="Ron_Test";               //~v@@@I~
    private static final String DUMP_SDCARD="DumpSDCard";          //~0A08I~
    private static final String RONVALUE_TEST="RonValue_Test";     //~0A02I~
    private static final String RONVALUE_NODORA="NoDora";          //~0A08I~
    private static final String CHKRANK="ChkRank";                 //~0A16I~
    private static final String RONVALUE_CASE="RonValue_Case";     //~0A07I~
    private static final String SET_DORA="SetDora";                //~0A14I~
    private static final String DORA_DOWNTYPE="Dora1Type";         //~0A12I~
    private static final String DORA_DOWNNUMBER="Dora1Num";        //~0A12I~
    private static final String DORA_UPTYPE="Dora2Type";           //~0A12I~
    private static final String DORA_UPNUMBER="Dora2Num";          //~0A12I~
    private static final String DORA_KANDOWNTYPE="Dora3Type";      //~0A12I~
    private static final String DORA_KANDOWNNUMBER="Dora3Num";     //~0A12I~
    private static final String DORA_KANUPTYPE="Dora4Type";        //~0A12I~
    private static final String DORA_KANUPNUMBER="Dora4Num";       //~0A12I~
    private static final String DORA_KANUPTYPE2="Dora5Type";       //~0A12R~
    private static final String DORA_KANUPNUMBER2="Dora5Num";      //~0A12R~
    private static final String DORA_KANDOWNTYPE2="Dora6Type";     //~0A12I~
    private static final String DORA_KANDOWNNUMBER2="Dora6Num";    //~0A12I~
    private static final String CHII_TEST="Chii_Test";             //~v@@@I~
    private static final String KAN_TEST="Kan_Test";               //~v@@@I~
//  private static final String POSITIONING="Positioning";         //~v@@@R~
    private static final String SKIPDICE="SkipDice";               //~v@@@I~
    private static final String TAKEDISCARD="TakeDiscard";         //~v@@@I~
    private static final String DRAWNREQDLG_LAST="DrawnReqDlg_Last";//~v@@@I~
    private static final String SHOWF2="ShowF2";                   //~v@@@I~
    private static final String FINAL_GAME="FinalGame";            //~v@@@I~
    private static final String INITIAL_POSITION="InitialPosition";//~va66I~
    private static final String INITIAL_DUPCTR="InitialDupCtr";    //~va66I~
    private static final String LAYOUT_FINALGAME="LayoutFinalGame";//~v@@@I~
    private static final String DRAWNLAST_SHOW_STICK="DrawnLastShowStick";//~v@@@I~
    private static final String FIRSTDEALER="FirstDealer";         //~v@@@I~
    private static final String FINALGAMECTRSET="FinalCtrSet";     //~v@@@R~
    private static final String FINALGAMECTRGAME="FinalCtrGame";   //~v@@@R~
    private static final String CHANKAN="Chankan";                 //~v@@@I~
    private static final String ROBOTBIRD="RobotBird";              //~v@@@I~
    private static final String CLOSE_ON_CONNECT="CloseOnConnect"; //~v@@@I~
    private static final String RULE_NO_SYNC="RuleNoSync";         //~v@@@I~
    private static final String RULE_SUSPEND="Suspend";            //~v@@@I~
    private static final String SUSPEND_IOERR="IOErr";             //~v@@@I~
    private static final String DISABLEBT="DisableBT";             //~v@@@I~
    private static final String DISABLEBT_TIMING="DisableBT_Timing";//~v@@@I~
    private static final String KANDEAL="KanDeal";                 //~v@@@I~
    private static final String DEAL_RONTAKEN="DealRonTaken";      //~va66I~
    private static final String DEAL_RONTAKEN_DOUBLEREACH="DealRonTakenDoubleReach";//~va66I~
    private static final String DEAL_RONTAKEN1ST="DealRonTaken1st";//~va66I~
    private static final String DEAL_RONRIVER="DealRonRiver";      //~va66I~
    private static final String DEAL_13NOPAIR="Deal13NoPair";      //~va66I~
    private static final String DEAL_14NOPAIR="Deal14NoPair";      //~va66I~
    private static final String KANDEAL_ANKAN="KanDealAnkan";    //~0406I~//~0407R~
    private static final String KANDEAL_CHANKAN="KanDealChankan";  //~0407I~
    private static final String WAITSELECT_PON="WaitSelectPon";    //~v@@@R~
    private static final String WAITSELECT_CHII="WaitSelectChii";  //~v@@@I~
    private static final String OPENHAND       ="OpenHand";        //~va65I~
    private static final String ROBOT_DISCARD_BUTTON="RobotDiscardButton";//~va66I~
    private static final String DEAL_MULTIRON="DealMultiRon";      //~va66I~
    private static final String DEAL_SINGLERON="DealSingleRon";    //~va66I~
    private static final String PONDEAL="PonDeal";                 //~va66I~
    private static final String CHIIDEAL="ChiiDeal";               //~va66I~
    private static final String PONCHIIDEAL="PonChiiDeal";         //~va66I~
    private static final String DOUBLERONDEAL="DoubleRonReal";     //~va66I~
    private static final String KANAFTERREACHDEAL="KanAfterReach"; //~va66I~
    private static final String KANRINSHANRONDEAL="RinshanRonDeal"; //~va66I~
    private static final String CALL1ST="Call1st";                 //~va66I~
    private static final String NOREACH="NoReach";                 //~va66I~
    private static final String DOREACH="DoReach";                 //~va66I~
    private static final String DEAL3DRAGON="Deal3Dragon";         //~va66I~
    private static final String DEALMULTIWAITRON="DealMultiWaitRon";//~va66I~
    private static final String DEALMULTIWAITRONOK="DealMultiWaitRonOK";//~va66I~
    private static final String DEALMULTIWAITWGR="DealMultiWaitWGR";//~va66I~
    private static final String DEALDOUBLERUNFIX="DealDoubleRunFix";//~va66I~
    private static final String DEALDOUBLERUNNOTFIX="DealDoubleRunNotFix";//~va66I~
    private static final String DEALDOUBLERUNNOTFIXNG="DealDoubleRunNotFixNG";//~va66I~
    private static final String DEALDOUBLERUNCHII="DealDoubleRunChii";//~va66I~
    private static final String DEAL3SHIKICHII   ="Deal3ShikiChii";//~va66I~
    private static final String DEAL3SHIKICHIITAKE="Deal3ShikiChiiTake";//~va66I~
    private static final String DEAL3SHIKICHIING ="Deal3ShikiChiiNG";//~va66I~
    private static final String DEALSAMECOLOR ="DealSameColor";    //~va66I~
    private static final String DEALATODUKETAKESAMECOLOR ="DealAtodukeTakeSameColor";//~va66I~
    private static final String DEALSAKIDUKE2HANTAKE     ="DealSakiduke2hanTake";//+va8xI~
    private static final String DEALATODUKETAKEYAKUHAI ="DealAtodukeTakeYakuhai";//~va66I~
    private static final String DEALANKANFIX1          ="DealAnkanFix1"         ;//~va8xI~
    private static final String ROBOTDISCARDTILE       ="RobotDiscardTile";//~va8xI~
                                                                   //~v@@@I~
    private static final int[] rbIDFirstDealer=new int[]{R.id.rbFirstDealer0,R.id.rbFirstDealer1,R.id.rbFirstDealer2,R.id.rbFirstDealer3,R.id.rbFirstDealer4};//~v@@@I~
    private static final int[] rbIDFinalGameCtrSet=new int[]{R.id.rbFinalSet1,R.id.rbFinalSet2,R.id.rbFinalSet3,R.id.rbFinalSet4};//~v@@@I~
    private static final int[] rbIDFinalGameCtrGame=new int[]{R.id.rbFinalGame1,R.id.rbFinalGame2,R.id.rbFinalGame3,R.id.rbFinalGame4};//~v@@@I~
    private static final int[] rbIDBTIOErr=new int[]{R.id.rbBTIOE_AfterTake,R.id.rbBTIOE_AfterDiscard,R.id.rbBTIOE_AfterPon,R.id.rbBTIOE_AfterKan,R.id.rbBTIOE_AfterRon,R.id.rbBTIOE_AfterOpen,R.id.rbBTIOE_AfterRobotTake};//~v@@@R~
    private UCheckBox cbPon_Test,cbChii_Test,cbKan_Test,/*cbPositioning,*/cbSkipDice,cbTakeDiscard,cbDrawnReqDlg_Last;//~v@@@R~
    private UCheckBox cbRon_Test;                                  //~v@@@I~
    private UCheckBox cbRonValue_Test,cbRonValue_NoDora;                             //~0A02I~//~0A08R~
    private UCheckBox cbSetDora;                                   //~0A14I~
    private UCheckBox cbChkRank;                                   //~0A16I~
    private UCheckBox cbDumpSDCard;                                //~0A08I~
    private EditText etRonValue_Case;                             //~0A07I~
    private EditText etDoraDownType,etDoraDownNumber;              //~0A12I~
    private EditText etDoraUpType,etDoraUpNumber;                  //~0A12I~
    private EditText etKanDownType,etKanDownNumber;                //~0A12I~
    private EditText etKanUpType,etKanUpNumber;                    //~0A12I~
    private EditText etKanUpType2,etKanUpNumber2;                  //~0A12I~
    private EditText etKanDownType2,etKanDownNumber2;              //~0A12I~
    private UCheckBox cbShowF2;                                    //~v@@@I~
    private UCheckBox cbFinalGame,cbLayoutFinalGame;               //~v@@@R~
    private UCheckBox cbSetPosition,cbSetDupCtr;                   //~va66R~
    private UCheckBox cbDrawnLastShowStick;                        //~v@@@I~
    private UCheckBox cbChankan,cbRobotBird;                       //~v@@@R~
    private UCheckBox cbCloseOnConnect,cbRuleNoSync;                            //~v@@@I~
    private UCheckBox cbSuspend;                                   //~v@@@I~
    private UCheckBox cbBTIOErr;                                   //~v@@@I~
    private UCheckBox cbKanDeal,cbWaitSelectPon,cbWaitSelectChii;  //~v@@@R~
    private UCheckBox cbPonDeal;                                   //~va66I~
    private UCheckBox cbChiiDeal;                                  //~va66I~
    private UCheckBox cbPonChiiDeal,cbDoubleRonDeal,cbKanAfterReachDeal;   //~va66R~
    private UCheckBox cbKanRinshanRonDeal;                         //~va66I~
    private UCheckBox cbCall1st,cbNoReach,cbDoReach;               //~va66R~
    private UCheckBox cbDeal3Dragon,cbDealMultiWaitRon,cbDealMultiWaitRonOK,cbDealMultiWaitWGR;//~va66R~
    private UCheckBox cbDealDoubleRunFix,cbDealDoubleRunNotFix,cbDealDoubleRunNotFixNG;//~va66R~
    private UCheckBox cbKanDealAnkan,cbKanDealChankan,cbDealDoubleRunChii,cbDeal3ShikiChii,cbDeal3ShikiChiiNG;                             //~0406I~//~0407R~//~va66R~
    private UCheckBox cbDeal3ShikiChiiTake,cbDealSameColor,cbDealAtodukeTakeSameColor,cbDealAtodukeTakeYakuhai;//~va66R~
    private UCheckBox cbDealRonTaken,cbDealRonTakenDouble,cbDeal13NoPair,cbDeal14NoPair;//~va66R~
    private UCheckBox cbDealRonTaken1st,cbRonRiver,cbDealAnkanFix1,cbDealSakiduke2hanTake;                //~va66R~//+va8xR~
    private UCheckBox cbOpenHand,cbRobotDiscardTile;                                  //~va65I~//~va8xR~
    private UCheckBox cbRobotDiscardButton;                        //~va66I~
    private UCheckBox cbDealMultiRon,cbDealSingleRon;              //~va66I~
    private Prop prop;                                             //~v@@@I~
    private boolean swChanged;                                     //~v@@@I~
    private URadioGroup rgFirstDealer;                             //~v@@@I~
    private URadioGroup rgFinalGameCtrSet,rgFinalGameCtrGame;      //~v@@@I~
    private URadioGroup rgBTIOErr;                                 //~v@@@I~
    private View llIOErr;                                          //~v@@@I~
                                                                   //~1A6fI~
    //******************************************                   //~v@@@M~
	public TODlg()                                                 //~v@@@R~
    {
        if (Dump.Y) Dump.println("TODlg.defaultConstructor");      //~v@@@R~
    }
    public static TODlg newInstance()                              //~v@@@R~
    {                                                              //~v@@@I~
        TODlg dlg=new TODlg();                                     //~v@@@R~
        UFDlg.setBundle(dlg,"TestOption",LAYOUTID,           //SettingDlg//~v@@@R~
                    UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN,//~v@@@I~
                    0,"NoHelp");                            //~v@@@R~
        return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@M~
    @Override
    protected void initLayout(View PView)                            //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("TODlg.initLayout");              //~v@@@I~
        super.initLayout(PView);                                   //~v@@@I~
    	cbPon_Test=new UCheckBox(PView,R.id.cbPon_Test);           //~v@@@I~
    	cbRon_Test=new UCheckBox(PView,R.id.cbRon_Test);           //~v@@@I~
    	cbDumpSDCard=new UCheckBox(PView,R.id.cbDumpSDCard);       //~0A08I~
    	cbRonValue_Test=new UCheckBox(PView,R.id.cbRonValue_Test); //~0A02I~
    	cbRonValue_NoDora=new UCheckBox(PView,R.id.cbRonValue_NoDora);//~0A08I~
    	cbChkRank=new UCheckBox(PView,R.id.cbChkRank);             //~0A16I~
    	cbSetDora=new UCheckBox(PView,R.id.cbSetDora);             //~0A14I~
    	etRonValue_Case=(EditText) UView.findViewById(PView,R.id.etRonValueCase);//~0A07I~
    	etDoraDownType=(EditText) UView.findViewById(PView,R.id.etDoraDownType);//~0A12I~
    	etDoraDownNumber=(EditText) UView.findViewById(PView,R.id.etDoraDownNumber);//~0A12I~
    	etDoraUpType=(EditText) UView.findViewById(PView,R.id.etDoraUpType);//~0A12I~
    	etDoraUpNumber=(EditText) UView.findViewById(PView,R.id.etDoraUpNumber);//~0A12I~
    	etKanDownType=(EditText) UView.findViewById(PView,R.id.etKanDownType);//~0A12I~
    	etKanDownNumber=(EditText) UView.findViewById(PView,R.id.etKanDownNumber);//~0A12I~
    	etKanUpType=(EditText) UView.findViewById(PView,R.id.etKanUpType);//~0A12I~
    	etKanUpNumber=(EditText) UView.findViewById(PView,R.id.etKanUpNumber);//~0A12I~
    	etKanDownType2=(EditText) UView.findViewById(PView,R.id.etKanDownType2);//~0A12I~
    	etKanDownNumber2=(EditText) UView.findViewById(PView,R.id.etKanDownNumber2);//~0A12I~
    	etKanUpType2=(EditText) UView.findViewById(PView,R.id.etKanUpType2);//~0A12I~
    	etKanUpNumber2=(EditText) UView.findViewById(PView,R.id.etKanUpNumber2);//~0A12I~
    	cbChii_Test=new UCheckBox(PView,R.id.cbChii_Test);         //~v@@@I~
    	cbKan_Test=new UCheckBox(PView,R.id.cbKan_Test);           //~v@@@I~
//  	cbPositioning=new UCheckBox(PView,R.id.cbPositioning);     //~v@@@R~
    	cbSkipDice=new UCheckBox(PView,R.id.cbSkipDice);           //~v@@@I~
    	cbTakeDiscard=new UCheckBox(PView,R.id.cbTakeDiscard);     //~v@@@I~
    	cbDrawnReqDlg_Last=new UCheckBox(PView,R.id.cbDrawnReqDlg_Last);//~v@@@I~
    	cbShowF2=new UCheckBox(PView,R.id.cbShowF2);               //~v@@@I~
    	cbFinalGame=new UCheckBox(PView,R.id.cbFinalGame);         //~v@@@I~
    	cbSetPosition=new UCheckBox(PView,R.id.cbSetPosition);     //~va66I~
    	cbSetDupCtr=new UCheckBox(PView,R.id.cbSetDupCtr);         //~va66I~
    	cbLayoutFinalGame=new UCheckBox(PView,R.id.cbLayoutFinalGame);//~v@@@I~
    	cbDrawnLastShowStick=new UCheckBox(PView,R.id.cbDrawnLastShowStick);//~v@@@I~
        rgFirstDealer=new URadioGroup(PView,R.id.rgFirstDealer,0,rbIDFirstDealer);//~v@@@I~
        rgFinalGameCtrSet=new URadioGroup(PView,R.id.rgFinalGameCtrSet,0,rbIDFinalGameCtrSet);//~v@@@R~
        rgFinalGameCtrGame=new URadioGroup(PView,R.id.rgFinalGameCtrGame,0,rbIDFinalGameCtrGame);//~v@@@R~
    	cbChankan=new UCheckBox(PView,R.id.cbChankan);             //~v@@@I~
    	cbRobotBird=new UCheckBox(PView,R.id.cbBirdWithRobot);      //~v@@@I~
    	cbCloseOnConnect=new UCheckBox(PView,R.id.cbCloseOnConnect);//~v@@@I~
    	cbRuleNoSync=new UCheckBox(PView,R.id.cbRuleNoSync);       //~v@@@I~
    	cbSuspend   =new UCheckBox(PView,R.id.cbSuspend);          //~v@@@I~
    	cbKanDeal   =new UCheckBox(PView,R.id.cbKanDeal);          //~v@@@I~
    	cbPonDeal   =new UCheckBox(PView,R.id.cbPonDeal);          //~va66I~
    	cbChiiDeal   =new UCheckBox(PView,R.id.cbChiiDeal);        //~va66I~
    	cbPonChiiDeal   =new UCheckBox(PView,R.id.cbPonChiiDeal);  //~va66I~
    	cbDoubleRonDeal   =new UCheckBox(PView,R.id.cbDoubleRonDeal);  //~va66I~
    	cbKanAfterReachDeal=new UCheckBox(PView,R.id.cbKanAfterReachDeal);//~va66I~
    	cbKanRinshanRonDeal=new UCheckBox(PView,R.id.cbKanRinshanRonDeal);//~va66I~
    	cbCall1st   =new UCheckBox(PView,R.id.cbCall1st);          //~va66I~
    	cbNoReach   =new UCheckBox(PView,R.id.cbNoReach);          //~va66I~
    	cbDoReach   =new UCheckBox(PView,R.id.cbDoReach);          //~va66I~
    	cbDeal3Dragon  =new UCheckBox(PView,R.id.cbDeal3Dragon);   //~va66I~
    	cbDealMultiWaitRon  =new UCheckBox(PView,R.id.cbDealMultiWaitRon);//~va66I~
    	cbDealMultiWaitRonOK  =new UCheckBox(PView,R.id.cbDealMultiWaitRonOK);//~va66I~
    	cbDealMultiWaitWGR    =new UCheckBox(PView,R.id.cbDealMultiWaitWGR);//~va66I~
    	cbDealDoubleRunFix  =new UCheckBox(PView,R.id.cbDealDoubleRunFix);//~va66I~
    	cbDealDoubleRunNotFix  =new UCheckBox(PView,R.id.cbDealDoubleRunNotFix);//~va66I~
    	cbDealDoubleRunNotFixNG  =new UCheckBox(PView,R.id.cbDealDoubleRunNotFixNG);//~va66I~
    	cbDealDoubleRunChii =new UCheckBox(PView,R.id.cbDealDoubleRunChii);//~va66I~
    	cbDeal3ShikiChii =new UCheckBox(PView,R.id.cbDeal3shikiChii);//~va66I~
    	cbDealSameColor  =new UCheckBox(PView,R.id.cbDealSameColor);//~va66I~
    	cbDealAtodukeTakeSameColor  =new UCheckBox(PView,R.id.cbDealAtodukeTakeSameColor);//~va66I~
    	cbDealSakiduke2hanTake  =new UCheckBox(PView,R.id.cbDealSakiduke2hanTake);//+va8xI~
    	cbRobotDiscardTile =new UCheckBox(PView,R.id.cbRobotDiscardTile);//~va8xI~
    	cbDealAtodukeTakeYakuhai    =new UCheckBox(PView,R.id.cbDealAtodukeTakeYakuhai  );//~va66I~
    	cbDealAnkanFix1    =new UCheckBox(PView,R.id.cbDealAnkanFix1);//~va8xI~
    	cbDeal3ShikiChiiTake =new UCheckBox(PView,R.id.cbDeal3shikiChiiTake);//~va66I~
    	cbDeal3ShikiChiiNG =new UCheckBox(PView,R.id.cbDeal3shikiChiiNG);//~va66I~
    	cbKanDealAnkan   =new UCheckBox(PView,R.id.cbKanDealAnkan);//~0406I~//~0407R~
    	cbDealRonTaken   =new UCheckBox(PView,R.id.cbDealRonTaken);//~va66I~
    	cbDealRonTakenDouble   =new UCheckBox(PView,R.id.cbDealRonTakenDouble);//~va66I~
    	cbDealRonTaken1st=new UCheckBox(PView,R.id.cbDealRonTaken1st);//~va66I~
    	cbRonRiver=new UCheckBox(PView,R.id.cbRonRiver);           //~va66I~
    	cbDeal13NoPair   =new UCheckBox(PView,R.id.cbDeal13NoPair);//~va66I~
    	cbDeal14NoPair   =new UCheckBox(PView,R.id.cbDeal14NoPair);//~va66I~
    	cbKanDealChankan   =new UCheckBox(PView,R.id.cbKanDealChankan);//~0407I~
    	cbOpenHand         =new UCheckBox(PView,R.id.cbOpenHand);  //~va65I~
    	cbRobotDiscardButton=new UCheckBox(PView,R.id.cbRobotDiscardButton);//~va66I~
    	cbDealMultiRon      =new UCheckBox(PView,R.id.cbDealMultiRon);//~va66I~
    	cbDealSingleRon     =new UCheckBox(PView,R.id.cbDealSingleRon);//~va66I~
    	cbBTIOErr   =new UCheckBox(PView,R.id.cbBTIOErr);          //~v@@@I~
        rgBTIOErr   =new URadioGroup(PView,R.id.rgBTIOErr,0,rbIDBTIOErr);//~v@@@I~
    	cbWaitSelectPon=new UCheckBox(PView,R.id.cbWaitSelectPon); //~v@@@R~
    	cbWaitSelectChii=new UCheckBox(PView,R.id.cbWaitSelectCii); //~v@@@I~
    	llIOErr=(View) UView.findViewById(PView,R.id.llIOErr);      //~v@@@I~
                                                                   //~v@@@I~
        prop=loadProp();                                           //~v@@@R~
        setInitialValue();                                         //~v@@@I~
    }                                                              //~v@@@M~
    //******************************************                   //~9811I~//~v@@@I~
	@Override                                                      //~9811I~//~v@@@I~
    protected int getDialogWidth()                                 //~9811R~//~v@@@I~
    {                                                              //~9811I~//~v@@@I~
        int ww;
        if (AG.portrait)                                           //~v@@@I~
    		ww=(int)(AG.scrWidth*(AG.swSmallDevice?RATE_WIDTH_PORTRAIT_SMALLDEVICE:RATE_WIDTH_PORTRAIT));//~v@@@R~
        else                                                       //~v@@@I~
        	ww=0;                                                  //~v@@@I~
    	if (Dump.Y) Dump.println("TODlg.getDialogWidth portrait="+AG.portrait+",ww="+ww);//~9811R~//~9813R~//~v@@@I~
        return ww;                                                 //~v@@@I~
    }                                                              //~9811I~//~v@@@I~
	//*****************                                                //~1613I~//~v@@@I~
    private static Prop loadProp()                                 //~v@@@R~
    {                                                              //~1613I~//~v@@@M~
        if (Dump.Y) Dump.println("TODlg.loadProp");                //~v@@@R~
	    Prop p=AG.loadProp(PROPFILE,DEFAULT_PROPFILE);             //~v@@@R~
        if (Dump.Y) Dump.println("TODlg.loadProp prop="+p.toString());//~v@@@R~
        return p;                                                  //~v@@@I~
    }                                                              //~v@@@I~
	//*****************                                            //~v@@@I~
    protected void setInitialValue()                               //~v@@@I~
    {                                                              //~v@@@I~
        setupDialog(prop);                                         //~v@@@I~
    }                                                              //~v@@@I~
	//*****************                                            //~v@@@I~
    private void setupDialog(Prop Pprop)                           //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("TODlg.setupDialog");             //~v@@@I~
    	cbPon_Test.setStateInt(Pprop.getParameter(PON_TEST,0));    //~v@@@I~
    	cbRon_Test.setStateInt(Pprop.getParameter(RON_TEST,0));    //~v@@@I~
    	cbDumpSDCard.setStateInt(Pprop.getParameter(DUMP_SDCARD,0));//~0A08I~
    	cbRonValue_Test.setStateInt(Pprop.getParameter(RONVALUE_TEST,0));//~0A02I~
    	cbRonValue_NoDora.setStateInt(Pprop.getParameter(RONVALUE_NODORA,0));//~0A08I~
    	cbChkRank.setStateInt(Pprop.getParameter(CHKRANK,0));      //~0A16I~
    	cbSetDora.setStateInt(Pprop.getParameter(SET_DORA,0));     //~0A14I~
    	etRonValue_Case.setText(Integer.toString(Pprop.getParameter(RONVALUE_CASE,0)));//~0A07I~
    	etDoraDownType.setText(Integer.toString(Pprop.getParameter(DORA_DOWNTYPE,0)));//~0A12I~
    	etDoraDownNumber.setText(Integer.toString(Pprop.getParameter(DORA_DOWNNUMBER,0)));//~0A12I~
    	etDoraUpType.setText(Integer.toString(Pprop.getParameter(DORA_UPTYPE,0)));//~0A12I~
    	etDoraUpNumber.setText(Integer.toString(Pprop.getParameter(DORA_UPNUMBER,0)));//~0A12I~
    	etKanDownType.setText(Integer.toString(Pprop.getParameter(DORA_KANDOWNTYPE,0)));//~0A12I~
    	etKanDownNumber.setText(Integer.toString(Pprop.getParameter(DORA_KANDOWNNUMBER,0)));//~0A12I~
    	etKanUpType.setText(Integer.toString(Pprop.getParameter(DORA_KANUPTYPE,0)));//~0A12I~
    	etKanUpNumber.setText(Integer.toString(Pprop.getParameter(DORA_KANUPNUMBER,0)));//~0A12I~
    	etKanUpType2.setText(Integer.toString(Pprop.getParameter(DORA_KANUPTYPE2,0)));//~0A12I~
    	etKanUpNumber2.setText(Integer.toString(Pprop.getParameter(DORA_KANUPNUMBER2,0)));//~0A12I~
    	etKanDownType2.setText(Integer.toString(Pprop.getParameter(DORA_KANDOWNTYPE2,0)));//~0A12M~
    	etKanDownNumber2.setText(Integer.toString(Pprop.getParameter(DORA_KANDOWNNUMBER2,0)));//~0A12M~
    	cbChii_Test.setStateInt(Pprop.getParameter(CHII_TEST,0));  //~v@@@I~
    	cbKan_Test.setStateInt(Pprop.getParameter(KAN_TEST,0));    //~v@@@I~
//  	cbPositioning.setStateInt(Pprop.getParameter(POSITIONING,0));//~v@@@R~
    	cbSkipDice.setStateInt(Pprop.getParameter(SKIPDICE,0));    //~v@@@I~
    	cbTakeDiscard.setStateInt(Pprop.getParameter(TAKEDISCARD,0));//~v@@@I~
    	cbDrawnReqDlg_Last.setStateInt(Pprop.getParameter(DRAWNREQDLG_LAST,0));//~v@@@I~
    	cbShowF2.setStateInt(Pprop.getParameter(SHOWF2,0));        //~v@@@I~
    	cbFinalGame.setStateInt(Pprop.getParameter(FINAL_GAME,0)); //~v@@@I~
    	cbSetPosition.setStateInt(Pprop.getParameter(INITIAL_POSITION,0));//~va66I~
    	cbSetDupCtr.setStateInt(Pprop.getParameter(INITIAL_DUPCTR,0));//~va66R~
    	cbLayoutFinalGame.setStateInt(Pprop.getParameter(LAYOUT_FINALGAME,0));//~v@@@I~
    	cbDrawnLastShowStick.setStateInt(Pprop.getParameter(DRAWNLAST_SHOW_STICK,0));//~v@@@I~
        rgFirstDealer.setCheckedID(Pprop.getParameter(FIRSTDEALER,0),false);//~v@@@R~
        rgFinalGameCtrSet.setCheckedID(Pprop.getParameter(FINALGAMECTRSET,0),false);//~v@@@I~
        rgFinalGameCtrGame.setCheckedID(Pprop.getParameter(FINALGAMECTRGAME,0),false);//~v@@@I~
    	cbChankan.setStateInt(Pprop.getParameter(CHANKAN,0));      //~v@@@I~
    	cbRobotBird.setStateInt(Pprop.getParameter(ROBOTBIRD,0));  //~v@@@I~
    	cbCloseOnConnect.setStateInt(Pprop.getParameter(CLOSE_ON_CONNECT,0));//~v@@@I~
    	cbRuleNoSync.setStateInt(Pprop.getParameter(RULE_NO_SYNC,0));//~v@@@I~
    	cbSuspend.setStateInt(Pprop.getParameter(RULE_SUSPEND,0)); //~v@@@I~
    	cbBTIOErr.setStateInt(Pprop.getParameter(DISABLEBT,0));    //~v@@@I~
    	cbKanDeal.setStateInt(Pprop.getParameter(KANDEAL,0));      //~v@@@I~
    	cbPonDeal.setStateInt(Pprop.getParameter(PONDEAL,0));      //~va66I~
    	cbChiiDeal.setStateInt(Pprop.getParameter(CHIIDEAL,0));    //~va66I~
    	cbPonChiiDeal.setStateInt(Pprop.getParameter(PONCHIIDEAL,0));//~va66I~
    	cbDoubleRonDeal.setStateInt(Pprop.getParameter(DOUBLERONDEAL,0));//~va66I~
    	cbKanAfterReachDeal.setStateInt(Pprop.getParameter(KANAFTERREACHDEAL,0));//~va66I~
    	cbKanRinshanRonDeal.setStateInt(Pprop.getParameter(KANRINSHANRONDEAL,0));//~va66I~
    	cbCall1st.setStateInt(Pprop.getParameter(CALL1ST,0));     //~va66I~
    	cbNoReach.setStateInt(Pprop.getParameter(NOREACH,0));      //~va66I~
    	cbDoReach.setStateInt(Pprop.getParameter(DOREACH,0));      //~va66I~
    	cbDeal3Dragon.setStateInt(Pprop.getParameter(DEAL3DRAGON,0));//~va66I~
    	cbDealMultiWaitRon.setStateInt(Pprop.getParameter(DEALMULTIWAITRON,0));//~va66I~
    	cbDealMultiWaitRonOK.setStateInt(Pprop.getParameter(DEALMULTIWAITRONOK,0));//~va66I~
    	cbDealMultiWaitWGR.setStateInt(Pprop.getParameter(DEALMULTIWAITWGR,0));//~va66I~
    	cbDealDoubleRunFix.setStateInt(Pprop.getParameter(DEALDOUBLERUNFIX,0));//~va66I~
    	cbDealDoubleRunNotFix.setStateInt(Pprop.getParameter(DEALDOUBLERUNNOTFIX,0));//~va66I~
    	cbDealDoubleRunNotFixNG.setStateInt(Pprop.getParameter(DEALDOUBLERUNNOTFIXNG,0));//~va66I~
    	cbDealDoubleRunChii.setStateInt(Pprop.getParameter(DEALDOUBLERUNCHII,0));//~va66I~
    	cbDeal3ShikiChii.setStateInt(Pprop.getParameter(DEAL3SHIKICHII,0));//~va66I~
    	cbDealSameColor.setStateInt(Pprop.getParameter(DEALSAMECOLOR,0));//~va66I~
    	cbDealAtodukeTakeSameColor.setStateInt(Pprop.getParameter(DEALATODUKETAKESAMECOLOR,0));//~va66I~
    	cbDealSakiduke2hanTake.setStateInt(Pprop.getParameter(DEALSAKIDUKE2HANTAKE,0));//+va8xI~
    	cbRobotDiscardTile.setStateInt(Pprop.getParameter(ROBOTDISCARDTILE,0));//~va8xI~
    	cbDealAtodukeTakeYakuhai.setStateInt(Pprop.getParameter(DEALATODUKETAKEYAKUHAI,0));//~va66I~
    	cbDealAnkanFix1.setStateInt(Pprop.getParameter(DEALANKANFIX1,0));//~va8xI~
    	cbDeal3ShikiChiiTake.setStateInt(Pprop.getParameter(DEAL3SHIKICHIITAKE,0));//~va66I~
    	cbDeal3ShikiChiiNG.setStateInt(Pprop.getParameter(DEAL3SHIKICHIING,0));//~va66I~
    	cbKanDealAnkan.setStateInt(Pprop.getParameter(KANDEAL_ANKAN,0));//~0406I~//~0407R~
    	cbDealRonTaken.setStateInt(Pprop.getParameter(DEAL_RONTAKEN,0));//~va66I~
    	cbDealRonTakenDouble.setStateInt(Pprop.getParameter(DEAL_RONTAKEN_DOUBLEREACH,0));//~va66I~
    	cbDealRonTaken1st.setStateInt(Pprop.getParameter(DEAL_RONTAKEN1ST,0));//~va66I~
    	cbRonRiver.setStateInt(Pprop.getParameter(DEAL_RONRIVER,0));//~va66I~
    	cbDeal13NoPair.setStateInt(Pprop.getParameter(DEAL_13NOPAIR,0));//~va66I~
    	cbDeal14NoPair.setStateInt(Pprop.getParameter(DEAL_14NOPAIR,0));//~va66I~
    	cbKanDealChankan.setStateInt(Pprop.getParameter(KANDEAL_CHANKAN,0));//~0407I~
    	cbOpenHand.setStateInt(Pprop.getParameter(OPENHAND,0));    //~va65I~
    	cbRobotDiscardButton.setStateInt(Pprop.getParameter(ROBOT_DISCARD_BUTTON,0));//~va66I~
    	cbDealMultiRon.setStateInt(Pprop.getParameter(DEAL_MULTIRON,0));//~va66I~
    	cbDealSingleRon.setStateInt(Pprop.getParameter(DEAL_SINGLERON,0));//~va66I~
        rgBTIOErr.setCheckedID(Pprop.getParameter(DISABLEBT_TIMING,0),false);//~v@@@I~
    	setEswnCB(llIOErr,Pprop.getParameter(SUSPEND_IOERR,0));    //~v@@@I~
    	cbWaitSelectPon.setStateInt(Pprop.getParameter(WAITSELECT_PON,0));//~v@@@R~
    	cbWaitSelectChii.setStateInt(Pprop.getParameter(WAITSELECT_CHII,0));//~v@@@I~
    }                                                              //~1613I~//~v@@@M~
    //*******************************************************      //~v@@@I~
    private int updateProp(String Pkey,int Pnewval)                //~v@@@I~
    {                                                              //~v@@@I~
    	int rc=0;                                                  //~v@@@I~
        if (Pnewval<0)                                             //~v@@@I~
        	return rc;                                             //~v@@@I~
    	int oldval=prop.getParameter(Pkey,-1);                  //~v@@@I~
        if (oldval!=Pnewval)                                       //~v@@@I~
        {                                                          //~v@@@I~
	        prop.setParameter(Pkey,Pnewval);                    //~v@@@I~
            rc=1;                                                  //~v@@@I~
        }                                                          //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //*******************************************************    //~v@@@R~
//    private void setTestOption()                                 //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("TODlg.setTestOption");         //~v@@@R~
//        if (cbPon_Test.getState())                               //~v@@@R~
//            TestOption.option|=TO_PON_TEST;                      //~v@@@R~
//        else                                                     //~v@@@R~
//            TestOption.option&=~TO_PON_TEST;                     //~v@@@R~
//        if (cbChii_Test.getState())                              //~v@@@R~
//            TestOption.option|=TO_CHII_TEST;                     //~v@@@R~
//        else                                                     //~v@@@R~
//            TestOption.option&=~TO_CHII_TEST;                    //~v@@@R~
//        if (cbKan_Test.getState())                               //~v@@@R~
//            TestOption.option|=TO_KAN_TEST;                      //~v@@@R~
//        else                                                     //~v@@@R~
//            TestOption.option&=~TO_KAN_TEST;                     //~v@@@R~
//        if (cbPositioning.getState())                            //~v@@@R~
//            TestOption.option|=TO_POSITIONING;                   //~v@@@R~
//        else                                                     //~v@@@R~
//            TestOption.option&=~TO_POSITIONING;                  //~v@@@R~
//        if (cbSkipDice.getState())                               //~v@@@R~
//            TestOption.option|=TO_SKIPDICE;                      //~v@@@R~
//        else                                                     //~v@@@R~
//            TestOption.option&=~TO_SKIPDICE;                     //~v@@@R~
//        if (cbTakeDiscard.getState())                            //~v@@@R~
//            TestOption.option|=TO_TAKEDISCARD;                   //~v@@@R~
//        else                                                     //~v@@@R~
//            TestOption.option&=~TO_TAKEDISCARD;                  //~v@@@R~
//        if (cbDrawnReqDlg_Last.getState())                       //~v@@@R~
//            TestOption.option|=TO_DRAWNREQDLG_LAST;              //~v@@@R~
//        else                                                     //~v@@@R~
//            TestOption.option&=~TO_DRAWNREQDLG_LAST;             //~v@@@R~
//        if (Dump.Y) Dump.println("TODlg.setTestOption from Dialog TO="+Integer.toHexString(TestOption.option));//~v@@@I~
//    }                                                            //~v@@@R~
    //*******************************************************      //~v@@@I~
    private static void setTestOption(Prop Pprop)                  //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("TODlg.setTestOption");           //~v@@@I~
        if (Pprop.getParameter(PON_TEST,0)!=0)                     //~v@@@I~
			TestOption.option|=TO_PON_TEST;                        //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option&=~TO_PON_TEST;                       //~v@@@I~
        if (Pprop.getParameter(RON_TEST,0)!=0)                     //~v@@@I~
			TestOption.option2|=TO2_RON_TEST;                      //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option2&=~TO2_RON_TEST;                      //~v@@@I~//~va65R~
        if (Pprop.getParameter(DUMP_SDCARD,0)!=0)                  //~0A08I~
        {                                                          //~0A08I~
			TestOption.option2|=TO2_DUMP_SDCARD;                   //~0A08I~
            Dump.open("Dump.txt",true/*sdcard*/);                  //~0A08I~
        }                                                          //~0A08I~
        else                                                       //~0A08I~
			TestOption.option2&=~TO2_DUMP_SDCARD;                   //~0A08I~//~va65R~
        if (Pprop.getParameter(RONVALUE_TEST,0)!=0)                //~0A02I~
			TestOption.option2|=TO2_RONVALUE_TEST;                  //~0A02I~
        else                                                       //~0A02I~
			TestOption.option2&=~TO2_RONVALUE_TEST;                 //~0A02I~//~va65R~
        if (Pprop.getParameter(RONVALUE_NODORA,0)!=0)              //~0A08I~
			TestOption.option2|=TO2_RONVALUE_NODORA;               //~0A08I~
        else                                                       //~0A08I~
			TestOption.option2&=~TO2_RONVALUE_NODORA;               //~0A08I~//~va65R~
        if (Pprop.getParameter(CHKRANK,0)!=0)                      //~0A16I~
			TestOption.option2|=TO2_CHKRANK;                      //~0A16I~
        else                                                       //~0A16I~
			TestOption.option2&=~TO2_CHKRANK;                       //~0A16I~//~va65R~
        if (Pprop.getParameter(SET_DORA,0)!=0)                     //~0A14I~
			TestOption.option2|=TO2_SETDORA;                       //~0A14I~
        else                                                       //~0A14I~
			TestOption.option2&=~TO2_SETDORA;                       //~0A14I~//~va65R~
                                                                   //~0A07I~
        if (Pprop.getParameter(OPENHAND,0)!=0)                     //~va65I~
			TestOption.option2|=TO2_OPENHAND;                      //~va65I~
        else                                                       //~va65I~
			TestOption.option2&=~TO2_OPENHAND;                     //~va65R~
        if (Pprop.getParameter(ROBOT_DISCARD_BUTTON,0)!=0)         //~va66I~
			TestOption.option2|=TO2_ROBOT_DISCARD_BUTTON;          //~va66I~
        else                                                       //~va66I~
			TestOption.option2&=~TO2_ROBOT_DISCARD_BUTTON;         //~va66I~
                                                                   //~va65I~
		TestOption.testCaseRonValue=Pprop.getParameter(RONVALUE_CASE,0);//~0A07I~
		TestOption.testDoraDownType=Pprop.getParameter(DORA_DOWNTYPE,0);//~0A12I~
		TestOption.testDoraDownNumber=Pprop.getParameter(DORA_DOWNNUMBER,0);//~0A12I~
		TestOption.testDoraUpType=Pprop.getParameter(DORA_UPTYPE,0);//~0A12I~
		TestOption.testDoraUpNumber=Pprop.getParameter(DORA_UPNUMBER,0);//~0A12I~
		TestOption.testKanDownType=Pprop.getParameter(DORA_KANDOWNTYPE,0);//~0A12I~
		TestOption.testKanDownNumber=Pprop.getParameter(DORA_KANDOWNNUMBER,0);//~0A12I~
		TestOption.testKanUpType=Pprop.getParameter(DORA_KANUPTYPE,0);//~0A12I~
		TestOption.testKanUpNumber=Pprop.getParameter(DORA_KANUPNUMBER,0);//~0A12I~
		TestOption.testKanUpType2=Pprop.getParameter(DORA_KANUPTYPE2,0);//~0A12I~
		TestOption.testKanUpNumber2=Pprop.getParameter(DORA_KANUPNUMBER2,0);//~0A12I~
		TestOption.testKanDownType2=Pprop.getParameter(DORA_KANDOWNTYPE2,0);//~0A12I~
		TestOption.testKanDownNumber2=Pprop.getParameter(DORA_KANDOWNNUMBER2,0);//~0A12I~
                                                                   //~0A07I~
        if (Pprop.getParameter(CHII_TEST,0)!=0)                    //~v@@@I~
			TestOption.option|=TO_CHII_TEST;                       //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option&=~TO_CHII_TEST;                      //~v@@@I~
        if (Pprop.getParameter(KAN_TEST,0)!=0)                     //~v@@@I~
			TestOption.option|=TO_KAN_TEST;                        //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option&=~TO_KAN_TEST;                       //~v@@@I~
//        if (Pprop.getParameter(POSITIONING,0)!=0)                //~v@@@R~
//            TestOption.option|=TO_POSITIONING;                   //~v@@@R~
//        else                                                     //~v@@@R~
//            TestOption.option&=~TO_POSITIONING;                  //~v@@@R~
        if (Pprop.getParameter(SKIPDICE,0)!=0)                     //~v@@@I~
			TestOption.option|=TO_SKIPDICE;                        //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option&=~TO_SKIPDICE;                       //~v@@@I~
        if (Pprop.getParameter(TAKEDISCARD,0)!=0)                  //~v@@@I~
			TestOption.option|=TO_TAKEDISCARD;                     //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option&=~TO_TAKEDISCARD;                    //~v@@@I~
        if (Pprop.getParameter(DRAWNREQDLG_LAST,0)!=0)             //~v@@@I~
			TestOption.option|=TO_DRAWNREQDLG_LAST;                //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option&=~TO_DRAWNREQDLG_LAST;               //~v@@@I~
        if (Pprop.getParameter(SHOWF2,0)!=0)                       //~v@@@I~
			TestOption.option2|=TO2_SHOWF2;                        //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option2&=~TO2_SHOWF2;                       //~v@@@I~
        if (Pprop.getParameter(FINAL_GAME,0)!=0)                   //~v@@@I~
			TestOption.option2|=TO2_FINAL_GAME;                    //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option2&=~TO2_FINAL_GAME;                   //~v@@@R~
        if (Pprop.getParameter(INITIAL_POSITION,0)!=0)             //~va66I~
			TestOption.option2|=TO2_INITIAL_POSITION;              //~va66I~
        else                                                       //~va66I~
			TestOption.option2&=~TO2_INITIAL_POSITION;             //~va66I~
        if (Pprop.getParameter(INITIAL_DUPCTR,0)!=0)               //~va66I~
			TestOption.option3|=TO3_SET_DUPCTR;                    //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_SET_DUPCTR;                   //~va66I~
        if (Pprop.getParameter(LAYOUT_FINALGAME,0)!=0)             //~v@@@I~
			TestOption.option2|=TO2_LAYOUT_FINALGAME;              //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option2&=~TO2_LAYOUT_FINALGAME;             //~v@@@I~
        if (Pprop.getParameter(DRAWNLAST_SHOW_STICK,0)!=0)         //~v@@@I~
			TestOption.option2|=TO2_DRAWNLAST_SHOW_STICK;          //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option2&=~TO2_DRAWNLAST_SHOW_STICK;         //~v@@@I~
        if (Pprop.getParameter(ROBOTBIRD,0)!=0)                    //~v@@@I~
			TestOption.option|=TO_BIRD_WITH_ROBOT;                 //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option&=~TO_BIRD_WITH_ROBOT;                //~v@@@I~
        if (Pprop.getParameter(CLOSE_ON_CONNECT,0)!=0)             //~v@@@I~
			TestOption.option|=TO_CLOSEONCONNECT;                  //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option&=~TO_CLOSEONCONNECT;                 //~v@@@I~
        if (Pprop.getParameter(RULE_NO_SYNC,0)!=0)                 //~v@@@I~
			TestOption.option|=TO_RULE_NOSYNC;                     //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option&=~TO_RULE_NOSYNC;                    //~v@@@I~
        if (Pprop.getParameter(RULE_SUSPEND,0)!=0)                 //~v@@@I~
			TestOption.option2|=TO2_SUSPEND;                       //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option2&=~TO2_SUSPEND;                      //~v@@@I~
        if (Pprop.getParameter(KANDEAL,0)!=0)                      //~v@@@I~
			TestOption.option|=TO_KAN_ADDDEAL;                      //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option&=~TO_KAN_ADDDEAL;                    //~v@@@I~
        if (Pprop.getParameter(KANDEAL_ANKAN,0)!=0)               //~0406I~//~0407R~
			TestOption.option2|=TO2_ANKAN_DEAL;                   //~0406I~//~0407R~
        else                                                       //~0406I~
			TestOption.option2&=~TO2_ANKAN_DEAL;                   //~0406I~//~0407R~
        if (Pprop.getParameter(DEAL_RONTAKEN,0)!=0)                //~va66I~
			TestOption.option3|=TO3_DEAL_RONTAKEN;                 //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_RONTAKEN;                //~va66I~
        if (Pprop.getParameter(DEAL_RONTAKEN_DOUBLEREACH,0)!=0)    //~va66I~
			TestOption.option3|=TO3_DEAL_RONTAKEN_DOUBLEREACH;     //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_RONTAKEN_DOUBLEREACH;    //~va66I~
        if (Pprop.getParameter(DEAL_RONRIVER,0)!=0)                //~va66I~
			TestOption.option3|=TO3_RON_RIVER;                     //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_RON_RIVER;                    //~va66I~
        if (Pprop.getParameter(DEAL_RONTAKEN1ST,0)!=0)             //~va66I~
			TestOption.option3|=TO3_DEAL_RONTAKEN1ST;              //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_RONTAKEN1ST;             //~va66I~
        if (Pprop.getParameter(DEAL_13NOPAIR,0)!=0)                //~va66I~
			TestOption.option3|=TO3_DEAL_13NOPAIR;                 //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_13NOPAIR;                //~va66I~
        if (Pprop.getParameter(DEAL_14NOPAIR,0)!=0)                //~va66I~
			TestOption.option3|=TO3_DEAL_14NOPAIR;                 //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_14NOPAIR;                //~va66I~
        if (Pprop.getParameter(KANDEAL_CHANKAN,0)!=0)              //~0407I~
			TestOption.option2|=TO2_CHANKAN_DEAL;                  //~0407I~
        else                                                       //~0407I~
			TestOption.option2&=~TO2_CHANKAN_DEAL;                 //~0407I~
        if (Pprop.getParameter(WAITSELECT_PON,0)!=0)               //~v@@@R~
			TestOption.option2|=TO2_WAITSELECT_PON;                //~v@@@R~
        else                                                       //~v@@@I~
			TestOption.option2&=~TO2_WAITSELECT_PON;               //~v@@@R~
        if (Pprop.getParameter(WAITSELECT_CHII,0)!=0)              //~v@@@I~
			TestOption.option2|=TO2_WAITSELECT_CHII;               //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option2&=~TO2_WAITSELECT_CHII;              //~v@@@I~
        if (Pprop.getParameter(DEAL_MULTIRON,0)!=0)                //~va66I~
			TestOption.option2|=TO2_DEAL_MULTIRON;                 //~va66I~
        else                                                       //~va66I~
			TestOption.option2&=~TO2_DEAL_MULTIRON;                //~va66I~
        if (Pprop.getParameter(DEAL_SINGLERON,0)!=0)               //~va66I~
			TestOption.option2|=TO2_DEAL_SINGLERON;                //~va66I~
        else                                                       //~va66I~
			TestOption.option2&=~TO2_DEAL_SINGLERON;               //~va66I~
                                                                   //~va66I~
        if (Pprop.getParameter(PONDEAL,0)!=0)                      //~va66I~
			TestOption.option2|=TO2_DEAL_PON;                      //~va66I~
        else                                                       //~va66I~
			TestOption.option2&=~TO2_DEAL_PON;                     //~va66I~
        if (Pprop.getParameter(CHIIDEAL,0)!=0)                     //~va66I~
			TestOption.option2|=TO2_DEAL_CHII;                     //~va66I~
        else                                                       //~va66I~
			TestOption.option2&=~TO2_DEAL_CHII;                    //~va66I~
                                                                   //~va66I~
        if (Pprop.getParameter(PONCHIIDEAL,0)!=0)                  //~va66I~
			TestOption.option2|=TO2_DEAL_PONCHII;                  //~va66I~
        else                                                       //~va66I~
			TestOption.option2&=~TO2_DEAL_PONCHII;                 //~va66I~
        if (Pprop.getParameter(DOUBLERONDEAL,0)!=0)                //~va66I~
			TestOption.option2|=TO2_DEAL_DOUBLERON;                //~va66I~
        else                                                       //~va66I~
			TestOption.option2&=~TO2_DEAL_DOUBLERON;               //~va66I~
        if (Pprop.getParameter(KANAFTERREACHDEAL,0)!=0)            //~va66I~
			TestOption.option2|=TO2_DEAL_KANAFTERREACH;            //~va66I~
        else                                                       //~va66I~
			TestOption.option2&=~TO2_DEAL_KANAFTERREACH;           //~va66I~
        if (Pprop.getParameter(KANRINSHANRONDEAL,0)!=0)            //~va66I~
			TestOption.option2|=TO2_DEAL_RINSHANRON;               //~va66I~
        else                                                       //~va66I~
			TestOption.option2&=~TO2_DEAL_RINSHANRON;           //~va66I~
                                                                   //~va66I~
        if (Pprop.getParameter(CALL1ST,0)!=0)                      //~va66I~
			TestOption.option2|=TO2_CALL1ST;                       //~va66I~
        else                                                       //~va66I~
			TestOption.option2&=~TO2_CALL1ST;                      //~va66I~
                                                                   //~va66I~
        if (Pprop.getParameter(NOREACH,0)!=0)                      //~va66I~
			TestOption.option3|=TO3_NOREACH;                       //~va66R~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_NOREACH;                      //~va66R~
        if (Pprop.getParameter(DOREACH,0)!=0)                      //~va66I~
			TestOption.option3|=TO3_ROBOT_DO_REACH;                //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_ROBOT_DO_REACH;               //~va66I~
        if (Pprop.getParameter(DEAL3DRAGON,0)!=0)                  //~va66I~
			TestOption.option3|=TO3_INTENT_3DRAGON;                //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_INTENT_3DRAGON;               //~va66I~
        if (Pprop.getParameter(DEALMULTIWAITRON,0)!=0)             //~va66I~
			TestOption.option3|=TO3_DEAL_MULTIWAITRON;             //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_MULTIWAITRON;            //~va66I~
        if (Pprop.getParameter(DEALMULTIWAITRONOK,0)!=0)           //~va66I~
			TestOption.option3|=TO3_DEAL_MULTIWAITRONOK;           //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_MULTIWAITRONOK;          //~va66I~
        if (Pprop.getParameter(DEALMULTIWAITWGR,0)!=0)             //~va66I~
			TestOption.option3|=TO3_DEAL_MULTIWAITWGR;             //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_MULTIWAITWGR;            //~va66I~
        if (Pprop.getParameter(DEALDOUBLERUNFIX,0)!=0)             //~va66I~
			TestOption.option3|=TO3_DEAL_DOUBLERUN_FIX;            //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_DOUBLERUN_FIX;           //~va66I~
        if (Pprop.getParameter(DEAL3SHIKICHII,0)!=0)               //~va66I~
			TestOption.option3|=TO3_DEAL_3SHIKI_CHII;              //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_3SHIKI_CHII;             //~va66I~
        if (Pprop.getParameter(DEALSAMECOLOR,0)!=0)                //~va66I~
			TestOption.option3|=TO3_DEAL_SAMECOLOR;                //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_SAMECOLOR;               //~va66I~
        if (Pprop.getParameter(DEALATODUKETAKESAMECOLOR,0)!=0)     //~va66I~
			TestOption.option3|=TO3_DEAL_ATODUKE_TAKE_SAMECOLOR;   //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_ATODUKE_TAKE_SAMECOLOR;  //~va66I~
        if (Pprop.getParameter(DEALSAKIDUKE2HANTAKE,0)!=0)         //+va8xI~
			TestOption.option3|=TO3_DEAL_SAKIDUKE_2HAN_TAKE;       //+va8xI~
        else                                                       //+va8xI~
			TestOption.option3&=~TO3_DEAL_SAKIDUKE_2HAN_TAKE;      //+va8xI~
        if (Pprop.getParameter(ROBOTDISCARDTILE,0)!=0)             //~va8xI~
			TestOption.option3|=TO3_ROBOT_DISCARD_TILE;            //~va8xI~
        else                                                       //~va8xI~
			TestOption.option3&=~TO3_ROBOT_DISCARD_TILE;           //~va8xI~
        if (Pprop.getParameter(DEALATODUKETAKEYAKUHAI,0)!=0)       //~va66I~
			TestOption.option3|=TO3_DEAL_ATODUKE_TAKE_YAKUHAI;     //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_ATODUKE_TAKE_YAKUHAI;    //~va66I~
        if (Pprop.getParameter(DEALANKANFIX1,0)!=0)                //~va8xI~
			TestOption.option3|=TO3_DEAL_ANKAN_FIX1;               //~va8xI~
        else                                                       //~va8xI~
			TestOption.option3&=~TO3_DEAL_ANKAN_FIX1;              //~va8xI~
        if (Pprop.getParameter(DEAL3SHIKICHIITAKE,0)!=0)           //~va66I~
			TestOption.option3|=TO3_DEAL_3SHIKI_CHII_TAKE;         //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_3SHIKI_CHII_TAKE;        //~va66I~
        if (Pprop.getParameter(DEAL3SHIKICHIING,0)!=0)             //~va66I~
			TestOption.option3|=TO3_DEAL_3SHIKI_CHIING;            //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_3SHIKI_CHIING;           //~va66I~
        if (Pprop.getParameter(DEALDOUBLERUNNOTFIX,0)!=0)          //~va66I~
			TestOption.option3|=TO3_DEAL_DOUBLERUN_NOTFIX;         //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_DOUBLERUN_NOTFIX;        //~va66I~
        if (Pprop.getParameter(DEALDOUBLERUNNOTFIXNG,0)!=0)        //~va66I~
			TestOption.option3|=TO3_DEAL_DOUBLERUN_NOTFIXNG;       //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_DOUBLERUN_NOTFIXNG;      //~va66I~
        if (Pprop.getParameter(DEALDOUBLERUNCHII,0)!=0)            //~va66I~
			TestOption.option3|=TO3_DEAL_DOUBLERUN_CHII;           //~va66I~
        else                                                       //~va66I~
			TestOption.option3&=~TO3_DEAL_DOUBLERUN_CHII;          //~va66I~
                                                                   //~va66I~
        TestOption.ioerr=Pprop.getParameter(SUSPEND_IOERR,0);      //~v@@@I~
                                                                   //~v@@@I~
        int rgFirstDealerID=Pprop.getParameter(FIRSTDEALER,0);//~v@@@I~
		TestOption.firstDealer=rgFirstDealerID;                      //~v@@@I~
        int rgFinalGameCtrSetID=Pprop.getParameter(FINALGAMECTRSET,0);//~v@@@I~
		TestOption.finalGameCtrSet=rgFinalGameCtrSetID;            //~v@@@R~
        int rgFinalGameCtrGameID=Pprop.getParameter(FINALGAMECTRGAME,0);//~v@@@I~
		TestOption.finalGameCtrGame=(rgFinalGameCtrGameID+1)*4-1;  //~v@@@R~
                                                                   //~v@@@I~
        if (Pprop.getParameter(CHANKAN,0)!=0)                      //~v@@@I~
        {                                                          //~v@@@I~
			TestOption.option|=TO_KAN_CHANKAN;                     //~v@@@I~
			TestOption.option|=TO_KAN_ADD;                         //~0A09I~
//  		TestOption.option&=~TO_KAN_TEST;                       //~v@@@R~//~0401R~//~0405R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~0A09I~
			TestOption.option&=~TO_KAN_CHANKAN;                    //~v@@@I~
			TestOption.option&=~TO_KAN_ADD;                        //~0A09I~
        }                                                          //~0A09I~
        TestOption.swDisableBT=Pprop.getParameter(DISABLEBT,0)!=0; //~v@@@I~
        TestOption.timingDisableBT=Pprop.getParameter(DISABLEBT_TIMING,0);//~v@@@I~
        if (Dump.Y) Dump.println("TODlg.setTestOption from Prop TO="+Integer.toHexString(TestOption.option));//~v@@@I~
        if (Dump.Y) Dump.println("TODlg.setTestOption from Prop TO2="+Integer.toHexString(TestOption.option2));//~v@@@I~
        if (Dump.Y) Dump.println("TODlg.setTestOption from Prop TO3="+Integer.toHexString(TestOption.option3));//~va66I~
        if (Dump.Y) Dump.println("TODlg.setTestOption firstDealer="+TestOption.firstDealer);//~v@@@I~
        if (Dump.Y) Dump.println("TODlg.setTestOption finalGameCtr set="+TestOption.finalGameCtrSet+",game="+TestOption.finalGameCtrGame);//~v@@@I~
        if (Dump.Y) Dump.println("TODlg.setTestOption timingDisableBT="+TestOption.timingDisableBT);//~v@@@R~
    }                                                              //~v@@@I~
    protected void dialog2Properties()                          //~v@@@I~
    {                                                              //~v@@@I~
    	int changed=0;                                             //~v@@@I~
        //*******************                                      //~v@@@I~
        if (Dump.Y) Dump.println("TODlg.dialog2Properties");       //~v@@@I~
        changed+=updateProp(PON_TEST,cbPon_Test.getStateInt());    //~v@@@I~
        changed+=updateProp(RON_TEST,cbRon_Test.getStateInt());    //~v@@@I~
        changed+=updateProp(DUMP_SDCARD,cbDumpSDCard.getStateInt());//~0A08I~
        changed+=updateProp(RONVALUE_TEST,cbRonValue_Test.getStateInt());//~0A02R~
        changed+=updateProp(RONVALUE_NODORA,cbRonValue_NoDora.getStateInt());//~0A08I~
        changed+=updateProp(CHKRANK,cbChkRank.getStateInt());      //~0A16I~
        changed+=updateProp(SET_DORA,cbSetDora.getStateInt());     //~0A14I~
        changed+=updateProp(RONVALUE_CASE,Integer.valueOf(etRonValue_Case.getText().toString()));//~0A07I~
        changed+=updateProp(DORA_DOWNTYPE,Integer.valueOf(etDoraDownType.getText().toString()));//~0A12I~
        changed+=updateProp(DORA_DOWNNUMBER,Integer.valueOf(etDoraDownNumber.getText().toString()));//~0A12I~
        changed+=updateProp(DORA_UPTYPE,Integer.valueOf(etDoraUpType.getText().toString()));//~0A12I~
        changed+=updateProp(DORA_UPNUMBER,Integer.valueOf(etDoraUpNumber.getText().toString()));//~0A12I~
        changed+=updateProp(DORA_KANDOWNTYPE,Integer.valueOf(etKanDownType.getText().toString()));//~0A12I~
        changed+=updateProp(DORA_KANDOWNNUMBER,Integer.valueOf(etKanDownNumber.getText().toString()));//~0A12I~
        changed+=updateProp(DORA_KANUPTYPE,Integer.valueOf(etKanUpType.getText().toString()));//~0A12I~
        changed+=updateProp(DORA_KANUPNUMBER,Integer.valueOf(etKanUpNumber.getText().toString()));//~0A12I~
        changed+=updateProp(DORA_KANDOWNTYPE2,Integer.valueOf(etKanDownType2.getText().toString()));//~0A12I~
        changed+=updateProp(DORA_KANDOWNNUMBER2,Integer.valueOf(etKanDownNumber2.getText().toString()));//~0A12I~
        changed+=updateProp(DORA_KANUPTYPE2,Integer.valueOf(etKanUpType2.getText().toString()));//~0A12I~
        changed+=updateProp(DORA_KANUPNUMBER2,Integer.valueOf(etKanUpNumber2.getText().toString()));//~0A12I~
        changed+=updateProp(CHII_TEST,cbChii_Test.getStateInt());  //~v@@@I~
        changed+=updateProp(KAN_TEST,cbKan_Test.getStateInt());    //~v@@@I~
//      changed+=updateProp(POSITIONING,cbPositioning.getStateInt());//~v@@@R~
        changed+=updateProp(SKIPDICE,cbSkipDice.getStateInt());    //~v@@@I~
        changed+=updateProp(TAKEDISCARD,cbTakeDiscard.getStateInt());//~v@@@I~
        changed+=updateProp(DRAWNREQDLG_LAST,cbDrawnReqDlg_Last.getStateInt());//~v@@@I~
        changed+=updateProp(SHOWF2,cbShowF2.getStateInt());        //~v@@@I~
        changed+=updateProp(FINAL_GAME,cbFinalGame.getStateInt()); //~v@@@I~
        changed+=updateProp(INITIAL_POSITION,cbSetPosition.getStateInt());//~va66I~
        changed+=updateProp(INITIAL_DUPCTR,cbSetDupCtr.getStateInt());//~va66I~
        changed+=updateProp(LAYOUT_FINALGAME,cbLayoutFinalGame.getStateInt());//~v@@@I~
        changed+=updateProp(DRAWNLAST_SHOW_STICK,cbDrawnLastShowStick.getStateInt());//~v@@@I~
        changed+=updateProp(FIRSTDEALER,rgFirstDealer.getCheckedID());//~v@@@I~
        changed+=updateProp(FINALGAMECTRSET,rgFinalGameCtrSet.getCheckedID());//~v@@@I~
        changed+=updateProp(FINALGAMECTRGAME,rgFinalGameCtrGame.getCheckedID());//~v@@@I~
        changed+=updateProp(CHANKAN,cbChankan.getStateInt());      //~v@@@I~
        changed+=updateProp(ROBOTBIRD,cbRobotBird.getStateInt());  //~v@@@I~
        changed+=updateProp(CLOSE_ON_CONNECT,cbCloseOnConnect.getStateInt());//~v@@@I~
        changed+=updateProp(RULE_NO_SYNC,cbRuleNoSync.getStateInt());//~v@@@I~
        changed+=updateProp(RULE_SUSPEND,cbSuspend.getStateInt()); //~v@@@I~
        changed+=updateProp(DISABLEBT,cbBTIOErr.getStateInt());    //~v@@@I~
        changed+=updateProp(KANDEAL,cbKanDeal.getStateInt());      //~v@@@I~
        changed+=updateProp(PONDEAL,cbPonDeal.getStateInt());      //~va66I~
        changed+=updateProp(CHIIDEAL,cbChiiDeal.getStateInt());    //~va66I~
        changed+=updateProp(PONCHIIDEAL,cbPonChiiDeal.getStateInt());//~va66I~
        changed+=updateProp(DOUBLERONDEAL,cbDoubleRonDeal.getStateInt());//~va66I~
        changed+=updateProp(KANAFTERREACHDEAL,cbKanAfterReachDeal.getStateInt());//~va66I~
        changed+=updateProp(KANRINSHANRONDEAL,cbKanRinshanRonDeal.getStateInt());//~va66I~
        changed+=updateProp(CALL1ST,cbCall1st.getStateInt());      //~va66I~
        changed+=updateProp(NOREACH,cbNoReach.getStateInt());      //~va66I~
        changed+=updateProp(DOREACH,cbDoReach.getStateInt());      //~va66I~
        changed+=updateProp(DEAL3DRAGON,cbDeal3Dragon.getStateInt());//~va66I~
        changed+=updateProp(DEALMULTIWAITRON,cbDealMultiWaitRon.getStateInt());//~va66I~
        changed+=updateProp(DEALMULTIWAITRONOK,cbDealMultiWaitRonOK.getStateInt());//~va66I~
        changed+=updateProp(DEALMULTIWAITWGR,cbDealMultiWaitWGR.getStateInt());//~va66I~
        changed+=updateProp(DEALDOUBLERUNFIX,cbDealDoubleRunFix.getStateInt());//~va66I~
        changed+=updateProp(DEALDOUBLERUNNOTFIX,cbDealDoubleRunNotFix.getStateInt());//~va66I~
        changed+=updateProp(DEALDOUBLERUNNOTFIXNG,cbDealDoubleRunNotFixNG.getStateInt());//~va66I~
        changed+=updateProp(DEALDOUBLERUNCHII,cbDealDoubleRunChii.getStateInt());//~va66I~
        changed+=updateProp(DEAL3SHIKICHII,cbDeal3ShikiChii.getStateInt());//~va66I~
        changed+=updateProp(DEALSAMECOLOR,cbDealSameColor.getStateInt());//~va66I~
        changed+=updateProp(DEALATODUKETAKESAMECOLOR,cbDealAtodukeTakeSameColor.getStateInt());//~va66I~
        changed+=updateProp(DEALSAKIDUKE2HANTAKE,cbDealSakiduke2hanTake.getStateInt());//+va8xI~
        changed+=updateProp(ROBOTDISCARDTILE,cbRobotDiscardTile.getStateInt());//~va8xI~
        changed+=updateProp(DEALATODUKETAKEYAKUHAI,cbDealAtodukeTakeYakuhai.getStateInt());//~va66I~
        changed+=updateProp(DEALANKANFIX1,cbDealAnkanFix1.getStateInt());//~va8xI~
        changed+=updateProp(DEAL3SHIKICHIITAKE,cbDeal3ShikiChiiTake.getStateInt());//~va66I~
        changed+=updateProp(DEAL3SHIKICHIING,cbDeal3ShikiChiiNG.getStateInt());//~va66I~
        changed+=updateProp(KANDEAL_ANKAN,cbKanDealAnkan.getStateInt());//~0406I~//~0407R~
        changed+=updateProp(DEAL_RONTAKEN,cbDealRonTaken.getStateInt());//~va66I~
        changed+=updateProp(DEAL_RONTAKEN_DOUBLEREACH,cbDealRonTakenDouble.getStateInt());//~va66I~
        changed+=updateProp(DEAL_RONRIVER,cbRonRiver.getStateInt());//~va66I~
        changed+=updateProp(DEAL_RONTAKEN1ST,cbDealRonTaken1st.getStateInt());//~va66I~
        changed+=updateProp(DEAL_13NOPAIR,cbDeal13NoPair.getStateInt());//~va66I~
        changed+=updateProp(DEAL_14NOPAIR,cbDeal14NoPair.getStateInt());//~va66I~
        changed+=updateProp(KANDEAL_CHANKAN,cbKanDealChankan.getStateInt());//~0407I~
        changed+=updateProp(OPENHAND,cbOpenHand.getStateInt());    //~va65I~
        changed+=updateProp(ROBOT_DISCARD_BUTTON,cbRobotDiscardButton.getStateInt());//~va66I~
        changed+=updateProp(DEAL_MULTIRON,cbDealMultiRon.getStateInt());//~va66I~
        changed+=updateProp(DEAL_SINGLERON,cbDealSingleRon.getStateInt());//~va66I~
        changed+=updateProp(DISABLEBT_TIMING,rgBTIOErr.getCheckedID());//~v@@@I~
        changed+=updateProp(SUSPEND_IOERR,getEswnCB(llIOErr));      //~v@@@I~
        changed+=updateProp(WAITSELECT_PON,cbWaitSelectPon.getStateInt());//~v@@@R~
        changed+=updateProp(WAITSELECT_CHII,cbWaitSelectChii.getStateInt());//~v@@@I~
        swChanged=changed!=0;                                      //~v@@@I~
        if (Dump.Y) Dump.println("TODlg.dialog2Properties swChanged="+swChanged);//~v@@@I~
        if (Dump.Y) Dump.println("TODlg.dialog2Properties prop="+prop.toString());//~v@@@I~
    }                                                              //~v@@@I~
//    //*******************************************************    //~v@@@R~
//    private void saveProperties()                                //~v@@@R~
//    {                                                            //~v@@@R~
//            String workDirSD=UFile.getSDPath(""); //"sdpath/appname"//~v@@@R~
//            String fpath=UFile.makePathName(PROPFILE,workDirSD); //~v@@@R~
//            if (Dump.Y) Dump.println("TODlg.saveProperties workdir="+workDirSD+",fpath="+fpath);//~v@@@R~
//            prop.saveProperties(fpath,"TestOption");             //~v@@@R~
//    }                                                            //~v@@@R~
    //*******************************************************      //~v@@@I~
    private void saveProperties()                                  //~v@@@I~
    {                                                              //~v@@@I~
        prop.savePropDataFile("TestOption");                       //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onClickOK()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("TODlg.onClickOK");               //~v@@@I~
	    dialog2Properties();                                       //~v@@@I~
	    setTestOption(prop);                                       //~v@@@I~
        if (swChanged)                                             //~v@@@I~
        {                                                          //~v@@@I~
			saveProperties();	//save to current.rule             //~v@@@I~
        }                                                          //~v@@@I~
        dismiss();                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    public static void prop2TO()                                   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("TODlg.prop2TO");                 //~v@@@I~
        Prop p=loadProp();                                         //~v@@@I~
	    setTestOption(p);                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    private void setEswnCB(View PView,int Pvalue)                  //~v@@@I~
    {                                                              //~v@@@I~
    	CheckBox cb;                                               //~v@@@I~
        if (Dump.Y) Dump.println("TODlg.setEswnCB value="+Integer.toHexString(Pvalue));//~v@@@I~
    	cb=(CheckBox)UView.findViewById(PView,R.id.cbAbPE);        //~v@@@I~
        if (cb!=null)                                              //~v@@@I~
			cb.setChecked((Pvalue & 0x01)!=0);                     //~v@@@I~
    	cb=(CheckBox)UView.findViewById(PView,R.id.cbAbPS);        //~v@@@I~
        if (cb!=null)                                              //~v@@@I~
			cb.setChecked((Pvalue & 0x02)!=0);                     //~v@@@I~
    	cb=(CheckBox)UView.findViewById(PView,R.id.cbAbPW);        //~v@@@I~
        if (cb!=null)                                              //~v@@@I~
			cb.setChecked((Pvalue & 0x04)!=0);                     //~v@@@I~
    	cb=(CheckBox)UView.findViewById(PView,R.id.cbAbPN);        //~v@@@I~
        if (cb!=null)                                              //~v@@@I~
			cb.setChecked((Pvalue & 0x08)!=0);                     //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    private int getEswnCB(View PView)                              //~v@@@I~
    {                                                              //~v@@@I~
    	int val=0;                                                 //~v@@@I~
    	CheckBox cb;                                               //~v@@@I~
    	cb=(CheckBox)UView.findViewById(PView,R.id.cbAbPE);        //~v@@@I~
        if (cb!=null && cb.isChecked())                            //~v@@@I~
			val|=0x01;                                             //~v@@@I~
    	cb=(CheckBox)UView.findViewById(PView,R.id.cbAbPS);        //~v@@@I~
        if (cb!=null && cb.isChecked())                            //~v@@@I~
			val|=0x02;                                             //~v@@@I~
    	cb=(CheckBox)UView.findViewById(PView,R.id.cbAbPW);        //~v@@@I~
        if (cb!=null && cb.isChecked())                            //~v@@@I~
			val|=0x04;                                             //~v@@@I~
    	cb=(CheckBox)UView.findViewById(PView,R.id.cbAbPS);        //~v@@@I~
        if (cb!=null && cb.isChecked())                            //~v@@@I~
			val|=0x08;                                             //~v@@@I~
        if (Dump.Y) Dump.println("TODlg.getEswnCB val="+Integer.toHexString(val));
        return val;//~v@@@I~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
