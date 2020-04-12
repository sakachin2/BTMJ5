//*CID://+DATER~:                             update#=  482;       //~v@@@R~//~9B16R~
//*****************************************************************//~v101I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                          //~v@@@R~
import android.view.View;
import android.widget.LinearLayout;

import com.btmtest.R;
import com.btmtest.TestOption;                                     //~v@@@R~
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.URadioGroup;
import com.btmtest.gui.USpinBtn;
import com.btmtest.gui.USpinner;
import com.btmtest.utils.Prop;                                     //~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;

import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.TestOption.*;
import static com.btmtest.dialog.RuleSettingEnum.*;                //~v@@@I~
import static com.btmtest.game.GConst.*;

public class RuleSettingOperation extends UFDlg                    //~v@@@R~
{                                                                  //~2C29R~
  	private static final int    TITLEID=R.string.Title_OperationSetting; //~v@@@R~//~0211R~
	private static final int    LAYOUTID=R.layout.setting_rule_operation;//~v@@@R~
	private static final int    HELP_TITLEID=TITLEID;              //~v@@@I~
	private static final String HELPFILE="RuleSettingOperation";//~v@@@R~//~9C13R~
    //**********************************************************   //~v@@@I~
    private static final int UNIT_SEC=10;                          //~9622I~//~v@@@M~
    //**********************************************************   //~v@@@R~
    private USpinBtn sbDelayPonKan,sbDelayTake,sbDelayLast;        //~v@@@I~
    private USpinBtn sbDelayDiscard,sbTimeoutTake/*,sbTimeoutTakeKan*/,sbTimeoutTakeRobot;//~v@@@R~
    private USpinBtn sbDelay2Touch;                                //~v@@@I~
//  private UCheckBox cbRuleWait;                                  //~v@@@I~//~9C03R~
    private UCheckBox cbPositioning;                               //~v@@@I~
    private USpinner spnSuspendPenalty;                            //~v@@@I~
    private USpinner spnSuspendPenaltyIOErr;                       //~v@@@I~
    private URadioGroup rgSuspend;                                 //~v@@@I~
//  private URadioGroup rg2Touch,rg2TouchRon;                      //~9C03I~//~9C09R~
//  private URadioGroup rg2Touch;                                  //~9C09I~//~9C10R~
//  private UCheckBox cb2TouchTOPon,cb2TouchTORon;                    //~9C03I~//~9C07R~//~9C09R~
    private UCheckBox cb2TouchTimeout;                             //~9C09I~
//  private UCheckBox cb2TouchPon,cb2TouchRon;                     //~9C07I~//~9C09R~
    private UCheckBox cb2TouchCancelableRon;                       //~9C10I~
    private UCheckBox cb2CheckRonable;                             //~0205I~
    //**********************************************************   //~v@@@I~
    private RuleSetting RSD;                                       //~v@@@I~
    private Prop curProp;                                          //~v@@@I~
    private boolean swChanged,swFixed;                             //~v@@@R~
                                                                   //~1A6fI~
    //******************************************                   //~v@@@M~
	public RuleSettingOperation()                                  //~v@@@R~
    {
        if (Dump.Y) Dump.println("RuleSettingOperation.defaultConstructor");//~v@@@R~
    }
    public static RuleSettingOperation newInstance(RuleSetting Pparent)//~v@@@R~
    {                                                              //~v@@@I~
        RuleSettingOperation dlg=new RuleSettingOperation();       //~v@@@R~
        UFDlg.setBundle(dlg,TITLEID,LAYOUTID,                      //~v@@@R~
                    UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN,//~v@@@I~
                    HELP_TITLEID,HELPFILE);                   //~v@@@I~//~9C13R~
        dlg.RSD=Pparent;                                           //~v@@@I~
        return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@M~
    @Override
    protected void initLayout(View PView)                            //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("RuleSettingOperation.initLayout");//~v@@@R~
        RSD.swChildInitializing=true;                              //~v@@@I~
        swFixed=RSD.swFixed;                                       //~v@@@I~
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
    	if (Dump.Y) Dump.println("RuleSettingOperation.getDialogWidth swSmallDevice="+AG.swSmallDevice+",ww="+ww);//~9819I~//~v@@@I~
        return ww;                                                 //~9819I~//~v@@@I~
    }                                                              //~9819I~//~v@@@I~
	//***********************************************************      //~1613I~//~v@@@R~
    private void setupLayout(View PView)                           //~v@@@I~
    {                                                              //~v@@@I~
        LinearLayout llSpinBtn;
        //*delay                                                       //~v@@@I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBDelayPonKan);//~v@@@I~
    	sbDelayPonKan=USpinBtn.newInstance(llSpinBtn,DEFAULT_DELAY_PONKAN_MIN,DEFAULT_DELAY_PONKAN_MAX,DEFAULT_DELAY_PONKAN_INC,DEFAULT_DELAY_PONKAN);//~v@@@I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBDelayTake);//~v@@@I~
    	sbDelayTake=USpinBtn.newInstance(llSpinBtn,DEFAULT_DELAY_TAKE_MIN,DEFAULT_DELAY_TAKE_MAX,DEFAULT_DELAY_TAKE_INC,DEFAULT_DELAY_TAKE);//~v@@@I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBDelayLast);//~v@@@I~
    	sbDelayLast=USpinBtn.newInstance(llSpinBtn,DEFAULT_DELAY_LAST_MIN,DEFAULT_DELAY_LAST_MAX,DEFAULT_DELAY_LAST_INC,DEFAULT_DELAY_LAST);//~v@@@I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBDelayDiscard);//~v@@@I~
    	sbDelayDiscard=USpinBtn.newInstance(llSpinBtn,DEFAULT_DELAY_DISCARD_MIN,DEFAULT_DELAY_DISCARD_MAX,DEFAULT_DELAY_DISCARD_INC,DEFAULT_DELAY_DISCARD);//~v@@@I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBDelay2Touch);//~v@@@I~
    	sbDelay2Touch=USpinBtn.newInstance(llSpinBtn,DEFAULT_DELAY_2TOUCH_MIN,DEFAULT_DELAY_2TOUCH_MAX,DEFAULT_DELAY_2TOUCH_INC,DEFAULT_DELAY_2TOUCH);//~v@@@I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBTimeoutTake);//~v@@@I~
    	sbTimeoutTake=USpinBtn.newInstance(llSpinBtn,DEFAULT_TIMEOUT_TAKE_MIN,DEFAULT_TIMEOUT_TAKE_MAX,DEFAULT_TIMEOUT_TAKE_INC,DEFAULT_TIMEOUT_TAKE);//~v@@@I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBTimeoutTakeRobot);//~v@@@I~
    	sbTimeoutTakeRobot=USpinBtn.newInstance(llSpinBtn,DEFAULT_TIMEOUT_TAKEROBOT_MIN,DEFAULT_TIMEOUT_TAKEROBOT_MAX,DEFAULT_TIMEOUT_TAKEROBOT_INC,DEFAULT_TIMEOUT_TAKEROBOT);//~v@@@I~
//  	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBTimeoutTakeKan);//~v@@@R~
//  	sbTimeoutTakeKan=USpinBtn.newInstance(llSpinBtn,DEFAULT_TIMEOUT_TAKEKAN_MIN,DEFAULT_TIMEOUT_TAKEKAN_MAX,DEFAULT_TIMEOUT_TAKEKAN_INC,DEFAULT_TIMEOUT_TAKEKAN);//~v@@@R~
        //*2touch                                                  //~v@@@I~//~9C03R~
//      cbRuleWait=new UCheckBox(PView,R.id.cbRuleWait);    //2touchMode//~9B16R~//~9C03R~
//  	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.ll2TouchPonKanChii);//~9C03I~//~9C09R~
//      rg2Touch=new URadioGroup(llSpinBtn,R.id.rg2Touch,0/*parm to listener*/,rbs2Touch);//~9C03I~//~9C07R~
//      cb2TouchPon=new UCheckBox(llSpinBtn,R.id.cb2TouchWithCancel);    //2touchMode//~9C07I~//~9C09R~
//      cb2TouchTO=new UCheckBox(PView,R.id.cb2TouchTimeoutPon);    //2touchMode//~9C03R~//~9C07R~
//      cb2TouchTOPon=new UCheckBox(llSpinBtn,R.id.cb2TouchTimeout);    //2touchMode//~9C07I~//~9C09R~
//  	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.ll2TouchRonTsumo);//~9C03I~//~9C09R~
//      rg2TouchRon=new URadioGroup(llSpinBtn,R.id.rg2Touch,0/*parm to listener*/,rbs2Touch);//~9C03I~//~9C07R~
//      cb2TouchRon=new UCheckBox(llSpinBtn,R.id.cb2TouchWithCancel);    //2touchMode//~9C07I~//~9C09R~
//      cb2TouchTORon=new UCheckBox(llSpinBtn,R.id.cb2TouchTimeout);    //2touchMode//~9C03R~//~9C09R~
//      rg2Touch=new URadioGroup(PView,R.id.rg2Touch,0/*parm to listener*/,rbs2Touch);//~9C09I~//~9C10R~
        cb2TouchCancelableRon=new UCheckBox(PView,R.id.cb2TouchCancelableRon);    //2touchMode//~9C10I~
        cb2CheckRonable=new UCheckBox(PView,R.id.cbCheckRonable);    //2touchMode//~0205I~
        cb2TouchTimeout=new UCheckBox(PView,R.id.cb2TouchTimeout);    //2touchMode//~9C09R~
                                                                   //~9C03I~
        //*positioning                                             //~v@@@I~
        cbPositioning=new UCheckBox(PView,R.id.cbPositioning);     //~v@@@I~
        //*suspend                                                 //~v@@@I~
        spnSuspendPenalty=new USpinner(PView,R.id.spnSuspendPenalty);//~v@@@I~
        spnSuspendPenalty.setArray(R.array.SuspendPenalty);        //~v@@@I~
        spnSuspendPenaltyIOErr=new USpinner(PView,R.id.spnSuspendPenaltyIOErr);//~v@@@I~
        spnSuspendPenaltyIOErr.setArray(R.array.SuspendPenalty);   //~v@@@R~
        rgSuspend=new URadioGroup(PView,R.id.rgSuspendOption,0,rbIDSuspendOption);//~v@@@I~
                                                                   //~v@@@I~
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
        if (Dump.Y) Dump.println("RuleSettingOperation.onClickOK");//~v@@@R~
	    getValue();                                                //~v@@@I~
        if (swChanged)                                             //~v@@@I~
	        RSD.swChangedOperation=swChanged;                      //~v@@@R~
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
    //*delay                                                       //~v@@@I~
        sbDelayPonKan.setVal(Pprop.getParameter(getKeyRS(RSID_DELAY_PONKAN),DEFAULT_DELAY_PONKAN),swFixed);//~v@@@I~
        sbDelayTake.setVal(Pprop.getParameter(getKeyRS(RSID_DELAY_TAKE),DEFAULT_DELAY_TAKE),swFixed);//~v@@@I~
        sbDelayLast.setVal(Pprop.getParameter(getKeyRS(RSID_DELAY_LAST),DEFAULT_DELAY_LAST),swFixed);//~v@@@I~
        sbDelayDiscard.setVal(Pprop.getParameter(getKeyRS(RSID_DELAY_DISCARD),DEFAULT_DELAY_DISCARD),swFixed);//~v@@@I~
        sbDelay2Touch.setVal(Pprop.getParameter(getKeyRS(RSID_DELAY_2TOUCH),DEFAULT_DELAY_2TOUCH),swFixed);//~v@@@I~
        sbTimeoutTake.setVal(Pprop.getParameter(getKeyRS(RSID_TIMEOUT_TAKE),DEFAULT_TIMEOUT_TAKE),swFixed);//~v@@@I~
        sbTimeoutTakeRobot.setVal(Pprop.getParameter(getKeyRS(RSID_TIMEOUT_TAKEROBOT),DEFAULT_TIMEOUT_TAKEROBOT),swFixed);//~v@@@I~
//      sbTimeoutTakeKan.setVal(Pprop.getParameter(getKeyRS(RSID_TIMEOUT_TAKEKAN),DEFAULT_TIMEOUT_TAKEKAN),swFixed);//~v@@@R~
    //*2Touch                                                        //~v@@@I~//~9C03R~
//      cbRuleWait.setStateInt(Pprop.getParameter(getKeyRS(RSID_RULEWAIT),0/*default false*/),swFixed);//~v@@@I~//~9C03R~
//      cb2TouchTO.setStateInt(Pprop.getParameter(getKeyRS(RSID_DELAY_2TOUCH_TO_PON),0/*default false*/),swFixed);//~9C03R~//~9C07R~
//      cb2TouchTORon.setStateInt(Pprop.getParameter(getKeyRS(RSID_DELAY_2TOUCH_TO_RON),0/*default false*/),swFixed);//~9C03R~//~9C07R~
//      rg2Touch.setCheckedID(Pprop.getParameter(getKeyRS(RSID_DELAY_2TOUCH_PON),0),swFixed);//~9C03R~//~9C07R~
//      rg2TouchRon.setCheckedID(Pprop.getParameter(getKeyRS(RSID_DELAY_2TOUCH_RON),0),swFixed);//~9C03I~//~9C07R~
//      cb2TouchTOPon.setStateInt(Pprop.getParameter(getKeyRS(RSID_2TOUCH_TO_PON),0/*default false*/),swFixed);//~9C07I~//~9C09R~
//      cb2TouchTORon.setStateInt(Pprop.getParameter(getKeyRS(RSID_2TOUCH_TO_RON),0/*default false*/),swFixed);//~9C07I~//~9C09R~
//      cb2TouchPon.setStateInt(Pprop.getParameter(getKeyRS(RSID_2TOUCH_PON),0/*default false*/),swFixed);//~9C07I~//~9C09R~
//      cb2TouchRon.setStateInt(Pprop.getParameter(getKeyRS(RSID_2TOUCH_RON),0/*default false*/),swFixed);//~9C07I~//~9C09R~//~9C10R~
//      rg2Touch.setCheckedID(Pprop.getParameter(getKeyRS(RSID_2TOUCH),0),swFixed);//~9C09I~//~9C10R~
        cb2TouchCancelableRon.setStateInt(Pprop.getParameter(getKeyRS(RSID_2TOUCH_CANCELABLE_RON),0/*default false*/),swFixed);//~9C10I~
        cb2CheckRonable.setStateInt(Pprop.getParameter(getKeyRS(RSID_CHECK_RONABLE),0/*default false*/),swFixed);//~0205I~
        cb2TouchTimeout.setStateInt(Pprop.getParameter(getKeyRS(RSID_2TOUCH_TIMEOUT),0/*default false*/),swFixed);//~9C09I~
    //*positioning                                                 //~v@@@I~
        cbPositioning.setStateInt(Pprop.getParameter(getKeyRS(RSID_POSITIONING),0/*default false*/),swFixed);//~v@@@I~
    //*suspend                                                     //~v@@@I~
        spnSuspendPenalty.select(Pprop.getParameter(getKeyRS(RSID_SUSPEND_PENALTY),0),swFixed);//~v@@@I~
        spnSuspendPenaltyIOErr.select(Pprop.getParameter(getKeyRS(RSID_SUSPEND_PENALTYIOERR),0),swFixed);//~v@@@I~
        rgSuspend.setCheckedID(Pprop.getParameter(getKeyRS(RSID_SUSPEND),0),swFixed);//~v@@@I~
    }                                                              //~v@@@I~
	//***********************************************************  //~v@@@I~
    private boolean dialog2Properties()                            //~v@@@I~
    {                                                              //~v@@@I~
    	int  changed=0;                                            //~v@@@I~
    //*******************                                          //~v@@@I~
    //*delay                                                       //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_DELAY_PONKAN),sbDelayPonKan.getVal());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_DELAY_TAKE),sbDelayTake.getVal());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_DELAY_LAST),sbDelayLast.getVal());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_DELAY_DISCARD),sbDelayDiscard.getVal());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_DELAY_2TOUCH),sbDelay2Touch.getVal());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_TIMEOUT_TAKE),sbTimeoutTake.getVal());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_TIMEOUT_TAKEROBOT),sbTimeoutTakeRobot.getVal());//~v@@@I~
//      changed+=updateProp(getKeyRS(RSID_TIMEOUT_TAKEKAN),sbTimeoutTakeKan.getVal());//~v@@@R~
    //*2Touch                                                      //~9C03R~
//      changed+=updateProp(getKeyRS(RSID_RULEWAIT),cbRuleWait.getStateInt());//~v@@@I~//~9C03R~
//      changed+=updateProp(getKeyRS(RSID_DELAY_2TOUCH_TO_PON),cb2TouchTO.getStateInt());//~9C03R~//~9C07R~
//      changed+=updateProp(getKeyRS(RSID_DELAY_2TOUCH_TO_RON),cb2TouchTORon.getStateInt());//~9C03I~//~9C07R~
//      changed+=updateProp(getKeyRS(RSID_DELAY_2TOUCH_PON),rg2Touch.getCheckedID());//~9C03R~//~9C07R~
//      changed+=updateProp(getKeyRS(RSID_DELAY_2TOUCH_RON),rg2TouchRon.getCheckedID());//~9C03I~//~9C07R~
//      changed+=updateProp(getKeyRS(RSID_2TOUCH_TO_PON),cb2TouchTOPon.getStateInt());//~9C07I~//~9C09R~
//      changed+=updateProp(getKeyRS(RSID_2TOUCH_TO_RON),cb2TouchTORon.getStateInt());//~9C07I~//~9C09R~
//      changed+=updateProp(getKeyRS(RSID_2TOUCH_PON),cb2TouchPon.getStateInt());//~9C07I~//~9C09R~
//      changed+=updateProp(getKeyRS(RSID_2TOUCH_RON),cb2TouchRon.getStateInt());//~9C07I~//~9C09R~
//      changed+=updateProp(getKeyRS(RSID_2TOUCH),rg2Touch.getCheckedID());//~9C09I~//~9C10R~
        changed+=updateProp(getKeyRS(RSID_2TOUCH_CANCELABLE_RON),cb2TouchCancelableRon.getStateInt());//~9C10I~
        changed+=updateProp(getKeyRS(RSID_CHECK_RONABLE),cb2CheckRonable.getStateInt());//~0205I~
        changed+=updateProp(getKeyRS(RSID_2TOUCH_TIMEOUT),cb2TouchTimeout.getStateInt());//~9C09I~
    //*positioning                                                 //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_POSITIONING),cbPositioning.getStateInt());//~v@@@I~
    //*suspend                                                     //~v@@@I~
        changed+=updateProp(getKeyRS(RSID_SUSPEND_PENALTY),spnSuspendPenalty.getSelectedIndex());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_SUSPEND_PENALTYIOERR),spnSuspendPenaltyIOErr.getSelectedIndex());//~v@@@I~
        changed+=updateProp(getKeyRS(RSID_SUSPEND),rgSuspend.getCheckedID());//~v@@@I~
                                                                   //~v@@@I~
        if (Dump.Y) Dump.println("RuleSettingOperation.dialog2Properties changed="+changed);//~v@@@R~
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
    //*if >10, unit=10ms by testoption                             //~v@@@I~
    //************************************************************************//~v@@@I~
    public static int adjustTime(int Psec)                         //~v@@@I~
    {                                                              //~v@@@I~
    	int rc=Psec;                                               //~v@@@I~
        if (rc<UNIT_SEC)                                           //~v@@@I~
        	rc*=1000;                                              //~v@@@I~
        else                                                       //~v@@@I~
        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0)            //~v@@@I~
        	rc*=10;		//by 10ms unit, 20-->200ms                 //~v@@@I~
        else                                                       //~v@@@I~
        	rc*=1000;	//ms                                       //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.adjustTime Psec="+Psec+",rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //************************************************************************//~v@@@I~
    public static int getDelayPonKan()                             //~v@@@I~
    {                                                              //~v@@@I~
		int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_PONKAN),DEFAULT_DELAY_PONKAN);//~v@@@I~
