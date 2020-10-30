//*CID://+DATER~: update#= 823;                                    //~va11R~//~0A11R~
//**********************************************************************//~v101I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//v@@5 20190126 player means position on the device                //~v@@5I~
//**********************************************************************//~1107I~
package com.btmtest.game.UA;                                         //~1107R~  //~1108R~//~1109R~//~v106R~//~v@@@R~

import com.btmtest.TestOption;
import com.btmtest.utils.Dump;
import com.btmtest.utils.UView;
import static com.btmtest.game.TileData.*;
import static com.btmtest.game.UA.Pair.*;


public class UARonValueTest                                        //~va11R~
{                                                                  //~va11I~
	private UARonValue UARV;                                       //~va11R~
    public boolean swStop=true;
    private int[][] dupCtr,dupCtrAll;                              //~va11R~
    private Pair[] pairEarth;                                    //~va11I~
	private int testCase;
	public UARonValueTest(UARonValue Puarv)                        //~va11R~
	{                                                              //~va11I~
    	UARV=Puarv;                                                //~va11I~
        testCase= TestOption.testCaseRonValue;                      //~va11I~
    }                                                              //~va11I~
	public RonResult ronTestValue()                                //~va11R~
	{//~va11I~
	//*************************************************************************//~9C12I~
	    RonResult rc=null;                                              //~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTestValue");              //~9C12I~//~va11R~
        if (testCase>=255010 && testCase<255999)                   //~0A11I~//~0A12R~
            return ronTestValue2();                                //~0A11I~
//*********                                                        //~va11I~
    switch(testCase)                                               //~va11I~
    {                                                              //~va11I~
    case 0:                                                        //~va11I~
        dupCtr=new int[][]{           //7 pair                     //~va11M~
        	{0,2,2, 0,0,0, 0,0,0},                                 //~9C12I~//~va11M~
        	{0,2,2, 0,0,0, 0,0,0},                                 //~9C12I~//~va11M~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~9C12I~//~va11M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~//~va11M~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11M~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-3 7pair tanyao rc="+rc.toString());//~9C12R~//~va11M~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 1:                                                        //~va11I~
        dupCtr=new int[][]{           //7 pair                     //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 2,2,2,2,  2,2,2, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-3 7pair tsuiso rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 20101:                                                    //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 2,2,2,2,  2,2,1, 1,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-3 tsuiso err "+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 2:                                                        //~va11I~
        dupCtr=new int[][]{           //7 pair                     //~9C12I~//~va11M~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~9C12I~//~va11M~
        	{2,2,2, 0,0,0, 2,0,2},                                 //~9C12I~//~va11M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~//~va11M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~//~va11M~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11M~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2 7pair simple rc="+rc.toString());//~9C12R~//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2021:                                                     //~va11I~
        dupCtr=new int[][]{           //7 pair                     //~va11I~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{2,2,0, 0,0,0, 4,0,2},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2 7pair by kan rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~0A17I~
    case 2022:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,2, 2,2,0, 0,2,0},                                 //~0A17R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2 7pair tanyao/pinfu 2peiko rc="+rc.toString());//~0A17R~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 2023:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,2,2, 2,0,2, 2,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,2,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2 7pair/honitsu 2peiko rc="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 2024:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,2,2, 0,0,2, 2,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,2,2,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2024 7pair honitsu         c="+rc.toString());//~0A17R~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 2025:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,2,2, 2,0,2, 2,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,2,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2025 7pair honitsu/2peiko  c="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 2026:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{2,2,2, 2,2,0, 2,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2026 7pair chinitsu       c="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 2027:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{2,2,2, 2,2,2, 0,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2027 7pair chinitsu / 2peiko    c="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 2028:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{2,0,0, 0,0,0, 0,0,2},                                 //~0A17I~
    	    { 0,0,0,0,  2,2,2, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2028 7pair honro                c="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 2029:                                                     //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{2,0,0, 0,0,0, 0,0,2},                                 //~0A17I~
        	{2,0,0, 0,0,0, 0,0,2},                                 //~0A17I~
        	{2,0,0, 0,0,0, 0,0,4},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17R~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2029 7pair chinro   with kan    c="+rc.toString());//~0A17R~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 20291:                                                    //~0A17I~
        dupCtr=new int[][]{           //7 pair                     //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,2,2, 2,0,2, 0,2,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,4,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,2,1);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-20291 7pair allgreen             c="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~va11I~
    case 2201:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 1,1,1},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2201 junchan rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,6/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-22011 junchan naki rc="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~va11I~
    case 2202:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 1,1,1},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,0,false);                      //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2202 junchan naki rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2203:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va11I~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,0,true);                       //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2203 honchan      rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-22031 honchan naki     rc="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~va11I~
    case 2204:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,1,1, 0,0,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~va11I~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,0,false);                      //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2204 honchan naki rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 3:                                                        //~va11I~
        dupCtr=new int[][]{                                        //~va11M~
        	{3,2,0, 0,0,0, 0,0,0},                                 //~9C12R~//~va11M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~//~va11M~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~//~va11M~
    	    { 3,3,0,0,  3,0,0, 0,0} };                         //7 //~9C12R~//~va11M~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,1/*ronNumber*/);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-3 4Anko tanki rc="+rc.toString());//~9C12R~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2301:                                                     //~va11R~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11R~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,0/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2301 pinfu 1ttsu  rc="+rc.toString());//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2302:                                                     //~va11R~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11R~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,0/*ronNumber*/,false);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2302 1ttsu naki rc="+rc.toString());//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2303:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 1,1,1, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,2/*ronNumber*/,false);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2303 1ttsu naki 30fu rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 231:                                                      //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11R~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11R~
    	    { 0,0,0,0,  2,0,0, 0,0} };                         //7 //~va11R~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);    //~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-1 3shiki  not pinf   rc="+rc.toString());//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2310:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 2,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 3,0,0,0,  3,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-1 wgr and round      rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 23101:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 2,0,3},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,3,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-1 wgr and round      rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 23102:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 2,0,0,0,  3,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,3/*ronType*/,0/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-1 pillow wind and round      rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 23103:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 3,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,1/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23103 fu keisan anko takyao rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 23104:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,3},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,1/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23104 fu keisan anko 1-9    rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 23105:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,3,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,1/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23105 fu keisan anko 1-9    rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23106 fu keisan minko ji rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 23107:                                                    //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 1,1,1},                                 //~va11I~
        	{3,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,2/*type*/,0/*number*/,3/*ctr*/,0            ),//~va11R~
        };                                                         //~va11I~
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23107 fu keisan 19 rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23108 fu keisan minko  rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23108 fu keisan minkan 2-8rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23108 fu keisan ankan not allhand 2-8rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-231092 fu keisan ankan 19 rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-231093 fu keisan ankan ji rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-231094 fu keisan minkan ji rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-231095 fu keisan minkan 19 rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-231096 fu keisan minkan 28 rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 232:                                                      //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-1 3shiki pinfu  rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-1 3shiki naki rc="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,7/*ronNumber*/,0/*ctrAnkan*/,false/*saAllHand*/,pairEarth);
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23203  40fu? rc="+rc.toString());
        break;
//*********
    case 23203:
        dupCtr=new int[][]{
        	{0,1,1, 1,1,2, 2,1,0},
        	{0,0,0, 0,0,0, 0,0,0},
        	{0,0,0, 0,0,0, 0,0,0},
    	    { 3,0,0,2,  0,0,0, 0,0} };                         //7
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,4/*ronNumber*/);
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23203 fu? rc="+rc.toString());
        break;
//*********                                                        //~va11I~
    case 2321:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,0/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2321 pinfu         rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2322:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,1,1, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,2/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2322 not pinfu  kanchan       rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2323:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,2/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2323 not pinfu  penchan 3     rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2324:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,1,1, 0,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,6/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2324 not pinfu  penchan 7     rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2325:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,0/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2325     pinfu  side and kanchan   rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2326:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,1/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2326     pinfu  side and kanchan   rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2327:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,2/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2327     pinfu  side and kanchan   rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2328:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{1,2,2, 1,0,0, 0,0,2},                                 //~va11I~
        	{0,0,1, 1,1,0, 1,1,1},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0/*ronType*/,3/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2328     pinfu  side and kanchan   rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 233:                                                      //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,2,2, 2,2,2, 2,2,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-233 daisharin pin rc="+rc.toString());//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2330:                                                     //~va11I~//~0A16R~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,2,2, 1,1,2, 2,1,0},                                 //~va11I~//~0A16R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~//~0A16R~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2330 pinfu honitsu rc="+rc.toString());//~va11R~//~0A16R~//~0A17R~
        break;                                                     //~va11I~
//*********                                                        //~0A20I~
    case 233001:                                                   //~0A20I~
        dupCtr=new int[][]{                                        //~0A20I~
        	{0,0,0, 0,0,1, 1,1,0},                                 //~0A20I~
        	{0,1,1, 2,2,2, 1,2,0},                                 //~0A20I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A20I~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,4/*ronNumber*/);    //~0A20I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-233001 said daisharin rc="+rc.toString());//~0A20I~
        break;                                                     //~0A20I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23301 honitsu naki rc="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A16I~
    case 2331:                                                     //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{1,2,2, 1,1,2, 2,1,2},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A16I~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);    //~0A16I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2331 pinfu chinitsu rc="+rc.toString());//~0A16I~
        break;                                                     //~0A16I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2331 naki  chinitsu rc="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~va11I~
    case 2332:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{1,2,2, 1,1,2, 2,1,2},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/,false);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2332 kui chinitsu rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 234:                                                      //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,2,2, 2,0,1, 1,1,0},                                 //~va11I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~va11I~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-234 iipeiko pinf rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23401 iipeiko naki rc="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~va11I~
    case 2341:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~va11I~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,1/*ronNumber*/);    //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2341 2peiko pinf rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
	    rc=UARV.ronTestSub(dupCtr,2/*ronType*/,3/*ronNumber*/);    //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23411 2peiko naki err rc="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23411 2peiko naki tanyao only rc="+rc.toString());//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~va11I~
    case 2342:                                                     //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 2,2,2, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~va11I~
	    rc=UARV.ronTestSub(dupCtr,1/*ronType*/,2/*ronNumber*/,false/*allInHand*/);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2341 2peiko pinf rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 235:                                                      //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,2,2, 2,0,1, 1,1,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                             //~va11I~
        dupCtrAll=new int[][]{                                     //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                              //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSEQ,2/*type*/,1/*number*/,0/*ctr*/,0            ),//~va11I~
        };                                                         //~va11I~
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-235 iipeiko naki rc="+rc.toString());//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 4:                                                        //~va11I~
        dupCtr=new int[][]{                                        //~9C12R~//~va11R~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{2,2,4, 2,2,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-4 4seq  rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 5:                                                        //~va11I~
        dupCtr=new int[][]{                                        //~9C12R~//~va11R~
        	{1,1,3, 4,3,1, 1,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-5 4seq-3  rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 6:                                                        //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{2,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-6 kokusi 13All  13wait rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 7:                                                        //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{2,0,0, 0,0,0, 0,0,1},                                 //~9C12I~//~va11R~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,8);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-7 13All not wait13  rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 8:                                                        //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,1,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-8 13All err2  rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A16I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-9 3dragon earth rc="+rc);//~9C12I~//~va11R~//~0A16I~
        break;                                                     //~va11I~
//*********                                                        //~0A16I~
    case 22901:                                                    //~0A16I~
                                                                   //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16R~
        	{0,0,0, 0,0,3, 0,0,0},                                 //~0A16R~
    	    { 3,0,0,0,  3,3,2, 0,0} };                         //7 //~0A16I~
	    rc=UARV.ronTestSub(dupCtr,3,4);                            //~0A16I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-22901 little 3dragon shosangen rc="+rc);//~0A16I~
        break;                                                     //~0A16I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,5/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-229011 little 3dragon shosangen naki rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A16I~
    case 22902:                                                    //~0A16I~
                                                                   //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A16I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~0A16I~
    	    { 0,2,0,0,  3,3,0, 0,0} };                         //7 //~0A16I~
	    rc=UARV.ronTestSub(dupCtr,3,4);                            //~0A16I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-22901 honor tile               rc="+rc);//~0A16I~
        break;                                                     //~0A16I~
//*********                                                        //~va11I~
    case 10:                                                       //~va11I~
                                                                   //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-10 13NoPair       rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 11:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{2,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-11 13NoPair       rc="+rc);//~9C12R~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 12:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,2,  1,0,1, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-12 13NoPair       rc="+rc);//~9C12R~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 13:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-13 13NoPair err1  rc="+rc);//~9C12R~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 14:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,1, 0,0,0, 0,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-14 13NoPair err2  rc="+rc);//~9C12R~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 15:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 0,0,0},                                 //~9C12I~
        	{1,0,0, 0,0,1, 0,0,1},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  1,1,1, 0,0} };                         //7 //~9C12I~
