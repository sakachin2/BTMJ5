//*CID://+vayZR~: update#= 519;                                    //+vayZR~
//**********************************************************************//~v101I~
//2025/03/01 vayZ should use not statusbar height but cutout       //~vayZI~
//2025/02/26 vayR statusbar hides top button                       //~vayRI~
//2025/02/25 vayN try edgemode from api30, and set base to scrWidth/scrHeight=real//~vayNI~
//2025/02/24 vayM android14 tablet(google pixel emulator), screen is change to LetterBox if start by landscape.//~vayMI~
//                Web says. from android12(Api31). optionally(by device maker) change to letterBox by orientation//~vayMI~
//2025/02/23 vayL adjust Top camera area if edge mode portrait     //~vayLI~
//2025/02/22 vayK api34 landRight spaceRight is too large. use inset not systemGesture but syste.//~vayKI~
//2025/02/21 vayH api34 is also edgeToEdgeMode by sdk35            //~vayHI~
//2025/02/19 vayy bypass set marginRight by rotation(camera button may exist)//~vayyI~
//2025/02/17 vayt from Android15,fullscreen(edge to edge)          //~vaytI~
//2025/02/16 vays (Bug)Decorview may swap W and H for api34        //~vaysI~
//2025/02/16 vayq open foldable+3button hide button of top         //~vayqI~
//2025/02/15 vaym DecorView excludes status/title/navigation. Use this as scrHeight.(at g10:api30, there is difference with realHeight-bottom of inset:systemGesture)//~vaymI~
//2023/02/31 vay6 foldable device,  open left-right is landscape. So compatibility mode(narrow portrait) if orientation is set to portrait.//~vay6I~
//2023/02/28 vay1 allow rotation of top view before startGame, then lock and view game panel//~vay1I~
//2023/02/22 vay0 v1.29 api14(Android34) is required.              //~vay0I~
//2023/01/11 vav8 vav7 is no good back to 430                      //~vav8I~
//2023/01/11 vav7 change smallFont decision from 430 to >320(320<xhdpi<=480)//~vav7I~
//2023/01/11 vav6 change smallDevice decision from 800 to 720(HD)  //~vav6I~
//2023/01/10 vav1 aspect change for long device to >=16:9          //~vav1I~
//2022/11/01 vau2 Ahsv-1ams 2022/11/01 control request permission to avoid 1amh:"null permission result".(W Activity: Can request only one set of permissions at a time)//~vau2I~
//2022/10/18 vate emulater pixel5(APi31) landscape; nor right bttons shown. it is same as api33//~vateI~
//2022/10/17 vata emulater(APi33) landscape; nor right bttons shown//~vataI~
//2022/10/16 vat9 logdevice determination change 2.0-->1.8 (tested emulator pixel5)//~vat9I~
//2022/03/28 vam6 android12(api31) Display.getRealSize, getRealMetrics//~vam6I~
//2021/10/21 vaf0 Play console crash report "IllegalStateException" at FragmentManagerImple.1536(checkStateLoss)//~vaf0I~
//2021/09/28 vaeg enlarge nameplate for long device                //~vaegI~
//2021/09/27 vaef gesture navigation mode from android11           //~vaefI~
//2021/09/26 vaee gesture navigation mode from android10           //~vaeeI~
//2021/09/24 vaed more adjust for small device(dip=width/dip2px<=320)//~vaedI~
//2021/08/25 vae0 Scped for BTMJ5                                  //~1ak2I~
//1ak2 2021/09/04 access external audio file                       //~1ak2I~
//1aj0 2021/08/14 androd11(api30) deprecated at api30;getDefaultDisplay, display.getSize(), display/getMetrics()//~1aj0I~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2020/11/04 va40 Android10(api29) upgrade                         //~va40I~
//2020/11/06 va30 change greenrobot EventCB to URunnable           //~va30I~
//**********************************************************************//~va30I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.utils;

import android.Manifest;
import android.annotation.TargetApi;                               //~1aj0I~
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;                                    //~0913I~
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
//import android.app.DialogFragment;                               //~va40R~
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;                      //~va40I~
import androidx.window.layout.DisplayFeature;                                //~5122I~//~vay6I~
import androidx.window.layout.WindowLayoutInfo;                    //~vay6I~
import androidx.window.layout.WindowMetricsCalculator;

import android.view.WindowMetrics;                                 //~vay0R~
import android.widget.LinearLayout;

import java.util.EmptyStackException;
import java.util.List;                                             //~vay6I~
//import de.greenrobot.event.EventBus;                             //~va30R~

import com.btmtest.R;
                                                                   //~v@@@I~
import static com.btmtest.StaticVars.AG;                           //~v@21I~//~v@@@I~
import static com.btmtest.AG.*;                                    //~vayKI~
//~v@@@I~


public class UView                                                 //~v@@@I~
{                                                                  //~v@@@I~
    private static final int HIGHT_DPI_TV=72;   //?                //~v@@@I~
    private static final int HIGHT_DPI_XHIGH=50;//not tested       //~v@@@I~
    private static final int HIGHT_DPI_HIGH=38;                    //~v@@@I~
    private static final int HIGHT_DPI_MED=25;                     //~v@@@I~
    private static final int HIGHT_DPI_LOW=19;                     //~v@@@I~
    private static final int BASE_NEXUS7=800;                      //~9808I~
    private static final int BASE_HD    =720;                      //~vav6I~
    private static final int MULTIWINDOW_SHIFT=50;                 //~0113I~
                                                                   //~vac5I~
//  private static final int DPI_USE_SMALL_FONT=360;               //~vac5R~
//  private static final int DPI_USE_SMALL_FONT=430;       //top 10 max is 424 at 2019//~vac5I~//~vav7R~
    private static final int DPI_XHDPI_MAX =320;                   //~vav7I~
//  private static final int DPI_USE_SMALL_FONT=DPI_XHDPI_MAX;  //over xhdpi//~vav7I~//~vav8R~
    private static final int DPI_USE_SMALL_FONT=430;               //~vav8I~
    private static final int SMALL_DIP=320;                        //~vae0I~
//  private static final int RATE_LONGDEVICE=2;                    //~vaegI~//~vat9R~
//  private static final float RATE_LONGDEVICE=1.8F;                //~vat9I~//~vav1R~
    private static final float RATE_LONGDEVICE=(float)(16.0/9.0-0.001);      //1.776777//~vav1I~
    private static final String CN="UView:";                       //~vay1I~
//    private static Stack<View> stackSnackbarLayout=new Stack<View>();//~v@@@R~
    private static boolean swRequestedExternalWrite,swRequestedExternalRead;//~1ak2I~
    public  static final int FOLDABLE_PORTRAIT =1;    //up-down open//~vay6R~
    public  static final int FOLDABLE_LANDSCAPE=2;    //left-right open//~vay6R~
    private static final double RATE_TALL_ASPECT=16.0/9.0;   //1.77//~5127R~//~vay6I~
//*************************                                        //~v@@@I~
	public UView()                                                 //~v@@@I~
    {                                                              //~v@@@I~
    }                                                              //~v@@@I~
//*************************                                        //~@@@@I~
	public static void fixOrientation(boolean Pfix)                      //~@@@@I~//~v@@@R~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("UView:fixOrientation Pfix="+Pfix);//~va40I~
        int ori2=ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;      //~@@@@I~
    	if (Pfix)                                                  //~@@@@I~
        {                                                          //~@@@@I~
            int ori=AG.resource.getConfiguration().orientation;    //~@@@@I~
//            if (ori==Configuration.ORIENTATION_LANDSCAPE)          //~1A54I~//~v@@@R~
//                ori2=ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;//~1A54I~//~v@@@R~
//            else                                                   //~1A54I~//~v@@@R~
//                ori2=ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;//~1A54I~//~v@@@R~
            if (ori==Configuration.ORIENTATION_LANDSCAPE || ori==ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)//~v@@@I~
				ori2=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;    //~v@@@I~
            else                                                   //~v@@@I~
                ori2=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;     //~v@@@I~
        }                                                          //~@@@@I~
        AG.activity.setRequestedOrientation(ori2);                 //~@@@@I~
    }                                                              //~@@@@I~
//*************************                                        //~1A6hI~
	public static void fixOrientation(Activity Pactivity,boolean Pfix)    //~1A6hI~//~v@@@R~
    {                                                              //~1A6hI~
        int ori2=ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;      //~1A6hI~
    	if (Pfix)                                                  //~1A6hI~
        {                                                          //~1A6hI~
            int ori=AG.resource.getConfiguration().orientation;    //~1A6hI~
            if (ori==Configuration.ORIENTATION_LANDSCAPE)          //~1A6hI~
                ori2=ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;//~1A6hI~
            else                                                   //~1A6hI~
                ori2=ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;//~1A6hI~
        }                                                          //~1A6hI~
    	if (Dump.Y) Dump.println("UView:fixOrientation Pfix="+Pfix+",req="+ori2);//~vay0I~//~vaytR~
        Pactivity.setRequestedOrientation(ori2);                   //~1A6hI~
    }                                                              //~1A6hI~
//*************************                                        //~vay1I~
	public static void requestOrientation(Activity Pactivity,int Pori)//~vay1I~
    {                                                              //~vay1I~
    	if (Dump.Y) Dump.println(CN+"requestOrientation ori="+Pori);//~vay1I~
        Pactivity.setRequestedOrientation(Pori);                   //~vay1I~
    }                                                              //~vay1I~
//*************************                                        //~1122M~
	public static void getScreenSize()                                    //~1122M~//~v@@@R~
    {                                                              //~1122M~
        if (Dump.Y) Dump.println("UView:getScreenSize osVersion="+Build.VERSION.SDK_INT);//~vataI~//~vaytR~
//  	Display display=((WindowManager)(AG.context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();//~1122M~//~1aj0R~
    	Display display=getDefaultDisplay();                       //~1aj0I~
        AG.dip2pix=AG.resource.getDisplayMetrics().density; //referred in getScrrenRealSize//~vaysI~
        getScreenRealSize(display);                                //~vaysI~
        Point p=new Point();                                       //~1A6pI~
        if (Build.VERSION.SDK_INT>=31)                               //~vam6I~
          getDisplaySize(display,p);                                 //~vam6I~
        else                                                         //~vam6I~
        if (Build.VERSION.SDK_INT>=30)   //android30(R)              //~1aj0I~
          getDisplaySize30(p);                                       //~1aj0R~
        else                                                         //~1aj0R~
        getDisplaySize(display,p);                                         //~1A6pR~
        AG.scrWidth=p.x;	//by pixel                             //~1A6pI~
        AG.scrHeight=p.y;   //                                     //~1A6pI~
        if (Dump.Y) Dump.println("UView:getScreenSize AG.scrWidth="+p.x+",AG.scrHeight="+p.y);//~1506R~//~@@@@R~//~1A6pR~//~v@@@R~//~vay6R~//~vaytR~
//      AG.dip2pix=AG.resource.getDisplayMetrics().density;        //~1428I~//~vaysR~
//      AG.sp2pix=AG.resource.getDisplayMetrics().scaledDensity;   //~@@@@I~//~vay0R~
        AG.scrDencity=AG.resource.getDisplayMetrics().densityDpi;  //~vac5I~
        AG.portrait=(AG.scrWidth<AG.scrHeight);                    //~1223R~
        if (Dump.Y) Dump.println("UView:getScreenSize dp2pix="+AG.dip2pix+",AG.portrait="+AG.portrait+",AG.scrWidth="+AG.scrWidth+",AG.scrHeight="+AG.scrHeight);//~vayqI~//~vaysR~
        getTitleBarHeight();                                       //~1413M~
//      getScreenRealSize(display);                                //~v@@@I~//~vaysR~
        AG.scrNavigationbarRightWidth=0;                           //~9807I~
        if (!AG.portrait)                                          //~9807I~
        {                                                          //~vaeeI~
            if (AG.scrWidthReal>AG.scrWidth)    //navigationBar on the right//~9807I~
            {                                                      //~vaefI~
                AG.scrNavigationbarRightWidth=AG.scrWidthReal-AG.scrWidth;    //navigationBar on the right//~9807I~
            }                                                      //~vaefI~
//      	AG.swLongDevice=AG.scrWidth>AG.scrHeight*RATE_LONGDEVICE;//~vaegI~//~vat9R~
        	AG.swLongDevice=AG.scrWidth>(int)(AG.scrHeight*RATE_LONGDEVICE);//~vat9I~
        }                                                          //~vaeeI~
        else                                                       //~vaeeI~
        {                                                          //~vaeeI~
            if (AG.scrHeightReal>AG.scrHeight)    //navigationBar on the right//~vaeeI~
                AG.scrNavigationbarBottomHeight=AG.scrHeightReal-AG.scrHeight;    //navigationBar on the bottom//~vaeeI~
//      	AG.swLongDevice=AG.scrHeight>AG.scrWidth*RATE_LONGDEVICE;//~vaegI~//~vat9R~
        	AG.swLongDevice=AG.scrHeight>(int)(AG.scrWidth*RATE_LONGDEVICE);//~vat9I~
        }                                                          //~vaeeI~
//  	if (Build.VERSION.SDK_INT>=30)                             //~vayKR~//~vayMR~
    	if (AG.osVersion>=APIVER_EDGEMODE)                         //~vayMI~
		    getInsetMargin();  //for edgeMode                      //~vayKR~
        if (Dump.Y) Dump.println("UView:getScreenSize portrait="+AG.portrait+",swLongDevice="+AG.swLongDevice+",scrNavigationbarRightWidth="+AG.scrNavigationbarRightWidth+",scrNavigationBarBottomHeight="+AG.scrNavigationbarBottomHeight);//~1ak2I~//~1aj0R~//~vaeeR~//~vaefR~//~vaegR~
        if (Dump.Y) Dump.println("UView:getScreenSize scrNavigationbarBottomHeightA11="+AG.scrNavigationbarBottomHeightA11);//~vaytI~
        if (Dump.Y) Dump.println("UView:AG.scrWidth="+AG.scrWidth+",AG.scrHeight="+AG.scrHeight);//~vayMI~
    }                                                              //~1122M~
    //*******************************************************      //~1aj0R~
    @TargetApi(Build.VERSION_CODES.R)   //>=30                     //~1aj0R~
	public static void getDisplaySize30(Point Ppoint)              //~1aj0R~
    {                                                              //~1aj0R~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30");        //~1aj0R~
        WindowMetrics wm=AG.activity.getWindowManager().getCurrentWindowMetrics();//~1aj0R~
	    int ww0=wm.getBounds().width();                             //~1aj0R~
	    int hh0=wm.getBounds().height();                              //~1aj0I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 windowMetrics ww="+ww0+",hh="+hh0);//~1aj0I~
//      Point ptDecor=new Point();                                 //~vaegR~
//      getDecorViewSize(ptDecor);                                 //~vaegR~
        Rect rectDecor=getDecorViewRect();                         //~vaegI~
        Insets insetnavi=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());//TODO test//~vaefI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 inset ignorevisibility navigationBars="+insetnavi);//~vaefI~//~vay6R~
        Insets insetstatus=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.statusBars());//TODO test//~vaefI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 inset ignoreVisibility statusbars="+insetstatus.toString());//~vaefI~//~vay6R~
        Insets insetnaviv=wm.getWindowInsets().getInsets(WindowInsets.Type.navigationBars());//TODO test//~vaefI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 inset navigationBaes="+Utils.toString(insetnaviv));//~vaefI~//~vay6R~
        Insets insetstatusv=wm.getWindowInsets().getInsets(WindowInsets.Type.statusBars());//TODO test//~vaefI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 inset statusBars="+Utils.toString(insetstatusv));//~vaefI~//~vay6R~
        Insets insetsys=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());//TODO test//~1aj0R~//~vaefR~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 inset IgnoreVisibility systemBars="+insetsys.toString());//~1aj0R~//~vaefR~//~vay6R~
     //   Insets insetov=wm.getWindowInsets().getInsets(WindowInsets.Type.systemOverlays());//~vay6I~
	 //   if (Dump.Y) Dump.println("UView:getDisplaySize30 inset systemOverlays="+Utils.toString(insetov));//~vay6I~
        Insets insetco=wm.getWindowInsets().getInsets(WindowInsets.Type.displayCutout());//~vay6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 inset displayCutouts="+Utils.toString(insetco));//~vay6I~
        Insets insetms=wm.getWindowInsets().getInsets(WindowInsets.Type.mandatorySystemGestures());//~vay6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 inset mandatorySystemGestures="+Utils.toString(insetms));//~vay6I~
        Insets insetcv=wm.getWindowInsets().getInsets(WindowInsets.Type.captionBar());//~vay6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 inset captionBar="+Utils.toString(insetcv));//~vay6I~
                                                                   //~vay6I~
        Insets inset=wm.getWindowInsets().getInsets(WindowInsets.Type.systemGestures());//~vaefR~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 inset systemGesture="+Utils.toString(inset));//~vaefR~
                                                                   //~vaefI~