//      if (rc<10)                                                 //~v@@@I~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~v@@@R~
//            rc*=1000;   //ms                                     //~v@@@R~
//        else                                                     //~v@@@R~
//            rc*=10;     //10ms unit 20-->200ms                   //~v@@@R~
        rc=adjustTime(rc);                                         //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.getDelayPonKan:"+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    public static int getDelayTake()                               //~v@@@I~
    {                                                              //~v@@@I~
		int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_TAKE),DEFAULT_DELAY_TAKE);//~v@@@I~
//      if (rc<10)                                                 //~v@@@I~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~v@@@R~
//            rc*=1000;   //ms                                     //~v@@@R~
//        else                                                     //~v@@@R~
//            rc*=10;     //10ms unit 20-->200ms                   //~v@@@R~
        rc=adjustTime(rc);                                         //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.getDelayTake:"+rc);  //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    public static int getDelayLast()                               //~v@@@I~
    {                                                              //~v@@@I~
		int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_LAST),DEFAULT_DELAY_LAST);//~v@@@I~
//      if (rc<10)                                                 //~v@@@I~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~v@@@R~
//            rc*=1000;   //ms                                     //~v@@@R~
//        else                                                     //~v@@@R~
//            rc*=10;     //10ms unit 20-->200ms                   //~v@@@R~
        rc=adjustTime(rc);                                         //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.getDelayLast:"+rc);  //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    public static int getDelayDiscard()                            //~v@@@I~
    {                                                              //~v@@@I~
		int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_DISCARD),DEFAULT_DELAY_DISCARD);//~v@@@I~
