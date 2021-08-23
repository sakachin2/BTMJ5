//*CID://+vac5R~: update#= 409;                                    //~vac5R~
//**********************************************************************
//2021/08/15 vac5 phone device(small DPI) support; use small size font//~vac5I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//**********************************************************************//~va66I~
package com.btmtest;                                               //~v@21R~

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
//    private static final int MAIN_MSGBAR =R.layout.main_msgbar;    //~9619I~//~9620R~
                                                                   //~v@21I~
    private FrameLayout frameLayout;                               //~v@21I~
    private MainActivity main;                                     //~v@21I~
    private View  btnsMain,titleMain;                              //~v@21I~
    private TextView  topMsgBar;                                       //~9619I~
    private Button btnSettings,btnConnect,btnHelp,btnStartGame;    //~v@21I~//~0119R~
    private Button btnTest1;              //TODO test              //~v@21I~
    private Button btnHistory;                                     //~0322R~
//  private static Bitmap bmpTop;                                  //~v@21R~
    private        Bitmap bmpTop;                                  //~v@21I~
    private ImageView imageView;                                   //~v@21I~
    private int WW,HH,imageHH,titleHH,btnsHH;                      //~v@21R~
//  private int frameLayoutHH,frameLayoutWW;                       //~v@21R~
//    private boolean swRestore;                                   //~v@21R~
    private String strMsgBar;                                      //~9621I~
    //**************************************************************//~v@@@R~
    public MainView(MainActivity Pmain,FrameLayout PframeLayout)//~v@21R~//~9620R~
    {
//      super(Pmain);                                           //~v@21M~
        AG.aMainView=this;                              //~9619I~  //~9620R~
    	main=Pmain;                                                //~v@21I~
    	frameLayout=PframeLayout;                                  //~v@21I~
        WW = AG.scrWidthReal;                                      //~v@21I~
        AG.scrPortraitWW =WW;	//by manifest topview is portrait  //~v@21I~
	    HH = AG.scrHeight;                                         //~v@21I~
        if (Dump.Y) Dump.println("MainView Constructor WW="+WW+",HH="+HH);//~v@21R~//~9620R~
    }
	//*************************                                    //~v@21I~
    public void reset()                                                //~v@21I~
    {                                                              //~v@21I~
        if(Dump.Y) Dump.println("MainView:reset");      //~v@21R~  //~9620R~
        if (bmpTop!=null)                                          //~v@21I~
        {                                                          //~v@21I~
        	UView.recycle(bmpTop);                                 //~v@21I~
            bmpTop=null;                                           //~v@21I~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
	//*************************************************************************//~v@21I~
    public void init()                                             //~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("MainView.init");      //~v@21R~  //~9620R~
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
    }                                                              //~v@21I~
	//*****************************************************************//~v@21R~
    private void setImage()                                        //~v@21I~
    {                                                              //~v@21I~
        if (bmpTop==null)                                          //~v@21I~
        {                                                          //~v@21I~
//          bmpTop=BitmapFactory.decodeResource(AG.resource,RID_TOPIMAGE);//~v@21R~
            Bitmap bmpSrc=BitmapFactory.decodeResource(AG.resource,RID_TOPIMAGE);//~v@21I~
            if (WW==bmpSrc.getWidth() && HH==bmpSrc.getHeight())      //~v@21I~
            	bmpTop=bmpSrc;                                     //~v@21I~
            else                                                   //~v@21I~
            {                                                      //~v@21I~
	 	    	if (Dump.Y) Dump.println("MainView.setImage bmp Src WW="+bmpSrc.getWidth()+",HH="+bmpSrc.getHeight());//~v@21R~//~9620R~
		        Bitmap bmpScaled=Bitmap.createScaledBitmap(bmpSrc,WW,HH,true/*filter*/);//~v@21I~
	  		    if (Dump.Y) Dump.println("MainView.setImage bmpScaled WW="+WW+",HH="+HH);//~v@21I~//~9620R~
                bmpTop=bmpScaled;                                  //~v@21I~
                UView.recycle(bmpSrc);                             //~v@21I~
            }                                                      //~v@21I~
        }                                                          //~v@21I~
        if (Dump.Y) Dump.println("MainView.setImage bmp WW="+bmpTop.getWidth()+",HH="+bmpTop.getHeight());//~v@21I~//~9620R~
//  	imageView=new ImageView(this);                             //~v@21R~
    	imageView=new ImageView(AG.context);                       //~v@21I~
        imageView.setImageBitmap(bmpTop);                          //~v@21R~
//      setImageBitmap(bmpTop);                                    //~v@21R~
        addImageView();                                            //~v@21R~
    }                                                              //~v@21I~
	//*************************                                    //~v@21I~
    private void addTitle()                                        //~v@21I~
    {                                                              //~v@21I~
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
        if (Dump.Y) Dump.println("MainView.addButtons");//~v@21R~  //~9620R~
		int wc=ViewGroup.LayoutParams.WRAP_CONTENT;                //~v@21I~
		int mp=ViewGroup.LayoutParams.MATCH_PARENT;                //~v@21I~
        FrameLayout.LayoutParams lp;                               //~v@21I~
        lp=new FrameLayout.LayoutParams(mp,wc);                    //~v@21I~
//      btnsMain=AG.inflater.inflate(MAIN_BUTTONS,null);           //~v@21I~//~vac5R~
        btnsMain=AG.inflater.inflate((AG.swSmallFont ? MAIN_BUTTONS_SMALLFONT : MAIN_BUTTONS),null);//~vac5I~
        topMsgBar=(TextView)    UView.findViewById(btnsMain,R.id.TopMsgBar);//~9620I~
//      lp.gravity=Gravity.BOTTOM|Gravity.CENTER;    //=layout_gravity//~v@21R~
        lp.gravity=Gravity.BOTTOM;    //=layout_gravity            //~v@21I~
        btnsMain.setLayoutParams(lp);                              //~v@21I~
//      btnsMain.setVisibility(View.INVISIBLE);                    //~v@21R~
//      btnsMain.setVisibility(View.VISIBLE);                      //~v@21R~
        frameLayout.addView(btnsMain);                             //~v@21I~
                                                                   //~v@21I~
//        ViewGroup.LayoutParams lpvg=btnsMain.getLayoutParams();  //~v@21R~
//        if (Dump.Y) Dump.println("MainView.addButtons getlayoutparm ww="+lpvg.width+".hh="+lpvg.height);//~v@21R~//~9620R~
        if (Dump.Y) Dump.println("MainView.addButtons view ww="+btnsMain.getMeasuredWidth()+",hh="+btnsMain.getMeasuredHeight());//~v@21I~//~9620R~
                                                                   //~v@21I~
        if (Dump.Y) Dump.println("MainView.addButtons btnsMain="+btnsMain.toString());//~v@21R~//~9620R~
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
//      if (AG.isDebuggable)                                       //~0316I~//+vac5R~
//          btnTest1.setVisibility(View.VISIBLE);                  //~0316I~//+vac5R~
    }                                                              //~v@21I~
	//*************************                                    //~v@21I~
    private void setButtonStatus(boolean PswConnected)             //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("MainView.setButtonStatus swconnected="+PswConnected);//~v@21R~//~9620R~
//  	btnStartGame.setEnabled(PswConnected);                     //~v@21I~//~9620R~
    	enableStartGame(PswConnected);                              //~9620I~
    }                                                              //~v@21I~
	//*************************************************************************//~v@21I~
    public void addImageView()                                     //~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("MainView.addImageView");//~v@21R~//~9620R~