//      int ww=wm.getBounds().width()-inset.left-inset.right;      //~1aj0R~//~vaefR~
//      int hh=wm.getBounds().height()-inset.top-inset.bottom;     //~1aj0R~//~vaefR~
        int ww,hh;                                                 //~vaefI~
        AG.swNavigationbarGestureMode=inset.left!=0 && inset.right !=0 && inset.top!=0 && inset.bottom!=0;//~vaefM~
        ww=ww0-inset.left-inset.right;                             //~vaefI~
      if (true)                                                    //~vaymI~
      {                                                            //~vaysI~
//      hh=rectDecor.bottom;  //API30 exclude tile,status,nabigation//~vaymI~//~vaysR~
        if (hh0>ww0)	//portrait                                 //~vaysI~
        {                                                          //~vaysI~
//      	hh=Math.max(rectDecor.right,rectDecor.bottom);         //~vaysR~
        	hh=Math.max(rectDecor.right-rectDecor.left,rectDecor.bottom-rectDecor.top);//~vaysI~
        	ww=ww0;                                                //~vaysI~
        }                                                          //~vaysI~
        else            //landscape                                //~vaysI~
        {                                                          //~vaysI~
//        	hh=Math.min(4rectDecor.right,rectDecor.bottom);         //~vaysR~//~vaytR~
        	hh=Math.min(rectDecor.right-rectDecor.left,rectDecor.bottom-rectDecor.top);//~vaysI~
        	ww=ww0;                                                //~vaysI~
        }                                                          //~vaysI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 window=("+ww0+","+hh0+"),by rectDecor=("+ww+","+hh+")");//~vaysI~
      }                                                            //~vaysI~
      else                                                         //~vaymI~
      {                                                            //~vaysI~
        hh=hh0-inset.bottom;  //fullscreen(no title) mode,bottom is 3button/gesture navigationbar//~vaefR~
        if (ww0>hh0)	//landscape                                //~vaefI~
        {                                                          //~vaefI~
//          ww=ww0-inset.left-inset.right;                         //~vaefI~//~vaegR~
//          if (inset.left==0 || inset.right==0)                   //~vaefI~//~vaegR~
//              ww-=inset.top;  //TODO why, but if not rigth button override by 3 buttton navigationbar//~vaefI~//~vaegR~
//          if (AG.swNavigationbarGestureMode)                     //~vaefR~//~vaegR~
//          {                                                      //~vaegR~
            	hh=hh0;	//hide navigationbar at MainActivity       //~vaefR~
                ww=ww0; //fill hidden navigationbar, but right buttons has to be shift to left//~vaegR~
//          }                                                      //~vaegR~
        }                                                          //~vaefI~
        else                                                       //~vaefI~
	        ww=ww0;                                                //~vaefI~
      }                                                            //~vaysI~
        AG.scrNavigationbarBottomHeightA11=inset.bottom;           //~vaefI~
        int marginLR;                                              //~vaegI~
        if (AG.swNavigationbarGestureMode)                         //~vaegI~
        {                                                          //~vaegI~
//	    	AG.scrNavigationbarLeftWidthA11=inset.left;            //~vaegR~
//      	AG.scrNavigationbarRightWidthA11=inset.right;          //~vaegR~
            int left=rectDecor.left;                               //~vaegI~
            int right=ww0-rectDecor.right;                         //~vaegI~
        	marginLR=Math.max(left,right);                         //~vaegI~
		    if (Dump.Y) Dump.println("UView:getDisplaySize30 gesture mode marginLR="+marginLR);//~vaegI~
        }                                                          //~vaegI~
        else  //3button mode                                       //~vaegI~
        {                                                          //~vaegI~
//      	marginLR=ww0-ptDecor.x;                                //~vaegR~
        	marginLR=ww0-(rectDecor.right-rectDecor.left);         //~vaegI~
			if (Dump.Y) Dump.println("UView:getDisplaySize30 3 button mode marginLR="+marginLR);//~vaegI~//~vataR~
        }                                                          //~vaegI~//~vataR~
        AG.scrNavigationbarRightWidthA11=marginLR;              //~vaefR~//~vaegI~
//        if (ww0>hh0)    //landscape                              //~vaegR~
//            if (!AG.swNavigationbarGestureMode)                  //~vaegR~
//            {                                                    //~vaegR~
//                AG.scrNavigationbarRightWidthA11+=inset.top;    //?? but required//~vaegR~
//            }                                                    //~vaegR~
        Ppoint.x=ww; Ppoint.y=hh;                                  //~1aj0R~
        AG.scrStatusBarHeight=inset.top;                           //~1aj0I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 navigationbar bottomHA11="+AG.scrNavigationbarBottomHeightA11+",leftWA11="+AG.scrNavigationbarLeftWidthA11+",rightWidthA11="+AG.scrNavigationbarRightWidthA11+",swgesturemode="+AG.swNavigationbarGestureMode);//~vaefR~//~vaegR~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 point="+Ppoint.toString()+",statusBarHeight="+AG.scrStatusBarHeight);//~vaefI~
    }                                                              //~1ak2I~
    //*******************************************************      //~vam6M~
    @TargetApi(31)   //>=31                                        //~vam6M~
    public static void getDisplaySize31(Display Pdisplay,Point Ppoint)//~vam6M~
    {                                                              //~vam6M~
        WindowMetrics metrics=getRealMetrics_from31(Pdisplay);     //~vam6M~
        Insets insetGesture=metrics.getWindowInsets().getInsets(WindowInsets.Type.systemGestures());//~vam6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 insetGesture="+insetGesture);//~vam6I~
        AG.swNavigationbarGestureMode=insetGesture.left!=0 && insetGesture.right !=0 && insetGesture.top!=0 && insetGesture.bottom!=0;//~vam6I~
                                                                   //~vam6I~
		Rect bounds=metrics.getBounds();                           //~vam6M~
	    int ww0=bounds.width();                                    //~vam6M~
	    int hh0=bounds.height();                                   //~vam6M~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 bounds="+bounds);//~vam6I~
        WindowInsets windowInsets=metrics.getWindowInsets();       //~vam6M~
        Insets insetnavi=windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());//TODO test//~vay6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 inset ignorevisibility navigationBars="+insetnavi);//~vay6I~
        Insets insetstatus=windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.statusBars());//TODO test//~vay6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 inset ignoreVisibility statusbars="+insetstatus.toString());//~vay6I~
        Insets insetnaviv=windowInsets.getInsets(WindowInsets.Type.navigationBars());//TODO test//~vay6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 inset navigationBaes="+Utils.toString(insetnaviv));//~vay6I~
        Insets insetstatusv=windowInsets.getInsets(WindowInsets.Type.statusBars());//TODO test//~vay6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 inset statusBars="+Utils.toString(insetstatusv));//~vay6I~
        Insets insetsys=windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());//TODO test//~vay6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 inset IgnoreVisibility systemBars="+insetsys.toString());//~vay6I~
        Insets insetco=windowInsets.getInsets(WindowInsets.Type.displayCutout());//~vay6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 inset displayCutouts="+Utils.toString(insetco));//~vay6I~
        Insets insetms=windowInsets.getInsets(WindowInsets.Type.mandatorySystemGestures());//~vay6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 inset mandatorySystemGestures="+Utils.toString(insetms));//~vay6I~
        Insets insetcv=windowInsets.getInsets(WindowInsets.Type.captionBar());//~vay6I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 inset captionBar="+Utils.toString(insetcv));//~vay6I~
                                                                   //~vay6I~
        Insets inset=windowInsets.getInsetsIgnoringVisibility      //~vam6M~
						(WindowInsets.Type.navigationBars()|WindowInsets.Type.displayCutout());//~vam6M~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 inset="+inset);//~vataI~
        int insetWW=inset.right+inset.left;                        //~vam6M~
        int insetHH=inset.top+inset.bottom;                        //~vam6M~
        Rect rectDecor=getDecorViewRect();                         //~vam6M~//~vataM~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 rectDecor="+rectDecor.toString());//~vataI~//~vay6R~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 insetWW="+insetWW+",insetHH="+insetHH+",insets="+inset);//~vam6M~
                                                                   //~vam6M~
        int ww=ww0-insetWW;                                        //~vam6I~
        int hh=hh0-insetHH;                                        //~vam6I~
      if (true)                                                    //~vaymI~
      {                                                            //~vaysI~
//      hh=rectDecor.bottom;  //API31 exclude tile,status,nabigation//~vaymI~//~vaysR~
        if (hh0>ww0)	//portrait                                 //~vaysI~
        {                                                          //~vaysI~
//        	hh=Math.max(rectDecor.right,rectDecor.bottom);         //~vaysR~
        	hh=Math.max(rectDecor.right-rectDecor.left,rectDecor.bottom-rectDecor.top);//~vaysI~
        	ww=ww0;                                                //~vaysI~
        }                                                          //~vaysI~
        else            //landscape                                //~vaysI~
        {                                                          //~vaysI~
//        	hh=Math.min(rectDecor.right,rectDecor.bottom);         //~vaysR~
        	hh=Math.min(rectDecor.right-rectDecor.left,rectDecor.bottom-rectDecor.top);//~vaysI~
        	ww=ww0;                                                //~vaysI~
        }                                                          //~vaysI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 window=("+ww0+","+hh0+"),by rectDecor=("+ww+","+hh+")");//~vaysR~
      }                                                            //~vaysI~
      else                                                         //~vaysI~
      {                                                            //~vaysI~
        if (ww0>hh0)	//landscape                                //~vam6I~
        {                                                          //~vam6I~
            hh=hh0;	//hide navigationbar at MainActivity           //~vam6I~
            ww=ww0; //fill hidden navigationbar, but right buttons has to be shift to left//~vam6I~
        }                                                          //~vam6I~
        else                                                       //~vam6I~
	        ww=ww0;                                                //~vam6I~
      }                                                            //~vaysI~
        AG.scrNavigationbarBottomHeightA11=inset.bottom;           //~vam6M~
        int marginLR;                                              //~vam6M~
