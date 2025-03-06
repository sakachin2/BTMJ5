//*CID://+vaz3R~: update#=1010;                                    //+vaz3R~
//**********************************************************************
//2025/03/03 vaz3 (Bug) if not show profile, crash at CompReqDlg   //+vaz3I~
//2025/03/02 vaz1 nameplate/profile animation at win called        //~vaz1I~
//2025/02/10 vayg Try nameplate on left of stock for also landscape//~vaygI~
//2025/02/07 vaye profile icon should not override earth           //~vayeI~
//2025/02/07 vayd Adjust ProfileIcon for landscape(additional UID) //~vaydI~
//2025/02/02 vaya Adjust ProfileIcon for longDevice                //~vayaI~
//2025/01/31 vay8 profile may overwrap                             //~vay8I~
//2023/01/31 vaw0 ProfileIcon overwrap by positioning tile when not long device landscape//~vaw0I~
//2023/01/29 vavu avoid overwrap profile icon and left of river when landscape//~vavuI~
//2023/01/28 vavq overwrap chk for Left/Right river and Face/You profile when landscape//~vavqI~
//2023/01/10 vav5 show profile icon on CompReqDlg                  //~vav5I~
//2022/10/18 vatb show dummy if profile is not set                 //~vatbI~
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
    private static final String CN="ProfileIcon:";                 //~vaw0I~
    private static final int PROFILE_FRAME_COLOR=Color.argb(0xff,0xff,0x59,0xff);
    private static final int PROFILE_FRAME_COLOR_SCORE=Color.argb(0xff,0xff,0x59,0x00);
    private static final int PROFILE_FRAME_WIDTH=2;
    private static final int MARGIN_RIVER_LEFT=4;                  //~vavuI~
//  private static final float  PROFILE_HWRATE=0.75f;	//W:3 vs H:4//~vay8R~
//  private static final double PROFILE_HWRATE=0.618;	//2/(1+root-5)//~vay8R~//~vayeR~
    private static final double PROFILE_HWRATE=(double)89/127;	//0.7 photo-L//~vayeR~
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
    private static final int MIN_ICON_HEIGHT=5;                    //~vay8I~
    private static double ADJUST_RATE_STARTER=0.5; //TEST          //~vayaR~

    private Rect[] rectProfile,rectProfileBeforeMove;
    private Rect[] rectStarter;                                    //~vayaI~
    private boolean swShowProfile;
    private boolean swShowMe;
    private Bitmap bmpMe,bmpMe0;
//  private Bitmap bmpDummy,bmpDummy0;                             //~vatbR~
    public  Bitmap          bmpDummy0;                             //~vatbI~
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
//  private int maxHeightByStock; //distance from score top to stock edge                                 //~vas3R~//~vas4R~//~vayeR~
    private float heightRatePerNamePlate,expandRateBeforeMove;     //~vas4I~
    private boolean swAdjustedWithStarter=false;                   //~vayaI~
    private boolean swNPLeft; //namePlate on the left of stock     //~vaygI~
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
        swNPLeft=AG.swNamePlateLeft || AG.swLongDevice;            //~vaygI~
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
		swAdjustedWithStarter=false;                               //~vayaI~
    }
    //***************************************
    //*from NamePlate init()
    //***************************************
	public void setRect(Rect[] PrectNamePlate)
    {
//      maxHeightByStock=0;                                          //~vas3I~//~vayeR~
    	if (Dump.Y) Dump.println(CN+"setRect swShowProfile="+swShowProfile+",swNPLeft="+swNPLeft+",swLongDevice="+AG.swLongDevice+",rectNamePlate="+Utils.toString(PrectNamePlate));//~vavqR~//~vay8R~//~vaygR~
        if (!swShowProfile)
        	return;
//      if (AG.swLongDevice)                                       //~vaygR~
        if (swNPLeft)   //nameplate on the left of stock           //~vaygI~
        {
//          maxHeightByStock=AG.aMJTable.getProfilePortraitLimit();//~vayaI~//~vayeR~
        	rectProfile=setRectOnNamePlate(PrectNamePlate);
//          rectProfileBeforeMove=getRectBeforeMoved(rectProfile); //~vas4R~
//          rectProfileBeforeMove=getRectBeforeMoved(rectProfile,PrectNamePlate);//~vas4I~//~vayaR~
//          rectProfileBeforeMove=getRectBeforeMovedPort(rectProfile,PrectNamePlate);//~vayaR~//~vaydR~
            rectProfileBeforeMove=getRectBeforeMoved(rectProfile,PrectNamePlate);//~vayeR~
//          rectProfile=rectProfileBeforeMove;                     //~vas3R~
            rectProfile=setRectOnNamePlatePort(PrectNamePlate);    //~vayaI~
        	adjustWithRiverNeighbor(rectProfile);                  //~vayaI~
		    adjustWithStock(rectProfile); //icon is on the stock   //~vayaI~
        }
        else
        {
            if (AG.portrait)
            {                                                      //~vas3I~
//            	maxHeightByStock=AG.aMJTable.getProfilePortraitLimit();//~vas3R~//~vayeR~
                rectProfile=setRectOnNamePlate(PrectNamePlate);
		    	if (Dump.Y) Dump.println(CN+"setRect portrait after setRectOnNamePlatePort rectprofile="+Utils.toString(rectProfile));//~vay8R~
            }                                                      //~vas3I~
            else
            {                                                      //~vay8I~
                rectProfile=AG.aMJTable.rectProfile;
		    	if (Dump.Y) Dump.println(CN+"setRect landscape tbl rectprofile="+Utils.toString(rectProfile));//~vay8I~
            }                                                      //~vay8I~
//          rectProfileBeforeMove=getRectBeforeMoved(rectProfile); //~vas4R~
//          rectProfileBeforeMove=getRectBeforeMoved(rectProfile,PrectNamePlate);//~vas4I~//~vaydR~
            rectProfileBeforeMove=getRectBeforeMoved(rectProfile,PrectNamePlate); //~vaydI~//~vayeR~
            if (!AG.portrait)                                      //~vavqI~
            {                                                      //~vaw0I~
//              adjustWithRiverBeforeMoved(rectProfileBeforeMove); //~vaw0I~//~vayeR~
//              adjustWithRiver(rectProfile);                      //~vavqI~//~vay8R~
                adjustWithRiverNeighbor(rectProfile);              //~vay8I~
            	adjustWithStock(rectProfile); //icon is on the stock if landscape//~vay8R~
            }                                                      //~vaw0I~
            else                                                   //~vay8I~
            {                                                      //~vay8I~
              if (false) //TEST already called if port from setRectOnNamePlate//~vayaI~
                rectProfile=setRectOnNamePlatePort(PrectNamePlate);//~vay8M~
        		adjustWithRiverNeighbor(rectProfile);              //~vay8I~
		        adjustWithStock(rectProfile); //icon is on the stock//~vay8I~
            }                                                      //~vay8I~
        }
    	if (Dump.Y) Dump.println(CN+"setRect rectProfile="+Utils.toString(rectProfile));//~vay8R~
    	if (Dump.Y) Dump.println(CN+"setRect rectProfileBeforeMove="+Utils.toString(rectProfileBeforeMove));//~vay8R~
    }
    //*****************************************************************************//~vas3R~
    //*for Portrait or longDevice                                   //~vay8R~//~vayeR~
    //*set profile rect on NamePlate in Gaming; height=NamePlateHeight*2//~vay8I~
    //*parm:rectNamePlate                                          //~vay8I~
    //*****************************************************************************//~vas3I~
	public Rect[] setRectOnNamePlate(Rect[] Prect)
    {
        Rect rs;
        int margin=PROFILE_MARGIN,xx1,xx2,yy1,yy2,hh,ww;           //~vayeR~
        Rect[] rects=new Rect[PLAYERS];
    //***************************
    	if (Dump.Y) Dump.println(CN+"setRectOnNamePlate swLongDevice="+AG.swLongDevice+",rectNamePlate="+Utils.toString(Prect));//~vay8R~
      if (true)                                                    //~vayaI~
	  {    //set height by width rate                              //~vayaI~
        //*on nameplate and justify right                          //~vayaI~
        //*right                                                   //~vayaI~
            rs=Prect[PLAYER_RIGHT];                                //~vayaI~
            ww=rs.bottom-rs.top;                                   //~vayaI~
            hh=(int)(ww/PROFILE_HWRATE);    //*0.75;               //~vayaI~
            yy1=rs.top;                                            //~vayaI~
            yy2=yy1+ww;                                            //~vayaI~
            xx2=rs.left-margin;                                    //~vayaI~
            xx1=xx2-hh;                                            //~vayaI~
            rects[PLAYER_RIGHT]=new Rect(xx1,yy1,xx2,yy2);         //~vayaI~
	    	if (Dump.Y) Dump.println(CN+"setRectOnNamePlate ww="+ww+",hh="+hh+",rect["+PLAYER_RIGHT+"]="+rects[PLAYER_RIGHT]);//~vayeR~
        //*facing                                                  //~vayaI~
            rs=Prect[PLAYER_FACING];                               //~vayaI~
            ww=rs.right-rs.left;                                   //~vayaI~
            hh=(int)(ww/PROFILE_HWRATE);    //*0.75;               //~vayaI~
            xx1=rs.left;                                           //~vayaI~
            xx2=xx1+ww;                                            //~vayaI~
            yy1=rs.bottom+margin;                                  //~vayaI~
            yy2=yy1+hh;                                            //~vayaI~
            rects[PLAYER_FACING]=new Rect(xx1,yy1,xx2,yy2);        //~vayaI~
	    	if (Dump.Y) Dump.println(CN+"setRectOnNamePlate ww="+ww+",hh="+hh+",rect["+PLAYER_FACING+"]="+rects[PLAYER_FACING]);//~vayeR~
        //*left                                                    //~vayaI~
            rs=Prect[PLAYER_LEFT];                                 //~vayaI~
            ww=rs.bottom-rs.top;                                   //~vayaI~
            hh=(int)(ww/PROFILE_HWRATE);    //*0.75;               //~vayaI~
            yy2=rs.bottom;                                         //~vayaI~
            yy1=yy2-ww;                                            //~vayaI~
            xx1=rs.right+margin;                                   //~vayaI~
            xx2=xx1+hh;                                            //~vayaI~
            rects[PLAYER_LEFT]=new Rect(xx1,yy1,xx2,yy2);          //~vayaI~
	    	if (Dump.Y) Dump.println(CN+"setRectOnNamePlate ww="+ww+",hh="+hh+",rect["+PLAYER_LEFT+"]="+rects[PLAYER_LEFT]);//~vayeR~
        //*you                                                     //~vayaI~
            rs=Prect[PLAYER_YOU];                                  //~vayaI~
            ww=rs.right-rs.left;                                   //~vayaI~
            hh=(int)(ww/PROFILE_HWRATE);    //*0.75;               //~vayaI~
            xx2=rs.right;                                          //~vayaI~
            xx1=xx2-ww;                                            //~vayaI~
            yy2=rs.top-margin;                                     //~vayaI~
            yy1=yy2-hh;                                            //~vayaI~
            rects[PLAYER_YOU]=new Rect(xx1,yy1,xx2,yy2);           //~vayaI~
	    	if (Dump.Y) Dump.println(CN+"setRectOnNamePlate ww="+ww+",hh="+hh+",rect["+PLAYER_YOU+"]="+rects[PLAYER_YOU]);//~vayeR~
      }//new                                                       //~vayaI~
      else                                                         //~vayaI~
      {                                                            //~vayaI~
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
            if (Dump.Y) Dump.println("ProfileIcon.setRectOnNamePlate portrait hh="+hh+",ww="+ww);//~vay8R~
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
	  }//old                                                       //~vayaI~
    	if (Dump.Y) Dump.println(CN+"setRectOnNamePlate exit rectProfile="+Utils.toString(rects));//~vay8R~//~vayaR~
        return rects;
    }
    //*****************************************************************************//~vay8I~
    //*for Prtrait or longDevice                                   //~vay8I~
    //*set profile rect on NamePlate in Gaming; height=NamePlateHeight*2//~vay8I~
    //*****************************************************************************//~vay8I~
	public Rect[] setRectOnNamePlatePort(Rect[] PrectNameplate)    //~vay8R~//~vayaR~
    {                                                              //~vay8I~
        Rect rn;                                                   //~vay8I~
        int ww,hh;                                                 //~vay8I~
        Rect[] rects=new Rect[PLAYERS];                            //~vay8R~
        int margin=NamePlate.PLATE_EDGE_WIDTH*2;                   //~vay8R~
    //***************************                                  //~vay8I~
    	if (Dump.Y) Dump.println(CN+"setRectOnNamePlatePort swLongDevice="+AG.swLongDevice+",nameplate line width="+margin+",rectNamePlate="+Utils.toString(PrectNameplate));//~vay8R~
        //*You                                                     //~vay8I~
        rn=PrectNameplate[PLAYER_YOU];                             //~vay8I~
        ww=rn.right-rn.left;                                       //~vay8I~
        hh=(int)(ww/PROFILE_HWRATE);                               //~vay8I~
        rects[PLAYER_YOU]   =new Rect( rn.left,           rn.top-hh-margin, rn.right,           rn.top-margin);//~vay8R~
        //*Right                                                   //~vay8I~
        rn=PrectNameplate[PLAYER_RIGHT];                           //~vay8I~
        ww=rn.bottom-rn.top;                                       //~vay8I~
        hh=(int)(ww/PROFILE_HWRATE);                                      //~vay8I~
        rects[PLAYER_RIGHT] =new Rect( rn.left-hh-margin, rn.top,           rn.left-margin,     rn.bottom);//~vay8R~
        //*Face                                                    //~vay8I~
        rn=PrectNameplate[PLAYER_FACING];                          //~vay8I~
        ww=rn.right-rn.left;                                       //~vay8I~
        hh=(int)(ww/PROFILE_HWRATE);                                      //~vay8I~
        rects[PLAYER_FACING]=new Rect( rn.left,           rn.bottom+margin, rn.right,           rn.bottom+hh+margin);//~vay8R~
        //*Left                                                    //~vay8I~
        rn=PrectNameplate[PLAYER_LEFT];                            //~vay8I~
        ww=rn.bottom-rn.top;                                       //~vay8I~
        hh=(int)(ww/PROFILE_HWRATE);                                      //~vay8I~
        rects[PLAYER_LEFT] =new Rect( rn.right+margin,    rn.top,           rn.right+hh+margin, rn.bottom);//~vay8R~
    	if (Dump.Y) Dump.println(CN+"setRectOnNamePlatePort before adjust rectProfile="+Utils.toString(rects));//~vay8R~
        return rects;                                              //~vay8I~
    }                                                              //~vay8I~
