//*CID://+DATER~:                             update#=  467;       //~v@@@R~//~0401R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                          //~v@@@R~
import android.view.View;
import android.widget.CheckBox;

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
    private static final String CHII_TEST="Chii_Test";             //~v@@@I~
    private static final String KAN_TEST="Kan_Test";               //~v@@@I~
//  private static final String POSITIONING="Positioning";         //~v@@@R~
    private static final String SKIPDICE="SkipDice";               //~v@@@I~
    private static final String TAKEDISCARD="TakeDiscard";         //~v@@@I~
    private static final String DRAWNREQDLG_LAST="DrawnReqDlg_Last";//~v@@@I~
    private static final String SHOWF2="ShowF2";                   //~v@@@I~
    private static final String FINAL_GAME="FinalGame";            //~v@@@I~
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
    private static final String KANDEAL_ANKAN="KanDealAnkan";    //~0406I~//~0407R~
    private static final String KANDEAL_CHANKAN="KanDealChankan";  //+0407I~
    private static final String WAITSELECT_PON="WaitSelectPon";    //~v@@@R~
    private static final String WAITSELECT_CHII="WaitSelectChii";  //~v@@@I~
                                                                   //~v@@@I~
    private static final int[] rbIDFirstDealer=new int[]{R.id.rbFirstDealer0,R.id.rbFirstDealer1,R.id.rbFirstDealer2,R.id.rbFirstDealer3,R.id.rbFirstDealer4};//~v@@@I~
    private static final int[] rbIDFinalGameCtrSet=new int[]{R.id.rbFinalSet1,R.id.rbFinalSet2,R.id.rbFinalSet3,R.id.rbFinalSet4};//~v@@@I~
    private static final int[] rbIDFinalGameCtrGame=new int[]{R.id.rbFinalGame1,R.id.rbFinalGame2,R.id.rbFinalGame3,R.id.rbFinalGame4};//~v@@@I~
    private static final int[] rbIDBTIOErr=new int[]{R.id.rbBTIOE_AfterTake,R.id.rbBTIOE_AfterDiscard,R.id.rbBTIOE_AfterPon,R.id.rbBTIOE_AfterKan,R.id.rbBTIOE_AfterRon,R.id.rbBTIOE_AfterOpen,R.id.rbBTIOE_AfterRobotTake};//~v@@@R~
    private UCheckBox cbPon_Test,cbChii_Test,cbKan_Test,/*cbPositioning,*/cbSkipDice,cbTakeDiscard,cbDrawnReqDlg_Last;//~v@@@R~
    private UCheckBox cbRon_Test;                                  //~v@@@I~
    private UCheckBox cbShowF2;                                    //~v@@@I~
    private UCheckBox cbFinalGame,cbLayoutFinalGame;               //~v@@@R~
    private UCheckBox cbDrawnLastShowStick;                        //~v@@@I~
    private UCheckBox cbChankan,cbRobotBird;                       //~v@@@R~
    private UCheckBox cbCloseOnConnect,cbRuleNoSync;                            //~v@@@I~
    private UCheckBox cbSuspend;                                   //~v@@@I~
    private UCheckBox cbBTIOErr;                                   //~v@@@I~
    private UCheckBox cbKanDeal,cbWaitSelectPon,cbWaitSelectChii;  //~v@@@R~
    private UCheckBox cbKanDealAnkan,cbKanDealChankan;                             //~0406I~//+0407R~
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
    	cbChii_Test=new UCheckBox(PView,R.id.cbChii_Test);         //~v@@@I~
    	cbKan_Test=new UCheckBox(PView,R.id.cbKan_Test);           //~v@@@I~
//  	cbPositioning=new UCheckBox(PView,R.id.cbPositioning);     //~v@@@R~
    	cbSkipDice=new UCheckBox(PView,R.id.cbSkipDice);           //~v@@@I~
    	cbTakeDiscard=new UCheckBox(PView,R.id.cbTakeDiscard);     //~v@@@I~
    	cbDrawnReqDlg_Last=new UCheckBox(PView,R.id.cbDrawnReqDlg_Last);//~v@@@I~
    	cbShowF2=new UCheckBox(PView,R.id.cbShowF2);               //~v@@@I~
    	cbFinalGame=new UCheckBox(PView,R.id.cbFinalGame);         //~v@@@I~
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
    	cbKanDealAnkan   =new UCheckBox(PView,R.id.cbKanDealAnkan);//~0406I~//~0407R~
    	cbKanDealChankan   =new UCheckBox(PView,R.id.cbKanDealChankan);//+0407I~
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
    	cbChii_Test.setStateInt(Pprop.getParameter(CHII_TEST,0));  //~v@@@I~
    	cbKan_Test.setStateInt(Pprop.getParameter(KAN_TEST,0));    //~v@@@I~
