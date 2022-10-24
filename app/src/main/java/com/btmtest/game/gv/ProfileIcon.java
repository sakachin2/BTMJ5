//*CID://+vatbR~: update#= 863;                                    //+vatbR~
//**********************************************************************
//2022/10/18 vatb show dummy if profile is not set                 //+vatbI~
//2022/10/12 vas4 enlarge profile icon for long device             //~vas4I~
//2022/10/11 vas3 tecLast(Android12) portrait icon before move overrup on stock//~vas3I~
//                (move down by the height of score of nameplate)  //~vas3I~
//2022/10/08 vard Adjust iconsize of before move not to override stock or nameplete for landscape mode//~vardI~
//2022/09/24 var8 display profile icon
//**********************************************************************
//*   Server                               Client                  //~2A02R~
//* at syncdate ok-- 73 -->                                        //~2A02I~
//*              <-- 74 --  client data                            //~2A02I~
//*    if all     -- 75 --> request client image to all client one by one//~2A02R~
//*              <-- 76 --  Client *Image*                         //~2A02R~
//*    if all     -- 77 --> all member data                        //~2A02I~
//*              <-- 78 --  notify missing image of other member   //~2A02I~
//*    if all     -- 79 --> missing other member *Image* to all clinet one by one//~2A02R~
//*              <-- 80 --  image received                         //~2A02I~
//*    if all     -- 81 --> notify image sync complete to all client//~2A02I~
//*                         post event startGame                   //~2A02I~
//**********************************************************************//~2A02I~
package com.btmtest.game.gv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;

import static com.btmtest.BT.enums.MsgIDConst.*;
import static com.btmtest.StaticVars.AG;
import static com.btmtest.dialog.PrefSetting.*;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.gv.ProfileData.*;

