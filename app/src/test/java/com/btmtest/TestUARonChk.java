//*CID://+va1aR~: update#= 741;                                    //~va1aR~
//**********************************************************************//~v101I~
//2020/10/19 va1a drop ronchk option,1han constraint only          //~va1aI~
//2020/10/10 va14 (BUG)7pairwith Kan is err even optio allow it    //~va14I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//v@@5 20190126 player means position on the device                //~v@@5I~
//**********************************************************************//~1107I~
package com.btmtest;                                          //~va1aR~


import com.btmtest.game.UA.UARonChk;
import com.btmtest.utils.Dump;
import com.btmtest.utils.Utils;

import org.junit.Before;
import org.junit.Test;

import static com.btmtest.game.GConst.*;
//****************************************************             //~9C11I~
public class TestUARonChk                                          //~va1aR~
{                                                                  //~0914I~
    private UARonChk2 UARC2;                                       //~va1aR~
    private void initEnv()                                         //~va1aR~
    {                                                              //~va1aI~
        StaticVars.AG=new AG();                                     //~va1aI~
    	StaticVars.AG.appName="TestUARonChjk";                      //~va1aR~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~va1aI~
//      new StaticVars(null);	//new AG().init(this);             //~va1aR~
        Dump.open("");	//write all to Terminal log,not exception only//+va1aR~
        if (Dump.Y) Dump.println("TestUARonChk.constructor");      //~va1aI~
    }                                                              //~va1aI~
    //*************************************************************************//~va1aR~
    @Before                                                        //~va1aR~
    public void setUp() throws Exception                           //~va1aR~
    {                                                              //~va1aR~
    	initEnv();                                                 //~va1aI~
        if (Dump.Y) Dump.println("TestUARonChk.setUp");         //~1506R~//~@@@@R~//~v@@@R~//~va1aR~
        UARC2=new UARonChk2();                                    //~va1aR~
    }                                                              //~va1aR~
	//*************************************************************************//~va1aM~
    @Test                                                          //~va1aM~
//  public boolean chkComplete(int Pplayer)                        //~va1aM~
    public void testChkComplete()                                  //~va1aM~
    {                                                              //~va1aM~
//        boolean rc;                                              //~va1aM~
//        player=Pplayer;                                          //~va1aM~
//        swAllInHand=isAllInHand();                               //~va1aM~
//        ctrPair=AG.aPlayers.getCtrPair(Pplayer);       //including Ron tile//~va1aM~
//        if (Dump.Y) Dump.println("UARonChk.chkComplete player="+Pplayer+",ctrPair="+ctrPair);//~9C11I~//~0205R~//~va11R~//~va1aM~
//        TileData[] tds=AG.aPlayers.getHands(Pplayer);       //including Ron tile//~va1aM~
//        TileData tdRon=null;                                     //~va1aM~
//        if (!Tiles.isTakenStatus(tds.length))                    //~va1aM~
//            tdRon=AG.aPlayers.getTileCompleteSelectInfoRon();    //~va1aM~
        UARC2.ronTest();                                             //~9C12M~//~9C13R~//~va1aI~
//        sw1stTake=CompReqDlg.chk1stTake();                       //~va1aM~
//        sortTiles(tds,tdRon);                                    //~va1aM~
//        if ((TestOption.option2 & TestOption.TO2_RON_TEST)!=0) //TODO//~va1aM~
//        {                                                        //~va1aM~
//            int[][] testHand=UARonValue.getTestHandRonChk();   //by testcase//~va1aM~
//            if (testHand!=null)                                  //~va1aM~
//                dupCtr=testHand;                                 //~va1aM~
//        }                                                        //~va1aM~
//        rc=chkCompleteSub();                                     //~va1aM~
//        return rc;                                               //~va1aM~
    }                                                              //~va1aM~
	//*************************************************************************//~va1aI~
class UARonChk2 extends UARonChk                                   //~va1aI~
{                                                                  //~va1aI~
    @Override                                                      //~va1aI~
    protected void init()                                               //~va1aI~
    {                                                              //~va1aI~
//        sw7Pair4Pair= RuleSettingYaku.is7Pair4Pair();            //~va1aI~
//        sw13NoPair= RuleSettingYaku.isYakuman13NoPair();         //~va1aI~
//        sw14NoPair= RuleSettingYaku.isYakuman14NoPair();         //~va1aI~
        sw7Pair4Pair=true;                                         //~va1aI~
        sw13NoPair=true;                                           //~va1aI~
        sw14NoPair=true;                                           //~va1aI~
    }                                                              //~va1aI~
	//*************************************************************************//~9C12I~
	private void ronTest()                                         //~9C12I~
    {                                                              //~9C12I~
	    boolean rc;
        if (Dump.Y) Dump.println("UARonChk.ronTest");              //~9C12I~
		ctrTileAll=HANDCTR_TAKEN;                                  //~9C12I~
        dupCtr=new int[][]{   //4 anko                             //~9C12R~
        	{3,3,0, 0,0,0, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~
    	    { 3,3,0,0,  2,0,0, 0,0} };                         //7 //~9C12R~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-1 4Anko rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{           //7 pair                     //~9C12I~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{2,2,2, 0,0,0, 2,0,2},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-2 7pair rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{           //7 pair err                 //~9C12I~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-3 7pair err rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12R~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{2,2,4, 2,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-4 4seq  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12R~
        	{1,1,3, 4,3,1, 1,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-5 4seq-3  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{2,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-6 13All  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-7 13All err1  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,1,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-8 13All err2  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{3,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-9 13All err3  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-10 13NoPair       rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{2,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-11 13NoPair       rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,2,  1,0,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-12 13NoPair       rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-13 13NoPair err1  rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,1, 0,0,0, 0,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-14 13NoPair err2  rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 0,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  1,1,1, 0,0} };                         //7 //~9C12I~
		ctrTileAll=11;                                             //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
		ctrTileAll=HANDCTR_TAKEN;                                  //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-15 13NoPair err3  rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,1,1,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-16 14NoPair       rc="+rc);//~9C12R~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,2,0,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-17 14NoPair err1  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,1, 0,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,1,1,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-18 14NoPair err2  rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{        //34567    2.5.8  sou           //~9C12I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-21 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,1, 1,2,1, 1,0,0},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-21 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,1, 1,1,1, 1,1,0},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-21 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                 //2345 pin  2.5        //~9C12I~
        	{0,0,2, 1,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-22 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,1, 1,1,2, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-22 2              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                   //2345678 pin 2.5.8  //~9C12I~
        	{0,2,1, 1,1,1, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-23 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,2,1, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-23 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,1,1, 1,2,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-23 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{              //67888 gg    5.8.g       //~9C12I~
        	{2,0,0, 0,1,1, 1,3,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-24 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{2,0,0, 0,0,1, 1,4,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-24 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{3,0,0, 0,0,1, 1,3,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-24 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                     //5666 zou 4.5.6   //~9C12I~
        	{0,0,0, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-25 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 0,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-25 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 0,1,3, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-25 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                           //4666 sou 4.5.6//~9C12I~
        	{0,0,0, 2,0,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-26 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-26 1              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                     //2345666 pin 1.2.4.5.7//~9C12I~
        	{1,1,1, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-27 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,2,1, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-27 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 2,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-27 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-27 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,1,3, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-27 5              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                   //2345777 pin 2.5.6  //~9C12I~
        	{0,2,1, 1,1,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-28 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,2,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-28 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,1,1, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-28 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                         //2333456 pin   1.2.4.7//~9C12I~
        	{1,1,3, 1,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-29 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,2,3, 1,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-29 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,3, 2,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-29 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,3, 1,1,1, 1,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-29 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                //3334568 pin 7.8       //~9C12I~
        	{0,0,3, 1,1,1, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-30 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 1,1,1, 0,2,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-30 2              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~
        	{0,1,1, 1,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~
        	{0,0,1, 2,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~
        	{0,0,1, 1,3,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~
        	{0,0,1, 1,2,3, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                   //3455777 pin 2.5.6  //~9C12I~
        	{0,1,1, 1,2,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,1, 1,3,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,1, 1,2,1, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{            //3445666 pin 2.4.5         //~9C12R~
        	{0,1,1, 2,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{            //3445666 pin 2.4.5         //~9C12R~
        	{0,0,1, 3,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{            //3445666 pin 2.4.5         //~9C12R~
        	{0,0,1, 2,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-31 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //3455678 man 2.5.8        //~9C12I~
        	{0,1,1, 1,2,1, 1,1,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-32 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //3455678 man 2.5.8        //~9C12I~
        	{0,0,1, 1,3,1, 1,1,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-32 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //3455678 man 2.5.8        //~9C12I~
        	{0,0,1, 1,2,1, 1,2,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-32 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,1,1, 1,1,1, 1,1,3},      //2345678999 man 1.2.4.5.7.8//~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,2,1, 1,1,1, 1,1,3},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 2,1,1, 1,1,3},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,2,1, 1,1,3},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,1,1, 2,1,3},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 5              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,1,1, 1,2,3},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-33 6              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                 //3334445 sou 3.4.5.6  //~9C12I~
        	{0,0,4, 3,1,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-34 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 4,1,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-34 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 3,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-34 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 3,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-34 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                 //3334555 sou 2.3.4.5.6//~9C12I~
        	{0,1,3, 1,3,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-35 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,4, 1,3,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-35 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 2,3,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-35 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 1,4,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-35 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 1,3,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-35 5              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{               //3335777 sou 4.5.6      //~9C12I~
        	{0,0,3, 1,1,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-36 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 0,2,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-36 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 0,1,1, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-36 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                  //3334455  sou 3.4.5.6//~9C12I~
        	{0,0,4, 2,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-37 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 3,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-37 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 2,3,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-37 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,3, 2,2,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-37 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //3344455 sou  3.4.5       //~9C12I~
        	{0,0,3, 3,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-38 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,2, 4,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-38 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,2, 3,3,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-38 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{           //4555567  man   3.4.6.7     //~9C12I~
        	{0,0,1, 1,4,1, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-39 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 2,4,1, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-39 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 1,4,2, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-39 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 1,4,1, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-39 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{              //4555566 man    3.4.6.7  //~9C12I~
        	{0,0,1, 1,4,2, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-40 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 2,4,2, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-40 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 1,4,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-40 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 1,4,2, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-40 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                 //5555667 man    4.6.7 //~9C12I~
        	{0,0,0, 1,4,2, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-41 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 0,4,3, 1,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-41 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,0,0, 0,4,2, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-41 3              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //5555677  man 4.6.7       //~9C12I~
        	{0,0,0, 1,4,1, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-42 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //5555677                  //~9C12I~
        	{0,0,0, 0,4,2, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-42 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //5555677                  //~9C12I~
        	{0,0,0, 0,4,1, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-42 1              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~9C12I~
        	{0,2,2, 2,4,2, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-51 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~9C12I~
        	{0,1,2, 2,4,3, 2,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-51 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~9C12I~
        	{0,1,2, 2,4,2, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-51 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~9C12I~
        	{0,1,2, 2,4,2, 2,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-51 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,2,2, 2,1,1, 4,1,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,1,2, 2,2,1, 4,1,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,1,2, 2,1,1, 4,2,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,1,3, 2,1,1, 4,1,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,1,2, 2,1,2, 4,1,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 5              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,1,2, 2,1,1, 4,1,2},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-52 6              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~9C12I~
        	{2,2,2, 4,3,1, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-53 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~9C12I~
        	{1,2,2, 4,3,1, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-53 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~9C12I~
        	{1,2,2, 4,4,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-53 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~9C12I~
        	{1,2,2, 4,3,2, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-53 4              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~9C12I~
        	{1,4,1, 1,1,4, 1,1,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-54 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~9C12I~
        	{0,4,1, 2,1,4, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-54 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~9C12I~
        	{0,4,1, 1,1,4, 2,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-54 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~9C12I~
        	{0,4,1, 1,2,4, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-54 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~9C12I~
        	{0,4,1, 1,1,4, 1,2,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-54 5              rc="+rc);//~9C12I~
                                                                   //~9C12I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~9C12I~
        	{0,3,1, 3,2,2, 2,1,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-55 1              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~9C12I~
        	{0,3,1, 2,2,2, 3,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-55 2              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~9C12I~
        	{0,3,2, 2,2,2, 2,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-55 3              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~9C12I~
        	{0,3,1, 2,2,3, 2,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-55 4              rc="+rc);//~9C12I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~9C12I~
        	{0,3,1, 2,2,2, 2,1,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkCompleteSub();                                       //~9C12I~
        if (Dump.Y) Dump.println("UARonChk.ronTest-55 5              rc="+rc);//~9C12I~
                                                                   //~9C12I~
    }                                                              //~9C12I~
}//UARonChk2                                                       //~va1aI~
}//class                                                           //~v@@@R~
