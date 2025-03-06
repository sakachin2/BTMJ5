//*CID://+vayVR~: update#= 548;                                    //~vayVR~
//**********************************************************************
//2025/02/27 vayV add rotation lock/unlock button on top panel     //~vayVR~
//2025/02/25 vayQ hide navigationbar ingame also for api29         //~vayQI~
//2025/02/25 vayN try edgemode from api30, and set base to scrWidth/scrHeight=real//~vayNI~
//2025/02/10 vayx api29(DragonTouch) top; space between button and 3button navigation bar//~vayxI~
//2025/02/10 vayw g10 port, game button was hide                   //~vaywI~
//2025/02/17 vayv move getframelayout to MainView
//2025/02/17 vayu ww and hh is not swappable for port and land     //~vayuI~
//2025/02/17 vayt from Android15,fullscreen(edge to edge)          //~vaytI~
//2025/02/13 vayk api29(3button navigation bar), when back to top, there is space between bottom button and navigation bar.//~vaykI~
//               api29 framleyout height is real height and it requires margin up bottom button.//~vaykI~
//               The backed framelayout hight margin up twice      //~vaykI~
//2025/02/13 vayj 3button navligation mode hide bottom button      //~vayjI~
//2025/02/10 vayh when retured from landscape, top latout did not back to portrait.//~vayhI~
//2023/02/28 vay1 allow rotation of top view before startGame, then lock and view game panel//~vay1I~
//2022/01/31 vaji change color of top left to identify server      //~vajiI~
//2021/10/23 vaf5 (Bug)TTop panel msgbar overflow, adjust textsize //~vaf5I~
//2021/09/27 vaef gesture navigation mode from android11           //~vaefI~
//2021/09/26 vaee gesture navigation mode from android10           //~vaeeI~
//2021/09/23 vaec main buttons for small device                    //~vaecI~
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//**********************************************************************//~va66I~
package com.btmtest;                                               //~v@21R~

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;                                  //~vayhI~
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.btmtest.BT.BTMulti;
import com.btmtest.game.GC;
import com.btmtest.game.gv.GMsg;
import com.btmtest.gui.UButton;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.btmtest.StaticVars.AG;                           //~v@21I~


