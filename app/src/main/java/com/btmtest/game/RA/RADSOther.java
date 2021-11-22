//*CID://+vagiR~: update#= 202;                                    //+vagiR~
//**********************************************************************
//2021/11/12 vagi (Bug)chk Other;Other Reach is ignoreed when less remaining tile//+vagiI~
//2021/11/01 vafm (Bug)getCtrEarthChanta returns >0 even tanyao earth exist(chanta and tanyao)//~vafmI~
//2021/04/26 va8n release INTENT_GIVEUP_WEAK when shaten reached to 0//~va8nI~
//2021/01/07 va60 CalcShanten
//**********************************************************************
//*Smart discard, check other player status
//**********************************************************************
package com.btmtest.game.RA;
import android.graphics.Point;
import java.util.Arrays;

import com.btmtest.TestOption;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.TestOption.*;

import static com.btmtest.StaticVars.AG;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
import static com.btmtest.game.Tiles.*;

//********************************************************************************************
public class RADSOther
{
	private RoundStat RS;
	private RADiscard RAD;
	private RADSmart RADS;
//  private int ctrPairOther,statPairOther;                        //~vafmR~
    private int ctrPairOther;                                      //~vafmI~
    private int[] itsStatPairOther;
    private int[] itsMarkPlayer=new int[PLAYERS];
    private int[] itsHandValueMark=new int[HANDCTR_TAKEN];         //~1220I~
    private int[] itsHandValueMarkSumm=new int[HANDCTR_TAKEN];	//summary fo all player to mark//~1302I~
    private int[] itsStatDiscard;
    private int[] itsHand;
    private int[] itsHandValue;
    private int[] itsHandPos;                                      //~1220I~
    private int ctrHand;
    private TileData[] tdsHand;
    private int cutEswn;                                           //~1311I~
//*************************
	public RADSOther()
    {
        if (Dump.Y) Dump.println("RADSOther.Constructor");
        init();
    }
    //*********************************************************
    private void  init()
    {
    	RAD=AG.aRADiscard;
    	RADS=AG.aRADSmart;
    	RS=AG.aRoundStat;
    }
    //***********************************************************************
    //*from RADSEval.evaluateTile                                  //~1309I~
    //***********************************************************************//~1309I~
    public void chkOtherPlayer(int PmyShanten,int PhanMax,int PctrWinTile,int PeswnDiscard,int[] PitsHand,int PctrHand)//~1216R~
    {
//      Point p=new Point();                                       //~1222R~
        //************************************
        if (Dump.Y) Dump.println("RADSOther.chkOtherPlayer myShanten="+PmyShanten+",hanMax="+PhanMax+",ctrWinTile="+PctrWinTile+",eswnDiscard="+PeswnDiscard+",RADS.myShanten="+RADS.myShanten);//~1216R~//~1220R~//~1303R~//+vagiR~
        ctrHand=PctrHand;
                                                                   //~1121I~
        itsHand=PitsHand;                                          //~1121M~
        tdsHand=RADS.tdsHand;
        itsHandValue=RADS.itsHandValue;
        itsHandPos=RADS.itsHandPos;                                //~1220I~
    	chkOtherPlayer(PmyShanten,PhanMax,PctrWinTile,PeswnDiscard);                      //~1121I~//~1216R~
        itsHand=null;                                              //~1121I~
        tdsHand=null;     //for gc                                 //~1121I~
        itsHandValue=null;                                         //~1121I~
        itsHandPos=null;                                           //~1220I~
    }
    //***********************************************************************//~1121I~
    private void chkOtherPlayer(int PmyShanten,int PhanMax,int PctrWinTile,int PeswnDiscard)      //~1121I~//~1216R~
    {                                                              //~1121I~
        Point p=new Point();                                       //~1121I~//~1222R~
        int ctrOtherReach=0;                                       //~1223I~
        //************************************                     //~1121I~
        if (Dump.Y) Dump.println("RADSOther.chkOtherPlayer entry myShanten="+PmyShanten+",hanmax="+PhanMax+",ctrWintile="+PctrWinTile+",eswnDiscard="+PeswnDiscard+",RADS.myShanten="+RADS.myShanten);//~1121I~//~1216R~//+vagiR~
		int ctrDora=RADS.getCtrDoraInHandAndEarth(PeswnDiscard);   //~1314I~
        cutEswn=AG.aAccounts.getCutEswn();                            //~1311I~
        int intent=RS.RSP[PeswnDiscard].getIntent();               //+vagiI~
        if (Dump.Y) Dump.println("RADSOther.chkOtherPlayer ctrDora="+ctrDora+",cutEswn="+cutEswn+",intent="+Integer.toHexString(intent));//+vagiI~
        Arrays.fill(itsMarkPlayer,0);                              //~1121I~
        int ctrOtherReacher=RS.getCtrOtherReach(PeswnDiscard);     //~1310I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~1121I~
        {                                                          //~1121I~
        	if (ii==PeswnDiscard)                                  //~1121I~
            	continue;                                          //~1121I~
        	itsStatPairOther=RS.RSP[ii].getPairStatistic(p);       //~1121I~
        	ctrPairOther=p.x;                                      //~1121I~
//      	statPairOther=p.y;                                     //~1121I~//~vafmR~
            itsStatDiscard=RS.RSP[ii].getDiscardStatistic();       //~1121I~
            if (RADS.myShanten>1)                                  //~1121I~
            {                                                      //~1121I~
                chkOtherPlayerSameColor(PeswnDiscard,ii);                         //~1121I~//~1215R~//~1217R~
                chkOtherPlayer13Orphan(ii);                          //~1121I~//~1215R~
//              chkOtherPlayerAllSame(); //TODO                    //~1121I~
            }                                                      //~1121I~
//          if (PhanMax<HV_HAN_MARKOTHER)   //4:if less than mangan(4han), hanMax is set if shanten==0//~1121I~//~1216R~//~1314R~
            if (PhanMax<HV_HAN_MARKOTHER   //4:if less than mangan(4han), hanMax is set if shanten==0//~1314I~
			&&  ctrDora<HV_DORA_MARKOTHER)   //<3, if dora>=3 ignore other//~1314I~
//  		    chkOtherPlayerMark(ii,PmyShanten,PhanMax,PctrWinTile,ctrOtherReacher);                            //~1121I~//~1216R~//~1310R~//+vagiR~
    		    chkOtherPlayerMark(ii,PmyShanten,PhanMax,PctrWinTile,ctrOtherReacher,intent);//+vagiI~
            else                                                   //+vagiI~
            if ((intent & INTENT_GIVEUP)!=0)                       //+vagiI~
			    chkOtherPlayerMark(ii,PmyShanten,PhanMax,PctrWinTile,ctrOtherReacher,intent);//+vagiI~
            if ((itsMarkPlayer[ii] & OTHER_MARK_REACH)!=0)         //~1223I~
            	if (ii!=PeswnDiscard)                              //~1223I~
                	ctrOtherReach++;                               //~1223I~
        }                                                          //~1121I~
        //set value                                                //~1217I~
//      int intent=RS.RSP[PeswnDiscard].getIntent();                      //~1223I~//+vagiR~
        if (ctrOtherReach>=HV_GIVEUP_BY_MULTIPLE_REACH)   //     =2;      //when other reach player>=2 set intent giveup//~1223I~
        	intent|=INTENT_GIVEUP;                                 //~1223I~
        int remainingTile=RAUtils.getCtrRemain();                  //~1224I~
        if (remainingTile<HV_CTR_UPTO_DRAW)            // 4*4         //~1224I~//~1302R~//~1311R~
        {                                                          //~1224I~
        	if (Dump.Y) Dump.println("RADSOther.chkOtherPlayer set giveup before myShanten="+PmyShanten+",remain="+remainingTile+",intent="+Integer.toHexString(intent));//~va8nI~
            if (PmyShanten>1)                                      //~1224I~
    	    	intent|=INTENT_GIVEUP;                             //~1224I~
            else                                                   //~1224I~
            if (PmyShanten==1)                                     //~1224I~
            {                                                      //~va8nI~
    	    	intent&=~INTENT_GIVEUP;                        //~1224I~//~va8nR~
    	    	intent|=INTENT_GIVEUP_WEAK;                        //~va8nI~
            }                                                      //~va8nI~
            else                                                   //~va8nI~
            if (PmyShanten==0)                                     //~va8nI~
    	    	intent&=~INTENT_GIVEUP_WEAK;                       //~va8nI~
        	if (Dump.Y) Dump.println("RADSOther.chkOtherPlayer@@@@ set giveup by remainingTile and shanten after intent="+Integer.toHexString(intent));//~1224I~//~va8nR~
        }                                                          //~1224I~
        RS.RSP[PeswnDiscard].setIntent(intent);                    //~1224I~
    	Arrays.fill(itsHandValueMarkSumm,0);                      //~1302I~
        boolean sw1st=true;                                        //~1302I~
        boolean swDoMark=false;                                    //~1313I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~1217I~
        {                                                          //~1217I~
			if (itsMarkPlayer[ii]!=0)                              //~1217I~
            {                                                      //~1302I~
                swDoMark=true;                                     //~1313I~
//    			setMarkOtherPlayer(PeswnDiscard,ii,PmyShanten,PhanMax,itsMarkPlayer[ii]);//~1217I~//~1219R~//~1220R~
    			setMarkOtherPlayerByHandValue(PeswnDiscard,ii,intent,PmyShanten,PhanMax,itsMarkPlayer[ii]);//~1220I~//~1223R~
                if (sw1st)                                         //~1302I~
					System.arraycopy(itsHandValueMark,0,itsHandValueMarkSumm,0,itsHandValueMark.length);//~1302I~
                else                                               //~1302I~
                {                                                  //~1302I~
                	sw1st=false;                                   //~1302I~
                	for (int jj=0;jj<itsHandValueMark.length;jj++)//~1302I~
                    {                                              //~1302I~
                    	if (itsHandValueMark[jj]<itsHandValueMarkSumm[jj])//~1302I~
							itsHandValueMarkSumm[jj]=itsHandValueMark[jj];//~1302I~
                    }                                              //~1302I~
                }                                                  //~1302I~
            }                                                      //~1302I~
        }                                                          //~1217I~
        if (swDoMark)                                              //~1313I~
            for (int jj=0;jj<itsHandValueMark.length;jj++)            //~1302I~//~1313R~
            {                                                          //~1302I~//~1313R~
                if (Dump.Y) Dump.println("RADSOther.chkOtherPlayer old itsHandValue["+jj+"]="+itsHandValue[jj]+",markSumm["+jj+"]="+itsHandValueMarkSumm[jj]);//~1302I~//~1313R~
                itsHandValue[jj]+=itsHandValueMarkSumm[jj];            //~1302I~//~1313R~
                if (Dump.Y) Dump.println("RADSOther.chkOtherPlayer new pos="+itsHandPos[jj]+",itsHandValue["+jj+"]="+itsHandValue[jj]);//~1302I~//~1311R~//~1313R~
            }                                                          //~1302I~//~1313R~
        if (Dump.Y) Dump.println("RADSOther.chkOtherPlayer@@@@exit eswnDiscard="+PeswnDiscard+",swDoMark="+swDoMark+",itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1302I~//~1313R~
    }                                                              //~1121I~
//    //***********************************************************************//~1215R~
//    private int getValueAllSameColorOther(int Ppos)              //~1215R~
//    {                                                            //~1215R~
//        int v=0;                                                 //~1215R~
//        if ((RADS.myIntent & INTENT_SAMECOLOR_MAN)!=0)           //~1215R~
//        {                                                        //~1215R~
//            if (Ppos/CTR_NUMBER_TILE!=0)                         //~1215R~
//                v+=DV_SAMECOLOR_OTHER;                           //~1215R~
//        }                                                        //~1215R~
//        else                                                     //~1215R~
//        if ((RADS.myIntent & INTENT_SAMECOLOR_PIN)!=0)           //~1215R~
//        {                                                        //~1215R~
//            if (Ppos/CTR_NUMBER_TILE!=1)                         //~1215R~
//                v+=DV_SAMECOLOR_OTHER;                           //~1215R~
//        }                                                        //~1215R~
//        else                                                     //~1215R~
//        {                                                        //~1215R~
//            if (Ppos/CTR_NUMBER_TILE!=2)                         //~1215R~
//                v+=DV_SAMECOLOR_OTHER;                           //~1215R~
//        }                                                        //~1215R~
//        if (Dump.Y) Dump.println("RADSOther.getValueAllSameColorOther rc="+v+",pos="+Ppos+",intent="+Integer.toHexString(RADS.myIntent));//~1215R~
//        return v;                                                //~1215R~
//    }                                                            //~1215R~
    //***********************************************************************
    private int getCautionLevelSameColor(int PeswnDiscard)         //~1217I~
    {                                                              //~1217I~
    	int rc=1;                                                  //~1217I~
        int intent=RS.RSP[PeswnDiscard].getIntent();               //~1217I~
        if ((intent & INTENT_GIVEUP_WEAK)!=0)                      //~1217I~
        	rc++;                                                  //~1217I~
        else                                                       //~1217I~
        if ((intent & INTENT_GIVEUP)!=0)                           //~1217I~
        	rc+=2;                                                 //~1217I~
        if (Dump.Y) Dump.println("RADSOther.getCautionLevelSameColor eswnDiscard="+PeswnDiscard+",rc="+rc);//~1217I~
        return rc;                                                 //~1217I~
    }                                                              //~1217I~
    //***********************************************************************//~1217I~
    private void chkOtherPlayerSameColor(int PeswnDiscard,int PotherEswn)           //~1215R~//~1217R~
    {
    	int[] its=itsStatDiscard;
        //************************************
    	int cautionLevel=getCautionLevelSameColor(PeswnDiscard);   //~1217I~
        int ctrDiscarded=RS.RSP[PotherEswn].ctrDiscarded;            //~1215I~
        if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerSameColor PeswnDiscard="+PeswnDiscard+",otherEswn="+PotherEswn+",ctrDiscarded="+ctrDiscarded+",RADS.myShanten="+RADS.myShanten);//~1215R~//~1303R~
        if (ctrDiscarded<HV_CTR_DISCARD_SAMECOLOR_OTHER)	//chk from 8 discarded//~1215I~
        	return;                                                //~1215I~
        int ctrWordStarting=its[CSI_WORD_STARTING];                                 //~1215I~//~1216I~
        if (ctrWordStarting>=HV_CTR_WORD_STARTING_SAMECOLOR)    // if word ctr >=2 at first 6 discard its is not samecolor//~1216I~//~1224R~
        {                                                          //~1216I~
	        if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerSameColor return by ctrWordStarting="+ctrWordStarting);//~1216I~
        	return;                                                //~1216I~
        }                                                          //~1216I~
        if (RS.RSP[PotherEswn].getPairCtr()==0)                    //~1302I~
        {                                                          //~1302I~
	        if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerSameColor return by no pair called");//~1302I~
        	return;                                                //~1302I~
        }                                                          //~1302I~
        int ctrMan=its[CSI_MAN];
        int ctrPin=its[CSI_PIN];
        int ctrSou=its[CSI_SOU];
        int colorOther=-1;
        int ctrOther;
        ctrOther=ctrPin+ctrSou;                                    //~1216R~
        if (ctrOther>0 && (double)ctrMan/ctrOther<HV_RATE_SAME_COLOR_OTHER)// the color/other color<0.2//~1215R~//~1216R~
            colorOther=0;
        else
        {
            ctrOther=ctrSou+ctrMan;                                //~1216R~
            if (ctrOther>0 && (double)ctrPin/ctrOther<HV_RATE_SAME_COLOR_OTHER)
                colorOther=1;
            else
            {
                ctrOther=ctrMan+ctrPin;                            //~1216R~
                if (ctrOther>0 && (double)ctrSou/ctrOther<HV_RATE_SAME_COLOR_OTHER)
                    colorOther=2;
            }
        }
        if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerSameColor ctrWordStarting="+ctrWordStarting+",colorOther="+colorOther+",ctrOther="+ctrOther+",ctrMan="+ctrMan+",ctrPin="+ctrPin+",ctrSou="+ctrSou);//~1216R~
        if (colorOther>=0)
        {
	        if (RS.RSP[PotherEswn].isSameColorEarth(colorOther))              //~1302I~
            {                                                      //~1302I~
	        	if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerSameColor return by other color called colorOther="+colorOther);//~1302I~
        			return;                                        //~1302I~
            }                                                      //~1302I~
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1215I~
                                    UView.showToastLong("RADSOther Other intent SAMECOLOR");//~1215I~//~1216R~
            for (int ii=0;ii<ctrHand;ii++)
            {
                if (tdsHand[ii].type==colorOther)
                {                                                  //~1215I~
        			if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerSameColor idx="+ii+",old="+itsHandValue[ii]);//~1215I~
                    itsHandValue[ii]+=DV_OTHER_SAMECOLOR*cautionLevel;  //set to 0//~1215R~//~1217R~
        			if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerSameColor@@@@ otherEswn="+PotherEswn+",new Value="+itsHandValue[ii]+",ctrPairOther="+ctrPairOther);//~1215I~//~1216R~
                }                                                  //~1215I~
            }
        }
    }
    //***********************************************************************
    private void chkOtherPlayer13Orphan(int PotherEswn)            //~1215R~
    {
    	int[] its=itsStatDiscard;                                  //~1215I~
        if (Dump.Y) Dump.println("RADSOther.chkOtherPlayer13Orphan ctrPairOther="+ctrPairOther+",itsStatDiscard="+ Utils.toString(its));//~1215R~
        if (ctrPairOther!=0)	//called once
        	return;
//      int ctrTanyao=its[CSI_TANYAO];                             //~1215R~
        int ctrAll=its[CSI_ALL];                                   //~1215R~
        int ctr13Orphan=its[CSI_TERMINAL]+its[CSI_WORD];           //~1215R~
        boolean swCaution=false;                                   //~1215R~
        if (ctrAll>=HV_CTR_CHK_OTHER_13ORPHAN && ctr13Orphan<=HV_CTR_OTHER_13ORPHAN)    //from 8 discard 19ji<=1//~1215R~
            swCaution=true;                                       //~1215R~
        if (Dump.Y) Dump.println("RADSOther.chkOtherPlayer13Orphand otherEswn="+PotherEswn+",ctaAll="+ctrAll+",ctr13Orphan="+ctr13Orphan+",swCaution="+swCaution);//~1215I~
        if (swCaution)                                             //~1215R~
        {
                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1215I~
                                    UView.showToastLong("RADSOther@@@@ Other intent 13ORPHAN");//~1215I~
            for (int ii=0;ii<ctrHand;ii++)
            {
            	int type=tdsHand[ii].type;
            	int num=tdsHand[ii].number;
                if (type==TT_JI ||  (num==0 || num==8))
                {                                                  //~1215I~
                    itsHandValue[ii]=DV_SET_OTHER_13ORPHAN;   //set 0     //~1213R~//~1215R~
        			if (Dump.Y) Dump.println("RADSOther.chkOtherPlayer13Orphand otherEswn="+PotherEswn+",new Value="+itsHandValue[ii]);//~1215I~
                }                                                  //~1215I~
            }
        }
    }
    //***********************************************************************
//  private void chkOtherPlayerMark(int Peswn/*other than me*/,int PmyShanten,int PhanMax,int PctrWinTile,int PctrOtherReacher)//~1216R~//~1220R~//~1310R~//+vagiR~
    private void chkOtherPlayerMark(int Peswn/*other than me*/,int PmyShanten,int PhanMax,int PctrWinTile,int PctrOtherReacher,int Pintent)//+vagiI~
    {
        if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerMark eswn="+Peswn+",shanten="+PmyShanten+",hanMax="+PhanMax+",ctrWinTile="+PctrWinTile+",ctrPairOther="+ctrPairOther+", RADS.myShanten="+RADS.myShanten+",ctrOtherReacher="+PctrOtherReacher+",intent="+Integer.toHexString(Pintent));//~1216R~//~1314R~//+vagiR~
        if (ctrPairOther>=HV_MARK_CALL)     //>=3 earth            //~1216R~//~1224R~
        {                                                          //+vagiI~
            if (PctrWinTile<HV_MARK_IGNORE_WINTILE)     //ignore other player reach if wintile>=4//~1216I~//~1220R~
            {                                                          //~1216I~//~1220R~
                                    if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1214I~//~1216I~//~1220R~
                                        UView.showToastLong("RADSOther Set mark earth ctr");//~1214I~//~1216R~//~1220R~
                if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerMark@@@@ mark by ctrEarth eswn="+Peswn+",ctrPairOther="+ctrPairOther);//~1216R~//~1220R~
                itsMarkPlayer[Peswn]|=OTHER_MARK_EARTH;                //~1219R~//~1220R~
            }                                                          //~1216I~//~1220R~
        }                                                          //+vagiI~
        else                                                       //~1220I~
        if (ctrPairOther>0)     //any erth                         //~1220I~
        	if (chkEarthDoraWord(Peswn))                           //~1220I~
            {                                                      //~1220I~
                                    if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1220I~
                                        UView.showToastLong("RADSOther Set mark by open DORA");//~1220I~
                if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerMark@@@@ mark by open DORA eswn="+Peswn);//~1220I~
            	itsMarkPlayer[Peswn]|=OTHER_MARK_DORA;             //~1220I~
            }                                                      //~1220I~
        if ((RS.RSP[Peswn].callStatus & CALLSTAT_REACH)!=0)
        {                                                          //~1223I~
        	if (PmyShanten>HV_MARK_NOTIGNORE_REACH_SHANTEN        //>1//~1307R~
//          ||  (RS.RSP[Peswn].callStatus & CALLSTAT_REACH_ONESHOT)!=0//~1308I~//~1309R~
            ||  ((RS.RSP[Peswn].callStatus & CALLSTAT_REACH_ONESHOT)!=0 && PhanMax<HV_MARK_IGNORE_REACH_ONESHOT_HAN) //ignore oneshot if han>=3//~1309I~
            ||  PmyShanten==0 && PctrWinTile<HV_MARK_IGNORE_WINTILE     //ignore other player reach if wintile>=4//~1216I~//~1220R~//~1223R~//~1307R~
            ||  PmyShanten>0 && RAUtils.getCtrRemain()<HV_SET_GIVEUP_REMAINING  //     =8;   //giveup at remaining<4 if shanten>0//~1311I~//~1314R~
            ||  PctrOtherReacher>=HV_GIVEUP_BY_MULTIPLE_REACH   //     =2;      //when other reach player>=2 set intent giveup//~1310I~
            ||  Peswn==ESWN_E || Peswn==cutEswn                   //mark reach if dealer or cutpos//~1315I~
            ||  (Pintent & INTENT_GIVEUP)!=0                     //mark REACH if already giveup//+vagiI~
            )                                                      //~1307I~
            {                                                          //~1216I~//~1220R~
                                    if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1216I~//~1220R~
                                        UView.showToastLong("RADSOther Set mark by reach");//~1216R~//~1220R~
                if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerMark@@@@ mark by reach eswn="+Peswn);//~1216R~//~1220R~
                itsMarkPlayer[Peswn]|=OTHER_MARK_REACH;                //~1219R~//~1220R~
                if ((RS.RSP[Peswn].callStatus & CALLSTAT_REACH_ONESHOT)!=0)//~1219I~//~1220R~
                    itsMarkPlayer[Peswn]|=OTHER_MARK_REACH_ONESHOT;    //~1219I~//~1220R~
            }                                                          //~1216I~//~1220R~
            if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerMark other reach chk shanten="+PmyShanten+",otherEswn="+Peswn+",ctrWinTile="+PctrWinTile+",PctrOtherReacher="+PctrOtherReacher+",ctrRemain="+RAUtils.getCtrRemain()+",callStatus="+Integer.toHexString(RS.RSP[Peswn].callStatus));//~1314I~//+vagiR~
        }                                                          //~1223I~
//      int remainingTile=PIECE_TILECTR-RS.ctrTakenAll-TILECTR_KEEPLEFT;
//        int remainingTile=RAUtils.getCtrRemain();                //~1224R~
//        if (ctrPairOther>=1 & remainingTile<CTR_UPTO_DRAW)       //~1224R~
//        {                                                          //~1216I~//~1224R~
//                                if ((TestOption.option2 & TO2_ROBOT_TOAST)!=0)//~1216I~//~1224R~
//                                    UView.showToastLong("RADSOther Set mark by remaining tile ct");//~1216R~//~1224R~
//            if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerMark@@@@ mark by remaining tile eswn="+Peswn+",remainingTile="+remainingTile);//~1216R~//~1224R~
//            itsMarkPlayer[Peswn]|=OTHER_MARK_DRAWING;              //~1219R~//~1224R~
//        }                                                          //~1216I~//~1224R~
//      if (itsMarkPlayer[Peswn]!=0)                               //~1217R~
//      	setMarkOtherPlayer(Peswn,PmyShanten,PhanMax);          //~1216R~//~1217R~
	    if (Dump.Y) Dump.println("RADSOther.chkOtherPlayerMark itsMarkPlayer=0x"+Utils.toHexString(itsMarkPlayer));//~1216I~//+vagiR~
    }
    //***********************************************************************//~1220M~
    private boolean chkEarthDoraWord(int Peswn)                    //~1220M~
    {                                                              //~1220M~
        int ctr=RADS.getCtrDoraEarth(Peswn);                       //~1220M~
        ctr+=RADS.getHanEarthWord(Peswn);                          //~1220M~
        boolean rc=ctr>=HV_HAN_EARTHOTHER;	//                =4;   //mark if opened han>=4//~1220M~
        if (Dump.Y) Dump.println("RADSOther.chkEarthDoraWord rc="+rc+",ctr="+ctr);//~1220M~
        return rc;                                                 //~1220M~
    }                                                              //~1220M~
    //***********************************************************************
    private int getForceMarkLevel(int Peswn,int PmyShanten,int PhanMax)//~1216R~
    {                                                              //~1216I~
        int rc=PmyShanten+1;                                       //~1216I~
        if (PhanMax>=HV_HAN_TO_MARK_IGNORE_OTHER)	//4:mangan     //~1216I~
        	rc--;                                                  //~1216I~
        if (Dump.Y) Dump.println("RADSOther.getForceMarkLevel rc="+rc+",eswn="+Peswn+",shanten="+PmyShanten+",hanMax="+PhanMax);//~1216I~
    	return rc;                                                 //~1216I~
    }                                                              //~1216I~
    //***********************************************************************//~1216I~
    //*set value for marked player                                 //~1216I~
    //***********************************************************************//~1216I~
    private void setMarkOtherPlayer_deprecated(int Peswn,int PeswnOther,int PmyShanten,int PhanMax,int Preason)//~1216I~//~1217R~//~1219R~//~1220R~
    {
        int intent=RS.RSP[Peswn].getIntent();                      //~1217I~
        if (Dump.Y) Dump.println("RADSOther.setMarkOtherPalyer eswn="+Peswn+",eswnOther="+PeswnOther+",shanten="+PmyShanten+",hanMax="+PhanMax+",intent="+Integer.toHexString(intent)+",reason="+Integer.toHexString(Preason));//~1216R~//~1217R~//~1219R~//~1302R~
        int forceLevel=getForceMarkLevel(Peswn,PmyShanten,PhanMax);                        //~1216I~
        for (int ii=0;ii<ctrHand;ii++)
        {
            int pos=RAUtils.getPosTile(tdsHand[ii]);
            if ((intent & INTENT_GIVEUP)!=0)                       //~1217I~
            {                                                      //~1217I~
                if (RAUtils.isFuriten(PeswnOther,pos))                      //~1216R~//~1217R~
                {                                                      //~1216I~//~1217R~
                    if (Dump.Y) Dump.println("RADSOther.setMarkOtherPalyer genbutu eswn="+PeswnOther+",pos="+pos+",old handValue["+ii+"]="+itsHandValue[ii]);//~1216R~//~1217R~
                    itsHandValue[ii]+=DV_MARK_OTHER_ITSELF*forceLevel;    // 1000   more discardable//~1216R~//~1217R~
                    if (Dump.Y) Dump.println("RADSOther.setMarkOtherPalyer genbutsu eswn="+PeswnOther+",handValue["+ii+"]="+itsHandValue[ii]);//~1216R~//~1217R~
                }                                                      //~1216I~//~1217R~
            }                                                      //~1217I~
            else                                                   //~1217I~
            if ((intent & INTENT_GIVEUP_WEAK)!=0 || (Preason & OTHER_MARK_REACH_ONESHOT)!=0)                  //~1217I~//~1219R~
            {                                                      //~1217I~
                if (pos<OFFS_WORDTILE) //number Tile                   //~1216I~//~1217R~
                {                                                      //~1216I~//~1217R~
                    int num=pos%CTR_NUMBER_TILE;                       //~1216I~//~1217R~
                    if (num<3 && RAUtils.isFuriten(PeswnOther,pos+3)      //1,2,3 <--4,5,6//~1216I~//~1217R~
                    ||  num>5 && RAUtils.isFuriten(PeswnOther,pos-3))     //7,8,9 <--4,5,6//~1216I~//~1217R~
                    {                                                  //~1216I~//~1217R~
                        if (Dump.Y) Dump.println("RADSOther.setMarkOtherPalyer suji eswn="+PeswnOther+",pos="+pos+",old handValue["+ii+"]="+itsHandValue[ii]);//~1216I~//~1217R~
                        itsHandValue[ii]+=DV_MARK_OTHER_LINK*forceLevel;    // 1500   more discardable//~1216I~//~1217R~
                        if (Dump.Y) Dump.println("RADSOther.setMarkOtherPalyer suji eswn="+PeswnOther+",handValue["+ii+"]="+itsHandValue[ii]);//~1216I~//~1217R~
                    }                                                  //~1216I~//~1217R~
                }                                                      //~1216I~//~1217R~
            }                                                      //~1217I~
        }
    }
    //***********************************************************************//~1220I~
    //*set value for marked player                                 //~1220I~
    //***********************************************************************//~1220I~
    private void setMarkOtherPlayerByHandValue(int Peswn/*eswnDiscard*/,int PeswnOther,int Pintent,int PmyShanten,int PhanMax,int Preason)//~1220I~//~1223R~
    {                                                              //~1220I~
        if (Dump.Y) Dump.println("RADSOther.setMarkOtherPalyerByHandMalue eswn="+Peswn+",otherEswn="+PeswnOther+",cutEswn="+cutEswn+",myShanten="+PmyShanten+",reason="+Integer.toHexString(Preason));//~1221I~//~1311R~
//  	System.arraycopy(itsHandValue,0,itsHandValueMark,0,ctrHand);//~1220I~//~1302R~
    	Arrays.fill(itsHandValueMark,0);                          //~1302I~
//      int intent=RS.RSP[Peswn].getIntent();                      //~1220I~//~1223R~
		int weight;                                                //~1311I~
        if (PeswnOther==ESWN_E)                                    //~1311I~
            weight=DV_MARK_OTHER_BY_LEVEL_PARENT; 	   // 1000   more discardable//~1311I~
        else                                                       //~1311I~
            weight=DV_MARK_OTHER_BY_LEVEL_CHILD; 	   // 1500   more discardable//~1311I~
        if (PeswnOther==cutEswn)                                   //~1311I~
            weight+=DV_MARK_OTHER_BY_LEVEL_ADD_BY_CUTPOS;  // 1000   more discardable//~1311I~
    	for (int ii=0;ii<ctrHand;ii++)                             //~1220I~
        {                                                          //~1220I~
            int pos=itsHandPos[ii];                                //~1220R~
            int markLevel=chkSafe(Peswn,PeswnOther,PmyShanten,PhanMax,Preason,Pintent,pos);//~1220R~//~1223R~
            if (Dump.Y) Dump.println("RADSOther.setMarkOtherPalyerByHandValue old="+itsHandValue[ii]+",level="+markLevel+",eswn="+Peswn+",otherEswn="+PeswnOther+",myShanten="+PmyShanten+",hanMax="+PhanMax+",reason="+Integer.toHexString(Preason));//~1220R~//~1302R~
//          itsHandValue[ii]+=DV_MARK_OTHER_BY_LEVEL*markLevel; 	   // 1000   more discardable//~1220I~//~1302R~
//          itsHandValueMark[ii]+=DV_MARK_OTHER_BY_LEVEL*markLevel; 	   // 1000   more discardable//~1302I~//~1311R~
            itsHandValueMark[ii]+=weight*markLevel; 	   // 1000   more discardable//~1311I~
            if (Dump.Y) Dump.println("RADSOther.setMarkOtherPalyerByHandValue new pos="+itsHandPos[ii]+",markLevel="+markLevel+",itsHandValueMark["+ii+"]="+itsHandValueMark[ii]);//~1220R~//~1221R~//~1302R~//~1311R~
        }                                                          //~1220I~
    }                                                              //~1220I~
    //***********************************************************************//~1220I~
    private int chkSafe(int Peswn,int PeswnOther,int PmyShanten,int PhanMax,int Preason,int Pintent,int Ppos)//~1220R~
    {                                                              //~1220I~
        if (Dump.Y) Dump.println("RADSOther.chkSafe eswnDiscard="+Peswn+",eswnOther="+PeswnOther+",shanten="+PmyShanten+",hanMax="+PhanMax+",intent="+Integer.toHexString(Pintent)+",reason="+Integer.toHexString(Preason)+",pos="+Ppos);//~1220R~//~1223R~//~1310R~
        int levelDiscard=0;                                        //~1220I~
        boolean swItself=RAUtils.isFuriten(PeswnOther,Ppos);       //~1220I~
        boolean swLink=false;                                      //~1220I~
        boolean swLinkBeforeReach=false;   //link before reach     //~1311I~
        if (Ppos<OFFS_WORDTILE) //number Tile                      //~1220I~
        {                                                          //~1220I~
        	int num=Ppos%CTR_NUMBER_TILE;                           //~1220I~
            int levelLink;                                         //~1311I~
            if (num<=TN3)                                          //~1224R~
            {                                                      //~1311I~
//  			swLink=RAUtils.isFuriten(PeswnOther,Ppos+3);      //1,2,3 <--4,5,6//~1224I~//~1311R~
    			levelLink=RS.chkFuritenSelfBeforeReach(PeswnOther,Ppos+3);      //1,2,3 <--4,5,6//~1311I~
                if (levelLink>0)	    //link tile after reach    //~1311I~
                	swLink=true;                                   //~1311I~
                else                                               //~1311I~
                if (levelLink<0)        //link tile before reach   //~1311I~
                	swLinkBeforeReach=true;                        //~1311I~
            }                                                      //~1311I~
            else                                                   //~1224I~
            if (num>=TN7)                                          //~1224R~
            {                                                      //~1311I~
//  			swLink=RAUtils.isFuriten(PeswnOther,Ppos-3);     //7,8,9 <--4,5,6//~1224I~//~1311R~
    			levelLink=RS.chkFuritenSelfBeforeReach(PeswnOther,Ppos-3);     //7,8,9 <--4,5,6//~1311I~
                if (levelLink>0)                                   //~1311I~
                	swLink=true;                                   //~1311I~
                else                                               //~1311I~
                if (levelLink<0)                                   //~1311I~
                	swLinkBeforeReach=true;                        //~1311I~
            }                                                      //~1311I~
            else	//4--6                                         //~1224I~
            	swLink=RAUtils.isFuriten(PeswnOther,Ppos-3) && RAUtils.isFuriten(PeswnOther,Ppos+3);     //1(4)7,2(5)8,3(6)9//~1224I~
            if (!swLink)                                           //~1224R~
            {   			//wall                                            //~1222I~//~1223R~
        		int numTop=(Ppos/CTR_NUMBER_TILE)*CTR_NUMBER_TILE; //~1223I~
                switch(num)                                        //~1224I~
                {                                                  //~1224I~
                case TN1:                                          //~1224I~
                	swLink=RAUtils.isEmpty(Peswn,numTop+TN2) || RAUtils.isEmpty(Peswn,numTop+TN3);//~1224I~
                	break;                                         //~1224I~
                case TN2:                                          //~1224I~
                	swLink=RAUtils.isEmpty(Peswn,numTop+TN3) || RAUtils.isEmpty(Peswn,numTop+TN4);//~1224I~
                	break;                                         //~1224I~
                case TN3:                                          //~1224I~
                	swLink=RAUtils.isEmpty(Peswn,numTop+TN4) || RAUtils.isEmpty(Peswn,numTop+TN5);//~1224I~
                	break;                                         //~1224I~
                case TN7:                                          //~1224I~
                	swLink=RAUtils.isEmpty(Peswn,numTop+TN5) || RAUtils.isEmpty(Peswn,numTop+TN6);//~1224I~
                	break;                                         //~1224I~
                case TN8:                                          //~1224I~
                	swLink=RAUtils.isEmpty(Peswn,numTop+TN6) || RAUtils.isEmpty(Peswn,numTop+TN7);//~1224I~
                	break;                                         //~1224I~
                case TN9:                                          //~1224I~
                	swLink=RAUtils.isEmpty(Peswn,numTop+TN7) || RAUtils.isEmpty(Peswn,numTop+TN8);//~1224I~
                	break;                                         //~1224I~
                }                                                  //~1224I~
            }                                                      //~1222I~
        	if (Dump.Y) Dump.println("RADSOther.chkSafe swItself="+swItself+",swLink="+swLink+",Ppos="+Ppos+",num="+num);//~1224I~
        }                                                          //~1220I~
        int force=0;                                               //~1220I~
        if ((Pintent & INTENT_GIVEUP)!=0)                          //~1220I~
        	force=2;                                               //~1220I~
        else                                                       //~1220I~
        if ((Pintent & INTENT_GIVEUP_WEAK)!=0)                     //~1220I~
        	force=1;                                               //~1220I~
        else                                                       //~1220I~
        {                                                          //~1220I~
        	if ((Preason & (OTHER_MARK_REACH_ONESHOT|OTHER_MARK_DORA))!=0)//~1220I~//~1221R~//~1224R~
        		force=2;                                           //~1220I~
            else                                                   //~1220I~
        		force=1;                                           //~1220I~
        	if (PmyShanten==0)                                     //~1220R~
            {                                                      //~1220I~
            	force--;                                           //~1220I~
        		if (PhanMax>=HV_HAN_TO_MARK_IGNORE_OTHER	//4:mangan//~1220I~//~1221R~
                )                                                  //~1221I~
                	force--;                                       //~1221I~
            }                                                      //~1221I~
            else                                                   //~1220I~
        	if (PmyShanten==1)                                     //~1220I~
            {                                                      //~1220I~
            	force--;                                           //~1220I~
            }                                                      //~1220I~
        }                                                          //~1220I~
        if (force==2)                                              //~1220I~
        {                                                          //~1220I~
        	if (swItself)                                          //~1220I~
            	levelDiscard=DV_MARK_OTHER_LEVEL_ITSELF;       //=5,000,000;  //add discadable by DV_MARK_OTHER_BY_LEVEL:1000//~1220I~//~1222R~
            else                                                   //~1220I~
        	if (swLink || Ppos>=OFFS_WORDTILE)                    //~1220I~
            	levelDiscard=DV_MARK_OTHER_LEVEL_LINK;         //=4,000,000;  //add discadable by DV_MARK_OTHER_BY_LEVEL:1000//~1220I~//~1222R~
            else                                                   //~1311I~
        	if (swLinkBeforeReach)                                 //~1311I~
            	levelDiscard=DV_MARK_OTHER_LEVEL_WARNING;         //=1,000,000;  //add discadable by DV_MARK_OTHER_BY_LEVEL:1000//~1311I~
        }                                                          //~1220I~
        else                                                       //~1220I~
        if (force==1)                                              //~1220I~
        {                                                          //~1220I~
        	if (swItself || swLink || Ppos>=OFFS_WORDTILE)        //~1220R~//~1224R~
            	levelDiscard=DV_MARK_OTHER_LEVEL_LINK;         //=4,000,000 ;  //add discadable by DV_MARK_OTHER_BY_LEVEL:1000//~1220R~//~1222R~
            else                                                   //~1311I~
        	if (swLinkBeforeReach)                                 //~1311I~
            	levelDiscard=DV_MARK_OTHER_LEVEL_WARNING2;                  //=  200,000; <reach:3,000,000 allow reach//~1311I~
        }                                                          //~1220I~
        else                                                       //~1220I~
        {                                                          //~1220I~
        	if (swItself || swLink || Ppos>=OFFS_WORDTILE)        //~1220I~//~1224R~
            	levelDiscard=DV_MARK_OTHER_LEVEL_WARNING;      //=1,000,000; <reach:3,000,000 allow reach//~1222R~
            else                                                   //~1311I~
        	if (swLinkBeforeReach)                                 //~1311I~
            	levelDiscard=DV_MARK_OTHER_LEVEL_WARNING2;                  //=  200,000; <reach:3,000,000 allow reach//~1311I~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RADSOther.chkSafe swItself="+swItself+",swLink="+swLink+",Ppos="+Ppos+",force="+force+",level="+levelDiscard);//~1220R~
        return levelDiscard;
    }                                                              //~1220I~
}//class RADSOther

