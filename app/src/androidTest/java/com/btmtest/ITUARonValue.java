//*CID://+vagrR~:                                   update#= 129;  //~vagrR~
//******************************************************************//~vafhI~
//2021/11/14 vagr (Bug of vafh)determins honchan when pillow:tanyao//~vagrI~
//2021/11/01 vafh (Bug) for HonChanta(TerminalMix),Dump to cache   //~vafhI~
//******************************************************************//~vafhI~
package com.btmtest;

//******************************************************************//~0B01R~
//*try non static method mocking                                   //~0B01I~
//******************************************************************//~0B01I~
//2021/08/25 vad5 move Dump.txt to cache to avoid /sdcard          //~vafhI~
//******************************************************************//~vafhI~
//import androidx.test.filters.InstrumentationRegistry;            //~0A31R~
import android.app.Activity;

import androidx.test.platform.app.InstrumentationRegistry;               //~0A31R~
import androidx.test.ext.junit.runners.AndroidJUnit4;                  //~0A31I~
//import androidx.test.platform.app.InstrumentationRegistry;       //~0A31R~
//import androidx.test.ext.junit.runners.AndroidJUnit4;            //~0A31R~
//import androidx.test.runners.AndroidJUnit4;                      //~0A31I~
                                                                   //~0A31I~
//import androidx.test.platform.app.InstrumentationRegistry;       //~0A31I~
//import androidx.test.ext.junit.runners.AndroidJUnit4;            //~0A31I~

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//***********************************                              //~0A31R~
                                                                   //~0A31I~
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UARon;
import com.btmtest.game.UserAction;
import com.btmtest.utils.Dump;                                     //~0A31M~
import com.btmtest.game.RA.RoundStat;                              //~vagrI~
                                                                   //~0A31M~
import static com.btmtest.TestOption.*;

