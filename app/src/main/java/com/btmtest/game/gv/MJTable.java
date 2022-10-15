//*CID://+vas3R~: update#= 533;                                    //~vas3R~
//**********************************************************************//~v101I~
//2022/10/11 vas3 tecLast(Android12) portrait icon before move overrup on stock//~vas3I~
//2022/10/08 vard Adjust iconsize of before move not to override stock or nameplete for landscape mode//~vardI~
//2022/09/24 var8 display profile icon                             //~var8I~
//2021/09/28 vaeg enlarge nameplate for long device                //~vagdI~
//2021/09/24 vaed more adjust for small device(dip=width/dip2px<=320)//~vaedI~
//**********************************************************************//~vaedI~
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
import android.os.Build;

public class MJTable                                               //~v@@@R~
{                                                                  //~0914I~
                                                                   //~v@@@I~
    private static final float  PROFILE_HWRATE=0.75f;	//W:3 vs H:4//~var8R~
    private static final int    PROFILE_MARGIN_LANDSCAPE=4;        //~var8R~
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
    private static final int    P_MARGIN_BOTTOM_DIP       =     0;	//dip;//~vaedI~
    private static final double P_MARGIN_TOP              =     0.05;//~v@@@R~//~v@@@R~
    private static final int    P_MARGIN_TOP_DIP          =     0; //dip matgin on layout is minus//~vaedR~
//  private static final double P_MARGIN_LEFT             =     0.00;//~v@@@R~
//  private static final double P_MARGIN_RIGHT            =     0.00;//~v@@@R~
    private static final double P_MARGIN_LEFT             =     0.05;//~v@@@I~
    private static final int    P_MARGIN_LEFT_DIP         =     8;  //dip//~vaedR~
    private static final double P_MARGIN_RIGHT            =     0.05;//for openReach//~v@@@R~
    private static final int    P_MARGIN_RIGHT_DIP        =     8;  //dip//~vaedR~
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
    public Rect[] rectProfile=new Rect[PLAYERS];                   //~var8R~
    private boolean swVerticalEarth=false;                         //~9317I~
    private int marginDiceBox;                                     //~9808I~
    public  int sizeMsgBar=SIZE_MSGBAR;                             //~9811I~
    public  int shift_back;                                         //~vaedI~
//  public  ProfileIcon aProfileIcon;                              //~var8R~
//*************************                                        //~v@@@I~
//*from GameView                                                   //~vaegI~
//*************************                                        //~vaegI~
	public MJTable(int Pww,int Phh)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("MJTable.Constructor Pww="+Pww+",Phh="+Phh);         //~1506R~//~@@@@R~//~v@@@R~//~9807R~//~1918R~
        AG.aMJTable=this;                                          //~v@@@I~
//      aProfileIcon=new ProfileIcon();   //construct at MainActivity//~var8R~
        WW=Pww;	//framelayout width                                //~v@@@R~
        HH=Phh;                                                    //~v@@@R~
        init();                                                    //~v@@@I~
    }                                                              //~0914I~
	//***************************************************************//~v@@@I~
    private void init()                                            //~v@@@I~
    {                                                              //~v@@@I~
    	if (AG.swSmallDevice)                                      //~9811I~
        	sizeMsgBar*=AG.scaleSmallDevice;                       //~9811I~
    	if (AG.swSmallDip)                                         //~vaedI~
        {                                                          //~vaedI~
        	shift_back=SHIFT_BACK_SMALLDIP;      //2               //~vaedI~
        }                                                          //~vaedI~
        else                                                       //~vaedI~
        {                                                          //~vaedI~
        	shift_back=SHIFT_BACK_STD;           //6               //~vaedI~
        }                                                          //~vaedI~
        if (Dump.Y) Dump.println("MJTable.init sizeMsgBar="+sizeMsgBar+",swSmappDpi="+AG.swSmallDip+",shift_back="+shift_back);//~vaedR~
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
        if (Dump.Y) Dump.println("MJTable.init GC.btnRight="+AG.aGC.btnRightW+",rightButtonW="+rightButtonW);//~vaedI~//~vaegR~
        setGeometry();                                             //~v@@@I~
        updateGeometry();                                          //~v@@@I~
        pieces=new Pieces(this,WW,HH);                             //~v@@@M~
        pieces.init();                                             //~v@@@I~
        setStarterPos();                                           //~v@@@R~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
    private void setGeometry()                                     //~v@@@R~
    {                                                              //~v@@@I~
        int dipmargin;                                             //~vaedI~
      if (AG.swSmallDip)                                           //~vaedI~
      {                                                            //~vaedI~
        int dipMargin=P_MARGIN_BOTTOM_DIP;                         //~vaedI~
        marginBottom       =(int)(swPortrait ? dipMargin : L_MARGIN_BOTTOM*HH);//~vaedI~
      }                                                            //~vaedI~
      else                                                         //~vaedI~
        marginBottom       =(int)((swPortrait ? P_MARGIN_BOTTOM        : L_MARGIN_BOTTOM        )*HH);//~v@@@R~
      if (AG.swSmallDip)                                           //~vaedI~
      {                                                            //~vaedI~
        int dipMargin=P_MARGIN_TOP_DIP;                            //~vaedR~
        marginTop          =(int)(swPortrait ? dipMargin : L_MARGIN_TOP*HH);//~vaedR~
      }                                                            //~vaedI~
      else                                                         //~vaedI~
        marginTop          =(int)((swPortrait ? P_MARGIN_TOP           : L_MARGIN_TOP           )*HH);//~v@@@I~
      if (AG.swSmallDip)                                           //~vaedI~
      {                                                            //~vaedI~
        int dipMargin=P_MARGIN_LEFT_DIP;                           //~vaedI~
        marginLeft         =(int)(swPortrait ? dipMargin : L_MARGIN_LEFT*WW);//~vaedI~
      }                                                            //~vaedI~
      else                                                         //~vaedI~
        marginLeft         =(int)((swPortrait ? P_MARGIN_LEFT          : L_MARGIN_LEFT          )*WW);//~v@@@I~
      if (AG.swSmallDip)                                           //~vaedI~
      {                                                            //~vaedI~
        int dipMargin=P_MARGIN_RIGHT_DIP;                          //~vaedI~
        marginRight        =(int)(swPortrait ? dipMargin : L_MARGIN_RIGHT*WW);//~vaedI~
      }                                                            //~vaedI~
      else                                                         //~vaedI~
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
//        if (Build.VERSION.SDK_INT>=30)   //for gesture navigationbar//~vaegR~
//        {                                                        //~vaegR~
//            if (!AG.portrait)   //landscape                      //~vaegR~
//                leftSpace+=AG.scrNavigationbarLeftWidthA11;      //~vaegR~
//            if (Dump.Y) Dump.println("MJTable.setGeometry A11 scrNavigationbarLeftWidthA11="+AG.scrNavigationbarLeftWidthA11);//~vaegR~
//        }                                                        //~vaegR~
        rightSpace=marginRight+rightButtonW;                       //~v@@@I~
        if (Dump.Y) Dump.println("MJTable.setGeometry margin Bottom="+marginBottom+",top="+marginTop);//~v@@@I~
        if (Dump.Y) Dump.println("MJTable.setGeometry margin stockH="+stockH+",marginStockSide="+marginStockSide);//~v@@@R~
        if (Dump.Y) Dump.println("MJTable.setGeometry margin Stock top="+marginStockTop+",marginBottom="+marginStockBottom);//~v@@@I~
        if (Dump.Y) Dump.println("MJTable.setGeometry topButtonH="+topButtonH+",topSpace="+topSpace);//~v@@@R~
        if (Dump.Y) Dump.println("MJTable.setGeometry leftSpace="+leftSpace+",marginLeft="+marginLeft);//~vaedI~
        if (Dump.Y) Dump.println("MJTable.setGeometry rightSpace="+rightSpace+",margRight="+marginRight+",rightButtonW="+rightButtonW);//~vaegI~
        if (Dump.Y) Dump.println("MJTable.setGeometry marginRiver="+marginRiver+",riverH="+riverH);//~vaegI~
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
//  							+riverPieceH+marginStockTop+SHIFT_BACK;////~9313R~//~vaedR~
    							+riverPieceH+marginStockTop+shift_back;////~vaedI~
        riverFacingX       =riverX+riverLength-riverPieceW;        //~v@@@R~
        riverFacingY       =stockFacingY+stockPieceH+marginRiver+riverH-riverPieceH;  //left top of 1st tile//~v@@@R~//~9313R~
//        riverLeftX         =leftSpace+marginStockSide+stockH+marginRiver+riverH-riverPieceH;//~v@@@R~
//        riverLeftY         =topSpace+(handY-(topSpace+riverLength))/2;//~v@@@R~
//        riverRightX        =WW-(rightSpace+marginStockSide+stockH+marginRiver+riverH);//~v@@@R~
//        riverRightY        =riverLeftY+riverLength-riverPieceW;    //left top of 1st tile//~v@@@R~
//      stockLeftX         =leftSpace+marginStockSide+stockH-stockPieceH;////~v@@@R~
//      stockLeftX         =leftSpace+marginStockSide+stockH-stockPieceH+SHIFT_BACK;////~v@@@I~//~vaedR~
//      stockLeftX         =leftSpace+marginStockSide+stockH-stockPieceH+shift_back;////~vaedI~//~vaegR~
        stockLeftX         =leftSpace+marginStockSide+stockH-stockPieceH;////~vaegI~
//      stockLeftY         =topSpace+(handY-(topSpace+stockLength))/2;//top most//~v@@@R~
        stockLeftY         =stockFacingY+stockPieceH+(stockY-(stockFacingY+stockPieceH))/2-stockLength/2;//~v@@@I~
//      stockRightX        =WW-(rightSpace+marginStockSide+stockH);//~v@@@R~
//      stockRightX        =WW-(rightSpace+marginStockSide+stockH+SHIFT_BACK);//~v@@@I~//~vaedR~
//      stockRightX        =WW-(rightSpace+marginStockSide+stockH+shift_back);//~vaedI~//~vaegR~
        stockRightX        =WW-(rightSpace+marginStockSide+stockH);//~vaegI~
//      stockRightY        =stockLeftY+stockLength-stockPieceW;       //bottom most//~v@@@R~
        stockRightY        =stockFacingY+stockPieceH+(stockY-(stockFacingY+stockPieceH))/2+stockLength/2-stockPieceW;//~v@@@R~
        riverLeftX         =leftSpace+marginStockSide+stockH+marginRiver+riverH-riverPieceH;//~v@@@I~
        riverLeftY         =stockLeftY+stockLength/2-riverLength/2;//~v@@@I~
        riverRightX        =WW-(rightSpace+marginStockSide+stockH+marginRiver+riverH);//~v@@@I~
        riverRightY        =riverLeftY+riverLength-riverPieceW;    //left top of 1st tile//~v@@@I~
        setOpenRect();                                             //~v@@@I~
        setNamePlateRect();                                        //~v@@@I~
        setProfileRect();                                          //~var8I~
        if (Dump.Y) Dump.println("MJTable.updateGeometry topSpace="+topSpace+",marginStockTop="+marginStockTop+",bottom="+marginStockBottom+",stockH="+stockH+",stockPieceH="+stockPieceH);//~v@@@R~
        if (Dump.Y) Dump.println("MJTable.updateGeometry stockFacing X="+stockFacingX+",Y="+stockFacingY+",riverPieceH="+riverPieceH);//~v@@@I~//~9313R~
        if (Dump.Y) Dump.println("MJTable.updateGeometry stockFacing X="+stockFacingX+",Y="+stockFacingY+",riverPieceH="+riverPieceH);//~vaegI~
    }                                                              //~v@@@I~
	//***************************************************************//~v@@@I~
	public Rect[] getOpenRect()                                    //~v@@@I~
    {                                                              //~v@@@I~
    	Rect[] rc=openRect;                                               //~0328I~
        if (Dump.Y) Dump.println("MJTable.getOpenRect rc="+ Utils.toString(rc));//~0328I~
    	return rc;                                                 //~0328R~
    }                                                              //~v@@@I~
	//***************************************************************//~0328I~
	public Rect[] getOpenRectClone()                               //~0328I~
    {                                                              //~0328I~
    	Rect[] rc=new Rect[]{new Rect(openRect[0]),                //~0328I~
    							new Rect(openRect[1]),             //~0328I~
    							new Rect(openRect[2]),             //~0328I~
    							new Rect(openRect[3])};            //~0328I~
        if (Dump.Y) Dump.println("MJTable.getOpenRectClose rc="+ Utils.toString(rc));//~0328I~
    	return rc;                                                 //~0328I~
    }                                                              //~0328I~
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
       if (AG.swLongDevice)                                        //~vaegR~
       {                                                           //~vaegR~
		setNamePlateRectLongDevice();                              //~vaegR~
       }                                                           //~vaegR~
       else                                                        //~vaegR~
       {                                                           //~vaegR~
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
       }                                                           //~vaegR~
      }                                                            //~9317I~
    }                                                              //~v@@@I~
	//***************************************************************//~var8I~
	//*set rect for pfofile icon                                   //~var8I~
	//***************************************************************//~var8I~
	private void setProfileRect()                                  //~var8I~
    {                                                              //~var8I~
        Rect rs;                                                   //~var8I~
        int margin=PROFILE_MARGIN_LANDSCAPE,xx1,xx2,yy1,yy2,hh,ww;      //~var8R~
    //********************************************                 //~var8I~
        if (Dump.Y) Dump.println("MJTable.setProfileRect entry swPortrait="+swPortrait+",swLongDevice="+AG.swLongDevice);//~var8I~
//        if (AG.swLongDevice)                                     //~var8R~
//        {                                                        //~var8R~
//            setProfileRectLongDevice();                          //~var8R~
//        }                                                        //~var8R~
//        else                                                     //~var8R~
//        {                                                        //~var8R~
			if (swPortrait)                                        //~var8I~
            {                                                      //~var8R~
            	;	//setup rect at NamePlate                      //~var8I~
        	}                                                      //~var8I~
            else	//landscape                                    //~var8I~
            {                                                      //~var8R~
            	hh=stockPieceH*2;                                  //~var8I~
                ww=(int)(hh*PROFILE_HWRATE);	//*0.75;       //~var8I~
            //*right                                               //~var8I~
                xx2=stockRightX-margin;                             //~var8I~
                xx1=xx2-hh;                                        //~var8I~
                yy1=stockRightY;                                   //~var8R~
                yy2=yy1+ww;                                        //~var8R~
                rectProfile[PLAYER_RIGHT]=new Rect(xx1,yy1,xx2,yy2);//~var8I~
            //*facing                                              //~var8I~
                xx1=stockFacingX;                                  //~var8R~
                xx2=xx1+ww;                                        //~var8R~
                yy1=stockFacingY+stockPieceH+margin;               //~var8I~
                yy2=yy1+hh;                                        //~var8I~
                rectProfile[PLAYER_FACING]=new Rect(xx1,yy1,xx2,yy2);//~var8I~
            //*left                                                //~var8I~
                xx1=stockLeftX+stockPieceH+margin;                 //~var8I~
                xx2=xx1+hh;                                        //~var8I~
                yy2=stockLeftY+stockPieceW;                        //~var8R~
                yy1=yy2-ww;                                        //~var8R~
                rectProfile[PLAYER_LEFT]=new Rect(xx1,yy1,xx2,yy2);//~var8R~
            //*you                                                 //~var8I~
                xx2=stockX+stockPieceW;                            //~var8R~
                xx1=xx2-ww;                                        //~var8R~
                yy2=stockY-margin;                                 //~var8I~
                yy1=yy2-hh;                                        //~var8I~
                rectProfile[PLAYER_YOU]=new Rect(xx1,yy1,xx2,yy2); //~var8I~
        	}                                                      //~var8I~
//        }                                                        //~var8R~
        if (Dump.Y) Dump.println("MJTable.setProfileRect exit rect="+Utils.toString(rectProfile));//~var8I~
    }                                                              //~var8I~
	//***************************************************************//~vas3I~
	//*from ProfileIcon for portrait icon height limit before move //~vas3I~
	//*distance from stock edge to score region top                //~vas3I~
	//***************************************************************//~vas3I~
	public int getProfilePortraitLimit()                           //~vas3R~
    {                                                              //~vas3I~
        Rect r;                                                    //~vas3I~
        int margin=PROFILE_MARGIN_LANDSCAPE;                       //~vas3I~
        if (Dump.Y) Dump.println("MJTable.getProfilePortraitLimit rectScore="+Utils.toString(AG.aNamePlate.rectScore));//~vas3R~
        //*leftStock end                                           //~vas3I~
        int leftB=AG.aStock.rectsBG[PLAYER_LEFT].bottom;            //+vas3R~
        r=AG.aNamePlate.rectScore[PLAYER_YOU];                     //~vas3I~
        int max1=r.top-leftB; //above score plate                  //~vas3R~
        //*rightStock start                                        //~vas3I~
        int rightT=AG.aStock.rectsBG[PLAYER_RIGHT].top;             //+vas3I~
        r=AG.aNamePlate.rectScore[PLAYER_FACING];                  //~vas3I~
        int max2=rightT-r.bottom; //under score plate             //~vas3R~
        int rc=Math.min(max1,max2)-riverPieceW;  //from score plate to stop edge-riverWidth//~vas3R~
        if (Dump.Y) Dump.println("MJTable.getProfilePortraitLimit rectNamePlate="+Utils.toString(rectNamePlate));//~vas3R~
        if (Dump.Y) Dump.println("MJTable.getProfilePortraitLimit leftB="+leftB+",rightT="+rightT+",riverPieceW="+riverPieceW);//~vas3I~
        if (Dump.Y) Dump.println("MJTable.getProfilePortraitLimit rc="+rc+",max1="+max1+",max2="+max2);//~vas3I~
        return rc;                                                 //~vas3R~
    }                                                              //~vas3I~
	//***************************************************************//~vardI~
	//*from Profile; chk override on landscape                     //~vardI~
    //*Adjust not to override other stock or name plate            //~vardI~
	//***************************************************************//~vardI~
	public int/*adjust value for hh*/ chkProfileRect(int Phh)      //~vardI~
    {                                                              //~vardI~
        int margin=PROFILE_MARGIN_LANDSCAPE,xx1,xx2,yy1,yy2,hh;    //~vardI~
        if (Dump.Y) Dump.println("MJTable.chkProfileRect entry swPortrait="+swPortrait+",Phh="+Phh+",rect="+Utils.toString(rectProfile));//~vardI~
        Rect[] rectNP=AG.aNamePlate.rectPlate; //adjusted for landscape except for longdevice//~vardI~
        if (Dump.Y) Dump.println("MJTable.chkProfileRect nameplate="+Utils.toString(rectNamePlate)+",rectNP="+Utils.toString(rectNP));//~vardR~
		if (swPortrait)                                            //~vardI~
        	return 0;                                              //~vardI~
        int hhDecrease=0;                                          //~vardI~
        hh=Phh;                                                //~vardI~
    //*right                                                       //~vardI~
        xx2=stockRightX-margin;                                    //~vardI~
        xx1=xx2-hh;                                                //~vardI~
        int stockEndYou=stockX+stockLength+margin;                 //~vardI~
        if (stockEndYou>xx1)     //right override stock of you     //~vardI~
            hhDecrease=Math.max(hhDecrease,stockEndYou-xx1);       //~vardI~
        if (Dump.Y) Dump.println("MJTable.setProfileRect adjust right xx1="+xx1+",stockEndYou="+stockEndYou+",hhDecrease="+hhDecrease);//~vardI~
    //*left                                                        //~vardI~
        xx1=stockLeftX+stockPieceH+margin;                         //~vardI~
        xx2=xx1+hh;                                                //~vardI~
        int stockStartFacing=stockFacingX-stockLength+stockPieceW-margin;//~vardI~
        if (stockStartFacing<xx2) //left override stock of facing  //~vardI~
            hhDecrease=Math.max(hhDecrease,xx2-stockStartFacing);  //~vardI~
        if (Dump.Y) Dump.println("MJTable.setProfileRect adjust left xx2="+xx2+",stockStartFacing="+stockStartFacing+",hhDecrease="+hhDecrease);//~vardI~
    //*you                                                         //~vardI~
        yy2=stockY-margin;                                         //~vardI~
        yy1=yy2-hh;                                                //~vardI~
        int nameplateEndLeft=rectNP[PLAYER_LEFT].bottom+margin;    //~vardR~
        if (nameplateEndLeft>yy1) //you override nameplete of left //~vardI~
            hhDecrease=Math.max(hhDecrease,nameplateEndLeft-yy1);  //~vardI~
        if (Dump.Y) Dump.println("MJTable.setProfileRect adjust you yy1="+yy1+",nameplateEndLeft="+nameplateEndLeft+",hhDecrease="+hhDecrease);//~vardI~
    //*facing                                                      //~vardI~
        yy1=stockFacingY+stockPieceH+margin;                       //~vardI~
        yy2=yy1+hh;                                                //~vardI~
        int nameplateEndRight=rectNP[PLAYER_RIGHT].top-margin;     //~vardR~
        if (nameplateEndRight<yy2) //facing override nameplete of right//~vardI~
            hhDecrease=Math.max(hhDecrease,yy2-nameplateEndRight); //~vardI~
        if (Dump.Y) Dump.println("MJTable.setProfileRect adjust facing yy2="+yy2+",nameplateEndRight="+nameplateEndRight+",hhDecrease="+hhDecrease);//~vardI~
        return hhDecrease;                                         //~vardI~
    }                                                              //~vardI~
	//***************************************************************//~vaegR~
	private void setNamePlateRectLongDevice()                      //~vaegR~
    {                                                              //~vaegR~
        Rect r;                                                    //~vaegR~
        int xx1,yy1,xx2,yy2,len,margin;                            //~vaegR~
        //**********************                                   //~vaegR~
        if (AG.portrait)                                           //~vaegR~
        	len=stockX;	//from left edge to left egde of stock You //~vaegR~
        else                                                       //~vaegR~
        	len=stockLeftY;	//from left edge to left egde of stock You//~vaegR~
    	margin=P_MARGIN_LEFT_DIP;	//                             //~vaegR~
    	len-=margin;                                               //~vaegR~
        if (Dump.Y) Dump.println("MJTable.setNamePlateRectLongDevice portrait="+AG.portrait+",len="+len);//~vaegM~
//      int limitRight=handX+handLength;                           //~vaegI~
//      if (Dump.Y) Dump.println("MJTable.setNamePlateRectLongDevice limitRight="+limitRight+",handX="+handX+",handLength="+handLength);//~vaegI~
        int limitRight=handX+Hands.getLengthHands(HANDCTR_TAKEN,handPieceW);//~vaegI~
        if (Dump.Y) Dump.println("MJTable.setNamePlateRectLongDevice limitRight="+limitRight+",handX="+handX);//~vaegI~
        //*right                                                   //~vaegR~
            xx2=stockRightX+stockH;	//including shift_back         //~vaegR~
            xx1=xx2-stockPieceH;                                   //~vaegR~
            int hhName=stockPieceH;                                //~vaegI~
        	if (Dump.Y) Dump.println("MJTable.setNamePlateRectLongDevice xx1="+xx1+",xx2="+xx2+",stockPieceH="+stockPieceH);//~vaegI~
            if ((xx1-hhName)<=limitRight+1)                         //~vaegI~
            	hhName=Math.max(stockH/2,(xx2-(limitRight+1))/2);  //~vaegR~
        	if (Dump.Y) Dump.println("MJTable.setNamePlateRectLongDevice xx1="+xx1+",hhName="+hhName);//~vaegR~
            xx1=xx2-hhName;                                        //~vaegI~
	        yy1=stockRightY+stockPieceW;                           //~vaegR~
            yy2=yy1+len;                                           //~vaegR~
            r=new Rect(xx1,yy1,xx2,yy2);                           //~vaegR~
            if (Dump.Y) Dump.println("MJTable.setNamePlateRectLongDevice right="+r.toString());//~vaegR~
            rectNamePlate[PLAYER_RIGHT]=r;                         //~vaegR~
        //*facing                                                  //~vaegR~
            xx1=stockX+stockLength;                                //~vaegR~
            xx2=xx1+len;                                           //~vaegR~
            yy1=stockFacingY-shift_back;                           //~vaegR~
            yy2=yy1+hhName;                                        //~vaegR~
            r=new Rect(xx1,yy1,xx2,yy2);                           //~vaegR~
            if (Dump.Y) Dump.println("MJTable.setNamePlateRectLongDevice facing="+r.toString());//~vaegR~
            rectNamePlate[PLAYER_FACING]=r;                        //~vaegR~
        //*left                                                    //~vaegR~
            xx1=stockLeftX-shift_back;                             //~vaegR~
            xx2=xx1+hhName;                                        //~vaegR~
            yy2=stockLeftY-SHIFT_SIDE;                             //~vaegR~
            yy1=yy2-len;                                           //~vaegR~
            r=new Rect(xx1,yy1,xx2,yy2);                           //~vaegR~
            if (Dump.Y) Dump.println("MJTable.setNamePlateRectLongDevice left="+r.toString());//~vaegR~
            rectNamePlate[PLAYER_LEFT]=r;                          //~vaegR~
        //*you                                                     //~vaegR~
            xx2=stockX;                                            //~vaegR~
            xx1=xx2-len;                                           //~vaegR~
            yy2=stockY+stockH;                                     //~vaegR~
            yy1=yy2-hhName;                                        //~vaegR~
            r=new Rect(xx1,yy1,xx2,yy2);                           //~vaegR~
            rectNamePlate[PLAYER_YOU]=r;                           //~vaegR~
            if (Dump.Y) Dump.println("MJTable.setNamePlateRectLongDevice you="+r.toString());//~vaegR~
    }                                                              //~vaegR~
	//***************************************************************//~var8I~
	private void setProfileRectLongDevice()                        //~var8I~
    {                                                              //~var8I~
        Rect r;                                                    //~var8I~
        int xx1,yy1,xx2,yy2,len,margin;                            //~var8I~
        //**********************                                   //~var8I~
        if (AG.portrait)                                           //~var8I~
        	len=stockX;	//from left edge to left egde of stock You //~var8I~
        else                                                       //~var8I~
        	len=stockLeftY;	//from left edge to left egde of stock You//~var8I~
    	margin=P_MARGIN_LEFT_DIP;	//                             //~var8I~
    	len-=margin;                                               //~var8I~
        if (Dump.Y) Dump.println("MJTable.setProfileRectLongDevice portrait="+AG.portrait+",len="+len);//~var8I~
        int limitRight=handX+Hands.getLengthHands(HANDCTR_TAKEN,handPieceW);//~var8I~
        if (Dump.Y) Dump.println("MJTable.setProfileRectLongDevice limitRight="+limitRight+",handX="+handX);//~var8I~
        //*right                                                   //~var8I~
            xx2=stockRightX+stockH;	//including shift_back         //~var8I~
            xx1=xx2-stockPieceH;                                   //~var8I~
            int hhName=stockPieceH;                                //~var8I~
        	if (Dump.Y) Dump.println("MJTable.setProfileRectLongDevice xx1="+xx1+",xx2="+xx2+",stockPieceH="+stockPieceH);//~var8I~
            if ((xx1-hhName)<=limitRight+1)                        //~var8I~
            	hhName=Math.max(stockH/2,(xx2-(limitRight+1))/2);  //~var8I~
        	if (Dump.Y) Dump.println("MJTable.setProfileRectLongDevice xx1="+xx1+",hhName="+hhName);//~var8I~
            xx1=xx2-hhName;                                        //~var8I~
	        yy1=stockRightY+stockPieceW;                           //~var8I~
            yy2=yy1+len;                                           //~var8I~
            r=new Rect(xx1,yy1,xx2,yy2);                           //~var8I~
            if (Dump.Y) Dump.println("MJTable.setProfileRectLongDevice right="+r.toString());//~var8I~
            rectProfile[PLAYER_RIGHT]=r;                           //~var8I~
        //*facing                                                  //~var8I~
            xx1=stockX+stockLength;                                //~var8I~
            xx2=xx1+len;                                           //~var8I~
            yy1=stockFacingY-shift_back;                           //~var8I~
            yy2=yy1+hhName;                                        //~var8I~
            r=new Rect(xx1,yy1,xx2,yy2);                           //~var8I~
            if (Dump.Y) Dump.println("MJTable.setProfileRectLongDevice facing="+r.toString());//~var8I~
            rectProfile[PLAYER_FACING]=r;                          //~var8I~
        //*left                                                    //~var8I~
            xx1=stockLeftX-shift_back;                             //~var8I~
            xx2=xx1+hhName;                                        //~var8I~
            yy2=stockLeftY-SHIFT_SIDE;                             //~var8I~
            yy1=yy2-len;                                           //~var8I~
            r=new Rect(xx1,yy1,xx2,yy2);                           //~var8I~
            if (Dump.Y) Dump.println("MJTable.setProfileRectLongDevice left="+r.toString());//~var8I~
            rectProfile[PLAYER_LEFT]=r;                            //~var8I~
        //*you                                                     //~var8I~
            xx2=stockX;                                            //~var8I~
            xx1=xx2-len;                                           //~var8I~
            yy2=stockY+stockH;                                     //~var8I~
            yy1=yy2-hhName;                                        //~var8I~
            r=new Rect(xx1,yy1,xx2,yy2);                           //~var8I~
            rectProfile[PLAYER_YOU]=r;                             //~var8I~
            if (Dump.Y) Dump.println("MJTable.setProfileRectLongDevice you="+r.toString());//~var8I~
    }                                                              //~var8I~
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
        if (Dump.Y) Dump.println("MJTables.getEarthPieceWidth handLendth="+handLength+",ww="+ww+",hh="+hh);//~v@@@R~//~var8R~
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
//      stockH=stockPieceH+SHIFT_BACK;                             //~9313I~//~0322R~//~vaedR~
        stockH=stockPieceH+shift_back;                             //~vaedI~
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
        if (Dump.Y) Dump.println("MJTables.getEarthLinePos pos="+p.toString());//~vaegI~
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