import com.btmtest.BT.BTIOThread;
import com.btmtest.BT.BTMulti;
import com.btmtest.BT.Members;
import com.btmtest.R;
import com.btmtest.dialog.PrefSetting;
import com.btmtest.game.Accounts;
import com.btmtest.game.GConst;
import com.btmtest.game.Players;
import com.btmtest.utils.Dump;
import com.btmtest.utils.EventCB;
import com.btmtest.utils.UFile;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProfileIcon
{
    private static final int PROFILE_FRAME_COLOR=Color.argb(0xff,0xff,0x59,0xff);
    private static final int PROFILE_FRAME_COLOR_SCORE=Color.argb(0xff,0xff,0x59,0x00);
    private static final int PROFILE_FRAME_WIDTH=2;
    private static final float  PROFILE_HWRATE=0.75f;	//W:3 vs H:4
    private static final float  PROFILE_EXPAND_BEFOREMOVE=1.0f;//*2.0
    private static final float  PROFILE_EXPAND_BEFOREMOVE_LONGDEVICE=1.0f;//*3.0//~vas4R~
    private static final float  PROFILE_HEIGHT_RATE_NAMEPLATE=2.0F;//~vas4I~
    private static final float  PROFILE_HEIGHT_RATE_NAMEPLATE_LONGDEVICE=4.0F;//~vas4R~
    private static final int    PROFILE_MARGIN=2;
    private static final String ASSET_PROFILE="profile";
    public  static final String ASSET_PROFILE_DUMMY="profile_dummy.jpg";
    private static final String[] ASSET_PROFILE_ROBOTS={"profile_dog.jpg","profile_rabit.jpg","profile_oni.jpg","profile_pirot.jpg"};
    private static final int CTR_ROBOT=4;
    private static final int CTR_WORD=5;                           //~var8I~

    private Rect[] rectProfile,rectProfileBeforeMove;
    private boolean swShowProfile;
    private boolean swShowMe;
    private Bitmap bmpMe,bmpMe0;
//  private Bitmap bmpDummy,bmpDummy0;                             //+vatbR~
    public  Bitmap          bmpDummy0;                             //+vatbI~
    private Bitmap[] bmpRobot0=new Bitmap[CTR_ROBOT];
    private Bitmap[] bmpRobot=new Bitmap[CTR_ROBOT];
    private Bitmap[] bmpCurrent=new Bitmap[PLAYERS];
    private Accounts ACC;                                          //~2A03R~
    private ProfileData PD;                                        //~var8R~
    private BTIOThread clientBTIOT;   //BTIOThread on Client       //~var8R~
    private BTMulti BTM;                                           //~2A02I~
    private Members BTG;                                           //~2A02I~
    private boolean swServer;                                      //~2A02I~
    private boolean swIsSetCurrent,swAfterMove;                    //~2A03R~
    private boolean swMsgExchanging;                               //~2A04I~
    private int maxHeightByStock; //distance from score top to stock edge                                 //~vas3R~//~vas4R~
    private float heightRatePerNamePlate,expandRateBeforeMove;     //~vas4I~
//*************************************************************
//* from Main Activity after Prop and UScoped init
//*************************************************************
	public ProfileIcon()            //for IT Mock
    {
    	if (Dump.Y) Dump.println("ProfileIcon.defaultConstructor swPortrait="+AG.portrait);
        AG.aProfileIcon=this;
        PD=new ProfileData(this);                                  //~var8R~
        swShowProfile= PrefSetting.isShowProfile();
//      swShowProfile=false; //TODO test
    	swShowMe=PrefSetting.isUseMyOwnProfile();
        heightRatePerNamePlate=AG.swLongDevice ? PROFILE_HEIGHT_RATE_NAMEPLATE_LONGDEVICE : PROFILE_HEIGHT_RATE_NAMEPLATE;//~vas4I~
        expandRateBeforeMove=AG.swLongDevice ? PROFILE_EXPAND_BEFOREMOVE_LONGDEVICE :PROFILE_EXPAND_BEFOREMOVE;//~vas4I~
        init();
    }
    //***************************************
    private void init()
    {
        bmpDummy0=loadBmpDummy();
        if (bmpDummy0==null)
        {
        	swShowProfile=false;
        	swShowMe=false;
        }
        else
        {
        	loadBmpRobot();
        	bmpMe0=loadBmpMe();
        }
    	if (Dump.Y) Dump.println("ProfileIcon.init bmpMe0="+bmpMe0);//~var8I~
    }
    //***************************************                      //~2A02I~
    private void initStartgame(Boolean PswServer)                   //~2A02I~//~2A03R~
    {                                                              //~2A02I~
    	if (Dump.Y) Dump.println("ProfileIcon.initStartGame swServer="+PswServer);//~2A02I~
        swServer=PswServer;                                        //~2A02I~//~2A03R~
        if (!swServer)                                             //~2A03I~
	        Accounts.createInstance();                                 //~var8I~//~2A03I~
    	ACC=AG.aAccounts;                                          //~2A03I~
        BTM=AG.aBTMulti;                                           //~2A02I~
        BTG=BTM.BTGroup;                                           //~2A02I~
        int idx=BTG.idxLocal;                                      //~2A02I~
        String yn=BTM.getYourName(idx);                            //~2A02I~
	    saveProfileDataMe(swServer,yn);                           //~2A02I~
    	if (Dump.Y) Dump.println("ProfileIcon.initStartGame ACC="+ACC+",BTM="+BTM);//~2A03I~
    }                                                              //~2A02I~
    //***************************************
    //*from PrefSetting, update bmpMe0                                            //~var8I~//~2A03R~
    //***************************************                      //~var8I~
    public  void propUpdated()
    {
    	if (Dump.Y) Dump.println("ProfileIcon.propupdated bmpDummy0="+bmpDummy0);//~2A06I~
        if (bmpDummy0!=null)
        {
        	swShowProfile= PrefSetting.isShowProfile();
    		swShowMe=PrefSetting.isUseMyOwnProfile();
    		PD.updateTypeMe(swShowMe);                             //~2A05I~
        	Bitmap bm=loadBmpMe();
            if (bm!=null)
            	recycle(bmpMe0);                                   //~2A03R~
            bmpMe0=bm;                                             //~2A06I~
        }
    	if (Dump.Y) Dump.println("ProfileIcon.propupdated bmpMe0="+bmpMe0+",bmpDummy0="+bmpDummy0);//~var8I~//~2A06R~
    }
    //***************************************
    public void endGame(boolean PswReturn)
    {
    	if (Dump.Y) Dump.println("ProfileIcon.endGame PswReturn="+PswReturn);
    	if (!PswReturn)
        	return;
        resetCurrent();                                            //~2A03I~
    }                                                              //~2A03I~
    //***************************************                      //~2A03I~
    private void resetCurrent()                                    //~2A03I~
    {                                                              //~2A03I~
    	if (Dump.Y) Dump.println("ProfileIcon.resetCurrent");     //~2A03I~
        for (int ii=0;ii<PLAYERS;ii++)
        {
        	recycleCurrent(bmpCurrent[ii]);                        //~2A03R~
        	bmpCurrent[ii]=null;                                   //~2A03I~
        }
        swIsSetCurrent=false;                                      //~2A03R~
        swAfterMove=false;                                         //~2A03I~
    }
    //***************************************
    //*from NamePlate init()
    //***************************************
	public void setRect(Rect[] PrectNamePlate)
    {
        maxHeightByStock=0;                                          //~vas3I~
    	if (Dump.Y) Dump.println("ProfileIcon.setRect swShowProfile="+swShowProfile+",rectNamePlate="+Utils.toString(PrectNamePlate));
        if (!swShowProfile)
        	return;
        if (AG.swLongDevice)
        {
        	rectProfile=setRectOnNamePlate(PrectNamePlate);
//          rectProfileBeforeMove=getRectBeforeMoved(rectProfile); //~vas4R~
            rectProfileBeforeMove=getRectBeforeMoved(rectProfile,PrectNamePlate);//~vas4I~
//          rectProfile=rectProfileBeforeMove;                     //~vas3R~
        }
        else
        {
            if (AG.portrait)
            {                                                      //~vas3I~
            	maxHeightByStock=AG.aMJTable.getProfilePortraitLimit();//~vas3R~
                rectProfile=setRectOnNamePlate(PrectNamePlate);
            }                                                      //~vas3I~
            else
                rectProfile=AG.aMJTable.rectProfile;
//          rectProfileBeforeMove=getRectBeforeMoved(rectProfile); //~vas4R~
            rectProfileBeforeMove=getRectBeforeMoved(rectProfile,PrectNamePlate);//~vas4I~
        }
    	if (Dump.Y) Dump.println("ProfileIcon.setRect rectProfile="+Utils.toString(rectProfile));
    	if (Dump.Y) Dump.println("ProfileIcon.setRect rectProfileBeforeMove="+Utils.toString(rectProfileBeforeMove));
    }
    //*****************************************************************************//~vas3R~
    //*set profile rect on NamePlate in Gaming; height=NamePlateHeight*2//~vas3I~
    //*****************************************************************************//~vas3I~
	public Rect[] setRectOnNamePlate(Rect[] Prect)
    {
        Rect rs;
        int margin=PROFILE_MARGIN,xx1,xx2,yy1,yy2,hh,ww;
        Rect[] rects=new Rect[PLAYERS];
    //***************************
    	if (Dump.Y) Dump.println("ProfileIcon.setRectOnNamePlate swLongDevice="+AG.swLongDevice+",rectNamePlate="+Utils.toString(Prect));
//        if (AG.swLongDevice)
//        {
//            if (Dump.Y) Dump.println("ProfileIcon.setRectOnNamePlate longDevice");
////          setRectLongDevice();
//        }
//        else
//        {
        //*on nameplate and justify right
            rs=Prect[PLAYER_YOU];
//          hh=(rs.bottom-rs.top)*2;                               //~vas4R~
            hh=(int)((rs.bottom-rs.top)*heightRatePerNamePlate/*2.0--*/);//~vas4R~
            ww=(int)(hh*PROFILE_HWRATE);    //*0.75;
            if (Dump.Y) Dump.println("ProfileIcon.setRectOnNamePlate portrate hh="+hh+",ww="+ww);
        //*right
            rs=Prect[PLAYER_RIGHT];
            yy1=rs.top;
            yy2=yy1+ww;
            xx2=rs.left-margin;
            xx1=xx2-hh;
            rects[PLAYER_RIGHT]=new Rect(xx1,yy1,xx2,yy2);
        //*facing
            rs=Prect[PLAYER_FACING];
            xx1=rs.left;
            xx2=xx1+ww;
            yy1=rs.bottom+margin;
            yy2=yy1+hh;
            rects[PLAYER_FACING]=new Rect(xx1,yy1,xx2,yy2);
        //*left
            rs=Prect[PLAYER_LEFT];
            yy2=rs.bottom;
            yy1=yy2-ww;
            xx1=rs.right+margin;
            xx2=xx1+hh;
            rects[PLAYER_LEFT]=new Rect(xx1,yy1,xx2,yy2);
        //*you
            rs=Prect[PLAYER_YOU];
            xx2=rs.right;
            xx1=xx2-ww;
            yy2=rs.top-margin;
            yy1=yy2-hh;
            rects[PLAYER_YOU]=new Rect(xx1,yy1,xx2,yy2);
//        }
    	if (Dump.Y) Dump.println("ProfileIcon.setRectOnNamePlate exit rect="+Utils.toString(rects));
        return rects;
    }
    //*************************************************************************//~vas3R~
    //*set Rect for before move seat(no score plate shown)         //~vas3I~
    //*height is double of in gaming                               //~vas3I~
    //*if on NamePlate(portrat or long device) show overriding score plate place//~vas3I~
    //*************************************************************************//~vas3I~
//  public Rect[] getRectBeforeMoved(Rect[] Prect)                 //~vas4R~
    private Rect[] getRectBeforeMoved(Rect[] Prect,Rect[] PrectNamePlate)//~vas4I~
    {
        Rect rs;
        int margin=PROFILE_MARGIN,xx1,xx2,yy1,yy2,hh,ww;
        Rect[] rects=new Rect[PLAYERS];
        int adjustByScore=0;                                       //~vas3I~
    //***************************
    	if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved swLongDevice="+AG.swLongDevice+",Prect="+Utils.toString(Prect));//~vardR~
        //*on nameplate and justify right
            rs=Prect[PLAYER_YOU];
//          hh=(int)((rs.bottom-rs.top)*PROFILE_EXPAND_BEFOREMOVE);//~vas4R~
            hh=(int)((rs.bottom-rs.top)*expandRateBeforeMove); // /(2.0--)*(1.0--)//~vas4R~
    		if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved hh="+hh);//~vas3I~
        	if (AG.swLongDevice || AG.portrait) //rectOnNamePlate  //~vas3R~
//      		adjustByScore=(rs.bottom-rs.top)/2;                //~vas3I~//~vas4R~
        		adjustByScore=(int)((rs.bottom-rs.top)/heightRatePerNamePlate);//~vas4I~
                                                                   //~vas3I~
            int hhAdjustByScore=rs.bottom-rs.top+hh-adjustByScore; //~vas4R~
            if (maxHeightByStock!=0 && hhAdjustByScore>=maxHeightByStock)//~vas3R~//~vas4R~
            	hh=maxHeightByStock-adjustByScore;                 //~vas3R~
                                                                   //~vas3I~
    		if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved new hh="+hh+",hhAdjustByScore="+hhAdjustByScore+",maxHeightByStock="+maxHeightByStock+",adjustByScore="+adjustByScore);//~vas3R~//~vas4R~
    		if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved adjustByScore="+adjustByScore+",additional hh="+hh+",hhYOUProfile="+(rs.bottom-rs.top));//~vardI~//~vas3R~//~vas4R~
        	if (!AG.swLongDevice)                                  //~vardR~
            {                                                      //~vardI~
				int hhDecrease=AG.aMJTable.chkProfileRect(hh+rs.bottom-rs.top);//landscape chk //~vardR~
            	if (hhDecrease>0)                                  //~vardI~
	        		hh-=hhDecrease;                                //~vardI~
	    		if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved decrease="+hhDecrease+",hh="+hh);//~vas3I~
            }                                                      //~vardI~
    		if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved nameplate width right-left="+(rs.right-rs.left)+",old hh="+hh);//~vas4M~
            int wwNamePlate=PrectNamePlate[PLAYER_YOU].right-PrectNamePlate[PLAYER_YOU].left;//~vas4I~
            int hhTotal=rs.bottom-rs.top+hh;                       //~vas4R~
            int ww2=(int)(hhTotal*PROFILE_HWRATE);  //width from height of you//~vas4I~
    		if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved org nameplate width="+wwNamePlate+",height="+hh+",hhTotal="+hhTotal+",ww2 by hhTotal="+ww2);//~vas4R~
            if (ww2>wwNamePlate)                                   //~vas4I~
            {                                                      //~vas4M~
            	int hhTotal2=(int)(wwNamePlate/PROFILE_HWRATE);    //~vas4R~
                hh-=hhTotal-hhTotal2;                              //~vas4R~
    			if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved reduce by nameplate hhTotal new="+hhTotal2+",old="+hhTotal+",new hh="+hh);//~vas4R~
                hhTotal=hhTotal2;                                  //~vas4I~
            }                                                      //~vas4M~
//          ww=(int)(hh*PROFILE_HWRATE);                           //~vas4R~
            ww=(int)(hhTotal*PROFILE_HWRATE)-(rs.right-rs.left);   //~vas4R~
    		if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved reduce by nameplate ww="+ww+",hhTotal="+hhTotal+",new hh="+hh);//~vas4I~
        //*right
            rs=Prect[PLAYER_RIGHT];
    		if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved hhRight="+(rs.right-rs.left)+",rect="+rs);//~vardR~
            xx1=rs.left-hh;      //doubled height
            yy2=rs.bottom+ww;
            xx2=rs.right;
            yy1=rs.top;
            xx1+=adjustByScore;  //shift down to nameplate                                  //~vas3R~
            xx2+=adjustByScore;                                    //~vas3R~
            rects[PLAYER_RIGHT]=new Rect(xx1,yy1,xx2,yy2);
        //*facing
            rs=Prect[PLAYER_FACING];
    		if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved hhFacing="+(rs.bottom-rs.top));//~vardI~
            xx2=rs.right+ww;
            yy2=rs.bottom+hh;
            xx1=rs.left;
            yy1=rs.top;
            yy1-=adjustByScore;                                    //~vas3I~
            yy2-=adjustByScore;                                    //~vas3I~
            rects[PLAYER_FACING]=new Rect(xx1,yy1,xx2,yy2);
        //*left
            rs=Prect[PLAYER_LEFT];
    		if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved hhLeft="+(rs.right-rs.left));//~vardI~
            xx2=rs.right+hh;
            yy1=rs.top-ww;
            xx1=rs.left;
            yy2=rs.bottom;
            xx1-=adjustByScore;                                    //~vas3I~
            xx2-=adjustByScore;                                    //~vas3I~
            rects[PLAYER_LEFT]=new Rect(xx1,yy1,xx2,yy2);
        //*you
            rs=Prect[PLAYER_YOU];
            xx1=rs.left-ww;
            yy1=rs.top-hh;
            xx2=rs.right;
            yy2=rs.bottom;
            yy1+=adjustByScore;                                    //~vas3I~
            yy2+=adjustByScore;                                    //~vas3I~
            rects[PLAYER_YOU]=new Rect(xx1,yy1,xx2,yy2);

    	if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved exit rect="+Utils.toString(rects));
        return rects;
    }
    //***************************************
    //*from NamePlate.showPlate
    //***************************************
	public void showOnNamePlate()                                  //~2A03R~
    {
    	Rect[] rects;                                              //~2A03I~
    	if (Dump.Y) Dump.println("ProfileIcon.showOnNamePlate swIsSetCurrent="+swIsSetCurrent+",swAfterMove="+swAfterMove+",swShowProfile="+swShowProfile);//~2A03R~
        if (!swShowProfile)
        	return;
        if (swAfterMove)                                           //~2A03I~
        	rects=rectProfile;                                     //~2A03I~
        else                                                       //~2A03I~
            rects=rectProfileBeforeMove;                           //~2A03I~
        if (!swIsSetCurrent)                                       //~2A03R~
        {                                                          //~2A03I~
            setCurrentBmp(swAfterMove);                            //~2A03R~
            adjustIconSizeCurrent(rects);                          //~2A03I~
            rotateCurrentIcon();    //NamePlate                    //~2A03R~
	        swIsSetCurrent=true;                                   //~2A03I~
        }                                                          //~2A03I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~2A03I~
        {                                                          //~2A03I~
            drawProfile(rects[ii],bmpCurrent[ii]);                     //~2A03R~
        }                                                          //~2A03I~
    }
//    //*****************************************************************//~2A03R~
//    //* not used                                                 //~2A03R~
//    //*****************************************************************//~2A03R~
//    private void adjustIconSize(Rect[] Prects)                   //~2A03R~
//    {                                                            //~2A03R~
//        if (Dump.Y) Dump.println("ProfileIcon.adjustIconSize rect="+Utils.toString(Prects));//~2A03R~
//        int ww=Prects[0].right-Prects[0].left;                   //~2A03R~
//        int hh=Prects[0].bottom-Prects[0].top;                   //~2A03R~
//        recycle(bmpDummy);                                       //~2A03R~
//        bmpDummy=Bitmap.createScaledBitmap(bmpDummy0,ww,hh,true/*amtialias*/);//~2A03R~
//        if (Dump.Y) Dump.println("ProfileIcon.adjustIconSize dummy ww="+ww+",hh="+hh+",bmpMe="+toString(bmpMe)+",bmpMe0="+toString(bmpMe0));//~2A02I~//~2A03R~
//        if (bmpMe0!=null)                                        //~2A03R~
//        {                                                        //~2A03R~
//            recycle(bmpMe);                                      //~2A03R~
//            bmpMe=Bitmap.createScaledBitmap(bmpMe0,ww,hh,true/*amtialias*/);//~2A03R~
//        }                                                        //~2A03R~
//        else                                                     //~2A03R~
//            bmpMe=Bitmap.createBitmap(bmpDummy);                 //~2A03R~
//        if (Dump.Y) Dump.println("ProfileIcon.adjustIconSize bmpMe="+toString(bmpMe)+",bmpMe0="+toString(bmpMe0));//~2A02I~//~2A03R~
//        for (int ii=0;ii<CTR_ROBOT;ii++)                         //~2A03R~
//        {                                                        //~2A03R~
//            if (bmpRobot0[ii]!=null)                             //~2A03R~
//            {                                                    //~2A03R~
//                recycle(bmpRobot[ii]);                           //~2A03R~
//                bmpRobot[ii]=Bitmap.createScaledBitmap(bmpRobot0[ii],ww,hh,true/*amtialias*/);//~2A03R~
//                if (Dump.Y) Dump.println("ProfileIcon.adjustIconSize robot ii="+ii+",ww="+ww+",hh="+hh+",scaled="+toString(bmpRobot[ii])+",old="+toString(bmpRobot0[ii]));//~2A03R~
//            }                                                    //~2A03R~
//        }                                                        //~2A03R~
//    }                                                            //~2A03R~
    //*****************************************************************//~2A03I~
	private void adjustIconSizeCurrent(Rect[] Prects)              //~2A03I~
    {                                                              //~2A03I~
    	if (Dump.Y) Dump.println("ProfileIcon.adjustIconSizeCurrent rect="+Utils.toString(Prects));//~2A03I~
    	int ww=Prects[0].right-Prects[0].left;                     //~2A03I~
    	int hh=Prects[0].bottom-Prects[0].top;                     //~2A03I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~2A03I~
        {                                                          //~2A03I~
	        Bitmap old=bmpCurrent[ii];                             //~2A03R~
            bmpCurrent[ii]=Bitmap.createScaledBitmap(old,ww,hh,true/*amtialias*/);//~2A03R~
	    	if (Dump.Y) Dump.println("ProfileIcon.adjustIconSizeCurrent old="+toString(old)+"new="+toString(bmpCurrent[ii]));//~2A03I~
	        recycleCurrent(old);                                   //~2A03I~
        }                                                          //~2A03I~
    	if (Dump.Y) Dump.println("ProfileIcon.adjustIconSizeCurrent exit bmps="+Utils.toString(bmpCurrent));//~2A03R~
    }                                                              //~2A03I~
    //*****************************************************************//~var8I~
    //*reduce bitmap of bmpMe0 size for perormace of exchange thrue network  //~var8I~//~2A03R~
    //*****************************************************************//~var8I~
	private Bitmap adjustIconSizeToExchange(Bitmap Pbmp)             //~var8I~
    {                                                              //~var8I~
        int wwProfile=(int)(AG.resource.getDimension(ID_PROFILE_WIDTH));//~var8I~
        int hhProfile=(int)(AG.resource.getDimension(ID_PROFILE_HEIGHT));//~var8I~
        int ww=(int)(wwProfile*(1.0+PROFILE_EXPAND_BEFOREMOVE));   //~var8R~
        int hh=(int)(hhProfile*(1.0+PROFILE_EXPAND_BEFOREMOVE));   //~var8R~
    	int wwBitmap=Pbmp.getWidth();                              //~var8I~
    	int hhBitmap=Pbmp.getHeight();                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.adjustIconSizeToExchange Pbmp="+toString(Pbmp));//~2A03R~
        Bitmap bm=Pbmp;                                            //~var8I~
        if (wwBitmap>ww || hhBitmap>hh)                            //~var8I~
        {                                                          //~var8I~
	        Bitmap bmScaled=Bitmap.createScaledBitmap(Pbmp,ww,hh,true/*amtialias*/);//~var8I~
        	if (bmScaled!=null)                                    //~var8I~
            {                                                      //~var8I~
		    	if (Dump.Y) Dump.println("ProfileIcon.adjustIconSizeToExchange scaled="+toString(bmScaled));//~var8I~//~2A03R~
        		recycle(Pbmp);                                     //~var8I~
                bm=bmScaled;                                       //~var8I~
            }                                                      //~var8I~
        }                                                          //~var8I~
        return bm;                                                 //~var8I~
    }                                                              //~var8I~
    //*****************************************************************
	private void rotateCurrentIcon()
    {
    	if (Dump.Y) Dump.println("ProfileIcon.rotateCurrentIcon entry bmps="+Utils.toString(bmpCurrent));//~2A02I~
        for (int ii=1;ii<PLAYERS;ii++)
        {
            Bitmap bmRotated=rotateBMP(bmpCurrent[ii],ii);                   //~2A03I~
            recycleCurrent(bmpCurrent[ii]);                        //~2A03R~
            bmpCurrent[ii]=bmRotated;                              //~2A03I~
        }
    	if (Dump.Y) Dump.println("ProfileIcon.rotateCurrentIcon exit bmps="+Utils.toString(bmpCurrent));//~2A02I~
    }
    //*****************************************************************
	private Bitmap rotateBMP(Bitmap Pbmp,int Pplayer)
    {
    	if (Dump.Y) Dump.println("ProfileIcon.rotateBMP player="+Pplayer+",bmp="+toString(Pbmp));//~2A03R~
        int degree=0;
        switch(Pplayer)
        {
        case 1:	//right
        	degree=270;
            break;
        case 2: //facing
        	degree=180;
            break;
        case 3: //facing
        	degree=90;
            break;
        }
        Matrix matrix=new Matrix();
        int ww=Pbmp.getWidth();
        int hh=Pbmp.getHeight();
        matrix.postRotate(degree);
        Bitmap bmRotated=Graphics.createBitmap(Pbmp,0,0,ww,hh,matrix,true);
        if (Dump.Y) Dump.println("ProfileIcon.rotateBMP degree="+degree+",ww="+ww+",hh="+hh);
        if (Dump.Y) Dump.println("ProfileIcon.rotateBMP Pbmp="+toString(Pbmp)+",rotated="+toString(bmRotated));//~2A03R~
        return bmRotated;
    }
    //*****************************************************************
    //*from NamePlate.showPlate(newPosition) clear rect before move
    //****************************************************************
	public void beforeShowNewPosition()
    {
    	if (Dump.Y) Dump.println("ProfileIcon.beforeShowNewPosition");
        if (!swShowProfile)
        	return;
        for (int ii=0;ii<PLAYERS;ii++)
		    Graphics.drawRect(rectProfileBeforeMove[ii],COLOR_BG_TABLE);
        resetCurrent();//reset bmp before move                     //~2A03I~
        AG.aGCanvas.stock.drawInitial();                           //~vas3I~
        swIsSetCurrent=false;                                      //~2A03I~
        swAfterMove=true;                                          //~2A03I~
    }
//    //***************************************                    //~vardR~
//    private void drawFrame(Rect[] Prect,boolean PswAfterMoved)   //~vardR~
//    {                                                            //~vardR~
//        if (Dump.Y) Dump.println("ProfileIcon.drawFrame rect="+ Utils.toString(Prect));//~vardR~

//        int color=PROFILE_FRAME_COLOR;                           //~vardR~
//        if (PswAfterMoved)                                       //~vardR~
//            color=PROFILE_FRAME_COLOR_SCORE;                     //~vardR~
//        int width=PROFILE_FRAME_WIDTH;                           //~vardR~
//        for (int ii=0;ii<PLAYERS;ii++)                           //~vardR~
//        {                                                        //~vardR~
//            Rect r=Prect[ii];                                    //~vardR~
//            Graphics.drawRect(r,color,width);                    //~vardR~
//        }                                                        //~vardR~
//    }                                                            //~vardR~
    //***************************************
	private void drawProfile(Rect Prect,Bitmap Pbm)
    {
    	if (Dump.Y) Dump.println("ProfileIcon.drawProfile rect="+ Utils.toString(Prect)+",bm="+Pbm);//~2A02R~
        Graphics.drawBitmap(Pbm,Prect.left,Prect.top);
        int color=PROFILE_FRAME_COLOR;
        int width=PROFILE_FRAME_WIDTH;
		Graphics.drawRect(Prect,color,width);
    }
    //***************************************
	private static Bitmap loadBmpDummy()
    {
    	if (Dump.Y) Dump.println("ProfileIcon.loadBmpDummy");
        String path=ASSET_PROFILE+"/"+ASSET_PROFILE_DUMMY;
		Bitmap bm=loadBmpAsset(path);
        return bm;
    }
    //***************************************
	private void loadBmpRobot()
    {
        if (Dump.Y) Dump.println("ProfileIcon.loadBmpRobot");
        for (int ii = 0; ii < ASSET_PROFILE_ROBOTS.length; ii++)    //ii=0:Me
        {
            String fnm = ASSET_PROFILE_ROBOTS[ii];
            String path = ASSET_PROFILE + "/" + fnm;
			Bitmap bm=loadBmpAsset(path);
            if (Dump.Y) Dump.println("ProfileIcon.loadBmpRobot ii="+ii+",bm="+toString(bm));//~var8R~//~2A03R~
            bmpRobot0[ii] = bm;
        }
    }
    //***************************************
	private static Bitmap loadBmpAsset(String Ppath)
    {
    	if (Dump.Y) Dump.println("ProfileIcon.loadBmpAsset path="+Ppath);
        Bitmap bm=null;
        try
        {
            InputStream is= UFile.openAssetFile(Ppath,false/*showException*/);
            if (is!=null)
            {
                BufferedInputStream bis=new BufferedInputStream(is);
//              bm= BitmapFactory.decodeStream(bis);               //~2A02R~
				BitmapFactory.Options opt=new BitmapFactory.Options();//~2A02I~
                opt.inPreferredConfig=Bitmap.Config.ARGB_8888;      //~2A02I~
                bm= BitmapFactory.decodeStream(bis,null/*outPadding*/,opt);//~2A02R~
                if (Dump.Y) Dump.println("ProfileIcon.loadBmpDummy bitmap "+toString(bm));//~var8R~//~2A03R~
            	if (Dump.Y) Dump.println("ProfileIcon.loadAsset config="+bm.getConfig());//~2A02I~
                bis.close();
            }
        }
        catch(IOException e)
        {
        	Dump.println(e,"ProfileIconloadBmpAsset IOException:"+Ppath);
        }
        return bm;
    }
    //***************************************
	private  Bitmap loadBmpMe()
    {
    	if (Dump.Y) Dump.println("ProfileIcon.loadBmpMe");
        Bitmap bm=null;
        String strUri=PrefSetting.getProfileMeStrUri();            //~var8R~
        String id=PrefSetting.getProfileMeID();                    //~var8I~
        if (strUri!=null && !strUri.equals(""))                    //~2A06R~
        {
            Uri uri=Uri.parse(strUri);                                 //~var8I~
            bm=loadBmpFile(uri,id);                                //~var8R~
            if (bm!=null)                                          //~var8I~
	            bm=adjustIconSizeToExchange(bm);                      //~var8I~
        }
        return bm;
    }
//    //***************************************                    //~2A02R~
//    //*==>use uri and id                                         //~2A02I~
//    //***************************************                    //~2A02I~
//    public static Bitmap getBMP(String Pname)                    //~2A02R~
//    {                                                            //~2A02R~
//        if (Dump.Y) Dump.println("ProfileIcon.getBMP fnm="+Pname);//~2A02R~
//        Bitmap bm;                                               //~2A02R~
//        if (Pname==null)                                         //~2A02R~
//            bm=loadBmpDummy();                                   //~2A02R~
//        else                                                     //~2A02R~
//            bm=loadBmpFile(Pname);                               //~2A02R~
//        if (Dump.Y) Dump.println("ProfileIcon.getBmpOther bitmap "+toString(bm));//~var8R~//~2A02R~//~2A03R~
//        return bm;                                               //~2A02R~
//    }                                                            //~2A02R~
    //***************************************                      //~var8I~
	public static Bitmap getBMP_StrUri(String PstrUri,String Pid)  //~var8R~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.getBMP_StrUri id="+Pid+",strUri="+PstrUri);//~var8R~
        if (PstrUri==null)                                            //~var8I~
        	return null;                                           //~var8I~
		Uri	uri=Uri.parse(PstrUri);                                //~var8I~
        Bitmap bm=getBMP(uri,Pid);                                 //~var8R~
	    if (Dump.Y) Dump.println("ProfileIcon.getBmp_StrUri bitmap "+toString(bm));//~var8I~//~2A03R~
        return bm;                                                 //~var8I~
    }                                                              //~var8I~
    //***************************************
	public static Bitmap getBMP(Uri Puri,String Pid)               //~var8R~
    {
    	if (Dump.Y) Dump.println("ProfileIcon.getBMP id="+Pid+",uri="+Puri);//~var8R~
        Bitmap bm;
        if (Puri==null)
        	bm=loadBmpDummy();
        else
        	bm=loadBmpFile(Puri,Pid);                              //~var8R~
	    if (Dump.Y) Dump.println("ProfileIcon.getBMP bitmap "+toString(bm));//~var8R~//~2A03R~
        return bm;
    }
    //***************************************
	private static Bitmap loadBmpFile(String Pname)
    {
    	if (Dump.Y) Dump.println("ProfileIcon.loadBmpFile by fileName name="+Pname);//~var8R~
        Bitmap bm=AG.aUMediaStore.loadBMP(Pname);
        return bm;
    }
    //***************************************
	private static Bitmap loadBmpFile(Uri Puri,String Pid)         //~var8R~
    {
    	if (Dump.Y) Dump.println("ProfileIcon.loadBmpFile by Uri id="+Pid+",uri="+Puri);//~var8R~
        Bitmap bm=AG.aUMediaStore.loadBMP(Puri,Pid);               //~var8R~
        return bm;
    }
    //***************************************
	public  Bitmap getBmpMe()
    {
	    if (Dump.Y) Dump.println("ProfileIcon.getBmpMe");
        Bitmap bm=bmpMe;
	    if (Dump.Y) Dump.println("ProfileIcon.getBmpMe bitmap "+toString(bm));//~var8R~//~2A03R~
        return bm;
    }
    //***************************************
    //*return bmpRobot0                                            //~2A03I~
    //***************************************                      //~2A03I~
	public Bitmap getBmpRobot(String Pname)
    {
        String[] nameRobots= GConst.robotYourNameDefaultConst;
    	if (Dump.Y) Dump.println("ProfileIcon.getBmpRobot name="+Pname+",robots="+Utils.toString(nameRobots));
//      Bitmap bm=bmpDummy;                                        //+vatbR~
        Bitmap bm=bmpDummy0;                                       //+vatbI~
        for (int ii=1;ii<nameRobots.length;ii++)	//ii=0:Me
        {
            if (Pname.equals(nameRobots[ii]))
            {
            	bm=bmpRobot0[ii-1];                                //~2A03R~
                break;
            }
        }
	    if (Dump.Y) Dump.println("ProfileIcon.getBmpRobot bitmap "+toString(bm));//~var8R~//~2A03R~
        return bm;
    }