public class MainView                                   //~v@21R~  //~9620R~
{
    private static final int MAIN_TITLE =R.layout.main_title;      //~v@21I~
    private static final int MAIN_TITLE_SMALLFONT =R.layout.main_title_smallfont;//~vac5I~
    private static final int RID_TOPIMAGE=R.drawable.top_portrait; //~v@21I~
    private static final int MAIN_BUTTONS =R.layout.main_buttons;  //~v@21I~
    private static final int MAIN_BUTTONS_SMALLFONT =R.layout.main_buttons_smallfont;//~vac5I~
    private static final int MAIN_BUTTONS_SMALLDEVICE =R.layout.main_buttons_smalldevice;//~vaecI~
    private static final int MAIN_BUTTONS_LOCK=R.layout.main_buttons_orientation;//~vayVI~
//    private static final int MAIN_MSGBAR =R.layout.main_msgbar;    //~9619I~//~9620R~
    private static final int COLOR_ROLE_SERVER=GC.COLOR_GST_RELEASE;//~vajiR~
    private static final int COLOR_ROLE_CLIENT=GC.COLOR_GST_RELEASE_CLIENT;//~vajiR~
    private static final int COLOR_ROLE_UNDEFINED=GC.COLOR_BTN_NORMAL_BG;//~vajiR~
    private static final String CN="MainView:";                    //~vay1I~
                                                                   //~v@21I~
    private FrameLayout frameLayout;                               //~v@21I~
    private MainActivity main;                                     //~v@21I~
    private View  btnsMain,titleMain;                              //~v@21I~
    private View  btnsMainLock;                                    //~vayVR~
    private TextView  topMsgBar;                                       //~9619I~
    private Button btnSettings,btnConnect,btnHelp,btnStartGame;    //~v@21I~//~0119R~
    private Button btnTest1;              //TODO test              //~v@21I~
    private Button btnHistory;                                     //~0322R~
    public  Button btnLock,btnUnLock;                             //~vayVR~
//  private static Bitmap bmpTop;                                  //~v@21R~
    private        Bitmap bmpTop;                                  //~v@21I~
//  private        Bitmap bmpTop2;  //rotated                      //~vay1I~//~vayuR~
//  private        Bitmap bmpLastTop;                              //~vay1I~//~vayuR~
    private ImageView imageView;                                   //~v@21I~
    private int WW,HH,imageHH,titleHH,btnsHH;                      //~v@21R~
//  private int frameLayoutHH,frameLayoutWW;                       //~v@21R~
//    private boolean swRestore;                                   //~v@21R~
    private String strMsgBar;                                      //~9621I~
    private float pixelTextSize;                                   //~vaf5I~
    private boolean initialRotation;                               //~vay1R~
    private Bitmap bmpSrc;                                         //~vayuI~
    public  Point frameLayoutSize;                                 //~vayvR~
    private Point frameLayoutSizeBeforeStartGame;                  //~vayvI~
	private int versionAboveSetButtonMargin=31;	//Android S(12)        //~vayvI~
    //**************************************************************//~v@@@R~
    public MainView(MainActivity Pmain,FrameLayout PframeLayout)//~v@21R~//~9620R~
    {
        if (Dump.Y) Dump.println(CN+"constructor PframeLayout size=(w="+PframeLayout.getWidth()+",h="+PframeLayout.getHeight()+")="+PframeLayout);//~vay1I~//~vayjR~
//      super(Pmain);                                           //~v@21M~
        AG.aMainView=this;                              //~9619I~  //~9620R~
    	main=Pmain;                                                //~v@21I~
    	frameLayout=PframeLayout;                                  //~v@21I~
        WW = AG.scrWidthReal;                                      //~v@21I~
//      AG.scrPortraitWW =WW;	//by manifest topview is portrait,notUsed  //~v@21I~//~vay1R~
	    HH = AG.scrHeight;                                         //~v@21I~
        if (Dump.Y) Dump.println(CN+"Constructor swAllowLandscape="+Pmain.swAllowTopLandscape+",portrait="+AG.portrait+",WW="+WW+",HH="+HH);//~vay1R~
    }
	//*************************                                    //~v@21I~
	//*from Ondestroy                                              //~vay1I~
	//*************************                                    //~vay1I~
    public void reset()                                                //~v@21I~
    {                                                              //~v@21I~
        if(Dump.Y) Dump.println(CN+"reset bmpTop="+bmpTop+",bmpsrc="+bmpSrc);      //~v@21R~  //~9620R~//~vay1R~//~vayuR~
        if (bmpTop!=null)                                          //~v@21I~
        {                                                          //~v@21I~
        	UView.recycle(bmpTop);                                 //~v@21I~
            bmpTop=null;                                           //~v@21I~
        }                                                          //~v@21I~
//        if (bmpTop2!=null)                                         //~vay1I~//~vayuR~
//        {                                                          //~vay1I~//~vayuR~
//            UView.recycle(bmpTop2);                                //~vay1I~//~vayuR~
//            bmpTop=null;                                           //~vay1I~//~vayuR~
//        }                                                          //~vay1I~//~vayuR~
        if (bmpSrc!=null)                                          //~vayuI~
        {                                                          //~vayuI~
        	UView.recycle(bmpSrc);                                 //~vayuI~
            bmpSrc=null;                                           //~vayuI~
        }                                                          //~vayuI~
    }                                                              //~v@21I~
	//*************************************************************************//~v@21I~
//  public void init()                                             //~v@21R~//~vayuR~
//  public void init(Point PframeLayout)                           //~vayuI~//~vayvR~
    public void init()                                             //~vayvI~
    {                                                              //~v@21I~
//  	frameLayoutSize=getFrameLayoutSize();                      //~vayvI~//~vayNR~
    	frameLayoutSize=getFrameLayoutSize(true/*swTop*/);         //~vayNI~
    	setFrameLayoutSize(frameLayoutSize);	//initially it is (0,0)//~vaytI~
        if (Dump.Y) Dump.println(CN+"frameLayoutSize="+frameLayoutSize);      //~v@21R~  //~9620R~//~vayuR~//~vayvR~
        WW=frameLayoutSize.x; HH=frameLayoutSize.y;                      //~vayuI~//~vayvR~
        reset();                                                   //~v@21I~
        setImage();                                                //~v@21I~
        addTitle();                                                //~v@21I~
//      addMsgBar();                                              //~v@21I~//~9619R~//~9620R~
        addButtons();                                              //~9619I~
        bindButtons();                                             //~v@21I~
//      if ((TestOption.option & TestOption.TO_CONNECTED)!=0)	//TODO test//~v@21I~//~va66R~
        	setButtonStatus(true);                                 //~v@21I~
//      else                                                       //~v@21I~//~va66R~
//      	setButtonStatus(false);                                //~v@21I~//~va66R~
	    setLockButton(main.swOrientationLocked);	//initially unlock//~vayVI~
    }                                                              //~v@21I~
	//*****************************************************************//~v@21R~
    private void setImage()                                        //~v@21I~
    {                                                              //~v@21I~
        if (bmpTop==null)                                          //~v@21I~
        {                                                          //~v@21I~
//          bmpTop=BitmapFactory.decodeResource(AG.resource,RID_TOPIMAGE);//~v@21R~
//          Bitmap bmpSrc=BitmapFactory.decodeResource(AG.resource,RID_TOPIMAGE);//~v@21I~//~vayuR~
            bmpSrc=BitmapFactory.decodeResource(AG.resource,RID_TOPIMAGE);//~vayuI~
	  		if (Dump.Y) Dump.println(CN+"setImage bmpSrc ww="+bmpSrc.getWidth()+",hh="+bmpSrc.getHeight());//~vay1I~
	  		if (Dump.Y) Dump.println(CN+"setImage WW="+WW+",HH="+HH+",scrStatusBarHeigh="+AG.scrStatusBarHeight+",scrNavigationbarBottonHeight="+AG.scrNavigationbarBottomHeight);//~vay1I~
//            if (WW==bmpSrc.getWidth() && HH==bmpSrc.getHeight())      //~v@21I~//~vay1R~
//                bmpTop=bmpSrc;                                     //~v@21I~//~vay1R~
//            else                                                   //~v@21I~//~vay1R~
//            {                                                      //~v@21I~//~vay1R~
	 	    	if (Dump.Y) Dump.println("MainView.setImage bmp Src WW="+bmpSrc.getWidth()+",HH="+bmpSrc.getHeight());//~v@21R~//~9620R~
                int hh=HH;                                         //~vac5I~
//            	if (Build.VERSION.SDK_INT>=30)   //android30(R)    //~vac5R~//~vaefR~
//  	        	hh+=AG.scrStatusBarHeight;                     //~vac5R~//~vaefR~
			                                                       //~vayxI~
            if (false)                                             //~vayxI~
              if (AG.swNewA10) //                                  //~vaeeR~
	          	if (Build.VERSION.SDK_INT==29)   //for gesture navigationbar//~vaeeI~
		        	hh+=AG.scrStatusBarHeight-AG.scrNavigationbarBottomHeight;//~vaeeI~
//  	        Bitmap bmpScaled=Bitmap.createScaledBitmap(bmpSrc,WW,HH,true/*filter*/);//~v@21I~//~vac5R~
    	        Bitmap bmpScaled=Bitmap.createScaledBitmap(bmpSrc,WW,hh,true/*filter*/);//~vac5I~
                bmpTop=bmpScaled;                                  //~v@21I~
	  		    if (Dump.Y) Dump.println("MainView.setImage bmpTop ww="+bmpTop.getWidth()+",hh="+bmpTop.getHeight());//~vay1I~
//*prepare rotated                                                 //~vay1I~
//     	        bmpTop2=Bitmap.createScaledBitmap(bmpSrc,hh,WW,true/*filter*/);//~vay1R~//~vayuR~
//	  		    if (Dump.Y) Dump.println(CN+"setImage bmpTop2 ww="+bmpTop2.getWidth()+",hh="+bmpTop2.getHeight());//~vay1I~//~vayuR~
//              UView.recycle(bmpSrc);                             //~v@21I~//~vayuR~
//            }                                                      //~v@21I~//~vay1R~
            initialRotation=AG.portrait;                           //~vay1I~
        	if (Dump.Y) Dump.println("MainView.setImage bmp WW="+bmpTop.getWidth()+",HH="+bmpTop.getHeight()+",bmp="+bmpTop);//~vay1R~
	    	imageView=new ImageView(AG.context);                   //~vay1I~
    	    imageView.setImageBitmap(bmpTop);                      //~vay1I~
//			bmpLastTop=bmpTop;                                     //~vay1I~//~vayuR~
	        addImageView();                                        //~vay1I~
        }                                                          //~v@21I~
        if (Dump.Y) Dump.println(CN+"setImage bmpTop="+bmpTop);//~vay1R~//~vayuR~
////      imageView=new ImageView(this);                             //~v@21R~//~vay1R~
//        imageView=new ImageView(AG.context);                       //~v@21I~//~vay1R~
//        imageView.setImageBitmap(bmpTop);                          //~v@21R~//~vay1R~
////      setImageBitmap(bmpTop);                                    //~v@21R~//~vay1R~
//        addImageView();                                            //~v@21R~//~vay1R~
        frameLayoutSizeBeforeStartGame=frameLayoutSize;            //~vayvI~
	  	if (Dump.Y) Dump.println(CN+"setImage saved frameLayoutSizeBeforeStartGame="+frameLayoutSizeBeforeStartGame);//~vayvI~
    }                                                              //~v@21I~
	//*****************************************************************//~vay1I~
	//*when rotation occured(configurationChanged) from MainActivity//~vay1I~
	//*****************************************************************//~vay1I~
    private void showBottomButton()                                //~vay1I~
    {                                                              //~vay1I~
        if (Dump.Y) Dump.println(CN+"showBottomButton SDK_INT="+Build.VERSION.SDK_INT);//~vay1I~
		int marginH=getMarginH();                                  //~vaytI~
	    if (Build.VERSION.SDK_INT!=29)   //for Android10 gesture navigationbar//~vay1I~
        {                                                          //~vayvI~
//         if (!(Build.VERSION.SDK_INT>versionAboveSetButtonMargin)) //>31;	//Android S(12)//~vayvI~//~vaytR~
		   if (marginH==0)                                         //~vaytR~
            return;
        }                                                          //~vayvI~
//      int hh=AG.scrNavigationbarBottomHeight;                    //~vay1I~//~vaytR~
        int hh=marginH;                                            //~vaytI~
        if (Dump.Y) Dump.println(CN+"showBottomButton scrNavigationBarHeight="+hh);//~vay1I~
//      if (hh!=0)                                                    //~vay1I~//~vayvR~
        {                                                          //~vay1I~
			int wc=ViewGroup.LayoutParams.WRAP_CONTENT;            //~vay1I~
			int mp=ViewGroup.LayoutParams.MATCH_PARENT;            //~vay1I~
        	FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(mp,wc);//~vay1I~
	        lp.gravity=Gravity.BOTTOM;    //=layout_gravity        //~vay1I~
	        lp.setMargins(0/*left*/,0/*top*/,0/*right*/,hh);       //~vay1I~
	        btnsMain.setLayoutParams(lp);                          //~vay1M~
//            if (Dump.Y) Dump.println(CN+"showBottomButton removeView btnMain");//~vay1I~
//            frameLayout.removeView(btnsMain);                    //~vay1I~
//            if (Dump.Y) Dump.println(CN+"showBottomButton addView btnMain");//~vay1R~
//            frameLayout.addView(btnsMain);                       //~vay1R~
            if (Dump.Y) Dump.println(CN+"showBottomButton setMargins margin="+hh);//~vay1R~//~vayvR~
        }                                                          //~vay1I~
    }                                                              //~vay1I~
	//*****************************************************************//~vay1I~
	//*when rotation occured(configurationChanged) from MainActivity//~vay1R~
	//*****************************************************************//~vay1I~
//  public void resetImage()                                       //~vay1R~//~vayuR~
//  public void resetImage(Point PframeLayout)                     //~vayuI~//~vayvR~
    public void resetImage(boolean Prestore)                       //~vayvI~
    {                                                              //~vay1I~
//  	frameLayoutSize=getFrameLayoutSize();                      //~vayvI~//~vayNR~
    	frameLayoutSize=getFrameLayoutSize(true/*swTop*/);         //~vayNI~
        if (Dump.Y) Dump.println(CN+"resetImage frameLayout="+frameLayoutSize);//~vayuI~//~vayvR~
      if (!Prestore)                                      //~vayuI~//~vayvR~
      {                                                            //~vayuI~
        if (frameLayoutSize.x==WW && frameLayoutSize.y==HH)              //~vayuR~//~vayvR~
        {                                                          //~vayuI~
	        if (Dump.Y) Dump.println(CN+"resetImage same not NULL layout return WW="+WW+",HH="+HH);//~vayuR~
            return;                                                //~vayuI~
        }                                                          //~vayuI~
        WW=frameLayoutSize.x; HH=frameLayoutSize.y;                //~vaytI~
//        int ww=AG.scrWidthReal;                                  //~vay1R~
//        int hh=AG.scrHeight;                                     //~vay1R~
//        if (Dump.Y) Dump.println(CN+"resetImage initialRotation="+initialRotation+",currentRotation="+AG.portrait+",ww="+ww+",hh="+hh+",bmpTop2="+bmpTop2);//~vay1R~
//        if (bmpTop2==null)                                       //~vay1R~
//        {                                                        //~vay1R~
//            Bitmap bmpSrc=BitmapFactory.decodeResource(AG.resource,RID_TOPIMAGE);//~vay1R~
//            if (AG.swNewA10) //                                  //~vay1R~
//                if (Build.VERSION.SDK_INT==29)   //for gesture navigationbar//~vay1R~
//                    hh+=AG.scrStatusBarHeight-AG.scrNavigationbarBottomHeight;//~vay1R~
//            Bitmap bmpScaled=Bitmap.createScaledBitmap(bmpTop,ww,hh,true/*filter*/);//~vay1R~
//            if (Dump.Y) Dump.println(CN+"resetImage bmpScaled scrStatusBarHeigh="+AG.scrStatusBarHeight+",scrNavigationBarBottyomHeight="+AG.scrNavigationbarBottomHeight+",scaledBMWidth="+bmpScaled.getWidth()+",scaledBMPHeight="+bmpScaled.getHeight());//~vay1R~
//            bmpTop2=bmpScaled;                                   //~vay1R~
//        }                                                        //~vay1R~
        int ww=frameLayoutSize.x;                                     //~vayuI~//~vayvR~
        int hh=frameLayoutSize.y;                                     //~vayuI~//~vayvR~
        if (bmpTop!=null)                                          //~vayuI~
			UView.recycle(bmpTop);                                 //~vayuI~
    	bmpTop=Bitmap.createScaledBitmap(bmpSrc,ww,hh,true/*filter*/);//~vayuI~
        if (Dump.Y) Dump.println(CN+"resetImage bmpTop ww="+ww+",hh="+hh);//~vayvI~
        if (Dump.Y) Dump.println(CN+"resetImage imageView ww="+imageView.getWidth()+",hh="+imageView.getHeight());//~vay1I~
        if (Dump.Y) Dump.println(CN+"resetImage frameLauout ww="+frameLayout.getWidth()+",hh="+frameLayout.getHeight());//~vay1I~
        if (Dump.Y) Dump.println(CN+"resetImage frameLayout="+frameLayout);//~vay1I~
        if (Dump.Y) Dump.println(CN+"resetImage imageView="+imageView);//~vay1I~
//        Bitmap bmptop;                                             //~vay1I~//~vayuR~
//        if (AG.portrait==initialRotation)                          //~vay1I~//~vayuR~
//            bmptop=bmpTop;                                         //~vay1I~//~vayuR~
//        else                                                       //~vay1I~//~vayuR~
//            bmptop=bmpTop2;                                        //~vay1I~//~vayuR~
//        if (Dump.Y) Dump.println(CN+"resetImage bmpTop="+bmpTop+",bmpTop2="+bmpTop2+",selected="+bmptop);//~vaykR~//~vayuR~
//        if (Dump.Y) Dump.println(CN+"resetImage new bmp WW="+bmptop.getWidth()+",HH="+bmptop.getHeight()+",bmptop="+bmptop);//~vay1R~//~vayuR~
    	setFrameLayoutSize(frameLayoutSize);                       //~vayvI~
      }//parm framelayout!=null                                    //~vayuI~
        imageView.setImageBitmap(bmpTop);                          //~vay1I~
//		bmpLastTop=bmpTop;  //restore at endgame                   //~vay1I~//~vayuR~
        showBottomButton();                                        //~vay1I~
        frameLayoutSizeBeforeStartGame=frameLayoutSize;            //~vayvI~
	  	if (Dump.Y) Dump.println(CN+"resetImage saved frameLayoutSizeBeforeStartGame="+frameLayoutSizeBeforeStartGame);//~vayvI~
    }                                                              //~vay1I~
	//*************************                                    //~v@21I~
    private void addTitle()                                        //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println(CN+"addTitle frameLayout="+frameLayout);//~vay1I~
		int wc=ViewGroup.LayoutParams.WRAP_CONTENT;                //~v@21I~
		int fp=ViewGroup.LayoutParams.MATCH_PARENT;                //~v@21R~
        FrameLayout.LayoutParams lp;                               //~v@21I~
        lp=new FrameLayout.LayoutParams(fp,wc);                    //~v@21I~
//      titleMain=AG.inflater.inflate(MAIN_TITLE,null);            //~v@21I~//~vac5R~
        titleMain=AG.inflater.inflate((AG.swSmallFont ? MAIN_TITLE_SMALLFONT : MAIN_TITLE),null);//~vac5I~
        appendTimestampMade(titleMain);                            //~v@21I~
        lp.gravity=Gravity.TOP|Gravity.LEFT;    //=layout_gravity  //~v@21I~
        titleMain.setLayoutParams(lp);                             //~v@21I~
        frameLayout.addView(titleMain);                            //~v@21I~
//                                                                 //~v@21R~
//        ViewGroup.LayoutParams lpvg=titleMain.getLayoutParams(); //~v@21R~
//        if (Dump.Y) Dump.println("MainView.addTitle getlayoutparm ww="+lpvg.width+".hh="+lpvg.height);//~v@21R~//~9620R~
        if (Dump.Y) Dump.println("MainView.addTitle view ww="+titleMain.getMeasuredWidth()+",hh="+titleMain.getMeasuredHeight());//~v@21I~//~9620R~
    }                                                              //~v@21I~
	//*************************                                    //~v@21I~
    private void appendTimestampMade(View Pview)                   //~v@21I~
    {                                                              //~v@21I~
    	Date ts=new Date(BuildConfig.TIMESTAMP);                   //~v@21I~
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd-HH.mm.ss");//~v@21I~
        String sts=sdf.format(ts);                                 //~v@21I~
		TextView tv=(TextView)    UView.findViewById(Pview,R.id.Title_Version);//~v@21I~
        String s=tv.getText().toString()+" ("+sts+")";              //~v@21I~
        if (Dump.Y) Dump.println("MainView.appendTimestampMade date="+sts+",title="+s);//~v@21I~//~9620R~
        tv.setText(s);                                             //~v@21I~
    }                                                              //~v@21I~
	//*************************                                    //~v@21I~
    private void addButtons()                                      //~v@21I~
    {                                                             //~v@21R~
        if (Dump.Y) Dump.println(CN+"addButtons AG.portrait="+AG.portrait+",Build.VERSION="+Build.VERSION.SDK_INT);//~v@21R~  //~9620R~//~vay1R~//~vaykR~
        if (Dump.Y) Dump.println(CN+"addButtons frameLayout="+frameLayout);//~vay1I~
		int wc=ViewGroup.LayoutParams.WRAP_CONTENT;                //~v@21I~
		int mp=ViewGroup.LayoutParams.MATCH_PARENT;                //~v@21I~
        FrameLayout.LayoutParams lp;                               //~v@21I~
        lp=new FrameLayout.LayoutParams(mp,wc);                    //~v@21I~
//      btnsMain=AG.inflater.inflate(MAIN_BUTTONS,null);           //~v@21I~//~vac5R~
//      btnsMain=AG.inflater.inflate((AG.swSmallFont ? MAIN_BUTTONS_SMALLFONT : MAIN_BUTTONS),null);//~vac5I~//~vaecR~
        btnsMain=AG.inflater.inflate(AG.swSmallDevice ? MAIN_BUTTONS_SMALLDEVICE : ((AG.swSmallFont ? MAIN_BUTTONS_SMALLFONT : MAIN_BUTTONS)),null);//~vaecI~
        if (main.swAllowTopLandscape)                              //~vayVI~
        {                                                          //~vayVI~
        	int lid=MAIN_BUTTONS_LOCK;                             //~vayVI~
	        btnsMainLock=AG.inflater.inflate(lid,null);            //~vayVI~
        }                                                          //~vayVI~
        topMsgBar=(TextView)    UView.findViewById(btnsMain,R.id.TopMsgBar);//~9620I~
//      lp.gravity=Gravity.BOTTOM|Gravity.CENTER;    //=layout_gravity//~v@21R~
        lp.gravity=Gravity.BOTTOM;    //=layout_gravity            //~v@21I~
		int marginH=getMarginH();                                  //~vaytI~
      if (true) //TEST                                             //~vayxI~
      {                                                            //~vayxI~
//	    if (AG.osVersion>=AG.APIVER_EDGEMODE)                         //~vayxI~//~vayNR~
  	    if (UView.isEdgeMode())                                      //~vayNI~
        {                                                          //~vayxI~
//        	lp.setMargins(0/*left*/,0/*top*/,0/*right*/,AG.scrNavigationbarBottomHeightA11);//~vayxR~//~vayNR~
        	if (Dump.Y) Dump.println(CN+"addButtons EdgeMode no set Bottom margin");//~vayNR~
        }                                                          //~vayxI~
      }                                                            //~vayxI~
      else                                                         //~vayxI~
      if (AG.swNewA10)                                             //~vaeeR~
       if (AG.portrait)                                            //~vaykI~
	    if (Build.VERSION.SDK_INT==29)   //for gesture navigationbar//~vaeeI~
        {                                                          //~vay1I~
        	if (Dump.Y) Dump.println(CN+"addButtons SDK_INT=29 bottomHeightA11="+AG.scrNavigationbarBottomHeightA11);//~vay1R~//~vaykR~
         if (false)                                                //~vayxI~
         {                                                         //~vayxI~
//        if (AG.scrNavigationbarBottomHeight!=0)   //Test         //~vay1R~
//        {                                                        //~vay1R~
        	lp.setMargins(0/*left*/,0/*top*/,0/*right*/,AG.scrNavigationbarBottomHeight);//~vaeeR~
        	if (Dump.Y) Dump.println(CN+"addButtons SDK_INT=29 setMargins="+AG.scrNavigationbarBottomHeight);//~vay1I~
//        }                                                        //~vay1R~
         }                                                         //~vayxI~
        }                                                          //~vay1I~
        else //TEST                                                //~vaykI~
//      if (Build.VERSION.SDK_INT>versionAboveSetButtonMargin) //>31;	//Android S(12)//~vayvI~//~vaytR~
		if (marginH!=0)                                            //~vaytI~
        {                                                          //~vaykI~
//        if (AG.scrNavigationbarBottomHeight!=0)   //Test         //~vaykI~
//        {                                                        //~vaykI~
//        	lp.setMargins(0/*left*/,0/*top*/,0/*right*/,AG.scrNavigationbarBottomHeight);//~vaykI~//~vaytR~
          	lp.setMargins(0/*left*/,0/*top*/,0/*right*/,marginH);  //~vaytI~
        	if (Dump.Y) Dump.println(CN+"addButtons SDK_INT="+Build.VERSION.SDK_INT+",marginH="+marginH+",AG.scrNavigationbarBottomHeight="+AG.scrNavigationbarBottomHeight);//~vaykI~//~vaytR~
//        }                                                        //~vaykI~
        }                                                          //~vaykI~
//        if (Build.VERSION.SDK_INT>=30)   //for gesture navigationbar//~vaefR~
//         if (false)                                              //~vaefR~
//          if (AG.swNavigationbarGestureMode)                     //~vaefR~
//          {                                                      //~vaefR~
//            if (Dump.Y) Dump.println("MainView.addButtons setMargin bottomHeightA11="+AG.scrNavigationbarBottomHeightA11);//~vaefR~
//            lp.setMargins(0/*left*/,0/*top*/,0/*right*/,AG.scrNavigationbarBottomHeightA11);//~vaefR~
//          }                                                      //~vaefR~
//        if (Build.VERSION.SDK_INT>=30)   //for gesture navigationbar//~vayjI~//~vaykR~
//          if (AG.portrait)                                       //~vaykR~
//            if (!AG.swNavigationbarGestureMode)                    //~vayjI~//~vaykR~
//            {                                                      //~vayjI~//~vaykR~
//                if (Dump.Y) Dump.println("MainView.addButtons setMargin bottomHeightA11="+AG.scrNavigationbarBottomHeightA11);//~vayjI~//~vaykR~
//                lp.setMargins(0/*left*/,0/*top*/,0/*right*/,AG.scrNavigationbarBottomHeightA11);//~vayjI~//~vaykR~
//            }                                                      //~vayjI~//~vaykR~
        if (Dump.Y) Dump.println(CN+"addButtons layoutParm="+lp);  //~vay1R~
        btnsMain.setLayoutParams(lp);                              //~v@21I~
//      btnsMain.setVisibility(View.INVISIBLE);                    //~v@21R~
//      btnsMain.setVisibility(View.VISIBLE);                      //~v@21R~
        frameLayout.addView(btnsMain);                             //~v@21I~
        if (main.swAllowTopLandscape)                              //~vayVI~
        {                                                          //~vayVI~
	        frameLayout.addView(btnsMainLock);                     //~vayVI~
        }                                                          //~vayVI~
                                                                   //~v@21I~
//        ViewGroup.LayoutParams lpvg=btnsMain.getLayoutParams();  //~v@21R~
//        if (Dump.Y) Dump.println("MainView.addButtons getlayoutparm ww="+lpvg.width+".hh="+lpvg.height);//~v@21R~//~9620R~
    	pixelTextSize=topMsgBar.getTextSize();                     //~vaf5I~
        if (Dump.Y) Dump.println("MainView.addButtons view ww="+btnsMain.getMeasuredWidth()+",hh="+btnsMain.getMeasuredHeight());//~v@21I~//~9620R~
        if (Dump.Y) Dump.println("MainView.addButtons btnsMain="+btnsMain.toString());//~v@21R~//~9620R~
        if (Dump.Y) Dump.println("MainView.addButtons navigationBottomHeight="+AG.scrNavigationbarBottomHeight+",bottomA11="+AG.scrNavigationbarBottomHeightA11);;;//~vaefR~
        if (Dump.Y) Dump.println("MainView.addButtons msgbar textsize="+pixelTextSize);//~vaf5I~
    }                                                              //~v@21I~