//      if (AG.swNavigationbarGestureMode)                         //~vam6R~
//      {                                                          //~vam6R~
//          int left=rectDecor.left;	//landscape effect delayed to Decorview//~vam6R~
//          int right=ww0-rectDecor.right;                         //~vam6R~
//      	marginLR=Math.max(left,right);                         //~vam6R~
//  	    if (Dump.Y) Dump.println("UView:getDisplaySize31 gesture mode marginLR="+marginLR);//~vam6R~
//      }                                                          //~vam6R~
//      else  //3button mode                                       //~vam6R~
//      {                                                          //~vam6R~
//      	marginLR=ww0-(rectDecor.right-rectDecor.left);	//landscape effect delayed to Decorview//~vam6R~
//  	    if (Dump.Y) Dump.println("UView:getDisplaySize31 3 button mode marginLR="+marginLR);//~vam6R~
//      }                                                          //~vam6R~
//      marginLR=0; //hide navigationBar on landscape, no navigationbar on portrait//~vam6I~//~vateR~
        int left=inset.left;                                       //~vateI~
        int right=inset.right;                                     //~vateI~
        marginLR=Math.max(left,right);                             //~vateI~
		if (Dump.Y) Dump.println("UView:getDisplaySize31 swPortrait="+AG.portrait+",marginLR="+marginLR+",left="+left+",right="+right);//~vateI~//~vay6R~
        AG.scrNavigationbarRightWidthA11=marginLR;                 //~vam6M~
        AG.scrStatusBarHeight=inset.top;                           //~vam6M~
                                                                   //~vam6M~
        Ppoint.x=ww;                                               //~vam6R~
        Ppoint.y=hh;                                               //~vam6R~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 navigationbar bottomHA11="+AG.scrNavigationbarBottomHeightA11+",leftWA11="+AG.scrNavigationbarLeftWidthA11+",rightWidthA11="+AG.scrNavigationbarRightWidthA11+",swgesturemode="+AG.swNavigationbarGestureMode);//~vam6M~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 point="+Ppoint.toString()+",statusBarHeight="+AG.scrStatusBarHeight);//~vam6M~
	    if (Dump.Y) Dump.println("UView:getDisplaySize31 point="+Ppoint.toString()+",bounds="+bounds+",insets="+inset);//~vam6M~
    }                                                              //~vam6M~
    //*******************************************************      //~vataI~
    @TargetApi(33)   //>=33 Android13           //~vataI~
	public static void getDisplaySize33(Display Pdisplay,Point Ppoint)              //~vataI~
    {                                                              //~vataI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33");        //~vataI~
        WindowMetrics wm=AG.activity.getWindowManager().getCurrentWindowMetrics();//~vataI~
	    int ww0=wm.getBounds().width();                            //~vataI~
	    int hh0=wm.getBounds().height();                           //~vataI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 windowMetrics ww="+ww0+",hh="+hh0);//~vataI~
        Rect rectDecor=getDecorViewRect();                         //~vataI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 rectDecor="+rectDecor.toString());//~vataI~
        Insets insetco=wm.getWindowInsets().getInsets(WindowInsets.Type.displayCutout());//~vaysI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 inset displayCutouts="+Utils.toString(insetco));//~vaysI~
        Insets insetnavi=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());//TODO test//~vataI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 insetnavi="+insetnavi.toString());//~vataI~
        Insets insetstatus=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.statusBars());//TODO test//~vataI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 insetstatus="+insetstatus.toString());//~vataI~
        Insets insetnaviv=wm.getWindowInsets().getInsets(WindowInsets.Type.navigationBars());//TODO test//~vataI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 insetnaviv="+Utils.toString(insetnaviv));//~vataI~
        Insets insetstatusv=wm.getWindowInsets().getInsets(WindowInsets.Type.statusBars());//TODO test//~vataI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 insetstatus visible="+Utils.toString(insetstatusv));//~vataI~
        Insets insetsys=wm.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());//TODO test//~vataI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 IgnoreVisibility inset SystemBars="+insetsys.toString());//~vataI~//~vayHR~
        Insets insetsysv=wm.getWindowInsets().getInsets(WindowInsets.Type.systemBars());//TODO test//~vayHI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 visble inset SystemBars="+insetsysv.toString());//~vayHR~
//deprecated at api30                                              //~vayKR~
//        int rSys=wm.getWindowInsets().getSystemWindowInsetRight();//~vayKI~
//        int lSys=wm.getWindowInsets().getSystemWindowInsetLeft();  //~vayHI~//~vayKR~
//        int tSys=wm.getWindowInsets().getSystemWindowInsetTop();   //~vayHI~//~vayKR~
//        int bSys=wm.getWindowInsets().getSystemWindowInsetBottom();//~vayHI~//~vayKR~
//        if (Dump.Y) Dump.println("UView:getDisplaySize33 inset SystemBars l="+lSys+",t="+tSys+",r="+rSys+",b="+bSys);//~vayHI~//~vayKR~
                                                                   //~vataI~
        Insets inset=wm.getWindowInsets().getInsets(WindowInsets.Type.systemGestures());//~vataI~
        AG.swNavigationbarGestureMode=inset.left!=0 && inset.right !=0 && inset.top!=0 && inset.bottom!=0;//~vataI~//~vayNM~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 inset systemGesture="+Utils.toString(inset));//~vataI~
        inset=insetsys;                                            //~vayMR~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 inset="+Utils.toString(inset));//~vayMI~
        int ww,hh;                                                 //~vataI~
                                                                   //~vayKI~
        ww=ww0-inset.left-inset.right;                             //~vataI~
      if (isEdgeMode())	//api>=35 EdgetoEdge Mode, show navigationBar by bottom margin//~vaytI~
      {                                                            //~vaytI~
      	ww=ww0;                                                    //~vaytR~
      	hh=hh0;                                                    //~vaytI~
        if (!AG.swNavigationbarGestureMode && hh>ww)	           //~vaytR~
        {                                                          //~vaytI~
      		hh=hh0-inset.bottom;                                   //~vaytI~
		    if (Dump.Y) Dump.println("UView:getDisplaySize33 portrait 3button mode redule height "+hh0+"-->"+hh+",inset.bottom="+inset.bottom);//~vaytI~//~vayHR~
        }                                                          //~vaytI~
      }                                                            //~vaytI~
      else                                                         //~vaytI~
      if (true)                                                    //~vaymI~
      {                                                            //~vaysI~
//      hh=rectDecor.bottom;  //API33 exclude tile,status,nabigation//~vaymI~//~vaysR~
        if (hh0>ww0)	//portrait                                 //~vaysI~
        {                                                          //~vaysI~
//        	hh=Math.max(rectDecor.right,rectDecor.bottom);         //~vaysR~
		  if (false)//TEST                                         //~vaysI~
          {                                                        //~vaysI~
        	hh=1600;                                               //~vaysI~
		    if (Dump.Y) Dump.println("UView:getDisplaySize33 1600 hh="+hh+",hh0="+hh0);//~vaysI~
          }                                                        //~vaysI~
          else                                                     //~vaysI~
		  if (false) //TEST                                        //~vaysR~
          {                                                        //~vaysI~
        	hh=hh0-inset.top-inset.bottom;                         //~vaysI~
		    if (Dump.Y) Dump.println("UView:getDisplaySize33 by systemGesture hh="+hh+",hh0="+hh0);//~vaysI~
          }                                                        //~vaysI~
          else                                                     //~vaysI~
          {                                                        //~vaysI~
        	hh=Math.max(rectDecor.right-rectDecor.left,rectDecor.bottom-rectDecor.top);//~vaysI~
		    if (Dump.Y) Dump.println("UView:getDisplaySize33 by Decorview hh="+hh+",hh0="+hh0);//~vaysI~
          }                                                        //~vaysI~
        	ww=ww0;                                                //~vaysI~
        }                                                          //~vaysI~
        else            //landscape                                //~vaysI~
        {                                                          //~vaysI~
//        	hh=Math.min(rectDecor.right,rectDecor.bottom);         //~vaysR~
        	hh=Math.min(rectDecor.right-rectDecor.left,rectDecor.bottom-rectDecor.top);//~vaysI~
        	ww=ww0;                                                //~vaysI~
        }                                                          //~vaysI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 window=("+ww0+","+hh0+"),by rectDecor=("+ww+","+hh+")");//~vaysI~
      }                                                            //~vaysI~
      else                                                         //~vaymI~
      {                                                            //~vaysI~
        hh=hh0-inset.bottom;  //fullscreen(no title) mode,bottom is 3button/gesture navigationbar//~vataI~
        if (ww0>hh0)	//landscape                                //~vataI~
        {                                                          //~vataI~
            hh=hh0; //hide navigationbar at MainActivity           //~vataR~//~vayqR~
            ww=ww0; //fill hidden navigationbar, but right buttons has to be shift to left//~vataR~
        }                                                          //~vataI~
        else                                                       //~vataI~
	        ww=ww0;                                                //~vataI~
      }                                                            //~vaysI~
                                                                   //~vayKI~
                                                                   //~vayKI~
        AG.scrNavigationbarBottomHeightA11=inset.bottom;           //~vataI~
        int marginLR;                                              //~vataI~
//        if (AG.swNavigationbarGestureMode)                       //~vataR~
//        {                                                        //~vataR~
            int left=inset.left;                                   //~vataR~
            int right=inset.right;                                 //~vataR~
//      	marginLR=right;                                        //~vataI~
        	marginLR=Math.max(left,right);                         //~vataR~
		    if (Dump.Y) Dump.println("UView:getDisplaySize33 marginLR="+marginLR+",left="+left+",right="+right);//~vataR~//~vayqR~
//        }                                                        //~vataR~
//        else  //3button mode                                     //~vataR~
//        {                                                        //~vataR~
//            marginLR=ww0-(inset.right+inset.left);               //~vataR~
//            if (Dump.Y) Dump.println("UView:getDisplaySize33 3 button mode marginLR="+marginLR);//~vataR~
//        }                                                        //~vataR~
                                                                   //~vayKI~
        AG.scrNavigationbarRightWidthA11=marginLR;                 //~vataI~
        Ppoint.x=ww; Ppoint.y=hh;                                  //~vataI~
        AG.scrStatusBarHeight=inset.top;                           //~vataI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 navigationbar bottomHA11="+AG.scrNavigationbarBottomHeightA11+",leftWA11="+AG.scrNavigationbarLeftWidthA11+",rightWidthA11="+AG.scrNavigationbarRightWidthA11+",swgesturemode="+AG.swNavigationbarGestureMode);//~vataI~
	    if (Dump.Y) Dump.println("UView:getDisplaySize33 point="+Ppoint.toString()+",statusBarHeight="+AG.scrStatusBarHeight);//~vataI~
    }                                                              //~vataI~
    //*******************************************************      //~1aj0I~
	public static Display getDefaultDisplay()                      //~1aj0I~
    {                                                              //~1aj0I~
	    if (Dump.Y) Dump.println("UView:getDefaultDisplay");       //~1aj0I~
    	Display d;                                                 //~1aj0I~
		if (Build.VERSION.SDK_INT>=30)   //android30(R)            //~1aj0I~
			d=getDefaultDisplay30();                               //~1aj0I~
        else                                                       //~1aj0I~
			d=getDefaultDisplay29();                               //~1aj0I~
        return d;                                                  //~1aj0I~
    }                                                              //~1aj0I~
    //*******************************************************      //~vam6I~
	public static WindowManager getWindowManager()                 //~vam6I~
    {                                                              //~vam6I~
		WindowManager wm=(WindowManager)(AG.context.getSystemService(Context.WINDOW_SERVICE));//~vam6I~
	    if (Dump.Y) Dump.println("UView:getWindowManager mgr="+wm);//~vam6I~
        return wm;
    }                                                              //~vam6I~
    //*******************************************************      //~1aj0I~
    @SuppressWarnings("deprecation")                               //~1aj0I~
	public static Display getDefaultDisplay29()                    //~1aj0I~
    {                                                              //~1aj0I~
	    if (Dump.Y) Dump.println("UView:getDefaultDisplay29");     //~1aj0I~
//  	Display display=((WindowManager)(AG.context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();//~1aj0I~//~vam6R~
    	Display display=getWindowManager().getDefaultDisplay();    //~vam6I~
        return display;                                            //~1aj0I~
    }                                                              //~1aj0I~
    //*******************************************************      //~1aj0I~
    @TargetApi(Build.VERSION_CODES.R)   //>=30                     //~1aj0I~
	public static Display getDefaultDisplay30()                    //~1aj0I~
    {                                                              //~1aj0I~
	    if (Dump.Y) Dump.println("UView:getDefaultDisplay30");     //~1aj0I~
		Display display=AG.context.getDisplay();                   //~1aj0I~
        return display;                                            //~1aj0I~
    }                                                              //~1aj0I~
    //*******************************************************      //~v@@@I~
