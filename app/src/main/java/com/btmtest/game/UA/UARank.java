//*CID://+vax6R~: update#= 1196;                                   //~vax6R~
//**********************************************************************//~v101I~
//2023/02/22 vax6 (Bug)single; accept ankan as furo                //~vax6I~
//2023/02/22 vax5 (Bug)3seqnum(3renpon) miss anko when seq Meld overwrap//~vax5I~
//2023/02/22 vax4 (Bug)3same(3tonko) miss anko when seq Meld overwrap//~vax4I~
//2023/02/22 vax3 (Bug)3sameHand(3Anko) miss anko when seq Meld overwrap//~vax3I~
//2023/02/22 vax2 (Bug) straight3, isStraight3Pair should chk start number of meld//~vax2I~
//2023/02/22 vax1 add local 3DupSeq(Pure Triple Chow)              //~vax1I~
//2023/02/22 vax0 add local 3Wind:2han                             //~vax0I~
//2023/02/22 vawz 3WindNoHonor; optionally 3/2 han allow RYAKU_ROUND//~vawzI~
//2023/02/22 vawy (Bug)3WindNoHonor,ROUND and Wind is on RankOther //~vawyI~
//2023/02/21 vawv add local yaku. 3SeqNum                          //~vawvI~
//2023/02/19 vawu MultiwaitTakeOK option have not to apply to when 1st earth is not related. Take is regarded as Fixed.//~vawuI~
//                Last fix is not OK for not FIX_LAST option if related is not on earth.//~vawuI~
//2023/02/19 vawt 3dragonsmall; consider shanpon of wgr is already in hand, tanki of wgr is fixed last//~vawtI~
//2023/02/17 vawr assume shanpon of Honor+Honor as fixed if 1st earth is not related//~vawrI~
//2023/02/16 vawq (Bug) MultiwaitTakeOK option was not applied if non related on Earth when FIRST option//~vawqI~
//2023/02/16 vawp (Bug)for 3anko,multiwait option was not applied for FIX_FIRST/FIX_MIDDLE option//~vawpI~
//2023/02/16 vawo (Bug)apply to 3Dragon small that 1st earth is not related, assume fixwait is fixed last.//~vawoI~
//2023/02/16 vawn (Bug)3Dragon small was not evaluated when pillow is Write(num==4)//~vawnI~
//2023/02/16 vawm FIX_FIRST:allow 2 honor tile shanpon if menzen   //~vawmI~
//2023/02/16 vawk FIX_FIRST:allow norelated on earth after related.//~vawkI~//~vawmR~
//2023/02/10 vawg add local yaku. 3ColorStraight                   //~vawgI~
//2023/02/11 vawf (Bug)honor tile multierr is not set              //~vawfI~
//2023/02/11 vawe (Bug)honor tile fix chk err; First on earth is assumed as fixerr//~vaweI~
//2023/02/11 vawd (Bug)3dragonsmall is not evaluated if no honor tile ok when fix err by not last option, so fixchk required.//~vawdI~
//2023/02/10 vawb (Bug)no yaku is evaluated when pairNumS=null(pillow==num and other is all ESWNWGR);  miss toitoi, 3anko, 3WindNoHonor, tanki, honro, honitsu//~vawbI~
//2023/02/10 vawa (Bug)3samenum(3tonko) for fix middle             //~vawaI~
//2023/02/10 vaw9 add local yaku. 3Wind-NoHonor                    //~vaw9I~
//2023/02/10 vaw8 add local yaku. SINGLE                           //~vaw8I~
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

