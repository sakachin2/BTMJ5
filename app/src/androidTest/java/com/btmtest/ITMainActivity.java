//*CID://+vagvR~:                                   update#=  34;  //~vagvR~
//******************************************************************//~vafhI~
//2021/11/15 vagv move TilesTest to debug dir                      //~vagvI~
//******************************************************************//~vafhI~
package com.btmtest;

import android.app.Activity;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;               //~0A31R~
import androidx.test.ext.junit.runners.AndroidJUnit4;                  //~0A31I~
//import androidx.test.platform.app.InstrumentationRegistry;       //~0A31R~
//import androidx.test.ext.junit.runners.AndroidJUnit4;            //~0A31R~
//import androidx.test.runners.AndroidJUnit4;                      //~0A31I~
                                                                   //~0A31I~
//import androidx.test.platform.app.InstrumentationRegistry;       //~0A31I~
//import androidx.test.ext.junit.runners.AndroidJUnit4;            //~0A31I~

import com.btmtest.utils.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
//***********************************                              //~0A31R~
                                                                   //~vagvI~
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.btmtest.TestOption.*;                            //~vagvI~
                                                                   //~0A31M~
@RunWith(AndroidJUnit4.class)
public class ITMainActivity                                        //~vagvR~
{                                                                  //~0A31I~
	@Rule                                                          //~vagvI~
    public ActivityScenarioRule<MainActivity> mASR=new ActivityScenarioRule(MainActivity.class);//~vagvR~
    public ActivityScenario mAS;                                   //~vagvR~
    private ITTilesTest mTT=new ITTilesTest();                     //~vagvR~
    //*************************************************************************//~0A31I~
    @Before                                                        //~0A31I~
    public void setUp() throws Exception                           //~0A31M~
    {                                                              //~0A31M~
    	TestOption.aTilesTest=mTT;                                 //~vagvI~
//        Intent intent=new Intent();                              //~vagvR~
//        mATR.launchActivity(intent);                             //~vagvR~
//        Activity activity=mATR.activity;                         //~vagvR~
    }                                                              //~0A31M~
    @After                                                         //~vagvI~
    public void tearDown() throws Exception                        //~vagvI~
    {                                                              //~vagvI~
    	mAS.close();                                               //~vagvI~
        mAS=null;                                                  //~vagvI~
    }                                                              //~vagvI~
    @Test                                                          //~0B01I~
    public void test()                                  //~0B01I~  //~vagvR~
    {                                                              //~0B01I~
        setUpEnv();                                                //~0B01I~
        Espresso.onView(withId(R.id.StartGame)).perform(click());  //~vagvR~
        waitActivity();                                                    //~vagvI~
    }                                                              //~0B01I~
    private void setUpEnv()                                             //~0B01I~
    {                                                              //~0B01I~
	     mAS=mASR.getScenario();                                   //~vagvR~
    }                                                              //~0B01I~
    private void waitActivity()                                            //~vagvI~
    {                                                              //~vagvI~
	     while(true)                                               //~vagvI~
         {                                                         //~vagvI~
         	Utils.sleep(1000L);                                    //~vagvI~
            if (TestOption.swActivityDestroyed)                    //+vagvI~
            	break;                                             //+vagvI~
         }                                                         //~vagvI~
    }                                                              //~vagvI~
}