//    //*************************************************************************//~vas3R~//~vayeR~
//    //*set Rect for before move seat(no score plate shown)         //~vas3I~//~vayeR~
//    //*height is double of in gaming                               //~vas3I~//~vayeR~
//    //*if on NamePlate(portrat or long device) show overriding score plate place//~vas3I~//~vayeR~
//    //*************************************************************************//~vas3I~//~vayeR~
////  public Rect[] getRectBeforeMoved(Rect[] Prect)                 //~vas4R~//~vayeR~
////  private Rect[] getRectBeforeMoved(Rect[] Prect,Rect[] PrectNamePlate)//~vas4I~//~vayaR~//~vayeR~
////  private Rect[] getRectBeforeMoved(Rect[] Prect,Rect[] PrectNamePlate)//~vayaR~//~vaydR~//~vayeR~
//    private Rect[] getRectBeforeMoved_OLD(Rect[] Prect,Rect[] PrectNamePlate)//~vaydI~//~vayeR~
//    {                                                            //~vayeR~
//        Rect rs;                                                 //~vayeR~
//        int margin=PROFILE_MARGIN,xx1,xx2,yy1,yy2,hh,ww;         //~vayeR~
//        Rect[] rects=new Rect[PLAYERS];                          //~vayeR~
//        int adjustByScore=0;                                       //~vas3I~//~vayeR~
//    //***************************                                //~vayeR~
//        if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved swLongDevice="+AG.swLongDevice+",Prect="+Utils.toString(Prect));//~vardR~//~vayeR~
//        if (AG.portrait)                                           //~vay8I~//~vayeR~
//        {                                                          //~vay8I~//~vayeR~
////          return getRectBeforeMovedPort(Prect,PrectNamePlate);   //~vay8I~//~vaydR~//~vayeR~
//            return getRectBeforeMoved(Prect);                      //~vaydI~//~vayeR~
//        }                                                          //~vay8I~//~vayeR~
//        //*on nameplate and justify right                        //~vayeR~
//            rs=Prect[PLAYER_YOU];                                //~vayeR~
////          hh=(int)((rs.bottom-rs.top)*PROFILE_EXPAND_BEFOREMOVE);//~vas4R~//~vayeR~
//            hh=(int)((rs.bottom-rs.top)*expandRateBeforeMove); // /(2.0--)*(1.0--)//~vas4R~//~vayeR~
//            if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved hh="+hh);//~vas3I~//~vayeR~
//            if (AG.swLongDevice || AG.portrait) //rectOnNamePlate  //~vas3R~//~vayeR~
////              adjustByScore=(rs.bottom-rs.top)/2;                //~vas3I~//~vas4R~//~vayeR~
//                adjustByScore=(int)((rs.bottom-rs.top)/heightRatePerNamePlate);//~vas4I~//~vayeR~
//                                                                   //~vas3I~//~vayeR~
//            int hhAdjustByScore=rs.bottom-rs.top+hh-adjustByScore; //~vas4R~//~vayeR~
//            if (maxHeightByStock!=0 && hhAdjustByScore>=maxHeightByStock)//~vas3R~//~vas4R~//~vayeR~
//                hh=maxHeightByStock-adjustByScore;                 //~vas3R~//~vayeR~
//                                                                   //~vas3I~//~vayeR~
//            if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved new hh="+hh+",hhAdjustByScore="+hhAdjustByScore+",maxHeightByStock="+maxHeightByStock+",adjustByScore="+adjustByScore);//~vas3R~//~vas4R~//~vayeR~
//            if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved adjustByScore="+adjustByScore+",additional hh="+hh+",hhYOUProfile="+(rs.bottom-rs.top));//~vardI~//~vas3R~//~vas4R~//~vayeR~
//            if (!AG.swLongDevice)                                  //~vardR~//~vayeR~
//            {                                                      //~vardI~//~vayeR~
//                int hhDecrease=AG.aMJTable.chkProfileRect(hh+rs.bottom-rs.top);//landscape chk //~vardR~//~vayeR~
//                if (hhDecrease>0)                                  //~vardI~//~vayeR~
//                    hh-=hhDecrease;                                //~vardI~//~vayeR~
//                if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved decrease="+hhDecrease+",hh="+hh);//~vas3I~//~vayeR~
//            }                                                      //~vardI~//~vayeR~
//            if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved nameplate width right-left="+(rs.right-rs.left)+",old hh="+hh);//~vas4M~//~vayeR~
//            int wwNamePlate=PrectNamePlate[PLAYER_YOU].right-PrectNamePlate[PLAYER_YOU].left;//~vas4I~//~vayeR~
//            int hhTotal=rs.bottom-rs.top+hh;                       //~vas4R~//~vayeR~
//            int ww2=(int)(hhTotal*PROFILE_HWRATE);  //width from height of you//~vas4I~//~vayeR~
//            if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved org nameplate width="+wwNamePlate+",height="+hh+",hhTotal="+hhTotal+",ww2 by hhTotal="+ww2);//~vas4R~//~vayeR~
//            if (ww2>wwNamePlate)                                   //~vas4I~//~vayeR~
//            {                                                      //~vas4M~//~vayeR~
//                int hhTotal2=(int)(wwNamePlate/PROFILE_HWRATE);    //~vas4R~//~vayeR~
//                hh-=hhTotal-hhTotal2;                              //~vas4R~//~vayeR~
//                if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved reduce by nameplate hhTotal new="+hhTotal2+",old="+hhTotal+",new hh="+hh);//~vas4R~//~vayeR~
//                hhTotal=hhTotal2;                                  //~vas4I~//~vayeR~
//            }                                                      //~vas4M~//~vayeR~
////          ww=(int)(hh*PROFILE_HWRATE);                           //~vas4R~//~vayeR~
//            ww=(int)(hhTotal*PROFILE_HWRATE)-(rs.right-rs.left);   //~vas4R~//~vayeR~
//            if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved reduce by nameplate ww="+ww+",hhTotal="+hhTotal+",new hh="+hh);//~vas4I~//~vayeR~
//        //*right                                                 //~vayeR~
//            rs=Prect[PLAYER_RIGHT];                              //~vayeR~
//            if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved hhRight="+(rs.right-rs.left)+",rect="+rs);//~vardR~//~vayeR~
//            xx1=rs.left-hh;      //doubled height                //~vayeR~
//            yy2=rs.bottom+ww;                                    //~vayeR~
//            xx2=rs.right;                                        //~vayeR~
//            yy1=rs.top;                                          //~vayeR~
//            xx1+=adjustByScore;  //shift down to nameplate                                  //~vas3R~//~vayeR~
//            xx2+=adjustByScore;                                    //~vas3R~//~vayeR~
//            rects[PLAYER_RIGHT]=new Rect(xx1,yy1,xx2,yy2);       //~vayeR~
//        //*facing                                                //~vayeR~
//            rs=Prect[PLAYER_FACING];                             //~vayeR~
//            if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved hhFacing="+(rs.bottom-rs.top));//~vardI~//~vayeR~
//            xx2=rs.right+ww;                                     //~vayeR~
//            yy2=rs.bottom+hh;                                    //~vayeR~
//            xx1=rs.left;                                         //~vayeR~
//            yy1=rs.top;                                          //~vayeR~
//            yy1-=adjustByScore;                                    //~vas3I~//~vayeR~
//            yy2-=adjustByScore;                                    //~vas3I~//~vayeR~
//            rects[PLAYER_FACING]=new Rect(xx1,yy1,xx2,yy2);      //~vayeR~
//        //*left                                                  //~vayeR~
//            rs=Prect[PLAYER_LEFT];                               //~vayeR~
//            if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved hhLeft="+(rs.right-rs.left));//~vardI~//~vayeR~
//            xx2=rs.right+hh;                                     //~vayeR~
//            yy1=rs.top-ww;                                       //~vayeR~
//            xx1=rs.left;                                         //~vayeR~
//            yy2=rs.bottom;                                       //~vayeR~
//            xx1-=adjustByScore;                                    //~vas3I~//~vayeR~
//            xx2-=adjustByScore;                                    //~vas3I~//~vayeR~
//            rects[PLAYER_LEFT]=new Rect(xx1,yy1,xx2,yy2);        //~vayeR~
//        //*you                                                   //~vayeR~
//            rs=Prect[PLAYER_YOU];                                //~vayeR~
//            xx1=rs.left-ww;                                      //~vayeR~
//            yy1=rs.top-hh;                                       //~vayeR~
//            xx2=rs.right;                                        //~vayeR~
//            yy2=rs.bottom;                                       //~vayeR~
//            yy1+=adjustByScore;                                    //~vas3I~//~vayeR~
//            yy2+=adjustByScore;                                    //~vas3I~//~vayeR~
//            rects[PLAYER_YOU]=new Rect(xx1,yy1,xx2,yy2);         //~vayeR~

//        if (Dump.Y) Dump.println("ProfileIcon.getRectBeforeMoved exit rect="+Utils.toString(rects));//~vayeR~
//        return rects;                                            //~vayeR~
//    }                                                            //~vayeR~
//    //*************************************************************************//~vay8I~//~vayeR~
//    //*for Portrait                                                //~vay8I~//~vayeR~
//    //*set Rect for before move seat(no score plate shown)         //~vay8I~//~vayeR~
//    //*height is double of in gaming                               //~vay8I~//~vayeR~
//    //*if on NamePlate(portrat or long device) show overriding score plate place//~vay8I~//~vayeR~
//    //*************************************************************************//~vay8I~//~vayeR~
//    private Rect[] getRectBeforeMovedPort_OLD2(Rect[] Prect,Rect[] PrectNamePlate)//~vay8I~//~vayaR~//~vayeR~
//    {                                                              //~vay8I~//~vayeR~
//        Rect rs;                                                   //~vay8I~//~vayeR~
//        int margin=PROFILE_MARGIN; // missing score box when before move//~vay8R~//~vayeR~
//        int minW,ww,hh;                                             //~vay8I~//~vayeR~
//        Rect[] rects=new Rect[PLAYERS];                            //~vay8I~//~vayeR~
//    //***************************                                  //~vay8I~//~vayeR~
//        if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort swLongDevice="+AG.swLongDevice+",maxHeightByStock="+maxHeightByStock+",Prect="+Utils.toString(PrectNamePlate));//~vay8R~//~vayaR~//~vayeR~
//        rs=PrectNamePlate[PLAYER_YOU];                             //~vay8R~//~vayeR~
//        ww=rs.right-rs.left;                                       //~vay8I~//~vayeR~
//        minW=ww;                                                   //~vay8R~//~vayeR~
//        rs=PrectNamePlate[PLAYER_RIGHT];                           //~vay8R~//~vayeR~
//        ww=rs.bottom-rs.top;                                       //~vay8I~//~vayeR~
//        minW=Math.min(minW,ww);                                    //~vay8I~//~vayeR~
//        rs=PrectNamePlate[PLAYER_FACING];                          //~vay8R~//~vayeR~
//        ww=rs.right-rs.left;                                       //~vay8I~//~vayeR~
//        minW=Math.min(minW,ww);                                    //~vay8I~//~vayeR~
//        rs=PrectNamePlate[PLAYER_LEFT];                            //~vay8R~//~vayeR~
//        ww=rs.bottom-rs.top;                                       //~vay8I~//~vayeR~
//        minW=Math.min(minW,ww);                                    //~vay8I~//~vayeR~
//        hh=maxHeightByStock-margin;                                //~vay8R~//~vayeR~
//        ww=(int)(hh*PROFILE_HWRATE);                               //~vay8I~//~vayeR~
//        if (ww>minW)                                               //~vay8I~//~vayeR~
//        {                                                          //~vay8I~//~vayeR~
//            ww=minW;                                               //~vay8I~//~vayeR~
//            hh=(int)(ww/PROFILE_HWRATE);                           //~vay8I~//~vayeR~
//        }                                                          //~vay8I~//~vayeR~
//        if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort minW="+minW+",margin="+margin+",hh="+hh+",ww="+ww+",maxHeightByStock="+maxHeightByStock);//~vay8I~//~vayeR~
//        //~vay8I~                                                //~vayeR~
//        //*right                                                   //~vay8I~//~vayeR~
//            rs=PrectNamePlate[PLAYER_RIGHT];                       //~vay8R~//~vayeR~
//            rects[PLAYER_RIGHT]=new Rect(rs.left-hh-margin,rs.top,rs.left-margin,rs.top+ww);//~vay8R~//~vayeR~
//        //*facing                                                  //~vay8I~//~vayeR~
//            rs=PrectNamePlate[PLAYER_FACING];                      //~vay8R~//~vayeR~
//            rects[PLAYER_FACING]=new Rect(rs.left,rs.bottom+margin,rs.left+ww,rs.bottom+hh+margin);//~vay8R~//~vayeR~
//        //*left                                                    //~vay8I~//~vayeR~
//            rs=PrectNamePlate[PLAYER_LEFT];                        //~vay8R~//~vayeR~
//            rects[PLAYER_LEFT]=new Rect(rs.right+margin,rs.bottom-ww,rs.right+hh+margin,rs.bottom);//~vay8R~//~vayeR~
//        //*you                                                     //~vay8I~//~vayeR~
//            rs=PrectNamePlate[PLAYER_YOU];                         //~vay8R~//~vayeR~
//            rects[PLAYER_YOU]=new Rect(rs.right-ww,rs.top-hh-margin,rs.right,rs.top-margin);//~vay8R~//~vayeR~
//        if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort exit rect="+Utils.toString(rects));//~vay8I~//~vayeR~
//        return rects;                                              //~vay8I~//~vayeR~
//    }                                                              //~vay8I~//~vayeR~
    //*************************************************************************//~vayaI~
    //override chk                                                 //~vayaI~
    //return available width and height                            //~vayaI~
    //Pspace: additional space required for the destination        //~vayaI~
    //Pdest: 0:tgt above src, 1:tgt left of src, 2: tgt under src, 3: tgt rigt of src//~vayaI~
    //*************************************************************************//~vayaI~
