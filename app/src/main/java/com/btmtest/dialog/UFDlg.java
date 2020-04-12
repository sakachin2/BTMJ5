//*CID://+DATER~:                             update#=  228;       //~1Af6R~//~v@@@R~//~9813R~
//*****************************************************************//~v101I~
//*common FragmentDialog                                           //~v@@@I~
//*****************************************************************//~v101I~
package com.btmtest.dialog;                                         //~v@@@R~
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;                                          //~v@@@I~
import android.app.DialogFragment;                                 //~v@@@I~
import android.app.Dialog;                                         //~v@@@I~
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;                                      //~v@@@I~
import android.view.ViewGroup;                                     //~v@@@I~
import android.view.LayoutInflater;                                //~v@@@I~
import android.view.View;                                          //~v@@@I~
import android.widget.LinearLayout;

import com.btmtest.R;                                              //~v@@@I~
import com.btmtest.gui.UButton;                                     //~v@@@R~
import com.btmtest.utils.Dump;                                     //~v@@@R~
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;                                    //~v@@@I~
import static com.btmtest.StaticVars.AG;                           //~9305I~//~v@@@I~
import static com.btmtest.game.GConst.*;

//**************************************************************************//~v@@@I~
//*OnCreate-->onCreateDialog-->show-->onCreateView-->onStart       //~v@@@I~
//**************************************************************************//~v@@@I~
public class UFDlg extends DialogFragment                          //~v@@@R~
        implements  UButton.UButtonI              //~v@@@I~
{                                                                  //~2C29R~
    public static final int FLAG_OKBTN            =0x01;           //~v@@@R~
    public static final int FLAG_CLOSEBTN         =0x02;           //~v@@@I~
    public static final int FLAG_CANCELBTN        =0x04;           //~v@@@I~
    public static final int FLAG_HELPBTN          =0x08;           //~v@@@I~
    public static final int FLAG_TOUCH_CANCELABLE =0x10;           //~v@@@R~
    public static final int FLAG_BACK_CANCELABLE  =0x20;           //~v@@@R~
    public static final int FLAG_RULEBTN          =0x40;           //~v@@@I~
    public static final int FLAG_NOTITLE          =0x80;           //~9B16I~
                                                                   //~v@@@I~
//  private static final int COLOR_TITLE_DIVIDER=Color.argb(0xff,0x00,0x59,0x00);//~v@@@R~
                                                                   //~v@@@I~
    private static final String PARM_TITLE="title";                         //~v@@@I~
    private static final String PARM_HELPFILE="helpfile";                   //~v@@@I~
    private static final String PARM_HELPTITLE="helptitle";                 //~v@@@I~
    private static final String PARM_FLAG="flag";                           //~v@@@I~
    private static final String PARM_LAYOUT="layout";                       //~v@@@I~
                                                                   //~v@@@I~
//  private static final int BASE_NEXUS7=800;                      //~v@@@R~
                                                                   //~v@@@I~
	public 	Button btnClose,btnHelp,btnCancel,btnOK;               //~v@@@R~
	public 	Button btnRule;                                        //~v@@@I~
	private LinearLayout llRelatedRule;                            //~v@@@I~
	private boolean swNoRule;                                      //~v@@@I~
	protected String helpFilename,title;                           //~v@@@R~
	private	int helpTitleId;                                       //~v@@@I~
    protected int optionFlag;                                      //~v@@@I~
    protected View layoutView;                                     //~v@@@I~
    public Bundle bundle;                                          //~v@@@R~
    public String tagSuffix="";                                    //~v@@@R~
    protected Dialog androidDlg;                                     //~v@@@I~//~9303R~//~v@@@I~
//  protected boolean swNarrow;                                    //~v@@@R~
	//**********************************                           //~v@@@I~
	public UFDlg()                                                 //~v@@@R~
	{                                                              //~3105R~
	}
	//**********************************                           //~v@@@R~
//  public static UFDlg newInstance(UFDlg Pdescendant,String Ptitle,int Playoutid,int Pflag,int Phelptitle,String Phelpfilename)//~v@@@R~
    public static void setBundle(UFDlg Pdescendant,String Ptitle,int Playoutid,int Pflag,int Phelptitle,String Phelpfilename)//~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("UFDlg.newInstance title="+Ptitle);//~v@@@I~
//  	UFDlg dlg=new UFDlg();                                     //~v@@@R~
    	Bundle b=new Bundle();                                       //~v@@@R~
	    b.putString(PARM_TITLE,Ptitle);                       //~v@@@R~
        b.putString(PARM_HELPFILE,Phelpfilename);             //~v@@@R~
        b.putInt(PARM_HELPTITLE,Phelptitle);                  //~v@@@R~
        b.putInt(PARM_LAYOUT,Playoutid);                      //~v@@@R~
        b.putInt(PARM_FLAG,Pflag);                            //~v@@@R~
//      dlg.setArguments(b);                                       //~v@@@R~
//      dlg.bundle=b;	//for child                                //~v@@@R~
        Pdescendant.bundle=b;	//for child                        //~v@@@R~
    	if (Dump.Y) Dump.println("UFDlg.newInstance setArgument"); //~v@@@I~
        Pdescendant.setArguments(b);                                //~v@@@I~
        Pdescendant.setCancelable((Pflag & FLAG_BACK_CANCELABLE)!=0);//~v@@@I~
//      return (UFDlg)Pdescendant;                                 //~v@@@R~
    }                                                              //~v@@@I~
	//**********************************                           //~v@@@I~
