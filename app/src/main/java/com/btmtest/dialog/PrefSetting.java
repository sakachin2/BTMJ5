//*CID://+vamuR~:                             update#=  545;       //~vamuR~
//*****************************************************************//~v101I~
//2022/04/22 vamu move playalone option to preference from operation settings//~vamuI~
//2021/12/21 vai0 (Bug)Id of NoBGM was shown by Japanese on english env.//~vai0I~
//2021/09/19 vae9 1ak2(access external audio file) for BTMJ        //~vae9I~
//2021/09/19 vae8 keep sharedPreference to external storage with PrefSetting item.//~vae8I~
//1ak2 2021/09/04 access external audio file                       //~1ak2I~
//2021/08/24 vad1 game buttons layout for lefty                    //~vad1I~
//2021/08/15 vac6 by vac6, change URadioGroup to UButtonRG         //~vac6I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2021/08/11 vac3 add BGM kouka                                    //~vac3I~
//2021/06/17 va9f correct reason of reverse orientation did not work(fix orientation was called)//~va9fI~
//                not work because onConfigurationChanged is not fired by RVERSE request//~va9fI~
//2020/10/18 va18 option to diaplay WinAnyway button               //~va18I~
//2020/04/27 va06:BGM                                              //~va06I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                        //~v@@@R~
import android.graphics.Rect;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;                                      //~vae9I~

import com.btmtest.R;
import com.btmtest.game.GC;
import com.btmtest.game.Status;
import com.btmtest.gui.UButton;
import com.btmtest.gui.URadioGroup;
import com.btmtest.gui.USpinBtn;
import com.btmtest.utils.Prop;                                     //~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.gui.UCheckBox;
import com.btmtest.gui.UButtonRG;
import com.btmtest.utils.UFile;
import com.btmtest.utils.UMediaStore;
import com.btmtest.utils.UScoped;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import com.btmtest.utils.sound.Sound;


import static com.btmtest.AG.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.dialog.PrefSettingEnum.*;                  //~v@@@R~
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Status.*;

