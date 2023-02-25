//*CID://+vax1R~:                             update#=  576;       //~vax1R~
//*****************************************************************//~v101I~
//2023/02/22 vax1 add local 3DupSeq(Pure Triple Chow)              //~vax1I~
//2023/02/22 vax0 add local 3Wind:2han                             //~vax0I~
//2023/02/22 vawz 3WindNoHonor; optionally 3/2 han allow RYAKU_ROUND//~vawzI~
//2023/02/21 vawv add local yaku. 3SeqNum                          //~vawvI~
//2023/02/18 vaws set AG.aUARanc for instrumentTest                //~vawsI~
//2023/02/10 vawg add local yaku. 3ColorStraight                   //~vawgI~
//2023/02/10 vaw9 add local yaku. 3Wind-NoHonor                    //~vaw9I~
//2023/02/10 vaw8 add local yaku. SINGLE                           //~vaw8I~
//2023/02/10 vaw6 add local yaku. 7STAR                            //~vaw6I~
//2023/02/10 vaw5 add local yaku. 4SEQNNUM                         //~vaw5I~
//2023/02/02 vaw4 add local yaku. 7PAIR28_MAN/7PAIR28_SOU          //~vaw4I~
//2022/10/07 varb (Bug)dump when prop is initial at received rule(UButtonRG default is -1)//~varbI~
//2022/09/09 var1 summary rule setting dialog;drop yakuman         //~var1I~
//2022/09/03 var0 summary rule setting dialog                      //~var0I~
//2022/08/13 vaq3 implements Yakuman 8continued                    //~vaq3I~
//2022/08/06 vapx add Psuedo-Tennpai:No option                     //~vapxI~
//2022/08/04 vapu PsuedoTenpai;simplify 0han ok or not(allow kataagari,fix last)//~vapuI~
//2022/08/04 vapt PsuedoTenpai;allow kataagari,fix yaku err if furiten OK//~vaptI~
//2022/08/03 vapp Psuedo tenpai; drop allNo(chk tenpai required for repeat/next round)//~vappI~
//2022/07/30 vapk implements keishiki tenpai                       //~vapkI~
//2022/07/24 vap5 OpenReach Robot option change; chkbox No(default)//~vap5I~
//2022/07/23 vap3 Yakuman for discarding OpenReach winning tile    //~vap3I~
//2022/03/19 vakR On client, dismiss child dialog of RuleSetting when receved from server//~vakRI~
//2022/03/19 vakQ notify update of rule when client received       //~vakQI~
//2022/02/20 vaka apply kataagari tsumo option                     //~vakaI~
//2022/02/16 vak3 delete option kataagari with fixLast(always allow kataagari with FixLast)//~vak3I~
//2022/01/18 vaj0 isFixed1() returns true if not sakiduke mode     //~vaj0I~
//2021/11/22 vah3 add Furiten reach reject option                  //~vah3I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2021/06/26 vaa0 support <img> in htmlText                        //~vaa0I~
//2021/06/15 va98 allow multiwait for take with allInHand          //~va98I~
//2021/06/06 va91 sakizukechk for robot                            //~va91I~
//2021/04/25 va8k KataAgari OK for all Draw(+pon/kan/chii) regardless fix option//~va8kI~
//2021/04/20 va8j KataAgari chk(No furiten chk) for also Human Take in PlayAloneNotifyMode//~va8jI~
//2021/04/18 va8f KataAgari chk                                    //~va8fI~
//2021/04/17 va8c for robot ron,2 hancontraint should ignore rinshan,haitei,one shot//~va8cI~
//2021/04/13 va86 show RonAnyWay button by not preference by operation rule//~va86I~
//2021/03/09 va6c change keiten default:allow all                  //~va6cI~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//2020/09/25 va12:add option:2han-30fu for 7pair                   //~va12I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                          //~v@@@R~
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.btmtest.R;
import com.btmtest.gui.UButtonRG;
import com.btmtest.gui.URadioGroup;
import com.btmtest.utils.Prop;                                     //~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.USpinner;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.dialog.CompReqDlg.*;
import static com.btmtest.dialog.HelpDialog.*;
import static com.btmtest.dialog.RuleSettingEnum.*;                //~v@@@I~
import static com.btmtest.game.GConst.*;