//  public static UFDlg newInstance(UFDlg Pdescendant,int Ptitleid,int Playoutid,int Pflag,int Phelptitleid,String Phelpfilename)//~v@@@R~
    public static void setBundle(UFDlg Pdescendant,int Ptitleid,int Playoutid,int Pflag,int Phelptitleid,String Phelpfilename)//~v@@@R~
    {                                                              //~v@@@I~
    	String title=Utils.getStr(Ptitleid);                         //~v@@@I~
//  	return newInstance(Pdescendant,title,Playoutid,Pflag,Phelptitleid,Phelpfilename);//~v@@@R~
    	setBundle(Pdescendant,title,Playoutid,Pflag,Phelptitleid,Phelpfilename);//~v@@@R~
    }                                                              //~v@@@I~
	//**********************************                           //~v@@@I~
//  public static UFDlg newInstance(UFDlg Pdescendant,int Ptitleid,int Playoutid,int Pflag)//~v@@@R~
    public static void setBundle(UFDlg Pdescendant,int Ptitleid,int Playoutid,int Pflag)//~v@@@R~
    {                                                              //~v@@@I~
    	String title=Utils.getStr(Ptitleid);                       //~v@@@I~
//  	return newInstance(Pdescendant,title,Playoutid,Pflag);     //~v@@@R~
    	setBundle(Pdescendant,title,Playoutid,Pflag);              //~v@@@R~
    }                                                              //~v@@@I~
	//**********************************                           //~v@@@I~
//  public static UFDlg newInstance(UFDlg Pdescendant,String Ptitle,int Playoutid,int Pflag)//~v@@@R~
    public static void setBundle(UFDlg Pdescendant,String Ptitle,int Playoutid,int Pflag)//~v@@@R~
    {                                                              //~v@@@I~
//  	return newInstance(Pdescendant,Ptitle,Playoutid,Pflag,0/*helptitle*/,null/*helpfile*/);//~v@@@R~
    	setBundle(Pdescendant,Ptitle,Playoutid,Pflag,0/*helptitle*/,null/*helpfile*/);//~v@@@R~
    }                                                              //~v@@@I~
//    //**********************************                         //~v@@@R~
//    private void setOnShowListener(Dialog Pdlg)   //not effective to fullscreen when dialog opened//~v@@@R~
//    {                                                            //~v@@@R~
//        final Dialog dlgOSL=Pdlg;                                //~v@@@R~
//        Pdlg.setOnShowListener(new DialogInterface.OnShowListener()//~v@@@R~
//                                    {                            //~v@@@R~
//                                        @Override                //~v@@@R~
//                                        public void onShow(DialogInterface Pdlgi)//~v@@@R~
//                                        {                        //~v@@@R~
//                                            if (Dump.Y) Dump.println("UFDlg.OnShowListener onShow");//~v@@@R~
//                                            setFocusable(dlgOSL,false);//~v@@@R~
//                                            setFullscreen();     //~v@@@R~
//                                            setFocusable(dlgOSL,true);//~v@@@R~
//                                        }                        //~v@@@R~
//                                    });                          //~v@@@R~
//    }                                                            //~v@@@R~
	//**********************************                           //~v@@@I~
	public void show()                                             //~v@@@R~
    {                                                              //~v@@@I~
    	String tag=Utils.getClassName(this)+tagSuffix;             //~v@@@R~
    	if (Dump.Y) Dump.println("UFDlg.show tag="+tag);           //~v@@@R~
//      setFocusable(false);                                       //~v@@@R~
//      setFullscreen();                                           //~v@@@R~
        UView.showDF((DialogFragment)this,tag);                    //~v@@@R~
//      setFocusable(true);                                        //~v@@@R~
    }                                                              //~v@@@I~
