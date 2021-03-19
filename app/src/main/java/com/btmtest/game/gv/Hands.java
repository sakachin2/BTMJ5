//*CID://+va65R~: update#= 771;                                    //~v@@@R~//~v@@5R~//~9A12R~//~va60R~//~va65R~
//**********************************************************************//~v101I~
//2021/02/01 va65 testoption of open hand for discardSmart test    //~va65I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//v@@5 20190126 player means position on the device                //~v@@5I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.btmtest.TestOption;
import com.btmtest.game.Players;
import com.btmtest.game.Tiles;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.TestOption.*;
import static com.btmtest.game.Complete.*;
import static com.btmtest.game.GConst.*;                           //~v@@@R~
import static com.btmtest.game.gv.Pieces.*;
import static com.btmtest.game.gv.MJTable.*;
import static com.btmtest.game.Tiles.*;                            //~v@@@I~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~


public class Hands                                                 //~v@@@R~
{                                                                  //~0914I~
	private static final int SPACE_HANDS_EARTH_YOU=10; 	//space between hands and earth//~v@@@R~
    private static final boolean[][] swVerticalEarthss={           //~v@@@I~
    													{false,false,false,false}, //landscape//~v@@@R~
//  													{true, true, true ,true }, //portrait//~v@@@R~//~v@@5R~
    													{false,false,false,false}, //landscape//~v@@5I~
                                                     };             //~v@@@I~
	private GCanvas gcanvas;                                       //~v@@@I~
    private MJTable table;                                         //~v@@@I~
    private Pieces pieces;//~v@@@I~
    private int pieceW,pieceH;                                     //~v@@@I~
    private int piecePairW,piecePairH;                             //~v@@@I~
    private Bitmap[][][] bmsssHands;  //[standing/lying][man/pin/sou/ji][number]//~v@@@R~
    private Bitmap[][] bmssHands;                                  //~v@@@R~
    public Rect rectHands;                                         //~v@@@R~
    private Rect rectHands0;                                       //~0325I~
    private int bgColor;                                           //~v@@@I~
    private int HH,WW;	//scrWidth,scrHeight                       //~v@@@I~
    public boolean swPortrait;                                     //~v@@@R~
    private int rightHandsYou;	//right boudary of Hands when taken from river//~v@@@I~
    private int shiftHands;                                        //~v@@@I~
    public boolean[] swVerticalEarths;                             //~v@@@R~
    private Players players;                                       //~v@@@I~
    private boolean swComplete;                                    //~v@@@I~
    private Rect[] openRects;                                      //~v@@@R~
    private Rect[] openRects0;                                     //~0328I~
    private HandsTouch handsTouch;                                 //~v@@@I~
    private static final int[][] displayPieces={                   //~v@@@I~
					{TT_MAN,1},{TT_MAN,9},                         //~v@@@I~
					{TT_PIN,1},{TT_PIN,9},                         //~v@@@I~
					{TT_SOU,1},{TT_SOU,9},                         //~v@@@I~
					{TT_JI,TT_4ESWN_E},{TT_JI,TT_4ESWN_S},{TT_JI,TT_4ESWN_W},{TT_JI,TT_4ESWN_N},//~v@@@I~
					{TT_JI,TT_4ESWN_CTR+TT_3WGR_W},{TT_JI,TT_4ESWN_CTR+TT_3WGR_G},{TT_JI,TT_4ESWN_CTR+TT_3WGR_R},//~v@@@I~
					{TT_JI,TT_4ESWN_E},                            //~v@@@I~
                    };                                             //~v@@@I~
    private Bitmap[] bmsHandsTouch=new Bitmap[HANDCTR_TAKEN];	//for touch//~v@@@I~
    private Point[] pointsHandsTouch=new Point[HANDCTR_TAKEN];    //for touch//~v@@@R~
    private int centerRectHands;                                   //~v@@@I~
    private Earth earth;                                           //~v@@@I~
	private Point pointHands;                                      //~0325I~
//	private int shiftOpen;                                         //~0328I~//~0329R~
	private Rect[] openRectShiftS=new Rect[PLAYERS];
//  private Rect openRectShift;//~0328I~                           //~0329R~
    public int complete_stroke_width_hand;                         //~0401R~
//*************************                                        //~v@@@I~
	public Hands()  //for IT                                       //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("Hands.default constructor");     //~va60I~
    }                                                              //~va60I~
	public Hands(GCanvas Pgcanvas)                                 //~v@@@R~
    {                                                              //~0914I~
    	AG.aHands=this;                                            //~v@@@R~
        gcanvas = Pgcanvas;                                          //~v@@@I~
        table = gcanvas.table;                                     //~v@@@I~
        pieces=table.pieces;                                       //~v@@@I~
        players=AG.aPlayers;                                       //~v@@@I~
        swPortrait=table.swPortrait;                               //~v@@@R~
        bgColor=COLOR_BG_TABLE;                                    //~v@@@R~
        pieceW=table.handPieceW;                                  //~v@@@R~
        pieceH=table.handPieceH;                                  //~v@@@R~
        complete_stroke_width_hand=Pieces.getStrokeWidth(pieceW);   //~0401I~
//      bmsssHands=pieces.bitmapAllPieces;                         //~v@@@R~//~0216R~
        bmsssHands=AG.bitmapAllPieces;                             //~0216I~
        bmssHands=bmsssHands[PIECE_STANDING];                      //~v@@@R~
//      Point pointHands=table.getEarthLinePos();                  //~v@@@R~//~0325R~
        pointHands=table.getEarthLinePos();                        //~0325I~
        rectHands=getRectHands(pointHands);                    //~v@@@R~
        rectHands0=new Rect(rectHands);	//rectHands will shift by pon/kan/chii//~0326R~
        centerRectHands=(rectHands.left+rectHands.right)/2;             //~v@@@I~
        Rect[] r=table.getRectEarthPair();                         //~v@@@I~
        WW=r[PLAYER_YOU].left; HH=r[PLAYER_YOU].top;                //~v@@@R~
        if (Dump.Y) Dump.println("Hands.constructor pair,WW="+WW+",HH="+HH);//~v@@@R~
//      posPairRight=marginPairRight;                              //~v@@@R~
//      posPairBottom=marginPairBottom;                            //~v@@@R~
        Point p=table.getPairPieceSize();                         //~v@@@I~
        piecePairW=p.x; piecePairH=p.y;                            //~v@@@I~
        swVerticalEarths=swVerticalEarthss[swPortrait ? 1 :0 ];    //~v@@@I~
        earth=new Earth(Pgcanvas,this);                            //~v@@@I~
        handsTouch=new HandsTouch(Pgcanvas,this,rectHands);        //~v@@@R~
        if (Dump.Y) Dump.println("Hands.constructor pair piece w="+piecePairW+",h="+piecePairH);//~v@@@I~
        if (Dump.Y) Dump.println("Hands.constructor rectHands0="+ Utils.toString(rectHands0));//~0326I~
      	openRects=table.getOpenRect();                             //~v@@5I~
      	openRects0=table.getOpenRectClone();                       //~0328R~
    }
