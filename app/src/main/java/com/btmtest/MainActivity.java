//*CID://+vamdR~:                             update#= 424;        //~vamdR~
//**********************************************************************//~@@@@I~
//2022/04/02 vamd Animation. at first show Dora                    //~vamdI~
//2022/03/31 vamc android12(api31) when backed to top from landscape by F1 button, navigation bar is disappeared.//~vamcI~
//                (from menu or back button, navigation bar is shown before back to top . so navigation bar is kept.)//~vamcI~
//                this bug of vam5. systembar means statusbar+navigationbar. hide systembar hide both//~vamcI~
//2022/03/31 vamb android12(api31) LIGHT_STATUS_BAR appears from compiled by abdroid12//~vambI~
//2022/03/29 vam8 android12(api31) Bluetooth permission is runtime permission//~vam8I~
//2022/03/28 vam5 android12(api31) deprecated BEHAVIOUR_SHOW_BARS_BY_SWIPE//~vam5I~
//2022/03/23 vakV google exception report; add try catch           //~vakvI~
//2022/01/31 vaji change color of top left to identify server      //~vajiI~
//2022/01/31 vajh over vajg/vage, try to allow startgame from client//~vajhI~
//2022/01/28 vaje (bug)startgame from client should be protected   //~vajeI~
//2022/01/11 vaik Youtube movie as help                            //~vaikI~
//2021/10/22 vaf3 Dump to logcat unconditionally before open       //~vaf3I~
//2021/10/21 vaf0 Play console crash report "IllegalStateException" at FragmentManagerImple.1536(checkStateLoss)//~vaf0I~
//2021/09/27 vaef gesture navigation mode from android11           //~vaefI~
//2021/09/26 vaee gesture navigation mode from android10           //~vaeeI~
//2021/09/21 vaeb try not cache but file, cache miss line?         //~vaebI~
//2021/09/19 vaea stop also non userBGM when pause                 //~vaeaI~
//2021/09/19 vae9 1ak2(access external audio file) for BTMJ        //~vae9I~
//2021/09/19 vae8 keep sharedPreference to external storage with PrefSetting item.//~vae8I~
//2021/09/16 vae5 (Bug)Property of resumed game did not use sg.rulefile at interrupted.//~vae5I~
//2021/09/13 vae2 BGM for BTMJ5                                    //~vae2I~
//2021/08/25 vae0 Scped for BTMJ5                                  //~vae0I~
//1ak5 2021/09/12 OnACtivityResut is missing for Bluetooth enable  //~1ak5I~
//1ak4 2021/09/11 androd11(api30) deprecated at api30;setSystemUiVisibility default constructor(requires parameter)//~1ak4I~
//1ak2 2021/09/04 access external audio file                       //~1ak2I~
//1ak1 2021/08/27 write Dump.txt to internal cache, it ca be pull by run-as cmd//~1ak1I~
//1ak0 2021/08/26 androd11:externalStorage:ScopedStorage           //~1ak0I~
//2021/06/17 va9f correct reason of reverse orientation did not work(fix orientation was called)//~va9fI~
//                not work because onConfigurationChanged is not fired by RVERSE request//~va9fI~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2020/11/08 va41 (Bug) coding Main.onPause had call CSI.onResume  //~va41I~
//2020/11/06 va30 change greenrobot EventCB to URunnable           //~va30I~
//2020/04/27 va06:BGM                                              //~va06I~
//**********************************************************************//~@@@@I~
package com.btmtest;

import android.content.Context;
import android.annotation.TargetApi;                               //~1ak4R~
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;                                  //~1ak4R~
import android.view.WindowInsetsController;                        //~1ak4R~
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.btmtest.BT.BTI;
import com.btmtest.dialog.HelpDialog;
import com.btmtest.dialog.MenuDialog;
import com.btmtest.dialog.MenuDlgConnect;
import com.btmtest.dialog.OrientationMenuDlg;
import com.btmtest.dialog.TestLayout;
import com.btmtest.game.Accounts;
import com.btmtest.game.GConst;
import com.btmtest.game.History;
import com.btmtest.game.HistoryData;
import com.btmtest.dialog.PrefSetting;
import com.btmtest.dialog.RuleSetting;                             //~9412R~
import com.btmtest.game.gv.Anim;
import com.btmtest.game.gv.Pieces;
import com.btmtest.utils.AlertDlg;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Alert;                                    //~8B07I~
import com.btmtest.gui.UButton;//~8B05I~
import com.btmtest.game.GC;                                        //~8B05I~
import com.btmtest.utils.EventCB;
import com.btmtest.utils.EventToast;
import com.btmtest.utils.Prop;
import com.btmtest.utils.UFile;
import com.btmtest.utils.UMediaStore;
import com.btmtest.utils.URunnable;
import com.btmtest.utils.UScoped;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;
import com.btmtest.game.Status;//~@@@@I~
import com.btmtest.BT.BTMulti;                                     //~@@@@I~
import com.btmtest.utils.sound.Sound;
import com.btmtest.wifi.IPMulti;                                   //~9723I~
import com.btmtest.wifi.CSI;                                       //~9B05I~
import com.btmtest.wifi.WDI;

import java.util.Arrays;

import static android.view.WindowInsetsController.*;
import static com.btmtest.AG.*;
import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.game.GConst.*;                          //~8B22I~
import static com.btmtest.StaticVars.AG;                           //~@@@@M~
import static com.btmtest.dialog.PrefSetting.*;
import static com.btmtest.utils.Alert.*;

public class MainActivity extends AppCompatActivity
		implements URunnable.URunnableI, UButton.UButtonI//~v@@@R~ //~@@@@I~
               ,Alert.AlertI                                        //~9611I~//~0119I~
{                                                                  //~8B05I~
	private static final String CN="MainActivity.";                //~vaf0I~
//    private static final String BUNDLE_AG="AG";                  //~vaf0R~
    private static final int HELP_TITLEID=R.string.Title_Help_Main;//~8C29I~
    private static final String HELPFILE="Main";              //~8C29R~//~9C13R~
//  private static final int MAIN_BUTTONS =R.layout.main_buttons;  //~8C29R~//~9807R~
    private static final int MAIN_TITLE =R.layout.main_title;      //~8C29I~
    private static final int MAIN_LAYOUT =R.layout.main_activity;  //~8C29I~
    private static final int RID_TOPIMAGE=R.drawable.top_portrait; //~8C29I~
                                                                   //~@@@@I~
//  public static final int MAXCLIENT=3; //TODO                    //~@@@@I~//~9718R~
    public static final int MAXCLIENT=4;                           //~0322R~
    public static final int PERMISSION_LOCATION=1;                 //~9930I~
    public static final int PERMISSION_EXTERNAL_STORAGE=2;         //~9B09I~
    public static final int PERMISSION_EXTERNAL_STORAGE_READ=3; //ReadOnly   //~1Ak2I~//~1ak2I~
    public static final int PERMISSION_BLUETOOTH=4; //SCAN and CONNECT//~vam8I~
                                                                   //~8C29I~
    public static final int TOP_ORIENTATION=ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;//~va9fR~
    private static String PLAYLIST_ID;                       //~vaikR~
                                                                   //~va9fI~
//  private Button btnSettings,btnConnect,btnHelp,btnStartGame;    //~8C29R~//~0119R~
    private Button btnHistory;                                     //~9613I~
    private View  btnsMain,titleMain;                              //~8C29R~
    private FrameLayout frameLayout;                                      //~8C29I~
//  private static Bitmap bmpTop;                                  //~8C30I~//~9105R~
    private int chngOrientation;                           //~9101I~
    private boolean swEndGame;                                     //~9102I~
    private	ImageView imageView;                                   //~9103I~
    private	MainView mainView;               //~9103I~             //~9620R~
    public  Point frameLayoutSizePortrait=null;                            //~9104I~//~9411R~
    public  Point frameLayoutSizeLandscape=null;                   //~9411R~
//  private MsgIOReceiver msgReceiver;                             //~@@@@I~//~9A23R~
//  private int reverseOrientation=-1;                             //~9610R~
    private boolean swPaused,swIOErr,swStopped;                    //~9A22R~
    private String msgIOErr;                                       //~9A22I~
//    private OrientationEventListener listenerOrientationChanged; //TODO test//~va9fR~
//  private int orientationBeforeGame;	//0/1:land/port, 8/9:reverse, 6/7:sensor//~va9fR~
    private boolean swServerStartGame=true;                        //~vajhI~
    //***********************************************************  //~8B05I~
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
//  	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);//~9610R~
//  	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);//~9610I~
//        AG ag=restoreInstanceState(savedInstanceState);          //~vaf0R~
//      if (Utils.isDebuggable((Context)this)) Dump.printlnLog("MainActivity.onCreate bundle="+Utils.toString(savedInstanceState));//~vaf3I~//~vaf0R~
//      Dump.printlnLog("MainActivity.onCreate DEBUG="+BuildConfig.DEBUG+",bundle="+Utils.toString(savedInstanceState));//~vaf0R~
        if (BuildConfig.DEBUG) Dump.printlnLog("MainActivity.onCreate bundle="+Utils.toString(savedInstanceState));//~vaf0I~
        if (savedInstanceState!=null)                              //~vaf0R~
        {                                                          //~vaf0R~
            if (BuildConfig.DEBUG) Dump.printlnLog("MainActivity.onCreate Restart");//~vaf0R~
////          UView.showToastLongDirect((Context)this,Utils.getStr(getResources(),R.string.Err_AppDestroyedUnexpectedlyRestart));//~vaf0R~
//            issueDestroyedWarningRestart();                      //~vaf0R~
        }                                                          //~vaf0R~
//      super.onCreate(savedInstanceState);                        //~vaf0R~
        super.onCreate(null);                                      //~vaf0I~
//      AG.init(this);                                             //~8B05I~//~9103R~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~0124I~
//      if (ag!=null)                                              //~vaf0R~
//        new StaticVars(this,ag);    //new AG().init(this);       //~vaf0R~
//      else                                                       //~vaf0R~
        new StaticVars(this);	//new AG().init(this);             //~9103I~
        new TestOption();                                          //~vae0I~
        if (savedInstanceState!=null)                              //~vaf0I~
        {                                                          //~vaf0I~
//          UView.showToastLongDirect((Context)this,Utils.getStr(getResources(),R.string.Err_AppDestroyedUnexpectedlyRestart));//~vaf0I~
            issueDestroyedWarningRestart();                        //~vaf0I~
        }                                                          //~vaf0I~
        if (Dump.Y) Dump.println("MainActivity.onCreate bundle="+Utils.toString(savedInstanceState));//~vaf0I~
     try                                                           //~vae0I~
     {                                                             //~vae0I~
        setFullscreen(true/*onCreate*/);                                           //~8B06I~//~8C29R~//~9103R~//~1ak4R~
//      Dump.open("");	//write all to log , move to AG                         //~8B05I~//~0124R~
        if (Dump.Y) Dump.println("MainActivity.onCreate");                 //~9102I~//~1ak4R~//~vaf0R~
        View mainView=AG.inflater.inflate(MAIN_LAYOUT,null);//~8B05I~//~8C29R~
//      AG.mainView=mainView;                                      //~8B07I~//~9620R~
        frameLayout=(FrameLayout)UView.findViewById(mainView,R.id.FrameLayout);//~8B06I~//~8C29R~
//        resetStatic();                                             //~9101I~//~9106R~
//      new GC(frameLayout);    //game controler                       //~8B05I~//~8B06M~//~8B26R~//~9101R~
        UView.getScreenSize();                                     //~9101I~
        initMainView(mainView);                                    //~8B05I~//~8C29M~//~9103M~
        setContentView(mainView);                    //~8B05I~
        AG.swMainView=true;                                        //~9815I~
        if (Dump.Y) Dump.println("Main constructor="+((Context)this).toString());//~8B10I~
        setFullscreen(false/*onCreate*/); //after setContentView?  //~1ak4R~
//            hideNavigationBar(true);      //TODO                   //~8B26R~//~8C29R~
//  	addButtons();    //should be after addView(gameView) to show over gameView//~8C29R~
//      new TestOption();                                     //~9103R~//~9515R~//~vae0R~
//        listenerOrientationChanged=new OrientationEventListener(this)   //TODO test//~va9fR~
//        {                                                        //~va9fR~
//            @Override                                            //~va9fR~
//            public void onOrientationChanged(int Pori)           //~va9fR~
//            {                                                    //~va9fR~
////              orientationChanged(Pori);                        //~va9fR~
//                if (Dump.Y) Dump.println("MainActivity.onOrientationChanged Pori="+Pori+",config="+AG.resource.getConfiguration().orientation);//~va9fR~
//            }                                                    //~va9fR~
//        };                                                       //~va9fR~
    	initApp();                                                 //~9106I~
        new Anim(mainView,frameLayout);                            //~vamdR~
      }                                                            //~vae0I~
      catch(Exception e)                                           //~vae0I~
      {                                                            //~vae0I~
        Dump.println(e,"MainActivity.OnCreate");                   //~vae0R~
      }                                                            //~vae0I~
    }
	//*************************                                    //~8B06I~
    private void setFullscreen(boolean PswOnCreate)                                   //~8B06I~//~9103R~
    {                                                             //~8B06I~//~vaeeR~
		if (Dump.Y) Dump.println("MainActivity.setFullScreen");    //~9103I~
        if (PswOnCreate)                       //requestWindowFeature allows only at before set content//~9103I~
        {                                                          //~9103I~
            requestWindowFeature(Window.FEATURE_NO_TITLE);             //~8B04I~//~8B06I~//~9103I~
            return;                                                //~1ak4I~
        }                                                          //~9103I~
        if (Build.VERSION.SDK_INT>=30) //kitkat 4.4                //~1ak4R~
		    setFullscreen30();                                     //~1ak4R~
        else                                                       //~1ak4R~
		    setFullscreen29();                                     //~1ak4R~
    }                                                              //~1ak4R~
	//*************************                                    //~1ak4R~
    @SuppressWarnings("deprecation")                               //~1A4sI~//~1A6pI~//~1ak4R~
    private void setFullscreen29()                                 //~1ak4R~
    {                                                              //~1ak4R~
        if (Build.VERSION.SDK_INT>=16) //4.1 Jelly Bean            //~vaeeI~
        {                                                          //~vaeeI~
		    setFullscreen16_29();                                  //~vaeeI~
            return;                                                //~vaeeI~
        }                                                          //~vaeeI~
		if (Dump.Y) Dump.println("MainActivity.setFullScreen29");  //~1ak4R~
//          getSupportActionBar.hide();                            //~9103I~
          getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);//~8B06I~//~9103R~
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//~8B04I~//~8B06I~//~9103R~
          getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//~9103I~
