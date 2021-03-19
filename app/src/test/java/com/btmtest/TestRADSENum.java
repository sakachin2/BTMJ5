//*CID://+va60R~: update#= 764;                                    //~va60R~
//**********************************************************************//~v101I~
//2021/01/07 va60 CalcShanten                                      //~va60I~
//**********************************************************************//~1107I~
package com.btmtest;                                          //~va1aR~


import com.btmtest.game.RA.RADSENum;                               //~va60R~
import com.btmtest.game.RA.RADSmart;
import com.btmtest.utils.Dump;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

//****************************************************             //~9C11I~
public class TestRADSENum                                          //~va60R~
{                                                                  //~0914I~
    private	static final String datapath="w:\\AndroidStudioProjects\\BTMJ5W\\app\\src\\test\\java\\com\\btmtest\\testdata\\";//~1107I~//~va60R~
    private	static final String testfile=datapath+"radsenum.input";//~va60R~
    private	static final String outfile=datapath+"radsenum_output";//~va60R~
    private	static final String dumpfile=datapath+"Dump.txt";      //~va60I~
//  private static FileWriter fwOut;                               //~1107I~//~va60R~
    private static FileWriter fwDump;                              //~va60I~
    private	static int[] itsHand=new int[36];                      //~va60I~
    private	static int[] itsHandPos=new int[14];                   //~va60I~
    private	static int[] itsHandValue=new int[14];                 //~va60I~
    private	static int[] itsHandMeldDora=new int[14];  //doravalue for dup//~va60I~
    private	static boolean[] btsHandMeldDoraIsRed5=new boolean[14];  //doravalue for dup//+va60I~
    private	static RADSENum RADSEN;                                //~va60I~
    private	static int intent;                                     //~va60I~
    //*************************************************************************//~va60I~
    private void initEnv()                                         //~va1aR~
    {                                                              //~va1aI~
        StaticVars.AG=new AG();                                     //~va1aI~
    	StaticVars.AG.appName="TestRADSENum";                      //~va1aR~//~va60R~
//        Dump.openExOnlyTerminal();	//write exception only to Terminal//~va1aI~
//      Dump.open("");	//write all to Terminal log,not exception only//~va1aI~//~va60R~
    	fwDump=openOutput(dumpfile);                               //~va60M~
        Dump.open(new PrintWriter(fwDump));                        //~va60R~
        if (Dump.Y) Dump.println("TestRADSENum.constructor");      //~va1aI~//~va60R~
    }                                                              //~va1aI~
    //*************************************************************************//~va1aR~
    @Before                                                        //~va1aR~
    public void setUp() throws Exception                           //~va1aR~
    {                                                              //~va1aR~
    	initEnv();                                                 //~va1aI~
//  	fwOut=openOutput(outfile);                                 //~va60R~
        if (Dump.Y) Dump.println("TestRADSENum.setUp");         //~1506R~//~@@@@R~//~v@@@R~//~va1aR~//~va60R~
        new RADSmart();
        RADSEN=new RADSENum();                                     //~va60R~
    }                                                              //~va1aR~
	//*************************************************************************//~va1aM~
    @Test                                                          //~va1aM~
    public void testRADSEnum()                                  //~va1aM~//~va60R~
    {                                                              //~va1aM~
        testRADSENum(testfile);                                    //~va60R~
//  	closeOutput(fwOut);                                        //~va60R~
    	closeOutput(fwDump);                                       //~va60I~
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
		int ctrHand=hand.length;                                   //~va60I~
        Arrays.fill(itsHandPos,0);                                 //~va60R~
        Arrays.fill(itsHand,0);                                    //~va60I~
        if (hand[0].charAt(0)=='D'                                 //+va60R~
        ||  hand[0].charAt(0)=='R')	//dora                         //+va60I~
        {                                                          //~va60I~
        	Arrays.fill(itsHandMeldDora,0);                        //~va60I~
        	Arrays.fill(btsHandMeldDoraIsRed5,false);              //+va60I~
            for (int ii=1;ii<hand.length;ii++)                     //~va60I~
            {                                                      //~va60I~
                try                                                //~va60I~
                {                                                  //~va60I~
                    itsHandMeldDora[ii-1]=Integer.parseInt(hand[ii]);//~va60R~
                    if (itsHandMeldDora[ii-1]!=0)                  //+va60I~
	        			if (hand[0].charAt(0)=='R')	               //+va60I~
    	                	btsHandMeldDoraIsRed5[ii-1]=true;      //+va60I~
                                                                   //+va60I~
                }                                                  //~va60I~
                catch(NumberFormatException e)                     //~va60I~
                {                                                  //~va60I~
                    System.out.println("line="+Pline+"\n"+e.toString());//~va60I~
                }                                                  //~va60I~
            }                                                      //~va60I~
            return true;                                                //~va60I~
        }                                                          //~va60I~
        if (hand[0].charAt(0)=='I')	//intent                       //~va60I~
        {                                                          //~va60I~
                try                                                //~va60I~
                {                                                  //~va60I~
                    intent=Integer.parseInt(hand[1]);              //~va60I~
                }                                                  //~va60I~
                catch(NumberFormatException e)                     //~va60I~
                {                                                  //~va60I~
                    System.out.println("line="+Pline+"\n"+e.toString());//~va60I~
                }                                                  //~va60I~
            return true;                                           //~va60I~
        }                                                          //~va60I~
        for (int ii=0;ii<hand.length;ii++)                         //~va60I~
		{                                                          //~va60I~
        	try                                                    //~va60I~
            {                                                      //~va60I~
            	itsHandPos[ii]=Integer.parseInt(hand[ii]);         //~va60R~
                itsHand[itsHandPos[ii]]++;                         //~va60I~
            }                                                      //~va60I~
            catch(NumberFormatException e)                         //~va60I~
            {                                                      //~va60I~
                System.out.println("line="+Pline+"\n"+e.toString());//~va60I~
            }                                                      //~va60I~
        }                                                          //~va60I~
        try                                                        //~va60I~
        {                                                          //~va60I~
        	Arrays.fill(itsHandValue,0);                           //~va60I~
        	RADSEN.chkNumberMeld4_Test(intent,itsHandPos,ctrHand,itsHand,itsHandValue,itsHandMeldDora,btsHandMeldDoraIsRed5);//+va60R~
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
	private static void testRADSENum(String Pfnm)                  //~va60R~
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
                if (line.charAt(0)=='*')                           //~va60I~
                    continue;                                      //~va60I~
                if (testLine(line))                                //~va60I~
	                ctrLine++;                                     //~va60I~
            }                                                      //~va60I~
            System.out.println("ctrLine="+ctrLine);                //~va60I~
            br.close();                                            //~va60I~
        }                                                          //~va60I~
        catch(Exception e)                                         //~va60I~
        {                                                          //~va60I~
        	System.out.println("file:"+Pfnm+";"+e.toString());     //~va60I~
//  		closeOutput(fwOut);                                    //~va60R~
    		closeOutput(fwDump);                                   //~va60I~
        }                                                          //~va60I~
    }                                                              //~va60I~