//    //***************************************                    //+vatbR~
//    public Bitmap getBmpOther(String Pname)                      //+vatbR~
//    {                                                            //+vatbR~
//        if (Dump.Y) Dump.println("ProfileIcon.getBmpOther");     //+vatbR~
//        Bitmap bm=bmpDummy;                                      //+vatbR~
//        if (Dump.Y) Dump.println("ProfileIcon.getBmpOther bitmap "+toString(bm));//~var8R~//~2A03R~//+vatbR~
//        return bm;                                               //+vatbR~
//    }                                                            //+vatbR~
//    //***************************************                    //~var8R~
//    public String makeMsgProfile(int Pcase)                      //~var8R~
//    {                                                            //~var8R~
//        String parm="";                                          //~var8R~
//        switch(Pcase)                                            //~var8R~
//        {                                                        //~var8R~
//        case 0:     //query                                      //~var8R~
//            parm=swShowMe?"1":"0";                               //~var8R~
//            break;                                               //~var8R~
//        case 1:     //resp                                       //~var8R~
//            parm=swShowMe?"1":"0";                               //~var8R~
//            break;                                               //~var8R~
//        }                                                        //~var8R~
//        if (Dump.Y) Dump.println("ProfileIcon.makeMsgProfile case="+Pcase+",rc="+parm);//~var8R~
//        return parm;                                             //~var8R~
//    }                                                            //~var8R~
    //********************************************************     //~var8I~
    //*from BTMulti.sendStartGame                                  //~var8I~
    //*on Server, at before sendToAllclient NOTIFY_SYNCOK          //~var8I~
    //********************************************************     //~var8I~
    public void startSyncProfileServer(String PdevName)            //~var8I~
    {                                                              //~var8I~
    	initStartgame(true/*swServer*/);                           //~2A02I~
//  	String yn=getYourName(PdevName);                           //~var8I~//~2A02R~
//  	if (Dump.Y) Dump.println("ProfileIcon.startSyncProfileServer dev="+PdevName+",yourname="+yn);//~var8I~//~2A02R~
//      saveProfileDataMe(true/*swSerrver*/,yn);                   //~var8I~//~2A02R~
    }                                                              //~var8I~
    //***************************************                      //~var8I~
    //*from BTIOThread.receivedSyncOK (73)                         //~var8R~//~2A06R~
    //*on BTIOThread of Client at SYNCOK,notify client profileInfo //~var8R~
    //*send 74 to Server                                           //~2A05I~
    //***************************************                      //~var8I~
    public void startSyncProfile(BTIOThread Pbtiot,String[] Pdata) //~var8I~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.startSyncProfile Pbtiot="+Pbtiot+",data="+Utils.toString(Pdata));//~var8I~
    	initStartgame(false/*swServer*/);                          //~2A02I~
		clientBTIOT=Pbtiot;