//        if (true)   //TODO test                                  //~9807R~
//          getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);//~9807R~
    }                                                              //~8B06I~
	//*************************                                    //~vaeeI~
    @SuppressWarnings("deprecation")                               //~vaeeI~
    private void setFullscreen16_29()                              //~vaeeI~
    {                                                              //~vaeeI~
		if (Dump.Y) Dump.println("MainActivity.setFullScreen16_29");//~vaeeI~
    	View decorView=getWindow().getDecorView();                 //~vaeeI~
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);//~vaeeI~
    }                                                              //~vaeeI~
	//*************************                                    //~1ak4R~
    @SuppressWarnings("deprecation")                               //~vam5I~
	@TargetApi(30)                                   //~1A6pI~     //~1ak4R~
    private void setFullscreen30()                                 //~1ak4R~
    {                                                              //~1ak4R~
		if (Dump.Y) Dump.println("MainActivity.setFullScreen30");  //~1ak4R~
        Window w=getWindow();                                      //~1ak4R~
		if (Dump.Y) Dump.println("MainActivity.setFullScreen30 window="+w);//~1ak4I~
        if (w!=null)                                               //~1ak4I~
        {                                                          //~1ak4I~
			if (Dump.Y) Dump.println("MainActivity.setFullScreen30 WindowInsetsController not Compat");//~1ak4I~
        	WindowInsetsController ic=w.getInsetsController();     //~1ak4I~
        	if (ic!=null)                                          //~1ak4R~
            {                                                      //~1ak4I~
//            if (false) //TODO test                               //~1ak4I~//~vam5R~
              if (Build.VERSION.SDK_INT>=31)                       //~vam5I~
    			setFullScreen_from31(ic);                          //~vam5I~
              else                                                 //~vam5I~
              {                                                    //~1ak4I~//~vam5R~
                ic.hide(WindowInsets.Type.statusBars());           //~1ak4R~
            	if (Dump.Y) Dump.println("MainActivity.setFullScreen30 swEndGame="+swEndGame);//~vamcI~
		        if (Dump.Y) Dump.println("MainActivity.setFullScreen30 getSystembarBehavior="+ic.getSystemBarsBehavior());//~vamcI~
		        if (Dump.Y) Dump.println("MainActivity.setFullScreen30 getSystembarAppearance="+ic.getSystemBarsAppearance());//~vamcI~
               if (swEndGame)	//endgame from landscape           //~vamcR~
               {                                                   //~vamcI~
            	if (Dump.Y) Dump.println("MainActivity.setFullScreen30 show navigationBar by swEndGame");//~vamcR~
		        ic.setSystemBarsBehavior(BEHAVIOR_DEFAULT);         //~vamcI~
	        	ic.show(WindowInsets.Type.navigationBars());       //~vamcI~
               }                                                   //~vamcI~
               else                                                //~vamcR~
               {                                                   //~vamcR~
//          	if (Dump.Y) Dump.println("MainActivity.setFullScreen30 hide navigationBar");//~vamcR~
//              ic.hide(WindowInsets.Type.navigationBars());       //~vam5I~//~vamcR~
//              ic.hide(WindowInsets.Type.systemBars());           //~vam5I~//~vamcR~
                int b=BEHAVIOR_SHOW_BARS_BY_SWIPE;                 //~1ak4R~
                ic.setSystemBarsBehavior(b);                       //~1ak4R~
//              int a=APPEARANCE_LIGHT_STATUS_BARS;                //~1ak4I~//~vambR~
//              ic.setSystemBarsAppearance(a,a);                   //~1ak4I~//~vambR~
               }                                                   //~vamcI~
              }                                                    //~1ak4I~//~vam5R~
				if (Dump.Y) Dump.println("MainActivity.setFullScreen30 ic="+ic);//~vam5I~
            }                                                      //~1ak4I~
        }                                                          //~1ak4I~
    }                                                              //~1ak4R~
	//*************************                                    //~vam5I~
    @TargetApi(31)                                                 //~vam5I~
    private void setFullScreen_from31(WindowInsetsController Pic)  //~vam5I~
    {                                                              //~vam5I~
		if (Dump.Y) Dump.println("MainActivity.setFullScreen_from31 ic="+Pic);//~vam5I~
        WindowInsetsController ic=Pic;                             //~vam5I~
        ic.hide(WindowInsets.Type.statusBars());                   //~vam5I~
      if (swEndGame)	//endgame from landscape                   //~vamcI~
      {                                                            //~vamcI~
		ic.setSystemBarsBehavior(BEHAVIOR_DEFAULT);                //~vamcI~
        ic.show(WindowInsets.Type.navigationBars());               //~vamcI~
      }                                                            //~vamcI~
      else                                                         //~vamcI~
      {                                                            //~vamcI~
//      ic.hide(WindowInsets.Type.navigationBars());               //~vam5I~//~vamcR~
//      ic.hide(WindowInsets.Type.systemBars());                   //~vam5I~//~vamcR~
//      int b=BEHAVIOR_DEFAULT;                                    //~vam5I~//~vam8R~
        int b=BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE;                    //~vam8I~//~vamcR~
        ic.setSystemBarsBehavior(b);                               //~vam5I~
//      int a=APPEARANCE_LIGHT_STATUS_BARS;                        //~vam5I~//~vambR~
//      ic.setSystemBarsAppearance(a,a);                           //~vam5I~//~vambR~
      }                                                            //~vamcI~
    }                                                              //~vam5I~
	//*************************                                    //~8B26I~
//    public static void hideNavigationBar(boolean PswHide)                 //~8B26R~//~8C29R~
//    {                                                              //~8B26I~//~8C29R~
//        if (Dump.Y) Dump.println("Main hideNavigationBar swHide="+PswHide);//~8B26I~//~8C29R~
//        View decor=AG.activity.getWindow().getDecorView();             //~8B26I~//~8C29R~
//        int flag=0;                                                //~8B26I~//~8C29R~
//        if (!PswHide)                                              //~8B26I~//~8C29R~
//        {                                                          //~8B26I~//~8C29R~
//                    flag= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//~8B26I~//~8C29R~
//                         |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN    //~8B26I~//~8C29R~
//                         |View.SYSTEM_UI_FLAG_LAYOUT_STABLE        //~8B26I~//~8C29R~
//                         ;                                         //~8B26I~//~8C29R~
//            decor.setSystemUiVisibility(flag);                     //~8B26I~//~8C29R~
//        }                                                          //~8B26I~//~8C29R~
//        else                                                       //~8B26I~//~8C29R~
//        {                                                          //~8B26I~//~8C29R~
//            UView.getScreenSize();                                 //~8B26I~//~8C29R~
//            if (AG.scrHeight<AG.scrWidth)   //landscape            //~8B26I~//~8C29R~
//            {                                                      //~8B26I~//~8C29R~
//                if (Build.VERSION.SDK_INT>=19)                     //~8B26R~//~8C29R~
////                    flag= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION    //~8B26R~//~8C29R~
////                         |View.SYSTEM_UI_FLAG_FULLSCREEN         //~8B26R~//~8C29R~
////                         |View.SYSTEM_UI_FLAG_IMMERSIVE          //~8B26R~//~8C29R~
////                         |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY   //~8B26R~//~8C29R~
//                    flag= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION      //~8B26I~//~8C29R~
//                         |View.SYSTEM_UI_FLAG_FULLSCREEN           //~8B26I~//~8C29R~
//                         |View.SYSTEM_UI_FLAG_IMMERSIVE            //~8B26I~//~8C29R~
//                         |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY     //~8B26I~//~8C29R~
//                         |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//~8B26R~//~8C29R~
//                         |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN    //~8B26I~//~8C29R~
//                         |View.SYSTEM_UI_FLAG_LAYOUT_STABLE        //~8B26I~//~8C29R~
//                         ;                                         //~8B26R~//~8C29R~
//                else                                               //~8B26R~//~8C29R~
//                if (Build.VERSION.SDK_INT>15)                      //~8B26R~//~8C29R~
//                    flag=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN;//~8B26R~//~8C29R~
//            }                                                      //~8B26I~//~8C29R~
//            if (flag!=0)                                           //~8B26R~//~8C29R~
//            {                                                      //~8B26I~//~8C29R~
//                if (Dump.Y) Dump.println("Main.hideNavigationBar flag="+Integer.toHexString(flag));//~8B26I~//~8C29R~
//                decor.setSystemUiVisibility(flag);                 //~8B26R~//~8C29R~
//            }                                                      //~8B26I~//~8C29R~
//        }                                                          //~8B26I~//~8C29R~
//    }                                                              //~8B26I~//~8C29R~
	//*************************                                    //~9106I~
	private void initApp()                                         //~9106I~
    {                                                              //~9106I~
    	PLAYLIST_ID=AG.resource.getString(R.string.YouTube_PlaylistID);//~vaikI~
    	Pieces.recycleAll();                                       //~@@@@I~
        new GConst();                                              //~@@@@I~
        new UScoped();                                             //~1ak1R~
        new UMediaStore();                                         //~1ak2R~
    	new Sound();     //after USCoped,UmediaStore init                      //~@@01M~//~vae9I~
        UFile.chkWritableSD();  //grant SDcard permission      //~1ak0I~//~1Ak0I~//~1ak0I~
//      msgReceiver=new MsgIOReceiver();                                 //~9106I~//~@@@@R~//~9A23R~
//      AG.aMsgIO=new MsgIO(msgReceiver);                          //~@@@@I~//~9A23R~
        new Status();                                          //~v@@@I~//~@@@@I~
//      AG.aBTMulti=new BTMulti(MAXCLIENT);                        //~9106I~//~9723R~
        new CSI();                                                 //~9B05I~
        new IPMulti(MAXCLIENT);                                    //~9723I~
//      new History();                                             //~9614I~//~9B09R~
        initHistory(true/*swInitApp*/);                            //~9B09I~
    }                                                              //~9106I~
	//*************************                                    //~8B05I~
	//*now foreground                                              //~9A22I~
	//*************************                                    //~9A22I~
    @Override                                                      //~8B05I~
    public void onStart()                                          //~8B05I~
    {                                                              //~8B05I~
        if(Dump.Y) Dump.println("MainActivity.onStart");           //~8B05I~//~vaf0R~
        super.onStart();                                           //~8B05I~
        swStopped=false;                                           //~9A22I~
//		registerEventBus(true);                                    //~va30R~
    }                                                              //~8B05I~
	//*************************                                    //~9A22I~
	//*now background                                              //~9A22I~
	//*************************                                    //~9A22I~
    @Override                                                      //~9A22I~
    public void onStop()                                           //~9A22I~
    {                                                              //~9A22I~
        if(Dump.Y) Dump.println("MainActivity.onStop");            //~9A22I~//~vaf0R~
//  	registerEventBus(false);                                   //~9A22R~
        swStopped=true; //keep registered and ignore except IOErr  //~9A22I~
        super.onStop();                                            //~9A22I~
    }                                                              //~9A22I~
	//*************************                                    //~8B05I~
    @Override                                                      //~8B05I~
    protected void onResume() {                                    //~8B05I~
        if(Dump.Y) Dump.println("MainActivity.onResume");          //~8B05I~//~vaf0R~
        super.onResume();                                          //~8B05I~
        swPaused=false;                                            //~9A22I~
      try                                                          //~9719I~
      {                                                            //~9719I~
//    	listenerOrientationChanged.enable(); //TODO test           //~va9fR~
        AG.aCSI.onResume();	//set BTHandler activity               //~9B05I~
        AG.aBTI.onResume();	//set BTHandler activity               //~@@@@I~
        WDI.onResume();		//register receiver                    //~0113I~
        if (AG.aWDA!=null)                                         //~9719I~
			AG.aWDA.onResume();                                    //~9719I~
//  	registerEventBus(true);                                    //~8C30I~//~@@@@M~//~9A22R~
//        hideNavigationBar(true);                                   //~8B26I~//~8C29R~
		if (AG.aGC!=null)                                          //~9101I~
	        AG.aGC.onResume();                                         //~8B06I~//~9101R~
		if (AG.aIPSubThread!=null)                                 //~9A02I~
	        AG.aIPSubThread.onResume();                            //~9A02I~
        if(Dump.Y) Dump.println("MainActivity.onResume swIOErr="+swIOErr+",msgIOErr="+msgIOErr);//~9A22I~//~vaf0R~
        if (swIOErr)	//ioerr received at task is background     //~9A22I~
        {                                                          //~9A22I~
        	swIOErr=false;                                         //~9A22M~
            String msg=msgIOErr;                                   //~9A22I~
            msgIOErr=null;                                         //~9A22M~
	        msgRead(MSGID_IOERR,msg);                              //~9A22R~
        }                                                          //~9A22I~
        UMediaStore.onResume();	//chk Sound.playBGM                //~1ak2R~
//      Sound.playBGM(SOUNDID_BGM_TOP);	                           //~va06R~//~vaeaR~
        Sound.onResume();                                          //~vaeaI~
      }                                                            //~9719I~
      catch(Exception e)                                           //~9719I~
      {                                                            //~9719I~
      	Dump.println(e,"MainActivity.onResume");                   //~9719I~//~vaf0R~
      }                                                            //~9719I~
    }                                                              //~8B05I~
	//*************************                                    //~8B05I~
    @Override                                                      //~8B05I~
    protected void onPause() {                                     //~8B05I~
        if(Dump.Y) Dump.println("MainActivity.onPause");           //~8B05I~//~vaf0R~
    	try                                                        //~9719I~//~0113M~
        {                                                          //~9719I~//~0113M~
//      AG.aCSI.onResume();	//set BTHandler activity               //~9B05I~//~va41R~
        AG.aCSI.onPause();                                         //~va41I~
        AG.aBTI.onPause();                                         //~@@@@I~
        UMediaStore.onPause();                                     //~1ak2R~
        Sound.onPause();                                           //~vaeaI~
	    WDI.onPause();		//unregister receiver                  //~0113I~
        super.onPause();                                           //~8B05I~
//    	listenerOrientationChanged.disable();    //TODO test       //~va9fR~
        swPaused=true;                                             //~9A22I~
//          registerEventBus(false);            //unregister at onDestroy//~8C30I~//~@@@@M~//~9719R~//~9A22R~
            if (AG.aGC!=null)                                          //~9101I~//~9719R~
                AG.aGC.onPause();                                          //~8B06I~//~9101R~//~9719R~
        	if (AG.aWDA!=null)                                     //~9719I~
				AG.aWDA.onPause();                                 //~9719I~
        	if (AG.aIPSubThread!=null)                             //~9A02I~
				AG.aIPSubThread.onPause();                         //~9A02I~
        }                                                          //~9719I~
        catch(Exception e)                                         //~9719I~
        {                                                          //~9719I~
        	Dump.println(e,"MainActivity.onPause");                //~9719I~//~vaf0R~
        }                                                          //~9719I~
    }                                                              //~8B05I~
	//*************************                                    //~8B05I~
    @Override                                                      //~8B05I~
    protected void onDestroy() {                                   //~8B05I~
        if(Dump.Y) Dump.println("MainActivity.onDestroy AG.status="+AG.status);         //~8B05I~//~vaf0R~
        if (AG.status!=AG.STATUS_STOPFINISH)                       //~vaf0I~
        {                                                          //~vaf0I~
//  		UView.showToastLongDirect(R.string.Err_AppDestroyedUnexpectedly);//~vaf0R~
    		issueDestroyedWarning();                               //~vaf0I~
        }                                                          //~vaf0I~
//        AG.popFragment();                                        //~vaf0R~
	    AG.aCSI.onDestroy();                                       //~9B05I~
//        registerEventBus(false);                                 //~va30R~
		if (AG.aGC!=null)                                          //~9101I~
	        AG.aGC.onDestroy();                                        //~8C03I~//~9101R~
		if (AG.aIPSubThread!=null)                                 //~9A02I~
	        AG.aIPSubThread.onDestroy();                           //~9A02I~
		if (AG.aSound!=null)                                       //~va06I~
	        AG.aSound.stopAll();                                   //~va06I~
        UMediaStore.onDestroy();                                   //~vae2I~
        if(Dump.Y) Dump.println("MainActivity.onDestroy call super.onDestroy");//~vaf0R~
        super.onDestroy();                                         //~8B05I~
//        if (true)                                                  //~9103I~//~9105R~
//        {                                                          //~9103I~//~9105R~
        	if (mainView!=null)                         //~9103I~  //~9620R~
            	mainView.reset();                       //~9103I~  //~9620R~
//        }                                                          //~9103I~//~9105R~
//        else                                                       //~9103I~//~9105R~
//        if (bmpTop!=null)                                          //~8C30I~//~9105R~
//        {                                                          //~8C30I~//~9105R~
//            UView.recycle(bmpTop);                                 //~9102R~//~9105R~
//            bmpTop=null;                                           //~8C30I~//~9105R~
//        }                                                          //~8C30I~//~9105R~
		StaticVars.onDestroy();                                    //~0216I~
		Dump.close();                                              //~vaebR~
        TestOption.swActivityDestroyed=true;	//notify to ITMainActivity//~vaf0I~
    }                                                              //~8B05I~
	//*************************                                    //~8B26I~
    @Override                                                      //~8B26I~
    public void onWindowFocusChanged(boolean PhasFocus)         //~8B26I~
    {                                                              //~8B26I~
        super.onWindowFocusChanged(PhasFocus);                     //~8B26I~
      try                                                          //~vakvI~
      {                                                            //~vakvI~
        if(Dump.Y) Dump.println("MainActivity.onWindowFocusChanged focus="+PhasFocus+",ww="+frameLayout.getWidth()+",hh="+frameLayout.getHeight());//~8B26I~//~vaeeR~//~vaf0R~
//        hideNavigationBar(true);    //done if portrait             //~8B26I~//~8C29R~
        if (PhasFocus)  //navigationbar reappear when dialog opend //~9511R~
        	hideNavigationBar(true);                               //~9511I~
      }                                                            //~vakvI~
      catch(Exception e)                                           //~vakvI~
      {                                                            //~vakvI~
        Dump.println(e,"onWindowFocusChanged");                    //~vakvI~
      }                                                            //~vakvI~
    }                                                              //~8B26I~
