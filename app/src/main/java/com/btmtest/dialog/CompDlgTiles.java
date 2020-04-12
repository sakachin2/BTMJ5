//*CID://+DATER~: update#= 522;                                    //~v@21R~//~9218R~
//**********************************************************************
//v@21  imageview                                                  //~v@21I~
//utility around screen
//**********************************************************************
package com.btmtest.dialog;                                        //~9219R~

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatImageView;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.btmtest.R;
import com.btmtest.game.Accounts;
import com.btmtest.game.Complete;
import com.btmtest.game.Players;
import com.btmtest.game.Status;
import com.btmtest.game.TileData;
import com.btmtest.game.Tiles;
import com.btmtest.game.gv.GCanvas;
import com.btmtest.game.gv.Graphics;
import com.btmtest.game.gv.HandsTouch;
import com.btmtest.game.gv.MJTable;
import com.btmtest.game.gv.Pieces;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import static com.btmtest.StaticVars.AG;                           //~v@21I~
import static com.btmtest.game.Complete.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.gv.Earth.*;
import static com.btmtest.game.gv.MJTable.*;

public class CompDlgTiles extends AppCompatImageView               //~v@21R~//~9925R~
{
//  public  static final int SPACING_X=4;                          //~v@21R~//~0328R~
    public  static final int SPACING_X=PAIR_SPACING; //5           //~0328I~
	public  static final int SPACING_Y=6;                          //~v@21I~
//  public  static final int SPACING_EARTH=SPACING_X*3;           //~9302I~//~0328R~
	private static final int HANDS_POSX=SPACING_X;                 //~v@21R~
	private static final int HANDS_POSY=SPACING_Y;                //~v@21R~
	private static final int EARTH_MARGIN_RIGHT=4;                 //~0327I~
                                                                   //~v@21I~
    private int WW, HH;                                    //~v@21R~//~9219R~
    private GCanvas gcanvas;                                       //~v@21R~
    private MJTable table;                                         //~v@21I~
    private Pieces pieces;
    private Players PLS;//~v@21I~
    private Canvas canvas;                                         //~v@21I~
    private int compEswn,compType,compEswnLooser;                  //~v@21I~
//  private CompleteDlg dlg;                                       //~v@21I~//~9301R~
    private int player,bgColor;                                            //~v@21I~
    private int pieceW,pieceH;             //~v@21R~               //~9219R~
//  private int earthPieceH,earthPieceW;                           //~v@21I~//~9301R~
    private boolean swTake;                                        //~v@21R~
//  private int ctrKanDrawn;                                        //~v@21I~//~9301R~
//  private TileData[] shuffled;                                   //~v@21I~//~9301R~
//  private Bitmap[][][] bmsssRiver;  //[standing/lying][man/pin/sou/ji][number]//~v@21I~//~9301R~
//  private boolean swDrawDoraRight;                               //~v@21I~//~9219R~
//  private TileData compTD,compTDKanTaken;                        //~v@21R~//~9301R~
    private TileData compTD;                                       //~9301I~
    private Complete.Status compStat;                              //~v@21I~
    private Complete.Status parmCompStat;                          //~9519I~
//  private static boolean swByXml;                                //~9218R~//~9302R~
//  private static int pairCtr;                                    //~9219I~//~9815R~
//  private int eswnReach;                                         //~9301R~//~9302R~
//  private boolean swReacher;                                     //~9301I~//~9302R~
    private int eswnReach;                                         //~9302R~
    private int pairWW,pairHH;                                     //~9302R~
    private boolean swReacher;                                     //~9302R~
    private boolean swSingleLine;                                  //~9302R~
    private boolean swRequestedLayout;                             //~9302I~
    private boolean swPending;                                     //~9309I~
    public int widthTileImage;                                     //~9815I~
    private int heightTileImage;                                   //~0326I~
//  private View layoutDialog;                                     //~9925I~//~9927R~
//  private LinearLayout llDialog;	//TODO test                    //~9927R~
	private int shiftEarth,firstPosEarth,endPosEarth;                          //~0326I~//~0327R~
	private boolean swChkEarthPosition;                            //~0326I~
	private int stroke_width;                                      //~0401I~
    //**************************************************************//~v@@@R~//~v@21R~
    //*if calss defined in xml                                     //~v@@@I~//~v@21R~
    //**************************************************************//~v@@@I~//~v@21R~
    public CompDlgTiles(Context Pcontext)                          //~9218I~
    {                                                              //~9218I~
        super(Pcontext);                                           //~9218R~
        if (Dump.Y) Dump.println("CompDlgTiles Constructor without attr this="+this.toString()+",swRequestedLayout="+swRequestedLayout);//~9406R~
        init(Pcontext);                                            //~9218I~
    }                                                              //~9218I~
//    public CompDlgTiles(Context Pcontext,AttributeSet Pattrs)          //~v@@@I~//~v@21R~//~9302R~
//    {                                                              //~v@@@I~//~v@21R~//~9302R~
//        super(Pcontext,Pattrs);                                    //~v@@@I~//~v@21R~//~9302R~
//        if (Dump.Y) Dump.println("CompDlgTiles Constructor with attr");//~v@@@R~//~v@21R~//~9302R~
//        swByXml=true;                                              //~9218I~//~9302R~
//        init(Pcontext);                                            //~v@@@R~//~v@21R~//~9302R~
//    }                                                              //~v@@@I~//~v@21R~//~9302R~
    //******************************************                   //~v@21I~
//  public static CompDlgTiles setImageLayout(View PView)                        //~v@21I~//~9218R~//~9228R~//~9815R~
//  {                                                              //~v@21I~//~9815R~
//      return setImageLayout(PView,-1/*PeswnReach*/);           //~9228I~//~9301R~//~9815R~
//      return setImageLayout(PView,-1/*PeswnReach*/,PcompStat);   //~9815R~
//  }                                                              //~9228I~//~9815R~
    //******************************************                   //~9519I~
    public static CompDlgTiles setImageLayout(View PView,Complete.Status PcompStat)//~9519I~
    {                                                              //~9519I~
//      CompDlgTiles dlg=setImageLayout(PView);                    //~9519I~//~9815R~
//      dlg.parmCompStat=PcompStat;                                //~9519I~//~9815R~
//      CompDlgTiles dlg=setImageLayout(PView,PcompStat);          //~9815R~
        CompDlgTiles dlg=setImageLayout(PView,-1/*PeswnReach*/,PcompStat);//~9815I~
        return dlg;
    }                                                              //~9519I~
    //******************************************                   //~9309I~
    public static CompDlgTiles setImageLayout(View PView,int PeswnReach)//~9309I~
    {                                                              //~9309I~
//      return setImageLayout(PView,PeswnReach,false/*swPendding*/);      //~9309I~//~9815R~
        return setImageLayout(PView,PeswnReach,false/*swPendding*/,null/*compstat*/);//~9815I~
    }                                                              //~9309I~
    //******************************************                   //~9815I~
    public static CompDlgTiles setImageLayout(View PView,int PeswnReach,Complete.Status PcompStat)//~9815I~
    {                                                              //~9815I~
	    return setImageLayout(PView,PeswnReach,false/*swPendding*/,PcompStat);//~9815I~
    }                                                              //~9815I~
    //******************************************                   //~9228I~
    public static CompDlgTiles setImageLayout(View PView,int PeswnReach,boolean PswPending)//~9815R~
    {                                                              //~9228I~
	    return setImageLayout(PView,PeswnReach,PswPending,null/*compstat*/);//~9815I~
    }                                                              //~9815I~
//    //******************************************                   //~9815I~//~9927R~
////  public static CompDlgTiles setImageLayout(View PView,int PeswnReach,boolean PswPending)//~9815I~//~9927R~
//    public static CompDlgTiles setImageLayout(View PView,int PeswnReach,boolean PswPending,Complete.Status PcompStat)//~9815I~//~9927R~
//    {                                                              //~9815I~//~9927R~
//        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout eswnReach="+PeswnReach+",PView="+PView.toString());//~9925I~//~9927R~
//        GCanvas gcanvas = AG.aGCanvas;                             //~v@21I~//~9927R~
//        MJTable table = gcanvas.table;                             //~v@21I~//~9927R~
//        int pieceH=table.handPieceH;                               //~v@21I~//~9927R~
////      eswnReach=PeswnReach;                                      //~9302R~//~9927R~
////      swReacher=PeswnReach!=-1;                                  //~9302R~//~9927R~
//        Point p=table.getPairPieceSize();                          //~v@21I~//~9927R~
//        int pairPieceH=p.y;                                        //~v@21I~//~9927R~
//        int pairPieceW=p.x;                                        //~9302I~//~9927R~
////      int pairctr=getPairCtr(PeswnReach);                                      //~9219I~//~9301R~//~9815R~//~9927R~
//        int pairctr=getPairCtr(PeswnReach,PcompStat);              //~9815I~//~9927R~
//        LinearLayout llImage    =(LinearLayout)    UView.findViewById(PView,R.id.LLCompTiles);//~v@21I~//~9927R~
////      LinearLayout llllImage    =(LinearLayout)    UView.findViewById(PView,R.id.LLLLCompTiles);//~9926I~//~9927R~
//        ViewGroup.LayoutParams llp=llImage.getLayoutParams();      //~v@21I~//~9812R~//~9814R~//~9927R~
////      LinearLayout.LayoutParams llp=(LinearLayout.LayoutParams)llImage.getLayoutParams();   //~9812I~//~9814R~//~9927R~
////      boolean swSingleLine=true;                                 //~9302R~//~9927R~
//        boolean swReacher=PeswnReach!=-1;                          //~9302I~//~9927R~
////      if (pairCtr!=0)                                            //~9219I~//~9302R~//~9927R~
////        if (pairCtr!=0 && !swReacher)  //default single for reacher//~9302I~//~9311R~//~9927R~
////        {                                                          //~9302I~//~9311R~//~9927R~
//////          swSingleLine=calcHeight(swReacher,eswnReach);          //~9302R~//~9311R~//~9927R~
//////          if (swSingleLine)                                      //~9302R~//~9311R~//~9927R~
//////              llp.height=pieceH+CompDlgTiles.SPACING_Y*2;        //~9302R~//~9311R~//~9927R~
//////          else                                                   //~9302R~//~9311R~//~9927R~
////                llp.height=pieceH+pairPieceH+CompDlgTiles.SPACING_Y*3;//~9302I~//~9311R~//~9927R~
////        }                                                          //~9302I~//~9311R~//~9927R~
////        else                                                       //~9219I~//~9311R~//~9927R~
////          llp.height=pieceH+CompDlgTiles.SPACING_Y*2;            //~9219I~//~9925R~//~9927R~
//        int heightTileImage=pieceH+CompDlgTiles.SPACING_Y*2;       //~9925I~//~9927R~
////      llp.width=getHandsMaxWidth();                              //~9812R~//~9815R~//~9927R~
//        int widthTileImage=getHandsMaxWidth(PeswnReach==-1/*swComp*/,pairctr);//~9815R~//~9927R~
//        llp.width=widthTileImage;                                  //~9815I~//~9925R~//~9927R~
//        llp.height=heightTileImage;                                //~9925I~//~9927R~
//        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout llImage llp w="+llp.width+",h="+llp.height);//~9925I~//~9927R~
////      llp.width=ViewGroup.LayoutParams.WRAP_CONTENT;  //TODO test//~9925R~//~9927R~
////      LinearLayout.LayoutParams llp2=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);//~9812I~//~9927R~
//        llImage.setLayoutParams(llp);                              //~v@21I~//~9812R~//~9925R~//~9927R~
////      llllImage.setLayoutParams(llp);                            //~9926I~//~9927R~
////      llImage.setLayoutParams(llp2);                             //~9812I~//~9925R~//~9927R~
////      if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout llImage llp2 w="+llp2.width+",h="+llp2.height);//~9925R~//~9927R~
////      LinearLayout.LayoutParams ivp=new LinearLayout.LayoutParams(llp.width,llp.height);//~9812I~//~9925R~//~9927R~
//        LinearLayout.LayoutParams ivp=new LinearLayout.LayoutParams(widthTileImage,heightTileImage);//~9925I~//~9927R~
//        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout pieceH="+pieceH+",pairPieceH="+pairPieceH+",layoutparm w="+llp.width+",h="+llp.height);//~v@21R~//~9301R~//~9927R~
////      if (!swByXml)                                              //~9218I~//~9302R~//~9927R~
////      {                                                          //~9218I~//~9302R~//~9927R~
//            CompDlgTiles v=new CompDlgTiles(AG.context);           //~9218R~//~9927R~
//            v.parmCompStat=PcompStat;                              //~9815I~//~9927R~
////          v.setLayoutParams(ivp);                                //~9812I~//~9925R~//~9927R~
//            if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout CompDlgTiles ivp w="+ivp.width+",h="+ivp.height);//~9925R~//~9927R~
//            v.eswnReach=PeswnReach;                                //~9301R~//~9302R~//~9927R~
//            v.swReacher=swReacher;                                 //~9302R~//~9927R~
//            v.pairHH=pairPieceH;                                  //~9302R~//~9927R~
//            v.pairWW=pairPieceW;                                   //~9302I~//~9927R~
//            v.swPending=PswPending; //drawnLast open Pending       //~9309I~//~9927R~
//            v.widthTileImage=widthTileImage;                       //~9815I~//~9927R~
//            v.setMinimumWidth(widthTileImage);                   //~9927R~
////          llImage.setMinimumWidth(widthTileImage);             //~9927R~
////          if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout setMinimumWidth w="+v.widthTileImage);//~9927R~
////          llImage.addView(v,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));//~9218I~//~9812R~//~9927R~
////          ViewGroup.LayoutParams llv=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);//~9812I~//~9927R~
////          LinearLayout.LayoutParams llv=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);//~9812R~//~9927R~
////          LinearLayout.LayoutParams llv=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);//~9812I~//~9927R~
////          LinearLayout.LayoutParams llv=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);//~9812I~//~9927R~
////          llImage.addView(v,llv);                                //~9812R~//~9813R~//~9814R~//~9927R~
////          llImage.addView(v);                                    //~9814I~//~9925R~//~9927R~
////          llImage.addView(v);                                    //~9925I~//~9927R~
//            llImage.addView(v,ivp);                                //~9812I~//~9813R~//~9925R~//~9927R~
////            v.swSingleLine=swSingleLine;                           //~9302I~//~9927R~
////          ViewGroup.LayoutParams lliv=v.getLayoutParams();       //~9814M~//~9925R~//~9927R~
////          v.layoutDialog=PView;                                  //~9925I~//~9927R~
////          v.llDialog=(LinearLayout)UView.findViewById(PView,R.id.DialogContainer);//TODO test //~9925R~//~9926R~//~9927R~
////          if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout llDialog before setLayoutParams w="+v.llDialog.getWidth());//~9926I~//~9927R~
////          v.llDialog.setLayoutParams(ivp);                         //~9925R~//~9926R~//~9927R~
////          v.llDialog.setMinimumWidth(widthTileImage);          //~9927R~
//            if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout setLayoutParams for DialogContainer ivp w="+ivp.width+",h="+ivp.height);//~9925I~//~9927R~
////          if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout llDialog after setLayoutParams w="+v.llDialog.getWidth());//~9926I~//~9927R~
//            return v;                                              //~9218I~//~9927R~
////      }                                                          //~9218I~//~9302R~//~9927R~
////      return null;                                               //~9218I~//~9302R~//~9927R~
//    }                                                              //~v@21I~//~9927R~
    //******************************************                   //~9927I~
    public static CompDlgTiles setImageLayout(View PView,int PeswnReach,boolean PswPending,Complete.Status PcompStat)//~9927I~
    {                                                              //~9927I~
        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout eswnReach="+PeswnReach+",PView="+PView.toString());//~9927I~
        GCanvas gcanvas = AG.aGCanvas;                             //~9927I~
        MJTable table = gcanvas.table;                             //~9927I~
        int pieceH=table.handPieceH;                               //~9927I~
        Point p=table.getPairPieceSize();                          //~9927I~
        int pairPieceH=p.y;                                        //~9927I~
        int pairPieceW=p.x;                                        //~9927I~
    	int pairctr=getPairCtr(PeswnReach,PcompStat);              //~9927I~
        LinearLayout llImage    =(LinearLayout)    UView.findViewById(PView,R.id.LLCompTiles);//~9927I~
        ViewGroup.LayoutParams llp=llImage.getLayoutParams();      //~9927I~
        boolean swReacher=PeswnReach!=-1;                          //~9927I~
        int heightTileImage=pieceH+CompDlgTiles.SPACING_Y*2;       //~9927I~
        int widthTileImage=getHandsMaxWidth(PeswnReach==-1/*swComp*/,pairctr);//~9927I~
        llp.width=widthTileImage;                                  //~9927I~
        llp.height=heightTileImage;                                //~9927I~
        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout llImage llp w="+llp.width+",h="+llp.height);//~9927I~
      	llImage.setLayoutParams(llp);                              //~9927I~
        LinearLayout.LayoutParams ivp=new LinearLayout.LayoutParams(widthTileImage,heightTileImage);//~9927I~
        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout pieceH="+pieceH+",pairPieceH="+pairPieceH+",layoutparm w="+llp.width+",h="+llp.height);//~9927I~
        CompDlgTiles v=new CompDlgTiles(AG.context);               //~9927I~
        v.parmCompStat=PcompStat;                                  //~9927I~
        v.eswnReach=PeswnReach;                                    //~9927I~
        v.swReacher=swReacher;                                     //~9927I~
        v.pairHH=pairPieceH;                                       //~9927I~
        v.pairWW=pairPieceW;                                       //~9927I~
        v.swPending=PswPending; //drawnLast open Pending           //~9927I~
        v.widthTileImage=widthTileImage;                           //~9927I~
        v.heightTileImage=heightTileImage;                         //~0326I~
        llImage.addView(v,ivp);                                    //~9927I~
        if (Dump.Y) Dump.println("CompDlgTiles.setImageLayout CompDlgTiles ivp w="+ivp.width+",h="+ivp.height);//~9927I~
        return v;                                                  //~9927I~
    }                                                              //~9927I~
    //*************************
    private void init(Context Pcontext)                            //~v@@@R~
    {
//  	dlg=AG.aCompleteDlg;                                       //~v@21R~//~9301R~
		if (Dump.Y) Dump.println("CompDlgTiles.init context="+Pcontext.toString());//~v@@@I~//~v@21R~
        gcanvas = AG.aGCanvas;                                     //~v@21I~
        table = gcanvas.table;                                     //~v@21I~
        pieces=table.pieces;                                       //~v@21I~
        bgColor=COLOR_BG_TABLE;                                    //~v@21I~
        pieceW=table.handPieceW;                                   //~v@21I~
        pieceH=table.handPieceH;                                   //~v@21I~
//      stockPieceH=AG.aStock.pieceH;                              //~v@21I~//~9219R~
//      stockPieceW=AG.aStock.pieceW;                              //~v@21I~//~9219R~
//      earthPieceH=AG.aEarth.piecePairH;                              //~v@21I~//~9301R~
//      earthPieceW=AG.aEarth.piecePairW;                              //~v@21I~//~9301R~
        PLS=AG.aPlayers;                                                           //~v@21I~
//      shuffled=AG.aTiles.getShuffled();                          //~v@21I~//~9301R~
//      bmsssRiver=pieces.bitmapAllPiecesRiver;                    //~v@21I~//~9301R~
        UView.setWillNotDraw(this,false);  //enable onDraw() callback     //~v@@@R~//~v@21R~//~9812I~
    }
//**********************************************************       //~v@@@I~//~v@21I~
    private void setLayoutSize()        //~v@@@R~                  //~v@21I~
    {                                                              //~v@@@I~//~v@21I~
//        if (Dump.Y) Dump.println("CompDlgTitle.setLayoutSize pieceW="+pieceW+",pieceH="+pieceH);//~v@21R~
//        ViewGroup.LayoutParams lp=getLayoutParams();   //~v@@@I~ //~v@21R~
//        lp.width=-1;        //fill parent                        //~v@21R~
//        lp.height=pieceH*3;                              //~v@@@R~//~v@21R~
//        View parent=(View)getParent();                           //~v@21R~
//        parent.setLayoutParams(lp);                           //~v@@@I~//~v@21R~
    }                                                              //~v@@@I~//~v@21I~
//    //***********************************************************//~v@21R~
//    public void paint()                                          //~v@21R~
//    {                                                            //~v@21R~
//        if (Dump.Y) Dump.println("CompDlgTiles:paint issue postInvalidate");//~v@21R~
//        postInvalidate();   //call onDraw                        //~v@21R~
//    }                                                            //~v@21R~
    //***********************************************************  //~v@21I~
    @Override                                                    //~v@@@R~//~v@21R~
    public void onDraw(Canvas Pcanvas/*android.graphics.Canvas*/)                           //~v@@@R~//~v@21R~
    {                                                            //~v@@@R~//~v@21R~
        try                                                        //~0327I~
        {                                                          //~0327I~
            onDrawSub(Pcanvas);                                    //~0327I~
        }                                                          //~0327I~
        catch(Exception e)                                         //~0327I~
        {                                                          //~0327I~
    		Dump.println(e,"CompDlgTiles.onDraw");                 //~0327I~
        }                                                          //~0327I~
    }                                                              //~0327I~
    private void onDrawSub(Canvas Pcanvas/*android.graphics.Canvas*/)//~0327I~
    {                                                              //~0327I~
//  	if (Dump.Y) Dump.println("CompReqDlg.onDraw DialogContainer width="+((View)llDialog).getWidth());//~9925R~//~9926R~//~9927R~
        View parent=(View)getParent();                                    //~v@21I~
        if (Dump.Y) Dump.println("CompDlgTiles.onDraw thisID="+Integer.toHexString(getId())+",parent id="+Integer.toHexString(parent.getId())+",W="+parent.getWidth()+",H="+parent.getHeight());//~v@21I~//~9309R~//~9810R~
        if (Dump.Y) Dump.println("CompDlgTiles.onDraw swReacher="+swReacher+",swPending="+swPending);             //~v@@@R~//~v@21R~//~9301R~//~9309R~
        if (Dump.Y) Dump.println("CompDlgTiles.pathWidth="+UView.showParentPathWidth(parent));//~0327I~
        getCompleteStatus();                                       //~9311I~
        WW=getWidth();                                             //~v@21R~
        HH=getHeight();                                            //~v@21R~
	    if (Dump.Y) Dump.println("CompDlgTiles.onDraw WW="+WW+",HH="+HH);//~v@21R~
      if (false)    //TODO Test                                    //~0326I~
      {                                                            //~0326I~
        if (requestLayout(parent))                                 //~9302I~
        	return;                                                //~9302I~
      }                                                            //~0326I~
      else                                                         //~0326I~
      {                                                            //~0326I~
        if (requestLayoutParent(parent))                           //~0326I~
        	return;                                                //~0326I~
      }                                                            //~0326I~
//      getCompleteStatus();                                       //~v@21I~//~9311R~
        canvas=Pcanvas;                                            //~v@21I~
        int endPosHand=drawHands();                                //~v@21R~
//      swDrawDoraRight=endPosHand<WW/2;                           //~v@21I~//~9219R~
//		if (pairCtr!=0)                                            //~9219I~//~9228R~//~9301R~//~9815R~
		swChkEarthPosition=true;                                   //~0326I~
	    	drawEarth();                                               //~v@21I~//~9219R~//~9228R~//~9301R~
		swChkEarthPosition=false;                                  //~0326I~
        if (firstPosEarth!=0) //pairctr!=0                         //~0326I~
        {                                                          //~0326I~
//      	int lenEarth=endPosEarth-firstPosEarth;                //~0327I~
            int d=firstPosEarth-endPosHand;                        //~0326R~
            if (d>0)                                               //~0326R~
                if (d>pieceW)                                      //~0326R~
                    shiftEarth=-(d-pieceW); //shift to left        //~0326R~
                else                                               //~0326R~
                    shiftEarth=0;                                  //~0326R~
            else                                                   //~0326R~
                shiftEarth=-d;   //shift to right, it may overflow //~0326R~
            if (Dump.Y) Dump.println("CompDlgTiles.onDraw pieceW="+pieceW+",firstPosEarth="+firstPosEarth+",endPoshand="+endPosHand+",dist="+d+",shiftEarth="+shiftEarth);//~0326R~
            drawEarth();                                           //~0326R~
        }                                                          //~0326I~
//      ctrKanDrawn=AG.aStock.ctrKanDrawn;                         //~v@21I~//~9301R~
//      drawDoraHidden(drawDora());                                //~v@21I~//~9219R~
    }                                                            //~v@@@R~//~v@21R~
    //***********************************************************  //~v@21I~
    private void getCompleteStatus()                               //~v@21I~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("CompDlgTiles.getCompleteStatus swReacher="+swReacher+",swPending="+swPending);//~9309I~
    	if (swReacher)                                             //~9228I~
        {                                                          //~9228I~
        	player=Accounts.eswnToPlayer(eswnReach);               //~9301I~
        	return;                                                //~9228I~
        }                                                          //~9228I~
    	if (swPending)                                             //~9309I~
        {                                                          //~9309I~
        	player=Accounts.eswnToPlayer(eswnReach);               //~9309I~
        	return;                                                //~9309I~
        }                                                          //~9309I~
        if (parmCompStat!=null)                                    //~9519I~
	    	compStat=parmCompStat; //eswn+type                     //~9519I~
        else                                                       //~9519I~
	    	compStat=Status.getCompleteStatus(); //eswn+type           //~v@21I~//~9519R~
    	compEswn=compStat.completeEswn;                            //~v@21R~
    	compType=compStat.completeType;                            //~v@21R~
    	compEswnLooser=compStat.completeEswnLooser;                //~v@21R~
    	compTD=compStat.completeTD;                                    //~v@21R~
//  	compTDKanTaken=compStat.completeKanTakenTD;                    //~v@21R~//~9301R~
        swTake=(compType & (COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0;//~v@21I~
    	player=Accounts.eswnToPlayer(compEswn);                    //~v@21R~
//  	player=PLAYER_YOU;                                         //~v@21R~
        if (Dump.Y) Dump.println("CompDlgTiles.getCompleteStatus player="+player+",compEswn="+compEswn+",compType="+compType+",loosereswn="+compEswnLooser+",swTake="+swTake);//~v@21R~
    }                                                              //~v@21I~
    //***********************************************************  //~9302I~
    //*under PairCtr!=0                                            //~9302I~
    //***********************************************************  //~9302I~
    private boolean requestLayout(View Pparent)             //~9302I~//~9812R~//~9814R~
    {                                                              //~9302I~
        boolean rc=false;                                                //~9302I~
        if (Dump.Y) Dump.println("CompDlgTiles.requestLayout swRequestLayout="+swRequestedLayout+",swReacher="+swReacher+",swPending="+swPending+",eswnReach="+eswnReach+",swRequestedLayout="+swRequestedLayout);//~9302R~//~9309R~//~9809R~
        View pp=(View)Pparent.getParent();                         //~9814I~
        if (Dump.Y) Dump.println("CompDlgTiles.requestLayout parent w="+Pparent.getWidth()+",h="+Pparent.getHeight()+",id="+Integer.toHexString(Pparent.getId())+"="+Pparent.toString());//~9925R~//~9926R~
        if (Dump.Y) Dump.println("CompDlgTiles.requestLayout parent of parent w="+pp.getWidth()+",h="+pp.getHeight()+",id="+Integer.toHexString(pp.getId())+"="+pp.toString());//~9926R~
//      View ppp=(View)pp.getParent();	//TODO test                //~9925R~
//      if (Dump.Y) Dump.println("CompDlgTiles.requestLayout parent of parent of parent w="+ppp.getWidth()+",h="+ppp.getHeight()+",id="+ppp.toString());//~9925R~
//        if (!swReacher)     //expand                               //~9302R~//~9311R~
//            return false;                                          //~9302I~//~9311R~
    	if (swRequestedLayout)                                    //~9302I~
        {                                                          //~9926I~
	        if (Dump.Y) Dump.println("CompDlgTiles.requestLayout swRequestLayout:true return");//~9926I~
			return false;                                          //~9302I~
        }                                                          //~9926I~
//  	int wwHands=getReachHandsWidth(player,pieceW);                 //~9302I~//~9406R~
    	int wwHands=getReachHandsWidth(player,pieceW,swReacher);   //~9406I~
    	int wwEarth=getReachEarthWidth(player,pairWW);             //~9302I~
    	int maxw=getHandsMaxWidth(true/*comp*/,0/*pairctr*/);                               //~9411R~//~9926R~
//      swSingleLine=WW-(wwHands+wwEarth)>SPACING_EARTH;           //~9302R~//~9411R~//~9815R~
        swSingleLine=true; //fully include by setLayout            //~9815I~
//      swSingleLine=maxw-(wwHands+wwEarth)>SPACING_EARTH;         //~9411R~
        if (Dump.Y) Dump.println("CompDlgTiles.requestLayout oldHH="+Pparent.getLayoutParams().height);//~9302I~
        if (!swSingleLine)                                                    //~9302I~//~9411R~
        {                                                          //~9302I~//~9814R~
//            int doublelineH=pieceH+pairHH+SPACING_Y*3;//~9302R~  //~9814R~
//            Pparent.getLayoutParams().height=doublelineH;        //~9814I~
////            if (WW<maxw)                                         //~9411R~//~9814R~
////                Pparent.getLayoutParams().width=maxw;            //~9411R~//~9814R~
////          Pparent.requestLayout();                                //~9302I~//~9814R~
//            setDoubleLineLayout(Pparent);                        //~9814I~
//            swRequestedLayout=true;                              //~9814R~
			setDoubleLineLayout(Pparent);                          //~9814I~
	        rc=true;//~9302I~
        }                                                          //~9302I~
//        else                                                     //~9411R~
//        if (WW<maxw)                                             //~9411R~
//        {                                                        //~9411R~
//            Pparent.getLayoutParams().width=maxw;                //~9411R~
//            Pparent.requestLayout();                             //~9411R~
//            swRequestedLayout=true;                              //~9411R~
//        }                                                        //~9411R~
        else                                                       //~9926I~
        {                                                          //~9926I~
	        if (Dump.Y) Dump.println("CompDlgTiles.requestLayout requestLayout swSingleLine maxw="+maxw+",Pparent="+Pparent.toString());//~9926I~
            Pparent.getLayoutParams().width=maxw;                  //~9926I~
            Pparent.requestLayout();                               //~9926I~
            swRequestedLayout=true;                                //~9926I~
	        LinearLayout.LayoutParams ivp=new LinearLayout.LayoutParams(824,89);//~9926I~
//    		llDialog.setLayoutParams(ivp);   //TODO test           //~9926I~
//          llDialog.requestLayout();                              //~9926I~//~9927R~
//  		if (Dump.Y) Dump.println("CompReqDlg.requestLayout DialogContainer width="+((View)llDialog).getWidth()+",tostring="+llDialog.toString());//~9926I~//~9927R~
        }                                                          //~9926I~
        if (Dump.Y) Dump.println("CompDlgTiles.requestLayout swSingleLine="+swSingleLine+",WW="+WW+",wwHands="+wwHands+",wwEarth="+wwEarth+",rc="+rc);//~9302R~//~9809R~
		return rc;                                                 //~9302I~
    }                                                              //~9302I~
    //***********************************************************  //~0326I~
    //*rc:true:skip draw                                           //~0326R~
    //***********************************************************  //~0326I~
    private boolean requestLayoutParent(View Pparent)              //~0326I~
    {                                                              //~0326I~
        int ww=Pparent.getWidth();                                 //~0326I~
		int hh=Pparent.getHeight();                                //~0326I~
        if (Dump.Y) Dump.println("CompDlgTiles.requestLayoutParent swRequestLayout="+swRequestedLayout+",parent ww="+ww+",hh="+hh+",id="+Integer.toHexString(Pparent.getId())+"="+Pparent.toString());//~0326R~
        if (Dump.Y) Dump.println("CompDlgTiles.requestLayoutParent widthTileImage="+widthTileImage);//~0326I~
	    if (Dump.Y) Dump.println("CompDlgTiles.requestLayoutParent old w="+Pparent.getLayoutParams().width+",layoutParms="+Pparent.getLayoutParams().toString());//~0326I~
        boolean rc;	                                               //~0326I~
        swSingleLine=true; //fully include by setLayout            //~0326I~
    	if (ww<widthTileImage)                                     //~0326I~
        {                                                          //~0326I~
            if (swRequestedLayout)                                 //~0326I~
            {                                                      //~0326I~
                if (Dump.Y) Dump.println("CompDlgTiles.requestLayoutParent @@@@ shor but swRequestLayout:true return");//~0326I~
                return false;   //draw anyway                      //~0326R~
            }                                                      //~0326I~
        	Pparent.getLayoutParams().width=widthTileImage;        //~0326R~
	        Pparent.requestLayout();                               //~0326I~
    	    swRequestedLayout=true;                                //~0326I~
            rc=true;	//skip draw                                //~0326R~
	        if (Dump.Y) Dump.println("CompDlgTiles.requestLayoutParent new w="+Pparent.getLayoutParams().width+",layoutParms="+Pparent.getLayoutParams().toString());//~0326R~
        }                                                          //~0326I~
        else                                                       //~0326I~
        	rc=false;   //draw                                     //~0326R~
	    if (Dump.Y) Dump.println("CompDlgTiles.requestLayoutParent rc="+rc);//~0326I~
		return rc;      //draw                                     //~0326I~
    }                                                              //~0326I~
    //***********************************************************  //~9814I~
    private void setDoubleLineLayout(View Pparent)                 //~9814I~
    {                                                              //~9814I~
    	int doublelineH=pieceH+pairHH+SPACING_Y*3;                 //~9814I~
        if (Dump.Y) Dump.println("CompDlgTiles.setDoubleLineLayout doubleH="+doublelineH+",WW="+WW);//~9814I~
        Pparent.getLayoutParams().height=doublelineH;              //~9814I~
        Pparent.requestLayout();                                   //~9814I~
        swRequestedLayout=true;                                    //~9814I~
        LinearLayout.LayoutParams ivp=new LinearLayout.LayoutParams(WW,doublelineH);//~9814I~
		this.setLayoutParams(ivp);                                 //~9814I~
    }                                                              //~9814I~
//    //***********************************************************  //~9302I~//~9812I~//~0328R~
//    //*under PairCtr!=0                                            //~9302I~//~9812I~//~0328R~
//    //***********************************************************  //~9302I~//~9812I~//~0328R~
//    private boolean requestLayoutNew(View Pparent)             //~9302I~//~9812I~//~9814R~//~0328R~
//    {                                                              //~9302I~//~9812I~//~0328R~
//        boolean rc=false;                                                //~9302I~//~9812I~//~0328R~
//        if (Dump.Y) Dump.println("CompDlgTiles.requestLayoutNew swRequestLayout="+swRequestedLayout+",swReacher="+swReacher+",swPending="+swPending+",eswnReach="+eswnReach+",swRequestedLayout="+swRequestedLayout);//~9302R~//~9309R~//~9809R~//~9812I~//~9925R~//~0328R~
////        if (!swReacher)     //expand                               //~9302R~//~9311R~//~9812I~//~0328R~
////            return false;                                          //~9302I~//~9311R~//~9812I~//~0328R~
////      if (swRequestedLayout)                                    //~9302I~//~9812I~//~0328R~
////          return false;                                          //~9302I~//~9812I~//~0328R~
////      int wwHands=getReachHandsWidth(player,pieceW);                 //~9302I~//~9406R~//~9812I~//~0328R~
//        int wwHands=getReachHandsWidth(player,pieceW,swReacher);   //~9406I~//~9812I~//~0328R~
//        int wwEarth=getReachEarthWidth(player,pairWW);             //~9302I~//~9812I~//~0328R~
////      int maxw=getHandsMaxWidth();                               //~9411R~//~9812I~//~0328R~
//        swSingleLine=WW-(wwHands+wwEarth)>SPACING_EARTH;           //~9302R~//~9411R~//~9812I~//~0328R~
////      swSingleLine=maxw-(wwHands+wwEarth)>SPACING_EARTH;         //~9411R~//~9812I~//~0328R~
//        if (Dump.Y) Dump.println("CompDlgTiles.requestLayout oldHH="+Pparent.getLayoutParams().height+",singleline="+swSingleLine);//~9302I~//~9812I~//~0328R~
//        if (!swSingleLine)                                                    //~9302I~//~9411R~//~9812I~//~0328R~
//        {                                                          //~9302I~//~9812I~//~0328R~
//            Pparent.getLayoutParams().height=pieceH+pairHH+SPACING_Y*3;//~9302R~//~9812I~//~0328R~
////            if (WW<maxw)                                         //~9411R~//~9812I~//~0328R~
////                Pparent.getLayoutParams().width=maxw;            //~9411R~//~9812I~//~0328R~
////          Pparent.requestLayout();                                //~9302I~//~9812I~//~0328R~
////          swRequestedLayout=true;                                //~9812I~//~0328R~
////          rc=true;//~9302I~                                      //~9812I~//~0328R~
//        }                                                          //~9302I~//~9812I~//~0328R~
//        if (swRequestedLayout)                                     //~9812I~//~0328R~
//            rc=false;                                              //~9812I~//~0328R~
//        else                                                       //~9812I~//~0328R~
//        {                                                          //~9812I~//~0328R~
//            if (Dump.Y) Dump.println("CompDlgTiles.requestLayout issue requestLayout");//~9812I~//~0328R~
//            swRequestedLayout=true;                                //~9812I~//~0328R~
//            Pparent.invalidate();                                  //~9812I~//~0328R~
//            Pparent.requestLayout();                               //~9812I~//~0328R~
//            rc=true;    //draw at next cycle onDraw                //~9812I~//~0328R~
//        }                                                          //~9812I~//~0328R~
////        else                                                     //~9411R~//~9812I~//~0328R~
////        if (WW<maxw)                                             //~9411R~//~9812I~//~0328R~
////        {                                                        //~9411R~//~9812I~//~0328R~
////            Pparent.getLayoutParams().width=maxw;                //~9411R~//~9812I~//~0328R~
////            Pparent.requestLayout();                             //~9411R~//~9812I~//~0328R~
////            swRequestedLayout=true;                              //~9411R~//~9812I~//~0328R~
////        }                                                        //~9411R~//~9812I~//~0328R~
//        if (Dump.Y) Dump.println("CompDlgTiles.requestLayout swSingleLine="+swSingleLine+",WW="+WW+",wwHands="+wwHands+",wwEarth="+wwEarth+",rc="+rc);//~9302R~//~9809R~//~9812I~//~0328R~
//        return rc;                                                 //~9302I~//~9812I~//~0328R~
//    }                                                              //~9302I~//~9812I~//~0328R~
//    //***********************************************************//~9302R~
//    //*under PairCtr!=0                                          //~9302R~
//    //***********************************************************//~9302R~
//    private static boolean calcHeight(boolean PswReacher,int PeswnReach)//~9302R~
//    {                                                            //~9302R~
//        boolean rc;                                              //~9302R~
//        if (Dump.Y) Dump.println("CompDlgTiles.calcHeight swReacher="+PswReacher+",eswnReach="+PeswnReach);//~9302R~
//        if (!PswReacher)                                         //~9302R~
//            return false;                                        //~9302R~
//        MJTable table = AG.aMJTable;                             //~9302R~
//        int ww=table.handPieceW;                                 //~9302R~
//        int hh=table.handPieceH;                                 //~9302R~
//        Point p=table.getPairPieceSize();                        //~9302R~
//        int pairhh=p.y;                                          //~9302R~
//        int pairww=p.x;                                          //~9302R~
//        int player=Accounts.eswnToPlayer(PeswnReach);            //~9302R~
//        int wwHands=getReachHandsWidth(player,ww);               //~9302R~
//        int wwEarth=getReachEarthWidth(player,pairww);           //~9302R~
////      int wwDlg=PView.getWidth();                              //~9302R~
////      rc=(wwDlg-(wwHands+wwEarth-wwDlg))>=SPACING_EARTH;       //~9302R~
//        rc=wwHands>wwEarth;                                      //~9302R~
//        if (Dump.Y) Dump.println("CompDlgTiles.calcHeight wwHands="+wwHands+",wwEarth="+wwEarth+",rc="+rc);//~9302R~
//        return rc;                                               //~9302R~
//    }                                                            //~9302R~
    //***********************************************************  //~v@21I~
    private int drawHands()                                        //~v@21R~
    {                                                              //~v@21I~
		Bitmap bm;                                                 //~v@21I~
        TileData td;                                               //~v@21I~
    //********************                                         //~v@21I~
        if (Dump.Y) Dump.println("CompDlgTiles.drawHands");        //~v@21I~
        TileData[] tds=AG.aPlayers.getHands(player);               //~v@21I~
        int ctr=tds.length;                                        //~v@21I~
        Rect rectHands=new Rect(0,0,WW,HH);                        //~v@21R~
        if (Dump.Y) Dump.println("CompDlgTiles.drawHands ctr="+ctr+",WW="+WW+",HH="+HH);//~v@21R~//~9810R~
//      Graphics.drawRect(canvas,rectHands,bgColor);               //~v@21I~//~9814R~
        int xx=HANDS_POSX;                                         //~v@21R~
        int yy=HANDS_POSY;                                         //~v@21R~
        boolean swTaken=Tiles.isTakenStatus(ctr);                  //~v@21I~
        stroke_width=AG.aHands.complete_stroke_width_hand;        //~0401I~
        for (int ii=0;ii<ctr;ii++)                                 //~v@21I~
        {                                                          //~v@21I~
            td=tds[ii];                                            //~v@21R~
            if (Dump.Y) Dump.println("CompDlgTiles.drawHand swTaken="+swTaken+",ii="+ii+",td="+td.toString());//~v@21R~
            bm=pieces.getBitmapHand(td);                           //~v@21R~
//          handsHH=bm.getHeight();                                //~v@21I~//~9219R~
        	if (swTaken && ii==ctr-1)                              //~v@21I~
            {                                                      //~v@21I~
                drawPiece(new Rect(xx,yy,xx+pieceW,yy+pieceH),new Point(xx,yy),bm,false);//~v@21R~
            }                                                      //~v@21I~
            else                                                   //~v@21I~
            {                                                      //~v@21I~
                Graphics.drawBitmap(canvas,bm,xx,yy);              //~v@21I~
            }                                                      //~v@21I~
            if (swTaken && ii==ctr-2)                              //~v@21I~
                xx+=pieceW+PIECE_SPACING_TAKEN;                    //~v@21I~
            else                                                   //~v@21I~
                xx+=pieceW+PIECE_SPACING;                          //~v@21I~
        }                                                          //~v@21I~
        td=compTD;                                                 //~v@21R~
        if (!swTaken && td!=null)                                  //~v@21R~
        {                                                          //~v@21I~
        	if (Dump.Y) Dump.println("CompDlgTiles.drawHands ,compTD="+TileData.toString(td));//~v@21R~
        	xx+=PIECE_SPACING_TAKEN;                               //~v@21I~
            bm=pieces.getBitmapHand(td);                           //~v@21I~
            drawPiece(new Rect(xx,yy,xx+pieceW,yy+pieceH),new Point(xx,yy),bm,true);//~v@21I~
            xx+=pieceW;                                            //~v@21I~
        }                                                          //~v@21I~
//        td=compTDKanTaken;                                       //~v@21R~
//        if (td!=null)                                            //~v@21R~
//        {                                                        //~v@21R~
//            td=compTD;                                           //~v@21R~
//            if (Dump.Y) Dump.println("CompDlgTiles.drawHands ,KanTakenTD="+TileData.toString(td));//~v@21R~
//            xx+=PIECE_SPACING_TAKEN*3;                           //~v@21R~
//            bm=pieces.getBitmapHand(td);                         //~v@21R~
//            drawPiece(new Rect(xx,yy,xx+pieceW,yy+pieceH),new Point(xx,yy),bm,true);//~v@21R~
//            xx+=pieceW;                                          //~v@21R~
//        }                                                        //~v@21R~
        if (Dump.Y) Dump.println("CompDlgTiles.drawHand pieceW="+pieceW+",xx="+xx);//~9311I~
        return xx;                                                 //~v@21I~
    }                                                              //~v@21I~
    //***********************************************************  //~9302I~
    private static int getReachHandsWidth(int Pplayer,int PpieceW,boolean PswReacher)        //~9302I~//~9406R~
    {                                                              //~9302I~
//  	Bitmap bm;                                                 //~9302I~//~9411R~
        TileData td;                                               //~9302I~
    //********************                                         //~9302I~
        if (Dump.Y) Dump.println("CompDlgTiles.getReachHandsWidth player="+Pplayer+",pieceW="+PpieceW);//~9302I~//~9814R~
        TileData[] tds=AG.aPlayers.getHands(Pplayer);              //~9302I~
        int ctr=tds.length;                                        //~9302I~
        int xx=HANDS_POSX+(PpieceW+PIECE_SPACING)*ctr;             //~9302I~
//      if (!Tiles.isTakenStatus(ctr))                             //~9311R~//~9406R~
        if (!PswReacher && !Tiles.isTakenStatus(ctr))              //~9406I~
        {                                                          //~9311I~
        	if (Dump.Y) Dump.println("CompDlgTiles.getReachhandsWidth more 1 tile for ron");//~9311R~
        	xx+=PIECE_SPACING_TAKEN+PpieceW;                       //~9311I~
        }                                                          //~9311I~
        if (Dump.Y) Dump.println("CompDlgTiles.getReachHandsWidth player="+Pplayer+",pieceW="+PpieceW+",xx="+xx);//~9311I~//~9814R~
        return xx;                                                 //~9302I~
    }                                                              //~9302I~
    //***********************************************************  //~9411I~
//  public  static int getHandsMaxWidth()                          //~9411I~//~9815R~
    public  static int getHandsMaxWidth(boolean PswComp,int Ppairctr)//~9815R~
    {                                                              //~9411I~
    	int xx;                                                    //~9814I~
        if (Dump.Y) Dump.println("CompDlgTiles.getHandsMaxWidth swComp="+PswComp+",pairctr="+Ppairctr);//~9815I~
//      if (!PswComp)                                              //~9815R~
//      {                                                            //~9814I~//~9815R~
//        int hpw=AG.aMJTable.handPieceW;                            //~9411I~//~9815R~
////      xx=HANDS_POSX+(hpw+PIECE_SPACING)*HANDCTR+PIECE_SPACING_TAKEN+hpw;//~9411I~//~9814R~//~9815R~
////      xx+=SPACING_EARTH+1;                                       //~9411R~//~9815R~
//        xx=HANDS_POSX+(hpw+PIECE_SPACING)*HANDCTR+HANDS_POSX;    //~9815R~
//        if (Dump.Y) Dump.println("CompDlgTiles.getHandsMaxWidth pieceW="+hpw+",width="+xx);//~9411I~//~9814M~//~9815R~
//      }                                                            //~9814I~//~9815R~
//      else                                                         //~9814I~//~9815R~
//      {                                                            //~9814I~//~9815R~
        if (Ppairctr==0)                                           //~9815R~
        {                                                          //~9815I~
        	int hpw=AG.aMJTable.handPieceW;                        //~9815M~
      	  if (!PswComp)                                            //~9815I~
            xx=HANDS_POSX+(hpw+PIECE_SPACING)*HANDCTR+HANDS_POSX;  //~9815I~
          else                                                     //~9815I~
			xx=HANDS_POSX+(hpw+PIECE_SPACING)*HANDCTR+PIECE_SPACING_TAKEN+hpw+HANDS_POSX;//~9815I~
	        if (Dump.Y) Dump.println("CompDlgTiles.getHandsMaxWidth pairctr=0 pieceW="+hpw+",width="+xx);//~9815R~
        }                                                          //~9815I~
        else                                                       //~9815I~
        {                                                          //~9815I~
        	int left=AG.aHands.getHandsLeftYou();                      //~9814I~//~9815R~
        	int right=AG.aEarth.getEarthRightYou();                    //~9814I~//~9815R~
//      	xx=right-left+HANDS_POSX;                                  //~9814R~//~9815R~//~0327R~
        	xx=right-left+HANDS_POSX+EARTH_MARGIN_RIGHT*2;         //~0327I~
        	if (Dump.Y) Dump.println("CompDlgTiles.getHandsMaxWidth comp pairctr="+Ppairctr+",left="+left+",right="+right+",width="+xx);//~9814I~//~9815I~
            if (Ppairctr!=0)                                       //~0327I~
            {                                                      //~0327I~
	            int adjust=adjustKan(xx,PswComp,Ppairctr);	//len assumed earth is all kan//~0327I~
                if (adjust!=0)                                     //~0327I~
                	xx=adjust;                                     //~0327I~
            }                                                      //~0327I~
        }                                                          //~9815I~
//      }                                                            //~9814I~//~9815R~
        return xx;                                                 //~9411I~
    }                                                              //~9411I~
    //*******************************************************************//~0327I~
    //*assume all earth is minkan(when pairctr!=0)                 //~0327I~
    //*******************************************************************//~0327I~
    private static int adjustKan(int Pwidth,boolean PswComp,int Ppairctr)//~0327I~
    {                                                              //~0327I~
        int hpw=AG.aMJTable.handPieceW;                            //~0327I~
        if (Dump.Y) Dump.println("CompDlgTiles.adjustKan width="+Pwidth+",PswComplete="+PswComp+",pairctr="+Ppairctr);//~0327I~
        int ctrhands=HANDCTR-3*Ppairctr;                           //~0327R~
        int lenhands=HANDS_POSX+ctrhands*hpw;                      //~0327I~
        if (PswComp)                                               //~0327I~
        	lenhands+=PIECE_SPACING_TAKEN+hpw;                     //~0327I~
        if (Dump.Y) Dump.println("CompDlgTiles.adjustKan handPieceW="+hpw+",lenhands="+lenhands);//~0327I~
        int wwearth=AG.aEarth.piecePairW;                           //~0327I~
        int hhearth=AG.aEarth.piecePairH;                           //~0327I~
        int lenearth=(wwearth*3+hhearth+PIECE_SPACING*3)*Ppairctr+SPACING_X*(Ppairctr-1)+EARTH_MARGIN_RIGHT*2;//~0327I~
        if (Dump.Y) Dump.println("CompDlgTiles.adjustKan wwearth="+wwearth+",hhearth="+hhearth+",lenearth="+lenearth);//~0327I~
        int adjust=lenhands+lenearth+wwearth/2;                    //~0327I~
        int rc;                                                    //~0327I~
        if (adjust<Pwidth)                                         //~0327I~
        	rc=0;                                                  //~0327I~
        else                                                       //~0327I~
        	rc=adjust;                                             //~0327I~
        if (Dump.Y) Dump.println("CompDlgTiles.adjustKan adjust="+adjust+",rc="+rc);//~0327I~
        return rc;                                                 //~0327I~
    }                                                              //~0327I~
    //*******************************************************************//~v@21I~
    private void drawPiece(Rect Prect,Point Ppoint,Bitmap Pbitmap,boolean PswComplete)//~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("CompDlgTiles.drawPiece swComplete="+PswComplete);//~v@21R~
        if (PswComplete)                                           //~v@21R~
        {                                                          //~v@21I~
//      	Graphics.drawRectFrameBitmap(canvas,Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y,COMPLETE_STROKE_WIDTH,COMPLETE_COLOR);//~v@21I~//+0401R~
        	Graphics.drawRectFrameBitmap(canvas,Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y,stroke_width,COMPLETE_COLOR);//+0401I~
        }                                                          //~v@21I~
        else                                                       //~v@21I~
        {                                                          //~v@21I~
//      	Graphics.drawRectFrameBitmap(canvas,Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y,HandsTouch.WIDTH_SELECTED,HandsTouch.COLOR_SELECTED);//~v@21I~//+0401R~
        	Graphics.drawRectFrameBitmap(canvas,Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y,stroke_width,HandsTouch.COLOR_SELECTED);//+0401I~
        }                                                          //~v@21I~
        if (Dump.Y) Dump.println("CompDlgTiles.drawPiece stroke_width="+stroke_width);//+0401I~
    }                                                              //~v@21I~
    //*******************************************************************//~9219I~//~9815R~
//  private static int getPairCtr(int PeswnReach)                                //~9219I~//~9301R~//~9815R~
    private static int getPairCtr(int PeswnReach,Complete.Status PcompStat)//~9815I~
    {                                                              //~9219I~//~9815R~
        int player;                                                //~9301I~//~9815R~
    //*********************                                        //~9219I~//~9815R~
        if (PeswnReach==-1)                                        //~9301I~//~9815R~
        {                                                          //~9301I~//~9815R~
//          Complete.Status stat=Status.getCompleteStatus(); //eswn+type//~9219I~//~9301R~//~9815R~
            Complete.Status stat=PcompStat;                        //~9815I~
            player=Accounts.eswnToPlayer(stat.completeEswn);               //~9219I~//~9301R~//~9815R~
        }                                                          //~9301I~//~9815R~
        else                                                       //~9301I~//~9815R~
            player=Accounts.eswnToPlayer(PeswnReach);              //~9301I~//~9815R~
//        TileData[][] pairs=AG.aPlayers.getEarth(player);           //~9219I~//~9815R~
//        int pairctr=pairs.length;                                  //~9219I~//~9815R~
//        int ctr=0;                                                 //~9219I~//~9815R~
//        for (int ii=0;ii<pairctr;ii++)                             //~9219I~//~9815R~
//        {                                                          //~9219I~//~9815R~
//            TileData[] tds=pairs[ii];  //initialized by 4 null entry                            //~9219I~//~9815R~
//            if (tds==null)                                         //~9219I~//~9815R~
//                break;                                             //~9219I~//~9815R~
//            ctr++;                                                 //~9219I~//~9815R~
//        }                                                          //~9219I~//~9815R~
    	int ctr=getPairCtrActual(player);                          //~9815I~
        if (Dump.Y) Dump.println("CompDlgTiles.getPairCtr PeswnReach="+PeswnReach+",player="+player+",pairctr="+ctr);//~9219I~//~9301R~//~9815R~
        return ctr;                                                //~9815R~
    }                                                              //~9219I~//~9815R~
    //*******************************************************************//~9815I~
    private static int getPairCtrActual(int Pplayer)               //~9815I~
    {                                                              //~9815I~
        TileData[][] pairs=AG.aPlayers.getEarth(Pplayer);          //~9815I~
        int pairctr=pairs.length;                                  //~9815I~
        int ctr=0;                                                 //~9815I~
        for (int ii=0;ii<pairctr;ii++)                             //~9815I~
        {                                                          //~9815I~
            TileData[] tds=pairs[ii];  //initialized by 4 null entry//~9815I~
            if (tds==null)                                         //~9815I~
                break;                                             //~9815I~
            ctr++;                                                 //~9815I~
        }                                                          //~9815I~
        if (Dump.Y) Dump.println("CompDlgTiles.getPairCtrActual player="+Pplayer+",ctr="+ctr);//~9815R~
        return ctr;                                                //~9815I~
    }                                                              //~9815I~
    //*******************************************************************//~v@21I~
	public void drawEarth()                                        //~v@21I~
    {                                                              //~v@21I~
    //*********************                                        //~v@21I~
    	firstPosEarth=0; //for parctr=0                            //~0326I~
        TileData[][] pairs=PLS.getEarth(player);                       //~v@21I~//~9815R~
//      int pairctr=pairs.length;                                  //~v@21I~//~9815R~
	    int pairctr=getPairCtrActual(player);                      //~9815I~
        if (Dump.Y) Dump.println("CompDlgTiles.drawEarth pairctr="+pairctr+",WW="+WW);//~v@21R~//~0326R~
        if (pairctr==0)                                            //~9815I~
        	return;                                                //~9815I~
//      int xx=WW-SPACING_X;                                       //~v@21R~//~9815R~
//      int xx=WW;                                                 //~9815I~//~0327R~
        int xx=WW-EARTH_MARGIN_RIGHT;                              //~0327I~
        endPosEarth=xx;                                            //~0327I~
	  	if (!swChkEarthPosition)                                   //~0326R~
      		xx+=shiftEarth;                                        //~0326R~
//      int yy=SPACING_Y+pieceH+SPACING_Y;                             //~v@21I~//~9302R~
        int yy=swSingleLine ? (SPACING_Y+pieceH-pairHH) : (SPACING_Y+pieceH+SPACING_Y);//~9302I~
        for (int ii=0;ii<pairctr;ii++)                                 //~v@21I~
        {                                                          //~v@21I~
    		TileData[] tds=pairs[ii];                              //~v@21I~
            if (tds==null)                                         //~v@21I~
            	break;                                             //~v@21I~
//          TileData[] tdsSorted=AG.aEarth.sortPairTile(tds,PLAYER_YOU);//~v@21I~//~9301R~
            TileData[] tdsSorted=AG.aEarth.sortPairTile(tds,player);//~9301I~
			xx=drawPair(xx,yy,tdsSorted);                          //~v@21R~
            xx-=SPACING_X;                                       //~v@21R~//~9219R~
	        if (Dump.Y) Dump.println("CompDlgTiles.drawEarth xx="+xx);//~0326I~
        }                                                          //~v@21I~
        firstPosEarth=xx+SPACING_X;                                          //~0326I~//~0327R~
	    if (Dump.Y) Dump.println("CompDlgTiles.drawEarth firstPosEarth="+firstPosEarth);//~0326I~
    }                                                              //~v@21I~
    //*******************************************************************//~9302I~
    //*REACH has all standing piece and no chankan                 //~9302I~
    //*******************************************************************//~9302I~
	public static int getReachEarthWidth(int Pplayer,int PpieceW)  //~9302I~
    {                                                              //~9302I~
    //*********************                                        //~9302I~
        TileData[][] pairs=AG.aPlayers.getEarth(Pplayer);           //~9302I~//~9815R~
//      int pairctr=pairs.length;                                  //~9302I~//~9815R~
	    int pairctr=getPairCtrActual(Pplayer);                     //~9815I~
        if (Dump.Y) Dump.println("CompDlgTiles.getReachEarthWidth pairctr="+pairctr);//~9302I~
        if (pairctr==0)                                            //~9302I~
        	return 0;                                              //~9302I~
        int xx=-SPACING_X;                                         //~9302I~
        for (int ii=0;ii<pairctr;ii++)                             //~9302I~
        {                                                          //~9302I~
    		TileData[] tds=pairs[ii];                              //~9302I~
            if (tds==null)                                         //~9302I~
                break;                                             //~9302I~
            xx-=SPACING_X+(PIECE_SPACING+PpieceW)*tds.length;       //~9302I~
        }                                                          //~9302I~
        xx+=SPACING_X;                                             //~9302I~
        if (Dump.Y) Dump.println("CompDlgTiles.getReachEarthWidth rc="+(-xx));//~9302I~
        return -xx;                                                //~9302I~
    }                                                              //~9302I~
    //*******************************************************************//~v@21I~
	public int drawPair(int Pxx,int Pyy,TileData[] Ptds)           //~v@21I~
    {                                                              //~v@21I~
    	int xxk=0,yyk=0,yy;                                           //~v@21I~
         Bitmap bm,bmKanAdd=null;                                                  //~v@21I~
         TileData tdKanAdd=null;                                   //~v@21I~
        int playerForAddKan=0;                                     //~v@21I~
                                                                   //~v@21I~
        if (Dump.Y) Dump.println("CompDlgTiles.drawPair xx="+Pxx+",yy="+Pyy+",tds:"+TileData.toString(Ptds));//~v@21I~
        int xx=Pxx;                                                    //~v@21I~
        stroke_width=AG.aRiver.stroke_width_river;                //~0401I~
        for (int ii=Ptds.length-1;ii>=0;ii--)                      //~v@21R~
        {                                                          //~v@21I~
        	TileData td=Ptds[ii];                                  //~v@21R~
            if ((td.flag & TDF_KAN_ADDED_TILE)!=0)                 //~v@21M~
            {                                                      //~v@21M~
            	tdKanAdd=td;                                       //~v@21I~
                continue;                                          //~v@21M~
            }                                                      //~v@21M~
            bm=getBitmapPair(td,player);                           //~v@21M~
            xx-=bm.getWidth();                                     //~v@21I~
	        if ((td.flag & TDF_DISCARDED)!=0)                         //~v@21I~
            {                                                      //~v@21I~
            	yy=Pyy+bm.getWidth()-bm.getHeight();               //~v@21R~
                xxk=xx; yyk=yy;                                    //~v@21I~
                playerForAddKan=td.player;                         //~v@21I~
            }                                                      //~v@21I~
            else                                                   //~v@21I~
            	yy=Pyy;                                            //~v@21I~
        	if (Dump.Y) Dump.println("CompDlgTiles.drawPair xx="+xx+",yy="+yy);//~0326I~
		  if (!swChkEarthPosition)                                 //~0326I~
          {                                                        //~0326I~
            if ((td.flag & (TDF_RON | TDF_KAN_RIVER))==(TDF_RON | TDF_KAN_RIVER))//~v@21I~
				drawPiece(new Rect(xx,yy,xx+bm.getWidth(),yy+bm.getHeight()),new Point(xx,yy),bm,true);//~v@21I~
            else                                                   //~v@21I~
	            Graphics.drawBitmap(canvas,bm,xx,yy);              //~v@21R~
          }                                                        //~0326I~
            xx-=PIECE_SPACING;                                     //~v@21R~
        }                                                          //~v@21I~
	  if (!swChkEarthPosition)                                     //~0326I~
      {                                                            //~0326I~
        if (tdKanAdd!=null)                                        //~v@21R~
        {                                                          //~v@21I~
            int pl=tdKanAdd.player;                                //~v@21R~
            tdKanAdd.player=playerForAddKan;	//temporary set same as river tile//~v@21I~
            bm=getBitmapPair(tdKanAdd,pl);                         //~v@210R~//~v@21R~
            tdKanAdd.player=pl;                                    //~v@21I~
        	drawEarthAddKan(xxk,yyk,bm);                           //~v@21R~
        }                                                          //~v@21I~
      }                                                            //~0326I~
        return xx;                                                 //~v@21I~
    }                                                              //~v@21I~
	//*********************************************************    //~v@21I~
    private Bitmap getBitmapPair(TileData Ptd,int Pplayer)         //~v@21I~
    {                                                              //~v@21I~
    	Bitmap bm;                                                 //~v@21I~
	    int player;                                                //~v@21I~
    //************************                                     //~v@21I~
        if (Dump.Y) Dump.println("CompDlgTiles.getBitmapPair Pplayer="+Pplayer+",tile="+Ptd.toString());//~v@21I~//~9301R~
	    if ((Ptd.flag & TDF_KAN_FACEDOWN)!=0)                      //~v@21I~
        {                                                          //~v@21I~
//      	player=Ptd.player;                                     //~v@21I~//~9302R~
        	player=PLAYER_YOU;                                     //~9302I~
//          bm=pieces.getBitmapHandPairStock(Pplayer);             //~v@21I~//~9302R~
            bm=pieces.getBitmapHandPairStock(player);              //~9302I~
        }                                                          //~v@21I~
        else                                                       //~v@21I~
        {                                                          //~v@21I~
            if ((Ptd.flag & (TDF_TAKEN_RIVER|TDF_KAN_ADDED_TILE))!=0)//~v@21I~
            {                                                      //~v@21I~
                player=Ptd.player;                                 //~v@21I~
//              if (Players.playerRelative(player,Pplayer/*base*/)==PLAYER_FACING) //taken facing discarded//~v@21I~//~9301R~
//                  player=Players.prevPlayer(player);             //~v@21I~//~9301R~
                player=Players.playerRelative(player,Pplayer/*base*/);//~9301R~
                if (player==PLAYER_FACING)                         //~9301R~
                    player=Players.prevPlayer(player);                //~9301I~
	        	if (Dump.Y) Dump.println("CompDlgTiles.getBitmapPair player="+player);//~v@21I~
            }                                                      //~v@21I~
            else                                                   //~v@21I~
//              player=Pplayer;                                    //~v@21I~//~9301R~
                player=PLAYER_YOU;                                 //~9301I~
            bm=pieces.getBitmapHandPair(Ptd,player);               //~v@21I~
        }                                                          //~v@21I~
        if (Dump.Y) Dump.println("CompDlgTiles.getBitmapPair result player="+player+",bm WW="+bm.getWidth()+",HH="+bm.getHeight());//~v@21R~
        return bm;                                                 //~v@21I~
    }                                                              //~v@21I~
    //*******************************************************************//~v@21R~
    //*update pair by add kan                                      //~v@21R~
    //*******************************************************************//~v@21R~
    public void drawEarthAddKan(int Pxx,int Pyy,Bitmap PbmKan)     //~v@21R~
    {                                                              //~v@21R~
        int xx,yy,diff;                                            //~v@21R~
    //*********************                                        //~v@21R~
        if (Dump.Y) Dump.println("CompDlgTile.drawEarthAddKan");//~v@21R~
        Bitmap bm=PbmKan;                                          //~v@21R~
        yy=Pyy-(bm.getWidth()-bm.getHeight());                     //~v@21I~
        Bitmap bmscaled=Pieces.scaleImage(bm,RATE_ADD_KAN);        //~v@21R~
        xx=Pxx+(bm.getWidth()-bmscaled.getWidth())/2;              //~v@21R~
        Graphics.drawBitmap(canvas,bmscaled,xx,yy);                //~v@21I~
        UView.recycle(bmscaled);                                   //~v@21R~
    }                                                              //~v@21R~
//    //*************************************************************//~v@21I~//~9219R~
//    private Bitmap getBitmapDora(TileData Ptd,boolean PswFaceDown) //~v@21R~//~9219R~
//    {                                                              //~v@21I~//~9219R~
//        int ww,hh;                                                 //~v@21I~//~9219R~
//        Bitmap bm;                                                 //~v@21I~//~9219R~
//    //************************                                     //~v@21I~//~9219R~
//        int num=Ptd.number;                                        //~v@21I~//~9219R~
//        if (Ptd.type!=TT_JI)                                       //~v@21I~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            if ((Ptd.flag & TDF_RED5)!=0)                          //~v@21R~//~9219R~
//                num=0;                                             //~v@21R~//~9219R~
//            else                                                   //~v@21R~//~9219R~
//                num++;                                             //~v@21R~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        if (PswFaceDown)                                           //~v@21I~//~9219R~
//            bm=pieces.getBitmapHandPairStock(PLAYER_YOU);          //~v@21I~//~9219R~
//        else                                                       //~v@21I~//~9219R~
//            bm=bmsssRiver[PLAYER_YOU][Ptd.type][num];              //~v@21R~//~9219R~
//        Bitmap bmout=Pieces.scaleImage(bm,RATE_DORA);             //~v@21R~//~9219R~
//        return bmout;                                              //~v@21I~//~9219R~
//    }                                                              //~v@21I~//~9219R~
//    //*************************************************************//~v@21I~//~9219R~
//    private int drawDora(int Pxx,int Pyy,TileData Ptd,boolean PswFaceDown) //~v@21R~//~9219R~
//    {                                                              //~v@21I~//~9219R~
//        if (Dump.Y) Dump.println("CompDlgTiles.drawDora");         //~v@21I~//~9219R~
//        Bitmap bm=getBitmapDora(Ptd,PswFaceDown);                   //~v@21I~//~9219R~
//        Graphics.drawBitmap(canvas,bm,Pxx,Pyy);        //~v@21R~ //~9219R~
//        int xx=Pxx+bm.getWidth();                                     //~v@21I~//~9219R~
//        UView.recycle(bm);                                         //~v@21I~//~9219R~
//        return xx;                                                 //~v@21I~//~9219R~
//    }                                                              //~v@21I~//~9219R~
//    //*********************************************************    //~v@21I~//~9219R~
//    //*show Dora                                                   //~v@21I~//~9219R~
//    //*********************************************************    //~v@21I~//~9219R~
//    public int drawDora()                                          //~v@21I~//~9219R~
//    {                                                              //~v@21I~//~9219R~
//        int xx,yy;                                                 //~v@21I~//~9219R~
//        if (swDrawDoraRight)                                       //~v@21I~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            xx=WW-SPACING_X-(int)((MAXCTR_KAN+1)*(earthPieceW*RATE_DORA)*2)-SPACING_X*4;//~v@21R~//~9219R~
//            yy=HANDS_POSY+(pieceH-(int)(earthPieceH*RATE_DORA))/2; //~v@21R~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        else                                                       //~v@21I~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            xx=HANDS_POSX;                                         //~v@21R~//~9219R~
//            yy=HH-SPACING_Y-(int)(earthPieceH*RATE_DORA);          //~v@21R~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        if (Dump.Y) Dump.println("CompDlgTiles.drawDora ctrKanDraw="+ctrKanDrawn);//~v@21I~//~9219R~
//        for (int ii=0;ii<=MAXCTR_KAN;ii++)                         //~v@21R~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            TileData td=shuffled[DORA_TDPOS-ii*STOCK_LAYER];       //~v@21I~//~9219R~
//            xx=drawDora(xx,yy,td,ii>ctrKanDrawn);                  //~v@21R~//~9219R~
//            xx+=PIECE_SPACING;                                     //~v@21I~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        return xx;                                                 //~v@21I~//~9219R~
//    }                                                              //~v@21I~//~9219R~
//    //*********************************************************    //~v@21I~//~9219R~
//    //*show hidden Dora(uradora)                                   //~v@21I~//~9219R~
//    //*********************************************************    //~v@21I~//~9219R~
//    public void drawDoraHidden(int PposX)                          //~v@21I~//~9219R~
//    {                                                              //~v@21I~//~9219R~
//        int xx,yy;                                                 //~v@21I~//~9219R~
//        if (Dump.Y) Dump.println("Stock.drawDoraComplete ctrKanDraw="+ctrKanDrawn);//~v@21I~//~9219R~
//        if (swDrawDoraRight)                                       //~v@21I~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            xx=PposX+SPACING_X*4;                                  //~v@21I~//~9219R~
//            yy=HANDS_POSY+(pieceH-(int)(earthPieceH*RATE_DORA))/2; //~v@21R~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        else                                                       //~v@21I~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            xx=PposX+SPACING_X*4;                                  //~v@21R~//~9219R~
//            yy=HH-SPACING_Y-(int)(earthPieceH*RATE_DORA);          //~v@21R~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        for (int ii=0;ii<=ctrKanDrawn;ii++)                        //~v@21R~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            if (ii==0 && !RuleSetting.isShowHiddenDora())          //~v@21I~//~9219R~
//                continue;                                          //~v@21I~//~9219R~
//            if (ii!=0 && !RuleSetting.isShowHiddenKanDora())       //~v@21I~//~9219R~
//                continue;                                          //~v@21I~//~9219R~
//            TileData td=shuffled[DORA_TDPOS-ii*STOCK_LAYER-1];     //~v@21I~//~9219R~
//            xx=drawDora(xx,yy,td,false);                                 //~v@21I~//~9219R~
//            xx+=PIECE_SPACING;                                     //~v@21I~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//    }                                                              //~v@21I~//~9219R~
}//class CompDlgTiles                                              //~v@21R~