public class RuleSettingYaku extends UFDlg                         //~v@@@R~
            implements UCheckBox.UCheckBoxI                        //~va6cI~
{                                                                  //~2C29R~
  	private static final int    TITLEID=R.string.Label_YakuList;   //~v@@@R~
	private static final int    LAYOUTID=R.layout.setting_rule_yaku;//~v@@@R~
	private static final int    LAYOUTID_SMALLFONT=R.layout.setting_rule_yaku_theme;//~vac5I~
	private static final int    HELP_TITLEID=TITLEID;              //~v@@@I~
	private static final String HELPFILE="RuleSettingYaku";        //~v@@@R~
    //**********************************************************   //~v@@@R~
    private UCheckBox cbKuitan,cbPinfuTaken;                       //~v@@@R~
    private UCheckBox cbShowAnywayBtn;                             //~va86I~
    private UCheckBox cbDoublePillow;                              //~va11I~
    private UCheckBox cb8ContNoNeedYaku,cb8ContReset,cb8ContMulti; //~v@@@R~
    private UCheckBox cbYakuMan2_4Anko,cbYakuMan2_Kokusi13,cbYakuMan2_9Ren9,cbYakuMan2_4Wind;//~v@@@I~
//  private UCheckBox cb13NoPair,cb14NoPair;                         //~v@@@I~//~va11R~
//  private UCheckBox cbYakuFix2Last;                              //~v@@@R~
    private UCheckBox cbAllGreenNoBlue,cb9RenPinSou,cbNoPair13,cbBigRing,cbRank13,cbRenhoMix,cbKokusiAnkanRon;//~v@@@R~
    private UCheckBox cbBigRingNotPin;                             //~vaw4I~
    private UCheckBox cb4SeqNum;                                   //~vaw5R~
    private UCheckBox cbSingle;                                    //~vaw8I~
    private UCheckBox cb3WindNoHonor;                              //~vaw9I~
    private UCheckBox cb3Wind;                                     //~vax0I~
    private UCheckBox cb3DupSeq;                                   //~vax1I~
    private UCheckBox cb3DupSeqAllowOpen;                          //~vax1I~
    private UCheckBox cb3WindNoHonorAllowRound;                    //~vawzI~
    private URadioGroup rg3WNH_Han;                                //~vawzI~
    private URadioGroup rg3DupSeq_Han;                             //~vax1I~
    private UCheckBox cb3ColorStraight;                            //~vawgI~
    private UCheckBox cb3SeqNum;                                   //~vawvI~
    private UCheckBox cb7Star;                                     //~vaw6I~
    private UCheckBox cbNoPair14;                                  //~va11I~
    private UCheckBox /*cbOpenReachRon,*/cb5thKan,cbPendingRankNo;      //~v@@@R~//~0329R~//~0330R~
    private UCheckBox cbPendingRankEmpty,cbPendingRankFuriten,cbPendingRank0OK;//~0330I~
    private UCheckBox cbPendingRankFixMulti;                       //~vaptI~
    private URadioGroup rgPendingRank2;                            //~0330I~
    private URadioGroup rgDrawnMangan;                             //~v@@@I~
    private URadioGroup rgOpenReachRobot,rgOpenReach;              //~0329I~
    private UCheckBox cbOpenReachRobot;                            //~vap5I~
    private URadioGroup rgYaku7Pair;                               //~v@@@I~
    private UCheckBox cbYaku7Pair4Pair;                            //~v@@@I~
    private USpinner spnDrawnManganRank;                           //~v@@@I~
    private USpinner spnRenhoRank;                                 //~v@@@I~
    private UButtonRG bg8Continue;                                 //~v@@@I~
    private URadioGroup rgYakuFix,rgYakuFix2;                      //~v@@@R~
    private URadioGroup rgYakuFixMultiwaitTake;                    //~va91I~
    private URadioGroup rgFuritenReach;                            //~vah3I~
//  private UCheckBox  cbYakuFix1;                                 //~va11R~
    private UCheckBox  cbOpenReach,cbMissingReach,cbAnkanAfterReach;//~v@@@R~
    private UCheckBox  cbOneShot;                                  //~va11I~
    private UCheckBox  cbYakuFixMultiwaitOK/*,cbYakuFixMultiwaitDrawOK*/;//~0208R~//~va8kR~
    //**********************************************************   //~v@@@I~
    private RuleSetting RSD;                                       //~v@@@I~
    private Prop curProp;                                          //~v@@@I~
    private boolean swChanged,swFixed;                             //~v@@@R~
    private boolean swReceived;                                    //~vakQI~
//  private boolean swNonAll;                                      //~vapkR~
    //******************************************                   //~v@@@M~
	public RuleSettingYaku()                                       //~v@@@R~
    {
        if (Dump.Y) Dump.println("RuleSettingYaku.defaultConstructor");//~v@@@R~
    }
    public static RuleSettingYaku newInstance(RuleSetting Pparent) //~v@@@R~
    {                                                              //~v@@@I~
        RuleSettingYaku dlg=new RuleSettingYaku();                 //~v@@@R~
//      UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~v@@@R~//~vac5R~
        UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),//~vac5I~
                    UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN,//~v@@@I~
                    HELP_TITLEID,HELPFILE);                        //~v@@@R~
        dlg.RSD=Pparent;                                           //~v@@@I~
        Pparent.aRuleSettingYaku=dlg;                              //~vakRI~
        return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@M~
    @Override
    protected void initLayout(View PView)                            //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("RuleSettingYaku.initLayout");    //~v@@@R~
        swFixed=RSD.swFixed;                                       //~v@@@I~
        swReceived=RSD.swReceived;                                 //~vakQI~
        RSD.swChildInitializing=true;                              //~v@@@I~
        super.initLayout(PView);                                   //~v@@@I~
        setupLayout(PView);                                        //~v@@@I~
        setInitialValue();                                         //~v@@@I~
        RSD.swChildInitializing=false;                             //~v@@@I~
    }                                                              //~v@@@M~
    //******************************************                   //~9819I~//~v@@@I~
	@Override                                                      //~9819I~//~v@@@I~
    protected int getDialogWidth()                                 //~9819I~//~v@@@I~
    {                                                              //~9819I~//~v@@@I~
    	int ww=(int)(getDialogWidthSmallDevicePortrait()*RATE_SMALLDEVICE_WIDTH);//~9819R~//~v@@@I~
    	if (Dump.Y) Dump.println("RuleSettingYaku.getDialogWidth swSmallDevice="+AG.swSmallDevice+",ww="+ww);//~9819I~//~v@@@I~
        return ww;                                                 //~9819I~//~v@@@I~
    }                                                              //~9819I~//~v@@@I~
    //******************************************                   //~vakRI~
    @Override                                                      //~vakRI~
    public void onDismissDialog()                                  //~vakRI~
    {                                                              //~vakRI~
        if (Dump.Y) Dump.println("RuleSettingYaku.onDismissDialog");//~vakRI~
        RSD.aRuleSettingYaku=null;                                 //~vakRI~
    }                                                              //~vakRI~
	//***********************************************************      //~1613I~//~v@@@R~
    private void setupLayout(View PView)                           //~v@@@I~
    {                                                              //~v@@@I~
        TextView tvStatementOnly=(TextView)  UView.findViewById(PView,R.id.tvYakuStatementOnly);//~va11I~
        tvStatementOnly.setText(Utils.getStrHtml(R.string.Desc_YakuStatementOnly));//~va11I~
        cbKuitan         =          new UCheckBox(PView,R.id.cbKuitan);//~v@@@R~
        cbShowAnywayBtn  =          new UCheckBox(PView,R.id.cbShowAnywayBtn);//~va86I~
        cbDoublePillow   =          new UCheckBox(PView,R.id.cbDoublePillow);//~va11I~
        cbPinfuTaken     =          new UCheckBox(PView,R.id.cbPinfuTaken);//~v@@@I~
        cbPendingRankNo   =         new UCheckBox(PView,R.id.cbPendingRankNo);//~v@@@I~//~0330R~
        cbPendingRankEmpty=         new UCheckBox(PView,R.id.cbPendingRankEmpty);//~0330I~
        cbPendingRankFuriten=       new UCheckBox(PView,R.id.cbPendingRankFuriten);//~0330I~
        cbPendingRankFixMulti=      new UCheckBox(PView,R.id.cbPendingRankFixMulti);//~vaptI~
        cbPendingRank0OK    =       new UCheckBox(PView,R.id.cbPendingRank0OK);//~0330I~
    	rgPendingRank2=new URadioGroup(PView,R.id.rgPendingRank2,0,rbIDPendingRank2);//~0330I~
    	setCBListener(cbPendingRankNo,R.id.cbPendingRankNo);       //~va6cI~
//        setCBListener(cbPendingRank0OK,R.id.cbPendingRank0OK);   //~vapkR~
//        setCBListener(cbPendingRankEmpty,R.id.cbPendingRankEmpty);//~vapkR~
//        setCBListener(cbPendingRankFuriten,R.id.cbPendingRankFuriten);//~vapkR~
    //*drawnMangan                                                 //~v@@@I~
        rgDrawnMangan=new URadioGroup(PView,R.id.rgDrawnMangan,0,rbIDDrawnMangan);//~v@@@I~
        spnDrawnManganRank =new USpinner(PView,R.id.spnDrawnManganRank);//~v@@@I~
        spnDrawnManganRank.setArray(rankDrawnMangan);              //~v@@@I~
    //*8Continue                                                   //~v@@@I~
        bg8Continue=new UButtonRG((ViewGroup)PView,rbs8Continue.length);//~v@@@I~
	    for (int ii=0;ii<rbs8Continue.length;ii++)                 //~v@@@I~
			bg8Continue.add(ID8Continue[ii],rbs8Continue[ii]);     //~v@@@I~
        bg8Continue.setDefaultChk(Y8C_DEFAULT);                    //~v@@@I~
    	cb8ContNoNeedYaku=new UCheckBox(PView,R.id.cb8ContNoNeedYaku);//~v@@@I~
    	cb8ContReset=new UCheckBox(PView,R.id.cb8ContReset);       //~v@@@R~
    	cb8ContMulti=new UCheckBox(PView,R.id.cb8ContMulti);       //~v@@@I~
    //*YakuFix                                                     //~v@@@I~
        rgYakuFix=new URadioGroup(PView,R.id.rgYakuFix,0,rbsYakuFix);//~v@@@I~
        rgYakuFixMultiwaitTake=new URadioGroup(PView,R.id.rgYakuFixMultiwaitTake,0,rbsYakuFixMultiwaitTake);//~va91I~
    	cbYakuFixMultiwaitOK=new UCheckBox(PView,R.id.cbYakuFixMultiwaitOK);//~0208I~
//  	cbYakuFixMultiwaitDrawOK=new UCheckBox(PView,R.id.cbYakuFixMultiwaitDrawOK);//~0208I~//~va8kR~
    //*YakuFix1                                                    //~va11I~
//  	cbYakuFix1=new UCheckBox(PView,R.id.cbYakuFix1);           //~va11R~
    //*YakuFix2                                                    //~v@@@I~
        rgYakuFix2=new URadioGroup(PView,R.id.rgYakuFix2,0,rbsYakuFix2);//~v@@@I~
//  	cbYakuFix2Last=new UCheckBox(PView,R.id.cbYakuFix2Last);   //~v@@@R~
    //*7Pair                                                       //~v@@@I~
        rgYaku7Pair=new URadioGroup(PView,R.id.rgYaku7Pair,0,rbsYaku7Pair);//~v@@@I~
    	cbYaku7Pair4Pair=new UCheckBox(PView,R.id.cbYaku7Pair4Pair);//~v@@@I~
    //*Yakuman2                                                    //~v@@@I~
    	cbYakuMan2_4Anko=new UCheckBox(PView,R.id.cbYakuMangan8_4AnkoTanki);//~v@@@I~
    	cbYakuMan2_Kokusi13=new UCheckBox(PView,R.id.cbYakuMangan8_Kokusi13);//~v@@@I~
    	cbYakuMan2_9Ren9=new UCheckBox(PView,R.id.cbYakuMangan8_9Ren9);//~v@@@I~
    	cbYakuMan2_4Wind=new UCheckBox(PView,R.id.cbYakuMangan8_4Wind);//~v@@@I~
    //*Yakuman                                                     //~v@@@I~
    	cbAllGreenNoBlue=new UCheckBox(PView,R.id.cbYakuman_AllGreenNoBlue);//~v@@@I~
    	cb9RenPinSou=new UCheckBox(PView,R.id.cbYakuman_9RenPinSou);//~v@@@I~
    	cbNoPair13=new UCheckBox(PView,R.id.cbYakuman_NoPair13);   //~v@@@I~
    	cbNoPair14=new UCheckBox(PView,R.id.cbYakuman_NoPair14);   //~va11I~
    	cbBigRing=new UCheckBox(PView,R.id.cbYakuman_BigRing);     //~v@@@I~
    	cbBigRingNotPin=new UCheckBox(PView,R.id.cbYakuman_BigRingNotPin);//~vaw4I~
    	cb4SeqNum=new UCheckBox(PView,R.id.cbYakuman_4SeqNum);     //~vaw5R~
    	cbSingle=new UCheckBox(PView,R.id.cbSingle);               //~vaw8I~
    	cb3WindNoHonor=new UCheckBox(PView,R.id.cb3WindNoHonor);   //~vaw9I~
    	cb3Wind=new UCheckBox(PView,R.id.cb3Wind);                 //~vax0I~
    	cb3DupSeq=new UCheckBox(PView,R.id.cb3DupSeq);             //~vax1I~
    	cb3DupSeqAllowOpen=new UCheckBox(PView,R.id.cb3DupSeqAllowOpen);//~vax1I~
    	cb3WindNoHonorAllowRound=new UCheckBox(PView,R.id.cb3WindNoHonorAllowRound);//~vawzI~
        rg3WNH_Han=new URadioGroup(PView,R.id.rg3WNH_Han,0,rbs3WNH_Han);//~vawzI~
        rg3DupSeq_Han=new URadioGroup(PView,R.id.rg3DupSeq_Han,0,rbs3DupSeq_Han);//~vax1I~
    	cb3ColorStraight=new UCheckBox(PView,R.id.cb3ColorStraight);//~vawgI~
    	cb3SeqNum=new UCheckBox(PView,R.id.cb3SeqNum);             //~vawvI~
    	cb7Star=new UCheckBox(PView,R.id.cbYakuman_7Star);         //~vaw6I~
    	cbRank13=new UCheckBox(PView,R.id.cbYakuman_Rank13);       //~v@@@I~
    	cbRenhoMix=new UCheckBox(PView,R.id.cbYakuman_RenhoMix);   //~v@@@I~
    	cbKokusiAnkanRon=new UCheckBox(PView,R.id.cbYakuman_KokusiAnkanRon);//~v@@@I~
//  	cbOpenReachRon=new UCheckBox(PView,R.id.cbYakuman_OpenReachRon);//~v@@@I~//~0329R~
    	cb5thKan=new UCheckBox(PView,R.id.cbYakuman_5thKan);       //~v@@@I~
//  	cb13NoPair=new UCheckBox(PView,R.id.cbYakuman_13NoPair);   //~v@@@I~//~va11R~
//  	cb14NoPair=new UCheckBox(PView,R.id.cbYakuman_14NoPair);   //~v@@@I~//~va11R~
        spnRenhoRank=new USpinner(PView,R.id.spnRenhoRank);        //~v@@@I~
        spnRenhoRank.setArray(rankRenho);                          //~v@@@I~
    //*Reach                                                       //~v@@@I~
        cbOpenReach=new UCheckBox(PView,R.id.cbOpenReach);         //~v@@@I~
//      cbMissingReach=new UCheckBox(PView,R.id.cbMissingReach);   //~v@@@I~//~vah3R~
        rgFuritenReach=new URadioGroup(PView,R.id.rgFuritenReach,0,rbsFuritenReach);   //~vah3I~
        cbOneShot=new UCheckBox(PView,R.id.cbOneShot);             //~va11I~
    	cbAnkanAfterReach=new UCheckBox(PView,R.id.cbAnkanAfterReach);//~v@@@I~
        rgOpenReach=new URadioGroup(PView,R.id.rgOpenReach,0/*listenerParm*/,rbsOpenReach);//~0329I~
        rgOpenReachRobot=new URadioGroup(PView,R.id.rgOpenReachRobot,0/*listenerParm*/,rbsOpenReachRobot);//~0329I~
        cbOpenReachRobot=new UCheckBox(PView,R.id.cbOpenReachRobot);//~vap5I~
    }                                                              //~v@@@I~
	//***********************************************************  //~v@@@I~
    private void setInitialValue()                                 //~v@@@I~
    {                                                              //~v@@@I~
    	curProp=RSD.curProp;                                       //~v@@@I~
	    properties2Dialog(curProp);                                 //~v@@@I~
        if (swReceived)                                            //~vakQI~
            chkUpdate();                                           //~vakQI~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@M~
    @Override                                                      //~v@@@M~
    public void onClickOK()                                        //~v@@@M~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("RuleSettingYaku.onClickOK");     //~v@@@M~
	    getValue();                                                //~v@@@I~
        if (swChanged)                                             //~v@@@I~
	        RSD.swChangedYaku=swChanged;                           //~v@@@R~
        dismiss();                                                 //~v@@@M~
    }                                                              //~1602M~//~v@@@I~
    //*******************************************************      //~vaa0I~
    @Override                                                      //~vaa0I~
    public void onClickHelp()                                      //~vaa0I~
    {                                                              //~vaa0I~
        if (Dump.Y) Dump.println("RuleSettingYaku.onClickHelp");   //~vaa0I~
    	HelpDialog.newInstance(HELP_TITLEID,HELPFILE,HELP_BG_IMAGE,HELP_FG_IMAGE).show();//~vaa0I~
    }                                                              //~vaa0I~
	//***********************************************************  //~v@@@I~
    private void getValue()                                        //~v@@@I~
    {                                                              //~v@@@I~
	    swChanged=dialog2Properties();                             //~v@@@I~
    }                                                              //~v@@@I~
	//***********************************************************  //~v@@@I~
    private void properties2Dialog(Prop Pprop)                     //~v@@@I~
    {                                                              //~v@@@I~
        cbKuitan.setStateInt(Pprop.getParameter(getKeyRS(RSID_KUITAN),0),swFixed);//~v@@@I~
        cbShowAnywayBtn.setStateInt(Pprop.getParameter(getKeyRS(RSID_SHOW_ANYWAY_BTN),0),swFixed);//~va86I~
        cbDoublePillow.setStateInt(Pprop.getParameter(getKeyRS(RSID_DOUBLE_PILLOW),0),swFixed);//~va11I~
        cbPinfuTaken.setStateInt(Pprop.getParameter(getKeyRS(RSID_PINFUTAKEN),0),swFixed);//~v@@@I~
//      cbPendingRankNo.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_RANKNO),1),swFixed);//~v@@@I~//~0330R~//~va6cR~
//      cbPendingRankEmpty.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_RANKEMPTY),0),swFixed);//~0330R~//~va6cR~
//      cbPendingRankFuriten.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_RANKFURITEN),0),swFixed);//~0330R~//~va6cR~
//      cbPendingRank0OK.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_RANK0),0),swFixed);//~0330R~//~va6cR~
        cbPendingRankNo.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_RANKNO),0),swFixed);//~va6cI~
        cbPendingRankEmpty.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_RANKEMPTY),1),swFixed);//~va6cI~
        cbPendingRankFuriten.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_RANKFURITEN),1),swFixed);//~va6cI~
     //   cbPendingRankFixMulti.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_RANKFIXMULTI),1),swFixed);//~vaptI~
        cbPendingRank0OK.setStateInt(Pprop.getParameter(getKeyRS(RSID_PENDING_RANK0),1),swFixed);//~va6cI~
        rgPendingRank2.setCheckedID(Pprop.getParameter(getKeyRS(RSID_PENDING_RANK2),PENDING_RANK2_DEFAULT),swFixed);//~0330I~
    //*drawnMangan                                                 //~v@@@I~
        rgDrawnMangan.setCheckedID(Pprop.getParameter(getKeyRS(RSID_DRAWN_MANGAN_TYPE),DRAWN_MANGAN_DEFAULT),swFixed);//~v@@@I~
        spnDrawnManganRank.select(Pprop.getParameter(getKeyRS(RSID_DRAWN_MANGAN_RANK),0),swFixed);//~v@@@I~
    //*8Continue                                                   //~v@@@I~
        bg8Continue.setChecked(Pprop.getParameter(getKeyRS(RSID_8CONTINUE),Y8C_DEFAULT),swFixed);//~v@@@I~
    	cb8ContNoNeedYaku.setStateInt(Pprop.getParameter(getKeyRS(RSID_8CONT_NONEEDYAKU),1),swFixed);//~v@@@R~
    	cb8ContReset.setStateInt(Pprop.getParameter(getKeyRS(RSID_8CONT_RESET),0),swFixed);//~v@@@R~
    	cb8ContMulti.setStateInt(Pprop.getParameter(getKeyRS(RSID_8CONT_MULTI),1),swFixed);//~v@@@I~
    //*YakuFix                                                     //~v@@@I~
        rgYakuFix.setCheckedID(Pprop.getParameter(getKeyRS(RSID_YAKUFIX),YAKUFIX_DEFAULT),swFixed);//~v@@@I~
        rgYakuFixMultiwaitTake.setCheckedID(Pprop.getParameter(getKeyRS(RSID_YAKUFIX_TAKE),YAKUFIX_TAKE_DEFAULT),swFixed);//~va91I~
    	cbYakuFixMultiwaitOK.setStateInt(Pprop.getParameter(getKeyRS(RSID_YAKUFIX_MULTIWAITOK),0),swFixed);//~0208I~
