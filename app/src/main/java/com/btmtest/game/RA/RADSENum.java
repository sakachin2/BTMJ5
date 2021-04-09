//*CID://+va70R~: update#= 440;                                    //+va70R~
//**********************************************************************
//2021/03/27 va70 Notify mode onTraining mode(notify pon/kam/chii/ron to speed up)//+va70I~
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
public class RADSENum                                              //~1225R~
{

    private static final int CONTID1      =-1;                           //~1226R~//~1227R~//~1228M~
    private static final int CONTIDPDUP   =-2;                     //~1301I~
    private static final int CONTID2DUP   =-4; 	                   //~1227R~//~1228M~//~1301I~
    private static final int CONTID3DUP   =-6;                     //~1227R~//~1228M~//~1301R~
    private static final int CONTIDP      =-3;                           //~1226I~//~1227R~//~1228M~//~1301I~
    private static final int CONTIDK      =-4;                     //~1301I~
    private static final int CONTID2      =-5;                           //~1226R~//~1227R~//~1228M~//~1301I~
    private static final int CONTID3      =-7;                     //~1227R~//~1228M~//~1301R~
    private static final int CONTID_PILLOW=-2;                     //~1227R~//~1228M~
    private static final int CONTIDO1     = 4;                     //~1301I~
    private static final int CONTIDO2     =-2;                     //~1301I~
    private static final int CONTIDO3     =-7;                     //~1301I~
    private static final int DV_NUMBER_CONTID   =100;              //~1227I~//~1228M~//~1309R~
    private static final int DV_CONTVALUE_DORA=-1;   //add 100 for each dora                                                               //~1226I~//~1227R~//~1228M~
    private static final int CONT_SEQ=0;                           //~1226I~//~1228M~
    private static final int CONT_DUP=1;                           //~1226I~//~1228M~
    //***********************************************************************//~1225I~//~1228M~
	private RoundStat RS;
	private RoundStat.RSPlayer RSP;
	private RADSmart RADS;
	private RADSOther RADSO;
	private RADSEval RADSE;                                         //~1302I~
    private int ctrHand;
    private int[] itsHand;	//34 entry
    private int[] itsHandValue,itsHandPos;                         //~1122R~
    private int[] itsHandNumberStat=new int[HANDCTR_TAKEN];
    private int[] itsHandPosMeld=new int[HANDCTR_TAKEN];           //~1214I~
    private int[] itsHandPosMeldCopy=new int[HANDCTR_TAKEN];       //~1217I~
    private int[] itsHandValueMeld=new int[HANDCTR_TAKEN];         //~1214I~
    private int[] itsHandMeldDora=new int[HANDCTR_TAKEN];          //~1216I~
    private boolean[] btsHandMeldDoraIsRed5=new boolean[HANDCTR_TAKEN];//~1302I~
    private int[] itsHandMeldWeight=new int[HANDCTR_TAKEN];         //~1225I~
    private int[] itsHandWK=new int[CTR_NUMBER_TILE];               //~1226I~
    private int[] itsContID=new int[CTR_NUMBER_TILE];               //~1226I~
    private int[] itsHandIdxStack=new int[OFFS_WORDTILE*PIECE_DUPCTR];//~1228I~//~1302M~
    private int[] itsHandIdxStackCtr=new int[OFFS_WORDTILE];       //~1228I~//~1302M~
    private int   posTopItsHand;                                   //~1226I~
//  private int[] itsStatHand,itsStatPair;                     //~1114R~//~1116R~//~1121I~//~1225R~
    private int[] itsDoraOpen=new int[2*(1+MAXCTR_KAN)];
    private int eswnDiscard;                                       //~1225I~
//  private int hanMax;                                            //~1225R~
//    private int ctrMan,ctrPin,ctrSou,ctrWord;                    //~1131R~
    private TileData[] tdsHand;                                    //~1121I~//~1122R~
//  private int ctrWinningTileTryNext;                             //~1216I~//~1220R~//~1225R~
    private boolean swMeld3=true;                                 //~1225I~
    private boolean swKanchan2=true;                               //~1227I~
    private int intent,posPillow;                                  //~1302R~
//*************************
	public RADSENum()                                              //~1225R~//~1302R~
    {
        if (Dump.Y) Dump.println("RADSENum.Constructor");           //~1131R~//~1225R~
        init();
    }
    //*********************************************************
    private void  init()
    {
    	RADS=AG.aRADSmart;
    	RADSE=AG.aRADSEval;                                        //~1302I~
    	RS=AG.aRoundStat;
    	RADSO=RADS.RADSO;
    }
    //*********************************************************    //~1225I~
    //*from RADSEval.evaluateTile                                  //~1302I~
    //*********************************************************    //~1302I~
    public void chkNumberMeld2(int PeswnDiscard,int Pintent,int[] PitsHand,int[] PitsHandPos,int[] PitsHandValue,int PctrHand)//~1225R~//~1302R~
    {                                                              //~1225I~
    	eswnDiscard=PeswnDiscard;                                  //~1225I~
        RSP=RS.RSP[eswnDiscard];                                   //~1225I~
        tdsHand=RADS.tdsHand;                                      //~1225I~
//      intent=RSP.getIntent();                                     //~1225I~//~1226I~//~1302R~
        intent=Pintent;                                            //~1302I~
    	itsHand=PitsHand; itsHandPos=PitsHandPos; itsHandValue=PitsHandValue; ctrHand=PctrHand;//~1225I~
        setupSeqMeldDora(ctrHand);  //setup dora indicator of tdsHand sequence//~1302I~
//      setupWork42();                                             //~1228I~//~1302R~
//      if (swMeld3)         //TODO test                           //~1225I~//~1302R~
//  		chkNumberMeld4();                                      //~1225I~//~1226R~//~1228R~
    		chkNumberMeld42();                                     //~1228I~
//      else                                                       //~1225I~//~1302R~
//      	chkNumberMeld2();                                      //~1225R~//~1302R~
                                                                   //~1225I~
        tdsHand=null;                                              //~1225I~
    	itsHand=null    ; itsHandPos=null       ; itsHandValue=null         ;//~1225I~
    }                                                              //~1225I~
    //***********************************************************************//~1214I~
    //*from RADSEval.evaluateTile                                  //~1302I~
    //***********************************************************************//~1302I~
    public int evaluateTileNumberMeld(int Pintent,int Pidx,TileData Ptd)      //~1214I~//~1225R~
    {                                                              //~1214I~
    	int v=0;                                                   //~1214I~
        if (Ptd.isRed5())                                          //~1214I~
        {                                                          //~1214I~
            v+=DV_DORA;                                            //~1214I~
	        if (Dump.Y) Dump.println("RADSENum.evaluateTileNumberMeld Red5 idx="+Pidx+",by red5="+v);//~1214I~//~1225R~
        }                                                          //~1214I~
        int num=Ptd.number;                                        //~1214I~
        if ((Pintent & INTENT_CHANTA)!=0)                          //~1225I~
	        v+=DVS_NUMBER_WEIGHT_CHANTA[num];                      //~1225I~
        else                                                       //~1225I~
	        v+=DVS_NUMBER_WEIGHT[num];                                 //~1214I~//~1225R~
	    if (Dump.Y) Dump.println("RADSENum.evaluateTileNumberMeld idx="+Pidx+",old="+itsHandValueMeld[Pidx]);//~1214R~//~1225R~
        v+=itsHandValueMeld[Pidx];                                 //~1214I~
        v+=Utils.getRandom(DV_WORD_RANDOM_MAX);   //add 0->9 randomly//~1217I~
        if (Dump.Y) Dump.println("RADSENum.evaluateTileNumberMeld intent="+Integer.toHexString(Pintent)+",idx="+Pidx+",type="+Ptd.type+",num="+num+",v="+v+",itsHandValueMeld="+itsHandValueMeld[Pidx]);//~1214I~//~1225R~
        return v;                                                  //~1214I~
    }                                                              //~1214I~
    //***********************************************************************//+va70R~
    //*from RADSEval.evaluateTile                                  //+va70R~
    //***********************************************************************//+va70R~
    public int evaluateForReach(int Ppos,int Pintent,TileData PtdDiscard)//+va70R~
    {                                                              //+va70R~
    	int v=0;                                                   //+va70R~
        if (PtdDiscard.isRed5())                                   //+va70R~
        {                                                          //+va70R~
            v+=DV_DORA;                                            //+va70R~
	        if (Dump.Y) Dump.println("RADSENum.evaluateTileForReach Red5 by red5="+v);//+va70R~
        }                                                          //+va70R~
        int num=PtdDiscard.number;                                 //+va70R~
        if ((Pintent & INTENT_CHANTA)!=0)                          //+va70R~
	        v+=DVS_NUMBER_WEIGHT_CHANTA[num];                      //+va70R~
        else                                                       //+va70R~
	        v+=DVS_NUMBER_WEIGHT[num];                             //+va70R~
        v+=Utils.getRandom(DV_WORD_RANDOM_MAX);   //add 0->9 randomly//+va70R~
        if (Dump.Y) Dump.println("RADSENum.evaluateTileForReach intent="+Integer.toHexString(Pintent)+",type="+PtdDiscard.type+",num="+num+",v="+v);//+va70R~
        return v;                                                  //+va70R~
    }                                                              //+va70R~
    //***********************************************************************//~1216I~
    //*insert take into itsHandPos                                 //~1216I~
    //***********************************************************************//~1216I~
    private void setupSeqMeldDora(int PctrHand)                     //~1216I~
    {                                                              //~1216I~
    	Arrays.fill(itsHandMeldDora,0);                            //~1216I~
    	Arrays.fill(btsHandMeldDoraIsRed5,false);                  //~1302I~
        for (int ii=0;ii<PctrHand;ii++)                            //~1216I~
        {                                                          //~1216I~
        	TileData td=tdsHand[ii];                               //~1216I~
            int ctrDupDora=0;                                      //~1216I~
            if (td.isRed5())                                         //~1216I~
            {                                                      //~1302I~
	            btsHandMeldDoraIsRed5[ii]=true;                    //~1302I~
            	ctrDupDora++;                                      //~1216I~
            }                                                      //~1302I~
            if (RADS.isDoraOpen(td))                               //~1216I~
            	ctrDupDora++;                                      //~1216I~
            itsHandMeldDora[ii]=ctrDupDora;                        //~1216I~
        }                                                          //~1216I~
        if (Dump.Y) Dump.println("RADSENum.setupSeqMeldDora itsHandMeldDora="+Utils.toStringMax(itsHandMeldDora,PctrHand));//~1216I~//~1225R~//~1302R~//~1307R~
        if (Dump.Y) Dump.println("RADSENum.setupSeqMeldDora btsHandMeldDoraIsRed5="+Utils.toStringMax(btsHandMeldDoraIsRed5,PctrHand));//~1302I~//~1307R~
    }                                                              //~1216I~
    //***************************************************************************************************//~1226M~
    //***************************************************************************************************//~1226M~
    //***************************************************************************************************//~1226M~
    //*for UnitTest                                                //~1226I~
    //***********************************************************************//~1226I~
    public void chkNumberMeld4_Test(int Pintent,int[] PitsHandPos,int PctrHand,int[] PitsHand,int[] PitsHandValue,int[] PitsHandMeldDora,boolean[] PbtsHandMeldDoraIsRed5)                                  //~1225I~//~1226R~//~1227R~//~1302R~
    {                                                              //~1225I~
        if (Dump.Y) Dump.println("RADSENum.chkNumberMeld4_Test@@@@ intent="+Integer.toHexString(Pintent)+",itsHandPos="+Utils.toStringMax(PitsHandPos,PctrHand)+"\n,@@@@itsHand="+Utils.toString(PitsHand,9));//~1226R~//~1227R~
        intent=Pintent;                                            //~1226I~
    	itsHandPos=PitsHandPos; ctrHand=PctrHand; itsHand=PitsHand;//~1226I~
    	itsHandValue=PitsHandValue;                                //~1227I~
        itsHandMeldDora=PitsHandMeldDora;                          //~1227I~
        btsHandMeldDoraIsRed5=PbtsHandMeldDoraIsRed5;              //~1302I~
        chkNumberMeld42();                                          //~1226R~//~1228R~
    }                                                              //~1226I~
    //***********************************************************************//~1228I~
    private void chkNumberMeld42()                                 //~1228I~
    {                                                              //~1228I~
        if (Dump.Y) Dump.println("RADSENum.chkNumberMeld42 intent=x"+Integer.toHexString(intent)+",itsHand="+Utils.toString(itsHand,9));//~1228I~
        setupWork42();                                             //~1302I~
//      posPillow=searchPillow4();                             //~1228I~//~1301R~//~1302R~
        posPillow=RADSE.posPillow;                                 //~1302I~
        for (int ii=0;ii<OFFS_WORDTILE;ii+=CTR_NUMBER_TILE)        //~1228I~
        {                                                          //~1228I~
//      	System.arraycopy(itsHand,ii,itsHandWK,0,CTR_NUMBER_TILE);//~1228R~
	    	Arrays.fill(itsHandWK,0);  //only for one tile type    //~1228R~
	    	Arrays.fill(itsContID,0);                              //~1228I~
            posTopItsHand=ii;	//parm to setContID                //~1228I~
        	chkNumberMeld42Sub(intent);                            //~1228I~
        }                                                          //~1228I~
        addByDora();                                               //~1228I~
        addByNearDora();                                           //~1309I~
        setHandValue();                                   //~1228I~//~1301R~
        if (Dump.Y) Dump.println("RADSENum.chkNumberMeld42@@@@exit itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1228I~//~1301R~
        if (Dump.Y) Dump.println("RADSENum.chkNumberMeld42     exit itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1228I~
    }                                                              //~1228I~
    //***********************************************************************//~1228I~
    private void setupWork42()                                     //~1228I~
    {                                                              //~1228I~
        if (Dump.Y) Dump.println("RADSENum.setupWork42 itsHand="+Utils.toString(itsHand,9));//~1228I~
//      setupSeqMeldDora(ctrHand);  //setup dora indicator of tdsHand sequence//~1302R~
        System.arraycopy(itsHandPos,0,itsHandPosMeld,0,ctrHand);   //~1228I~
        Arrays.fill(itsHandIdxStack,-1);                           //~1228R~
        Arrays.fill(itsHandIdxStackCtr,0);                         //~1228I~
        for (int ii=0;ii<ctrHand;ii++)                             //~1228I~
        {                                                          //~1228I~
        	int pos=itsHandPos[ii];                                //~1228I~
            if (pos<OFFS_WORDTILE)                                //~1228I~
            {                                                      //~1302I~
            	int ctrDup=itsHandIdxStackCtr[pos];                //~1302I~
	            int posDup=pos*PIECE_DUPCTR+ctrDup;                //~1302R~
	            itsHandIdxStack[posDup]=ii;                        //~1302I~
            	itsHandIdxStackCtr[pos]=++ctrDup;                  //~1302I~
                                                                   //~1302I~
//          	if (itstd.isRed5() && ctrDup>1)    //shft to insert red5 to top//~1302R~
    			if (btsHandMeldDoraIsRed5[ii] && ctrDup>1)  //shft to insert red5 to top to include to meld//~1302I~
                {                                                  //~1302I~
		            posDup=pos*PIECE_DUPCTR;                       //~1302I~
                    for (int jj=ctrDup-1;jj>=1;jj--)               //~1302R~
                    {                                              //~1302I~
			            itsHandIdxStack[posDup+jj]=itsHandIdxStack[posDup+jj-1];//~1302R~
                    }                                              //~1302I~
			        itsHandIdxStack[posDup]=ii;                         //~1302I~
                }                                                  //~1302I~
            }                                                      //~1302I~
        }                                                          //~1228I~
        if (Dump.Y) Dump.println("RADSENum.setupWork42 itsHandIdxSstackCtr="+Utils.toString(itsHandIdxStackCtr,9));//~1228I~
        if (Dump.Y) Dump.println("RADSENum.setupWork42 itsHandIdxSstack="+Utils.toString(itsHandIdxStack,4));//~1228I~
    }                                                              //~1228I~
    //***********************************************************************//~1228I~
    private void chkNumberMeld42Sub(int Pintent)                   //~1228I~
    {                                                              //~1228I~
        //****************************                             //~1228I~
        if (Dump.Y) Dump.println("RADSENum.chkNumberMeld42Sub posTopIthsHand=" + posTopItsHand + ",intent=" + Integer.toHexString(Pintent));//~1228I~//~1301R~
		srchContSeq42(Pintent);                                     //~1228I~//~1302R~
    	chkNumberDup42(Pintent);                                          //~1228I~//~1306R~
	    chkKanchan42(Pintent);                                            //~1228R~//~1301R~
    	chkNumberOrphan();                                         //~1301I~
        if (Dump.Y) Dump.println("RADSENum.chkNumberMeld42Sub@exit itsHandPosMeld=" + Utils.toStringMax(itsHandPosMeld, ctrHand));//~1228I~//~1301R~
        if (Dump.Y) Dump.println("RADSENum.chkNumberMeld42Sub itsContID=" + Arrays.toString(itsContID));//~1228I~//~1301R~
    }                                                              //~1228I~
    //***********************************************************************//~1227I~//~1228M~
    private void srchContSeq(int Pintent)                          //~1227R~//~1228M~
    {                                                              //~1227I~//~1228M~
        int ctrSeq, posStart, posNext, num;            //~1227I~   //~1228M~
        //****************************                             //~1227I~//~1228M~
        if (Dump.Y) Dump.println("RADSENum.srchContSeq posTopIthsHand=" + posTopItsHand + ",intent=" + Integer.toHexString(Pintent));//~1227I~//~1228R~
        if ((Pintent & INTENT_CHANTA) != 0)                        //~1227I~//~1228M~
        {                                                          //~1227I~//~1228M~
            //*at top *******************                          //~1227I~//~1228M~
            for (; ; )    //multiple sme meld                      //~1227I~//~1228M~
            {                                                      //~1227I~//~1228M~
                if (itsHandWK[TN1] != 0)                           //~1227I~//~1228M~
                    ctrSeq = srchMeld(TN1, 1/*dest*/);             //~1227I~//~1228M~
                else                                               //~1227I~//~1228M~
                    if (itsHandWK[TN2] != 0)                       //~1227I~//~1228M~
                        ctrSeq = srchMeld(TN2, 1/*dest*/);         //~1227I~//~1228M~
                    else                                           //~1227I~//~1228M~
                        break;                                     //~1227I~//~1228M~
            }                                                      //~1227I~//~1228M~
            //*at end ******************                           //~1227I~//~1228M~
            for (; ; )    //multiple sme meld                      //~1227I~//~1228M~
            {                                                      //~1227I~//~1228M~
                if (itsHandWK[TN9] != 0)                           //~1227I~//~1228M~
                    ctrSeq = srchMeld(TN9, -1/*dest*/);            //~1227I~//~1228M~
                else                                               //~1227I~//~1228M~
                    if (itsHandWK[TN8] != 0)                       //~1227I~//~1228M~
                        ctrSeq = srchMeld(TN8, -1/*dest*/);        //~1227I~//~1228M~
                    else                                           //~1227I~//~1228M~
                        break;                                     //~1227I~//~1228M~
            }                                                      //~1227I~//~1228M~
            if (Dump.Y) Dump.println("RADSENum.srchContSeq@after chanta itsHandPosMeld=" + Utils.toStringMax(itsHandPosMeld, ctrHand));//~1227I~//~1228R~
            if (Dump.Y) Dump.println("RADSENum.srchContSeq itsHandWK=" + Arrays.toString(itsHandWK));//~1227I~//~1228R~
            if (Dump.Y) Dump.println("RADSENum.srchContSeq itsContID=" + Arrays.toString(itsContID));//~1227I~//~1228R~
        }//chanta                                                  //~1227I~//~1228M~
        //*from middle *******************                         //~1227I~//~1228M~
        //* arround of TN5                                         //~1227I~//~1228M~
        for (; ; )    //max 4 seq                                  //~1227I~//~1228M~
        {                                                          //~1227I~//~1228M~
            if (itsHandWK[TN5] == 0)                               //~1227I~//~1228M~
                break;                                             //~1227I~//~1228M~
            posStart = TN5;                                        //~1227I~//~1228M~
            for (int ii = TN4; ii >= TN1; ii--)                    //~1227I~//~1228M~
            {                                                      //~1227I~//~1228M~
                if (itsHandWK[ii] == 0)                            //~1227I~//~1228M~
                    break;                                         //~1227I~//~1228M~
                posStart = ii;                                     //~1227I~//~1228M~
            }                                                      //~1227I~//~1228M~
            srchMeld(posStart, 1/*dest*/);                         //~1227I~//~1228M~
        }                                                          //~1227I~//~1228M~
        //* left of TN5                                            //~1227I~//~1228M~
        posStart = TN4;                                            //~1227I~//~1228M~
        for (; ; )    //multiple Meld                              //~1227I~//~1228M~
        {                                                          //~1227I~//~1228M~
            posNext = -1;                                          //~1227I~//~1228M~
            for (int ii = posStart; ii >= TN1; ii--)               //~1227I~//~1228M~
            {                                                      //~1227I~//~1228M~
                if (itsHandWK[ii] != 0)                            //~1227I~//~1228M~
                {                                                  //~1227I~//~1228M~
                    posNext = ii;                                  //~1227I~//~1228M~
                    break;                                         //~1227I~//~1228M~
                }                                                  //~1227I~//~1228M~
            }                                                      //~1227I~//~1228M~
            if (posNext == -1)                                     //~1227I~//~1228M~
                break;                                             //~1227I~//~1228M~
            ctrSeq = srchMeld(posNext, -1/*dest*/);                //~1227I~//~1228M~
            posStart = posNext;                                    //~1227I~//~1228M~
        }                                                          //~1227I~//~1228M~
        //* right of TN5                                           //~1227I~//~1228M~
        posStart = TN6;                                            //~1227I~//~1228M~
        for (; ; )    //multiple Meld                              //~1227I~//~1228M~
        {                                                          //~1227I~//~1228M~
            posNext = -1;                                          //~1227I~//~1228M~
            for (int ii = posStart; ii <= TN9; ii++)               //~1227I~//~1228M~
            {                                                      //~1227I~//~1228M~
                if (itsHandWK[ii] != 0)                            //~1227I~//~1228M~
                {                                                  //~1227I~//~1228M~
                    posNext = ii;                                  //~1227I~//~1228M~
                    break;                                         //~1227I~//~1228M~
                }                                                  //~1227I~//~1228M~
            }                                                      //~1227I~//~1228M~
            if (posNext == -1)                                     //~1227I~//~1228M~
                break;                                             //~1227I~//~1228M~
            ctrSeq = srchMeld(posNext, 1/*dest*/);                 //~1227I~//~1228M~
            posStart = posNext;                                    //~1227I~//~1228M~
        }                                                          //~1227I~//~1228M~
        if (Dump.Y) Dump.println("RADSENum.srchContSeq@after seq itsHandPosMeld=" + Utils.toStringMax(itsHandPosMeld, ctrHand));//~1227I~//~1228R~
        if (Dump.Y) Dump.println("RADSENum.srchContSeq itsHandWK=" + Arrays.toString(itsHandWK));//~1227I~//~1228R~
        if (Dump.Y) Dump.println("RADSENum.srchContSeq itsContID=" + Arrays.toString(itsContID));//~1227I~//~1228R~
    }                                                              //~1227I~//~1228M~
    //***********************************************************************//~1228I~
    private void srchContSeq42(int Pintent)                        //~1228I~
    {                                                              //~1228I~
    	int idx,posStack,contID;                                          //~1228I~//~1301R~
        //****************************                             //~1228I~
        if (Dump.Y) Dump.println("RADSENum.srchContSeq42 posTopIthsHand=" + posTopItsHand + ",intent=" + Integer.toHexString(Pintent));//~1228I~
        if (Dump.Y) Dump.println("RADSENum.srchContSeq42 itsHandWK=" + Arrays.toString(itsHandWK));//~1228I~
        for (int ii=0;ii<CTR_NUMBER_TILE;)                         //~1228R~
        {                                                          //~1228I~
	        int pos=posTopItsHand+ii;                              //~1228I~
//      	if (itsHandWK[ii]==0)                                  //~1228R~
        	if (itsHand[pos]==0                                    //~1228R~
        	||  itsHandWK[ii]>=itsHand[pos])                       //~1228I~
            {                                                      //~1228I~
            	ii++;                                              //~1228I~
            	continue;                                          //~1228I~
            }                                                      //~1228I~
            int ctrSeq=1;                                          //~1228I~
	        for (int jj=ii+1;jj<CTR_NUMBER_TILE;jj++)              //~1228I~
    	    {                                                      //~1228I~
//      		if (itsHandWK[jj]==0)                              //~1228R~
        		if (itsHandWK[jj]>=itsHand[++pos])              //~1228R~
                 	break;                                         //~1228I~
                ctrSeq++;                                          //~1228I~
            }                                                      //~1228I~
//      	int contID=ctrSeq==1 ? CONTID1 : (ctrSeq==2 ? CONTID2 : CONTID3);//~1228I~//~1301R~
			switch(ctrSeq)                                         //~1301I~
            {                                                      //~1301I~
            case 1:                                                //~1301I~
            	contID=CONTID1;                                    //~1301I~
                break;                                             //~1301I~
            case 2:                                                //~1301I~
                if (ii==TN1 || ii==TN8)                            //~1301I~
            		contID=CONTIDP;  //penchan                     //~1301I~
                else                                               //~1301I~
            		contID=CONTID2;                                //~1301I~
                break;                                             //~1301I~
            default:                                               //~1301I~
            	contID=CONTID3;                                    //~1301I~
            }                                                      //~1301I~
            pos=posTopItsHand+ii;                              //~1228R~
	        for (int jj=ii;jj<ii+ctrSeq;jj++,pos++)                //~1228R~
    	    {                                                      //~1228I~
//      		posStack=pos*PIECE_DUPCTR+--itsHandWK[jj];         //~1228R~
        		posStack=pos*PIECE_DUPCTR+itsHandWK[jj]++;         //~1228R~
        		idx=itsHandIdxStack[posStack];                     //~1228I~
                if (contID!=CONTID1) //set handvalue:0 to orphan if plus is kept//~1228I~
                {                                                  //~1228I~
                	itsHandPosMeld[idx]=contID;                    //~1228I~
                }                                                  //~1228I~
                itsContID[jj]=Math.min(contID,itsContID[jj]);      //~1228R~
            }                                                      //~1228I~
        }                                                          //~1228I~
        if (Dump.Y) Dump.println("RADSENum.srcContSeq42@after seq itsHandPosMeld=" + Utils.toStringMax(itsHandPosMeld, ctrHand));//~1228I~
        if (Dump.Y) Dump.println("RADSENum.srcContSeq42 itsHandWK=" + Arrays.toString(itsHandWK));//~1228I~
        if (Dump.Y) Dump.println("RADSENum.srcContSeq42 itsContID=" + Arrays.toString(itsContID));//~1228I~
    }                                                              //~1228I~
    //***********************************************************************//~1228M~
    private void chkNumberDup()                                    //~1228M~
    {                                                              //~1228M~
        int contID, pos,  num;                                     //~1228M~
        //****************************                             //~1228M~
        if (Dump.Y) Dump.println("RADSENum.chkNumberDup");         //~1228M~
        for (int ii = 0; ii < ctrHand; ii++)                       //~1228M~
        {                                                          //~1228M~
            pos = itsHandPosMeld[ii];                              //~1228M~
            if (pos < 0)    //used in meld                         //~1228M~
                continue;                                          //~1228M~
            if (pos < posTopItsHand || pos > posTopItsHand + TN9)  //~1228M~
                continue;                                          //~1228M~
            num = pos % CTR_NUMBER_TILE;                           //~1228M~
            if (num <= TN8 && itsContID[num + 1] < CONTID1)        //~1228M~
                contID = itsContID[num + 1];                       //~1228M~
            else                                                   //~1228M~
                if (num >= TN2 && itsContID[num - 1] < CONTID1)    //~1228M~
                    contID = itsContID[num - 1];                   //~1228M~
                else                                               //~1228M~
                    contID = 0;                                    //~1228M~
            if (contID < 0)                                        //~1228M~
            {                                                      //~1228M~
                setContID(CONT_DUP,num, contID==CONTID2 ? CONTID2DUP : CONTID3DUP);//~1228M~
            }                                                      //~1228M~
        }                                                          //~1228M~
        if (Dump.Y) Dump.println("RADSENum.chkNumberDup@after dup itsHandPosMeld=" + Utils.toStringMax(itsHandPosMeld, ctrHand));//~1228R~
        if (Dump.Y) Dump.println("RADSENum.chkNumberDup itsHandWK=" + Arrays.toString(itsHandWK));//~1228R~
        if (Dump.Y) Dump.println("RADSENum.chkNumberDup itsContID=" + Arrays.toString(itsContID));//~1228R~
    }                                                              //~1228M~
    //***********************************************************************//~1228I~
    private void chkNumberDup42(int Pintent)                                  //~1228I~//~1306R~
    {                                                              //~1228I~
        int contID,posStack;                                       //~1228I~
        //****************************                             //~1228I~
        boolean swChanta=(Pintent & INTENT_CHANTA)!=0;             //~1306I~
        if (Dump.Y) Dump.println("RADSENum.chkNumberDup42 swChanta="+swChanta+",intent=x"+Integer.toHexString(Pintent));       //~1228I~//~1306R~
        posStack=posTopItsHand*PIECE_DUPCTR;                       //~1228I~
        for (int ii=0;ii<CTR_NUMBER_TILE;ii++,posStack+=PIECE_DUPCTR)//~1228I~
        {                                                          //~1228I~
            contID=itsContID[ii];                              //~1228I~
            if (contID>=CONTID1)	//no tile                      //~1228R~
            	continue;                                          //~1228I~
//          contID=contID==CONTID2 ? CONTID2DUP : CONTID3DUP;      //~1228R~//~1301I~
			switch(contID)                                         //~1301I~
            {                                                      //~1301I~
            case CONTIDP:                                          //~1301I~
            	contID=CONTIDPDUP;                                 //~1301I~
                break;                                             //~1301I~
            case CONTID2:                                          //~1301I~
            	contID=CONTID2DUP;                                 //~1301I~
                break;                                             //~1301I~
            default:                                               //~1301I~
            	contID=CONTID3DUP;                                 //~1301I~
                if (!swChanta)                                     //~1306I~
                {                                                  //~1306I~
                	if (ii==TN6 && itsContID[ii+1]==CONTID3 && itsContID[ii+2]==CONTID3 && itsContID[ii+3]==CONTID3) //discard not 6 but 9//~1306I~
                    	contID=CONTID3;                            //~1306I~
                    else                                           //~1306I~
                	if (ii==TN4 && itsContID[ii-1]==CONTID3 && itsContID[ii-2]==CONTID3 && itsContID[ii-3]==CONTID3) //discard not 4 but 1//~1306I~
                    	contID=CONTID3;                            //~1306I~
                }                                                  //~1306I~
            }                                                      //~1301I~
//          int ctrDup=itsHandIdxStackCtr[posTopItsHand+ii];       //~1228R~
            int ctrDup=itsHand[posTopItsHand+ii];                  //~1228I~
            for (int jj=0;jj<ctrDup;jj++)                          //~1228R~
    	    {                                                      //~1228I~
        		int idx=itsHandIdxStack[posStack+jj];              //~1228I~
                if (itsHandPosMeld[idx]>CONTID1) //tile not in a meld//~1228R~
                	itsHandPosMeld[idx]=contID;                    //~1228R~
            }                                                      //~1228I~
        }                                                          //~1228I~
        if (Dump.Y) Dump.println("RADSENum.chkNumberDup42@after dup itsHandPosMeld=" + Utils.toStringMax(itsHandPosMeld, ctrHand));//~1228I~
        if (Dump.Y) Dump.println("RADSENum.chkNumberDup itsHandWK=" + Arrays.toString(itsHandWK));//~1228I~
        if (Dump.Y) Dump.println("RADSENum.chkNumberDup itsContID=" + Arrays.toString(itsContID));//~1228I~
    }                                                              //~1228I~
    //***********************************************************************//~1227I~
    private void addByDora()                         //~1227I~
    {                                                              //~1227I~
        if (Dump.Y) Dump.println("RADSENum.addByDora itsHandPos="+Utils.toStringMax(itsHandPos,ctrHand));//~1227I~
        if (Dump.Y) Dump.println("RADSENum.addByDora itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1227I~
        for (int ii=0;ii<ctrHand;ii++)                             //~1227I~
        {                                                          //~1227I~
        	int pos=itsHandPos[ii];                                //~1227I~
            if (itsHandMeldDora[ii]==0)                              //~1227I~
            	continue;                                          //~1227I~
            int vDora=itsHand[pos]*itsHandMeldDora[ii]*DV_CONTVALUE_DORA;                  //~1227I~
            addDoraL(ii,pos,vDora);                                //~1227I~
            addDoraR(ii,pos,vDora);                                //~1227I~
        }                                                          //~1227I~
        if (Dump.Y) Dump.println("RADSENum.addByDora exit itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1227I~
	}                                                              //~1227I~
    //***********************************************************************//~1309I~
    private void addByNearDora()                                   //~1309I~
    {                                                              //~1309I~
        if (Dump.Y) Dump.println("RADSENum.addByNearDora itsHandPos="+Utils.toStringMax(itsHandPos,ctrHand));//~1309I~
        if (Dump.Y) Dump.println("RADSENum.addByNearDora itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1309I~
        if (Dump.Y) Dump.println("RADSENum.addByNearDora itsHandIdxSstack="+Utils.toString(itsHandIdxStack,4));//~1309I~
        if (Dump.Y) Dump.println("RADSENum.addByNearDora itsDoraOpen="+Utils.toStringMax(RADS.itsDoraOpen,RADS.ctrDoraOpen));//~1309I~
    	int[] itsDoraOpen=RADS.itsDoraOpen;                        //~1309I~
    	int ctrDoraOpen=RADS.ctrDoraOpen;                        //~1309I~
        for (int ii=0;ii<ctrDoraOpen;ii++)	//conver to pos        //~1309I~
        {                                                          //~1309I~
        	int type=itsDoraOpen[ii+ii];                           //~1309I~
			int num=itsDoraOpen[ii+ii+1];                          //~1309I~
            if (type==TT_JI)                                       //~1309I~
            	continue;                                          //~1309I~
	    	int pos=type*CTR_NUMBER_TILE+num;                       //~1309I~
	        if (itsHand[pos]!=0)                                   //~1309I~
            	continue;                                          //~1309I~
            if (num>=TN2 && itsHand[pos-1]!=0)                      //~1309I~
	            addByNearDoraSub(pos-1,DV_DORA_NEAR1);             //~1309I~
            if (num>=TN3 && itsHand[pos-2]!=0)                      //~1309I~
	            addByNearDoraSub(pos-2,DV_DORA_NEAR2);             //~1309I~
            if (num<=TN8 && itsHand[pos+1]!=0)                      //~1309I~
	            addByNearDoraSub(pos+1,DV_DORA_NEAR1);             //~1309I~
            if (num<=TN7 && itsHand[pos+2]!=0)                      //~1309I~//~1310R~
	            addByNearDoraSub(pos+2,DV_DORA_NEAR2);             //~1309I~
        }                                                          //~1309I~
        if (Dump.Y) Dump.println("RADSENum.addByNearDora exit eswn="+eswnDiscard+",itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1309I~
    }                                                              //~1309I~
    //***********************************************************************//~1309I~
    private void addByNearDoraSub(int Ppos,int Pvalue)             //~1309I~
    {                                                              //~1309I~
        int posStack=Ppos*PIECE_DUPCTR;                            //~1309I~
    	for (int ii=0;ii<PIECE_DUPCTR;ii++)                        //~1309I~
        {                                                          //~1309I~
        	int idx=itsHandIdxStack[posStack+ii];                  //~1309I~
            if (idx==-1)                                           //~1309I~
            	break;                                             //~1309I~
	        if (Dump.Y) Dump.println("RADSENum.addByNearDoraSub pos="+Ppos+",v="+Pvalue+",old itsHandValue["+idx+"]="+itsHandValue[idx]);//~1309I~
            itsHandValue[idx]+=Pvalue;                             //~1309I~
	        if (Dump.Y) Dump.println("RADSENum.addByNearDoraSub eswn="+eswnDiscard+",pos="+Ppos+",new tsHandValue["+idx+"]="+itsHandValue[idx]);//~1309I~
        }                                                          //~1309I~
	}                                                              //~1309I~
    //***********************************************************************//~1227I~
    private void addDoraL(int Pidx,int Ppos,int PvDora)            //~1227I~
    {                                                              //~1227I~
        if (Dump.Y) Dump.println("RADSENum.addDoraL idx="+Pidx+",pos="+Ppos+",vDora="+PvDora);//~1227I~
        if (Dump.Y) Dump.println("RADSENum.addDoraL itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1227I~
        int type=Ppos/CTR_NUMBER_TILE;                             //~1227I~
        int numPrev=Ppos%CTR_NUMBER_TILE;                          //~1227I~
        int numPrev0=numPrev;                                      //~1307I~
        for (int ii=Pidx;ii>=0;ii--)                               //~1227I~
        {                                                          //~1227I~
        	int posNext=itsHandPos[ii];                            //~1227I~
	        int typeNext=posNext/CTR_NUMBER_TILE;                  //~1227R~
    	    int numNext=posNext%CTR_NUMBER_TILE;                   //~1227R~
            if (typeNext!=type)                                    //~1227I~
            	break;                                             //~1227I~
            if (numNext<numPrev-2)  //allow kanchan                //~1227I~
            	break;                                             //~1227I~
            if (numNext==numPrev-2)  //kanchan                      //~1307I~
            	if (numPrev<numPrev0-1)              //oxoO ok, oxooO ng//~1307I~
	            	break;                                         //~1307I~
            itsHandPosMeld[ii]+=PvDora;                            //~1227I~
            numPrev=numNext;                                       //~1227I~
        }                                                          //~1227I~
        if (Dump.Y) Dump.println("RADSENum.addDoraL exit itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1227I~
	}                                                              //~1227I~
    //***********************************************************************//~1227I~
    private void addDoraR(int Pidx,int Ppos,int PvDora)            //~1227I~
    {                                                              //~1227I~
        if (Dump.Y) Dump.println("RADSENum.addDoraR idx="+Pidx+",pos="+Ppos+",vDora="+PvDora);//~1227I~
        if (Dump.Y) Dump.println("RADSENum.addDoraR itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1227I~
        int type=Ppos/CTR_NUMBER_TILE;                             //~1227I~
        int numPrev=Ppos%CTR_NUMBER_TILE;                          //~1227I~
        int numPrev0=numPrev;                                      //~1307I~
        for (int ii=Pidx+1;ii<ctrHand;ii++)                        //~1227R~
        {                                                          //~1227I~
        	int posNext=itsHandPos[ii];                            //~1227I~
	        int typeNext=posNext/CTR_NUMBER_TILE;                  //~1227R~
    	    int numNext=posNext%CTR_NUMBER_TILE;                   //~1227R~
            if (typeNext!=type)                                    //~1227I~
            	break;                                             //~1227I~
            if (numNext>numPrev+2)                                 //~1227I~
            	break;                                             //~1227I~
            if (numNext==numPrev+2)  //kanchan                      //~1307I~
            	if (numPrev>numPrev0+1)              //Ooxo ok, Oooxo ng//~1307I~
	            	break;                                         //~1307I~
            itsHandPosMeld[ii]+=PvDora;                            //~1227I~
            numPrev=numNext;                                       //~1227I~
        }                                                          //~1227I~
        if (Dump.Y) Dump.println("RADSENum.addDoraR exit itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1227I~
	}                                                              //~1227I~
    //***********************************************************************//~1227I~
    private void setHandValue()                      //~1227R~     //~1301R~
    {                                                              //~1227I~
        for (int ii=0;ii<ctrHand;ii++)                             //~1227I~
        {                                                          //~1227I~
        	int pos=itsHandPos[ii];                                //~1227I~//~1228M~
            if (pos>=OFFS_WORDTILE)                               //~1227I~//~1228M~
            	continue;                                          //~1227I~//~1228M~
        	int base=itsHandPosMeld[ii];                           //~1227I~
//          if (base>0)  //not part of meld      4:num orphan     //~1227I~//~1302R~
//          	base=0;                                            //~1227I~//~1302R~
            itsHandValue[ii]+=base*DV_NUMBER_CONTID;                //~1227I~//~1228R~//~1309R~
        }                                                          //~1227I~
        if (Dump.Y) Dump.println("RADSENum.setHandValue itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand)+"\n,itsHandValue="+Utils.toStringMax(itsHandValue,ctrHand));//~1227R~//~1301R~
	}                                                              //~1227I~
    //***********************************************************************//~1226I~
    private int getIdxPosMeld(int Pnum)                            //~1226R~
    {                                                              //~1226I~
        if (Dump.Y) Dump.println("RADSENum.getIdxPosMeld Pnum="+Pnum+",posTopItsHand="+posTopItsHand);//~1226I~
    	if (Pnum<TN1 || Pnum>TN9)                                  //~1226I~
        	return -1;                                             //~1226I~
        int pos=posTopItsHand+Pnum;                                   //~1226I~
        int idx=-1;                                                //~1226I~
        for (int ii=0;ii<ctrHand;ii++)                                 //~1226I~
        {                                                          //~1226I~
        	if (pos==itsHandPosMeld[ii])                           //~1226R~
            {                                                      //~1226I~
            	idx=ii;                                            //~1226I~
	        	if (Dump.Y) Dump.println("RADSENum.getIdxPosMeld idx="+idx);//~1226R~
                return idx;                                        //~1226I~
            }                                                      //~1226I~
        }                                                          //~1226I~
        if (Dump.Y) Dump.println("RADSENum.getIdxPosMeld @@@@ pos Not Found num="+Pnum+",itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1226R~
        return idx;                                                //~1226I~
    }                                                              //~1226I~
    //***********************************************************************//~1226I~
    private void setContID(int Ptype,int Pnum,int Pctr/*contCtrSeq or contID for Ddup*/)//~1227R~
    {                                                              //~1225I~
	    int contID;                                                //~1227I~
        if (Dump.Y) Dump.println("RADSENum.setContID before itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1226R~
        if (Dump.Y) Dump.println("RADSENum.setContID itsHandWK="+Arrays.toString(itsHandWK));//~1226R~
        if (Dump.Y) Dump.println("RADSENum.setContID itsContID="+Arrays.toString(itsContID));//~1226R~
	    int idx=getIdxPosMeld(Pnum);                               //~1226R~
        if (idx==-1)                                               //~1226I~
        	return;                                                //~1226R~
        if (Ptype==CONT_SEQ)                                       //~1227I~
	    	contID=Pctr==1 ? CONTID1 : (Pctr==2 ? CONTID2 : CONTID3);//~1226I~//~1227R~
        else                                                       //~1227I~
	    	contID=Pctr;                                           //~1227I~
        if (Ptype==CONT_SEQ)                                       //~1226I~
	        itsHandWK[Pnum]--;                                     //~1226I~
        if (Pctr!=1)                                               //~1226I~
        {                                                          //~1226I~
		    itsHandPosMeld[idx]=contID;                            //~1226R~
        }                                                          //~1226I~
        if (itsContID[Pnum]>contID)   //minus max                  //~1226I~
        	itsContID[Pnum]=contID;                                //~1226I~
        if (Dump.Y) Dump.println("RADSENum.setContID contType="+Ptype+",num="+Pnum+",ctr="+Pctr+",contID="+contID);//~1226R~
        if (Dump.Y) Dump.println("RADSENum.setContID after  itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1226R~
        if (Dump.Y) Dump.println("RADSENum.setContID itsHandWK="+Arrays.toString(itsHandWK));//~1226R~
        if (Dump.Y) Dump.println("RADSENum.setContID itsContID="+Arrays.toString(itsContID));//~1226R~
    }                                                              //~1226I~
    //***********************************************************************//~1227I~
    //*chk kanchan with left tile                                  //~1227I~
    //***********************************************************************//~1227I~
    private void setContIDKanchan(int Pidx,int Pnum,int PcontIDL,int PcontIDR,int PidxL,int PidxR)//~1227R~
    {                                                              //~1227I~
    	int idxL,idxR,contID;                                      //~1227I~
        if (Dump.Y) Dump.println("RADSENum.setContIDKanchan before itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1227I~
        if (Dump.Y) Dump.println("RADSENum.setContIDKanchan itsContID="+Arrays.toString(itsContID));//~1227I~
	    idxL=PidxL;                                                //~1227R~
	    idxR=PidxR;                                                //~1227R~
        if (PcontIDL<CONTIDK)        //left is part of meld        //~1227I~
        {                                                          //~1227I~
            itsContID[Pnum]=CONTIDK;                               //~1227I~
            itsHandPosMeld[idxL]+=-1;                              //~1227I~
            itsHandPosMeld[Pidx]=PcontIDL+1;                       //~1227I~
        	if (PcontIDR<CONTIDK)                                  //~1227I~
            {                                                      //~1227I~
                itsHandPosMeld[Pidx]=Math.min(PcontIDL,PcontIDR)+1;//~1227R~
                itsHandPosMeld[idxR]+=-1;                          //~1227I~
            }                                                      //~1227I~
        }                                                          //~1227I~
        else                                                       //~1227R~
        if (PcontIDL==CONTIDK)        //left is part of meld       //~1227R~
        {                                                          //~1227M~
	        itsContID[Pnum]=CONTIDK;                               //~1227I~
    	    itsHandPosMeld[Pidx]=CONTIDK;                          //~1227I~
            if (PcontIDR<CONTIDK)  //right kanchan is meld         //~1227I~
            {                                                      //~1227I~
                itsHandPosMeld[Pidx]=PcontIDR+1;                   //~1227I~
                itsHandPosMeld[idxR]+=-1;                          //~1227I~
            }                                                      //~1227I~
        }                                                          //~1227I~
        else                                                       //~1227I~
        if (PcontIDL==CONTID1)        //left is part of meld       //~1227I~
        {                                                          //~1227I~
	        itsContID[Pnum]=CONTIDK;                               //~1227I~
    	    itsHandPosMeld[Pidx]=CONTIDK;                          //~1227I~
	        itsContID[Pnum-2]=CONTIDK;                             //~1227I~
    	    itsHandPosMeld[idxL]=CONTIDK;                          //~1227I~
            if (PcontIDR<CONTIDK)  //right kanchan is meld         //~1227I~
            {                                                      //~1227I~
                itsHandPosMeld[Pidx]=PcontIDR+1;                   //~1227I~
                itsHandPosMeld[idxR]+=-1;                          //~1227I~
            }                                                      //~1227I~
        }                                                          //~1227I~
        else    //no left tile                                     //~1227R~
        if (PcontIDR==CONTID1)                                     //~1227I~
        {                                                          //~1227I~
            itsContID[Pnum]=CONTIDK;                               //~1227I~
            itsHandPosMeld[Pidx]=CONTIDK;                          //~1227I~
        }                                                          //~1227I~
        else                                                       //~1227I~
        if (PcontIDR<CONTIDK)  //right kanchan is meld             //~1227I~
        {                                                          //~1227I~
            itsContID[Pnum]=CONTIDK;                               //~1227I~
            itsHandPosMeld[Pidx]=PcontIDR+1;                       //~1227I~
            itsHandPosMeld[idxR]+=-1;                              //~1227I~
        }                                                          //~1227I~
        if (Dump.Y) Dump.println("RADSENum.setContIDKanchan after kanchan Pidx="+Pidx+",num="+Pnum+",PcontIDL="+PcontIDL+",contIDR="+PcontIDR+",PidxL="+PidxL+",PidxR="+PidxR);//~1227R~
        if (Dump.Y) Dump.println("RADSENum.setContIDKanchan itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1227R~
        if (Dump.Y) Dump.println("RADSENum.setContIDKanchan itsContID="+Arrays.toString(itsContID));//~1227I~
    }                                                              //~1227I~
    //***********************************************************************//~1226I~//~1227M~
    //*search continued                                            //~1226I~//~1227M~
    //***********************************************************************//~1226I~//~1227M~
    private int srchMeld(int Pstart,int Pdest)                     //~1226I~//~1227M~
    {                                                              //~1226I~//~1227M~
    	int  ctrSeq=0;                                             //~1226I~//~1227M~
    	if (Dump.Y) Dump.println("RADSENum.srchMeld start="+Pstart+",dest="+Pdest);//~1226R~//~1227M~
        if (Pdest>0)                                               //~1226I~//~1227M~
        {                                                          //~1226I~//~1227M~
            for (int ii=Pstart;ii<=TN9;ii++)                       //~1226I~//~1227M~
            {                                                      //~1226I~//~1227M~
                if (itsHandWK[ii]==0)                              //~1226I~//~1227M~
                    break;                                         //~1226I~//~1227M~
                ctrSeq++;                                          //~1226I~//~1227M~
            }                                                      //~1226I~//~1227M~
            for (int ii=Pstart;ii<=TN9;ii++)                       //~1226I~//~1227M~
            {                                                      //~1226I~//~1227M~
                if (itsHandWK[ii]==0)                              //~1226I~//~1227M~
                    break;                                         //~1226I~//~1227M~
                setContID(CONT_SEQ,ii,ctrSeq);                     //~1226R~//~1227M~
            }                                                      //~1226I~//~1227M~
        }                                                          //~1226I~//~1227M~
        else                                                       //~1226I~//~1227M~
        {                                                          //~1226I~//~1227M~
            for (int ii=Pstart;ii>=TN1;ii--)                       //~1226I~//~1227M~
            {                                                      //~1226I~//~1227M~
                if (itsHandWK[ii]==0)                              //~1226I~//~1227M~
                    break;                                         //~1226I~//~1227M~
                ctrSeq++;                                          //~1226I~//~1227M~
            }                                                      //~1226I~//~1227M~
            for (int ii=Pstart;ii>=TN1;ii--)                       //~1226I~//~1227M~
            {                                                      //~1226I~//~1227M~
                if (itsHandWK[ii]==0)                              //~1226I~//~1227M~
                    break;                                         //~1226I~//~1227M~
                setContID(CONT_SEQ,ii,ctrSeq);                     //~1226R~//~1227M~
            }                                                      //~1226I~//~1227M~
        }                                                          //~1226I~//~1227M~
        if (Dump.Y) Dump.println("RADSENum.srchMeld after  itsHandPosMeld="+Utils.toStringMax(itsHandPosMeld,ctrHand));//~1226I~//~1227M~
        if (Dump.Y) Dump.println("RADSENum.srchMeld itsHandWK="+Arrays.toString(itsHandWK));//~1226R~//~1227M~
        if (Dump.Y) Dump.println("RADSENum.srchMeld itsContID="+Arrays.toString(itsContID));//~1226R~//~1227M~
        return ctrSeq;                                             //~1226I~//~1227M~
    }                                                              //~1226I~//~1227M~
    //**************************************************************************************//~1227I~
    private void chkKanchan()                                      //~1227I~
    {                                                              //~1227I~
    	int pos,num,contIDL,contIDR;                               //~1227I~
    //*******************************                              //~1227I~
        if (Dump.Y) Dump.println("RADSENum.chkKanchan");           //~1227I~
        for (int ii = 0; ii < ctrHand; ii++)                                 //~1226R~//~1227M~
        {                                                              //~1225R~//~1227M~
        	int posL,posR,idxL,idxR;                               //~1227M~
            pos = itsHandPosMeld[ii];                                    //~1226R~//~1227M~
            if (pos<0)    //used in meld                           //~1226R~//~1227M~
                continue;                                              //~1226I~//~1227M~
            if (pos < posTopItsHand || pos > posTopItsHand + TN9)            //~1226R~//~1227M~
                continue;                                              //~1226I~//~1227M~
            idxL=-1;                                               //~1227M~
            posL=-1;                                               //~1227M~
            if (ii>0)                                              //~1227M~
            {                                                      //~1227M~
                idxL=ii-1;                                         //~1227M~
            	for (int jj=idxL;jj>=0;jj--)                       //~1227M~
                {                                                  //~1227M~
                	int tmp=itsHandPos[jj];                        //~1227M~
                	if (tmp<posTopItsHand)                         //~1227M~
                    	break;                                     //~1227M~
                	if (tmp!=pos)                                  //~1227M~
                    {                                              //~1227M~
                    	posL=tmp;                                  //~1227M~
                        idxL=jj;                                   //~1227M~
                        break;                                     //~1227M~
                    }                                              //~1227M~
                }                                                  //~1227M~
        	}                                                      //~1227M~
                                                                   //~1227M~
            idxR=-1;                                               //~1227M~
            posR=-1;                                               //~1227M~
            if (ii<ctrHand-1)                                      //~1227M~
            {                                                      //~1227M~
                idxR=ii+1;                                         //~1227M~
            	for (int jj=idxR;jj<ctrHand;jj++)                  //~1227M~
                {                                                  //~1227M~
                	int tmp=itsHandPos[jj];                        //~1227M~
		            if (tmp>posTopItsHand+TN9)                     //~1227M~
                    	break;                                     //~1227M~
                	if (tmp!=pos)                                  //~1227M~
                    {                                              //~1227M~
                    	posR=tmp;                                  //~1227M~
                        idxR=jj;                                   //~1227M~
                        break;                                     //~1227M~
                    }                                              //~1227M~
                }                                                  //~1227M~
            }                                                      //~1227M~
            num = pos % CTR_NUMBER_TILE;                                   //~1226I~//~1227M~
            if (num>=TN3 && posL==pos-2)                                          //~1226I~//~1227M~
            	contIDL=itsContID[num-2];                          //~1226I~//~1227M~
            else                                                   //~1226I~//~1227M~
            	contIDL=1;                                         //~1226I~//~1227M~
            if (num<=TN7 && posR==num+2)                                          //~1226I~//~1227M~
            	contIDR=itsContID[num+2];                          //~1226I~//~1227M~
            else                                                   //~1226I~//~1227M~
            	contIDR=1;                                         //~1226I~//~1227M~
			setContIDKanchan(ii,num,contIDL,contIDR,idxL,idxR);                 //~1226R~//~1227M~
        }                                                              //~1226I~//~1227M~
    }                                                              //~1227I~
    //**************************************************************************************//~1227I~
    private void chkKanchan2()                                     //~1227I~
    {                                                              //~1227I~
    	int contID,contIDR,contIDL;                                //~1227I~
    //*******************************                              //~1227I~
        if (Dump.Y) Dump.println("RADSENum.chkKanchan2");          //~1227I~
        for (int ii = 0; ii < CTR_NUMBER_TILE; ii++)               //~1227R~
        {                                                          //~1227I~
        	contID=itsContID[ii];                                  //~1227I~
            if (contID!=0)                                         //~1227I~
            	continue;                                          //~1227I~
            contIDL=(ii>=TN2) ? itsContID[ii-1] : 0;               //~1227I~
            contIDR=(ii<=TN8) ? itsContID[ii+1] : 0;               //~1227I~
            if (contIDL!=0 && contIDR!=0)	//tile exist           //~1227R~
            {                                                      //~1227I~
                addContIDKanchan2(ii-1,-1,contIDR);                //~1227I~
                addContIDKanchan2(ii+1,1,contIDL);                 //~1227I~
            }                                                      //~1227I~
        }                                                          //~1227I~
    }                                                              //~1227I~
    //**************************************************************************************//~1227I~
    private int addContIDKanchan2(int PposStart,int Pdest,int PcontID)//~1227I~
    {                                                              //~1227I~
    	int posEnd,posStart,contID=0,pos;                              //~1227I~
    //*******************************                              //~1227I~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan2 posStart="+PposStart+",Pdest="+Pdest+",contID="+PcontID);//~1227R~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan2 contID="+Utils.toString(itsContID));//~1227I~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan2 handPos="+Utils.toString(itsHandPos));//~1227I~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan2 handPosMeld="+Utils.toString(itsHandPosMeld));//~1227R~
        posEnd=PposStart;                                          //~1227I~
        for (int ii = PposStart; ii>=0 && ii<CTR_NUMBER_TILE; ii+=Pdest)//~1227R~
        {                                                          //~1227I~
        	contID=itsContID[ii];                                  //~1227I~
            if (contID==0)                                         //~1227I~
            	break;                                             //~1227I~
	        posEnd=ii;                                             //~1227I~
        }                                                          //~1227I~
        posStart=posTopItsHand+PposStart;                          //~1227I~
        posEnd=posTopItsHand+posEnd;                               //~1227I~
        for (int ii = 0; ii < ctrHand; ii++)                       //~1227I~
        {                                                          //~1227I~
            pos = itsHandPos[ii];                                  //~1227I~
            if (pos==posStart)                                     //~1227I~
            {                                                      //~1227I~
                contID=itsContID[pos-posTopItsHand];               //~1227R~
                if (contID==CONTID1)                               //~1227I~
                {                                                  //~1227I~
                    if (PcontID==CONTID1)                          //~1227I~
                        itsHandPosMeld[ii]=CONTIDK;                 //~1227I~
                    else                                           //~1227I~
                        itsHandPosMeld[ii]=PcontID+1;              //~1227R~
                }                                                  //~1227I~
                else                                               //~1227I~
                {                                                  //~1227I~
                    itsHandPosMeld[ii]--;                          //~1227R~
                }                                                  //~1227I~
            }                                                      //~1227I~
            else                                                   //~1227I~
            if (Pdest<0)                                           //~1227I~
            {                                                      //~1227I~
            	if (pos>posStart)                                  //~1227R~
                	break;                                         //~1227I~
            	if (pos>=posEnd)                                   //~1227I~
	                itsHandPosMeld[ii]--;                          //~1227R~
            }                                                      //~1227I~
            else                                                   //~1227I~
            {                                                      //~1227I~
            	if (pos>posEnd)                                    //~1227I~
                	break;                                         //~1227I~
            	if (pos>=posStart)                                 //~1227I~
	                itsHandPosMeld[ii]--;                          //~1227R~
            }                                                      //~1227I~
        }                                                          //~1227I~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan2@after dest="+Pdest+",contID="+contID+",posStart="+posStart+",posEnd="+posEnd);//~1227R~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan2@after handPosMeld="+Utils.toString(itsHandPosMeld));//~1227R~
        return contID;                                             //~1227I~
    }                                                              //~1227I~
    //**************************************************************************************//~1228I~
    private void chkKanchan42(int Pintent)                                    //~1228I~//~1301R~
    {                                                              //~1228I~
    	int contID,contIDR,contIDL;                                //~1228I~
    //*******************************                              //~1228I~
        if (Dump.Y) Dump.println("RADSENum.chkKanchan42");         //~1228I~
        for (int ii = 0; ii < CTR_NUMBER_TILE; ii++)               //~1228I~
        {                                                          //~1228I~
        	contID=itsContID[ii];                                  //~1228I~
            if (contID!=0)                                         //~1228I~
            	continue;                                          //~1228I~
            contIDL=(ii>=TN2) ? itsContID[ii-1] : 0;               //~1228I~
            contIDR=(ii<=TN8) ? itsContID[ii+1] : 0;               //~1228I~
            if (contIDL!=0 && contIDR!=0)	//1 space between tile //~1228R~
            {                                                      //~1228I~
                addContIDKanchan42(Pintent,ii-1,-1,contIDR);               //~1228R~//~1301R~
                addContIDKanchan42(Pintent,ii+1,1,contIDL);                //~1228R~//~1301R~
            }                                                      //~1228I~
        }                                                          //~1228I~
        if (Dump.Y) Dump.println("RADSENum.addKanchan42@after kanchan handPosMeld="+Utils.toString(itsHandPosMeld));//~1301R~
    }                                                              //~1228I~
    //**************************************************************************************//~1228I~
    private void addContIDKanchan42(int Pintent,int PposStart,int Pdest,int PcontID)//~1228R~//~1301R~
    {                                                              //~1228I~
    	int contID,idx,posStack;                                   //~1228R~
    //*******************************                              //~1228I~
        boolean swChanta=(Pintent & INTENT_CHANTA)!=0;             //~1301I~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan42 intent="+Integer.toHexString(Pintent)+",swChanta="+swChanta+",posStart="+PposStart+",Pdest="+Pdest+",contID="+PcontID);//~1228I~//~1301R~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan42 contID="+Utils.toString(itsContID));//~1228I~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan42 itsHand="+Utils.toString(itsHand,9));//~1228I~//~1301R~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan42 handPosMeld="+Utils.toString(itsHandPosMeld));//~1228I~
        contID=itsContID[PposStart];                               //~1228I~
        if (contID==CONTID1)                                       //~1228R~
        {                                                          //~1228I~
        	itsContID[PposStart]=CONTIDK;                          //~1301I~
            if (PcontID==CONTID1)                                  //~1228I~
                contID=CONTIDK;                                    //~1228R~
            else                                                   //~1228I~
            if (PcontID==CONTIDP)   //kanchan to penchan	       //~1301I~
            {                                                      //~1301I~
            	if (swChanta)                                      //~1301I~
	                contID=PcontID+1;                              //~1301I~
                else                                               //~1301I~
	                contID=CONTIDK;                                //~1301I~
            }                                                      //~1301I~
            else                                                   //~1301I~
            if (PcontID==CONTIDK)   //kanchan to kanchan           //~1307I~
	            contID=CONTIDK;                                    //~1307I~
            else                                                   //~1307I~
                contID=PcontID+1;                                  //~1228R~
            posStack=(posTopItsHand+PposStart)*PIECE_DUPCTR;       //~1228I~
            int ctrDup=itsHand[posTopItsHand+PposStart];           //~1228I~
            for (int jj=0;jj<ctrDup;jj++)                          //~1228I~
            {                                                      //~1228I~
                idx=itsHandIdxStack[posStack+jj];                  //~1228I~
                if (PcontID==CONTIDK)   //kanchanto kanchan        //~1307I~
                	itsHandPosMeld[idx]=jj==0 ? CONTIDK : CONTIDK+1;	//dup//~1307I~
                else                                               //~1307I~
                	itsHandPosMeld[idx]=contID;                        //~1228I~//~1307R~
            }                                                      //~1228I~
        }                                                          //~1228I~
        else                                                       //~1307I~
        if (contID!=CONTIDK)                                       //~1307I~
            for (int ii=PposStart; ii>=0 && ii<CTR_NUMBER_TILE; ii+=Pdest)//for allcontinued to kanchan//~1301R~
            {                                                      //~1228R~
                contID=itsContID[ii];                              //~1228R~
                if (contID==0)                                     //~1228R~
                    break;                                         //~1228R~
                if (contID==CONTIDP && !swChanta)                  //~1301I~
                	break;                                         //~1301I~
                posStack=(posTopItsHand+ii)*PIECE_DUPCTR;          //~1228R~
                int ctrDup=itsHand[posTopItsHand+ii];              //~1228R~
                for (int jj=0;jj<ctrDup;jj++)                      //~1228R~
                {                                                  //~1228R~
                    idx=itsHandIdxStack[posStack+jj];              //~1228R~
                    itsHandPosMeld[idx]--;                         //~1228R~
                }                                                  //~1228R~
            }                                                      //~1228R~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan42@after handPosMeld="+Utils.toString(itsHandPosMeld));//~1228I~
        if (Dump.Y) Dump.println("RADSENum.addContIDKanchan42@after itsContID="+Utils.toString(itsContID));//~1307I~
    }                                                              //~1228I~
    //***********************************************************************//~1301I~
    private void chkNumberOrphan()                                 //~1301I~
    {                                                              //~1301I~
        int contID,posStack;                                       //~1301I~
        //****************************                             //~1301I~
        if (Dump.Y) Dump.println("RADSENum.chkNumberOrphan before itsHandPosMeld=" + Utils.toStringMax(itsHandPosMeld, ctrHand));//~1301I~
        if (Dump.Y) Dump.println("RADSENum.chkNumberOrphan itsContID=" + Arrays.toString(itsContID));//~1301I~
        posStack=posTopItsHand*PIECE_DUPCTR;                       //~1301I~
        for (int ii=0;ii<CTR_NUMBER_TILE;ii++,posStack+=PIECE_DUPCTR)//~1301I~
        {                                                          //~1301I~
            contID=itsContID[ii];                                  //~1301I~
            if (contID<CONTID1)	//in meld                          //~1301R~
            	continue;                                          //~1301I~
            int ctrDup=itsHand[posTopItsHand+ii];                  //~1301I~
            if (ctrDup==0) //no tile                               //~1301I~
            	continue;                                          //~1301I~
            switch(ctrDup)                                         //~1301I~
            {                                                      //~1301I~
            case 1:                                                //~1301I~
            	contID=CONTIDO1;                                      //~1301I~
                break;                                             //~1301I~
            case 2:                                                //~1301I~
            	contID=CONTIDO2;                                      //~1301I~
	            if (posTopItsHand+ii==posPillow)                   //~1301R~
                	contID+=CONTID_PILLOW;                         //~1301I~
                break;                                             //~1301I~
            default:                                          //~1301I~
            	contID=CONTIDO3;                                      //~1301I~
            }                                                      //~1301I~
            for (int jj=0;jj<ctrDup;jj++)                          //~1301I~
    	    {                                                      //~1301I~
        		int idx=itsHandIdxStack[posStack+jj];              //~1301I~
                itsHandPosMeld[idx]=contID;                        //~1301I~
            }                                                      //~1301I~
        }                                                          //~1301I~
        if (Dump.Y) Dump.println("RADSENum.chkNumberOrphan@after itsHandPosMeld=" + Utils.toStringMax(itsHandPosMeld, ctrHand));//~1301R~
        if (Dump.Y) Dump.println("RADSENum.chkNumberOrphan posPillow="+posPillow+",itsContID=" + Arrays.toString(itsContID));//~1301R~
    }                                                              //~1301I~
}//class RADSENum                                                   //~1131R~//~1225R~
