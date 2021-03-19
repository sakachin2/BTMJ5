//*CID://+va60R~: update#= 748;                                    //~va60R~
//**********************************************************************//~v101I~
//2021/01/07 va60 CalcShanten                                      //~va60I~
//**********************************************************************//~1107I~
package com.btmtest;                                          //~va1aR~


import com.btmtest.game.RA.Shanten;                                //~va60R~
import com.btmtest.utils.Dump;
import com.btmtest.AG;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static com.btmtest.game.GConst.*;
import static com.btmtest.StaticVars.AG;                           //~va60I~
//****************************************************             //~9C11I~
public class TestShanten                                           //~va60R~
{                                                                  //~0914I~
    private	static final String datapath="w:\\AndroidStudioProjects\\BTMJ5W\\app\\src\\test\\java\\com\\btmtest\\testdata\\";//~1107I~//~va60R~
    private	static final String testfile=datapath+"calshan_input1";//~va60R~
    private	static final String outfile=datapath+"calshan_output"; //~va60I~
    private	static final String tblfile=datapath+"calshan_bytetbl.zip";//~va60I~
    private static int[] itsHand=new int[14];                      //~va60R~
    private static FileWriter fwOut;                               //~1107I~//~va60I~
    //*************************************************************************//~va60I~
    private void initEnv()                                         //~va1aR~
    {                                                              //~va1aI~
        StaticVars.AG=new AG();                                     //~va1aI~
    	StaticVars.AG.appName="TestShanten";                      //~va1aR~//~va60R~
        Dump.openExOnlyTerminal();	//write exception only to Terminal//~va1aI~
        Dump.open("");	//write all to Terminal log,not exception only//~va1aI~
        if (Dump.Y) Dump.println("TestShanten.constructor");      //~va1aI~//~va60R~
    }                                                              //~va1aI~
    //*************************************************************************//~va1aR~
    @Before                                                        //~va1aR~
    public void setUp() throws Exception                           //~va1aR~
    {                                                              //~va1aR~
    	initEnv();                                                 //~va1aI~
    	openOutput(outfile);                                       //~va60I~
        if (Dump.Y) Dump.println("TestShanten.setUp");         //~1506R~//~@@@@R~//~v@@@R~//~va1aR~//~va60R~
        new Shanten(tblfile,fwOut);	//could not access to asset when UnitTest//~va60R~
    }                                                              //~va1aR~
	//*************************************************************************//~va1aM~
    @Test                                                          //~va1aM~
    public void testShanten()                                  //~va1aM~//~va60R~
    {                                                              //~va1aM~
        testCalcShanten(testfile);                                 //~va60R~
    	closeOutput();                                        //~va60I~
    }                                                              //~va1aM~     //~va60I~
//***********************************************                  //~va60I~
//test file                                                        //~va60I~
//1st lien:line ctr                                                //~va60I~
//2nd 14 items of tile id                                          //~va60I~
// man:0-8, pin:9-17, sou:18-26, ji:27-30,31-33                    //~va60I~
//***********************************************                  //~va60I~
	private static boolean testLine(String Pline)                  //~va60I~
    {                                                              //~va60I~
    	String line=Pline.trim();                                  //~va60I~
        String[] hand=line.split(" +");                            //~va60I~
        if (hand.length==1)	//linectr line                         //~va60I~
        	return false;                                          //~va60I~
//      System.out.println("line="+Pline);                         //~va60I~
        Arrays.fill(itsHand,0);                                    //~va60I~
        for (int ii=0;ii<hand.length;ii++)                         //~va60I~
		{                                                          //~va60I~
        	try                                                    //~va60I~
            {                                                      //~va60I~
            	itsHand[ii]=Integer.parseInt(hand[ii]);            //~va60I~
            }                                                      //~va60I~
            catch(NumberFormatException e)                         //~va60I~
            {                                                      //~va60I~
                System.out.println("line="+Pline+"\n"+e.toString());//~va60I~
            }                                                      //~va60I~
        }                                                          //~va60I~
        try                                                        //~va60I~
        {                                                          //~va60I~
        	AG.aShanten.getShanten(itsHand,hand.length/*ctr hand*/);//+va60R~
        }                                                          //~va60I~
        catch(Exception e)                                         //~va60I~
        {                                                          //~va60I~
            System.out.println("testLine line="+line+",exception="+e.toString());//~va60I~
            e.printStackTrace();                                   //~va60I~
            System.exit(4);                                        //~va60I~
        }                                                          //~va60I~
        return true;                                               //~va60I~
    }                                                              //~va60I~
//***********************************************                  //~va60I~
	private static void testCalcShanten(String Pfnm)               //~va60I~
    {                                                              //~va60I~
        int ctrLine=0;                                             //~va60I~
        try                                                        //~va60I~
		{                                                          //~va60I~
            FileReader fr = new FileReader(Pfnm);                  //~va60I~
            BufferedReader br=new BufferedReader(fr);              //~va60I~
            while(true)                                            //~va60I~
            {                                                      //~va60I~
            	String line=br.readLine();                         //~va60I~
                if (line==null)                                    //~va60I~
                	break;                                         //~va60I~
                if (testLine(line))                                //~va60I~
	                ctrLine++;                                     //~va60I~
            }                                                      //~va60I~
            System.out.println("ctrLine="+ctrLine);                //~va60I~
            br.close();                                            //~va60I~
        }                                                          //~va60I~
        catch(Exception e)                                         //~va60I~
        {                                                          //~va60I~
        	System.out.println("file:"+Pfnm+";"+e.toString());     //~va60I~
        }                                                          //~va60I~
    }                                                              //~va60I~
//***********************************************                  //~1107I~//~va60I~
    private static void openOutput(String Pfnm)                    //~1107I~//~va60I~
    {                                                              //~1107I~//~va60I~
        fwOut=null;                                                //~1107I~//~va60I~
        try                                                        //~1107I~//~va60I~
        {                                                          //~1107I~//~va60I~
            File f=new File(Pfnm);                                 //~1107I~//~va60I~
            fwOut=new FileWriter(f);                               //~1107R~//~va60I~
        }                                                          //~1107I~//~va60I~
        catch(IOException e)                                       //~1107I~//~va60I~
        {                                                          //~1107I~//~va60I~
            System.out.println("openOutput file:"+Pfnm+";"+e.toString());//~1107I~//~va60I~
        }                                                          //~1107I~//~va60I~
    }                                                              //~1107I~//~va60I~
//***********************************************                  //~1107I~//~va60I~
    private static void writeLine(String Ptext)                    //~1107I~//~va60I~
    {                                                              //~1107I~//~va60I~
        try                                                        //~1107I~//~va60I~
        {                                                          //~1107I~//~va60I~
            fwOut.write(Ptext+"\n");                                    //~1107I~//~va60I~
        }                                                          //~1107I~//~va60I~
        catch(IOException e)                                       //~1107I~//~va60I~
        {                                                          //~1107I~//~va60I~
            System.out.println("writeLine text="+Ptext+",exception="+e.toString());//~1107I~//~va60I~
        }                                                          //~1107I~//~va60I~
    }                                                              //~1107I~//~va60I~
//***********************************************                  //~1107I~//~va60I~
    private static void closeOutput()                              //~1107I~//~va60I~
    {                                                              //~1107I~//~va60I~
        if (fwOut!=null)                                           //~1107I~//~va60I~
            try                                                    //~1107I~//~va60I~
            {                                                      //~1107I~//~va60I~
                fwOut.close();                                     //~1107I~//~va60I~
            }                                                      //~1107I~//~va60I~
            catch(IOException e)                                   //~1107I~//~va60I~
            {                                                      //~1107I~//~va60I~
                System.out.println("closeOutput exception="+e.toString());//~1107I~//~va60I~
            }                                                      //~1107I~//~va60I~
    }                                                              //~1107I~//~va60I~
}//class                                                           //~v@@@R~
