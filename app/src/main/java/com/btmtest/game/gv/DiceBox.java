//*CID://+vam0R~: update#= 612;                                    //~vam0R~
//**********************************************************************//~v101I~
//2022/03/24 vam0 show Wareme sign                                 //~vam0I~
//2021/09/24 vaed more adjust for small device(dip=width/dip2px<=320)//~vaedI~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//v@11 2019/02/02 TakeOne by touch                                 //~v@11I~
//v@21  imageview                                                  //~v@21I~
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
import android.graphics.RectF;
//import android.view.SurfaceHolder;                               //~v@21R~

import com.btmtest.R;
import com.btmtest.dialog.RuleSetting;
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;
import com.btmtest.TestOption;                                     //~v@11I~
import com.btmtest.utils.sound.Sound;

import static com.btmtest.game.GConst.*;                           //~v@@@R~
import static com.btmtest.game.GCMsgID.*;                          //~v@@@I~
import static com.btmtest.StaticVars.AG;                           //~v@21I~
import static com.btmtest.TestOption.*;                            //~v@11I~


public class DiceBox extends Thread                                //~v@@@R~
{                                                                  //~0914I~
                                                                   //~v@@@I~
	private static final int COLOR_BG_DISABLE=Color.argb(0xff,0x1d,0x20,0x88);//dark blue//~v@@@R~
//	private static final int COLOR_BG_ENABLE=Color.argb(0xff,0x2c,0x7c,0xff); //blue more lighter//~v@@@R~
//	private static final int COLOR_BG_ENABLE=Color.argb(0xff,0x00,0xff,0xff); //blue more lighter//~v@@@R~
//	private static final int COLOR_BG_ENABLE=Color.argb(0xff,0x1b,0xa4,0xd7); //blue more lighter//~v@@@I~//~v@21R~
  	private static final int COLOR_BG_ENABLE=Color.argb(0xff,0x00,0xbf,0xff); //deep sky blue//~v@21I~
	private static final int COLOR_BG_CASTING=Color.argb(0xff,0xff,0x45,0x00);//~v@@@M~
//  public  static final int COLOR_FG_DISABLE=Color.argb(0xa0,0x80,0x80,0x80);//~v@@@R~//~v@11R~
    public  static final int COLOR_FG_DISABLE=Color.argb(0xc0,0x80,0x80,0x80);//~v@11I~
    public  static final int COLOR_FG_DISABLE_NOP=Color.argb(0x00,0x00,0x00,0x00);//~va60I~
	private static final int COLOR_EDGE=Color.argb(0xff,0xff,0xff,0x00);//~v@@@I~
//    private static final int COLOR_LIGHT_OFF=Color.argb(0xff,0x00,0x33,0x00);//dark green//~v@@@R~
//  private static final int COLOR_LIGHT_DISABLE=Color.argb(0xff,0x1d,0x20,0x88); //dark blue//~v@@@R~//~v@21R~
    private static final int COLOR_LIGHT_DISABLE=Color.argb(0xff,0x19,0x19,0x70); //midnightblue//~v@21I~
	private static final int COLOR_LIGHT_EDGE=Color.argb(0xff,0xcc,0x99,0x33); //wood//~v@@@I~
//	private static final int COLOR_LIGHT_EDGE_STARTER=Color.argb(0xff,0xff,0x00,0x00); //red//~v@@@I~//~v@11R~
//  	private static final int COLOR_LIGHT_EDGE_STARTER=Color.argb(0xff,0xff,0x67,0x43); //red same as starter mark//~v@11R~
  	private static final int COLOR_LIGHT_EDGE_STARTER=Color.argb(0xff,0xcc,0x24,0x00); //red same as starter mark//~v@11I~
//  private static final int COLOR_LIGHT_CURRENT=Color.argb(0xff,0x00,0xff,0x00);   //green//~v@@@R~
    private static final int COLOR_LIGHT_CURRENT=Color.argb(0xff,0xff,0xff,0x00);   //yellow//~v@21R~
//  private static final int COLOR_LIGHT_WAITING_DISCARD=Color.argb(0xff,0xcc,0xff,0xcc);   //green//~v@@@R~
//  private static final int COLOR_LIGHT_WAITING_DISCARD=Color.argb(0xff,0xf8,0xee,0x00);   //green//~v@@@R~
//  private static final int COLOR_LIGHT_WAITING_DISCARD=Color.argb(0xff,0xfb,0xda,0x11);   //yellow//~v@@@R~
    private static final int COLOR_LIGHT_WAITING_DISCARD=Color.argb(0xff,0x54,0xe8,0x1c);   //green//~v@21I~
//  private static final int COLOR_LIGHT_WAITING_RESPONSE=Color.argb(0xff,0xf8,0xee,0x00);//yellow//~v@@@R~
//  private static final int COLOR_LIGHT_WAITING_RESPONSE=Color.argb(0xff,0xcc,0xff,0xcc);//water blue//~v@@@R~
    private static final int COLOR_LIGHT_WAITING_RESPONSE=Color.argb(0xff,0xac,0xff,0x91);//light green//~v@21R~
    private static final int COLOR_LIGHT_WAITING_ANYONE=Color.argb(0xff,0xac,0xff,0x91);//ligth blue//~v@@@R~
    private static final int COLOR_LIGHT_WAITING_ALL=Color.argb(0xff,0xac,0xff,0x9c);//ligth blue//~v@@@R~
//  private static final int COLOR_LIGHT_WAITING_DICE=Color.argb(0xff,0x1b,0xa4,0xd7);//ligth blue//~v@@@R~//~v@21R~
    private static final int COLOR_LIGHT_WAITING_DICE=COLOR_BG_ENABLE;//~v@21I~
//  private static final int COLOR_LIGHT_WAITING_DICE_OTHER=Color.argb(0xff,0x1a,0x6f,0x8f);//dull blue//~v@21R~
//  private static final int COLOR_LIGHT_WAITING_DICE_OTHER=Color.argb(0xff,0x72,0x62,0xa9);//dull blue//~v@21I~
//  private static final int COLOR_LIGHT_WAITING_DICE_OTHER=Color.argb(0xff,0x50,0x4d,0xcb);//dull blue//~v@21I~
//  private static final int COLOR_LIGHT_WAITING_DICE_OTHER=Color.argb(0xff,0x00,0x00,0xff);//blue//~v@21R~
//  private static final int COLOR_LIGHT_WAITING_DICE_OTHER=Color.argb(0xff,0x00,0xbf,0xff);//deep sky blue//~v@21I~
//  private static final int COLOR_LIGHT_WAITING_DICE_OTHER=Color.argb(0xff,0x1e,0x90,0xff);//dodge blue//~v@21I~
//  private static final int COLOR_LIGHT_WAITING_DICE_OTHER=Color.argb(0xff,0x46,0x82,0xb4);//dodge blue//~v@21R~
                                                                   //~v@21I~
//  private static final int COLOR_LIGHT_DISCARD                =Color.argb(0xff,0xff,0xff,0x00);   //yellow//~v@21I~//~v@11R~
//  private static final int COLOR_LIGHT_DISCARD                =Color.argb(0xff,0xff,0x33,0x66);   //orange//~v@11R~
    private static final int COLOR_LIGHT_DISCARD                =Color.argb(0xff,0xff,0x66,0x00);   //orange//~v@11I~
    private static final int COLOR_LIGHT_DISCARD_RON_TIMEOUT    =Color.argb(0xff,0xff,0xff,0x00);   //yellow//~v@11R~
    public  static final int COLOR_LIGHT_DISCARD_TIMEOUT        =Color.argb(0xff,0x54,0xe8,0x1c);   //green//~v@21I~//~v@11R~
  	private static final int COLOR_LIGHT_TAKEN                  =Color.argb(0xff,0x00,0xbf,0xff); //deep sky blue//~v@21I~
//	private static final int COLOR_WAITING_CIRCLE               =Color.argb(0xff,0xff,0x66,0x00); //orange//~v@11R~
  	private static final int COLOR_WAITING_CIRCLE               =Color.argb(0xff,0x54,0xe8,0x1c);//~v@11R~
  	private static final int COLOR_WAITING_RING                 =Color.argb(0xff,0xff,0xff,0xff);//~v@11R~
    private static final int COLOR_STARTER_CIRCLE               =Color.argb(0xff,0xcc,0x24,0x00);//~v@11R~
    private static final int COLOR_STARTER_RING                 =Color.argb(0xff,0xff,0x00,0xff);//~v@11R~//~vam0R~
                                                                   //~v@21I~
    private static final double TOUCH_ALLOWANCE=0.4;                //~v@@@I~
    private static final int MAX_SPOTS=6;                          //~v@@@I~
	private static final int DBACTION_CASTING=0;                   //~v@@@I~
	private static final int DBACTION_ENABLE=1;                    //~v@@@I~
	private static final int EDGE_WIDTH=2;                         //~v@@@I~
	private static final int LIGHT_RADIUS=20;                      //~v@@@R~
//  private static final int LIGHT_EDGE=4;                         //~v@@@R~//~vaedR~
    private static final int LIGHT_EDGE_STD=4;                     //~vaedR~
    private static final int LIGHT_EDGE_SMALLDIP=2;                //~vaedR~
	private static final int LIGHT_SWEEP_ANGLE=180;                //~v@@@I~
//  private static final int RADIUS_WAITING_CIRCLE=10;             //~v@11R~//~vaedR~
	private static final int RADIUS_WAITING_CIRCLE_STD=10;         //~vaedI~
	private static final int RADIUS_WAITING_CIRCLE_SMALLDIP=4;     //~vaedR~
    private static final int RADIUS_STARTER_CIRCLE=6;             //~v@11R~//~vaedR~
	private static final int WIDTH_WAITING_RING=2;                 //~v@11I~
    private static final int WIDTH_STARTER_RING=2;                 //~v@11R~
	private static final int DISTANCE_WAITING_CIRCLE=2;              //~v@11I~
    private static final int COLOR_SPRITPOS_BG=AG.getColor(R.color.spritposid);//~vam0R~
    private static final int COLOR_SPRITPOS_FG=0xffffffff;         //~vam0R~
//  private static final int COLOR_SPRITPOS_FRAME=0xff8080ff;      //~vam0R~
    private static final int COLOR_SPRITPOS_FRAME=COLOR_SPRITPOS_BG;//~vam0I~
//  private static final int COLOR_SPRITPOS_FG=AG.getColor(R.color.spritposid);//~vam0I~
//  private static final int COLOR_SPRITPOS_BG=0xffffff00;         //~vam0I~
//  private static final int COLOR_SPRITPOS_FRAME=COLOR_SPRITPOS_FG;//~vam0R~
    private static final int MARGIN_SPRITPOS_STARTER=2;            //~vam0I~
//    private static final int DISTANCE_STARTER_CIRCLE=2;          //~v@11R~
                                                                   //~v@@@I~
//  public static boolean swEnable=false;                          //~v@@@I~//~v@21R~
    private       boolean swEnable=false;                          //~v@21I~
//  public static DiceBox diceBox;                                         //~v@@@I~//~v@21R~
//  private static boolean isAlive;                                //~v@@@I~//~v@21R~
//  private boolean isAlive;                                       //~v@21I~//~v@11R~
    private Boolean isAlive=new Boolean(false);	//object as lockword//~v@11R~
                                                                   //~v@@@I~
    private  GCanvas gcanvas;                                      //~v@@@I~
    private Pieces pieces;//~v@@@I~
    private MJTable table;                                                 //~v@@@I~
    private Bitmap[] bmsDice;                                              //~v@@@I~
    private Bitmap[] bmsSplitPosID;                                //~vam0I~
//    private final SurfaceHolder holder;                            //~v@@@R~//~v@21R~
    private int[] sleepInterval ={ 10, 20, 50,100,300,800};    //ms//~v@@@R~//~v@11R~
//  private int[] sleepCtr      ={ 30, 10, 10,  5,  4,  1};    //ms//~v@@@R~//~v@11R~
    private int[] sleepCtr      ={ 10,  5,  5,  3,  1,  1};    //ms//~v@11R~
//  private int[] sleepInterval ={ 10,500}; //TODO                     //~v@@@I~//~v@11R~
//  private int[] sleepCtr      ={ 10,1}; //TODO                     //~v@@@R~//~v@11R~
    private int[] startAngleLight={  0,270,180, 90};               //~v@@@R~
//  private Point[] posLight;                                      //~v@@@I~//~v@11R~
    private Point[] posLightWaiting;                               //~v@11I~
    private Point[] posLightStarter;                               //~v@11R~
    private Rect[]  boxLight;                                      //~v@@@I~//~vaedR~
//  public  Rect[]  boxLight;  //TODO test                         //~vaedR~
    private RectF[]  boxLightArc;                                  //~v@@@I~
    private RectF[]  boxLightArcInner;                             //~v@@@I~
    private Canvas canvas;                                                 //~v@@@I~
    private Point[] possDice;                                              //~v@@@I~
    private Rect rectDiceBox;                                      //~v@@@R~
    private int bgColor;                                               //~v@@@I~
    private int action=DBACTION_CASTING;                           //~v@@@I~
    private int bgTable=COLOR_BG_TABLE;                            //~v@@@R~
    private int roll1,roll2=MAX_SPOTS-1;                           //~v@@@R~
    private boolean enableDiceAfter;                               //~v@@@R~
//  private boolean enableDice;                                    //~v@@@R~
    private int marginDice,marginDiceX1,marginDiceX2,marginDiceY1,marginDiceY2;//~v@@@I~
//    private boolean swRunning;                                   //~v@@@R~
    private int playerCurrent=0;                                   //~v@@@R~
    private int[] statusLight=new int[PLAYERS];                    //~v@@@I~
    private int touchAllowance;                                    //~v@@@I~
                                                                   //~v@@@I~
    private static final int LST_DISABLE=0;                        //~v@@@I~
    private static final int LST_SHADOW=0x100;                     //~v@21I~
//    private static final int LST_OFF=1;                          //~v@@@R~
    private static final int LST_CURRENT=2;                        //~v@@@I~
    private static final int LST_WAITING_DISCARD=3;                //~v@@@R~
    private static final int LST_WAITING_RESPONSE=4;
    private static final int LST_WAITING_ANYONE=5;//~v@@@I~
    private static final int LST_WAITING_ALL=6;                    //~v@@@I~
    private static final int LST_WAITING_DICE=7;                   //~v@@@R~
    private static final int LST_WAITING_DICE_STARTER=8;           //~v@@@R~
    private static final int LST_WAITING_DICE_OTHER=9;           //~v@@@R~
    private static final int LST_DISCARD           =10;            //~v@21I~
    private static final int LST_DISCARD_TIMEOUT   =11;            //~v@21I~
    private static final int LST_TAKEN             =12;            //~v@21I~
    private static final int LST_DISCARD_RON_TIMEOUT=13;
    //~v@@@I~
    private int[] spotsSetup=new int[PLAYERS];                     //~v@@@I~
    private boolean swInitDone;                                    //~v@@@I~
    private int currentStarter,radiusStarterMark=RADIUS_STARTER_CIRCLE;//~v@11R~
    private int posDrawnStarterMark=-1;                            //~v@11I~
    private int radiusLight;                                       //~v@11I~
    private int colorShadowLamp=COLOR_FG_DISABLE;                  //~va60R~
	private int LIGHT_EDGE;                                        //~vaedR~
	private int RADIUS_WAITING_CIRCLE;                             //~vaedI~
    private Point posLightStarterPrevious=null;                    //~vam0I~
    private int playerStarter=-1;                                  //~vam0I~
    private Rect recterGameSeq=null;                               //~vam0I~
    private boolean swSplitPos;                                    //~vam0I~
    // *************************                                        //~v@@@I~
	public DiceBox() //default constructor for IT Mocking          //~va60I~
	{                                                              //~va60I~
        colorShadowLamp=AG.swTrainingMode ? COLOR_FG_DISABLE_NOP : COLOR_FG_DISABLE;//~va60I~
    	if (Dump.Y) Dump.println("DiceBox default trainingMode="+AG.swTrainingMode+", colorShadowLamp="+Integer.toHexString(colorShadowLamp));//~va60I~
    }                                                              //~va60I~
	public DiceBox(GCanvas Pgcanvas)                               //~v@@@R~
    {                                                              //~0914I~
        swSplitPos= RuleSetting.isSpritPos();                       //~vam0I~
//  	diceBox=this;                                              //~v@@@I~//~v@21R~
        AG.aDiceBox=this;                                          //~v@@@I~
        gcanvas=Pgcanvas;                                          //~v@@@I~
//        holder=gcanvas.holder;                                     //~v@@@I~//~v@21R~
        table = gcanvas.table;                                       //~v@@@I~
        pieces = gcanvas.pieces;                                     //~v@@@I~
//      bmsDice = pieces.bitmapDice;                               //~v@@@R~//~v@11R~
        bmsDice = AG.bitmapDice;                                   //~v@11I~
        if (AG.swSmallDip)                                         //~vaedR~
        {                                                          //~vaedR~
			LIGHT_EDGE=LIGHT_EDGE_SMALLDIP;                        //~vaedR~
			RADIUS_WAITING_CIRCLE=RADIUS_WAITING_CIRCLE_SMALLDIP;  //~vaedI~
        }                                                          //~vaedR~
        else                                                       //~vaedR~
        {                                                          //~vaedR~
			LIGHT_EDGE=LIGHT_EDGE_STD;                             //~vaedR~
			RADIUS_WAITING_CIRCLE=RADIUS_WAITING_CIRCLE_STD;       //~vaedI~
        }                                                          //~vaedR~
        possDice =table.getDicePos();                              //~v@@@I~
		rectDiceBox=getDiceRect();                                 //~v@@@R~
        bgColor=COLOR_BG_DISABLE;	//initial                      //~v@@@R~
        radiusLight=LIGHT_RADIUS;                                  //~v@11M~
        if (AG.swSmallDevice)                                      //~v@11M~
        	radiusLight=(int)(radiusLight*AG.scaleSmallDevice);    //~v@11M~
        setLightBox();                                             //~v@@@R~
        marginDice=(possDice[0].x-rectDiceBox.left)-2;             //~v@@@R~
//      touchAllowance=(int)(LIGHT_RADIUS*2*TOUCH_ALLOWANCE);      //~v@@@I~//~v@11R~
        touchAllowance=(int)(radiusLight*2*TOUCH_ALLOWANCE);       //~v@11I~
        createBitmapSplitPosID();                                  //~vam0I~
    	if (Dump.Y) Dump.println("DiceBox constructor marginDice="+marginDice+",touchAllowance="+touchAllowance+",LIGHT_EDGE="+LIGHT_EDGE);//~v@@@R~//~va60R~
    }
    //****************************************************************************************    //~v@@@I~//~v@21R~
    //*Lamp for take/discard                                       //~v@21I~
    //*                       PLAYER_YOU                            other//~v@21I~
    //*  at Discard          DiscardColor:On                       same as PLAYER_YOU//~v@21I~
    //*  at DiscardTimeout   +NextPlayer's WaitingTake color on    +Shadowed WaitingTake:On//~v@21I~
    //*  at Take             DiscardColor:Off,TakenColor:On        DiscardColor:Off,Shadowed TakenColor:On//~v@21I~
    //****************************************************************************************//~v@21I~
    private void setLightBox()                                     //~v@@@R~
    {                                                              //~v@@@I~
//      int w=EDGE_WIDTH+1;                                        //~v@@@R~
        int x1,x2,y1,y2;                                           //~v@@@I~
//      int r=LIGHT_RADIUS;                                        //~v@@@M~//~v@11R~
        int r=radiusLight;                                         //~v@11I~
        x1=rectDiceBox.left; x2=rectDiceBox.right; y1=rectDiceBox.top; y2=rectDiceBox.bottom;//~v@@@I~
        int centerX=(x1+x2)/2;                                     //~v@@@I~
        int centerY=(y1+y2)/2;                                     //~v@@@I~
        int e=EDGE_WIDTH;       //box edge                         //~v@@@R~
        int m=LIGHT_EDGE;     //margin by edge of arc              //~v@@@R~
                                                                   //~v@@@I~
    	boxLight=new Rect[PLAYERS];                                //~v@@@I~
    	boxLightArc=new RectF[PLAYERS];                            //~v@@@I~
    	boxLightArcInner=new RectF[PLAYERS];                       //~v@@@I~
                                                                   //~v@@@I~
//        boxLight[0]=new Rect(centerX-r  ,y2+e       ,centerX+r  ,y2+e+r    );//~v@@@R~
//        boxLight[1]=new Rect(x2+e       ,centerY-r  ,x2+e+r     ,centerY+r );//~v@@@R~
//        boxLight[2]=new Rect(centerX-r  ,y1-e-r     ,centerX+r  ,y1-e      );//~v@@@R~
//        boxLight[3]=new Rect(x1-e-r     ,centerY-r  ,x1-e       ,centerY+r );//~v@@@R~
        boxLight[0]=new Rect(centerX-r-m  ,y2+e         ,centerX+r+m  ,y2+e+r+m    );//~v@@@R~
        boxLight[1]=new Rect(x2+e         ,centerY-r-m  ,x2+e+r+m     ,centerY+r+m );//~v@@@R~
        boxLight[2]=new Rect(centerX-r-m  ,y1-e-r-m     ,centerX+r+m,y1-e        );//~v@@@R~
        boxLight[3]=new Rect(x1-e-r-m      ,centerY-r-m   ,x1-e     ,centerY+r+m );//~v@@@R~
                                                                   //~v@@@I~
//        boxLightArc[0]=new RectF(boxLight[0]);                   //~v@@@R~
//        boxLightArc[1]=new RectF(boxLight[1]);                   //~v@@@R~
//        boxLightArc[2]=new RectF(boxLight[2]);                   //~v@@@R~
//        boxLightArc[3]=new RectF(boxLight[3]);                   //~v@@@R~
//                                                                 //~v@@@R~
//        boxLightArc[0].top-=r;                                   //~v@@@R~
//        boxLightArc[1].left-=r;                                  //~v@@@R~
//        boxLightArc[2].bottom+=r;                                //~v@@@R~
//        boxLightArc[3].right+=r;                                 //~v@@@R~
        boxLightArc[0]=new RectF(centerX-r    ,y2+e-r       ,centerX+r    ,y2+e+r      );//~v@@@I~
        boxLightArc[1]=new RectF(x2+e-r       ,centerY-r    ,x2+e+r       ,centerY+r   );//~v@@@I~
        boxLightArc[2]=new RectF(centerX-r    ,y1-e-r       ,centerX+r  ,y1-e+r      );//~v@@@I~
        boxLightArc[3]=new RectF(x1-e-r        ,centerY-r     ,x1-e+r   ,centerY+r   );//~v@@@I~
        r-=m;                                                      //~v@@@I~
        boxLightArcInner[0]=new RectF(centerX-r    ,y2+e-r       ,centerX+r    ,y2+e+r      );//~v@@@I~
        boxLightArcInner[1]=new RectF(x2+e-r       ,centerY-r    ,x2+e+r       ,centerY+r   );//~v@@@I~
        boxLightArcInner[2]=new RectF(centerX-r    ,y1-e-r       ,centerX+r  ,y1-e+r      );//~v@@@I~
        boxLightArcInner[3]=new RectF(x1-e-r        ,centerY-r     ,x1-e+r   ,centerY+r   );//~v@@@I~
    //*for WaitingCircle                                           //~v@11I~
    	int d=RADIUS_WAITING_CIRCLE+DISTANCE_WAITING_CIRCLE;       //~v@11I~
        posLightWaiting=new Point[PLAYERS];                         //~v@11I~
      	if (AG.swSmallDip)                                         //~vaedI~
    		d=RADIUS_WAITING_CIRCLE;                               //~vaedI~
        posLightWaiting[0]=new Point(boxLight[0].right+d,boxLight[0].top+d);//~v@11I~
        posLightWaiting[1]=new Point(boxLight[1].left+d,boxLight[1].top-d);//~v@11R~
        posLightWaiting[2]=new Point(boxLight[2].left-d,boxLight[2].bottom-d);//~v@11R~
        posLightWaiting[3]=new Point(boxLight[3].right-d,boxLight[3].bottom+d);//~v@11R~
        if (Dump.Y) Dump.println("DiceBox.getLightRect d="+d+",m="+m);//~va60I~
        if (Dump.Y) Dump.println("DiceBox.getLightRect posLightWaiting[0]="+posLightWaiting[0]);//~va60I~
        if (Dump.Y) Dump.println("DiceBox.getLightRect posLightWaiting[1]="+posLightWaiting[1]);//~va60I~
        if (Dump.Y) Dump.println("DiceBox.getLightRect posLightWaiting[2]="+posLightWaiting[2]);//~va60I~
        if (Dump.Y) Dump.println("DiceBox.getLightRect posLightWaiting[3]="+posLightWaiting[3]);//~va60I~
                                                                   //~v@11I~
//        int ds=RADIUS_STARTER_CIRCLE+DISTANCE_STARTER_CIRCLE;    //~v@11R~
//      posLightStarter=new Point[PLAYERS];                        //~v@11R~
//      posLightStarter[0]=new Point(boxLight[0].left-d,boxLight[0].top+d);//~v@11R~
//      posLightStarter[1]=new Point(boxLight[1].left+d,boxLight[1].bottom+d);//~v@11R~
//      posLightStarter[2]=new Point(boxLight[2].right+d,boxLight[2].bottom-d);//~v@11R~
//      posLightStarter[3]=new Point(boxLight[3].right-d,boxLight[3].top-d);//~v@11R~
        setPosLightStarter();                                      //~v@11I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@11I~
    private void setPosLightStarter()                                   //~v@11I~
    {                                                              //~v@11I~
        posLightStarter=new Point[PLAYERS];                        //~v@11I~
//      Bitmap[][] bmss=pieces.bitmapStarter;                      //~v@11R~
        Bitmap[][] bmss=AG.bitmapStarter;                          //~v@11I~
	    Bitmap bm=bmss[0][0];                                      //~v@11I~
//      radiusStarterMark=Math.min(bm.getWidth(),bm.getHeight())/3; 	 //2/3//~v@11R~
        radiusStarterMark=Math.min(bm.getWidth(),bm.getHeight())*9/20;    //9/10//~v@11R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@11I~
        {                                                          //~v@11I~
	        bm=bmss[0/*ESWN_E*/][ii];                              //~v@11R~
        	Point p=table.getStarterPos(ii);                       //~v@11I~
        	Point center=new Point(p.x+bm.getWidth()/2,p.y+bm.getHeight()/2);//~v@11I~
            posLightStarter[ii]=center;                            //~v@11I~
        }                                                          //~v@11I~
        if (Dump.Y) Dump.println("DiceBox.setPosLightStarter posLightStarter="+Utils.toString(posLightStarter));//~vam0I~
    }                                                              //~v@11I~
    //*********************************************************    //~v@11I~
    public Rect[] getLightRect()                                   //~v@11I~
    {                                                              //~v@11I~
        Rect[] rs=boxLight;                                        //~v@11I~
        if (Dump.Y) Dump.println("DiceBox.getLightRect 0="+rs[0].toString()+",1="+rs[1].toString()+",2="+rs[2].toString()+",3="+rs[3].toString());//~v@11I~
        return rs;                                                 //~v@11I~
    }                                                              //~v@11I~
    //*********************************************************    //~v@@@I~
//  private void drawLight(boolean Pon,boolean Plock)              //~v@@@R~
//  public void drawLight(Canvas Pcanvas,boolean Pon)              //~v@@@R~
    private void drawLightInit(Canvas Pcanvas)                     //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.drawLightInit swInitDone="+swInitDone);//~v@@@I~
    	if (swInitDone)	                                           //~v@@@I~
        	return;                                                //~v@@@I~
    	swInitDone=true;                                           //~v@@@I~
		int colorLight=COLOR_LIGHT_DISABLE;                        //~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
//          drawLight(boxLight[ii],boxLightArc[ii],startAngleLight[ii],Pon,Plock);//~v@@@R~
            drawLight(Pcanvas,boxLight[ii],boxLightArc[ii],boxLightArcInner[ii],startAngleLight[ii],colorLight);//~v@@@R~
	        statusLight[ii]=LST_DISABLE;                           //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*from Gcanvas                                                //~v@@@I~
    //*********************************************************    //~v@@@I~
////  private void drawLight(Rect Pbox, RectF Pboxarc,int Pstart,boolean Pon, boolean Pswlock)//~v@@@R~
//    private void drawLight(Canvas Pcanvas,Rect Pbox, RectF Pboxarc,int Pstart,boolean Pon)//~v@@@R~
//    {                                                            //~v@@@R~
//        canvas=Pcanvas;                                          //~v@@@R~
//        if (canvas==null)                                        //~v@@@R~
//            canvas=Graphics.lockCanvas(Pbox);                    //~v@@@R~
////      swEnable=true;  //TODO                                   //~v@@@R~
////      int colorLight=swEnable?COLOR_LIGHT_CURRENT:COLOR_LIGHT_OFF;    //initial//~v@@@R~
//        int colorLight=COLOR_LIGHT_DISABLE; //initial            //~v@@@R~
////        synchronized(holder)                                   //~v@@@R~
////        {                                                      //~v@@@R~
////            drawBox(canvas,Pbox,bgTable);                      //~v@@@R~
////            Graphics.drawArc(canvas,Pboxarc,Pstart,LIGHT_SWEEP_ANGLE,COLOR_LIGHT_EDGE,LIGHT_EDGE);//~v@@@R~
////            int w=LIGHT_EDGE;                                  //~v@@@R~
////            RectF b=Pboxarc;                                   //~v@@@R~
////            RectF inner=new RectF(b.left+w,b.top+w,b.right-w,b.bottom-w);//~v@@@R~
////            Graphics.drawArc(canvas,inner,Pstart,LIGHT_SWEEP_ANGLE,colorLight);//~v@@@R~
////        }                                                      //~v@@@R~
//        drawLight(canvas,Pbox,Pboxarc,Pstart,colorLight);        //~v@@@R~
//        if (Pcanvas==null)                                       //~v@@@R~
//            Graphics.unlockCanvas(canvas);                       //~v@@@R~
//    }                                                            //~v@@@R~
    //*********************************************************    //~v@@@I~
    //*under locked                                                //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void drawLight(Canvas Pcanvas,Rect Pbox, RectF Pboxarc,RectF PrectInnerArc,int Pstart,int Pcolor)//~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.DrawLight drawRingArc color="+Integer.toHexString(Pcolor));//~v@11I~
		Graphics.drawRingArc(Pcanvas,Pbox,Pboxarc,PrectInnerArc,Pstart,LIGHT_SWEEP_ANGLE,LIGHT_EDGE,COLOR_LIGHT_EDGE,Pcolor);//~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void drawLight(Rect Pbox, RectF Pboxarc,RectF PboxarcInner,int Pstart,int Pstat)//~v@@@R~//~v@21R~
    {                                                              //~v@@@I~
	    drawLight(Pbox,Pboxarc,PboxarcInner,Pstart,Pstat,(long)0/*PcolorOpaque*/);//~v@21R~
    }                                                              //~v@21I~
    //*********************************************************    //~v@21I~
    private void drawLight(Rect Pbox, RectF Pboxarc,RectF PboxarcInner,int Pstart,int Pstat,long PcolorOpaque)//~v@21R~
    {                                                              //~v@21I~
//      int colorLight,colorEdge=COLOR_LIGHT_EDGE;                 //~v@@@R~
        int colorLight,colorEdge=0;                                //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.DrawLight stat="+Pstat+",opaque="+PcolorOpaque); //~v@@@I~//~v@11R~
//        RectF inner;    //inner arc                              //~v@@@R~
//        switch(Pstart)                                           //~v@@@R~
//        {                                                        //~v@@@R~
//        case 270:   //right                                      //~v@@@R~
//            inner=new RectF(Pboxarc.left           ,Pboxarc.top+LIGHT_EDGE,Pboxarc.right-LIGHT_EDGE,Pboxarc.bottom-LIGHT_EDGE);//~v@@@R~
//            break;                                               //~v@@@R~
//        case 180:   //facing                                     //~v@@@R~
//            inner=new RectF(Pboxarc.left+LIGHT_EDGE,Pboxarc.top           ,Pboxarc.right-LIGHT_EDGE,Pboxarc.bottom-LIGHT_EDGE);//~v@@@R~
//            break;                                               //~v@@@R~
//        case 90:    //left                                       //~v@@@R~
//            inner=new RectF(Pboxarc.left+LIGHT_EDGE,Pboxarc.top+LIGHT_EDGE,Pboxarc.right           ,Pboxarc.bottom-LIGHT_EDGE);//~v@@@R~
//            break;                                               //~v@@@R~
//        default:    //                                           //~v@@@R~
//            inner=new RectF(Pboxarc.left+LIGHT_EDGE,Pboxarc.top+LIGHT_EDGE,Pboxarc.right-LIGHT_EDGE,Pboxarc.bottom           );//~v@@@R~
//        }                                                        //~v@@@R~
        switch(Pstat)                                              //~v@@@I~
        {                                                          //~v@@@I~
//        case LST_OFF:                                            //~v@@@R~
//            colorLight=COLOR_LIGHT_OFF;                          //~v@@@R~
//            break;                                               //~v@@@R~
        case LST_CURRENT:                                          //~v@@@I~
            colorLight=COLOR_LIGHT_CURRENT;                        //~v@@@R~
        	break;                                                 //~v@@@I~
        case LST_DISCARD:                                           //~v@21I~
            colorLight=COLOR_LIGHT_DISCARD;                        //~v@21I~
        	break;                                                 //~v@21I~
        case LST_DISCARD_TIMEOUT:                                   //~v@21I~
            colorLight=COLOR_LIGHT_DISCARD_TIMEOUT;                //~v@21I~
        	break;                                                 //~v@21I~
        case LST_DISCARD_RON_TIMEOUT:                              //~v@11I~
            colorLight=COLOR_LIGHT_DISCARD_RON_TIMEOUT;            //~v@11I~
        	break;                                                 //~v@11I~
        case LST_TAKEN  :                                          //~v@21I~
            colorLight=COLOR_LIGHT_TAKEN;                          //~v@21I~
        	break;                                                 //~v@21I~
        case LST_WAITING_DISCARD:                                  //~v@@@I~
            colorLight=COLOR_LIGHT_WAITING_DISCARD;                //~v@@@I~
        	break;                                                 //~v@@@I~
        case LST_WAITING_RESPONSE:                                 //~v@@@I~
            colorLight=COLOR_LIGHT_WAITING_RESPONSE;               //~v@@@I~
        	break;                                                 //~v@@@M~
        case LST_WAITING_ANYONE:                                   //~v@@@I~
            colorLight=COLOR_LIGHT_WAITING_ANYONE;                 //~v@@@I~
        	break;                                                 //~v@@@I~
        case LST_WAITING_ALL:                                      //~v@@@I~
            colorLight=COLOR_LIGHT_WAITING_ALL;                    //~v@@@I~
        	break;                                                 //~v@@@I~
        case LST_WAITING_DICE:                                     //~v@@@R~
            colorLight=COLOR_LIGHT_WAITING_DICE;                   //~v@@@R~
        	break;                                                 //~v@@@I~
//        case LST_WAITING_DICE_OTHER:    //waitiong other member action//~v@21R~
//            colorLight=COLOR_LIGHT_WAITING_DICE_OTHER;           //~v@21R~
//            break;                                               //~v@21R~
        case LST_WAITING_DICE_STARTER:                             //~v@@@R~
            colorLight=COLOR_LIGHT_WAITING_DICE;                   //~v@@@R~
            colorEdge=COLOR_LIGHT_EDGE_STARTER;                    //~v@@@I~
        	break;                                                 //~v@@@I~
        default:                                                   //~v@@@I~
            colorLight=COLOR_LIGHT_DISABLE;                        //~v@@@R~
        }                                                          //~v@@@I~
        if (colorEdge==0)                                          //~v@@@I~
        {                                                          //~v@21I~
        	if (PcolorOpaque==0)                                   //~v@21R~
    			Graphics.drawArc(Pbox,PboxarcInner,Pstart,LIGHT_SWEEP_ANGLE,colorLight);//~v@@@I~//~v@21R~
            else                                                   //~v@21I~
    			Graphics.drawArc(Pbox,PboxarcInner,Pstart,LIGHT_SWEEP_ANGLE,colorLight,PcolorOpaque);//~v@21I~
        }                                                          //~v@21I~
        else                                                       //~v@@@I~
    		Graphics.drawRingArc(Pbox,Pboxarc,PboxarcInner,Pstart,LIGHT_SWEEP_ANGLE,LIGHT_EDGE,colorEdge,colorLight);//~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@11I~
    public  void setWaiterLight(int Pplayer,boolean PswOn)         //~v@11I~
    {                                                              //~v@11I~
        Point p=posLightWaiting[Pplayer];
		int r=RADIUS_WAITING_CIRCLE;                               //~v@11I~
        if (Dump.Y) Dump.println("DiceBox.setWaiterLight swOn="+PswOn+",player="+Pplayer+",pos="+p.toString()+",radius="+r);//~v@11I~//~va60I~
        if (PswOn)                                                 //~v@11I~
        {                                                          //~v@11I~
			Graphics.drawCircle(p,r,COLOR_WAITING_CIRCLE);         //~v@11R~
			Graphics.drawCircle(p,r-WIDTH_WAITING_RING,COLOR_WAITING_RING,WIDTH_WAITING_RING);//~v@11R~
        }                                                          //~v@11I~
        else                                                       //~v@11I~
        {                                                          //~v@11I~
			Graphics.drawCircle(p,r,COLOR_BG_TABLE);               //~v@11R~
        }                                                          //~v@11I~
    }                                                              //~v@11I~
    //*********************************************************    //~v@11R~
    //*Starter.java draw currentStartermark *************************************//~v@11R~
    //*********************************************************    //~v@11R~
//    public void drawCurrentStarterMark(int PgameSeq/*0,1,2,3*/)  //~v@11R~
//    {                                                            //~v@11R~
//        if (Dump.Y) Dump.println("DiceBox.drawCurrentStartermark gameSeq="+PgameSeq);//~v@11R~
//        int player=AG.aAccounts.getCurrentStarterPos();          //~v@11R~
//        int prevplayer=Players.prevPlayer(player);               //~v@11R~
//        int r=RADIUS_STARTER_CIRCLE;                             //~v@11R~
//        Point p=posLightStarter[prevplayer];                     //~v@11R~
//        Graphics.drawCircle(p,r,COLOR_BG_TABLE);                 //~v@11R~
//        Point ps=posLightStarter[player];                        //~v@11R~
//        Graphics.drawCircle(ps,r,COLOR_STARTER_CIRCLE);          //~v@11R~
////      Graphics.drawCircle(ps,r-WIDTH_STARTER_RING,COLOR_STARTER_RING,WIDTH_STARTER_RING);//~v@11R~
//        Graphics.drawCircle(ps,r-WIDTH_STARTER_RING,COLOR_WAITING_RING,WIDTH_STARTER_RING);//~v@11R~
//    }                                                            //~v@11R~
    //*********************************************************    //~v@11I~
    public void drawCurrentStarterMark(int Pplayer,boolean PswErase)//~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("DiceBox.drawCurrentStarterMark player="+Pplayer+",swErase="+PswErase);//~v@11I~//~vam0R~
//      int r=RADIUS_STARTER_CIRCLE;                               //~v@11R~
        int r=radiusStarterMark;                                     //~v@11I~
        Point p=posLightStarter[Pplayer];                          //~v@11I~
        if (PswErase)                                              //~v@11I~
	        Graphics.drawCircle(p,r,COLOR_BG_TABLE);               //~v@11I~
        else                                                       //~v@11I~
        {                                                          //~v@11I~
        	Graphics.drawCircle(p,r,COLOR_STARTER_CIRCLE);        //~v@11I~
        	Graphics.drawCircle(p,r-WIDTH_STARTER_RING,COLOR_WAITING_RING,WIDTH_STARTER_RING);//~v@11I~
        }                                                          //~v@11I~
    }                                                              //~v@11I~
    //*********************************************************    //~v@21I~
    private void drawLight(boolean PswShadow,int Pplayer,int Pstat)//~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.drawLight swShadow="+PswShadow+",stat="+Pstat+",player="+Pplayer);//~v@21I~
        if (PswShadow)                                             //~v@21I~
			drawLightShadow(Pplayer,Pstat);                        //~v@21I~
        else                                                       //~v@21I~
			drawLight(Pplayer,Pstat);                              //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~v@@@I~
    private void drawLight(int Pplayer,int Pstat)                  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.drawLight stat="+Pstat+",player="+Pplayer+",boxLight="+boxLight[Pplayer].toString());//~v@@@R~//~v@11R~//~va60R~
//      drawLight(boxLight[Pplayer],boxLightArc[Pplayer],startAngleLight[Pplayer],Pstat);//~v@@@R~
        drawLight(boxLight[Pplayer],boxLightArc[Pplayer],boxLightArcInner[Pplayer],startAngleLight[Pplayer],Pstat);//~v@@@I~
        statusLight[Pplayer]=Pstat;                                //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@21I~
    private void drawLightShadow(int Pplayer,int Pstat)            //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.drawLightShadow stat="+Pstat+",player="+Pplayer+",colorShadowLamp="+Integer.toHexString(colorShadowLamp));//~va60R~
//      drawLight(boxLight[Pplayer],boxLightArc[Pplayer],startAngleLight[Pplayer],Pstat);//~v@21I~
//      drawLight(boxLight[Pplayer],boxLightArc[Pplayer],boxLightArcInner[Pplayer],startAngleLight[Pplayer],Pstat,(long)COLOR_FG_DISABLE);//~v@21R~//~v@11R~
//      drawLight(boxLight[Pplayer],boxLightArc[Pplayer],boxLightArcInner[Pplayer],startAngleLight[Pplayer],Pstat); //no shadow, look at PLAYER_YOU of each//~v@11R~
//      drawLight(boxLight[Pplayer],boxLightArc[Pplayer],boxLightArcInner[Pplayer],startAngleLight[Pplayer],Pstat,(long)COLOR_FG_DISABLE);//~v@11I~//~va60R~
        drawLight(boxLight[Pplayer],boxLightArc[Pplayer],boxLightArcInner[Pplayer],startAngleLight[Pplayer],Pstat,(long)colorShadowLamp);//~va60I~
        statusLight[Pplayer]=Pstat|LST_SHADOW;                     //~v@21R~
    }                                                              //~v@21I~
    //*********************************************************    //~v@@@I~
    private void drawLightEdge(int Pplayer,int Pcolor)             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.drawLightEdge player="+Pplayer+",color="+Integer.toHexString(Pcolor));//~v@@@I~//~va60R~
        Graphics.drawArc(boxLight[Pplayer],boxLightArc[Pplayer],startAngleLight[Pplayer],LIGHT_SWEEP_ANGLE,Pcolor,LIGHT_EDGE);//~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@21I~
    public  void resetLightAll()                                   //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.resetLightAll");         //~v@21I~
    	resetLight(LST_DISABLE,-1);                                //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~v@@@I~
    private void resetLight(int Pstatus,int Pplayer)               //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.resetLight Pplayer="+Pplayer+",stat="+Pstatus);//~v@@@R~//~v@21R~//~v@11R~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
        	if (ii!=Pplayer)                                       //~v@@@I~
            {                                                      //~v@11I~
                if (Dump.Y) Dump.println("DiceBox.resetLight player="+ii+",status="+Pstatus+",oldstatus="+statusLight[ii]);//~v@@@R~//~v@11I~
                if (statusLight[ii]!=Pstatus)                      //~v@@@R~
                {                                                  //~v@@@R~
                    drawLight(ii,Pstatus);                         //~v@@@R~
                }                                                  //~v@@@R~
            }                                                      //~v@11I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void resetLight(int Pplayer)                            //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.resetLight player="+Pplayer);//~v@@@I~//~v@21R~
        drawLight(Pplayer,LST_DISABLE);                                 //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void drawLightCurrent(int Pplayer)                      //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.drawLightCurrent player="+Pplayer+",oldCurrent="+playerCurrent);//~v@@@I~//~v@21R~
	    drawLightCurrent(Pplayer,false/*PswShadow*/);              //~v@21I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@21I~
    public void drawLightCurrent(int Pplayer,boolean PswShadow)    //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.DrawLight player="+Pplayer+",oldCurrent="+playerCurrent+",swShadow="+PswShadow);//~v@21I~
    	resetLight(LST_DISABLE,Pplayer);                           //~v@21I~
        drawLight(PswShadow,Pplayer,LST_CURRENT);                  //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~v@21I~
    //light on until next player taken                             //~v@21I~
    //*********************************************************    //~v@21I~
    public void drawLightDiscard(int Pplayer)                      //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.DrawLightDiscard player="+Pplayer);//~v@21I~
    	resetLight(LST_DISABLE,Pplayer);                           //~v@21I~
        drawLight(false/*PswShadow*/,Pplayer,LST_DISCARD);         //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~v@21I~
    //discard timeout, light next player ON                        //~v@21I~
    //leave discarded player light ON                              //~v@21I~
    //*********************************************************    //~v@21I~
    public void drawLightDiscardTimeout(int Pplayer,boolean PswShadow)//~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.DrawLightDiscardTimeout player="+Pplayer+",swShadow="+PswShadow);//~v@21I~
        int next=Players.nextPlayer(Pplayer);                           //~v@21I~
        drawLight(PswShadow,next,LST_DISCARD_TIMEOUT);             //~v@21I~
        if (!PswShadow)                                            //~v@11I~
	        AG.aStock.enableNextMark();                            //~v@11I~
    }                                                              //~v@21I~
    //*********************************************************    //~0403I~
    public void drawLightKanTakable(int Pplayer,boolean PswShadow) //~0403I~
    {                                                              //~0403I~
        if (Dump.Y) Dump.println("DiceBox.DrawLightKanTakable player="+Pplayer+",swShadow="+PswShadow);//~0403I~
    	resetLight(LST_DISABLE,Pplayer);                           //~0403I~
        drawLight(PswShadow,Pplayer,LST_DISCARD_TIMEOUT);          //~0403I~
//        if (!PswShadow)                                          //~0403I~
//            AG.aStock.enableNextMark();                          //~0403I~
    }                                                              //~0403I~
    //*********************************************************    //~v@11I~
    //discard timeout, ron time up to ponkan time                  //~v@11I~
    //*********************************************************    //~v@11I~
    public void drawLightDiscardRonTimeout(int Pplayer)            //~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("DiceBox.DrawLightDiscardRonTimeout player="+Pplayer);//~v@11I~
        drawLight(false,Pplayer,LST_DISCARD_RON_TIMEOUT);      //~v@11I~
    }                                                              //~v@11I~
    //*********************************************************    //~v@21I~
    //taken light on current taken                                 //~v@21I~
    //*********************************************************    //~v@21I~
    public void drawLightTakeOne(int Pplayer,boolean PswShadow)    //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.DrawLightTakeOne player="+Pplayer+",swShadow="+PswShadow);//~v@21R~
    	resetLight(LST_DISABLE,Pplayer); //clear all               //~v@21I~
        drawLight(PswShadow,Pplayer,LST_TAKEN);                       //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~v@@@I~
    public void setWaitingDiscard(int Pplayer)                     //~v@@@R~
    {                                                              //~v@@@I~
	    setWaitingDiscard(Pplayer,false/*PswShadow*/);             //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~v@21I~
    public void setWaitingDiscard(int Pplayer,boolean PswShadow)   //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.setWaitingDiscard player="+Pplayer);//~v@@@R~
    	resetLight(LST_DISABLE,Pplayer);                           //~v@@@R~
//      drawLight(Pplayer,LST_WAITING_DISCARD);                    //~v@@@R~//~v@21R~
        drawLight(PswShadow,Pplayer,LST_WAITING_DISCARD);          //~v@21I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*for also by mapDummy                                        //~v@11I~
    //*********************************************************    //~v@11I~
    public void setWaitingDice(int Pplayer)                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.setWaitingDice player="+Pplayer);//~v@@@I~
        DiceBox.enable(true);                                      //~v@@@M~
    	resetLight(LST_DISABLE,Pplayer);                           //~v@@@I~
        drawLight(Pplayer,LST_WAITING_DICE);                       //~v@@@I~
        if ((TestOption.option & TO_SKIPDICE)!=0)                  //~v@11I~
        	AG.aACAction.touchDice();                              //~v@11I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@21I~
    public void setWaitingDiceOther(int Pplayer)                   //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.setWaitingDiceOther player="+Pplayer);//~v@21I~
        DiceBox.enable(false);                                     //~v@21I~
    	resetLight(LST_DISABLE,Pplayer);                           //~v@21I~
//      drawLight(Pplayer,LST_WAITING_DICE_OTHER);                 //~v@21R~
        drawLightShadow(Pplayer,LST_WAITING_DICE);                 //~v@21I~
//      if ((TestOption.option & TO_SKIPDICE)!=0)                  //~v@11R~
//      	AG.aACAction.touchDice();                              //~v@11R~
    }                                                              //~v@21I~
    //*********************************************************    //~v@@@I~
    public void setWaitingDice(int Pplayer,boolean PswStarter)     //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.setWaitingDice Pplayer="+Pplayer+",swstarter="+PswStarter+",currentStarter="+currentStarter);//~v@@@R~//~v@11R~
        setWaitingDice(Pplayer);                                   //~v@@@I~
