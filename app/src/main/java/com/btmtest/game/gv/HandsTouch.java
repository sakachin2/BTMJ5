//*CID://+DATER~: update#= 629;                                    //~v@@@R~//~v@21R~//~9B30R~
//**********************************************************************//~v101I~
//v@21  imageview                                                  //~v@21I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.btmtest.R;
import com.btmtest.utils.Dump;

import java.util.Arrays;

import static com.btmtest.game.GConst.*;                           //~v@@@R~
import static com.btmtest.StaticVars.AG;                           //~v@21I~


public class HandsTouch                                            //~v@@@R~
{                                                                  //~0914I~
//  private static final int ALLOWANCE_V =20;//verticaol allowance //~v@@@R~//~v@21R~
//  private static final int ALLOWANCE_H =20; //horizonta allowance for left/right end piece//~v@@@I~//~v@21R~
    private static final int ALLOWANCE_V =5; //verticaol allowance //~v@21I~
    private static final int ALLOWANCE_H =5; //horizonta allowance for left/right end piece//~v@21I~
//  public static final int COLOR_SELECTED=Color.argb(0xff,0x00,0xc0,0x00);//~v@@@R~
    public static final int COLOR_SELECTED=Color.argb(0xff,0x00,0xc0,0x00);//~v@@@R~
//  public static final int WIDTH_SELECTED=6;                     //~v@@@R~//~v@21R~//~0401R~
//  public static final int WIDTH_SELECTED_OPEN=8;                 //~v@@@I~//~0401R~
    private MJTable table;                                         //~v@@@I~
    private Pieces pieces;//~v@@@I~
    private Canvas canvas;
    private GCanvas gCanvas;//~v@@@I~
    private int pieceW,pieceH;                                     //~v@@@I~
    private Bitmap[] bmsHands;                                     //~v@@@R~
    private Point[] pointsHands;                                   //~v@@@R~
    private int bgColor;
    private Rect rectHands;//~v@@@I~
    private boolean swPortrait;                                    //~v@@@I~
    private int pairRightBottom,pairFacingRight,pairLeftTop;	//boundary of drawOpen//~v@@@I~
    private Hands hands;
    private int ctrHands,dist_move;//~v@@@R~
    private int posOld=-1;                                         //~v@@@R~
    private boolean swMultiSelectionMode=false,enableKan=false;    //~v@21R~
    private boolean[] swSelectedMulti;                                //~v@21I~
//*************************                                        //~v@@@I~
	public HandsTouch(GCanvas Pgcanvas,Hands Phands,Rect Prect)    //~v@@@R~
    {                                                              //~0914I~
    	AG.aHandsTouch=this;                                       //~v@21I~
        gCanvas = Pgcanvas;                                          //~v@@@I~
        hands=Phands;                                              //~v@@@I~
        rectHands=Prect;                                           //~v@@@I~
        table = gCanvas.table;                                     //~v@@@I~
        pieceW=table.handPieceW;                                  //~v@@@R~
        pieceH=table.handPieceH;                                  //~v@@@R~
        dist_move=(pieceW*pieceW+pieceH*pieceH)/2;   // 1/1.4      //~v@21R~
        swSelectedMulti=new boolean[HANDCTR];                          //~v@21I~
        Arrays.fill(swSelectedMulti,false);                        //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.constructor");        //~v@@@R~
    }
//*************************                                        //~v@@@I~
	public void discard()                                               //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("HandsTouch.discard set posOld="+posOld);//~v@@@I~
        if (posOld>=0)                                             //~v@@@I~
        {                                                          //~v@@@I~
	        resetSelection(posOld);                                //~v@@@I~
        	posOld=-1;                                             //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
//*************************                                        //~v@@@I~
	public void notifyRect(Bitmap[] Pbms,Point[] Ppoints,int Pctr)      //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("HandsTouch.notifyRect ctr="+Pctr);//~v@@@I~
        pointsHands=Ppoints;                                        //~v@@@I~
        bmsHands=Pbms;                                             //~v@@@R~
        ctrHands=Pctr;                                             //~v@@@I~
    }                                                              //~v@@@I~
//*************************                                        //~v@@@I~
	public void notifyRectTaken(Bitmap Pbm,Point Ppoint,int Ppos)  //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("HandsTouch.notifyRectTaken without canvas parm posOld="+posOld+",pos="+Ppos+",point x="+Ppoint.x+",y="+Ppoint.y);//~v@@@R~
        pointsHands[Ppos]=Ppoint;                                  //~v@@@I~
        bmsHands[Ppos]=Pbm;                                         //~v@@@I~
        ctrHands=Ppos+1;                                           //~v@@@I~
        if (posOld!=Ppos)                                          //~v@@@R~
        {                                                          //~v@@@R~
    		if (posOld>=0)                                         //~v@@@R~
    			resetSelection(posOld);                            //~v@@@R~
//  		drawTouch(Ppos);    //selected frame was drawn at Hands//~v@@@R~
            posOld=Ppos;                                           //~v@@@R~
        }                                                          //~v@@@R~
//      AG.aGC.touchEvent(Ppos);    //notify default selected tile(Players.selectedPos)//~v@@@R~
    }                                                              //~v@@@I~
