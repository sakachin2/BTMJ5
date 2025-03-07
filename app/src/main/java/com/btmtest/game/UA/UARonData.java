//*CID://+vakhR~: update#= 826;                                    //~vakhR~
//**********************************************************************//~v101I~
//2022/02/20 vakh set kataagari err different from fix err         //~vakhI~
//2021/11/06 vag0 (Bug)Kan call is not shanten up                  //~vag0I~
//2021/11/06 vafz (Bug of evaluate Pon/Chii ronvalue);add Pair for earth for the pon/chii
//2021/11/01 vafi (Bug)number of pair was not top; it cause of failure chanta,straight,3sameseq...//~vafiI~//~vag0R~
//2021/06/12 va94 (Bug)point calc ron at take same should be *2    //~va94I~
//2021/06/06 va91 sakizukechk for robot                            //~va91I~
//2021/04/26 va8r (Bug)Fu is 110 for pinfu tsumo under punfu-tsumo=yes//~va8rI~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//**********************************************************************//~1107I~
//point Same  1/9/ji==>4/8  2->8==>2/4                             //~va11I~
//point Kan   1/9/ji:16/32 tanyao:8/16                             //~va11I~
//**********************************************************************//~va11I~
package com.btmtest.game.UA;                                       //~va11R~

import com.btmtest.dialog.CompReqDlg;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;                           //~v@@@I~//~va11I~

import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.RA.RAConst.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.UA.Pair.*;
import static com.btmtest.game.UA.Rank.*;                          //~va11I~