//  	int lp=ViewGroup.LayoutParams.MATCH_PARENT;                //~v@21I~
//  	int wc=ViewGroup.LayoutParams.WRAP_CONTENT;                //~v@21I~
//  	int mp=ViewGroup.LayoutParams.MATCH_PARENT;                //~v@21I~
//      FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(mp,wp);//~v@21I~
//      frameLayout.addView(this,lp,lp);                           //~v@21R~
//      frameLayout.addView(this);                                 //~v@21R~
        frameLayout.addView(imageView);                            //~v@21I~
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
    public void hideTopView(Point PframeLayoutSize)                //~v@21R~
    {                                                              //~v@21I~
//      frameLayoutWW=PframeLayoutSize.x;                          //~v@21R~
//      frameLayoutHH=PframeLayoutSize.y;                          //~v@21R~
        if (Dump.Y) Dump.println("MainView.hideTopView");//~v@21R~ //~9620R~
//        swRestore=false;                                         //~v@21R~
        hideImage(true);                                           //~v@21I~
	    hideButtons(true/*hide*/);                                 //~v@21I~
	    hideTitle(true/*hide*/);                                   //~v@21I~
    	setFrameLayoutSize(PframeLayoutSize);                       //~v@21I~
        if (Dump.Y) Dump.println("MainView.hideTopView frameLayout PframeLayoutSize="+PframeLayoutSize.toString());//~v@21R~//~9620R~
    }                                                              //~v@21I~
//**********************************************************       //~v@21I~
//  public void restore()                                          //~v@21R~
    public void restore(Point PframeLayoutSize)                    //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("MainView.restore size="+PframeLayoutSize.toString());//~v@21R~//~9620R~
//      PframeLayoutSize.y-=100;                                   //~v@21R~
        if (Dump.Y) Dump.println("MainView.restore size="+PframeLayoutSize.toString());//~v@21I~//~9620R~
//        swRestore=true;                                          //~v@21R~
//      setFrameLayoutSize();                                      //~v@21R~
        setFrameLayoutSize(PframeLayoutSize);                      //~v@21I~
        hideImage(false);                                          //~v@21M~
//        restoreTitleBtn();                                       //~v@21R~
	    hideTitle(false/*hide*/);                                  //~v@21I~
	    hideButtons(false/*hide*/);                                //~v@21I~