//		ctrTileAll=11;                                             //~9C12I~//~va11R~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
//		ctrTileAll=HANDCTR_TAKEN;                                  //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-15 13NoPair err3  rc="+rc);//~9C12R~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 16:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,1,1,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-16 14NoPair       rc="+rc);//~9C12R~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 17:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,0, 1,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,2,0,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-17 14NoPair err1  rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 18:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{1,0,1, 0,0,0, 1,0,0},                                 //~9C12I~
        	{0,0,1, 0,0,1, 0,0,1},                                 //~9C12I~
        	{0,1,0, 0,1,0, 0,1,0},                                 //~9C12I~
    	    { 0,1,1,1,  1,1,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-18 14NoPair err2  rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 281:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,3, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 3,3,3,2,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,2);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-281 little 4wind  rc="+rc);//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A16I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28101 4wind small naki rc="+rc);//~0A16I~
        break;                                                     //~0A16I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A16I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28101 4wind big naki rc="+rc);//~0A16I~
        break;                                                     //~0A16I~
//*********                                                        //~0A16I~
    case 282:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 3,3,3,3,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,2);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-282 big    4wind  rc="+rc);//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 283:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,3,3, 3,0,3, 0,0,0},                                 //~va11R~
    	    {0,0,0,0,  0,2,0, 0,0} };                         //7  //~va11R~
	    rc=UARV.ronTestSub(dupCtr,2,1);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-283 all green Dragon pillow   rc="+rc);//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,2/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A16I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28101 allGreen  naki rc="+rc);//~0A16I~