//  public static void getScreenRealSize(Display Pdisplay)         //~v@@@I~//~vac5R~
    private static void getScreenRealSize(Display Pdisplay)        //~vac5I~
    {                                                              //~v@@@I~
		if (Build.VERSION.SDK_INT>=19)   //Navigationbar can be hidden//~v@@@R~
        {                                                          //~v@@@I~
	        Point p=new Point();                                   //~v@@@I~
//        	Pdisplay.getSize(p);                                   //~v@@@I~
//      	Pdisplay.getRealSize(p); //api17:4.2.2 JELLY bean mr1  //~v@@@R~//~vam6R~
        	getRealSize(Pdisplay,p); //api17:4.2.2 JELLY bean mr1  //~vam6I~
        	AG.scrWidthReal=p.x;                                   //~v@@@I~
        	AG.scrHeightReal=p.y;                                  //~v@@@I~
	        if (Dump.Y) Dump.println("UView:getScreenRealSize getRealSize() w="+AG.scrWidthReal+",h="+AG.scrHeightReal);//~v@@@I~//~va40R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
			DisplayMetrics m=new DisplayMetrics();                 //~v@@@R~
//  		Pdisplay.getMetrics(m);                                //~v@@@R~//~1aj0R~
    		displayGetMetrics(Pdisplay,m);                         //~1aj0I~
        	AG.scrWidthReal=m.widthPixels;                         //~v@@@R~
        	AG.scrHeightReal=m.heightPixels;                       //~v@@@R~
	        if (Dump.Y) Dump.println("UView:getScreenRealSize Displaymetrics w="+AG.scrWidthReal+",h="+AG.scrHeightReal);//~v@@@I~
        }                                                          //~v@@@I~
        int ww=Math.min(AG.scrWidthReal,AG.scrHeightReal);         //~9809R~
//      AG.swSmallDevice=ww<BASE_NEXUS7;                           //~9809I~//~vav6R~
        AG.swSmallDevice=ww<BASE_HD;                               //~vav6I~
//      AG.scaleSmallDevice=(double)ww/BASE_NEXUS7;               //~9809I~//~vav6R~
        AG.scaleSmallDevice=(double)ww/BASE_HD;                    //~vav6I~
        if (AG.dip2pix!=0)                                         //~vac5I~
        {                                                          //~vaedR~
        	AG.scrPortraitWidthDPI=(int)(ww/AG.dip2pix);                  //~vac5I~
            AG.swSmallDip=AG.scrPortraitWidthDPI<=SMALL_DIP;       //~vaedR~
        }                                                          //~vaedR~
        else                                                       //~vac5I~
        	AG.scrPortraitWidthDPI=ww;                             //~vac5I~
        AG.swSmallFont=AG.scrPortraitWidthDPI<=DPI_USE_SMALL_FONT;  //~vac5I~
        AG.scrRotation=Pdisplay.getRotation();                     //~vayyI~
	    if (Dump.Y) Dump.println("UView:getScreenRealSize AG.scrRotation="+AG.scrRotation+",swSmallDip="+AG.swSmallDip+",scaleSmallDevice="+AG.scaleSmallDevice+",swSmallDevice="+AG.swSmallDevice+",dip2pix="+AG.dip2pix+",swSmallFont="+AG.swSmallFont+",scrPortraitWidthDPI="+AG.scrPortraitWidthDPI);//~vac5I~//~1aj0R~//~vaedR~//~vayyR~
    }                                                              //~v@@@I~
    //*******************************************************      //~1aj0I~
    @SuppressWarnings("deprecation")                               //~1aj0I~
    private static void displayGetMetrics(Display Pdisplay,DisplayMetrics Pmetrics)//~1aj0I~
    {                                                              //~1aj0I~
	    if (Dump.Y) Dump.println("UView:displayGetMetrics");       //~1aj0I~
		Pdisplay.getMetrics(Pmetrics);                             //~1aj0I~
    }                                                              //~1aj0I~
    //*******************************************************      //~v@@@I~
    public static void getTitleBarHeight()                         //~1413R~
    {                                                              //~1413M~
        Rect rect=new Rect();                                      //~1413M~
        android.view.Window w=AG.activity.getWindow();                                 //~1413M~
        View v=w.getDecorView();                                   //~1413M~
        v.getWindowVisibleDisplayFrame(rect);                      //~1413M~
        if (Dump.Y) Dump.println("UView.getTitleBarHeight  DecorView rect="+rect.toString());//~1506R~//~v106R~//~v@@@R~//~vaefR~
        v=w.findViewById(android.view.Window.ID_ANDROID_CONTENT);               //~1413M~
        AG.titleBarTop=rect.top;                                   //~1413M~
        AG.titleBarBottom=v.getTop();                              //~1413M~
        if (Dump.Y) Dump.println("UView.getTitleBarHeight TitleBar top="+AG.titleBarTop+",bottom="+AG.titleBarBottom+",ID_ANDROID_CONTENT="+Integer.toHexString(android.view.Window.ID_ANDROID_CONTENT)+",v="+Utils.toString(v));//~vaefR~
        v=w.findViewById(android.R.id.content);                   //~vaefI~
        if (Dump.Y) Dump.println("UView.getTitleBarHeight TitleBar R.id.content="+Integer.toHexString(android.R.id.content)+",v="+Utils.toString(v));//~vaefI~
        if (Dump.Y) Dump.println("UView.getTitleBarHeight w="+(v!=null?v.getWidth():"null")+",h="+(v!=null ? v.getHeight() : "null"));//~vaefI~
    }                                                              //~1413M~
    //*******************************************************      //~1aj0I~
    public static void getDecorViewSize(Point Ppoint)              //~1aj0I~
    {                                                              //~1aj0I~
        Rect rect=new Rect();                                      //~1aj0I~
        android.view.Window w=AG.activity.getWindow();             //~1aj0I~
        View v=w.getDecorView();                                   //~1aj0I~
        v.getWindowVisibleDisplayFrame(rect);                      //~1aj0I~
        if (Dump.Y) Dump.println("UView.getViewSize  DecorView rect="+rect.toString());//~1aj0I~//~vaefR~
        Ppoint.x=rect.right-rect.left;                             //~1aj0I~
        Ppoint.y=rect.bottom-rect.top;                             //~1aj0I~
        if (Dump.Y) Dump.println("UView.getDecorViewSize rc="+Ppoint.toString());//~1aj0I~
    }                                                              //~1aj0I~
    //*******************************************************      //~vaegI~
    public static Rect getDecorViewRect()                          //~vaegI~
    {                                                              //~vaegI~
        Rect rect=new Rect();                                      //~vaegI~
        android.view.Window w=AG.activity.getWindow();             //~vaegI~
        View v=w.getDecorView();                                   //~vaegI~
        v.getWindowVisibleDisplayFrame(rect);                      //~vaegI~
        if (Dump.Y) Dump.println("UView.getDecorViewRect DecorView rect="+rect.toString());//~vaegI~//~vayqR~
        return rect;                                               //~vaegI~
    }                                                              //~vaegI~
    public static Point getTitleBarPosition()                      //~1413I~
    {                                                              //~1413I~
    	if (AG.titleBarBottom==0)                                  //~1413I~
        	getTitleBarHeight();                                   //~1413I~
        return new Point(AG.titleBarTop,AG.titleBarBottom);        //~1413I~
    }                                                              //~1413I~
    public static int getFramePosition()                         //~1413I~
    {                                                              //~1413I~
    	if (AG.titleBarBottom==0)                                  //~1413I~
        {                                                          //~@@@@I~
        	getTitleBarHeight();                                   //~1413I~
			if (AG.titleBarBottom==0) //not yet drawn once(in onCreate())//~@@@@I~
            {                                                      //~@@@@I~
            	return getDefaultTitlebarHeight();                 //~@@@@I~
            }                                                      //~@@@@I~
        }                                                          //~@@@@I~
        return AG.titleBarBottom;                                  //~1413I~
    }                                                              //~1413I~
//******************                                               //~@@@@I~
    public static int getDefaultTitlebarHeight()                   //~@@@@I~
    {                                                              //~@@@@I~
        int	h=HIGHT_DPI_TV;                                        //~@@@@R~
        int density=AG.resource.getDisplayMetrics().densityDpi;    //~@@@@I~
        if (Dump.Y) Dump.println("UView:getDefaultDencity density="+density);//~9717I~
        switch(density)                                            //~@@@@I~
        {                                                          //~@@@@I~
        case DisplayMetrics.DENSITY_MEDIUM:                        //~@@@@I~
        	h=HIGHT_DPI_MED;                                      //~@@@@I~
            break;                                                 //~@@@@I~
        case DisplayMetrics.DENSITY_LOW:                           //~@@@@I~
        	h=HIGHT_DPI_LOW;                                      //~@@@@I~
            break;                                                 //~@@@@I~
        case DisplayMetrics.DENSITY_HIGH:                          //~@@@@I~
	        h=HIGHT_DPI_HIGH;                                      //~@@@@I~
            break;                                                 //~@@@@I~
        case DisplayMetrics.DENSITY_XHIGH:                         //~@@@@I~
	        h=HIGHT_DPI_XHIGH;                                     //~@@@@I~
            break;                                                 //~@@@@I~
        }                                                          //~@@@@I~
        return h;                                           //~@@@@I~
    }                                                              //~@@@@I~
//*************************                                        //~1128I~
	static public View inflateView(int Presid)                     //~1128I~
    {                                                              //~1128I~
		View layoutview=inflateLayout(Presid);                     //~1128I~
        return layoutview;                                         //~1128I~
    }                                                              //~1128I~
//******************                                               //~1124I~//~1216M~
	static private View inflateLayout(int Presid)                   //~1122I~//~1216I~
    {                                                              //~1122I~//~1216M~
    	View layoutView=AG.inflater.inflate(Presid,null);          //~1122I~//~1216M~
        if (Dump.Y) Dump.println("UView:inflateLayout res="+Integer.toHexString(Presid)+",view="+layoutView.toString());//~@@@@R~//~v@@@R~
        return layoutView;                                         //~1122I~//~1216M~
    }                                                              //~1122I~//~1216M~
//**********************************                               //~v106I~
    public static void lockContention(String Ptext)                //~v106I~
    {                                                              //~v106I~
    	showToastLong(R.string.lockContention,Ptext);              //~v106I~
	}                                                              //~v106I~
//**********************************                               //~1B0gI~//~1Ad8I~
    public static void memoryShortage(String Ptext)                //~1B0gI~//~1Ad8I~
    {                                                              //~1B0gI~//~1Ad8I~
    	showToastLong(R.string.ErrOutOfMemory,Ptext);                //~1B0gI~//~1Ad8I~
	}                                                              //~1B0gI~//~1Ad8I~
//**********************************                               //~1A6pI~
//  public static void getDisplaySize(Display Pdisplay,Point Ppoint)//~1A6pI~//~vam6R~
    private static void getDisplaySize(Display Pdisplay,Point Ppoint)//~vam6I~
    {                                                              //~1A6pI~
        if (Dump.Y) Dump.println("UView:getDisplaySize osVersion="+Build.VERSION.SDK_INT);//~vataI~//~vaytR~
//      Pdisplay.getSize(Ppoint);                                    //~1A6pI~//~v@@@M~//~1aj0R~
		if (Build.VERSION.SDK_INT>=33)   //android-13(T)           //~vataI~
			getDisplaySize33(Pdisplay,Ppoint);                     //~vataI~
        else                                                       //~vataI~
		if (Build.VERSION.SDK_INT>=31)                             //~vam6I~
			getDisplaySize31(Pdisplay,Ppoint);                     //~vam6I~
        else                                                       //~vam6I~
		if (Build.VERSION.SDK_INT>=30)   //android30(R)            //~1aj0I~
			getDisplaySize30(Pdisplay,Ppoint);                     //~1aj0I~
        else                                                       //~1aj0I~
			getDisplaySize29(Pdisplay,Ppoint);                     //~1aj0I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize point="+Ppoint);//~vam6I~
    }                                                              //~1A6pI~
    //*******************************************************      //~1aj0I~
    //*size contains titlebar exclude statusbar and navigationbar  //~vaefI~
    //*******************************************************      //~vaefI~
    @SuppressWarnings("deprecation")                               //~1aj0I~
//  public static void getDisplaySize29(Display Pdisplay,Point Ppoint)//~1aj0I~//~vam6R~
    private static void getDisplaySize29(Display Pdisplay,Point Ppoint)//~vam6I~
    {                                                              //~1aj0I~
//        Rect rectDecor=getDecorViewRect(); //API29                 //~vaymI~//~vayZR~
//        WindowMetrics wm=AG.activity.getWindowManager().getCurrentWindowMetrics();//~vayZR~
//        WindowInsets wi=wm.getWindowInsets();                    //~vayZR~
//        Insets navi=wi.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());//~vayZR~
//        if (Dump.Y) Dump.println(CN+"getInsetMargin inset navigation="+navi);//~vayZR~
//        Insets stat=wi.getInsetsIgnoringVisibility(WindowInsets.Type.statusBars());//~vayZR~
//        if (Dump.Y) Dump.println(CN+"getInsetMargin inset status="+stat);//~vayZR~
//        Insets insetco=wm.getWindowInsets().getInsets(WindowInsets.Type.displayCutout());//~vayZR~
//        if (Dump.Y) Dump.println("UView:getInsetMargin displayCutouts="+Utils.toString(insetco));//~vayZR~
//        WindowInsetsCompat.Builder wicb=new WindowInsetsCompat.Builder();//~vayZR~
//        WindowInsetsCompat wic=wicb.build();                     //~vayZI~
                                                                   //~vayZI~
//        DisplayCutoutCompat co=wic.getDisplayCutout();           //~vayZR~
//        if (Dump.Y) Dump.println("UView:getInsetMargin displayCutoutsCompat="+co);//~vayZI~
//        AG.scrCutoutLeft=co.getSafeInsetLeft();                  //~vayZI~
//        AG.scrCutoutRight=co.getSafeInsetRight();                //~vayZI~
//        AG.scrCutoutTop=co.getSafeInsetTop();                    //~vayZI~
//        AG.scrCutoutBottom=co.getSafeInsetBottom();              //~vayZI~
                                                                   //~vayZI~
        Pdisplay.getSize(Ppoint);                                  //~1aj0I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize29 point="+Ppoint.toString());//~1aj0I~
    }                                                              //~1aj0I~
    //*******************************************************      //~1aj0I~
    //*not Used                                                    //~vaymI~
    //*******************************************************      //~vaymI~
    @SuppressWarnings("deprecation")                               //~vam6I~
    @TargetApi(Build.VERSION_CODES.R)   //>=30                     //~1aj0I~
    public static void getDisplaySize30(Display Pdisplay,Point Ppoint)//~1aj0I~
    {                                                              //~1aj0I~
		DisplayMetrics m=new DisplayMetrics();                     //~1aj0I~
    	Pdisplay.getRealMetrics(m);                                //~1aj0I~
        Ppoint.x=m.widthPixels;                                    //~1aj0I~
        Ppoint.y=m.heightPixels;                                   //~1aj0I~
	    if (Dump.Y) Dump.println("UView:getDisplaySize30 point="+Ppoint.toString());//~1aj0I~
    }                                                              //~1A6pI~//~1aj0I~
