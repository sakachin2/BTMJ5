//*CID://+DATER~: update#= 409;                                    //~v@21R~//~9A15R~
//**********************************************************************//~v101I~
//v@21  imageview                                                  //~v@21I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.view.View;

import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static android.view.View.LAYER_TYPE_SOFTWARE;
import static com.btmtest.StaticVars.AG;                           //~v@21I~

public class Graphics //extends Handler                            //~v@@@R~
{                                                                  //~0914I~
//  private static int WW,HH;                                             //~v@@@I~//~v@21R~//~9A16R~
    private        int WW,HH;                                      //~9A16I~
//  private static Bitmap bmShadow;	//for redraw at resume         //~v@@@R~//~v@21R~//~9A16R~
    private        Bitmap bmShadow;	//for redraw at resume         //~9A16R~
//  private static Canvas canvasShadow;                            //~v@@@I~//~v@21R~//~9A16R~
    private        Canvas canvasShadow;                            //~9A16I~
//    private int ctrInvalidate;  //invalidate may not schedule onDraw each time//~9A15R~
//*************************                                        //~v@@@I~
//    public Graphics(Canvas Pcanvas,int PscrW,int PscrH)                //~v@@@R~//~v@21R~
//    {                                                              //~0914I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics Constructor="+this.toString()+",canvas="+Pcanvas.toString());         //~1506R~//~@@@@R~//~v@@@R~//~v@21R~
//        AG.aGraphics=this;                                         //~v@@@I~//~v@21R~
//        canvas=Pcanvas;                                            //~v@@@I~//~v@21R~
//        WW=PscrW;  HH=PscrH;                                       //~v@@@I~//~v@21R~
////      bmShadow=Bitmap.createBitmap(WW,HH,Bitmap.Config.ARGB_8888);//~v@@@R~//~v@21R~
//        bmShadow=Bitmap.createBitmap(WW,HH,Bitmap.Config.RGB_565/*surfacechanged show pixceformat=5=RGB_%&5*/);//~v@@@I~//~v@21R~
////      bmShadow=Bitmap.createBitmap(WW,HH,Bitmap.Config.ARGB_8888,true/*hasAlpha:to make OPAQUE(default of surfacevie)*/);//~v@@@R~//~v@21R~
////      bmShadow=Bitmap.createBitmap(WW,HH,Bitmap.Config.ARGB_8888,false/*hasAlpha:to make OPAQUE(default of surfacevie)*/);//~v@@@I~//~v@21R~
//        canvasShadow=new Canvas(bmShadow);                         //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics constructor bmShadow="+bmShadow.toString());//~v@@@I~//~v@21R~
//    }                                                              //~0914I~//~v@@@R~//~v@21R~
    public static Bitmap createBitmap(int Pww,int Phh,Bitmap.Config Pconfig) //~0216I~
    {                                                              //~0216I~
		Bitmap bm=Bitmap.createBitmap(Pww,Phh,Pconfig);            //~0216I~
        if (Dump.Y) Dump.println("Graphics createBitmap ww="+Pww+",hh="+Phh+",type="+Pconfig.toString()+",byteCount="+bm.getByteCount()+",bitmap="+bm.toString());//~0216R~
        return bm;
    }                                                              //~0216I~
    public static Bitmap createBitmap(Bitmap Pbmsrc,int Pposx,int Pposy,int Pww,int Phh,Matrix Pmatrix,boolean Pfilter)//~0216I~
    {                                                              //~0216I~
		Bitmap bm=Bitmap.createBitmap(Pbmsrc,Pposx,Pposy,Pww,Phh,Pmatrix,Pfilter);//~v@@@I~//~0216I~
        if (Dump.Y) Dump.println("Graphics createBitmap posx="+Pposx+",posy="+Pposy+",filter="+Pfilter+",matrix="+Pmatrix.toString()+"srcbm="+Pbmsrc.toString());//~0216I~
        if (Dump.Y) Dump.println("Graphics createBitmap ww="+Pww+",hh="+Phh+",byteCount="+bm.getByteCount()+",bitmap="+bm.toString());//~0216R~
        return bm;
    }                                                              //~0216I~
    public Graphics(Canvas Pcanvas,Bitmap Pbitmap,int PscrW,int PscrH)//~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Graphics Constructor ww="+PscrW+",hh="+PscrH);//~v@21R~
//      AG.aGraphics=this;                                         //~v@21I~//~0217R~
        WW=PscrW;  HH=PscrH;                                       //~v@21I~
        reset();                                                   //~v@21I~
        AG.aGraphics=this;  //sfter reset by old AG.aGraphics      //~0217I~
        bmShadow=Pbitmap;                                          //~v@21R~
        canvasShadow=Pcanvas;                                      //~v@21R~
        if (Dump.Y) Dump.println("Graphics.constructor HWaccelerate="+Pcanvas.isHardwareAccelerated());             //~v@@@R~//~v@21I~
        if (Dump.Y) Dump.println("Graphics constructor bmShadow="+bmShadow.toString());//~v@21I~
    }                                                              //~v@21I~
//**************************************************************   //~v@21M~
//*at endgame from GC                                              //~v@21M~
//**************************************************************   //~v@21M~
    public static void reset()                                     //~v@21I~
    {                                                              //~v@21M~
        if (Dump.Y) Dump.println("Graphics.reset AG.aGraphics="+ Utils.toString(AG.aGraphics));//~0217I~
        if (AG.aGraphics==null)                                    //~0217I~
            return;                                                //~0217I~
        if (Dump.Y) Dump.println("Graphics.reset bmshadow"+Utils.toString(AG.aGraphics.bmShadow));//~0217R~
        if (AG.aGraphics.bmShadow==null)                                        //~v@21I~//~9A16R~
            return;                                                //~v@21I~
        UView.recycle(AG.aGraphics.bmShadow);                                   //~v@21M~//~9A16R~
	    AG.aGraphics.bmShadow=null;                                             //~v@21M~//~9A16R~
	    AG.aGraphics.canvasShadow=null;                            //~9A16R~
//        AG.aGraphics.ctrInvalidate=0;//~v@21I~                   //~9A15R~
    }                                                              //~v@21M~
//***************************************************************************//~v@21I~
//*draw shadowcopy to imageview on UI thread                       //~v@21I~
//***************************************************************************//~v@21I~
    public static void onDraw(Canvas Pcanvas)                      //~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Graphics.onDraw Pcanvas="+Pcanvas.toString());//~v@21R~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21R~//~9A16R~
        {                                                          //~v@21I~
//            if (Dump.Y) Dump.println("Graphics.onDraw ctrInvalidate="+AG.aGraphics.ctrInvalidate);//~9A15R~
//            AG.aGraphics.ctrInvalidate--;                        //~9A15R~
//            if (AG.aGraphics.ctrInvalidate<0)                    //~9A15R~
//                    AG.aGraphics.ctrInvalidate=0;                //~9A15R~
//            if (AG.aGraphics.ctrInvalidate>0)   //onDraw will be called continbuously    //TODO test//~9A15R~
//            {                                                    //~9A15R~
//                if (Dump.Y) Dump.println("Graphics.onDraw exit without-drawCanvas ctrInvalidate="+AG.aGraphics.ctrInvalidate);//~9A15R~
//                return;                                          //~9A15R~
//            }                                                    //~9A15R~
//      	Rect dst=new Rect(0,0,WW,HH);                          //~v@21R~//~9A16R~
        	Rect dst=new Rect(0,0,AG.aGraphics.WW,AG.aGraphics.HH);//~9A16I~
    		Pcanvas.drawBitmap(AG.aGraphics.bmShadow,null/*src*/,dst,null/*paint*/);//~v@21R~//~9A15R~//~9A16R~
        }                                                          //~v@21I~
        if (Dump.Y) Dump.println("Graphics.onDraw WW="+AG.aGraphics.WW+",HH="+AG.aGraphics.HH+",AG.aGraphics.bmShadow="+AG.aGraphics.bmShadow.toString());//~v@21R~//~9A16R~
    }                                                              //~v@21I~