//*************************                                        //~8C03I~
    @Override                                                      //~8C03I~
    public void onBackPressed()                                    //~8C03I~
	{                                                              //~8C03I~
        onExit();                                                  //~8C03I~
    }                                                              //~8C03I~
	//***************************************************************                                    //~8B05I~//~8C29R~
	//*Manifest:screenOrienntation="portrait"                      //~8C29I~
	//***************************************************************//~8C29I~
    private void initMainView(View Pview)                               //~8B05I~//~8C29R~
    {                                                              //~8B05I~
        if (Dump.Y) Dump.println("MainActivity.initMainView frameLayout="+frameLayout.toString());//~9103I~
//      if (true)                                                    //~9103I~//~9104R~
//      {                                                            //~9103I~//~9104R~
      	mainView=new MainView(this,frameLayout);//~9103I~//~9104R~ //~9620R~
        mainView.init();                                //~9103I~  //~9620R~
//      }                                                            //~9103I~//~9104R~
//      else                                                         //~9103I~//~9104R~
//      {                                                            //~9103I~//~9104R~
//        setImage();                                                //~8C29I~//~8C30R~//~9103R~//~9104R~
//        addTitle();                                                //~8C29I~//~9104R~
//        addButtons();                                             //~8C29R~//~9104R~
//        bindButtons();                                             //~8C29I~//~9104R~
//        if ((TestOption.option & TestOption.TO_CONNECTED)!=0)   //TODO test//~8C30R~//~9104R~
//            setButtonStatus(true);                                 //~8C30I~//~9104R~
//        else                                                       //~8C30I~//~9104R~
//            setButtonStatus(false);                                     //~8C29I~//~8C30R~//~9104R~
//      }                                                            //~9103I~//~9104R~
    }                                                              //~8B05I~
//    //*************************                                    //~8C29I~//~9104R~
//    private void addTitle()                                        //~8C29I~//~9104R~
//    {                                                              //~8C29I~//~9104R~
//        int wc=ViewGroup.LayoutParams.WRAP_CONTENT;                //~8C29I~//~9104R~
//        int fp=ViewGroup.LayoutParams.FILL_PARENT;                 //~9103I~//~9104R~
//        FrameLayout.LayoutParams lp;                               //~8C29I~//~9104R~
//        lp=new FrameLayout.LayoutParams(fp,wc);                    //~8C29I~//~9103R~//~9104R~
//        titleMain=AG.inflater.inflate(MAIN_TITLE,null);            //~8C29I~//~9104R~
//        lp.gravity=Gravity.TOP|Gravity.LEFT;    //=layout_gravity  //~8C29I~//~9104R~
//        titleMain.setLayoutParams(lp);                             //~8C29I~//~9104R~
//        frameLayout.addView(titleMain);                            //~8C29I~//~9104R~
//    }                                                              //~8C29I~//~9104R~
	//*************************                                    //~8C30I~
    private void hideTitle(boolean Pswhide)                        //~8C30I~
    {                                                              //~8C30I~
        if (Dump.Y) Dump.println("MainActivity.hideTitle swHide="+Pswhide);//~8C30I~
      if (false)                                                   //~9103I~
      	titleMain.setVisibility(Pswhide ? View.GONE : View.VISIBLE);//~8C30I~//~9103R~
      else                                                         //~9103I~
      {                                                            //~9103I~
        if (Pswhide)                                               //~9103R~
            frameLayout.removeView(titleMain);                     //~9103R~
        else                                                       //~9103R~
            frameLayout.addView(titleMain);                        //~9103R~
      }                                                            //~9103I~
    }                                                              //~8C30I~
//    //*************************                                    //~8C29I~//~0119R~
//    private void bindButtons()                                     //~8C29I~//~0119R~
//    {                                                              //~8C29I~//~0119R~
//        btnSettings  =              UButton.bind(btnsMain,R.id.Settings,this);//~8C29I~//~0119R~
//        btnConnect   =              UButton.bind(btnsMain,R.id.Connect,this);//~8C29I~//~0119R~
////      btnStartGame =              UButton.bind(btnsMain,R.id.StartGame,this);//~8C29I~//~0119R~
//        btnHistory   =              UButton.bind(btnsMain,R.id.History,this);//~9613I~//~0119R~
//        btnHelp      =              UButton.bind(btnsMain,R.id.Help,this);//~8C29I~//~0119R~
//    }                                                              //~8C29I~//~0119R~
//    //*************************                                    //~8C29I~//~0119R~
//    private void setButtonStatus(boolean PswConnected)             //~8C29I~//~0119R~
//    {                                                              //~8C29I~//~0119R~
//        if (Dump.Y) Dump.println("MainActivity.setButtonStatus swconnected="+PswConnected);//~8C30I~//~0119R~
//        btnStartGame.setEnabled(PswConnected);                     //~8C29I~//~0119R~
//    }                                                              //~8C29I~//~0119R~
//    //*************************                                    //~8C29I~//~9105R~
//    private void setImage()                                        //~8C29I~//~8C30R~//~9103R~//~9105R~
//    {                                                              //~8C29I~//~9105R~
//        if (Dump.Y) Dump.println("MainActivity.setImage");         //~8C30I~//~9105R~
//        if (bmpTop==null)                                          //~8C30I~//~9105R~
//        {                                                          //~8C30I~//~9105R~
//            bmpTop=BitmapFactory.decodeResource(AG.resource,RID_TOPIMAGE);//~8C30I~//~9105R~
//        }                                                          //~8C30I~//~9105R~
//        imageView=new ImageView(this);                       //~8C29I~//~9103R~//~9105R~
////      imageView.setImageResource(RID_TOPIMAGE);                  //~8C29I~//~8C30R~//~9105R~
//        imageView.setImageBitmap(bmpTop);                          //~8C30I~//~9105R~
//        frameLayout.addView(imageView);                            //~8C29I~//~8C30R~//~9103R~//~9105R~
//    }                                                              //~8C29I~//~9105R~
//    //*************************                                    //~9103I~//~9105R~
//    private void hideImage(boolean PswHide)                        //~9103I~//~9105R~
//    {                                                              //~9103I~//~9105R~
//        if (Dump.Y) Dump.println("MainActivity.hideImage swHide="+PswHide);//~9103I~//~9105R~
//        if (PswHide)                                               //~9103I~//~9105R~
//            frameLayout.removeView(imageView);                         //~9103I~//~9105R~
//        else                                                       //~9103I~//~9105R~
//          if (false)                                               //~9103I~//~9105R~
//            frameLayout.addView(imageView);                        //~9103R~//~9105R~
//          else                                                     //~9103I~//~9105R~
//            setImage(); //set PFLAG_DRAW on to invalidate()        //~9103R~//~9105R~
//    }                                                              //~9103I~//~9105R~
//    //*************************                                    //~8C29I~//~9614R~
//    private void addButtons()                                      //~8C29R~//~9614R~
//    {                                                              //~8C29I~//~9614R~
//        if (Dump.Y) Dump.println("MainActivity.addButtons");       //~9103I~//~9614R~
//        int wc=ViewGroup.LayoutParams.WRAP_CONTENT;                //~8C29I~//~9614R~
//        int mp=ViewGroup.LayoutParams.MATCH_PARENT;                //~8C29I~//~9614R~
//        FrameLayout.LayoutParams lp;                               //~8C29I~//~9614R~
//        lp=new FrameLayout.LayoutParams(mp,wc);                    //~8C29R~//~9614R~
//        btnsMain=AG.inflater.inflate(MAIN_BUTTONS,null);           //~8C29R~//~9614R~
//        lp.gravity=Gravity.BOTTOM|Gravity.CENTER;    //=layout_gravity//~8C29I~//~9614R~
//        btnsMain.setLayoutParams(lp);                                  //~8C29I~//~9614R~
//        btnsMain.setVisibility(View.INVISIBLE);                    //~9103I~//~9614R~
//        btnsMain.setVisibility(View.VISIBLE);                      //~9103I~//~9614R~
//        frameLayout.addView(btnsMain);                             //~8C29R~//~9614R~
//        if (Dump.Y) Dump.println("MainActivity.addButtons btnsMain="+btnsMain.toString());//~9103I~//~9614R~
//    }                                                              //~8C29I~//~9614R~
	//*************************                                    //~8C29I~
    private void hideButtons(boolean Pswhide)                                     //~8C29I~//~8C30R~
    {                                                              //~8C29I~
        if (Dump.Y) Dump.println("MainActivity.hideButtons swHide="+Pswhide);//~8C30I~
//    if (false) //TODO test                                       //~9103I~//~0322R~
//		btnsMain.setVisibility(Pswhide ? View.GONE : View.VISIBLE);//~9103R~//~0322R~
//    else                                                         //~9103I~//~0322R~
      {                                                            //~9103I~
        if (Pswhide)                                               //~9103R~
            frameLayout.removeView(btnsMain);                      //~9103R~
        else                                                       //~9103R~
        {                                                          //~9103R~
            frameLayout.addView(btnsMain);                         //~9103R~
//  		addButtons();                                          //~9103R~
        }                                                          //~9103R~
      }                                                            //~9103I~
    }                                                              //~8C29I~
    //******************************************                   //~8B05I~
    @Override
    public void onClickButton(Button Pbutton)                      //~8B05I~
	{                                                              //~8B05I~
    	boolean rc=true;                                           //~8B05I~
        AG.aMainView.clearMsg();                                   //~9621I~
        if (Dump.Y) Dump.println("MainActivity.onClickButton"+Pbutton.getText());//~8B05I~//~vaf0R~
        int id=Pbutton.getId();                                    //~8B05I~
        switch(id)                                                 //~8B05I~
        {                                                          //~8B05I~
        case R.id.Settings:                                         //~8B05I~//~8C29R~
	        Status.resetEndgameSomeone();                          //~9B20I~
            onSetting();                                           //~9106I~//~@@@@R~
            break;                                             //~8B05I~//~8C29R~
        case R.id.Connect:                                         //~8B05I~//~8C29R~
    	    initHistory(false/*PswinitApp*/);	//may not have writable permission,init history by internal storage//~vae0I~
    	    Status.resetEndgameSomeone();                          //~9B20I~
            onConnect();                                           //~9106I~
            break;                                             //~8B05I~//~8C29R~
        case R.id.StartGame:                                       //~8C29I~
            if (Dump.Y) Dump.println("MainActivity.onClickButton:startGame config.orientation="+AG.resource.getConfiguration().orientation);//~va9fM~
    	    initHistory(false/*PswinitApp*/);	//may not have writable permission,init history by internal storage//~vae0R~
    	    Status.resetEndgameSomeone();                          //~9B20I~
    		AG.resumeHD=null;                                      //~9901I~
	    	if (AG.ruleSyncDate.equals(PROP_INIT_SYNCDATE))        //~0124I~
            {                                                      //~0124I~
            	MainView.drawMsg(R.string.Err_RuleIsInitial);      //~0124I~
                break;                                             //~0124I~
            }                                                      //~0124I~
//            if (!BTMulti.isServerDevice())                       //~va17R~
//            {                                                    //~va17R~
//                MainView.drawMsg(R.string.Err_GameFromNotServer);//~va17R~
//                break;                                           //~va17R~
//            }                                                    //~va17R~
			if (!chkRobotGame())                                   //~0119I~
            	break;                                      //~0119I~
        	startGame(true/*chk setting*/);                                           //~8C30R~//~9101R~
            break;                                                 //~8C29I~
        case R.id.History:                                         //~9613I~
    	    initHistory(false/*PswinitApp*/);	//may not have writable permission,init history by internal storage//~vae0R~
    	    Status.resetEndgameSomeone();                          //~9B20I~
        	gameHistory();                                         //~9613I~
            break;                                                 //~9613I~
        case R.id.Help:                                         //~8B07I~//~8C29R~
        	onClickHelp();                                         //~8C29R~
            break;                                             //~8B07I~//~8C29R~
        case R.id.Test1:      //TODO test                          //~@@@@I~
			if (Dump.Y) Dump.println("MainActivity.showDlgComp");  //~@@@@I~
	    	if ((TestOption.option2 & TestOption.TO2_LAYOUT_FINALGAME)!=0) //TODO TEST//~9520I~
            {                                                      //~9520I~
        	    TestLayout.newInstance().show();                //~9520I~//~0316R~
            }                                                      //~9520I~
//            else                                                   //~9520I~//~0316R~
//            if ((TestOption.option & TestOption.TO_SCORE_LAYOUT)!=0) //TODO TEST//~@@@@I~//~0316R~
//            {                                                      //~@@@@I~//~0316R~
//                ScoreDlg.newInstance(EGDR_NORMAL,new int[PLAYERS],new int[PLAYERS]).show();     //~@@@@I~//~0316R~
//            }                                                      //~@@@@I~//~0316R~
//            else                                                   //~@@@@I~//~0316R~
//            if ((TestOption.option & TestOption.TO_DRAWNLAST_LAYOUT)!=0) //TODO TEST//~@@@@I~//~0316R~
//            {                                                      //~@@@@I~//~0316R~
//                new UAEndGame();                                   //~@@@@I~//~0316R~
//                DrawnDlgLast.newInstance(0,0,new int[PLAYERS],new int[PLAYERS],new int[PARMPOS_DRAWN_DIALOGDATA_CTR]).show();                 //~@@@@I~//~0316R~
//            }                                                      //~@@@@I~//~0316R~
//            else                                                   //~9425I~//~0316R~
//            if ((TestOption.option & TestOption.TO_DRAWNDLGHW_LAYOUT)!=0) //TODO TEST//~9425I~//~0316R~
//            {                                                      //~9425I~//~0316R~
//                new UAEndGame();                                   //~9425I~//~0316R~
//                DrawnDlgHW.newInstance(EGDR_99TILE,NGTP_NEXT,new int[]{1,1,1,1},0).show();//~9425R~//~9705R~//~0316R~
//            }                                                      //~9425I~//~0316R~
//            else                                                   //~@@@@I~//~0316R~
//            if ((TestOption.option & TestOption.TO_DRAWNREQDLGHW_LAYOUT)!=0) //TODO TEST//~@@@@I~//~0316R~
//            {                                                      //~@@@@I~//~0316R~
//                new UAEndGame();                                   //~@@@@I~//~0316R~
//                DrawnReqDlgHW.newInstance().show();                //~@@@@R~//~9426R~//~0316R~
////              DrawnDlgHW.newInstance().show();                   //~@@@@I~//~9426R~//~0316R~
//            }                                                      //~@@@@I~//~0316R~
//            else                                                   //~@@@@I~//~0316R~
//            if ((TestOption.option & TestOption.TO_COMPREQDLG_LAYOUT_SHOW)!=0) //TODO TEST//~@@@@I~//~0316R~
//            {                                                      //~@@@@I~//~0316R~
//                CompReqDlg.newInstance(new Complete().new Status(0,0,0,null,null)).show();//~@@@@R~//~0316R~
//            }                                                      //~@@@@I~//~0316R~
//            else                                                   //~@@@@I~//~0316R~
//            if ((TestOption.option & TestOption.TO_DRAWNREQDLG_LAYOUT)!=0) //TODO TEST//~@@@@R~//~0316R~
//            {                                                      //~9413I~//~0316R~
//                if (AG.aUAEndGame==null)                           //~9413I~//~0316R~
//                    new UAEndGame();                               //~9413I~//~0316R~
//                DrawnReqDlgLast.newInstance().show();                  //~@@@@R~//~0316R~
//            }                                                      //~9413I~//~0316R~
//            else                                                   //~@@@@I~//~0316R~
//            if ((TestOption.option & TestOption.TO_DRAWNDLG_LAYOUT)!=0) //TODO TEST//~@@@@I~//~0316R~
//                CompleteDlg.newInstance().show();                  //~@@@@I~//~0316R~
//            else                                                   //~@@@@I~//~0316R~
//            if ((TestOption.option & TestOption.TO_COMPDLG_LAYOUT)!=0) //TODO TEST//~@@@@R~//~0316R~
//                CompleteDlg.newInstance().show();                  //~@@@@M~//~0316R~
//            else                                                   //~@@@@I~//~0316R~
//            if ((TestOption.option & TestOption.TO_COMPREQDLG_LAYOUT)!=0) //TODO TEST//~@@@@I~//~0316R~
//                CompReqDlg.newInstance(AG.aComplete.new Status(0,0,0,null,null)).show();//~@@@@I~//~0316R~
////          CompNotenDlg.newInstance().show();                     //~@@@@R~//~0316R~
            break;                                                 //~@@@@I~
        }                                                          //~8B05I~
    }                                                              //~8B05I~
