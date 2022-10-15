//*CID://+var7R~: update#= 661;                                    //~var7R~
//**********************************************************************//~v101I~
//2022/09/20 var7 vertical Top2Bottom,doublequatation u201c/201d   //~var7I~
//2022/09/20 var6 vertical Top2Bottom,(") distance is too large    //~var6I~
//2022/09/20 var5 vertical Top2Bottom,change () to u-fe35/fe36(vertical kakko)//~var5I~
//2022/09/20 var4 vertical Top2Bottom,space distance is too large  //~var4I~
//2022/09/19 var3 Magbar for .ko                                   //~var3I~
//2021/10/23 vaf5 (Bug)TTop panel msgbar overflow, adjust textsize //~vaf5I~
//2021/06/17 va99 protect loop by msgBar size                      //~va99I~
//2021/02/01 va66 training mode(1 human and 3 robot)               //~va66I~
//2021/01/07 va60 CalcShanten (smart Robot)                        //~va60I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Matrix;                                    //~0215I~
import android.util.TypedValue;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;//~v@@@R~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~

import com.btmtest.MainView;
import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.GConst;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Tables;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

public class GMsg                                                  //~v@@@R~
{                                                                  //~0914I~
    public  static final int GMSGOPT_ANDTOAST=0x01;                 //~v@@@I~
    public  static final int GMSGOPT_ALL=0x02;                     //~0224I~
    public  static final int GMSGOPT_CLIENTSELF=0x04;	//issue on the client if sendToServer Failed//~0110I~//~0224I~
    public  static final int GMSGOPT_ESWN=0x08;	//issue on the client if sendToServer Failed//~0225I~
    public  static final int GMSGOPT_STRPARM=0x10;	//with stringparm//~0303I~
                                                                   //~v@@@I~
    private static final int TEXT_SIZE=40;                         //~v@@@R~
    private static final int TEXT_COLOR=Color.argb(0xff,0xff,0xff,0xff);//~v@@@R~
//  private static final int BG_COLOR=Color.argb(0xa0,0xff,0xff,0xff);//~v@@@R~
    private static final int COLOR_BG_HL=Color.argb(0xff,0xff,0xff,0x00); //yellow//~v@@@R~
    private static final int COLOR_FG_HL=Color.argb(0xff,0x00,0x00,0x00); //black//~v@@@I~
    private static final int COLOR_MSGBAR_TEXT=Color.argb(0xf0,0xff,0xff,0xff); //yellow//~v@@@I~
    private static final int HL_MARGIN=10;                         //~v@@@I~
    private static final double TEXT_MARGIN=0.2;
    private static final int TEXT_MARGIN_SIDE=10;               //~v@@@I~
    private static final double MSGH_ALLOWANCE=0.95;  //avoid overflow 90% shrink//~v@@@R~
//  private static final double STROKE_WIDTH=1.5;                  //~v@@@R~//~0401R~
    private static final int ADJUSTVJ=4;                           //~0216I~
	private static final Tables[] StblAction={                     //~v@@@I~
					new Tables(Utils.getStr(R.string.UserAction_Pon),GCM_PON),//~v@@@I~
					new Tables(Utils.getStr(R.string.UserAction_Kan),GCM_KAN),//~v@@@I~
					new Tables(Utils.getStr(R.string.UserAction_Chii),GCM_CHII),//~v@@@I~
					new Tables(Utils.getStr(R.string.Label_Ron),GCM_RON),//~v@@@I~
					new Tables(Utils.getStr(R.string.Label_Take),GCM_TAKE),//~v@@@I~
					new Tables(Utils.getStr(R.string.UserAction_Reach),GCM_REACH),	//last is longest text //~v@@@I~//~9C02I~
					new Tables(Utils.getStr(R.string.UserAction_Reach_Open),GCM_REACH_OPEN),	//last is longest text//~va66I~
                    };                                             //~1A08I~//~v@@@I~
    private static final String SHIFTCHAR="。、";                              //~0215R~//~0216I~
    private static final char   SHIFTCHAR_DQL='“'; //u201c        //~var7I~
    private static final char   SHIFTCHAR_DQR='”'; //u201d        //~var7I~
//  private static final String SHIFTUP=".,";                      //~0216I~//~var4R~
//  private static final String SHIFTUP="., ";                     //~var4I~//~var6R~
    private static final String SHIFTUP="., \"\'";                 //~var6I~
    private static final String SHIFTUP2="\"\'";   //shift for followings//~var6I~
//  private static final String SHIFTDOWN="\"\'";                  //~0216I~
//  private GCanvas gcanvas;                                       //~v@@@R~
//  private Canvas canvas;                                         //~v@@@I~
    private MJTable table;                                         //~v@@@I~
//  private Pieces pieces;                                         //~v@@@I~
    private int scrW,scrH;                                         //~v@@@I~
    private Paint paint;                                           //~v@@@I~
    private Paint paintHL;                                         //~v@@@I~
    private int textSize;                                          //~v@@@I~
//  private int msgbarLen,msgbarSize;                              //~v@@@I~//~va99R~
    protected int msgbarLen,msgbarSize;   //protected for ITMock   //~va99I~
    private boolean swPortrait;                                    //~v@@@I~
    private Rect rectMsgbar;
    private int xxDraw,yyDraw;                                     //~v@@@I~
    private float[] fposDraw;                                      //~v@@@I~
    private Rect rectVTextHL;                                      //~v@@@I~
    private boolean swDrawn;
    private int colorHL;//~v@@@I~
    private Rect rectDraw,rectTextHL;                              //~v@@@I~
    private boolean swVerticalB2T;	//vertical bottom to top       //~0215I~
    private boolean swVerticalT2B;	//vertical top to bottom for !JP and !Portrait(for .ko msg)//~var3I~
    private Bitmap bmB2T;                                          //~0215I~
    private Rect rectB2T,rectB2TText;	//horizontal<--virtical, rotate later//~0215R~
//  private String SshiftCharSbcs="\",.";                          //~0216R~
//  private double pxToSp;                                         //~v@@@R~
    private String msgVertical;                                    //~var5I~
    private boolean swCharShiftSbcsType2;                          //~var6I~
//*************************                                        //~v@@@I~
	public GMsg()   //for IT override                              //~va60I~
    {                                                              //~va60I~
        if (Dump.Y) Dump.println("GMsg.defaultConstructor");       //~va60I~
    }                                                              //~va60I~
	public GMsg(GCanvas Pgcanvas)                                  //~v@@@R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("GMsg.constructor");              //~0217I~
    	recycle(AG.aGMsg);                                         //~0217I~
    	AG.aGMsg=this;                                             //~v@@@R~
        GCanvas gcanvas = Pgcanvas;                                //~v@@@R~
        table = gcanvas.table;                                     //~v@@@I~
//      pieces=table.pieces;                                       //~v@@@I~
        scrW=AG.aGameView.WW;                                      //~v@@@R~
        scrH=AG.aGameView.HH;                                      //~v@@@R~
        textSize=TEXT_SIZE;                                        //~v@@@I~
        swPortrait=table.swPortrait;                               //~v@@@I~
        msgbarLen=swPortrait ? table.WW-(table.leftButtonW+table.rightButtonW) : table.HH-(table.topButtonH+table.bottomButtonH);//~v@@@I~
//      msgbarSize=MSGBAR_SIZE;                                    //~v@@@R~
        msgbarSize=table.sizeMsgBar;    //portrait:height, landscape:width                   //~v@@@I~//~0215R~
        if (swPortrait)                                            //~v@@@I~
        	rectMsgbar=new Rect(table.leftButtonW, table.HH-table.bottomButtonH, table.leftButtonW+msgbarLen,  table.HH-table.bottomButtonH+msgbarSize);//~v@@@R~
        else                                                       //~v@@@I~
	        if (AG.aRule.swLeftButtons)                            //~v@@@I~
	        	rectMsgbar=new Rect(scrW-table.rightButtonW, table.topButtonH, scrW-table.rightButtonW+msgbarSize, table.topButtonH+msgbarLen);//~v@@@R~
            else                                                   //~v@@@I~
    	    	rectMsgbar=new Rect(table.leftButtonW-msgbarSize, table.topButtonH, table.leftButtonW, table.topButtonH+msgbarLen);//~v@@@R~
//      pxToSp=AG.resource.getDisplayMetrics().scaledDensity;      //~v@@@R~
		swVerticalB2T=!AG.isLangJP && !swPortrait;	//landscape english//~0215I~
        if (swVerticalB2T)                                         //~0215I~
        {                                                          //~0215I~
        	rectB2T= new Rect(0,0,rectMsgbar.bottom-rectMsgbar.top,rectMsgbar.right-rectMsgbar.left);//~0215I~
//          bmB2T=Bitmap.createBitmap(rectB2T.right,rectB2T.bottom,Bitmap.Config.ARGB_8888);//~0215R~//~0216R~
            bmB2T=Graphics.createBitmap(rectB2T.right,rectB2T.bottom,Bitmap.Config.ARGB_8888);//~0216I~
        }                                                          //~0215I~
        if (Dump.Y) Dump.println("GMsg.msgbar rect="+rectMsgbar.toString());//~v@@@R~
        setHLMsg();                                                //~v@@@I~
    }
    //*********************************************************    //~0217I~
    public void onDestroy()                                        //~0217I~
    {                                                              //~0217I~
        if (Dump.Y) Dump.println("GMsg.onDstroy");                 //~0217I~
        recycle(this);                                             //~0217R~
    }                                                              //~0217I~
    //*********************************************************    //~0217I~
    private static void recycle(GMsg Pinst)                        //~0217R~
    {                                                              //~0217I~
        if (Dump.Y) Dump.println("GMsg.recycle");                  //~0217R~
        if (Pinst==null)                                           //~0217I~
        	return;                                                //~0217I~
        UView.recycle(Pinst.bmB2T);                                //~0217R~
        Pinst.bmB2T=null;                                          //~0217R~
    }                                                              //~0217I~
    //*********************************************************    //~v@@@I~
    private Rect setPaint(String Pmsg)                             //~v@@@R~
    {                                                              //~v@@@I~
        paint=new Paint();                                          //~v@@@I~
        paint.setAntiAlias(true);                                  //~v@@@I~
        paint.setColor(TEXT_COLOR);                                //~v@@@I~
        paint.setTextSize(textSize);                               //~v@@@R~
//      paint.setTypeface(Typeface.create(Typeface.MONOSPACE,Typeface.NORMAL));   //TODO test//~0202R~
        int strsz=(int)paint.measureText(Pmsg);                    //~v@@@I~
        int xx=(scrW-strsz)/2;                                     //~v@@@R~
        int yy=(int)(textSize*(1+TEXT_MARGIN));                    //~v@@@R~
        Rect r=new Rect(xx-TEXT_MARGIN_SIDE,scrH/2,xx+strsz+TEXT_MARGIN_SIDE,scrH/2+yy);//~v@@@R~
        if (Dump.Y) Dump.println("GMsg.getRect strsz="+strsz+",l="+r.left+",t="+r.top+",r="+r.right+",b="+r.bottom);//~v@@@I~
        return r;                                                  //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void setHLMsg()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.setHLMsg");                 //~v@@@I~
    	for (int ii=0;ii<StblAction.length;ii++)                   //~v@@@I~
        {                                                          //~v@@@I~
        	String action=StblAction[ii].name;                     //~v@@@I~
            if (AG.aGMsg.swPortrait)                               //~v@@@I~
            {                                                      //~v@@@I~
    			setMsgbarHorizontal(action);                       //~v@@@R~
	        	StblAction[ii].setObject(new Point(xxDraw,yyDraw));//~v@@@I~
            }                                                      //~v@@@I~
            else                                                   //~v@@@I~
            {                                                      //~v@@@I~
    			setMsgbarVertical(action);                         //~v@@@R~
	        	StblAction[ii].setObject(fposDraw);                //~v@@@I~
            }                                                      //~v@@@I~
        }                                                          //~v@@@I~
        //*rect by longest(Raeach)                                 //~9C02I~
        if (AG.aGMsg.swPortrait)                                   //~9C02I~
        {                                                          //~9C02I~
            rectTextHL=new Rect(rectDraw);                         //~9C02I~
            rectTextHL.left-=HL_MARGIN;                            //~9C02I~
            rectTextHL.right+=HL_MARGIN;                           //~9C02I~
        }                                                          //~9C02I~
        else                                                       //~9C02I~
        {                                                          //~9C02I~
            rectTextHL=new Rect(rectDraw);                         //~9C02I~
            rectTextHL.top-=HL_MARGIN;                             //~9C02I~
            rectTextHL.bottom+=HL_MARGIN;                          //~9C02I~
        }                                                          //~9C02I~
        paintHL=new Paint(paint);                                  //~v@@@R~
        paintHL.setColor(COLOR_FG_HL);                             //~v@@@R~
        if (Dump.Y) Dump.println("GMsg.setHLMsg rectTextHL="+rectTextHL.toString());//~v@@@I~
    }                                                              //~v@@@I~
