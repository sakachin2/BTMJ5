//*CID://+vayMR~: update#= 374;                                    //+vayMR~
//**********************************************************************
//2025/02/24 vayM android14 tablet(google pixel emulator), screen is change to LetterBox if start by landscape.//+vayMI~
//                Web says. from android12(Api31). optionally(by device maker) change to letterBox by orientation//+vayMI~
//2025/02/15 vayn there is more bit difference over vaym. use real framlayout size(getHeight())//~vaynI~
//2025/02/15 vaym DecorView excludes status/title/navigation. Use this as scrHeight.(at g10:api30, there is difference with realHeight-bottom of inset:systemGesture)//~vaymI~
//2025/02/13 vayi foldable open portrate, wide space between bottom button and hand.//~vayiI~
//                "button height is by layaout and dimension", now adjust by screen size//~vayiI~
//2022/03/28 vam6 android12(api31) Display.getRealSize, getRealMetrics//~vam6I~
//2021/09/27 vaef gesture navigation mode from android11           //~vaefI~
//**********************************************************************//~vaefI~
//v@21  imageview                                                  //~v@21I~
//utility around screen
//**********************************************************************
package com.btmtest.game.gv;

import android.content.Context;
import android.os.Build;
import android.os.HandlerThread;
import android.os.Message;
import androidx.appcompat.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.graphics.Canvas;

import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~


//public class GameView extends SurfaceView                        //~v@21R~
//                    implements SurfaceHolder.Callback            //~v@21R~
public class GameView extends AppCompatImageView                   //~v@21R~
{
    public int WW, HH;                                             //~v@@@R~
    public MJTable table;                                            //~v@@@R~
//  private HandlerThread handlerThread;                           //~v@21R~
//  private Looper looper;                                         //~v@21R~
    private GameViewHandler gvHandler;                             //~v@@@R~
    public  SurfaceHolder holder;                                  //~v@@@R~
//  private GC GC;                                                 //~v@@@I~//~v@21R~
//  public boolean swHideNavigationbar;                                   //~v@@@I~//~v@21R~
    private int xTouchDown,yTouchDown;                             //~v@21R~
    private boolean swMove;                                        //~v@21I~
    //**************************************************************//~v@@@R~
    //*from GC                                                     //~v@@@I~
    //**************************************************************//~v@@@I~
    public GameView(Context Pcontext)
    {
        super(Pcontext);                                           //~v@21R~
        AG.aGameView=this;                                         //~v@21I~
        if (Dump.Y) Dump.println("GameView Constructor gameview="+this.toString()+",context="+Pcontext.toString());//~v@21R~
        init(Pcontext);                                            //~v@@@R~
    }
//    //**************************************************************//~v@@@R~//~v@21R~
//    //*if calss defined in xml                                     //~v@@@I~//~v@21R~
//    //**************************************************************//~v@@@I~//~v@21R~
//    public GameView(Context Pcontext,AttributeSet Pattrs)          //~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        super(Pcontext,Pattrs);                                    //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("GameView Constructor with attr");//~v@@@R~//~v@21R~
//        init(Pcontext);                                            //~v@@@R~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~

