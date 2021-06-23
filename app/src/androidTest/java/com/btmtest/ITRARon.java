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
import com.btmtest.game.RA.RoundStat;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UARon;
import com.btmtest.game.UserAction;
import com.btmtest.utils.Dump;                                     //~0A31M~
                                                                   //~0A31M~
import static com.btmtest.ITMock.*;
import static com.btmtest.TestOption.*;
import static com.btmtest.game.GConst.*;

//+0A31I~

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ITRARon                                          //~0B02R~//~1607R~//~1611R~
{                                                                  //~0A31I~
	private AG AG;                                                 //~0B01I~
    ITRARonSub UARVS;                                      //~0B02I~//~1607R~//~1611R~
	//*************************************************************************//~0B01I~
    private void initEnv()                                         //~0B01I~
    {                                                              //~0B01I~
        TestOption.option3 |= TO3_IT_RARON;                        //~1612I~
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
        AG.createSettings();                                        //~0B02I~//~1611M~
        AG.loadProp();                                             //~0B02I~//~1611M~
                                                                   //~1611I~
        ITMock itm=new ITMock();                                   //~1611I~
        int flag=ITM_DICEBOX|ITM_ACCOUNTS|ITM_POINTSTICK|ITM_USERACTION;//~1611R~
        itm.init(flag,0);                                          //~1611I~
                                                                   //~0B01I~
        AG.aPlayers=new Players();                                 //~0B01I~
        AG.aAccounts=new Accounts();                               //~0B01I~
        AG.aStatus=new Status();                                   //~0B01I~
        initACC();                                                 //~1611R~
        AG.aUARon=new UARon(new UserAction());                     //~0B02R~
        new RoundStat();                                            //~1611I~
        AG.aRoundStat.newGame(true,0,0,0);                         //~1611I~
        if (Dump.Y) Dump.println("ITRARon.constructor");      //~0B02R~//~1607R~//~1611R~
    }                                                              //~0B01I~
    private void initACC()                                         //~1611I~
    {                                                              //~1611I~
        if (Dump.Y) Dump.println("Accounts.init");                 //~1611I~
    	boolean rc=true;                                           //~1611I~
        Accounts.Account[] accounts=new Accounts.Account[PLAYERS]; //~1611I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~1611I~
        {                                                          //~1611I~
        	accounts[ii]=AG.aAccounts.newAccount(ii);              //~1611I~
        }                                                          //~1611I~
		Accounts.Account[] byESWN=new Accounts.Account[PLAYERS];   //~1611I~
        for (int ii=0;ii<PLAYERS;ii++)                             //~1611I~
        {                                                          //~1611I~
        	byESWN[ii]=accounts[ii];                               //~1611I~
        }                                                          //~1611I~
		AG.aAccounts.accounts=accounts;                            //~1611I~
		AG.aAccounts.accountsByESWN=byESWN;                        //~1611I~
        AG.aAccounts.setCurrentAccountsByESWN();                   //~1611I~
    }                                                              //~1611I~
    //*************************************************************************//~0A31I~
    @Before                                                        //~0A31I~
    public void setUp() throws Exception                           //~0A31M~
    {                                                              //~0A31M~
    	initEnv();                                                 //~0B01I~
        UARVS=new ITRARonSub();                               //~0B02I~//~1607R~//~1611R~
       if (Dump.Y) Dump.println("ITUARonValuse.setUp");           //~0B02R~
        UARVS.setTestAll(true);                                    //~0B02I~//~1310R~//~1607R~//~1613R~
//      UARVS.setTestAll(false);                                   //~1310I~//~1607R~//~1613R~
    }                                                              //~0A31M~
    @Test                                                          //~0B01I~
    public void testChkComplete()                                  //~0B01I~
    {                                                              //~0B01I~
        setUpEnv();                                                //~0B01I~
//        UARVS.ronTestValue(0);                      //~1408I~       //~1607R~//~1613R~
//      UARVS.ronTestValue(101);     //3shiki                      //~1607R~//~1608R~//~1609R~//~1610R~//~1611R~//~1613R~//~1617R~
//      UARVS.ronTestValue(201);     //ittsu                       //~1607R~//~1611R~
//      UARVS.ronTestValue(301);     //3tonko                      //~1607R~//~1611R~
//      UARVS.ronTestValue(401);     //3kan                        //~1607R~//~1609R~
//      UARVS.ronTestValue(501);     //3dragonSmall                //~1607I~//~1608R~//~1611R~
//      UARVS.ronTestValue(601);     //honor tile                  //~1610R~//~1611R~//~1612R~
//    UARVS.ronTestValue(701);     //kataagari sample            //~1612R~//~1613R~
//      UARVS.ronTestValue(70942);     //kataagari sample          //~1612I~//~1613R~
      UARVS.ronTestValue(8043);     //kataagari human               //~1617I~//+1623R~
    }                                                              //~0B01I~
    private void setUpEnv()                                             //~0B01I~
    {                                                              //~0B01I~
        TestOption.option2|=TO2_RONVALUE_NODORA;    //skip CompDlgDora//~0B02I~
    }                                                              //~0B01I~
}
