package com.btmtest;

//***********************************                              //~0A31I~
//import androidx.test.filters.InstrumentationRegistry;            //~0A31R~
import android.support.test.InstrumentationRegistry;               //~0A31R~
import android.support.test.runner.AndroidJUnit4;                  //~0A31I~
//import androidx.test.platform.app.InstrumentationRegistry;       //~0A31R~
//import androidx.test.ext.junit.runners.AndroidJUnit4;            //~0A31R~
//import androidx.test.runners.AndroidJUnit4;                      //~0A31I~
                                                                   //~0A31I~
//import androidx.test.platform.app.InstrumentationRegistry;       //~0A31I~
//import androidx.test.ext.junit.runners.AndroidJUnit4;            //~0A31I~

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
//***********************************                              //~0A31R~
import android.content.Context;                                    //~0A31M~
                                                                   //~0A31I~
import com.btmtest.game.UA.UARonChk;                               //~0A31M~
import com.btmtest.utils.Dump;                                     //~0A31M~
                                                                   //~0A31M~
import static com.btmtest.game.GConst.*;                           //~0A31R~

//+0A31I~

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ITUARonChk                                            //~0A31R~
{                                                                  //~0A31I~
                                                                   //+0A31I~
    private MainActivity mActivity;                                //+0A31I~
//*****************                                                //+0A31I~
    Context appContext = InstrumentationRegistry.getTargetContext();//~0A31I~
	UARonChk2 UARC2;                                               //~0A31I~
                                                                   //+0A31I~
    //*************************************************************************//~0A31M~
    @Rule                                                          //+0A31R~
//    public ActivityTestRule<MainActivity> mRule=                   //+0A31I~
//            new ActivityTestRule<>(MainActivity.class);            //+0A31I~
    //*************************************************************************//+0A31I~
    @Before                                                        //+0A31I~
    public void setUp() throws Exception                           //~0A31M~
    {                                                              //~0A31M~
        initMain();                                                //~0A31M~
        if (Dump.Y) Dump.println("TestUARonChk.setUp");            //~0A31M~
		UARC2=new UARonChk2();                                     //~0A31I~
    }                                                              //~0A31M~
    private void initMain()                                        //~0A31I~
    {                                                              //~0A31I~
//      MainActivity ma=new MainActivity();                        //~0A31R~
//        mActivity=mRule.getActivity();                             //+0A31I~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~0A31I~
        new StaticVars(mActivity);	//new AG().init(this);         //+0A31R~
        Dump.open("");	//write all to Terminal log,not exception only//~0A31I~
        if (Dump.Y) Dump.println("TestUARonChk.constructor");      //~0A31I~
    }                                                              //~0A31I~
	//*************************************************************************//~0A31I~
    @Test                                                          //~0A31I~
//  public boolean chkComplete(int Pplayer)                        //~0A31I~
    public void testChkComplete()                                  //~0A31I~
    {                                                              //~0A31I~
//        boolean rc;                                              //~0A31I~
//        player=Pplayer;                                          //~0A31I~
//        swAllInHand=isAllInHand();                               //~0A31I~
//        ctrPair=AG.aPlayers.getCtrPair(Pplayer);       //including Ron tile//~0A31I~
//        if (Dump.Y) Dump.println("UARonChk.chkComplete player="+Pplayer+",ctrPair="+ctrPair);//~0A31I~
//        TileData[] tds=AG.aPlayers.getHands(Pplayer);       //including Ron tile//~0A31I~
//        TileData tdRon=null;                                     //~0A31I~
//        if (!Tiles.isTakenStatus(tds.length))                    //~0A31I~
//            tdRon=AG.aPlayers.getTileCompleteSelectInfoRon();    //~0A31I~
        UARC2.ronTest();                                           //~0A31R~
//        sw1stTake=CompReqDlg.chk1stTake();                       //~0A31I~
//        sortTiles(tds,tdRon);                                    //~0A31I~
//        if ((TestOption.option2 & TestOption.TO2_RON_TEST)!=0) //TODO//~0A31I~
//        {                                                        //~0A31I~
//            int[][] testHand=UARonValue.getTestHandRonChk();   //by testcase//~0A31I~
//            if (testHand!=null)                                  //~0A31I~
//                dupCtr=testHand;                                 //~0A31I~
//        }                                                        //~0A31I~
//        rc=chkCompleteSub();                                     //~0A31I~
//        return rc;                                               //~0A31I~
    }                                                              //~0A31I~
	//*************************************************************************//~0A31I~
	class UARonChk2 extends UARonChk                               //~0A31I~
    {                                                              //~0A31I~
	//*************************************************************************//~0A31M~
        @Override                                                  //~0A31I~
        protected void init()                                      //~0A31I~
        {                                                          //~0A31I~
            sw7Pair4Pair=true;                                     //~0A31I~
            sw13NoPair=true;                                       //~0A31I~
            sw14NoPair=true;                                       //~0A31I~
        }                                                          //~0A31I~
        //*************************************************************************//~0A31R~
        public void ronTest()                                      //~0A31R~
        {                                                          //~0A31R~
            boolean rc;                                            //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest");          //~0A31R~
            ctrTileAll=HANDCTR_TAKEN;                              //~0A31R~
            dupCtr=new int[][]{   //4 anko                         //~0A31R~
                {3,3,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 3,3,0,0,  2,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-1 4Anko rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{           //7 pair                 //~0A31R~
                {2,2,0, 0,0,0, 0,0,0},                             //~0A31R~
                {2,2,2, 0,0,0, 2,0,2},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-2 7pair rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{           //7 pair err             //~0A31R~
                {2,2,0, 0,0,0, 0,0,0},                             //~0A31R~
                {2,2,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-3 7pair err rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {2,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {2,2,4, 2,2,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-4 4seq  rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,1,3, 4,3,1, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-5 4seq-3  rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {2,0,0, 0,0,0, 0,0,1},                             //~0A31R~
                {1,0,0, 0,0,0, 0,0,1},                             //~0A31R~
                {1,0,0, 0,0,0, 0,0,1},                             //~0A31R~
                { 1,1,1,1,  1,1,1, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-6 13All  rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,0,0, 0,0,0, 0,0,1},                             //~0A31R~
                {1,0,0, 0,0,0, 0,0,1},                             //~0A31R~
                {1,0,0, 0,0,0, 0,0,1},                             //~0A31R~
                { 1,1,1,1,  1,1,1, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-7 13All err1  rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,0,0, 0,0,0, 0,0,1},                             //~0A31R~
                {1,0,0, 0,0,0, 0,0,1},                             //~0A31R~
                {1,1,0, 0,0,0, 0,0,1},                             //~0A31R~
                { 1,1,1,1,  1,1,1, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-8 13All err2  rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {3,0,0, 0,0,0, 0,0,1},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,1},                             //~0A31R~
                {1,0,0, 0,0,0, 0,0,1},                             //~0A31R~
                { 1,1,1,1,  1,1,1, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-9 13All err3  rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,0,0, 1,0,0, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,1, 0,0,1},                             //~0A31R~
                {2,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 1,1,1,1,  1,1,1, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-10 13NoPair       rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {2,0,0, 1,0,0, 1,0,0},                             //~0A31R~
                {0,0,1, 0,0,1, 0,0,1},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 1,1,1,1,  1,1,1, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-11 13NoPair       rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,0,0, 1,0,0, 1,0,0},                             //~0A31R~
                {0,0,1, 0,0,1, 0,0,1},                             //~0A31R~
                {1,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 1,1,1,2,  1,0,1, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-12 13NoPair       rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,0,0, 1,0,0, 1,0,0},                             //~0A31R~
                {1,0,0, 0,0,1, 0,0,1},                             //~0A31R~
                {1,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 1,1,1,1,  1,1,1, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-13 13NoPair err1  rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,0,1, 0,0,0, 0,0,0},                             //~0A31R~
                {1,0,0, 0,0,1, 0,0,1},                             //~0A31R~
                {2,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 1,1,1,1,  1,1,1, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-14 13NoPair err2  rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,0,0, 1,0,0, 0,0,0},                             //~0A31R~
                {1,0,0, 0,0,1, 0,0,1},                             //~0A31R~
                {2,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  1,1,1, 0,0} };                         //7//~0A31R~
            ctrTileAll=11;                                         //~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            ctrTileAll=HANDCTR_TAKEN;                              //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-15 13NoPair err3  rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,0,0, 1,0,0, 1,0,0},                             //~0A31R~
                {0,0,1, 0,0,1, 0,0,1},                             //~0A31R~
                {0,1,0, 0,1,0, 0,1,0},                             //~0A31R~
                { 0,1,1,1,  1,1,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-16 14NoPair       rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,0,0, 1,0,0, 1,0,0},                             //~0A31R~
                {0,0,1, 0,0,1, 0,0,1},                             //~0A31R~
                {0,1,0, 0,1,0, 0,1,0},                             //~0A31R~
                { 0,2,0,1,  1,1,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-17 14NoPair err1  rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,0,1, 0,0,0, 1,0,0},                             //~0A31R~
                {0,0,1, 0,0,1, 0,0,1},                             //~0A31R~
                {0,1,0, 0,1,0, 0,1,0},                             //~0A31R~
                { 0,1,1,1,  1,1,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-18 14NoPair err2  rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{        //34567    2.5.8  sou       //~0A31R~
                {0,1,1, 1,1,1, 1,0,0},                             //~0A31R~
                {2,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-21 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,1, 1,2,1, 1,0,0},                             //~0A31R~
                {2,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-21 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,1, 1,1,1, 1,1,0},                             //~0A31R~
                {2,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-21 3              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                 //2345 pin  2.5    //~0A31R~
                {0,0,2, 1,1,1, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-22 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,1, 1,1,2, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-22 2              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                   //2345678 pin 2.5.8//~0A31R~
                {0,2,1, 1,1,1, 1,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-23 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,1, 1,2,1, 1,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-23 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,1, 1,1,1, 1,2,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-23 3              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{              //67888 gg    5.8.g   //~0A31R~
                {2,0,0, 0,1,1, 1,3,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-24 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {2,0,0, 0,0,1, 1,4,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-24 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {3,0,0, 0,0,1, 1,3,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-24 3              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                     //5666 zou 4.5.6//~0A31R~
                {0,0,0, 1,1,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-25 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,0, 0,2,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-25 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,0, 0,1,3, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-25 3              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                           //4666 sou 4.5.6//~0A31R~
                {0,0,0, 2,0,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-26 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,0, 1,1,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-26 1              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                     //2345666 pin 1.2.4.5.7//~0A31R~
                {1,1,1, 1,1,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-27 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,2,1, 1,1,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-27 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,1, 2,1,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-27 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,1, 1,2,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-27 4              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,1, 1,1,3, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-27 5              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                   //2345777 pin 2.5.6//~0A31R~
                {0,2,1, 1,1,0, 3,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-28 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,1, 1,2,0, 3,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-28 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,1, 1,1,1, 3,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-28 3              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                         //2333456 pin   1.2.4.7//~0A31R~
                {1,1,3, 1,1,1, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-29 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,2,3, 1,1,1, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-29 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,3, 2,1,1, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-29 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,3, 1,1,1, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-29 4              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                //3334568 pin 7.8   //~0A31R~
                {0,0,3, 1,1,1, 1,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-30 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 1,1,1, 0,2,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-30 2              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~0A31R~
                {0,1,1, 1,2,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-31 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~0A31R~
                {0,0,1, 2,2,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-31 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~0A31R~
                {0,0,1, 1,3,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-31 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                       //3455666 pin 2.4.5.7//~0A31R~
                {0,0,1, 1,2,3, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-31 4              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                   //3455777 pin 2.5.6//~0A31R~
                {0,1,1, 1,2,0, 3,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-31 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,1, 1,3,0, 3,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-31 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,1, 1,2,1, 3,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-31 3              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{            //3445666 pin 2.4.5     //~0A31R~
                {0,1,1, 2,1,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-31 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{            //3445666 pin 2.4.5     //~0A31R~
                {0,0,1, 3,1,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-31 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{            //3445666 pin 2.4.5     //~0A31R~
                {0,0,1, 2,2,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-31 3              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{             //3455678 man 2.5.8    //~0A31R~
                {0,1,1, 1,2,1, 1,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-32 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //3455678 man 2.5.8    //~0A31R~
                {0,0,1, 1,3,1, 1,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-32 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //3455678 man 2.5.8    //~0A31R~
                {0,0,1, 1,2,1, 1,2,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-32 3              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {1,1,1, 1,1,1, 1,1,3},      //2345678999 man 1.2.4.5.7.8//~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-33 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,2,1, 1,1,1, 1,1,3},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-33 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,1, 2,1,1, 1,1,3},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-33 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,1, 1,2,1, 1,1,3},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-33 4              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,1, 1,1,1, 2,1,3},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-33 5              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,1,1, 1,1,1, 1,2,3},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-33 6              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                 //3334445 sou 3.4.5.6//~0A31R~
                {0,0,4, 3,1,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-34 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 4,1,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-34 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 3,2,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-34 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 3,1,1, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-34 4              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                 //3334555 sou 2.3.4.5.6//~0A31R~
                {0,1,3, 1,3,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-35 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,4, 1,3,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-35 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 2,3,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-35 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 1,4,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-35 4              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 1,3,1, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-35 5              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{               //3335777 sou 4.5.6  //~0A31R~
                {0,0,3, 1,1,0, 3,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-36 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 0,2,0, 3,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-36 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 0,1,1, 3,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-36 3              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                  //3334455  sou 3.4.5.6//~0A31R~
                {0,0,4, 2,2,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-37 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 3,2,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-37 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 2,3,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-37 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,3, 2,2,1, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-37 4              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{             //3344455 sou  3.4.5   //~0A31R~
                {0,0,3, 3,2,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-38 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,2, 4,2,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-38 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,2, 3,3,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-38 3              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{           //4555567  man   3.4.6.7 //~0A31R~
                {0,0,1, 1,4,1, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-39 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,0, 2,4,1, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-39 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,0, 1,4,2, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-39 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,0, 1,4,1, 2,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-39 4              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{              //4555566 man    3.4.6.7//~0A31R~
                {0,0,1, 1,4,2, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-40 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,0, 2,4,2, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-40 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,0, 1,4,3, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-40 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,0, 1,4,2, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-40 4              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{                 //5555667 man    4.6.7//~0A31R~
                {0,0,0, 1,4,2, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-41 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,0, 0,4,3, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-41 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{                                    //~0A31R~
                {0,0,0, 0,4,2, 2,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-41 3              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{             //5555677  man 4.6.7   //~0A31R~
                {0,0,0, 1,4,1, 2,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-42 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //5555677              //~0A31R~
                {0,0,0, 0,4,2, 2,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-42 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //5555677              //~0A31R~
                {0,0,0, 0,4,1, 3,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-42 1              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{             //2334455556677  2.5.8.6.7//~0A31R~
                {0,2,2, 2,4,2, 2,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-51 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2334455556677  2.5.8.6.7//~0A31R~
                {0,1,2, 2,4,3, 2,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-51 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2334455556677  2.5.8.6.7//~0A31R~
                {0,1,2, 2,4,2, 3,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-51 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2334455556677  2.5.8.6.7//~0A31R~
                {0,1,2, 2,4,2, 2,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-51 4              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A31R~
                {0,2,2, 2,1,1, 4,1,1},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-52 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A31R~
                {0,1,2, 2,2,1, 4,1,1},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-52 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A31R~
                {0,1,2, 2,1,1, 4,2,1},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-52 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A31R~
                {0,1,3, 2,1,1, 4,1,1},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-52 4              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A31R~
                {0,1,2, 2,1,2, 4,1,1},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-52 5              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2334456777789  2.5.8.3.6.9//~0A31R~
                {0,1,2, 2,1,1, 4,1,2},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-52 6              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{             //1223344445556  1.7.5.6//~0A31R~
                {2,2,2, 4,3,1, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-53 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //1223344445556  1.7.5.6//~0A31R~
                {1,2,2, 4,3,1, 1,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-53 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //1223344445556  1.7.5.6//~0A31R~
                {1,2,2, 4,4,1, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-53 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //1223344445556  1.7.5.6//~0A31R~
                {1,2,2, 4,3,2, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-53 4              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{             //2222345666678  1.4.7.5.8//~0A31R~
                {1,4,1, 1,1,4, 1,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-54 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2222345666678  1.4.7.5.8//~0A31R~
                {0,4,1, 2,1,4, 1,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-54 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2222345666678  1.4.7.5.8//~0A31R~
                {0,4,1, 1,1,4, 2,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-54 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2222345666678  1.4.7.5.8//~0A31R~
                {0,4,1, 1,2,4, 1,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-54 4              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2222345666678  1.4.7.5.8//~0A31R~
                {0,4,1, 1,1,4, 1,2,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-54 5              rc="+rc);//~0A31R~
                                                                   //~0A31R~
            dupCtr=new int[][]{             //2223445566778  4.7.3.6.9//~0A31R~
                {0,3,1, 3,2,2, 2,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-55 1              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2223445566778  4.7.3.6.9//~0A31R~
                {0,3,1, 2,2,2, 3,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-55 2              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2223445566778  4.7.3.6.9//~0A31R~
                {0,3,2, 2,2,2, 2,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-55 3              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2223445566778  4.7.3.6.9//~0A31R~
                {0,3,1, 2,2,3, 2,1,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-55 4              rc="+rc);//~0A31R~
            dupCtr=new int[][]{             //2223445566778  4.7.3.6.9//~0A31R~
                {0,3,1, 2,2,2, 2,1,1},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                {0,0,0, 0,0,0, 0,0,0},                             //~0A31R~
                { 0,0,0,0,  0,0,0, 0,0} };                         //7//~0A31R~
            rc=chkCompleteSub();                                   //~0A31R~
            if (Dump.Y) Dump.println("UARonChk.ronTest-55 5              rc="+rc);//~0A31R~
                                                                   //~0A31R~
        }                                                          //~0A31R~
    }//class UARonChk2                                             //~0A31I~
}