    //*************************
//  private void init(Context Pcontext)                            //~v@@@R~//~v@21R~
    protected void init(Context Pcontext)	//protected for IT override//~v@21I~
    {
    	AG.aGameView=this;                                         //~v@@@R~
//        AG.svContext=Pcontext;                                   //~v@21R~
		if (Dump.Y) Dump.println("GameView.init context="+Pcontext.toString()+",HWAccelerator="+isHardwareAccelerated());//~v@@@I~//~v@21R~
        try
        {
            setLayerType(LAYER_TYPE_SOFTWARE,null);//TODO TEST,For delayed draw edaler's 1st take//~v@21R~
			if (Dump.Y) Dump.println("GameView.init after setLayer HWAccelerator="+isHardwareAccelerated());//~v@21I~
// 			if (Dump.Y) Dump.println("GameView.init setWillNotDraw:false");//~v@21R~
            UView.setWillNotDraw(this,false);  //enable onDraw() callback     //~v@@@R~//~v@21R~
//            setFocusable(true);                                    //~v@@@I~//~v@21R~
//          setZOrderOnTop(true);                                  //~v@@@R~
//          UView.getScreenSize();                                 //~v@@@R~
            WW = AG.scrWidthReal;                                  //~v@@@R~
   			if (Dump.Y) Dump.println("GameView.init osVersion="+ Build.VERSION.SDK_INT+",swPortrait="+AG.aGC.swPortrait+",scrWidthReal="+AG.scrWidthReal+",scrNavigationbarRightWidth="+AG.scrNavigationbarRightWidth);//~vayiR~
   			if (Dump.Y) Dump.println("GameView.init gesturemode="+AG.swNavigationbarGestureMode+",AG.scrWidth="+AG.scrWidth+",AG.scrHeight="+AG.scrHeight+",frameHeight="+AG.aGC.frameHeight);//~vayiI~
            if (!AG.aGC.swPortrait)	//landscape                    //~v@@@I~
            {                                                      //~v@@@I~
//            if (!AG.swNavigationbarGestureMode)	//when gesture mode navigation bar//~vayiR~//~vaymR~
//	            HH = AG.aGC.frameHeight;  //for land,excluding navigation bar//~vayiR~//~vaymR~
//            else                                                 //~vayiI~//~vaymR~
                HH = AG.scrHeightReal;                             //~v@@@R~//~vayiR~
//	    	  if (Build.VERSION.SDK_INT>=30)   //for gesture navigationbar//~vaefR~//+vayMR~
  	    	  if (AG.osVersion>=AG.APIVER_EDGEMODE)   //for gesture navigationbar//+vayMR~
              {                                                    //~vaefR~
//              	HH-=AG.scrNavigationbarBottomHeightA11;        //~vaefR~
//	                WW-=AG.scrNavigationbarRightWidthA11+AG.scrNavigationbarLeftWidthA11;	//some device,navigationbar is on the right when landscape and could not hide//~vaefR~
//	                WW=AG.scrWidth;	//adjusted by ? login on UView.getScreenSize30//~vaefR~
//	                WW-=AG.scrNavigationbarRightWidthA11+AG.scrNavigationbarLeftWidthA11;	//some device has navigationbar space on the right when landscape and could not hide//~vaefR~
//	                WW-=AG.scrNavigationbarRightWidthA11;          //~vaefR~
  	                WW-=AG.aGC.marginLR;                           //~vaefI~
		   			if (Dump.Y) Dump.println("GameView.init landscape A11 WW="+WW+",HH="+HH+",scrNavigationbarBottomHeight="+AG.scrNavigationbarBottomHeightA11+",leftWidthA11="+AG.scrNavigationbarLeftWidthA11+",rightWidthA11="+AG.scrNavigationbarRightWidthA11);//~vaefR~
              }                                                    //~vaefR~
              else //api<30                                           //~vaefI~
                WW-=AG.scrNavigationbarRightWidth;	//some device,navigationbar is on the right when landscape and could not hide//~v@21R~
//              swHideNavigationbar=true;                          //~v@@@I~//~v@21R~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
//            if (AG.swNavigationbarGestureMode)	//when gesture mode navigation bar//~vayiI~//~vaymR~
//              HH = AG.scrHeightReal;                             //~vayiI~//~vaymR~
//            else                                                 //~vayiI~//~vaymR~
              {                                                    //~vayiI~
// 	            HH = AG.scrHeight;                                 //~v@@@I~//~vayiR~//~vaynR~
	            HH = AG.aGC.frameHeight;  //by frameLayout.getHeight()//~vaynI~
               if (false)                                          //~vayiI~
//      		if (Build.VERSION.SDK_INT>=30)   //for gesture navigationbar//~vaefI~//+vayMR~
        		if (AG.osVersion>=AG.APIVER_EDGEMODE)   //for gesture navigationbar//+vayMR~
            	{                                                  //~vaefI~
                	HH-=AG.scrNavigationbarBottomHeightA11;       //~vaefI~
		   			if (Dump.Y) Dump.println("GameView.init portrait HH="+HH+",scrNavigationbarBottomHeightA11="+AG.scrNavigationbarBottomHeightA11);//~vaefR~//~vam6R~
            	}                                                  //~vaefI~
              }                                                    //~vayiI~
            }                                                      //~v@@@I~
		   	if (Dump.Y) Dump.println("GameView.init WW="+WW+",HH="+HH);//~vayiI~
//          UView.fixOrientation(true);
            table=new MJTable(WW,HH);                                //~v@@@R~
//            holder=getHolder();                                  //~v@21R~
//            AG.holder=holder;                                      //~v@@@I~//~v@21R~
//            holder.addCallback(this);                              //~v@@@I~//~v@21R~
//          holder.setFormat(PixelFormat.TRANSPARENT);//TODO       //~v@@@R~
//            if(Dump.Y) Dump.println("GameView.createView addCallback holder="+holder.toString());//~v@@@R~//~v@21R~
//            setChangeListener();                                 //~v@21R~
            prepareHandler();                                      //~v@21I~
                                                                   //~v@21I~
        }
        catch (Exception e)
        {
            Dump.println(e, "GameView startMain exception");
        }
    }
	//*************************************************************************
    public void onDestroy()
	{
//        AG.svContext=null;                                         //~v@@@I~//~v@21R~
        if(Dump.Y) Dump.println("GameView.onDestroy");
    	if (Dump.Y) Dump.println("GmaeView.onDestroy");            //~v@@@I~
        if (gvHandler!=null)                                      //~v@@@I~
	        gvHandler.onDestroy();                                 //~v@@@R~
//      if (AG.aPieces!=null)                                      //~v@21R~
//          AG.aPieces.recycleAll();                               //~v@21R~
        Pieces.recycleAll();                                       //~v@21I~
    }
    public void onPause()
    {
    	if (Dump.Y) Dump.println("GmaeView.onPause");              //~v@@@I~
    	if (gvHandler!=null)                                      //~v@@@I~
	    	gvHandler.onPause();                                   //~v@@@R~
    }
    public void onResume()
    {
//  	GC=AG.aGC;                                                  //~v@@@I~//~v@21R~
    	if (Dump.Y) Dump.println("GameView.onResume");             //~v@@@R~
        if (gvHandler!=null)                                       //~v@@@R~
            gvHandler.onResume();                                  //~v@@@R~
    }
//    //*************************                                  //~v@21R~
//    @Override                                                    //~v@21R~
//    public void surfaceCreated(SurfaceHolder Pholder)            //~v@21R~
//    {                                                            //~v@21R~
//        AG.holder=Pholder;                                         //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("GameView.surfaceCreated holder="+Pholder.toString());//~v@@@R~//~v@21R~
//        try                                                        //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            prepareHandler();                                      //~v@@@R~//~v@21R~
////          GC.sendMsg(GCM_TEST,null);  //TOD                      //~v@@@R~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//        catch(Exception e)                                         //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Dump.println(e,"GameView.surfaceCreated");             //~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
////        setWillNotDraw(false);  //enable onDraw() callback       //~v@@@R~//~v@21R~
//        gvHandler.surfaceCreated(Pholder);                          //~v@@@I~//~v@21R~
//    }                                                            //~v@21R~
    //*************************
    private void prepareHandler()                                  //~v@21R~
    {
        if (Dump.Y) Dump.println("GameView.prepareHandler");       //~v@@@R~
//      handlerThread = new HandlerThread(Utils.getClassName(this));//~v@21R~
		HandlerThread handlerThread=new HandlerThread(Utils.getClassName(this));//~v@21I~
        handlerThread.start();        //do run()
//      looper = handlerThread.getLooper();                        //~v@21R~
//      gvHandler = new GameViewHandler(this,looper,holder);       //~v@@@R~//~v@21R~
//      gvHandler = new GameViewHandler(this,looper);              //~v@21R~
        gvHandler = GameViewHandler.newInstance(this,handlerThread);//~v@21I~
	    gvHandler.onResume();   //open msgq                        //~v@@@R~
    }

//    //*********************************************************************//~v@@@R~//~v@21R~
//    //*callback when format or size changed                        //~v@@@I~//~v@21R~
//    //*********************************************************************//~v@@@I~//~v@21R~
//    @Override                                                    //~v@21R~
//    public void surfaceChanged(SurfaceHolder Pholder, int Pformat, int Pww, int Phh)//~v@21R~
//    {                                                            //~v@21R~
//        if (Dump.Y) Dump.println("GameView.surfaceChanged holder="+Pholder.toString()+",format=" + Pformat + ",ww=" + Pww + ",hh=" + Phh);//~v@@@R~//~v@21R~
//        AG.holder=Pholder;                                         //~v@@@I~//~v@21R~
////      Toast.makeText(AG.svContext,"test toast",Toast.LENGTH_SHORT).show(); //TODO//~v@@@R~//~v@21R~
////      Toast.makeText(AG.context,"test toast2",Toast.LENGTH_SHORT).show(); //TODO//~v@@@I~//~v@21R~
////      AG.aGraphics.surfaceChanged(Pholder,Pformat);              //~v@@@R~//~v@21R~
//        sendMsg(GCM_SURFACE_CHG,Pformat,Pww,Phh);                  //~v@@@I~//~v@21R~
//    }                                                            //~v@21R~

//    //*************************                                  //~v@21R~
//    @Override                                                    //~v@21R~
//    public void surfaceDestroyed(SurfaceHolder Pholder)          //~v@21R~
//    {                                                            //~v@21R~
//        if (Dump.Y) Dump.println("GameView.surfaceDestroyed");     //~v@@@R~//~v@21R~
//        handlerThread = null;                                    //~v@21R~
//        AG.holder=null;                                            //~v@@@I~//~v@21R~
//    }                                                            //~v@21R~
//    //************************************************************//~v@@@R~//~v@21R~
//    //*for surface view                                          //~v@@@R~//~v@21R~
//    //*invalidate()/update view is not trigger of onDraw()       //~v@@@R~//~v@21R~
//    //************************************************************//~v@@@R~//~v@21R~
//    @Override                                                    //~v@@@R~//~v@21R~
//    public void onAttachedToWindow()                             //~v@@@R~//~v@21R~
//    {                                                            //~v@@@R~//~v@21R~
//        super.onAttachedToWindow();                              //~v@@@R~//~v@21R~
//        if (Dump.Y) Dump.println("GameView.onAttachedToWindow"); //~v@@@R~//~v@21R~
//    }                                                            //~v@@@R~//~v@21R~
//    @Override                                                    //~v@21R~
//    public void onDetachedFromWindow()                           //~v@21R~
//    {                                                            //~v@21R~
//        super.onDetachedFromWindow();                            //~v@21R~
//        if (Dump.Y) Dump.println("GameView.onDetachedFromWindow");//~v@21R~
//    }                                                            //~v@21R~
//    //************************************************************//~v@@@R~
//    //*for surface view                                          //~v@@@R~
//    //*invalidate()/update view is not trigger of onDraw()       //~v@@@R~
//    //************************************************************//~v@@@R~
//    //************************************************************//~v@21R~
//    //*postinvalidate is works if view is attached to a window   //~v@21R~
//    //************************************************************//~v@21R~
//    public void setChangeListener()                              //~v@21R~
//    {                                                            //~v@21R~
//        if (Dump.Y) Dump.println("GameView.setChangeListener");  //~v@21R~
//        UView.setAttachStateChangeListener(this);                //~v@21R~
//    }                                                            //~v@21R~
    //***********************************************************  //~v@21M~
    public void paint()                                            //~v@21M~
    {                                                              //~v@21M~
        if (Dump.Y) Dump.println("GameView:paint issue postInvalidate");//~v@21I~
//  	postInvalidate();   //call onDraw                          //~v@21R~
    	Graphics.invalidate(this);   //call onDraw                 //~v@21I~
    }                                                              //~v@21M~
    //***********************************************************  //~v@21I~
    @Override                                                    //~v@@@R~//~v@21R~
    public void onDraw(Canvas Pcanvas/*android.graphics.Canvas*/)                           //~v@@@R~//~v@21R~
    {                                                            //~v@@@R~//~v@21R~
        if (Dump.Y) Dump.println("GameView.onDraw HWaccelerate="+Pcanvas.isHardwareAccelerated());             //~v@@@R~//~v@21R~
      try                                                          //~vam6I~
      {                                                            //~vam6I~
        Graphics.onDraw(Pcanvas);                                  //~v@21I~
      }                                                            //~vam6I~
      catch(Exception e)                                           //~vam6I~
      {                                                            //~vam6I~
    	Dump.println(e,"GameView.onDraw");                         //~vam6I~
      }                                                            //~vam6I~
    }                                                            //~v@@@R~//~v@21R~
    //*************************                                    //~v@@@I~
    @Override                                                      //~v@@@I~
    public boolean onTouchEvent(MotionEvent Pevent)                //~v@@@I~
    {                                                              //~v@@@I~
    	boolean rc=false;                                              //~v@@@I~
    	int action=Pevent.getAction();                             //~v@@@I~
        int x=(int)Pevent.getX();                                       //~v@@@I~
        int y=(int)Pevent.getY();                                       //~v@@@I~
        if (Dump.Y) Dump.println("GameView.onTouchEvent action="+action+",x="+x+",y="+y);//~v@@@I~//~v@21R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
            switch(action)                                         //~v@@@R~
            {                                                      //~v@@@R~
            case MotionEvent.ACTION_DOWN:       //0                   //~v@@@R~
            	xTouchDown=x;                                      //~v@21I~
            	yTouchDown=y;                                      //~v@21I~
                swMove=false;                                      //~v@21I~
                rc=true;    //need to callback by ACTION_UP        //~v@@@R~
                break;                                             //~v@@@R~
            case MotionEvent.ACTION_UP:         //1                   //~v@@@R~
            	if (!swMove)	                                   //~v@21I~
                {                                                  //~v@21I~
	            	xTouchDown=0;                                  //~v@21I~
    	        	yTouchDown=0;                                  //~v@21I~
                }                                                  //~v@21I~
//              sendMsg(GCM_TOUCH,action,x,y);                     //~v@@@R~//~v@21R~
                sendMsg(GCM_TOUCH,action,x,y,xTouchDown,yTouchDown);//~v@21I~
            	xTouchDown=0;                                      //~v@21I~
            	yTouchDown=0;                                      //~v@21I~
                break;                                             //~v@@@R~
            case MotionEvent.ACTION_MOVE:       //2                //~v@21I~
                swMove=true;                                       //~v@21I~
                break;                                             //~v@21I~
            }                                                      //~v@@@R~
		}                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
    		Dump.println(e,"GameView.onTouchEvent");               //~v@@@I~
        }                                                          //~v@@@I~
        return rc;	//continue process                             //~v@@@R~
    }                                                              //~v@@@I~
    //***********************************************************
    private void sendMsg(int Pmsgid,String Pmsgdata)               //~v@@@R~
    {
        if (Dump.Y) Dump.println("GameView.sendMsg msgid="+Pmsgid+",msg="+Pmsgdata);
        gvHandler.sendMsg(Pmsgid,Pmsgdata);
    }
    //***********************************************************  //~v@@@I~
//  private void sendMsg(int Pmsgid,int Pparm1,int Pparm2,int Pparm3)//~v@@@R~//~v@21R~
    private void sendMsg(int Pmsgid,int Pparm1,int Pparm2,int Pparm3,int Pparm4,int Pparm5)//~v@21I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GameView.sendMsg msgid="+Pmsgid+",parm1="+Pparm1+",parm2="+Pparm2+",parm3="+Pparm3+",parm4="+Pparm4+",parm5="+Pparm5);//~v@@@R~//~v@21R~
//      gvHandler.sendMsg(Pmsgid,Pparm1,Pparm2,Pparm3);            //~v@@@R~//~v@21R~
        Message msg=GameViewHandler.obtainMsg(Pmsgid,Pparm1,Pparm2,Pparm3,Pparm4,Pparm5);//~v@21I~
        gvHandler.sendMessage(msg);                                    //~v@21I~
    }                                                              //~v@@@I~
    //***********************************************************  //~v@21I~
    public static void repaint()                                   //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("GameView.repaint");               //~v@21I~
        AG.aGameView.paint();	//postInvalidate();                //~v@21I~
    }                                                              //~v@21I~
}//class GameView