import android.graphics.Point;

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
    private boolean swNewChkHonorTile=false;	//use old to keep consistency//~vawfR~
    private boolean swForFixFirst_allowNonRelatedAfterRelated=true;//~vawkI~
    private boolean swBugFixTakeOK=true;                           //~vawqI~
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
//      swYakuFixMultiWaitTakeOK= AG.aRoundStat.swYakuFixLastMultiWaitTakeOK;//~vakaI~//~vaw9R~
        swYakuFixMultiWaitTakeOK=RuleSettingYaku.isYakuFixMultiWaitTakeOK(); //for instrumentTest setup timing//~vaw9I~
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
     	int sizePairNumSS=pairNumSS.length;                        //~va11R~
        if (Dump.Y) Dump.println("UARank.setRank pairNumSS=size="+sizePairNumSS+"="+Pair.toString(pairNumSS));//~va11R~//~vawaI~
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
        if (Dump.Y) Dump.println("UARank.setRank swChkFix="+swChkFix+",typeYakuFix="+typeYakuFix+",swAllinhand="+swAllInHand+",swTaken="+swTaken);//~vaw9I~
        for (Pair pairS[]:pairNumSS)                               //~va11R~
        {                                                          //~va11I~
	        if (Dump.Y) Dump.println("UARank.setRank pairS="+Pair.toString(pairS));//~vawaI~//~vawvR~
            longRank=new Rank();                                   //~va11R~
            longRankFixErr=new Rank();                                 //~va91I~
        	int rank=0;                                            //~va91R~
        	rankFixErr=0;                                          //~va91I~
        	rankFixErrMultiWait=0;                                 //~vakhI~
//        	if (pairS!=null)                                       //~va11I~//~vawbR~
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
	//*called when pair exists on Earth under chkFix option        //~vawdM~
	//*==>for Honor Tile, see chkEarthHonorTile()                  //~vawdR~
    //*YAKUFIX_FIRST: 1st earth have be related  OR other is all other related have to be in hand.//~vawdI~
    //*            ==>If AllInHand, OK if not KataAgari(3 meld is all in Hand or Last is Fixed).//~vawfR~
    //*               if 1st call is related   t but remaining have to be all in hand)//~vawdI~//~vawfR~
    //*            (mixed related and nonrereted is allowed by YAKUFIXED_MIDDLE)//~vawdI~
    //*            (allow multiple related after first related)    //~vawdI~
    //*YAKUFIX_MIDDLE:allow related after no related.(fix at last is not allowed)//~vawdI~
	//*swLast:Ron is related, swMiddle:mixed related and no related//~vawdR~
	//*rc:false:set fix err                                        //~vawdM~
	//*rc:false:fix err                                            //~va91I~
	//*************************************************************************//~va91I~
    private boolean setFixErr(int Prank,boolean PswLast,boolean PswMiddle)//~va91I~
    {                                                              //~va91I~
        if (Dump.Y) Dump.println("UARank.setFixErr typeYakuFix="+typeYakuFix+",Prank="+Rank.toStringName(Prank)+",swLast="+PswLast+",swMiddle="+PswMiddle);//~va91I~//~vawgR~
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
        if (Dump.Y) Dump.println("UARank.setFixErr rc="+rc+",rankFixErr="+rankFixErr+",Prank="+Rank.toStringName(Prank)+",swLast="+PswLast+",swMiddle="+PswMiddle);//~vakaI~//~vawdR~
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
    //*called under swTaken=true & swLast=true(3dragonSmall,3WindNoHonor) or swLastNotFix(else)//~vawpI~
    //*apply multiWait TakeOK (reset FixErr)                       //~vawpR~
    //*for ronTaken is related and ryanmen under sakizuke,nakaduke err//~vakaR~
    //*rc=true:assume Fixed by Take                                //~vawpR~
    //****************************************************************//~vakaI~
    private boolean setMultiWaitTake(int Prank,boolean PswOther)   //~vakaI~//~vakhR~
    {                                                              //~vakaI~
    	if (Dump.Y) Dump.println("UARank.setMultiWaitTake rank="+Rank.toStringName(Prank)+",swYakuFixMultiWaitTakeOK="+swYakuFixMultiWaitTakeOK+",swOther="+PswOther+",swAllInHand="+swAllInHand+",typeYakuFix="+typeYakuFix);//~vakaR~//~vakhR~
        boolean rc=false;                                          //~vakaI~
	    if (swAllInHand || swYakuFixMultiWaitTakeOK)	//not all in Hand//~vakaR~
        {                                                          //~vakaI~
          if (PswOther && typeYakuFix==YAKUFIX_FIRST)              //~vakaI~
          {                                                        //~vakaI~
			if (swBugFixTakeOK)                                    //~vawqI~
            {                                                      //~vawqI~
            	addYakuFixErrMultiWaitTakeOK(Prank);      //allow multiwait take ron//~vawqI~
            	rc=true;                                           //~vawqI~
            }                                                      //~vawqI~
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
    //****************************************************************//~vawuI~
    //*under swTaken & fixed at Last                               //~vawuI~
    //****************************************************************//~vawuI~
    private boolean isMultiWaitTake(int PctrPairFirst/*before non related on earth*/,int PctrPairNotFirst/*after non related*/)//~vawuI~
    {                                                              //~vawuI~
        boolean rc=PctrPairFirst!=0;     //requres  related on earth before other//~vawuR~
        if (!rc && typeYakuFix==YAKUFIX_MIDDLE)                    //~vawuI~
        	rc=PctrPairNotFirst!=0;      //for Middle allow related after non related//~vawuR~
		if (Dump.Y) Dump.println("UARank.isMultiWaitTake rc="+rc+",ctrPairFisrt="+PctrPairFirst+",ctrPairNotFirst="+PctrPairNotFirst);//~vawuI~
        return rc;                                                 //~vawuI~
    }                                                              //~vawuI~
    //****************************************************************//~vakhI~
    //*apply multiWait TakeOK if swTaken & swLastNotFix for FixErr //~vakhI~
    //*For honor, no case of err by fixMix                         //~vakhI~
    //*for ronTaken is related and ryanmen under sakizuke,nakaduke err//~vakhI~
    //*NOT Used                                                    //~vawqI~
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
    //*Not Used                                                    //~vaq0I~
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
    //*Not Used                                                    //~vaq0I~
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
	//*Not Used                                                    //~vaq0I~
	//*************************************************************************//~vaq0I~
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
	//*PpairS may be null                                          //~vawbI~
	//*************************************************************************//~vawbI~
    private int setRank(Pair[] PpairS)                             //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARank.setRank PpairsS="+Pair.toString(PpairS));//~va11R~//~vawbR~
    	int rank=0;
        pairNumS=PpairS;                                           //~va11R~
      if (pairNumS==null)                                          //~vawbI~
        sizePairSeqS=0;                                            //~vawbI~
      else                                                         //~vawbI~
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
	    	if (swNewChkHonorTile)                                 //~vawdI~
	          	swHonorTileOK=chkEarthHonorTile2();	//Wind,Round,WGR//~vawdI~
            else                                                   //~vawdI~
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
        rank+=chk3SeqNum();         //3renpon                      //~vawvI~
//      rank+=chk3DupSeq();         //1shiki3jun                   //~vax1R~
        int rankDup3=chk3DupSeq();         //1shiki3jun                //~vax1I~
        rank+=rankDup3;                                            //~vax1I~
        rank+=chk3SameHand();       //3anko                        //~va11R~
        rank+=chkStraight();        //ikkitukan                    //~va11R~
        rank+=chkStraight3();       //3colorStraight               //~vawgR~
        rank+=chkAllSame();         //toitoi                       //~va11R~
        rank+=chkTerminalMix();     //honchanta                    //~va11R~
        rank+=chk3Kan();            //3kan                         //~va11R~
    //rank3                                                        //~va11R~
      if (rankDup3==0)	                                           //~vax1I~
        rank+=chkSameSeq();        //1/2peiko                     //~va11R~
        rank+=chkTerminal();        //junchanta                    //~va11R~
        rank+=chkFlushMix();        //honitsu                      //~va11R~
    //rank4                                                        //~va11R~
        if (Dump.Y) Dump.println("UARank.chkYakuStandard swHonorTileOK="+swHonorTileOK);//~vawdI~
//    if (swHonorTileOK)	//3DragonSmall is mised with yakuhai;fixed of by honor tile 3dragon has 2 han more//~vakdI~//~vawdR~
//    {                                                            //~vakdI~//~vawdR~
        rank+=chk3DragonSmall();    //shosangen                    //~va11R~
//    }                                                            //~vakdI~//~vawdR~
        rank+=chkTerminalOnlyMix(); //honro                        //~va11R~
    //rank6                                                        //~va11R~
        rank+=chkFlush();           //chinitsu                     //~va11R~
//      rank+=chk3WindNoHonor(longRank);                           //~vaw9I~//~vawyR~
//      rank+=chk3WindNoHonor();                           //~vawyI~//~vax0R~
        int rankNH=chk3WindNoHonor();                              //~vax0I~
        rank+=rankNH;                                              //~vax0I~
        if (rankNH==0)                                             //~vax0I~
	        rank+=chk3Winds();                                     //~vax0R~
        rank+=chkSingle(rank);         //1tile in hand             //~vaw8R~
        if (Dump.Y) Dump.println("UARank.chkYakuStandard rc="+rank);//~va11R~
        return rank;                                               //~va11I~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    public boolean isPinfu()                                       //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARank.isPinfu sizePiarSeqS="+sizePairSeqS);//~vawbI~
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
     	if (sizePairSeqS==0)                                       //~vawbI~
        	rc=false;                                              //~vawbI~
        else                                                       //~vawbI~
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
        if (Dump.Y) Dump.println("UARank.isPinfu intNoAllHand="+intNotAllHand+",ctrPairNotNum="+ctrPairNotNum+",swHonor="+swHonor+",rc="+rc+",pairNumS="+Pair.toString(pairNumS));//~va26R~//~vaq0R~
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
    //*FixFirst chk                                                //~vaq0I~//~vawfM~
	//*==>for Honor Tile, see another Logic                        //~vawfI~
    //*YAKUFIX_FIRST: Basiccaly, 1st earth have be related  OR all other related have to be in hand(last should not be related).//~vawfR~
    //*           ==> OK if 1st call is for Honor meld, OR OK if Honor meld is in your hand(last should not be related).//~vawfR~
    //*             Else(no erath or 1st call is not related)      //~vawfR~
    //*               For shanpon of Honor and Honor               //~vawfI~
    //*                   OK if Taken.                             //~vawfR~
    //*               For shanpon of Honor and Non-Honor           //~vawfI~
    //*                   OK if Taken at AllInHand.                //~vawfI~
    //*                 Else(not AllInHand), OK if setting allows selective winning meld.//~vawfR~
    //*YAKUFIX_MIDDLE:only fixed at last is fixErr                 //~vawfI~
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
        boolean swOtherFirst=false;                                      //~vawrI~
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
            {                                                      //~vawrI~
                swOther=true;                                      //~vaq0I~
                if (ctrPairFirst==0)                               //~vawrI~
	                swOtherFirst=true;                                  //~vawrI~
            }                                                      //~vawrI~
        }                                                          //~vaq0I~
        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile after chkEarth swOther="+swOther+",ctraPairNotFirst="+ctrPairNotFirst+",ctrPairFirst="+ctrPairFirst);//~vaq0R~//~vaweR~
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
					  if (isMultiWaitTake(ctrPairFirst,ctrPairNotFirst))//~vawuI~
                    	if (swAllInHand || swYakuFixMultiWaitTakeOK) //allow as anko by take//~vaq0R~
                        {                                          //~vaq0I~
		                	swLastNotFix=false;	//ron fixed        //~vaq0I~
	    	            	ctrPairFirst+=rankUpLast;              //~vaq0R~
                            rankUpLast=0;	//evaluated as ctrPairFirst, not use for err by swLastNotFix//~vaq0I~
                        }                                          //~vaq0I~
                }                                                  //~vaq0I~
                else                                               //~vaq0I~
                {                                                  //~vaq0I~
                //*shanpon of honor+honor                          //~vawrI~
//              	if (swTaken)     //shanpon Honor+Honor,assume anko//~vaq0I~//~vawrR~
//                  {                                              //~vaq0I~//~vawrR~
//                    if (swAllInHand || swYakuFixMultiWaitTakeOK) //allow as anko by take//~vawrR~
//                    {                                            //~vawrR~
              	    if (swTaken     //shanpon Honor+Honor,assume anko//~vawrI~
                    && (swAllInHand || swYakuFixMultiWaitTakeOK) //allow as anko by take//~vawrI~
					&& isMultiWaitTake(ctrPairFirst,ctrPairNotFirst)//~vawuI~
                    )                                              //~vawrI~
                    {                                              //~vawrI~
	    	        	ctrPairFirst+=rankUpLast;                  //~vaq0I~
                      	rankUpLast=0;	//evaluated as ctrPairFirst, not use for err by swLastNotFix//~vaq0I~
//                    }                                            //~vawrR~
                    }                                              //~vaq0I~
//                  else                                           //~vawrI~//~vawuR~
//                	if (swOtherFirst)                              //~vawrI~//~vawuR~
//                	{                                              //~vawrI~//~vawuR~
//      				if (Dump.Y) Dump.println("UARank.chkEarthHonorTile assume R+R not as fixed by 1st is NonRelated");//~vawrI~//~vawuR~
//                	}                                              //~vawrI~//~vawuR~
                    else	                                       //~vawmI~
                    if (!swOther)                                  //~vawmI~
                    {                                              //~vawmI~
	    	        	ctrPairFirst+=rankUpLast;                  //~vawmI~
                      	rankUpLast=0;	//evaluated as ctrPairFirst, not use for err by swLastNotFix//~vawmI~
                    }                                              //~vawmI~
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
          if (ctrPairFirst==0)                                     //~vaweI~
          {                                                        //~vaweI~
            rankFixErr+=ctrPairNotFirst;                           //~vaq0R~
//          if (swOther || swLastNotFix)   //false if taken allowed//~vaq0R~//~vawfR~
//          if (swLastNotFix)   //false if taken allowed           //~vawfR~
            {                                                      //~vaq0I~
            	rankFixErr+=rankUpLast;    //0 if taken            //~vaq0R~
//          	if (!swOther)  //false if taken allowed            //~vaq0R~//~vawfR~
//              	if (ctrPairFirst+ctrPairNotFirst==0) //by the condition last shambon only        //~vaq0R~//~vawfR~
                if (swLastNotFix)   //shanpon by honor and not honor//~vawfI~
	                    rankFixErrMultiWait+=rankUpLast;           //~vaq0R~
            }                                                      //~vaq0I~
          }                                                        //~vaweI~
        }                                                          //~vaq0R~
        else                                                       //~vaq0I~
        if (typeYakuFix==YAKUFIX_MIDDLE)                           //~vaq0R~
        {                                                          //~vaq0R~
//          if (swLastNotFix)   //false if taken allowed           //~vaq0R~//~vawfR~
            if (ctrPairFirst+ctrPairNotFirst==0)	//no honor on earth and hand//~vawfI~
            {                                                      //~vaq0R~
            	rankFixErr+=rankUpLast;    //0 if taken            //~vaq0R~
//              if (ctrPairFirst+ctrPairNotFirst==0)	//no honor on earth and hand//~vaq0R~//~vawfR~
                if (swLastNotFix)   //shanpon by honor and not honor//~vawfI~
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
        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile swOther="+swOther+",swOtherFirst="+swOtherFirst+",rankFixErr="+rankFixErr+",rankFixErrMultiWait="+rankFixErrMultiWait);//~vaq0R~//~vawrR~
        rc=rankFixErr==0;                                           //~vaq0R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairFirst="+ctrPairFirst+",ctrPairNotFirst="+ctrPairNotFirst);//~vaq0R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile rankUp="+rankUp+",rankUpLast="+rankUpLast);//~vaq0R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile rc="+rc+",rankFixErr="+rankFixErr+",rankFixErrMultiWait="+rankFixErrMultiWait);//~vaq0I~
    	return rc;                                                 //~vaq0I~
    }                                                              //~vaq0I~
    //****************************************************************//~vawdI~
    //*FixFirst chk                                                //~vawdI~
	//*************************************************************************//~vawdM~
	//*==>for Honor Tile, see another Logic                        //~vawdM~
    //*YAKUFIX_FIRST: 1st earth have be related  OR other is all other related have to be in hand.//~vawdM~
    //*            ==>disallow fix by last, first earth is Honor or a Honor have to be in Hand//~vawdM~
    //*YAKUFIX_MIDDLE:allow related after no related.(fix at last is not allowed)//~vawdM~
    //*            ==>disallow fix by last, a Honor is in earth or in hand.//~vawdM~
	//*swLast:Ron is related, swMiddle:a Honor is before honor in earth//~vawdM~
	//*rc:false:fix err                                            //~vawdI~
	//*************************************************************************//~vawdM~
    //*rc:false fixFirst/FixMiddle err                             //~vawdI~
    //****************************************************************//~vawdI~
    private boolean chkEarthHonorTile2()	//Wind,Round,WGR       //~vawdI~
    {                                                              //~vawdI~
        boolean swOther=false,swMiddle=false,rc;      //~vawdI~
        boolean swLastNotFix=false;                                //~vawdR~
        int ctrWGR;                                                //~vawdI~         //~vawdI~
        boolean swDouble;                                          //~vawdI~
        int rankUp=0,rankUpLast=0;                                 //~vawdI~
    //***************************                                  //~vawdI~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile2 pairEarth="+Pair.toString(pairEarth));//~vawdR~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~vawdI~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile longRankOther="+Rank.toStringName(UARDT.longRankOther,true/*printHonor*/));//~vawdI~
        swDouble=(eswnHonor==roundHonor);                          //~vawdI~
        Rank rankOther=UARDT.longRankOther;                        //~vawdI~
        ctrWGR=rankOther.getWGR();                                 //~vawdI~
        if (!rankOther.isContains(RYAKU_ROUND)                     //~vawdI~
        &&  !rankOther.isContains(RYAKU_WIND)                      //~vawdI~
        &&  ctrWGR==0                                              //~vawdI~
        )                                                          //~vawdI~
        {                                                          //~vawdI~
	    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile no honor rank longrRank="+Rank.toStringName(longRank));//~vawdI~
        	return true;                                           //~vawdI~
        }                                                          //~vawdI~
        //**chk on earth                                           //~vawdR~
	    int ctrMiddle=0;
        boolean swFirst=false;
        for (Pair pair:pairEarth)                                  //~vawdI~
        {                                                          //~vawdI~
        	if (pair==null)                                        //~vawdI~
            	continue;                                          //~vawdI~
            if (pair.type==TT_JI && (pair.number>=TT_4ESWN_CTR || pair.number==eswnHonor|| pair.number==roundHonor))//~vawdI~
            {     //honor tile                                     //~vawdR~
            	rankUp=(swDouble && pair.number==eswnHonor) ? 2:1; //~vawdI~
                if (swOther)  //NR+R                               //~vawdR~
                {                                                  //~vawdI~
//                  ctrPairNotFirst+=rankUp;                       //~vawdI~
					swMiddle=true;                                 //~vawdI~
                    ctrMiddle+=rankUp; //err ctr when FIX_FIRST    //~vawdR~
                }                                                  //~vawdI~
                else                                               //~vawdI~
//                  ctrPairFirst+=rankUp;                          //~vawdI~
                    swFirst=true;  //fixed                         //~vawdR~
            }                                                      //~vawdI~
            else                                                   //~vawdI~
                swOther=true;                                      //~vawdI~
        }                                                          //~vawdI~
        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile after chkEarth swOther="+swOther+",swMiddle="+swMiddle+",swFirst="+swFirst+",ctrMiddle="+ctrMiddle);//~vawdI~
        if (swFirst)                                               //~vawdI~
        {                                                          //~vawdI~
	        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile return true by 1st related on Earth");//~vawdI~
        	return true;	//no fix err                           //~vawdI~
        }                                                          //~vawdI~
        boolean swInHand=false;                                            //~vawdI~
        for (Pair pair:pairNotNum)     //earth and hand            //~vawdI~
        {                                                          //~vawdI~
	        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile chk hand swHand="+pair.swHand+",PairNotNum pair="+pair);//~vawdR~
            if (!pair.swHand)    //not hand                        //~vawdI~
            	continue;                                          //~vawdI~
            if (!(pair.type==TT_JI && (pair.number>=TT_4ESWN_CTR || pair.number==eswnHonor|| pair.number==roundHonor)))	//not honor meld//~vawdI~
            	continue;                                          //~vawdI~
            rankUp=(swDouble && pair.number==eswnHonor) ? 2:1;     //~vawdI~
//          boolean swAnko=false;                                  //~vawdI~
            if (pair.type==ronType && pair.number==ronNumber)	//ron by shanpon//~vawdI~
            {                                                      //~vawdI~
                rankUpLast=rankUp;	//ron by honor tile            //~vawdI~
            	boolean swLastFixed=(typePillow==TT_JI && (numberPillow>=TT_4ESWN_CTR || numberPillow==eswnHonor|| numberPillow==roundHonor));	//pillow is honor tile//~vawdI~
            	if (!swLastFixed) //shanpon of honor and not honor //~vawdI~
                {                                                  //~vawdI~
                  	swLastNotFix=true;	//ron is not fixed         //~vawdR~
	        		if (typeYakuFix!=YAKUFIX_LAST && swTaken)      //~vawdI~
                    	if (swAllInHand || swYakuFixMultiWaitTakeOK) //allow as anko by take//~vawdI~
                        {                                          //~vawdI~
  		                	swLastNotFix=false;	//ron fixed        //~vawdR~
//      	            	ctrPairFirst+=rankUpLast;              //~vawdI~
//                          rankUpLast=0;	//no additional rank by selection//~vawdR~
                        }                                          //~vawdI~
                }                                                  //~vawdI~
                else                                               //~vawdI~
                {                                                  //~vawdI~
                  	if (swTaken)     //shanpon Honor+Honor,assume anko//~vawdR~
                    {                                              //~vawdR~
//	    	        	ctrPairFirst+=rankUpLast;                  //~vawdR~
//                  	rankUpLast=0;	//evaluated as ctrPairFirst, not use for err by swLastNotFix//~vawdI~
		        		swInHand=true;  //assume fixed in Hand     //~vawdI~
                    }                                              //~vawdR~
                    else                                           //~vawdI~
                    	if (!swAllInHand)        //allow Honor+honor if AllInHand//~vawdI~
        	          		swLastNotFix=true;	//ron is not fixed //~vawdR~
	                if (Dump.Y) Dump.println("UARank.chkEarthHonorTile Ron shanpon LastFix="+swLastFixed+",rankUpLast="+rankUpLast);//~vawdR~
                }                                                  //~vawdI~
                if (Dump.Y) Dump.println("UARank.chkEarthHonorTile Ron shanpon LastFix="+swLastFixed+",rankUpLast="+rankUpLast);//~vawdR~
            }                                                      //~vawdI~
            else                                                   //~vawdR~
            {                                                      //~vawdI~
//            	ctrPairFirst+=rankUp;                              //~vawdI~
		        swInHand=true;                                     //~vawdI~
				break;	//found in Hand, no fix err	               //~vawdI~
			}                                                      //~vawdI~
	        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile rankUpLast="+rankUpLast);//~vawdR~
        }//all meld hand and earth                                 //~vawdI~
        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile after chkHand swInHand="+swInHand+",swTaken="+swTaken+",swYakuFixMultiWaitTakeOK="+swYakuFixMultiWaitTakeOK);//~vawdR~
        if (swInHand)    //anko                                    //~vawdR~
        {                                                          //~vawdI~
	        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile return true by Anko");//~vawdI~
        	return true;                                           //~vawdI~
        }                                                          //~vawdI~
        if (rankUpLast>0 && !swLastNotFix)	//last selective shanpon, and it is fixed selection//~vawdI~
        {                                   //fixerr gor FIRST and MIDDLE(not called if LAST)//~vawdI~
	        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile return true by shanpon is fixed swTaken="+swTaken+",swYakuFixMultiWaitTakeOK="+swYakuFixMultiWaitTakeOK);//~vawdI~
        	return true;                                           //~vawdI~
        }                                                          //~vawdI~
        if (typeYakuFix==YAKUFIX_FIRST)                            //~vawdI~
        {                                                          //~vawdI~
//        if (ctrPairFirst==0)                                     //~vawdI~
//        {                                                        //~vawdI~
//          rankFixErr+=ctrPairNotFirst;                           //~vawdI~
//          if (swOther || swLastNotFix)   //false if taken allowed//~vawdI~
//          if (swFirst || swLastNotFix)   //1st on earth not fix by ron//~vawdI~
//          {                                                      //~vawdI~
//          	rankFixErr+=rankUpLast;    //0 if taken            //~vawdI~
//          	if (!swOther)  //false if taken allowed            //~vawdI~
//              	if (ctrPairFirst+ctrPairNotFirst==0)           //~vawdI~
//                      rankFixErrMultiWait+=rankUpLast;           //~vawdI~
//          }                                                      //~vawdI~
//        }                                                        //~vawdI~
            rankFixErr+=rankUpLast;                                //~vawdI~
            rankFixErrMultiWait+=rankUpLast;                       //~vawdI~
            if (swMiddle)  //NR+R           //fix err for middle if FIRST//~vawdM~
            	rankFixErr+=ctrMiddle;                             //~vawdI~
        }                                                          //~vawdI~
        else                                                       //~vawdR~
        if (typeYakuFix==YAKUFIX_MIDDLE)                           //~vawdR~
        {                                                          //~vawdR~
            rankFixErr+=rankUpLast;    //0 if taken                //~vawdR~
            rankFixErrMultiWait+=rankUpLast;                       //~vawdR~
        }                                                          //~vawdR~
//      else    //LAST                                             //~vawdR~
//      {                                                          //~vawdR~
//          ;//LAST allow All han                                  //~vawdR~
//      }                                                          //~vawdR~
        if (Dump.Y) Dump.println("UARank.chkEarthHonorTile swOther="+swOther+",rankFixErr="+rankFixErr+",rankFixErrMultiWait="+rankFixErrMultiWait);//~vawdI~
        rc=rankFixErr==0;                                          //~vawdI~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix);//~vawdR~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile typePillow="+typePillow+",numberPillow="+numberPillow);//~vawdI~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile rankUp="+rankUp+",rankUpLast="+rankUpLast+",swNotLastFix="+swLastNotFix);//~vawdI~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile rc="+rc+",rankFixErr="+rankFixErr+",rankFixErrMultiWait="+rankFixErrMultiWait);//~vawdI~
    	return rc;                                                 //~vawdI~
    }                                                              //~vawdI~
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
    	if (Dump.Y) Dump.println("UARank.chk3SameSeq rc="+rc+",rankFixErr="+rankFixErr);     //~va11R~//~vawdR~
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
        int ctrPairFirst=0;                                        //~vawkI~
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
                else                                               //~vawkI~
                    ctrPairFirst++;                                //~vawkI~
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
    	  if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vawkI~
          {                                                        //~vawkI~
			if (Dump.Y) Dump.println("UARank.chkEarth3SameSeq swNotLast=T,ignore swOther swLast by ctrPairFirst!=0");//~vawkI~//~vawpR~
          }                                                        //~vawkI~
          else                                                     //~vawkI~
          {                                                        //~vawkI~
        	if (swOther)                                           //~va91I~
	            if (ctrPairNotFirst!=0)  //non related earth pair exist//~va91I~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~va91I~
          }                                                        //~vawkI~
        }                                                          //~va91I~
        else      //ron tile related yaku                          //~va91I~
        {                                                          //~va91I~
        	if (swSeqFix)    //kanchan etc                         //~va91I~
            {	                                                   //~va91I~
    		  if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vawkI~
              {                                                    //~vawkI~
			    if (Dump.Y) Dump.println("UARank.chkEarth3SameSeq swSeqFix,ignore swOther by ctrPairFirst!=0");//~vawkI~//~vawpR~
              }                                                    //~vawkI~
              else                                                 //~vawkI~
              {                                                    //~vawkI~
            	if (swOther)                                       //~va91I~
	              if (ctrPairNotFirst!=0)  //related earth pair exist after non related//~vakbI~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~vakbI~
                  else                                             //~vakbI~
                	swLast=true;                                   //~va91I~
              }                                                    //~vawkI~
            }                                                      //~va91I~
            else	//ryanmen                                      //~va91I~
            {                                                      //~va91I~
                swLast=true;                                       //~va91I~
                swLastNotFix=true;                                 //~vakaI~
            }                                                      //~va91I~
        }                                                          //~va91I~
        rc=setFixErr(RYAKU_3SAMESEQ,swLast,swMiddle);              //~va91I~
        if (!rc && swTaken && swLastNotFix)	//fix err and last related take//~vakaI~
          if (isMultiWaitTake(ctrPairFirst,ctrPairNotFirst))       //~vawuI~
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
		    	if (Dump.Y) Dump.println("UARank.chk3Same pair1="+pair1+",ctr="+ctr);//~vaw9I~
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
    	if (Dump.Y) Dump.println("UARank.chk3Same rc="+rc+",swMultiWaitErr="+swFixErrMultiWait+",rankFixErr="+rankFixErr+",rankFixerrMultiWait="+rankFixErrMultiWait);        //~va11R~//~vakhR~//~vaw9R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va91I~
    //*FixFirst chk                                                //~va91I~
    //*rc:false fixFirst/FixMiddle err                             //~va91I~
    //****************************************************************//~va91I~
    private boolean chkEarth3Same(int Pnum)        //3tonko        //~va91R~
    {                                                              //~va91I~
        int ctrPairNotFirst=0;                                     //~va91R~
        int ctrPairFirst=0;                                        //~vawuI~
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
                else                                               //~vawuI~
                	ctrPairFirst++;                                //~vawuI~
            }                                                      //~va91R~
            else                                                   //~va91R~
                swOther=true;                                      //~va91R~
        }                                                          //~va91R~
        boolean swLastRelated=(ronType!=TT_JI && ronNumber==Pnum); //ron tile related to yaku//~vakaI~
     	if (swLastRelated)                                             //~vax4I~
        {                                                          //~vax4I~
            if (sizePairSeqS>0)                                    //~vax4I~
            {                                                      //~vax4I~
                for (Pair pair:pairNumS)                           //~vax4I~
                {                                                  //~vax4I~
                    if (Dump.Y) Dump.println("UARank.chk3EarthSame pair="+Pair.toString(pair));//~vax4I~//~vax5R~
                    if (pair.typePair==PT_NUMSEQ && pair.swHand)    //not earth//~vax4I~
                    {                                              //~vax4I~
                        if (ronType==pair.type && ronNumber>=pair.number && ronNumber<pair.number+3)//~vax4I~
                        {                                          //~vax4I~
		                    if (Dump.Y) Dump.println("UARank.chkEarth3Same reset swRelated by DupSeq Meld");//~vax4R~
                            swLastRelated=false;                       //~vax4I~
                            break;                                 //~vax4I~
                        }                                          //~vax4I~
                    }                                              //~vax4I~
                }                                                  //~vax4I~
            }                                                      //~vax4I~
        }                                                          //~vax4I~
        if (ctrPairNotFirst!=0)  //non related earth pair exist    //~va91R~
            swMiddle=true;       //OK if YAKUFIX_MIDDLE            //~va91R~
//      if (!swMiddle)   even middle chk fixed at last             //~va91I~//~vawaR~
//          if (ronType!=TT_JI && ronNumber==Pnum) //ron tile related to yaku//~va91I~//~vax4R~
            if (swLastRelated) //ron tile related to yaku          //~vax4I~
            {                                                      //~va91I~
                swLast=true;                                       //~va91I~
            }                                                      //~va91I~
        rc=setFixErr(RYAKU_3SAMENUM,swLast,swMiddle);                 //~va91R~
        if (!rc && swTaken && swLastRelated)	//fix err and last related take//~vakaI~
          if (isMultiWaitTake(ctrPairFirst,ctrPairNotFirst))       //~vawuI~
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
        if (Dump.Y) Dump.println("UARank.chk3SameHand swPinfu="+swPinfu+",pairEarth="+pairEarth);//~vaq0I~
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
     	boolean swDupSeq=false;                                          //~vax3I~
     if (sizePairSeqS>0)                                           //~vawbI~
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
                                                                   //~vax3I~
        	if (pair.typePair==PT_NUMSEQ && pair.swHand)	//not earth//~vax3I~
            {                                                      //~vax3I~
  	        	if (ronType==pair.type && ronNumber>=pair.number && ronNumber<pair.number+3)//~vax3I~
                	swDupSeq=true;                                 //~vax3I~
            }                                                      //~vax3I~
        }                                                          //~va11I~
        if (swDupSeq)                                              //~vax3I~
        {                                                          //~vax3I~
        	if (swLast)	//ron also for triples                     //~vax3I~
            {                                                      //~vax3I~
            	swLast=false;                                      //~vax3I~
                ctr++;                                             //~vax3I~
            }                                                      //~vax3I~
        }                                                          //~vax3I~
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
// 			      if (!swYakuFixMultiWaitTakeOK)	//not all in Hand//~vawpI~//~vawuR~
                	rankFixErr+=RANK_3SAMEHAND;                    //~vapzI~
                }                                                  //~vapzI~
            }                                                      //~vapzI~
        }                                                          //~vakgI~
        if (ctr==3)                                                //~va11I~
        {                                                          //~va11I~
            addYaku(RYAKU_3SAMEHAND);                              //~va11I~
        	rc=RANK_3SAMEHAND;                                     //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARank.chk3SameHand rc="+rc+",ctr="+ctr+",swDupSeq="+swDupSeq+",swLast="+swLast+",swTaken="+swTaken+",swOther="+swOther+",swAllInHand="+swAllInHand+",rankFixErr="+rankFixErr);//~va11R~//~vakgR~//~vawdR~//~vawoR~//~vax3R~
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
        int ctrPairFirst=0;                                        //~vawkI~
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
                else                                               //~vawkI~
                    ctrPairFirst++;                                //~vawkI~
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
    	  if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vawkI~
          {                                                        //~vawkI~
			if (Dump.Y) Dump.println("UARank.chkEarthStraight swNotLast=T,ignore swOther swLast by ctrPairFirst!=0");//~vawkI~//~vawpR~
          }                                                        //~vawkI~
          else                                                     //~vawkI~
          {                                                        //~vawkI~
        	if (swOther)                                           //~va91I~
	            if (ctrPairNotFirst!=0)  //non related earth pair exist//~va91I~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~va91I~
          }                                                        //~vawkI~
        }                                                          //~va91I~
        else      //ron tile related yaku                          //~va91I~
        {                                                          //~va91I~
        	if (swSeqFix)    //kanchan etc                         //~va91I~
            {                                                      //~va91I~
    		  if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vawkI~
              {                                                    //~vawkI~
			    if (Dump.Y) Dump.println("UARank.chkEarthStraight swSeqFix,ignore swOther by ctrPairFirst!=0");//~vawkI~//~vawpR~
              }                                                    //~vawkI~
              else                                                 //~vawkI~
              {                                                    //~vawkI~
            	if (swOther)                                       //~va91I~
	              if (ctrPairNotFirst!=0)  //related earth pair exist after non related//~vakbR~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~vakbI~
                  else                                             //~vakbI~
                	swLast=true;                                   //~va91I~
              }                                                    //~vawkI~
            }                                                      //~va91I~
            else	//ryanmen                                      //~va91I~
            {                                                      //~va91I~
                swLast=true;                                       //~va91I~
                swLastNotFix=true;                                 //~vakaI~
            }                                                      //~va91I~
        }                                                          //~va91I~
        rc=setFixErr(RYAKU_STRAIGHT,swLast,swMiddle);              //~va91I~
        if (!rc && swTaken && swLastNotFix)	//fix err and last related take//~vakaI~
          if (isMultiWaitTake(ctrPairFirst,ctrPairNotFirst))       //~vawuI~
	    	rc=setMultiWaitTake(RYAKU_STRAIGHT,swOther);	//allow multiwait take by option//~vakaI~
        if (!rc && swLastNotFix)	//last related is ron or not effective take//~vakhR~
	        setFixErrMultiWait(swOther);                           //~vakhI~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight rc="+rc+",Ptype="+Ptype+",ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairRon="+ctrPairRon+",ctrPairNotFirst="+ctrPairNotFirst+",swOther="+swOther);//~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight swTaken="+swTaken+",swNotLast="+swNotLast+",swLastNotFix="+swLastNotFix+",swNearRelated="+swNearRelated+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle+",swSeqFix="+swSeqFix);//~va91R~//~vakaR~
    	return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //****************************************************************//~vawfI~
    private int chkStraight3()        //3colorStraight             //~vawgR~
    {                                                              //~vawgR~
       	Pair pair;                                                 //~vawgR~
    //********                                                     //~vawgR~
	    if (Dump.Y) Dump.println("UARank.chkStraight3 swChkFix="+swChkFix);           //~vawgR~//~vawpR~
        if (swTanyao)                                              //~vawgR~
        {                                                          //~vawgR~
	    	if (Dump.Y) Dump.println("UARank.chkStraight3 tanyao rc=0");//~vawgR~
        	return 0;                                              //~vawgR~
        }                                                          //~vawgR~
    	if (!RuleSettingYaku.isLocalYaku3ColorStraight())          //~vawgR~
        	return 0;                                              //~vawgR~
        int rc=0;                                                  //~vawgR~
        int wkBitType=0,wkBitNum=0;                                //~vawgR~
        int ctrTypeNum=0,ctrNumType=0,maskTypeNum=0;                //~vawgI~
        for (int ii=0;ii<sizePairSeqS;ii++)                        //~vawgR~
        {                                                          //~vawgR~
            pair=pairNumS[ii];                                     //~vawgR~
		    if (Dump.Y) Dump.println("UARank.chkStraight3 pair="+pair);//~vawgR~
        	if (pair.typePair!=PT_NUMSEQ)                          //~vawgR~
            	continue;                                          //~vawgR~
            int num=pair.number;                                   //~vawgR~
            if (num!=TN1 && num!=TN4 && num!=TN7)                  //~vawgR~
            	continue;                                          //~vawgR~
            int type=pair.type;                                    //~vawgR~
            int idxnum=num/3;                                      //~vawgI~
            wkBitType|=(1<<type);                                  //~vawgR~
            wkBitNum |=(1<<idxnum);                                //~vawgR~
            ctrTypeNum+=(1<<(2-type)*8);                           //~vawgR~
            ctrNumType+=(1<<idxnum*8);                             //~vawgR~
            maskTypeNum|=(1<<(2-type)*8+idxnum);                   //~vawgR~
        }                                                          //~vawgR~
	    if (Dump.Y) Dump.println("UARank.chkStraight3 wkBitNum="+wkBitNum+",wkBitType="+wkBitType);//~vawgR~
        if ((wkBitType & 0x07)==0x07 && (wkBitNum & 0x07)==0x07)     //3color and 3num//~vawgR~
        {                                                          //~vawgR~
	        addYaku(RYAKU_STRAIGHT3);                              //~vawgR~
	        rc=RANK_STRAIGHT3;                                     //~vawgR~
        }                                                          //~vawgR~
        if (rc!=0)                                                 //~vawgR~
        {                                                          //~vawgR~
        	rc-=intNotAllHand;                                     //~vawgR~
            if (swChkFix)                                          //~vawgR~
            {                                                      //~vawgI~
            	maskTypeNum=resetDupPairStraight3(maskTypeNum,ctrTypeNum,ctrNumType);//~vawgI~
	            if (!chkEarthStraight3(maskTypeNum))               //~vawgR~
                {                                                  //~vawgR~
                	rankFixErr+=rc;                                //~vawgR~
                    if (swFixErrMultiWait)                         //~vawgR~
                		rankFixErrMultiWait+=rc;                   //~vawgR~
                }                                                  //~vawgR~
            }                                                      //~vawgI~
        }                                                          //~vawgR~
    	if (Dump.Y) Dump.println("UARank.chkStraight3 rc="+rc+",rankFixErr="+rankFixErr+",rankFixErrMultiWait="+rankFixErrMultiWait);//~vawgR~
    	return rc;                                                 //~vawgR~
    }                                                              //~vawgR~
    //****************************************************************//~vawgI~
    private int resetDupPairStraight3(int PmaskTypeNum,int PctrTypeNum,int PctrNumType)        //3colorStraight//~vawgI~
    {                                                              //~vawgI~
    	if (Dump.Y) Dump.println("UARank.resetDupPairStraight3 mask="+Integer.toHexString(PmaskTypeNum)+",ctrTypeNum="+Integer.toHexString(PctrTypeNum)+",ctrNumType="+Integer.toHexString(PctrNumType));//~vawgR~
        int mask=PmaskTypeNum;                                     //~vawgI~
        int idxType=-1,idxNum=-1;                                  //~vawgI~
        int ctr=PctrTypeNum;                                       //~vawgI~
        for (int ii=0;ii<3;ii++)                                   //~vawgI~
        {                                                          //~vawgI~
        	if ((ctr&0xff)==2)                                     //~vawgI~
            {                                                      //~vawgI~
            	idxType=ii;                                        //~vawgI~
            	break;                                             //~vawgI~
            }                                                      //~vawgI~
            ctr>>=8;                                               //~vawgI~
        }                                                          //~vawgI~
        if (idxType>=0)	//dup detected                             //~vawgI~
        {                                                          //~vawgI~
	        ctr=PctrNumType;                                       //~vawgI~
            for (int ii=0;ii<3;ii++)                               //~vawgI~
            {                                                      //~vawgI~
                if ((ctr&0xff)==2)                                 //~vawgI~
                {                                                  //~vawgI~
                    idxNum=ii;                                     //~vawgI~
                    break;                                         //~vawgI~
                }                                                  //~vawgI~
	            ctr>>=8;                                           //~vawgI~
            }                                                      //~vawgI~
            if (idxNum>=0)                                         //~vawgI~
            	mask &=~(1<<(idxType*8+idxNum));                   //~vawgI~
        }                                                          //~vawgI~
    	if (Dump.Y) Dump.println("UARank.resetDupPairStraight3 mask="+Integer.toHexString(mask)+",idxType="+idxType+",idxNum="+idxNum);//~vawgR~
        return mask;                                               //~vawgI~
    }                                                              //~vawgI~
    //****************************************************************//~vawgI~
    //*Pnum Type:0x01:1man, 0x02:1pin, 0x04:1sou, 0x10:4man, ...   //~vawgI~
    //****************************************************************//~vawgI~
