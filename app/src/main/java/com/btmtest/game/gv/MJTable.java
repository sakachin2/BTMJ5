//*CID://+DATER~: update#= 480;                                    //~v@@@R~//~9313R~
//**********************************************************************//~v101I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.game.GConst.*;//~v@@@R~
import static com.btmtest.StaticVars.*;                            //~v@@@R~
import static com.btmtest.game.gv.Earth.*;
import static com.btmtest.game.gv.Stock.*;
import static com.btmtest.game.gv.Pieces.*;//~v@@@I~

import android.graphics.Point;
import android.graphics.Rect;

public class MJTable                                               //~v@@@R~
{                                                                  //~0914I~
                                                                   //~v@@@I~
                                                                   //~v@@@I~
    public static final int    TBL_RIVERCTR_X_P         =6; //it is mannar//~v@@@R~
    public static final int    TBL_RIVERCTR_Y_P         =3;        //~v@@@R~
    public static final int    TBL_RIVERCTR_X_L         =10;       //~v@@@I~
    public static final int    TBL_RIVERCTR_Y_L         =2;        //~v@@@I~
    public static final int    TBL_RIVER_SPACING_X      =1;        //~v@@@I~
    public static final int    TBL_RIVER_SPACING_Y      =2;        //~v@@@I~
                                                                   //~v@@@I~
    public static final int    PIECE_SPACING=1;	               //~v@@@I~
    public static final int    PIECE_SPACING_TAKEN=5;              //~v@@@I~
//  public static final int    MSGBAR_SIZE              =40;       //~v@@@I~//~9811R~
    private static final int   SIZE_MSGBAR              =40;       //~9811I~
                                                                   //~v@@@I~
    private static final int    STOCK_SPACING_X=1;                 //~v@@@R~
    private static final int    DICE_PIECE_WIDTH=50;               //~v@@@R~
//  private static final int    DICE_PIECE_WIDTH_SMALL=40;         //~9808I~//~9809R~
    private static final int    DICE_PIECE_GAP=4;                  //~v@@@I~
    private static final int    DICEBOX_MARGIN=10;                 //~v@@@I~
//  private static final int    DICEBOX_MARGIN_SMALL=7;            //~9808I~//~9809R~
    private static final int    POINTSTICK_MARGIN=10;              //~v@@@R~
                                                                   //~v@@@I~
    private static final double P_MARGIN_BOTTOM           =     0.02;//~v@@@I~
    private static final double P_MARGIN_TOP              =     0.05;//~v@@@R~//~v@@@R~
//  private static final double P_MARGIN_LEFT             =     0.00;//~v@@@R~
//  private static final double P_MARGIN_RIGHT            =     0.00;//~v@@@R~
    private static final double P_MARGIN_LEFT             =     0.05;//~v@@@I~
    private static final double P_MARGIN_RIGHT            =     0.05;//for openReach//~v@@@R~
    private static final double P_MARGIN_STOCK_TOP        =     0.01;//~v@@@R~
    private static final double P_MARGIN_STOCK_BOTTOM     =     0.01;//~v@@@I~
//  private static final double P_MARGIN_STOCK_SIDE       =     0.02;//~v@@@R~
    private static final double P_MARGIN_STOCK_SIDE       =     0.025;//~v@@@I~
    private static final double P_STOCK_HEIGHT            =     0.025;//~v@@@R~//~v@@@R~
    private static final double P_MARGIN_RIVER            =     0.01;//~v@@@I~
    private static final double P_RIVER_HEIGHT            =     0.15;//~v@@@R~
    private static final double P_MARGIN_HAND_SIDE       =      0.015;//~v@@@R~
//  private static final double P_MARGIN_HAND_SIDE       =      0.10;//~v@@@R~
    private static final double P_RIVER_SPACE_Y           =     0.01;//~v@@@R~
    private static final int    P_MARGIN_EARTH_PAIR_RIGHT =   15;  //~v@@@R~
    private static final int    P_MARGIN_EARTH_PAIR_BOTTOM=    5;  //~v@@@R~
                                                                   //~v@@@M~
    private static final double L_MARGIN_BOTTOM           =     0.005;//~v@@@R~
    private static final double L_MARGIN_TOP              =     0.01;//~v@@@I~
    private static final double L_MARGIN_LEFT             =     0.05;//~v@@@R~
    private static final double L_MARGIN_RIGHT            =     0.05;//~v@@@R~
//  private static final double L_MARGIN_STOCK_TOP        =     0.10;//~v@@@R~//~9313R~
    private static final double L_MARGIN_STOCK_TOP        =     0.01;//~9313I~
    private static final double L_MARGIN_STOCK_BOTTOM     =     0.02;//~v@@@I~
//  private static final double L_MARGIN_STOCK_SIDE       =     0.10;//~v@@@R~
    private static final double L_MARGIN_STOCK_SIDE       =     0.02;//~v@@@I~
    private static final double L_STOCK_HEIGHT            =     0.10;//~v@@@R~
    private static final double L_MARGIN_RIVER            =     0.01;//~v@@@I~
    private static final double L_RIVER_HEIGHT            =     0.15;//~v@@@R~
    private static final double L_MARGIN_HAND_SIDE       =      0.05;//~v@@@R~
    private static final double L_RIVER_SPACE_Y           =     0.01;//~v@@@R~
    private static final int    L_MARGIN_EARTH_PAIR_RIGHT =   15;  //~v@@@R~
    private static final int    L_MARGIN_EARTH_PAIR_BOTTOM=    5;  //~v@@@R~
                                                                   //~v@@@I~
    public Pieces pieces;                                          //~v@@@R~
    public boolean swPortrait;                                     //~v@@@R~
                                                                   //~v@@@I~
    public int handPieceH,handPieceW,riverPieceW,riverPieceH;      //~v@@@R~
    public int stockH,stockPieceW,stockPieceH,stockEarthPieceW,stockEarthPieceH;                     //~v@@@R~
    public int riverCtrX,riverCtrY;                                //~v@@@I~
    public int stockLength;                                        //~v@@@I~
                                                                   //~v@@@I~
    public int WW,HH;//scrWW,scrHH;                                  //~v@@@R~//~9807R~
    private boolean swLarge=true;                                  //~v@@@R~
    private double pieceScale;                                     //~v@@@I~
    private int marginBottom,marginTop,moutainH,marginRiver,riverH;//~v@@@R~
    private int marginLeft,marginRight;                            //~v@@@I~
    private int marginHandSide,marginStockTop,marginStockBottom,marginStockSide;//~v@@@R~
    public  int bottomButtonH,topButtonH,leftButtonW,rightButtonW; //~v@@@R~
    public  int handX,handY;                                       //~v@@@R~
    private int stockX,stockY,riverX,riverY;                       //~v@@@I~
    private int riverLeftX,riverLeftY,riverRightX,riverRightY,riverFacingX,riverFacingY;//~v@@@R~
	private int stockLeftX,stockLeftY,stockRightX,stockRightY,stockFacingX,stockFacingY;//~v@@@R~
    private int handLength,riverLength;                            //~v@@@R~
    private int dicePieceWidth;                        //~v@@@R~
    private int diceBoxX,diceBoxY,diceBoxW,diceBoxH;               //~v@@@I~
    private int[] diceX,diceY;                                     //~v@@@I~
    private int[] starterX={0,0,0,0};                              //~v@@@I~
    private int[] starterY={0,0,0,0};                              //~v@@@I~
    private int[] coinX={0,0,0,0};                                 //~v@@@I~
    private int[] coinY={0,0,0,0};                                 //~v@@@I~
    public  int[] birdX={0,0,0,0};                                 //~9430R~
    public  int[] birdY={0,0,0,0};                                 //~9430R~
    private int topSpace,leftSpace,rightSpace;                     //~v@@@R~
    private Rect[] openRect=new Rect[PLAYERS];                     //~v@@@I~
    public  Rect[] rectNamePlate=new Rect[PLAYERS];                //~v@@@R~
    private boolean swVerticalEarth=false;                         //~9317I~
    private int marginDiceBox;                                     //~9808I~
    public  int sizeMsgBar=SIZE_MSGBAR;                             //~9811I~
//*************************                                        //~v@@@I~
	public MJTable(int Pww,int Phh)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("MJTable Constructor Pww="+Pww+",Phh="+Phh);         //~1506R~//~@@@@R~//~v@@@R~//~9807R~
        AG.aMJTable=this;                                          //~v@@@I~
        WW=Pww;	//framelayout width                                //~v@@@R~
        HH=Phh;                                                    //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
	//***************************************************************//~v@@@I~
    private void init()                                            //~v@@@I~
    {                                                              //~v@@@I~
    	if (AG.swSmallDevice)                                      //~9811I~
        	sizeMsgBar*=AG.scaleSmallDevice;                       //~9811I~
//  	marginDiceBox=AG.swSmallDevice ? DICEBOX_MARGIN_SMALL : DICEBOX_MARGIN;//~9808I~//~9809R~
    	marginDiceBox=AG.swSmallDevice ? (int)(DICEBOX_MARGIN*AG.scaleSmallDevice) : DICEBOX_MARGIN;//~9809I~
    	swPortrait=(HH>WW);                                        //~v@@@I~
//      leftButtonW   =swPortrait ? 0 :AG.aGC.btnLeftW+(AG.aRule.swLeftButtons ? 0 : MSGBAR_SIZE) ;//~v@@@R~//~9811R~
        leftButtonW   =swPortrait ? 0 :AG.aGC.btnLeftW+(AG.aRule.swLeftButtons ? 0 : sizeMsgBar) ;//~9811I~
//      rightButtonW  =swPortrait ? 0 :AG.aGC.btnRightW+(AG.aRule.swLeftButtons ? MSGBAR_SIZE : 0);//~v@@@R~//~9811R~
        rightButtonW  =swPortrait ? 0 :AG.aGC.btnRightW+(AG.aRule.swLeftButtons ? sizeMsgBar : 0);//~9811I~
//      bottomButtonH =swPortrait ? GC.btnBottomH : 0;             //~v@@@R~
//      bottomButtonH =swPortrait ? AG.aGC.btnBottomH+MSGBAR_SIZE : 0;//~v@@@I~//~9811R~
        bottomButtonH =swPortrait ? AG.aGC.btnBottomH+sizeMsgBar : 0;//~9811I~
        topButtonH    =swPortrait ? AG.aGC.btnTopH : 0;                //~v@@@R~
        if (Dump.Y) Dump.println("MJTable.init GC.btnLeft="+AG.aGC.btnLeftW+",leftButtonW="+leftButtonW);//~9313I~
        setGeometry();                                             //~v@@@I~
        updateGeometry();                                          //~v@@@I~
        pieces=new Pieces(this,WW,HH);                             //~v@@@M~
        pieces.init();                                             //~v@@@I~
        setStarterPos();                                           //~v@@@R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    private void setGeometry()                                     //~v@@@R~
    {                                                              //~v@@@I~
        marginBottom       =(int)((swPortrait ? P_MARGIN_BOTTOM        : L_MARGIN_BOTTOM        )*HH);//~v@@@R~
        marginTop          =(int)((swPortrait ? P_MARGIN_TOP           : L_MARGIN_TOP           )*HH);//~v@@@I~
        marginLeft         =(int)((swPortrait ? P_MARGIN_LEFT          : L_MARGIN_LEFT          )*WW);//~v@@@I~
        marginRight        =(int)((swPortrait ? P_MARGIN_RIGHT         : L_MARGIN_RIGHT         )*WW);//~v@@@I~
        marginStockTop     =(int)((swPortrait ? P_MARGIN_STOCK_TOP     : L_MARGIN_STOCK_TOP     )*HH);//~v@@@R~
        marginStockBottom  =(int)((swPortrait ? P_MARGIN_STOCK_BOTTOM  : L_MARGIN_STOCK_BOTTOM  )*HH);//~v@@@I~
        marginStockSide    =(int)((swPortrait ? P_MARGIN_STOCK_SIDE    : L_MARGIN_STOCK_SIDE    )*WW);//~v@@@R~
        stockH             =(int)((swPortrait ? P_STOCK_HEIGHT         : L_STOCK_HEIGHT         )*HH);//~v@@@R~
        marginRiver        =(int)((swPortrait ? P_MARGIN_RIVER         : L_MARGIN_RIVER         )*HH);//~v@@@I~
        riverH             =(int)((swPortrait ? P_RIVER_HEIGHT         : L_RIVER_HEIGHT         )*HH);//~v@@@I~
        marginHandSide     =(int)((swPortrait ? P_MARGIN_HAND_SIDE    : L_MARGIN_HAND_SIDE    )*WW);//~v@@@R~
        riverCtrY          =      (swPortrait ? TBL_RIVERCTR_Y_P       : TBL_RIVERCTR_Y_L);//~v@@@I~
        riverCtrX          =      (swPortrait ? TBL_RIVERCTR_X_P       : TBL_RIVERCTR_X_L);//~v@@@I~
        topSpace=marginTop+topButtonH;                             //~v@@@I~
        leftSpace=marginLeft+leftButtonW;                          //~v@@@I~
        rightSpace=marginRight+rightButtonW;                       //~v@@@I~
        if (Dump.Y) Dump.println("MJTable.setGeometry margin Bottom="+marginBottom+",top="+marginTop);//~v@@@I~
        if (Dump.Y) Dump.println("MJTable.setGeometry margin stockH="+stockH+",marginStockSide="+marginStockSide);//~v@@@R~
        if (Dump.Y) Dump.println("MJTable.setGeometry margin Stock top="+marginStockTop+",marginBottom="+marginStockBottom);//~v@@@I~
        if (Dump.Y) Dump.println("MJTable.setGeometry topButtonH="+topButtonH+",topSpace="+topSpace);//~v@@@R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*called twice after handPieceH,riverPieceH reviced           //~v@@@R~
	//***************************************************************//~v@@@I~
    private void updateGeometry()                                  //~v@@@R~
    {                                                              //~v@@@I~
        handLength         =WW-(leftSpace+rightSpace+marginHandSide*2);//~v@@@R~
        handX             =leftSpace+marginHandSide;               //~v@@@R~
        handY             =HH-(bottomButtonH+marginBottom+handPieceH);                      //handPieceH later//~v@@@R~
        stockX             =leftSpace+(WW-(leftSpace+rightSpace+stockLength))/2; //left most//~v@@@R~
        stockY             =handY-(marginStockBottom+stockH);                               //left top//~v@@@R~
        riverX             =leftSpace+(WW-(leftSpace+rightSpace+riverLength))/2;        //riverLength//~v@@@R~
        riverY             =stockY-(marginRiver+riverH);       //left top for the player of 1st tile//~v@@@R~
        stockFacingX       =stockX+stockLength-stockPieceW;           //right most piece//~v@@@R~
//      stockFacingY       =topSpace+marginStockTop+stockH-stockPieceH+SHIFT_BACK;////~v@@@R~//~9313R~
        stockFacingY       =topSpace+(swPortrait ? P_MARGIN_EARTH_PAIR_BOTTOM : L_MARGIN_EARTH_PAIR_BOTTOM)//~9313I~
								+riverPieceH+marginStockTop+SHIFT_BACK;////~9313R~
        riverFacingX       =riverX+riverLength-riverPieceW;        //~v@@@R~
        riverFacingY       =stockFacingY+stockPieceH+marginRiver+riverH-riverPieceH;  //left top of 1st tile//~v@@@R~//~9313R~
//        riverLeftX         =leftSpace+marginStockSide+stockH+marginRiver+riverH-riverPieceH;//~v@@@R~
//        riverLeftY         =topSpace+(handY-(topSpace+riverLength))/2;//~v@@@R~
//        riverRightX        =WW-(rightSpace+marginStockSide+stockH+marginRiver+riverH);//~v@@@R~
//        riverRightY        =riverLeftY+riverLength-riverPieceW;    //left top of 1st tile//~v@@@R~
//      stockLeftX         =leftSpace+marginStockSide+stockH-stockPieceH;////~v@@@R~
        stockLeftX         =leftSpace+marginStockSide+stockH-stockPieceH+SHIFT_BACK;////~v@@@I~
//      stockLeftY         =topSpace+(handY-(topSpace+stockLength))/2;//top most//~v@@@R~
        stockLeftY         =stockFacingY+stockPieceH+(stockY-(stockFacingY+stockPieceH))/2-stockLength/2;//~v@@@I~
//      stockRightX        =WW-(rightSpace+marginStockSide+stockH);//~v@@@R~
        stockRightX        =WW-(rightSpace+marginStockSide+stockH+SHIFT_BACK);//~v@@@I~
//      stockRightY        =stockLeftY+stockLength-stockPieceW;       //bottom most//~v@@@R~
        stockRightY        =stockFacingY+stockPieceH+(stockY-(stockFacingY+stockPieceH))/2+stockLength/2-stockPieceW;//~v@@@R~
        riverLeftX         =leftSpace+marginStockSide+stockH+marginRiver+riverH-riverPieceH;//~v@@@I~
        riverLeftY         =stockLeftY+stockLength/2-riverLength/2;//~v@@@I~
        riverRightX        =WW-(rightSpace+marginStockSide+stockH+marginRiver+riverH);//~v@@@I~
        riverRightY        =riverLeftY+riverLength-riverPieceW;    //left top of 1st tile//~v@@@I~
        setOpenRect();                                             //~v@@@I~
        setNamePlateRect();                                        //~v@@@I~
        if (Dump.Y) Dump.println("MJTable.updateGeometry topSpace="+topSpace+",marginStockTop="+marginStockTop+",bottom="+marginStockBottom+",stockH="+stockH+",stockPieceH="+stockPieceH);//~v@@@R~
        if (Dump.Y) Dump.println("MJTable.updateGeometry stockFacing X="+stockFacingX+",Y="+stockFacingY+",riverPieceH="+riverPieceH);//~v@@@I~//~9313R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	public Rect[] getOpenRect()                                    //~v@@@I~
    {                                                              //~v@@@I~
    	Rect[] rc=openRect;                                               //+0328I~
        if (Dump.Y) Dump.println("MJTable.getOpenRect rc="+ Utils.toString(rc));//~0328I~
    	return rc;                                                 //~0328R~
    }                                                              //~v@@@I~
	//***************************************************************//+0328I~
	public Rect[] getOpenRectClone()                               //+0328I~
    {                                                              //+0328I~
    	Rect[] rc=new Rect[]{new Rect(openRect[0]),                //+0328I~
    							new Rect(openRect[1]),             //+0328I~
    							new Rect(openRect[2]),             //+0328I~
    							new Rect(openRect[3])};            //+0328I~
        if (Dump.Y) Dump.println("MJTable.getOpenRectClose rc="+ Utils.toString(rc));//+0328I~
    	return rc;                                                 //+0328I~
    }                                                              //+0328I~
	//***************************************************************//~v@@@I~
	private void setOpenRect()                                     //~v@@@I~
    {                                                              //~v@@@I~
    	int center,edge,len,start;                                 //~v@@@I~
        Rect r;                                                    //~v@@@I~
    //********************************************                 //~v@@@I~
    	openRect[PLAYER_YOU]=new Rect(0,0,0,0);                    //~v@@@I~
    	len=riverPieceW*HANDCTR_TAKEN+PIECE_SPACING*(HANDCTR-1)+PIECE_SPACING_TAKEN;//~v@@@I~
    //*right                                                       //~v@@@I~
		center=stockRightY+stockPieceW-stockLength/2;              //~v@@@I~
		edge=rightButtonW;                                         //~v@@@I~
        start=center+len/2;                                        //~v@@@I~
        r=new Rect( WW-(edge+riverPieceH),   start-len,  WW-edge,              start);//~v@@@R~
        openRect[PLAYER_RIGHT]=r;                                  //~v@@@I~
        if (Dump.Y) Dump.println("MJTable.setOpenRect right l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@I~
    //*facing                                                      //~v@@@I~
		center=stockFacingX+stockPieceW-stockLength/2;             //~v@@@I~
//  	edge=topButtonH;                                           //~v@@@I~//~9313R~
        edge=topSpace+(swPortrait ? P_MARGIN_EARTH_PAIR_BOTTOM : L_MARGIN_EARTH_PAIR_BOTTOM);//~9313I~
        start=center+len/2;                                        //~v@@@I~
        r=new Rect( start-len,          edge,            start,             edge+riverPieceH);//~v@@@R~
        openRect[PLAYER_FACING]=r;                                 //~v@@@R~
        if (Dump.Y) Dump.println("MJTable.setOpenRect facing l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@I~
    //*left                                                        //~v@@@I~
		center=stockLeftY+stockLength/2;                           //~v@@@I~
		edge=leftButtonW;                                          //~v@@@I~
        start=center-len/2;                                        //~v@@@I~
        r=new Rect( edge,               start,           edge+riverPieceH,  start+len);//~v@@@R~
        openRect[PLAYER_LEFT]=r;                                   //~v@@@R~
        if (Dump.Y) Dump.println("MJTable.setOpenRect left l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private void setNamePlateRect()                                //~v@@@I~
    {                                                              //~v@@@I~
        Rect r;                                                    //~v@@@I~
        int xx1,yy1,xx2,yy2;                                       //~9317I~
    //********************************************                 //~v@@@I~
    //*right                                                       //~v@@@I~
      if (swVerticalEarth)	                                       //~9317I~
      {                                                            //~9317I~
        r=openRect[PLAYER_RIGHT];                                  //~v@@@I~//~9317R~
//      r=new Rect( r.left, r.top, r.right,r.bottom);              //~v@@@R~//~9317R~
//      r=new Rect( r.left, stockRightY+stockPieceW-stockLength, r.right,stockRightY+stockPieceW);//~v@@@R~//~9317R~
        r=new Rect( stockRightX-marginRiver-riverPieceH, stockRightY+stockPieceW-stockLength, stockRightX-marginRiver+riverPieceH,stockRightY+stockPieceW);//~v@@@I~//~9317R~
        rectNamePlate[PLAYER_RIGHT]=r;                             //~v@@@I~//~9317R~
        if (Dump.Y) Dump.println("MJTable.setOpenRect right l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@I~//~9317R~
    //*facing                                                      //~v@@@I~//~9317R~
        r=openRect[PLAYER_FACING];                                 //~v@@@I~//~9317R~
//      r=new Rect( r.left, r.top,r.right, r.bottom);              //~v@@@R~//~9317R~
        r=new Rect( stockFacingX+stockPieceW-stockLength, stockFacingY+stockPieceH+marginRiver, stockFacingX+stockPieceW, stockFacingY+stockPieceH+marginRiver+riverPieceH);//~v@@@R~//~9317R~
        rectNamePlate[PLAYER_FACING]=r;                            //~v@@@I~//~9317R~
        if (Dump.Y) Dump.println("MJTable.setOpenRect facing l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@I~//~9317R~
    //*left                                                        //~v@@@I~//~9317R~
        r=openRect[PLAYER_LEFT];                                   //~v@@@I~//~9317R~
//      r=new Rect( r.left, r.top, r.right,r.bottom);              //~v@@@R~//~9317R~
//      r=new Rect( r.left, stockLeftY, r.right,stockLeftY+stockLength);//~v@@@R~//~9317R~
        r=new Rect( stockLeftX+stockPieceH+marginRiver, stockLeftY, stockLeftX+stockPieceH+marginRiver+riverPieceH, stockLeftY+stockLength);//~v@@@I~//~9317R~
        rectNamePlate[PLAYER_LEFT]=r;                              //~v@@@I~//~9317R~
        if (Dump.Y) Dump.println("MJTable.setOpenRect left l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@I~//~9317R~
    //*you                                                         //~v@@@I~//~9317R~
//      r=new Rect( stockX, handY, stockX+stockLength,handY+riverPieceH);//~v@@@R~//~9317R~
        r=new Rect( stockX, stockY-marginRiver-riverPieceH, stockX+stockLength, stockY-marginRiver);//~v@@@I~//~9317R~
        rectNamePlate[PLAYER_YOU]=r;                               //~v@@@I~//~9317R~
        if (Dump.Y) Dump.println("MJTable.setOpenRect you l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@I~//~9317R~
      }                                                            //~9317R~
      else                                                         //~9317I~
      {                                                            //~9317I~
    //*right                                                       //~9317I~
        xx1=stockX+stockLength;                                    //~9317I~
        xx2=stockRightX+stockPieceH;                               //~9317R~
        yy1=stockRightY+stockPieceW;                               //~9317I~
        yy2=stockY+stockPieceH;                                    //~9317R~
        r=new Rect(xx1,yy1,xx2,yy2);                               //~9317I~
        if (Dump.Y) Dump.println("MJTable.setNamePlateRect right="+r.toString());//~9317R~
        rectNamePlate[PLAYER_RIGHT]=r;                             //~9317I~
    //*facing                                                      //~9317I~
        xx1=stockX+stockLength;                                    //~9317I~
        xx2=stockRightX+stockPieceH;                               //~9317R~
        yy1=stockFacingY;                                          //~9317I~
        yy2=stockRightY-stockLength+stockPieceW;                   //~9317R~
        r=new Rect(xx1,yy1,xx2,yy2);                               //~9317I~
        if (Dump.Y) Dump.println("MJTable.setNamePlateRect facing="+r.toString());//~9317I~
        rectNamePlate[PLAYER_FACING]=r;                            //~9317I~
    //*left                                                        //~9317I~
        xx1=stockLeftX;                                            //~9317I~
        xx2=stockFacingX-stockLength+stockPieceW;                  //~9317R~
        yy1=stockFacingY;                                          //~9317R~
        yy2=stockLeftY-SHIFT_SIDE;                                 //~9317R~
        r=new Rect(xx1,yy1,xx2,yy2);                               //~9317I~
        if (Dump.Y) Dump.println("MJTable.setNamePlateRect left="+r.toString());//~9317I~
        rectNamePlate[PLAYER_LEFT]=r;                              //~9317I~
    //*you                                                         //~9317I~
        xx1=stockLeftX;                                            //~9317I~
        xx2=stockX;                                                //~9317I~
        yy1=stockLeftY+stockLength;                                //~9317I~
        yy2=stockY+stockPieceH;                                    //~9317R~
        r=new Rect(xx1,yy1,xx2,yy2);                               //~9317I~
        rectNamePlate[PLAYER_YOU]=r;                               //~9317I~
        if (Dump.Y) Dump.println("MJTable.setNamePlateRect you="+r.toString());//~9317I~
      }                                                            //~9317I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	private void setStarterPos()                                   //~v@@@I~
    {                                                              //~v@@@I~
//        int centerX=leftSpace+(WW-(leftSpace+rightSpace))/2;     //~v@@@R~
//        int centerY=topSpace+(handY-topSpace)/2;                 //~v@@@R~
		int left=riverLeftX+riverPieceH;                           //~v@@@I~
        int centerX=left+(riverRightX-left)/2;                     //~v@@@I~
        int bottom=riverFacingY+riverPieceH;                       //~v@@@I~
        int centerY=bottom+(riverY-bottom)/2;                      //~v@@@R~
        if (Dump.Y) Dump.println("MJTable.setStarterPos centerX="+centerX+",centerY="+centerY);//~v@@@I~
        int wwc,hhc,wws,hhs;                                       //~v@@@R~
        int gap=POINTSTICK_MARGIN;                                 //~v@@@R~
//*coin                                                            //~v@@@R~
//      wwc=pieces.coinW;                                          //~v@@@R~
//      hhc=pieces.coinH;                                          //~v@@@R~
        wwc=coinW;                                                 //~v@@@I~
        hhc=coinH;                                                 //~v@@@I~
        if (Dump.Y) Dump.println("MJTable.setStarterPos wwc="+wwc+",hhc="+hhc+",riverH="+riverH+",riverY="+riverY);//~v@@@R~
        coinX[0]=centerX-wwc/2;                                    //~v@@@I~
        coinY[0]=riverY-hhc-gap;                                    //~v@@@I~
        coinX[2]=centerX-wwc/2;                                    //~v@@@I~
        coinY[2]=riverFacingY+riverPieceH+gap;                     //~v@@@I~
        coinX[1]=riverRightX-hhc-gap;                              //~v@@@I~
        coinY[1]=centerY-wwc/2;                                    //~v@@@I~
        coinX[3]=riverLeftX+riverPieceH+gap;                       //~v@@@I~
        coinY[3]=centerY-wwc/2;                                    //~v@@@I~
        if (Dump.Y) Dump.println("MJTable.setStarterPos coinY0="+coinY[0]+",coinY2="+coinY[2]);//~v@@@I~
//*starter                                                         //~v@@@I~
//      wws=pieces.starterW;                                       //~v@@@R~
//      hhs=pieces.starterH;                                       //~v@@@R~
        wws=starterW;                                              //~v@@@I~
        hhs=starterH;                                              //~v@@@I~
        starterX[0]=centerX-wws/2;                                 //~v@@@R~
        starterY[0]=coinY[0]-hhs;                                  //~v@@@R~
        starterX[2]=centerX-wws/2;                                 //~v@@@R~
        starterY[2]=coinY[2]+hhc;                                  //~v@@@R~
        starterX[1]=coinX[1]-hhs;                                  //~v@@@R~
        starterY[1]=centerY-wws/2;                                 //~v@@@R~
        starterX[3]=coinX[3]+hhc;                                  //~v@@@R~
        starterY[3]=centerY-wws/2;                                 //~v@@@R~
//*dice                                                            //~v@@@I~
        diceX=new int[2];	//2 dice                               //~v@@@I~
        diceY=new int[2];	//2 dice                               //~v@@@I~
		if (swPortrait)                                            //~v@@@I~
        {                                                          //~v@@@I~
        	diceX[0]=centerX-dicePieceWidth/2;                     //~v@@@R~
        	diceY[0]=centerY-(dicePieceWidth+DICE_PIECE_GAP/2);   //2 dice vertical//~v@@@R~
        	diceX[1]=diceX[0];                                     //~v@@@R~
        	diceY[1]=centerY+DICE_PIECE_GAP/2;                     //~v@@@I~
//      	diceBoxW=dicePieceWidth+DICEBOX_MARGIN*2;              //~v@@@I~//~9808R~
//      	diceBoxH=(dicePieceWidth+DICEBOX_MARGIN)*2+DICE_PIECE_GAP;//~v@@@I~//~9808R~
        	diceBoxW=dicePieceWidth+marginDiceBox*2;               //~9808I~
        	diceBoxH=(dicePieceWidth+marginDiceBox)*2+DICE_PIECE_GAP;//~9808I~
        }                                                          //~v@@@I~
        else       //horizontal 2 dice                             //~v@@@R~
        {                                                          //~v@@@I~
        	diceX[0]=centerX-(dicePieceWidth+DICE_PIECE_GAP/2);   //2 dice vertical//~v@@@R~
        	diceY[0]=centerY-dicePieceWidth/2;                     //~v@@@R~
        	diceX[1]=centerX+DICE_PIECE_GAP/2;   //2 dice vertical //~v@@@I~
        	diceY[1]=diceY[0];                                     //~v@@@I~
//      	diceBoxH=dicePieceWidth+DICEBOX_MARGIN*2;              //~v@@@I~//~9808R~
//      	diceBoxW=(dicePieceWidth+DICEBOX_MARGIN)*2+DICE_PIECE_GAP;//~v@@@I~//~9808R~
        	diceBoxH=dicePieceWidth+marginDiceBox*2;               //~9808I~
        	diceBoxW=(dicePieceWidth+marginDiceBox)*2+DICE_PIECE_GAP;//~9808I~
        }                                                          //~v@@@I~
//      diceBoxX=diceX[0]-DICEBOX_MARGIN;                          //~v@@@R~//~9808R~
//      diceBoxY=diceY[0]-DICEBOX_MARGIN;                          //~v@@@I~//~9808R~
        diceBoxX=diceX[0]-marginDiceBox;                           //~9808I~
        diceBoxY=diceY[0]-marginDiceBox;                           //~9808I~
//*bird                                                            //~9430I~
        wws=birdW;                                                 //~9430I~
        hhs=birdH;                                                 //~9430I~
        birdX[0]=centerX-wws/2;                                    //~9430I~
        birdY[0]=starterY[0]-hhs-BIRD_DIST;                        //~9430R~
        birdX[2]=centerX-wws/2;                                    //~9430I~
        birdY[2]=starterY[2]+starterH+BIRD_DIST;                   //~9430R~
        birdX[1]=starterX[1]-hhs-BIRD_DIST;                        //~9430R~
        birdY[1]=centerY-wws/2;                                    //~9430I~
        birdX[3]=starterX[3]+starterH+BIRD_DIST;                   //~9430R~
        birdY[3]=centerY-wws/2;                                    //~9430I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*return handPieceWidth                                       //~v@@@R~
	//***************************************************************//~v@@@I~
	public int getEarthPieceWidth(int PpieceW,int PpieceH)         //~v@@@R~
    {                                                              //~v@@@I~
    	int ww=handLength;                                         //~v@@@R~
        ww-=PIECE_SPACING*(HANDCTR-1)+PIECE_SPACING_TAKEN;	//for 14 pieces//~v@@@R~
        ww=(int)((double)ww/HANDCTR_TAKEN);                        //~v@@@R~
        int hh=(int)((double)PpieceH/PpieceW*ww);                  //~v@@@I~
        handPieceW=ww;                                             //~v@@@R~
        handPieceH=hh;                                             //~v@@@R~
        if (Dump.Y) Dump.println("MJTables.getEarthPieceSize handLendth="+handLength+",ww="+ww+",hh="+hh);//~v@@@R~
        updateGeometry();                                          //~v@@@I~
        return ww;  //1 piece width                                //~v@@@R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*adjust earthPieceSize by scaled bitmap                      //~v@@@I~
	//***************************************************************//~v@@@I~
	public void setEarthPieceSize(int Pww,int Phh)                 //~v@@@I~
    {                                                              //~v@@@I~
    	int ww=handLength;                                         //~v@@@R~
        handPieceW=Pww;                                            //~v@@@R~
        handPieceH=Phh;                                            //~v@@@R~
        if (Dump.Y) Dump.println("MJTables.setEarthPieceSize ww="+Pww+",hh="+Phh);//~v@@@I~
        updateGeometry();                                          //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*return riverPieceWidth                                      //~v@@@I~
	//***************************************************************//~v@@@I~
	public Point getRiverPieceWidth(int PpieceW/*Hand Piece Width*/,int PpieceH)//~v@@@R~
    {                                                              //~v@@@I~
        int hh=riverH;                                             //~v@@@I~
        hh-=TBL_RIVER_SPACING_Y*(riverCtrY-1);                      //~v@@@R~
        hh/=riverCtrY;                                             //~v@@@R~
    	int ww=(int)((double)PpieceW/PpieceH*hh);                  //~v@@@R~
        int ww2=Earth.chkEarthSpace(swPortrait,PpieceW,(double)PpieceH/PpieceW);//~v@@@R~
        if (ww2<ww)                                                //~v@@@I~
        {                                                          //~v@@@I~
//      	ww=ww2;                                                //~v@@@I~//~0327R~
            hh=(int)((double)hh*ww2/ww);                           //~v@@@I~
        	ww=ww2;                                                //~0327I~
        }                                                          //~v@@@I~
        int ww3=chkRiverKan(PpieceW,ww,hh);                        //~0327I~
        if (ww3<ww)                                                //~0327I~
        {                                                          //~0327I~
            hh=(int)((double)hh*ww3/ww);                           //~0327I~
        	ww=ww3;                                                //~0327M~
        }                                                          //~0327I~
        riverPieceW=ww;                                            //~v@@@I~
        riverPieceH=hh;                                            //~v@@@I~
        riverLength=(riverPieceW+TBL_RIVER_SPACING_X)*riverCtrX;   //~v@@@R~
        if (Dump.Y) Dump.println("MJTables.getRiverPieceWidth ww="+ww+",hh="+hh+",len="+riverLength+",riverH="+riverH+",riverY="+riverY);//~v@@@R~
        updateGeometry();                                          //~v@@@I~
        return new Point(ww,hh);                                    //~v@@@R~
    }                                                              //~v@@@I~
	//***************************************************************//~0327I~
	//*ajust to (minkan pair size< hand 3 tiles length)            //~0327I~
	//***************************************************************//~0327I~
	private int chkRiverKan(int PhandW,int PriverW,int PriverH)    //~0327I~
    {                                                              //~0327I~
        if (Dump.Y) Dump.println("MJTables.chkRiverKan handW="+PhandW+",riverW="+PriverW+",riverH="+PriverH);//~0327I~
        double rate=3.0+(double)PriverH/PriverW;                    //~0327I~
        int lenHand=PhandW*3+PIECE_SPACING*2;		//3tiles of hand//~0327I~
        int spacing=1/*3 and 2 tile spacing*/+PAIR_SPACING/*Earth:between pairs*/;//~0328I~
        int riverW=(int)((lenHand-spacing)/rate);                 //~0327I~//~0328R~
        if (Dump.Y) Dump.println("MJTables.chkRiverKan lenHand="+lenHand+",rate="+rate+",riverW="+riverW);//~0327I~
        return riverW;                                             //~0327I~
    }                                                              //~0327I~
	//***************************************************************//~v@@@I~
	//*from pieces, adjusted river piece size by bitmap scaling    //~v@@@R~
	//***************************************************************//~v@@@I~
	public void setRiverPieceSize(int Pww,int Phh)                 //~v@@@R~
    {                                                              //~v@@@I~
        riverPieceW=Pww;                                           //~v@@@I~
        riverPieceH=Phh;                                           //~v@@@I~
        riverLength=(riverPieceW+TBL_RIVER_SPACING_X)*riverCtrX;   //~v@@@I~
        riverH=(riverPieceH+TBL_RIVER_SPACING_Y)*riverCtrY-TBL_RIVER_SPACING_Y;//~v@@@I~
        if (Dump.Y) Dump.println("MJTables.setRiverPieceSize ww="+Pww+",hh="+Phh+",len="+riverLength+",riverH="+riverH+"riverY="+riverY);//~v@@@R~
        updateGeometry();                                          //~v@@@I~
//      setStarterPos();                                           //~v@@@R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*return stockPieceWidth                                      //~v@@@R~
	//***************************************************************//~v@@@I~
	public int getStockPieceWidth()                                //~v@@@R~
    {                                                              //~v@@@I~
//      int hh=stockH/2;                                           //~v@@@R~
        int hh=stockH;                                             //~v@@@I~
    	int ww=(int)((double)hh*riverPieceW/riverPieceH);          //~v@@@R~
        int len=(ww+STOCK_SPACING_X)*STOCKCTR_EACH;                 //~v@@@R~
        int lenHorizontal=WW-(leftSpace+rightSpace+marginStockSide*2+stockH*2);//~v@@@R~
        int lenVertical=handY-(topSpace+marginStockSide*2+stockH*2);//~v@@@R~
        int minlen=Math.min(lenHorizontal,lenVertical);            //~v@@@R~
        if (len>minlen)                                            //~v@@@R~
        {                                                          //~v@@@I~
        	ww=(int)((double)minlen/STOCKCTR_EACH)-STOCK_SPACING_X; //~v@@@R~
            hh=(int)((double)ww*riverPieceH/riverPieceW);           //~v@@@I~
	        len=(ww+STOCK_SPACING_X)*STOCKCTR_EACH;                 //~v@@@R~
        }                                                          //~v@@@I~
        stockPieceW=ww;                                            //~v@@@R~
        stockPieceH=hh;                                            //~v@@@R~
        stockLength=len;                                           //~v@@@R~
        if (Dump.Y) Dump.println("MJTables.getStockPieceSize ww="+ww+",hh="+hh+",len="+stockLength);//~v@@@R~
        updateGeometry();                                          //~v@@@I~
        return ww;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*from pieces, adjusted riverpiece size by bitmap scaling     //~v@@@I~
	//***************************************************************//~v@@@I~
	public void setStockPieceSize(int Pstockww,int Pstockhh,int Pearthww,int Pearthhh)//~v@@@I~
    {                                                              //~v@@@I~
        stockPieceW=Pstockww;                                      //~v@@@I~
        stockPieceH=Pstockhh;                                      //~v@@@I~
        stockEarthPieceW=Pearthww;                                 //~v@@@I~
        stockEarthPieceH=Pearthhh;                                 //~v@@@I~
        stockLength=(stockPieceW+STOCK_SPACING_X)*STOCKCTR_EACH;   //~v@@@I~
//      stockH=stockPieceH+4;	//TODO                             //~v@@@I~//~9313R~
        stockH=stockPieceH+SHIFT_BACK;                             //~9313I~//~0322R~
        if (Dump.Y) Dump.println("MJTables.setStockPieceSize stockH="+stockH+",ww="+stockPieceW+",hh="+stockPieceH+",len="+stockLength+",stockerath w="+stockEarthPieceW+",h="+stockEarthPieceH);//~v@@@I~//~9313R~
        updateGeometry();                                          //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*return dice Piece Width                                     //~v@@@I~
	//***************************************************************//~v@@@I~
	public int getDicePieceWidth()                                 //~v@@@I~
    {                                                              //~v@@@I~
//      int ww=DICE_PIECE_WIDTH;                                   //~v@@@I~//~9808R~
//      int ww=AG.swSmallDevice ? DICE_PIECE_WIDTH_SMALL : DICE_PIECE_WIDTH;//~9808I~//~9809R~
        int ww=AG.swSmallDevice ? (int)(DICE_PIECE_WIDTH*AG.scaleSmallDevice) : DICE_PIECE_WIDTH;//~9809I~
    	dicePieceWidth=ww;                                         //~v@@@I~
        if (Dump.Y) Dump.println("MJTables.getDicePieceSize ww="+ww+",smallDevice="+AG.swSmallDevice);//~v@@@I~//~9808R~
        return ww;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	public Point getEarthLinePos()                                 //~v@@@R~
    {                                                              //~v@@@I~
    	Point p=new Point(handX,handY);                            //~v@@@R~
        return p;                                                  //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//point of right-bottom of 1st pair box by distance from wall for the player//~v@@@I~
	//***************************************************************//~v@@@I~
	public Rect[] getRectEarthPair()                                 //~v@@@I~
    {                                                              //~v@@@I~
    	Rect[] rs=new Rect[PLAYERS];                               //~v@@@R~
        rs[PLAYER_YOU]   =new Rect(WW,HH,       //pos right-bottom //~v@@@R~
//              rightSpace    +(swPortrait ? P_MARGIN_EARTH_PAIR_RIGHT  : L_MARGIN_EARTH_PAIR_RIGHT),//~v@@@R~
                rightButtonW  +(swPortrait ? P_MARGIN_EARTH_PAIR_RIGHT  : L_MARGIN_EARTH_PAIR_RIGHT),//~v@@@I~
                bottomButtonH +(swPortrait ? P_MARGIN_EARTH_PAIR_BOTTOM : L_MARGIN_EARTH_PAIR_BOTTOM));//~v@@@R~
        rs[PLAYER_RIGHT] =new Rect(HH,WW,   //pos right top        //~v@@@R~
                rightButtonW  +(swPortrait ? P_MARGIN_EARTH_PAIR_BOTTOM : L_MARGIN_EARTH_PAIR_BOTTOM),//~v@@@R~
                topSpace      +(swPortrait ? P_MARGIN_EARTH_PAIR_RIGHT  : L_MARGIN_EARTH_PAIR_RIGHT));//~v@@@R~
        rs[PLAYER_FACING] =new Rect(WW,HH,  //pos left-top         //~v@@@R~
                leftSpace     +(swPortrait ? P_MARGIN_EARTH_PAIR_RIGHT  : L_MARGIN_EARTH_PAIR_RIGHT),//~v@@@R~
//              topSpace      +(swPortrait ? P_MARGIN_EARTH_PAIR_BOTTOM : L_MARGIN_EARTH_PAIR_BOTTOM));//~v@@@R~
//              topSpace      +(swPortrait ? P_MARGIN_EARTH_PAIR_BOTTOM : L_MARGIN_EARTH_PAIR_BOTTOM)-stockH);//~v@@@I~//~9313R~
                topSpace      +(swPortrait ? P_MARGIN_EARTH_PAIR_BOTTOM : L_MARGIN_EARTH_PAIR_BOTTOM));//~9313I~
        rs[PLAYER_LEFT]   =new Rect(HH,WW,   //pos left-bottom     //~v@@@R~
                leftButtonW   +(swPortrait ? P_MARGIN_EARTH_PAIR_BOTTOM : L_MARGIN_EARTH_PAIR_BOTTOM),//~v@@@R~
                HH-handY     +(swPortrait ? P_MARGIN_EARTH_PAIR_RIGHT  : L_MARGIN_EARTH_PAIR_RIGHT ));//~v@@@R~
        if (Dump.Y) Dump.println("MJTable.getRectEarthPair YOU right="+rs[PLAYER_YOU].right+",bottom="+rs[PLAYER_YOU].bottom);//~v@@@I~
        if (Dump.Y) Dump.println("MJTable.getRectEarthPair Right right="+rs[PLAYER_RIGHT].right+",bottom="+rs[PLAYER_RIGHT].bottom);//~v@@@I~
        if (Dump.Y) Dump.println("MJTable.getRectEarthPair Facing right="+rs[PLAYER_FACING].right+",bottom="+rs[PLAYER_FACING].bottom);//~v@@@I~
        if (Dump.Y) Dump.println("MJTable.getRectEarthPair Left right="+rs[PLAYER_LEFT].right+",bottom="+rs[PLAYER_LEFT].bottom);//~v@@@I~
        return rs;                                                 //~v@@@R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	public Point getPairPieceSize()                                //~v@@@R~
    {                                                              //~v@@@I~
    	return new Point(riverPieceW,riverPieceH);                  //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	public Point getRiverPos(int Pmember)                          //~v@@@I~
    {                                                              //~v@@@I~
    	Point p;                                                   //~v@@@I~
        switch(Pmember)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_RIGHT:		//1                                //~v@@@R~
    		p=new Point(riverRightX,riverRightY);                  //~v@@@I~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:		//2                                //~v@@@R~
	    	p=new Point(riverFacingX,riverFacingY);                //~v@@@I~
            break;                                                 //~v@@@I~
        case PLAYER_LEFT:		//3                                //~v@@@R~
    		p=new Point(riverLeftX,riverLeftY);                    //~v@@@I~
            break;                                                 //~v@@@I~
        default:                    //0                            //~v@@@I~
    		p=new Point(riverX,riverY);                            //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("MJTable.getRiverPos memer="+Pmember+",x="+p.x+",y="+p.y+",riverH="+riverH+",riverY="+riverY);//~v@@@R~
        return p;                                                  //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	//*left-top point of left edge tile for each player            //~v@@@I~
	//***************************************************************//~v@@@I~
	public Point getStockPos(int Pmember)                          //~v@@@R~
    {                                                              //~v@@@I~
    	Point p;
        switch(Pmember)                                            //~v@@@I~
        {                                                          //~v@@@I~
        case PLAYER_RIGHT:		//1                                //~v@@@R~
    		p=new Point(stockRightX,stockRightY);                  //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_FACING:		//2                                //~v@@@R~
    		p=new Point(stockFacingX,stockFacingY);                //~v@@@R~
            break;                                                 //~v@@@I~
        case PLAYER_LEFT:		//3                                //~v@@@R~
    		p=new Point(stockLeftX,stockLeftY);                    //~v@@@R~
            break;                                                 //~v@@@I~
        default:                    //0                            //~v@@@I~
    		p=new Point(stockX,stockY);                            //~v@@@R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("MJTable.getStockPos memer="+Pmember+",x="+p.x+",y="+p.y);//~v@@@R~
        return p;                                                  //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
//    public Rect[] getStockRect()                                 //~v@@@R~
//    {                                                            //~v@@@R~
//        Point p;                                                 //~v@@@R~
//        int player,hh=stockH,ll=stockLength;                     //~v@@@R~
//        Rect[] rects=new Rect[PLAYERS];                          //~v@@@R~
//        //*******************                                    //~v@@@R~
//        player=PLAYER_YOU;                                       //~v@@@R~
//        p=getStockPos(player);                                   //~v@@@R~
//        rects[player]= new Rect(p.x, p.y, p.x+ll, p.y+hh);       //~v@@@R~
//        player=PLAYER_RIGHT;                                     //~v@@@R~
//        p=getStockPos(player);                                   //~v@@@R~
//        rects[player]= new Rect(p.x, p.y, p.x+hh, p.y+ll);       //~v@@@R~
//        player=PLAYER_FACING;                                    //~v@@@R~
//        p=getStockPos(player);                                   //~v@@@R~
//        rects[player]= new Rect(p.x, p.y, p.x+ll, p.y+hh);       //~v@@@R~
//        player=PLAYER_LEFT;                                      //~v@@@R~
//        p=getStockPos(player);                                   //~v@@@R~
//        rects[player]= new Rect(p.x, p.y, p.x+hh, p.y+ll);       //~v@@@R~
//        if (Dump.Y) Dump.println("MJTable.getStockRect");        //~v@@@R~
//        return rects;                                            //~v@@@R~
//    }                                                            //~v@@@R~
	//***************************************************************//~v@@@I~
	public Point getStarterPos(int Pmember)                        //~v@@@I~
    {                                                              //~v@@@I~
    	Point p;                                                   //~v@@@I~
    	p=new Point(starterX[Pmember],starterY[Pmember]);           //~v@@@R~
        return p;                                                  //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	public Point getPointStickPos(int Pmember)                     //~v@@@R~
    {                                                              //~v@@@I~
    	Point p;                                                   //~v@@@I~
    	p=new Point(coinX[Pmember],coinY[Pmember]);                //~v@@@I~
        return p;                                                  //~v@@@I~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	public Point[] getDicePos()                                    //~v@@@R~
    {                                                              //~v@@@I~
    	Point[] ps=new Point[2];                                   //~v@@@R~
    	ps[0]=new Point(diceX[0],diceY[0]);                        //~v@@@R~
    	ps[1]=new Point(diceX[1],diceY[1]);                        //~v@@@I~
        return ps;                                                 //~v@@@R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	public Rect getDiceBoxRect()                                   //~v@@@I~
    {                                                              //~v@@@I~
    	return new Rect(diceBoxX,diceBoxY,diceBoxX+diceBoxW,diceBoxY+diceBoxH);//~v@@@I~
    }                                                              //~v@@@I~
}//class MJTables                                                  //~v@@@R~