//    //*************************                                  //~v@21R~
//    private void addButtons2()                                   //~v@21R~
//    {                                                            //~v@21R~
//        if (Dump.Y) Dump.println("MainView.addButtons2");//~v@21R~//~9620R~
//        int wc=ViewGroup.LayoutParams.WRAP_CONTENT;              //~v@21R~
//        int mp=ViewGroup.LayoutParams.MATCH_PARENT;              //~v@21R~
//        FrameLayout.LayoutParams lp;                             //~v@21R~
//        lp=new FrameLayout.LayoutParams(mp,wc);                  //~v@21R~
////      btnsMain=AG.inflater.inflate(MAIN_BUTTONS,null);         //~v@21R~
////      lp.gravity=Gravity.BOTTOM|Gravity.CENTER;    //=layout_gravity//~v@21R~
////      lp.gravity=Gravity.BOTTOM;    //layout_gravity because lp is for parent//~v@21R~
//        lp.gravity=Gravity.CENTER;    //layout_gravity because lp is for parent//~v@21I~
//        btnsMain.setLayoutParams(lp);                            //~v@21R~
////      btnsMain.setVisibility(View.INVISIBLE);                  //~v@21R~
////      btnsMain.setVisibility(View.VISIBLE);                    //~v@21R~
////      frameLayout.addView(btnsMain,-1,lp);                     //~v@21R~
//        frameLayout.addView(btnsMain);                           //~v@21I~
////      frameLayout.addView(btnsMain,-1);                        //~v@21I~
////      frameLayout.addView(btnsMain,-1,lp);                     //~v@21I~
//                                                                 //~v@21R~
////        ViewGroup.LayoutParams lpvg=btnsMain.getLayoutParams();//~v@21R~
////        if (Dump.Y) Dump.println("MainView.addButtons getlayoutparm ww="+lpvg.width+".hh="+lpvg.height);//~v@21R~//~9620R~
//        if (Dump.Y) Dump.println("MainView.addButtons view ww="+btnsMain.getMeasuredWidth()+",hh="+btnsMain.getMeasuredHeight());//~v@21R~//~9620R~
//                                                                 //~v@21R~
//        if (Dump.Y) Dump.println("MainView.addButtons btnsMain="+btnsMain.toString());//~v@21R~//~9620R~
//    }                                                            //~v@21R~
//    //*************************                                    //~v@21I~//~9411R~
//    @Override                                                      //~v@21I~//~9411R~
//    public void onLayout(boolean PswChanged,int Pl,int Pt,int Pr,int Pb)    //TODO test//~v@21I~//~9411R~
//    {                                                              //~v@21I~//~9411R~
//        super.onLayout(PswChanged,Pl,Pt,Pr,Pb);                    //~v@21I~//~9411R~
//        if (Dump.Y) Dump.println("MainView.onLayout swChanged="+PswChanged+",l="+Pl+",t="+Pt+",r="+Pr+",b="+Pb);//~v@21I~//~9411R~//~9620R~
//    }                                                              //~v@21I~//~9411R~
//    @Override                                                      //~v@21I~//~9411R~
//    public void onSizeChanged(int PnewW,int PnewH,int PoldW,int PoldH)//~v@21I~//~9411R~
//    {                                                              //~v@21I~//~9411R~
//        super.onSizeChanged(PnewW,PnewH,PoldW,PoldH);              //~v@21I~//~9411R~
//        if (Dump.Y) Dump.println("MainView.onSizeChanged new="+PnewW+","+PnewH+",old="+PoldW+","+PoldH);//~v@21I~//~9411R~//~9620R~
//    }                                                              //~v@21I~//~9411R~
	//*************************                                    //~v@21I~
    private void bindButtons()                                     //~v@21I~
    {                                                              //~v@21I~
        btnSettings  =              UButton.bind(btnsMain,R.id.Settings,main);//~v@21R~//~0119R~
        btnConnect   =              UButton.bind(btnsMain,R.id.Connect,main);//~v@21R~//~0119R~
        btnStartGame =              UButton.bind(btnsMain,R.id.StartGame,main);//~v@21R~
        btnHistory   =              UButton.bind(btnsMain,R.id.History,main);//~9614I~//~0119R~
        btnHelp      =              UButton.bind(btnsMain,R.id.Help,main);//~v@21R~//~0119R~
        btnTest1     =              UButton.bind(btnsMain,R.id.Test1,main);	//TODO test//~v@21I~//~0119R~
	  if (btnsMainLock!=null)                                      //+vayVI~
      {                                                            //+vayVI~
        btnLock      =              UButton.bind(btnsMainLock,R.id.OrientationLock,main);	//TODO test//~vayVR~
        btnUnLock    =              UButton.bind(btnsMainLock,R.id.OrientationUnLock,main);	//TODO test//~vayVI~
      }                                                            //+vayVI~
//      if (AG.isDebuggable)                                       //~0316I~//~vac5R~
//          btnTest1.setVisibility(View.VISIBLE);                  //~0316I~//~vac5R~
    }                                                              //~v@21I~
	//*************************                                    //~v@21I~
    private void setButtonStatus(boolean PswConnected)             //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("MainView.setButtonStatus swconnected="+PswConnected);//~v@21R~//~9620R~