//  	cbYakuFixMultiwaitDrawOK.setStateInt(Pprop.getParameter(getKeyRS(RSID_YAKUFIX_MULTIWAITDRAWOK),0),swFixed);//~0208I~//~va8kR~
    //*YakuFix1                                                    //~va11I~
//  	cbYakuFix1.setStateInt(Pprop.getParameter(getKeyRS(RSID_YAKUFIX1),0),swFixed);//~va11R~
    //*YakuFix2                                                    //~v@@@I~
        rgYakuFix2.setCheckedID(Pprop.getParameter(getKeyRS(RSID_YAKUFIX2),YAKUFIX2_DEFAULT),swFixed);//~v@@@I~
//  	cbYakuFix2Last.setStateInt(Pprop.getParameter(getKeyRS(RSID_YAKUFIX2LAST),1),swFixed);//~v@@@R~
    //*7Pair                                                       //~v@@@I~
        rgYaku7Pair.setCheckedID(Pprop.getParameter(getKeyRS(RSID_7PAIR),YAKU7PAIR_DEFAULT),swFixed);//~v@@@I~
    	cbYaku7Pair4Pair.setStateInt(Pprop.getParameter(getKeyRS(RSID_7PAIR4PAIR),0),swFixed);//~v@@@I~
    //*Yakuman2                                                    //~v@@@I~
    	cbYakuMan2_4Anko.setStateInt(Pprop.getParameter(getKeyRS(RSID_4ANKO1),1),swFixed);//~v@@@R~
    	cbYakuMan2_Kokusi13.setStateInt(Pprop.getParameter(getKeyRS(RSID_KOKUSI13),1),swFixed);//~v@@@R~
    	cbYakuMan2_9Ren9.setStateInt(Pprop.getParameter(getKeyRS(RSID_9REN9),1),swFixed);//~v@@@R~
    	cbYakuMan2_4Wind.setStateInt(Pprop.getParameter(getKeyRS(RSID_4WIND),1),swFixed);//~v@@@R~
    //*Yakuman                                                     //~v@@@I~
    	cbAllGreenNoBlue.setStateInt(Pprop.getParameter(getKeyRS(RSID_ALLGREEN_NOBLUE),1),swFixed);//~v@@@I~
    	cb9RenPinSou.setStateInt(Pprop.getParameter(getKeyRS(RSID_9RENPINSOU),1),swFixed);//~v@@@I~
    	cbNoPair13.setStateInt(Pprop.getParameter(getKeyRS(RSID_NOPAIR13),1),swFixed);//~v@@@I~
    	cbNoPair14.setStateInt(Pprop.getParameter(getKeyRS(RSID_NOPAIR14),1),swFixed);//~va11I~
    	cbBigRing.setStateInt(Pprop.getParameter(getKeyRS(RSID_BIGRING),1),swFixed);//~v@@@I~
    	cbBigRingNotPin.setStateInt(Pprop.getParameter(getKeyRS(RSID_BIGRING_NOTPIN),0),swFixed);//~vaw4I~
    	cb4SeqNum.setStateInt(Pprop.getParameter(getKeyRS(RSID_4SEQNUM),0),swFixed);//~vaw5R~
    	cbSingle.setStateInt(Pprop.getParameter(getKeyRS(RSID_SINGLE),0),swFixed);//~vaw8I~
    	cb3WindNoHonor.setStateInt(Pprop.getParameter(getKeyRS(RSID_3WINDNOHONOR),0),swFixed);//~vaw9I~
    	cb3Wind.setStateInt(Pprop.getParameter(getKeyRS(RSID_3WIND),0),swFixed);//~vax0I~
    	cb3DupSeq.setStateInt(Pprop.getParameter(getKeyRS(RSID_3DUPSEQ),0),swFixed);//~vax1I~
    	cb3DupSeqAllowOpen.setStateInt(Pprop.getParameter(getKeyRS(RSID_3DUPSEQ_ALLOWOPEN),0),swFixed);//~vax1I~
    	cb3WindNoHonorAllowRound.setStateInt(Pprop.getParameter(getKeyRS(RSID_3WINDNOHONOR_ROUNDOK),0),swFixed);//~vawzI~
        rg3WNH_Han.setCheckedID(Pprop.getParameter(getKeyRS(RSID_3WINDNOHONOR_HAN),0),swFixed);//~vawzI~
        rg3DupSeq_Han.setCheckedID(Pprop.getParameter(getKeyRS(RSID_3DUPSEQ_HAN),0),swFixed);//~vax1I~
    	cb3ColorStraight.setStateInt(Pprop.getParameter(getKeyRS(RSID_3COLORSTRAIGHT),0),swFixed);//~vawgI~
    	cb3SeqNum.setStateInt(Pprop.getParameter(getKeyRS(RSID_3SEQNUM),0),swFixed);//~vawvI~
    	cb7Star.setStateInt(Pprop.getParameter(getKeyRS(RSID_7STAR),0),swFixed);//~vaw6I~
    	cbRank13.setStateInt(Pprop.getParameter(getKeyRS(RSID_RANK13),1),swFixed);//~v@@@I~
    	cbKokusiAnkanRon.setStateInt(Pprop.getParameter(getKeyRS(RSID_KOKUSI_ANKANRON),1),swFixed);//~v@@@I~
//  	cbOpenReachRon.setStateInt(Pprop.getParameter(getKeyRS(RSID_OPENREACHRON),1),swFixed);//~v@@@I~//~0329R~
    	cb5thKan.setStateInt(Pprop.getParameter(getKeyRS(RSID_5THKAN),0),swFixed);//~v@@@I~
//  	cb13NoPair.setStateInt(Pprop.getParameter(getKeyRS(RSID_13NOPAIR),1),swFixed);//~v@@@R~//~va11R~
//  	cb14NoPair.setStateInt(Pprop.getParameter(getKeyRS(RSID_14NOPAIR),1),swFixed);//~v@@@R~//~va11R~
    	cbRenhoMix.setStateInt(Pprop.getParameter(getKeyRS(RSID_RENHOMIX),0),swFixed);//~v@@@I~
        spnRenhoRank.select(Pprop.getParameter(getKeyRS(RSID_RENHORANK),RENHORANK_DEFAULT),swFixed);//~v@@@I~
    //*Reach                                                       //~v@@@I~
        cbOpenReach.setStateInt(Pprop.getParameter(getKeyRS(RSID_REACH_OPEN),0/*defaultIdx*/),swFixed);//~v@@@I~
//      cbMissingReach.setStateInt(Pprop.getParameter(getKeyRS(RSID_REACH_MISSING),0/*defaultIdx*/),swFixed);//~v@@@I~//~vah3R~
        rgFuritenReach.setCheckedID(Pprop.getParameter(getKeyRS(RSID_REACH_FURITEN),FURITEN_REACH_DEFAULT),swFixed);//~vah3I~
        cbOneShot.setStateInt(Pprop.getParameter(getKeyRS(RSID_ONESHOT),1/*defaultIdx*/),swFixed);//~va11I~
        cbAnkanAfterReach.setStateInt(Pprop.getParameter(getKeyRS(RSID_ANKAN_AFTER_REACH),1/*default ON*/),swFixed);//~v@@@I~
        rgOpenReach.setCheckedID(Pprop.getParameter(getKeyRS(RSID_OPENREACH_PAY),OPENREACH_DEFAULT),swFixed);//~0329I~
        rgOpenReachRobot.setCheckedID(Pprop.getParameter(getKeyRS(RSID_OPENREACH_ROBOT),OPENREACH_ROBOT_DEFAULT),swFixed);//~0329I~
        cbOpenReachRobot.setStateInt(Pprop.getParameter(getKeyRS(RSID_OPENREACH_ROBOT_CBNO),1/*default ON*/),swFixed);//~vap5I~
    }                                                              //~v@@@I~
	//***********************************************************  //~vakQI~
    public  static boolean chkUpdateCheckOnly(RuleSetting Prsd)    //~vakQI~
    {                                                              //~vakQI~
		if (Dump.Y) Dump.println("RuleSettingYaku.chkUpdateCheckOnly curProp="+Prsd.curProp.toString());//~vakQI~
	    if (Dump.Y) Dump.println("RuleSettingYaku.chkUpdateCheckOnly AG.ruleProp="+AG.ruleProp.toString());//~vakQI~
        boolean rc=false;                                          //~vakQI~
        for (;;)                                                   //~vakQI~
        {                                                          //~vakQI~
            if 	(Prsd.isChanged(RSID_KUITAN))                  {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_SHOW_ANYWAY_BTN))         {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_DOUBLE_PILLOW))           {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_PINFUTAKEN))              {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_PENDING_RANKNO))          {rc=true; break;}//~vakQI~
//          if  (Prsd.isChanged(RSID_PENDING_RANKEMPTY))       {rc=true; break;}//~vakQI~//~vapuR~
//          if  (Prsd.isChanged(RSID_PENDING_RANKFURITEN))     {rc=true; break;}//~vakQI~//~vapuR~
    //        if  (Prsd.isChanged(RSID_PENDING_RANKFIXMULTI))    {rc=true; break;}//~vaptI~
            if  (Prsd.isChanged(RSID_PENDING_RANK0))           {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_PENDING_RANK2))           {rc=true; break;}//~vakQI~
    //*drawnMangan                                                 //~vakQI~
            if  (Prsd.isChanged(RSID_DRAWN_MANGAN_TYPE))       {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_DRAWN_MANGAN_RANK))       {rc=true; break;}//~vakQI~
    //*8Continue                                                   //~vakQI~
            if  (Prsd.isChanged(RSID_8CONTINUE))               {rc=true; break;}//~vakQI~
//          if  (Prsd.isChanged(RSID_8CONT_NONEEDYAKU))        {rc=true; break;}//~vakQI~//~vaq3R~
            if  (Prsd.isChanged(RSID_8CONT_RESET))             {rc=true; break;}//~vakQI~