//  private Point chkOverwrapNeighbor(int Pdest,Rect Ptgt,Rect Psrc,int Pspace,int Pmargin)//~vayaI~//~vayeR~
    private Point chkOverwrapNeighbor(int Pdest,Rect Ptgt,Rect Psrc,int Pspace,int Pmargin,boolean PchkEarth)//~vayeI~
    {                                                              //~vayaI~
    	int distH,distW,distH2,distW2,srcH,srcW;                             //~vayaI~
        int maxW,ww,hh;                                            //~vayeI~
        Rect rc;                                                   //~vayaI~
    //*************************************                        //~vayaI~
    	if (Dump.Y) Dump.println(CN+"chkOverwrapNeighbor dest="+Pdest+",tgt="+Ptgt+",src="+Psrc+",Pspace="+Pspace+",margin="+Pmargin+",PchkEarth="+PchkEarth);//~vayaI~//~vayeR~
    	if (Dump.Y) Dump.println(CN+"chkOverwrapNeighbor HWRATE="+PROFILE_HWRATE);//~vaygI~
    	switch(Pdest)                                              //~vayaI~
        {                                                          //~vayaI~
        case 0:  //tgt left, src you                               //~vayaI~
	    	srcW=Psrc.right-Psrc.left;                             //~vayaI~
    		srcH=Psrc.bottom-Psrc.top;                             //~vayaI~
        	distH=(Ptgt.bottom+Pspace+Pmargin)-Psrc.top;   //vertical overwrap if +//~vayaI~
        	distW=(Ptgt.right+Pmargin)-Psrc.left;          //horizontal overwrap if +//~vayaI~
            maxW=Psrc.right-(Ptgt.left+Pmargin); 	//from earth   //~vayeI~
        	break;                                                 //~vayaI~
        case 1:  //tgt you , src right                             //~vayaI~
	    	srcW=Psrc.bottom-Psrc.top;                             //~vayaI~
    		srcH=Psrc.right-Psrc.left;                             //~vayaI~
        	distH=(Ptgt.right+Pspace+Pmargin)-Psrc.left;   //horizontal overwrap if +//~vayaI~
        	distW=Psrc.bottom-(Ptgt.top-Pmargin);   //vertical overwrap if +//~vayaI~
            maxW=(Ptgt.bottom-Pmargin)-Psrc.top; 	//from earth   //~vayeI~
        	break;                                                 //~vayaI~
        case 2:  //tgt right , src facing                          //~vayaI~
	    	srcW=Psrc.right-Psrc.left;                             //~vayaI~
    		srcH=Psrc.bottom-Psrc.top;                             //~vayaI~
        	distH=Psrc.bottom-(Ptgt.top-Pspace-Pmargin);   //vertical overwrap if +//~vayaI~
        	distW=Psrc.right-(Ptgt.left-Pmargin);   //horizontal overwrap if +//~vayaI~
            maxW=(Ptgt.right-Pmargin)-Psrc.left; 	//from earth   //~vayeI~
    	if (Dump.Y) Dump.println(CN+"chkOverwrapNeighbor @@@@ distH="+distH+",distW="+distW+",limitH="+(Ptgt.top-Pspace-Pmargin)+",limitW="+(Ptgt.left-Pmargin));//~vayeI~
        	break;                                                 //~vayaI~
        default:  //tgt facing , src left                          //~vayaI~
	    	srcW=Psrc.bottom-Psrc.top;                             //~vayaI~
    		srcH=Psrc.right-Psrc.left;                             //~vayaI~
        	distH=Psrc.right-(Ptgt.left-Pspace-Pmargin);   //vertical overwrap if +//~vayaR~
        	distW=(Ptgt.bottom+Pmargin)-Psrc.top;   //horizontal overwrap if +//~vayaR~
            maxW=Psrc.bottom-(Ptgt.top+Pmargin); 	//from earth   //~vayeR~
        }                                                          //~vayaI~
    	if (Dump.Y) Dump.println(CN+"chkOverwrapNeighbor distH="+distH+",distW="+distW+",maxW="+maxW);//~vayaI~//~vayeR~
        distH2=0; distW2=0;                                        //~vayaI~
        if (distH>0 && distW>0) //rect overwrap                    //~vayaI~
        {                                                  //~vayaI~
            distW2=(int)(distH*PROFILE_HWRATE);        //ww decrese by hh decrease//~vayaI~
            if (distW2<=distW)  //    adjusted by hh < overwrap ww  //~vayaI~//~vayeR~
            {                                                      //~vayaI~
            	distH2=distH;   //and distW2 above                 //~vayaR~
		    	if (Dump.Y) Dump.println(CN+"chkOverwrapNeighbor select distH, distW2("+distW2+") <= distW("+distW+")");//~vayaI~//~vayeR~
            }                                                      //~vayaI~
            else                //hh is larger when ww adjusted    //~vayaI~
            {                                                      //~vayaI~
                hh=(int)(distW/PROFILE_HWRATE);        //ww decrese by hh decrease//~vayaI~
		    	if (Dump.Y) Dump.println(CN+"chkOverwrapNeighbor select distW, distW2("+distW2+") > distW("+distW+")");//~vayaI~//~vaydM~//~vayeR~
                distW2=distW;                                      //~vayaI~
                distH2=hh;                                         //~vayaR~
            }                                                      //~vayaI~
		    if (Dump.Y) Dump.println(CN+"chkOverwrapNeighbor select distW2="+distW2+",distH2="+distH2);//~vayaI~
        }                                                          //~vayaI~
        ww=srcW-distW2;  //adjusted W and H regardless direction//~vayaI~//~vayeR~
        hh=srcH-distH2;  //adjusted W and H regardless direction  //~vayeI~
		if (Dump.Y) Dump.println(CN+"chkOverwrapNeighbor ww="+ww+",hh="+hh);//~vayeI~
        if (PchkEarth)                                             //~vayeI~
            if (ww>maxW)                                           //~vayeR~
            {                                                      //~vayeR~
                ww=maxW;                                           //~vayeR~
                hh=(int)(ww/PROFILE_HWRATE);        //ww decrese by hh decrease//~vayeR~
                if (Dump.Y) Dump.println(CN+"chkOverwrapNeighbor adjust by maxW="+maxW+",ww="+ww+",hh="+hh);//~vayeR~
            }                                                      //~vayeR~
        Point p=new Point(ww,hh);  //adjusted W and H regardless direction//~vayeI~
    	if (Dump.Y) Dump.println(CN+"chkOverwrapNeighbor exit newW/H="+p+",srcW="+srcW+",srcH="+srcH);//~vayaR~
        return p;                                                  //~vayaI~
    }                                                              //~vayaI~
    //*************************************************************************//~vayeI~
    //*before move no score box, shift icon to name box            //~vayeI~
    //*ICON is on nameplate                                        //~vayeI~
    //if sw off, simply adjust width by HWRATE                     //~vayeI~
    //*************************************************************************//~vayeI~
    private Rect[] shiftToNameBox(Rect[] PrectProfile,Rect[] PrectNamePlate,boolean PswShift)//~vayeR~
    {                                                              //~vayeI~
    	Rect[] out;                                                //~vayeI~
    	Rect rp,rn;                                                //~vayeR~
        int scoreH,hh,ww,npW;                                      //~vayeR~
    //******************                                           //~vayeI~
    	if (Dump.Y) Dump.println(CN+"shiftToNameBox swShift="+PswShift);//~vayeI~
        out=new Rect[PLAYERS];                                     //~vayeI~
    //*you                                                         //~vayeR~
        rp=new Rect(PrectProfile[PLAYER_YOU]);                     //~vayeR~
        rn=PrectNamePlate[PLAYER_YOU];                                 //~vayeI~
        if (PswShift)                                              //~vayeI~
        {                                                          //~vayeI~
        	scoreH=rn.bottom-rn.top;                               //~vayeR~
        	rp.bottom+=scoreH;                                     //~vayeI~
        }                                                          //~vayeI~
        hh=rp.bottom-rp.top;                                        //~vayeI~
        ww=(int)(hh*PROFILE_HWRATE);                               //~vayeI~
        npW=rn.right-rn.left;                                      //~vayeI~
	    if (Dump.Y) Dump.println(CN+"shiftToNameBox YOU src="+npW+",ww by hh="+ww);//~vayeI~
        if (npW<ww) //over nameplate width                         //~vayeI~
        {                                                          //~vayeI~
            ww=npW;                                                //~vayeI~
            hh=(int)(ww/PROFILE_HWRATE);                           //~vayeI~
            rp.top=rp.bottom-hh;                                   //~vayeI~
	    	if (Dump.Y) Dump.println(CN+"shiftToNameBox YOU width over nameplate new ww="+ww+",hh="+hh);//~vayeI~
        }                                                          //~vayeI~
        rp.left=rp.right-ww;                                       //~vayeI~
        out[PLAYER_YOU]=rp;                                        //~vayeR~
    //*right                                                       //~vayeI~
        rp=new Rect(PrectProfile[PLAYER_RIGHT]);                   //~vayeR~
        rn=PrectNamePlate[PLAYER_RIGHT];                               //~vayeI~
        if (PswShift)                                              //~vayeI~
        {                                                          //~vayeI~
        	scoreH=rn.right-rn.left;                               //~vayeR~
        	rp.right+=scoreH;                                      //~vayeR~
        }                                                          //~vayeI~
        hh=rp.right-rp.left;                                       //~vayeI~
        ww=(int)(hh*PROFILE_HWRATE);        //ww decrese by hh decrease//~vayeI~
        npW=rn.bottom-rn.top;                                      //~vayeI~
	    if (Dump.Y) Dump.println(CN+"shiftToNameBox RIGHT src="+npW+",ww by hh="+ww);//~vayeI~
        if (npW<ww) //over nameplate                               //~vayeI~
        {                                                          //~vayeI~
            ww=npW;                                                //~vayeI~
            hh=(int)(ww/PROFILE_HWRATE);                           //~vayeI~
            rp.left=rp.right-hh;                                   //~vayeI~
	    	if (Dump.Y) Dump.println(CN+"shiftToNameBox RIGHT width over nameplate new ww="+ww+",hh="+hh);//~vayeI~
        }                                                          //~vayeI~
        rp.bottom=rp.top+ww;                                       //~vayeI~
        out[PLAYER_RIGHT]=rp;                                      //~vayeR~
    //*face                                                        //~vayeI~
        rp=new Rect(PrectProfile[PLAYER_FACING]);                  //~vayeR~
        rn=PrectNamePlate[PLAYER_FACING];                              //~vayeI~
        if (PswShift)                                              //~vayeI~
        {                                                          //~vayeI~
        	scoreH=rn.bottom-rn.top;                               //~vayeR~
        	rp.top-=scoreH;                                        //~vayeI~
        }                                                          //~vayeI~
        hh=rp.bottom-rp.top;                                       //~vayeI~
        ww=(int)(hh*PROFILE_HWRATE);        //ww decrese by hh decrease//~vayeI~
        npW=rn.right-rn.left;                                      //~vayeI~
	    if (Dump.Y) Dump.println(CN+"shiftToNameBox FACE src="+npW+",ww by hh="+ww);//~vayeI~
        if (npW<ww) //over nameplate                               //~vayeI~
        {                                                          //~vayeI~
            ww=npW;                                                //~vayeI~
            hh=(int)(ww/PROFILE_HWRATE);                           //~vayeI~
            rp.bottom=rp.top+hh;                                   //~vayeI~
	    	if (Dump.Y) Dump.println(CN+"shiftToNameBox FACE width over nameplate new ww="+ww+",hh="+hh);//~vayeI~
        }                                                          //~vayeI~
        rp.right=rp.left+ww;                                        //~vayeI~
        out[PLAYER_FACING]=rp;                                     //~vayeR~
    //*left                                                        //~vayeI~
        rp=new Rect(PrectProfile[PLAYER_LEFT]);                    //~vayeR~
        rn=PrectNamePlate[PLAYER_LEFT];                                //~vayeI~
        if (PswShift)                                              //~vayeI~
        {                                                          //~vayeI~
        	scoreH=rn.right-rn.left;                               //~vayeR~
        	rp.left-=scoreH;                                       //~vayeI~
        }                                                          //~vayeI~
        hh=rp.right-rp.left;                                       //~vayeI~
        ww=(int)(hh*PROFILE_HWRATE);        //ww decrese by hh decrease//~vayeI~
        npW=rn.bottom-rn.top;                                      //~vayeI~
        if (npW<ww) //over nameplate                               //~vayeI~
        {                                                          //~vayeI~
            ww=npW;                                                //~vayeI~
            hh=(int)(ww/PROFILE_HWRATE);                           //~vayeI~
            rp.right=rp.left+hh;                                   //~vayeI~
	    	if (Dump.Y) Dump.println(CN+"shiftToNameBox LEFT width over nameplate new ww="+ww+",hh="+hh);//~vayeI~
        }                                                          //~vayeI~
        rp.top=rp.bottom-ww;                                       //~vayeI~
        out[PLAYER_LEFT]=rp;                                       //~vayeR~
                                                                   //~vayeI~
    	if (Dump.Y) Dump.println(CN+"shiftToNameBox rectProfile="+Utils.toString(PrectProfile));//~vayeR~
    	if (Dump.Y) Dump.println(CN+"shiftToNameBox rectNamePlate="+Utils.toString(PrectNamePlate));//~vayeI~
    	if (Dump.Y) Dump.println(CN+"shiftToNameBox out="+Utils.toString(out));//~vayeR~
        return out;                                                //~vayeI~
    }                                                              //~vayeI~
    //*************************************************************************//~vayaI~
    //*for Port or LongDevice at beforemove                        //~vayaI~
    //*ICON is on nameplate                                        //~vayaI~
    //*************************************************************************//~vayaI~