//  	btnStartGame.setEnabled(PswConnected);                     //~v@21I~//~9620R~
    	enableStartGame(PswConnected);                              //~9620I~
    }                                                              //~v@21I~
	//**************************************************************//~vajiI~
	//*from BTCDialog,WDA dismissDialog                            //~vajiI~
	//**************************************************************//~vajiI~
    public void showConnectStatus()                                //~vajiI~
    {                                                              //~vajiI~
        if (Dump.Y) Dump.println("MainView.showConnectStatus entry");//~vajiI~
        if (AG.isMainThread())                                     //~vajiI~
        {                                                          //~vajiI~
		    showConnectStatusUI();                                 //~vajiI~
        }                                                          //~vajiI~
        else                                                       //~vajiI~
        {                                                          //~vajiI~
            AG.activity.runOnUiThread(                             //~vajiI~
                new Runnable()                                     //~vajiI~
                {                                                  //~vajiI~
                    @Override                                      //~vajiI~
                    public void run()                              //~vajiI~
                    {                                              //~vajiI~
					    showConnectStatusUI();                     //~vajiI~
                    }                                              //~vajiI~
                }                                                  //~vajiI~
                                      );                           //~vajiI~
		}                                                          //~vajiI~
        if (Dump.Y) Dump.println("MainView.showConnectStatus exit");//~vajiI~
    }                                                              //~vajiI~
    public void showConnectStatusUI()                              //~vajiI~
    {                                                              //~vajiI~
        int color;                                                 //~vajiM~
        if (Dump.Y) Dump.println("MainView.showConnectStatusUI");  //~vajiI~
        try                                                        //~vajiI~
        {                                                          //~vajiI~
            if (BTMulti.getConnectedCtr()==0)                      //~vajiR~
                color=COLOR_ROLE_UNDEFINED;                        //~vajiR~
            else                                                   //~vajiR~
            if (BTMulti.isServerDevice())                          //~vajiR~
                color=COLOR_ROLE_SERVER;                           //~vajiR~
            else                                                   //~vajiR~
            if (BTMulti.isClientDevice())                          //~vajiR~
                color=COLOR_ROLE_CLIENT;                           //~vajiR~
            else                                                   //~vajiR~
                color=COLOR_ROLE_UNDEFINED;                        //~vajiR~
            if (Dump.Y) Dump.println("MainView.showConnectStatus color="+Integer.toHexString(color));//~vajiR~
            Utils.setBtnBG(btnConnect,color);                      //~vajiR~
        }                                                          //~vajiI~
        catch(Exception e)                                         //~vajiI~
        {                                                          //~vajiI~
            Dump.println(e,"MainView.drawMsg:runOnUiThread");      //~vajiI~
        }                                                          //~vajiI~
    }                                                              //~vajiI~
	//*************************************************************************//~v@21I~
    public void addImageView()                                     //~v@21R~
    {                                                              //~v@21I~
//  	int lp=ViewGroup.LayoutParams.MATCH_PARENT;                //~v@21I~
//  	int wc=ViewGroup.LayoutParams.WRAP_CONTENT;                //~v@21I~
//  	int mp=ViewGroup.LayoutParams.MATCH_PARENT;                //~v@21I~
//      FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(mp,wp);//~v@21I~
//      frameLayout.addView(this,lp,lp);                           //~v@21R~
//      frameLayout.addView(this);                                 //~v@21R~
        frameLayout.addView(imageView);                            //~v@21I~
        if (Dump.Y) Dump.println("MainView.addImageView after addView frameLayout=("+frameLayout.getWidth()+","+frameLayout.getHeight()+")");//~v@21R~//~9620R~//~vac5R~
        if (Dump.Y) Dump.println("MainView.addImageView after addView imageView ww="+imageView.getWidth()+",hh="+imageView.getHeight());//~vac5I~//~vay1R~
        if (Dump.Y) Dump.println("MainView.addImageView after addView frameLauout ww="+frameLayout.getWidth()+",hh="+frameLayout.getHeight());//~vay1I~
        if (Dump.Y) Dump.println(CN+"addImageView frameLayout="+frameLayout);//~vay1I~
        if (Dump.Y) Dump.println(CN+"addImageView imageView="+imageView);//~vay1I~
    }                                                              //~v@21I~