//      String yn=getYourName(Pbtiot.localDeviceName);             //~var8R~//~2A02R~
//      saveProfileDataMe(false/*swServer*/,yn);                    //~var8I~//~2A02R~
//      String msg=getProfileDataMe(yn,swShowMe);                  //~var8R~//~2A02R~
        String msg=getProfileDataMe(swShowMe);                     //~2A02I~
        Pbtiot.sendMsgProfile(GCM_PROFILE_STARTSYNC,msg);//74      //~var8R~//~2A05R~
    }                                                              //~var8I~
    //*********************************************************    //~var8I~//~2A06R~
    //*on Server, at received STARTSYNC:74 ; if all, send 75       //~var8R~//~2A06R~
    //*********************************************************    //~var8I~//~2A06R~
    public void startSyncProfileReceived(int PidxSender,String Pdata)//~var8R~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.startSyncProfileReceived idxSender="+PidxSender+",data="+Pdata);//~var8R~
        saveProfileDataOtherOnServer(PidxSender,Pdata);            //~var8R~
        if (isReceivedAllClientProfile())                          //~var8I~
        {                                                          //~2A04I~
            if (!requestImageOfAllClient())	//send 75 ?. no need to get client image                             //~var8R~//~2A04R~//~2A06R~
	        {                                                      //~2A04I~
    	    	notifyReceivedImageAllClient(); //send GCM_PROFILE_NOTIFY_ALL//~2A04I~
        	}                                                      //~2A04I~
        }                                                          //~2A04I~
    }                                                              //~var8I~
    //****************************************************************//~var8I~
    //*on BTIOThread of Client  received GCM_PROFILE_GETIMAGE_C2S:75//~var8I~
    //****************************************************************//~var8I~
    public void receivedRequestImageC2S(int PidxSender,String[] Pdata)//~var8M~
    {                                                              //~var8M~
    	if (Dump.Y) Dump.println("ProfileIcon.receivedRequestImageC2S idxSender="+PidxSender+",data="+Utils.toString(Pdata));//~var8M~
        sendBmpMe(clientBTIOT,GCM_PROFILE_GETIMAGE_C2SR);          //~var8R~
	    msgImageExchanging(false/*PswS2C*/);                       //~2A04I~
    }                                                              //~var8M~
    //****************************************************************//~var8I~
    //*from GVH thread of Server          received 76 (C2S:Client Image)                      //~var8R~//~2A03R~//~2A06R~
    //****************************************************************//~var8I~
    public void receivedRequestImageC2SR(int PidxSender,String Pdata1/*size*/,String Pdata2/*pi data*/,byte[] Pbyte/*bitmap compressed*/)//~var8R~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.receivedRequestImageC2SR idxSender="+PidxSender+",data1="+Pdata1+",data2="+Pdata2+",byte="+Pbyte+",bytelen="+(Pbyte==null?"0":Pbyte.length));//~var8R~
	    saveProfileImageOtherOnServer(PidxSender,Pbyte);           //~var8I~
	    boolean rc=requestImageOfAllClient();                      //~var8I~
        if (!rc)	//exausted, sent to all to get client profile bitmap//~var8R~
        {                                                          //~var8I~
        	notifyReceivedImageAllClient(); //send 77:GCM_PROFILE_NOTIFY_ALL//~var8I~//~2A06R~
        }                                                          //~var8I~
    }                                                              //~var8I~
    //****************************************************************//~var8I~
    //*on GVH og Server , send 77 to all client                    //~var8I~
    //****************************************************************//~var8I~
    public void notifyReceivedImageAllClient()                     //~var8I~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.notifyRecivedImageAllClient");//~var8I~
        String msg=PD.makeMsgAllProfile();                         //~var8R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~var8I~
        {                                                          //~var8I~
        	if (ii!=PD.idxServer)                                  //~var8R~
            {                                                      //~var8I~
	        	ProfileData.ProfileInfo pi=PD.getMember(ii);                   //~2A05R~
	        	if (pi!=null)                                      //~2A05I~
                {                                                  //~2A05I~
                	PD.setNotifySent(ii,pi);   //set PDT_NOTIFIED                       //~2A05I~//~2A06R~
        			sendMsgToTheClient(ii,GCM_PROFILE_NOTIFY_ALL,msg);//~var8I~
                }                                                  //~2A05I~
            }                                                      //~var8I~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.notifyRecivedImageAllClient exit");//~var8I~
    }                                                              //~var8I~
    //****************************************************************//~var8I~
    //*from BIOT                                                   //~var8I~
    //*on BTIOT of Client , received 77, send 78                   //~var8I~//~2A02R~
    //****************************************************************//~var8I~
    public void receivedProfileNotifyAll(int PidxSender,String[] Pdata)//~var8I~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.receivedProfileNotifyAll sender="+PidxSender+",words="+Utils.toString(Pdata));//~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.receivedProfileNotifyAll idxMe="+PD.idxMe+",idxServer="+PD.idxServer);//~var8R~
        String[] datas=parseMulti(Pdata[2]);                       //~var8I~
        for (int ii=0;ii<datas.length;ii++)                        //~var8I~
        {                                                          //~var8I~
    		saveProfileDataOtherOnClient(datas[ii]);           //~var8R~//~2A02R~
        }                                                          //~var8I~
        String msg=PD.makeMsgClientStatus();                       //~2A02R~
    	sendMsgToServer(GCM_PROFILE_NOTIFY_ALL_RESP,msg);  //78          //~var8I~//~2A02R~//~2A05R~
    	if (Dump.Y) Dump.println("ProfileIcon.receivedProfileNotifyAll exit");//~var8I~
    }                                                              //~var8I~
    //********************************************************************       //~var8I~//~2A06I~
    //*on Server  received GCM_PROFILE_NOTIFY_ALL_RESP:78; if all send 79         //~var8R~//~2A06I~
    //********************************************************************       //~var8I~//~2A06I~
    public void receivedProfileNotifyAllResp(int PidxSender,String Pdata)//~var8R~//~2A06M~
    {                                                              //~var8I~//~2A06M~
    	if (Dump.Y) Dump.println("ProfileIcon.receivedProfileNotifyAllResp idxSender="+PidxSender+",data="+Pdata);//~var8R~//~2A06M~
		String[] strPIs=parseMulti(Pdata);                         //~2A02I~//~2A06M~
        for (int ii=0;ii<strPIs.length;ii++)                       //~2A02I~//~2A06M~
        {                                                          //~var8I~//~2A06M~
			String[] words=parse(strPIs[ii]);                      //~2A02I~//~2A06M~
        	if (words.length>=CTR_WORD)                            //~2A02I~//~2A06M~
        	{                                                      //~2A02I~//~2A06M~
		        PD.updateOtherSaved(PidxSender,words[0]/*saved*/,words[1]/*yourname*/);//~2A02R~//~2A06M~
            }                                                      //~2A02I~//~2A06M~
        }                                                          //~var8I~//~2A06M~
    	if (PD.isNotifyRespReceivedAll())                          //~2A02I~//~2A06M~
        	sendBmpToAllClient();                                  //~2A02I~//~2A06M~
    }                                                              //~var8I~//~2A06M~
    //***************************************                      //~var8I~
    private boolean isReceivedAllClientProfile()                   //~var8I~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.isReceivedALlClientProfile");//~var8I~
        boolean rc=PD.isReceivedAllClientProfile();                //~var8R~
    	if (Dump.Y) Dump.println("ProfileIcon.isReceivedALlClientProfile rc="+rc);//~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //******************************************************                      //~var8I~//~2A06R~
    //*On Server send 75 to all client one by one                  //~var8I~
    //******************************************************                      //~var8I~//~2A06R~
    private boolean requestImageOfAllClient()                      //~var8R~
    {                                                              //~var8I~
    	boolean rc=false;                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.requestImageOfAllClient");//~var8R~
	    int idx=PD.selectClientToRequestImage();                   //~var8R~
        if (idx>=0)                                                //~var8I~
        {                                                          //~var8I~
	        String msg=PD.makeMsgRequestImageToClient(idx);        //~var8R~
        	sendMsgToTheClient(idx,GCM_PROFILE_GETIMAGE_C2S,msg);//~var8R~
            rc=true;                                               //~var8I~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.requestImageOfAllClient rc="+rc);//~var8I~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //***************************************                      //~var8I~
    private void sendMsgToAllClient(int PmsgID,String Pmsg)        //~var8R~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.sendMsgToAllClient msgid="+PmsgID+",msg="+Pmsg);//~var8R~
        BTM.sendMsgToAllClient(true/*swApp*/,PmsgID,Pmsg); //~var8R~//~2A02R~
    }                                                              //~var8I~
    //***************************************                      //~var8I~
    private void sendMsgToTheClient(int Pidx,int PmsgID,String Pmsg)//~var8I~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.sendMsgToTheClient idx="+Pidx+",msgid="+PmsgID+",msg="+Pmsg);//~var8I~
        BTIOThread.sendMsg(Pidx,true/*swApp*/,PmsgID,Pmsg);             //~var8I~
    }                                                              //~var8I~
    //***************************************                      //~var8I~
    private void sendMsgToServer(int PmsgID,String Pmsg)           //~var8I~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.sendMsgToServer idxServer="+PD.idxServer+",msgid="+PmsgID+",msg="+Pmsg);//~var8R~
        clientBTIOT.sendMsg(PD.idxServer,true/*swApp*/,PmsgID,Pmsg);//~var8R~
    }                                                              //~var8I~
    //***************************************                      //~var8I~
    private void sendImageToServer(byte[] Pbyte)        //~var8I~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.sendImageToServer byte="+Pbyte);//~var8I~
        clientBTIOT.sendByte(Pbyte);      //~var8I~
    }                                                              //~var8I~
    //***************************************                      //~2A02I~
    private void sendImageToTheClient(int PidxRemote,byte[] Pbyte) //~2A02I~
    {                                                              //~2A02I~
    	if (Dump.Y) Dump.println("ProfileIcon.sendImageToTheClient int idxRemote="+PidxRemote+"+byteLen="+Pbyte.length);//~2A02I~
        BTIOThread.sendByte(PidxRemote,Pbyte);                          //~2A02I~
    }                                                              //~2A02I~
    //***************************************                      //~var8I~