//  private boolean isStraight3Pair(int PmaskTypeNum,int Ptype,int Pnum)        //3colorStraight//~vawgR~//~vax2R~
    private boolean isStraight3Pair(boolean PswMeld,int PmaskTypeNum,int Ptype,int Pnum)        //3colorStraight//~vax2I~
    {                                                              //~vawgI~
                                                                   //~vax2I~
        boolean rc=(PmaskTypeNum & (1<<((2-Ptype)*8+Pnum/3)))!=0;  //~vawgR~
    	if (PswMeld && Pnum%3!=0)                                  //~vax2I~
        	rc=false;                                              //~vax2I~
    	if (Dump.Y) Dump.println("UARank.isStraight3Pair rc="+rc+",swMeld="+PswMeld+",mask="+Integer.toHexString(PmaskTypeNum)+",type="+Ptype+",num="+Pnum);//~vawgI~//~vax2R~
        return rc;
    }                                                              //~vawgI~
    //****************************************************************//~vawgR~
    //*FixFirst chk                                                //~vawgR~
    //PnumType: 0x01:1man, 0x02:1pin, 0x04:1sou, 0x08:4man, ...    //~vawgI~
    //*rc:false fixFirst/FixMiddle err                             //~vawgM~
    //****************************************************************//~vawgR~
    private boolean chkEarthStraight3(int PmaskTypeNum)        //3colorStraight//~vawgR~
    {                                                              //~vawgR~
        int ctrPairNotFirst=0,ctrPairRon=0,pairnum;                //~vawgR~
        boolean swNotLast=false,swSeqFix=false,swOther=false,swLast=false,swMiddle=false,rc=true;//~vawgR~
        boolean swNearRelated=false;                               //~vawgR~
        boolean swLastNotFix=false;                                //~vawgR~
        int ctrPairFirst=0;                                        //~vawkI~
    //***************************                                  //~vawgR~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight3 pairEarth="+Pair.toString(pairEarth));//~vawgR~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight3 sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~vawgR~
        if (ronType==TT_JI)                                        //~vawgR~
        	swNotLast=true;    //ron tile is not related to yaku, out of fixLast//~vawgR~
        //*chk earth same pair as ron tile                         //~vawgR~
	    for (Pair pair:pairEarth)                                  //~vawgR~
        {                                                          //~vawgR~
        	if (pair==null)                                        //~vawgR~
            	continue;                                          //~vawgR~
            pairnum=pair.number;                                   //~vawgR~
            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~vawgR~
                continue;                                          //~vawgR~
            if (pair.typePair==PT_NUMSEQ                           //~vawgR~
//          &&  isStraight3Pair(PmaskTypeNum,pair.type,pair.number))  //related to yaku//~vawgR~//~vax2R~
            &&  isStraight3Pair(true/*swMeld*/,PmaskTypeNum,pair.type,pair.number))  //related to yaku//~vax2I~
            {                                                      //~vawgR~
                if (swOther)                                       //~vawgR~
                    ctrPairNotFirst++;                             //~vawgR~
                else                                               //~vawkI~
                    ctrPairFirst++;                                //~vawkI~
                if (pair.type==ronType && (ronNumber>=pairnum && ronNumber<pairnum+3))//~vawgR~
                	swNotLast=true;    //pair related of ron tile is already on earth, not swLast case//~vawgR~
            }                                                      //~vawgR~
            else                                                   //~vawgR~
                swOther=true;                                      //~vawgR~
        }                                                          //~vawgR~
//      if (!isStraight3Pair(PmaskTypeNum,ronType,ronNumber))  //related to yaku//~vawgR~//~vax2R~
        if (!isStraight3Pair(false/*swMeld*/,PmaskTypeNum,ronType,ronNumber))  //related to yaku//~vax2I~
            swNotLast=true;                                        //~vawgI~
		if (Dump.Y) Dump.println("UARank.chkEarthStraight3 after chk Earth swNotLast="+swNotLast+",swOther="+swOther+",ctrPairFirst="+ctrPairFirst+",ctrPairNotFirst="+ctrPairNotFirst);//~vax1I~
        if (!swNotLast)	//pair caontains rontile is not on Earth   //~vawgR~
        {                                                          //~vawgR~
            //*search dup in hands                                 //~vawgR~
            for (int ii=0;ii<sizePairSeqS;ii++)                    //~vawgR~
            {                                                      //~vawgR~
                Pair pair=pairNumS[ii];
                pairnum=pair.number;//~vawgR~
                if (pair.typePair==PT_NUMSEQ && pair.type==ronType 	//related to yaku//~vawgI~
 				&&  ronNumber>=pairnum && ronNumber<pairnum+3      //related to ron//~vawgI~
                && (pair.flag & TDF_CHII)==0)//in hand by flag     //~vawgI~
                {                                                  //~vawgR~
//  		        if (isStraight3Pair(PmaskTypeNum,pair.type,pairnum))  //related to yaku//~vawgR~//~vax2R~
    		        if (isStraight3Pair(true/*swMeld*/,PmaskTypeNum,pair.type,pairnum))  //related to yaku//~vax2I~
                    {                                              //~vawgR~
                		ctrPairRon++;      //pair related to ron   //~vawgR~
	                	if (ronNumber==pairnum+1 ||  pairnum==TN1 && ronNumber==TN3 ||  pairnum==TN7 && ronNumber==TN7)//~vawgR~
                        	swSeqFix=true;    //not ryanmen        //~vawgR~
                    }                                              //~vawgR~
                    else   //not related to yaku but related to ron tile//~vawgR~
                    	swNearRelated=true;                        //~vawgR~
                }                                                  //~vawgR~
	    		if (Dump.Y) Dump.println("UARank.chkEarthStraight3 pair="+pair+",swSeqFix="+swSeqFix+",swNearRelated="+swNearRelated+",ctrPairRon="+ctrPairRon);//~vawgI~
            }                                                      //~vawgR~
    		if (Dump.Y) Dump.println("UARank.chkEarthStraight3 swSeqFix="+swSeqFix+",swNearRelated="+swNearRelated+",ctrPairRon="+ctrPairRon);//~vawgR~
            if (swNearRelated && ctrPairRon!=0)                    //~vawgR~
            	ctrPairRon--;                                      //~vawgR~
            //**ctrPairRon==2:double meld,                         //~vawgR~
			//**ctrpairRon==0:nearRelated & relatedMeld(apply ron to nearrelated and related is in hand)//~vawgR~
            //**              or no related meld in hand(on Earth, it is not last)//~vawgR~
            if (ctrPairRon==2 || ctrPairRon==0)	//doubled pair or not related ron tile//~vawgR~
            	swNotLast=true;                                    //~vawgR~
        }                                                          //~vawgR~
        if (swNotLast)	//ron tile is not related to yaku          //~vawgR~
        {                                                          //~vawgR~
    	  if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vawkI~
          {                                                        //~vawkI~
			if (Dump.Y) Dump.println("UARank.chkEarthStraight3 swNotLast=T,ignore swOther swLast by ctrPairFirst!=0");//~vawkR~
          }                                                        //~vawkI~
          else                                                     //~vawkI~
          {                                                        //~vawkI~
        	if (swOther)                                           //~vawgR~
	            if (ctrPairNotFirst!=0)  //non related earth pair exist//~vawgR~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~vawgR~
          }                                                        //~vawkI~
        }                                                          //~vawgR~
        else      //ron tile related yaku                          //~vawgR~
        {                                                          //~vawgR~
        	if (swSeqFix)    //kanchan etc                         //~vawgR~
            {                                                      //~vawgR~
    		  if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vawkI~
              {                                                    //~vawkI~
			    if (Dump.Y) Dump.println("UARank.chkEarthStraight3 swSeqFix,ignore swOther by ctrPairFirst!=0");//~vawkI~
              }                                                    //~vawkI~
              else                                                 //~vawkI~
              {                                                    //~vawkI~
            	if (swOther)                                       //~vawgR~
	              if (ctrPairNotFirst!=0)  //related earth pair exist after non related//~vawgR~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~vawgR~
                  else                                             //~vawgR~
                  {                                                //~vawkI~
                	swLast=true;                                   //~vawgR~
                  }                                                //~vawkI~
              }                                                    //~vawkI~
            }                                                      //~vawgR~
            else	//ryanmen                                      //~vawgR~
            {                                                      //~vawgR~
                swLast=true;                                       //~vawgR~
                swLastNotFix=true;                                 //~vawgR~
            }                                                      //~vawgR~
        }                                                          //~vawgR~
        rc=setFixErr(RYAKU_STRAIGHT3,swLast,swMiddle);             //~vawgR~
        if (!rc && swTaken && swLastNotFix)	//fix err and last related take//~vawgR~
          if (isMultiWaitTake(ctrPairFirst,ctrPairNotFirst))       //~vawuI~
	    	rc=setMultiWaitTake(RYAKU_STRAIGHT3,swOther);	//allow multiwait take by option//~vawgR~
        if (!rc && swLastNotFix)	//last related is ron or not effective take//~vawgR~
	        setFixErrMultiWait(swOther);                           //~vawgR~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight3 rc="+rc+",ronType="+ronType+",ronNumber="+ronNumber+",ctrPairRon="+ctrPairRon+",ctrPairNotFirst="+ctrPairNotFirst+",swOther="+swOther);//~vawgR~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight3 swTaken="+swTaken+",swNotLast="+swNotLast+",swLastNotFix="+swLastNotFix+",swNearRelated="+swNearRelated+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle+",swSeqFix="+swSeqFix);//~vawgR~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight3 ctrPairFirst="+ctrPairFirst+",swAllowNonRelated="+swForFixFirst_allowNonRelatedAfterRelated);//~vawkI~
    	return rc;                                                 //~vawgR~
    }                                                              //~vawgR~
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
      if (sizePairSeqS>0)                                          //~vawbI~
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
    	if (Dump.Y) Dump.println("UARank.chkTerminalMix rc="+rc+",swTaken="+swTaken+",rankFixErr="+rankFixErr);  //~va11R~//~vakfR~//~vawdR~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va11I~
    //*chanta                                                      //~vawbI~
    //****************************************************************//~vawbI~
    private boolean chkTerminalNum()                                     //~va11I~
    {                                                              //~va11I~
    	boolean rc=true;                                           //~va11I~
        boolean swSeq=false;                                       //~va11I~
      if (sizePairSeqS>0)                                          //~vawbI~
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
      if (sizePairSeqS>0)                                          //~vawbI~
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
    	if (Dump.Y) Dump.println("UARank.chk3Kan rc="+rc+",rankFixErr="+rankFixErr);         //~va11R~//~vawdR~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va91I~
    //*FixFirst chk                                                //~va91I~
    //*rc:false fixFirst/FixMiddle err                             //~va91I~
    //****************************************************************//~va91I~
    private boolean chkEarth3Kan()        //3kan                   //~va91I~
    {                                                              //~va91I~
        int ctrPairNotFirst=0;                                     //~va91R~
        int ctrPairFirst=0;                                        //~vawmI~
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
                else                                               //~vawmI~
                	ctrPairFirst++;                                //~vawmI~
            }                                                      //~va91R~
            else                                                   //~va91R~
                swOther=true;                                      //~va91R~
        }                                                          //~va91R~
      if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vawmI~
      {                                                            //~vawmI~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Kan no swMiddle by ctrPairFirst");//~vawmI~//~vawrR~
      }                                                            //~vawmI~
      else                                                         //~vawmI~
      {                                                            //~vawmI~
        if (ctrPairNotFirst!=0)                                 //~va91I~
            swMiddle=true;                                         //~va91I~
      }                                                            //~vawmI~
        rc=setFixErr(RYAKU_3KAN,swLast,swMiddle);                  //~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Kan ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairNotFirst="+ctrPairNotFirst);//~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Kan rc="+rc+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle);//~va91R~
    	return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //****************************************************************//~va11I~