//    //*************************                                    //~9619I~//~9620R~
//    private void addMsgBar()                                       //~9619I~//~9620R~
//    {                                                              //~9619I~//~9620R~
//        if (Dump.Y) Dump.println("MainView.addMsgBar"); //~9619I~//~9620R~
//        View llmsgbar=AG.inflater.inflate(MAIN_MSGBAR,null);       //~9619I~//~9620R~
//        topMsgBar=(TextView)    UView.findViewById(llmsgbar,R.id.TopMsgBar);//~9619I~//~9620R~
//                                                                   //~9619I~//~9620R~
//        int wc=ViewGroup.LayoutParams.WRAP_CONTENT;                //~9619I~//~9620R~
//        int mp=ViewGroup.LayoutParams.MATCH_PARENT;                //~9619I~//~9620R~
//        FrameLayout.LayoutParams lp;                               //~9619I~//~9620R~
//        lp=new FrameLayout.LayoutParams(mp,wc);                    //~9619I~//~9620R~
//        lp.gravity=Gravity.BOTTOM;    //=layout_gravity            //~9619I~//~9620R~
//        llmsgbar.setLayoutParams(lp);                              //~9619I~//~9620R~
//        frameLayout.addView(llmsgbar);                             //~9619I~//~9620R~
////      frameLayout.addView(llmsgbar,1,new ViewGroup.LayoutParams(mp,wc));//~9619I~//~9620R~
//        if (Dump.Y) Dump.println("MainView.addMsgBar view ww="+llmsgbar.getMeasuredWidth()+",hh="+llmsgbar.getMeasuredHeight());//~9619I~//~9620R~
//    }                                                              //~9619I~//~9620R~
//**********************************************************       //~v@21I~
//*from MainActivity at startGame                                  //~v@21R~
//**********************************************************
//  public void hideTopView(Point PframeLayoutSize)                //~v@21R~//~vayvR~
    public void hideTopView()                                      //~vayvI~
    {                                                              //~v@21I~
//      frameLayoutWW=PframeLayoutSize.x;                          //~v@21R~
//      frameLayoutHH=PframeLayoutSize.y;                          //~v@21R~
        if (Dump.Y) Dump.println(CN+"hideTopView title h="+titleMain.getHeight()+",w="+titleMain.getWidth());//~v@21R~ //~9620R~//~vac5R~//~vay1R~
        if (Dump.Y) Dump.println(CN+"hideTopView frameLayout="+frameLayout);//~vay1I~
        if (Dump.Y) Dump.println(CN+"hideTopView frameLayoutSize="+frameLayoutSize);//~vayvI~
//        swRestore=false;                                         //~v@21R~
        hideImage(true);                                           //~v@21I~
	    hideButtons(true/*hide*/);                                 //~v@21I~
	    hideTitle(true/*hide*/);                                   //~v@21I~
        if (true)                                                  //~vaywI~
	        UView.getScreenSize();                                 //~vaywI~
//  	frameLayoutSize=getFrameLayoutSize();  //for game port/land//~vayvI~//~vayNR~
    	frameLayoutSize=getFrameLayoutSize(false/*swTop*/);  //for game port/land//~vayNI~
//  	setFrameLayoutSize(PframeLayoutSize);                       //~v@21I~//~vayvR~
    	setFrameLayoutSize(frameLayoutSize);                       //~vayvI~
        if (Dump.Y) Dump.println("MainView.hideTopView frameLayoutSize="+frameLayoutSize+",beforeStartGame="+frameLayoutSizeBeforeStartGame);//~vayvR~
    }                                                              //~v@21I~
//**********************************************************       //~v@21I~
//  public void restore()                                          //~v@21R~
//  public void restore(Point PframeLayoutSize)                    //~v@21I~//~vayvR~
    public void restore()                                          //~vayvI~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println(CN+"restore frameLayoutSize="+frameLayoutSize+",beforeGameStart="+frameLayoutSizeBeforeStartGame);//~v@21R~//~9620R~//~vay1R~//~vayvR~
        if (Dump.Y) Dump.println(CN+"restore frameLayout="+frameLayout);//~vay1I~
//      PframeLayoutSize.y-=100;                                   //~v@21R~
//        swRestore=true;                                          //~v@21R~
//      setFrameLayoutSize();                                      //~v@21R~
//      setFrameLayoutSize(PframeLayoutSize);                      //~v@21I~//~vayvR~
        setFrameLayoutSize(frameLayoutSizeBeforeStartGame);        //~vayvR~
        frameLayoutSize=frameLayoutSizeBeforeStartGame;            //~vayvI~
        hideImage(false);                                          //~v@21M~
//        restoreTitleBtn();                                       //~v@21R~
	    hideTitle(false/*hide*/);                                  //~v@21I~
	    hideButtons(false/*hide*/);                                //~v@21I~
//        frameLayout.setVisibility(View.INVISIBLE);               //~v@21R~
//        frameLayout.setVisibility(View.VISIBLE);                 //~v@21R~
//        frameLayout.invalidate();                                //~v@21R~
//        AG.mainView.invalidate();                                //~v@21R~
    	drawMsg("");                                               //~0216I~
        if (Dump.Y) Dump.println(CN+"restore frameLayoutSize="+frameLayoutSize);//~vayvI~
    }                                                              //~v@21I~
//**********************************************************       //~vay1I~
//*restore at endGame                                              //~vay1I~
//**********************************************************       //~vay1I~
//  public void restoreAllowTopLandscape(Point PframeLayoutSize)   //~vay1R~//~vayvR~
    public void restoreAllowTopLandscape()                         //~vayvI~
    {                                                              //~vay1I~
        if (Dump.Y) Dump.println(CN+"restoreAllowTopLandscape portrait="+AG.portrait+",frameLayoutSize="+frameLayoutSize);//~vay1R~//~vayvR~
//      setFrameLayoutSize(PframeLayoutSize);                      //~vay1R~
//     	Point p=new Point(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);//~vay1R~
//      Point p=PframeLayoutSize;                                  //~vay1I~//~vayvR~
        Point p=frameLayoutSize;                                   //~vayvI~
        if (Dump.Y) Dump.println(CN+"restoreAllowTopLandscape force frameLayoutSize="+p);//~vay1I~//~vayvR~
//      setFrameLayoutSize(p);                                     //~vay1M~//~vayNR~
        setFrameLayoutSize(frameLayoutSizeBeforeStartGame);        //~vayNI~
        frameLayoutSize=frameLayoutSizeBeforeStartGame;            //~vayNI~
        hideImage(false);                                          //~vay1R~
//    	resetImage(null);   //restore bmpTop                                           //~vay1I~//~vayuR~//~vayvR~
      	resetImage(true/*restore*/);   //restore bmpTop            //~vayvI~
        hideTitle(false/*hide*/);                                  //~vay1R~
        hideButtons(false/*hide*/);                                //~vay1R~
//        imageView.setImageBitmap(bmpLastTop); //at astartGame    //~vay1R~
        drawMsg("");                                               //~vay1R~
    }                                                              //~vay1I~
//**********************************************************       //~v@21I~
//  private void setFrameLayoutSize()                              //~v@21R~
    private void setFrameLayoutSize(Point Psize)                   //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println(CN+"setFrameLayoutSize frameLayout Psize="+Psize);//~v@21R~//~9620R~//~vay1R~//~vayjR~
        if (Dump.Y) Dump.println(CN+"setFrameLayoutSize frameLayout="+frameLayout);//~vay1I~
//	    ViewGroup.LayoutParams lp=frameLayout.getLayoutParams();             //~v@21I~//~vayhR~
//		ViewGroup.LayoutParams lp=main.frameLayoutParent.getLayoutParams();//~vayhR~
//      if (true)                                                  //~v@21R~
//      	return;	//TODO test                                    //~v@21R~
//        if (true)   //TODO test                                  //~vayvR~
//        {                                                        //~vayvR~
//            if (Dump.Y) Dump.println(CN+"setFrameLayoutSize return NOP TEST frameLayoutSize="+Psize);//~vayvR~
//            return;                                              //~vayvR~
//        }                                                        //~vayvR~
        if (Dump.Y) Dump.println("MainView.setFrameLayoutSize frameLayout Psize="+Psize.toString());//~v@21I~//~9620R~
//      lp.width=frameLayoutWW;                                    //~v@21R~
//      lp.height=frameLayoutHH;                                   //~v@21R~
//      lp.width=Psize.x;                                          //~v@21I~//~vayhR~
//      lp.height=Psize.y;                                         //~v@21I~//~vayhR~
  		ViewGroup.LayoutParams lp=new LinearLayout.LayoutParams(Psize.x,Psize.y);//~vayhI~
	    frameLayout.setLayoutParams(lp);                           //~v@21I~
        if (Dump.Y) Dump.println(CN+"setFrameLayoutSize return frameLayout="+frameLayout);//~vay1I~
    }                                                              //~v@21I~
