//*CID://+va1aR~: update#= 757;                                    //~va1aR~
//**********************************************************************//~v101I~
//**********************************************************************//~1107I~
package com.btmtest;                                          //~va1aR~

import android.support.test.InstrumentationRegistry;               //~va1aI~
import android.support.test.runner.AndroidJUnit4;                  //~va1aI~

import static org.mockito.Mockito.*;                               //~va1aI~

import android.content.Context;                                    //~va1aI~
                                                                   //~va1aI~
import com.btmtest.dialog.CompReqDlg;
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.Status;
import com.btmtest.utils.Dump;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;                                   //~va1aI~
import org.mockito.InjectMocks;                                    //~va1aI~
                                                                   //~va1aI~
import org.mockito.Mockito;                                        //~va1aR~
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;                     //~va1aI~
import org.powermock.core.classloader.annotations.PrepareForTest;  //~va1aI~
import org.powermock.modules.junit4.PowerMockRunner;               //~va1aM~
                                                                   //~va1aI~
//import org.mockito.Mockito;                                      //~va1aR~
import static org.mockito.Mockito.*;                               //~va1aI~

//****************************************************             //~9C11I~
@RunWith(PowerMockRunner.class)                                    //~va1aI~
@PrepareForTest({CompReqDlg.class})                                //~va1aI~
public class ITUARonChk2                                           //~va1aR~
{                                                                  //~0914I~
	private AG AG;                                                 //~va1aM~
	//*************************************************************************//~va1aM~
    private void initEnv()                                         //~va1aM~
    {                                                              //~va1aM~
        AG=new AG();                                               //~va1aM~
        StaticVars.AG=AG;                                          //~va1aM~
    	AG.appName="ｅ雀";                                         //~va1aM~
    	AG.appNameE="eMahJong";                                    //~va1aM~
        AG.context=InstrumentationRegistry.getTargetContext();     //~va1aM~
        AG.resource=AG.context.getResources();                     //~va1aM~
        AG.dirSep="/";                                             //~va1aM~
                                                                   //~va1aM~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~va1aM~
//      Dump.open("");	//write all to Terminal log,not exception only//~va1aM~
        Dump.open("Dump.txt",true/*sdcard*/);                      //~va1aM~
                                                                   //~va1aM~
        AG.aPlayers=new Players();                                 //~va1aM~
        AG.aAccounts=new Accounts();                               //~va1aM~
        AG.aStatus=new Status();                                   //~va1aM~
        if (Dump.Y) Dump.println("ITUARonChk2.constructor");       //~va1aM~
    }                                                              //~va1aM~
    //*************************************************************************//~va1aR~
    @Before                                                        //~va1aR~
    public void setUp() throws Exception                           //~va1aR~
    {                                                              //~va1aR~
    	MockitoAnnotations.initMocks(this);                        //~va1aI~
    	PowerMockito.mockStatic(CompReqDlg.class);                 //~va1aM~
    	initEnv();                                                 //~va1aR~
        if (Dump.Y) Dump.println("ITUARonChk2.setUp");         //~1506R~//~@@@@R~//~v@@@R~//~va1aR~
    }                                                              //~va1aR~
	//*************************************************************************//~va1aI~
	//*mock static by powerMock                                    //~va1aI~
	//*************************************************************************//~va1aI~
    @Test                                                          //~va1aI~
    public void testChkComplete()                                  //~va1aI~
    {                                                              //~va1aI~
        PowerMockito.when(CompReqDlg.chk1stTake()).thenReturn(true);//~va1aR~
        new ITUARonChkSub().ronTest();                             //~va1aI~
    }                                                              //~va1aI~
	//*************************************************************************//~va1aI~
}//class                                                           //~v@@@R~
