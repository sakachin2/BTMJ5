//*CID://+DATER~: update#= 591;                                    //~v@@@R~//~v@21R~//~9501R~
//**********************************************************************//~v101I~
//v@21  imageview                                                  //~v@21I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Paint;                                     //~v@@@M~
import android.graphics.Matrix;                                    //~v@@@M~
import android.graphics.Color;                                     //~v@@@M~

import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Status.*;
import static com.btmtest.StaticVars.AG;                           //~v@21I~

import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.Status;//~v@@@R~
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;

import java.util.Arrays;

public class Starter                                               //~v@@@R~
{                                                                  //~0914I~
    private static final int TEXT_SIZE=30;                         //~v@@@I~
    private static final int TEXT_COLOR=Color.argb(0xff,0xff,0xff,0xff); //white//~v@@@I~
	private static final int MARGINSEQX=5;                         //~v@@@I~
	private static final int MARGINTEXT=6;                         //~v@@@I~
    private static final int TEXT_BASELINE=4;                      //~9519I~
    private static final int ALPHA_CURRENT_STARTER=0xa0;           //~v@21R~//~9514R~//~9602R~
//  private static final int ALPHA_CURRENT_STARTER=0x80;           //~v@21R~//~9602R~
                                                                   //~v@@@I~
//    private Canvas canvas;                                         //~v@@@I~
    private MJTable table;                                         //~v@@@I~
//    private Pieces pieces;                                         //~v@@@I~
//  Bitmap[][] bmssStarter;                                        //~v@@@R~//~v@21R~
//  private int posStart;                                          //~v@@@I~//~v@21R~
    private int playerStarter;                                     //~v@@@I~
    private Rect rectStarter;                                      //~v@@@R~
    private Rect rectSeq,rectBounds;                               //~v@@@I~
    private Point pointSeq;                                        //~v@@@M~
    private Paint paintSeq;                                        //~v@@@M~
    private Canvas canvasTest;                                     //~v@@@M~
    private boolean swTest;                                        //~v@@@M~
    private int marginText,sizeText;                                        //~9809I~
//*************************                                        //~v@@@I~
	public Starter(GCanvas Pgcanvas)                               //~v@@@R~
    {                                                              //~0914I~
    	AG.aStarter=this;                                          //~v@@@R~
        table = AG.aMJTable;                                     //~v@@@I~
//        pieces=table.pieces;                                       //~v@@@I~
		marginText=MARGINTEXT;                                    //~9809I~
		sizeText=TEXT_SIZE;                                        //~9809I~
        if (AG.swSmallDevice)                                      //~9809I~
        {                                                          //~9809I~
        	marginText=(int)(marginText*AG.scaleSmallDevice);      //~9809I~
        	sizeText=(int)(sizeText*AG.scaleSmallDevice);          //~9809I~
        }                                                          //~9809I~
        if (Dump.Y) Dump.println("Starter.constructor marginText="+marginText+",sizeText="+sizeText);//~9809I~
    }
//    //*********************************************************    //~v@@@I~//~v@21R~
//    public void setStarter(int Pplayer)                            //~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Starter.drawStarter");           //~v@@@I~//~v@21R~
//        posStart=Pplayer;                                          //~v@@@I~//~v@21R~
//        drawStarter(posStart);                                     //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
    //*********************************************************    //~v@@@I~
    //*to show all starter ESWN                                    //~9513I~
    //*********************************************************    //~9513I~
    public void drawStarter()                                      //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Starter.drawStarter status="+Status.getGameStatus());//~v@@@R~
        if (Status.getGameStatus()==GS_SETUP)                          //~v@@@I~
        	return;                                                //~v@@@I~
//      canvas=gcanvas.canvas;                                     //~v@@@M~//~v@21R~
//      Bitmap[][] bmss=Pieces.bitmapStarter;                      //~v@@@I~//~0216R~
        Bitmap[][] bmss=AG.bitmapStarter;                          //~0216I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
            Point p=table.getStarterPos(ii);                       //~v@@@I~
            int xx=p.x;                                            //~v@@@I~
            int yy=p.y;                                            //~v@@@I~
            Bitmap bm=bmss[ii][ii];                                //~v@@@I~
//          Graphics.drawBitmap(canvas,bm,xx,yy);                               //~v@@@I~//~v@21R~
            Graphics.drawBitmap(bm,xx,yy);                         //~v@21I~
        }                                                          //~v@@@I~