//    //*********************************************************  //~v@@@R~
//    public void drawMsg(String Pmsg)                             //~v@@@R~
//    {                                                            //~v@@@R~
////        if (Dump.Y) Dump.println("GMsg.drawMsg:" + Pmsg);      //~v@@@R~
////        Rect rect = setPaint(Pmsg);                            //~v@@@R~
////        canvas = Graphics.lockCanvas(rect);                    //~v@@@R~
////        try                                                    //~v@@@R~
////        {                                                      //~v@@@R~
////            drawMsg(Pmsg, rect);                               //~v@@@R~
////        }                                                      //~v@@@R~
////        catch (Exception e)                                    //~v@@@R~
////        {                                                      //~v@@@R~
////            Dump.println(e, "GMsg.drawText");                  //~v@@@R~
////        }                                                      //~v@@@R~
////        Graphics.unlockCanvas(canvas);                         //~v@@@R~
//        SpriteDlg.show("Title",Pmsg);                            //~v@@@R~
//    }                                                            //~v@@@R~
//    //********************************************               //~v@@@R~
//    private void drawMsg(String Pmsg,Rect Prect)                 //~v@@@R~
//    {                                                            //~v@@@R~
//        Paint p=new Paint();                                     //~v@@@R~
//        p.setStyle(Paint.Style.FILL_AND_STROKE);                 //~v@@@R~
//        p.setStrokeWidth((float)STROKE_WIDTH);                   //~v@@@R~
//        p.setColor(BG_COLOR);                                    //~v@@@R~
//        canvas.drawRect(Prect,p);                                //~v@@@R~
//        canvas.drawText(Pmsg,Prect.left+TEXT_MARGIN_SIDE,(int)(Prect.bottom-textSize*TEXT_MARGIN),paint);//~v@@@R~
//    }                                                            //~v@@@R~
    //********************************************                 //~v@@@I~
    public static void drawMsgbar(int Presid)                      //~v@@@R~
    {                                                              //~v@@@I~
    	drawMsgbar(Utils.getStr(Presid));                           //~v@@@I~
    }                                                              //~v@@@I~
    //********************************************               //~v@@@R~//~0113R~
    public static void clearMsgbar()  //MaiView or GameView                           //~v@@@R~//~0113R~
    {                                                            //~v@@@R~//~0113R~
        if (AG.aGMsg==null || AG.aGMsg.swDrawn)                                             //~v@@@I~//~0113R~
        {                                                        //~v@@@I~//~0113R~
            drawMsgbar("");                                      //~v@@@R~//~0113R~
            if (AG.aGMsg!=null)
                AG.aGMsg.swDrawn=false;                                       //~v@@@I~//~0113R~
        }                                                        //~v@@@I~//~0113R~
    }                                                            //~v@@@R~//~0113R~
    //*********************************************************    //~v@@@M~
    public void reset()              //GameView only                              //~v@@@M~//~0113R~
    {                                                              //~v@@@M~
        if (Dump.Y) Dump.println("GMsg.reset swDrawn="+swDrawn);   //~v@@@I~
    	if (swDrawn)                                               //~v@@@I~
        {                                                          //~v@@@I~
    		drawMsgbar("");                                        //~v@@@I~
            swDrawn=false;                                         //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@M~
    //********************************************                 //~9C02I~
    public static void showHL(int Popt,int PactionID)              //~9C02I~
    {                                                              //~9C02I~
    	AG.aGMsg.drawHL(Popt,PactionID);                           //~9C02I~
    }                                                              //~9C02I~
    //********************************************                 //~9C06I~
    public static void showHL(int Popt,int PactionID,int Pplayer)      //~9C06I~
    {                                                              //~9C06I~
    	AG.aGMsg.drawHL(Popt,PactionID,Pplayer);                   //~9C06I~
    }                                                              //~9C06I~
    //********************************************                 //~0218I~
    public static void showHLName(int Popt,int PactionID,int Pplayer)//~0218I~
    {                                                              //~0218I~
    	AG.aGMsg.drawHLName(Popt,PactionID,Pplayer);               //~0218I~
    }                                                              //~0218I~
    //********************************************                 //~v@@@I~
    private void drawHL(int Popt,int PactionID)              //~v@@@I~//~9C02R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.showHL actionid="+PactionID);//~v@@@I~//~9C02R~
    	int idx=Tables.find(StblAction,PactionID);                  //~v@@@I~
        if (idx==-1)                                               //~v@@@I~
        {                                                          //~v@@@I~
        	UView.showToast("Gmsg action not Found id="+PactionID);//~v@@@I~
            return;                                                //~v@@@I~
        }                                                          //~v@@@I~
        Tables tb=StblAction[idx];
        String nm=tb.name;//~v@@@I~
        if (swPortrait)                                   //~v@@@I~//~9C02R~
        {                                                          //~v@@@I~
        	Point p=(Point)tb.getObject();                         //~v@@@I~
        	drawTextHL(nm,p.x,p.y);                       //~v@@@R~//~9C02R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
      	  if (swVerticalB2T)	//landscape english                //~0216I~
    		drawHL(Popt,nm);	//re-call setMsgbarHorizontal(Pmsg) ,easy udate//~0216I~
          else                                                     //~0216I~
          {                                                        //~0216I~
        	float[] fpos=(float[])tb.getObject();                  //~v@@@I~
        	drawTextHL(nm,fpos);                          //~v@@@R~//~9C02R~
          }                                                        //~0216I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //********************************************                 //~9C06I~
    private void drawHL(int Popt,int PactionID,int Pplayer)        //~9C06I~
    {                                                              //~9C06I~
        if (Dump.Y) Dump.println("GMsg.drawHL actionid="+PactionID+",player="+Pplayer);//~9C06I~//~9C10R~
    	int idx=Tables.find(StblAction,PactionID);                 //~9C06I~
        if (idx==-1)                                               //~9C06I~
        {                                                          //~9C06I~
        	UView.showToast("Gmsg action not Found id="+PactionID);//~9C06I~
            return;                                                //~9C06I~
        }                                                          //~9C06I~
        Tables tb=StblAction[idx];                                 //~9C06I~
    	int eswn= Accounts.playerToEswn(Pplayer);                   //~9C06I~
        String msg=AG.resource.getString(R.string.Info_ActionESWN, GConst.nameESWN[eswn],tb.name);//~9C06R~
	    drawHL(Popt,msg);                                          //~9C06I~
    }                                                              //~9C06I~
    //********************************************                 //~0218I~
    private void drawHLName(int Popt,int PactionID,int Pplayer)    //~0218I~
    {                                                              //~0218I~
        if (Dump.Y) Dump.println("GMsg.drawHLName actionid="+PactionID+",player="+Pplayer);//~0218I~//~va66R~
//        int idx=Tables.find(StblAction,PactionID);                 //~0218I~//~va66R~
//        if (idx==-1)                                               //~0218I~//~va66R~
//        {                                                          //~0218I~//~va66R~
//            UView.showToast("Gmsg action not Found id="+PactionID);//~0218I~//~va66R~
//            return;                                                //~0218I~//~va66R~
//        }                                                          //~0218I~//~va66R~
//        Tables tb=StblAction[idx];                                 //~0218I~//~va66R~
//        int eswn= Accounts.playerToEswn(Pplayer);                  //~0218I~//~va66R~
//        String yn=AG.aAccounts.currentEswnToAccountName(eswn);     //~0218I~//~va66R~
//        String msg=AG.resource.getString(R.string.Info_ActionEswnWithName, GConst.nameESWN[eswn],tb.name,yn);//~0218I~//~va66R~
        String msg=getHLName(Popt,PactionID,Pplayer);              //~va66I~
	    drawHL(Popt,msg);                                          //~0218I~
    }                                                              //~0218I~
    //********************************************                 //~va66I~
    public String getHLName(int Popt,int PactionID,int Pplayer)    //~va66I~
    {                                                              //~va66I~
        if (Dump.Y) Dump.println("GMsg.getHLName actionid="+PactionID+",player="+Pplayer);//~va66I~
    	int idx=Tables.find(StblAction,PactionID);                 //~va66I~
        if (idx==-1)                                               //~va66I~
        {                                                          //~va66I~
        	UView.showToast("Gmsg action not Found id="+PactionID);//~va66I~
            return "UnknownAction";                                                //~va66I~
        }                                                          //~va66I~
        Tables tb=StblAction[idx];                                 //~va66I~
    	int eswn= Accounts.playerToEswn(Pplayer);                  //~va66I~
		String yn=AG.aAccounts.currentEswnToAccountName(eswn);     //~va66I~
        String msg=AG.resource.getString(R.string.Info_ActionEswnWithName, GConst.nameESWN[eswn],tb.name,yn);//~va66I~
        if (Dump.Y) Dump.println("GMsg.getHLName msg="+msg);       //~va66I~
        return msg;                                                //~va66I~
    }                                                              //~va66I~
    //********************************************                 //~9C02M~
    public static void showHL(int Popt,String Pmsg)                //~9C02M~
    {                                                              //~9C02M~
        AG.aGMsg.drawHL(Popt,Pmsg);                                //~9C02M~
    }                                                              //~9C02M~
    //********************************************                 //~9C02I~
    private void drawHL(int Popt,String Pmsg)                      //~9C02R~
    {                                                              //~9C02I~
        if (Dump.Y) Dump.println("GMsg.drawHL msg="+Pmsg);         //~9C02I~//~9C10R~
        if (table==null)    //Instrumetation test                  //~va60R~
        	return;                                                //~va60I~
        Rect r;                                                    //~9C02I~
        if (swPortrait)                                            //~9C02R~
        {                                                          //~9C02I~
            setMsgbarHorizontal(Pmsg);                             //~9C02R~
            r=new Rect(rectDraw);                                  //~9C02I~
            r.left-=HL_MARGIN;                                     //~9C02I~
            r.right+=HL_MARGIN;                                    //~9C02I~
			drawTextHL(Pmsg,xxDraw,yyDraw,paintHL,r);               //~9C02I~
        }                                                          //~9C02I~
        else                                                       //~9C02I~
        {                                                          //~9C02I~
            setMsgbarVertical(Pmsg);                             //~9C02I~
//    	  if (swVerticalB2T)	//landscape english                //~0215M~//~var3R~
      	  if (swVerticalB2T && !swVerticalT2B)	//landscape english but not KO//~var3I~
          {                                                        //~0215I~
            r=new Rect(rectB2TText);                               //~0215R~
            r.left-=HL_MARGIN;                                     //~0215I~
            r.right+=HL_MARGIN;                                    //~0215I~
        	AG.aGMsg.drawTextHLB2T(Pmsg,fposDraw,paintHL,r);       //~0215M~
          }                                                        //~0215I~
          else                                                     //~0215M~
          {                                                        //~0215I~
            r=new Rect(rectDraw);                                  //~9C02I~
            r.top-=HL_MARGIN;                                      //~9C02I~
            r.bottom+=HL_MARGIN;                                   //~9C02I~
           if (msgVertical!=null) //output of setMsgbarVertical    //~var5I~
        	AG.aGMsg.drawTextHL(msgVertical,fposDraw,paintHL,r);   //~var5I~
           else                                                    //~var5I~
        	AG.aGMsg.drawTextHL(Pmsg,fposDraw,paintHL,r);              //~9C02I~
          }                                                        //~0215I~
        }                                                          //~9C02I~
    }                                                              //~9C02I~
    //********************************************               //~v@@@I~//~va66R~
    public static String editEswn(int Pmsgid,int Pplayer)//~v@@@I~ //~va66R~
    {                                                            //~v@@@I~//~va66R~
        int eswn=Accounts.playerToEswn(Pplayer);                 //~v@@@I~//~va66R~
        String msg=Utils.getStr(Pmsgid,GConst.nameESWN[eswn]);//~v@@@I~//~va66I~
        if (Dump.Y) Dump.println("GMsg.editEswn player="+Pplayer+"eswn="+eswn+",msg="+msg);//~v@@@I~//~va66R~
        return msg;                                                //~va66R~
    }                                                            //~v@@@I~//~va66R~
//    //********************************************               //~v@@@R~
//    //*with colored background                                   //~v@@@R~
//    //********************************************               //~v@@@R~
//    public static void drawMsgbarHL(String Ptext)                //~v@@@R~
//    {                                                            //~v@@@R~
//        AG.aGMsg.colorHL=COLOR_BG_HL;                            //~v@@@R~
//        drawMsgbar(Ptext);                                       //~v@@@R~
//        AG.aGMsg.colorHL=0;                                      //~v@@@R~
//    }                                                            //~v@@@R~
    //********************************************                 //~v@@@I~
    public static void drawMsgbar(String Ptext)                    //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.drawMsgbar swMainView="+AG.swMainView+",text="+Ptext);//~v@@@R~
      	if (AG.swMainView)	                                       //~v@@@I~
        {                                                          //~v@@@I~
        	MainView.drawMsg(Ptext);                               //~v@@@I~
            return;                                                //~v@@@I~
        }                                                          //~v@@@I~
        if (AG.aGMsg==null)                                        //~v@@@I~
        {                                                          //~v@@@I~
        	UView.showToast(Ptext);                                      //~v@@@I~
        	return;                                                //~v@@@I~
        }                                                          //~v@@@I~
        synchronized(AG.aGMsg)                                         //~v@@@I~
        {                                                          //~v@@@I~
        if (Ptext.length()==0)                                     //~v@@@I~
        {                                                          //~v@@@I~
    		Graphics.drawRect(AG.aGMsg.rectMsgbar,COLOR_BG_TABLE); //~v@@@R~
            AG.aGMsg.swDrawn=false;                                         //~v@@@I~
//          return;                                                //~v@@@R~
        }                                                          //~v@@@I~
        else                                                       //~v@@@I~
        {                                                          //~v@@@I~
            if (AG.aGMsg.swPortrait)                               //~v@@@R~
                AG.aGMsg.drawMsgbarHorizontal(Ptext);              //~v@@@R~
            else                                                   //~v@@@R~
                AG.aGMsg.drawMsgbarVertical(Ptext);                //~v@@@R~
        }                                                          //~v@@@I~
        }                                                          //~v@@@I~
        GameView.repaint();                                        //~v@@@I~
    }                                                              //~v@@@I~
    //********************************************                 //~v@@@I~
    private void setMsgbarHorizontal(String Pmsg)                  //~v@@@R~
    {                                                              //~v@@@I~
    	int strsz=adjustTextSizeHorizontal(Pmsg);	//setup paint  //~v@@@R~
        int xx=rectMsgbar.left+(msgbarLen-strsz)/2;                //~v@@@I~
        int yy=rectMsgbar.bottom;                                  //~v@@@I~
//      yy-=paint.getFontMetrics().descent;                        //~v@@@R~
		Rect r=new Rect();                                         //~v@@@I~
        paint.getTextBounds(Pmsg,0,Pmsg.length(),r);               //~v@@@I~
        yy-=r.bottom;                                              //~v@@@I~
        paint.setStyle(Paint.Style.FILL_AND_STROKE);               //~v@@@R~
//      paint.setStrokeWidth((float)STROKE_WIDTH);                 //~v@@@R~
        paint.setColor(COLOR_MSGBAR_TEXT);                         //~v@@@I~
//      Graphics.drawText(rectMsgbar,COLOR_BG_TABLE,Pmsg,xx,yy,paint);//~v@@@R~
        xxDraw=xx;                                                 //~v@@@I~
        yyDraw=yy;                                                 //~v@@@I~
        rectDraw=new Rect(xx,rectMsgbar.top,xx+strsz,rectMsgbar.bottom);//~v@@@R~
        if (Dump.Y) Dump.println("GMsg.drawMsgbarHorizontal msg="+Pmsg+",xx="+xx+",yy="+yy+",strsz="+strsz+",bgRect="+r.toString());//~v@@@I~
    }                                                              //~v@@@I~
    //********************************************                 //~v@@@I~
    private void drawMsgbarHorizontal(String Pmsg)                 //~v@@@R~
    {                                                              //~v@@@I~
        int color=colorHL==0 ? COLOR_BG_TABLE : colorHL;           //~v@@@I~
	    drawMsgbarHorizontalWithBG(Pmsg,color);                        //~v@@@I~
    }                                                              //~v@@@I~
    //********************************************                 //~v@@@I~
    private void drawMsgbarHorizontalWithBG(String Pmsg,int PbgColor)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.drawMsgbarHorizontal bgColor="+Integer.toHexString(PbgColor));//~v@@@R~
    	setMsgbarHorizontal(Pmsg);                                 //~v@@@R~
        drawText(rectMsgbar,PbgColor,Pmsg,xxDraw,yyDraw,paint);    //~v@@@R~
    }                                                              //~v@@@I~
    //********************************************                 //~v@@@I~
    private void drawTextHL(String Ptext,int Pxx,int Pyy)         //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.drawTextHL horizontal text="+Ptext+",rect="+rectTextHL.toString()+",xx="+Pxx+",yy="+Pyy);//~v@@@I~//~9C10R~
        drawTextHL(Ptext,Pxx,Pyy,paintHL,rectTextHL);//~v@@@I~       //~9C02R~
    }                                                              //~v@@@I~
    //********************************************                 //~9C02I~
    private void drawTextHL(String Ptext,int Pxx,int Pyy,Paint Ppaint,Rect PrectText)//~9C02I~
    {                                                              //~9C02I~
        if (Dump.Y) Dump.println("GMsg.drawTextHL text="+Ptext+",horizontal rect="+rectTextHL.toString()+",xx="+Pxx+",yy="+Pyy+",rectText="+PrectText.toString());//~9C02I~//~9C10R~
        Graphics.drawText(rectMsgbar,COLOR_BG_TABLE,Ptext,Pxx,Pyy,Ppaint,PrectText,COLOR_BG_HL);//~9C02I~
        swDrawn=true;                                              //~9C02I~
    }                                                              //~9C02I~
    //********************************************                 //~v@@@I~
    private void drawText(Rect Prect,int PbgColor,String Pmsg,int Pxx,int Pyy,Paint Ppaint)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.drawText horizontal rect="+Prect.toString()+",xx="+Pxx+",yy="+Pyy);//~v@@@R~
        Graphics.drawText(Prect,PbgColor,Pmsg,Pxx,Pyy,Ppaint);     //~v@@@I~
        swDrawn=true;                                              //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@M~
    private int adjustTextSizeHorizontal(String Pmsg)              //~v@@@I~
    {                                                              //~v@@@M~
//        paint=new Paint();                                       //~v@@@R~
//        int maxH=(int)((rectMsgbar.bottom-rectMsgbar.top)*MSGH_ALLOWANCE);//~v@@@R~
//        int sz=TEXT_SIZE,strsz;                                  //~v@@@R~
//        Rect r=new Rect();                                       //~v@@@R~
//        int ctr=Pmsg.length();                                   //~v@@@R~
//        for (;;)                                                 //~v@@@R~
//        {                                                        //~v@@@R~
//            paint.getTextBounds(Pmsg,0,ctr,r);                   //~v@@@R~
//            int hh=r.bottom-r.top;                               //~v@@@R~
//            paint.setTextSize(sz);                               //~v@@@R~
//            strsz=(int)paint.measureText(Pmsg);                  //~v@@@R~
//            if (Dump.Y) Dump.println("GMsg.adjustTextSizeHorizontal sz="+sz+",strsz="+strsz+",rectMsgbar="+rectMsgbar.toString()+",maxH="+maxH+",textBounds="+r.toString()+",magbarLen="+msgbarLen);//~v@@@R~
//            if (strsz<msgbarLen && hh<maxH)                      //~v@@@R~
//                break;                                           //~v@@@R~
//            sz-=4;                                               //~v@@@R~
//        }                                                        //~v@@@R~
//        if (Dump.Y) Dump.println("GMsg.adjustTextSizeHorizontal sz="+sz+",strsz="+strsz);//~v@@@R~
        int sz,strsz;                                              //~v@@@I~
        //********************                                     //~v@@@I~
        paint=new Paint();                                         //~v@@@I~
        int maxH=(int)((rectMsgbar.bottom-rectMsgbar.top)*MSGH_ALLOWANCE);//~v@@@I~
//      sz=(int)(maxH/pxToSp);                                     //~v@@@R~
        sz=maxH;                                                   //~v@@@I~
        Rect r=new Rect();                                         //~v@@@I~
        int ctr=Pmsg.length();                                     //~v@@@I~
        for (;;)                                                   //~v@@@I~
        {                                                          //~v@@@I~
//          paint.getTextBounds(Pmsg,0,ctr,r);                     //~v@@@I~
//          int hh=r.bottom-r.top;                                 //~v@@@I~
            paint.setTextSize(sz);                                 //~v@@@I~
            strsz=(int)paint.measureText(Pmsg);                    //~v@@@I~
            if (Dump.Y) Dump.println("GMsg.adjustTextSizeHorizontal sz="+sz+",strsz="+strsz+",rectMsgbar="+rectMsgbar.toString()+",maxH="+maxH+",magbarLen="+msgbarLen);//~v@@@I~
//          if (strsz<msgbarLen && hh<maxH)                        //~v@@@I~
            if (strsz<msgbarLen)                                   //~v@@@I~
                break;                                             //~v@@@I~
            if (sz<=0)                                             //~va99I~
            {                                                      //~va99I~
        		sz=maxH;                                           //~va99I~
                break;                                             //~va99I~
            }                                                      //~va99I~
            sz-=4;                                                 //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.adjustTextSizeHorizontal sz="+sz+",strsz="+strsz);//~v@@@I~
        return strsz;                                              //~v@@@I~
    }                                                              //~v@@@M~
    //*********************************************************    //~0215I~
    private int adjustTextSizeVerticalB2T(String Pmsg)             //~0215I~
    {                                                              //~0215I~
        int sz,strsz;                                              //~0215I~
        //********************                                     //~0215I~
        if (Dump.Y) Dump.println("GMsg.adjustTextSizeVerticalB2T rectMsgbar="+rectMsgbar.toString());//~0215I~
        paint=new Paint();                                         //~0215I~
//      int maxH=(int)((rectMsgbar.bottom-rectMsgbar.top)*MSGH_ALLOWANCE);//~0215I~
        int maxH=(int)((rectMsgbar.right-rectMsgbar.left)*MSGH_ALLOWANCE);//~0215I~
        sz=maxH;                                                   //~0215I~
        Rect r=new Rect();                                         //~0215I~
        int ctr=Pmsg.length();                                     //~0215I~
        for (;;)                                                   //~0215I~
        {                                                          //~0215I~
            paint.setTextSize(sz);                                 //~0215I~
            strsz=(int)paint.measureText(Pmsg);                    //~0215I~
            if (Dump.Y) Dump.println("GMsg.adjustTextSizeVerticalB2T sz="+sz+",strsz="+strsz+",maxH="+maxH+",magbarLen="+msgbarLen);//~0215I~
            if (strsz<msgbarLen)                                   //~0215I~
                break;                                             //~0215I~
            if (sz<=0)                                             //~va99I~
            {                                                      //~va99I~
        		sz=maxH;                                           //~va99I~
                break;                                             //~va99I~
            }                                                      //~va99I~
            sz-=4;                                                 //~0215I~
        }                                                          //~0215I~
        if (Dump.Y) Dump.println("GMsg.adjustTextSizeVerticalB2T sz="+sz+",strsz="+strsz);//~0215I~
        return strsz;                                              //~0215I~
    }                                                              //~0215I~
//    //********************************************               //~v@@@R~
//    private void drawMsgbarVertical(String Pmsg)                 //~v@@@R~
//    {                                                            //~v@@@R~
//        int charH=adjustTextSizeVertical(Pmsg); //setup paint    //~v@@@R~
//        int ctr=Pmsg.length();                                   //~v@@@R~
//        int strsz=charH*ctr;                                     //~v@@@R~
//        int xx=rectMsgbar.left;                                  //~v@@@R~
//        int yy=rectMsgbar.top+(rectMsgbar.bottom-rectMsgbar.top-strsz)/2;//~v@@@R~
//        yy+=charH-paint.getFontMetrics().descent;                //~v@@@R~
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);             //~v@@@R~
////      paint.setStrokeWidth((float)STROKE_WIDTH);               //~v@@@R~
//        paint.setColor(COLOR_MSGBAR_TEXT);                       //~v@@@R~
//        int strw=(int)paint.measureText(Pmsg);                   //~v@@@R~
//        int charW=strw/ctr;                                      //~v@@@R~
//        if (msgbarSize>charW)                                    //~v@@@R~
//            xx+=(msgbarSize-charW)/2;                            //~v@@@R~
//        float[] fpos=new float[ctr*2];                           //~v@@@R~
//        for (int ii=0;ii<ctr;ii++)                               //~v@@@R~
//        {                                                        //~v@@@R~
//            fpos[ii*2]=xx;                                       //~v@@@R~
//            fpos[ii*2+1]=yy+charH*ii;                            //~v@@@R~
//        }                                                        //~v@@@R~
////        canvas = Graphics.lockCanvas(rectMsgbar);              //~v@@@R~
////        try                                                    //~v@@@R~
////        {                                                      //~v@@@R~
////            Graphics.drawRect(canvas,rectMsgbar,COLOR_BG_TABLE);//~v@@@R~
////            Graphics.drawText(canvas,Pmsg,fpos,paint);         //~v@@@R~
////        }                                                      //~v@@@R~
////        catch (Exception e)                                    //~v@@@R~
////        {                                                      //~v@@@R~
////            Dump.println(e, "GMsg.drawMsgbarVertical");        //~v@@@R~
////        }                                                      //~v@@@R~
////        Graphics.unlockCanvas(canvas);                         //~v@@@R~
//        Graphics.drawText(rectMsgbar,COLOR_BG_TABLE,Pmsg,fpos,paint);//~v@@@R~
//    }                                                            //~v@@@R~
    //********************************************                 //~v@@@I~
    private void setMsgbarVertical(String Pmsg)                    //~v@@@R~
    {                                                              //~v@@@I~
	    if (Dump.Y) Dump.println("GMsg.setMsgBarVertical swVerticalB2T="+swVerticalB2T+",msg="+Pmsg);//~va66I~
        msgVertical=null;                                          //~var5I~
    	swVerticalT2B=swVerticalB2T && isDBCSText(Pmsg); //!JP and landscape, chk ko//~var3I~
      if (!swVerticalT2B)                                          //~var3I~
    	if (swVerticalB2T)	//landscape english                    //~0215I~
        {                                                          //~0215I~
		    setMsgbarVerticalB2T(Pmsg);                            //~0215I~
            return;                                                //~0215I~
        }                                                          //~0215I~
        msgVertical=replaceVertical(Pmsg);	                       //~var5I~
        if (msgVertical!=null)                                    //~var5I~
            Pmsg=msgVertical;                                       //~var5I~
    	int charH=adjustTextSizeVertical(Pmsg);	//setup paint      //~v@@@R~//~var5R~
        int ctr=Pmsg.length();                                     //~v@@@I~
        int xx=rectMsgbar.left;                                    //~v@@@I~
        int yy=rectMsgbar.top+(rectMsgbar.bottom-rectMsgbar.top-charH*ctr)/2;//~v@@@I~
        int yy0=yy-(int)paint.getFontMetrics().descent;                 //~v@@@R~
        int descent=(int)paint.getFontMetrics().descent;                  //~v@@@R~//~0216R~
        yy+=charH-descent;                                         //~0216I~
        paint.setStyle(Paint.Style.FILL_AND_STROKE);               //~v@@@I~
        paint.setColor(COLOR_MSGBAR_TEXT);                         //~v@@@I~
        int strw=(int)paint.measureText(Pmsg);                     //~v@@@I~
//      int charW=strw/ctr;                                        //~v@@@I~//~0218R~
//      if (msgbarSize>charW)                                      //~v@@@I~//~0218R~
//      	xx+=(msgbarSize-charW)/2-ADJUSTVJ;                              //~v@@@I~//~0216R~//~0218R~
        int charW=msgbarSize;                                      //~0218I~
        if (Dump.Y) Dump.println("Gmsg.setMsgbarVertical msgbarSize="+msgbarSize+",charW="+charW);//~0216I~//~var5R~
        float[] fpos=new float[ctr*2];                             //~v@@@I~
        int totalShiftY=0;                                         //~var4I~
        for (int ii=0;ii<ctr;ii++)                                 //~var4I~
        {                                                          //~var4I~
            int wwch=(int)paint.measureText(Pmsg,ii,ii+1);         //~var4I~
	        Point shift;                                           //~var4I~
            shift=chkCharShift(Pmsg,ii,charW,charH);               //~var4I~
          if (shift.x==0 && shift.y==0)                            //~var4I~
            if (wwch<charW)                                        //~var4I~
            {                                                      //~var4I~
                shift=new Point((charW-wwch)/2,0);                 //~var4I~
//              int shiftDQ=chkCharShiftDQ(Pmsg,ii,charH);         //+var7R~
//            if (shiftDQ!=0)                                      //+var7R~
//              shift.y=-shiftDQ;                                  //+var7R~
//            else                                                 //+var7R~
	            shift.y=-chkCharShiftSbcs(Pmsg,ii,charH);          //~var4I~
            }                                                      //~var4I~
            totalShiftY+=shift.y;                                  //~var4I~
	        if (Dump.Y) Dump.println("GMsg.setTextHeightVertical getTextBounds ii="+ii+",shift="+shift+",totalShiftY="+totalShiftY);//~var4I~
        }                                                          //~var4I~
        yy+=totalShiftY/2;                                         //~var4I~
	    if (Dump.Y) Dump.println("GMsg.setTextHeightVertical getTextBounds totalShiftY="+totalShiftY+",yy="+yy);//~var6I~
        for (int ii=0;ii<ctr;ii++)                                 //~v@@@I~
        {                                                          //~v@@@I~
            int wwch=(int)paint.measureText(Pmsg,ii,ii+1);         //~0215R~
	        Point shift;                                           //~0215I~
            shift=chkCharShift(Pmsg,ii,charW,charH);               //~0218I~
          if (shift.x==0 && shift.y==0)                            //~0218I~
            if (wwch<charW)                                      //~0215I~//~0216R~
            {                                                      //~0216I~
//          	if (wwch<charW/2)                                  //~0216I~
//              	shift=new Point((charW-wwch)/3,0);             //~0216I~
//              else                                               //~0216I~
                	shift=new Point((charW-wwch)/2,0);                 //~0215I~//~0216R~
//              int shiftDQ=chkCharShiftDQ(Pmsg,ii,charH);         //+var7R~
//            if (shiftDQ!=0)                                      //+var7R~
//              shift.y=-shiftDQ;                                  //+var7R~
//            else                                                 //+var7R~
	            shift.y=-chkCharShiftSbcs(Pmsg,ii,charH);          //~0216R~
            }                                                      //~0216I~
//          else                                                   //~0215I~//~0218R~
//              shift=chkCharShift(Pmsg,ii,charW,charH);           //~0215R~//~0218R~
        	fpos[ii*2]=xx+shift.x;                                         //~v@@@I~//~0215R~
//          fpos[ii*2+1]=yy+charH*ii-shift.y;                              //~v@@@I~//~0215R~//~var6R~
          int shiftDQ=chkCharShiftDQ(Pmsg,ii,charH);               //+var7I~
          if (shiftDQ!=0)                                          //+var7I~
            fpos[ii*2+1]=yy+charH*ii+shiftDQ;                      //+var7I~
          else                                                     //+var7I~
    	  if (swCharShiftSbcsType2) //quotation                               //~var6I~
            fpos[ii*2+1]=yy+charH*ii;                              //~var6M~
          else                                                     //~var6I~
            fpos[ii*2+1]=yy+charH*ii-shift.y;                      //~var6M~
            yy-=shift.y;                                           //~var4I~
	        if (Dump.Y) Dump.println("GMsg.setMsgbarVertical getTextBounds ii="+ii+",descent="+descent+",measure=wwch="+wwch+",charW="+charW+",shift="+shift+",xx="+xx);//~0215I~//~0216R~//~va66R~//~var5R~//~var6R~
	        if (Dump.Y) Dump.println("GMsg.setMsgbarVertical yy="+yy+",fpos="+fpos[ii*2]+","+fpos[ii*2+1]);//~var4I~//~var5R~//~var6R~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.setMsgbarVertical fpos="+Arrays.toString(fpos));//~v@@@I~//~0218R~//~var5R~
//      Graphics.drawText(rectMsgbar,COLOR_BG_TABLE,Pmsg,fpos,paint);//~v@@@R~
        fposDraw=fpos;                                             //~v@@@I~
        rectDraw=new Rect(rectMsgbar.left,yy0,rectMsgbar.right,yy0+charH*(ctr));//~v@@@R~//~var4R~
        if (Dump.Y) Dump.println("GMsg.setMsgbarVertical exit msgVertical="+msgVertical);//~var5I~
    }                                                              //~v@@@I~
    //********************************************                 //~0215I~
    //*adjust char spacinf for for DBCS 。、                       //~var6I~
    //********************************************                 //~var6I~
    private Point chkCharShift(String Pmsg,int Ppos,int PcharW,int PcharH)//~0215R~
    {                                                              //~0215I~
    	int rcW=0,rcH=0;                                           //~0215R~
    	char ch=Pmsg.charAt(Ppos);                                 //~0215I~
    	if (SHIFTCHAR.indexOf(ch)>=0)                              //~0215I~//~0216R~
        {                                                          //~0215I~
        	rcW=PcharW/2;                                          //~0215R~
        	rcH=PcharH/2;                                          //~0215I~
        }                                                          //~0215I~
        if (Dump.Y) Dump.println("GMsg.chkCharShift rcW="+rcW+",ch="+ch+",rcH="+rcH+",charH="+PcharH+",charW="+PcharW);//~0215R~
        return new Point(rcW,rcH);                                 //~0215R~
    }                                                              //~0215I~
    //********************************************                 //~var7I~
    //*adjust char for DBCS double quote                           //+var7R~
    //********************************************                 //~var7I~
    private int  chkCharShiftDQ(String Pmsg,int Ppos,int PcharH)   //~var7I~
    {                                                              //~var7I~
    	int shiftH=0;                                              //+var7R~
    	char ch=Pmsg.charAt(Ppos);                                 //~var7I~
        if (ch==SHIFTCHAR_DQL)    //left dquote                    //~var7I~
        {                                                          //~var7I~
        	shiftH=PcharH/2;      //shift down                       //+var7R~
        }                                                          //~var7I~
//        else                                                     //+var7R~
//        if (ch==SHIFTCHAR_DQR)   //right dquote                  //+var7R~
//        {                                                        //+var7R~
//            rcH=PcharH/2;                                        //+var7R~
//            swCharShiftSbcsType2=true;                           //+var7R~
//        }                                                        //+var7R~
        if (Dump.Y) Dump.println("GMsg.chkCharShiftDQ pos="+Ppos+",ch="+ch+",shiftH="+shiftH+",PcharH="+PcharH);//+var7R~
        return shiftH;                                                //~var7I~
    }                                                              //~var7I~
    //***********************************************************                 //~0216I~//~var6R~
    //*adjust char spacinf for for SBCS  .,(space),"(Quate)        //~var6I~
    //***********************************************************  //~var6I~
    private int chkCharShiftSbcs(String Pmsg,int Ppos,int PcharH)  //~0216I~
    {                                                              //~0216I~
		Rect r=new Rect();                                         //~0216I~
        paint.getTextBounds(Pmsg,Ppos,Ppos+1,r);                       //~0216I~
        if (Dump.Y) Dump.println("GMsg.chkCharShiftSbcs rect="+r); //~var6I~
        int h=r.bottom-r.top;                                      //~0216I~
    	int rcH=0;                                                 //~0216I~
    	char ch=Pmsg.charAt(Ppos);                                 //~0216R~
        int ich=ch;                                                //~0224I~
        if (ich>0x7f)	//not sbcs                                 //~0224I~
        	return 0;                                               //~0224I~
//  	if (SshiftCharSbcs.indexOf(ch)>=0)                         //~0216R~
//      	rcH=PcharH*2/3;                                        //~0216R~
        if (h<PcharH/2)                                            //~0216R~
        	rcH=(PcharH-h)/2;                                      //~0216I~
    	if (SHIFTUP.indexOf(ch)>=0)                                //~0216I~
            rcH=-rcH;                                              //~0216I~
    	swCharShiftSbcsType2=SHIFTUP2.indexOf(ch)>=0;             //~var6I~
        if (Dump.Y) Dump.println("GMsg.chkCharShiftSbcs ch="+Pmsg.charAt(Ppos)+",swCharShiftSbcsType2="+swCharShiftSbcsType2+",h="+h+",rcH="+rcH+",charH="+PcharH);//~0216I~//~0224R~//~var6R~
        return rcH;                                                //~0216I~
    }                                                              //~0216I~
    //********************************************                 //~0215I~
    //*Bottom to Top                                               //~0215I~
    //********************************************                 //~0215I~
    private void setMsgbarVerticalB2T(String Pmsg)                 //~0215I~
    {                                                              //~0215I~
        if (Dump.Y) Dump.println("drawMsgbarVerticalB2T");         //~0215I~
//  	int charH=adjustTextSizeVertical(Pmsg);	//setup paint      //~0215I~
    	int strw=adjustTextSizeVerticalB2T(Pmsg);	//setup paint  //~0215I~
        int ctr=Pmsg.length();                                     //~0215I~
        int xx=rectMsgbar.left;                                    //~0215I~
//      int yy=rectMsgbar.top+(rectMsgbar.bottom-rectMsgbar.top-charH*ctr)/2;//~0215R~
        int yy=rectMsgbar.top+(rectMsgbar.bottom-rectMsgbar.top-strw)/2;//~0215I~
//      int yy0=yy-(int)paint.getFontMetrics().descent;            //~0215R~
        int yy0=yy;  //distance from top                           //~0215R~
//      int yy0=yy-(int)paint.getFontMetrics().descent;            //~0215I~
//      yy+=charH-paint.getFontMetrics().descent;                  //~0215I~
        paint.setStyle(Paint.Style.FILL_AND_STROKE);               //~0215I~
        paint.setColor(COLOR_MSGBAR_TEXT);                         //~0215I~
//      int strw=(int)paint.measureText(Pmsg);                     //~0215I~
//      int charW=strw/ctr;                                        //~0215I~
//      if (msgbarSize>charW)                                      //~0215I~
//      	xx+=(msgbarSize-charW)/2;                              //~0215I~
//      float[] fpos=new float[ctr*2];                             //~0215I~
        float[] fpos=new float[2];                                 //~0215I~
//      for (int ii=0;ii<ctr;ii++)                                 //~0215I~
//      {                                                          //~0215I~
//      	fpos[ii*2]=xx;                                         //~0215I~
//          fpos[ii*2+1]=yy+charH*ii;                              //~0215I~
//      }                                                          //~0215I~
//      if (Dump.Y) Dump.println("drawMsgbarVertical fpos="+Arrays.toString(fpos));//~0215I~
      	fpos[0]=yy0;                                               //~0215R~
      	fpos[1]=rectMsgbar.right-rectMsgbar.left-paint.getFontMetrics().descent;//~0215R~
        fposDraw=fpos;                                             //~0215I~
//      rectDraw=new Rect(rectMsgbar.left,yy0,rectMsgbar.right,yy0+charH*(ctr));//~0215I~
        rectDraw=new Rect(rectMsgbar.left,yy0,rectMsgbar.right,yy0+strw);//virtical//~0215R~
        rectB2TText=new Rect(rectB2T);                              //~0215I~
        rectB2TText.left=yy0;                                      //~0215I~
        rectB2TText.right=yy0+strw;                                //~0215I~
    }                                                              //~0215I~
    //********************************************                 //~v@@@I~
//  private void drawMsgbarVertical(String Pmsg)                   //~v@@@R~//~va99R~
    protected void drawMsgbarVertical(String Pmsg)	//protected for IT Mock//~va99I~
    {                                                              //~v@@@I~
        int color=colorHL==0 ? COLOR_BG_TABLE : colorHL;           //~v@@@I~
	    drawMsgbarVerticalWidthBG(Pmsg,color);                     //~v@@@I~
    }                                                              //~v@@@I~
    //********************************************                 //~v@@@I~
    private void drawMsgbarVerticalWidthBG(String Pmsg,int PbgColor)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.drawMsgbarVerticalWithBG bgColor="+Integer.toHexString(PbgColor));//~v@@@I~
	    setMsgbarVertical(Pmsg);                                   //~v@@@R~
//    if (swVerticalB2T)	//landscape english                    //~0215I~//~var3R~
      if (swVerticalB2T && !swVerticalT2B)	//landscape english    //~var3I~
        drawTextB2T(PbgColor,Pmsg,fposDraw,paint);      //~0215I~
      else                                                         //~0215I~
      {                                                            //~var5I~
       if (msgVertical!=null)                                      //~var5I~
        Graphics.drawText(rectMsgbar,PbgColor,msgVertical,fposDraw,paint);//~var5I~
       else                                                        //~var5I~
        Graphics.drawText(rectMsgbar,PbgColor,Pmsg,fposDraw,paint);//~v@@@I~
      }                                                            //~var5I~
        swDrawn=true;                                              //~v@@@I~
    }                                                              //~v@@@I~
    //********************************************                 //~v@@@I~
    private void drawText(Rect Prect,int PbgColor,String Pmsg,float[] Pfpos,Paint Ppaint)//~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.drawText");        //~v@@@I~//~0215R~
        Graphics.drawText(Prect,PbgColor,Pmsg,Pfpos,Ppaint);       //~v@@@I~
        swDrawn=true;                                              //~v@@@I~
    }                                                              //~v@@@I~
    //********************************************                 //~v@@@I~
    private void drawTextHL(String Pmsg,float[] Pfpos)             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.drawTextHL msg="+Pmsg);      //~v@@@I~//~0215R~//~va60R~
        if (table==null)    //Instrumetation test                  //~va60I~
        	return;                                                //~va60I~
        drawTextHL(Pmsg,Pfpos,paintHL,rectTextHL);                   //~9C02I~
    }                                                              //~v@@@I~
    //********************************************                 //~9C02I~
    private void drawTextHL(String Pmsg,float[] Pfpos,Paint Ppaint,Rect PrectText)//~9C02I~
    {                                                              //~9C02I~
        if (Dump.Y) Dump.println("GMsg.drawTextHL rectText="+PrectText.toString());//~9C02I~//~0215R~
        Graphics.drawText(rectMsgbar,COLOR_BG_TABLE,Pmsg,Pfpos,Ppaint,PrectText,COLOR_BG_HL);//~9C02I~
        swDrawn=true;                                              //~9C02I~
    }                                                              //~9C02I~
    //********************************************                 //~0215I~
    private void drawTextB2T(int Pbgcolor,String Pmsg,float[] Pfpos,Paint Ppaint)//~0215R~
    {                                                              //~0215I~
        if (Dump.Y) Dump.println("GMsg.drawTextB2T msg="+Pmsg+",pos="+Arrays.toString(Pfpos));//~0215R~
        Canvas cc=new Canvas(bmB2T);                               //~0215R~
    	Graphics.drawRect(cc,rectB2T,Pbgcolor);                    //~0215R~
    	Graphics.drawText(cc,Pmsg,Pfpos[0],Pfpos[1],Ppaint);       //~0215I~
        rotateB2T(bmB2T);                                             //~0215I~
    }                                                              //~0215I~
    //********************************************                 //~0215I~
    private void drawTextHLB2T(String Pmsg,float[] Pfpos,Paint Ppaint,Rect PrectText)//~0215I~
    {                                                              //~0215I~
        if (Dump.Y) Dump.println("GMsg.drawTextHLB2T rectText="+PrectText.toString());//~0215I~
        Canvas cc=new Canvas(bmB2T);                               //~0215R~
        Graphics.drawText(cc,rectB2T,COLOR_BG_TABLE,Pmsg,Pfpos[0],Pfpos[1],Ppaint,PrectText,COLOR_BG_HL);//~0215R~
        rotateB2T(bmB2T);                                             //~0215I~
        swDrawn=true;                                              //~0215I~
    }                                                              //~0215I~
    //*********************************************************    //~0215I~
    private void rotateB2T(Bitmap Pbm)                             //~0215I~
    {                                                              //~0215I~
        int degree=270;          //bottom to top                   //~0215R~
		Matrix matrix=new Matrix();                                //~0215I~
        int ww=Pbm.getWidth();                                     //~0215I~
        int hh=Pbm.getHeight();                                    //~0215I~
        if (Dump.Y) Dump.println("GMsg.rotateB2T bitmap ww="+ww+",hh="+hh);//~0215I~
        matrix.setRotate(degree,ww/2,hh/2);                        //~0215I~
//      Bitmap bmr=Bitmap.createBitmap(Pbm,0,0,ww,hh,matrix,true); //~0215R~//~0216R~
        Bitmap bmr=Graphics.createBitmap(Pbm,0,0,ww,hh,matrix,true);//~0216I~
        if (Dump.Y) Dump.println("GMsg.rotateB2T bmr ww="+bmr.getWidth()+",hh="+bmr.getHeight());//~0215I~
        Graphics.drawBitmap(bmr,rectMsgbar.left,rectMsgbar.top);  //~0215I~
        UView.recycle(bmr);                                        //~0215I~
    }                                                              //~0215I~
    //*********************************************************    //~v@@@I~
