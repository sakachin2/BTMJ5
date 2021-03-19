package com.btmtest;

//***********************************                              //~0A31I~
//*success without static method mockking                          //~0B01I~
//***********************************                              //~0B01I~
//import androidx.test.filters.InstrumentationRegistry;            //~0A31R~
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
import com.btmtest.utils.Dump;                                     //~0A31M~
                                                                   //~0A31M~


//+0A31I~

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ITUARonChk4                                           //~0B01R~
{                                                                  //~0A31I~
	private AG AG;                                                 //~0B01I~
	//*************************************************************************//~0B01I~
    private void initEnv()                                         //~0B01I~
    {                                                              //~0B01I~
        AG=new AG();                                               //~0B01I~
        StaticVars.AG=AG;                                          //~0B01I~
    	AG.appName="ｅ雀";                                         //~0B01I~
    	AG.appNameE="eMahJong";                                    //~0B01I~
//      AG.context=InstrumentationRegistry.getTargetContext();     //~0B01I~//~1108R~
//      AG.context=InstrumentationRegistry.getInstrumentation().getContext();     //~0B01I~//~1108I~//+1111R~
        AG.context=InstrumentationRegistry.getInstrumentation().getTargetContext();//+1111I~
        AG.resource=AG.context.getResources();                     //~0B01I~
        AG.dirSep="/";                                             //~0B01I~
                                                                   //~0B01I~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~0B01I~
//      Dump.open("");	//write all to Terminal log,not exception only//~0B01I~
        Dump.open("Dump.txt",true/*sdcard*/);                      //~0B01I~
                                                                   //~0B01I~
        AG.aPlayers=new Players();                                 //~0B01I~
        AG.aAccounts=new Accounts();                               //~0B01I~
        AG.aStatus=new Status();                                   //~0B01I~
        if (Dump.Y) Dump.println("ITUARonChk2.constructor");       //~0B01I~
    }                                                              //~0B01I~
    //*************************************************************************//~0A31I~
    @Before                                                        //~0A31I~
    public void setUp() throws Exception                           //~0A31M~
    {                                                              //~0A31M~
    	initEnv();                                                 //~0B01I~
        if (Dump.Y) Dump.println("TestUARonChk4.setUp");           //~0B01R~
    }                                                              //~0A31M~
    @Test                                                          //~0B01I~
    public void testChkComplete()                                  //~0B01I~
    {                                                              //~0B01I~
        new ITUARonChkSub().ronTest();                             //~0B01I~
    }                                                              //~0B01I~
}