//***************************************************************************//~9A15I~
    public static void invalidate(GameView Pview)                  //~9A15I~
    {                                                              //~9A15I~
        if (Dump.Y) Dump.println("Graphics.invalidate");           //~9A15I~
        synchronized(AG.aGraphics.bmShadow)                                     //~9A15I~//~9A16R~
        {                                                          //~9A15I~
//            AG.aGraphics.ctrInvalidate++;                        //~9A15R~
//            if (Dump.Y) Dump.println("Graphics.invalidate ctrInvalidate="+AG.aGraphics.ctrInvalidate);//~9A15R~
			Pview.postInvalidate();   //call onDraw                //~9A15I~
        }                                                          //~9A15I~
    }                                                              //~9A15I~
//    //****************************************                     //~v@@@I~//~v@21R~
//    //*frm GameView->GVHander->GCanvas                             //~v@@@I~//~v@21R~
//    //****************************************                     //~v@@@I~//~v@21R~
//    public static void surfaceCreated(SurfaceHolder Pholder)       //~v@@@R~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.surfaceCreated holder="+Pholder.toString());//~v@@@R~//~v@21R~
////        setShadow();                                             //~v@@@R~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
//    //****************************************                   //~v@@@R~
//    //*frm GameView->GVHander->GCanvas                           //~v@@@R~
//    //****************************************                   //~v@@@R~
//    public static void surfaceChanged(SurfaceHolder Pholder, int Pformat)//~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Graphics.surfacChanged holder="+Pholder.toString());//~v@@@R~
//        setShadow();                                             //~v@@@R~
//    }                                                            //~v@@@R~
//    //****************************************                     //~v@@@I~//~v@21R~
//    //*frm GameView->GVHander->GCanvas                             //~v@@@I~//~v@21R~
//    //****************************************                     //~v@@@I~//~v@21R~
//    public  static void setShadow()                                //~v@@@R~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.setShadow AG.aGraphics.bmShadow="+(AG.aGraphics.bmShadow==null?"null":AG.aGraphics.bmShadow.toString()));//~v@@@I~//~v@21R~//~9A16R~
//        if (AG.aGraphics.bmShadow!=null)                                        //~v@@@I~//~v@21R~//~9A16R~
//        {                                                          //~v@@@I~//~v@21R~
//            if (Dump.Y) Dump.println("Graphics.setShadow ww="+AG.aGraphics.bmShadow.getWidth()+",hh="+AG.aGraphics.bmShadow.getHeight());//~v@@@I~//~v@21R~//~9A16R~
//            Canvas cc=lockCanvas();                                //~v@@@I~//~v@21R~
////          drawBitmap(cc,AG.aGraphics.bmShadow,0,0);                           //~v@@@I~//~v@21R~//~9A16R~
////          Paint p= new Paint();                                  //~v@@@I~//~v@21R~
////          p.setAlpha(0);                                         //~v@@@I~//~v@21R~
////          cc.drawBitmap(AG.aGraphics.bmShadow,0,0,p);                         //~v@@@I~//~v@21R~//~9A16R~
////          Pcanvas.drawBitmap(Pbitmap,src,dst,null/*paint*/);     //~v@@@I~//~v@21R~
//            Rect rect=new Rect(0,0,AG.aGraphics.WW,AG.aGraphics.HH);                         //~v@@@I~//~v@21R~
//            drawRect(true,cc,rect,COLOR_BG_TABLE);                 //~v@@@I~//~v@21R~
//            drawBitmap(true/*local*/,cc,AG.aGraphics.bmShadow,0,0);             //~v@@@I~//~v@21R~//~9A16R~
//            unlockCanvas(cc);                                      //~v@@@I~//~v@21R~
//            if (Dump.Y) Dump.println("Graphics.setShadow written shadow");//~v@@@R~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
//    //*********************************************************************//~v@@@I~//~v@21R~
//    //*why AG.aGraphics.bmShadow is not created at app start ?                  //~v@@@I~//~v@21R~//~9A16R~
//    //*********************************************************************//~v@@@I~//~v@21R~
//    public void reset()                                          //~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.onDestroy AG.aGraphics.bmShadow="+(AG.aGraphics.bmShadow==null?"null":AG.aGraphics.bmShadow.toString()));//~v@@@I~//~v@21R~//~9A16R~
//        AG.aGraphics.bmShadow=null;                                           //~v@21R~//~9A16R~
//        AG.aGraphics.canvasShadow=null;                                       //~v@21R~//~9A16R~
//    }                                                              //~v@@@I~//~v@21R~
//    //****************************************************       //~v@@@R~
//    public void drawRect(Rect Prect, int Pcolor)                 //~v@@@R~
//    {                                                            //~v@@@R~
//        drawRect(canvas,Prect,Pcolor);                           //~v@@@R~
//    }                                                            //~v@@@R~
//    //****************************************************         //~v@@@I~//~v@21R~
//    public static void drawRect(Canvas Pcanvas, Rect Prect, int Pcolor, int Pwidth)//~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        drawRect(false,Pcanvas,Prect,Pcolor,Pwidth);               //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
//    //****************************************************         //~v@@@I~//~v@21R~
//    public static void drawRect(Rect Prect, int Pcolor, int Pwidth)//~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRect color="+Integer.toHexString(Pcolor)+",strokeWidth="+Pwidth);//~v@@@I~//~v@21R~
//        synchronized(AG.aGameView.holder)                          //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Canvas cc=lockCanvas(Prect);                           //~v@@@I~//~v@21R~
//            try                                                    //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                drawRect(false,cc,Prect,Pcolor,Pwidth);            //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            catch(Exception e)                                     //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                Dump.println(e,"Graphics.drawRect with width");    //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            unlockCanvas(cc);                                      //~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
    //****************************************************         //~v@21I~
    public static void drawRect(Rect Prect, int Pcolor, int Pwidth)//~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawRect color="+Integer.toHexString(Pcolor)+",strokeWidth="+Pwidth);//~v@21I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21R~//~9A16R~
        {                                                          //~v@21I~
            drawRect(AG.aGraphics.canvasShadow,Prect,Pcolor,Pwidth);            //~v@21R~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
    //****************************************************         //~v@@@I~
    //*stroke rect                                                 //~v@@@I~
    //****************************************************         //~v@@@I~
	public static void drawRect(Canvas Pcanvas, Rect Prect, int Pcolor, int Pwidth)//~v@@@R~//~v@21R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawRect color="+Integer.toHexString(Pcolor)+",strokeWidth="+Pwidth);//~v@@@R~//~v@21R~
        if (Dump.Y) Dump.println("Graphics.drawRect l="+Prect.left+",t="+Prect.top+",r="+Prect.right+",b="+Prect.bottom);//~v@@@I~
        Paint p=new Paint();                                       //~v@@@I~
        p.setAntiAlias(true);                                      //~v@@@I~
        p.setColor(Pcolor);                                        //~v@@@M~
        p.setStyle(Paint.Style.STROKE);                            //~v@@@M~
        p.setStrokeWidth((float)Pwidth);                           //~v@@@R~
        if (Dump.Y) Dump.println("Graphics.drawRect style="+p.getStyle()+",strokeWidth="+p.getStrokeWidth());//~v@@@I~
//      Pcanvas.drawRect(Prect,p);                                         //~v@@@R~//~v@21R~
		int w=Pwidth/2;                                            //~v@21I~
		Rect frame=new Rect(Prect.left+w,Prect.top+w,Prect.right-w,Prect.bottom-w);//~v@21I~
        Pcanvas.drawRect(frame,p);                                 //~v@21I~
//        if (!PswLocal)                                              //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            AG.aGraphics.canvasShadow.drawRect(Prect,p);                        //~v@@@R~//~v@21R~//~9A16R~
//            if (Dump.Y) Dump.println("Graphics.drawRect written shadow");//~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
    }                                                              //~v@@@I~
    //****************************************************         //~v@21I~
    public static void drawRect(Rect Prect, int Pcolor)            //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawRect color="+Integer.toHexString(Pcolor));//~v@21I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21R~//~9A16R~
        {                                                          //~v@21I~
            drawRect(AG.aGraphics.canvasShadow,Prect,Pcolor);                   //~v@21R~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
    //****************************************************         //~v@@@I~
    //*under synchronized                                          //~v@21I~
    //****************************************************         //~v@21I~
	public static void drawRect(Canvas Pcanvas,Rect Prect, int Pcolor)//~v@@@R~//~v@21R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawRect color="+Integer.toHexString(Pcolor));//~v@@@R~//~v@21R~
        if (Dump.Y) Dump.println("Graphics.drawRect l="+Prect.left+",t="+Prect.top+",r="+Prect.right+",b="+Prect.bottom);//~v@@@I~
        Paint p=new Paint();                                       //~v@@@I~
        p.setColor(Pcolor);                                        //~v@@@I~
        Pcanvas.drawRect(Prect,p);                                 //~v@@@R~
//        if (!PswLocal)                                             //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            AG.aGraphics.canvasShadow.drawRect(Prect,p);                        //~v@@@R~//~v@21R~//~9A16R~
//            if (Dump.Y) Dump.println("Graphics.drawRect written shadow");//~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
    }                                                              //~v@@@I~
