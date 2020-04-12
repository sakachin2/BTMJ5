//*CID://+v@@@R~: update#= 391;                                    //~v@@@R~
//**********************************************************************//~v101I~
//utility around screen                                            //~v@@@I~
//**********************************************************************//~1107I~
package com.btmtest.game.gv;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Message;

import com.btmtest.game.Status;
import static com.btmtest.game.Status.*;
import com.btmtest.utils.Dump;
import static com.btmtest.game.gv.Pieces.*;                        //~v@@@I~
import static com.btmtest.game.GConst.*;                           //~v@@@R~
import static com.btmtest.game.GCMsgID.*;                          //~v@@@I~
import static com.btmtest.StaticVars.AG;                           //~v@@@I~


//public class GCanvas extends Handler                             //~v@@@R~
public class GCanvas                                               //~v@@@I~
{                                                                  //~0914I~
                                                                   //~v@@@I~
//    public  SurfaceHolder holder;                                //~v@@@R~
//    private Graphics gg;                                         //~v@@@R~
    public  Canvas canvas;                                         //~v@@@R~
    private int WW,HH,handPieceH,handPieceW,riverPieceW,riverPieceH;//~v@@@R~
    private Bitmap[][][]  bmPieces,bmPiecesRiver;                  //~v@@@R~
    public  Pieces pieces;                                         //~v@@@R~
    public  MJTable table;                                         //~v@@@R~
    private boolean swPortrait;                                    //~v@@@R~
    public  DiceBox diceBox;
    private Stock stock;
    private River river;//~v@@@R~
    private Hands hands;                                           //~v@@@R~
    private Graphics aGraphics;                                    //~v@@@I~
    private ICanvas iCanvas;                                       //~v@21I~//~v@@@M~
//*************************                                        //~v@@@I~
//  public GCanvas(SurfaceHolder Pholder,MJTable Ptable,int Pww,int Phh)//~v@@@R~
    public GCanvas(MJTable Ptable,int Pww,int Phh)                 //~v@@@I~
    {                                                              //~0914I~
//  	if (Dump.Y) Dump.println("GCanvas constructor holder="+Pholder.toString());//~v@@@R~
    	if (Dump.Y) Dump.println("GCanvas constructor");           //~v@@@I~
    	AG.aGCanvas=this;                                          //~v@@@I~
        WW=Pww;                                                    //~v@@@M~
        HH=Phh;                                                    //~v@@@M~
        swPortrait=(HH>WW);                                        //~v@@@M~
        iCanvas=new ICanvas(WW,HH);                            //~v@21R~//~v@@@I~
        canvas=iCanvas.getCanvas();                                //~v@@@I~
//  	holder=Pholder;                                            //~v@@@R~
    	table=Ptable;                                              //~v@@@R~
    	pieces=table.pieces;                                        //~v@@@I~
//  	bmPieces=pieces.bitmapAllPieces;                           //~v@@@R~
    	bmPieces=AG.bitmapAllPieces;                               //~v@@@I~
//  	bmPiecesRiver=pieces.bitmapAllPiecesRiver;                 //~v@@@R~
    	bmPiecesRiver=AG.bitmapAllPiecesRiver;                     //~v@@@I~
        handPieceW=table.handPieceW;                              //~v@@@R~
        handPieceH=table.handPieceH;                              //~v@@@R~
        riverPieceW=table.riverPieceW;                            //~v@@@I~
        riverPieceH=table.riverPieceH;                            //~v@@@I~
        hands=new Hands(this);                                     //~v@@@R~
        river=new River(this);                                     //~v@@@R~
        new Starter(this);                                         //~v@@@M~
        diceBox=new DiceBox(this);                                 //~v@@@M~
        stock=new Stock(this);                                     //~v@@@I~
        new PointStick(this);                                           //~v@@@I~
        new NamePlate(this);	//after dicebox and starter for landscape nameplate//~v@@@R~
        new GMsg(this);                                            //~v@@@I~
    	if (Dump.Y) Dump.println("GCanvas constructor End");       //~v@@@I~
    }                                                              //~0914I~//~v@@@R~
//    //****************************************                   //~v@@@R~
//    //*frm GameView->GVHander                                    //~v@@@R~
//    //****************************************                   //~v@@@R~
      public void onDestroy()                                      //~v@@@R~
      {                                                            //~v@@@R~
          iCanvas.onDestroy();                                     //~v@@@R~
          if (AG.aGMsg!=null)                                      //~v@@@I~
          	AG.aGMsg.onDestroy();                                  //~v@@@I~
          if (AG.aNamePlate!=null)                                 //~v@@@I~
          	AG.aNamePlate.onDestroy();                             //~v@@@I~
      }                                                            //~v@@@R~
    //****************************************                     //~v@@@I~
    //*frm GameView->GVHander                                      //~v@@@I~
//    //****************************************                   //~v@@@R~
//    public void surfaceCreated(SurfaceHolder Pholder)            //~v@@@R~
//    {                                                            //~v@@@R~
//        Graphics.surfaceCreated(Pholder);                        //~v@@@R~
//    }                                                            //~v@@@R~
	//*************************                                    //~v@@@I~
	public Canvas lockCanvas()                                     //~v@@@R~
    {                                                              //~v@@@I~
//        canvas=holder.lockCanvas();                              //~v@@@R~
//      if (Dump.Y) Dump.println("GameViewHandler.lockCanvas canvas="+canvas.toString()+",holder="+holder.toString());//~v@@@R~
//        return canvas;                                           //~v@@@R~
		return Graphics.lockCanvas();                              //~v@@@R~
    }                                                              //~v@@@I~
	//*************************                                    //~v@@@I~
	public void lockCanvas(Rect Pdirtyrect)                        //~v@@@I~
    {                                                              //~v@@@I~
//        canvas=holder.lockCanvas(Pdirtyrect);                    //~v@@@R~
		Graphics.lockCanvas(Pdirtyrect);                           //~v@@@I~
//      if (Dump.Y) Dump.println("GameViewHandler.lockCanvas with dirtyrect x=("+Pdirtyrect.left+","+Pdirtyrect.right+"),y=("+Pdirtyrect.top+","+Pdirtyrect.bottom);//~v@@@R~
//      if (Dump.Y) Dump.println("GameViewHandler.lockCanvas with dirtyrect canvas="+canvas.toString()+",holder="+holder.toString());//~v@@@R~
    }                                                              //~v@@@I~
	//*************************                                    //~v@@@I~
	public void unlockCanvas()                                     //~v@@@I~
    {                                                              //~v@@@I~
//      if (Dump.Y) Dump.println("GameViewHandler.unlockCanvas canvas="+canvas.toString()+",holder="+holder.toString());//~v@@@R~
//        holder.unlockCanvasAndPost(canvas);                      //~v@@@R~
		Graphics.unlockCanvas();                                   //~v@@@I~
    }                                                              //~v@@@I~
//    //*************************                                  //~v@@@R~
//    public void onResume()                                       //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("Gcanvas.onResume");            //~v@@@R~
//        if (AG.aGraphics!=null)                                  //~v@@@R~
//            AG.aGraphics.onResume();                             //~v@@@R~
//    }                                                            //~v@@@R~
	//*********************************************************    //~v@@@R~
	//*GVH enclose by try catch                                    //~v@@@R~
    //*********************************************************    //~v@@@R~
	public void draw(Message Pmsg)                                 //~v@@@R~
    {                                                              //~1120I~//~1122M~
        if (Dump.Y) Dump.println("GCanvas.draw msg="+Pmsg.what);   //~v@@@R~
        String msgData=GameViewHandler.getMsgData(Pmsg);               //~v@@@I~
//lock/unlock at each process                                      //~v@@@I~
        switch(Pmsg.what)                                          //~v@@@I~
        {                                                          //~v@@@I~
        case GCM_DICE:                                             //~v@@@I~
            DiceBox.enable(true);                             //~v@@@R~
            break;                                                 //~v@@@I~
        case GCM_SETUP:                                            //~v@@@I~
//          DiceBox.enable(true);                                  //~v@@@I~
		    lockCanvas();	//set canvas                           //~v@@@I~
        	try                                                    //~v@@@I~
        	{                                                      //~v@@@I~
//                gg=new Graphics(canvas,WW,HH);                   //~v@@@R~
	            drawSetup();                                       //~v@@@R~
        	}                                                      //~v@@@I~
        	catch(Exception e)//unlock mandatory                   //~v@@@I~
        	{                                                      //~v@@@I~
        		Dump.println(e,"GCanvas.draw");                    //~v@@@I~
        	}                                                      //~v@@@I~
        	unlockCanvas();                                        //~v@@@I~
            break;                                                 //~v@@@I~
        default:                                                   //~v@@@I~
		    lockCanvas();	//set canvas                           //~v@@@R~
        	try                                                    //~v@@@I~
        	{                                                      //~v@@@I~
//                gg=new Graphics(canvas,WW,HH);                   //~v@@@R~
        		drawSync(Pmsg);                                     //~v@@@I~
        	}                                                      //~v@@@I~
        	catch(Exception e)//unlock mandatory                   //~v@@@I~
        	{                                                      //~v@@@I~
        		Dump.println(e,"GCanvas.draw");                    //~v@@@I~
        	}                                                      //~v@@@I~
        	unlockCanvas();                                        //~v@@@I~
//          AG.aGMsg.drawMsg("さいころを振ってください");	//TDO test//~v@@@R~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    //*process under locked                                        //~v@@@I~
    //*********************************************************    //~v@@@I~
	private void drawSync(Message Pmsg)                            //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GCanvas.drawSync msg="+Pmsg.what);//~v@@@I~
        String msgData=GameViewHandler.getMsgData(Pmsg);           //~v@@@I~
        switch(Pmsg.what)                                          //~v@@@I~
        {                                                          //~v@@@I~
        case GCM_TEST:                                             //~v@@@I~
            drawTest(msgData);  //GCM_TEST                         //+v@@@R~
            break;                                                 //~v@@@I~
        case GCM_INIT:                                             //~v@@@I~
            drawInit();                                            //~v@@@R~
            break;                                                 //~v@@@I~
        }                                                          //~v@@@I~
    }                                                              //~v@@@I~
    //*********************************************************    //~v@@@I~
    private void drawBG()                                          //~v@@@M~
    {                                                              //~v@@@M~
    	Rect rect=new Rect(0,0,WW,HH);                             //~v@@@M~
//      gg.drawRect(rect,COLOR_BG_TABLE);                          //~v@@@R~
//      Graphics.drawRect(canvas,rect,COLOR_BG_TABLE);             //~v@@@R~
        Graphics.drawRect(rect,COLOR_BG_TABLE);       //~v@@@I~
    }                                                              //~v@@@M~
	//*********************************************************    //~v@@@I~
	private void drawTest(String Pmsgdata)   //BY GCM_TEST         //+v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GCanvas.drawTest");              //~v@@@I~
        drawBG();                                                  //~v@@@I~
        int margin=20;                                             //~v@@@R~
        int ww=0,hh=0;                                             //~v@@@R~
        int xx=margin;                                             //~v@@@R~
        int yy=margin;                                             //~v@@@R~
        if (Dump.Y) Dump.println("GCanvas.drawTest Stand/Lying");  //~v@@@I~
		yy=drawTest(xx,yy,bmPieces);                                //~v@@@I~
        if (Dump.Y) Dump.println("GCanvas.drawTest River");        //~v@@@R~
		drawTest(xx,yy,bmPiecesRiver);                             //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	private int drawTest(int Pxx,int Pyy,Bitmap[][][] Pbmps) //BY GCM_TEST//+v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GCanvas.drawTest yy="+Pyy);      //~v@@@R~
        int sepW=2;                                                //~v@@@I~
        int sepH=10;                                               //~v@@@I~
        int ppl=14;     //piece per line                           //~v@@@I~
        int ww=0,hh=0;
        int xx=Pxx;
        int yy=Pyy;//~v@@@I~
        for (int ii=0;ii<Pbmps.length;ii++)                        //~v@@@I~
        {                                                          //~v@@@I~
	        int ctr=0;                                             //~v@@@I~
        	for (int jj=0;jj<PIECE_TYPECTR_ALL;jj++)               //~v@@@R~
            {                                                      //~v@@@I~
            	Bitmap[] bms=Pbmps[ii][jj];                        //~v@@@R~
                for (int kk=0;kk<bms.length;kk++)                  //~v@@@R~
                {                                                  //~v@@@I~
                	Bitmap bm=bms[kk];                             //~v@@@I~
	                ww=bm.getWidth();                              //~v@@@I~
    	            hh=bm.getHeight();                             //~v@@@I~
//              	gg.drawBitmap(bm,xx,yy);                       //~v@@@R~
                	Graphics.drawBitmap(canvas,bm,xx,yy);          //~v@@@I~
                    xx+=ww+sepW;                                   //~v@@@I~
                    ctr++;                                         //~v@@@I~
                    if (ctr%ppl==0)                                //~v@@@I~
                    {                                              //~v@@@I~
                        xx=Pxx;                                    //~v@@@I~
                        yy+=sepH+hh;                               //~v@@@I~
                    }                                              //~v@@@I~
                }                                                  //~v@@@I~
            }                                                      //~v@@@I~
            xx=Pxx;                                                //~v@@@I~
            yy+=sepH+hh;                                           //~v@@@I~
        }                                                          //~v@@@I~
        return yy;                                                 //~v@@@I~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	private void drawInit()                                        //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GCanvas.drawInit");              //~v@@@I~
    	Status.setGameStatus(GS_INIT);                             //~v@@@I~
        if (Dump.Y) Dump.println("GCanvas.drawInit");              //~v@@@R~
        drawBG();                                                  //~v@@@I~
        Point p=table.getEarthLinePos();                            //~v@@@I~
        drawEarthYours(p.x,p.y,bmPieces[PIECE_STANDING]);          //~v@@@R~
        drawRiver();                                               //~v@@@R~
        AG.aStarter.drawStarter(); //TODO                          //~v@@@R~
		drawCoin();                                                //~v@@@R~
        diceBox.drawDice(canvas,0,5);                              //~v@@@R~
        drawStocks();                                              //~v@@@R~
	}                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	private void drawSetup()                                       //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GCanvas.drawSetup");             //~v@@@R~
        drawBG();                                                  //~v@@@I~
//      Point p=table.getEarthLinePos();                           //~v@@@I~
//      drawEarthYours(p.x,p.y,bmPieces[PIECE_STANDING]);          //~v@@@I~
        drawRiver();                                               //~v@@@I~
        diceBox.drawDice(canvas,0,5);                              //~v@@@I~
        drawStocks();                                              //~v@@@I~
	}                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	public void drawEarthYours(int Pxx,int Pyy,Bitmap[][] Pbmps)   //~v@@@R~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GCanvas.drawEarthYours xx="+Pxx+",yy="+Pyy);//~v@@@R~
        AG.aHands.drawInitial(canvas);                             //~v@@@R~
