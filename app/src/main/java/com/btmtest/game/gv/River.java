//*CID://+va05R~: update#= 582;                                    //+va05R~
//**********************************************************************//~v101I~
//2020/04/26 va05:sound effect at positioning tile get             //+va05I~
//**********************************************************************//+va05I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                       //~0303R~

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.btmtest.TestOption;
import com.btmtest.game.Players;

import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.Players.*;
import static com.btmtest.game.Complete.*;
import com.btmtest.game.Tiles;
import static com.btmtest.game.Tiles.*;
import com.btmtest.game.TileData;
import static com.btmtest.game.TileData.*;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;
import com.btmtest.utils.sound.Sound;

import java.util.Arrays;

import static com.btmtest.game.gv.MJTable.*;
import static com.btmtest.game.GConst.*;                           //~v@@@R~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~


public class River                                                 //~v@@@R~
{                                                                  //~0914I~
	private static final int COLOR_FG_DISABLE=Color.argb(0xa0,0x80,0x80,0x80);//~v@@@I~
    private static final int COLOR_NEXT_PLAYER_PONKAN =Color.argb(0xff,0xff,0xff,0x00);   //yellow//~v@11R~//~v@@@I~
    private static final int COLOR_NEXT_PLAYER        =Color.argb(0xff,0x54,0xe8,0x1c);   //green//~v@21I~//~v@11R~//~v@@@I~
//  private static final int COLOR_DISCARDED          =Color.argb(0xff,0x00,0xbf,0xff);   //deep sky blue//~v@@@I~
//  private static final int COLOR_DISCARDED          =Color.argb(0xff,0xff,0x33,0x66);   //Light's orange//~v@11R~//~v@@@R~
    private static final int COLOR_DISCARDED          =Color.argb(0xff,0xff,0x66,0x00);   //Light's orange//~v@@@I~
//  private static final int COLOR_ERASE_FRAME        =Color.argb(0xff,0xff,0xff,0xff);   //white//~v@@@R~
//  private static final int COMPLETE_COLOR           =Color.argb(0xff,0xff,0x66,0x00);   //Ron's orange//~v@@@I~
    private static final int COLOR_INNER_FRAME        =Color.argb(0xff,0x00,0x00,0x00);   //black//~v@@@R~
    private static final int RIVER_MAXTILE=24;                     //~0324I~
                                                                   //~v@@@I~
	private static final int CTR_SETUP=TT_4ESWN_CTR+2;             //~v@@@R~
                                                                   //~v@@@I~
	private GCanvas gcanvas;                                       //~v@@@R~
    private MJTable table;
    private Tiles tiles;//~v@@@I~
    private Pieces pieces;//~v@@@I~
    private Point[] pointsRiver;                                   //~v@@@R~
    private Canvas canvas;                                         //~v@@@I~
    private int bgColor;                                           //~v@@@I~
    private int pieceW,pieceH;                                     //~v@@@I~
    private Bitmap[][][] bmsssRiver;  //[standing/lying][man/pin/sou/ji][number]//~v@@@I~
    private int sepH,sepW,rowRiver,colRiver;
    private boolean swPortrait;//~v@@@I~
    private boolean swComplete;                                            //~v@@@I~
    private Players players;
    private TileData[] tdsSetup,sortedSetupTiles;                  //~v@@@R~
    private int ctrSelected=0;                                     //~v@@@I~
    private Rect rectSetupTiles;                                   //~v@@@I~
    private Rect rectDiscarded;                                    //~v@@@I~
    private Bitmap bmDiscarded;                                    //~v@@@I~
    private Bitmap bmComplete;                                     //~0303I~
    private Rect rectComplete;                                      //~0303I~
    private int[] lastRowCols=new int[PLAYERS];                     //~0323R~//~0324R~
    private Rect lastRowRect;                                      //~0323I~
    private boolean swRiverCleared;                                //~0324I~
    public int stroke_width_river;                                 //~0401I~
//*************************                                        //~v@@@I~
	public River(GCanvas Pgcanvas)                                 //~v@@@R~
    {                                                              //~0914I~
    	AG.aRiver=this;                                             //~v@@@I~
        gcanvas = Pgcanvas;                                          //~v@@@I~
        table = gcanvas.table;                                     //~v@@@I~
        players=AG.aPlayers;                                       //~v@@@I~
        tiles=AG.aTiles;                                           //~v@@@I~
        pieces=table.pieces;                                       //~v@@@I~
        swPortrait=table.swPortrait;                               //~v@@@I~
        bgColor=COLOR_BG_TABLE;                                    //~v@@@R~
        pieceW=table.riverPieceW;                                  //~v@@@R~
        pieceH=table.riverPieceH;                                  //~v@@@R~
        stroke_width_river=Pieces.getStrokeWidth(pieceW);           //~0401I~
//      bmsssRiver=pieces.bitmapAllPiecesRiver;                    //~v@@@R~
        bmsssRiver=AG.bitmapAllPiecesRiver;                        //~v@@@I~
        rowRiver=swPortrait ? TBL_RIVERCTR_Y_P : TBL_RIVERCTR_Y_L;	//row of  portrait and landscape//~v@@@I~
        colRiver=swPortrait ? TBL_RIVERCTR_X_P : TBL_RIVERCTR_X_L;	//row of  portrait and landscape//~v@@@I~
        sepW=TBL_RIVER_SPACING_X;                                  //~v@@@I~
        sepH=TBL_RIVER_SPACING_Y;                                  //~v@@@I~
        getPointsRiver();                                          //~v@@@I~
    }
    //*******************************************************************//~v@@@I~
    //*left top of 1st tile for each player                        //~v@@@I~
    //*******************************************************************//~v@@@I~
	public void getPointsRiver()                        //~v@@@I~
    {                                                              //~v@@@I~
    	Point[] points=new Point[PLAYERS];	                               //~v@@@I~
        points[PLAYER_YOU]=table.getRiverPos(PLAYER_YOU);          //~v@@@I~
        points[PLAYER_RIGHT]=table.getRiverPos(PLAYER_RIGHT);      //~v@@@I~
        points[PLAYER_FACING]=table.getRiverPos(PLAYER_FACING);    //~v@@@I~
        points[PLAYER_LEFT]=table.getRiverPos(PLAYER_LEFT);        //~v@@@I~
    	pointsRiver=points;                                        //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*for test, fromGCanvas                                       //~v@@@I~
    //*******************************************************************//~v@@@I~
	public void drawInitial(Canvas Pcanvas)                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("River.drawInitial");             //~v@@@I~
        int ww=pieceW;                                        //~v@@@I~
        int hh=pieceH;                                        //~v@@@I~
        int sepW=TBL_RIVER_SPACING_X;                              //~v@@@I~
        int sepH=TBL_RIVER_SPACING_Y;                              //~v@@@I~
        int ppl=table.riverCtrX;                                   //~v@@@I~
        Bitmap[][][] bmsss=bmsssRiver;    //person,(man,pin,sou,ji),number//~v@@@R~
        for (int jj=0;jj<PLAYERS;jj++)	//person                   //~v@@@I~
        {                                                          //~v@@@I~
        	Bitmap[][] bmss=bmsss[jj];       //4type,numper        //~v@@@I~
        	Point p=table.getRiverPos(jj);                         //~v@@@I~
        	int xx=p.x;                                            //~v@@@I~
        	int yy=p.y;                                            //~v@@@I~
            int xx0=xx;                                            //~v@@@I~
            int yy0=yy;                                            //~v@@@I~
        	for (int ii=0;ii<table.riverCtrY;ii++)                 //~v@@@I~
            {                                                      //~v@@@I~
                Bitmap[] bms = bmss[ii];  //number                 //~v@@@I~
                for (int kk = 0; kk < table.riverCtrX; kk++)       //~v@@@I~
                {                                                  //~v@@@I~
                    Bitmap bm = bms[kk];                           //~v@@@I~
                    Graphics.drawBitmap(Pcanvas,bm, xx, yy);       //~v@@@I~
                    switch (jj)                                    //~v@@@I~
                    {                                              //~v@@@I~
                        case 1:   //right                          //~v@@@I~
                            yy -= sepW + ww;                       //~v@@@I~
                            break;                                 //~v@@@I~
                        case 2:   //facing                         //~v@@@I~
                            xx -= sepW + ww;                       //~v@@@I~
                            break;                                 //~v@@@I~
                        case 3:   //left                           //~v@@@I~
                            yy += sepW + ww;                       //~v@@@I~
                            break;                                 //~v@@@I~
                        default:                                   //~v@@@I~
                            xx += sepW + ww;                       //~v@@@I~
                    }                                              //~v@@@I~
                }                                                  //~v@@@I~
                switch (jj)                                        //~v@@@I~
                {                                                  //~v@@@I~
                    case 1:   //right                              //~v@@@I~
                        yy = yy0;                                  //~v@@@I~
                        xx += sepH + hh;                           //~v@@@I~
                        break;                                     //~v@@@I~
                    case 2:   //facing                             //~v@@@I~
                        xx = xx0;                                  //~v@@@I~
                        yy -= sepH + hh;                           //~v@@@I~
                        break;                                     //~v@@@I~
                    case 3:   //left                               //~v@@@I~
                        yy = yy0;                                  //~v@@@I~
                        xx -= sepH + hh;                           //~v@@@I~
                        break;                                     //~v@@@I~
                    default:                                       //~v@@@I~
                        xx = xx0;                                  //~v@@@I~
                        yy += sepH + hh;                           //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*erase drawn river piece                                     //~v@@@I~
    //*******************************************************************//~v@@@I~
	public void newGame()                                          //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("River.newGame");                 //~v@@@I~
//        for (int ii=0;ii<PLAYERS;ii++)  //person                   //~v@@@R~//~0324R~
//        {                                                          //~v@@@I~//~0324R~
//            Rect r=getPieceRectField(ii);                          //~v@@@I~//~0324R~
//            Graphics.drawRect(r,bgColor);                          //~v@@@I~//~0324R~
////        if (false) //TODO Test                                 //~0324R~
//            if (lastRowRect!=null)                                 //~0323I~//~0324R~
//                Graphics.drawRect(lastRowRect,bgColor);            //~0323I~//~0324R~
//        }                                                          //~v@@@I~//~0324R~
        clearRiver();                                              //~0324I~
        swRiverCleared=false;	//for nextgame                     //~0324I~
        rectDiscarded=null;                                        //~v@@@I~
        bmComplete=null;                                           //~0303I~
        rectComplete=null;                                         //~0303I~
//      lastRowRect=null;                                          //~0324R~
//      Arrays.fill(lastRowCols,0);                                 //~0323I~//~0324R~
    }                                                              //~v@@@I~
    //*******************************************************************//~0324I~
	public boolean  clearRiver()                                   //~0324R~
    {                                                              //~0324I~
    	boolean rc=false;                                                //~0324I~
        if (Dump.Y) Dump.println("River.clearRiver swRiverCleared="+swRiverCleared);//~0324I~
        if (swRiverCleared)    //once called from ScoreDlg         //~0324I~
        	return false;                                                //~0324I~
        swRiverCleared=true;                                       //~0324I~
        for (int ii=0;ii<PLAYERS;ii++)	//person                   //~0324I~
        {                                                          //~0324I~
			Rect r=getPieceRectField(ii);                          //~0324I~
    	    Graphics.drawRect(r,bgColor);                          //~0324I~
            if (lastRowRect!=null)                                 //~0324I~
            {                                                      //~0324I~
	    	    Graphics.drawRect(lastRowRect,bgColor);            //~0324I~
                rc=true;	//overflow detected                    //~0324I~
            }                                                      //~0324I~
        }                                                          //~0324I~
//      rectDiscarded=null;                                        //~0324I~
//      bmComplete=null;                                           //~0324I~
//      rectComplete=null;                                         //~0324I~
        lastRowRect=null;                                          //~0324I~
        Arrays.fill(lastRowCols,0);                                //~0324I~
        if (Dump.Y) Dump.println("River.clearRiver rc="+rc);       //~0324I~
        return rc;                                                 //~0324I~
    }                                                              //~0324I~
	//*********************************************************    //~v@@@M~
	//*discarded pos                                               //~v@@@I~
	//*********************************************************    //~v@@@I~
	private Rect getPieceRect(int Pplayer,int Ppos,boolean Pswstanding)//~v@@@R~//~0323R~
    {                                                              //~v@@@M~
        int xx1,xx2,yy1,yy2,xx0,yy0;                               //~v@@@I~
        int ww,hh,diffW,diffH,posReach,rowReach,colReach;          //~v@@@R~
    //************************                                     //~v@@@R~
        if (Dump.Y) Dump.println("River.getPieceRect player="+Pplayer+",pos="+Ppos+",PswStanding="+Pswstanding);//~v@@@R~
    	int row=Ppos/colRiver;                                     //~v@@@M~
    	int col=Ppos%colRiver;                                     //~v@@@M~
        if (row>=rowRiver)                                         //~v@@@I~
        {                                                          //~v@@@I~
        	row--;                                                 //~v@@@I~
            col+=colRiver;                                         //~v@@@I~
            lastRowCols[Pplayer]=col;                              //~0324I~
        }                                                          //~v@@@I~
    	posReach=players.getPosReach(Pplayer);                     //~v@@@R~
        if (Dump.Y) Dump.println("River.getPieceRect posReach="+posReach);//~v@@@I~
        diffW=0; diffH=0;                                          //~v@@@R~
        ww=pieceW; hh=pieceH;                                      //~v@@@R~
        if (posReach>=0 && posReach<=Ppos)	 //after reach tile    //~v@@@R~
        {                                                          //~v@@@I~
            rowReach=posReach/colRiver;                            //~v@@@I~
            colReach=posReach%colRiver;                            //~v@@@I~
            if (Dump.Y) Dump.println("River.getPieceRect rowReach="+rowReach+",colReach="+colReach);//~v@@@I~
            if (rowReach>=rowRiver)                                //~v@@@I~
            {                                                      //~v@@@I~
                row--;                                             //~v@@@I~
                col+=colRiver;                                     //~v@@@I~
            }                                                      //~v@@@I~
            if (row==rowReach && col>colReach)  //intermediate Lying piece//~v@@@R~
                diffW=pieceH-pieceW;	//add horizontally by previous reach//~v@@@R~
            else                                                   //~v@@@I~
            if (!Pswstanding && Ppos==posReach)     //current is Lying//~v@@@R~
            {                                                      //~v@@@R~
                diffH=pieceH-pieceW;    //shift vertical position for lying tile//~v@@@R~
                ww=pieceH; hh=pieceW;   //lying piece size         //~v@@@R~
            }                                                      //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("River.getPieceRect diffW="+diffW+",diffH="+diffH);//~v@@@R~
                                                                   //~v@@@I~
        Point p=pointsRiver[Pplayer];                               //~v@@@I~
        xx0=p.x;                                                   //~v@@@I~
        yy0=p.y;                                                   //~v@@@I~
        switch(Pplayer)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
            xx1=xx0+(pieceW+sepW)*col+diffW;                       //~v@@@R~
            yy1=yy0+(pieceH+sepH)*row+diffH;                       //~v@@@R~
            xx2=xx1+ww;                                            //~v@@@I~
            yy2=yy1+hh;                                            //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
            xx1=xx0+(pieceH+sepH)*row+diffH;                       //~v@@@R~
            yy2=yy0+pieceW-(pieceW+sepW)*col-diffW;   //yy0 is top left of 1st tile//~v@@@R~
        	xx2=xx1+hh;                                            //~v@@@R~
        	yy1=yy2-ww;                                            //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
            xx2=xx0+pieceW-(pieceW+sepW)*col-diffW;  //xx0 is top left of 1st tile//~v@@@R~
            yy1=yy0-(pieceH+sepH)*row;                             //~v@@@R~
            xx1=xx2-ww;                                            //~v@@@R~
            yy2=yy1+hh;                                            //~v@@@I~
            break;                                                 //~v@@@I~
        default:         //Left                                    //~v@@@R~
            xx1=xx0-(pieceH+sepH)*row;                             //~v@@@R~
            yy1=yy0+(pieceW+sepW)*col+diffW;                       //~v@@@R~
        	xx2=xx1+hh;                                            //~v@@@R~
        	yy2=yy1+ww;                                            //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("River.getPieceRect x1="+xx1+",y1="+yy1+",x2="+xx2+",y2="+yy2);//~v@@@I~
        if (Dump.Y) Dump.println("River.getPieceRect lastRowCols="+Arrays.toString(lastRowCols));//~0323I~//~0324R~
    	Rect rect=new Rect(xx1,yy1,xx2,yy2);                       //~v@@@R~
        return rect;                                               //~v@@@R~
    }                                                              //~v@@@M~
	//*********************************************************    //~v@@@I~
	//*discarded pos to erase for new game                     //~v@@@I~//~0323R~
	//*********************************************************    //~v@@@I~
	private Rect getPieceRectField(int Pplayer)                     //~v@@@I~//~0323R~
    {                                                              //~v@@@I~
        int xx1,xx2,yy1,yy2,xx0,yy0;                               //~v@@@I~
        int ww,hh,diff;                                            //~v@@@I~
        int xxx1=0,xxx2=0,yyy1=0,yyy2=0;	//last row endpos              //~0323I~
    //************************                                     //~v@@@I~
        if (Dump.Y) Dump.println("River.getPieceRectField player="+Pplayer+",lastRowCols="+Arrays.toString(lastRowCols));//~v@@@I~//~0323R~//~0324R~
    	int col=colRiver;                                          //~v@@@I~
    	int row=rowRiver;                                          //~v@@@I~
        ww=pieceW; hh=pieceH;                                      //~v@@@I~
        diff=hh-ww;                                                //~v@@@I~
        Point p=pointsRiver[Pplayer];                              //~v@@@I~
        xx0=p.x;                                                   //~v@@@I~
        yy0=p.y;                                                   //~v@@@I~
        int lastCols=lastRowCols[Pplayer]+1;                               //~0323I~//~0324R~
//      lastCols=RIVER_MAXTILE-colRiver*(rowRiver-1);   //TODO Test//~0324R~
        if (Dump.Y) Dump.println("River.getPieceRectField lastCols="+lastCols);//~0324I~
        int lastEnd;                                               //~0324R~
        switch(Pplayer)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
            xx1=xx0;                                               //~v@@@I~
            yy1=yy0;                                               //~v@@@I~
            xx2=xx1+(pieceW+sepW)*col+diff;                        //~v@@@I~
            yy2=yy1+(pieceH+sepH)*row;                             //~v@@@I~
//            if (lastEnd>0 && lastEnd>xx2)                          //~0323I~//~0324R~
//            {                                                      //~0323I~//~0324R~
//                xxx1=xx1; xxx2=lastEnd; yyy1=yy2-pieceH; yyy2=yy2; //~0323I~//~0324R~
//            }                                                      //~0323I~//~0324R~
//            else                                                   //~0323I~//~0324R~
//                lastEnd=0;                                         //~0323I~//~0324R~
			lastEnd=xx1+(pieceW+sepW)*lastCols+diff;               //~0324R~
            xxx1=xx2; xxx2=lastEnd; yyy1=yy2-pieceH-sepH; yyy2=yy2;//~0324R~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
        	xx1=xx0;                                               //~v@@@I~
            yy2=yy0+ww;                                            //~v@@@I~
            xx2=xx1+(pieceH+sepH)*row;                             //~v@@@I~
            yy1=yy2-(pieceW+sepW)*col-diff;   //yy0 is top left of 1st tile//~v@@@I~
//            if (lastEnd>0 && lastEnd<yy1)                          //~0323I~//~0324R~
//            {                                                      //~0323I~//~0324R~
//                xxx1=xx2-pieceH; xxx2=xx2; yyy1=lastEnd; yyy2=yy1; //~0323I~//~0324R~
//            }                                                      //~0323I~//~0324R~
//            else                                                   //~0323I~//~0324R~
//                lastEnd=0;                                         //~0323I~//~0324R~
			lastEnd=yy2-(pieceW+sepW)*lastCols-diff;               //~0324R~
            xxx1=xx2-pieceH-sepH; xxx2=xx2; yyy1=lastEnd; yyy2=yy1;//~0324R~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
            xx2=xx0+ww;                                            //~v@@@I~
            yy2=yy0+hh;                                            //~v@@@I~
            xx1=xx2-(pieceW+sepW)*col-diff;  //xx0 is top left of 1st tile//~v@@@I~
            yy1=yy2-(pieceH+sepH)*row;                             //~v@@@I~
//            if (lastEnd>0 && lastEnd<xx1)                          //~0323I~//~0324R~
//            {                                                      //~0323I~//~0324R~
//                xxx1=lastEnd; xxx2=xx2; yyy1=yy1; yyy2=yy1+pieceH; //~0323I~//~0324R~
//            }                                                      //~0323I~//~0324R~
//            else                                                   //~0323I~//~0324R~
//                lastEnd=0;                                         //~0323I~//~0324R~
			lastEnd=xx2-(pieceW+sepW)*lastCols-diff;               //~0324R~
            xxx1=lastEnd; xxx2=xx1; yyy1=yy1; yyy2=yy1+pieceH+sepH;//~0324R~
            break;                                                 //~v@@@I~
        default:         //Left                                    //~v@@@I~
            xx2=xx0+hh;                                            //~v@@@I~
            yy1=yy0;                                               //~v@@@I~
            xx1=xx2-(pieceH+sepH)*row;                             //~v@@@I~
            yy2=yy1+(pieceW+sepW)*col+diff;                        //~v@@@I~
//            if (lastEnd>0 && lastEnd>yy2)                          //~0323I~//~0324R~
//            {                                                      //~0323I~//~0324R~
//                xxx1=xx1; xxx2=xx1+pieceH; yyy1=yy1; yyy2=lastEnd; //~0323I~//~0324R~
//            }                                                      //~0323I~//~0324R~
//            else                                                   //~0323I~//~0324R~
//                lastEnd=0;                                         //~0323I~//~0324R~
			lastEnd=yy1+(pieceW+sepW)*lastCols+diff;               //~0324R~
            xxx1=xx1; xxx2=xx1+pieceH+sepH; yyy1=yy2; yyy2=lastEnd;//~0324R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("River.getPieceRect diff="+diff+",ww="+ww+",hh="+hh+",x0="+xx0+",y0="+yy0);//~v@@@I~
    	Rect rect=new Rect(xx1,yy1,xx2,yy2);                       //~v@@@I~
        if (lastCols>colRiver)                                               //~0323I~//~0324R~
            lastRowRect=new Rect(xxx1,yyy1,xxx2,yyy2);             //~0323I~//~0324R~
        else                                                       //~0323I~//~0324R~
            lastRowRect=null;                                      //~0323I~//~0324R~
        if (Dump.Y) Dump.println("River.getPieceRectField lastRowRect="+Utils.toString(lastRowRect));//~0324R~
        if (Dump.Y) Dump.println("River.getPieceRectField rc="+rect.toString());//~0324R~
        return rect;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    private Bitmap getBitmap(int Pplayer,TileData Ptd,Boolean Pswreach)//~v@@@R~
    {                                                              //~v@@@I~
        int player=Pswreach ? Players.nextPlayer(Pplayer) : Pplayer;//~v@@@I~
        if (Dump.Y) Dump.println("River.getBitmap player="+Pplayer+",reach="+Pswreach+",so player is= "+player);//~v@@@I~
        Bitmap bm=pieces.getBitmapRiver(Ptd,player);               //~v@@@R~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
//    //*********************************************************  //~v@@@R~
//    public void discard(TileData Ptile)                          //~v@@@R~
//    {                                                            //~v@@@R~
//        posDiscardedTile=Status.getCurrentTile();   //for reach chk//~v@@@R~
//        int player=Status.getCurrentPlayer();                    //~v@@@R~
//        boolean swReach=(chkReach(player)==REACH_DISCARDED);    //reached then discard//~v@@@R~
//        int pos=players.getDiscardedCtr(player)-1;               //~v@@@R~
//        Rect rect=getPieceRect(player,pos,false/*not force standing*/);//~v@@@R~
//        Bitmap bm=getBitmap(player,Ptile,swReach);               //~v@@@R~
//        canvas=Graphics.lockCanvas(rect);                        //~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            Graphics.drawBitmap(canvas,bm,rect.left,rect.top);   //~v@@@R~
//        }                                                        //~v@@@R~
//        catch(Exception e)                                       //~v@@@R~
//        {                                                        //~v@@@R~
//            Dump.println(e,"River.discard");                     //~v@@@R~
//        }                                                        //~v@@@R~
//        Graphics.unlockCanvas(canvas);                           //~v@@@R~
//    }                                                            //~v@@@R~
	//*********************************************************    //~v@@@I~
	public void drawDiscarded()                                    //~v@@@I~//~0401R~
    {                                                              //~v@@@I~
		drawDiscarded(false/*erase*/);                             //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*TODO for test                                               //~v@@@I~
	//*********************************************************    //~v@@@I~
	private void drawDiscarded(boolean Pswerase)                    //~v@@@R~//~0401R~
    {                                                              //~v@@@I~
        TileData td=players.tileLastDiscarded;                     //~v@@@R~
        if (td==null)                                              //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("River.drawDiscarded tile=null Pswerase="+Pswerase);//~v@@@I~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("River.drawDiscarded tile player="+td.player+",eswn="+td.eswn+",type="+td.type+",num="+td.number+",flag="+Integer.toHexString(td.flag));//~v@@@R~
//  	posDiscardedTile=Status.getCurrentTile();	//for reach chk//~v@@@R~
        int player=td.getPlayer();                                 //~v@@@I~
        int pos=players.getDiscardedCtr(player)-1;                 //~v@@@R~
        boolean swReach=chkReachPos(player,pos); 	//reached then discard//~v@@@I~
    	Rect rect=getPieceRect(player,pos,false/*not force standing*/);//~v@@@I~
        Bitmap bm=getBitmap(player,td,swReach);                 //~v@@@I~
        	if (Pswerase)                                          //~v@@@I~
            {                                                      //~v@@@I~
    	        Graphics.drawRect(rect,bgColor);                   //~v@@@I~
				rectDiscarded=null;                                   //~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
            	Graphics.drawBitmap(bm,rect.left,rect.top);        //~v@@@I~
            	if ((td.flag & TDF_TAKEN_RIVER)!=0)                //~v@@@R~
                {                                                  //~v@@@I~
    	        	Graphics.drawRect(rect,COLOR_FG_DISABLE);      //~v@@@I~
					rectDiscarded=null ;                               //~v@@@I~
                }                                                  //~v@@@I~
                if (swComplete)                                    //~v@@@I~
                {                                                  //~v@@@I~
                	bmComplete=bm;                                 //~0303I~
                	rectComplete=rect;                             //~0303I~
//  	        	Graphics.drawRect(rect,COMPLETE_COLOR,COMPLETE_STROKE_WIDTH);//~v@@@I~//~0401R~
    	        	Graphics.drawRect(rect,COMPLETE_COLOR,stroke_width_river);//~0401I~
        			if (Dump.Y) Dump.println("River.drawDiscarded swComplete=T stroke_width="+stroke_width_river);//~0401I~
					rectDiscarded=null;                               //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
    }                                                              //~v@@@I~
//    //*********************************************************  //~v@@@R~
//    public void drawDiscarded(int PplayerErase)                  //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("River.drawDiscarded no tile parm dplayerErase="+PplayerErase);//~v@@@R~
//        drawDiscarded(players.tileLastDiscarded,PplayerErase);   //~v@@@R~
//    }                                                            //~v@@@R~
	//*********************************************************    //~v@@@I~
	public void drawDiscarded(int Pplayer,TileData Ptd,int PplayerErase)//~v@@@R~
    {                                                              //~v@@@I~
	    if (Dump.Y) Dump.println("River.drawDiscarded tile parm player="+Pplayer+",playerErase="+PplayerErase);//~v@@@R~
		drawDiscarded(Pplayer,Ptd);                                        //~v@@@I~
        if (Ptd!=null && (Ptd.flag & TDF_TAKEN_RIVER)!=0)          //~v@@@I~
	        if (PplayerErase>=0)                                   //~v@@@R~
				eraseDiscarded(PplayerErase);                      //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*no erase function                                           //~v@@@I~
	//*********************************************************    //~v@@@I~
	public void drawDiscarded(int Pplayer,TileData Ptd)            //~v@@@R~
    {                                                              //~v@@@I~
	    if (Dump.Y) Dump.println("River.drawDiscarded tile parm player="+Pplayer+",swComplete="+swComplete);//~v@@@R~
//      TileData td=players.tileLastDiscarded;                     //~v@@@I~
        TileData td=Ptd;                                           //~v@@@I~
        if (td==null)                                              //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("River.drawDiscarded tile=null");//~v@@@I~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("River.drawDiscarded tile type="+td.type+",num="+td.number+",flag="+td.flag);//~v@@@I~
//      int player=td.getPlayer();                                 //~v@@@R~
        int player=Pplayer;                                        //~v@@@I~
        int pos=players.getDiscardedCtr(player)-1;                 //~v@@@I~
        boolean swReach=chkReachPos(player,pos); 	//reached then discard//~v@@@I~
    	Rect rect=getPieceRect(player,pos,false/*not force standing*/);//~v@@@I~
        Bitmap bm=getBitmap(player,td,swReach);                    //~v@@@I~
//      Graphics.drawBitmap(canvas,bm,rect.left,rect.top);         //~v@@@R~
        Graphics.drawBitmap(bm,rect.left,rect.top);                //~v@@@I~
        bmDiscarded=bm;                                            //~v@@@I~
		rectDiscarded=null;                                           //~v@@@I~
        if ((td.flag & TDF_TAKEN_RIVER)!=0)                        //~v@@@I~
//          Graphics.drawRect(canvas,rect,COLOR_FG_DISABLE);       //~v@@@R~
            Graphics.drawRect(rect,COLOR_FG_DISABLE);              //~v@@@I~
//      else                                                       //~v@@@R~
        if (swComplete)                                            //~v@@@R~
        {                                                          //~0303I~
//          Graphics.drawRect(canvas,rect,COMPLETE_COLOR,COMPLETE_STROKE_WIDTH);//~v@@@R~
//          Graphics.drawRect(rect,COMPLETE_COLOR,COMPLETE_STROKE_WIDTH);//~v@@@I~//~0401R~
            Graphics.drawRect(rect,COMPLETE_COLOR,stroke_width_river);//~0401I~
        	if (Dump.Y) Dump.println("River.drawDiscarded swComplete=T stroke_width="+stroke_width_river);//~0401I~
            bmComplete=bm;                                         //~0303I~
            rectComplete=rect;                                     //~0303I~
        }                                                          //~0303I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
		    rectDiscarded=rect;	//redraw frame when timeout changed//~v@@@I~
			drawFrameDiscardedTile(GCM_DISCARD);                   //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	public void eraseDiscarded(int Pplayer)                        //~v@@@I~
    {                                                              //~v@@@I~
	    if (Dump.Y) Dump.println("River.eraseDiscarded player="+Pplayer);//~v@@@I~
        int player=Pplayer;                                        //~v@@@I~
        int pos=players.getDiscardedCtr(player)-1;                 //~v@@@I~
        boolean swReach=chkReachPos(player,pos); 	//reached then discard//~v@@@I~
    	Rect rect=getPieceRect(player,pos,false/*not force standing*/);//~v@@@I~
//  	Graphics.drawRect(canvas,rect,bgColor);                    //~v@@@R~
    	Graphics.drawRect(rect,bgColor);                           //~v@@@I~
		rectDiscarded=null;                                           //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*from UserAction                                             //~v@@@I~
	//*********************************************************    //~v@@@I~
	public void reach(int Pplayer)                          //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("River.reach Pplayer="+Pplayer);  //~v@@@R~
        if (!chkReach(Pplayer)) 	//not "reach after discard"    //~v@@@R~
        	return;                                                //~v@@@R~
        int pos=players.getDiscardedCtr(Pplayer);                  //~v@@@R~
    	Rect rectBG=getPieceRect(Pplayer,pos-1,true/*force standing*/);	//erase rect//~v@@@R~
    	Rect rect=getPieceRect(Pplayer,pos-1,false);               //~v@@@R~
        TileData tile=players.getDiscardedTile(Pplayer);           //~v@@@R~
        Bitmap bm=getBitmap(Pplayer,tile,true/*Lying*/);           //~v@@@R~
        //**erase the piece before reach                           //~v@@@I~
//        canvas=Graphics.lockCanvas(rectBG);                      //~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            Graphics.drawRect(canvas,rectBG,bgColor);            //~v@@@R~
//        }                                                        //~v@@@R~
//        catch(Exception e)                                       //~v@@@R~
//        {                                                        //~v@@@R~
//            Dump.println(e,"River.discard");                     //~v@@@R~
//        }                                                        //~v@@@R~
//        Graphics.unlockCanvas(canvas);                           //~v@@@R~
        Graphics.drawRect(rectBG,bgColor);                         //~v@@@I~
        //**draw reach piece                                       //~v@@@I~
//        canvas=Graphics.lockCanvas(rect);                        //~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            Graphics.drawBitmap(canvas,bm,rect.left,rect.top);   //~v@@@R~
//        }                                                        //~v@@@R~
//        catch(Exception e)                                       //~v@@@R~
//        {                                                        //~v@@@R~
//            Dump.println(e,"River.discard");                     //~v@@@R~
//        }                                                        //~v@@@R~
//        Graphics.unlockCanvas(canvas);                           //~v@@@R~
        Graphics.drawBitmap(rect,bm);                              //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    private boolean chkReach(int Pplayer)                              //~v@@@R~
    {                                                              //~v@@@I~
    	boolean rc=players.getReachStatus(Pplayer)==REACH_DONE;    //~v@@@R~
        if (Dump.Y) Dump.println("River.chkReach rc="+rc);         //~v@@@R~
    	return rc;                                                 //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    private boolean chkReachPos(int Pplayer,int Ppos/*discardctr-1*/)//~v@@@I~
    {                                                              //~v@@@I~
    	boolean rc=players.getReachStatus(Pplayer)==REACH_DONE;    //~v@@@R~
        int posReach=players.getPosReach(Pplayer);                 //~v@@@R~
        rc=(rc && posReach==Ppos);                                 //~v@@@I~
        if (Dump.Y) Dump.println("River.chkReachPos rc="+rc+",posReach="+posReach+",curpos="+Ppos);//~v@@@R~
    	return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public TileData eraseTaken()                                   //~v@@@R~
    {                                                              //~v@@@I~
        TileData td=players.tileLastDiscarded;                     //~v@@@R~
        if (Dump.Y) Dump.println("River.eraseTaken");              //~v@@@I~
        if (td!=null && (td.flag & TDF_TAKEN_RIVER)!=0)            //~v@@@R~
        {                                                          //~v@@@I~
        	drawDiscarded(true/*erase*/);                          //~v@@@R~
	        if (Dump.Y) Dump.println("River.eraseTaken td type="+td.type+",number="+td.number);//~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
            td=null;                                               //~v@@@I~
        return td;                                                 //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  takePon(int Pplayer)                             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("River.takePon player="+Pplayer); //~v@@@I~
//      TileData td=players.tileLastDiscarded;                     //~v@@@R~
//      td.setTaken(false/*not kan*/);                             //~v@@@R~
        drawDiscarded();                                           //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  takeChii(int Pplayer)                            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("River.takeChii player="+Pplayer);//~v@@@I~
//      takePon(Pplayer);                                          //~v@@@I~
        drawDiscarded();                                           //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  takeKan(int Pplayer,int Pkantype)                //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("River.takeKan player="+Pplayer+",kantype=="+Pkantype);//~v@@@R~
        if (Pkantype==KAN_RIVER)                                   //~v@@@I~
	        drawDiscarded();                                       //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  complete(int Pplayer,int Pflag)                  //~v@@@I~
    {                                                              //~v@@@I~
    	TileData td=null;                                          //~v@@@I~
        if (Dump.Y) Dump.println("River.complete player="+Pplayer+",flag="+Pflag);//~v@@@I~
//      if ((Pflag & COMPLETE_KAN_ADD)!=0)	//ron by not on river  //~v@@@R~
        if ((Pflag & (COMPLETE_RIVER|COMPLETE_KAN_RIVER))==0)	//ron by not on river//~v@@@R~
        {                                                          //~v@@@I~
	        return;                                                //~v@@@I~
        }                                                          //~v@@@I~
//        if ((Pflag & COMPLETE_TAKEN)==0)    //river              //~v@@@R~
//        {                                                        //~v@@@R~
	        td=players.getLastDiscarded(); //show tile in hand     //~v@@@I~
//        }                                                        //~v@@@R~
        if (td==null)	//no tile to show as target in hand        //~v@@@I~
        	return;                                                //~v@@@I~
        int player=td.getPlayer();                                 //~v@@@I~
        swComplete=true;                                           //~v@@@I~
        drawDiscarded(player,td);                                  //~v@@@R~
        swComplete=false;                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*positiondata to be send                                     //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  int[] getTilesSetup()                                  //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("River.getTilesSetup");           //~v@@@I~
		TileData[] tds=tdsSetup;                                    //~v@@@R~
    	int sz=tds.length;                                         //~v@@@R~
        int[] tiles=new int[sz*2];    //type+number                //~v@@@R~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@I~
        {                                                          //~v@@@I~
        	tiles[ii*2]=tds[ii].type;                              //~v@@@R~
        	tiles[ii*2+1]=tds[ii].number;                          //~v@@@R~
        }                                                          //~v@@@I~
        return tiles;                                              //~v@@@I~
    }                                                              //~v@@@I~
	//*************************************************************//~v@@@I~
	//*set setupdata for on client                                 //~v@@@I~
	//*************************************************************//~v@@@I~
    public  void setTilesSetup(int Picker,int Pspot,int[] PtilesSetup)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("River.setTilesSetup spot="+Pspot);//~v@@@R~
    	int sz=PtilesSetup.length/2;                               //~v@@@R~
        TileData[] tds=new TileData[sz];    //type+number        //~v@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@I~
        {                                                          //~v@@@I~
//      	tds[ii]=new TileData(PtilesSetup[ii*2+1]/*number*/,PtilesSetup[ii*2]/*type*/,false/*dora*/);//~v@@@R~
        	tds[ii]=new TileData(PtilesSetup,ii*2,false/*dora*/);  //~v@@@I~
	        if (Dump.Y) Dump.println("River.setTilesSetup type="+tds[ii].type+",num="+tds[ii].number);//~v@@@I~
        }                                                          //~v@@@I~
		tdsSetup=tds;                                              //~v@@@R~
        sortedSetupTiles=sortSetupTile(Picker,(Pspot%2==1));       //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  setup(int PstartingPlayer,boolean PswFaceup)     //~v@@@I~
    {                                                              //~v@@@I~
        TileData[] tds;                                            //~v@@@I~
        int xx,yy,xx0,yy0,ww,stepX,stepY,stepX2,stepY2,ctr;        //~v@@@I~
    //****************************                                 //~v@@@I~
	    if (Dump.Y) Dump.println("River.setup startingPlayer="+PstartingPlayer+",swFaceup="+PswFaceup);//~v@@@I~
    	int player=Players.nextPlayer(PstartingPlayer,PLAYER_FACING);//~v@@@I~
        if (tdsSetup==null)                                        //~v@@@I~
	        tdsSetup=getTilesSetup(player);                               //~v@@@I~
        tds=tdsSetup;                                               //~v@@@I~
    	Rect rect=getRectSetup(player);                            //~v@@@I~
        rectSetupTiles=rect;                                       //~v@@@I~
        ww=table.riverPieceW;                                      //~v@@@I~
        switch(player)                                             //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
        	xx0=rect.left;                                         //~v@@@I~
        	yy0=rect.top;                                          //~v@@@I~
            stepX=(ww+PIECE_SPACING);                              //~v@@@I~
            stepY=0;                                               //~v@@@I~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
        	xx0=rect.left;                                         //~v@@@I~
        	yy0=rect.bottom-ww;                                    //~v@@@I~
            stepX=0;                                               //~v@@@I~
            stepY=-(ww+PIECE_SPACING);                             //~v@@@I~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
        	xx0=rect.right-ww;                                     //~v@@@I~
        	yy0=rect.top;                                          //~v@@@I~
            stepX=-(ww+PIECE_SPACING);                             //~v@@@I~
            stepY=0;                                               //~v@@@I~
            break;                                                 //~v@@@I~
        default: //left                                            //~v@@@I~
        	xx0=rect.left;                                         //~v@@@I~
        	yy0=rect.top;                                          //~v@@@I~
            stepX=0;                                               //~v@@@I~
            stepY=ww+PIECE_SPACING;                                //~v@@@I~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
//        canvas=Graphics.lockCanvas(rect);                        //~v@@@R~
//        try                                                        //~v@@@I~
//        {                                                          //~v@@@I~
            xx=xx0; yy=yy0;                                        //~v@@@I~
        	for (TileData td:tds)                                  //~v@@@I~
        	{                                                      //~v@@@I~
	        	Bitmap bm=getBitmapSetup(player,td,PswFaceup);     //~v@@@I~
//          	Graphics.drawBitmap(canvas,bm,xx,yy);              //~v@@@R~
            	Graphics.drawBitmap(bm,xx,yy);                     //~v@@@I~
                xx+=stepX; yy+=stepY;                              //~v@@@I~
        	}                                                      //~v@@@I~
//        }                                                          //~v@@@I~
//        catch(Exception e)                                       //~v@@@R~
//        {                                                        //~v@@@R~
//            Dump.println(e,"River.discard");                     //~v@@@R~
//        }                                                        //~v@@@R~
//        Graphics.unlockCanvas(canvas);                           //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
    private Rect getRectSetup(int Pplayer)                         //~v@@@I~
    {                                                              //~v@@@I~
    	Rect r;                                                    //~v@@@I~
    //*left top of 1st tile for each player                        //~v@@@I~
    	Point p=pointsRiver[Pplayer];                              //~v@@@I~
        int xx=p.x, yy=p.y;                                        //~v@@@I~
        int ww=pieceW, hh=pieceH;                                  //~v@@@I~
        int len=(ww+TBL_RIVER_SPACING_X)*CTR_SETUP;                //~v@@@R~
        int adjust=((colRiver-CTR_SETUP)/2)*(ww+TBL_RIVER_SPACING_X);		//if landscape colRiver=10//~v@@@R~
        switch(Pplayer)                                             //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
        	r=new Rect(xx+adjust ,yy, xx+adjust+len,yy+hh);        //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
        	r=new Rect(xx ,yy+ww-adjust-len, xx+hh,yy+ww-adjust);  //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
        	r=new Rect(xx+ww-adjust-len,yy,xx+ww-adjust,yy+hh);    //~v@@@R~
            break;                                                 //~v@@@I~
        default: //left                                            //~v@@@I~
        	r=new Rect(xx,yy+adjust,xx+hh,yy+adjust+len);          //~v@@@R~
        }                                                          //~v@@@I~
        return r;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
    private TileData[] getTilesSetup(int Pplayer)                          //~v@@@I~
    {                                                              //~v@@@I~
    	int rand;                                                  //~v@@@I~
		int outctr=0,loopctr=0;                                    //~v@@@M~
    //************************************                         //~v@@@I~
    	TileData[] tds=new TileData[CTR_SETUP];                    //~v@@@I~
        rand = Utils.getRandom(2);                                 //~v@@@I~
        if (rand==0)                                               //~v@@@I~
        {                                                          //~v@@@I~
//      	tds[0]=new TileData(0,TT_PIN,false);                   //~v@@@R~
        	tds[0]=new TileData(TT_PIN,0,false);                   //~v@@@I~
//      	tds[CTR_SETUP-1]=new TileData(1,TT_PIN,false);         //~v@@@R~
        	tds[CTR_SETUP-1]=new TileData(TT_PIN,1,false);         //~v@@@I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
//      	tds[0]=new TileData(1,TT_PIN,false);                   //~v@@@R~
        	tds[0]=new TileData(TT_PIN,1,false);                   //~v@@@I~
//      	tds[CTR_SETUP-1]=new TileData(0,TT_PIN,false);         //~v@@@R~
        	tds[CTR_SETUP-1]=new TileData(TT_PIN,0,false);         //~v@@@I~
        }                                                          //~v@@@I~
    	TileData[] tdswk=new TileData[TT_4ESWN_CTR];               //~v@@@R~
        tdswk[0]=new TileData(TT_JI,TT_4ESWN_E,false);             //~v@@@R~
        tdswk[1]=new TileData(TT_JI,TT_4ESWN_S,false);             //~v@@@R~
        tdswk[2]=new TileData(TT_JI,TT_4ESWN_W,false);             //~v@@@R~
        tdswk[3]=new TileData(TT_JI,TT_4ESWN_N,false);             //~v@@@R~
        //*shuffle eswn                                            //~v@@@I~
        while (outctr < TT_4ESWN_CTR)                              //~v@@@R~
        {                                                          //~v@@@I~
            rand = Utils.getRandom(TT_4ESWN_CTR);              //~v@@@R~
            loopctr++;                                             //~v@@@I~
            if (Dump.Y) Dump.println("River.shuffleSetup rand="+rand);//~v@@@I~
            if (tdswk[rand]!=null)                                  //~v@@@I~
            {                                                      //~v@@@I~
	            if (Dump.Y) Dump.println("River.shuffleSetup selected outctr="+outctr+",loopctr="+loopctr+",type="+tdswk[rand].type+",no="+tdswk[rand].number);//~v@@@I~
            	tds[1+outctr++]=tdswk[rand];                       //~v@@@R~
                tdswk[rand]=null;                                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        return tds;                                             //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    private Bitmap getBitmapSetup(int Pplayer,TileData Ptd,Boolean PswFaceup)//~v@@@I~
    {                                                              //~v@@@I~
        Bitmap bm;                                                 //~v@@@I~
        if (Dump.Y) Dump.println("River.getBitmapSetup player="+Pplayer+",swFaceup="+PswFaceup+",type="+Ptd.type+",no="+Ptd.number);//~v@@@R~
        if (PswFaceup)                                             //~v@@@I~
        	bm=pieces.getBitmapRiver(Ptd,Pplayer);                 //~v@@@I~
        else                                                       //~v@@@I~
    		bm=pieces.getBitmapHandPairStock(Pplayer);             //~v@@@I~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*prepare to pick(sort tile ESWN)                             //~v@@@R~
	//*if Odd: start from 1-Pin side else 2-Pin side               //~v@@@I~
	//*********************************************************    //~v@@@I~
    private TileData[]  sortSetupTile(int P1stPicker,boolean PswOdd)//~v@@@R~
    {                                                              //~v@@@I~
    	int dest,pos;                                              //~v@@@R~
    //******************                                           //~v@@@I~
    	if (PswOdd)                                                //~v@@@I~
			dest=tdsSetup[0].number==0 ? 1 : -1;      //pin1 start //~v@@@R~
        else                                                       //~v@@@I~
			dest=tdsSetup[0].number==0 ? -1 : 1;                   //~v@@@I~
        if (dest==1)                                               //~v@@@I~
        	pos=1;                                                 //~v@@@I~
        else                                                       //~v@@@I~
        	pos=4;                                                 //~v@@@I~
        TileData[] tdsout=new TileData[PLAYERS];                   //~v@@@R~
        for (int ii=0;ii<PLAYERS;ii++,pos+=dest)                   //~v@@@R~
        {                                                          //~v@@@I~
        	tdsout[ii]=tdsSetup[pos];                              //~v@@@I~
	        if (Dump.Y) Dump.println("River.sortSetupTile ii="+ii+",type="+tdsout[ii].type+",num="+tdsout[ii].number);//~v@@@I~
        }
        return tdsout;//~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*pickup eswn piece sequencially                              //~v@@@R~
	//*********************************************************    //~v@@@I~
    public int get1stPicker(int PplayerTempStarter,int Pspot)      //~v@@@I~
    {                                                              //~v@@@I~
        boolean swOdd=(Pspot%2==1);                                //~v@@@I~
        int pos=Players.playerByDice(Pspot);                            //~v@@@I~
        int player1stPicker=Players.nextPlayer(PplayerTempStarter,pos);//~v@@@I~
        sortedSetupTiles=sortSetupTile(player1stPicker,swOdd);     //~v@@@R~
        if (Dump.Y) Dump.println("River.get1stPicker player="+PplayerTempStarter+",spot="+Pspot+",1stpicker="+player1stPicker);//~v@@@R~
 //       setPlayerPosition(sortedSetupTiles,player1stPicker);       //~v@@@I~
	    return player1stPicker;                                    //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*pickup eswn piece sequencially                              //~v@@@R~
	//*********************************************************    //~v@@@I~
    public int[] getPlayerPosition(int P1stPicker)                 //~v@@@I~
    {                                                              //~v@@@I~
    	int[] posPlayer=new int[PLAYERS];                           //~v@@@I~
        int p=P1stPicker;                                           //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	TileData td=sortedSetupTiles[ii];                      //~v@@@R~
            posPlayer[td.number]=p;                                //~v@@@I~
	        if (Dump.Y) Dump.println("River.getPlayerPosition player="+p+",pos="+td.number);//~v@@@R~
            p=Players.nextPlayer(p);                               //~v@@@I~
        }                                                          //~v@@@I~
	    if (Dump.Y) Dump.println("River.getPlayerPosition posPlayer="+Arrays.toString(posPlayer));//~v@@@R~
        if ((TestOption.option2 & TestOption.TO2_FINAL_GAME)!=0)   //~v@@@I~
        if (TestOption.firstDealer!=0)	//swap test dealer vs East //~v@@@I~
        {                                                          //~v@@@I~
        	int testdealer=TestOption.firstDealer-1;    //0:no,1:N1=E,2:N2=E,3:N1=N,4:N2:N//~v@@@R~
            if (testdealer<=1)                                     //~v@@@I~
            {                                                      //~v@@@I~
        		int dealer=posPlayer[0];  //East                   //~v@@@R~
                for (int ii=0;ii<PLAYERS;ii++)                     //~v@@@R~
                    if (posPlayer[ii]==testdealer)                 //~v@@@R~
                        posPlayer[ii]=dealer;                      //~v@@@R~
                posPlayer[0]=testdealer;                           //~v@@@R~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
            	testdealer-=2;                                     //~v@@@I~
        		int last=posPlayer[3];  //North                    //~v@@@I~
                for (int ii=0;ii<PLAYERS;ii++)                     //~v@@@I~
                    if (posPlayer[ii]==testdealer)                 //~v@@@I~
                        posPlayer[ii]=last;                        //~v@@@I~
                posPlayer[3]=testdealer;                           //~v@@@I~
            }                                                      //~v@@@I~
	    	if (Dump.Y) Dump.println("River.getPlayerPosition firstDealer="+testdealer+",posPlayer="+Arrays.toString(posPlayer));//~v@@@R~
        }                                                          //~v@@@I~
	    if (Dump.Y) Dump.println("River.getPlayerPosition posPlayer="+Arrays.toString(posPlayer));//~v@@@I~
        return posPlayer;                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*pickup eswn piece sequencially                              //~v@@@R~
	//*********************************************************    //~v@@@I~
    public boolean setupAccepted(int Pplayer)                      //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("River.setupAccepted player="+Pplayer+",ctrSelected="+ctrSelected);//~v@@@M~
    	TileData td=sortedSetupTiles[ctrSelected++];                //~v@@@I~
    	Rect rect=getRectSetupAccept(Pplayer);                      //~v@@@I~
        Bitmap bm=getBitmapSetup(Pplayer,td,true);                 //~v@@@I~
//        canvas=Graphics.lockCanvas(rect);                        //~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            Graphics.drawBitmap(canvas,bm,rect.left,rect.top);   //~v@@@R~
//        }                                                        //~v@@@R~
//        catch(Exception e)                                       //~v@@@R~
//        {                                                        //~v@@@R~
//            Dump.println(e,"River.discard");                     //~v@@@R~
//        }                                                        //~v@@@R~
//        Graphics.unlockCanvas(canvas);                           //~v@@@R~
        Graphics.drawBitmap(rect,bm);                          //~v@@@I~
   		Sound.play(SOUNDID_TAKE,false/*not change to beep when beeponly option is on*/);//+va05I~
        return ctrSelected==PLAYERS;                               //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
    private Rect getRectSetupAccept(int Pplayer)                   //~v@@@I~
    {                                                              //~v@@@I~
    	Rect r=AG.aStock.rectsBG[Pplayer];                                   //~v@@@R~
    	Rect ro;                                                   //~v@@@I~
        int ww=pieceW,hh=pieceH;                                   //~v@@@R~
        switch(Pplayer)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
        	ro=new Rect(r.right,   r.top,      r.right+ww,   r.top+hh);//~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
        	ro=new Rect(r.left,    r.top-ww,    r.left+hh,   r.top);//~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
        	ro=new Rect(r.left-ww, r.bottom-hh, r.left,      r.bottom);//~v@@@R~
            break;                                                 //~v@@@I~
        default: //left                                            //~v@@@I~
        	ro=new Rect(r.right-hh,r.bottom, r.right,        r.bottom+ww);//~v@@@R~
        }                                                          //~v@@@I~
        return ro;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*erase eswn tile                                             //~v@@@I~
	//*********************************************************    //~v@@@I~
    public void endOfPositioning()                                 //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("River.endOfPositioning");        //~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
	    	Rect rect=getRectSetupAccept(ii);                      //~v@@@I~
	        Graphics.drawRect(rect,bgColor);                       //~v@@@I~
	    }                                                          //~v@@@I~
	    Graphics.drawRect(rectSetupTiles,bgColor);                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*draw discarded tile when timeout                            //~v@@@I~
	//*msgid:GCM_DISCARD,GCM_NEXT_PLAYER_PONKAN,GCM_NEXTP_LAYER    //~v@@@I~
	//*********************************************************    //~v@@@I~
	public void drawFrameDiscardedTile(int Pmsgid)                 //~v@@@I~
    {                                                              //~v@@@I~
    	int color=0;                                               //~v@@@I~
    	switch(Pmsgid)                                             //~v@@@I~
        {                                                          //~v@@@I~
        case GCM_NEXT_PLAYER_PONKAN:                               //~v@@@I~
            color=COLOR_NEXT_PLAYER_PONKAN;                        //~v@@@I~
        	break;                                                 //~v@@@I~
        case GCM_NEXT_PLAYER:                                      //~v@@@I~
            color=COLOR_NEXT_PLAYER;                               //~v@@@I~
        	break;                                                 //~v@@@I~
        case GCM_DISCARD:                                          //~v@@@I~
            color=COLOR_DISCARDED;                                 //~v@@@I~
        	break;                                                 //~v@@@I~
        case GCM_TAKE:                                             //~v@@@I~
            if (rectDiscarded!=null)                                 //~v@@@I~
            {                                                      //~v@@@I~
    	        Graphics.drawRect(rectDiscarded,bgColor);          //~v@@@I~
        		Graphics.drawBitmap(bmDiscarded,rectDiscarded.left,rectDiscarded.top);//~v@@@I~
	            rectDiscarded=null;	//skip frame draw              //~v@@@I~
            }                                                      //~v@@@I~
        	break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
	    if (Dump.Y) Dump.println("River.drawFrameDiscardedTile msgid="+Pmsgid+",color="+Integer.toHexString(color)+",rect="+Utils.toString(rectDiscarded));//~v@@@I~
        if (color!=0 && rectDiscarded!=null)                       //~v@@@I~
        {                                                          //~v@@@I~
//  		Graphics.drawRect(rectDiscarded,color,COMPLETE_STROKE_WIDTH);//~v@@@R~//~0401R~
    		Graphics.drawRect(rectDiscarded,color,stroke_width_river);//~0401I~
            if (Dump.Y) Dump.println("River.drawFrameDiscardedTile stroke_width="+stroke_width_river);//~0401I~
            if (Pmsgid==GCM_NEXT_PLAYER_PONKAN)                    //~v@@@I~
            {                                                      //~v@@@I~
//          	Rect r=new Rect(rectDiscarded.left+COMPLETE_STROKE_WIDTH-1,//~v@@@R~//~0401R~
//          	                rectDiscarded.top+COMPLETE_STROKE_WIDTH-1,//~v@@@R~//~0401R~
//          	                rectDiscarded.right-COMPLETE_STROKE_WIDTH+1,//~v@@@R~//~0401R~
//          	                rectDiscarded.bottom-COMPLETE_STROKE_WIDTH+1);//~v@@@R~//~0401R~
            	Rect r=new Rect(rectDiscarded.left+stroke_width_river-1,//~0401I~
            	                rectDiscarded.top+stroke_width_river-1,//~0401I~
            	                rectDiscarded.right-stroke_width_river+1,//~0401I~
            	                rectDiscarded.bottom-stroke_width_river+1);//~0401I~
	    		Graphics.drawRect(r,COLOR_INNER_FRAME,1);          //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~0303I~
	//*from Complete.resetCompleteNextPlayer                       //~0303I~
	//*erase frame of winning tile                                 //~0303I~
	//*********************************************************    //~0303I~
	public void resetComplete()                                    //~0303I~
    {                                                              //~0303I~
	    if (Dump.Y) Dump.println("River.resetComplete rectComplete="+Utils.toString(rectComplete));//~0303I~
        if (rectComplete!=null)                                    //~0303I~
        {                                                          //~0303I~
        	Graphics.drawBitmap(bmComplete,rectComplete.left,rectComplete.top);//~0303I~
            bmComplete=null;                                       //~0303I~
            rectComplete=null;                                     //~0303I~
        }                                                          //~0303I~
    }                                                              //~0303I~
}//class River                                                 //~dataR~//~@@@@R~//~v@@@R~