//**********************************************************       //~8B05I~
//*finish process                                                  //~8B05I~
//*from Utils.stopFinish<--Alert.exit reply                        //~vaf0I~
//**********************************************************       //~8B05I~
    public void destroyClose()                                     //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
    {                                                              //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
        if (Dump.Y) Dump.println("MainActivity:destroyClose");//~@@@@I~//~@@@2I~//~@@@@I~//~v@@@R~//~@@@@I~
        AG.status=AG.STATUS_STOPFINISH;                               //~@@@@I~//~v@@@I~//~@@@@I~
	    URunnable.setRunFunc(this/*RunnableI*/,0/*sleep*/,null/*parm*/,0/*phase*/);//~@@@@R~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
    }                                                              //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
//*************************                                        //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
//*callback from Runnable *                                        //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
//*************************                                        //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
    public void URunnableCB(Object Pparmobj,int Pphase)                //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
    {                                                              //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
        int wait1=100;                                   //~@@@2I~//~@@@@R~//~v@@@R~//~@@@@R~
        int wait2=1000;                                            //~@@@@I~
    //*********************                                        //~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
    	if (Dump.Y) Dump.println("MainActivity:URunnableCB phase="+Pphase);   //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@R~//~@@@@I~
    	if (Pphase==0)	//initial call,close socket streamIO       //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
        {                                                          //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
	    	if (Dump.Y) Dump.println("MainActivity:URunnableCB phase=0 closeStream");//~@@@@I~//~@@@2I~//~@@@@I~//~v@@@R~//~@@@@I~
        	if (AG.aBTI==null)                        //~@@@2I~//~@@@@I~//~@@@2I~//~@@@@I~//~v@@@R~//~@@@@I~
            	return;                                            //~v@@@I~//~@@@@I~
            AG.aBTMulti.closeStream();                             //~v@@@R~//~@@@@I~
            if (AG.aUADelayed!=null)                               //~@@@@I~
	            AG.aUADelayed.onDestroy();                         //~@@@@I~
		    URunnable.setRunFunc(this/*RunnableI*/,wait1/*sleep ms*/,null/*parm*/,1/*phase*/);//~@@@@R~//~@@@2I~//~@@@@R~//~v@@@I~//~@@@@R~
	        return;                                                //~@@@@R~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
        }                                                          //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
    	if (Pphase==1)	//stop timer,board                         //~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
        {                                                          //~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
    		if (Dump.Y) Dump.println("MainActivity:URunnableCB runfunc phase=1 stop BT");//~@@@@I~//~v@@@R~//~@@@@I~
    	    AG.aBTMulti.destroy();                                      //~@@@@R~//~@@@2I~//~@@@@I~//~v@@@R~//~@@@@I~
            URunnable.setRunFunc(this/*RunnableI*/,wait2/*sleep ms*/,null/*parm*/,2/*phase*/);//~@@@2I~//~@@@@I~//~v@@@R~//~@@@@I~
            return;                                                //~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
        }                                                          //~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
    	if (Dump.Y) Dump.println("MainActivity:uRunnableCB runfunc phase=2"); //~@@@@I~//~@@@2I~//~@@@@R~//~v@@@R~//~@@@@R~
		Utils.finish();	//shedule ondestroy                            //~@@@2I~//~@@@@I~//~v@@@I~//~@@@@R~
    }                                                              //~@@@@I~//~@@@2I~//~@@@@I~//~v@@@I~//~@@@@I~
//**********************************************************       //~8C03I~//~@@@@M~
    private void onExit()                                          //~8C03I~//~@@@@M~
    {                                                              //~8C03I~//~@@@@M~
        if (Dump.Y) Dump.println("MainActivity.onExit");           //~9B06I~
    	if (GC.isOnGameView())                                     //~9B06I~
        {                                                          //~9B06I~
//  		UView.showToast(R.string.Err_GamingNow);               //~9B06I~//~0411R~
    		AG.aGC.exitOnGameView();                               //~0411I~
        	return;                                                //~9B06I~
        }                                                          //~9B06I~
    	int flag= BUTTON_POSITIVE|Alert.BUTTON_NEGATIVE|Alert.EXIT;//~8C03I~//~@@@@M~
        Alert.showAlert(null/*title*/,Utils.getStr(R.string.Qexit),flag,null/*callback*/);//~8C03I~//~@@@@M~
        saveProp();                                                //~vae8I~
    }                                                              //~8C03I~//~@@@@M~
    //******************************************                   //~8C29I~
    protected void onClickHelp()                                   //~8C29I~
	{                                                              //~8C29I~
        if (Dump.Y) Dump.println("MainActivity.onClickHelp");      //~8C29I~
//  	HelpDialog.newInstance(HELP_TITLEID,HELPFILE).show(); //~8C29I~//~9C13R~//~vaikR~
    	HelpDialog.newInstance(HELP_TITLEID,HELPFILE).showPlaylist(PLAYLIST_ID);//~vaikR~
    }                                                              //~8C29I~
//**********************************************************       //~8930I~//~8C30I~
//*toast on main thread from UView                                 //~v@@@R~//~8C30I~
//**********************************************************       //~8930I~//~8C30I~
//    private void registerEventBus(boolean Pswon)                 //~va30R~
//    {                                                            //~va30R~
//        if (Dump.Y) Dump.println("registerEventBus sw="+Pswon);  //~va30R~
//        if (Pswon)                                               //~va30R~
//        {                                                        //~va30R~
//            if(!EventBus.getDefault().isRegistered(this))              //~8930I~//~va30R~
//                EventBus.getDefault().register(this);                  //~8930I~//~va30R~
//        }                                                        //~va30R~
//        else                                                     //~va30R~
//            EventBus.getDefault().unregister(this);                    //~8930I~//~va30R~
//    }                                                            //~va30R~
    public void onEventMainThread(EventToast Pevent)               //~8930I~//~8C30I~
    {                                                              //~8930I~//~8C30I~
    	Pevent.showToast();                                         //~8930I~//~v@@@R~//~8C30I~
    }                                                              //~8930I~//~8C30I~
//**********************************************************       //~9A22I~
    private boolean isEventAcceptable(EventCB Pevent)              //~9A22I~
    {                                                              //~9A22I~
		if (Dump.Y) Dump.println("MainActivity.isEventAcceptable");//~9A22I~
    	boolean rc;
        if (swStopped)                                             //~9A22I~
        {                                                          //~9A22I~
        	rc=false;                                              //~9A22I~
    		if (Pevent.action==EventMsg.ACTION_GETCTLMSG)          //~9A22I~
            {                                                      //~9A22I~
        		int msgid=Pevent.getParmInt1();                    //~9A22I~
			    if (msgid==MSGID_IOERR)                            //~9A22I~
                {                                                  //~9A22I~
			        if (Dump.Y) Dump.println("MainActivity.isEventAcceptable issue moveAppToFront");//~9A22I~
	            	Utils.moveAppToFront();                        //~9A22I~
    	            swIOErr=true;                                  //~9A22I~
        	        msgIOErr=Pevent.getParmStr();                 //~9A22I~
                }                                                  //~9A22I~
            }                                                      //~9A22I~
        }                                                          //~9A22I~
        else                                                       //~9A22I~
        	rc=true;                                               //~9A22I~
        if (Dump.Y) Dump.println("MainActivity.isEventAcceptable="+rc);//~9A22I~
        return rc;
    }                                                              //~9A22I~
