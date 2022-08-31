//*CID://+vaq0R~: update#=1003;                                    //~vaq1R~//~vaq0R~
//**********************************************************************//~v101I~
//2022/08/08 vaq0 (Bug)Honor tile; if Fix First, count fixErr need to consider middle Pon for 2-han constraint chk.//~vaq0I~
//                e.g) for Pon+Chii+Pon when Fix First, count of Fix Err should be 1(previously it was 0)//~vaq0I~
//2022/08/06 vapz (Bug)3anko fixchk,did not set FixErr             //~vapzI~
//2022/08/06 vapy (Bug)sameSeq doublerun did not chk multiwait     //~vapyI~
//2022/02/20 vakh set kataagari err different from fix err         //~vakhI~
//2022/02/20 vakg (Bug)invalid 3samehand(3anko) yaku chk           //~vakfI~
//2022/02/20 vakf (Bug)additional to vaka, chk FixYaku required for 3anko,(mix)chanta and tanyao//~vakfI~
//2022/02/22 vake (Bug)should reset all hanfixerr for multiple honor tile//~vakeI~
//2022/02/22 vakd (Bug)small 3dragon depends fix rule of honor(eswn,wgr); no need to fix rule for small 3dragon(allow big dragon:yakuman is always)//~vakdI~
//2022/02/20 vakb (Bug)It is Fix:Middle for straight/3sameseq when nonrelated chii before related if fixed at last//~vakbI~
//2022/02/20 vaka apply kataagari tsumo option                     //~vakaI~
//2021/11/14 vagr (Bug of vafh)determins honchan when pillow:tanyao//~vafrI~
//2021/11/01 vafh bug for HonChanta(TerminalMix)                   //~vafhI~
//2021/07/17 vaaQ (Bug)honchan decision; not cheked pillow is terminal//~vaaQI~
//2021/06/06 va91 sakizukechk for robot                            //~va91I~
//2021/04/07 va7d (Bug)misjudge 3shiki for 234 man 234234(pin 1peiko)//~va7dI~
//2021/03/09 va6d (BUG)mixFlush allows other color pillow          //~va26I~
//2020/11/02 va26 (BUG)Pinfu check err;missing check notnum        //~va26I~
//2020/11/02 va25 (BUG)Straight check error                        //~va25R~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                       //~va11R~

import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.dialog.RuleSettingEnum.*;
import static com.btmtest.StaticVars.AG;                           //~v@@@I~//~vakaI~
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.UA.Pair.*;
import static com.btmtest.game.UA.Rank.*;
import static com.btmtest.game.UA.UARonData.*;
import static com.btmtest.game.gv.Pieces.*;

