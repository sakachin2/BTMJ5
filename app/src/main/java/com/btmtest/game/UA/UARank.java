//*CID://+va11R~: update#= 803;                                    //~va11R~
//**********************************************************************//~v101I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                       //~va11R~

import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.UA.Pair.*;
import static com.btmtest.game.UA.Rank.*;
import static com.btmtest.game.UA.UARonData.*;
import static com.btmtest.game.gv.Pieces.PIECE_NOTNUM_CTR;

//****************************************************             //~9C11I~
public class UARank                                                //~va11R~
{                                                                  //~0914I~
                                                                   //~va11I~
    UARonValue UARV;                                               //~va11R~
//  private int[][] dupCtr,dupCtrAll;                              //~va11R~
    private int[][] dupCtr;                                        //~va11I~
    private boolean swTanyao,swRulePinfuTaken,swHonor;             //~va11R~
    private int rankFinal;//,rankTanyao;                           //~va11R~
    private UARonDataTree UARDT;                                   //~va11R~
    private UARonData UARD;                                        //~va11R~
    private int intNotAllHand,player,ctrEarth;                     //~va11R~
    private boolean swPinfu;                                       //~va11I~
    private UAPair UAP;                                            //~va11R~
    private	Pair[][] pairSeqSS;                                    //~va11R~
    private Pair[] pairNumS;                                       //~va11R~
    private int sizePairSeqS;                                      //~va11R~
    private Pair[] pairNotNum;                                     //~va11R~
    private int ctrPairNotNum;                                     //~va11I~
    private static final int MAX_YAKU=20;                          //~va11I~
    private int[][] yakuSS;   //yaku list for each mixed pairs     //~va11R~
    private int[] ctrYakuSS;                                       //~va11R~
    public Rank[] longRankS;	//rank for each mixed pair         //~va11R~
    private Rank longRank;                                         //~va11M~
    private Rank longRank7;                                        //~va11I~
    private int[]  intRankS;	//rank accum                       //~va11I~
    private int idxPairNumSS;                                         //~va11R~
    private int statusPillow,typePillow,numberPillow;              //~va11R~
    private boolean swTaken;                                       //~va11I~
    private int ronNumber,ronType;                                 //~va11I~
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
        intNotAllHand=UARV.swAllInHand ? 0 : 1;                    //~va11R~
        player=UARV.player;                                        //~va11I~
        ronType=UARV.ronType;                                      //~va11I~
        ronNumber=UARV.ronNumber;                                  //~va11I~
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
        swHonor=UARDT.swHonor;	//yakuhai                          //~va11I~
        statusPillow=UARD.statusPillow;                            //~va11R~
        typePillow=UARD.typePillow;                                //~va11I~
        numberPillow=UARD.numberPillow;                            //~va11I~
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
        ctrYakuSS=new int[sizePairNumSS];                          //~va11R~
     	intRankS=new int[sizePairNumSS];                           //~va11R~
     	longRankS=new Rank[sizePairNumSS];                         //~va11R~
        idxPairNumSS=0;                                            //~va11R~
        for (Pair pairS[]:pairNumSS)                               //~va11R~
        {                                                          //~va11I~
            longRank=new Rank();                                   //~va11R~
        	int rank=0;                                            //~va11I~
        	if (pairS!=null)                                       //~va11I~
        		rank=setRank(pairS);                               //~va11R~
        	intRankS[idxPairNumSS]=rank;                           //~va11R~
        	longRankS[idxPairNumSS]=longRank;                         //~va11R~
            idxPairNumSS++;                                        //~va11R~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARank.setRank rankS="+ Arrays.toString(intRankS)+",longRankS="+Rank.toString(longRankS));//~va11R~
    }                                                              //~va11I~
	//*************************************************************************//~va11I~
    private void addYaku(int Prank)                                //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARank.addYaku Prank="+Prank);   //~va11R~
    	Rank.addYaku(longRank,Prank);                                   //~va11R~
    }                                                              //~va11I~
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
        if (Dump.Y) Dump.println("UARank.isPinfu intNoAllHand="+intNotAllHand+",swHonor="+swHonor+",rc="+rc+",pairNumS="+Pair.toString(pairNumS));//~va11R~
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
        for (int ii=0;ii<sizePairSeqS;ii++)                        //~va11R~
        {                                                          //~va11I~
        	Pair pair1,pair2;                                      //~va11R~
            pair1=pairNumS[ii];                                    //~va11R~
        	if (pair1.typePair==PT_NUMSEQ)                         //~va11I~
            {                                                      //~va11I~
		        int ctr=0;                                         //~va11I~
	  	    	for (int jj=ii+1;jj<sizePairSeqS;jj++)             //~va11R~
                {                                                  //~va11I~
		            pair2=pairNumS[jj];                            //~va11R~
		        	if (pair2.typePair==PT_NUMSEQ && pair2.type!=pair1.type && pair2.number==pair1.number)//~va11I~
                    	ctr++;                                     //~va11I~
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
        	rc-=intNotAllHand;                                     //~va11R~
    	if (Dump.Y) Dump.println("UARank.chk3SameSeq rc="+rc);     //~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
    //****************************************************************//~va11I~
    private int chk3Same()           //3tonko, Pon OK              //~va11R~
    {                                                              //~va11I~
        if (swPinfu)                                               //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chk3Same pinfu rc=0");//~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
        int rc=0;                                                  //~va11I~
        for (int ii=0;ii<sizePairSeqS;ii++)                        //~va11R~
        {                                                          //~va11I~
        	Pair pair1,pair2;                                      //~va11R~
            pair1=pairNumS[ii];                                    //~va11R~
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
    	if (Dump.Y) Dump.println("UARank.chk3Same rc="+rc);        //~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
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
        if (swTanyao)                                              //~va11I~
        {                                                          //~va11I~
	    	if (Dump.Y) Dump.println("UARank.chkStraight tanyao rc=0");//~va11R~
        	return 0;                                              //~va11I~
        }                                                          //~va11I~
        int rc=0;                                                           //~va11I~
        int wkBit=0;                                               //+va11I~
        for (int ii=0;ii<sizePairSeqS;ii++)                        //~va11R~
        {                                                          //~va11I~
        	Pair pair1,pair2;                                      //~va11R~
            pair1=pairNumS[ii];                                    //~va11R~
        	if (pair1.typePair!=PT_NUMSEQ)                         //~va11I~
            	continue;                                          //~va11I~
            int num1=pair1.number;                                 //~va11I~
            if (num1!=0 && num1!=3 && num1!=6)                     //~va11I~
            	continue;                                          //~va11I~
            wkBit|=(1<<num1);                                      //+va11I~
            for (int jj=ii+1;jj<sizePairSeqS;jj++)                 //~va11R~
            {                                                      //~va11I~
                pair2=pairNumS[jj];                                //~va11R~
	        	if (pair2.typePair!=PT_NUMSEQ)                     //~va11I~
	            	continue;                                      //~va11I~
	            int num2=pair2.number;                             //~va11I~
	            if (num2!=0 && num2!=3 && num2!=6)                 //~va11I~
     		       	continue;                                      //~va11I~
                if (pair2.type==pair1.type && num2!=num1)          //~va11I~
                {                                                  //+va11I~
                    wkBit|=(1<<num2);                              //+va11R~
                }                                                  //+va11I~
            }                                                      //~va11I~
            if (wkBit==0x07)                                       //+va11R~
            {                                                      //~va11I~
	            addYaku(RYAKU_STRAIGHT);                           //~va11I~
	        	rc=RANK_STRAIGHT;                                  //~va11I~
                break;                                             //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (rc!=0)                                                 //~va11R~
        	rc-=intNotAllHand;                                     //~va11R~
    	if (Dump.Y) Dump.println("UARank.chkStraight rc="+rc+",wkBit="+wkBit);//+va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
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
    	if (ctrPairNotNum!=0/*mix*/ || statusPillow==STP_NOTNUM || statusPillow==STP_HONOR)//~va11R~
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
        }                                                          //~va11I~
    	if (Dump.Y) Dump.println("UARank.chk3Kan rc="+rc);         //~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
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
        	type=-1;                                               //~va11I~
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
            }                                                      //~va11I~
    	}                                                          //~va11I~
    	if (Dump.Y) Dump.println("UARank.chk3DragonSmall rc="+rc+",ctrDragon="+ctrDragon+",ctrPairNotNum="+ctrPairNotNum);//~va11R~
    	return rc;                                                 //~va11I~
    }                                                              //~va11I~
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