//**********************************************************       //~v@@@I~//~8C30I~
//*Callback request                                                //~v@@@I~//~8C30I~
//**********************************************************       //~v@@@I~//~8C30I~
    public void onEventMainThread(EventCB Pevent)                  //~v@@@R~//~8C30I~
    {                                                              //~v@@@I~//~8C30I~
        if(Dump.Y) Dump.println("MainActivity.onEventMainThread eventCB action="+Pevent.action);//~v@@@I~//~8C30I~//~vaf0R~
        if(Dump.Y) Dump.println("MainActivity.onEventMainThread swPaused="+swPaused+",swStopped="+swStopped);//~9A22R~//~vaf0R~
        try                                                        //~9901I~
        {                                                          //~9901I~
            if (!isEventAcceptable(Pevent))                              //~9A22I~
            	return;                                            //~9A22I~
    	switch(Pevent.action)                                      //~v@@@I~//~8C30I~
        {                                                          //~v@@@I~//~8C30I~
        case EventMsg.ACTION_SETTINGS:  //=1                            //~v@@@I~//~8C30I~//~9A24R~
//      	BTCDialog.callSettingsBT();                             //~v@@@I~//~8C30I~
        	break;                                                 //~v@@@I~//~8C30I~
        case EventMsg.ACTION_GETCTLMSG: //=2                            //~v@@@R~//~8C30I~//~9A24R~
        	receivedCtlMsg(Pevent);                                //~v@@@R~//~8C30I~//~@@@@R~
        	break;                                                 //~v@@@I~//~8C30I~
        case EventMsg.ACTION_PUTCTLMSG: //=3                             //~v@@@I~//~8C30I~//~9A24R~
//      	sendCtlMsg(Pevent);                                 //~v@@@I~//~8C30I~
        	break;                                                 //~v@@@I~//~8C30I~
//        case EventMsg.ACTION_RECEIVED:                             //~v@@@R~//~8C30I~//~@@@@R~
//      	receivedAppMsg(Pevent);                                //~v@@@R~//~8C30I~
//            break;                                                 //~v@@@I~//~8C30I~//~@@@@R~
        case EventMsg.ACTION_SEND:      //=5                            //~v@@@I~//~8C30I~//~9A24R~
//      	sendAppMsg(Pevent);                                    //~v@@@I~//~8C30I~
        	break;                                                 //~v@@@I~//~8C30I~
        case ECB_ACTION_ENDGAME:                                   //~8C30R~
        	endGame(false/*configchange*/);                                             //~8C30I~//~9102R~
	        AG.aMainView.showConnectStatus();                          //~vac5R~//~vajiI~
        	break;                                                 //~8C30I~
//        case ECB_INVALIDATE:      use postinvalidate                                 //~9102I~//~9103R~
//            AG.aGC.invalidate();                                   //~9102I~//~9103R~
//            break;                                                 //~9102I~//~9103R~
//        case ECB_RESTOREMAIN:      //use postinvalidate              //~9103I~//~9105R~
//            restoreMainView();                                     //~9103I~//~9105R~
//            break;                                                 //~9103I~//~9105R~
//        case ECB_ADD_GCVIEW:                                       //~8C30I~//~8C31R~
//            addGCView();                                           //~8C30I~//~8C31R~
//            break;                                                 //~8C30I~//~8C31R~
        case ECB_ORIENTATION: //from orientationmenudlg            //~9101R~
            changeOrientation(Pevent);                             //~9101R~
            break;                                                 //~9101I~
        case ECB_ACTION_STARTGAME: 	//from BTIOT                   //~9901R~
            syncDateOK();                                          //~9621I~//~9901R~
            break;                                                 //~9621I~
        case ECB_ACTION_RESUMEGAME: 	//from ResumeDlg           //~9901I~
            resumeGame();                                          //~9901I~
            break;                                                 //~9901I~
//        case ECB_COMPDLG_RESP:                                     //~@@@@R~
//            CompReqDlg.repaintUI(Pevent);                          //~@@@@I~
//            break;                                                 //~@@@@I~
//        case ECB_COMPRESULT_RESP:                                //~@@@@R~
//            CompleteDlg.repaintUI(Pevent);                       //~@@@@R~
//            break;                                               //~@@@@R~
//        case ECB_DRAWNHW_RESP:                                   //~@@@@R~
//            DrawnDlgHW.repaintUI(Pevent);                        //~@@@@R~
//            break;                                               //~@@@@R~
        case ECB_ACTION_REACH: 	//from ResumeDlg                   //~9A31I~
            updateButtonStatusReach(Pevent);                      //~9A31I~
            break;                                                 //~9A31I~
                             //~@@@@I~
        }                                                          //~v@@@I~//~8C30I~
        }                                                          //~9901I~
        catch(Exception e)                                         //~9901I~
        {                                                          //~9901I~
        	Dump.println(e,"onEvendMainThread");                   //~9901I~
        }                                                          //~9901I~
        if(Dump.Y) Dump.println("MainActivity.onEventMainThread eventCB return");//~v@@@I~//~8C30I~//~vaf0R~
    }                                                              //~v@@@I~//~8C30I~
//**********************************************************       //~8C30I~
	private void startGame(boolean PswChkSetting)                                       //~8C30I~//~9101R~
    {                                                              //~8C30I~
        if (Dump.Y) Dump.println("MainActivity.startGame swchksetting="+PswChkSetting);//~9901I~
		startGame(PswChkSetting,false/*swResume*/);                //~9901I~
    }                                                              //~9901I~
//**********************************************************       //~9901I~
	private void startGame(boolean PswChkSetting,boolean PswResume)//~9901I~
    {                                                              //~9901I~
        if (Dump.Y) Dump.println("MainActivity.startGame swchksetting="+PswChkSetting+",swResume="+PswResume);       //~8C30I~//~9101R~//~9901R~
        chngOrientation=0;                                         //~9101I~
        if (PswChkSetting)                                         //~9101I~
        {                                                          //~9101I~
        	if (frameLayoutSizePortrait==null)                     //~9411I~
        		frameLayoutSizePortrait=UView.getMeasuredSize(frameLayout);	//initially portrait//~9411I~
	        if (Dump.Y) Dump.println("MainActivity.startGame frameLayoutSizePortrait="+frameLayoutSizePortrait.toString());//~9411I~
//          if (!chkMember())                                      //~@@@@I~//~9621R~
//      	if (RuleSetting.chkChangedRule())                          //~@@@@I~//~9406R~//~9620R~
         if (!AG.swTrainingMode)                                      //~va66I~
         {                                                         //~va66I~
          if (!PswResume)                                          //~9901I~
        	if (!chkRuleSync(true/*swMsg*/))                       //~9620R~
            	return;   //rule not synched                                         //~@@@@I~//~9406R~
         }                                                         //~va66I~
//  		if (!createAccounts())                                 //~9621R~
//          	return;   //rule not synched                       //~9621R~
            if (!chkMember())                                      //~9621I~
            	return;                                            //~9621I~
//      	changeOrientation(OperationSetting.orientation);       //~9101I~//~9412R~
        	changeOrientation(PrefSetting.getOrientation());         //~9412I~
        	if (chngOrientation!=0)                                //~9101I~
            	return;                                            //~9101I~
        }                                                          //~9101I~
        if (!AG.portrait)                                          //~9411I~
        {                                                          //~9411I~
        	if (frameLayoutSizeLandscape==null)                                 //~9104I~//~9411I~
            {                                                      //~9411I~
        	//  frameLayoutSizeLandscape=UView.getMeasuredSize(frameLayout); //not yet updated//~9411R~
        	    frameLayoutSizeLandscape=new Point(AG.scrWidth,frameLayoutSizePortrait.x);//display hight such as (1280,800)//~9411I~
            }                                                      //~9411I~
	        if (Dump.Y) Dump.println("MainActivity.startGame frameLayoutSizeLandscape="+frameLayoutSizeLandscape.toString());//~9411I~
        }                                                          //~9411I~
//    	mainView.hide(frameLayoutSizePortrait);                                //~9103I~//~9104R~//~9411R~//~9620R~
      	mainView.hideTopView(AG.portrait ? frameLayoutSizePortrait : frameLayoutSizeLandscape);//~9411R~//~9620R~
//      orientationBeforeGame=AG.resource.getConfiguration().orientation;//~va9fR~
	    new GC(frameLayout);    //game controler               //~9101I~//~@@@@R~
        AG.aGC.startGame();                                        //~8C30R~
    }                                                              //~8C30I~
//**********************************************************       //~9621I~
	private void startGameClient()                                 //~9621I~
    {                                                              //~9621I~
        if (Dump.Y) Dump.println("MainActivity.startGameClient");//~9621I~
        chngOrientation=0;                                         //~9621I~
//      if (PswChkSetting)                                         //~9621I~
//      {                                                          //~9621I~
        	if (frameLayoutSizePortrait==null)                     //~9621I~
        		frameLayoutSizePortrait=UView.getMeasuredSize(frameLayout);	//initially portrait//~9621I~
	        if (Dump.Y) Dump.println("MainActivity.startGame frameLayoutSizePortrait="+frameLayoutSizePortrait.toString());//~9621I~
//      	if (!chkRuleSync(true/*swMsg*/))                       //~9621I~
//          	return;   //rule not synched                       //~9621I~
//          if (!chkMember())                                      //~9621I~
//          	return;                                            //~9621I~
            Accounts.createInstance();                             //~9621I~
        	changeOrientation(PrefSetting.getOrientation());       //~9621I~
        	if (chngOrientation!=0)                                //~9621I~
            	return;                                            //~9621I~
//      }                                                          //~9621I~
		startGame(false/*PswChkSetting*/);                         //~9621I~
    }                                                              //~9621I~
//**********************************************************       //~9A31I~
	private void updateButtonStatusReach(EventCB Pevent)           //~9A31I~
    {                                                              //~9A31I~
    	int actionid=Pevent.getParmInt1();                         //~9A31I~
        if (Dump.Y) Dump.println("MainActivity.updateButtonStatusReach actionid="+actionid);//~9A31I~
		AG.aGC.updateButtonStatusReach(actionid);                  //~9A31I~
    }                                                              //~9A31I~
//**********************************************************       //~9101I~
	public void changeOrientation(EventCB Pevent)                  //~9101I~
    {                                                              //~9101I~
    	int ori=Pevent.getParmInt1();                              //~9101I~
        if (Dump.Y) Dump.println("MainActivity.changeOrientation Event ori="+ori);//~9101I~
		changeOrientation(ori);                                    //~9101I~
        if (chngOrientation==0) //no need to change orientation    //~9102I~
        	startGame(false/*chk setting*/);                       //~9102I~
    }                                                              //~9101I~
//**********************************************************       //~9101I~
	public void changeOrientation(int PrequestedOrientation)        //~9101I~
    {                                                              //~9101I~
        int rc=0;                                                  //~9101I~
        int curOri=AG.resource.getConfiguration().orientation;     //~9610I~
        if (Dump.Y) Dump.println("MainActivity.changeOrientation swPortrait="+AG.portrait+",req="+PrequestedOrientation+",current="+curOri);//~9610I~//~va66R~//~va9fR~
        switch(PrequestedOrientation)                              //~9101I~
        {                                                          //~9101I~
        case PS_ORIENTATION_PORTRAIT:                              //~9101I~//~9412R~
//          if (AG.portrait)                                       //~9610R~//~va9fR~
//          if (curOri==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)  //~9610R~
//          if (curOri==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)  //~va9fR~
            if (AG.portrait)                                       //~va9fI~
                break;                                             //~9101I~
            requestChangeOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//~9101I~
            rc=1;                                                  //~9101I~
            break;                                                 //~9101I~
        case PS_ORIENTATION_PORTRAIT_REVERSE:                      //~9610I~
//            if (curOri==ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT)//~9610R~
//                break;                                           //~9610R~
//            if (curOri==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//~9610R~
//            {                                                    //~9610R~
//                requestChangeOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);    //reverse dose not fire onconfiguration changed,so once change to landscape//~9610R~
//                reverseOrientation=PrequestedOrientation;        //~9610R~
//            }                                                    //~9610R~
//            else                                                 //~9610R~
//            {                                                    //~9610R~
//                requestChangeOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);//~9610R~
//                reverseOrientation=-1;                           //~9610R~
//            }                                                    //~9610R~
//          if (AG.portrait)   //could not change standard<-->reverse//~9610I~//~va9fR~
//          if (curOri==ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT)//~va9fR~
            if (AG.portrait)   //could not change standard<-->reverse//~va9fI~
                break;                                             //~9610I~
            requestChangeOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);//~9610I~
            rc=1;                                                  //~9610I~
            break;                                                 //~9610I~
        case PS_ORIENTATION_LANDSCAPE:                             //~9101I~//~9412R~
//          if (!AG.portrait)                                      //~9101R~//~9610R~//~va9fR~
//          if (curOri==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) //~9610R~
//          if (curOri==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) //~va9fR~
            if (!AG.portrait)                                      //~va9fI~
                break;                                             //~9101I~
            requestChangeOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//~9101I~
            rc=1;                                                  //~9101I~
            break;                                                 //~9101I~
        case PS_ORIENTATION_LANDSCAPE_REVERSE:                     //~9610I~
//            if (curOri==ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE)//~9610R~
//                break;                                           //~9610R~
//            requestChangeOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);//~9610R~
//          if (!AG.portrait)                                      //~9610I~//~va9fR~
//          if (curOri==ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE)//~va9fR~
            if (!AG.portrait)                                      //~va9fI~
                break;                                             //~9610I~
            requestChangeOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);//~9610I~
            rc=1;                                                  //~9610I~
            break;                                                 //~9610I~
        default:                                                   //~9101I~
            showOrientationMenuDlg();                              //~9101I~
            rc=2;                                                  //~9101I~
            break;                                                 //~9101I~
        }                                                          //~9101I~
        chngOrientation=rc;                                        //~9101I~
        if (Dump.Y) Dump.println("MainActivity.changeOrientation rc="+rc);//~9610I~
    }                                                              //~9101I~
	//*************************************************************************//~9101I~
    public void requestChangeOrientation(int Porientation)         //~9101I~
    {                                                              //~9101I~
        if (Dump.Y) Dump.println("MainActivity.requestChangeOrientation setRequestOrientation Activiy orientation="+Porientation);//~9101I~//~va9fR~
        setRequestedOrientation(Porientation);                     //~9101I~
    }                                                              //~9101I~