//~v@@@I~
public class PrefSetting extends SettingDlg                        //~v@@@R~
    	implements UMediaStore.UMediaStoreI                        //~vae9R~
{                                                                  //~2C29R~
	public  static final String PROP_NAME="PreferenceSetting";     //~v@@@I~
	private static final int    TITLEID=R.string.Title_PrefSetting;//~v@@@R~
	private static final int    LAYOUTID=R.layout.setting_preference;    //~v@@@R~
	private static final int    LAYOUTID_SMALLFONT=R.layout.setting_preference_theme;//~vac5I~
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
//  private URadioGroup rgBGM;                                     //~va06I~//~vac6R~
    private UButtonRG   bgBGM;                                     //~vac6I~
//  private static final int[] rbIDBGM=new int[]{R.id.rbBGMNo,R.id.rbBGMSame,R.id.rbBGM4Seasons,R.id.rbBGM4SeasonsFast};//~va06R~//~vac3R~
//  private static final int[] rbIDBGM=new int[]{R.id.rbBGMNo,R.id.rbBGMSame,R.id.rbBGM4Seasons,R.id.rbBGM4SeasonsFast,R.id.rbBGMSeriesK};//~vac3I~//~vae9R~
    private static final int[] rbIDBGM=new int[]{R.id.rbBGMNo,R.id.rbBGMSame,R.id.rbBGM4Seasons,R.id.rbBGM4SeasonsFast,R.id.rbBGMSeriesK,R.id.rbUserBGM};//~vae9I~
	public  static final int    PS_BGM_NO=0;                       //~va06I~
	public  static final int    PS_BGM_COMMON=1;                   //~va06R~
	public  static final int    PS_BGM_4SEASONS=2;                 //~va06R~
	public  static final int    PS_BGM_4SEASONS_FAST=3;            //~va06I~
	public  static final int    PS_BGM_SERIESK=4;                  //~vac3I~
	public  static final int    PS_BGM_USER=5;                     //~vae9I~
	public  static final int    PS_BGM_DEFAULT=PS_BGM_COMMON;      //~va06I~
//  private static final int[] IDS_BGM=new int[]{PS_BGM_NO,PS_BGM_COMMON,PS_BGM_4SEASONS,PS_BGM_4SEASONS_FAST,PS_BGM_SERIESK};//~9417I~//~vac6I~//~vae9R~
    private static final int[] IDS_BGM=new int[]{PS_BGM_NO,PS_BGM_COMMON,PS_BGM_4SEASONS,PS_BGM_4SEASONS_FAST,PS_BGM_SERIESK,PS_BGM_USER};//~vae9I~
    private static final int[] IDS_LLUSERBGM=new int[/*MAX_USERBGM*/]{R.id.llUserBGM0,R.id.llUserBGM1,R.id.llUserBGM2,R.id.llUserBGM3,R.id.llUserBGM4,//~vae9R~
                                                                  R.id.llUserBGM5,R.id.llUserBGM6,R.id.llUserBGM7,R.id.llUserBGM8,R.id.llUserBGM9};//~vae9R~
    private static final int[] PSIDS_USERBGM=new int[/*MAX_USERBGM*/]{PSID_USERBGM0,PSID_USERBGM1,PSID_USERBGM2,PSID_USERBGM3,PSID_USERBGM4,//~vae9R~
                                                                  PSID_USERBGM5,PSID_USERBGM6,PSID_USERBGM7,PSID_USERBGM8,PSID_USERBGM9};//~vae9R~
    private static final int[] PSIDS_USERBGM_URI=new int[/*MAX_USERBGM*/]{PSID_USERBGM_URI0,PSID_USERBGM_URI1,PSID_USERBGM_URI2,PSID_USERBGM_URI3,PSID_USERBGM_URI4,//~vae9R~
                                                                  PSID_USERBGM_URI5,PSID_USERBGM_URI6,PSID_USERBGM_URI7,PSID_USERBGM_URI8,PSID_USERBGM_URI9};//~vae9I~
    private static final int[] PSIDS_USERBGM_TITLE=new int[/*MAX_USERBGM*/]{PSID_USERBGM_TITLE0,PSID_USERBGM_TITLE1,PSID_USERBGM_TITLE2,PSID_USERBGM_TITLE3,PSID_USERBGM_TITLE4,//~vae9R~
                                                                  PSID_USERBGM_TITLE5,PSID_USERBGM_TITLE6,PSID_USERBGM_TITLE7,PSID_USERBGM_TITLE8,PSID_USERBGM_TITLE9};//~vae9I~
//  private static final String NOTITLE=Utils.getStr(R.string.NoUserBGM);//~vae9R~//~vai0R~
    private              String NOTITLE;                           //~vai0I~
    private static final String NOTITLE_ENG=Utils.getStr(R.string.NoUserBGMENG);//~vai0I~
    private static final String NOTITLE_JPN=Utils.getStr(R.string.NoUserBGMJPN);//~vai0I~
                                                                   //~va06I~
    public static final int 	DEFAULT_VOLUME_MIN=0;              //~v@@@I~
    public static final int 	DEFAULT_VOLUME_MAX=10;             //~v@@@I~
    public static final int 	DEFAULT_VOLUME_INC=1;              //~v@@@I~
    public static final int 	DEFAULT_VOLUME=5;                  //~v@@@I~
                                                                   //~v@@@I~
    private UCheckBox cbDelRiverTileTaken;                         //~v@@@I~
    private UCheckBox cbNoRelatedRule;                             //~v@@@I~
    private UCheckBox cbNoTakeButton,cbNoDiscardButton;            //~v@@@I~
    private UCheckBox cbNoAnywayButton;                            //~va18I~
    private UCheckBox cbNoSound,cbBeepOnly;                        //~v@@@R~
    private UCheckBox cbLeftyPortrait,cbLeftyLandscape;            //~vad1I~
//  private UCheckBox cbPortraitReverse;                           //~va9fR~
//  private boolean swFixedParm;                                   //~v@@@R~
    private Prop curPropOld;                                 //~v@@@I~
    private USpinBtn sbVolume;                                     //~v@@@I~
    private USpinBtn sbVolumeBGM;                                  //~va06I~
    private int changedSound;                                      //~v@@@I~
    private int changedBGM;                                        //~va06I~
    private int changedBtn;                                        //~v@@@I~
    private UCheckBox cbUserBGMNoRound;                            //~vae9I~
    private UCheckBox cbUseUPicker;                                //~vae9I~
    private EditText etUserBGMSelection;                           //~vae9I~
    private Button[]  btnsUserBGM=new Button[MAX_USERBGM];         //~vae9R~
    private Button[]  btnsUserBGMDelete=new Button[MAX_USERBGM];   //~vae9I~
    private int userBGMButtonNumber;                               //~vae9I~
    private UMediaStore UMS;                                       //~vae9I~
    private String[] strsUserBGMUri=new String[MAX_USERBGM]; //temp work at dismiss//~vae9I~
    private String[] strsUserBGMTitle=new String[MAX_USERBGM];     //~vae9I~
    private TextView[] tvsUserBGMTitle=new TextView[MAX_USERBGM];  //~vae9M~
    private boolean swError;                                       //~vae9I~
    private UCheckBox cbAllowRobotAllButton;                       //~vamuI~
    private UCheckBox cbPlayAloneNotify;                           //~vamuI~
    //******************************************                   //~v@@@I~
    //******************************************                   //~v@@@M~
	public PrefSetting()                                           //~v@@@R~
    {
        super();
        AG.aPrefSetting=this;                                      //~v@@@I~
        UMS=AG.aUMediaStore;                                       //~vae9I~
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
//      UFDlg.setBundle(dlg,TITLEID,LAYOUTID,           //SettingDlg//~v@@@I~//~vac5R~
        UFDlg.setBundle(dlg,TITLEID,(AG.swSmallFont ? LAYOUTID_SMALLFONT : LAYOUTID),           //SettingDlg//~vac5I~
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
        NOTITLE=Utils.getStr(R.string.NoUserBGM);                  //~vai0I~
        if (Dump.Y) Dump.println("PrefSetting.initLayout NOTITLE="+NOTITLE);        //~v@@@R~//~vai0R~
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
//      cbPortraitReverse=new UCheckBox(PView,R.id.cbPortraitReverse);//~va9fR~
        cbDelRiverTileTaken=new UCheckBox(PView,R.id.cbDelRiverTileTaken);//~v@@@I~
        cbNoRelatedRule=new UCheckBox(PView,R.id.cbNoRelatedRule); //~v@@@I~
        cbNoTakeButton=new UCheckBox(PView,R.id.cbNoTakeButton);   //~v@@@I~
        cbNoDiscardButton=new UCheckBox(PView,R.id.cbNoDiscardButton);//~v@@@I~
        cbNoAnywayButton=new UCheckBox(PView,R.id.cbNoAnywayButton);//~va18I~
        cbLeftyPortrait=new UCheckBox(PView,R.id.cbLeftyPortrait);  //~vad1I~
        cbLeftyLandscape=new UCheckBox(PView,R.id.cbLeftyLandscape);//~vad1I~
        cbNoSound=new UCheckBox(PView,R.id.cbNoSound);             //~v@@@I~
        cbBeepOnly=new UCheckBox(PView,R.id.cbBeepOnly);           //~v@@@I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBSoundVolume);//~v@@@I~
    	sbVolume= USpinBtn.newInstance(llSpinBtn,DEFAULT_VOLUME_MIN,DEFAULT_VOLUME_MAX,DEFAULT_VOLUME_INC,DEFAULT_VOLUME);//~v@@@I~
    	llSpinBtn=(LinearLayout)       UView.findViewById(PView,R.id.llSBBGMVolume);//~va06I~
    	sbVolumeBGM= USpinBtn.newInstance(llSpinBtn,DEFAULT_VOLUME_MIN,DEFAULT_VOLUME_MAX,DEFAULT_VOLUME_INC,DEFAULT_VOLUME);//~va06I~
//      rgBGM=new URadioGroup(PView,R.id.rgBGM,0,rbIDBGM);         //~va06I~//~vac6R~
        bgBGM=new UButtonRG((ViewGroup)PView,rbIDBGM.length);      //~vac6I~
	    for (int ii=0;ii<rbIDBGM.length;ii++)                      //~vac6I~
			bgBGM.add(IDS_BGM[ii],rbIDBGM[ii]);                    //~vac6I~
        bgBGM.setDefaultChk(PS_BGM_DEFAULT);                       //~vac6I~
        cbUserBGMNoRound=new UCheckBox(PView,R.id.cbUserBGMNoRound);//~vae9I~
        cbUseUPicker=new UCheckBox(PView,R.id.cbUseUPicker);       //~vae9I~
        etUserBGMSelection=(EditText)UView.findViewById(PView,R.id.etUserBGMSelection);//~vae9R~
        for (int ii=0;ii<MAX_USERBGM;ii++)                         //~vae9R~
        {                                                          //~vae9I~
	    	LinearLayout ll=(LinearLayout)UView.findViewById(PView,IDS_LLUSERBGM[ii]);//~vae9R~
	    	tvsUserBGMTitle[ii]=(TextView)UView.findViewById(ll,R.id.tvUserBGM);//~vae9R~
	    	btnsUserBGM[ii]=UButton.bind(ll,R.id.btnUserBGM,this); //~vae9I~
	    	btnsUserBGMDelete[ii]=UButton.bind(ll,R.id.btnUserBGMDelete,this);//~vae9I~
//      	tvsUserBGMTitle[ii].setText(strsUserBGMTitle[ii]);         //~vae9I~//~vai0R~
        	tvsUserBGMTitle[ii].setText(getDisplayNameUserBGM(strsUserBGMTitle[ii]));//~vai0I~
        }                                                          //~vae9I~
        cbAllowRobotAllButton=new UCheckBox(PView,R.id.cbAllowRobotAllButton);//~vamuI~
        cbPlayAloneNotify=new UCheckBox(PView,R.id.cbPlayAloneNotify);//~vamuI~
    }                                                              //~v@@@I~
	//*********************************************************    //~vai0I~
    private String getDisplayNameUserBGM(String Ptitle)            //~vai0I~
    {                                                              //~vai0I~
    	String displayName=(Ptitle==null || Ptitle.equals("")) ? NOTITLE : Ptitle;//~vai0R~
        if (Dump.Y) Dump.println("PrefSetting.getDisplayNameUserBGM Ptitle="+Ptitle+",displayName="+displayName);//~vai0I~
        return displayName;                                        //~vai0I~
    }                                                              //~vai0I~
	//*********************************************************    //~vae9R~
	//*from UMediaStore                                            //~vae9I~
	//*********************************************************    //~vae9I~
    public static void setupUserBGM(Prop Pprop,String[] PstrsUri,String[] PstrsTitle)//~vae9R~
    {                                                              //~vae9I~
        if (Dump.Y) Dump.println("PrefSetting.setupUserBGM");      //~vae9I~
	    Prop cp=AG.prefProp;                                        //~vae9I~
        for (int ii=0;ii<MAX_USERBGM;ii++)                         //~vae9I~
        {                                                          //~vae9I~
    		PstrsUri[ii]=Pprop.getParameter(getKeyPS(PSIDS_USERBGM_URI[ii]),"");//~vae9R~
//  		PstrsTitle[ii]=Pprop.getParameter(getKeyPS(PSIDS_USERBGM_TITLE[ii]),NOTITLE);//~vae9R~//~vai0R~
//          if (PstrsTitle[ii].equals(NOTITLE))                    //~vae9I~//~vai0R~
//  			PstrsUri[ii]="";                                   //~vae9I~//~vai0R~
    		PstrsTitle[ii]=Pprop.getParameter(getKeyPS(PSIDS_USERBGM_TITLE[ii]),"");//~vai0I~
            if (PstrsTitle[ii].equals(NOTITLE_ENG) || PstrsTitle[ii].equals(NOTITLE_JPN))//~vai0I~
    			PstrsTitle[ii]="";                                 //~vai0I~
        }                                                          //~vae9I~
        if (Dump.Y) Dump.println("PrefSetting.setupUserBGM strUri="+Utils.toString(PstrsUri)+",title="+Utils.toString(PstrsTitle));//~vae9I~
    }                                                              //~vae9I~
	//*****************                                                //~1613I~//~v@@@I~
    protected void setInitialValue()                                 //~v@@@I~
    {                                                              //~1613I~//~v@@@M~
        if (Dump.Y) Dump.println("PrefSetting.setInitialValue");   //~v@@@R~
        propCmt=PROP_NAME;                                         //~v@@@R~
	    curProp=AG.prefProp.getClone();                             //~v@@@R~
	    setupUserBGM(curProp,strsUserBGMUri,strsUserBGMTitle);      //~vae9I~
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
        switch(Pbuttonid)                                          //~vae9I~
        {                                                          //~vae9I~
        case R.id.btnUserBGM:                                      //~vae9I~
            userBGMButtonNumber=getBGMButtonNumber();              //~vae9I~
        	boolean swUPicker=cbUseUPicker.getStateInt()!=0;       //~vae9R~
		    UMediaStore.changeBGM(this,swUPicker);	//callback BGMSelected                           //~1Ak2R~//~vae9R~
            break;                                                 //~vae9I~
        case R.id.btnUserBGMDelete:                                //~vae9I~
            userBGMButtonNumber=getBGMButtonNumber();              //~vae9I~
	    	deleteBGM();                                           //~vae9I~
            break;                                                 //~vae9I~
        }                                                          //~vae9I~
    }                                                              //~v@@@I~
    //**************************************                       //~vae9I~
    private int getBGMButtonNumber()                               //~vae9I~
    {                                                              //~vae9I~
        Button btn=btnClicked;  //UFDlg protected                  //~vae9I~
        int llid=((View)(btn.getParent())).getId();                //~vae9R~
        int num=-1;                                                //~vae9I~
        for (int ii=0;ii<MAX_USERBGM;ii++)                         //~vae9R~
        {                                                          //~vae9I~
	    	if (llid==IDS_LLUSERBGM[ii])                           //~vae9I~
            {                                                      //~vae9I~
            	num=ii;                                            //~vae9I~
            	break;                                             //~vae9I~
            }                                                      //~vae9I~
        }                                                          //~vae9I~
    	if (Dump.Y) Dump.println("RuleSetting.getButtonNumber llid="+Integer.toHexString(llid)+",btnNumber="+num);//~vae9I~
        return num;                                                //~vae9I~
    }                                                              //~vae9I~
    //**************************************                       //~vae9I~
    @Override //UMediastoreI                                       //~vae9I~
    public void BGMSelected(Uri Puri, UMediaStore.AudioFile PaudioFile)         //~vae9I~
    {                                                              //~vae9I~
        int num=userBGMButtonNumber;                               //~vae9R~
    	if (Dump.Y) Dump.println("RuleSetting.BGMSelected btnNo="+num+",AudioFile="+PaudioFile.toString()+",uri="+Puri);//~vae9I~
        if (num>=0 && num<MAX_USERBGM)                             //~vae9R~
        {                                                          //~vae9I~
        	if (PaudioFile!=null)                                  //~vae9I~
            {                                                      //~vae9I~
        		String title=PaudioFile.title;                     //~vae9R~
        		String artist=PaudioFile.artist;                   //~vae9I~
            	if (artist!=null && !artist.equals(""))            //~vae9R~
            		title=title+" ("+artist+")";                   //~vae9R~
	        	strsUserBGMTitle[num]=title;                       //~vae9I~
	    		tvsUserBGMTitle[num].setText(title);               //~vae9I~
	        	strsUserBGMUri[num]=Puri.toString();               //~vae9I~
            }                                                      //~vae9I~
        }                                                          //~vae9I~
    }                                                              //~vae9I~
    //**************************************                       //~vae9I~
    public void deleteBGM()                                        //~vae9I~
    {                                                              //~vae9I~
        int num=userBGMButtonNumber;                               //~vae9I~
    	if (Dump.Y) Dump.println("RuleSetting.deleteBGM btnNo="+num+",title="+Utils.toString(strsUserBGMTitle)+",Puri="+Utils.toString(strsUserBGMUri));//~vae9I~
//      strsUserBGMTitle[num]=NOTITLE;                             //~vae9I~//~vai0R~
        strsUserBGMTitle[num]="";                                  //~vai0I~
	    tvsUserBGMTitle[num].setText(NOTITLE);                     //~vae9I~
	    strsUserBGMUri[num]="";                                    //~vae9I~
    }                                                              //~vae9I~
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
//      cbPortraitReverse.setStateInt(Pprop.getParameter(getKeyPS(PSID_ORIENTATION_PORT_REV),0),swFixed);//~va9fR~
                                                                   //~v@@@I~
        cbDelRiverTileTaken.setStateInt(Pprop.getParameter(getKeyPS(PSID_DEL_TILE_TAKEN),0/*defaultIdx*/),swFixed);//~v@@@I~
//      cbNoRelatedRule.setStateInt(Pprop.getParameter(getKeyPS(PSID_NO_RELATED_RULE),1/*defaultIdx*/),swFixed);//~v@@@R~
        cbNoRelatedRule.setStateInt(Pprop.getParameter(getKeyPS(PSID_NO_RELATED_RULE),1/*defaultIdx*/),false);//~v@@@I~
//      cbNoTakeButton.setStateInt(Pprop.getParameter(getKeyPS(PSID_NOTAKE_BUTTON),0/*defaultIdx*/),swFixed);//~v@@@R~
//      cbNoDiscardButton.setStateInt(Pprop.getParameter(getKeyPS(PSID_NODISCARD_BUTTON),0/*defaultIdx*/),swFixed);//~v@@@R~
        cbNoTakeButton.setStateInt(Pprop.getParameter(getKeyPS(PSID_NOTAKE_BUTTON),0/*defaultIdx*/),false);//~v@@@I~
        cbNoDiscardButton.setStateInt(Pprop.getParameter(getKeyPS(PSID_NODISCARD_BUTTON),0/*defaultIdx*/),false);//~v@@@I~
        cbNoAnywayButton.setStateInt(Pprop.getParameter(getKeyPS(PSID_NOANYWAY_BUTTON),1/*default*/),false);//~va18R~
        cbLeftyPortrait.setStateInt(Pprop.getParameter(getKeyPS(PSID_LEFTY_PORTRAIT),0/*default*/),swFixed);//~vad1R~
        cbLeftyLandscape.setStateInt(Pprop.getParameter(getKeyPS(PSID_LEFTY_LANDSCAPE),0/*default*/),swFixed);//~vad1R~
//      cbNoSound.setStateInt(Pprop.getParameter(getKeyPS(PSID_NOSOUND),0/*defaultIdx*/),swFixed);//~v@@@R~
        cbNoSound.setStateInt(Pprop.getParameter(getKeyPS(PSID_NOSOUND),0/*defaultIdx*/),false);//~v@@@I~
        cbBeepOnly.setStateInt(Pprop.getParameter(getKeyPS(PSID_BEEPONLY),0/*defaultIdx*/),swFixed);//~v@@@I~
//      sbVolume.setVal(Pprop.getParameter(getKeyPS(PSID_VOLUME),DEFAULT_VOLUME),swFixed);//~v@@@R~
        sbVolume.setVal(Pprop.getParameter(getKeyPS(PSID_VOLUME),DEFAULT_VOLUME),false);//~v@@@I~
        sbVolumeBGM.setVal(Pprop.getParameter(getKeyPS(PSID_VOLUME_BGM),DEFAULT_VOLUME),false);//~va06I~
//      rgBGM.setCheckedID(Pprop.getParameter(getKeyPS(PSID_BGM),PS_BGM_DEFAULT),false);//~va06R~//~vac6R~
        bgBGM.setChecked(Pprop.getParameter(getKeyPS(PSID_BGM),PS_BGM_DEFAULT),false);//~vac6I~
        cbUserBGMNoRound.setStateInt(Pprop.getParameter(getKeyPS(PSID_USERBGM_ROUND),0),false);//~vae9R~
        cbUseUPicker.setStateInt(Pprop.getParameter(getKeyPS(PSID_USERBGM_UPICKER),0),false);//~vae9I~
        etUserBGMSelection.setText(Pprop.getParameter(getKeyPS(PSID_USERBGM_SELECTION),""));//~vae9R~
      	if (swFixed)                                               //~vae9I~
        	etUserBGMSelection.setEnabled(false);                  //~vae9I~
        for (int ii=0;ii<MAX_USERBGM;ii++)                         //~vae9R~
        {                                                          //~vae9I~
//      	tvsUserBGMTitle[ii].setText(strsUserBGMTitle[ii]); //~vae9R~//~vai0R~
        	tvsUserBGMTitle[ii].setText(getDisplayNameUserBGM(strsUserBGMTitle[ii]));//~vai0I~
        }                                                          //~vae9I~
        cbAllowRobotAllButton.setStateInt(Pprop.getParameter(getKeyPS(PSID_ALLOW_ROBOT_ALL_BTN),0/*default:false*/),swFixed);//~vamuI~
        cbPlayAloneNotify.setStateInt(Pprop.getParameter(getKeyPS(PSID_PLAY_ALONE_NOTIFY),DEFAULT_PLAY_ALONE_NOTIFY/*default:true*/),swFixed);//~vamuI~
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
//      changed+=updateProp(getKeyPS(PSID_ORIENTATION_PORT_REV),cbPortraitReverse.getStateInt());//~va9fR~
        changed+=updateProp(getKeyPS(PSID_DEL_TILE_TAKEN),cbDelRiverTileTaken.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyPS(PSID_NO_RELATED_RULE),cbNoRelatedRule.getStateInt());//~v@@@I~
//      changed+=updateProp(getKeyPS(PSID_NOTAKE_BUTTON),cbNoTakeButton.getStateInt());//~v@@@R~
//      changed+=updateProp(getKeyPS(PSID_NODISCARD_BUTTON),cbNoDiscardButton.getStateInt());//~v@@@R~
        changedBtn+=updateProp(getKeyPS(PSID_NOTAKE_BUTTON),cbNoTakeButton.getStateInt());//~v@@@I~
        changedBtn+=updateProp(getKeyPS(PSID_NODISCARD_BUTTON),cbNoDiscardButton.getStateInt());//~v@@@I~
        changedBtn+=updateProp(getKeyPS(PSID_NOANYWAY_BUTTON),cbNoAnywayButton.getStateInt());//~va18I~
        changedBtn+=updateProp(getKeyPS(PSID_LEFTY_PORTRAIT),cbLeftyPortrait.getStateInt());//~vad1I~
        changedBtn+=updateProp(getKeyPS(PSID_LEFTY_LANDSCAPE),cbLeftyLandscape.getStateInt());//~vad1I~
        changed+=updateProp(getKeyPS(PSID_BEEPONLY),cbBeepOnly.getStateInt());//~v@@@I~
        changed+=updateProp(getKeyPS(PSID_ALLOW_ROBOT_ALL_BTN),cbAllowRobotAllButton.getStateInt());//~vamuI~
        changed+=updateProp(getKeyPS(PSID_PLAY_ALONE_NOTIFY),cbPlayAloneNotify.getStateInt());//~vamuI~
                                                                   //~v@@@I~
        changedSound+=updateProp(getKeyPS(PSID_NOSOUND),cbNoSound.getStateInt());//~v@@@M~
        changedSound+=updateProp(getKeyPS(PSID_VOLUME),sbVolume.getVal());//~v@@@R~
        changedBGM+=updateProp(getKeyPS(PSID_VOLUME_BGM),sbVolumeBGM.getVal());//~va06I~
//      changedBGM+=updateProp(getKeyPS(PSID_BGM),rgBGM.getCheckedID());//~va06I~//~vac6R~
        changedBGM+=updateProp(getKeyPS(PSID_BGM),bgBGM.getChecked());//~vac6I~
        changedBGM+=updateProp(getKeyPS(PSID_USERBGM_ROUND),cbUserBGMNoRound.getStateInt());//~vae9R~
        changed+=updateProp(getKeyPS(PSID_USERBGM_UPICKER),cbUseUPicker.getStateInt());//~vae9R~
        String playSeq=etUserBGMSelection.getText().toString();     //~vae9R~
        changedBGM+=updateProp(getKeyPS(PSID_USERBGM_SELECTION),playSeq);//~vae9I~
        for (int ii=0;ii<MAX_USERBGM;ii++)                         //~vae9R~
        {                                                          //~vae9I~
	    	String title=strsUserBGMTitle[ii];                     //~vae9R~
	        changedBGM+=updateProp(getKeyPS(PSIDS_USERBGM_TITLE[ii]),title); //AG.prefProp.P will be replayed by curProp at OnClickOK//~vae9R~
	    	String strUri=strsUserBGMUri[ii];                      //~vae9R~
	        changedBGM+=updateProp(getKeyPS(PSIDS_USERBGM_URI[ii]),strUri);//~vae9I~
	        if (Dump.Y) Dump.println("PrefSetting.dialog2Properties userBGM title="+title+",strUri="+strUri);//~vae9I~
        }                                                          //~vae9I~
        changed+=changedSound;                                     //~va06I~
        changed+=changedBGM;                                     //~v@@@I~//~va06I~
        changed+=changedBtn;                                       //~v@@@I~
                                                                   //~v@@@I~
        if (changed!=0)                                            //~v@@@I~
        {                                                          //~v@@@I~
	        swChanged=true;                                        //~v@@@I~
        }                                                          //~v@@@I~
        AG.swChangedPreference|=swChanged;                         //~vae8I~
//      swError|=chkPlaySeq(playSeq,strsUserBGMTitle);             //~vae9I~//~vai0R~
        swError|=chkPlaySeq(playSeq,strsUserBGMTitle,strsUserBGMUri);//~vai0I~
        if (Dump.Y) Dump.println("PrefSetting.dialog2Properties changed="+changed+",changedSound="+changedSound+",changedBGM="+changedBGM);//~v@@@I~//~va06R~
        return changed!=0;                                         //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************      //~vae9I~
//  private boolean chkPlaySeq(String PplaySeq,String[] Ptitle)    //~vae9I~//~vai0R~
    private boolean chkPlaySeq(String PplaySeq,String[] Ptitle,String[] Puri)//~vai0I~
    {                                                              //~vae9I~
        if (Dump.Y) Dump.println("PrefSetting.chkPlaySeq playseq="+PplaySeq+",title="+Utils.toString(Ptitle)+",uri="+Utils.toString(Puri));//~vae9I~//~vai0R~
    	boolean rc=false;                                          //~vae9R~
    	if (PplaySeq.length()==0 && bgBGM.getChecked()==PS_BGM_USER)//~vae9R~
        {                                                          //~vae9I~
        	UView.showToast(R.string.Err_NoPlaySeqNo);             //~vae9I~
            return true; //err,do not dismiss                      //~vae9R~
        }                                                          //~vae9I~
    	for (int ii=0;ii<PplaySeq.length();ii++)                     //~vae9I~
        {                                                          //~vae9I~
        	int num=Character.getNumericValue(PplaySeq.charAt(ii));//~vae9I~
//          if (Ptitle[num]==null || Ptitle[num].equals(NOTITLE))  //~vae9I~//~vai0R~
            if (Puri[num]==null || Puri[num].equals(""))           //~vai0I~
            {                                                      //~vae9I~
            	rc=true;                                           //~vae9I~
                break;                                             //~vae9I~
            }                                                      //~vae9I~
        }                                                          //~vae9I~
        if (rc)                                                    //~vae9I~
        	UView.showToast(R.string.Err_PlaySeqNo);               //~vae9I~
        if (Dump.Y) Dump.println("PrefSetting.chkPlaySeq rc="+rc); //~vae9I~
    	return rc;                                                 //~vae9I~
    }                                                              //~vae9I~
    //*******************************************************      //~v@@@I~
    @Override //SettingDlg                                         //~v@@@I~
    protected String getFilter()                                   //~v@@@I~
    {                                                              //~v@@@I~
    	return PROP_EXT_PREFERENCE;                              //~v@@@I~
    }                                                              //~v@@@I~
    //**************************************                       //~1A4zI~//~v@@@M~
    @Override
    public Prop checkValidity(String Pfname)                        //~1A4zI~//~v@@@R~
    {                                                              //~1A4zI~//~v@@@M~
        if (Dump.Y) Dump.println("PrefSettingg.checkValidity fnm="+Pfname);//~v@@@I~//~1ak2R~
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
        swError=false;                                             //~vae9I~
	    getValue();                                                //~v@@@I~
        if (swError)                                               //~vae9I~
        {                                                          //~vae9I~
	        if (Dump.Y) Dump.println("PrefSetting.onClickOK return by swError");//~vae9I~
            return;                                                //~vae9I~
        }                                                          //~vae9I~
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
//            if (PrefSetting.getBGMType()==PS_BGM_USER)           //~vae9R~
//            {                                                    //~vae9R~
//                int soundID=0;     //top                         //~vae9R~
//                if (Status.getGameStatus()==GS_GAME_STARTED)     //~vae9R~
//                {                                                //~vae9R~
//                    Rect r=Status.getGameSeq();                  //~vae9R~
//                    soundID=r.top+1;    //gameCtrGame            //~vae9R~
//                }                                                //~vae9R~
//                Sound.playBGM(soundID);  //play UserBGM          //~vae9R~
//            }                                                    //~vae9R~
//            else                                                 //~vae9R~
//            if (Status.getGameStatus()==GS_GAME_STARTED)           //~va06I~//~vae9R~
//            {                                                      //~va06I~//~vae9R~
//                Rect r=Status.getGameSeq();                        //~va06I~//~vae9R~
//                GC.playSound(r.top);    //gameCtrGame              //~va06I~//~vae9R~
//            }                                                      //~va06I~//~vae9R~
//            else                                                   //~va06I~//~vae9R~
//                Sound.playBGM(SOUNDID_BGM_TOP);                    //~va06I~//~vae9R~
            int soundID=getSoundID();                              //~vae9I~
			Sound.playBGM(soundID);  //play UserBGM                //~vae9I~
        }                                                          //~va06I~
	    if (Dump.Y) Dump.println("PrefSetting.onClickOK dismiss"); //~1ak2I~
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
    //*******************************************************************************//~vae8I~
    //*from MainActivity->AG-->                                    //~vae8I~
    //*******************************************************************************//~vae8I~
    public static void recoverProp(String Pmember)                 //~vae8I~
    {                                                              //~vae8I~
    	if (Dump.Y) Dump.println("PrefSetting.recoverProp,member="+Pmember);//~vae8R~
        String fpath=makeFullpath(Pmember);                        //~vae8R~
        if (fpath==null)                                           //~vae8I~
        	return;                                                //~vae8I~
        boolean rc=AG.prefProp.loadProperties(fpath);              //~vae8R~
    	if (Dump.Y) Dump.println("PrefSetting.recoverProp rc="+rc+",AG.prefProp="+AG.prefProp.toString());//~vae8R~
        if (rc)                                                    //~vae8I~
	        recoverSharedPreference();                             //~vae8R~
    }                                                              //~vae8I~
    //*******************************************************************************//~vae8I~
    //*from MainActivity->AG-->                                    //~vae8I~
    //*******************************************************************************//~vae8I~
    public static boolean saveProp(String Pmember)                 //~vae8R~
    {                                                              //~vae8I~
    	if (Dump.Y) Dump.println("PrefSetting.saveProp swChagedPreference="+AG.swChangedPreference);//~vae8R~
		if (!chkChangedOther()                                     //~vae8R~
        &&  !AG.swChangedPreference                                //~vae8R~
//      &&  !(AG.prefProp.getParameter(getKeyPS(PSID_SAVED),"")).equals(""))//external storage prop is old version//~vae8R~
        )                                                          //~vae8I~
        {                                                          //~vae8I~
    	    if (Dump.Y) Dump.println("PrefSetting.saveProp return by no change");   //~vae8I~
        	return false;                                                //~vae8I~
        }                                                          //~vae8I~
//      AG.prefProp.setParameter(getKeyPS(PSID_SAVED),"1");        //~vae8R~
        AG.swChangedPreference=true;	//for chkChangeOther       //~vae8R~
        String fpath=makeFullpath(Pmember);                        //~vae8R~
        boolean rc;                                                //~vae8I~
        if (UScoped.isScoped())                                    //~vae8I~
	        rc=AG.prefProp.savePropertiesScoped(fpath,PROP_NAME,true/*swOverride*/);      //~9B08I~//~vae0R~//~vae8I~
        else                                                       //~vae8I~
	        rc=AG.prefProp.saveProperties(fpath,PROP_NAME);//~vae8I~
    	if (Dump.Y) Dump.println("PrefSetting.saveProp rc="+rc);   //~vae8I~
        return rc;
    }                                                              //~vae8I~
    //*******************************************************************************//~vae8I~
    private static boolean chkChangedOther()                       //~vae8I~
    {                                                              //~vae8I~
        String key,vals,valp;                                      //~vae8I~
    	boolean rc=false;                                          //~vae8I~
        Prop p=AG.prefProp;
                                                                   //~vae8I~
        key=getKeyPS(PSID_YOURNAME);                               //~vae8I~
        vals=Utils.getPreference(PREFKEY_YOURNAME,"");             //~vae8R~
        valp=p.getParameter(getKeyPS(PSID_YOURNAME),"");           //~vae8R~
    	if (Dump.Y) Dump.println("PrefSetting.chkChangedOther _YOURNAME getPreference="+vals+",prefProp="+valp);//~vae8R~
        if (!valp.equals(vals))                                    //~vae8R~
        {                                                          //~vae8I~
	        p.setParameter(key,vals);                              //~vae8I~
        	rc=true;                                               //~vae8I~
        }                                                          //~vae8I~
    	if (Dump.Y) Dump.println("PrefSetting.chkChangedOther rc="+rc);//~vae8I~
        return rc;
    }                                                              //~vae8I~
    //*******************************************************************************//~vae8I~
    public static String makeFullpath(String Pmember)              //~vae8R~
    {                                                              //~vae8I~
    	if (Dump.Y) Dump.println("PrefSetting.makeFullpath member="+Pmember);//~vae8R~
        boolean swScoped=UScoped.isScoped();	//use scoped storage for history data//~vae0I~//~vae8I~
        String[] folders=FileDialog.getFolder(swScoped);           //~vae0I~//~vae8I~
        String workDirSD=folders[0];                                       //~9614I~//~vae8I~
        String pathDataDir=folders[1];                                     //~9614I~//~vae8I~
        String path=null;                                          //~vae8I~
        if (workDirSD!=null)	//external or scoped storage available//~vae8I~
        {                                                          //~vae8I~
        	path= UFile.makeFullpath(workDirSD,Pmember,""/*extension*/);//~vae8R~
        }                                                          //~vae8I~
        if (Dump.Y) Dump.println("PrefSetting.makeFullpath swScoped="+swScoped+",workDirSD="+workDirSD+",pathDataDir="+pathDataDir+",path="+path);//~vae8I~
        return path;                                               //~vae8I~
    }                                                              //~vae8I~
    //*******************************************************************************//~vae8I~
    private static void recoverSharedPreference()                  //~vae8R~
    {                                                              //~vae8I~
    	if (Dump.Y) Dump.println("PrefSetting.recoverSharedPreference");//~vae8I~
        Prop p=AG.prefProp;                                        //~vae8I~
        String yn=p.getParameter(getKeyPS(PSID_YOURNAME),"");      //~vae8I~
        Utils.putPreference(PREFKEY_YOURNAME,yn);            //~@@01I~//~vae8I~
        AG.YourName=yn;                                            //~vae8I~
        AG.aSound.recoverSound();                                  //~vae8I~
    	if (Dump.Y) Dump.println("PrefSetting.recoverSharedPreference _YOURNAME="+yn);//~vae8I~
    }                                                              //~vae8I~
    //*******************************************************************************//~v@@@I~
    //*******************************************************************************//~v@@@I~
    //*******************************************************************************//~v@@@I~
    public static int getOrientation()                         //~v@@@R~
    {                                                              //~v@@@I~
        int rc=AG.prefProp.getParameter(getKeyPS(PSID_ORIENTATION),0);//~v@@@I~
    	if (Dump.Y) Dump.println("PrefSetting.getOrientation rc="+rc);//~v@@@R~
        return rc;                                                 //~v@@@R~
    }                                                              //~v@@@I~
//    //**************************************                     //~va9fR~
//    public static int getOrientationPortReverse()                //~va9fR~
//    {                                                            //~va9fR~
//        int rc=AG.prefProp.getParameter(getKeyPS(PSID_ORIENTATION_PORT_REV),0);//~va9fR~
//        if (Dump.Y) Dump.println("PrefSetting.getOrientationPortReverse rc="+rc);//~va9fR~
//        return rc;                                               //~va9fR~
//    }                                                            //~va9fR~
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
    //**************************************                       //~vad1I~
    public static boolean isLeftyPortrait()                        //~vad1I~
    {                                                              //~vad1I~
		int def=0;	//false                                        //~vad1I~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_LEFTY_PORTRAIT),def)!=0;//~vad1I~
    	if (Dump.Y) Dump.println("PrefSetting.isLeftyPortrait:"+rc);//~vad1I~
        return rc;                                                 //~vad1I~
    }                                                              //~vad1I~
    //**************************************                       //~vad1I~
    public static boolean isLeftyLandscape()                       //~vad1I~
    {                                                              //~vad1I~
		int def=0;	//false                                        //~vad1I~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_LEFTY_LANDSCAPE),def)!=0;//~vad1I~
    	if (Dump.Y) Dump.println("PrefSetting.isLeftyLandscape:"+rc);//~vad1I~
        return rc;                                                 //~vad1I~
    }                                                              //~vad1I~
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
    //*******************************************************************************//~vae9I~
    public static String getBGMUserSelection()                     //~vae9I~
    {                                                              //~vae9I~
        String rc=AG.prefProp.getParameter(getKeyPS(PSID_USERBGM_SELECTION),"");//~vae9I~
    	if (Dump.Y) Dump.println("PrefSetting.getBGMUserSelection rc="+rc);//~vae9I~
        return rc;                                                 //~vae9I~
    }                                                              //~vae9I~
    //*******************************************************************************//~vae9I~
    public static boolean isUserBGMRound()                         //~vae9R~
    {                                                              //~vae9I~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_USERBGM_ROUND),0)!=0;//~vae9R~
    	if (Dump.Y) Dump.println("PrefSetting.isUserBGMRound rc="+rc);//~vae9R~
        return rc;                                                 //~vae9I~
    }                                                              //~vae9I~
    //*******************************************************************************//~vae9I~
    public static boolean isUseUPicker()                           //~vae9I~
    {                                                              //~vae9I~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_USERBGM_UPICKER),0)!=0;//~vae9I~
    	if (Dump.Y) Dump.println("PrefSetting.isUseUPicker rc="+rc);//~vae9I~
        return rc;                                                 //~vae9I~
    }                                                              //~vae9I~
//    //*******************************************************************************//~1ak2I~//~vai0R~
//    public void setBGMTitle(String Ptitle,String PstrUri)          //~1ak2I~//~vai0R~
//    {                                                              //~1ak2I~//~vai0R~
//        if (Dump.Y) Dump.println("PrefSetting.setBGMTitle title="+Ptitle+",strUti="+PstrUri);//~1ak2I~//~vai0R~
//        //TODO                                                     //~1ak2I~//~vai0R~
//    }                                                              //~1ak2I~//~vai0R~
//*************************************************************    //~vae9I~
    public static int getSoundID()                                 //~vae9I~
    {                                                              //~vae9I~
        int typeBGM=PrefSetting.getBGMType();                      //~vae9I~
	    int soundID=SOUNDID_BGM_TOP;                               //~vae9I~
        if (typeBGM==PS_BGM_NO)                                    //~vae9M~
        	soundID=-1;                                            //~vae9M~
        else                                                       //~vae9M~
        if (typeBGM==PS_BGM_USER)                                  //~vae9I~
        {                                                          //~vae9I~
        	soundID=getSoundIDUser();                              //~vae9R~
        }                                                          //~vae9I~
        else                                                       //~vae9I~
        if (Status.getGameStatus()!=GS_GAME_STARTED)               //~vae9I~
        	soundID=SOUNDID_BGM_TOP;                               //~vae9I~
        else                                                       //~vae9I~
        {                                                          //~vae9I~
        	soundID=SOUNDID_BGM_TOP;                               //~vae9I~
            Rect r=Status.getGameSeq();                            //~vae9I~
            int ctrGame=r.top;                                     //~vae9I~
        	if (Dump.Y) Dump.println("PrefSetting.getSoundID ctrGame="+ctrGame);//~vae9R~
            switch (typeBGM)                                       //~vae9I~
            {                                                      //~vae9I~
            case PS_BGM_4SEASONS:                                  //~vae9I~
                switch (ctrGame%PLAYERS)                           //~vae9I~
                {                                                  //~vae9I~
                case 0:                                            //~vae9I~
                    soundID=SOUNDID_BGM_GAME1SLOW;                 //~vae9I~
                    break;                                         //~vae9I~
                case 1:                                            //~vae9I~
                    soundID=SOUNDID_BGM_GAME2SLOW;                 //~vae9I~
                    break;                                         //~vae9I~
                case 2:                                            //~vae9I~
                    soundID=SOUNDID_BGM_GAME3SLOW;                 //~vae9I~
                    break;                                         //~vae9I~
                default:                                           //~vae9I~
                    soundID=SOUNDID_BGM_GAME4SLOW;                 //~vae9I~
                }                                                  //~vae9I~
                break;                                             //~vae9I~
            case PS_BGM_4SEASONS_FAST:                             //~vae9I~
                switch (ctrGame%PLAYERS)                           //~vae9I~
                {                                                  //~vae9I~
                case 0:                                            //~vae9I~
                    soundID=SOUNDID_BGM_GAME1FAST;                 //~vae9I~
                    break;                                         //~vae9I~
                case 1:                                            //~vae9I~
                    soundID=SOUNDID_BGM_GAME2FAST;                 //~vae9I~
                    break;                                         //~vae9I~
                case 2:                                            //~vae9I~
                    soundID=SOUNDID_BGM_GAME3FAST;                 //~vae9I~
                    break;                                         //~vae9I~
                default:                                           //~vae9I~
                    soundID=SOUNDID_BGM_GAME4FAST;                 //~vae9I~
                }                                                  //~vae9I~
                break;                                             //~vae9I~
            case PS_BGM_SERIESK:                                   //~vae9I~
                switch (ctrGame%PLAYERS)                           //~vae9I~
                {                                                  //~vae9I~
                case 0:                                            //~vae9I~
                    soundID=SOUNDID_BGM_EBURISHOU;                 //~vae9I~
                    break;                                         //~vae9I~
                case 1:                                            //~vae9I~
                    soundID=SOUNDID_BGM_MIZUCHUKOUKA;              //~vae9I~
                    break;                                         //~vae9I~
                case 2:                                            //~vae9I~
                    soundID=SOUNDID_BGM_TOUCHIKUKOUKA;             //~vae9I~
                    break;                                         //~vae9I~
                case 3:                                            //~vae9I~
                    soundID=SOUNDID_BGM_KYOUTO;                    //~vae9I~
                    break;                                         //~vae9I~
                default:                                           //~vae9I~
                    soundID=SOUNDID_BGM_TOP;                       //~vae9I~
                }                                                  //~vae9I~
                break;                                             //~vae9I~
            }//switch                                              //~vae9I~
        }                                                          //~vae9I~
        if (Dump.Y) Dump.println("PrefSetting.getSoundID rc="+soundID+",typeBGM="+typeBGM);//~vae9R~
        return soundID;                                            //~vae9M~
    }                                                              //~vae9I~
//*************************************************************    //~vae9I~
//*from alos UMediaStore                                           //~vae9I~
//*************************************************************    //~vae9I~
    public static int getSoundIDUser()                             //~vae9R~
    {                                                              //~vae9I~
    	int soundID=-1;                                            //~vae9I~
        String selection=getBGMUserSelection();     //~vae9I~
        int len=selection.length();                                //~vae9I~
        if (Status.getGameStatus()!=GS_GAME_STARTED)               //~vae9I~
        	soundID=0;                                             //~vae9I~
        else                                                       //~vae9I~
    	if (!PrefSetting.isUserBGMRound())                         //~vae9R~
        	soundID=0;                                             //~vae9I~
        else                                                       //~vae9I~
        {                                                          //~vae9I~
	    	Rect r=Status.getGameSeq();                            //~vae9I~
        	int offsGame=r.left/*set*/*PLAYERS+r.top/*game in set*/;//~vae9R~
            if (len<=1)                                            //~vae9I~
            	soundID=0;                                         //~vae9I~
            else                                                   //~vae9I~
                soundID=offsGame%(len-1)+1;                        //~vae9I~
        }                                                          //~vae9I~
        int rc=(soundID>=len) ? -1 : Character.getNumericValue(selection.charAt(soundID));//~vae9I~
        if (Dump.Y) Dump.println("PrefSetting.getSoundIDUser rc="+rc+",soundID="+soundID+",selection="+selection);//~vae9I~
        return rc;                                                 //~vae9I~
    }                                                              //~vae9I~
    public static int getSoundIDUser(int PplaySeq)                 //~vae9I~
    {                                                              //~vae9I~
        String selection=getBGMUserSelection();                    //~vae9I~
        int len=selection.length();                                //~vae9I~
        int rc=(PplaySeq>=len) ? -1 : Character.getNumericValue(selection.charAt(PplaySeq));//~vae9I~
        if (Dump.Y) Dump.println("PrefSetting.getSoundIDUser playseq="+PplaySeq+",rc="+rc+",selection="+selection);//~vae9I~
        return rc;                                                 //~vae9I~
    }                                                              //~vae9I~
//*************************************************************    //~vamuI~
    //**************************************                       //~vamuI~
	public static boolean isAllowRobotAllButton()                  //~vamuI~
    {                                                              //~vamuI~
    	int def=0;	//false                                        //~vamuI~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_ALLOW_ROBOT_ALL_BTN),def)!=0;//+vamuR~
        if (Dump.Y) Dump.println("PrefSetting.isAllowRobotAllButton rc="+rc);//~vamuI~
        return rc;                                                 //~vamuI~
    }                                                              //~vamuI~
    //**************************************                       //~vamuI~
	public static boolean isPlayAloneNotify()                      //~vamuI~
    {                                                              //~vamuI~
    	int def=DEFAULT_PLAY_ALONE_NOTIFY;	//true                 //~vamuR~
        boolean rc=AG.prefProp.getParameter(getKeyPS(PSID_PLAY_ALONE_NOTIFY),def)!=0;//+vamuR~
        if (Dump.Y) Dump.println("PrefSetting.isPlayAloneNotify rc="+rc);//~vamuI~
        return rc;                                                 //~vamuI~
    }                                                              //~vamuI~
}//class                                                           //~v@@@R~