//    //**********************************                         //~v@@@R~
//    private void setFocusable(boolean Psw)                       //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("UFDlg.setFocusable sw="+Psw);  //~v@@@R~
//        if (Psw)                                                 //~v@@@R~
//            getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);//~v@@@R~
//        else                                                     //~v@@@R~
//            getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);//~v@@@R~
//    }                                                            //~v@@@R~
//    //**********************************                         //~v@@@R~
//    private void setFocusable(Dialog Pdlg,boolean Psw)           //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("UFDlg.setFocusable sw="+Psw+",dialog="+Pdlg.toString());//~v@@@R~
//        if (Psw)                                                 //~v@@@R~
//            Pdlg.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);//~v@@@R~
//        else                                                     //~v@@@R~
//            Pdlg.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);//~v@@@R~
//    }                                                            //~v@@@R~
//    //**********************************                         //~v@@@R~
//    private void setFullscreen()                                 //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("UFDlg.setFullscreen");         //~v@@@R~
//        MainActivity.hideNavigationBar(true);                    //~v@@@R~
//    }                                                            //~v@@@R~
//    //**********************************                         //~v@@@R~
//    public void show(UFDlg Pdlg)                                 //~v@@@R~
//    {                                                            //~v@@@R~
//        String tag=Utils.getClassName(Pdlg);                     //~v@@@R~
//        if (Dump.Y) Dump.println("UFDlg.show tag="+tag);         //~v@@@R~
//        UView.showDF((DialogFragment)Pdlg,tag);                  //~v@@@R~
//    }                                                            //~v@@@R~
    //******************************************                   //~v@@@I~
  	@Override                                                      //~v@@@I~
    public void onCreate(Bundle Pbundle)                           //~v@@@R~
    {                                                              //~v@@@I~
        super.onCreate(Pbundle);                                   //~v@@@I~
//      swNarrow=(AG.portrait ? AG.scrWidth : AG.scrHeight)<BASE_NEXUS7;  //mediapad 3.7:600*1024,nexus7:800*1280//~v@@@R~
    	if (Dump.Y) Dump.println("UFDlg.onCreate swSmallDevice="+AG.swSmallDevice);//~v@@@R~
//      if (swNarrow)                                              //~v@@@R~
        if (AG.swSmallDevice)                                         //~v@@@I~
        {                                                          //~v@@@I~
    		if (Dump.Y) Dump.println("UFDlg.onCreate setStyle smallDevice");   //~v@@@I~//~9923R~
	        setStyle(0,R.style.DialogThemeCustomNarrow);           //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~9923I~
        {                                                          //~9923I~
    		if (Dump.Y) Dump.println("UFDlg.onCreate setStyle non small");//~9923I~
//          setStyle(DialogFragment.STYLE_NO_TITLE,0);             //~9923I~
//          setStyle(DialogFragment.STYLE_NORMAL,0);               //~9923I~
            setStyle(DialogFragment.STYLE_NORMAL,R.style.DialogThemeCustomWithTitle); //TODO test//~9923I~
        }                                                          //~9923I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
  	@Override                                                      //~v@@@I~
    public void onResume()                                              //~v@@@I~
    {                                                              //~v@@@I~
//  	super.onResume();                                          //~v@@@I~//~9925R~
    	if (Dump.Y) Dump.println("UFDlg.onResume");                //~v@@@I~
//      setWidth(androidDlg);                                      //~v@@@R~
        setWidthOnResume(androidDlg);                              //~v@@@I~
    	super.onResume();                                          //~9925I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
  	@Override                                                      //~v@@@I~
    public void onStart()                                          //~v@@@I~
    {                                                              //~v@@@I~
    	super.onStart();                                           //~v@@@I~
    	if (Dump.Y) Dump.println("UFDlg.onStart");                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
  	@Override                                                      //~v@@@R~
    public Dialog onCreateDialog(Bundle Pbundle)                   //~v@@@R~
    {                                                              //~v@@@R~
    	if (Dump.Y) Dump.println("UFDlg.onCreateDialog");          //~v@@@I~
        Dialog dlg=null;                                           //~v@@@I~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	dlg=super.onCreateDialog(Pbundle);     //titleDivider is not exist//~v@@@R~
//      	dlg=new Dialog(getActivity(),R.style.DialogThemeCustom);    //troublesome//~v@@@I~//TDOD test//~v@@@R~
//        	setStyle(0,R.style.DialogThemeCustom);                 //~v@@@R~
//          setWidth(dlg);                                         //~v@@@R~
    	    if ((optionFlag & FLAG_NOTITLE)!=0)                     //~9B16I~
            {                                                      //~9B16I~
		    	if (Dump.Y) Dump.println("UFDlg.onCreateDialog request NoTitle");//~9B16I~
	        	dlg.requestWindowFeature(Window.FEATURE_NO_TITLE); //~9B16I~
            }                                                      //~9B16I~
        }                                                          //~v@@@I~
        catch (Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"UFDlg:onCreateDialog");                //~v@@@I~
        }                                                          //~v@@@I~
        return dlg;                                                //~v@@@R~
    }                                                              //~v@@@R~
//    //******************************************                   //~v@@@I~//~9925R~
//    private void setWidth(Dialog Pdlg)                             //~v@@@R~//~9925R~
//    {                                                              //~v@@@I~//~9925R~
////      if (swNarrow && !AG.portrait)   //landscape                //~v@@@R~//~9925R~
//        if (AG.swSmallDevice && !AG.portrait)   //landscape        //~v@@@I~//~9925R~
//        {                                                          //~v@@@I~//~9925R~
//            double rate=(double)AG.scrHeight/AG.scrWidth;   //     //~v@@@R~//~9925R~
//            if (Dump.Y) Dump.println("UFDlg.setWidth HH="+AG.scrHeight+",WW="+AG.scrWidth+",rate="+rate);//~v@@@I~//~9925R~
//            UView.setDialogWidth(Pdlg,rate);                       //~v@@@R~//~9925R~
//        }                                                          //~v@@@I~//~9925R~
//    }                                                              //~v@@@I~//~9925R~
    //******************************************                   //~v@@@I~
    protected void setWidthOnResume(Dialog Pdlg)                   //~v@@@I~
    {                                                              //~v@@@I~
		int ww=getDialogWidth();                                   //~v@@@R~
    	if (Dump.Y) Dump.println("UFDlg.setWidthOnResume ww="+ww); //~v@@@R~
        if (ww!=0)                                                 //~v@@@R~
	    	UView.setDialogWidth(Pdlg,ww);                         //~v@@@R~
        else                                                       //~9924I~
//	    	UView.setDialogWidthMatchParent(Pdlg);	//HW MediaPad T5(android8) expand dialog height,so set hight=wrap_content required//~9924I~//~9925R~
//	    	UView.setDialogWidthWrapContent(Pdlg);	//HW MediaPad T5(android8) expand dialog height,so set hight=wrap_content required//~9925R~
  	    	UView.setDialogWidthMatchParentPortrait(Pdlg);	//HW MediaPad T5(android8) expand dialog height,so set hight=wrap_content required//~9925I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected int getDialogWidth()                                 //~v@@@R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("UFDlg.getDialogWidth ww=0");     //~v@@@R~
    	return 0;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    public int getDialogWidthSmallDevice()                         //~v@@@I~
    {                                                              //~v@@@I~
    	int ww=0;                                                  //~v@@@I~
        if (AG.swSmallDevice)                                      //~v@@@I~
	    	ww=AG.portrait ? AG.scrWidthReal : AG.scrHeightReal;   //~v@@@I~
    	if (Dump.Y) Dump.println("UFDlg.getDialogWidthSmallDevice ww=0");//~v@@@I~
    	return ww;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~9819I~
    public static int getDialogWidthSmallDevicePortrait()                 //~9819I~//~9B11R~
    {                                                              //~9819I~
    	int ww=0;                                                  //~9819I~
        if (AG.swSmallDevice)                                      //~9819I~
	    	ww=AG.portrait ? AG.scrWidthReal : 0;                  //~9819I~
    	if (Dump.Y) Dump.println("UFDlg.getDialogWidthSmallDevicePortrait swPortrait="+AG.portrait+",ww="+ww);//~9819I~
    	return ww;                                                 //~9819I~
    }                                                              //~9819I~
    //******************************************                   //~9820I~
    public static int getDialogWidthSmallDevicePortraitWithRate(double Prate)//~9820R~//~9B11R~
    {                                                              //~9820I~
    	int ww=getDialogWidthSmallDevicePortrait();                //~9820R~
	    ww=(int)(ww*Prate);                                        //~9820R~
    	if (Dump.Y) Dump.println("UFDlg.getDialogWidthSmallDevicePortraitWithRate rate="+Prate+",ww="+ww);//~9820R~
    	return ww;                                                 //~9820I~
    }                                                              //~9820I~
    //******************************************                   //~9820I~
    public int getDialogWidthSmallDevicePortraitFixedRate()        //~9820I~
    {                                                              //~9820I~
    	int ww=getDialogWidthSmallDevicePortraitWithRate(RATE_SMALLDEVICE_WIDTH);//~9820I~
    	if (Dump.Y) Dump.println("UFDlg.getDialogWidthSmallDevicePortraitFixedRate ww="+ww);//~9820I~
    	return ww;                                                 //~9820I~
    }                                                              //~9820I~
    //******************************************                   //~9B11I~
    public static int getDialogWidthFixedRate()                    //~9B11I~
    {                                                              //~9B11I~
        int ww=Math.min(AG.scrWidthReal,AG.scrHeightReal);         //~9809R~//~9B11I~
	    ww*=RATE_MAX_WIDTH;	//0.95                                 //~9B11I~
    	if (Dump.Y) Dump.println("UFDlg.getDialogWidthFixRate ww="+ww+"scrWidthReal="+AG.scrWidthReal+",scrHeightReal="+AG.scrHeightReal);//~9B11R~
    	return ww;                                                 //~9B11I~
    }                                                              //~9B11I~
    //******************************************                   //~v@@@M~
	@Override                                                      //~v@@@M~
    public View onCreateView(LayoutInflater Pinflater, ViewGroup Pcontainer, Bundle Pbundle)//~v@@@R~
	{                                                              //~v@@@M~
    	if (Dump.Y) Dump.println("UFDlg.onCreateView");            //~v@@@I~
        Bundle b=getArguments();                                   //~v@@@I~
        title=b.getString(PARM_TITLE);                             //~v@@@R~
        int layoutid=b.getInt(PARM_LAYOUT);                        //~v@@@I~
        optionFlag=b.getInt(PARM_FLAG);                            //~v@@@I~
        helpFilename=b.getString(PARM_HELPFILE);                   //~v@@@I~
        helpTitleId=b.getInt(PARM_HELPTITLE);                     //~v@@@I~
                                                                   //~v@@@I~
//      getDialog().setTitle(title);    //TODO test before inflate //~9923I~
        layoutView = Pinflater.inflate(layoutid,null);              //~v@@@R~
        if (Dump.Y) Dump.println("UFDlg.onCreateView layoutid="+Integer.toHexString(layoutid));//~9820I~
        Dialog dlg=getDialog();                                    //~v@@@M~
//      dlg.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);	//TODO test//~9923R~
//      dlg.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //TODO test//~9923I~
        androidDlg=dlg;                                            //~v@@@I~
        dlg.setCanceledOnTouchOutside((optionFlag & FLAG_TOUCH_CANCELABLE)!=0);//~v@@@R~
    	if ((optionFlag & FLAG_NOTITLE)!=0)                         //~9B16I~
        {                                                          //~9B16I~
            if (Dump.Y) Dump.println("UFDlg.onCreateDialog request NoTitle");//~9B16I~
            dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);     //~9B16I~
        }                                                          //~9B16I~
        else                                                       //~9B16I~
	        dlg.setTitle(title);                                       //~v@@@I~//~9B16R~
	    AG.dialogPaddingHorizontal=UView.getDialogPaddingHorizontal(dlg);//~9927I~
        setDivider(dlg,layoutView,0/*color:default*/);    //divide not found on N2//~v@@@R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	initLayout(layoutView);                                //~v@@@R~
//          setOnShowListener(dlg);                                //~v@@@R~
        }                                                          //~v@@@I~
        catch (Exception e)                                        //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"UFDlg:onCreateView");                  //~v@@@I~
        }                                                          //~v@@@I~
        return layoutView;                                         //~v@@@R~
    }                                                              //~v@@@M~
    //******************************************                   //~v@@@I~
    public static void setDivider(Dialog Pdlg,View Pview,int Pcolor)//~v@@@R~
    {                                                              //~v@@@I~
        int id;  View v;                                           //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg:setDivider color="+Integer.toHexString(Pcolor));//~v@@@R~
//        id=Pdlg.getContext().getResources().getIdentifier("android:id/alertTitle",null,null);//~v@@@I~
//        TextView tv=Pdlg.findViewById(id);                       //~v@@@I~
//        if (Dump.Y) Dump.println("UFDlg.setDivider find in dlg getid alertTitle="+id+",TextView==null?="+(tv==null));//~v@@@I~
//        if (tv!=null)                                            //~v@@@I~
//        {                                                        //~v@@@I~
//            tv.setBackgroundColor(Pcolor);                       //~v@@@I~
//        }                                                        //~v@@@I~
                                                                   //~v@@@I~
//        id=Pdlg.getContext().getResources().getIdentifier("android:id/titleDivider",null,null);//~v@@@R~
//        v=Pview.findViewById(id);                                //~v@@@I~
//        if (Dump.Y) Dump.println("UFDlg.setDivider find in view getid titleDivider="+id+",view==null?="+(v==null));//~v@@@I~
//        v=Pdlg.findViewById(id);                                 //~v@@@R~
//        if (Dump.Y) Dump.println("UFDlg.setDivider getid titleDivider="+id+",view==null?="+(v==null));//~v@@@R~
//        if (v!=null)                                             //~v@@@R~
//        {                                                        //~v@@@R~
//            v.setBackgroundColor(Pcolor);                        //~v@@@R~
//        }                                                        //~v@@@R~
//        id=Pdlg.getContext().getResources().getIdentifier("android:id/titleDividerNoCustom",null,null);//~v@@@R~
//        v=Pdlg.findViewById(id);                                 //~v@@@R~
//        if (Dump.Y) Dump.println("UFDlg.setDivider getid titleDividerNoCustom="+id+",view==null?="+(v==null));//~v@@@R~
//        v=Pview.findViewById(id);                                //~v@@@I~
//        if (Dump.Y) Dump.println("UFDlg.setDivider find in Pview getid titleDividerNoCustom="+id+",view==null?="+(v==null));//~v@@@I~
//        id=R.id.titleDividerNoCustom;                            //~v@@@R~
//        v=Pdlg.findViewById(id);                                 //~v@@@R~
//        if (Dump.Y) Dump.println("UFDlg.setDivider R.id.titleDividerNoCustom="+id+",view==null?="+(v==null));//~v@@@R~
//        v=Pview.findViewById(id);                                //~v@@@R~
//        if (Dump.Y) Dump.println("UFDlg.setDivider find in layoutView R.id.titleDividerNoCustom="+id+",view==null?="+(v==null));//~v@@@R~
////      id=R.id.titleDivider;                                    //~v@@@R~
////      v=Pdlg.findViewById(id);                                 //~v@@@R~
////      if (Dump.Y) Dump.println("UFDlg.setDivider R.id.titleDivider="+id+",view==null?="+(v==null));//~v@@@R~
        int color=Pcolor;                                          //~v@@@I~
        if (Pcolor==0)                                             //~v@@@I~
            color=AG.resource.getColor(R.color.title_divider);//~v@@@I~
        id=Pdlg.getContext().getResources().getIdentifier("android:id/titleDivider",null,null);//~v@@@I~
        v=Pdlg.findViewById(id);                                   //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg.setDivider getid titleDivider="+Integer.toHexString(id)+",view==null?="+(v==null));//~v@@@I~
    	View vd=Pview.findViewById(R.id.title_divider_custom);     //~v@@@R~
        if (Dump.Y) Dump.println("UFDlg.setDivider user title_divider="+Integer.toHexString(R.id.title_divider_custom)+",view==null?="+(vd==null));//~v@@@R~
        if (v==null)                                               //~v@@@I~
        {                                                          //~v@@@I~
        	if (vd!=null)                                          //~v@@@I~
		        if (Pcolor!=0)                                     //~v@@@I~
		            v.setBackgroundColor(color);                   //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	if (Pcolor!=0)                                         //~v@@@I~
		        v.setBackgroundColor(color);                       //~v@@@I~
            if (vd!=null)                                          //~v@@@I~
    	    	vd.setVisibility(View.GONE);                       //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected void initLayout(View Playout)                        //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg:initLayout");              //~v@@@I~
	    initLayoutUFDlg(Playout);                                  //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected void initLayoutUFDlg(View Playout)                   //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg:initLayoutUFDlg");         //~v@@@R~
	    initButton(Playout);                                       //~v@@@I~
	}                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void initButton(View Playout)                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg:initButton");              //~v@@@I~
        if ((optionFlag & FLAG_OKBTN)!=0)                          //~v@@@I~
	        btnOK=UButton.bind(Playout,R.id.OK,this);              //~v@@@I~
        if ((optionFlag & FLAG_CLOSEBTN)!=0)                       //~v@@@I~
	        btnClose=UButton.bind(Playout,R.id.Close,this);        //~v@@@I~
        if ((optionFlag & FLAG_CANCELBTN)!=0)                      //~v@@@I~
	        btnCancel=UButton.bind(Playout,R.id.Cancel,this);      //~v@@@I~
        if ((optionFlag & FLAG_HELPBTN)!=0)                        //~v@@@I~
	        btnHelp=UButton.bind(Playout,R.id.Help,this);          //~v@@@I~
        if ((optionFlag & FLAG_RULEBTN)!=0)                        //~v@@@I~
        {                                                          //~v@@@I~
	        btnRule=UButton.bind(Playout,R.id.btnRuleRelated,this);//~v@@@I~
	        llRelatedRule=(LinearLayout)Playout.findViewById(R.id.llRelatedRule);//~v@@@I~
            swNoRule=PrefSetting.isNoRelatedRule();               //~v@@@I~
            showRule(swNoRule);                                    //~v@@@I~
        }                                                          //~v@@@I~
	}                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    private void showRule(boolean PswNoRule)                      //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg:showRule swNoRule="+PswNoRule);//~v@@@I~
	    if (btnRule==null || llRelatedRule==null)                  //~v@@@I~
        	return;                                                //~v@@@I~
    	if (PswNoRule)                                             //~v@@@I~
        {                                                          //~v@@@I~
            btnRule.setText(Utils.getStr(R.string.View));          //~v@@@I~//+0207R~
            llRelatedRule.setVisibility(View.GONE);                //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
            btnRule.setText(Utils.getStr(R.string.Hide));        //~v@@@I~//+0207R~
            llRelatedRule.setVisibility(View.VISIBLE);             //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override //UButtonI                                           //~v@@@I~
    public void onClickButton(Button Pbutton)                   //~v@@@R~
	{                                                              //~v@@@I~
    	boolean rc=true;                                           //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg:onClickButton"+Pbutton.getText());//~v@@@I~
    	try                                                        //~v@@@I~
        {                                                          //~v@@@I~
        	int id=Pbutton.getId();                                //~v@@@I~
        	switch(id)                                             //~v@@@I~
            {                                                      //~v@@@I~
            case R.id.OK:                                          //~v@@@R~
                onClickOK();                                       //~v@@@R~
                break;                                             //~v@@@I~
            case R.id.Close:                                       //~v@@@I~
                onClickClose();                                    //~v@@@I~
                break;                                             //~v@@@I~
            case R.id.Cancel:                                      //~v@@@I~
                onClickCancel();                                   //~v@@@I~
                break;                                             //~v@@@I~
            case R.id.Help:                                        //~v@@@I~
                onClickHelp();                                     //~v@@@I~
                break;                                             //~v@@@I~
            case R.id.btnRuleRelated:                              //~v@@@I~
                onClickRule();                                     //~v@@@I~
                break;                                             //~v@@@I~
            default:                                               //~v@@@I~
                onClickOther(id);                                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"UFDlg:onClick:"+Pbutton.getText());    //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@M~
	@Override                                                      //~v@@@M~
    public void onActivityCreated(Bundle Pbundle)                  //~v@@@M~
	{                                                              //~v@@@M~
        if (Dump.Y) Dump.println("UFDlg:onActivityCreated");       //~v@@@I~
    	super.onActivityCreated(Pbundle);                          //~v@@@M~
        RestoreInstanceState(Pbundle);                             //~v@@@M~
     }                                                             //~v@@@M~
    //******************************************                   //~v@@@M~
    //*called after onPause                                        //~v@@@M~
    //******************************************                   //~v@@@M~
	@Override                                                      //~v@@@M~
    public void onSaveInstanceState(Bundle Pbundle)                //~v@@@M~
	{                                                              //~v@@@M~
        if (Dump.Y) Dump.println("UFDlg:onSaveInstanciate bundle!=null="+(Pbundle!=null));//~v@@@I~
		super.onSaveInstanceState(Pbundle);                        //~v@@@M~
    }                                                              //~v@@@M~
    //***********************************************************  //~v@@@M~
    //*DialogFragment has no method onRestoreInstanciate           //~v@@@M~
    //*restore at onActivityCreated                                //~v@@@M~
    //***********************************************************  //~v@@@M~
    public void RestoreInstanceState(Bundle Pbundle)               //~v@@@M~
	{                                                              //~v@@@M~
        if (Dump.Y) Dump.println("UFDlg:RestoreInstanciate bundle not null="+(Pbundle!=null));//~v@@@R~
        setupDialog(Pbundle);                                      //~v@@@I~
    }                                                              //~v@@@M~
    //***********************************************************  //~v@@@I~
    // will be overridden to fill layout contents                  //~v@@@I~
    //***********************************************************  //~v@@@I~
    public void setupDialog(Bundle Pbundle)                        //~v@@@I~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg:initDialog bundle not null="+(Pbundle!=null));//~v@@@I~
    }                                                              //~v@@@I~
	//**********************************                           //~v@@@R~
	@Override                                                      //~v@@@I~
    public void onDestroy()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg.onDestroy");               //~v@@@R~
        super.onDestroy();                                         //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    @Override                                                      //~v@@@I~
    public void onDismiss(DialogInterface Pdialog)                 //~v@@@I~
 	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg:onDismiss");               //~v@@@I~
        remove(this);                                                  //~v@@@I~//~9903R~
        onDismissDialog();                                         //~v@@@I~
//      remove();                                                  //~v@@@R~
    }                                                              //~v@@@I~
    //*****************************                                //~v@@@I~
    protected void onDismissDialog()                               //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg:onDismissDialog");         //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@M~
    protected void onClickOK()                                     //~v@@@R~
	{                                                              //~v@@@M~
        if (Dump.Y) Dump.println("UFDlg.onClickOK");               //~v@@@I~
	    dismiss();                                                 //~v@@@M~
    }                                                              //~v@@@M~
    //******************************************                   //~v@@@I~
    protected void onClickClose()                                  //~v@@@R~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg.onClickClose");            //~v@@@I~
	    dismiss();                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected void onClickCancel()                                 //~v@@@R~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg.onClickCancel");           //~v@@@I~
	    dismiss();                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected void onClickHelp()                                   //~v@@@R~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg.onClickHelp");             //~v@@@I~
    	HelpDialog.newInstance(helpTitleId,helpFilename).show();                 //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    protected void onClickRule()                                   //~v@@@I~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg.onClickRule swNoRule="+swNoRule);//~v@@@I~
        swNoRule=!swNoRule;                                         //~v@@@I~
	    showRule(swNoRule);                                        //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************                   //~v@@@I~
    //*will be overridden                                          //~v@@@I~
    //******************************************                   //~v@@@I~
    protected void onClickOther(int Pid)                           //~v@@@R~
	{                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UFDlg.onClickOther");            //~v@@@I~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    public static void remove(DialogFragment Pdlg)                                           //~v@@@I~//~9903R~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("UFDlg.remove");                  //~v@@@I~
        FragmentManager fm=Pdlg.getFragmentManager();                   //~v@@@I~
        if (fm==null)                                              //~v@@@I~
        {                                                          //~9904I~
	        if (Dump.Y) Dump.println("UFDlg.remove FragmentManager is null");//~9904I~
            return;                                                //~v@@@I~
        }                                                          //~9904I~
        FragmentTransaction ft=fm.beginTransaction();              //~v@@@I~
        if (ft==null)                                              //~v@@@I~
        {                                                          //~9904I~
	        if (Dump.Y) Dump.println("UFDlg.remove FragmentTransaction is null");//~9904R~
            return;                                                //~v@@@I~
        }                                                          //~9904I~
        ft.remove(Pdlg);                                           //~v@@@I~//~9903R~
        ft.commit();                                               //~v@@@I~
        fm.popBackStack();                                         //~v@@@I~
	    if (Dump.Y) Dump.println("UFDlg.remove exit");             //~9904I~
    }                                                              //~v@@@I~
}//class                                                           //~v@@@R~