//*************************                                        //~1120I~//~9101I~
    @Override                                                      //~1120I~//~9101I~
    public void onConfigurationChanged(Configuration Pcfg)         //~1120I~//~9101I~
	{                                                              //~1120I~//~9101I~
        if (Dump.Y) Dump.println("MainActivity.onConfigurationChanged swEndGame="+swEndGame+",curOri="+AG.resource.getConfiguration().orientation);//~9610R~//~va66R~
        if (Dump.Y) Dump.println("MainActivity.onConfigurationChanged getConfig="+AG.resource.getConfiguration().toString());//~9610I~
        if (Dump.Y) Dump.println("MainActivity.onConfigurationChanged Pcfg="+Pcfg.toString());//~9610I~
        if (Dump.Y) Dump.println("MainActivity.onConfigurationChanged chngOrientation="+chngOrientation);//~vamcR~
        super.onConfigurationChanged(Pcfg);                        //~1120I~//~1413R~//~9101I~
//      if (Dump.Y) Dump.println("MainActivity.onConfigurationChanged reverseOrientation="+reverseOrientation);        //~1120I~//~1513R~//~9101R~//~9610R~
//      if (reverseOrientation!=-1)                                //~9610R~
//      {                                                          //~9610R~
//          changeOrientation(reverseOrientation);                 //~9610R~
//          reverseOrientation=-1;                                 //~9610R~
//      }                                                          //~9610R~
//      else                                                       //~9610R~
    	try                                                        //~1513I~//~9101I~
        {                                                          //~1513I~//~9101I~
            UView.getScreenSize();                             //~1513R~//~v107R~//~@@@@R~//~9101I~
	        if (chngOrientation!=0)                                //~9101I~
            {                                                      //~9101I~
            	if (swEndGame)                                     //~9102I~
                {                                                  //~9102I~
//                  if (true) //TODO test                            //~9103I~//~9104R~
//                  {                                                //~9103I~//~9104R~
                    hideNavigationBar(false);                      //~9102I~//~9103R~//~9104R~
					endGame(true/*PswConfigChanged*/);             //~9102I~
//                  }                                                //~9103I~//~9104R~
                }                                                  //~9102I~
                else                                               //~9102I~
                {                                                  //~9102I~
		        	AG.aGC=null;    //new instance at startGame        //~9101I~//~9102R~
//                  hideNavigationBar(false);//TODO test           //~9807R~
                    hideNavigationBar(true);                       //~9102I~
					startGame(false);                                  //~9101I~//~9102R~
                }                                                  //~9102I~
            }                                                      //~9101I~
        }                                                          //~1513I~//~9101I~
        catch(Exception e)                                         //~1513I~//~9101I~
        {                                                          //~1513I~//~9101I~
        	Dump.println(e,"onConfiurationChanged");               //~1513I~//~9101I~
        }                                                          //~1513I~//~9101I~
        chngOrientation=0;                                         //~9102I~
        swEndGame=false;                                           //~9102I~
	}                                                              //~1120I~//~9101I~
	//*************************************************************************//~9101I~
    public void showOrientationMenuDlg()                        //~9101I~
    {                                                              //~9101I~
        if (Dump.Y) Dump.println("MainActivity.showOrientationMenuDialog");//~9101I~
        new OrientationMenuDlg().show();                       //~9101I~
    }                                                              //~9101I~
////**********************************************************       //~8C30I~//~9104R~
////*after startGame, after GCView was setup, add to frameview on UI thread//~8C30I~//~9104R~
////**********************************************************       //~8C30I~//~9104R~
//    private void addGCView()                                       //~8C30I~//~9104R~
//    {                                                              //~8C30I~//~9104R~
//        if (Dump.Y) Dump.println("MainActivity.addGCView");        //~8C30I~//~9104R~
//        if (AG.aGC!=null)                                          //~9101I~//~9104R~
//            AG.aGC.addGCView();                                        //~8C30I~//~9101R~//~9104R~
//    }                                                              //~8C30I~//~9104R~
//**********************************************************       //~8C30I~
	private void endGame(boolean PswConfigChanged)                                         //~8C30I~//~9102R~
    {                                                              //~8C30I~
        if (Dump.Y) Dump.println("MainActivity.endGame configchanged="+PswConfigChanged+",AG.portrait="+AG.portrait);          //~8C30I~//~9102R~//~9610R~//~va9fR~
        if (AG.savePropForResume!=null)                            //~vae5R~
        {                                                          //~vae5I~
        	AG.ruleProp=AG.savePropForResume;                      //~vae5R~
        	AG.savePropForResume=null;                             //~vae5R~
        }                                                          //~vae5I~
//      UView.getMeasuredSize(frameLayout); //TODO test            //~9105I~//~0322R~
        if (!PswConfigChanged)                                     //~9102I~
        {                                                          //~9102I~
            if (!AG.portrait)                                      //~9102I~
            {                                                      //~9102I~
            	swEndGame=true;                                    //~9102I~
//                hideNavigationBar(false);//TODO test               //~9103I~//~9104R~
//  	    	restoreMainView();                                 //~9103I~
//      	  if (PrefSetting.getOrientation()==PS_ORIENTATION_PORTRAIT_REVERSE)//~9610I~//~va9fR~
//              changeOrientation(PS_ORIENTATION_PORTRAIT_REVERSE);//~9610I~//~va9fR~
//            else                                                 //~9610I~//~va9fR~
//              changeOrientation(PS_ORIENTATION_PORTRAIT);        //~9102I~//~va9fR~
//  			requestChangeOrientation(TOP_ORIENTATION);         //~va9fR~
//  			requestChangeOrientation(orientationBeforeGame);   //~va9fR~
//      	  if (orientationBeforeGame==ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT)//~va9fR~
    			requestChangeOrientation(TOP_ORIENTATION);         //~va9fI~
		        chngOrientation=1;                                 //~va9fI~
                return;                                            //~9102I~
            }                                                      //~9102I~
        }                                                          //~9102I~
        else                                                       //~9102I~
        {                                                          //~9103I~
		    setFullscreen(false/*avoid requestFeature*/);          //~9103R~
//          UView.fixOrientation(true);                            //~v@@@I~//~9102I~//~va9fR~
//            if (false)                                           //~9105R~
//            {                                                    //~9105R~
//            new EventCB(ECB_RESTOREMAIN).postEvent();               //~v@21R~//~9103I~//~9105R~
//            return;                                                //~9103I~//~9105R~
//            }                                                    //~9105R~
        }                                                          //~9103I~
    	restoreMainView();                                          //~9103I~
//      Sound.playBGM(SOUNDID_BGM_TOP);                            //~va06R~//~vae9R~
        Sound.playBGMTop();                                        //~vae9I~
//        if (PswConfigChanged)                                    //~9103R~
//        {                                                        //~9103R~
//            if (Dump.Y) Dump.println("MainActivity.endGame postInvalidate");//~9103R~
//            frameLayout.postInvalidate();                        //~9103R~
//        }                                                        //~9103R~
//      UView.setWillNotDraw(frameLayout,false);                   //~9102I~//~9103R~
    }                                                              //~8C30I~
//**********************************************************       //~9103I~
    private void restoreMainView()                                 //~9103I~
    {                                                              //~9103I~
        if (Dump.Y) Dump.println("MainActivity.restoreMainView swMainView="+AG.swMainView);  //~9103I~//~9815R~
        AG.swMainView=true;                                        //~9815I~
//      if (true)                                                    //~9103I~//~9104R~
//    	mainView.restore();                             //~9103R~//~9411R~//~9620R~
      	mainView.restore(frameLayoutSizePortrait);      //~9411I~  //~9620R~
//      else                                                         //~9103I~//~9104R~
//      {                                                            //~9103I~//~9104R~
//        hideImage(false);                            //~8C30R~     //~9103R~//~9104R~
//        hideTitle(false/*hide*/);                                  //~8C30I~//~9103M~//~9104R~
//        hideButtons(false/*hide*/);                                //~8C30I~//~9103M~//~9104R~
//        frameLayout.setVisibility(View.INVISIBLE);                 //~9103R~//~9104R~
//        frameLayout.setVisibility(View.VISIBLE);                   //~9103I~//~9104R~
//        frameLayout.invalidate();                                  //~9103M~//~9104R~
//        AG.mainView.invalidate();                                  //~9103I~//~9104R~
//      }                                                            //~9103I~//~9104R~
    }                                                              //~9103I~
//**********************************************************       //~9101I~
//* staric valiable remains after Destry,need to reset at createActivity//~9101I~
//**********************************************************       //~9101I~
//    private void resetStatic()                                     //~9101I~//~9106R~
//    {                                                              //~9101I~//~9106R~
//        if (Dump.Y) Dump.println("MainActivity.resetStatic");      //~9101I~//~9106R~
//        Pieces.resetStatic();                                      //~9101I~//~9106R~
//    }                                                              //~9101I~//~9106R~
	//*************************                                    //~va9fI~
	@TargetApi(30)                                                 //~1ak4R~
    private static void hideNavigationBar30(boolean PswHide)       //~1ak4R~
    {                                                              //~1ak4R~
        if (Dump.Y) Dump.println("MainActivity.hideNavigationBar30 swHide="+PswHide);//~1ak4R~
        if (Dump.Y) Dump.println("MainActivity.hideNavigationBar30 swNavigationbarGestureMode="+AG.swNavigationbarGestureMode+",portrait="+AG.portrait);//~vaefI~
//      if (true) //follow system setting gesture or 3 button      //~1ak4R~//~vaefR~
//      	return; //if hide navigation,statusbar pull down override framelayout and dose not up never(Top panel title disappear)//~1ak4R~//~vaefR~
//      if (AG.portrait || !AG.swNavigationbarGestureMode)         //~vaefR~
//      {                                                          //~vaefR~
//      	if (Dump.Y) Dump.println("MainActivity.hideNavigationBar30 nop by portrait or not Gesture mode");//~vaefR~
//      	return;                                                //~vaefR~
//      }                                                          //~vaefR~
        WindowInsetsController ic=AG.activity.getWindow().getInsetsController();//~1ak4R~
        if (Dump.Y) Dump.println("MainActivity.hideNavigationBar30 WindowInsetsControler="+Utils.toString(ic));//~1ak4I~
        if (ic!=null)                                              //~1ak4R~
        {                                                          //~1ak4R~
	        if (Dump.Y) Dump.println("MainActivity.hideNavigationBar30 getSystembarBehavior="+ic.getSystemBarsBehavior());//~vamcI~
	        if (Dump.Y) Dump.println("MainActivity.hideNavigationBar30 getSystembarAppearance="+ic.getSystemBarsAppearance());//~vamcI~
		  if (AG.portrait)                                         //~vaefI~
          {                                                        //~vaefI~
	        ic.show(WindowInsets.Type.navigationBars());           //~vaefI~
	        if (Dump.Y) Dump.println("MainActivity.hideNavigationBar30 portraite show navigationbars swEndGame="+AG.aMainActivity.swEndGame);//~vaefI~//~vamcR~
	        if (Dump.Y) Dump.println("MainActivity.hideNavigationBar30 after getSystembarBehavior="+ic.getSystemBarsBehavior());//~vamcI~
	        if (Dump.Y) Dump.println("MainActivity.hideNavigationBar30 after getSystembarAppearance="+ic.getSystemBarsAppearance());//~vamcI~
		  }                                                        //~vaefI~
          else                                                     //~vaefI~
          {                                                        //~vaefI~
        	if (PswHide)                                           //~1ak4R~
            {                                                      //~1ak4R~
	        	ic.hide(WindowInsets.Type.navigationBars());       //~1ak4R~
	            if (Dump.Y) Dump.println("MainActivity.hideNavigationBar30 swHide hide navigationbars swEndGame="+AG.aMainActivity.swEndGame);//~vamcR~
//              int b=BEHAVIOR_SHOW_BARS_BY_SWIPE;                 //~1ak4R~//~vaefR~
                int b=BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE;       //~vaefI~
            	ic.setSystemBarsBehavior(b);    //allready request by fullscreen//~1ak4R~
//              int a=APPEARANCE_LIGHT_STATUS_BARS;                //~1ak4I~//~vaefR~
//          	ic.setSystemBarsAppearance(a,a);                   //~1ak4I~//~vaefR~
            }                                                      //~1ak4R~
            else                                                   //~1ak4R~
            {                                                      //~1ak4I~
	        	ic.show(WindowInsets.Type.navigationBars());       //~1ak4R~
	            if (Dump.Y) Dump.println("MainActivity.hideNavigationBar30 !swHide show navigationbars swEndGame="+AG.aMainActivity.swEndGame);//~vamcR~
//              int a=APPEARANCE_LIGHT_STATUS_BARS;                //~1ak4I~//~vaefR~
//          	ic.setSystemBarsAppearance(a,a);                   //~1ak4I~//~vaefR~
            }                                                      //~1ak4I~
          }                                                        //~vaefI~
        }                                                            //~1ak4I~//~vaefR~
    }                                                              //~1ak4R~
	//*************************                                    //~8B26I~//~9102I~
