//******************************************************************//~0B03I~
//2020/11/03 va27 Tenpai chk at Reach                              //~0B03I~
//******************************************************************//~0B03I~
package com.btmtest;
//******************************************************************//~0B01R~
import android.support.test.InstrumentationRegistry;               //~0A31R~
import android.support.test.runner.AndroidJUnit4;                  //~0A31I~
                                                                   //~0B03I~
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
//***********************************                              //~0A31R~
                                                                   //~0A31I~
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UARonValue;
import com.btmtest.utils.Dump;                                     //~0A31M~
                                                                   //~0A31M~
import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.game.GConst.*;                           //~0A31R~

//+0A31I~

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ITUAReachChk                                          //~0B03R~
{                                                                  //~0A31I~
	private AG AG;                                                 //~0B01I~
	//*************************************************************************//~0B01I~
    private void initEnv()                                         //~0B01I~
    {                                                              //~0B01I~
        AG=new AG();                                               //~0B01I~
        StaticVars.AG=AG;                                          //~0B01I~
    	AG.appName="ｅ雀";                                         //~0B01I~
    	AG.appNameE="eMahJong";                                    //~0B01I~
        AG.context=InstrumentationRegistry.getTargetContext();     //~0B01I~
        AG.resource=AG.context.getResources();                     //~0B01I~
        AG.dirSep="/";                                             //~0B01I~
                                                                   //~0B01I~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~0B01I~
//      Dump.open("");	//write all to Terminal log,not exception only//~0B01I~
        Dump.open("Dump.txt",true/*sdcard*/);                      //~0B01I~
                                                                   //~0B03I~
        AG.createSettings();                                       //~0B03I~
        AG.loadProp();                                             //~0B03I~
                                                                   //~0B01I~
        AG.aPlayers=new Players();                                 //~0B01I~
        AG.aAccounts=new Accounts();                               //~0B01I~
        AG.aStatus=new Status();                                   //~0B01I~
        AG.aUARonValue=new UARonValue();                           //~0B03I~
        if (Dump.Y) Dump.println("ITUAReachChk.constructor");      //+0B03R~
    }                                                              //~0B01I~
    //*************************************************************************//~0A31I~
    @Before                                                        //~0A31I~
    public void setUp() throws Exception                           //~0A31M~
    {                                                              //~0A31M~
    	initEnv();                                                 //~0B01I~
        if (Dump.Y) Dump.println("ITUAReachChk.setUp");            //+0B03R~
    }                                                              //~0A31M~
    @Test                                                          //~0B01I~
    public void testChkReachSub()                                  //~0B03R~
    {                                                              //~0B01I~
        setUpEnv();                                                //~0B01I~
        new ITUAReachChkSub().reachTest();                         //~0B03R~
    }                                                              //~0B01I~
    private void setUpEnv()                                             //~0B01I~
    {                                                              //~0B01I~
        Players PL=AG.aPlayers;                                    //~0B01I~
        PL.actionBeforeRon=GCM_TAKE;                               //~0B01I~
        PL.ctrTakenAll=1;                  //sim 1st take          //~0B01I~
    }                                                              //~0B01I~
}
