//*CID://+va1aR~: update#= 772;                                    //~va1aR~
//**********************************************************************//~v101I~
//2020/10/19 va1a drop ronchk option,1han constraint only          //~va1aI~
//2020/10/10 va14 (BUG)7pairwith Kan is err even optio allow it    //~va14I~
//2020/09/25 va11:optionally evaluate point                        //~va11I~
//v@@6 20190129 send ctrRemain and eswn                            //~v@@6I~
//v@@5 20190126 player means position on the device                //~v@@5I~
//**********************************************************************//~1107I~
package com.btmtest;                                          //~va1aR~

import com.btmtest.game.UA.UAReachChk;                        //~va1aR~
import com.btmtest.utils.Dump;
import static com.btmtest.game.GConst.*;                           //~va1aM~
import static org.junit.Assert.*;

public class ITUAReachChkSub extends UAReachChk                    //~va1aR~
{                                                                  //~va1aI~
	private AG AG=StaticVars.AG;                                   //~va1aI~
    @Override                                                      //~va1aI~
    protected void init()                                               //~va1aI~
    {                                                              //~va1aI~
        sw7Pair4Pair=true;                                         //~va1aI~
    }                                                              //~va1aI~
	//*************************************************************************//~va1aI~
	private void setUpRonTest()                                    //~va1aR~
    {                                                              //~va1aI~
     	swAllInHand=true;                                          //~va1aI~
        UARV=AG.aUARonValue;                                       //~va1aI~
	    swListAll=true;	//protect for ITUAReachChk                 //+va1aI~
    }                                                              //~va1aI~
	//*************************************************************************//~9C12I~
	public void reachTest()                                        //~va1aR~
    {                                                              //~9C12I~
		setUpRonTest();                                            //~va1aI~
                                                                   //~va1aI~
	    boolean rc;
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest");     //~va1aR~
		ctrTileAll=HANDCTR_TAKEN;                                  //~9C12I~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                                        //~va1aI~
        	{0,2,1, 2,2,1, 1,1,1},           //2man                //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va1aI~
        	{0,0,0, 2,0,0, 0,0,0},           //4so                 //~va1aR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va1aI~
	    rc=chkReachSub();                                          //~va1aI~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-201              rc="+rc);//~va1aR~
        assertTrue("reachTest-201",rc);                            //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                                        //~va1aI~
        	{0,2,1, 2,2,1, 1,1,1},         //2man                  //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va1aI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va1aI~
    	    { 0,0,0,2,  0,0,0, 0,0} };      //pei                   //7//~va1aR~
	    rc=chkReachSub();                                          //~va1aI~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-202              rc="+rc);//~va1aR~
        assertTrue("reachTest-202",rc);                            //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                                        //~va1aI~
        	{0,3,1, 1,0,0, 1,1,1},      //2,5,man                  //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va1aI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va1aI~
    	    { 0,0,0,2,  0,0,0, 0,0} };   //pei                     //~va1aR~
	    rc=chkReachSub();                                          //~va1aI~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-203              rc="+rc);//~va1aR~
        assertTrue("reachTest-203",rc);                            //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{   //4 anko  haku                       //~va1aR~
        	{3,3,0, 0,0,0, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~
    	    { 3,3,0,0,  1,0,0, 0,0} };                             //~va1aR~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-1 4Anko rc="+rc);//~va1aR~
        assertTrue("reachTest-1",rc);                              //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{   //4 anko     2,3,4,5sou              //~va1aR~
        	{3,3,0, 0,0,0, 0,0,0},                                 //~va1aI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va1aI~
        	{0,3,3, 1,0,0, 0,0,0},                                 //~va1aR~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va1aI~
	    rc=chkReachSub();                                          //~va1aI~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-1-2 4Anko rc="+rc);//~va1aR~
        assertTrue("reachTest-1-2",rc);                            //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{           //7 pair                     //~9C12I~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{2,2,2, 0,0,0, 1,0,2},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-2 7pair rc="+rc);//~va1aR~
        assertTrue("reachTest-2",rc);                              //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{           //7 pair                     //~va1aI~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~va1aI~
        	{2,2,2, 0,0,0, 0,0,2},                                 //~va1aI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va1aI~
    	    { 0,0,0,0,  0,0,1, 0,0} };                         //7 //~va1aI~
	    rc=chkReachSub();                                          //~va1aI~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-2-2 7pair rc="+rc);//~va1aR~
        assertTrue("reachTest-2-2",rc);                            //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{           //7 pair err                 //~9C12I~
        	{2,2,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{2,2,2, 0,0,0, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  1,1,1, 0,0} };                         //7 //~va1aR~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-3 7pair err rc="+rc);//~va1aR~
        assertFalse("reachTest-3 7pair err",rc);                   //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                                        //~9C12R~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{2,2,3, 2,2,0, 0,0,0},        //2,5pin                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-4 4seq  rc="+rc);//~va1aR~
        assertTrue("reachTest-4",rc);                              //~va1aR~