////**********************************************************     //~v@21R~
//    private void restoreTitleBtn()                               //~v@21R~
//    {                                                            //~v@21R~
//        if (Dump.Y) Dump.println("MainView.restoreTitleBtn");//~v@21R~//~9620R~
//        hideTitle(false/*hide*/);                                //~v@21R~
//        hideButtons(false/*hide*/);                              //~v@21R~
//    }                                                            //~v@21R~
////**********************************************************     //~v@21R~
//    private void resetTitleBtn()                                 //~v@21R~
//    {                                                            //~v@21R~
//        if (Dump.Y) Dump.println("MainView.restoreTitleBtn");//~v@21R~//~9620R~
//        hideTitle(true/*hide*/);                                 //~v@21R~
//        hideButtons(true/*hide*/);                               //~v@21R~
//        restoreTitleBtn();                                       //~v@21R~
//    }                                                            //~v@21R~
//    //*************************************************************************//~v@21R~
//    @Override                                                    //~v@@@R~//~v@21R~
//    public void onDraw(Canvas Pcanvas/*android.graphics.Canvas*/)                           //~v@@@R~//~v@21R~
//    {                                                            //~v@@@R~//~v@21R~
//        if (Dump.Y) Dump.println("MainView.onDraw WW="+WW+",HH="+HH+",btnsHH="+btnsHH+",titleHH="+titleHH);             //~v@@@R~//~v@21R~//~9620R~
//        imageHH=HH-btnsHH;                                       //~v@21R~
//        Rect dst=new Rect(0,0,WW,imageHH);                       //~v@21R~
//        Rect src=new Rect(0,0,WW,imageHH);                       //~v@21R~
//        Pcanvas.drawBitmap(bmpTop,src,dst,null/*paint*/);        //~v@21R~
//    }                                                            //~v@@@R~//~v@21R~
//    //*************************************************************************//~v@21R~
//    @Override                                                    //~v@21R~
//    public void onLayout(boolean Pchanged,int Pleft,int Ptop,int Pright,int Pbottom)//~v@21R~
//    {                                                            //~v@21R~
//        if (Dump.Y) Dump.println("MainView.onLayout changed="+Pchanged+",l="+Pleft+",t="+Ptop+",r="+Pright+",b="+Pbottom);//~v@21R~//~9620R~
//        super.onLayout(Pchanged,Pleft,Ptop,Pright,Pbottom);      //~v@21R~
//        if (!swRestore)                                          //~v@21R~
//            return;                                              //~v@21R~
//        if (true)                                                //~v@21R~
//            return;                                              //~v@21R~
//        if (Dump.Y) Dump.println("MainView.onLayout rect of view l="+this.getLeft()+",t="+this.getTop()+",r="+this.getRight()+",b="+this.getBottom());//~v@21R~//~9620R~
//        int ww=this.getRight();                                  //~v@21R~
//        int hh=this.getBottom();                                 //~v@21R~
//        if (ww!=WW||hh!=imageHH)                                 //~v@21R~
//        {                                                        //~v@21R~
//            if (Dump.Y) Dump.println("MainView.onLayout set right="+WW+",bottom=imageHH="+imageHH);//~v@21R~//~9620R~
//            this.layout(this.getLeft(),this.getTop(),WW,imageHH);//~v@21R~
//        }                                                        //~v@21R~
//        else                                                     //~v@21R~
//            resetTitleBtn();                                     //~v@21R~
//    }                                                            //~v@21R~
	//*************************                                    //~v@21I~
    private void hideImage(boolean PswHide)                        //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println(CN+"hideImage swHide="+PswHide);//~v@21I~//~vay1R~
        if (Dump.Y) Dump.println(CN+"hideImage frameLayout="+frameLayout);//~vay1I~
        if (Dump.Y) Dump.println(CN+"hideImage imageView="+imageView);//~vay1I~
        if (PswHide)                                               //~v@21I~
        {                                                          //~vay1I~
//          frameLayout.removeView(this);                          //~v@21R~
            frameLayout.removeView(imageView);                     //~v@21I~
    	    if (Dump.Y) Dump.println(CN+"hideImage remove imageView="+imageView);//~vay1R~
        }                                                          //~vay1I~
        else                                                       //~v@21I~
        {                                                          //~vay1I~
//          if (true)                                              //~v@21R~
//          frameLayout.addView(this);                             //~v@21R~
            addImageView();                                        //~v@21I~
//          else                                                   //~v@21R~
//            setImage(); //set PFLAG_DRAW on to invalidate()      //~v@21R~
    		if (Dump.Y) Dump.println(CN+"hideImage addView imageView");//~vay1I~
        }                                                          //~vay1I~
    }                                                              //~v@21I~
	//*************************                                    //~v@21I~
    private void hideTitle(boolean Pswhide)                        //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println(CN+"hideTitle swHide="+Pswhide);//~v@21I~//~vay1R~
        if (Dump.Y) Dump.println(CN+"hideTitle frameLayout="+frameLayout);//~vay1I~
//      if (false)                                                 //~v@21R~
//        titleMain.setVisibility(Pswhide ? View.GONE : View.VISIBLE);//~v@21R~
//      else                                                       //~v@21R~
//      {                                                          //~v@21R~
        if (Pswhide)                                               //~v@21I~
        {                                                          //~v@21I~
        	if (Dump.Y) Dump.println(CN+"hideTitle remove titleMain view ww="+titleMain.getMeasuredWidth()+",hh="+titleMain.getMeasuredHeight());//~v@21I~//~9620R~//~vay1R~
//            titleHH=titleMain.getMeasuredHeight();               //~v@21R~
            frameLayout.removeView(titleMain);                     //~v@21I~
        }                                                          //~v@21I~
        else                                                       //~v@21I~
        {                                                          //~vay1I~
        	if (Dump.Y) Dump.println(CN+"hideTitle add titleMain view ww="+titleMain.getMeasuredWidth()+",hh="+titleMain.getMeasuredHeight());//~vay1I~
            frameLayout.addView(titleMain);                        //~v@21I~
        }                                                          //~vay1I~
//      }                                                          //~v@21R~
    }                                                              //~v@21I~
	//*************************                                    //~v@21I~
    private void hideButtons(boolean Pswhide)                      //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println(CN+"hideButtons swHide="+Pswhide);//~v@21I~//~vay1R~
        if (Dump.Y) Dump.println(CN+"hideButtons frameLayout="+frameLayout);//~vay1I~
//      if (false) //TODO test                                     //~v@21R~
//        btnsMain.setVisibility(Pswhide ? View.GONE : View.VISIBLE);//~v@21R~
//      else                                                       //~v@21R~
//      {                                                          //~v@21R~
        if (Pswhide)                                               //~v@21I~
        {                                                          //~v@21I~
        	if (Dump.Y) Dump.println("MainView.hideButtons remove buttonview view ww="+btnsMain.getMeasuredWidth()+",hh="+btnsMain.getMeasuredHeight());//~v@21I~//~9620R~//~vay1R~
//            btnsHH=btnsMain.getMeasuredHeight();                 //~v@21R~
            frameLayout.removeView(btnsMain);                      //~v@21I~
	        if (main.swAllowTopLandscape)                          //~vayVI~
    	    {                                                      //~vayVI~
	            frameLayout.removeView(btnsMainLock);              //~vayVI~
        	}                                                      //~vayVI~
        }                                                          //~v@21I~
        else                                                       //~v@21I~
        {                                                          //~v@21I~
        	if (Dump.Y) Dump.println("MainView.hideButtons add buttonview view ww="+btnsMain.getMeasuredWidth()+",hh="+btnsMain.getMeasuredHeight());//~vay1I~
//          if (false)                                             //~v@21R~
//          {                                                      //~v@21R~
//            int wc=ViewGroup.LayoutParams.WRAP_CONTENT;          //~v@21R~
//            int mp=ViewGroup.LayoutParams.MATCH_PARENT;          //~v@21R~
//            FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(mp,wc);//~v@21R~
//            if (Dump.Y) Dump.println("MainView.hideButtons default margin t="+lp.topMargin+",b="+lp.bottomMargin+",l="+lp.leftMargin+",r="+lp.rightMargin);//~v@21R~//~9620R~
////            lp.topMargin=-40;                                  //~v@21R~
//                                                                 //~v@21R~
////          lp.gravity=Gravity.BOTTOM|Gravity.CENTER;    //=layout_gravity//~v@21R~
//            lp.gravity=Gravity.BOTTOM;    //=layout_gravity      //~v@21R~
////          lp.gravity=Gravity.FILL_VERTICAL|Gravity.CENTER;    //=layout_gravity//~v@21R~
//            btnsMain.setLayoutParams(lp);                        //~v@21R~
//            frameLayout.addView(btnsMain);                       //~v@21R~
//          }                                                      //~v@21R~
//          else                                                   //~v@21R~
//          {                                                      //~v@21R~
//            frameLayout.addView(btnsMain,-1/*last*/);            //~v@21R~
//      if (false)           //TODO test                           //~v@21R~//~0322R~
//          frameLayout.addView(btnsMain);                         //~v@21I~//~0322R~
//      else                                                       //~v@21R~//~0322R~
        {                                                          //~v@21I~
//  		addButtons2();                                         //~v@21R~
//  		addButtons();                                          //~v@21I~
//          btnsMain=AG.inflater.inflate(MAIN_BUTTONS,null);       //~v@21I~//~9411R~
//    if (false)                                                   //~9411I~
//    {                                                            //~9411I~
//            int wc=ViewGroup.LayoutParams.WRAP_CONTENT;            //~v@21I~//~9411R~
//            int mp=ViewGroup.LayoutParams.MATCH_PARENT;            //~v@21I~//~9411R~
//            FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(mp,wc);//~v@21I~//~9411R~
////          lp.gravity=Gravity.BOTTOM|Gravity.CENTER;    //=layout_gravity//~v@21I~//~9411R~
//            lp.gravity=Gravity.BOTTOM;    //=layout_gravity        //~v@21R~//~9411R~
////          lp.gravity=Gravity.CENTER;                             //~v@21R~//~9411R~
////          lp.gravity=Gravity.FILL_VERTICAL|Gravity.CENTER;    //=layout_gravity//~v@21I~//~9411R~
//            btnsMain.setLayoutParams(lp);                          //~v@21I~//~9411R~
//    }//TODO test                                                 //~9411I~
		    restoreButtonLP();                                     //~vaykI~
            frameLayout.addView(btnsMain);                         //~v@21I~
	        if (main.swAllowTopLandscape)                          //~vayVI~
    	    {                                                      //~vayVI~
	            frameLayout.addView(btnsMainLock);                 //~vayVI~
        	}                                                      //~vayVI~
        }                                                          //~v@21R~
        }                                                          //~v@21I~