//          if  (Prsd.isChanged(RSID_8CONT_MULTI))             {rc=true; break;}//~vakQI~//~vaq3R~
    //*YakuFix                                                     //~vakQI~
            if  (Prsd.isChanged(RSID_YAKUFIX))                 {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_YAKUFIX_TAKE))            {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_YAKUFIX_MULTIWAITOK))     {rc=true; break;}//~vakQI~
    //*YakuFix2                                                    //~vakQI~
            if  (Prsd.isChanged(RSID_YAKUFIX2))                {rc=true; break;}//~vakQI~
    //*7Pairif                                                     //~vakQI~
            if  (Prsd.isChanged(RSID_7PAIR))                   {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_7PAIR4PAIR))              {rc=true; break;}//~vakQI~
    //*Yakuman2                                                    //~vakQI~
            if  (Prsd.isChanged(RSID_4ANKO1))                  {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_KOKUSI13))                {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_9REN9))                   {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_4WIND))                   {rc=true; break;}//~vakQI~
    //*Yakuman                                                     //~vakQI~
            if  (Prsd.isChanged(RSID_ALLGREEN_NOBLUE))         {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_9RENPINSOU))              {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_NOPAIR13))                {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_NOPAIR14))                {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_BIGRING))                 {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_BIGRING_NOTPIN))          {rc=true; break;}//~vaw4I~
            if  (Prsd.isChanged(RSID_4SEQNUM))                 {rc=true; break;}//~vaw5R~
            if  (Prsd.isChanged(RSID_SINGLE))                  {rc=true; break;}//~vaw8I~
            if  (Prsd.isChanged(RSID_3WINDNOHONOR))            {rc=true; break;}//~vaw9I~
            if  (Prsd.isChanged(RSID_3WIND))                   {rc=true; break;}//~vax0I~
            if  (Prsd.isChanged(RSID_3DUPSEQ))                 {rc=true; break;}//~vax1I~
            if  (Prsd.isChanged(RSID_3DUPSEQ_ALLOWOPEN))       {rc=true; break;}//~vax1I~
            if  (Prsd.isChanged(RSID_3WINDNOHONOR_ROUNDOK))    {rc=true; break;}//~vawzI~
            if  (Prsd.isChanged(RSID_3WINDNOHONOR_HAN))        {rc=true; break;}//~vawzI~
            if  (Prsd.isChanged(RSID_3DUPSEQ_HAN))             {rc=true; break;}//~vax1I~
            if  (Prsd.isChanged(RSID_3COLORSTRAIGHT))          {rc=true; break;}//~vawgI~
            if  (Prsd.isChanged(RSID_3SEQNUM))                 {rc=true; break;}//~vawvI~
            if  (Prsd.isChanged(RSID_7STAR))                   {rc=true; break;}//~vaw6I~
            if  (Prsd.isChanged(RSID_RANK13))                  {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_KOKUSI_ANKANRON))         {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_5THKAN))                  {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_RENHOMIX))                {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_RENHORANK))               {rc=true; break;}//~vakQI~
    //*Reachif                                                     //~vakQI~
            if  (Prsd.isChanged(RSID_REACH_OPEN))              {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_REACH_FURITEN))           {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_ONESHOT))                 {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_ANKAN_AFTER_REACH))       {rc=true; break;}//~vakQI~
            if  (Prsd.isChanged(RSID_OPENREACH_PAY))           {rc=true; break;}//~vakQI~
//          if  (Prsd.isChanged(RSID_OPENREACH_ROBOT))         {rc=true; break;}//~vakQI~//~vap5R~
            if  (Prsd.isChanged(RSID_OPENREACH_ROBOT_CBNO))    {rc=true; break;}//~vap5I~
            break;                                                 //~vakQI~
        }                                                          //~vakQI~
	    if (Dump.Y) Dump.println("RuleSettingYaku.chkUpdateCheckOnly rc="+rc);//~vakQI~
        return rc;                                                 //~vakQI~
    }                                                              //~vakQI~
	//***********************************************************  //~var0M~
    public  static boolean chkUpdateCheckOnly_ExceptSumm(RuleSetting Prsd)//~var0M~
    {                                                              //~var0M~
		if (Dump.Y) Dump.println("RuleSettingYaku.chkUpdateCheckOnly_ExceptSumm curProp="+Prsd.curProp.toString());//~var0M~
	    if (Dump.Y) Dump.println("RuleSettingYaku.chkUpdateCheckOnly_ExceptSumm AG.ruleProp="+AG.ruleProp.toString());//~var0M~
        boolean rc=false;                                          //~var0I~
        for (;;)                                                   //~var0I~
        {                                                          //~var0I~
    //*Yakuman2                                                    //~var1I~
            if  (Prsd.isChanged(RSID_4ANKO1))                  {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_KOKUSI13))                {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_9REN9))                   {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_4WIND))                   {rc=true; break;}//~var1I~
    //*Yakuman                                                     //~var1I~
            if  (Prsd.isChanged(RSID_ALLGREEN_NOBLUE))         {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_9RENPINSOU))              {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_NOPAIR13))                {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_NOPAIR14))                {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_BIGRING))                 {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_BIGRING_NOTPIN))          {rc=true; break;}//~vaw4I~
            if  (Prsd.isChanged(RSID_4SEQNUM))                 {rc=true; break;}//~vaw5R~
            if  (Prsd.isChanged(RSID_SINGLE))                  {rc=true; break;}//~vaw8I~
            if  (Prsd.isChanged(RSID_3WINDNOHONOR))            {rc=true; break;}//~vaw9I~
            if  (Prsd.isChanged(RSID_3WIND))                   {rc=true; break;}//~vax0I~
            if  (Prsd.isChanged(RSID_3DUPSEQ))                 {rc=true; break;}//~vax1I~
            if  (Prsd.isChanged(RSID_3DUPSEQ_ALLOWOPEN))       {rc=true; break;}//~vax1I~
            if  (Prsd.isChanged(RSID_3WINDNOHONOR_ROUNDOK))    {rc=true; break;}//~vawzI~
            if  (Prsd.isChanged(RSID_3WINDNOHONOR_HAN))        {rc=true; break;}//~vawzI~
            if  (Prsd.isChanged(RSID_3DUPSEQ_HAN))             {rc=true; break;}//~vax1I~
            if  (Prsd.isChanged(RSID_3COLORSTRAIGHT))          {rc=true; break;}//~vawgI~
            if  (Prsd.isChanged(RSID_3SEQNUM))                 {rc=true; break;}//~vawvI~
            if  (Prsd.isChanged(RSID_7STAR))                   {rc=true; break;}//~vaw6I~
            if  (Prsd.isChanged(RSID_RANK13))                  {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_KOKUSI_ANKANRON))         {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_5THKAN))                  {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_RENHOMIX))                {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_RENHORANK))               {rc=true; break;}//~var1I~
    //*drawnMangan                                                 //~var1I~
            if  (Prsd.isChanged(RSID_DRAWN_MANGAN_TYPE))       {rc=true; break;}//~var1I~
            if  (Prsd.isChanged(RSID_DRAWN_MANGAN_RANK))       {rc=true; break;}//~var1I~
    //*8Continue                                                   //~var0I~
            if  (Prsd.isChanged(RSID_8CONTINUE))               {rc=true; break;}//~var0I~
            if  (Prsd.isChanged(RSID_8CONT_RESET))             {rc=true; break;}//~var0I~
            break;                                                 //~var0I~
        }                                                          //~var0I~
	    if (Dump.Y) Dump.println("RuleSettingYaku.chkUpdateCheckOnly_ExceptSumm rc="+rc);//~var0M~
        return rc;                                                 //~var0M~
    }                                                              //~var0M~
	//***********************************************************  //~vakQI~
    private void chkUpdate()                                       //~vakQI~
    {                                                              //~vakQI~
		if (Dump.Y) Dump.println("RuleSettingYaku.chkUpdate curProp="+curProp.toString());//~vakQI~
	    if (Dump.Y) Dump.println("RuleSettingYaku.chkUpdate AG.ruleProp="+AG.ruleProp.toString());//~vakQI~
        RSD.setBGUpdated(cbKuitan,RSD.isChanged(RSID_KUITAN));     //~vakQR~
        RSD.setBGUpdated(cbShowAnywayBtn,RSD.isChanged(RSID_SHOW_ANYWAY_BTN));//~vakQR~
        RSD.setBGUpdated(cbDoublePillow,RSD.isChanged(RSID_DOUBLE_PILLOW));//~vakQR~
        RSD.setBGUpdated(cbPinfuTaken,RSD.isChanged(RSID_PINFUTAKEN));//~vakQR~
    //*Pending                                                     //~vapuI~
        RSD.setBGUpdated(cbPendingRankNo,RSD.isChanged(RSID_PENDING_RANKNO));//~vakQR~
//      RSD.setBGUpdated(cbPendingRankEmpty,RSD.isChanged(RSID_PENDING_RANKEMPTY));//~vakQR~//~vapuR~
//      RSD.setBGUpdated(cbPendingRankFuriten,RSD.isChanged(RSID_PENDING_RANKFURITEN));//~vakQR~//~vapuR~
     //   RSD.setBGUpdated(cbPendingRankFixMulti,RSD.isChanged(RSID_PENDING_RANKFIXMULTI));//~vaptI~
        RSD.setBGUpdated(cbPendingRank0OK,RSD.isChanged(RSID_PENDING_RANK0));//~vakQR~
        RSD.setBGUpdated(rgPendingRank2,RSD.isChanged(RSID_PENDING_RANK2));//~vakQR~
    //*drawnMangan                                                 //~vakQI~
        RSD.setBGUpdated(rgDrawnMangan,RSD.isChanged(RSID_DRAWN_MANGAN_TYPE));//~vakQR~
        RSD.setBGUpdated(spnDrawnManganRank,RSD.isChanged(RSID_DRAWN_MANGAN_RANK));//~vakQR~
    //*8Continue                                                   //~vakQI~
//      RSD.setBGUpdated(bg8Continue,RSID_8CONTINUE);//~vakQR~     //~varbR~
        RSD.setBGUpdated(bg8Continue,RSID_8CONTINUE,Y8C_DEFAULT);  //~varbI~
//  	RSD.setBGUpdated(cb8ContNoNeedYaku,RSD.isChanged(RSID_8CONT_NONEEDYAKU));//~vakQR~//~vapuR~
    	RSD.setBGUpdated(cb8ContReset,RSD.isChanged(RSID_8CONT_RESET));//~vakQR~
//  	RSD.setBGUpdated(cb8ContMulti,RSD.isChanged(RSID_8CONT_MULTI));//~vakQR~//~vapuR~
    //*YakuFix                                                     //~vakQI~
        RSD.setBGUpdated(rgYakuFix,RSD.isChanged(RSID_YAKUFIX));   //~vakQR~
        RSD.setBGUpdated(rgYakuFixMultiwaitTake,RSD.isChanged(RSID_YAKUFIX_TAKE));//~vakQR~
    	RSD.setBGUpdated(cbYakuFixMultiwaitOK,RSD.isChanged(RSID_YAKUFIX_MULTIWAITOK));//~vakQR~
    //*YakuFix2                                                    //~vakQI~
        RSD.setBGUpdated(rgYakuFix2,RSD.isChanged(RSID_YAKUFIX2)); //~vakQR~
    //*7Pair                                                       //~vakQI~
        RSD.setBGUpdated(rgYaku7Pair,RSD.isChanged(RSID_7PAIR));   //~vakQR~
    	RSD.setBGUpdated(cbYaku7Pair4Pair,RSD.isChanged(RSID_7PAIR4PAIR));//~vakQR~
    //*Yakuman2                                                    //~vakQI~
    	RSD.setBGUpdated(cbYakuMan2_4Anko,RSD.isChanged(RSID_4ANKO1));//~vakQR~
    	RSD.setBGUpdated(cbYakuMan2_Kokusi13,RSD.isChanged(RSID_KOKUSI13));//~vakQR~
    	RSD.setBGUpdated(cbYakuMan2_9Ren9,RSD.isChanged(RSID_9REN9));//~vakQR~
    	RSD.setBGUpdated(cbYakuMan2_4Wind,RSD.isChanged(RSID_4WIND));//~vakQR~
    //*Yakuman                                                     //~vakQI~
    	RSD.setBGUpdated(cbAllGreenNoBlue,RSD.isChanged(RSID_ALLGREEN_NOBLUE));//~vakQR~
    	RSD.setBGUpdated(cb9RenPinSou,RSD.isChanged(RSID_9RENPINSOU));//~vakQR~
    	RSD.setBGUpdated(cbNoPair13,RSD.isChanged(RSID_NOPAIR13)); //~vakQR~
    	RSD.setBGUpdated(cbNoPair14,RSD.isChanged(RSID_NOPAIR14)); //~vakQR~
    	RSD.setBGUpdated(cbBigRing,RSD.isChanged(RSID_BIGRING));   //~vakQR~
    	RSD.setBGUpdated(cbBigRingNotPin,RSD.isChanged(RSID_BIGRING_NOTPIN));//~vaw4I~
    	RSD.setBGUpdated(cb4SeqNum,RSD.isChanged(RSID_4SEQNUM));   //~vaw5R~
    	RSD.setBGUpdated(cbSingle,RSD.isChanged(RSID_SINGLE));     //~vaw8I~
    	RSD.setBGUpdated(cb3WindNoHonor,RSD.isChanged(RSID_3WINDNOHONOR));//~vaw9I~
    	RSD.setBGUpdated(cb3Wind,RSD.isChanged(RSID_3WIND));       //~vax0I~
    	RSD.setBGUpdated(cb3DupSeq,RSD.isChanged(RSID_3DUPSEQ));   //~vax1I~
    	RSD.setBGUpdated(cb3DupSeqAllowOpen,RSD.isChanged(RSID_3DUPSEQ_ALLOWOPEN));//~vax1I~
    	RSD.setBGUpdated(cb3WindNoHonorAllowRound,RSD.isChanged(RSID_3WINDNOHONOR_ROUNDOK));//~vawzI~
        RSD.setBGUpdated(rg3WNH_Han,RSD.isChanged(RSID_3WINDNOHONOR_HAN));//~vawzI~
        RSD.setBGUpdated(rg3DupSeq_Han,RSD.isChanged(RSID_3DUPSEQ_HAN));//~vax1I~
    	RSD.setBGUpdated(cb3ColorStraight,RSD.isChanged(RSID_3COLORSTRAIGHT));//~vawgI~
    	RSD.setBGUpdated(cb3SeqNum,RSD.isChanged(RSID_3SEQNUM));   //~vawvI~
    	RSD.setBGUpdated(cb7Star,RSD.isChanged(RSID_7STAR));       //~vaw6I~
    	RSD.setBGUpdated(cbRank13,RSD.isChanged(RSID_RANK13));     //~vakQR~
    	RSD.setBGUpdated(cbKokusiAnkanRon,RSD.isChanged(RSID_KOKUSI_ANKANRON));//~vakQR~
    	RSD.setBGUpdated(cb5thKan,RSD.isChanged(RSID_5THKAN));     //~vakQR~
    	RSD.setBGUpdated(cbRenhoMix,RSD.isChanged(RSID_RENHOMIX)); //~vakQR~
        RSD.setBGUpdated(spnRenhoRank,RSD.isChanged(RSID_RENHORANK));//~vakQR~
    //*Reach                                                       //~vakQI~
        RSD.setBGUpdated(cbOpenReach,RSD.isChanged(RSID_REACH_OPEN));//~vakQR~
        RSD.setBGUpdated(rgFuritenReach,RSD.isChanged(RSID_REACH_FURITEN));//~vakQR~
        RSD.setBGUpdated(cbOneShot,RSD.isChanged(RSID_ONESHOT));   //~vakQR~
        RSD.setBGUpdated(cbAnkanAfterReach,RSD.isChanged(RSID_ANKAN_AFTER_REACH));//~vakQR~
        RSD.setBGUpdated(rgOpenReach,RSD.isChanged(RSID_OPENREACH_PAY));//~vakQR~
//      RSD.setBGUpdated(rgOpenReachRobot,RSD.isChanged(RSID_OPENREACH_ROBOT));//~vakQR~//~vapuR~
        RSD.setBGUpdated(cbOpenReachRobot,RSD.isChanged(RSID_OPENREACH_ROBOT_CBNO));//~vap5I~
    }                                                              //~vakQI~
	//***********************************************************  //~v@@@I~
    private boolean dialog2Properties()                            //~v@@@I~
    {                                                              //~v@@@I~
    	int  changed=0;                                            //~v@@@I~
    //*******************                                          //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_KUITAN),cbKuitan.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_SHOW_ANYWAY_BTN),cbShowAnywayBtn.getStateInt());//~va86I~
        changed+=updateProp(getKeyRS(RSID_DOUBLE_PILLOW),cbDoublePillow.getStateInt());//~va11I~
        changed+=updateProp(getKeyRS(RSID_PINFUTAKEN),cbPinfuTaken.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_PENDING_RANKNO),cbPendingRankNo.getStateInt());//~v@@@I~//~0330R~
        changed+=updateProp(getKeyRS(RSID_PENDING_RANKEMPTY),cbPendingRankEmpty.getStateInt());//~0330I~
        changed+=updateProp(getKeyRS(RSID_PENDING_RANKFURITEN),cbPendingRankFuriten.getStateInt());//~0330I~
     //   changed+=updateProp(getKeyRS(RSID_PENDING_RANKFIXMULTI),cbPendingRankFixMulti.getStateInt());//~vaptI~
        changed+=updateProp(getKeyRS(RSID_PENDING_RANK0),cbPendingRank0OK.getStateInt());//~0330I~
        changed+=updateProp(getKeyRS(RSID_PENDING_RANK2),rgPendingRank2.getCheckedID());//~0330I~
    //*drawnMangan                                                 //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_TYPE),rgDrawnMangan.getCheckedID());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_RANK),spnDrawnManganRank.getSelectedIndex());//~v@@@I~
    //*8Continue                                                   //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_8CONTINUE),bg8Continue.getChecked());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_8CONT_NONEEDYAKU),cb8ContNoNeedYaku.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_8CONT_RESET),cb8ContReset.getStateInt());//~v@@@R~
