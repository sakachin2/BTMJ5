//*CID://+va70R~: update#= 319;                                    //~va70R~
//**********************************************************************
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//~va70I~
//2021/01/07 va60 CalcShanten
//**********************************************************************
package com.btmtest.game.RA;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.StaticVars.AG;
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
    public  int posPillow;                                         //~1302R~
//*************************
	public RADSEval()
    {
        if (Dump.Y) Dump.println("RADEval.Constructor");           //~1131R~
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
    }
    //***********************************************************************
    public void evaluateHand(int PmyShanten,int PeswnDiscard,int[] PitsHand,int PctrHand,int[] PitsHandValue)//~1127R~
    {
        if (Dump.Y) Dump.println("RADEval.evaluateHand myShanten="+PmyShanten+",eswnDiscard="+PeswnDiscard);//~1127R~//~1131R~
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
        if (Dump.Y) Dump.println("RADEval.adjustByTileForReach itsHand="+Utils.toString(PitsHand,9,PctrHand));//~va70I~
    	TileData td=RADS.tdsHand[Pidx];   //evaluateHand is not called//~va70R~
        RSP=RS.RSP[Peswn];                                         //~va70I~
        if (Ppos>=OFFS_WORDTILE)   //WGR+ESWN                      //~va70R~
           	v=evaluateTileWord(Ppos);                              //~va70R~
        else                                                       //~va70R~
           	v=RADSEN.evaluateForReach(Ppos,RSP.getIntent(),td);    //~va70R~
        if (RADS.isDoraOpen(td))                                   //~va70R~
        	v+=DV_DORA;                                            //~va70R~
        if (Dump.Y) Dump.println("RADEval.adjustByTileForReach idx="+Pidx+",pos="+Ppos+",v="+v+",td="+td.toString());//~va70R~
        return v;                                                  //~va70R~
    }                                                              //~va70R~
    //***********************************************************************
    private void evaluateTile(int PmyShanten)                      //~1127R~
    {
    	int v;
        int maxWinTile=0;                                          //~1216I~
        int posHanMax=-1;                                          //~1220R~
        TileData td;
        if (Dump.Y) Dump.println("RADEval.evaluateTile swDoReach="+RADS.swDoReach+",itsHand="+Utils.toString(itsHand,9));//~1131R~//~1201R~
        if (Dump.Y) Dump.println("RADEval.evaluateTile before itshandPos="+Utils.toString(itsHandPos,-1,ctrHand));//~1213R~
        if (Dump.Y) Dump.println("RADEval.evaluateTile before itsHandValue="+Utils.toString(itsHandValue,-1,ctrHand));//~1127R~//~1131R~//~1213R~
        int intent=RSP.getIntent();                                //~1225I~//~1309M~
        posPillow=searchPillow4(intent);                           //~1302R~
//  	chkNumberStat();                                           //~1214R~
//  	chkNumberMeld();                                           //~1214R~
//  	RADSEN.chkNumberMeld2();                                          //~1214I~//~1225R~
        boolean skipStandard=((intent & INTENT_7PAIR)!=0) || ((intent & INTENT_7PAIR)!=0 && PmyShanten==1);//~1314I~
        if (!skipStandard)                                         //~1314I~
	    	RADSEN.chkNumberMeld2(eswnDiscard,intent,itsHand,itsHandPos,itsHandValue,ctrHand);//~1225R~//~1302R~//~1313R~
    	hanMax=0;
        int posOld=-1;                                             //~1220R~
        for (int ii=0;ii<ctrHand;ii++)
        {
        	int pos=itsHandPos[ii];                                //~1122R~
            if (!RS.isDiscardable(eswnDiscard,pos))//chk pao openreach
            {
            	itsHandValue[ii]=DV_NOTDISCARDABLE;
	        	if (Dump.Y) Dump.println("RADEval.evaluateTile notDiscardable ii="+ii+",handVal="+itsHandValue[ii]);//~1213I~
                continue;
            }
//      	int shanten=0;                                         //~1122R~//~1127R~
//            if (!RADS.swDoReach)                                        //~1122I~//~1309R~
//            {                                                      //~1127I~//~1309R~
////              shanten=chkShantenAtDiscard(PmyShanten,ii,pos);               //~1122I~//~1127R~//~1309R~
//                chkShantenAtDiscard(PmyShanten,ii,pos);            //~1127I~//~1309R~
//            }                                                      //~1127I~//~1309R~
        	evaluateIntent(ii,pos);                                //~1307I~
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
        	if (Dump.Y) Dump.println("RADEval.evaluateTile add pos="+pos+",idx="+ii+",old="+itsHandValue[ii]);//~1214I~//~1218R~
	        itsHandValue[ii]+=v;                                   //~1122R~//~1126R~
        	if (Dump.Y) Dump.println("RADEval.evaluateTile add exposed v="+v+",ii="+ii+",handVal="+itsHandValue[ii]);//~1126I~//~1131R~
          }                                                        //~1313M~
//      	evaluateIntent(ii,pos);                                //~1307R~
	        int han=0;                                               //~1122R~
//          if (!RADS.swDoReach)    //evaluateWinlist done at RAReach//~1309R~
//          {                                                      //~1220I~//~1309R~
            	boolean swSameAsPrev=pos==posOld;                   //~1220R~
//              han=evaluateNextTake(ii,pos,shanten);	//han at ron if shanten=0;//~1122R~//~1127R~//~1220R~
                han=evaluateNextTake(ii,pos,PmyShanten,swSameAsPrev,intent);	//han at ron if shanten=0;//~1127I~//~1220R~//~1309R~
//          }                                                      //~1220I~//~1309R~
            posOld=pos;                                            //~1220R~
            if (han>hanMax)	//shanten=0 and winnable, shanten is upped or same as previous; if same value is not up,so evaluate by han//~1220R~
            {                                                      //~1220I~
            	hanMax=han;
                posHanMax=pos;                                     //~1220R~
            }                                                      //~1220I~
            if (maxWinTile<ctrWinningTileTryNext)                  //~1216I~
	            maxWinTile=ctrWinningTileTryNext;                  //~1216I~
        }
        if (posHanMax>=0)                                          //~1220R~
        {                                                          //~1220I~
		    evaluateHanMax(posHanMax);                              //~1220I~
        }                                                          //~1220I~
                                                                   //~1307I~
        if (PmyShanten==0 && (intent & INTENT_7PAIR)!=0)           //~1307I~
	      	evaluateDiscard7Pair();                                //~1307I~