//      drawBird(true/*swInit*/);                                                //~v@21I~//~9501R~
    }                                                              //~v@@@I~
    //*********************************************************    //~9611I~
    public Rect[] getStarterRect()                                 //~9611I~
    {                                                              //~9611I~
        if (Dump.Y) Dump.println("Starter.getStarterRect");        //~9611I~
//      Bitmap[][] bmss=Pieces.bitmapStarter;                      //~9611I~//~0216R~
        Bitmap[][] bmss=AG.bitmapStarter;                          //~0216I~
        Rect[] rs=new Rect[PLAYERS];                               //~9611I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9611I~
        {                                                          //~9611I~
            Point p=table.getStarterPos(ii);                       //~9611I~
            Bitmap bm=bmss[ii][ii];                                //~9611I~
            rs[ii]=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~9611I~
	        if (Dump.Y) Dump.println("Starter.getStarterRect ii="+ii+",rect="+rs[ii].toString());//~9611I~
        }                                                          //~9611I~
        return rs;                                                 //~9611I~
    }                                                              //~9611I~
    //*********************************************************    //~v@21I~
    private void drawBird(boolean PswInit)                                         //~v@21I~//~9501R~
    {                                                              //~v@21I~
    	boolean swBird=AG.aAccounts.isGrillBird();                 //~9501I~
        if (Dump.Y) Dump.println("Starter.drawBird swInit="+PswInit+",swBird="+swBird);//~9501I~
        if (!swBird)                                               //~9501I~
        	return;                                                //~9501I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@21I~
        {                                                          //~v@21I~
            drawBird(PswInit,ii);                                          //~v@21R~//~9501R~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~9501I~
    //*from Accounts.setGrilled                                    //~9519I~
    //*********************************************************    //~9519I~
    public void drawBird(int PidxMember,boolean PswReset)          //~9501I~
    {                                                              //~9501I~
        if (Dump.Y) Dump.println("Starter.drawBird idxMember="+PidxMember+",swReset="+PswReset);//~9501I~
        if (PswReset)                                              //~9501I~
        {                                                          //~9501I~
	    	if (AG.aAccounts.isGrillBird())                        //~9501I~
            {                                                      //~9501I~
            	int eswn=AG.aAccounts.idxToCurrentEswn(PidxMember); //~9501I~
            	int player=Accounts.eswnToPlayer(eswn);        //~9501I~
			    drawBird(false/*PswInit*/,player);                 //~9501I~
            }                                                      //~9501I~
        }                                                          //~9501I~
    }                                                              //~9501I~
    //*********************************************************    //~v@21I~
    private void drawBird(boolean PswInit, int Pplayer)                              //~v@21I~//~9501R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("Starter.drawBird player="+Pplayer);//~v@21R~//~9501R~
//      Bitmap[] bmss=Pieces.bitmapBird;                           //~v@21I~//~0216R~
        Bitmap[] bmss=AG.bitmapBird;                               //~0216I~
        Bitmap bm=bmss[Pplayer];                                   //~v@21M~
        int xx=table.birdX[Pplayer];                               //~v@21I~
        int yy=table.birdY[Pplayer];                               //~v@21I~
    	boolean swBird=AG.aAccounts.isGrillBird(Pplayer);                  //~v@21I~
        if (Dump.Y) Dump.println("Starter.drawBird swBird="+swBird+",xx="+xx+",yy="+yy);//~v@21I~
        if (swBird)                                                //~v@21I~
        {                                                          //~9501I~
        	if (PswInit)                                           //~9501I~
		        Graphics.drawBitmap(bm,xx,yy);                         //~v@21R~//~9501R~
        }                                                          //~9501I~
        else                                                       //~v@21I~
        {                                                          //~v@21I~
        	Rect r=new Rect(xx,yy,xx+bm.getWidth(),yy+bm.getHeight());//~v@21I~
	        Graphics.drawRect(r,COLOR_BG_TABLE); //bg clear required//~v@21I~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~9519I~
    //*from Accounts.setCtrContinuedGain                           //~9519I~
    //*********************************************************    //~9519I~
    public void showCtrContinuedGain(int[] Pctrs/*position seq*/)  //~9519I~
    {                                                              //~9519I~
        if (Dump.Y) Dump.println("Starter.showCtrContinuedGain ctrs="+Arrays.toString(Pctrs));//~9519I~
//      Bitmap[] bmss=Pieces.bitmapBird;                           //~9519I~//~0216R~
        Bitmap[] bmss=AG.bitmapBird;                               //~0216I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~9519I~
        {                                                          //~9519I~
        	Bitmap bm=bmss[ii];                                    //~9519I~
	        int xx=table.birdX[ii];                                //~9519I~
        	int yy=table.birdY[ii];                                //~9519I~
            int idx=AG.aAccounts.playerToMember(ii);                   //~9519I~
            int pos=AG.aAccounts.playerToPosition(ii);                 //~9519I~
    		boolean swBird=AG.aAccounts.isGrillBird(ii);           //~9519R~
	        if (Dump.Y) Dump.println("Starter.showCtrContinuedGain ii="+ii+",swBird="+swBird+",idx="+idx+",pos="+pos+",ctr="+Pctrs[pos]+",xx="+xx+",yy="+yy);//~9519R~
        	if (!swBird)                                           //~9519I~
        	{                                                      //~9519I~
        		Rect r=new Rect(xx,yy,xx+bm.getWidth(),yy+bm.getHeight());//~9519I~
	        	Graphics.drawRect(r,COLOR_BG_TABLE); //bg clear required//~9519I~
                if (Pctrs[pos]!=0)                                 //~9519I~
                	drawCtrContinuedGain(ii,r,Pctrs[pos]);         //~9519I~
        	}                                                      //~9519I~
        }                                                          //~9519I~
    }                                                              //~9519I~
    //*********************************************************    //~v@@@I~
    public void drawStarter(int Pplayer)                           //~v@@@I~
    {                                                              //~v@@@I~
        Point p=table.getStarterPos(Pplayer);                      //~v@@@I~
//      Bitmap[][] bmss=Pieces.bitmapStarter;                      //~v@@@I~//~0216R~
        Bitmap[][] bmss=AG.bitmapStarter;                          //~0216I~
        Rect gamectr=Status.getGameSeq();                          //~v@@@R~
        int setCtr=gamectr.left;                                   //~v@@@I~
        if (Dump.Y) Dump.println("Starter.drawStarter player="+Pplayer+",setCtr="+setCtr);//~v@@@I~//~9513I~
//      Bitmap bm=bmss[setCtr][Pplayer];                           //~v@@@R~//~9513R~
        int eswn=getStarterMark(setCtr);                           //~9513R~
        Bitmap bm=bmss[eswn][Pplayer];                             //~9513I~
        Rect rect=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@@@R~
		Graphics.drawBitmap(rect,bm,p.x,p.y);                      //~v@@@I~
        playerStarter=Pplayer;                                     //~v@@@I~
	    rectStarter=rect;                                          //~v@@@I~
//      AG.aDiceBox.setWaitingDice(Pplayer,true/*set starterID*/);   //waiting lamp//~v@@@I~//~9514R~
    }                                                              //~v@@@I~
    //*********************************************************    //~9513I~
    private int getStarterMark(int PsetCtr)                        //~9513R~
    {                                                              //~9513I~
    	int eswn=PsetCtr;                                          //~9513I~
        if (Dump.Y) Dump.println("Starter.getStarterMark round setCtr="+PsetCtr+",eswn="+eswn);//~9513R~
        return eswn;                                               //~9513I~
    }                                                              //~9513I~
    //*********************************************************    //~9513I~
    private int getStarterMark()                                   //~9513I~
    {                                                              //~9513I~
        Rect gamectr=Status.getGameSeq();                          //~9513I~
        return getStarterMark(gamectr.left);                       //~9513I~
    }                                                              //~9513I~
    //*********************************************************    //~v@@@I~
    public void moveStarter(int Pplayer)                           //~v@@@I~
    {                                                              //~v@@@I~
//      Bitmap[][] bmss=Pieces.bitmapStarter;                      //~v@@@I~//~0216R~
        Bitmap[][] bmss=AG.bitmapStarter;                          //~0216I~
        Bitmap bm;                                                 //~v@@@I~
        Rect rect;                                                 //~v@@@I~
        Point p;                                                   //~v@@@I~
    //*****************                                            //~v@@@I~
        if (Dump.Y) Dump.println("Starter.moveStarter player="+Pplayer+",old="+playerStarter);//~v@@@I~
        if (Pplayer!=playerStarter)                                //~v@@@I~
        {                                                          //~v@@@I~
        	p=table.getStarterPos(playerStarter);                  //~v@@@I~
        	bm=bmss[0/*east*/][playerStarter];                     //~v@@@I~
        	rect=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@@@I~
			Graphics.drawRect(rect,COLOR_BG_TABLE);                //~v@@@I~
                                                                   //~v@@@I~
        	p=table.getStarterPos(Pplayer);                        //~v@@@I~
        	bm=bmss[0/*east*/][Pplayer];                           //~v@@@I~
        	rect=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@@@I~
			Graphics.drawRectBitmap(rect,COLOR_BG_TABLE,bm,p.x,p.y);//~v@@@I~
            playerStarter=Pplayer;                                //~v@@@I~
	        rectStarter=rect;                                      //~v@@@I~
        }                                                          //~v@@@I~
        drawBird(true/*swInit*/);                                                //~v@21I~//~9501R~
    }                                                              //~v@@@I~
    //*********************************************************    //~9602I~
    public  void drawCurrentStarter(int Pplayer/*currentStarter relative pos*/)//~9602R~
    {                                                              //~9602I~
	    int pos1st=AG.aAccounts.starterRelativePos;	//first starter Position(=player)//~9602I~
        if (Dump.Y) Dump.println("Starter.drawCurrentStarter player="+Pplayer+",pos1st="+pos1st);//~9602I~
        if (Pplayer==pos1st)                                       //~9602I~
        	drawStarter(pos1st);                                   //~9602I~
        else                                                       //~9602I~
			draw1stStarterMarkNot1st(pos1st);                      //~9602I~
    }                                                              //~9602I~
    //*********************************************************    //~9602I~
    private void draw1stStarterMarkNot1st(int Ppos)                //~9602I~
    {                                                              //~9602I~
        if (Dump.Y) Dump.println("Starter.draw1stStarterMarkNot1st pos="+Ppos);//~9602I~
//      Bitmap[][] bmss=Pieces.bitmapStarter;                      //~9602I~//~0216R~
        Bitmap[][] bmss=AG.bitmapStarter;                          //~0216I~
        Point p=table.getStarterPos(Ppos);                               //~9602I~
        Rect gamectr=Status.getGameSeq();                          //~9602I~
        int setCtr=gamectr.left;                                   //~9602I~
        int eswn=getStarterMark(setCtr);                           //~9602I~
        Bitmap bm=bmss[eswn][Ppos];                                //~9602R~
        Rect rect=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~9602I~
        Graphics.drawRect(rect,COLOR_BG_TABLE);  //erase once      //~9602I~
        Graphics.drawBitmapAlpha(bm,p.x,p.y,ALPHA_CURRENT_STARTER);//shadow on 1st starter mark from 2nd game//~9602I~
    }                                                              //~9602I~
//    //*********************************************************    //~v@21I~//~9514R~
//    private void drawCurrentStarterMark(int Pplayer)               //~v@21R~//~9514R~
//    {                                                              //~v@21I~//~9514R~
//        Bitmap[][] bmss=Pieces.bitmapStarter;                      //~v@21I~//~9514R~
//        Bitmap bm;                                                 //~v@21I~//~9514R~
//        Rect rect;                                                 //~v@21I~//~9514R~
//        Point p;                                                   //~v@21I~//~9514R~
//    //*****************                                            //~v@21I~//~9514R~
//        int starter1st=AG.aAccounts.starterRelativePos;            //~v@21I~//~9514R~
//        int prevPlayer=Players.prevPlayer(Pplayer);                //~v@21I~//~9514R~
//        if (Dump.Y) Dump.println("Starter.drawCurrentStarterMark Pplayer="+Pplayer+",starter1st="+starter1st+",prevPlayer="+prevPlayer);//~v@21I~//~9514R~
//        if (prevPlayer!=starter1st) //erase previous not 1st starter mark//~9513R~//~9514R~
//        {                                                          //~v@21I~//~9514R~
//            p=table.getStarterPos(prevPlayer);                     //~v@21I~//~9514R~
//            bm=bmss[0/*east*/][prevPlayer];                        //~v@21I~//~9514R~
//            rect=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@21I~//~9514R~
//            Graphics.drawRect(rect,COLOR_BG_TABLE);  //erase starter mark of dealer not 1st//~v@21R~//~9514R~
//        }                                                          //~v@21I~//~9514R~
//        if (Pplayer!=starter1st)    //shadow 1st startr,small current starter//~9513R~//~9514R~
//        {                                                          //~v@21I~//~9514R~
//            p=table.getStarterPos(Pplayer);                        //~v@21I~//~9514R~
//            bm=bmss[0/*east*/][Pplayer];                           //~9513R~//~9514R~
//            rect=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@21I~//~9514R~
////          Graphics.drawBitmapAlpha(bm,p.x,p.y,ALPHA_CURRENT_STARTER);//shadow on 1st starter mark//~v@21R~//~9514R~
//            draw1stStarterMarkNot1st(bmss,starter1st);//shadow on 1st starter mark//~v@21R~//~9514R~
//            drawCurrentStarterMark(rect,bm);//shdow on 1st starter mark//~v@21I~//~9514R~
//        }                                                          //~v@21I~//~9514R~
//        else                                                       //~9513I~//~9514R~
//        {                                                          //~9513I~//~9514R~
//            drawStarter(Pplayer);    //for nextgame         //~9513I~//~9514R~
////          AG.aDiceBox.setWaitingDice(Pplayer,true/*set starterID*/);   //waiting lamp//~9514R~
//        }                                                          //~9513I~//~9514R~
//    }                                                              //~v@21I~//~9514R~
//    //*********************************************************    //~v@21I~//~9514R~
//    private void draw1stStarterMarkNot1st(Bitmap[][] Pbmss,int Ppos)//~v@21R~//~9514R~
//    {                                                              //~v@21I~//~9514R~
//        if (Dump.Y) Dump.println("Starter.draw1stStarterMarkNot1st pos="+Ppos);//~v@21R~//~9514R~
////      Bitmap bm=Pbmss[0/*east*/][Ppos];                           //~v@21I~//~9513R~//~9514R~
//        int eswn=getStarterMark();                                  //~9513I~//~9514R~
//        Bitmap bm=Pbmss[eswn][Ppos];                               //~9513I~//~9514R~
//        Point p=table.getStarterPos(Ppos);                         //~v@21I~//~9514R~
//        Rect rect=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@21I~//~9514R~
//        Graphics.drawRect(rect,COLOR_BG_TABLE);  //erase once      //~v@21R~//~9514R~
//        Graphics.drawBitmapAlpha(bm,p.x,p.y,ALPHA_CURRENT_STARTER);//shadow on 1st starter mark from 2nd game//~v@21R~//~9514R~
//    }                                                              //~v@21I~//~9514R~
//    //*********************************************************    //~v@21I~//~9514R~
//    private void drawCurrentStarterMark(Rect Prect,Bitmap Pbm)     //~v@21I~//~9514R~
//    {                                                              //~v@21I~//~9514R~
//        int ww=Prect.right-Prect.left;                             //~v@21I~//~9514R~
//        int hh=Prect.bottom-Prect.top;                             //~v@21I~//~9514R~
//        if (Dump.Y) Dump.println("Starter.drawCurrentStarterMark rect="+Prect.toString()+",ww="+ww+",hh="+hh);//~v@21I~//~9514R~
//        Bitmap bm2=Pieces.scaleImage(Pbm,ww*3/4,hh*3/4);           //~v@21R~//~9514R~
//        Graphics.drawBitmap(bm2,Prect.left+ww/8,Prect.top+hh/8);    //show half scale startermark//~v@21R~//~9514R~
//        UView.recycle(bm2);                                        //~v@21I~//~9514R~
//    }                                                              //~v@21I~//~9514R~
//    //*********************************************************    //~v@@@I~//~v@21R~
//    public void showCompletion(int Pplayer)                        //~v@@@R~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("Starter.showCompletion");             //~v@@@R~//~v@21R~
//        bmssStarter=pieces.bitmapStarter;                          //~v@@@R~//~v@21R~
//        Bitmap bm=bmssStarter[PIECE_COINS_REACH][Pplayer];         //~v@@@R~//~v@21R~
//        Point p=table.getStarterPos(Pplayer);                      //~v@@@R~//~v@21R~
//        Rect rect=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@@@I~//~v@21R~
//        int xx=p.x;                                                //~v@@@I~//~v@21R~
//        int yy=p.y;                                                //~v@@@I~//~v@21R~
//        Graphics.drawBitmap(rect,bm,xx,yy);                        //~v@@@I~//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
//    //*********************************************************    //~v@@@M~//~0322R~
//    public void showGameSeqTest(Canvas Pcanvas) //TODO test        //~v@@@M~//~0322R~
//    {                                                              //~v@@@M~//~0322R~
////        int set,game,dup,posMark;                                  //~v@@@M~//~0322R~
//    //************************                                     //~v@@@M~//~0322R~
//        if (Dump.Y) Dump.println("Starter.showGameSeq");           //~v@@@R~//~0322R~
//        swTest=true;                                               //~v@@@M~//~0322R~
//        canvasTest=Pcanvas;                                        //~v@@@M~//~0322R~
//        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@M~//~0322R~
//        {                                                          //~v@@@M~//~0322R~
//            showGameSeq(ii);                                       //~v@@@M~//~0322R~
//        }                                                          //~v@@@M~//~0322R~
//        swTest=false;                                              //~v@@@M~//~0322R~
//    }                                                              //~v@@@M~//~0322R~
    //*********************************************************    //~v@@@M~
    public void showGameSeq(int Ppos)                              //~v@@@M~
    {                                                              //~v@@@M~
        int game,dup,posMark,reach;                                  //~v@@@M~
//      Bitmap[][] bmss=Pieces.bitmapStarter;                      //~v@@@I~//~0216R~
        Bitmap[][] bmss=AG.bitmapStarter;                          //~0216I~
        //************************                                     //~v@@@M~
        if (Dump.Y) Dump.println("Starter.showGameSeq pos="+Ppos);           //~v@@@R~//~9902R~
        Rect gs=Status.getGameSeq();                               //~v@@@M~
//      set=gs.left; game=gs.top; dup=gs.right;                    //~v@@@M~//~v@21R~
        game=gs.top; dup=gs.right;                                 //~v@21I~
        reach=gs.bottom;                                           //~9506I~
//        posMark=AG.aGC.playerStarter;                            //~v@@@M~
        posMark=Ppos;                                              //~v@@@M~
        getPointSeq(posMark);                                      //~v@@@M~
//      String strSeq=Utils.getStr(R.string.Info_GameSeq,game+1);  //~v@@@M~//~v@21R~
//      String strSeq=GConst.gameSeq[game];                     //~v@21I~//~9513R~
        String strSeq=Status.getStringGameRound();                 //~9513R~
        getRectSeq(posMark,strSeq);                                //~v@@@M~
        Bitmap bm=getBitmapSeq(posMark,strSeq);                    //~v@@@M~
        if (swTest)                                                //~v@@@M~
	        Graphics.drawBitmap(canvasTest,bm,rectSeq.left,rectSeq.top);//~v@@@M~
        else                                                       //~v@@@M~
	        Graphics.drawBitmap(rectSeq,bm);                       //~v@@@M~
        UView.recycle(bm);                                         //~0217I~
        AG.aDiceBox.drawSplitPosIDStarter(posMark,rectSeq);        //~2327I~
//        if (dup!=0)                                              //~v@@@R~
//        {                                                        //~v@@@R~
        	int posRelative=Players.nextPlayer(posMark,game);      //~v@@@R~
        	Point p=table.getStarterPos(posRelative);              //~v@@@I~
        	bm=bmss[0/*east*/][posRelative];                       //~v@@@I~
        	Rect rect=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@@@I~
        	AG.aPointStick.showStickDup(posRelative,rect,dup);     //~v@@@R~
//        }                                                        //~v@@@R~
        AG.aPointStick.showReachContinued(rect,posRelative,reach);          //~9506I~//~9510R~
//  	AG.aDiceBox.drawCurrentStarterMark(game);                  //~v@21R~
//      drawCurrentStarterMark(AG.aAccounts.getCurrentStarterPos());//~v@21R~//~9514R~
//      drawStarter(AG.aAccounts.getCurrentStarterPos());          //~9514R~
//  	drawCurrentStarterMark(Players.nextPlayer(AG.aAccounts.getCurrentStarterPos()));//Test//~v@21R~
//      UView.recycle(bm);                                         //~0217R~
    }                                                              //~v@@@M~
    //*********************************************************    //~v@@@M~
    private void getPointSeq(int Ppos)                             //~v@@@M~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("Starter.getPointSeq pos="+Ppos); //~9902I~
    	if (pointSeq!=null)                                        //~v@@@M~
        	return;                                                //~v@@@M~
        Rect r=rectStarter;                                        //~v@@@I~
        Point p;                                                   //~v@@@M~
//        Rect ro;                                                   //~v@@@M~
        int m=MARGINSEQX;                                          //~v@@@M~
        switch(Ppos)                                               //~v@@@M~
        {                                                          //~v@@@M~
        case PLAYER_YOU:                                           //~v@@@M~
            p=new Point (r.right+m, r.top);                        //~v@@@M~
            break;                                                 //~v@@@M~
        case PLAYER_FACING:                                        //~v@@@M~
            p=new Point (r.left-m, r.bottom);                      //~v@@@M~
            break;                                                 //~v@@@M~
        case PLAYER_RIGHT:                                         //~v@@@M~
            p=new Point (r.left, r.top-m);                         //~v@@@M~
            break;                                                 //~v@@@M~
        default:     //left                                        //~v@@@M~
            p=new Point (r.right, r.bottom+m);                     //~v@@@M~
        }                                                          //~v@@@M~
        pointSeq=p;                                                //~v@@@M~
        if (Dump.Y) Dump.println("Starter.getPointSeq pos="+Ppos+",x="+p.x+",y="+p.y);//~v@@@R~
    }                                                              //~v@@@M~
    //*********************************************************    //~v@@@M~
    private Paint setPaintSeq()                                    //~v@@@M~
    {                                                              //~v@@@M~
        Paint p=new Paint();                                       //~v@@@M~
        p.setAntiAlias(true);                                      //~v@@@M~
        p.setColor(TEXT_COLOR);                                    //~v@@@M~
//      p.setTextSize(TEXT_SIZE);                                  //~v@@@M~//~9809R~
        p.setTextSize(sizeText);                                   //~9809I~
        return p;                                                  //~v@@@M~
    }                                                              //~v@@@M~
    //*********************************************************    //~v@@@M~
    private void getRectSeq(int Ppos,String PstrSeq)               //~v@@@M~
    {                                                              //~v@@@M~
    	paintSeq=setPaintSeq();                                    //~v@@@M~
        Rect rb=new Rect();                                        //~v@@@M~
    	paintSeq.getTextBounds(PstrSeq,0,PstrSeq.length(),rb);     //~v@@@M~
        rectBounds=rb;                                             //~v@@@M~
//      int m=MARGINTEXT;                                          //~v@@@M~//~9809R~
        int m=marginText;                                          //~9809I~
        int hh=rb.bottom-rb.top+m;                                 //~v@@@M~
        int ww=rb.right-rb.left+m*2;                               //~v@@@M~
        if (Dump.Y) Dump.println("Starter.getRectSeq Bound  l="+rb.left+",t="+rb.top+",r="+rb.right+",b="+rb.bottom+",ww="+ww+",hh="+hh);//~v@@@R~
        Point p=pointSeq;                                          //~v@@@M~
        Rect ro;                                                   //~v@@@M~
        switch(Ppos)                                               //~v@@@M~
        {                                                          //~v@@@M~
        case PLAYER_YOU:                                           //~v@@@M~
            ro=new Rect(p.x, p.y, p.x+ww, p.y+hh);                 //~v@@@M~
            break;                                                 //~v@@@M~
        case PLAYER_FACING:                                        //~v@@@M~
            ro=new Rect(p.x-ww, p.y-hh, p.x, p.y);                 //~v@@@M~
            break;                                                 //~v@@@M~
        case PLAYER_RIGHT:                                         //~v@@@M~
            ro=new Rect(p.x, p.y-ww, p.x+hh, p.y);                 //~v@@@M~
            break;                                                 //~v@@@M~
        default:     //Left                                        //~v@@@M~
            ro=new Rect(p.x-hh, p.y, p.x, p.y+ww);                 //~v@@@M~
            break;                                                 //~v@@@M~
        }                                                          //~v@@@M~
        if (Dump.Y) Dump.println("Starter.getRectSeq pos="+Ppos+",str="+PstrSeq+",l="+ro.left+",t="+ro.top+",r="+ro.right+",b="+ro.bottom);//~v@@@R~
        rectSeq=ro;                                                //~v@@@M~
    }                                                              //~v@@@M~
    //*********************************************************    //~v@@@M~
    private Bitmap getBitmapSeq(int Ppos,String Pstrseq)           //~v@@@M~
    {                                                              //~v@@@M~
    	int ww,hh,degree;                                          //~v@@@M~
		Matrix matrix=new Matrix();                                //~v@@@M~
    //******************************                               //~v@@@M~
    	Rect r=rectBounds;                                         //~v@@@M~
        if (Dump.Y) Dump.println("Starter.getBitmapSeq pos="+Ppos+",strseq="+Pstrseq+",rectBounds="+r.toString());//~9809I~//+2327R~
//      int m=MARGINTEXT;                                          //~v@@@M~//~9809R~
        int m=marginText;                                          //~9809I~
//      Bitmap bm=Bitmap.createBitmap(strsz+TEXT_MARGIN*2,r.bottom-r.top+TEXT_MARGIN*2,Bitmap.Config.ARGB_8888);//~v@@@M~
//      Bitmap bm=Bitmap.createBitmap(r.right-r.left+m*2,r.bottom-r.top+m,Bitmap.Config.ARGB_8888);//~v@@@M~//~0216R~
        Bitmap bm=Graphics.createBitmap(r.right-r.left+m*2,r.bottom-r.top+m,Bitmap.Config.ARGB_8888);//~0216I~
        ww=bm.getWidth();                                          //~v@@@M~
        hh=bm.getHeight();                                         //~v@@@M~
        if (Dump.Y) Dump.println("Starter.getBitmapSeq bitmap ww="+ww+",hh="+hh);//~9809I~
        Canvas cc=new Canvas(bm);                                  //~v@@@M~
        Rect rectBG=new Rect(0,0,ww,hh);                           //~v@@@M~
//      Graphics.drawRect(true/*local*/,cc,rectBG,COLOR_BG_TABLE); //~v@@@R~//~v@21R~
//      Graphics.drawText(true/*local*/,cc,Pstrseq,m,hh-r.bottom,paintSeq);//~v@@@R~//~v@21R~
        Graphics.drawRect(cc,rectBG,COLOR_BG_TABLE);               //~v@21I~
        Graphics.drawText(cc,Pstrseq,m,hh-r.bottom,paintSeq);      //~v@21I~
        switch(Ppos)                                               //~v@@@M~
        {                                                          //~v@@@M~
        case PLAYER_FACING:                                        //~v@@@M~
            degree=180;                                            //~v@@@M~
            break;                                                 //~v@@@M~
        case PLAYER_RIGHT:                                         //~v@@@M~
            degree=270;                                            //~v@@@M~
            break;                                                 //~v@@@M~
        case PLAYER_LEFT:                                          //~v@@@M~
            degree=90;                                             //~v@@@M~
            break;                                                 //~v@@@M~
        default:     //YOU                                         //~v@@@M~
            degree=0;                                              //~v@@@M~
        }                                                          //~v@@@M~
        if (degree!=0)                                             //~v@@@M~
        {                                                          //~v@@@M~
            matrix.setRotate(degree,ww/2,hh/2);                    //~v@@@M~
//          Bitmap bmr=Bitmap.createBitmap(bm,0,0,ww,hh,matrix,true);//~v@@@M~//~0216R~
            Bitmap bmr=Graphics.createBitmap(bm,0,0,ww,hh,matrix,true);//~0216I~
            UView.recycle(bm);                                     //~v@21R~
            bm=bmr;                                                //~v@@@M~
        }                                                          //~v@@@M~
        return bm;                                                 //~v@@@M~
    }                                                              //~v@@@M~
    //*********************************************************    //~9519I~
    private void drawCtrContinuedGain(int Pplayer,Rect Prect,int Pctr)//~9519I~
    {                                                              //~9519I~
    //************************                                     //~9519I~
        if (Dump.Y) Dump.println("Starter.drawCtrContinuedGain player="+Pplayer+",rect="+Prect.toString()+",ctr="+Pctr);//~9519I~
        int ww=Prect.right-Prect.left;                             //~9519I~
        int hh=Prect.bottom-Prect.top;                             //~9519I~
        int hhh=Math.min(ww,hh);                                   //~9519I~
        int www=Math.max(ww,hh);                                   //~9519I~
		Paint paintCtr=new Paint();                                //~9519I~
        paintCtr.setColor(TEXT_COLOR);                             //~9519I~
        paintCtr.setAntiAlias(true);                               //~9519I~
        paintCtr.setTextSize(hhh);                                 //~9519I~
//      Bitmap bm=Bitmap.createBitmap(www,hhh,Bitmap.Config.ARGB_8888);//~9519I~//~0216R~
        Bitmap bm=Graphics.createBitmap(www,hhh,Bitmap.Config.ARGB_8888);//~0216I~
        Canvas cc=new Canvas(bm);                                  //~9519I~
        Graphics.drawText(cc,Integer.toString(Pctr),0,hhh-TEXT_BASELINE,paintCtr);//~9519I~
		Bitmap  bmr=PointStick.rotateByPosition(bm,Pplayer);       //~9519I~
        if (bm!=bmr)                                               //~9519I~
	        UView.recycle(bm);                                     //~9519I~
        Graphics.drawBitmap(Prect,bmr);                            //~9519I~
        UView.recycle(bmr);                                        //~9519I~
    }                                                              //~9519I~
}//class Starter                                                 //~dataR~//~@@@@R~//~v@@@R~
