//*CID://+vamqR~: update#=    159;                                 //+vamqR~
//**********************************************************************//~vamdI~
//2022/04/15 vamq recycle from animationEnd callback. may not called always CB but aboid crash by used recycle BMP//+vamqI~
//2022/04/12 vamp Animation. for Riichi                            //~vampR~
//2022/04/11 vamm multi anim dora                                  //~vammI~
//2022/04/11 vamk change animation to bounce type at the place     //~vamkI~
//2022/04/07 vamh Animation. for Pon/Chii/Kan                      //~vamhI~
//2022/04/05 vamg Animation. at Win call                           //~vamgI~
//2022/04/02 vamd Animation. at first show Dora                    //~vamdI~
//**********************************************************************//~vamdI~
package com.btmtest.game.gv;                                       //~vamdI~
                                                                   //~vamdI~
import com.btmtest.R;                                              //~vamdI~
import com.btmtest.StaticVars;
import com.btmtest.dialog.RuleSettingOperation;
import com.btmtest.game.Players;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;                                     //~vamdI~
import com.btmtest.utils.URunnable;
import com.btmtest.utils.UView;

import static com.btmtest.StaticVars.AG;                           //~vamdI~
import static com.btmtest.game.Complete.*;
import static com.btmtest.game.GConst.*;
//~vamdI~
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;                                          //~vamdI~
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;                      //~vamdI~
import android.view.animation.Animation;                           //~vamdI~
import android.view.animation.AnimationSet;                        //~vamdI~
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;                     //~vamdI~
import android.view.animation.ScaleAnimation;                      //~vamdI~
import android.view.animation.TranslateAnimation;                  //~vamdI~
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

