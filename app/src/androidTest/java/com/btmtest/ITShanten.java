//*CID://+dateR~:                             update#=   13;       //~1108I~
//******************************************************************//~0B03I~
//2021/01/07 va60 CalcShanten                                      //~1108I~
//******************************************************************//~0B03I~
//*test data:androidTest\assets\calshan_input1                     //~1108I~
//*tablefile:main/assets\calshan_bytetbl.zip                       //~1108I~
//*result Dump(adbhw3pulldump)                                     //~1108I~
//******************************************************************//~1108I~
package com.btmtest;
//******************************************************************//~0B01R~
import android.content.Context;
import android.content.res.Resources;

import androidx.test.platform.app.InstrumentationRegistry;               //~0A31R~
import androidx.test.ext.junit.runners.AndroidJUnit4;                  //~0A31I~
                                                                   //~0B03I~
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//***********************************                              //~0A31R~
                                                                   //~0A31I~
import com.btmtest.game.Accounts;
import com.btmtest.game.Players;
import com.btmtest.game.Status;
import com.btmtest.game.UA.UARonValue;
import com.btmtest.utils.Dump;                                     //~0A31M~
import com.btmtest.game.RA.Shanten;                                //~1108I~
import com.btmtest.AG;                                             //~1108I~
import com.btmtest.utils.UFile;
import com.btmtest.utils.Utils;
//~0A31M~
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import static com.btmtest.game.GCMsgID.*;
import static com.btmtest.StaticVars.AG;                           //~1108I~

