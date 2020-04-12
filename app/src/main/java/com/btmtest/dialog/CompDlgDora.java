//*CID://+DATER~: update#= 429;                                    //~v@21R~//~9218R~
//**********************************************************************
//v@21  imageview                                                  //~v@21I~
//utility around screen
//**********************************************************************
package com.btmtest.dialog;                                        //~9219R~

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.btmtest.R;
import com.btmtest.TestOption;
import com.btmtest.game.Complete;
import com.btmtest.game.Players;
import com.btmtest.dialog.RuleSetting;                             //~9412R~
import com.btmtest.game.TileData;
import com.btmtest.game.gv.GCanvas;
import com.btmtest.game.gv.Graphics;
import com.btmtest.game.gv.MJTable;
import com.btmtest.game.gv.Pieces;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import static com.btmtest.StaticVars.AG;                           //~v@21I~
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.gv.MJTable.*;
import static com.btmtest.game.gv.Stock.*;
import static com.btmtest.game.Tiles.*;


public class CompDlgDora extends AppCompatImageView               //~v@21R~//~9219R~
{
	private  static final int MARGIN_X=6;                          //~9219R~
	private  static final int MARGIN_Y=6;                          //~9219I~
	private  static final int SPACING_X=4;//~v@21I~//~9219R~
	private static final double RATE_DORA=0.8;                     //~v@21I~
                                                                   //~v@21I~
    private int WW, HH;                                    //~v@21R~
//    private GCanvas gcanvas;                                       //~v@21R~
//    private MJTable table;                                         //~v@21I~
    private Pieces pieces;                                         //~9219R~
//    private Players PLS;//~v@21I~
    private Canvas canvas;                                         //~v@21I~
//    private int compEswn,compType,compEswnLooser;                  //~v@21I~
//    private CompleteDlg dlg;                                       //~v@21I~
    private int bgColor;                                            //~v@21I~
//  private int stockPieceW,stockPieceH;             //~v@21R~     //~9219R~
//    private int earthPieceH,earthPieceW;                           //~v@21I~
//    private boolean swTake;                                        //~v@21R~
    private int ctrKanDrawn;                                        //~v@21I~
    private TileData[] shuffled;                                   //~v@21I~
    private Bitmap[][][] bmsssRiver;  //[standing/lying][man/pin/sou/ji][number]//~v@21I~
//    private boolean swDrawDoraRight;                               //~v@21I~
//    private TileData compTD,compTDKanTaken;                        //~v@21R~
    private Complete.Status compStat;                              //~v@21I~
    private static boolean swByXml;                                //~9218R~
    //**************************************************************//~v@@@R~//~v@21R~
    //*if calss defined in xml                                     //~v@@@I~//~v@21R~
    //**************************************************************//~v@@@I~//~v@21R~
    public CompDlgDora(Context Pcontext)                          //~9218I~//~9219R~
    {                                                              //~9218I~
        super(Pcontext);                                           //~9218R~
        if (Dump.Y) Dump.println("CompDlgDora Constructor without attr");//~9218I~//~9219R~
        init(Pcontext);                                            //~9218I~
    }                                                              //~9218I~
    public CompDlgDora(Context Pcontext,AttributeSet Pattrs)          //~v@@@I~//~v@21R~//~9219R~
    {                                                              //~v@@@I~//~v@21R~
        super(Pcontext,Pattrs);                                    //~v@@@I~//~v@21R~
        if (Dump.Y) Dump.println("CompDlgDora Constructor with attr");//~v@@@R~//~v@21R~//~9219R~
    	swByXml=true;                                              //~9218I~
    	if ((TestOption.option & TestOption.TO_COMPREQDLG_LAYOUT)!=0) //TODO TEST//~9219I~//~9223R~
            return;                                                //~9219I~
        init(Pcontext);                                            //~v@@@R~//~v@21R~
    }                                                              //~v@@@I~//~v@21R~
    //******************************************                   //~9219I~
    private static int getLayoutH()                                //~9219I~
    {                                                              //~9219I~
        Bitmap bm=AG.aPieces.getBitmapHandPairStock(PLAYER_YOU);   //~9219I~
        int hh=(int)(bm.getHeight()*RATE_DORA);                    //~9219I~
        hh+=MARGIN_Y*2;                                            //~9219I~
        if (Dump.Y) Dump.println("CompDlgDora.getLayoutH hh="+hh); //~9219I~
        return hh;                                                 //~9219I~
    }                                                              //~9219I~
    //******************************************                   //~v@21I~
    public static CompDlgDora setImageLayout(View PView)                        //~v@21I~//~9218R~//~9219R~
    {                                                              //~v@21I~
//        GCanvas gcanvas = AG.aGCanvas;                             //~v@21I~//~9219R~
//        MJTable table = gcanvas.table;                             //~v@21I~//~9219R~
//        int pieceH=table.handPieceH;                               //~v@21I~//~9219R~
//        Point p=table.getPairPieceSize();                          //~v@21I~//~9219R~
//        int pairPieceH=p.y;                                        //~v@21I~//~9219R~
        LinearLayout llImage=(LinearLayout)UView.findViewById(PView,R.id.LLDora);//~v@21I~//~9219R~
        ViewGroup.LayoutParams llp=llImage.getLayoutParams();      //~v@21I~
        if (Dump.Y) Dump.println("CompDlgDora.setImageLayout llp getLayoutParams HH="+llp.height+",WW="+llp.width);//~9812I~
        int hh=getLayoutH();                                       //~9219I~
        llp.height=hh;                                             //~9219R~
//      llp.width=ViewGroup.LayoutParams.MATCH_PARENT;             //~9219R~//~9809R~//~9810R~
        llp.width=getDoraMaxWidth();               //~9809R~       //~9810R~//~9812R~
    	llImage.setLayoutParams(llp);     //TODO test              //~v@21I~//~9809R~
        if (Dump.Y) Dump.println("CompDlgDora.setImageLayout llp HH="+hh+",layoutparm w="+llp.width+",h="+llp.height);//~9809R~
//        if (!swByXml)                                              //~9218I~//~9219R~
//        {                                                          //~9218I~//~9219R~
//            CompDlgDora v=new CompDlgDora(AG.context);           //~9218R~//~9219R~
//            llImage.addView(v,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));//~9218I~//~9219R~
//            return v;                                              //~9218I~//~9219R~
//        }                                                          //~9218I~//~9219R~
//      return null;                                               //~9218I~//~9810R~
        CompDlgDora v=new CompDlgDora(AG.context);                 //~9810I~
//      llImage.addView(v,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));//~9810I~//~9812R~
        ViewGroup.LayoutParams llv=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);//~9812I~
        llImage.addView(v,llv);                                    //~9812I~
        return v;                                                  //~9810I~
    }                                                              //~v@21I~
    //*************************
    private void init(Context Pcontext)                            //~v@@@R~
    {
//		dlg=AG.aCompleteDlg;                                       //~v@21R~
		if (Dump.Y) Dump.println("CompDlgDora.init context="+Pcontext.toString());//~v@@@I~//~v@21R~//~9219R~
//        gcanvas = AG.aGCanvas;                                     //~v@21I~//~9219R~
//        table = gcanvas.table;                                     //~v@21I~//~9219R~
        pieces=AG.aPieces;                                       //~v@21I~//~9219R~
        bgColor=COLOR_BG_TABLE;                                    //~v@21I~
//        pieceW=table.handPieceW;                                   //~v@21I~//~9219R~
//        pieceH=table.handPieceH;                                   //~v@21I~//~9219R~
//        stockPieceH=AG.aStock.pieceH;                              //~v@21I~
//        stockPieceW=AG.aStock.pieceW;                              //~v@21I~
//           bm=pieces.getBitmapHandPairStock(PLAYER_YOU);          //~9219I~
//        earthPieceH=AG.aEarth.piecePairH;                              //~v@21I~//~9219R~
//        earthPieceW=AG.aEarth.piecePairW;                              //~v@21I~//~9219R~
//        PLS=AG.aPlayers;                                                           //~v@21I~//~9219R~
        shuffled=AG.aTiles.getShuffled();                          //~v@21I~
		if (Dump.Y) Dump.println("CompDlgDora.init shuffled length="+shuffled.length);//~9223I~
//      bmsssRiver=pieces.bitmapAllPiecesRiver;                    //~v@21I~//~9219R~//+0216R~
        bmsssRiver=AG.bitmapAllPiecesRiver;                        //+0216I~
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
    //***********************************************************  //~v@21I~
    @Override                                                    //~v@@@R~//~v@21R~
    public void onDraw(Canvas Pcanvas/*android.graphics.Canvas*/)                           //~v@@@R~//~v@21R~
    {                                                            //~v@@@R~//~v@21R~
        View parent=(View)getParent();                                    //~v@21I~
        canvas=Pcanvas;                                            //~v@21I~//~9219M~
        if (Dump.Y) Dump.println("CompDlgDora.onDraw parent id="+Integer.toHexString(parent.getId())+",parent W="+parent.getWidth()+",H="+parent.getHeight());//~v@21I~//~9219R~//~9809R~
        if (Dump.Y) Dump.println("CompDlgDora.onDraw");             //~v@@@R~//~v@21R~//~9219R~
        try                                                        //~9223I~
        {                                                          //~9223I~
    //      Bitmap bm=AG.aPieces.getBitmapHandPairStock(PLAYER_YOU);   //~9219R~//~9223R~
    //      int pieceH=bm.getHeight();                                 //~9219R~//~9223R~
            WW=getWidth();                                             //~v@21R~//~9223R~
            HH=getHeight();                                            //~9219I~//~9223R~
    //      HH=pieceH+MARGIN_Y*2;                                      //~9219R~//~9223R~
            if (Dump.Y) Dump.println("CompDlgDora.onDraw WW="+WW+",HH="+HH);//~v@21R~//~9219R~//~9223R~
            Rect r=new Rect(0,0,WW,HH);                                //~9219I~//~9223R~
            Graphics.drawRect(canvas,r,bgColor);               //~9219I~//~9223R~
    //        getCompleteStatus();                                       //~v@21I~//~9219R~//~9223R~
    //        int endPosHand=drawHands();                                //~v@21R~//~9219R~//~9223R~
    //        swDrawDoraRight=endPosHand<WW/2;                           //~v@21I~//~9219R~//~9223R~
    //        drawEarth();                                               //~v@21I~//~9219R~//~9223R~
            ctrKanDrawn=AG.aStock.ctrKanDrawn;                         //~v@21I~//~9223R~
            drawDoraHidden(drawDora());                                //~v@21I~//~9223R~
        }                                                          //~9223I~
        catch(Exception e)                                         //~9223I~
        {                                                          //~9223I~
            Dump.println(e,"CompDlgDora.onDraw");                  //~9223I~
        }                                                          //~9223I~
    }                                                            //~v@@@R~//~v@21R~
//    //***********************************************************  //~v@21I~//~9219R~
//    private void getCompleteStatus()                               //~v@21I~//~9219R~
//    {                                                              //~v@21I~//~9219R~
//        compStat=Status.getCompleteStatus(); //eswn+type           //~v@21I~//~9219R~
//        compEswn=compStat.completeEswn;                            //~v@21R~//~9219R~
//        compType=compStat.completeType;                            //~v@21R~//~9219R~
//        compEswnLooser=compStat.completeEswnLooser;                //~v@21R~//~9219R~
//        compTD=compStat.completeTD;                                    //~v@21R~//~9219R~
//        compTDKanTaken=compStat.completeKanTakenTD;                    //~v@21R~//~9219R~
//        swTake=(compType & (COMPLETE_TAKEN|COMPLETE_KAN_TAKEN))!=0;//~v@21I~//~9219R~
//        player=Accounts.eswnToPlayer(compEswn);                    //~v@21R~//~9219R~
//        if (Dump.Y) Dump.println("CompDlgDora.getCompleteStatus player="+player+",compEswn="+compEswn+",compType="+compType+",loosereswn="+compEswnLooser+",swTake="+swTake);//~v@21R~//~9219R~
//    }                                                              //~v@21I~//~9219R~
//    //***********************************************************  //~v@21I~//~9219R~
//    private int drawHands()                                        //~v@21R~//~9219R~
//    {                                                              //~v@21I~//~9219R~
//        Bitmap bm;                                                 //~v@21I~//~9219R~
//        TileData td;                                               //~v@21I~//~9219R~
//    //********************                                         //~v@21I~//~9219R~
//        if (Dump.Y) Dump.println("CompDlgDora.drawHands");        //~v@21I~//~9219R~
//        TileData[] tds=AG.aPlayers.getHands(player);               //~v@21I~//~9219R~
//        int ctr=tds.length;                                        //~v@21I~//~9219R~
//        Rect rectHands=new Rect(0,0,WW,HH);                        //~v@21R~//~9219R~
//        if (Dump.Y) Dump.println("CompDlgDora.drawHands ctr="+ctr);//~v@21R~//~9219R~
//        Graphics.drawRect(canvas,rectHands,bgColor);               //~v@21I~//~9219R~
//        int xx=HANDS_POSX;                                         //~v@21R~//~9219R~
//        int yy=HANDS_POSY;                                         //~v@21R~//~9219R~
//        boolean swTaken=Tiles.isTakenStatus(ctr);                  //~v@21I~//~9219R~
//        for (int ii=0;ii<ctr;ii++)                                 //~v@21I~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            td=tds[ii];                                            //~v@21R~//~9219R~
//            if (Dump.Y) Dump.println("CompDlgDora.drawHand swTaken="+swTaken+",ii="+ii+",td="+td.toString());//~v@21R~//~9219R~
//            bm=pieces.getBitmapHand(td);                           //~v@21R~//~9219R~
//            handsHH=bm.getHeight();                                //~v@21I~//~9219R~
//            if (swTaken && ii==ctr-1)                              //~v@21I~//~9219R~
//            {                                                      //~v@21I~//~9219R~
//                drawPiece(new Rect(xx,yy,xx+pieceW,yy+pieceH),new Point(xx,yy),bm,false);//~v@21R~//~9219R~
//            }                                                      //~v@21I~//~9219R~
//            else                                                   //~v@21I~//~9219R~
//            {                                                      //~v@21I~//~9219R~
//                Graphics.drawBitmap(canvas,bm,xx,yy);              //~v@21I~//~9219R~
//            }                                                      //~v@21I~//~9219R~
//            if (swTaken && ii==ctr-2)                              //~v@21I~//~9219R~
//                xx+=pieceW+PIECE_SPACING_TAKEN;                    //~v@21I~//~9219R~
//            else                                                   //~v@21I~//~9219R~
//                xx+=pieceW+PIECE_SPACING;                          //~v@21I~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        td=compTD;                                                 //~v@21R~//~9219R~
//        if (!swTaken && td!=null)                                  //~v@21R~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            if (Dump.Y) Dump.println("CompDlgDora.drawHands ,compTD="+TileData.toString(td));//~v@21R~//~9219R~
//            xx+=PIECE_SPACING_TAKEN;                               //~v@21I~//~9219R~
//            bm=pieces.getBitmapHand(td);                           //~v@21I~//~9219R~
//            drawPiece(new Rect(xx,yy,xx+pieceW,yy+pieceH),new Point(xx,yy),bm,true);//~v@21I~//~9219R~
//            xx+=pieceW;                                            //~v@21I~//~9219R~
//        }                                                          //~v@21I~//~9219R~
////        td=compTDKanTaken;                                       //~v@21R~//~9219R~
////        if (td!=null)                                            //~v@21R~//~9219R~
////        {                                                        //~v@21R~//~9219R~
////            td=compTD;                                           //~v@21R~//~9219R~
////            if (Dump.Y) Dump.println("CompDlgDora.drawHands ,KanTakenTD="+TileData.toString(td));//~v@21R~//~9219R~
////            xx+=PIECE_SPACING_TAKEN*3;                           //~v@21R~//~9219R~
////            bm=pieces.getBitmapHand(td);                         //~v@21R~//~9219R~
////            drawPiece(new Rect(xx,yy,xx+pieceW,yy+pieceH),new Point(xx,yy),bm,true);//~v@21R~//~9219R~
////            xx+=pieceW;                                          //~v@21R~//~9219R~
////        }                                                        //~v@21R~//~9219R~
//        return xx;                                                 //~v@21I~//~9219R~
//    }                                                              //~v@21I~//~9219R~
//    //*******************************************************************//~v@21I~//~9219R~
//    private void drawPiece(Rect Prect,Point Ppoint,Bitmap Pbitmap,boolean PswComplete)//~v@21R~//~9219R~
//    {                                                              //~v@21I~//~9219R~
//        if (Dump.Y) Dump.println("CompDlgDora.drawPiece swComplete="+PswComplete);//~v@21R~//~9219R~
//        if (PswComplete)                                           //~v@21R~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            Graphics.drawRectFrameBitmap(canvas,Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y,COMPLETE_STROKE_WIDTH,COMPLETE_COLOR);//~v@21I~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        else                                                       //~v@21I~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            Graphics.drawRectFrameBitmap(canvas,Prect,bgColor,Pbitmap,Ppoint.x,Ppoint.y,HandsTouch.WIDTH_SELECTED,HandsTouch.COLOR_SELECTED);//~v@21I~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//    }                                                              //~v@21I~//~9219R~
//    //*******************************************************************//~v@21I~//~9219R~
//    public void drawEarth()                                        //~v@21I~//~9219R~
//    {                                                              //~v@21I~//~9219R~
//    //*********************                                        //~v@21I~//~9219R~
//        TileData[][] pairs=PLS.getEarth(player);                       //~v@21I~//~9219R~
//        int pairctr=pairs.length;                                  //~v@21I~//~9219R~
//        if (Dump.Y) Dump.println("CompDlgDora.drawEarth pairctr="+pairctr);//~v@21R~//~9219R~
//        int xx=WW-SPACING_X;                                       //~v@21R~//~9219R~
//        int yy=SPACING_Y+pieceH+SPACING_Y;                             //~v@21I~//~9219R~
//        for (int ii=0;ii<pairctr;ii++)                                 //~v@21I~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            TileData[] tds=pairs[ii];                              //~v@21I~//~9219R~
//            if (tds==null)                                         //~v@21I~//~9219R~
//                break;                                             //~v@21I~//~9219R~
//            TileData[] tdsSorted=AG.aEarth.sortPairTile(tds,PLAYER_YOU);//~v@21I~//~9219R~
//            xx=drawPair(xx,yy,tdsSorted);                          //~v@21R~//~9219R~
//            xx-=SPACING_X;                                       //~v@21R~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//    }                                                              //~v@21I~//~9219R~
//    //*******************************************************************//~v@21I~//~9219R~
//    public int drawPair(int Pxx,int Pyy,TileData[] Ptds)           //~v@21I~//~9219R~
//    {                                                              //~v@21I~//~9219R~
//        int xxk=0,yyk=0,yy;                                           //~v@21I~//~9219R~
//         Bitmap bm,bmKanAdd=null;                                                  //~v@21I~//~9219R~
//         TileData tdKanAdd=null;                                   //~v@21I~//~9219R~
//        int playerForAddKan=0;                                     //~v@21I~//~9219R~
//                                                                   //~v@21I~//~9219R~
//        if (Dump.Y) Dump.println("CompDlgDora.drawPair xx="+Pxx+",yy="+Pyy+",tds:"+TileData.toString(Ptds));//~v@21I~//~9219R~
//        int xx=Pxx;                                                    //~v@21I~//~9219R~
//        for (int ii=Ptds.length-1;ii>=0;ii--)                      //~v@21R~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            TileData td=Ptds[ii];                                  //~v@21R~//~9219R~
//            if ((td.flag & TDF_KAN_ADDED_TILE)!=0)                 //~v@21M~//~9219R~
//            {                                                      //~v@21M~//~9219R~
//                tdKanAdd=td;                                       //~v@21I~//~9219R~
//                continue;                                          //~v@21M~//~9219R~
//            }                                                      //~v@21M~//~9219R~
//            bm=getBitmapPair(td,player);                           //~v@21M~//~9219R~
//            xx-=bm.getWidth();                                     //~v@21I~//~9219R~
//            if ((td.flag & TDF_DISCARDED)!=0)                         //~v@21I~//~9219R~
//            {                                                      //~v@21I~//~9219R~
//                yy=Pyy+bm.getWidth()-bm.getHeight();               //~v@21R~//~9219R~
//                xxk=xx; yyk=yy;                                    //~v@21I~//~9219R~
//                playerForAddKan=td.player;                         //~v@21I~//~9219R~
//            }                                                      //~v@21I~//~9219R~
//            else                                                   //~v@21I~//~9219R~
//                yy=Pyy;                                            //~v@21I~//~9219R~
//            if ((td.flag & (TDF_RON | TDF_KAN_RIVER))==(TDF_RON | TDF_KAN_RIVER))//~v@21I~//~9219R~
//                drawPiece(new Rect(xx,yy,xx+bm.getWidth(),yy+bm.getHeight()),new Point(xx,yy),bm,true);//~v@21I~//~9219R~
//            else                                                   //~v@21I~//~9219R~
//                Graphics.drawBitmap(canvas,bm,xx,yy);              //~v@21R~//~9219R~
//            xx-=PIECE_SPACING;                                     //~v@21R~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        if (tdKanAdd!=null)                                        //~v@21R~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            int pl=tdKanAdd.player;                                //~v@21R~//~9219R~
//            tdKanAdd.player=playerForAddKan;    //temporary set same as river tile//~v@21I~//~9219R~
//            bm=getBitmapPair(tdKanAdd,pl);                         //~v@210R~//~v@21R~//~9219R~
//            tdKanAdd.player=pl;                                    //~v@21I~//~9219R~
//            drawEarthAddKan(xxk,yyk,bm);                           //~v@21R~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        return xx;                                                 //~v@21I~//~9219R~
//    }                                                              //~v@21I~//~9219R~
//    //*********************************************************    //~v@21I~//~9219R~
//    private Bitmap getBitmapPair(TileData Ptd,int Pplayer)         //~v@21I~//~9219R~
//    {                                                              //~v@21I~//~9219R~
//        Bitmap bm;                                                 //~v@21I~//~9219R~
//        int player;                                                //~v@21I~//~9219R~
//    //************************                                     //~v@21I~//~9219R~
//        if (Dump.Y) Dump.println("CompDlgDora.getBitmapPair player="+Pplayer+",tile="+Ptd.toString());//~v@21I~//~9219R~
//        if ((Ptd.flag & TDF_KAN_FACEDOWN)!=0)                      //~v@21I~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            player=Ptd.player;                                     //~v@21I~//~9219R~
//            bm=pieces.getBitmapHandPairStock(Pplayer);             //~v@21I~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        else                                                       //~v@21I~//~9219R~
//        {                                                          //~v@21I~//~9219R~
//            if ((Ptd.flag & (TDF_TAKEN_RIVER|TDF_KAN_ADDED_TILE))!=0)//~v@21I~//~9219R~
//            {                                                      //~v@21I~//~9219R~
//                player=Ptd.player;                                 //~v@21I~//~9219R~
//                if (Players.playerRelative(player,Pplayer)==PLAYER_FACING) //taken facing discarded//~v@21I~//~9219R~
//                    player=Players.prevPlayer(player);             //~v@21I~//~9219R~
//                if (Dump.Y) Dump.println("CompDlgDora.getBitmapPair player="+player);//~v@21I~//~9219R~
//            }                                                      //~v@21I~//~9219R~
//            else                                                   //~v@21I~//~9219R~
//                player=Pplayer;                                    //~v@21I~//~9219R~
//            bm=pieces.getBitmapHandPair(Ptd,player);               //~v@21I~//~9219R~
//        }                                                          //~v@21I~//~9219R~
//        if (Dump.Y) Dump.println("CompDlgDora.getBitmapPair result player="+player+",bm WW="+bm.getWidth()+",HH="+bm.getHeight());//~v@21R~//~9219R~
//        return bm;                                                 //~v@21I~//~9219R~
//    }                                                              //~v@21I~//~9219R~
//    //*******************************************************************//~v@21R~//~9219R~
//    //*update pair by add kan                                      //~v@21R~//~9219R~
//    //*******************************************************************//~v@21R~//~9219R~
//    public void drawEarthAddKan(int Pxx,int Pyy,Bitmap PbmKan)     //~v@21R~//~9219R~
//    {                                                              //~v@21R~//~9219R~
//        int xx,yy,diff;                                            //~v@21R~//~9219R~
//    //*********************                                        //~v@21R~//~9219R~
//        if (Dump.Y) Dump.println("CompDlgTile.drawEarthAddKan");//~v@21R~//~9219R~
//        Bitmap bm=PbmKan;                                          //~v@21R~//~9219R~
//        yy=Pyy-(bm.getWidth()-bm.getHeight());                     //~v@21I~//~9219R~
//        Bitmap bmscaled=Pieces.scaleImage(bm,RATE_ADD_KAN);        //~v@21R~//~9219R~
//        xx=Pxx+(bm.getWidth()-bmscaled.getWidth())/2;              //~v@21R~//~9219R~
//        Graphics.drawBitmap(canvas,bmscaled,xx,yy);                //~v@21I~//~9219R~
//        UView.recycle(bmscaled);                                   //~v@21R~//~9219R~
//    }                                                              //~v@21R~//~9219R~
    //*************************************************************//~v@21I~//~9219R~
    private Bitmap getBitmapDora(TileData Ptd,boolean PswFaceDown) //~v@21R~//~9219R~
    {                                                              //~v@21I~//~9219R~
        int ww,hh;                                                 //~v@21I~//~9219R~
        Bitmap bm;                                                 //~v@21I~//~9219R~
    //************************                                     //~v@21I~//~9219R~
        int num=Ptd.number;                                        //~v@21I~//~9219R~
        if (Ptd.type!=TT_JI)                                       //~v@21I~//~9219R~
        {                                                          //~v@21I~//~9219R~
            if ((Ptd.flag & TDF_RED5)!=0)                          //~v@21R~//~9219R~
                num=0;                                             //~v@21R~//~9219R~
            else                                                   //~v@21R~//~9219R~
                num++;                                             //~v@21R~//~9219R~
        }                                                          //~v@21I~//~9219R~
        if (PswFaceDown)                                           //~v@21I~//~9219R~
            bm=pieces.getBitmapHandPairStock(PLAYER_YOU);          //~v@21I~//~9219R~
        else                                                       //~v@21I~//~9219R~
            bm=bmsssRiver[PLAYER_YOU][Ptd.type][num];              //~v@21R~//~9219R~
        Bitmap bmout=Pieces.scaleImage(bm,RATE_DORA);             //~v@21R~//~9219R~
        return bmout;                                              //~v@21I~//~9219R~
    }                                                              //~v@21I~//~9219R~
    //*************************************************************//~v@21I~
	private int drawDora(int Pxx,int Pyy,TileData Ptd,boolean PswFaceDown) //~v@21R~
    {                                                              //~v@21I~
        if (Dump.Y) Dump.println("CompDlgDora.drawDora");         //~v@21I~//~9219R~
        Bitmap bm=getBitmapDora(Ptd,PswFaceDown);                   //~v@21I~
        Graphics.drawBitmap(canvas,bm,Pxx,Pyy);        //~v@21R~
        int xx=Pxx+bm.getWidth();                                     //~v@21I~
        UView.recycle(bm);                                         //~v@21I~
        return xx;                                                 //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~v@21I~
    //*show Dora                                                   //~v@21I~
    //*********************************************************    //~v@21I~
    private int drawDora()                                          //~v@21I~//~9810R~
    {                                                              //~v@21I~
    	int xx,yy;                                                 //~v@21I~
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
        xx=MARGIN_X;                                               //~9219I~
        yy=MARGIN_Y;                                               //~9219I~
        if (Dump.Y) Dump.println("CompDlgDora.drawDora ctrKanDraw="+ctrKanDrawn);//~v@21I~//~9219R~
        for (int ii=0;ii<=MAXCTR_KAN;ii++)                         //~v@21R~
        {                                                          //~v@21I~
	    	TileData td=shuffled[DORA_TDPOS-ii*STOCK_LAYER];       //~v@21I~
		    xx=drawDora(xx,yy,td,ii>ctrKanDrawn);                  //~v@21R~
            xx+=PIECE_SPACING;                                     //~v@21I~
        }                                                          //~v@21I~
        return xx;                                                 //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~v@21I~
    //*show hidden Dora(uradora)                                   //~v@21I~
    //*********************************************************    //~v@21I~
    private void drawDoraHidden(int PposX)                          //~v@21I~//~9810R~
    {                                                              //~v@21I~
    	int xx,yy;                                                 //~v@21I~
        if (Dump.Y) Dump.println("CompDlgDora.drawDoraHidden ctrKanDraw="+ctrKanDrawn);//~v@21I~//~9810R~
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
        xx=PposX+SPACING_X*4;                                       //~9219I~
        yy=MARGIN_Y;                                               //~9219I~
        for (int ii=0;ii<=ctrKanDrawn;ii++)                        //~v@21R~
        {                                                          //~v@21I~
            if (ii==0 && !RuleSetting.isShowHiddenDora())          //~v@21I~
            	continue;                                          //~v@21I~
            if (ii!=0 && !RuleSetting.isShowHiddenKanDora())       //~v@21I~
            	continue;                                          //~v@21I~
	    	TileData td=shuffled[DORA_TDPOS-ii*STOCK_LAYER-1];     //~v@21I~
		    xx=drawDora(xx,yy,td,false);                                 //~v@21I~
            xx+=PIECE_SPACING;                                     //~v@21I~
        }                                                          //~v@21I~
    }                                                              //~v@21I~
    //*********************************************************    //~9810I~
    private static int getDoraMaxWidth()                           //~9810R~
    {                                                              //~9810I~
        Bitmap bm=AG.aPieces.getBitmapHandPairStock(PLAYER_YOU);   //~9810I~
        int ww=(int)(bm.getWidth()*RATE_DORA);                     //~9810I~
        int xx=MARGIN_X;                                           //~9810I~
        xx+=(ww+PIECE_SPACING)*(MAXCTR_KAN+1);                     //~9810R~
        xx+=SPACING_X*4;                                            //~9810I~
        xx+=(ww+PIECE_SPACING)*(MAXCTR_KAN+1);                     //~9810R~
        if (Dump.Y) Dump.println("CompDlgDora.getDoraMaxWidth width="+xx+",pieceW="+ww);//~9810I~
        return xx;                                                 //~9810I~
    }                                                              //~9810I~
}//class CompDlgDora                                              //~v@21R~//~9219R~