//      changed+=updateProp(getKeyRS(RSID_8CONT_MULTI),cb8ContReset.getStateInt());//~v@@@I~//~vaq3R~
        changed+=updateProp(getKeyRS(RSID_8CONT_MULTI),cb8ContMulti.getStateInt());//~vaq3I~
    //*YakuFix                                                     //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_YAKUFIX),rgYakuFix.getCheckedID());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_YAKUFIX_TAKE),rgYakuFixMultiwaitTake.getCheckedID());//~va91I~
        changed+=updateProp(getKeyRS(RSID_YAKUFIX_MULTIWAITOK),cbYakuFixMultiwaitOK.getStateInt());//~0208I~
//      changed+=updateProp(getKeyRS(RSID_YAKUFIX_MULTIWAITDRAWOK),cbYakuFixMultiwaitDrawOK.getStateInt());//~0208I~//~va8kR~
    //*YakuFix1                                                    //~va11I~
//      changed+=updateProp(getKeyRS(RSID_YAKUFIX1),cbYakuFix1.getStateInt());//~va11R~
    //*YakuFix2                                                    //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_YAKUFIX2),rgYakuFix2.getCheckedID());//~v@@@I~
//      changed+=updateProp(getKeyRS(RSID_YAKUFIX2LAST),cbYakuFix2Last.getStateInt());//~v@@@R~
    //*7Pair                                                       //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_7PAIR),rgYaku7Pair.getCheckedID());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_7PAIR4PAIR),cbYaku7Pair4Pair.getStateInt());//~v@@@I~
    //*Yakuman2                                                    //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_4ANKO1),cbYakuMan2_4Anko.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_KOKUSI13),cbYakuMan2_Kokusi13.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_9REN9),cbYakuMan2_9Ren9.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_4WIND),cbYakuMan2_4Wind.getStateInt());//~v@@@I~
    //*Yakuman                                                     //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_ALLGREEN_NOBLUE),cbAllGreenNoBlue.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_9RENPINSOU),cb9RenPinSou.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_NOPAIR13),cbNoPair13.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_NOPAIR14),cbNoPair14.getStateInt());//~va11I~
        changed+=updateProp(getKeyRS(RSID_BIGRING),cbBigRing.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_BIGRING_NOTPIN),cbBigRingNotPin.getStateInt());//~vaw4I~
        changed+=updateProp(getKeyRS(RSID_4SEQNUM),cb4SeqNum.getStateInt());//~vaw5R~
        changed+=updateProp(getKeyRS(RSID_SINGLE),cbSingle.getStateInt());//~vaw8I~
        changed+=updateProp(getKeyRS(RSID_3WINDNOHONOR),cb3WindNoHonor.getStateInt());//~vaw9I~
        changed+=updateProp(getKeyRS(RSID_3WIND),cb3Wind.getStateInt());//~vax0I~
        changed+=updateProp(getKeyRS(RSID_3DUPSEQ),cb3DupSeq.getStateInt());//~vax1I~
        changed+=updateProp(getKeyRS(RSID_3DUPSEQ_ALLOWOPEN),cb3DupSeqAllowOpen.getStateInt());//~vax1I~
        changed+=updateProp(getKeyRS(RSID_3WINDNOHONOR_ROUNDOK),cb3WindNoHonorAllowRound.getStateInt());//~vawzI~
        changed+=updateProp(getKeyRS(RSID_3WINDNOHONOR_HAN),rg3WNH_Han.getCheckedID());//~vawzI~
        changed+=updateProp(getKeyRS(RSID_3DUPSEQ_HAN),rg3DupSeq_Han.getCheckedID());//~vax1I~
        changed+=updateProp(getKeyRS(RSID_3COLORSTRAIGHT),cb3ColorStraight.getStateInt());//~vawgI~
        changed+=updateProp(getKeyRS(RSID_3SEQNUM),cb3SeqNum.getStateInt());//~vawvI~
        changed+=updateProp(getKeyRS(RSID_7STAR),cb7Star.getStateInt());//~vaw6I~
        changed+=updateProp(getKeyRS(RSID_RANK13),cbRank13.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_KOKUSI_ANKANRON),cbKokusiAnkanRon.getStateInt());//~v@@@I~
//      changed+=updateProp(getKeyRS(RSID_OPENREACHRON),cbOpenReachRon.getStateInt());//~v@@@I~//~0329R~
        changed+=updateProp(getKeyRS(RSID_5THKAN),cb5thKan.getStateInt());//~v@@@I~
//      changed+=updateProp(getKeyRS(RSID_13NOPAIR),cb13NoPair.getStateInt());//~v@@@I~//~va11R~
//      changed+=updateProp(getKeyRS(RSID_14NOPAIR),cb14NoPair.getStateInt());//~v@@@I~//~va11R~
        changed+=updateProp(getKeyRS(RSID_RENHOMIX),cbRenhoMix.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_RENHORANK),spnRenhoRank.getSelectedIndex());//~v@@@I~
    //*Reach                                                       //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_REACH_OPEN),cbOpenReach.getStateInt());//~v@@@I~