//  private Rect[] getRectBeforeMovedPortAdjusted(Rect[] Prect,Rect[] PrectNamePlate)//~vayaI~//~vaydR~
    private Rect[] getRectBeforeMoved(Rect[] PrectS,Rect[] PrectNamePlate)                //~vaydI~//~vayeR~
    {                                                              //~vayaI~
        Rect rectSrc,rs;                                              //~vayaR~//~vayeR~
        Rect[] rectSrcS;                                           //~vayeR~
        Rect[] stocks;                                             //~vayeI~
        Rect[] setups;    //setup tile on river at before move place//~vayaM~
        int margin=PROFILE_MARGIN; // missing score box when before move//~vayaI~//~vaygR~
        int minW,minH,ww,hh;                                          //~vayaI~
        Point newWH1,newWH2;                                       //~vayaI~
        //***************************                                  //~vayaI~
    	if (Dump.Y) Dump.println(CN+"getRectBeforeMoved swNPLeft="+swNPLeft+",swLongDevice="+AG.swLongDevice+",PrectProfile="+Utils.toString(PrectS));//~vayaI~//~vaydR~//~vayeR~//~vaygR~
    	if (Dump.Y) Dump.println(CN+"getRectBeforeMoved rectProfile="+Utils.toString(PrectS));//~vayaI~//~vaydR~
//      rectSrcS=shiftToNameBox(PrectS,PrectNamePlate,(AG.swLongDevice || AG.portrait));//~vayeR~//~vaygR~
        rectSrcS=shiftToNameBox(PrectS,PrectNamePlate,swNPLeft);   //~vaygI~
        stocks=AG.aStock.rectsBG;                                  //~vayaI~
        setups=AG.aRiver.getRectSetupAll();                        //~vayaI~
        if (Dump.Y) Dump.println(CN+"getRectBeforeMoved stocks="+Utils.toString(stocks));//~vayaI~//~vaydR~
        int wwPiece=AG.aMJTable.riverPieceW+margin;   //setup piece width //~vayeR~//~vaygR~
                                                                   //~vayaI~
        rectSrc=rectSrcS[PLAYER_YOU];                                 //~vayaI~//~vayeR~
	    newWH1=chkOverwrapNeighbor(PLAYER_YOU,stocks[PLAYER_LEFT],rectSrc,wwPiece,margin,false/*chkEarth*/);//~vayaR~//~vayeR~
	    newWH2=chkOverwrapNeighbor(PLAYER_YOU,setups[PLAYER_LEFT],rectSrc,0,margin,false/*chkEarth*/);//~vayaR~//~vayeR~
        ww=Math.min(newWH1.x,newWH2.x);                            //~vayaR~
        hh=Math.min(newWH1.y,newWH2.y);                            //~vayaI~
        minW=ww;                                                   //~vayaI~
        minH=hh;                                                   //~vayaI~
    	if (Dump.Y) Dump.println(CN+"getRectBeforeMoved YOU minW="+minW+",minH="+minH);//~vaydI~
                                                                   //~vayaI~
        rectSrc=rectSrcS[PLAYER_RIGHT];                               //~vayaI~//~vayeR~
	    newWH1=chkOverwrapNeighbor(PLAYER_RIGHT,stocks[PLAYER_YOU],rectSrc,wwPiece,margin,false/*chkEarth*/);//~vayaI~//~vayeR~
	    newWH2=chkOverwrapNeighbor(PLAYER_RIGHT,setups[PLAYER_YOU],rectSrc,0,margin,false/*chkEarth*/);//~vayaI~//~vayeR~
        ww=Math.min(newWH1.x,newWH2.x);                            //~vayaI~
        hh=Math.min(newWH1.y,newWH2.y);                            //~vayaI~
        minW=Math.min(minW,ww);                                    //~vayaI~
        minH=Math.min(minH,hh);                                    //~vayaI~
    	if (Dump.Y) Dump.println(CN+"getRectBeforeMoved RIGHT minW="+minW+",minH="+minH);//~vaydI~
                                                                   //~vayaI~
        rectSrc=rectSrcS[PLAYER_FACING];                              //~vayaI~//~vayeR~
	    newWH1=chkOverwrapNeighbor(PLAYER_FACING,stocks[PLAYER_RIGHT],rectSrc,wwPiece,margin,false/*chkEarth*/);//~vayaR~//~vayeR~
	    newWH2=chkOverwrapNeighbor(PLAYER_FACING,setups[PLAYER_RIGHT],rectSrc,0,margin,false/*chkEarth*/);//~vayaR~//~vayeR~
        ww=Math.min(newWH1.x,newWH2.x);                            //~vayaI~
        hh=Math.min(newWH1.y,newWH2.y);                            //~vayaI~
        minW=Math.min(minW,ww);                                    //~vayaI~
        minH=Math.min(minH,hh);                                    //~vayaI~
    	if (Dump.Y) Dump.println(CN+"getRectBeforeMoved FACE minW="+minW+",minH="+minH);//~vaydI~
                                                                   //~vayaI~
        rectSrc=rectSrcS[PLAYER_LEFT];                                //~vayaI~//~vayeR~
	    newWH1=chkOverwrapNeighbor(PLAYER_LEFT,stocks[PLAYER_FACING],rectSrc,wwPiece,margin,false/*chkEarth*/);//~vayaR~//~vayeR~
	    newWH2=chkOverwrapNeighbor(PLAYER_LEFT,setups[PLAYER_FACING],rectSrc,0,margin,false/*chkEarth*/);//~vayaR~//~vayeR~
        ww=Math.min(newWH1.x,newWH2.x);                            //~vayaI~
        hh=Math.min(newWH1.y,newWH2.y);                            //~vayaI~
        minW=Math.min(minW,ww);                                    //~vayaI~
        minH=Math.min(minH,hh);                                    //~vayaI~
    	if (Dump.Y) Dump.println(CN+"getRectBeforeMoved LEFT minW="+minW+",minH="+minH);//~vaydI~
                                                                   //~vaydI~
        Rect[] rects=new Rect[PLAYERS];                            //~vayaI~
        //*you                                                     //~vayaI~
            rs=rectSrcS[PLAYER_YOU];                                  //~vayaI~//~vayeR~
            rects[PLAYER_YOU]=new Rect(rs.right-minW, rs.bottom-minH, rs.right, rs.bottom);//~vayaI~
        //*right                                                   //~vayaI~
            rs=rectSrcS[PLAYER_RIGHT];                                //~vayaI~//~vayeR~
            rects[PLAYER_RIGHT]=new Rect(rs.right-minH, rs.top, rs.right, rs.top+minW);//~vayaI~
        //*facing                                                  //~vayaI~
            rs=rectSrcS[PLAYER_FACING];                               //~vayaI~//~vayeR~
            rects[PLAYER_FACING]=new Rect(rs.left, rs.top, rs.left+minW,rs.top+minH);//~vayaI~
        //*left                                                    //~vayaI~
            rs=rectSrcS[PLAYER_LEFT];                                 //~vayaI~//~vayeR~
            rects[PLAYER_LEFT]=new Rect(rs.left, rs.bottom-minW, rs.left+minH, rs.bottom);//~vayaI~
    	if (Dump.Y) Dump.println(CN+"getRectBeforeMoved exit rect="+Utils.toString(rects));//~vayaI~//~vaydR~
        return rects;                                              //~vayaI~
    }                                                              //~vayaI~
//    //*************************************************************************//~vayaI~//~vayeR~
//    //*for Port or LongDevice at beforemove                        //~vayaR~//~vayeR~
//    //*ICON is on nameplate                                        //~vayaI~//~vayeR~
//    //*************************************************************************//~vayaI~//~vayeR~
//    private Rect[] getRectBeforeMovedPort_OLD(Rect[] Prect,Rect[] PrectNamePlate)//~vayaI~//~vaydR~//~vayeR~
//    {                                                              //~vayaI~//~vayeR~
//        Rect rpr,stock,rs,setup;                                   //~vayaR~//~vayeR~
//        int margin=PROFILE_MARGIN; // missing score box when before move//~vayaI~//~vayeR~
//        int minW=0,ww,hh;                                            //~vayaI~//~vayeR~
//        Rect[] stocks;                                           //~vayeR~
//        Rect[] setups;    //setup tile on river at before move place//~vayaI~//~vayeR~
//        boolean swOverride=false;//~vayaI~                       //~vayeR~
//    //***************************                                  //~vayaI~//~vayeR~
//        if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort swLongDevice="+AG.swLongDevice+",maxHeightByStock="+maxHeightByStock+",PrectNamePlate="+Utils.toString(PrectNamePlate)+",PrectProfile="+Utils.toString(Prect));//~vayaR~//~vayeR~
////      if (true) //TODO test                                      //~vayaI~//~vaydR~//~vayeR~
////          return getRectBeforeMovedPortAdjusted(Prect,PrectNamePlate);//~vayaR~//~vaydR~//~vayeR~
//        stocks=AG.aStock.rectsBG;                                //~vayeR~
//        setups=AG.aRiver.getRectSetupAll();                        //~vayaI~//~vayeR~
//        if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort stocks="+Utils.toString(stocks));//~vayaI~//~vayeR~
//        int wwPiece=AG.aMJTable.handPieceH;                  //~vayaI~//~vayeR~
//        rpr=Prect[PLAYER_YOU];     //rectProfile on NamePlate      //~vayaR~//~vayeR~
//        stock=stocks[PLAYER_LEFT];                                 //~vayaI~//~vayeR~
//        setup=setups[PLAYER_LEFT];                                 //~vayaR~//~vayeR~
//     //* icon:you stock:left                                       //~vayaI~//~vayeR~
//        if (rpr.left<stock.right+margin) //overwrap your icon to left stock//~vayaR~//~vayeR~
//            if (rpr.top-stock.bottom<wwPiece)                      //~vayaR~//~vayeR~
//                swOverride=true;                                   //~vayaR~//~vayeR~
//        if (!swOverride)                                           //~vayaI~//~vayeR~
//        {                                                          //~vayaI~//~vayeR~
//            minW=rpr.right-rpr.left;    //You                      //~vayaR~//~vayeR~
//        }                                                          //~vayaI~//~vayeR~
//        if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort rpr:YOU and stock:LEFT swOverride="+swOverride+",minW="+minW);//~vayaR~//~vayeR~
//     //* icon:right stock:you                                      //~vayaI~//~vayeR~
//        rpr=Prect[PLAYER_RIGHT];                                    //~vayaR~//~vayeR~
//        stock=stocks[PLAYER_YOU];                                  //~vayaI~//~vayeR~
//        setup=setups[PLAYER_YOU];                                  //~vayaR~//~vayeR~
//        if (rpr.bottom>stock.top-margin) //overwrap                //~vayaR~//~vayeR~
//            if (rpr.left-stock.right<wwPiece)                      //~vayaR~//~vayeR~
//                swOverride=true;                                   //~vayaR~//~vayeR~
//        if (!swOverride)                                           //~vayaI~//~vayeR~
//        {                                                          //~vayaI~//~vayeR~
//            minW=Math.min(minW,rpr.bottom-rpr.top); //right        //~vayaR~//~vayeR~
//            if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort RIGHT minW="+minW+",rpr=="+rpr+",setup YOU="+setup);//~vayaR~//~vayeR~
//            if (rpr.bottom>setup.top-margin)    //setup you and icon right//~vayaR~//~vayeR~
//                if (rpr.left<setup.right-margin)    //overwarp setup:you and icon:right//~vayaR~//~vayeR~
//                {                                                  //~vayaI~//~vayeR~
//                    minW=Math.min(minW,(setup.top-margin)-rpr.top); //right//~vayaR~//~vayeR~
//                    if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort adjust by setup YOU minw="+minW);//~vayaI~//~vayeR~
//                }                                                  //~vayaI~//~vayeR~
//        }                                                          //~vayaI~//~vayeR~
//        if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort rpr:RIGHT and stock:YOU swOverride="+swOverride+",minW="+minW);//~vayaR~//~vayeR~
//     //* icon:face stock:right                                     //~vayaI~//~vayeR~
//        rpr=Prect[PLAYER_FACING];                                   //~vayaR~//~vayeR~
//        stock=stocks[PLAYER_RIGHT];                                //~vayaI~//~vayeR~
//        setup=setups[PLAYER_RIGHT];                                //~vayaR~//~vayeR~
//        if (rpr.right>stock.left-margin) //overwrap                //~vayaR~//~vayeR~
//            if (stock.top-rpr.bottom<wwPiece)                      //~vayaR~//~vayeR~
//                swOverride=true;                                   //~vayaR~//~vayeR~
//        if (!swOverride)                                           //~vayaI~//~vayeR~
//            minW=Math.min(minW,rpr.right-rpr.left); //face         //~vayaR~//~vayeR~
//        if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort rpr FACING swOverride="+swOverride+",minW="+minW);//~vayaR~//~vayeR~
//     //* icon:left stock:face                                      //~vayaI~//~vayeR~
//        rpr=Prect[PLAYER_LEFT];                                     //~vayaR~//~vayeR~
//        stock=stocks[PLAYER_FACING];                               //~vayaI~//~vayeR~
//        setup=setups[PLAYER_FACING];                               //~vayaR~//~vayeR~
//        if (rpr.top<stock.bottom+margin) //overwrap                //~vayaR~//~vayeR~
//            if (stock.left-rpr.right<wwPiece)                      //~vayaR~//~vayeR~
//                swOverride=true;                                   //~vayaR~//~vayeR~
//        if (!swOverride)               //icon left override stock face//~vayaR~//~vayeR~
//        {                                                          //~vayaI~//~vayeR~
//            if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort LEFT minw="+minW+",rpr="+rpr+",setup FACING="+setup);//~vayaR~//~vayeR~
//            minW=Math.min(minW,rpr.bottom-rpr.top); //left         //~vayaR~//~vayeR~
//            if (rpr.top<setup.bottom+margin)    //setup you and icon right//~vayaR~//~vayeR~
//                if (rpr.right>setup.left-margin)    //icon:Left override setup:face//~vayaR~//~vayeR~
//                {                                                  //~vayaI~//~vayeR~
//                    minW=Math.min(minW,rpr.bottom-(setup.bottom+margin)); //right//~vayaR~//~vayeR~
//                    if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort adjust by setup:Face minw="+minW);//~vayaI~//~vayeR~
//                }                                                  //~vayaI~//~vayeR~
//        }                                                          //~vayaI~//~vayeR~
//        if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort rpr LEFT swOverride="+swOverride+",minW="+minW);//~vayaR~//~vayeR~
//                                                                   //~vayaI~//~vayeR~
//        if (swOverride)                                            //~vayaI~//~vayeR~
//        {                                                          //~vayaI~//~vayeR~
//            hh=maxHeightByStock;                                   //~vayaI~//~vayeR~
//            ww=(int)(hh*PROFILE_HWRATE);                                 //~vayaI~//~vayeR~
//        }                                                          //~vayaI~//~vayeR~
//        else                                                       //~vayaI~//~vayeR~
//        {                                                          //~vayaI~//~vayeR~
//            ww=minW;                                               //~vayaI~//~vayeR~
//            hh=(int)(ww/PROFILE_HWRATE);                                 //~vayaI~//~vayeR~
//        }                                                          //~vayaI~//~vayeR~
//        if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort minW="+minW+",wwPiece="+wwPiece+",margin="+margin+",hh="+hh+",ww="+ww+",maxHeightByStock="+maxHeightByStock);//~vayaR~//~vayeR~
//        Rect[] rects=new Rect[PLAYERS];                            //~vayaR~//~vayeR~
//        //*you                                                     //~vayaI~//~vayeR~
//            rs=Prect[PLAYER_YOU];                                  //~vayaR~//~vayeR~
//            rects[PLAYER_YOU]=new Rect(rs.right-ww, rs.bottom-hh, rs.right, rs.bottom);//~vayaR~//~vayeR~
//        //*right                                                   //~vayaI~//~vayeR~
//            rs=Prect[PLAYER_RIGHT];                                //~vayaR~//~vayeR~
//            rects[PLAYER_RIGHT]=new Rect(rs.right-hh, rs.top, rs.right, rs.top+ww);//~vayaR~//~vayeR~
//        //*facing                                                  //~vayaI~//~vayeR~
//            rs=Prect[PLAYER_FACING];                               //~vayaR~//~vayeR~
//            rects[PLAYER_FACING]=new Rect(rs.left, rs.top, rs.left+ww,rs.top+hh);//~vayaR~//~vayeR~
//        //*left                                                    //~vayaI~//~vayeR~
//            rs=Prect[PLAYER_LEFT];                                 //~vayaR~//~vayeR~
//            rects[PLAYER_LEFT]=new Rect(rs.left, rs.bottom-ww, rs.left+hh, rs.bottom);//~vayaR~//~vayeR~
//        if (Dump.Y) Dump.println(CN+"getRectBeforeMovedPort exit rect="+Utils.toString(rects));//~vayaI~//~vayeR~
//        return rects;                                              //~vayaI~//~vayeR~
//    }                                                              //~vayaI~//~vayeR~
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
        {                                                          //~vay8I~
          if (false) //TEST                                        //~vayaI~
          	if (!swAdjustedWithStarter)                            //~vayaR~
            {                                                      //~vayaI~
    			if (Dump.Y) Dump.println(CN+"showOnNamePlate nop by swAdjustedWithStarter OFF");//~vayaI~
            	return;                                            //~vayaI~
            }                                                      //~vayaI~
          if (false) //TEST                                        //~vayaR~
			adjustWithStarterSeq2();                               //~vayaM~
          else                                                     //~vayaI~
			adjustWithStarterSeq3();                               //~vayaI~
        	rects=rectProfile;                                     //~2A03I~
    		if (Dump.Y) Dump.println(CN+"showOnNamePlate afterMove rectProfile="+Utils.toString(rects));//~vay8I~
        }                                                          //~vay8I~
        else                                                       //~2A03I~
        {                                                          //~vay8I~
            rects=rectProfileBeforeMove;                           //~2A03I~
    		if (Dump.Y) Dump.println(CN+"showOnNamePlate beforeMove rectProfile="+Utils.toString(rects));//~vay8I~
        }                                                          //~vay8I~
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
    //*****************************************************************//~vayaI~
    private void drawProfileAll(Rect[] PrectProfile)               //~vayaI~
    {                                                              //~vayaI~
    	if (Dump.Y) Dump.println(CN+"drawProfileAll rectProfile="+Utils.toString(PrectProfile));//~vayaI~
        for (int ii=0;ii<PLAYERS;ii++)                             //~vayaI~
        {                                                          //~vayaI~
            drawProfile(PrectProfile[ii],bmpCurrent[ii]);          //~vayaI~
        }                                                          //~vayaI~
    }                                                              //~vayaI~
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
    //***************************************                      //~vav5I~
    public Bitmap getPlayerProfile(int Pplayer)                    //~vav5I~
    {                                                              //~vav5I~
    	if (Dump.Y) Dump.println("ProfileIcon.getPlayerProfile player="+Pplayer);//~vav5I~
        Bitmap bmp=bmpCurrent[Pplayer];                            //~vav5I~
        if (bmp==null)                                             //+vaz3I~
        {                                                          //+vaz3I~
    		if (Dump.Y) Dump.println(CN+"getPlayerProfile return null");//+vaz3I~
            return null;                                           //+vaz3I~
        }                                                          //+vaz3I~
        Bitmap bmRotated=rotateBMP(bmp,-Pplayer);                  //~vav5I~
    	if (Dump.Y) Dump.println(CN+"getPlayerProfile bmRotated="+bmRotated);//+vaz3I~
        return bmRotated;
    }                                                              //~vav5I~
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
        case -1:	//right                                        //~vav5I~
        	degree=-270;                                           //~vav5I~
            break;                                                 //~vav5I~
        case -2: //facing                                          //~vav5I~
        	degree=-180;                                           //~vav5I~
            break;                                                 //~vav5I~
        case -3: //left                                            //~vav5I~
        	degree=-90;                                            //~vav5I~
            break;                                                 //~vav5I~
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
    //*****************************************************************//~vayaI~
    //*by adjustwithStarterSeq                                     //~vayaI~
    //****************************************************************//~vayaI~
	private void clearProfileIcon(Rect[] PrectProfile)             //~vayaR~
    {                                                              //~vayaI~
    	if (Dump.Y) Dump.println(CN+"clearProfileIcon");           //~vayaI~
        for (int ii=0;ii<PLAYERS;ii++)                             //~vayaI~
		    Graphics.drawRect(PrectProfile[ii],COLOR_BG_TABLE);    //~vayaI~
    }                                                              //~vayaI~
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
//      Bitmap bm=bmpDummy;                                        //~vatbR~
        Bitmap bm=bmpDummy0;                                       //~vatbI~
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
//    //***************************************                    //~vatbR~
//    public Bitmap getBmpOther(String Pname)                      //~vatbR~
//    {                                                            //~vatbR~
//        if (Dump.Y) Dump.println("ProfileIcon.getBmpOther");     //~vatbR~
//        Bitmap bm=bmpDummy;                                      //~vatbR~
//        if (Dump.Y) Dump.println("ProfileIcon.getBmpOther bitmap "+toString(bm));//~var8R~//~2A03R~//~vatbR~
//        return bm;                                               //~vatbR~
//    }                                                            //~vatbR~
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
//***************************************************************  //~vavqI~
//*for lanscape                                                    //~vavqI~
//*Not Used                                                        //~vay8I~
//***************************************************************  //~vavqI~
	private int adjustWithRiver(Rect[] PrectProfile)               //~vavqI~
    {                                                              //~vavqI~
    	if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver PrectProfile="+Utils.toString(PrectProfile));//~vavqI~
        int shrinkH=0;                                              //~vavqI~
        Rect rectProfile=PrectProfile[PLAYER_FACING];              //~vavqI~
        Rect rectRiver;                                            //~vaw0R~
        rectRiver=AG.aRiver.getRiverRect(PLAYER_RIGHT);       //~vaw0I~
	  if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver rectProfile[FACING]="+rectProfile+",rectRiver[RIGHT]="+rectRiver);//~vaw0I~
        if (rectRiver.left<rectProfile.right && rectRiver.top<rectProfile.bottom)   //overwrap//~vavqI~
        {                                                          //~vavqI~
        //*overwrap with neighbor river                            //~vay8R~
            shrinkH=rectProfile.bottom-rectRiver.top;   //overwrap//~vavqI~
//          int shrinkW=(int)((double)shrinkH*(rectProfile.right-rectProfile.left)/(rectProfile.bottom-rectProfile.top));//~vavqR~//~vay8R~
            int shrinkW=(int)((double)shrinkH*PROFILE_HWRATE); //=0.618;	//2/(1+root-5)//~vay8I~
    		if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver overWrap shrinkH="+shrinkH+",shrinkW="+shrinkW+",rectProfile="+rectProfile);//~vavqR~//~vaw0R~
            if (PrectProfile[PLAYER_YOU   ].left+shrinkW+5>=PrectProfile[PLAYER_YOU   ].right//~vavqI~
            ||  PrectProfile[PLAYER_YOU   ].top +shrinkH+5>=PrectProfile[PLAYER_YOU   ].bottom)//~vavqI~
            {                                                      //~vaw0I~
            	shrinkH=0;                                         //~vavqI~
	    	    if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver if shrinked icon is smaller tha 5 pix, avoid shrink rectProfile[YOU]="+PrectProfile[PLAYER_YOU   ]);//~vaw0I~
            }                                                      //~vaw0I~
            else                                                   //~vavqI~
            {                                                      //~vavqI~
                PrectProfile[PLAYER_YOU   ].left   +=shrinkW;      //~vavqR~
                PrectProfile[PLAYER_YOU   ].top    +=shrinkH;      //~vavqR~
                PrectProfile[PLAYER_RIGHT ].left   +=shrinkH;      //~vavqR~
                PrectProfile[PLAYER_RIGHT ].bottom -=shrinkW;      //~vavqR~
                PrectProfile[PLAYER_FACING].bottom -=shrinkH;      //~vavqR~
                PrectProfile[PLAYER_FACING].right  -=shrinkW;      //~vavqR~
                PrectProfile[PLAYER_LEFT  ].top    +=shrinkW;      //~vavqR~
                PrectProfile[PLAYER_LEFT  ].right  -=shrinkH;      //~vavqR~
            }                                                      //~vavqI~
        }                                                          //~vavqI~
    	if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver after end of river rectProfile="+Utils.toString(PrectProfile));//~vavuI~
        Point ptY=AG.aMJTable.getRiverPos(PLAYER_YOU);             //~vavuR~
        Point ptL=AG.aMJTable.getRiverPos(PLAYER_LEFT);            //~vavuI~
        int wrapWW=PrectProfile[PLAYER_YOU].right+MARGIN_RIVER_LEFT-ptY.x;//~vavuR~
        int spaceWW=PrectProfile[PLAYER_YOU].left-MARGIN_RIVER_LEFT-ptL.x-AG.aMJTable.riverPieceH;//~vavuI~
    	if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver riverPieceH="+AG.aMJTable.riverPieceH+",wrapWW="+wrapWW+",spaceWW="+spaceWW);//~vavuI~
      if (wrapWW>0)                                                //~vay8R~
        if (spaceWW>=wrapWW)                                       //~vavuI~
        {                                                          //~vavuI~
        //*horizontally overwrap with my river                     //~vay8R~
            PrectProfile[PLAYER_YOU   ].left   -=wrapWW;           //~vavuI~
            PrectProfile[PLAYER_YOU   ].right  -=wrapWW;           //~vavuI~
            PrectProfile[PLAYER_RIGHT ].top    +=wrapWW;           //~vavuI~
            PrectProfile[PLAYER_RIGHT ].bottom +=wrapWW;           //~vavuI~
            PrectProfile[PLAYER_FACING].left   +=wrapWW;           //~vavuI~
            PrectProfile[PLAYER_FACING].right  +=wrapWW;           //~vavuI~
            PrectProfile[PLAYER_LEFT  ].top    -=wrapWW;           //~vavuI~
            PrectProfile[PLAYER_LEFT  ].bottom -=wrapWW;           //~vavuI~
    		if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver adjust by wrapWW rectProfile="+Utils.toString(PrectProfile));//~vavuI~//~vaw0R~
            wrapWW=0;                                              //~vavuI~
        }                                                          //~vavuI~
        if (wrapWW>0)                                              //~vavuI~
        {                                                          //~vavuI~
        //*no space to shift to avoid overwrap with my river       //~vay8R~
//          int wrapHH=(int)((double)wrapWW/(rectProfile.right-rectProfile.left)*(rectProfile.bottom-rectProfile.top));//~vavuR~//~vay8R~
            int wrapHH=(int)((double)wrapWW/PROFILE_HWRATE); //=0.618;	//2/(1+root-5)//~vay8I~
    		if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver overWrap with riverLeft H="+wrapHH+",W="+wrapWW+",rectProfile="+Utils.toString(PrectProfile));//~vavuR~
            if (PrectProfile[PLAYER_YOU   ].right-wrapWW-5>=PrectProfile[PLAYER_YOU   ].left//~vavuI~
            ||  PrectProfile[PLAYER_YOU   ].top+wrapHH+5>=PrectProfile[PLAYER_YOU   ].bottom)//~vavuI~
            {                                                      //~vavuI~
                PrectProfile[PLAYER_YOU   ].top    +=wrapHH;       //~vavuI~
                PrectProfile[PLAYER_YOU   ].right  -=wrapWW;       //~vavuI~
                PrectProfile[PLAYER_RIGHT ].left   +=wrapHH;       //~vavuI~
                PrectProfile[PLAYER_RIGHT ].top    +=wrapWW;       //~vavuI~
                PrectProfile[PLAYER_FACING].bottom -=wrapHH;       //~vavuI~
                PrectProfile[PLAYER_FACING].left   +=wrapWW;       //~vavuI~
                PrectProfile[PLAYER_LEFT  ].right  -=wrapHH;       //~vavuI~
                PrectProfile[PLAYER_LEFT  ].bottom -=wrapWW;       //~vavuI~
	            shrinkH+=wrapHH;                                 //~vavuI~
    			if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver overWrap RiverTop rectProfile="+Utils.toString(PrectProfile));//~vavuI~
            }                                                      //~vavuI~
        }                                                          //~vavuI~
    	if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver rc=shrinkH="+shrinkH);//~vavqI~//~vavuM~//~vaw0R~
        return shrinkH;
    }                                                              //~vavqI~
