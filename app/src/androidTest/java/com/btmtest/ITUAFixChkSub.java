//*CID://+va23R~: update#= 915;                                    //~va23R~
//**********************************************************************//~v101I~
//2020/11/02 va23 use Junit for UARonValue                         //~va23I~
//**********************************************************************//~1107I~
package com.btmtest;                                               //~va23I~

import com.btmtest.TestOption;
import com.btmtest.game.Players;
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
	private AG AG=StaticVars.AG;                                   //~va23I~
    Players PL=AG.aPlayers;                                        //~va23I~
    public boolean swTestAll=false;                                //~va23R~
	private int testCase=0;                                        //~va23R~
    protected void init()                                          //~0B02I~
    {                                                              //~0B02I~
        sw7Pair4Pair=true;                                         //~0B02I~
        sw13NoPair=true;                                           //~0B02I~
        sw14NoPair=true;                                           //~0B02I~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-101 3shiki FIX_LAST ryanmen rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23M~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1011 3shiki FIX_FIRST kanchan rc="+rc.toString());//~va23R~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1012 3shiki FIX_FIRST 1-penchan rc="+rc.toString());//~va23I~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1031 3shiki FIX_FIRST by ANKAN rc="+rc.toString());//~va23I~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-104 3shiki FIX_FIRST all in hand rc="+rc.toString());//~va23M~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1041 3shiki FIX_LAST all in hand but ron rc="+rc.toString());//~va23I~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-1042 3shiki FIX_LAST other chii ron kanchan rc="+rc.toString());//~va23R~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-10501 3shiki FIX_FIRST double meld near+0 in hand rc="+rc.toString());//~va23I~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-10502 3shiki FIX_FIRST double meld near+1 in hand rc="+rc.toString());//~va23I~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-10503 3shiki FIX_FIRST double meld near+2 in hand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2052 ittsu  FIX_LAST  neer double on earth last ron rc="+rc.toString());//~va23R~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2053 ittsu  FIX_FIRST neer double on earth all in hand rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-209 ittsu  FIX_LAST   menzenn ryanmen rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-2093ittsu  FIX_FIRST  menzenn penchan9 rc="+rc.toString());//~va23I~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-302 3tonko  FIX_FIRST kanTaken ron other c="+rc.toString());//~va23R~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3041 3tonko  FIX_LAST all in hand but ron last shanpon  rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 305:                                                      //~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-305 3tonko  FIX_FIRST double meld in hand NOP");//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 3051:                                                     //~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3051 3tonko  FIX_FIRST double on earth NOP");//~va23R~
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
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-3062 3tonko  FIX_FIRST all pon or kanAdd ron other rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-601 Honor FIX_LAST menzen last rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6011 Honor FIX_FIRST menzen shabo rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6012 Honor FIX_LAST Not menzen shabo last rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6013 Honor FIX_MIDDLE Not menzen shabo last rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-602 Honor FIX_FIRST last but other in hand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-603 Honor FIX_FIRST last but 1st on earth in hand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-604 Honor FIX_FIRST with inhand rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6041 Honor FIX_FIRST with inhand with other earth rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-605 Honor FIX_FIRST by 1st earth rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6051 Honor FIX_FIRST by 1st earth with other 2nd rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-606 Honor FIX_FIRST by 1st earth 2nd other rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6061 Honor FIX_MIDDLE by 1st other 2nd honor rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6062 Honor FIX_FISRT 2nd Earth but 1st InHand rc="+rc.toString());//~va23R~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-607 Honor FIX_MIDDLE ronTile but earth is middle rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-608 NoHonor  rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-609 FIX_LAST Wind/Round  rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
    case 6091:                                                     //~va23I~
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
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6091 FIX_LAST Wind/Round shanpon rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6092 FIX_FIRST Wind/Round menzen WGR tanki rc="+rc.toString());//~va23I~
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
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,true /*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6093 FIX_FIRST Wind/Round menzen shabo with WGR  rc="+rc.toString());//+va23R~
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
				new Pair(PT_NUMSAME,3/*type*/,0/*number*/,3/*ctr*/,TDF_CHII     ),//~va23I~
        };                                                         //~va23I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23I~
        if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-6094 FIX_FIRST Wind/Round 1st,WGR tanki rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    default:  //value1                                             //~va23R~
        if (swTestAll) break;                                      //~va23R~
	    if (Dump.Y) Dump.println("ITUAFixChkSub.ronTest-@@@@ caseNo err");//~va23R~
    }                                                              //~va11I~
        return rc;                                                           //~9C12I~
    }                                                              //~va23I~
}//class                                                           //~v@@@R~