//      changed+=updateProp(getKeyRS(RSID_REACH_MISSING),cbMissingReach.getStateInt());//~v@@@I~//~vah3R~
        changed+=updateProp(getKeyRS(RSID_REACH_FURITEN),rgFuritenReach.getCheckedID());//~vah3I~
        changed+=updateProp(getKeyRS(RSID_ONESHOT),cbOneShot.getStateInt());//~va11I~
        changed+=updateProp(getKeyRS(RSID_ANKAN_AFTER_REACH),cbAnkanAfterReach.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_OPENREACH_PAY),rgOpenReach.getCheckedID());//~0329I~
        changed+=updateProp(getKeyRS(RSID_OPENREACH_ROBOT),rgOpenReachRobot.getCheckedID());//~0329I~
        changed+=updateProp(getKeyRS(RSID_OPENREACH_ROBOT_CBNO),cbOpenReachRobot.getStateInt());//~vap5I~
                                                                   //~v@@@I~
        if (Dump.Y) Dump.println("RuleSettingYaku.dialog2Properties changed="+changed);//~v@@@I~
    	return changed!=0;                                         //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    private int updateProp(String Pkey,int Pnewval)                //~v@@@I~
    {                                                              //~v@@@I~
    	int rc=0;                                                  //~v@@@I~
        if (Pnewval<0)                                             //~v@@@I~
        	return rc;                                             //~v@@@I~
    	int oldval=curProp.getParameter(Pkey,-1);                  //~v@@@R~
        if (oldval!=Pnewval)                                       //~v@@@I~
        {                                                          //~v@@@I~
	        curProp.setParameter(Pkey,Pnewval);                    //~v@@@I~
            rc=1;                                                  //~v@@@I~
        }                                                          //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //************************************************************************//~v@@@I~
    //************************************************************************//~v@@@I~
    //************************************************************************//~v@@@I~
    //**************************************                       //~v@@@I~
    //*drawnMangan                                                 //~v@@@I~
    //**************************************                       //~v@@@I~
    private static int getDrawnManganType()                        //~v@@@I~
    {                                                              //~v@@@I~
        int def=DRAWN_MANGAN_DEFAULT;                              //~v@@@I~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_TYPE),def);//~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.getDrawnManganType rc"+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    public static boolean isDrawnManganAvailable()                 //~v@@@I~
    {                                                              //~v@@@I~
        int type=getDrawnManganType();                             //~v@@@I~
        boolean rc=type!=DRAWN_MANGAN_NO;                          //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnManganAvailable rc"+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    public static boolean isDrawnManganAsRon()                     //~v@@@I~
    {                                                              //~v@@@I~
        int type=getDrawnManganType();                             //~v@@@I~
        boolean rc=type==DRAWN_MANGAN_ASRON;                       //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnManganAsRon:"+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    public static boolean isDrawnManganAsDrawn()                   //~v@@@I~
    {                                                              //~v@@@I~
        int type=getDrawnManganType();                             //~v@@@I~
        boolean rc=type==DRAWN_MANGAN_ASDRAWN;                     //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.isDrawnManganAsDrawn:"+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    public static int getDrawnManganPoint()                        //~v@@@I~
    {                                                              //~v@@@I~
		int idx=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_RANK),0);//~v@@@I~
        int rc=pointsDrawnMangan[idx];                             //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.getDrawnManganPoint:"+rc+",idx="+idx);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    public static String getDrawnManganRank()                      //~v@@@I~
    {                                                              //~v@@@I~
		int idx=AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_RANK),0);//~v@@@I~
        String rc=rankDrawnMangan[idx];                            //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.getDrawnManganRank:"+rc+",idx="+idx);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*used on other dialog to show related setting                //~v@@@I~
    //*********************************************************    //~v@@@I~
    public static void setDrawnMangan(View PView,boolean PswFixed) //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.setDrawnMangan swFixed="+PswFixed);//~v@@@R~
    	URadioGroup rgDrawnMangan=new URadioGroup(PView,R.id.rgDrawnMangan,0,rbIDDrawnMangan);//~v@@@I~
        USpinner spnDrawnManganRank =new USpinner(PView,R.id.spnDrawnManganRank);//~v@@@I~
        spnDrawnManganRank.setArray(rankDrawnMangan);              //~v@@@I~
                                                                   //~v@@@I~
        rgDrawnMangan.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_TYPE),0),PswFixed);//~v@@@I~
        spnDrawnManganRank.select(AG.ruleProp.getParameter(getKeyRS(RSID_DRAWN_MANGAN_RANK),0),PswFixed);//~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~va60I~
    public static void setKeiten(View PView,boolean PswFixed)      //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("RuleSetting.setKeiten swFixed="+PswFixed);//~va60I~
    	UCheckBox cbPendingRankNo,cbPendingRankEmpty,cbPendingRankFuriten,cbPendingRank0OK;//~va60I~
    	UCheckBox cbPendingRankFixMulti;                           //~vaptI~
	    URadioGroup rgPendingRank2;                                //~vaptI~
                                                                   //~va60I~
        cbPendingRankNo     =       new UCheckBox(PView,R.id.cbPendingRankNo);//~va60I~
        cbPendingRankEmpty  =       new UCheckBox(PView,R.id.cbPendingRankEmpty);//~va60I~
        cbPendingRankFuriten=       new UCheckBox(PView,R.id.cbPendingRankFuriten);//~va60I~
        cbPendingRankFixMulti=      new UCheckBox(PView,R.id.cbPendingRankFixMulti);//~vaptI~
        cbPendingRank0OK    =       new UCheckBox(PView,R.id.cbPendingRank0OK);//~va60I~
    	rgPendingRank2      =       new URadioGroup(PView,R.id.rgPendingRank2,0,rbIDPendingRank2);//~va60I~
                                                                   //~va60I~