//      if (Pplayer!=currentStarter)                               //~v@@@I~//~v@11R~
//      {                                                          //~v@@@I~//~v@11R~
//      	drawLightEdge(currentStarter,COLOR_LIGHT_EDGE);        //~v@@@R~//~v@11R~
//      	drawLight(Pplayer,LST_WAITING_DICE_STARTER);           //~v@@@R~//~v@11R~
        	drawLight(Pplayer,LST_WAITING_DICE);                   //~v@11I~
        	drawLightEdge(Pplayer,COLOR_LIGHT_EDGE_STARTER);       //~v@11R~
	        currentStarter=Pplayer;                                //~v@@@I~
//      }                                                          //~v@@@I~//~v@11R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@11I~
    public void setLightStarter(int Pplayer,boolean PswResetPrev)          //~v@11I~
    {                                                              //~v@11I~
        if (Dump.Y) Dump.println("DiceBox.setLightStarter Pplayer="+Pplayer+",swResetPrev="+PswResetPrev+",currentStarter="+currentStarter+",posDrawnStarterMark="+posDrawnStarterMark);//~va60R~
    	clearSplitPosID();                                         //~vam0I~
//      if (PswResetPrev)                                          //~v@11R~
        if (PswResetPrev && posDrawnStarterMark>=0)                //~v@11R~
        {                                                          //~v@11R~
//          int prev=Players.prevPlayer(Pplayer);                  //~v@11R~
            int prev=posDrawnStarterMark;                          //~v@11I~
            posDrawnStarterMark=-1;                                //~v@11I~
//          drawLightEdge(prev,COLOR_LIGHT_EDGE);                  //~v@11R~
            drawCurrentStarterMark(prev,true/*swErase*/);          //~v@11I~
        }                                                          //~v@11R~
//      setWaitingDice(Pplayer);                                   //~v@11R~
//      drawLightEdge(Pplayer,COLOR_LIGHT_EDGE_STARTER);           //~v@11R~
        if (Dump.Y) Dump.println("DiceBox.setLightStarter starterRelativePos="+AG.aAccounts.starterRelativePos);//~va60I~
	  if (Pplayer==AG.aAccounts.starterRelativePos)	//1st starter pos//~v@11I~
         posDrawnStarterMark=-1;                                   //~v@11I~
      else                                                         //~v@11I~
      {                                                            //~v@11I~
        posDrawnStarterMark=Pplayer;                               //~v@11I~
        drawCurrentStarterMark(Pplayer,false/*swErase*/);          //~v@11I~
      }                                                            //~v@11I~
	    currentStarter=Pplayer;                                    //~v@11I~
        AG.aStarter.drawCurrentStarter(Pplayer);                   //~v@11I~
    }                                                              //~v@11I~
    //*********************************************************    //~v@@@I~
    public void setWaitingResponseAnyone()                         //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.setWaitingResponse");    //~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
            drawLight(ii,LST_WAITING_ANYONE);                      //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void setWaitingResponseAll()                            //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.setWaitingResponseAll"); //~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
            drawLight(ii,LST_WAITING_ALL);                         //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//    //*********************************************************  //~v@@@R~
