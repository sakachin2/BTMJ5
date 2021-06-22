//*CID://+va9fR~:                             update#=  467;       //~va18R~//~va9fR~
//*****************************************************************//~v101I~
//2021/06/17 va9f correct reason of reverse orientation did not work(fix orientation was called)//~va9fI~
//                not work because onConfigurationChanged is not fired by RVERSE request//~va9fI~
//2020/10/18 va18 option to diaplay WinAnyway button               //~va18I~
//2020/04/27 va06:BGM                                              //~va06I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import com.btmtest.R;
import com.btmtest.game.GC;
import com.btmtest.game.Status;
import com.btmtest.gui.URadioGroup;
import com.btmtest.gui.USpinBtn;
import com.btmtest.utils.Prop;                                     //~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.gui.UCheckBox;
import com.btmtest.utils.UView;
import com.btmtest.utils.sound.Sound;


import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.dialog.PrefSettingEnum.*;                  //~v@@@R~
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Status.*;

//~v@@@I~
public class PrefSetting extends SettingDlg                        //~v@@@R~
{                                                                  //~2C29R~
	public  static final String PROP_NAME="PreferenceSetting";     //~v@@@I~
	private static final int    TITLEID=R.string.Title_PrefSetting;//~v@@@R~
	private static final int    LAYOUTID=R.layout.setting_preference;    //~v@@@R~
	private static final int    HELP_TITLEID=R.string.Title_PrefSetting;//~v@@@R~
	private static final String HELPFILE="SettingPreference";      //~v@@@R~
                                                                   //~v@@@I~
    private SettingDlg settingDlg;                                 //~v@@@I~
                                                                   //~v@@@I~
    private URadioGroup rgOrientation;                             //~v@@@I~
    private static final int[] rbIDOrientation=new int[]{R.id.rbOriSelectMenu,R.id.rbOriPortrait,R.id.rbOriPortraitReverse,R.id.rbOriLandscape,R.id.rbOriLandscapeReverse};//~v@@@R~
	public  static final int    PS_ORIENTATION_SELECTMENU=0;       //~v@@@I~
	public  static final int    PS_ORIENTATION_PORTRAIT=1;  //=ActivityInfo//~v@@@R~
	public  static final int    PS_ORIENTATION_PORTRAIT_REVERSE=2; //~v@@@I~
	public  static final int    PS_ORIENTATION_LANDSCAPE=3;        //~v@@@R~
	public  static final int    PS_ORIENTATION_LANDSCAPE_REVERSE=4;//~v@@@I~
                                                                   //~v@@@I~
    private URadioGroup rgBGM;                                     //~va06I~
    private static final int[] rbIDBGM=new int[]{R.id.rbBGMNo,R.id.rbBGMSame,R.id.rbBGM4Seasons,R.id.rbBGM4SeasonsFast};//~va06R~
	public  static final int    PS_BGM_NO=0;                       //~va06I~
	public  static final int    PS_BGM_COMMON=1;                   //~va06R~
	public  static final int    PS_BGM_4SEASONS=2;                 //~va06R~
	public  static final int    PS_BGM_4SEASONS_FAST=3;            //~va06I~
	public  static final int    PS_BGM_DEFAULT=PS_BGM_COMMON;      //~va06I~
                                                                   //~va06I~
    public static final int 	DEFAULT_VOLUME_MIN=0;              //~v@@@I~
    public static final int 	DEFAULT_VOLUME_MAX=10;             //~v@@@I~
    public static final int 	DEFAULT_VOLUME_INC=1;              //~v@@@I~
    public static final int 	DEFAULT_VOLUME=5;                  //~v@@@I~
                                                                   //~v@@@I~
                                                                   //~v@@@I~
    private UCheckBox cbDelRiverTileTaken;                         //~v@@@I~
    private UCheckBox cbNoRelatedRule;                             //~v@@@I~
    private UCheckBox cbNoTakeButton,cbNoDiscardButton;            //~v@@@I~
    private UCheckBox cbNoAnywayButton;                            //~va18I~
    private UCheckBox cbNoSound,cbBeepOnly;                        //~v@@@R~
//  private UCheckBox cbPortraitReverse;                           //+va9fR~
//  private boolean swFixedParm;                                   //~v@@@R~
    private Prop curPropOld;                                 //~v@@@I~
    private USpinBtn sbVolume;                                     //~v@@@I~
    private USpinBtn sbVolumeBGM;                                  //~va06I~
    private int changedSound;                                      //~v@@@I~
    private int changedBGM;                                        //~va06I~
    private int changedBtn;                                        //~v@@@I~
    //******************************************                   //~v@@@I~
    //******************************************                   //~v@@@M~
	public PrefSetting()                                           //~v@@@R~
    {
        super();
        AG.aPrefSetting=this;                                      //~v@@@I~
        if (Dump.Y) Dump.println("SettingDlg.defaultConstructor"); //~v@@@I~
    }
    //******************************************                   //~v@@@I~
    public static PrefSetting newInstance()                        //~v@@@R~
    {                                                              //~v@@@I~
	    return PrefSetting.newInstance(false/*PswFixed*/);         //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    public static PrefSetting newInstance(boolean PswFixed)        //~v@@@I~
    {                                                              //~v@@@I~
        PrefSetting dlg=new PrefSetting();                         //~v@@@I~
        UFDlg.setBundle(dlg,TITLEID,LAYOUTID,           //SettingDlg//~v@@@I~
                    UFDlg.FLAG_OKBTN|UFDlg.FLAG_CANCELBTN|UFDlg.FLAG_HELPBTN,//~v@@@I~
                    HELP_TITLEID,HELPFILE);                        //~v@@@R~
//      dlg.swFixedParm=PswFixed;                                  //~v@@@R~
        dlg.swFixed=PswFixed;                                      //~v@@@I~
        if (Dump.Y) Dump.println("PrefSetting.newInstance swFixed="+PswFixed);//~v@@@I~
        return dlg;                                                //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onDismissDialog()                                  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("PrefSetup.onDismissDialog");     //~v@@@I~
    	AG.aPrefSetting=null;                                      //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@M~
    @Override
    protected void initLayout(View PView)                            //~v@@@I~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("PrefSetting.initLayout");        //~v@@@R~
//      swFixed=swFixedParm;                                       //~v@@@R~
        super.initLayoutUFDlg(PView);                              //~v@@@R~
        setupLayout(PView);                                        //~v@@@I~
        setInitialValue();                                         //~v@@@I~
    }                                                              //~v@@@M~
    //******************************************                   //~v@@@I~
    protected void setupLayout(View PView)                         //~v@@@I~
    {                                                              //~v@@@I~
        LinearLayout llSpinBtn;                                    //~v@@@I~
    //*******************************                              //~v@@@I~
        if (Dump.Y) Dump.println("PrefSetting.setupLayout");       //~v@@@I~
        rgOrientation=new URadioGroup(PView,R.id.rgOrientation,0,rbIDOrientation);//~v@@@I~
        ((View)UView.findViewById(PView,R.id.rbOriPortraitReverse)).setVisibility(View.GONE);//~v@@@I~
//      ((View)UView.findViewById(PView,R.id.rbOriLandscapeReverse)).setVisibility(View.GONE);//~v@@@I~//~va9fR~
                                                                   //~v@@@I~
//      cbPortraitReverse=new UCheckBox(PView,R.id.cbPortraitReverse);//+va9fR~
        cbDelRiverTileTaken=new UCheckBox(PView,R.id.cbDelRiverTileTaken);//~v@@@I~
        cbNoRelatedRule=new UCheckBox(PView,R.id.cbNoRelatedRule); //~v@@@I~
        cbNoTakeButton=new UCheckBox(PView,R.id.cbNoTakeButton);   //~v@@@I~
        cbNoDiscardButton=new UCheckBox(PView,R.id.cbNoDiscardButton);//~v@@@I~
        cbNoAnywayButton=new UCheckBox(PView,R.id.cbNoAnywayButton);//~va18I~
        cbNoSound=new UCheckBox(PView,R.id.cbNoSound);             //~v@@@I~
        cbBeepOnly=new UCheckBox(PView,R.id.cbBeepOnly);           //~v@@@I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBSoundVolume);//~v@@@I~
    	sbVolume= USpinBtn.newInstance(llSpinBtn,DEFAULT_VOLUME_MIN,DEFAULT_VOLUME_MAX,DEFAULT_VOLUME_INC,DEFAULT_VOLUME);//~v@@@I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBBGMVolume);//~va06I~
    	sbVolumeBGM= USpinBtn.newInstance(llSpinBtn,DEFAULT_VOLUME_MIN,DEFAULT_VOLUME_MAX,DEFAULT_VOLUME_INC,DEFAULT_VOLUME);//~va06I~
        rgBGM=new URadioGroup(PView,R.id.rgBGM,0,rbIDBGM);         //~va06I~
    }                                                              //~v@@@I~
	//*****************                                                //~1613I~//~v@@@I~
    protected void setInitialValue()                                 //~v@@@I~
    {                                                              //~1613I~//~v@@@M~
        if (Dump.Y) Dump.println("PrefSetting.setInitialValue");   //~v@@@R~
        propCmt=PROP_NAME;                                         //~v@@@R~
	    curProp=AG.prefProp.getClone();                             //~v@@@R~
	    curPropOld=AG.prefProp.getClone();                         //~v@@@I~
        setupDialog();                                             //~v@@@I~
    }                                                              //~v@@@I~
	//*****************                                            //~v@@@I~
    private void setupDialog()                                    //~v@@@I~
    {                                                              //~v@@@I~
        properties2Dialog(curProp);                                //~v@@@I~
    }                                                              //~1613I~//~v@@@M~
    //******************************************                   //~v@@@I~
    @Override //UFDlg                                              //~v@@@I~
    protected int getDialogWidth()                                 //~v@@@I~
    {                                                              //~v@@@I~
    	int ww=(int)(getDialogWidthSmallDevicePortrait()*RATE_SMALLDEVICE_WIDTH);//~v@@@I~
        if (Dump.Y) Dump.println("PrefSetting.getDialogWidth ww="+ww);//~v@@@I~
        return ww;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onClickOtherUnknown(int Pbuttonid)                 //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.onClickOtherUnknown btnid="+Integer.toHexString(Pbuttonid));//~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    //*setupdialog from property                                   //~v@@@I~
    //*******************************************************      //~v@@@I~
    @Override //SettingDlg                                         //~v@@@I~
    protected void properties2Dialog(Prop Pprop)                     //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("PrefSetting.prop2Dialog swFixed="+swFixed);//~v@@@R~
//      rgOrientation.setCheckedID(Pprop.getParameter(getKeyPS(PSID_ORIENTATION),0),swFixed);//~v@@@R~
//      rgOrientation.setCheckedID(Pprop.getParameter(getKeyPS(PSID_ORIENTATION),0),swFixedParm);//~v@@@R~
        rgOrientation.setCheckedID(Pprop.getParameter(getKeyPS(PSID_ORIENTATION),0),swFixed);//~v@@@I~
                                                                   //~va9fI~
//      cbPortraitReverse.setStateInt(Pprop.getParameter(getKeyPS(PSID_ORIENTATION_PORT_REV),0),swFixed);//+va9fR~
                                                                   //~v@@@I~
        cbDelRiverTileTaken.setStateInt(Pprop.getParameter(getKeyPS(PSID_DEL_TILE_TAKEN),0/*defaultIdx*/),swFixed);//~v@@@I~
//      cbNoRelatedRule.setStateInt(Pprop.getParameter(getKeyPS(PSID_NO_RELATED_RULE),1/*defaultIdx*/),swFixed);//~v@@@R~
        cbNoRelatedRule.setStateInt(Pprop.getParameter(getKeyPS(PSID_NO_RELATED_RULE),1/*defaultIdx*/),false);//~v@@@I~
//      cbNoTakeButton.setStateInt(Pprop.getParameter(getKeyPS(PSID_NOTAKE_BUTTON),0/*defaultIdx*/),swFixed);//~v@@@R~
//      cbNoDiscardButton.setStateInt(Pprop.getParameter(getKeyPS(PSID_NODISCARD_BUTTON),0/*defaultIdx*/),swFixed);//~v@@@R~
        cbNoTakeButton.setStateInt(Pprop.getParameter(getKeyPS(PSID_NOTAKE_BUTTON),0/*defaultIdx*/),false);//~v@@@I~
        cbNoDiscardButton.setStateInt(Pprop.getParameter(getKeyPS(PSID_NODISCARD_BUTTON),0/*defaultIdx*/),false);//~v@@@I~
        cbNoAnywayButton.setStateInt(Pprop.getParameter(getKeyPS(PSID_NOANYWAY_BUTTON),1/*default*/),false);//~va18R~
//      cbNoSound.setStateInt(Pprop.getParameter(getKeyPS(PSID_NOSOUND),0/*defaultIdx*/),swFixed);//~v@@@R~
        cbNoSound.setStateInt(Pprop.getParameter(getKeyPS(PSID_NOSOUND),0/*defaultIdx*/),false);//~v@@@I~
        cbBeepOnly.setStateInt(Pprop.getParameter(getKeyPS(PSID_BEEPONLY),0/*defaultIdx*/),swFixed);//~v@@@I~
//      sbVolume.setVal(Pprop.getParameter(getKeyPS(PSID_VOLUME),DEFAULT_VOLUME),swFixed);//~v@@@R~
        sbVolume.setVal(Pprop.getParameter(getKeyPS(PSID_VOLUME),DEFAULT_VOLUME),false);//~v@@@I~
        sbVolumeBGM.setVal(Pprop.getParameter(getKeyPS(PSID_VOLUME_BGM),DEFAULT_VOLUME),false);//~va06I~
        rgBGM.setCheckedID(Pprop.getParameter(getKeyPS(PSID_BGM),PS_BGM_DEFAULT),false);//~va06R~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    @Override //SettingDlg                                         //~v@@@I~
    protected boolean dialog2Properties(String Pfnm)               //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("RuleSetting.dialog2Properties fpath="+Pfnm);//~v@@@I~
	    return dialog2Properties();                                //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    @Override //SettingDlg                                         //~v@@@I~
    protected boolean dialog2Properties()                            //~v@@@R~
    {                                                              //~v@@@R~
    	int  changed=0;                                            //~v@@@R~
        //*******************                                      //~v@@@I~
        if (Dump.Y) Dump.println("PrefSetting.dialog2Properties"); //~v@@@I~
	    changedSound=0;                                            //~v@@@I~
	    changedBGM=0;                                              //~va06I~
	    changedBtn=0;                                              //~v@@@I~
        if (Dump.Y) Dump.println("PrefSetting.dialog2Properties"); //~v@@@R~
        changed+=updateProp(getKeyPS(PSID_ORIENTATION),rgOrientation.getCheckedID());//~v@@@I~
//      changed+=updateProp(getKeyPS(PSID_ORIENTATION_PORT_REV),cbPortraitReverse.getStateInt());//+va9fR~
        changed+=updateProp(getKeyPS(PSID_DEL_TILE_TAKEN),cbDelRiverTileTaken.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyPS(PSID_NO_RELATED_RULE),cbNoRelatedRule.getStateInt());//~v@@@I~
//      changed+=updateProp(getKeyPS(PSID_NOTAKE_BUTTON),cbNoTakeButton.getStateInt());//~v@@@R~
//      changed+=updateProp(getKeyPS(PSID_NODISCARD_BUTTON),cbNoDiscardButton.getStateInt());//~v@@@R~
        changedBtn+=updateProp(getKeyPS(PSID_NOTAKE_BUTTON),cbNoTakeButton.getStateInt());//~v@@@I~
        changedBtn+=updateProp(getKeyPS(PSID_NODISCARD_BUTTON),cbNoDiscardButton.getStateInt());//~v@@@I~
        changedBtn+=updateProp(getKeyPS(PSID_NOANYWAY_BUTTON),cbNoAnywayButton.getStateInt());//~va18I~
        changed+=updateProp(getKeyPS(PSID_BEEPONLY),cbBeepOnly.getStateInt());//~v@@@I~
                                                                   //~v@@@I~
        changedSound+=updateProp(getKeyPS(PSID_NOSOUND),cbNoSound.getStateInt());//~v@@@M~
        changedSound+=updateProp(getKeyPS(PSID_VOLUME),sbVolume.getVal());//~v@@@R~
        changedBGM+=updateProp(getKeyPS(PSID_VOLUME_BGM),sbVolumeBGM.getVal());//~va06I~
        changedBGM+=updateProp(getKeyPS(PSID_BGM),rgBGM.getCheckedID());//~va06I~
        changed+=changedSound;                                     //~va06I~
        changed+=changedBGM;                                     //~v@@@I~//~va06I~
        changed+=changedBtn;                                       //~v@@@I~
                                                                   //~v@@@I~
        if (changed!=0)                                            //~v@@@I~
        {                                                          //~v@@@I~
	        swChanged=true;                                        //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("PrefSetting.dialog2Properties changed="+changed+",changedSound="+changedSound+",changedBGM="+changedBGM);//~v@@@I~//~va06R~
        return changed!=0;                                         //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~v@@@I~
    @Override //SettingDlg                                         //~v@@@I~
    protected String getFilter()                                   //~v@@@I~
    {                                                              //~v@@@I~
    	return AG.PROP_EXT_PREFERENCE;                              //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~1A4zI~//~v@@@M~
    @Override
    public Prop checkValidity(String Pfname)                        //~1A4zI~//~v@@@R~
    {                                                              //~1A4zI~//~v@@@M~
        if (Dump.Y) Dump.println("PrefSettingg.chkValidity fnm="+Pfname);//~v@@@I~
    	Prop prop=new Prop();                                      //~v@@@M~
        if (!prop.loadProperties(Pfname)                           //~v@@@M~
        ||  prop.getParameter(AG.PROP_VALIDATINGKEY_PREFERENCE,0)!=1)//~v@@@R~
        {                                                          //~v@@@M~
        	return null;                                           //~v@@@R~
        }                                                          //~v@@@M~
        return prop;                                               //~v@@@R~
    }                                                              //~1A4zI~//~v@@@M~
    //*******************************************************      //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onClickOK()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("PrefSetting.onClickOK");         //~v@@@R~
	    getValue();                                                //~v@@@I~
        if (swChanged)                                             //~v@@@I~
        {                                                          //~v@@@I~
			saveProperties();	//save to current.rule             //~v@@@R~
	        AG.prefProp.resetProperties(curProp);                  //~v@@@I~
        }                                                          //~v@@@I~
//        if (swFixedParm)    //from MenuInGameDlg                 //~v@@@R~
//            chkChanged();                                        //~v@@@R~
        if (swFixed)    //from MenuInGameDlg                   //~v@@@I~
	        if (changedBtn!=0)    //from MenuInGameDlg             //~v@@@I~
                AG.aGC.prefSettingChanged();                       //~v@@@I~
        if (changedSound!=0 && AG.aSound!=null)                   //~v@@@I~
        {                                                          //~v@@@I~
        	AG.aSound.resetOption();                               //~v@@@I~
        }                                                          //~v@@@I~
        if (changedBGM!=0 && AG.aSound!=null)                      //~va06I~
        {                                                          //~va06I~
        	AG.aSound.resetOptionBGM();                            //~va06I~
	        if (Dump.Y) Dump.println("PrefSetting.onClickOK changedBGM status="+Status.getGameStatus()+",gameseq="+Status.getGameSeq().toString());//~va06I~
        	if (Status.getGameStatus()==GS_GAME_STARTED)           //~va06I~
            {                                                      //~va06I~
    			Rect r=Status.getGameSeq();                        //~va06I~
            	GC.playSound(r.top);	//gameCtrGame              //~va06I~
            }                                                      //~va06I~
            else                                                   //~va06I~
	        	Sound.playBGM(SOUNDID_BGM_TOP);                    //~va06I~
        }                                                          //~va06I~
        dismiss();                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //*******************************************************    //~v@@@R~
//    private void chkChanged()                                    //~v@@@R~
//    {                                                            //~v@@@R~
//        //*******************                                    //~v@@@R~
//        if (Dump.Y) Dump.println("PrefSetting.chkChanged");      //~v@@@R~
////      changed+=updateProp(getKeyPS(PSID_ORIENTATION),rgOrientation.getCheckedID());//~v@@@R~
////      changed+=updateProp(getKeyPS(PSID_DEL_TILE_TAKEN),cbDelRiverTileTaken.getStateInt());//~v@@@R~
////      changed+=updateProp(getKeyPS(PSID_NO_RELATED_RULE),cbNoRelatedRule.getStateInt());//~v@@@R~
////      changed+=updateProp(getKeyPS(PSID_NODISCARD_BUTTON),cbNoDiscardButton.getStateInt());//~v@@@R~
//        if (chkChanged(PSID_NOTAKE_BUTTON)||chkChanged(PSID_NODISCARD_BUTTON))//~v@@@R~
//            if (AG.aGC!=null)                                    //~v@@@R~
//                AG.aGC.prefSettingChanged();                     //~v@@@R~
//    }                                                            //~v@@@R~
    //*******************************************************      //~v@@@I~
    protected boolean chkChanged(int PkeyID)                           //~v@@@I~
    {                                                              //~v@@@I~
        String key=getKeyPS(PkeyID);                               //~v@@@I~
    	int oldval=curPropOld.getParameter(key,-1);               //~v@@@I~
    	int newval=curProp.getParameter(key,-1);                  //~v@@@I~
        boolean rc=(oldval!=newval);                               //~v@@@I~
    	if (Dump.Y) Dump.println("PrefSetting.chkChanged key="+key+",oldsval="+oldval+",newval="+newval+",rc="+rc);//~v@@@I~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************************//~v@@@I~
    //*******************************************************************************//~v@@@I~
    //*******************************************************************************//~v@@@I~
    public static int getOrientation()                         //~v@@@R~
    {                                                              //~v@@@I~
        int rc=AG.prefProp.getParameter(getKeyPS(PSID_ORIENTATION),0);//~v@@@I~
    	if (Dump.Y) Dump.println("PrefSetting.getOrientation rc="+rc);//~v@@@R~
        return rc;                                                 //~v@@@R~
    }                                                              //~v@@@I~
//    //**************************************                     //+va9fR~
//    public static int getOrientationPortReverse()                //+va9fR~
//    {                                                            //+va9fR~
//        int rc=AG.prefProp.getParameter(getKeyPS(PSID_ORIENTATION_PORT_REV),0);//+va9fR~
//        if (Dump.Y) Dump.println("PrefSetting.getOrientationPortReverse rc="+rc);//+va9fR~
//        return rc;                                               //+va9fR~
//    }                                                            //+va9fR~
    //**************************************                       //~v@@@I~
    public static boolean isDeleteRiverTileTaken()                 //~v@@@I~
    {                                                              //~v@@@I~
		int def=0;	//false                                        //~v@@@I~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_DEL_TILE_TAKEN),def)!=0;//~v@@@I~
    	if (Dump.Y) Dump.println("PrefSetting.isDeleteRiverTileTaken:"+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    public static boolean isNoRelatedRule()                        //~v@@@I~
    {                                                              //~v@@@I~
		int def=1;	//true                                         //~v@@@R~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_NO_RELATED_RULE),def)!=0;//~v@@@I~
    	if (Dump.Y) Dump.println("PrefSetting.isNoRelatedRule:"+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    public static boolean isNoTakeButton()                         //~v@@@I~
    {                                                              //~v@@@I~
		int def=0;	//false                                        //~v@@@I~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_NOTAKE_BUTTON),def)!=0;//~v@@@I~
    	if (Dump.Y) Dump.println("PrefSetting.isNoTakeButton:"+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    public static boolean isNoDiscardButton()                      //~v@@@I~
    {                                                              //~v@@@I~
		int def=0;	//false                                        //~v@@@I~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_NODISCARD_BUTTON),def)!=0;//~v@@@I~
    	if (Dump.Y) Dump.println("PrefSetting.isNoDiscardButton:"+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~va18I~
    public static boolean isNoAnywayButton()                       //~va18I~
    {                                                              //~va18I~
		int def=1;	//false                                        //~va18R~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_NOANYWAY_BUTTON),def)!=0;//~va18I~
    	if (Dump.Y) Dump.println("PrefSetting.isNoAnywayButton:"+rc);//~va18I~
        return rc;                                                 //~va18I~
    }                                                              //~va18I~
    //**************************************                       //~v@@@I~
    public static boolean isNoSound()                              //~v@@@I~
    {                                                              //~v@@@I~
		int def=0;	//false                                        //~v@@@I~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_NOSOUND),def)!=0;//~v@@@I~
    	if (Dump.Y) Dump.println("PrefSetting.isNoSound rc="+rc);  //~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    public static boolean isBeepOnly()                             //~v@@@I~
    {                                                              //~v@@@I~
		int def=0;	//false                                        //~v@@@I~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_BEEPONLY),def)!=0;//~v@@@I~
    	if (Dump.Y) Dump.println("PrefSetting.isBeepOnly rc="+rc); //~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~v@@@I~
    public static float getSoundVolume()                           //~v@@@I~
    {                                                              //~v@@@I~
		int level=AG.prefProp.getParameter(getKeyPS(PSID_VOLUME),DEFAULT_VOLUME);//~v@@@I~
        float rc=(float)(level*0.1);                                        //~v@@@I~
    	if (Dump.Y) Dump.println("RuleSetting.getSoundVolume:"+rc);//~v@@@R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~va06I~
    public static float getBGMVolume()                             //~va06I~
    {                                                              //~va06I~
		int level=AG.prefProp.getParameter(getKeyPS(PSID_VOLUME_BGM),DEFAULT_VOLUME);//~va06I~
        float rc=(float)(level*0.1);                               //~va06I~
    	if (Dump.Y) Dump.println("RuleSetting.getBGMVolume:"+rc);  //~va06I~
        return rc;                                                 //~va06I~
    }                                                              //~va06I~
    //*******************************************************************************//~va06I~
    public static boolean isNoBGM()                                //~va06R~
    {                                                              //~va06I~
        boolean rc=getBGMType()==PS_BGM_NO;                        //~va06R~
    	if (Dump.Y) Dump.println("PrefSetting.getNoBGM rc="+rc);   //~va06R~
        return rc;                                                 //~va06I~
    }                                                              //~va06I~
    //*******************************************************************************//~va06I~
    public static int getBGMType()                                 //~va06I~
    {                                                              //~va06I~
        int rc=AG.prefProp.getParameter(getKeyPS(PSID_BGM),PS_BGM_DEFAULT);//~va06R~
    	if (Dump.Y) Dump.println("PrefSetting.getBGMType rc="+rc); //~va06I~
        return rc;                                                 //~va06I~
    }                                                              //~va06I~
}//class                                                           //~v@@@R~