//      }                                                          //~v@21R~
    }                                                              //~v@21I~
    //******************************************                   //~vaykI~
    private void restoreButtonLP()                                 //~vaykI~
    {                                                              //~vaykI~
                                                                   //~vaykI~
        if (Dump.Y) Dump.println(CN+"retoreButtonLP");             //~vaykI~
        FrameLayout.LayoutParams lp=(FrameLayout.LayoutParams)(btnsMain.getLayoutParams()); //~vaykI~
        if (Dump.Y) Dump.println(CN+"restoreButtonLP lp="+lp);     //~vaykI~
//      if (true) //TEST                                           //~vayxR~
//      {                                                          //~vayxR~
//        if (AG.osVersion>=AG.APIVER_EDGEMODE)                    //~vayxR~
//        {                                                        //~vayxR~
//            if (Dump.Y) Dump.println(CN+"addButtons SDK_INT="+AG.osVersion);//~vayxR~
//            lp.setMargins(0/*left*/,0/*top*/,0/*right*/,0);      //~vayxR~
//        }                                                        //~vayxR~
//      }                                                          //~vayxR~
//      else                                                       //~vayxR~
      if (false)                                                   //~vayxI~
      	if (AG.swNewA10)                                           //~vaykI~
	    	if (Build.VERSION.SDK_INT==29)   //for gesture navigationbar//~vaykI~
        	{                                                      //~vaykI~
        		if (Dump.Y) Dump.println(CN+"addButtons SDK_INT=29 reset margin");//~vaykI~
        		lp.setMargins(0/*left*/,0/*top*/,0/*right*/,0);    //~vaykI~
		        if (Dump.Y) Dump.println(CN+"restoreButtonLP clear margin lp="+lp);//~vaykI~
            }                                                      //~vaykI~
    }                                                              //~vaykI~
    //******************************************                   //~9619I~
    public static void drawMsg(String Pmsg)                        //~9619R~
    {                                                              //~9619I~
        if (Dump.Y) Dump.println("MainView.drawMsg msgbar="+Pmsg);//~9619R~//~9620R~//~9704R~
        AG.aMainView.strMsgBar=Pmsg;                                             //~9621I~
        if (AG.isMainThread())                                     //~9621I~
        {                                                          //~9621I~
//	        AG.aMainView.topMsgBar.setText(Pmsg);           //~9619I~  //~9620R~//~9621R~//~vaf5R~
  	        AG.aMainView.drawMsgAdjusted(Pmsg);                    //~vaf5I~
	        if (Dump.Y) Dump.println("MainView.drawMsg setText msg="+Pmsg);//~9621I~
        }                                                          //~9621I~
        else                                                       //~9621I~
        {                                                          //~9621I~
            AG.activity.runOnUiThread(                                 //~9305I~//~9621I~
                new Runnable()                                         //~9305I~//~9621I~
                {                                                      //~9305I~//~9621I~
                    @Override                                          //~9305I~//~9621I~
                    public void run()                                       //~9305I~//~9621I~
                    {                                                  //~9305I~//~9621I~
                        try                                            //~9305I~//~9621I~
                        {                                              //~9305I~//~9621I~
                            if (Dump.Y) Dump.println("MainView.drawMsg runonUiThread.run");//~9305I~//~9314R~//~9621R~
//  				        AG.aMainView.topMsgBar.setText(AG.aMainView.strMsgBar);//~9621R~//~vaf5R~
    				        AG.aMainView.drawMsgAdjusted(AG.aMainView.strMsgBar);//~vaf5I~
                        }                                              //~9305I~//~9621I~
                        catch(Exception e)                             //~9305I~//~9621I~
                        {                                              //~9305I~//~9621I~
                            Dump.println(e,"MainView.drawMsg:runOnUiThread");  //~9305I~//~9314R~//~9621R~
                        }                                              //~9305I~//~9621I~
                    }                                                  //~9305I~//~9621I~
                }                                                      //~9305I~//~9621I~
                                      );                               //~9305I~//~9621I~
		}                                                          //~9621I~
    }                                                              //~9619I~
    //******************************************                   //~9619I~
    public static void drawMsg(int Pmsgid)                         //~9619R~
    {                                                              //~9619I~
        if (Dump.Y) Dump.println("MainView.drawMsg msgid="+Integer.toHexString(Pmsgid));//~9619R~//~9620R~
	    drawMsg(Utils.getStr(Pmsgid));                             //~9619R~
    }                                                              //~9619I~
    //******************************************                   //~9621I~
    public static void clearMsg()                                  //~9621I~
    {                                                              //~9621I~
        if (Dump.Y) Dump.println("MainView.drawMsg clearMsg");     //~9621I~
	    drawMsg("");                                               //~9621I~
    }                                                              //~9621I~
    //******************************************                   //~9621I~
    public static void drawMsg(int Pmsgid,String Pparm)           //~9621I~
    {                                                              //~9621I~
        if (Dump.Y) Dump.println("MainView.drawMsg msgid="+Integer.toHexString(Pmsgid)+",parm="+Pparm);//~9621R~
	    drawMsg(Utils.getStr(Pmsgid,Pparm));                       //~9621I~
    }                                                              //~9621I~
    //******************************************                   //~9620I~
    public void enableStartGame(boolean PswEnable)                 //~9620I~
    {                                                              //~9620I~
        if (Dump.Y) Dump.println("MainView.enableStartGame enable="+PswEnable);//~9620R~
    	btnStartGame.setEnabled(PswEnable);                        //~9620I~
    }                                                              //~9620I~
    //******************************************                   //~vaf5I~
    private void  drawMsgAdjusted(String Pmsg)                     //~vaf5I~
    {                                                              //~vaf5I~
        if (Dump.Y) Dump.println("MainView.drawMsgAdjusted msg="+Pmsg);//~vaf5I~
        if (Pmsg.equals(""))                                       //~vaf5I~
        {                                                          //~vaf5I~
			topMsgBar.setText(Pmsg);                               //~vaf5I~
            return;                                                //~vaf5I~
        }                                                          //~vaf5I~
//      float pixelTextSize=topMsgBar.getTextSize();               //~vaf5R~
        int pixelWW=topMsgBar.getWidth();                          //~vaf5I~
        float pixelTextSizeNew= GMsg.adjustTextSize(pixelTextSize,pixelWW,Pmsg);//~vaf5I~
        int unit= TypedValue.COMPLEX_UNIT_PX;                       //~vaf5I~
        if (pixelTextSizeNew!=pixelTextSize)                       //~vaf5I~
        {                                                          //~vaf5I~
        	if (Dump.Y) Dump.println("MainView.drawMsgAdjusted setTextSize="+pixelTextSizeNew);//~vaf5I~
	        topMsgBar.setTextSize(unit,pixelTextSizeNew);          //~vaf5I~
        }                                                          //~vaf5I~
		topMsgBar.setText(Pmsg);                                   //~vaf5I~
//      if (pixelTextSizeNew!=pixelTextSize)                       //~vaf5R~
//      {                                                          //~vaf5R~
//          topMsgBar.setTextSize(unit,pixelTextSize);             //~vaf5R~
//      }                                                          //~vaf5R~
    }                                                              //~vaf5I~
////*****************************************************************************************//~vaytR~
////*Not used, UVIew set AG.scrHeight by the condition of API35 & 3 button & portrait//~vaytI~
////*****************************************************************************************//~vaytI~
//    private int getEdgeHeight()                                  //~vaytR~
//    {                                                            //~vaytR~
//        int hhEdge=0;                                            //~vaytR~
//        if (!AG.portrait)                                        //~vaytR~
//            hhEdge=AG.scrHeightReal;                             //~vaytR~
//        else                                                     //~vaytR~
//        if (AG.swNavigationbarGestureMode)                       //~vaytR~
//            hhEdge=AG.scrHeightReal;                             //~vaytR~
//        else    //3button mode                                   //~vaytR~
//        if (UView.isEdgeMode()) //EdgetoEdge mode(API>=35)       //~vaytR~
//            hhEdge=AG.scrHeightReal-AG.scrNavigationbarBottomHeightA11;  //port,3button,api35//~vaytR~
//        if (Dump.Y) Dump.println(CN+"getEdgeHeight AG.scrHeightReal="+AG.scrHeightReal+",AG.scrNavigationbarBottomHeightA11="+AG.scrNavigationbarBottomHeightA11);//~vaytR~
//        if (Dump.Y) Dump.println(CN+"getEdgeHeight hhEdge="+hhEdge+",AG.portrait="+AG.portrait+",AG.swNavigationbarGestureMode="+AG.swNavigationbarGestureMode);//~vaytR~
//        return hhEdge;                                           //~vaytR~
//    }                                                            //~vaytR~
//*****************************************************************************************//~vaytI~
	private int getMarginH()                                       //~vaytI~
    {                                                              //~vaytI~
        int hhMargin=0;                                            //~vaytI~
      if (false)	//3button navi was excluded by getEdgeHeight for API35//~vaytI~
        if (AG.portrait                                            //~vaytI~
        &&  !AG.swNavigationbarGestureMode //3button              //~vaytI~
        &&  UView.isEdgeMode()	//EdgetoEdge mode(API>=35)         //~vaytI~
        )                                                          //~vaytI~
        	hhMargin=AG.scrNavigationbarBottomHeightA11;           //~vaytR~
		if (Dump.Y) Dump.println(CN+"getMarginH AG.scrHeightReal="+AG.scrHeightReal+",AG.scrNavigationbarBottomHeightA11="+AG.scrNavigationbarBottomHeightA11);//~vaytR~
		if (Dump.Y) Dump.println(CN+"getMarginH hhMargin="+hhMargin+",AG.portrait="+AG.portrait+",AG.swNavigationbarGestureMode="+AG.swNavigationbarGestureMode);//~vaytR~
        return hhMargin;                                           //~vaytI~
    }                                                              //~vaytI~