public class UARonData                                             //~va11R~
{                                                                  //~0914I~
                                                                   //~va11I~
    private int dupCtr,pairType;                                   //~va11R~
    public  int typePillow,numberPillow;                           //~va11R~
	public  int statusPillow;                                      //~va11I~
    public  static final int STP_TANYAO=0;                         //~va11R~
    public  static final int STP_NOTNUM=1;  //4ESWN except round and window//~va11R~
    public  static final int STP_HONOR=2;   //3WGS or 4ESWN when round or window//~va11R~
    public  static final int STP_TERMINAL=3;                       //~va11R~
    public  int rank,point;                                        //~va11R~
    private int ronType,ronNumber;                                 //~va11I~
    private int eswn,round,player;                               //~va11R~
    private UARonDataTree UARDT;                                   //~va11I~
    private int[] intRankS,pointS;                               //~va11I~
    private int[] intRankFixErrS;                                  //~va91I~
    private int[] intRankFixErrSMultiWait;                         //~vakhI~
    private boolean swTaken;                                       //~va94I~
    public UAPair UAP;                                             //~va11R~
    public int idxPatternMax,pointMax,intRankMax,ctrPatternMix;           //~va11I~
    public int intRankFixErrMax;                                    //~va91I~
    public int intRankFixErrMaxMultiWait;                          //~vakhI~
    public Rank longRankMax;                                       //~va11I~
    public Rank longRankFixErrMax;                                 //~va91R~
    public Rank[] longRankS;                                       //~va11I~
    public Rank[] longRankFixErrS;                                 //~va91R~
    public boolean swPillowTanki;                                  //~va11I~
	//*************************************************************************//~va11I~
    public UARonData(int PpairType,int Ptype,int Pnumber,int PdupCtr)//~va11R~
    {                                                              //~va11R~
        pairType=PpairType; typePillow=Ptype; numberPillow=Pnumber; dupCtr=PdupCtr;//~va11R~
        if (Dump.Y) Dump.println("UARonData.Constructor data="+toString());//~va11R~
        if (PpairType==PT_DUMMY_TOP)                               //~va11I~
        	init();                                                //~va11I~
    }                                                              //~va11R~
    //******************************************                   //~va11I~
    private void init()                                            //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonData.init");                //~va11I~
        UAP = new UAPair(PT_DUMMY_TOP, 0/*type*/, 0/*number*/, true);//~va11R~
    }//~va11I~
    //******************************************                   //~va11I~
    public int getAmmount(UARonDataTree Puardt,int Pplayer,int Peswn,int Pround,int PrankBase)//~va11R~
    {                                                              //~va11I~
        UARDT=Puardt;                                              //~va11R~
        eswn=Peswn;                                                //~va11I~
        round=Pround;                                              //~va11I~
        chkPillow(typePillow,numberPillow);           //~va11I~
        player=Pplayer;                                            //~va11I~
        ronType=UARDT.ronType;                                     //~va11I~
        ronNumber=UARDT.ronNumber;                                 //~va11I~
//      makePattern();                                             //~va11R~
        if (Dump.Y) Dump.println("UARonData.getAmmount eswn="+Peswn+",round="+Pround+",player="+player+",ronType="+ronType+",ronNumber="+ronNumber+",rankBase="+PrankBase);//~va11R~
//  	UARDT.aUARank.chkYakuStandard(this,uard,dupCtr,dupCtrAll); //~va11I~
    	intRankS=UARDT.aUARank.getRankStandard(UARDT,this);       //~va11I~
    	intRankFixErrS=UARDT.aUARank.intRankFixErrS;               //~va91I~
    	intRankFixErrSMultiWait=UARDT.aUARank.intRankFixErrSMultiWait;//~vakhI~
    	longRankS=UARDT.aUARank.longRankS;                         //~va11I~
    	longRankFixErrS=UARDT.aUARank.longRankFixErrS;             //~va91R~
        pointS=getPoint();                                         //~va11R~
        int amtMax=getAmmount(PrankBase);  //by intRankS and pointS//~va11R~
        if (Dump.Y) Dump.println("UARonData.getAmmount longRankS="+Rank.toStringName(longRankS));//+vakhR~
        if (Dump.Y) Dump.println("UARonData.getAmmount amtMax="+amtMax);//~vakhI~
        return amtMax;                                             //~va11R~
    }                                                              //~va11I~
    //******************************************                   //~va11I~
    private void chkPillow(int Ptype,int Pnumber)                  //~va11I~
    {                                                              //~va11I~
    	statusPillow=STP_TANYAO;                                   //~va11I~
        if (Ptype<PIECE_NUMBERTYPECTR)                             //~va11I~
        {                                                          //~va11I~
			if (Pnumber==0 || Pnumber==8)                         //~va11I~
				statusPillow=STP_TERMINAL;                         //~va11I~
        }                                                          //~va11I~
        else                                                       //~va11I~
        {                                                          //~va11I~
        	if (Pnumber>=TT_4ESWN_CTR)                             //~va11I~
            	statusPillow=STP_HONOR;                            //~va11I~
            else                                                   //~va11I~
            if (Pnumber==eswn||Pnumber==round)                     //~va11I~
            	statusPillow=STP_HONOR;                            //~va11I~
            else                                                   //~va11I~
            	statusPillow=STP_NOTNUM;                           //~va11I~
        }                                                          //~va11I~
	}                                                              //~va11I~
    //******************************************                   //~va11I~
    private int[] getPoint()                                        //~va11R~
    {                                                              //~va11I~
        swTaken=UARDT.swTaken;                                     //~va94M~
//      int pointPillow=getPointPillow();                          //~va11R~
        int pointNotNum=getPointNotNum();                          //~va11I~
        int base;                                                  //~va11I~
        boolean swAllInHand=UARDT.swAllInHand;                     //~va11R~
//      base=swAllInHand ? POINT_ALLHAND : POINT_NOTALLHAND;       //~va11R~
//      base=(swAllInHand && !UARDT.swTaken) ? POINT_ALLHAND : POINT_NOTALLHAND;  //menzen ron:30,else 20//~va11I~//~va94R~
        base=(swAllInHand && !swTaken) ? POINT_ALLHAND : POINT_NOTALLHAND;  //menzen ron:30,else 20//~va94I~
//      int pointS[]=getPointNum(base+pointPillow+pointNotNum);    //~va11R~
        int pointS[]=getPointNum(base+pointNotNum);                //~va11I~
        if (Dump.Y) Dump.println("UARonData.getPoint pointNotNum="+pointNotNum+",swTaken="+swTaken+",swAllInHand="+swAllInHand+",base="+base+",pointS="+Arrays.toString(pointS));//~va11R~//~va94R~
        return pointS;                                             //~va11I~
    }                                                              //~va11I~
    //******************************************                   //~va11M~
    //*+2:WGR,wind,round,tanki                                     //~va11I~
    //******************************************                   //~va11I~
    private int getPointPillow()                                   //~va11M~
    {                                                              //~va11M~
    	int pt=0;                                                  //~va11M~
       	swPillowTanki=false;                                       //~va11I~
        if (typePillow==TT_JI)                                     //~va11M~
        {                                                          //~va11M~
        	if (UARDT.swRuleDoublePillowPoint)                     //~va11M~
            {                                                      //~va11M~
        		if (numberPillow==eswn)                            //~va11M~
            		pt+=2;                                         //~va11M~
        		if (numberPillow==round)                           //~va11M~
            		pt+=2;                                         //~va11M~
            }                                                      //~va11M~
            else                                                   //~va11M~
        		if (numberPillow==eswn||numberPillow==round)       //~va11M~
            		pt+=2;                                         //~va11M~
            if (numberPillow>=TT_4ESWN_CTR)	//dragon               //~va11M~
            	pt+=2;                                             //~va11M~
        }                                                          //~va11M~
        if (typePillow==ronType && numberPillow==ronNumber)	//tanki//~va11M~
        {                                                          //~va11I~
        	swPillowTanki=true;                                    //~va11I~
            pt+=2;                                                 //~va11M~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonData.getPointPillow pt="+pt);//~va11M~
        return pt;                                                 //~va11M~
    }                                                              //~va11M~
    //******************************************                   //~va11I~
    //*1/9/ji 4 or 8                                               //~va11I~
    //******************************************                   //~va11I~
    private int getPointNotNum()                                 //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonData.getPointNotNum");      //~va11I~
     	Pair[] pairS=UAP.pairNotNum;                               //~va11R~
        int pt=0;                                                  //~va11I~
        for (Pair pair:pairS)                                      //~va11R~
        {                                                          //~va11I~
        	if (pair==null)                                        //~va11I~
            	continue;                                          //~va11I~
        	if (pair.dupCtr==4)                                    //~va11R~
            {                                                      //~va11I~
            	boolean swTake=(pair.flag & TDF_KAN_TAKEN)!=0;      //~va11I~
            	pt+=getPointKan(swTake,pair.type,pair.number);     //~va11R~
            }                                                      //~va11I~
            else                                                   //~va11I~
            {                                                      //~va94I~
//          	pt+=getPointSame(null,pair.swHand,pair.type,pair.number);//~va11R~//~va94R~
            	pt+=getPointSame(null,pair.swHand,pair.type,pair.number,swTaken);//~va94R~
            }                                                      //~va94I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonData.getPointNotNum pt="+pt);//~va11R~
        return pt;                                                 //~va11I~
    }                                                              //~va11I~
    //******************************************                   //~va11I~
    private int[] getPointNum(int PbasePoint)                      //~va11R~
    {                                                              //~va11I~
     	Pair[][] pairNumSS=UAP.mixedSS;                            //~va11R~
     	int sizePairNumSS=pairNumSS.length;
        if (Dump.Y) Dump.println("UARonData.getPointNum base="+PbasePoint+",sizePairNumSS="+sizePairNumSS+",pairNumSS="+Pair.toString(pairNumSS));//~va11R~
     	ctrPatternMix=sizePairNumSS;//~va11R~
        int[] pointS=new int[sizePairNumSS];                       //~va11R~
        int idxPairNumSS=0;                                        //~va11R~
        for (Pair pairS[]:pairNumSS)                               //~va11R~
        {                                                          //~va11I~
        	if (pairS==null)                                       //~va11I~
            	continue;                                          //~va11I~
    		if (longRankS[idxPairNumSS].isContains(RYAKU_PINFU))      //~va11I~
            {                                                      //~va11I~
//          	if (UARDT.swTaken)	//pinfu tsumo:2han             //~va11I~//~va8rR~
//  	            pointS[idxPairNumSS]=PbasePoint-(POINT_ALLHAND-POINT_NOTALLHAND);//~va11I~//~va8rR~
//              else                                               //~va11I~//~va8rR~
		            pointS[idxPairNumSS]=PbasePoint;               //~va11R~
//                if ((TestOption.option2 & TestOption.TO2_RONVALUE_TESTSUB)!=0)//~va11R~
//                    UView.showToastLong("pointNum pinfu="+pointS[idxPairNumSS]);//~va11R~
		        if (Dump.Y) Dump.println("UARonData.getPointNum ii="+idxPairNumSS+",set basePoint="+PbasePoint);//~va11I~
            }                                                      //~va11I~
            else	                                               //~va11I~
            {                                                      //~va11I~
				int pointPillow=getPointPillow();                  //~va11I~
//          	pointS[idxPairNumSS]=getPointNum(pairS)+PbasePoint;//~va11R~
//          	int pt=getPointNum(pairS)+PbasePoint;              //~va11R~
            	int pt=getPointNum(pairS)+PbasePoint+pointPillow;  //~va11I~
                if (UARDT.swTaken)                                 //~va11I~
                	pt+=2;                                         //~va11I~
//                if ((TestOption.option2 & TestOption.TO2_RONVALUE_TESTSUB)!=0)//~va11R~
//                    UView.showToastLong("pointNum="+pt);         //~va11R~
//          	pointS[idxPairNumSS]=Utils.roundUp(pt,10);         //~va11R~
            	pointS[idxPairNumSS]=pt;                           //~va11I~
            }                                                      //~va11I~
	        idxPairNumSS++;                                        //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonData.getPoint swTaken="+UARDT.swTaken+",pointS="+ Arrays.toString(pointS));//~va11R~
        return pointS;
    }                                                              //~va11I~
    //******************************************                   //~va11M~
    private int getPointNum(Pair[] PpairS)                         //~va11R~
    {                                                              //~va11M~
        if (Dump.Y) Dump.println("UARonData.getPointNum PpairS="+Pair.toString(PpairS));//~va11R~
        int pt=0;                                                  //~va11I~
        boolean swSeqPoint=false;                                  //~va11I~
        for(Pair pair:PpairS)                                      //~va11R~
        {                                                          //~va11M~
        	if (pair==null)                                        //~va11I~
            	continue;                                          //~va11I~
            if (pair.typePair==PT_NUMSAME)                         //~va11R~
            	pt+=getPointNumSame(PpairS,pair);                  //~va11R~
            else               //NumSame                           //~va11I~
            {                                                      //~va11I~
            	if (getPointNumSeq(pair)>0)                        //~va11R~
                	swSeqPoint=true;                               //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11M~
        if (swSeqPoint)    //avoid double count for peiko          //~va11I~
        	pt+=2;                                                 //~va11I~
        if (Dump.Y) Dump.println("UARonData.getPointNum rc="+pt);  //~va11R~
        return pt;                                                 //~va11I~
    }                                                              //~va11M~
    //******************************************                   //~va11M~
    private int getPointNumSame(Pair[] PpairS,Pair Ppair)          //~va11R~
    {                                                              //~va11M~
        int pt;                                                    //~va11I~
        if (Ppair.dupCtr==4)                                        //~va11I~
        {                                                          //~va11I~
            boolean swTake=(Ppair.flag & TDF_KAN_TAKEN)!=0;         //~va11I~
            pt=getPointKan(swTake,Ppair.type,Ppair.number);        //~va11I~
        }                                                          //~va11I~
        else                                                       //~va11I~
        {                                                          //~va94I~
//      	pt=getPointSame(PpairS,Ppair.swHand,Ppair.type,Ppair.number);//~va11R~//~va94R~
        	pt=getPointSame(PpairS,Ppair.swHand,Ppair.type,Ppair.number,swTaken);//~va94R~
        }                                                          //~va94I~
        if (Dump.Y) Dump.println("UARonData.getPointNumSame pt="+pt+",pair="+Pair.toString(Ppair));//~va11R~
        return pt;                                                 //~va11M~
    }                                                              //~va11M~
    //******************************************                   //~va11M~
    public  int getPointNumSeq(Pair Ppair)                         //~va11R~
    {                                                              //~va11M~
        if (Dump.Y) Dump.println("UARonData.getPointNumSeq swPillowTanki="+swPillowTanki+",typePillow="+typePillow+",numberPillow="+numberPillow+",ronNumber="+ronNumber+",ronType="+ronType+",Ppair="+Pair.toString(Ppair));//~va11R~
        int pt=0;                                                  //~va11M~
        if (Ppair.type==ronType)                                   //~va11M~
        {                                                          //~va11M~
        	boolean swDup=swPillowTanki && Ppair.type==typePillow; //~va11I~
        	int num=Ppair.number;                                  //~va11M~
            if (ronNumber==num+1) //kanchan                        //~va11M~
            {                                                      //~va11I~
            	if (!(swDup && ronNumber==numberPillow))	//dup  //~va11I~
                {                                                  //~va11I~
		        	if (Dump.Y) Dump.println("UARonData.getPointNumSeq kanchan ronNumber="+ronNumber+",number="+num);//~va11R~
            		pt=2;                                          //~va11R~
                }                                                  //~va11I~
            }                                                      //~va11I~
            else                                                   //~va11M~
            if (ronNumber==num && num==6) //(7)89 penchan          //~va11M~
            {                                                      //~va11I~
            	if (!(swDup && ronNumber==numberPillow))	//dup  //~va11I~
                {                                                  //~va11I~
			        if (Dump.Y) Dump.println("UARonData.getPointNumSeq penchan 7 ronNumber="+ronNumber+",number="+num);//~va11R~
        	    	pt=2;                                          //~va11R~
                }                                                  //~va11I~
            }                                                      //~va11I~
            else                                                   //~va11M~
            if (ronNumber==num+2 && num==0) //12(3) penchan        //~va11M~
            {                                                      //~va11I~
            	if (!(swDup && ronNumber==numberPillow))	//dup  //~va11I~
                {                                                  //~va11I~
			        if (Dump.Y) Dump.println("UARonData.getPointNumSeq penchan 3 ronNumber="+ronNumber+",number="+num);//~va11R~
	            	pt=2;                                          //~va11R~
                }                                                  //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11M~
        if (Dump.Y) Dump.println("UARonData.getPointNumSeq pt="+pt);//~va11R~
        return pt;                                                 //~va11M~
    }                                                              //~va11M~
    //******************************************                   //~va11M~
    //*1/9/ji==>4/8  2->8==>2/4                                    //~va11I~
    //******************************************                   //~va11I~
//  private int getPointSame(Pair[] PpairS,boolean PswHand,int Ptype,int Pnumber)//~va11R~//~va94R~
    private int getPointSame(Pair[] PpairS,boolean PswHand,int Ptype,int Pnumber,boolean PswTaken)//~va94R~
    {                                                              //~va11M~
    	int pt=0;                                                  //~va11M~
        if (Ptype==TT_JI || Pnumber==0 || Pnumber==8)	//1,9,ji   //~va11M~
        {                                                          //~va11I~
        	pt=4;                                                  //~va11M~
        	if (Dump.Y) Dump.println("UARonData.getPointSame 19JI");//~va11I~
        }                                                          //~va11I~
        else                                                       //~va11M~
        {                                                          //~va11I~
        	pt=2;                                                  //~va11M~
        	if (Dump.Y) Dump.println("UARonData.getPointSame 2-8");//~va11I~
        }                                                          //~va11I~
//      if (PswHand && !(Ptype==ronType && Pnumber==ronNumber))    //~va11R~
        if (PswHand)                                               //~va11I~
			if (!(Ptype==ronType && Pnumber==ronNumber))           //~va11I~
	        	pt+=pt;                                            //~va11R~
            else                                                   //~va11I~
            {                                                      //~va94I~
            	if (PswTaken) //Ron by Self-Draw                   //~va94R~
		        	pt+=pt;                                        //~va94I~
                else                                               //~va94I~
            	if (chkSameAndSeq(PpairS,Ptype,Pnumber))           //~va11I~
		        	pt+=pt;                                        //~va11I~
            }                                                      //~va94I~
        if (Dump.Y) Dump.println("UARonData.getPointSame swHand="+PswHand+",type="+Ptype+",Pnumber="+Pnumber+",pt="+pt);//~va11R~
        return pt;                                                 //~va11M~
    }                                                              //~va11M~
    //***************************************************************************************************//~va11I~
    //*even if num same is same as ron tile, ron tile may be a part of seq. if, so get consealed point//~va11I~
    //*e.g)  ron by 2 for 222234 (pair is 222 and 234), assume 2 is part of 222//~va94I~
    //***************************************************************************************************//~va11I~
    private boolean chkSameAndSeq(Pair[] PpairS,int Ptype,int Pnumber) //~va11I~
    {                                                              //~va11I~
    	boolean rc=false;                                           //~va11I~
        if (Dump.Y) Dump.println("UARonData.chkSameAndSeq type="+Ptype+",Pnumber="+Pnumber+",pairS="+Pair.toString(PpairS));//~va11I~
        if (PpairS==null)   //notnum
            return false;
        for(Pair pair:PpairS)                                      //~va11I~
        {                                                          //~va11I~
	        if (Dump.Y) Dump.println("UARonData.chkSameAndSeq pair="+Pair.toString(pair));//~va11I~
        	if (pair==null)                                        //~va11I~
            	continue;                                          //~va11I~
            if (pair.typePair==PT_NUMSAME)                         //~va11I~
            	continue;                                          //~va11I~
            if (pair.type!=Ptype)                                  //~va11I~
            	continue;                                          //~va11I~
            int num=pair.number;                                   //~va11I~
            if (Pnumber>=num && Pnumber<num+3)                     //~va11I~
            {                                                      //~va11I~
            	rc=true;                                           //~va11I~
            	break;                                             //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonData.chkSameAndSeq rc="+rc); //~va11I~
        return rc;
    }                                                              //~va11I~
    //******************************************                   //~va11I~
    //*1/9/ji:16/32 tanyao:8/16                                    //~va11I~
    //******************************************                   //~va11I~
    private int getPointKan(boolean PswHand,int Ptype,int Pnumber) //~va11R~
    {                                                              //~va11I~
    	int pt=0;                                                  //~va11I~
        if (Ptype==TT_JI || Pnumber==0 || Pnumber==8)	//1,9,ji   //~va11I~
        {                                                          //~va11I~
        	pt=16;                                                 //~va11I~
        	if (Dump.Y) Dump.println("UARonData.getPointKan 19JI");//~va11I~
        }                                                          //~va11I~
        else                                                       //~va11I~
        {                                                          //~va11I~
        	pt=8;                                                 //~va11I~
        	if (Dump.Y) Dump.println("UARonData.getPointKan 2-8"); //~va11I~
        }                                                          //~va11I~
        if (PswHand)                                               //~va11R~
        	pt+=pt;                                                //~va11I~
        if (Dump.Y) Dump.println("UARonData.getPointKan swHand="+PswHand+",type="+Ptype+",Pnumber="+Pnumber+",pt="+pt);//~va11R~
        return pt;                                                 //~va11I~
    }                                                              //~va11I~
    //******************************************                   //~va11I~
    private int getAmmount(int PrankBase)                          //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonData.getAmmount rankBase="+PrankBase+",ctrPatternMax="+ctrPatternMix);//~va11R~//~vag0R~
        int amtMax=0;                                              //~va11I~
        int rankMax=0;                                             //~va11I~
        for (int ii=0;ii<ctrPatternMix;ii++)                       //~va11I~
        {                                                          //~va11I~
        	int pt=pointS[ii];                                     //~va11R~
            if (pt!=POINT_7PAIR2)	//7pair may be 25              //~va11I~
	            pt=Utils.roundUp(pt,10);                           //~va11R~
	    	intRankS[ii]+=PrankBase;                               //~va11R~
	    	int rank=intRankS[ii];                                 //~va11I~
        	if (Dump.Y) Dump.println("UARonData.getAmmount ii="+ii+",rank="+rank);//~va11I~//~vag0R~
//          if (rank>=MIN_RANK_YAKUMAN)                            //~va11R~
    		if (rank>=MIN_RANK_YAKUMAN && RuleSettingYaku.isYakumanByRank())//~va11I~
            {                                                      //~va11I~
            	UARDT.addYakuman(RYAKU_BYRANK,false/*single yakuman*/);//~va11I~
                setMax(ii);                                        //~va11R~
		        if (Dump.Y) Dump.println("UARonData.getAmmount return by RYAKU_BYRANK rank="+rank+",longRankMax="+longRankMax.toString()+",longRankFixErrMax="+longRankFixErrMax.toString());//~va11I~//~va91R~
                return 0;                                          //~va11I~
            }                                                      //~va11I~
    		int amt=getAmmount(rank,pt);                           //~va11I~
		    if (Dump.Y) Dump.println("UARonData.getAmmount ii="+ii+",amt="+amt+",old amtMax="+amtMax+",rank="+rank+",old rankMax="+rankMax);//~va11R~
            if (amtMax==0 || amt>amtMax || (amt==amtMax && rank>rankMax) )//~va11I~
            {                                                      //~va11I~
            	amtMax=amt;                                        //~va11I~
            	rankMax=rank;                                      //~va11I~
            	setMax(ii);                                        //~va11R~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonData.getAmmount idxMax="+idxPatternMax+",amtMax="+amtMax);//~va11I~
        return amtMax;                                             //~va11I~
    }                                                              //~va11I~
    //******************************************                   //~va11I~
    private void setMax(int Pidx)                    //~va11R~
    {                                                              //~va11I~
    	idxPatternMax=Pidx;                                        //~va11I~
        pointMax=pointS[Pidx];                                     //~va11I~
        intRankMax=intRankS[Pidx];                                 //~va11R~
        intRankFixErrMax=intRankFixErrS[Pidx];                     //~va91I~
        intRankFixErrMaxMultiWait=intRankFixErrSMultiWait[Pidx];   //~vakhI~
        longRankMax=longRankS[Pidx];                               //~va11I~
        longRankFixErrMax=longRankFixErrS[Pidx];                   //~va91R~
        if (Dump.Y) Dump.println("UARonData.setMax idxPatternMax="+Pidx+",pointMax="+pointMax+",intRankMax="+intRankMax+",intRankFixErrMax="+intRankFixErrMax+",intRankFixErrMaxMultiWait="+intRankFixErrMaxMultiWait+",longRankMax="+Rank.toString(longRankMax)+"="+Rank.toStringName(longRankMax)+",longRankFixErr="+Rank.toString(longRankFixErrMax));//~va11R~//~va91R~//~vakhR~
    }                                                              //~va11I~
    //******************************************                   //~va11I~
    private int getAmmount(int Prank,int Ppoint)                   //~va11I~
    {                                                              //~va11I~
    	//TODO for renho 4 han do RankMup                          //~va11I~
    	int amt= CompReqDlg.calcPointBase(Ppoint,Prank,UARDT.swRuleRankMUp);//~va11I~
        if (Dump.Y) Dump.println("UARonData.getAmmount rank="+Prank+",point="+Ppoint+",amt="+amt+",swRuleRankMUp="+UARDT.swRuleRankMUp);//~va11R~
        return amt;                                                //~va11I~
    }                                                              //~va11I~
    //******************************************                   //~va11I~
    //*from UARDT.getAmmount                                       //~vafiI~
    //******************************************                   //~vafiI~
//  public void makePattern()                                      //~va11R~
    public void makePattern(Pair[] PpairSEarth)                    //~va11I~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonData.makePattern");         //~va11I~
//      Pair[] pairS=getPairEarth();                               //~va11R~
//  	UAP.makePattern(pairS);                                    //~va11R~
    	UAP.makePattern(PpairSEarth);                              //~va11I~
    }                                                              //~va11I~
    //******************************************                   //~va11I~
    public static Pair[] getPairEarth(TileData[][] PtdSS)                            //~va11R~
    {                                                              //~va11I~
        if (Dump.Y) Dump.println("UARonData.getPairEarth");        //~va11I~
        TileData[][] pairOnEarth=PtdSS;     //~va11I~
        Pair[] list=new Pair[pairOnEarth.length];                  //~va11R~
        int ctr=0;                                                 //~va11I~
	    for (TileData[] tds:pairOnEarth)                           //~va11I~
        {                                                          //~va11I~
        	if (tds!=null && tds.length!=0)                        //~va11I~
            {                                                      //~va11I~
                TileData td=tds[0];                                //~va11I~
                int typePair=PT_NOTNUM;                            //~va11I~
                int numTop=td.number;
                if (td.type<PIECE_NUMBERTYPECTR)                   //~va11I~
                	if ((td.flag & TDF_CHII)!=0)	//            =0x40;  //pair by CHII//~va11I~
                    {                                              //~vafiI~
		                typePair=PT_NUMSEQ;                        //~va11I~
        				numTop=TN9;                            //~vafiI~
                        for (TileData td2:tds)                      //~vafiI~
	                        numTop=Math.min(numTop,td2.number);     //~vafiR~
                    }                                              //~vafiI~
                    else                                           //~va11I~
                    {                                              //~vafiI~
		                typePair=PT_NUMSAME;                       //~va11I~
                    }                                              //~vafiI~
//  			Pair pair=new Pair(typePair,td.type,td.number,tds.length,td.flag);//~va11R~//~vafiR~
    			Pair pair=new Pair(typePair,td.type,numTop,tds.length,td.flag);//~vafiI~
                list[ctr++]=pair;                                  //~va11I~
            }                                                      //~va11I~
        }                                                          //~va11I~
        if (Dump.Y) Dump.println("UARonData.getPairEarth exit pairEarth="+list);//~vakhI~
        return list;
    }                                                              //~va11I~
    //******************************************                   //~vafiI~
    public static Pair[] getPairEarth(TileData[][] PtdSS,int Paction,int PposTop,int Pflag)//~vafiI~
    {                                                              //~vafiI~
        if (Dump.Y) Dump.println("UARonData.getPairEarth action="+Paction+",PposTop="+PposTop+",flag="+Integer.toString(Pflag));//~vafiI~
        Pair[] list=getPairEarth(PtdSS);                           //~vafiI~
        int num=PposTop%CTR_NUMBER_TILE;                           //~vafiR~
        int type=PposTop/CTR_NUMBER_TILE;                          //~vafiR~
        int typePair=PT_NOTNUM;                                    //~vafiI~
        int flag=Pflag;                                            //~vafiI~
        if (Paction==GCM_CHII)                                     //~vafiI~
        	flag|=TDF_CHII;                                        //~vafiI~
        else                                                       //~vafiI~
        if (Paction==GCM_KAN)                                      //~vag0I~
        	flag|=TDF_KAN_RIVER;                                   //~vag0I~
        else                                                       //~vag0I~
        	flag|=TDF_PON;                                         //~vafiI~
                                                                   //~vafiI~
        if (type<PIECE_NUMBERTYPECTR)                              //~vafiI~
        {                                                          //~vafiI~
            if (Paction==GCM_CHII)                                 //~vafiI~
                typePair=PT_NUMSEQ;                                //~vafiI~
            else                                                   //~vafiI~
                typePair=PT_NUMSAME;                               //~vafiI~
        }                                                          //~vafiI~
        int numTop=num;                                            //~vafiI~
//    	Pair pair=new Pair(typePair,type,numTop,PAIRCTR,Pflag);    //~vafiI~//~vag0R~
      	Pair pair=new Pair(typePair,type,numTop,(Paction==GCM_KAN ? PAIRCTR_KAN : PAIRCTR),Pflag);//~vag0I~
        for (int ii=0;ii<list.length;ii++)                         //~vafzR~
        	if (list[ii]==null)                                    //~vafzI~
            {                                                      //~vafzI~
        		list[ii]=pair;                                            //~vafiI~//~vafzR~
                break;                                             //~vafzI~
            }                                                      //~vafzI~
        if (Dump.Y) Dump.println("UARonData.getPairEarth exit Pairs="+Pair.toString(list));//~vafiI~//~vafzR~
        return list;                                               //~vafiI~
    }                                                              //~vafiI~
}                                                                  //~va11I~
