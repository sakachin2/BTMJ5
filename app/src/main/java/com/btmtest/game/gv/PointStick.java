//*CID://+DATER~: update#= 572;                                    //~v@@@R~//~9506R~
//**********************************************************************//~v101I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import static com.btmtest.StaticVars.*;
import static com.btmtest.game.gv.Pieces.*;
import static com.btmtest.game.GConst.*;//~v@@@R~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~

import com.btmtest.game.Players;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

public class PointStick                                            //~v@@@R~
{                                                                  //~0914I~
    private static final int MARGINDUPX=8;                         //~v@@@R~
    private static final int SPACING_DUP=4;
    private static final int BASELINE_REACH=4;                     //~9510R~
    private static final int REACH_STICK_WIDTH=10;                 //~9509R~
    private static final int REACH_STICK_SPACING=4;                //~9509I~
    private static final int CTR_FOR_DUPSTICK_SPACE=8;//12;            //~9510I~//~9511R~
    private static final int TEXT_COLOR_CTR_FG=Color.argb(0xff,0xff,0xff,0xff);//~9506I~
    private static final int TEXT_COLOR_CTR_FG_REACHCTR=Color.argb(0xff,0xff,0xff,0xff);//~9510R~
//  private GCanvas gcanvas;                                       //~v@@@R~//~9506R~
    private Canvas canvas;                                         //~v@@@I~
    private MJTable table;                                         //~v@@@I~
    private Pieces pieces;                                         //~v@@@I~
    private Bitmap[][] bmssPointStick;                             //~v@@@R~
    private Bitmap[] bmsDupStick=new Bitmap[PLAYERS];              //~v@@@R~
    private Bitmap[] bmsReachContinuedStick=new Bitmap[PLAYERS];   //~v@@@I~
//  private Bitmap bmDupStick;                                     //~v@@@R~
    private Rect rectDup;                                          //~v@@@I~
    private Rect rectReach;                                        //~v@@@I~
    private Rect rectReachContinued;                               //~v@@@I~
    private Rect[] rectReachS=new Rect[PLAYERS];                   //~v@@@I~
    private Rect[] rectsReachContinued=new Rect[PLAYERS];          //~9506I~
    private Rect[] rectsReachContinuedCtr=new Rect[PLAYERS];       //~9510I~
    private Players PLS;                                           //~v@@@I~
    private boolean swShowReachLying=true;                         //~9510I~
//*************************                                        //~v@@@I~
	public PointStick(GCanvas Pgcanvas)                            //~v@@@R~
    {                                                              //~0914I~
    	AG.aPointStick=this;                                       //~v@@@R~
//      gcanvas = Pgcanvas;                                          //~v@@@I~//~9506R~
        table = AG.aMJTable;                                         //~9506R~
        pieces=table.pieces;                                       //~v@@@I~
//      bmssPointStick=pieces.bitmapPointStick;                    //~v@@@I~//~0216R~
        bmssPointStick=AG.bitmapPointStick;                        //~0216I~
        PLS=AG.aPlayers;                                           //~v@@@I~
    }
    //*********************************************************    //~v@@@I~
    public void newGame()                                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.newGame");            //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                              //~v@@@I~
        {                                                          //~v@@@I~
//      	rectReach=rectsReachContinued[ii];                     //~9506I~//~9510R~
        	rectReach=rectsReachContinuedCtr[ii];                  //~9510I~
    		resetReach();                                          //~9506I~
        	rectReach=rectReachS[ii];                              //~v@@@I~
    		resetReach();                                          //~v@@@R~
        }                                                          //~v@@@I~
        Arrays.fill(rectsReachContinuedCtr,null);                              //~v@@@I~//~9506R~//~9510R~
        Arrays.fill(rectsReachContinued,null);                     //~9510I~
        Arrays.fill(rectReachS,null);                              //~9506I~
	    resetStickDup();                                           //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*from Players                                                //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void reachDone(int Pplayer)                             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.reachDone player="+Pplayer);//~v@@@R~
        Bitmap bm=bmssPointStick[PIECE_COINS_REACH][Pplayer];      //~v@@@R~
        Point p=table.getPointStickPos(Pplayer);                   //~v@@@R~
        Rect rect=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@@@I~
//        int xx=p.x;                                              //~v@@@R~
//        int yy=p.y;                                              //~v@@@R~
//        canvas=Graphics.lockCanvas(rect);                        //~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            Graphics.drawBitmap(canvas,bm,xx,yy);                //~v@@@R~
//        }                                                        //~v@@@R~
//        catch(Exception e)                                       //~v@@@R~
//        {                                                        //~v@@@R~
//            Dump.println(e,"PointStick.discard");                //~v@@@R~
//        }                                                        //~v@@@R~
//        Graphics.unlockCanvas(canvas);                           //~v@@@R~
          Graphics.drawBitmap(rect,bm);                            //~v@@@I~
        rectReach=rect;                                            //~v@@@I~
        rectReachS[Pplayer]=rect;                                  //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*from Starter                                                //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void showReachContinued(Rect PrectStarter,int Pplayer,int PreachCtr)               //~v@@@R~//~9510R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.showReach player="+Pplayer+",reachctr="+PreachCtr);//~v@@@I~
        if (PreachCtr==0)                                           //~v@@@I~
        	return;                                                //~v@@@I~
        rectReachContinued=getRectReachContinued(PrectStarter,Pplayer);               //~v@@@I~//~9506R~//~9510R~
    	Bitmap bm=getBitmapReachContinued(Pplayer,rectReachContinued);      //~v@@@I~//~9509R~
        drawReachContinuedStick(Pplayer,bm,rectReachContinued,PreachCtr);//~v@@@I~
        rectsReachContinued[Pplayer]=rectReachContinued;           //~9506I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*public from Players                                         //~9511I~
	//***************************************************************//~9511I~
    public  void resetReach()                                      //~v@@@R~//~9511R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.resetReach rectReach="+Utils.toString(rectReach));         //~v@@@I~//~9904R~
    	if (rectReach==null)                                       //~v@@@I~
        	return;                                                //~v@@@I~
		Graphics.drawRect(rectReach,COLOR_BG_TABLE);               //~v@@@I~
    	rectReach=null;                                            //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*show dup stick(dealer's continued win+drawn ctr)                  //~v@@@I~//~9510R~
	//***************************************************************//~v@@@I~
    public void showStickDup(int Pplayer,Rect PrectStarter,int Pdupctr)//~v@@@I~
    {                                                              //~v@@@I~
    	Rect r=PrectStarter;                                       //~v@@@M~
        if (Dump.Y) Dump.println("PointStick.showStickDup player="+Pplayer+",dupctr="+Pdupctr+",rect l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@M~
        if (Pdupctr==0)                                               //~v@@@I~//~9509R~//~9510R~
        {                                                          //~v@@@I~//~9509R~//~9510R~
//            resetStickDup();    //if previously drawn              //~v@@@I~//~9509R~
            return;                                                //~v@@@I~//~9509R~//~9510R~
        }                                                          //~v@@@I~//~9509R~//~9510R~
        rectDup=getRectDup(Pplayer,r,Pdupctr);                     //~9510I~
    	Bitmap bm=getBitmapDup(Pplayer,Pdupctr);           //~v@@@I~
//      drawDupStick(Pplayer,bm,rectDup,Pdupctr);                  //~9510I~
        for (int ii=1;ii<=Pdupctr;ii++)                            //~9510R~
            drawDupStick(Pplayer,bm,rectDup,ii);                  //~v@@@I~//~9510R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    private void resetStickDup()                                   //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.resetStickDup rectDul==null:"+(rectDup==null));//~v@@@R~
    	if (rectDup==null)                                         //~v@@@I~
        	return;                                                //~v@@@I~
		Graphics.drawRect(rectDup,COLOR_BG_TABLE);                 //~v@@@I~
    	rectDup=null;                                              //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*rect:starte mark                                            //~9510I~
    //*********************************************************    //~9510I~
    private Rect getRectDup(int Pplayer,Rect Prect,int Pdupctr)    //~v@@@R~//~9510R~
    {                                                              //~v@@@I~
        int w,h,ww,rww,mx,rectH;                                //~v@@@I~
    //************************                                     //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.getRectDup player="+Pplayer+",dupctr="+Pdupctr+",starter rect="+Prect.toString());//~v@@@R~//~9510R~
        Rect r=Prect;                                              //~v@@@I~
        rectH=Math.min(r.right-r.left,r.bottom-r.top);             //~v@@@I~
        Bitmap bm=getBitmapDup(Pplayer,rectH);	//scale to height  //~v@@@I~
    	w=bm.getWidth();                                           //~v@@@I~
    	h=bm.getHeight();                                          //~v@@@I~
        ww=Math.min(w,h);                                          //~v@@@R~
        mx=MARGINDUPX;                                             //~v@@@I~
    	rww=(ww+SPACING_DUP)*Pdupctr+SPACING_DUP*((Pdupctr+3)/4-1)/*between each 4 */;//~v@@@R~//~9510R~
        Rect ro;                                                   //~v@@@I~
        switch(Pplayer)                                               //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
            ro=new Rect(0, r.top, r.left-mx, r.bottom);            //~v@@@I~
            ro.left=ro.right-rww;                                  //~v@@@I~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
            ro=new Rect(r.right+mx, r.top, 0, r.bottom);           //~v@@@I~
            ro.right=ro.left+rww;                                  //~v@@@I~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
            ro=new Rect(r.left, r.bottom+mx, r.right,0);           //~v@@@I~
            ro.bottom=ro.top+rww;                                  //~v@@@I~
            break;                                                 //~v@@@I~
        default:     //Left                                        //~v@@@I~
            ro=new Rect(r.left, 0, r.right, r.top-mx);             //~v@@@I~
            ro.top=ro.bottom-rww;                                  //~v@@@I~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.getRectDup player="+Pplayer+",dupstick rect="+ro.toString());//~9510R~
//      rectDup=ro;                                                //~v@@@I~//~9510R~
        return ro;                                                 //~9510I~
    }                                                              //~v@@@I~
    //*********************************************************    //~9506I~
    private Rect getRectReachContinued(Rect PrectStarter,int Pplayer)                //~9506I~//~9510R~
    {                                                              //~9506I~
        int w,h,ww,rww,mx,rectH;                                   //~9506I~
    //************************                                     //~9506I~
        if (Dump.Y) Dump.println("PointStick.getRectReachContinued player="+Pplayer+",birdW="+birdW+",birdH="+birdH);//~9506I~//~9510R~
        Rect ro,roctr;                                                   //~9506I~//~9510R~
        int xx=table.birdX[Pplayer];                               //~9506I~
        int yy=table.birdY[Pplayer];                               //~9506I~
      if (swShowReachLying)                                         //~9510I~
      {                                                            //~9510I~
	    Rect rdup=getRectDup(Pplayer,PrectStarter,CTR_FOR_DUPSTICK_SPACE);//~9510I~
        int wwdup=rdup.right-rdup.left;                            //~9510I~
        int hhdup=rdup.bottom-rdup.top;                            //~9510I~
        switch(Pplayer)                                            //~9510I~
        {                                                          //~9510I~
        case PLAYER_YOU:                                           //~9510I~
//          ro=new Rect(PrectStarter.left-wwdup,yy,PrectStarter.left,yy+birdH);//~9510R~
            ro=new Rect(rdup.left,yy,rdup.right,yy+birdH);         //~9510R~
//          roctr=new Rect(rdup.left,yy,PrectStarter.left,yy+birdH);//~9510R~
            roctr=new Rect(rdup.left,yy,xx,yy+birdH);              //~9510I~
            break;                                                 //~9510I~
        case PLAYER_FACING:                                        //~9510I~
//          ro=new Rect(PrectStarter.right,yy,PrectStarter.right+wwdup,yy+birdH);//~9510R~
            ro=new Rect(rdup.left,yy,rdup.right,yy+birdH);         //~9510R~
//          roctr=new Rect(PrectStarter.right,yy,rdup.right,yy+birdH);//~9510R~
            roctr=new Rect(xx+birdW,yy,rdup.right,yy+birdH);       //~9510I~
            break;                                                 //~9510I~
        case PLAYER_RIGHT:                                         //~9510I~
//          ro=new Rect(xx,PrectStarter.bottom,xx+birdH,PrectStarter.bottom+hhdup);//~9510R~
            ro=new Rect(xx,rdup.top,xx+birdH,rdup.bottom);         //~9510R~
//          roctr=new Rect(xx,PrectStarter.bottom,xx+birdH,rdup.bottom);//~9510R~
            roctr=new Rect(xx,yy+birdW,xx+birdH,rdup.bottom);      //~9510I~
            break;                                                 //~9510I~
        default:     //Left                                        //~9510I~
//          ro=new Rect(xx,PrectStarter.top-hhdup,xx+birdH,PrectStarter.top);//~9510R~
            ro=new Rect(xx,rdup.top,xx+birdH,rdup.bottom);         //~9510R~
//          roctr=new Rect(xx,rdup.top,xx+birdH,PrectStarter.top); //~9510R~
            roctr=new Rect(xx,rdup.top,xx+birdH,yy);               //~9510I~
            break;                                                 //~9510I~
        }                                                          //~9510I~
        rectsReachContinuedCtr[Pplayer]=roctr;                     //~9510I~
        if (Dump.Y) Dump.println("PointStick.getRectReachContinued roctr="+roctr.toString());//~9510I~
      }                                                            //~9510I~
      else                                                         //~9510I~
      {                                                            //~9510I~
        switch(Pplayer)                                            //~9506I~
        {                                                          //~9506I~
        case PLAYER_YOU:                                           //~9506I~
            ro=new Rect(xx-birdW,yy,xx,yy+birdH);                  //~9506I~
            break;                                                 //~9506I~
        case PLAYER_FACING:                                        //~9506I~
            ro=new Rect(xx+birdW,yy,xx+birdW+birdW,yy+birdH);      //~9506I~
            break;                                                 //~9506I~
        case PLAYER_RIGHT:                                         //~9506I~
            ro=new Rect(xx,yy+birdW,xx+birdH,yy+birdW+birdW);      //~9506I~
            break;                                                 //~9506I~
        default:     //Left                                        //~9506I~
            ro=new Rect(xx,yy-birdW,xx+birdH,yy);                  //~9506I~
            break;                                                 //~9506I~
        }                                                          //~9506I~
      }                                                            //~9510I~
        if (Dump.Y) Dump.println("PointStick.getRectReachContinued player="+Pplayer+",rect="+ro.toString());//~9506I~//~9509R~
                                                                   //~9510I~
        return ro;                                                 //~9506I~
    }                                                              //~9506I~
    //*********************************************************    //~v@@@I~
    private Bitmap getBitmapDup(int Pplayer,int PrectH)            //~v@@@I~
    {                                                              //~v@@@I~
    	int ww,hh,wws,hhs;                                         //~v@@@R~
    //******************************                               //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.getBitmapDup player="+Pplayer+",rectH="+PrectH);//~v@@@R~
    	if (bmsDupStick[Pplayer]!=null)                            //~v@@@I~
        	return bmsDupStick[Pplayer];                           //~v@@@I~
        Bitmap bm=bmssPointStick[PIECE_COINS_DUP][Pplayer==0 ? 3 : Pplayer-1]; //reverse lying and standing//~v@@@R~
        ww=bm.getWidth();                                          //~v@@@I~
        hh=bm.getHeight();                                         //~v@@@I~
        if (ww>hh)                                                 //~v@@@I~
        {                                                          //~v@@@I~
        	wws=PrectH; hhs=hh*wws/ww;                             //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	hhs=PrectH; wws=ww*hhs/hh;                             //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.getBitmapDump scaleing ww="+wws+",hh="+hhs);//~v@@@R~
        Bitmap bmdup=Pieces.scaleImage(bm,wws,hhs);                //~v@@@R~
//      UView.recycle(bm);                                         //~v@@@R~
        bmsDupStick[Pplayer]=bmdup;                               //~v@@@I~
        return bmdup;                                              //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private Bitmap getBitmapReachContinued(int Pplayer,Rect Prect) //~v@@@I~//~9509R~
    {                                                              //~v@@@I~
    	int ww,hh,wws,hhs;                                         //~v@@@I~
        Bitmap bm;                                                 //~9510I~
        Bitmap bmReachContinued;                                   //~9510I~
    //******************************                               //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.getBitmapReachContinued player="+Pplayer+",rect="+Prect.toString());//~v@@@I~//~9509R~
    	if (bmsReachContinuedStick[Pplayer]!=null)                 //~v@@@I~
        	return bmsReachContinuedStick[Pplayer];                //~v@@@I~
      if (swShowReachLying)                                         //~9510I~
      {                                                            //~9510I~
        bm=bmssPointStick[PIECE_COINS_REACH][Pplayer];             //~9510I~
        ww=bm.getWidth();                                          //~9510I~
        hh=bm.getHeight();                                         //~9510I~
        int rww=Prect.right-Prect.left;                            //~9510I~
        int rhh=Prect.bottom-Prect.top;                            //~9510I~
        if (Pplayer%2==0)                                          //~9510I~
        {                                                          //~9510I~
        	wws=rww-SPACING_DUP; hhs=wws*hh/ww;                    //~9510R~
        }                                                          //~9510I~
        else                                                       //~9510I~
        {                                                          //~9510I~
        	hhs=rhh-SPACING_DUP; wws=hhs*ww/hh;                    //~9510R~
        }                                                          //~9510I~
        if (Dump.Y) Dump.println("PointStick.getBitmapReachContinued scaleing rww="+rww+",ww="+ww+",wws="+wws+",rhh="+rhh+",hh="+hh+",hhs="+hhs);//~9510I~
        bmReachContinued=Pieces.scaleImage(bm,wws,hhs);            //~9510R~
      }                                                            //~9510I~
      else                                                         //~9510I~
      {                                                            //~9510I~
        bm=bmssPointStick[PIECE_COINS_REACH][Players.nextPlayer(Pplayer)]; //reverse lying and standing//~v@@@I~//~9509R~//~9510R~
        ww=bm.getWidth();                                          //~v@@@I~
        hh=bm.getHeight();                                         //~v@@@I~
        int rww=Prect.right-Prect.left;                            //~9509I~
        int rhh=Prect.bottom-Prect.top;                            //~9509I~
        if (Pplayer%2==0)                                                 //~v@@@I~//~9509R~
        {                                                          //~v@@@I~
//      	hhs=rhh; wws=hhs*ww/hh;                                //~9509R~
        	hhs=rhh; wws=REACH_STICK_WIDTH;                        //~9509I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//      	wws=rww; hhs=wws*hh/ww;                                //~9509R~
        	wws=rww; hhs=REACH_STICK_WIDTH;                        //~9509I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.getBitmapReachContinued scaleing rww="+rww+",ww="+ww+",wws="+wws+",rhh="+rhh+",hh="+hh+",hhs="+hhs);//~v@@@I~//~9509R~
        bmReachContinued=Pieces.scaleImage(bm,wws,hhs);                //~v@@@I~//~9506R~//~9510R~
      }                                                            //~9510I~
        bmsReachContinuedStick[Pplayer]=bmReachContinued;                                //~v@@@I~//~9506R~//~9510R~
        return bmReachContinued;                                              //~v@@@I~//~9506R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*rect:box dup stick                                          //~9510R~
    //*********************************************************    //~9510I~
    private void drawDupStick(int Pplayer,Bitmap Pbitmap,Rect Prect,int Pdupctr)//~v@@@I~
    {                                                              //~v@@@I~
        int xx0,yy0,ww,hh,xx,yy,stepx,stepy,stepx2,stepy2;         //~v@@@R~
    //************************                                     //~v@@@I~
    	ww=Pbitmap.getWidth();                                     //~v@@@I~
    	hh=Pbitmap.getHeight();                                    //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.drawDupStick ww="+ww+",hh="+hh+",dupctr="+Pdupctr);       //~v@@@I~//~9510R~
        Rect r=Prect;                                              //~v@@@I~
        switch(Pplayer)                                               //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
//          xx0=r.left; yy0=r.top; stepx=ww+SPACING_DUP; stepy=0;  //~v@@@R~
//          xx0=r.right-ww; yy0=r.top; stepx=-(ww+SPACING_DUP); stepy=0;//~v@@@I~//~9510R~
            xx0=r.right-ww-SPACING_DUP; yy0=r.top; stepx=-(ww+SPACING_DUP); stepy=0;//~9510R~
//          stepx2=SPACING_DUP;    stepy2=0; //more space for each 4 stick//~v@@@R~
            stepx2=-SPACING_DUP;    stepy2=0; //more space for each 4 stick//~v@@@I~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
//          xx0=r.right-ww; yy0=r.top; stepx=-(ww+SPACING_DUP); stepy=0;//~v@@@R~
//          xx0=r.left; yy0=r.top; stepx=(ww+SPACING_DUP); stepy=0;//~v@@@I~//~9510R~
            xx0=r.left+SPACING_DUP; yy0=r.top; stepx=(ww+SPACING_DUP); stepy=0;//~9510I~
            stepx2=SPACING_DUP;  stepy2=0;                         //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
//          xx0=r.left; yy0=r.bottom-hh; stepx=0; stepy=-(hh+SPACING_DUP);//~v@@@R~
//          xx0=r.left; yy0=r.top; stepx=0; stepy=(hh+SPACING_DUP);//~v@@@I~//~9510R~
            xx0=r.left; yy0=r.top+SPACING_DUP; stepx=0; stepy=(hh+SPACING_DUP);//~9510I~
            stepy2=SPACING_DUP;  stepx2=0;                         //~v@@@R~
            break;                                                 //~v@@@I~
        default:     //Left                                        //~v@@@I~
//          xx0=r.left; yy0=r.top      ; stepx=0; stepy=(hh+SPACING_DUP);//~v@@@R~
//          xx0=r.left; yy0=r.bottom-hh; stepx=0; stepy=-(hh+SPACING_DUP);//~v@@@R~//~9510R~
            xx0=r.left; yy0=r.bottom-hh-SPACING_DUP; stepx=0; stepy=-(hh+SPACING_DUP);//~9510I~
            stepy2=-SPACING_DUP; stepx2=0;                         //~v@@@R~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
//        xx=xx0; yy=yy0;                                          //~v@@@R~
//        for (int ii=0;ii<Pdupctr;ii++)                           //~v@@@R~
//        {                                                        //~v@@@R~
//            Rect rd=new Rect(xx,yy,xx+ww,yy+hh);                 //~v@@@R~
//            Graphics.drawBitmap(rd,Pbitmap);                     //~v@@@R~
//            xx+=stepx; yy+=stepy;                                //~v@@@R~
//            if (ii!=0 && ii%4==0)                                //~v@@@R~
//            {                                                    //~v@@@R~
//                xx+=stepx2; yy+=stepy2;                          //~v@@@R~
//            }                                                    //~v@@@R~
//        }                                                        //~v@@@R~
        xx=xx0+(Pdupctr-1)*stepx;                                  //~v@@@I~
		yy=yy0+(Pdupctr-1)*stepy;                                  //~v@@@I~
        int by4=(Pdupctr-1)/4;                                         //~v@@@I~//~9510R~
        xx+=by4*stepx2;                                        //~v@@@I~//~9510R~
        yy+=by4*stepy2;                                        //~v@@@I~//~9510R~
        Rect rd=new Rect(xx,yy,xx+ww,yy+hh);                       //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.drawDupStick rect="+rd.toString());//~9510I~
        Graphics.drawBitmap(rd,Pbitmap);                           //~v@@@I~
                                                                   //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*rect rectReachContinued                                     //~9510I~
    //*********************************************************    //~9510I~
    private void drawReachContinuedStick(int Pplayer,Bitmap Pbitmap,Rect Prect,int PreachCtr)//~v@@@I~//~9506R~
    {                                                              //~v@@@I~
        Rect r;                                                    //~9510I~
        int wwnum,hhnum;                                           //~9510I~
    //************************                                     //~v@@@I~
        if (Dump.Y) Dump.println("PointStick.drawReachContinuedStick player="+Pplayer+",reachCtr="+PreachCtr+",rect="+Prect.toString());//~9506R~//~9509R~
        int ww=Pbitmap.getWidth();                                 //~9509I~
        int hh=Pbitmap.getHeight();                                //~9509I~
      if (swShowReachLying)                                         //~9510I~
      {                                                            //~9510I~
        r=new Rect(Prect);                                         //~9510I~
        switch(Pplayer)                                            //~9510I~
        {                                                          //~9510I~
        case PLAYER_YOU:                                           //~9510I~
//          r.top=r.bottom-hh;                                     //~9510R~
//          r.top=(r.bottom+r.top-hh)/2;                           //~9510R~
//          r.bottom=r.top+hh/2+1;                                 //~9510R~
            r.bottom-=BASELINE_REACH;                              //~9510R~
            r.top=r.bottom-hh;                                     //~9510I~
            break;                                                 //~9510I~
        case PLAYER_FACING:                                        //~9510I~
//          r.bottom=r.top+hh;                                     //~9510R~
//          r.top=(r.bottom+r.top-hh)/2;                           //~9510R~
//          r.bottom=r.top+hh/2+1;                                 //~9510R~
            r.top+=BASELINE_REACH;                                 //~9510R~
            r.bottom=r.top+hh;                                     //~9510I~
            break;                                                 //~9510I~
        case PLAYER_RIGHT:                                         //~9510I~
//          r.left=r.right-ww;                                     //~9510R~
//          r.left=(r.left+r.right-ww)/2;                          //~9510R~
//          r.right=r.left+ww/2+1;                                 //~9510R~
            r.right-=BASELINE_REACH;                               //~9510R~
            r.left=r.right-ww;                                     //~9510I~
            break;                                                 //~9510I~
        default:     //Left                                        //~9510I~
//          r.right=r.left+ww;                                     //~9510R~
//          r.left=(r.left+r.right-ww)/2;                          //~9510R~
//          r.right=r.left+ww/2+1;                                 //~9510R~
            r.left+=BASELINE_REACH;                                //~9510R~
            r.right=r.left+ww;                                     //~9510I~
        }                                                          //~9510I~
        Graphics.drawBitmap(r,Pbitmap);                            //~9510I~
        r=new Rect(Prect);                                         //~9510I~
        wwnum=r.right-r.left;                                      //~9510R~
        hhnum=r.bottom-r.top;                                      //~9510R~
        switch(Pplayer)                                            //~9510I~
        {                                                          //~9510I~
        case PLAYER_YOU:                                           //~9510I~
//      	r.left=r.right-hhnum;                                  //~9510R~
        	r.left=r.right;                                        //~9510I~
        	r.right+=hhnum;                                        //~9510I~
            wwnum=hhnum;                                           //~9510I~
            break;                                                 //~9510I~
        case PLAYER_FACING:                                        //~9510I~
//      	r.right=r.left-hhnum;                                  //~9510R~
        	r.right=r.left;                                        //~9510I~
        	r.left-=hhnum;                                         //~9510I~
            wwnum=hhnum;                                           //~9510I~
            break;                                                 //~9510I~
        case PLAYER_RIGHT:                                         //~9510I~
//          r.bottom=r.top+wwnum;                                  //~9510R~
            r.bottom=r.top;                                        //~9510I~
            r.top-=wwnum;                                          //~9510I~
            hhnum=wwnum;                                           //~9510I~
            break;                                                 //~9510I~
        default:     //Left                                        //~9510I~
//          r.top=r.bottom-wwnum;                                  //~9510R~
            r.top=r.bottom;                                        //~9510I~
            r.bottom+=wwnum;                                       //~9510I~
            hhnum=wwnum;                                           //~9510I~
        }                                                          //~9510I~
      }                                                            //~9510I~
      else                                                         //~9510I~
      {                                                            //~9510I~
        int ww2=ww*2;                                              //~9509I~
        int hh2=hh*2;                                              //~9509I~
        r=new Rect(Prect);                                              //~v@@@I~//~9509R~//~9510R~
        switch(Pplayer)                                            //~9509I~
        {                                                          //~9509I~
        case PLAYER_YOU:                                           //~9509I~
            break;                                                 //~9509I~
        case PLAYER_FACING:                                        //~9509I~
            r.left=r.right-ww;                                     //~9509I~
            break;                                                 //~9509I~
        case PLAYER_RIGHT:                                         //~9509I~
            r.top=r.bottom-hh;                                     //~9509R~
            break;                                                 //~9509I~
        default:     //Left                                        //~9509I~
        }                                                          //~9509I~
        Graphics.drawBitmap(r,Pbitmap);                           //~v@@@I~//~9506R~
        r=new Rect(Prect);                                         //~9509I~
        hhnum=Math.max(ww,hh);                                 //~9509R~//~9510R~
        wwnum=hhnum;                                           //~9509R~//~9510R~
        switch(Pplayer)                                            //~9506I~
        {                                                          //~9506I~
        case PLAYER_YOU:                                           //~9506I~
            r.left+=ww+REACH_STICK_SPACING;  r.right=r.left+wwnum;                     //~9506I~//~9509R~
            break;                                                 //~9506I~
        case PLAYER_FACING:                                        //~9506I~
            r.right-=ww+REACH_STICK_SPACING; r.left=r.right-wwnum;                    //~9506I~//~9509R~
            break;                                                 //~9506I~
        case PLAYER_RIGHT:                                         //~9506I~
            r.bottom-=hh+REACH_STICK_SPACING; r.top=r.bottom-wwnum;                   //~9506I~//~9509R~
            break;                                                 //~9506I~
        default:     //Left                                        //~9506I~
            r.top+=hh+REACH_STICK_SPACING; r.bottom=r.top+wwnum;                      //~9506I~//~9509R~
        }                                                          //~9506I~
      }                                                            //~9510I~
        if (Dump.Y) Dump.println("PointStick.drawReachContinuedStick hhnum="+hhnum+",wwnum="+wwnum+",ctr rect="+r.toString());//~9506I~//~9509R~
	    drawReachContinuedStickCtr(Pplayer,wwnum,hhnum,r,PreachCtr);//~9506R~
    }                                                              //~v@@@I~
    //*********************************************************    //~9506I~
    private void drawReachContinuedStickCtr(int Pplayer,int Pww,int Phh,Rect Prect,int PreachCtr)//~9506R~
    {                                                              //~9506I~
    //************************                                     //~9506I~
        if (Dump.Y) Dump.println("PointStick.drawContinuedStickCtr player="+Pplayer+",Pww="+Pww+",Phh="+Phh+",reachCtr="+PreachCtr+",rect="+Prect.toString());//~9509R~
      if (swShowReachLying)                                         //~9510I~
      {                                                            //~9510I~
		Paint paintCtr=new Paint();                                //~9510I~
        paintCtr.setColor(TEXT_COLOR_CTR_FG_REACHCTR);             //~9510I~
        paintCtr.setAntiAlias(true);                               //~9510I~
        int textSize=Phh;                                          //~9510I~
        paintCtr.setTextSize(textSize);                            //~9510I~
        Rect r=Prect;                                              //~9510I~
//      Bitmap bm=Bitmap.createBitmap(Pww,Phh,Bitmap.Config.ARGB_8888);//~9510I~//+0216R~
        Bitmap bm=Graphics.createBitmap(Pww,Phh,Bitmap.Config.ARGB_8888);//+0216I~
        Canvas cc=new Canvas(bm);                                  //~9510I~
        Graphics.drawText(cc,Integer.toString(PreachCtr),0,Phh-BASELINE_REACH,paintCtr);//~9510R~
		Bitmap  bmr=rotateByPosition(bm,Pplayer);                  //~9510I~
        if (bm!=bmr)                                               //~9510I~
	        UView.recycle(bm);                                     //~9510I~
        Graphics.drawBitmap(r,bmr);                                //~9510I~
        UView.recycle(bmr);                                        //~9510I~
      }                                                            //~9510I~
      else                                                         //~9510I~
      {                                                            //~9510I~
		Paint paintCtr=new Paint();                                //~9506I~
        paintCtr.setColor(TEXT_COLOR_CTR_FG);                      //~9506I~
        paintCtr.setAntiAlias(true);                                  //~9509I~
        int textSize=Phh;                                          //~9509I~
        paintCtr.setTextSize(textSize);                               //~9509I~
        Rect r=Prect;                                              //~9506I~
//      Bitmap bm=Bitmap.createBitmap(Pww,Phh,Bitmap.Config.ARGB_8888);//~9506R~//+0216R~
        Bitmap bm=Graphics.createBitmap(Pww,Phh,Bitmap.Config.ARGB_8888);//+0216I~
        Canvas cc=new Canvas(bm);                                  //~9506I~
        Graphics.drawRect(cc,new Rect(0,0,Pww,Phh),COLOR_BG_TABLE);                    //~9506I~//~9509R~
//      Graphics.drawText(cc,Integer.toString(PreachCtr),0,0,paintCtr);            //~9506I~//~9509R~
        Graphics.drawText(cc,Integer.toString(PreachCtr),0,Phh,paintCtr);//~9509R~
		Bitmap  bmr=rotateByPosition(bm,Pplayer);                  //~9506I~
        if (bm!=bmr)                                               //~9509I~
	        UView.recycle(bm);                                          //~9506I~//~9509R~
        Graphics.drawBitmap(r,bmr);                                //~9506I~
        UView.recycle(bmr);                                         //~9506I~
      }                                                            //~9510I~
    }                                                              //~9506I~
    //*********************************************************    //~9506I~
    public static Bitmap rotateByPosition(Bitmap Pbm,int PposTo)          //~9506I~//~9519R~
    {                                                              //~9506I~
    //**************************                                   //~9506I~
        if (Dump.Y) Dump.println("PointStick.rotateByPosition to="+PposTo);//~9506I~
        Bitmap bm=Pbm;                                             //~9506I~
        int degree=0;                                              //~9506I~
        switch(PposTo)                                            //~9506I~
        {                                                          //~9506I~
        case 0:                                                    //~9506I~
        	degree=0;                                              //~9506I~
            break;                                                 //~9506I~
        case 1:                                                    //~9506I~
        	degree=270;     //by clockwise                         //~9506I~
            break;                                                 //~9506I~
        case 3:                                                    //~9506I~
        	degree=90;     //by clockwise                          //~9506I~
            break;                                                 //~9506I~
        default:                                                   //~9506I~
        	degree=180;                                            //~9506I~
        }                                                          //~9506I~
        if (degree!=0)                                             //~9506I~
        {                                                          //~9506I~
			Matrix matrix=new Matrix();                            //~9506I~
            int ww=bm.getWidth();                                  //~9506I~
            int hh=bm.getHeight();                                 //~9506I~
            matrix.setRotate(degree,ww/2,hh/2);                    //~9506I~
//      	bm=Bitmap.createBitmap(bm,0,0,ww,hh,matrix,true);      //~9506I~//+0216R~
        	bm=Graphics.createBitmap(bm,0,0,ww,hh,matrix,true);    //+0216I~
	        if (Dump.Y) Dump.println("NamePlate.rotateByPosition rotate degree="+degree+",ww="+ww+",hh="+hh);//~9506I~
        }                                                          //~9506I~
        return bm;                                                 //~9506I~
    }                                                              //~9506I~
    //*********************************************************    //~v@@@I~
    //*From UARon, Reach rest by Ron                               //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void complete(int Pplayer)                                   //~v@@@I~
    {                                                              //~v@@@I~
        TileData lastTD=AG.aPlayers.tileLastDiscarded;             //~v@@@I~
        if (PLS.swLastActionIsDiscard && lastTD.isReached())       //~v@@@R~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("PointStick.complete player="+Pplayer+",lastTD="+lastTD.toString());//~v@@@I~
        	resetReach();                                          //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
}//class PointStick                                                 //~dataR~//~@@@@R~//~v@@@R~
