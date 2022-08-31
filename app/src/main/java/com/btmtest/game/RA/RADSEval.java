//*CID://+vapjR~: update#= 458;                                    //+vapjR~
//**********************************************************************
//2022/07/30 vapj 7pair reach, select non valueword                //+vapjI~
//2022/07/29 vapi isDoraOpen is duplicated effect(DV_DORA:-600)    //~vapiI~
//2022/07/29 vaph keep word tile if 7pair shanten==1               //~vaphI~
//2022/07/29 vapg reach by 7pair, skip reach if waiting dora       //~vapgI~
//2022/01/15 vaiv (bug)evaluateDiscard7Pair,if no furiten tile word tile was not selected//~vaivI~
//2022/01/15 vaiu 7pair evaluation;consider red5 and set base for num//~vaiuI~
//2022/01/13 vaim select tile to discard to avoid furiten at tryNextTake//~vaimI~
//2021/11/01 vafn chk ronable(inclucding 2han constraint) required if shanten up to 0 in Not AllInHand//~vafnI~
//2021/10/28 vaff pon/chii call for INTENT_CHANTA                  //~vaffI~
//2021/10/28 vafc pon/chii call for INTENT_TANYAO                  //~vafbI~
//2021/10/28 vafb evaluate INTENT_3SAMESEQ                         //~vafbI~
//2021/10/27 vaf9 evaluate INTENT_STRAIGHT                         //~vaf9I~
//2021/10/26 vaf6 (Bug)have to ignore shanten Down for INTENT_3DRAGON//~vaf6I~
//2021/07/29 vabq (Bug)evaluate NextTake should evaluate for same pos except TN5(may be red5), and should set amtHanMax if not sey itsHanAmt=0.//~vabqI~
//2021/07/27 vabb evaluate value of allsame; more minus for trplet over pair//~vabbI~
//2021/07/25 vab9 ignore shanten Up/Down if once called pon/chii according intent//~vab9I~//~vabqR~
//2021/07/25 vab5 itsHandValue up by hanMaxed should be by rank exceptDora//~vab3I~
//2021/07/25 vab3 selectrMeld;select if possibility of dor even not red5 exist//~vab3I~
//2021/07/14 vaaK red5 dora chk error; At getvalue from TryNext chkRedTile count tile of try discard//~vaaKI~
//2021/07/12 vaaE select word tile to discard if winlist=1 also for NOT reach//~vaaEI~
//2021/07/11 vaaC set intent tanyao/chanta/samecolor for also 7pair//~vaaCI~
//2021/07/05 vaan (Bug)handValue of maxHan should be inclreased for discard priority up//~vaanI~
//2021/07/03 vaaj (Bug)select large han even if amt is same(hanMax was not set)//~vaajI~
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/01/07 va60 CalcShanten
//**********************************************************************
package com.btmtest.game.RA;
import android.graphics.Point;

import com.btmtest.dialog.RuleSetting;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;                           //~va60I~
import static com.btmtest.game.Tiles.*;

//********************************************************************************************
public class RADSEval
{

