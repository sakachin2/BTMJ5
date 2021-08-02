//*CID://+vaaQR~: update#= 901;                                    //+vaaQR~
//**********************************************************************//~v101I~
//2021/07/17 vaaQ (Bug)honchan decision; not cheked pillow is terminal//+vaaQI~
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
    private int  rankFixErr;                                       //~va91I~
    private int idxPairNumSS;                                         //~va11R~
    private int statusPillow,typePillow,numberPillow;              //~va11R~
    private boolean swTaken;                                       //~va11I~
    private int ronNumber,ronType;                                 //~va11I~
    private Pair[] pairEarth;                                      //~va91I~
    private int typeYakuFix,eswnHonor,roundHonor;                                       //~va91I~
    private Rank longRankFixErr;                                   //~va91R~
    private boolean swAllInHand;                                   //~va91I~
    private boolean swChkFix;                                      //~va91I~
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
    	typeYakuFix=RuleSettingYaku.getYakuFix();                   //~va91I~
        swAllInHand=UARV.swAllInHand;                               //~va91I~
        intNotAllHand=UARV.swAllInHand ? 0 : 1;                    //~va11R~
        player=UARV.player;                                        //~va11I~
        ronType=UARV.ronType;                                      //~va11I~
        ronNumber=UARV.ronNumber;                                  //~va11I~
        pairEarth=UARV.pairEarth;                                  //~va91M~
        if (Dump.Y) Dump.println("UARank.init typeYAkuFix="+typeYakuFix+",intNotAllHand="+intNotAllHand+",ronType="+ronType+",ronNumber="+ronNumber+",pairEarth count="+pairEarth.length);//~va91I~
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
     	longRankS=new Rank[sizePairNumSS];                         //~va11R~
     	longRankFixErrS=new Rank[sizePairNumSS];                   //~va91I~
        idxPairNumSS=0;                                            //~va11R~
//      swChkFix=(!swAllInHand && UARV.typeYakuFix!=YAKUFIX_LAST); //~va91R~
//      swChkFix=(UARV.typeYakuFix!=YAKUFIX_LAST) && !(swAllInHand && swTake);//~va91R~
        swChkFix=typeYakuFix!=YAKUFIX_LAST; //Later Take on allInHand is cheked and it is OK by 1han of RANK_TAKE_NOEARTH//~va91R~
        for (Pair pairS[]:pairNumSS)                               //~va11R~
        {                                                          //~va11I~
            longRank=new Rank();                                   //~va11R~
            longRankFixErr=new Rank();                                 //~va91I~
        	int rank=0;                                            //~va91R~
        	rankFixErr=0;                                          //~va91I~
        	if (pairS!=null)                                       //~va11I~
        		rank=setRank(pairS);                               //~va11R~
        	intRankS[idxPairNumSS]=rank;                           //~va11R~
        	intRankFixErrS[idxPairNumSS]=rankFixErr;               //~va91I~
        	longRankS[idxPairNumSS]=longRank;                         //~va11R~
        	longRankFixErrS[idxPairNumSS]=longRankFixErr;          //~va91I~
            idxPairNumSS++;                                        //~va11R~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARank.setRank rankS="+ Arrays.toString(intRankS)+",longRankFixErrS="+Rank.toString(longRankFixErrS)+",longRankS="+Rank.toString(longRankS)+",longRankFixErrS="+Rank.toString(longRankFixErrS));//~va11R~//~va91R~
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
        return rc;//~va91I~
    }                                                              //~va91I~
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
        if (swChkFix)                                              //~va91R~
    		chkEarthHonorTile();                                   //~va91R~
    //rank1                                                        //~va11M~