//+0A31I~

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ITShanten                                          //~0B03R~//~1108R~
{                                                                  //~0A31I~
//  private	static final String testfile="calshan_input1";         //~1108R~//~1225R~
    private	static final String testfile="calshan_input1.test1";   //~1225I~
    private static int[] itsHand=new int[14];                      //~1108I~
	//*************************************************************************//~0B01I~
    private void initEnv()                                         //~0B01I~
    {                                                              //~0B01I~
        AG=new AG();                                               //~1108R~
    	AG.appName="ｅ雀";                                         //~0B01I~//~1108R~
    	AG.appNameE="eMahJong";                                    //~0B01I~//~1108R~
//      AG.context=InstrumentationRegistry.getInstrumentation().getContext();     //~0B01I~//~1108R~
        AG.context=InstrumentationRegistry.getInstrumentation().getTargetContext();     //~1108I~
        AG.resource=AG.context.getResources();                     //~0B01I~
        AG.dirSep="/";                                             //~0B01I~
                                                                   //~0B01I~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~0B01I~
        Dump.open("Dump.txt",true/*sdcard*/);                      //~0B01I~
        if (Dump.Y) Dump.println("ITShanten.constructor");      //~0B03R~//~1108R~
        AG.createSettings();                                       //+1225I~
        AG.loadProp();                                             //+1225I~
    }                                                              //~0B01I~
    //*************************************************************************//~0A31I~
    @Before                                                        //~0A31I~
    public void setUp() throws Exception                           //~0A31M~
    {                                                              //~0A31M~
        if (Dump.Y) Dump.println("ITShanten.setUp");            //~0B03R~//~1108I~
    	initEnv();                                                 //~0B01I~
        new Shanten();	//access to asset shantenTable             //~1108I~
    }                                                              //~0A31M~
    @Test                                                          //~0B01I~
    public void testShanten()                                  //~0B03R~//~1108R~
    {                                                              //~0B01I~
        testCalcShanten(testfile);                                 //~1108I~
    }                                                              //~0B01I~
//***********************************************                  //~1108I~
//test file                                                        //~1108I~
//1st lien:line ctr                                                //~1108I~
//2nd 14 items of tile id                                          //~1108I~
// man:0-8, pin:9-17, sou:18-26, ji:27-30,31-33                    //~1108I~
//***********************************************                  //~1108I~
	private static boolean testLine(String Pline)                  //~1108I~
    {                                                              //~1108I~
    	String line=Pline.trim();                                  //~1108I~
        String[] hand=line.split(" +");                            //~1108I~
        if (hand.length==1)	//linectr line                         //~1108I~
        	return false;                                          //~1108I~
//      System.out.println("line="+Pline);                         //~1108I~
        Arrays.fill(itsHand,0);                                    //~1108I~
        for (int ii=0;ii<hand.length;ii++)                         //~1108I~
		{                                                          //~1108I~
        	try                                                    //~1108I~
            {                                                      //~1108I~
            	itsHand[ii]=Integer.parseInt(hand[ii]);            //~1108I~
            }                                                      //~1108I~
            catch(NumberFormatException e)                         //~1108I~
            {                                                      //~1108I~
                System.out.println("line="+Pline+"\n"+e.toString());//~1108I~
            }                                                      //~1108I~
        }                                                          //~1108I~
        try                                                        //~1108I~
        {                                                          //~1108I~
        	Dump.println("ctr="+hand.length+",itsHand="+ Utils.toString(itsHand,9));//+1225I~
        	AG.aShanten.getShanten(itsHand,hand.length/*ctr hand*/);//~1108I~
        }                                                          //~1108I~
        catch(Exception e)                                         //~1108I~
        {                                                          //~1108I~
            System.out.println("testLine line="+line+",exception="+e.toString());//~1108I~
            e.printStackTrace();                                   //~1108I~
            System.exit(4);                                        //~1108I~
        }                                                          //~1108I~
        return true;                                               //~1108I~
    }                                                              //~1108I~
////***********************************************                  //~1108I~//~1109R~
//    private static InputStreamReader openTestAsset(String Pfnm)    //~1108R~//~1109R~
//    {                                                              //~1108I~//~1109R~
//        Resources resourceMain=AG.resource;                       //~1108I~//~1109R~
//        Context testContext=InstrumentationRegistry.getInstrumentation().getContext();//~1108I~//~1109R~
//        AG.resource=testContext.getResources();                    //~1108I~//~1109R~
//        InputStream is= UFile.openAssetFile(Pfnm,true);             //~1108I~//~1109R~
//        InputStreamReader isr=new InputStreamReader(is);            //~1108I~//~1109R~
//        AG.resource=AG.resource;                                   //~1108I~//~1109R~
//        return isr;                                                //~1108R~//~1109R~
//    }                                                              //~1108I~//~1109R~
//***********************************************                  //~1108I~
	private static void testCalcShanten(String Pfnm)               //~1108I~
    {                                                              //~1108I~
        int ctrLine=0;                                             //~1108I~
//        try                                                        //~1108I~//~1109R~
//        {                                                          //~1108I~//~1109R~
////          FileReader fr = new FileReader(Pfnm);                  //~1108R~//~1109R~
//            InputStreamReader fr=openTestAsset(Pfnm);              //~1108R~//~1109R~
//            if (fr==null)                                          //~1108I~//~1109R~
//            {                                                      //~1108I~//~1109R~
//                if (Dump.Y) Dump.println("testCalcShanten file:"+Pfnm+" not found");//~1108I~//~1109R~
//                return;                                            //~1108I~//~1109R~
//            }                                                      //~1108I~//~1109R~
//            BufferedReader br=new BufferedReader(fr);              //~1108I~//~1109R~
			ITData.open(Pfnm);                                     //~1109I~
            while(true)                                            //~1108I~
            {                                                      //~1108I~
//          	String line=br.readLine();                         //~1108I~//~1109R~
            	String line=ITData.readLine();                     //~1109I~
                if (line==null)                                    //~1108I~
                	break;                                         //~1108I~
                if (testLine(line))                                //~1108I~
	                ctrLine++;                                     //~1108I~
            }                                                      //~1108I~
            System.out.println("ctrLine="+ctrLine);                //~1108I~//~1109R~
//            br.close();                                            //~1108I~//~1109R~
//        }                                                          //~1108I~//~1109R~
//        catch(Exception e)                                         //~1108I~//~1109R~
//        {                                                          //~1108I~//~1109R~
//            System.out.println("file:"+Pfnm+";"+e.toString());     //~1108I~//~1109R~
//        }                                                          //~1108I~//~1109R~
    }                                                              //~1108I~
}