//***********************************************                  //~1107I~//~va60I~
    private static FileWriter openOutput(String Pfnm)                    //~1107I~//~va60R~
    {                                                              //~1107I~//~va60I~
        FileWriter fwOut=null;                                                //~1107I~//~va60R~
        try                                                        //~1107I~//~va60I~
        {                                                          //~1107I~//~va60I~
            File f=new File(Pfnm);                                 //~1107I~//~va60I~
            fwOut=new FileWriter(f);                               //~1107R~//~va60I~
        }                                                          //~1107I~//~va60I~
        catch(IOException e)                                       //~1107I~//~va60I~
        {                                                          //~1107I~//~va60I~
            System.out.println("openOutput file:"+Pfnm+";"+e.toString());//~1107I~//~va60I~
        }                                                          //~1107I~//~va60I~
        return fwOut;                                              //~va60I~
    }                                                              //~1107I~//~va60I~
////***********************************************                  //~1107I~//~va60R~
//    private static void writeLine(String Ptext)                    //~1107I~//~va60R~
//    {                                                              //~1107I~//~va60R~
//        try                                                        //~1107I~//~va60R~
//        {                                                          //~1107I~//~va60R~
//            fwOut.write(Ptext+"\n");                                    //~1107I~//~va60R~
//        }                                                          //~1107I~//~va60R~
//        catch(IOException e)                                       //~1107I~//~va60R~
//        {                                                          //~1107I~//~va60R~
//            System.out.println("writeLine text="+Ptext+",exception="+e.toString());//~1107I~//~va60R~
//        }                                                          //~1107I~//~va60R~
//    }                                                              //~1107I~//~va60R~
//***********************************************                  //~1107I~//~va60I~
    private static void closeOutput(FileWriter Pfw)                              //~1107I~//~va60R~
    {                                                              //~1107I~//~va60I~
        if (Pfw!=null)                                           //~1107I~//~va60R~
            try                                                    //~1107I~//~va60I~
            {                                                      //~1107I~//~va60I~
                Pfw.close();                                     //~1107I~//~va60R~
            }                                                      //~1107I~//~va60I~
            catch(IOException e)                                   //~1107I~//~va60I~
            {                                                      //~1107I~//~va60I~
                System.out.println("closeOutput exception="+e.toString());//~1107I~//~va60I~
            }                                                      //~1107I~//~va60I~
    }                                                              //~1107I~//~va60I~
}//class                                                           //~v@@@R~