//        int ww=handPieceW;                                       //~v@@@R~
//        int hh=handPieceH;                                       //~v@@@R~
//        int sepW=PIECE_SPACING;                                  //~v@@@R~
//        int sepW2=PIECE_SPACING_NOW;                             //~v@@@R~
//        int ppl=14;     //piece per line                         //~v@@@R~
//        int xx=Pxx;                                              //~v@@@R~
//        int yy=Pyy;                                              //~v@@@R~
//        int ctr=0;                                               //~v@@@R~
//        for (int jj=0;jj<PIECE_TYPECTR_ALL && ctr<ppl;jj++)      //~v@@@R~
//        {                                                        //~v@@@R~
//            Bitmap[] bms=Pbmps[jj];                              //~v@@@R~
//            for (int kk=0;kk<bms.length && ctr<ppl;kk++)         //~v@@@R~
//            {                                                    //~v@@@R~
//                Bitmap bm=bms[kk];                               //~v@@@R~
//                gg.drawBitmap(bm,xx,yy);                         //~v@@@R~
//                if (ctr==ppl-2)                                  //~v@@@R~
//                    xx+=ww+sepW2;                                //~v@@@R~
//                else                                             //~v@@@R~
//                    xx+=ww+sepW;                                 //~v@@@R~
//                ctr++;                                           //~v@@@R~
//            }                                                    //~v@@@R~
//        }                                                        //~v@@@R~
//        return yy;                                               //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	public void drawRiver()                                        //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GCanvas.drawRiver");             //~v@@@I~
//      AG.aRiver.drawInitial(canvas);   //TODO TEST               //~v@@@R~
    }                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	private void drawStocks()                                      //~v@@@R~
    {                                                            //~v@@@R~
        if (Dump.Y) Dump.println("GCanvas.drawStocks");            //~v@@@I~
//      stock.drawInitial(canvas);                                 //~v@@@R~
        stock.drawInitial();                                       //~v@@@R~
    }                                                              //~v@@@I~