//      rank+=chkSameSeq();         //1peiko  by chkSameSeq2       //~va11R~
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
        rank+=chk3DragonSmall();    //shosangen                    //~va11R~
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
    //****************************************************************//~va91I~
    //*FixFirst chk                                                //~va91I~
    //*rc:false fixFirst/FixMiddle err                             //~va91I~
    //****************************************************************//~va91I~
    private boolean chkEarthHonorTile()	//Wind,Round,WGR           //~va91I~
    {                                                              //~va91I~
        int ctrPairNotFirst=0,ctrPairFirst=0;                      //~va91R~
        boolean swOther=false,swLast=false,swMiddle=false,rc;      //~va91R~
        int ctrWGR;                                                //~va91I~
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
    						if (Dump.Y) Dump.println("UARank.chkEarthHonorTile swAllInHand="+swAllInHand+",swMiddle="+swMiddle+",typePillow="+typePillow+",numberPillow="+numberPillow);//~va91R~
                        	if (!swMiddle)                         //~va91I~
                            {                                      //~va91I~
	                            swLast=true;                       //~va91R~
                                if (swAllInHand)                   //~va91I~
                					if (typePillow==TT_JI && (numberPillow>=TT_4ESWN_CTR || numberPillow==eswnHonor|| numberPillow==roundHonor))//~va91I~
                                    	swLast=false;	//menzen shanpon//~va91I~
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
        if (rankOther.isContains(RYAKU_ROUND))                     //~va91R~
        {                                                          //~va91I~
	        if (!setFixErr(RYAKU_ROUND,swLast,swMiddle))           //~va91I~
            	han++;                                             //~va91I~
        }                                                          //~va91I~
        if (rankOther.isContains(RYAKU_WIND))                      //~va91R~
        {                                                          //~va91I~
	        if (!setFixErr(RYAKU_WIND,swLast,swMiddle))            //~va91I~
	            han++;                                             //~va91I~
        }                                                          //~va91I~
        if (ctrWGR!=0)                                             //~va91I~
        {                                                          //~va91I~
	        if (!setFixErrWGR(swLast,swMiddle))                    //~va91R~
	            han++;                                             //~va91I~
        }                                                          //~va91I~
        rankFixErr+=han;
        rc=han==0;                                                 //~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairFirst="+ctrPairFirst+",ctrPairNotFirst="+ctrPairNotFirst);//~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarthHonorTile rc="+rc+",han="+han+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle);//~va91R~
    	return rc;                                                 //~va91I~
    }                                                              //~va91I~
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
                	rankFixErr+=rc;                                //~va91I~
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
                	swLast=true;                                   //~va91I~
            }                                                      //~va91I~
            else	//ryanmen                                      //~va91I~
            {                                                      //~va91I~
                swLast=true;                                       //~va91I~
            }                                                      //~va91I~
        }                                                          //~va91I~
        rc=setFixErr(RYAKU_3SAMESEQ,swLast,swMiddle);              //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SameSeq rc="+rc+",Pnum="+Pnum+",ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairRon="+ctrPairRon+",ctrPairNotFirst="+ctrPairNotFirst+",swOther="+swOther);//~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3SameSeq swNotLast="+swNotLast+",swNearRelated="+swNearRelated+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle+",swSeqFix="+swSeqFix);//~va91R~
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
                	rankFixErr+=rc;                                //~va91I~
        }                                                          //~va91I~
    	if (Dump.Y) Dump.println("UARank.chk3Same rc="+rc);        //~va11R~
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
        if (ctrPairNotFirst!=0)  //non related earth pair exist    //~va91R~
            swMiddle=true;       //OK if YAKUFIX_MIDDLE            //~va91R~
        if (!swMiddle)                                             //~va91I~
            if (ronType!=TT_JI && ronNumber==Pnum) //ron tile related to yaku//~va91I~
            {                                                      //~va91I~
                swLast=true;                                       //~va91I~
            }                                                      //~va91I~
        rc=setFixErr(RYAKU_3SAMENUM,swLast,swMiddle);                 //~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Same Pnum="+Pnum+",ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairNotFirst="+ctrPairNotFirst);//~va91R~
    	if (Dump.Y) Dump.println("UARank.chkEarth3Same rc="+rc+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle);//~va91R~
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
        for (Pair pair:pairNumS)                                   //~va11R~
        {                                                          //~va11I~
            if (Dump.Y) Dump.println("UARank.chk3SameHand pair="+Pair.toString(pair));//~va11R~
        	if (pair.typePair==PT_NUMSAME && pair.swHand)	//not earth//~va11R~
	        	if (!(pair.type==ronType && pair.number==ronNumber))//~va11I~
            		ctr++;                                         //~va11R~
        }                                                          //~va11I~
        if (ctrPairNotNum!=0)                                      //~va11I~
            for (Pair pair:pairNotNum)     //earth and hand        //~va11R~
            {                                                      //~va11R~
	            if (Dump.Y) Dump.println("UARank.chk3SameHand pair="+Pair.toString(pair));//~va11I~
                if (pair.swHand)	//not earth                    //~va11R~
		        	if (!(pair.type==ronType && pair.number==ronNumber))//~va11I~
    	                ctr++;                                     //~va11R~
            }                                                      //~va11R~
        if (ctr==3)                                                //~va11I~
        {                                                          //~va11I~
            addYaku(RYAKU_3SAMEHAND);                              //~va11I~
        	rc=RANK_3SAMEHAND;                                     //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARank.chk3Samehand rc="+rc+",ctr="+ctr);//~va11R~
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
                	rankFixErr+=rc;                                //~va91I~
        }                                                          //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkStraight rc="+rc+",wkBit="+Integer.toHexString(wkBit));//~va25R~
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
            if (swNearRelated && ctrPairRon!=0)                        //~va91I~
            	ctrPairRon--;                                  //~va91I~
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
                	swLast=true;                                   //~va91I~
            }                                                      //~va91I~
            else	//ryanmen                                      //~va91I~
            {                                                      //~va91I~
                swLast=true;                                       //~va91I~
            }                                                      //~va91I~
        }                                                          //~va91I~
        rc=setFixErr(RYAKU_STRAIGHT,swLast,swMiddle);              //~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight rc="+rc+",Ptype="+Ptype+",ronType="+ronType+",ronNumber="+ronNumber+",typeYakuFix="+typeYakuFix+",ctrPairRon="+ctrPairRon+",ctrPairNotFirst="+ctrPairNotFirst+",swOther="+swOther);//~va91I~
    	if (Dump.Y) Dump.println("UARank.chkEarthStraight swNotLast="+swNotLast+",swNearRelated="+swNearRelated+",swOther="+swOther+",swLast="+swLast+",swMiddle="+swMiddle+",swSeqFix="+swSeqFix);//~va91R~
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
	    	if (Dump.Y) Dump.println("UARank.chkAllSame ctr="+ctr+",pair="+Pair.toString(pair));//~va11I~
        }                                                          //~va11I~
        if (ctrPairNotNum!=0)                                      //~va11I~
            for (Pair pair:pairNotNum)     //earth and hand        //~va11R~
            {                                                      //~va11I~
           		ctr++;                                             //~va11R~
		    	if (Dump.Y) Dump.println("UARank.chkAllSame ctr="+ctr+",pair="+Pair.toString(pair));//~va11I~
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
//  	if (ctrPairNotNum!=0/*mix*/ || statusPillow==STP_NOTNUM || statusPillow==STP_HONOR)//~va11R~//+vaaQR~
    	if (statusPillow!=STP_TANYAO && ctrPairNotNum!=0/*mix*/)   //+vaaQI~
        {                                                          //~va11I~
        	if (chkTerminalNum())                                  //~va11I~
            {                                                      //~va11I~
	        	addYaku(RYAKU_19SEQMIX);                           //~va11I~
                rc=RANK_19SEQMIX;                                  //~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (rc!=0)                                                 //~va11R~
        	rc-=intNotAllHand;                                     //~va11R~
    	if (Dump.Y) Dump.println("UARank.chkTerminalMix rc="+rc);  //~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va11I~
    private boolean chkTerminalNum()                                     //~va11I~
    {                                                              //~va11I~
    	boolean rc=true;                                           //~va11I~
        boolean swSeq=false;                                       //~va11I~
        for (Pair pair:pairNumS)                                   //~va11R~
        {                                                          //~va11I~
            int num=pair.number;                                   //~va11I~
            if (pair.typePair==PT_NUMSAME)                         //~va11I~
            {                                                      //~va11I~
                if (num!=0 && num!=8)                         //~va11I~
                {                                                  //~va11I~
                	rc=false;                                      //~va11I~
                    break;                                         //~va11I~
                }                                                  //~va11I~
            }                                                      //~va11I~
            else                                                   //~va11I~
            {                                                      //~va11I~
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
        //TODO test ankan, not declared kan                        //~va11I~
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
        for (int ii=0;ii<sizePairSeqS;ii++)                        //~va11R~
        {                                                          //~va11I~
        	Pair pair1,pair2;                                      //~va11R~
            pair1=pairNumS[ii];                                    //~va11R~
        	if (pair1.type<PIECE_NUMBERTYPECTR && pair1.typePair==PT_NUMSEQ)//~va11I~
	  	    	for (int jj=ii+1;jj<sizePairSeqS;jj++)             //~va11R~
                {                                                  //~va11I~
		            pair2=pairNumS[jj];                            //~va11R~
		        	if (pair2.typePair==PT_NUMSEQ && pair2.type==pair1.type && pair2.number==pair1.number)//~va11R~
                    	ctrSameSeq++;                               //~va11I~
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
	        addYaku(yaku);                                         //~va11I~
    	if (Dump.Y) Dump.println("UARank.chkSameSeq rc="+rc+",yaku="+yaku);//~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    private int chkTerminal()        //junchanta                   //~va11I~
    {                                                              //~va11I~
        if (swTanyao)                                              //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chkTerminal tanyao rc=0");//~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
    	int rc=0;                                                  //~va11R~
        int ctr=0;                                                 //~va11I~
    	if (ctrPairNotNum==0 && statusPillow==STP_TERMINAL)        //~va11R~
        {                                                          //~va11I~
        	if (chkTerminalNum())                                  //~va11I~
            {                                                      //~va11I~
	        	addYaku(RYAKU_19SEQ);                               //~va11I~
                rc=RANK_19SEQ;                                     //~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (rc!=0)                                                 //~va11R~
        	rc-=intNotAllHand;                                     //~va11R~
    	if (Dump.Y) Dump.println("UARank.chkTerminal rc="+rc);     //~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    private int chkFlushMix()        //honitsu                     //~va11I~
    {                                                              //~va11I~
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
        for (Pair pair:pairNumS)                                   //~va11R~
        {                                                          //~va11I~
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
                if (pair.type==TT_JI && pair.number>=TT_4ESWN_CTR) //~va11R~
                    ctrDragon++;                                   //~va11I~
            }                                                      //~va11I~
        if (ctrDragon==2)                                          //~va11I~
        {                                                          //~va11I~
        	if (typePillow==TT_JI && numberPillow>TT_4ESWN_CTR)    //~va11I~
            {                                                      //~va11I~
	        	addYaku(RYAKU_3DRAGONSMALL);                       //~va11I~
        		rc=RANK_3DRAGONSMALL;                              //~va11I~
	            if (swChkFix)                                      //~va91I~
	            	if (!chkEarth3DragonSmall())                   //~va91R~
	                	rankFixErr+=rc;                            //~va91I~
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
