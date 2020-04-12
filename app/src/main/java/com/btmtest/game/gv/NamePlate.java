//*CID://+DATER~: update#= 637;                                    //~v@@@R~//~v@21R~//~9317R~
//**********************************************************************//~v101I~
//v@21  imageview                                                  //~v@21I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import static com.btmtest.game.Complete.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~

import com.btmtest.dialog.RuleSetting;
import com.btmtest.game.Players;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;

import java.util.Arrays;

public class NamePlate                                             //~v@@@R~
{                                                                  //~0914I~
    private static final int TEXT_SIZE=30;                         //~v@@@R~//~9317R~
    private static final int TEXT_COLOR=Color.argb(0xff,0xff,0xff,0xff); //white//~v@@@R~
    private static final int TEXT_COLOR_BG=Color.argb(0xff,0x1d,0x20,0x88); //dark blue//~v@@@I~
    private static final int TEXT_COLOR_EDGE=Color.argb(0xff,0xff,0xff,0x00);//~v@@@I~//~0407R~
//  private static final int TEXT_COLOR_EDGE=Color.argb(0xff,0xff,0x00,0x00);    //TODO//~0407I~
    private static final int TEXT_COLOR_SCORE_BG=Color.argb(0xff,0x00,0xff,0xff);//~9317R~
    private static final int TEXT_COLOR_SCORE_BG_UNDER_PLUS=Color.argb(0xff,0xff,0xff,0xff);    //white//~9415R~
    private static final int TEXT_COLOR_SCORE_BG_UNDER_BASE=Color.argb(0xff,0xff,0xcc,0x99);    //orange//~9415I~
    private static final int TEXT_COLOR_SCORE_FG=Color.argb(0xff,0x00,0x00,0x00);//~9317I~
    private static final int TEXT_COLOR_DISABLE=Color.argb(0x80,0x00,0x59,0x00);//~v@@@I~
    private static final int COMPLETE_COLOR_SCORE=Color.argb(0xc0,Color.red(COMPLETE_COLOR),Color.green(COMPLETE_COLOR),Color.blue(COMPLETE_COLOR));//+0408R~
    private static final int PLATE_EDGE_WIDTH=2;                  //~v@@@R~//~9317R~//~0407R~
//  private static final int PLATE_EDGE_WIDTH=4; //TODO            //~0407R~
    private static final int TEXT_MARGIN_SIDE=10;                  //~v@@@I~
    private static final int TEXTBOX_MARGINH=2;                    //~v@@@R~//~v@21R~
    private static final int TEXTBOX_MARGINW=10;                   //~v@21I~
    private static final int TEXT_MARGIN=20;                       //~v@@@R~
    private static final int TEXT_MARGIN_SCORE=6;                  //~9317I~
    private static final double TEXT_SCALE=0.6;                     //~v@@@I~
    private static final int NPLAND_MARGIN_H=4; //distance between lamp and landscape nameplate//~9611I~
    private static final int NPLAND_RATE_W=2; 	//nameplate width=starterwidth*rate//~9611I~
                                                                   //~v@@@I~
    private MJTable table;                                         //~v@@@I~
    private Rect[] boundsPlate=new Rect[PLAYERS];                  //~v@@@R~
    private Rect[] rectPlate;                                      //~v@@@R~
    private Rect[] rectScore=new Rect[PLAYERS];                    //~9317I~
    private Rect[] rectScoreName=new Rect[PLAYERS];                //~0303I~
    private Rect[] rectBitmap=new Rect[PLAYERS];                   //~v@@@I~
    private String[] memberName;                                   //~v@@@I~
    private Bitmap[] bitmapPlate;                                  //~v@@@I~
    private Bitmap bmScore;                                        //~9317I~
    private Paint paint;                                           //~v@@@I~
    private Paint paintScore;                                      //~9317I~
    private int textBoxH;                                          //~v@@@R~
    private int textBoxW;                                          //~9317I~
    private int nameplateH,nameplateW;                             //~9317I~
    private boolean swVerticalEarth=false;                         //~v@21I~
    private int nameH,scoreH;                                      //~9317R~
    private int scoreBase,scorePlus;                               //~9415I~
    private int maxNPLhh;                                          //~9611R~
    private boolean swNPL=true;                                    //~9611I~
    private boolean swNPLLand=false;                               //~9806I~
    private int[] newPosition;                                     //~0324I~
//*************************                                        //~v@@@I~
	public NamePlate(GCanvas Pgcanvas)                             //~v@@@R~
    {                                                              //~0914I~
    	if (Dump.Y) Dump.println("NamePlate.constructor");         //~0217I~
	    recycle(AG.aNamePlate);                                    //~0217I~
    	AG.aNamePlate=this;                                        //~v@@@R~
        GCanvas gcanvas = Pgcanvas;                                          //~v@@@I~
        table = gcanvas.table;                                     //~v@@@I~
      if (AG.portrait || !swNPL)                                   //~9611R~
        rectPlate=table.rectNamePlate;                             //~v@@@I~
      else                                                         //~9611I~
        rectPlate=getRectPlateLandscape();                         //~9611I~
//      memberName=AG.aGC.memberName;                              //~v@@@I~//~0305R~
//      memberName=AG.aAccounts.getAccountNames();                 //~0305R~
        getRuleSetting();                                          //~9415I~
	    paint=setPaint();                                          //~v@@@I~
    }
    //******************************************                   //~0217I~
    public void onDestroy()                                        //~0217I~
    {                                                              //~0217I~
    	if (Dump.Y) Dump.println("NamePlate.onDestroy");           //~0217I~
        recycle(this);                                             //~0217I~
    }                                                              //~0217I~
    //******************************************                   //~0217I~
    private static void recycle(NamePlate Pinst)                   //~0217I~
    {                                                              //~0217I~
    	if (Dump.Y) Dump.println("NamePlate.recycle");             //~0217I~
        if (Pinst==null)                                           //~0217I~
        	return;                                                //~0217I~
        UView.recycle(Pinst.bmScore);                              //~0217I~
        Pinst.bmScore=null;                                        //~0217I~
	    Bitmap[] bms=Pinst.bitmapPlate;                            //~0217I~
        if (bms!=null)                                             //~0217I~
        {                                                          //~0217I~
	        for (int ii=0;ii<PLAYERS;ii++)                         //~0217I~
            {                                                      //~0217I~
		        UView.recycle(bms[ii]);                            //~0217R~
                bms[ii]=null;                                      //~0217I~
            }                                                      //~0217I~
		    Pinst.bitmapPlate=null;                                //~0217I~
        }                                                          //~0217I~
    }                                                              //~0217I~
    //******************************************                   //~9611I~
    private Rect[] getRectPlateLandscape()                         //~9611I~
    {                                                              //~9611I~
    	int hh,ww;                                                 //~9611I~
    	if (Dump.Y) Dump.println("NamePlate.getRectPlateLandscape");//~9611I~
        Rect[] rsS=AG.aStarter.getStarterRect();                   //~9611I~
        Rect[] rsL=AG.aDiceBox.getLightRect();                     //~9611I~
        Rect[] rsO=new Rect[PLAYERS];                              //~9611I~
        hh=rsS[0].top-rsL[0].bottom-NPLAND_MARGIN_H;               //~9611R~
        maxNPLhh=rsS[0].top-rsL[0].top; 	//max score height     //~9611R~
        ww=(rsS[0].right-rsS[0].left)*NPLAND_RATE_W;               //~9611I~
        rsO[0]=new Rect(rsS[0].right  , rsS[0].top-hh    , rsS[0].right+ww , rsS[0].top      );//~9611I~
        rsO[1]=new Rect(rsS[1].left-hh, rsS[1].top-ww    , rsS[1].left     , rsS[1].top      );//~9611I~
        rsO[2]=new Rect(rsS[2].left-ww, rsS[2].bottom    , rsS[2].left     , rsS[2].bottom+hh);//~9611I~
        rsO[3]=new Rect(rsS[3].right  , rsS[3].bottom    , rsS[3].right+hh , rsS[3].bottom+ww);//~9611I~
    	if (Dump.Y) Dump.println("NamePlate.getRectPlateLandscape ww="+ww+",hh="+hh+",maxNPLhh="+maxNPLhh);//~9611R~
    	if (Dump.Y) Dump.println("NamePlate.getRectPlateLandscape 0="+rsO[0].toString()+",1="+rsO[1].toString()+",2="+rsO[2].toString()+",3="+rsO[3].toString());//~9611I~
        return rsO;
    }                                                              //~9611I~
    //******************************************                   //~9415I~
    private void getRuleSetting()                                  //~9415I~
    {                                                              //~9415I~
    	scoreBase=RuleSetting.getInitialScore();                   //~9415I~
    	scorePlus=RuleSetting.getDebt();                           //~9415I~
    }                                                              //~9415I~
    //*********************************************************    //~v@@@I~
    private Paint setPaint()                            //~v@@@I~
    {                                                              //~v@@@I~
        Paint p=new Paint();                                       //~v@@@I~
        p.setAntiAlias(true);                                      //~v@@@I~
        p.setColor(TEXT_COLOR);                                    //~v@@@I~
        p.setTextSize(TEXT_SIZE);                                  //~v@@@I~
//      descent=(int)(p.getFontMetrics().descent);                 //~v@@@R~
		paintScore=new Paint(p);                                   //~9317R~
        paintScore.setColor(TEXT_COLOR_SCORE_FG);                  //~9317I~
        return p;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~9317I~
    private static int/*pixel*/ adjustTextSize(Paint Ppaint,String Pstr,int PinitialSize,int PmaxW,int PmaxH)//~9317R~
    {                                                              //~9317I~
        int sz=PinitialSize;                                           //~9317I~
        int ww,hh;                                                 //~9317I~
        for (;;)                                                   //~9317I~
        {                                                          //~9317I~
            Ppaint.setTextSize(sz);                                 //~9317I~
            int strsz=(int)Ppaint.measureText(Pstr);                //~9317I~
            ww=strsz;                                              //~9317I~
            Rect r=new Rect();                                     //~9317I~
    	    Ppaint.getTextBounds(Pstr,0,Pstr.length(),r);          //~9317I~
            hh=r.bottom-r.top;                                     //~9317I~
            if (Dump.Y) Dump.println("NamePlate.adjustTextSize str="+Pstr+",sz="+sz+",strsz="+strsz+",maxW="+PmaxW+",maxH="+PmaxH+",hh="+hh+",boundrect="+r.toString());//~9317R~//~9806R~
            if (strsz<PmaxW && hh<PmaxH)                           //~9317R~
                break;                                             //~9317I~
//          sz-=2;                                                 //~9317R~//~9806R~
            sz--;                                                  //~9806I~
        }                                                          //~9317I~
        if (Dump.Y) Dump.println("NamePlate.adjustTextSize sz="+sz+",ww="+ww+",hh="+hh);//~9317R~
        return sz;                                                 //~9317R~
    }                                                              //~9317I~
    //*********************************************************    //~v@@@I~
    public void showPlate()                                        //~v@@@R~
    {                                                              //~v@@@I~
    //**************************                                   //~v@@@I~
        if (Dump.Y) Dump.println("NamePlate.showPlate");           //~v@@@M~
    	if (bitmapPlate==null)                                     //~v@@@I~
        	init();                                                //~v@@@R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
            Rect rect=rectPlate[ii];                               //~v@@@R~
//          if (true)                //TODO test                   //~v@21R~
//          {                                                      //~v@21R~
//            Graphics.drawRect(rect,TEXT_COLOR_EDGE,PLATE_EDGE_WIDTH+ii*2);//~v@21R~
//          }                                                      //~v@21R~
//          else                                                   //~v@21R~
//          {                                                      //~v@21R~
            Rect rectbm=rectBitmap[ii];                            //~v@@@I~
            Bitmap bm=bitmapPlate[ii];                         //~v@@@I~
	        if (Dump.Y) Dump.println("NamePlate.showPlate ii="+ii);//~v@21I~
            Graphics.drawRectBitmap(rect,COLOR_BG_TABLE,bm,rectbm.left,rectbm.top);//~v@@@I~
//          }                                                      //~v@21R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@21I~
    //*after positioning end                                       //~v@21I~
    //*********************************************************    //~v@21I~
    public void showPlate(int[] PnewPosition)                      //~v@21I~
    {                                                              //~v@21I~
    //**************************                                   //~v@21I~
        if (Dump.Y) Dump.println("NamePlate.showPlate newPosition");//~v@21I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@21R~
        {                                                          //~v@21I~
        	int player=PnewPosition[ii];                           //~v@21R~
        	if (Dump.Y) Dump.println("NamePlate.showPlate newPosition ii="+ii+",player="+player);//~v@21R~
            Rect rect=rectPlate[ii];                               //~v@21I~
            Rect rectbm=rectBitmap[ii];                            //~v@21I~
            Bitmap bm=bitmapPlate[player];                         //~v@21R~
            Bitmap bmr=rotateByPosition(bm,player,ii);                     //~v@21I~
            Graphics.drawRectBitmap(rect,COLOR_BG_TABLE,bmr,rectbm.left,rectbm.top);//~v@21R~
            if (bmr!=bm)                                           //~v@21I~
                UView.recycle(bmr);                                //~v@21I~
        }                                                          //~v@21I~
        showScore();                              //~9317I~        //~9318R~
        newPosition=PnewPosition;                                  //~0324I~
    }                                                              //~v@21I~
    //*********************************************************    //~9317I~
    private Bitmap createBMScore()                                  //~9317I~//~0217R~
    {                                                              //~9317I~
    //**************************                                   //~9317I~
        Rect r=rectScore[PLAYER_YOU];                              //~9317I~
        int ww=r.right-r.left;                                     //~9317I~
        int hh=r.bottom-r.top;                                     //~9317I~
        if (Dump.Y) Dump.println("NamePlate.createBMScore rectScore="+rectScore.toString()+",ww="+ww+",hh="+hh);       //~9317I~//~9611I~
//      Bitmap bm=Bitmap.createBitmap(ww,hh,Bitmap.Config.ARGB_8888);//~9317I~//~0216R~
        Bitmap bm=Graphics.createBitmap(ww,hh,Bitmap.Config.ARGB_8888);//~0216I~
        return bm;                                                 //~9317I~
    }                                                              //~9317I~
    //*********************************************************    //~0324I~
    //*from ScoreDlg                                               //~0324I~
    //*River may overflow to NamePlate, so Show score after clear River//~0324I~
    //*********************************************************    //~0324I~
    public void showScoreClearRiver()                              //~0324I~
    {                                                              //~0324I~
        if (Dump.Y) Dump.println("NamePlate.showScoreClearRiver"); //~0324I~
//    if (false) //TODO Test                                       //~0324R~
    	boolean riverOverflow=AG.aRiver.clearRiver();              //~0324R~
        if (riverOverflow)                                         //~0324I~
        	showPlate(newPosition); //name and score               //~0324R~
        else                                                       //~0324I~
		    showScore();                                           //~0324I~
    }                                                              //~0324I~
    //*********************************************************    //~9317I~
    public void showScore()                            //~9317I~   //~9318R~
    {                                                              //~9317I~
    //**************************                                   //~9317I~
        if (Dump.Y) Dump.println("NamePlate.showScore");           //~9317I~
        if (bmScore==null)                                         //~9317I~
        	bmScore=createBMScore();                               //~9317I~
        int[] score=AG.aAccounts.score;                            //~9318I~
        if (Dump.Y) Dump.println("NamePlate.showScore score="+Arrays.toString(score));//~9904I~
        Canvas cc=new Canvas(bmScore);                             //~9317I~
        int ww=bmScore.getWidth();                                      //~9317I~
        int hh=bmScore.getHeight();                                     //~9317I~
        Rect r=new Rect(0,0,ww,hh);                                //~9317I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9317I~
        {                                                          //~9317I~
//      	String num=Integer.toString(score[ii]);	//account index seq               //~9317I~//~9318R~
//      	int idx=AG.aAccounts.playerToMember(ii);               //~9318I~//~9420R~
        	int idx=AG.aAccounts.playerToPosition(ii);             //~9420I~
        	String num=Integer.toString(score[idx]);	//account index seq//~9318I~
            int pos=rightJustify(r,num,paintScore);                //~9317I~
            int bg;                                                //~9415I~
            if (score[idx]>=scorePlus)                                    //~9415I~
	            bg=TEXT_COLOR_SCORE_BG;                            //~9415I~
            else                                                   //~9415I~
            if (score[idx]>=scoreBase)                                    //~9415I~
	            bg=TEXT_COLOR_SCORE_BG_UNDER_PLUS;                 //~9415R~
            else                                                   //~9415I~
	            bg=TEXT_COLOR_SCORE_BG_UNDER_BASE;                 //~9415I~
        	if (Dump.Y) Dump.println("NamePlate.showScore ii="+ii+",idx="+idx+",score="+score[idx]+",bgcolor="+Integer.toHexString(bg)+",initScore="+scoreBase+",plus="+scorePlus);//~9415I~
//          Graphics.drawRect(cc,r,TEXT_COLOR_SCORE_BG);           //~9317I~//~9415R~
            Graphics.drawRect(cc,r,bg);                            //~9415I~
            int xx=pos;                                            //~9317R~
            Rect br=new Rect();                                    //~9317I~
    		paintScore.getTextBounds(num,0,num.length(),br);       //~9317I~
//          int yy=-br.top+(scoreH+br.top)/2+PLATE_EDGE_WIDTH+TEXTBOX_MARGINH;       //~9317I~//~9318R~//~9806R~
            int yy=scoreH-br.bottom-TEXTBOX_MARGINH;               //~9806R~
        	if (Dump.Y) Dump.println("NamePlate.showScore ii="+ii+",num="+num+",paintScore.getTextBounds="+br.toString()+",scoreH="+scoreH+",nameH="+nameH+",yy="+yy);//~9806I~//~9904R~
            Graphics.drawText(cc,num,xx,yy,paintScore);            //~9317I~
//          Graphics.drawRect(cc,r,TEXT_COLOR_EDGE,PLATE_EDGE_WIDTH);//~9317I~//~0407R~
            drawLinesText(cc,r,TEXT_COLOR_EDGE,PLATE_EDGE_WIDTH);  //~0407R~
			Bitmap  bmr=rotateByPosition(bmScore,PLAYER_YOU,ii);        //~9317I~
            Graphics.drawBitmap(bmr,rectScore[ii].left,rectScore[ii].top);//~9317I~
            if (bmr!=bmScore)                                           //~9317I~
                UView.recycle(bmr);                                //~9317I~
        	Graphics.drawRect(rectBitmap[ii],TEXT_COLOR_EDGE,PLATE_EDGE_WIDTH);//~0303I~//~0407R~
        }                                                          //~9317I~
    }                                                              //~9317I~
    //*********************************************************    //~0407I~
    private void drawLinesText(Canvas Pcanvas,Rect Prect,int Pcolor,int Pwidth)//~0407I~
    {                                                              //~0407I~
        if (Dump.Y) Dump.println("NamePlate.drawLinesText color="+Integer.toHexString(Pcolor)+",width="+Pwidth+",rect="+Prect.toString());//~0407I~
    	float[] pts=new float[12];                                 //~0407R~
        pts[0]=Prect.left; pts[1]=Prect.bottom;                    //~0407I~
        pts[2]=Prect.left; pts[3]=Prect.top;                       //~0407I~
        pts[4]=Prect.left; pts[5]=Prect.top;                       //~0407I~
        pts[6]=Prect.right; pts[7]=Prect.top;                      //~0407R~
        pts[8]=Prect.right; pts[9]=Prect.top;                      //~0407I~
        pts[10]=Prect.right; pts[11]=Prect.bottom;                 //~0407R~
    	Graphics.drawLines(Pcanvas,pts,Pcolor,Pwidth);            //~0407I~
    }                                                              //~0407I~
    //*********************************************************    //~9823I~
    public void showScoreFinalPoint(String[] PfinalPoint1000,int[] PfinalPoint)//~9823I~
    {                                                              //~9823I~
    //**************************                                   //~9823I~
        if (Dump.Y) Dump.println("NamePlate.showScoreFinalPoint fp100="+ Arrays.toString(PfinalPoint1000)+",fp="+Arrays.toString(PfinalPoint));//~9823R~
        if (bmScore==null)                                         //~9823I~
        	bmScore=createBMScore();                               //~9823I~
//      int[] score=AG.aAccounts.score;                            //~9823I~
        int[] score=PfinalPoint;                                   //~9823I~
        String[] fp=PfinalPoint1000;                                  //~9823I~
        Canvas cc=new Canvas(bmScore);                             //~9823I~
        int ww=bmScore.getWidth();                                 //~9823I~
        int hh=bmScore.getHeight();                                //~9823I~
        Rect r=new Rect(0,0,ww,hh);                                //~9823I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9823I~
        {                                                          //~9823I~
        	int idx=AG.aAccounts.playerToPosition(ii);             //~9823I~
//      	String num=Integer.toString(score[idx]);	//account index seq//~9823I~
        	String num=fp[idx];	//account index seq                //~9823R~
            int pos=rightJustify(r,num,paintScore);                //~9823I~
            int bg;                                                //~9823I~
            if (score[idx]>=scorePlus)                             //~9823I~
	            bg=TEXT_COLOR_SCORE_BG;                            //~9823I~
            else                                                   //~9823I~
            if (score[idx]>=scoreBase)                             //~9823I~
	            bg=TEXT_COLOR_SCORE_BG_UNDER_PLUS;                 //~9823I~
            else                                                   //~9823I~
	            bg=TEXT_COLOR_SCORE_BG_UNDER_BASE;                 //~9823I~
        	if (Dump.Y) Dump.println("NamePlate.showScoreFinalPoint ii="+ii+",idx="+idx+",score="+score[idx]+",bgcolor="+Integer.toHexString(bg)+",initScore="+scoreBase+",plus="+scorePlus);//~9823I~
            Graphics.drawRect(cc,r,bg);                            //~9823I~
            int xx=pos;                                            //~9823I~
            Rect br=new Rect();                                    //~9823I~
    		paintScore.getTextBounds(num,0,num.length(),br);       //~9823I~
            int yy=scoreH-br.bottom-TEXTBOX_MARGINH;               //~9823I~
        	if (Dump.Y) Dump.println("NamePlate.showScoreFinalPoint paintScore.getTextBounds="+br.toString()+",scoreH="+scoreH+",nameH="+nameH+",yy="+yy);//~9823R~
            Graphics.drawText(cc,num,xx,yy,paintScore);            //~9823I~
//          Graphics.drawRect(cc,r,TEXT_COLOR_EDGE,PLATE_EDGE_WIDTH);//~9823I~//~0407R~
            drawLinesText(cc,r,TEXT_COLOR_EDGE,PLATE_EDGE_WIDTH);  //~0407I~
			Bitmap  bmr=rotateByPosition(bmScore,PLAYER_YOU,ii);   //~9823I~
            Graphics.drawBitmap(bmr,rectScore[ii].left,rectScore[ii].top);//~9823I~
            if (bmr!=bmScore)                                      //~9823I~
                UView.recycle(bmr);                                //~9823I~
        }                                                          //~9823I~
    }                                                              //~9823I~
    //*********************************************************    //~9317I~
    public int rightJustify(Rect Prect,String Pstr,Paint Ppaint)   //~9317I~
    {                                                              //~9317I~
    	int strsz=(int)paintScore.measureText(Pstr);               //~9317I~
        int ww=Prect.right-Prect.left;                             //~9317I~
        int pos=ww-TEXTBOX_MARGINW-strsz;                          //~9317I~
        if (pos<0)                                                 //~9317I~
        	pos=0;                                                 //~9317I~
        if (Dump.Y) Dump.println("NamePlate.rightJustify str="+Pstr+",strsz="+strsz+",ww="+ww+",pos="+pos);//~9317R~
        return pos;                                                //~9317I~
    }                                                              //~9317I~
    //*********************************************************    //~v@21I~
    private Bitmap rotateByPosition(Bitmap Pbm,int PposFrom,int PposTo)//~v@21I~//~0217R~
    {                                                              //~v@21I~
    //**************************                                   //~v@21I~
        if (Dump.Y) Dump.println("NamePlate.rotateByPosition from="+PposFrom+",to="+PposTo);//~v@21I~
        Bitmap bm=Pbm;                                             //~v@21I~
        int degree;                                                //~v@21I~
        if (PposFrom==PposTo)                                      //~v@21I~
        	degree=0;                                              //~v@21I~
        else                                                       //~v@21I~
        if (PposTo==Players.nextPlayer(PposFrom))   //move to right//~v@21I~
        {                                                          //~v@21I~
        	degree=270;     //by clockwise                         //~v@21I~
        }                                                          //~v@21I~
        else                                                       //~v@21I~
        if (PposFrom==Players.nextPlayer(PposTo))   //move to left //~v@21I~
        {                                                          //~v@21I~
        	degree=90;     //by clockwise                          //~v@21I~
        }                                                          //~v@21I~
        else		  //move to facing                             //~v@21I~
        {                                                          //~v@21I~
        	degree=180;                                            //~v@21I~
        }                                                          //~v@21I~
        if (degree!=0)                                             //~v@21I~
        {                                                          //~v@21I~
			Matrix matrix=new Matrix();                            //~v@21I~
            int ww=bm.getWidth();                                  //~v@21I~
            int hh=bm.getHeight();                                 //~v@21I~
            matrix.setRotate(degree,ww/2,hh/2);                  //~v@21I~
//      	bm=Bitmap.createBitmap(bm,0,0,ww,hh,matrix,true);    //~v@21I~//~0216R~
        	bm=Graphics.createBitmap(bm,0,0,ww,hh,matrix,true);    //~0216I~
	        if (Dump.Y) Dump.println("NamePlate.rotateByPosition rotate degree="+degree+",ww="+ww+",hh="+hh);//~v@21I~
        }                                                          //~v@21I~
        return bm;                                                 //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~v@@@I~
    private void init()                                            //~v@@@I~
    {                                                              //~v@@@I~
    	adjustRectHeight();                                        //~v@@@I~
    	createBitmap();                                            //~v@@@I~
    	adjustRectWidth();                                         //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void adjustRectHeight()                                     //~v@@@I~
    {                                                              //~v@@@I~
    	Rect r;                                                    //~v@@@I~
        int minH,hh;                                               //~v@@@I~
        int minW,ww;                                               //~v@21I~
        r=rectPlate[PLAYER_YOU];        minH=r.bottom-r.top;       //~v@@@I~
        								minW=r.right-r.left;                                       //~v@21I~//~9317R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight rectPlate You="+r. toString());//~9806I~
        r=rectPlate[PLAYER_FACING];     hh=r.bottom-r.top;         minH=Math.min(minH,hh);//~v@@@I~
                                        ww=r.right-r.left;         minW=Math.min(minW,ww);//~v@21I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight rectPlate Facing="+r. toString());//~9806I~
        r=rectPlate[PLAYER_RIGHT];      hh=r.right-r.left;         minH=Math.min(minH,hh);//~v@@@I~
                                        ww=r.bottom-r.top;         minW=Math.min(minW,ww);//~v@21I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight rectPlate Right="+r. toString());//~9806I~
        r=rectPlate[PLAYER_LEFT];       hh=r.right-r.left;         minH=Math.min(minH,hh);//~v@@@I~
                                        ww=r.bottom-r.top;         minW=Math.min(minW,ww);//~v@21I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight rectPlate Left="+r. toString());//~9806I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight minH="+minH+",minW="+minW);//~9806I~
    swNPLLand=false;                                               //~9806I~
     if (swVerticalEarth)    //false                                                   //~v@@@I~//~v@21I~//~9611R~
     {                                                             //~v@21I~
        minH=(int)(minH*TEXT_SCALE);                               //~v@@@I~
        minH-=TEXTBOX_MARGINH;                                     //~v@@@R~//~v@21R~
        r=rectPlate[PLAYER_YOU];        r.top+=TEXTBOX_MARGINH;       r.bottom=r.top+minH;//~v@@@R~//~v@21R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player you l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@I~
        r=rectPlate[PLAYER_FACING];     r.bottom-=TEXTBOX_MARGINH;    r.top=r.bottom-minH;//~v@@@R~//~v@21R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player facing l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@I~
        r=rectPlate[PLAYER_RIGHT];      r.left+=TEXTBOX_MARGINH;      r.right=r.left+minH;//~v@@@R~//~v@21R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player right l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@I~
        r=rectPlate[PLAYER_LEFT];       r.right-=TEXTBOX_MARGINH;     r.left=r.right-minH;//~v@@@R~//~v@21R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player left l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@I~
        textBoxH=minH;                                             //~v@@@I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight minH="+minH);//~v@@@I~
      }                                                            //~v@21I~
      else                                                         //~v@21I~
     {                                                             //~v@21I~
	    if (!AG.portrait && swNPL)                                 //~9806I~
        {                                                          //~9806I~
	        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight Land and swNPL old minH="+minH);//~9806I~
	        if (minH<(maxNPLhh-NPLAND_MARGIN_H*2)/2)               //~9806I~
            {                                                      //~9806I~
            	minH=(maxNPLhh-NPLAND_MARGIN_H*2)/2;               //~9806I~
			    swNPLLand=true;                                    //~9806I~
            }                                                      //~9806I~
	        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight Land and swNPL minH="+minH+",maxNPLhh="+maxNPLhh);//~9806I~
        }                                                          //~9806I~
        minH-=TEXTBOX_MARGINH*2;                                   //~v@21R~
        minW-=TEXTBOX_MARGINW;                                     //~v@21R~
        r=rectPlate[PLAYER_YOU];                                   //~9317I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player you="+r.toString());//~9317I~
        r.bottom-=TEXTBOX_MARGINH;       r.top=r.bottom-minH;//~v@21R~//~9317R~
        r.right-=TEXTBOX_MARGINW;         r.left=r.right-minW;//~v@21R~//~9317R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player you="+r.toString());//~v@21I~
        r=rectPlate[PLAYER_FACING];                                //~9317R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player facing="+r.toString());//~9317I~
        r.top+=TEXTBOX_MARGINH;    r.bottom=r.top+minH;            //~9317I~
        r.left+=TEXTBOX_MARGINW;    r.right=r.left+minW;//~v@21R~  //~9317R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player facing="+r.toString());//~v@21I~
        r=rectPlate[PLAYER_RIGHT];                                 //~9317R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player right="+r.toString());//~9317I~
        r.right-=TEXTBOX_MARGINH;  r.left=r.right-minH;            //~9317I~
        r.top+=TEXTBOX_MARGINW;     r.bottom=r.top+minW;//~v@21R~  //~9317R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player right="+r.toString());//~v@21I~
        r=rectPlate[PLAYER_LEFT];                                  //~9317R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player left="+r.toString());//~9317I~
        r.left+=TEXTBOX_MARGINH;   r.right=r.left+minH;            //~9317I~
        r.bottom-=TEXTBOX_MARGINW;  r.top=r.bottom-minW;//~v@21R~  //~9317R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight player left="+r.toString());//~v@21I~
        textBoxH=(minH/2)*2;                                             //~v@21I~//~9317R~
        textBoxW=(minW/2)*2;	//to get center                    //~9317R~
        if (Dump.Y) Dump.println("NamePlate.adjustRectHeight textBoxH="+textBoxH+",textBoxW="+textBoxW);//~v@21I~//~9317R~
      }                                                            //~v@21I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void adjustRectWidth()                                      //~v@@@I~
    {                                                              //~v@@@I~
		Bitmap bm;                                                 //~v@@@I~
        Rect r,ro;                                                 //~v@@@R~
        int center,ww,hh;                                                   //~v@@@I~
    //**********************************                           //~v@@@I~
      if (swVerticalEarth)                                         //~9317I~
      {                                                            //~9317I~
    	bm=bitmapPlate[PLAYER_YOU]; ww=bm.getWidth();              //~v@@@I~
    	r=rectPlate[PLAYER_YOU];    center=(r.right+r.left)/2;     //~v@@@R~
        ro=new Rect(center-ww/2, r.top, center+ww/2, r.bottom);    //~v@@@R~
        rectBitmap[PLAYER_YOU]=ro;                                 //~v@@@I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player You l="+ro.left+",t="+ro.top+",r="+ro.right+",b="+ro.bottom);//~v@@@I~
                                                                   //~v@@@I~
    	bm=bitmapPlate[PLAYER_FACING]; ww=bm.getWidth();           //~v@@@I~
    	r=rectPlate[PLAYER_FACING];    center=(r.right+r.left)/2;  //~v@@@R~
        ro=new Rect(center-ww/2, r.top, center+ww/2, r.bottom);    //~v@@@R~
        rectBitmap[PLAYER_FACING]=ro;                              //~v@@@I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player facing l="+ro.left+",t="+ro.top+",r="+ro.right+",b="+ro.bottom);//~v@@@I~
                                                                   //~v@@@I~
    	bm=bitmapPlate[PLAYER_RIGHT]; hh=bm.getHeight();           //~v@@@I~
    	r=rectPlate[PLAYER_RIGHT];    center=(r.bottom+r.top)/2;   //~v@@@R~
        ro=new Rect(r.left, center-hh/2, r.right, center+hh/2);    //~v@@@R~
        rectBitmap[PLAYER_RIGHT]=ro;                               //~v@@@I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player right l="+ro.left+",t="+ro.top+",r="+ro.right+",b="+ro.bottom);//~v@@@I~
                                                                   //~v@@@I~
    	bm=bitmapPlate[PLAYER_LEFT];  hh=bm.getHeight();           //~v@@@I~
    	r=rectPlate[PLAYER_LEFT];     center=(r.bottom+r.top)/2;   //~v@@@R~
        ro=new Rect(r.left, center-hh/2, r.right, center+hh/2);    //~v@@@R~
        rectBitmap[PLAYER_LEFT]=ro;                                //~v@@@I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player left l="+ro.left+",t="+ro.top+",r="+ro.right+",b="+ro.bottom);//~v@@@I~
      }                                                            //~9317I~
      else                                                         //~9317I~
      {                                                            //~9317I~
    	bm=bitmapPlate[PLAYER_YOU]; ww=bm.getWidth(); hh=bm.getHeight();//~9317R~
    	r=rectPlate[PLAYER_YOU];                                   //~9317I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player You rectBitmap="+r.toString());//~9317I~
        ro=new Rect(r.left,r.bottom-hh,r.left+ww,r.bottom);        //~9317I~
        rectBitmap[PLAYER_YOU]=ro;                                 //~9317I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player You rectBitmap="+ro.toString());//~9317I~
                                                                   //~9317I~
    	bm=bitmapPlate[PLAYER_FACING]; ww=bm.getWidth(); hh=bm.getHeight();//~9317R~
    	r=rectPlate[PLAYER_FACING];                                //~9317I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player Facing rectBitmap="+r.toString());//~9317I~
        ro=new Rect(r.left,r.top,r.left+ww,r.top+hh);              //~9317I~
        rectBitmap[PLAYER_FACING]=ro;                              //~9317I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player Facing rectBitmap="+ro.toString());//~9317I~
                                                                   //~9317I~
    	bm=bitmapPlate[PLAYER_RIGHT]; ww=bm.getWidth(); hh=bm.getHeight();//~9317R~
    	r=rectPlate[PLAYER_RIGHT];                                 //~9317I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player Right rectBitmap="+r.toString());//~9317I~
        ro=new Rect(r.right-ww,r.top,r.right,r.top+hh);            //~9317I~
        rectBitmap[PLAYER_RIGHT]=ro;                               //~9317I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player Right rectBitmap="+ro.toString());//~9317I~
                                                                   //~9317I~
    	bm=bitmapPlate[PLAYER_LEFT]; ww=bm.getWidth(); hh=bm.getHeight();//~9317R~
    	r=rectPlate[PLAYER_LEFT];                                  //~9317I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player Left rectBitmap="+r.toString());//~9317I~
        ro=new Rect(r.left,r.top,r.left+ww,r.top+hh);              //~9317I~
        rectBitmap[PLAYER_LEFT]=ro;                                //~9317I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth player Left rectBitmap="+ro.toString());//~9317I~
                                                                   //~9317I~
		rectScore[PLAYER_YOU]=new Rect(rectBitmap[PLAYER_YOU]);     //~9317I~
        rectScore[PLAYER_YOU].bottom=rectScore[PLAYER_YOU].top;    //~9317R~
        rectScore[PLAYER_YOU].top=rectScore[PLAYER_YOU].top-scoreH;//~9317I~
        rectScoreName[PLAYER_YOU]=new Rect(rectScore[PLAYER_YOU].left,rectScore[PLAYER_YOU].top,rectScore[PLAYER_YOU].right,rectBitmap[PLAYER_YOU].bottom);//~0303I~
                                                                   //~9317I~
		rectScore[PLAYER_RIGHT]=new Rect(rectBitmap[PLAYER_RIGHT]); //~9317I~
        rectScore[PLAYER_RIGHT].right=rectScore[PLAYER_RIGHT].left;//~9317R~
        rectScore[PLAYER_RIGHT].left=rectScore[PLAYER_RIGHT].left-scoreH;//R//~9317I~
        rectScoreName[PLAYER_RIGHT]=new Rect(rectScore[PLAYER_RIGHT].left,rectScore[PLAYER_RIGHT].top,rectBitmap[PLAYER_RIGHT].right,rectScore[PLAYER_RIGHT].bottom);//~0303I~
                                                                   //~9317I~
		rectScore[PLAYER_FACING]=new Rect(rectBitmap[PLAYER_FACING]);//~9317I~
        rectScore[PLAYER_FACING].top=rectScore[PLAYER_FACING].bottom;//~9317R~
        rectScore[PLAYER_FACING].bottom=rectScore[PLAYER_FACING].bottom+scoreH;//F//~9317I~
        rectScoreName[PLAYER_FACING]=new Rect(rectScore[PLAYER_FACING].left,rectBitmap[PLAYER_FACING].top,rectScore[PLAYER_FACING].right,rectScore[PLAYER_FACING].bottom);//~0303R~
                                                                   //~9317I~
		rectScore[PLAYER_LEFT]=new Rect(rectBitmap[PLAYER_LEFT]);   //~9317I~
        rectScore[PLAYER_LEFT].left=rectScore[PLAYER_LEFT].right;  //~9317R~
        rectScore[PLAYER_LEFT].right=rectScore[PLAYER_LEFT].right+scoreH;//L//~9317I~
        rectScoreName[PLAYER_LEFT]=new Rect(rectBitmap[PLAYER_LEFT].left,rectBitmap[PLAYER_LEFT].top,rectScore[PLAYER_LEFT].right,rectScore[PLAYER_LEFT].bottom);//~0303I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth rectScore="+rectScore[0].toString()+rectScore[1].toString()+rectScore[2].toString()+rectScore[3].toString());//~9611I~
        if (Dump.Y) Dump.println("NamePlate.adjustRectWidth rectScoreName="+rectScoreName[0].toString()+rectScoreName[1].toString()+rectScoreName[2].toString()+rectScoreName[3].toString());//~0303I~
      }                                                            //~9317I~
                                                                   //~9317I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void createBitmap()                                    //~v@@@I~
    {                                                              //~v@@@I~
    	int ww,hh,ww2,hh2,degree,strsz;                            //~v@@@R~
		Matrix matrix=new Matrix();                                       //~v@@@I~
        Bitmap bm;                                                 //~9317I~
        Canvas cc;
        String s;//~9317I~
    //******************************                               //~v@@@I~
        s="123456";        //6 DBCS                         //~9317I~
//      adjustTextSize(paintScore,s,TEXT_SIZE,textBoxW-TEXTBOX_MARGINH*2,textBoxH-TEXTBOX_MARGINH*2);//~9317I~//~9806R~
	  if (swNPLLand)                                               //~9806I~
        adjustTextSize(paintScore,s,TEXT_SIZE,textBoxW,textBoxH);  //~9806I~
      else                                                         //~9806I~
        adjustTextSize(paintScore,s,TEXT_SIZE,textBoxW,textBoxH-TEXTBOX_MARGINH*2);//~9806I~
        s="あいうえお";        //5 DBCS                            //~9317R~
//      nameH=adjustTextSize(paint,s,TEXT_SIZE,textBoxW-TEXTBOX_MARGINH*2,textBoxH-TEXTBOX_MARGINH*2);//~9317M~//~9319R~
        nameH=adjustTextSize(paint,s,TEXT_SIZE,textBoxW,textBoxH);          //~9319I~
        nameplateW=textBoxW;                                       //~9317R~
//      nameplateH=nameH+TEXTBOX_MARGINH*2+PLATE_EDGE_WIDTH*2;     //~9317R~//~9806R~
        nameplateH=nameH+TEXTBOX_MARGINH*2+PLATE_EDGE_WIDTH;       //~9806I~
        if (AG.portrait)                                            //~9410I~
	        scoreH=nameH*3/2;                                            //~9317M~//~9319R~//~9410R~
        else                                                       //~9410I~
      	if (!swNPL)                                                //~9611I~
	        scoreH=nameH*5/2;                                      //~9410R~
        else                                                       //~9611I~
//          scoreH=maxNPLhh-nameplateH-NPLAND_MARGIN_H*2;          //~9611R~//~9922R~
            scoreH=Math.min(maxNPLhh-nameplateH-NPLAND_MARGIN_H*2,nameH*3/2);//~9922I~
        if (Dump.Y) Dump.println("NamePlate.createBitmap swNPL="+swNPL+",scoreH="+scoreH+",nameH="+nameH+",plateW="+nameplateW+",plateH="+nameplateH);//~9317I~//~9611R~
                                                                   //~9317I~
    	bitmapPlate=new Bitmap[PLAYERS];                           //~v@@@I~
        memberName=AG.aAccounts.getAccountNames();                 //~0305I~
    	for (int ii=0;ii<PLAYERS;ii++)                                 //~v@@@I~
        {                                                          //~v@@@I~
        	String nm=memberName[ii];                              //~v@@@I~
        	if (Dump.Y) Dump.println("NamePlate.createBitmap ii="+ii+",name="+nm);//~v@21I~
          if (swVerticalEarth)                                     //~9317I~
          {                                                        //~9317I~
            Rect r=new Rect();                                     //~v@@@I~//~9317M~
    	    paint.getTextBounds(nm,0,nm.length(),r);                 //~v@@@I~
            boundsPlate[ii]=r;                                     //~v@@@I~
        	strsz=(int)paint.measureText(nm);                      //~v@@@I~
//          Bitmap bm=Bitmap.createBitmap(r.right-r.left+TEXT_MARGIN*2,r.bottom-r.top+TEXT_MARGIN*2,Bitmap.Config.ARGB_8888);//~v@@@R~
//          bm=Bitmap.createBitmap(strsz+TEXT_MARGIN*2,r.bottom-r.top+TEXT_MARGIN*2,Bitmap.Config.ARGB_8888);//~v@@@R~//~9317R~//~0216R~
            bm=Graphics.createBitmap(strsz+TEXT_MARGIN*2,r.bottom-r.top+TEXT_MARGIN*2,Bitmap.Config.ARGB_8888);//~0216I~
            ww=bm.getWidth();                                      //~v@@@M~
            hh=bm.getHeight();                                     //~v@@@M~
                                                                   //~v@@@I~
            cc=new Canvas(bm);                              //~v@@@M~//~9317R~
            Rect rectBG=new Rect(0,0,ww,hh);                       //~v@@@I~
//            Graphics.drawRect(true/*local*/,cc,rectBG,TEXT_COLOR_BG);//~v@@@R~//~v@21R~
//            Graphics.drawRect(true/*local*/,cc,rectBG,TEXT_COLOR_EDGE,PLATE_EDGE_WIDTH);//~v@@@R~//~v@21R~
//            Graphics.drawText(true/*local*/,cc,nm,TEXT_MARGIN,hh-r.bottom-TEXT_MARGIN,paint);//~v@@@R~//~v@21R~
            Graphics.drawRect(cc,rectBG,TEXT_COLOR_BG);            //~v@21I~
            Graphics.drawRect(cc,rectBG,TEXT_COLOR_EDGE,PLATE_EDGE_WIDTH);//~v@21I~
            Graphics.drawText(cc,nm,TEXT_MARGIN,hh-r.bottom-TEXT_MARGIN,paint);//~v@21I~
                                                                   //~v@@@I~
            hh2=textBoxH;                                           //~v@@@I~
            ww2=(int)(ww*((double)textBoxH/hh));                   //~v@@@R~
		    bm=Graphics.scaleBitmap(bm,ww2,hh2,true/*recycle*/);   //~v@@@I~
          }                                                        //~9317M~
          else                                                     //~9317M~
          {                                                        //~9317M~
//          bm=Bitmap.createBitmap(nameplateW,nameplateH,Bitmap.Config.ARGB_8888);//~9317R~//~0216R~
            bm=Graphics.createBitmap(nameplateW,nameplateH,Bitmap.Config.ARGB_8888);//~0216I~
            ww=bm.getWidth();                                      //~9317M~
            hh=bm.getHeight();                                     //~9317M~
        	if (Dump.Y) Dump.println("NamePlate.createBitmap ww="+ww+",hh="+hh);//~9317M~
            cc=new Canvas(bm);                                     //~9317M~
            Rect rectBG=new Rect(0,0,ww,hh);                       //~9317M~
            Graphics.drawRect(cc,rectBG,TEXT_COLOR_BG);            //~9317M~
//          rectBG.right-=PLATE_EDGE_WIDTH;                        //~9317R~
            rectScore[ii]=new Rect(rectPlate[ii]);                     //~9317R~
            Graphics.drawRect(cc,rectBG,TEXT_COLOR_EDGE,PLATE_EDGE_WIDTH);//~9317M~
//  	    paint.getTextBounds(nm,0,nm.length(),r);               //~9317R~
//          Graphics.drawText(cc,nm,TEXT_MARGIN_SCORE,r.bottom-r.top+TEXT_MARGIN_SCORE,paint);//~9317R~
//          Graphics.drawText(cc,nm,TEXTBOX_MARGINH+PLATE_EDGE_WIDTH,nameH,paint);//~9317R~
//          drawTextName(cc,"あいうえおかきくけこ"/*nm*/,rectBG,paint);  //TODO//~9317R~
            drawTextName(cc,nm,rectBG,paint);                      //~9317I~//~0322R~
//            Rect rectScore=new Rect(0,nameH,ww,nameplateH);      //~9317R~
//            Graphics.drawRect(cc,rectScore,TEXT_COLOR_SCORE);    //~9317R~
//            Graphics.drawRect(cc,rectScore,TEXT_COLOR_EDGE,PLATE_EDGE_WIDTH*2);//~9317R~
//            Graphics.drawText(cc,"123456",nameH,scoreH,paintScore); //TODO//~9317R~
            hh2=hh;                                                //~9317M~
            ww2=ww/2;                                              //~9317M~
          }                                                        //~9317M~
            switch(ii)                                             //~v@@@I~
            {                                                      //~v@@@I~
            case PLAYER_FACING:                                    //~v@@@I~
                degree=180;                                        //~v@@@I~
                break;                                             //~v@@@I~
            case PLAYER_RIGHT:                                     //~v@@@I~
                degree=270;                                        //~v@@@I~
                break;                                             //~v@@@I~
            case PLAYER_LEFT:                                      //~v@@@I~
                degree=90;                                         //~v@@@I~
                break;                                             //~v@@@I~
            default:	 //YOU                                     //~v@@@I~
                degree=0;                                          //~v@@@I~
            }                                                      //~v@@@I~
            if (degree!=0)                                         //~v@@@I~
            {                                                      //~v@@@I~
                matrix.setRotate(degree,ww2/2,hh2/2);              //~v@@@R~
                Bitmap bmr;                                        //~9317I~
	          if (swVerticalEarth)                                 //~9317I~
//              bmr=Bitmap.createBitmap(bm,0,0,ww2,hh2,matrix,true);//~v@@@R~//~9317R~//~0216R~
                bmr=Graphics.createBitmap(bm,0,0,ww2,hh2,matrix,true);//~0216I~
              else                                                 //~9317I~
//              bmr=Bitmap.createBitmap(bm,0,0,ww,hh,matrix,true); //~9317R~//~0216R~
                bmr=Graphics.createBitmap(bm,0,0,ww,hh,matrix,true);//~0216I~
                UView.recycle(bm);                                 //~v@21R~
                bm=bmr;                                            //~v@@@I~
            }                                                      //~v@@@I~
            bitmapPlate[ii]=bm;                                    //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~9317I~
    private void drawTextName(Canvas Pcanvas,String Pstr,Rect Prect,Paint Ppaint)//~9317I~
    {                                                              //~9317I~
    	Rect r=new Rect();                                         //~9317I~
    	Ppaint.getTextBounds(Pstr,0,Pstr.length(),r);                //~9317I~
        int yy=-r.top+PLATE_EDGE_WIDTH+TEXTBOX_MARGINH;            //~9317R~
        int xx=TEXTBOX_MARGINH+PLATE_EDGE_WIDTH;                   //~9317I~
        if (Dump.Y) Dump.println("NamePlate.drawText xx="+xx+",yy="+yy+",nameH="+nameH+",str="+Pstr+",bounds="+r.toString());//~9317I~
    	Graphics.drawText(Pcanvas,Pstr,xx,yy,Ppaint);          //~9317I~
    }                                                              //~9317I~
//    //*********************************************************    //~v@@@I~//~0217R~
//    public int setPlayerPosition(int PposEast,int[] PposMember)    //~v@@@R~//~0217R~
//    {                                                              //~v@@@I~//~0217R~
//        Matrix matrix=new Matrix();                                //~v@@@I~//~0217R~
//        int starter=0;                                             //~v@@@I~//~0217R~
//    //*************                                                //~v@@@I~//~0217R~
//        int eastPlayer=PposMember[0];                              //~v@@@I~//~0217R~
//        int yourpos=0;                                             //~v@@@I~//~0217R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~//~0217R~
//        {                                                          //~v@@@I~//~0217R~
//            int player=PposMember[ii];    //ii=0:East player       //~v@@@I~//~0217R~
//            if (player==0)                                         //~v@@@I~//~0217R~
//            {                                                      //~v@@@I~//~0217R~
//                yourpos=ii;                                        //~v@@@I~//~0217R~
//                break;                                             //~v@@@I~//~0217R~
//            }                                                      //~v@@@I~//~0217R~
//        }                                                          //~v@@@I~//~0217R~
//        int[] newPos=new int[PLAYERS];                             //~v@@@I~//~0217R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~//~0217R~
//        {                                                          //~v@@@I~//~0217R~
//            int dist=Players.playerRelative(ii,yourpos);            //~v@@@I~//~0217R~
//            if (ii==0)                                              //~v@@@I~//~0217R~
//                starter=dist;   //starter relative to me           //~v@@@I~//~0217R~
//            int player=PposMember[ii];    //ii=0:East player       //~v@@@I~//~0217R~
//            newPos[dist]=player;                                   //~v@@@I~//~0217R~
//            if (Dump.Y) Dump.println("NamePlate.setPlayerPosition player="+player+",name="+memberName[player]+",newpos="+dist+",yourpos="+yourpos+",ii="+ii);//~v@@@I~//~0217R~
//        }                                                          //~v@@@I~//~0217R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~//~0217R~
//        {                                                          //~v@@@I~//~0217R~
//            int player=newPos[ii];                                 //~v@@@I~//~0217R~
//            int dist=Players.playerRelative(ii,player);                                   //~v@@@I~//~0217R~
//            int degree=360-dist*90;                                //~v@@@I~//~0217R~
//            Bitmap bm=bitmapPlate[player];                       //~0217R~
//            if (degree!=0)                                         //~v@@@I~//~0217R~
//            {                                                      //~v@@@I~//~0217R~
//                int ww=bm.getWidth(); int hh=bm.getHeight();//~v@@@I~//~0217R~
//                matrix.setRotate(degree,ww/2,hh/2);              //~v@@@I~//~0217R~
////              Bitmap bmr=Bitmap.createBitmap(bm,0,0,ww,hh,matrix,true);//~v@@@I~//~0216R~//~0217R~
//                Bitmap bmr=Graphics.createBitmap(bm,0,0,ww,hh,matrix,true);//~0216I~//~0217R~
//                UView.recycle(bm);                                 //~v@21R~//~0217R~
//                bm=bmr;                                            //~v@@@I~//~0217R~
//            }                                                      //~v@@@I~//~0217R~
//            Rect rect=rectPlate[ii];                               //~v@@@I~//~0217R~
//            Rect rectbm=rectBitmap[ii];                            //~v@@@I~//~0217R~
//            int ww=bm.getWidth(); int hh=bm.getHeight();           //~v@@@I~//~0217R~
//            int xx,yy,center;                                      //~v@@@R~//~0217R~
//            switch(ii)                                             //~v@@@I~//~0217R~
//            {                                                      //~v@@@I~//~0217R~
//            case PLAYER_YOU:                                       //~v@@@I~//~0217R~
//            case PLAYER_FACING:                                    //~v@@@I~//~0217R~
//                center=(rect.left+rect.right)/2;                    //~v@@@I~//~0217R~
//                xx=center-ww/2; yy=rect.top;                       //~v@@@R~//~0217R~
//                break;                                             //~v@@@I~//~0217R~
//            default:    //left                                     //~v@@@I~//~0217R~
//                center=(rect.top+rect.bottom)/2;                    //~v@@@I~//~0217R~
//                yy=center-hh/2; xx=rect.left;                      //~v@@@R~//~0217R~
//            }                                                      //~v@@@I~//~0217R~
//            Graphics.drawRectBitmap(rect,COLOR_BG_TABLE,bm,xx,yy); //~v@@@R~//~0217R~
//        }                                                          //~v@@@I~//~0217R~
//        return starter;                                            //~v@@@I~//~0217R~
//    }                                                              //~v@@@I~//~0217R~
    //*********************************************************    //~v@@@I~
    //*shadow over nameplate                                       //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void gameStarted()                                      //~v@@@I~
    {                                                              //~v@@@I~
//        Matrix matrix=new Matrix();                                //~v@@@I~//~9318R~
//    //*************                                                //~v@@@I~//~9318R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~//~9318R~
//        {                                                          //~v@@@I~//~9318R~
//            Rect rect=rectPlate[ii];                               //~v@@@I~//~9318R~
//            Graphics.drawRect(rect,TEXT_COLOR_DISABLE);           //~v@@@I~//~9318R~
//        }                                                          //~v@@@I~//~9318R~
        if (Dump.Y) Dump.println("NamePlate.gameStarted");         //~9318I~
    }                                                              //~v@@@I~
    //*********************************************************    //~0303I~
    public void complete(boolean PswReset,int Pplayer)             //~0303I~
    {                                                              //~0303I~
        if (Dump.Y) Dump.println("NamePlate.complete swReset="+PswReset+",player="+Pplayer);//~0303I~
//        Rect rect=rectScoreName[Pplayer];                          //~0303R~//~0408R~
//        int color=PswReset ? TEXT_COLOR_EDGE : COMPLETE_COLOR;     //~0303I~//~0408R~
//        Graphics.drawRect(rect,color,PLATE_EDGE_WIDTH);            //~0303I~//~0408R~
        if (!PswReset)                                             //~0408I~
        {                                                          //~0408I~
        	Rect rect=rectScore[Pplayer];                          //~0408I~
        	int color=COMPLETE_COLOR_SCORE;                        //~0408I~
	        Graphics.drawRect(rect,color);                         //~0408I~
        }                                                          //~0408I~
    }                                                              //~0303I~
}//class NamePlate                                                 //~dataR~//~@@@@R~//~v@@@R~
