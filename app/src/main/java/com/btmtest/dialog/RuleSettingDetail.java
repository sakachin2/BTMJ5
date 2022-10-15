//*CID://+var0R~:                             update#=  786;       //~var0R~
//*****************************************************************//~v101I~
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
import com.btmtest.R;
import com.btmtest.utils.Dump;                                     //~v@@@R~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
                                                                   //~var0I~
import com.btmtest.gui.CommonListener;                             //~var0I~
import com.btmtest.utils.Utils;

public class RuleSettingDetail extends RuleSetting                        //~v@@@R~//~var0R~
{                                                                  //~2C29R~
	private static final int    TITLEID=R.string.Title_RuleSetting;//~v@@@I~
	private static final int    LAYOUTID=R.layout.setting_ruledetail;      //~v@@@I~//~9412R~//~var0R~
	private static final int    LAYOUTID_SMALLFONT=R.layout.setting_ruledetail_theme;//~vac5I~//~var0R~
	private static final int    HELP_TITLEID=R.string.Title_RuleSetting;//~v@@@I~
	private static final String HELPFILE="RuleSetting";       //~v@@@I~//~9412R~//~9515R~//~9615R~//~9C13R~
    //*****************************************************        //~9404I~
    private RuleSettingSumm parentSumm;                            //~var0I~
    private boolean swChanged,swOK;	//use at onDimiss              //~var0I~
    //*****************************************************        //~1A6fI~//~9404R~
	public RuleSettingDetail()                                     //~var0R~
    {
        if (Dump.Y) Dump.println("RuleSettingDetail.defaultConstructor"); //~v@@@I~//~vaqhR~//~var0R~
    }
    //******************************************                   //~var0I~
    public static RuleSettingDetail newInstance(RuleSettingSumm PparentSumm)//~var0R~
    {                                                              //~var0I~
        RuleSettingDetail dlg=new RuleSettingDetail();                  //~var0R~
        dlg.parentSumm=PparentSumm;                                    //~var0I~
        UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),           //SettingDlg//~var0I~
                    UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN,//~var0I~
                    HELP_TITLEID,HELPFILE);                        //~var0I~
        return dlg;                                                //~var0I~
    }                                                              //~var0I~
    //******************************************                   //~var0I~
    public static RuleSettingDetail newInstance(RuleSettingSumm PparentSumm,boolean PswReceived,String PsenderYourName,String PreceivedProp)//~var0I~
    {                                                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingDetail.newInstance swReceived="+PswReceived+",senderYourName="+PsenderYourName+",receivedProp="+PreceivedProp);//~var0I~
        RuleSettingDetail dlg=newInstance(PparentSumm);            //+var0R~
        dlg.swReceived=PswReceived;	//save at OK btn               //~var0I~
        dlg.senderYourName=PsenderYourName;                        //~var0I~
        dlg.strReceivedProp=PreceivedProp;                         //~var0I~
        return dlg;                                                //~var0I~
    }                                                              //~var0I~
	//*****************                                            //~var0I~
    @Override //RuleSetting                                        //~var0I~
    protected void getCurProp()                                    //~var0I~
    {                                                              //~var0I~
        curProp=parentSumm.curProp;                                //~var0I~
        if (Dump.Y) Dump.println("RuleSettingDetail.getCurProp curProp="+curProp);//~var0I~
    }                                                              //~var0I~
    //********************************************************     //~var0I~
    @Override     //RuleSetting                                    //~var0I~
    public void onClickOK()                                        //~var0I~
    {                                                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingDetail.onClickOK");   //~var0I~
        swChanged=dialog2Properties(); //update curProp            //~var0R~
        swOK=true;	//use at onDimiss                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingDetail.onClickOK curProp="+curProp);//~var0I~
        dismiss();                                                 //~var0I~
    }                                                              //~var0I~
    //******************************************                   //~var0I~
    @Override                                                      //~var0I~
    public void onDismissDialog()                                  //~var0I~
    {                                                              //~var0I~
        if (Dump.Y) Dump.println("RuleSettingDetail.onDismissDialog swOK="+swOK+",swChanged="+swChanged);//~var0I~
        CommonListener.resetListener();                            //~var0I~
        if (Utils.isShowingDialogFragment(aRuleSettingOperation))  //~var0I~
        	aRuleSettingOperation.dismiss();                       //~var0I~
        if (Utils.isShowingDialogFragment(aRuleSettingYaku))       //~var0I~
        	aRuleSettingYaku.dismiss();                            //~var0I~
    	if (swOK && swChanged)                                     //~var0I~
	    	parentSumm.repaintSumm();                                  //~var0I~
        if (Dump.Y) Dump.println("RuleSettingDetail.onDismissDialog RuleSetting="+AG.aRuleSetting+",RuleSettingSumm="+AG.aRuleSettingSumm);//~var0I~
        AG.aRuleSetting=AG.aRuleSettingSumm;                       //~var0I~
    }                                                              //~var0I~
}//class                                                           //~v@@@R~