//*********                                                        //~0A16I~
    case 284:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,3,3, 3,0,0, 0,2,0},                                 //~va11I~//~0A16R~
    	    { 0,0,0,0,  0,3,0, 0,0} };                         //7 //~va11I~//~0A16R~
	    rc=UARV.ronTestSub(dupCtr,2,1);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-284 all green Dragon tripret  rc="+rc);//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 285:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,3,3, 3,0,3, 0,2,0},                                 //~va11I~//~0A16R~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~//~0A16R~
	    rc=UARV.ronTestSub(dupCtr,2,1);                            //~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-285 all green no Dragon rc="+rc);//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 286:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va11I~
        	{3,0,0, 0,0,0, 0,0,2},                                 //~va11I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-286 chinrou              rc="+rc);//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A16I~//~0A17R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28101 chirou  naki rc="+rc);//~0A16I~//~0A17R~
        break;                                                     //~0A17I~
//*********                                                        //~va11I~
    case 2861:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{3,0,0, 0,0,0, 0,0,3},                                 //~va11I~
        	{3,0,0, 0,0,0, 0,0,2},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  3,0,0, 0,0} };                         //7  //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2861 honro      rc="+rc);//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28611 honro naki     rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-287 4kan  chinro      rc="+rc);//~va11R~
        break;                                                     //~va11I~
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
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{4,0,0, 0,0,0, 0,0,4},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,2/*type*/,0/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11I~
				new Pair(PT_NUMSAME,2/*type*/,8/*number*/,4/*ctr*/,TDF_KAN_RIVER),//~va11I~
        };                                                         //~va11I~
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,8/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2870 4kan err          rc="+rc);//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-287 4kan not chinro rc="+rc);//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2872 3anko by 3kan rc="+rc);//~va11I~//~0A16R~
        break;                                                     //~va11I~