//      if (RADS.swDoReach)                                        //~1122I~//~1309R~
//          hanMax=RADS.hanMaxReach;                               //~1122I~//~1309R~
        RADSO.chkOtherPlayer(PmyShanten,hanMax,maxWinTile,eswnDiscard,itsHand,ctrHand);//~1216R~
        if (Dump.Y) Dump.println("RADEval.evaluateTile after="+Utils.toString(itsHandValue,-1,ctrHand));//~1127R~//~1131R~
        if (Dump.Y) Dump.println("RADEval.evaluateTile after itsHandPos="+Utils.toString(itsHandPos,-1,ctrHand));//~1218I~
//      if (Dump.Y) Dump.println("RADEval.evaluateTile after itsHandMeld="+Utils.toString(itsHandPosMeld,-1,ctrHand));//~1218I~//~1225R~
    }
    //***********************************************************************//~1220I~
    //*not shuntenUp but kept shanten=0; add to all tile of same pos value//~1220I~
    //***********************************************************************//~1220I~
    private void evaluateHanMax(int PposHanMax)                        //~1220I~
    {                                                              //~1220I~
        if (Dump.Y) Dump.println("RADEval.evaluateHanMax pos="+PposHanMax);//~1220I~
        for (int ii=0;ii<ctrHand;ii++)                             //~1220I~
        {                                                          //~1220I~
        	if (PposHanMax==itsHandPos[ii])                        //~1220I~
        	{                                                      //~1220I~
	        	if (Dump.Y) Dump.println("RADEval.evaluateHanMax old idxHandValue["+ii+"]="+itsHandValue[ii]);//~1220I~
    	    	itsHandValue[ii]+=DV_TRYNEXT_HANMAX; //-1000,000//~1220I~
	        	if (Dump.Y) Dump.println("RADEval.evaluateHanMax new idxHandValue["+ii+"]="+itsHandValue[ii]);//~1220I~
            }                                                      //~1220I~
        }                                                          //~1220I~
    }                                                              //~1220I~
    //***********************************************************************
    private int chkShantenAtDiscard(int PmyShanten,int Pidx/*idx of tdsHand*/,int Ppos/*of tds[Pidx]*/,int Pintent)//~1126R~//~1127R~//~1309R~
    {
        int shanten=RAUtils.getShantenAdd(itsHand,ctrHand,Ppos,-1);
        if (Dump.Y) Dump.println("RADEval.chkShantenAtDiscard shanten="+PmyShanten+"intent=" + Integer.toHexString(RADS.myIntent));//~1309I~
        int v=0;                                                     //~1127I~
//      if (shanten>PmyShanten)                                    //~1127I~//~1302R~
        if (PmyShanten<=HV_NOIGNORE_SHANTEN_DOWN && shanten>PmyShanten)  //if shanten>4 no set INTENT_13ORPHAN; so intent>4 ignore shanten down//~1302R~
        {                                                          //~1127I~
            if ((Pintent & INTENT_SAMECOLOR_ANY) == 0)                //~1309I~
            {                                                      //~1309I~
                v = DV_SHANTEN_DOWN;          //-100,000                 //~1127I~//~1302R~//~1309R~
                if (Dump.Y) Dump.println("RADEval.chkShantenAtDiscard shantenDOWN Pidx=" + Pidx + ",pos=" + Ppos + ",shanten=" + shanten + ",v=" + v);//~1309R~
        	}                                                      //~1309R~
        }//~1127I~
        else                                                       //~1127I~
        if (shanten<PmyShanten) //step up to ron                   //~1215R~
        	v=(MAX_SHANTEN-shanten)*DV_SHANTEN;   //-1000; add plus to highvalue to more discardable//~1215R~//~1309R~
        else                                                       //~1215I~
            v=0;                                                   //~1215I~
        if (Dump.Y) Dump.println("RADEval.chkShantenAtDiscard pos="+Ppos+",idx="+Pidx+",pos="+Ppos+",old="+itsHandValue[Pidx]);//~1214I~//~1215R~
        itsHandValue[Pidx]+=v;
        if (Dump.Y) Dump.println("RADEval.chkShantenAtDiscard eswn="+eswnDiscard+",idx="+Pidx+",pos="+Ppos+",PmyShanten="+PmyShanten+",shanten="+shanten+",v="+v+",new v="+itsHandValue[Pidx]);//~1126R~//~1127R~//~1131R~//~1215R~//~1219R~
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
        if (Dump.Y) Dump.println("RADEval.evaluateTileWord Ppos="+Ppos+",ctrHand="+ctr+",exposed="+exposed+",intent=x"+Integer.toHexString(intent));//~1131R~//~1215R~//~1307R~
        int expected=PIECE_DUPCTR-ctr-exposed;                     //~1215I~
        if (ctr==1 && expected==0)                                 //~1217I~
        {                                                          //~1217I~
			v+=DV_WORD_ORPHAN;    	//950 more discardable//~1217I~//~1220R~
            if (RADS.isDoraOpen(Ppos))                             //~1220I~
            {                                                      //~1220I~
				v-=DV_DORA;                                        //~1220I~
		        if (Dump.Y) Dump.println("RADEval.evaluateTileWord remove dora effect Ppos="+Ppos+",ctrHand="+ctr+",exposed="+exposed);//~1220I~
            }                                                      //~1220I~
		}                                                          //~1217I~
        else                                                       //~1217I~
        {                                                          //~1217I~
	        v+=DVS_WORD[ctr-1];                                         //~1215M~//~1217I~//~1307R~
            if (ctr<=2)                                                //~1215I~//~1217R~
            {                                                          //~1215I~//~1217R~
                if (expected>0)                                        //~1215I~//~1217R~
                {                                                      //~1215I~//~1217R~
            		if (ctr==1 && Ppos>=OFFS_WORDTILE_DRAGON && (intent & INTENT_3DRAGON)!=0)//~1307I~
                    {                                              //~1307I~
                    	v+=DV_WORD_YAKU*4+expected*DV_WORD_NOTEXPOSED;    //(950)-200*4-90*remaining//~1307I~
				        if (Dump.Y) Dump.println("RADEval.evaluateTileWord 3dragon Ppos="+Ppos+",ctrHand="+ctr+",exposed="+exposed+",v="+v);//~1307I~
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
        if (Dump.Y) Dump.println("RADEval.evaluateTileWord pos="+Ppos+",v="+v+",expected="+expected);//~1131R~//~1220R~
        return v;
    }
    //***********************************************************************
    private void evaluateIntent(int Pidx/*in tdsHand*/,int Ppos/*in itsHand*/)
    {
        if (Dump.Y) Dump.println("RADEval.evaluateIntent idx="+Pidx+",pos="+Ppos+",eswnDiscard="+eswnDiscard+",myIntent=x"+Integer.toHexString(RADS.myIntent));//~1131R~//~1213R~//~1215R~
//      int intent=RSP.intent;                                     //~1302R~
        int intent=RSP.getIntent();                                //~1302I~
        if ((intent & INTENT_7PAIR)!=0)
        {                                                          //~1213I~
	        if (Dump.Y) Dump.println("RADEval.evaluateIntent idx="+Pidx+",pos="+Ppos+",ctr="+itsHand[Ppos]+",sw7PairKan="+RS.sw7PairKan);//~1216I~
            switch(itsHand[Ppos])                                  //~1216I~
            {                                                      //~1216I~
            case 2:                                                //~1216I~
	    		if (Dump.Y) Dump.println("RADEval.evaluateIntent 7pair pair idx="+Pidx+",old="+itsHandValue[Pidx]);//~1214I~//~1216R~
                itsHandValue[Pidx]+=DV_7PAIR;  //-30,000           //~1313R~
	        	if (Dump.Y) Dump.println("RADEval.evaluateIntent 7pair pair idx="+Pidx+",handVal="+itsHandValue[Pidx]);//~1213I~//~1214R~//~1216R~
                break;                                             //~1216I~
            case 3:                                                //~1216I~
	    		if (Dump.Y) Dump.println("RADEval.evaluateIntent 7pair triplet idx="+Pidx+",old="+itsHandValue[Pidx]);//~1216I~
                itsHandValue[Pidx]+=DV_7PAIR3;  //-20,000                   //~1216I~//~1313R~
	        	if (Dump.Y) Dump.println("RADEval.evaluateIntent 7pair triplet idx="+Pidx+",handVal="+itsHandValue[Pidx]);//~1216I~
                break;                                             //~1216I~
            case 4:                                                //~1216I~
            	if (RS.sw7PairKan)                                 //~1216I~
                {                                                  //~1216I~
	    			if (Dump.Y) Dump.println("RADEval.evaluateIntent 7pair quad idx="+Pidx+",old="+itsHandValue[Pidx]);//~1216I~
                	itsHandValue[Pidx]+=DV_7PAIR;  //-30,000                //~1216I~//~1313R~
	        		if (Dump.Y) Dump.println("RADEval.evaluateIntent 7pair quad idx="+Pidx+",handVal="+itsHandValue[Pidx]);//~1216I~
                }                                                  //~1216I~
            default:	//1                                        //~1313I~
		        if (tdsHand[Pidx].isRed5()                         //~1313I~
                ||  RADS.isDoraOpen(Ppos))                         //~1313I~
	                itsHandValue[Pidx]+=DV_DORA;	//-600         //~1313I~
            }                                                      //~1216I~
        }                                                          //~1213I~
        else                                                       //~1213I~
        if ((intent & INTENT_13ORPHAN)!=0)
        {                                                          //~1213I~
        	setValue13Orphan(true,Pidx,Ppos);                      //~1213R~
        }                                                          //~1213I~
        else                                                       //~1213I~
        {                                                          //~1213I~
        	if ((intent & INTENT_TANYAO)!=0)                       //~1213R~
//      		setValueTanyao(false/*sw13Orphan*/,true/*swMe*/);  //~1213R~
        		setValueTanyao(false/*sw13Orphan*/,true/*swMe*/,Pidx,Ppos);//~1213I~
            else                                                   //~1217I~
        	if ((intent & INTENT_CHANTA)!=0)                       //~1217I~
        		setValueChanta(true/*swMe*/,Pidx,Ppos);            //~1217I~
        	if ((RADS.myIntent & INTENT_SAMECOLOR_ANY)!=0)         //~1213R~
//      		setValueSameColor(true/*swMe*/);                   //~1213R~
        		setValueSameColor(true/*swMe*/,Pidx,Ppos);          //~1213I~
        	if ((RADS.myIntent & INTENT_ALLSAME)!=0)    //toitoi   //~1218I~
        		setValueAllSame(true/*swMe*/,Pidx,Ppos);           //~1218I~
        }                                                          //~1213I~
    }
    //***********************************************************************//~1307I~
    //*at shanten0, select expected tile for 7Pair                 //~1307I~
    //***********************************************************************//~1307I~
    private void evaluateDiscard7Pair()                            //~1307I~
    {                                                              //~1307I~
        if (Dump.Y) Dump.println("RADEval.evaluateDiscard7Pair");  //~1307I~
        int idxDiscard=-1;                                         //~1307I~
        int maxExpectedWord=0,maxExpected=0,maxExpectedFuriten=0,idxMaxExpectedWord=-1,idxMaxExpected=-1,idxMaxExpectedFuriten=-1;//~1307I~
        for (int ii=0;ii<ctrHand;ii++)                             //~1307I~
        {                                                          //~1307I~
        	int pos=itsHandPos[ii];                                //~1307I~
            int expected=PIECE_DUPCTR-itsHand[pos]-RS.itsExposed[pos]; //~1307I~
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
            if (expected>maxExpected)                              //~1307I~
            {                                                      //~1307I~
            	maxExpected=expected;                              //~1307I~
                idxMaxExpected=ii;                                 //~1307I~
            }                                                      //~1307I~
            if (furiten>0) //link furiten                          //~1307I~
        	{                                                      //~1307I~
	            if (expected>maxExpectedFuriten)                   //~1307I~
    	        {                                                  //~1307I~
        	    	maxExpectedFuriten=expected;                   //~1307I~
            	    idxMaxExpectedFuriten=ii;                      //~1307I~
            	}                                                  //~1307I~
            }                                                      //~1307I~
            if (pos>=OFFS_WORDTILE && RS.itsExposed[pos]>0)           //~1307I~
        	{                                                      //~1307I~
	            if (expected>maxExpectedWord)                      //~1307I~
    	        {                                                  //~1307I~
        	    	maxExpectedWord=expected;                      //~1307I~
            	    idxMaxExpectedWord=ii;                         //~1307I~
            	}                                                  //~1307I~
            }                                                      //~1307I~
        }                                                          //~1307I~
        if (maxExpectedWord>=maxExpectedFuriten)                    //~1307I~
        	idxDiscard=idxMaxExpectedWord;                         //~1307I~
        else                                                       //~1307I~
        if (maxExpectedFuriten>=maxExpected)                        //~1307I~
        	idxDiscard=idxMaxExpectedFuriten;                      //~1307I~
        else                                                       //~1307I~
        	idxDiscard=idxMaxExpected;                             //~1307I~
        if (idxDiscard!=-1)                                        //~1307I~
        {                                                          //~1308I~
	        if (Dump.Y) Dump.println("RADEval.evaluateDiscard7Pair old itsHandValue["+idxDiscard+"]="+itsHandValue[idxDiscard]);//~1307I~//~1308I~
            itsHandValue[idxDiscard]=DV_DISCARD;	//100,000      //~1307I~
        	if (Dump.Y) Dump.println("RADEval.evaluateDiscard7Pair new itsHandValue["+idxDiscard+"]="+itsHandValue[idxDiscard]);//~1307I~//~1308I~
        }                                                          //~1308I~
        if (Dump.Y) Dump.println("RADEval.evaluateDiscard7Pair idxMaxExpected="+idxMaxExpected+",expected="+maxExpected);//~1307I~
        if (Dump.Y) Dump.println("RADEval.evaluateDiscard7Pair idxMaxExpectedWord="+idxMaxExpectedWord+",expected="+maxExpectedWord);//~1307I~
        if (Dump.Y) Dump.println("RADEval.evaluateDiscard7Pair idxMaxExpectedFuriten="+idxMaxExpectedFuriten+",expected="+maxExpectedFuriten);//~1307I~
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
        if (Dump.Y) Dump.println("RADEval.chkFuriten rc="+rc+",pos="+Ppos+",eswn="+eswnDiscard);//~1307I~
        return rc;
    }                                                              //~1307I~
    //***********************************************************************
    private void setValue13Orphan(boolean PswMe,int Pidx,int Ppos) //~1213R~
    {
        if (Dump.Y) Dump.println("RADEval.setValue13Orphan wMe="+PswMe+",idx="+Pidx+",pos="+Ppos);      //~1131R~//~1213R~
        setValueTanyao(true/*sw13Orphan*/,PswMe,Pidx,Ppos);        //~1213R~
    }
//    //***********************************************************************//~1213R~
//    private void setValueTanyao(boolean Psw13Orphan,boolean PswMe)//~1213R~
//    {                                                            //~1213R~
//        if (Dump.Y) Dump.println("RADEval.setValueTanyao sw13Orphan="+Psw13Orphan);//~1131R~//~1213R~
//        if (Dump.Y) Dump.println("RADEval.setValueTanyao swMe="+PswMe+",itsHandValue="+ Arrays.toString(itsHandValue));//~1127I~//~1131R~//~1213R~
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
//        if (Dump.Y) Dump.println("RADEval.setValueTanyao after itsHandValue="+Arrays.toString(itsHandValue));//~1127I~//~1131R~//~1213R~
//    }                                                            //~1213R~
    //***********************************************************************//~1213I~
    private void setValueTanyao(boolean Psw13Orphan,boolean PswMe,int Pidx,int Ppos)//~1213I~
    {                                                              //~1213I~
        if (Dump.Y) Dump.println("RADEval.setValueTanyao sw13Orphan="+Psw13Orphan+",swMe="+PswMe+",idx="+Pidx+",pos="+Ppos);//~1213I~
        if (Dump.Y) Dump.println("RADEval.setValueTanyao swMe="+PswMe+",old itsHandValue="+itsHandValue[Pidx]);//~1213I~//~1214R~
        int type=Ppos/CTR_NUMBER_TILE; int num=Ppos%CTR_NUMBER_TILE;//~1213I~
        if (type==TT_JI || num==0 || num==8)                       //~1213I~
        {                                                          //~1213I~
            if (Psw13Orphan)                                       //~1213I~
                itsHandValue[Pidx]+=PswMe ? DV_13ORPHAN : DV_13ORPHAN_OTHER;//~1213I~
            else                                                   //~1213I~
            {                                                      //~1219I~
            	if (RSP.swAllInHand)	                           //~1219I~
                	itsHandValue[Pidx]+=DV_NOT_TANYAO;	//10000                 //~1213I~//~1219R~
                else                                               //~1219I~
                	itsHandValue[Pidx]+=DV_NOT_TANYAO_AFTER_CALL;	//20000//~1219I~//~1220R~//~1221R~
            }                                                      //~1219I~
        }                                                          //~1213I~
        if (Dump.Y) Dump.println("RADEval.setValueTanyao after itsHandValue="+itsHandValue[Pidx]);//~1213I~//~1214R~
    }                                                              //~1213I~
    //***********************************************************************//~1217I~
    private void setValueChanta(boolean PswMe,int Pidx,int Ppos)   //~1217I~
    {                                                              //~1217I~
        if (Dump.Y) Dump.println("RADEval.setValueChanta swMe="+PswMe+",idx="+Pidx+",pos="+Ppos);//~1217I~
        if (Dump.Y) Dump.println("RADEval.setValueChanta old itsHandValue="+itsHandValue[Pidx]);//~1217I~
        int type=Ppos/CTR_NUMBER_TILE; int num=Ppos%CTR_NUMBER_TILE;//~1217I~
        if (type!=TT_JI && num>2 && num<6)      //456              //~1217I~
        {                                                          //~1217I~
            if (RSP.swAllInHand)                                   //~1220I~
        		itsHandValue[Pidx]+=DV_NOT_CHANTA;                     //~1217I~//~1220R~
            else                                                   //~1220I~
                itsHandValue[Pidx]+=DV_NOT_CHANTA_AFTER_CALL;	//20000//~1220R~//~1221R~
        }                                                          //~1217I~
        if (Dump.Y) Dump.println("RADEval.setValueChanta after itsHandValue="+itsHandValue[Pidx]);//~1217I~
    }                                                              //~1217I~
    //***********************************************************************//~1218I~
    private void setValueAllSame(boolean PswMe,int Pidx,int Ppos)  //~1218I~
    {                                                              //~1218I~
        if (Dump.Y) Dump.println("RADEval.setValueAllSame swMe="+PswMe+",idx="+Pidx+",pos="+Ppos);//~1218I~
        if (Dump.Y) Dump.println("RADEval.setValueAllSame old itsHandValue="+itsHandValue[Pidx]);//~1218I~
        int type=Ppos/CTR_NUMBER_TILE; int num=Ppos%CTR_NUMBER_TILE;//~1218I~
        if (itsHand[Ppos]>=2)                                      //~1218I~
        {                                                          //~1218I~
        	itsHandValue[Pidx]+=DV_ALL_SAME;	//-20000                      //~1218I~//~1221R~
        }                                                          //~1218I~
        if (Dump.Y) Dump.println("RADEval.setValueAllSame after itsHandValue="+itsHandValue[Pidx]);//~1218I~
    }                                                              //~1218I~
    //***********************************************************************//~1213I~
    private void setValueSameColor(boolean PswMe,int Pidx,int Ppos)//~1213I~
    {                                                              //~1213I~
        if (Dump.Y) Dump.println("RADEval.setValueSameColor swMe="+PswMe+",idx="+Pidx+",pos="+Ppos);//~1213I~//~1302R~
        if (Dump.Y) Dump.println("RADEval.setValueSameColor eswnDiscard="+eswnDiscard+",intent=x"+Integer.toHexString(RADS.myIntent));//~1213I~
        int color;                                                 //~1213I~
        if ((RADS.myIntent & INTENT_SAMECOLOR_MAN)!=0)             //~1213I~
            color=0;                                               //~1213I~
        else                                                       //~1213I~
        if ((RADS.myIntent & INTENT_SAMECOLOR_PIN)!=0)             //~1213I~
            color=1;                                               //~1213I~
        else                                                       //~1213I~
            color=2;                                               //~1213I~
        int tdsType=Ppos/CTR_NUMBER_TILE;                          //~1213I~
	    if (Dump.Y) Dump.println("RADEval.setValueSameColor pos="+Ppos+",idx="+Pidx+",old="+itsHandValue[Pidx]);//~1214I~
        if (tdsType!=TT_JI && tdsType!=color)                      //~1213I~
        {                                                          //~1213I~
            if (PswMe)     //my intent                                        //~1213I~//~1216R~
                itsHandValue[Pidx]+=DV_NOT_SAMECOLOR;   //100,000 more discardable   //~1213I~//~1216R~//~1303R~
        }                                                          //~1213I~
        else                                                       //~1213I~
        {                                                          //~1213I~
            if (!PswMe)   //other player causion                                         //~1213I~//~1216R~
                itsHandValue[Pidx]+=DV_SAMECOLOR_OTHER; //-10000 less discardable           //~1213I~//~1216R~
        }                                                          //~1213I~
        if (Dump.Y) Dump.println("RADEval.setValueSameColor pos="+Ppos+",itsHandValue["+Pidx+"]="+itsHandValue[Pidx]);//~1213R~//~1214R~
    }                                                              //~1213I~
    //***********************************************************************
    //*chk shanten up by next take
    //*if myShanten>=1
    //***********************************************************************
    private int evaluateNextTake(int Pidx,int Ppos,int Pshanten,boolean PswSameAsPrev,int Pintent)//~1220R~//~1309R~
    {
        if (Dump.Y) Dump.println("RADEval.evaluateNextTake idx="+Pidx+",pos="+Ppos+",shanten="+Pshanten+",swSameAsPrev="+PswSameAsPrev);//~1131R~//~1212R~//~1220R~
        if (Dump.Y) Dump.println("RADEval.evaluateNextTake old itsHandValue["+Pidx+"]="+itsHandValue[Pidx]);//~1216I~
        if (Pshanten>HV_SHANTEN_TRYNEXT) //from shanten>2          //~1302R~
        {                                                          //~1309I~
    		chkShantenAtDiscard(Pshanten,Pidx,Ppos,Pintent);   //~1309R~
        	return 0;
        }                                                          //~1309I~
        int han=0;    	                                           //~1220I~
        if (!PswSameAsPrev)                                        //~1220I~
        {                                                          //~1220I~
            itsHand[Ppos]--;                                       //~1220R~
            int old=itsHandValue[Pidx];                            //~1303I~
            han=tryNextTake(Pidx,Ppos,Pshanten,ctrHand-1,Pintent);         //~1220R~//~1309R~
            diffTryNext=itsHandValue[Pidx]-old;                    //~1303I~
            itsHand[Ppos]++;                                       //~1220R~
        }                                                          //~1220I~
        else                                                       //~1303I~
        {                                                          //~1303I~
	        if (Dump.Y) Dump.println("RADEval.evaluateNextTake sameasPrev old pos="+Ppos+",diffTryNext="+diffTryNext+",itsHandValue["+Pidx+"]="+itsHandValue[Pidx]);//~1303I~
			itsHandValue[Pidx]+=diffTryNext;                       //~1303I~
	        if (Dump.Y) Dump.println("RADEval.evaluateNextTake sameasPrev eswn="+eswnDiscard+",ctrWinningTileTryNext="+ctrWinningTileTryNext+",itsHandValue["+Pidx+"]="+itsHandValue[Pidx]);//~1303I~
        }                                                          //~1303I~
//      if (Dump.Y) Dump.println("RADEval.evaluateNextTake exit old pos="+Ppos+",itsHandValue["+Pidx+"]="+itsHandValue[Pidx]);//~1216I~//~1220R~//~1303R~
//      itsHandValue[Pidx]+=ctrWinningTileTryNext*DV_BY_CTR_WINTILE;//100,000//~1216R~//~1220R~//~1303R~
//      if (Dump.Y) Dump.println("RADEval.evaluateNextTake exit eswn="+eswnDiscard+",ctrWinningTileTryNext="+ctrWinningTileTryNext+",itsHandValue["+Pidx+"]="+itsHandValue[Pidx]);//~1216R~//~1220R~//~1301R~//~1303R~
        return han;
    }
    //***********************************************************************
    //*Ppos:dropped pos to calc Pshanten
    //***********************************************************************
    private int tryNextTake(int Pidx/*idx of tdsHand try to discard*/,int Ppos/*of tdsHand[Pidx]*/,int Pshanten,int PctrHand,int Pintent)//~1126R~//~1309R~
    {
        if (Dump.Y) Dump.println("RADEval.tryNextTake idx="+Pidx+",pos="+Ppos+",shanten="+Pshanten+",ctrHand="+PctrHand);//~1131R~
        if (Dump.Y) Dump.println("RADEval.tryNextTake before itsExposed="+Utils.toString(RS.itsExposed,9));//~1218R~
        if (Dump.Y) Dump.println("RADEval.tryNextTake old    itsHandValue="+itsHandValue[Pidx]);//~1127R~//~1131R~//~1220I~
        int hanMax=0;
    	int shanten;                                               //~1127I~
//      int shantenUpMax=0;                                        //~1127I~//~1218R~
        int ctrWinningTile=0;
        int ctrShantenDownCase=0;//~1216I~
        //*******************                                      //~1127I~
        if (Dump.Y) Dump.println("RADEval.tryNextTake chk shantenDown pos="+Ppos+",idx="+Pidx+",Pshanten="+Pshanten);//~1220I~
//        shanten=AG.aShanten.getShantenMin(itsHand,PctrHand); //chk drop collapse meld//~1218I~//~1220R~
//        if (Dump.Y) Dump.println("RADEval.tryNextTake chk shantenDown pos="+Ppos+",idx="+Pidx+",shanten="+shanten+",Pshanten="+Pshanten);//~1127I~//~1131R~//~1220R~
//        if (shanten>Pshanten)   //shanten down                     //~1218I~//~1220R~
//        {                                                          //~1127I~//~1220R~
//            if (Dump.Y) Dump.println("RADEval.tryNextTake shanten Down pos="+Ppos+",idx="+Pidx+",shanten="+shanten+",Pshanten="+Pshanten+",old="+itsHandValue[Pidx]);//~1127I~//~1131R~//~1214R~//~1220R~
//            itsHandValue[Pidx]+=DV_SHANTEN_DOWN;       //-100000  //do not discard//~1218R~//~1220R~
//            if (Dump.Y) Dump.println("RADEval.tryNextTake idx="+Pidx+",itsHandValue="+itsHandValue[Pidx]);//~1213I~//~1220R~
//            ctrWinningTileTryNext=0;                             //~1220R~
//            return 0; //han                                        //~1127I~//~1220R~
//        }                                                          //~1127I~//~1220R~
        boolean swShantenDownfalse;                                //~1127I~
        int ctrTileShantenUp=0;                                    //~1218I~
        for (int ii=0;ii<CTR_TILETYPE;ii++)
        {
        	if (ii==Ppos)            //same as discard
            	continue;                                          //~1127M~
        	int ctrRemain=PIECE_DUPCTR-(itsHand[ii]+RS.itsExposed[ii]);//~1218I~
		    if (Dump.Y) Dump.println("RADEval.tryNextTake ctrRemain="+ctrRemain+",pos="+ii);//~1218I~
        	if (ctrRemain<=0)                                      //~1218I~
            	continue;                                          //~1206I~//~1218M~
//  		if (!isCanMakeSet(ii,itsHand))                         //~1218R~
//              continue;                                          //~1218R~
        	itsHand[ii]++;   //try taken
//  		shanten=RADS.getShanten(false/*PswIntent*/,PctrHand+1);//~1127R~//~1218R~
	        shanten=AG.aShanten.getShantenMin(itsHand,PctrHand); //chk drop collapse meld//~1218I~
            int shantenUp=Pshanten-shanten;                        //~1127I~
//          if (shantenUp>0)                                       //~1127R~//~1220R~
            if (shantenUp>=0)     //evaluate even if shanten same  //~1220I~
            {
		        if (Dump.Y) Dump.println("RADEval.tryNextTake eswn="+eswnDiscard+",old ctrTileShantenUP="+ctrTileShantenUp+",pos="+ii);//~1218I~//~1220R~
                if (shantenUp>0)                                   //~1220I~
	                ctrTileShantenUp+=ctrRemain;                       //~1218R~//~1220R~
		        if (Dump.Y) Dump.println("RADEval.tryNextTake Pidx="+Pidx+",Ppos="+Ppos+",new ctrTileShantenUP="+ctrTileShantenUp+",pos="+ii);//~1218R~
              for(;;)                                              //~1130I~
              {                                                    //~1130I~
//          	if (shantenUp>shantenUpMax)                        //~1127I~//~1218R~
//              	shantenUpMax=shantenUp;                          //~1127I~//~1218R~
		        if (Dump.Y) Dump.println("RADEval.tryNextTake shantenUp idx="+Pidx+",pos="+ii);//~1127R~//~1131R~
            	if (shanten==-1) //ron by next take
                {
//  				if (RAUtils.isFuriten(eswnDiscard,ii))	//ron next take but ron tile is furiteniscard//~1220R~
    				if (RSP.isFuritenSelf(ii))	//ron next take but ron tile is furiteniscard//~1220I~
                		break;                                     //~1130I~
                    int han=getRonValue(itsHand,ii/*emulated takeOne*/);
                    if (han>hanMax)
                    {
                    	hanMax=han;
                    }
                    ctrWinningTile+=PIECE_DUPCTR-(itsHand[ii]-1)-RS.itsExposed[ii];//~1216I~
			        if (Dump.Y) Dump.println("RADEval.tryNextTake eswn="+eswnDiscard+",shanten=-1 pos="+ii+",ctrWinningTile="+ctrWinningTile);//~1216I~//~1220R~
                }
                else
            	if (shanten==0) //tenpai
                {
//  				if (RADS.chkFuriten())	//chk any winning tile is furiten//~1220R~
    				if (RADS.chkFuritenSelf())	//chk any winning tile is furiten//~1220I~
                		break;                                     //~1130I~
                }
//              itsHandValue[Pidx]+=DV_SHANTEN_UP;                 //~1126R~//~1127R~
		        if (Dump.Y) Dump.println("RADEval.tryNextTake shantenUp shanten="+shanten+",Pshanten="+Pshanten);//~1127R~//~1131R~
                break;                                             //~1130I~
              } // to restore itsHand ctr                          //~1130I~
            }
            else                                                   //~1220I~
            {                                                      //~1220I~
                ctrShantenDownCase++;                              //~1220I~
				if (Dump.Y) Dump.println("RADEval.tryNextTake shantenDown pos="+Ppos+",idx="+Pidx+",shanten="+shanten+",Pshanten="+Pshanten);//~1220I~
            }                                                      //~1220I~
        	itsHand[ii]--;
        }
//      if (shantenUpMax>0)                                        //~1127I~//~1218R~
        if (ctrTileShantenUp>0)                                    //~1218I~
        {                                                          //~1127I~
	    	if (Dump.Y) Dump.println("RADEval.tryNextTake idx="+Pidx+",old="+itsHandValue[Pidx]);//~1214I~
//  		itsHandValue[Pidx]+=DV_SHANTEN_UP*shantenUpMax;	//add plus more discardable//~1216R~//~1218R~
    		itsHandValue[Pidx]+=DV_SHANTEN_UP+DV_SHANTEN_UP_TILE*ctrTileShantenUp;	//add plus more discardable//~1218I~
//* 		                     100,000     +100               *ctrTileShantenUp;	//add plus more discardable//~1220R~
	        if (Dump.Y) Dump.println("RADEval.tryNextTake UP after Pshanten="+Pshanten+",idx="+Pidx+",itsHandValue="+itsHandValue[Pidx]);//~1127I~//~1131R~//~1220R~
        }                                                          //~1127I~
        ctrWinningTileTryNext=ctrWinningTile;                      //~1216I~
        if (ctrTileShantenUp==0 && ctrShantenDownCase>0)           //~1220I~
        {                                                          //~1220I~
            if (Dump.Y) Dump.println("RADEval.tryNextTake shantenDown pos="+Ppos+",idx="+Pidx+",ctrTileShantenUp="+ctrTileShantenUp+",ctrShantenDowncase="+ctrShantenDownCase+",Pshanten="+Pshanten+",old="+itsHandValue[Pidx]);//~1220I~//+va70R~
        	if ((Pintent & INTENT_SAMECOLOR_ANY)==0)               //~1309I~
    	        itsHandValue[Pidx]+=DV_SHANTEN_DOWN;       //-100000  //do not discard//~1220I~//~1309R~
            if (Dump.Y) Dump.println("RADEval.tryNextTake shantenDown intent="+Integer.toHexString(RADS.myIntent)+",idx="+Pidx+",itsHandValue="+itsHandValue[Pidx]);//~1220I~//~1309R~
            ctrWinningTileTryNext=0;                               //~1220I~
        }                                                          //~1220I~
        if (Dump.Y) Dump.println("RADEval.tryNextTake eswnDiscd="+eswnDiscard+",Pidx="+Pidx+",Ppos="+Ppos+",hanMax="+hanMax+",ctrTileShanteUp="+ctrTileShantenUp+",ctrWinningTile="+ctrWinningTile);//~1127R~//~1131R~//~1213R~//~1216R~//~1218R~
        return hanMax;
    }
    //***********************************************************************
    private int getRonValue(int[] PitsHand,int Ppos)
    {
    	int han=0;
        if (Dump.Y) Dump.println("RADEval.getRonValue");           //~1131R~
        AG.aRARon.getRonValue(playerDiscard,PitsHand,Ppos);
        return han;
    }
    //***********************************************************************//~1227I~//~1302M~
    //*search pillow candidate                                     //~1227I~//~1302M~
    //***********************************************************************//~1227I~//~1302M~
    private int searchPillow4(int Pintent)                                   //~1227I~//~1302R~
    {                                                              //~1227I~//~1302M~
        int posPillow=-1,posPillow2=-1,posPillow0=-1,num;                        //~1227I~//~1301R~//~1302M~
        //*****************************                            //~1227I~//~1302M~
        if (Dump.Y) Dump.println("RADSENum.searchPillow4");        //~1227I~//~1302M~
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
        if (Dump.Y) Dump.println("RADSENum.searchPillow posPillow="+posPillow+",posPillow0="+posPillow0+",posPillow2="+posPillow2+",itsHand="+Utils.toString(itsHand,9));//~1227R~//~1301R~//~1302M~
        return posPillow;                                          //~1302M~
    }                                                              //~1227I~//~1302M~
}//class RADEval                                                   //~1131R~