//  private String getProfileDataMe(String PyourName,boolean PswShowMe)//~var8R~//~2A02R~
    private String getProfileDataMe(boolean PswShowMe)             //~2A02I~
    {                                                              //~var8I~
//      String rc=PD.getMe(PyourName,PswShowMe);                   //~var8R~//~2A02R~
        String rc=PD.getMe(PswShowMe);                             //~2A02I~
    	if (Dump.Y) Dump.println("ProfileIcon.getProfileDataMe PswShowMe="+PswShowMe+",rc="+rc);//~var8R~//~2A02R~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
    //***************************************                      //~var8I~
    private void saveProfileDataMe(boolean PswServer,String PyourName)//~var8R~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.saveProfileDataMe swSerer="+PswServer+",yn="+PyourName);//~2A05I~
        String displayName=PrefSetting.getProfileMeDispName();     //~var8I~
        String ts=PrefSetting.getProfileMeTimestamp();             //~var8I~
        if (ts==null)                                              //~2A05I~
        	ts="0";                                                //~2A05I~
        String size=PrefSetting.getProfileMeSize();                //~var8I~
        if (size==null)                                            //~2A05I~
        	size="0";                                              //~2A05I~
        if (bmpMe0==null)                                          //~2A05I~
	        PD.putMe(PswServer,BTM.localDeviceName,PyourName,0/*type*/,displayName,ts,size,null);//~2A05I~
        else                                                       //~2A05I~
        {                                                          //~2A05I~
	        byte[] buff=PD.compressBmpMe(bmpMe0);                  //~2A05I~
	        PD.putMe(PswServer,BTM.localDeviceName,PyourName,PDT_AVAIL,displayName,ts,size,buff);//~var8R~//~2A02R~//~2A03R~//~2A05R~
        }                                                          //~2A05I~
    	if (Dump.Y) Dump.println("ProfileIcon.saveProfileDataMe bmpMe0="+bmpMe0+",displayName="+displayName+",timestamp="+ts+",size="+size);//~var8R~
    }                                                              //~var8I~
    //***************************************                      //~var8I~
    //*on Server                                                   //~var8I~
    //***************************************                      //~var8I~
    private void saveProfileDataOtherOnServer(int PidxSender,String Pdata)//~var8R~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.saveProfileDataOtherOnServer idxSender="+PidxSender+",data="+Pdata);//~var8R~
		String[] words=parse(Pdata);	//yourname,type,displayname,timestamp,size//~var8R~
        String rc;
        int bmType=Utils.parseInt(words[1],0);                     //~var8R~
        if (words.length>=CTR_WORD)                                 //~var8I~
			PD.putOther(PidxSender,words[0]/*yn*/,bmType/*bmType*/,words[2]/*displayName*/,words[3]/*ts*/,words[4]/*size*/);//~var8R~
    }                                                              //~var8I~
    //***************************************                      //~var8I~
    //*on Server received 76                                       //~var8I~//~2A03R~
    //***************************************                      //~var8I~
    private void saveProfileImageOtherOnServer(int PidxSender,byte[] Pcompressed)//~var8I~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.saveProfileImageOtherOnServer idxSender="+PidxSender+",compressedImage="+Pcompressed);//~var8I~//~2A06R~
		PD.updateImageOther(PidxSender,Pcompressed);               //~var8R~
    }                                                              //~var8I~
    //************************************************************ //~var8R~
    //*on Client                                                   //~var8I~
    //************************************************************ //~var8I~
    private void saveProfileDataOtherOnClient(String Pdata)        //~var8I~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.saveProfileDataOtherOnClient data="+Pdata);//~var8I~
		String[] words=parse(Pdata);	//yourname,type,displayname,timestamp,size//~var8I~
        String rc;                                                 //~var8I~
        if (words.length>=CTR_WORD)                                //~var8I~
        {                                                          //~var8I~
        	String yn=words[0];
            int idx=BTG.searchByYourname(yn);                 //~var8I~//~2A02R~
            if (idx!=BTG.idxLocal)                                 //~2A02I~
            {                                                      //~2A02I~
            	int bmType=Utils.parseInt(words[1],0);//~var8I~    //~2A02R~//~2A06R~
				PD.putOther(idx,yn,bmType,words[2]/*displayName*/,words[3]/*ts*/,words[4]/*size*/);//~var8R~//~2A02R~
            }                                                      //~2A02I~
        }                                                          //~var8I~
    }                                                              //~var8I~
