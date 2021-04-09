//*CID://+va79R~: update#= 290;                                    //~va79R~
//**********************************************************************
//2021/04/05 va79 (Bug)hung when ron at take without ronable       //~va79I~
//2021/01/07 va60 CalcShanten
//**********************************************************************
package com.btmtest.game.RA;
import android.graphics.Point;

import com.btmtest.TestOption;
import com.btmtest.game.Accounts;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;
import static com.btmtest.TestOption.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
import static com.btmtest.game.Tiles.*;

//********************************************************************************************
public class RADSmart
{
	private RoundStat RS;
	private RoundStat.RSPlayer RSP;
	private RADiscard RAD;
	private RADSEval RADSE;
	public  RADSOther RADSO;
    public  TileData[] tdsHand;
    private int ctrHand;
    private int[] itsHand,itsExposed;	//34 entry
//  private boolean[] btsNonDiscardable=new boolean[CTR_TILETYPE];	//34 entry//~1121R~
    public  int[] itsStatHand,itsStatPair;                         //~1121R~
    public  int[] itsHandValue=new int[HANDCTR_TAKEN];
//    private int[] itsPosShanten0Discard= new int[HANDCTR_TAKEN];
//    private int[] itsHanShanten0Discard= new int[HANDCTR_TAKEN];
//    private int ctrPosShanten0Discard;
    private int[] itsHandNumberStat=new int[HANDCTR_TAKEN];
    private int[] itsMinShantenTile=new int[HANDCTR_TAKEN];
    public  int[] itsHandPos=new int[HANDCTR_TAKEN];               //~1122R~
    private int[] itsReachValue=new int[HANDCTR_TAKEN];            //~1122I~
//  private int myCtrPair,myStatPair;                              //~1121R~
    public  int playerDiscard;                                     //~1121R~
    private int eswnDiscard,posTaken;
    public  int[] itsDoraOpen=new int[2*(1+MAXCTR_KAN)];           //~1217R~
    public  int   ctrDoraOpen;                                     //~1215I~//~1217R~
    public  int myIntent;
    public  int myShanten;                                         //~1201R~
//  private int ctrMan,ctrPin,ctrSou,ctrWord,ctrAll;               //~1215R~
    private boolean[] btsWin=new boolean[CTR_TILETYPE];  //evaluate wait expecting value
    private boolean[] btsWinMerge=new boolean[CTR_TILETYPE];  //evaluate wait expecting value
    public	boolean swDoReach;                                     //~1122I~
    public	int hanMaxReach;                                       //~1122I~
    private boolean swDiscardedSmart;                              //~1201I~
//*************************
	public RADSmart()
    {
        if (Dump.Y) Dump.println("RADSmart.Constructor");
        AG.aRADSmart=this;
        RADSO=new RADSOther();                                     //~1122M~
        RADSE=new RADSEval();
        init();
    }
    //*********************************************************
    private void  init()
    {
    	RAD=AG.aRADiscard;
    	RS=AG.aRoundStat;
    }
    //*********************************************************    //~1201I~
    //*from RACAll at discard done                                  //~1201I~//~1205R~
    //*set currentShanten and Intent                               //~1201I~
    //*********************************************************    //~1201I~
    public void discardedSmart(int Peswn)                          //~1201I~
    {                                                              //~1201I~
        if (Dump.Y) Dump.println("RADSmart.discardedSmart eswn="+Peswn);//~1201I~
        swDiscardedSmart=true;                                     //~1201I~
    	selectSmart(Peswn,null);                                   //~1201I~
        swDiscardedSmart=false;                                    //~1201I~
        if (Dump.Y) Dump.println("RADSmart.return");               //~1201I~
    }                                                              //~1201I~
    //*********************************************************
    //*from RADiscard
    //*13/14-NoPair	Robot pass it                                  //~1124I~
    //*99TileDraw  Robot pass it                                   //~1124I~
    //*PtdTaken==null when discard at Pon/Chii                     //~1126I~
    //*********************************************************
    public  TileData selectSmart(int Peswn,TileData PtdTaken)
    {
        TileData tdDiscard=null;
        //********************
        if (Dump.Y) Dump.println("RADSmart.selectSmart eswn="+Peswn+",PtdTaken="+Utils.toString(PtdTaken)+",swDiscardedSmart="+swDiscardedSmart);//~1126I~//~1205R~
        eswnDiscard=Peswn;
        RSP=RS.RSP[Peswn];
//      if ((RSP.callStatus & CALLSTAT_REACH)!=0) //already reach issued//~1219I~//~1221R~
//      {                                                          //~1219I~//~1221R~
//          if (Dump.Y) Dump.println("RADSmart.selectSmart after reach eswn="+Peswn+",PtdTaken="+Utils.toString(PtdTaken));//~1219I~//~1221R~
//      	return PtdTaken;                                       //~1219I~//~1221R~
//      }                                                          //~1219I~//~1221R~
//      playerDiscard=RAD.playerDiscard;                           //~1201R~
        playerDiscard= Accounts.eswnToPlayer(Peswn);               //~1201I~
//      itsHand=RAD.itsHand;	//34 entry                         //~1201R~
        itsHand=RS.getItsHandEswn(Peswn);   //including taken      //~1201I~
//      tdsHand=RAD.tdsHand;    //max 14                           //~1201R~
        tdsHand=AG.aPlayers.getHands(playerDiscard);               //~1201I~
        ctrHand=RAUtils.getTilesPos(tdsHand,itsHandPos);	//max 14 entry pos of hand//~1122I~
        itsExposed=RS.itsExposed;
        if (swDiscardedSmart)                                      //~1201I~
        {                                                          //~1201I~
        	myShanten=getShanten(true/*swIntent*/,ctrHand);	//save myIntent to RSP//~1201I~
    		RS.setCurrentShanten(Peswn,myShanten);                 //~1201I~
        }                                                          //~1201I~
        else                                                       //~1201I~
        {                                                          //~1201I~
            Arrays.fill(itsHandValue,DV_BASE);                         //~1122M~//~1201R~
            if (PtdTaken==null)                                        //~1126I~//~1201R~
                tdDiscard=selectSmartPonChii(Peswn);                   //~1126I~//~1201R~
            else                                                       //~1126I~//~1201R~
                tdDiscard=selectSmartTaken(Peswn,PtdTaken);            //~1126I~//~1201R~
            if (Dump.Y) Dump.println("RADSmart.selectSmart tdDiscard="+TileData.toString(tdDiscard));//~1126R~//~1201R~
        }                                                          //~1201I~
        itsHand=null;	//for gc                                   //~1126I~
        tdsHand=null;                                              //~1126I~
        itsExposed=null;                                           //~1126I~
        return tdDiscard;
    }
    //*********************************************************    //~1305I~
    private int getIdxHandPos(int Ppos)                            //~1305I~
    {                                                              //~1305I~
        int idx=-1;                                                //~1305I~
        for (int ii=0;ii<ctrHand;ii++)                             //~1305I~
        {                                                          //~1305I~
        	if (Ppos==itsHandPos[ii])                               //~1305I~
            {                                                      //~1305I~
  				idx=ii;                                            //~1305I~
                break;                                             //~1305I~
            }                                                      //~1305I~
        }                                                          //~1305I~
        if (Dump.Y) Dump.println("RADSmart.getIdxHandPos pos="+Ppos+",idx="+idx+",itsHandPos="+Utils.toStringMax(itsHandPos,ctrHand));//~1305I~
        return idx;
    }                                                              //~1305I~
    //*********************************************************    //~1126I~
    private TileData selectSmartTaken(int Peswn,TileData PtdTaken) //~1126R~
    {                                                              //~1126I~
        TileData tdDiscard=null;                                   //~1126I~
        //********************                                     //~1126I~
        if (Dump.Y) Dump.println("RADSmart.selectSmartTaken eswn="+Peswn+",PtdTaken="+Utils.toString(PtdTaken));//~1126I~
        swDoReach=false;                                           //~1126I~
        posTaken=RAUtils.getPosTile(PtdTaken);                     //~1126I~
        if ((RSP.callStatus & CALLSTAT_REACH)!=0) //reach called   //~1126I~
        {                                                          //~1126I~
        	myShanten=getShanten(false/*swIntent*/,ctrHand);  //set also myintent//~1206I~
            if (myShanten==-1)	//                                 //~1206I~
            {                                                      //~1206I~
        		if (callRonTaken(itsHand,PtdTaken))                //~1206I~
            	{                                                  //~1206I~
		        	if (Dump.Y) Dump.println("RADSmart.selectSmartTaken under reach return null after Ron issued tdTaken="+PtdTaken.toString());//~1206I~//~va79R~
            		return null;                                   //~1206I~
            	}                                                  //~1206I~
            }                                                      //~1206I~
	        if (callKanTaken(true/*reach*/,PtdTaken,posTaken))     //~1126I~
            {                                                      //~1126I~
		        if (Dump.Y) Dump.println("RADSmart.selectSmartTaken return KanTaken under reach to take rinshan tdTaken="+PtdTaken.toString());//~1126I~
                return null;                                       //~1126I~
            }                                                      //~1126I~
		    if (Dump.Y) Dump.println("RADSmart.selectSmartTaken return taken because after reach tdDiscard="+PtdTaken.toString());//~1126I~//~1218R~
            return PtdTaken;                                   //~1126I~//~1218R~
        }                                                          //~1126I~
        myShanten=getShanten(true/*swIntent*/,ctrHand);	//output also myIntent;//~1201R~
        if (myShanten==-1)                                         //~1126I~
        {                                                          //~1126I~
        	if (callRonTaken(itsHand,PtdTaken))                    //~1126I~
            {                                                      //~1126I~
		        if (Dump.Y) Dump.println("RADSmart.selectSmartTaken return null after Ron issued tdTaken="+PtdTaken.toString());//~1126I~//~1405R~
            	return null;                                       //~1126I~
            }                                                      //~1126I~
        }                                                          //~1126I~
//      if (callKanTaken(false/*reach*/,PtdTaken,posTaken))        //~1126I~//~1222R~
//      {                                                          //~1126I~//~1222R~
//  		if (Dump.Y) Dump.println("RADSmart.selectSmartTaken return by KanTaken not under Reach to take Rinshan tdTaken="+PtdTaken.toString());//~1126I~//~1222R~
//          return null;                                           //~1126I~//~1222R~
//      }                                                          //~1126I~//~1222R~
	    if (myShanten==0 && (RSP.callStatus & CALLSTAT_REACH)==0) //reach not called//~1126I~
    	    swDoReach=callReach();  //not skip reach               //~1126I~//~1309R~
     	if (swDoReach)                                             //~1309I~
        {
            tdDiscard = selectDiscardReach(myShanten);    //chk otherPlayer//~1309R~
            if (tdDiscard==null)                                   //~1309I~
            	swDoReach=false;                                   //~1309R~
        }
      if (!swDoReach)                                         //~1309I~
	    tdDiscard=selectDiscard(myShanten);                                   //~1126I~//~1127R~//~1201R~
        if (tdDiscard==null)                                       //~1126I~
        	tdDiscard=PtdTaken;                                    //~1126I~
        if (callKanTaken(false/*reach*/,PtdTaken,posTaken))  //not take but discard timing for selected tile//~1222I~
        {                                                          //~1222I~
    		if (Dump.Y) Dump.println("RADSmart.selectSmartTaken return by KanTaken not under Reach to take Rinshan tdTaken="+PtdTaken.toString());//~1222I~
            return null;                                           //~1222I~
        }                                                          //~1222I~
        if (swDoReach)                                             //~1126I~
            callReach(tdDiscard);	//issue Reach                  //~1126I~
        if (Dump.Y) Dump.println("RADSmart.selectSmartTaken tdDiscard="+tdDiscard.toString());//~1126I~
        return tdDiscard;                                          //~1126I~
    }                                                              //~1126I~
    //*********************************************************    //~1126I~
    private TileData selectSmartPonChii(int Peswn)                 //~1126I~
    {                                                              //~1126I~
        TileData tdDiscard=null;                                   //~1126I~
        //********************                                     //~1126I~
        if (Dump.Y) Dump.println("RADSmart.selectSmartPonChii eswn="+Peswn);//~1126R~
        swDoReach=false;                                           //~1126I~
        posTaken=RAUtils.getPosTile(tdsHand[0]);    //may not used //~1126R~
        myShanten=getShanten(true/*swIntent*/,ctrHand);  //set also myintent          //~1126I~//~1201R~
                                                                   //~1126I~
        setNonDiscardablePonChii();	//kuikae                       //~1305I~
	    tdDiscard=selectDiscard(myShanten);                          //~1126I~//~1127R~//~1201R~
                                                                   //~1126I~
        if (tdDiscard==null)                                       //~1126I~
        	tdDiscard=tdsHand[0];                                  //~1126I~
        if (Dump.Y) Dump.println("RADSmart.selectSmartPonChii tdDiscard="+tdDiscard.toString());//~1126I~//~1213R~
        return tdDiscard;                                          //~1126I~
    }                                                              //~1126I~
    //***********************************************************************
    private boolean callKanTaken(boolean PswReach,TileData PtdTaken,int PposTaken)
    {
        if (Dump.Y) Dump.println("RADSmart.callKanTaken swReach="+PswReach);//~1124R~
        int posKan=AG.aRACall.callKanTaken(playerDiscard,eswnDiscard,myShanten,PswReach,PtdTaken,PposTaken,itsHand,ctrHand);//~1122R~//~1124R~
        boolean rc=(posKan>=0);                                    //~1124I~
//        if (rc)                //decrease at GCM_KAN msg         //~1124R~
//        {                                                        //~1124R~
//            itsHand[posKan]=0;                                   //~1124R~
//            ctrHand-=PIECE_DUPCTR;                               //~1124R~
//        }                                                        //~1124R~
        if (Dump.Y) Dump.println("RADSmart.callKanTaken rc="+rc+",posKan="+posKan+",ctrHand="+ctrHand+",itsHand="+Utils.toString(itsHand,9));//~1124R~
        return rc;
    }
    //***********************************************************************
    //*evaluate reachPos                                           //~1222I~
    //***********************************************************************//~1222I~
    private boolean callReach()
    {
        if (Dump.Y) Dump.println("RADSmart.callReach");
        int hanMaxMax=AG.aRAReach.callReach(playerDiscard,eswnDiscard,posTaken,itsHand,itsHandPos,ctrHand,itsHandValue);//~1122R~
        boolean rc=hanMaxMax!=0;    //not skipReach                //~1309R~
        hanMaxReach=hanMaxMax;                                     //~1122I~
        if (Dump.Y) Dump.println("RADSmart.callReach return rc="+rc);//~1122R~
        return rc;
    }
    //***********************************************************************//~1122I~
    //*issueReach                                                  //~1222I~
    //***********************************************************************//~1222I~
    private boolean callReach(TileData PtdDiscard)                 //~1122I~
    {                                                              //~1122I~
        if (Dump.Y) Dump.println("RADSmart.callReach tdDiscard="+TileData.toString(PtdDiscard));//~1122I~
        boolean rc=AG.aRAReach.callReach(playerDiscard,eswnDiscard,PtdDiscard);//~1122I~
        if (Dump.Y) Dump.println("RADSmart.callReach rc="+rc);     //~1122I~
        return rc;                                          //~1122I~
    }                                                              //~1122I~
    //***********************************************************************
    private boolean callRonTaken(int[] PitsHand,TileData PtdTaken)
    {
        if (Dump.Y) Dump.println("RADSmart.callRonTaken tdTaken="+PtdTaken.toString());
//      AG.aRARon.callRonTaken(playerDiscard,eswnDiscard,PitsHand,PtdTaken);//~va79R~
//      return true;                                               //~va79R~
        boolean rc=AG.aRARon.callRonTaken(playerDiscard,eswnDiscard,PitsHand,PtdTaken);//~va79R~
        if (Dump.Y) Dump.println("RADSmart.callRonTaken rc="+rc);  //+va79I~
        return rc;                                                 //~va79R~
    }
    //***********************************************************************
    public int getShanten(boolean PswSetIntent,int PctrHand)       //~1121R~
    {
        if (Dump.Y) Dump.println("RADSmart.getShanten swSetIntent="+PswSetIntent+",eswnDiscard="+eswnDiscard+",PctrHand="+PctrHand);//~1213R~//~1217R~
    	int flag_shanten=AG.aShanten.getShantenMinFlag(itsHand,PctrHand);	//standard,13orphan,7pair//~1121R~
        int shanten=flag_shanten & 0xff;                           //~1121I~
        if (shanten==0xff)                                         //~1205I~
        	shanten=-1;                                            //~1205I~
        int flag=flag_shanten >>8;                                 //~1121I~
        if (PswSetIntent)                                          //~1121R~
        {                                                          //~1121I~
        	int intent=RSP.getIntent();                            //~1213I~
            if (PctrHand>=HANDCTR)  //swAllInHand is true for Ankan(ctrHand<HANDCTR),but invalid for 13orphan/7pair//~1219R~
            {                                                      //~1213I~
                if ((flag & SHANTEN_STANDARD)!=0)                   //~1215R~
	                intent&=~(INTENT_13ORPHAN | INTENT_7PAIR);     //~1215I~
                else                                               //~1215I~
                if ((intent & (INTENT_13ORPHAN | INTENT_7PAIR))==0)//~1213I~
                {                                                  //~1213R~
                    if ((flag & SHANTEN_13ORPHAN)!=0 && shanten<=HV_SET_INTENT_MAX_SHANTEN_13ORPHAN) //<=4                      //~1121I~//~1213R~//~1214R~//~1219R~//~1302R~
                    {                                                      //~1121I~//~1213R~
                        if (RSP.ctrTaken <=HV_SET_INTENT_TAKE_13ORPHAM) //select 13 orphan at 1st take//~1121I~//~1213R~
                        	if ((intent & INTENT_7PAIR)==0)    //~1213R~//~1214R~
                            {                                      //~1214I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1214I~
                                    UView.showToastLong("RADSmart Set intent 13ORPHAN eswn="+eswnDiscard);//~1214I~//~1220R~//~1223R~
        						if (Dump.Y) Dump.println("RADSmart.getShanten @@@@ set intent 13orphan eswn="+eswnDiscard);//~1220I~
                            	intent|=INTENT_13ORPHAN;        //~1121R~//~1213R~//~1214R~
                            }                                      //~1214I~
                    }                                                      //~1121I~//~1213R~
                    if ((flag & SHANTEN_7PAIR)!=0 && shanten<HV_SET_INTENT_MAX_SHANTEN_7PAIR) //<3 shanten           //~1121I~//~1213I~//~1214R~//~1217R~//~1223R~
                    {                                                      //~1121I~//~1213I~
                        if (RSP.ctrTaken<=HV_SET_INTENT_TAKE_7PAIR     //select 7pair before 4 take//~1121I~//~1213I~//~1217R~
                        ||  shanten<=HV_SET_INTENT_MAX_SHANTEN_7PAIR_ANYTIME)	//set intent anytime if shanten<=1//~1217I~
                        	if ((intent & INTENT_13ORPHAN)==0) //~1213I~//~1214R~
                            {                                      //~1214I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1214I~
                                    UView.showToastLong("RADSmart Set intent 7PAIR eswn="+eswnDiscard);//~1214I~//~1220R~//~1223R~
        						if (Dump.Y) Dump.println("RADSmart.getShanten @@@@ set intent 7Pair eswn="+eswnDiscard);//~1220I~
	                        	intent|=INTENT_7PAIR;                          //~1121I~//~1213I~//~1214R~
                            }                                      //~1214I~
                    }                                                      //~1121I~//~1213I~
                }                                                  //~1213I~
            }                                                      //~1213I~
            else                                                   //~1213I~
                intent&=~(INTENT_13ORPHAN | INTENT_7PAIR);         //~1213I~
        	RSP.setIntent(intent);  //for chkstatuistic            //~1216I~
//      	RSP.intent|=intent;                              //~1121R~//~1201R~
//  	    if ( (intent & (INTENT_7PAIR | INTENT_13ORPHAN))==0)   //~1213I~//~1224R~
//  		    intent|=chkStatistic();    //may be TREND_ALLSAME      //~1201I~//~1213R~//~1215R~
//  		    intent=chkStatistic(shanten);    //may be TREND_ALLSAME   //~1215I~//~1217R~//~1224R~
    		intent=chkStatistic(intent,shanten);    //may be TREND_ALLSAME//~1224I~
            myIntent=intent;                                       //~1201R~
        	RSP.setIntent(myIntent);                               //~1201I~
        }                                                          //~1121I~
        if (Dump.Y) Dump.println("RADSmart.getShanten swIntent="+PswSetIntent+",shanten="+shanten+",ctrTaken="+RSP.ctrTaken+",intent="+Integer.toHexString(myIntent));//~1121R~//~1201R~//~1214R~
        return shanten;
    }
    //***********************************************************************
    private TileData selectDiscard(int PmyShanten)                   //~1127R~//~1201R~
    {
    	TileData tdDiscard;
        //**************************
        if (Dump.Y) Dump.println("RADSmart.selectDiscard swDoReach="+swDoReach);        //~1201R~//~1309R~
        setNonDiscardable();	//pao and no tile
//      getStatistic();    //suit,19ji                             //~1201R~
        getDoraOpen();
        RADSE.evaluateHand(PmyShanten,eswnDiscard,itsHand,ctrHand,itsHandValue);//~1121R~//~1127R~
//      chkSameColor();
//      chk13Orphan();
//        chkOtherPlayer();
//        chkRonValue();
        tdDiscard=selectTile();
        return tdDiscard;
    }
    //***********************************************************************//~1309I~
    private TileData selectDiscardReach(int PmyShanten)            //~1309R~
    {                                                              //~1309I~
    	TileData tdDiscard;                                        //~1309I~
        int posOld,posNew;                                          //~1309I~
        //**************************                               //~1309I~
        if (Dump.Y) Dump.println("RADSmart.selectDiscard swDoReach="+swDoReach);//~1309I~
        tdDiscard=selectTile();                                    //~1309I~
        if (tdDiscard==null)                                       //~1309I~
        	return null;                                          //~1309I~
        posOld=RAUtils.getPosTile(tdDiscard);                      //~1309I~
        int hanMax=hanMaxReach;                                    //~1309I~
        int maxWinTile=AG.aRAReach.ctrWinTotal;                    //~1309I~
        RADSO.chkOtherPlayer(PmyShanten,hanMax,maxWinTile,eswnDiscard,itsHand,ctrHand);//~1309I~
        tdDiscard=selectTile();                                    //~1309I~
        if (tdDiscard==null)                                       //~1309I~
        	return null;                                          //~1309I~
        posNew=RAUtils.getPosTile(tdDiscard);                      //~1309I~
        if (posOld!=posNew)	//chkOther not changed discard tile    //~1309R~
        {                                                          //~1309I~
        	tdDiscard=null;                                        //+1309I~             //~1309I~
        }                                                          //~1309I~
        if (Dump.Y) Dump.println("RADSmart.selectDiscardReach rc="+TileData.toString(tdDiscard)+",swDoReach="+swDoReach+",posOld="+posOld+",posNew="+posNew);//~1309R~
        return tdDiscard;                                          //~1309R~
    }                                                              //~1309I~
    //***********************************************************************
    //*set non discardable tbl
    //***********************************************************************
    private void setNonDiscardable()
    {
        if (Dump.Y) Dump.println("RADSmart.setNonDiscardable itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1121R~//~1125R~//~1305R~
//      Arrays.fill(btsNonDiscardable,false);                      //~1121R~
        for (int ii=0;ii<ctrHand;ii++)
        {
        	int pos=RAUtils.getPosTile(tdsHand[ii]);
        	if (!RAD.isDiscardable(eswnDiscard,pos))
            {                                                      //~1121I~
  				itsHandValue[ii]=DV_NOTDISCARDABLE;                //~1121R~
//          	btsNonDiscardable[pos]=true;                       //~1121R~
        		if (Dump.Y) Dump.println("RADSmart.setNonDiscardable itsHandValue["+ii+"]="+itsHandValue[ii]);//~1220I~
            }                                                      //~1121I~
        }
    }
    //***********************************************************************//~1305I~
    //*chk kuikae                                                  //~1305I~
    //***********************************************************************//~1305I~
    private void setNonDiscardablePonChii()                        //~1305I~
    {                                                              //~1305I~
        if (Dump.Y) Dump.println("RADSmart.setNonDiscardablePonChii itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1305I~
        int[] posNotDiscardable=new int[2];                        //~1305I~
        int ctr=AG.aUserAction.UAD.getSameMeldTile(playerDiscard,posNotDiscardable);//~1305I~
        for (int ii=0;ii<ctrHand;ii++)                             //~1305I~
        {                                                          //~1305I~
        	int pos=itsHandPos[ii];                                //~1305I~
            for (int jj=0;jj<ctr;jj++)                             //~1305I~
            {                                                      //~1305I~
                if (pos==posNotDiscardable[jj])                    //~1305I~
                {                                                  //~1305I~
                	itsHandValue[ii]=DV_NOTDISCARDABLE;            //~1305I~
                	if (Dump.Y) Dump.println("RADSmart.setNonDiscardable itsHandValue["+ii+"]="+itsHandValue[ii]);//~1305I~
                }                                                  //~1305I~
            }                                                      //~1305I~
        }                                                          //~1305I~
        if (Dump.Y) Dump.println("RADSmart.setNonDiscardablePonChii itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1305I~
    }                                                              //~1305I~
//    //***********************************************************************//~1201R~
//    private void getStatistic()                                  //~1201R~
//    {                                                            //~1201R~
//        if (Dump.Y) Dump.println("RADSmart.getStatistic");       //~1201R~
//        itsStatHand=RS.RSP[eswnDiscard].getHandStatistic();      //~1201R~
//        myIntent=getIntent(intentByShanten);                                       //~1131R~//~1201R~
//        Point p=new Point();                                       //~1121R~//~1122R~//~1201R~
//        itsStatPair=RS.RSP[eswnDiscard].getPairStatistic(null/*p:ctr and status summary*/);//~1121R~//~1201R~
//        int ctrPair=p.x;                                             //~1121R~//~1122R~//~1201R~
//        int stat=p.y;                                            //~1121R~//~1122R~//~1201R~
//        int ctrPairSame=(stat & PS_ALLSAME)!=0 ? ctrPair : 0;      //~1122I~//~1201R~
//        int ctrSame=ctrPairSame+itsStatHand[CSI_PON]+itsStatHand[CSI_KAN];//~1122R~//~1201R~
//        if (ctrSame>=HV_TREND_SAME)                              //~1201R~
//            myIntent|=INTENT_TREND_SAME;                         //~1201R~
//        RS.RSP[eswnDiscard].setIntent(myIntent);                   //~1131I~//~1201R~
//    }                                                            //~1201R~
    //***********************************************************************//~1201I~
    //*return intent by statistic                                  //~1201I~
    //***********************************************************************//~1201I~
    private int chkStatistic(int Pintent,int Pshanten)                                     //~1201I~//~1217R~//~1224R~
    {                                                              //~1201I~
        int intent=Pintent;                                        //~1224R~
        if (Dump.Y) Dump.println("RADSmart.chkStatistic shanten="+Pshanten+",intent="+Integer.toHexString(Pintent));         //~1201I~//~1217R~//~1224R~
        itsStatHand=RS.RSP[eswnDiscard].getHandStatistic();        //~1201I~
        Point p=new Point();                                       //~1201I~
        itsStatPair=RS.RSP[eswnDiscard].getPairStatistic(null/*p:ctr and status summary*/);//~1201I~
        int ctrPair=p.x;                                           //~1201I~
        int stat=p.y;                                              //~1201I~
        int ctrDiscard=RS.RSP[eswnDiscard].ctrDiscarded;             //~1217I~
        if (ctrDiscard>=HV_DISCARD_GIVEUP && Pshanten>=HV_SHANTEN_GIVEUP)//~1217I~
//          ctrDiscard>=15                && Pshanten>=2                )//~1218I~//~1222R~//~1302I~
        {                                                          //~1302I~
            intent|=INTENT_GIVEUP;                                 //~1217I~
        	if (Dump.Y) Dump.println("RADSmart.chkStatistic giveup by discard="+ctrDiscard+",shanten="+Pshanten);//~1302I~
        }                                                          //~1302I~
        else                                                       //~1217I~
        if (ctrDiscard>=HV_DISCARD_GIVEUP_WEAK && Pshanten>=HV_SHANTEN_GIVEUP_WEAK)//~1217I~
        {                                                          //~1302I~
//         (ctrDiscard>=12                     && Pshanten>=2                     )//~1218I~
            intent|=INTENT_GIVEUP_WEAK;                            //~1217I~
        	if (Dump.Y) Dump.println("RADSmart.chkStatistic giveup weak by discard="+ctrDiscard+",shanten="+Pshanten);//~1302I~
        }                                                          //~1302I~
                                                                   //~1217I~
        if ((intent & (INTENT_GIVEUP_WEAK|INTENT_GIVEUP))==0)       //~1217I~
        {                                                          //~1217I~
    	    if ((Pintent & INTENT_13ORPHAN)==0)                    //~1224I~
            {                                                      //~1224I~
                int ctrPairSame=(stat & PS_ALLSAME)!=0 ? ctrPair : 0;      //~1201I~//~1217M~//~1224R~
                int ctrSame=ctrPairSame+itsStatHand[CSI_PON]+itsStatHand[CSI_KAN];//~1201I~//~1217I~//~1224R~
                int ctrPairInHand=itsStatHand[CSI_PAIR];                 //~1224I~
                if (ctrSame>=HV_TREND_SAME_SAME && ctrPairInHand>=HV_TREND_SAME_PAIR) //>=2 and >=3//~1224R~
                {                                                          //~1214I~//~1217I~//~1224R~
                                        if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1214I~//~1217I~//~1224R~
                                            UView.showToastLong("RADSmart Set intent TREND_SAME eswn="+eswnDiscard);//~1214I~//~1217I~//~1223R~//~1224R~
                    if (Dump.Y) Dump.println("RADSmart@@@@ Set intent TREND_SAME eswn="+eswnDiscard);//~1223I~//~1224R~
                    intent|=INTENT_TREND_SAME;                             //~1201I~//~1217I~//~1224R~
                    intent&=~INTENT_7PAIR;                         //~1224I~
                }                                                          //~1214I~//~1217I~//~1224R~
                if ((intent & (INTENT_TREND_SAME | INTENT_7PAIR))==0)//~1224I~//~1302R~
                {                                                  //~1308I~
			        intent&=~(INTENT_CHANTA|INTENT_TANYAO|INTENT_ALLSAME|INTENT_SAMECOLOR_ANY);//~1308I~
			        intent|=getIntentByStatistics(Pshanten,ctrPair);                        //~1201I~//~1217I~//~1218R~//~1224I~
                }                                                  //~1308I~
            }                                                      //~1224I~
        	if (chkIntent3Dragon()!=0)                            //~1307I~//~1308R~
            	intent|=INTENT_3DRAGON;                             //~1308I~
            else                                                   //~1308I~
            	intent&=~INTENT_3DRAGON;                            //~1308I~
            if ((intent & INTENT_3DRAGON)!=0)                      //~1307I~
            	intent&=~(INTENT_7PAIR|INTENT_13ORPHAN);           //~1307I~
        }                                                          //~1217I~
        else                                                       //~1224I~
			intent &= ~(INTENT_7PAIR | INTENT_13ORPHAN);           //~1224I~
        if (Dump.Y) Dump.println("RADSmart.chkStatistic intent="+Integer.toHexString(intent));//~1217I~
        return intent;                                             //~1201I~
    }                                                              //~1201I~
    //***********************************************************************
    //*get upper only considered next/conbutsu
    //*set array of (type,number)
    //***********************************************************************
    private void getDoraOpen()
    {
    	if (RS.swUpdateDora)                                       //~1215I~
        {                                                          //~1215I~
        	RS.swUpdateDora=false;                                 //~1215I~
            Arrays.fill(itsDoraOpen,0);                            //~1215I~
	        ctrDoraOpen=AG.aUARonValue.UARDT.getDoraOpen(itsDoraOpen);//~1215I~
        }                                                          //~1215I~
        for (int ii=0;ii<ctrDoraOpen;ii++)	//conver to pos
        {
        	int type=itsDoraOpen[ii+ii];
			int num=itsDoraOpen[ii+ii+1];
            setDoraOpen(type,num);
        }
        if (Dump.Y) Dump.println("RADSmart.getDoraOpen ctrDoraOpen="+ctrDoraOpen+",itsDoraOpen="+Arrays.toString(itsDoraOpen));
    }
    //***********************************************************************
    private void setDoraOpen(int Ptype,int Pnum)
    {
    	for (int ii=0;ii<ctrHand;ii++)
        {
        	TileData td=tdsHand[ii];
            if (Ptype==td.type && Pnum==td.number)
            {                                                      //~1220I~
	            itsHandValue[ii]+=DV_DORA;    //= -600             //~1220R~
        		if (Dump.Y) Dump.println("RADSmart.setDoraOpen itsHandValue["+ii+"]="+itsHandValue[ii]);//~1220I~
            }                                                      //~1220I~
        }
        if (Dump.Y) Dump.println("RADSmart.setDoraOpen type="+Ptype+",num="+Pnum+",itsDoraOpen="+Utils.toStringMax(itsHandValue,ctrHand));//~1125R~//~1305R~
    }
    //***********************************************************************//~1216I~
    public boolean isDoraOpen(TileData Ptd)                        //~1216I~
    {                                                              //~1216I~
    	boolean rc=false;                                          //~1216I~
        for (int ii=0;ii<ctrDoraOpen;ii++)	//conver to pos        //~1216I~
        {                                                          //~1216I~
        	if (Ptd.type==itsDoraOpen[ii+ii] && Ptd.number==itsDoraOpen[ii+ii+1])//~1216I~
            {                                                      //~1216I~
            	rc=true;                                           //~1216I~
                break;                                             //~1216I~
            }                                                      //~1216I~
            	                                                   //~1216I~
        }                                                          //~1216I~
        if (Dump.Y) Dump.println("RADSmart.isDoraOpen rc="+rc+",type="+Ptd.type+",num="+Ptd.number+",itsDoraOpen="+Utils.toString(itsDoraOpen,-1,ctrDoraOpen));//~1216I~
        return rc;
    }                                                              //~1216I~
    //***********************************************************************//~1313I~
    public int getCtrDoraInHand()                                  //~1313I~
    {                                                              //~1313I~
	    int rc=getCtrDoraInHand(eswnDiscard);                      //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInHand eswn="+eswnDiscard+",rc="+rc);//~1313I~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~1313I~
    public int getCtrDoraInHand(int Peswn)                         //~1313I~
    {                                                              //~1313I~
    	int player=Accounts.eswnToPlayer(Peswn);                   //~1313I~
        TileData[] tds=AG.aPlayers.getHands(player);               //~1313I~
    	int rc=0;                                                  //~1313I~
    	for (int ii=0;ii<tds.length;ii++)                          //~1313I~
        {                                                          //~1313I~
        	TileData td=tds[ii];                              //~1313I~
		    if (isDoraOpen(td))                                    //~1313I~
            	rc++;                                             //~1313I~
            if (td.isRed5())                                       //~1313I~
            	rc++;                                             //~1313I~
        }                                                          //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInHand eswn="+Peswn+",rc="+rc);//~1313I~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~1313I~
    public int getCtrDoraInEarch()                                 //~1313I~
    {                                                              //~1313I~
		int rc=getCtrDoraEarth(eswnDiscard);                      //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInEarth eswn="+eswnDiscard+",rc="+rc);//~1313R~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~1313I~
    public int getCtrDoraInEarth(int Peswn)                        //~1313I~
    {                                                              //~1313I~
		int rc=getCtrDoraEarth(Peswn);                             //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInEarth eswn="+Peswn+",rc="+rc);//~1313R~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~1313I~
    public int getCtrDoraInHandAndEarch()                          //~1313I~
    {                                                              //~1313I~
		int rc=getCtrDoraInHandAndEarth(eswnDiscard);             //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInHandAndEarth eswn="+eswnDiscard+",rc="+rc);//~1313R~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~1313I~
    public int getCtrDoraInHandAndEarth(int Peswn)                 //~1313I~
    {                                                              //~1313I~
		int rc=getCtrDoraInHand(Peswn)+getCtrDoraInEarth(Peswn);    //~1313I~
        if (Dump.Y) Dump.println("RADSmart.getCtrDoraInHandAndEarth eswn="+Peswn+",rc="+rc);//~1313R~
        return rc;                                                 //~1313I~
    }                                                              //~1313I~
    //***********************************************************************//~1221I~
    private boolean isDoraOpenChanta()                             //~1221I~
    {                                                              //~1221I~
    	boolean rc=false;                                          //~1221I~
        for (int ii=0;ii<ctrDoraOpen;ii++)	//conver to pos        //~1221I~
        {                                                          //~1221I~
        	int type=itsDoraOpen[ii+ii];                           //~1221I~
        	int num=itsDoraOpen[ii+ii+1];                          //~1221I~
        	if (type==TT_JI || num==TN1 || num==TN9)   //~1221I~   //~1222R~
            {                                                      //~1221I~
            	rc=true;                                           //~1221I~
                break;                                             //~1221I~
            }                                                      //~1221I~
                                                                   //~1221I~
        }                                                          //~1221I~
        if (Dump.Y) Dump.println("RADSmart.isDoraOpenChanta rc="+rc+",itsDoraOpen="+Utils.toString(itsDoraOpen,-1,ctrDoraOpen));//~1221I~
        return rc;                                                 //~1221I~
    }                                                              //~1221I~
    //***********************************************************************//~1220I~
    public boolean isDoraOpen(int Ppos)                            //~1220I~
    {                                                              //~1220I~
    	boolean rc=false;                                          //~1220I~
        int type=Ppos/CTR_NUMBER_TILE; int num=Ppos%CTR_NUMBER_TILE;//~1220I~
        for (int ii=0;ii<ctrDoraOpen;ii++)	//conver to pos        //~1220I~
        {                                                          //~1220I~
        	if (type==itsDoraOpen[ii+ii] && num==itsDoraOpen[ii+ii+1])//~1220I~
            {                                                      //~1220I~
            	rc=true;                                           //~1220I~
                break;                                             //~1220I~
            }                                                      //~1220I~
                                                                   //~1220I~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RADSmart.isDoraOpen rc="+rc+",pos="+Ppos+",type="+type+",num="+num+",itsDoraOpen="+Utils.toString(itsDoraOpen,-1,ctrDoraOpen));//~1220I~
        return rc;                                                 //~1220I~
    }                                                              //~1220I~
    //***********************************************************************
    private TileData selectTile()
    {
        TileData tdDiscard=null;
        int mx=DV_NOTDISCARDABLE,mxPos=-1;
        //*********************************
        if (Dump.Y) Dump.println("RADSmart.selectTile@@@@ itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1125R~//~1301R~//~1305R~
        for (int ii=0;ii<ctrHand;ii++)
        {
        	int hv=itsHandValue[ii];
        	if (hv>mx)
            {
            	mx=hv;
                mxPos=ii;
            }
        }
        if (mxPos>=0)
        {                                                          //~1222I~
	        tdDiscard=tdsHand[mxPos];
            if (tdDiscard.isRed5())	//select non red5 if exist     //~1222I~
            {                                                      //~1222I~
            	int num=tdDiscard.number; int type=tdDiscard.type; //~1222R~
                for (int ii=0;ii<ctrHand;ii++)                     //~1222I~
                {                                                  //~1222I~
                	TileData td=tdsHand[ii];                       //~1222I~
                    if (td.type==type && td.number==num && !td.isRed5())//~1222I~
                    {                                              //~1222I~
                        tdDiscard=td;                              //~1222I~
                        break;                                     //~1222I~
                    }                                              //~1222I~
                }                                                  //~1222I~
            }                                                      //~1222I~
        }                                                          //~1222I~
        if (Dump.Y) Dump.println("RADSmart.selectTile eswn="+eswnDiscard+",ctrHand="+ctrHand+",mxPos="+mxPos+",mx="+mx+",tdDiscard="+Utils.toString(tdDiscard));//~1125R~//~1220R~
        return tdDiscard;
    }
    //***********************************************************************
    //*chk any winning tile is furiten
    //*from RADSEval.tryNextTake                                   //~1213I~
    //***********************************************************************
    public  boolean chkFuriten()                                   //~1121R~
    {
//        boolean rc=false;                                        //~1213R~
//        getWinList();                                            //~1213R~
//        for (int ii=0;ii<CTR_TILETYPE;ii++)                      //~1213R~
//        {                                                        //~1213R~
//            if (btsWin[ii])                                      //~1213R~
//                if (RS.isFuriten(eswnDiscard,ii))                  //~1121R~//~1213R~
//                {                                                //~1213R~
//                    rc=true;                                     //~1213R~
//                    break;                                       //~1213R~
//                }                                                //~1213R~
//        }                                                        //~1213R~
        boolean rc=chkFuriten(eswnDiscard,itsHand,ctrHand);        //~1213I~
        if (Dump.Y) Dump.println("RADSmart.chkFuriten rc="+rc);    //~1213R~
        return rc;                                                 //~1213R~
	}
    //***********************************************************************//~1220I~
    public  boolean chkFuritenSelf()                               //~1220I~
    {                                                              //~1220I~
        boolean rc=chkFuritenSelf(eswnDiscard,itsHand,ctrHand);    //~1220I~
        if (Dump.Y) Dump.println("RADSmart.chkFuritenSelf rc="+rc);//~1220I~
        return rc;                                                 //~1220I~
	}                                                              //~1220I~
    //***********************************************************************//~1213I~
    //*from RARon at RonRiver                                      //~1213I~
    //***********************************************************************//~1213I~
    public  boolean chkFuriten(int PeswnOther,int[] PitsHand,int PctrHand)//~1213I~
    {                                                              //~1213I~
        if (Dump.Y) Dump.println("RADSmart.chkFuriten eswnOther="+PeswnOther+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9,PctrHand));//~1213I~//~1306R~
    	boolean rc=false;                                          //~1213I~
    	getWinList(PitsHand,PctrHand);                             //~1213I~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~1213I~
        {                                                          //~1213I~
        	if (btsWin[ii])                                        //~1213I~
//          	if (RS.isFuriten(PeswnOther,ii))                    //~1213I~//~1306R~
            	if (RS.isFuritenRon(PeswnOther,ii))                //~1306I~
                {                                                  //~1213I~
                	rc=true;                                       //~1213I~
                    break;                                         //~1213I~
                }                                                  //~1213I~
        }                                                          //~1213I~
        if (Dump.Y) Dump.println("RADSmart.chkFuriten rc="+rc);    //~1213I~
        return rc;                                                 //~1213I~
	}                                                              //~1213I~
    //***********************************************************************//~1220I~
    //*from RADSEval                                               //~1220I~
    //***********************************************************************//~1220I~
    public  boolean chkFuritenSelf(int PeswnOther,int[] PitsHand,int PctrHand)//~1220I~
    {                                                              //~1220I~
        if (Dump.Y) Dump.println("RADSmart.chkFuriten eswnOther="+PeswnOther+",ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1220I~//~1305R~
    	boolean rc=false;                                          //~1220I~
    	getWinList(PitsHand,PctrHand);                             //~1220I~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~1220I~
        {                                                          //~1220I~
        	if (btsWin[ii])                                        //~1220I~
            	if (RS.isFuritenSelf(PeswnOther,ii))               //~1220I~
                {                                                  //~1220I~
                	rc=true;                                       //~1220I~
                    break;                                         //~1220I~
                }                                                  //~1220I~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RADSmart.chkFuritenSelf rc="+rc);//~1220I~
        return rc;                                                 //~1220I~
	}                                                              //~1220I~
    //***********************************************************************//~1213I~
    private void getWinList(int[] PitsHand,int PctrHand)           //~1213I~
    {                                                              //~1213I~
        if (Dump.Y) Dump.println("RADSmart.getWinList ctrHand="+PctrHand+",itsHand="+Utils.toString(PitsHand,9));//~1213I~//~1305R~
        AG.aRAReach.getBtsWinList(PitsHand,PctrHand,btsWin);	//get tenpai pattern//~1213I~
        if (Dump.Y) Dump.println("RADSmart.getWinList btsWin="+Utils.toString(btsWin,9,PctrHand));//~1213I~
    }                                                              //~1213I~
//    //***********************************************************************//~1131R~
//    public int setIntent(int[] PitsStat)                         //~1131R~
//    {                                                            //~1131R~
//        if (Dump.Y) Dump.println("RoundStat.setIntent");         //~1131R~
//        if (ctrTaken>=HV_SET_INTENT)                             //~1131R~
//            return intent;                                       //~1131R~
//        intent=(intent & INTENT_BY_SHANTEN);//        =0x0700; //to reset 13orphan,7pair,normal//~1131R~
//        intent|=chkIntentSameColor(ctrTaken,PitsStat);           //~1131R~
//        intent|=chkIntentAllSame(ctrTaken,PitsStat);             //~1131R~
//        if (Dump.Y) Dump.println("RoundStat.setIntent eswn="+eswn+",intent="+Integer.toHexString(intent));//~1131R~
//        return intent;                                           //~1131R~
//    }                                                            //~1131R~
//    //***********************************************************************//~1131R~
//    //*set intent of same color                                  //~1131R~
//    //***********************************************************************//~1131R~
//    private int chkIntentSameColor(int ctrTaken,int[] PitsStat)  //~1131R~
//    {                                                            //~1131R~
//        int intent=0;                                            //~1131R~
//        int ctrMan=PitsStat[CSI_MAN];                            //~1131R~
//        int ctrPin=PitsStat[CSI_PIN];                            //~1131R~
//        int ctrSou=PitsStat[CSI_SOU];                            //~1131R~
//        int ctrWord=PitsStat[CSI_WORD];                          //~1131R~
//        int limit=HV_SET_INTENT_SAMECOLOR-ctrTaken/2;            //~1131R~
//        if ((ctrPin+ctrSou)<limit)                               //~1131R~
//            intent=INTENT_SAMECOLOR_MAN;                         //~1131R~
//        else                                                     //~1131R~
//        if ((ctrSou+ctrMan)<limit)                               //~1131R~
//            intent=INTENT_SAMECOLOR_PIN;                         //~1131R~
//        else                                                     //~1131R~
//        if ((ctrMan+ctrPin)<limit)                               //~1131R~
//            intent=INTENT_SAMECOLOR_SOU;                         //~1131R~
//        if (Dump.Y) Dump.println("RADEval.chkIntentSameColor intent="+Integer.toHexString(intent)+",man="+ctrMan+",pin="+ctrPin+",sou="+ctrSou+",ctrWord="+ctrWord+",HV_SET_INTENT_SAMECOLOR="+HV_SET_INTENT_SAMECOLOR+",limit="+limit);//~1131R~
//        return intent;                                           //~1131R~
//    }                                                            //~1131R~
//    //***********************************************************************//~1131R~
//    //*set intent of toitoi                                      //~1131R~
//    //***********************************************************************//~1131R~
//    private int chkIntentAllSame(int PctrTaken,int[] PitsStat)   //~1131R~
//    {                                                            //~1131R~
//        int intent=0;                                            //~1131R~
//        int ctrPair=PitsStat[CSI_PAIR];                          //~1131R~
//        int ctrPon=PitsStat[CSI_PON];                            //~1131R~
//        int ctrKan=PitsStat[CSI_KAN];                            //~1131R~
//        int v=(ctrKan+ctrPon)*HV_SET_INTENT_ALLSAME_VALUE_PON+ctrPair*HV_SET_INTENT_ALLSAME_VALUE_PAIR;//~1131R~
////      v-=PctrTaken;                                            //~1131R~
//        if (v>=HV_SET_INTENT_ALLSAME_VALUE)                      //~1131R~
//            intent=INTENT_ALLSAME;                               //~1131R~
//        if (Dump.Y) Dump.println("RADEval.chkIntentAllSame rc="+intent+",pair="+ctrPair+",ppon="+ctrPon+",kan="+ctrKan+",ctrTaken="+PctrTaken+",val="+v+",HV_SET_INTENT_ALLSAME_VALUE="+HV_SET_INTENT_ALLSAME_VALUE);//~1131R~
//        return intent;                                           //~1131R~
//    }                                                            //~1131R~
    //***********************************************************************//~1131I~
    private int getIntentByStatistics(int Pshanten,int PctrPair)                                        //~1131R~//~1201R~//~1217R~//~1218R~
    {                                                              //~1131I~
        if (Dump.Y) Dump.println("RADSmart.getIntentByStatistics shanten="+Pshanten);            //~1131I~//~1201R~//~1218R~
        int intent=0;
//      int intent=RSP.getIntent();                                //~1217R~
//      if (RSP.ctrTaken>=HV_SET_INTENT)                           //~1131I~//~1217R~
//          return intent;                                     //~1131I~//~1217R~
//      int intent=(RSP.intent & INTENT_BY_SHANTEN);//        =0x0700; //to reset 13orphan,7pair,normal//~1131I~//~1201R~
//      intent|=chkIntentSameColor(RSP.ctrTaken);                  //~1131I~//~1213R~
//      intent|=chkIntentAllSame(RSP.ctrTaken);                    //~1131I~//~1213R~
//      RSP.setIntent(intent); //add to 7pair,13orphan                 //~1131I~//~1201R~
        if (chkChanta(PctrPair))                                   //~1217I~
			intent|=INTENT_CHANTA;                                 //~1217I~
        else                                                       //~1217I~
        if (chkTanyao(PctrPair,Pshanten))                                   //~1217I~//~1223R~
			intent|=INTENT_TANYAO;                                 //~1217I~
        intent|=chkIntentSameColor(RSP.ctrTaken,Pshanten) | chkIntentAllSame(RSP.ctrTaken);//~1213I~//~1215R~//~1218R~//~1219R~
        if (Dump.Y) Dump.println("RADSmart.getIntentByStatistics eswnDiscard="+eswnDiscard+",intent=x"+Integer.toHexString(intent));//~1131I~//~1201R~//~1303R~
        return intent;                                             //~1131I~
    }                                                              //~1131I~
    //***********************************************************************//~1215I~
    //*chk earth color including no earth                          //~1218I~
    //***********************************************************************//~1218I~
    private boolean chkEarthDiscard(int Ptype)                     //~1215I~
    {                                                              //~1215I~
        boolean rc=RSP.isSameColorEarth(Ptype); //including no earth                    //~1215I~//~1218R~
//        if (true)                                                //~1215I~
//        {                                                        //~1215I~
//            int ctrSameDiscarded=0;                              //~1215I~
//            for (int ii=Ptype*CTR_NUMBER_TILE;ii<(Ptype+1)*CTR_NUMBE_TILE;ii++)//~1215I~
//                ctrSameDiscarded+=RSP.discarded[ii];             //~1215I~
//            if (ctrSameDiscarded                                 //~1215I~
//        }                                                        //~1215I~
        if (Dump.Y) Dump.println("RADSmart.chkEarthDiscard rc="+rc+",eswn="+RSP.eswn+",type="+Ptype);//~1215I~
        return rc;                                                 //~1215I~
    }                                                              //~1215I~
    //***********************************************************************//~1218I~
    private boolean chkSameColorTrend(int Ptype,int Pshanten,int PctrType,int PctrWordDup,int PctrOther,int PctrTaken)//~1218I~
    {                                                              //~1218I~
    	boolean rc;                                                //~1219I~
		int intent=RSP.intent;                                     //~1219I~
//      int limit=HV_SET_INTENT_SAMECOLOR-PctrTaken/3;    //4-ctrTaken/3//~1219R~
//      int limit=Pshanten;                                        //~1219I~//~1308R~
        int limit=PctrTaken<=HV_SET_INTENT_SAMECOLOR_TAKEN ? HV_SET_INTENT_SAMECOLOR_OTHER1 : HV_SET_INTENT_SAMECOLOR_OTHER2; //6?3:2//~1308I~
		if ((intent & INTENT_SAMECOLOR_ANY)!=0)                    //~1219I~
        {                                                          //~1219I~
//            if (Ptype==TT_MAN)                                     //~1219I~//~1220R~
//                rc=(intent & INTENT_SAMECOLOR_MAN)!=0;             //~1219I~//~1220R~
//            else                                                   //~1219I~//~1220R~
//            if (Ptype==TT_PIN)                                     //~1219I~//~1220R~
//                rc=(intent & INTENT_SAMECOLOR_PIN)!=0;             //~1219I~//~1220R~
//            else                                                   //~1219I~//~1220R~
//                rc=(intent & INTENT_SAMECOLOR_SOU)!=0;             //~1219I~//~1220R~
            rc=RAUtils.isMatchSameColor(false/*No allow Word*/,intent,Ptype);//~1220I~
        }                                                          //~1219I~
        else                                                       //~1219I~
        {                                                          //~1219I~
            rc=chkEarthDiscard(Ptype) && Pshanten<HV_INTENT_SAMECOLOR_SHANTEN;//<=3//~1219R~//~1308R~
            if (rc)                                                    //~1218I~//~1219R~
            {                                                          //~1218I~//~1219R~
//              rc=PctrOther<PctrType && (PctrOther-PctrWordDup)<limit;//~1218I~//~1219R~//~1308R~
                rc=PctrOther<PctrType && PctrOther<=limit;         //~1308I~
            }                                                          //~1218I~//~1219R~
        }                                                          //~1219I~
        if (Dump.Y) Dump.println("RADSmart.chkSameColorTrend rc="+rc+",old intent=x"+Integer.toHexString(intent)+",ctrTaken="+PctrTaken+",limit="+limit+",type="+Ptype+",shanten="+Pshanten+",ctrType="+PctrType+",ctrWordDup="+PctrWordDup+",ctrOther="+PctrOther);//~1218I~//~1219R~//~1308R~
        return rc;                                                 //~1218I~
    }                                                              //~1218I~
    //***********************************************************************//~1131I~
    //*set intent of same color                                    //~1131I~
    //***********************************************************************//~1131I~
    private int chkIntentSameColor(int PctrTaken,int Pshanten)                   //~1131I~//~1215R~//~1218R~
    {                                                              //~1131I~
        if (Dump.Y) Dump.println("RADSmart.chkIntentSameColor ctrTaken="+PctrTaken+",shanten="+Pshanten);//~1215I~//~1218R~
		int ctrMan,ctrPin,ctrSou/*,ctrWord,ctrAll*/,ctrWordDup;               //~1215I~//~1218R~
        int intent=0;                                              //~1131I~
        ctrMan=itsStatHand[CSI_MAN];                               //~1131I~
        ctrPin=itsStatHand[CSI_PIN];                               //~1131I~
        ctrSou=itsStatHand[CSI_SOU];                               //~1131I~
//      ctrWord=itsStatHand[CSI_WORD];                             //~1131I~//~1215R~
        ctrWordDup=itsStatHand[CSI_WORD_DUP]/2;	//decrese if word pair exist; 1 for pair and tripret,2 for 2 pair,...//~1217R~//~1218R~
//      int limit=HV_SET_INTENT_SAMECOLOR-PctrTaken/3;    //other color <4//~1215R~//~1218R~
//      if ((ctrPin+ctrSou)<ctrMan && (ctrPin+ctrSou-ctrWordDup)<limit && chkEarthDiscard(TT_MAN))      //~1215R~//~1217R~//~1218R~
        if (chkSameColorTrend(TT_MAN,Pshanten,ctrMan,ctrWordDup,ctrPin+ctrSou,PctrTaken))//~1218I~
        {                                                          //~1214I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1214I~
                                    UView.showToastLong("RADSmart Set intent SAMECOLOR MAN eswn="+eswnDiscard);//~1214I~//~1223R~
	        if (Dump.Y) Dump.println("RADSmart.chkIntentAllSameColor@@@@ setIntent MAN eswn="+eswnDiscard);//~1219I~//~1303R~
            intent=INTENT_SAMECOLOR_MAN;                           //~1131I~
        }                                                          //~1214I~
        else                                                       //~1131I~
//      if ((ctrSou+ctrMan)<ctrPin && (ctrSou+ctrMan-ctrWordDup)<limit && chkEarthDiscard(TT_PIN))                                 //~1131I~//~1215R~//~1217R~//~1218R~
        if (chkSameColorTrend(TT_PIN,Pshanten,ctrPin,ctrWordDup,ctrSou+ctrMan,PctrTaken))//~1218I~
        {                                                          //~1214I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1214I~
                                    UView.showToastLong("RADSmart Set intent SAMECOLOR PIN +eswn="+eswnDiscard);//~1214I~//~1223R~
	        if (Dump.Y) Dump.println("RADSmart.chkIntentAllSameColor@@@@ setIntent PIN eswn="+eswnDiscard);//~1219I~//~1303R~
            intent=INTENT_SAMECOLOR_PIN;                           //~1131I~
        }                                                          //~1214I~
        else                                                       //~1131I~
//      if ((ctrMan+ctrPin)<ctrSou && (ctrMan+ctrPin-ctrWordDup)<limit && chkEarthDiscard(TT_SOU))                                 //~1131I~//~1215R~//~1217R~//~1218R~
        if (chkSameColorTrend(TT_SOU,Pshanten,ctrSou,ctrWordDup,ctrMan+ctrPin,PctrTaken))//~1218I~
        {                                                          //~1214I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1214I~
                                    UView.showToastLong("RADSmart Set intent SAMECOLOR SOU eswn="+eswnDiscard);//~1214I~//~1223R~
            intent=INTENT_SAMECOLOR_SOU;                           //~1131I~
	        if (Dump.Y) Dump.println("RADSmart.chkIntentAllSameColor@@@@ setIntent SOU eswn="+eswnDiscard);//~1219I~//~1303R~
        }                                                          //~1214I~
        if (Dump.Y) Dump.println("RADSmart.chkIntentSameColor intent="+Integer.toHexString(intent)+",man="+ctrMan+",pin="+ctrPin+",sou="+ctrSou+",ctrWordDup="+ctrWordDup+",HV_SET_INTENT_SAMECOLOR="+HV_SET_INTENT_SAMECOLOR);//~1131I~//~1217R~
        return intent;                                             //~1131I~
    }                                                              //~1131I~
    //***********************************************************************//~1131I~
    //*set intent of toitoi                                        //~1131I~
    //***********************************************************************//~1131I~
    private int chkIntentAllSame(int PctrTaken)                    //~1131I~
    {                                                              //~1131I~
        int intent=0;                                              //~1131I~
        int ctrPair=itsStatHand[CSI_PAIR];                         //~1131I~
        int ctrPon=itsStatHand[CSI_PON];                           //~1131I~
        int ctrKan=itsStatHand[CSI_KAN];                           //~1131I~
        int ctrEarth=RS.RSP[eswnDiscard].ctrPairStatus;            //~1217I~
        if (ctrEarth!=0 && !RS.RSP[eswnDiscard].isPairSameAll())   //~1217I~
        	return 0;                                              //~1217I~
        int v=(ctrKan+ctrPon+ctrEarth)*HV_SET_INTENT_ALLSAME_VALUE_PON+ctrPair*HV_SET_INTENT_ALLSAME_VALUE_PAIR;//~1305R~
		//pon*3+pair*1>=9                                         //~1305I~//~1308R~
//      v-=PctrTaken;                                              //~1131I~
        if (v>=HV_SET_INTENT_ALLSAME_VALUE)       //9                 //~1131I~//~1217R~//~1308R~
        {                                                          //~1217I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1217I~
                                    UView.showToastLong("RADSmart Set intent ALLSAME eswn="+eswnDiscard);//~1217I~//~1223R~
	        if (Dump.Y) Dump.println("RADSmart.chkIntentAllSame@@@@ setIntent eswn="+eswnDiscard);//~1217I~//~1219R~//~1303R~
            intent=INTENT_ALLSAME;                                 //~1131I~
        }                                                          //~1217I~
        if (Dump.Y) Dump.println("RADSmart.chkIntentAllSame rc=intent=x"+Integer.toHexString(intent)+",pair="+ctrPair+",pon="+ctrPon+",kan="+ctrKan+",ctrEarth="+ctrEarth+",ctrTaken="+PctrTaken+",val="+v+",HV_SET_INTENT_ALLSAME_VALUE="+HV_SET_INTENT_ALLSAME_VALUE);//~1131I~//~1303R~
        return intent;                                             //~1131I~
    }                                                              //~1131I~
    //***********************************************************************//~1217I~
    private boolean chkTanyao(int PctrPair,int Pshanten)                            //~1217I~//~1223R~
    {                                                              //~1217I~
    	boolean rc=false;                                          //~1217I~
        if (Dump.Y) Dump.println("RADSmart.chkTanyao eswn="+eswnDiscard+",ctrPair="+PctrPair);//~1217I~
        if (!RS.RSP[eswnDiscard].isPairTanyaoAllOrNoPair())        //~1217R~
            return false;                                          //~1217R~
        int ctrNonTanyao=itsStatHand[CSI_WORD]+itsStatHand[CSI_TERMINAL];//~1217I~
        int ctrTaken=RS.RSP[eswnDiscard].ctrTaken;                 //~1217I~
        if (ctrNonTanyao<=(ctrTaken<HV_INTENT_TANYAO_TAKECTR ? HV_INTENT_TANYAO_TILECTR_EARLY : HV_INTENT_TANYAO_TILECTR))//~1218R~
//*                        ctrTaken<8                        ? 3                                2//~1218I~
        {                                                          //~1217I~
    		if (Pshanten>=HV_INTENT_TANYAO_SHANTEN_FORCE)	//  =2;     //if shanten<2 ignore chanta meld & chanta dora//~1223I~
                if (!isDoraOpenChanta() && !chkChantaMeldForTanyao())  //~1221I~//~1223R~
                {                                                      //~1221I~//~1223R~
                                    if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1217I~//~1223R~
                                        UView.showToastLong("RADSmart.chkTanyao set intent TANYAO eswn="+eswnDiscard);//~1217I~//~1223R~
                    if (Dump.Y) Dump.println("RADSmart.chkTanyao @@@@ setIntent eswn="+eswnDiscard);//~1217I~//~1219R~//~1221R~//~1223R~
                    rc=true;                                               //~1217I~//~1221R~//~1223R~
                }                                                      //~1221I~//~1223R~
        }                                                          //~1217I~
        if (Dump.Y) Dump.println("RADSmart.chkTanyao rc="+rc+",ctrTaken="+ctrTaken+",ctrNonTanyaoTile="+ctrNonTanyao);//~1217I~
        return rc;                                                 //~1217I~
    }                                                              //~1217I~
    //***********************************************************************//~1217I~
    private boolean chkChanta(int PctrPair)                        //~1217I~
    {                                                              //~1217I~
    	boolean rc=false;                                          //~1217I~
    	int ctr,ctrMeld3=0,ctrMeld=0;                              //~1307I~
        if (Dump.Y) Dump.println("RADSmart.chkChanta eswn="+eswnDiscard+",ctrPair="+PctrPair);//~1217I~
        if (!RS.RSP[eswnDiscard].isPairChantaAllOrNoPair())        //~1217R~
            return false;                                          //~1217R~
        for (int pos=0;pos<OFFS_WORDTILE;pos+=CTR_NUMBER_TILE)         //~1217I~
        {                                                          //~1217I~
        	ctr=itsHand[pos];                                      //~1217I~
        	if (ctr>1)                                             //~1217I~
            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4                                   //~1217I~//~1221R~
            else                                                   //~1217I~
        	if (ctr==1)                                            //~1217I~
            {                                                      //~1217I~
				if (itsHand[pos+1]!=0 && itsHand[pos+2]!=0)        //~1217I~//~1307R~
	            	ctrMeld3++;                                    //~1307R~
                else                                               //~1307I~
				if (itsHand[pos+1]!=0 || itsHand[pos+2]!=0)        //~1307I~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4;   //~1307I~
                else                                               //~1217I~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT1;  //1;                                    //~1217I~//~1221R~
            }                                                      //~1217I~
        	int pos2=pos+TN9;                        //~1217I~     //~1307R~
        	ctr=itsHand[pos2];                                     //~1217I~
        	if (ctr>1)                                             //~1217I~
            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4;                                        //~1217I~//~1221R~
            else                                                   //~1217I~
        	if (ctr==1)                                            //~1217I~
            {                                                      //~1217I~
        		if (itsHand[pos2-1]!=0 && itsHand[pos2-2]!=0)      //~1217R~//~1307R~
	            	ctrMeld3++;                                    //~1307I~
                else                                               //~1217I~
        		if (itsHand[pos2-1]!=0 || itsHand[pos2-2]!=0)      //~1307I~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2; //4;   //~1307I~
                else                                               //~1307I~
	            	ctrMeld+=HV_INTENT_CHANTA_MELD_WEIGHT2;  //1;                                    //~1217I~//~1221R~
            }                                                      //~1217I~
        }                                                          //~1217I~
        ctrMeld+=itsStatHand[CSI_WORD_DUP]/2*HV_INTENT_CHANTA_MELD_WEIGHT2;//~1221R~
        if (ctrMeld3!=0 && (ctrMeld/HV_INTENT_CHANTA_MELD_WEIGHT2+PctrPair)>=HV_INTENT_CHANTA_MELD)     //intent chanta if candidate>=5//~1217R~//~1221R~//~1307R~
        {                                                          //~1217I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1217I~
                                    UView.showToastLong("RADSmart Set intent CHANTA eswn="+eswnDiscard);//~1217I~//~1223R~
	        if (Dump.Y) Dump.println("RADSmart.chkChanta @@@@ setIntent eswn="+eswnDiscard);//~1217I~//~1219R~
            rc=true;                                               //~1217I~
        }                                                          //~1217I~
        if (Dump.Y) Dump.println("RADSmart.chkChanta itsHand="+Utils.toString(itsHand,9));//~1217I~//~1305R~
        if (Dump.Y) Dump.println("RADSmart.chkChanta itsStatHand="+Utils.toString(itsStatHand));//~1307I~
        if (Dump.Y) Dump.println("RADSmart.chkChanta rc="+rc+",ctrMeld="+ctrMeld+",ctrMeld3="+ctrMeld3);//~1217I~//~1307R~
        return rc;                                                 //~1217I~
    }                                                              //~1217I~
    //***********************************************************************//~1221I~
    //*chk complete chanta meld                                    //~1221I~
    //***********************************************************************//~1221I~
    private boolean chkChantaMeldForTanyao()           //~1221I~
    {                                                              //~1221I~
        if (Dump.Y) Dump.println("RADSmart.chkChantaMeldForTanyao eswn="+eswnDiscard);//~1221I~
        if (!RS.RSP[eswnDiscard].isPairTanyaoAllOrNoPair())        //~1221I~
            return false;                                          //~1221I~
        int ctrMeld=0;                                             //~1221I~
        for (int pos=0;pos<OFFS_WORDTILE;pos+=CTR_NUMBER_TILE)     //~1221I~
        {                                                          //~1221I~
        	int ctr=itsHand[pos];                                      //~1221I~
        	if (ctr>=3)                                            //~1221I~
            	ctrMeld++;                                         //~1221I~
            else                                                   //~1221I~
        	if (ctr>0)                                             //~1221I~
            {                                                      //~1221I~
				if (itsHand[pos+1]!=0 || itsHand[pos+2]!=0)        //~1221I~
                	ctrMeld++;                                     //~1221I~
            }                                                      //~1221I~
        	int pos2=pos+CTR_NUMBER_TILE-1;                        //~1221I~
        	ctr=itsHand[pos2];                                     //~1221I~
        	if (ctr>=3)                                           //~1221I~
            	ctrMeld++;                                         //~1221I~
            else                                                   //~1221I~
        	if (ctr>0)                                             //~1221I~
            {                                                      //~1221I~
        		if (itsHand[pos2-1]!=0 || itsHand[pos2-2]!=0)      //~1221I~
	            	ctrMeld++;                                     //~1221I~
            }                                                      //~1221I~
        }                                                          //~1221I~
        ctrMeld+=itsStatHand[CSI_WORD_DUP]/2;                       //~1221I~
        boolean rc=ctrMeld<=HV_INTENT_TANYAO_MAX_CHANTA_MELD;	//2//~1221I~
        if (Dump.Y) Dump.println("RADSmart.chkChantaMeldForTanyao rc="+rc+",ctrMeld="+ctrMeld);//~1221I~
        return rc;                                                 //~1221I~
    }                                                              //~1221I~
    //***********************************************************************//~1220I~
    //*from RADSOther                                              //~1220I~
    //***********************************************************************//~1220I~
    public int getCtrDoraEarth(int Peswn/*otherPlayer*/)           //~1220I~
    {                                                              //~1220I~
        int ctr=0;                                                 //~1220I~
        int player=RS.RSP[Peswn].player;                           //~1220I~
        TileData[][] tdss=AG.aPlayers.getEarth(player);        //~1220I~
        if (tdss!=null)                                            //~1220I~
        {                                                          //~1220I~
            for (TileData[] tds:tdss)                              //~1220I~
            {                                                      //~1220I~
                if (tds==null)                                     //~1220R~
                    break;                                         //~1220I~
                if (Dump.Y) Dump.println("RoundStat.getCtrDoraEarth earth tds="+TileData.toString(tds));//~1220I~
                for (TileData td:tds)                              //~1220I~
                {                                                  //~1220I~
                    if (td==null)                                  //~1220I~
                        break;                                     //~1220I~
                    int pos=RAUtils.getPosTile(td);                //~1220I~
                    if (isDoraOpen(pos))                           //~1220I~
                        ctr++;                                     //~1220I~
                    if (td.isRed5())                               //~1220I~
                        ctr++;                                     //~1220I~
                }                                                  //~1220I~
            }                                                      //~1220I~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RoundStat.getCtrDoraEarth ctr="+ctr+",eswn="+Peswn+",player="+player);//~1220I~
        return ctr;                                                 //~1220I~
    }                                                              //~1220I~
    //***********************************************************************//~1220I~
    public int getHanEarthWord(int Peswn/*otherPlayer*/)           //~1220I~
    {                                                              //~1220I~
        int ctr=0;                                                 //~1220I~
        int player=RS.RSP[Peswn].player;                           //~1220I~
        TileData[][] tdss=AG.aPlayers.getEarth(player);        //~1220I~
        if (tdss!=null)                                            //~1220I~
        {                                                          //~1220I~
            for (TileData[] tds:tdss)                              //~1220I~
            {                                                      //~1220I~
                if (tds==null)                                     //~1220I~
                    break;                                         //~1220I~
                if (Dump.Y) Dump.println("RoundStat.getHanEarthWord earth tds="+TileData.toString(tds));//~1220I~
                TileData td=tds[0];                                //~1220I~
                if (td.type==TT_JI)                                //~1220I~
                {                                                  //~1220I~
                	ctr+=RAUtils.chkValueWordTile(td,Peswn)/2;     //~1220I~
                }                                                  //~1220I~
            }                                                      //~1220I~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RoundStat.getHanEarthWord ctr="+ctr+",eswn="+Peswn+",player="+player);//~1220I~
        return ctr;                                                 //~1220I~
    }                                                              //~1220I~
    //***********************************************************************//~1307I~
    //big/small 3dragon                                            //~1307I~
    //***********************************************************************//~1307I~
    private int chkIntent3Dragon()                                 //~1307I~
    {                                                              //~1307I~
        int intent=0,ctr1=0,ctr2=0,ctr3=0,ctr;                     //~1307I~
        ctr3+=itsHand[OFFS_WORDTILE_DRAGON  ]>=3 ? 1 : 0;          //~1307I~
        ctr3+=itsHand[OFFS_WORDTILE_DRAGON+1]>=3 ? 1 : 0;          //~1307I~
        ctr3+=itsHand[OFFS_WORDTILE_DRAGON+2]>=3 ? 1 : 0;          //~1307I~
        ctr2+=itsHand[OFFS_WORDTILE_DRAGON  ]==2 ? 1 : 0;          //~1307I~
        ctr2+=itsHand[OFFS_WORDTILE_DRAGON+1]==2 ? 1 : 0;          //~1307I~
        ctr2+=itsHand[OFFS_WORDTILE_DRAGON+2]==2 ? 1 : 0;          //~1307I~
        ctr1+=itsHand[OFFS_WORDTILE_DRAGON  ]==1 && itsExposed[OFFS_WORDTILE_DRAGON  ]<=2 ? 1:0;//~1307I~
        ctr1+=itsHand[OFFS_WORDTILE_DRAGON+1]==1 && itsExposed[OFFS_WORDTILE_DRAGON+1]<=2 ? 1:0;//~1307I~
        ctr1+=itsHand[OFFS_WORDTILE_DRAGON+2]==1 && itsExposed[OFFS_WORDTILE_DRAGON+2]<=2 ? 1:0;//~1307I~
        ctr=ctr1+ctr2*2+ctr3*3;                                    //~1307I~
		TileData[][] pairOnEarth=AG.aPlayers.getEarth(playerDiscard);//~1307I~
        for (int ii=0;ii<PAIRS_MAX;ii++)                           //~1307I~
        {                                                          //~1307I~
            TileData[] tds=pairOnEarth[ii];                        //~1307I~
            if (tds==null)                                         //~1307I~
                break;                                             //~1307I~
            if (tds[0].type==TT_JI && tds[0].number>=TT_4ESWN_CTR)  //~1307I~
            	ctr3++;                                            //~1307I~
        }                                                          //~1307I~
        if (ctr>=HV_SET_INTENT_3DRAGON)                                //~1307I~
        	intent|=INTENT_3DRAGON;	//>=6              //~1307I~
        if (Dump.Y) Dump.println("RADSmart.chkIntent3Dragon rc=intent=x"+Integer.toHexString(intent)+",ctr="+ctr+",ctr1="+ctr1+",ctr2="+ctr2+",ctr3="+ctr3);//~1307I~
        return intent;                                             //~1307I~
    }                                                              //~1307I~
}//class RADSmart