//    public void setWaitingAction(int Pplayer)                    //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("DiceBox.setWaitingAction Pplayer="+Pplayer);//~v@@@R~
//        resetLight(LST_DISABLE,Pplayer);                         //~v@@@R~
//        drawLight(Pplayer,LST_WAITING_DICE);                     //~v@@@R~
//    }                                                            //~v@@@R~
//    //*********************************************************  //~v@@@R~
//    public void setWaitingAction(int Pplayer,boolean PswStarter) //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("DiceBox.setWaitingAction Pplayer="+Pplayer);//~v@@@R~
//        resetLight(LST_DISABLE,Pplayer);                         //~v@@@R~
//        drawLight(Pplayer,LST_WAITING_DICE_STARTER);             //~v@@@R~
//    }                                                            //~v@@@R~
    //*********************************************************    //~v@@@I~
    public void resetAll()                                         //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.resetAll");              //~v@@@I~
    	for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
            if (statusLight[ii]!=LST_DISABLE)                      //~v@@@I~
	            drawLight(ii,LST_DISABLE);                         //~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void setWaitingResponse(int Pplayer)                    //~v@@@I~
    {                                                              //~v@@@I~
	    setWaitingResponse(Pplayer,false/*PswShadow*/);            //~v@21I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@21I~
    public void setWaitingResponse(int Pplayer,boolean PswShadow)  //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.setWaitingResponse player="+Pplayer+",swShadow="+PswShadow);//~v@21I~
	    setWaitingResponse(Pplayer,PswShadow,true/*PswResetAll*/);  //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~v@21I~
    public void setWaitingResponse(int Pplayer,boolean PswShadow,boolean PswResetAll)//~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.setWaitingResponse player="+Pplayer+",swShadow="+PswShadow+",swResetAll="+PswResetAll);//~v@21R~
        if (PswResetAll)                                           //~v@21I~
	    	resetLight(LST_DISABLE,Pplayer);                       //~v@21R~
        if (PswShadow)                                             //~v@21I~
	        drawLightShadow(Pplayer,LST_WAITING_RESPONSE);         //~v@21M~
        else                                                       //~v@21I~
	        drawLight(Pplayer,LST_WAITING_RESPONSE);               //~v@21M~
        if (Dump.Y) Dump.println("DiceBox.setWaitingResponse player="+Pplayer+",statusLight="+Utils.toString(statusLight));//~vaedI~
    }                                                              //~v@21I~
    //*********************************************************    //~v@@@I~
    //*return waiting player count                                 //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void resetWaitingResponse(int Pplayer)                  //~v@@@R~
    {                                                              //~v@@@I~
        drawLight(Pplayer,LST_DISABLE);                            //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@21I~
    //*on Server and Client                                        //~v@21I~
    //*********************************************************    //~v@21I~
    public static void emulateDiceCasted(boolean PswServer,int Proll1,int Proll2)//~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.emulateDiceCast roll1="+Proll1+",roll2="+Proll2+",aDicsBox.isAlive="+AG.aDiceBox.isAlive);//~v@21I~//~v@11R~
        boolean swAlive;                                            //~v@11I~
        synchronized(AG.aDiceBox.isAlive)                          //~v@11I~
        {                                                          //~v@11I~
        	swAlive=AG.aDiceBox.isAlive;                           //~v@11I~
            if (!swAlive)                                          //~v@11I~
		        AG.aDiceBox.isAlive=true;                          //~v@11I~
        }                                                          //~v@11I~
//      if (!AG.aDiceBox.isAlive)                                              //~v@@@I~//~v@21M~//~v@11R~
        if (!swAlive)                                              //~v@11I~
        {                                                          //~v@@@I~//~v@21M~
//          AG.aDiceBox.isAlive=true;                              //~v@11R~
	        AG.aDiceBox.enableDiceAfter=false;                     //~v@21I~
	        DiceBox.Casting t=AG.aDiceBox.new Casting(PswServer,Proll1-1,Proll2-1);//~v@21I~
    	    t.start();                                             //~v@@@R~//~v@21M~
        }                                                          //~v@@@I~//~v@21M~
        else                                                       //~v@11I~
        {                                                          //~v@11I~
        	if (Dump.Y) Dump.println("DiceBox.emulateDiceCast @@@@ dupricated casr req");//~v@11I~
        }                                                          //~v@11I~
    }                                                              //~v@21I~
	//*******************************************************************//~v@21I~
	//*on Server                                                   //~v@21I~
	//*******************************************************************//~v@21I~
	public static Point cast(boolean PswServer)                  //~v@@@R~//~v@21R~
    {                                                              //~v@@@I~//~v@21M~
        int roll1=Utils.getRandom(MAX_SPOTS)+1;                        //~v@21I~
        int roll2=Utils.getRandom(MAX_SPOTS)+1;                        //~v@21I~
        if (Dump.Y) Dump.println("DiceBox.cast roll1="+roll1+",roll2="+roll2);//~v@21I~
	    emulateDiceCasted(PswServer,roll1,roll2);                   //~v@21R~
        return new Point(roll1,roll2);                             //~v@21I~
    }                                                              //~v@@@I~//~v@21M~
    //**************************************************************************************//~v@21R~
    //**************************************************************************************//~v@21I~
    //**************************************************************************************//~v@21I~
    public class Casting extends Thread                            //~v@@@I~
    {                                                              //~v@@@I~
    	int emRoll1,emRoll2;                                       //~v@21R~
        boolean swServer,swEmulated;                               //~v@21R~
    //***********************************                          //~v@21I~
    	public Casting()                                           //~v@21R~
        {                                                          //~v@21I~
    		emRoll1=0; emRoll2=0;                                   //~v@21I~
        	if (Dump.Y) Dump.println("DiceBox.Casting constructor");//~v@21I~
        }                                                          //~v@21I~
    //***********************************                          //~v@21I~
    	public Casting(boolean PswServer,int Proll1,int Proll2)    //~v@21R~
        {                                                          //~v@21I~
        	emRoll1=Proll1; emRoll2=Proll2;                        //~v@21R~
            swServer=PswServer;                                    //~v@21I~
            swEmulated=true;                                       //~v@21I~
        	if (Dump.Y) Dump.println("DiceBox.Casting constructor emulation swServer="+PswServer+",roll1="+Proll1+",roll2="+Proll2);//~v@21R~
        }                                                          //~v@21I~
    //***********************************                          //~v@@@R~
        @Override                                                  //~v@@@R~
        public void run ()                                         //~v@@@R~
        {                                                          //~v@@@R~
//          isAlive=true;                                          //~v@21I~//~v@11R~
        	int sleepTime=0;                                         //~v@@@I~
        	if (Dump.Y) Dump.println("DiceBox.Casting thread run");//~v@@@I~
        	try                                                    //~v@@@I~
            {                                                      //~v@@@I~
   				Sound.play(SOUNDID_DICE_ROLL,false/*not change to beep when beeponly option is on*/);//~0410I~
                bgColor=COLOR_BG_ENABLE;    //initial              //~v@@@R~
                boolean swBlink=false;                             //~v@@@R~
                for (int ii=0;ii<sleepCtr.length;ii++)             //~v@@@R~
                {                                                  //~v@@@R~
                    int loopctr=sleepCtr[ii];                      //~v@@@R~
                    sleepTime=sleepInterval[ii];                   //~v@@@R~
//                  for (int jj=0;jj<loopctr;jj++)                 //~v@@@R~//~v@11R~
                    for (int jj=0;jj<loopctr-1;jj++)               //~v@11I~
                    {                                              //~v@@@R~
                        bgColor=(swBlink||ii==sleepCtr.length-1)?COLOR_BG_ENABLE:COLOR_BG_CASTING;//~v@@@M~
                        roll1=Utils.getRandom(MAX_SPOTS);          //~v@@@I~
                        roll2=Utils.getRandom(MAX_SPOTS);          //~v@@@I~
                        marginDiceX1=Utils.getRandom(marginDice)-marginDice/2;//~v@@@R~
                        marginDiceY1=Utils.getRandom(marginDice)-marginDice/2;//~v@@@R~
                        marginDiceX2=Utils.getRandom(marginDice)-marginDice/2;//~v@@@R~
                        marginDiceY2=Utils.getRandom(marginDice)-marginDice/2;//~v@@@R~
			        	if (Dump.Y) Dump.println("DiceBox.Casting thread run dice="+roll1+","+roll2);//~v@@@I~
			        	if (Dump.Y) Dump.println("DiceBox.Casting thread run margin=("+marginDiceX1+","+marginDiceY1+"),("+marginDiceX2+","+marginDiceY2+")");//~v@@@I~
                          drawDice(roll1,roll2,0/*fgcolor*/);      //~v@@@I~
//                        AG.aGameView.postInvalidate();           //~v@21R~
                          AG.aGameView.paint();                    //~v@21I~
                        Utils.sleep(sleepTime);                    //~v@@@R~
                        swBlink=!swBlink;                          //~v@@@R~
                    }                                              //~v@@@R~
                }                                                  //~v@@@R~
                marginDiceX1=0;                                    //~v@@@I~
                marginDiceY1=0;                                    //~v@@@I~
                marginDiceX2=0;                                    //~v@@@I~
                marginDiceY2=0;                                   //~v@@@I~
                if (swEmulated)                                  //~v@21R~
                {                                                  //~v@21I~
                	roll1=emRoll1; roll2=emRoll2;                  //~v@21I~
                }                                                  //~v@21I~
			    if (Dump.Y) Dump.println("DiceBox.Casting last paint");//~v@11I~
                bgColor=COLOR_BG_CASTING;                          //~v@11R~
                drawDice(roll1,roll2,0/*fgcolor*/);                //~v@11I~
                AG.aGameView.paint();                              //~v@11I~
	   			Sound.play(SOUNDID_DICE_FIX,false/*not change to beep when beeponly option is on*/);//~0410I~
                Utils.sleep(sleepTime);                            //~v@11M~
                if (enableDiceAfter)                       //~v@@@I~//~v@21R~
                {                                          //~v@@@I~//~v@21R~
                    bgColor=COLOR_BG_ENABLE;               //~v@@@I~//~v@21R~
                    drawDice(roll1,roll2,0/*fgcolor*/);    //~v@@@I~//~v@21R~
                }                                          //~v@@@I~//~v@21R~
                else                                       //~v@@@I~//~v@21R~
                {                                          //~v@@@I~//~v@21R~
                    bgColor=COLOR_BG_DISABLE;   //casting end//~v@@@I~//~v@21R~
                    drawDice(roll1,roll2,COLOR_FG_DISABLE);//~v@@@I~//~v@21R~
                }                                          //~v@@@I~//~v@21R~
			    if (Dump.Y) Dump.println("DiceBox.Casting thread run swEmulated="+swEmulated+",enableDiceAfter="+enableDiceAfter+",roll1="+roll1+",roll2="+roll2);//~v@11R~
//                if (msgid==0) //not emulation(dice casted player)//~v@21R~
//                    GameViewHandler.sendMsg(GCM_DICE_CASTED,roll1+1,roll2+1,0);//~v@@@R~//~v@21R~
//                else    //emulation                              //~v@21R~
//                if (!swServer)                                   //~v@21R~
//                    AG.aAccounts.sendToServerDelayedResponse(msgid);//~v@21R~
                  GameViewHandler.sendMsg(GCM_DICE_CASTED,roll1+1,roll2+1,0);//~v@21I~
            }                                                      //~v@@@I~
            catch(Exception e)                                     //~v@@@I~
            {                                                      //~v@@@I~
            	Dump.println(e,"Dice cast subthread");             //~v@@@I~
            }	                                                   //~v@@@I~
//          isAlive=false;                                         //~v@@@I~//~v@11R~
            swEnable=enableDiceAfter;                              //~v@@@I~
            if (!swEnable)                                         //~v@@@I~
			    resetAll();                                        //~v@@@I~
			if (Dump.Y) Dump.println("DiceBox.Casting thread exit swEnable="+swEnable);//~v@11I~
          synchronized(isAlive)                                    //~v@11I~
          {                                                        //~v@11I~
            isAlive=false;                                         //~v@11I~
          }                                                        //~v@11I~
        }                                                          //~v@@@R~
    }//class Casting                                               //~v@@@I~
    //***********************************                          //~v@@@I~