//****                                                             //~va1aR~
        dupCtr=new int[][]{          //4pin                        //~va1aR~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~va1aI~
        	{2,2,4, 1,2,0, 0,0,0},                                 //~va1aI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va1aI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va1aI~
	    rc=chkReachSub();                                          //~va1aI~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-4-2 4seq  rc="+rc);//~va1aR~
        assertTrue("reachTest-4-2",rc);                            //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{          //1,3,4,5,7,8                 //~va1aR~
        	{1,1,3, 3,3,1, 1,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12R~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-5 4seq-3  rc="+rc);//~va1aR~
        assertTrue("reachTest-5",rc);                              //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~va1aR~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-6 13All 13wait rc="+rc);//~va1aR~
        assertTrue("reachTest-6",rc);                              //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                                        //~va1aI~
        	{2,0,0, 0,0,0, 0,0,1},                                 //~va1aI~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~va1aI~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~va1aI~
    	    { 0,1,1,1,  1,1,1, 0,0} };                         //7 //~va1aI~
	    rc=chkReachSub();                                          //~va1aI~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-6-2 13All  rc="+rc);//~va1aR~
        assertTrue("reachTest-6-2",rc);                            //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,0, 0,0,0, 0,0,1},                                 //~va1aR~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                             //~va1aR~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-7 13All err1  rc="+rc);//~va1aR~
        assertFalse("reachTest-7 13all err1 14nopair",rc);         //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                                        //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,1,0, 0,0,0, 0,0,0},                                 //~va1aR~
    	    { 2,1,1,1,  1,1,1, 0,0} };                             //~va1aR~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-8 13All rc="+rc);//~va1aR~
        assertTrue("reachTest-8 13all err2",rc);                   //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                                        //~va1aI~
        	{0,1,0, 0,0,0, 0,0,1},                                 //~va1aI~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~va1aI~
        	{1,1,0, 0,0,0, 0,0,0},                                 //~va1aI~
    	    { 2,1,1,1,  1,1,1, 0,0} };                             //~va1aI~
	    rc=chkReachSub();                                          //~va1aI~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-8-2 13All err2 rc="+rc);//~va1aR~
        assertFalse("reachTest-8-2 13all err2",rc);                //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{          //1pin                        //~va1aR~
        	{2,0,0, 0,0,0, 0,0,1},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
        	{1,0,0, 0,0,0, 0,0,1},                                 //~9C12I~
    	    { 1,1,1,1,  1,1,1, 0,0} };                             //~va1aR~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-9 13All rc="+rc);//~va1aR~
        assertTrue("reachTest-9 13all",rc);                        //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{        //34567    2.5.8  sou           //~9C12I~
        	{0,0,1, 1,1,1, 1,0,0},                                 //~va1aR~
        	{2,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-21 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-21-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                 //2345 pin  2.5        //~9C12I~
        	{0,1,1, 1,1,0, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-22 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-22-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                   //2345678 pin 2.5.8  //~9C12I~
        	{0,1,1, 1,1,1, 1,1,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-23 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-23-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{              //67888 gg   1,5.8.       //~va1aR~
        	{2,0,0, 0,0,1, 1,3,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-24 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-24-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                     //5666 zou 4.5.7   //~va1aR~
        	{0,0,0, 0,1,3, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-25 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-25-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                           //4666 sou 4.5//~va1aR~
        	{0,0,0, 1,0,3, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-26 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-26-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                     //2345666 pin 1.2.4.5.7//~9C12I~
        	{0,1,1, 1,1,3, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-27 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-27-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                   //2345777 pin 2.5.6  //~9C12I~
        	{0,1,1, 1,1,0, 3,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-28 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-28-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                         //2333456 pin   1.2.4.7//~9C12I~
        	{0,1,3, 1,1,1, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-29 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-29-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                //3334568 pin 7.8       //~9C12I~
        	{0,0,3, 1,1,1, 0,1,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-30 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-30-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~9C12I~
        	{0,0,1, 1,2,3, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-31 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-31-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                   //3455777 pin 2.5.6  //~9C12I~
        	{0,0,1, 1,2,0, 3,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-31 5              rc="+rc);//~va1aR~
        assertTrue("reachTest-31-5",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{            //3445666 pin 2.4.5         //~9C12R~
        	{0,0,1, 2,1,3, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-31 8              rc="+rc);//~va1aR~
        assertTrue("reachTest-31-8",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{             //3455678 man 2.5.8        //~9C12I~
        	{0,0,1, 1,2,1, 1,1,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-32 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-32-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                                        //~9C12I~
        	{0,1,1, 1,1,1, 1,1,3},      //2345678999 man 1.2.4.5.7.8//~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-33 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-33-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                 //3334445 sou 3.4.5.6  //~9C12I~
        	{0,0,3, 3,1,0, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-34 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-34-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                 //3334555 sou 2.3.4.5.6//~9C12I~
        	{0,0,3, 1,3,0, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-35 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-35-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{               //3335777 sou 4.5.6      //~9C12I~
        	{0,0,3, 0,1,0, 3,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-36 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-36-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                  //3334455  sou 3.4.5.6//~9C12I~
        	{0,0,3, 2,2,0, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-37 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-37-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{             //3344455 sou  3.4.5       //~9C12I~
        	{0,0,2, 3,2,0, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-38 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-38-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{           //4555567  man   3.4.6.7     //~9C12I~
        	{0,0,0, 1,4,1, 1,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-39 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-39-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{              //4555566 man    3.4.6.7  //~9C12I~
        	{0,0,0, 1,4,2, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-40 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-40-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{                 //5555667 man    4.6.7 //~9C12I~
        	{0,0,0, 0,4,2, 1,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-41 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-41-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{             //5555677  man 4.6.7       //~9C12I~
        	{0,0,0, 0,4,1, 2,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-42 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-42-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{             //2334455556677  2.5.8.6.7 //~9C12I~
        	{0,1,2, 2,4,2, 2,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-51 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-51-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~9C12I~
        	{0,1,2, 2,1,1, 4,1,1},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-52 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-52-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{             //1223344445556  1.7.5.6   //~9C12I~
        	{1,2,2, 4,3,1, 0,0,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-53 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-53-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{             //2222345666678  1.4.7.5.8 //~9C12I~
        	{0,4,1, 1,1,4, 1,1,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-54 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-54-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{             //2223445566778  4.7.3.6.9 //~9C12I~
        	{0,3,1, 2,2,2, 2,1,0},                                 //~va1aR~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~9C12I~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~9C12I~
	    rc=chkReachSub();                                          //~va1aR~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-55 1              rc="+rc);//~va1aR~
        assertTrue("reachTest-55-1",rc);                           //~va1aR~
//****                                                             //~va1aI~
        dupCtr=new int[][]{             //1--9 9gate               //~va1aI~
        	{3,1,1, 1,1,1, 1,1,3},                                 //~va1aI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va1aI~
        	{0,0,0, 0,0,0, 0,0,0},                                 //~va1aI~
    	    { 0,0,0,0,  0,0,0, 0,0} };                         //7 //~va1aI~
	    rc=chkReachSub();                                          //~va1aI~
        if (Dump.Y) Dump.println("ITUAReachChkSub.reachTest-56 9gate 9wait    rc="+rc);//~va1aI~
        assertTrue("reachTest-55-1",rc);                           //~va1aI~
    }                                                              //~va1aI~
                                                          //~va1aI~
}//ITUARachChkSub                                                  //~va1aR~
