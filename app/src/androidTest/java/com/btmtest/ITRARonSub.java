//*CID://+va23R~: update#= 952;                                    //~va23R~
//**********************************************************************//~v101I~
//2020/11/02 va23 use Junit for UARonValue                         //~va23I~
//**********************************************************************//~1107I~
package com.btmtest;                                               //~va23I~

import com.btmtest.game.Players;
import com.btmtest.game.RA.RARon;
import com.btmtest.game.TileData;
import com.btmtest.game.UA.Pair;
import com.btmtest.game.UA.RonResult;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.PLAYERS;
import static com.btmtest.game.GConst.*;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.UA.Pair.*;


public class ITRARonSub extends RARon                              //~va23R~
{                                                                  //~va11I~
	private AG AG=StaticVars.AG;                                   //~va23I~
    Players PL=AG.aPlayers;                                        //~va23I~
    public boolean swTestAll=false;                                //~va23R~
	private int testCase=0;                                        //~va23R~
    protected void init()                                          //~0B02I~
    {                                                              //~0B02I~
    }                                                              //~0B02I~
	//*************************************************************************//~va23I~
	public void setCase(int Pcase)                                 //~va23I~
    {                                                              //~va23I~
    	testCase=Pcase;                                            //~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.setCase case="+Pcase);//~va23R~
    }                                                              //~va23I~
	//*************************************************************************//~va23I~
	public void setTestAll(boolean Pall)                           //~va23I~
    {                                                              //~va23I~
    	swTestAll=Pall;                                            //~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.setTestAll Pall="+Pall);//~va23R~
    }                                                              //~va23I~
	//*************************************************************************//~va23I~
	public boolean ronTestValue(int Pcase)                       //~va23I~
	{                                                              //~va23I~
    	testCase=Pcase;                                            //~va23I~
		return ronTestValue();                                     //~va23I~
    }                                                              //~va23M~
    private void set1stTake()                                      //~va23I~
    {                                                              //~va23I~
        PL.actionBeforeRon=GCM_TAKE;                               //~va23I~
        PL.ctrTakenAll=1;                  //sim 1st take          //~va23I~
    }                                                              //~va23I~
    private void reset1stTake()                                    //~va23I~
    {                                                              //~va23I~
        PL.ctrTakenAll=10;                  //sim 1st take         //~va23I~
    }                                                              //~va23I~
	//*************************************************************************//~va23I~
    private int[][] testDupCtr,testDupCtrAll;                      //~va23I~
    private int[][] dupCtr,dupCtrAll;                              //~va23I~
    private int testRonType,testRonNumber,testCtrAnkan;            //~va23I~
    private boolean testSwAllHand;                                 //~va23I~
    private Pair[]  testPairEarth,pairEarth;                       //~va23R~
    private boolean testSkip1314NoPair;                            //~va23I~
    private int eswnOther=1;                                       //~va23I~
    private int eswnYou=0,playerYou=0,playerOther=1;               //~va23R~
    private boolean swMatchMode;                                   //~va23I~
	//*************************************************************************//~va23I~
    public boolean  ronTestSub(int[][] PdupCtr,int PronType,int PronNumber)//~va23I~
    {                                                              //~va23I~
        int[][] dupCtrAll= Utils.cloneArray2(PdupCtr);              //~va23I~
	    return ronTestSub(PdupCtr,dupCtrAll,PronType,PronNumber,0,true,null);//~va23I~
    }                                                              //~va23I~
    public boolean  ronTestSub(int[][] PdupCtr,int PronType,int PronNumber,boolean PswAllHand)//~va23I~
    {                                                              //~va23I~
        int[][] dupCtrAll=Utils.cloneArray2(PdupCtr);              //~va23I~
	    return ronTestSub(PdupCtr,dupCtrAll,PronType,PronNumber,0,PswAllHand,null);//~va23I~
    }                                                              //~va23I~
    private void newGame()                                              //~va23I~
    {                                                              //~va23I~
    	AG.aPlayers.ctrTakenAll=1;                                 //~va23I~
        AG.aPlayers.newGame(false,playerYou);                      //~va23I~
    }                                                              //~va23I~
    public boolean ronTestSubMatchMode(int[][] PdupCtr,int[][] PdupCtrAll,int PronType,int PronNumber,int PctrAnkan,boolean PswTake,Pair[] PpairEarth)//~va23I~
    {                                                              //~va23I~
    	swMatchMode=true;                                          //~va23I~
	    boolean rc=ronTestSub(PdupCtr,PdupCtrAll,PronType,PronNumber,PctrAnkan,PswTake,PpairEarth);//~va23I~
    	swMatchMode=false;                                         //~va23I~
        return rc;                                                 //~va23I~
    }                                                              //~va23I~
    public boolean ronTestSub(int[][] PdupCtr,int[][] PdupCtrAll,int PronType,int PronNumber,int PctrAnkan,boolean PswTake,Pair[] PpairEarth)//~va23R~
    {                                                              //~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTestSub start");   //~va23R~
        newGame();                                                 //~va23R~
        int ctrTile=0;                                             //~va23I~
        for (int ii=0;ii<4;ii++)                                   //~va23I~
        	for (int jj=0;jj<9;jj++)                               //~va23I~
            {                                                      //~va23I~
            	ctrTile+=PdupCtr[ii][jj];                          //~va23R~
                if (!PswTake && ii==PronType && jj==PronNumber)    //~va23I~
                	ctrTile--;                                     //~va23I~
            }                                                      //~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTestSub ctrTile="+ctrTile);//~va23R~
        testDupCtr=PdupCtr;                                        //~va23I~
        testDupCtrAll=PdupCtrAll;                                  //~va23I~
//      testSwAllHand=PswAllHand;                                  //~va23R~
    	testSwAllHand=PpairEarth.length==0;                        //~va23I~
        testCtrAnkan=PctrAnkan;                                    //~va23I~
        testRonType=PronType; testRonNumber=PronNumber;            //~va23I~
        testPairEarth=PpairEarth;                                  //~va23R~
        	dupCtr=testDupCtr;                                     //~va23I~
        	dupCtrAll=testDupCtrAll;                               //~va23I~
        	int ronType=testRonType; int ronNumber=testRonNumber;          //~va23I~
        	pairEarth=testPairEarth;                               //~va23I~
            TileData tdRonLast=new TileData(ronType,ronNumber,false/*dira*/);//~va23I~
        TileData[] tds=setTdsHand(dupCtr,ctrTile,PswTake,PronType,PronNumber);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTestSub tdsHand="+TileData.toString(tds));//~va23I~
        AG.aPlayers.setHandsClient(playerYou,tds);                 //~va23R~
        int[] itsH=AG.aRoundStat.getItsHandEswnYou(eswnYou);       //~va23R~
        AG.aRoundStat.RSP[eswnYou].swAllInHand=testSwAllHand;      //~va23I~
        setEarth(pairEarth);                                       //~va23I~
    	boolean rc;                                                //~va23R~
      if (swMatchMode)                                             //~va23I~
      {                                                            //~va23I~
    	rc=isRonableMultiWaitMatchModeHuman(PLAYER_YOU,PswTake,tdRonLast);//~va8fI~//~va23I~
      }                                                            //~va23I~
      else                                                         //~va23I~
      {                                                            //~va23I~
        if (PswTake)                                               //~va23I~
        {                                                          //~va23I~
	    	rc=callRonTaken(playerYou,eswnYou,itsH,ctrTile,tdRonLast);//~va23I~
        }                                                          //~va23I~
        else                                                       //~va23I~
	    	rc=callRonRiver(eswnYou,tdRonLast);                    //~va23I~
	  }                                                            //~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTestSub rc="+rc+",ronType="+PronType+",ronNumber="+PronNumber+"\n,dupCtr="+Utils.toString(PdupCtr)+"\n,dupCtrAll="+Utils.toString(PdupCtrAll));//~va23R~
        return rc;                                          //~va23I~
    }                                                              //~va23I~
    private TileData[] setTdsHand(int[][] PdupCtr,int PctrTile,boolean PswTake,int PronType,int PronNumber)//~va23R~
    {                                                              //~va23I~
    	TileData[] tds=new TileData[PctrTile];                      //~va23I~
    	int ctr=0;                                                 //~va23I~
    	for (int ii=0;ii<4;ii++)                                   //~va23I~
        	for (int jj=0;jj<9;jj++)                                   //~va23I~
            {                                                      //~va23I~
            	int dup=PdupCtr[ii][jj];                           //~va23R~
                if (!PswTake && ii==PronType && jj==PronNumber)    //~va23R~
                	dup--;                                         //~va23I~
                for (int kk=0;kk<dup;kk++)                        //~va23I~
                {                                                  //~va23I~
                	TileData td=new TileData(ii,jj);               //~va23I~
                    tds[ctr++]=td;                                 //~va23I~
                }                                                  //~va23I~
            }                                                      //~va23I~
        return tds;    	                                           //~va23I~
    }                                                              //~va23I~
    private void setEarth(Pair[] PpairEarth)                 //~va23I~
    {                                                              //~va23I~
    	for (int ii=0;ii<PpairEarth.length;ii++)                   //~va23I~
        {                                                          //~va23I~
         	Pair pair=PpairEarth[ii];                              //~va23I~
         	int type=pair.type;                                    //~va23I~
         	int num=pair.number;                                   //~va23I~
         	int ctr=pair.dupCtr;                                   //~va23I~
         	int flag=pair.flag;                                    //~va23I~
         	int flagPair=0;                                          //~va23I~
            TileData[] tds=new TileData[ctr];                      //~va23I~
	        switch(pair.typePair)                                  //~va23I~
            {                                                      //~va23I~
            case PT_NOTNUM:                                        //~va23I~
            case PT_NUMSAME:                                       //~va23I~
            	for (int jj=0;jj<ctr;jj++)                         //~va23I~
                {                                                  //~va23I~
                	tds[jj]=new TileData(type,num,flag,jj/*remain*/,eswnYou);//~va23I~
                }                                                  //~va23I~
                flagPair=ctr==4 ? TDF_KAN_RIVER : TDF_PON;         //~va23I~
            	break;                                             //~va23I~
            case PT_NUMSEQ:                                        //~va23I~
            	for (int jj=0;jj<ctr;jj++)                         //~va23I~
                {                                                  //~va23I~
                	tds[jj]=new TileData(type,num+jj,flag,0/*remain*/,eswnYou);//~va23I~
                }                                                  //~va23I~
                flagPair=TDF_CHII;                                 //~va23I~
            	break;                                             //~va23I~
            }                                                      //~va23I~
            AG.aPlayers.addPair(playerYou,tds,flagPair);            //~va23I~
	        if (Dump.Y) Dump.println("IRRARonSub.setEarth tds="+TileData.toString(tds));//~va23I~
        }                                                          //~va23I~
    }                                                              //~va23I~
	//*************************************************************************//~0B02I~
	public boolean ronTestValue()                                //~va11R~
	{//~va11I~
	//*************************************************************************//~9C12I~
	    boolean rc=false;                                              //~va11R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTestValue swTestAll="+swTestAll+",caseNo="+testCase);              //~9C12I~//~0B02R~//~va23R~
//*********                                                        //~va11I~
    switch(testCase)                                               //~va11I~
    {                                                              //~va11I~
    case 0:                                                        //~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23M~
//*3shiki                                                          //~va23I~
//*********                                                        //~va23I~
    case 101:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-101 3shiki FIX_LAST ryanmen rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23M~
    case 10111:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,3, 0,0,0},                                 //~va23R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-10111 3shiki FIX_LAST ryanmen menzenn rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 1011:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-1011 3shiki FIX_FIRST kanchan rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 10112:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-10112 3shiki FIX_FIRST menzenn kanchan rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 1012:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 0,0,1, 1,1,0},                                 //~va23I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 0,0,1, 1,1,0},                                 //~va23I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-1012 3shiki FIX_FIRST 1-penchan rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 1013:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{2,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-1013 3shiki FIX_FIRST 9-penchan rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23M~
    case 102:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-102 3shiki FIX_FIRST 1 related chii not related ron tile rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23I~
    case 103:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-103 3shiki FIX_MIDDLE rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23I~
    case 1031:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,4, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,1/*type*/,5/*number*/,4/*ctr*/,TDF_KAN_TAKEN     ),//~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-1031 3shiki FIX_FIRST by ANKAN rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23M~
    case 104:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-104 3shiki FIX_FIRST all in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23I~
    case 1041:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-1041 3shiki FIX_LAST all in hand but ron rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 1042:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-1042 3shiki FIX_LAST other chii ron kanchan rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23M~
    case 105:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,2,2, 2,0,0, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,2,2, 2,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-105 3shiki FIX_FIRST double meld in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23I~
    case 10501:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-10501 3shiki FIX_FIRST double meld near+0 in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 10502:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-10502 3shiki FIX_FIRST double meld near+1 in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 10503:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-10503 3shiki FIX_FIRST double meld near+2 in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 1051:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,2,2, 2,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-1051 3shiki FIX_FIRST double on earth rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 1052:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,2,2, 2,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-1052 3shiki FIX_FIRST double on earth kanchan last rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 1053:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-1053 3shiki FIX_LAST  other chii ron last          rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 1054:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-1054 3shiki FIX_LAST  other chii ron last kanchan  rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23M~
    case 106:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
				new Pair(PT_NUMSEQ ,2/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-106 3shiki FIX_FIRST all chii rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
    case 107:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
				new Pair(PT_NUMSEQ ,2/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-107 3shiki FIX_FIRST all chii+tanki rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
    case 108:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23M~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va23M~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
				new Pair(PT_NUMSEQ ,2/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-108 3shiki FIX_MIDDLE all chii other intermediate rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23I~
//*ittsu  straight                                                 //~va23R~
//*********                                                        //~va23I~
    case 201:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 3,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,3/*ctr*/,TDF_PON           ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-201 ittsu  ryanmen FIX_LAST pon other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 202:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 3,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-202 ittsu  FIX_FIRST rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2021:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,2,2, 2,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2021 ittsu  FIX_FIRST allinhand+otherChii rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2022:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,2,2, 2,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2022 ittsu  FIX_LAST last ryanmen+otherChii rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2023:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,2,2, 2,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2023 ittsu  FIX_LAST last kanchan+otherChii rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 203:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
				new Pair(PT_NUMSEQ ,1/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-203 ittsu  FIX_MIDDLE rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 20310:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-20310 ittsu  FIX_LAST 2shiki middleChii+last ryanmen Ron rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2031:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 4,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,4/*ctr*/,TDF_KAN_TAKEN     ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2031 ittsu  FIX_FIRST by ANKAN rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 204:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-204 ittsu  FIX_FIRST all in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2041:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2041 ittsu  FIX_LAST all in hand but kanchan ron rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 205:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 2,2,2, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 2,2,2, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-205 ittsu  FIX_FIRST double meld in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 20501:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 2,2,2, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 2,2,2, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-20501 ittsu  FIX_FIRST menzen double meld ron ryanmen in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 20502:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 2,2,2, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 2,2,2, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-20502 ittsu  FIX_FIRST menzen double meld ron kanchan in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 20510:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-20510 ittsu  FIX_FIRST near double meld in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 20511:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-20511 ittsu  FIX_FIRST near+0 double meld in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 205111:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-205111 ittsu  FIX_FIRST near+1 double meld in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 205112:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-205112 ittsu  FIX_FIRST near+2 double meld in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 20512:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
         dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-20512 ittsu  FIX_FIRST near double meld in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2051:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 2,2,2, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2051 ittsu  FIX_FIRST double on earth rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2052:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,2/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2052 ittsu  FIX_LAST  neer double on earth last ryanmen  ron rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2053:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,2, 2,2,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,2/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2053 ittsu  FIX_FIRST neer double on earth all in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 206:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
				new Pair(PT_NUMSEQ ,1/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-206 ittsu  FIX_FIRST all chii rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2061:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,2, 1,1,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,2/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2061 Not ITTSU  rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 207:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
				new Pair(PT_NUMSEQ ,1/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-207 ittsu  FIX_FIRST all chii+tanki rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 208:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-208 ittsu  FIX_MIDDLE all chii rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 209:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 3,0,0, 0,0,2},                                 //~va23R~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 3,0,0, 0,0,2},                                 //~va23R~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-209 ittsu  FIX_LAST   menzenn ryanmen no other yaku rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2091:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2091ittsu  FIX_FIRST  menzenn kanchann rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2092:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2092ittsu  FIX_FIRST  menzenn penchan1 rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 2093:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-2093ittsu  FIX_FIRST  menzenn penchan9 rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va11I~
//*3tonko                                                          //~va23I~
//*********                                                        //~va23I~
    case 301:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{2,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,0},                                 //~va23I~
        	{2,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-301 3tonko FIX_LAST ron last 1-chii rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 302:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-302 3tonko  FIX_FIRST ron other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 3021:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,4,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_TAKEN)//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-302 3tonko  FIX_FIRST kanTaken ron other c="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 303:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-303 3tonko  FIX_MIDDLE ron other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 3031:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 4,0,0, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-3031 3tonko  FIX_FIRST by ANKAN ron other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 304:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-304 3tonko  FIX_FIRST all in hand ron other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 3041:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-3041 3tonko  FIX_LAST all in hand but ron last 1chii  rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 3042:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-3041 3tonko  FIX_LAST all in hand but ron last shanpon  rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 305:                                                      //~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-305 3tonko  FIX_FIRST double meld in hand NOP");//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 3051:                                                     //~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-3051 3tonko  FIX_FIRST double on earth NOP");//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 306:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,3/*ctr*/,TDF_PON      )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-306 3tonko  FIX_FIRST all pon ron other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 3061:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,4,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_RIVER)//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-3061 3tonko  FIX_FIRST all pon or kan_river ron other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 3062:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,4,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_TAKEN)//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-3062 3tonko  FIX_FIRST all pon or kanTaken ron other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 3063:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,4,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_ADD  )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-3062 3tonko  FIX_FIRST all pon or kanAdd ron other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 307:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-307 3tonko  FIX_FIRST all pon +tanki ron other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 308:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,3/*ctr*/,TDF_PON      )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-308 3tonko  FIX_MIDDLE all pon  rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
//*3kan                                                            //~va23R~
//*********                                                        //~va23I~
    case 401:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,4,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,4,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,4,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_ADD  ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_TAKEN)//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-401 3kan    FIX_FIRST all kan rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 402:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,4,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,4,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,4,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_ADD  ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_ADD  ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_ADD  ),//~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-402 3kan    FIX_FIRST all kan +tanki rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 403:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,4,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,4,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,4,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_ADD  ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_ADD  ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_ADD  ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-403 3kan    FIX_MIDDLE all kan  rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 404:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,4,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,4,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,4,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_ADD  ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_ADD  ),//~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-404 3kan    FIX_FIRST  all kan (1 ankan) rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
//*3DragonSmall                                                    //~va23I~
//*********                                                        //~va23I~
    case 501:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-501 3dragonSmall    FIX_FIRST  pillow in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 5011:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-5011 3dragonSmall    FIX_FIRST  ron pillow rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 5012:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-5012 3dragonSmall    FIX_FIRST  ron shanpon other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 5013:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-5013 3dragonSmall    FIX_LAST  ron shanpon WGR rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 502:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON     ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII)//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-502 3dragonSmall    FIX_FIRST +other chii rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 503:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-503 3dragonSmall    FIX_FAST pillow last rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 5031:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-5031 3dragonSmall    FIX_FIRST shanpon other  last rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 504:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-504 3dragonSmall    FIX_LAST WGR    last rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 505:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-505 3dragonSmall    FIX_FIRST with inhand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 506:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-506 3dragonSmall    FIX_MIDDLE with inhand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 5061:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 4,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-5061 3dragonSmall    FIX_FIRST by ANKAN with inhand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 507:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-507 3dragonSmall    FIX_FIRST allInHand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
//*honor tile                                                      //~va23I~
//*********                                                        //~va23I~
    case 601:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-601 Honor FIX_LAST menzen last rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 6010:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true /*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6010 Honor FIX_LAST menzen tsumo last rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 60101:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,true /*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-60101 Honor FIX_FIRST in hand shabo other tsumo last rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 60102:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,4, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,4, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-60102 Honor FIX_FIRST in hand point calc same and seq last rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 60103:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,4, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,4, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,true /*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-60103 Honor FIX_FIRST in hand point calc same and seq tsumo last rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 6011:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6011 Honor FIX_FIRST menzen shabo rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 6012:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6012 Honor FIX_LAST Not menzen shabo both WGR last rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 60121:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true /*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-60121 Honor FIX_LAST Not menzen tsumo shabo both WGR last rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 6013:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {3,0,0,0,  3,2,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,0/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6013 Honor FIX_MIDDLE Not menzen shabo last rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 602:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-602 Honor FIX_FIRST last but other in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 603:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-603 Honor FIX_FIRST last but 1st on earth in hand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 604:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-604 Honor FIX_FIRST with inhand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 6041:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6041 Honor FIX_FIRST with inhand with other earth rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 605:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-605 Honor FIX_FIRST by 1st earth rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 6051:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6051 Honor FIX_FIRST by 1st earth with other 2nd rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 606:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-606 Honor FIX_FIRST by 1st earth 2nd other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 6061:                                                     //~va23R~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6061 Honor FIX_MIDDLE by 1st other 2nd honor rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 6062:                                                     //~va23R~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6062 Honor FIX_FISRT 2nd Earth but 1st InHand rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 607:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-607 Honor FIX_MIDDLE ronTile but earth is middle rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 608:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,2/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-608 NoHonor  rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 609:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,2/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-609 FIX_LAST other pon + shabo Wind/Round and Num RonWGR  rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 6090:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,2/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6090 FIX_LAST other pon + shabo Wind/Round and Num  tsumo WGR rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 6091:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23R~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6091 FIX_LAST menzen shabo Wind/Round and num rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 60911:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-60911 FIX_LAST menzen tsumo shabo Wind/Round and num rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 6092:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {3,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {3,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6092 FIX_FIRST Wind/Round menzen WGR tanki rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 6093:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {3,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {3,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6093 FIX_FIRST Wind/Round menzen shabo with WGR  rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 6094:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {3,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,0/*number*/,3/*ctr*/,TDF_PON      ),//~va23R~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-6094 FIX_FIRST Wind/Round 1st,WGR tanki rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    default:  //value1                                             //~va23I~
		rc=ronTestValue2();                                        //~va23R~
	    if (Dump.Y) Dump.println("IRRARonSub.ronTest-@@@@ caseNo err");//~va23I~
    }                                                              //~va23I~
        return rc;                                                 //~va23I~
    }                                                              //~va23I~
	//*************************************************************************//~va23I~
	//*************************************************************************//~va23I~
	//*************************************************************************//~va23I~
	public boolean ronTestValue2()                                 //~va23I~
	{                                                              //~va23I~
	//*************************************************************************//~va23I~
	    boolean rc=false;                                          //~va23I~
//*********                                                        //~va23I~
    switch(testCase)                                               //~va23I~
    {                                                              //~va23I~
//*********                                                        //~va23I~
    case 701:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,2,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23R~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,2,0,  0,0,3, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,6/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-701 FIX_Middle WGR rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 702:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,1, 1,1,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,1, 1,1,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-702 FIX_FIRST menzen shanpon WGR rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 703:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,3, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,3, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,3, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,3, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-703 FIX_FIRST but last all multiOK-1/3 3anko rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 7031:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,2,3, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,3, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,2,3, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,3, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-7031 FIX_FIRST last but all multiOK-2/3 4anko rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 7032:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,3, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,3, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,3, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,3, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-7032 FIX_FIRST last but all multiOK-3/3 3anko tanyao rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 704:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 2,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 2,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-704 FIX_LAST last WGR kataagari rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 705:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,2,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,2,0, 0,0,0},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-705  FIX_LAST last ittsu kataagari rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 706:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,2,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,2,0, 0,0,0},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-706  FIX_FIRST last 1tsu  other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 707:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,2,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,2,0, 0,0,0},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-707  FIX_FIRST last but 1ttsu penchan rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 708:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{3,0,0, 2,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{3,0,0, 2,0,0, 0,0,0},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-708  FIX_FIRST  last but 1ttsu kanchan rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 7093:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,1,1, 2,1,1},                                 //~va23I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,3, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,1,1, 2,1,1},                                 //~va23I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,3, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-7093  FIX_FIRST WGR inhand shannpon with other rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70941:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{3,3,3, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 2,0,3},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{3,3,3, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 2,0,3},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70941 FIX_FIRST all yaku 1/2 4anko rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70942:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{3,3,3, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,3},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{3,3,3, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,3},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70942 FIX_FIRST all yaku 2/2 junchan ipeiko rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70951:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,3,3, 2,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,3,3, 2,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70951 FIX_FIRST all yaku 1/3 toitoi 3anko rc="+rc);//~va23R~