////***********************************************************************//~v@@@R~
////* Hands lockCanvas,other tile is not selected                  //~v@@@R~
////***********************************************************************//~v@@@R~
//    public void notifyRectTaken(Canvas Pcanvas,Bitmap Pbm,Point Ppoint,int Ppos)//~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("HandsTouch.notifyRectTaken with canvas pos="+Ppos+",point x="+Ppoint.x+",y="+Ppoint.y);//~v@@@R~
//        pointsHands[Ppos]=Ppoint;                                //~v@@@R~
//        bmsHands[Ppos]=Pbm;                                      //~v@@@R~
//        ctrHands=Ppos+1;                                         //~v@@@R~
////      if (posOld!=Ppos)                                        //~v@@@R~
////      {                                                        //~v@@@R~
////          if (posOld>=0)                                       //~v@@@R~
////              resetSelection(posOld);                          //~v@@@R~
//            drawTouch(Pcanvas,Ppos);    //selected frame was drawn at Hands//~v@@@R~
////          posOld=Ppos;                                         //~v@@@R~
////      }                                                        //~v@@@R~
////      AG.aGC.touchEvent(Ppos);    //notify default selected tile(Players.selectedPos)//~v@@@R~
//    }                                                            //~v@@@R~
    //*******************************************************************//~v@@@I~
	public void resetSelection(int Ppos)                           //~v@@@I~
    {                                                              //~v@@@I~
    	int xx,yy;                                                 //~v@@@I~
        Rect r;                                                    //~v@@@I~
        Bitmap bm;                                                 //~v@@@I~
    //************************************                         //~v@@@I~
        if (Dump.Y) Dump.println("HandsTouch.resetSelection olpos="+posOld);//~v@@@I~
        if (Ppos>=0 && Ppos<ctrHands)	//erase selected id        //~v@@@I~
        {                                                          //~v@@@I~
	        xx=pointsHands[Ppos].x;  yy=pointsHands[Ppos].y;       //~v@@@I~
	        r=new Rect(xx,yy,xx+pieceW,yy+pieceH);                 //~v@@@I~
	        bm=bmsHands[Ppos];                                     //~v@@@I~
	        Graphics.drawBitmap(r,bm,xx,yy);                       //~v@@@I~
            posOld=-1;                                             //~v@21I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@21I~
	public void resetSelectionMulti()                              //~v@21I~
    {                                                              //~v@21I~
    	int xx,yy;                                                 //~v@21I~
        Rect r;                                                    //~v@21I~
        Bitmap bm;                                                 //~v@21I~
    //************************************                         //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.resetSelectionMulti");//~v@21I~
        for (int ii=0;ii<HANDCTR;ii++)                             //~v@21I~
        {                                                          //~v@21I~
        	if (swSelectedMulti[ii])                               //~v@21I~
            	resetSelection(ii);                                //~v@21I~
        }
        Arrays.fill(swSelectedMulti,false);//~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.resetSelectionMulti exit swSelectedMulti="+Arrays.toString(swSelectedMulti));//~v@21I~
    }                                                              //~v@21I~
    //*******************************************************************//~v@@@I~
	private void drawTouch(int Ppos)                               //~v@@@R~
    {                                                              //~v@@@I~
    	int xx,yy;                                                 //~v@@@I~
        Rect r;                                                    //~v@@@I~
        Bitmap bm;                                                 //~v@@@I~
    //************************************                         //~v@@@I~
        if (Dump.Y) Dump.println("HandsTouch.drawTouch without Pcanvas pos="+Ppos+",old="+posOld);//~v@@@R~
        if (Ppos==posOld)                                          //~v@@@I~
        	return;                                                //~v@@@I~
        if (posOld>=0)                                             //~v@@@R~
			resetSelection(posOld);                                //~v@@@I~
        posOld=Ppos;                                               //~v@@@I~
        xx=pointsHands[Ppos].x; yy=pointsHands[Ppos].y;        //~v@@@I~
        r=new Rect(xx,yy,xx+pieceW,yy+pieceH);                     //~v@@@I~
//      bm=bmsHands[Ppos];                                         //~v@@@R~
//      Graphics.drawRect(r,COLOR_SELECTED,WIDTH_SELECTED);        //~v@@@R~//~0401R~
        Graphics.drawRect(r,COLOR_SELECTED,AG.aHands.complete_stroke_width_hand); //~0401I~//+0406R~
        if (Dump.Y) Dump.println("HandsTouch.drawTouch stroke_width="+AG.aHands.complete_stroke_width_hand);//~0401I~
    }                                                              //~v@@@I~
    //*******************************************************************//~v@21I~
	private void drawTouchMulti(int Ppos)                          //~v@21I~
    {                                                              //~v@21I~
    	int xx,yy;                                                 //~v@21I~
        Rect r;                                                    //~v@21I~
        Bitmap bm;                                                 //~v@21I~
    //************************************                         //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.drawTouchMulti entry swSelectedMulti="+Arrays.toString(swSelectedMulti));//~v@21I~
    	boolean oldStatus=swSelectedMulti[Ppos];                //~v@21I~
        boolean newStatus=!oldStatus;                              //~v@21I~
    	swSelectedMulti[Ppos]=newStatus;                        //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.drawTouchMulti pos="+Ppos+",old="+oldStatus+",new="+newStatus);//~v@21I~
        xx=pointsHands[Ppos].x; yy=pointsHands[Ppos].y;            //~v@21I~
        r=new Rect(xx,yy,xx+pieceW,yy+pieceH);                     //~v@21I~
        if (newStatus)                                             //~v@21I~
        {                                                          //~0401I~
//  	    Graphics.drawRect(r,COLOR_SELECTED,WIDTH_SELECTED);    //~v@21R~//~0401R~
    	    Graphics.drawRect(r,COLOR_SELECTED,AG.aHands.complete_stroke_width_hand);//~0401I~
        	if (Dump.Y) Dump.println("HandsTouch.drawTouch stroke_width="+AG.aHands.complete_stroke_width_hand);//~0401I~
        }                                                          //~0401I~
        else                                                       //~v@21I~
			resetSelection(Ppos);                                  //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.drawTouchMulti exit swSelectedMulti="+Arrays.toString(swSelectedMulti));//~v@21I~
    }                                                              //~v@21I~
//    //*******************************************************************//~v@@@I~//~v@21R~
//    private void drawTouch(Canvas Pcanvas,int Ppos)               //~v@@@I~//~v@21R~
//    {                                                              //~v@@@I~//~v@21R~
//        int xx,yy;                                                 //~v@@@I~//~v@21R~
//        Rect r;                                                    //~v@@@I~//~v@21R~
//        Bitmap bm;                                                 //~v@@@I~//~v@21R~
//    //************************************                         //~v@@@I~//~v@21R~
//        if (Dump.Y) Dump.println("HandsTouch.drawTouch width canvas pos="+Ppos+",old="+posOld);//~v@@@I~//~v@21R~
////      if (Ppos==posOld)                                          //~v@@@I~//~v@21R~
////          return;                                                //~v@@@I~//~v@21R~
////      if (posOld>=0)                                             //~v@@@I~//~v@21R~
////          resetSelection(posOld);                                //~v@@@I~//~v@21R~
//        posOld=Ppos;                                               //~v@@@I~//~v@21R~
//        xx=pointsHands[Ppos].x; yy=pointsHands[Ppos].y;            //~v@@@I~//~v@21R~
//        r=new Rect(xx,yy,xx+pieceW,yy+pieceH);                     //~v@@@I~//~v@21R~
////      Graphics.drawRect(false/*not local*/,Pcanvas,r,COLOR_SELECTED,WIDTH_SELECTED);//~v@@@I~//~v@21R~
//        Graphics.drawRect(Pcanvas,r,COLOR_SELECTED,WIDTH_SELECTED);//~v@21R~
//    }                                                              //~v@@@I~//~v@21R~
	//*********************************************************    //~v@21I~
    public  int isTouch(int Pxx,int Pyy,int PxxDown,int PyyDown)   //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.isTouch xx=="+Pxx+",yy="+Pyy+",xxDown="+PxxDown+",yyDown="+PyyDown);//~v@21I~
        int xxDiff=PxxDown-Pxx;                                    //~v@21I~
        int yyDiff=PyyDown-Pyy;                                    //~v@21I~
        int diff=xxDiff*xxDiff+yyDiff*yyDiff;                      //~v@21I~
		int pos=getTouchPos(PxxDown,PyyDown);                    //~v@21I~
        int selectedPos=AG.aPlayers.getTileSelectedPos(PLAYER_YOU);      //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.isTouch selectedPos="+selectedPos+",pos="+pos+",diff="+diff+",dist_move="+dist_move);//~v@21R~
    	if (!(PxxDown==0 && PyyDown==0)	//not moved between Down and Up//~v@21I~
        &&  diff>=dist_move                                        //~v@21I~
        &&  pos!=ISTOUCH_NONE && pos==selectedPos                  //~v@21R~
        &&  !swMultiSelectionMode                                  //~v@21M~
        )                                                          //~v@21I~
    		pos+=ISTOUCH_SWIPED;                                   //~v@21R~
        else                                                       //~v@21I~
			pos=isTouch(Pxx,Pyy);                                  //~v@21R~
        if (Dump.Y) Dump.println("HandsTouch.isTouch rc pos="+pos);//~v@21R~
        return pos;
    }                                                              //~v@21I~
	//*********************************************************    //~v@21I~
    private  int getTouchPos(int Pxx,int Pyy)                      //~v@21I~
    {                                                              //~v@21I~
    	int pos=ISTOUCH_NONE;                                      //~v@21I~
    //********************                                         //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.getTouchPos xx=="+Pxx+",yy="+Pyy);//~v@21I~
        if (pointsHands==null || ctrHands==0 || rectHands==null)   //~v@21R~
   	    	return ISTOUCH_NONE;                                   //~v@21I~
    	if (Pyy>=rectHands.top-ALLOWANCE_V && Pyy<=rectHands.bottom+ALLOWANCE_V)//~v@21I~
        {                                                          //~v@21I~
        	if (Pxx>=pointsHands[0].x-ALLOWANCE_H && Pxx<=pointsHands[ctrHands-1].x+pieceW+ALLOWANCE_H)//~v@21I~
            {                                                      //~v@21I~
            	pos=ctrHands-1;                                    //~v@21I~
            	for (int ii=0;ii<ctrHands-1;ii++)                  //~v@21I~
                {                                                  //~v@21I~
                	if (Pxx<pointsHands[ii+1].x)                   //~v@21I~
                    {                                              //~v@21I~
                    	pos=ii;                                    //~v@21I~
                    	break;                                     //~v@21I~
                    }                                              //~v@21I~
                }                                                  //~v@21I~
            }                                                      //~v@21I~
        }                                                          //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.getTouchPos pos="+pos);//~v@21I~
        return pos;                                                //~v@21I~
    }                                                              //~v@21I~
	//*********************************************************    //~v@@@I~
//  public  int isTouch(int Pxx,int Pyy)                           //~v@@@I~//~v@21R~
    private  int isTouch(int Pxx,int Pyy)                          //~v@21I~
    {                                                              //~v@@@I~
    	int pos=ISTOUCH_NONE;                                      //~v@@@R~
    //********************                                         //~v@@@I~
        if (Dump.Y) Dump.println("HandsTouch.isTouch xx=="+Pxx+",yy="+Pyy+",multimode="+swMultiSelectionMode);//~v@@@R~//~v@21R~
//      if (!AG.aPlayers.isTileSelectable(PLAYER_YOU))             //~v@@@R~//~v@21R~
//      	return pos;                                            //~v@@@I~//~v@21R~
//        if (Pyy>=rectHands.top-ALLOWANCE_V && Pyy<=rectHands.bottom+ALLOWANCE_V)//~v@@@I~//~v@21R~
//        {                                                          //~v@@@I~//~v@21R~
//            if (Pxx>=pointsHands[0].x-ALLOWANCE_H && Pxx<=pointsHands[ctrHands-1].x+pieceW+ALLOWANCE_H)//~v@@@R~//~v@21R~
//            {                                                      //~v@@@I~//~v@21R~
//                pos=ctrHands-1;                                    //~v@@@I~//~v@21R~
//                for (int ii=0;ii<ctrHands-1;ii++)                  //~v@@@I~//~v@21R~
//                {                                                  //~v@@@I~//~v@21R~
//                    if (Pxx<pointsHands[ii+1].x)                        //~v@@@I~//~v@21R~
//                    {                                              //~v@@@I~//~v@21R~
//                        pos=ii;                                    //~v@@@I~//~v@21R~
//                        break;                                     //~v@@@I~//~v@21R~
//                    }                                              //~v@@@I~//~v@21R~
//                }                                                  //~v@@@I~//~v@21R~
//            }                                                      //~v@@@I~//~v@21R~
//        }                                                          //~v@@@I~//~v@21R~
    	pos=getTouchPos(Pxx,Pyy);                              //~v@21I~
        if (pos>=0)                                                //~v@@@I~
        {                                                          //~v@21I~
	        if (!AG.aPlayers.isTileSelectable(PLAYER_YOU)          //~v@21R~
	        &&  !swMultiSelectionMode && !enableKan)               //~v@21R~
            {                                                      //~v@21I~
            	AG.aACAction.showErrmsg(0/*opt*/,R.string.AE_TakeOnly);//~v@21I~
    	    	return ISTOUCH_NONE;                               //~v@21I~
            }                                                      //~v@21I~
	        if (swMultiSelectionMode)                              //~v@21I~
	            drawTouchMulti(pos);                               //~v@21I~
            else                                                   //~v@21I~
	            drawTouch(pos);                                        //~v@@@I~//~v@21R~
        }                                                          //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.isTouch xx=="+Pxx+",yY="+Pyy+",pos="+pos);//~v@@@I~
        return pos;                                                //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~9C01I~
    public void resetSelection()                                   //~9C01I~
    {                                                              //~9C01I~
        if (Dump.Y) Dump.println("HandsTouch.resetSelection posOld="+posOld+",swMultiSelectionMode="+swMultiSelectionMode);//~9C01I~
        if (swMultiSelectionMode)                                  //~9C01I~
    		enableMultiSelectionMode(false);                       //~9C01I~
		resetSelection(posOld);                                    //~9C01I~
    }                                                              //~9C01I~
	//*********************************************************    //~v@@@I~
    public void enableMultiSelectionMode(boolean Penable)          //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.enableMultiSelectionMode enable="+Penable);//~v@21R~
        swMultiSelectionMode=Penable;                              //~v@21I~
        if (!Penable)                                              //~v@21I~
        	resetSelectionMulti();                                 //~v@21I~
//        else                                                       //~v@21I~//~9B30R~
//            if (posOld>=0 && posOld<ctrHands)   //pre-selected     //~v@21I~//~9B30R~
//                swSelectedMulti[posOld]=true;                      //~v@21I~//~9B30R~
    	if (Dump.Y) Dump.println("HandsTouch.enableMultiSelectionMode posOld="+posOld+",swSelectedMulti="+Arrays.toString(swSelectedMulti));//~v@21I~
        enableKan=false;                                           //~v@21I~
    }                                                              //~v@21I~
//    //*********************************************************    //~v@21I~//~9B30R~
//    //*for retry select by multimode with pretouched tile          //~v@21I~//~9B30R~
//    //*********************************************************    //~v@21I~//~9B30R~
//    public boolean enableMultiSelectionModeWithPreTouched()           //~v@21I~//~9B30R~
//    {                                                              //~v@21I~//~9B30R~
//        boolean rc=false;                                          //~v@21I~//~9B30R~
//        if (Dump.Y) Dump.println("HandsTouch.enableMultiSelectionModePreTouchede swMultiSelectionMode="+swMultiSelectionMode+",posOld="+posOld+",swSelectedMulti="+Arrays.toString(swSelectedMulti));//~v@21I~//~9B30R~
//        if (!swMultiSelectionMode)                                 //~v@21I~//~9B30R~
//            if (posOld>=0 && posOld<ctrHands)   //pre-selected     //~v@21I~//~9B30R~
//                if (!swSelectedMulti[posOld])                      //~v@21I~//~9B30R~
//                {                                                  //~v@21I~//~9B30R~
//                    enableMultiSelectionMode(true);                //~v@21I~//~9B30R~
//                    rc=true;                                       //~v@21I~//~9B30R~
//                }                                                  //~v@21I~//~9B30R~
//        if (Dump.Y) Dump.println("HandsTouch.enableMultiSelectionModePreTouchede rc="+rc+",swMultiSelectionMode="+swMultiSelectionMode+",swSelectedMulti="+Arrays.toString(swSelectedMulti));//~v@21R~//~9B30R~
//        return rc;                                                 //~v@21I~//~9B30R~
//    }                                                              //~v@21I~//~9B30R~
    //*********************************************************    //~9B30I~
    //*set multiple mode resetting posOld of singlemode            //~9B30I~
    //*rc:reset posOld                                             //~9B30I~
    //*********************************************************    //~9B30I~
    public boolean enableMultiSelectionModeResettingSingleMode()   //~9B30I~
    {                                                              //~9B30I~
        boolean rc=false;                                          //~9B30I~
        if (Dump.Y) Dump.println("HandsTouch.enableMultiSelectionModeResettingSingleMode="+swMultiSelectionMode+",posOld="+posOld+",swSelectedMulti="+Arrays.toString(swSelectedMulti));//~9B30I~
        if (!swMultiSelectionMode)                                 //~9B30I~
            if (posOld>=0 && posOld<ctrHands)   //pre-selected     //~9B30I~
            {                                                      //~9B30I~
				resetSelection(posOld);                             //~9B30I~
                rc=true;                                           //~9B30I~
            }                                                      //~9B30I~
	    enableMultiSelectionMode(true);                            //~9B30I~
        if (Dump.Y) Dump.println("HandsTouch.enableMultiSelectionModeResettingSingleMode rc="+rc+",swMultiSelectionMode="+swMultiSelectionMode+",swSelectedMulti="+Arrays.toString(swSelectedMulti));//~9B30R~
        return rc;                                                 //~9B30I~
    }                                                              //~9B30I~
	//*********************************************************    //~v@21I~
    public void enableMultiSelectionMode(boolean Penable,boolean PswKan)//~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("HandsTouch.enableMultiSelectionMode enable="+Penable+",swKan="+PswKan);//~v@21I~
        swMultiSelectionMode=Penable;                              //~v@21I~
        if (!Penable)                                              //~v@21I~
        	resetSelectionMulti();                                 //~v@21I~
        enableKan=true;                                            //~v@21I~
    }                                                              //~v@21I~
	//*********************************************************    //~v@21I~
    public boolean[] getSelectedMulti()                            //~v@21I~
    {                                                              //~v@21I~
    	if (Dump.Y) Dump.println("HandsTouch.getSelectedMulti:="+Arrays.toString(swSelectedMulti));//~v@21I~
    	return swSelectedMulti;                                    //~v@21I~
    }                                                              //~v@21I~
	//*********************************************************    //~9C07I~
    public int getPosOld()                      //TODO for test    //~9C07I~
    {                                                              //~9C07I~
    	if (Dump.Y) Dump.println("HandsTouch.getPosOld ="+posOld); //~9C07I~
    	return posOld;                                             //~9C07I~
    }                                                              //~9C07I~
}//class HandsTouch                                                 //~dataR~//~@@@@R~//~v@@@R~