//*rank3                                                           //~va11I~
    private int chkSameSeq()        //1/2peiko                     //~va11R~
    {                                                              //~va11I~
    	if (Dump.Y) Dump.println("UARank.chkSameSeq intNotAllHand="+intNotAllHand);//~vawbI~
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
    	if (Dump.Y) Dump.println("UARank.chkSameSeq rc="+rc+",ctrSameSeq="+ctrSameSeq+",yaku="+yaku+",rankFixErr="+rankFixErr);//~va11R~//~vawdR~//~vawfR~
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
    	if (Dump.Y) Dump.println("UARank.chkEarthSameSeq sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~vapyI~//~vax1R~
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
    	if (Dump.Y) Dump.println("UARank.chkTerminal rc="+rc+",rankFixErr="+rankFixErr);     //~va11R~//~vawdR~
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
      if (sizePairSeqS>0)                                          //~vawbI~
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
//      	if (typePillow==TT_JI && numberPillow>TT_4ESWN_CTR)    //~va11I~//~vawnR~
        	if (typePillow==TT_JI && numberPillow>=TT_4ESWN_CTR)    //~vawnI~
            {                                                      //~va11I~
	        	addYaku(RYAKU_3DRAGONSMALL);                       //~va11I~
        		rc=RANK_3DRAGONSMALL;                              //~va11I~
//              if (swChkFix)                                      //~va91I~//~vakdR~
//              	if (!chkEarth3DragonSmall())                   //~va91R~//~vakdR~
//                  	rankFixErr+=rc;                            //~va91I~//~vakdR~
                if (swChkFix)                                      //~vawdI~
              		if (!chkEarth3DragonSmall())                   //~vawdI~
                  		rankFixErr+=rc;                            //~vawdI~
            }                                                      //~va11I~
    	}                                                          //~va11I~
    	if (Dump.Y) Dump.println("UARank.chk3DragonSmall rc="+rc+",rankFixErr="+rankFixErr+",ctrDragon="+ctrDragon+",ctrPairNotNum="+ctrPairNotNum);//~va11R~//~vawdR~
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
        boolean swRelated=false;                                   //~vawdI~
        int ctrPairFirst=0;                                        //~vawkR~
    //***************************                                  //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall pairEarth="+Pair.toString(pairEarth));//~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~va91I~
        if (pairEarth==null)                                       //~vawdI~
        {                                                          //~vawdI~
	    	if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall return true by Earth=null");//~vawdI~
        	return true;                                           //~vawdI~
        }                                                          //~vawdI~
	    for (Pair pair:pairEarth)                                  //~va91I~
        {                                                          //~va91R~
        	if (pair==null)                                        //~va91I~
            	continue;                                          //~va91I~
            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~va91R~
                continue;                                          //~va91R~
            if (pair.typePair==PT_NUMSAME && pair.type==TT_JI && pair.number>=TT_4ESWN_CTR)//~va91R~
            {                                                      //~va91R~
                swRelated=true;                                    //~vawdI~
                if (swOther)                                       //~va91R~
                {                                                  //~vawkR~
                    ctrPairNotFirst++;                             //~va91R~//~vawdR~//~vawuR~
                    swMiddle=true;         //NR+R                  //~vawdI~
                }                                                  //~vawkR~
                else                                               //~vawkR~
                	ctrPairFirst++;                                //~vawkR~
//            	if (pair.type==ronType && pair.number==ronNumber)  //~va91R~//~vawdR~//~vawoR~
//              {                                                  //~vawdI~//~vawoR~
//     				if (!(typePillow==TT_JI && numberPillow>=TT_4ESWN_CTR))	//not shanpon with other WGR//~vawdI~//~vawoR~
//                	swLast=true;                               //~vawdR~//~vawoR~
//              }   //never occurs, ex) White on earth and ron by White//~vawoR~
            }                                                      //~va91R~
            else                                                   //~va91R~
            {                                                      //~vawdI~
                swOther=true;                                      //~va91R~
//              if (swRelated)                                     //~vawdI~//~vawoR~
//                  swMiddle=true;          //R+NR                 //~vawdI~//~vawoR~
            }                                                      //~vawdI~
        }                                                          //~va91R~
//      if (ctrPairNotFirst!=0)  //non related earth pair exist    //~va91R~//~vawdR~
//          swMiddle=true;       //OK if YAKUFIX_MIDDLE            //~va91R~//~vawdR~
//      if (!swMiddle)                                             //~va91I~//~vawdR~
//          if (ronType==TT_JI && ronNumber>=TT_4ESWN_CTR)  //ron tile related to yaku//~va91R~//~vawdR~
//          {                                                      //~va91R~//~vawdR~
//              if (!(ronNumber==numberPillow && !swOther))    //fixed at last if ron tile=pillow//~va91I~//~vawdR~
//                  swLast=true;                                   //~va91R~//~vawdR~
//          }                                                      //~va91R~//~vawdR~
        if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vawkM~
        {                                                          //~vawkM~
          	if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall reset swMiddle to allow non related by ctrPairFirst!=0");//~vawkM~//~vawoR~
            swMiddle=false;                                        //~vawkM~
        }                                                          //~vawkM~
//      if (ronType==TT_JI && ronNumber>=TT_4ESWN_CTR)  //ron tile related to yaku//~vawdI~//~vawoR~
//      {                                                          //~vawdI~//~vawoR~
//          if (ronType==typePillow && ronNumber==numberPillow) //tanki pillow//~vawdI~//~vawoR~
//              swLast=false;                                      //~vawdI~//~vawoR~
//      }                                                          //~vawdI~//~vawoR~
        if (ronType==TT_JI && ronNumber>=TT_4ESWN_CTR)  //ron tile related to yaku//~vawoI~
        {                                                          //~vawoI~
//          if (typePillow==TT_JI && numberPillow>=TT_4ESWN_CTR)  //ron by WGR pillow  or shanpon of related//~vawoR~//~vawtR~
            if (typePillow==ronType && numberPillow==ronNumber)  //pillow tanki//~vawtI~
            {                                                      //~vawoI~
          		if (swOther & ctrPairFirst==0)	//non related at 1st//~vawoI~
          		{                                                  //~vawoI~
//                if (swAllInHand || swYakuFixMultiWaitTakeOK) //allow as anko by take//~vawtI~//~vawuR~
//                {                                                //~vawtI~//~vawuR~
//        			if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall tanki, allowed by take ok option swAllInHand="+swAllInHand);//~vawtI~//~vawuR~
//                }                                                //~vawtI~//~vawuR~
//                else                                             //~vawtI~//~vawuR~
//                {                                                //~vawtI~//~vawuR~
          			if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall tanki, fixlast by 1st earth is non related");//~vawoI~//~vawtR~
	                swLast=true;                                   //~vawoI~
//                }                                                //~vawtI~//~vawuR~
          		}                                                  //~vawoI~
            }                                                      //~vawoI~
        }                                                          //~vawoI~
//        else                                                       //~vawoI~//~vawtR~
//        {                                                          //~vawoI~//~vawtR~
//            if (typePillow==TT_JI && numberPillow>=TT_4ESWN_CTR)  //ron by WGR pillow, that is shanpon of WGR and non related//~vawtR~
//            {                                                      //~vawoI~//~vawtR~
//                if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall ron is NR and pillow is WGR, that is all in hand");//~vawoI~//~vawtR~
//            }                                                      //~vawoI~//~vawtR~
//            else                                                   //~vawoI~//~vawtR~
//            if (chkEarth3DragonSmallLast()) //shanpon of related and non related//~vawoI~//~vawtR~
//            {                                                      //~vawoI~//~vawtR~
//                if (swOther & ctrPairFirst==0)  //non related at 1st//~vawoI~//~vawtR~
//                {                                                  //~vawoI~//~vawtR~
//                    if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall last is shanpon of R+NR,fixlast by 1st earth is non related");//~vawoI~//~vawtR~
//                   swLast=true;                                    //~vawoR~//~vawtR~
//                }                                                  //~vawoI~//~vawtR~
//            }                                                      //~vawoI~//~vawtR~
//        }                                                          //~vawoI~//~vawtR~
                                                                   //~vawoI~
        rc=setFixErr(RYAKU_3DRAGONSMALL,swLast,swMiddle);          //~va91I~//~vawdR~
        if (!rc /*fixErr*/ && swTaken && swLast)	//fix err and last related take//~vawdI~
          if (isMultiWaitTake(ctrPairFirst,ctrPairNotFirst))       //~vawuI~
	    	rc=setMultiWaitTake(RYAKU_3DRAGONSMALL,swOther);	//allow multiwait take by option//~vawdI~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall ronType="+ronType+",ronNumber="+ronNumber+",typePillow="+typePillow+",numberPilloe="+numberPillow+",typeYakuFix="+typeYakuFix);//~va91R~//~vawdR~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmall rc="+rc+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle+",ctrPairFirst="+ctrPairFirst);//~vawoR~
    	return rc;                                                 //~va91I~
    }                                                              //~va91I~
    //****************************************************************//~vawoI~
    //*chk ron by shanpon of related and non related               //~vawoI~
    //****************************************************************//~vawoI~
    private boolean chkEarth3DragonSmallLast()                      //~vawoI~
    {                                                              //~vawoI~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmallLast");//~vawoI~
        boolean swRelatedAndNonRelated=false;                      //~vawoI~
        for (int ii=0;ii<sizePairSeqS;ii++)                        //~vawoI~
        {                                                          //~vawoI~
            Pair pair=pairNumS[ii];                                //~vawoI~
            if (!pair.swHand)                                      //~vawoI~
            	continue;	                                       //~vawoI~
            if (pair.typePair==PT_NUMSAME                          //~vawoI~
            &&  pair.type==ronType                                 //~vawoI~
            &&  pair.number==ronNumber                                 //~vawoI~
            )                                                      //~vawoI~
            {                                                      //~vawoI~
		        swRelatedAndNonRelated=true;                       //~vawoI~
            	break;		//ron by non related triples           //~vawoI~
            }                                                      //~vawoI~
        }                                                          //~vawoI~
	    if (Dump.Y) Dump.println("UARank.chkEarth3DragonSmallLast rc="+swRelatedAndNonRelated);//~vawoI~
	    return swRelatedAndNonRelated;                             //~vawoR~
    }                                                              //~vawoI~
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
      if (sizePairSeqS>0)                                          //~vawbI~
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
	//*************************************************************************//~vaw8R~
	//*chk at last for avoid no other fix yaku                     //~vaw8R~
	//*************************************************************************//~vaw8R~
    private int chkSingle(int PtotalRank)        //1tile in hand   //~vaw8R~
    {                                                              //~vaw8R~
       	if (Dump.Y) Dump.println("UARank.chkSingle swChkFix="+swChkFix+",PtotalRank="+PtotalRank+",rankFixErr="+rankFixErr+",pairEarth="+pairEarth);//~vaw8R~
//      if (swChkFix)   //not fixLast                              //~vaw8R~//~vax6R~
//      	if (PtotalRank<=rankFixErr)                            //~vaw8R~//~vax6R~
//      		return 0;                                          //~vaw8R~//~vax6R~
        if (pairEarth==null)                                       //~vaw8R~
        	return 0;                                              //~vaw8R~
    	if (Dump.Y) Dump.println("UARank.chkSingle pairEarth="+Pair.toString(pairEarth));//~vaw8R~
    	if (!RuleSettingYaku.isLocalYakuSingle())                  //~vaw8R~
        	return 0;                                              //~vaw8R~
        int ctrEarth=0;                                            //~vaw8R~
	    for (Pair pair:pairEarth)                                  //~vaw8R~
        {                                                          //~vaw8R~
        	if (pair==null)                                        //~vaw8R~
            	continue;                                          //~vaw8R~
//          if ((pair.flag & TDF_KAN_TAKEN)==0)      //Ankan is not a furo//~vaw8R~//~vax6R~
	            ctrEarth++;                                        //~vaw8R~
        }                                                          //~vaw8R~
        int rc=ctrEarth==PAIRS_MAX ? RANK_SINGLE : 0;                        //~vaw8R~//~vaw9R~
        if (rc>0)                                                  //~vaw8R~
        {                                                          //~vax6I~
 			addYaku(RYAKU_SINGLE);                                 //~vaw8R~
            if (swChkFix)   //not fixLast                          //+vax6I~
         		if (!setFixErr(RYAKU_SINGLE,true/*swLast*/,false/*swMiddle*/))//+vax6R~
                	rankFixErr+=rc;                                //+vax6R~
        }                                                          //~vax6I~
    	if (Dump.Y) Dump.println("UARank.chkSingle rc="+rc+",swTaken="+",rankFixErr="+rankFixErr+",ctrEarth="+ctrEarth);//~vaw8R~//~vax6R~
    	return rc;                                                 //~vaw8R~
    }                                                              //~vaw8R~
	//*************************************************************************//~vaw9I~
	//*no honor 3 wind triples                                     //~vaw9I~
	//*************************************************************************//~vaw9I~