//      cbPendingRankNo.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKNO),1),PswFixed);//~va60R~//~va6cR~
//      cbPendingRankEmpty.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKEMPTY),0),PswFixed);//~va60R~//~va6cR~
//      cbPendingRankFuriten.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKFURITEN),0),PswFixed);//~va60R~//~va6cR~
//      cbPendingRank0OK.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANK0),0),PswFixed);//~va60R~//~va6cR~
        cbPendingRankNo.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKNO),0),PswFixed);//~va6cI~
        cbPendingRankEmpty.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKEMPTY),1),PswFixed);//~va6cI~
        cbPendingRankFuriten.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKFURITEN),1),PswFixed);//~va6cI~
     //   cbPendingRankFixMulti.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKFIXMULTI),1),PswFixed);//~vaptI~
        cbPendingRank0OK.setStateInt(AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANK0),1),PswFixed);//~va6cI~
        rgPendingRank2.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANK2),PENDING_RANK2_DEFAULT),PswFixed);//~va60R~
    }                                                              //~va60I~
    //*********************************************************    //~v@@@I~
    public static boolean isAvailableOpenReach()                   //~v@@@I~
    {                                                              //~v@@@I~
        int def=0;  //false                                        //~v@@@I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_REACH_OPEN),def)!=0;//~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.isAvailableOpenReach:"+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~va11I~
    public static boolean isReachOneShot()                         //~va11I~
    {                                                              //~va11I~
        int def=1;  //false                                        //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_ONESHOT),def)!=0;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.isReachOneShot:"+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //*********************************************************    //~0329I~
//    public static boolean isAvailableOpenReachRobot()            //~vap5R~
//    {                                                            //~vap5R~
//        int def=OPENREACH_ROBOT_DEFAULT;                         //~vap5R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_OPENREACH_ROBOT),def)!=OPENREACH_ROBOT_NONE;//~vap5R~
//        if (Dump.Y) Dump.println("RuleSetting.isAvailableOpenReachRobot:"+rc);//~vap5R~
//        return rc;                                               //~vap5R~
//    }                                                            //~vap5R~
    //*********************************************************    //~vap5I~
    //*rc:true:available                                           //~vap5I~
    //*********************************************************    //~vap5I~
    public static boolean isAvailableOpenReachRobotNo()            //~vap5I~
    {                                                              //~vap5I~
		int def=1;                                                 //~vap5I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_OPENREACH_ROBOT_CBNO),def)==0;	//Off:Yes//~vap5I~
        if (Dump.Y) Dump.println("RuleSetting.isAvailableOpenReachRobotNo:"+rc);//~vap5I~
        return rc;                                                 //~vap5I~
    }                                                              //~vap5I~
    //*********************************************************    //~v@@@I~
    public static boolean isAvailableKanAfterReach()               //~v@@@I~
    {                                                              //~v@@@I~
        int def=1;  //true                                         //~v@@@I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_ANKAN_AFTER_REACH),def)!=0;//~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.isAvailableKanAfterReach="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static boolean isAvailableAnkanRon()                    //~v@@@I~
    {                                                              //~v@@@I~
        int def=1;  //true                                         //~v@@@I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_KOKUSI_ANKANRON),def)!=0;//~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.isAvailableAnkanRon="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static boolean is7Pair4Pair()                           //~v@@@I~
    {                                                              //~v@@@I~
        int def=0;  //false                                        //~v@@@I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_7PAIR4PAIR),def)!=0;//~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.is7Pair4Pair="+rc);  //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //**************************************                       //~v@@@I~//~va11R~
//    public static boolean is13NoPair()                             //~v@@@I~//~va11R~
//    {                                                              //~v@@@I~//~va11R~
//        int def=1;  //true                                         //~v@@@R~//~va11R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_13NOPAIR),def)!=0;//~v@@@I~//~va11R~
//        if (Dump.Y) Dump.println("RuleSetting.is13NoPair="+rc);    //~v@@@I~//~va11R~
//        return rc;                                                 //~v@@@I~//~va11R~
//    }                                                              //~v@@@I~//~va11R~
//    //**************************************                       //~v@@@I~//~va11R~
//    public static boolean is14NoPair()                             //~v@@@I~//~va11R~
//    {                                                              //~v@@@I~//~va11R~
//        int def=1;  //true                                         //~v@@@R~//~va11R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_14NOPAIR),def)!=0;//~v@@@I~//~va11R~
//        if (Dump.Y) Dump.println("RuleSetting.is14NoPair="+rc);    //~v@@@I~//~va11R~
//        return rc;                                                 //~v@@@I~//~va11R~
//    }                                                              //~v@@@I~//~va11R~
    //**************************************                       //~0407I~
	public static boolean is5thKanOn()                             //~0407I~
    {                                                              //~0407I~
        int def=0;  //true                                         //~0407I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_5THKAN),def)!=0;//~0407I~
        if (Dump.Y) Dump.println("RuleSetting.is5thKanOn="+rc);    //~0407I~
        return rc;                                                 //~0407I~
    }                                                              //~0407I~
    //**************************************                       //~va11I~
	public static boolean is13WaitAll()                            //~va11I~
    {                                                              //~va11I~
        int def=1;  //true                                         //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_KOKUSI13),def)!=0;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.is13WaitAll="+rc);   //~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean is7Pair50()                              //~va11I~
    {                                                              //~va11I~
        int def=YAKU7PAIR_50_1;  //50fu 1han                       //~va11R~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_7PAIR),def)==YAKU7PAIR_50_1;//~va11R~
        if (Dump.Y) Dump.println("RuleSetting.is7Pair50="+rc);     //~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va12R~
	public static boolean is7Pair30()                              //~va12R~
    {                                                              //~va12R~
        int def=YAKU7PAIR_50_1;  //50fu 1han                       //~va12R~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_7PAIR),def)==YAKU7PAIR_30_2;//~va12R~
        if (Dump.Y) Dump.println("RuleSetting.is7Pair30="+rc);     //~va12R~
        return rc;                                                 //~va12R~
    }                                                              //~va12R~
    //**************************************                       //~va11I~
	public static boolean isAllGreenNoDragon()                     //~va11I~
    {                                                              //~va11I~
        int def=1;  //allow no Dragon                              //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_ALLGREEN_NOBLUE),1)!=0;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.isAllGreenNoDragon="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean is4WindDouble()                          //~va11I~
    {                                                              //~va11I~
        int def=1;  //allow no Dragon                              //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_4WIND),1)==1;  //~va11I~
        if (Dump.Y) Dump.println("RuleSetting.is4WindDouble="+rc); //~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean is9GateManOnly()                         //~va11R~
    {                                                              //~va11I~
        int def=1;  //allow Pin/Sou                                //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_9RENPINSOU),1)==0;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.is9GateManOnly="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean is9GateDouble()                          //~va11I~
    {                                                              //~va11I~
        int def=1;  //double                                       //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_9REN9),1)==1;  //~va11I~
        if (Dump.Y) Dump.println("RuleSetting.is9GateDouble="+rc); //~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean isTanyaoEarth()                          //~va11I~
    {                                                              //~va11I~
        int def=0;  //No                                           //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_KUITAN),0)==1;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.isTanyaoEarth rc="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean isDoublePillowPoint()                    //~va11I~
    {                                                              //~va11I~
        int def=0;  //No                                           //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DOUBLE_PILLOW),0)==1;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.isDoublePillowPoint rc="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static int getRank1stChildRon()                         //~va11R~
    {                                                              //~va11I~
        int idx=AG.ruleProp.getParameter(getKeyRS(RSID_RENHORANK),RENHORANK_DEFAULT);//~va11I~
        int idxRank=intsRankRenho[idx];                            //~va11I~
        if (Dump.Y) Dump.println("RuleSetting.getRank1stChildRon spinner idx="+idx+",idxRank="+idxRank);//~va11I~
        return idxRank;                                            //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean isYakumanRank1stChildRon()                   //~va11I~
    {                                                              //~va11I~
        int idx=getRank1stChildRon();                              //~va11I~
        boolean rc=idx==RANKIDX_YAKUMAN;                           //~va11I~
        if (Dump.Y) Dump.println("RuleSetting.isYakumanRank1stChildRon rc="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean isMixRank1stChildRon()                       //~va11I~
    {                                                              //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_RENHOMIX),0)!=0;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.isMixRank1stChildRon rc="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean isPinfuTaken()                               //~va11I~
    {                                                              //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_PINFUTAKEN),0)!=0;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.isPinfTaken rc="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean is4SameDouble()                          //~va11I~
    {                                                              //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_4ANKO1),1)!=0;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.is4SameDouble rc="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean isYakumanChariot()                       //~va11I~
    {                                                              //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_BIGRING),1)!=0;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.isYakumanChariot rc="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~vaw4I~
	public static boolean isYakumanChariotNotPin()                 //~vaw4I~
    {                                                              //~vaw4I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_BIGRING_NOTPIN),0)!=0;//~vaw4I~
        if (Dump.Y) Dump.println("RuleSetting.isYakumanChariotNotPin rc="+rc);//~vaw4I~
        return rc;                                                 //~vaw4I~
    }                                                              //~vaw4I~
    //**************************************                       //~vaw5R~
	public static boolean isYakuman4SeqNum()                       //~vaw5R~
    {                                                              //~vaw5R~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_4SEQNUM),0)!=0;//~vaw5R~
        if (Dump.Y) Dump.println("RuleSetting.isYakuman4SeqNum rc="+rc);//~vaw5R~
        return rc;                                                 //~vaw5R~
    }                                                              //~vaw5R~
    //**************************************                       //~vaw8I~
	public static boolean isLocalYakuSingle()                      //~vaw8I~
    {                                                              //~vaw8I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_SINGLE),0)!=0;//~vaw8I~
        if (Dump.Y) Dump.println("RuleSetting.isLocalYakuSingle rc="+rc);//~vaw8I~
        return rc;                                                 //~vaw8I~
    }                                                              //~vaw8I~
    //**************************************                       //~vaw9I~
	public static boolean isLocalYaku3WindNoHonor()                //~vaw9I~
    {                                                              //~vaw9I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_3WINDNOHONOR),0)!=0;//~vaw9I~
        if (Dump.Y) Dump.println("RuleSetting.isLocalYaku3WindNoHonor rc="+rc);//~vaw9I~
        return rc;                                                 //~vaw9I~
    }                                                              //~vaw9I~
    //**************************************                       //~vax0I~
	public static boolean isLocalYaku3Wind()                       //~vax0I~
    {                                                              //~vax0I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_3WIND),0)!=0;//~vax0I~
        if (Dump.Y) Dump.println("RuleSetting.isLocalYaku3Wind rc="+rc);//~vax0I~
        return rc;                                                 //~vax0I~
    }                                                              //~vax0I~
    //**************************************                       //~vax1I~
	public static boolean isLocalYaku3DupSeq()                     //~vax1I~
    {                                                              //~vax1I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_3DUPSEQ),0)!=0;//~vax1R~
        if (Dump.Y) Dump.println("RuleSetting.isLocalYaku3DupSeq rc="+rc);//~vax1I~
        return rc;                                                 //~vax1I~
    }                                                              //~vax1I~
    //**************************************                       //~vax1I~
	public static boolean isLocalYaku3DupSeqAllowOpen()            //~vax1I~
    {                                                              //~vax1I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_3DUPSEQ_ALLOWOPEN),0)!=0;//~vax1I~
        if (Dump.Y) Dump.println("RuleSetting.isLocalYaku3DupSeqAllowOpen rc="+rc);//~vax1I~
        return rc;                                                 //~vax1I~
    }                                                              //~vax1I~
    //**************************************                       //~vawzI~
	public static boolean isLocalYaku3WindNoHonorAllowRound()      //~vawzI~
    {                                                              //~vawzI~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_3WINDNOHONOR_ROUNDOK),0)!=0;//~vawzI~
        if (Dump.Y) Dump.println("RuleSetting.isLocalYaku3WindNoHonorAllowRound rc="+rc);//~vawzI~
        return rc;                                                 //~vawzI~
    }                                                              //~vawzI~
    //**************************************                       //~vawzI~
	public static int getLocalYaku3WindNoHonorHan()                //~vawzI~
    {                                                              //~vawzI~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_3WINDNOHONOR_HAN),0);//~vawzI~
        rc=(rc==0 ? 2 : 3);                                        //~vawzI~
        if (Dump.Y) Dump.println("RuleSetting.getLocalYaku3WindNoHonorHan rc="+rc);//~vawzI~
        return rc;                                                 //~vawzI~
    }                                                              //~vawzI~
    //**************************************                       //~vax1I~
	public static Point getLocalYaku3DupSeqHan()                   //~vax1I~
    {                                                              //~vax1I~
        int type=AG.ruleProp.getParameter(getKeyRS(RSID_3DUPSEQ_HAN),0);//~vax1I~
        Point p=new Point();                                       //~vax1R~
        p.x=(type==0 ? 3 : 2);                                     //~vax1R~
        p.y=isLocalYaku3DupSeqAllowOpen() ? p.x-1 : 0;             //+vax1R~
        if (Dump.Y) Dump.println("RuleSetting.getLocalYaku3DupSeqHan type="+type+",rc="+p);//~vax1I~
        return p;                                                  //~vax1I~
    }                                                              //~vax1I~
    //**************************************                       //~vawgI~
	public static boolean isLocalYaku3ColorStraight()              //~vawgI~
    {                                                              //~vawgI~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_3COLORSTRAIGHT),0)!=0;//~vawgI~
        if (Dump.Y) Dump.println("RuleSetting.isLocalYaku3ColorStraight rc="+rc);//~vawgI~
        return rc;                                                 //~vawgI~
    }                                                              //~vawgI~
    //**************************************                       //~vawvI~
	public static boolean isLocalYaku3SeqNum()                     //~vawvI~
    {                                                              //~vawvI~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_3SEQNUM),0)!=0;//~vawvI~
        if (Dump.Y) Dump.println("RuleSetting.isLocalYaku3SeqNum rc="+rc);//~vawvI~
        return rc;                                                 //~vawvI~
    }                                                              //~vawvI~
    //**************************************                       //~vaw6I~
	public static boolean isYakuman7Star()                         //~vaw6I~
    {                                                              //~vaw6I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_7STAR),0)!=0;//~vaw6I~
        if (Dump.Y) Dump.println("RuleSetting.isYakuman7Star rc="+rc);//~vaw6I~
        return rc;                                                 //~vaw6I~
    }                                                              //~vaw6I~
    //**************************************                       //~va11I~
	public static boolean isYakuman13NoPair()                      //~va11I~
    {                                                              //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_NOPAIR13),1)!=0;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.isYakuman13NoPair rc="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean isYakuman14NoPair()                      //~va11I~
    {                                                              //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_NOPAIR14),1)!=0;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.isYakuman14NoPair rc="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                       //~va11I~
	public static boolean isYakumanByRank()                        //~va11I~
    {                                                              //~va11I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_RANK13),1)!=0;//~va11I~
        if (Dump.Y) Dump.println("RuleSetting.isYakumanByRank rc="+rc);//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**************************************                     //~va11R~//~va60R~
    //*1han constraint rc0(no),1(4stick),2:5Stick                                          //~va11R~//~va60R~
    //**************************************                     //~va11R~//~va60R~
    public static int getYakuFix2Constraint()                           //~va11R~//~va60R~
    {                                                            //~va11R~//~va60R~
        int id=AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX2),YAKUFIX2_DEFAULT);	//default=NO=0//~va60I~
        int rc;                                                    //~va60I~
        switch(id)                                                 //~va60I~
        {                                                          //~va60I~
        case YAKUFIX2_STICK4:                                      //~va60I~
        	rc=4;                                                  //~va60I~
            break;                                                 //~va60I~
        case YAKUFIX2_STICK5:                                      //~va60I~
        	rc=5;                                                  //~va60I~
            break;                                                 //~va60I~
        default:                                                   //~va60I~
            rc=0;                                                  //~va60I~
        }                                                          //~va60I~
        if (Dump.Y) Dump.println("RuleSetting.getYakuFix2Constraint rc="+rc);//~va11R~//~va60R~
        return rc;                                               //~va11R~//~va60R~
    }                                                            //~va11R~//~va60R~
    //**************************************                       //~va8cI~
    public static int getYakuFix()                                 //~va8cI~
    {                                                              //~va8cI~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX),YAKUFIX_DEFAULT);	//default=NO=0//~va8cI~
        if (Dump.Y) Dump.println("RuleSetting.getYakuFix rc="+rc); //~va8cI~
        return rc;                                                 //~va8cI~
    }                                                              //~va8cI~
    //***********************************************************************//~vawrI~//~vawsI~
    //*for InstrumentTest,change temporally  typeYakuFix,swYakuFixMultiWaitTakeOK//~vawrI~//~vawsI~
    //***********************************************************************//~vawrI~//~vawsI~
    public static void setYakuFixForIT(int PtypeFix,boolean PtakeOK)//~vawsI~
    {                                                              //~vawsI~
    	if (Dump.Y) Dump.println("RuleSettingYaku.setYakuFixForIT test-OLD typeYakuFix="+getYakuFix()+",multiWaitTake="+getYakuFixMultiwaitTake());//~vawsI~
        AG.ruleProp.setParameter(getKeyRS(RSID_YAKUFIX),PtypeFix); //~vawsR~
        AG.ruleProp.setParameter(getKeyRS(RSID_YAKUFIX_TAKE),PtakeOK?0:1);//~vawsR~
    	if (Dump.Y) Dump.println("RuleSettingYaku.setYakuFixForIT test-NEW typeYakuFix="+getYakuFix()+",multiWaitTake="+getYakuFixMultiwaitTake());//~vawsI~
    }                                                              //~vawsI~
    //**************************************                       //~va91I~
    public static int getYakuFixMultiwaitTake()                    //~va91I~
    {                                                              //~va91I~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX_TAKE),YAKUFIX_TAKE_DEFAULT);	//default=NO=0//~va91I~
        if (Dump.Y) Dump.println("RuleSetting.getYakuFixTake rc="+rc);//~va91I~
        return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //**************************************                       //~vakaI~
    //*allow multiwait take for not FixLast option                 //~vakaI~
    //**************************************                       //~vakaI~
    public static boolean isYakuFixMultiWaitTakeOK()               //~vakaI~
    {                                                              //~vakaI~
        boolean rc=getYakuFixMultiwaitTake()==YAKUFIX_TAKE_ALL;    //~vakaI~
        if (Dump.Y) Dump.println("RuleSetting.isYakuFixMultiWaitTakeOK rc="+rc);//~vakaI~
        return rc;                                                 //~vakaI~
    }                                                              //~vakaI~
    //**************************************                       //~va8cI~
    public static boolean isYakuFixLast()                              //~va8cI~
    {                                                              //~va8cI~
        int fix=AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX),YAKUFIX_DEFAULT);	//default=NO=0//~va8cI~
	    boolean rc=fix==YAKUFIX_LAST;                              //~va8cI~
        if (Dump.Y) Dump.println("RuleSetting.isYakuFixLast rc="+rc);//~va8cI~
        return rc;                                                 //~va8cI~
    }                                                              //~va8cI~
    //**************************************                       //~vaj0I~
    public static boolean isYakuFixNotFirst()                      //~vaj0I~
    {                                                              //~vaj0I~
        int fix=AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX),YAKUFIX_DEFAULT);	//default=NO=0//~vaj0I~
	    boolean rc=fix!=YAKUFIX_FIRST;                             //~vaj0I~
        if (Dump.Y) Dump.println("RuleSetting.isYakuFixNotFirst rc="+rc);//~vaj0I~
        return rc;                                                 //~vaj0I~
    }                                                              //~vaj0I~
    //*******************************************************      //~va6cI~
    private void setCBListener(UCheckBox Pcb,int Pid)              //~va6cI~
    {                                                              //~va6cI~
        if (Dump.Y) Dump.println("RuleSettingYaku.setCBListener id="+Pid+",cb="+Integer.toHexString(Pid));//~va6cI~
        Pcb.setListener(this,Pid);                                 //~va6cI~
    }                                                              //~va6cI~
    //*******************************************************      //~va6cI~
    //*UCheckBoxI                                                  //~va6cI~
    //*******************************************************      //~va6cI~
    @Override                                                      //~va6cI~
    public void onChangedUCB(CompoundButton Pbtn, boolean PisChecked, int Pparm)//~va6cI~
    {                                                              //~va6cI~
        if (Dump.Y) Dump.println("RuleSettingYaku.onChangedUCB swChildInitializing="+RSD.swChildInitializing+",isChecked="+PisChecked+",parm="+Integer.toHexString(Pparm));//~vapkR~
        boolean enable;                                              //~va6cI~
	    if (RSD.swChildInitializing)                               //~va6cI~
        	return;                                                //~va6cI~
        switch(Pparm)                                              //~va6cI~
        {                                                          //~va6cI~
        case R.id.cbPendingRankNo:                                //~va6cI~
        	enable=!PisChecked;                                   //~va6cI~
        	cbPendingRankEmpty.setState(enable);                   //~va6cI~
        	cbPendingRankFuriten.setState(enable);                 //~va6cI~
        	cbPendingRank0OK.setState(enable);                  //~va6cI~
        	cbPendingRankEmpty.setEnabled(enable);                 //~vapkI~
        	cbPendingRankFuriten.setEnabled(enable);               //~vapkI~
        	cbPendingRank0OK.setEnabled(enable);                    //~vapkI~
        	rgPendingRank2.setEnabledAll(enable);                  //~vapxR~
//          	swNonAll=PisChecked;    //set no all               //~vapkR~
        	break;                                                 //~va6cI~
//        case R.id.cbPendingRankEmpty:                            //~vapkR~
//        case R.id.cbPendingRankFuriten:                          //~vapkR~
//        case R.id.cbPendingRank0OK:                              //~vapkR~
//            if (swNonAll)                                        //~vapkR~
//                break;  //by RankNo                              //~vapkR~
//            cbPendingRankNo.setState(false);                     //~vapkR~
//            break;                                               //~vapkR~
        }                                                          //~va6cI~
    }                                                              //~va6cI~
    //**************************************                       //~va86M~
    public static boolean isShowAnywayButton()                     //~va86M~
    {                                                              //~va86M~
		int def=0;	//false                                        //~va86R~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_SHOW_ANYWAY_BTN),def)!=0;//~va86R~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isShowAnywayButton:"+rc);//~va86R~
        return rc;                                                 //~va86M~
    }                                                              //~va86M~
    //**************************************                       //~va8fI~
    public static boolean isYakuFixMultiwaitOK()                   //~va8fI~
    {                                                              //~va8fI~
//  	int def=0;	//false                                        //~va8fI~//~vak3R~
//      boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX_MULTIWAITOK),def)!=0;//~va8fI~//~vak3R~
        boolean rc=true;                                                   //~vak3I~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isYakuFixMultiwaitOK always rc="+rc);//~va8fI~//~vak3R~
        return rc;                                                 //~va8fI~
    }                                                              //~va8fI~
//    //**************************************                       //~va8fI~//~va8kR~
//    public static boolean isYakuFixMultiwaitDrawOK()               //~va8fI~//~va8kR~
//    {                                                              //~va8fI~//~va8kR~
//        int def=0;  //false                                        //~va8fI~//~va8kR~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX_MULTIWAITDRAWOK),def)!=0;//~va8fI~//~va8kR~
//        if (Dump.Y) Dump.println("RuleSettingYaku.isYakuFixMultiwaitDawOK rc="+rc);//~va8fI~//~va8kR~
//        return rc;                                                 //~va8fI~//~va8kR~
//    }                                                              //~va8fI~//~va8kR~
    //**************************************                       //~va8jI~
    public static boolean isFuritenReachOK()                       //~va8jI~
    {                                                              //~va8jI~
		int def=FURITEN_REACH_DEFAULT;	//0:chombo                 //~vah3R~
//      boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_REACH_MISSING),def)!=0;//~va8jI~//~vah3R~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_REACH_FURITEN),def)==FURITEN_REACH_YES;//~vah3I~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isFuritenReachOK rc="+rc);//~va8jI~
        return rc;                                                 //~va8jI~
    }                                                              //~va8jI~
    //**************************************                       //~vah3I~
    public static boolean isFuritenReachNo()                       //~vah3I~
    {                                                              //~vah3I~
		int def=FURITEN_REACH_DEFAULT;	//0:chombo                 //~vah3I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_REACH_FURITEN),def)==FURITEN_REACH_NO;//~vah3I~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isFuritenReachNo rc="+rc);//~vah3I~
        return rc;                                                 //~vah3I~
    }                                                              //~vah3I~
    //**************************************                       //~vah3I~
    public static boolean isFuritenReachReject()                   //~vah3I~
    {                                                              //~vah3I~
		int def=FURITEN_REACH_DEFAULT;	//0:chombo                 //~vah3I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_REACH_FURITEN),def)==FURITEN_REACH_REJECT;//~vah3I~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isFuritenReachReject rc="+rc);//~vah3I~
        return rc;                                                 //~vah3I~
    }                                                              //~vah3I~
    //**************************************                       //~vap3I~
    public static boolean isYakumanOpenReachDiscard()              //~vap3I~
    {                                                              //~vap3I~
		int def=OPENREACH_DEFAULT;	//0:pay yakuman                //~vap3I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_OPENREACH_PAY),def)==OPENREACH_YAKUMAN;//~vap3I~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isYakumanOpenReachDiscard rc="+rc);//~vap3I~
        return rc;                                                 //~vap3I~
    }                                                              //~vap3I~
//    //**************************************                     //~vap5R~
//    public static int getOpenReachDiscardRobotOption()           //~vap5R~
//    {                                                            //~vap5R~
//        int def=OPENREACH_ROBOT_DEFAULT;    //0:none             //~vap5R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_OPENREACH_ROBOT),OPENREACH_ROBOT_DEFAULT);//~vap5R~
//        if (Dump.Y) Dump.println("RuleSettingYaku.igetOpenReachDiscardRobotOption rc="+rc);//~vap5R~
//        return rc;                                               //~vap5R~
//    }                                                            //~vap5R~
    //*******************************************************************//~vapkI~
    public static boolean isPendingRankNo()                        //~vapkI~
    {                                                              //~vapkI~
//  	int def=0;	//all None=false                               //~vappR~
//      boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKNO),def)!=0;//~vappR~
//      boolean rc=false;   //this option dropped                          //~vappI~//~vapxR~
    	int def=0;	//all None=false                               //~vapxI~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKNO),def)!=0;//~vapxI~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isPendingRankNo rc="+rc);//~vapkI~
        return rc;                                                 //~vapkI~
    }                                                              //~vapkI~
    //*******************************************************************//~vapkI~
    public static boolean isPendingRankEmpty()                     //~vapkI~
    {                                                              //~vapkI~
		int def=1;	//empty OK                                     //~vapkI~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKEMPTY),def)!=0;//~vapkI~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isPendingRankEmpty rc="+rc);//~vapkI~
        return rc;                                                 //~vapkI~
    }                                                              //~vapkI~
    //*******************************************************************//~vapkI~
    public static boolean isPendingRankFuriten()                   //~vapkI~
    {                                                              //~vapkI~
		int def=1;	//furiten OK                                   //~vapkI~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKFURITEN),def)!=0;//~vapkI~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isPendingRankFuriten rc="+rc);//~vapkI~
        return rc;                                                 //~vapkI~
    }                                                              //~vapkI~
    //*******************************************************************//~vaptI~
 //   public static boolean isPendingRankFixMulti()              //~vaptI~
//    {                                                              //~vaptI~
//		int def=1;	//fxi last and kataagari OK                    //~vaptI~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANKFIXMULTI),def)!=0;//~vaptI~
//    	if (Dump.Y) Dump.println("RuleSettingYaku.isPendingRankFixMulti rc="+rc);//~vaptI~
//        return rc;                                                 //~vaptI~
//    }                                                              //~vaptI~
    //*******************************************************************//~vapkI~
    public static boolean isPendingRankRank0()                     //~vapkI~
    {                                                              //~vapkI~
		int def=1;	//0 han ok                                     //~vapkI~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANK0),def)!=0;//~vapkI~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isPendingRankRank0 rc="+rc);//~vapkI~
        return rc;                                                 //~vapkI~
    }                                                              //~vapkI~
    //*******************************************************************//~vapkI~
    public static boolean isPendingRankRank2Rank0OK()              //~vapkI~
    {                                                              //~vapkI~
        int def=PENDING_RANK2_DEFAULT;        //need 1 han         //~vapkI~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANK2),def)==PENDING_RANK2_RANK0OK;//~vapkI~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isPendingRankRank2Rank0OK rc="+rc);//~vapkI~
        return rc;                                                 //~vapkI~
    }                                                              //~vapkI~
//    //*******************************************************************//~vaptI~//~vapuR~
//    public static boolean isPendingRankRank2Rank1OK()              //~vaptI~//~vapuR~
//    {                                                              //~vaptI~//~vapuR~
//        int def=PENDING_RANK2_DEFAULT;        //need 1 han         //~vaptI~//~vapuR~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_PENDING_RANK2),def)==PENDING_RANK2_FIX1;//~vaptI~//~vapuR~
//        if (Dump.Y) Dump.println("RuleSettingYaku.isPendingRankRank2Rank1OK rc="+rc);//~vaptI~//~vapuR~
//        return rc;                                                 //~vaptI~//~vapuR~
//    }                                                              //~vaptI~//~vapuR~
    //*******************************************************************//~vaq3I~
    public static int get8Continue()                               //~vaq3I~
    {                                                              //~vaq3I~
        int def=Y8C_DEFAULT;                                       //~vaq3I~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_8CONTINUE),def);//~vaq3I~
        if (Dump.Y) Dump.println("RuleSettingYaku.get8Continue rc="+rc);//~vaq3I~
        return rc;                                                 //~vaq3I~
    }                                                              //~vaq3I~
    //*******************************************************************//~vaq3I~
    //*require 1/2 han to win                                      //~vaq3I~
    //*******************************************************************//~vaq3I~
    public static boolean is8ContNeedYaku()                            //~vaq3I~
    {                                                              //~vaq3I~
        int def=1;       //need 1/2 han                            //~vaq3I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_8CONT_NONEEDYAKU),def)!=0;//~vaq3I~
        if (Dump.Y) Dump.println("RuleSettingYaku.is8ContNeedYaku rc="+rc);//~vaq3I~
        return rc;                                                 //~vaq3I~
    }                                                              //~vaq3I~
    //*******************************************************************//~vaq3I~
    //*reset ctr once applied yakuman                              //~vaq3I~
    //*******************************************************************//~vaq3I~
    public static boolean is8ContReset()                               //~vaq3I~
    {                                                              //~vaq3I~
        int def=0;       //no reset                                //~vaq3I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_8CONT_RESET),def)!=0;//~vaq3I~
        if (Dump.Y) Dump.println("RuleSettingYaku.is8ContReset rc="+rc);//~vaq3I~
        return rc;                                                 //~vaq3I~
    }                                                              //~vaq3I~
    //*******************************************************************//~vaq3I~
    //*mix with other yakuman                                      //~vaq3I~
    //*******************************************************************//~vaq3I~
    public static boolean is8ContMulti()                               //~vaq3I~
    {                                                              //~vaq3I~
        int def=1;       //mix                                     //~vaq3I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_8CONT_MULTI),def)!=0;//~vaq3R~
        if (Dump.Y) Dump.println("RuleSettingYaku.is8ContMulti rc="+rc);//~vaq3I~
        return rc;                                                 //~vaq3I~
    }                                                              //~vaq3I~
}//class                                                           //~v@@@R~