//*************************                                        //~0325I~
	public void newGame()                                               //~0325I~
    {                                                              //~0325I~
        if (Dump.Y) Dump.println("Hands.newGame");                 //~0325I~
//      if (true) return; //TODO Test                              //~0325R~
//  	AG.aHands=this;                                            //~0325I~
//      gcanvas = Pgcanvas;                                        //~0325I~
//      table = gcanvas.table;                                     //~0325I~
//      pieces=table.pieces;                                       //~0325I~
//      players=AG.aPlayers;                                       //~0325I~
//      swPortrait=table.swPortrait;                               //~0325I~
//      bgColor=COLOR_BG_TABLE;                                    //~0325I~
//      pieceW=table.handPieceW;                                   //~0325I~
//      pieceH=table.handPieceH;                                   //~0325I~
//      bmsssHands=AG.bitmapAllPieces;                             //~0325I~
//      bmssHands=bmsssHands[PIECE_STANDING];                      //~0325I~
//      Point pointHands=table.getEarthLinePos();                  //~0325R~
//      rectHands=getRectHands(pointHands);                        //~0325R~
        rectHands=new Rect(rectHands0);                                      //~0325I~//~0327R~
        centerRectHands=(rectHands.left+rectHands.right)/2;        //~0325I~
//      Rect[] r=table.getRectEarthPair();                         //~0325I~
//      WW=r[PLAYER_YOU].left; HH=r[PLAYER_YOU].top;               //~0325I~
//      if (Dump.Y) Dump.println("Hands.constructor pair,WW="+WW+",HH="+HH);//~0325I~
//      Point p=table.getPairPieceSize();                          //~0325I~
//      piecePairW=p.x; piecePairH=p.y;                            //~0325I~
//      swVerticalEarths=swVerticalEarthss[swPortrait ? 1 :0 ];    //~0325I~
//      earth=new Earth(Pgcanvas,this);                            //~0325I~
//      handsTouch=new HandsTouch(Pgcanvas,this,rectHands);        //~0325I~
//      if (Dump.Y) Dump.println("Hands.constructor pair piece w="+piecePairW+",h="+piecePairH);//~0325I~
//    	openRects=table.getOpenRect();                             //~0325I~
    }                                                              //~0325I~
    //*******************************************************************//~v@@@I~
    //*for test, fromGCanvas                                       //~v@@@I~
    //*******************************************************************//~v@@@I~
	public void drawInitial(Canvas Pcanvas)                        //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Hands.drawInitialDeal");         //~v@@@R~
        int ww=pieceW;                                             //~v@@@I~
        int hh=pieceH;                                             //~v@@@I~
        int sepW=PIECE_SPACING;                                    //~v@@@I~
        int sepW2=PIECE_SPACING_TAKEN;                           //~v@@@I~
        int ppl=14;     //piece per line                           //~v@@@I~
        int xx=rectHands.left;                                     //~v@@@R~
        int yy=rectHands.top;                                      //~v@@@R~
        int ctr=0;                                                 //~v@@@I~
//        for (int jj=0;jj<PIECE_TYPECTR_ALL && ctr<ppl;jj++)      //~v@@@R~
//        {                                                        //~v@@@R~
//            Bitmap[] bms=bmssHands[jj];                          //~v@@@R~
//            for (int kk=0;kk<bms.length && ctr<ppl;kk++)         //~v@@@R~
//            {                                                    //~v@@@R~
//                Bitmap bm=bms[kk];                               //~v@@@R~
//                Graphics.drawBitmap(Pcanvas,bm,xx,yy);           //~v@@@R~
//                if (ctr==ppl-2)                                  //~v@@@R~
//                    xx+=ww+sepW2;                                //~v@@@R~
//                else                                             //~v@@@R~
//                    xx+=ww+sepW;                                 //~v@@@R~
//                ctr++;                                           //~v@@@R~
//            }                                                    //~v@@@R~
//        }                                                        //~v@@@R~
        for (int ii=0;ii<displayPieces.length;ii++)                //~v@@@I~
        {                                                          //~v@@@I~
            Bitmap bm=bmssHands[displayPieces[ii][0]][displayPieces[ii][1]];//~v@@@I~
            Graphics.drawBitmap(Pcanvas,bm,xx,yy);                 //~v@@@I~
            if (ctr==ppl-2)                                        //~v@@@I~
            	xx+=ww+sepW2;                                      //~v@@@I~
            else                                                   //~v@@@I~
                xx+=ww+sepW;                                       //~v@@@I~
            ctr++;                                                 //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@R~
    public void drawHands(boolean Pswtakeone,boolean Pswintercept) //~v@@@R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("Hands.drawHands swTakeone="+Pswtakeone+",Pswintercepts="+Pswintercept);//~v@@@R~
//      int player=AG.aAccounts.getCurrentEswn();                  //~v@@@R~
//      int player=Accounts.getPlayerYou(); //eswn(server) or PLAYER_YOU(client)//~v@@@R~//~v@@5R~
        int player=PLAYER_YOU;                                     //~v@@5I~
        drawHands(Pswtakeone,Pswintercept,player);                 //~v@@@R~
    }                                                              //~v@@@R~
    //*******************************************************************//~v@@@R~
    //*for client by DEAL msg data                                 //~v@@@R~
    //*******************************************************************//~v@@@R~
    public void drawHands(TileData[] Ptds)                         //~v@@@R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("Hands.drawHands by TilesData");  //~v@@@R~
//        TileData[] tds=Ptds;                                     //~v@@@R~
//        int ctr=tds.length;                                      //~v@@@R~
//        Graphics.drawRect(rectHands,bgColor);   //clear previous rect//~v@@@R~
//        if (Dump.Y) Dump.println("Hands.drawHands ctr="+ctr+",shiftHands="+shiftHands+",recthands l="+rectHands.left+",r="+rectHands.right);//~v@@@R~
//        rectHands.left+=shiftHands;                              //~v@@@R~
//        rectHands.right=rectHands.left+getLengthHands(ctr);      //~v@@@R~
//        Graphics.drawRect(rectHands,bgColor);                    //~v@@@R~
//        int xx=rectHands.left;                                   //~v@@@R~
//        int yy=rectHands.top;                                    //~v@@@R~
//        for (int ii=0;ii<ctr;ii++)                               //~v@@@R~
//        {                                                        //~v@@@R~
//            TileData td=tds[ii];                                 //~v@@@R~
//            if (Dump.Y) Dump.println("Hands.drawHand type="+td.type+",no="+td.number+",td="+td.toString());//~v@@@R~
//            Bitmap bm=pieces.getBitmapHand(td);                  //~v@@@R~
//            Graphics.drawBitmap(bm,xx,yy);                       //~v@@@R~
//            xx+=pieceW+PIECE_SPACING;                            //~v@@@R~
//        }                                                        //~v@@@R~
//      int player=AG.aAccounts.getPlayerPosOnServer();	//player# on server//~v@@@R~
        int player=PLAYER_YOU;  // set on players[0] if client by received msg data//~v@@@I~
        drawHands(false/*PswTakeOne*/,false/*Pswintercept*/,player);//~v@@@I~
    }                                                              //~v@@@R~
    //*******************************************************************//~v@@@I~
    private void drawHands(boolean Pswtakeone,boolean Pswintercept,int Pplayer)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Hands.drawHands swTakeone="+Pswtakeone+",Pswintercepts="+Pswintercept+",Pplayer="+Pplayer);//~v@@@I~//~v@@5R~
//      int player=PLAYER_YOU;		//called when you only         //~v@@@R~
//      int player=AG.aAccounts.getCurrentESWN();                  //~v@@@R~
        int player=Pplayer;                                        //~v@@@I~
        TileData[] tds=getHands(player);                           //~v@@@R~
//      TileData[] tds=getHands();                                 //~v@@@R~
        int ctr=tds.length;                                        //~v@@@I~
        if (shiftHands<0)                                          //~v@@5I~
        {                                                          //~v@@5I~
        	Rect r=new Rect(rectHands);                            //~v@@5I~
            r.right+=shiftHands;                                   //~v@@5I~
		    Graphics.drawRect(r,bgColor);	//clear previous rect  //~v@@5I~
        }                                                          //~v@@5I~
        else                                                       //~v@@5I~
		    Graphics.drawRect(rectHands,bgColor);	//clear previous rect//~v@@@I~//~v@@5R~
        if (Dump.Y) Dump.println("Hands.drawHands ctr="+ctr+",shiftHands="+shiftHands+",recthands l="+rectHands.left+",r="+rectHands.right);//~v@@@I~
        rectHands.left+=shiftHands;                                //~v@@@I~
        rectHands.right=rectHands.left+getLengthHands(ctr);        //~v@@@I~