//  private int chk3WindNoHonor(Rank Prank)                        //~vaw9I~//~vawyR~
    private int chk3WindNoHonor()                                  //~vawyI~
    {                                                              //~vaw9I~
       	if (Dump.Y) Dump.println("UARank.chk3WindNoHonor pairNotNum="+Pair.toString(pairNotNum));//~vawaR~
//      if ((Prank.getRank() & (RYAKU_WIND|RYAKU_ROUND))!=0)       //~vawyI~
        Rank rankOther=UARDT.longRankOther;                        //~vawyI~
//      if (rankOther.isContains(RYAKU_ROUND)                      //~vawyI~//~vawzR~
        if ((rankOther.isContains(RYAKU_ROUND) && !RuleSettingYaku.isLocalYaku3WindNoHonorAllowRound())//~vawzI~
        ||  rankOther.isContains(RYAKU_WIND))                       //~vawyI~
        {                                                          //~vaw9I~
	       	if (Dump.Y) Dump.println("UARank.chk3WindNoHonor rc=0 by Honor Wind or Round rankOther="+Rank.toStringName(rankOther));//~vaw9I~//~vawyR~
        	return 0;                                              //~vaw9I~
        }                                                          //~vaw9I~
                                                                   //~vax0I~
    	if (!RuleSettingYaku.isLocalYaku3WindNoHonor())            //~vaw9I~
        	return 0;                                              //~vaw9I~
        //**                                                       //~vaw9I~
        int ctr=0;                                                 //~vaw9I~
        boolean swLast=false;                                      //~vaw9I~
        if (ctrPairNotNum!=0)                                      //~vaw9I~
            for (Pair pair:pairNotNum)     //earth and hand        //~vaw9I~
            {                                                      //~vaw9I~
                if (pair.type==TT_JI && pair.number<TT_4ESWN_CTR)  //~vaw9I~
                {                                                  //~vaw9I~
                    ctr++;                                         //~vaw9I~
	                if (pair.swHand && pair.type==ronType && pair.number==ronNumber)//~vaw9R~
		                swLast=true;                               //~vaw9I~
                }                                                  //~vaw9I~
            }                                                      //~vaw9I~
        if (ctr!=3)                                                //~vaw9I~
        {                                                          //~vaw9I~
	       	if (Dump.Y) Dump.println("UARank.chk3WindNoHonor rc=0 by pair ctr="+ctr);//~vaw9I~
        	return 0;                                              //~vaw9I~
        }                                                          //~vaw9I~
//      int rc=RANK_3WIND_NOHONOR;                                 //~vaw9I~//~vawzR~
        int rc=RuleSettingYaku.getLocalYaku3WindNoHonorHan();      //~vawzI~
        if (swChkFix)                                              //~vaw9I~
            if (!chkEarth3WindNoHonor(swLast))                     //~vaw9R~
            {                                                      //~vaw9I~
                rankFixErr+=rc;                                    //~vaw9I~
                if (swFixErrMultiWait)                             //~vaw9I~
                    rankFixErrMultiWait+=rc;                       //~vaw9I~
            }                                                      //~vaw9I~
    //**                                                           //~vaw9I~
		addYaku(RYAKU_3WIND_NOHONOR);                              //~vaw9R~
    	if (Dump.Y) Dump.println("UARank.chk3WindNoHonor rc="+rc+",rankFixErr="+rankFixErr); //~vaw9I~//~vawdR~
    	return rc;                                                 //~vaw9I~
    }                                                              //~vaw9I~
    //****************************************************************//~vaw9I~
    //*FixFirst chk                                                //~vaw9I~
    //*rc:false fixFirst/FixMiddle err                             //~vaw9I~
    //****************************************************************//~vaw9I~
    private boolean chkEarth3WindNoHonor(boolean PswLast)        //3shiki//~vaw9R~
    {                                                              //~vaw9I~
        int ctrPairNotFirst=0;                                     //~vaw9I~
        int ctrPairFirst=0;                                        //~vawmI~
        boolean swOther=false,swLast=false,swMiddle=false,rc=true; //~vaw9I~
    //***************************                                  //~vaw9I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3WindNoHonor PswLast="+PswLast+",pairEarth="+Pair.toString(pairEarth));//~vaw9R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3WindNoHonor sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~vaw9I~
        swLast=PswLast;                                            //~vaw9I~
	    for (Pair pair:pairEarth)                                  //~vaw9I~
        {                                                          //~vaw9I~
        	if (pair==null)                                        //~vaw9I~
            	continue;                                          //~vaw9I~
            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~vaw9I~
                continue;                                          //~vaw9I~
            if (pair.type==TT_JI && pair.number<TT_4ESWN_CTR)      //~vaw9I~
            {                                                      //~vaw9I~
                if (swOther)                                       //~vaw9I~
                    ctrPairNotFirst++;                             //~vaw9I~
                else                                               //~vawmI~
                	ctrPairFirst++;                                //~vawmI~
            }                                                      //~vaw9I~
            else                                                   //~vaw9I~
                swOther=true;                                      //~vaw9I~
        }                                                          //~vaw9I~
      if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vawmR~
      {                                                            //~vawmI~
    	if (Dump.Y) Dump.println("UARank.chkEarth3WindNoHonor no swMiddle by ctrPairFirst");//~vawmI~
      }                                                            //~vawmI~
      else                                                         //~vawmI~
      {                                                            //~vawmI~
        if (ctrPairNotFirst!=0)  //non related earth pair exist    //~vaw9I~
            swMiddle=true;       //OK if YAKUFIX_MIDDLE            //~vaw9I~
      }                                                            //~vawmI~
        rc=setFixErr(RYAKU_3WIND_NOHONOR,swLast,swMiddle);          //~vaw9I~
        if (!rc && swTaken && swLast)	//fix err and last related take//~vaw9I~
          if (isMultiWaitTake(ctrPairFirst,ctrPairNotFirst))       //~vawuI~
	    	rc=setMultiWaitTake(RYAKU_3WIND_NOHONOR,swOther);	//allow multiwait take by option//~vaw9I~
        if (!rc &&  swLast)	//last related is ron or not effective take//~vaw9I~
	        setFixErrMultiWait(swOther);                           //~vaw9I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3WindNoHonor ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairNotFirst="+ctrPairNotFirst);//~vaw9I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3WindNoHonor rc="+rc+",swLast="+swLast+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle);//~vaw9I~
    	return rc;                                                 //~vaw9I~
    }                                                              //~vaw9I~
	//*************************************************************************//~vax0I~
	//*Big 3 wind triples(allows Round and self wind)              //~vax0I~
	//*************************************************************************//~vax0I~
    private int chk3Winds()                                        //~vax0I~
    {                                                              //~vax0I~
       	if (Dump.Y) Dump.println("UARank.chk3Winds pairNotNum="+Pair.toString(pairNotNum));//~vax0I~
    	if (!RuleSettingYaku.isLocalYaku3Wind())                   //~vax0I~
        	return 0;                                              //~vax0I~
        int ctr=0;                                                 //~vax0I~
        boolean swLast=false;                                      //~vax0I~
        if (ctrPairNotNum!=0)                                      //~vax0I~
            for (Pair pair:pairNotNum)     //earth and hand        //~vax0I~
            {                                                      //~vax0I~
                if (pair.type==TT_JI && pair.number<TT_4ESWN_CTR)  //~vax0I~
                {                                                  //~vax0I~
                    ctr++;                                         //~vax0I~
	                if (pair.swHand && pair.type==ronType && pair.number==ronNumber)//~vax0I~
		                swLast=true;                               //~vax0I~
                }                                                  //~vax0I~
            }                                                      //~vax0I~
        if (ctr!=3)                                                //~vax0I~
        {                                                          //~vax0I~
	       	if (Dump.Y) Dump.println("UARank.chk3Winds rc=0 by pair ctr="+ctr);//~vax0I~
        	return 0;                                              //~vax0I~
        }                                                          //~vax0I~
		addYaku(RYAKU_3WIND);                                      //~vax0I~
        int rc=RANK_3WIND;                                         //~vax0I~
        if (swChkFix)                                              //~vax0I~
            if (!chkEarth3Winds(swLast))                           //~vax0I~
            {                                                      //~vax0I~
                rankFixErr+=rc;                                    //~vax0I~
                if (swFixErrMultiWait)                             //~vax0I~
                    rankFixErrMultiWait+=rc;                       //~vax0I~
            }                                                      //~vax0I~
    	if (Dump.Y) Dump.println("UARank.chk3Winds rc="+rc+",rankFixErr="+rankFixErr);//~vax0I~
    	return rc;                                                 //~vax0I~
    }                                                              //~vax0I~
    //****************************************************************//~vax0I~
    //*FixFirst chk                                                //~vax0I~
    //*rc:false fixFirst/FixMiddle err                             //~vax0I~
    //****************************************************************//~vax0I~
    private boolean chkEarth3Winds(boolean PswLast)        //3shiki//~vax0I~
    {                                                              //~vax0I~
        int ctrPairNotFirst=0;                                     //~vax0I~
        int ctrPairFirst=0;                                        //~vax0I~
        boolean swOther=false,swLast=false,swMiddle=false,rc=true; //~vax0I~
    //***************************                                  //~vax0I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Winds PswLast="+PswLast+",pairEarth="+Pair.toString(pairEarth));//~vax0I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Winds sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~vax0I~
        swLast=PswLast;                                            //~vax0I~
	    for (Pair pair:pairEarth)                                  //~vax0I~
        {                                                          //~vax0I~
        	if (pair==null)                                        //~vax0I~
            	continue;                                          //~vax0I~
            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~vax0I~
                continue;                                          //~vax0I~
            if (pair.type==TT_JI && pair.number<TT_4ESWN_CTR)      //~vax0I~
            {                                                      //~vax0I~
                if (swOther)                                       //~vax0I~
                    ctrPairNotFirst++;                             //~vax0I~
                else                                               //~vax0I~
                	ctrPairFirst++;                                //~vax0I~
            }                                                      //~vax0I~
            else                                                   //~vax0I~
                swOther=true;                                      //~vax0I~
        }                                                          //~vax0I~
      if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vax0I~
      {                                                            //~vax0I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Winds no swMiddle by ctrPairFirst");//~vax0I~
      }                                                            //~vax0I~
      else                                                         //~vax0I~
      {                                                            //~vax0I~
        if (ctrPairNotFirst!=0)  //non related earth pair exist    //~vax0I~
            swMiddle=true;       //OK if YAKUFIX_MIDDLE            //~vax0I~
      }                                                            //~vax0I~
        rc=setFixErr(RYAKU_3WIND,swLast,swMiddle);                 //~vax0I~
        if (!rc && swTaken && swLast)	//fix err and last related take//~vax0I~
          if (isMultiWaitTake(ctrPairFirst,ctrPairNotFirst))       //~vax0I~
	    	rc=setMultiWaitTake(RYAKU_3WIND,swOther);	//allow multiwait take by option//~vax0I~
        if (!rc &&  swLast)	//last related is ron or not effective take//~vax0I~
	        setFixErrMultiWait(swOther);                           //~vax0I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Winds ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairNotFirst="+ctrPairNotFirst);//~vax0I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Winds rc="+rc+",swLast="+swLast+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle);//~vax0I~
    	return rc;                                                 //~vax0I~
    }                                                              //~vax0I~
    //****************************************************************//~vawvI~
    private int chk3SeqNum()           //3renpon                   //~vawvI~
    {                                                              //~vawvI~
     	int numSeq=-1,typeSeq=-1;                                  //~vawvI~
    //*************************                                    //~vawvI~
	    if (Dump.Y) Dump.println("UARank.chk3SeqNum entry");       //~vawvI~
        if (swPinfu)                                               //~vawvI~
        {                                                          //~vawvI~
	    	if (Dump.Y) Dump.println("UARank.chk3SeqNum pinfu rc=0");//~vawvI~
        	return 0;                                              //~vawvI~
        }                                                          //~vawvI~
    	if (!RuleSettingYaku.isLocalYaku3SeqNum())                 //~vawvI~
        	return 0;                                              //~vawvI~
        int rc=0;                                                  //~vawvI~
        int bitNum;                                                //~vawvI~
        for (int ii=0;ii<sizePairSeqS && ii<2;ii++)                //~vawvR~
        {                                                          //~vawvI~
        	Pair pair1,pair2;                                      //~vawvI~
            pair1=pairNumS[ii];                                    //~vawvI~
            if (Dump.Y) Dump.println("UARank.chk3SeqNum ii="+ii+",pair1="+pair1);//~vawvI~
        	if (pair1.typePair==PT_NUMSEQ)                         //~vawvI~
            	continue;                                          //~vawvI~
            numSeq=pair1.number;                                   //~vawvR~
            typeSeq=pair1.type;                                    //~vawvI~
            bitNum=(1<<numSeq);                                    //~vawvI~
            int ctr=0;                                             //~vawvR~
            for (int jj=ii+1;jj<sizePairSeqS;jj++)                 //~vawvR~
            {                                                      //~vawvR~
                pair2=pairNumS[jj];                                //~vawvR~
	            if (Dump.Y) Dump.println("UARank.chk3SeqNum jj="+jj+",pair2="+pair2);//~vawvR~
	        	if (pair2.typePair==PT_NUMSEQ)                     //~vawvR~
    	        	continue;                                      //~vawvI~
                if (pair2.type!=typeSeq)                           //~vawvR~
    	        	continue;                                      //~vawvI~
                ctr++;                                             //~vawvR~
	            bitNum|=(1<<pair2.number);                         //~vawvR~
            }                                                      //~vawvR~
            if (Dump.Y) Dump.println("UARank.chk3SeqNum pair1="+pair1+",ctr="+ctr+",bitNum="+Integer.toHexString(bitNum));//~vawvM~
            if (ctr!=2)                                            //~vawvI~
            	continue;                                          //~vawvI~
            boolean swFound=false;                                 //~vawvI~
            for (int kk=TN1;kk<=TN7;kk++)                               //~vawvI~
            {                                                      //~vawvI~
            	if (((bitNum>>kk) & 0x07)==0x07)	//3 cont num   //~vawvI~
                {                                                  //~vawvI~
                	swFound=true;	                               //~vawvI~
                	numSeq=kk;	//startNum                         //~vawvI~
                    break;                                         //~vawvI~
				}                                                  //~vawvI~
            }                                                      //~vawvI~
            if (swFound)                                           //~vawvI~
            {                                                      //~vawvI~
            	addYaku(RYAKU_3SEQNUM);                            //~vawvR~
            	rc=RANK_3SAMENUM;                                  //~vawvI~
	            if (Dump.Y) Dump.println("UARank.chk3SeqNum found num="+numSeq+",type="+typeSeq);//~vawvI~
            	break;                                             //~vawvI~
            }                                                      //~vawvI~
        }                                                          //~vawvI~
        if (rc!=0)                                                 //~vawvI~
        {                                                          //~vawvI~
            if (swChkFix)                                          //~vawvI~
	            if (!chkEarth3SeqNum(typeSeq,numSeq))              //~vawvR~
                {                                                  //~vawvI~
                	rankFixErr+=rc;                                //~vawvI~
                    if (swFixErrMultiWait)                         //~vawvI~
                		rankFixErrMultiWait+=rc;                   //~vawvI~
                }                                                  //~vawvI~
        }                                                          //~vawvI~
    	if (Dump.Y) Dump.println("UARank.chk3SeqNum rc="+rc+",swMultiWaitErr="+swFixErrMultiWait+",rankFixErr="+rankFixErr+",rankFixerrMultiWait="+rankFixErrMultiWait);//~vawvI~
    	return rc;                                                 //~vawvR~
    }                                                              //~vawvI~
    //****************************************************************//~vawvI~
    //*FixFirst chk                                                //~vawvI~
    //*rc:false fixFirst/FixMiddle err                             //~vawvI~
    //****************************************************************//~vawvI~
    private boolean chkEarth3SeqNum(int Ptype,int Pnum/*top of 3Seq*/)//~vawvR~
    {                                                              //~vawvI~
        int ctrPairNotFirst=0;                                     //~vawvI~
        int ctrPairFirst=0;                                        //~vawvI~
        boolean swOther=false,swMiddle=false,rc=true; //~vawvI~
    //***************************                                  //~vawvI~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SeqNum type="+Ptype+",num="+Pnum);//~vawvR~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SeqNum pairEarth="+Pair.toString(pairEarth));//~vawvI~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SeqNum sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~vawvI~
	    for (Pair pair:pairEarth)                                  //~vawvI~
        {                                                          //~vawvI~
        	if (pair==null)                                        //~vawvI~
            	continue;                                          //~vawvI~
            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~vawvI~
                continue;                                          //~vawvI~
            if (pair.typePair==PT_NUMSAME && pair.type==Ptype && pair.number>=Pnum && pair.number<Pnum+3)//~vawvR~
            {                                                      //~vawvI~
                if (swOther)                                       //~vawvI~
                    ctrPairNotFirst++;                             //~vawvI~
                else                                               //~vawvI~
                	ctrPairFirst++;                                //~vawvI~
            }                                                      //~vawvI~
            else                                                   //~vawvI~
                swOther=true;                                      //~vawvI~
        }                                                          //~vawvI~
        boolean swLast=(ronType==Ptype && (ronNumber>=Pnum && ronNumber<Pnum+3));//~vawvR~
     	if (swLast)                                                //~vax5I~
        {                                                          //~vax5I~
            if (sizePairSeqS>0)                                    //~vax5I~
            {                                                      //~vax5I~
                for (Pair pair:pairNumS)                           //~vax5I~
                {                                                  //~vax5I~
                    if (Dump.Y) Dump.println("UARank.chkEarth3SeqNum pair="+Pair.toString(pair));//~vax5R~
                    if (pair.typePair==PT_NUMSEQ && pair.swHand)    //not earth//~vax5I~
                    {                                              //~vax5I~
                        if (ronType==pair.type && ronNumber>=pair.number && ronNumber<pair.number+3)//~vax5R~
                        {                                          //~vax5I~
		                    if (Dump.Y) Dump.println("UARank.chkEarth3SeqNum reset swLast by DupSeq Meld");//~vax5I~
                            swLast=false;                          //~vax5I~
                            break;                                 //~vax5I~
                        }                                          //~vax5I~
                    }                                              //~vax5I~
                }                                                  //~vax5I~
            }                                                      //~vax5I~
        }                                                          //~vax5I~
        if (ctrPairNotFirst!=0)  //non related earth pair exist    //~vawvI~
            swMiddle=true;       //OK if YAKUFIX_MIDDLE            //~vawvI~
        rc=setFixErr(RYAKU_3SEQNUM,swLast,swMiddle);              //~vawvI~//~vax1R~
        if (!rc && swTaken && swLast)	//fix err and last related take//~vawvR~
          if (isMultiWaitTake(ctrPairFirst,ctrPairNotFirst))       //~vawvI~
	    	rc=setMultiWaitTake(RYAKU_3SEQNUM,swOther);	//allow multiwait take by option//~vawvR~
        if (!rc &&  swLast)	//last related is ron or not effective take//~vawvR~
	        setFixErrMultiWait(swOther);                           //~vawvI~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SeqNum Pnum="+Pnum+",ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairNotFirst="+ctrPairNotFirst);//~vawvI~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SeqNum typePillow="+typePillow+",numPillow="+numberPillow);//~vawvI~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SeqNum rc="+rc+",swLast="+swLast+",swOther="+swOther+",swMiddle="+swMiddle);//~vawvI~//~vax5R~
    	return rc;                                                 //~vawvI~
    }                                                              //~vawvI~
    //****************************************************************//~vax1I~
    //*1shiki 3jun:Pure Triple Chow                                //~vax1I~
    //****************************************************************//~vax1I~
    private int chk3DupSeq()                                       //~vax1R~
    {                                                              //~vax1I~
     	int numSeq=-1,typeSeq=-1;                                  //~vax1I~
    //*************************                                    //~vax1I~
	    if (Dump.Y) Dump.println("UARank.chk3DupSeq entry");       //~vax1R~
    	if (!RuleSettingYaku.isLocalYaku3DupSeq())                 //~vax1R~
        	return 0;                                              //~vax1I~
        int rc=0;                                                  //~vax1I~
    	boolean swOpenOK=RuleSettingYaku.isLocalYaku3DupSeqAllowOpen();//~vax1I~
        boolean swOpen;                                            //~vax1I~
        for (int ii=0;ii<sizePairSeqS && ii<2;ii++)                //~vax1I~
        {                                                          //~vax1I~
        	Pair pair1,pair2;                                      //~vax1I~
            pair1=pairNumS[ii];                                    //~vax1I~
            if (Dump.Y) Dump.println("UARank.chk3DupSeq ii="+ii+",pair1="+pair1);//~vax1R~
        	if (pair1.typePair!=PT_NUMSEQ)                         //~vax1R~
            	continue;                                          //~vax1I~
            swOpen=!pair1.swHand;                                  //~vax1I~
            if (!swOpenOK && swOpen)                               //~vax1I~
            	continue;                                          //~vax1I~
            numSeq=pair1.number;                                   //~vax1I~
            typeSeq=pair1.type;                                    //~vax1I~
            int ctr=0;                                             //~vax1I~
            for (int jj=ii+1;jj<sizePairSeqS;jj++)                 //~vax1I~
            {                                                      //~vax1I~
                pair2=pairNumS[jj];                                //~vax1I~
	            if (Dump.Y) Dump.println("UARank.chk3DupSeq jj="+jj+",pair2="+pair2);//~vax1R~
	        	if (pair2.typePair!=PT_NUMSEQ)                     //~vax1R~
    	        	continue;                                      //~vax1I~
	            swOpen=!pair2.swHand;                              //~vax1R~
	            if (!swOpenOK && swOpen)                           //~vax1I~
    	        	continue;                                      //~vax1I~
                if (pair2.type!=typeSeq || pair2.number!=numSeq)   //~vax1R~
    	        	continue;                                      //~vax1I~
                ctr++;                                             //~vax1I~
            }                                                      //~vax1I~
            if (Dump.Y) Dump.println("UARank.chk3DupSeq pair1="+pair1+",ctr="+ctr);//~vax1R~
            if (ctr==2)                                            //~vax1R~
            {                                                      //~vax1I~
	            addYaku(RYAKU_3DUPSEQ);                            //~vax1I~
//  	        rc=RANK_3DUPSEQ;                                   //~vax1R~
    	        Point p=RuleSettingYaku.getLocalYaku3DupSeqHan();   //~vax1I~
    	        rc=p.x;                                            //~vax1I~
	        	rc-=intNotAllHand;                                 //~vax1I~
        	    if (Dump.Y) Dump.println("UARank.chk3SeqNum found num="+numSeq+",type="+typeSeq);//~vax1I~
            		break;                                         //~vax1R~
            }                                                      //~vax1I~
        }                                                          //~vax1I~
        if (rc!=0)                                                 //~vax1I~
        {                                                          //~vax1I~
            if (swChkFix)                                          //~vax1I~
	            if (!chkEarth3DupSeq(typeSeq,numSeq))              //~vax1R~
                {                                                  //~vax1I~
                	rankFixErr+=rc;                                //~vax1I~
                    if (swFixErrMultiWait)                         //~vax1I~
                		rankFixErrMultiWait+=rc;                   //~vax1I~
                }                                                  //~vax1I~
        }                                                          //~vax1I~
    	if (Dump.Y) Dump.println("UARank.chk3DupSeq rc="+rc+",swMultiWaitErr="+swFixErrMultiWait+",rankFixErr="+rankFixErr+",rankFixerrMultiWait="+rankFixErrMultiWait);//~vax1R~
    	return rc;                                                 //~vax1I~
    }                                                              //~vax1I~
    //****************************************************************//~vax1I~
    //*FixFirst chk                                                //~vax1I~
    //*rc:false fixFirst/FixMiddle err                             //~vax1I~
    //****************************************************************//~vax1I~
    private boolean chkEarth3DupSeq(int Ptype,int Pnum/*top of 3Seq*/)//~vax1R~
    {                                                              //~vax1I~
        int ctrPairNotFirst=0,ctrPairRon=0,pairnum,pairtype;       //~vax1R~
        boolean swNotLast=false,swSeqFix=false,swOther=false,swLast=false,swMiddle=false,rc=true;//~vax1I~
        boolean swNearRelated=false;                               //~vax1I~
        boolean swLastNotFix=false;                                //~vax1I~
        int ctrPairFirst=0;                                        //~vax1I~
    //***************************                                  //~vax1I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DupSeq pairEarth="+Pair.toString(pairEarth));//~vax1I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DupSeq sizePairSeqS="+sizePairSeqS+",pairNumS="+Pair.toString(pairNumS));//~vax1I~
        //*chk earth same pair as ron tile                         //~vax1I~
	    for (Pair pair:pairEarth)                                  //~vax1I~
        {                                                          //~vax1I~
        	if (pair==null)                                        //~vax1I~
            	continue;                                          //~vax1I~
            pairnum=pair.number;                                   //~vax1I~
            pairtype=pair.type;                                    //~vax1I~
            if ((pair.flag & TDF_KAN_TAKEN)!=0)      //Ankan is not a furo//~vax1I~
                continue;                                          //~vax1I~
            if (pair.typePair==PT_NUMSEQ && pairnum==Pnum && pairtype==Ptype)	//related to yaku//~vax1R~
            {                                                      //~vax1I~
                if (swOther)                                       //~vax1I~
                    ctrPairNotFirst++;                             //~vax1I~
                else                                               //~vax1I~
                    ctrPairFirst++;                                //~vax1I~
//              if (pairtype==ronType && (ronNumber>=pairnum && ronNumber<pairnum+3))//~vax1R~
//              	swNotLast=true;    //pair related of ron tile is already on earth, not swLast case//~vax1R~
            }                                                      //~vax1I~
            else                                                   //~vax1I~
                swOther=true;                                      //~vax1I~
        }                                                          //~vax1I~
        if (!(ronType==Ptype && ronNumber>=Pnum && ronNumber<Pnum+3))      //not related to ron//~vax1R~
            swNotLast=true;                                        //~vax1I~
        if (!swNotLast)	//pair caontains rontile is not on Earth   //~vax1I~
        {                                                          //~vax1I~
            //*search dup in hands                                 //~vax1I~
            for (int ii=0;ii<sizePairSeqS;ii++)                    //~vax1I~
            {                                                      //~vax1I~
                Pair pair=pairNumS[ii];                            //~vax1I~
                pairnum=pair.number;                               //~vax1I~
                if (pair.typePair==PT_NUMSEQ && pair.type==ronType 	//related to yaku//~vax1I~
 				&&  ronNumber>=pairnum && ronNumber<pairnum+3      //related to ron//~vax1I~
                && (pair.flag & TDF_CHII)==0)//in hand by flag     //~vax1I~
                {                                                  //~vax1I~
                	if (pairnum==Pnum)  //meld for yaku            //~vax1I~
                    {                                              //~vax1I~
                        ctrPairRon++;      //pair related to ron   //~vax1I~
                        if (ronNumber==pairnum+1 ||  pairnum==TN1 && ronNumber==TN3 ||  pairnum==TN7 && ronNumber==TN7)//~vax1I~
                            swSeqFix=true;    //not ryanmen        //~vax1I~
                    }                                              //~vax1I~
                    else   //not related to yaku but related to ron tile//~vax1I~
                    	swNearRelated=true;                        //~vax1I~
                }                                                  //~vax1I~
            }                                                      //~vax1I~
//          if (swNearRelated && ctrPairRon!=0)                    //~vax1R~
//          	ctrPairRon--;                                      //~vax1R~
//          if (ctrPairRon==2 || ctrPairRon==0)	//doubled pair or not related ron tile//~vax1R~
//          	swNotLast=true;                                    //~vax1R~
            if (swNearRelated)                                     //~vax1I~
            	swNotLast=true;  //apply ron for non-related meld  //~vax1I~
        }                                                          //~vax1I~
        if (swNotLast)	//ron tile is not related to yaku          //~vax1I~
        {                                                          //~vax1I~
    	  if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vax1I~
          {                                                        //~vax1I~
			if (Dump.Y) Dump.println("UARank.chkEarth3DupSeq swNotLast=T,ignore swOther swLast by ctrPairFirst!=0");//~vax1I~
          }                                                        //~vax1I~
          else                                                     //~vax1I~
          {                                                        //~vax1I~
        	if (swOther)                                           //~vax1I~
	            if (ctrPairNotFirst!=0)  //non related earth pair exist//~vax1I~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~vax1I~
          }                                                        //~vax1I~
        }                                                          //~vax1I~
        else      //ron tile related yaku                          //~vax1I~
        {                                                          //~vax1I~
        	if (swSeqFix)    //kanchan etc                         //~vax1I~
            {                                                      //~vax1I~
    		  if (swForFixFirst_allowNonRelatedAfterRelated && ctrPairFirst!=0)//~vax1I~
              {                                                    //~vax1I~
			    if (Dump.Y) Dump.println("UARank.chkEarth3DupSeq swSeqFix,ignore swOther by ctrPairFirst!=0");//~vax1I~
              }                                                    //~vax1I~
              else                                                 //~vax1I~
              {                                                    //~vax1I~
            	if (swOther)                                       //~vax1I~
	              if (ctrPairNotFirst!=0)  //related earth pair exist after non related//~vax1I~
    	            swMiddle=true;       //OK if YAKUFIX_MIDDLE    //~vax1I~
                  else                                             //~vax1I~
                	swLast=true;                                   //~vax1I~
              }                                                    //~vax1I~
            }                                                      //~vax1I~
            else	//ryanmen                                      //~vax1I~
            {                                                      //~vax1I~
                swLast=true;                                       //~vax1I~
                swLastNotFix=true;                                 //~vax1I~
            }                                                      //~vax1I~
        }                                                          //~vax1I~
        rc=setFixErr(RYAKU_3DUPSEQ,swLast,swMiddle);               //~vax1R~
        if (!rc && swTaken && swLastNotFix)	//fix err and last related take//~vax1I~
          if (isMultiWaitTake(ctrPairFirst,ctrPairNotFirst))       //~vax1I~
	    	rc=setMultiWaitTake(RYAKU_3DUPSEQ,swOther);	//allow multiwait take by option//~vax1R~
        if (!rc && swLastNotFix)	//last related is ron or not effective take//~vax1I~
	        setFixErrMultiWait(swOther);                           //~vax1I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DupSeq rc="+rc+",Pnum="+Pnum+",ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairRon="+ctrPairRon+",ctrPairNotFirst="+ctrPairNotFirst+",swOther="+swOther);//~vax1I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3DupSeq swTaken="+swTaken+",swLastNotFix="+swLastNotFix+",swNotLast="+swNotLast+",swNearRelated="+swNearRelated+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle+",swSeqFix="+swSeqFix);//~vax1I~
    	return rc;                                                 //~vax1I~
    }                                                              //~vax1I~
}//class                                                           //~v@@@R~