//        frameLayout.setVisibility(View.INVISIBLE);               //~v@21R~
//        frameLayout.setVisibility(View.VISIBLE);                 //~v@21R~
//        frameLayout.invalidate();                                //~v@21R~
//        AG.mainView.invalidate();                                //~v@21R~
    	drawMsg("");                                               //~0216I~
    }                                                              //~v@21I~
//**********************************************************       //~v@21I~
//  private void setFrameLayoutSize()                              //~v@21R~
    private void setFrameLayoutSize(Point Psize)                   //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("MainView.setFrameLayoutSize frameLayout Psize="+Psize.toString());//~v@21R~//~9620R~
	    ViewGroup.LayoutParams lp=frameLayout.getLayoutParams();             //~v@21I~
//      if (true)                                                  //~v@21R~
//      	return;	//TODO test                                    //~v@21R~
        if (Dump.Y) Dump.println("MainView.setFrameLayoutSize frameLayout Psize="+Psize.toString());//~v@21I~//~9620R~
//      lp.width=frameLayoutWW;                                    //~v@21R~
//      lp.height=frameLayoutHH;                                   //~v@21R~
        lp.width=Psize.x;                                          //~v@21I~
        lp.height=Psize.y;                                         //~v@21I~
	    frameLayout.setLayoutParams(lp);                           //~v@21I~
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
        if (Dump.Y) Dump.println("MainActivity.hideImage swHide="+PswHide);//~v@21I~
        if (PswHide)                                               //~v@21I~
//          frameLayout.removeView(this);                          //~v@21R~
            frameLayout.removeView(imageView);                     //~v@21I~
        else                                                       //~v@21I~
//          if (true)                                              //~v@21R~
//          frameLayout.addView(this);                             //~v@21R~
            addImageView();                                        //~v@21I~
//          else                                                   //~v@21R~
//            setImage(); //set PFLAG_DRAW on to invalidate()      //~v@21R~
    }                                                              //~v@21I~
	//*************************                                    //~v@21I~
    private void hideTitle(boolean Pswhide)                        //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("MainActivity.hideTitle swHide="+Pswhide);//~v@21I~
//      if (false)                                                 //~v@21R~
//        titleMain.setVisibility(Pswhide ? View.GONE : View.VISIBLE);//~v@21R~
//      else                                                       //~v@21R~
//      {                                                          //~v@21R~
        if (Pswhide)                                               //~v@21I~
        {                                                          //~v@21I~
        	if (Dump.Y) Dump.println("MainView.hideTitle view ww="+titleMain.getMeasuredWidth()+",hh="+titleMain.getMeasuredHeight());//~v@21I~//~9620R~
//            titleHH=titleMain.getMeasuredHeight();               //~v@21R~
            frameLayout.removeView(titleMain);                     //~v@21I~
        }                                                          //~v@21I~
        else                                                       //~v@21I~
            frameLayout.addView(titleMain);                        //~v@21I~
//      }                                                          //~v@21R~
    }                                                              //~v@21I~
	//*************************                                    //~v@21I~
    private void hideButtons(boolean Pswhide)                      //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("MainActivity.hideButtons swHide="+Pswhide);//~v@21I~
//      if (false) //TODO test                                     //~v@21R~
//        btnsMain.setVisibility(Pswhide ? View.GONE : View.VISIBLE);//~v@21R~
//      else                                                       //~v@21R~
//      {                                                          //~v@21R~
        if (Pswhide)                                               //~v@21I~
        {                                                          //~v@21I~
        	if (Dump.Y) Dump.println("MainView.hideButtons view ww="+btnsMain.getMeasuredWidth()+",hh="+btnsMain.getMeasuredHeight());//~v@21I~//~9620R~
//            btnsHH=btnsMain.getMeasuredHeight();                 //~v@21R~
            frameLayout.removeView(btnsMain);                      //~v@21I~
        }                                                          //~v@21I~
        else                                                       //~v@21I~
        {                                                          //~v@21I~
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
            frameLayout.addView(btnsMain);                         //~v@21I~
        }                                                          //~v@21R~
        }                                                          //~v@21I~
//      }                                                          //~v@21R~
    }                                                              //~v@21I~
    //******************************************                   //~9619I~
    public static void drawMsg(String Pmsg)                        //~9619R~
    {                                                              //~9619I~
        if (Dump.Y) Dump.println("MainView.drawMsg msgbar="+Pmsg);//~9619R~//~9620R~//~9704R~
        AG.aMainView.strMsgBar=Pmsg;                                             //~9621I~
        if (AG.isMainThread())                                     //~9621I~
        {                                                          //~9621I~
	        AG.aMainView.topMsgBar.setText(Pmsg);           //~9619I~  //~9620R~//~9621R~
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
    				        AG.aMainView.topMsgBar.setText(AG.aMainView.strMsgBar);//~9621R~
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
}//class MainView                                       //~v@21R~  //~9620R~