//**********************************                               //~v@@@I~
    public static void showSnackbar(View Pview,String Pmsg,int Pperiod)//~v@@@R~
    {                                                              //~v@@@I~
    	View v=Pview;                                              //~v@@@I~
        if (v==null)                                               //~v@@@I~
        	v=popSnackbarParent();                                 //~v@@@I~
        if (Pperiod==Snackbar.LENGTH_INDEFINITE)                     //~v@@@I~
        {                                                          //~v@@@I~
        	final Snackbar sb=Snackbar.make(v,Pmsg,Pperiod);       //~v@@@R~
			sb.setAction("Ok",new View.OnClickListener()            //~v@@@R~
								{                                  //~v@@@I~
                                	@Override                      //~v@@@I~
                                    public void onClick(final View v)//~v@@@I~
                                    {                              //~v@@@I~
                                    	sb.dismiss();              //~v@@@R~
                                    }                              //~v@@@I~
								});                                //~v@@@R~
			sb.show();                                             //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        	Snackbar.make(AG.parentSnackbar,Pmsg,Pperiod).setAction("Action", null).show();//~v@@@R~
    }                                                              //~v@@@I~
//**********************************************************       //~v@@@M~
    public static void showSnackbar(View Pview,int Presid,int Pperiod)//~v@@@I~
    {                                                              //~v@@@M~
        String msg=Utils.getStr(Presid);                           //~v@@@M~
	    showSnackbar(Pview,msg,Pperiod);                           //~v@@@I~
    }                                                              //~v@@@M~
//**********************************************************       //~v@@@I~
    public static View popSnackbarParent()                         //~v@@@I~
    {                                                              //~v@@@I~
    	View v;                                                    //~v@@@I~
    	try                                                        //~v@@@I~
        {                                                          //~v@@@I~
    		v=AG.stackSnackbarLayout.pop();                        //~v@@@R~
        }                                                          //~v@@@I~
        catch (EmptyStackException e)                              //~v@@@I~
        {                                                          //~v@@@I~
        	v=(View)AG.parentSnackbar;                             //~v@@@I~
        }                                                          //~v@@@I~
        return v;                                                  //~v@@@I~
    }                                                              //~v@@@I~
//**********************************************************       //~v@@@I~
    public static void pushSnackbarParent(View Pview)              //~v@@@I~
    {                                                              //~v@@@I~
    	AG.stackSnackbarLayout.push(Pview);                        //~v@@@R~
    }                                                              //~v@@@I~
//**********************************                               //~v@@@I~
    public static void showDF(DialogFragment Pdf, String tag)      //~v@@@R~
    {                                                              //~v@@@I~
//        FragmentTransaction ft = AG.activity.getFragmentManager().beginTransaction();//~v@@@R~
//        ft.add(Pdf,tag);                                         //~v@@@R~
//        ft.commitAllowingStateLoss();                            //~v@@@R~
		Pdf.show(AG.fragmentManager,tag);                          //~v@@@R~
    }                                                              //~v@@@I~
//**********************************************************       //~v@@@M~
    public static void showToast(int Presid)                       //~v@@@M~
    {                                                              //~v@@@M~
		showToastShort(Presid);                                 //~v@@@R~
    }                                                              //~v@@@M~
//**********************************************************       //~v@@@I~
    public static void showToastShort(int Presid)                  //~v@@@I~
    {                                                              //~v@@@I~
		showToast(Presid,"");                                 //~v@@@R~
    }                                                              //~v@@@I~
//**********************************************************       //~v@@@M~
    public static void showToastLong(int Presid)                   //~v@@@M~
    {                                                              //~v@@@M~
		showToastLong(Presid,"");                                  //~v@@@M~
    }                                                              //~v@@@M~
//**********************************************************       //~vaf0I~
    public static void showToastLongDirect(int Presid)             //~vaf0I~
    {                                                              //~vaf0I~
		showToastLongDirect(Presid,"");                            //~vaf0I~
    }                                                              //~vaf0I~
//**********************************************************       //~v@@@M~
    public static void showToast(int Presid,String Ptext)          //~v@@@M~
    {                                                              //~v@@@M~
        String msg=Utils.getStr(Presid)+Ptext;                     //~v@@@M~
    	if (Dump.Y) Dump.println("showToast msg="+msg);            //~v@@@M~
        if (AG.status==AG.STATUS_STOPFINISH)                       //~v@@@M~
            return;                                                //~v@@@M~
//  	EventBus.getDefault().post(new EventToast(msg,false));     //~va30R~
    	EventCB.post(new EventToast(msg,false));                   //~va30I~
    }                                                              //~v@@@M~
//**********************************************************       //~v@@@M~
    public static void showToastLong(int Presid,String Ptext)      //~v@@@M~
    {                                                              //~v@@@M~
        String msg=Utils.getStr(Presid)+Ptext;                     //~v@@@M~
    	if (Dump.Y) Dump.println("showToastLong msg="+msg);        //~v@@@M~
        if (AG.status==AG.STATUS_STOPFINISH)                       //~v@@@M~
            return;                                                //~v@@@M~
//  	EventBus.getDefault().post(new EventToast(msg,true));      //~va30R~
    	EventCB.post(new EventToast(msg,true));                    //~va30I~
    }                                                              //~v@@@M~
//**********************************************************       //~vaf0I~
    public static void showToastLongDirect(int Presid,String Ptext)//~vaf0I~
    {                                                              //~vaf0I~
        String msg=Utils.getStr(Presid)+Ptext;                     //~vaf0I~
    	if (Dump.Y) Dump.println("showToastLongDirect msg="+msg);  //~vaf0I~
        new EventToast(msg,true/*long*/).showToast();              //~vaf0I~
    }                                                              //~vaf0I~
//**********************************************************       //~vaf0I~
    public static void showToastLongDirect(String Ptext)           //~vaf0I~
    {                                                              //~vaf0I~
    	if (Dump.Y) Dump.println("showToastLongDirect text="+Ptext);//~vaf0I~
        new EventToast(Ptext,true/*long*/).showToast();            //~vaf0I~
    }                                                              //~vaf0I~
//**********************************************************       //~vaf0I~
    public static void showToastLongDirect(Context Pcontext,String Ptext)//~vaf0I~
    {                                                              //~vaf0I~
    	if (Dump.Y) Dump.println("showToastLongDirect text="+Ptext);//~vaf0I~
        new EventToast(Ptext,true/*long*/).showToast(Pcontext);    //~vaf0I~
    }                                                              //~vaf0I~
//**********************************************************       //~v@@@M~
    public static void showToast(String Ptext)                     //~v@@@M~
    {                                                              //~v@@@M~
		showToastShort(Ptext);                                     //~v@@@I~
    }                                                              //~v@@@M~
//**********************************************************       //~v@@@I~
    public static void showToastShort(String Ptext)                //~v@@@I~
    {                                                              //~v@@@I~
    	if (Dump.Y) Dump.println("showToast msg="+Ptext);          //~v@@@I~
        if (AG.status==AG.STATUS_STOPFINISH)                       //~v@@@I~
            return;                                                //~v@@@I~
//  	EventBus.getDefault().post(new EventToast(Ptext,false));   //~va30R~
    	EventCB.post(new EventToast(Ptext,false));                 //~va30I~
    }                                                              //~v@@@I~
//**********************************************************       //~v@@@M~
    public static void showToastLong(String Ptext)                 //~v@@@M~
    {                                                              //~v@@@M~
    	if (Dump.Y) Dump.println("showToastLong msg="+Ptext);      //~v@@@M~
        if (AG.status==AG.STATUS_STOPFINISH)                       //~v@@@M~
            return;                                                //~v@@@M~
//  	EventBus.getDefault().post(new EventToast(Ptext,true));    //~va30R~
    	EventCB.post(new EventToast(Ptext,true));                  //~va30I~
    }                                                              //~v@@@M~
//****************                                                 //~1416I~//~1Ad7R~//~v@@@I~
    public static View findViewById(View Playout,int Pid)          //~1416I~//~1Ad7R~//~v@@@I~
    {                                                              //~1416I~//~1Ad7R~//~v@@@I~
        View v=Playout.findViewById(Pid);                          //~1416I~//~1Ad7R~//~v@@@I~//~9416R~
        if (Dump.Y) Dump.println("UView.findViewById rc==null?="+(v==null?"true":"false")+",id="+Integer.toHexString(Pid));//~9416I~
        return v;                                                  //~9416I~
    }                                                              //~1416I~//~1Ad7R~//~v@@@I~
//****************                                                 //~v@@@I~
    public static void recycle(Bitmap Pbitmap)                     //~v@@@I~
    {                                                              //~v@@@I~
    	if (Pbitmap==null)      //in case bm[] point same bitmap   //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("UView.recycle bitmap bitmap=null");//~v@@@I~//~0216R~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("UView.recycle bitmap isRecycled="+Pbitmap.isRecycled());//~0216I~
        if (!Pbitmap.isRecycled())                                 //~v@@@I~
        {                                                          //~0216I~
        	if (Dump.Y) Dump.println("UView.recycle bitmap isRecycled=false byteCount="+Pbitmap.getByteCount()+",bitmap="+Pbitmap.toString());//~0216I~//~0217R~
	        Pbitmap.recycle();                                     //~v@@@I~
        }                                                          //~0216I~
    }                                                              //~v@@@I~
//****************                                                 //~v@@@I~
    public static void setWillNotDraw(View Pview,boolean PswNotDraw)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UView.setWillNotDraw swNotDraw="+PswNotDraw+",view="+Pview.toString());//~v@@@I~
        Pview.setWillNotDraw(false);	//enable onDraw() callback //~v@@@I~
    }                                                              //~v@@@I~
//****************                                                 //~v@@@I~
    public static void setAttachStateChangeListener(View Pview)    //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("UView.setAttachStateChangeListener view="+Pview.toString());//~v@@@I~
        View.OnAttachStateChangeListener l=                        //~v@@@I~
        	new View.OnAttachStateChangeListener()                 //~v@@@I~
            	{                                                  //~v@@@I~
                	@Override                                      //~v@@@I~
                    public void onViewAttachedToWindow(View Pview)             //~v@@@I~
                    {                                              //~v@@@I~
                    	if (Dump.Y) Dump.println("UView.onViewAttachedToWindow view="+Pview.toString());//~v@@@R~
                    }                                              //~v@@@I~
                	@Override                                      //~v@@@I~
                    public void onViewDetachedFromWindow(View Pview)           //~v@@@I~
                    {                                              //~v@@@I~
                    	if (Dump.Y) Dump.println("UView.onViewDetachedFromWindow view="+Pview.toString());//~v@@@R~
                    }                                              //~v@@@I~
                };                                                 //~v@@@I~
    	Pview.addOnAttachStateChangeListener(l);                   //~v@@@I~
    }                                                              //~v@@@I~
//****************                                                 //~v@@@I~
    public static Point getMeasuredSize(View Pview)                //~v@@@I~
    {                                                              //~v@@@I~
        int ww=Pview.getMeasuredWidth();                           //~v@@@I~
        int hh=Pview.getMeasuredHeight();                          //~v@@@I~
        if (Dump.Y) Dump.println("UView.getMeasuredSize ww="+ww+",hh="+hh+",view="+Pview.toString());//~v@@@I~
        return new Point(ww,hh);                                   //~v@@@I~
    }                                                              //~v@@@I~