//    private int adjustTextSizeVertical(String Pmsg)              //~v@@@R~
//    {                                                            //~v@@@R~
//        int charH;                                               //~v@@@R~
//        int sz,strsz;                                            //~v@@@R~
//        Rect r=new Rect();                                       //~v@@@R~
//    //********************                                       //~v@@@R~
//        paint=new Paint();                                       //~v@@@R~
//        int ctr=Pmsg.length();                                   //~v@@@R~
//        sz=(int)(TEXT_SIZE*MSGH_ALLOWANCE);                      //~v@@@R~
//        for (;;)                                                 //~v@@@R~
//        {                                                        //~v@@@R~
//            paint.setTextSize(sz);                               //~v@@@R~
//            paint.getTextBounds(Pmsg,0,ctr,r);                   //~v@@@R~
//            charH=r.bottom-r.top;                                //~v@@@R~
//            if (Dump.Y) Dump.println("GMsg.adjustTextSizeVertical sz="+sz+",charH="+charH+",len="+charH*ctr+",boundsRect="+r.toString());//~v@@@R~
//            if (charH*ctr<msgbarLen)                             //~v@@@R~
//            {                                                    //~v@@@R~
//                paint.setTextSize(sz-4);                         //~v@@@R~
//                break;                                           //~v@@@R~
//            }                                                    //~v@@@R~
//            sz-=4;                                               //~v@@@R~
//        }                                                        //~v@@@R~
//        if (Dump.Y) Dump.println("GMsg.adjustTextSizeVertical sz="+sz+",charH="+charH+",len="+charH*ctr);//~v@@@R~
//        return charH;                                            //~v@@@R~
//    }                                                            //~v@@@R~
    //*********************************************************    //~v@@@I~
    private int adjustTextSizeVertical(String Pmsg)                //~v@@@I~
    {                                                              //~v@@@I~
        int sz,charH;                                              //~v@@@R~
    //********************                                         //~v@@@I~
	    if (Dump.Y) Dump.println("GMsg.adjustTextSizeVertical msg="+Pmsg);//~va66I~
        paint=new Paint();                                         //~v@@@I~
        int ctr=Pmsg.length();                                     //~v@@@I~
        sz=TEXT_SIZE;                                              //~v@@@I~
        for (;;)                                                   //~v@@@I~
        {                                                          //~v@@@I~
            charH=getTextHeightVertical(Pmsg,sz);  //0 if width over//~v@@@R~
		    if (Dump.Y) Dump.println("GMsg.adjustTextSizeVertical charH="+charH+",ctr="+ctr+",msgbarLen="+msgbarLen);//~va66I~
            if (charH>0 && charH*ctr<msgbarLen)                    //~v@@@R~
            {                                                      //~v@@@I~
                break;                                             //~v@@@I~
            }                                                      //~v@@@I~
            if (sz<=0)                                             //~va99I~
            {                                                      //~va99I~
        		sz=TEXT_SIZE;                                      //~va99I~
                break;                                             //~va99I~
            }                                                      //~va99I~
            sz-=4;                                                 //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.adjustTextSizeVertical sz="+sz+",charH="+charH+",textH="+charH*ctr);//~v@@@R~
        return charH;                                              //~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private int getTextHeightVertical(String Pmsg,int Psz)//~v@@@I~
    {                                                              //~v@@@I~
	    if (Dump.Y) Dump.println("GMsg.getTextHeightVertical msg="+Pmsg+",sz="+Psz);//~va66I~
    	Rect r=new Rect();                                         //~v@@@I~
        paint.setTextSize(Psz);                                     //~v@@@I~
        int maxh=0;                                                //~v@@@R~
		int charW=(int)(msgbarSize*MSGH_ALLOWANCE);                //~v@@@I~
    	for (int ii=0;ii<Pmsg.length();ii++)                         //~v@@@I~
        {                                                          //~v@@@I~
            paint.getTextBounds(Pmsg,ii,ii+1,r);                   //~v@@@R~
            int h=r.bottom-r.top;                                      //~v@@@I~
            int w=(int)paint.measureText(Pmsg,ii,ii+1);                //~v@@@R~
	        if (Dump.Y) Dump.println("GMsg.getTextHeightVertical getTextBounds ii="+ii+",w="+w+",h="+h+",charW="+charW+",measured="+paint.measureText(Pmsg,ii,ii+1));//~v@@@R~//~va99R~
            if (w>charW)                                           //~v@@@I~
            	return 0;	//try next size                        //~v@@@I~
            maxh=Math.max(h,maxh);                                 //~v@@@I~
        }                                                          //~v@@@I~
        if (Dump.Y) Dump.println("GMsg.getTextHeightVertical maxh="+maxh);//~v@@@I~
        return maxh;                                               //~v@@@R~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void errorMsg(int Popt,String Ptext)                    //~v@@@I~
    {                                                              //~v@@@I~
    	drawMsgbar(Ptext);                                         //~v@@@I~
        if ((Popt & GMSGOPT_ANDTOAST)!=0)                          //~v@@@I~
        	UView.showToast(Ptext);                                //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void errorMsg(int Popt,int Pmsgid)                      //~v@@@I~
    {                                                              //~v@@@I~
//  	drawMsgbar(Utils.getStr(Pmsgid));                          //~v@@@R~
    	errorMsg(Popt,Utils.getStr(Pmsgid));                            //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    public void clearIfTouched(int Pxx,int Pyy)                    //~v@@@I~
    {                                                              //~v@@@I~
    	boolean swClear;                                           //~v@@@I~
    	if (swPortrait)                                            //~v@@@I~
    	    swClear=Pyy>rectMsgbar.top && Pyy<rectMsgbar.bottom;     //~v@@@I~
        else                                                       //~v@@@I~
    	    swClear=Pxx>rectMsgbar.left && Pxx<rectMsgbar.right;   //~v@@@R~
        if (Dump.Y) Dump.println("GMsg.clearIfTouched swPortRait="+swPortrait+",xx="+Pxx+",yy="+Pyy+",swClear="+swClear+",rectMsgbar="+rectMsgbar.toString());//~v@@@R~
        if (swClear)	                                           //~v@@@I~
        	reset();                                               //~v@@@I~
    }                                                              //~v@@@I~
//    //*********************************************************  //~v@@@R~
//    public static void drawMsgBarAll(int Popt,String Pmsg)       //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("GMsg.drawMsgAll msg="+Pmsg);   //~v@@@R~
//        UserAction.showInfoAll(Popt,Pmsg);                       //~v@@@R~
//    }                                                            //~v@@@R~
    //*********************************************************    //~0224I~
    public static String makeSendMsg(int Popt,int Pmsgid)          //~0224I~
    {                                                              //~0224I~
        String rc=Popt+MSG_SEPAPP+Integer.toHexString(Pmsgid);     //~0224R~
        if (Dump.Y) Dump.println("GMsg.makeSendMsg opt="+Popt+",msgid="+Integer.toHexString(Pmsgid)+",rc="+rc);//~0224R~
        return rc;                                                 //~0224I~
    }                                                              //~0224I~
    //*********************************************************    //~0225I~
    public static String makeSendMsg(int Popt,int Pmsgid,String Pdata)//~0303I~
    {                                                              //~0303I~
        String rc=(Popt|GMSGOPT_STRPARM)+MSG_SEPAPP+Integer.toHexString(Pmsgid)+MSG_SEP+Pdata;//~0303R~
        if (Dump.Y) Dump.println("GMsg.makeSendMsg opt="+Popt+",msgid="+Integer.toHexString(Pmsgid)+",data="+Pdata+",rc="+rc);//~0303I~
        return rc;                                                 //~0303I~
    }                                                              //~0303I~
    //*********************************************************    //~0303I~
    public static String makeSendMsgEswn(int Popt,int Pmsgid,int Peswn)//~0225I~
    {                                                              //~0225I~
        String rc=(Popt|GMSGOPT_ESWN)+MSG_SEPAPP+Integer.toHexString(Pmsgid)+MSG_SEPAPP+Peswn;//~0225I~
        if (Dump.Y) Dump.println("GMsg.makeSendMsgEswn opt="+Popt+",msgid="+Integer.toHexString(Pmsgid)+",rc="+rc);//~0225I~
        return rc;                                                 //~0225I~
    }                                                              //~0225I~
    //*********************************************************    //~0303I~
    public static String makeSendMsgEswn(int Popt,int Pmsgid,int Peswn,String Pdata)//~0303I~
    {                                                              //~0303I~
        String rc=(Popt|GMSGOPT_ESWN|GMSGOPT_STRPARM)+MSG_SEPAPP+Integer.toHexString(Pmsgid)+MSG_SEPAPP+Peswn+MSG_SEP+Pdata;//~0303R~
        if (Dump.Y) Dump.println("GMsg.makeSendMsgEswn opt="+Popt+",msgid="+Integer.toHexString(Pmsgid)+",data="+Pdata+",rc="+rc);//~0303I~
        return rc;                                                 //~0303I~
    }                                                              //~0303I~
    //*********************************************************    //~0224I~
    public static int[] parseSendMsg(String Pdata)                 //~0224R~
    {                                                              //~0224I~
        String[] strs=Pdata.split(MSG_SEPAPP,0/*No out size limit*/);//~0224I~
        int sz=strs.length;                                        //~0224I~
        int[] ints=new int[sz];                                    //~0224I~
        for (int ii=0;ii<sz;ii++)                                  //~0224I~
        {                                                          //~0224I~
        	if (ii==1)  //msgid                                    //~0224I~//~0225R~
        		ints[ii]=Utils.parseIntHex(strs[ii],0);            //~0224I~
            else                                                   //~0224I~
        		ints[ii]=Utils.parseInt(strs[ii],0); //opt and eswn//~0224I~//~0225R~
        }                                                          //~0224I~
        if (Dump.Y) Dump.println("GMsg.parseSendMsg ints="+Arrays.toString(ints));//~0224I~
        return ints;                                               //~0224I~
    }                                                              //~0224I~
    //*********************************************************    //~vaf5I~
    public static float adjustTextSize(float PpixTextSize,int Pwidth,String Pmsg)//~vaf5I~
    {                                                              //~vaf5I~
        if (Dump.Y) Dump.println("GMsg.adjustTextSize pixTextSize="+PpixTextSize+",layoutWidth="+Pwidth);//~vaf5I~
        Paint paint=new Paint();                                         //~vaf5I~
        int maxW=(int)(Pwidth*MSGH_ALLOWANCE);                            //~vaf5I~
        int unit= TypedValue.COMPLEX_UNIT_PX;                       //~vaf5I~
        float sz=PpixTextSize;                                     //~vaf5I~
        for (;;)                                                   //~vaf5I~
        {                                                          //~vaf5I~
            paint.setTextSize(sz);                            //~vaf5I~
            int strsz=(int)paint.measureText(Pmsg);                //~vaf5I~
            if (Dump.Y) Dump.println("GMsg.adjustTextSize sz="+sz+",strsz="+strsz);//~vaf5I~
            if (strsz<maxW)                                        //~vaf5I~
                break;                                             //~vaf5I~
            if (sz<=4.0)                                           //~vaf5I~
            	break;                                             //~vaf5I~
            sz-=4;                                                 //~vaf5I~
        }                                                          //~vaf5I~
        if (Dump.Y) Dump.println("GMsg.adjustTextSize rc="+sz);    //~vaf5I~
        return sz;                                                 //~vaf5I~
    }                                                              //~vaf5I~
    //*********************************************************    //~var3I~
    //*for !JAP and !Portrait                                      //~var3I~
    //*rc=true if no SBCS except space and contains DBCS           //~var3I~
    //*********************************************************    //~var3I~
    private static boolean isDBCSText(String Pmsg)                 //~var3I~
    {                                                              //~var3I~
        if (Dump.Y) Dump.println("GMsg.isDBCSText msg="+Pmsg+",isLangKO="+AG.isLangKO);//~var3I~
    	if (!AG.isLangKO)                                          //~var3I~
    		return false;                                          //~var3I~
        int ctr=Pmsg.length();                                     //~var3I~
        boolean swDBCS=false;                                      //~var3I~
        boolean swSBCS=false;                                      //~var3I~
        for (int ii=0;ii<ctr;ii++)                                 //~var3I~
        {                                                          //~var3I~
            int num=Pmsg.codePointAt(ii);                          //~var3R~
//      	if (Dump.Y) Dump.println("GMsg.isDBCSText ii="+ii+",codePoint="+num);//~var3R~
            if (num>=0xac00 && num<=0xd7a3)   //hungle             //~var3R~
            	swDBCS=true;                                       //~var3I~
            else                                                   //~var3I~
            if ((num>=0x41 && num<=0x7f)                           //~var3R~
//          || (num==0x28||num==0x29||num==0x22)//()"              //~var3I~//~var5R~
            )                                                      //~var3I~
            {                                                      //~var3I~
                swSBCS=true;                                       //~var3I~
                break;                                             //~var3I~
            }                                                      //~var3I~
        }                                                          //~var3I~
        boolean rc=!swSBCS && swDBCS;                              //~var3I~
        if (Dump.Y) Dump.println("GMsg.isDBCSText rc="+rc);        //~var3R~
        return rc;                                                 //~var3R~
    }                                                              //~var3I~
    //*********************************************************    //~var5I~
    //*replace char such as () for veritical writing               //~var5I~
    //*********************************************************    //~var5I~
    private static String replaceVertical(String Pmsg)             //~var5I~
    {                                                              //~var5I~
    	String msg=Pmsg;                                           //~var5I~
        if (msg.indexOf('(')>=0)                                   //~var5I~
        	msg=msg.replace('(',(char)0xfe35);                     //~var5R~
        if (msg.indexOf(')')>=0)                                   //~var5I~
        	msg=msg.replace(')',(char)0xfe36);                     //~var5R~
        if (msg.indexOf('（')>=0)                                  //~var5I~
        	msg=msg.replace('（',(char)0xfe35);                    //~var5R~
        if (msg.indexOf('）')>=0)                                  //~var5I~
        	msg=msg.replace('）',(char)0xfe36);                    //~var5R~
        if (msg.indexOf('「')>=0)                                  //~var5I~
        	msg=msg.replace('「',(char)0xfe41);                    //~var5R~
        if (msg.indexOf('」')>=0)                                  //~var5I~
        	msg=msg.replace('」',(char)0xfe42);                    //~var5R~
//        if (AG.isLangKO)         //"." may be used as decimal point//~var6R~
//        {                                                          //~var5I~//~var6R~
//            if (msg.indexOf('.')>=0)                               //~var5I~//~var6R~
//                msg=msg.replace('.','。');   //vertical only       //~var5R~//~var6R~
//            if (msg.indexOf(',')>=0)                               //~var5I~//~var6R~
//                msg=msg.replace(',','、');   //vertical only       //~var5R~//~var6R~
//        }                                                          //~var5I~//~var6R~
        if (msg==Pmsg)                                             //~var5I~
            msg=null;                                              //~var5I~
        if (Dump.Y) Dump.println("GMsg.replaceVertical Pmsg="+Pmsg+",rc="+msg);//~var5I~
        return msg;                                                //~var5I~
    }                                                              //~var5I~
}//class GMsg                                                 //~dataR~//~@@@@R~//~v@@@R~