//***************************************************************  //~vay8I~
//*for port/lanscape/longDevice                                                    //~vay8I~//~vayaR~
//*no need to check with self river because                        //~vayeI~
//* for port and long device, it is on the left of stock,          //~vayeI~
//* for landscape it is on te first stock tile.                    //~vayeI~
//***************************************************************  //~vay8I~
	private void adjustWithRiverNeighbor(Rect[] PrectProfile)       //~vay8I~//~vayeR~
    {                                                              //~vay8I~
    	if (Dump.Y) Dump.println(CN+"adjustWithRiverNeighbor PrectProfile="+Utils.toString(PrectProfile));//~vay8I~//~vayeR~
//		int overwrap=getMaxOverwrapWithRiverNeighbor(PrectProfile);//~vay8I~//~vayeR~
//        if (overwrap>0)                                            //~vay8I~//~vayeR~
//        {                                                          //~vay8I~//~vayeR~
//            int shrinkH=overwrap;                                  //~vay8I~//~vayeR~
//            int shrinkW=(int)((double)shrinkH*PROFILE_HWRATE); //=0.618;    //2/(1+root-5)//~vay8I~//~vayeR~
//            if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver overWrap shrinkH="+shrinkH+",shrinkW="+shrinkW+",rectProfile="+rectProfile);//~vay8I~//~vayeR~
//            PrectProfile[PLAYER_YOU   ].left   +=shrinkW;          //~vay8I~//~vayeR~
//            PrectProfile[PLAYER_YOU   ].top    +=shrinkH;          //~vay8I~//~vayeR~
//            PrectProfile[PLAYER_RIGHT ].left   +=shrinkH;          //~vay8I~//~vayeR~
//            PrectProfile[PLAYER_RIGHT ].bottom -=shrinkW;          //~vay8I~//~vayeR~
//            PrectProfile[PLAYER_FACING].bottom -=shrinkH;          //~vay8I~//~vayeR~
//            PrectProfile[PLAYER_FACING].right  -=shrinkW;          //~vay8I~//~vayeR~
//            PrectProfile[PLAYER_LEFT  ].top    +=shrinkW;          //~vay8I~//~vayeR~
//            PrectProfile[PLAYER_LEFT  ].right  -=shrinkH;          //~vay8I~//~vayeR~
//        }                                                          //~vay8I~//~vayeR~
  		Point p=getMaxOverwrapWithRiverNeighbor(PrectProfile);     //~vayeI~
        int ww=p.x; int hh=p.y;                                     //~vayeI~
		Rect r;	                                                   //~vayeI~
        r=PrectProfile[PLAYER_YOU];                                //~vayeI~
        r.left=r.right-ww;  r.top=r.bottom-hh;                     //~vayeI~
        r=PrectProfile[PLAYER_RIGHT];                              //~vayeI~
        r.left=r.right-hh;  r.bottom=r.top+ww;                     //~vayeI~
        r=PrectProfile[PLAYER_FACING];                             //~vayeI~
        r.right=r.left+ww;  r.bottom=r.top+hh;                     //~vayeI~
        r=PrectProfile[PLAYER_LEFT];                               //~vayeI~
        r.right=r.left+hh;  r.top=r.bottom-ww;                     //~vayeI~
    	if (Dump.Y) Dump.println(CN+"adjustWithRiverNeighbor exit rectProfile="+Utils.toString(PrectProfile));//~vayaI~//~vayeR~
//      return overwrap;                                           //~vay8I~//~vayeR~
    }                                                              //~vay8I~
//***************************************************************  //~vayaI~
//*from Starter                                                    //~vayaR~
//***************************************************************  //~vayaI~
	public void adjustWithStarterSeq(Rect[] PrectGameSeq)          //~vayaR~
    {                                                              //~vayaI~
    	if (Dump.Y) Dump.println(CN+"adjustWithStarterSeq PrectGameSeq="+Utils.toString(PrectGameSeq));//~vayaI~
    	if (Dump.Y) Dump.println(CN+"adjustWithStarterSeq rectProfile="+Utils.toString(rectProfile));//~vayaI~
		int overwrap=getMaxOverwrapWithStarterSeq(rectProfile,PrectGameSeq);//~vayaI~
        if (overwrap>0)                                            //~vayaI~
        {                                                          //~vayaI~
            int shrinkH=overwrap;                                  //~vayaI~
            int shrinkW=(int)((double)shrinkH*PROFILE_HWRATE); //=0.618;	//2/(1+root-5)//~vayaI~
    		if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiver overWrap shrinkH="+shrinkH+",shrinkW="+shrinkW+",rectProfile="+rectProfile);//~vayaI~
            rectProfile[PLAYER_YOU   ].left   +=shrinkW;           //~vayaR~
            rectProfile[PLAYER_YOU   ].top    +=shrinkH;           //~vayaR~
            rectProfile[PLAYER_RIGHT ].left   +=shrinkH;           //~vayaR~
            rectProfile[PLAYER_RIGHT ].bottom -=shrinkW;           //~vayaR~
            rectProfile[PLAYER_FACING].bottom -=shrinkH;           //~vayaR~
            rectProfile[PLAYER_FACING].right  -=shrinkW;           //~vayaR~
            rectProfile[PLAYER_LEFT  ].top    +=shrinkW;           //~vayaR~
            rectProfile[PLAYER_LEFT  ].right  -=shrinkH;           //~vayaR~
        }                                                          //~vayaI~
    	if (Dump.Y) Dump.println(CN+"adjustWithStartGameSeq rc=overwarp="+overwrap);//~vayaI~
    	if (Dump.Y) Dump.println(CN+"adjustWithStartGameseq exit rectProfile="+Utils.toString(rectProfile));//~vayaI~
        swAdjustedWithStarter=true;                                //~vayaI~
		showOnNamePlate();                                          //~vayaI~
    }                                                              //~vayaI~