//+0A31I~

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ITUARonValue                                          //~0B02R~
{                                                                  //~0A31I~
	private AG AG;                                                 //~0B01I~
    ITUARonValueSub UARVS;                                       //~0B02I~
    public static int MaxCase=0;                                   //~vagrR~
	//*************************************************************************//~0B01I~
    private void initEnv()                                         //~0B01I~
    {                                                              //~0B01I~
        AG=new AG();                                               //~0B01I~
        StaticVars.AG=AG;                                          //~0B01I~
    	AG.appName="ｅ雀";                                         //~0B01I~
    	AG.appNameE="eMahJong";                                    //~0B01I~
//      AG.context=InstrumentationRegistry.getTargetContext();     //~0B01I~//~1108R~
//      AG.context=InstrumentationRegistry.getInstrumentation().getContext();     //~0B01I~//~1108I~//~1310R~
        AG.context=InstrumentationRegistry.getInstrumentation().getTargetContext();//~1310I~
        AG.resource=AG.context.getResources();                     //~0B01I~
        AG.isDebuggable=true;                                      //~vagrI~
        AG.dirSep="/";                                             //~0B01I~
        AG.mainThread=Thread.currentThread();                         //~@@@@I~//~1Ad7R~//~v@@@R~//~1310I~
//      AG.aMainActivity=(MainActivity)AG.context;
                                                     //~0B01I~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~0B01I~
//      Dump.open("");	//write all to Terminal log,not exception only//~0B01I~
//      Dump.open("Dump.txt",true/*sdcard*/);                      //~0B01I~//~vafhR~
        Dump.open("Dump.txt",false/*sdcard*/);                     //~vafhI~
                                                                   //~0B02I~
        AG.createSettings();                                        //~0B02I~
        AG.loadProp();                                             //~0B02I~
                                                                   //~0B01I~
        AG.aPlayers=new Players();                                 //~0B01I~
        AG.aAccounts=new Accounts();                               //~0B01I~
        AG.aStatus=new Status();                                   //~0B01I~
        AG.aUARon=new UARon(new UserAction());                     //~0B02R~
        new RoundStat();                                           //~vagrI~
        if (Dump.Y) Dump.println("ITUARonValue.constructor");      //~0B02R~
    }                                                              //~0B01I~
    //*************************************************************************//~0A31I~
    @Before                                                        //~0A31I~
    public void setUp() throws Exception                           //~0A31M~
    {                                                              //~0A31M~
    	initEnv();                                                 //~0B01I~
        UARVS=new ITUARonValueSub();                               //~0B02I~
        UARVS.sw13WaitAll=true;                                    //~0B02R~
        UARVS.sw4WindDouble=true;                                  //~0B02I~
        UARVS.sw9GateDouble=true;                                  //~0B02I~
        if (Dump.Y) Dump.println("ITUARonValuse.setUp");           //~0B02R~
        UARVS.setTestAll(true);                                    //~0B02I~//~1310R~//~vagrR~
//      UARVS.setTestAll(false);                                   //~1310I~//~vagrR~
    }                                                              //~0A31M~
    @Test                                                          //~0B01I~
    public void testChkComplete()                                  //~0B01I~
    {                                                              //~0B01I~
      try                                                          //~vagrI~
      {                                                            //~vagrI~
        setUpEnv();                                                //~0B01I~
//      UARVS.ronTestValue(0/*case#*/); //start                    //~vagrI~
//      UARVS.ronTestValue(255041/*0*//*case#*/);                  //~0B02R~//~1310R~
//      UARVS.ronTestValue(23204/*case#*/);                        //~1310I~//~1407R~
//      UARVS.ronTestValue(23205/*case#*/);                        //~1310I~//~1407R~
//      UARVS.ronTestValue(23206/*case#*/);                        //~1310I~//~1407R~
//      UARVS.ronTestValue(20102/*case#*/);                        //~1407I~//~1408R~
//      UARVS.ronTestValue(20103/*case#*/);                        //~1407I~//~1408R~
//      UARVS.ronTestValue(20104/*case#*/);                        //~1408R~
//      UARVS.ronTestValue(28722/*case#*/);                        //~1408I~//~1717R~
//      UARVS.ronTestValue(23207/*case#*/);                        //~1717R~
//      UARVS.ronTestValue(23208/*case#*/);                        //~1717R~
//      UARVS.ronTestValue(23209/*case#*/);                        //~1717R~
//        UARVS.ronTestValue(232091/*case#*/);                       //~1717I~//~1B01R~
//        UARVS.ronTestValue(232092/*case#*/);                       //~1717I~//~1B01R~
//        UARVS.ronTestValue(232093/*case#*/);                       //~1717I~//~1B01R~
//        UARVS.ronTestValue(2201 /*case#*/);                        //~vafhI~//~vagrR~
//        UARVS.ronTestValue(22011/*case#*/);                      //~vagrR~
//        UARVS.ronTestValue(22012/*case#*/);                      //~vagrR~
//        UARVS.ronTestValue(22013/*case#*/);                        //~vafhI~//~vagrR~
//          UARVS.ronTestValue(2202 /*case#*/);                    //~vagrR~
//          UARVS.ronTestValue(22021/*case#*/);                    //~vagrR~
//          UARVS.ronTestValue(22022/*case#*/);                    //~vagrR~
//          UARVS.ronTestValue(22031/*case#*/);                        //~1B01I~//~vafhR~//~vagrR~
//          UARVS.ronTestValue(22032/*case#*/);                        //~1B01I~//~vafhR~//~vagrR~
//          UARVS.ronTestValue(22033/*case#*/);                        //~1B01I~//~vafhR~//~vagrR~
//          UARVS.ronTestValue(22034/*case#*/);                        //~1B01I~//~vafhR~//~vagrR~
//          UARVS.ronTestValue(220341/*case#*/);                   //~vagrR~
//          UARVS.ronTestValue(22035/*case#*/);                        //~1B01I~//~vafhR~//~vagrR~
//          UARVS.ronTestValue(22036/*case#*/);                    //~vagrR~
//          UARVS.ronTestValue(22037/*case#*/);                    //~vagrR~
//          UARVS.ronTestValue(22038/*case#*/);                    //~vagrR~
//          UARVS.ronTestValue(22039/*case#*/);                    //~vagrR~
//          UARVS.ronTestValue(231052/*case#*/);                   //~vagrR~
//          UARVS.ronTestValue(231053/*case#*/);                   //~vagrR~
//          UARVS.ronTestValue(231054/*case#*/);                   //~vagrR~
//          UARVS.ronTestValue(231055/*case#*/);                   //~vagrR~
//          UARVS.ronTestValue(231056/*case#*/);                   //~vagrR~
//          UARVS.ronTestValue(231057/*case#*/);                   //~vagrR~
//          UARVS.ronTestValue(231058/*case#*/);                   //~vagrR~
//          UARVS.ronTestValue(231059/*case#*/);                   //~vagrR~
//          UARVS.ronTestValue(2310591/*case#*/);                  //~vagrR~
//            UARVS.ronTestValue(23321/*case#*/);                  //~vagrR~
//            UARVS.ronTestValue(233211/*case#*/);                 //~vagrR~
//            UARVS.ronTestValue(23322/*case#*/);                  //~vagrR~
//            UARVS.ronTestValue(23323/*case#*/);                  //~vagrR~
//            UARVS.ronTestValue(23324/*case#*/);                  //~vagrR~
//            UARVS.ronTestValue(23325/*case#*/);                  //~vagrR~
//            UARVS.ronTestValue(23326/*case#*/);                  //~vagrR~
//              UARVS.ronTestValue(2332601/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332602/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332603/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332604/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332605/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332606/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332607/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332608/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332609/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332610/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332611/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332612/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332613/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332614/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332615/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332701/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332801/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2332901/*case#*/);              //~vagrR~
//MaxCase=2333100 ;      UARVS.ronTestValue(2333001/*case#*/);   //help test//~vagrR~
//              UARVS.ronTestValue(2333013/*case#*/);              //~vagrR~
//              UARVS.ronTestValue(2333113/*case#*/);  //3tonko    //~vagrR~
//              UARVS.ronTestValue(2333201/*case#*/);   //3windNoHonor//~vagrR~
//  MaxCase=22901300;   UARVS.ronTestValue(22901201/*case#*/);   //honor//~vagrR~
//                UARVS.ronTestValue(22901301/*case#*/); //straight3//~vagrR~
//axCase=22901500;   UARVS.ronTestValue(22901401/*case#*/); //straight3 chkEarth//~vagrR~
//MaxCase=22901600;      UARVS.ronTestValue(22901501/*case#*/); //3shiki    chkEarth//~vagrR~
//xCase=22901700;  UARVS.ronTestValue(22901601/*case#*/); //straight    chkEarth//~vagrR~
// MaxCase=22901800;            UARVS.ronTestValue(22901701/*case#*/); //straight3   chkEarth//~vagrR~
//   MaxCase=22901900;  UARVS.ronTestValue(22901801/*case#*/); //honor//~vagrR~
//MaxCase=22902000 ;      UARVS.ronTestValue(22901901/*case#*/);   //3WindNoHonor earth//~vagrR~
//MaxCase=22902100 ;      UARVS.ronTestValue(22902001/*case#*/);   //3kan earth//~vagrR~
//  MaxCase=22902200 ;      UARVS.ronTestValue(22902101/*case#*/);   //little dragon//~vagrR~
//xCase=22902210 ;      UARVS.ronTestValue(22902201/*case#*/);   //3anko//~vagrR~
MaxCase=22902400 ;      UARVS.ronTestValue(22902301/*case#*/);   //other multiwaittake//+vagrR~
//MaxCase=22902500 ;      UARVS.ronTestValue(22902401/*case#*/);   //help test//~vagrR~
//  MaxCase=22902600 ;      UARVS.ronTestValue(22902501/*case#*/);   //3renpon//~vagrR~
//MaxCase=22902700 ;      UARVS.ronTestValue(22902601/*case#*/);   //3dupseq//~vagrR~
//MaxCase=22902700 ;      UARVS.ronTestValue(22902609/*case#*/);   //3dupseq//~vagrR~
        }                                                          //~vagrI~
        catch(Exception e)                                         //~vagrI~
        {                                                          //~vagrI~
            Dump.printlnNoMsg(e,"ITUARonValue");                   //~vagrR~
        }                                                          //~vagrI~
    }                                                              //~0B01I~
    private void setUpEnv()                                             //~0B01I~
    {                                                              //~0B01I~
        TestOption.option2|=TO2_RONVALUE_NODORA;    //skip CompDlgDora//~0B02I~
    }                                                              //~0B01I~
}