//*****************************************************************************************//~vayvR~
//	private Point getFrameLayoutSize()                             //~vayhR~//~vayNR~
  	private Point getFrameLayoutSize(Boolean PswTop)               //~vayNI~
    {                                                              //~vayhI~
	    if (Dump.Y) Dump.println(CN+"getFrameLayoutSize PswTop="+PswTop+",edgeMode="+UView.isEdgeMode());//~vayNR~
	    if (Dump.Y) Dump.println(CN+"getFrameLayoutSize AG.foldingFeature="+AG.foldingFeature+",AG.portrait="+AG.portrait+",gestureMode="+AG.swNavigationbarGestureMode+",AG.scrHeight="+AG.scrHeight+",AG.scrWidth="+AG.scrWidth+",AG.scrHeightReal="+AG.scrHeightReal);//~vayNI~
	    if (Dump.Y) Dump.println(CN+"getFrameLayoutSize frameLayout ww="+frameLayout.getWidth()+",hh="+frameLayout.getHeight());//~vayhI~//~vayvR~
		if (Dump.Y) Dump.println(CN+"getFrameLayoutSize frameLayout MeasuredSize="+UView.getMeasuredSize(frameLayout));//~vayrI~
		if (Dump.Y) Dump.println(CN+"getFrameLayoutSize swAllowTopLandscale="+main.swAllowTopLandscape);//~vayvR~
        Point p;                                                   //~vayhI~
        if (UView.isEdgeMode())                                    //~vayNI~
			return getFrameLayoutSizeEdgeMode(PswTop);              //~vayNI~
        if (AG.portrait)                                           //~vayhI~
        {                                                          //~vayhI~
//	        int hhEdge=getEdgeHeight();                            //~vaytR~
//        	if (frameLayoutSizePortrait==null)                     //~vayhI~//~vayvR~
//          {                                                      //~vayhI~//~vayvR~
//        		frameLayoutSizePortrait=UView.getMeasuredSize(frameLayout);//~vayhR~
//          	frameLayoutSizePortrait=new Point(AG.scrWidth,AG.scrHeight);//~vayhR~//~vayvR~
//            	frameLayoutSize=new Point(AG.scrWidth,AG.scrHeight);//~vayvI~//~vaytR~
//              frameLayoutSize=new Point(AG.scrWidth,hhEdge==0 ? AG.scrHeight : hhEdge);//~vaytR~
            	frameLayoutSize=new Point(AG.scrWidth,AG.scrHeight);//~vaytI~
	        	if (Dump.Y) Dump.println(CN+"getFrameLayoutSize Portrait new frameLayoutSize="+frameLayoutSize);//~vayhR~//~vayvR~
//          }                                                      //~vayhI~//~vayvR~
//          p=frameLayoutSizePortrait;                             //~vayhI~//~vayvR~
            p=frameLayoutSize;                                     //~vayvI~
          if (!PswTop)	//inGame                                   //~vayQI~
          	if (AG.osVersion==AG.APIVER_GESTUREMODE) //api29(Android-10)dragonTouch//~vayQR~
          	{                                                      //~vayQR~
		    	p.y+=AG.scrNavigationbarBottomHeight;              //~vayQR~
	    		if (Dump.Y) Dump.println(CN+"getFrameLayoutSize inGame add AG.scrNavigationBarBottomHeight="+AG.scrNavigationbarBottomHeight+",p.y="+p.y);//~vayQR~
          	}                                                      //~vayQR~
          if (false) //TEST                                        //~vaytI~
          {                                                        //~vayrI~
	        int hh=frameLayout.getHeight();                        //~vaynI~
            if (p.y!=0 && hh!=0)                                   //~vaynR~
	  			if (Build.VERSION.SDK_INT>29)	//api29 frameLayout.getHeight() may return real height//~vaynI~
                {                                                  //~vayrI~
		        	if (Dump.Y) Dump.println(CN+"getFrameLayoutSize portrait 29<api="+Build.VERSION.SDK_INT+" update portrait height by frameLayout "+p.y+" --> "+hh);//~vayrR~//~vayvR~
                  if (hh<p.y)   //api35 framelayoutH=realH         //~vayrI~
		          	p.y=hh;                                        //~vaynR~
                }                                                  //~vayrI~
          }                                                        //~vayrI~
        }                                                          //~vayhI~
        else                                                       //~vayhI~
        {                                                          //~vayhI~
//        	if (frameLayoutSizeLandscape==null)                    //~vayhI~//~vayvR~
//          {                                                      //~vayhI~//~vayvR~
//             if (AG.swNavigationbarGestureMode)                   //~vayqI~//~vayrR~
//          	    frameLayoutSizeLandscape=new Point(AG.scrWidth,AG.scrHeightReal);//~vayhR~//~vayvR~
          	    frameLayoutSize=new Point(AG.scrWidth,AG.scrHeightReal);//~vayvI~
//            else                                                 //~vayqI~//~vayrR~
//        	    frameLayoutSizeLandscape=new Point(AG.scrWidth,AG.scrHeight);//~vayqI~//~vayrR~
//        	  if (swAllowTopLandscape)  //folding feature          //~vayrI~//~vayvR~
              if (main.swAllowTopLandscape)                        //~vayvR~
              {                                                    //~vayrI~
	        	if (Dump.Y) Dump.println(CN+"getFrameLayoutSize allowTopLandscape set AG.scrHeight="+AG.scrHeight);//~vayrI~//~vayvR~
//          	frameLayoutSizeLandscape.y=AG.scrHeight;           //~vayrI~//~vayvR~
          	    frameLayoutSize.y=AG.scrHeight;                    //~vayvI~
              }                                                    //~vayrI~
	        	if (Dump.Y) Dump.println(CN+"getFrameLayoutSize frameLayoutSize="+frameLayoutSize);//~vayhR~//~vayqR~//~vayvR~
//          }                                                      //~vayhI~//~vayvR~
//          p=frameLayoutSizeLandscape;                            //~vayhI~//~vayvR~
            p=frameLayoutSize;                                     //~vayvI~
        }                                                          //~vayhI~
	    if (Dump.Y) Dump.println(CN+"getFrameLayoutSize return frameLayoutSize="+p);//~vayhI~//~vayvR~
        return p;                                                  //~vayhI~
    }                                                              //~vayhI~
	private Point getFrameLayoutSizeEdgeMode(boolean PswTop)       //~vayNI~
    {                                                              //~vayNI~
	    if (Dump.Y) Dump.println(CN+"getFrameLayoutSizeEdgeMode PswTop="+PswTop+",gestureMode="+AG.swNavigationbarGestureMode+",AG.scrNavigationbarBottomHeightA11="+AG.scrNavigationbarBottomHeightA11);//~vayNI~
        Point p=new Point();                                                   //~vayNI~
        int ww=AG.scrWidth;     //real                             //~vayNI~
        int hh=AG.scrHeight;    //real;                            //~vayNI~
        if (PswTop) //top panel                                    //~vayNI~
        {                                                          //~vayNI~
	        //*gesture mode navigation bar is transparent,use full screen//~vayNI~
            if (!AG.swNavigationbarGestureMode)//3button           //~vayNI~
            {                                                      //~vayNI~
            	hh-=AG.scrNavigationbarBottomHeightA11;            //~vayNI~
            }                                                      //~vayNI~
        }                                                          //~vayNI~
        p.x=ww; p.y=hh;                                            //~vayNI~
	    if (Dump.Y) Dump.println(CN+"getFrameLayoutSizeEdgeMode return frameLayoutSize="+p);//~vayNR~
        return p;                                                  //~vayNI~
    }                                                              //~vayNI~
    //*********************************************************************//~vayVI~
    public void setLockButton(boolean PswLock)                     //~vayVR~
    {                                                              //~vayVI~
        if (Dump.Y) Dump.println(CN + "setLockButton Plock="+PswLock+",swAllowTopLandscape="+main.swAllowTopLandscape+",btnsMainLock="+btnsMainLock);//~vayVR~
	    if (btnsMainLock==null)                                    //~vayVI~
        	return;                                                //~vayVI~
        if (PswLock)                                                //~vayVI~
        {                                                          //~vayVI~
            btnLock.setVisibility(View.GONE);                      //~vayVR~
            btnUnLock.setVisibility(View.VISIBLE);                 //~vayVR~
	        if (Dump.Y) Dump.println(CN + "setLockButton unlock visible");//~vayVI~
//          UView.showToastLongDirect((Context)main,"Locked");     //~vayVR~
        }                                                          //~vayVI~
        else                                                       //~vayVI~
        {                                                          //~vayVI~
            btnLock.setVisibility(View.VISIBLE);                   //~vayVR~
            btnUnLock.setVisibility(View.GONE);                    //~vayVR~
	        if (Dump.Y) Dump.println(CN + "setLockButton lock visible");//~vayVI~
//          UView.showToastLongDirect((Context)main,"UnLocked");   //~vayVR~
        }
    }//~vayVI~
}//class MainView                                       //~v@21R~  //~9620R~