//***************************************************************  //~vayaI~
	public void adjustWithStarterSeq2()                            //~vayaR~
    {                                                              //~vayaI~
    	if (Dump.Y) Dump.println(CN+"adjustWithStarterSeq2 PrectProfile="+Utils.toString(rectProfile));//~vayaR~
        Rect rectStarterSeq[]=AG.aStarter.adjustProfileIconBeforeMove();//~vayaI~
		int overwrap=getMaxOverwrapWithStarterSeq(rectProfile,rectStarterSeq);//~vayaR~
        if (overwrap>0)                                            //~vayaI~
        {                                                          //~vayaI~
            int shrinkH=overwrap;                                  //~vayaI~
            int shrinkW=(int)((double)shrinkH*PROFILE_HWRATE); //=0.618;	//2/(1+root-5)//~vayaI~
    		if (Dump.Y) Dump.println(CN+"adjustWithStarterSeq2 overWrap shrinkH="+shrinkH+",shrinkW="+shrinkW+",rectProfile="+rectProfile);//~vayaR~
            rectProfile[PLAYER_YOU   ].left   +=shrinkW;           //~vayaI~
            rectProfile[PLAYER_YOU   ].top    +=shrinkH;           //~vayaI~
            rectProfile[PLAYER_RIGHT ].left   +=shrinkH;           //~vayaI~
            rectProfile[PLAYER_RIGHT ].bottom -=shrinkW;           //~vayaI~
            rectProfile[PLAYER_FACING].bottom -=shrinkH;           //~vayaI~
            rectProfile[PLAYER_FACING].right  -=shrinkW;           //~vayaI~
            rectProfile[PLAYER_LEFT  ].top    +=shrinkW;           //~vayaI~
            rectProfile[PLAYER_LEFT  ].right  -=shrinkH;           //~vayaI~
        }                                                          //~vayaI~
    	if (Dump.Y) Dump.println(CN+"adjustWithStartSeq2 rc=overwarp="+overwrap);//~vayaR~
    	if (Dump.Y) Dump.println(CN+"adjustWithStartSeq2 exit rectProfile="+Utils.toString(rectProfile));//~vayaR~
    }                                                              //~vayaI~
//***************************************************************  //~vayaI~
	public void adjustWithStarterSeq3()                            //~vayaI~
    {                                                              //~vayaI~
		int overwrap;                                              //~vayaI~
                                                                   //~vayaI~
    	if (Dump.Y) Dump.println(CN+"adjustWithStarterSeq3 PrectProfile="+Utils.toString(rectProfile));//~vayaI~
        Rect rectSeq=AG.aStarter.getRectStarterSeq();              //~vayaR~
        if (AG.portrait)                                           //~vayaI~
        {                                                          //~vayaI~
			overwrap=rectSeq.right-rectProfile[PLAYER_RIGHT].left; //~vayaR~
            if (rectProfile[PLAYER_RIGHT].bottom<rectSeq.top) //icon is above starter//~vayaI~
            	overwrap=0;                                        //~vayaI~
        }                                                          //~vayaI~
        else                                                       //~vayaI~
        {                                                          //~vayaI~
			overwrap=rectSeq.bottom-rectProfile[PLAYER_YOU].top;   //~vayaI~
            if (rectProfile[PLAYER_YOU].left>rectSeq.right) //icon is right of starter//~vayaI~
            	overwrap=0;                                        //~vayaI~
        }                                                          //~vayaI~
        if (overwrap>0)                                            //~vayaR~
        {                                                          //~vayaR~
            int shrinkH=overwrap;                                  //~vayaR~
            int shrinkW=(int)((double)shrinkH*PROFILE_HWRATE); //=0.618;    //2/(1+root-5)//~vayaR~
            if (Dump.Y) Dump.println(CN+"adjustWithStarterSeq3 overWrap shrinkH="+shrinkH+",shrinkW="+shrinkW+",rectProfile="+Utils.toString(rectProfile));//~vayaR~
            rectProfile[PLAYER_YOU   ].left   +=shrinkW;           //~vayaR~
            rectProfile[PLAYER_YOU   ].top    +=shrinkH;           //~vayaR~
            rectProfile[PLAYER_RIGHT ].left   +=shrinkH;           //~vayaR~
            rectProfile[PLAYER_RIGHT ].bottom -=shrinkW;           //~vayaR~
            rectProfile[PLAYER_FACING].bottom -=shrinkH;           //~vayaR~
            rectProfile[PLAYER_FACING].right  -=shrinkW;           //~vayaR~
            rectProfile[PLAYER_LEFT  ].top    +=shrinkW;           //~vayaR~
            rectProfile[PLAYER_LEFT  ].right  -=shrinkH;           //~vayaR~
        }                                                          //~vayaR~
    	if (Dump.Y) Dump.println(CN+"adjustWithStartSeq3 rc=overwarp="+overwrap);//~vayaI~
    	if (Dump.Y) Dump.println(CN+"adjustWithStartSeq3 exit rectProfile="+Utils.toString(rectProfile));//~vayaI~
    }                                                              //~vayaI~
//***************************************************************  //~vaw0I~
//*for lanscape not long device                                    //~vaw0I~//~vayaR~//~vayeR~
//*chk Your positioning tile and ProfileIcon of the Right          //~vaw0I~
//*NOT Used                                                        //~vayeI~
//***************************************************************  //~vaw0I~
	private int adjustWithRiverBeforeMoved(Rect[] PrectProfile)    //~vaw0I~
    {                                                              //~vaw0I~
    	if (Dump.Y) Dump.println(CN+"adjustWithRiverBeforeMoved rectProfile="+Utils.toString(PrectProfile));//~vaw0I~//~vay8R~
	    int shrinkH=0;
        Rect rectProfileRight=PrectProfile[PLAYER_RIGHT];          //~vaw0I~
	    Rect rectPositioningTile=AG.aRiver.getRectSetupAccept(PLAYER_YOU);       //~vaw0I~
        int overwrap=rectPositioningTile.right+6/*gap*/-rectProfileRight.left;//~vaw0R~
        if (overwrap>0)                                            //~vaw0I~
        {                                                          //~vaw0I~
            shrinkH=overwrap;                                  //~vaw0I~
//          int shrinkW=(int)((double)shrinkH*(rectProfileRight.right-rectProfileRight.left)/(rectProfileRight.bottom-rectProfileRight.top));//~vaw0I~//~vay8R~
            int shrinkW=(int)((double)shrinkH*PROFILE_HWRATE); //=0.618;	//2/(1+root-5)//~vay8I~
    		if (Dump.Y) Dump.println("ProfileIcon.adjustWithRiverBeforeMoved overWrap H="+shrinkH+",W="+shrinkW);//~vaw0I~//~vay8R~
            if (rectProfileRight.left+shrinkH>=rectProfileRight.right)//~vaw0I~
            	shrinkH=0;                                         //~vaw0I~
            else                                                   //~vaw0I~
            {                                                      //~vaw0I~
                PrectProfile[PLAYER_YOU   ].left   +=shrinkW;      //~vaw0I~
                PrectProfile[PLAYER_YOU   ].top    +=shrinkH;      //~vaw0I~
                PrectProfile[PLAYER_RIGHT ].left   +=shrinkH;      //~vaw0I~
                PrectProfile[PLAYER_RIGHT ].bottom -=shrinkW;      //~vaw0I~
                PrectProfile[PLAYER_FACING].bottom -=shrinkH;      //~vaw0I~
                PrectProfile[PLAYER_FACING].right  -=shrinkW;      //~vaw0I~
                PrectProfile[PLAYER_LEFT  ].top    +=shrinkW;      //~vaw0I~
                PrectProfile[PLAYER_LEFT  ].right  -=shrinkH;      //~vaw0I~
            }                                                      //~vaw0I~
        }                                                          //~vaw0I~
    	if (Dump.Y) Dump.println(CN+"adjustWithRiverBeforeMoved after end of setup rectProfile="+Utils.toString(PrectProfile));//~vaw0I~//~vay8R~//~vayeR~
    	if (Dump.Y) Dump.println(CN+"adjustWithRiverBeforeMoved rc=shrinkH="+shrinkH);//~vaw0R~//~vay8R~
        return shrinkH;                                            //~vaw0I~
    }                                                              //~vaw0I~
//***************************************************************  //~vay8I~
	private int getMaxOverwrapWithRiverNeighbor_OLD(Rect[] PrectProfile)//~vay8R~//~vayeR~
    {                                                              //~vay8I~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiverNeibor portrait="+AG.portrait+",rectProfile="+Utils.toString(PrectProfile));//~vay8I~//~vayeR~
        int margin=MIN_ICON_HEIGHT;  //for riich landd piece       //~vay8R~
        int overflow=0,ov;                                           //~vay8I~//~vayaR~
        Rect rectIcon,rectRiver;                                //~vay8R~//~vayaR~
    //*river:YOU and ICON:RIGHT                                    //~vay8I~
        rectIcon=PrectProfile[PLAYER_RIGHT];                    //~vay8I~//~vayaR~
        rectRiver=AG.aRiver.getRiverRect(PLAYER_YOU);              //~vay8I~
        ov=rectRiver.right+margin/*gap*/-rectIcon.left;         //~vay8R~//~vayaR~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiver ov RIGHT="+ov);//~vay8I~
        if (ov>0)	//head of right icon reach to river of you     //~vay8I~
        {                                                          //~vay8I~
        	if (rectIcon.bottom<rectRiver.top || rectIcon.top>rectRiver.bottom)//~vay8I~//~vayaR~
            {                                                      //~vay8I~
		    	if (Dump.Y) Dump.println(CN+"ov=0 by right profile above or bellow of YOU river");//~vay8I~
            	ov=0;	                                           //~vay8I~
            }                                                      //~vay8I~
	        overflow=ov;                                               //~vay8M~//~vayaI~
        }                                                          //~vay8I~
    //*river:RIGHT and ICON:FACE                                   //~vay8I~
        rectIcon=PrectProfile[PLAYER_FACING];                   //~vay8I~//~vayaR~
        rectRiver=AG.aRiver.getRiverRect(PLAYER_RIGHT);            //~vay8I~
        ov=rectIcon.bottom-(rectRiver.top-margin/*gap*/);       //~vay8R~//~vayaR~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiver ov FACE="+ov);//~vay8I~
        if (ov>0)	//head of face icon reach to river of right    //~vay8I~
        {                                                          //~vay8I~
        	if (rectIcon.right<rectRiver.left || rectIcon.left>rectRiver.right)//~vay8I~//~vayaR~
            {                                                      //~vay8I~
		    	if (Dump.Y) Dump.println(CN+"ov=0 by face profile left or right of RIGHT river");//~vay8I~
            	ov=0;                                              //~vay8I~
            }                                                      //~vay8I~
	        overflow=Math.max(ov,overflow);                            //~vay8I~//~vayaI~
        }                                                          //~vay8I~
    //*river:FACE and ICON:LEFT                                    //~vay8I~
        rectIcon=PrectProfile[PLAYER_LEFT];                     //~vay8I~//~vayaR~
        rectRiver=AG.aRiver.getRiverRect(PLAYER_FACING);           //~vay8I~
        ov=rectIcon.right-(rectRiver.left-margin/*gap*/);    //~vay8I~//~vayaR~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiver ov LEFT="+ov);//~vay8I~
        if (ov>0)	//head of left icon reach to river of face     //~vay8I~
        {                                                          //~vay8I~
        	if (rectIcon.top>rectRiver.bottom || rectIcon.bottom<rectRiver.top)//~vay8I~//~vayaR~
            {                                                      //~vay8I~
		    	if (Dump.Y) Dump.println(CN+"ov=0 by face profile left or right of RIGHT river");//~vay8I~
            	ov=0;                                              //~vay8I~
            }                                                      //~vay8I~
	        overflow=Math.max(ov,overflow);                            //~vay8I~//~vayaI~
        }                                                          //~vay8I~
    //*river:FACE and ICON:LEFT                                    //~vay8I~
        rectIcon=PrectProfile[PLAYER_YOU];                      //~vay8I~//~vayaR~
        rectRiver=AG.aRiver.getRiverRect(PLAYER_LEFT);             //~vay8I~
        ov=rectRiver.bottom+margin/*gap*/-rectIcon.top;         //~vay8R~//~vayaR~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiverNeighbor ov YOU="+ov);//~vay8R~
        if (ov>0)	//head of you icon reach to river of left      //~vay8I~
        {                                                          //~vay8I~
        	if (rectIcon.left>rectRiver.right || rectIcon.right<rectRiver.left)//~vay8I~//~vayaR~
            {                                                      //~vay8I~
		    	if (Dump.Y) Dump.println(CN+"ov=0 by face profile left or right of RIGHT river");//~vay8I~
            	ov=0;                                              //~vay8I~
            }                                                      //~vay8I~
	        overflow=Math.max(ov,overflow);                            //~vay8I~//~vayaI~
        }                                                          //~vay8I~
        int iconH=rectIcon.bottom-rectIcon.top; //icon of YOU//~vay8R~//~vayaR~
        if (overflow>iconH)                                        //~vay8I~
        	overflow=iconH-MIN_ICON_HEIGHT;                        //~vay8R~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiverNeighbor return overflow="+overflow+",margin="+margin+",iconH="+iconH);//~vay8R~//~vayaR~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiver exit rectProfile="+Utils.toString(PrectProfile));//~vayaR~
        return overflow;                                           //~vay8I~
    }                                                              //~vay8I~