//~vamdI~
public class Anim                                                  //~vamgR~
		implements URunnable.URunnableI                                      //~vamgI~
{                                                                  //~vamgI~
    private static final int ANIM_FUNC_DORA=1;                     //~vamdI~
    private static final int ANIM_FUNC_WIN=2;                      //~vamgI~
    private static final int ANIM_FUNC_CALL=3;                     //~vamhI~
                                                                   //~vamhI~
    private static final int ANIM_DURATION_DORA=2000;	//milisec  //~vamdR~//~vamgR~
    private static final int ANIM_DURATION_ON_THE_PLACE=2000;	//milisec//~vamkR~//~vammR~//~vampR~
    private static final int ANIM_DURATION_CALL=1000;	//milisec  //TODO test//~vamhR~//~vammR~//~vampR~
//  private static final int ANIM_DELAY_RECYCLE=ANIM_DURATION_DORA+3000;	//milisec//~vamgI~//+vamqR~
    private static final int ANIM_DELAY_RECYCLE=3000;	//milisec  //+vamqI~
    private static final float ANIM_ACCEL_RATE=4.0f;                    //~vamdI~//~vamgR~//~vamhM~
    private static final float ANIM_SCALE_ON_THE_PLACE=3.0f;       //~vamkR~
    private static final float ANIM_BOUNCE_RATE=2.0f;              //~vamhM~
    private static final float ANIM_BOUNCE_TAKE=1.5f;              //~vamhI~
    private static final float ANIM_SCALE_KAN_TAKEN=2.0f;          //~vamhI~
                                                                   //~vamhI~
    private static final int STROKE_WIDTH_WINNING_TILE=4;          //~vamgI~
    private static final int LAYOUTID_ANIMATION=R.layout.gvimage_animation;//~vamdI~
    private static final int LLID_ANIMATION=R.id.llAnimation;//~vamdI~//~vamgR~
    private static final int VIEWID_ANIMATION=R.id.Image_Animation;//~vamgI~
    private static final int VIEWID_DORA0=R.id.Image_Dora0;        //~vamkI~
    private static final int VIEWID_DORA1=R.id.Image_Dora1;        //~vammI~
    private static final int VIEWID_DORA2=R.id.Image_Dora2;        //~vammI~
    private static final int VIEWID_DORA3=R.id.Image_Dora3;        //~vammI~
    private static final int VIEWID_DORA4=R.id.Image_Dora4;        //~vammI~
    private static final int VIEWID_REACH=R.id.Image_Reach;        //~vampI~
	private static final int RUNID_START_ANIM=1;                   //~vamgR~
	private static final int RUNID_RECYCLE=2;                      //~vamgR~
//*********************************************************        //~vamdI~
//  private AnimationSet lastAnimation;                            //~vamgR~
    private FrameLayout frameLayout;                               //~vamdI~
    private View llAnimation;                                      //~vamdR~
    private ImageView imageViewAnimation;                          //~vamdI~
    private ImageView viewReach;                                   //~vampI~
    private ImageView[] viewSDora;                                   //~vamkR~//~vammR~
    private View mainView;                                         //~vamgI~
    private int delayUnit;                                         //~vamhI~
//*********************************************************        //~vamdI~
//*!! Pieces  android.graphics.matrix.setRotate: counterClockwise  //~vamhI~
//*!! animation rotation is also same                              //~vamhI~
//*********************************************************        //~vamhI~
//*from MainActivity                                               //~vamgR~
//*********************************************************        //~vamdI~
    public Anim(View PmainView,FrameLayout PframeLayout)           //~vamdR~
    {                                                              //~vamdI~
        if (Dump.Y) Dump.println("Anim.constructor");              //~vamdI~
    	AG.aAnim=this;                                             //~vamdI~
        mainView=PmainView;                                        //~vamdI~
        frameLayout=PframeLayout;                                  //~vamdI~
        init();                                                    //~vamdI~
    }                                                              //~vamdI~
//*********************************************************        //~vamdI~
	private void init()                                            //~vamdI~
    {                                                              //~vamdI~
        if (Dump.Y) Dump.println("Anim.init");                      //~vamdI~
        View v=AG.inflater.inflate(LAYOUTID_ANIMATION,null);  //~vamdI~//~vamgR~
        llAnimation=(LinearLayout)UView.findViewById(v,LLID_ANIMATION);//~vamdR~//~vamgR~
        imageViewAnimation=(ImageView)UView.findViewById(llAnimation,VIEWID_ANIMATION);//~vamgI~
        viewReach=(ImageView)UView.findViewById(llAnimation,VIEWID_REACH);//~vampI~
        viewSDora=new ImageView[1+MAXCTR_KAN];                     //~vammR~
        viewSDora[0]=(ImageView)UView.findViewById(llAnimation,VIEWID_DORA0);//~vamkR~//~vammR~
        viewSDora[1]=(ImageView)UView.findViewById(llAnimation,VIEWID_DORA1);//~vammI~
        viewSDora[2]=(ImageView)UView.findViewById(llAnimation,VIEWID_DORA2);//~vammI~
        viewSDora[3]=(ImageView)UView.findViewById(llAnimation,VIEWID_DORA3);//~vammI~
        viewSDora[4]=(ImageView)UView.findViewById(llAnimation,VIEWID_DORA4);//~vammI~
//      animationListener=getAnimationListener();                  //~vamdI~//~vamgR~
        delayUnit= RuleSettingOperation.getDelayUnit();  //1 or 2   //~vamhI~
        if (Dump.Y) Dump.println("Anim.init llAnimation="+Integer.toHexString(llAnimation.getId())+"="+llAnimation+",imageViewAnimation="+Integer.toHexString(imageViewAnimation.getId())+"="+imageViewAnimation);//~vamgR~
    }                                                              //~vamdI~
//*********************************************************        //~vamdI~//~vamgM~
	private Bitmap getBitmapPiece(TileData Ptd)                    //~vamdI~//~vamgM~
    {                                                              //~vamdI~//~vamgM~
        if (Dump.Y) Dump.println("Anim.getBitmapPiece td="+Ptd);   //~vamdI~//~vamgM~
        Bitmap bmp=AG.aPieces.getBitmapDrawable(Ptd);//~vamdI~     //~vamgR~
        return bmp;                                                //~vamdI~//~vamgM~
    }                                                              //~vamdI~//~vamgM~
//*********************************************************        //~vamgI~
	private Bitmap drawWinningFrame(Bitmap Pbmp)                   //~vamgR~
    {                                                              //~vamgI~
        if (Dump.Y) Dump.println("Anim.drawWinFrame bmp="+Pbmp);   //~vamgI~
        Bitmap bmpMutable=Pbmp.copy(Bitmap.Config.ARGB_8888,true/*mutable*/);//~vamgR~
        Canvas canvas=new Canvas(bmpMutable);                      //~vamgR~
        Rect rect=new Rect();                                      //~vamgI~
        rect.right=bmpMutable.getWidth();                          //~vamgR~
        rect.bottom=bmpMutable.getHeight();                        //~vamgR~
    	Graphics.drawRect(canvas,rect,COMPLETE_COLOR,STROKE_WIDTH_WINNING_TILE);//~vamgI~
//      UView.recycle(Pbmp);                                        //~vamgI~//~vamhR~
        if (Dump.Y) Dump.println("Anim.drawWinFrame mutableBmp="+bmpMutable);//~vamgI~
        return bmpMutable;                                      //~vamgI~
    }                                                              //~vamgI~
//*********************************************************        //~vamdI~
//*from Stock.showDora                                             //~vamdI~
//*********************************************************        //~vamdI~
	public void showDora(Rect PtgtRect, Bitmap PtgtBitmap, int PctrKan, TileData Ptd,int Pplayer)//~vamgI~
    {                                                              //~vamgI~
        if (Dump.Y) Dump.println("Anim.showDora player="+Pplayer+",tgtRect="+PtgtRect+",ctrKan="+PctrKan+",aG.ctrKan="+AG.aTiles.ctrKan+",Ptd="+Ptd);//~vamgI~
        if (PctrKan!=AG.aTiles.ctrKan)                             //~vamgI~
            return;                                                //~vamgI~
//  	if (srcBitmap!=null)                                       //~vamgI~
//  		UView.recycle(srcBitmap); //crash if recycled at onAnimationEnd//~vamgI~
//      Bitmap startBitmap=getBitmapPiece(Ptd);                    //~vamgI~//~vamkR~
//  	showAnim(ANIM_FUNC_DORA,PtgtRect,startBitmap,Pplayer);      //~vamgR~//~vamkR~
//  	showAnimOnThePlace(ANIM_FUNC_DORA,viewDora0,PtgtRect,PtgtBitmap,Pplayer);//~vamkR~//~vammR~
    	showAnimOnThePlace(ANIM_FUNC_DORA,viewSDora[PctrKan],PtgtRect,PtgtBitmap,Pplayer);//~vammI~
	}                                                              //~vamgI~
//*********************************************************        //~vamgM~
//*from Hands, Earth, River                                        //~vamgI~
//*********************************************************        //~vamgI~
    public void showWin(Rect PtgtRect,TileData Ptd,int Pplayer,int Popt)//~vamgI~
    {                                                              //~vamgI~
        if (Dump.Y) Dump.println("Anim.showWin player="+Pplayer+",opt="+Popt+",tgtRect="+PtgtRect+",Ptd="+Ptd);//~vamgI~
        Bitmap startBitmap=getBitmapPiece(Ptd);                    //~vamgI~
        startBitmap=drawWinningFrame(startBitmap);                 //~vamgR~
        int player=AG.aPlayers.getCurrentPlayer();   //taken or discarded//~vamgI~
        Rect tgtRect=PtgtRect;                                     //~vamgI~
        if (Popt==KAN_TAKEN)    //tgtRect is for 4 tiles           //~vamgI~
        {                                                          //~vamgI~
            int unit;                                              //~vamgI~
            if (player==PLAYER_YOU||player==PLAYER_FACING)   //~vamgI~
            {                                                      //~vamgI~
                unit=(tgtRect.right-tgtRect.left)/4;               //~vamgI~
                tgtRect.left+=unit;                                //~vamgI~
                tgtRect.right=tgtRect.left+unit;                   //~vamgI~
            }                                                      //~vamgI~
            else                                                   //~vamgI~
            {                                                      //~vamgI~
                unit=(tgtRect.bottom-tgtRect.top)/4;               //~vamgI~
                tgtRect.top+=unit;                                 //~vamgI~
                tgtRect.bottom=tgtRect.top+unit;                   //~vamgI~
            }                                                      //~vamgI~
        }                                                          //~vamgI~
        if (Dump.Y) Dump.println("Anim.showWin player="+player+",tgtRect="+tgtRect);//~vamgI~
		showAnim(ANIM_FUNC_WIN,tgtRect,startBitmap,player);        //~vamgR~
    }                                                              //~vamgI~
//*********************************************************        //~vamgI~
//*for win                                                         //~vampI~
//*********************************************************        //~vampI~
	private void showAnim(int PfuncID,Rect PtgtRect,Bitmap PstartBitmap,int Pplayer)//~vamgR~
    {                                                              //~vamgI~
        if (Dump.Y) Dump.println("Anim.showAnim funcid="+PfuncID+",rect="+PtgtRect+",player="+Pplayer+",startBitmap="+PstartBitmap);//~vamgR~//~vamkR~
        Rect tgtRect=PtgtRect;                                     //~vamgI~
//  	srcView=imageViewAnimation;                                //~vamgI~
//      srcView.setImageBitmap(srcBitmap);	//piece in drawable    //~vamgI~
        AnimationSet set=new AnimationSet(true/*all member shareInterpolator*/);//~vamgI~
        int srcWW=PstartBitmap.getWidth();                         //~vamgI~
        int srcHH=PstartBitmap.getHeight();                        //~vamgI~
                                                                   //~vamgI~
        int posPlayer=2;                                           //~vamgI~
        Bitmap[][] bmss=AG.bitmapStarter;                          //~vamgI~
        Bitmap bmpFrom=bmss[posPlayer][posPlayer];                 //~vamgI~
        Point p=AG.aMJTable.getStarterPos(posPlayer/*facing*/);    //~vamgI~
        p.x+=bmpFrom.getWidth()/2;                                 //~vamgI~
        p.y+=bmpFrom.getHeight()/2;                                //~vamgI~
        int fromWW=AG.aPieces.pieceWW*2;                           //~vamgI~
        int fromHH=AG.aPieces.pieceHH*2;                           //~vamgI~
        p.x-=fromWW/2;                                             //~vamgI~
        p.y-=fromHH/2;                                             //~vamgI~
        int HH=tgtRect.bottom-tgtRect.top;                         //~vamgR~
        int WW=tgtRect.right-tgtRect.left;                         //~vamgR~
        int tgtHH=Math.max(HH,WW);                                 //~vamgI~
        int tgtWW=Math.min(HH,WW);                                 //~vamgI~
        if (Dump.Y) Dump.println("Anim.showAnim transAnimation fromWW="+fromWW+",fromHH="+fromHH+",tgtWW="+tgtWW+",tgtHH="+tgtHH);//~vamgI~//~vamkR~
                                                                   //~vamgI~
        float rateXstart=(float)fromWW/srcWW;                      //~vamgI~
        float rateYstart=(float)fromHH/srcHH;                      //~vamgI~
        float rateXend=(float)tgtWW/srcWW;                         //~vamgI~
        float rateYend=(float)tgtHH/srcWW;                         //~vamgI~
        if (Dump.Y) Dump.println("Anim.showAnim scaleAnimation rateXstart="+rateXstart+",rateYstart="+rateYstart+",rateXend="+rateXend+",rateYend="+rateYend);//~vamgI~//~vamkR~
        ScaleAnimation scale=new ScaleAnimation(rateXstart/*fromX*/,rateXend/*toX*/,rateYstart/*fromY*/,rateXend/*toY*/,0.0f/*pivotX*/,0.0f/*pibotY*/);//~vamgI~
        set.addAnimation(scale);                                   //~vamgI~
                                                                   //~vamgI~
		float rotateStart=0.0f,rotateEnd,pivotX=0.5f,pivotY=0.5f/*leftTop*/;//~vamgI~
        float endX,endY;                                           //~vamgI~
    	switch(Pplayer)                                            //~vamgI~
        {                                                          //~vamgI~
        case PLAYER_YOU:                                           //~vamgI~
          if (HH<WW)	//lying                                    //~vamgI~
          {                                                        //~vamgI~
	        rotateEnd=-90.0f; //top to left                        //~vamgI~
        	endX=(float)tgtRect.left;                              //~vamgR~
	        endY=(float)tgtRect.bottom;                            //~vamgR~
          }                                                        //~vamgI~
          else                                                     //~vamgI~
          {                                                        //~vamgI~
	        rotateEnd=0.0f;                                        //~vamgI~
        	endX=(float)tgtRect.left;                              //~vamgR~
	        endY=(float)tgtRect.top;                               //~vamgR~
          }                                                        //~vamgI~
            break;                                                 //~vamgI~
        case PLAYER_RIGHT:                                         //~vamgI~
          if (HH>WW)	//lying for stock                          //~vamgI~
          {                                                        //~vamgI~
	        rotateEnd=-180.0f;  //top to down                      //~vamgI~
        	endX=(float)tgtRect.right;                             //~vamgR~
	        endY=(float)tgtRect.bottom;                            //~vamgR~
          }                                                        //~vamgI~
          else                                                     //~vamgI~
          {                                                        //~vamgI~
	        rotateEnd=-90.0f;	//clockwise                        //~vamgI~
        	endX=(float)tgtRect.left;                              //~vamgR~
	        endY=(float)tgtRect.bottom;                            //~vamgR~
          }                                                        //~vamgI~
            break;                                                 //~vamgI~
        case PLAYER_FACING:                                        //~vamgI~
          if (HH<WW)	//lying                                    //~vamgI~
          {                                                        //~vamgI~
	        rotateEnd=90.0f;  //top to right                       //~vamgI~
        	endX=(float)tgtRect.right;                             //~vamgR~
	        endY=(float)tgtRect.top;                               //~vamgR~
          }                                                        //~vamgI~
          else                                                     //~vamgI~
          {                                                        //~vamgI~
	        rotateEnd=180.0f;	//clockwise                        //~vamgI~
        	endX=(float)tgtRect.right;                             //~vamgR~
	        endY=(float)tgtRect.bottom;                            //~vamgI~
          }                                                        //~vamgI~
            break;                                                 //~vamgI~
        default:      //Left                                       //~vamgI~
          if (HH>WW)	//lying vs stock                           //~vamgI~
          {                                                        //~vamgI~
	        rotateEnd=0.0f;  //top to top                          //~vamgI~
        	endX=(float)tgtRect.left;                              //~vamgI~
	        endY=(float)tgtRect.top;                               //~vamgI~
          }                                                        //~vamgI~
          else                                                     //~vamgI~
          {                                                        //~vamgI~
	        rotateEnd=90.0f;	//clockwise                        //~vamgI~
        	endX=(float)tgtRect.right;                             //~vamgI~
	        endY=(float)tgtRect.top;                               //~vamgI~
          }                                                        //~vamgI~
        }                                                          //~vamgI~
        RotateAnimation rotate=new RotateAnimation(rotateStart,rotateEnd,pivotX,pivotY);//~vamgI~
        set.addAnimation(rotate);                                  //~vamgI~
                                                                   //~vamgI~
        float startX=(float)p.x;                                   //~vamgI~
        float startY=(float)p.y;                                   //~vamgI~
        if (Dump.Y) Dump.println("Anim.showAnim transAnimation startX="+startX+",startY="+startY+",endX="+endX+",endY="+endY);//~vamgI~//~vamkR~
        TranslateAnimation trans=new TranslateAnimation(           //~vamgI~
						Animation.ABSOLUTE/*fromXType*/,startX/*fromX*/,//~vamgI~
						Animation.ABSOLUTE/*toXType*/,endX/*toX*/, //~vamgI~
						Animation.ABSOLUTE/*fromYType*/,startY/*fromY*/,//~vamgI~
						Animation.ABSOLUTE/*toYType*/,endY/*toY*/);//~vamgI~
        set.addAnimation(trans);                                   //~vamgR~
                                                                   //~vamgI~
        AccelerateInterpolator ai=new AccelerateInterpolator(ANIM_ACCEL_RATE);//~vamgR~
        set.setInterpolator(ai);                                   //~vamgI~
        set.setDuration(ANIM_DURATION_DORA/delayUnit);                       //~vamgR~//~vamhR~
        startAnimationUI(PfuncID,set,PstartBitmap);                //~vamgR~
    }                                                              //~vamgI~
//*********************************************************        //~vamkI~
//*dora,pon,kan,chii,reach                                         //~vampI~
//*********************************************************        //~vampI~
	private void showAnimOnThePlace(int PfuncID,ImageView PimageView,Rect PtgtRect,Bitmap PstartBitmap,int Pplayer)//~vamkR~
    {                                                              //~vamkI~
        if (Dump.Y) Dump.println("Anim.showAnimOnThePlace funcid="+PfuncID+",rect="+PtgtRect+",player="+Pplayer+",startBitmap="+PstartBitmap);//~vamkR~
        Rect tgtRect=PtgtRect;                                     //~vamkI~
        AnimationSet set=new AnimationSet(true/*all member shareInterpolator*/);//~vamkI~
        float scaleRate=ANIM_SCALE_ON_THE_PLACE;                   //~vamkI~
        int toHH=PtgtRect.bottom-PtgtRect.top;                     //~vamkI~
        int toWW=PtgtRect.right-PtgtRect.left;                     //~vamkI~
        float fromWW=toWW*scaleRate;                                //~vamkI~
        float fromHH=toHH*scaleRate;                                //~vamkI~
        if (Dump.Y) Dump.println("Anim.showAnimOnThePlace transAnimation scaleRate="+scaleRate+",toWW="+toWW+",toHH="+toHH+",fromWW="+fromWW+",fromHH="+fromHH);//~vamkR~
        float rateXstart=scaleRate;                                //~vamkI~
        float rateYstart=scaleRate;                                //~vamkI~
        float rateXend=1.0f;                                        //~vamkI~
        float rateYend=1.0f;                                        //~vamkI~
        if (Dump.Y) Dump.println("Anim.showAnimOnThePlace scaleAnimation rateXstart="+rateXstart+",rateYstart="+rateYstart+",rateXend="+rateXend+",rateYend="+rateYend);//~vamkR~
        ScaleAnimation scale=new ScaleAnimation(rateXstart/*fromX*/,rateXend/*toX*/,rateYstart/*fromY*/,rateXend/*toY*/,0.5f/*pivotX*/,0.5f/*pibotY*/);//~vamkR~
        set.addAnimation(scale);                                   //~vamkI~
                                                                   //~vamkI~
        float startX=tgtRect.left-(fromWW-toWW)/2;                 //~vamkI~
        float startY=tgtRect.top-(fromHH-toHH)/2;                  //~vamkR~
        float endX=tgtRect.left;                                   //~vamkI~
        float endY=tgtRect.top;                                    //~vamkI~
        if (Dump.Y) Dump.println("Anim.showAnimOnThePlace transAnimation startX="+startX+",startY="+startY+",endX="+endX+",endY="+endY);//~vamkR~
        TranslateAnimation trans=new TranslateAnimation(           //~vamkI~
						Animation.ABSOLUTE/*fromXType*/,startX/*fromX*/,//~vamkI~
						Animation.ABSOLUTE/*toXType*/,endX/*toX*/, //~vamkI~
						Animation.ABSOLUTE/*fromYType*/,startY/*fromY*/,//~vamkI~
						Animation.ABSOLUTE/*toYType*/,endY/*toY*/);//~vamkI~
        set.addAnimation(trans);                                   //~vamkI~
                                                                   //~vamkI~
        AccelerateInterpolator ai=new AccelerateInterpolator(ANIM_ACCEL_RATE);//~vamkI~
        set.setInterpolator(ai);                                   //~vamkI~
        set.setDuration(ANIM_DURATION_ON_THE_PLACE/delayUnit);     //~vamkI~
        BounceInterpolator bi=new BounceInterpolator();            //~vamkI~
        set.setInterpolator(bi);                                   //~vamkI~
        startAnimationUI(PfuncID,PimageView,set,PstartBitmap);     //~vamkR~
    }                                                              //~vamkI~
//*********************************************************        //~vampI~
//*from River                                                      //~vampI~
//*********************************************************        //~vampI~
    public void calledReach(int Pplayer,Rect Prect,Bitmap Pbmp)    //~vampI~
    {                                                              //~vampI~
        if (Dump.Y) Dump.println("Anim.calledReach player="+Pplayer+",rect="+Prect+",bmp="+Pbmp);//~vampI~
		showAnimOnThePlace(ANIM_FUNC_CALL,viewReach,Prect,Pbmp,Pplayer);//~vampI~
    }                                                              //~vampI~
//*********************************************************        //~vamhM~
//*from UAPon,UAChii,UAKan                                         //~vamhM~
//*if flag==KAN_TAKEN(includes KAN_ADD) playerRiver=-1 and bmRiver=null;//~vamhM~
//*********************************************************        //~vamhM~
    public void calledPonKanChii(int Pflag/*TDF_XXX*/,int PplayerEarth,Rect PrectEarth,TileData PtdOnEarth,Bitmap PbmOnEarth,//~vamhM~
					int PplayerRiver,Rect PrectRiver,Bitmap PbmRiver)//~vamhM~
    {                                                              //~vamhM~
        if (Dump.Y) Dump.println("Anim.calledPonKanChii Pflag="+Pflag+",playerEarth="+PplayerEarth+",rectEarth="+PrectEarth+",tdOnEarth="+PtdOnEarth+",bmOnEarth="+PbmOnEarth+",playerRiver="+PplayerRiver+",rectRiver="+PrectRiver+",bmpRiver="+PbmRiver);//~vamhM~
    	if (PbmRiver==null)                                         //~vamhM~
        	showKanTaken(Pflag,PplayerEarth,PrectEarth,PtdOnEarth,PbmOnEarth);//~vamhR~
        else                                                       //~vamhM~
        	showPonKanChiiRiver(Pflag,PplayerEarth,PrectEarth,PtdOnEarth,PbmOnEarth,PplayerRiver,PrectRiver,PbmRiver);//~vamhR~
    }                                                              //~vamhM~
//*********************************************************        //~vamhI~
    private void showPonKanChiiRiver(int Pflag/*TDF_XXX*/,int PplayerEarth,Rect PrectEarth,TileData PtdOnEarth,Bitmap PbmOnEarth,//~vamhR~
					int PplayerRiver,Rect PrectRiver,Bitmap PbmRiver)//~vamhI~
    {                                                              //~vamhI~
        if (Dump.Y) Dump.println("Anim.showPonKanChiiRiver Pflag="+Pflag+",playerEarth="+PplayerEarth+",rectEarth="+PrectEarth+",tdOnEarth="+PtdOnEarth+",bmOnEarth="+PbmOnEarth+",playerRiver="+PplayerRiver+",rectRiver="+PrectRiver+",bmpRiver="+PbmRiver);//~vamhR~
        AnimationSet set=new AnimationSet(true/*all member shareInterpolator*/);//~vamhI~
        int srcWW=PbmRiver.getWidth();                             //~vamhI~
        int srcHH=PbmRiver.getHeight();                            //~vamhI~
                                                                   //~vamhI~
        Bitmap bmpFrom=PbmRiver;                                   //~vamhI~
        int tgtHH=PrectEarth.bottom-PrectEarth.top;                //~vamhI~
        int tgtWW=PrectEarth.right-PrectEarth.left;                //~vamhI~
        if (Dump.Y) Dump.println("Anim.showPonKanChiiRiver fromWW="+srcWW+",fromHH="+srcHH+",tgtWW="+tgtWW+",tgtHH="+tgtHH);//~vamhR~
//*scale                                                           //~vamhR~
        float rateXstart=1.0f;                                     //~vamhI~
        float rateYstart=1.0f;                                     //~vamhI~
        float rateXend=(float)Math.max(tgtHH,tgtWW)/Math.max(srcHH,srcWW);//~vamhR~
        float rateYend=(float)Math.min(tgtHH,tgtWW)/Math.min(srcHH,srcWW);//~vamhI~
        if (Dump.Y) Dump.println("Anim.showPonKanChiiRiver scaleAnimation rateXstart="+rateXstart+",rateYstart="+rateYstart+",rateXend="+rateXend+",rateYend="+rateYend);//~vamhR~
        ScaleAnimation scale=new ScaleAnimation(rateXstart/*fromX*/,rateXend/*toX*/,rateYstart/*fromY*/,rateXend/*toY*/,0.0f/*pivotX*/,0.0f/*pibotY*/);//~vamhI~
        set.addAnimation(scale);                                   //~vamhI~
//*rotation                                                        //~vamhI~
		float rotateStart=0.0f,rotateEnd=0.0f,pivotX=0.5f,pivotY=0.5f/*leftTop*/;//~vamhR~
        float endX,endY,startX,startY;                             //~vamhR~
        int posRelative= Players.playerRelative(PplayerRiver,PplayerEarth);//~vamhI~
        if (posRelative==PLAYER_FACING) //taken facing discarded  //same as Earth.getBitmapPair; when earth from facing River , earth lying to left top//~vamhI~
        	rotateEnd=90f;                                         //~vamhI~
        if (PtdOnEarth.isReached())                                //~vamhI~
        	rotateEnd+=90f;	//stand up                             //~vamhR~
        if (Dump.Y) Dump.println("Anim.showPonKanChiiRiver after chk reach rotateEnd="+rotateEnd);//~vamhR~
        RotateAnimation rotate=new RotateAnimation(rotateStart,rotateEnd,pivotX,pivotY);//~vamhR~
        set.addAnimation(rotate);                                  //~vamhR~
//*translate                                                       //~vamhR~
        startX=(float)PrectRiver.left;                             //~vamhM~
        startY=(float)PrectRiver.top;                              //~vamhM~
        endX=(float)PrectEarth.left;                               //~vamhM~
        endY=(float)PrectEarth.top;                                //~vamhM~
        if (rotateEnd!=0f)                                         //~vamhI~
        {                                                          //~vamhI~
            if (rotateEnd<=90f)                                    //~vamhI~
            {                                                      //~vamhI~
                endX=(float)PrectEarth.right;                      //~vamhI~
            }                                                      //~vamhI~
            else                                                   //~vamhI~
            if (rotateEnd<=180f)                                   //~vamhI~
            {                                                      //~vamhI~
                endX=(float)PrectEarth.right;                      //~vamhI~
                endY=(float)PrectEarth.bottom;                     //~vamhI~
            }                                                      //~vamhI~
            else                                                   //~vamhI~
            if (rotateEnd<=270f)                                   //~vamhI~
            {                                                      //~vamhI~
                endY=(float)PrectEarth.bottom;                     //~vamhI~
            }                                                      //~vamhI~
        }                                                          //~vamhI~
        if (Dump.Y) Dump.println("Anim.showPonKanChiiRiver startX="+startX+",startY="+startY+",exdX="+endX+",endY="+endY);//~vamhI~
        TranslateAnimation trans=new TranslateAnimation(           //~vamhI~
						Animation.ABSOLUTE/*fromXType*/,startX/*fromX*/,//~vamhI~
						Animation.ABSOLUTE/*toXType*/,endX/*toX*/, //~vamhI~
						Animation.ABSOLUTE/*fromYType*/,startY/*fromY*/,//~vamhI~
						Animation.ABSOLUTE/*toYType*/,endY/*toY*/);//~vamhI~
        set.addAnimation(trans);                                   //~vamhI~
                                                                   //~vamhI~
        AccelerateInterpolator ai=new AccelerateInterpolator(ANIM_ACCEL_RATE);//~vamhI~
        set.setInterpolator(ai);                                   //~vamhI~
        BounceInterpolator bi=new BounceInterpolator();            //~vamhR~
        set.setInterpolator(bi);                                   //~vamhR~
        set.setDuration(ANIM_DURATION_CALL/delayUnit);             //~vamhR~
        startAnimationUI(ANIM_FUNC_CALL,set,bmpFrom);              //~vamhR~
    }                                                              //~vamhI~
//*********************************************************        //~vamhI~
//*Kan_ADD and KAN_TAKEN                                           //~vamhI~
//*********************************************************        //~vamhI~
    private void showKanTaken(int Pflag/*TDF_XXX*/,int PplayerEarth,Rect PrectEarth,TileData PtdOnEarth,Bitmap PbmpOnEarth)//~vamhI~
    {                                                              //~vamhI~
        if (Dump.Y) Dump.println("Anim.showKanTaken Pflag="+Pflag+",playerEarth="+PplayerEarth+",rectEarth="+PrectEarth+",tdOnEarth="+PtdOnEarth+",bmOnEarth="+PbmpOnEarth);//~vamhI~
        AnimationSet set=new AnimationSet(true/*all member shareInterpolator*/);//~vamhM~
        Rect tgtRect=PrectEarth;                                     //~vamhI~
        int tgtWW=tgtRect.right-tgtRect.left;                      //~vamhI~
        int tgtHH=tgtRect.bottom-tgtRect.top;                      //~vamhI~
		int player=PplayerEarth;                                   //~vamhI~
        if (PtdOnEarth.isKanAddedTile())                                    //~vamhI~
        	player=Players.nextPlayer(player);                     //~vamhI~
        Bitmap srcBitmap=AG.aPieces.getBitmapHandPair(PtdOnEarth,player);	//standing//~vamhI~
        int srcWW=srcBitmap.getWidth();                            //~vamhR~
        int srcHH=srcBitmap.getHeight();                           //~vamhR~
        if (Dump.Y) Dump.println("Anim.showKanTaken srcWW="+srcWW+",srcHH="+srcHH+",tgtWW="+tgtWW+",tgtHH="+tgtHH);//~vamhI~
        float scaleWstart=ANIM_SCALE_KAN_TAKEN;                    //~vamhR~
        float scaleHstart=ANIM_SCALE_KAN_TAKEN;                    //~vamhR~
        float scaleWend=(float)tgtWW/srcWW;                        //~vamhR~
        float scaleHend=(float)tgtHH/srcHH;                        //~vamhR~
        if (Dump.Y) Dump.println("Anim.showKanTaken earthPieceW="+StaticVars.earthPieceW+",erathPieceH="+StaticVars.earthPieceH+",srcWW="+srcWW+",srcHH="+srcHH+",tgtWW="+tgtWW+",tgtHH="+tgtHH);//~vamhR~
        if (Dump.Y) Dump.println("Anim.showKanTaken scaleWstart="+scaleWstart+",scaleHstart="+scaleHstart+",scaleWend="+scaleWend+",scaleHend="+scaleHend);//~vamhI~
        ScaleAnimation scale=new ScaleAnimation(scaleWstart/*fromX*/,scaleWend/*toX*/,scaleHstart/*fromY*/,scaleHend/*toY*/,1.0f/*pivotX*/,1.0f/*pibotY*/);//~vamhR~
        set.addAnimation(scale);                                   //~vamhI~
                                                                   //~vamhI~
        float endX=(float)PrectEarth.left;                         //~vamhI~
        float endY=(float)PrectEarth.top;                          //~vamhI~
        float startW=srcWW*scaleWstart;                            //~vamhR~
        float startH=srcHH*scaleWstart;                            //~vamhI~
        float expandW=(startW-tgtWW)/2;                            //~vamhI~
        float expandH=(startH-tgtHH)/2;                            //~vamhR~
        float startX,startY;                                       //~vamhI~
        switch(PplayerEarth)                                       //~vamhI~
        {                                                          //~vamhI~
        case PLAYER_YOU:                                           //~vamhI~
            startX=endX-expandW;                                   //~vamhI~
            startY=endY-startH;                                    //~vamhR~
            break;                                                 //~vamhI~
        case PLAYER_RIGHT:                                         //~vamhI~
            startX=endX-startW;                                    //~vamhR~
            startY=endY-expandH;                                   //~vamhR~
            break;                                                 //~vamhI~
        case PLAYER_FACING:                                        //~vamhI~
            startX=endX-expandW;                                   //~vamhR~
            startY=endY+tgtHH;                                     //~vamhR~
            break;                                                 //~vamhI~
        default:      //Left                                       //~vamhI~
            startX=endX+tgtWW;                                     //~vamhR~
            startY=endY-expandH;                                   //~vamhR~
        }                                                          //~vamhI~
        if (Dump.Y) Dump.println("Anim.showKanTaken transAnimation startX="+startX+",startY="+startY+",endX="+endX+",endY="+endY);//~vamhR~
        if (Dump.Y) Dump.println("Anim.showKanTaken transAnimation expandW="+expandW+",expandH="+expandH+",tgtWW="+tgtWW+",tgtHH="+tgtHH+",srcWW="+srcWW+",srcHH="+srcHH);//~vamhR~
        TranslateAnimation trans=new TranslateAnimation(           //~vamhR~
                        Animation.ABSOLUTE/*fromXType*/,startX/*fromX*/,//~vamhR~
                        Animation.ABSOLUTE/*toXType*/,endX/*toX*/, //~vamhR~
                        Animation.ABSOLUTE/*fromYType*/,startY/*fromY*/,//~vamhR~
                        Animation.ABSOLUTE/*toYType*/,endY/*toY*/);//~vamhR~
        set.addAnimation(trans);                                   //~vamhR~
                                                                   //~vamhI~
        AccelerateInterpolator ai=new AccelerateInterpolator(ANIM_ACCEL_RATE);//~vamhI~
        set.setInterpolator(ai);                                   //~vamhI~
        BounceInterpolator bi=new BounceInterpolator();            //~vamhR~
        set.setInterpolator(bi);                                   //~vamhR~
        set.setDuration(ANIM_DURATION_CALL/delayUnit);             //~vamhI~
        startAnimationUI(ANIM_FUNC_CALL,set,srcBitmap);            //~vamhR~
    }                                                              //~vamhI~
//*********************************************************        //~vamgI~
//*for Win,PonKanChii                                              //~vampI~
//*********************************************************        //~vampI~
	public void startAnimationUI(int PfuncID,AnimationSet Pset,Bitmap Pbitmap)//~vamgR~
    {                                                              //~vamgI~
        if (Dump.Y) Dump.println("Anim.startAnimation funcID="+PfuncID+",set="+Pset);//~vamgI~
//  	RunParmData parmObj=new RunParmData(PfuncID,Pset,Pbitmap); //~vamgI~//~vamkR~
		RunParmData parmObj=new RunParmData(PfuncID,Pset,Pbitmap,imageViewAnimation);//~vamkI~
		URunnable.runOnUiThread(this,0/*delay*/,parmObj,RUNID_START_ANIM);//~vamgR~
	}                                                              //~vamgI~
//*********************************************************        //~vamkI~
//*for dora                                                        //~vampI~
//*********************************************************        //~vampI~
	public void startAnimationUI(int PfuncID,ImageView PimageView,AnimationSet Pset,Bitmap Pbitmap)//~vamkI~
    {                                                              //~vamkI~
        if (Dump.Y) Dump.println("Anim.startAnimation funcID="+PfuncID+",set="+Pset+",view="+PimageView);//~vamkI~
		RunParmData parmObj=new RunParmData(PfuncID,Pset,Pbitmap,PimageView);//~vamkI~
		URunnable.runOnUiThread(this,0/*delay*/,parmObj,RUNID_START_ANIM);//~vamkI~
	}                                                              //~vamkI~
//*********************************************************        //~vamgI~
	public void URunnableCB(Object PparmObj,int PparmInt)          //~vamgI~
    {                                                              //~vamgI~
        int runID=PparmInt;                                        //~vamgM~
        if (Dump.Y) Dump.println("Anim.URunnableCB runID="+runID); //~vamgI~
    	RunParmData parm=(RunParmData)PparmObj;                     //~vamgI~
        Bitmap bm;                                                 //~vamgI~
        switch(runID)                                              //~vamgI~
        {                                                          //~vamgI~
        case RUNID_START_ANIM:                                     //~vamgI~
            AnimationSet set=parm.set;                             //~vamgR~
            bm=parm.bm;                                            //~vamgR~
            int funcID=parm.funcID;                                //~vamgR~
            if (Dump.Y) Dump.println("Anim.URunnableCB RUNID_START_ANIM func="+funcID+",set="+set+",bmp="+bm);//~vamgR~
            startAnimationUIrun(funcID,set,bm);                    //~vamgR~
            break;                                                 //~vamgI~
        case RUNID_RECYCLE:                                        //~vamgI~
            bm=parm.bm;                                            //~vamgI~
            if (Dump.Y) Dump.println("Anim.URunnableCB RUNID_RECYCLE bmp="+bm);//~vamgI~
	        UView.recycle(bm);                                     //~vamgI~
            break;                                                 //~vamgI~
        }                                                          //~vamgI~
    }                                                              //~vamgI~
//*********************************************************        //~vamgI~
	public void startAnimationUIrun(int PfuncID,AnimationSet Pset,Bitmap Pbitmap)//~vamgI~
    {                                                              //~vamgI~
        if (Dump.Y) Dump.println("Anim.startAnimationUIrun funcID="+PfuncID+",set="+Pset);//~vamgR~
		View ll=findAnimationViewAdded();                          //~vamgI~
        if (ll!=null)	//already added                            //~vamgR~
        {                                                          //~vamgI~
        	if (Dump.Y) Dump.println("Anim.startAnimation @@@@ use previously added llView");//~vamgI~//~vammR~
//          cancelAnimation();                                     //~vamgR~//~vammR~
        }                                                          //~vamgI~
        else                                                       //~vamgI~
        {                                                          //~vamgI~
        	if (Dump.Y) Dump.println("Anim.startAnimationUIrun addView");//~vamgR~
        	int lp=ViewGroup.LayoutParams.MATCH_PARENT;            //~vamgI~
	    	frameLayout.addView(llAnimation,lp,lp);                //~vamgI~
        }                                                          //~vamgI~
        MyListener listener=getAnimationListener(PfuncID,Pbitmap);                //~vamgI~
        Pset.setAnimationListener(listener);                     //~vamgR~
        ImageView v=imageViewAnimation;                            //~vamgR~
        v.setImageBitmap(Pbitmap);	//piece in drawable            //~vamgR~
//  	setVisibility(true);                                       //~vamgR~
        v.startAnimation(Pset);                                    //~vamgR~
//    if (PfuncID==ANIM_FUNC_WIN)   //copyed bitmap                //~vamhI~//+vamqR~
//      recycle(Pbitmap);                                          //~vamgM~//+vamqR~
//      lastAnimation=Pset;                                        //~vamgR~
        if (Dump.Y) Dump.println("Anim.startAnimationUIrun exit"); //~vamgI~
	}                                                              //~vamgI~
//*********************************************************        //~vamgM~
	public View findAnimationViewAdded()                           //~vamgM~
    {                                                              //~vamgM~
        int ctr=frameLayout.getChildCount();                       //~vamgM~
        if (Dump.Y) Dump.println("Anim.findAnimationViewAdded childCount="+ctr);//~vamgM~
        View v;                                                    //~vamgM~
        for (int ii=0;ii<ctr;ii++)                                 //~vamgM~
        {                                                          //~vamgM~
        	v=frameLayout.getChildAt(ii);                          //~vamgM~
            int id=v.getId();                                      //~vamgM~
		    if (Dump.Y) Dump.println("Anim.findAnimationViewAdded childAt "+ii+"="+Integer.toHexString(id)+"="+v);//~vamgM~
            if (id==LLID_ANIMATION)                                //~vamgM~
            {                                                      //~vamgM~
		        if (Dump.Y) Dump.println("Anim.findAnimationViewAdded found="+v);//~vamgM~
            	return v;                                          //~vamgM~
            }                                                      //~vamgM~
        }                                                          //~vamgM~
		if (Dump.Y) Dump.println("Anim.findAnimationViewAdded @@@@ not found=");//~vamgM~
        return null;                                               //~vamgM~
    }                                                              //~vamgM~
//*********************************************************        //~vamgI~
//*from gc at endgame                                              //~vamgI~
//*********************************************************        //~vamgI~
	public void removeAnimation()                                  //~vamgI~
    {                                                              //~vamgI~
        if (Dump.Y) Dump.println("Anim.removeAnimation");          //~vamgI~
//      cancelAnimation();                                         //~vamgI~//~vammR~
        cancelAnimationAll();                                      //~vammI~
    	removeView();                                              //~vamgI~
    }                                                              //~vamgI~
//*********************************************************        //~vamdI~
//*run on UIThread                                                 //~vamdI~
//*********************************************************        //~vamdI~
	public void onAnimationEndCB(int PfuncID,Animation Panimation,Bitmap Pbitmap)             //~vamdR~//~vamgR~
    {                                                              //~vamdI~
        if (Dump.Y) Dump.println("Anim.onAnimationEndCB PfuncID="+PfuncID+",bitmap="+Pbitmap+",Panimation="+Panimation);//~vamdR~//~vamgR~
        Panimation.setAnimationListener(null);               //~vamdR~
//      Panimation.reset();                                        //~vamgI~
//      imageViewAnimation.clearAnimation(); //TODO test           //~vamgR~
//  	setVisibility(false);                                      //~vamgR~
        switch(PfuncID)                                         //~vamdI~
        {                                                          //~vamdI~
        case ANIM_FUNC_DORA:                                       //~vamdI~
            onAnimationEndShowDora();                              //~vamdI~
            break;                                                 //~vamdI~
        case ANIM_FUNC_WIN:                                        //~vamgI~
            onAnimationEndShowWin(Pbitmap);                               //~vamgI~//+vamqR~
            break;                                                 //~vamgI~
        }                                                          //~vamdI~
//      if (Panimation==lastAnimation)                             //~vamgR~
//          removeView();                                          //~vamgR~
    }                                                              //~vamdI~
////*********************************************************      //~vamgR~
////*animate also with android:visibility="invisible"              //~vamgI~
////*********************************************************      //~vamgI~
//    private void setVisibility(boolean PswShow)                  //~vamgR~
//    {                                                            //~vamgR~
//      if (true) //TODO test                                      //~vamgR~
//        return;                                                  //~vamgR~
//        if (Dump.Y) Dump.println("Anim.setVisibility swShow="+PswShow);//~vamgR~
//        View v=imageViewAnimation;                               //~vamgR~
//        if (PswShow)                                             //~vamgR~
//            v.setVisibility(View.VISIBLE);                       //~vamgR~
//        else                                                     //~vamgR~
//            v.setVisibility(View.INVISIBLE);                     //~vamgR~
//    }                                                            //~vamgR~
//*********************************************************        //~vamgM~
//*cancel and clear                                                //~vamgI~
//*********************************************************        //~vamgI~
//  private void cancelAnimation()                                 //~vamgM~//~vammR~
    private void cancelAnimation(View Pview)                       //~vammI~
    {                                                              //~vamgM~
        if (Dump.Y) Dump.println("Anim.cancelAnimation Pview="+Pview);          //~vamgM~//~vammR~
//      View v=imageViewAnimation;                                 //~vamgM~//~vammR~
        View v=Pview;                                              //~vammI~
        AnimationSet set=(AnimationSet)(v.getAnimation());         //~vamgM~
        if (Dump.Y) Dump.println("Anim.cancelAnimation getAnimation="+set);//~vamgM~
        if (set!=null)                                             //~vamgM~
        {                                                          //~vamgM~
	        if (Dump.Y) Dump.println("Anim.cancelAnimation set.cancel()");//~vamgM~
            set.cancel();                                          //~vamgM~
        }                                                          //~vamgM~
	    if (Dump.Y) Dump.println("Anim.cancelAnimation v.clearAnimation()");//~vamgM~
	    v.clearAnimation();                                        //~vamgM~
    }                                                              //~vamgM~
//*********************************************************        //~vammI~
	private void cancelAnimationAll()                              //~vammI~
    {                                                              //~vammI~
        if (Dump.Y) Dump.println("Anim.cancelAnimationAll");       //~vammI~
    	cancelAnimation(imageViewAnimation);                       //~vammI~
    	cancelAnimation(viewReach);                                //~vampI~
        for (int ii=0;ii<viewSDora.length;ii++)                    //~vammI~
        {                                                          //~vammI~
	    	cancelAnimation(viewSDora[ii]);                        //~vammI~
        }                                                          //~vammI~
    }                                                              //~vammI~
//*********************************************************        //~vamgI~
    private void removeView()                                      //~vamgR~
    {                                                              //~vamgI~
        if (Dump.Y) Dump.println("Anim.removeView");               //~vamgI~
        View ll=findAnimationViewAdded();                          //~vamgR~
        if (ll!=null)	//already added                            //~vamgR~
        {                                                          //~vamgI~
        	if (Dump.Y) Dump.println("Anim.removeView issue remove");//~vamgI~
        	frameLayout.removeView(ll);                            //~vamgR~
        }                                                          //~vamgI~
    }                                                              //~vamgI~
//*********************************************************        //~vamgI~
    private void recycle(Bitmap Pbitmap)                           //~vamgI~
    {                                                              //~vamgI~
        if (Dump.Y) Dump.println("Anim.recycle bitmap="+Pbitmap);  //~vamgI~
//  	RunParmData parmObj=new RunParmData(0,null,Pbitmap);       //~vamgI~//~vamkR~
    	RunParmData parmObj=new RunParmData(0,null,Pbitmap,null/*imageView*/);//~vamkR~
		URunnable.runOnUiThread(this,ANIM_DELAY_RECYCLE/*delay*/,parmObj,RUNID_RECYCLE);//~vamgR~
    }                                                              //~vamgI~
//*********************************************************        //~vamdI~
	private void onAnimationEndShowDora()                             //~vamdI~
    {                                                              //~vamdI~
        if (Dump.Y) Dump.println("Anim.onAnimationEndShowDora");   //~vamdI~
    }                                                              //~vamdI~
//*********************************************************        //~vamgI~
//  private void onAnimationEndShowWin()                           //~vamgI~//+vamqR~
    private void onAnimationEndShowWin(Bitmap Pbitmap)             //+vamqI~
    {                                                              //~vamgI~
        if (Dump.Y) Dump.println("Anim.onAnimationEndShowWin");    //~vamgI~
        recycle(Pbitmap);                                          //+vamqI~
    }                                                              //~vamgI~
//*********************************************************          //~vamgI~
    private MyListener getAnimationListener(int PfuncID,Bitmap Pbitmap)//~vamgI~
    {                                                              //~vamgI~
        if (Dump.Y) Dump.println("Anim.getAnimationListener");     //~vamgI~
        MyListener listener=new MyListener(PfuncID,Pbitmap);                //~vamgI~
        return listener;                                         //~vamgI~
    }                                                              //~vamgI~
//*********************************************************          //~vamgI~
    class MyListener                                               //~vamgR~
		implements Animation.AnimationListener                     //~vamgI~
    {                                                              //~vamgI~
    	int funcID;                                                //~vamgI~
    	Bitmap bm;                                                 //~vamgI~
    	public MyListener(int PfuncID,Bitmap Pbitmap)              //~vamgI~
        {                                                          //~vamgI~
        	funcID=PfuncID; bm=Pbitmap;                            //~vamgI~
        }                                                          //~vamgI~
        @Override                                                  //~vamgI~
        public void onAnimationStart(Animation Panimation)         //~vamgI~
        {                                                          //~vamgI~
            if (Dump.Y) Dump.println("Anim.myListener.onAnimationStart");//~vamgI~
        }                                                          //~vamgI~
        @Override                                                  //~vamgI~
        public void onAnimationEnd(Animation Panimation)           //~vamgI~
		{                                                          //~vamgI~
            if (Dump.Y) Dump.println("Anim.myListener.onAnimationEnd");//~vamgI~
            try                                                    //~vamgI~
            {                                                      //~vamgI~
                onAnimationEndCB(funcID,Panimation,bm);            //~vamgR~
            }                                                      //~vamgI~
            catch(Exception e)                                     //~vamgI~
            {                                                      //~vamgI~
                Dump.println(e,"Anim.onAnimationEnd");             //~vamgI~
            }                                                      //~vamgI~
        }                                                          //~vamgI~
        @Override                                                  //~vamgI~
        public void onAnimationRepeat(Animation Panimation)        //~vamgI~
		{                                                          //~vamgI~
            if (Dump.Y) Dump.println("Anim.myListener.onAnimationRepeat");//~vamgI~
        }                                                          //~vamgI~
    }                                                              //~vamgI~
//*********************************************************        //~vamgI~
    class RunParmData                                              //~vamgI~
    {                                                              //~vamgI~
    	int funcID;                                                //~vamgI~
    	Bitmap bm;                                                 //~vamgI~
    	AnimationSet set;                                          //~vamgI~
    	ImageView view;                                            //~vamkI~
//    	public RunParmData(int PfuncID,AnimationSet Pset,Bitmap Pbitmap)//~vamgI~//~vamkR~
      	public RunParmData(int PfuncID,AnimationSet Pset,Bitmap Pbitmap,ImageView Pview)//~vamkI~
        {                                                          //~vamgI~
        	funcID=PfuncID; bm=Pbitmap; set=Pset;                  //~vamgI~
            view=Pview;                                            //~vamkI~
            if (Dump.Y) Dump.println("RunParmData funcid="+funcID+",set="+set+",bitmap="+bm+",view="+Pview);//~vamgI~//~vamkR~
        }                                                          //~vamgI~
    }                                                              //~vamgI~
}                                                                  //~vamdI~
