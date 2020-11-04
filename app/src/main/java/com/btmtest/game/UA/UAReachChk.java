//*CID://+va27R~: update#= 751;                                    //~va27R~
//**********************************************************************//~v101I~
//2020/11/03 va27 Tenpai chk at Reach                              //~va27I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import android.graphics.Point;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.TileData;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import java.util.Arrays;

import static com.btmtest.game.GConst.*;
import static com.btmtest.game.Tiles.*;
import static com.btmtest.game.gv.Pieces.*;
import static com.btmtest.StaticVars.AG;                           //~9C11I~
//****************************************************             //~9C11I~
public class UAReachChk                                            //~va27R~
{                                                                  //~0914I~
    protected Point posPillow;//~v@@@R~                            //~va11R~
    public int[][] dupCtr=new int[PIECE_TYPECTR_ALL][PIECE_NUMBERCTR];	//4*9//~9C12I~//~va11R~
    protected boolean sw7Pair4Pair;                                  //~9C11I~//~va11R~
//  protected boolean sw14NoPair,sw13NoPair;                         //~9C11R~//~va27R~
//  protected boolean swIs14NoPair;                                //~va27R~
    protected int ctrRecursive;                                      //~9C12I~//~va11R~
    protected int ctrTileAll;                                        //~9C12I~//~va11R~
    protected int ctrPair;                                         //~va11I~
    public  int player;                                            //~va11I~
//  private boolean sw1stTake;                                     //~va27R~
    public boolean swAllInHand;                                    //~va27R~
    protected UARonValue UARV;                                     //~va27R~
    protected boolean swListAll=false;	//protect for ITUAReachChk //+va27R~
//*************************                                        //~v@@@I~
	public UAReachChk()                                //~0914R~//~dataR~//~1107R~//~1111R~//~@@@@R~//~v@@@R~//~va27R~
    {                                                              //~0914I~
        if (Dump.Y) Dump.println("UAReachChk Constructor");         //~1506R~//~@@@@R~//~v@@@R~//~va27R~
        AG.aUAReachChk=this;                                       //~va27I~
        init();                                                    //~v@@@I~//~va14R~
    }                                                              //~0914I~
	//*************************************************************************//~v@@@I~
	protected void init()                                             //~v@@@I~//~va11R~//~va14R~
    {                                                              //~v@@@I~
        sw7Pair4Pair= RuleSettingYaku.is7Pair4Pair();               //~9C11I~
//      sw13NoPair= RuleSettingYaku.isYakuman13NoPair();           //~va27R~
//      sw14NoPair= RuleSettingYaku.isYakuman14NoPair();           //~va27R~
    }                                                              //~v@@@I~
	//*************************************************************************//~9C11I~
	//*                                                            //~va11R~
	//*************************************************************************//~9C11I~
    public boolean chkReach(int Pplayer,TileData PtdDiscard)       //~va27R~
    {                                                              //~9C11I~
    	boolean rc;                                                //~9C11I~
        UARV=AG.aUARonValue;                                       //~va27I~
        player=Pplayer;                                            //~va11I~
        swAllInHand=UARV.isAllInHand();                            //~va27R~
//  	ctrPair=AG.aPlayers.getCtrPair(Pplayer);       //including Ron tile//~va27R~
        if (Dump.Y) Dump.println("UAReachChk.chkReach player="+Pplayer);//~9C11I~//~0205R~//~va11R~//~va27R~
        TileData[] tds=AG.aPlayers.getHands(Pplayer);		//including Ron tile//~9C11R~
        sortTiles(tds,PtdDiscard);                                 //~va27R~
	    rc=chkReachSub();                                          //~va27R~
        return rc;//~9C12I~
    }                                                              //~9C12I~
	//*************************************************************************//~va27I~
    private void sortTiles(TileData[] Ptds,TileData PtdDiscard)    //~va27I~
    {                                                              //~va27I~
    	int type,num;                                              //~va27I~
    //*******************************                              //~va27I~
        if (Dump.Y) Dump.println("UAReachChk.sortTiles tds="+TileData.toString(Ptds)+",tdDiscard="+Utils.toString(PtdDiscard));//~va27I~
        for (int ii=0;ii<PIECE_TYPECTR_ALL;ii++)                   //~va27I~
            Arrays.fill(dupCtr[ii],0);                             //~va27I~
        ctrTileAll=Ptds.length;                                    //~va27I~
        for (TileData td : Ptds)                                   //~va27I~
        {                                                          //~va27I~
            type = td.type;                                        //~va27I~
            num = td.number;                                       //~va27I~
            dupCtr[type][num]++;                                   //~va27I~
        }                                                          //~va27I~
        if (PtdDiscard!=null)                                      //~va27I~
        {                                                          //~va27I~
            type = PtdDiscard.type;                                    //~va27I~
            num = PtdDiscard.number;                                   //~va27I~
            dupCtr[type][num]--;                                   //~va27I~
        }                                                          //~va27I~
        if (Dump.Y) Dump.println("UAReachChk.sortTiles dupCtr="+ Utils.toString(dupCtr));//~va27I~
    }                                                              //~va27I~
	//*************************************************************************//~9C12I~
    protected boolean chkReachSub()	//protected for ITReachChk     //~va27R~
    {                                                              //~9C12I~
//      boolean rc=isStandardPairing();                                     //~9C11I~//~va27R~
        boolean rc=chkRonnableStandard();                          //~va27I~
//      sw13_14NoPair=false;                                       //~va27R~
        if (!rc||swListAll)                                        //~va27R~
        {                                                          //~9C11I~
        	if (is7Pair())                                         //~9C11I~
        		rc=true;                                           //~9C11I~
            else                                                   //~9C11I~
        	if (is13All())                                         //~9C11I~
        		rc=true;                                           //~9C11I~
        }                                                          //~9C11I~
        if (Dump.Y) Dump.println("UAReachChk.chkReachSub rc="+rc);     //~9C11I~//~9C12R~//~va27R~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
	//*************************************************************************//~va27I~
    private boolean chkRonnableStandard()                         //~va27I~
    {                                                              //~va27I~
    	boolean rc=false,rc2;                                      //~va27R~
        int[][] dupCtrClone=Utils.cloneArray2(dupCtr);             //~va27I~
        if (Dump.Y) Dump.println("UAReachChk.chkRonnableStandard dupCtr="+Utils.toString(dupCtr));//~va27I~
                                                                   //~va27I~
        for (int ii=0;ii<PIECE_NOTNUM_CTR;ii++)                         //7//~va27I~
        {                                                          //~va27I~
            int num=dupCtrClone[TT_JI][ii];                        //~va27R~
            if (num==1 || num==2)                                  //~va27I~
            {                                                      //~va27I~
            	dupCtrClone[TT_JI][ii]++;                          //~va27R~
                rc2=UARV.isStandardPairing(dupCtrClone);           //~va27R~
                Utils.copyArray2(dupCtr/*from*/,dupCtrClone/*to*/);	//isStandarPair changes dupCtr//~va27I~
                if (rc2)                                           //~va27R~
                {                                                  //~va27I~
                	rc=true;                                       //~va27I~
			        if (Dump.Y) Dump.println("UAReachChk.chkRonnableStandard break by notNum ii="+ii+",dupCtr="+Utils.toString(dupCtr));//~va27R~
                  	if (!swListAll)   //TODO test                  //~va27R~
                		break;                                     //~va27R~
                }                                                  //~va27I~
            }                                                      //~va27I~
        }                                                          //~va27I~
        if (!rc||swListAll)                                        //~va27R~
        {                                                          //~va27I~
        	boolean[] swCandidateS=new boolean[PIECE_NUMBERCTR];   //~va27I~
            for (int ii=0;ii<PIECE_NUMBERTYPECTR;ii++)             //~va27I~
            {                                                      //~va27I~
                for (int jj=0;jj<PIECE_NUMBERCTR;jj++)                         //7//~va27I~
                {                                                  //~va27I~
                    int num=dupCtrClone[ii][jj];                   //~va27R~
                    if (num!=0)                                    //~va27I~
                    {                                              //~va27I~
	                    swCandidateS[jj]=true;                     //~va27R~
                        if (jj>0)                                  //~va27I~
	                    	swCandidateS[jj-1]=true;               //~va27I~
                        if (jj<PIECE_NUMBERCTR-1)                  //~va27I~
	                    	swCandidateS[jj+1]=true;               //~va27I~
                    }                                              //~va27I~
                }                                                  //~va27I~
                for (int jj=0;jj<PIECE_NUMBERCTR;jj++)                         //7//~va27I~
                {                                                  //~va27I~
                	if (swCandidateS[jj])                          //~va27I~
                    {                                              //~va27I~
	            		if (dupCtrClone[ii][jj]==4)                //~va27R~
                        	continue;                              //~va27I~
	            		dupCtrClone[ii][jj]++;                     //~va27I~
                		rc2=UARV.isStandardPairing(dupCtrClone);   //~va27R~
                		Utils.copyArray2(dupCtr/*from*/,dupCtrClone/*to*/);	//isStandarPair changes dupCtr//~va27I~
                        if (rc2)                                   //~va27R~
                        {                                          //~va27R~
                        	rc=true;                               //~va27I~
                            if (Dump.Y) Dump.println("UAReachChk.chkRonnableStandard break by Num ii="+ii+",jj="+jj+",dupctr="+Utils.toString(dupCtr));//~va27R~
                          	if (!swListAll)   //TODO test          //~va27R~
                            	break;                             //~va27R~
                        }                                          //~va27R~
                    }                                              //~va27I~
                }                                                  //~va27I~
                Arrays.fill(swCandidateS,false);                   //~va27I~
            }                                                      //~va27I~
        }                                                          //~va27I~
        if (Dump.Y) Dump.println("UAReachChk.chkRonnableStandard rc="+rc);//~va27I~
        return rc;                                                //~va27I~
    }                                                              //~va27I~
	//*************************************************************************//~9C11I~
    private boolean is7Pair()                                      //~9C11I~//~va27R~
    {                                                              //~9C11I~
        if (Dump.Y) Dump.println("UAReachChk.is7Pair");            //~va27R~
    	if (!swAllInHand)                                            //~va11R~
        	return false;                                          //~va11I~
        int ctrPair=0;                                             //~9C12I~
        int ctrSingle=0;                                           //~va27I~
        int[] dupctr;                                              //~9C12I~
        dupctr=dupCtr[TT_JI];                                      //~9C12I~
        for (int ii=0;ii<PIECE_NOTNUM_CTR;ii++)                    //~9C11I~
        {                                                          //~9C11I~
            int num=dupctr[ii];                                    //~9C12I~
            if (num==1)                                            //~va27I~
            	ctrSingle++;                                       //~va27I~
            else                                                   //~va27I~
            if (num==2)                                            //~va14I~
                ctrPair++;                                         //~9C12I~
            else                                                   //~va14I~
            if (num==3 && sw7Pair4Pair)                            //~va27R~
            {                                                      //~va27I~
                ctrPair++;                                         //~va27R~
                ctrSingle++;                                       //~va27I~
            }                                                      //~va27I~
            else                                                   //~va27I~
            if (num==4 && sw7Pair4Pair)                            //~va27I~
                ctrPair+=2;                                        //~va27I~
        }                                                          //~9C11I~
        for (int jj=0;jj<PIECE_NUMBERTYPECTR;jj++)                 //~9C11I~
        {                                                          //~9C11I~
	        dupctr=dupCtr[jj];                                     //~9C12I~
            for (int ii=0;ii<PIECE_NUMBERCTR;ii++)                 //~9C11I~
            {                                                      //~9C11I~
    			int num=dupctr[ii];                                //~9C12I~
	            if (num==1)                                        //~va27I~
    	        	ctrSingle++;                                   //~va27I~
        	    else                                               //~va27I~
                if (num==2)                                        //~va14I~
    	            ctrPair++;                                     //~9C12I~
                else                                               //~va14I~
	            if (num==3 && sw7Pair4Pair)                        //~va27I~
    	        {                                                  //~va27I~
        	        ctrPair++;                                     //~va27I~
            	    ctrSingle++;                                   //~va27I~
            	}                                                  //~va27I~
            	else                                               //~va27I~
                if (num==4 && sw7Pair4Pair)                       //~va14I~
    	            ctrPair+=2;                                    //~va14I~
            }                                                      //~9C11I~
        }                                                          //~9C11I~
        boolean rc=ctrPair==6 && ctrSingle==1;                     //~va27R~
        if (Dump.Y) Dump.println("UAReachChk.is7Pair sw7Pair4Pair="+sw7Pair4Pair+",ctrPair="+ctrPair+",ctrSingle="+ctrSingle+",rc="+rc);       //~9C11I~//~9C12R~//~va27R~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
	//*************************************************************************//~9C11I~
	//*Kokushi                                                     //~0202I~
	//*************************************************************************//~0202I~
    private boolean is13All()                                      //~9C11I~//~va27R~
    {                                                              //~9C11I~
        if (Dump.Y) Dump.println("UAReachChk.is13All");            //~va27R~
        if (!swAllInHand)                                          //~va11R~
        	return false;                                          //~va11I~
        int ctrPillow=0;                                           //~9C11I~
        int ctrSingle=0;                                           //~9C11I~
        int num;                                                   //~9C11I~
        int[] dupctr;                                              //~9C12I~
        dupctr=dupCtr[TT_JI];                                      //~9C12I~
        for (int ii=0;ii<PIECE_NOTNUM_CTR;ii++)                    //~9C11I~
        {                                                          //~9C11I~
            num=dupctr[ii];                                        //~9C12I~
            if (num==2)                                            //~9C11I~
            	ctrPillow++;                                       //~9C11I~
            else                                                   //~9C11I~
            if (num==1)                                            //~9C11I~
            	ctrSingle++;                                       //~9C11I~
        }                                                          //~9C11I~
        for (int jj=0;jj<PIECE_NUMBERTYPECTR;jj++)                 //~9C11I~
        {                                                          //~9C11I~
	        dupctr=dupCtr[jj];                                     //~9C12I~
        	num=dupctr[0];                                         //~9C12I~
            if (num==2)                                            //~9C11I~
            	ctrPillow++;                                       //~9C11I~
            else                                                   //~9C11I~
            if (num==1)                                            //~9C11I~
            	ctrSingle++;                                       //~9C11I~
        	num=dupctr[PIECE_NUMBERCTR-1];                         //~9C12I~
            if (num==2)                                            //~9C11I~
            	ctrPillow++;                                       //~9C11I~
            else                                                   //~9C11I~
            if (num==1)                                            //~9C11I~
            	ctrSingle++;                                       //~9C11I~
        }                                                          //~9C11I~
//      boolean rc=ctrPillow==1 && ctrSingle==12;                  //~9C11I~//~va27R~
        boolean rc=(ctrPillow==1 && ctrSingle==11)||(ctrPillow==0 && ctrSingle==13);//~va27I~
        if (Dump.Y) Dump.println("UAReachChk.13All rc="+rc+",ctrPillow="+ctrPillow+",ctrSingle="+ctrSingle);//~va27R~
        return rc;                                                 //~9C11I~
    }                                                              //~9C11I~
}//class                                                           //~v@@@R~