//      canvas=Graphics.lockCanvas(rectHands);                     //~v@@@R~//~v@@5R~
//      boolean swTaken=(ctr-1)%PAIRCTR!=0 && Pswtakeone/*take kan*/;//~v@@@R~
        boolean swTaken=Tiles.isTakenStatus(ctr) && Pswtakeone/*take kan*/;//~v@@@I~
        if (Dump.Y) Dump.println("Hands.drawHands swTaken="+swTaken);//~v@@5I~
//      try                                                        //~v@@@I~//~v@@5R~
//      {                                                          //~v@@@I~//~v@@5R~
//          Graphics.drawRect(canvas,rectHands,bgColor);           //~v@@@R~//~v@@5R~
            Graphics.drawRect(rectHands,bgColor);                  //~v@@5I~
            int xx=rectHands.left;                                     //~v@@@R~
            int yy=rectHands.top;                                  //~v@@@R~
            for (int ii=0;ii<ctr;ii++)                             //~v@@@R~
            {                                                      //~v@@@R~
                TileData td=tds[ii];
                if (Dump.Y) Dump.println("Hands.drawHand type="+td.type+",no="+td.number+",td="+td.toString());//~v@@@R~
                Bitmap bm=pieces.getBitmapHand(td);               //~v@@@R~
    			if (swTaken && ii==ctr-1)	//kan taken case       //~v@@@I~//~v@@5R~
                {                                                  //~v@@@I~
//  				drawPiece(canvas,new Rect(xx,yy,xx+pieceW,yy+pieceH),new Point(xx,yy),bm,true/*PswSelected*/);//~v@@@R~//~v@@5R~
    				drawPiece(new Rect(xx,yy,xx+pieceW,yy+pieceH),new Point(xx,yy),bm,true/*PswSelected*/);//~v@@5I~
                }                                                  //~v@@@I~
                else                                               //~v@@@I~
    			if (Pswintercept && ii==ctr-1)	//pon/chii         //~v@@5I~
                {                                                  //~v@@5I~
    				drawPiece(new Rect(xx,yy,xx+pieceW,yy+pieceH),new Point(xx,yy),bm,true/*PswSelected*/);//~v@@5I~
                }                                                  //~v@@5I~
                else                                               //~v@@5I~
                {                                                  //~v@@@I~
//              	Graphics.drawBitmap(canvas,bm,xx,yy);          //~v@@@R~//~v@@5R~
                	Graphics.drawBitmap(bm,xx,yy);                 //~v@@5I~
                }                                                  //~v@@@I~
                bmsHandsTouch[ii]=bm;                               //~v@@@I~
                pointsHandsTouch[ii]=new Point(xx,yy);             //~v@@@R~
//              if (Pswtakeone && ii==ctr-2)                       //~v@@@R~
                if (swTaken && ii==ctr-2)                          //~v@@@I~
                    xx+=pieceW+PIECE_SPACING_TAKEN;                //~v@@@R~
                else                                               //~v@@@R~
                    xx+=pieceW+PIECE_SPACING;                      //~v@@@R~
            }                                                      //~v@@@R~
//          if (Pswintercept)   //pon,chii,kan                     //~v@@@R~
            if (!Tiles.isTakenStatus(ctr))   //prepare space for taken//~v@@@R~
            {                                                      //~v@@@R~
            	rectHands.right=xx+pieceW;                                //~v@@@I~
                if (Dump.Y) Dump.println("Hands.drawHands get from river rectHands right="+xx);//~v@@@R~
            }                                                      //~v@@@R~
