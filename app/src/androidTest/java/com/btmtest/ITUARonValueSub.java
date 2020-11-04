//*CID://+va23R~: update#= 866;                                    //~va23R~
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


public class ITUARonValueSub extends UARonValue                    //~0B02R~
{                                                                  //~va11I~
	private AG AG=StaticVars.AG;                                   //~va23I~
    Players PL=AG.aPlayers;                                        //~va23I~
    public boolean swTestAll=false;                                //~va23R~
//    private int[][] dupCtr,dupCtrAll;                            //~va23R~
//    private Pair[] pairEarth;                                    //~va23R~
	private int testCase=0;                                        //~va23R~
//    public ITUARonValueSub(UARonValue Puarv)                     //~0B02R~
//    {                                                            //~0B02R~
//        UARV=Puarv;                                              //~0B02R~
//        testCase= TestOption.testCaseRonValue;                   //~0B02R~
//    }                                                            //~0B02R~
    protected void init()                                          //~0B02I~
    {                                                              //~0B02I~
//        sw7Pair4Pair= RuleSettingYaku.is7Pair4Pair();            //~0B02I~
//        sw13NoPair= RuleSettingYaku.isYakuman13NoPair();         //~0B02I~
//        sw14NoPair= RuleSettingYaku.isYakuman14NoPair();         //~0B02I~
        sw7Pair4Pair=true;                                         //~0B02I~
        sw13NoPair=true;                                           //~0B02I~
        sw14NoPair=true;                                           //~0B02I~
    }                                                              //~0B02I~
	//*************************************************************************//~va23I~
	public void setCase(int Pcase)                                 //~va23I~
    {                                                              //~va23I~
    	testCase=Pcase;                                            //~va23I~
        if (Dump.Y) Dump.println("ITUARonValueSub.setCase case="+Pcase);//~va23I~
    }                                                              //~va23I~
	//*************************************************************************//~va23I~
	public void setTestAll(boolean Pall)                           //~va23I~
    {                                                              //~va23I~
    	swTestAll=Pall;                                            //~va23I~
        if (Dump.Y) Dump.println("ITUARonValueSub.setTestAll Pall="+Pall);//~va23I~
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
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTestSub start");//~va23R~
//        if (swGetTestHand)                                       //~va23R~
//        {                                                        //~va23R~
//            testHand=PdupCtr;                                    //~va23R~
//            if (Dump.Y) Dump.println("ITUARonValueSub.ronTestSub getTestHand="+Utils.toString(testHand));//~va23R~
//            return new RonResult(0,0,0,new Rank()); //dummy      //~va23R~
//        }                                                        //~va23R~
//        TestOption.option2 &= ~TestOption.TO2_RONVALUE_TEST;     //~va23I~
//        TestOption.option2 |= TestOption.TO2_RONVALUE_TESTSUB;   //~va23I~
        int ctrTile=0;                                             //~va23I~
        for (int ii=0;ii<4;ii++)                                   //~va23I~
        	for (int jj=0;jj<9;jj++)                               //~va23I~
            	if (PdupCtr[ii][jj]!=0)                            //~va23I~
                	ctrTile++;                                     //~va23I~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTestSub ctrTile="+ctrTile);//~va23I~
//      if (ctrTile<HANDCTR)                                       //~va23I~
//      	testSkip1314NoPair=true;                               //~va23I~
        testDupCtr=PdupCtr;                                        //~va23I~
        testDupCtrAll=PdupCtrAll;                                  //~va23I~
        testSwAllHand=PswAllHand;                                  //~va23I~
        testCtrAnkan=PctrAnkan;                                    //~va23I~
        testRonType=PronType; testRonNumber=PronNumber;            //~va23I~
        testPairEarth=PpairEarth;                                  //~va23R~
//      getValue(PLAYER_YOU);	//left:amt,top:yaku,right:rank,bottom:point//~va23I~
        	dupCtr=testDupCtr;                                     //~va23I~
        	dupCtrAll=testDupCtrAll;                               //~va23I~
        	ctrAnkan=testCtrAnkan;                                 //~va23I~
        	ronType=testRonType; ronNumber=testRonNumber;          //~va23I~
        	pairEarth=testPairEarth;                               //~va23I~
        	swAllInHand=testSwAllHand;                             //~va23I~
            tdRonLast=new TileData(ronType,ronNumber,false/*dira*/);//~va23I~
                                                                   //~va23I~
    	    boolean rc=chkRonValueSub();                           //~va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTestSub ronResult="+ronResult.toString()+",\nswAllInhand="+swAllInHand+",ronType="+PronType+",ronNumber="+PronNumber+"\n,dupCtr="+Utils.toString(PdupCtr)+"\n,dupCtrAll="+Utils.toString(PdupCtrAll));//~va23R~
//        TestOption.option2 |= TestOption.TO2_RONVALUE_TEST;      //~va23I~
//        TestOption.option2 &= ~TestOption.TO2_RONVALUE_TESTSUB;  //~va23I~
        return ronResult;                                          //~va23I~
    }                                                              //~va23I~
	//*************************************************************************//~0B02I~
	public RonResult ronTestValue()                                //~va11R~
	{//~va11I~
	//*************************************************************************//~9C12I~
        if ((TestOption.option2 & TestOption.TO2_CHKRANK)==0)      //~va23M~
	        UView.showToastLong("ITUARonValueSub.Start testCase="+testCase);                 //~0A11R~//~va23I~
	    RonResult rc=null;                                              //~va11R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTestValue");              //~9C12I~//~0B02R~
//        if (testCase>=255010 && testCase<255999)                   //~0A11I~//~va23R~
//        {                                                        //~va23R~
//            rc=ronTestValue2();                                  //~va23R~
//            if (!swTestAll)                                      //~va23R~
//                return;                                          //~va23R~
//        }                                                        //~va23R~
//*********                                                        //~va11I~
    switch(testCase)                                               //~va11I~
    {                                                              //~va11I~
    case 0:                                                        //~va11I~
        dupCtr=new int[][]{           //7 pair                     //~va11M~
        	{0,2,2, 0,0,0, 0,0,0},                                 //~9C12I~//~va11M~
        	{0,2,2, 0,0,0, 0,0,0},                                 //~9C12I~//~va11M~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~9C12I~//~va11M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~//~va11M~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-0 7pair tanyao rc="+rc.toString());//~9C12R~//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va11I~
    case 1:                                                        //~va11I~
        dupCtr=new int[][]{           //7 pair                     //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 2,2,2,2,  2,2,2, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-1 7pair tsuiso rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 20101:                                                    //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 2,2,2,2,  2,2,1, 1,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-20101 tsuiso err "+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 2:                                                        //~va11I~
        dupCtr=new int[][]{           //7 pair                     //~9C12I~//~va11M~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~9C12I~//~va11M~
        	{2,2,2, 0,0,0, 2,0,2},                                 //~9C12I~//~va11M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~//~va11M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~//~va11M~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2 7pair simple rc="+rc.toString());//~9C12R~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2021:                                                     //~va11I~
        dupCtr=new int[][]{           //7 pair                     //~va11I~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{2,2,0, 0,0,0, 4,0,2},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2021 7pair by kan rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 2022:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,2, 2,2,0, 0,2,0},                                 //~0A17R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2022 7pair tanyao/pinfu 2peiko rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 2023:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,2,2, 2,0,2, 2,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,2,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2023 7pair/honitsu 2peiko rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 2024:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,2,2, 0,0,2, 2,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,2,2,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2024 7pair honitsu         c="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 2025:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,2,2, 2,0,2, 2,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,2,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2025 7pair honitsu/2peiko  c="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 2026:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{2,2,2, 2,2,0, 2,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2026 7pair chinitsu       c="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 2027:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{2,2,2, 2,2,2, 0,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2027 7pair chinitsu / 2peiko    c="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 2028:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{2,0,0, 0,0,0, 0,0,2},                                 //~0A17I~
    	    { 0,0,0,0,  2,2,2, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2028 7pair honro                c="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 2029:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{2,0,0, 0,0,0, 0,0,2},                                 //~0A17I~
        	{2,0,0, 0,0,0, 0,0,2},                                 //~0A17I~
        	{2,0,0, 0,0,0, 0,0,4},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17R~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2029 7pair chinro   with kan    c="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 20291:                                                    //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,2,2, 2,0,2, 0,2,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,4,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,2,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-20291 7pair allgreen             c="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2201:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 1,1,1},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2201 junchan rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 22011:                                                    //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~0A17I~
        	{2,0,0, 0,0,0, 1,1,1},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A17I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~0A17I~
        	{2,0,0, 0,0,0, 1,1,1},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSEQ ,0/*type*/,0/*number*/,3/*ctr*/,0            ),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22011 junchan naki rc="+rc.toString());//~0B02R~
       if (!swTestAll) break;                                      //~va23R~
//      break; //TODO test                                         //~va23R~
//*********                                                        //~va11I~
    case 2202:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 1,1,1},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0,0,false);                           //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2202 junchan naki rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2203:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va11I~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0,0,true);                            //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2203 honchan      rc="+rc.toString());//~0B02R~
        if (!swTestAll)  break;                                    //~va23R~
//*********                                                        //~0A17I~
    case 22031:                                                    //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~0A17I~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A17I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~0A17I~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSEQ ,0/*type*/,0/*number*/,3/*ctr*/,0            ),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22031 honchan naki     rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2204:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va11I~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0,0,false);                           //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2204 honchan naki rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 3:                                                        //~va11I~
        dupCtr=new int[][]{                                        //~va11M~
        	{3,2,0, 0,0,0, 0,0,0},                                 //~9C12R~//~va11M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~//~va11M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~//~va11M~
    	    { 3,3,0,0,  3,0,0, 0,0} };                         //7 //~9C12R~//~va11M~
	    rc=ronTestSub(dupCtr,0/*ronType*/,1/*ronNumber*/);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-3 4Anko tanki rc="+rc.toString());//~9C12R~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2301:                                                     //~va11R~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va11I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va23R~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,0/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2301 pinfu 1ttsu  rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va23I~
    case 23011:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va23R~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va23I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,0/*ronNumber*/);         //~va23I~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23011 pinfu 1ttsu  rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va23I~
    case 23012:                                                    //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va23I~
        	{0,2,0, 0,0,0, 1,1,1},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,0/*ronNumber*/);         //~va23I~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23012 pinfu 1ttsu  rc="+rc.toString());//~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va11I~
    case 2302:                                                     //~va11R~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11R~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,0/*ronNumber*/,false);   //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2302 1ttsu naki rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2303:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,2/*ronNumber*/,false);   //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2303 1ttsu naki 30fu rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 231:                                                      //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11R~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11R~
    	    { 0,0,0,0,  2,0,0, 0,0} };                         //7 //~va11R~
	    rc=ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-231 3shiki  not pinf   rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2310:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 2,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 3,0,0,0,  3,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2310 wgr and round      rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 23101:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 2,0,3},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23101 no wgr and round/wind      rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 23102:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 2,0,0,0,  3,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,3/*ronType*/,0/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23102 pillow wind and round      rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 23103:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 3,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23103 fu keisan anko takyao rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 23104:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,3},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23104 fu keisan anko 1-9    rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 23105:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,3,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23105 fu keisan anko 1-9    rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va23I~
    case 231052:                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va23I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  2,0,0, 0,0} };                         //7 //~va23I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,1/*ronNumber*/);         //~va23I~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-231052 not pinfu by pllow wgr rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~va11I~
    case 23106:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,3,  0,0,0, 0,0} };                         //7 //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,3/*type*/,3/*number*/,3/*ctr*/,0            ),//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23106 fu keisan minko ji rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 23107:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va23R~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{3,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,2/*type*/,0/*number*/,3/*ctr*/,0            ),//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23107 fu keisan 19 rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
    case 23108:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11R~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,3,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,2/*type*/,1/*number*/,3/*ctr*/,0            ),//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23108 fu keisan minko  rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
    case 23109:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,4,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,2/*type*/,1/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23109 fu keisan minkan 2-8rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
    case 231091:                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11R~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 3,0,0},                                 //~va11R~
        	{2,4,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,0            ),//~va11R~
				new Pair(PT_NUMSAME,2/*type*/,1/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-231091 fu keisan ankan not allhand 2-8rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
    case 231092:                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{4,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,2/*type*/,0/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-231092 fu keisan ankan 19 rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
    case 231093:                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,4,  0,0,0, 0,0} };                         //7 //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,3/*type*/,3/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-231093 fu keisan ankan ji rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 231094:                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,4,  0,0,0, 0,0} };                         //7 //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,3/*type*/,3/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-231094 fu keisan minkan ji rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 231095:                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,4},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,2/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-231095 fu keisan minkan 19 rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 231096:                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,4,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-231096 fu keisan minkan 28 rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 232:                                                      //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-232 3shiki pinfu  rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 23201:                                                    //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~0A17I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A17I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~0A17I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A17I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSEQ ,0/*type*/,1/*number*/,3/*ctr*/,0            ),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23201 3shiki naki rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********
    case 23202:
        dupCtr=new int[][]{
        	{0,0,0, 0,0,0, 0,3,0},
        	{0,0,0, 0,0,3, 0,2,0},
        	{0,0,0, 0,0,0, 0,0,0},
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7
        dupCtrAll=new int[][]{
        	{0,0,0, 0,0,0, 3,3,0},
        	{0,0,0, 0,0,3, 3,2,0},
        	{0,0,0, 0,0,0, 0,0,0},
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7
        pairEarth=new Pair[]{
				new Pair(PT_NUMSAME,0/*type*/,6/*number*/,3/*ctr*/,0            ),
    			new Pair(PT_NUMSAME,1/*type*/,6/*number*/,3/*ctr*/,0            ),
        };
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,7/*ronNumber*/,0/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23202  40fu? rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********
    case 23203:
        dupCtr=new int[][]{
        	{0,1,1, 1,1,2, 2,1,0},
        	{0,0,0, 0,0,0, 0,0,0},
        	{0,0,0, 0,0,0, 0,0,0},
    	    { 3,0,0,2,  0,0,0, 0,0} };                         //7
	    rc=ronTestSub(dupCtr,0/*ronType*/,4/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23203 fu? rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2321:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,0/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2321 pinfu         rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2322:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,2/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2322 not pinfu  kanchan       rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2323:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,2/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2323 not pinfu  penchan 3     rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2324:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,1/*ronType*/,6/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2324 not pinfu  penchan 7     rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2325:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,0/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2325     pinfu  side and kanchan   rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2326:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2326     pinfu  side and kanchan   rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2327:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,2/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2327     pinfu  side and kanchan   rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2328:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0/*ronType*/,3/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2328     pinfu  side and kanchan   rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 233:                                                      //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,2,2, 2,2,2, 2,2,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-233 daisharin pin rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2330:                                                     //~va11I~//~0A16R~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,2,2, 1,1,2, 2,1,0},                                 //~va11I~//~0A16R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~//~0A16R~
	    rc=ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2330 pinfu honitsu rc="+rc.toString());//~va11R~//~0A16R~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A20I~
    case 233001:                                                   //~0A20I~
        dupCtr=new int[][]{                                        //~0A20I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~0A20I~
        	{0,1,1, 2,2,2, 1,2,0},                                 //~0A20I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A20I~
	    rc=ronTestSub(dupCtr,1/*ronType*/,4/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-233001 said daisharin rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 23301:                                                    //~0A17I~//~0A20R~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,1,1, 1,1,2, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{1,2,2, 1,1,2, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSEQ ,1/*type*/,0/*number*/,3/*ctr*/,0            ),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23301 honitsu naki rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A16I~
    case 2331:                                                     //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{1,2,2, 1,1,2, 2,1,2},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A16I~
	    rc=ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2331 pinfu chinitsu rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 23311:                                                    //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{1,1,1, 0,1,2, 2,1,2},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{1,2,2, 1,1,2, 2,1,2},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,0            ),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23311 naki  chinitsu rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2332:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,2,2, 1,1,2, 2,1,2},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/,false/*Allhand**/);//~va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2332 kui chinitsu rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 234:                                                      //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,2,2, 2,0,1, 1,1,0},                                 //~va11I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~va11I~
	    rc=ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-234 iipeiko pinf rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
    case 23401:                                                    //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,2,2, 2,0,1, 1,1,0},                                 //~0A17I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSEQ,1/*type*/,5/*number*/,0/*ctr*/,0            ),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23401 iipeiko naki rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2341:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~va11I~
	    rc=ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2341 2peiko pinf rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 23411:                                                    //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,0            ),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
	    rc=ronTestSub(dupCtr,2/*ronType*/,3/*ronNumber*/);         //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23411 2peiko naki err rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 23412:                                                    //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSEQ ,1/*type*/,1/*number*/,3/*ctr*/,0            ),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23412 2peiko naki tanyao only rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2342:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~va11I~
	    rc=ronTestSub(dupCtr,1/*ronType*/,2/*ronNumber*/,false/*allInHand*/);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2342 2peiko pinf rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 235:                                                      //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,2,2, 2,0,1, 1,1,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va23R~
        	{0,2,2, 2,0,1, 1,1,0},                                 //~va23R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23R~
    	    {0,0,0,0,  0,0,0, 0,0} };                              //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSEQ,2/*type*/,1/*number*/,0/*ctr*/,0            ),//~va11I~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-235 iipeiko naki rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 4:                                                        //~va11I~
        dupCtr=new int[][]{                                        //~9C12R~//~va11R~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{2,2,4, 2,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-4 4seq  rc="+rc);//~9C12I~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 5:                                                        //~va11I~
        dupCtr=new int[][]{                                        //~9C12R~//~va11R~
        	{1,1,3, 4,3,1, 1,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-5 4seq-3  rc="+rc);//~9C12I~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 6:                                                        //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{2,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-6 kokusi 13All  13wait rc="+rc);//~9C12I~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 7:                                                        //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{2,0,0, 0,0,0, 0,0,1},                                 //~9C12I~//~va11R~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,8);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-7 13All not wait13  rc="+rc);//~9C12I~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 8:                                                        //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,1,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-8 13All err2  rc="+rc);//~9C12I~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 9:                                                        //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~//~va11R~//~0A16R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~//~0A16R~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~//~va11R~//~0A16R~
    	    { 3,0,0,0,  0,0,3, 0,0} };                         //7 //~9C12I~//~va11R~//~0A16R~
        dupCtrAll=new int[][]{                                     //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
    	    {3,0,0,0,  3,4,3, 0,0} };                         //7  //~0A16I~
        pairEarth=new Pair[]{                                      //~0A16I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,0            ),//~0A16I~
				new Pair(PT_NUMSAME,3/*type*/,5/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~0A16I~
        };                                                         //~0A16I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-9 3dragon earth rc="+rc);//~9C12I~//~va11R~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A16I~
    case 22901:                                                    //~0A16I~
                                                                   //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16R~
        	{0,0,0, 0,0,3, 0,0,0},                                 //~0A16R~
    	    { 3,0,0,0,  3,3,2, 0,0} };                         //7 //~0A16I~
	    rc=ronTestSub(dupCtr,3,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22901 little 3dragon shosangen rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 229011:                                                   //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,3, 0,0,0},                                 //~0A17I~
    	    { 3,0,0,0,  0,3,2, 0,0} };                         //7 //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,3, 0,0,0},                                 //~0A17I~
    	    { 3,0,0,0,  4,3,2, 0,0} };                         //7 //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-229011 little 3dragon shosangen naki rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A16I~
    case 22902:                                                    //~0A16I~
                                                                   //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~0A16I~
    	    { 0,2,0,0,  3,3,0, 0,0} };                         //7 //~0A16I~
	    rc=ronTestSub(dupCtr,3,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22902 honor tile               rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 10:                                                       //~va11I~
	    set1stTake();                                              //~va23I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-10 13NoPair       rc="+rc);//~9C12I~//~0B02R~
        reset1stTake();                                            //~va23I~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 11:                                                       //~va11I~
	    set1stTake();                                              //~va23I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{2,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-11 13NoPair       rc="+rc);//~9C12R~//~0B02R~
        reset1stTake();                                            //~va23I~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 12:                                                       //~va11I~
	    set1stTake();                                              //~va23I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,2,  1,0,1, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-12 13NoPair       rc="+rc);//~9C12R~//~0B02R~
        reset1stTake();                                            //~va23I~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 13:                                                       //~va11I~
	    set1stTake();                                              //~va23I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-13 13NoPair err1  rc="+rc);//~9C12R~//~0B02R~
        reset1stTake();                                            //~va23I~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 14:                                                       //~va11I~
	    set1stTake();                                              //~va23I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,1, 0,0,0, 0,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-14 13NoPair err2  rc="+rc);//~9C12R~//~0B02R~
        reset1stTake();                                            //~va23I~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 15:                                                       //~va11I~
	    set1stTake();                                              //~va23I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 0,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  1,1,1, 0,0} };                         //7 //~9C12I~
//		ctrTileAll=11;                                             //~9C12I~//~va11R~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
//		ctrTileAll=HANDCTR_TAKEN;                                  //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-15 13NoPair err3  rc="+rc);//~9C12R~//~0B02R~
        reset1stTake();                                            //~va23I~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 16:                                                       //~va11I~
	    set1stTake();                                              //~va23I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,1,1,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-16 14NoPair       rc="+rc);//~9C12R~//~0B02R~
        reset1stTake();                                            //~va23I~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 17:                                                       //~va11I~
	    set1stTake();                                              //~va23I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,2,0,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-17 14NoPair err1  rc="+rc);//~9C12I~//~0B02R~
        reset1stTake();                                            //~va23I~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 18:                                                       //~va11I~
	    set1stTake();                                              //~va23I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,1, 0,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,1,1,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-18 14NoPair err2  rc="+rc);//~9C12I~//~0B02R~
        reset1stTake();                                            //~va23I~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 281:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,3, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 3,3,3,2,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0,2);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-281 little 4wind  rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 28101:                                                    //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~0A16I~
    	    {2,0,0,0,  0,0,0, 0,0} };                         //7  //~0A16I~
        dupCtrAll=new int[][]{                                     //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~0A16I~
    	    {2,3,3,4,  0,0,0, 0,0} };                         //7  //~0A16I~
        pairEarth=new Pair[]{                                      //~0A16I~
				new Pair(PT_NUMSAME,3/*type*/,1/*number*/,3/*ctr*/,0            ),//~0A16I~
				new Pair(PT_NUMSAME,3/*type*/,2/*number*/,3/*ctr*/,0            ),//~0A16I~
				new Pair(PT_NUMSAME,3/*type*/,3/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~0A16I~
        };                                                         //~0A16I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-28101 4wind small naki rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A16I~
    case 28102:                                                    //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~0A16I~
    	    {3,0,0,0,  0,0,0, 0,0} };                         //7  //~0A16I~
        dupCtrAll=new int[][]{                                     //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~0A16I~
    	    {3,3,3,4,  0,0,0, 0,0} };                         //7  //~0A16I~
        pairEarth=new Pair[]{                                      //~0A16I~
				new Pair(PT_NUMSAME,3/*type*/,1/*number*/,3/*ctr*/,0            ),//~0A16I~
				new Pair(PT_NUMSAME,3/*type*/,2/*number*/,3/*ctr*/,0            ),//~0A16I~
				new Pair(PT_NUMSAME,3/*type*/,3/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~0A16I~
        };                                                         //~0A16I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-28102 4wind big naki rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A16I~
    case 282:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 3,3,3,3,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=ronTestSub(dupCtr,0,2);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-282 big    4wind  rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 283:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,3,3, 3,0,3, 0,0,0},                                 //~va11R~
    	    {0,0,0,0,  0,2,0, 0,0} };                         //7  //~va11R~
	    rc=ronTestSub(dupCtr,2,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-283 all green Dragon pillow   rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 28301:                                                    //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,3,0, 0,0,3, 0,0,0},                                 //~0A16I~
    	    {0,0,0,0,  0,2,0, 0,0} };                         //7  //~0A16I~
        dupCtrAll=new int[][]{                                     //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,3,3, 4,0,3, 0,0,0},                                 //~0A16I~
    	    {0,0,0,0,  0,2,0, 0,0} };                         //7  //~0A16I~
        pairEarth=new Pair[]{                                      //~0A16I~
				new Pair(PT_NUMSAME,2/*type*/,2/*number*/,3/*ctr*/,0            ),//~0A16I~
				new Pair(PT_NUMSAME,2/*type*/,3/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~0A16I~
        };                                                         //~0A16I~
	    rc=ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-28301 allGreen  naki rc="+rc);//~va23R~
//*********                                                        //~0A16I~
    case 284:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,3,3, 3,0,0, 0,2,0},                                 //~va11I~//~0A16R~
    	    { 0,0,0,0,  0,3,0, 0,0} };                         //7 //~va11I~//~0A16R~
	    rc=ronTestSub(dupCtr,2,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-284 all green Dragon tripret  rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 285:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,3,3, 3,0,3, 0,2,0},                                 //~va11I~//~0A16R~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~//~0A16R~
	    rc=ronTestSub(dupCtr,2,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-285 all green no Dragon rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 286:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va11I~
        	{3,0,0, 0,0,0, 0,0,2},                                 //~va11I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-286 chinrou              rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A16I~
    case 28601:                                                    //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,2},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~0A16I~
        dupCtrAll=new int[][]{                                     //~0A16I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~0A16I~
        	{3,0,0, 0,0,0, 0,0,2},                                 //~0A16I~
        	{4,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~0A16I~
        pairEarth=new Pair[]{                                      //~0A16I~
				new Pair(PT_NUMSAME,1/*type*/,0/*number*/,3/*ctr*/,0            ),//~0A16I~
				new Pair(PT_NUMSAME,2/*type*/,0/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~0A16I~
        };                                                         //~0A16I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A16I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-28601 chirou  naki rc="+rc);//~0A16I~//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2861:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va11I~
        	{3,0,0, 0,0,0, 0,0,2},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va11I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2861 honro      rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 28611:                                                    //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 0,0,0, 0,0,3},                                 //~0A17I~
        	{3,0,0, 0,0,0, 0,0,2},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{4,0,0, 0,0,0, 0,0,3},                                 //~0A17I~
        	{3,0,0, 0,0,0, 0,0,2},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSAME,0/*type*/,0/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-28611 honro naki     rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 287:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,0,0, 0,0,0, 0,0,4},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,4},                                 //~va11I~
        	{4,0,0, 0,0,0, 0,0,4},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,0/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11I~
				new Pair(PT_NUMSAME,1/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11I~
				new Pair(PT_NUMSAME,2/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11I~
				new Pair(PT_NUMSAME,3/*type*/,0/*number*/,4/*ctr*/,TDF_KAN_RIVER)//~va11I~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-287 4kan  chinro      rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2870:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{2,0,0, 0,0,0, 2,0,4},                                 //~va11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{2,0,0, 0,0,0, 2,0,4},                                 //~va23R~
        	{4,0,0, 0,0,0, 0,0,4},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,2/*type*/,0/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11I~
				new Pair(PT_NUMSAME,2/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11I~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2870 4kan err          rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2871:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,0,0, 0,0,0, 0,0,4},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,4},                                 //~va11R~
        	{0,4,0, 0,0,0, 0,0,4},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,0/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11I~
				new Pair(PT_NUMSAME,1/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11I~
				new Pair(PT_NUMSAME,2/*type*/,1/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11R~
				new Pair(PT_NUMSAME,2/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11M~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2871 4kan not chinro rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2872:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,0,0, 0,0,0, 0,0,3},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,4},                                 //~va11R~
        	{0,4,0, 0,0,0, 0,0,4},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,0/*type*/,8/*number*/,3/*ctr*/,0            ),//~va11R~
				new Pair(PT_NUMSAME,1/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11I~
				new Pair(PT_NUMSAME,2/*type*/,1/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11R~
				new Pair(PT_NUMSAME,2/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_TAKEN)//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2872 3anko by 3kan rc="+rc);//~va11I~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A16I~
    case 28721:                                                    //~0A16I~
                                                                   //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~0A16I~
        	{2,0,0, 0,0,0, 0,0,3},                                 //~0A16I~
        	{0,0,0, 1,1,1, 0,0,3},                                 //~0A16I~//~0A17R~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~0A16I~
	    rc=ronTestSub(dupCtr,1,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-28721 3anko rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A16I~
    case 28722:                                                    //~0A16I~
                                                                   //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~0A16I~//~0A17R~
        	{2,0,0, 0,0,0, 0,3,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~0A16I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~0A16I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{0,0,0, 1,1,1, 0,4,0},                                 //~0A17R~
        	{2,0,0, 0,0,0, 0,3,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~0A17I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_RIVER)//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-28722 3tonko naki rc="+rc);//~0A16I~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 28723:                                                    //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{2,0,0, 0,0,0, 0,3,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~0A17I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{1,1,1, 0,0,0, 0,4,0},                                 //~0A17I~
        	{2,0,0, 0,0,0, 0,3,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~0A17I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSEQ ,0/*type*/,0/*number*/,3/*ctr*/,0            ),//~0A17I~
				new Pair(PT_NUMSAME,0/*type*/,7/*number*/,4/*ctr*/,TDF_KAN_TAKEN)//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-28723 3anko naki naki rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
    case 2873:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,0,0, 0,0,0, 0,0,3},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,4},                                 //~va11R~
        	{0,4,0, 0,0,0, 0,0,4},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,0/*type*/,8/*number*/,3/*ctr*/,0            ),//~va11R~
				new Pair(PT_NUMSAME,1/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11I~
				new Pair(PT_NUMSAME,2/*type*/,1/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11R~
				new Pair(PT_NUMSAME,2/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_TAKEN)//~va11R~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2873 3kan rc="+rc);//~va11I~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
    case 28731:                                                    //~0A16I~
                                                                   //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
    	    {2,0,0,0,  0,0,0, 0,0} };                         //7  //~0A16I~
        dupCtrAll=new int[][]{                                     //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
    	    {2,3,3,0,  3,0,4, 0,0} };                         //7  //~0A16I~//~0A17R~
        pairEarth=new Pair[]{                                      //~0A16I~
				new Pair(PT_NUMSAME,3/*type*/,1/*number*/,3/*ctr*/,0            ),//~0A16I~
				new Pair(PT_NUMSAME,3/*type*/,2/*number*/,3/*ctr*/,0            ),//~0A16I~//~0A17R~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,3/*ctr*/,0            ),//~0A16I~//~0A17R~
				new Pair(PT_NUMSAME,3/*type*/,6/*number*/,4/*ctr*/,TDF_KAN_TAKEN)//~0A16I~//~0A17R~
        };                                                         //~0A16I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-28731 tsuiiso naki rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2874:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,0,0, 0,0,0, 0,0,4},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,4},                                 //~va11I~
        	{0,4,0, 0,0,0, 0,0,4},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,0/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11I~
				new Pair(PT_NUMSAME,1/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11I~
				new Pair(PT_NUMSAME,2/*type*/,1/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11I~
				new Pair(PT_NUMSAME,2/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_TAKEN)//~va11I~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,4/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~va11I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2874 4kan 4anko tanki rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2875:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~va11R~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {4,4,4,4,  2,0,0, 0,0} };                         //7  //~va11R~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,3/*type*/,1/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11I~
				new Pair(PT_NUMSAME,3/*type*/,2/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11I~
				new Pair(PT_NUMSAME,3/*type*/,3/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~va11I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,4/*ctr*/,TDF_KAN_TAKEN)//~va11I~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,4/*ctrAnkan*/,true/*swAllHand*/,pairEarth);//~va11R~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2875 4kan 4ankoTanki big4wind tuiso tenho rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A20I~
    case 2876:                                                     //~0A20I~
        dupCtr=new int[][]{                                        //~0A20I~
        	{3,3,3, 0,0,0, 0,0,0},                                 //~0A20I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~0A20I~
        dupCtrAll=new int[][]{                                     //~0A20I~
        	{3,3,3, 0,0,0, 0,0,0},                                 //~0A20I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~0A20I~
        pairEarth=new Pair[]{                                      //~0A20I~
				new Pair(PT_NUMSAME,1/*type*/,0/*number*/,3/*ctr*/,0),//~0A20R~//~0A21R~
        };                                                         //~0A20I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,0/*ctrAnkan*/,false/*swAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2876 not 4anko  rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A20I~
    case 2877:                                                     //~0A20I~
        dupCtr=new int[][]{                                        //~0A20I~
        	{3,3,3, 0,0,0, 0,0,0},                                 //~0A20I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~0A20I~
        dupCtrAll=new int[][]{                                     //~0A20I~
        	{3,3,3, 0,0,0, 0,0,0},                                 //~0A20I~
        	{4,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
    	    {0,0,0,0,  2,0,0, 0,0} };                         //7  //~0A20I~
        pairEarth=new Pair[]{                                      //~0A20I~
				new Pair(PT_NUMSAME,1/*type*/,0/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~0A20I~//~0A21R~
        };                                                         //~0A20I~
	    rc=ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true/*swAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2877 1ankan 4anko  rc="+rc);//~0A20I~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A20I~
    case 2878:                                                     //~0A20I~
        dupCtr=new int[][]{                                        //~0A20I~
        	{3,3,3, 0,0,0, 0,0,0},                                 //~0A20I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~0A20I~//~0A21R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
    	    {0,0,0,2,  0,0,0, 0,0} };                         //7  //~0A20I~//~0A21R~
	    rc=ronTestSub(dupCtr,1,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2878 shabo ron not 4anko  rc="+rc);//~0A20I~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 288:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{3,2,1, 1,1,1, 1,1,3},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-288 9gate wait9  rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 289:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{3,2,1, 1,1,1, 1,1,3},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-289 9gate not wait9  rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 290:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
        	{3,2,1, 1,1,1, 1,1,3},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
	    rc=ronTestSub(dupCtr,1,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-290 9gate not man  rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
//    case 19:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{        //34567    2.5.8  sou           //~9C12I~//~va23R~
//            {0,1,1, 1,1,1, 1,0,0},                               //~va23R~
//            {2,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-19 1              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 20:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,0,1, 1,2,1, 1,0,0},                               //~va23R~
//            {2,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-20              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
//*********                                                        //~va11I~
    case 220:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,3,0, 1,1,1, 3,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
	    rc=ronTestSub(dupCtr,1,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-220 tanyao only        rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 221:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,3,0, 0,2,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,3,0, 3,0,0, 3,0,0},                                 //~va11I~
        	{0,3,0, 0,2,0, 0,0,0},                                 //~va23R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,0/*type*/,1/*number*/,3/*ctr*/,0),//~va11I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,3/*ctr*/,0),//~va11I~
				new Pair(PT_NUMSAME,0/*type*/,6/*number*/,3/*ctr*/,0),//~va11I~
        };                                                         //~va11I~
	    rc=ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-221  kuitan toitoi     rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 2211:                                                       //~va11I~//~0A12R~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{2,0,0, 1,1,1, 1,1,1},  //dora 9man  up                //~9C12I~//~0A12R~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~9C12I~//~0A12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 3,0,0,0,  0,0,0, 0,0} };                             //~0A12R~
	    rc=ronTestSub(dupCtr,3,0,false);                                       //~9C12I~//~va11R~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2211 dora chk 9man up  rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~9C12I~//~va11R~//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 22112:                                                    //~va23I~
    //***************                                              //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{2,1,1, 1,0,1, 1,1,0},  //dora 1man  down              //~0A12I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 3,0,0,0,  0,0,0, 0,0} };                             //~0A12I~
	    rc=ronTestSub(dupCtr,3,0,false);                           //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22112 dora chk 1man down  rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 22113:                                                    //~va23I~
    //***************                                              //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},   //dora 9pin kan up            //~0A12I~
        	{2,0,0, 1,1,1, 1,1,1},                                 //~0A12I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 3,0,0,0,  0,0,0, 0,0} };                             //~0A12I~
	    rc=ronTestSub(dupCtr,3,0,false);                           //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22113 dora chk 9pin kan up   rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 22114:                                                    //~va23I~
    //***************                                              //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},   //dora 9pin kan down          //~0A12I~
        	{2,0,0, 1,1,1, 1,1,1},                                 //~0A12I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 3,0,0,2,  0,0,0, 0,0} };                             //~0A12I~
	    rc=ronTestSub(dupCtr,3,0,false);                           //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22114 dora chk pei  kan down rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 22115:                                                    //~va23I~
    //***************                                              //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},   //red                         //~0A12I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 3,0,0,0,  3,0,2, 0,0} };                         //7 //~0A12I~
	    rc=ronTestSub(dupCtr,3,0,false);                           //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22115 Rof WGR            rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 22116:                                                    //~va23I~
    //***************                                              //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},   //red                         //~0A12I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 3,0,0,0,  3,0,2, 0,0} };                         //7 //~0A12I~
	    rc=ronTestSub(dupCtr,3,0,false);                           //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22116 Rof WGR next       rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~va23R~
    //****************                                             //~0A12I~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 22:                                                       //~va11I~
        dupCtr=new int[][]{                 //2345 pin  2.5        //~9C12I~//~va11R~
        	{0,0,2, 1,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22              rc="+rc);//~9C12I~//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A16I~
    case 22201:          //no 1 han   dora                         //~0A16I~//~0A24R~
                                                                   //~0A16I~
        dupCtr=new int[][]{                 //2345 pin  2.5        //~0A16I~
        	{1,1,1, 1,1,1, 0,0,0},                                 //~0A16I~//~0A24R~
        	{3,0,0, 0,0,0, 1,1,1},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~//~0A24R~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~0A16I~//~0A24R~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22201 0han hand    rc="+rc);//~0A16R~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A24I~
    case 222011:          // 1 han white                           //~0A24I~
                                                                   //~0A24I~
        dupCtr=new int[][]{                                        //~0A24I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~0A24I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~0A24I~
        	{2,0,0, 1,1,1, 0,0,0},                                 //~0A24I~
    	    { 0,0,0,0,  3,0,0, 0,0} };                         //7 //~0A24I~
	    rc=ronTestSub(dupCtr,3,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-222011 1han white    rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A24I~
    case 222012:          // 1 han white                           //~0A24I~
                                                                   //~0A24I~
        dupCtr=new int[][]{                                        //~0A24I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~0A24I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~0A24I~
        	{2,0,0, 1,1,1, 0,0,0},                                 //~0A24I~
    	    { 3,0,0,0,  0,0,0, 0,0} };                         //7 //~0A24I~
	    rc=ronTestSub(dupCtr,3,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-222012 1han white    rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 22202:          //no 1 han                                //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{                 //2345 pin  2.5        //~0A17I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{3,0,0, 0,0,0, 1,1,1},                                 //~0A17I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        dupCtrAll=new int[][]{                 //2345 pin  2.5     //~0A17I~
        	{2,0,0, 1,1,1, 0,0,0},                                 //~0A17I~
        	{3,0,0, 0,0,0, 1,1,1},                                 //~0A17I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSEQ,1/*type*/,0/*number*/,3/*ctr*/,0),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-22202 0han naki    rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
////*********                                                      //~va23R~
//    case 23:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,0,1, 1,1,2, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-23              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 24:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                   //2345678 pin 2.5.8  //~9C12I~//~va23R~
//            {0,2,1, 1,1,1, 1,1,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-24              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 25:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,1,1, 1,2,1, 1,1,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-25              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 26:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,1,1, 1,1,1, 1,2,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-26              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 27:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{              //67888 gg    5.8.g       //~9C12I~//~va23R~
//            {2,0,0, 0,1,1, 1,3,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-27              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 28:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {2,0,0, 0,0,1, 1,4,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-28              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 29:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {3,0,0, 0,0,1, 1,3,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-29              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 30:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                     //5666 zou 4.5.6   //~9C12I~//~va23R~
//            {0,0,0, 1,1,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-30              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 31:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,0,0, 0,2,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-31              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 32:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,0,0, 0,1,3, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-32              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 33:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                           //4666 sou 4.5.6//~9C12I~//~va23R~
//            {0,0,0, 2,0,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 34:                                                     //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-33             rc="+rc);//~9C12I~//~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,0,0, 1,1,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-34             rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 35:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                     //2345666 pin 1.2.4.5.7//~9C12I~//~va23R~
//            {1,1,1, 1,1,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-35              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 36:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,2,1, 1,1,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-36              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 37:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,1,1, 2,1,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-37               rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 38:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,1,1, 1,2,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-38             rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 39:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,1,1, 1,1,3, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-39             rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 40:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                   //2345777 pin 2.5.6  //~9C12I~//~va23R~
//            {0,2,1, 1,1,0, 3,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-40             rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 41:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,1,1, 1,2,0, 3,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-41              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 42:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,1,1, 1,1,1, 3,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-42              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 422:                                                    //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                         //2333456 pin   1.2.4.7//~9C12I~//~va23R~
//            {1,1,3, 1,1,1, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-422             rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 43:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,2,3, 1,1,1, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-43              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 44:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,1,3, 2,1,1, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-44              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 45:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,1,3, 1,1,1, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-45               rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 46:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                //3334568 pin 7.8       //~9C12I~//~va23R~
//            {0,0,3, 1,1,1, 1,1,0},                               //~va23R~
//            {0,1,1, 1,1,1, 1,0,0},                                 //~9C12I~//~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,2);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-46               rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 2460:                                                   //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                //3334568 pin 7.8     //~va23R~
//            {0,0,1, 1,1,1, 1,1,0},                               //~va23R~
//            {0,1,1, 1,1,1, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 2,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,2);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2460              rc="+rc);//~va11I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 2461:                                                   //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                //3334568 pin 7.8     //~va23R~
//            {0,0,3, 1,1,1, 1,1,0},                               //~va23R~
//            {0,1,1, 1,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 1,1,1, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,3);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2461              rc="+rc);//~va11I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 2462:                                                   //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                //3334568 pin 7.8     //~va23R~
//            {0,0,3, 1,1,1, 1,1,0},                               //~va23R~
//            {0,1,1, 1,0,0, 0,0,0},                               //~va23R~
//            {0,1,1, 1,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,4);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2462              rc="+rc);//~va11I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 2463:                                                   //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                //3334568 pin 7.8     //~va23R~
//            {0,0,3, 1,1,1, 1,1,0},                               //~va23R~
//            {0,1,1, 1,1,1, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,5);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2463              rc="+rc);//~va11I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 2464:                                                   //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                //3334568 pin 7.8     //~va23R~
//            {0,0,3, 1,1,1, 1,1,0},                               //~va23R~
//            {0,1,1, 1,1,1, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,6);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2464              rc="+rc);//~va11I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 2465:                                                   //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                //3334568 pin 7.8     //~va23R~
//            {0,0,3, 1,1,1, 1,1,0},                               //~va23R~
//            {0,1,1, 1,0,1, 1,1,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,7);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2465              rc="+rc);//~va11I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 47:                                                     //~va23R~
//        dupCtr=new int[][]{                                        //~9C12I~//~va23R~
//            {0,0,3, 1,1,1, 0,0,0},                                 //~9C12I~//~va23R~
//            {0,0,0, 0,0,0, 0,2,0},                                 //~9C12I~//~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-47              rc="+rc);//~9C12I~//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
//*********                                                        //~0A16M~
    case 24701:     //258                                          //~0A16I~
                                                                   //~0A16M~
        dupCtr=new int[][]{                                        //~0A16M~
        	{1,1,1, 1,1,1, 1,1,1},        //pinfu                  //~0A16M~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A16M~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A16M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A16M~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-24701 ittsu       rc="+rc.toString());//~0B02R~
                                         //~0A16M~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 24702:     //258                                          //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 1,1,1, 1,1,1},        //pinfu                  //~0A17I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        dupCtrAll=new int[][]{                                     //~0A17I~
        	{1,1,1, 1,1,1, 1,1,1},        //pinfu                  //~0A17I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
        pairEarth=new Pair[]{                                      //~0A17I~
				new Pair(PT_NUMSEQ,0/*type*/,0/*number*/,3/*ctr*/,0),//~0A17I~
        };                                                         //~0A17I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-24702 ittsu  naki rc="+rc.toString());//~0B02R~
                                                                   //~0A17I~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 48:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~//~va11R~
        	{0,1,1, 1,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 3,3,0},                                 //~9C12I~//~va11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,5);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-48               rc="+rc);//~9C12I~//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    case 49:                                                       //~va11I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~//~va11R~
        	{0,0,1, 2,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{3,3,0, 0,0,0, 0,0,0},                                 //~9C12I~//~va11R~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=ronTestSub(dupCtr,0,4);                                       //~9C12I~//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-49                rc="+rc);//~9C12I~//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~va11I~
    default:  //value1                                             //~va23R~
        if (rc==null)                                              //~va23I~
			rc=ronTestValue2(testCase);                            //~va23R~
        else                                                       //~va23I~
			rc=ronTestValue2(0);                                   //~va23I~
    }                                                              //~va11I~
//        if ((TestOption.option2 & TestOption.TO2_CHKRANK)==0)    //~va23R~
//            UView.showToastLong("testCase="+testCase);                 //~va11I~//~0A11R~//~va23R~
        return rc;                                                           //~9C12I~
    }                                                              //~9C12I~
	//*************************************************************************//~0A17I~
	private RonResult ronTestValue2(int PtestCase)                               //~0A11I~//~va23R~
	{                                                              //~0A11I~
    	testCase=PtestCase;                                        //~va23R~
	//*************************************************************************//~0A11I~
	    RonResult rc=null;                                         //~0A11I~
//      boolean swTestAll=false;                                   //~va23R~
//      if (swTestAll)                                             //~va23R~
//          testCase=0;                                            //~va23R~
//*********                                                        //~0A11I~
    switch(testCase)                                               //~0A11I~
    {                                                              //~0A11I~
    case 0:                                                        //~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~0A11I~
    case 255011:     //258                                         //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},        //pinf                   //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,0,2, 1,1,1, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255011  wait3  rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A11I~
    case 255012:     //258                                         //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,2,1, 1,0,0},        //pinfu                  //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,0,2, 1,1,1, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255012  wait3       rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255013:       //258                                       //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,1,1, 1,1,0},        //pinfu                  //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,0,2, 1,1,1, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,7);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255013  wait3       rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255021:          //25                                     //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,1, 1,1,0, 0,0,0},    //32                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255021 nobetan   rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255022:            //25                                   //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,0, 0,0,0},       //32                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255022 nobetan   rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255031:            //258                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,1, 1,1,1, 1,1,0},     //32                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255031 nobetan3  rc="+rc.toString());//~0B02R~
        if (!swTestAll)        break;                              //~va23R~
//*********                                                        //~0A11I~
    case 255032:           //258                                   //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                //32                    //~0A11R~
        	{0,1,1, 1,2,1, 1,1,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255032 nobetan3  rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255033:           //258                                   //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,1,1, 1,2,0},              //32               //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,7);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255033 nobetan3  rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~va23I~
    case 2550332:          //258                                   //~va23I~
                                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,1,1, 3,0,0},              //32               //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
	    rc=ronTestSub(dupCtr,0,6);                                 //~va23I~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2550332 nobetan3  rc="+rc.toString());//~va23I~
        if (!swTestAll)         break;                             //~va23I~
//*********                                                        //~0A11I~
    case 255041:                                                   //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{     //0-4 1-2                          //~0A11R~
        	{0,1,1, 4,0,0, 0,0,0},       //34                      //~0A11R~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255041 2shabo    rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255042:                //0-4 1-2                          //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 3,0,0, 0,0,0},                                 //~0A11I~
        	{0,3,0, 0,0,0, 0,0,0},          //32                   //~0A11R~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,1,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255042 2shabo    rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~va23I~
    case 2550421:                //0-4 1-2                         //~va23I~
                                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},          //32                   //~va23I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~va23I~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2550421 2shabo    rc="+rc.toString());//~va23R~
        if (!swTestAll)         break;                             //~va23I~
//*********                                                        //~va23I~
    case 255043:                //0-4 1-2                          //~va23I~
                                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 3,0,0, 0,0,0},                                 //~va23I~
        	{0,1,1, 1,0,0, 0,0,0},          //32                   //~va23I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255043 pinf of pillow tanki  rc="+rc.toString());//~va23I~
        if (!swTestAll)         break;                             //~va23I~
//*********                                                        //~va23I~
    case 255044:                                                   //~va23I~
                                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 4,0,0, 0,0,0},                                 //~va23I~
        	{0,2,0, 0,0,0, 0,0,0},          //32                   //~va23I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255044 shabo anko or seq     rc="+rc.toString());//~va23I~
        if (!swTestAll)         break;                             //~va23I~
//*********                                                        //~0A11I~
    case 255051:         //124                                     //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{1,1,3, 0,0,0, 0,0,0},      //pinfu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11R~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255051 13        rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255052:            //124                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,3, 0,0,0, 0,0,0},           //36                  //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11R~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255052 13        rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255053:            //124                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,3, 1,0,0, 0,0,0},              //pinfu            //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11R~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255053 13        rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255061:       //23                                        //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,0, 3,0,0, 0,0,0},    //36                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255061 1-3       rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255062:           //23                                    //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 3,0,0, 0,0,0},      //32                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,2);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255062 1-3       rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255071:     //12457                                       //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,1, 1,1,3, 0,0,0},     //2  36                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255071 11113     rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255072:            //12457                                //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,3, 0,0,0},      //5   36                   //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255072 11113     rc="+rc.toString());//~0B02R~
        if (!swTestAll)         break;                             //~va23R~
//*********                                                        //~0A11I~
    case 255073:             //12457                               //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{1,1,1, 1,1,3, 0,0,0},        //1   pinfu              //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255073 11113     rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255074:             //12457           pinfu               //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                //4                     //~0A11R~
        	{0,1,1, 2,1,3, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255074 11113     rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255075:             //12457                               //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                  //7  pinfu            //~0A11R~
        	{0,1,1, 1,1,3, 1,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,6);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255075 11113     rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255081:            //256                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,1, 1,1,0, 3,0,0},    //36                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255081 111103    rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255082:             //256                                 //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{               //36                     //~0A11R~
        	{0,1,1, 1,2,0, 3,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255082 111103    rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255083:                  //256                            //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,1,1, 3,0,0},            //32                 //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,5);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255083 111103    rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255091:    //1247                                         //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,3, 1,1,1, 0,0,0},        //36                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);        //2                      //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255091 13111     rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255092:     //1247                                        //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{1,1,3, 1,1,1, 0,0,0},            //pinfu              //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,0);           //1                   //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255092 13111     rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255093:           //1247                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,3, 2,1,1, 0,0,0},             //pinfu             //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);          //4                    //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255093 13111     rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255094:           //1247                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,3, 1,1,1, 1,0,0},          //pinfu                //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,6);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255094 13111     rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255101:    //78                                           //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 1,1,1, 1,1,0},     //32                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,6);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255101 311101    rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255102: //78                                              //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 1,1,1, 0,2,0},         //36                    //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,7);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255102 311102    rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255111:     //2457                                        //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,3, 0,0,0},      //34                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255111 1123      rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255112:      //2457                                       //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 2,2,3, 0,0,0},         //pinfu                  //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255112 1123      rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255113:            //2457                                 //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,3,3, 0,0,0},        //36                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255113 1123      rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255114:                  //2457                           //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,2,3, 1,0,0},         //pinfu                 //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,6);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255114 1123      rc="+rc.toString());//~0B02R~
        if (!swTestAll)           break;                           //~va23R~
//*********                                                        //~0A11I~
    case 255121:             //256                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,0, 3,0,0},    //34                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255121 11203     rc="+rc.toString());//~0B02R~
        if (!swTestAll)            break;                          //~va23R~
//*********                                                        //~0A11I~
    case 255122:             //256                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,3,0, 3,0,0},        //36                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255122 11203     rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255123:             //256                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,2,1, 3,0,0},        //32                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,5);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255123 11203     rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255131:             //245                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 2,1,3, 0,0,0},           //pinfu               //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255131 11203     rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255132:             //245                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 3,1,3, 0,0,0},   //36                          //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255132 11203     rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255133:             //245                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 2,2,3, 0,0,0},   //pinfu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255133 11203     rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255141:             //258                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,1, 1,1,0},    //pinfu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255141           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255142:             //258                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                 //pinfu                //~0A11R~
        	{0,0,1, 1,3,1, 1,1,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255142           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255143:             //258                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,2,1, 1,2,0},    //32                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,7);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255143           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255151:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{1,1,1, 1,1,1, 1,1,3},      //pinfu                    //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255151           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255152:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,1, 1,1,1, 1,1,3},     //40                        //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255152           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255153:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 2,1,1, 1,1,3},    //pinfu                      //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255153           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255154:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,1, 1,1,3},      //40                       //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255154           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255155:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,1,1, 2,1,3},        //pinfu                  //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,6);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255155           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255156:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,1,1, 1,2,3},     //40                        //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,7);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255156           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~va23I~
    case 25517:             //124578                               //~va23I~
                                                                   //~va23I~
        dupCtr=new int[][]{                                        //~va23I~
        	{0,1,1, 1,1,1, 2,1,3},        //pinfu                  //~va23I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va23I~
        	{0,3,0, 0,0,0, 0,0,0},                                 //~va23I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va23I~
	    rc=ronTestSub(dupCtr,0,6);                                 //~va23I~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255157           rc="+rc.toString());//~va23I~
        if (!swTestAll)          break;                            //~va23I~
//*********                                                        //~0A11I~
    case 255161:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,4, 3,1,0, 0,0,0},     //34                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,2);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255161           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255162:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 4,1,0, 0,0,0},        //36                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255162           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255163:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 3,2,0, 0,0,0},       //40                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255163           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255164:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 3,1,1, 0,0,0},         //34                    //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,5);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255164           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255171:             //23456                               //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,3, 1,3,0, 0,0,0},    //34fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,1);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255171           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255172:             //23456                               //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11R~
        	{0,0,4, 1,3,0, 0,0,0},    //34fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,2);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255172           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255173:             //23456                               //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 2,3,0, 0,0,0},    //40fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255173           rc="+rc.toString());//~0B02R~
        if (!swTestAll)          break;                            //~va23R~
//*********                                                        //~0A11I~
    case 255174:             //23456                               //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 1,4,0, 0,0,0},      //34fu                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255174           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255175:             //23456                               //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 1,3,1, 0,0,0},    //34fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,5);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255175           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255181:             //456                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 1,1,0, 3,0,0}, //36fu                          //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255181           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255182:             //456                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 0,2,0, 3,0,0}, //40fu                          //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255182           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255183:             //456                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 0,1,1, 3,0,0},     //46fu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,5);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255183           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255191:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,4, 2,2,0, 0,0,0},    //pinfu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,2);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255191           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255192:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 3,2,0, 0,0,0}, //fu=36                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255192           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255193:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 2,3,0, 0,0,0},   //36fu                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255193           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255194:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 2,2,1, 0,0,0},        //pinfu                  //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,5);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255194           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255201:             //345                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 3,2,0, 0,0,0},      //36fu                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,2);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255201           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255202:             //345                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,2, 4,2,0, 0,0,0},     //32fu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255202           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255203:             //345                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,2, 3,3,0, 0,0,0},   //36fu                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255203           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255211:             //3467                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,4,1, 1,0,0},      //pinfu                    //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,2);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255211           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255212:             //3467                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 2,4,1, 1,0,0},     //36fu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255212           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255213:             //3467                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 1,4,2, 1,0,0},     //pinfu                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,5);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255213           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255214:             //3467                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 1,4,1, 2,0,0},   //36fu                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,6);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255214           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255231:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 1,4,2, 1,0,0}, //pinfu                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255231           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255232:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 0,4,3, 1,0,0},    //36fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,5);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255232           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255233:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 0,4,2, 2,0,0},  //pinfu                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,6);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255233           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255241:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 1,4,1, 2,0,0},    //34fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,3);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255241           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255242:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 0,4,2, 2,0,0},  //32fu                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,5);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255242           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255243:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 0,4,1, 3,0,0},    //36fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=ronTestSub(dupCtr,0,6);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255243           rc="+rc.toString());//~0B02R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A12I~
    case 255300:             //6                                   //~0A12I~
                                                                   //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,1,3, 1,0,0},    //32fu                       //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
	    rc=ronTestSub(dupCtr,0,5);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255300         rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 2553002:             //6                                  //~va23I~
    //*************                                                //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,1,4, 1,0,0},    //36fu                       //~0A12I~
        	{0,1,1, 1,0,0, 2,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
	    rc=ronTestSub(dupCtr,0,5);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2553002         rc="+rc.toString());//~va23R~
    //*************                                                //~0A12I~
        if (!swTestAll) break;                                     //~0A12I~
//*********                                                        //~0A12I~
    case 255301:                                                   //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        dupCtrAll=new int[][]{                                     //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,4,2, 0,0,0, 0,0,0},         //30+16                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        pairEarth=new Pair[]{                                      //~0A12I~
				new Pair(PT_NUMSAME,2/*type*/,1/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~0A12I~
        };                                                         //~0A12I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-255301 ankan  2-8   rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 2553012:                                                  //~va23I~
     //*********                                                   //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        dupCtrAll=new int[][]{                                     //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{4,0,2, 0,0,0, 0,0,0},          //30-32                //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        pairEarth=new Pair[]{                                      //~0A12I~
				new Pair(PT_NUMSAME,2/*type*/,0/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~0A12I~
        };                                                         //~0A12I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2553012 ankan  19  rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 2553013:                                                  //~va23I~
     //*********                                                   //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        dupCtrAll=new int[][]{                                     //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,4, 0,0} };          //30+32        //7 //~0A12I~
        pairEarth=new Pair[]{                                      //~0A12I~
				new Pair(PT_NUMSAME,3/*type*/,4/*number*/,4/*ctr*/,TDF_KAN_TAKEN),//~0A12I~
        };                                                         //~0A12I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2553013 ankan  ji  rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 2553014:                                                  //~va23I~
     //*********                                                   //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        dupCtrAll=new int[][]{                                     //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,4,2, 0,0,0, 0,0,0},       //20+8                    //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        pairEarth=new Pair[]{                                      //~0A12I~
				new Pair(PT_NUMSAME,2/*type*/,1/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~0A12I~
        };                                                         //~0A12I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2553014 minkan 2-8   rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 2553015:                                                  //~va23I~
     //*********                                                   //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        dupCtrAll=new int[][]{                                     //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,4},       //20+16                   //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        pairEarth=new Pair[]{                                      //~0A12I~
				new Pair(PT_NUMSAME,2/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~0A12I~
        };                                                         //~0A12I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2553015 minkan 19    rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 2553016:                                                  //~va23I~
     //*********                                                   //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        dupCtrAll=new int[][]{                                     //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},       //20+16                   //~0A12I~
    	    { 0,0,0,4,  0,0,0, 0,0} };                         //7 //~0A12I~
        pairEarth=new Pair[]{                                      //~0A12I~
				new Pair(PT_NUMSAME,3/*type*/,3/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~0A12I~
        };                                                         //~0A12I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2553016 minkan ji    rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 2553017:                                                  //~va23I~
     //*********                                                   //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        dupCtrAll=new int[][]{                                     //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,3,0},       //20+2                    //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        pairEarth=new Pair[]{                                      //~0A12I~
				new Pair(PT_NUMSAME,2/*type*/,7/*number*/,3/*ctr*/,0            ),//~0A12I~
        };                                                         //~0A12I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2553017 pon 2-8      rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 2553018:                                                  //~va23I~
     //*********                                                   //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        dupCtrAll=new int[][]{                                     //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,3},       //20+4                    //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        pairEarth=new Pair[]{                                      //~0A12I~
				new Pair(PT_NUMSAME,2/*type*/,8/*number*/,3/*ctr*/,0            ),//~0A12I~
        };                                                         //~0A12I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2553018 pon 19       rc="+rc.toString());//~va23R~
        if (!swTestAll) break;                                     //~va23I~
    case 2553019:                                                  //~va23I~
     //*********                                                   //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        dupCtrAll=new int[][]{                                     //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A12I~
        	{0,0,2, 0,0,0, 0,0,0},       //20+4                    //~0A12I~
    	    { 3,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
        pairEarth=new Pair[]{                                      //~0A12I~
				new Pair(PT_NUMSAME,3/*type*/,0/*number*/,3/*ctr*/,0            ),//~0A12I~
        };                                                         //~0A12I~
	    rc=ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-2553019 pon ji       rc="+rc.toString());//~va23R~
     //*********                                                   //~0A12I~
        if (!swTestAll) break;                                     //~0A12I~
//*********                                                        //~0A12I~
    default:    //value2                                           //~va23R~
    	if (rc==null)                                              //~va23I~
			rc=ronTestValue3(testCase);                            //~va23R~
        else                                                       //~va23I~
			rc=ronTestValue3(0);                                   //~va23I~
    }                                                              //~0A11I~
        return rc;                                                 //~0A11I~
    }                                                              //~0A11I~
	//*************************************************************************//~0A17I~
	private RonResult ronTestValue3(int PtestCase)                 //~0A17R~
	{                                                              //~0A17I~
	    RonResult rc=null;                                         //~0A17I~
        testCase=PtestCase;                                        //~va23I~
//      if (swTestAll)                                             //~va23R~
//          testCase=0;                                            //~va23R~
	//*************************************************************************//~0A17I~
    switch(testCase)                                               //~va23R~
    {                                                              //~0A17I~
    case 0:                                                        //~va23I~
        if (!swTestAll) break;                                     //~va23I~
//*********                                                        //~0A17I~
    case 50:                                                       //~0A17I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~0A17I~
        	{0,0,1, 1,4,1, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-50 3              rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 51:                                                       //~0A17I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~0A17I~
        	{0,0,1, 1,2,3, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{1,1,1, 1,1,1, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,4);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-51 4              rc="+rc);//~0B02R~
        if (!swTestAll) break;                                     //~va23R~
////*********                                                      //~va23R~
//    case 52:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                   //3455777 pin 2.5.6//~va23R~
//            {0,1,1, 1,2,0, 0,0,0},                               //~va23R~
//            {0,0,0, 3,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-52 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 53:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,1, 1,3,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 3,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-53 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 54:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,1, 1,2,1, 3,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-54 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 55:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{            //3445666 pin 2.4.5       //~va23R~
//            {0,1,1, 2,1,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-55 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 56:                                                     //~va23R~
//        dupCtr=new int[][]{            //3445666 pin 2.4.5       //~va23R~
//            {0,0,1, 3,1,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-56 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 57:                                                     //~va23R~
//        dupCtr=new int[][]{            //3445666 pin 2.4.5       //~va23R~
//            {0,0,1, 2,2,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-57 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 58:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{             //3455678 man 2.5.8      //~va23R~
//            {0,1,1, 1,2,1, 1,1,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-58 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 59:                                                     //~va23R~
//        dupCtr=new int[][]{             //3455678 man 2.5.8      //~va23R~
//            {0,0,1, 1,3,1, 1,1,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-59 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 60:                                                     //~va23R~
//        dupCtr=new int[][]{             //3455678 man 2.5.8      //~va23R~
//            {0,0,1, 1,2,1, 1,2,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-60 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 61:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {1,1,1, 1,1,1, 1,1,3},      //2345678999 man 1.2.4.5.7.8//~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-61 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 62:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,2,1, 1,1,1, 1,1,3},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-62 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 63:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,1,1, 2,1,1, 1,1,3},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-36 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 64:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,1,1, 1,2,1, 1,1,3},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-64 4              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 65:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,1,1, 1,1,1, 2,1,3},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-65 5              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 66:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,1,1, 1,1,1, 1,2,3},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-66 6              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 67:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                 //3334445 sou 3.4.5.6//~va23R~
//            {0,0,4, 3,1,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-67 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 68:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,3, 4,1,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-68 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 69:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,3, 3,2,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-69 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 70:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,3, 3,1,1, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-70 4              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 71:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                 //3334555 sou 2.3.4.5.6//~va23R~
//            {0,1,3, 1,3,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-71 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 72:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,4, 1,3,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-72 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 73:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,3, 2,3,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-73 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 74:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,3, 1,4,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-74 4              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 75:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,3, 1,3,1, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-75 5              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 76:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{               //3335777 sou 4.5.6    //~va23R~
//            {0,0,3, 1,1,0, 3,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-76 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 77:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,3, 0,2,0, 3,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-77 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 78:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,3, 0,1,1, 3,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-78 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 79:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                  //3334455  sou 3.4.5.6//~va23R~
//            {0,0,4, 2,2,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-79 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 80:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,3, 3,2,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-80 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 81:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,3, 2,3,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-81 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 82:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,3, 2,2,1, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-82 4              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 83:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{             //3344455 sou  3.4.5     //~va23R~
//            {0,0,3, 3,2,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-83 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 84:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,2, 4,2,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-84 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 85:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,2, 3,3,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-85 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 86:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{           //4555567  man   3.4.6.7   //~va23R~
//            {0,0,1, 1,4,1, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-86 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 87:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,0, 2,4,1, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-87 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 88:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,0, 1,4,2, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-88 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 89:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,0, 1,4,1, 2,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-89 4              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 90:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{              //4555566 man    3.4.6.7//~va23R~
//            {0,0,1, 1,4,2, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-90 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 91:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,0, 2,4,2, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-91 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 92:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,0, 1,4,3, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-92 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 93:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,0, 1,4,2, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-93 4              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 94:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{                 //5555667 man    4.6.7//~va23R~
//            {0,0,0, 1,4,2, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-94 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 95:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,0, 0,4,3, 1,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-95 2              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 96:                                                     //~va23R~
//        dupCtr=new int[][]{                                      //~va23R~
//            {0,0,0, 0,4,2, 2,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-96 3              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 97:                                                     //~va23R~
//                                                                 //~va23R~
//        dupCtr=new int[][]{             //5555677  man 4.6.7     //~va23R~
//            {0,0,0, 1,4,1, 2,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-97 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 98:                                                     //~va23R~
//        dupCtr=new int[][]{             //5555677                //~va23R~
//            {0,0,0, 0,4,2, 2,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-98 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
////*********                                                      //~va23R~
//    case 99:                                                     //~va23R~
//        dupCtr=new int[][]{             //5555677                //~va23R~
//            {0,0,0, 0,4,1, 3,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            {0,0,0, 0,0,0, 0,0,0},                               //~va23R~
//            { 0,0,0,0,  0,0,0, 0,0} };                         //7//~va23R~
//        rc=ronTestSub(dupCtr,0,0);                               //~va23R~
//        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-99 1              rc="+rc);//~va23R~
//        if (!swTestAll) break;                                   //~va23R~
//*********                                                        //~0A17I~
    case 100:                                                      //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~0A17I~
        	{0,2,2, 2,4,2, 2,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,1);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-100 1              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 101:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~0A17I~
        	{0,1,2, 2,4,3, 2,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,2);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-101 2              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 102:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~0A17I~
        	{0,1,2, 2,4,2, 3,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,3);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-102 3              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 103:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~0A17I~
        	{0,1,2, 2,4,2, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,4);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-103 4              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 104:                                                      //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,2,2, 2,1,1, 4,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,5);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-104 1              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 105:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,1,2, 2,2,1, 4,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,6);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-105 2              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 106:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,1,2, 2,1,1, 4,2,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,7);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-106 3              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 107:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,1,3, 2,1,1, 4,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,8);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-107 4              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 108:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,1,2, 2,1,2, 4,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-108 5              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 109:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,1,2, 2,1,1, 4,1,2},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //+va23R~
	    rc=ronTestSub(dupCtr,0,1);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-109 6              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 110:                                                      //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~0A17I~
        	{2,2,2, 4,3,1, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,0);                                 //~0B02R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-110 1              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 111:                                                      //~0A17I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~0A17I~
        	{1,2,2, 4,3,1, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,1);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-111 2              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 112:                                                      //~0A17I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~0A17I~
        	{1,2,2, 4,4,1, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,2);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-112 3              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 113:                                                      //~0A17I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~0A17I~
        	{1,2,2, 4,3,2, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,3);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-113 4              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 114:                                                      //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~0A17I~
        	{1,4,1, 1,1,4, 1,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,4);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-114 1              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 115:                                                      //~0A17I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~0A17I~
        	{0,4,1, 2,1,4, 1,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,5);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-115 2              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 116:                                                      //~0A17I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~0A17I~
        	{0,4,1, 1,1,4, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,6);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-116 3              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 117:                                                      //~0A17I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~0A17I~
        	{0,4,1, 1,2,4, 1,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,7);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-117 4              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 118:                                                      //~0A17I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~0A17I~
        	{0,4,1, 1,1,4, 1,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,1);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-118 5              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 119:                                                      //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~0A17I~
        	{0,3,1, 3,2,2, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,2);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-119 1              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 120:                                                      //~0A17I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~0A17I~
        	{0,3,1, 2,2,2, 3,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,3);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-120 2              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 121:                                                      //~0A17I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~0A17I~
        	{0,3,2, 2,2,2, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,4);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-121 3              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 122:                                                      //~0A17I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~0A17I~
        	{0,3,1, 2,2,3, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,5);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-122 4              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
//*********                                                        //~0A17I~
    case 123:                                                      //~0A17I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~0A17I~
        	{0,3,1, 2,2,2, 2,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=ronTestSub(dupCtr,0,6);                                 //+va23R~
        if (Dump.Y) Dump.println("ITUARonValueSub.ronTest-123 5              rc="+rc);//~va23R~
        if (!swTestAll) break;                                     //~va23R~
    default:       //value3                                        //~va23R~
        UView.showToastLong("testCaseValue3 ERR="+testCase);       //~va23R~
    }                                                              //~0A17I~
        return rc;                                                 //~0A17I~
    }                                                              //~0A17I~
	//*************************************************************************//~0A17I~
}//class                                                           //~v@@@R~