//      if (rc<10)                                                 //~v@@@I~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~v@@@R~
//            rc*=1000;   //ms                                     //~v@@@R~
//        else                                                     //~v@@@R~
//            rc*=10;     //10ms unit 20-->200ms                   //~v@@@R~
        rc=adjustTime(rc);                                         //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.getDelayLast:"+rc);  //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    public static int getDelay2Touch()                             //~v@@@I~
    {                                                              //~v@@@I~
		int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_2TOUCH),DEFAULT_DELAY_2TOUCH);//~v@@@I~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~v@@@R~
//            rc*=1000;   //ms                                     //~v@@@R~
//        else                                                     //~v@@@R~
//            rc*=10;     //10ms unit 20-->200ms                   //~v@@@R~
        rc=adjustTime(rc);                                         //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.getDelayLast:"+rc);  //~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    public static int getTimeoutTake()                             //~v@@@I~
    {                                                              //~v@@@I~
		int rc=AG.ruleProp.getParameter(getKeyRS(RSID_TIMEOUT_TAKE),DEFAULT_TIMEOUT_TAKE);//~v@@@I~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~v@@@R~
//            rc*=1000;   //ms                                     //~v@@@R~
//        else                                                     //~v@@@R~
//            rc*=10;     //10ms unit 20-->200ms                   //~v@@@R~
        rc=adjustTime(rc);                                         //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.getTimeoutTake:"+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    public static int getTimeoutTakeRobot()                        //~v@@@I~
    {                                                              //~v@@@I~
		int rc;                                                    //+0229I~
    	int human=getTimeoutTake();                               //+0229I~
        if (human==0)                                              //+0229I~
        {                                                          //+0229I~
			rc=AG.ruleProp.getParameter(getKeyRS(RSID_TIMEOUT_TAKEROBOT),DEFAULT_TIMEOUT_TAKEROBOT);//~v@@@I~//+0229R~
	        rc=adjustTime(rc);                                         //~v@@@I~//+0229I~
        }                                                          //+0229I~
        else                                                       //+0229I~
			rc=human;                                              //+0229I~
    	if (Dump.Y) Dump.println("RuleSetting.getTimeoutTakeRobot:"+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //**************************************                     //~v@@@R~
//    public static int getTimeoutTakeKan()                        //~v@@@R~
//    {                                                            //~v@@@R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_TIMEOUT_TAKEKAN),DEFAULT_TIMEOUT_TAKEKAN);//~v@@@R~
//        if ((TestOption.option2 & TO2_UNIT_MILISEC)!=0 && rc<UNIT_SEC)//~v@@@R~
//            rc*=1000;   //ms                                     //~v@@@R~
//        else                                                     //~v@@@R~
//            rc*=10;     //10ms unit 20-->200ms                   //~v@@@R~
//        if (Dump.Y) Dump.println("RuleSetting.getTimeoutTakeKan:"+rc);//~v@@@R~
//        return rc;                                               //~v@@@R~
//    }                                                            //~v@@@R~
//    //**************************************                     //~v@@@R~//~9B16R~//~9C03R~
//    public static boolean isRuleWait()                           //~v@@@R~//~9B16R~//~9C03R~
//    {                                                            //~v@@@R~//~9B16R~//~9C03R~
//        int def=0;  //true                                       //~v@@@R~//~9B16R~//~9C03R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_RULEWAIT),def)!=0;//~v@@@R~//~9B16R~//~9C03R~
//        if (Dump.Y) Dump.println("RuleSetting.isRuleWait rc="+rc);//~v@@@R~//~9B16R~//~9C03R~
//        return rc;                                               //~v@@@R~//~9B16R~//~9C03R~
//    }                                                            //~v@@@R~//~9B16R~//~9C03R~
//    //**************************************                       //~9C03I~//~9C09R~
//    public static boolean is2TouchTimeoutPonKanChii()              //~9C03I~//~9C09R~
//    {                                                              //~9C03I~//~9C09R~
//        int def=0;  //true                                         //~9C03I~//~9C09R~
////      boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_2TOUCH_TO_PON),def)!=0;//~9C03R~//~9C07R~//~9C09R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_2TOUCH_TO_PON),def)!=0;//~9C07I~//~9C09R~
//        if (Dump.Y) Dump.println("RuleSetting.is2TouchTimeoutPonKanChii rc="+rc);//~9C03I~//~9C09R~
//        return rc;                                                 //~9C03I~//~9C09R~
//    }                                                              //~9C03I~//~9C09R~
//    //**************************************                       //~9C03I~//~9C09R~
//    public static boolean is2TouchTimeoutRon()                     //~9C03I~//~9C09R~
//    {                                                              //~9C03I~//~9C09R~
//        int def=0;  //true                                         //~9C03I~//~9C09R~
////      boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_2TOUCH_TO_RON),def)!=0;//~9C03I~//~9C07R~//~9C09R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_2TOUCH_TO_RON),def)!=0;//~9C07I~//~9C09R~
//        if (Dump.Y) Dump.println("RuleSetting.is2TouchTimeoutRon rc="+rc);//~9C03I~//~9C09R~
//        return rc;                                                 //~9C03I~//~9C09R~
//    }                                                              //~9C03I~//~9C09R~
    //**************************************                       //~9C09I~
    public static boolean is2TouchTimeout()                        //~9C09I~
    {                                                              //~9C09I~
        int def=0;  //true                                         //~9C09I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_2TOUCH_TIMEOUT),def)!=0;//~9C09I~
        if (Dump.Y) Dump.println("RuleSetting.is2TouchTimeout rc="+rc);//~9C09I~
        return rc;                                                 //~9C09I~
    }                                                              //~9C09I~
//    //**************************************                       //~9C03I~//~9C07R~
//    public static int get2TouchOptionPonKanChii()                  //~9C03I~//~9C07R~
//    {                                                              //~9C03I~//~9C07R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_2TOUCH_PON),0);//~9C03R~//~9C07R~
//        if (Dump.Y) Dump.println("RuleSetting.get2TouchOptionPonKanChii rc="+rc);//~9C03I~//~9C07R~
//        return rc;                                                 //~9C03I~//~9C07R~
//    }                                                              //~9C03I~//~9C07R~
//    //**************************************                       //~9C03I~//~9C07R~
//    public static int get2TouchOptionRon()                         //~9C03I~//~9C07R~
//    {                                                              //~9C03I~//~9C07R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_DELAY_2TOUCH_RON),0);//~9C03I~//~9C07R~
//        if (Dump.Y) Dump.println("RuleSetting.get2TouchOptionRon rc="+rc);//~9C03I~//~9C07R~
//        return rc;                                                 //~9C03I~//~9C07R~
//    }                                                              //~9C03I~//~9C07R~
//    //**************************************                       //~9C07I~//~9C09R~
//    public static boolean is2TouchWithCancelPonKanChii()               //~9C07I~//~9C09R~
//    {                                                              //~9C07I~//~9C09R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_2TOUCH_PON),0)!=0;//~9C07I~//~9C09R~
//        if (Dump.Y) Dump.println("RuleSetting.is2TouchWithCancelPonKanChii rc="+rc);//~9C07I~//~9C09R~
//        return rc;                                                 //~9C07I~//~9C09R~
//    }                                                              //~9C07I~//~9C09R~
//    //**************************************                       //~9C07I~//~9C09R~
//    public static boolean is2TouchWithCancelRon()                  //~9C07I~//~9C09R~
//    {                                                              //~9C07I~//~9C09R~
//        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_2TOUCH_RON),0)!=0;//~9C07I~//~9C09R~
//        if (Dump.Y) Dump.println("RuleSetting.is2TouchWithCancelRon rc="+rc);//~9C07I~//~9C09R~
//        return rc;                                                 //~9C07I~//~9C09R~
//    }                                                              //~9C07I~//~9C09R~
//    //**************************************                       //~9C09I~//~9C10R~
//    private static int get2TouchCancelable()                       //~9C09I~//~9C10R~
//    {                                                              //~9C09I~//~9C10R~
//        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_2TOUCH),0);  //~9C09I~//~9C10R~
//        if (Dump.Y) Dump.println("RuleSetting.get2TouchCancelable rc="+rc);//~9C09I~//~9C10R~
//        return rc;                                                 //~9C09I~//~9C10R~
//    }                                                              //~9C09I~//~9C10R~
//    //**************************************                       //~9C09I~//~9C10R~
//    public static boolean is2TouchWithCancelPonKanChii()           //~9C09I~//~9C10R~
//    {                                                              //~9C09I~//~9C10R~
//        int opt=get2TouchCancelable();                             //~9C09I~//~9C10R~
//        boolean rc=opt==STOP2T_YES;                                //~9C09I~//~9C10R~
//        if (Dump.Y) Dump.println("RuleSetting.is2TouchWithCancelPonKanChii rc="+rc);//~9C09I~//~9C10R~
//        return rc;                                                 //~9C09I~//~9C10R~
//    }                                                              //~9C09I~//~9C10R~
//    //**************************************                       //~9C09I~//~9C10R~
//    public static boolean is2TouchWithCancelRon()                  //~9C09I~//~9C10R~
//    {                                                              //~9C09I~//~9C10R~
//        int opt=get2TouchCancelable();                             //~9C09I~//~9C10R~
//        boolean rc=opt!=STOP2T_NO;                                 //~9C09I~//~9C10R~
//        if (Dump.Y) Dump.println("RuleSetting.is2TouchWithCancelRon rc="+rc);//~9C09I~//~9C10R~
//        return rc;                                                 //~9C09I~//~9C10R~
//    }                                                              //~9C09I~//~9C10R~
    //**************************************                       //~9C10I~
    public static boolean is2TouchCancelableRon()                  //~9C10I~
    {                                                              //~9C10I~
        int def=0;  //false                                        //~9C10I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_2TOUCH_CANCELABLE_RON),def)!=0;//~9C10I~
        if (Dump.Y) Dump.println("RuleSetting.is2TouchCancelableRon rc="+rc);//~9C10I~
        return rc;                                                 //~9C10I~
    }                                                              //~9C10I~
    //**************************************                       //~0205I~
    public static boolean isCheckRonable()                         //~0205I~
    {                                                              //~0205I~
        int def=0;  //false                                        //~0205I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_CHECK_RONABLE),def)!=0;//~0205I~
        if (Dump.Y) Dump.println("RuleSetting.isCheckRonable rc="+rc);//~0205I~
        return rc;                                                 //~0205I~
    }                                                              //~0205I~
    //**************************************                       //~v@@@I~
	public static boolean isPositioningSkip()                      //~v@@@I~
    {                                                              //~v@@@I~
		int def=0;	//true                                         //~v@@@I~
        boolean rc=AG.ruleProp.getParameter(getKeyRS(RSID_POSITIONING),def)!=0;//~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.isPositioningSkip rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static int getSuspendOption()                           //~v@@@I~
    {                                                              //~v@@@I~
        int rc=AG.ruleProp.getParameter(getKeyRS(RSID_SUSPEND),0);//~v@@@R~
        if (Dump.Y) Dump.println("RuleSetting.getSuspendOption rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static int getSuspendPenalty()                          //~v@@@I~
    {                                                              //~v@@@I~
        int idx=AG.ruleProp.getParameter(getKeyRS(RSID_SUSPEND_PENALTY),0);//~v@@@R~
        int rc=intsSuspendPenalty[idx];                            //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.getSuspendPenalty idx="+idx+",rc="+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
	public static int getSuspendPenaltyIOErr()                     //~v@@@I~
    {                                                              //~v@@@I~
        int idx=AG.ruleProp.getParameter(getKeyRS(RSID_SUSPEND_PENALTYIOERR),0);//~v@@@I~
        int rc=intsSuspendPenalty[idx];                            //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.getSuspendPenaltyIOErr idx="+idx+",rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*********************************************************    //~v@@@I~
    public static void setSuspendOption(View PView,boolean PswFixed)//~v@@@R~
    {                                                              //~v@@@I~
    	USpinner spnSuspendPenalty=new USpinner(PView,R.id.spnSuspendPenalty);//~v@@@I~
    	USpinner spnSuspendPenaltyIOErr=new USpinner(PView,R.id.spnSuspendPenaltyIOErr);//~v@@@I~
        spnSuspendPenalty.setArray(R.array.SuspendPenalty);        //~v@@@I~
        spnSuspendPenaltyIOErr.setArray(R.array.SuspendPenalty);   //~v@@@I~
    	URadioGroup rgSuspend=new URadioGroup(PView,R.id.rgSuspendOption,0,rbIDSuspendOption);//~v@@@I~
        spnSuspendPenalty.select(AG.ruleProp.getParameter(getKeyRS(RSID_SUSPEND_PENALTY),0),PswFixed);//~v@@@R~
        spnSuspendPenaltyIOErr.select(AG.ruleProp.getParameter(getKeyRS(RSID_SUSPEND_PENALTYIOERR),0),PswFixed);//~v@@@I~
        rgSuspend.setCheckedID(AG.ruleProp.getParameter(getKeyRS(RSID_SUSPEND),0),PswFixed);//~v@@@R~
                                                                   //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.setSuspendOption");  //~v@@@I~
    }                                                              //~v@@@I~
    //**********************************************************************//~9C04I~
    public static boolean isForceAcceptEvenNotDiscoverable()       //~9C04I~
    {                                                              //~9C04I~
        boolean rc=true;                                           //~9C04I~
        if (Dump.Y) Dump.println("RuleSettingOperation.isForceAcceptEvenNotDiscoverable rc="+rc);//~9C04I~
        return rc;                                                 //~9C04I~
    }                                                              //~9C04I~
}//class                                                           //~v@@@R~