//***************************************************************  //~1AecI~//~var8I~
	public static String[] parse(String Pmsg)                     //~1AecI~//~var8I~
    {                                                              //~1AecI~//~var8I~
        String[] rc=Pmsg.split(MSG_SEPAPP,0);                              //~1AecI~//~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.parse msg="+Pmsg+",rc="+Utils.toString(rc));//~var8I~
        return rc;
    }                                                              //~1AecI~//~var8I~
//***************************************************************  //~var8I~
	public static String[] parseMulti(String Pmsg)                 //~var8I~
    {                                                              //~var8I~
        String[] rc=Pmsg.split(MSG_SEPAPP3,0);                     //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.parseMulti msg="+Pmsg+"\n,rc="+Utils.toString(rc));//~var8I~//~2A02R~
        return rc;                                                 //~var8I~
    }                                                              //~var8I~
//***************************************************************  //~var8I~
	public String getYourName(String PdeviceName)           //~var8I~
    {                                                              //~var8I~
        String yn=BTG.getYourNameName(PdeviceName);//~var8I~       //~2A02R~
    	if (Dump.Y) Dump.println("ProfileIcon.getYourName device="+PdeviceName+",yn="+yn);//~var8I~
        return yn;                                                 //~var8I~
    }                                                              //~var8I~
