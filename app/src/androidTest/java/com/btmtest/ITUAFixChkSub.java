//*CID://+va23R~: update#= 976;                                    //~va23R~
//**********************************************************************//~v101I~
//2020/11/02 va23 use Junit for UARonValue                         //~va23I~
//**********************************************************************//~1107I~
package com.btmtest;                                               //~va23I~

import com.btmtest.TestOption;
import com.btmtest.dialog.RuleSettingYaku;
import com.btmtest.game.Players;
import com.btmtest.game.RA.RoundStat;
import com.btmtest.game.TileData;
import com.btmtest.game.UA.Pair;
import com.btmtest.game.UA.Rank;
import com.btmtest.game.UA.RonResult;
import com.btmtest.game.UA.UARonValue;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import com.btmtest.utils.Utils;

import static com.btmtest.game.GCMsgID.GCM_TAKE;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.UA.Pair.*;


public class ITUAFixChkSub extends UARonValue                    //~0B02R~//~va23R~
{                                                                  //~va11I~
	private AG AG;                                                 //~va23R~
    public boolean swTestAll=false;                                //~va23R~
	private int testCase=0;
	private Players PL;//~va23R~
    protected void init()                                          //~0B02I~
    {                                                              //~0B02I~
		AG=StaticVars.AG;                                          //~va23I~
	    PL=AG.aPlayers;                                    //~va23I~
        sw7Pair4Pair=true;                                         //~0B02I~
        sw13NoPair=true;                                           //~0B02I~
        sw14NoPair=true;                                           //~0B02I~
        new RoundStat();                                           //~va23I~
		AG.aRoundStat.swYakuFixLastMultiWaitTakeOK= RuleSettingYaku.isYakuFixMultiWaitTakeOK();//~vakaI~//~va23I~
    }                                                              //~0B02I~
	//*************************************************************************//~va23I~
	public void setCase(int Pcase)                                 //~va23I~
    {                                                              //~va23I~
    	testCase=Pcase;                                            //~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.setCase case="+Pcase);//~va23R~
    }                                                              //~va23I~
	//*************************************************************************//~va23I~
	public void setTestAll(boolean Pall)                           //~va23I~
    {                                                              //~va23I~
    	swTestAll=Pall;                                            //~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.setTestAll Pall="+Pall);//~va23R~
    }                                                              //~va23I~
	//*************************************************************************//~va23I~
	public RonResult ronTestValue(int Pcase)                       //~va23I~
	{                                                              //~va23I~
    	testCase=Pcase;                                            //~va23I~
		return ronTestValue();                                     //~va23I~
    }                                                              //~va23M~
    private void set1stTake()                                      //~va23I~
    {                                                              //~va23I~
        PL.actionBeforeRon=GCM_TAKE;                               //~va23I~
        PL.ctrTakenAll=1;                  //sim 1st take          //~va23I~
        sw1stTake=true;                                            //~va23I~
    }                                                              //~va23I~
    private void reset1stTake()                                    //~va23I~
    {                                                              //~va23I~
        PL.ctrTakenAll=10;                  //sim 1st take         //~va23I~
        sw1stTake=false;                                           //~va23I~
    }                                                              //~va23I~
	//*************************************************************************//~va23I~
    private int[][] testDupCtr,testDupCtrAll;                      //~va23I~
    private int testRonType,testRonNumber,testCtrAnkan;            //~va23I~
    private boolean testSwAllHand;                                 //~va23I~
    private Pair[]  testPairEarth;                                 //~va23I~
    private boolean testSkip1314NoPair;                            //~va23I~
	//*************************************************************************//~va23I~
    public RonResult ronTestSub(int[][] PdupCtr,int PronType,int PronNumber)//~va23I~
    {                                                              //~va23I~
        int[][] dupCtrAll= Utils.cloneArray2(PdupCtr);              //~va23I~
	    return ronTestSub(PdupCtr,dupCtrAll,PronType,PronNumber,0,true,null);//~va23I~
    }                                                              //~va23I~
    public RonResult ronTestSub(int[][] PdupCtr,int PronType,int PronNumber,boolean PswAllHand)//~va23I~
    {                                                              //~va23I~
        int[][] dupCtrAll=Utils.cloneArray2(PdupCtr);              //~va23I~
	    return ronTestSub(PdupCtr,dupCtrAll,PronType,PronNumber,0,PswAllHand,null);//~va23I~
    }                                                              //~va23I~
    public RonResult ronTestSub(int[][] PdupCtr,int[][] PdupCtrAll,int PronType,int PronNumber,int PctrAnkan,boolean PswAllHand,Pair[] PpairEarth,boolean PswTaken)//~va23I~
    {                                                              //~va23I~
    	boolean old=swTaken;                                       //~va23I~
        swTaken=PswTaken;                                          //~va23I~
	    RonResult r=ronTestSub(PdupCtr,PdupCtrAll,PronType,PronNumber,PctrAnkan,PswAllHand,PpairEarth);//~va23I~
    	swTaken=old;                                               //~va23I~
	    return r;                                                  //~va23I~
    }                                                              //~va23I~
    public RonResult ronTestSub(int[][] PdupCtr,int[][] PdupCtrAll,int PronType,int PronNumber,int PctrAnkan,boolean PswAllHand,Pair[] PpairEarth)//~va23I~
    {                                                              //~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTestSub start");//~va23R~
        int ctrTile=0;                                             //~va23I~
        for (int ii=0;ii<4;ii++)                                   //~va23I~
        	for (int jj=0;jj<9;jj++)                               //~va23I~
            	if (PdupCtr[ii][jj]!=0)                            //~va23I~
                	ctrTile++;                                     //~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTestSub ctrTile="+ctrTile);//~va23R~
        testDupCtr=PdupCtr;                                        //~va23I~
        testDupCtrAll=PdupCtrAll;                                  //~va23I~
        testSwAllHand=PswAllHand;                                  //~va23I~
        testCtrAnkan=PctrAnkan;                                    //~va23I~
        testRonType=PronType; testRonNumber=PronNumber;            //~va23I~
        testPairEarth=PpairEarth;                                  //~va23R~
        	dupCtr=testDupCtr;                                     //~va23I~
        	dupCtrAll=testDupCtrAll;                               //~va23I~
        	ctrAnkan=testCtrAnkan;                                 //~va23I~
        	ronType=testRonType; ronNumber=testRonNumber;          //~va23I~
        	pairEarth=testPairEarth;                               //~va23I~
        	swAllInHand=testSwAllHand;                             //~va23I~
            tdRonLast=new TileData(ronType,ronNumber,false/*dira*/);//~va23I~
                                                                   //~va23I~
    	    boolean rc=chkRonValueSub();                           //~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTestSub ronResult="+ronResult.toString()+",\nswAllInhand="+swAllInHand+",ronType="+PronType+",ronNumber="+PronNumber+"\n,dupCtr="+Utils.toString(PdupCtr)+"\n,dupCtrAll="+Utils.toString(PdupCtrAll));//~va23R~
        return ronResult;                                          //~va23I~
    }                                                              //~va23I~
	//*************************************************************************//~0B02I~
	public RonResult ronTestValue()                                //~va11R~
	{//~va11I~
	//*************************************************************************//~9C12I~
	    RonResult rc=null;                                              //~va11R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTestValue swTestAll="+swTestAll+",caseNo="+testCase);              //~9C12I~//~0B02R~//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-101 3shiki FIX_LAST related chii ryanmen rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23M~
    case 10101:                                                    //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-10101 3shiki FIX_LAST related chii ryanmen Take rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 10111:                                                    //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-10111 3shiki FIX_LAST ryanmen menzenn rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 1011101:                                                  //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth,/*swtake*/true);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1011101 3shiki FIX_LAST ryanmen menzenn rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1011 3shiki FIX_FIRST related Chii kanchan rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-10112 3shiki FIX_FIRST menzenn kanchan rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1012 3shiki FIX_FIRST related Chii,1-penchan rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1013 3shiki FIX_FIRST 9-penchan rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-102 3shiki FIX_FIRST 1 related chii not related ron tile rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-103 3shiki FIX_MIDDLE rc="+rc.toString());//~va23M~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1031 3shiki FIX_FIRST by ANKAN ron other rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-104 3shiki FIX_FIRST rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1041 3shiki FIX_LAST ryanmen  rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1042 3shiki FIX_LAST nonrelated Chii kanchan rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-105 3shiki FIX_FIRST double meld in hand rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-10501 3shiki FIX_FIRST double meld near+0 All in hand rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-10502 3shiki FIX_FIRST double meld near+1 All in hand rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-10503 3shiki FIX_FIRST double meld near+2 All in hand rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1051 3shiki FIX_FIRST double on earth rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1052 3shiki FIX_FIRST double on earth kanchan last rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1053 3shiki FIX_LAST  other chii ron last          rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1054 3shiki FIX_LAST  other chii ron last kanchan  rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-106 3shiki FIX_FIRST all chii rc="+rc.toString());//~va23M~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-107 3shiki FIX_FIRST all chii+tanki rc="+rc.toString());//~va23M~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-108 3shiki FIX_MIDDLE all chii other intermediate rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23I~
    case 10901:	//3sameseq                                         //~va23I~
		threesameseqAdditional(testCase);                          //~va23I~
        if (!swTestAll) break;                                     //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-201 ittsu  FIX_LAST rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 20101:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTaken*/);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-20101 ittsu  FIX_LAST TAKE rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-202 ittsu  FIX_FIRST rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2021 ittsu  FIX_FIRST allinhand+otherChii rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2022 ittsu  FIX_LAST last ryanmen+otherChii rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 202201:                                                   //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTaken*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-202201 ittsu  TAKE FIX_LAST last ryanmen+otherChii rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2023 ittsu  FIX_LAST last kanchan+otherChiirc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 20231:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{1,2,2, 2,1,1, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2023 ittsu  FIX_FIRST last is unrelated rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-203 ittsu  FIX_MIDDLE rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 203001:                                                   //~va23I~
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
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-203001 ittsu  FIX_FIRST  unrelated chii,ron unrelated rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 203002:                                                   //~va23I~
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
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-203002 ittsu  FIX_MIDDLE one unrelated chii ron related kanchan rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 203003:                                                   //~va23I~
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
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-203003 ittsu  FIX_MIDDLE one unrelated chii ron related ryanmen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 20300301:                                                 //~va23R~
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
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTaken*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-20300301 ittsu  TAKE FIX_MIDDLE one unrelated chii ron related ryanmen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 203004:                                                   //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-203004 ittsu  last Not Fix, FIRST/MIDDLE ERR rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 20300401:                                                 //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-20300401 ittsu  TAKE last Not Fix, FIRST/MIDDLE ERR rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 20301:                                                    //~va23I~
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
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-20301 ittsu  other chii but FIX_FIRST all in hand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-20310 ittsu  FIX_LAST middleChii+lastRon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 2031001:                                                  //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTaken*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2031001 ittsu  TAKE FIX_LAST middleChii+lastRon rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2031 ittsu  FIX_FIRST by ANKAN rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-204 ittsu  FIX_FIRST all in hand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2041 ittsu  FIX_LAST all in hand but ron rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-205 ittsu  FIX_FIRST double meld in hand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-20501 ittsu  FIX_FIRST menzen double meld ron ryanmen in hand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-20502 ittsu  FIX_FIRST menzen double meld ron kanchan in hand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-20510 ittsu  FIX_FIRST near double meld in hand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-20511 ittsu  FIX_FIRST near+0 double meld in hand rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-205111 ittsu  FIX_FIRST near+1 double meld in hand rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-205112 ittsu  FIX_FIRST near+2 double meld in hand rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-20512 ittsu  FIX_FIRST near double meld in hand rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2051 ittsu  FIX_FIRST double on earth rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2052 ittsu  FIX_LAST  near double on earth last ron rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 205201:                                                   //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTaken*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-205201 ittsu  TAKE FIX_LAST  near double on earth last ron rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2053 ittsu  FIX_FIRST near double on earth all in hand rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-206 ittsu  FIX_FIRST all chii rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2061 Not ITTSU  rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-207 ittsu  FIX_FIRST all chii+tanki rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-208 ittsu  FIX_MIDDLE all chii rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 209:                                                      //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-209 ittsu  FIX_LAST   menzenn ryanmen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 20901:                                                    //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true/*swAllHand*/,pairEarth,true/*swTaken*/);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-20901 ittsu  TAKE FIX_LAST   menzenn ryanmen rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2091ittsu  FIX_FIRST  menzenn kanchann rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2092ittsu  FIX_FIRST  menzenn penchan1 rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2093ittsu  FIX_FIRST  menzenn penchan9 rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 209401:                                                   //~va23I~
		straightAdditional(testCase);                                 //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-301 3tonko FIX_LAST ron last 1-chii rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-302 3tonko  FIX_FIRST ron other rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 3020:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,3/*ctr*/,TDF_PON      )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3020 3tonko  FIX_FIRST related+related+shanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 302001:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,3,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,7/*number*/,3/*ctr*/,TDF_PON      )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-302001 3tonko  TAKE FIX_FIRST related+related+shanpon rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3021 3tonko  FIX_FIRST kanTaken ron other c="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-303 3tonko  FIX_MIDDLE ron other rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3031 3tonko  FIX_FIRST by ANKAN ron other rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-304 3tonko  FIX_FIRST all in hand ron other rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 3040:                                                     //~va23I~
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
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3040 3tonko  FIX_MIDDLE nonrelated+related shanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 304001:                                                   //~va23I~
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
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     )//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-304001 3tonko  TAKE FIX_MIDDLE nonrelated+related shanpon rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3041 3tonko  FIX_LAST all in hand but ron last 1chii  rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3042 3tonko  FIX_LAST all in hand but ron last shanpon  rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 304201:                                                   //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-304201 3tonko  FIX_LAST TAKE all in hand but ron last shanpon  rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-306 3tonko  FIX_FIRST all pon ron other rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3061 3tonko  FIX_FIRST all pon or kan_river ron other rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3062 3tonko  FIX_FIRST all pon or kanTaken ron other rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3063 3tonko  FIX_FIRST all pon or kanAdd ron other rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-307 3tonko  FIX_FIRST all pon +tanki ron other rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-308 3tonko  FIX_MIDDLE all pon  rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 320:                                                      //~va23I~
        tanyaochanta3anko(testCase);                               //~va23I~
//      if (!swTestAll) break;                                     //~va23R~
        break;                                                     //~va23I~
//*********                                                        //~va23I~
    case 3401:                                                     //~va23I~
        tanyaochanta3anko(testCase);                               //~va23I~
//      if (!swTestAll) break;                                     //~va23R~
        break;                                                     //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-401 3kan    FIX_FIRST all kan rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-402 3kan    FIX_FIRST all kan +tanki rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-403 3kan    FIX_MIDDLE all kan  rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-404 3kan    FIX_FIRST  all kan (1 ankan) rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-501 3dragonSmall    FIX_FIRST  pillow in hand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-5011 3dragonSmall    FIX_FIRST  ron pillow rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-5012 3dragonSmall    FIX_FIRST  ron shanpon other rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-5013 3dragonSmall    FIX_LAST  ron shanpon WGR rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 5014:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-5014 3dragonSmall    FIX_LAST related+norelated pon,ron shanpon WGR rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 501401:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-501401 3dragonSmall TAKE   FIX_LAST TAKE related+norelated pon,ron shanpon WGR rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 5015:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-5015 3dragonSmall    FIX_LAST norelated+related pon,ron shanpon WGR rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 501501:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-50151 3dragonSmall  TAKE  FIX_LAST norelated+related pon,ron shanpon WGR rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-502 3dragonSmall    FIX_FIRST +other chii rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 5021:                                                     //~va23I~
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
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-5021 3dragonSmall other ron   FIX_FIRST +other chii+WGpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 502101:                                                   //~va23I~
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
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-502101 3dragonSmall  TAKE other ron FIX_FIRST +other chii+WGpon rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-503 3dragonSmall    FIX_FAST pillow last rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-5031 3dragonSmall    FIX_FIRST shanpon other  last rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-504 3dragonSmall    FIX_LAST WGR    last rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-505 3dragonSmall    FIX_FIRST with inhand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-506 3dragonSmall    FIX_MIDDLE with inhand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-5061 3dragonSmall    FIX_FIRST by ANKAN with inhand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-507 3dragonSmall    FIX_FIRST allInHand rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 508:                                                      //~va23I~
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
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-508 3dragonSmall    FIX_MIDDLE nonrelated+rWG R tanki rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 50801:                                                    //~va23I~
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
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-50801 3dragonSmall TAKE   FIX_MIDDLE nonrelated+rWG R tanki rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 509:                                                      //~va23I~
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
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-509 3dragonSmall    FIX_MIDDLE W+nonrelated+G R tanki rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 50901:                                                    //~va23I~
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
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-50901 3dragonSmall  TAKE  FIX_MIDDLE W+nonrelated+G R tanki rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 5091:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {3,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,0/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-5091 3dragonSmall    FIX_FIRST E+W+G R tanki rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 509101:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {3,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,3/*type*/,0/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swtake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-509101 3dragonSmall TAKE   FIX_FIRST E+W+G R tanki rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 5092:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {3,0,0,0,  0,0,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {3,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-5092 3dragonSmall    FIX_FMiddle nonrelated W+G R East anko tanki rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
//*honor tile                                                      //~va23I~
//*********                                                        //~va23I~
    case 601:                                                      //~va23I~
        honor(testCase);                                           //~va23I~
        if (!swTestAll) break;                                     //~va23I~
        break;                                                     //~va23I~
//*********                                                        //~va23I~
    default:  //value1                                             //~va23R~
        if (swTestAll) break;                                      //~va23R~
	    if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-@@@@ caseNo err");//~va23R~
    }                                                              //~va11I~
        return rc;                                                           //~9C12I~
    }                                                              //~va23I~
private void straightAdditional(int Pcase)                              //~va23I~
{                                                                  //~va23I~
	RonResult rc;
	switch (testCase)                                               //~va23I~
	{                                                              //~va23I~
		case 209401:                                                   //~va23I~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23M~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209401 fixLast, nonrelated Chii, kanchan Take rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209402:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23M~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209402 fixLast, nonrelated Chii, kanchan Ron  rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209403:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23M~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209403 fixLast, nonrelated Chii, ryanmen Take rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209404:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23M~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209404 fixLast, nonrelated Chii, ryanmen Ron  rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209405:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23M~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209405 fixLast,    related Chii, kanchan Take rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209406:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23M~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209406 fixLast,    related Chii, kanchan Ron  rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209407:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23M~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209407 fixLast,    related Chii, ryanmen Take rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209408:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23M~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209408 fixLast,    related Chii, ryanmen Ron  rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23I~
		case 2094081:                                              //~va23I~
			dupCtr = new int[][]{                                  //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                   //~va23I~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			dupCtrAll = new int[][]{                               //~va23I~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                   //~va23I~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true/*swTaken*/);//~va23I~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-2094081 fixLast,  nonrelated+related Chii, kanchan Take  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 2094082:                                              //~va23I~
			dupCtr = new int[][]{                                  //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                   //~va23I~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			dupCtrAll = new int[][]{                               //~va23I~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                   //~va23I~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,false/*swTaken*/);//~va23I~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-2094082 fixLast,  nonrelated+related Chii, kanchan Ron   rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 2094083:                                              //~va23I~
			dupCtr = new int[][]{                                  //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                   //~va23I~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			dupCtrAll = new int[][]{                               //~va23I~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                   //~va23I~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true/*swTaken*/);//~va23I~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-2094083 fixLast,  nonrelated+related Chii, ryanmen Take  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 2094084:                                              //~va23I~
			dupCtr = new int[][]{                                  //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                   //~va23I~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			dupCtrAll = new int[][]{                               //~va23I~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                   //~va23I~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,false/*swTaken*/);//~va23I~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-2094084 fixLast,  nonrelated+related Chii, ryanmen Ron   rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 2094085:                                              //~va23I~
			dupCtr = new int[][]{                                  //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                   //~va23I~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			dupCtrAll = new int[][]{                               //~va23I~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                   //~va23I~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,true /*swTaken*/);//~va23I~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-2094085 fixLast, related+nonrelated Chii, kanchan Take  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 2094086:                                              //~va23I~
			dupCtr = new int[][]{                                  //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                   //~va23I~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			dupCtrAll = new int[][]{                               //~va23I~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                   //~va23I~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 4/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,false/*swTaken*/);//~va23I~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-2094086 fixLast, related+nonrelated Chii, kanchan Ron   rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 2094087:                                              //~va23I~
			dupCtr = new int[][]{                                  //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                   //~va23I~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			dupCtrAll = new int[][]{                               //~va23I~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                   //~va23I~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,true /*swTaken*/);//~va23I~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-2094087 fixLast, related+nonrelated Chii, ryanmen Take  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 2094088:                                              //~va23I~
			dupCtr = new int[][]{                                  //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                   //~va23I~
					{0, 0, 0, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			dupCtrAll = new int[][]{                               //~va23I~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                   //~va23I~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                   //~va23I~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 1/*type*/, 0/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,false/*swTaken*/);//~va23I~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-2094088 fixLast, related+nonrelated Chii, ryanmen Ron   rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23M~
		case 209409:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209409 fixLast,    AllInHand   , kanchan Take rc=" + rc.toString());//~va23R~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209410:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209410 fixLast,    AllInHand   , kanchan Ron  rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209411:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209411 fixLast,    AllInHand   , ryanmen Take rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209412:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 0/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209412 fixLast,    AllInHand   , ryanmen Ron  rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209413:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209413 Already Fixed,  nonrelated Chii Take         rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209414:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 0/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209414 Already Fixed,  nonrelated Chii Ron          rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209415:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 0, 0, 0, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23M~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209415 Already Fixed,     related Chii Take         rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209416:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 0, 0, 0, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 1, 1, 1, 0, 0, 2},                                 //~va23M~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
					new Pair(PT_NUMSEQ, 1/*type*/, 3/*number*/, 3/*ctr*/, TDF_CHII),//~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23M~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209416 Already Fixed,     related Chii Ron          rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209417:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                   //~va23R~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                   //~va23R~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                   //~va23R~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209417 Already Fixed,     AllInHand    Take         rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
//*********                                                        //~va23M~
		case 209418:                                                   //~va23M~
			dupCtr = new int[][]{                                        //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                   //~va23R~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                   //~va23R~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			dupCtrAll = new int[][]{                                     //~va23M~
					{0, 0, 0, 3, 0, 0, 0, 0, 2},                   //~va23R~
					{1, 1, 1, 1, 1, 1, 1, 1, 1},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0},                                 //~va23M~
					{0, 0, 0, 0, 0, 0, 0, 0, 0}};                         //7 //~va23M~
			pairEarth = new Pair[]{                                      //~va23M~
			};                                                         //~va23M~
			rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 3/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)
				Dump.println("ITUAFixChkSub.ronTest-209418 Already Fixed,     AllInHand    Ron          rc=" + rc.toString());//~va23M~
			if (!swTestAll) break;                                     //~va23M~
	}
}//~va23I~
//*********                                                        //~va23I~
private void threesameseqAdditional(int testCase)                 //~va23I~
{                                                                  //~va23I~
	RonResult rc;                                                  //~va23I~
	switch (testCase)                                              //~va23I~
	{                                                              //~va23I~
		case 10901:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23R~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23I~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10901 fixLast, nonrelated Chii, kanchan Take rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10902:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10902 fixLast, nonrelated Chii, kanchan Ron  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10903:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10903 fixLast, nonrelated Chii, ryanmen Take rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10904:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10904 fixLast, nonrelated Chii, ryanmen Ron  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10905:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10905 fixLast,    related Chii, kanchan Take rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10906:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10906 fixLast,    related Chii, kanchan Ron  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10907:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10907 fixLast,    related Chii, ryanmen Take rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10908:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10908 fixLast,    related Chii, ryanmen Ron  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 109081:                                               //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-109081 fixLast,  nonrelated+related Chii, kanchan Take  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 109082:                                               //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-109082 fixLast,  nonrelated+related Chii, kanchan Ron   rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 109083:                                               //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-109083 fixLast,  nonrelated+related Chii, ryanmen Take  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 109084:                                               //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-109084 fixLast,  nonrelated+related Chii, ryanmen Ron   rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 109085:                                               //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,true /*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-109085 fixLast, related+nonrelated Chii, kanchan Take  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 109086:                                               //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-109086 fixLast, related+nonrelated Chii, kanchan Ron   rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 109087:                                               //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,true /*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-109087 fixLast, related+nonrelated Chii, ryanmen Take  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 109088:                                               //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
                    new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
                    new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth,false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-109088 fixLast, related+nonrelated Chii, ryanmen Ron   rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10909:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23R~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10909 fixLast,    AllInHand   , kanchan Take rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10910:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23R~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 2/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10910 fixLast,    AllInHand   , kanchan Ron  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10911:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23R~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10911 fixLast,    AllInHand   , ryanmen Take rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10912:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23R~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            pairEarth=new Pair[]{                                  //~va23I~
            };                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 1/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10912 fixLast,    AllInHand   , ryanmen Ron  rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10913:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 1/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII),//~va23R~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10913 Already Fixed,  nonrelated Chii Take         rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10914:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 1/*type*/, 6/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 0/*ronType*/, 8/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10914 Already Fixed,  nonrelated Chii Ron          rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10915:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10915 Already Fixed,     related Chii Take         rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10916:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,0,0, 0,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 1,1,1},                             //~va23I~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
					new Pair(PT_NUMSEQ, 0/*type*/, 1/*number*/, 3/*ctr*/, TDF_CHII),//~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/, false/*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10916 Already Fixed,     related Chii Ron          rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10917:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 3,0,0},                             //~va23R~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 3,0,0},                             //~va23R~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, true /*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10917 Already Fixed,     AllInHand    Take         rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
//*********                                                        //~va23I~
		case 10918:                                                //~va23I~
            dupCtr=new int[][]{                                    //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 3,0,0},                             //~va23R~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
            dupCtrAll=new int[][]{                                 //~va23I~
                {0,1,1, 1,0,0, 0,0,2},                             //~va23I~
                {0,1,1, 1,0,0, 3,0,0},                             //~va23R~
                {0,1,1, 1,0,0, 0,0,0},                             //~va23I~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23I~
			pairEarth = new Pair[]{                                //~va23I~
			};                                                     //~va23I~
			rc = ronTestSub(dupCtr, dupCtrAll, 1/*ronType*/, 6/*ronNumber*/, 1/*ctrAnkan*/, true /*saAllHand*/, pairEarth, false/*swTaken*/);//~va23R~
			if (Dump.Y)                                            //~va23I~
				Dump.println("ITUAFixChkSub.ronTest-10918 Already Fixed,     AllInHand    Ron          rc=" + rc.toString());//~va23I~
			if (!swTestAll) break;                                 //~va23I~
	}                                                              //~va23I~
}                                                                  //~va23I~
//*********                                                        //~va23I~
//*honor tile                                                      //~va23I~
//*********                                                        //~va23I~
private void honor(int testCase)                                   //~va23I~
{                                                                  //~va23I~
	RonResult rc;                                                  //~va23I~
	switch (testCase)                                              //~va23I~
	{                                                              //~va23I~
    case 601:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//+va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-601 Honor FIX_LAST menzen shanpon last rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 60101:                                                    //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true/*swTake*/);//+va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-60101 Honor FIX_LAST menzen shanpon TAKE rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 6011:                                                     //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6011 Honor FIX_FIRST menzen shabo rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 601101:                                                     //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-601101 Honor FIX_FIRST menzen shabo rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 6012:                                                     //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6012 Honor FIX_LAST nonrelated+ shabo last rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 601201:                                                   //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-601201 Honor FIX_LAST nonrelated+ shabo last TAKE rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 6013:                                                     //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,2,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {3,0,0,0,  3,2,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
				new Pair(PT_NUMSAME,3/*type*/,0/*number*/,3/*ctr*/,TDF_PON      ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6013 Honor FIX_MIDDLE nonrelated+east shabo WG last rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 601301:                                                   //~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-601301 Honor FIX_MIDDLE nonrelated+east shabo WG last rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 602:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,3,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,3,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-602 Honor FIX_FIRST last but other in hand rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 603:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,3,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,3/*ctr*/,TDF_PON      ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-603 Honor FIX_FIRST last but 1st on earth in hand rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 604:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-604 Honor FIX_FIRST with inhand rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 6041:                                                     //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6041 Honor FIX_FIRST nonrelated +W anko rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 605:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-605 Honor FIX_FIRST by 1st earth rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 6051:                                                     //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6051 Honor FIX_FIRST by 1st earth with other 2nd rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 606:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-606 Honor FIX_FIRST by 1st earth 2nd other rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 6061:                                                     //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6061 Honor FIX_MIDDLE nonrelated+W pon rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 6062:                                                     //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  0,3,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,3,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6062 Honor FIX_FISRT nonrelated+Wpon,G anko rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 607:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  0,3,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,3,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-607 Honor FIX_MIDDLE ronTile but earth is middle rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 60701:                                                    //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  0,3,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  3,3,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,TDF_PON      ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-60701 Honor FIX_MIDDLE ronTile but earth is middle rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 608:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,3,0},                                 //~va23M~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSAME,2/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-608 NoHonor  rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 609:                                                      //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSAME,2/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-609 FIX_LAST Wind/Round  rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 60901:                                                    //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23M~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSAME,2/*type*/,6/*number*/,3/*ctr*/,TDF_CHII     ),//~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-60901 FIX_LAST Wind/Round TAKE rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 6091:                                                     //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6091 FIX_LAST Wind/Round shanpon rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 609101:                                                   //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,2},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true/*swTake*/);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-609101 FIX_LAST Wind/Round shanpon TAKE rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 6092:                                                     //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {3,0,0,0,  2,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {3,0,0,0,  2,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6092 FIX_FIRST Wind/Round menzen WGR tanki rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 6093:                                                     //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {3,0,0,0,  2,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {3,0,0,0,  2,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6093 FIX_FIRST Wind/Round menzen shabo with WGR  rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
    case 6094:                                                     //~va23M~
        dupCtr=new int[][]{                                        //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23M~
        dupCtrAll=new int[][]{                                     //~va23M~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23M~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23M~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23M~
    	    {3,0,0,0,  2,0,0, 0,0} };                         //7  //~va23M~
        pairEarth=new Pair[]{                                      //~va23M~
				new Pair(PT_NUMSAME,3/*type*/,0/*number*/,3/*ctr*/,TDF_PON      ),//~va23R~
        };                                                         //~va23M~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23M~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6094 FIX_FIRST Wind/Round 1st,WGR tanki rc="+rc.toString());//~va23M~
        if (!swTestAll) break;                                     //~va23M~
                                                                 //~va23I~
    case 6095:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{2,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23R~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{2,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23R~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6095 FIX_MIDDLE nonrelated WN sghanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 609501:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{2,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23R~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{2,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va23R~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-609501 FIX_MIDDLE TAKE nonrelated WN sghanpon rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 6096:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSEQ ,3/*type*/,4/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6096 FIX_MIDDLE nonrelated related GR sghanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 609601:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSEQ ,3/*type*/,4/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-609601 TAKE FIX_MIDDLE nonrelated related GR sghanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 6097:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSEQ ,3/*type*/,4/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6097 FIX_MIDDLE nonrelated related GN sghanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 609701:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,3,2, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  3,3,2, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
				new Pair(PT_NUMSEQ ,3/*type*/,4/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-609701 FIX_MIDDLE nonrelated related GN sghanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    }                                                              //~va23I~
}//honortile                                                       //~va23I~
private void tanyaochanta3anko(int testCase)                       //~va23I~
{                                                                  //~va23I~
	RonResult rc;                                                  //~va23I~
	switch (testCase)                                              //~va23I~
	{                                                              //~va23I~
//***********                                                      //~va23I~
//3anko                                                            //~va23I~
//***********                                                      //~va23I~
    case 320:                                                      //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-320 FIX_FIRST menzen norelated ron rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 320001:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true /*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-320001 TAKE FIX_FIRST menzen norelated ron rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3201:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3201   YAKU=0         non related,shanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 320101:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23R~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,3/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true /*swTake*/);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-320101      FIX_LAST  non related,shanpon rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 3202  :                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3202   YAKU=0         non related,shanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 320201:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true /*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-320201 YAKU=0         non related,shanpon rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 3203  :                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,3,0,0,  2,0,0, 0,0} };                         //7  //~va23R~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,3,0,0,  2,0,0, 0,0} };                         //7  //~va23R~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3203   YAKU=0         non related,shanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 320301:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,3,0,0,  2,0,0, 0,0} };                         //7  //~va23R~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,3,0,0,  2,0,0, 0,0} };                         //7  //~va23R~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true /*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-320301 YAKU=0         non related,shanpon rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 3204  :                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3204   YAKU=0         non related,shanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 320401:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true /*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-320401 YAKU=0         non related,shanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3205  :                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23R~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{4,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,1/*type*/,0/*number*/,4/*ctr*/,TDF_KAN_TAKEN     ),//~va23I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3205   YAKU=0         non related,shanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 320501:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23R~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{4,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSAME,1/*type*/,0/*number*/,4/*ctr*/,TDF_KAN_TAKEN     ),//~va23I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,3/*ctr*/,TDF_PON      ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true /*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-320501 YAKU=0         non related,shanpon rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3206  :                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3206 memzen shanpon                  rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 320601:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  2,3,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true /*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-320601 memzen shanpon  TAKE                rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//***********                                                      //~va23I~
//chanta                                                           //~va23I~
//***********                                                      //~va23I~
    case 3301:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3301 Mix Chanta  Penchan      menzen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 330101:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true /*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-330101 Mix Chanta TAKE  Penchan     menzen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3302:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3302 Mix Chanta  ryanmen      menzen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 330201:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-330201 Mix Chanta TAKE ryanmen      menzen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3303:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,2,0,3,  0,0,0, 0,0} };                         //7  //~va23R~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va23R~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,2,0,3,  0,0,0, 0,0} };                         //7  //~va23R~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3303 Mix Chanta  related kanchan    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 330301:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,2,0,3,  0,0,0, 0,0} };                         //7  //~va23R~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va23R~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,2,0,3,  0,0,0, 0,0} };                         //7  //~va23R~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23R~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-330301 Mix Chanta TAKE related kanchan    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3304:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3304 Mix Chanta  related ryanmen    rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 330401:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-330401 Mix Chanta TAKE related ryanmen    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3305:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3305 Mix Chanta NOCHANTA nonrelated+nrelated kanchan    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 330501:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-330401 Mix Chanta TAKE nonrelate+related kanchan    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3306:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3306 Mix Chanta shanpon  nonrelated+nrelated kanchan    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 330601:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-330601 Mix Chanta TAKE shanpon nonrelate+related kanchan    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3307:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3307 pure Chanta  related ryanmen    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 330701:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,6/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-330701 pure Chanta TAKE related ryanmen    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3308:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3308 Mix Chanta  related ryanmen    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 330801:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,3,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,0/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-330801 Mix Chanta TAKE related ryanmen    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3309:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3309 pureChanta  ryanmen      menzen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 330901:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-330901 pureChanta TAKE ryanmen      menzen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3310:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{1,1,1, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{1,1,1, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3310 pureChanta  ryanmen same ronmum meld     menzen rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 331001:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{1,1,1, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va23I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va23I~
        	{1,1,1, 0,0,0, 0,0,3},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true /*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-331001 TAKE pureChanta  ryanmen same ronmum meld     menzen rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//***********                                                      //~va23I~
//tanyao                                                           //~va23I~
//***********                                                      //~va23I~
    case 3401:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,false/*swTake*/);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3401     Tanyao  kanchan      menzen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 340101:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true /*swTake*/);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-340101     Tanyao TAKE  kanchan     menzen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3402:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3402     Tanyao  ryanmen fix     menzen rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 340201:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-340201     Tanyao TAKE ryanmen      menzen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3403:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3403     Tanyao  related kanchan    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 340301:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,2/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-340301     Tanyao TAKE related kanchan    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3404:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3404     Tanyao  related ryanmen    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 340401:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-340401     Tanyao TAKE related ryanmen    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3405:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,2,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,3,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,2,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3405  NO Tanyao                                         rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3406:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,1/*number*/,3/*ctr*/,TDF_PON           ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,3/*number*/,3/*ctr*/,TDF_PON           ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,7/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3406     Tanyao tanki    nonrelated+nrelated kanchan    rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3407:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,3/*number*/,3/*ctr*/,TDF_PON           ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3407     Tanyao shanpon                                 rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
	case 340701:                                                   //~va23R~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,0,0, 0,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSEQ ,1/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
				new Pair(PT_NUMSAME,2/*type*/,3/*number*/,3/*ctr*/,TDF_PON           ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true /*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-340701 TAKE   Tanyao shanpon                                 rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3408:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,2,2, 2,0,0, 0,2,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3408     Tanyao ryanmen right side                      rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 340801:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,2,2, 2,0,0, 0,2,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true /*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-340801 TAKE     Tanyao ryanmen right side                      rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3409:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,2,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,2,0, 0,0,2, 2,2,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3409     Tanyao ryanmen left  side                      rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 340901:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,2,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,2,0, 0,0,2, 2,2,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
				new Pair(PT_NUMSEQ ,0/*type*/,5/*number*/,3/*ctr*/,TDF_CHII          ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth,true /*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-340901 TAKE   Tanyao ryanmen left  side                      rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 3410:                                                     //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,false/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3410     Tanyao  ryanmen         menzen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 341001:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        dupCtrAll=new int[][]{                                     //~va23I~
        	{0,1,1, 1,0,0, 0,2,0},                                 //~va23I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~va23I~
        	{0,3,0, 3,0,0, 0,0,0},                                 //~va23I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va23I~
        pairEarth=new Pair[]{                                      //~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth,true/*swTake*/);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-341001     Tanyao TAKE ryanmen      menzen rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    }                                                              //~va23M~
}//tanyaochanta3peiko                                              //~va23I~
}//class                                                           //~v@@@R~