//    //****************************************************         //~v@@@I~//~v@21R~
//    public static void drawRect(Rect Prect,int Pcolor)             //~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRect no canvas parm");//~v@@@I~//~v@21R~
//        drawRectBitmap(Prect,Pcolor,null/*bitmap*/,0/*Pxx*/,0/*Pyy*/);//~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
//    //****************************************************         //~v@@@I~//~v@21M~
//    public static void drawRectFrame(Rect Prect, int Pcolor, int Pwidth)//~v@@@I~//~v@21M~
//    {                                                              //~v@@@I~//~v@21M~
//        if (Dump.Y) Dump.println("Graphics.drawRectFrame color="+Integer.toHexString(Pcolor)+",strokeWidth="+Pwidth);//~v@@@I~//~v@21M~
//        synchronized(AG.aGameView.holder)                          //~v@@@I~//~v@21M~
//        {                                                          //~v@@@I~//~v@21M~
//            Canvas cc=lockCanvas(Prect);                           //~v@@@I~//~v@21M~
//            try                                                    //~v@@@I~//~v@21M~
//            {                                                      //~v@@@I~//~v@21M~
//                drawRectFrame(false,cc,Prect,Pcolor,Pwidth);       //~v@@@I~//~v@21M~
//            }                                                      //~v@@@I~//~v@21M~
//            catch(Exception e)                                     //~v@@@I~//~v@21M~
//            {                                                      //~v@@@I~//~v@21M~
//                Dump.println(e,"Graphics.drawRectFrame with width");//~v@@@I~//~v@21M~
//            }                                                      //~v@@@I~//~v@21M~
//            unlockCanvas(cc);                                      //~v@@@I~//~v@21M~
//        }                                                          //~v@@@I~//~v@21M~
//    }                                                              //~v@@@I~//~v@21M~
//    //****************************************************       //~v@21R~
//    public static void drawRectFrame(Rect Prect, int Pcolor, int Pwidth)//~v@21R~
//    {                                                            //~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRectFrame color="+Integer.toHexString(Pcolor)+",strokeWidth="+Pwidth);//~v@21R~
//        synchronized(AG.aGraphics.bmShadow)                                   //~v@21R~//~9A16R~
//        {                                                        //~v@21R~
//            drawRectFrame(false,cc,Prect,Pcolor,Pwidth);         //~v@21R~
//        }                                                        //~v@21R~
//    }                                                            //~v@21R~
//    //*********************************************************************************************//~v@@@R~//~v@21R~
//    //*stroke rect using path because drawrect(width) fill in th box,but no effect to use path//~v@@@R~//~v@21R~
//    //*********************************************************************************************//~v@@@R~//~v@21R~
//    public static void drawRectFrame(boolean PswLocal,Canvas Pcanvas, Rect Prect, int Pcolor, int Pwidth)//~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRectFrame swLocal="+PswLocal+",color="+Integer.toHexString(Pcolor)+",strokeWidth="+Pwidth);//~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRectFrame l="+Prect.left+",t="+Prect.top+",r="+Prect.right+",b="+Prect.bottom);//~v@@@I~//~v@21R~
//        Paint p=new Paint();                                       //~v@@@I~//~v@21R~
//        p.setAntiAlias(true);                                      //~v@@@I~//~v@21R~
//        p.setColor(Pcolor);                                        //~v@@@I~//~v@21R~
//        p.setStyle(Paint.Style.STROKE);                            //~v@@@I~//~v@21R~
//        p.setStrokeWidth((float)Pwidth);                           //~v@@@I~//~v@21R~
//        Path path=new Path();                                      //~v@@@I~//~v@21R~
//        path.moveTo(Prect.left,Prect.top);                         //~v@@@I~//~v@21R~
//        path.lineTo(Prect.right,Prect.top);                        //~v@@@I~//~v@21R~
//        path.lineTo(Prect.right,Prect.bottom);                     //~v@@@R~//~v@21R~
//        path.lineTo(Prect.left,Prect.bottom);                      //~v@@@R~//~v@21R~
//        path.lineTo(Prect.left,Prect.top);                         //~v@@@R~//~v@21R~
//        Pcanvas.drawPath(path,p);                                  //~v@@@I~//~v@21R~
//        if (!PswLocal)                                             //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            drawRectFrame(true,AG.aGraphics.canvasShadow,Prect,Pcolor,Pwidth);  //~v@@@I~//~v@21R~//~9A16R~
//            if (Dump.Y) Dump.println("Graphics.drawRect written shadow");//~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
//    //****************************************************         //~v@@@I~//~v@21R~
//    public static void drawRectBitmap(Rect Prect, int Pcolor,Bitmap Pbitmap,int Pxx,int Pyy)//~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRectBitmap no canvas parm bitmap="+(Pbitmap==null?"null":Pbitmap.toString()));//~v@@@R~//~v@21R~
//        synchronized(AG.aGameView.holder)                                //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Canvas cc=lockCanvas(Prect);                           //~v@@@I~//~v@21R~
//            try                                                    //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                drawRectBitmap(cc,Prect,Pcolor,Pbitmap,Pxx,Pyy);   //~v@@@I~//~v@21R~
//            }                                                      //~v@@@R~//~v@21R~
//            catch(Exception e)                                     //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                Dump.println(e,"Graphics.drawRectBitmap");         //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            unlockCanvas(cc);                                      //~v@@@R~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
    //****************************************************         //~v@21I~
    public static void drawRectBitmap(Rect Prect, int Pcolor,Bitmap Pbitmap,int Pxx,int Pyy)//~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawRectBitmap no canvas parm bitmap="+(Pbitmap==null?"null":Pbitmap.toString()));//~v@21I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21R~//~9A16R~
        {                                                          //~v@21I~
            drawRectBitmap(AG.aGraphics.canvasShadow,Prect,Pcolor,Pbitmap,Pxx,Pyy);//~v@21R~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
    //****************************************************         //~v@@@I~
    //*under synchronized                                          //~v@21I~
    //****************************************************         //~v@21I~
	public static void drawRectBitmap(Canvas Pcanvas,Rect Prect, int Pcolor,Bitmap Pbitmap,int Pxx,int Pyy)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawRectBitmap with canvas parm bitmap="+(Pbitmap==null?"null":Pbitmap.toString()));//~v@@@I~
        	drawRect(Pcanvas,Prect,Pcolor);                        //~v@@@I~
        if (Pbitmap!=null)                                         //~v@@@I~
        	drawBitmap(Pcanvas,Pbitmap,Pxx,Pyy);                   //~v@@@I~
    }                                                              //~v@@@I~
