//*CID://+v@21R~: update#= 353;                                    //~v@21R~
//**********************************************************************//~v101I~
//v@21  imageview                                                  //~v@21I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.btmtest.utils.Dump;                                     //~v@21M~
import com.btmtest.utils.UFile;                                    //~v@21M~
import static com.btmtest.StaticVars.AG;                           //~v@21I~

public class ICanvas                                               //~v@21R~
{                                                                  //~0914I~
    private Canvas canvas;                                         //~v@@@I~
    private int WW,HH;                                             //~v@@@I~
    private Bitmap bmShadow;	//for redraw at resume         //~v@@@R~//~v@21R~
    private Canvas canvasShadow;                            //~v@@@I~//~v@21R~
//**************************************************************                                        //~v@@@I~//~v@21R~
    public ICanvas(int PscrW,int PscrH)                            //~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("ICanvas Constructor ww="+PscrW+",hh="+PscrH);//~v@21R~
        AG.aICanvas=this;                                          //~v@21R~
        WW=PscrW;  HH=PscrH;                                       //~v@21I~
//      bmShadow=Bitmap.createBitmap(WW,HH,Bitmap.Config.ARGB_8888);//~v@21R~
        bmShadow=Graphics.createBitmap(WW,HH,Bitmap.Config.ARGB_8888);//~v@21I~
        canvasShadow=new Canvas(bmShadow);                         //~v@21I~
        if (Dump.Y) Dump.println("ICanvas constructor createBitmap bmShadow="+bmShadow.toString());//~v@21R~
        new Graphics(canvasShadow,bmShadow,PscrW,PscrH);           //~v@21R~
    }                                                              //~v@21I~
////************************************************************** //~v@21R~
////*On UI Thread, draw shadowBitmap to canvas                     //~v@21R~
////************************************************************** //~v@21R~
//    public void onDraw(Canvas Pcanvas)                           //~v@21R~
//    {                                                            //~v@21R~
//        if (Dump.Y) Dump.println("ICanvas onDraw");              //~v@21R~
//        AG.aGraphics.onDraw(Pcanvas);                            //~v@21R~
//    }                                                            //~v@21R~
//**************************************************************   //~v@21I~
    public void onDestroy()                                        //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("ICanvas onDestroy");             //~v@21I~
//      save();                                                    //~v@21R~
	    reset();                                                   //~v@21I~
    }                                                              //~v@21I~
//**************************************************************   //~v@21I~
    private void reset()                                           //~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("ICanvas reset");                 //~v@21I~
        Graphics.reset();    //recycle bmShadow                    //+v@21R~
	    bmShadow=null;                                             //~v@21R~
        canvasShadow=null;                                         //~v@21I~
    }                                                              //~v@21I~
//**************************************************************   //~v@21I~
    public Canvas getCanvas()                                      //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("ICanvas getCanvas");             //~v@21I~
        return canvasShadow;                                  //~v@21I~
    }                                                              //~v@21I~
                                                                   //~v@21I~
//**************************************************************   //~v@21I~
//*save image to file                                              //~v@21I~
//**************************************************************   //~v@21I~
	public  void save()                                      //~v@21I~
    {                                                              //~1312I~//~v@21I~
		byte[] bytedata;                                           //~1312I~//~v@21I~
        String filename=getFilename();                             //~v@21I~
        if (Dump.Y) Dump.println("AjagoEmpty:save Bitmap-save start:"+filename);//~6A09R~//~v@21I~
		bytedata=bmp2byte(bmShadow);               //~1312I~       //~v@21I~
    	UFile.writeOutputFiles(filename,bytedata);//~1514R~       //~v@21I~
        if (Dump.Y) Dump.println("Bitmap-save end:"+filename);     //~v@21I~
    }                                                              //~1312I~//~v@21I~
//*************************                                        //~1312I~//~v@21I~
	public static byte[] bmp2byte(Bitmap Pbitmap)                         //~1312I~//~v@21I~
    {                                                              //~1312I~//~v@21I~
    	Bitmap.CompressFormat fmt=Bitmap.CompressFormat.PNG;       //~1312I~//~v@21I~
        int quality=100;       //100% no meaning for PNG           //~1312I~//~v@21I~
    	ByteArrayOutputStream os=new ByteArrayOutputStream();      //~1312I~//~v@21I~
        Pbitmap.compress(fmt,quality,os);                          //~1312I~//~v@21I~
        return os.toByteArray();                                   //~1312I~//~v@21I~
    }                                                              //~1312I~//~v@21I~
//*************************                                        //~1312I~//~v@21I~
	public static String getFilename()                             //~v@21I~
    {                                                              //~1312I~//~v@21I~
        return "BoardImage";                                       //~v@21I~
    }                                                              //~1312I~//~v@21I~
//**************************************************************   //~v@21I~
//*load saved image file                                           //~v@21I~
//**************************************************************   //~v@21I~
	public boolean load()                                          //~v@21I~
    {                                                              //~1120I~//~1122M~//~v@21I~
    	InputStream is;                                            //~1312I~//~v@21I~
        Bitmap bm;                                                 //~1312I~//~v@21I~
        String filename=getFilename();                             //~v@21I~
        if (Dump.Y) Dump.println("ICanvas:load Bitmap-load start:"+filename);//~6A09R~//~v@21I~
        is=UFile.openInputData(filename);    //~1514R~            //~v@21I~
        if (is==null)                                      //~1514R~//~v@21I~
        	return false;                                  //~1514R~//~v@21I~
        bm=BitmapFactory.decodeStream(is);                 //~1514R~//~v@21I~
        try                                                    //~1Ag6I~//~v@21I~
        {                                                      //~1Ag6I~//~v@21I~
                is.close();                                        //~1Ag6I~//~v@21I~
        }                                                      //~1Ag6I~//~v@21I~
        catch(IOException e)                                   //~1Ag6I~//~v@21I~
        {                                                      //~1Ag6I~//~v@21I~
        	Dump.println(e,"ICanvas.load close failed"+filename);//~1Ag6I~//~v@21I~
        }                                                      //~1Ag6I~//~v@21I~
        if (bm==null)                                               //~1312I~//~v@21I~
        	return false;                                      //~1312I~//~v@21I~
        if (Dump.Y) Dump.println("Bitmap-load shadow end:"+filename+",bitmap="+((Object)bm).toString());//~1506R~//~v@21I~
        bmShadow=bm;                                               //~v@21I~
        return true;                                               //~1312R~//~v@21I~
    }                                                              //~1120I~//~1122M~//~v@21I~
}//class ICanvas                                                 //~dataR~//~@@@@R~//~v@@@R~//~v@21R~