//*********                                                        //~va23I~
    case 70952:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,2,3, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,2,3, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70952 FIX_FIRST all yaku 2/3 toitoi2 3anko rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70953:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,2,4, 2,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,2,4, 2,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70953 FIX_FIRST all yaku 3/3 1peijo rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70961:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,3,1, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,3,1, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70961 FIX_FIRST all yaku 1/3 pinfu   rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70962:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,3,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,3,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70962 FIX_FIRST all yaku 2/3 tanpin  rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70963:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,3,2, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,3,2, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70963 FIX_FIRST all yaku 3/3 tanyao  rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70971:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{1,1,1, 2,1,1, 1,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{1,1,1, 2,1,1, 1,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70971 FIX_FIRST all yaku 1/3 pinfu   rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70972:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 3,1,1, 1,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 3,1,1, 1,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70972 FIX_FIRST all yaku 2/3 tanpin  rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70973:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 2,1,1, 2,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 2,1,1, 2,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70973 FIX_FIRST all yaku 3/3 tanyao  rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70981:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 1,2,1, 1,1,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,1,1, 1,2,1, 1,1,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70981 FIX_FIRST all yaku 1/3 tanpin  rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70982:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,0,1, 1,3,1, 1,1,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,0,1, 1,3,1, 1,1,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70982 FIX_FIRST all yaku 2/3 tanpin  rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70983:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,0,1, 1,2,1, 1,2,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,1,1, 1,0,0},                                 //~va23I~
        	{0,0,1, 1,2,1, 1,2,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70983 FIX_FIRST all yaku 3/3 tanyao  rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 70991:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{3,0,1, 1,1,0, 0,0,0},                                 //~va23I~
        	{0,0,1, 1,1,0, 0,0,0},                                 //~va23I~
        	{0,0,1, 1,1,0, 0,2,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{3,0,1, 1,1,0, 0,0,0},                                 //~va23I~
        	{0,0,1, 1,1,0, 0,0,0},                                 //~va23I~
        	{0,0,1, 1,1,0, 0,2,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-70991 FIX_LAST  ryanmen   3shiki     rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 709101:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{3,0,0, 2,0,0, 0,0,0},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{3,0,0, 2,0,0, 0,0,0},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-709101 FIX_LAST  ryanmen   1ttsu      rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 709111:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{2,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{2,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-709111 FIX_FIRST ryanmen   pinfu 1/2 ittsu     rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 709112:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{2,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{1,1,1, 1,1,2, 1,1,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{2,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{1,1,1, 1,1,2, 1,1,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-709112 FIX_FISRST ryanmen   pinfu 2/2 pinfu     rc="+rc);//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 709121:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{2,0,2, 2,2,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{2,0,2, 2,2,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-709121 FIX_FIRST ryanmen  2peiko 1/2           rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 709122:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{2,0,2, 2,2,0, 0,0,0},                                 //~va23I~
        	{0,0,1, 2,2,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{2,0,2, 2,2,0, 0,0,0},                                 //~va23I~
        	{0,0,1, 2,2,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*swTake*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("IRRARonSub.ronTest-709122 FIX_FIRST ryanmen  1peiko 2/2           rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    default:  //value1                                             //~va23R~
		rc=ronTestValue3();                                        //~va23I~
	    if (Dump.Y) Dump.println("IRRARonSub.ronTest-@@@@ caseNo err");//~va23R~
    }                                                              //~va11I~
        return rc;                                                           //~9C12I~
    }                                                              //~va23I~
	//*************************************************************************//~va23I~
	//*************************************************************************//~va23I~
	//*************************************************************************//~va23I~
	public boolean ronTestValue3()                                 //~va23I~
	{                                                              //~va23I~
		//*************************************************************************//~va23I~
		boolean rc = false;                                          //~va23I~
//*********                                                        //~va23I~
		switch (testCase)                                               //~va23I~
		{                                                              //~va23I~
//*********                                                        //~va23I~
			case 801:                                                      //~va23I~
				dupCtr = new int[][]{                                        //~va23I~
						{1, 1, 1, 1, 1, 1, 0, 2, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7  //~va23I~
				dupCtrAll = new int[][]{                                     //~va23I~
						{1, 1, 1, 1, 1, 1, 0, 2, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7  //~va23I~
				pairEarth = new Pair[]{                                      //~va23I~
				};                                                         //~va23I~
				rc = ronTestSubMatchMode(dupCtr, dupCtrAll, 0/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, true /*swTake*/, pairEarth);//~va23I~
				if (Dump.Y)
					Dump.println("IRRARonSub.ronTest-801 OK menzen tsumo kataagari Not Tanyao rc=" + rc);//~va23I~
				if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
			case 8011:                                                     //~va23I~
				dupCtr = new int[][]{                                        //~va23I~
						{0, 1, 1, 2, 1, 1, 0, 2, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7  //~va23I~
				dupCtrAll = new int[][]{                                     //~va23I~
						{0, 1, 1, 2, 1, 1, 0, 2, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7  //~va23I~
				pairEarth = new Pair[]{                                      //~va23I~
				};                                                         //~va23I~
				rc = ronTestSubMatchMode(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, true /*swTake*/, pairEarth);//~va23I~
				if (Dump.Y)
					Dump.println("IRRARonSub.ronTest-8011 OK menzen tsumo kataagari tanyao rc=" + rc);//~va23R~
				if (!swTestAll) break;                                     //~va23I~
			case 8012:                                             //~va23I~
				dupCtr = new int[][]{                              //~va23I~
						{0, 1, 1, 2, 1, 1, 0, 2, 0},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
				dupCtrAll = new int[][]{                           //~va23I~
						{0, 1, 1, 2, 1, 1, 0, 2, 0},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 3, 0},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
				pairEarth = new Pair[]{                            //~va23I~
				};                                                 //~va23I~
				rc = ronTestSubMatchMode(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, false/*swTake*/, pairEarth);//~va23I~
				if (Dump.Y)                                        //~va23I~
					Dump.println("IRRARonSub.ronTest-8012 OK menzen Ron kataagari tanyao rc=" + rc);//~va23I~
				if (!swTestAll) break;                             //~va23I~
			case 802:                                                      //~va23I~
				dupCtr = new int[][]{                                        //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23I~
						{0, 0, 0, 1, 1, 1, 1, 1, 1},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7  //~va23I~
				dupCtrAll = new int[][]{                                     //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23I~
						{0, 0, 0, 1, 1, 1, 1, 1, 1},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 3},                                 //~va23I~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7  //~va23I~
				pairEarth = new Pair[]{                                      //~va23I~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~va23I~
						new Pair(PT_NUMSAME, 2/*type*/, 8/*number*/, 3/*ctr*/, TDF_PON)//~va23I~
				};                                                         //~va23I~
				rc = ronTestSubMatchMode(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/, false/*swTake*/, pairEarth);//~va23I~
				if (Dump.Y) Dump.println("IRRARonSub.ronTest-802  FIX_FIRST WGR rc=" + rc);//~va23I~
				if (!swTestAll) break;                                     //~va23I~
			case 8021:                                                     //~va23I~
				dupCtr = new int[][]{                                        //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23I~
						{0, 0, 0, 1, 1, 1, 1, 1, 1},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7  //~va23I~
				dupCtrAll = new int[][]{                                     //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23I~
						{0, 0, 0, 1, 1, 1, 1, 1, 1},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 3},                                 //~va23I~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7  //~va23I~
				pairEarth = new Pair[]{                                      //~va23I~
						new Pair(PT_NUMSAME, 2/*type*/, 8/*number*/, 3/*ctr*/, TDF_PON),//~va23I~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON),//~va23I~
				};                                                         //~va23I~
				rc = ronTestSubMatchMode(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/, false/*swTake*/, pairEarth);//~va23I~
				if (Dump.Y)
					Dump.println("IRRARonSub.ronTest-8021  FIX_MIDDLE WGR rc=" + rc);//~va23I~
				if (!swTestAll) break;                                     //~va23I~
			case 8022:                                                     //~va23I~
				dupCtr = new int[][]{                                        //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23I~
						{0, 0, 0, 1, 1, 1, 1, 1, 1},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23I~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7  //~va23I~
				dupCtrAll = new int[][]{                                     //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23I~
						{0, 0, 0, 1, 1, 1, 1, 1, 1},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 3},                                 //~va23I~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7  //~va23I~
				pairEarth = new Pair[]{                                      //~va23I~
						new Pair(PT_NUMSAME, 2/*type*/, 8/*number*/, 3/*ctr*/, TDF_PON)//~va23I~
				};                                                         //~va23I~
				rc = ronTestSubMatchMode(dupCtr, dupCtrAll, 3/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/, false/*swTake*/, pairEarth);//~va23I~
				if (Dump.Y) Dump.println("IRRARonSub.ronTest-8022  FIX_LAST WGR ok if kataagari setting of FixLast rc=" + rc);//~va23R~
				if (!swTestAll) break;                                     //~va23I~
			case 803:                                                      //~va23I~
				dupCtr = new int[][]{                                        //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23I~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7  //~va23I~
				dupCtrAll = new int[][]{                                     //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23I~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 3},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7  //~va23I~
				pairEarth = new Pair[]{                                      //~va23I~
						new Pair(PT_NUMSAME, 2/*type*/, 8/*number*/, 3/*ctr*/, TDF_PON)//~va23I~
				};                                                         //~va23I~
				rc = ronTestSubMatchMode(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, true /*swTake*/, pairEarth);//~va23I~
				if (Dump.Y)
					Dump.println("IRRARonSub.ronTest-803 ittsu kataagari tsumo OK if setting is not menzen tsumo ok rc=" + rc);//~va23R~
				if (!swTestAll) break;                                     //~va23I~
			case 8031:                                                     //~va23I~
				dupCtr = new int[][]{                                        //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23I~
						{0, 1, 1, 2, 1, 1, 1, 1, 1},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7  //~va23I~
				dupCtrAll = new int[][]{                                     //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23I~
						{0, 1, 1, 2, 1, 1, 1, 1, 1},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 3},                                 //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7  //~va23I~
				pairEarth = new Pair[]{                                      //~va23I~
						new Pair(PT_NUMSAME, 2/*type*/, 8/*number*/, 3/*ctr*/, TDF_PON)//~va23I~
				};                                                         //~va23I~
				rc = ronTestSubMatchMode(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, true /*swTake*/, pairEarth);//~va23I~
				if (Dump.Y)
					Dump.println("IRRARonSub.ronTest-8031 ittsu kataagari tsumo NG rc=" + rc);//~va23I~
				if (!swTestAll) break;                                     //~va23I~
			case 8032:                                             //~va23I~
				dupCtr = new int[][]{                              //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~va23I~
						{0, 1, 1, 1, 1, 1, 2, 1, 1},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
				dupCtrAll = new int[][]{                           //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~va23I~
						{0, 1, 1, 1, 1, 1, 2, 1, 1},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~va23I~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~va23I~
				pairEarth = new Pair[]{                            //~va23I~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON)//~va23I~
				};                                                 //~va23I~
				rc = ronTestSubMatchMode(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/,false /*swTake*/, pairEarth);//~va23R~
				if (Dump.Y)                                        //~va23I~
					Dump.println("IRRARonSub.ronTest-8032 FIX_FIRST multiron rc=" + rc);//~va23I~
			case 8033:                                             //~va23I~
				dupCtr = new int[][]{                              //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~va23I~
						{0, 0, 0, 0, 1, 1, 2, 1, 1},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
				dupCtrAll = new int[][]{                           //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~va23I~
						{0, 1, 1, 1, 1, 1, 2, 1, 1},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~va23I~
						{0, 0, 0, 0, 3, 0, 0, 0, 0}};                         //7//~va23I~
				pairEarth = new Pair[]{                            //~va23I~
						new Pair(PT_NUMSEQ , 1/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
						new Pair(PT_NUMSAME, 3/*type*/, 4/*number*/, 3/*ctr*/, TDF_PON)//~va23I~
				};                                                 //~va23I~
				rc = ronTestSubMatchMode(dupCtr, dupCtrAll, 1/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/, true /*swTake*/, pairEarth);//~va23R~
				if (Dump.Y)                                        //~va23I~
					Dump.println("IRRARonSub.ronTest-8033 FIX_MIDDLE  multiron rc=" + rc);//~va23I~
				if (!swTestAll) break;                             //~va23I~
			case 8034:                                             //~va23I~
				dupCtr = new int[][]{                              //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~va23I~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
				dupCtrAll = new int[][]{                           //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 2},               //~va23I~
						{1, 1, 1, 1, 1, 1, 1, 1, 1},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 3},               //~va23I~
						{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
				pairEarth = new Pair[]{                            //~va23I~
						new Pair(PT_NUMSAME, 2/*type*/, 8/*number*/, 3/*ctr*/, TDF_PON)//~va23I~
				};                                                 //~va23I~
				rc = ronTestSubMatchMode(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, false/*swTake*/, pairEarth);//~va23I~
				if (Dump.Y)                                        //~va23I~
					Dump.println("IRRARonSub.ronTest-8034 FIX_LAST kataagari OK if kataagariOK setting multiron rc=" + rc);//+va23R~
				if (!swTestAll) break;                             //~va23I~
			default:  //value1                                             //~va23I~
				if (Dump.Y) Dump.println("IRRARonSub.ronTest3-@@@@ caseNo err="+testCase);//~va23R~
		}                                                              //~va23I~
		return rc;
	}//~va23I~
}//class                                                           //~v@@@R~