//  public void doEnable(boolean Penable)                          //~v@@@R~//+vam0R~
    private void doEnable(boolean Penable)                         //+vam0I~
    {                                                              //~v@@@I~
    	swEnable=Penable;                                          //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.doEnable enable="+Penable);//~v@11I~
        bgColor=swEnable?COLOR_BG_ENABLE:COLOR_BG_DISABLE;	//initial//~v@@@I~
//        canvas=Graphics.lockCanvas(rectDiceBox);                 //~v@@@R~
//        synchronized(holder)                                     //~v@@@R~
//        {                                                        //~v@@@R~
        	if (swEnable)                                            //~v@@@I~
//          	drawDice(canvas);                                  //~v@@@R~
            	drawDice(roll1,roll2,0);                           //~v@@@I~
            else                                                   //~v@@@I~
//  	        drawDice(canvas,COLOR_FG_DISABLE);                 //~v@@@R~
    	        drawDice(roll1,roll2,COLOR_FG_DISABLE);            //~v@@@I~
//        }                                                        //~v@@@R~
//        Graphics.unlockCanvas(canvas);                           //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@R~
	private void drawDice(Canvas Pcanvas)                          //~v@@@R~
    {                                                              //~1120I~//~1122M~
        if (Dump.Y) Dump.println("DiceBox.drawDice");              //~v@@@R~
        drawDice(Pcanvas,roll1,roll2);                             //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	private void drawDice(Canvas Pcanvas,int Pfgcolor)             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.drawDice fgcolor="+Integer.toHexString(Pfgcolor));//~v@@@R~
        drawDice(Pcanvas,roll1,roll2);                             //~v@@@I~
        drawFG(Pcanvas,Pfgcolor);                                  //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*from Gcanvas initialize under lockCanvas done status        //~v@@@R~
	//*********************************************************    //~v@@@I~
	public void drawDice(Canvas Pcanvas,int Proll1,int Proll2)     //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.drawDice roll1="+Proll1+",roll2="+Proll2);//~v@@@R~
        drawEdge(Pcanvas);                                         //~v@@@I~
        drawBG(Pcanvas);                                           //~v@@@I~
    	Bitmap bm;                                                 //~v@@@I~
        int xx0,yy0,xx1,yy1;                                       //~v@@@I~
        xx0=possDice[0].x;                                         //~v@@@I~
		yy0=possDice[0].y;                                         //~v@@@I~
        xx1=possDice[1].x;                                         //~v@@@I~
		yy1=possDice[1].y;                                         //~v@@@I~
                                                                   //~v@@@I~
    	bm=bmsDice[Proll1];    //6                                 //~v@@@R~
        Graphics.drawBitmap(Pcanvas,bm,xx0+marginDiceX1,yy0+marginDiceY1);//~v@@@R~
    	bm=bmsDice[Proll2];    //6                                 //~v@@@R~
        Graphics.drawBitmap(Pcanvas,bm,xx1+marginDiceX2,yy1+marginDiceY2);//~v@@@R~
//        if (!swRunning)                                          //~v@@@R~
////          drawLight(Pcanvas,false/*light off*/);               //~v@@@R~
            drawLightInit(Pcanvas);                                //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*not from Fcanvas init(no locked canvas)                     //~v@@@I~
	//*********************************************************    //~v@@@I~
	public void drawDice(int Proll1,int Proll2,int PfgColor)       //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.drawDice roll1="+Proll1+",roll2="+Proll2+",fgColor="+PfgColor);//~v@@@I~//~v@11R~
        Rect rectEdge=new Rect(rectDiceBox.left-EDGE_WIDTH,rectDiceBox.top-EDGE_WIDTH,rectDiceBox.right+EDGE_WIDTH,rectDiceBox.bottom+EDGE_WIDTH);//~v@@@R~
        int xx0,yy0,xx1,yy1;                                       //~v@@@I~
        xx0=possDice[0].x;                                         //~v@@@I~
        yy0=possDice[0].y;                                         //~v@@@I~
        xx1=possDice[1].x;                                         //~v@@@I~
        yy1=possDice[1].y;                                         //~v@@@I~
        Graphics.drawDiceBox(rectEdge,COLOR_EDGE,EDGE_WIDTH,rectDiceBox,bgColor,PfgColor,//~v@@@I~
		    			bmsDice[Proll1],xx0+marginDiceX1,yy0+marginDiceY1,//~v@@@I~
		    			bmsDice[Proll2],xx1+marginDiceX2,yy1+marginDiceY2);//~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void drawEdge(Canvas Pcanvas)                          //~v@@@I~
    {                                                              //~v@@@I~
    	int strokeW=EDGE_WIDTH;                                    //~v@@@R~
//        Paint p=new Paint();                                     //~v@@@R~
//        p.setStyle(Paint.Style.STROKE);                          //~v@@@R~
//        p.setStrokeWidth(strokeW);                               //~v@@@R~
        Rect rect=new Rect(rectDiceBox.left-strokeW,rectDiceBox.top-strokeW,rectDiceBox.right+strokeW,rectDiceBox.bottom+strokeW);//~v@@@R~
        Graphics.drawRect(Pcanvas,rect,COLOR_EDGE);                //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void drawBG(Canvas Pcanvas)                            //~v@@@I~
    {                                                              //~v@@@I~
        Graphics.drawRect(Pcanvas,rectDiceBox,bgColor);            //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void drawFG(Canvas Pcanvas,int  Pfgcolor)              //~v@@@I~
    {                                                              //~v@@@I~
        Graphics.drawRect(Pcanvas,rectDiceBox,Pfgcolor);           //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void drawBox(Canvas Pcanvas,Rect Prect,int Pcolor)     //~v@@@I~
    {                                                              //~v@@@I~
        Graphics.drawRect(Pcanvas,Prect,Pcolor);                   //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*draw as dirty parts                                         //~v@@@I~
	//*********************************************************    //~v@@@I~
	public Rect getDiceRect()                                     //~v@@@I~
    {                                                              //~v@@@I~
        Rect rect=table.getDiceBoxRect();                              //~v@@@R~
        if (Dump.Y) Dump.println("DiceBox.getDiceRect top="+rect.top+",left="+rect.left+",bottom="+rect.bottom+",right="+rect.right);//~v@@@R~
        return rect;                                               //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	//*rc=6:dicebox, 0-3:player light, -1:none, 4: all             //~v@@@R~
	//*********************************************************    //~v@@@I~
	public int isTouched(int Pxx,int Pyy)                          //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.isTouched xx="+Pxx+",yy="+Pyy);//~vaedI~
    	int rc=ISTOUCH_NONE;                                       //~v@@@R~
        int stat=0;                                                //~v@@@R~
	    if (swEnable && (Pxx>rectDiceBox.left && Pxx<rectDiceBox.right && Pyy>rectDiceBox.top && Pyy<rectDiceBox.bottom))//~v@@@R~
        	rc=ISTOUCH_DICE;                                       //~v@@@R~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
        	if (Dump.Y) Dump.println("DiceBox.isTouched stat="+Utils.toString(statusLight));//~vaedI~
            for (int ii=0;ii<PLAYERS;ii++)                         //~v@@@I~
            {                                                      //~v@@@I~
	        	if (Dump.Y) Dump.println("DiceBox.isTouched allowance="+touchAllowance+",boxLight="+boxLight[ii].toString());//~vaedI~
                if (statusLight[ii]==LST_WAITING_RESPONSE          //~v@@@R~
                ||   statusLight[ii]==LST_WAITING_ANYONE           //~v@@@R~
                ||   statusLight[ii]==LST_DISCARD_TIMEOUT          //~v@11I~
                ||   statusLight[ii]==LST_WAITING_ALL)             //~v@@@I~
                	if (Pxx>boxLight[ii].left-touchAllowance && Pxx<boxLight[ii].right+touchAllowance && Pyy>boxLight[ii].top-touchAllowance && Pyy<boxLight[ii].bottom+touchAllowance)//~v@@@R~
                    {	                                           //~v@@@I~
                    	rc=ii;                                     //~v@@@R~
                		stat=statusLight[ii];                      //~v@@@R~
			        	if (Dump.Y) Dump.println("DiceBox.isTouched on boxLight stat="+stat);//~vaedI~
                        break;                                     //~v@@@I~
                    }                                              //~v@@@I~
            }                                                      //~v@@@I~
            if (rc!=ISTOUCH_NONE)                                  //~v@@@I~
            {                                                      //~v@@@I~
                if (stat==LST_WAITING_ANYONE)                      //~v@@@I~
                {                                                  //~v@@@I~
                    resetAll();                                    //~v@@@I~
                    rc|=ISTOUCH_ANYONE;                            //~v@@@I~
                }                                                  //~v@@@I~
                else                                               //~v@@@I~
                if (stat==LST_WAITING_ALL)                         //~v@@@I~
                {                                                  //~v@@@I~
                    resetLight(rc);                                 //~v@@@I~
                    if (!isAnyoneWaiting())                        //~v@@@I~
                        rc|=ISTOUCH_ALL;                           //~v@@@I~
                }                                                  //~v@@@I~
                else                                               //~v@21I~
                if (stat==LST_DISCARD_TIMEOUT)                     //~v@11I~
                {                                                  //~v@11I~
//                  resetLight(rc);                                //~v@11R~
                    rc|=ISTOUCH_DISCARD_TIMEOUT;                   //~v@11I~
                }                                                  //~v@11I~
                else                                               //~v@11I~
                    resetLight(rc);     //avoid dup event          //~v@21R~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.isTouched stat="+stat+",swEnable="+swEnable+",rc="+rc);      //~v@@@I~//~v@21R~//~v@11R~
        return rc;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	public static void enable(Boolean Penable)                     //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.enable enable="+Penable);//~v@@@I~
        AG.aDiceBox.doEnable(Penable);                                 //~v@@@R~//~v@21R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public boolean isAnyoneWaiting()                               //~v@@@I~
    {                                                              //~v@@@I~
        boolean rc=false;                                          //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@I~
        {                                                          //~v@@@I~
            if (statusLight[ii]!=LST_DISABLE)                      //~v@@@I~
            {                                                      //~v@@@I~
            	rc=true;                                           //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("DiceBox.isAnyoneWaiting rc="+rc);
        return rc;//~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~vam0I~
    private void createBitmapSplitPosID()                               //~vam0I~
    {                                                              //~vam0I~
        if (Dump.Y) Dump.println("DiceBox.createBitmapSplitPosID swSplitPos="+swSplitPos);//~vam0I~
    	if (!swSplitPos)                                           //~vam0I~
        	return;                                                //~vam0I~
        Bitmap bmp;                                                //~vam0I~
        Matrix matrix=new Matrix();                                //~vam0I~
        Paint paint=new Paint();                                   //~vam0I~
    	bmsSplitPosID=new Bitmap[PLAYERS];                         //~vam0I~
        int sz=radiusStarterMark*2;                                //~vam0I~
        if (Dump.Y) Dump.println("DiceBox.createBitmapSplitPosID sz="+sz);//~vam0R~
        bmp=Graphics.createBitmap(sz,sz,Bitmap.Config.ARGB_8888);  //~vam0I~
        Canvas cc=new Canvas(bmp);                                 //~vam0M~
        Rect r=new Rect(0,0,sz,sz);                                //~vam0M~
        Graphics.drawRect(cc,r,COLOR_SPRITPOS_FRAME);              //~vam0R~
        int border=2;                                              //~vam0I~
        r.left+=border; r.right-=border; r.top+=border; r.bottom-=border;//~vam0R~
        Graphics.drawRect(cc,r,COLOR_SPRITPOS_BG);                 //~vam0I~
                                                                   //~vam0I~
	    String text=Utils.getStr(R.string.Label_SpritPosID);       //~vam0I~
        paint.setColor(COLOR_SPRITPOS_FG);                         //~vam0I~
    	int textsz=sz-border*2;                                    //~vam0R~
    	Rect bounds=adjustTextSize(paint,text,textsz/*initial size*/,textsz/*maxW*/,textsz/*maxH*/);//~vam0I~
        int xx=bounds.left+border;                                 //~vam0R~
        int yy=textsz-bounds.bottom-(textsz-(-bounds.top+bounds.bottom))/2+border;//~vam0R~
        if (Dump.Y) Dump.println("DiceBox.createBitmapSplitPosID paint="+paint);//~vam0I~
        Graphics.drawText(cc,text,xx,yy,paint);                    //~vam0R~
        bmsSplitPosID[0]=bmp;                                      //~vam0I~
                                                                   //~vam0I~
        matrix.setRotate(270,sz/2,sz/2);          //~v@@@I~       //~vam0I~
        bmp=Graphics.createBitmap(bmp,0,0,sz,sz,matrix,true);      //~vam0I~
        bmsSplitPosID[1]=bmp;                                      //~vam0I~
        bmp=Graphics.createBitmap(bmp,0,0,sz,sz,matrix,true);      //~vam0I~
        bmsSplitPosID[2]=bmp;                                      //~vam0I~
        bmp=Graphics.createBitmap(bmp,0,0,sz,sz,matrix,true);      //~vam0I~
        bmsSplitPosID[3]=bmp;                                      //~vam0I~
    }                                                              //~vam0I~
    //*********************************************************    //~vam0I~
    //*from Stock.drawDeal                                         //~vam0I~
    //*********************************************************    //~vam0I~
    public  void drawSplitPosID(int Peswn)                         //~vam0R~
    {                                                              //~vam0I~
        if (Dump.Y) Dump.println("DiceBox.drawSplitPosID swSplitPos="+swSplitPos+",playerStarter="+playerStarter);//~vam0R~
    	if (!swSplitPos)                                           //~vam0I~
        	return;                                                //~vam0I~
        int player= Accounts.eswnToPlayer(Peswn);                 //~vam0I~
        Bitmap bmp=bmsSplitPosID[player];                           //~vam0I~
        Point p=new Point(posLightStarter[player]); //center of circle//~vam0R~
        if (Dump.Y) Dump.println("DiceBox.drawSplitPosID cutEswn="+Peswn+",player="+player+",starter circlePoint="+p);//~vam0R~
        int sz=radiusStarterMark;                                  //~vam0I~
        if (player==playerStarter)	//drawn starterMark            //~vam0I~
        {                                                          //~vam0I~
        	Rect r=recterGameSeq;                                  //~vam0I~
            int margin=MARGIN_SPRITPOS_STARTER;                    //~vam0I~
            int sz2=sz*2;                                          //~vam0I~
            switch(player)                                         //~vam0I~
            {                                                      //~vam0I~
            case 0:                                                //~vam0I~
                p.x=r.right+margin;                                //~vam0I~
                p.y=r.bottom-sz2;                                  //~vam0R~
                break;                                             //~vam0I~
            case 1:                                                //~vam0I~
                p.x=r.right-sz2;                                   //~vam0R~
                p.y=r.top-sz2-margin;                              //~vam0R~
                break;                                             //~vam0I~
            case 2:                                                //~vam0I~
                p.x=r.left-sz2-margin;                             //~vam0R~
                p.y=r.top;                                         //~vam0R~
                break;                                             //~vam0I~
            case 3:                                                //~vam0I~
                p.x=r.left;                                        //~vam0R~
                p.y=r.bottom+margin;                               //~vam0I~
                break;                                             //~vam0I~
            }                                                      //~vam0I~
        }                                                          //~vam0I~
        else                                                       //~vam0I~
        {                                                          //~vam0I~
            switch(player)                                         //~vam0R~
            {                                                      //~vam0R~
            case 0:                                                //~vam0R~
                p.x+=sz;                                           //~vam0R~
                p.y-=sz;                                           //~vam0R~
                break;                                             //~vam0R~
            case 1:                                                //~vam0R~
                p.x-=sz;                                           //~vam0R~
                p.y-=sz*3;                                         //~vam0R~
                break;                                             //~vam0R~
            case 2:                                                //~vam0R~
                p.x-=sz*3;                                         //~vam0R~
                p.y-=sz;                                           //~vam0R~
                break;                                             //~vam0R~
            case 3:                                                //~vam0R~
                p.x-=sz;                                           //~vam0R~
                p.y+=sz;                                           //~vam0R~
                break;                                             //~vam0R~
            }                                                      //~vam0R~
        }                                                          //~vam0I~
        Graphics.drawBitmap(bmp,p.x,p.y);                          //~vam0I~
        posLightStarterPrevious=p;                                 //~vam0R~
        if (Dump.Y) Dump.println("DiceBox.drawSplitPosID splitPosID point="+p);//~vam0I~
    }                                                              //~vam0I~
    //*********************************************************    //~vam0I~
    //*from Starter.drawDeal                                       //~vam0I~
    //*********************************************************    //~vam0I~
    public  void drawSplitPosIDStarter(int Pplayer,Rect PrectGameSeq)//~vam0I~
    {                                                              //~vam0I~
        if (Dump.Y) Dump.println("DiceBox.drawSplitPosIDStarter player="+Pplayer+",rectGameSeq="+PrectGameSeq);//~vam0I~
        playerStarter=Pplayer;                                     //~vam0I~
        recterGameSeq=PrectGameSeq;                                //~vam0I~
    }                                                              //~vam0I~
    //*********************************************************    //~vam0I~
    public  void clearSplitPosID()                                 //~vam0I~
    {                                                              //~vam0I~
        if (Dump.Y) Dump.println("DiceBox.clearSplitPosID oldPoint="+posLightStarterPrevious);//~vam0I~
        if (posLightStarterPrevious!=null)                         //~vam0I~
        {                                                          //~vam0I~
            int xx=posLightStarterPrevious.x;                      //~vam0I~
            int yy=posLightStarterPrevious.y;                      //~vam0I~
            int sz=radiusStarterMark*2;                            //~vam0I~
            Rect r=new Rect(xx,yy,xx+sz,yy+sz);                    //~vam0I~
            Graphics.drawRect(r,COLOR_BG_TABLE);                   //~vam0R~
        	posLightStarterPrevious=null;                          //~vam0I~
        }                                                          //~vam0I~
    }                                                              //~vam0I~
    //*********************************************************    //~vam0I~
    private static Rect adjustTextSize(Paint Ppaint,String Pstr,int PinitialSize,int PmaxW,int PmaxH)//~vam0R~
    {                                                              //~vam0I~
        Rect r=new Rect();                                         //~vam0I~
        int sz=PinitialSize;                                       //~vam0I~
        int ww,hh;                                                 //~vam0I~
        for (;;)                                                   //~vam0I~
        {                                                          //~vam0I~
            Ppaint.setTextSize(sz);                                //~vam0I~
            int strsz=(int)Ppaint.measureText(Pstr);               //~vam0I~
            ww=strsz;                                              //~vam0I~
    	    Ppaint.getTextBounds(Pstr,0,Pstr.length(),r);          //~vam0I~
            hh=r.bottom-r.top;                                     //~vam0I~
            if (Dump.Y) Dump.println("DiceBox.adjustTextSize str="+Pstr+",sz="+sz+",strsz="+strsz+",maxW="+PmaxW+",maxH="+PmaxH+",hh="+hh+",boundrect="+r.toString());//~vam0I~
            if (strsz<PmaxW && hh<PmaxH)                           //~vam0I~
                break;                                             //~vam0I~
//          sz-=2;                                                 //~vam0I~
            sz--;                                                  //~vam0I~
        }                                                          //~vam0I~
        if (Dump.Y) Dump.println("DiceBox.adjustTextSize sz="+sz+",ww="+ww+",hh="+hh);//~vam0I~
        return r;                                                  //~vam0R~
    }                                                              //~vam0I~
}//class DiceBox                                                   //~v@@@R~