//*********                                                        //~0A16I~
    case 28721:                                                    //~0A16I~
                                                                   //~0A16I~
        dupCtr=new int[][]{                                        //~0A16I~
        	{0,0,0, 0,0,0, 0,3,0},                                 //~0A16I~
        	{2,0,0, 0,0,0, 0,0,3},                                 //~0A16I~
        	{0,0,0, 1,1,1, 0,0,3},                                 //~0A16I~//~0A17R~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~0A16I~
	    rc=UARV.ronTestSub(dupCtr,1,0);                            //~0A16I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28721 3anko rc="+rc);//~0A16I~
        break;                                                     //~0A16I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28722 3tonko naki rc="+rc);//~0A16I~//~0A17R~
        break;                                                     //~0A16I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28723 3anko naki naki rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2873 3kan rc="+rc);//~va11I~//~0A16R~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,0/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A16I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28731 tsuiiso naki rc="+rc);//~0A16I~
        break;                                                     //~0A16I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,0/*ronNumber*/,4/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~va11I~//~0A21R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2874 4kan 4anko tanki rc="+rc);//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,4/*ctrAnkan*/,true/*swAllHand*/,pairEarth);//~va11R~//~0A20R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2875 4kan 4ankoTanki big4wind tuiso tenho rc="+rc);//~va11I~
        break;                                                     //~va11I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,0/*ctrAnkan*/,false/*swAllHand*/,pairEarth);//~0A20R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2876 not 4anko  rc="+rc);//~0A20I~
        break;                                                     //~0A20I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,3/*ronType*/,4/*ronNumber*/,1/*ctrAnkan*/,true/*swAllHand*/,pairEarth);//~0A20I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2877 1ankan 4anko  rc="+rc);//~0A20I~//~0A21R~
        break;                                                     //~0A20I~
//*********                                                        //~0A20I~
    case 2878:                                                     //~0A20I~
        dupCtr=new int[][]{                                        //~0A20I~
        	{3,3,3, 0,0,0, 0,0,0},                                 //~0A20I~
        	{3,0,0, 0,0,0, 0,0,0},                                 //~0A20I~//~0A21R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A20I~
    	    {0,0,0,2,  0,0,0, 0,0} };                         //7  //~0A20I~//~0A21R~
	    rc=UARV.ronTestSub(dupCtr,1,0);                            //~0A21I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2878 shabo ron not 4anko  rc="+rc);//~0A20I~//~0A21R~
        break;                                                     //~0A20I~
//*********                                                        //~va11I~
    case 288:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{3,2,1, 1,1,1, 1,1,3},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-288 9gate wait9  rc="+rc);//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 289:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{3,2,1, 1,1,1, 1,1,3},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-288 9gate not wait9  rc="+rc);//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 290:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
        	{3,2,1, 1,1,1, 1,1,3},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11R~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
	    rc=UARV.ronTestSub(dupCtr,1,1);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-288 9gate not man  rc="+rc);//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 19:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{        //34567    2.5.8  sou           //~9C12I~//~va11R~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-21 1              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 20:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,0,1, 1,2,1, 1,0,0},                                 //~9C12I~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-21 2              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 220:                                                      //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                                        //~va11I~
        	{0,3,0, 1,1,1, 3,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
	    rc=UARV.ronTestSub(dupCtr,1,1);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-220 tanyao only        rc="+rc);//~va11I~
        break;                                                     //~va11I~
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
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    {0,0,0,0,  0,0,0, 0,0} };                         //7  //~va11I~
        pairEarth=new Pair[]{                                      //~va11I~
				new Pair(PT_NUMSAME,0/*type*/,1/*number*/,3/*ctr*/,0),//~va11I~
				new Pair(PT_NUMSAME,0/*type*/,3/*number*/,3/*ctr*/,0),//~va11I~
				new Pair(PT_NUMSAME,0/*type*/,6/*number*/,3/*ctr*/,0),//~va11I~
        };                                                         //~va11I~
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,1/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-221  kuitan toitoi     rc="+rc);//~va11I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2211:                                                       //~va11I~//~0A12R~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{2,0,0, 1,1,1, 1,1,1},  //dora 9man  up                //~9C12I~//~0A12R~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~9C12I~//~0A12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 3,0,0,0,  0,0,0, 0,0} };                             //~0A12R~
	    rc=UARV.ronTestSub(dupCtr,3,0,false);                                       //~9C12I~//~va11R~//~0A12R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2211-1 dora chk 9man up  rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~9C12I~//~va11R~//~0A12R~
    //***************                                              //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{2,1,1, 1,0,1, 1,1,0},  //dora 1man  down              //~0A12I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 3,0,0,0,  0,0,0, 0,0} };                             //~0A12I~
	    rc=UARV.ronTestSub(dupCtr,3,0,false);                      //~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2211-2 dora chk 1man down  rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~0A12I~
    //***************                                              //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},   //dora 9pin kan up            //~0A12I~
        	{2,0,0, 1,1,1, 1,1,1},                                 //~0A12I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 3,0,0,0,  0,0,0, 0,0} };                             //~0A12I~
	    rc=UARV.ronTestSub(dupCtr,3,0,false);                      //~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2211-3 dora chk 9pin kan up   rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~0A12I~
    //***************                                              //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},   //dora 9pin kan down          //~0A12I~
        	{2,0,0, 1,1,1, 1,1,1},                                 //~0A12I~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 3,0,0,2,  0,0,0, 0,0} };                             //~0A12I~
	    rc=UARV.ronTestSub(dupCtr,3,0,false);                      //~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2211-4 dora chk pei  kan down rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~0A12I~
    //***************                                              //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},   //red                         //~0A12I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 3,0,0,0,  3,0,2, 0,0} };                         //7 //~0A12I~
	    rc=UARV.ronTestSub(dupCtr,3,0,false);                      //~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2211-5 Rof WGR            rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~0A12I~
    //***************                                              //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},   //red                         //~0A12I~
        	{0,0,0, 1,1,1, 1,1,1},                                 //~0A12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A12I~
    	    { 3,0,0,0,  3,0,2, 0,0} };                         //7 //~0A12I~
	    rc=UARV.ronTestSub(dupCtr,3,0,false);                      //~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2211-6 Rof WGR next       rc="+rc.longRank.toStringName(true,true)+"="+rc.toString());//~0A12R~
    //****************                                             //~0A12I~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 22:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                 //2345 pin  2.5        //~9C12I~//~va11R~
        	{0,0,2, 1,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-22 1              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~0A16I~
    case 22201:          //no 1 han   dora                         //~0A16I~//~0A24R~
                                                                   //~0A16I~
        dupCtr=new int[][]{                 //2345 pin  2.5        //~0A16I~
        	{1,1,1, 1,1,1, 0,0,0},                                 //~0A16I~//~0A24R~
        	{3,0,0, 0,0,0, 1,1,1},                                 //~0A16I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A16I~//+0A24R~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~0A16I~//~0A24R~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A16I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-22201 0han hand    rc="+rc);//~0A16R~//~0A17R~
        break;                                                     //~0A16I~
//*********                                                        //~0A24I~
    case 222011:          // 1 han white                           //~0A24I~
                                                                   //~0A24I~
        dupCtr=new int[][]{                                        //~0A24I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~0A24I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~0A24I~
        	{2,0,0, 1,1,1, 0,0,0},                                 //~0A24I~
    	    { 0,0,0,0,  3,0,0, 0,0} };                         //7 //~0A24I~
	    rc=UARV.ronTestSub(dupCtr,3,3);                            //~0A24I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-222011 1han white    rc="+rc);//~0A24I~
        break;                                                     //~0A24I~
//*********                                                        //~0A24I~
    case 222012:          // 1 han white                           //~0A24I~
                                                                   //~0A24I~
        dupCtr=new int[][]{                                        //~0A24I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~0A24I~
        	{0,0,0, 0,0,0, 1,1,1},                                 //~0A24I~
        	{2,0,0, 1,1,1, 0,0,0},                                 //~0A24I~
    	    { 3,0,0,0,  0,0,0, 0,0} };                         //7 //~0A24I~
	    rc=UARV.ronTestSub(dupCtr,3,0);                            //~0A24I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-222012 1han white    rc="+rc);//~0A24I~
        break;                                                     //~0A24I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-22202 0han naki    rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~va11I~
    case 23:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,0,1, 1,1,2, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-22 2              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 24:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                   //2345678 pin 2.5.8  //~9C12I~//~va11R~
        	{0,2,1, 1,1,1, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23 1              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 25:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,1,1, 1,2,1, 1,1,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23 2              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 26:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,1,1, 1,1,1, 1,2,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-23 3              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 27:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{              //67888 gg    5.8.g       //~9C12I~//~va11R~
        	{2,0,0, 0,1,1, 1,3,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-24 1              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 28:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{2,0,0, 0,0,1, 1,4,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-24 2              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 29:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{3,0,0, 0,0,1, 1,3,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-24 3              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 30:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                     //5666 zou 4.5.6   //~9C12I~//~va11R~
        	{0,0,0, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-25 1              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 31:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,0,0, 0,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-25 2              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 32:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,0,0, 0,1,3, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-25 3              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 33:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                           //4666 sou 4.5.6//~9C12I~//~va11R~
        	{0,0,0, 2,0,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 34:                                                       //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-26 1              rc="+rc);//~9C12I~//~va11R~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,0,0, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-26 1              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 35:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                     //2345666 pin 1.2.4.5.7//~9C12I~//~va11R~
        	{1,1,1, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-27 1              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 36:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,2,1, 1,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-27 2              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 37:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,1,1, 2,1,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-27 3              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 38:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,1,1, 1,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-27 4              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 39:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,1,1, 1,1,3, 1,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-27 5              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 40:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                   //2345777 pin 2.5.6  //~9C12I~//~va11R~
        	{0,2,1, 1,1,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28 1              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 41:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,1,1, 1,2,0, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28 2              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 42:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,1,1, 1,1,1, 3,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-28 3              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 422:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                         //2333456 pin   1.2.4.7//~9C12I~//~va11R~
        	{1,1,3, 1,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-29 1              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 43:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,2,3, 1,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-29 2              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 44:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,1,3, 2,1,1, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-29 3              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 45:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,1,3, 1,1,1, 1,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-45 4              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 46:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                //3334568 pin 7.8       //~9C12I~//~va11R~
        	{0,0,3, 1,1,1, 1,1,0},                                 //~9C12I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~9C12I~//~va11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,2);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-46 1              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2460:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                //3334568 pin 7.8       //~va11I~
        	{0,0,1, 1,1,1, 1,1,0},                                 //~va11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 2,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,2);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2460              rc="+rc);//~va11I~//~0A11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2461:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                //3334568 pin 7.8       //~va11I~
        	{0,0,3, 1,1,1, 1,1,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,0,0, 1,1,1, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2461              rc="+rc);//~va11I~//~0A11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2462:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                //3334568 pin 7.8       //~va11I~
        	{0,0,3, 1,1,1, 1,1,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2462              rc="+rc);//~va11I~//~0A11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2463:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                //3334568 pin 7.8       //~va11I~
        	{0,0,3, 1,1,1, 1,1,0},                                 //~va11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2463              rc="+rc);//~va11I~//~0A11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2464:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                //3334568 pin 7.8       //~va11I~
        	{0,0,3, 1,1,1, 1,1,0},                                 //~va11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,6);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2464              rc="+rc);//~va11I~//~0A11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 2465:                                                     //~va11I~
                                                                   //~va11I~
        dupCtr=new int[][]{                //3334568 pin 7.8       //~va11I~
        	{0,0,3, 1,1,1, 1,1,0},                                 //~va11I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~va11I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va11I~
	    rc=UARV.ronTestSub(dupCtr,0,7);                            //~va11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-2465              rc="+rc);//~va11I~//~0A11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 47:                                                       //~va11I~
        dupCtr=new int[][]{                                        //~9C12I~//~va11R~
        	{0,0,3, 1,1,1, 0,0,0},                                 //~9C12I~//~0A11R~
        	{0,0,0, 0,0,0, 0,2,0},                                 //~9C12I~//~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-47 2              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~0A16M~
    case 24701:     //258                                          //~0A16I~
                                                                   //~0A16M~
        dupCtr=new int[][]{                                        //~0A16M~
        	{1,1,1, 1,1,1, 1,1,1},        //pinfu                  //~0A16M~
        	{1,1,1, 0,0,0, 0,0,0},                                 //~0A16M~
        	{0,0,2, 0,0,0, 0,0,0},                                 //~0A16M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A16M~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A16M~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-24701 ittsu       rc="+rc.toString());//~0A16I~
                                         //~0A16M~
        break;                                                     //~0A16M~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,3/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-24702 ittsu  naki rc="+rc.toString());//~0A17I~
                                                                   //~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~va11I~
    case 48:                                                       //~va11I~
                                                                   //~9C12I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~//~va11R~
        	{0,1,1, 1,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 3,3,0},                                 //~9C12I~//~va11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-48 1              rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    case 49:                                                       //~va11I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~//~va11R~
        	{0,0,1, 2,2,3, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{3,3,0, 0,0,0, 0,0,0},                                 //~9C12I~//~va11R~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                                       //~9C12I~//~va11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-49                rc="+rc);//~9C12I~//~va11R~
        break;                                                     //~va11I~
//*********                                                        //~va11I~
    default:                                                       //~0A11I~
		rc=ronTestValue3(testCase);                                 //~0A17I~
    }                                                              //~va11I~
        if ((TestOption.option2 & TestOption.TO2_CHKRANK)==0)      //~0A16I~
	        UView.showToastLong("testCase="+testCase);                 //~va11I~//~0A11R~//~0A16R~
        return rc;                                                           //~9C12I~
    }                                                              //~9C12I~
	//*************************************************************************//~0A17I~
	private RonResult ronTestValue2()                               //~0A11I~//~0A17R~
	{                                                              //~0A11I~
	//*************************************************************************//~0A11I~
	    RonResult rc=null;                                         //~0A11I~
        boolean swTestAll=false;                                    //~0A11I~
//*********                                                        //~0A11I~
    switch(testCase)                                               //~0A11I~
    {                                                              //~0A11I~
    case 255010:     //258                                         //~0A11I~
        swTestAll=true;                                            //~0A11I~
//*********                                                        //~0A11I~
    case 255011:     //258                                         //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},        //pinf                   //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,0,2, 1,1,1, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255011  wait3  rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255012:     //258                                         //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,2,1, 1,0,0},        //pinfu                  //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,0,2, 1,1,1, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255012  wait3       rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255013:       //258                                       //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,1,1, 1,1,0},        //pinfu                  //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,0,2, 1,1,1, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,7);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255013  wait3       rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255021:          //25                                     //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,1, 1,1,0, 0,0,0},    //32                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255021 nobetan   rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11M~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255022:            //25                                   //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,0, 0,0,0},       //32                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255022 nobetan   rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255031:            //258                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,1, 1,1,1, 1,1,0},     //32                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255031 nobetan3  rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255032:           //258                                   //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                //32                    //~0A11R~
        	{0,1,1, 1,2,1, 1,1,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255032 nobetan3  rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255033:           //258                                   //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,1,1, 1,2,0},              //32               //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,7);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255033 nobetan3  rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255041:                                                   //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{     //0-4 1-2                          //~0A11R~
        	{0,1,1, 4,0,0, 0,0,0},       //34                      //~0A11R~
        	{0,2,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255041 2shabo    rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255042:                //0-4 1-2                          //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 3,0,0, 0,0,0},                                 //~0A11I~
        	{0,3,0, 0,0,0, 0,0,0},          //32                   //~0A11R~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,1,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255042 2shabo    rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255051:         //124                                     //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{1,1,3, 0,0,0, 0,0,0},      //pinfu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11R~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255051 13        rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255052:            //124                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,3, 0,0,0, 0,0,0},           //36                  //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11R~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255052 13        rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255053:            //124                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,3, 1,0,0, 0,0,0},              //pinfu            //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11R~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255053 13        rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255061:       //23                                        //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,0, 3,0,0, 0,0,0},    //36                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255061 1-3       rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255062:           //23                                    //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 3,0,0, 0,0,0},      //32                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,2);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255062 1-3       rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255071:     //12457                                       //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,1, 1,1,3, 0,0,0},     //2  36                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255071 11113     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255072:            //12457                                //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,3, 0,0,0},      //5   36                   //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255072 11113     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255073:             //12457                               //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{1,1,1, 1,1,3, 0,0,0},        //1   pinfu              //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255073 11113     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255074:             //12457           pinfu               //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                //4                     //~0A11R~
        	{0,1,1, 2,1,3, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255074 11113     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255075:             //12457                               //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                  //7  pinfu            //~0A11R~
        	{0,1,1, 1,1,3, 1,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,6);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255075 11113     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255081:            //256                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,1, 1,1,0, 3,0,0},    //36                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255081 111103    rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255082:             //256                                 //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{               //36                     //~0A11R~
        	{0,1,1, 1,2,0, 3,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255082 111103    rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255083:                  //256                            //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,1,1, 3,0,0},            //32                 //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255083 111103    rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255091:    //1247                                         //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,3, 1,1,1, 0,0,0},        //36                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);        //2                 //~0A11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255091 13111     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255092:     //1247                                        //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{1,1,3, 1,1,1, 0,0,0},            //pinfu              //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,0);           //1              //~0A11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255092 13111     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255093:           //1247                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,3, 2,1,1, 0,0,0},             //pinfu             //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);          //4               //~0A11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255093 13111     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255094:           //1247                                  //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,3, 1,1,1, 1,0,0},          //pinfu                //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,6);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255094 13111     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255101:    //78                                           //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 1,1,1, 1,1,0},     //32                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,6);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255101 311101    rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255102: //78                                              //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 1,1,1, 0,2,0},         //36                    //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,7);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255102 311102    rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255111:     //2457                                        //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,3, 0,0,0},      //34                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255111 1123      rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255112:      //2457                                       //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 2,2,3, 0,0,0},         //pinfu                  //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255112 1123      rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255113:            //2457                                 //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,3,3, 0,0,0},        //36                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255113 1123      rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255114:                  //2457                           //~0A11R~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,2,3, 1,0,0},         //pinfu                 //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,6);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255114 1123      rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255121:             //256                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,0, 3,0,0},    //34                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255121 11203     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255122:             //256                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,3,0, 3,0,0},        //36                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255122 11203     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255123:             //256                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,2,1, 3,0,0},        //32                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255123 11203     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255131:             //245                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 2,1,3, 0,0,0},           //pinfu               //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255131 11203     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255132:             //245                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 3,1,3, 0,0,0},   //36                          //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255132 11203     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255133:             //245                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 2,2,3, 0,0,0},   //pinfu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255133 11203     rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255141:             //258                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,1, 1,1,0},    //pinfu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255141           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255142:             //258                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                 //pinfu                //~0A11R~
        	{0,0,1, 1,3,1, 1,1,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255142           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255143:             //258                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,2,1, 1,2,0},    //32                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,7);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255143           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255151:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{1,1,1, 1,1,1, 1,1,3},      //pinfu                    //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A11R~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255151           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11M~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255152:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,2,1, 1,1,1, 1,1,3},     //40                        //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255152           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255153:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 2,1,1, 1,1,3},    //pinfu                      //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255153           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255154:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,2,1, 1,1,3},      //40                       //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255154           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255155:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,1,1, 2,1,3},        //pinfu                  //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,6);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255155           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255156:             //124578                              //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,1, 1,1,1, 1,2,3},     //40                        //~0A11R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,7);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255156           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255161:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,4, 3,1,0, 0,0,0},     //34                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,2);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255161           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255162:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 4,1,0, 0,0,0},        //36                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255162           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255163:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 3,2,0, 0,0,0},       //40                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255163           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255164:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 3,1,1, 0,0,0},         //34                    //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255164           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255171:             //23456                               //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,1,3, 1,3,0, 0,0,0},    //34fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,1);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255171           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255172:             //23456                               //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11R~
        	{0,0,4, 1,3,0, 0,0,0},    //34fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,2);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255172           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255173:             //23456                               //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 2,3,0, 0,0,0},    //40fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255173           rc="+rc.toString());//~0A11R~
        if (!swTestAll)                                            //~0A11I~
        break;                                                     //~0A11I~
//*********                                                        //~0A11I~
    case 255174:             //23456                               //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 1,4,0, 0,0,0},      //34fu                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255174           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255175:             //23456                               //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 1,3,1, 0,0,0},    //34fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255175           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255181:             //456                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 1,1,0, 3,0,0}, //36fu                          //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255181           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255182:             //456                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 0,2,0, 3,0,0}, //40fu                          //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255182           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255183:             //456                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 0,1,1, 3,0,0},     //46fu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255183           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255191:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,4, 2,2,0, 0,0,0},    //pinfu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,2);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255191           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255192:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 3,2,0, 0,0,0}, //fu=36                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255192           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255193:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 2,3,0, 0,0,0},   //36fu                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255193           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255194:             //3456                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 2,2,1, 0,0,0},        //pinfu                  //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255194           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255201:             //345                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,3, 3,2,0, 0,0,0},      //36fu                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,2);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255201           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255202:             //345                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,2, 4,2,0, 0,0,0},     //32fu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255202           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255203:             //345                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,2, 3,3,0, 0,0,0},   //36fu                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255203           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255211:             //3467                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,1, 1,4,1, 1,0,0},      //pinfu                    //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,2);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255211           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255212:             //3467                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 2,4,1, 1,0,0},     //36fu                      //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255212           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255213:             //3467                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 1,4,2, 1,0,0},     //pinfu                     //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255213           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255214:             //3467                                //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 1,4,1, 2,0,0},   //36fu                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,6);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255214           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255231:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 1,4,2, 1,0,0}, //pinfu                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255231           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255232:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 0,4,3, 1,0,0},    //36fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255232           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255233:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 0,4,2, 2,0,0},  //pinfu                        //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,6);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255233           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255241:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 1,4,1, 2,0,0},    //34fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,3);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255241           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255242:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 0,4,2, 2,0,0},  //32fu                         //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255242           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A11I~
    case 255243:             //467                                 //~0A11I~
                                                                   //~0A11I~
        dupCtr=new int[][]{                                        //~0A11I~
        	{0,0,0, 0,4,1, 3,0,0},    //36fu                       //~0A11R~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A11I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A11I~
	    rc=UARV.ronTestSub(dupCtr,0,6);                            //~0A11I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255243           rc="+rc.toString());//~0A11R~
        if (!swTestAll) break;                                     //~0A11R~
//*********                                                        //~0A12I~
    case 255300:             //6                                   //~0A12I~
                                                                   //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,1,3, 1,0,0},    //32fu                       //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
        	{0,1,1, 1,1,1, 1,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255300-1         rc="+rc.toString());//~0A12I~
    //*************                                                //~0A12I~
        dupCtr=new int[][]{                                        //~0A12I~
        	{0,0,0, 0,1,4, 1,0,0},    //36fu                       //~0A12I~
        	{0,1,1, 1,0,0, 2,0,0},                                 //~0A12I~
        	{0,1,1, 1,0,0, 0,0,0},                                 //~0A12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A12I~
	    rc=UARV.ronTestSub(dupCtr,0,5);                            //~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255300-2         rc="+rc.toString());//~0A12I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255301-1 ankan  2-8   rc="+rc.toString());//~0A12I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255301-2 ankan  19  rc="+rc.toString());//~0A12I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,true/*saAllHand*/,pairEarth);//~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255301-3 ankan  ji  rc="+rc.toString());//~0A12I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255301-4 minkan 2-8   rc="+rc.toString());//~0A12I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255301-5 minkan 19    rc="+rc.toString());//~0A12I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255301-6 minkan ji    rc="+rc.toString());//~0A12I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255301-7 pon 2-8      rc="+rc.toString());//~0A12I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255301-8 pon 19       rc="+rc.toString());//~0A12I~
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
	    rc=UARV.ronTestSub(dupCtr,dupCtrAll,0/*ronType*/,1/*ronNumber*/,1/*ctrAnkan*/,false/*saAllHand*/,pairEarth);//~0A12I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-255301-9 pon ji       rc="+rc.toString());//~0A12I~
     //*********                                                   //~0A12I~
        if (!swTestAll) break;                                     //~0A12I~
//*********                                                        //~0A12I~
    default:                                                       //~0A11I~
        UView.showToastLong("tsetCase ERR="+testCase);             //~0A11I~
    }                                                              //~0A11I~
        if ((TestOption.option2 & TestOption.TO2_CHKRANK)==0)      //~0A16I~
	        UView.showToastLong("tsetCase="+testCase);                 //~0A11R~//~0A16R~
        return rc;                                                 //~0A11I~
    }                                                              //~0A11I~
	//*************************************************************************//~0A17I~
	private RonResult ronTestValue3(int PtestCase)                 //~0A17R~
	{                                                              //~0A17I~
	    RonResult rc=null;                                         //~0A17I~
	//*************************************************************************//~0A17I~
    switch(PtestCase)                                              //~0A17R~
    {                                                              //~0A17I~
//*********                                                        //~0A17I~
    case 50:                                                       //~0A17I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~0A17I~
        	{0,0,1, 1,4,1, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,1,1, 1,0,1, 1,1,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-50 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 51:                                                       //~0A17I~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~0A17I~
        	{0,0,1, 1,2,3, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{1,1,1, 1,1,1, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,4);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-51 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 52:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{                   //3455777 pin 2.5.6  //~0A17I~
        	{0,1,1, 1,2,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 3,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-52 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 53:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,1, 1,3,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 3,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-31 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 54:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,1, 1,2,1, 3,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-31 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 55:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{            //3445666 pin 2.4.5         //~0A17I~
        	{0,1,1, 2,1,3, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-31 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 56:                                                       //~0A17I~
        dupCtr=new int[][]{            //3445666 pin 2.4.5         //~0A17I~
        	{0,0,1, 3,1,3, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-31 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 57:                                                       //~0A17I~
        dupCtr=new int[][]{            //3445666 pin 2.4.5         //~0A17I~
        	{0,0,1, 2,2,3, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-31 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 58:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //3455678 man 2.5.8        //~0A17I~
        	{0,1,1, 1,2,1, 1,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-32 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 59:                                                       //~0A17I~
        dupCtr=new int[][]{             //3455678 man 2.5.8        //~0A17I~
        	{0,0,1, 1,3,1, 1,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-32 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 60:                                                       //~0A17I~
        dupCtr=new int[][]{             //3455678 man 2.5.8        //~0A17I~
        	{0,0,1, 1,2,1, 1,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-32 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 61:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{1,1,1, 1,1,1, 1,1,3},      //2345678999 man 1.2.4.5.7.8//~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-33 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 62:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,2,1, 1,1,1, 1,1,3},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-33 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 63:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,1,1, 2,1,1, 1,1,3},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-33 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 64:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,1,1, 1,2,1, 1,1,3},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-33 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 65:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,1,1, 1,1,1, 2,1,3},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-33 5              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 66:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,1,1, 1,1,1, 1,2,3},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-33 6              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 67:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{                 //3334445 sou 3.4.5.6  //~0A17I~
        	{0,0,4, 3,1,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-34 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 68:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,3, 4,1,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-34 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 69:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,3, 3,2,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-34 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 70:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,3, 3,1,1, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-34 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 71:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{                 //3334555 sou 2.3.4.5.6//~0A17I~
        	{0,1,3, 1,3,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-35 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 72:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,4, 1,3,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-35 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 73:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,3, 2,3,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-35 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 74:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,3, 1,4,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-35 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 75:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,3, 1,3,1, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-35 5              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 76:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{               //3335777 sou 4.5.6      //~0A17I~
        	{0,0,3, 1,1,0, 3,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-36 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 77:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,3, 0,2,0, 3,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-36 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 78:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,3, 0,1,1, 3,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-36 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 79:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{                  //3334455  sou 3.4.5.6//~0A17I~
        	{0,0,4, 2,2,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-37 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 80:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,3, 3,2,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-37 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 81:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,3, 2,3,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-37 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 82:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,3, 2,2,1, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-37 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 83:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //3344455 sou  3.4.5       //~0A17I~
        	{0,0,3, 3,2,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-38 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 84:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,2, 4,2,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-38 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 85:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,2, 3,3,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-38 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 86:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{           //4555567  man   3.4.6.7     //~0A17I~
        	{0,0,1, 1,4,1, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-39 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 87:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 2,4,1, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-39 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 88:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 1,4,2, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-39 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 89:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 1,4,1, 2,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-39 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 90:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{              //4555566 man    3.4.6.7  //~0A17I~
        	{0,0,1, 1,4,2, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-40 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 91:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 2,4,2, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-40 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 92:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 1,4,3, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-40 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 93:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 1,4,2, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-40 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 94:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{                 //5555667 man    4.6.7 //~0A17I~
        	{0,0,0, 1,4,2, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-41 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 95:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 0,4,3, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-41 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 96:                                                       //~0A17I~
        dupCtr=new int[][]{                                        //~0A17I~
        	{0,0,0, 0,4,2, 2,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-41 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 97:                                                       //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //5555677  man 4.6.7       //~0A17I~
        	{0,0,0, 1,4,1, 2,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-42 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 98:                                                       //~0A17I~
        dupCtr=new int[][]{             //5555677                  //~0A17I~
        	{0,0,0, 0,4,2, 2,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-42 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 99:                                                       //~0A17I~
        dupCtr=new int[][]{             //5555677                  //~0A17I~
        	{0,0,0, 0,4,1, 3,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-42 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 100:                                                      //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~0A17I~
        	{0,2,2, 2,4,2, 2,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-51 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 101:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~0A17I~
        	{0,1,2, 2,4,3, 2,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-51 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 102:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~0A17I~
        	{0,1,2, 2,4,2, 3,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-51 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 103:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~0A17I~
        	{0,1,2, 2,4,2, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-51 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 104:                                                      //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,2,2, 2,1,1, 4,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-52 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 105:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,1,2, 2,2,1, 4,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-52 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 106:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,1,2, 2,1,1, 4,2,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-52 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 107:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,1,3, 2,1,1, 4,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-52 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 108:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,1,2, 2,1,2, 4,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-52 5              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 109:                                                      //~0A17I~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A17I~
        	{0,1,2, 2,1,1, 4,1,2},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-52 6              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 110:                                                      //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~0A17I~
        	{2,2,2, 4,3,1, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-53 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 111:                                                      //~0A17I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~0A17I~
        	{1,2,2, 4,3,1, 1,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-53 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 112:                                                      //~0A17I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~0A17I~
        	{1,2,2, 4,4,1, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-53 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 113:                                                      //~0A17I~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~0A17I~
        	{1,2,2, 4,3,2, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-53 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 114:                                                      //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~0A17I~
        	{1,4,1, 1,1,4, 1,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-54 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 115:                                                      //~0A17I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~0A17I~
        	{0,4,1, 2,1,4, 1,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-54 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 116:                                                      //~0A17I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~0A17I~
        	{0,4,1, 1,1,4, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-54 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 117:                                                      //~0A17I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~0A17I~
        	{0,4,1, 1,2,4, 1,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-54 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 118:                                                      //~0A17I~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~0A17I~
        	{0,4,1, 1,1,4, 1,2,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-54 5              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 119:                                                      //~0A17I~
                                                                   //~0A17I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~0A17I~
        	{0,3,1, 3,2,2, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-55 1              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 120:                                                      //~0A17I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~0A17I~
        	{0,3,1, 2,2,2, 3,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-55 2              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 121:                                                      //~0A17I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~0A17I~
        	{0,3,2, 2,2,2, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-55 3              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 122:                                                      //~0A17I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~0A17I~
        	{0,3,1, 2,2,3, 2,1,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-55 4              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
//*********                                                        //~0A17I~
    case 123:                                                      //~0A17I~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~0A17I~
        	{0,3,1, 2,2,2, 2,1,1},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~0A17I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~0A17I~
	    rc=UARV.ronTestSub(dupCtr,0,0);                            //~0A17I~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-55 5              rc="+rc);//~0A17I~
        break;                                                     //~0A17I~
    default:                                                       //~0A17I~
        UView.showToastLong("testCase ERR="+testCase);             //~0A17I~
    }                                                              //~0A17I~
        return rc;                                                 //~0A17I~
    }                                                              //~0A17I~
	//*************************************************************************//~0A17I~
}//class                                                           //~v@@@R~