//***************************************************************  //~var8I~
//*send 76  (image C2S)                                                       //~2A03I~//~2A06R~
//***************************************************************  //~2A03I~
	public void sendBmpMe(BTIOThread Pbtio,int PmsgID)             //~var8R~
    {                                                              //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.sendBMPMe msgid="+PmsgID+",bmp"+bmpMe0);//~var8R~//~2A02R~
        Bitmap bm=bmpMe0;                                          //~var8I~
    	String msg=PD.makeMsgImageC2S();                           //~var8R~
    	if (msg==null)                                             //~var8R~
        	sendMsgToServer(PmsgID,"0"+MSG_SEP+msg);               //~var8R~
        else                                                       //~var8I~
        {                                                          //~var8I~
//      	byte[] ba=PD.compressBmpMe(bm);                        //~var8R~//~2A03R~
        	byte[] ba=PD.getCompressedMe();                          //~2A03R~
            long sizeCompressed=(long)ba.length;                   //~var8R~
        	sendMsgToServer(PmsgID,sizeCompressed+MSG_SEP+msg);    //~var8R~
    		sendImageToServer(ba);                                  //~var8R~
        }                                                          //~var8I~
    	if (Dump.Y) Dump.println("ProfileIcon.sendBMPMe exit");    //~2A02I~
    }                                                              //~var8I~
//***************************************************************  //~2A02I~
//* at 78/80 received from all client                                 //~2A04I~//~2A06R~
//***************************************************************  //~2A04I~
	public void sendBmpToAllClient()    //~2A02I~
    {                                                              //~2A02I~
    	if (Dump.Y) Dump.println("ProfileIcon.sendBMPToAllClient");//~2A02I~
        Point s_and_c=new Point();                                 //~2A02I~
        byte[] image=PD.getImageS2C(s_and_c);                          //~2A02I~
        if (image!=null)                                           //~2A02I~
        	sendBmpS2C(s_and_c.x/*client idx to send*/,s_and_c.y/*image idx*/,image);//send 79//~2A02I~//~2A04R~
        else                                                       //~2A04I~
	        chkCompleteS2C();	                                       //~2A02I~//~2A04R~
    	if (Dump.Y) Dump.println("ProfileIcon.sendBMPToAllClient exit");//~2A02I~
    }                                                              //~2A02I~
//***************************************************************  //~2A02I~
//*on GVH of Server                                                //~2A02I~
//*send 79 with image                                               //~2A02I~//~2A04R~
//***************************************************************  //~2A02I~
	public void sendBmpS2C(int PidxRemote,int PidxOther,byte[] Pbuff)//~2A02I~
    {                                                              //~2A02I~
    	if (Dump.Y) Dump.println("ProfileIcon.sendBmpS2C idxRemote="+PidxRemote+",idxOther="+PidxOther+",buff="+Pbuff+",len="+Pbuff.length);//~2A02I~
        String msg=PD.makeMsgImageS2C(PidxOther,Pbuff.length);     //~2A02I~
        sendMsgToTheClient(PidxRemote,GCM_PROFILE_SENDIMAGE_S2C,msg);//~2A02I~
    	sendImageToTheClient(PidxRemote,Pbuff);                    //~2A02R~
	    msgImageExchanging(true/*PswS2C*/);                        //~2A04I~
    	if (Dump.Y) Dump.println("ProfileIcon.sendBmpS2C exit");   //~2A02I~
    }                                                              //~2A02I~