	private RoundStat RS;
	private RoundStat.RSPlayer RSP;
	private RADSmart RADS;
	private RADSOther RADSO;
	private RADSENum  RADSEN;                                      //~1225I~
    private int ctrHand;
    private int[] itsHand;	//34 entry
    private int[] itsHandValue,itsHandPos;                         //~1122R~
//    private int[] itsHandNumberStat=new int[HANDCTR_TAKEN];
//  private int[] itsHandPosMeld=new int[HANDCTR_TAKEN];           //~1214I~//~1225R~
//    private int[] itsHandPosMeldCopy=new int[HANDCTR_TAKEN];       //~1217I~
//    private int[] itsHandValueMeld=new int[HANDCTR_TAKEN];         //~1214I~
//    private int[] itsHandMeldDora=new int[HANDCTR_TAKEN];          //~1216I~
//  private int[] itsStatHand,itsStatPair;                     //~1114R~//~1116R~//~1121I~//~1225R~
    private int eswnDiscard,playerDiscard;                         //~1121R~
//    private int[] itsDoraOpen=new int[2*(1+MAXCTR_KAN)];
    private int hanMax;
//    private int ctrMan,ctrPin,ctrSou,ctrWord;                    //~1131R~
    private TileData[] tdsHand;                                    //~1121I~//~1122R~
    private int ctrWinningTileTryNext;                             //~1216I~//~1220R~
    private int diffTryNext;                                       //~1303I~
    private int amtRonValue,amtMax,amtHanMax;                      //~vaajI~
    public  int posPillow;                                         //~1302R~
//  private int[] itsHanAmt=new int[CTR_TILETYPE];                 //~vaajI~//~vabqR~
    private int[] itsHanAmt=new int[HANDCTR_TAKEN];                //~vabqI~
    private int[]     itsWait1=new int[6];         //tanki list posDiscard/PosWait/WaitTileTotal//~vaaEI~
    private int       ctrWait1;                                    //~vaaEI~
    private int  ctrWinListTryNextTake,posWait1TryNextTake,ctrWait1TryNextTake,ctrWinListTryNextTakeTanki;//~vaaEI~
    private boolean swUseRed5;                                     //~vaaKI~
    private int addByForceIntentStandard;                          //~vabbI~
//*************************
	public RADSEval()
    {
        if (Dump.Y) Dump.println("RADSEval.Constructor");           //~1131R~//~vaajR~
        AG.aRADSEval=this;                                         //~1302I~
        init();
    }
    //*********************************************************
    private void  init()
    {
    	RADS=AG.aRADSmart;
    	RS=AG.aRoundStat;
    	RADSO=RADS.RADSO;
    	RADSEN=new RADSENum();                                     //~1225I~//~1302R~
		swUseRed5= RuleSetting.isUseRed5Tile();                     //~vaaKI~
    }
    //***********************************************************************
    public void evaluateHand(int PmyShanten,int PeswnDiscard,int[] PitsHand,int PctrHand,int[] PitsHandValue)//~1127R~
    {
        if (Dump.Y) Dump.println("RADSEval.evaluateHand myShanten="+PmyShanten+",eswnDiscard="+PeswnDiscard);//~1127R~//~1131R~//~vaajR~
        eswnDiscard=PeswnDiscard;
        playerDiscard=RADS.playerDiscard;                          //~1121I~
        ctrHand=PctrHand;
                                                                   //~1121I~
        itsHand=PitsHand;                                          //~1121M~
        itsHandValue=PitsHandValue;
        RSP=RS.RSP[eswnDiscard];                                  //~1121I~
//      itsStatHand=RADS.itsStatHand;                              //~1121I~//~1225R~
        tdsHand=RADS.tdsHand;                                      //~1121I~//~1122R~
        itsHandPos=RADS.itsHandPos;                                //~1122I~
                                                                   //~1121I~
//      RADS.myIntent=chkIntent();                                 //~1131R~
        evaluateTile(PmyShanten);                                  //~1127R~
                                                                   //~1121I~
        itsHand=null;      	//for gc
        itsHandValue=null;  //for gc
        RSP=null;                                                  //~1121I~
//      itsStatHand=null;                                          //~1121I~//~1225R~
        tdsHand=null;                                              //~1121I~//~1122R~
        itsHandPos=null;                                           //~1122I~
    }
    //***********************************************************************//~va70R~
    //*from RAReach.selectDiscard for same HandValue               //~va70R~
    //***********************************************************************//~va70R~
    public int adjustByTileForReach(int Peswn,int Ppos,int Pidx,int[] PitsHand,int PctrHand)//~va70R~
    {                                                              //~va70R~
    	int v;                                                     //~va70M~
        itsHand=PitsHand; ctrHand=PctrHand;                        //~va70I~
        if (Dump.Y) Dump.println("RADSEval.adjustByTileForReach itsHand="+Utils.toString(PitsHand,9));//~va70I~//~vaajR~
    	TileData td=RADS.tdsHand[Pidx];   //evaluateHand is not called//~va70R~
        RSP=RS.RSP[Peswn];                                         //~va70I~
        if (Ppos>=OFFS_WORDTILE)   //WGR+ESWN                      //~va70R~
           	v=evaluateTileWord(Ppos);                              //~va70R~
        else                                                       //~va70R~
           	v=RADSEN.evaluateForReach(Ppos,RSP.getIntent(),td);    //~va70R~
//      if (RADS.isDoraOpen(td))      already added at RADSmart.callReach()~//~vapiR~
//      	v+=DV_DORA;                                            //~vapiR~
        if (Dump.Y) Dump.println("RADSEval.adjustByTileForReach idx="+Pidx+",pos="+Ppos+",rc=v="+v+",td="+td.toString());//~va70R~//~vapgR~
        return v;                                                  //~va70R~
    }                                                              //~va70R~
    //***********************************************************************
    private void evaluateTile(int PmyShanten)                      //~1127R~
    {
    	int v;
        int maxWinTile=0;                                          //~1216I~
//      int posHanMax=-1;                                          //~vabqR~
        int idxHanMax=-1;                                          //~vabqI~
        int hanAmtMax=0;//~1220R~
        TileData td;
        if (Dump.Y) Dump.println("RADSEval.evaluateTile swDoReach="+RADS.swDoReach+",itsHand="+Utils.toString(itsHand,9));//~1131R~//~1201R~//~vaajR~
        if (Dump.Y) Dump.println("RADSEval.evaluateTile before itsHandPos="+Utils.toString(itsHandPos,-1,ctrHand));//~1213R~//~va70R~//~vaajR~
        if (Dump.Y) Dump.println("RADSEval.evaluateTile before itsHandValue="+Utils.toString(itsHandValue,-1,ctrHand));//~1127R~//~1131R~//~1213R~//~vaajR~
        int intent=RSP.getIntent();                                //~1225I~//~1309M~
        posPillow=searchPillow4(intent);                           //~1302R~
        Arrays.fill(itsHanAmt,0);                                   //~vaajI~
//  	chkNumberStat();                                           //~1214R~
//  	chkNumberMeld();                                           //~1214R~
//  	RADSEN.chkNumberMeld2();                                          //~1214I~//~1225R~
//      boolean skipStandard=((intent & INTENT_7PAIR)!=0) || ((intent & INTENT_7PAIR)!=0 && PmyShanten==1);//~1314I~//~vaaCR~
        boolean skipStandard=((intent & INTENT_13ORPHAN)!=0) || ((intent & INTENT_7PAIR)!=0 && PmyShanten<=HV_INTENT_SHANTEN_7PAIR_ONLY);   //<=1//~vaaCI~
        if (!skipStandard)                                         //~1314I~
	    	RADSEN.chkNumberMeld2(eswnDiscard,intent,itsHand,itsHandPos,itsHandValue,ctrHand);//~1225R~//~1302R~//~1313R~
    	hanMax=0;
        int posOld=-1;                                             //~1220R~
        int amtOld=-1;                                             //~vabqI~
        int hanOld=-1;                                             //~vabqI~
        ctrWait1=0;                                                //~vaaEI~
        boolean swChkIntent=chkIntentTryDiscard(intent);                 //~vab9I~
        int addByForceIntent=0;                                    //~vabbI~
        if (swChkIntent)                                           //~vabbI~
        {                                                          //~vabbI~
            addByForceIntent=addByIgnoreRonvalue();	//minus value over Shanten Up/Down value//~vabbR~
        }                                                          //~vabbI~
	    if (Dump.Y) Dump.println("RADSEval.evaluateTile swChkIntent="+swChkIntent+",eswn="+eswnDiscard+",addByForceIntent="+addByForceIntent);//~vab9I~//~vabbR~
        for (int ii=0;ii<ctrHand;ii++)
        {
        	int pos=itsHandPos[ii];                                //~1122R~
            if (!RS.isDiscardable(eswnDiscard,pos))//chk pao openreach
            {
            	itsHandValue[ii]=DV_NOTDISCARDABLE;
	        	if (Dump.Y) Dump.println("RADSEval.evaluateTile notDiscardable ii="+ii+",handVal="+itsHandValue[ii]);//~1213I~//~vaajR~
                continue;
            }
//      	int shanten=0;                                         //~1122R~//~1127R~
//            if (!RADS.swDoReach)                                        //~1122I~//~1309R~
//            {                                                      //~1127I~//~1309R~
////              shanten=chkShantenAtDiscard(PmyShanten,ii,pos);               //~1122I~//~1127R~//~1309R~
//                chkShantenAtDiscard(PmyShanten,ii,pos);            //~1127I~//~1309R~
//            }                                                      //~1127I~//~1309R~
//        	evaluateIntent(ii,pos);                                //~1307I~//~vabbR~
//        	evaluateIntent(ii,pos,addByForceIntent);               //~vaphR~
          	evaluateIntent(ii,pos,addByForceIntent,PmyShanten);    //~vaphI~
          if (!skipStandard)                                       //~1314I~
          {                                                        //~1313I~
            if (pos>=OFFS_WORDTILE)   //WGR+ESWN
            {
            	v=evaluateTileWord(pos);
            }
            else           //number
            {
//          	v=evaluateTileNumber(ii,pos,tdsHand[ii]);          //~1122R~//~1214R~
              	v=RADSEN.evaluateTileNumberMeld(intent,ii,tdsHand[ii]);                      //~1214I~//~1215R~//~1225R~
            }
        	if (Dump.Y) Dump.println("RADSEval.evaluateTile add pos="+pos+",idx="+ii+",old="+itsHandValue[ii]);//~1214I~//~1218R~//~vaajR~
	        itsHandValue[ii]+=v;                                   //~1122R~//~1126R~
        	if (Dump.Y) Dump.println("RADSEval.evaluateTile add exposed v="+v+",ii="+ii+",handVal="+itsHandValue[ii]);//~1126I~//~1131R~//~vaajR~
          }                                                        //~1313M~
//      	evaluateIntent(ii,pos);                                //~1307R~
	        int han=0;                                               //~1122R~
	        int amt=0;                                             //~vaajI~
	        ctrWinListTryNextTake=0;                               //~vaaEI~
	        ctrWinListTryNextTakeTanki=0;                          //~vaaEI~
//            if (swChkIntent && isIgnoreRonValue(itsHand,pos,intent))    //have to follow intent//~vab9R~//~vabbR~
//            {                                                      //~vab9I~//~vabbR~
//                if (Dump.Y) Dump.println("RADSEval.evaluateTile skip shanten Up/Down by intent pos="+pos);//~vab9I~//~vabbR~
//                addByIgnoreRonvalue(ii,pos);                     //~vabbR~
//                continue;                                          //~vab9I~//~vabbR~
//            }                                                      //~vab9I~//~vabbR~
//          if (!RADS.swDoReach)    //evaluateWinlist done at RAReach//~1309R~
//          {                                                      //~1220I~//~1309R~
//          	boolean swSameAsPrev=pos==posOld;                   //~1220R~//~vabqR~
            	boolean swSameAsPrev=pos==posOld && (pos>=OFFS_WORDTILE || pos%CTR_NUMBER_TILE!=TN5);//~vabqI~
//              han=evaluateNextTake(ii,pos,shanten);	//han at ron if shanten=0;//~1122R~//~1127R~//~1220R~
                han=evaluateNextTake(ii,pos,PmyShanten,swSameAsPrev,intent);	//han at ron if shanten=0;//~1127I~//~1220R~//~1309R~
                if (swSameAsPrev)                                  //~vabqI~
                {                                                  //~vabqI~
                	amtHanMax=amtOld;                              //~vabqI~
                    han=hanOld;                                    //~vabqI~
                }                                                  //~vabqI~
                amt=amtHanMax;  //output of evaluateNextTake       //~vaajI~
//          }                                                      //~1220I~//~1309R~
            posOld=pos;                                            //~1220R~
            amtOld=amt;                                            //~vabqI~
            hanOld=han;                                            //~vabqI~
//          if (han>hanMax)	//shanten=0 and winnable, shanten is upped or same as previous; if same value is not up,so evaluate by han//~1220R~//~vaajR~
//          {                                                      //~1220I~//~vaajR~
//          	hanMax=han;                                        //~vaajR~
//              posHanMax=pos;                                     //~1220R~//~vaajR~
//          }                                                      //~1220I~//~vaajR~
		    if (Dump.Y) Dump.println("RADSEval.evaluateTile han="+han+",hanMax="+hanMax+",ctrWinListTryNextTake="+ctrWinListTryNextTake+",ctrWinListTryNextTakeTanki="+ctrWinListTryNextTakeTanki);//~vaaEI~
            if (han>=hanMax)	//shanten=0 and winnable, shanten is upped or same as previous; if same value is not up,so evaluate by han//~vaaEI~
            {                                                      //~vaaEI~
            	hanMax=han;                                        //~vaaEI~
                if (ctrWinListTryNextTake==1 && ctrWinListTryNextTakeTanki==1)	//tanki//~vaaEI~
                {                                                  //~vaaEI~
//                  if (ctrWait1<itsWait1.length)    //tanki list  //~vapgR~
                    if (ctrWait1+3<=itsWait1.length)    //tanki list	;+3 for safety//~vapgI~
                    {                                              //~vaaEI~
                        itsWait1[ctrWait1++]=ii;    //idx,discard tile cause wait1//~vaaEI~
                        itsWait1[ctrWait1++]=posWait1TryNextTake;   //discard tile cause wait1//~vaaEI~
                        itsWait1[ctrWait1++]=ctrWait1TryNextTake;  //discard tile cause wait1//~vaaEI~
                        if (Dump.Y) Dump.println("RADSEval.evaluateTile ctrWait1="+ctrWait1+",itsWait1="+Arrays.toString(itsWait1));//~vaaEI~//~vab5R~
                    }                                              //~vaaEI~
                }                                                  //~vaaEI~
            }                                                      //~vaaEI~
            int hanAmt=(han<<24)+amt;                              //~vaajR~
//          itsHanAmt[pos]=hanAmt;                                 //~vaajI~//~vabqR~
            itsHanAmt[ii]=hanAmt;   // in ctrHans                  //~vabqI~
        	if (Dump.Y) Dump.println("RADSEval.evaluateTile pos="+pos+",hanAmt="+Integer.toHexString(hanAmt)+",itsHanAmt="+Utils.toHexString(itsHanAmt,-1,ctrHand));//~vab5I~//~vapgR~
            if (hanAmt>hanAmtMax)	//shanten=0 and winnable, shanten is upped or same as previous; if same value is not up,so evaluate by han//~vaajI~
            {                                                      //~vaajI~
//              posHanMax=pos;                                     //~vabqR~
                idxHanMax=ii;                                      //~vabqI~
                hanAmtMax=hanAmt;//~vaajI~
		        if (Dump.Y) Dump.println("RADSEval.evaluateTile han="+han+",hanAmt="+Integer.toHexString(hanAmt)+",idxHanMax="+idxHanMax);//~vaajR~//~vapgR~
            }                                                      //~vaajI~
            if (maxWinTile<ctrWinningTileTryNext)                  //~1216I~
	            maxWinTile=ctrWinningTileTryNext;                  //~1216I~
        }
        if (Dump.Y) Dump.println("RADSEval.evaluateTile hanMax="+hanMax+",idxHanMax="+idxHanMax);//~vaajR~
//      if (posHanMax>=0)                                          //~1220R~//~vabqR~
        if (idxHanMax>=0)                                          //~vabqI~
        {                                                          //~1220I~
//  	    evaluateHanMax(posHanMax);                              //~1220I~//~vaajR~
//  	    evaluateHanMax(itsHanAmt,posHanMax);                   //~vaajI~//~vabqR~
    	    evaluateHanMax(itsHanAmt,ctrHand,idxHanMax);           //~vabqI~
        }                                                          //~1220I~
        if (ctrWait1==itsWait1.length)                             //~vaaEI~
	        evaluateWait1();                                       //~vaaEI~
        if (PmyShanten==0 && (intent & INTENT_7PAIR)!=0)           //~1307I~
	      	evaluateDiscard7Pair();                                //~1307I~
        if ((intent & INTENT_STRAIGHT)!=0)                         //~vaf9R~
        	evaluateIntentStraight(itsHandPos,ctrHand,itsHand);    //~vaf9R~
        if ((intent & INTENT_3SAMESEQ)!=0)                         //~vafbI~
        	evaluateIntent3SameSeq(itsHandPos,ctrHand,itsHand);    //~vafbI~
        if ((RS.getIntentCalled(eswnDiscard) & INTENT_TANYAO)!=0)  //~vafcI~
        	evaluateIntentTanyaoCalled(itsHandPos,ctrHand,itsHand);//~vafcI~
        if ((RS.getIntentCalled(eswnDiscard) & INTENT_CHANTA)!=0)  //~vaffI~
        	evaluateIntentChantaCalled(itsHandPos,ctrHand,itsHand);//~vaffI~
//      if (RADS.swDoReach)                                        //~1122I~//~1309R~
//          hanMax=RADS.hanMaxReach;                               //~1122I~//~1309R~
        RADSO.chkOtherPlayer(PmyShanten,hanMax,maxWinTile,eswnDiscard,itsHand,ctrHand);//~1216R~
        if (Dump.Y) Dump.println("RADSEval.evaluateTile after="+Utils.toString(itsHandValue,-1,ctrHand));//~1127R~//~1131R~//~vaajR~
        if (Dump.Y) Dump.println("RADSEval.evaluateTile after itsHandPos="+Utils.toString(itsHandPos,-1,ctrHand));//~1218I~//~vaajR~
//      if (Dump.Y) Dump.println("RADSEval.evaluateTile after itsHandMeld="+Utils.toString(itsHandPosMeld,-1,ctrHand));//~1218I~//~1225R~//~vaajR~
    }
    //***********************************************************************//~vaaEI~
    //*itsWait1: (posDiscard+posWait1)*2                           //~vaaEI~
    //***********************************************************************//~vaaEI~
    private void evaluateWait1()                                   //~vaaEI~
    {                                                              //~vaaEI~
        if (Dump.Y) Dump.println("RADSEval.evaluateWait1 ctrWait1="+ctrWait1+",itsWait1="+Arrays.toString(itsWait1));//~vaaEI~
        if (Dump.Y) Dump.println("RADSEval.evaluateWait1 entry itsHandValue="+Utils.toString(itsHandValue));//~vapgR~
        int  totalWait1=itsWait1[2];                               //~vaaEI~
        int  totalWait2=itsWait1[5];                               //~vaaEI~
        if (totalWait1==0 || totalWait2==0 || totalWait1!=totalWait2)//~vaaEI~
        {                                                          //~vaaEI~
	        if (Dump.Y) Dump.println("RADSEval.evaluateWait1 return by totalWait ctr");//~vaaEI~
        	return;                                                //~vaaEI~
        }                                                          //~vaaEI~
        int idxDiscard1=itsWait1[0];                               //~vaaEI~
        int idxDiscard2=itsWait1[3];                               //~vaaEI~
        int posWait1=itsWait1[1];                                  //~vaaEI~
        int posWait2=itsWait1[4];                                  //~vaaEI~
        boolean swTanyao1=RAUtils.isTanyaoTile(posWait1);          //~vaaEI~
        boolean swTanyao2=RAUtils.isTanyaoTile(posWait2);          //~vaaEI~
        int idxAdd=-1;                                             //~vaaEI~
        if (posWait1>=OFFS_WORDTILE)                               //~vaaEI~
        	idxAdd=idxDiscard1;                                    //~vaaEI~
	    else                                                       //~vaaEI~
        if (posWait2>=OFFS_WORDTILE)                               //~vaaEI~
        	idxAdd=idxDiscard2;                                    //~vaaEI~
	    else                                                       //~vaaEI~
        if (!swTanyao1)                                            //~vaaEI~
        	idxAdd=idxDiscard1;                                    //~vaaEI~
        else                                                       //~vaaEI~
        if (!swTanyao2)                                            //~vaaEI~
        	idxAdd=idxDiscard2;                                    //~vaaEI~
	    if (Dump.Y) Dump.println("RADSEval.evaluateWait1 idxAdd1="+idxAdd);//~vaaEI~
        if (idxAdd!=-1)                                            //~vaaEI~
        {                                                          //~vaaEI~
		    if (Dump.Y) Dump.println("RADSEval.evaluateWait1 old="+itsHandValue[idxAdd]);//~vaaEI~
        	itsHandValue[idxAdd]+=DV_WAIT1_CHANTA;	//	        = 2000000;		//discard for reach//~vaaEI~
		    if (Dump.Y) Dump.println("RADSEval.evaluateWait1 new="+itsHandValue[idxAdd]);//~vaaEI~
        }                                                          //~vaaEI~
        if (Dump.Y) Dump.println("RADSEval.evaluateWait1 exit itsHandValue="+Utils.toString(itsHandValue));//~vapgR~
    }                                                              //~vaaEI~
                                                                //~vaaEI~
    //***********************************************************************//~1220I~
    //*not shuntenUp but kept shanten=0; add to all tile of same pos value//~1220I~
    //***********************************************************************//~1220I~
////  private void evaluateHanMax(int PposHanMax)                        //~1220I~//~vaajR~//~vabqR~
//    private void evaluateHanMax(int[] PitsHanAmt,int PposHanMax) //~vaajR~//~vabqR~
//    {                                                              //~1220I~//~vabqR~
//        int hanAmtMax=PitsHanAmt[PposHanMax];                      //~vaajR~//~vabqR~
//        if (Dump.Y) Dump.println("RADSEval.evaluateHanMax pos="+PposHanMax+",itsHanAmt="+Utils.toHexString(PitsHanAmt,9));//~1220I~//~vaajR~//~vaanR~//~vabqR~
//      for (int jj=0;jj<CTR_TILETYPE;jj++)                          //~vaajI~//~vabqR~
//      {                                                            //~vaajI~//~vabqR~
//        int hanAmt=PitsHanAmt[jj];                                 //~vaajR~//~vabqR~
//        if (hanAmt!=hanAmtMax)                                     //~vaajR~//~vabqR~
//            continue;                                              //~vaajI~//~vabqR~
//        for (int ii=0;ii<ctrHand;ii++)                             //~1220I~//~vabqR~
//        {                                                          //~1220I~//~vabqR~
////          if (PposHanMax==itsHandPos[ii])                        //~1220I~//~vaajR~//~vabqR~
//            if (itsHandPos[ii]==jj)                                //~vaajI~//~vabqR~
//            {                                                      //~1220I~//~vabqR~
//                if (Dump.Y) Dump.println("RADSEval.evaluateHanMax old idxHandValue["+ii+"]="+itsHandValue[ii]);//~1220I~//~vaajR~//~vabqR~
////              itsHandValue[ii]+=DV_TRYNEXT_HANMAX; //-1000,000//~1220I~//~vaanR~//~vabqR~
//                itsHandValue[ii]+=-DV_TRYNEXT_HANMAX; //-(-1000,000)//~vaanI~//~vabqR~
//                if (Dump.Y) Dump.println("RADSEval.evaluateHanMax new idxHandValue["+ii+"]="+itsHandValue[ii]);//~1220I~//~vaajR~//~vabqR~
//            }                                                      //~1220I~//~vabqR~
//        }                                                          //~1220I~//~vabqR~
//      }                                                            //~vaajI~//~vabqR~
//    }                                                              //~1220I~//~vabqR~
    //***********************************************************************//~vabqI~
    private void evaluateHanMax(int[] PitsHanAmt,int PctrHand,int PidxHanMax)//~vabqI~
    {                                                              //~vabqI~
        int hanAmtMax=PitsHanAmt[PidxHanMax];                      //~vabqI~
        if (Dump.Y) Dump.println("RADSEval.evaluateHanMax ctrHand="+PctrHand+",idxHanMax="+PidxHanMax+",itsHanAmt="+Utils.toHexString(PitsHanAmt,-1,PctrHand));//~vabqR~
        for (int ii=0;ii<ctrHand;ii++)                             //~vabqI~
        {                                                          //~vabqI~
            if (PitsHanAmt[ii]==hanAmtMax)                         //~vabqR~
            {                                                      //~vabqI~
                if (Dump.Y) Dump.println("RADSEval.evaluateHanMax old idxHandValue["+ii+"]="+itsHandValue[ii]);//~vabqI~
                itsHandValue[ii]+=-DV_TRYNEXT_HANMAX; //-(-1000,000)//~vabqI~
                if (Dump.Y) Dump.println("RADSEval.evaluateHanMax new idxHandValue["+ii+"]="+itsHandValue[ii]);//~vabqI~
            }                                                      //~vabqI~
        }                                                          //~vabqI~
    }                                                              //~vabqI~
    //***********************************************************************//~vabbI~
    //*large minus value for tile follws intent                    //~vabbI~
    //***********************************************************************//~vabbI~
//  private void addByIgnoreRonvalue(int Pidx,int Ppos)            //~vabbR~
    private int addByIgnoreRonvalue()                              //~vabbR~
    {                                                              //~vabbI~
    	int ctrTileUp=HANDCTR*PIECE_DUPCTR;	//13*4                 //~vabbI~
    	int v=DV_SHANTEN_DOWN;       //-100,000                    //~vabbI~
    	v-=DV_SHANTEN_UP+DV_SHANTEN_UP_TILE*ctrTileUp;	//-(100,000+100*ctr)//~vabbI~
//      if (Dump.Y) Dump.println("RADSEval.AddByIgnoreRonValuechk old="+itsHandValue[Pidx]);//~vabbR~
//      itsHandValue[Pidx]+=v;                                     //~vabbR~
//      if (Dump.Y) Dump.println("RADSEval.chkShantenAtDiscard eswn="+eswnDiscard+",idx="+Pidx+",pos="+Ppos+",v="+v+",new HandValue="+itsHandValue[Pidx]);//~vabbR~
        if (Dump.Y) Dump.println("RADSEval.addByIgnoreRonValue eswn="+eswnDiscard+",addValue="+v+",DV_SHANTEN_DOWN="+DV_SHANTEN_DOWN+",DV_SHANTEN_UP="+DV_SHANTEN_UP+",DV_SHANTEN_UP_TILE="+DV_SHANTEN_UP_TILE+",ctrUpTile="+ctrTileUp);//~vabbI~//~vaf6R~
        return v;                                                  //~vabbI~
    }                                                              //~vabbI~
    //***********************************************************************
    private int chkShantenAtDiscard(int PmyShanten,int Pidx/*idx of tdsHand*/,int Ppos/*of tds[Pidx]*/,int Pintent)//~1126R~//~1127R~//~1309R~
    {
        int shanten=RAUtils.getShantenAdd(itsHand,ctrHand,Ppos,-1);
        if (Dump.Y) Dump.println("RADSEval.chkShantenAtDiscard shanten="+PmyShanten+"intent=" + Integer.toHexString(RADS.myIntent));//~1309I~//~vaajR~
        int v=0;                                                     //~1127I~
//      if (shanten>PmyShanten)                                    //~1127I~//~1302R~
        if (PmyShanten<=HV_NOIGNORE_SHANTEN_DOWN && shanten>PmyShanten)  //if shanten>4 no set INTENT_13ORPHAN; so intent>4 ignore shanten down//~1302R~
        {                                                          //~1127I~
            if ((Pintent & INTENT_SAMECOLOR_ANY) == 0)                //~1309I~
            {                                                      //~1309I~
                v = DV_SHANTEN_DOWN;          //-100,000                 //~1127I~//~1302R~//~1309R~
                if (Dump.Y) Dump.println("RADSEval.chkShantenAtDiscard shantenDOWN Pidx=" + Pidx + ",pos=" + Ppos + ",shanten=" + shanten + ",v=" + v);//~1309R~//~vaajR~
        	}                                                      //~1309R~
        }//~1127I~
        else                                                       //~1127I~
        if (shanten<PmyShanten) //step up to ron                   //~1215R~
        	v=(MAX_SHANTEN-shanten)*DV_SHANTEN;   //-1000; add plus to highvalue to more discardable//~1215R~//~1309R~
        else                                                       //~1215I~
            v=0;                                                   //~1215I~
        if (Dump.Y) Dump.println("RADSEval.chkShantenAtDiscard pos="+Ppos+",idx="+Pidx+",pos="+Ppos+",old="+itsHandValue[Pidx]);//~1214I~//~1215R~//~vaajR~
        itsHandValue[Pidx]+=v;
        if (Dump.Y) Dump.println("RADSEval.chkShantenAtDiscard eswn="+eswnDiscard+",idx="+Pidx+",pos="+Ppos+",PmyShanten="+PmyShanten+",shanten="+shanten+",v="+v+",new v="+itsHandValue[Pidx]);//~1126R~//~1127R~//~1131R~//~1215R~//~1219R~//~vaajR~
        return shanten;
    }
    //***********************************************************************
    private int evaluateTileWord(int Ppos)
    {
    	int v=0;
        int intent=RSP.getIntent();                                //~1307I~
//      int exposed=RS.itsExposed[pos]*DV_EXPOSED;                  //~1213I~//~1215I~
        int exposed=RS.itsExposed[Ppos];                           //~1215I~
        int ctr=itsHand[Ppos];  //>0 by tdsHand                    //~1215M~
        if (Dump.Y) Dump.println("RADSEval.evaluateTileWord Ppos="+Ppos+",ctrHand="+ctr+",exposed="+exposed+",intent=x"+Integer.toHexString(intent));//~1131R~//~1215R~//~1307R~//~vaajR~
        int expected=PIECE_DUPCTR-ctr-exposed;                     //~1215I~
        if (ctr==1 && expected==0)                                 //~1217I~
        {                                                          //~1217I~
			v+=DV_WORD_ORPHAN;    	//950 more discardable//~1217I~//~1220R~
            if (RADS.isDoraOpen(Ppos))                             //~1220I~
            {                                                      //~1220I~
				v-=DV_DORA;                                        //~1220I~
		        if (Dump.Y) Dump.println("RADSEval.evaluateTileWord remove dora effect Ppos="+Ppos+",ctrHand="+ctr+",exposed="+exposed);//~1220I~//~vaajR~
            }                                                      //~1220I~
		}                                                          //~1217I~
        else                                                       //~1217I~
        if (ctr>0)	//for safety                                   //~vaaCI~
        {                                                          //~1217I~
	        v+=DVS_WORD[ctr-1];                                         //~1215M~//~1217I~//~1307R~
            if (ctr<=2)                                                //~1215I~//~1217R~
            {                                                          //~1215I~//~1217R~
                if (expected>0)                                        //~1215I~//~1217R~
                {                                                      //~1215I~//~1217R~
            		if (ctr==1 && Ppos>=OFFS_WORDTILE_DRAGON && (intent & INTENT_3DRAGON)!=0)//~1307I~
                    {                                              //~1307I~
                    	v+=DV_WORD_YAKU*4+expected*DV_WORD_NOTEXPOSED;    //(950)-200*4-90*remaining//~1307I~
				        if (Dump.Y) Dump.println("RADSEval.evaluateTileWord 3dragon Ppos="+Ppos+",ctrHand="+ctr+",exposed="+exposed+",v="+v);//~1307I~//~vaajR~
                    }                                              //~1307I~
                    else                                           //~1307I~
                    {                                              //~1307I~
                    	int han=RAUtils.chkValueWordTile(Ppos,eswnDiscard);//~1220R~//~1302R~//~1307R~
                    	v+=DV_WORD_YAKU*han*ctr/2+expected*DV_WORD_NOTEXPOSED;    //-200*tileValue(han)*ctr-90*remaining//~1217R~//~1301R~//~1302R~//~1307R~
                    }                                              //~1307I~
                }                                                      //~1215I~//~1217R~
            }                                                          //~1215I~//~1217R~
        }                                                          //~1217I~
        v+=Utils.getRandom(DV_WORD_RANDOM_MAX);   //add 0->9 randomly//~1217I~
        if (Dump.Y) Dump.println("RADSEval.evaluateTileWord pos="+Ppos+",v="+v+",expected="+expected);//~1131R~//~1220R~//~vaajR~
        return v;
    }
    //***********************************************************************
//  private void evaluateIntent(int Pidx/*in tdsHand*/,int Ppos/*in itsHand*/)//~vabbR~
//  private void evaluateIntent(int Pidx/*in tdsHand*/,int Ppos/*in itsHand*/,int PaddByForceIntent)//~vaphR~
    private void evaluateIntent(int Pidx/*in tdsHand*/,int Ppos/*in itsHand*/,int PaddByForceIntent,int PmyShanten)//~vaphI~
    {
//      int intent=RSP.intent;                                     //~1302R~
        int intent=RSP.getIntent();                                //~1302I~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent idx="+Pidx+",pos="+Ppos+",intent="+Integer.toHexString(intent)+",eswnDiscard="+eswnDiscard+",myIntent=x"+Integer.toHexString(RADS.myIntent)+",PaddByForceIntent="+PaddByForceIntent);//~vaiuI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent old pos="+Ppos+",handValue="+itsHandValue[Pidx]);//~vaiuI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent entry handValue="+Utils.toString(itsHandValue));//~vapiI~
        if ((intent & INTENT_7PAIR)!=0)
        {                                                          //~1213I~
	        if (Dump.Y) Dump.println("RADSEval.evaluateIntent idx="+Pidx+",pos="+Ppos+",ctr="+itsHand[Ppos]+",sw7PairKan="+RS.sw7PairKan);//~1216I~//~vaajR~
            switch(itsHand[Ppos])                                  //~1216I~
            {                                                      //~1216I~
            case 2:                                                //~1216I~
	    		if (Dump.Y) Dump.println("RADSEval.evaluateIntent 7pair pair idx="+Pidx+",old="+itsHandValue[Pidx]);//~1214I~//~1216R~//~vaajR~
                itsHandValue[Pidx]+=DV_7PAIR;  //-30,000           //~1313R~
	        	if (Dump.Y) Dump.println("RADSEval.evaluateIntent 7pair pair idx="+Pidx+",handVal="+itsHandValue[Pidx]);//~1213I~//~1214R~//~1216R~//~vaajR~
                break;                                             //~1216I~
            case 3:                                                //~1216I~
	    		if (Dump.Y) Dump.println("RADSEval.evaluateIntent 7pair triplet idx="+Pidx+",old="+itsHandValue[Pidx]);//~1216I~//~vaajR~
                itsHandValue[Pidx]+=DV_7PAIR3;  //-20,000                   //~1216I~//~1313R~
	        	if (Dump.Y) Dump.println("RADSEval.evaluateIntent 7pair triplet idx="+Pidx+",handVal="+itsHandValue[Pidx]);//~1216I~//~vaajR~
                break;                                             //~1216I~
            case 4:                                                //~1216I~
            	if (RS.sw7PairKan)                                 //~1216I~
                {                                                  //~1216I~
	    			if (Dump.Y) Dump.println("RADSEval.evaluateIntent 7pair quad idx="+Pidx+",old="+itsHandValue[Pidx]);//~1216I~//~vaajR~
                	itsHandValue[Pidx]+=DV_7PAIR;  //-30,000                //~1216I~//~1313R~
	        		if (Dump.Y) Dump.println("RADSEval.evaluateIntent 7pair quad idx="+Pidx+",handVal="+itsHandValue[Pidx]);//~1216I~//~vaajR~
                }                                                  //~1216I~
            default:	//1                                        //~1313I~
            	boolean swDora=false;                              //~vaaCI~
		        if (tdsHand[Pidx].isRed5()                         //~1313I~
//              ||  RADS.isDoraOpen(Ppos))                         //~vapiR~
                )                                                  //~vapiI~
                {                                                  //~vaaCI~
	                itsHandValue[Pidx]+=DV_DORA;	//-600         //~1313I~
                    swDora=true;                                   //~vaaCI~
                }                                                  //~vaaCI~
        		if (Dump.Y) Dump.println("RADSEval.evaluateIntent add dora pos="+Ppos+",swDora="+swDora+",new handValue="+itsHandValue[Pidx]);//~vapiR~
//  		    evaluateIntent7PairAdditional(intent,swDora,Pidx,Ppos);//~vaphR~
    		    evaluateIntent7PairAdditional(intent,swDora,Pidx,Ppos,PmyShanten);//~vaphI~
            }                                                      //~1216I~
        }                                                          //~1213I~
        else                                                       //~1213I~
        if ((intent & INTENT_13ORPHAN)!=0)
        {                                                          //~1213I~
        	setValue13Orphan(true,Pidx,Ppos);                      //~1213R~
        }                                                          //~1213I~
        else                                                       //~1213I~
        {                                                          //~1213I~
        	addByForceIntentStandard=PaddByForceIntent;            //~vabbI~
//      	if ((intent & INTENT_TANYAO)!=0)                       //~1213R~//~vafcR~
        	if ((intent & INTENT_TANYAO)!=0 && (RSP.getIntentCalled() & INTENT_TANYAO)==0)//~vafcI~
//      		setValueTanyao(false/*sw13Orphan*/,true/*swMe*/);  //~1213R~
        		setValueTanyao(false/*sw13Orphan*/,true/*swMe*/,Pidx,Ppos);//~1213I~
            else                                                   //~1217I~
//      	if ((intent & INTENT_CHANTA)!=0)                       //~1217I~//~vaffR~
        	if ((intent & INTENT_CHANTA)!=0 && (RSP.getIntentCalled() & INTENT_CHANTA)==0)//~vaffI~
        		setValueChanta(true/*swMe*/,Pidx,Ppos);            //~1217I~
        	if ((RADS.myIntent & INTENT_SAMECOLOR_ANY)!=0)         //~1213R~
//      		setValueSameColor(true/*swMe*/);                   //~1213R~
        		setValueSameColor(true/*swMe*/,Pidx,Ppos);          //~1213I~
        	if ((RADS.myIntent & INTENT_ALLSAME)!=0)    //toitoi   //~1218I~
        		setValueAllSame(true/*swMe*/,Pidx,Ppos);           //~1218I~
        	if ((RADS.myIntent & INTENT_3DRAGON)!=0)    //toitoi   //~vaf6I~
        		setValue3Dragon(true/*swMe*/,Pidx,Ppos);           //~vaf6I~
        	addByForceIntentStandard=0;                            //~vabbI~
        }                                                          //~1213I~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent exit handValue="+Utils.toString(itsHandValue));//~vapiI~
	    if (Dump.Y) Dump.println("RADSEval.evaluateIntent exit idx="+Pidx+",handVal="+itsHandValue[Pidx]);//~vaf6I~
    }
    //***********************************************************************//~vaaCI~
    //*for 7Pair itsHand ctr==1                                    //~vapiR~
    //***********************************************************************//~vaiuI~
//  private void evaluateIntent7PairAdditional(int Pintent,boolean PswDora,int Pidx/*in tdsHand*/,int Ppos/*in itsHand*/)//~vaphR~
    private void evaluateIntent7PairAdditional(int Pintent,boolean PswDora,int Pidx/*in tdsHand*/,int Ppos/*in itsHand*/,int PmyShanten)//~vaphI~
    {                                                              //~vaaCI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent7PairAdditional SHANTEN="+PmyShanten+",PswDora="+PswDora+",intent="+Integer.toHexString(Pintent)+",pos="+Ppos+",idx="+Pidx+",old="+itsHandValue[Pidx]);//~vaphR~
        if ((Pintent & INTENT_TANYAO)!=0)                          //~vaaCR~
        {                                                          //~vaaCI~
        	setValueTanyao(false/*sw13Orphan*/,true/*swMe*/,Pidx,Ppos);	//add to 1/9/ji//~vaaCI~
            if (Ppos<OFFS_WORDTILE)                                //~vaaCI~
	            itsHandValue[Pidx]+=DVS_NUMBER_WEIGHT[Ppos%CTR_NUMBER_TILE];//~vaaCI~
        }                                                          //~vaaCI~
        else                                                       //~vaaCI~
        if ((Pintent & INTENT_CHANTA)!=0)                          //~vaaCR~
        {                                                          //~vaaCI~
		    setValueChanta(true/*swMe*/,Pidx,Ppos);   //add to tanyao tile//~vaaCI~
            if (Ppos<OFFS_WORDTILE)                                //~vaaCI~
	            itsHandValue[Pidx]+=DVS_NUMBER_WEIGHT_CHANTA[Ppos%CTR_NUMBER_TILE];//~vaaCI~
        }                                                          //~vaaCI~
//      else                                                       //~vaaCI~//~vaiuR~
//      if (!PswDora)                                             //~vaaCI~//~vaiuR~
//      {                                                          //~vaaCI~//~vaiuR~
//          if (RAUtils.isTanyaoTile(Ppos))                        //~vaaCI~//~vaiuR~
//  		    setValueChanta(true/*swMe*/,Pidx,Ppos);   //add to tanyao tile,select 1/9/ji//~vaaCR~//~vaiuR~
//      }                                                          //~vaaCI~//~vaiuR~
        if (Ppos>=OFFS_WORDTILE)                                   //~vaaCI~
        {                                                          //~vaiuI~
          if (PmyShanten==1 && (Pintent & INTENT_TANYAO)==0)       //~vaphI~
          {                                                        //~vaphI~
            int additional;                                        //~vaphI~
            if (RAUtils.chkValueWordTile(Ppos,eswnDiscard)>0)      //~vaphI~
            {                                                      //~vaphI~
              if (Ppos==OFFS_WORDTILE+eswnDiscard)	//keep my wind //+vapjR~
            	additional=-(DVS_NUMBER_WEIGHT[0]*5)/2;	//40*2.5=-100 more discardable over num tile//+vapjR~
              else                                                 //+vapjR~
            	additional=-DVS_NUMBER_WEIGHT[0]*2;	//-40*2 more discardable over num tile//+vapjR~
            }                                                      //~vaphI~
            else                                                   //~vaphI~
            {                                                      //~vaphI~
            	additional=-DVS_NUMBER_WEIGHT[0]*3;	//-40*3 more discardable over num tile//~vapiR~
            }                                                      //~vaphI~
                if (Dump.Y) Dump.println("RADSEval.evaluateIntent7PairAdditional for value word tile !tanyao and shanten==1  pos="+Ppos+",idx="+Pidx+",old="+itsHandValue[Pidx]);//~vaphI~
                itsHandValue[Pidx]+=additional;                    //~vaphI~
                if (Dump.Y) Dump.println("RADSEval.evaluateIntent7PairAdditional additional="+additional+",new="+itsHandValue[Pidx]);//~vaphI~
          }                                                        //~vaphI~
          else                                                     //~vaphI~
          {                                                        //~vaphI~
//          if (RAUtils.chkValueWordTile(Ppos,eswnDiscard)>0)      //~vaaCI~//~vaiuR~
//          {                                                      //~vaaCI~//~vaiuR~
//              int additional=DV_NOT_CHANTA/2;   //10000/2 //select honor tile over other word tile//~vaaCI~//~vaiuR~
            int additional;                                        //~vaiuI~
            if (RAUtils.chkValueWordTile(Ppos,eswnDiscard)>0)      //~vaiuI~
            {                                                      //~vaiuI~
            	additional=DVS_NUMBER_WEIGHT[0]*2;	//40*2 more discardable over num tile//~vaiuI~
            }                                                      //~vaiuI~
            else                                                   //~vaiuI~
            {                                                      //~vaiuI~
            	additional=DVS_NUMBER_WEIGHT[0]*3;	//40*3 more discardable over num tile//~vaiuR~
            }                                                      //~vaiuI~
                if (Dump.Y) Dump.println("RADSEval.evaluateIntent7PairAdditional for value word tile mix tanyao/chanta  pos="+Ppos+",idx="+Pidx+",old="+itsHandValue[Pidx]);//~vaaCI~
                itsHandValue[Pidx]+=additional;                    //~vaaCI~
                if (Dump.Y) Dump.println("RADSEval.evaluateIntent7PairAdditional additional="+additional+",new="+itsHandValue[Pidx]);//~vaaCI~//~vaiuR~
//          }                                                      //~vaaCI~//~vaiuR~
          }                                                        //~vaphI~
        }                                                          //~vaiuI~
        else                                                       //~vaiuI~
        {                                                          //~vaiuI~
        	int num=Ppos%CTR_NUMBER_TILE;                          //~vaiuI~
            int addByNum=DVS_NUMBER_WEIGHT[num];                   //~vaiuI~
			if (swUseRed5 && !PswDora)  //not red5 byt tn5         //~vaiuI~
            {                                                      //~vaiuI~
            	switch(num)                                        //~vaiuI~
                {                                                  //~vaiuI~
                case TN5:                                          //~vaiuI~
            		addByNum+=DV_DORA/2;                           //~vaiuI~
                    break;                                         //~vaiuI~
                case TN4:                                          //~vaiuI~
                case TN6:                                          //~vaiuI~
            		addByNum+=DV_DORA_NEAR1/2; //-100/2            //~vaiuI~
                    break;                                         //~vaiuI~
                case TN3:                                          //~vaiuI~
                case TN7:                                          //~vaiuI~
            		addByNum+=DV_DORA_NEAR2/2;  //-50/2            //~vaiuI~
                    break;                                         //~vaiuI~
                }                                                  //~vaiuI~
            }                                                      //~vaiuI~
            if (Dump.Y) Dump.println("RADSEval.evaluateIntent7PairAdditional add by Num pos="+Ppos+",idx="+Pidx+",old="+itsHandValue[Pidx]);//~vaiuI~
            itsHandValue[Pidx]+=addByNum;                          //~vaiuI~
            if (Dump.Y) Dump.println("RADSEval.evaluateIntent7PairAdditional new="+itsHandValue[Pidx]+",addbyNum="+addByNum+",swUseRed5="+swUseRed5);//~vaiuI~
        }                                                          //~vaiuI~
        if ((Pintent & INTENT_SAMECOLOR_ANY)!=0)                   //~vaaCR~
        {                                                          //~vaaCI~
           setValueSameColor(true/*PswMe*/,Pidx,Ppos);             //~vaaCI~
        }                                                          //~vaaCI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent7PairAdditional pos="+Ppos+",idx="+Pidx+",new="+itsHandValue[Pidx]);//~vaaCI~
    }                                                              //~vaaCI~
    //***********************************************************************//~1307I~
    //*at shanten0, select expected tile for 7Pair                 //~1307I~
    //***********************************************************************//~1307I~
    private void evaluateDiscard7Pair()                            //~1307I~
    {                                                              //~1307I~
        if (Dump.Y) Dump.println("RADSEval.evaluateDiscard7Pair entry itsHandValue="+Utils.toString(itsHandValue));//~vapgR~
//        int idxDiscard=-1;                                         //~1307I~//~vaivR~
//        int maxExpectedWord=0,maxExpected=0,maxExpectedFuriten=0,idxMaxExpectedWord=-1,idxMaxExpected=-1,idxMaxExpectedFuriten=-1;//~1307I~//~vaivR~
        for (int ii=0;ii<ctrHand;ii++)                             //~1307I~
        {                                                          //~1307I~
        	int pos=itsHandPos[ii];                                //~1307I~
            int ctrHand=itsHand[pos];                              //~vaivI~
	        if (Dump.Y) Dump.println("RADSEval.evaluateDiscard7Pair pos="+pos+",ctrHand="+ctrHand+",old itsHandValue["+ii+"]="+itsHandValue[ii]);//~vaivI~
            if (ctrHand>1)                                         //~vaivI~
            	continue;                                          //~vaivI~
            int expected=PIECE_DUPCTR-ctrHand-RS.itsExposed[pos]; //~1307I~//~vaivR~
	        if (Dump.Y) Dump.println("RADSEval.evaluateDiscard7Pair pos="+pos+",expected="+expected);//~vaivI~
            if (expected==0)                                       //~1307I~
            {                                                      //~1307I~
            	itsHandValue[ii]+=DV_DISCARD;	//100,000          //~1307R~
            	continue;                                          //~1307I~
            }                                                      //~1307I~
            int furiten=chkFuriten(pos);                           //~1307I~
            if (furiten<0)	//genbutu                              //~1307I~
            {                                                      //~1307I~
            	itsHandValue[ii]+=DV_DISCARD;	//100,000          //~1307R~
            	continue;                                          //~1307I~
            }                                                      //~1307I~
//            if (expected>maxExpected)                              //~1307I~//~vaivR~
//            {                                                      //~1307I~//~vaivR~
//                maxExpected=expected;                              //~1307I~//~vaivR~
//                idxMaxExpected=ii;                                 //~1307I~//~vaivR~
//            }                                                      //~1307I~//~vaivR~
            itsHandValue[ii]+=(PIECE_DUPCTR-expected)*DV_DISCARD/PIECE_DUPCTR;	//75,000(expected=1), 50,000(2), 25,000(3)//~vaivI~
            if (furiten>0) //link furiten                          //~1307I~
        	{                                                      //~1307I~
//                if (expected>maxExpectedFuriten)                   //~1307I~//~vaivR~
//                {                                                  //~1307I~//~vaivR~
//                    maxExpectedFuriten=expected;                   //~1307I~//~vaivR~
//                    idxMaxExpectedFuriten=ii;                      //~1307I~//~vaivR~
//                }                                                  //~1307I~//~vaivR~
            	itsHandValue[ii]-=DV_DISCARD/PIECE_DUPCTR;	//100,000/4//~vaivI~
            }                                                      //~1307I~
//          if (pos>=OFFS_WORDTILE && RS.itsExposed[pos]>0)           //~1307I~//~vaivR~
            else                                                   //~vaivI~
            if (pos>=OFFS_WORDTILE)                                //~vaivI~
        	{                                                      //~1307I~
//                if (expected>maxExpectedWord)                      //~1307I~//~vaivR~
//                {                                                  //~1307I~//~vaivR~
//                    maxExpectedWord=expected;                      //~1307I~//~vaivR~
//                    idxMaxExpectedWord=ii;                         //~1307I~//~vaivR~
//                }                                                  //~1307I~//~vaivR~
            	itsHandValue[ii]-=DV_DISCARD/PIECE_DUPCTR;	//100,000/4//~vaivI~
            }                                                      //~1307I~
        	if (Dump.Y) Dump.println("RADSEval.evaluateDiscard7Pair new furiten="+furiten+",pos="+pos+",itsHandValue["+ii+"]="+itsHandValue[ii]);//~vaivI~
        }                                                          //~1307I~
////      if (maxExpectedWord>=maxExpectedFuriten)                    //~1307I~//~vaivR~
//        if (maxExpectedWord>=maxExpectedFuriten && maxExpectedFuriten>0)//~vaivR~
//            idxDiscard=idxMaxExpectedWord;                         //~1307I~//~vaivR~
//        else                                                       //~1307I~//~vaivR~
////      if (maxExpectedFuriten>=maxExpected)                        //~1307I~//~vaivR~
//        if (maxExpectedFuriten>=maxExpected && maxExpected>0)    //~vaivR~
//            idxDiscard=idxMaxExpectedFuriten;                      //~1307I~//~vaivR~
//        else                                                       //~1307I~//~vaivR~
//        if (!swSelected)                                         //~vaivR~
//            idxDiscard=idxMaxExpected;                             //~1307I~//~vaivR~
//        if (idxDiscard!=-1)                                        //~1307I~//~vaivR~
//        {                                                          //~1308I~//~vaivR~
//            if (Dump.Y) Dump.println("RADSEval.evaluateDiscard7Pair old itsHandValue["+idxDiscard+"]="+itsHandValue[idxDiscard]);//~1307I~//~1308I~//~vaajR~//~vaivR~
////          itsHandValue[idxDiscard]=DV_DISCARD;    //100,000      //~1307I~//~vaivR~
//            itsHandValue[idxDiscard]-=DV_DISCARD;   //100,000    //~vaivR~
//            if (Dump.Y) Dump.println("RADSEval.evaluateDiscard7Pair new itsHandValue["+idxDiscard+"]="+itsHandValue[idxDiscard]);//~1307I~//~1308I~//~vaajR~//~vaivR~
//        }                                                          //~1308I~//~vaivR~
//        if (Dump.Y) Dump.println("RADSEval.evaluateDiscard7Pair idxMaxExpected="+idxMaxExpected+",expected="+maxExpected);//~1307I~//~vaajR~//~vaivR~
//        if (Dump.Y) Dump.println("RADSEval.evaluateDiscard7Pair idxMaxExpectedWord="+idxMaxExpectedWord+",expected="+maxExpectedWord);//~1307I~//~vaajR~//~vaivR~
//        if (Dump.Y) Dump.println("RADSEval.evaluateDiscard7Pair idxMaxExpectedFuriten="+idxMaxExpectedFuriten+",expected="+maxExpectedFuriten);//~1307I~//~vaajR~//~vaivR~
        if (Dump.Y) Dump.println("RADSEval.evaluateDiscard7Pair exit itsHandValue="+Utils.toString(itsHandValue));//~vapgR~
    }                                                              //~1307I~
    //***********************************************************************//~1307I~
    //*rc:-1 furiten itself,1:link furiten                         //~1307I~
    //***********************************************************************//~1307I~
    private int chkFuriten(int Ppos)                               //~1307I~
    {                                                              //~1307I~
    	int rc=0;                                                  //~1307I~
    	if (RS.isFuritenSelf(eswnDiscard,Ppos))                    //~1307I~
        	rc=-1;                                                 //~1307I~
        else                                                       //~1307I~
        if (Ppos<OFFS_WORDTILE)                                    //~1307I~
        {                                                          //~1307I~
        	int num=Ppos%CTR_NUMBER_TILE;                          //~1307I~
            if (num>=TN1 && num<=TN3)                              //~1307I~
            {                                                      //~1307I~
		    	if (RS.isFuritenSelf(eswnDiscard,Ppos+3))          //~1307I~
                	rc=1;                                          //~1307I~
            }                                                      //~1307I~
            else                                                   //~1307I~
            if (num>=TN7 && num<=TN9)                              //~1307I~
            {                                                      //~1307I~
		    	if (RS.isFuritenSelf(eswnDiscard,Ppos-3))          //~1307I~
                	rc=1;                                          //~1307I~
            }                                                      //~1307I~
        }                                                          //~1307I~
        if (Dump.Y) Dump.println("RADSEval.chkFuriten rc="+rc+",pos="+Ppos+",eswn="+eswnDiscard);//~1307I~//~vaajR~
        return rc;
    }                                                              //~1307I~
    //***********************************************************************
    private void setValue13Orphan(boolean PswMe,int Pidx,int Ppos) //~1213R~
    {
        if (Dump.Y) Dump.println("RADSEval.setValue13Orphan wMe="+PswMe+",idx="+Pidx+",pos="+Ppos);      //~1131R~//~1213R~//~vaajR~
        setValueTanyao(true/*sw13Orphan*/,PswMe,Pidx,Ppos);        //~1213R~
    }
//    //***********************************************************************//~1213R~
//    private void setValueTanyao(boolean Psw13Orphan,boolean PswMe)//~1213R~
//    {                                                            //~1213R~
//        if (Dump.Y) Dump.println("RADSEval.setValueTanyao sw13Orphan="+Psw13Orphan);//~1131R~//~1213R~//~vaajR~
//        if (Dump.Y) Dump.println("RADSEval.setValueTanyao swMe="+PswMe+",itsHandValue="+ Arrays.toString(itsHandValue));//~1127I~//~1131R~//~1213R~//~vaajR~
//        for (int ii=0;ii<ctrHand;ii++)                           //~1213R~
//        {                                                        //~1213R~
//            int pos=itsHandPos[ii];                                //~1122I~//~1213R~
//            int type=pos/CTR_NUMBER_TILE; int num=pos%CTR_NUMBER_TILE;//~1122I~//~1213R~
//            if (type==TT_JI || num==0 || num==8)                   //~1122R~//~1213R~
//            {                                                    //~1213R~
//                if (Psw13Orphan)                                 //~1213R~
//                    itsHandValue[ii]+=PswMe ? DV_13ORPHAN : DV_13ORPHAN_OTHER;//~1122R~//~1213R~
//                else                                             //~1213R~
//                    itsHandValue[ii]+=DV_NOT_TANYAO;             //~1213R~
//            }                                                    //~1213R~
//        }                                                        //~1213R~
//        if (Dump.Y) Dump.println("RADSEval.setValueTanyao after itsHandValue="+Arrays.toString(itsHandValue));//~1127I~//~1131R~//~1213R~//~vaajR~
//    }                                                            //~1213R~
    //***********************************************************************//~1213I~
    private void setValueTanyao(boolean Psw13Orphan,boolean PswMe,int Pidx,int Ppos)//~1213I~
    {                                                              //~1213I~
        if (Dump.Y) Dump.println("RADSEval.setValueTanyao sw13Orphan="+Psw13Orphan+",swMe="+PswMe+",idx="+Pidx+",pos="+Ppos);//~1213I~//~vaajR~
        if (Dump.Y) Dump.println("RADSEval.setValueTanyao swMe="+PswMe+",old itsHandValue="+itsHandValue[Pidx]);//~1213I~//~1214R~//~vaajR~
        int type=Ppos/CTR_NUMBER_TILE; int num=Ppos%CTR_NUMBER_TILE;//~1213I~
        if (type==TT_JI || num==0 || num==8)                       //~1213I~
        {                                                          //~1213I~
            if (Psw13Orphan)                                       //~1213I~
                itsHandValue[Pidx]+=PswMe ? DV_13ORPHAN : DV_13ORPHAN_OTHER;//~1213I~
            else                                                   //~1213I~
            {                                                      //~1219I~
//          	if (RSP.swAllInHand)	                           //~1219I~//~vab9R~
                	itsHandValue[Pidx]+=DV_NOT_TANYAO;	//10000                 //~1213I~//~1219R~
//              else                                               //~1219I~//~vab9R~
//              	itsHandValue[Pidx]+=DV_NOT_TANYAO_AFTER_CALL;//alternatively by addByForceIntentStandard	//110000//~1219I~//~1220R~//~1221R~//~vab9R~
            }                                                      //~1219I~
        }                                                          //~1213I~
        else                                                       //~vabbI~
        	itsHandValue[Pidx]+=addByForceIntentStandard;	       //~vabbI~
        if (Dump.Y) Dump.println("RADSEval.setValueTanyao after itsHandValue="+itsHandValue[Pidx]+",addByForceIntentStandard="+addByForceIntentStandard);//~1213I~//~1214R~//~vaajR~//~vabbR~//~vafcR~
    }                                                              //~1213I~
    //***********************************************************************//~1217I~
    private void setValueChanta(boolean PswMe,int Pidx,int Ppos)   //~1217I~
    {                                                              //~1217I~
        if (Dump.Y) Dump.println("RADSEval.setValueChanta swMe="+PswMe+",idx="+Pidx+",pos="+Ppos);//~1217I~//~vaajR~
        if (Dump.Y) Dump.println("RADSEval.setValueChanta old itsHandValue="+itsHandValue[Pidx]);//~1217I~//~vaajR~
        int type=Ppos/CTR_NUMBER_TILE; int num=Ppos%CTR_NUMBER_TILE;//~1217I~
        if (type!=TT_JI && num>2 && num<6)      //456              //~1217I~
        {                                                          //~1217I~
//          if (RSP.swAllInHand)                                   //~1220I~//~vab9R~
        		itsHandValue[Pidx]+=DV_NOT_CHANTA;        //+10000             //~1217I~//~1220R~
//          else                                                   //~1220I~//~vab9R~
//              itsHandValue[Pidx]+=DV_NOT_CHANTA_AFTER_CALL;	//20000//~1220R~//~1221R~//~vab9R~
        }                                                          //~1217I~
        else  //123,789,ji                                         //~vabbI~
        	itsHandValue[Pidx]+=addByForceIntentStandard;          //~vabbI~
        if (Dump.Y) Dump.println("RADSEval.setValueChanta after itsHandValue="+itsHandValue[Pidx]+",addByForceIntentStabdard="+addByForceIntentStandard);//~1217I~//~vaajR~//~vabbR~
    }                                                              //~1217I~
    //***********************************************************************//~1218I~
    private void setValueAllSame(boolean PswMe,int Pidx,int Ppos)  //~1218I~
    {                                                              //~1218I~
        if (Dump.Y) Dump.println("RADSEval.setValueAllSame swMe="+PswMe+",idx="+Pidx+",pos="+Ppos);//~1218I~//~vaajR~
        if (Dump.Y) Dump.println("RADSEval.setValueAllSame old itsHandValue="+itsHandValue[Pidx]);//~1218I~//~vaajR~
        int type=Ppos/CTR_NUMBER_TILE; int num=Ppos%CTR_NUMBER_TILE;//~1218I~
        if (itsHand[Ppos]>=2)                                      //~1218I~
        {                                                          //~1218I~
          if (itsHand[Ppos]>=3)                                    //~vabbI~
	        itsHandValue[Pidx]+=DV_ALL_SAME3;	//-60000           //~vabbI~
          else                                                     //~vabbI~
        	itsHandValue[Pidx]+=DV_ALL_SAME;	//-20000                      //~1218I~//~1221R~
          itsHandValue[Pidx]+=addByForceIntentStandard;	//minus to ignore shanten Up/Down//~vabbI~
        }                                                          //~1218I~
        if (Dump.Y) Dump.println("RADSEval.setValueAllSame after itsHandValue="+itsHandValue[Pidx]+",addByForceIntentStandard="+addByForceIntentStandard);//~1218I~//~vaajR~//~vabbR~
    }                                                              //~1218I~
    //***********************************************************************//~1213I~
    private void setValueSameColor(boolean PswMe,int Pidx,int Ppos)//~1213I~
    {                                                              //~1213I~
        if (Dump.Y) Dump.println("RADSEval.setValueSameColor swMe="+PswMe+",idx="+Pidx+",pos="+Ppos);//~1213I~//~1302R~//~vaajR~
        if (Dump.Y) Dump.println("RADSEval.setValueSameColor eswnDiscard="+eswnDiscard+",intent=x"+Integer.toHexString(RADS.myIntent));//~1213I~//~vaajR~
        int color;                                                 //~1213I~
        if ((RADS.myIntent & INTENT_SAMECOLOR_MAN)!=0)             //~1213I~
            color=0;                                               //~1213I~
        else                                                       //~1213I~
        if ((RADS.myIntent & INTENT_SAMECOLOR_PIN)!=0)             //~1213I~
            color=1;                                               //~1213I~
        else                                                       //~1213I~
            color=2;                                               //~1213I~
        int tdsType=Ppos/CTR_NUMBER_TILE;                          //~1213I~
	    if (Dump.Y) Dump.println("RADSEval.setValueSameColor pos="+Ppos+",idx="+Pidx+",old="+itsHandValue[Pidx]);//~1214I~//~vaajR~
        if (tdsType!=TT_JI && tdsType!=color)                      //~1213I~
        {                                                          //~1213I~
            if (PswMe)     //my intent                                        //~1213I~//~1216R~
                itsHandValue[Pidx]+=DV_NOT_SAMECOLOR;   //100,000 more discardable   //~1213I~//~1216R~//~1303R~
        }                                                          //~1213I~
        else                                                       //~1213I~
        {                                                          //~1213I~
            if (!PswMe)   //other player causion                                         //~1213I~//~1216R~
                itsHandValue[Pidx]+=DV_SAMECOLOR_OTHER; //-10000 less discardable           //~1213I~//~1216R~
            else                                                   //~vabbI~
          		itsHandValue[Pidx]+=addByForceIntentStandard;	//minus to ignore shanten Up/Down//~vabbI~
        }                                                          //~1213I~
        if (Dump.Y) Dump.println("RADSEval.setValueSameColor pos="+Ppos+",itsHandValue["+Pidx+"]="+itsHandValue[Pidx]+",addbyForceIntentStandard="+addByForceIntentStandard+",DV_NOT_SAMECOLOR="+DV_NOT_SAMECOLOR);//~1213R~//~1214R~//~vaajR~//~vabbR~
    }                                                              //~1213I~
    //***********************************************************************//~vaf9I~
    private void evaluateIntentStraight(int[] PitsHandPos,int PctrHand,int[] PitsHand)//~vaf9R~
    {                                                              //~vaf9I~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraight itsHandPos="+Utils.toString(PitsHandPos,-1,PctrHand)+",itsHand="+Utils.toString(PitsHand,9));//~vaf9I~//~vafbR~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraight old itsHandValue="+Utils.toString(itsHandValue,-1,PctrHand));//~vaf9I~//~vafbR~
        int color,type;                                                 //~vaf9I~
        color=RS.getColorStraight(eswnDiscard);                    //~vaf9R~
        Point p=RS.getEarthColorStraight(eswnDiscard);             //~vafbI~
        int numTop=p==null ? -1 : p.y;                             //~vafbI~
        for (int ii=0;ii<PctrHand;ii++)                            //~vaf9I~
        {                                                          //~vaf9I~
        	int pos=PitsHandPos[ii];                               //~vaf9I~
            type=pos/CTR_NUMBER_TILE;                              //~vaf9I~
            int num=pos%CTR_NUMBER_TILE;                           //~vafbI~
            if (type==color)                                       //~vaf9I~
            {                                                      //~vaf9I~
            	if (numTop>=0 && num>=numTop && num<numTop+3)      //~vafbI~
                	continue;                                      //~vafbI~
	            int ctrTile=PitsHand[pos];                         //~vaf9I~
            	if (ctrTile==2)                                    //~vaf9R~
                {                                                  //~vaf9I~
			    	evaluateIntentStraightAdjustRed5(PitsHandPos,PctrHand,ii,pos);      //~vaf9I~
	        		continue;                                      //~vaf9I~
                }                                                  //~vaf9I~
        		if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraight more not discardable idx="+ii+",pos="+pos+",old itsHhandValue="+itsHandValue[ii]);//~vaf9I~//~vafbR~
	            itsHandValue[ii]+=DV_STRAIGHT;   //-10,000 not more discardable//~vaf9R~
        		if (!RSP.swAllInHand)                               //~vaf9I~//~vafbR~
		            itsHandValue[ii]+=DV_STRAIGHT_CALLED;   //-10,000 not more discardable//~vaf9I~
        		if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraight more not discardable swAllInHand="+RSP.swAllInHand+",idx="+ii+",pos="+pos+",new itsHhandValue="+itsHandValue[ii]);//~vaf9R~//~vafbR~
            }                                                      //~vaf9I~
        }                                                          //~vaf9I~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraight new itsHandValue="+Utils.toString(itsHandValue,-1,PctrHand));//~vaf9I~//~vafbR~
    }                                                              //~vaf9I~
    //***********************************************************************//~vafbI~
    private void evaluateIntent3SameSeq(int[] PitsHandPos,int PctrHand,int[] PitsHand)//~vafbI~
    {                                                              //~vafbI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeq itsHandPos="+Utils.toString(PitsHandPos,-1,PctrHand)+",itsHand="+Utils.toString(PitsHand,9));//~vafbI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeq old itsHandValue="+Utils.toString(itsHandValue,-1,PctrHand));//~vafbI~
        int numTop=RS.getNum3SameSeq(eswnDiscard);                 //~vafbI~
        int num,type;                                              //~vafbI~
        for (int ii=0;ii<PctrHand;ii++)                            //~vafbI~
        {                                                          //~vafbI~
        	int pos=PitsHandPos[ii];                               //~vafbI~
            if (pos>=OFFS_WORDTILE)                                //~vafbI~
            	continue;                                          //~vafbI~
            num=pos%CTR_NUMBER_TILE;                               //~vafbI~
            if (num>=numTop && num<numTop+PAIRCTR)                 //~vafbI~
            {                                                      //~vafbI~
	            int ctrTile=PitsHand[pos];                         //~vafbI~
            	if (ctrTile==2)                                    //~vafbI~
                {                                                  //~vafbI~
			    	evaluateIntent3SameSeqAdjustRed5(PitsHandPos,PctrHand,ii,pos,numTop);//~vafbR~
	        		continue;                                      //~vafbI~
                }                                                  //~vafbI~
        		if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeq more not discardable idx="+ii+",pos="+pos+",old itsHhandValue="+itsHandValue[ii]);//~vafbI~
	            itsHandValue[ii]+=DV_3SAMESEQ;   //-10,000 not more discardable//~vafbI~
        		if (!RSP.swAllInHand)                              //~vafbI~
		            itsHandValue[ii]+=DV_3SAMESEQ_CALLED;   //-10,000 not more discardable//~vafbI~
        		if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeq more not discardable swAllInHand="+RSP.swAllInHand+",idx="+ii+",pos="+pos+",new itsHhandValue="+itsHandValue[ii]);//~vafbI~
            }                                                      //~vafbI~
        }                                                          //~vafbI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeq new itsHandValue="+Utils.toString(itsHandValue,-1,PctrHand));//~vafbI~
    }                                                              //~vafbI~
    //***********************************************************************//~vafcI~
    private void evaluateIntentTanyaoCalled(int[] PitsHandPos,int PctrHand,int[] PitsHand)//~vafcI~
    {                                                              //~vafcI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentTanyaoCalled itsHandPos="+Utils.toString(PitsHandPos,-1,PctrHand)+",itsHand="+Utils.toString(PitsHand,9));//~vafcR~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentTanyaoCalled old itsHandValue="+Utils.toString(itsHandValue,-1,PctrHand));//~vafcR~
        int num,type;                                              //~vafcI~
        for (int ii=0;ii<PctrHand;ii++)                            //~vafcI~
        {                                                          //~vafcI~
        	int pos=PitsHandPos[ii];                               //~vafcI~
            if (RAUtils.isTanyaoTile(pos))                         //~vafcI~
            {                                                      //~vafcI~
		        if (Dump.Y) Dump.println("RADSEval.evaluateIntentTanyaoCalled old itsHandValue["+ii+"]="+itsHandValue[ii]);//~vafcR~
		    	itsHandValue[ii]+=DV_TANYAO_CALLED;   //-20,000 not more discardable//~vafcI~
		        if (Dump.Y) Dump.println("RADSEval.evaluateIntentTanyaoCalled not more discardable pos="+pos+",new itsHandValue["+ii+"]="+itsHandValue[ii]);//~vafcR~
            }                                                      //~vafcI~
        }                                                          //~vafcI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentTantaoCalled new itsHandValue="+Utils.toString(itsHandValue,-1,PctrHand));//~vafcI~//~vaffR~
    }                                                              //~vafcI~
    //***********************************************************************//~vaffI~
    private void evaluateIntentChantaCalled(int[] PitsHandPos,int PctrHand,int[] PitsHand)//~vaffI~
    {                                                              //~vaffI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentChantaCalled itsHandPos="+Utils.toString(PitsHandPos,-1,PctrHand)+",itsHand="+Utils.toString(PitsHand,9));//~vaffI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentChantaCalled old itsHandValue="+Utils.toString(itsHandValue,-1,PctrHand));//~vaffI~
        int num,type;                                              //~vaffI~
        for (int ii=0;ii<PctrHand;ii++)                            //~vaffI~
        {                                                          //~vaffI~
        	int pos=PitsHandPos[ii];                               //~vaffI~
            if (RAUtils.isChantaMeldTile(pos,0/*Pon and Chii*/))   //~vaffR~
            {                                                      //~vaffI~
		        if (Dump.Y) Dump.println("RADSEval.evaluateIntentChantaCalled old itsHandValue["+ii+"]="+itsHandValue[ii]);//~vaffI~
		    	itsHandValue[ii]+=DV_TANYAO_CALLED;   //-20,000 not more discardable//~vaffI~
		        if (Dump.Y) Dump.println("RADSEval.evaluateIntentChataCalled not more discardable pos="+pos+",new itsHandValue["+ii+"]="+itsHandValue[ii]);//~vaffI~
            }                                                      //~vaffI~
        }                                                          //~vaffI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentChantaCalled new itsHandValue="+Utils.toString(itsHandValue,-1,PctrHand));//~vaffI~
    }                                                              //~vaffI~
    //***********************************************************************//~vaf9I~
    //*select Red5 for sameTileCtr==2                              //~vaf9I~
    //***********************************************************************//~vaf9I~
    private void evaluateIntentStraightAdjustRed5(int[] PitsHandPos,int PctrHand,int Pidx,int Ppos)//~vaf9I~
    {                                                              //~vaf9I~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraightAdjustRed5 Pidx="+Pidx+",Ppos="+Ppos);//~vaf9I~//~vafbR~
		TileData td1,td2=null;                                     //~vaf9I~
        boolean red51,red52=false;
        int idxAdjust=-1,idx2=-1,idxAnother=-1;            //~vaf9I~//~vafbR~
        int numTop=((Ppos%CTR_NUMBER_TILE)/PAIRCTR)*PAIRCTR;        //~vafbI~
		td1=RADS.tdsHand[Pidx];   //evaluateHand is not called     //~vaf9I~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraightAdjustRed5 numTop="+numTop+",td1="+td1.toString());//~vaf9I~//~vafbR~
		red51=td1.isRed5();                                        //~vaf9I~
        int num=td1.number;                                        //~vafbI~
        for (int ii=Pidx+1;ii<PctrHand;ii++)                       //~vaf9I~
        {                                                          //~vaf9I~
        	if (PitsHandPos[ii]==Ppos)	//2nd of dup tile          //~vaf9I~
            {                                                      //~vaf9I~
				td2=RADS.tdsHand[ii];   //evaluateHand is not called//~vaf9I~
				red52=td2.isRed5();                                //~vaf9I~
                idx2=ii;                                           //~vaf9I~
		        if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraightAdjustRed5 idx2="+idx2+",td2="+td2.toString());//~vaf9R~//~vafbR~
            }                                                      //~vaf9I~
        }                                                          //~vaf9I~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraightAdjustRed5 idx2="+idx2+",red51="+red51+",red52="+red52);//~vaf9I~//~vafbR~
        if (td2!=null)                                             //~vaf9I~
        {                                                          //~vaf9I~
            idxAnother=idx2;                                       //~vafbI~
			if (red51 && !red52)                                   //~vaf9I~
            	idxAdjust=Pidx;                                    //~vaf9I~
            else                                                   //~vaf9I~
			if (!red51 && red52)                                   //~vaf9I~
            {                                                      //~vafbI~
                idxAnother=Pidx;                                   //~vafbI~
            	idxAdjust=idx2;                                    //~vaf9I~
            }                                                      //~vafbI~
            else                                                   //~vaf9I~
            	idxAdjust=Pidx;                                    //~vaf9I~
        }                                                          //~vaf9I~
        if (idxAdjust!=-1)                                         //~vaf9I~
        {                                                          //~vaf9I~
	    	itsHandValue[idxAdjust]+=DV_STRAIGHT;   //-10,000 not more discardable//~vaf9I~
        	if (!RSP.swAllInHand)                                   //~vaf9I~//~vafbR~
		    	itsHandValue[idxAdjust]+=DV_STRAIGHT_CALLED;   //-200,000 not more discardable//~vaf9I~//~vafbR~
        	if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraightAdjustRed5 more not discardable pos="+Ppos+",idxAdjust="+idxAdjust+",new itsHandValue="+itsHandValue[idxAdjust]);//~vaf9R~//~vafbR~
        }                                                          //~vaf9I~
        if (idxAnother!=-1 && (num==numTop || num==numTop+2))      //~vafbI~
        {                                                          //~vafbI~
        	if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraightAdjustRed5 more discardable Another pos="+Ppos+",idxAnother="+idxAnother+",old itsHandValue="+itsHandValue[idxAnother]);//~vafbI~
        	if (!RSP.swAllInHand)                                  //~vafbI~
		    	itsHandValue[idxAnother]+=-DV_STRAIGHT_CALLED;   //+200,000 more discardable//~vafbI~
        	if (Dump.Y) Dump.println("RADSEval.evaluateIntentStraightAdjustRed5 more discardable swAllInHand="+RSP.swAllInHand+",pos="+Ppos+",idxAnother="+idxAnother+",new itsHandValue="+itsHandValue[idxAnother]);//~vafbI~
        }                                                          //~vafbI~
    }                                                              //~vaf9I~
    //***********************************************************************//~vafbI~
    //*select Red5 for sameTileCtr==2                              //~vafbI~
    //***********************************************************************//~vafbI~
    private void evaluateIntent3SameSeqAdjustRed5(int[] PitsHandPos,int PctrHand,int Pidx,int Ppos,int PnumTop)//~vafbR~
    {                                                              //~vafbI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeqAdjustRed5 Pidx="+Pidx+",Ppos="+Ppos+",numTop="+PnumTop);//~vafbR~
		TileData td1,td2=null;                                     //~vafbI~
        boolean red51,red52=false;                                 //~vafbI~
        int idxAdjust=-1,idx2=-1,idxAnother=-1;                    //~vafbR~
		td1=RADS.tdsHand[Pidx];   //evaluateHand is not called     //~vafbI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeqAdjustRed5 td1="+td1.toString());//~vafbI~
		red51=td1.isRed5();                                        //~vafbI~
        int num=td1.number;                                        //~vafbI~
        for (int ii=Pidx+1;ii<PctrHand;ii++)                       //~vafbI~
        {                                                          //~vafbI~
        	if (PitsHandPos[ii]==Ppos)	//2nd of dup tile          //~vafbI~
            {                                                      //~vafbI~
				td2=RADS.tdsHand[ii];   //evaluateHand is not called//~vafbI~
				red52=td2.isRed5();                                //~vafbI~
                idx2=ii;                                           //~vafbI~
		        if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeqAdjustRed5 idx2="+idx2+",td2="+td2.toString());//~vafbI~
            }                                                      //~vafbI~
        }                                                          //~vafbI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeqAdjustRed5 idx2="+idx2+",red51="+red51+",red52="+red52);//~vafbI~
        if (td2!=null)                                             //~vafbI~
        {                                                          //~vafbI~
            idxAnother=idx2;                                        //~vafbI~
			if (red51 && !red52)                                   //~vafbI~
            	idxAdjust=Pidx;                                    //~vafbI~
            else                                                   //~vafbI~
			if (!red51 && red52)                                   //~vafbI~
            {                                                      //~vafbI~
            	idxAdjust=idx2;                                    //~vafbI~
                idxAnother=Pidx;                                   //~vafbI~
            }                                                      //~vafbI~
            else                                                   //~vafbI~
            	idxAdjust=Pidx;                                    //~vafbI~
        }                                                          //~vafbI~
        if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeqAdjustRed5 idxAdjust="+idxAdjust+",idxAnother="+idxAnother);//~vafbI~
        if (idxAdjust!=-1)                                         //~vafbI~
        {                                                          //~vafbI~
        	if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeqAdjustRed5 more not discardable pos="+Ppos+",idxAdjust="+idxAdjust+",old itsHandValue="+itsHandValue[idxAdjust]);//~vafbI~
	    	itsHandValue[idxAdjust]+=DV_3SAMESEQ;   //-10,000 not more discardable//~vafbI~
        	if (!RSP.swAllInHand)                                  //~vafbR~
		    	itsHandValue[idxAdjust]+=DV_3SAMESEQ_CALLED;   //-200,000 not more discardable//~vafbR~
        	if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeqAdjustRed5 more not discardable swAllInHand="+RSP.swAllInHand+",pos="+Ppos+",idxAdjust="+idxAdjust+",new itsHandValue="+itsHandValue[idxAdjust]);//~vafbR~
        }                                                          //~vafbI~
        if (idxAnother!=-1 && (num==PnumTop || num==PnumTop+2))    //~vafbI~
        {                                                          //~vafbI~
        	if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeqAdjustRed5 more discardable Another pos="+Ppos+",idxAnother="+idxAnother+",old itsHandValue="+itsHandValue[idxAnother]);//~vafbI~
        	if (!RSP.swAllInHand)                                  //~vafbI~
		    	itsHandValue[idxAnother]+=-DV_3SAMESEQ_CALLED;   //+200,000 more discardable//~vafbR~
        	if (Dump.Y) Dump.println("RADSEval.evaluateIntent3SameSeqAdjustRed5 more discardable swAllInHand="+RSP.swAllInHand+",pos="+Ppos+",idxAnother="+idxAnother+",new itsHandValue="+itsHandValue[idxAnother]);//~vafbI~
        }                                                          //~vafbI~
    }                                                              //~vafbI~
    //***********************************************************************//~vaf6I~
    private void setValue3Dragon(boolean PswMe,int Pidx,int Ppos)  //~vaf6I~
    {                                                              //~vaf6I~
	    if (Dump.Y) Dump.println("RADSEval.evaluateIntent 3dragon idx="+Pidx+",old handVal="+itsHandValue[Pidx]);//~vaf6I~
        if (Ppos>=OFFS_WORDTILE_DRAGON)                            //~vaf6I~
        	itsHandValue[Pidx]+=addByForceIntentStandard*2;	//minus to ignore shanten Up/Down//~vaf6R~
        if (Dump.Y) Dump.println("RADSEval.setValue3Dragon pos="+Ppos+",itsHandValue["+Pidx+"]="+itsHandValue[Pidx]+",addbyForceIntentStandard="+addByForceIntentStandard);//~vaf6I~
    }                                                              //~vaf6I~
    //***********************************************************************
    //*chk shanten up by next take
    //*if myShanten>=1
    //***********************************************************************
    private int evaluateNextTake(int Pidx,int Ppos,int Pshanten,boolean PswSameAsPrev,int Pintent)//~1220R~//~1309R~
    {
        if (Dump.Y) Dump.println("RADSEval.evaluateNextTake idx="+Pidx+",pos="+Ppos+",shanten="+Pshanten+",swSameAsPrev="+PswSameAsPrev);//~1131R~//~1212R~//~1220R~//~vaajR~
        if (Dump.Y) Dump.println("RADSEval.evaluateNextTake old itsHandValue["+Pidx+"]="+itsHandValue[Pidx]);//~1216I~//~vaajR~
        if (Pshanten>HV_SHANTEN_TRYNEXT) //from shanten>2          //~1302R~
        {                                                          //~1309I~
    		chkShantenAtDiscard(Pshanten,Pidx,Ppos,Pintent);   //~1309R~
        	return 0;
        }                                                          //~1309I~
        int han=0;    	                                           //~1220I~
        int amt=0;                                                 //~vaajI~
        if (!PswSameAsPrev)                                        //~1220I~
        {                                                          //~1220I~
            itsHand[Ppos]--;                                       //~1220R~
            TileData tdRed5=setDropRed5(playerDiscard,Ppos);       //~vaaKR~
            int old=itsHandValue[Pidx];                            //~1303I~
            han=tryNextTake(Pidx,Ppos,Pshanten,ctrHand-1,Pintent);         //~1220R~//~1309R~
            amt=amtMax;                                            //~vaajI~
            diffTryNext=itsHandValue[Pidx]-old;                    //~1303I~
            itsHand[Ppos]++;                                       //~1220R~
			resetDropRed5(tdRed5);                                 //~vaaKR~
        }                                                          //~1220I~
        else                                                       //~1303I~
        {                                                          //~1303I~
	        if (Dump.Y) Dump.println("RADSEval.evaluateNextTake sameasPrev old pos="+Ppos+",diffTryNext="+diffTryNext+",itsHandValue["+Pidx+"]="+itsHandValue[Pidx]);//~1303I~//~vaajR~
			itsHandValue[Pidx]+=diffTryNext;                       //~1303I~
	        if (Dump.Y) Dump.println("RADSEval.evaluateNextTake sameasPrev eswn="+eswnDiscard+",ctrWinningTileTryNext="+ctrWinningTileTryNext+",itsHandValue["+Pidx+"]="+itsHandValue[Pidx]);//~1303I~//~vaajR~
        }                                                          //~1303I~
        amtHanMax=amt;                                             //~vaajI~
//      if (Dump.Y) Dump.println("RADSEval.evaluateNextTake exit old pos="+Ppos+",itsHandValue["+Pidx+"]="+itsHandValue[Pidx]);//~1216I~//~1220R~//~1303R~//~vaajR~
//      itsHandValue[Pidx]+=ctrWinningTileTryNext*DV_BY_CTR_WINTILE;//100,000//~1216R~//~1220R~//~1303R~
        if (Dump.Y) Dump.println("RADSEval.evaluateNextTake exit eswn="+eswnDiscard+",ctrWinningTileTryNext="+ctrWinningTileTryNext+",itsHandValue["+Pidx+"]="+itsHandValue[Pidx]);//~vaffR~
        if (Dump.Y) Dump.println("RADSEval.evaluateNextTake exit pos="+Ppos+",han="+han+",amtHanMax="+Integer.toHexString(amtHanMax));//~vaajR~//~vaanR~//~vaaKR~
        return han;
    }
    //***********************************************************************//~vaaKI~
    //*for count red5 at UARonDataTree;if red5 at pos, set flag not to count as red5//~vaaKI~
    //***********************************************************************//~vaaKI~
    public  TileData setDropRed5(int Pplayer,int Ppos)             //~vaaKR~
    {                                                              //~vaaKI~
        if (Dump.Y) Dump.println("RADSEval.setDropRed5 player="+Pplayer+",pos="+Ppos+",swUseRed5="+swUseRed5);//~vaaKR~
//        if (!swUseRed5)                                          //~vaaKR~
//            return null;                                         //~vaaKR~
//        int type=Ppos/CTR_NUMBER_TILE;                           //~vaaKR~
//        int num=Ppos%CTR_NUMBER_TILE;                            //~vaaKR~
//        if (num!=TN5)                                            //~vaaKR~
//            return null;                                         //~vaaKR~
//        int ctrRed5=0;                                           //~vaaKR~
//        int ctrNonRed5=0;                                        //~vaaKR~
//        TileData tdRed5=null;                                    //~vaaKR~
//        TileData[] tdsH=AG.aPlayers.getHands(Pplayer);           //~vaaKR~
//        for (TileData td:tdsH)                                  //~1119I~//~vaaKR~
//        {                                                        //~vaaKR~
//            if (type!=td.type || num!=td.number)                 //~vaaKR~
//                continue;                                        //~vaaKR~
//            if (td.isRed5())                                     //~vaaKR~
//            {                                                    //~vaaKR~
//                ctrRed5++;                                       //~vaaKR~
//                tdRed5=td;                                       //~vaaKR~
//            }                                                    //~vaaKR~
//            else                                                 //~vaaKR~
//                ctrNonRed5++;                                    //~vaaKR~
//        }                                                        //~vaaKR~
//        if (ctrNonRed5==0 && ctrRed5!=0)        //could not select non red5 to discard//~vaaKI~
//            tdRed5.setDiscardedRed5(true);                       //~vaaKI~
//        else                                                     //~vaaKI~
//            tdRed5=null;                                         //~vaaKI~
//  	TileData tdRed5=isHavingRed5(Pplayer,Ppos);                //~vaaKR~//~vab3R~
    	TileData tdRed5=isHavingRed5(Pplayer,Ppos,true/*PswSelectNonRed5*/);//~vab3I~
        if (tdRed5!=null)		//could not select non red5 to discard//~vaaKR~
        	tdRed5.setDiscardedRed5(true);                             //~vaaKI~
        if (Dump.Y) Dump.println("RADSEval.setDropRed5 pos="+Ppos+",tdRed5="+Utils.toString(tdRed5));//~vaaKR~
        return tdRed5;                                             //~vaaKR~
    }                                                              //~vaaKI~
    //***********************************************************************//~vaaKI~
    //*chk red5 exist at pos                                       //~vaaKI~
    //***********************************************************************//~vaaKI~
//  public  TileData isHavingRed5(int Pplayer,int Ppos)            //~vaaKR~//~vab3R~
    public  TileData isHavingRed5(int Pplayer,int Ppos,boolean PswSelectNonRed5)//~vab3I~
    {                                                              //~vaaKI~
        if (Dump.Y) Dump.println("RADSEval.setDropRed5 player="+Pplayer+",pos="+Ppos+",swUseRed5="+swUseRed5+",PswSelectNonRed5="+PswSelectNonRed5);//~vaaKI~//~vab3R~
        if (!swUseRed5)                                            //~vaaKI~
        {                                                          //~vaaKI~
	        if (Dump.Y) Dump.println("RADSEval.setDropRed5 return false by swUseRed5");//~vaaKI~
        	return null;                                          //~vaaKI~
        }                                                          //~vaaKI~
        int type=Ppos/CTR_NUMBER_TILE;                             //~vaaKI~
        int num=Ppos%CTR_NUMBER_TILE;                              //~vaaKI~
        if (num!=TN5)                                              //~vaaKI~
        {                                                          //~vaaKI~
	        if (Dump.Y) Dump.println("RADSEval.setDropRed5 return false by num!=5");//~vaaKI~
        	return null;                                          //~vaaKI~
        }                                                          //~vaaKI~
        int ctrRed5=0;                                             //~vaaKI~
        int ctrNonRed5=0;                                          //~vaaKI~
        TileData tdRed5=null;                                      //~vaaKI~
        TileData[] tdsH=AG.aPlayers.getHands(Pplayer);             //~vaaKI~
		for (TileData td:tdsH)                                     //~vaaKI~
		{                                                          //~vaaKI~
        	if (type!=td.type || num!=td.number)                   //~vaaKI~
            	continue;                                          //~vaaKI~
			if (td.isRed5())                                       //~vaaKI~
            {                                                      //~vaaKI~
            	ctrRed5++;                                         //~vaaKI~
                tdRed5=td;                                         //~vaaKI~
            }                                                      //~vaaKI~
            else                                                   //~vaaKI~
            	ctrNonRed5++;                                      //~vaaKI~
        }                                                          //~vaaKI~
      if (PswSelectNonRed5)	//select non red5 if exist             //~vab3I~
        if (!(ctrNonRed5==0 && ctrRed5!=0))	//could not select non red5 to discard//~vaaKR~
        	tdRed5=null;                                           //~vaaKI~
        if (Dump.Y) Dump.println("RADSEval.setDropRed5 pos="+Ppos+",tdRed5="+Utils.toString(tdRed5)+",tdsHand="+TileData.toString(tdsH));//~vaaKI~
        return tdRed5;                                             //~vaaKR~
    }                                                              //~vaaKI~
    //***********************************************************************//~vaaKI~
    //*reset discardedRed5                                         //~vaaKI~
    //***********************************************************************//~vaaKI~
    public  void resetDropRed5(TileData PtdRed5)                   //~vaaKR~
    {                                                              //~vaaKI~
        if (Dump.Y) Dump.println("RADSEval.resetDropRed5 tdRed5="+Utils.toString(PtdRed5));//~vaaKR~
        if (PtdRed5!=null)                                         //~vaaKR~
            PtdRed5.setDiscardedRed5(false);                          //~vaaKR~
    }                                                              //~vaaKI~
    //***********************************************************************
    //*Ppos:dropped pos to calc Pshanten
    //***********************************************************************
    private int tryNextTake(int Pidx/*idx of tdsHand try to discard*/,int Ppos/*of tdsHand[Pidx]*/,int Pshanten,int PctrHand,int Pintent)//~1126R~//~1309R~
    {
        if (Dump.Y) Dump.println("RADSEval.tryNextTake entry eswn="+eswnDiscard+",Pidx="+Pidx+",pos="+Ppos+",shanten="+Pshanten+",ctrHand="+PctrHand+",itsHand="+Utils.toString(itsHand,9));//~vaffR~
        if (Dump.Y) Dump.println("RADSEval.tryNextTake before itsExposed="+Utils.toString(RS.itsExposed,9));//~1218R~//~vaajR~
        if (Dump.Y) Dump.println("RADSEval.tryNextTake old    itsHandValue="+itsHandValue[Pidx]);//~1127R~//~1131R~//~1220I~//~vaajR~
        int hanMax=0;
        int amtMx=0;                                               //~vaajI~
    	int shanten;                                               //~1127I~
//      int shantenUpMax=0;                                        //~1127I~//~1218R~
        int ctrWinningTile=0;
        int ctrShantenDownCase=0;//~1216I~
        boolean swFuriten=false,swNotFuriten=false;                //~vaimI~
        //*******************                                      //~1127I~
        if (Dump.Y) Dump.println("RADSEval.tryNextTake chk shantenDown pos="+Ppos+",idx="+Pidx+",Pshanten="+Pshanten);//~1220I~//~vaajR~
//        shanten=AG.aShanten.getShantenMin(itsHand,PctrHand); //chk drop collapse meld//~1218I~//~1220R~
//        if (Dump.Y) Dump.println("RADSEval.tryNextTake chk shantenDown pos="+Ppos+",idx="+Pidx+",shanten="+shanten+",Pshanten="+Pshanten);//~1127I~//~1131R~//~1220R~//~vaajR~
//        if (shanten>Pshanten)   //shanten down                     //~1218I~//~1220R~
//        {                                                          //~1127I~//~1220R~
//            if (Dump.Y) Dump.println("RADSEval.tryNextTake shanten Down pos="+Ppos+",idx="+Pidx+",shanten="+shanten+",Pshanten="+Pshanten+",old="+itsHandValue[Pidx]);//~1127I~//~1131R~//~1214R~//~1220R~//~vaajR~
//            itsHandValue[Pidx]+=DV_SHANTEN_DOWN;       //-100000  //do not discard//~1218R~//~1220R~
//            if (Dump.Y) Dump.println("RADSEval.tryNextTake idx="+Pidx+",itsHandValue="+itsHandValue[Pidx]);//~1213I~//~1220R~//~vaajR~
//            ctrWinningTileTryNext=0;                             //~1220R~
//            return 0; //han                                        //~1127I~//~1220R~
//        }                                                          //~1127I~//~1220R~
//        boolean swShantenDownfalse;                                //~1127I~//~vaaCR~
        int ctrTileShantenUp=0;                                    //~1218I~
	    ctrWinListTryNextTake=0;                                   //~vaaEI~
	    ctrWinListTryNextTakeTanki=0;                               //~vaaEI~
        for (int ii=0;ii<CTR_TILETYPE;ii++)
        {
        	if (ii==Ppos)            //same as discard
            	continue;                                          //~1127M~
        	int ctrRemain=PIECE_DUPCTR-(itsHand[ii]+RS.itsExposed[ii]);//~1218I~
		    if (Dump.Y) Dump.println("RADSEval.tryNextTake ctrRemain="+ctrRemain+",pos="+ii);//~1218I~//~vaajR~
//      	if (ctrRemain<=0)                                      //~1218I~//~vaimR~
        	if (PIECE_DUPCTR==itsHand[ii])                         //~vaimI~
            	continue;                                          //~1206I~//~1218M~
//  		if (!isCanMakeSet(ii,itsHand))                         //~1218R~
//              continue;                                          //~1218R~
        	itsHand[ii]++;   //try taken
//  		shanten=RADS.getShanten(false/*PswIntent*/,PctrHand+1);//~1127R~//~1218R~
	        shanten=AG.aShanten.getShantenMin(itsHand,PctrHand); //chk drop collapse meld//~1218I~
            if (shanten==-1) //ron by next take                    //~vaimI~
            {                                                      //~vaimI~
    			if (RSP.isFuritenSelf(ii))	//ron next take but ron tile is furiteniscard//~vaimI~
                	swFuriten=true;                                //~vaimI~
                else                                               //~vaimI~
                	swNotFuriten=true;                             //~vaimI~
            }                                                      //~vaimI~
		    if (Dump.Y) Dump.println("RADSEval.tryNextTake ctrRemain="+ctrRemain+",pos="+ii+",swFuriten="+swFuriten+",swFuritenNo="+swNotFuriten);//~vaimI~
        	if (ctrRemain<=0) //need furiten chked for also ctrRemain=0//~vaimI~
            {                                                      //~vaimI~
			    if (Dump.Y) Dump.println("RADSEval.tryNextTake continue by ctrRemain="+ctrRemain);//~vaimI~
        		itsHand[ii]--;                                     //~vaimI~
            	continue;                                          //~vaimI~
            }                                                      //~vaimI~
            int shantenUp=Pshanten-shanten;                        //~1127I~
//          if (shantenUp>0)                                       //~1127R~//~1220R~
            if (shantenUp>=0)     //evaluate even if shanten same  //~1220I~
            {
		        if (Dump.Y) Dump.println("RADSEval.tryNextTake eswn="+eswnDiscard+",old ctrTileShantenUP="+ctrTileShantenUp+",pos="+ii);//~1218I~//~1220R~//~vaajR~
                if (shantenUp>0)                                   //~1220I~
	                ctrTileShantenUp+=ctrRemain;                       //~1218R~//~1220R~
		        if (Dump.Y) Dump.println("RADSEval.tryNextTake eswn="+eswnDiscard+",Pidx="+Pidx+",Ppos="+Ppos+",new ctrTileShantenUP="+ctrTileShantenUp+",pos="+ii);//~vaffR~
              for(;;)                                              //~1130I~
              {                                                    //~1130I~
//          	if (shantenUp>shantenUpMax)                        //~1127I~//~1218R~
//              	shantenUpMax=shantenUp;                          //~1127I~//~1218R~
		        if (Dump.Y) Dump.println("RADSEval.tryNextTake shantenUp idx="+Pidx+",pos="+ii);//~1127R~//~1131R~//~vaajR~
            	if (shanten==-1) //ron by next take
                {
//  				if (RAUtils.isFuriten(eswnDiscard,ii))	//ron next take but ron tile is furiteniscard//~1220R~
//  				if (RSP.isFuritenSelf(ii))	//ron next take but ron tile is furiteniscard//~1220I~//~vaimR~
//              		break;                                     //~1130I~//~vaimR~
                    ctrWinListTryNextTake++;                       //~vaaEI~
                    if (RAUtils.isSingle(itsHand,ii,-1/*ron by pillow constructed*/))//~vaaER~
                    {	                                           //~vaaEI~
	                    ctrWinListTryNextTakeTanki++;              //~vaaEI~
                    	posWait1TryNextTake=ii;                    //~vaaER~
                    	ctrWait1TryNextTake=ctrRemain;             //~vaaEI~
		   				if (Dump.Y) Dump.println("RADSEval.tryNextTake single WinTile pos="+posWait1TryNextTake+",ctrWinListTryNextTake="+ctrWinListTryNextTake+",ctrWinlistTryNextTakeTanki="+ctrWinListTryNextTakeTanki);//~vaaEI~
                    }                                              //~vaaEI~
                    int han=getRonValue(itsHand,ii/*emulated takeOne*/);
                    if (han>hanMax)
                    {
                    	hanMax=han;
                        amtMx=amtRonValue;                         //~vaajI~
                    }
                    else                                           //~vaajI~
                    if (han==hanMax)                               //~vaajI~
                    {                                              //~vaajI~
                    	if (amtRonValue>amtMx)                     //~vaajI~
                        	amtMx=amtRonValue;                     //~vaajI~
                    }                                              //~vaajI~
                    ctrWinningTile+=PIECE_DUPCTR-(itsHand[ii]-1)-RS.itsExposed[ii];//~1216I~
			        if (Dump.Y) Dump.println("RADSEval.tryNextTake eswn="+eswnDiscard+",shanten=-1 pos="+ii+",hanMax="+hanMax+",amtMx="+amtMx+",ctrWinningTile="+ctrWinningTile);//~1216I~//~1220R~//~vaajR~
                }
//                else                                             //~vaimR~
//                if (shanten==0) //tenpai                         //~vaimR~
//                {                                                //~vaimR~
//*no effect,finally break                                         //~vaimI~
////                  if (RADS.chkFuriten())  //chk any winning tile is furiten//~1220R~//~vaimR~
//                    if (RADS.chkFuritenSelf())  //chk any winning tile is furiten//~1220I~//~vaimR~
//                        break;                                     //~1130I~//~vaimR~
//                }                                                //~vaimR~
//              itsHandValue[Pidx]+=DV_SHANTEN_UP;                 //~1126R~//~1127R~
		        if (Dump.Y) Dump.println("RADSEval.tryNextTake shantenUp shanten="+shanten+",Pshanten="+Pshanten);//~1127R~//~1131R~//~vaajR~
                break;                                             //~1130I~
              } // to restore itsHand ctr                          //~1130I~
            }
            else                                                   //~1220I~
            {                                                      //~1220I~
                ctrShantenDownCase++;                              //~1220I~
				if (Dump.Y) Dump.println("RADSEval.tryNextTake eswn="+eswnDiscard+",shantenDown pos="+Ppos+",idx="+Pidx+",shanten="+shanten+",Pshanten="+Pshanten);//~vaffR~
            }                                                      //~1220I~
        	itsHand[ii]--;
        }
        if (swFuriten)                                             //~vaimI~
        {                                                          //~vaimI~
        	swFuriten=!isEvaluateFuriten(swNotFuriten,Ppos);       //~vaimI~
        }                                                          //~vaimI~
      if (swFuriten)                                               //~vaimI~
      {                                                            //~vaimI~
	    	if (Dump.Y) Dump.println("RADSEval.tryNextTake Furiten idx="+Pidx+",old="+itsHandValue[Pidx]);//~vaimI~
    	    itsHandValue[Pidx]+=DV_AVOID_FURITEN;       //-100000  //do not discard//~vaimI~
	    	if (Dump.Y) Dump.println("RADSEval.tryNextTake Furiten idx="+Pidx+",new="+itsHandValue[Pidx]);//~vaimI~
            ctrWinningTileTryNext=0;                               //~vaimI~
        	amtMax=0;                                              //~vaimI~
        	hanMax=0;                                              //~vaimI~
      }                                                            //~vaimI~
      else                                                         //~vaimI~
      {                                                            //~vaimI~
//      if (shantenUpMax>0)                                        //~1127I~//~1218R~
        if (ctrTileShantenUp>0)                                    //~1218I~
        {                                                          //~1127I~
	    	if (Dump.Y) Dump.println("RADSEval.tryNextTake idx="+Pidx+",old="+itsHandValue[Pidx]);//~1214I~//~vaajR~
//  		itsHandValue[Pidx]+=DV_SHANTEN_UP*shantenUpMax;	//add plus more discardable//~1216R~//~1218R~
    		itsHandValue[Pidx]+=DV_SHANTEN_UP+DV_SHANTEN_UP_TILE*ctrTileShantenUp;	//add plus more discardable//~1218I~
//* 		                     100,000     +100               *ctrTileShantenUp;	//add plus more discardable//~1220R~
	        if (Dump.Y) Dump.println("RADSEval.tryNextTake UP after Pshanten="+Pshanten+",idx="+Pidx+",itsHandValue="+itsHandValue[Pidx]);//~1127I~//~1131R~//~1220R~//~vaajR~
        }                                                          //~1127I~
        ctrWinningTileTryNext=ctrWinningTile;                      //~1216I~
        if (ctrTileShantenUp==0 && ctrShantenDownCase>0)           //~1220I~
        {                                                          //~1220I~
            if (Dump.Y) Dump.println("RADSEval.tryNextTake shantenDown pos="+Ppos+",idx="+Pidx+",ctrTileShantenUp="+ctrTileShantenUp+",ctrShantenDowncase="+ctrShantenDownCase+",Pshanten="+Pshanten+",old="+itsHandValue[Pidx]);//~1220I~//~va70R~//~vaajR~
        	if ((Pintent & INTENT_SAMECOLOR_ANY)==0)               //~1309I~
    	        itsHandValue[Pidx]+=DV_SHANTEN_DOWN;       //-100000  //do not discard//~1220I~//~1309R~
            if (Dump.Y) Dump.println("RADSEval.tryNextTake shantenDown intent="+Integer.toHexString(RADS.myIntent)+",idx="+Pidx+",itsHandValue="+itsHandValue[Pidx]);//~1220I~//~1309R~//~vaajR~
            ctrWinningTileTryNext=0;                               //~1220I~
        }                                                          //~1220I~
        amtMax=amtMx;                                              //~vaajI~
      }//!swFuriten                                                //~vaimI~
        if (Dump.Y) Dump.println("RADSEval.tryNextTake exit eswnDiscard="+eswnDiscard+",Pidx="+Pidx+",Ppos="+Ppos+",hanMax="+hanMax+",amtMax="+amtMax+",ctrTileShanteUp="+ctrTileShantenUp+",ctrWinningTile="+ctrWinningTile);//~1127R~//~1131R~//~1213R~//~1216R~//~1218R~//~vaajR~//~vafbR~
        if (Dump.Y) Dump.println("RADSEval.tryNextTake exit ctrWinlistTryNexttake="+ctrWinListTryNextTake+",ctrShantenDownCase="+ctrShantenDownCase);//~vaffR~
        return hanMax;
    }
    //***********************************************************************//~vaimI~
    //*For furiten, evaluate if Ron by Draw is allowed             //~vaimI~
    //***********************************************************************//~vaimI~
    private boolean isEvaluateFuriten(boolean PswExistNotFuritenTile,int Ppos)//~vaimI~
    {                                                              //~vaimI~
    	boolean rc;                                                //~vaimI~
        if (PswExistNotFuritenTile) //win tile is mix of furiten and not furiten//~vaimI~
        	rc=false;	//always select discard avoiding furiten   //~vaimI~
        else      //all win tile is furiten                        //~vaimI~
        	rc=false;	//do not evaluate this discard of Ppos     //~vaimI~
        if (Dump.Y) Dump.println("RADSEval.isEvaluateFuriten rc="+rc+",pos="+Ppos+",swExistNotFuritenTile="+PswExistNotFuritenTile);//~vaimI~
        return rc;                                                 //~vaimI~
    }                                                              //~vaimI~
    //***********************************************************************//~vafcR~
    //*get max Amt for winlist                                     //~vafcR~
    //***********************************************************************//~vafcR~
    public Point evaluateWinListCall(int Peswn,int[] PitsHand,int PctrHand,int[] PitsWin)//~vafcR~
    {                                                              //~vafcR~
        if (Dump.Y) Dump.println("RADSEval.evaluateWinListCall entry eswn="+Peswn+",itsWin="+Utils.toString(PitsWin)+",ctrHand="+PctrHand+",PitsHand="+Utils.toString(PitsHand,9));//~vafcR~
        int posMaxAmt=-1;                                          //~vafcR~
        int amtMx=0;                                               //~vafcR~
        //*******************                                      //~vafcR~
        int player=RS.RSP[Peswn].player;                           //~vafcR~
        for (int ii=0;ii<PitsWin.length;ii++)                      //~vafcR~
        {                                                          //~vafcR~
        	int pos=PitsWin[ii];                                       //~vafcR~
	        if (Dump.Y) Dump.println("RADSEval.evaluateWinList posWin="+pos);//~vafcR~
	        Point hanAndAmt=AG.aRARon.getRonValueEvaluateCall(player,PitsHand,pos);//~vafcR~
        	int amt=hanAndAmt.y;	//output of RARon.getRonValue()//~vafcR~
            if (amt>amtMx)                                         //~vafcR~
            {                                                      //~vafcR~
            	amtMx=amt;                                         //~vafcR~
                posMaxAmt=pos;                                     //~vafcR~
            }                                                      //~vafcR~
        }                                                          //~vafcR~
        Point posAndAmt=new Point(posMaxAmt,amtMx);                //~vafcR~
        if (Dump.Y) Dump.println("RADSEval.evaluateWinListCall exit posAndAmt="+posAndAmt.toString());//~vafcR~
        return posAndAmt;                                          //~vafcR~
    }                                                              //~vafcR~
//    //***********************************************************************//~vafnI~//~vaivR~
//    //*get max Amt for winlist                                     //~vafnI~//~vaivR~
//    //***********************************************************************//~vafnI~//~vaivR~
//    //*NoUser                                                    //~vaivI~
//    public boolean evaluateWinListCallRonable(int Peswn,int[] PitsHand,int PctrHand,int Paction,int PposTop,int[] PitsWin)//~vafnR~//~vaivR~
//    {                                                              //~vafnI~//~vaivR~
//        if (Dump.Y) Dump.println("RADSEval.evaluateWinListCall entry eswn="+Peswn+",itsWin="+Utils.toString(PitsWin)+",ctrHand="+PctrHand+",PitsHand="+Utils.toString(PitsHand,9));//~vafnI~//~vaivR~
//        if (Dump.Y) Dump.println("RADSEval.evaluateWinListCall action="+Paction+",posTop="+PposTop);//~vafnI~//~vaivR~
//        boolean rc=true;                                           //~vafnI~//~vaivR~
//        int player=RS.RSP[Peswn].player;                           //~vafnI~//~vaivR~
//        for (int ii=0;ii<PitsWin.length;ii++)                      //~vafnI~//~vaivR~
//        {                                                          //~vafnI~//~vaivR~
//            int pos=PitsWin[ii];                                   //~vafnI~//~vaivR~
//            if (Dump.Y) Dump.println("RADSEval.evaluateWinListCallRonable posWin="+pos);//~vafnI~//~vaivR~
//            Point hanAndAmt=AG.aRARon.getRonValueEvaluateCall2nd(player,PitsHand,pos,Paction,PposTop);//~vafnR~//~vaivR~
//            int amt=hanAndAmt.y;    //output of RARon.getRonValue()//~vafnI~//~vaivR~
//            if (amt==0) //ewve on 2 hanconstraint                  //~vafnI~//~vaivR~
//            {                                                      //~vafnI~//~vaivR~
//                rc=false;                                          //~vafnI~//~vaivR~
//                break;                                             //~vafnI~//~vaivR~
//            }                                                      //~vafnI~//~vaivR~
//        }                                                          //~vafnI~//~vaivR~
//        if (Dump.Y) Dump.println("RADSEval.evaluateWinListCallRonable exit rc="+rc);//~vafnI~//~vaivR~
//        return rc;                                                 //~vafnI~//~vaivR~
//    }                                                              //~vafnI~//~vaivR~
    //***********************************************************************//~vafnI~
    //*get max Amt for winlist,return 0 if amt=0 pattern exist     //~vafnI~
    //***********************************************************************//~vafnI~
    public int evaluateFixedCallRonableWinList(int Peswn,int[] PitsHand,int PctrHand,int Paction,int PposTop,int[] PitsWin)//~vafnI~
    {                                                              //~vafnI~
        if (Dump.Y) Dump.println("RADSEval.evaluateFixedCallRonableWinList entry eswn="+Peswn+",itsWin="+Utils.toString(PitsWin)+",ctrHand="+PctrHand+",PitsHand="+Utils.toString(PitsHand,9));//~vafnI~
        if (Dump.Y) Dump.println("RADSEval.evaluateFixedCallRonableWinList action="+Paction+",posTop="+PposTop);//~vafnI~
        boolean rc=true;                                           //~vafnI~
        int player=RS.RSP[Peswn].player;                           //~vafnI~
        int amtMax=0;                                              //~vafnI~
        for (int ii=0;ii<PitsWin.length;ii++)                      //~vafnI~
        {                                                          //~vafnI~
        	int pos=PitsWin[ii];                                   //~vafnI~
	        if (Dump.Y) Dump.println("RADSEval.evaluateWinListCallRonable posWin="+pos);//~vafnI~
	        Point hanAndAmt=AG.aRARon.getRonValueEvaluateCall2nd(player,PitsHand,pos,Paction,PposTop);//~vafnI~
        	int amt=hanAndAmt.y;	//output of RARon.getRonValue()//~vafnI~
            if (amt==0)	//ewve on 2 hanconstraint                  //~vafnI~
            {                                                      //~vafnI~
            	amtMax=0;                                          //~vafnI~
            	break;                                             //~vafnI~
            }                                                      //~vafnI~
            amtMax=Math.max(amtMax,amt);                           //~vafnI~
        }                                                          //~vafnI~
        if (Dump.Y) Dump.println("RADSEval.evaluateFixedCallRonableWinList exit amtMax="+amtMax);//~vafnI~
        return amtMax;                                                 //~vafnI~
    }                                                              //~vafnI~
    //***********************************************************************
    private int getRonValue(int[] PitsHand,int Ppos)
    {
//  	int han=0;                                                 //~vaajR~
       int han=                                                     //~vaajI~
//      AG.aRARon.getRonValue(playerDiscard,PitsHand,Ppos);        //~vab5R~
        AG.aRARon.getRonValueEvaluate(playerDiscard,PitsHand,Ppos);//~vab5R~
        amtRonValue=AG.aRARon.amtRonValue;	//output of RARon.getRonValue()//~vaajI~
        if (Dump.Y) Dump.println("RADSEval.getRonValue han="+han+",pointRonValue="+amtRonValue);           //~1131R~//~vaajR~
        return han;
    }
    //***********************************************************************//~1227I~//~1302M~
    //*search pillow candidate                                     //~1227I~//~1302M~
    //***********************************************************************//~1227I~//~1302M~
    private int searchPillow4(int Pintent)                                   //~1227I~//~1302R~
    {                                                              //~1227I~//~1302M~
        int posPillow=-1,posPillow2=-1,posPillow0=-1,num;                        //~1227I~//~1301R~//~1302M~
        //*****************************                            //~1227I~//~1302M~
        if (Dump.Y) Dump.println("RADSEval.searchPillow4");        //~1227I~//~1302M~//~vaajR~//~vab9R~
        for (int ii=0;ii<CTR_TILETYPE;ii++)                        //~1227I~//~1302M~
        {                                                          //~1227I~//~1302M~
        	if (itsHand[ii]!=2)                                    //~1227I~//~1302M~
            	continue;                                          //~1227I~//~1302M~
            if (ii<OFFS_WORDTILE)                                  //~1227I~//~1302M~
            {                                                      //~1227I~//~1302M~
                num=ii%CTR_NUMBER_TILE;                            //~1227I~//~1302M~
                if (num==TN1)                                      //~1227R~//~1302M~
                {                                                  //~1227I~//~1302M~
	                if (itsHand[ii+1]==0)                          //~1227I~//~1302M~
                    {                                              //~1301I~//~1302M~
		                if (itsHand[ii+2]==0)                      //~1301I~//~1302M~
                        	posPillow0=ii;                          //~1301I~//~1302M~
                        else                                       //~1301I~//~1302M~
	                    	posPillow=ii;                              //~1227I~//~1301R~//~1302M~
                    }                                              //~1301I~//~1302M~
                    else                                           //~1227I~//~1302M~
	                if (itsHand[ii+1]==1)                          //~1227I~//~1302M~
	                    posPillow2=ii;                             //~1227I~//~1302M~
                }                                                  //~1227I~//~1302M~
                else                                               //~1227I~//~1302M~
                if (num==TN9)                                      //~1227I~//~1302M~
                {                                                  //~1227I~//~1302M~
	                if (itsHand[ii-1]==0)                          //~1227I~//~1302M~
                    {                                              //~1301I~//~1302M~
		                if (itsHand[ii-2]==0)                      //~1301I~//~1302M~
                        	posPillow0=ii;                          //~1301I~//~1302M~
                        else                                       //~1301I~//~1302M~
		                    posPillow=ii;                              //~1227I~//~1301R~//~1302M~
                    }                                              //~1301I~//~1302M~
                    else                                           //~1227I~//~1302M~
	                if (itsHand[ii-1]==1)                          //~1227I~//~1302M~
	                    posPillow2=ii;                             //~1227I~//~1302M~
                }                                                  //~1227I~//~1302M~
                else                                               //~1227I~//~1302M~
                if (itsHand[ii-1]==0 && itsHand[ii+1]==0)          //~1227R~//~1302M~
                {                                                  //~1227I~//~1302M~
                	if (itsHand[ii-1]==0 && itsHand[ii+1]==0)      //~1301I~//~1302M~
                    {                                              //~1301I~//~1302M~
                		if (ii==TN3 && itsHand[ii-2]==0            //~1301I~//~1302M~
                		||  ii==TN7 && itsHand[ii+2]==0            //~1301I~//~1302M~
                		||  ii>=TN3 && ii<=TN7 && itsHand[ii-2]==0 && itsHand[ii+2]==0//~1301I~//~1302M~
                        )                                          //~1301I~//~1302M~
                        	posPillow0=ii;                         //~1301I~//~1302M~
                        else                                       //~1301I~//~1302M~
                    		posPillow=ii;                                  //~1227I~//~1301R~//~1302M~
                    }                                              //~1301I~//~1302M~
                    else                                           //~1301I~//~1302M~
                	if (itsHand[ii-1]==0 && itsHand[ii+1]==1       //~1227I~//~1302M~
                	||  itsHand[ii+1]==0 && itsHand[ii-1]==1       //~1302M~
                    )                                              //~1301R~//~1302M~
    	                posPillow2=ii;                             //~1227I~//~1302M~
                }                                                  //~1227I~//~1302M~
            }                                                      //~1227I~//~1302M~
            else    //word                                         //~1227R~//~1302M~
            {                                                      //~1227I~//~1302M~
                if ((Pintent & INTENT_TANYAO)==0)                  //~1302I~
                {                                                  //~1302I~
                    if (RAUtils.chkValueWordTile(ii,eswnDiscard)==0)//~1302I~
	            		posPillow=ii;                              //~1302I~
                    else                                           //~1302I~
	            		posPillow2=ii;                                     //~1227R~//~1302R~
                }                                                  //~1302I~
            }                                                      //~1227I~//~1302M~
            	                                                   //~1227I~//~1302M~
        }                                                          //~1227I~//~1302M~
        if (posPillow0>0)                                           //~1227I~//~1301R~//~1302M~
        	posPillow=posPillow0;                                  //~1301I~//~1302M~
        else                                                       //~1301I~//~1302M~
        if (posPillow<0)                                           //~1301I~//~1302M~
        	posPillow=posPillow2;                                  //~1301I~//~1302M~
        if (Dump.Y) Dump.println("RADSEval.searchPillow posPillow="+posPillow+",posPillow0="+posPillow0+",posPillow2="+posPillow2+",itsHand="+Utils.toString(itsHand,9));//~1227R~//~1301R~//~1302M~//~vaajR~//~vab9R~
        return posPillow;                                          //~1302M~
    }                                                              //~1227I~//~1302M~
    //***********************************************************************//~vab9I~
    private boolean chkIntentTryDiscard(int Pintent)                          //~vab9I~
    {                                                              //~vab9I~
        boolean swChkIntent=true;                                  //~vab9I~
        if (RSP.swAllInHand)                                       //~vab9M~
        {                                                          //~vab9M~
	        if (Dump.Y) Dump.println("RADSEval.chkIntentTryDiscard no chkIntent by swAllInHand");//~vab9I~
        	swChkIntent=false;	//flexible if all in hand          //~vab9I~
        }                                                          //~vab9M~
        else                                                       //~vab9I~
        if (Pintent==0)                                             //~vab9I~
        {                                                          //~vab9M~
	        if (Dump.Y) Dump.println("RADSEval.chkIntentTryDiscard no chkIntent by No intent");//~vab9I~
        	swChkIntent=false;	//no intent                        //~vab9I~
        }                                                          //~vab9M~
        else                                                       //~vab9I~
        if ((Pintent & (INTENT_3DRAGON))!=0)                       //~vab9I~
        {                                                          //~vab9I~
	        if (Dump.Y) Dump.println("RADSEval.chkIntentTryDiscard chkIntent by Yakuman(3DRAGON) intent");//~vab9I~//~vaf6R~
//      	swChkIntent=false;	//no intent                        //~vab9I~//~vaf6R~
        	swChkIntent=true;   //ignore shanten down(keep dragon tile)//~vaf6I~
        }                                                          //~vab9I~
        else                                                       //~vab9I~
        if (RSP.isFixedFirst())                                    //~vab9M~
        {                                                          //~vab9M~
	        if (Dump.Y) Dump.println("RADSEval.chkIntentTryDiscard no chkIntent by isFixedFirst");//~vab9I~
        	swChkIntent=false;	//already fixed,consider shanten Up/Down//~vab9I~
        }                                                          //~vab9M~
	    if (Dump.Y) Dump.println("RADSEval.chkIntentTryDiscard rc="+swChkIntent+",eswn="+eswnDiscard+",Pintent="+Integer.toHexString(Pintent)+",swAllInHand="+RSP.swAllInHand);//~vab9R~//~vabbR~
        return swChkIntent;                                        //~vab9I~
    }                                                              //~vab9I~
//    //***********************************************************************//~vab9R~
//    //*return keep this tile according intent                    //~vab9R~
//    //*rc:true:ignore shanten Up/Down                            //~vab9R~
//    //***********************************************************************//~vab9R~
//    private boolean isIgnoreRonValue(int[] PitsHand,int Ppos/*try discard*/,int Pintent)    //have to follow intent//~vab9R~
//    {                                                            //~vab9R~
//        if (Dump.Y) Dump.println("RADSEval.isIgnoreRonValue pos="+Ppos+",intent="+Integer.toHexString(Pintent)+",itsHand="+Utils.toString(PitsHand,9));//~vab9R~
//        boolean rc=false;                                        //~vab9R~
//        int num=Ppos%CTR_NUMBER_TILE;                            //~vab9R~
//        int type=Ppos/CTR_NUMBER_TILE;                           //~vab9R~
//        int[] itsStatHand=AG.aRADSmart.itsStatHand; //status before discard         //~1201I~//~vab9R~
//        if (Dump.Y) Dump.println("RADSEval.isIgnoreRonValue itsStathand="+Arrays.toString(itsStatHand));//~vab9R~
//        if ((Pintent & INTENT_TANYAO)!=0)                        //~vab9R~
//        {                                                        //~vab9R~
//            if (RAUtils.isTanyaoTile(Ppos)) //this should be kept//~vab9R~
//                if (itsStatHand[CSI_TERMINAL]!=0 || itsStatHand[CSI_WORD]!=0)   //non tanyao in hand to be discarded//~vab9R~
//                {                                                //~vab9R~
//                    if (Dump.Y) Dump.println("RADSEval.isIgnoreRonValue intent=tanyao return true by non tanyao tile in hand");//~vab9R~
//                    rc=true;                                     //~vab9R~
//                }                                                //~vab9R~
//        }                                                        //~vab9R~
//        else                                                     //~vab9R~
//        if ((Pintent & INTENT_CHANTA)!=0)                        //~vab9R~
//        {                                                        //~vab9R~
//            if (type==TT_JI || num<=TN3 && num>=TN7)       //need for chanta//~vab9R~
//            {                                                    //~vab9R~
//                for (int ii=0;ii<OFFS_WORDTILE;ii++)             //~vab9R~
//                {                                                //~vab9R~
//                    if (PitsHand[ii]==0)                         //~vab9R~
//                        continue;                                //~vab9R~
//                    int num2=ii%CTR_NUMBER_TILE;                 //~vab9R~
//                    if (num2>TN3 && num2<TN7)      //to be discarded//~vab9R~
//                    {                                            //~vab9R~
//                        if (Dump.Y) Dump.println("RADSEval.isIgnoreRonValue intent=chanta return true by num>=TN4 & num<=TN6 exist in hand");//~vab9R~
//                        rc=true;                                 //~vab9R~
//                        break;                                   //~vab9R~
//                    }                                            //~vab9R~
//                }                                                //~vab9R~
//            }                                                    //~vab9R~
//        }                                                        //~vab9R~
//        if (rc)                                                  //~vab9R~
//            return true;                                         //~vab9R~
//        if ((Pintent & INTENT_SAMECOLOR_ANY)!=0)                 //~vab9R~
//        {                                                        //~vab9R~
//            if (RAUtils.isMatchSameColor(true/*allow Word*/,Pintent,type)) //this should be kept by same color as intent//~vab9R~
//            {                                                    //~vab9R~
//                if ((Pintent & INTENT_SAMECOLOR_MAN)!=0)         //~vab9R~
//                {                                                //~vab9R~
//                    if (itsStatHand[CSI_PIN]!=0 || itsStatHand[CSI_SOU]!=0)  //other color to be discarded//~vab9R~
//                    {                                            //~vab9R~
//                        if (Dump.Y) Dump.println("RADSEval.isIgnoreRonValue intent=samecolor_man return true by non Man tile in hand");//~vab9R~
//                        rc=true;                                 //~vab9R~
//                    }                                            //~vab9R~
//                }                                                //~vab9R~
//                else                                             //~vab9R~
//                if ((Pintent & INTENT_SAMECOLOR_PIN)!=0)         //~vab9R~
//                {                                                //~vab9R~
//                    if (itsStatHand[CSI_SOU]!=0 || itsStatHand[CSI_MAN]!=0)//~vab9R~
//                    {                                            //~vab9R~
//                        if (Dump.Y) Dump.println("RADSEval.isIgnoreRonValue intent=samecolor_pin return true by non Pin tile in hand");//~vab9R~
//                        rc=true;                                 //~vab9R~
//                    }                                            //~vab9R~
//                }                                                //~vab9R~
//                else                                             //~vab9R~
//                if ((Pintent & INTENT_SAMECOLOR_SOU)!=0)         //~vab9R~
//                {                                                //~vab9R~
//                    if (itsStatHand[CSI_MAN]!=0 || itsStatHand[CSI_PIN]!=0)//~vab9R~
//                    {                                            //~vab9R~
//                        if (Dump.Y) Dump.println("RADSEval.isIgnoreRonValue intent=samecolor_Sou return true by non Sou tile in hand");//~vab9R~
//                        rc=true;                                 //~vab9R~
//                    }                                            //~vab9R~
//                }                                                //~vab9R~
//            }                                                    //~vab9R~
//        }                                                        //~vab9R~
//        if (rc)                                                  //~vab9R~
//            return true;                                         //~vab9R~
//        if ((Pintent & INTENT_7PAIR)==0 && (Pintent & INTENT_ALLSAME)!=0)//~vab9R~
//        {                                                        //~vab9R~
//            if (PitsHand[Ppos]>1)   //this should not be discard //~vab9R~
//                if (itsStatHand[CSI_SINGLE]!=0)  //single tile to be discarded//~vab9R~
//                    {                                            //~vab9R~
//                        if (Dump.Y) Dump.println("RADSEval.isIgnoreRonValue intent=allsame return true by single tile exist in hand");//~vab9R~
//                        rc=true;                                 //~vab9R~
//                    }                                            //~vab9R~
//        }                                                        //~vab9R~
//        if (Dump.Y) Dump.println("RADSEval.isIgnoreRonValue exit rc="+rc+",eswn="+eswnDiscard+",pos="+Ppos);//~vab9R~//~vabbR~//~vab9R~
//        return rc;                                               //~vab9R~
//    }                                                            //~vab9R~
}//class RADEval                                                   //~1131R~