//  public static void hideNavigationBar(boolean PswHide)                 //~8B26R~//~9102I~//~1ak4R~
    private static void hideNavigationBar(boolean PswHide)         //~1ak4R~
    {                                                              //~1ak4R~
        if (Dump.Y) Dump.println("MainActivity.hideNavigationBar swHide="+PswHide);//~1ak4I~
//      if (true)   //TODO test                                    //~vae0R~//~1ak4R~
//      	return;                                                //~vae0R~//~1ak4R~
        if (Build.VERSION.SDK_INT>=30) //kitkat 4.4                //~1ak4R~
		    hideNavigationBar30(PswHide);                          //~1ak4R~
        else                                                       //~1ak4R~
		    hideNavigationBar29(PswHide);                          //~1ak4R~
    }                                                              //~1ak4R~
	//*************************                                    //~1ak4R~
    @SuppressWarnings("deprecation")                               //~1ak4R~
    private static void hideNavigationBar29(boolean PswHide)       //~1ak4R~
    {                                                              //~8B26I~//~9102I~
        if (Dump.Y) Dump.println("MainActivity.hideNavigationBar29 swHide="+PswHide);//~8B26I~//~9102I~//~9807R~//~1ak4R~
//      if (Build.VERSION.SDK_INT==29) //Android10(Q)              //~vaeeR~
//      	chkInsets29();                                         //~vaeeR~
      	if (AG==null)                                              //~vakvI~
      	{                                                          //~vakvI~
        	if (Dump.Y) Dump.println("MainActivity.hideNavigationBar29 @@@@ AG==null");//~vakvI~
      	}                                                          //~vakvI~
      	if (AG.activity==null)                                     //~vakvI~
      	{                                                          //~vakvI~
        	if (Dump.Y) Dump.println("MainActivity.hideNavigationBar29 @@@@ AG.Activity==null");//~vakvI~
      	}                                                          //~vakvI~
      	if (AG.activity.getWindow()==null)                         //~vakvI~
      	{                                                          //~vakvI~
        	if (Dump.Y) Dump.println("MainActivity.hideNavigationBar29 @@@@ AG.activity.getWindow()=null");//~vakvI~
      	}                                                          //~vakvI~
    	View decor=AG.activity.getWindow().getDecorView();             //~8B26I~//~9102I~
      	if (decor==null)                                           //~vakvI~
      	{                                                          //~vakvI~
        	if (Dump.Y) Dump.println("MainActivity.hideNavigationBar29 @@@@ AG.activity.getWindow().getDecorView()=null");//~vakvI~
            return;                                                //~vakvI~
      	}                                                          //~vakvI~
    	int flag=0;                                                //~8B26I~//~9102I~
        if (!PswHide)                                              //~8B26I~//~9102I~
        {                                                          //~8B26I~//~9102I~
//TODO test         flag= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//~8B26I~//~9102I~//~vaebR~
                    flag=0                                         //~vaebI~
                         |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN    //~8B26I~//~9102I~
                         |View.SYSTEM_UI_FLAG_LAYOUT_STABLE        //~8B26I~//~9102I~
                         ;                                         //~8B26I~//~9102I~
		    decor.setSystemUiVisibility(flag);                     //~8B26I~//~9102I~
        }                                                          //~8B26I~//~9102I~
        else                                                       //~8B26I~//~9102I~
        {                                                          //~8B26I~//~9102I~
        	UView.getScreenSize();                                 //~8B26I~//~9102I~
        	if (Dump.Y) Dump.println("MainActivity.hideNavigationBar scrNavigationbarRightWidth="+AG.scrNavigationbarRightWidth+",scrW="+AG.scrWidth+",scrH="+AG.scrHeight);//~9807I~
            if (AG.scrHeight<AG.scrWidth)	//landscape            //~8B26I~//~9102I~
            {                                                      //~8B26I~//~9102I~
              if (AG.scrNavigationbarRightWidth!=0) //navigationbar on the right and could not hide//~9807R~
              {                                                    //~9807R~
                if (Build.VERSION.SDK_INT>=19) //kitkat 4.4        //~9807R~
                    flag= 0                                        //~9807R~
                         |View.SYSTEM_UI_FLAG_FULLSCREEN           //~9807R~
                         |View.SYSTEM_UI_FLAG_IMMERSIVE            //~9807R~
                         |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY     //~9807R~
                                                                   //~9807R~
                         |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN    //~9807R~
                         |View.SYSTEM_UI_FLAG_LAYOUT_STABLE        //~9807R~
                         ;                                         //~9807R~
                else                                               //~9807R~
                if (Build.VERSION.SDK_INT>15)                      //~9807R~
                    flag=                                    View.SYSTEM_UI_FLAG_FULLSCREEN;//~9807R~
              }                                                    //~9807R~
              else                                                 //~9807R~
              {                                                    //~9807R~
                if (Build.VERSION.SDK_INT>=19) //kitkat 4.4                     //~8B26R~//~9102I~//~9511R~
//                    flag= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION    //~8B26R~//~9102I~
//                         |View.SYSTEM_UI_FLAG_FULLSCREEN         //~8B26R~//~9102I~
//                         |View.SYSTEM_UI_FLAG_IMMERSIVE          //~8B26R~//~9102I~
//                         |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY   //~8B26R~//~9102I~
                    flag= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION      //~8B26I~//~9102I~//~vaebR~
//TODO test         flag= 0                                        //~vaebR~
                         |View.SYSTEM_UI_FLAG_FULLSCREEN           //~8B26I~//~9102I~
//TODO test              |View.SYSTEM_UI_FLAG_IMMERSIVE           //not hidden after appeared by drag//~9807R~
                         |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY    //hiden after short time elapsed//~9807R~
                         |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//~8B26R~//~9102I~
                         |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN    //~8B26I~//~9102I~
                         |View.SYSTEM_UI_FLAG_LAYOUT_STABLE        //~8B26I~//~9102I~
                         ;                                         //~8B26R~//~9102I~
                else                                               //~8B26R~//~9102I~
                if (Build.VERSION.SDK_INT>15)                      //~8B26R~//~9102I~
                    flag=View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN;//~8B26R~//~9102I~
              }                                                    //~9807R~
            }                                                      //~8B26I~//~9102I~
            else    //Portrait                                     //~vaeeI~
            {                                                      //~vaeeI~
        		if (Dump.Y) Dump.println("MainActivity.hideNavigationBar portrait version="+Build.VERSION.SDK_INT);//~vaeeI~
				if (Build.VERSION.SDK_INT==29) //Android10(Q)      //~vaeeI~
                {                                                  //~vaeeI~
    				hideNavigationBar29Portrait(PswHide);          //~vaeeI~
                    flag=0;                                        //~vaeeI~
                }                                                  //~vaeeI~
            }                                                      //~vaeeI~
            if (flag!=0)                                           //~8B26R~//~9102I~
            {                                                      //~8B26I~//~9102I~
        		if (Dump.Y) Dump.println("MainActivity.hideNavigationBar version="+Build.VERSION.SDK_INT+",flag="+Integer.toHexString(flag));//~8B26I~//~9102I~//~9511R~//~1ak4R~
                decor.setSystemUiVisibility(flag);                 //~8B26R~//~9102I~
            }                                                      //~8B26I~//~9102I~
        }                                                          //~8B26I~//~9102I~
    }                                                              //~8B26I~//~9102I~
	//*************************                                    //~vaeeI~
    @SuppressWarnings("deprecation")                               //~vaeeI~
    private static void hideNavigationBar29Portrait(boolean PswHide)//~vaeeI~
    {                                                              //~vaeeI~
    	if (!AG.swNewA10)                                          //~vaeeR~
        	return;                //old version                   //~vaeeR~
        if (Dump.Y) Dump.println("MainActivity.hideNavigationBar29Portrait swHide="+PswHide);//~vaeeI~
    	View decor=AG.activity.getWindow().getDecorView();         //~vaeeI~
    	int flag=0;                                                //~vaeeI~
        if (!PswHide)                                              //~vaeeI~
        {                                                          //~vaeeI~
//TODO test         flag= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//~vaeeI~
                    flag=0                                         //~vaeeI~
                         |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION      //~vaeeI~
                         |View.SYSTEM_UI_FLAG_FULLSCREEN           //~vaeeI~
                         |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY    //hiden after short time elapsed//~vaeeI~
                         |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//~vaeeI~
                                                                   //~vaeeI~
                         |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN    //~vaeeI~
                         |View.SYSTEM_UI_FLAG_LAYOUT_STABLE        //~vaeeI~
                         ;                                         //~vaeeI~
		    decor.setSystemUiVisibility(flag);                     //~vaeeI~
        }                                                          //~vaeeI~
        else                                                       //~vaeeI~
        {                                                          //~vaeeI~
        	UView.getScreenSize();                                 //~vaeeI~
        	if (Dump.Y) Dump.println("MainActivity.hideNavigationBar29Portrait scrNavigationbarRightWidth="+AG.scrNavigationbarRightWidth+",scrW="+AG.scrWidth+",scrH="+AG.scrHeight);//~vaeeI~
//                  flag= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION      //~vaeeI~
                    flag= 0                                        //~vaeeI~
                         |View.SYSTEM_UI_FLAG_FULLSCREEN           //~vaeeI~
                         |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY    //hiden after short time elapsed//~vaeeI~
                         |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//~vaeeI~
                         |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN    //~vaeeI~
                         |View.SYSTEM_UI_FLAG_LAYOUT_STABLE        //~vaeeI~
                         ;                                         //~vaeeI~
        	if (Dump.Y) Dump.println("MainActivity.hideNavigationBar29Portrait version="+Build.VERSION.SDK_INT+",flag="+Integer.toHexString(flag));//~vaeeI~
            decor.setSystemUiVisibility(flag);                     //~vaeeI~
        }                                                          //~vaeeI~
    }                                                              //~vaeeI~
//    //*************************                                  //~vaeeR~
//    @SuppressWarnings("deprecation")                             //~vaeeR~
//    private static void chkInsets29()                            //~vaeeR~
//    {                                                            //~vaeeR~
//        if (Dump.Y) Dump.println("MainActivity.chkInsets29");    //~vaeeR~
//        View decor=AG.activity.getWindow().getDecorView();       //~vaeeR~
//        decor.setOnApplyWindowInsetsListener(                    //~vaeeR~
//            new View.onApplyWindowInsetsListener() {             //~vaeeR~
//                @Override                                        //~vaeeR~
//                public WindowInsets onApplyWindowInstes(View Pview,WindowInsets Pinset)//~vaeeR~
//                {                                                //~vaeeR~
//                    if (Dump.Y) Dump.println("MainActivity.chkInset29 inset="+Pinset.toString());//~vaeeR~
//                    return Pinset.consumeSystemWindowInsets();   //~vaeeR~
//                }                                                //~vaeeR~
//            });                                                  //~vaeeR~
//    }                                                            //~vaeeR~
	//*************************                                    //~9613I~
    private void gameHistory()                                         //~9613I~
    {                                                              //~9613I~
        if (Dump.Y) Dump.println("MainActivity.History");          //~9613I~
//      HistoryDlg.newInstance();                                  //~9613I~//~9614R~
        AG.aHistory.showDlg();                                     //~9614I~
    }                                                              //~9613I~
	//*************************                                    //~9106I~
    private void onConnect()                                       //~9106I~
    {                                                              //~9106I~
        if (Dump.Y) Dump.println("MainActivity.onConnect");        //~@@@@I~
//      AG.aBTMulti.onConnect();                                   //~9106I~//~9719R~
        MenuDlgConnect.showMenu();                                 //~9719I~
    }                                                              //~9106I~
    //*************************                                    //~@@@@R~//~9621R~
    private boolean chkMember()                                    //~@@@@R~//~9621R~
    {                                                              //~@@@@R~//~9621R~
        if ((TestOption.option & TestOption.TO_TEST_ORI)!=0)       //~@@@@R~//~9621R~
            return true;                                           //~@@@@R~//~9621R~
        boolean rc=Accounts.createInstance();                      //~@@@@R~//~9621R~
        if (Dump.Y) Dump.println("MainActivity.chkMember rc="+rc); //~@@@@R~//~9621R~
        if (!rc)                                                   //~@@@@R~//~9621R~
            UView.showToastLong(R.string.Err_LackingMember);                    //~v@@@I~//~@@@@R~//~9621R~//~0322R~
        return rc;                                                 //~@@@@R~//~9621R~
    }                                                              //~@@@@R~//~9621R~
//    //*************************                                  //~9621R~
//    private boolean createAccounts()                             //~9621R~
//    {                                                            //~9621R~
//        boolean rc=Accounts.createInstance();                    //~9621R~
//        if (Dump.Y) Dump.println("MainActivity.createInstance rc="+rc);//~9621R~
//        return rc;                                               //~9621R~
//    }                                                            //~9621R~
//    //*************************                                  //~@@@@I~
//    private void int Account()                                   //~@@@@I~
//    {                                                            //~@@@@I~
//        if (Dump.Y) Dump.println("MainActivity.chkMember rc="+rc);//~@@@@I~
//        if (!rc)                                                 //~@@@@I~
//            UView.showToast(R.string.Err_LackingMember);         //~@@@@I~
//        return rc;                                               //~@@@@I~
////        boolean rc=Accounts.createInstance();                  //~@@@@I~
////        if (Dump.Y) Dump.println("MainActivity.chkMember rc="+rc);//~@@@@I~
////        if (!rc)                                               //~@@@@I~
////            UView.showToast(R.string.Err_LackingMember);       //~@@@@I~
////        return rc;                                             //~@@@@I~
////    }                                                          //~@@@@I~
//    }                                                            //~@@@@I~
//***************************************************************************//~@@@@I~
	public void receivedCtlMsg(EventCB Peventcb)                   //~@@@@I~
    {                                                              //~@@@@I~
        int msgid=Peventcb.getParmInt1();                          //~@@@@I~
        String msg=Peventcb.getParmStr();                          //~@@@@I~
        if (Dump.Y) Dump.println("MainActivity.receivedCtlMsg swPaused="+swPaused+",msgid="+msgid+",msg="+msg);      //~@@@@I~//~9A22I~
//      AG.aBTMulti.msgRead(msgid,msg==null?"":msg);               //~@@@@I~//~9A22R~
        msgRead(msgid,msg);                                        //~9A22I~
    }                                                              //~@@@@I~
//***************************************************************************//~9A22I~
	private void msgRead(int Pmsgid,String Pmsg)                        //~9A22I~
    {                                                              //~9A22I~
        if (Dump.Y) Dump.println("MainActivity.msgRead msgid="+Pmsgid+",msg="+Pmsg);//~9A22I~
        AG.aBTMulti.msgRead(Pmsgid,Pmsg==null?"":Pmsg);               //~9A22I~
    }                                                              //~9A22I~
//***************************************************************************//~@@@@I~
    private void onSetting()                                       //~@@@@I~
    {                                                              //~@@@@I~
        if (Dump.Y) Dump.println("MainActivity.onSetting");        //~@@@@I~
        MenuDialog.showSettingMenu();                               //~v@@@R~//~@@@@I~
    }                                                              //~@@@@I~
//***************************************************************************//~9620I~
//*rc true:sync ok                                                 //~9620I~
//***************************************************************************//~9620I~
    private boolean chkRuleSync(boolean PswMsg)                    //~9620R~
    {                                                              //~9620I~
        if (Dump.Y) Dump.println("MainActivity.chkRuleSync");      //~9621R~
        if ((TestOption.option & TestOption.TO_RULE_NOSYNC)!=0)   //TODO test//~9620I~
        	return true;                                           //~9620I~
          boolean rc;                                                //~9620I~//~0119R~
//        int ctrActive=BTMulti.getMemberConnected();                //~9622I~//~0119R~
//        if (ctrActive==0 || (ctrActive<PLAYERS && !RuleSetting.isAllowRobot()))//~9622I~//~0119R~
//        {                                                          //~9622I~//~0119R~
//            UView.showToast(R.string.Err_LackingMember);           //~9622I~//~0119R~
//            return false;                                          //~9622I~//~0119R~
//        }                                                          //~9622I~//~0119R~
		String devnm=AG.aBTMulti.chkReconnectingEnv();             //~0110R~
		if (devnm!=null)                                           //~0110I~
        {                                                          //~0110I~
	        Alert.showMessage(R.string.StartGame,Utils.getStr(R.string.Err_ReconnectingExistStartGame,devnm));//~0110R~
            return false;                                          //~0110I~
        }                                                          //~0110I~
		rc=AG.aBTMulti.chkRuleSync(PswMsg);                        //~9620R~
        if (Dump.Y) Dump.println("MainActivity.chkuleSync rc="+rc);//~9620I~
        return rc;                                                 //~9620I~
    }                                                              //~9620I~