//***************************************************************  //~vayeI~
//*profile icon and its left river                                 //~vayeI~
//***************************************************************  //~vayeI~
	private Point getMaxOverwrapWithRiverNeighbor(Rect[] PrectProfile)//~vayeI~
    {                                                              //~vayeI~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiverNeibor portrait="+AG.portrait+",rectProfile="+Utils.toString(PrectProfile));//~vayeI~
        int margin=2;    //riich piece land is considered in river rect//~vayeI~
        int ww,hh,minW,minH;
        Point newWH;//~vayeI~
        Rect rectIcon,rectRiver;                                   //~vayeI~
    //*************************                                    //~vayeI~
    //*ICON:YOU and river:LEFT                                     //~vayeI~
        rectIcon=PrectProfile[PLAYER_YOU];                         //~vayeI~
        rectRiver=AG.aRiver.getRiverRect(PLAYER_LEFT);             //~vayeI~
        ww=rectIcon.right-rectIcon.left; hh=rectIcon.bottom-rectIcon.top;//~vayeI~
        if (!(rectIcon.right<rectRiver.left-margin)) //NOT icon left of river//~vayeI~
        {                                                          //~vayeI~
	    	newWH=chkOverwrapNeighbor(PLAYER_YOU,rectRiver/*tgt*/,rectIcon,0,margin,false/*chkEarth*/);//~vayeI~
	        ww=newWH.x; hh=newWH.y;                                //~vayeR~
        }                                                          //~vayeI~
        minW=ww; minH=hh;                                          //~vayeI~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiverNeighbor ICON:YOU ww="+ww+",hh="+hh+",minW="+minW+",minH="+minH);//~vayeI~
    //*ICON:RIGHT and river:YOU                                    //~vayeI~
        rectIcon=PrectProfile[PLAYER_RIGHT];                       //~vayeI~
        rectRiver=AG.aRiver.getRiverRect(PLAYER_YOU);              //~vayeI~
        ww=rectIcon.bottom-rectIcon.top; hh=rectIcon.right-rectIcon.left;//~vayeI~
        if (!(rectIcon.top>rectRiver.bottom+margin)) //NOT icon bellow river bottom//~vayeI~
        {                                                          //~vayeI~
	    	newWH=chkOverwrapNeighbor(PLAYER_RIGHT,rectRiver/*tgt*/,rectIcon,0,margin,false/*chkEarth*/);//~vayeI~
	        ww=newWH.x; hh=newWH.y;                                //~vayeR~
        }                                                          //~vayeI~
        minW=Math.min(minW,ww); minH=Math.min(minH,hh);            //~vayeI~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiverNeighbor ICON:RIGHT ww="+ww+",hh="+hh+",minW="+minW+",minH="+minH);//~vayeI~
    //*ICON:FACE and river:RIGHT                                   //~vayeI~
        rectIcon=PrectProfile[PLAYER_FACING];                     //~vayeI~
        rectRiver=AG.aRiver.getRiverRect(PLAYER_RIGHT);            //~vayeR~
        ww=rectIcon.right-rectIcon.left; hh=rectIcon.bottom-rectIcon.top;//~vayeI~
        if (!(rectIcon.left>rectRiver.right+margin)) //NOT icon right of river//~vayeI~
        {                                                          //~vayeI~
	    	newWH=chkOverwrapNeighbor(PLAYER_FACING,rectRiver/*tgt*/,rectIcon,0,margin,false/*chkEarth*/);//~vayeR~
	        ww=newWH.x; hh=newWH.y;                                //~vayeR~
        }                                                          //~vayeI~
        minW=Math.min(minW,ww); minH=Math.min(minH,hh);            //~vayeI~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiverNeighbor ICON:FACE ww="+ww+",hh="+hh+",minW="+minW+",minH="+minH);//~vayeI~
    //*ICON:LEFT and river:FACE                                    //~vayeI~
        rectIcon=PrectProfile[PLAYER_LEFT];                        //~vayeI~
        rectRiver=AG.aRiver.getRiverRect(PLAYER_FACING);           //~vayeI~
        ww=rectIcon.bottom-rectIcon.top; hh=rectIcon.right-rectIcon.left;//~vayeI~
        if (!(rectIcon.bottom<rectRiver.top-margin)) //NOT icon above river top//~vayeR~
        {                                                          //~vayeI~
	    	newWH=chkOverwrapNeighbor(PLAYER_LEFT,rectRiver/*tgt*/,rectIcon,0,margin,false/*chkEarth*/);//~vayeR~
	        ww=newWH.x; hh=newWH.y;                                //~vayeR~
        }                                                          //~vayeI~
        minW=Math.min(minW,ww); minH=Math.min(minH,hh);            //~vayeI~
        Point p=new Point(minW,minH);                              //~vayeI~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithRiverNeighbor ICON:LEFT ww="+ww+",hh="+hh+",minW="+minW+",minH="+minH);//~vayeI~
        return p;                                                  //~vayeI~
    }                                                              //~vayeI~
//***************************************************************  //~vayaI~
	private int getMaxOverwrapWithStarterSeq(Rect[] PrectProfile,Rect[] PrectStarterSeq)//~vayaR~
    {                                                              //~vayaI~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithStarterSeq rectProfile="+Utils.toString(PrectProfile)+",rectStarterSeq="+Utils.toString(PrectStarterSeq));//~vayaR~
        int margin=MIN_ICON_HEIGHT;  //for riich landd piece       //~vayaI~
        int overflow,ov;                                           //~vayaI~
        Rect rectIcon,rectOver;                                    //~vayaR~
    //*river:YOU and ICON:RIGHT                                    //~vayaI~
        rectIcon=PrectProfile[PLAYER_RIGHT];                       //~vayaI~
        rectOver=PrectStarterSeq[PLAYER_YOU];                      //~vayaR~
        ov=rectOver.right+margin/*gap*/-rectIcon.left;             //~vayaR~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithStarterSeq ov RIGHT="+ov);//~vayaR~
        if (ov>0)	//head of right icon reach to river of you     //~vayaI~
        {                                                          //~vayaI~
        	if (rectIcon.bottom<rectOver.top || rectIcon.top>rectOver.bottom)//~vayaR~
            {                                                      //~vayaI~
		    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithStarterSeq ov=0 by right profile above or bellow of YOU river");//~vayaR~
            	ov=0;                                              //~vayaI~
            }                                                      //~vayaI~
        }                                                          //~vayaI~
        overflow=ov;                                               //~vayaI~
    //*river:RIGHT and ICON:FACE                                   //~vayaI~
        rectIcon=PrectProfile[PLAYER_FACING];                      //~vayaI~
        rectOver=PrectStarterSeq[PLAYER_RIGHT];                    //~vayaR~
        ov=rectIcon.bottom-(rectOver.top-margin/*gap*/);           //~vayaR~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithStarterSeq ov FACE="+ov);//~vayaR~
        if (ov>0)	//head of face icon reach to river of right    //~vayaI~
        {                                                          //~vayaI~
        	if (rectIcon.right<rectOver.left || rectIcon.left>rectOver.right)//~vayaR~
            {                                                      //~vayaI~
		    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithStarterSeq ov=0 by face profile left or right of RIGHT river");//~vayaR~
            	ov=0;                                              //~vayaI~
            }                                                      //~vayaI~
        }                                                          //~vayaI~
        overflow=Math.max(ov,overflow);                            //~vayaI~
    //*river:FACE and ICON:LEFT                                    //~vayaI~
        rectIcon=PrectProfile[PLAYER_LEFT];                        //~vayaI~
        rectOver=PrectStarterSeq[PLAYER_FACING];                   //~vayaI~
        ov=rectIcon.right-(rectOver.left-margin/*gap*/);           //~vayaR~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithStarterSeq ov LEFT="+ov);//~vayaR~
        if (ov>0)	//head of left icon reach to river of face     //~vayaI~
        {                                                          //~vayaI~
        	if (rectIcon.top>rectOver.bottom || rectIcon.bottom<rectOver.top)//~vayaR~
            {                                                      //~vayaI~
		    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithStarterSeq ov=0 by face profile left or right of RIGHT river");//~vayaR~
            	ov=0;                                              //~vayaI~
            }                                                      //~vayaI~
        }                                                          //~vayaI~
        overflow=Math.max(ov,overflow);                            //~vayaI~
    //*river:FACE and ICON:LEFT                                    //~vayaI~
        rectIcon=PrectProfile[PLAYER_YOU];                         //~vayaI~
        rectOver=PrectStarterSeq[PLAYER_LEFT];                     //~vayaI~
        ov=rectOver.bottom+margin/*gap*/-rectIcon.top;             //~vayaR~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithStarterSeq ov YOU="+ov);//~vayaR~
        if (ov>0)	//head of you icon reach to river of left      //~vayaI~
        {                                                          //~vayaI~
        	if (rectIcon.left>rectOver.right || rectIcon.right<rectOver.left)//~vayaR~
            {                                                      //~vayaI~
		    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithStarterSeq ov=0 by face profile left or right of RIGHT river");//~vayaR~
            	ov=0;                                              //~vayaI~
            }                                                      //~vayaI~
        }                                                          //~vayaI~
        overflow=Math.max(ov,overflow);                            //~vayaI~
        int iconH=rectIcon.bottom-rectIcon.top; //icon of YOU      //~vayaI~
        if (overflow>iconH)                                        //~vayaI~
        	overflow=iconH-MIN_ICON_HEIGHT;                        //~vayaI~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithStarterSeq exit overflow="+overflow+",margin="+margin+",iconH="+iconH);//~vayaR~
    	if (Dump.Y) Dump.println(CN+"getMaxOverwrapWithStarterSeq exit rectProfile="+Utils.toString(PrectProfile));//~vayaR~
        return overflow;                                           //~vayaI~
    }                                                              //~vayaI~