//      }                                                          //~v@@@I~//~v@@5R~
//      catch(Exception e)                                         //~v@@@I~//~v@@5R~
//      {                                                          //~v@@@I~//~v@@5R~
//      	Dump.println(e,"Hands.drawHands");                     //~v@@@R~//~v@@5R~
//      }                                                          //~v@@@I~//~v@@5R~
//      Graphics.unlockCanvas(canvas);                             //~v@@@I~//~v@@5R~
        handsTouch.notifyRect(bmsHandsTouch,pointsHandsTouch,ctr); //~v@@@R~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
    //*redraw Hands after taken from river                         //~v@@@I~
    //*******************************************************************//~v@@@I~
    private void shiftHandsByPair(boolean PswverticalEarth)        //~v@@@R~
    {                                                              //~v@@@I~
    	TileData[] tds;                                            //~v@@@I~
    	Rect rectPair;                                             //~v@@@I~
    //*****************                                            //~v@@@I~
//  	if (!PswverticalEarth)                                     //~v@@@R~
//      	return;                                                //~v@@@R~
        int ctrPair=AG.aPlayers.getCtrPair(PLAYER_YOU);            //~v@@@I~
        if (ctrPair==0)                                            //~v@@@I~
        	return;                                                //~v@@@I~
    	rectPair=earth.rectPairYou;                                      //~v@@@I~//~v@@5M~
		if (Dump.Y) Dump.println("Hands.shiftHandsByPair swVerticalEarth="+PswverticalEarth+",ctrPair="+ctrPair+",rectPair="+rectPair.toString()+",rectHands="+rectHands.toString());//~v@@5R~
        if (ctrPair==1)	//not 1st                                  //~v@@@I~//~v@@5R~
        {                                                          //~v@@@I~
    		tds=earth.getCurrentPair(PLAYER_YOU);             //~v@@@I~
	        int leftPair=rectPair.left-(tds.length!=PAIRCTR_KAN ? piecePairW : 0);//~v@@@R~
		    rightHandsYou=Math.min(rectHands.right,leftPair-SPACE_HANDS_EARTH_YOU);//~v@@@I~
        }                                                          //~v@@@I~
        int ctr=getHandCtr(PLAYER_YOU);                            //~v@@@R~
//      int ctr=getHandCtr();                                      //~v@@@R~
//      int len=ctr*(pieceW+PIECE_SPACING);                        //~v@@@R~
        int len=getLengthHands(ctr+1/*for taken*/);                //~v@@@R~
//        int ww=rightHandsYou-rectHands.left;                     //~v@@@R~
//        int shift=(ww-len)/2;                                    //~v@@@R~
//        if (shift>0)                                             //~v@@@R~
//        {                                                        //~v@@@R~
////          rectHands.left+=shift;                               //~v@@@R~
//            shiftHands=shift;                                    //~v@@@R~
////          rectHands.right=rectHands.left+shift+len;            //~v@@@R~
//            if (Dump.Y) Dump.println("Hands.shiftHandsByPair rectHands shift="+shift+",rectHands left="+rectHands.left+",right="+rectHands.right);//~v@@@R~
//            drawHands(false/*not takeone*/,true/*intercept*/);   //~v@@@R~
//        }                                                        //~v@@@R~
//        else                                                     //~v@@@R~
//        {                                                        //~v@@@R~
////          rectHands.right=rectHands.left+len;                  //~v@@@R~
//            shiftHands=0;                                        //~v@@@R~
//        }                                                        //~v@@@R~
	if (true)                                                      //~v@@5I~
    {                                                              //~v@@5I~
        int l=rectPair.left-SPACE_HANDS_EARTH_YOU;                 //~v@@5I~
        int r=rectHands.left;                                      //~v@@5R~
        if (Tiles.isTakenStatus(ctr))                              //~v@@5R~
        	r+=getLengthHands(ctr);                                //~v@@5I~
        else                                                       //~v@@5I~
        	r+=getLengthHands(ctr+1/*for taken*/);                 //~v@@5R~
        shiftHands=l-r;                                            //~v@@5R~
//      if (shiftHands<0)                                          //~v@@5I~
//      	rectHands.right-=shiftHands;	//dont clear drawearth //~v@@5I~
	        if (Dump.Y) Dump.println("Hands.shiftHandsByPair Handsctr="+ctr+",lenofctr="+getLengthHands(ctr)+",pieceW="+pieceW+",l="+l+",r="+r+",len="+len+",rightHandsYou="+rightHandsYou+",shift="+shiftHands);//~v@@5R~
//          drawHands(false/*not takeone*/,true/*intercept*/);     //~v@@5R~
            drawHands(false/*not takeone*/,false/*discard and kan:no intercept*/);//~v@@5I~
    }                                                              //~v@@5I~
    else                                                           //~v@@5I~
    {                                                              //~v@@5I~
		if (centerRectHands+len/2<rightHandsYou)                   //~v@@@I~
        {                                                          //~v@@@I~
        	int shift=(centerRectHands-len/2)-rectHands.left;           //~v@@@I~
	        if (Dump.Y) Dump.println("Hands.shiftHandsByPair ctr="+ctr+",len="+len+",rightHandsYou="+rightHandsYou+",center="+centerRectHands+",shift="+shift+",rectHands left="+rectHands.left+",right="+rectHands.right);//~v@@@I~
//          if (shift>0)                                           //~v@@@R~
            {                                                      //~v@@@I~
				shiftHands=shift;                                  //~v@@@I~
                drawHands(false/*not takeone*/,true/*intercept*/); //~v@@@I~
			}                                                      //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@5I~
        if (Dump.Y) Dump.println("Hands.shiftHandsByPair ctr="+ctr+",len="+len+",shiftHands="+shiftHands+",rightHandsYou="+rightHandsYou+",center="+centerRectHands+",rectHands left="+rectHands.left+",right="+rectHands.right);//~v@@@R~
        shiftHands=0;   //rectHands was updated at drawHands       //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@@@I~
//  private void drawPiece(Rect Prect,Point Ppoint,Bitmap Pbitmap) //~v@@@R~
    private void drawPiece(Rect Prect,Point Ppoint,Bitmap Pbitmap,boolean PswSelected)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Hands.drawPiece PswSelected="+PswSelected+",swComplete="+swComplete);               //~v@@@R~//~v@@5R~
//  	Graphics.drawRectBitmap(Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y);//~v@@@R~
//  	Graphics.drawBitmap(Prect,Pbitmap,Ppoint.x,Ppoint.y);      //~v@@@R~
        if (swComplete)                                            //~v@@@M~
        {                                                          //~v@@@I~
//  		Graphics.drawRect(Prect,COMPLETE_COLOR,COMPLETE_STROKE_WIDTH);//~v@@@M~
//  		Graphics.drawRectFrame(Prect,COMPLETE_COLOR,COMPLETE_STROKE_WIDTH);//~v@@@I~
//	    	Graphics.drawRectFrameBitmap(Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y,COMPLETE_STROKE_WIDTH,COMPLETE_COLOR);//~v@@@I~//~0401R~
	    	Graphics.drawRectFrameBitmap(Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y,complete_stroke_width_hand,COMPLETE_COLOR);//~0401I~
        	if (Dump.Y) Dump.println("Hands.drawPiece swComplete=true stroke_width_hand="+complete_stroke_width_hand);//~0401I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
		if (PswSelected)                                           //~v@@@I~
        {                                                          //~v@@@I~
//      	Graphics.drawRectFrameBitmap(Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y,HandsTouch.WIDTH_SELECTED,HandsTouch.COLOR_SELECTED);//~v@@@R~//~0401R~
        	Graphics.drawRectFrameBitmap(Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y,complete_stroke_width_hand,HandsTouch.COLOR_SELECTED);//~0401I~
        	if (Dump.Y) Dump.println("Hands.drawPiece swComplete=false swSelected=true complete_stroke_width_hand="+complete_stroke_width_hand);//~0401I~
//      	Graphics.drawBitmap(Prect,Pbitmap,Ppoint.x,Ppoint.y);  //~v@@@R~
//          handsTouch.notifyRectTaken(Pbitmap,Ppoint,getHandCtr(PLAYER_YOU)-1);//~v@@@R~
//  		Graphics.drawRectBitmap(Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y);//~v@@@R~
            handsTouch.notifyRectTaken(Pbitmap,Ppoint,getHandCtr(PLAYER_YOU)-1);//~v@@@R~
//          handsTouch.notifyRectTaken(Pbitmap,Ppoint,getHandCtr()-1);//~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
			Graphics.drawRectBitmap(Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y);//~v@@@I~
    }                                                              //~v@@@I~
//    //*******************************************************************//~v@@@I~//~v@@5R~
//    private void drawPiece(Canvas Pcanvas,Rect Prect,Point Ppoint,Bitmap Pbitmap,boolean PswSelected)//~v@@@I~//~v@@5R~
//    {                                                              //~v@@@I~//~v@@5R~
//        if (Dump.Y) Dump.println("Hands.drawPiece with canvas");   //~v@@@I~//~v@@5R~
//        if (PswSelected)                                           //~v@@@I~//~v@@5R~
//        {                                                          //~v@@@I~//~v@@5R~
//            Graphics.drawRectFrameBitmap(Pcanvas,Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y,HandsTouch.WIDTH_SELECTED,HandsTouch.COLOR_SELECTED);//~v@@@R~//~v@@5R~
////          handsTouch.notifyRectTaken(Pbitmap,Ppoint,getHandCtr(PLAYER_YOU)-1);//~v@@@I~//~v@@5R~
////          Graphics.drawRectBitmap(Pcanvas,Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y);//~v@@@R~//~v@@5R~
//            handsTouch.notifyRectTaken(Pbitmap,Ppoint,getHandCtr(PLAYER_YOU)-1);//~v@@@R~//~v@@5R~
////          handsTouch.notifyRectTaken(Pbitmap,Ppoint,getHandCtr()-1);//~v@@@R~//~v@@5R~
//        }                                                          //~v@@@I~//~v@@5R~
//        else                                                       //~v@@@I~//~v@@5R~
//            Graphics.drawRectBitmap(Pcanvas,Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y);//~v@@@I~//~v@@5R~
//    }                                                              //~v@@@I~//~v@@5R~
	//*********************************************************    //~v@@@M~
	private Rect getRectPiece(int Ppos,boolean Pswtaken)           //~v@@@R~
    {                                                              //~v@@@M~
        int xx1,xx2,yy1,yy2;                                        //~v@@@R~
    //*****************************                                //~v@@@I~
        xx1=rectHands.left;                                        //~v@@@R~
        yy1=rectHands.top;                                          //~v@@@R~
        xx1+=(pieceW+PIECE_SPACING)*Ppos-PIECE_SPACING;            //~v@@@R~
        if (Pswtaken)                                              //~v@@@R~
        	xx1+=PIECE_SPACING_TAKEN;                              //~v@@@I~
        xx2=xx1+pieceW;                                            //~v@@@I~
        yy2=yy1+pieceH;                                            //~v@@@I~
        if (Dump.Y) Dump.println("Hands.getRectPiece pos="+Ppos+",x1="+xx1+",y1="+yy1+",x2="+xx2+",y2="+yy2);//~v@@@R~
    	return new Rect(xx1,yy1,xx2,yy2);                          //~v@@@R~
    }                                                              //~v@@@M~
	//*********************************************************    //~v@@@I~
	//*at initiale pointHands-->rectHands                          //~v@@@I~
	//*********************************************************    //~v@@@I~
	private Rect getRectHands(Point PpointHands)                   //~v@@@R~
    {                                                              //~v@@@I~
        int xx1,xx2,yy1,yy2;                                       //~v@@@I~
    //*****************************                                //~v@@@I~
        xx1=PpointHands.x;                                         //~v@@@R~
        yy1=PpointHands.y;                                         //~v@@@R~
        xx2=xx1+(pieceW+PIECE_SPACING)*HANDCTR-PIECE_SPACING+PIECE_SPACING_TAKEN+pieceW;//~v@@@R~
        yy2=yy1+pieceH;                                             //~v@@@I~
        if (Dump.Y) Dump.println("Hands.getRectHands x1="+xx1+",y1="+yy1+",x2="+xx2+",y2="+yy2);//~v@@@R~
    	return new Rect(xx1,yy1,xx2,yy2);                          //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	public  int getLengthHands(int Pctr)                           //~v@@@I~//~v@@5R~
    {                                                              //~v@@@I~
        int len=(pieceW+PIECE_SPACING)*Pctr-PIECE_SPACING;         //~v@@@I~
//      if ((Pctr-1)%PAIRCTR!=0)                                   //~v@@@R~
        if (Tiles.isTakenStatus(Pctr))                             //~v@@@I~
        	len+=PIECE_SPACING_TAKEN-PIECE_SPACING;                //~v@@@I~
        if (Dump.Y) Dump.println("Hands.getLengthHands ctr="+Pctr+",len="+len);//~v@@@I~
    	return len;                                                //~v@@@I~
    }                                                              //~v@@@I~
//    //*********************************************************  //~v@@@R~
//    public TileData[] getCurrentPair(int Pplayer)                //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Hands.getcurrentPair player="+Pplayer);//~v@@@R~
//        TileData[] tds=AG.aPlayers.getCurrentPair(Pplayer);      //~v@@@R~
//        return tds;                                              //~v@@@R~
//    }                                                            //~v@@@R~
	//*********************************************************    //~v@@@I~
	public TileData[] getHands(int Pplayer)                        //~v@@@R~
    {                                                              //~v@@@I~
        TileData[] tds=AG.aPlayers.getHands(Pplayer);               //~v@@@I~
        if (Dump.Y) Dump.println("Hands.getHands player="+Pplayer+",ctr="+tds.length);//~v@@@R~
        return tds;                                                //~v@@@I~
    }                                                              //~v@@@I~
//    //*********************************************************  //~v@@@R~
//    public TileData[] getHands()                                 //~v@@@R~
//    {                                                            //~v@@@R~
//        int player=Accounts.getPlayerYou();                      //~v@@@R~
//        TileData[] tds=AG.aPlayers.getHands(player);             //~v@@@R~
//        if (Dump.Y) Dump.println("Hands.getHands without PplayerParm player="+player+",ctr="+tds.length);//~v@@@R~
//        return tds;                                              //~v@@@R~
//    }                                                            //~v@@@R~
    //*********************************************************    //~v@@@R~
    public int getHandCtr(int Pplayer)                             //~v@@@R~
    {                                                              //~v@@@R~
        int ctr=AG.aPlayers.getHandCtr(Pplayer);                   //~v@@@R~
        if (Dump.Y) Dump.println("Hands.getHandCtr Player="+Pplayer+",ctr="+ctr);//~v@@@R~
        return ctr;                                                //~v@@@R~
    }                                                              //~v@@@R~
//    //*********************************************************  //~v@@@R~
//    public int getHandCtr()                                      //~v@@@R~
//    {                                                            //~v@@@R~
//        int player=Accounts.getPlayerYou();                      //~v@@@R~
//        int ctr=AG.aPlayers.getHandCtr(player);                  //~v@@@R~
//        if (Dump.Y) Dump.println("Hands.getHandCtr without PlayerParm layer="+player+",ctr="+ctr);//~v@@@R~
//        return ctr;                                              //~v@@@R~
//    }                                                            //~v@@@R~
    //*********************************************************    //~v@@@R~
    //*adjust rect box by stock position from Stock                //~v@@@R~
    //*********************************************************    //~v@@@R~
    public void notifyStockRect(Rect[] PrectStock)                 //~v@@@R~
    {                                                              //~v@@@R~
//        rectStock=PrectStock;                                    //~v@@@R~
        if (Dump.Y) Dump.println("Hands.adjustRectPair from Stock");//~v@@@R~
        earth.notifyStockRect(PrectStock);                  //~v@@@I~
    }                                                              //~v@@@R~
	//*********************************************************    //~v@@@I~
    private Bitmap getBitmap(TileData Ptd)                         //~v@@@R~
    {                                                              //~v@@@I~
        Bitmap bm=pieces.getBitmapHand(Ptd);                        //~v@@@I~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*draw current taken tile                                     //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void   takeOne(int Pplayer,TileData Ptd)               //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Hands.takeOne player="+Pplayer+",type="+Ptd.type+",no="+Ptd.number);//~v@@@R~
//      int player=PswServer ? Pplayer : PLAYER_YOU;               //~v@@@R~
//      int player=Accounts.getPlayerYou();                        //~v@@@R~
//      int ctr=getHandCtr(Pplayer);                               //~v@@@R~
        int ctr=getHandCtr(Pplayer);                                //~v@@@R~
//      int ctr=getHandCtr();                                      //~v@@@R~
        Rect r=getRectPiece(ctr-1,true);                           //~v@@@R~
        Bitmap bm=getBitmap(Ptd);                                  //~v@@@R~
        Point p=new Point(r.left,r.top);                           //~v@@@I~
        drawPiece(r,p,bm,true/*set selectframe*/);                 //~v@@@R~
        rectHands.right=r.right;                                   //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  discard(int Pplayer,TileData Ptd)             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Hands.discard player="+Pplayer+",type="+Ptd.type+",no="+Ptd.number);//~v@@@R~
        if (Pplayer==PLAYER_YOU)                                   //~v@@@I~
        {                                                          //~v@@@I~
        	drawHands(false/*not takeone*/,false/*not intercept*/);//~v@@@I~
        	handsTouch.discard();                                  //~v@@@I~
	        shiftHandsByPair(swVerticalEarths[PLAYER_YOU]);        //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	if (Dump.Y) Dump.println("Hands.discard player is Not You");//~v@@@I~
        	if (players.isOpen(Pplayer))                           //~v@@@I~
	        	drawOpen(Pplayer,null);                            //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  takePon(int Pplayer)                             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Hands.takePon player="+Pplayer); //~v@@@I~
        if (Pplayer==PLAYER_YOU)                                   //~v@@@R~
	        drawHands(false/*not takeone*/,true/*intercept*/);     //~v@@@R~
        else                                                       //~va65I~
        {                                                          //~va65I~
//      	if ((TestOption.option2 & TO2_OPENHAND)!=0 && AG.swTrainingMode && AG.aAccounts.isRobotPlayer(Pplayer))//+va65R~
        	if ((TestOption.option2 & TO2_OPENHAND)!=0                      && AG.aAccounts.isRobotPlayer(Pplayer))//+va65I~
            {                                                      //~va65I~
		        if (Dump.Y) Dump.println("Hands.takePon robot & open");//~va65I~
	    		drawOpenPonKanChiiRobot(Pplayer);                  //~va65R~
            }                                                      //~va65I~
        }                                                          //~va65I~
        earth.drawEarth(Pplayer);                                        //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  takeChii(int Pplayer)                            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Hands.takeChii player="+Pplayer);//~v@@@I~
        takePon(Pplayer);                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  takeKan(int Pplayer,int Pkantype)                //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Hands.takeKan player="+Pplayer+",kantype="+Pkantype);//~v@@@R~
        if (Pplayer==PLAYER_YOU)                                   //~v@@@I~
	        drawHands(false/*not yet taken from wanpai*/,Pkantype==KAN_RIVER/*intercept*/);//~v@@@R~
        else                                                       //~va65I~
        {                                                          //~va65I~
//      	if ((TestOption.option2 & TO2_OPENHAND)!=0 && AG.swTrainingMode && AG.aAccounts.isRobotPlayer(Pplayer))//+va65R~
        	if ((TestOption.option2 & TO2_OPENHAND)!=0                      && AG.aAccounts.isRobotPlayer(Pplayer))//+va65I~
            {                                                      //~va65I~
		        if (Dump.Y) Dump.println("Hands.takeKan robot & open");//~va65I~
	    		drawOpenPonKanChiiRobot(Pplayer);                  //~va65R~
            }                                                      //~va65I~
        }                                                          //~va65I~
        if (Pkantype==KAN_ADD)	//update pon pair on earth         //~v@@@I~
	        earth.drawEarthAddKan(Pplayer);                              //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@5I~
	        earth.drawEarth(Pplayer);                                    //~v@@@R~
	        if (Pplayer==PLAYER_YOU)                               //~v@@5I~
		        shiftHandsByPair(swVerticalEarths[PLAYER_YOU]);    //~v@@5I~
        }                                                          //~v@@5I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  complete(int Pplayer,int Pflag)                  //~v@@@I~
    {                                                              //~v@@@I~
//  	TileData td2=AG.aPlayers.getTileComplete(); //TEST         //~v@@5R~
        if (Dump.Y) Dump.println("Hands.complete player="+Pplayer+",flag="+Pflag);//~v@@@I~
//  	TileData td=AG.aPlayers.getCurrentTile();                  //~v@@5R~
    	TileData td=AG.aPlayers.getCurrentTileForRon();            //~v@@5I~
        if (td==null)	//no tile to show as target in hand        //~v@@@I~
        	return;                                                //~v@@@I~
//      if ((td.flag & TDF_KAN_ADDED_TILE)!=0)                          //~v@@@I~//~v@@5R~
        if ((Pflag & COMPLETE_KAN_ADD)!=0)                         //~v@@5I~
        {                                                          //~v@@@I~
        	earth.complete(Pplayer,td);                            //~v@@@I~
        	if (Pplayer!=PLAYER_YOU)                               //~v@@5I~
                drawOpen(Pplayer,null);                            //~v@@5I~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@5I~
            if (Pplayer==PLAYER_YOU)                               //~v@@5R~
            {                                                          //~v@@@I~//~v@@5R~
    //          if ((Pflag & COMPLETE_TAKEN)!=0)                   //~v@@5R~
                if ((Pflag & COMPLETE_KAN_TAKEN_OTHER)!=0)	//ankan ron//~v@@5R~
                	earth.complete(td,Pflag);	//draw ron mark on earth kan//~v@@5M~
                else                                               //~v@@5M~
                {                                                  //~v@@5I~
                    if (td.isTaken())                              //~v@@5R~
                    {                                              //~v@@5R~
                        swComplete=true;                                       //~v@@@I~//~v@@5R~
                        takeOne(Pplayer,td);                       //~v@@5R~
                        swComplete=false;                                      //~v@@@I~//~v@@5R~
                    }                                              //~v@@5R~
                }                                                  //~v@@5I~
            }                                                          //~v@@@I~//~v@@5R~
            else                                                   //~v@@5R~
            {                                                          //~v@@@I~//~v@@5R~
                if ((Pflag & COMPLETE_KAN_TAKEN_OTHER)!=0)	//ankan ron//~v@@5I~
                	earth.complete(td,Pflag);	//draw ron mark on earth kan//~v@@5I~
                drawOpen(Pplayer,td);                             //~v@@@R~//~v@@5R~
            }                                                          //~v@@@I~//~v@@5R~
        }                                                          //~v@@5I~
    }                                                              //~v@@@I~
	//*********************************************************    //~9A13I~
	//*from UARon                                                  //~9A13I~
	//*********************************************************    //~9A13I~
    public void  resetComplete(int Pplayer,int Pflag)              //~9A13I~
    {                                                              //~9A13I~
        if (Dump.Y) Dump.println("Hands.complete player="+Pplayer+",flag="+Pflag);//~9A13I~
//                                                                 //~9A13I~
//  	TileData td=AG.aPlayers.getCurrentTileForRon();            //~9A13I~
//      if (td==null)	//no tile to show as target in hand        //~9A13I~
//      	return;                                                //~9A13I~
//      if ((Pflag & COMPLETE_KAN_ADD)!=0)                         //~9A13I~
//      {                                                          //~9A13I~
//      	earth.complete(Pplayer,td);                            //~9A13I~
//      	if (Pplayer!=PLAYER_YOU)                               //~9A13I~
//              drawOpen(Pplayer,null);                            //~9A13I~
//      }                                                          //~9A13I~
//      else                                                       //~9A13I~
//      {                                                          //~9A13I~
//          if (Pplayer==PLAYER_YOU)                               //~9A13I~
//          {                                                      //~9A13I~
//              if ((Pflag & COMPLETE_KAN_TAKEN_OTHER)!=0)	//ankan ron//~9A13I~
//              	earth.complete(td,Pflag);	//draw ron mark on earth kan//~9A13I~
//              else                                               //~9A13I~
//              {                                                  //~9A13I~
//                  if (td.isTaken())                              //~9A13I~
//                  {                                              //~9A13I~
//                      swComplete=true;                           //~9A13I~
//                      takeOne(Pplayer,td);                       //~9A13I~
//                      swComplete=false;                          //~9A13I~
//                  }                                              //~9A13I~
//              }                                                  //~9A13I~
//          }                                                      //~9A13I~
//          else                                                   //~9A13I~
//          {                                                      //~9A13I~
//              if ((Pflag & COMPLETE_KAN_TAKEN_OTHER)!=0)	//ankan ron//~9A13I~
//              	earth.complete(td,Pflag);	//draw ron mark on earth kan//~9A13I~
//              drawOpen(Pplayer,td);                              //~9A13I~
//          }                                                      //~9A13I~
//      }                                                          //~9A13I~
      	if (Pplayer!=PLAYER_YOU)                                   //~9A13I~
            clearOpenRect(Pplayer);                                //~9A13I~
    }                                                              //~9A13I~
	//*********************************************************    //~v@@@I~
    public  void  open(int Pplayer)                                //~v@@@I~
    {                                                              //~v@@@I~
    //********************                                         //~v@@@I~
        if (Dump.Y) Dump.println("Hands.open player="+Pplayer);    //~v@@@I~
//      if (openRects==null)                                       //~v@@@R~//~v@@5R~
//      	openRects=table.getOpenRect();                         //~v@@@R~//~v@@5R~
        drawOpen(Pplayer,null/*currentTaken*/);                    //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*taken tile is already inserted to hand                      //~v@@@I~
	//*********************************************************    //~v@@@I~
    private int getPosTaken(TileData[] Ptds,TileData Ptd)          //~v@@@R~
    {                                                              //~v@@@I~
    	int pos=-1,ctr;                                            //~v@@@R~
    //********************                                         //~v@@@I~
    	if (Ptd==null)	//not taken                                //~v@@@R~
        	return pos;	//-1                                       //~v@@@I~
        ctr=Ptds.length;                                           //~v@@@I~
////      if (ctr%PAIRCTR==PAIRCTR_REMAIN)    //not taken status   //~v@@@R~
//        if (!Players.isTakenStatus(ctr))    //not taken status   //~v@@@R~
//            return pos; //-1                                     //~v@@@R~
//        for (int ii=0;ii<ctr;ii++)                               //~v@@@R~
//            if (TileData.TDCompare(Ptds[ii],Ptd)==0)             //~v@@@R~
//            {                                                    //~v@@@R~
//                pos=ii;                                          //~v@@@R~
//                break;                                           //~v@@@R~
//            }                                                    //~v@@@R~
		pos=ctr-1;                                                 //~v@@@I~
        if (Dump.Y) Dump.println("Hands.getPosTaken type="+Ptd.type+",no="+Ptd.number+",flag="+Ptd.flag+",pos="+pos);//~v@@@I~
        return pos;                                                //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  drawOpen(int Pplayer,TileData Ptd)               //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Hands.drawOpen player="+Pplayer+",td="+Utils.toString(Ptd));//~0328I~
        Rect r;                                                    //~0329I~
//        if (Pplayer!=PLAYER_YOU)                                   //~0328M~//~0329R~
//        {                                                          //~0328M~//~0329R~
////          shiftOpen=getOpenShift(Pplayer);                       //~0328R~//~0329R~
//            r=getOpenShift(Pplayer);                             //~0329I~
//            drawOpenSub(Pplayer,null/*currentTaken*/,r);             //~0328I~//~0329R~
//            openRectShiftS[Pplayer]=r;  //to clear     //~0328R~ //~0329R~
//            shiftOpen=0;                                           //~0328R~//~0329R~
//        }                                                          //~0328M~//~0329R~
//        else                                                       //~0328M~//~0329R~
//            drawOpenSub(Pplayer,Ptd,open);                           //~0328I~//~0329R~
        if (Pplayer!=PLAYER_YOU)                                   //~0329I~
        {                                                          //~0329I~
            r=getOpenShift(Pplayer);                               //~0329I~
            if (r==null)  //no earth                               //~0329R~
	        	r=openRects0[Pplayer];                             //~0329R~
	        openRectShiftS[Pplayer]=r;  //to clear                 //~0329R~
        }                                                          //~0329I~
        else                                                       //~0329I~
        	r=openRects[Pplayer];                                   //~0329I~
        drawOpenSub(Pplayer,Ptd,r);                                //~0329I~
    }                                                              //~0328I~
	//*********************************************************    //~0328M~
    private Rect getOpenShift(int Pplayer)                          //~0328R~//~0329R~
    {                                                              //~0328M~
    //********************                                         //~0328M~
        if (Dump.Y) Dump.println("Hands.getOpenShift player="+Pplayer);//~0328R~
        if (Dump.Y) Dump.println("Hands.getOpenShift openRects0="+Utils.toString(openRects0));//~0328R~
        if (Dump.Y) Dump.println("Hands.getOpenShift openRects="+Utils.toString(openRects));//~0328R~
        Rect rlast=AG.aEarth.getLastRectPair(Pplayer);	//last pair Rect//~0328M~
//      Rect ropen=openRects[Pplayer];                             //~0328R~//~0329R~
        if (rlast==null)	//no earth                             //~0328I~
        {                                                          //~0328I~
	        if (Dump.Y) Dump.println("Hands.getOpenShift no earth return null");//~0328I~//~0329R~
        	return null;                                              //~0328I~//~0329R~
        }                                                          //~0328I~
        int len=getOpenWidthOther(Pplayer);                        //~0328I~
//      int shift;                                             //~0328R~//~0329R~
        int pos=0;                                                   //~0329I~
        int ww=table.riverPieceW;                                  //~0328I~
//      if (Dump.Y) Dump.println("Hands.getOpenShift openRect="+ropen.toString());//~0328I~//~0329R~
        Rect rnew=null;                                            //~0329I~
        switch(Pplayer)                                            //~0328M~
        {                                                          //~0328M~
        case PLAYER_RIGHT:                                         //~0328M~
            pos=rlast.bottom+len+ww/*spacing*/;                    //~0328R~
//      	shift=pos-ropen.bottom;                                //~0328R~//~0329R~
            rnew=new Rect(rlast.left   ,pos-len      ,rlast.right    ,pos);//~0329R~
            break;                                                 //~0328M~
        case PLAYER_FACING:                                        //~0328M~
            pos=rlast.right+ww+len;                                //~0328R~
//      	shift=pos-ropen.right;                                 //~0328R~//~0329R~
            rnew=new Rect(pos-len      ,rlast.top    ,pos            ,rlast.bottom);//~0329R~
            break;                                                 //~0328M~
        case PLAYER_LEFT:                                          //~0328M~
            pos=rlast.top-ww-len;                                  //~0328R~
//      	shift=ropen.top-pos;                                   //~0328R~//~0329R~
            rnew=new Rect(rlast.left   ,pos          ,rlast.right    ,pos+len  );//~0329R~
            break;                                                 //~0328I~
        }                                                          //~0328M~
        if (Dump.Y) Dump.println("Hands.getOpenShift len="+len+",pos="+pos+",rnew="+Utils.toString(rnew));//~0329R~
        return rnew;                                               //~0329R~
    }                                                              //~0328M~
	//*********************************************************    //~0328I~
    private int  getOpenWidthOther(int Pplayer)                   //~0328I~
    {                                                              //~0328I~
    	TileData[] tds=getHands(Pplayer);                          //~0328I~
        int ctr=tds.length;                                            //~0328I~
        int ww=table.riverPieceW;                                      //~0328I~
        int spaceTaken=Tiles.isCtrTaken(ctr) ? (PIECE_SPACING_TAKEN-PIECE_SPACING) : 0;//~0328I~
        int len=(ww+PIECE_SPACING)*ctr+spaceTaken;                 //~0328I~
        if (Dump.Y) Dump.println("Hands.getOpenWidthOther player="+Pplayer+",tds ctr="+ctr+",ww="+ww+",len="+len);//~0328I~
        return len;                                                //~0328I~
    }                                                              //~0328I~
	//*********************************************************    //~0328I~
    private void  drawOpenSub(int Pplayer,TileData Ptd,Rect PopenRect)            //~0328I~//~0329R~
    {                                                              //~0328I~
        int xx,yy,xx0,yy0,ww,stepX,stepY,stepX2,stepY2,ctr;        //~0328I~
    //********************                                         //~v@@@I~
    	TileData[] tds=getHands(Pplayer);                          //~v@@@R~
//  	TileData[] tds=getHands();                                 //~v@@@R~
        ctr=tds.length;                                            //~v@@@I~
        int pos=getPosTaken(tds,Ptd);                              //~v@@@I~
//      if (pos==ctr-1)     //inserted last                        //~v@@@R~
//      	pos=-1;	        //no need to draw at last              //~v@@@R~
        ww=table.riverPieceW;                                      //~v@@@I~
//      Rect rect=openRects[Pplayer];                              //~v@@@R~//~0329R~
        Rect rect=PopenRect;                                       //~0329I~
        if (Dump.Y) Dump.println("Hands.drawOpenSub player="+Pplayer+",ctr="+ctr+",openRect="+rect.toString());//~v@@@I~//~v@@5R~//~9A13I~//~0328R~
        switch(Pplayer)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
        	xx0=rect.left;                                         //~v@@@I~
        	yy0=rect.bottom-ww;                                    //~v@@@I~
//          yy0+=shiftOpen;                                        //~0328I~//~0329R~
            stepX=0;                                               //~v@@@I~
            stepY=-(ww+PIECE_SPACING);                             //~v@@@I~
            stepX2=0;                                              //~v@@@I~
            stepY2=-(PIECE_SPACING_TAKEN-PIECE_SPACING);           //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
        	xx0=rect.right-ww;                                     //~v@@@I~
//          xx0+=shiftOpen;                                        //~0328R~//~0329R~
        	yy0=rect.top;                                          //~v@@@I~
            stepX=-(ww+PIECE_SPACING);                             //~v@@@I~
            stepY=0;                                               //~v@@@I~
            stepX2=-(PIECE_SPACING_TAKEN-PIECE_SPACING);           //~v@@@R~
            stepY2=0;                                              //~v@@@I~
            break;                                                 //~v@@@I~
        case PLAYER_LEFT:                                          //~v@@@I~
        	xx0=rect.left;                                         //~v@@@I~
        	yy0=rect.top;                                          //~v@@@I~
//          yy0+=shiftOpen;                                        //~0328R~//~0329R~
            stepX=0;                                               //~v@@@I~
            stepY=ww+PIECE_SPACING;                                //~v@@@I~
            stepX2=0;                                              //~v@@@I~
            stepY2=PIECE_SPACING_TAKEN-PIECE_SPACING;              //~v@@@R~
            break;                                                 //~v@@@I~
            default:                                               //~v@@@I~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
            Graphics.drawRect(rect,bgColor);                       //~v@@5I~
            xx=xx0; yy=yy0;                                        //~v@@@I~
            for (int ii=0;ii<ctr;ii++)                             //~v@@@I~
            {                                                      //~v@@@I~
//              if (ii!=pos)                                       //~v@@@R~
//              {                                                  //~v@@@R~
                    TileData td=tds[ii];                           //~v@@@R~
                    if (Dump.Y) Dump.println("Hands.drawOpen type="+td.type+",no="+td.number+",td="+td.toString());//~v@@@R~//~0328R~
                    Bitmap bm=pieces.getBitmapRiver(td,Pplayer);   //~v@@@R~
                    Graphics.drawBitmap(bm,xx,yy);                 //~v@@5I~
//                  if ((ctr==HANDCTR_TAKEN && ii==ctr-1)           //~v@@@I~//~v@@5R~
                    if ((Tiles.isCtrTaken(ctr) && ii==ctr-1)         //~v@@5I~
                    ||  td.isRon())                                //~v@@5I~
                    {                                              //~v@@@I~
                    	Rect r=new Rect(xx,yy,xx+bm.getWidth(),yy+bm.getHeight());//~v@@@I~
        			  if (td.isRon())                              //~v@@5R~
                      {                                            //~0401I~
//      				Graphics.drawRect(r,COMPLETE_COLOR,COMPLETE_STROKE_WIDTH);//~v@@5I~//~0401R~
        				Graphics.drawRect(r,COMPLETE_COLOR,AG.aRiver.stroke_width_river);//~0401R~
			        	if (Dump.Y) Dump.println("Hands.drawOpenSub td.isRon()=true stroke_width_river="+AG.aRiver.stroke_width_river);//~0401R~
                      }                                            //~0401I~
        			  else                                         //~v@@5I~
                      {                                            //~0401I~
//                  	Graphics.drawRect(r,HandsTouch.COLOR_SELECTED,HandsTouch.WIDTH_SELECTED_OPEN);//~v@@5I~//~0401R~
                    	Graphics.drawRect(r,HandsTouch.COLOR_SELECTED,AG.aRiver.stroke_width_river);//~0401R~
			        	if (Dump.Y) Dump.println("Hands.drawOpenSub td.isRon()=false stroke_width_river="+AG.aRiver.stroke_width_river);//~0401R~
                      }                                            //~0401I~
                    }                                              //~v@@@I~
                    xx+=stepX; yy+=stepY;                          //~v@@@I~
//                  if (ctr==HANDCTR_TAKEN && ii==ctr-2)           //~v@@@I~//~v@@5R~
                    if (isCtrTaken(ctr) && ii==ctr-2)              //~v@@5I~
                    {                                              //~v@@@M~
                        xx += stepX2;
                        yy += stepY2;
                    }//~v@@@M~
//              }                                                  //~v@@@R~
            }                                                      //~v@@@I~
//          openRectShift=new Rect(xx0,yy0,xx,yy);                 //~0328R~//~0329R~
//            if (Dump.Y) Dump.println("Hands.drawOpenSub openRectShift="+openRectShift.toString());//~0328R~
//            if (pos>=0)                                          //~v@@@R~
//            {                                                    //~v@@@R~
//                xx+=stepX2; yy+=stepY2;                          //~v@@@R~
//                Bitmap bm=pieces.getBitmapRiver(Ptd,Pplayer);    //~v@@@R~
//                Graphics.drawBitmap(canvas,bm,xx,yy);            //~v@@@R~
//            }                                                    //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public  void  clearOpenRect(int Pplayer)                       //~v@@@I~
    {                                                              //~v@@@I~
    //********************                                         //~v@@@I~
//      Rect rect=openRects[Pplayer];                              //~v@@@I~//~0329R~
//      if (Dump.Y) Dump.println("Hands.clearOpenRect player="+Pplayer+",rect="+rect.toString());//~v@@@I~//~9A13I~//~0329R~
//      Graphics.drawRect(rect,bgColor);                         //~v@@@I~//~0328R~//~0329R~
        if (Dump.Y) Dump.println("Hands.clearOpenRect openRectShiftS="+Utils.toString(openRectShiftS));//~0328I~//~0329R~
        Rect rect;                                                 //~0329I~
        rect=openRectShiftS[Pplayer];                              //~0328I~
        if (rect!=null)                                            //~0328I~
        {                                                          //~0328I~
          	Graphics.drawRect(rect,bgColor);                       //~0328I~
        	openRectShiftS[Pplayer]=null;                          //~0328I~
        }                                                          //~0328I~
    }                                                              //~v@@@I~
	//*********************************************************    //~9A13I~
    public  void  clearHands()                                     //~9A13I~
    {                                                              //~9A13I~
    //********************                                         //~9A13I~
        if (Dump.Y) Dump.println("Hands.clearHands of PLAYER_YOU rectHands="+rectHands.toString());//~9A13I~
        Graphics.drawRect(rectHands,bgColor);                      //~9A13I~
    }                                                              //~9A13I~
	//*********************************************************    //~v@@@I~
//  public  int isTouch(int Pxx,int Pyy)                           //~v@@@I~//~v@@5R~
    public  int isTouch(int Pxx,int Pyy,int PxxDown,int PyyDown)   //~v@@5I~
    {                                                              //~v@@@I~
    //********************                                         //~v@@@I~
        if (Dump.Y) Dump.println("Hands.isTouch xx=="+Pxx+",yy="+Pyy+",xxDown="+PxxDown+",yyDown="+PyyDown);//~v@@@R~//~v@@5R~
//      return handsTouch.isTouch(Pxx,Pyy);                        //~v@@5R~
        return handsTouch.isTouch(Pxx,Pyy,PxxDown,PyyDown);        //~v@@5I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
    public int getHandsLeftYou()                                   //~v@@5I~
    {                                                              //~v@@5I~
//      int pos=rectHands.left;                                    //~v@@5I~//~0325R~
        int pos=rectHands0.left;                                   //~0325I~
        if (Dump.Y) Dump.println("Hands.getHandsLeftYou pos="+pos+",rectHands0="+Utils.toString(rectHands0)+",rectHands="+Utils.toString(rectHands));//~v@@5I~//~0326R~
        return pos;                                                //~v@@5I~
    }                                                              //~v@@5I~
	//*********************************************************    //~va65I~
	//*for Test,from RoundStat.discard                             //~va65R~
	//*********************************************************    //~va65I~
    public void drawOpenDiscardRobot(int Pplayer,int Peswn,TileData PtdDiscarded)//~va65I~
    {                                                              //~va65I~
        if (Dump.Y) Dump.println("Hands.drawOpenDiscardRobot player="+Pplayer+",eswn="+Peswn+",PtdDiscarded="+TileData.toString(PtdDiscarded));//~va65I~
        clearOpenRect(Pplayer);     //take may shift out leftmost tile//~va65I~
	    drawOpen(Pplayer,null);                                    //~va65I~
    }                                                              //~va65I~
	//*********************************************************    //~va65I~
	//*for Test,from RoundStat.takeOne                             //~va65I~
	//*********************************************************    //~va65I~
    public void drawOpenTakeOneRobot(int Pplayer,int Peswn,TileData PtdTaken)//~va65I~
    {                                                              //~va65I~
        if (Dump.Y) Dump.println("Hands.drawOpenTakeOneRobot player="+Pplayer+",eswn="+Peswn+",PtdTaken="+TileData.toString(PtdTaken));//~va65I~
	    drawOpen(Pplayer,PtdTaken);                                //~va65I~
    }                                                              //~va65I~
	//*********************************************************    //~va65I~
    public void drawOpenPonKanChiiRobot(int Pplayer)               //~va65I~
    {                                                              //~va65I~
        if (Dump.Y) Dump.println("Hands.drawOpenPonKanChiiRobot player="+Pplayer);//~va65I~
//      clearOpenRect(Pplayer);                                    //~va65R~
	    drawOpen(Pplayer,null);                                    //~va65I~
    }                                                              //~va65I~
}//class Hands                                                 //~dataR~//~@@@@R~//~v@@@R~