//    //****************************************************         //~v@@@I~//~v@21R~
//    public static void drawRectFrameBitmap(Rect Prect, int Pcolor,Bitmap Pbitmap,int Pxx,int Pyy,int Pwidth,int PcolorFrame)//~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRectBitmapFrame no canvas parm bitmap="+(Pbitmap==null?"null":Pbitmap.toString()));//~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRectBitmapFrame width="+Pwidth+",framecolor="+Integer.toHexString(PcolorFrame));//~v@@@I~//~v@21R~
//        synchronized(AG.aGameView.holder)                          //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Canvas cc=lockCanvas(Prect);                           //~v@@@I~//~v@21R~
//            try                                                    //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                drawRectFrameBitmap(cc,Prect,Pcolor,Pbitmap,Pxx,Pyy,Pwidth,PcolorFrame);//~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            catch(Exception e)                                     //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                Dump.println(e,"Graphics.drawFrameRectBitmap");    //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            unlockCanvas(cc);                                      //~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
    //****************************************************         //~v@21I~
    public static void drawRectFrameBitmap(Rect Prect, int Pcolor,Bitmap Pbitmap,int Pxx,int Pyy,int Pwidth,int PcolorFrame)//~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawRectBitmapFrame no canvas parm bitmap="+(Pbitmap==null?"null":Pbitmap.toString()));//~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawRectBitmapFrame width="+Pwidth+",framecolor="+Integer.toHexString(PcolorFrame));//~v@21I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21R~//~9A16R~
        {                                                          //~v@21I~
            drawRectFrameBitmap(AG.aGraphics.canvasShadow,Prect,Pcolor,Pbitmap,Pxx,Pyy,Pwidth,PcolorFrame);//~v@21R~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
    //****************************************************         //~v@@@I~//~v@21M~
    //*under synchronized                                          //~v@21I~
    //****************************************************         //~v@21I~
	public static void drawRectFrameBitmap(Canvas Pcanvas,Rect Prect, int Pcolor,Bitmap Pbitmap,int Pxx,int Pyy,int Pwidth,int PcolorFrame)//~v@@@I~//~v@21M~
    {                                                              //~v@@@I~//~v@21M~
        if (Dump.Y) Dump.println("Graphics.drawRectBitmapFrame with canvas parm bitmap="+(Pbitmap==null?"null":Pbitmap.toString()));//~v@@@I~//~v@21M~
        if (Dump.Y) Dump.println("Graphics.drawRectBitmapFrame width="+Pwidth+",framecolor="+Integer.toHexString(PcolorFrame));//~v@@@I~//~v@21M~
        drawRect(Pcanvas,Prect,Pcolor);                            //~v@@@I~//~v@21M~
        if (Pbitmap!=null)                                         //~v@@@I~//~v@21M~
        	drawBitmap(Pcanvas,Pbitmap,Pxx,Pyy);                   //~v@@@I~//~v@21M~
//      drawRect(Pcanvas,Prect,PcolorFrame,Pwidth);                //~v@@@R~//~v@21R~//~0401R~
        Rect center=new Rect(Prect);                                //~0401I~
        if (Pwidth>1 && (Pwidth%2)!=0)	//odd                      //~0401R~
        {                                                          //~0401I~
//      	int middle=(Pwidth+1)/2;                               //~0401R~
        	int middle=1;                                          //~0401I~
            center.left+=middle; center.right-=middle; center.top+=middle; center.bottom-=middle;//~0401I~
        }                                                          //~0401I~
        drawRect(Pcanvas,center,PcolorFrame,Pwidth);               //~0401I~
//        int w=Pwidth/2;                                          //~v@21R~
//        Rect frame=new Rect(Prect.left+2,Prect.top+w,Prect.right-w,Prect.bottom-w);//~v@21R~
//      drawRect(Pcanvas,frame,PcolorFrame,Pwidth);                //~v@21R~
    }                                                              //~v@@@I~//~v@21M~
    //****************************************************         //~v@@@M~
	public static void drawBitmap(Bitmap Pbitmap, int Pxx, int Pyy)       //~v@@@M~//~v@21R~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("Graphics.drawBitmap bitmap="+Pbitmap.toString());//~v@@@M~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21R~//~9A16R~
        {                                                          //~v@21I~
			drawBitmap(AG.aGraphics.canvasShadow,Pbitmap,Pxx,Pyy);                        //~v@@@M~//~v@21R~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@@@M~
    //****************************************************         //~v@21I~
	public static void drawBitmapAlpha(Bitmap Pbitmap, int Pxx, int Pyy,int Palpha)//~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawBitmapAlpha alpha="+Palpha+",bitmap="+Pbitmap.toString());//~v@21I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21I~//~9A16R~
        {                                                          //~v@21I~
			drawBitmapAlpha(AG.aGraphics.canvasShadow,Pbitmap,Pxx,Pyy,Palpha);  //~v@21I~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
    //****************************************************         //~v@21M~
    public static void drawBitmap(Rect Prect,Bitmap Pbitmap,int Pxx,int Pyy)//~v@21M~
    {                                                              //~v@21M~
        if (Dump.Y) Dump.println("Graphics.drawBitmap no canvas parm bitmap="+Pbitmap.toString());//~v@21M~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21I~//~9A16R~
        {                                                          //~v@21M~
            drawBitmap(AG.aGraphics.canvasShadow,Pbitmap,Pxx,Pyy);              //~v@21I~//~9A16R~
        }                                                          //~v@21M~
    }                                                              //~v@21M~
//    //****************************************************         //~v@@@M~//~v@21R~
//    //*under synchronized                                        //~v@21R~
//    //****************************************************       //~v@21R~
//    public static void drawBitmap(Canvas Pcanvas,Bitmap Pbitmap, int Pxx, int Pyy)//~v@@@M~//~v@21R~
//    {                                                              //~v@@@M~//~v@21R~
//        drawBitmap(false/*Pswlocal*/,Pcanvas,Pbitmap,Pxx,Pyy);//~v@@@I~//~v@21R~
//    }                                                              //~v@@@M~//~v@21R~
    //****************************************************         //~v@@@I~
    //*under synchronized                                          //~v@21I~
    //****************************************************         //~v@21I~
	public static void drawBitmap(Canvas Pcanvas,Bitmap Pbitmap, int Pxx, int Pyy)//~v@@@I~//~v@21R~
    {                                                              //~v@@@I~
    	int ww=Pbitmap.getWidth();                                 //~v@@@I~
    	int hh=Pbitmap.getHeight();                                //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawBitmap with canvas parm bitmap="+Pbitmap.toString());//~v@@@R~//~v@21R~
        if (Dump.Y) Dump.println("Graphics.drawBitmap xx="+Pxx+",yy="+Pyy+",bitmapsize ww="+ww+",hh="+hh+",canvas="+Pcanvas.toString());//~v@@@I~
        Rect src=new Rect(0,0,ww,hh);                              //~v@@@I~
        Rect dst=new Rect(Pxx,Pyy,Pxx+ww,Pyy+hh);                  //~v@@@I~
        Pcanvas.drawBitmap(Pbitmap,src,dst,null/*paint*/);         //~v@@@I~
//        if (!Pswlocal)                                             //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            AG.aGraphics.canvasShadow.drawBitmap(Pbitmap,src,dst,null/*paint*/);//~v@@@I~//~v@21R~//~9A16R~
//            if (Dump.Y) Dump.println("Graphics.drawRect written shadow");//~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
    }                                                              //~v@@@I~
    //****************************************************         //~v@21I~
    //*under synchronized                                          //~v@21I~
    //****************************************************         //~v@21I~
	public static void drawBitmapAlpha(Canvas Pcanvas,Bitmap Pbitmap, int Pxx, int Pyy,int Palpha)//~v@21I~
    {                                                              //~v@21I~
    	int ww=Pbitmap.getWidth();                                 //~v@21I~
    	int hh=Pbitmap.getHeight();                                //~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawBitmapAlpha with canvas parm bitmap="+Pbitmap.toString());//~v@21R~
        if (Dump.Y) Dump.println("Graphics.drawBitmapAlpha xx="+Pxx+",yy="+Pyy+",bitmapsize ww="+ww+",hh="+hh+",canvas="+Pcanvas.toString());//~v@21R~
        Paint p=new Paint();                                       //~v@21I~
        p.setAlpha(Palpha);                                        //~v@21I~
        Rect src=new Rect(0,0,ww,hh);                              //~v@21I~
        Rect dst=new Rect(Pxx,Pyy,Pxx+ww,Pyy+hh);                  //~v@21I~
        Pcanvas.drawBitmap(Pbitmap,src,dst,p);                     //~v@21R~
    }                                                              //~v@21I~
    //****************************************************         //~v@@@I~
	public static void drawBitmap(Rect Prect,Bitmap Pbitmap)       //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawBitmap no canvas/point  parm bitmap="+Pbitmap.toString());//~v@@@R~
		drawBitmap(Prect,Pbitmap,Prect.left,Prect.top);            //~v@@@I~
    }                                                              //~v@@@I~
//    //****************************************************         //~v@@@I~//~v@21R~
//    public static void drawBitmap(Rect Prect,Bitmap Pbitmap,int Pxx,int Pyy)//~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawBitmap no canvas parm bitmap="+Pbitmap.toString());//~v@@@R~//~v@21R~
//        synchronized(AG.aGameView.holder)                          //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Canvas cc=lockCanvas(Prect);                           //~v@@@I~//~v@21R~
//            try                                                    //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
////              drawRect(cc,Prect,Pcolor);                         //~v@@@I~//~v@21R~
////              if (Pbitmap!=null)                                 //~v@@@I~//~v@21R~
//                drawBitmap(cc,Pbitmap,Pxx,Pyy);                    //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            catch(Exception e)                                     //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                Dump.println(e,"Graphics.drawBitmap");             //~v@@@R~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            unlockCanvas(cc);                                      //~v@@@R~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
    //****************************************************         //~v@@@I~
	public static void drawCircle(Point Ppos,int Pradius,int Pcolor)      //~v@@@R~//~v@21R~
    {                                                              //~v@@@I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21I~//~9A16R~
        {                                                          //~v@21I~
			drawCircle(AG.aGraphics.canvasShadow,Ppos,Pradius,Pcolor);                    //~v@@@I~//~v@21I~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@@@I~
    //****************************************************         //~v@21I~
	public static void drawCircle(Point Ppos,int Pradius,int Pcolor,int Pwidth)//~v@21R~
    {                                                              //~v@21I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21I~//~9A16R~
        {                                                          //~v@21I~
			drawCircle(AG.aGraphics.canvasShadow,Ppos,Pradius,Pcolor,Pwidth);//~v@21R~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
    //****************************************************         //~v@@@I~
    //*under synchronized                                          //~v@21I~
    //****************************************************         //~v@21I~
	public static void drawCircle(Canvas Pcanvas,Point Ppos,int Pradius,int Pcolor)//~v@@@I~//~v@21R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawCircle color="+Integer.toHexString(Pcolor));//~v@@@I~
        Paint p=new Paint();                                       //~v@@@I~
        p.setColor(Pcolor);                                        //~v@@@I~
        p.setAntiAlias(true);                                      //~v@@@I~
        Pcanvas.drawCircle(Ppos.x,Ppos.y,Pradius,p);               //~v@@@I~
//        AG.aGraphics.canvasShadow.drawCircle(Ppos.x,Ppos.y,Pradius,p);          //~v@@@I~//~v@21R~//~9A16R~
    }                                                              //~v@@@I~
    //****************************************************         //~v@21I~
	private static void drawCircle(Canvas Pcanvas,Point Ppos,int Pradius,int Pcolor,int Pwidth)//~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawCircle with Paint");//~v@21I~
        Paint p=new Paint();                                       //~v@21I~
        p.setColor(Pcolor);                                        //~v@21I~
        p.setAntiAlias(true);                                      //~v@21I~
        p.setStyle(Paint.Style.STROKE);                            //~v@21I~
        p.setStrokeWidth(Pwidth);                      //~v@21I~
        Pcanvas.drawCircle(Ppos.x,Ppos.y,Pradius,p);               //~v@21I~
    }                                                              //~v@21I~
//    //****************************************************         //~v@@@I~//~v@21R~
//    public void drawArc(RectF Prect, int Pstart, int Psweep, int Pcolor)//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        drawArc(Prect,Pstart,Psweep,Pcolor,(long)0/*colorOpaque*/);                //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
//    //****************************************************       //~v@21R~
//    public void drawArc(RectF Prect, int Pstart, int Psweep, int Pcolor,long PcolorOpaque)//~v@21R~
//    {                                                            //~v@21R~
//        synchronized(AG.aGraphics.bmShadow)                                   //~v@21R~//~9A16R~
//        {                                                        //~v@21R~
//            drawArc(AG.aGraphics.canvasShadow,Prect,Pstart,Psweep,Pcolor,PcolorOpaque);//~v@21R~//~9A16R~
//        }                                                        //~v@21R~
//    }                                                            //~v@21R~
    //****************************************************         //~v@@@I~
    //*under synchronized                                          //~v@21I~
    //****************************************************         //~v@21I~
	public static void drawArc(Canvas Pcanvas,RectF Prect, int Pstart, int Psweep, int Pcolor)//~v@21I~
//    {                                                            //~v@21R~
//        drawArc(Pcanvas,Prect,Pstart,Psweep,Pcolor,(long)0/*PcolorOpaque*/);//~v@21R~
//    }                                                            //~v@21R~
//    //****************************************************       //~v@21R~
//    public static void drawArc(Canvas Pcanvas,RectF Prect, int Pstart, int Psweep, int Pcolor,long PcolorOpaque)//~v@@@I~//~v@21R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawArc color="+Integer.toHexString(Pcolor));//~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawArc left="+Prect.left+",top="+Prect.top+",right="+Prect.right+",bottom="+Prect.bottom);//~v@@@I~
        Paint p=new Paint();                                       //~v@@@I~
        p.setColor(Pcolor);                                        //~v@@@I~
        p.setAntiAlias(true);                                      //~v@@@I~
        Pcanvas.drawArc(Prect,Pstart,Psweep,false/*useCenter*/,p);              //~v@@@I~//~v@21R~
//        if (PcolorOpaque!=0)                                     //~v@21R~
//        {                                                        //~v@21R~
//            p.setColor((int)PcolorOpaque);                       //~v@21R~
//            Pcanvas.drawArc(Prect,Pstart,Psweep,false/*useCenter*/,p);//~v@21R~
//        }                                                        //~v@21R~
//        AG.aGraphics.canvasShadow.drawArc(Prect,Pstart,Psweep,false,p);         //~v@@@I~//~v@21R~//~9A16R~
    }                                                              //~v@@@I~
    //****************************************************         //~v@@@I~
	public void drawArc(RectF Prect, int Pstart, int Psweep, int Pcolor,int Pwidth)//~v@@@I~
    {                                                              //~v@@@I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21I~//~9A16R~
        {                                                          //~v@21I~
			drawArc(AG.aGraphics.canvasShadow,Prect,Pstart,Psweep,Pcolor,Pwidth);         //~v@@@I~//~v@21I~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@@@I~
    //****************************************************         //~v@@@I~
    //*under synchronized                                          //~v@21I~
    //****************************************************         //~v@21I~
	public static void drawArc(Canvas Pcanvas,RectF Prect, int Pstart, int Psweep, int Pcolor,int Pwidth)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawArc color="+Integer.toHexString(Pcolor));//~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawArc left="+Prect.left+",top="+Prect.top+",right="+Prect.right+",bottom="+Prect.bottom+",width="+Pwidth);//~v@@@I~
        Paint p=new Paint();                                       //~v@@@I~
        p.setColor(Pcolor);                                        //~v@@@I~
        p.setStyle(Paint.Style.STROKE);                            //~v@@@I~
        p.setStrokeWidth(Pwidth);                                  //~v@@@I~
        p.setAntiAlias(true);                                      //~v@@@I~
//      Pcanvas.drawArc(Prect,Pstart,Psweep,true,p);               //~v@@@I~//~v@21R~
        Pcanvas.drawArc(Prect,Pstart,Psweep,false/*useCenter*/,p); //~v@21I~
//        AG.aGraphics.canvasShadow.drawArc(Prect,Pstart,Psweep,true,p);          //~v@@@I~//~v@21R~//~9A16R~
    }                                                              //~v@@@I~
    //****************************************************         //~v@21M~
    //*draw inner half circle                                      //~v@21M~
    //****************************************************         //~v@21M~
    public static void drawArc(Rect Prect/*lock range*/,RectF PrectArc,int Pstart, int Psweep,int Pcolor)//~v@21I~
    {                                                              //~v@21I~
	    drawArc(Prect,PrectArc,Pstart,Psweep,Pcolor,(long)0/*opaque*/);//~v@21I~
    }                                                              //~v@21I~
    //****************************************************         //~v@21I~
    public static void drawArc(Rect Prect/*lock range*/,RectF PrectArc,int Pstart, int Psweep,int Pcolor,long PcolorOpaque)//~v@21R~
    {                                                              //~v@21M~
        if (Dump.Y) Dump.println("Graphics.drawArc color="+Integer.toHexString(Pcolor));//~v@21M~
        if (Dump.Y) Dump.println("Graphics.drawArc left="+Prect.left+",top="+Prect.top+",right="+Prect.right+",bottom="+Prect.bottom);//~v@21M~
        if (Dump.Y) Dump.println("Graphics.drawArc start="+Pstart+",sweep="+Psweep);//~v@21M~
        if (Dump.Y) Dump.println("Graphics.drawArc opaq="+Integer.toHexString((int)PcolorOpaque));//~v@21I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21I~//~9A16R~
        {                                                          //~v@21M~
    		drawArc(AG.aGraphics.canvasShadow,PrectArc,Pstart,Psweep,Pcolor);   //~v@21I~//~9A16R~
    		drawArc(AG.aGraphics.canvasShadow,PrectArc,Pstart,Psweep,(int)PcolorOpaque);//~v@21I~//~9A16R~
        }                                                          //~v@21M~
    }                                                              //~v@21M~
    //****************************************************         //~v@21I~
    public static void drawRingArc(Rect Prect,RectF PrectArc,RectF PrectArcInner,int Pstart, int Psweep, int PringWidth,int PcolorRing,int PcolorInner)//~v@21M~
    {                                                              //~v@21M~
        if (Dump.Y) Dump.println("Graphics.drawRingArc colorRing="+Integer.toHexString(PcolorRing));//~v@21M~
        if (Dump.Y) Dump.println("Graphics.drawRingArc colorInner="+Integer.toHexString(PcolorInner));//~v@21M~
        if (Dump.Y) Dump.println("Graphics.drawRingArc left="+Prect.left+",top="+Prect.top+",right="+Prect.right+",bottom="+Prect.bottom);//~v@21M~
        if (Dump.Y) Dump.println("Graphics.drawRingArc start="+Pstart+",sweep="+Psweep);//~v@21M~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21I~//~9A16R~
        {                                                          //~v@21M~
    		drawRingArc(AG.aGraphics.canvasShadow,Prect,PrectArc,PrectArcInner,Pstart,Psweep,PringWidth,PcolorRing,PcolorInner);//~v@21R~//~9A16R~
        }                                                          //~v@21M~
    }                                                              //~v@21M~
    //****************************************************         //~v@@@I~
    //*under synchronized                                          //~v@21I~
    //****************************************************         //~v@21I~
    public static void drawRingArc(Canvas Pcanvas,Rect Prect,RectF PrectArc,RectF PrectArcInner,int Pstart, int Psweep,int PringWidth, int PcolorRing,int PcolorInner)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawRingArc with canvas colorRing="+Integer.toHexString(PcolorRing));//~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawRingArc with canvas colorInner="+Integer.toHexString(PcolorInner));//~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawRingArc with canvas left="+Prect.left+",top="+Prect.top+",right="+Prect.right+",bottom="+Prect.bottom);//~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawRingArc with canvas start="+Pstart+",sweep="+Psweep);//~v@@@I~
//      drawRect(Pcanvas,Prect,PcolorBG);                        //box//~v@@@R~
        drawArc(Pcanvas,PrectArc,Pstart,Psweep,PcolorRing,PringWidth);  //ring//~v@@@R~
        drawArc(Pcanvas,PrectArcInner,Pstart,Psweep,PcolorInner);       //~v@@@I~
    }                                                              //~v@@@I~
//    //****************************************************         //~v@@@I~//~v@21R~
////  public static void drawRingArc(Rect Prect,int PcolorBG,RectF PrectArc,int Pstart, int Psweep, int PcolorRing,RectF PrectFInnerArc,int PcolorInner)//~v@@@R~//~v@21R~
//    public static void drawRingArc(Rect Prect,RectF PrectArc,RectF PrectArcInner,int Pstart, int Psweep, int PringWidth,int PcolorRing,int PcolorInner)//~v@@@R~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRingArc colorRing="+Integer.toHexString(PcolorRing));//~v@@@R~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRingArc colorInner="+Integer.toHexString(PcolorInner));//~v@@@R~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRingArc left="+Prect.left+",top="+Prect.top+",right="+Prect.right+",bottom="+Prect.bottom);//~v@@@R~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawRingArc start="+Pstart+",sweep="+Psweep);//~v@@@R~//~v@21R~
////      RectF rectFInner=new RectF(PrectArc.left+Pwidth, PrectArc.top+Pwidth, PrectArc.right-Pwidth, PrectArc.bottom-Pwidth);//~v@@@R~//~v@21R~
////        int pad=2;                                               //~v@@@I~//~v@21R~
////        Rect rect=new Rect(Prect.left-pad, Prect.top-pad, Prect.right+pad, Prect.bottom+pad);//~v@@@I~//~v@21R~
//        synchronized(AG.aGameView.holder)                          //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
////          Canvas cc=lockCanvas(Prect);                           //~v@@@R~//~v@21R~
////          Canvas cc=lockCanvas(rect);                            //~v@@@R~//~v@21R~
//            Canvas cc=lockCanvas(Prect);                           //~v@@@I~//~v@21R~
//            try                                                    //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//////              drawRect(cc,Prect,PcolorBG);                        //box//~v@@@R~//~v@21R~
//////              drawRect(cc,rect,PcolorBG);                        //box//~v@@@R~//~v@21R~
////                drawRect(cc,Prect,PcolorBG);                        //box//~v@@@R~//~v@21R~
//////              drawArc(cc,PrectArc,Pstart,Psweep,PcolorRing,Pwidth);   //ring//~v@@@R~//~v@21R~
////                drawArc(cc,PrectArc,Pstart,Psweep,PcolorRing);  //ring//~v@@@R~//~v@21R~
//////              drawArc(cc,rectFInner,Pstart,Psweep,PcolorInner);//~v@@@R~//~v@21R~
////                drawArc(cc,PrectArcInner,Pstart,Psweep,PcolorInner);//~v@@@R~//~v@21R~
//                drawRingArc(cc,Prect,PrectArc,PrectArcInner,Pstart,Psweep,PringWidth,PcolorRing,PcolorInner);//~v@@@R~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            catch(Exception e)                                     //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                Dump.println(e,"Graphics.drawBitmap");             //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            unlockCanvas(cc);                                      //~v@@@R~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
//    //****************************************************         //~v@@@I~//~v@21R~
//    //*draw outer ring only                                        //~v@@@I~//~v@21R~
//    //****************************************************         //~v@@@I~//~v@21R~
//    public static void drawArc(Rect Prect/*lockRange*/,RectF PrectArc,int Pstart, int Psweep, int Pcolor,int Pwidth)//~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawArc colorRing="+Integer.toHexString(Pcolor));//~v@@@R~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawArc left="+Prect.left+",top="+Prect.top+",right="+Prect.right+",bottom="+Prect.bottom);//~v@@@R~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawArc start="+Pstart+",sweep="+Psweep+",width="+Pwidth);//~v@@@R~//~v@21R~
//        synchronized(AG.aGameView.holder)                          //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Canvas cc=lockCanvas(Prect);                           //~v@@@I~//~v@21R~
//            try                                                    //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                drawArc(cc,PrectArc,Pstart,Psweep,Pcolor,Pwidth);  //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            catch(Exception e)                                     //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                Dump.println(e,"Graphics.drawBitmap");             //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            unlockCanvas(cc);                                      //~v@@@R~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
    //****************************************************         //~v@21I~
    //*draw outer ring only                                        //~v@21I~
    //****************************************************         //~v@21I~
    public static void drawArc(Rect Prect/*lockRange*/,RectF PrectArc,int Pstart, int Psweep, int Pcolor,int Pwidth)//~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawArc colorRing="+Integer.toHexString(Pcolor));//~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawArc left="+Prect.left+",top="+Prect.top+",right="+Prect.right+",bottom="+Prect.bottom);//~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawArc start="+Pstart+",sweep="+Psweep+",width="+Pwidth);//~v@21I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21R~//~9A16R~
        {                                                          //~v@21I~
    		drawArc(AG.aGraphics.canvasShadow,PrectArc,Pstart,Psweep,Pcolor,Pwidth);//~v@21R~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
//    //****************************************************         //~v@@@I~//~v@21R~
//    //*draw inner half circle                                      //~v@@@I~//~v@21R~
//    //****************************************************         //~v@@@I~//~v@21R~
//    public static void drawArc(Rect Prect/*lock range*/,RectF PrectArc,int Pstart, int Psweep,int Pcolor)//~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawArc color="+Integer.toHexString(Pcolor));//~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawArc left="+Prect.left+",top="+Prect.top+",right="+Prect.right+",bottom="+Prect.bottom);//~v@@@R~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawArc start="+Pstart+",sweep="+Psweep);//~v@@@R~//~v@21R~
//        synchronized(AG.aGameView.holder)                          //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Canvas cc=lockCanvas(Prect);                           //~v@@@I~//~v@21R~
//            try                                                    //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                drawArc(cc,PrectArc,Pstart,Psweep,Pcolor);         //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            catch(Exception e)                                     //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                Dump.println(e,"Graphics.drawBitmap");             //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            unlockCanvas(cc);                                      //~v@@@R~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
    //****************************************************         //~v@@@I~
    //*draw outer ring only                                        //~v@21I~
    //****************************************************         //~v@21I~
	public static void drawText(Canvas Pcanvas,String Ptext,int Pxx, int Pyy,Paint Ppaint)//~v@@@R~//~v@21R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawText text="+Ptext+",xx="+Pxx+",yy="+Pyy+",canvas="+Pcanvas.toString());//~v@@@R~
        Pcanvas.drawText(Ptext,(float)Pxx,(float)Pyy,Ppaint);      //~v@@@I~
//        if (!PswLocal)                                             //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            AG.aGraphics.canvasShadow.drawText(Ptext,(float)Pxx,(float)Pyy,Ppaint);  //bitmap is work for text rendering//~v@@@R~//~v@21R~//~9A16R~
//            if (Dump.Y) Dump.println("Graphics.drawRect written shadow");//~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
    }                                                              //~v@@@I~
    //****************************************************         //~0215I~
	public static void drawText(Canvas Pcanvas,String Ptext,float Pxx, float Pyy,Paint Ppaint)//~0215I~
    {                                                              //~0215I~
        if (Dump.Y) Dump.println("Graphics.drawText float pos text="+Ptext+",xx="+Pxx+",yy="+Pyy+",canvas="+Pcanvas.toString()+",paint="+Ppaint.toString());//~0215R~
        Pcanvas.drawText(Ptext,Pxx,Pyy,Ppaint);                    //~0215I~
    }                                                              //~0215I~
//    //****************************************************         //~v@@@I~//~v@21R~
//    public static void drawText(Rect Prect,int Pbgcolor,String Ptext,int Pxx, int Pyy,Paint Ppaint)//~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawText without canvas parm text="+Ptext+",xx="+Pxx+",yy="+Pyy);//~v@@@I~//~v@21R~
//        synchronized(AG.aGameView.holder)                          //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Canvas cc=lockCanvas(Prect);                           //~v@@@I~//~v@21R~
//            try                                                    //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                drawRect(false,cc,Prect,Pbgcolor);                       //~v@@@I~//~v@21R~
//                drawText(false,cc,Ptext,Pxx,Pyy,Ppaint);                 //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            catch(Exception e)                                     //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                Dump.println(e,"Graphics.drawText");               //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            unlockCanvas(cc);                                      //~v@@@R~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
    //****************************************************         //~v@21I~
	public static void drawText(Rect Prect,int Pbgcolor,String Ptext,int Pxx, int Pyy,Paint Ppaint)//~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawText without canvas parm text="+Ptext+",xx="+Pxx+",yy="+Pyy);//~v@21I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21R~//~9A16R~
        {                                                          //~v@21I~
            drawRect(AG.aGraphics.canvasShadow,Prect,Pbgcolor);                 //~v@21R~//~9A16R~
            drawText(AG.aGraphics.canvasShadow,Ptext,Pxx,Pyy,Ppaint);           //~v@21R~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
    //****************************************************         //~9C02I~
	public static void drawText(Rect Prect,int Pbgcolor,String Ptext,int Pxx, int Pyy,Paint Ppaint,Rect PrectText,int PbgcolorText)//~9C02I~
    {                                                              //~9C02I~
        if (Dump.Y) Dump.println("Graphics.drawText without canvas parm text="+Ptext+",xx="+Pxx+",yy="+Pyy+",textBG="+Integer.toHexString(PbgcolorText));//~9C02I~
        synchronized(AG.aGraphics.bmShadow)                        //~9C02I~
        {                                                          //~9C02I~
            drawRect(AG.aGraphics.canvasShadow,Prect,Pbgcolor);    //~9C02I~
            drawRect(AG.aGraphics.canvasShadow,PrectText,PbgcolorText);//~9C02I~
            drawText(AG.aGraphics.canvasShadow,Ptext,Pxx,Pyy,Ppaint);//~9C02I~
        }                                                          //~9C02I~
    }                                                              //~9C02I~
    //****************************************************         //~v@@@I~
	public static void drawText(Canvas Pcanvas,String Ptext,float[] Ppos,Paint Ppaint)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.drawText by path text="+Ptext+",canvas="+Pcanvas.toString()+",Ppos="+ Arrays.toString(Ppos));//~v@@@R~//~0216R~
        Pcanvas.drawPosText(Ptext,Ppos,Ppaint);                       //~v@@@I~
//      AG.aGraphics.canvasShadow.drawPosText(Ptext,Ppos,Ppaint);        //bitmap is work//~v@@@R~//~9A16R~
    }                                                              //~v@@@I~
    //****************************************************         //~v@@@I~
//    public static void drawText(Rect Prect,int Pbgcolor,String Ptext,float[] Ppos,Paint Ppaint)//~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.drawText path without canvas parm text="+Ptext);//~v@@@I~//~v@21R~
//        synchronized(AG.aGameView.holder)                          //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Canvas cc=lockCanvas(Prect);                           //~v@@@I~//~v@21R~
//            try                                                    //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                drawRect(cc,Prect,Pbgcolor);                       //~v@@@I~//~v@21R~
//                drawText(cc,Ptext,Ppos,Ppaint);                    //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            catch(Exception e)                                     //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                Dump.println(e,"Graphics.drawText");               //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            unlockCanvas(cc);                                      //~v@@@R~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
    //****************************************************         //~v@21I~
	public static void drawText(Rect Prect,int Pbgcolor,String Ptext,float[] Ppos,Paint Ppaint)//~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Graphics.drawText path without canvas parm text="+Ptext);//~v@21I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21R~//~9A16R~
        {                                                          //~v@21I~
            drawRect(AG.aGraphics.canvasShadow,Prect,Pbgcolor);                 //~v@21R~//~9A16R~
            drawText(AG.aGraphics.canvasShadow,Ptext,Ppos,Ppaint);              //~v@21R~//~9A16R~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
    //****************************************************         //~9C02M~
	public static void drawText(Rect Prect,int Pbgcolor,String Ptext,float[] Ppos,Paint Ppaint,Rect PrectText,int PbgcolorText)//~9C02I~
    {                                                              //~9C02M~
        if (Dump.Y) Dump.println("Graphics.drawText by path text="+Ptext+",rect="+Prect.toString()+",textRect="+PrectText.toString()+",textBG="+Integer.toHexString(PbgcolorText));//~9C02I~
        synchronized(AG.aGraphics.bmShadow)                        //~9C02I~
        {                                                          //~9C02I~
            drawRect(AG.aGraphics.canvasShadow,Prect,Pbgcolor);    //~9C02I~
	    	drawRect(AG.aGraphics.canvasShadow,PrectText,PbgcolorText);//~9C02I~
            drawText(AG.aGraphics.canvasShadow,Ptext,Ppos,Ppaint); //~9C02I~
        }                                                          //~9C02I~
    }                                                              //~9C02M~
    //****************************************************         //~0215I~
	public static void drawText(Canvas Pcanvas,Rect Prect,int Pbgcolor,String Ptext,float[] Ppos,Paint Ppaint,Rect PrectText,int PbgcolorText)//~0215I~
    {                                                              //~0215I~
        if (Dump.Y) Dump.println("Graphics.drawText with canvas by path text="+Ptext+",rect="+Prect.toString()+",textRect="+PrectText.toString()+",textBG="+Integer.toHexString(PbgcolorText));//~0215I~
        drawRect(Pcanvas,Prect,Pbgcolor);                          //~0215I~
	    drawRect(Pcanvas,PrectText,PbgcolorText);                  //~0215I~
        drawText(Pcanvas,Ptext,Ppos,Ppaint);                       //~0215I~
    }                                                              //~0215I~
    //****************************************************         //~0215I~
	public static void drawText(Canvas Pcanvas,Rect Prect,int Pbgcolor,String Ptext,float Pxx,float Pyy,Paint Ppaint,Rect PrectText,int PbgcolorText)//~0215I~
    {                                                              //~0215I~
        if (Dump.Y) Dump.println("Graphics.drawText with canvas by float pos and bgcolor text="+Ptext+",rect="+Prect.toString()+",textRect="+PrectText.toString()+",textBG="+Integer.toHexString(PbgcolorText)+",paint="+Ppaint.toString());//~0215R~
        drawRect(Pcanvas,Prect,Pbgcolor);                          //~0215I~
	    drawRect(Pcanvas,PrectText,PbgcolorText);                  //~0215I~
        drawText(Pcanvas,Ptext,Pxx,Pyy,Ppaint);                    //~0215R~
    }                                                              //~0215I~
    //*********************************************************    //~v@@@I~//~v@21R~
    public static Canvas lockCanvas(Rect Prect)                    //~v@@@I~//~v@21R~
    {                                                              //~v@@@I~//~v@21R~
        if (Dump.Y) Dump.println("Graphics.lockCanvas l="+Prect.left+",t="+Prect.top+",r="+Prect.right+",b="+Prect.bottom);//~v@@@I~//~v@21R~
//        Canvas canvas=null;                                        //~v@@@R~//~v@21R~
//        try                                                        //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            canvas=AG.holder.lockCanvas(Prect);                    //~v@@@I~//~v@21R~
//            if (Dump.Y) Dump.println("Graphics.lockCanvas canvas="+(canvas==null ? "null" : canvas.toString()));//~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//        catch(Exception e)                                         //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Dump.println(e,"Graphics lockcanvas");                 //~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//        return canvas;                                            //~v@@@I~//~v@21R~
        return AG.aGraphics.canvasShadow;                                       //~v@21I~//~9A16R~
    }                                                              //~v@@@I~//~v@21R~
    //*********************************************************    //~v@@@I~
    public static Canvas lockCanvas()                              //~v@@@I~
    {                                                              //~v@@@I~
//        if (Dump.Y) Dump.println("Graphics.lockCanvas no dirtyrect");//~v@@@I~//~v@21R~
//        Canvas cc=null;                                            //~v@@@I~//~v@21R~
//        try                                                        //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            cc=AG.holder.lockCanvas();                             //~v@@@I~//~v@21R~
//            if (Dump.Y) Dump.println("Graphics.lockCanvas no dirtyrect canvas="+cc.toString());//~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//        catch(Exception e)                                         //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Dump.println(e,"Graphics lockcanvas");                 //~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//        return cc;                                                 //~v@@@I~//~v@21R~
        return AG.aGraphics.canvasShadow;                                       //~v@21R~//~9A16R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public  static void unlockCanvas(Canvas Pcanvas)               //~v@@@I~
    {                                                              //~v@@@I~
//        if (Pcanvas==null)                                         //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            if (Dump.Y) Dump.println("Graphics.unLockCanvas canvas=null");//~v@@@I~//~v@21R~
//            return;                                                //~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Graphics.unLockCanvas canvas="+Pcanvas.toString());//~v@@@I~//~v@21R~
//        AG.holder.unlockCanvasAndPost(Pcanvas);                    //~v@@@R~//~v@21R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@21I~
    public  static void unlockCanvas()                             //~v@21I~
    {                                                              //~v@21I~
    }                                                              //~v@21I~
	//***************************************************************//~v@@@I~
    public static Bitmap scaleBitmap(Bitmap Pbitmap,int Pw,int Ph,boolean Pswrecycle)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.scaleBitmap req w="+Pw+",h="+Ph);//~v@@@I~//~0216R~
        Bitmap bm=Bitmap.createScaledBitmap(Pbitmap,Pw,Ph,true/*fillter*/);//~v@@@I~
        if (Dump.Y) Dump.println("Graphics.scaleBitmap ww="+Pw+",hh="+Ph+",swrecycle="+Pswrecycle+",byteCount="+bm.getByteCount()+",bitmap="+bm.toString());//~0216R~
        if (Pswrecycle)                                            //~v@@@I~
	        UView.recycle(Pbitmap);                                //~v@21R~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    public static void drawDice(Canvas Pcanvas,Rect PrectEdge,int PcolorEdge,int PwidthEdge,Rect PrectBox,int PcolorBox,int PcolorFG,//~v@@@R~
    							Bitmap Pbitmap1,int Px1,int Py1,Bitmap Pbitmap2,int Px2,int Py2)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Graphics.DrawDice");                      //~v@@@I~//~v@21R~
		drawRect(Pcanvas,PrectEdge,PcolorEdge);                    //~v@@@R~
		drawRect(Pcanvas,PrectBox,PcolorBox);                      //~v@@@I~
		drawBitmap(Pcanvas,Pbitmap1,Px1,Py1);               //~v@@@I~
		drawBitmap(Pcanvas,Pbitmap2,Px2,Py2);               //~v@@@I~
        if (PcolorFG!=0)	//overidden color                      //~v@@@R~
        	drawRect(Pcanvas,PrectBox,PcolorFG);                   //~v@@@I~
                                                                   //~v@@@I~
    }                                                              //~v@@@I~
//    //***************************************************************//~v@@@I~//~v@21R~
//    public static void drawDiceBox(Rect PrectEdge,int PcolorEdge,int PwidthEdge,Rect PrectBox,int PcolorBox,int PcolorFG,//~v@@@R~//~v@21R~
//                                Bitmap Pbitmap1,int Px1,int Py1,Bitmap Pbitmap2,int Px2,int Py2)//~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        synchronized(AG.aGameView.holder)                          //~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            Canvas cc=lockCanvas(PrectEdge);                       //~v@@@I~//~v@21R~
//            try                                                    //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                drawDice(cc,PrectEdge,PcolorEdge,PwidthEdge,PrectBox,PcolorBox,PcolorFG,//~v@@@R~//~v@21R~
//                                Pbitmap1,Px1,Py1,Pbitmap2,Px2,Py2);//~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            catch(Exception e)                                     //~v@@@I~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                Dump.println(e,"Graphics.DiceBox");                //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//            unlockCanvas(cc);                                      //~v@@@R~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
    //***************************************************************//~v@21I~
    public static void drawDiceBox(Rect PrectEdge,int PcolorEdge,int PwidthEdge,Rect PrectBox,int PcolorBox,int PcolorFG,//~v@21I~
                                Bitmap Pbitmap1,int Px1,int Py1,Bitmap Pbitmap2,int Px2,int Py2)//~v@21I~
    {                                                              //~v@21I~
        synchronized(AG.aGraphics.bmShadow)                                     //~v@21R~//~9A16R~
        {                                                          //~v@21I~
            drawDice(AG.aGraphics.canvasShadow,PrectEdge,PcolorEdge,PwidthEdge,PrectBox,PcolorBox,PcolorFG,//~v@21R~//~9A16R~
                                Pbitmap1,Px1,Py1,Pbitmap2,Px2,Py2);//~v@21I~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
	//***************************************************************//~0407I~
    public static void drawLines(Canvas Pcanvas,float[] Ppoints,int Pcolor,int Pwidth)//~0407R~
    {                                                              //~0407I~
        if (Dump.Y) Dump.println("Graphics.drawLines color="+Integer.toHexString(Pcolor)+",width="+Pwidth+",float="+Arrays.toString(Ppoints));//~0407R~
        Paint p=new Paint();                                       //~0407I~
        p.setAntiAlias(true);                                      //~0407I~
        p.setColor(Pcolor);                                        //~0407I~
        p.setStyle(Paint.Style.STROKE);                            //~0407I~
        p.setStrokeWidth((float)Pwidth);                           //~0407I~
        if (Pcanvas==null)                                         //+0407I~
            AG.aGraphics.canvasShadow.drawLines(Ppoints,p);        //+0407I~
        else                                                       //+0407I~
	        Pcanvas.drawLines(Ppoints,p);                          //+0407R~
    }                                                              //~0407I~
}//class Graphics                                                 //~dataR~//~@@@@R~//~v@@@R~
