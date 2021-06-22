//*CID://+va98R~:                             update#=  506;       //~va91R~//+va98R~
//*****************************************************************//~v101I~
//2021/06/15 va98 allow multiwait for take with allInHand          //+va98I~
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.btmtest.R;
import com.btmtest.TestOption;                                     //~v@@@R~
import com.btmtest.dialog.RuleSettingEnum;                         //~v@@@R~
import com.btmtest.gui.UButtonRG;
import com.btmtest.gui.URadioGroup;
import com.btmtest.utils.Prop;                                     //~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.dialog.UFDlg;
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.USpinner;
import com.btmtest.utils.UFile;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.io.File;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.dialog.CompReqDlg.RANKIDX_YAKUMAN;
import static com.btmtest.dialog.RuleSettingEnum.*;                //~v@@@I~
import static com.btmtest.game.GConst.*;

public class RuleSettingYaku extends UFDlg                         //~v@@@R~
            implements UCheckBox.UCheckBoxI                        //~va6cI~
{                                                                  //~2C29R~
  	private static final int    TITLEID=R.string.Label_YakuList;   //~v@@@R~
	private static final int    LAYOUTID=R.layout.setting_rule_yaku;//~v@@@R~
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
    private UCheckBox cbNoPair14;                                  //~va11I~
    private UCheckBox /*cbOpenReachRon,*/cb5thKan,cbPendingRankNo;      //~v@@@R~//~0329R~//~0330R~
    private UCheckBox cbPendingRankEmpty,cbPendingRankFuriten,cbPendingRank0OK;//~0330I~
    private URadioGroup rgPendingRank2;                            //~0330I~
    private URadioGroup rgDrawnMangan;                             //~v@@@I~
    private URadioGroup rgOpenReachRobot,rgOpenReach;              //~0329I~
    private URadioGroup rgYaku7Pair;                               //~v@@@I~
    private UCheckBox cbYaku7Pair4Pair;                            //~v@@@I~
    private USpinner spnDrawnManganRank;                           //~v@@@I~
    private USpinner spnRenhoRank;                                 //~v@@@I~
    private UButtonRG bg8Continue;                                 //~v@@@I~
    private URadioGroup rgYakuFix,rgYakuFix2;                      //~v@@@R~
    private URadioGroup rgYakuFixMultiwaitTake;                    //~va91I~
//  private UCheckBox  cbYakuFix1;                                 //~va11R~
    private UCheckBox  cbOpenReach,cbMissingReach,cbAnkanAfterReach;//~v@@@R~
    private UCheckBox  cbOneShot;                                  //~va11I~
    private UCheckBox  cbYakuFixMultiwaitOK/*,cbYakuFixMultiwaitDrawOK*/;//~0208R~//~va8kR~
    //**********************************************************   //~v@@@I~
    private RuleSetting RSD;                                       //~v@@@I~
    private Prop curProp;                                          //~v@@@I~
    private boolean swChanged,swFixed;                             //~v@@@R~
                                                                   //~1A6fI~
    //******************************************                   //~v@@@M~
	public RuleSettingYaku()                                       //~v@@@R~
    {
        if (Dump.Y) Dump.println("RuleSettingYaku.defaultConstructor");//~v@@@R~
    }
    public static RuleSettingYaku newInstance(RuleSetting Pparent) //~v@@@R~
    {                                                              //~v@@@I~
        RuleSettingYaku dlg=new RuleSettingYaku();                 //~v@@@R~
        UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~v@@@R~
                    UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN,//~v@@@I~
                    HELP_TITLEID,HELPFILE);                        //~v@@@R~
        dlg.RSD=Pparent;                                           //~v@@@I~
        return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@M~
    @Override
    protected void initLayout(View PView)                            //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("RuleSettingYaku.initLayout");    //~v@@@R~
        swFixed=RSD.swFixed;                                       //~v@@@I~
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
        cbPendingRank0OK    =       new UCheckBox(PView,R.id.cbPendingRank0OK);//~0330I~
    	rgPendingRank2=new URadioGroup(PView,R.id.rgPendingRank2,0,rbIDPendingRank2);//~0330I~
    	setCBListener(cbPendingRankNo,R.id.cbPendingRankNo);       //~va6cI~
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
        cbMissingReach=new UCheckBox(PView,R.id.cbMissingReach);   //~v@@@I~
        cbOneShot=new UCheckBox(PView,R.id.cbOneShot);             //~va11I~
    	cbAnkanAfterReach=new UCheckBox(PView,R.id.cbAnkanAfterReach);//~v@@@I~
        rgOpenReach=new URadioGroup(PView,R.id.rgOpenReach,0/*listenerParm*/,rbsOpenReach);//~0329I~
        rgOpenReachRobot=new URadioGroup(PView,R.id.rgOpenReachRobot,0/*listenerParm*/,rbsOpenReachRobot);//~0329I~
    }                                                              //~v@@@I~
	//***********************************************************  //~v@@@I~
    private void setInitialValue()                                 //~v@@@I~
    {                                                              //~v@@@I~
    	curProp=RSD.curProp;                                       //~v@@@I~
	    properties2Dialog(curProp);                                 //~v@@@I~
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
        cbMissingReach.setStateInt(Pprop.getParameter(getKeyRS(RSID_REACH_MISSING),0/*defaultIdx*/),swFixed);//~v@@@I~
        cbOneShot.setStateInt(Pprop.getParameter(getKeyRS(RSID_ONESHOT),1/*defaultIdx*/),swFixed);//~va11I~
        cbAnkanAfterReach.setStateInt(Pprop.getParameter(getKeyRS(RSID_ANKAN_AFTER_REACH),1/*default ON*/),swFixed);//~v@@@I~
        rgOpenReach.setCheckedID(Pprop.getParameter(getKeyRS(RSID_OPENREACH_PAY),OPENREACH_DEFAULT),swFixed);//~0329I~
        rgOpenReachRobot.setCheckedID(Pprop.getParameter(getKeyRS(RSID_OPENREACH_ROBOT),OPENREACH_ROBOT_DEFAULT),swFixed);//~0329I~
    }                                                              //~v@@@I~
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
        changed+=updateProp(getKeyRS(RSID_PENDING_RANK0),cbPendingRank0OK.getStateInt());//~0330I~
        changed+=updateProp(getKeyRS(RSID_PENDING_RANK2),rgPendingRank2.getCheckedID());//~0330I~
    //*drawnMangan                                                 //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_TYPE),rgDrawnMangan.getCheckedID());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_DRAWN_MANGAN_RANK),spnDrawnManganRank.getSelectedIndex());//~v@@@I~
    //*8Continue                                                   //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_8CONTINUE),bg8Continue.getChecked());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_8CONT_NONEEDYAKU),cb8ContNoNeedYaku.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_8CONT_RESET),cb8ContReset.getStateInt());//~v@@@R~
        changed+=updateProp(getKeyRS(RSID_8CONT_MULTI),cb8ContReset.getStateInt());//~v@@@I~
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
        changed+=updateProp(getKeyRS(RSID_REACH_MISSING),cbMissingReach.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_ONESHOT),cbOneShot.getStateInt());//~va11I~
        changed+=updateProp(getKeyRS(RSID_ANKAN_AFTER_REACH),cbAnkanAfterReach.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_OPENREACH_PAY),rgOpenReach.getCheckedID());//~0329I~
        changed+=updateProp(getKeyRS(RSID_OPENREACH_ROBOT),rgOpenReachRobot.getCheckedID());//~0329I~
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
	    URadioGroup rgPendingRank2;                                //~va60I~
                                                                   //~va60I~
        cbPendingRankNo     =       new UCheckBox(PView,R.id.cbPendingRankNo);//~va60I~
        cbPendingRankEmpty  =       new UCheckBox(PView,R.id.cbPendingRankEmpty);//~va60I~
        cbPendingRankFuriten=       new UCheckBox(PView,R.id.cbPendingRankFuriten);//~va60I~
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
    public static boolean isAvailableOpenReachRobot()              //~0329I~
    {                                                              //~0329I~
        int def=OPENREACH_ROBOT_DEFAULT;                           //~0329I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_OPENREACH_ROBOT),def)!=OPENREACH_ROBOT_NONE;//~0329I~
        if (Dump.Y) Dump.println("RuleSetting.isAvailableOpenReachRobot:"+rc);//~0329I~
        return rc;                                                 //~0329I~
    }                                                              //~0329I~
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
    //**************************************                       //~va91I~
    public static int getYakuFixMultiwaitTake()                    //~va91I~
    {                                                              //~va91I~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX_TAKE),YAKUFIX_TAKE_DEFAULT);	//default=NO=0//~va91I~
        if (Dump.Y) Dump.println("RuleSetting.getYakuFixTake rc="+rc);//~va91I~
        return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //**************************************                       //~va8cI~
    public static boolean isYakuFixLast()                              //~va8cI~
    {                                                              //~va8cI~
        int fix=AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX),YAKUFIX_DEFAULT);	//default=NO=0//~va8cI~
	    boolean rc=fix==YAKUFIX_LAST;                              //~va8cI~
        if (Dump.Y) Dump.println("RuleSetting.isYakuFixLast rc="+rc);//~va8cI~
        return rc;                                                 //~va8cI~
    }                                                              //~va8cI~
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
        if (Dump.Y) Dump.println("RuleSettingYaku.onChangedUCB swChildInitializing="+RSD.swChildInitializing+",isChecked="+PisChecked+",parm="+Integer.toHexString(Pparm));//~va6cI~
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
        	break;                                                 //~va6cI~
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
		int def=0;	//false                                        //~va8fI~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_YAKUFIX_MULTIWAITOK),def)!=0;//~va8fI~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isYakuFixMultiwaitOK rc="+rc);//~va8fI~
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
		int def=0;	//false                                        //~va8jI~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_REACH_MISSING),def)!=0;//~va8jI~
    	if (Dump.Y) Dump.println("RuleSettingYaku.isFuritenReachOK rc="+rc);//~va8jI~
        return rc;                                                 //~va8jI~
    }                                                              //~va8jI~
}//class                                                           //~v@@@R~