//****************                                                 //~9928I~
    public static Point getMeasuredSize(View Pview,int Psize,int Pmode)//~9928I~
    {                                                              //~9928I~
        if (Dump.Y) Dump.println("UView.getMeasuredSize width mode Psize="+Psize+",mode="+Pmode);//~9928I~
        int msw= View.MeasureSpec.makeMeasureSpec(Psize,Pmode);    //~9928I~
        int msh= View.MeasureSpec.makeMeasureSpec(Psize,Pmode);    //~9928I~
        Pview.measure(msw,msh);                                    //~9928I~
        Point p=getMeasuredSize(Pview);                            //~9928I~
        return p;                                                  //~9928I~
    }                                                              //~9928I~
    //******************************************************************************//~9410I~
    //*!! call from onStart                                        //~9410I~
    //******************************************************************************//~9410I~
    public static void setDialogWidth(Dialog Pdlg, double Prate)   //~9410R~
    {                                                              //~9410I~
	    int ww;                                                    //~9410I~
	    ww=(int)(AG.scrWidth*Prate);                               //~9410R~
        int hh=ViewGroup.LayoutParams.WRAP_CONTENT;                //~9410I~
        if (Dump.Y) Dump.println("Uview.setDialogWidth:ww="+ww+",hh="+hh+",rate="+Prate+",scrWidth="+AG.scrWidth+",portrait="+AG.portrait);//~9410R~//~9925R~
        Pdlg.getWindow().setLayout(ww,hh);                         //~9410I~
    }                                                              //~9410I~
    //******************************************************************************//~9811I~
    public static void setDialogWidth(Dialog Pdlg, int Pww)        //~9810I~
    {                                                              //~9810I~
	    int ww=Math.min(AG.scrWidth,Pww);                              //~9810I~
        int hh=ViewGroup.LayoutParams.WRAP_CONTENT;                //~9810I~
        if (Dump.Y) Dump.println("Uview.setDialogWidth:Pww="+Pww+",ww="+ww+",scrWidth="+AG.scrWidth+",hh="+hh);//~9810R~//~9925R~
//        if (true) //TODO test                                    //~9925I~
//        {                                                        //~9925I~
//            WindowManager.LayoutParams lp=Pdlg.getWindow().getAttributes();//~9925I~
//            lp.width=ww;                                         //~9925I~
//            lp.height=ViewGroup.LayoutParams.WRAP_CONTENT;       //~9925I~
//            Pdlg.getWindow().setAttributes(lp);                  //~9925I~
//            if (Dump.Y) Dump.println("Uview.setDialogWidth setattribute");//~9925I~
//        }                                                        //~9925I~
//        else                                                     //~9925I~
        Pdlg.getWindow().setLayout(ww,hh);                         //~9810I~
    }                                                              //~9810I~
    //******************************************************************************//~9811I~
    public static void setDialogWidthMatchParent(Dialog Pdlg)      //~9811I~
    {                                                              //~9811I~
        if (Dump.Y) Dump.println("Uview.setDialogWidthMatchParent");//~9811I~//~9925R~
    	Pdlg.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);//~9811I~
    }                                                              //~9811I~
    //******************************************************************************//~9925I~
    public static void setDialogWidthMatchParentPortrait(Dialog Pdlg)//~9925I~
    {                                                              //~9925I~
        if (Dump.Y) Dump.println("Uview.setDialogWidthMatchParentPortrait swPortrait="+AG.portrait);//~9925I~
        if (AG.portrait)                                           //~9925I~
    		Pdlg.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);//~9925I~
        else                                                       //~9925I~
        {                                                          //~9925I~
        	int ww=Math.min(AG.scrWidthReal,AG.scrHeightReal);     //~9925I~
	        if (Dump.Y) Dump.println("Uview.setDialogWidthMatchParentPortrait ww="+ww+",realW="+AG.scrWidthReal+",realH="+AG.scrHeightReal);//~9925I~
//  		Pdlg.getWindow().setLayout(ww,LinearLayout.LayoutParams.WRAP_CONTENT);//~9925R~//~9927R~
    		Pdlg.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT); //TODO test//~9927I~
        }                                                          //~9925I~
    }                                                              //~9925I~
    //******************************************************************************//~9812I~
    public static void setDialogWidthWrapContent(Dialog Pdlg)      //~9812I~
    {                                                              //~9812I~
        if (Dump.Y) Dump.println("Uview.setDialogWidthWrapContent");//~9812I~
    	Pdlg.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);//~9812I~
    }                                                              //~9812I~
    //******************************************************************************//~9927I~
    public static int getDialogPaddingHorizontal(Dialog Pdlg)      //~9927I~
    {                                                              //~9927I~
        View decor=Pdlg.getWindow().getDecorView();                //~9927I~
        int p=decor.getPaddingRight();                             //~9927I~
        p+=decor.getPaddingLeft();                                 //~9927I~
        if (Dump.Y) Dump.println("Uview.getDialogPaddingHorizontal padding="+p);//~9927I~
        return p;
    }                                                              //~9927I~
