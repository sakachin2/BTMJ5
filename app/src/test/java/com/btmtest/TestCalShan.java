//*CID://+dateR~:                             update#=    3;       //~1107I~
//**********************************************************************//~v101I~
//**********************************************************************//~1107I~
package com.btmtest;                                          //~va1aR~

import com.btmtest.game.RA.CalShan;                                //~1107R~

import org.junit.Before;
import org.junit.Test;

import static com.btmtest.game.GConst.*;
//****************************************************             //~9C11I~
public class TestCalShan                                           //~1107R~
{                                                                  //~0914I~
    private	String datapath="w:\\AndroidStudioProjects\\BTMJ5W\\app\\src\\test\\java\\com\\btmtest\\testdata\\";//~1107I~
    private	String tblfile=datapath+"calshan_bytetbl.zip";         //+1107R~
    private	String outfile=datapath+"calshan_output";              //~1107I~
    //*************************************************************************//~va1aR~
    @Before                                                        //~va1aR~
    public void setUp() throws Exception                           //~va1aR~
    {                                                              //~va1aR~
    }                                                              //~va1aR~
	//*************************************************************************//~va1aM~
    @Test                                                          //~va1aM~
    public void testcalShanFalse()                                  //~va1aM~//~1107R~
    {                                                              //~va1aM~
        String[] args={datapath+"calshan_input1",tblfile,"false",outfile};//~1107I~
        CalShan.main(args);                                        //~1107R~
    }                                                              //~va1aM~
//    //*************************************************************************//+1107R~
//    @Test                                                        //+1107R~
//    public void testcalShanTrue()                                //+1107R~
//    {                                                            //+1107R~
//        String[] args={datapath+"calshan_input1",tblfile,"true",outfile};//+1107R~
//        CalShan.main(args);                                      //+1107R~
//    }                                                            //+1107R~
	//*************************************************************************//~va1aI~
}//class                                                           //~v@@@R~
