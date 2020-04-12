//*CID://+DATER~: update#= 566;                                    //~v@@@R~//~v@11R~//~9214R~
//**********************************************************************//~v101I~
//v@11 2019/02/02 TakeOne by touch                                 //~v@11I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.Rule;
import com.btmtest.dialog.RuleSetting;                             //~9412R~
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.game.gv.Pieces.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.GConst.*;                           //~v@@@R~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~


public class Stock                                                 //~v@@@R~
{                                                                  //~0914I~
	public  static final int COLOR_BG_STOCK=Color.argb(0xff,0x00,0x40,0x00);//~v@@@I~
	private static final int COLOR_NEXTONE=DiceBox.COLOR_LIGHT_DISCARD_TIMEOUT;//~v@11R~
	private static final int COLOR_NEXTONE_SHADOW=DiceBox.COLOR_FG_DISABLE;	//=Color.argb(0xa0,0x80,0x80,0x80);//~v@11I~
	private static final int COLOR_NEXTONE_RING=Color.argb(0xff,0x00,0x00,0x00);//~v@11I~
    private static final int WIDTH_NEXTONE_RING=2;                 //~v@11R~
    public  static final int SHIFT_BACK=6;                         //~v@@@I~//~9313R~
    public  static final int SHIFT_SIDE=2;                         //~v@@@I~//~9313R~
    private static final int MARGIN_BG=4;                          //~v@@@R~
    private static final int DORA_STOCKPOS=2;	//cutpos -2        //~v@@@I~
    public  static final int DORA_TDPOS=(10-1);   	//[9]:initial dora(backward by ctrKan from shuffeled TileData KEEP_LEFT(14)(2*5dora)+2*2(kan)//~v@@@R~//~9217R~
    private static final int DEALCTR=(PLAYERS*HANDCTR)/2;	//deal initally (4*(4*3+1))/2=26 stock//~v@@@R~
                                                                   //~v@11I~
	private static final int ALLOWANCE_V =20; //verticaol allowance//~v@11I~
	private static final int ALLOWANCE_H =20; //horizonta allowance for left/right end piece//~v@11I~
                                                                   //~v@@@I~
    private static final int SPACING_X      =1;                    //~v@@@I~
    private static final int SPACING_Y      =2;                    //~v@@@I~
    private static final int MAXCTR_DORA=MAXCTR_KAN+1;             //~0328I~
                                                                   //~v@@@I~
	private GCanvas gcanvas;                                       //~v@@@R~
    private MJTable table;                                         //~v@@@I~
//    private Tiles tiles;                                         //~v@@@R~
    private Pieces pieces;//~v@@@I~
    private Rect[]  rectsStock;
    private Point[] pointsStock;//~v@@@I~
//  private Canvas canvas;                                         //~v@@@I~//~9529R~
    private Bitmap[] bmsStock;                                     //~v@@@R~
    public  Rect[] rectsBG;                                        //~v@@@R~
    public  int pieceW,pieceH;                                     //~v@@@I~//~9217R~
    private TileData[] shuffled;                                   //~v@@@I~
    private Bitmap[][][] bmsssRiver;  //[standing/lying][man/pin/sou/ji][number]//~v@@@I~
    private int nextStock,stockPlayer;                             //~v@@@I~
    private int nextStockKan,stockPlayerKan;                       //~v@@@I~
    private Point pointDora;                                       //~v@@@I~
    private Rect lastRectDora;                                     //~v@11I~
    private int  lastPosDora;                                      //~v@11I~
    public int ctrKanDrawn=0;                                     //~v@@@I~//~9217R~
    private Rect rectNextStock,rectNextStockKan;                   //~v@11R~
    private int  playerNextStock,playerNextStockKan;               //~v@11R~
    private Rect[] drawnRectDora=new Rect[1+MAXCTR_KAN];                //~9214I~//~9217R~
    private int[]  drawnPlayerDora=new int[1+MAXCTR_KAN];               //~9214I~//~9217R~
    private int ctrDrawDora;                                       //~9214I~
//  private Rect[] rectCompleteDora=new Rect[MAXCTR_KAN];          //~9503I~//~0328R~
    private Rect[] rectCompleteDora=new Rect[MAXCTR_DORA];         //~0328I~
    private int ctrDoraComplete;                                   //~9503I~
//*************************                                        //~v@@@I~
	public Stock(GCanvas Pgcanvas)                                 //~v@@@R~
    {                                                              //~0914I~
    	AG.aStock=this;                                            //~v@@@I~
        gcanvas = Pgcanvas;                                          //~v@@@I~
        table = gcanvas.table;                                     //~v@@@I~
//        tiles=AG.aTiles;                                         //~v@@@R~
        pieces=table.pieces;                                       //~v@@@I~
//      bmsStock=pieces.bitmapStock[PIECE_STOCK_STOCK];            //~v@@@I~//~0216R~
        bmsStock=AG.bitmapStock[PIECE_STOCK_STOCK];                //~0216I~
        pieceW=table.stockPieceW;                                  //~v@@@I~
        pieceH=table.stockPieceH;                                  //~v@@@I~
//      bmsssRiver=pieces.bitmapAllPiecesRiver;                    //~v@@@I~//~0216R~
        bmsssRiver=AG.bitmapAllPiecesRiver;                        //~0216I~
    }
	//*********************************************************    //~v@@@I~
	//*from GCanvas at init                                        //~v@@@I~
	//*********************************************************    //~v@@@I~
//  public void drawInitial(Canvas Pcanvas)                        //~v@@@I~//~9529R~
    public void drawInitial()                                      //~9529I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawInitial");             //~v@@@I~
        pointsStock=new Point[PLAYERS];                            //~v@@@I~
        pointsStock[PLAYER_YOU]   =table.getStockPos(PLAYER_YOU);  //~v@@@R~
        pointsStock[PLAYER_RIGHT] =table.getStockPos(PLAYER_RIGHT);//~v@@@R~
        pointsStock[PLAYER_FACING]=table.getStockPos(PLAYER_FACING);//~v@@@R~
        pointsStock[PLAYER_LEFT]  =table.getStockPos(PLAYER_LEFT); //~v@@@R~
        rectsBG=getBGRect();                                       //~v@@@I~
//      drawYou(Pcanvas,STOCKCTR_EACH,false/*reverse*/);           //~v@@@R~//~9529R~
//      drawRight(Pcanvas,STOCKCTR_EACH,false);                    //~v@@@R~//~9529R~
//      drawFacing(Pcanvas,STOCKCTR_EACH,false);                   //~v@@@R~//~9529R~
//      drawLeft(Pcanvas,STOCKCTR_EACH,false);                     //~v@@@R~//~9529R~
        drawInitialStock();                                        //~9529I~
        AG.aHands.notifyStockRect(rectsBG);                        //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~9529I~
    private void drawInitialStock()                                //~9529I~
    {                                                              //~9529I~
        drawYou(STOCKCTR_EACH,false/*reverse*/);                   //~9529I~
        drawRight(STOCKCTR_EACH,false);                            //~9529I~
        drawFacing(STOCKCTR_EACH,false);                           //~9529I~
        drawLeft(STOCKCTR_EACH,false);                             //~9529I~
    }                                                              //~9529I~
	//*********************************************************    //~9503I~
	public void newGame()                                          //~9503I~
    {                                                              //~9503I~
        if (Dump.Y) Dump.println("Stock.newGame ctrDoraComplete="+ctrDoraComplete);//~9503I~
        drawInitialStock();                                        //~9529I~
    	for (int ii=0;ii<ctrDoraComplete;ii++)                         //~9503I~
        {                                                          //~9503I~
	    	Rect r=rectCompleteDora[ii];                           //~9503I~
        	Graphics.drawRect(r,COLOR_BG_TABLE);                   //~9503I~
        }                                                          //~9503I~
	    ctrDoraComplete=0;                                         //~9503I~
    	ctrKanDrawn=0;                                             //+0409I~
    }                                                              //~9503I~
	//*********************************************************    //~v@@@M~
	//*set background rect shifted                                 //~v@@@R~
	//*********************************************************    //~v@@@M~
	private Rect[] getBGRect()                                     //~v@@@R~
    {                                                              //~v@@@M~
        int diffB=SHIFT_BACK;                                      //~v@@@M~
        int diffS=SHIFT_SIDE;                                      //~v@@@M~
        int ww=pieceW;                                             //~v@@@M~
        int hh=pieceH;                                             //~v@@@M~
        int ll=table.stockLength;                                  //~v@@@M~
        int mm=MARGIN_BG;                                          //~v@@@I~
        Point p;                                                   //~v@@@M~
    //**********************                                       //~v@@@M~
    	Rect[] rects=new Rect[PLAYERS];                            //~v@@@M~
        p=pointsStock[PLAYER_YOU];                                 //~v@@@R~
        rects[PLAYER_YOU]    =new Rect(p.x-mm,        p.y-mm,             p.x+ll+mm,       p.y+hh+diffB+mm);//~v@@@R~
        p=pointsStock[PLAYER_RIGHT];                               //~v@@@R~
        rects[PLAYER_RIGHT]  =new Rect(p.x-mm,        p.y+ww-ll-diffS-mm, p.x+hh+diffB+mm, p.y+ww+mm);//~v@@@R~
        p=pointsStock[PLAYER_FACING];                              //~v@@@R~
        rects[PLAYER_FACING] =new Rect(p.x+ww-ll-mm,  p.y-diffB-mm,       p.x+ww+mm,       p.y+hh+mm);//~v@@@R~
        p=pointsStock[PLAYER_LEFT];                                //~v@@@R~
        rects[PLAYER_LEFT]   =new Rect(p.x-diffB-mm,  p.y-diffS-mm,       p.x+hh+mm,       p.y+ll+mm);//~v@@@R~
        if (Dump.Y) Dump.println("Stock.getBGRect stocklength="+ll+",hh="+hh);//~v@@@M~
        return rects;                                              //~v@@@M~
    }                                                              //~v@@@M~
    //*************************************************************//~v@@@I~
    //*draw anti-clockwise                                         //~v@@@I~
    //*************************************************************//~v@@@I~
//  private void drawYou(Canvas Pcanvas,int Pctr,boolean Pswreverse)//~v@@@R~//~9529R~
    private void drawYou(int Pctr,boolean Pswreverse)              //~9529I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawYou");                 //~v@@@I~
//      Graphics.drawRect(Pcanvas,rectsBG[PLAYER_YOU],COLOR_BG_STOCK);//~v@@@R~//~9529R~
        Graphics.drawRect(rectsBG[PLAYER_YOU],COLOR_BG_STOCK);     //~9529I~
        Bitmap bm=bmsStock[PLAYER_YOU];                            //~v@@@R~
        Point p=pointsStock[PLAYER_YOU];                           //~v@@@I~
        int xx=p.x;                                                //~v@@@I~
        int yy=p.y;                                                //~v@@@I~
        if (!Pswreverse)                                            //~v@@@R~
        {                                                          //~v@@@I~
        	xx+=(STOCKCTR_EACH-Pctr)*(pieceW+SPACING_X);           //~v@@@R~
        }                                                          //~v@@@I~
        for (int kk=0;kk<Pctr;kk++)                                //~v@@@R~
        {                                                          //~v@@@I~
//      	Graphics.drawBitmap(Pcanvas,bm,xx,yy+SHIFT_BACK);      //~v@@@I~//~9529R~
        	Graphics.drawBitmap(bm,xx,yy+SHIFT_BACK);              //~9529I~
//          Graphics.drawBitmap(Pcanvas,bm,xx,yy);                 //~v@@@I~//~9529R~
            Graphics.drawBitmap(bm,xx,yy);                         //~9529I~
            xx += SPACING_X + pieceW;                              //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
	private void drawYouDora(int Ppos,TileData Ptd)                //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawYouDora pos="+Ppos);   //~v@@@R~
		Point p=getPiecePointUpperYou(Ppos);                        //~v@@@I~
        Bitmap bm=getBitmapDora(PLAYER_YOU,Ptd);                   //~v@@@I~
//      Graphics.drawBitmap(canvas,bm,p.x,p.y);                    //~v@@@R~
        Rect r=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@@@I~
        Graphics.drawBitmap(r,bm);                           //~v@@@I~
        lastRectDora=r;                                            //~v@11I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*draw upper on nomal pos,lower down shifted                  //~v@@@I~
    //*************************************************************//~v@@@I~
	private Point getPiecePointUpperYou(int Ppos)                  //~v@@@R~
    {                                                              //~v@@@I~
    	int xx,yy;                                                 //~v@@@I~
    //*******************:                                         //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getPiecePointYou");        //~v@@@I~
        Point p=pointsStock[PLAYER_YOU];                           //~v@@@I~
        xx=p.x+(STOCKCTR_EACH-Ppos)*(pieceW+SPACING_X);        //~v@@@R~
        yy=p.y;                                                //~v@@@I~
        return new Point(xx,yy);                                   //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*lower is down shifted                                       //~v@@@I~
    //*************************************************************//~v@@@I~
	private void drawStockTakenYou(int Ppos,int Player,boolean Pswkan)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawStockTakenYou");       //~v@@@R~
		Rect r=getPieceRectYou(Ppos,Player);                       //~v@@@R~
        Bitmap bm=bmsStock[PLAYER_YOU];                            //~v@@@I~
		                                                           //~v@@@I~
		Graphics.drawRectBitmap(r,COLOR_BG_STOCK,(Player==0 ? bm: null),r.left,r.top+SHIFT_BACK);//~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*include both upper and lower(down shifted)                  //~v@@@I~
    //*************************************************************//~v@@@I~
	private Rect getPieceRectYou(int Ppos,int Player)              //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getPieceRectYou");         //~v@@@I~
        Point p=getPiecePointUpperYou(Ppos);                       //~v@@@R~
        return new Rect(p.x,p.y,p.x+pieceW,p.y+pieceH+SHIFT_BACK);	//include both upper/lower//~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*unti-clockwize                                              //~v@@@I~
    //*************************************************************//~v@@@I~
//  private void drawRight(Canvas Pcanvas,int Pctr,boolean Pswreverse)//~v@@@R~//~9529R~
    private void drawRight(int Pctr,boolean Pswreverse)            //~9529I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawRight ctr="+Pctr+",swreverse="+Pswreverse);//~v@@@R~
//      Graphics.drawRect(Pcanvas,rectsBG[PLAYER_RIGHT],COLOR_BG_STOCK);//~v@@@R~//~9529R~
        Graphics.drawRect(rectsBG[PLAYER_RIGHT],COLOR_BG_STOCK);   //~9529I~
        Bitmap bm=bmsStock[PLAYER_RIGHT];                          //~v@@@R~
        Point p=pointsStock[PLAYER_RIGHT];                         //~v@@@I~
        int xx=p.x;                                                //~v@@@I~
        int yy=p.y;                                                //~v@@@I~
        if (!Pswreverse)                                            //~v@@@I~
        {                                                          //~v@@@I~
        	yy-=(STOCKCTR_EACH-Pctr)*(pieceW+SPACING_X);           //~v@@@R~
        }                                                          //~v@@@I~
        for (int kk=0;kk<Pctr;kk++)                                //~v@@@R~
        {                                                          //~v@@@I~
//      	Graphics.drawBitmap(Pcanvas,bm,xx,yy);                 //~v@@@I~//~9529R~
        	Graphics.drawBitmap(bm,xx,yy);                         //~9529I~
//          Graphics.drawBitmap(Pcanvas,bm,xx+SHIFT_BACK,yy-SHIFT_SIDE);//~v@@@I~//~9529R~
            Graphics.drawBitmap(bm,xx+SHIFT_BACK,yy-SHIFT_SIDE);   //~9529I~
            yy -= SPACING_X + pieceW;                              //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
	private void drawRightDora(int Ppos,TileData Ptd)              //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawRightDora");           //~v@@@I~
		Point p=getPiecePointUpperRight(Ppos);                      //~v@@@I~
        Bitmap bm=getBitmapDora(PLAYER_RIGHT,Ptd);                 //~v@@@I~
//      Graphics.drawBitmap(canvas,bm,p.x,p.y);                    //~v@@@R~
        Rect r=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@@@I~
        Graphics.drawBitmap(r,bm);                           //~v@@@I~
        lastRectDora=r;                                            //~v@11I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*draw upper right/up shifted                                 //~v@@@I~
    //*************************************************************//~v@@@I~
	private Point getPiecePointUpperRight(int Ppos)                //~v@@@R~
    {                                                              //~v@@@I~
    	int xx,yy;                                                 //~v@@@I~
    //*******************:                                         //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getPiecePointRight");      //~v@@@I~
        Point p=pointsStock[PLAYER_RIGHT];                         //~v@@@I~
        xx=p.x+SHIFT_BACK;                                     //~v@@@I~
        yy=p.y-(STOCKCTR_EACH-Ppos)*(pieceW+SPACING_X)-SHIFT_SIDE;//~v@@@R~
        return new Point(xx,yy);                                   //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
	private void drawStockTakenRight(int Ppos,int Player,boolean Pswkan)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawStockTakenRight");     //~v@@@I~
		Rect r=getPieceRectRight(Ppos,Player);                     //~v@@@R~
        Bitmap bm=bmsStock[PLAYER_RIGHT];                          //~v@@@I~
		Graphics.drawRectBitmap(r,COLOR_BG_STOCK,(Player==0 ? bm : null),r.left,r.top+SHIFT_SIDE); //lower//~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*include both upper(right-up shifted) and lower              //~v@@@I~
    //*************************************************************//~v@@@I~
	private Rect getPieceRectRight(int Ppos,int Player)            //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getPieceRectRight");       //~v@@@I~
        Point p=getPiecePointUpperRight(Ppos);                     //~v@@@I~
        return new Rect(p.x-SHIFT_BACK,p.y,p.x+pieceH,p.y+pieceW+SHIFT_SIDE);//~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*draw anti-clockwise                                         //~v@@@I~
    //*************************************************************//~v@@@I~
//  private void drawFacing(Canvas Pcanvas,int Pctr,boolean Pswreverse)//~v@@@R~//~9529R~
    private void drawFacing(int Pctr,boolean Pswreverse)           //~9529I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawFacing");              //~v@@@I~
//      Graphics.drawRect(Pcanvas,rectsBG[PLAYER_FACING],COLOR_BG_STOCK);//~v@@@R~//~9529R~
        Graphics.drawRect(rectsBG[PLAYER_FACING],COLOR_BG_STOCK);  //~9529I~
        Bitmap bm=bmsStock[PLAYER_FACING];                         //~v@@@R~
        Point p=pointsStock[PLAYER_FACING];                        //~v@@@I~
        int xx=p.x;                                                //~v@@@I~
        int yy=p.y;                                                //~v@@@I~
        if (!Pswreverse)                                            //~v@@@I~
        {                                                          //~v@@@I~
        	xx-=(STOCKCTR_EACH-Pctr)*(pieceW+SPACING_X);           //~v@@@R~
        }                                                          //~v@@@I~
        for (int kk=0;kk<Pctr;kk++)                                //~v@@@R~
        {                                                          //~v@@@I~
//      	Graphics.drawBitmap(Pcanvas,bm,xx,yy);                 //~v@@@I~//~9529R~
        	Graphics.drawBitmap(bm,xx,yy);                         //~9529I~
//          Graphics.drawBitmap(Pcanvas,bm,xx,yy-SHIFT_BACK);      //~v@@@I~//~9529R~
            Graphics.drawBitmap(bm,xx,yy-SHIFT_BACK);              //~9529I~
            xx -= SPACING_X + pieceW;                              //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
	private void drawFacingDora(int Ppos,TileData Ptd)             //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawFaicingDora");         //~v@@@I~
		Point p=getPiecePointUpperFacing(Ppos);                     //~v@@@I~
        Bitmap bm=getBitmapDora(PLAYER_FACING,Ptd);               //~v@@@I~
//      Graphics.drawBitmap(canvas,bm,p.x,p.y);                    //~v@@@R~
        Rect r=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@@@I~
        Graphics.drawBitmap(r,bm);                           //~v@@@I~
        lastRectDora=r;                                            //~v@11I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*draw upper up shifted                                       //~v@@@I~
    //*************************************************************//~v@@@I~
	private Point getPiecePointUpperFacing(int Ppos)               //~v@@@R~
    {                                                              //~v@@@I~
    	int xx,yy;                                                 //~v@@@I~
    //*******************:                                         //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getPiecePointFacing");     //~v@@@I~
        Point p=pointsStock[PLAYER_FACING];                        //~v@@@I~
        xx=p.x-(STOCKCTR_EACH-Ppos)*(pieceW+SPACING_X);        //~v@@@R~
        yy=p.y-SHIFT_BACK;                                     //~v@@@I~
        return new Point(xx,yy);                                   //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
	private void drawStockTakenFacing(int Ppos,int Player,boolean Pswkan)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawStockTakenFacing");    //~v@@@I~
		Rect r=getPieceRectFacing(Ppos,Player);                    //~v@@@R~
        Bitmap bm=bmsStock[PLAYER_FACING];                         //~v@@@I~
		Graphics.drawRectBitmap(r,COLOR_BG_STOCK,(Player==0 ? bm : null),r.left,r.top+SHIFT_BACK);//this lower//~v@@@R~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*include both upper(up shifted) and lower                    //~v@@@I~
    //*************************************************************//~v@@@I~
	private Rect getPieceRectFacing(int Ppos,int Player)           //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getPieceRectFacing");      //~v@@@I~
        Point p=getPiecePointUpperFacing(Ppos);                    //~v@@@I~
        return new Rect(p.x,p.y,p.x+pieceW,p.y+pieceH+SHIFT_BACK);	//include both upper/lower//~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*draw anti-clockwise                                         //~v@@@I~
    //*************************************************************//~v@@@I~
//  private void drawLeft(Canvas Pcanvas,int Pctr,boolean Pswreverse)//~v@@@R~//~9529R~
    private void drawLeft(int Pctr,boolean Pswreverse)             //~9529I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawLeft");                //~v@@@I~
//      Graphics.drawRect(Pcanvas,rectsBG[PLAYER_LEFT],COLOR_BG_STOCK);//~v@@@R~//~9529R~
        Graphics.drawRect(rectsBG[PLAYER_LEFT],COLOR_BG_STOCK);    //~9529I~
        Bitmap bm=bmsStock[PLAYER_LEFT];                           //~v@@@R~
        Point p=pointsStock[PLAYER_LEFT];                          //~v@@@I~
        int xx=p.x;                                           //~v@@@I~
        int yy=p.y;                                                //~v@@@R~
        if (!Pswreverse)                                            //~v@@@I~
        {                                                          //~v@@@I~
        	yy+=(STOCKCTR_EACH-Pctr)*(pieceW+SPACING_X);           //~v@@@R~
        }                                                          //~v@@@I~
        for (int kk=0;kk<Pctr;kk++)                                //~v@@@R~
        {                                                          //~v@@@I~
//      	Graphics.drawBitmap(Pcanvas,bm,xx,yy);                 //~v@@@I~//~9529R~
        	Graphics.drawBitmap(bm,xx,yy);                         //~9529I~
//          Graphics.drawBitmap(Pcanvas,bm,xx-SHIFT_BACK,yy-SHIFT_SIDE);//~v@@@I~//~9529R~
            Graphics.drawBitmap(bm,xx-SHIFT_BACK,yy-SHIFT_SIDE);   //~9529I~
            yy += SPACING_X + pieceW;                              //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
	private void drawLeftDora(int Ppos,TileData Ptd)               //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawLeftDora");            //~v@@@I~
		Point p=getPiecePointUpperLeft(Ppos);                       //~v@@@I~
        Bitmap bm=getBitmapDora(PLAYER_LEFT,Ptd);                  //~v@@@I~
//      Graphics.drawBitmap(canvas,bm,p.x,p.y);                    //~v@@@R~
        Rect r=new Rect(p.x,p.y,p.x+bm.getWidth(),p.y+bm.getHeight());//~v@@@I~
        Graphics.drawBitmap(r,bm);                           //~v@@@I~
        lastRectDora=r;                                            //~v@11I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*draw upper left/up shifted                                  //~v@@@I~
    //*************************************************************//~v@@@I~
	private Point getPiecePointUpperLeft(int Ppos)                 //~v@@@R~
    {                                                              //~v@@@I~
    	int xx,yy;                                                 //~v@@@I~
    //*******************:                                         //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getPiecePointLeft");       //~v@@@I~
        Point p=pointsStock[PLAYER_LEFT];                          //~v@@@I~
        xx=p.x-SHIFT_BACK;                                     //~v@@@I~
        yy=p.y+(STOCKCTR_EACH-Ppos)*(pieceW+SPACING_X)-SHIFT_SIDE;//~v@@@R~
        return new Point(xx,yy);                                   //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
	private void drawStockTakenLeft(int Ppos,int Player,boolean Pswkan)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawStockTakenLeft pos="+Ppos+",layer="+Player+",swkan="+Pswkan);//~v@@@R~
		Rect r=getPieceRectLeft(Ppos,Player);                      //~v@@@I~
        Bitmap bm=bmsStock[PLAYER_LEFT];                           //~v@@@R~
//        canvas=Graphics.lockCanvas(r);                           //~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            Graphics.drawRect(canvas,r,COLOR_BG_STOCK); //background clear of th layer//~v@@@R~
//            if (Player==0)  //upper taken                        //~v@@@R~
//                Graphics.drawBitmap(canvas,bm,r.left+SHIFT_BACK,r.top+SHIFT_SIDE); //lower//~v@@@R~
//            if (Ppos!=STOCKCTR_EACH && !Pswkan)                  //~v@@@R~
//                Graphics.drawBitmap(canvas,bm,r.left+SHIFT_BACK,r.top+SHIFT_SIDE-(pieceW+SPACING_X));//lower of next stock overrided by current//~v@@@R~
//        }                                                        //~v@@@R~
//        catch(Exception e)                                       //~v@@@R~
//        {                                                        //~v@@@R~
//            Dump.println(e,"Stock.drawStockTakenLeft");          //~v@@@R~
//        }                                                        //~v@@@R~
//        Graphics.unlockCanvas(canvas);                           //~v@@@R~
//        Graphics.drawRectBitmap(r,COLOR_BG_STOCK, bm,r.left,r.top+SHIFT_BACK);//this lower//~v@@@R~
//        if (Player==0)  //upper taken                              //~v@@@R~//~v@11R~
//            Graphics.drawRectBitmap(r,COLOR_BG_STOCK,bm,r.left+SHIFT_BACK,r.top+SHIFT_SIDE); //lower//~v@@@R~//~v@11R~
//        else                                                       //~v@@@R~//~v@11R~
//        {                                                        //~v@11I~
//          if (Ppos!=STOCKCTR_EACH && !Pswkan)                        //~v@@@R~//~v@11R~
//              Graphics.drawRectBitmap(r,COLOR_BG_STOCK,bm,r.left+SHIFT_BACK,r.top+SHIFT_SIDE-(pieceW+SPACING_X));//lower of next stock overrided by current//~v@@@R~//~v@11R~
//          else                                                       //~v@@@R~//~v@11R~
//              Graphics.drawRect(r,COLOR_BG_STOCK); //background clear of the layer//~v@@@R~//~v@11R~
//        }                                                        //~v@11I~
		Graphics.drawRectBitmap(r,COLOR_BG_STOCK,(Player==0 ? bm : null),r.left+SHIFT_BACK,r.top+SHIFT_SIDE); //lower//~v@11M~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    //*include both upper(left-up shifted) and lower               //~v@@@I~
    //*************************************************************//~v@@@I~
	private Rect getPieceRectLeft(int Ppos,int Player)             //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getPieceRectLeft");        //~v@@@R~
        Point p=getPiecePointUpperLeft(Ppos);                      //~v@@@R~
        return new Rect(p.x,p.y,p.x+pieceH+SHIFT_BACK,p.y+pieceW+SHIFT_SIDE);//~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void drawDeal(int Pcutpos)                              //~v@@@R~
    {                                                              //~v@@@I~
     // 	AG.aDiceBox.drawLight(null/*canvas*/,true/*light on*/);	//TODO test//~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawDeal cutpos="+Pcutpos);//~v@@@I~
	    drawDeal(0/*Pplayer*/,Pcutpos);                         //~v@@@I~
//        int cutPlayer=(Pcutpos-1)%PLAYERS;                       //~v@@@R~
//        shuffled=AG.aTiles.getShuffled();                        //~v@@@R~
//        Point p=getStockPosDora(cutPlayer,Pcutpos);              //~v@@@R~
//        pointDora=p;                                             //~v@@@R~
//        int player=p.x;                                          //~v@@@R~
//        int pos=p.y;                                             //~v@@@R~
//                                                                 //~v@@@R~
//        Rect rectbg=rectsBG[cutPlayer];                          //~v@@@R~
//            drawDealPlayer(rectbg,cutPlayer,Pcutpos,false/*reverse*/);//~v@@@R~
//                                                                 //~v@@@R~
//        if (player==cutPlayer)                                   //~v@@@R~
//            drawDora(player,pos,0/*ctrKan*/);                    //~v@@@R~
//        else                                                     //~v@@@R~
//        {                                                        //~v@@@R~
//                drawDora(player,pos,0/*ctrKan*/);                //~v@@@R~
//        }                                                        //~v@@@R~
//        drawRemainingStock(cutPlayer,Pcutpos);                   //~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void drawDeal(int Pplayer,int Pcutpos)                  //~v@@@I~
    {                                                              //~v@@@I~
    	ctrDrawDora=0;                                             //~9214I~
        int dist=(Pcutpos-1)%PLAYERS;                              //~v@@@I~
        int cutPlayer=Players.nextPlayer(Pplayer,dist);            //~v@@@I~
        int eswn=Accounts.playerToEswn(cutPlayer);                  //~9607I~
//      AG.aAccounts.setCutPlayer(cutPlayer,dist/*eswn*/);         //~9530I~//~9607R~
        AG.aAccounts.setCutPlayer(cutPlayer,eswn);                 //~9607I~
        if (Dump.Y) Dump.println("Stock.drawDeal player="+Pplayer+",dist="+dist+",eswn="+eswn+",cutPlayer="+cutPlayer+",cutpos="+Pcutpos);//~v@@@I~//~9607R~
        shuffled=AG.aTiles.getShuffled();                          //~v@@@I~
	    Point p=getStockPosDora(cutPlayer,Pcutpos);                //~v@@@I~
        pointDora=p;                                               //~v@@@I~
    	int player=p.x;                                            //~v@@@I~
        int pos=p.y;                                               //~v@@@I~
                                                                   //~v@@@I~
        Rect rectbg=rectsBG[cutPlayer];                            //~v@@@I~
        drawDealPlayer(rectbg,cutPlayer,Pcutpos,false/*reverse*/); //~v@@@I~
                                                                   //~v@@@I~
//        if (player==cutPlayer)                                   //~v@@@I~
            drawDora(player,pos,0/*ctrKan*/);                      //~v@@@I~
//        else                                                     //~v@@@I~
//        {                                                        //~v@@@I~
//            drawDora(player,pos,0/*ctrKan*/);                    //~v@@@I~
//        }                                                        //~v@@@I~
        drawRemainingStock(cutPlayer,Pcutpos);                     //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void drawDealPlayer(Rect Prect,int Pplayer,int Pcutpos,boolean Pswreverse)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("drawDealPlayer player="+Pplayer+",cutpos="+Pcutpos+",swreverse="+Pswreverse);//~v@@@I~
        if (Dump.Y) Dump.println("drawDealPlayer rect l="+Prect.left+",t="+Prect.top+",r="+Prect.right+",b="+Prect.bottom);//~v@@@I~
//      canvas=Graphics.lockCanvas(Prect);                         //~v@@@I~//~9529R~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
		    drawDealPlayer(Pplayer,Pcutpos,Pswreverse);            //~v@@@I~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
            Dump.println(e,"Stock.drawDealPlayer");                //~v@@@I~
        }                                                          //~v@@@I~
//      Graphics.unlockCanvas(canvas);                             //~v@@@I~//~9529R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void drawDealPlayer(int Pplayer,int Pcutpos,boolean Pswreverse)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("drawDealPlayer player="+Pplayer+",cutpos="+Pcutpos+",swreverse="+Pswreverse);//~v@@@I~
    	switch(Pplayer)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
//      	drawYou(canvas,Pcutpos,Pswreverse);                    //~v@@@I~//~9529R~
        	drawYou(Pcutpos,Pswreverse);                           //~9529I~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
//      	drawRight(canvas,Pcutpos,Pswreverse);                  //~v@@@I~//~9529R~
        	drawRight(Pcutpos,Pswreverse);                         //~9529I~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
//      	drawFacing(canvas,Pcutpos,Pswreverse);                 //~v@@@I~//~9529R~
        	drawFacing(Pcutpos,Pswreverse);                        //~9529I~
            break;                                                 //~v@@@I~
        default:                                                   //~v@@@I~
//      	drawLeft(canvas,Pcutpos,Pswreverse);                   //~v@@@I~//~9529R~
        	drawLeft(Pcutpos,Pswreverse);                          //~9529I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void drawDora(int Pplayer,int Ppos,int PctrKan)        //~v@@@R~
    {                                                              //~v@@@I~
        if (shuffled==null)	//client                               //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y)Dump.println("Stock.drawDora shuffle=null");//~v@@@I~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
//  	TileData td=shuffled[DORA_TDPOS];          //5             //~v@@@R~
    	TileData td=shuffled[DORA_TDPOS-PctrKan*STOCK_LAYER];      //~v@@@R~
        if (Dump.Y)Dump.println("Stock.drawDora of shuffled player="+Pplayer+",Ppos="+Ppos+",ctrKan="+PctrKan+",td:"+td.toString());//~v@@@I~
        if (Dump.Y)Dump.println("Stock.drawDora of shuffled:"+TileData.toString(shuffled,0,DORA_TDPOS+1));//~v@@@R~
	    drawDora(Pplayer,Ppos,PctrKan,td);                         //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void drawDora(int Pplayer,int Ppos,int PctrKan,TileData Ptd)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y)Dump.println("Stock.drawDora player="+Pplayer+",pos="+Ppos+",ctrKan="+PctrKan+",type="+Ptd.type+",no="+Ptd.number+",dora="+Ptd.isRed5());//~v@@@R~
    	switch(Pplayer)                                             //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
	        drawYouDora(Ppos,Ptd);                                 //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
	        drawRightDora(Ppos,Ptd);                               //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
	        drawFacingDora(Ppos,Ptd);                              //~v@@@R~
            break;                                                 //~v@@@I~
        default:                                                   //~v@@@I~
	        drawLeftDora(Ppos,Ptd);                                //~v@@@R~
        }                                                          //~v@@@I~
        Ptd.setDora();                                              //~v@@@I~
//      drawnRectDora[ctrDrawDora]=lastRectDora;                   //~9214R~//~9217R~
//      drawnPlayerDora[ctrDrawDora]=Pplayer;                      //~9214R~//~9217R~
//      ctrDrawDora++;                                             //~9214I~//~9217R~
        drawnRectDora[PctrKan]=lastRectDora;                       //~9217I~
        drawnPlayerDora[PctrKan]=Pplayer;                          //~9217I~
        if (Dump.Y)Dump.println("Stock.drawDora lastrect="+lastRectDora.toString());//~9214R~//~9217R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //* return TileData and set pos                                //~v@@@I~
    //*********************************************************    //~v@@@I~
    public TileData getTileDora(int PcutPlayer,int Pcutpos,int PctrKan,Point Poutpos)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getDora Pplayer="+PcutPlayer+",Pcutpos="+Pcutpos+",ctrKan="+PctrKan);//~v@@@I~
	    Point p=getStockPosDora(PcutPlayer,Pcutpos);               //~v@@@I~
    	TileData td=shuffled[DORA_TDPOS-PctrKan*STOCK_LAYER];      //~v@@@I~
        Poutpos.x=p.x;	//cutplayer                                //~v@@@I~
        Poutpos.y=p.y;	//cutpos                                   //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getDora player="+Poutpos.x+",pos="+Poutpos.y+",type="+td.type+",num="+td.number+",dora5="+td.isRed5());//~v@@@I~
        return td;                                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void takeOne(int Pplayer,int Ppos,boolean Pswkan)      //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.takeOne player="+Pplayer+",pos="+Ppos+",swkan="+Pswkan);//~v@@@R~
        int pos=Ppos/STOCK_LAYER+1;	//pos from backend 0,1-->1     //~v@@@R~
        int layer=Ppos%STOCK_LAYER;                                //~v@@@I~
    	switch(Pplayer)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
	        drawStockTakenYou(pos,layer,Pswkan);                   //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_RIGHT:                                         //~v@@@I~
	        drawStockTakenRight(pos,layer,Pswkan);                 //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
	        drawStockTakenFacing(pos,layer,Pswkan);                //~v@@@R~
            break;                                                 //~v@@@I~
        default:                                                   //~v@@@I~
	        drawStockTakenLeft(pos,layer,Pswkan);                  //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*************************************************************//~v@@@I~
    private Bitmap getBitmapDora(int Pplayer,TileData Ptd)         //~v@@@I~
    {                                                              //~v@@@I~
    	int ww,hh;                                                 //~v@@@I~
    //************************                                     //~v@@@I~
    	switch(Pplayer)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_YOU:                                           //~v@@@I~
        case PLAYER_FACING:                                        //~v@@@I~
	    	ww=pieceW; hh=pieceH;                                  //~v@@@I~
            break;                                                 //~v@@@I~
        default:                                                   //~v@@@I~
	    	ww=pieceH; hh=pieceW;                                  //~v@@@I~
    	}                                                          //~v@@@I~
    	int num=Ptd.number;                                           //~v@@@I~
        if (Ptd.type!=TT_JI)                //~v@@@I~
        {                                                          //~v@@@I~
        	if ((Ptd.flag & TDF_RED5)!=0)                          //~v@@@R~
	        	num=0;                                             //~v@@@I~
            else                                                   //~v@@@I~
            	num++;                                             //~v@@@I~
        }                                                          //~v@@@I~
        Bitmap bm=bmsssRiver[Pplayer][Ptd.type][num];              //~v@@@I~
        Bitmap bmout=Pieces.scaleImage(bm,ww,hh);                  //~v@@@I~
        return bmout;                                              //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public  Point getStockPosDora(int Pplayer,int Pcutpos)         //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getStockPosDora player="+Pplayer+",pos="+Pcutpos);//~v@@@R~
    	int player=Pplayer;                                         //~v@@@I~
    	int pos=Pcutpos-DORA_STOCKPOS; //cutpos-2:remain ctr       //~v@@@R~
        if (pos==0)                                                //~v@@@R~
        {                                                          //~v@@@I~
        	player=Players.nextPlayer(Pplayer);                     //~v@@@R~
			pos=STOCKCTR_EACH;                                     //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getStockPosDora player="+player+",pos="+pos);//~v@@@R~
        Point p=new Point(player,pos);                              //~v@@@I~
        return p;                                                  //~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*pointDora x:player,y:pos on 17 stocks                       //~v@@@I~
    //*********************************************************    //~v@@@I~
    private Point getStockPosKan(int Pplayer,int Pcutpos,int Pctrkan)  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getStockPosKan player="+Pplayer+",pos="+Pcutpos);//~v@@@I~
    	int player=Pplayer;                                        //~v@@@I~
    	int pos=Pcutpos*STOCK_LAYER-(Pctrkan-1); //remain ctr      //~v@@@R~
        if (pos==0)                                                //~v@@@I~
        {                                                          //~v@@@I~
        	player=Players.nextPlayer(Pplayer);                    //~v@@@I~
			pos=STOCKCTR_EACH;                                     //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getStockPosKan player="+player+",pos="+pos+",ctrKan="+Pctrkan);//~v@@@I~
        return new Point(player,pos);                      //~v@@@R~
    }                                                              //~v@@@I~
    //*****************************************************************//~v@@@I~
    private void drawRemainingStock(int Pplayer,int Pcutpos)      //~v@@@I~
    {                                                              //~v@@@I~
        Rect rectbg;                                               //~v@@@I~
	    int player,moredealctr,pos1st;                             //~v@@@I~
    //************************                                     //~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawRemainigStock player="+Pplayer+",pos="+Pcutpos);//~v@@@M~
        moredealctr=DEALCTR-(STOCKCTR_EACH-Pcutpos);    //min: 17(stocctr)-12(dice)=5,requres max :24-5=19//~v@@@R~
        player=Players.prevPlayer(Pplayer);                         //~v@@@I~
        while(moredealctr>0)                                       //~v@@@I~
        {                                                          //~v@@@I~
	        if (Dump.Y) Dump.println("Stock.drawRemainigStock prev player="+player+",need stock="+moredealctr);//~v@@@I~
		    rectbg=rectsBG[player];                                //~v@@@I~
			int residual=Math.max(STOCKCTR_EACH-moredealctr,0);    //~v@@@R~
			drawDealPlayer(rectbg,player,residual,true/*swreverse*/);//~v@@@R~
            moredealctr-=STOCKCTR_EACH;                            //~v@@@R~
            if (moredealctr>=0)                                    //~v@@@I~
		    	player=Players.prevPlayer(player);                  //~v@@@R~
        }                                                          //~v@@@I~
	    if (Dump.Y) Dump.println("Stock.drawRemainigStock moredealctr="+moredealctr+",player="+player);//~v@@@I~
        if (moredealctr==0)                                        //~v@@@I~
            pos1st=0;                                              //~v@@@I~
        else                                                       //~v@@@I~
        	pos1st=STOCKCTR_EACH+moredealctr;                      //~v@@@R~
        setNextStockPos(Pplayer,Pcutpos,player,pos1st);            //~v@@@R~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
	private void setNextStockPos(int Pcutplayer,int Pcutpos,int Pplayer,int Ppos)//~v@@@R~
    {                                                              //~v@@@I~
    	stockPlayer=Pplayer;                                       //~v@@@I~
    	nextStock=Ppos*STOCK_LAYER;                                //~v@@@I~
        if (Dump.Y) Dump.println("Status.resetNextStockPos player="+Pplayer+",pos="+Ppos);//~v@@@I~
        stockPlayerKan=Pcutplayer;                                 //~v@@@R~
        nextStockKan=Pcutpos;                                      //~v@@@R~
        if (Dump.Y) Dump.println("Status.resetNextStockPos cutplayer="+Pcutplayer+",cutpos="+Pcutpos);//~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************       //~v@@@I~
    //*return 0-->33                                               //~v@@@I~
    //******************************************************       //~v@@@I~
	private Point getNextStockPos()                                //~v@@@I~
    {                                                              //~v@@@I~
        if (nextStock==STOCKCTR_EACH*STOCK_LAYER)                  //~v@@@I~
        {                                                          //~v@@@I~
        	stockPlayer=Players.prevPlayer(stockPlayer);           //~v@@@I~
    		nextStock=0;                                           //~v@@@I~
        }                                                          //~v@@@I~
    	Point p=new Point(stockPlayer,nextStock);                  //~v@@@I~
        if (Dump.Y) Dump.println("Status.getNextStockPos player="+stockPlayer+",pos="+nextStock);//~v@@@I~
        nextStock++;                                               //~v@@@I~
        return p;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //******************************************************       //~v@11I~
    //*return 0-->33                                               //~v@11I~
    //******************************************************       //~v@11I~
	private Point getNextStockMarkPos()                            //~v@11I~
    {                                                              //~v@11I~
    	int player=stockPlayer,pos=nextStock;                      //~v@11I~
        if (nextStock==STOCKCTR_EACH*STOCK_LAYER)                  //~v@11I~
        {                                                          //~v@11I~
        	player=Players.prevPlayer(stockPlayer);                //~v@11I~
    		pos=0;                                                 //~v@11I~
        }                                                          //~v@11I~
    	Point p=new Point(player,pos);                             //~v@11I~
        if (Dump.Y) Dump.println("Status.getNextStockMarkPos player="+player+",pos="+pos);//~v@11I~
        return p;                                                  //~v@11I~
    }                                                              //~v@11I~
    //*********************************************************    //~v@@@I~
    public void takeOne()                                          //~v@@@R~
    {                                                              //~v@@@I~
	    int player,pos;                                            //~v@@@R~
    //************************                                     //~v@@@I~
    	Point p=getNextStockPos();                                 //~v@@@R~
        player=p.x;                                                //~v@@@I~
        pos=p.y;                                                   //~v@@@I~
        takeOne(player,pos,false/*not kan*/);                      //~v@@@R~
		drawNextOne(false);                                        //~v@11R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*take from wanpai                                            //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void takeOneKan()                                       //~v@@@I~
    {                                                              //~v@@@I~
        int ctrKan=AG.aTiles.ctrKan-1;	//already 1 upped*/        //~v@@@R~
    	int player=stockPlayerKan;                                 //~v@@@I~
    	int pos=nextStockKan;	//cutpos                           //~v@@@R~
        pos=(pos-1-ctrKan/STOCK_LAYER)*STOCK_LAYER/*back 1 layer unti-clockwise*/+ctrKan%STOCK_LAYER;//~v@@@R~
        if (Dump.Y) Dump.println("Stock.takeOneKan player="+player+",pos="+pos+",kanctr="+ctrKan);//~v@@@R~
        takeOne(player,pos,true/*kan*/);                           //~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*after Action:Kan                                            //~v@@@I~
    //*open kan-dora depending rule                                //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void takeKan(int Pplayer,int Pkantype)                  //~v@@@I~
    {                                                              //~v@@@I~
	    drawDora(Pplayer,Pkantype);                                //~v@@@I~
        if (Dump.Y) Dump.println("Stock.takeKan player="+Pplayer+",kanType="+Pkantype);//~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void discard(int Pplayer)                               //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Stock.discard player="+Pplayer); //~v@@@I~
	    drawDora(Pplayer,0/*discard*/);                            //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~9218I~
    public void drawPendingOpenDora(int Pplayer,int PactionID)     //~9218R~
    {                                                              //~9218I~
        if (Dump.Y) Dump.println("Stock.drawPendingOpenDora actionID="+PactionID);//~9218I~
	    drawDora(Pplayer,-PactionID);                              //~9218I~
    }                                                              //~9218I~
//    //*********************************************************    //~v@11I~//~9218R~
//    public boolean completeResetDora()                             //~v@11R~//~9218R~
//    {                                                              //~v@11I~//~9218R~
//        if (Dump.Y) Dump.println("Stock.completeResetDora ctrKan="+AG.aTiles.ctrKan+",ctrKanDrawn="+ctrKanDrawn);//~v@11I~//~9218R~
//        if (ctrKanDrawn==0)                                        //~v@11I~//~9218R~
//            return false;                                          //~v@11R~//~9218R~
//        Bitmap bm=bmsStock[lastPosDora];                           //~v@11I~//~9218R~
//        Graphics.drawBitmap(lastRectDora,bm);                      //~v@11R~//~9218R~
//        ctrKanDrawn--;                                             //~v@11I~//~9218R~
//        return true;                                               //~v@11I~//~9218R~
//    }                                                              //~v@11I~//~9218R~
    //*********************************************************    //~v@@@I~
    public void drawDora(int Pplayer,int Pkantype)                 //~v@@@I~
    {                                                              //~v@@@I~
        int ctrKan=AG.aTiles.ctrKan;	//not yet ctr upped,ctr up when takenKan//~v@@@I~
        if (Dump.Y) Dump.println("Stock.drawDora player="+Pplayer+",kantype="+Pkantype+",ctrkan="+ctrKan+",ctrKanDrawn="+ctrKanDrawn);//~v@@@I~//~9217R~
        if (!AG.aTiles.chkOpenKanDora(Pkantype))                   //~v@@@I~
        {                                                          //~v@@@I~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
//      for (int ii=ctrKanDrawn+1;ii<=ctrKan;ii++)                 //~v@@@I~//~9217R~
        for (int ii=ctrKanDrawn;ii<=ctrKan;ii++)                   //~9217R~
        {                                                          //~v@@@I~
            Point p=pointDora;                                     //~v@@@R~
            Point pk=getStockPosDoraKan(p.x,p.y,ii);               //~v@@@R~
            drawDora(pk.x,pk.y,ii);                                //~v@@@R~
	        lastPosDora=p.x;                                       //~v@11I~
        }                                                          //~v@@@I~
        ctrKanDrawn=ctrKan;                                        //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~9214I~
    //*show hidden Dora(uradora)                                   //~9214I~
    //*********************************************************    //~9214I~
    public void drawDoraComplete()                                 //~9214I~
    {                                                              //~9214I~
        if (Dump.Y) Dump.println("Stock.drawDoraComplete ctrKanDraw="+ctrKanDrawn);//~9214I~
        if (Dump.Y) Dump.println("Stock.drawDoraComplete ctrDoraComplete="+ctrDoraComplete);//~9815I~
        ctrDoraComplete=0;                                         //~9815I~
        for (int ii=0;ii<=ctrKanDrawn;ii++)                        //~9214I~//~9217R~
        {                                                          //~9214I~
            if (ii==0 && !RuleSetting.isShowHiddenDora())          //~9214I~
            	continue;                                          //~9214I~
            if (ii!=0 && !RuleSetting.isShowHiddenKanDora())       //~9214I~
            	continue;                                          //~9214I~
            int player=drawnPlayerDora[ii];                        //~9214I~
            Rect r=drawnRectDora[ii];                              //~9214I~
	    	TileData td=shuffled[DORA_TDPOS-ii*STOCK_LAYER-1];     //~9214R~
		    drawDoraComplete(player,r,td);                         //~9214I~
        }                                                          //~9214I~
    }                                                              //~9214I~
    //*********************************************************    //~9214I~
    private void drawDoraComplete(int Pplayer,Rect Prect,TileData Ptd)//~9214I~
    {                                                              //~9214I~
        if (Dump.Y) Dump.println("Stock.drawDoraComplete player="+Pplayer+",rect="+Prect.toString()+",td:"+Ptd.toString());//~9214I~
        if (Dump.Y) Dump.println("Stock.drawDoraComplete ctrDoraComplete="+ctrDoraComplete);//~9815I~
    	switch(Pplayer)                                            //~9214I~
        {                                                          //~9214I~
        case PLAYER_YOU:                                           //~9214I~
	        drawYouDoraComplete(Prect,Ptd);                        //~9214I~
            break;                                                 //~9214I~
        case PLAYER_RIGHT:                                         //~9214I~
	        drawRightDoraComplete(Prect,Ptd);                      //~9214I~
            break;                                                 //~9214I~
        case PLAYER_FACING:                                        //~9214I~
	        drawFacingDoraComplete(Prect,Ptd);                     //~9214I~
            break;                                                 //~9214I~
        default:                                                   //~9214I~
	        drawLeftDoraComplete(Prect,Ptd);                       //~9214I~
        }                                                          //~9214I~
    }                                                              //~9214I~
    //*************************************************************//~9214I~
	private void drawYouDoraComplete(Rect Prect,TileData Ptd)      //~9214I~
    {                                                              //~9214I~
        if (Dump.Y) Dump.println("Stock.drawYouDoraComplete");     //~9214I~
        Bitmap bm=getBitmapDora(PLAYER_YOU,Ptd);                   //~9214I~
        int d=bm.getHeight();                                      //~9214I~
        d+=SHIFT_SIDE;                                             //~9214I~
        Rect r=new Rect(Prect.left, Prect.top-d, Prect.right, Prect.bottom-d);//~9214I~
        Graphics.drawBitmap(r,bm);                                 //~9214I~
        rectCompleteDora[ctrDoraComplete++]=r;                     //~9503I~
    }                                                              //~9214I~
    //*************************************************************//~9214I~
	private void drawRightDoraComplete(Rect Prect,TileData Ptd)      //~9214I~
    {                                                              //~9214I~
        if (Dump.Y) Dump.println("Stock.drawRightDoraComplete");   //~9214I~
        Bitmap bm=getBitmapDora(PLAYER_RIGHT,Ptd);                 //~9214I~
        int d=bm.getWidth();                                       //~9214I~
        d+=SHIFT_BACK;                                             //~9214I~
        Rect r=new Rect(Prect.left-d, Prect.top, Prect.right-d, Prect.bottom);//~9214I~
        Graphics.drawBitmap(r,bm);                                 //~9214I~
        rectCompleteDora[ctrDoraComplete++]=r;                     //~9503I~
    }                                                              //~9214I~
    //*************************************************************//~9214I~
	private void drawFacingDoraComplete(Rect Prect,TileData Ptd)     //~9214I~
    {                                                              //~9214I~
        if (Dump.Y) Dump.println("Stock.drawFaicingDoraComplete"); //~9214I~
        Bitmap bm=getBitmapDora(PLAYER_FACING,Ptd);                //~9214I~
        int d=bm.getHeight();                                      //~9214I~
        d+=SHIFT_BACK;                                             //~9214I~
        Rect r=new Rect(Prect.left, Prect.top+d, Prect.right, Prect.bottom+d);//~9214I~
        Graphics.drawBitmap(r,bm);                                 //~9214I~
        rectCompleteDora[ctrDoraComplete++]=r;                     //~9503I~
    }                                                              //~9214I~
    //*************************************************************//~9214I~
	private void drawLeftDoraComplete(Rect Prect,TileData Ptd)       //~9214I~
    {                                                              //~9214I~
        if (Dump.Y) Dump.println("Stock.drawLeftDoraComplete");    //~9214I~
        Bitmap bm=getBitmapDora(PLAYER_LEFT,Ptd);                  //~9214I~
        int d=bm.getWidth();                                       //~9214I~
        d+=SHIFT_BACK;                                             //~9214I~
        Rect r=new Rect(Prect.left+d, Prect.top, Prect.right+d, Prect.bottom);//~9214I~
        Graphics.drawBitmap(r,bm);                                 //~9214I~
        rectCompleteDora[ctrDoraComplete++]=r;                     //~9503I~
    }                                                              //~9214I~
    //*********************************************************    //~v@@@I~
    private Point getStockPosDoraKan(int Pplayer,int Pdorapos,int ctrKan)//~v@@@I~
    {                                                              //~v@@@I~
        int pos,player;
        if (Dump.Y) Dump.println("Stock.getStockPosDoraKan doraplayer="+Pplayer+",dorapos="+Pdorapos+",ctrKan="+ctrKan);//~v@@@R~
        pos=Pdorapos-ctrKan;                                       //~v@@@I~
        player=Pplayer;
        if (pos<=0)                                                //~v@@@I~
        {                                                          //~v@@@I~
        	player=Players.nextPlayer(Pplayer);                    //~v@@@I~
			pos=STOCKCTR_EACH+pos;                                 //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Stock.getStockPosDataKan player="+player+",pos="+pos);//~v@@@I~
        Point p=new Point(player,pos);                             //~v@@@I~
        return p;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@11I~
    private void drawNextOne(boolean Pswkan)                               //~v@11R~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("Stock.drawNextOne swkan="+Pswkan);//~v@11I~
		Point p=getNextStockMarkPos();                             //~v@11I~
	    drawNextOne(p.x,p.y,Pswkan);                               //~v@11I~
    }                                                              //~v@11I~
    //*********************************************************    //~v@11I~
    private void drawNextOne(int Pplayer,int Ppos,boolean Pswkan)  //~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("Stock.drawNextOne player="+Pplayer+",pos="+Ppos+",swkan="+Pswkan);//~v@11I~
        int pos=Ppos/STOCK_LAYER+1;	//pos from backend 0,1-->1     //~v@11I~
        int layer=Ppos%STOCK_LAYER;                                //~v@11I~
        Rect r;                                                    //~v@11I~
    	switch(Pplayer)                                            //~v@11I~
        {                                                          //~v@11I~
        case PLAYER_YOU:                                           //~v@11I~
	        r=drawStockNextYou(pos,layer,Pswkan);                  //~v@11I~
            break;                                                 //~v@11I~
        case PLAYER_RIGHT:                                         //~v@11I~
	        r=drawStockNextRight(pos,layer,Pswkan);                //~v@11I~
            break;                                                 //~v@11I~
        case PLAYER_FACING:                                        //~v@11I~
	        r=drawStockNextFacing(pos,layer,Pswkan);               //~v@11I~
            break;                                                 //~v@11I~
        default:                                                   //~v@11I~
	        r=drawStockNextLeft(pos,layer,Pswkan);                 //~v@11I~
        }                                                          //~v@11I~
        if (Pswkan)                                                 //~v@11I~
        {                                                          //~v@11I~
	        drawNextMark(r,AG.aPlayers.getCurrentPlayer()==PLAYER_YOU);//~v@11R~
        	rectNextStockKan=r;                                    //~v@11I~
        	playerNextStockKan=Pplayer;                            //~v@11I~
        }                                                          //~v@11I~
        else                                                       //~v@11I~
        {                                                          //~v@11I~
        	if (!AG.aTiles.chkLast())                              //~9225I~
            {	                                                   //~9225I~
	        	drawNextMark(r,false);                                 //~v@11I~//~9225R~
        		rectNextStock=r;                                       //~v@11R~//~9225R~
        		playerNextStock=Pplayer;                               //~v@11R~//~9225R~
            }                                                      //~9225I~
        }                                                          //~v@11I~
    }                                                              //~v@11I~
    //*************************************************************//~v@11I~
    private void drawNextMark(Rect Prect,boolean PswEnable)        //~v@11R~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("Stock.drawNextMark rect:"+Prect.toString()+",swEnable="+PswEnable);//~v@11R~//~9625R~
        int centerX=(Prect.left+Prect.right)/2;                    //~v@11R~
        int centerY=(Prect.top+Prect.bottom)/2;                    //~v@11R~
        int radius=Math.min((Prect.bottom-Prect.top),(Prect.right-Prect.left))*2/5;//~v@11R~
		Graphics.drawCircle(new Point(centerX,centerY),radius,COLOR_NEXTONE);//~v@11R~
        if (!PswEnable)                                            //~v@11I~
			Graphics.drawCircle(new Point(centerX,centerY),radius,COLOR_NEXTONE_SHADOW);//~v@11I~
		Graphics.drawCircle(new Point(centerX,centerY),radius,COLOR_NEXTONE_RING,WIDTH_NEXTONE_RING);//~v@11I~
    }                                                              //~v@11I~
    //*************************************************************//~v@11I~
    //*from DiceBox                                                //~v@11I~
    //*************************************************************//~v@11I~
    public void enableNextMark()                                   //~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("Stock.enableNextMark rectNextStock="+ Utils.toString(rectNextStock));          //~v@11I~//~0403R~
        if (rectNextStock==null)                                   //~v@11I~
        	return;                                                //~v@11I~
	    drawNextMark(rectNextStock,true);                           //~v@11I~
    }                                                              //~v@11I~
    //*************************************************************//~0403I~
    //*UAKan disable nextMark to take on stock                     //~0403I~
    //*************************************************************//~0403I~
    public void disableNextMark()                                  //~0403I~
    {                                                              //~0403I~
        if (Dump.Y) Dump.println("Stock.disbleNextMark rectNextStock="+Utils.toString(rectNextStock));//~0403I~
        if (rectNextStock==null)                                   //~0403I~
        	return;                                                //~0403I~
	    drawNextMark(rectNextStock,false);                         //~0403I~
    }                                                              //~0403I~
    //*************************************************************//~v@11I~
    //*lower is down shifted                                       //~v@11I~
    //*************************************************************//~v@11I~
	private Rect drawStockNextYou(int Ppos,int Player,boolean Pswkan)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("Stock.drawStockNextYou");        //~v@11R~
		Rect r=getPieceRectYou(Ppos,Player);                       //~v@11I~
//      Bitmap bm=bmsStock[PLAYER_YOU];                            //~v@11I~
//  	Graphics.drawRectBitmap(r,COLOR_BG_STOCK,(Player==0 ? bm: null),r.left,r.top+SHIFT_BACK);//~v@11I~
        if (Player==0)                                             //~v@11I~
	    	r.bottom-=SHIFT_BACK;                                  //~v@11I~
        else                                                       //~v@11I~
	    	r.top+=SHIFT_BACK;                                     //~v@11R~
        return r;                                                  //~v@11I~
    }                                                              //~v@11I~
    //*************************************************************//~v@11I~
	private Rect drawStockNextRight(int Ppos,int Player,boolean Pswkan)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("Stock.drawStockNextRight");      //~v@11I~
		Rect r=getPieceRectRight(Ppos,Player);                     //~v@11I~
//      Bitmap bm=bmsStock[PLAYER_RIGHT];                          //~v@11I~
//  	Graphics.drawRectBitmap(r,COLOR_BG_STOCK,(Player==0 ? bm : null),r.left,r.top+SHIFT_SIDE); //lower//~v@11I~
        if (Player==0)                                             //~v@11I~
        {                                                          //~v@11I~
	    	r.left+=SHIFT_BACK;                                    //~v@11R~
	    	r.bottom-=SHIFT_SIDE; //lower                          //~v@11I~
        }                                                          //~v@11I~
        else                                                       //~v@11I~
        {                                                          //~v@11I~
	    	r.right-=SHIFT_BACK; //lower                           //~v@11I~
	    	r.top+=SHIFT_SIDE; //lower                             //~v@11I~
        }                                                          //~v@11I~
        return r;                                                  //~v@11I~
    }                                                              //~v@11I~
    //*************************************************************//~v@11I~
	private Rect drawStockNextFacing(int Ppos,int Player,boolean Pswkan)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("Stock.drawStockNextFacing");     //~v@11I~
		Rect r=getPieceRectFacing(Ppos,Player);                    //~v@11I~
//      Bitmap bm=bmsStock[PLAYER_FACING];                         //~v@11I~
//  	Graphics.drawRectBitmap(r,COLOR_BG_STOCK,(Player==0 ? bm : null),r.left,r.top+SHIFT_BACK);//this lower//~v@11I~
        if (Player==0)                                             //~v@11I~
	    	r.bottom-=SHIFT_BACK;                                  //~v@11I~
        else                                                       //~v@11I~
	    	r.top+=SHIFT_BACK;                                     //~v@11I~
        return r;                                                  //~v@11I~
    }                                                              //~v@11I~
    //*************************************************************//~v@11I~
	private Rect drawStockNextLeft(int Ppos,int Player,boolean Pswkan)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("Stock.drawStockNextLeft pos="+Ppos+",layer="+Player+",swkan="+Pswkan);//~v@11I~
		Rect r=getPieceRectLeft(Ppos,Player);                      //~v@11I~
        if (Player==0)                                             //~v@11R~
        {    // +v@11I~
	    	r.right-=SHIFT_BACK;                                   //~v@11I~
	    	r.bottom-=SHIFT_SIDE; //lower                          //~v@11I~
        }
        else                                                       //~v@11I~
        {                                                          //~v@11I~
            r.left += SHIFT_BACK;                                    //~v@11I~
            r.top += SHIFT_SIDE;                                   //~v@11R~
        }
        return r;                                                  //~v@11I~
    }                                                              //~v@11I~
    //*************************************************************//~v@11I~
	public int isTouch(int Pxx,int Pyy)                           //~v@11I~
    {                                                              //~v@11I~
    	int pos=ISTOUCH_NONE;                                      //~v@11I~
    //********************                                         //~v@11I~
        if (Dump.Y) Dump.println("Stock.isTouch Pxx="+Pxx+",Pyy="+Pyy);//~v@11M~
        int pos2=isTouchKan(Pxx,Pyy);                              //~v@11I~
        if (pos2!=ISTOUCH_NONE)                                    //~v@11I~
        {                                                          //~v@11I~
	        return pos2;                                           //~v@11I~
        }                                                          //~v@11I~
        if (rectNextStock==null)                                   //~v@11I~
        	return pos;                                            //~v@11I~
        if (AG.aPlayers.getNextPlayer()!=PLAYER_YOU)               //~v@11I~
        	return pos;                                            //~v@11I~
    	if (Pyy>=rectNextStock.top-ALLOWANCE_V && Pyy<=rectNextStock.bottom+ALLOWANCE_V)//~v@11I~
        {                                                          //~v@11I~
        	if (Pxx>=rectNextStock.left-ALLOWANCE_H && Pxx<=rectNextStock.right+ALLOWANCE_H)//~v@11I~
            {                                                      //~v@11I~
		        pos=playerNextStock|ISTOUCH_TAKEONE;               //~v@11I~
            }                                                      //~v@11I~
        }                                                          //~v@11I~
        if (Dump.Y) Dump.println("Stock.isTouch xx=="+Pxx+",yY="+Pyy+",pos="+pos+",rect="+rectNextStock.toString());//~v@11R~
        return pos;                                                //~v@11I~
    }                                                              //~v@11I~
    //*************************************************************//~v@11I~
	public int isTouchKan(int Pxx,int Pyy)                         //~v@11I~
    {                                                              //~v@11I~
    	int pos=ISTOUCH_NONE;                                      //~v@11I~
    //********************                                         //~v@11I~
        if (Dump.Y) Dump.println("Stock.isTouchKan Pxx="+Pxx+",Pyy="+Pyy);//~v@11I~
        if (rectNextStockKan==null)                                //~v@11I~
        	return pos;                                            //~v@11I~
        if (AG.aPlayers.getCurrentPlayer()!=PLAYER_YOU)            //~v@11I~
        	return pos;                                            //~v@11I~
    	if (Pyy>=rectNextStockKan.top-ALLOWANCE_V && Pyy<=rectNextStockKan.bottom+ALLOWANCE_V)//~v@11I~
        {                                                          //~v@11I~
        	if (Pxx>=rectNextStockKan.left-ALLOWANCE_H && Pxx<=rectNextStockKan.right+ALLOWANCE_H)//~v@11I~
            {                                                      //~v@11I~
		        pos=playerNextStockKan|ISTOUCH_TAKEONE_KAN;        //~v@11I~
            }                                                      //~v@11I~
        }                                                          //~v@11I~
        if (Dump.Y) Dump.println("Stock.isTouch xx=="+Pxx+",yY="+Pyy+",pos="+pos+",rect="+rectNextStockKan.toString());//~v@11R~
        return pos;                                                //~v@11I~
    }                                                              //~v@11I~
    //*********************************************************    //~v@11I~
    //*mark wanpai at Kan                                          //~v@11I~
    //*********************************************************    //~v@11I~
    public void drawNextOneKan()                                   //~v@11I~
    {                                                              //~v@11I~
        int ctrKan=AG.aTiles.ctrKan-1;	//already 1 upped*/        //~v@11I~
    	int player=stockPlayerKan;                                 //~v@11I~
    	int pos=nextStockKan;	//cutpos                           //~v@11I~
        pos=(pos-1-ctrKan/STOCK_LAYER)*STOCK_LAYER/*back 1 layer unti-clockwise*/+ctrKan%STOCK_LAYER;//~v@11I~
        if (Dump.Y) Dump.println("Stock.drawNextOneKan player="+player+",pos="+pos+",kanctr="+ctrKan);//~v@11I~
//      takeOne(player,pos,true/*kan*/);                           //~v@11I~
    	drawNextOne(player,pos,true/*Pswkan*/);                    //~v@11I~
    }                                                              //~v@11I~
}//class Stock                                                 //~dataR~//~@@@@R~//~v@@@R~