//***************************************************************************//~0119I~
    private boolean chkRobotGame()                                 //~0119I~
    {                                                              //~0119I~
    	boolean rc;                                                //~0119I~
        int ctrActive=BTMulti.getMemberConnected();                //~0119I~
        if (Dump.Y) Dump.println("MainActivity.chkRobotGamec ctrActive="+ctrActive);//~va66I~
        AG.swTrainingMode=false;                                    //~va66I~
        if (ctrActive==0)                                          //~0119I~
        {                                                          //~0119I~
//          UView.showToast(R.string.Err_LackingMemberNoConnection);//~0119I~//~va66R~
//  	    if (RuleSetting.isAllowRobotAll())                     //~va66R~
			if (RuleSetting.isAllowRobot())                        //~va66I~
            {                                                      //~va66I~
        		AG.swTrainingMode=true;                             //~va66I~
    			AG.aBTMulti.setTrainingMode();                     //~va66I~
	            String msg=Utils.getStr(R.string.Err_LackingMemberNoConnectionTraining);//~va66I~
		        int flag=BUTTON_POSITIVE|BUTTON_NEGATIVE;          //~va66I~
				Alert.showAlert(0/*app_name*/,msg,flag,this);      //~va66I~
                if (!RuleSetting.isThinkRobot())                   //~va66I~
		            UView.showToastLong(R.string.Warn_TrainingModeWithNotThinkRobot);//~va66I~
                                                                   //~va66I~
            }                                                      //~va66I~
            else                                                   //~va66I~
            {                                                      //~va66I~
	            String msg=Utils.getStr(R.string.Err_LackingMemberNoConnection);//~va66I~
				Alert.showMessage(0/*app_name*/,msg);              //~va66I~
            }                                                      //~va66I~
            return false;                                          //~0119I~
        }                                                          //~0119I~
      if (swServerStartGame)                                       //~vajhR~
        if (!BTMulti.isServerDevice())                             //~vajeI~
        {                                                          //~vajeI~
            MainView.drawMsg(R.string.Err_GameFromNotServer);      //~vajeI~
            return false;                                          //~vajeI~
        }                                                          //~vajeI~
        if (ctrActive+1<PLAYERS)                                   //~0119R~
        {                                                          //~0119I~
			if (RuleSetting.isAllowRobot())                        //~0119I~
            {                                                      //~0119I~
	            String msg=Utils.getStr(R.string.Err_LackingMemberAllowRobotYes,PLAYERS-ctrActive-1);//~0119R~
		        int flag=BUTTON_POSITIVE|BUTTON_NEGATIVE;                  //~9611I~//~0119I~
				Alert.showAlert(0/*app_name*/,msg,flag,this);                //~9608I~//~0119I~
            }                                                      //~0119I~
            else                                                   //~0119I~
            {                                                      //~0119I~
	            String msg=Utils.getStr(R.string.Err_LackingMemberAllowRobotNo,PLAYERS-ctrActive-1);//~0119R~
				Alert.showMessage(0/*app_name*/,msg);              //~0119I~
            }                                                      //~0119I~
            return false;                                          //~0119I~
        }                                                          //~0119I~
        return true;                                               //~0119I~
    }                                                              //~0119I~
    //*******************************************************      //~9611I~//~0119I~
    @Override   //AlertI                                           //~9611I~//~0119I~
	public int alertButtonAction(int Pbuttonid,int Prc)            //~9611I~//~0119I~
    {                                                              //~9611I~//~0119I~
        if (Dump.Y) Dump.println("MainActivity.alertButtonAction swTrainingMode="+AG.swTrainingMode+",buttonID="+Pbuttonid+",rc="+Prc);//~9611I~//~9709R~//~9A14R~//~0119I~//~0329R~//~va66R~
      try                                                          //~va66I~
      {                                                            //~va66I~
    	if (Pbuttonid==BUTTON_POSITIVE)                            //~9611I~//~0119I~
        {                                                          //~0119I~
            BTMulti.resetMemberDisconnected();                     //~0119R~
        	startGame(true/*chk setting*/);                        //~0119I~
        }                                                          //~0119I~
        else                                                       //~va66I~
        	AG.swTrainingMode=false;                                //~va66I~
      }                                                            //~va66I~
      catch(Exception e)                                           //~va66I~
      {                                                            //~va66I~
        Dump.println(e,"MainActivity.alertButtonAction");          //~va66R~
      }                                                            //~va66I~
                                                                   //~va66I~
        return 0;                                                  //~9611I~//~0119I~
    }                                                              //~9611I~//~0119I~
//***************************************************************************//~9621I~
    private void syncDateOK()                                      //~9621I~
    {                                                              //~9621I~
        if (Dump.Y) Dump.println("MainActivity.syncdateOK");       //~9621I~
        startGameClient();                                         //~9621I~
    }                                                              //~9621I~
//***************************************************************************//~9901I~
    private void resumeGame()                                      //~9901I~
    {                                                              //~9901I~
        if (Dump.Y) Dump.println("MainActivity.resumeGame");       //~9901I~
        HistoryData hd=AG.resumeHD;                                //~9901I~
        Prop p=hd.getRuleProp();                                   //~vae5I~
        if (p==null)                                               //~vae5I~
        {                                                          //~vae5I~
        	if (Dump.Y) Dump.println("MainActivity.resumeGame canceled by prop get failed");//~vae5I~
            return;                                                //~vae5I~
        }                                                          //~vae5I~
    	AG.savePropForResume=AG.ruleProp;                          //~vae5R~
    	AG.ruleProp=p;                                             //~vae5I~
        startGame(true/*chk setting*/,true/*swResume*/);           //~9901R~
    }                                                              //~9901I~
//***************************************************************************//~9B09I~
//*Server:on Click History or StartGame with PswInitApp=false              //~vae2I~//~vae5R~
//*Client:                                                         //~vae5I~
//***************************************************************************//~vae2I~
    private void initHistory(boolean PswInitApp)                   //~9B09I~
    {                                                              //~9B09I~
        if (Dump.Y) Dump.println("MainActivity.initHistory swInitApp="+PswInitApp);//~9B09I~
//      if (PswInitApp)                                            //~9B09I~//~vae0R~
//          if (!UFile.chkWritableSD())  //duplicated,requested already       //~9B09I~//~vae0R~
//  	    	return;                                            //~9B09I~//~vae0R~
      if (PswInitApp)                                              //~vae0R~
        new History();                                             //~9B09I~
       else                                                        //~vae0R~
        AG.aHistory.init();                                        //~vae0R~
    }                                                              //~9B09I~
//***************************************************************************//~9930I~
	@Override                                                      //~9930I~
    public void onRequestPermissionsResult(int PrequestID,String[] Ptypes,int[] Presults)//~9930I~
    {                                                              //~9930I~
        if (Dump.Y) Dump.println("MainActivity.onRequestPermissionResult reqid="+PrequestID+",type="+ Arrays.toString(Ptypes)+",result="+Arrays.toString(Presults));//~9930I~
        if (Presults.length==0)  //once crashed //TODO              //~1ak4R~
        {                                                          //~1ak4I~
        	if (Dump.Y) Dump.println("MainActivity.onRequestPermissionResult@@@@ no data Length=0");//~1ak4I~
            return;                                                //~1ak4I~
        }                                                          //~1ak4I~
        boolean granted;
        switch(PrequestID)                                         //~9930I~
        {                                                          //~9930I~
        case PERMISSION_LOCATION:                                  //~9930I~
        	granted=UView.isPermissionGranted(Presults[0]); //~9930I~
            MenuDlgConnect.grantedWifi(granted);                   //~9930R~
        	break;                                                 //~9930I~
        case PERMISSION_EXTERNAL_STORAGE:                          //~9B09I~
        	granted=UView.isPermissionGranted(Presults[0]);//~9B09I~
            UFile.grantedExternalStorage(granted);                 //~9B09I~
//  	    initHistory(false/*PswinitApp*/);	//may not have writable permission,init history by internal storage//~9B09I~//~1ak4R~
	        recoverProp();                                         //~vae8I~
        	break;                                                 //~9B09I~
        case PERMISSION_EXTERNAL_STORAGE_READ:                     //~vae0I~
        	granted=UView.isPermissionGranted(Presults[0]);        //~vae0I~
            UFile.grantedExternalStorageRead(granted);             //~vae0I~
            AG.aUScoped.grantedExternalStorageRead(granted);        //~vae0I~
	        recoverProp();                                         //~vae8I~
        	break;                                                 //~vae0I~
        case PERMISSION_BLUETOOTH:  //API31                        //~vam8I~
        	granted=UView.isPermissionGranted(Presults[0]) && UView.isPermissionGranted(Presults[1]);//~vam8I~
            BTI.grantedPermission(granted);                        //~vam8I~
        	break;                                                 //~vam8I~
        }                                                          //~9930I~
    }                                                              //~9930I~
//***************************************************************************//~v107I~//~1ak5I~
	@Override                                                      //~v107I~//~1ak5I~
    public void onActivityResult(int requestCode, int resultCode, Intent data)//~1ak2R~
	{                                                              //~1ak2I~
        if(Dump.Y) Dump.println("MainActivity.onActivityResult req="+requestCode+",result="+ resultCode);//~v107I~//~1A6aR~//~1ak5I~//~vaf0R~
      if (requestCode==AG.ACTIVITY_REQUEST_PICKUP_AUDIO)           //~1Ak2I~//~1ak2I~
      {                                                            //~1Ak2I~//~1ak2I~
        if(Dump.Y) Dump.println("MainActivity.onActivityResult AUDIO");//~1ak2I~//~vaf0R~
        UMediaStore.onActivityResult(requestCode,resultCode,data); //~1Ak2I~//~1ak2I~
      }                                                            //~1Ak2I~//~1ak2I~
      else                                                         //~1Ak2I~//~1ak2I~
      if (requestCode>AG.ACTIVITY_REQUEST_SCOPED && requestCode<AG.ACTIVITY_REQUEST_SCOPED_LAST)//~1Ak1I~//~1ak1I~
      {                                                            //~1Ak1I~//~1ak1I~
        AG.aUScoped.onActivityResult(requestCode,resultCode,data); //~1Ak1R~//~1ak1I~
        recoverProp();                                             //~vae8M~
      }                                                            //~1Ak1I~//~1ak1I~
      else                                                         //~1Ak1I~//~1ak1I~
      {                                                            //~1Ak1I~//~1ak1I~
		if (AG.aBTI!=null)                                       //~v107R~//~@@@@R~//~1ak5I~
			AG.aBTI.activityResult(requestCode,resultCode,data); //~v107R~//~@@@@R~//~1ak5I~
      }                                                            //~1ak1R~
	   super.onActivityResult(requestCode,resultCode,data);	//if not called,compile err//~1ak2I~
    }                                                              //~v107I~//~1ak5I~
//***************************************************************************//~vae8I~
    private void recoverProp()                                          //~vae8I~
    {                                                              //~vae8I~
        if(Dump.Y) Dump.println("MainActivity:recoverProp");       //~vae8I~
        AG.recoverProp();                                          //~vae8I~
    }                                                              //~vae8I~
//***************************************************************************//~vae8I~
    private void saveProp()                                             //~vae8I~
    {                                                              //~vae8I~
        if(Dump.Y) Dump.println("MainActivity:saveProp");          //~vae8I~
        AG.saveProp();                                             //~vae8I~
    }                                                              //~vae8I~
    //******************************************                   //~vaf0I~
	@Override                                                      //~vaf0I~
    public void onSaveInstanceState(Bundle Pbundle)                //~vaf0I~
	{                                                              //~vaf0I~
        if (Dump.Y) Dump.println(CN+"onSaveInstanciate bundle"+Utils.toString(Pbundle));//~vaf0R~
		super.onSaveInstanceState(Pbundle);                        //~vaf0I~
//        Pbundle.putObject(BUNDLE_AG,AG);                         //~vaf0R~
    }                                                              //~vaf0I~
    //******************************************                   //~vaf0I~
	@Override                                                      //~vaf0I~
    public void onRestoreInstanceState(Bundle Pbundle)             //~vaf0I~
	{                                                              //~vaf0I~
        if (Dump.Y) Dump.println(CN+"onRestoreInstance bundle"+Utils.toString(Pbundle));//~vaf0R~
		super.onSaveInstanceState(Pbundle);                        //~vaf0I~
//      AG ag=Pbundle.getObject(BUNDLE_AG");                       //~vaf0R~
//      StaticVars.AG=ag;                                          //~vaf0I~
    }                                                              //~vaf0I~
//    //******************************************                 //~vaf0R~
//    public AG restoreInstanceState(Bundle Pbundle)               //~vaf0R~
//    {                                                            //~vaf0R~
//        if (Dump.Y) Dump.println(CN+"RestoreInstance bundle"+Utils.toString(Pbundle));//~vaf0R~
//        if (Pbundle==null)                                       //~vaf0R~
//            return null;                                         //~vaf0R~
//        AG ag=Pbundle.getObject(BUNDLE_AG);                      //~vaf0R~
//        if (Dump.Y) Dump.println(CN+"RestoreInstance "+BUDLE_AG+"="+Utils.toString(ag));//~vaf0R~
//        return ag;                                               //~vaf0R~
//    }                                                            //~vaf0R~
	private void issueDestroyedWarning()                           //~vaf0I~
    {                                                              //~vaf0I~
        if (Dump.Y) Dump.println(CN+"issueDestroyWarning");        //~vaf0I~
//        int msgid=R.string.Err_AppDestroyedUnexpectedly;         //~vaf0R~
////      Alert.showMessage(AG.appName,Utils.getStr(msgid));       //~vaf0R~
//        AlertDlg.showMessage(AG.appName,Utils.getStr(msgid));    //~vaf0R~
  		UView.showToastLongDirect(R.string.Err_AppDestroyedUnexpectedly);//~vaf0I~
	}                                                              //~vaf0I~
	private void issueDestroyedWarningRestart()                    //~vaf0I~
    {                                                              //~vaf0I~
        if (Dump.Y) Dump.println(CN+"issueDestroyWarning");        //~vaf0I~
//        UView.showToastLongDirect((Context)this,Utils.getStr(getResources(),R.string.Err_AppDestroyedUnexpectedlyRestart));//~vaf0I~
        int msgid=R.string.Err_AppDestroyedUnexpectedlyRestart;    //~vaf0I~
		String appName=((Context)this).getText(R.string.app_name).toString();//~vaf0I~
		String msg=((Context)this).getText(msgid).toString();      //~vaf0I~
        AlertDlg.showMessageMainThread(appName,msg);               //~vaf0R~
	}                                                              //~vaf0I~
}
