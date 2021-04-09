package com.btmtest;

//******************************************************************//~0B01R~
//*try non static method mocking                                   //~0B01I~
//******************************************************************//~0B01I~
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
        AG.dirSep="/";                                             //~0B01I~
        AG.mainThread=Thread.currentThread();                         //~@@@@I~//~1Ad7R~//~v@@@R~//~1310I~
//      AG.aMainActivity=(MainActivity)AG.context;
                                                     //~0B01I~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~0B01I~
//      Dump.open("");	//write all to Terminal log,not exception only//~0B01I~
        Dump.open("Dump.txt",true/*sdcard*/);                      //~0B01I~
                                                                   //~0B02I~
        AG.createSettings();                                        //~0B02I~
        AG.loadProp();                                             //~0B02I~
                                                                   //~0B01I~
        AG.aPlayers=new Players();                                 //~0B01I~
        AG.aAccounts=new Accounts();                               //~0B01I~
        AG.aStatus=new Status();                                   //~0B01I~
        AG.aUARon=new UARon(new UserAction());                     //~0B02R~
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
//      UARVS.setTestAll(true);                                    //~0B02I~//~1310R~
        UARVS.setTestAll(false);                                   //~1310I~
    }                                                              //~0A31M~
    @Test                                                          //~0B01I~
    public void testChkComplete()                                  //~0B01I~
    {                                                              //~0B01I~
        setUpEnv();                                                //~0B01I~
//      UARVS.ronTestValue(255041/*0*//*case#*/);                  //~0B02R~//~1310R~
//      UARVS.ronTestValue(23204/*case#*/);                        //~1310I~//~1407R~
//      UARVS.ronTestValue(23205/*case#*/);                        //~1310I~//~1407R~
//      UARVS.ronTestValue(23206/*case#*/);                        //~1310I~//~1407R~
//      UARVS.ronTestValue(20102/*case#*/);                        //~1407I~//+1408R~
//      UARVS.ronTestValue(20103/*case#*/);                        //~1407I~//+1408R~
//      UARVS.ronTestValue(20104/*case#*/);                        //+1408R~
        UARVS.ronTestValue(28722/*case#*/);                        //+1408I~
    }                                                              //~0B01I~
    private void setUpEnv()                                             //~0B01I~
    {                                                              //~0B01I~
        TestOption.option2|=TO2_RONVALUE_NODORA;    //skip CompDlgDora//~0B02I~
    }                                                              //~0B01I~
}
