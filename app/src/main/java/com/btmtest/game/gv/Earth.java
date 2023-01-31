//*CID://+vavpR~: update#= 716;                                    //~vavpR~
//**********************************************************************//~v101I~
//2023/01/28 vavp size of piece on earth for landscape. It can be enlarge more.//~vavpI~
//2022/04/07 vamh Animation. for Pon/Chii/Kan                      //~vamhI~
//2021/11/08 vag9 Ankan on Earth;display Red5 if option active(it may be disappear by facedown tile)//~vag9I~
//2021/02/01 va65 testoption of open hand for discardSmart test    //~va65I~
//**********************************************************************//~va65I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.btmtest.TestOption;
import com.btmtest.game.Players;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.TestOption.*;
import static com.btmtest.game.TileData.*;                         //~v@@@R~
import static com.btmtest.game.GConst.*;                           //~v@@@R~
import static com.btmtest.game.gv.MJTable.*;//~v@@@I~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~

public class Earth                                                 //~v@@@R~
{                                                                  //~0914I~
	public  static final int PAIR_SPACING=5;                       //~v@@@R~
    private static final int MARGIN_PAIR_START=10;                                                               //~v@@@I~
    public  static final double RATE_ADD_KAN=0.5;                  //~v@@@R~
//  private static final int COLOR_KAN_ADD_UNDER=Color.argb(0x80,0x80,0x80,0x80);//~v@@@R~
    private static final int COMPLETE_COLOR_KAN_ADD=Color.argb(0xff,0xff,0x00,0x00);//~v@@@I~
    private static final int COMPLETE_COLOR_KAN_TAKEN=Color.argb(0xff,0xff,0x00,0x00);//~v@@@I~
//  private static final int COMPLETE_STROKE_WIDTH_ADD_KAN=4;      //~v@@@R~
    private static final double MAX_EARCH_RATE=0.75;    // 3/4     //+vavpI~
	private GCanvas gcanvas;                                       //~v@@@I~
    private MJTable table;                                         //~v@@@I~
    private Pieces pieces;//~v@@@I~
//  private Canvas canvas;                                         //~v@@@R~
    public  int piecePairW,piecePairH;                             //~v@@@R~
    private Rect[] earthRect;                                      //~v@@@I~
    private int bgColor;                                           //~v@@@I~
    private int HH,WW;	//scrWidth,scrHeight                       //~v@@@I~
    public Rect rectPairYou;	//right boudary of Hands when taken from river//~v@@@R~
    private boolean[] swVerticalEarths;                            //~v@@@I~
    private boolean swVerticalEarth;                               //~v@@@I~
    private Players players;                                       //~v@@@I~
    private Rect[] openRects;                                      //~v@@@R~
    private int pairRightBottom,pairFacingRight,pairLeftTop;	//boundary of drawOpen//~v@@@I~
    private Rect[] rectStock;                                      //~v@@@I~
    private Hands hands;                                           //~v@@@I~
    private Bitmap bitmapScaledAddKan;                             //~v@@@I~
    private Rect rectScaledAddKan;                                 //~v@@@I~
    private Rect lastRectPair;                                     //~v@@@I~
    private Rect[][] savedRectPair=new Rect[PLAYERS][PAIRS_MAX];   //~v@@@I~
    private int[] savedPairCtr=new int[PLAYERS];                   //~v@@@I~
    public Rect rectTileCalled;                                    //~vamhI~
    public int playerDrawEarth;                                    //~vamhI~
    public TileData tdOnEarth;                                     //~vamhR~
    public Bitmap bmOnEarth;                                       //~vamhI~
//*************************                                        //~v@@@I~
    public Earth(GCanvas Pgcanvas,Hands Phands)                    //~v@@@R~
    {                                                              //~0914I~//~v@@@R~
        AG.aEarth=this;                                            //~v@@@R~
        hands=Phands;                                              //~v@@@I~
        gcanvas = Pgcanvas;                                        //~v@@@R~
        table = gcanvas.table;                                     //~v@@@R~
        pieces=table.pieces;                                       //~v@@@R~
        players=AG.aPlayers;                                       //~v@@@R~
        bgColor=COLOR_BG_TABLE;                                    //~v@@@R~
        swVerticalEarths=hands.swVerticalEarths;//~v@@@R~
        Rect[] r=table.getRectEarthPair();                         //~v@@@R~
        earthRect=r;                                               //~v@@@R~
        WW=r[PLAYER_YOU].left; HH=r[PLAYER_YOU].top;               //~v@@@R~
        Point p=table.getPairPieceSize();                          //~v@@@R~
        piecePairW=p.x; piecePairH=p.y;                            //~v@@@R~
        if (Dump.Y) Dump.println("Earth.constructor pair,WW="+WW+",HH="+HH+",piecePairW="+piecePairW+",piecePairH="+piecePairH);//~v@@@I~
    }                                                              //~v@@@R~
    //*******************************************************************//~v@@@R~
    private int getRiverTilePos(TileData[] Ptds)                   //~v@@@R~
    {                                                              //~v@@@R~
        int rc=-1;                                                 //~v@@@R~
        for (int ii=0;ii<Ptds.length;ii++)                         //~v@@@R~
        {                                                          //~v@@@R~
            if ((Ptds[ii].flag & TDF_TAKEN_RIVER)!=0)              //~v@@@R~
            {                                                      //~v@@@R~
                rc=ii;                                             //~v@@@R~
                break;                                             //~v@@@R~
            }                                                      //~v@@@R~
        }                                                          //~v@@@R~
        if (Dump.Y) Dump.println("Earth.getRiverTilePos rc="+rc);  //~v@@@R~
//            if ((TestOption.option & TestOption.TO_KAN_CHANKAN)!=0)//~v@@@R~
//            {                                                    //~v@@@R~
//                if (Dump.Y) Dump.println("Player.getRiverTilePos rc="+rc+",@@@@reset to 0 by testoption");//~v@@@R~
//                rc=0;                                            //~v@@@R~
//            }                                                    //~v@@@R~
        return rc;                                                 //~v@@@R~
    }                                                              //~v@@@R~
    //*******************************************************************//~v@@@R~
    public TileData[] sortPairTile(TileData[] Ptds,int Pplayer)    //~v@@@R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("Earth.sortPairTile player="+Pplayer+",Ptds="+TileData.toString(Ptds));//~v@@@I~
        int ctr=Ptds.length;                                       //~v@@@R~
        int posRiver=getRiverTilePos(Ptds);                        //~v@@@R~
//        int posKanAdd=getKanAddTilePos(Ptds);                    //~v@@@R~
        boolean swRiver=posRiver>=0;                               //~v@@@R~
//      if (ctr==PAIRCTR_KAN && !isKanRiver(Ptds))                 //~v@@@R~
        if (ctr==PAIRCTR_KAN && !swRiver)                          //~v@@@R~
        {                                                          //~v@@@R~
//          Ptds[0].setKanTaken();                                 //~v@@@R~
//          Ptds[ctr-1].setKanTaken();                             //~v@@@R~
			return sortPairKanTaken(Ptds);                          //~v@@@I~
        }                                                          //~v@@@R~
//      int discarder=Ptds[0].player;                              //~v@@@R~
        TileData td=Ptds[posRiver];                                //~v@@@R~
        int discarder=td.player;                                   //~v@@@R~
        int player=Players.playerRelative(discarder,Pplayer);      //~v@@@R~
        if (Dump.Y) Dump.println("Earth.sortPairTile rivertile="+td.toString());//~v@@@R~
        TileData[] tdsout=new TileData[ctr];                       //~v@@@R~
        switch(player)                                             //~v@@@R~
        {                                                          //~v@@@R~
        case PLAYER_LEFT:                                          //~v@@@R~
//          tdsout[0]=Ptds[0];                                     //~v@@@R~
            tdsout[0]=td;                                          //~v@@@R~
//            if (posKanAdd>=0)                                    //~v@@@R~
//                tdsout[1]=Ptds[posKanAdd];                       //~v@@@R~
            if (Dump.Y) Dump.println("Earth.sortPairTile left");   //~v@@@R~
            break;                                                 //~v@@@R~
        case PLAYER_FACING:                                        //~v@@@R~
//          tdsout[1]=Ptds[0];                                     //~v@@@R~
            tdsout[1]=td;                                          //~v@@@R~
//            if (posKanAdd>=0)                                    //~v@@@R~
//                tdsout[2]=Ptds[posKanAdd];                       //~v@@@R~
            if (Dump.Y) Dump.println("Earth.sortPairTile facing is 2nd");//~v@@@R~
            break;                                                 //~v@@@R~
        default: //you and right                                   //~v@@@R~
//          tdsout[ctr-1]=Ptds[0];                                 //~v@@@R~
//            if (posKanAdd>=0)                                    //~v@@@R~
//            {                                                    //~v@@@R~
//                tdsout[ctr-2]=td;                                //~v@@@R~
//                tdsout[ctr-1]=Ptds[posKanAdd];                   //~v@@@R~
//            }                                                    //~v@@@R~
//            else                                                 //~v@@@R~
                tdsout[ctr-1]=td;                                  //~v@@@R~
            if (Dump.Y) Dump.println("Earth.sortPairTile right is last");//~v@@@R~
        }                                                          //~v@@@R~
        //*fill remaining                                          //~v@@@R~
        for (int ii=0;ii<ctr;ii++)                                 //~v@@@R~
        {                                                          //~v@@@R~
            for (int jj=0;jj<ctr;jj++)                             //~v@@@R~
            {                                                      //~v@@@R~
                if (tdsout[jj]==null)                              //~v@@@R~
                {                                                  //~v@@@R~
//                  if (ii!=posRiver && ii!=posKanAdd)             //~v@@@R~
                    if (ii!=posRiver)                              //~v@@@R~
                    {                                              //~v@@@R~
                        tdsout[jj]=Ptds[ii];                       //~v@@@R~
                        if (Dump.Y) Dump.println("Earth.sortPairTile jj="+jj+",ii="+ii+"="+Ptds[ii].toString());//~v@@@R~
                        break;                                     //~v@@@R~
                    }                                              //~v@@@R~
                }                                                  //~v@@@R~
            }                                                      //~v@@@R~
        }                                                          //~v@@@R~
        if (Dump.Y) Dump.println("Earth.sortPairTile sortout="+TileData.toString(tdsout));//~v@@@R~
        return tdsout;                                             //~v@@@R~
    }                                                              //~v@@@R~
    //*******************************************************************//~v@@@I~
    public TileData[] sortPairKanTaken(TileData[] Ptds)            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Earth.sortPairKanTaken entry="+TileData.toString(Ptds));//~vag9I~
        int ctr=Ptds.length;                                       //~v@@@I~
        TileData tdWk;                                             //~vag9I~
        if (Ptds[0].isRed5())                                      //~vag9I~
        {                                                          //~vag9I~
        	tdWk=Ptds[1];                                          //~vag9I~
        	Ptds[1]=Ptds[0];	                                   //~vag9I~
        	Ptds[0]=tdWk;                                          //~vag9I~
        }                                                          //~vag9I~
        if (Ptds[ctr-1].isRed5())                                  //~vag9I~
        {                                                          //~vag9I~
        	tdWk=Ptds[2];                                          //~vag9I~
        	Ptds[2]=Ptds[ctr-1];                                   //~vag9I~
        	Ptds[ctr-1]=tdWk;                                      //~vag9I~
        }                                                          //~vag9I~
        Ptds[0].setKanFaceDown();                                    //~v@@@I~
        Ptds[ctr-1].setKanFaceDown();                                //~v@@@I~
        if (Dump.Y) Dump.println("Earth.sortPairKanTaken exit="+TileData.toString(Ptds));//~vag9I~
        return Ptds;                                               //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*update pair by add kan                                      //~v@@@I~
    //*******************************************************************//~v@@@I~
	public void drawEarthAddKan(int Pplayer)                       //~v@@@I~
    {                                                              //~v@@@I~
    	int xx,yy,diff;                                            //~v@@@R~
    //*********************                                        //~v@@@I~
        if (Dump.Y) Dump.println("Earth.drawEarthAddKan player="+Pplayer);//~v@@@R~
    	TileData[] tds=AG.aPlayers.getEarthForAddKan(Pplayer);	           //~v@@@I~
        if (tds==null)                                             //~v@@@I~
        	return;                                                //~v@@@I~
	    TileData tdRiver=tds[getRiverTilePos(tds)];                      //~v@@@I~
        TileData tdAddKan=tds[tds.length-1];	//last is added kan            //~v@@@I~
        tdAddKan=new TileData(tdAddKan);                  //~v@@@I~
        int playerLose=tdAddKan.player;                            //~v@@@I~
        tdAddKan.player=tdRiver.player;    //temporally change for same rect and bitmap as RivaerTaken bitmap//~v@@@R~
//      Rect rectPiece=AG.aPlayers.getPieceRectForAddKan(Pplayer); //~v@@@R~
//        Graphics.drawRect(rectPiece,COLOR_KAN_ADD_UNDER);          //~v@@@I~
        Bitmap bm=getBitmapPair(tdAddKan,Pplayer);                 //~v@@@R~
        Rect rect=getRectEarthAddKan(Pplayer,bm);                  //~v@@@I~
        saveRectTileCalled(Pplayer,rect,tdAddKan,bm);              //~vamhR~
        tdAddKan.player=playerLose;	//recover player kan added(pay for completion)//~v@@@I~
        Bitmap bmscaled=Pieces.scaleImage(bm,RATE_ADD_KAN);        //~v@@@I~
//        switch(Pplayer)                                          //~v@@@R~
//        {                                                        //~v@@@R~
//        case PLAYER_YOU:                                         //~v@@@R~
//            diff=bm.getWidth()-bm.getHeight();                   //~v@@@R~
//            rectPiece.top-=diff;                                 //~v@@@R~
//            rectPiece.left+=bm.getWidth()/4;                     //~v@@@R~
//            break;                                               //~v@@@R~
//        case PLAYER_RIGHT:                                       //~v@@@R~
//            diff=bm.getHeight()-bm.getWidth();                   //~v@@@R~
//            rectPiece.left-=diff;                                //~v@@@R~
//            rectPiece.top+=bm.getHeight()/4;                     //~v@@@R~
//            break;                                               //~v@@@R~
//        case PLAYER_FACING:                                      //~v@@@R~
//            diff=bm.getWidth()-bm.getHeight();                   //~v@@@R~
//            rectPiece.bottom+=diff;                              //~v@@@R~
//            rectPiece.top=rectPiece.bottom-bmscaled.getHeight(); //~v@@@R~
//            rectPiece.left+=bm.getWidth()/4;                     //~v@@@R~
//            break;                                               //~v@@@R~
//        default:    //Left                                       //~v@@@R~
//            diff=bm.getHeight()-bm.getWidth();                   //~v@@@R~
//            rectPiece.right+=diff;                               //~v@@@R~
//            rectPiece.left=rectPiece.right-bmscaled.getWidth();  //~v@@@R~
//            rectPiece.top+=bm.getHeight()/4;                     //~v@@@R~
//        }                                                        //~v@@@R~
        Graphics.drawBitmap(rect,bmscaled,rect.left,rect.top);     //~v@@@R~
//      bmscaled.recycle();                                        //~v@@@R~
        bitmapScaledAddKan=bmscaled;                               //~v@@@I~
        rectScaledAddKan=rect;                                     //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*rect for add kan                                            //~v@@@I~
    //*******************************************************************//~v@@@I~
	private Rect getRectEarthAddKan(int Pplayer,Bitmap Pbitmap)    //~v@@@R~
    {                                                              //~v@@@I~
    	int diff,ww,hh;                                            //~v@@@I~
    //*********************                                        //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getRectEarthAddKan player="+Pplayer);//~v@@@R~
        Rect rectPiece=AG.aPlayers.getPieceRectForAddKan(Pplayer); //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getRectEarthAddKan rectPiece="+rectPiece.toString());//~vag9I~
        Rect r=new Rect(rectPiece);                                //~v@@@I~
     //   Bitmap bm=getBitmapPair(tdAddKan,Pplayer);                 //~v@@@I~
        ww=Pbitmap.getWidth(); hh=Pbitmap.getHeight();                       //~v@@@I~
        switch(Pplayer)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
            diff=ww-hh;                                            //~v@@@I~
            r.top-=diff;                                           //~v@@@I~
            r.bottom=r.top+(int)(hh*RATE_ADD_KAN);                 //~v@@@I~
            r.left+=(int)(ww*RATE_ADD_KAN/2);                      //~v@@@I~
            r.right=r.left+(int)(ww*RATE_ADD_KAN);                 //~v@@@I~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
            diff=hh-ww;                                            //~v@@@I~
            r.left-=diff;                                          //~v@@@I~
            r.right=r.left+(int)(ww*RATE_ADD_KAN);                 //~v@@@I~
            r.top+=(int)(hh*RATE_ADD_KAN/2);                       //~v@@@I~
            r.bottom=r.top+(int)(hh*RATE_ADD_KAN);                 //~v@@@I~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
            diff=ww-hh;                                            //~v@@@I~
            r.bottom+=diff;                                        //~v@@@I~
            r.top=(int)(r.bottom-hh*RATE_ADD_KAN);                 //~v@@@I~
            r.left+=(int)(ww*RATE_ADD_KAN/2);                      //~v@@@I~
            r.right=r.left+(int)(ww*RATE_ADD_KAN);                 //~v@@@I~
            break;                                                 //~v@@@I~
        default:    //Left                                         //~v@@@I~
            diff=hh-ww;                                            //~v@@@I~
            r.right+=diff;                                         //~v@@@I~
            r.left=(int)(r.right-ww*RATE_ADD_KAN);                 //~v@@@I~
            r.top+=(int)(hh*RATE_ADD_KAN/2);                       //~v@@@I~
            r.bottom=r.top+(int)(hh*RATE_ADD_KAN);                 //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getRectEarthAddKan return ww="+ww+",hh="+hh+",diff="+diff+",r="+r.toString());//~vag9I~
        return r;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*erase drawn pair                                            //~v@@@I~
    //*******************************************************************//~v@@@I~
	public void newGame()                                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Earth.newGame savedpairCtr="+Arrays.toString(savedPairCtr));//~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	if (ii!=0)                                             //~v@@@I~
	        	AG.aHands.clearOpenRect(ii);                       //~v@@@I~
            else                                                   //~v@@@I~
	        	AG.aHands.clearHands();                            //~v@@@I~
            for (int jj=0;jj<savedPairCtr[ii];jj++)                    //~v@@@I~
        		Graphics.drawRect(savedRectPair[ii][jj],bgColor); //bg clear required//~v@@@I~
        }                                                          //~v@@@I~
        Arrays.fill(savedPairCtr,0);                               //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*draw current pair by pon/chii/kan                           //~v@@@R~
    //*******************************************************************//~v@@@I~
	public void drawEarth(int Pplayer)                             //~v@@@R~
    {                                                              //~v@@@I~
    //*********************                                        //~v@@@I~
        if (Dump.Y) Dump.println("Earth.drawEarth player="+Pplayer);//~v@@@R~
    	TileData[] tds=getCurrentPair(Pplayer);                    //~v@@@R~
        if (tds==null)                                             //~v@@@I~
        	return;                                                //~v@@@I~
        if (openRects==null)                                       //~v@@@R~
	    	openRects=table.getOpenRect();                         //~v@@@R~
        if (players.isOpen(Pplayer))                               //~v@@@I~
        	hands.clearOpenRect(Pplayer);                                //~v@@@I~
        else                                                       //~va65I~
//      if ((TestOption.option2 & TO2_OPENHAND)!=0 && AG.swTrainingMode && AG.aAccounts.isRobotPlayer(Pplayer))//~va65R~
        if ((TestOption.option2 & TO2_OPENHAND)!=0                      && AG.aAccounts.isRobotPlayer(Pplayer))//~va65I~
        	hands.clearOpenRect(Pplayer);                          //~va65I~
        tds=sortPairTile(tds,Pplayer);                             //~v@@@R~
        Rect rectPair=getRectPair(Pplayer,tds);                    //~v@@@R~
//        canvas=Graphics.lockCanvas(rectPair);                    //~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
			switch(Pplayer)                                        //~v@@@I~
            {                                                      //~v@@@I~
            case PLAYER_YOU:                                       //~v@@@I~
            	drawEarthYou(Pplayer,rectPair,tds);                //~v@@@I~
                rectPairYou=rectPair;	//for shift Hands          //~v@@@I~
            	break;                                             //~v@@@I~
            case PLAYER_RIGHT:                                     //~v@@@I~
            	drawEarthRight(Pplayer,rectPair,tds);              //~v@@@I~
                pairRightBottom=Math.max(pairRightBottom,rectPair.bottom);	//top limit of opendraw//~v@@@I~
                openRects[PLAYER_RIGHT].top=Math.max(pairRightBottom,openRects[PLAYER_RIGHT].top);//~v@@@I~
		        if (Dump.Y) Dump.println("Earth.drawEarth pairRightBottom="+pairRightBottom+",open.top="+openRects[PLAYER_RIGHT].top);//~v@@@R~
            	break;                                             //~v@@@I~
            case PLAYER_FACING:                                    //~v@@@I~
            	drawEarthFacing(Pplayer,rectPair,tds);             //~v@@@I~
                pairFacingRight=Math.max(pairFacingRight,rectPair.right);   //left limit of opendraw//~v@@@I~
                openRects[PLAYER_FACING].left=Math.max(pairFacingRight,openRects[PLAYER_FACING].left);//~v@@@I~
		        if (Dump.Y) Dump.println("Earth.drawEarth pairFacingRight="+pairFacingRight+",open.left="+openRects[PLAYER_FACING].left);//~v@@@R~
            	break;                                             //~v@@@I~
            default:	//Left                                     //~v@@@R~
            	drawEarthLeft(Pplayer,rectPair,tds);               //~v@@@I~
                pairLeftTop=pairLeftTop==0 ? rectPair.top : Math.min(pairLeftTop,rectPair.top);             //bottom limit of opendraw//~v@@@R~
                openRects[PLAYER_LEFT].bottom=Math.min(pairLeftTop,openRects[PLAYER_LEFT].bottom);//~v@@@I~
		        if (Dump.Y) Dump.println("Earth.drawEarth pairLeftTop="+pairLeftTop+",open.bottom="+openRects[PLAYER_LEFT].bottom);//~v@@@R~
            }                                                      //~v@@@I~
//        }                                                        //~v@@@R~
//        catch(Exception e)                                       //~v@@@R~
//        {                                                        //~v@@@R~
//            Dump.println(e,"Earth.drawInitialDeal");             //~v@@@R~
//        }                                                        //~v@@@R~
//        Graphics.unlockCanvas(canvas);                           //~v@@@R~
        lastRectPair=rectPair;                                     //~v@@@I~
        savedRectPair[Pplayer][savedPairCtr[Pplayer]++]=rectPair;  //~v@@@I~
        if (players.isOpen(Pplayer))                               //~v@@@I~
	        hands.drawOpen(Pplayer,null/*taken tile*/);                  //~v@@@I~
        else                                                       //~va65I~
//      if ((TestOption.option2 & TO2_OPENHAND)!=0 && AG.swTrainingMode && AG.aAccounts.isRobotPlayer(Pplayer))//~va65R~
        if ((TestOption.option2 & TO2_OPENHAND)!=0                      && AG.aAccounts.isRobotPlayer(Pplayer))//~va65I~
	        hands.drawOpen(Pplayer,null/*taken tile*/);            //~va65I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
//  public void drawEarthYou(int Pplayer,Rect Prectpair,TileData[] Ptds)//~v@@@I~//~vamhR~
    private void drawEarthYou(int Pplayer,Rect Prectpair,TileData[] Ptds)//~vamhI~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Earth.drawEarthYou player="+Pplayer);//~v@@@R~
//      Graphics.drawRect(canvas,Prectpair,bgColor); //bg clear required//~v@@@R~
        Graphics.drawRect(Prectpair,bgColor); //bg clear required  //~v@@@I~
        int xx=Prectpair.left;                                      //~v@@@I~
        int yy=Prectpair.top;        //start point                  //~v@@@I~
        for (TileData td:Ptds)                                      //~v@@@I~
        {                                                          //~v@@@I~
        	if (Dump.Y) Dump.println("Earth.drawEarthYou td="+td.toString());//~v@@@I~
            Rect rectPiece=getRectPairPieceYou(Pplayer,td,xx,yy);  //~v@@@I~
            if ((td.flag & (TDF_DISCARDED|TDF_PON))==(TDF_DISCARDED|TDF_PON))//~v@@@I~
            	AG.aPlayers.savePieceRectForAddKan(Pplayer,rectPiece); //~v@@@I~
            Bitmap bm=getBitmapPair(td,Pplayer);                   //~v@@@I~
            saveRectTileCalled(td,PLAYER_YOU,rectPiece,bm);        //~vamhI~
//          Graphics.drawBitmap(canvas,bm,rectPiece.left,rectPiece.top);//~v@@@R~
            Graphics.drawBitmap(bm,rectPiece.left,rectPiece.top);  //~v@@@I~
            xx=rectPiece.right+PIECE_SPACING;                      //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//* point is left-top                                          //~v@@@R~
	//*********************************************************    //~v@@@I~
	public Rect getRectPairPieceYou(int Pplayer,TileData Ptd,int Pxx,int Pyy)//~v@@@I~
    {                                                              //~v@@@I~
        int xx1,xx2,yy1,yy2,ctr,ww,hh;                             //~v@@@I~
    //*****************************                                //~v@@@I~
    	ww=piecePairW; hh=piecePairH;                              //~v@@@I~
    	xx1=Pxx; yy1=Pyy;                                          //~v@@@I~
    	int flag=Ptd.flag;                                         //~v@@@I~
        if ((flag & TDF_DISCARDED)!=0)                             //~v@@@I~
        {                                                          //~v@@@I~
        	xx2=xx1+hh;                                            //~v@@@I~
        	yy2=yy1+hh;                                            //~v@@@R~
            yy1=yy2-ww;                                            //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	xx2=xx1+ww;                                            //~v@@@R~
			yy2=yy1+hh;                                            //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getRectPairPiece x1="+xx1+",y1="+yy1+",x2="+xx2+",y2="+yy2);//~v@@@R~
    	return new Rect(xx1,yy1,xx2,yy2);                          //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
//  public void drawEarthRight(int Pplayer,Rect Prectpair,TileData[] Ptds)//~v@@@I~//~vamhR~
    private void drawEarthRight(int Pplayer,Rect Prectpair,TileData[] Ptds)//~vamhI~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Earth.drawEarthRight player="+Pplayer);//~v@@@R~
//      Graphics.drawRect(canvas,Prectpair,bgColor); //bg clear required//~v@@@R~
        Graphics.drawRect(Prectpair,bgColor); //bg clear required  //~v@@@I~
        int xx=Prectpair.left;                                      //~v@@@I~
        int yy=Prectpair.bottom;                                    //~v@@@I~
        for (TileData td:Ptds)                                      //~v@@@I~
        {                                                          //~v@@@I~
            Rect rectPiece=getRectPairPieceRight(Pplayer,td,xx,yy);//~v@@@I~
            if ((td.flag & (TDF_DISCARDED|TDF_PON))==(TDF_DISCARDED|TDF_PON))//~v@@@I~
            	AG.aPlayers.savePieceRectForAddKan(Pplayer,rectPiece); //~v@@@I~
            Bitmap bm=getBitmapPair(td,Pplayer);                   //~v@@@I~
            saveRectTileCalled(td,PLAYER_RIGHT,rectPiece,bm);      //~vamhI~
//          Graphics.drawBitmap(canvas,bm,rectPiece.left,rectPiece.top);//~v@@@R~
            Graphics.drawBitmap(bm,rectPiece.left,rectPiece.top);  //~v@@@I~
            yy=rectPiece.top;                                      //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//* point is left-bottom                                       //~v@@@I~
	//*********************************************************    //~v@@@I~
	public Rect getRectPairPieceRight(int Pplayer,TileData Ptd,int Pxx,int Pyy)//~v@@@I~
    {                                                              //~v@@@I~
        int xx1,xx2,yy1,yy2,ctr,ww,hh;                             //~v@@@I~
    //*****************************                                //~v@@@I~
    	ww=piecePairW; hh=piecePairH;                              //~v@@@I~
    	xx1=Pxx;                                                   //~v@@@I~
        yy2=Pyy;                                                   //~v@@@I~
    	int flag=Ptd.flag;                                         //~v@@@I~
        if ((flag & TDF_DISCARDED)!=0)                             //~v@@@I~
        {                                                          //~v@@@I~
        	xx2=xx1+hh;                                            //~v@@@I~
        	xx1=xx2-ww;                                            //~v@@@I~
        	yy1=yy2-hh;                                            //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	xx2=xx1+hh;                                            //~v@@@I~
			yy1=yy2-ww;                                            //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getRectPairPieceRight x1="+xx1+",y1="+yy1+",x2="+xx2+",y2="+yy2);//~v@@@R~
    	return new Rect(xx1,yy1,xx2,yy2);                          //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
	//* start from right tile                                      //~v@@@I~
	//*********************************************************    //~v@@@I~
//  public void drawEarthFacing(int Pplayer,Rect Prectpair,TileData[] Ptds)                       //~v@@@I~//~vamhR~
    private void drawEarthFacing(int Pplayer,Rect Prectpair,TileData[] Ptds)//~vamhI~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Earth.drawEarthFacing player="+Pplayer);//~v@@@R~
//      Graphics.drawRect(canvas,Prectpair,bgColor); //bg clear required//~v@@@R~
        Graphics.drawRect(Prectpair,bgColor); //bg clear required  //~v@@@I~
        int xx=Prectpair.right;                                     //~v@@@I~
        int yy=Prectpair.top;                                       //~v@@@I~
        for (TileData td:Ptds)                                      //~v@@@I~
        {                                                          //~v@@@I~
            Rect rectPiece=getRectPairPieceFacing(Pplayer,td,xx,yy);//~v@@@I~
            if ((td.flag & (TDF_DISCARDED|TDF_PON))==(TDF_DISCARDED|TDF_PON))//~v@@@I~
            	AG.aPlayers.savePieceRectForAddKan(Pplayer,rectPiece); //~v@@@I~
            Bitmap bm=getBitmapPair(td,Pplayer);                   //~v@@@I~
            saveRectTileCalled(td,PLAYER_FACING,rectPiece,bm);     //~vamhI~
//          Graphics.drawBitmap(canvas,bm,rectPiece.left,rectPiece.top);//~v@@@R~
            Graphics.drawBitmap(bm,rectPiece.left,rectPiece.top);  //~v@@@I~
            xx=rectPiece.left;                                     //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//* point is right-top                                         //~v@@@I~
	//*********************************************************    //~v@@@I~
	public Rect getRectPairPieceFacing(int Pplayer,TileData Ptd,int Pxx,int Pyy)//~v@@@I~
    {                                                              //~v@@@I~
        int xx1,xx2,yy1,yy2,ctr,ww,hh;                             //~v@@@I~
    //*****************************                                //~v@@@I~
    	ww=piecePairW; hh=piecePairH;                              //~v@@@I~
    	xx2=Pxx;                                                   //~v@@@I~
        yy1=Pyy;                                                   //~v@@@I~
    	int flag=Ptd.flag;                                         //~v@@@I~
        if ((flag & TDF_DISCARDED)!=0)                             //~v@@@I~
        {                                                          //~v@@@I~
        	xx1=xx2-hh;                                            //~v@@@I~
        	yy2=yy1+ww;                                            //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	xx1=xx2-ww;                                            //~v@@@R~
			yy2=yy1+hh;                                            //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getRectPairPieceFacing x1="+xx1+",y1="+yy1+",x2="+xx2+",y2="+yy2);//~v@@@R~
    	return new Rect(xx1,yy1,xx2,yy2);                          //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
	//* start from left(top) tile                                  //~v@@@I~
	//*********************************************************    //~v@@@I~
//  public void drawEarthLeft(int Pplayer,Rect Prectpair,TileData[] Ptds)//~v@@@I~//~vamhR~
    private void drawEarthLeft(int Pplayer,Rect Prectpair,TileData[] Ptds)//~vamhI~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Earth.drawEarthLeft player="+Pplayer);//~v@@@R~
//      Graphics.drawRect(canvas,Prectpair,bgColor); //bg clear required//~v@@@R~
        Graphics.drawRect(Prectpair,bgColor); //bg clear required  //~v@@@I~
        int xx=Prectpair.left;                                     //~v@@@R~
        int yy=Prectpair.top;                                      //~v@@@R~
        for (TileData td:Ptds)                                      //~v@@@I~
        {                                                          //~v@@@I~
            Rect rectPiece=getRectPairPieceLeft(Pplayer,td,xx,yy); //~v@@@I~
            if ((td.flag & (TDF_DISCARDED|TDF_PON))==(TDF_DISCARDED|TDF_PON))//~v@@@I~
            	AG.aPlayers.savePieceRectForAddKan(Pplayer,rectPiece); //~v@@@I~
            Bitmap bm=getBitmapPair(td,Pplayer);                   //~v@@@I~
            saveRectTileCalled(td,PLAYER_LEFT,rectPiece,bm);       //~vamhI~
//          Graphics.drawBitmap(canvas,bm,rectPiece.left,rectPiece.top);//~v@@@R~
            Graphics.drawBitmap(bm,rectPiece.left,rectPiece.top);  //~v@@@I~
            yy=rectPiece.bottom;                                   //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//* point is left-bottom                                       //~v@@@I~
	//*********************************************************    //~v@@@I~
	public Rect getRectPairPieceLeft(int Pplayer,TileData Ptd,int Pxx,int Pyy)//~v@@@I~
    {                                                              //~v@@@I~
        int xx1,xx2,yy1,yy2,ctr,ww,hh;                             //~v@@@I~
    //*****************************                                //~v@@@I~
    	ww=piecePairW; hh=piecePairH;                              //~v@@@I~
    	xx1=Pxx;                                                   //~v@@@I~
        yy1=Pyy;                                                   //~v@@@R~
    	int flag=Ptd.flag;                                         //~v@@@I~
        if ((flag & TDF_DISCARDED)!=0)                             //~v@@@I~
        {                                                          //~v@@@I~
        	xx2=xx1+ww;                                            //~v@@@R~
        	yy2=yy1+hh;                                            //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	xx2=xx1+hh;                                            //~v@@@I~
			yy2=yy1+ww;                                            //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getRectPairPieceLeft x1="+xx1+",y1="+yy1+",x2="+xx2+",y2="+yy2);//~v@@@R~
    	return new Rect(xx1,yy1,xx2,yy2);                          //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@R~
    public TileData[] getCurrentPair(int Pplayer)                  //~v@@@R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("Earth.getcurrentPair player="+Pplayer);//~v@@@R~
        TileData[] tds=AG.aPlayers.getCurrentPair(Pplayer);        //~v@@@R~
        return tds;                                                //~v@@@R~
    }                                                              //~v@@@R~
    //*********************************************************    //~v@@@R~
    //*adjust rect box by stock position from Stock                //~v@@@R~
    //*********************************************************    //~v@@@R~
    public void notifyStockRect(Rect[] PrectStock)                 //~v@@@R~
    {                                                              //~v@@@R~
        rectStock=PrectStock;                                      //~v@@@R~
        if (Dump.Y) Dump.println("Earth.adjustRectPair from Stock");//~v@@@R~
    }                                                              //~v@@@R~
    //*********************************************************    //~v@@@R~
    //*adjust rect box by stock position                           //~v@@@R~
    //*********************************************************    //~v@@@R~
    public void adjustRectPair(Rect[] PrectStock)                  //~v@@@R~
    {                                                              //~v@@@R~
        int ww,hh,ww4,dist,stockEnd,pairStart,stockBottom;         //~v@@@R~
        Rect rectPair;                                             //~v@@@R~
    //*******************************                              //~v@@@R~
        ww=piecePairW; hh=piecePairH;                              //~v@@@R~
//        if (PpairLen!=0)                                         //~v@@@R~
//            ww4=PpairLen;                                        //~v@@@R~
//        else                                                     //~v@@@R~
            ww4=ww*PAIRCTR+hh;                                     //~v@@@R~
    //*You                                                         //~v@@@R~
        rectPair=earthRect[PLAYER_YOU];    //distans from wall right and bottom//~v@@@R~
        stockEnd=PrectStock[PLAYER_YOU].right;                     //~v@@@R~
        int stockRight=stockEnd;                                   //~v@@@R~
        stockBottom=PrectStock[PLAYER_YOU].bottom;                 //~v@@@R~
        int endHands=hands.rectHands.right;                              //~v@@@R~
        stockEnd=Math.max(stockEnd,endHands);                      //~v@@@R~
        pairStart=WW-(ww4+rectPair.right);                         //~v@@@R~
        if (Dump.Y) Dump.println("Earth.adjustRectPair you endHands="+endHands+",ww4="+ww4+",stockRight="+stockRight+",stockEnd="+stockEnd+",pairStart="+pairStart+",stockBottom="+stockBottom);//~v@@@R~
        if (Dump.Y) Dump.println("Earth.adjustRectPair rectHands="+hands.rectHands.toString());//~v@@@I~
        dist=pairStart-stockEnd;                                   //~v@@@R~
        if (hands.swPortrait && swVerticalEarths[PLAYER_YOU]             //~v@@@R~
        &&  stockRight<pairStart)                                  //~v@@@R~
        {                                                          //~v@@@R~
            if (endHands>pairStart)                                //~v@@@R~
                rectPair.bottom=HH-stockBottom;                    //~v@@@R~
        }                                                          //~v@@@R~
        else                                                       //~v@@@R~
        {                                                          //~v@@@R~
            if (dist>0)                                            //~v@@@R~
            {                                                      //~v@@@R~
                rectPair.right+=Math.max(dist-MARGIN_PAIR_START,0);//~v@@@R~
//              rectPair.bottom=stockBottom;                       //~v@@@R~
                rectPair.bottom=HH-hands.rectHands.bottom;         //~v@@@I~
            }                                                      //~v@@@R~
    //      else                                                   //~v@@@R~
    //          rectPair.right=0;     //2 tile was deleted         //~v@@@R~
        }                                                          //~v@@@R~
        if (Dump.Y) Dump.println("Earth.adjustRectPair you stockEnd="+stockEnd+",dist="+dist+",right="+rectPair.right+",bottom="+rectPair.bottom);//~v@@@R~
    //*Right                                                       //~v@@@R~
        rectPair=earthRect[PLAYER_RIGHT];                          //~v@@@R~
        stockEnd=PrectStock[PLAYER_RIGHT].top;                     //~v@@@R~
        pairStart=(ww4+rectPair.bottom);                           //~v@@@R~
        dist=stockEnd-pairStart;                                   //~v@@@R~
        if (dist>0)                                                //~v@@@R~
            rectPair.bottom+=Math.max(dist-MARGIN_PAIR_START,0);   //~v@@@R~
        else                                                       //~v@@@R~
        if (swVerticalEarths[PLAYER_RIGHT])                        //~v@@@R~
            rectPair.bottom=0;                                     //~v@@@R~
        if (Dump.Y) Dump.println("Earth.adjustRectPair right stockEnd="+stockEnd+",dist="+dist+",rectPair right="+rectPair.right+",bottom="+rectPair.bottom);//~v@@@R~
    //*Facing                                                      //~v@@@R~
        rectPair=earthRect[PLAYER_FACING];                         //~v@@@R~
        stockEnd=PrectStock[PLAYER_FACING].left;                   //~v@@@R~
        pairStart=ww4+rectPair.right;                              //~v@@@R~
        dist=stockEnd-pairStart;                                   //~v@@@R~
        if (dist>0)                                                //~v@@@R~
            rectPair.right+=Math.max(dist-MARGIN_PAIR_START,0);    //~v@@@R~
        else                                                       //~v@@@R~
        if (swVerticalEarths[PLAYER_FACING])                       //~v@@@I~
            rectPair.right=0;                                      //~v@@@R~
        if (Dump.Y) Dump.println("Earth.adjustRectPair facing stockEnd="+stockEnd+",dist="+dist+",right="+rectPair.right);//~v@@@R~
    //*Left                                                        //~v@@@R~
        rectPair=earthRect[PLAYER_LEFT];                           //~v@@@R~
        if (Dump.Y) Dump.println("Earth.adjustRectPair ww4="+ww4+",rectPair right="+rectPair.right+",bottom="+rectPair.bottom);//~v@@@I~
        stockEnd=PrectStock[PLAYER_LEFT].bottom;                   //~v@@@R~
//      pairStart=table.handY-ww4; //erathY contains bottomButtonH //~v@@@R~
        pairStart=HH-(rectPair.bottom+ww4); //erathY contains bottomButtonH//~v@@@I~
        dist=pairStart-stockEnd;                                   //~v@@@R~
        if (dist>0)                                                //~v@@@R~
            rectPair.bottom+=Math.max(dist-MARGIN_PAIR_START,0);   //~v@@@R~
        else                                                       //~v@@@R~
            rectPair.bottom=HH-table.handY;                        //~v@@@R~
        if (Dump.Y) Dump.println("Earth.adjustRectPair left stockEnd="+stockEnd+",dist="+dist+",rectPair right="+rectPair.right+",bottom="+rectPair.bottom);//~v@@@R~
    }                                                              //~v@@@R~
    //*********************************************************    //~v@@@R~
    //*return pickup kan(minkan:taken from river)                  //~v@@@R~
    //*********************************************************    //~v@@@R~
    private boolean isPickupKan(TileData[] Ptds)                   //~v@@@R~
    {                                                              //~v@@@R~
        for (TileData td:Ptds)                                     //~v@@@R~
            if ((td.flag & TDF_TAKEN_RIVER)!=0)                    //~v@@@R~
                return true;                                       //~v@@@R~
        return false;                                              //~v@@@R~
    }                                                              //~v@@@R~
    //*********************************************************    //~v@@@I~
    private int getPairLen(TileData[] Ptds)                        //~v@@@I~
    {                                                              //~v@@@I~
        int len=0,ww,hh;                                           //~v@@@I~
    //********************                                         //~v@@@I~
        ww=piecePairW; hh=piecePairH;                              //~v@@@I~
        for (TileData td:Ptds)                                     //~v@@@I~
        {                                                          //~v@@@I~
        	int flag=td.flag;                                      //~v@@@I~
        	if ((flag & TDF_DISCARDED)!=0)                              //~v@@@I~
            	len+=hh;                                            //~v@@@I~
            else                                                   //~v@@@I~
            	len+=ww;                                            //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getPairLen rc="+len+",ctr="+Ptds.length);//~v@@@R~
        return len;//~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@R~
    public Rect getRectPair(int Pplayer,TileData[] Ptds)//~v@@@R~
    {                                                              //~v@@@R~
        int xx1,xx2,yy1,yy2,ww,hh,addKan=0;                    //~v@@@R~
    //*****************************                                //~v@@@R~
        ww=piecePairW; hh=piecePairH;                              //~v@@@R~
        if (Ptds.length==PAIRCTR_KAN)                              //~v@@@R~
			addKan=(isPickupKan(Ptds) ? ww : ww-(hh-ww))+PIECE_SPACING;//~v@@@R~
        Point p=AG.aPlayers.getPointPair(Pplayer);//previously saved right-bottom of pairBox//~v@@@R~
        if (p==null)    //TODO test                                //~v@@@R~
        {                                                          //~v@@@R~
            int len=getPairLen(Ptds);                              //~v@@@R~
            adjustRectPair(rectStock);                         //~v@@@R~
        }                                                          //~v@@@R~
        swVerticalEarth=swVerticalEarths[Pplayer];                 //~v@@@I~
        switch(Pplayer)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
            if (p==null)    //1st pair                             //~v@@@I~
            {                                                      //~v@@@I~
                xx2=WW-earthRect[PLAYER_YOU].right  /*distance from right button*/;//~v@@@R~
                yy2=HH-earthRect[PLAYER_YOU].bottom /*distance from bottom button*/;//~v@@@R~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
          	  if (swVerticalEarth)                                 //~v@@@R~
              {                                                    //~v@@@I~
                xx2=p.x;                                            //~v@@@I~
                yy2=p.y;                                           //~v@@@I~
              }                                                    //~v@@@I~
              else                                                 //~v@@@I~
              {                                                    //~v@@@I~
                xx2=p.x-PAIR_SPACING;                              //~v@@@I~
                yy2=p.y;                                           //~v@@@I~
              }                                                    //~v@@@I~
            }                                                      //~v@@@I~
            yy1=yy2-hh;                                            //~v@@@R~
            xx1=xx2-(hh+ww+ww+PIECE_SPACING*2+addKan);             //~v@@@R~
          	if (swVerticalEarth)                                  //~v@@@I~
            	AG.aPlayers.savePointPair(Pplayer,new Point(xx2,yy1)); //new right-bottom;left of prev pair//~v@@@I~
            else                                                   //~v@@@I~
            	AG.aPlayers.savePointPair(Pplayer,new Point(xx1,yy2)); //new right-bottom;left of prev pair//~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
            if (p==null)    //1st pair                             //~v@@@I~
            {                                                      //~v@@@I~
                xx2=WW-earthRect[PLAYER_RIGHT].right  /*distance from right button*/;//~v@@@R~
                yy1=   earthRect[PLAYER_RIGHT].bottom /*distance from top button*/;//~v@@@R~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
          	  if (swVerticalEarth)                                 //~v@@@I~
              {                                                    //~v@@@I~
                xx2=p.x-hh;                                        //~v@@@I~
                yy1=p.y;                                           //~v@@@I~
              }                                                    //~v@@@I~
              else                                                 //~v@@@I~
              {                                                    //~v@@@I~
                xx2=p.x;                                           //~v@@@I~
                yy1=p.y+PAIR_SPACING;                              //~v@@@I~
              }                                                    //~v@@@I~
            }                                                      //~v@@@I~
            xx1=xx2-hh;                                            //~v@@@I~
            yy2=yy1+(ww+ww+hh+PIECE_SPACING*2+addKan);             //~v@@@R~
          	if (swVerticalEarth)                                   //~v@@@I~
            	AG.aPlayers.savePointPair(Pplayer,new Point(xx2,yy1));//new right-bottom,over prev pair(-x)//~v@@@R~
            else                                                   //~v@@@I~
            	AG.aPlayers.savePointPair(Pplayer,new Point(xx2,yy2));//new right-top//~v@@@I~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
            if (p==null)    //1st pair                             //~v@@@I~
            {                                                      //~v@@@I~
                xx1=   earthRect[PLAYER_FACING].right  /*distance from left button*/;//~v@@@R~
                yy1=   earthRect[PLAYER_FACING].bottom /*distance from top button*/;//~v@@@R~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
          	  if (swVerticalEarth)                                 //~v@@@I~
              {                                                    //~v@@@I~
                xx1=p.x;                                           //~v@@@I~
//              yy1=p.y+hh;                                        //~v@@@R~
                yy1=p.y-hh;                                        //~v@@@I~
              }                                                    //~v@@@I~
              else                                                 //~v@@@I~
              {                                                    //~v@@@I~
                xx1=p.x+PAIR_SPACING;                              //~v@@@R~
                yy1=p.y;                                           //~v@@@I~
              }                                                    //~v@@@I~
            }                                                      //~v@@@I~
            xx2=xx1+(ww+ww+hh+PIECE_SPACING*2+addKan);             //~v@@@R~
            yy2=yy1+hh;                                            //~v@@@I~
          	if (swVerticalEarth)                                   //~v@@@I~
            	AG.aPlayers.savePointPair(Pplayer,new Point(xx1,yy1)); //new roght-bottom,over prev pair(+y)//~v@@@R~
            else                                                   //~v@@@I~
            	AG.aPlayers.savePointPair(Pplayer,new Point(xx2,yy1)); //new roght-bottom,over prev pair(+y)//~v@@@I~
            break;                                                 //~v@@@I~
         default:   //PLAYER_LEFT                                  //~v@@@R~
            if (p==null)    //1st pair                             //~v@@@I~
            {                                                      //~v@@@I~
                xx1=   earthRect[PLAYER_LEFT].right  /*distance from left button*/;//~v@@@R~
                yy2=HH-earthRect[PLAYER_LEFT].bottom /*distance from hands*/;//~v@@@R~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
          	  if (swVerticalEarth)                                 //~v@@@I~
              {                                                    //~v@@@I~
                xx1=p.x+hh;                                        //~v@@@I~
                yy2=p.y;                                           //~v@@@I~
              }                                                    //~v@@@I~
              else                                                 //~v@@@I~
              {                                                    //~v@@@I~
                xx1=p.x;                                           //~v@@@I~
                yy2=p.y-PAIR_SPACING;                              //~v@@@I~
              }                                                    //~v@@@I~
            }                                                      //~v@@@I~
            yy1=yy2-(ww+ww+hh+PIECE_SPACING*2+addKan);             //~v@@@R~
            xx2=xx1+hh;                                            //~v@@@I~
          	if (swVerticalEarth)                                   //~v@@@I~
	            AG.aPlayers.savePointPair(Pplayer,new Point(xx1,yy2));//new right-bottom,over previous//~v@@@R~
            else                                                   //~v@@@I~
	            AG.aPlayers.savePointPair(Pplayer,new Point(xx1,yy1));//new right-bottom,over previous//~v@@@I~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getRectPair player="+Pplayer+",x1="+xx1+",y1="+yy1+",x2="+xx2+",y2="+yy2);//~v@@@R~
        return new Rect(xx1,yy1,xx2,yy2);                          //~v@@@R~
    }                                                              //~v@@@R~
	//*********************************************************    //~v@@@I~
    private Bitmap getBitmapPair(TileData Ptd,int Pplayer)         //~v@@@R~
    {                                                              //~v@@@I~
    	Bitmap bm;                                                 //~v@@@I~
	    int player;                                                //~v@@@R~
    //************************                                     //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getBitmapPair player="+Pplayer+",tile="+Ptd.toString());//~v@@@R~
	    if ((Ptd.flag & TDF_KAN_FACEDOWN)!=0)                      //~v@@@R~
        {                                                          //~v@@@I~
	    	player=Ptd.player;                                     //~v@@@I~
            bm=pieces.getBitmapHandPairStock(player);              //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//          if ((Ptd.flag & TDF_TAKEN_RIVER)!=0)                   //~v@@@R~
            if ((Ptd.flag & (TDF_TAKEN_RIVER|TDF_KAN_ADDED_TILE))!=0)//~v@@@I~
            {                                                      //~v@@@R~
                player=Ptd.player;                                 //~v@@@R~
                if (Players.playerRelative(player,Pplayer)==PLAYER_FACING) //taken facing discarded//~v@@@R~
                    player=Players.prevPlayer(player);             //~v@@@R~
            }                                                      //~v@@@R~
            else                                                   //~v@@@R~
                player=Pplayer;                                    //~v@@@R~
            bm=pieces.getBitmapHandPair(Ptd,player);               //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getBitmapPair result player="+player);//~v@@@I~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*show complete mark for chankan                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public void complete(int Pplayer,TileData Ptd)                //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Earth.complete player="+Pplayer+",tile:"+Ptd.toString());//~v@@@R~
        Bitmap bm=bitmapScaledAddKan;                                     //~v@@@I~
		Rect r=rectScaledAddKan;                                   //~v@@@I~
        int stroke_width=Pieces.getStrokeWidth(bm.getWidth());      //~v@@@I~
//      Graphics.drawRectFrameBitmap(r,bgColor,bm,r.left,r.top,COMPLETE_STROKE_WIDTH_ADD_KAN,COMPLETE_COLOR_KAN_ADD);//~v@@@R~
        Graphics.drawRectFrameBitmap(r,bgColor,bm,r.left,r.top,stroke_width,COMPLETE_COLOR_KAN_ADD);//~v@@@I~
        AG.aAnim.showWin(r,Ptd,Pplayer,KAN_ADD);                   //~vag9R~
        if (Dump.Y) Dump.println("Earth.complete chankan stroke_width="+stroke_width);//~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*draw ron mark on earth kan  (kokusi ron for ankan)          //~v@@@R~
	//*********************************************************    //~v@@@I~
//  public void complete(TileData Ptd,int Pflag)                   //~v@@@I~//~vag9R~
    public void complete(TileData Ptd,int Pflag,int Pplayer)       //~vag9I~
    {                                                              //~v@@@I~
		Rect r=lastRectPair;                                       //~v@@@I~
        if (Dump.Y) Dump.println("Earth.complete flag="+Pflag+",tile:"+Ptd.toString()+",rect="+r.toString());//~v@@@I~
//      Graphics.drawRect(r,COMPLETE_COLOR_KAN_TAKEN,COMPLETE_STROKE_WIDTH);//~v@@@R~
        Graphics.drawRect(r,COMPLETE_COLOR_KAN_TAKEN,AG.aRiver.stroke_width_river);//~v@@@I~
        AG.aAnim.showWin(r,Ptd,Pplayer,KAN_TAKEN);                 //~vag9R~
        if (Dump.Y) Dump.println("Earth.complete ankan ron stroke width="+AG.aRiver.stroke_width_river);//~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*chk space for earth                                         //~v@@@I~
	//*********************************************************    //~v@@@I~
    public static int chkEarthSpace(boolean PswPortRait,int PpieceW/*Hand PieceW*/,double PrateHeightWidth)//~v@@@R~
    {                                                              //~v@@@I~
    	int ww,space;                                              //~v@@@I~
		Rect[] rs=AG.aMJTable.getRectEarthPair();	//BoxWidth,BoxHeight,Horizontal,VerticalMargin//~v@@@I~
        if (Dump.Y) Dump.println("Earth.chkEarthSpace earthPair 0="+rs[0].toString());//~v@@@I~
        if (Dump.Y) Dump.println("Earth.chkEarthSpace earthPair 1="+rs[1].toString());//~v@@@I~
        if (Dump.Y) Dump.println("Earth.chkEarthSpace earthPair 2="+rs[2].toString());//~v@@@I~
        if (Dump.Y) Dump.println("Earth.chkEarthSpace earthPair 3="+rs[3].toString());//~v@@@I~
                                                                   //~v@@@I~
        if (PswPortRait)                                           //~v@@@I~
        	space=rs[0].left-rs[0].right;                          //~v@@@I~
        else                                                       //~v@@@I~
        	space=rs[1].left-rs[1].bottom;                         //~v@@@I~
        int req1=MARGIN_PAIR_START+PAIR_SPACING*3 /*4pair*/ + (PIECE_SPACING*3 /*4tiles*/)*4 /*4pair*/;//~v@@@I~
        int req3=PpieceW*2+PIECE_SPACING_TAKEN*2;                   //~v@@@I~
        double req2=(3 /*tilesStand*/ + PrateHeightWidth /*1tiles lying*/) *4 /*4pair*/;//~v@@@I~
      	if (!PswPortRait)                                          //~vavpI~
      	{                                                          //~vavpI~
        	space=rs[0].left-rs[0].right;                          //~vavpI~
        	req3=PIECE_SPACING_TAKEN*2;                            //~vavpI~
        	req2+=2;                                               //~vavpI~
        	if (Dump.Y) Dump.println("Earth.chkEarthSpace portrait space="+space+",req3="+req3+",req2="+req2);//~vavpI~
      	}                                                          //~vavpI~
        ww=(int)((space-req1-req3)/req2);                          //~v@@@R~
        int wwMax=(int)(PpieceW*MAX_EARCH_RATE);                     //+vavpI~
        if (Dump.Y) Dump.println("Earth.chkEarthSpace handW="+PpieceW+",space="+space+",req1="+req1+",req2="+req2+",req3="+req3+",ww="+ww+",wwMax="+wwMax);//+vavpI~
        if (ww>wwMax)                                              //+vavpI~
        	ww=wwMax;                                              //+vavpI~
        if (Dump.Y) Dump.println("Earth.chkEarthSpace ww="+ww+",wwMax="+wwMax);//+vavpR~
        return ww;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public int getEarthRightYou()                                  //~v@@@I~
    {                                                              //~v@@@I~
        int pos=WW-earthRect[PLAYER_YOU].right;    //distance from wall right and bottom//~v@@@R~
        if (Dump.Y) Dump.println("Earth.getEarthRightYou pos="+pos+",WW="+WW+",earthRect="+earthRect[PLAYER_YOU].toString());//~v@@@R~
        return pos;                                                //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*get last pair Rect to shift OpenTile                        //~v@@@I~
	//*********************************************************    //~v@@@I~
    public Rect[] getLastRectPairS()                               //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getLastRectPairS savedPairCtr="+Arrays.toString(savedPairCtr));//~v@@@R~
        if (Dump.Y) Dump.println("Earth.getLastRectPairS savedPairCtr="+ Utils.toString(savedRectPair));//~v@@@R~
    	Rect[] rp=new Rect[PLAYERS];                               //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
		    rp[ii]=savedPairCtr[ii]==0 ? null : savedRectPair[ii][savedPairCtr[ii]-1];//~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getLastRectPair rc="+Utils.toString(rp));//~v@@@I~
        return rp;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*get last pair Rect to shift OpenTile                        //~v@@@I~
	//*********************************************************    //~v@@@I~
    public Rect getLastRectPair(int Pplayer)                       //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getLastRectPair player="+Pplayer+",savedPairCtr="+Arrays.toString(savedPairCtr));//~v@@@I~
        if (Dump.Y) Dump.println("Earth.getLastRectPair savedRectPair="+ Utils.toString(savedRectPair));//~v@@@R~
		int ctr=savedPairCtr[Pplayer];                             //~v@@@I~
        Rect rp=ctr==0 ? null : savedRectPair[Pplayer][ctr-1];     //~v@@@I~
        if (Dump.Y) Dump.println("Earth.getLastRectPair rc="+Utils.toString(rp));//~v@@@I~
        return rp;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~vamhI~
	//*Tile on Earth called Pon/Chii/KAN_RIVER                     //~vamhI~
	//*********************************************************    //~vamhI~
    private boolean saveRectTileCalled(TileData Ptd,int Pplayer,Rect Prect,Bitmap Pbitmap)//~vamhR~
    {                                                              //~vamhI~
        boolean rc=false;                                          //~vamhI~
        if ((Ptd.flag & TDF_DISCARDED)!=0)                         //~vamhI~
        {                                                          //~vamhI~
        	if ((Ptd.flag & (TDF_PON | TDF_CHII | TDF_RON | TDF_KAN_RIVER))!=0)//~vamhI~
            	rc=true;                                           //~vamhI~
        }                                                          //~vamhI~
        if ((Ptd.flag & TDF_KAN_TAKEN)!=0)                         //~vamhR~
        	if ((Ptd.flag & (TDF_KAN_FACEDOWN))==0)
        	    rc=true;//~vamhI~
        if (Dump.Y) Dump.println("Earth.saveRectTileCalled rc="+rc+",td="+Ptd);//~vamhI~
        if (rc)                                                    //~vamhI~
		    saveRectTileCalled(Pplayer,Prect,Ptd,Pbitmap);         //~vamhR~
        return rc;                                                 //~vamhI~
    }                                                              //~vamhI~
	//*********************************************************    //~vamhI~
	//*for Animation to Earth tile for also from DrawEarthAddKan   //~vamhR~
	//*********************************************************    //~vamhI~
    private void saveRectTileCalled(int Pplayer,Rect Prect,TileData Ptd,Bitmap Pbitmap)//~vamhR~
    {                                                              //~vamhI~
    	rectTileCalled=Prect;                                      //~vamhI~
    	playerDrawEarth=Pplayer;                                   //~vamhI~
    	tdOnEarth=Ptd;                                             //~vamhI~
    	bmOnEarth=Pbitmap;                                          //~vamhI~
        if (Dump.Y) Dump.println("Earth.saveRectTileCalled plyaer="+Pplayer+",rect="+Prect+",tdOnEarth="+Ptd+",bmOnEarth="+bmOnEarth);//~vamhR~
    }                                                              //~vamhI~
}//class Hands                                                 //~dataR~//~@@@@R~//~v@@@R~