//  	cbPositioning.setStateInt(Pprop.getParameter(POSITIONING,0));//~v@@@R~
    	cbSkipDice.setStateInt(Pprop.getParameter(SKIPDICE,0));    //~v@@@I~
    	cbTakeDiscard.setStateInt(Pprop.getParameter(TAKEDISCARD,0));//~v@@@I~
    	cbDrawnReqDlg_Last.setStateInt(Pprop.getParameter(DRAWNREQDLG_LAST,0));//~v@@@I~
    	cbShowF2.setStateInt(Pprop.getParameter(SHOWF2,0));        //~v@@@I~
    	cbFinalGame.setStateInt(Pprop.getParameter(FINAL_GAME,0)); //~v@@@I~
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
    	cbKanDealAnkan.setStateInt(Pprop.getParameter(KANDEAL_ANKAN,0));//~0406I~//~0407R~
    	cbKanDealChankan.setStateInt(Pprop.getParameter(KANDEAL_CHANKAN,0));//+0407I~
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
			TestOption.option&=~TO2_RON_TEST;                      //~v@@@I~
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
        if (Pprop.getParameter(KANDEAL_CHANKAN,0)!=0)              //+0407I~
			TestOption.option2|=TO2_CHANKAN_DEAL;                  //+0407I~
        else                                                       //+0407I~
			TestOption.option2&=~TO2_CHANKAN_DEAL;                 //+0407I~
        if (Pprop.getParameter(WAITSELECT_PON,0)!=0)               //~v@@@R~
			TestOption.option2|=TO2_WAITSELECT_PON;                //~v@@@R~
        else                                                       //~v@@@I~
			TestOption.option2&=~TO2_WAITSELECT_PON;               //~v@@@R~
        if (Pprop.getParameter(WAITSELECT_CHII,0)!=0)              //~v@@@I~
			TestOption.option2|=TO2_WAITSELECT_CHII;               //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option2&=~TO2_WAITSELECT_CHII;              //~v@@@I~
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
//  		TestOption.option&=~TO_KAN_TEST;                       //~v@@@R~//~0401R~//~0405R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
			TestOption.option&=~TO_KAN_CHANKAN;                    //~v@@@I~
        TestOption.swDisableBT=Pprop.getParameter(DISABLEBT,0)!=0; //~v@@@I~
        TestOption.timingDisableBT=Pprop.getParameter(DISABLEBT_TIMING,0);//~v@@@I~
        if (Dump.Y) Dump.println("TODlg.setTestOption from Prop TO="+Integer.toHexString(TestOption.option));//~v@@@I~
        if (Dump.Y) Dump.println("TODlg.setTestOption from Prop TO2="+Integer.toHexString(TestOption.option2));//~v@@@I~
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
        changed+=updateProp(CHII_TEST,cbChii_Test.getStateInt());  //~v@@@I~
        changed+=updateProp(KAN_TEST,cbKan_Test.getStateInt());    //~v@@@I~
//      changed+=updateProp(POSITIONING,cbPositioning.getStateInt());//~v@@@R~
        changed+=updateProp(SKIPDICE,cbSkipDice.getStateInt());    //~v@@@I~
        changed+=updateProp(TAKEDISCARD,cbTakeDiscard.getStateInt());//~v@@@I~
        changed+=updateProp(DRAWNREQDLG_LAST,cbDrawnReqDlg_Last.getStateInt());//~v@@@I~
        changed+=updateProp(SHOWF2,cbShowF2.getStateInt());        //~v@@@I~
        changed+=updateProp(FINAL_GAME,cbFinalGame.getStateInt()); //~v@@@I~
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
        changed+=updateProp(KANDEAL_ANKAN,cbKanDealAnkan.getStateInt());//~0406I~//~0407R~
        changed+=updateProp(KANDEAL_CHANKAN,cbKanDealChankan.getStateInt());//+0407I~
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