////***************************************************************//~vayaR~
////*not used                                                      //~vayaR~
////***************************************************************//~vayaR~
//    private void adjustWithStarter(Rect[] PrectProfile)          //~vayaR~
//    {                                                            //~vayaR~
//        if (Dump.Y) Dump.println(CN+"adjustWithStarter swLongDevice="+AG.swLongDevice+",portrait="+AG.portrait+",in rectProfile="+Utils.toString(PrectProfile));//~vayaR~
//        if (!(AG.swLongDevice || AG.portrait)) //not case of prifile on nameplate//~vayaR~
//        {                                                        //~vayaR~
//            if (Dump.Y) Dump.println(CN+"adjustWithStarter not profile on nameplate");//~vayaR~
//            return;                                              //~vayaR~
//        }                                                        //~vayaR~
//        if (rectStarter!=null)                                   //~vayaR~
//        {                                                        //~vayaR~
//            if (Dump.Y) Dump.println(CN+"adjustWithStarter alredy adjusted rectStarter="+Utils.toString(rectStarter));//~vayaR~
//            return;                                              //~vayaR~
//        }                                                        //~vayaR~
////      AG.aStarter.getStarterSeqRect();                         //~vayaR~
//        double adjustRate=ADJUST_RATE_STARTER;                   //~vayaR~
//        Rect rect; int hh,ww;                                    //~vayaR~
//    //*ICON YOU                                                  //~vayaR~
//        rect=PrectProfile[PLAYER_YOU];                           //~vayaR~
//        hh=rect.bottom-rect.top;                                 //~vayaR~
//        hh=(int)(hh*(1-adjustRate));                             //~vayaR~
//        ww=(int)(hh*PROFILE_HWRATE); //=0.618;  //2/(1+root-5)   //~vayaR~
//        rect.top+=hh; rect.left+=ww;                             //~vayaR~
//    //*ICON RIGHT                                                //~vayaR~
//        rect=PrectProfile[PLAYER_RIGHT];                         //~vayaR~
//        hh=rect.right-rect.left;                                 //~vayaR~
//        hh=(int)(hh*(1-adjustRate));                             //~vayaR~
//        ww=(int)(hh*PROFILE_HWRATE); //=0.618;  //2/(1+root-5)   //~vayaR~
//        rect.left+=hh; rect.bottom-=ww;                          //~vayaR~
//    //*ICON FACE                                                 //~vayaR~
//        rect=PrectProfile[PLAYER_FACING];                        //~vayaR~
//        hh=rect.bottom-rect.top;                                 //~vayaR~
//        hh=(int)(hh*(1-adjustRate));                             //~vayaR~
//        ww=(int)(hh*PROFILE_HWRATE); //=0.618;  //2/(1+root-5)   //~vayaR~
//        rect.bottom-=hh; rect.right-=ww;                         //~vayaR~
//    //*ICON LEFT                                                 //~vayaR~
//        rect=PrectProfile[PLAYER_LEFT];                          //~vayaR~
//        hh=rect.right-rect.left;                                 //~vayaR~
//        hh=(int)(hh*(1-adjustRate));                             //~vayaR~
//        ww=(int)(hh*PROFILE_HWRATE); //=0.618;  //2/(1+root-5)   //~vayaR~
//        rect.right-=hh; rect.top+=ww;                            //~vayaR~
//        if (Dump.Y) Dump.println(CN+"adjustWithStarter out rectProfile="+Utils.toString(PrectProfile));//~vayaR~
//    }                                                            //~vayaR~
//***************************************************************  //~vay8R~
//*adjust with neighbor stock                                      //~vay8R~
//***************************************************************  //~vay8R~
	private void adjustWithStock_OLD(Rect[] PrectProfile)              //~vay8R~//~vaydR~
    {                                                              //~vay8R~
    	if (Dump.Y) Dump.println(CN+"adjustWithStock PrectProfile="+Utils.toString(PrectProfile));//~vay8R~
        int ovW,ovH,ovW2,maxH,maxW,shrinkW,shrinkH;                //~vay8R~
        Rect rectStock,rectProfile;                                //~vay8R~
        int ww,hh;                                                 //~vayaI~
	    int margin=PROFILE_FRAME_WIDTH;	// =2;                     //~vayaI~
        int profW=PrectProfile[PLAYER_YOU].right-PrectProfile[PLAYER_YOU].left;	//ww of profile YOU//~vay8M~//~vayaI~
        int profH=PrectProfile[PLAYER_YOU].bottom-PrectProfile[PLAYER_YOU].top;	//hh of profile YOU//~vay8I~//~vayaI~
    //*stock=YOU,profile=RIGHT                                     //~vay8R~
        rectStock=AG.aStock.rectsBG[PLAYER_YOU];                   //~vay8R~
        rectProfile=PrectProfile[PLAYER_RIGHT];                    //~vay8R~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock rectProfile[RIGHT]="+rectProfile+",rectStock[YOU]="+rectStock);//~vay8R~
        ovW=rectProfile.bottom-rectStock.top;                      //~vay8R~
        ovW2=rectProfile.bottom-rectStock.bottom;  //to earthh     //~vay8I~
        ovH=rectStock.right-rectProfile.left;                      //~vay8R~
        maxH=0; maxW=0;                                            //~vay8I~
        shrinkH=0; shrinkW=0;                                      //~vay8I~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock Profile RIGHT ovW="+ovW+",OvW2="+ovW2+",ovH="+ovH);//~vay8R~
        if (ovH>0)                                                 //~vay8I~
        {                                                          //~vay8I~
        	if (ovW>0)                                             //~vay8I~
            {                                                      //~vayaI~
            	ww=profW-ovW-margin;                               //~vayaI~
                hh=(int)(ww/PROFILE_HWRATE); //=0.618;	//2/(1+root-5)//~vayaI~
	        	shrinkH=Math.min(ovH,profH-hh);                    //~vayaR~
			    if (Dump.Y) Dump.println(CN+"adjustWithStock ovH="+ovH+",ovW="+ovW+",ww="+ww+",hh="+hh+",shrinkH="+shrinkH);//~vayaI~
            }                                                      //~vayaI~
        }                                                          //~vay8I~
        if (ovW2>0)                                                //~vay8R~
        	shrinkW=ovW2;                                      //~vay8R~//~vayaR~
        maxW=Math.max(maxW,shrinkW);                               //~vayaI~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock Profile shrinkW="+shrinkW+",shrinkH="+shrinkH);//~vay8I~
    //*stock=RIGHT,profile=FACE                                    //~vay8R~
        shrinkH=0; shrinkW=0;                                      //~vay8I~
        rectStock=AG.aStock.rectsBG[PLAYER_RIGHT];                 //~vay8R~
        rectProfile=PrectProfile[PLAYER_FACING];                   //~vay8R~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock rectProfile[FACE]="+rectProfile+",rectStock[RIGHT]="+rectStock);//~vay8R~
        ovW=rectProfile.right-rectStock.left;                      //~vay8R~
        ovW2=rectProfile.right-rectStock.right;                    //~vay8I~
        ovH=rectProfile.bottom-rectStock.top;                      //~vay8R~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock Profile FACE ovW="+ovW+",ovW2="+ovW2+",ovH="+ovH);//~vay8R~
        if (ovH>0)                                                 //~vay8I~
        {                                                          //~vay8I~
        	if (ovW>0)                                             //~vay8I~
            {                                                      //~vayaI~
            	ww=profW-ovW-margin;                               //~vayaI~
                hh=(int)(ww/PROFILE_HWRATE); //=0.618;	//2/(1+root-5)//~vayaI~
	        	shrinkH=Math.min(ovH,profH-hh);                    //~vayaR~
			    if (Dump.Y) Dump.println(CN+"adjustWithStock ovH="+ovH+",ovW="+ovW+",ww="+ww+",hh="+hh+",shrinkH="+shrinkH);//~vayaI~
	        }                                                      //~vayaR~
        }                                                          //~vay8I~
        if (ovW2>0)                                                //~vay8I~
        	shrinkW=ovW2;                                      //~vay8I~//~vayaR~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock Profile shrinkW="+shrinkW+",shrinkH="+shrinkH);//~vay8I~
        maxH=Math.max(maxH,shrinkH);                               //~vay8R~
        maxW=Math.max(maxW,shrinkW);                               //~vay8I~
    //*stock=FACE,profile=LEFT                                     //~vay8R~
        shrinkH=0; shrinkW=0;                                      //~vay8I~
        rectStock=AG.aStock.rectsBG[PLAYER_FACING];                //~vay8R~
        rectProfile=PrectProfile[PLAYER_LEFT];                     //~vay8R~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock rectProfile[LEFT]="+rectProfile+",rectStock[FACE]="+rectStock);//~vay8R~
        ovW=rectStock.bottom-rectProfile.top;                      //~vay8R~
        ovW2=rectStock.top-rectProfile.top;                        //~vay8I~
        ovH=rectProfile.right-rectStock.left;                      //~vay8R~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock Profile LEFT ovW="+ovW+",ovW2="+ovW2+",ovH="+ovH);//~vay8R~
        if (ovH>0)                                                 //~vay8I~
        {                                                          //~vay8I~
        	if (ovW>0)                                             //~vay8I~
            {                                                      //~vayaI~
            	ww=profW-ovW-margin;                               //~vayaI~
                hh=(int)(ww/PROFILE_HWRATE); //=0.618;	//2/(1+root-5)//~vayaI~
	        	shrinkH=Math.min(ovH,profH-hh);                    //~vayaR~
			    if (Dump.Y) Dump.println(CN+"adjustWithStock ovH="+ovH+",ovW="+ovW+",ww="+ww+",hh="+hh+",shrinkH="+shrinkH);//~vayaI~
	        }                                                      //~vayaI~
        }                                                          //~vay8I~
        if (ovW2>0)                                                //~vay8I~
        	shrinkW=ovW2;                                      //~vay8I~//~vayaR~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock Profile shrinkW="+shrinkW+",shrinkH="+shrinkH);//~vay8I~
        maxH=Math.max(maxH,shrinkH);                               //~vay8I~
        maxW=Math.max(maxW,shrinkW);                               //~vay8I~
    //*stock=LEFT,profile=YOU                                      //~vay8R~
        shrinkH=0; shrinkW=0;                                      //~vay8I~
        rectStock=AG.aStock.rectsBG[PLAYER_LEFT];                  //~vay8R~
        rectProfile=PrectProfile[PLAYER_YOU];                      //~vay8R~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock rectProfile[YOU]="+rectProfile+",rectStock[LEFT]="+rectStock);//~vay8R~
        ovW=rectStock.right-rectProfile.left;                      //~vay8R~
        ovW2=rectStock.left-rectProfile.left;                      //~vay8I~
        ovH=rectStock.bottom-rectProfile.top;                      //~vay8R~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock Profile YOU ovW="+ovW+",ovW2="+ovW2+",ovH="+ovH);//~vay8R~
        if (ovH>0)                                                 //~vay8I~
        {                                                          //~vay8I~
        	if (ovW>0)                                             //~vay8I~
            {                                                      //~vayaI~
            	ww=profW-ovW-margin;                               //~vayaI~
                hh=(int)(ww/PROFILE_HWRATE); //=0.618;	//2/(1+root-5)//~vayaI~
	        	shrinkH=Math.min(ovH,profH-hh);                    //~vayaR~
			    if (Dump.Y) Dump.println(CN+"adjustWithStock ovH="+ovH+",ovW="+ovW+",ww="+ww+",hh="+hh+",shrinkH="+shrinkH);//~vayaI~
	        }                                                      //~vayaI~
        }                                                          //~vay8I~
        if (ovW2>0)                                                //~vay8I~
        	shrinkW=ovW2;                                      //~vay8I~//~vayaR~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock Profile shrinkW="+shrinkW+",shrinkH="+shrinkH);//~vay8I~
        maxH=Math.max(maxH,shrinkH);                               //~vay8I~
        maxW=Math.max(maxW,shrinkW);                               //~vay8I~
                                                                   //~vay8I~
	    if (Dump.Y) Dump.println(CN+"adjustWithStock profH="+profH+",profW="+profW+",maxShrinkH="+maxH+",maxShrinkW="+maxW);//~vay8M~
        int h1=0,w1=0;                                             //~vay8I~
        if (maxH>0)   //shrink height                              //~vay8I~
        {                                                          //~vay8I~
        	h1=profH-maxH;           //new height                  //~vay8I~
            w1=(int)(h1*PROFILE_HWRATE); //=0.618;	//2/(1+root-5)//~vay8I~
            if (w1>profW-maxW)                                     //~vay8I~
            {                                                      //~vay8I~
            	w1=profW-maxW;                                     //~vay8I~
                h1=(int)(w1/PROFILE_HWRATE); //=0.618;	//2/(1+root-5)//~vay8I~
            }                                                      //~vay8I~
        }                                                          //~vay8I~
        else                                                       //~vay8I~
        if (maxW>0)                                                //~vay8I~
        {                                                          //~vay8I~
            w1=profW-maxW;          //new width                    //~vay8I~
            h1=(int)(w1/PROFILE_HWRATE); //=0.618;	//2/(1+root-5) //~vay8I~
        }                                                          //~vay8I~
        shrinkW=0; shrinkH=0;                                      //~vay8R~
        if (w1>0)                                                  //~vay8I~
        	shrinkW=profW-w1;                                      //~vay8I~
        if (h1>0)                                                  //~vay8I~
        	shrinkH=profH-h1;                                      //~vay8I~
    	if (Dump.Y) Dump.println(CN+"adjustWithStock shrinkW="+shrinkW+",shrinkH="+shrinkH+",w1="+w1+",h1="+h1);//~vay8R~
        if (shrinkW>0 || shrinkW >0);                              //~vay8R~
        {                                                          //~vay8R~
            PrectProfile[PLAYER_YOU   ].left   +=shrinkW;          //~vay8R~
            PrectProfile[PLAYER_YOU   ].top    +=shrinkH;          //~vay8R~
            PrectProfile[PLAYER_RIGHT ].left   +=shrinkH;          //~vay8R~
            PrectProfile[PLAYER_RIGHT ].bottom -=shrinkW;          //~vay8R~
            PrectProfile[PLAYER_FACING].bottom -=shrinkH;          //~vay8R~
            PrectProfile[PLAYER_FACING].right  -=shrinkW;          //~vay8R~
            PrectProfile[PLAYER_LEFT  ].top    +=shrinkW;          //~vay8R~
            PrectProfile[PLAYER_LEFT  ].right  -=shrinkH;          //~vay8R~
        }                                                          //~vay8R~
    	if (Dump.Y) Dump.println(CN+"adjustWithStock rectProfile="+Utils.toString(PrectProfile));//~vay8R~
    }                                                              //~vay8R~
//***************************************************************  //~vaydI~
//*adjust with neighbor stock after Move, port and landscape       //~vaydI~
//***************************************************************  //~vaydI~
	private void adjustWithStock(Rect[] Prect/*rectProfile*/)       //~vaydR~
    {                                                              //~vaydI~
        Rect rectSrc;                                              //~vaydR~
        Rect[] stocks;                                             //~vaydI~
        int margin=PROFILE_MARGIN; // missing score box when before move//~vaydI~
        int minW,minH;                                             //~vaydR~
        Point newWH1;                                              //~vaydR~
        //***************************                              //~vaydI~
    	if (Dump.Y) Dump.println(CN+"adjustWithStock swLongDevice="+AG.swLongDevice+",PrectProfile="+Utils.toString(Prect));//~vaydR~//~vayeR~
    	if (Dump.Y) Dump.println(CN+"adjustWithStock rectProfile="+Utils.toString(Prect));//~vaydR~
        stocks=AG.aStock.rectsBG;                                  //~vaydI~
        if (Dump.Y) Dump.println(CN+"adjustWithStock2 stocks="+Utils.toString(stocks));//~vaydR~
     //*profile:you stock:left                                     //~vaydR~
        rectSrc=Prect[PLAYER_YOU];                                 //~vaydI~
	    newWH1=chkOverwrapNeighbor(PLAYER_YOU,stocks[PLAYER_LEFT],rectSrc,0/*wwPiece for beforeMove*/,margin,true/*chkEarth*/);//~vaydI~//~vayeR~
        minW=newWH1.x;                                             //~vaydR~
        minH=newWH1.y;                                             //~vaydI~
     //*profile:right stock:you                                    //~vaydI~
        rectSrc=Prect[PLAYER_RIGHT];                               //~vaydI~
	    newWH1=chkOverwrapNeighbor(PLAYER_RIGHT,stocks[PLAYER_YOU],rectSrc,0/*wwPiece for beforeMove*/,margin,true/*chkEarth*/);//~vaydR~//~vayeR~
        minW=Math.min(minW,newWH1.x);                              //~vaydR~
        minH=Math.min(minH,newWH1.y);                              //~vaydR~
     //*profile:face stock:right                                   //~vaydI~
        rectSrc=Prect[PLAYER_FACING];                              //~vaydI~
	    newWH1=chkOverwrapNeighbor(PLAYER_FACING,stocks[PLAYER_RIGHT],rectSrc,0/*wwPiece for beforeMove*/,margin,true/*chkEarth*/);//~vaydR~//~vayeR~
        minW=Math.min(minW,newWH1.x);                              //~vaydR~
        minH=Math.min(minH,newWH1.y);                              //~vaydR~
     //*profile:left stock:face                                    //~vaydI~
        rectSrc=Prect[PLAYER_LEFT];                                //~vaydI~
	    newWH1=chkOverwrapNeighbor(PLAYER_LEFT,stocks[PLAYER_FACING],rectSrc,0/*wwPiece for beforeMove*/,margin,true/*chkEarth*/);//~vaydR~//~vayeR~
        minW=Math.min(minW,newWH1.x);                              //~vaydR~
        minH=Math.min(minH,newWH1.y);                              //~vaydR~
    	if (Dump.Y) Dump.println(CN+"adjustWithStock minW="+minW+",minH="+minH);//~vaydR~
        //*you                                                     //~vaydI~
            rectSrc=Prect[PLAYER_YOU];                             //~vaydR~
//          rectSrc=new Rect(rectSrc.right-minW, rectSrc.bottom-minH, rectSrc.right, rectSrc.bottom);//~vaydR~
            rectSrc.left=rectSrc.right-minW;                       //~vaydI~
            rectSrc.top=rectSrc.bottom-minH;                       //~vaydI~
        //*right                                                   //~vaydI~
            rectSrc=Prect[PLAYER_RIGHT];                           //~vaydR~
//          rectSrc=new Rect(rectSrc.right-minH, rectSrc.top, rectSrc.right, rectSrc.top+minW);//~vaydR~
            rectSrc.left=rectSrc.right-minH;                       //~vaydI~
            rectSrc.bottom=rectSrc.top+minW;                       //~vaydI~
        //*facing                                                  //~vaydI~
            rectSrc=Prect[PLAYER_FACING];                          //~vaydR~
//          rectSrc[PLAYER_FACING]=new Rect(rectSrc.left, rectSrc.top, rectSrc.left+minW,rectSrc.top+minH);//~vaydR~
            rectSrc.right=rectSrc.left+minW;                       //~vaydI~
            rectSrc.bottom=rectSrc.top+minH;                       //~vaydI~
        //*left                                                    //~vaydI~
            rectSrc=Prect[PLAYER_LEFT];                            //~vaydR~
//          rectSrc[PLAYER_LEFT]=new Rect(rectSrc.left, rectSrc.bottom-minW, rectSrc.left+minH, rectSrc.bottom);//~vaydR~
            rectSrc.top=rectSrc.bottom-minW;                       //~vaydI~
            rectSrc.right=rectSrc.left+minH;                       //~vaydI~
    	if (Dump.Y) Dump.println(CN+"adjustWithStock exit rect="+Utils.toString(Prect));//~vaydR~
    }                                                              //~vaydI~
    //***************************************                      //~vaz1I~
    public boolean getWinner(int Pplayer,Rect Prect,Bitmap[] Pbitmaps)//~vaz1I~
    {                                                              //~vaz1I~
    	if (Dump.Y) Dump.println(CN+"getWinner Pplayer="+Pplayer+",swShowProfile="+swShowProfile);//~vaz1I~
        if (!swShowProfile)                                        //~vaz1I~
        	return false;                                                 //~vaz1I~
    	if (Dump.Y) Dump.println(CN+"getWinner rects="+rectProfile);//~vaz1I~
        Prect.set(rectProfile[Pplayer]);                           //~vaz1I~
    	if (Dump.Y) Dump.println(CN+"getWinner bmps="+bmpCurrent); //~vaz1I~
        Bitmap bmp=bmpCurrent[Pplayer];                                 //~vaz1I~
        if (bmp==null)                                              //~vaz1I~
        {                                                          //~vaz1I~
	    	if (Dump.Y) Dump.println(CN+"getWinner return false by bmp=null");//~vaz1I~
        	return false;                                          //~vaz1I~
        }                                                          //~vaz1I~
        Pbitmaps[0]=bmp;                                           //~vaz1I~
	    if (Dump.Y) Dump.println(CN+"getWinner return true bmp="+bmp);//~vaz1I~
        return true;                                               //~vaz1I~
    }                                                              //~vaz1I~
}//class ProfileIcon