//    //******************************************************************************//~9410I~//~9810R~
//    //*!! call from onStart                                        //~9410I~//~9810R~
//    //*Pww:max for portrait,min for landscape                    //~9810I~
//    //******************************************************************************//~9410I~//~9810R~
//    public static void setDialogWidth(Dialog Pdlg, double Prate,int Pww)//~9410I~//~9810R~
//    {                                                              //~9410I~//~9810R~
//        int WW=AG.scrWidth;                                        //~9410I~//~9810R~
//        int ww=(int)(WW*Prate);                                    //~9410I~//~9810R~
//        if (AG.portrait)                                           //~9410I~//~9810R~
//        {                                                          //~9410I~//~9810R~
//            if (Pww<WW)                                            //~9410I~//~9810R~
//                ww=Math.max(ww,Pww);                               //~9410I~//~9810R~
//        }                                                          //~9410I~//~9810R~
//        else                                                       //~9410I~//~9810R~
//        {                                                          //~9410I~//~9810R~
//            if (Pww<WW)                                            //~9410I~//~9810R~
//                ww=Math.min(ww,Pww);                               //~9410I~//~9810R~
//        }                                                          //~9410I~//~9810R~
//        int hh=ViewGroup.LayoutParams.WRAP_CONTENT;                //~9410I~//~9810R~
//        Pdlg.getWindow().setLayout(ww,hh);                         //~9410I~//~9810R~
//        if (Dump.Y) Dump.println("Uview.setDialogWidth:portrait="+AG.portrait+",scrWidth="+WW+",rate="+Prate+",Pww="+Pww+",setww="+ww);//~9410I~//~9810R~
//    }                                                              //~9410I~//~9810R~
    //******************************************************************************//~9930I~
    public static boolean isPermissionGrantedLocation()            //~9930I~
    {                                                              //~9930I~
        String type= Manifest.permission.ACCESS_FINE_LOCATION;      //~9930I~
        boolean rc=isPermissionGranted(type);                      //~9930I~
        if (Dump.Y) Dump.println("Uview.isPermissionGrantedLocation rc="+rc);//~9930I~
        return rc;                                                 //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9B09I~
    public static boolean isPermissionGrantedExternalStorage()     //~9B09I~
    {                                                              //~9B09I~
        String type= Manifest.permission.WRITE_EXTERNAL_STORAGE;   //~9B09I~
        boolean rc=isPermissionGranted(type);                      //~9B09I~
        if (Dump.Y) Dump.println("Uview.isPermissionGrantedExternalStorage rc="+rc);//~9B09I~
        return rc;                                                 //~9B09I~
    }                                                              //~9B09I~
    //******************************************************************************//~1ak2I~
    public static boolean isPermissionDeniedExternalStorage()      //~1ak2I~
    {                                                              //~1ak2I~
        String type= Manifest.permission.WRITE_EXTERNAL_STORAGE;   //~1ak2I~
        boolean rc=isPermissionDenied(type);                       //~1ak2I~
    	rc=swRequestedExternalWrite && !rc;		//once requested but denied//~1ak2I~
        if (Dump.Y) Dump.println("Uview.isPermissionDeniedExternalStorage rc="+rc+",swRequestedExterbalWrite="+swRequestedExternalWrite);//~1ak2R~
        return rc;                                                 //~1ak2I~
    }                                                              //~1ak2I~
    //******************************************************************************//~1ak2I~
    public static boolean isPermissionGrantedExternalStorageRead() //~1ak2I~
    {                                                              //~1ak2I~
        String type= Manifest.permission.READ_EXTERNAL_STORAGE;    //~1ak2I~
        boolean rc=isPermissionGranted(type);                      //~1ak2I~
//  	rc=swRequestedExternalRead && !rc;		//once requested but denied//~1ak2R~
        if (Dump.Y) Dump.println("Uview.isPermissionGrantedExternalStorageRead rc="+rc);//~1ak2I~
        return rc;                                                 //~1ak2I~
    }                                                              //~1ak2I~
    //******************************************************************************//~1ak2I~
    public static boolean isPermissionDeniedExternalStorageRead()  //~1ak2I~
    {                                                              //~1ak2I~
        String type= Manifest.permission.READ_EXTERNAL_STORAGE;    //~1ak2I~
        boolean rc=isPermissionDenied(type);                       //~1ak2I~
    	rc=swRequestedExternalRead && !rc;		//once requested but denied//~1ak2I~
        if (Dump.Y) Dump.println("Uview.isPermissionDeniedExternalStorageRead rc="+rc+",swRequestedExternalRead="+swRequestedExternalRead);//~1ak2R~
        return rc;                                                 //~1ak2I~
    }                                                              //~1ak2I~
    //******************************************************************************//~9930I~
    public static boolean isPermissionGranted(String Ptype)        //~9930I~
    {                                                              //~9930I~
	    if (Dump.Y) Dump.println("Uview.isPermissionGranted Build.VERSION.SDK_INIT="+Build.VERSION.SDK_INT);//~9A01I~
//      if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M) //M:Mashmallow=api23=Android6//~9A01R~
//      {                                                          //~9A01R~
//          if (Dump.Y) Dump.println("Uview.isPermissionGranted version < android6(api23): Build.VERSION.SDK_INIT="+Build.VERSION.SDK_INT);//~9A01R~
//      	return true;                                           //~9A01R~
//      }                                                          //~9A01R~
        //PackageManager.PERMISSION_GRANTED=0; PERMISSION_DENIED=-1//~vae0R~
        boolean rc= ContextCompat.checkSelfPermission(AG.activity,Ptype)== PackageManager.PERMISSION_GRANTED;//~vae0R~
        if (Dump.Y) Dump.println("Uview.isPermissionGranted type="+Ptype+",rc="+rc);//~9930I~
        if (!rc)                                                   //~1ak2I~
        {                                                          //~1ak2I~
            if (Dump.Y) Dump.println("UView.isPermissionGranted shouldShowRequestPermissionRationale="+ActivityCompat.shouldShowRequestPermissionRationale(AG.activity,Ptype));//~1ak2I~
        }                                                          //~1ak2I~
        return rc;                                                 //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9930I~
    public static boolean isPermissionGranted(int Presult)         //~9930I~
    {                                                              //~9930I~
        boolean rc=Presult==PackageManager.PERMISSION_GRANTED;       //~9930I~
        if (Dump.Y) Dump.println("Uview.isPermissionGranted Presult="+Presult+",rc="+rc);//~9930I~
        return rc;                                                 //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9930I~
    public static boolean isPermissionDeniedLocation()             //~9930I~
    {                                                              //~9930I~
        String type=Manifest.permission.ACCESS_FINE_LOCATION;      //~9930I~
        boolean rc=isPermissionDenied(type);                       //~9930I~
        if (Dump.Y) Dump.println("Uview.isPermissionDeniedLocation rc="+rc);//~9930I~
        return rc;                                                 //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9930I~
    public static boolean isPermissionDenied(String Ptype)         //~9930I~
    {                                                              //~9930I~
        boolean rc=ActivityCompat.shouldShowRequestPermissionRationale(AG.activity,Ptype);//~9930I~
        if (Dump.Y) Dump.println("Uview.isPermissionDenied by shouldShowRequestPermissionRationale type="+Ptype+",rc="+rc);//~9930I~//~1ak2R~
        return rc;                                                 //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9930I~
    public static void requestPermissionLocation(int PrequestID)   //~9930I~
    {                                                              //~9930I~
        if (Dump.Y) Dump.println("Uview.requestPermissionLocation requestid="+PrequestID);//~9930I~
        String type=Manifest.permission.ACCESS_FINE_LOCATION;      //~9930I~
	    requestPermission(type,PrequestID);                        //~9930I~
    }                                                              //~9930I~
    //******************************************************************************//~9B09I~
    //*Read and write                                              //~1ak2I~
    //******************************************************************************//~1ak2I~
    public static void requestPermissionExternalStorage(int PrequestID)//~9B09I~
    {                                                              //~9B09I~
        if (Dump.Y) Dump.println("Uview.requestPermissionExternalStorage requestid="+PrequestID);//~9B09I~//~1ak2R~
//      String type=Manifest.permission.WRITE_EXTERNAL_STORAGE;    //~9B09I~//~1ak2R~
//      String[] type={Manifest.permission.WRITE_EXTERNAL_STORAGE, //~1ak2R~
//                     Manifest.permission.READ_EXTERNAL_STORAGE}; //Required for Mediastore query//~1ak2R~
        String type=Manifest.permission.WRITE_EXTERNAL_STORAGE;    //WRITE means also READ//~1ak2I~
	    requestPermission(type,PrequestID);                        //~9B09I~
    	swRequestedExternalWrite=true;                             //~1ak2I~
    }                                                              //~9B09I~
    //******************************************************************************//~1ak2I~
    public static void requestPermissionExternalStorageRead(int PrequestID)//~1ak2I~
    {                                                              //~1ak2I~
        if (Dump.Y) Dump.println("Uview.requestPermissionExternalStorageRead requestid="+PrequestID);//~1ak2I~
        String type=Manifest.permission.READ_EXTERNAL_STORAGE;     //~1ak2I~
	    requestPermission(type,PrequestID);                        //~1ak2I~
    	swRequestedExternalRead=true;                              //~1ak2I~
    }                                                              //~1ak2I~
    //******************************************************************************//~9930I~
    public static void requestPermission(String Ptype,int PrequestID)//~9930I~
    {                                                              //~9930I~
        if (Dump.Y) Dump.println("Uview.requestPermission type="+Ptype+",requestID="+PrequestID);//~9930I~
        String[] types=new String[]{Ptype};                        //~9930I~
//      ActivityCompat.requestPermissions(AG.activity,types,PrequestID);//~9930I~//~vau2R~
        UPermission.requestPermissions(types,PrequestID);          //~vau2I~
    }                                                              //~9930I~
    //******************************************************************************//~1ak2I~
    public static void requestPermission(String[] Ptypes,int PrequestID)//~1ak2I~
    {                                                              //~1ak2I~
        if (Dump.Y) Dump.println("Uview.requestPermission types="+Utils.toString(Ptypes)+",requestID="+PrequestID);//~1ak2I~
//      ActivityCompat.requestPermissions(AG.activity,Ptypes,PrequestID);//~1ak2I~//~vau2R~
        UPermission.requestPermissions(Ptypes,PrequestID);         //~vau2I~
    }                                                              //~1ak2I~
//    //******************************************************************************//~9B25R~
//    public static void getBackgroundColor(Button Pbtn)           //~9B25R~
//    {                                                            //~9B25R~
//        if (Dump.Y) Dump.println("Uview.getBackgroundColor btn="+Pbtn.toString());//~9B25R~
//        Drawable d=Pbtn.getBackgrounbd();                        //~9B25R~
//        ColorDrawable c=(ColorDrawable)d;                        //~9B25R~
//        int color=c.getColor();                                  //~9B25R~
//        if (Dump.Y) Dump.println("Uview.getBackgroundColor color="+Integer.toHexString(color));//~9B25R~
//    }                                                            //~9B25R~
    //*******************************************************************//~0113I~
    //*call on onCreateView                                        //~0113I~
    //*******************************************************************//~0113I~
    public static void shiftDialog(Dialog PandroidDlg,int Pctr,int Pshift)//~0113I~
    {                                                              //~0113I~
		if (Dump.Y) Dump.println("UView.shiftDialog ctr="+Pctr+",shift="+Pshift+",dialog="+PandroidDlg.toString());//~0113I~
        if (Pctr!=0)                                               //~0113I~
        {                                                          //~0113I~
            WindowManager.LayoutParams lp=PandroidDlg.getWindow().getAttributes();//~0113I~
            lp.y=Pctr*(Pshift==0 ? MULTIWINDOW_SHIFT : Pshift);    //~0113I~
        }                                                          //~0113I~
    }                                                              //~0113I~
    //*******************************************************************//~0327I~
    public static String showParentPathWidth(View Pview)           //~0327I~
    {                                                              //~0327I~
        StringBuffer sb=new StringBuffer();                        //~0327I~
        for (View v=Pview;v!=null;)                                //~0327R~
        {                                                          //~0327I~
        	if (!(v instanceof View))                              //~0327I~
            	break;                                             //~0327I~
        	int id=v.getId();                                      //~0327I~
    		sb.append("id="+Integer.toHexString(v.getId())+",width="+v.getWidth()+"\t");//~0327R~
        	if (v.getParent() instanceof View)                     //~0327I~
            	v=(View)v.getParent();                             //~0327I~
            else                                                   //~0327I~
                break;                                             //~0327I~
        }                                                          //~0327I~
        String s=sb.toString();                                    //~0327I~
		if (Dump.Y) Dump.println("UView.showParentPathWidth rc="+s);//~0327I~
        return s;                                                  //~0327I~
    }                                                              //~0327I~
    //*******************************************************************//~vam6I~
    //*for APi>=19                                                 //~vam6I~
    //*******************************************************************//~vam6I~
    private static void getRealSize(Display Pdisplay,Point Ppoint) //~vam6I~
    {                                                              //~vam6I~
		if (Dump.Y) Dump.println("UView.getRealSize apiLevel="+Build.VERSION.SDK_INT);//~vam6I~
		if (Build.VERSION.SDK_INT>=31)   //Navigationbar can be hidden//~vam6I~
		    getRealSize_from31(Pdisplay,Ppoint);                   //~vam6I~
        else                                                       //~vam6I~
		    getRealSize_19To30(Pdisplay,Ppoint);                   //~vam6I~
		if (Dump.Y) Dump.println("UView.getRealSize exit point="+Ppoint);//~vam6I~
    }                                                              //~vam6I~
    //*******************************************************************//~vam6I~
    @SuppressWarnings("deprecation")                               //~vam6R~
    @TargetApi(19)                                                 //~vam6I~
    private static void getRealSize_19To30(Display Pdisplay,Point Ppoint)//~vam6I~
    {                                                              //~vam6I~
		if (Dump.Y) Dump.println("UView.getRealSize_upto30 display="+Pdisplay);//~vam6I~
		Pdisplay.getRealSize(Ppoint);                              //~vam6I~
		if (Dump.Y) Dump.println("UView.getRealSize_upto30 exit point="+Ppoint);//~vam6I~
    }                                                              //~vam6I~
    //*******************************************************************//~vam6I~
    @TargetApi(31)                                                 //~vam6I~
    private static void getRealSize_from31(Display Pdisplay,Point Ppoint)//~vam6I~
    {                                                              //~vam6I~
        WindowMetrics metrics=getRealMetrics_from31(Pdisplay);     //~vam6R~
        Rect rect=metrics.getBounds();                             //~vam6R~
		if (Dump.Y) Dump.println("UView.getRealSize_from31 display="+Pdisplay+",metrics="+metrics+",getBounds="+rect);//~vam6R~
        Ppoint.x=rect.right-rect.left;                              //~vam6R~
        Ppoint.y=rect.bottom-rect.top;                              //~vam6R~
		if (Dump.Y) Dump.println("UView.getRealSize_from31 exit point="+Ppoint);//~vam6I~
    }                                                              //~vam6I~
    //*******************************************************************//~vam6I~
    @TargetApi(31)                                                 //~vam6I~
    public static WindowMetrics getRealMetrics_from31(Display Pdisplay)//~vam6I~
    {                                                              //~vam6I~
		WindowManager mgr=getWindowManager();                      //~vam6I~
        WindowMetrics metrics=mgr.getCurrentWindowMetrics();       //~vam6I~
		if (Dump.Y) Dump.println("UView.getRealMetrics_31 metrics="+metrics);//~vam6R~
        return metrics;                                            //~vam6I~
    }                                                              //~vam6I~
    //*******************************************************      //~vay6I~
    //*return Rect null if closed                                  //~vay6I~
    //bottom:height, right:width, left/1(port)/2(land), 0;closed or not foldable//~vay6I~
    //*******************************************************      //~vay6I~
    public Rect getFoldingState(WindowLayoutInfo newLayoutInfo)    //~vay6I~
    {                                                              //~vay6I~
        List<DisplayFeature> lst=newLayoutInfo.getDisplayFeatures();//~vay6I~
        Rect rect= new Rect(0, 0, 0, 0);                           //~vay6I~
        int sz=lst.size();                                         //~vay6I~
        if (Dump.Y) Dump.println("getFoldingState size="+sz);      //~vay6I~
        if (sz!=0)                                                 //~vay6I~
        {                                                          //~vay6I~
        	DisplayFeature df=(DisplayFeature)lst.get(0);          //~vay6I~
            Rect r=df.getBounds();                                 //~vay6I~
            if (r.left==0)        //horizontal(up/down) open      l=0, t=b , r=height closed, b=width closed//~vay6I~
            {                                                      //~vay6I~
                rect.right=r.right;     //width open (portrait)    //~vay6I~
                rect.bottom=r.bottom*2; //height                   //~vay6I~
                rect.left=FOLDABLE_PORTRAIT;  //natural portrait   //~vay6R~
            }                                                      //~vay6I~
            else                 //vertical(left/right) open     l=r, t=0, r=width closed, b=height closed//~vay6I~
            {                                                      //~vay6I~
                rect.right=r.right*2;   //width openheight;        //~vay6I~
                rect.bottom=r.bottom;   //height;                  //~vay6I~
                rect.left=FOLDABLE_LANDSCAPE;  //natural portrait  //~vay6R~
            }                                                      //~vay6I~
        }                                                          //~vay6I~
		if (Dump.Y) Dump.println("getFoldingState rect="+rect.toString()+",ww="+rect.right+",hh="+rect.bottom);//~vay6I~
        return rect;                                               //~vay6I~
    }                                                              //~vay6I~
    //*******************************************************      //~vay6I~
    public static boolean isFoldableDevice()                       //~vay6I~
    {                                                              //~vay6I~
    	if (Dump.Y) Dump.println("isFoldableDevice");              //~vay6I~
        PackageManager pm=AG.context.getPackageManager();          //~vay6I~
        boolean rc=pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_HINGE_ANGLE);//~vay6I~
    	if (Dump.Y) Dump.println("isFoldableDevice rc="+rc);       //~vay6I~
        return rc;                                                 //~vay6I~
    }                                                              //~vay6I~
    //*******************************************************      //~vay6I~
    public static boolean isTallDevice(int Pww,int Phh)            //~vay6I~
    {                                                              //~vay6I~
        boolean rc;                                                //~vay6I~
        if (Phh>Pww)                                               //~vay6I~
	        rc=(double)Phh/Pww>=RATE_TALL_ASPECT;                  //~vay6I~
        else                                                       //~vay6I~
	        rc=(double)Pww/Phh>=RATE_TALL_ASPECT;                  //~vay6I~
        if (Dump.Y) Dump.println("isTallDevice rc="+rc+",ww="+Pww+",hh="+Phh+",aspect="+((double)Phh/Pww)+",limit="+RATE_TALL_ASPECT);//~vay6I~
        return rc;                                                 //~vay6I~
    }                                                              //~vay6I~
    //*******************************************************************//~vaytI~
    public  static boolean isEdgeMode()                            //~vaytI~
    {                                                              //~vaytI~
//		boolean rc=(Build.VERSION.SDK_INT>=35);   //android V(15)    //~vaytI~//~vayMR~
  		boolean rc=(AG.osVersion>=APIVER_EDGEMODE_DEFAULT);   //api35 android V(15)//~vayMR~
        rc|=AG.swSetEdgeToEdge;                                    //~vayHI~
		if (Dump.Y) Dump.println("UView.isEdgeMode rc="+rc+",apiLevel="+Build.VERSION.SDK_INT+",AG.swSetEdgeToEdge="+AG.swSetEdgeToEdge);//~vaytI~//~vayHR~
        return rc;                                                 //~vaytI~
    }                                                              //~vaytI~
    //*******************************************************************//~vayHI~
    public static boolean enableEdgeToEdge()                  //~vayHI~
    {                                                              //~vayHI~
    	boolean rc=false;                                          //~vayHI~
        if (!isEdgeMode()) //default on api35                      //~vayHI~
//          if (Build.VERSION.SDK_INT>=32);   //Android12:api31:tec//~vayHI~//~vayMR~
            if (AG.osVersion>=APIVER_EDGEMODE)   //30 Android11:api30:g10//~vayMR~//~vayNR~
            {                                                      //~vayHI~
//                AG.activity.enableEdgeToEdge();                  //~vayHR~
                EdgeToEdge.enable(AG.aMainActivity);                    //~vayHR~
                AG.swSetEdgeToEdge=true;                           //~vayHI~
                rc=true;                                           //~vayHI~
            }                                                      //~vayHI~
		if (Dump.Y) Dump.println("UView.enableEdgeToEdge requested="+rc+",apiLevel="+Build.VERSION.SDK_INT);//~vayHI~
        return rc;                                                 //~vayHI~
    }                                                              //~vayHI~
    //*******************************************************************//~vayKI~
    //*for >=api30                                                 //~vayKI~//~vayZR~
    //     AG.scrNavigationbarBottomHeightA11                      //~vayKI~
    //     AG.scrNavigationbarLeftWidthA11                         //~vayKI~
    //     AG.scrNavigationbarRightWidthA11                        //~vayKI~
    //*******************************************************************//~vayKI~
    @TargetApi(30)   //                                            //~vayKR~
    private static void getInsetMargin()                           //~vayKR~
    {                                                              //~vayKI~
    	int left=0,right=0,bottom=0,top=0;                         //~vayKI~
    	int naviH,statH;                                           //~vayMI~//~vayZR~
    //*************                                                //~vayKI~
    	int rot=AG.scrRotation;                                    //~vayKM~
	    if (Dump.Y) Dump.println(CN+"getInsetMargin api="+Build.VERSION.SDK_INT+",edgeMode="+isEdgeMode()+",foldingFeature="+AG.foldingFeature+",foldingFeatureOpened="+AG.foldingFeatureOpened+",swLongDevice="+AG.swLongDevice);//~vayMR~
	    if (Dump.Y) Dump.println(CN+"getInsetMargin portrait="+AG.portrait+",rotation="+rot+"="+//~vayKR~
(rot==ROT_LAND_LEFT ? "left" :                                     //~vayKI~
(rot==ROT_LAND_RIGHT ? "right" :                                   //~vayKI~
(rot==ROT_PORT ? "portTop" : "portBottom"))));                     //~vayKI~
        if (isEdgeMode())//api>=32                                 //~vayKI~
        {                                                          //~vayKI~
        //*api30                                                   //~vayZI~
            WindowMetrics wm=AG.activity.getWindowManager().getCurrentWindowMetrics();//~vayKR~
            WindowInsets wi=wm.getWindowInsets();                  //~vayKR~
//          Insets insets=wi.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());//~vayKR~//~vayMR~
            Insets navi=wi.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());//~vayMI~
	    	if (Dump.Y) Dump.println(CN+"getInsetMargin inset navigation="+navi);//~vayMI~
        	Insets stat=wi.getInsetsIgnoringVisibility(WindowInsets.Type.statusBars());//~vayMI~
	    	if (Dump.Y) Dump.println(CN+"getInsetMargin inset status="+stat);//~vayNR~
            naviH=navi.left; naviH=Math.max(naviH,navi.top); naviH=Math.max(naviH,navi.right); naviH=Math.max(naviH,navi.bottom);//~vayMR~
            statH=stat.left; statH=Math.max(statH,stat.top); statH=Math.max(statH,stat.right); statH=Math.max(statH,stat.bottom);//~vayMR~//~vayZR~
	    	if (Dump.Y) Dump.println(CN+"getInsetMargin statH="+statH+",naviH="+naviH);//~vayMI~//~vayZR~
        	Insets insetco=wm.getWindowInsets().getInsets(WindowInsets.Type.displayCutout());//~vayZI~
	    	if (Dump.Y) Dump.println("UView:getInsetMargin displayCutouts="+Utils.toString(insetco));//~vayZI~
         if (true)                                                 //~vayZI~
         {                                                         //~vayZI~
         	AG.scrCutoutLeft=insetco.left;                         //~vayZR~
         	AG.scrCutoutRight=insetco.right;                       //~vayZR~
         	AG.scrCutoutTop=insetco.top;                           //~vayZR~
         	AG.scrCutoutBottom=insetco.bottom;                     //~vayZI~
			if (!AG.swNavigationbarGestureMode)//if gesture mode, navigation is transparent//~vayZI~
            {                                                      //~vayZI~
            //*3button mode                                        //~vayZI~
                top=navi.top;                                      //~vayZR~
                bottom=navi.bottom;                                //~vayZR~
                left=navi.left;                                   //~vayZR~
                right=navi.right;                                  //~vayZR~
            }                                                      //~vayZI~
         }                                                         //~vayZI~
         else                                                      //~vayZI~
         if (false)                                                //~vayZR~
         {                                                         //~vayZI~
         	left=insetco.left;                                     //~vayZI~
         	right=insetco.right;                                   //~vayZI~
			if (AG.swNavigationbarGestureMode)//navigation is transparent//~vayZI~
            {                                                      //~vayZI~
            	top=insetco.top;                                   //~vayZI~
                bottom=insetco.bottom;                             //~vayZI~
            }                                                      //~vayZI~
            else //3button mode                                    //~vayZI~
            {                                                      //~vayZI~
            	boolean swDone=false;                              //~vayZI~
                if (AG.foldingFeature)                             //~vayZR~
                {                                                  //~vayZI~
                	if (AG.foldingFeatureOpened!=FOLDING_STATE_CLOSE)	//open or unknown//~vayZR~
                    bottom=naviH;    //rotation keep navigationbar at bottom//~vayZR~
                }//folding feature                                 //~vayZR~
                if (!swDone)                                       //~vayZR~
                {                                                  //~vayZI~
                    if (AG.portrait)//no top/bottom for landscape  //~vayZR~
                    {                                              //~vayZR~
                        if (navi.bottom!=0)                        //~vayZR~
                        {                                          //~vayZR~
                            if (insetco.bottom!=0)                 //~vayZR~
                            {                                      //~vayZR~
                                bottom=Math.max(navi.bottom,insetco.bottom);//~vayZI~
                                if (Dump.Y) Dump.println(CN+"getInsetMargin normal port overwrap top="+top+",bottom="+bottom);//~vayZR~
                            }                                      //~vayZR~
                            else                                   //~vayZR~
                            {                                      //~vayZR~
                                top=insetco.top;                   //~vayZR~
                                bottom=navi.bottom;                //~vayZR~
                                if (Dump.Y) Dump.println(CN+"getInsetMargin normal port top="+top+",bottom="+bottom);//~vayZR~
                            }                                      //~vayZR~
                        }                                          //~vayZR~
                        else                                       //~vayZR~
                        if (navi.top!=0)                           //~vayZR~
                        {                                          //~vayZR~
                            if (insetco.top!=0)                    //~vayZR~
                            {                                      //~vayZR~
                                top=Math.max(navi.top,insetco.top);//~vayZR~
                                if (Dump.Y) Dump.println(CN+"getInsetMargin reverse port overwrap top="+top+",bottom="+bottom);//~vayZR~
                            }                                      //~vayZR~
                            else                                   //~vayZR~
                            {                                      //~vayZR~
                                top=navi.top;                      //~vayZR~
                                bottom=insetco.bottom;             //~vayZR~
                                if (Dump.Y) Dump.println(CN+"getInsetMargin reverse port both top="+top+",bottom="+bottom);//~vayZR~
                            }                                      //~vayZR~
                        }//navi top!=0                             //~vayZI~
                    }//portrait                                    //~vayZR~
            	}//Not folding feature                             //~vayZI~
            }//3button mode                                        //~vayZR~
         }//true                                                   //~vayZR~
         else //false                                              //~vayZI~
         {                                                         //~vayZI~
          if (AG.foldingFeature                                   //~vayMI~//~vayNR~
          &&  AG.foldingFeatureOpened==FOLDING_STATE_CLOSE)        //~vayNI~
          {                                                        //~vayMI~
                if (AG.portrait)                                   //~vayKR~
                {                                                  //~vayKR~
                    if (AG.scrRotation==ROT_PORT)                  //~vayKR~
                    {                                              //~vayKR~
//                      top=insets.top;       bottom=insets.bottom;   //~vayKR~//~vayLR~//~vayMR~
                        top=statH;            bottom=naviH;        //~vayMI~
                    }                                              //~vayKR~
                    else       //reverse                           //~vayKR~
                    {                                              //~vayKR~
//                      bottom=insets.bottom; bottom=insets.top;      //~vayKR~//~vayLR~//~vayMR~
//                      top=naviH;            bottom=statH;        //~vayMI~//~vayNR~
                        top=statH;            bottom=naviH; //evenif reverse navi is bottom//~vayNI~
                    }                                              //~vayKR~
			      	if (AG.swNavigationbarGestureMode)             //~vayMI~
                    	bottom=0;                                  //~vayMI~
                }                                                  //~vayKR~
                else   //land                                      //~vayKR~
                {                                                  //~vayKR~
                    if (AG.scrRotation==ROT_LAND_LEFT)             //~vayKR~
                    {                                              //~vayKR~
//                      left=insets.top;  //right=0(toEdge) right=insets.bottom;//~vayKR~//~vayMR~
                        left=statH;                                //~vayMI~
                    }                                              //~vayKR~
                    else                                           //~vayKR~
                    {                                              //~vayKR~
//                      right=insets.top; //left=0(toEdge) left=insets.bottom;//~vayKR~//~vayMR~
                        right=statH;                               //~vayMI~
                    }                                              //~vayKR~
                }                                                  //~vayKR~
          }//foldingFeature & closed                                        //~vayMM~//~vayNR~
          else                                                     //~vayMI~
        //*not foldable                                            //~vayMI~
	      if (!AG.swNavigationbarGestureMode)                      //~vayMI~
          {                                                        //~vayMI~
 	      	  if (AG.swLongDevice)                                 //~vayMR~
    	      {                                                    //~vayMR~
                if (AG.portrait)                                   //~vayMI~
                {                                                  //~vayMI~
                    if (AG.scrRotation==ROT_PORT)                  //~vayMI~
                    {                                              //~vayMI~
//                      top=insets.top;       bottom=insets.bottom;//~vayMR~
                        top=statH;            //~vayMI~
                        bottom=naviH;                              //~vayMI~
                    }                                              //~vayMI~
                    else       //reverse                           //~vayMI~
                    {                                              //~vayMI~
//                      bottom=insets.bottom; bottom=insets.top;   //~vayMR~
                        bottom=statH;                              //~vayMI~
                        top=naviH;                                 //~vayMI~
                    }                                              //~vayMI~
            		if (Dump.Y) Dump.println(CN+"getInsetMargin Long port top="+top+",bottom="+bottom);//~vayMI~
                }                                                  //~vayMI~
                else   //land                                      //~vayMI~
                {                                                  //~vayMI~
                    if (AG.scrRotation==ROT_LAND_LEFT)             //~vayMI~
                    {                                              //~vayMI~
//                      left=insets.left;                          //~vayMR~
                        left=statH;                                //~vayMI~
                    }                                              //~vayMI~
                    else                                           //~vayMI~
                    {                                              //~vayMI~
//                      right=insets.right;                        //~vayMR~
                        right=statH;                               //~vayMI~
                    }                                              //~vayMI~
            		if (Dump.Y) Dump.println(CN+"getInsetMargin Long land left="+left+",right="+right);//~vayMI~
                }                                                  //~vayMI~
              }//long device                                       //~vayMR~
              else                                                 //~vayMR~
              {                                                    //~vayMR~
               //tablet                                            //~vayMR~
//                  if (AG.portrait)                               //~vayMR~//~vayNR~
//                  {                                              //~vayMR~//~vayNR~
//                      bottom=insets.bottom; bottom=insets.top;   //~vayMR~
                        bottom=naviH;                              //~vayMI~
                        if (Dump.Y) Dump.println(CN+"getInsetMargin tablet bottom="+bottom);//~vayMR~//~vayNR~
//                  }                                              //~vayMR~//~vayNR~
              }                                                    //~vayMR~
          }//3button mode                                          //~vayMR~
          else //gesturemode                                       //~vayRI~
          {                                                        //~vayRI~
 	      	  if (AG.swLongDevice)                                 //~vayRI~
    	      {                                                    //~vayRI~
                if (AG.portrait)                                   //~vayRI~
                {                                                  //~vayRI~
                    if (AG.scrRotation==ROT_PORT)                  //~vayRI~
                    {                                              //~vayRI~
                        top=statH;                                 //~vayRI~
                    }                                              //~vayRI~
                    else       //reverse                           //~vayRI~
                    {                                              //~vayRI~
                        bottom=statH;                              //~vayRI~
                    }                                              //~vayRI~
            		if (Dump.Y) Dump.println(CN+"getInsetMargin gesture mode Long port top="+top+",bottom="+bottom);//~vayRI~
                }                                                  //~vayRI~
                else   //land                                      //~vayRI~
                {                                                  //~vayRI~
                    if (AG.scrRotation==ROT_LAND_LEFT)             //~vayRI~
                    {                                              //~vayRI~
                        left=statH;                                //~vayRI~
                    }                                              //~vayRI~
                    else                                           //~vayRI~
                    {                                              //~vayRI~
                        right=statH;                               //~vayRI~
                    }                                              //~vayRI~
            		if (Dump.Y) Dump.println(CN+"getInsetMargin gesture mode Long land left="+left+",right="+right);//~vayRI~
                }                                                  //~vayRI~
              }//long device                                       //~vayRI~
          }                                                        //~vayRI~
         }//false                                                  //~vayZI~
            if (Dump.Y) Dump.println(CN+"getInsetMargin l="+left+",t="+top+",r="+right+",b="+bottom);//~vayKR~
            AG.scrNavigationbarLeftWidthA11=left;                  //~vayLR~
            AG.scrNavigationbarRightWidthA11=right;                //~vayLR~
            AG.scrNavigationbarBottomHeightA11=bottom;             //~vayLR~
            AG.scrNavigationbarTopHeightA11=top;                   //~vayLR~
            AG.scrWidth=AG.scrWidthReal;                           //~vayNR~
            AG.scrHeight=AG.scrHeightReal;                         //~vayNR~
    //deprecated at api30                                          //~vayKR~
    //        int rSys=PwindowInsets.getSystemWindowInsetRight();  //~vayKR~
    //        int lSys=PwindowInsets.getSystemWindowInsetLeft();   //~vayKR~
    //        int tSys=PwindowInsets.getSystemWindowInsetTop();    //~vayKR~
    //        int bSys=PwindowInsets.getSystemWindowInsetBottom(); //~vayKR~
    //        if (Dump.Y) Dump.println(CN+"getInsetMargin deprecated SystemBars get l="+lSys+",t="+tSys+",r="+rSys+",b="+bSys);//~vayKR~
		}//edge mode                                               //~vayKI~
//	    getWindowMetrics();//TEST                                  //~vayKI~
		if (Dump.Y) Dump.println(CN+"getInsetMargin exit edgeMode="+isEdgeMode()//~vayKR~
+",AG.scrNavigationbarLeftWidthA11="+AG.scrNavigationbarLeftWidthA11//~vayKI~//~vayLM~
+",AG.scrNavigationbarRightWidthA11="+AG.scrNavigationbarRightWidthA11//~vayKI~//~vayLM~
+",AG.scrNavigationbarTopHeightA11="+AG.scrNavigationbarTopHeightA11//~vayLI~
+",AG.scrNavigationbarBottomHeightA11="+AG.scrNavigationbarBottomHeightA11);//~vayKI~
		if (Dump.Y) Dump.println(CN+"getInsetMargin exit AG.scrWidth="+AG.scrWidth+",AG.scrHeight="+AG.scrHeight);//~vayNI~
    }                                                              //~vayKI~
    //*******************************************************************//~vayKI~
    //*for >=api31                                                 //~vayKI~
    //     AG.scrNavigationbarBottomHeightA11                      //~vayKI~
    //     AG.scrNavigationbarLeftWidthA11                         //~vayKI~
    //     AG.scrNavigationbarRightWidthA11                        //~vayKI~
    //*******************************************************************//~vayKI~
    private static void getWindowMetrics()                         //~vayKI~
    {                                                              //~vayKI~
    //	WindowMetricsCalculator wmc=WindowMetricsCalculator.computeCurrentWindowMetrics(AG.aMainActivity);//~vayKI~
    //	Rect r=wmc.bounds;                                       //~vayKI~
		WindowMetricsCalculator	wmc2=WindowMetricsCalculator.getOrCreate();//~vayKI~
	//	WindowMetrics wm=wmc2.computeCurrentWindowMetrics(AG.aMainActivity);//~vayKI~
		androidx.window.layout.WindowMetrics wm2=wmc2.computeCurrentWindowMetrics(AG.aMainActivity);//~vayKI~
		if (Dump.Y) Dump.println(CN+"getWindowMetrics WindowMetricsCalcurator="+wmc2);//~vayKI~
		if (Dump.Y) Dump.println(CN+"getWindowMetrics WindowMetrics="+wm2);//~vayKI~
        Rect r=wm2.getBounds();                                         //~vayKI~
		if (Dump.Y) Dump.println(CN+"getWindowMetrics bounds="+r); //~vayKM~
    }                                                              //~vayKI~
}//class UView                                                     //~9410I~