//    //*********************************************************  //~v@@@R~
//    public void drawStarter()                                    //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("GCanvas.drawStarter");         //~v@@@R~
//        Bitmap[][] bmss=pieces.bitmapStarter;                    //~v@@@R~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~v@@@R~
//        {                                                        //~v@@@R~
//            Point p=table.getStarterPos(ii);                     //~v@@@R~
//            int xx=p.x;                                          //~v@@@R~
//            int yy=p.y;                                          //~v@@@R~
//            Bitmap bm=bmss[ii][ii];                              //~v@@@R~
//            gg.drawBitmap(bm,xx,yy);                             //~v@@@R~
//        }                                                        //~v@@@R~
//    }                                                            //~v@@@R~
//    //*********************************************************  //~v@@@R~
    public void drawCoin()                                         //~v@@@R~
    {                                                              //~v@@@R~
        if (Dump.Y) Dump.println("GCanvas.drawCoin");              //~v@@@R~
//      Bitmap[][] bmss=pieces.bitmapPointStick;                   //~v@@@R~
        Bitmap[][] bmss=AG.bitmapPointStick;                       //~v@@@I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~v@@@R~
        {                                                          //~v@@@R~
            Point p=table.getPointStickPos(ii);                          //~v@@@R~
            int xx=p.x;                                            //~v@@@R~
            int yy=p.y;                                            //~v@@@R~
            Bitmap bm=bmss[ii][ii];                                //~v@@@R~
//          gg.drawBitmap(bm,xx,yy);                               //~v@@@R~
            Graphics.drawBitmap(canvas,bm,xx,yy);                  //~v@@@I~
        }                                                          //~v@@@R~
    }                                                              //~v@@@R~
//    //*********************************************************  //~v@@@R~
//    public void drawDiceCasting(boolean Penableafter)            //~v@@@R~
//    {                                                            //~v@@@R~
//        if (Dump.Y) Dump.println("GCanvas.drawDiceCasting");     //~v@@@R~
//        DiceBox.cast(Penableafter);                              //~v@@@R~
//    }                                                            //~v@@@R~
	//*********************************************************    //~v@@@I~
	public void drawStock(int Pcutpos)                             //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GCanvas.drawStock cutpos="+Pcutpos);//~v@@@R~
        stock.drawDeal(Pcutpos);                                   //~v@@@R~
	}                                                              //~v@@@I~
	//*********************************************************    //~v@@@I~
	public void drawStock(int Pplayer,int Pcutpos)                 //~v@@@I~
    {                                                              //~v@@@I~
        if (Dump.Y) Dump.println("GCanvas.drawStock player="+Pplayer+",cutpos="+Pcutpos);//~v@@@I~
        stock.drawDeal(Pplayer,Pcutpos);                           //~v@@@R~
	}                                                              //~v@@@I~
}//class GCanvas                                                 //~dataR~//~@@@@R~//~v@@@R~
