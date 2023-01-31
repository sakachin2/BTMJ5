//*CID://+dateR~:                             update#=    7;       //~1107I~
//**********************************************************************//~v101I~
//**********************************************************************//~1107I~
package com.btmtest;                                          //~va1aR~

import com.btmtest.game.RA.CalShan;                                //~1107R~

import org.junit.Before;
import org.junit.Test;

import static com.btmtest.game.GConst.*;
//****************************************************             //~9C11I~
public class TestShuffle2                                           //~1107R~//~1B19R~//~3109R~
{                                                                  //~0914I~
    private	String datapath="w:\\AndroidStudioProjects\\BTMJ5W\\app\\src\\test\\java\\com\\btmtest\\testdata\\";//~1107I~
    private	String outfile=datapath+"testShuffle2_output";              //~1107I~//~1B19R~//~3109R~
    //*************************************************************************//~va1aR~
    @Before                                                        //~va1aR~
    public void setUp() throws Exception                           //~va1aR~
    {                                                              //~va1aR~
    }                                                              //~va1aR~
	//*************************************************************************//~va1aM~
    @Test                                                          //~va1aM~
    public void test()                                  //~va1aM~//~1107R~//~1B19R~
    {                                                              //~va1aM~
        String[] args={outfile};//~1107I~                          //~1B19R~
        Shuffle2.main(args);                                        //~1107R~//~1B19R~//+3109R~
    }                                                              //~va1aM~
}//class                                                           //~v@@@R~
