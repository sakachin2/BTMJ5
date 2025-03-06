//*CID://+vay7R~: update#= 400;                                    //~vay7R~
//**********************************************************************//~v101I~
//2025/01/31 vay7 when portrate, stock height is too small.        //~vay7I~
//2022/04/02 vamd Animation. at first show Dora                    //~vamdI~
//2021/09/24 vaed more adjust for small device(dip=width/dip2px<=320)//~vaedI~
//**********************************************************************//~vaedI~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.game.Complete;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;//~v@@@R~
import com.btmtest.R;
import static com.btmtest.StaticVars.*;                            //~v@@@R~

import static com.btmtest.game.TileData.*;
import static com.btmtest.game.Tiles.*;                       //~v@@@I~
import static com.btmtest.game.GConst.*;                           //~v@@@I~
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;


public class Pieces                                                //~v@@@R~
{                                                                  //~0914I~
    private static final String CN="Pieces:";                      //~vay7R~
    public static final double PIECE_RATE_RIVER=0.8;               //~v@@@R~
//    public static int    PIECE_SPACING=1;   //pieces between holding//~v@@@R~
//    public static int    PIECE_SPACING_TAKEN=5;  //piece now gotten//~v@@@R~
    public  static final int PIECE_STANDING      =0;               //~v@@@M~
    public  static final int PIECE_LYING         =1;               //~v@@@M~
    public  static final int PIECE_SHAPE_CTR     =2; //standing lying//~v@@@M~
    public  static final int PIECE_REDMARK       =0;               //~v@@@M~
    public  static final int PIECE_TYPECTR_ALL   =4; 	//+ji      //~v@@@M~
    public  static final int PIECE_NUMBER_DORA   =5;	//dora number//~v@@@I~
    public  static final int PIECE_NOTNUM_CTR    =7;	//E,S,W,N,White,Green,Red//~v@@@M~
    public  static final int PIECE_COINS_REACH   =1;	           //~v@@@I~
    public  static final int PIECE_COINS_DUP     =0;               //~v@@@I~
    public  static final double BIRD_RATEW=0.6;                    //~v@@@R~
    public  static final double BIRD_RATEH=0.6;                    //~v@@@R~
    public  static final int    BIRD_DIST=2;                       //~v@@@R~
                                                                   //~v@@@I~
    private static final int COMPLETE_STROKE_WIDTH=6;              //~v@@@I~
    private static final double STROKE_WIDTH_RATE=0.1;             //~v@@@M~
    private static final int    STROKE_WIDTH_MIN=4;                //~v@@@I~
    private static final int    STROKE_WIDTH_MIN_SMALLDIP=2;       //~vaedR~
                                                                   //~v@@@M~
//Small image                                                      //~v@@@R~
    //*standing                                                    //~v@@@I~
    private static final int[] resSmallManS={ //*standing          //~v@@@R~
                                   R.drawable.s_manrs,             //~v@@@R~
                                   R.drawable.s_man1s,             //~v@@@R~
                                   R.drawable.s_man2s,             //~v@@@R~
                                   R.drawable.s_man3s,             //~v@@@R~
                                   R.drawable.s_man4s,             //~v@@@R~
                                   R.drawable.s_man5s,             //~v@@@R~
                                   R.drawable.s_man6s,             //~v@@@R~
                                   R.drawable.s_man7s,             //~v@@@R~
                                   R.drawable.s_man8s,             //~v@@@R~
                                   R.drawable.s_man9s,             //~v@@@R~
                                };                                 //~v@@@I~
	private static final int[] resSmallPinS={                      //~v@@@R~
                                   R.drawable.s_pinrs,             //~v@@@R~
                                   R.drawable.s_pin1s,             //~v@@@R~
                                   R.drawable.s_pin2s,             //~v@@@R~
                                   R.drawable.s_pin3s,             //~v@@@R~
                                   R.drawable.s_pin4s,             //~v@@@R~
                                   R.drawable.s_pin5s,             //~v@@@R~
                                   R.drawable.s_pin6s,             //~v@@@R~
                                   R.drawable.s_pin7s,             //~v@@@R~
                                   R.drawable.s_pin8s,             //~v@@@R~
                                   R.drawable.s_pin9s,             //~v@@@R~
                                };                                 //~v@@@I~
    private static final int[] resSmallSouS={                      //~v@@@R~
                                   R.drawable.s_sours,             //~v@@@R~
                                   R.drawable.s_sou1s,             //~v@@@R~
                                   R.drawable.s_sou2s,             //~v@@@R~
                                   R.drawable.s_sou3s,             //~v@@@R~
                                   R.drawable.s_sou4s,             //~v@@@R~
                                   R.drawable.s_sou5s,             //~v@@@R~
                                   R.drawable.s_sou6s,             //~v@@@R~
                                   R.drawable.s_sou7s,             //~v@@@R~
                                   R.drawable.s_sou8s,             //~v@@@R~
                                   R.drawable.s_sou9s,             //~v@@@R~
                                };                                 //~v@@@I~
    private static final int[] resSmallJiS={                       //~v@@@R~
                                   R.drawable.s_ji4es,             //~v@@@R~
                                   R.drawable.s_ji4ss,             //~v@@@R~
                                   R.drawable.s_ji4ws,             //~v@@@R~
                                   R.drawable.s_ji4ns,             //~v@@@R~
                                   R.drawable.s_ji3ws,             //~v@@@R~
                                   R.drawable.s_ji3gs,             //~v@@@R~
                                   R.drawable.s_ji3rs,             //~v@@@R~
                                };                                 //~v@@@I~
    private static final int[] resSmallManL={ //Lying              //~v@@@R~
                                   R.drawable.s_manrl,             //~v@@@R~
                                   R.drawable.s_man1l,             //~v@@@R~
                                   R.drawable.s_man2l,             //~v@@@R~
                                   R.drawable.s_man3l,             //~v@@@R~
                                   R.drawable.s_man4l,             //~v@@@R~
                                   R.drawable.s_man5l,             //~v@@@R~
                                   R.drawable.s_man6l,             //~v@@@R~
                                   R.drawable.s_man7l,             //~v@@@R~
                                   R.drawable.s_man8l,             //~v@@@R~
                                   R.drawable.s_man9l,             //~v@@@R~
                                };                                 //~v@@@I~
    private static final int[] resSmallPinL={                      //~v@@@R~
                                   R.drawable.s_pinrl,             //~v@@@R~
                                   R.drawable.s_pin1l,             //~v@@@R~
                                   R.drawable.s_pin2l,             //~v@@@R~
                                   R.drawable.s_pin3l,             //~v@@@R~
                                   R.drawable.s_pin4l,             //~v@@@R~
                                   R.drawable.s_pin5l,             //~v@@@R~
                                   R.drawable.s_pin6l,             //~v@@@R~
                                   R.drawable.s_pin7l,             //~v@@@R~
                                   R.drawable.s_pin8l,             //~v@@@R~
                                   R.drawable.s_pin9l,             //~v@@@R~
                                };                                 //~v@@@I~
    private static final int[] resSmallSouL={                      //~v@@@R~
                                   R.drawable.s_sourl,             //~v@@@R~
                                   R.drawable.s_sou1l,             //~v@@@R~
                                   R.drawable.s_sou2l,             //~v@@@R~
                                   R.drawable.s_sou3l,             //~v@@@R~
                                   R.drawable.s_sou4l,             //~v@@@R~
                                   R.drawable.s_sou5l,             //~v@@@R~
                                   R.drawable.s_sou6l,             //~v@@@R~
                                   R.drawable.s_sou7l,             //~v@@@R~
                                   R.drawable.s_sou8l,             //~v@@@R~
                                   R.drawable.s_sou9l,             //~v@@@R~
                                };                                 //~v@@@I~
    private static final int[] resSmallJiL={                       //~v@@@R~
                                   R.drawable.s_ji4el,             //~v@@@R~
                                   R.drawable.s_ji4sl,             //~v@@@R~
                                   R.drawable.s_ji4wl,             //~v@@@R~
                                   R.drawable.s_ji4nl,             //~v@@@R~
                                   R.drawable.s_ji3wl,             //~v@@@R~
                                   R.drawable.s_ji3gl,             //~v@@@R~
                                   R.drawable.s_ji3rl,             //~v@@@R~
                                };                                 //~v@@@I~
//Large image                                                      //~v@@@I~
    //*standing                                                    //~v@@@I~
    private static final int[] resLargeManS={ //*standing          //~v@@@R~
                                   R.drawable.l_manrs,             //~v@@@I~
                                   R.drawable.l_man1s,             //~v@@@I~
                                   R.drawable.l_man2s,             //~v@@@I~
                                   R.drawable.l_man3s,             //~v@@@I~
                                   R.drawable.l_man4s,             //~v@@@I~
                                   R.drawable.l_man5s,             //~v@@@I~
                                   R.drawable.l_man6s,             //~v@@@I~
                                   R.drawable.l_man7s,             //~v@@@I~
                                   R.drawable.l_man8s,             //~v@@@I~
                                   R.drawable.l_man9s,             //~v@@@I~
                                };                                 //~v@@@I~
	private static final int[] resLargePinS={                      //~v@@@R~
                                   R.drawable.l_pinrs,             //~v@@@I~
                                   R.drawable.l_pin1s,             //~v@@@I~
                                   R.drawable.l_pin2s,             //~v@@@I~
                                   R.drawable.l_pin3s,             //~v@@@I~
                                   R.drawable.l_pin4s,             //~v@@@I~
                                   R.drawable.l_pin5s,             //~v@@@I~
                                   R.drawable.l_pin6s,             //~v@@@I~
                                   R.drawable.l_pin7s,             //~v@@@I~
                                   R.drawable.l_pin8s,             //~v@@@I~
                                   R.drawable.l_pin9s,             //~v@@@I~
                                };                                 //~v@@@I~
    private static final int[] resLargeSouS={                      //~v@@@R~
                                   R.drawable.l_sours,             //~v@@@I~
                                   R.drawable.l_sou1s,             //~v@@@I~
                                   R.drawable.l_sou2s,             //~v@@@I~
                                   R.drawable.l_sou3s,             //~v@@@I~
                                   R.drawable.l_sou4s,             //~v@@@I~
                                   R.drawable.l_sou5s,             //~v@@@I~
                                   R.drawable.l_sou6s,             //~v@@@I~
                                   R.drawable.l_sou7s,             //~v@@@I~
                                   R.drawable.l_sou8s,             //~v@@@I~
                                   R.drawable.l_sou9s,             //~v@@@I~
                                };                                 //~v@@@I~
    private static final int[] resLargeJiS={                       //~v@@@R~
                                   R.drawable.l_ji4es,             //~v@@@I~
                                   R.drawable.l_ji4ss,             //~v@@@I~
                                   R.drawable.l_ji4ws,             //~v@@@I~
                                   R.drawable.l_ji4ns,             //~v@@@I~
                                   R.drawable.l_ji3ws,             //~v@@@I~
                                   R.drawable.l_ji3gs,             //~v@@@I~
                                   R.drawable.l_ji3rs,             //~v@@@I~
                                };                                 //~v@@@I~
    private static final int[] resLargeManL={ //Lying              //~v@@@R~
                                   R.drawable.l_manrl,             //~v@@@I~
                                   R.drawable.l_man1l,             //~v@@@I~
                                   R.drawable.l_man2l,             //~v@@@I~
                                   R.drawable.l_man3l,             //~v@@@I~
                                   R.drawable.l_man4l,             //~v@@@I~
                                   R.drawable.l_man5l,             //~v@@@I~
                                   R.drawable.l_man6l,             //~v@@@I~
                                   R.drawable.l_man7l,             //~v@@@I~
                                   R.drawable.l_man8l,             //~v@@@I~
                                   R.drawable.l_man9l,             //~v@@@I~
                                };                                 //~v@@@I~
    private static final int[] resLargePinL={                      //~v@@@R~
                                   R.drawable.l_pinrl,             //~v@@@I~
                                   R.drawable.l_pin1l,             //~v@@@I~
                                   R.drawable.l_pin2l,             //~v@@@I~
                                   R.drawable.l_pin3l,             //~v@@@I~
                                   R.drawable.l_pin4l,             //~v@@@I~
                                   R.drawable.l_pin5l,             //~v@@@I~
                                   R.drawable.l_pin6l,             //~v@@@I~
                                   R.drawable.l_pin7l,             //~v@@@I~
                                   R.drawable.l_pin8l,             //~v@@@I~
                                   R.drawable.l_pin9l,             //~v@@@I~
                                };                                 //~v@@@I~
    private static final int[] resLargeSouL={                      //~v@@@R~
                                   R.drawable.l_sourl,             //~v@@@I~
                                   R.drawable.l_sou1l,             //~v@@@I~
                                   R.drawable.l_sou2l,             //~v@@@I~
                                   R.drawable.l_sou3l,             //~v@@@I~
                                   R.drawable.l_sou4l,             //~v@@@I~
                                   R.drawable.l_sou5l,             //~v@@@I~
                                   R.drawable.l_sou6l,             //~v@@@I~
                                   R.drawable.l_sou7l,             //~v@@@I~
                                   R.drawable.l_sou8l,             //~v@@@I~
                                   R.drawable.l_sou9l,             //~v@@@I~
                                };                                 //~v@@@I~
    private static final int[] resLargeJiL={                       //~v@@@R~
                                   R.drawable.l_ji4el,             //~v@@@I~
                                   R.drawable.l_ji4sl,             //~v@@@I~
                                   R.drawable.l_ji4wl,             //~v@@@I~
                                   R.drawable.l_ji4nl,             //~v@@@I~
                                   R.drawable.l_ji3wl,             //~v@@@I~
                                   R.drawable.l_ji3gl,             //~v@@@I~
                                   R.drawable.l_ji3rl,             //~v@@@I~
                                };                                 //~v@@@I~
                                                                   //~v@@@I~
    private static final int[] resStock={                          //~v@@@R~
                                   R.drawable.stock,	           //~v@@@R~
//                                 R.drawable.stock_earth //used kan taken(minkan)//~v@@@R~
                                   R.drawable.stock       //used kan taken(minkan)//~v@@@I~
                                };                                 //~v@@@I~
    private static final int[] resStarter={                        //~v@@@R~
                                   R.drawable.eswn_e,              //~v@@@I~
                                   R.drawable.eswn_s,              //~v@@@I~
                                   R.drawable.eswn_w,              //~v@@@I~
                                   R.drawable.eswn_n,              //~v@@@I~
                                };                                 //~v@@@I~
    private static final int[] resCoin={                           //~v@@@R~
                                   R.drawable.coin_100,            //~v@@@I~
                                   R.drawable.coin_1000,           //~v@@@I~
                                   R.drawable.coin_5000,           //~v@@@I~
                                   R.drawable.coin_10000,          //~v@@@I~
                                };                                 //~v@@@I~
    public static final int PIECE_STOCK_STOCK   =0;                //~v@@@R~
    public static final int PIECE_STOCK_EARTH   =1;  //for minkan  //~v@@@R~
    public static final int PIECE_STOCK_CTR     =2;                //~v@@@I~
                                                                   //~v@@@I~
    private static final int[] resDice={                           //~v@@@R~
                                   R.drawable.dice_plane1,         //~v@@@R~
                                   R.drawable.dice_plane2,         //~v@@@R~
                                   R.drawable.dice_plane3,         //~v@@@R~
                                   R.drawable.dice_plane4,         //~v@@@R~
                                   R.drawable.dice_plane5,         //~v@@@R~
                                   R.drawable.dice_plane6,         //~v@@@R~
                                };                                 //~v@@@I~
    private static final int resBird= R.drawable.bird;             //~v@@@R~
	//**********************************                           //~v@@@I~
                                                                   //~v@@@I~
    private static final int[][] resSmallAllS={resSmallManS,resSmallPinS,resSmallSouS,resSmallJiS};//~v@@@R~
    private static final int[][] resSmallAllL={resSmallManL,resSmallPinL,resSmallSouL,resSmallJiL};//~v@@@R~
    private static final int[][] resLargeAllS={resLargeManS,resLargePinS,resLargeSouS,resLargeJiS};//~v@@@R~
    private static final int[][] resLargeAllL={resLargeManL,resLargePinL,resLargeSouL,resLargeJiL};//~v@@@R~
    private static final int[][] typeAll={		//piece is not synmetric//~v@@@R~
                                   // r 1 2 3 4 5 6 7 8 9          //~v@@@I~
                                     {1,1,1,1,1,1,1,1,1,1 }, //man //~v@@@I~
                                     {0,0,0,0,0,0,0,1,0,0 }, //pin //~v@@@I~
                                     {0,1,0,1,0,0,0,1,0,0 }, //sou //~v@@@I~
                                     {1,1,1,1,0,1,1},        //ji  //~v@@@R~
                                   };                              //~v@@@I~
                                                                   //~v@@@I~
    private int WW,HH;
    private boolean swLarge=true;                                  //~v@@@R~
    private double earthPieceScale,riverPieceScale;                //~v@@@R~
    public int pieceWW,pieceHH;                                    //~v@@@R~
    private MJTable table;                                         //~v@@@M~
                                                                   //~v@@@I~
    private int[][] resAllS,resAllL;                               //~v@@@I~
    private int[][][] resAll;  //stand/lying, man/pin/sou, r/1/2/...//~v@@@I~
//*************************                                        //~v@@@I~
	public Pieces(MJTable Ptable,int Pww,int Phh)                  //~v@@@I~
    {                                                              //~v@@@I~
    	AG.aPieces=this;                                            //~v@@@I~
    	table=Ptable;                                              //~v@@@I~
//      WW=AG.scrWidth;                                            //~v@@@R~
//      HH=AG.scrHeight;                                           //~v@@@R~
        WW=Pww;                                                    //~v@@@I~
        HH=Phh;                                                    //~v@@@I~
        if (Dump.Y) Dump.println("Pieces Constructor");         //~1506R~//~@@@@R~//~v@@@R~
//        try                                                      //~v@@@R~
//        {                                                        //~v@@@R~
//            init();                                              //~v@@@R~
//        }                                                          //~1109I~//~1120M~//~1122M~//~v@@@R~
//        catch(Exception e)                                         //~1109I~//~1120M~//~1122M~//~v@@@R~
//        {                                                          //~1109I~//~1120M~//~1122M~//~v@@@R~
//            Dump.println(e,"Pieces Constructor");                //~v@@@R~
//        }                                                          //~1109I~//~1120M~//~1122M~//~v@@@R~
    }                                                              //~0914I~
	//*************************                                        //~1109I~//~1111I~//~1122M~//~v@@@R~
	public void init()                                             //~v@@@R~
    {                                                              //~1120I~//~1122M~
        try                                                        //~v@@@I~
        {                                                          //~v@@@I~
            UView.getScreenSize();                                       //~1122I~//~v@@@R~
//          WW=AG.scrWidth;                                        //~v@@@R~
//          HH=AG.scrHeight;                                       //~v@@@R~
    //      UView.fixOrientation(true);                                  //~@@@@I~//~v@@@R~
            loadImage();                                           //~v@@@R~
        }                                                          //~v@@@I~
        catch(Exception e)                                         //~v@@@I~
        {                                                          //~v@@@I~
    		Dump.println(e,"Pieces.init");                         //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private void loadImage()                                       //~v@@@I~
    {                                                              //~v@@@I~
        if (swLarge)                                               //~v@@@I~
        {                                                          //~v@@@I~
            resAllS=resLargeAllS;                                  //~v@@@R~
            resAllL=resLargeAllL;                                  //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
            resAllS=resSmallAllS;                                  //~v@@@R~
            resAllL=resSmallAllL;                                  //~v@@@R~
        }                                                          //~v@@@I~
        resAll=new int[PIECE_SHAPE_CTR][][];                        //~v@@@I~
        resAll[PIECE_STANDING]=resAllS;                            //~v@@@I~
        resAll[PIECE_LYING]=resAllL;                               //~v@@@I~
		getEarthScale();                                           //~v@@@R~
    	loadPieces(earthPieceScale);                               //~v@@@R~
        table.setEarthPieceSize(earthPieceW,earthPieceH);          //~v@@@I~
		getRiverScale();                                           //~v@@@I~
    	loadPiecesRiver(riverPieceScale);                          //~v@@@R~
        table.setRiverPieceSize(riverPieceW,riverPieceH);          //~v@@@I~
		getStockScale();                                           //~v@@@I~
    	loadPiecesStock();                                         //~v@@@I~
        table.setStockPieceSize(stockPieceW,stockPieceH,stockEarthPieceW,stockEarthPieceH);//~v@@@I~
    }                                                              //~1120I~//~1122M~
	//***************************************************************//~v@@@I~
	//*piece in hand(<=14)                                         //~v@@@I~
	//***************************************************************//~v@@@I~
	private void getEarthScale()                                   //~v@@@R~
    {                                                              //~v@@@I~
        int res=resAllS[0][0];                                     //~v@@@R~
    	Bitmap bm=loadPieceImage(res);	//standing                 //~v@@@R~
        int ww=bm.getWidth();                                      //~v@@@R~
        int hh=bm.getHeight();                                     //~v@@@R~
        recycle(bm);                                               //~v@@@M~
        pieceWW=ww;                                                //~v@@@I~
        pieceHH=hh;                                                //~v@@@I~
        int pww=table.getEarthPieceWidth(ww,hh); //HandPieceWidth  //~v@@@R~
        if (Dump.Y) Dump.println("Pieces.getEarthScale scr w="+WW+",h="+HH+",bmp stand w="+ww+",h="+hh);//~v@@@R~//~vamdR~
        double rate=(double)pww/ww;                                //~v@@@R~
        if (Dump.Y) Dump.println("Pieces.getEarthScale rate="+rate);//~v@@@R~
        earthPieceScale=rate;                                      //~v@@@R~
        earthPieceW=pww;                                           //~v@@@I~
        earthPieceH=(int)(hh*earthPieceScale);                            //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.getEarthScale earthPiece scaled w="+earthPieceW+",h="+earthPieceH);//~v@@@I~//~vamdR~
		loadPiecesStarter();                                       //~v@@@I~
		loadPiecesCoin();                                          //~v@@@I~
		loadPiecesDice();                                          //~v@@@I~
		loadPiecesBird();                                          //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private void getRiverScale()                                   //~v@@@I~
    {                                                              //~v@@@I~
    	int ww=(int)(pieceWW*earthPieceScale);                            //~v@@@I~
    	int hh=(int)(pieceHH*earthPieceScale);                            //~v@@@I~
    	Point p=table.getRiverPieceWidth(ww,hh);    //on earthPiece scaled//~v@@@R~
        int pww=p.x;                                               //~v@@@I~
    	double rate=(double)pww/ww;                                //~v@@@R~
        if (Dump.Y) Dump.println("Pieces.loadPieceImage.getRiverScale rate="+rate+",ww="+ww+",hh="+hh);//~v@@@R~//~vamdR~
    	riverPieceScale=rate;                                      //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private void getStockScale()                                   //~v@@@I~
    {                                                              //~v@@@I~
        int ww=table.getStockPieceWidth();                         //~v@@@R~
        int hh=(int)((double)ww*earthPieceH/earthPieceW);                 //~v@@@R~
        StockPieceW=ww;                                            //~v@@@R~
        StockPieceH=hh;                                            //~v@@@R~
        if (Dump.Y) Dump.println("Pieces.loadPieceImage.getStockScale StockPieceW="+ww+",SstockPieceH="+hh);//~v@@@R~//~vamdR~//+vay7R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    public static void recycleAll()                                //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.recycleAll");             //~v@@@I~
        recyclePieces();                                           //~v@@@I~
        recyclePiecesRiver();                                      //~v@@@I~
        recyclePiecesStock();                                      //~v@@@I~
        recyclePieces4();                                          //~v@@@R~
		recyclePiecesDice();                                       //~v@@@I~
    	recyclePieces2();                                          //~v@@@R~
		recyclePiecesBird();                                       //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private void loadPieces(double Pscale)                         //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.loadPieces oldScale="+oldScale+",new="+Pscale);//~v@@@I~
        if (oldScale!=0.0)                                         //~v@@@R~
        {                                                          //~v@@@I~
			if (oldScale==Pscale)                                  //~v@@@I~
            	return;                                            //~v@@@I~
            recyclePieces();                                       //~v@@@I~
        }                                                          //~v@@@I~
        oldScale=Pscale;                                           //~v@@@I~
    	int[] pcsS,pcsL;                                           //~v@@@R~
        int sz1=PIECE_TYPECTR_ALL;                                     //~v@@@R~
//      bitmapAllPieces=new Bitmap[PIECE_SHAPE_CTR][][];           //~v@@@R~
        AG.bitmapAllPieces=new Bitmap[PIECE_SHAPE_CTR][][];        //~v@@@I~
                                                                   //~v@@@I~
        Bitmap[][] bmAllS=new Bitmap[sz1][] ;                      //~v@@@R~
        Bitmap[][] bmAllL=new Bitmap[sz1][] ;                      //~v@@@R~
//      bitmapAllPieces[0]=bmAllS;                                 //~v@@@R~
//      bitmapAllPieces[1]=bmAllL;                                 //~v@@@R~
        AG.bitmapAllPieces[0]=bmAllS;                              //~v@@@I~
        AG.bitmapAllPieces[1]=bmAllL;                              //~v@@@I~
        for (int ii=0;ii<sz1;ii++)                                 //~v@@@I~
        {                                                          //~v@@@I~
                                                                   //~v@@@I~
        	pcsS=resAllS[ii];                                      //~v@@@R~
        	pcsL=resAllL[ii];                                      //~v@@@R~
	        int sz2=pcsS.length;                                    //~v@@@I~
            Bitmap[] bmS=new Bitmap[sz2] ;                         //~v@@@R~
            Bitmap[] bmL=new Bitmap[sz2] ;                         //~v@@@R~
            bmAllS[ii]=bmS;                                          //~v@@@I~
            bmAllL[ii]=bmL;                                        //~v@@@I~
            for (int jj=0;jj<sz2;jj++)                             //~v@@@I~
            {                                                      //~v@@@I~
            	int resSmall=pcsS[jj];                             //~v@@@R~
            	int resLarge=pcsL[jj];                             //~v@@@R~
			    bmS[jj]=loadPieceImage(resSmall,Pscale);           //~v@@@R~
			    bmL[jj]=loadPieceImage(resLarge,Pscale);           //~v@@@R~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        earthPieceW=bmAllS[0][0].getWidth(); //get real width after scaled//~v@@@R~
        earthPieceH=bmAllS[0][0].getHeight(); //get real width after scaled//~v@@@R~
        if (Dump.Y) Dump.println("Pieces.loadPieces earth(Hand) bitmap W="+earthPieceW+",H="+earthPieceH);//~v@@@R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private static void recyclePieces()                            //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.recyclePieces");          //~v@@@I~
//      if (bitmapAllPieces==null)                                 //~v@@@R~
        if (AG.bitmapAllPieces==null)                              //~v@@@I~
        	return;                                                //~v@@@I~
        for (int ii=0;ii<PIECE_SHAPE_CTR;ii++)                     //~v@@@I~
        {                                                          //~v@@@I~
        	for (int jj=0;jj<PIECE_TYPECTR_ALL;jj++)               //~v@@@I~
            {                                                      //~v@@@I~
//          	int len=bitmapAllPieces[ii][jj].length;            //~v@@@R~
            	int len=AG.bitmapAllPieces[ii][jj].length;         //~v@@@I~
	        	for (int kk=0;kk<len;kk++)                         //~v@@@R~
                {                                                  //~v@@@I~
//              	recycle(bitmapAllPieces[ii][jj][kk]);          //~v@@@R~
                	recycle(AG.bitmapAllPieces[ii][jj][kk]);       //~v@@@I~
//              	bitmapAllPieces[ii][jj][kk]=null;              //~v@@@R~
                	AG.bitmapAllPieces[ii][jj][kk]=null;           //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
//      bitmapAllPieces=null;                                      //~v@@@R~
        AG.bitmapAllPieces=null;                                   //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private void loadPiecesRiver(double Pscale)                    //~v@@@R~
    {                                                              //~v@@@I~
        Matrix matrix;                                             //~v@@@R~
    	int[] pcsS,pcsL;                                           //~v@@@I~
        int ww=0,hh=0;
        Bitmap bmscaled,bm;//~v@@@I~
    //*******************                                          //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.loadPiecesRiver oldScale="+oldScaleRiver+",new="+Pscale);//~v@@@I~
        if (oldScaleRiver!=0.0)                                    //~v@@@R~
        {                                                          //~v@@@I~
	        if (oldScaleRiver==Pscale)                             //~v@@@I~
            	return;                                            //~v@@@I~
            recyclePiecesRiver();                                  //~v@@@I~
        }                                                          //~v@@@I~
        oldScaleRiver=Pscale;                                      //~v@@@I~
        Bitmap[][][] bmouts=new Bitmap[PLAYERS][PIECE_TYPECTR_ALL][];//4*4(man,pin,sou,ji)*number//~v@@@R~
//      bitmapAllPiecesRiver=bmouts;                               //~v@@@R~
        AG.bitmapAllPiecesRiver=bmouts;                            //~v@@@I~
//      Bitmap[][] bmin=bitmapAllPieces[PIECE_STANDING];		//standing//~v@@@R~
        Bitmap[][] bmin=AG.bitmapAllPieces[PIECE_STANDING];		//standing//~v@@@I~
        int[][] types=typeAll;                                       //~v@@@I~
//get scaled ww/hh                                                 //~v@@@I~
        bm=bmin[0][0];                                      //~v@@@I~
	    bm=scaleImage(bm,Pscale);                                  //~v@@@I~
        ww=bm.getWidth();                                      //~v@@@I~
        hh=bm.getHeight();                                     //~v@@@I~
        recycle(bm);                                               //~v@@@I~
        riverPieceW=ww; //get real width after scaled              //~v@@@I~
        riverPieceH=hh; //get real height after scaled             //~v@@@I~
//*                                                                //~v@@@I~
   		Bitmap bmRotationBase=null; //filled at player=0           //~v@@@I~
		matrix=new Matrix();

        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@R~
        {                                                          //~v@@@I~
            for (int jj=0;jj<bmin.length;jj++)      //man,pin,sou,ji//~v@@@R~
            {                                                      //~v@@@I~
		        Bitmap[] bms=bmin[jj];                             //~v@@@I~
		        Bitmap[] bmout=new Bitmap[bms.length];             //~v@@@I~
                bmouts[ii][jj]=bmout;                              //~v@@@I~
            	for (int kk=0;kk<bms.length;kk++)   //number       //~v@@@R~
                {                                                  //~v@@@I~
        			bm=bms[kk];                             //~v@@@I~
//                  int ww=bm.getWidth();                          //~v@@@R~
//                  int hh=bm.getHeight();                         //~v@@@R~
//          		Bitmap bmscaled;                               //~v@@@R~
                    int type=types[jj][kk];                        //~v@@@I~
                    switch(ii)                                     //~v@@@I~
                    {                                              //~v@@@I~
                    case 0:                 //up                   //~v@@@I~
    	                bmscaled=scaleImage(bm,Pscale);            //~v@@@R~
                        bmRotationBase=bmscaled;                    //~v@@@I~
                    	break;                                     //~v@@@I~
                    case 1:   //right person,orientation to left   //~v@@@R~
//                        matrix=new Matrix();                     //~v@@@R~
//                        matrix.setRotate(270,ww/2,hh/ww);        //~v@@@R~
//                        bm=Bitmap.createBitmap(bm,0,0,ww,hh,matrix,true);//~v@@@R~
//                        bmscaled=scaleImage(bm,Pscale);          //~v@@@R~
                        bmRotationBase=bmouts[0][jj][kk];          //~v@@@I~
                        matrix.setRotate(270,ww/2,hh/ww);          //~v@@@I~
//                      bmscaled=Bitmap.createBitmap(bmRotationBase,0,0,ww,hh,matrix,true);//~v@@@R~
                        bmscaled=Graphics.createBitmap(bmRotationBase,0,0,ww,hh,matrix,true);//~v@@@I~
                    	break;                                     //~v@@@I~
                    case 2:  //down                                //~v@@@I~
                        if (type==0)    //no dest       //same as "up"//~v@@@I~
                            bmscaled=bmouts[0][jj][kk];            //~v@@@I~
                        else                                       //~v@@@I~
                        {                                          //~v@@@I~
//                            matrix=new Matrix();                 //~v@@@R~
//                            matrix.setRotate(180,ww/2,hh/ww);    //~v@@@R~
//                            bm=Bitmap.createBitmap(bm,0,0,ww,hh,matrix,true);//~v@@@R~
//                            bmscaled=scaleImage(bm,Pscale);      //~v@@@R~
                        	bmRotationBase=bmouts[0][jj][kk];      //~v@@@I~
                            matrix.setRotate(180,ww/2,hh/ww);      //~v@@@I~
//                          bmscaled=Bitmap.createBitmap(bmRotationBase,0,0,ww,hh,matrix,true);//~v@@@R~
                            bmscaled=Graphics.createBitmap(bmRotationBase,0,0,ww,hh,matrix,true);//~v@@@I~
                        }                                          //~v@@@I~
                    	break;                                     //~v@@@I~
                    default: // case 3:  //left person,orientation to right//~v@@@R~
                        if (type==0)    //no dest                  //~v@@@I~
                            bmscaled=bmouts[1][jj][kk];	//same as "to left"//~v@@@I~
                        else                                       //~v@@@I~
                        {                                          //~v@@@I~
//                            matrix=new Matrix();                 //~v@@@R~
//                            matrix.setRotate(90,ww/2,hh/ww);     //~v@@@R~
//                            bm=Bitmap.createBitmap(bm,0,0,ww,hh,matrix,true);//~v@@@R~
//                            bmscaled=scaleImage(bm,Pscale);      //~v@@@R~
                        	bmRotationBase=bmouts[0][jj][kk];      //~v@@@I~
                            matrix.setRotate(90,ww/2,hh/ww);       //~v@@@I~
//                          bmscaled=Bitmap.createBitmap(bmRotationBase,0,0,ww,hh,matrix,true);//~v@@@R~
                            bmscaled=Graphics.createBitmap(bmRotationBase,0,0,ww,hh,matrix,true);//~v@@@I~
                        }                                          //~v@@@I~
                    }                                              //~v@@@I~
                    bmout[kk]=bmscaled;                            //~v@@@R~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.loadPiecesRiver bitmap W="+riverPieceW+",H="+riverPieceH);//~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private static void recyclePiecesRiver()                       //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.recyclePiecesRiver");     //~v@@@I~
//  	if (bitmapAllPiecesRiver==null)                            //~v@@@R~
    	if (AG.bitmapAllPiecesRiver==null)                         //~v@@@I~
        	return;                                                //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
            for (int jj=0;jj<PIECE_TYPECTR_ALL;jj++)      //man,pin,sou,ji//~v@@@I~
            {                                                      //~v@@@I~
//  	        int len=bitmapAllPiecesRiver[ii][jj].length;       //~v@@@R~
    	        int len=AG.bitmapAllPiecesRiver[ii][jj].length;    //~v@@@I~
            	for (int kk=0;kk<len;kk++)   //number              //~v@@@I~
                {                                                  //~v@@@I~
//      			recycle(bitmapAllPiecesRiver[ii][jj][kk]);     //~v@@@R~
        			recycle(AG.bitmapAllPiecesRiver[ii][jj][kk]);//~v@@@I~
//      			bitmapAllPiecesRiver[ii][jj][kk]=null;         //~v@@@R~
        			AG.bitmapAllPiecesRiver[ii][jj][kk]=null;      //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
//		bitmapAllPiecesRiver=null;                                 //~v@@@R~
  		AG.bitmapAllPiecesRiver=null;                              //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private void loadPiecesStock()                                 //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println(CN+"loadPiecesStock StockPieceW="+StockPieceW+",StockPieceH="+StockPieceH);//+vay7R~
        recyclePiecesStock();                                      //~v@@@I~
        int sz=resStock.length;                 //2                //~v@@@R~
        Bitmap[][] bmouts=new Bitmap[sz][];                        //~v@@@I~
//      bitmapStock=bmouts;                                        //~v@@@R~
        AG.bitmapStock=bmouts;                                     //~v@@@I~
        Matrix matrix=new Matrix();                                //~v@@@I~
        for (int jj=0;jj<sz;jj++)                                  //~v@@@R~
        {                                                          //~v@@@I~
            Bitmap[] bmout=new Bitmap[PLAYERS];                    //~v@@@R~
            bmouts[jj]=bmout;                                      //~v@@@I~
            int res=resStock[jj];    //nodestination standing and lying//~v@@@I~
//          int ww=(jj==PIECE_STOCK_EARTH ? earthPieceW : StockPieceW);//~v@@@R~
//          int hh=(jj==PIECE_STOCK_EARTH ? earthPieceH : StockPieceH);//~v@@@R~
            int ww=(jj==PIECE_STOCK_EARTH ? riverPieceW : StockPieceW);//~v@@@I~
            int hh=(jj==PIECE_STOCK_EARTH ? riverPieceH : StockPieceH);//~v@@@I~
            Bitmap bm=loadPieceImage(res,ww,hh);                   //~v@@@I~
            int bmww=bm.getWidth();                                //~v@@@I~
            int bmhh=bm.getHeight();                               //~v@@@I~
            for (int ii=0;ii<PLAYERS;ii++)                         //~v@@@R~
            {                                                      //~v@@@I~
                switch(ii)                                         //~v@@@I~
                {                                                  //~v@@@I~
                case 0:                 //up                       //~v@@@I~
                case 2:                 //down                     //~v@@@I~
                    bmout[ii]=bm;                                  //~v@@@I~
                    break;                                         //~v@@@R~
                case 1:    //1:to left and 3/right                 //~v@@@R~
                    matrix.setRotate(90,bmww/2,bmhh/2);            //~v@@@R~
//                  bmout[ii]=Bitmap.createBitmap(bm,0,0,bmww,bmhh,matrix,true);//~v@@@R~
                    bmout[ii]=Graphics.createBitmap(bm,0,0,bmww,bmhh,matrix,true);//~v@@@I~
                    break;                                         //~v@@@I~
                default:   //1:to left and 3/right                 //~v@@@I~
                    matrix.setRotate(270,bmww/2,bmhh/2);           //~v@@@R~
//                  bmout[ii]=Bitmap.createBitmap(bm,0,0,bmww,bmhh,matrix,true);//~v@@@R~
                    bmout[ii]=Graphics.createBitmap(bm,0,0,bmww,bmhh,matrix,true);//~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
//      stockPieceW=bitmapStock[PIECE_STOCK_STOCK][0].getWidth();  //~v@@@R~
//      stockPieceH=bitmapStock[PIECE_STOCK_STOCK][0].getHeight(); //~v@@@R~
//      stockEarthPieceW=bitmapStock[PIECE_STOCK_EARTH][0].getWidth();//~v@@@R~
//      stockEarthPieceH=bitmapStock[PIECE_STOCK_EARTH][0].getHeight();//~v@@@R~
        stockPieceW=AG.bitmapStock[PIECE_STOCK_STOCK][0].getWidth();//~v@@@I~
        stockPieceH=AG.bitmapStock[PIECE_STOCK_STOCK][0].getHeight();//~v@@@I~
        stockEarthPieceW=AG.bitmapStock[PIECE_STOCK_EARTH][0].getWidth();//~v@@@I~
        stockEarthPieceH=AG.bitmapStock[PIECE_STOCK_EARTH][0].getHeight();//~v@@@I~
        if (Dump.Y) Dump.println("Pieces.loadPiecesStock stockPieceW="+stockPieceW+",stockPieceH="+stockPieceH+",earth stock w="+stockEarthPieceW+",h="+stockEarthPieceH);//~v@@@I~//+vay7R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private static void recyclePiecesStock()                       //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.recyclePiecesStock");     //~v@@@R~
//      if (bitmapStock==null)                                     //~v@@@R~
        if (AG.bitmapStock==null)                                  //~v@@@I~
        	return;                                                //~v@@@I~
//      int sz=bitmapStock.length;                 //2             //~v@@@R~
        int sz=AG.bitmapStock.length;                 //2          //~v@@@I~
        for (int jj=0;jj<sz;jj++)                                  //~v@@@I~
        {                                                          //~v@@@I~
            for (int ii=0;ii<PLAYERS;ii++)                         //~v@@@I~
            {                                                      //~v@@@I~
//      		recycle(bitmapStock[jj][ii]);                      //~v@@@R~
        		recycle(AG.bitmapStock[jj][ii]);                   //~v@@@I~
//        		bitmapStock[jj][ii]=null;                          //~v@@@R~
          		AG.bitmapStock[jj][ii]=null;                       //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
//      bitmapStock=null;                                          //~v@@@R~
        AG.bitmapStock=null;                                       //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private void loadPiecesStarter()                               //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.loadPiecesStarter");      //~v@@@R~
//      if (bitmapStarter!=null)                                   //~v@@@R~
        if (AG.bitmapStarter!=null)                                //~v@@@I~
        	return;                                                //~v@@@I~
	    int sz=resStarter.length;                                  //~v@@@I~
        Bitmap[][] bmouts=new Bitmap[sz][PLAYERS];                 //~v@@@R~
		loadPieces4(resStarter,bmouts);                      //~v@@@I~
//      bitmapStarter=bmouts;                                      //~v@@@R~
        AG.bitmapStarter=bmouts;                                   //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private void loadPiecesBird()                                  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.loadPiecesBird");         //~v@@@I~
//      if (bitmapBird!=null)                                      //~v@@@R~
        if (AG.bitmapBird!=null)                                   //~v@@@I~
        	return;                                                //~v@@@I~
        Bitmap[] bmouts=new Bitmap[PLAYERS];                       //~v@@@R~
		loadPieces41(resBird,bmouts);                              //~v@@@R~
//      bitmapBird=bmouts;                                         //~v@@@R~
        AG.bitmapBird=bmouts;                                      //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*create bitmap for 4 destination object ; for starter mark   //~v@@@R~
	//***************************************************************//~v@@@I~
	private void loadPieces4(int[] Presids,Bitmap[][] Poutbm)      //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.loadPieces4");            //~v@@@R~
//      recyclePieces4();                                          //~v@@@R~
        int ww=0,hh=0,bmW,bmH;                                     //~v@@@I~
        int sz=Presids.length;                                     //~v@@@I~
        Bitmap[][] outbm=Poutbm;                                   //~v@@@I~
        Matrix matrix=new Matrix();                                //~v@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@I~
        {                                                          //~v@@@I~
            int res=Presids[ii];                                   //~v@@@I~
            Bitmap bm;                                             //~v@@@I~
          if (AG.swSmallDevice)                                    //~v@@@I~
            bm=loadPieceImage(res,AG.scaleSmallDevice);               //~v@@@R~
          else                                                     //~v@@@I~
            bm=loadPieceImage(res,ww,hh);                          //~v@@@R~
            bmW=bm.getWidth();                                     //~v@@@I~
            bmH=bm.getHeight();                                    //~v@@@I~
            starterW=bmW;                                          //~v@@@I~
            starterH=bmH;                                          //~v@@@I~
            for (int jj=0;jj<PLAYERS;jj++)                         //~v@@@R~
            {                                                      //~v@@@I~
                switch(jj)                                         //~v@@@I~
                {                                                  //~v@@@I~
                case 0:                 //up                       //~v@@@I~
                    outbm[ii][jj]=bm;                                  //~v@@@I~
                    break;                                         //~v@@@I~
                case 1:                //right player              //~v@@@I~
                    matrix.setRotate(270,bmW/2,bmH/2);             //~v@@@I~
//                  outbm[ii][jj]=Bitmap.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@R~
                    outbm[ii][jj]=Graphics.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@I~
                    break;                                         //~v@@@I~
                case 2:                 //down                     //~v@@@I~
                    matrix.setRotate(180,bmW/2,bmH/2);             //~v@@@I~
//                  outbm[ii][jj]=Bitmap.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@R~
                    outbm[ii][jj]=Graphics.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@I~
                    break;                                         //~v@@@I~
                default:   //1:to left and 3/right                 //~v@@@I~
                    matrix.setRotate(90,bmW/2,bmH/2);              //~v@@@I~
//                  outbm[ii][jj]=Bitmap.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@R~
                    outbm[ii][jj]=Graphics.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*create bitmap for 4 destination object                      //~v@@@I~
	//***************************************************************//~v@@@I~
	private void loadPieces41(int Presids,Bitmap[] Poutbm)         //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.loadPiece4");             //~v@@@I~
        int ww=0,hh=0,bmW,bmH;                                     //~v@@@I~
        Bitmap[] outbm=Poutbm;                                     //~v@@@I~
        Matrix matrix=new Matrix();                                //~v@@@I~
        ww=(int)(starterW*BIRD_RATEW);                             //~v@@@R~
        hh=(int)(starterH*BIRD_RATEH);                             //~v@@@R~
            int res=Presids;                                       //~v@@@I~
            Bitmap bm=loadPieceImage(res,ww,hh);                   //~v@@@I~
            bmW=bm.getWidth();                                     //~v@@@I~
            bmH=bm.getHeight();                                    //~v@@@I~
            birdW=bmW;                                             //~v@@@R~
            birdH=bmH;                                             //~v@@@R~
            for (int jj=0;jj<PLAYERS;jj++)                         //~v@@@I~
            {                                                      //~v@@@I~
                switch(jj)                                         //~v@@@I~
                {                                                  //~v@@@I~
                case 0:                 //up                       //~v@@@I~
                    outbm[jj]=bm;                                  //~v@@@I~
                    break;                                         //~v@@@I~
                case 1:                //right player              //~v@@@I~
                    matrix.setRotate(270,bmW/2,bmH/2);             //~v@@@I~
//                  outbm[jj]=Bitmap.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@R~
                    outbm[jj]=Graphics.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@I~
                    break;                                         //~v@@@I~
                case 2:                 //down                     //~v@@@I~
                    matrix.setRotate(180,bmW/2,bmH/2);             //~v@@@I~
//                  outbm[jj]=Bitmap.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@R~
                    outbm[jj]=Graphics.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@I~
                    break;                                         //~v@@@I~
                default:   //1:to left and 3/right                 //~v@@@I~
                    matrix.setRotate(90,bmW/2,bmH/2);              //~v@@@I~
//                  outbm[jj]=Bitmap.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@R~
                    outbm[jj]=Graphics.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*recycle starter                                             //~v@@@I~
	//***************************************************************//~v@@@I~
	private static void recyclePieces4()                           //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.recyclePieces4");         //~v@@@R~
//      if (bitmapStarter==null)                                   //~v@@@R~
        if (AG.bitmapStarter==null)                                //~v@@@I~
        	return;                                                //~v@@@I~
//      int sz=bitmapStarter.length;                               //~v@@@R~
        int sz=AG.bitmapStarter.length;                            //~v@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@I~
        {                                                          //~v@@@I~
            for (int jj=0;jj<PLAYERS;jj++)                         //~v@@@I~
            {                                                      //~v@@@I~
//  	        recycle(bitmapStarter[ii][jj]);                    //~v@@@R~
    	        recycle(AG.bitmapStarter[ii][jj]);                 //~v@@@I~
//  	        bitmapStarter[ii][jj]=null;                        //~v@@@R~
    	        AG.bitmapStarter[ii][jj]=null;                     //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
//      bitmapStarter=null;                                        //~v@@@R~
        AG.bitmapStarter=null;                                     //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*recycle Bird                                                //~v@@@I~
	//***************************************************************//~v@@@I~
	private static void recyclePiecesBird()                        //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.recycleBird");            //~v@@@I~
//      if (bitmapBird==null)                                      //~v@@@R~
        if (AG.bitmapBird==null)                                   //~v@@@I~
        	return;                                                //~v@@@I~
        for (int jj=0;jj<PLAYERS;jj++)                             //~v@@@I~
        {                                                          //~v@@@I~
//          recycle(bitmapBird[jj]);                               //~v@@@R~
            recycle(AG.bitmapBird[jj]);                            //~v@@@I~
//          bitmapBird[jj]=null;                                   //~v@@@R~
            AG.bitmapBird[jj]=null;                                //~v@@@I~
        }                                                          //~v@@@I~
//      bitmapBird=null;                                           //~v@@@R~
        AG.bitmapBird=null;                                        //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@M~
	//*create bitmap for 2 destination object                      //~v@@@M~
	//***************************************************************//~v@@@M~
	private void loadPiecesCoin()                                  //~v@@@M~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("Pieces.loadPiecesCoin");         //~v@@@M~
//      if (bitmapPointStick!=null)                                //~v@@@R~
        if (AG.bitmapPointStick!=null)                             //~v@@@I~
            return;                                                //~v@@@M~
	    int sz=resCoin.length;                                     //~v@@@M~
        Bitmap[][] bmouts=new Bitmap[sz][PLAYERS];                 //~v@@@M~
		loadPieces2(resCoin,bmouts,true);                          //~v@@@M~
//      bitmapPointStick=bmouts;                                   //~v@@@R~
        AG.bitmapPointStick=bmouts;                                //~v@@@I~
    }                                                              //~v@@@M~
	//***************************************************************//~v@@@I~
	private void loadPieces2(int[] Presids,Bitmap[][] Poutbm,boolean Ptall)//~v@@@R~
    {                                                              //~v@@@I~
//  	recyclePieces2();                                          //~v@@@R~
        int ww=0,hh=0,bmW,bmH;                                     //~v@@@I~
        int sz=Presids.length;                                     //~v@@@I~
        Bitmap[][] outbm=Poutbm;                                   //~v@@@I~
        Matrix matrix=new Matrix();                                //~v@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@I~
        {                                                          //~v@@@I~
            int res=Presids[ii];                                   //~v@@@I~
//          Bitmap bm=loadPieceImage(res,ww,hh);                   //~v@@@R~
            Bitmap bm;                                             //~v@@@I~
            if (AG.swSmallDevice)                                  //~v@@@I~
				bm=loadPieceImage(res,AG.scaleSmallDevice); //~v@@@I~
            else                                                   //~v@@@I~
				bm=loadPieceImage(res,ww,hh);               //~v@@@I~
            bmW=bm.getWidth();                                     //~v@@@I~
            bmH=bm.getHeight();                                    //~v@@@I~
            if (Ptall)	//original is portrait style               //~v@@@R~
            {                                                      //~v@@@I~
            	coinW=bmH;                                         //~v@@@I~
            	coinH=bmW;                                         //~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
            	coinW=bmW;                                         //~v@@@R~
            	coinH=bmH;                                         //~v@@@R~
            }                                                      //~v@@@I~
            matrix.setRotate(90,bmW/2,bmH/2);                      //~v@@@I~
//          Bitmap bm90=Bitmap.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@R~
            Bitmap bm90=Graphics.createBitmap(bm,0,0,bmW,bmH,matrix,true);//~v@@@I~
            for (int jj=0;jj<PLAYERS;jj++)                         //~v@@@R~
            {                                                      //~v@@@I~
                switch(jj)                                         //~v@@@I~
                {                                                  //~v@@@I~
                case 0:                                            //~v@@@I~
                case 2:                 //up                       //~v@@@I~
                	if (Ptall)	//original is portrait stle        //~v@@@I~
	                    outbm[ii][jj]=bm90;                        //~v@@@I~
                    else                                           //~v@@@I~
	                    outbm[ii][jj]=bm;                          //~v@@@R~
                    break;                                         //~v@@@I~
                default:   //1:to left and 3/right                 //~v@@@I~
                	if (Ptall)                                     //~v@@@I~
	                    outbm[ii][jj]=bm;                          //~v@@@I~
                    else                                           //~v@@@I~
	                    outbm[ii][jj]=bm90;                        //~v@@@R~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*recycle coin                                                //~v@@@I~
	//***************************************************************//~v@@@I~
	private static void recyclePieces2()                           //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.recyclePieces2");         //~v@@@R~
//      if (bitmapPointStick==null)                                //~v@@@R~
        if (AG.bitmapPointStick==null)                             //~v@@@I~
        	return;                                                //~v@@@I~
//      int sz=bitmapPointStick.length;                            //~v@@@R~
        int sz=AG.bitmapPointStick.length;                         //~v@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@I~
        {                                                          //~v@@@I~
            for (int jj=0;jj<PLAYERS;jj++)                         //~v@@@I~
            {                                                      //~v@@@I~
//  	        recycle(bitmapPointStick[ii][jj]);                 //~v@@@R~
    	        recycle(AG.bitmapPointStick[ii][jj]);              //~v@@@I~
//  	        bitmapPointStick[ii][jj]=null;                     //~v@@@R~
    	        AG.bitmapPointStick[ii][jj]=null;                  //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
//      bitmapPointStick=null;                                     //~v@@@R~
        AG.bitmapPointStick=null;                                  //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@M~
	private void loadPiecesDice()                                  //~v@@@M~
    {                                                              //~v@@@M~
    	int ww=table.getDicePieceWidth();                          //~v@@@M~
        if (Dump.Y) Dump.println("Pieces.loadPiecesDice oldDiceWidth="+oldDiceWidth+",new="+ww);//~v@@@I~
        if (oldDiceWidth!=0)                                       //~v@@@I~
        {                                                          //~v@@@I~
        	if (oldDiceWidth==ww)                                  //~v@@@I~
            	return;                                            //~v@@@I~
	    	recyclePiecesDice();                                   //~v@@@I~
        }                                                          //~v@@@I~
        oldDiceWidth=ww;                                           //~v@@@I~
        int hh=ww;                                                 //~v@@@M~
        int ress[]=resDice;                                        //~v@@@M~
	    int sz=ress.length;                                        //~v@@@M~
        Bitmap[] bmouts=new Bitmap[sz];                            //~v@@@M~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@M~
        {                                                          //~v@@@M~
        	int res=ress[ii];                                      //~v@@@M~
		    Bitmap bm=loadPieceImage(res,ww,hh);                   //~v@@@M~
            bmouts[ii]=bm;                                         //~v@@@M~
        }                                                          //~v@@@M~
//      bitmapDice=bmouts;                                         //~v@@@R~
        AG.bitmapDice=bmouts;                                      //~v@@@I~
    }                                                              //~v@@@M~
	//***************************************************************//~v@@@M~
	private static void recyclePiecesDice()                             //~v@@@R~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("Pieces.recyclePieceDice");       //~v@@@M~
//      if (bitmapDice==null)                                      //~v@@@R~
        if (AG.bitmapDice==null)                                   //~v@@@I~
        	return;                                                //~v@@@M~
//      int sz=bitmapDice.length;                                  //~v@@@R~
        int sz=AG.bitmapDice.length;                               //~v@@@I~
        for (int ii=0;ii<sz;ii++)                                  //~v@@@M~
        {                                                          //~v@@@M~
//          recycle(bitmapDice[ii]);                               //~v@@@R~
            recycle(AG.bitmapDice[ii]);                            //~v@@@I~
//          bitmapDice[ii]=null;                                   //~v@@@R~
            AG.bitmapDice[ii]=null;                                //~v@@@I~
        }                                                          //~v@@@M~
//      bitmapDice=null;                                           //~v@@@R~
        AG.bitmapDice=null;                                        //~v@@@I~
    }                                                              //~v@@@M~
	//***************************************************************//~v@@@I~
    public static Bitmap loadPieceImage(int Presid,double Pscale)  //~v@@@I~
    {                                                              //~v@@@I~
        Bitmap bm=loadPieceImage(Presid);                          //~v@@@I~
        if (Pscale!=1.0)                                           //~v@@@I~
        {                                                          //~v@@@I~
        	if (Dump.Y) Dump.println("Pieces.loadPieceImage old ww="+bm.getWidth()+",hh="+bm.getHeight());//~v@@@I~
        	int ww=(int)(bm.getWidth()*Pscale);                    //~v@@@I~
        	int hh=(int)(bm.getHeight()*Pscale);                   //~v@@@I~
            {                                                      //~v@@@I~
                Bitmap bmscaled=scaleImage(bm,ww,hh);              //~v@@@I~
                recycle(bm);                                       //~v@@@I~
                bm=bmscaled;                                       //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.loadPieceImage scale="+Pscale+",ww="+bm.getWidth()+",hh="+bm.getHeight());//~v@@@I~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    public static Bitmap loadPieceImage(int Presid,int Pww,int Phh)//~v@@@R~
    {                                                              //~v@@@I~
        Bitmap bm=loadPieceImage(Presid);                          //~v@@@I~
        if (Dump.Y) Dump.println(CN+"loadPieceImage resid="+Integer.toHexString(Presid)+",Pww="+Pww+",Phh="+Phh);//~vay7I~
        int ww=bm.getWidth();                                      //~v@@@I~
        int hh=bm.getHeight();                                     //~v@@@I~
        int phh=Phh;                                               //~v@@@I~
        int pww=Pww;                                               //~v@@@I~
        if (phh!=0 || pww!=0)                                      //~v@@@I~
        {                                                          //~v@@@I~
        	if (phh==0)                                            //~v@@@R~
        		phh=(int)((double)pww*((double)hh/(double)ww));    //~v@@@R~
        	if (pww==0)                                            //~v@@@I~
        		pww=(int)((double)phh*((double)ww/(double)hh));    //~v@@@I~
                                                                   //~v@@@I~
            if (pww!=ww && phh!=hh)                                //~v@@@R~
            {                                                      //~v@@@R~
                Bitmap bmscaled=scaleImage(bm,pww,phh);            //~v@@@R~
                recycle(bm);                                       //~v@@@R~
                bm=bmscaled;                                       //~v@@@R~
            }                                                      //~v@@@R~
        }                                                          //~v@@@I~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    public static Bitmap loadPieceImage(int Presid)                //~v@@@I~
    {                                                              //~v@@@I~
        Bitmap bm=BitmapFactory.decodeResource(AG.resource,Presid);//~v@@@I~
        if (Dump.Y) Dump.println("loadPieceImage decode w="+bm.getWidth()+",h="+bm.getHeight()+",bm="+bm.toString());//~v@@@R~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    public static Bitmap scaleImage(Bitmap Pbitmap,int Pw,int Ph)  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("loadPieceImage.scaleImage scale req w="+Pw+",h="+Ph);//~v@@@I~//~vamdR~
        Bitmap bm=Bitmap.createScaledBitmap(Pbitmap,Pw,Ph,true/*filter*/);//~v@@@I~
        if (Dump.Y) Dump.println("loadPieceImage.scaleImage scaled w="+bm.getWidth()+",h="+bm.getHeight()+",bm="+bm.toString());//~v@@@R~//~vamdR~
        return bm;                                                        //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    public static Bitmap scaleImage(Bitmap Pbitmap,double Pscale)  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("loadPieceImage.scaleImage scaled by scale="+Pscale+",old w="+Pbitmap.getWidth()+",h="+Pbitmap.getHeight());//~v@@@R~//~vamdR~
        int ww=(int)(Pbitmap.getWidth()*Pscale);                       //~v@@@I~
        int hh=(int)(Pbitmap.getHeight()*Pscale);                      //~v@@@I~
        if (Dump.Y) Dump.println("loadPieceImage scaled ww="+ww+",hh="+hh);//~v@@@I~
        Bitmap bm=Bitmap.createScaledBitmap(Pbitmap,ww,hh,true/*filter*/);//~v@@@I~
        if (Dump.Y) Dump.println("loadPieceImage.scaleImage scaled by scale="+Pscale+",w="+bm.getWidth()+",h="+bm.getHeight());//~v@@@R~//~vamdR~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    public static void recycle(Bitmap Pbitmap)                     //~v@@@I~
    {                                                              //~v@@@I~
	    if (Dump.Y) Dump.println("Pieces.recycle");                //~v@@@I~
    	UView.recycle(Pbitmap);                                    //~v@@@R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    public Bitmap getBitmapHand(TileData Ptd)               //~v@@@R~
    {                                                              //~v@@@I~
        int num=Ptd.number;                                        //~v@@@R~
        if (Ptd.type<TT_JI)    //man,pin,sou                        //~v@@@I~
        {                                                          //~v@@@I~
        	if ((Ptd.flag & TDF_RED5)!=0)                          //~v@@@I~
            	num=PIECE_REDMARK;                                 //~v@@@R~
            else                                                   //~v@@@I~
	        	num++;                                             //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.getBitmapHand type="+Ptd.type+",number="+Ptd.number+",flag="+Ptd.flag+",bitmapNo="+num);//~v@@@R~
//      Bitmap bm=bitmapAllPieces[PIECE_STANDING][Ptd.type][num];  //~v@@@R~
        Bitmap bm=AG.bitmapAllPieces[PIECE_STANDING][Ptd.type][num];//~v@@@I~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    public Bitmap getBitmapHandPair(TileData Ptd,int Pplayer)      //~v@@@R~
    {                                                              //~v@@@I~
        Bitmap bm=getBitmapRiver(Ptd,Pplayer);                       //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.getBitmapHandPair player="+Pplayer+",type="+Ptd.type+",number="+Ptd.number+",flag="+Ptd.flag);//~v@@@R~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*for ankan, upside down bitmap                               //~v@@@I~
	//***************************************************************//~v@@@I~
    public Bitmap getBitmapHandPairStock(int Pplayer)              //~v@@@I~
    {                                                              //~v@@@I~
//      Bitmap bm=bitmapStock[PIECE_STOCK_EARTH][Pplayer];         //~v@@@R~
        Bitmap bm=AG.bitmapStock[PIECE_STOCK_EARTH][Pplayer];      //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.getBitmapHandPairStock player="+Pplayer);//~v@@@I~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    public Bitmap getBitmapRiver(TileData Ptd,int Pplayer)         //~v@@@R~
    {                                                              //~v@@@I~
        int num=Ptd.number;                                        //~v@@@I~
        if (Ptd.type<TT_JI)    //man,pin,sou                       //~v@@@I~
        {                                                          //~v@@@I~
        	if ((Ptd.flag & TDF_RED5)!=0)                          //~v@@@I~
            	num=PIECE_REDMARK;                                 //~v@@@I~
            else                                                   //~v@@@I~
	        	num++;                                             //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.getBitmapRiver player="+Pplayer+",td="+Ptd.toString());//~v@@@R~
//      Bitmap bm=bitmapAllPiecesRiver[Pplayer][Ptd.type][num];    //~v@@@R~
        Bitmap bm=AG.bitmapAllPiecesRiver[Pplayer][Ptd.type][num]; //~v@@@I~
        return bm;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    public static int getStrokeWidth(int PpieceW)                  //~v@@@I~
    {                                                              //~v@@@I~
        int rc=COMPLETE_STROKE_WIDTH;                              //~v@@@R~
        int byRate=(int)(PpieceW*STROKE_WIDTH_RATE);               //~v@@@I~
        if (rc>byRate)                                             //~v@@@I~
        {                                                          //~v@@@I~
	        rc=(byRate/2)*2;	//even number                      //~v@@@R~
          if (AG.swSmallDip)                                       //~vaedI~
          {                                                        //~vaedI~
        	if (rc<STROKE_WIDTH_MIN_SMALLDIP)                      //~vaedI~
            	rc=STROKE_WIDTH_MIN_SMALLDIP;                      //~vaedI~
          }                                                        //~vaedI~
          else                                                     //~vaedI~
        	if (rc<STROKE_WIDTH_MIN)                               //~v@@@I~
            	rc=STROKE_WIDTH_MIN;                               //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("Pieces.getStrokeWidth rc="+rc+"swSmallDip="+AG.swSmallDip+",parmW="+PpieceW+",rate="+STROKE_WIDTH_RATE+",byRate="+byRate+",min="+STROKE_WIDTH_MIN);//~v@@@I~//~vaedR~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~vamdI~
	//*From Anim.getPiece                                          //~vamdI~
	//***************************************************************//~vamdI~
    public Bitmap getBitmapDrawable(TileData Ptd)                  //~vamdR~
    {                                                              //~vamdI~
        if (Dump.Y) Dump.println("Pieces.getBitmapDrawable td="+Ptd);//~vamdR~
        int num=Ptd.number;                                        //~vamdI~
        int type=Ptd.type;                                         //~vamdI~
        if (type!=TT_JI)                                           //~vamdI~
        	if (Ptd.isRed5())                                      //~vamdI~
            	num=0;                                             //~vamdI~
            else                                                   //~vamdI~
            	num++;                                             //~vamdI~
        int resID=resAllS[type][num];                              //~vamdR~
		Bitmap bmp=loadPieceImage(resID);                          //~vamdI~
        if (Dump.Y) Dump.println("Pieces.getBitmapDrawable bmp="+bmp);//~vamdI~
        return bmp;                                                //~vamdI~
    }                                                              //~vamdI~
}//class Pieces                                                    //~v@@@R~