//***************************************************************  //~2A02I~
//* on Server GVH                                                  //~2A02I~
//* send 81 to all if s2c completed                                //~2A05I~
//***************************************************************  //~2A02I~
	public boolean chkCompleteS2C()                                   //~2A02I~//~2A04R~
    {                                                              //~2A02I~
    	boolean rc=false;                                          //~2A04I~
    	if (Dump.Y) Dump.println("ProfileIcon.chkCompleteS2C");    //~2A02I~
        if (PD.isCompleteS2C())                                    //~2A02I~
        {                                                          //~2A04I~
        	sendMsgToAllClient(GCM_PROFILE_SYNC_COMP,""); //81 //~2A02I~//~2A05R~
            rc=true;                                               //~2A04I~
        }                                                          //~2A04I~
    	if (Dump.Y) Dump.println("ProfileIcon.chkCompleteS2C exit rc="+rc);//~2A02I~//~2A04R~
        return rc;                                                 //~2A04I~
    }                                                              //~2A02I~
    //****************************************************************//~2A02I~
    //*on BTIOThread of Client ;received 79 ,send 80                                   //~2A02R~//~2A06R~
    //****************************************************************//~2A02I~
    public void receivedSendImageS2C(String PdataPi,byte[] Pbuff)  //~2A02R~
    {                                                              //~2A02I~
    	if (Dump.Y) Dump.println("ProfileIcon.receivedSendImageS2C buff="+Pbuff+",data="+PdataPi);//~2A02R~
        String[] strpi=parse(PdataPi);                             //~2A02R~
	    PD.updateImageOtherOnClient(strpi[0]/*yourName*/,Pbuff);   //~2A02R~
        String msg=PD.makeMsgImageS2CR(strpi[0]);                     //~2A02I~
    	sendMsgToServer(GCM_PROFILE_SENDIMAGE_S2CR,msg);           //~2A02I~
    }                                                              //~2A02I~
    //****************************************************************//~2A02I~
    //*on GVH of Server from ACAction  received 80                            //~2A02I~//~2A04R~
    //****************************************************************//~2A02I~
    public void receivedSendImageS2CR(int Psender,String Pdata)    //~2A02I~
    {                                                              //~2A02I~
    	if (Dump.Y) Dump.println("ProfileIcon.receivedSendImageS2CR sender="+Psender+",data="+Pdata);//~2A02I~//~2A04R~
        String[] strpi=parse(Pdata);                               //~2A02I~
	    PD.updateSendImageS2C(Psender,strpi[0]/*yourName*/);       //~2A02I~
		sendBmpToAllClient();	//repeat                             //~2A04I~
    }                                                              //~2A02I~
    //****************************************************************//~2A02I~
    //*on BTIO of Client ,received 81                                          //~2A02I~//~2A06R~
    //****************************************************************//~2A02I~
    public void receivedProfileSyncComp()                          //~2A02I~
    {                                                              //~2A02I~
    	if (Dump.Y) Dump.println("ProfileIcon.receivedProfileSyncComp");//~2A02I~
//      setCurrentBmp();                                           //~2A03R~
		new EventCB(ECB_ACTION_STARTGAME).postEvent();            //~2A02I~//~2A03R~
    }                                                              //~2A02I~
    //****************************************************************//~2A03I~
    //* sorce bmp to resize,rotate                                 //~2A03I~
    //****************************************************************//~2A03I~
    private void setCurrentBmp(boolean PswAfterMove)               //~2A03R~
    {                                                              //~2A03I~
    	ACC=AG.aAccounts;  //for playalone game                    //~2A03I~
    	if (Dump.Y) Dump.println("ProfileIcon.setCurrentBmp");     //~2A03I~
        Bitmap bm;
//      bmpCurrent[PLAYER_YOU]=bmpMe0;                             //~2A03R~//~2A05R~
        for (int ii=0;ii<PLAYERS;ii++)                             //~2A03R~
        {                                                          //~2A03I~
            int idx=getAccountIndex(PswAfterMove,ii);              //~2A03R~
            Accounts.Account ac=ACC.accounts[idx];                 //~2A03I~
            if (ac.idxMembers==AG.aBTMulti.BTGroup.idxLocal)       //~2A03R~//~2A05R~
                if (swShowMe)                                      //~2A05I~
                {                                                  //~2A05I~
	            	bm=bmpMe0;                                         //~2A03I~//~2A05R~
                    if (bm==null)                                  //~2A05I~
	                	bm=bmpDummy0;                              //~2A05I~
                }                                                  //~2A05I~
                else                                               //~2A05I~
                	bm=bmpDummy0;                                  //~2A05I~
            else                                                   //~2A03I~//~2A05R~
            if (ac.isDummy())                                      //~2A03I~
				bm=getBmpRobot(ac.name);                           //~2A03I~
            else                                                   //~2A03I~
            {                                                      //~2A03I~
            	byte[] buff=PD.getImage(ac.idxMembers);                      //~2A03I~
                if (buff==null)                                    //~2A03I~
                	bm=bmpDummy0;                                  //~2A03R~
                else                                               //~2A03I~
					bm=byteToBmp(buff);                            //~2A03I~
            }                                                      //~2A03I~
            bmpCurrent[ii]=bm;                                     //~2A03I~
        }                                                          //~2A03I~
    	if (Dump.Y) Dump.println("ProfileIcon.setCurrentBmp bmpCurrent="+Utils.toString(bmpCurrent));//~2A03I~
    }                                                              //~2A03I~
    //****************************************************************//~2A03I~
    private int getAccountIndex(boolean PswAfterMove,int Pplayer)  //~2A03R~
    {                                                              //~2A03I~
    	int idx;                                                   //~2A03I~
                                                                   //~2A03I~
        if (PswAfterMove)                                          //~2A03I~
	    	idx=ACC.playerToMember(Pplayer);                       //~2A03I~
        else	                                                   //~2A03I~
	    	idx= Players.nextPlayer(ACC.yourESWN/*initial ESWN*/,Pplayer);//~2A03R~
    	if (Dump.Y) Dump.println("ProfileIcon.getAccountIndex swAfterMove="+PswAfterMove+",player="+Pplayer+",idx="+idx+",ACC.yourESWN="+ACC.yourESWN);//~2A03R~
        return idx;                                                //~2A03R~
    }                                                              //~2A03I~
    //****************************************************************//~2A03I~
    private Bitmap byteToBmp(byte[] Pbuff)                         //~2A03I~
    {                                                              //~2A03I~
        Bitmap bm=BitmapFactory.decodeByteArray(Pbuff,0,Pbuff.length);//~2A03I~
    	if (Dump.Y) Dump.println("ProfileIcon.byreToBmp buff="+Pbuff+",bm="+toString(bm));//~2A03I~
        return bm;                                                 //~2A03I~
    }                                                              //~2A03I~
    //****************************************************************//~2A04I~
    //*also from BTIOThread                                        //~2A04I~
    //****************************************************************//~2A04I~
    public void msgImageExchanging(boolean PswS2C)                 //~2A04I~
    {                                                              //~2A04I~
    	if (Dump.Y) Dump.println("ProfileIcon.msgImageExchanging swS2C="+PswS2C+",swMsgExchanging="+swMsgExchanging);//~2A04I~
        if (!swMsgExchanging)                                      //~2A04I~
        {                                                          //~2A04I~
        	UView.showToastLong(R.string.Info_ImageExchanging);   //~2A04I~
        	swMsgExchanging=true;                                  //~2A04I~
        }                                                          //~2A04I~
    }                                                              //~2A04I~
    //***************************************                      //~2A03M~
    private void recycle(Bitmap Pbmp)                              //~2A03M~
    {                                                              //~2A03M~
    	if (Dump.Y) Dump.println("ProfileIcon.recycle bmp="+toString(Pbmp));//~2A03M~
        UView.recycle(Pbmp);                                       //~2A03M~
    }                                                              //~2A03M~
    //***************************************                      //~2A03I~
    private void recycleCurrent(Bitmap Pbmp)                       //~2A03I~
    {                                                              //~2A03I~
    	if (Dump.Y) Dump.println("ProfileIcon.recycleCurrent bmp="+toString(Pbmp));//~2A03I~
        if (Pbmp==bmpDummy0 || Pbmp==bmpMe0)                       //~2A03I~
        	return;                                                //~2A03I~
        for (int ii=0;ii<CTR_ROBOT;ii++)                           //~2A03I~
        {                                                          //~2A03I~
    		if (Pbmp==bmpRobot0[ii])                               //~2A03I~
            	return;                                            //~2A03I~
        }                                                          //~2A03I~
        recycle(Pbmp);                                             //~2A03I~
    }                                                              //~2A03I~
//***************************************************************  //~var8I~//~2A03M~
	public static String toString(Bitmap Pbmp)                     //~var8I~//~2A03M~
    {                                                              //~var8I~//~2A03M~
    	if (Pbmp==null)                                            //~var8I~//~2A03M~
        	return "null";                                  //~var8I~//~2A03M~
        return Pbmp+",ww="+Pbmp.getWidth()+",hh="+Pbmp.getHeight();     //~var8I~//~2A03M~
    }                                                              //~var8I~//~2A03M~
}//class ProfileIcon
