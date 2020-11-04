//*CID://+DATER~: update#= 825;                                    //~va11R~//~0A11R~
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
public RonResult ronTestValue()                                    //~0B02R~
{                                                                  //~0B02R~
//*************************************************************************//~0B02R~
    RonResult rc=null;                                             //~0B02R~
    if (Dump.Y) Dump.println("UARonValueTest.ronTestValue");              //~9C12I~//~0B02R~
    if ((TestOption.option2 & TestOption.TO2_CHKRANK)==0)          //+0B02M~
	    UView.showToastLong("testCase="+testCase);                 //~va11I~//~0A11R~//+0B02M~
//*********                                                        //~va11I~
    switch(testCase)                                               //~va11I~
    {                                                              //~va11I~
    case 999901:                                                   //~0B02R~
        dupCtr=new int[][]{           //7 pair                     //~va11M~
        	{0,2,2, 0,0,0, 0,0,0},                                 //~9C12I~//~va11M~
        	{0,2,2, 0,0,0, 0,0,0},                                 //~9C12I~//~va11M~
        	{0,2,2, 2,0,0, 0,0,0},                                 //~9C12I~//~va11M~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~//~va11M~
	    rc=UARV.ronTestSub(dupCtr,0,0);                                       //~9C12I~//~va11M~
        if (Dump.Y) Dump.println("UARonValueTest.ronTest-3 7pair tanyao rc="+rc.toString());//~9C12R~//~va11M~
        break;                                                     //~va11I~
//*********                                                        //~0A17I~
    case 999902:                                                   //~0B02I~
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
    default:                                                       //~0A11I~
        UView.showToastLong("testCase ERR="+testCase);             //+0B02I~
    }                                                              //~va11I~
    return rc;                                                     //~0B02R~
}                                                                  //~0B02R~
}//class                                                           //~v@@@R~