//****************************************************             //~9C11I~
public class UARank                                                //~va11R~
{                                                                  //~0914I~
                                                                   //~va11I~
    UARonValue UARV;                                               //~va11R~
//  private int[][] dupCtr,dupCtrAll;                              //~va11R~
    private int[][] dupCtr;                                        //~va11I~
    private boolean swTanyao,swRulePinfuTaken,swHonor;             //~va11R~
//  private int rankFinal;//,rankTanyao;                           //~va11R~//~va91R~
    private UARonDataTree UARDT;                                   //~va11R~
    private UARonData UARD;                                        //~va11R~
    private int intNotAllHand,player,ctrEarth;                     //~va11R~
    private boolean swPinfu;                                       //~va11I~
    private UAPair UAP;                                            //~va11R~
//  private	Pair[][] pairSeqSS;                                    //~va11R~//~va91R~
    private Pair[] pairNumS;                                       //~va11R~
    private int sizePairSeqS;                                      //~va11R~
    private Pair[] pairNotNum;                                     //~va11R~
    private int ctrPairNotNum;                                     //~va11I~
    private static final int MAX_YAKU=20;                          //~va11I~
//  private int[][] yakuSS;   //yaku list for each mixed pairs     //~va11R~//~va91R~
//  private int[] ctrYakuSS;                                       //~va11R~//~va91R~
    public Rank[] longRankS;	//rank for each mixed pair         //~va11R~
    public Rank[] longRankFixErrS;	//rank for each mixed pair     //~va91I~
    private Rank longRank;                                         //~va11M~
    private Rank longRank7;                                        //~va11I~
    private int[]  intRankS;	//rank accum                       //~va11I~
    public  int[]  intRankFixErrS;	//rank accum                   //~va91R~
    public  int[]  intRankFixErrSMultiWait;	//of RankFixErr        //~vakhI~
    private int  rankFixErr;                                       //~va91I~
    private int  rankFixErrMultiWait;                              //~vakhI~
    private int idxPairNumSS;                                         //~va11R~
    private int statusPillow,typePillow,numberPillow;              //~va11R~
    private boolean swTaken;                                       //~va11I~
    private int ronNumber,ronType;                                 //~va11I~
    private Pair[] pairEarth;                                      //~va91I~
    private int typeYakuFix,eswnHonor,roundHonor;                                       //~va91I~
    private Rank longRankFixErr;                                   //~va91R~
    private boolean swAllInHand;                                   //~va91I~
    private boolean swChkFix;                                      //~va91I~
    private boolean swYakuFixMultiWaitTakeOK;                      //~vakaI~
    private boolean swFixErrMultiWait;                                //~vakhI~
    private boolean swNewChkEarthHonorTile=true;                   //~vaq0R~
//******************************************************           //~va11I~
//*from UARonDataTree                                              //~va11I~
//******************************************************           //~va11I~
	UARank(UARonValue Pparent)                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~9C11R~//~0925R~//~va11R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UARank.Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~9C11R~//~0925R~//~va11R~
        UARV=Pparent;                                              //~va11I~
        init();                                                    //~v@@@I~//~va11R~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	private void init()                                             //~v@@@I~//~va11R~
    {                                                              //~v@@@I~
    	swRulePinfuTaken=RuleSettingYaku.isPinfuTaken();           //~va11I~
	    swYakuFixMultiWaitTakeOK= AG.aRoundStat.swYakuFixLastMultiWaitTakeOK;//~vakaI~
    	typeYakuFix=RuleSettingYaku.getYakuFix();                   //~va91I~
        swAllInHand=UARV.swAllInHand;                               //~va91I~
        intNotAllHand=UARV.swAllInHand ? 0 : 1;                    //~va11R~
        player=UARV.player;                                        //~va11I~
        ronType=UARV.ronType;                                      //~va11I~
        ronNumber=UARV.ronNumber;                                  //~va11I~
        pairEarth=UARV.pairEarth;                                  //~va91M~
        if (Dump.Y) Dump.println("UARank.init typeYAkuFix="+typeYakuFix+",intNotAllHand="+intNotAllHand+",ronType="+ronType+",ronNumber="+ronNumber+",pairEarth count="+(pairEarth==null ? 0 : pairEarth.length));//~va91I~//~vafrR~
        if (Dump.Y) Dump.println("UARank.init swYakuFixMultiWaitTakeOK="+swYakuFixMultiWaitTakeOK);//~vakaI~
    }                                                              //~v@@@I~
	//*************************************************************************//~va11I~
	//*from UARonDataTree.getAmmount                               //~va11R~
	//*************************************************************************//~va11I~
//  public void chkYakuStandard(UARonDataTree Puardt,UARonData Puard,int[][] PdupCtr,int[][] PdupCtrAll)//~va11R~
    public int[] getRankStandard(UARonDataTree Puardt,UARonData Puard)//~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARank.getRankStandard");        //~va11R~
    	UARDT=Puardt;                                              //~va11I~
    	UARD=Puard;                                                //~va11I~
        swTaken=UARDT.swTaken;                                     //~va11R~
        UAP=UARD.UAP;                                              //~va11R~
//  	dupCtr=PdupCtr;                                            //~va11R~
//      dupCtrAll=PdupCtrAll;                                      //~va11R~
        swTanyao=UARDT.swTanyao;                                   //~va11I~
//      rankTanyao=UARDT.rankTanyao;                               //~va11R~
        swHonor=UARDT.swHonor;	//yakuhai including pillow         //~va26R~
        statusPillow=UARD.statusPillow;                            //~va11R~
        typePillow=UARD.typePillow;                                //~va11I~
        numberPillow=UARD.numberPillow;                            //~va11I~
        eswnHonor=UARDT.eswn;                                      //~va91I~
        roundHonor=UARDT.round;                                    //~va91I~
        if (Dump.Y) Dump.println("UARank.init getRankStandard eswnHonor="+eswnHonor+",roundHonor="+roundHonor);//~va91I~
        if (Dump.Y) Dump.println("UARank.init getRankStandard statusPillow="+statusPillow+",typePillow="+typePillow+",numberPillow="+numberPillow+",swTanyao="+swTanyao+",swHonor="+swHonor);//~vaaQI~
                                                                   //~va11I~
        setRank();                                                 //~va11I~
                                                                   //~va11I~
        return intRankS;                                           //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private void setRank()                                         //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARank.setRank");                //~va11R~
     	pairNotNum=UAP.pairNotNum;                                 //~va11R~
     	ctrPairNotNum=UAP.ctrPairNotNum;                           //~va11R~
        if (Dump.Y) Dump.println("UARank.setRank ctrPairNotNum="+ctrPairNotNum+",pairNotNum="+Pair.toString(pairNotNum));//~va11R~
     	Pair[][] pairNumSS=UAP.mixedSS;                            //~va11R~
        if (Dump.Y) Dump.println("UARank.setRank pairNumSS="+Pair.toString(pairNumSS));//~va11R~
     	int sizePairNumSS=pairNumSS.length;                        //~va11R~
//      ctrYakuSS=new int[sizePairNumSS];                          //~va11R~//~va91R~
     	intRankS=new int[sizePairNumSS];                           //~va11R~
     	intRankFixErrS=new int[sizePairNumSS];                     //~va91I~
     	intRankFixErrSMultiWait=new int[sizePairNumSS];            //~vakhI~
     	longRankS=new Rank[sizePairNumSS];                         //~va11R~
     	longRankFixErrS=new Rank[sizePairNumSS];                   //~va91I~
        idxPairNumSS=0;                                            //~va11R~
//      swChkFix=(!swAllInHand && UARV.typeYakuFix!=YAKUFIX_LAST); //~va91R~
//      swChkFix=(UARV.typeYakuFix!=YAKUFIX_LAST) && !(swAllInHand && swTake);//~va91R~
//      swChkFix=typeYakuFix!=YAKUFIX_LAST; //Later Take on allInHand is cheked and it is OK by 1han of RANK_TAKE_NOEARTH//~va91R~//~vakaR~
        swChkFix=typeYakuFix!=YAKUFIX_LAST && !(swAllInHand && swTaken);//~vakaI~
        for (Pair pairS[]:pairNumSS)                               //~va11R~
        {                                                          //~va11I~
            longRank=new Rank();                                   //~va11R~
            longRankFixErr=new Rank();                                 //~va91I~
        	int rank=0;                                            //~va91R~
        	rankFixErr=0;                                          //~va91I~
        	rankFixErrMultiWait=0;                                 //~vakhI~
        	if (pairS!=null)                                       //~va11I~
        		rank=setRank(pairS);                               //~va11R~
        	intRankS[idxPairNumSS]=rank;                           //~va11R~
        	intRankFixErrS[idxPairNumSS]=rankFixErr;               //~va91I~
        	intRankFixErrSMultiWait[idxPairNumSS]=rankFixErrMultiWait;//~vakhI~
        	longRankS[idxPairNumSS]=longRank;                         //~va11R~
        	if (Dump.Y) Dump.println("UARank.setRank rank="+rank+",rankFixErr="+rankFixErr+",idxPairNumSS="+idxPairNumSS+",longRank="+longRank.toStringName());//~vafrR~
        	longRankFixErrS[idxPairNumSS]=longRankFixErr;          //~va91I~
            idxPairNumSS++;                                        //~va11R~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARank.setRank rankS="+ Arrays.toString(intRankS)+",longRankFixErrS="+Rank.toString(longRankFixErrS)+",longRankS="+Rank.toString(longRankS)+",longRankFixErrS="+Rank.toString(longRankFixErrS));//~va11R~//~va91R~
        if (Dump.Y) Dump.println("UARank.setRank intRankFixErrS="+Arrays.toString(intRankFixErrS));//~vafrI~
        if (Dump.Y) Dump.println("UARank.setRank intRankFixErrSMultiWait="+Arrays.toString(intRankFixErrSMultiWait));//~vakhI~
    }                                                              //~va11I~
	//*************************************************************************//~va91I~
	//*rc:false:fix err                                            //~va91I~
	//*************************************************************************//~va91I~
    private boolean setFixErr(int Prank,boolean PswLast,boolean PswMiddle)//~va91I~
    {                                                              //~va91I~
        if (Dump.Y) Dump.println("UARank.setFixErr Prank="+Rank.toStringName(Prank)+",swLast="+PswLast+",swMiddle="+PswMiddle);//~va91I~
        boolean rc=true;
        if (typeYakuFix==YAKUFIX_FIRST)                            //~va91I~
        {                                                          //~va91I~
            if (PswLast || PswMiddle)                                //~va91I~
            {                                                      //~va91I~
                addYakuFixErr(Prank);                              //~va91R~
                rc=false;                                          //~va91I~
            }                                                      //~va91I~
        }                                                          //~va91I~
        else                                                       //~va91I~
        {                                                          //~va91I~
            if (PswLast)                                            //~va91I~
            {                                                      //~va91I~
	            addYakuFixErr(Prank);                              //~va91R~
                rc=false;                                          //~va91I~
            }                                                      //~va91I~
        }
        if (Dump.Y) Dump.println("UARank.setFixErr rc="+rc+",Prank="+Rank.toStringName(Prank)+",swLast="+PswLast+",swMiddle="+PswMiddle);//~vakaI~
        return rc;//~va91I~
    }                                                              //~va91I~
	//*************************************************************************//~vakhI~
	//*setk multiwait err                                          //~vakhR~
    //fixerr AND kataagari tsumo not allowed by option             //~vakhI~
    //	  if (!(swTaken && swLastNotFix && setMultiWaitTake(RYAKU_ROUND,swOther)))	//allow multiwait take by option//~vakhI~
	//*rc:swFixErrMultiWait                                        //~vakhI~
	//*************************************************************************//~vakhI~
    private boolean setFixErrMultiWait_old(boolean PswOther)       //~vakhR~
    {                                                              //~vakhI~
    	boolean rc;                                                //~vakhI~
        swFixErrMultiWait=false;                                   //~vakhI~
        if (typeYakuFix==YAKUFIX_FIRST && PswOther)	//no related on Earth//~vakhI~
        	rc=false;                                              //~vakhI~
        else                                                       //~vakhI~
        	rc=true;                                               //~vakhI~
        swFixErrMultiWait=rc;                                      //~vakhI~
        if (Dump.Y) Dump.println("UARank.setFixErrMultiWait_old typeYakuFix="+typeYakuFix+",rc=swFixErrMultiWait="+rc+",swOther="+PswOther);//~vakhI~
        return rc;                                                 //~vakhI~
    }                                                              //~vakhI~
	//*************************************************************************//~vakhI~
    private boolean setFixErrMultiWait(boolean PswOther)           //~vakhI~
    {                                                              //~vakhI~
    	boolean rc;                                                //~vakhI~
        swFixErrMultiWait=false;                                   //~vakhI~
//      if (typeYakuFix==YAKUFIX_FIRST && PswOther)	//no related on Earth//~vakhI~
        if (PswOther)	//no related on Earth                      //~vakhI~
        	rc=false;                                              //~vakhI~
        else                                                       //~vakhI~
        	rc=true;                                               //~vakhI~
        swFixErrMultiWait=rc;                                      //~vakhI~
        if (Dump.Y) Dump.println("UARank.setFixErrMultiWait typeYakuFix="+typeYakuFix+",rc=swFixErrMultiWait="+rc+",swOther="+PswOther);//~vakhI~
        return rc;                                                 //~vakhI~
    }                                                              //~vakhI~
    //****************************************************************//~vakaI~
    //*apply multiWait TakeOK if swTaken & swLastNotFix for FixErr //~vakhR~
    //*for ronTaken is related and ryanmen under sakizuke,nakaduke err//~vakaR~
    //****************************************************************//~vakaI~
    private boolean setMultiWaitTake(int Prank,boolean PswOther)   //~vakaI~//~vakhR~
    {                                                              //~vakaI~
    	if (Dump.Y) Dump.println("UARank.setMultiWaitTake rank="+Rank.toStringName(Prank)+",swYakuFixMultiWaitTakeOK="+swYakuFixMultiWaitTakeOK+",swOther="+PswOther+",swAllInHand="+swAllInHand+",typeYakuFix="+typeYakuFix);//~vakaR~//~vakhR~
        boolean rc=false;                                          //~vakaI~
	    if (swAllInHand || swYakuFixMultiWaitTakeOK)	//not all in Hand//~vakaR~
        {                                                          //~vakaI~
          if (PswOther && typeYakuFix==YAKUFIX_FIRST)              //~vakaI~
          {                                                        //~vakaI~
    		if (Dump.Y) Dump.println("UARank.setMultiWaitTake no rest by take by FixFirst and swOther=T");//~vakaI~
          }                                                        //~vakaI~
          else                                                     //~vakaI~
          {                                                        //~vakaI~
            addYakuFixErrMultiWaitTakeOK(Prank);      //allow multiwait take ron//~vakaR~
            rc=true;                                               //~vakaR~
          }                                                        //~vakaI~
        }                                                          //~vakaI~
    	if (Dump.Y) Dump.println("UARank.setMultiWaitTake rc="+rc+",rank="+Rank.toStringName(Prank));//~vakaI~//~vakhR~
        return rc;                                                 //~vakaI~
    }                                                              //~vakaI~
    //****************************************************************//~vakhI~
    //*apply multiWait TakeOK if swTaken & swLastNotFix for FixErr //~vakhI~
    //*For honor, no case of err by fixMix                         //~vakhI~
    //*for ronTaken is related and ryanmen under sakizuke,nakaduke err//~vakhI~
    //****************************************************************//~vakhI~
    private boolean setMultiWaitTakeHonor(int Prank,boolean PswOther)//~vakhR~
    {                                                              //~vakhI~
    	if (Dump.Y) Dump.println("UARank.setMultiWaitTakeHonor rank="+Rank.toStringName(Prank)+",swYakuFixMultiWaitTakeOK="+swYakuFixMultiWaitTakeOK+",swOther="+PswOther+",swAllInHand="+swAllInHand+",typeYakuFix="+typeYakuFix);//~vakhR~
        boolean rc=false;                                          //~vakhI~
	    if (swAllInHand)	//always allow in AllInHand            //~vakhR~
        {                                                          //~vakhI~
            addYakuFixErrMultiWaitTakeOK(Prank);      //allow multiwait take ron//~vakhI~
            rc=true;                                               //~vakhI~
        }                                                          //~vakhI~
    	if (Dump.Y) Dump.println("UARank.setMultiWaitTakeHonor rc="+rc+",rank="+Rank.toStringName(Prank));//~vakhR~
        return rc;                                                 //~vakhI~
    }                                                              //~vakhI~
    //****************************************************************//~vakgI~
    //*3anko fix last allowance                                    //~vapzR~
    //*rc:true:allow fix timing                                    //~vapzI~
    //****************************************************************//~vakgI~
    private boolean isMultiWaitTake(boolean PswOther)              //~vakgI~
    {                                                              //~vakgI~
    	if (Dump.Y) Dump.println("UARank.isMultiWaitTake swYakuFixMultiWaitTakeOK="+swYakuFixMultiWaitTakeOK+",swOther="+PswOther+",swAllInHand="+swAllInHand+",typeYakuFix="+typeYakuFix);//~vakgI~
        boolean rc=false;                                          //~vakgI~
	    if (swAllInHand || swYakuFixMultiWaitTakeOK)	//not all in Hand//~vakgI~
        {                                                          //~vakgI~
          if (PswOther && typeYakuFix==YAKUFIX_FIRST)              //~vakgI~
          {                                                        //~vakgI~
    		if (Dump.Y) Dump.println("UARank.isMultiWaitTake no rest by take by FixFirst and swOther=T");//~vakgI~
          }                                                        //~vakgI~
          else                                                     //~vakgI~
          {                                                        //~vakgI~
            rc=true;                                               //~vakgI~
          }                                                        //~vakgI~
        }                                                          //~vakgI~
    	if (Dump.Y) Dump.println("UARank.isMultiWaitTake rc="+rc); //~vakgI~
        return rc;                                                 //~vakgI~
    }                                                              //~vakgI~
    //****************************************************************//~vakaI~
    //*return:true:reset fixerr                                    //~vakaI~
    //*Not Used                                                    //+vaq0I~
    //****************************************************************//~vakaI~
    private boolean setMultiWaitTakeWGR(boolean PswOther)          //~vakaI~
    {                                                              //~vakaI~
    	if (Dump.Y) Dump.println("UARank.setMultiWaitTakeWGR swYakuFixMultiWaitTakeOK="+swYakuFixMultiWaitTakeOK+",swOther="+PswOther+",swAllInHand="+swAllInHand);//~vakaI~//~vakhR~
        boolean rc=false;                                          //~vakaI~
	    if (swAllInHand || swYakuFixMultiWaitTakeOK)	//not all in Hand//~vakaI~
        {                                                          //~vakaI~
          if (PswOther && typeYakuFix==YAKUFIX_FIRST)              //~vakaI~
          {                                                        //~vakaI~
    		if (Dump.Y) Dump.println("UARank.setMultiWaitTakeWGR no rest by take by FixFirst and swOther=T");//~vakaI~
          }                                                        //~vakaI~
          else                                                     //~vakaI~
          {                                                        //~vakaI~
            addYakuFixErrMultiWaitTakeOKWGR();      //allow multiwait take ron//~vakaR~
            rc=true;                                               //~vakaI~
          }                                                        //~vakaI~
        }                                                          //~vakaI~
    	if (Dump.Y) Dump.println("UARank.setMultiWaitTakeWGR rc="+rc);//~vakaR~//~vakhR~
        return rc;                                                 //~vakaI~
    }                                                              //~vakaI~
    //****************************************************************//~vakhI~
    //*return:true:reset fixerr                                    //~vakhI~
    //*Not Used                                                    //+vaq0I~
    //****************************************************************//~vakhI~
    private boolean setMultiWaitTakeWGRHonor(boolean PswOther)     //~vakhR~
    {                                                              //~vakhI~
    	if (Dump.Y) Dump.println("UARank.setMultiWaitTakeWGRHonor swYakuFixMultiWaitTakeOK="+swYakuFixMultiWaitTakeOK+",swOther="+PswOther+",swAllInHand="+swAllInHand);//~vakhR~
        boolean rc=false;                                          //~vakhI~
	    if (swAllInHand)	//always allow in AllInHand            //~vakhI~
        {                                                          //~vakhI~
            addYakuFixErrMultiWaitTakeOKWGR();      //allow multiwait take ron//~vakhI~
            rc=true;                                               //~vakhI~
        }                                                          //~vakhI~
    	if (Dump.Y) Dump.println("UARank.setMultiWaitTakeWGRHonor rc="+rc);//~vakhI~
        return rc;                                                 //~vakhI~
    }                                                              //~vakhI~
	//*************************************************************************//~va91I~
	//*rc:false:fix err                                            //~va91I~
	//*************************************************************************//~va91I~
    private boolean setFixErrWGR(boolean PswLast,boolean PswMiddle)//~va91I~
    {                                                              //~va91I~
        if (Dump.Y) Dump.println("UARank.setFixErrWGR swLast="+PswLast+",swMiddle="+PswMiddle);//~va91I~
        boolean rc=true;                                           //~va91I~
        if (typeYakuFix==YAKUFIX_FIRST)                            //~va91I~
        {                                                          //~va91I~
            if (PswLast || PswMiddle)                              //~va91I~
            {                                                      //~va91I~
            	longRankFixErr.addYaku(RYAKU_CTR_WGR,1);    //at ReqDlg dcrese amount up to Mangan*3//~va91I~
                rc=false;                                          //~va91I~
            }                                                      //~va91I~
        }                                                          //~va91I~
        else                                                       //~va91I~
        {                                                          //~va91I~
            if (PswLast)                                           //~va91I~
            {                                                      //~va91I~
            	longRankFixErr.addYaku(RYAKU_CTR_WGR,1);    //at ReqDlg dcrese amount up to Mangan*3//~va91I~
                rc=false;                                          //~va91I~
            }                                                      //~va91I~
        }                                                          //~va91I~
        if (Dump.Y) Dump.println("UARank.setFixErrWGR rc="+rc+",swLast="+PswLast+",swMiddle="+PswMiddle);//~vakaI~
        return rc;                                                 //~va91I~
    }                                                              //~va91I~
	//*************************************************************************//~va11I~
    private void addYaku(int Prank)                                //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARank.addYaku Prank="+Prank);   //~va11R~
    	Rank.addYaku(longRank,Prank);                                   //~va11R~
    }                                                              //~va11I~
	//*************************************************************************//~va91I~
    private void addYakuFixErr(int Prank)                          //~va91R~
    {                                                              //~va91I~
        if (Dump.Y) Dump.println("UARank.addYakuFixErr Prank="+Rank.toStringName(Prank));//~va91R~
    	Rank.addYaku(longRankFixErr,Prank);                        //~va91R~
    }                                                              //~va91I~
	//*************************************************************************//~vakaI~
    private void addYakuFixErrMultiWaitTakeOK(int Prank)           //~vakaI~
    {                                                              //~vakaI~
        if (Dump.Y) Dump.println("UARank.addYakuFixErrMultiWaitTakeOK Prank="+Rank.toStringName(Prank));//~vakaI~
    	Rank.resetYaku(longRankFixErr,Prank);                      //~vakaI~
    }                                                              //~vakaI~
	//*************************************************************************//~vakaI~
	//*Not Used                                                    //+vaq0I~
	//*************************************************************************//+vaq0I~
    private void addYakuFixErrMultiWaitTakeOKWGR()                 //~vakaI~
    {                                                              //~vakaI~
        if (Dump.Y) Dump.println("UARank.addYakuFixErrMultiWaitTakeOKWGR");//~vakaI~
    	Rank.resetYakuWGR(longRankFixErr);                         //~vakaI~
    }                                                              //~vakaI~
	//*************************************************************************//~va11I~
    private void addYaku7(int Prank)                               //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARank7.addYaku Prank="+Prank);  //~va11I~
    	Rank.addYaku(longRank7,Prank);                             //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    public Rank getRank(int PidxRank)                           //~va11I~
    {                                                              //~va11I~
    	Rank longRank=longRankS[PidxRank];                         //~va11I~
        if (Dump.Y) Dump.println("UARank.getRankMix idx="+PidxRank+",rc=Prank="+longRank.toString());//~va11I~
        return longRank;
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private int setRank(Pair[] PpairS)                             //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARank.setRank PpairsS="+Pair.toString(PpairS));//~va11R~
    	int rank=0;
        pairNumS=PpairS;                                           //~va11R~
        sizePairSeqS=pairNumS.length;                              //~va11R~
//        if (swTanyao)                                            //~va11R~
//        {                                                        //~va11R~
//            if (rankTanyao!=0)                                   //~va11R~
//            {                                                    //~va11R~
//                addYaku(RYAKU_TANYAO);                           //~va11R~
//                rank+=rankTanyao;   //optionally 1 set by UARonDataTree//~va11R~
//            }                                                    //~va11R~
//        }                                                        //~va11R~
        swPinfu=isPinfu();                                         //~va11R~
        if (swPinfu)                                               //~va11R~
        {                                                          //~va11I~
            if (!swTaken || swRulePinfuTaken)                      //~va11R~
            {                                                      //~va11I~
		    	addYaku(RYAKU_PINFU);                              //~va11I~
                rank+=RANK_PINFU;	//rank by taken is added at other place//~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
        boolean swHonorTileOK=true;                                //~vakdI~
        if (swChkFix)                                              //~va91R~
        {                                                          //~vakdI~
	    	if (swNewChkEarthHonorTile)                            //~vaq0I~
	          	swHonorTileOK=chkEarthHonorTile();	//Wind,Round,WGR//~vaq0I~
            else                                                   //~vaq0I~
          		swHonorTileOK=chkEarthHonorTile_Old();             //~vaq0R~
        }                                                          //~vakdI~
    //rank1                                                        //~va11M~
//      rank+=chkSameSeq();         //1peiko  by chkSameSeq2       //~va11R~
        if (swChkFix) //not FixLast & !(take at allinHand)         //~vakfR~
        	chkTanyaoTaken();                                      //~vakfR~
    //rank2                                                        //~va11R~
        rank+=chk3SameSeq();        //3shoku                       //~va11R~
        rank+=chk3Same();           //3tonko                       //~va11R~
        rank+=chk3SameHand();       //3anko                        //~va11R~
        rank+=chkStraight();        //ikkitukan                    //~va11R~
        rank+=chkAllSame();         //toitoi                       //~va11R~
        rank+=chkTerminalMix();     //honchanta                    //~va11R~
        rank+=chk3Kan();            //3kan                         //~va11R~
    //rank3                                                        //~va11R~
        rank+=chkSameSeq();        //1/2peiko                     //~va11R~
        rank+=chkTerminal();        //junchanta                    //~va11R~
        rank+=chkFlushMix();        //honitsu                      //~va11R~
    //rank4                                                        //~va11R~
      if (swHonorTileOK)	//3DragonSmall is mised with yakuhai;fixed of by honor tile 3dragon has 2 han more//~vakdI~
      {                                                            //~vakdI~
        rank+=chk3DragonSmall();    //shosangen                    //~va11R~
      }                                                            //~vakdI~
        rank+=chkTerminalOnlyMix(); //honro                        //~va11R~
    //rank6                                                        //~va11R~
        rank+=chkFlush();           //chinitsu                     //~va11R~
        if (Dump.Y) Dump.println("UARank.chkYakuStandard rc="+rank);//~va11R~
        return rank;                                               //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    public boolean isPinfu()                                       //~va11R~
    {                                                              //~va11I~
    	boolean rc=true;                                              //~va11R~
    	if (intNotAllHand!=0)                                      //~va11R~
        	rc=false;                                              //~va11I~
        else                                                       //~va11I~
        if (swHonor)  //true also if pillow is honor               //~va11R~
        	rc=false;                                              //~va11I~
        else                                                       //~va26I~
     	if (ctrPairNotNum!=0)                                      //~va26I~
        	rc=false;                                              //~va26I~
        else                                                       //~va11I~
        {                                                          //~va11I~
            boolean swSide=false;                                  //~va11I~
        	if (Dump.Y) Dump.println("UARank.isPinfu pairNumS="+Pair.toString(pairNumS));//~va11I~
            for(Pair pair:pairNumS)                                //~va11R~
            {                                                      //~va11I~
            	if (pair.typePair!=PT_NUMSEQ)                      //~va11R~
                {                                                  //~va11I~
                	rc=false;                                      //~va11I~
                    break;                                         //~va11I~
                }                                                  //~va11I~
//*1 rank up is higher than point 2 up                             //~va11I~
                else    //NUMSEQ                                   //~va11R~
                {                                                  //~va11R~
                    int rc2=isPinfuSub(pair);   //chk penchan,kanchan//~va11R~
                    if (rc2==0)     //not contains ron tile        //~va11I~
                        continue;                                  //~va11I~
                    if (rc2==1)	//side                             //~va11R~
    	                swSide=true;                               //~va11I~
                }                                                  //~va11R~
            }                                                      //~va11I~
            if (!swSide)	//found pair containing ron tile on the side//~va11I~
            {                                                      //~va11I~
		        if (Dump.Y) Dump.println("UARank.isPinfu swSide=false");//~va11I~
            	rc=false;                                          //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARank.isPinfu intNoAllHand="+intNotAllHand+",ctrPairNotNum="+ctrPairNotNum+",swHonor="+swHonor+",rc="+rc+",pairNumS="+Pair.toString(pairNumS));//~va26R~
        return rc;                                                 //~va11M~
    }                                                              //~va11I~
    //******************************************************************//~va11I~
    //rc:2,penchan/kanchan 1: both side 0:not contains ron tile    //~va11I~
    //*****************************************************************//~va11I~
    public  int isPinfuSub(Pair Ppair)                              //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonData.getPointNumSeq Ppair="+Pair.toString(Ppair));//~va11I~
        int pt=0;                                                  //~va11I~
        if (Ppair.type==ronType)                                   //~va11I~
        {                                                          //~va11I~
        	int num=Ppair.number;                                  //~va11I~
            if (ronNumber==num+1) //kanchan                        //~va11I~
            	pt=2;                                              //~va11I~
            else                                                   //~va11I~
            if (ronNumber==num && num==6) //(7)89 penchan          //~va11I~
            	pt=2;                                              //~va11I~
            else                                                   //~va11I~
            if (ronNumber==num+2 && num==0) //12(3) penchan        //~va11I~
            	pt=2;                                              //~va11I~
            else                                                   //~va11I~
            if (ronNumber==num || ronNumber==num+2) //side         //~va11I~
            	pt=1;                                              //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARank.isPinfuSub rc="+pt+",ronType="+ronType+",ronNumber="+ronNumber+",pairType="+Ppair.type+",pairNumber="+Ppair.number);//~va11R~
        return pt;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~vakfI~
    //*rc:fixerr was reset to allow multiwait for tanyao taken     //~vakfI~
    //****************************************************************//~vakfI~
    private boolean chkTanyaoTaken()                      //~vakfI~
    {                                                              //~vakfI~
        boolean swLastNotFix=false,rc=false;                       //~vakfR~
        Rank longRankOther=UARDT.longRankOther;                    //~vakfI~
    //***************************                                  //~vakfI~
    	if (Dump.Y) Dump.println("UARank.chkTanyaoTaken longRankOther="+longRankOther.toString()+",ronType="+ronType+",ronNumber="+ronNumber);//~vakfI~
        if (!longRankOther.isContains(RYAKU_TANYAO))               //~vakfI~
        	return false;                                          //~vakfI~
        for (int ii=0;ii<sizePairSeqS;ii++)                        //~vakfI~
        {                                                          //~vakfI~
            Pair pair=pairNumS[ii];                                //~vakfI~
            int pairnum=pair.number;                               //~vakfI~
            if (pair.typePair==PT_NUMSEQ                           //~vakfI~
            &&  pair.type==ronType                                 //~vakfI~
            && (pair.flag & TDF_CHII)==0)//in hand by flag         //~vakfI~
            {                                                      //~vakfI~
            	if ((pairnum==TN2 && ronNumber==pairnum+2)      //right side of seqmeld of TN2//~vakfI~
            	||  (pairnum==TN6 && ronNumber==pairnum  ))      //left side of seqmeld of TN6//~vakfI~
                    swLastNotFix=true;                             //~vakfI~
            }                                                      //~vakfI~
        }                                                          //~vakfI~
        if (swLastNotFix)	//fix err and last related take        //~vakfI~
        {                                                          //~vakfI~
        	rc=setFixErr(RYAKU_TANYAO,true/*swLast*/,false/*swMiddle*/);//~vakfI~
        	if (!rc && swTaken)	//fix err and last related take    //~vakfI~
	    		rc=setMultiWaitTake(RYAKU_TANYAO,false/*swOther*/);	//allow multiwait take by option//~vakfI~
	        if (!rc)   //multiwait not accepted by swTaken         //~vakfI~//~vakhR~
            {                                                      //~vakhI~
		        rankFixErr+=RANK_TANYAO;                           //~vakfI~
		        rankFixErrMultiWait+=RANK_TANYAO;                  //~vakhI~
            }                                                      //~vakhI~
        }                                                          //~vakfI~
    	if (Dump.Y) Dump.println("UARank.chkTanyaoTaken rc="+rc+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix);//~vakfI~
    	if (Dump.Y) Dump.println("UARank.chkTanyaoTaken swTaken="+swTaken+",swLastNotFix="+swLastNotFix+",rankFixErr="+rankFixErr);//~vakfR~
    	return rc;                                                 //~vakfI~
    }                                                              //~vakfI~
    //****************************************************************//~va91I~
    //*FixFirst chk                                                //~va91I~
    //*rc:false fixFirst/FixMiddle err                             //~va91I~
    //****************************************************************//~va91I~
//  private boolean chkEarthHonorTile()	//Wind,Round,WGR           //~va91I~//~vaq0R~
    private boolean chkEarthHonorTile_Old()	//Wind,Round,WGR       //~vaq0I~
    {                                                              //~va91I~
        int ctrPairNotFirst=0,ctrPairFirst=0;                      //~va91R~
        boolean swOther=false,swLast=false,swMiddle=false,rc;      //~va91R~
        boolean swLastNotFix=false;                                //~vakaI~
        int ctrWGR;                                                //~va91I~
        boolean swTakeOK;                                          //~vakhI~
    //***************************                                  //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile pairEarth="+Pair.toString(pairEarth));//~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile longRankOther="+Rank.toStringName(UARDT.longRankOther,true/*printHonor*/));//~va91R~
        Rank rankOther=UARDT.longRankOther;                        //~va91I~
        ctrWGR=rankOther.getWGR();                                 //~va91R~
        if (!rankOther.isContains(RYAKU_ROUND)                     //~va91R~
        &&  !rankOther.isContains(RYAKU_WIND)                      //~va91R~
        &&  ctrWGR==0                                              //~va91R~
        )                                                          //~va91I~
        {                                                          //~va91I~
	    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile no honor rank longrRank="+Rank.toStringName(longRank));//~va91I~
        	return true;                                           //~va91I~
        }                                                          //~va91I~
        //*chk earth                                               //~va91I~
	    for (Pair pair:pairEarth)                                  //~va91I~
        {                                                          //~va91I~
        	if (pair==null)                                        //~va91I~
            	continue;                                          //~va91I~
            if (pair.type==TT_JI && (pair.number>=TT_4ESWN_CTR || pair.number==eswnHonor|| pair.number==roundHonor))//~va91I~
            {                                                      //~va91I~
                if (swOther)                                       //~va91I~
                    ctrPairNotFirst++;                             //~va91I~
                else                                               //~va91I~
                    ctrPairFirst++;                                //~va91I~
            }                                                      //~va91I~
            else                                                   //~va91I~
                swOther=true;                                      //~va91I~
        }                                                          //~va91I~
        if (ctrPairFirst==0)                                       //~va91I~
        {                                                          //~va91I~
        	//chk in hand                                          //~va91I~
        	if (ctrPairNotFirst!=0)  //non related earth pair exist//~va91I~
            {                                                      //~va91I~
            	swMiddle=true;       //OK if YAKUFIX_MIDDLE        //~va91I~
            }                                                      //~va91I~
            for (Pair pair:pairNotNum)     //earth and hand        //~va91I~
            {                                                      //~va91I~
                if (pair.type==TT_JI && (pair.number>=TT_4ESWN_CTR || pair.number==eswnHonor|| pair.number==roundHonor))//~va91I~
                    if (pair.swHand)    //not earth                //~va91I~
                    {                                              //~va91I~
                        if (pair.type==ronType && pair.number==ronNumber)//~va91I~
                        {                                          //~va91I~
                        	boolean swLastFixed=(typePillow==TT_JI && (numberPillow>=TT_4ESWN_CTR || numberPillow==eswnHonor|| numberPillow==roundHonor));//~vakaI~
                            if (!swLastFixed) //shanpon of honor and not honor//~vakaI~
                            	swLastNotFix=true;                 //~vakaI~
    						if (Dump.Y) Dump.println("UARank.chkEarthHonorTile swAllInHand="+swAllInHand+",swMiddle="+swMiddle+",typePillow="+typePillow+",numberPillow="+numberPillow);//~va91R~
                        	if (!swMiddle)                         //~va91I~
                            {                                      //~va91I~
//                                swLast=true;                       //~va91R~//~vakhR~
//                                if (swAllInHand)                   //~va91I~//~vakhR~
////                                  if (typePillow==TT_JI && (numberPillow>=TT_4ESWN_CTR || numberPillow==eswnHonor|| numberPillow==roundHonor))//~va91I~//~vakaR~//~vakhR~
//                                    if (swLastFixed)               //~vakaI~//~vakhR~
//                                        swLast=false;   //menzen shanpon//~va91I~//~vakhR~
								swLast=!swLastFixed || swOther;    //~vakhI~
                            }                                      //~va91I~
                        }                                          //~va91I~
                        else                                       //~va91I~
                        {                                          //~va91I~
                            swMiddle=false;                         //~va91I~
                        	swLast=false;                           //~va91I~
                            break;                                 //~va91I~
                        }                                          //~va91I~
                    }                                              //~va91I~
            }                                                      //~va91I~
        }                                                          //~va91I~
        int id;                                                    //~va91I~
        int han=0;                                                  //~va91I~
        int hanMultiWait=0;                                        //~vakhI~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile swTaken="+swTaken+",swlastNotFix="+swLastNotFix);//~vakaI~
        if (rankOther.isContains(RYAKU_ROUND))                     //~va91R~
        {                                                          //~va91I~
	        if (!setFixErr(RYAKU_ROUND,swLast,swMiddle))           //~va91I~
            {                                                      //~vakaI~
//              if (!(swTaken && swLastNotFix && setMultiWaitTake(RYAKU_ROUND,swOther)))  //allow multiwait take by option//~vakaR~//~vakhR~
//              {                                                  //~vakhR~
//                han++;                                             //~va91I~//~vakhR~
//                if (setFixErrMultiWait(swOther))                 //~vakhR~
//                    hanMultiWait++;                              //~vakhR~
//              }                                                  //~vakhR~
             	swTakeOK=false;                                    //~vakhI~
        	  	if (swTaken && swLastNotFix)                       //~vakhI~
					swTakeOK=setMultiWaitTakeHonor(RYAKU_ROUND,swOther);//allow multiwait take by option//~vakhR~
                if (!swTakeOK)                                     //~vakhI~
                {                                                  //~vakhI~
                	han++;                                         //~vakhI~
                	if (swLastNotFix && setFixErrMultiWait(swOther))//~vakhR~
                		hanMultiWait++;                            //~vakhI~
                }                                                  //~vakhI~
            }                                                      //~vakaI~
        }                                                          //~va91I~
        if (rankOther.isContains(RYAKU_WIND))                      //~va91R~
        {                                                          //~va91I~
	        if (!setFixErr(RYAKU_WIND,swLast,swMiddle))            //~va91I~
            {                                                      //~vakaI~
//              if (!(swTaken && swLastNotFix && setMultiWaitTake(RYAKU_WIND,swOther)))   //allow multiwait take by option//~vakaR~//~vakhR~
//              {                                                  //~vakhR~
//                han++;                                             //~va91I~//~vakhR~
//                if (setFixErrMultiWait(swOther))                 //~vakhR~
//                    hanMultiWait++;                              //~vakhR~
//              }                                                  //~vakhR~
             	swTakeOK=false;                                    //~vakhI~
        	  	if (swTaken && swLastNotFix)                       //~vakhI~
					swTakeOK=setMultiWaitTakeHonor(RYAKU_WIND,swOther);//allow multiwait take by option//~vakhR~
                if (!swTakeOK)                                     //~vakhI~
                {                                                  //~vakhI~
                	han++;                                         //~vakhI~
                	if (swLastNotFix && setFixErrMultiWait(swOther))//~vakhR~
                		hanMultiWait++;                            //~vakhI~
                }                                                  //~vakhI~
            }                                                      //~vakaI~
        }                                                          //~va91I~
        if (ctrWGR!=0)                                             //~va91I~
        {                                                          //~va91I~
	        if (!setFixErrWGR(swLast,swMiddle))                    //~va91R~
            {                                                      //~vakaI~
//              if (!(swTaken && swLastNotFix && setMultiWaitTakeWGR(swOther)))   //allow multiwait take by option//~vakaR~//~vakhR~
//              {                                                  //~vakhR~
////              han++;                                             //~va91I~//~vakeR~//~vakhR~
//                han+=ctrWGR;                                       //~vakeI~//~vakhR~
////              if (setFixErrMultiWait(swOther))                 //~vakhR~
//                    hanMultiWait++;                              //~vakhR~
//              }                                                  //~vakhR~
             	swTakeOK=false;                                    //~vakhM~
        	  	if (swTaken && swLastNotFix)                       //~vakhI~
					swTakeOK=setMultiWaitTakeWGRHonor(swOther);//allow multiwait take by option//~vakhR~
                if (!swTakeOK)                                     //~vakhM~
                {                                                  //~vakhM~
                	han+=ctrWGR;                                   //~vakhM~
                	if (swLastNotFix && setFixErrMultiWait(swOther))//~vakhR~
                		hanMultiWait+=ctrWGR;                      //~vakhM~
                }                                                  //~vakhM~
            }                                                      //~vakaI~
        }                                                          //~va91I~
        rankFixErr+=han;
        rankFixErrMultiWait+=hanMultiWait;                         //~vakhI~
        rc=han==0;                                                 //~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairFirst="+ctrPairFirst+",ctrPairNotFirst="+ctrPairNotFirst);//~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile rc="+rc+",han="+han+",hanMultiWait="+hanMultiWait+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle+",swLastNotFix="+swLastNotFix);//~va91R~//~vakhR~
    	return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //****************************************************************//~vaq0I~
    //*FixFirst chk                                                //~vaq0I~
    //*rc:false fixFirst/FixMiddle err                             //~vaq0I~
    //****************************************************************//~vaq0I~
    private boolean chkEarthHonorTile()	//Wind,Round,WGR           //~vaq0R~
    {                                                              //~vaq0I~
        int ctrPairNotFirst=0,ctrPairFirst=0;                      //~vaq0I~
        boolean swOther=false,swLast=false,swMiddle=false,rc;      //~vaq0I~
        boolean swLastNotFix=false;                                //~vaq0I~
        int ctrWGR;                                                //~vaq0I~
        boolean swTakeOK;                                          //~vaq0I~
        boolean swDouble;                                          //~vaq0I~
        int rankUp=0,rankUpLast=0;                                 //~vaq0R~
    //***************************                                  //~vaq0I~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile pairEarth="+Pair.toString(pairEarth));//~vaq0R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~vaq0R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile longRankOther="+Rank.toStringName(UARDT.longRankOther,true/*printHonor*/));//~vaq0R~
        swDouble=(eswnHonor==roundHonor);                          //~vaq0I~
        Rank rankOther=UARDT.longRankOther;                        //~vaq0I~
        ctrWGR=rankOther.getWGR();                                 //~vaq0I~
        if (!rankOther.isContains(RYAKU_ROUND)                     //~vaq0I~
        &&  !rankOther.isContains(RYAKU_WIND)                      //~vaq0I~
        &&  ctrWGR==0                                              //~vaq0I~
        )                                                          //~vaq0I~
        {                                                          //~vaq0I~
	    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile no honor rank longrRank="+Rank.toStringName(longRank));//~vaq0R~
        	return true;                                           //~vaq0I~
        }                                                          //~vaq0I~
        //*chk earth                                               //~vaq0I~
	    for (Pair pair:pairEarth)                                  //~vaq0I~
        {                                                          //~vaq0I~
        	if (pair==null)                                        //~vaq0I~
            	continue;                                          //~vaq0I~
            if (pair.type==TT_JI && (pair.number>=TT_4ESWN_CTR || pair.number==eswnHonor|| pair.number==roundHonor))//~vaq0I~
            {                                                      //~vaq0I~
            	rankUp=(swDouble && pair.number==eswnHonor) ? 2:1;//~vaq0I~
                if (swOther)                                       //~vaq0I~
                    ctrPairNotFirst+=rankUp;                       //~vaq0R~
                else                                               //~vaq0I~
                    ctrPairFirst+=rankUp;                          //~vaq0R~
            }                                                      //~vaq0I~
            else                                                   //~vaq0I~
                swOther=true;                                      //~vaq0I~
        }                                                          //~vaq0I~
        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile after chkEarth swOther="+swOther+",ctraPairNotFirst="+ctrPairNotFirst+".ctrPairFirst="+ctrPairFirst);//~vaq0R~
        for (Pair pair:pairNotNum)     //earth and hand            //~vaq0I~
        {                                                          //~vaq0I~
	        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile chk hand swHand="+pair.swHand+",pair="+pair);//~vaq0R~
            if (!pair.swHand)    //not hand                        //~vaq0I~
            	continue;                                          //~vaq0I~
            if (!(pair.type==TT_JI && (pair.number>=TT_4ESWN_CTR || pair.number==eswnHonor|| pair.number==roundHonor)))	//not honor meld//~vaq0I~
            	continue;                                          //~vaq0I~
            rankUp=(swDouble && pair.number==eswnHonor) ? 2:1; //~vaq0I~
            boolean swAnko=false;                                  //~vaq0I~
            if (pair.type==ronType && pair.number==ronNumber)	//ron by shanpon//~vaq0I~
            {                                                      //~vaq0I~
                rankUpLast=rankUp;	//ron by honor tile            //~vaq0I~
            	boolean swLastFixed=(typePillow==TT_JI && (numberPillow>=TT_4ESWN_CTR || numberPillow==eswnHonor|| numberPillow==roundHonor));	//pillow is honor tile//~vaq0M~
            	if (!swLastFixed) //shanpon of honor and not honor //~vaq0I~
                {                                                  //~vaq0I~
                	swLastNotFix=true;	//ron is not fixed         //~vaq0M~
	        		if (typeYakuFix!=YAKUFIX_LAST && swTaken)         //~vaq0I~
                    	if (swAllInHand || swYakuFixMultiWaitTakeOK) //allow as anko by take//~vaq0R~
                        {                                          //~vaq0I~
		                	swLastNotFix=false;	//ron fixed        //~vaq0I~
	    	            	ctrPairFirst+=rankUpLast;              //~vaq0R~
                            rankUpLast=0;	//evaluated as ctrPairFirst, not use for err by swLastNotFix//~vaq0I~
                        }                                          //~vaq0I~
                }                                                  //~vaq0I~
                else                                               //~vaq0I~
                {                                                  //~vaq0I~
                	if (swTaken)     //shanpon Honor+Honor,assume anko//~vaq0I~
                    {                                              //~vaq0I~
	    	        	ctrPairFirst+=rankUpLast;                  //~vaq0I~
                    	rankUpLast=0;	//evaluated as ctrPairFirst, not use for err by swLastNotFix//~vaq0I~
                    }                                              //~vaq0I~
	                if (Dump.Y) Dump.println("UARank.chkEarthHonorTile Ron shanpon LastFix="+swLastFixed+",rankUpLast="+rankUpLast+",swLastNotFix="+swLastNotFix);//~vaq0R~
                }                                                  //~vaq0I~
                if (Dump.Y) Dump.println("UARank.chkEarthHonorTile Ron shanpon LastFix="+swLastFixed+",rankUpLast="+rankUpLast+",swLastNotFix="+swLastNotFix);//~vaq0R~
            }                                                      //~vaq0I~
            else                                                   //~vaq0I~
            	ctrPairFirst+=rankUp;                              //~vaq0I~
	        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile swAnko="+swAnko+",swOther="+swOther+",ctrPairNotFirst="+ctrPairNotFirst+",ctrPairFirst="+ctrPairFirst);//~vaq0I~
        }//all meld hand and earth                                 //~vaq0I~
        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile after chkHand ctraPairNotFirst="+ctrPairNotFirst+".ctrPairFirst="+ctrPairFirst);//~vaq0R~
        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile after chkHand swTaken="+swTaken+",swLastNotFix="+swLastNotFix+",swYakuFixMultiWaitTakeOK="+swYakuFixMultiWaitTakeOK);//~vaq0M~
                                                                   //~vaq0I~
        if (typeYakuFix==YAKUFIX_FIRST)                            //~vaq0R~
        {                                                          //~vaq0R~
            rankFixErr+=ctrPairNotFirst;                           //~vaq0R~
            if (swOther || swLastNotFix)   //false if taken allowed//~vaq0R~
            {                                                      //~vaq0I~
            	rankFixErr+=rankUpLast;    //0 if taken            //~vaq0R~
            	if (!swOther)  //false if taken allowed            //~vaq0R~
                	if (ctrPairFirst+ctrPairNotFirst==0)           //~vaq0R~
	                    rankFixErrMultiWait+=rankUpLast;           //~vaq0R~
            }                                                      //~vaq0I~
        }                                                          //~vaq0R~
        else                                                       //~vaq0I~
        if (typeYakuFix==YAKUFIX_MIDDLE)                           //~vaq0R~
        {                                                          //~vaq0R~
            if (swLastNotFix)   //false if taken allowed           //~vaq0R~
            {                                                      //~vaq0R~
            	rankFixErr+=rankUpLast;    //0 if taken            //~vaq0R~
                if (ctrPairFirst+ctrPairNotFirst==0)	//no honor on earth and hand//~vaq0R~
	            	rankFixErrMultiWait+=rankUpLast;               //~vaq0R~
            }                                                      //~vaq0R~
        }                                                          //~vaq0R~
        else    //LAST                                             //~vaq0R~
        {                                                          //~vaq0R~
//            if (swLastNotFix)   //false if taken allowed         //~vaq0R~
//            {                                                    //~vaq0R~
//                rankFixErr+=rankUpLast;                          //~vaq0R~
//                if (ctrPairFirst+ctrPairNotFirst==0)             //~vaq0R~
//                    rankFixErrMultiWait+=rankUpLast;             //~vaq0R~
//            }                                                    //~vaq0R~
            ;//LAST allow All han                                  //~vaq0I~
        }                                                          //~vaq0R~
        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile swOther="+swOther+",rankFixErr="+rankFixErr+",rankFixErrMultiWait="+rankFixErrMultiWait);//~vaq0R~
        rc=rankFixErr==0;                                           //~vaq0R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairFirst="+ctrPairFirst+",ctrPairNotFirst="+ctrPairNotFirst);//~vaq0R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile rankUp="+rankUp+",rankUpLast="+rankUpLast);//~vaq0R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile rc="+rc+",rankFixErr="+rankFixErr+",rankFixErrMultiWait="+rankFixErrMultiWait);//~vaq0I~
    	return rc;                                                 //~vaq0I~
    }                                                              //~vaq0I~
    //****************************************************************//~va11I~
//*rank1                                                           //~va11I~
//    private int chkSameSeq()         //1peiko rank1              //~va11R~
//    {                                                            //~va11R~
//    }                                                            //~va11R~
    //****************************************************************//~va11I~
//*rank2                                                           //~va11I~
    private int chk3SameSeq()        //3shiki                      //~va11R~
    {                                                              //~va11I~
        int rc=0;                                                  //~va11I~
        int num3SameSeq=0;                                          //~va91I~
//      for (int ii=0;ii<sizePairSeqS;ii++)                        //~va11R~//~va7dR~
        for (int ii=0;ii<sizePairSeqS && ii<2;ii++)                //~va7dI~
        {                                                          //~va11I~
        	Pair pair1,pair2;                                      //~va11R~
            pair1=pairNumS[ii];                                    //~va11R~
        	if (pair1.typePair==PT_NUMSEQ)                         //~va11I~
            {                                                      //~va11I~
		        int ctr=0;                                         //~va11I~
                int pair2type=-1,pair2number=-1;                   //~va7dI~
	  	    	for (int jj=ii+1;jj<sizePairSeqS;jj++)             //~va11R~
                {                                                  //~va11I~
		            pair2=pairNumS[jj];                            //~va11R~
		        	if (pair2.typePair==PT_NUMSEQ && pair2.type!=pair1.type && pair2.number==pair1.number)//~va11I~
                    {                                              //~va7dI~
//                  	ctr++;                                     //~va11I~//~va7dR~
                        if (ctr==0)                                //~va7dI~
                        {                                          //~va7dI~
                            ctr=1;                                 //~va7dI~
                            pair2type=pair2.type; pair2number=pair2.number;//~va7dI~
                        }                                          //~va7dI~
                        else                                       //~va7dI~
		        	    if (pair2.typePair==PT_NUMSEQ && pair2.type!=pair2type && pair2.number==pair2number)//~va7dI~
                        {                                          //~va7dI~
                            num3SameSeq=pair2number;               //~va91I~
                            ctr=2;                                 //~va7dI~
                        }                                          //~va7dI~
                    }                                              //~va7dI~
                }                                                  //~va11I~
        		if (ctr==2)                                        //~va11I~
                {                                                  //~va11I~
                	addYaku(RYAKU_3SAMESEQ);                       //~va11I~
        			rc=RANK_3SAMESEQ;                              //~va11I~
                    break;                                         //~va11I~
                }                                                  //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (rc!=0)                                                 //~va11R~
        {                                                          //~va91I~
        	rc-=intNotAllHand;                                     //~va11R~
            if (swChkFix)                                          //~va91R~
	            if (!chkEarth3SameSeq(num3SameSeq))                //~va91R~
                {                                                  //~vakhI~
                	rankFixErr+=rc;                                //~va91I~
                    if (swFixErrMultiWait)                         //~vakhI~
                		rankFixErrMultiWait+=rc;                   //~vakhI~
                }                                                  //~vakhI~
        }                                                          //~va91I~
    	if (Dump.Y) Dump.println("UARank.chk3SameSeq rc="+rc);     //~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va91I~
    //*FixFirst chk                                                //~va91I~
    //*rc:false fixFirst/FixMiddle err                             //~va91I~
    //****************************************************************//~va91I~
    private boolean chkEarth3SameSeq(int Pnum)        //3shiki     //~va91R~
    {                                                              //~va91I~
        int ctrPairNotFirst=0,ctrPairRon=0,pairnum;                //~va91R~
        boolean swNotLast=false,swSeqFix=false,swOther=false,swLast=false,swMiddle=false,rc=true;//~va91R~
        boolean swNearRelated=false;                               //~va91I~
        boolean swLastNotFix=false;                                //~vakaI~
    //***************************                                  //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SameSeq pairEarth="+Pair.toString(pairEarth));//~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SameSeq sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~va91I~
        //*chk earth same pair as ron tile                         //~va91I~
	    for (Pair pair:pairEarth)                                  //~va91I~
        {                                                          //~va91R~
        	if (pair==null)                                        //~va91I~
            	continue;                                          //~va91I~
            pairnum=pair.number;                                    //~va91I~
            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~va91R~
                continue;                                          //~va91R~
            if (pair.typePair==PT_NUMSEQ && pairnum==Pnum)	//related to yaku//~va91R~
            {                                                      //~va91R~
                if (swOther)                                       //~va91R~
                    ctrPairNotFirst++;                             //~va91R~
                if (pair.type==ronType && (ronNumber>=pairnum && ronNumber<pairnum+3))//~va91R~
                	swNotLast=true;    //pair related of ron tile is already on earth, not swLast case//~va91R~
            }                                                      //~va91R~
            else                                                   //~va91R~
                swOther=true;                                      //~va91R~
        }                                                          //~va91R~
        if (!(ronNumber>=Pnum && ronNumber<Pnum+3))      //not related to ron//~va91I~
            swNotLast=true;                                        //~va91I~
        if (!swNotLast)	//pair caontains rontile is not on Earth   //~va91I~
        {                                                          //~va91I~
            //*search dup in hands                                 //~va91I~
            for (int ii=0;ii<sizePairSeqS;ii++)                    //~va91I~
            {                                                      //~va91I~
                Pair pair=pairNumS[ii];                                 //~va91I~
                pairnum=pair.number;                                //~va91R~
                if (pair.typePair==PT_NUMSEQ && pair.type==ronType 	//related to yaku//~va91R~
 				&&  ronNumber>=pairnum && ronNumber<pairnum+3      //related to ron//~va91R~
                && (pair.flag & TDF_CHII)==0)//in hand by flag     //~va91I~
                {                                                  //~va91I~
                	if (pairnum==Pnum)  //meld for yaku            //~va91I~
                    {                                              //~va91I~
                        ctrPairRon++;      //pair related to ron   //~va91R~
                        if (ronNumber==pairnum+1 ||  pairnum==TN1 && ronNumber==TN3 ||  pairnum==TN7 && ronNumber==TN7)//~va91R~
                            swSeqFix=true;    //not ryanmen        //~va91R~
                    }                                              //~va91R~
                    else   //not related to yaku but related to ron tile//~va91I~
                    	swNearRelated=true;                        //~va91I~
                }                                                  //~va91I~
            }                                                      //~va91I~
            if (swNearRelated && ctrPairRon!=0)                    //~va91I~
            	ctrPairRon--;                                      //~va91I~
            if (ctrPairRon==2 || ctrPairRon==0)	//doubled pair or not related ron tile//~va91R~
            	swNotLast=true;                                     //~va91R~
        }                                                          //~va91I~
        if (swNotLast)	//ron tile is not related to yaku          //~va91R~
        {                                                          //~va91I~
        	if (swOther)                                           //~va91I~
	            if (ctrPairNotFirst!=0)  //non related earth pair exist//~va91I~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~va91I~
        }                                                          //~va91I~
        else      //ron tile related yaku                          //~va91I~
        {                                                          //~va91I~
        	if (swSeqFix)    //kanchan etc                         //~va91I~
            {	                                                   //~va91I~
            	if (swOther)                                       //~va91I~
	              if (ctrPairNotFirst!=0)  //related earth pair exist after non related//~vakbI~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~vakbI~
                  else                                             //~vakbI~
                	swLast=true;                                   //~va91I~
            }                                                      //~va91I~
            else	//ryanmen                                      //~va91I~
            {                                                      //~va91I~
                swLast=true;                                       //~va91I~
                swLastNotFix=true;                                 //~vakaI~
            }                                                      //~va91I~
        }                                                          //~va91I~
        rc=setFixErr(RYAKU_3SAMESEQ,swLast,swMiddle);              //~va91I~
        if (!rc && swTaken && swLastNotFix)	//fix err and last related take//~vakaI~
	    	rc=setMultiWaitTake(RYAKU_3SAMESEQ,swOther);	//allow multiwait take by option//~vakaI~
        if (!rc && swLastNotFix)	//last related is ron or not effective take//~vakhI~
	        setFixErrMultiWait(swOther);                           //~vakhI~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SameSeq rc="+rc+",Pnum="+Pnum+",ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairRon="+ctrPairRon+",ctrPairNotFirst="+ctrPairNotFirst+",swOther="+swOther);//~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SameSeq swTaken="+swTaken+",swLastNotFix="+swLastNotFix+",swNotLast="+swNotLast+",swNearRelated="+swNearRelated+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle+",swSeqFix="+swSeqFix);//~va91R~//~vakaR~
    	return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //****************************************************************//~va11I~
    private int chk3Same()           //3tonko, Pon OK              //~va11R~
    {                                                              //~va11I~
    	int numSame=0;                                             //~va91I~
    //*************************                                    //~va91I~
        if (swPinfu)                                               //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chk3Same pinfu rc=0");//~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
        int rc=0;                                                  //~va11I~
//      for (int ii=0;ii<sizePairSeqS;ii++)                        //~va11R~//~va7dR~
        for (int ii=0;ii<sizePairSeqS && ii<2;ii++)                //~va7dI~
        {                                                          //~va11I~
        	Pair pair1,pair2;                                      //~va11R~
            pair1=pairNumS[ii];                                    //~va11R~
            numSame=pair1.number;                                  //~va91I~
        	if (pair1.typePair==PT_NUMSAME)                        //~va11I~
            {                                                      //~va11I~
	        	int ctr=0;                                         //~va11I~
	  	    	for (int jj=ii+1;jj<sizePairSeqS;jj++)             //~va11R~
                {                                                  //~va11I~
		            pair2=pairNumS[jj];                            //~va11R~
		        	if (pair2.typePair==PT_NUMSAME &&  pair2.number==pair1.number)//~va11I~
                    	ctr++;                                     //~va11I~
                }                                                  //~va11I~
                if (ctr==2)                                        //~va11I~
                {                                                  //~va11I~
                	addYaku(RYAKU_3SAMENUM);                       //~va11I~
		        	rc=RANK_3SAMENUM;                              //~va11R~
                    break;                                         //~va11I~
                }                                                  //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (rc!=0)                                                 //~va91I~
        {                                                          //~va91I~
            if (swChkFix)                                          //~va91I~
	            if (!chkEarth3Same(numSame))                       //~va91R~
                {                                                  //~vakhI~
                	rankFixErr+=rc;                                //~va91I~
                    if (swFixErrMultiWait)                            //~vakhI~
                		rankFixErrMultiWait+=rc;                   //~vakhI~
                }                                                  //~vakhI~
        }                                                          //~va91I~
    	if (Dump.Y) Dump.println("UARank.chk3Same rc="+rc+",swMultiWaitErr="+swFixErrMultiWait+",rankFixErr="+rankFixErr+",rankFixerrMultiWait="+rankFixErrMultiWait);        //~va11R~//~vakhR~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va91I~
    //*FixFirst chk                                                //~va91I~
    //*rc:false fixFirst/FixMiddle err                             //~va91I~
    //****************************************************************//~va91I~
    private boolean chkEarth3Same(int Pnum)        //3tonko        //~va91R~
    {                                                              //~va91I~
        int ctrPairNotFirst=0;                                     //~va91R~
        boolean swOther=false,swLast=false,swMiddle=false,rc=true; //~va91R~
    //***************************                                  //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Same pairEarth="+Pair.toString(pairEarth));//~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Same sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~va91I~
	    for (Pair pair:pairEarth)                                  //~va91I~
        {                                                          //~va91R~
        	if (pair==null)                                        //~va91I~
            	continue;                                          //~va91I~
            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~va91R~
                continue;                                          //~va91R~
            if (pair.typePair==PT_NUMSAME && pair.type!=TT_JI && pair.number==Pnum)//~va91R~
            {                                                      //~va91R~
                if (swOther)                                       //~va91R~
                    ctrPairNotFirst++;                             //~va91R~
            }                                                      //~va91R~
            else                                                   //~va91R~
                swOther=true;                                      //~va91R~
        }                                                          //~va91R~
        boolean swLastRelated=(ronType!=TT_JI && ronNumber==Pnum); //ron tile related to yaku//~vakaI~
        if (ctrPairNotFirst!=0)  //non related earth pair exist    //~va91R~
            swMiddle=true;       //OK if YAKUFIX_MIDDLE            //~va91R~
        if (!swMiddle)                                             //~va91I~
            if (ronType!=TT_JI && ronNumber==Pnum) //ron tile related to yaku//~va91I~
            {                                                      //~va91I~
                swLast=true;                                       //~va91I~
            }                                                      //~va91I~
        rc=setFixErr(RYAKU_3SAMENUM,swLast,swMiddle);                 //~va91R~
        if (!rc && swTaken && swLastRelated)	//fix err and last related take//~vakaI~
	    	rc=setMultiWaitTake(RYAKU_3SAMENUM,swOther);	//allow multiwait take by option//~vakaI~//~vakhR~
        if (!rc &&  swLastRelated)	//last related is ron or not effective take//~vakhI~
	        setFixErrMultiWait(swOther);                           //~vakhI~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Same Pnum="+Pnum+",ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairNotFirst="+ctrPairNotFirst);//~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Same rc="+rc+",swLastRelated="+swLastRelated+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle);//~va91R~//~vakaR~
    	return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //****************************************************************//~va11I~
    private int chk3SameHand()       //3anko                       //~va11R~
    {                                                              //~va11R~
        if (swPinfu)                                               //~va11R~
        {                                                          //~va11R~
            if (Dump.Y) Dump.println("UARank.chk3SameHand pinfu rc=0");//~va11R~
            return 0;                                              //~va11R~
        }                                                          //~va11R~
        int rc=0;                                                  //~va11R~
        int ctr=0;                                                  //~va11I~
    	boolean swLast=false,swOther=false;                      //~vakgR~
	    for (Pair pair:pairEarth)                                  //~vakgI~
        {                                                          //~vakgI~
        	if (pair==null)                                        //~vakgI~
            	continue;                                          //~vakgI~
            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~vakgI~
                continue;                                          //~vakgI~
            swOther=true;                                          //~vakgI~
        }                                                          //~vakgI~
        for (Pair pair:pairNumS)                                   //~va11R~
        {                                                          //~va11I~
            if (Dump.Y) Dump.println("UARank.chk3SameHand pair="+Pair.toString(pair));//~va11R~
        	if (pair.typePair==PT_NUMSAME && pair.swHand)	//not earth//~va11R~
            {                                                      //~vakgR~
  	        	if (!(pair.type==ronType && pair.number==ronNumber))//~va11I~
            		ctr++;                                         //~va11R~
                else                                               //~vakgR~
  	        		swLast=true;                                   //~vakgR~
            }                                                      //~vakgR~
        }                                                          //~va11I~
        if (ctrPairNotNum!=0)                                      //~va11I~
            for (Pair pair:pairNotNum)     //earth and hand        //~va11R~
            {                                                      //~va11R~
	            if (Dump.Y) Dump.println("UARank.chk3SameHand pair="+Pair.toString(pair));//~va11I~
	            if (Dump.Y) Dump.println("UARank.chk3SameHand ronType="+ronType+",ronNumber="+ronNumber);//~vaq0R~
                if (pair.swHand)	//not earth                    //~va11R~
                {                                                  //~vakgR~
  		        	if (!(pair.type==ronType && pair.number==ronNumber))//~va11I~
    	                ctr++;                                     //~va11R~
                    else                                           //~vakgR~
	  	        		swLast=true;                               //~vakgR~
                }                                                  //~vakgR~
            }                                                      //~va11R~
        if (ctr==2)      //!FixLast & !(allInHand & taken):(earth or ron)//~vakgR~
        {                                                          //~vakgI~
	  		if (swLast & swTaken)	//last is not taken            //~vakgR~
            {                                                      //~vapzI~
//  			if (isMultiWaitTake(swOther))                      //~vakgR~//~vapzR~
	    		if (swAllInHand || typeYakuFix==YAKUFIX_LAST)      //~vapzI~
	            	ctr++;                                         //~vakgR~
                else                                               //~vapzI~
                {                                                  //~vapzI~
	            	ctr++;                                         //~vapzI~
                	rankFixErr+=RANK_3SAMEHAND;                    //~vapzI~
                }                                                  //~vapzI~
            }                                                      //~vapzI~
        }                                                          //~vakgI~
        if (ctr==3)                                                //~va11I~
        {                                                          //~va11I~
            addYaku(RYAKU_3SAMEHAND);                              //~va11I~
        	rc=RANK_3SAMEHAND;                                     //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARank.chk3SameHand rc="+rc+",ctr="+ctr+",swLast="+swLast+",swTaken="+swTaken+",swOther="+swOther+",swChkFix="+swChkFix);//~va11R~//~vakgR~
        return rc;                                                 //~va11R~
    }                                                              //~va11R~
    //****************************************************************//~va11I~
    private int chkStraight()        //ikkitukan                   //~va11I~
    {                                                              //~va11I~
    	int typeStraight=0;                                        //~va91I~
        if (swTanyao)                                              //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chkStraight tanyao rc=0");//~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
        int rc=0;                                                           //~va11I~
        int wkBit=0;                                               //~va11I~
        for (int ii=0;ii<sizePairSeqS;ii++)                        //~va11R~
        {                                                          //~va11I~
        	Pair pair1,pair2;                                      //~va11R~
            pair1=pairNumS[ii];                                    //~va11R~
        	if (pair1.typePair!=PT_NUMSEQ)                         //~va11I~
            	continue;                                          //~va11I~
            int num1=pair1.number;                                 //~va11I~
            if (num1!=0 && num1!=3 && num1!=6)                     //~va11I~
            	continue;                                          //~va11I~
            typeStraight=pair1.type;                               //~va91I~
//          wkBit|=(1<<num1);                                      //~va25R~
            wkBit=(1<<num1);                                       //~va25I~
            for (int jj=ii+1;jj<sizePairSeqS;jj++)                 //~va11R~
            {                                                      //~va11I~
                pair2=pairNumS[jj];                                //~va11R~
	        	if (pair2.typePair!=PT_NUMSEQ)                     //~va11I~
	            	continue;                                      //~va11I~
	            int num2=pair2.number;                             //~va11I~
	            if (num2!=0 && num2!=3 && num2!=6)                 //~va11I~
     		       	continue;                                      //~va11I~
                if (pair2.type==pair1.type && num2!=num1)          //~va11I~
                {                                                  //~va11I~
                    wkBit|=(1<<num2);                              //~va11R~
                }                                                  //~va11I~
            }                                                      //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chkStraight ii="+ii+",wkBit="+Integer.toHexString(wkBit));//~va25I~
//          if (wkBit==0x07)     //   6   3  0                     //~va25R~
            if (wkBit==0x49)     //B-0100 1001                     //~va25R~
            {                                                      //~va11I~
	            addYaku(RYAKU_STRAIGHT);                           //~va11I~
	        	rc=RANK_STRAIGHT;                                  //~va11I~
                break;                                             //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (rc!=0)                                                 //~va11R~
        {                                                          //~va91I~
        	rc-=intNotAllHand;                                     //~va11R~
            if (swChkFix)                                          //~va91I~
	            if (!chkEarthStraight(typeStraight))               //~va91R~
                {                                                  //~vakhI~
                	rankFixErr+=rc;                                //~va91I~
                    if (swFixErrMultiWait)                         //~vakhI~
                		rankFixErrMultiWait+=rc;                   //~vakhI~
                }                                                  //~vakhI~
        }                                                          //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkStraight rc="+rc+",rankFixErr="+rankFixErr+",rankFixErrMultiWait="+rankFixErrMultiWait+",wkBit="+Integer.toHexString(wkBit));//~va25R~//~vakhR~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va91I~
    //*FixFirst chk                                                //~va91I~
    //*rc:false fixFirst/FixMiddle err                             //~va91I~
    //****************************************************************//~va91I~
    private boolean chkEarthStraight(int Ptype)        //straight(ittsu)//~va91I~
    {                                                              //~va91I~
        int ctrPairNotFirst=0,ctrPairRon=0,pairnum;                //~va91R~
        boolean swNotLast=false,swSeqFix=false,swOther=false,swLast=false,swMiddle=false,rc=true;//~va91I~
        boolean swNearRelated=false;                                     //~va91I~
        boolean swLastNotFix=false;                                //~vakaI~
    //***************************                                  //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight pairEarth="+Pair.toString(pairEarth));//~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~va91I~
        if (ronType!=Ptype)                                        //~va91M~
        	swNotLast=true;    //ron tile is not related to yaku, out of fixLast//~va91I~
        //*chk earth same pair as ron tile                         //~va91I~
	    for (Pair pair:pairEarth)                                  //~va91I~
        {                                                          //~va91I~
        	if (pair==null)                                        //~va91I~
            	continue;                                          //~va91I~
            pairnum=pair.number;                                   //~va91I~
            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~va91I~
                continue;                                          //~va91I~
            if (pair.typePair==PT_NUMSEQ && pair.type==Ptype       //~va91I~
            && (pairnum==TN1 || pairnum==TN4 || pairnum==TN7))     //~va91I~
            {                                                      //~va91I~
                if (swOther)                                       //~va91I~
                    ctrPairNotFirst++;                             //~va91I~
                if (pair.type==ronType && (ronNumber>=pairnum && ronNumber<pairnum+3))//~va91I~
                	swNotLast=true;    //pair related of ron tile is already on earth, not swLast case//~va91I~
            }                                                      //~va91I~
            else                                                   //~va91I~
                swOther=true;                                      //~va91I~
        }                                                          //~va91I~
        if (!swNotLast)	//pair caontains rontile is not on Earth   //~va91I~
        {                                                          //~va91I~
            //*search dup in hands                                 //~va91I~
            for (int ii=0;ii<sizePairSeqS;ii++)                    //~va91I~
            {                                                      //~va91I~
                Pair pair=pairNumS[ii];                                 //~va91I~
                pairnum=pair.number;                               //~va91I~
                if (pair.typePair==PT_NUMSEQ && pair.type==Ptype && pair.type==ronType 	//related to yaku//~va91R~
 				&&  ronNumber>=pairnum && ronNumber<pairnum+3      //related to ron//~va91R~
                && (pair.flag & TDF_CHII)==0)//in hand by flag     //~va91I~
                {                                                  //~va91I~
                  	if (pairnum==TN1 || pairnum==TN4 || pairnum==TN7) //related to yaku//~va91I~
                    {                                              //~va91I~
                		ctrPairRon++;      //pair related to ron   //~va91R~
	                	if (ronNumber==pairnum+1 ||  pairnum==TN1 && ronNumber==TN3 ||  pairnum==TN7 && ronNumber==TN7)//~va91R~
                        	swSeqFix=true;    //not ryanmen        //~va91R~
                    }                                              //~va91I~
                    else   //not related to yaku but related to ron tile//~va91I~
                    	swNearRelated=true;                        //~va91I~
                                                                   //~va91I~
                }                                                  //~va91I~
            }                                                      //~va91I~
    		if (Dump.Y) Dump.println("UARank.chkEarthStraight swNearRelated="+swNearRelated+",ctrPairRon="+ctrPairRon);//~vagrI~
            if (swNearRelated && ctrPairRon!=0)                        //~va91I~
            	ctrPairRon--;                                  //~va91I~
            //**ctrPair==2:double meld,                            //~vagrI~
			//**ctrpair==0:nearRelated & relatedMeld(apply ron to nearrelated and related is in hand)//~vagrI~
            //**           or no related meld in hand(on Earth, it is not last)//~vagrI~
            if (ctrPairRon==2 || ctrPairRon==0)	//doubled pair or not related ron tile//~va91I~
            	swNotLast=true;                                    //~va91I~
        }                                                          //~va91I~
        if (swNotLast)	//ron tile is not related to yaku          //~va91I~
        {                                                          //~va91I~
        	if (swOther)                                           //~va91I~
	            if (ctrPairNotFirst!=0)  //non related earth pair exist//~va91I~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~va91I~
        }                                                          //~va91I~
        else      //ron tile related yaku                          //~va91I~
        {                                                          //~va91I~
        	if (swSeqFix)    //kanchan etc                         //~va91I~
            {                                                      //~va91I~
            	if (swOther)                                       //~va91I~
	              if (ctrPairNotFirst!=0)  //related earth pair exist after non related//~vakbR~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~vakbI~
                  else                                             //~vakbI~
                	swLast=true;                                   //~va91I~
            }                                                      //~va91I~
            else	//ryanmen                                      //~va91I~
            {                                                      //~va91I~
                swLast=true;                                       //~va91I~
                swLastNotFix=true;                                 //~vakaI~
            }                                                      //~va91I~
        }                                                          //~va91I~
        rc=setFixErr(RYAKU_STRAIGHT,swLast,swMiddle);              //~va91I~
        if (!rc && swTaken && swLastNotFix)	//fix err and last related take//~vakaI~
	    	rc=setMultiWaitTake(RYAKU_STRAIGHT,swOther);	//allow multiwait take by option//~vakaI~
        if (!rc && swLastNotFix)	//last related is ron or not effective take//~vakhR~
	        setFixErrMultiWait(swOther);                           //~vakhI~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight rc="+rc+",Ptype="+Ptype+",ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairRon="+ctrPairRon+",ctrPairNotFirst="+ctrPairNotFirst+",swOther="+swOther);//~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight swTaken="+swTaken+",swNotLast="+swNotLast+",swLastNotFix="+swLastNotFix+",swNearRelated="+swNearRelated+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle+",swSeqFix="+swSeqFix);//~va91R~//~vakaR~
    	return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //****************************************************************//~va11I~
    private int chkAllSame()         //toitoi                      //~va11I~
    {                                                              //~va11I~
        if (swPinfu)                                               //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chkAllSame pinfu rc=0");//~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
        int rc=0;                                                  //~va11I~
        int ctr=0;                                                  //~va11I~
        for (Pair pair:pairNumS)                                   //~va11R~
        {                                                          //~va11I~
        	if (pair.typePair==PT_NUMSAME)                         //~va11I~
            	ctr++;                                             //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chkAllSame Num ctr="+ctr+",pair="+Pair.toString(pair));//~va11I~//~vafhR~
        }                                                          //~va11I~
        if (ctrPairNotNum!=0)                                      //~va11I~
            for (Pair pair:pairNotNum)     //earth and hand        //~va11R~
            {                                                      //~va11I~
           		ctr++;                                             //~va11R~
		    	if (Dump.Y) Dump.println("UARank.chkAllSame NotNum ctr="+ctr+",pair="+Pair.toString(pair));//~va11I~//~vafhR~
            }                                                      //~va11I~
        if (ctr==PAIRS_MAX)                                         //~va11R~
        {                                                          //~va11I~
	        addYaku(RYAKU_ALLSAME);                                //~va11I~
        	rc=RANK_ALLSAME;                                       //~va11I~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UARank.chkAllSame rc="+rc);      //~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va11I~
    private int chkTerminalMix()     //honchanta                   //~va11I~
    {                                                              //~va11I~
        if (swTanyao)                                              //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chkTerminalMix tanyao rc=0");//~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
    	int rc=0;                                                  //~va11I~
        int ctr=0;                                                 //~va11I~
//  	if (ctrPairNotNum!=0/*mix*/ || statusPillow==STP_NOTNUM || statusPillow==STP_HONOR)//~va11R~//~vaaQR~
	    if (Dump.Y) Dump.println("UARank.chkTerminalMix statusPillow="+statusPillow+",ctrPairNotNum="+ctrPairNotNum);//~vaaQI~
//  	if (statusPillow!=STP_TANYAO && ctrPairNotNum!=0/*mix*/)   //~vaaQI~//~vafhR~
//  	if (statusPillow==STP_NOTNUM/*eswn*/||statusPillow==STP_HONOR || ctrPairNotNum!=0)//~vafhI~//~vafrR~
    	if (statusPillow!=STP_TANYAO && (statusPillow!=STP_TERMINAL || ctrPairNotNum!=0/*mix*/))//~vafrR~
        {                                                          //~va11I~
        	if (chkTerminalNum())                                  //~va11I~
            {                                                      //~va11I~
	        	addYaku(RYAKU_19SEQMIX);                           //~va11I~
                rc=RANK_19SEQMIX;                                  //~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (rc!=0)                                                 //~va11R~
        {                                                          //~vakfR~
        	rc-=intNotAllHand;                                     //~va11R~
            if (swChkFix)                                          //~vakfR~
	            if (!chkTerminalNumTaken(RYAKU_19SEQMIX))          //~vakfR~
                {                                                  //~vakhI~
                	rankFixErr+=rc;                                //~vakfR~
                	rankFixErrMultiWait+=rc;                       //~vakhI~
                }                                                  //~vakhI~
        }                                                          //~vakfR~
    	if (Dump.Y) Dump.println("UARank.chkTerminalMix rc="+rc+",swTaken="+swTaken);  //~va11R~//~vakfR~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va11I~
    private boolean chkTerminalNum()                                     //~va11I~
    {                                                              //~va11I~
    	boolean rc=true;                                           //~va11I~
        boolean swSeq=false;                                       //~va11I~
        for (Pair pair:pairNumS)                                   //~va11R~
        {                                                          //~va11I~
    		if (Dump.Y) Dump.println("UARank.chkTerminalNum pair="+pair.toString());//~vafhI~
            int num=pair.number;                                   //~va11I~
            if (pair.typePair==PT_NUMSAME)                         //~va11I~
            {                                                      //~va11I~
	    		if (Dump.Y) Dump.println("UARank.chkTerminalNum PT_SAME num="+num);//~vafhI~
                if (num!=0 && num!=8)                         //~va11I~
                {                                                  //~va11I~
                	rc=false;                                      //~va11I~
                    break;                                         //~va11I~
                }                                                  //~va11I~
            }                                                      //~va11I~
            else                                                   //~va11I~
            {                                                      //~va11I~
	    		if (Dump.Y) Dump.println("UARank.chkTerminalNum Not PT_SAME num="+num);//~vafhI~
                if (num!=0 && num!=6)                         //~va11I~
                {                                                  //~va11I~
                	rc=false;                                      //~va11I~
                    break;                                         //~va11I~
                }                                                  //~va11I~
                swSeq=true;                                        //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (!swSeq)	//diff with honro                              //~va11I~
        	rc=false;                                              //~va11I~
    	if (Dump.Y) Dump.println("UARank.chkTerminalNum rc="+rc+",swSeq="+swSeq);//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~vakfR~
    //*multiwait chk and allow takeen by option                    //~vakfR~
    //*rc:false fixFirst err                                       //~vakfR~
    //****************************************************************//~vakfR~
    private boolean chkTerminalNumTaken(int Pyaku) //(mix)chanta   //~vakfR~
    {                                                              //~vakfR~
        boolean swLastNotFix=false,rc=true;                        //~vakfR~
    //***************************                                  //~vakfR~
    	if (Dump.Y) Dump.println("UARank.chkTerminalNumTaken");    //~vakfR~
        for (int ii=0;ii<sizePairSeqS;ii++)                        //~vakfR~
        {                                                          //~vakfR~
            Pair pair=pairNumS[ii];                                //~vakfR~
            int pairnum=pair.number;                               //~vakfR~
            if (pair.typePair==PT_NUMSEQ                           //~vakfR~
            &&  ronNumber>=pairnum && ronNumber<pairnum+3      //related to ron//~vakfR~
            && (pair.flag & TDF_CHII)==0)//in hand by flag         //~vakfR~
            {                                                      //~vakfR~
                if (ronNumber==TN1 || ronNumber==TN9)    //ryanmen //~vakfR~
                    swLastNotFix=true;                             //~vakfR~
            }                                                      //~vakfR~
        }                                                          //~vakfR~
        if (swLastNotFix)	//ron tile ryanmen                     //~vakfR~
        {                                                          //~vakfR~
	        rc=setFixErr(Pyaku,true/*swLast*/,false/*swMiddle*/);  //false if fix err//~vakfR~
        	if (!rc && swTaken)	//fix err and last related take    //~vakfR~
	    		rc=setMultiWaitTake(Pyaku,false/*swOther*/);	//allow multiwait take by option//~vakfR~
        }                                                          //~vakfR~
    	if (Dump.Y) Dump.println("UARank.chkTerminalNumTaken rc="+rc+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix);//~vakfR~
    	if (Dump.Y) Dump.println("UARank.chkTerminalNumTaken swTaken="+swTaken+",swLastNotFix="+swLastNotFix);//~vakfR~
    	return rc;                                                 //~vakfR~
    }                                                              //~vakfR~
    //***********************************                          //~va11I~
    private int chk3Kan()            //3kan                       //~va11I~
    {                                                              //~va11I~
        if (swPinfu)                                               //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chk3Kan pinfu rc=0"); //~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
        int rc=0;                                                  //~va11I~
        int ctr=0;                                                  //~va11I~
        for (Pair pair:pairNumS)                                   //~va11R~
        {                                                          //~va11I~
        	if (pair.typePair==PT_NUMSAME && pair.dupCtr==4)       //~va11I~
            	ctr++;                                             //~va11I~
        }                                                          //~va11I~
        if (ctrPairNotNum!=0)                                      //~va11I~
            for (Pair pair:pairNotNum)     //earth and hand        //~va11R~
            {                                                      //~va11R~
                if (pair.dupCtr==4)                                //~va11R~
                    ctr++;                                         //~va11R~
            }                                                      //~va11R~
        if (ctr==3)                                                //~va11I~
        {                                                          //~va11I~
	        addYaku(RYAKU_3KAN);                                   //~va11I~
        	rc=RANK_3KAN;                                          //~va11I~
            if (swChkFix)                                          //~va91I~
	            if (!chkEarth3Kan())                               //~va91R~
                	rankFixErr+=rc;                                //~va91I~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UARank.chk3Kan rc="+rc);         //~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va91I~
    //*FixFirst chk                                                //~va91I~
    //*rc:false fixFirst/FixMiddle err                             //~va91I~
    //****************************************************************//~va91I~
    private boolean chkEarth3Kan()        //3kan                   //~va91I~
    {                                                              //~va91I~
        int ctrPairNotFirst=0;                                     //~va91R~
        boolean swOther=false,swLast=false,swMiddle=false,rc=true; //~va91R~
    //***************************                                  //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Kan pairEarth="+Pair.toString(pairEarth));//~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Kan sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~va91R~
	    for (Pair pair:pairEarth)                                  //~va91I~
        {                                                          //~va91R~
        	if (pair==null)                                        //~va91I~
            	continue;                                          //~va91I~
            if (pair.typePair==PT_NUMSAME && (pair.flag & (TDF_KAN_TAKEN|TDF_KAN_RIVER|TDF_KAN_ADD))!=0)//~va91R~
            {                                                      //~va91R~
                if (swOther)                                       //~va91R~
                    ctrPairNotFirst++;                             //~va91R~
                                                                   //~va91R~
            }                                                      //~va91R~
            else                                                   //~va91R~
                swOther=true;                                      //~va91R~
        }                                                          //~va91R~
        if (ctrPairNotFirst!=0)                                 //~va91I~
            swMiddle=true;                                         //~va91I~
        rc=setFixErr(RYAKU_3KAN,swLast,swMiddle);                  //~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Kan ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairNotFirst="+ctrPairNotFirst);//~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Kan rc="+rc+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle);//~va91R~
    	return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //****************************************************************//~va11I~
//*rank3                                                           //~va11I~
    private int chkSameSeq()        //1/2peiko                     //~va11R~
    {                                                              //~va11I~
    	if (intNotAllHand!=0)                                      //~va11I~
        	return 0;                                              //~va11I~
    	int rc=0;                                                  //~va11R~
        int ctrSameSeq=0;                                          //~va11I~
        int numSameSeq=-1;                                         //~vapyI~
        int typeSameSeq=-1;                                        //~vapyI~
        for (int ii=0;ii<sizePairSeqS;ii++)                        //~va11R~
        {                                                          //~va11I~
        	Pair pair1,pair2;                                      //~va11R~
            pair1=pairNumS[ii];                                    //~va11R~
        	if (pair1.type<PIECE_NUMBERTYPECTR && pair1.typePair==PT_NUMSEQ)//~va11I~
	  	    	for (int jj=ii+1;jj<sizePairSeqS;jj++)             //~va11R~
                {                                                  //~va11I~
		            pair2=pairNumS[jj];                            //~va11R~
		        	if (pair2.typePair==PT_NUMSEQ && pair2.type==pair1.type && pair2.number==pair1.number)//~va11R~
                    {                                              //~vapyI~
                    	ctrSameSeq++;                               //~va11I~
                        numSameSeq=pair1.number;                   //~vapyI~
                        typeSameSeq=pair1.type;                    //~vapyI~
                    }                                              //~vapyI~
                }                                                  //~va11I~
        }                                                          //~va11I~
        int yaku=0;                                                //~va11I~
        if (ctrSameSeq==2)                                         //~va11I~
        {                                                          //~va11I~
	        yaku=RYAKU_2SAMESEQ;                                   //~va11R~
        	rc=RANK_2SAMESEQ;                                      //~va11R~
        }                                                          //~va11I~
        else                                                       //~va11I~
        if (ctrSameSeq!=0)                                         //~va11I~
        {                                                          //~va11I~
	        yaku=RYAKU_1SAMESEQ;                                   //~va11R~
        	rc=RANK_1SAMESEQ;                                      //~va11R~
        }                                                          //~va11I~
        if (rc!=0)                                                    //~va11I~
        {                                                          //~vapyI~
	        addYaku(yaku);                                         //~va11I~
            if (!chkEarthSameSeq(yaku,typeSameSeq,numSameSeq))    //~vapyI~
            {                                                      //~vapyI~
                rankFixErr+=rc;                                    //~vapyI~
                if (swFixErrMultiWait)                             //~vapyI~
                    rankFixErrMultiWait+=rc;                       //~vapyI~
            }                                                      //~vapyI~
        }                                                          //~vapyI~
    	if (Dump.Y) Dump.println("UARank.chkSameSeq rc="+rc+",yaku="+yaku);//~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~vapyI~
    //*FixFirst chk                                                //~vapyI~
    //*rc:false fixFirst/FixMiddle err                             //~vapyI~
    //****************************************************************//~vapyI~
    private boolean chkEarthSameSeq(int Pyaku,int Ptype,int Pnum)  //~vapyI~
    {                                                              //~vapyI~
        int /*ctrPairNotFirst=0,*/ctrPairRon=0,pairnum;            //~vapyI~
        boolean swNotLast=false,swSeqFix=false,swOther=false,swLast=false,swMiddle=false,rc=true;//~vapyI~
        boolean swNearRelated=false;                               //~vapyI~
        boolean swLastNotFix=false;                                //~vapyI~
    //***************************                                  //~vapyI~
    	if (Dump.Y) Dump.println("UARank.chkEarthSameSeq yaku="+Pyaku+",type="+Ptype+",num="+Pnum);//~vapyI~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~vapyI~
//        //*chk earth same pair as ron tile                       //~vapyI~
//        for (Pair pair:pairEarth)                                //~vapyI~
//        {                                                        //~vapyI~
//            if (pair==null)                                      //~vapyI~
//                continue;                                        //~vapyI~
//            pairnum=pair.number;                                 //~vapyI~
//            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~vapyI~
//                continue;                                        //~vapyI~
//            if (pair.typePair==PT_NUMSEQ && pairnum==Pnum)  //related to yaku//~vapyI~
//            {                                                    //~vapyI~
//                if (swOther)                                     //~vapyI~
//                    ctrPairNotFirst++;                           //~vapyI~
//                if (pair.type==ronType && (ronNumber>=pairnum && ronNumber<pairnum+3))//~vapyI~
//                    swNotLast=true;    //pair related of ron tile is already on earth, not swLast case//~vapyI~
//            }                                                    //~vapyI~
//            else                                                 //~vapyI~
//                swOther=true;                                    //~vapyI~
//        }                                                        //~vapyI~
        if (!(ronNumber>=Pnum && ronNumber<Pnum+3))      //not related to ron//~vapyI~
            swNotLast=true;                                        //~vapyI~
        if (!swNotLast)	//pair contains rontile is not on Earth    //~vapyI~
        {                                                          //~vapyI~
            //*search dup in hands                                 //~vapyI~
            for (int ii=0;ii<sizePairSeqS;ii++)                    //~vapyI~
            {                                                      //~vapyI~
                Pair pair=pairNumS[ii];                            //~vapyI~
                pairnum=pair.number;                               //~vapyI~
                if (pair.typePair==PT_NUMSEQ && pair.type==ronType 	//related to yaku//~vapyI~
 				&&  ronNumber>=pairnum && ronNumber<pairnum+3      //related to ron//~vapyI~
//              && (pair.flag & TDF_CHII)==0)//in hand by flag //assumption of all in hand//~vapyI~
                )                                                  //~vapyI~
                {                                                  //~vapyI~
                	if (pairnum==Pnum)  //meld for yaku is ron meld//~vapyI~
                    {                                              //~vapyI~
                        ctrPairRon++;      //pair related to ron   //~vapyI~
                        if (ronNumber==pairnum+1 ||  pairnum==TN1 && ronNumber==TN3 ||  pairnum==TN7 && ronNumber==TN7)//~vapyI~
                            swSeqFix=true;    //not ryanmen        //~vapyI~
                    }                                              //~vapyI~
                    else   //not related to yaku but related to ron tile//~vapyI~
                    	swNearRelated=true;                        //~vapyI~
                }                                                  //~vapyI~
            }                                                      //~vapyI~
            if (swNearRelated && ctrPairRon!=0)                    //~vapyI~
            	ctrPairRon--;                                      //~vapyI~
            if (Pyaku==RYAKU_1SAMESEQ)                             //~vapyI~
            {                                                      //~vapyI~
	            if (ctrPairRon==3 || ctrPairRon==0)	//doubled pair or not related ron tile//~vapyI~
	            	swNotLast=true;                                //~vapyI~
            }                                                      //~vapyI~
            else    //2SameSeq                                     //~vapyI~
	            if (ctrPairRon==0)	//doubled pair or not related ron tile//~vapyI~
	            	swNotLast=true;                                //~vapyI~
        }                                                          //~vapyI~
        if (swNotLast)	//ron tile is not related to yaku          //~vapyI~
        {                                                          //~vapyI~
//      	if (swOther)	//other on earth                       //~vapyI~
//              if (ctrPairNotFirst!=0)  //non related earth pair exist//~vapyI~
//  	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~vapyI~
            ;                                                      //~vapyI~
        }                                                          //~vapyI~
        else      //ron tile related yaku                          //~vapyI~
        {                                                          //~vapyI~
        	if (swSeqFix)    //kanchan etc                         //~vapyI~
            {                                                      //~vapyI~
//          	if (swOther)                                       //~vapyI~
//                if (ctrPairNotFirst!=0)  //related earth pair exist after non related//~vapyI~
//  	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~vapyI~
//                else                                             //~vapyI~
//              	swLast=true;                                   //~vapyI~
        	    ;                                                  //~vapyI~
            }                                                      //~vapyI~
            else	//ryanmen                                      //~vapyI~
            {                                                      //~vapyI~
                swLast=true;                                       //~vapyI~
                swLastNotFix=true;                                 //~vapyI~
            }                                                      //~vapyI~
        }                                                          //~vapyI~
        rc=setFixErr(Pyaku,swLast,swMiddle);                       //~vapyI~
        if (!rc && swTaken && swLastNotFix)	//fix err and last related take//~vapyI~
	    	rc=setMultiWaitTake(Pyaku,swOther);	//allow multiwait take by option//~vapyI~
        if (!rc && swLastNotFix)	//last related is ron or not effective take//~vapyI~
	        setFixErrMultiWait(swOther);                           //~vapyI~
    	if (Dump.Y) Dump.println("UARank.chkEarthSameSeq rc="+rc+",Pnum="+Pnum+",ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairRon="+ctrPairRon+",swOther="+swOther);//~vapyI~
    	if (Dump.Y) Dump.println("UARank.chkEarthSameSeq swTaken="+swTaken+",swLastNotFix="+swLastNotFix+",swNotLast="+swNotLast+",swNearRelated="+swNearRelated+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle+",swSeqFix="+swSeqFix);//~vapyI~
    	return rc;                                                 //~vapyI~
    }                                                              //~vapyI~
    //****************************************************************//~vaaQI~
    private int chkTerminal()        //junchanta                   //~va11I~
    {                                                              //~va11I~
        if (swTanyao)                                              //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chkTerminal tanyao rc=0");//~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
    	int rc=0;                                                  //~va11R~
        int ctr=0;                                                 //~va11I~
    	if (Dump.Y) Dump.println("UARank.chkTerminal ctrPairNotNum="+ctrPairNotNum+",statusPillow="+statusPillow);//~vafhI~
    	if (ctrPairNotNum==0 && statusPillow==STP_TERMINAL)        //~va11R~
        {                                                          //~va11I~
        	if (chkTerminalNum())                                  //~va11I~
            {                                                      //~va11I~
	        	addYaku(RYAKU_19SEQ);                               //~va11I~
                rc=RANK_19SEQ;                                     //~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (rc!=0)                                                 //~va11R~
        {                                                          //~vakfI~
        	rc-=intNotAllHand;                                     //~va11R~
            if (swChkFix)                                          //~vakfI~
	            if (!chkTerminalNumTaken(RYAKU_19SEQ))             //~vakfI~
                {                                                  //~vakhI~
                	rankFixErr+=rc;                                //~vakfI~
		        	rankFixErrMultiWait+=rc;                       //~vakhI~
                }                                                  //~vakhI~
        }                                                          //~vakfI~
    	if (Dump.Y) Dump.println("UARank.chkTerminal rc="+rc);     //~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    private int chkFlushMix()        //honitsu                     //~va11I~
    {                                                              //~va11I~
	    if (Dump.Y) Dump.println("UARank.chkFlushMix entry swTanyao="+swTanyao+",statusPillow="+statusPillow+",ctrPairNotNum="+ctrPairNotNum);//~vafrI~
        if (swTanyao)                                              //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chkFlushMix tanyao rc=0");//~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
        int rc=0;                                                  //~va11I~
    	if (ctrPairNotNum!=0 || statusPillow==STP_NOTNUM || statusPillow==STP_HONOR)//~va11R~
        {                                                          //~va11I~
        	if (chkFlushNum(true/*mixed*/))                        //~va11R~
            {                                                      //~va11I~
	        	addYaku(RYAKU_FLASHMIX);                           //~va11I~
                rc=RANK_FLASHMIX;                                  //~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (rc!=0)                                                 //~va11R~
        	rc-=intNotAllHand;                                     //~va11R~
    	if (Dump.Y) Dump.println("UARank.chkFlushMix rc="+rc);     //~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //***********************************                          //~va11I~
    private boolean chkFlushNum(boolean PswMix)                    //~va11R~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UARank.chkFlushNum swMix="+PswMix+",typePillow="+typePillow);//~vafrI~
    	boolean rc=true;                                           //~va11I~
        int type;                                                  //~va11R~
        if (PswMix)                                                //~va11I~
        {                                                          //~va26I~
        	if (typePillow==TT_JI)                                 //~va26I~
	        	type=-1;                                               //~va11I~//~va26R~
            else                                                   //~va26I~
	        	type=typePillow;                                   //~va26I~
        }                                                          //~va26I~
        else                                                       //~va11I~
        	type=typePillow;                                       //~va11I~
        if (Dump.Y) Dump.println("UARank.chkFlushNum pairNumS="+Pair.toString(pairNumS));//~vafrI~
        for (Pair pair:pairNumS)                                   //~va11R~
        {                                                          //~va11I~
    		if (Dump.Y) Dump.println("UARank.chkFlushNum pair.type="+pair.type+",pair="+pair.toString());//~vafrI~
        	if (type<0)                                            //~va11I~
            {                                                      //~va11I~
            	type=pair.type;                                    //~va11I~
            	continue;                                          //~va11I~
            }                                                      //~va11I~
            if (pair.type!=type)                                   //~va11I~
            {                                                      //~va11I~
                rc=false;                                          //~va11I~
                break;                                             //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UARank.chkFlushNum rc="+rc+",swMix="+PswMix);//~va11R~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va11I~
//*rank4                                                           //~va11I~
    private int chk3DragonSmall()    //shosangen                   //~va11I~
    {                                                              //~va11I~
        if (swTanyao)                                              //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chk3DragonSmall tanyao rc=0");//~va11I~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
        if (swPinfu)                                               //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chk3DragonSmall pinfu rc=0");//~va11I~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
    	int rc=0;                                                  //~va11I~
        int ctrDragon=0;                                           //~va11I~
        if (ctrPairNotNum!=0)                                      //~va11I~
            for (Pair pair:pairNotNum)                            //~va11I~
            {                                                      //~va11I~
    			if (Dump.Y) Dump.println("UARank.chk3DragonSmall pairNotNum="+pair);//~vaq0R~
                if (pair.type==TT_JI && pair.number>=TT_4ESWN_CTR) //~va11R~
                    ctrDragon++;                                   //~va11I~
            }                                                      //~va11I~
        if (ctrDragon==2)                                          //~va11I~
        {                                                          //~va11I~
        	if (typePillow==TT_JI && numberPillow>TT_4ESWN_CTR)    //~va11I~
            {                                                      //~va11I~
	        	addYaku(RYAKU_3DRAGONSMALL);                       //~va11I~
        		rc=RANK_3DRAGONSMALL;                              //~va11I~
//              if (swChkFix)                                      //~va91I~//~vakdR~
//              	if (!chkEarth3DragonSmall())                   //~va91R~//~vakdR~
//                  	rankFixErr+=rc;                            //~va91I~//~vakdR~
            }                                                      //~va11I~
    	}                                                          //~va11I~
    	if (Dump.Y) Dump.println("UARank.chk3DragonSmall rc="+rc+",ctrDragon="+ctrDragon+",ctrPairNotNum="+ctrPairNotNum);//~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va91I~
    //*FixFirst chk                                                //~va91I~
    //*rc:false fixFirst/FixMiddle err                             //~va91I~
    //****************************************************************//~va91I~
    private boolean chkEarth3DragonSmall()        //sho-sangen     //~va91I~
    {                                                              //~va91I~
        int ctrPairNotFirst=0;                                     //~va91I~
        boolean swOther=false,swLast=false,swMiddle=false,rc=true; //~va91I~
    //***************************                                  //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall pairEarth="+Pair.toString(pairEarth));//~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~va91I~
	    for (Pair pair:pairEarth)                                  //~va91I~
        {                                                          //~va91R~
        	if (pair==null)                                        //~va91I~
            	continue;                                          //~va91I~
            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~va91R~
                continue;                                          //~va91R~
            if (pair.typePair==PT_NUMSAME && pair.type==TT_JI && pair.number>=TT_4ESWN_CTR)//~va91R~
            {                                                      //~va91R~
                if (swOther)                                       //~va91R~
                    ctrPairNotFirst++;                             //~va91R~
                                                                   //~va91R~
            }                                                      //~va91R~
            else                                                   //~va91R~
                swOther=true;                                      //~va91R~
        }                                                          //~va91R~
        if (ctrPairNotFirst!=0)  //non related earth pair exist    //~va91R~
            swMiddle=true;       //OK if YAKUFIX_MIDDLE            //~va91R~
        if (!swMiddle)                                             //~va91I~
            if (ronType==TT_JI && ronNumber>=TT_4ESWN_CTR)  //ron tile related to yaku//~va91R~
            {                                                      //~va91R~
                if (!(ronNumber==numberPillow && !swOther))    //fixed at last if ron tile=pillow//~va91I~
                    swLast=true;                                   //~va91R~
            }                                                      //~va91R~
        rc=setFixErr(RYAKU_3DRAGONSMALL,swLast,swMiddle);          //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall ronType="+ronType+",ronNumber="+ronNumber+",typePillow="+typePillow+",numberPilloe="+numberPillow+",typeYakuFix="+typeYakuFix+",ctrPairNotFirst="+ctrPairNotFirst);//~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall rc="+rc+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle);//~va91I~
    	return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //**********************************************************   //~va11I~
    //*chinroutou is aready checked previously                     //~va11I~
    //**********************************************************   //~va11I~
    private int chkTerminalOnlyMix() //honro  Pon ok               //~va11R~
    {                                                              //~va11I~
        if (swTanyao)                                              //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chKTerminalOnlyMix tanyao rc=0");//~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
        if (swPinfu)                                               //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chkTerminalOnlyMix pinfu rc=0");//~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
        if (statusPillow==STP_TANYAO)   //notnum,honor,terminal    //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chkTerminalOnlyMix statusPillow="+statusPillow);//~va11I~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
        int rc=0;                                                  //~va11I~
        boolean swTerminal=true;                                   //~va11R~
        for (Pair pair:pairNumS)                                   //~va11R~
        {                                                          //~va11R~
            int num=pair.number;                                   //~va11R~
            if (pair.typePair!=PT_NUMSAME                          //~va11R~
            ||  (num!=0 && num!=8))                                //~va11R~
            {                                                      //~va11R~
                swTerminal=false;                                  //~va11R~
                break;                                             //~va11R~
            }                                                      //~va11R~
        }                                                          //~va11R~
        if (swTerminal)                                            //~va11R~
        {                                                          //~va11I~
	        addYaku(RYAKU_19SAMEMIX);                              //~va11I~
            rc=RANK_19SAMEMIX;                                     //~va11R~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UARank.chkTerminalOnlyMix rc="+rc);//~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
//*rank6                                                           //~va11I~
    //**********************************************************   //~va11I~
    private int chkFlush()			//chinitsu                     //~va11I~
    {                                                              //~va11I~
        int rc=0;                                                  //~va11I~
	    if (Dump.Y) Dump.println("UARank.chkFlush entry statusPillow="+statusPillow+",ctrPairNotNum="+ctrPairNotNum);//~vafrI~
    	if (ctrPairNotNum==0)                                      //~va11R~
        {                                                          //~va11I~
        	if (chkFlushNum(false/*mixed*/))                       //~va11R~
            {                                                      //~va11I~
		        addYaku(RYAKU_FLASH);                              //~va11I~
                rc=RANK_FLASH;                                     //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (rc!=0)                                                 //~va11R~
        	rc-=intNotAllHand;                                     //~va11R~
    	if (Dump.Y) Dump.println("UARank.chkFlush rc="+rc);        //~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    public int setRank7Pair(Rank PlongRank7)                       //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARank.getRank7Pair");           //~va11I~
        dupCtr=UARV.dupCtr;                                        //~va11R~
        longRank7=new Rank();                                      //~va11R~
    	int rank=0;                                                //~va11I~
//        rank+=chkSameSeq();         //1peiko  by chkSameSeq2     //~va11R~
    //rank2                                                        //~va11I~
//        rank+=chk3SameSeq();        //3shoku                     //~va11R~
//        rank+=chk3Same();           //3tonko                     //~va11R~
//        rank+=chk3SameHand();       //3anko                      //~va11R~
//        rank+=chkStraight();        //ikkitukan                  //~va11R~
//        rank+=chkAllSame();         //toitoi                     //~va11R~
//        rank+=chkTerminalMix7();     //honchanta,chk as standard //~va11R~
//        rank+=chk3Kan();            //3kan                       //~va11R~
    //rank3                                                        //~va11I~
//        rank+=chkSameSeq();        //1/2peiko                    //~va11R~
//        rank+=chkTerminal7();        //junchanta,chk as standard //~va11R~
//      rank+=chkFlushMix7();        //honitsu                     //~va11R~
        int rcFlush=chkFlushNum7();                                //~va11I~
        if (rcFlush==1) //mix                                      //~va11I~
        	rank+=chkFlushMix7();        //honitsu                 //~va11I~
    //rank4                                                        //~va11I~
//        rank+=chk3DragonSmall();    //shosangen                  //~va11R~
        rank+=chkTerminalOnlyMix7(); //honro                       //~va11I~
    //rank6                                                        //~va11I~
        if (rcFlush==2) //mix                                      //~va11I~
	        rank+=chkFlush7();           //chinitsu                //~va11R~
        PlongRank7.mix(longRank7);                                 //~va11R~
        if (Dump.Y) Dump.println("UARank.setRank7Pair rc="+rank+",longRank7="+longRank7.toStringName());//~va11R~
        return rank;                                               //~va11I~
    }                                                              //~va11I~
    //**********************************************************   //~va11I~
    private int chkFlushMix7()        //honitsu                    //~va11I~
    {                                                              //~va11I~
	    addYaku7(RYAKU_FLASHMIX);                                  //~va11R~
        int rc=RANK_FLASHMIX;                                      //~va11R~
        rc-=intNotAllHand;                                         //~va11R~
    	if (Dump.Y) Dump.println("UARank.chkFlushMix7 rc="+rc);    //~va11I~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //**********************************************************   //~va11I~
    private int chkFlush7()        //chinitsu                      //~va11I~
    {                                                              //~va11I~
		addYaku7(RYAKU_FLASH);                                     //~va11R~
        int rc=RANK_FLASH;                                         //~va11R~
        rc-=intNotAllHand;                                         //~va11R~
    	if (Dump.Y) Dump.println("UARank.chkFlush7 rc="+rc);       //~va11I~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //***********************************                          //~va11I~
    //*rc:1:mix,2:all,0:none                                       //~va11I~
    //***********************************                          //~va11I~
    private int chkFlushNum7()                                     //~va11R~
    {                                                              //~va11I~
    	int rc=2; 	//full                                         //~va11R~
        int type=-1;                                               //~va11I~
        for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                 //~va11I~
        {                                                          //~va11I~
	        for (int jj=0;jj<PIECE_NUMBERCTR;jj++)                 //~va11I~
            {                                                      //~va11I~
	        	if (dupCtr[ii][jj]!=0)                             //~va11I~
            	{                                                  //~va11I~
                    if (type==-1)                                  //~va11I~
                    {                                              //~va11I~
                        type=ii;                                   //~va11I~
                        continue;                                  //~va11I~
                    }                                              //~va11I~
            		if (ii!=type)                                  //~va11I~
                    {                                              //~va11I~
                    	rc=0;                                      //~va11I~
            			break;                                     //~va11I~
                    }                                              //~va11I~
            	}                                                  //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (rc!=0)                                                 //~va11R~
        {                                                          //~va11I~
	        for (int jj=0;jj<PIECE_NOTNUM_CTR;jj++)                //~va11R~
            {                                                      //~va11I~
	        	if (dupCtr[TT_JI][jj]!=0)                          //~va11R~
            	{                                                  //~va11I~
                	rc=1;                                          //~va11R~
            		break;                                         //~va11R~
            	}                                                  //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UARank.chkFlushNum7 rc="+rc);    //~va11R~
    	if (Dump.Y) Dump.println("UARank.chkFlushNum7 dupCtr="+Utils.toString(dupCtr));//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va11I~
    private int chkTerminalOnlyMix7()        //honro               //~va11I~
    {                                                              //~va11I~
    	int rc=0;                                                  //~va11I~
    	if (chkAll19Mix7())                                        //~va11I~
        {                                                          //~va11I~
	        addYaku7(RYAKU_19SAMEMIX);                             //~va11I~
            rc=RANK_19SAMEMIX;                                     //~va11I~
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UARank.chkTerminalOnlyMix7 rc="+rc);//~va11I~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
	//*chiro is chked already                                      //~va11I~
	//*************************************************************************//~va11I~
    private boolean chkAll19Mix7()                                 //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonValue.chkAll19 swTanyao="+swTanyao);//~va11I~
    	boolean rc=true;                                           //~va11I~
        for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)                 //~va11I~
	        for (int jj=1;jj<PIECE_NUMBERCTR-1;jj++)               //~va11I~
	        	if (dupCtr[ii][jj]!=0)                             //~va11R~
            	{                                                  //~va11I~
            		rc=false;                                      //~va11I~
            		break;                                         //~va11I~
            	}                                                  //~va11I~
        if (Dump.Y) Dump.println("UARank.chkAll19Mix7 rc="+rc);    //~va11I~
    	if (Dump.Y) Dump.println("UARank.chkAll19Mix7 dupCtr="+Utils.toString(dupCtr));//~va11I~
        return rc;                                                 //~va11I~
    }                                                              //~va11I~
}//class                                                           //~v@@@R~
